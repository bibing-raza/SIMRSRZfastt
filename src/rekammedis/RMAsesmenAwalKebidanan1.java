package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author perpustakaan
 */
public final class RMAsesmenAwalKebidanan1 extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabModeRiwayat;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, psLaprm;
    private ResultSet rs, rs1, rs2, rs3, rsPrev, rsLaprm;
    private int i = 0, x = 0, skor = 0, pilihan = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private RMAsesmenAwalKebidanan2 halaman2 = new RMAsesmenAwalKebidanan2(null, false);
    private String wktSimpan = "", sendiri = "", rujukan = "", pkm = "", spog = "", rsLain = "", dismen = "", spoting = "", menor = "", metro = "",
            lainKeluhanHaid = "", hipertensiDahulu = "", dmDahulu = "", jantungDahulu = "", asmaDahulu = "", lainyaDahulu = "", hipertensiKeluarga = "",
            dmKeluarga = "", jantungKeluarga = "", asmaKeluarga = "", lainyaKeluarga = "", pil = "", suntik1 = "", suntik3 = "", implan = "", iud = "",
            tidakKb = "", istriKawin = "", suamiKawin = "", orangTua = "", suami = "", anak = "", tinggalSendiri = "", asuransi = "", jaminan = "",
            biayaSendiri = "", lainStatusEkonomi = "", bersih = "", oedema = "", ruftur = "", candiloma = "", lainPemeriksaanGeni = "", alamatSama = "",
            stsrwt = "";

    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsesmenAwalKebidanan1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang/Poli/Inst.", "Tgl. Asesmen", "Umur Pasien", "Pekerjaan Pasien", "Agama", "Alamat Pasien", "Status Nikah", "Nama Bidan", "Nama Dokter",
            "ruang_rawat", "tgl_asesmen", "jam_asesmen", "nm_suami", "umur_suami", "pekerjaan_suami", "alamat_suami", "agama_suami", "alasan_masuk", "td", "nadi", "respirasi",
            "suhu", "kesadaran", "saturasi", "cek_sendiri", "cek_rujukan", "jns_rujukan", "ket_jns_rujukan", "cek_pkm", "ket_pkm", "cek_spog", "cek_rs_lain", "ket_rs_lain", "gr",
            "pr", "a", "hamil", "gpapah", "dengan", "perut", "keluhan_perut", "tgl_perut", "jam_perut", "keluar", "keluhan_keluar", "tgl_keluar_lendir", "jam_keluar_lendir", "darah",
            "keluhan_darah", "jns_darah", "tgl_darah", "jam_darah", "keluar_air", "keluhan_keluar_air", "jns_keluar_air", "tgl_keluar_air", "jam_keluar_air", "pergerakan_janin_2jam_terakhir",
            "ket_pergerakan_janin_2jam_terakhir", "pusing", "tgl_pusing", "jam_pusing", "nyeri_ulu_hati", "tgl_nyeri_ulu_hati", "jam_nyeri_ulu_hati", "pandangan_kabur", "tgl_pandangan_kabur",
            "jam_pandangan_kabur", "odema", "tgl_odema", "odema_di", "mual", "tgl_mual", "jam_mual", "muntah", "tgl_muntah", "jam_muntah", "batuk", "tgl_batuk", "jam_batuk", "pilek",
            "tgl_pilek", "jam_pilek", "demam", "tgl_demam", "jam_demam", "riw_perjalanan_jauh", "ket_riw_perjalanan_jauh", "vaksin_covid19", "jlh_vaksin_covid19", "periksa_ketempat_bidan",
            "hasil_pemeriksaan_bidan", "ibu_anc", "jns_anc", "jlh_anc", "dengan_dokter1", "jlh_dengan_dokter1", "dengan_dokter2", "jlh_dengan_dokter2", "dengan_dokter3", "jlh_dengan_dokter3",
            "hpht", "hpl", "uk", "bb_sebelum_hamil", "bb_terakhir", "tbi", "umur_pertama_haid", "lama_haid", "berapa_kali_ganti_pembalut", "keluhan_waktu_haid", "cek_dismen", "cek_spoting",
            "cek_menor", "cek_metro", "cek_lain_keluhan_haid", "ket_lain_keluhan_haid", "riw_penyakit_dahulu", "cek_hipertensi_dahulu", "cek_dm_dahulu", "cek_jantung_dahulu", "cek_asma_dahulu",
            "cek_lainya_dahulu", "ket_lain_penyakit_dahulu", "riw_penyakit_keluarga", "cek_hipertensi_keluarga", "cek_dm_keluarga", "cek_jantung_keluarga", "cek_asma_keluarga", "cek_lainya_keluarga",
            "ket_lain_penyakit_keluarga", "riw_ginekologi", "ket_ginekologi", "cek_pil", "lama_pil", "satuan_lama_pil", "cek_suntik1", "lama_suntik1", "satuan_lama_suntik1", "cek_suntik3",
            "lama_suntik3", "satuan_lama_suntik3", "cek_implan", "lama_implan", "satuan_lama_implan", "cek_iud", "lama_iud", "satuan_lama_iud", "cek_tidak_kb", "status_perkawinan", "cek_istri_kawin",
            "cek_suami_kawin", "jlh_perkawinan_istri", "jlh_perkawinan_suami", "usia_pertama_nikah", "usia_perkawinan", "keluarga_terdekat", "hubungan", "cek_orang_tua", "cek_suami", "cek_anak",
            "cek_tinggal_sendiri", "curiga_penganiayaan", "kegiatan_ibadah", "status_emosional", "cek_asuransi", "cek_jaminan", "cek_biaya_sendiri", "cek_lain_status_ekonomi", "ket_lain_status_ekonomi",
            "leopold1", "leopold2", "leopold3", "leopold4", "bandle_ring", "perut_tegang", "palpasi", "teraba_massa", "sebesar", "goyang", "nyeri_tekan", "vt_pembukaan", "vt_nyeri_goyang", "tfu",
            "taksiran_berat_janin", "his_kontraksi", "jns_his_kontraksi", "durasi", "jns_durasi", "auskultasi", "cek_bersih", "cek_oedema", "cek_ruftur", "cek_candiloma", "cek_lain_pemeriksaan_geni",
            "ket_lain_pemeriksaan_geni", "periksa_dalam_obstetri", "inspekulo", "hasil_inspekulo", "diagnosis_sementara", "icd_10", "planing", "waktu_simpan", "status_rawat"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 208; i++) {
            TableColumn column = tbAsesmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(220);
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
            } else if (i == 161) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 162) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 163) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 164) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 165) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 166) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 167) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 168) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 169) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 170) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 171) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 172) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 173) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 174) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 175) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 176) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 177) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 178) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 179) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 180) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 181) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 182) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 183) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 184) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 185) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 186) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 187) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 188) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 189) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 190) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 191) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 192) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 193) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 194) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 195) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 196) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 197) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 198) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 199) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 200) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 201) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 202) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 203) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 204) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 205) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 206) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 207) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRiwayat = new DefaultTableModel(null, new String[]{
            "no_rawat", "Thn. Partus", "Tempat Partus", "Umur Hamil", "Jns. Persalinan", "Penolong Persalinan",
            "Penyulit", "Jns. Kelamin", "Brt. Lahir", "Keadaan Anak Skrng.", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {return false;}
        };
        
        tbRiwayat.setModel(tabModeRiwayat);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 11; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(140);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(140);
            } else if (i == 5) {
                column.setPreferredWidth(140);
            } else if (i == 6) {
                column.setPreferredWidth(140);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(140);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());

        TnamaSuami.setDocument(new batasInput((int) 255).getKata(TnamaSuami));
        TumurSuami.setDocument(new batasInput((int) 3).getKata(TumurSuami));
        TpekerjaanSuami.setDocument(new batasInput((int) 150).getKata(TpekerjaanSuami));
        TalasanMskRS.setDocument(new batasInput((int) 255).getKata(TalasanMskRS));
        Ttd.setDocument(new batasInput((int) 7).getKata(Ttd));
        Tnadi.setDocument(new batasInput((int) 7).getKata(Tnadi));
        Trespi.setDocument(new batasInput((int) 7).getKata(Trespi));
        Tsuhu.setDocument(new batasInput((int) 7).getKata(Tsuhu));
        Tkesadaran.setDocument(new batasInput((int) 180).getKata(Tkesadaran));
        Tsaturasi.setDocument(new batasInput((int) 7).getKata(Tsaturasi));
        TketRujukan.setDocument(new batasInput((int) 180).getKata(TketRujukan));
        TketPkm.setDocument(new batasInput((int) 180).getKata(TketPkm));
        TketRsLain.setDocument(new batasInput((int) 180).getKata(TketRsLain));
        Tgr.setDocument(new batasInput((int) 7).getKata(Tgr));
        Tpr.setDocument(new batasInput((int) 7).getKata(Tpr));
        Ta.setDocument(new batasInput((int) 7).getKata(Ta));
        Thamil.setDocument(new batasInput((int) 3).getKata(Thamil));
        Tgpapah.setDocument(new batasInput((int) 100).getKata(Tgpapah));
        Tdengan.setDocument(new batasInput((int) 180).getKata(Tdengan));
        Tpergerakan.setDocument(new batasInput((byte) 3).getOnlyAngka(Tpergerakan));
        TketRiwPerjalanan.setDocument(new batasInput((int) 180).getKata(TketRiwPerjalanan));
        TketVaksin.setDocument(new batasInput((byte) 3).getOnlyAngka(TketVaksin));
        TjlhAnc.setDocument(new batasInput((byte) 3).getOnlyAngka(TjlhAnc));
        TnmDokter1.setDocument(new batasInput((int) 255).getKata(TnmDokter1));
        TjlhDokter1.setDocument(new batasInput((byte) 3).getOnlyAngka(TjlhDokter1));
        TnmDokter2.setDocument(new batasInput((int) 255).getKata(TnmDokter2));
        TjlhDokter2.setDocument(new batasInput((byte) 3).getOnlyAngka(TjlhDokter2));
        TnmDokter3.setDocument(new batasInput((int) 255).getKata(TnmDokter3));
        TjlhDokter3.setDocument(new batasInput((byte) 3).getOnlyAngka(TjlhDokter3));
        Thpht.setDocument(new batasInput((int) 180).getKata(Thpht));
        Thpl.setDocument(new batasInput((int) 180).getKata(Thpl));
        Tuk.setDocument(new batasInput((int) 7).getKata(Tuk));
        TbbSebelum.setDocument(new batasInput((int) 7).getKata(TbbSebelum));
        TbbTerakhir.setDocument(new batasInput((int) 7).getKata(TbbTerakhir));
        Ttbi.setDocument(new batasInput((int) 7).getKata(Ttbi));
        TumurPertama.setDocument(new batasInput((byte) 3).getOnlyAngka(TumurPertama));
        TlamaHaid.setDocument(new batasInput((byte) 3).getOnlyAngka(TlamaHaid));
        Tberapa.setDocument(new batasInput((byte) 3).getOnlyAngka(Tberapa));
        TkeluhanLain.setDocument(new batasInput((int) 180).getKata(TkeluhanLain));
        TlainDahulu.setDocument(new batasInput((int) 180).getKata(TlainDahulu));
        TlainKeluarga.setDocument(new batasInput((int) 180).getKata(TlainKeluarga));
        TriwGinekologi.setDocument(new batasInput((int) 180).getKata(TriwGinekologi));
        TlamaPil.setDocument(new batasInput((int) 4).getKata(TlamaPil));
        TlamaSuntik1.setDocument(new batasInput((int) 4).getKata(TlamaSuntik1));
        TlamaSuntik3.setDocument(new batasInput((int) 4).getKata(TlamaSuntik3));
        TlamaImplan.setDocument(new batasInput((int) 4).getKata(TlamaImplan));
        TlamaIud.setDocument(new batasInput((int) 4).getKata(TlamaIud));
        TthnPartus.setDocument(new batasInput((byte) 4).getOnlyAngka(TthnPartus));
        TtempatPartus.setDocument(new batasInput((int) 150).getKata(TtempatPartus));
        TumurHamil.setDocument(new batasInput((int) 15).getKata(TumurHamil));
        TjnsPersalinan.setDocument(new batasInput((int) 150).getKata(TjnsPersalinan));
        Tpenolong.setDocument(new batasInput((int) 150).getKata(Tpenolong));
        Tpenyulit.setDocument(new batasInput((int) 150).getKata(Tpenyulit));
        TbrtLahir.setDocument(new batasInput((int) 15).getKata(TbrtLahir));
        TusiaPertama.setDocument(new batasInput((byte) 4).getOnlyAngka(TusiaPertama));
        TusiaPerkawinan.setDocument(new batasInput((byte) 3).getOnlyAngka(TusiaPerkawinan));
        TklgTerdekat.setDocument(new batasInput((int) 150).getKata(TklgTerdekat));
        ThubKeluarga.setDocument(new batasInput((int) 150).getKata(ThubKeluarga));
        TsttsLainEkonomi.setDocument(new batasInput((int) 180).getKata(TsttsLainEkonomi));
        Tleo1.setDocument(new batasInput((int) 255).getKata(Tleo1));        
        Tleo2.setDocument(new batasInput((int) 255).getKata(Tleo2));
        Tleo3.setDocument(new batasInput((int) 255).getKata(Tleo3));
        Tleo4.setDocument(new batasInput((int) 255).getKata(Tleo4));
        Tpalpasi.setDocument(new batasInput((int) 180).getKata(Tpalpasi));
        Tsebesar.setDocument(new batasInput((int) 180).getKata(Tsebesar));
        TvtPembukaan.setDocument(new batasInput((int) 180).getKata(TvtPembukaan));
        Ttfu.setDocument(new batasInput((int) 7).getKata(Ttfu));
        Ttaksiran.setDocument(new batasInput((int) 7).getKata(Ttaksiran));
        ThisKontraksi.setDocument(new batasInput((int) 7).getKata(ThisKontraksi));
        Tdurasi.setDocument(new batasInput((int) 7).getKata(Tdurasi));
        Tauskultasi.setDocument(new batasInput((int) 7).getKata(Tauskultasi));
        TlainPemeriksaan.setDocument(new batasInput((int) 180).getKata(TlainPemeriksaan));
        Ticd.setDocument(new batasInput((int) 10).getKata(Ticd));        
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMAsesmenAwalKebidanan1")) {
                    if (pilihan == 1) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            TnmDokter1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDokter1.requestFocus();
                        }
                    } else if (pilihan == 2) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            TnmDokter2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDokter2.requestFocus();
                        }
                    } else if (pilihan == 3) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            TnmDokter3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDokter3.requestFocus();
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
        
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(".isi td{border-right: 1px solid #edf2e8;font: 10px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}");
        Document doc = kit.createDefaultDocument();
        
        LoadHTML1.setEditable(true);        
        LoadHTML1.setEditorKit(kit);        
        LoadHTML1.setDocument(doc);
        LoadHTML1.setEditable(false);        
        LoadHTML1.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel205 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel206 = new widget.Label();
        TtglAsesmen = new widget.Tanggal();
        jLabel207 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel208 = new widget.Label();
        jLabel119 = new widget.Label();
        jLabel209 = new widget.Label();
        TumurPasien = new widget.TextBox();
        jLabel210 = new widget.Label();
        TpekerjaanPasien = new widget.TextBox();
        jLabel211 = new widget.Label();
        TalamatPasien = new widget.TextBox();
        jLabel212 = new widget.Label();
        TagamaPasien = new widget.TextBox();
        jLabel213 = new widget.Label();
        TnamaSuami = new widget.TextBox();
        jLabel214 = new widget.Label();
        TumurSuami = new widget.TextBox();
        jLabel215 = new widget.Label();
        TpekerjaanSuami = new widget.TextBox();
        jLabel216 = new widget.Label();
        TalamatSuami = new widget.TextBox();
        chkAlamatSama = new widget.CekBox();
        jLabel217 = new widget.Label();
        cmbAgamaSuami = new widget.ComboBox();
        jLabel218 = new widget.Label();
        TalasanMskRS = new widget.TextBox();
        jLabel120 = new widget.Label();
        jLabel219 = new widget.Label();
        Ttd = new widget.TextBox();
        jLabel220 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel221 = new widget.Label();
        Trespi = new widget.TextBox();
        jLabel222 = new widget.Label();
        Tsuhu = new widget.TextBox();
        jLabel223 = new widget.Label();
        Tsaturasi = new widget.TextBox();
        jLabel224 = new widget.Label();
        jLabel227 = new widget.Label();
        Tkesadaran = new widget.TextBox();
        jLabel228 = new widget.Label();
        chkSendiri = new widget.CekBox();
        chkRujukan = new widget.CekBox();
        cmbJnsRujukan = new widget.ComboBox();
        TketRujukan = new widget.TextBox();
        chkSpog = new widget.CekBox();
        chkPkm = new widget.CekBox();
        TketPkm = new widget.TextBox();
        chkRsLain = new widget.CekBox();
        TketRsLain = new widget.TextBox();
        jLabel229 = new widget.Label();
        Tgr = new widget.TextBox();
        jLabel230 = new widget.Label();
        Tpr = new widget.TextBox();
        jLabel231 = new widget.Label();
        Ta = new widget.TextBox();
        jLabel232 = new widget.Label();
        Thamil = new widget.TextBox();
        jLabel233 = new widget.Label();
        Tgpapah = new widget.TextBox();
        jLabel234 = new widget.Label();
        Tdengan = new widget.TextBox();
        jLabel121 = new widget.Label();
        jLabel235 = new widget.Label();
        cmbPerut = new widget.ComboBox();
        cmbKeluhanPerut = new widget.ComboBox();
        jLabel236 = new widget.Label();
        TtglPerut = new widget.Tanggal();
        jLabel237 = new widget.Label();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        jLabel238 = new widget.Label();
        jLabel239 = new widget.Label();
        cmbKeluar = new widget.ComboBox();
        cmbKeluhanKeluar = new widget.ComboBox();
        jLabel240 = new widget.Label();
        TtglKeluar = new widget.Tanggal();
        jLabel241 = new widget.Label();
        cmbJam3 = new widget.ComboBox();
        cmbMnt3 = new widget.ComboBox();
        cmbDtk3 = new widget.ComboBox();
        jLabel242 = new widget.Label();
        jLabel243 = new widget.Label();
        cmbDarah = new widget.ComboBox();
        cmbKeluhanDarah = new widget.ComboBox();
        cmbJnsDarah = new widget.ComboBox();
        jLabel244 = new widget.Label();
        TtglDarah = new widget.Tanggal();
        jLabel245 = new widget.Label();
        cmbJam4 = new widget.ComboBox();
        cmbMnt4 = new widget.ComboBox();
        cmbDtk4 = new widget.ComboBox();
        jLabel246 = new widget.Label();
        jLabel247 = new widget.Label();
        cmbKeluarAir = new widget.ComboBox();
        cmbJnsKeluarAir = new widget.ComboBox();
        jLabel248 = new widget.Label();
        TtglKeluarAir = new widget.Tanggal();
        jLabel249 = new widget.Label();
        cmbJam5 = new widget.ComboBox();
        cmbMnt5 = new widget.ComboBox();
        cmbDtk5 = new widget.ComboBox();
        jLabel250 = new widget.Label();
        cmbKeluhanKeluarAir = new widget.ComboBox();
        jLabel251 = new widget.Label();
        cmbPergerakan = new widget.ComboBox();
        Tpergerakan = new widget.TextBox();
        jLabel252 = new widget.Label();
        jLabel253 = new widget.Label();
        cmbPusing = new widget.ComboBox();
        jLabel254 = new widget.Label();
        TtglPusing = new widget.Tanggal();
        jLabel255 = new widget.Label();
        cmbJam6 = new widget.ComboBox();
        cmbMnt6 = new widget.ComboBox();
        cmbDtk6 = new widget.ComboBox();
        jLabel256 = new widget.Label();
        jLabel257 = new widget.Label();
        cmbNyeriUlu = new widget.ComboBox();
        jLabel258 = new widget.Label();
        TtglNyeriUlu = new widget.Tanggal();
        jLabel259 = new widget.Label();
        cmbJam7 = new widget.ComboBox();
        cmbMnt7 = new widget.ComboBox();
        cmbDtk7 = new widget.ComboBox();
        jLabel260 = new widget.Label();
        jLabel261 = new widget.Label();
        cmbPandangan = new widget.ComboBox();
        jLabel262 = new widget.Label();
        TtglPandangan = new widget.Tanggal();
        jLabel263 = new widget.Label();
        cmbJam8 = new widget.ComboBox();
        cmbMnt8 = new widget.ComboBox();
        cmbDtk8 = new widget.ComboBox();
        jLabel264 = new widget.Label();
        jLabel265 = new widget.Label();
        cmbOdema = new widget.ComboBox();
        jLabel266 = new widget.Label();
        TtglOdema = new widget.Tanggal();
        jLabel267 = new widget.Label();
        cmbOdemaDi = new widget.ComboBox();
        jLabel268 = new widget.Label();
        cmbMual = new widget.ComboBox();
        jLabel269 = new widget.Label();
        TtglMual = new widget.Tanggal();
        jLabel270 = new widget.Label();
        cmbJam9 = new widget.ComboBox();
        cmbMnt9 = new widget.ComboBox();
        cmbDtk9 = new widget.ComboBox();
        jLabel271 = new widget.Label();
        jLabel272 = new widget.Label();
        cmbMuntah = new widget.ComboBox();
        jLabel273 = new widget.Label();
        TtglMuntah = new widget.Tanggal();
        jLabel274 = new widget.Label();
        cmbJam10 = new widget.ComboBox();
        cmbMnt10 = new widget.ComboBox();
        cmbDtk10 = new widget.ComboBox();
        jLabel275 = new widget.Label();
        jLabel276 = new widget.Label();
        cmbBatuk = new widget.ComboBox();
        jLabel277 = new widget.Label();
        TtglBatuk = new widget.Tanggal();
        jLabel278 = new widget.Label();
        cmbJam11 = new widget.ComboBox();
        cmbMnt11 = new widget.ComboBox();
        cmbDtk11 = new widget.ComboBox();
        jLabel279 = new widget.Label();
        jLabel280 = new widget.Label();
        cmbPilek = new widget.ComboBox();
        jLabel281 = new widget.Label();
        TtglPilek = new widget.Tanggal();
        jLabel282 = new widget.Label();
        cmbJam12 = new widget.ComboBox();
        cmbMnt12 = new widget.ComboBox();
        cmbDtk12 = new widget.ComboBox();
        jLabel283 = new widget.Label();
        jLabel284 = new widget.Label();
        cmbDemam = new widget.ComboBox();
        jLabel285 = new widget.Label();
        TtglDemam = new widget.Tanggal();
        jLabel286 = new widget.Label();
        cmbJam13 = new widget.ComboBox();
        cmbMnt13 = new widget.ComboBox();
        cmbDtk13 = new widget.ComboBox();
        jLabel287 = new widget.Label();
        jLabel288 = new widget.Label();
        cmbRiwPerjalanan = new widget.ComboBox();
        TketRiwPerjalanan = new widget.TextBox();
        jLabel289 = new widget.Label();
        cmbVaksin = new widget.ComboBox();
        TketVaksin = new widget.TextBox();
        jLabel290 = new widget.Label();
        jLabel291 = new widget.Label();
        cmbPeriksa = new widget.ComboBox();
        jLabel292 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        TketHasilPemeriksaan = new widget.TextArea();
        jLabel293 = new widget.Label();
        cmbAnc = new widget.ComboBox();
        jLabel294 = new widget.Label();
        cmbAncDi = new widget.ComboBox();
        TjlhAnc = new widget.TextBox();
        jLabel295 = new widget.Label();
        jLabel296 = new widget.Label();
        TnmDokter1 = new widget.TextBox();
        BtnDokter1 = new widget.Button();
        TjlhDokter1 = new widget.TextBox();
        jLabel297 = new widget.Label();
        jLabel298 = new widget.Label();
        TnmDokter2 = new widget.TextBox();
        BtnDokter2 = new widget.Button();
        TjlhDokter2 = new widget.TextBox();
        jLabel299 = new widget.Label();
        jLabel300 = new widget.Label();
        TnmDokter3 = new widget.TextBox();
        BtnDokter3 = new widget.Button();
        TjlhDokter3 = new widget.TextBox();
        jLabel301 = new widget.Label();
        jLabel122 = new widget.Label();
        jLabel302 = new widget.Label();
        Thpht = new widget.TextBox();
        jLabel303 = new widget.Label();
        Thpl = new widget.TextBox();
        jLabel304 = new widget.Label();
        Tuk = new widget.TextBox();
        jLabel305 = new widget.Label();
        jLabel306 = new widget.Label();
        TbbSebelum = new widget.TextBox();
        jLabel307 = new widget.Label();
        TbbTerakhir = new widget.TextBox();
        jLabel308 = new widget.Label();
        Ttbi = new widget.TextBox();
        jLabel309 = new widget.Label();
        jLabel123 = new widget.Label();
        jLabel310 = new widget.Label();
        TumurPertama = new widget.TextBox();
        jLabel311 = new widget.Label();
        TlamaHaid = new widget.TextBox();
        jLabel312 = new widget.Label();
        Tberapa = new widget.TextBox();
        jLabel313 = new widget.Label();
        jLabel314 = new widget.Label();
        cmbKeluhanWaktu = new widget.ComboBox();
        chkDismen = new widget.CekBox();
        chkSpoting = new widget.CekBox();
        chkMenor = new widget.CekBox();
        chkMetro = new widget.CekBox();
        chkKeluhanLain = new widget.CekBox();
        TkeluhanLain = new widget.TextBox();
        jLabel315 = new widget.Label();
        cmbRiwPenDahulu = new widget.ComboBox();
        chkHipertensiDahulu = new widget.CekBox();
        chkDmDahulu = new widget.CekBox();
        chkJantungDahulu = new widget.CekBox();
        chkAsmaDahulu = new widget.CekBox();
        chkLainDahulu = new widget.CekBox();
        TlainDahulu = new widget.TextBox();
        jLabel316 = new widget.Label();
        cmbRiwPenKeluarga = new widget.ComboBox();
        chkHipertensiKeluarga = new widget.CekBox();
        chkDmKeluarga = new widget.CekBox();
        chkJantungKeluarga = new widget.CekBox();
        chkAsmaKeluarga = new widget.CekBox();
        chkLainKeluarga = new widget.CekBox();
        TlainKeluarga = new widget.TextBox();
        jLabel317 = new widget.Label();
        cmbRiwGinekologi = new widget.ComboBox();
        TriwGinekologi = new widget.TextBox();
        jLabel318 = new widget.Label();
        chkPil = new widget.CekBox();
        TlamaPil = new widget.TextBox();
        cmbSatLamaPil = new widget.ComboBox();
        chkSuntik1 = new widget.CekBox();
        TlamaSuntik1 = new widget.TextBox();
        cmbSatLamaSuntik1 = new widget.ComboBox();
        chkSuntik3 = new widget.CekBox();
        TlamaSuntik3 = new widget.TextBox();
        cmbSatLamaSuntik3 = new widget.ComboBox();
        chkImplan = new widget.CekBox();
        TlamaImplan = new widget.TextBox();
        cmbSatLamaImplan = new widget.ComboBox();
        chkIud = new widget.CekBox();
        TlamaIud = new widget.TextBox();
        cmbSatLamaIud = new widget.ComboBox();
        chkTidakPernah = new widget.CekBox();
        jLabel124 = new widget.Label();
        Scroll1 = new widget.ScrollPane();
        tbRiwayat = new widget.Table();
        BtnTambahRiwayat = new widget.Button();
        BtnSimpanRiwayat = new widget.Button();
        BtnHapusRiwayat = new widget.Button();
        BtnGantiRiwayat = new widget.Button();
        jLabel125 = new widget.Label();
        jLabel328 = new widget.Label();
        cmbSttsPerkawinan = new widget.ComboBox();
        jLabel329 = new widget.Label();
        chkIstri = new widget.CekBox();
        cmbJlhIstri = new widget.ComboBox();
        chkSuami = new widget.CekBox();
        cmbJlhSuami = new widget.ComboBox();
        jLabel330 = new widget.Label();
        TusiaPertama = new widget.TextBox();
        jLabel331 = new widget.Label();
        TusiaPerkawinan = new widget.TextBox();
        jLabel332 = new widget.Label();
        TklgTerdekat = new widget.TextBox();
        jLabel333 = new widget.Label();
        ThubKeluarga = new widget.TextBox();
        jLabel334 = new widget.Label();
        chkOrtu = new widget.CekBox();
        chkTinggalSuami = new widget.CekBox();
        chkAnak = new widget.CekBox();
        chkTinggalSendiri = new widget.CekBox();
        jLabel335 = new widget.Label();
        cmbCuriga = new widget.ComboBox();
        jLabel336 = new widget.Label();
        cmbKegiatan = new widget.ComboBox();
        jLabel337 = new widget.Label();
        cmbSttsEmosional = new widget.ComboBox();
        jLabel338 = new widget.Label();
        chkAsuransi = new widget.CekBox();
        chkJaminan = new widget.CekBox();
        chkBiaya = new widget.CekBox();
        chkSttsLain = new widget.CekBox();
        TsttsLainEkonomi = new widget.TextBox();
        jLabel126 = new widget.Label();
        jLabel339 = new widget.Label();
        Tleo1 = new widget.TextBox();
        jLabel340 = new widget.Label();
        Tleo2 = new widget.TextBox();
        jLabel341 = new widget.Label();
        Tleo3 = new widget.TextBox();
        jLabel342 = new widget.Label();
        Tleo4 = new widget.TextBox();
        jLabel343 = new widget.Label();
        cmbBandle = new widget.ComboBox();
        jLabel344 = new widget.Label();
        cmbPerutTegang = new widget.ComboBox();
        jLabel127 = new widget.Label();
        jLabel345 = new widget.Label();
        Tpalpasi = new widget.TextBox();
        jLabel346 = new widget.Label();
        cmbTeraba = new widget.ComboBox();
        jLabel347 = new widget.Label();
        Tsebesar = new widget.TextBox();
        jLabel348 = new widget.Label();
        cmbGoyang = new widget.ComboBox();
        jLabel349 = new widget.Label();
        cmbNyeriTekan = new widget.ComboBox();
        jLabel350 = new widget.Label();
        TvtPembukaan = new widget.TextBox();
        jLabel351 = new widget.Label();
        cmbVtNyeri = new widget.ComboBox();
        jLabel225 = new widget.Label();
        Ttfu = new widget.TextBox();
        jLabel226 = new widget.Label();
        jLabel352 = new widget.Label();
        Ttaksiran = new widget.TextBox();
        jLabel353 = new widget.Label();
        ThisKontraksi = new widget.TextBox();
        jLabel354 = new widget.Label();
        cmbHis = new widget.ComboBox();
        Tdurasi = new widget.TextBox();
        jLabel355 = new widget.Label();
        cmbDurasi = new widget.ComboBox();
        jLabel356 = new widget.Label();
        Tauskultasi = new widget.TextBox();
        jLabel357 = new widget.Label();
        jLabel358 = new widget.Label();
        chkBersih = new widget.CekBox();
        chkOedema = new widget.CekBox();
        chkRuftur = new widget.CekBox();
        chkCandi = new widget.CekBox();
        chkLainPemeriksaan = new widget.CekBox();
        TlainPemeriksaan = new widget.TextBox();
        jLabel359 = new widget.Label();
        TperiksaDalam = new widget.TextBox();
        jLabel360 = new widget.Label();
        cmbInspekulo = new widget.ComboBox();
        jLabel361 = new widget.Label();
        ThasilInspekulo = new widget.TextBox();
        jLabel362 = new widget.Label();
        Tdiagnosis = new widget.TextBox();
        jLabel363 = new widget.Label();
        Ticd = new widget.TextBox();
        jLabel364 = new widget.Label();
        scrollPane10 = new widget.ScrollPane();
        Tplaning = new widget.TextArea();
        jLabel319 = new widget.Label();
        TthnPartus = new widget.TextBox();
        jLabel323 = new widget.Label();
        Tpenolong = new widget.TextBox();
        jLabel320 = new widget.Label();
        TtempatPartus = new widget.TextBox();
        jLabel324 = new widget.Label();
        Tpenyulit = new widget.TextBox();
        jLabel321 = new widget.Label();
        TumurHamil = new widget.TextBox();
        jLabel325 = new widget.Label();
        cmbJenkel = new widget.ComboBox();
        jLabel322 = new widget.Label();
        TjnsPersalinan = new widget.TextBox();
        jLabel326 = new widget.Label();
        TbrtLahir = new widget.TextBox();
        jLabel327 = new widget.Label();
        scrollPane9 = new widget.ScrollPane();
        TkeadaanAnak = new widget.TextArea();
        panelTombol = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        BtnHalaman = new widget.Button();
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
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnHapus1 = new widget.Button();
        BtnPrint1 = new widget.Button();
        BtnHalaman1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        internalFrame4 = new widget.InternalFrame();
        Scroll19 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        panelGlass2 = new widget.panelisi();
        BtnHalaman2 = new widget.Button();
        BtnCari1 = new widget.Button();
        BtnKeluar2 = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Asesmen Awal Kebidanan (hal. 1) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
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
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2416));
        FormInput.setLayout(null);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(125, 38, 131, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(364, 38, 490, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(259, 38, 100, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 38, 120, 23);

        jLabel205.setForeground(new java.awt.Color(0, 0, 0));
        jLabel205.setText("Ruang Rawat :");
        jLabel205.setName("jLabel205"); // NOI18N
        FormInput.add(jLabel205);
        jLabel205.setBounds(0, 66, 120, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(125, 66, 340, 23);

        jLabel206.setForeground(new java.awt.Color(0, 0, 0));
        jLabel206.setText("Tanggal : ");
        jLabel206.setName("jLabel206"); // NOI18N
        FormInput.add(jLabel206);
        jLabel206.setBounds(465, 66, 60, 23);

        TtglAsesmen.setEditable(false);
        TtglAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
        TtglAsesmen.setDisplayFormat("dd-MM-yyyy");
        TtglAsesmen.setName("TtglAsesmen"); // NOI18N
        TtglAsesmen.setOpaque(false);
        TtglAsesmen.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglAsesmen);
        TtglAsesmen.setBounds(527, 66, 90, 23);

        jLabel207.setForeground(new java.awt.Color(0, 0, 0));
        jLabel207.setText("Pukul : ");
        jLabel207.setName("jLabel207"); // NOI18N
        FormInput.add(jLabel207);
        jLabel207.setBounds(620, 66, 50, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(673, 66, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(725, 66, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(777, 66, 45, 23);

        jLabel208.setForeground(new java.awt.Color(0, 0, 0));
        jLabel208.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel208.setText("Wita");
        jLabel208.setName("jLabel208"); // NOI18N
        FormInput.add(jLabel208);
        jLabel208.setBounds(829, 66, 30, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("IDENTITAS PASIEN");
        jLabel119.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(0, 10, 140, 23);

        jLabel209.setForeground(new java.awt.Color(0, 0, 0));
        jLabel209.setText("Umur Pasien :");
        jLabel209.setName("jLabel209"); // NOI18N
        FormInput.add(jLabel209);
        jLabel209.setBounds(0, 94, 120, 23);

        TumurPasien.setEditable(false);
        TumurPasien.setForeground(new java.awt.Color(0, 0, 0));
        TumurPasien.setName("TumurPasien"); // NOI18N
        FormInput.add(TumurPasien);
        TumurPasien.setBounds(125, 94, 60, 23);

        jLabel210.setForeground(new java.awt.Color(0, 0, 0));
        jLabel210.setText("Pekerjaan Pasien :");
        jLabel210.setName("jLabel210"); // NOI18N
        FormInput.add(jLabel210);
        jLabel210.setBounds(190, 94, 95, 23);

        TpekerjaanPasien.setEditable(false);
        TpekerjaanPasien.setForeground(new java.awt.Color(0, 0, 0));
        TpekerjaanPasien.setName("TpekerjaanPasien"); // NOI18N
        FormInput.add(TpekerjaanPasien);
        TpekerjaanPasien.setBounds(290, 94, 290, 23);

        jLabel211.setForeground(new java.awt.Color(0, 0, 0));
        jLabel211.setText("Alamat Pasien :");
        jLabel211.setName("jLabel211"); // NOI18N
        FormInput.add(jLabel211);
        jLabel211.setBounds(0, 122, 120, 23);

        TalamatPasien.setEditable(false);
        TalamatPasien.setForeground(new java.awt.Color(0, 0, 0));
        TalamatPasien.setName("TalamatPasien"); // NOI18N
        FormInput.add(TalamatPasien);
        TalamatPasien.setBounds(125, 122, 730, 23);

        jLabel212.setForeground(new java.awt.Color(0, 0, 0));
        jLabel212.setText("Agama Pasien :");
        jLabel212.setName("jLabel212"); // NOI18N
        FormInput.add(jLabel212);
        jLabel212.setBounds(580, 94, 90, 23);

        TagamaPasien.setEditable(false);
        TagamaPasien.setForeground(new java.awt.Color(0, 0, 0));
        TagamaPasien.setName("TagamaPasien"); // NOI18N
        FormInput.add(TagamaPasien);
        TagamaPasien.setBounds(674, 94, 180, 23);

        jLabel213.setForeground(new java.awt.Color(0, 0, 0));
        jLabel213.setText("Nama Suami :");
        jLabel213.setName("jLabel213"); // NOI18N
        FormInput.add(jLabel213);
        jLabel213.setBounds(0, 150, 120, 23);

        TnamaSuami.setForeground(new java.awt.Color(0, 0, 0));
        TnamaSuami.setName("TnamaSuami"); // NOI18N
        TnamaSuami.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnamaSuamiKeyPressed(evt);
            }
        });
        FormInput.add(TnamaSuami);
        TnamaSuami.setBounds(125, 150, 270, 23);

        jLabel214.setForeground(new java.awt.Color(0, 0, 0));
        jLabel214.setText("Umur Suami :");
        jLabel214.setName("jLabel214"); // NOI18N
        FormInput.add(jLabel214);
        jLabel214.setBounds(400, 150, 70, 23);

        TumurSuami.setForeground(new java.awt.Color(0, 0, 0));
        TumurSuami.setName("TumurSuami"); // NOI18N
        TumurSuami.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurSuamiKeyPressed(evt);
            }
        });
        FormInput.add(TumurSuami);
        TumurSuami.setBounds(475, 150, 50, 23);

        jLabel215.setForeground(new java.awt.Color(0, 0, 0));
        jLabel215.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel215.setText("tahun   Pekerjaan Suami :");
        jLabel215.setName("jLabel215"); // NOI18N
        FormInput.add(jLabel215);
        jLabel215.setBounds(530, 150, 128, 23);

        TpekerjaanSuami.setForeground(new java.awt.Color(0, 0, 0));
        TpekerjaanSuami.setName("TpekerjaanSuami"); // NOI18N
        TpekerjaanSuami.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpekerjaanSuamiKeyPressed(evt);
            }
        });
        FormInput.add(TpekerjaanSuami);
        TpekerjaanSuami.setBounds(660, 150, 195, 23);

        jLabel216.setForeground(new java.awt.Color(0, 0, 0));
        jLabel216.setText("Alamat Suami :");
        jLabel216.setName("jLabel216"); // NOI18N
        FormInput.add(jLabel216);
        jLabel216.setBounds(0, 178, 120, 23);

        TalamatSuami.setForeground(new java.awt.Color(0, 0, 0));
        TalamatSuami.setName("TalamatSuami"); // NOI18N
        TalamatSuami.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalamatSuamiKeyPressed(evt);
            }
        });
        FormInput.add(TalamatSuami);
        TalamatSuami.setBounds(125, 178, 730, 23);

        chkAlamatSama.setBackground(new java.awt.Color(255, 255, 250));
        chkAlamatSama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAlamatSama.setForeground(new java.awt.Color(0, 0, 0));
        chkAlamatSama.setText("Sama Dengan Pasien");
        chkAlamatSama.setBorderPainted(true);
        chkAlamatSama.setBorderPaintedFlat(true);
        chkAlamatSama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAlamatSama.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAlamatSama.setName("chkAlamatSama"); // NOI18N
        chkAlamatSama.setOpaque(false);
        chkAlamatSama.setPreferredSize(new java.awt.Dimension(175, 23));
        chkAlamatSama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAlamatSamaActionPerformed(evt);
            }
        });
        FormInput.add(chkAlamatSama);
        chkAlamatSama.setBounds(860, 178, 130, 23);

        jLabel217.setForeground(new java.awt.Color(0, 0, 0));
        jLabel217.setText("Agama Suami :");
        jLabel217.setName("jLabel217"); // NOI18N
        FormInput.add(jLabel217);
        jLabel217.setBounds(0, 206, 120, 23);

        cmbAgamaSuami.setBackground(new java.awt.Color(245, 253, 240));
        cmbAgamaSuami.setForeground(new java.awt.Color(0, 0, 0));
        cmbAgamaSuami.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "ISLAM", "KRISTEN", "KATOLIK", "HINDU", "BUDHA", "KONG HU CHU" }));
        cmbAgamaSuami.setLightWeightPopupEnabled(false);
        cmbAgamaSuami.setName("cmbAgamaSuami"); // NOI18N
        FormInput.add(cmbAgamaSuami);
        cmbAgamaSuami.setBounds(125, 206, 130, 23);

        jLabel218.setForeground(new java.awt.Color(0, 0, 0));
        jLabel218.setText("Alasan Masuk RS :");
        jLabel218.setName("jLabel218"); // NOI18N
        FormInput.add(jLabel218);
        jLabel218.setBounds(255, 206, 100, 23);

        TalasanMskRS.setForeground(new java.awt.Color(0, 0, 0));
        TalasanMskRS.setName("TalasanMskRS"); // NOI18N
        TalasanMskRS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalasanMskRSKeyPressed(evt);
            }
        });
        FormInput.add(TalasanMskRS);
        TalasanMskRS.setBounds(360, 206, 495, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("KEADAAN UMUM");
        jLabel120.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(0, 234, 140, 23);

        jLabel219.setForeground(new java.awt.Color(0, 0, 0));
        jLabel219.setText("Tekanan Darah :");
        jLabel219.setName("jLabel219"); // NOI18N
        FormInput.add(jLabel219);
        jLabel219.setBounds(0, 262, 120, 23);

        Ttd.setForeground(new java.awt.Color(0, 0, 0));
        Ttd.setName("Ttd"); // NOI18N
        Ttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdKeyPressed(evt);
            }
        });
        FormInput.add(Ttd);
        Ttd.setBounds(125, 262, 70, 23);

        jLabel220.setForeground(new java.awt.Color(0, 0, 0));
        jLabel220.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel220.setText("mmHg      Nadi :");
        jLabel220.setName("jLabel220"); // NOI18N
        FormInput.add(jLabel220);
        jLabel220.setBounds(204, 262, 80, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(285, 262, 70, 23);

        jLabel221.setForeground(new java.awt.Color(0, 0, 0));
        jLabel221.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel221.setText("x/menit    Respirasi :");
        jLabel221.setName("jLabel221"); // NOI18N
        FormInput.add(jLabel221);
        jLabel221.setBounds(360, 262, 103, 23);

        Trespi.setForeground(new java.awt.Color(0, 0, 0));
        Trespi.setName("Trespi"); // NOI18N
        Trespi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrespiKeyPressed(evt);
            }
        });
        FormInput.add(Trespi);
        Trespi.setBounds(465, 262, 70, 23);

        jLabel222.setForeground(new java.awt.Color(0, 0, 0));
        jLabel222.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel222.setText("x/menit    Suhu :");
        jLabel222.setName("jLabel222"); // NOI18N
        FormInput.add(jLabel222);
        jLabel222.setBounds(540, 262, 83, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        FormInput.add(Tsuhu);
        Tsuhu.setBounds(625, 262, 60, 23);

        jLabel223.setForeground(new java.awt.Color(0, 0, 0));
        jLabel223.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel223.setText("°C     Saturasi :");
        jLabel223.setName("jLabel223"); // NOI18N
        FormInput.add(jLabel223);
        jLabel223.setBounds(690, 262, 78, 23);

        Tsaturasi.setForeground(new java.awt.Color(0, 0, 0));
        Tsaturasi.setName("Tsaturasi"); // NOI18N
        Tsaturasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsaturasiKeyPressed(evt);
            }
        });
        FormInput.add(Tsaturasi);
        Tsaturasi.setBounds(768, 262, 60, 23);

        jLabel224.setForeground(new java.awt.Color(0, 0, 0));
        jLabel224.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel224.setText("%");
        jLabel224.setName("jLabel224"); // NOI18N
        FormInput.add(jLabel224);
        jLabel224.setBounds(835, 262, 20, 23);

        jLabel227.setForeground(new java.awt.Color(0, 0, 0));
        jLabel227.setText("Kesadaran :");
        jLabel227.setName("jLabel227"); // NOI18N
        FormInput.add(jLabel227);
        jLabel227.setBounds(0, 290, 120, 23);

        Tkesadaran.setForeground(new java.awt.Color(0, 0, 0));
        Tkesadaran.setName("Tkesadaran"); // NOI18N
        Tkesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesadaranKeyPressed(evt);
            }
        });
        FormInput.add(Tkesadaran);
        Tkesadaran.setBounds(125, 290, 455, 23);

        jLabel228.setForeground(new java.awt.Color(0, 0, 0));
        jLabel228.setText("Cara Pasien Datang :");
        jLabel228.setName("jLabel228"); // NOI18N
        FormInput.add(jLabel228);
        jLabel228.setBounds(0, 318, 120, 23);

        chkSendiri.setBackground(new java.awt.Color(255, 255, 250));
        chkSendiri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkSendiri);
        chkSendiri.setForeground(new java.awt.Color(0, 0, 0));
        chkSendiri.setText("Sendiri");
        chkSendiri.setBorderPainted(true);
        chkSendiri.setBorderPaintedFlat(true);
        chkSendiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSendiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSendiri.setName("chkSendiri"); // NOI18N
        chkSendiri.setOpaque(false);
        chkSendiri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSendiri);
        chkSendiri.setBounds(125, 318, 60, 23);

        chkRujukan.setBackground(new java.awt.Color(255, 255, 250));
        chkRujukan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkRujukan);
        chkRujukan.setForeground(new java.awt.Color(0, 0, 0));
        chkRujukan.setText("Rujukan");
        chkRujukan.setBorderPainted(true);
        chkRujukan.setBorderPaintedFlat(true);
        chkRujukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRujukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRujukan.setName("chkRujukan"); // NOI18N
        chkRujukan.setOpaque(false);
        chkRujukan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRujukanActionPerformed(evt);
            }
        });
        FormInput.add(chkRujukan);
        chkRujukan.setBounds(200, 318, 70, 23);

        cmbJnsRujukan.setBackground(new java.awt.Color(245, 253, 240));
        cmbJnsRujukan.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bidan", "BPM" }));
        cmbJnsRujukan.setLightWeightPopupEnabled(false);
        cmbJnsRujukan.setName("cmbJnsRujukan"); // NOI18N
        FormInput.add(cmbJnsRujukan);
        cmbJnsRujukan.setBounds(275, 318, 60, 23);

        TketRujukan.setForeground(new java.awt.Color(0, 0, 0));
        TketRujukan.setName("TketRujukan"); // NOI18N
        TketRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketRujukanKeyPressed(evt);
            }
        });
        FormInput.add(TketRujukan);
        TketRujukan.setBounds(340, 318, 240, 23);

        chkSpog.setBackground(new java.awt.Color(255, 255, 250));
        chkSpog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkSpog);
        chkSpog.setForeground(new java.awt.Color(0, 0, 0));
        chkSpog.setText("SPOG");
        chkSpog.setBorderPainted(true);
        chkSpog.setBorderPaintedFlat(true);
        chkSpog.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSpog.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSpog.setName("chkSpog"); // NOI18N
        chkSpog.setOpaque(false);
        chkSpog.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSpog);
        chkSpog.setBounds(585, 318, 60, 23);

        chkPkm.setBackground(new java.awt.Color(255, 255, 250));
        chkPkm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkPkm);
        chkPkm.setForeground(new java.awt.Color(0, 0, 0));
        chkPkm.setText("PKM");
        chkPkm.setBorderPainted(true);
        chkPkm.setBorderPaintedFlat(true);
        chkPkm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPkm.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPkm.setName("chkPkm"); // NOI18N
        chkPkm.setOpaque(false);
        chkPkm.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPkm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPkmActionPerformed(evt);
            }
        });
        FormInput.add(chkPkm);
        chkPkm.setBounds(275, 346, 55, 23);

        TketPkm.setForeground(new java.awt.Color(0, 0, 0));
        TketPkm.setName("TketPkm"); // NOI18N
        TketPkm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketPkmKeyPressed(evt);
            }
        });
        FormInput.add(TketPkm);
        TketPkm.setBounds(340, 346, 240, 23);

        chkRsLain.setBackground(new java.awt.Color(255, 255, 250));
        chkRsLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkRsLain);
        chkRsLain.setForeground(new java.awt.Color(0, 0, 0));
        chkRsLain.setText("RS Lain");
        chkRsLain.setBorderPainted(true);
        chkRsLain.setBorderPaintedFlat(true);
        chkRsLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRsLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRsLain.setName("chkRsLain"); // NOI18N
        chkRsLain.setOpaque(false);
        chkRsLain.setPreferredSize(new java.awt.Dimension(175, 23));
        chkRsLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRsLainActionPerformed(evt);
            }
        });
        FormInput.add(chkRsLain);
        chkRsLain.setBounds(585, 346, 60, 23);

        TketRsLain.setForeground(new java.awt.Color(0, 0, 0));
        TketRsLain.setName("TketRsLain"); // NOI18N
        TketRsLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketRsLainKeyPressed(evt);
            }
        });
        FormInput.add(TketRsLain);
        TketRsLain.setBounds(650, 346, 205, 23);

        jLabel229.setForeground(new java.awt.Color(0, 0, 0));
        jLabel229.setText("Gr :");
        jLabel229.setName("jLabel229"); // NOI18N
        FormInput.add(jLabel229);
        jLabel229.setBounds(0, 374, 120, 23);

        Tgr.setForeground(new java.awt.Color(0, 0, 0));
        Tgr.setName("Tgr"); // NOI18N
        Tgr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgrKeyPressed(evt);
            }
        });
        FormInput.add(Tgr);
        Tgr.setBounds(125, 374, 70, 23);

        jLabel230.setForeground(new java.awt.Color(0, 0, 0));
        jLabel230.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel230.setText("Pr :");
        jLabel230.setName("jLabel230"); // NOI18N
        FormInput.add(jLabel230);
        jLabel230.setBounds(195, 374, 30, 23);

        Tpr.setForeground(new java.awt.Color(0, 0, 0));
        Tpr.setName("Tpr"); // NOI18N
        Tpr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprKeyPressed(evt);
            }
        });
        FormInput.add(Tpr);
        Tpr.setBounds(225, 374, 70, 23);

        jLabel231.setForeground(new java.awt.Color(0, 0, 0));
        jLabel231.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel231.setText("A :");
        jLabel231.setName("jLabel231"); // NOI18N
        FormInput.add(jLabel231);
        jLabel231.setBounds(295, 374, 25, 23);

        Ta.setForeground(new java.awt.Color(0, 0, 0));
        Ta.setName("Ta"); // NOI18N
        Ta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TaKeyPressed(evt);
            }
        });
        FormInput.add(Ta);
        Ta.setBounds(322, 374, 70, 23);

        jLabel232.setForeground(new java.awt.Color(0, 0, 0));
        jLabel232.setText("Hamil : ");
        jLabel232.setName("jLabel232"); // NOI18N
        FormInput.add(jLabel232);
        jLabel232.setBounds(400, 374, 50, 23);

        Thamil.setForeground(new java.awt.Color(0, 0, 0));
        Thamil.setName("Thamil"); // NOI18N
        Thamil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThamilKeyPressed(evt);
            }
        });
        FormInput.add(Thamil);
        Thamil.setBounds(455, 374, 70, 23);

        jLabel233.setForeground(new java.awt.Color(0, 0, 0));
        jLabel233.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel233.setText("minggu     G.PAPAH : ");
        jLabel233.setName("jLabel233"); // NOI18N
        FormInput.add(jLabel233);
        jLabel233.setBounds(530, 374, 105, 23);

        Tgpapah.setForeground(new java.awt.Color(0, 0, 0));
        Tgpapah.setName("Tgpapah"); // NOI18N
        Tgpapah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgpapahKeyPressed(evt);
            }
        });
        FormInput.add(Tgpapah);
        Tgpapah.setBounds(635, 374, 220, 23);

        jLabel234.setForeground(new java.awt.Color(0, 0, 0));
        jLabel234.setText("Dengan :");
        jLabel234.setName("jLabel234"); // NOI18N
        FormInput.add(jLabel234);
        jLabel234.setBounds(0, 402, 120, 23);

        Tdengan.setForeground(new java.awt.Color(0, 0, 0));
        Tdengan.setName("Tdengan"); // NOI18N
        Tdengan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdenganKeyPressed(evt);
            }
        });
        FormInput.add(Tdengan);
        Tdengan.setBounds(125, 402, 730, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("KELUHAN");
        jLabel121.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(0, 430, 140, 23);

        jLabel235.setForeground(new java.awt.Color(0, 0, 0));
        jLabel235.setText("Perut :");
        jLabel235.setName("jLabel235"); // NOI18N
        FormInput.add(jLabel235);
        jLabel235.setBounds(0, 458, 120, 23);

        cmbPerut.setBackground(new java.awt.Color(245, 253, 240));
        cmbPerut.setForeground(new java.awt.Color(0, 0, 0));
        cmbPerut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPerut.setLightWeightPopupEnabled(false);
        cmbPerut.setName("cmbPerut"); // NOI18N
        cmbPerut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPerutActionPerformed(evt);
            }
        });
        FormInput.add(cmbPerut);
        cmbPerut.setBounds(125, 458, 60, 23);

        cmbKeluhanPerut.setBackground(new java.awt.Color(245, 253, 240));
        cmbKeluhanPerut.setForeground(new java.awt.Color(0, 0, 0));
        cmbKeluhanPerut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mules", "Nyeri" }));
        cmbKeluhanPerut.setLightWeightPopupEnabled(false);
        cmbKeluhanPerut.setName("cmbKeluhanPerut"); // NOI18N
        FormInput.add(cmbKeluhanPerut);
        cmbKeluhanPerut.setBounds(192, 458, 60, 23);

        jLabel236.setForeground(new java.awt.Color(0, 0, 0));
        jLabel236.setText("Mulai Tgl. : ");
        jLabel236.setName("jLabel236"); // NOI18N
        FormInput.add(jLabel236);
        jLabel236.setBounds(260, 458, 65, 23);

        TtglPerut.setEditable(false);
        TtglPerut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
        TtglPerut.setDisplayFormat("dd-MM-yyyy");
        TtglPerut.setName("TtglPerut"); // NOI18N
        TtglPerut.setOpaque(false);
        TtglPerut.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglPerut);
        TtglPerut.setBounds(330, 458, 90, 23);

        jLabel237.setForeground(new java.awt.Color(0, 0, 0));
        jLabel237.setText("Jam : ");
        jLabel237.setName("jLabel237"); // NOI18N
        FormInput.add(jLabel237);
        jLabel237.setBounds(420, 458, 45, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(470, 458, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(523, 458, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(575, 458, 45, 23);

        jLabel238.setForeground(new java.awt.Color(0, 0, 0));
        jLabel238.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel238.setText("Wita");
        jLabel238.setName("jLabel238"); // NOI18N
        FormInput.add(jLabel238);
        jLabel238.setBounds(627, 458, 50, 23);

        jLabel239.setForeground(new java.awt.Color(0, 0, 0));
        jLabel239.setText("Keluar :");
        jLabel239.setName("jLabel239"); // NOI18N
        FormInput.add(jLabel239);
        jLabel239.setBounds(0, 486, 120, 23);

        cmbKeluar.setBackground(new java.awt.Color(245, 253, 240));
        cmbKeluar.setForeground(new java.awt.Color(0, 0, 0));
        cmbKeluar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKeluar.setLightWeightPopupEnabled(false);
        cmbKeluar.setName("cmbKeluar"); // NOI18N
        cmbKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKeluarActionPerformed(evt);
            }
        });
        FormInput.add(cmbKeluar);
        cmbKeluar.setBounds(125, 486, 60, 23);

        cmbKeluhanKeluar.setBackground(new java.awt.Color(245, 253, 240));
        cmbKeluhanKeluar.setForeground(new java.awt.Color(0, 0, 0));
        cmbKeluhanKeluar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Lendir", "Lendir Darah", "Darah" }));
        cmbKeluhanKeluar.setLightWeightPopupEnabled(false);
        cmbKeluhanKeluar.setName("cmbKeluhanKeluar"); // NOI18N
        FormInput.add(cmbKeluhanKeluar);
        cmbKeluhanKeluar.setBounds(192, 486, 95, 23);

        jLabel240.setForeground(new java.awt.Color(0, 0, 0));
        jLabel240.setText("Mulai Tgl. : ");
        jLabel240.setName("jLabel240"); // NOI18N
        FormInput.add(jLabel240);
        jLabel240.setBounds(295, 486, 65, 23);

        TtglKeluar.setEditable(false);
        TtglKeluar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
        TtglKeluar.setDisplayFormat("dd-MM-yyyy");
        TtglKeluar.setName("TtglKeluar"); // NOI18N
        TtglKeluar.setOpaque(false);
        TtglKeluar.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglKeluar);
        TtglKeluar.setBounds(365, 486, 90, 23);

        jLabel241.setForeground(new java.awt.Color(0, 0, 0));
        jLabel241.setText("Jam : ");
        jLabel241.setName("jLabel241"); // NOI18N
        FormInput.add(jLabel241);
        jLabel241.setBounds(455, 486, 45, 23);

        cmbJam3.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam3.setName("cmbJam3"); // NOI18N
        cmbJam3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam3MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam3);
        cmbJam3.setBounds(505, 486, 45, 23);

        cmbMnt3.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt3.setName("cmbMnt3"); // NOI18N
        cmbMnt3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt3MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt3);
        cmbMnt3.setBounds(558, 486, 45, 23);

        cmbDtk3.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk3.setName("cmbDtk3"); // NOI18N
        cmbDtk3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk3MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk3);
        cmbDtk3.setBounds(610, 486, 45, 23);

        jLabel242.setForeground(new java.awt.Color(0, 0, 0));
        jLabel242.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel242.setText("Wita");
        jLabel242.setName("jLabel242"); // NOI18N
        FormInput.add(jLabel242);
        jLabel242.setBounds(662, 486, 50, 23);

        jLabel243.setForeground(new java.awt.Color(0, 0, 0));
        jLabel243.setText("Darah :");
        jLabel243.setName("jLabel243"); // NOI18N
        FormInput.add(jLabel243);
        jLabel243.setBounds(0, 514, 120, 23);

        cmbDarah.setBackground(new java.awt.Color(245, 253, 240));
        cmbDarah.setForeground(new java.awt.Color(0, 0, 0));
        cmbDarah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbDarah.setLightWeightPopupEnabled(false);
        cmbDarah.setName("cmbDarah"); // NOI18N
        cmbDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDarahActionPerformed(evt);
            }
        });
        FormInput.add(cmbDarah);
        cmbDarah.setBounds(125, 514, 60, 23);

        cmbKeluhanDarah.setBackground(new java.awt.Color(245, 253, 240));
        cmbKeluhanDarah.setForeground(new java.awt.Color(0, 0, 0));
        cmbKeluhanDarah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Encer", "Segar", "Bergumpal" }));
        cmbKeluhanDarah.setLightWeightPopupEnabled(false);
        cmbKeluhanDarah.setName("cmbKeluhanDarah"); // NOI18N
        FormInput.add(cmbKeluhanDarah);
        cmbKeluhanDarah.setBounds(192, 514, 95, 23);

        cmbJnsDarah.setBackground(new java.awt.Color(245, 253, 240));
        cmbJnsDarah.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsDarah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Banyak", "Sedikit - sedikit" }));
        cmbJnsDarah.setLightWeightPopupEnabled(false);
        cmbJnsDarah.setName("cmbJnsDarah"); // NOI18N
        FormInput.add(cmbJnsDarah);
        cmbJnsDarah.setBounds(295, 514, 105, 23);

        jLabel244.setForeground(new java.awt.Color(0, 0, 0));
        jLabel244.setText("Mulai Tgl. : ");
        jLabel244.setName("jLabel244"); // NOI18N
        FormInput.add(jLabel244);
        jLabel244.setBounds(405, 514, 65, 23);

        TtglDarah.setEditable(false);
        TtglDarah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
        TtglDarah.setDisplayFormat("dd-MM-yyyy");
        TtglDarah.setName("TtglDarah"); // NOI18N
        TtglDarah.setOpaque(false);
        TtglDarah.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglDarah);
        TtglDarah.setBounds(473, 514, 90, 23);

        jLabel245.setForeground(new java.awt.Color(0, 0, 0));
        jLabel245.setText("Jam : ");
        jLabel245.setName("jLabel245"); // NOI18N
        FormInput.add(jLabel245);
        jLabel245.setBounds(565, 514, 45, 23);

        cmbJam4.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam4.setName("cmbJam4"); // NOI18N
        cmbJam4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam4MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam4);
        cmbJam4.setBounds(615, 514, 45, 23);

        cmbMnt4.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt4.setName("cmbMnt4"); // NOI18N
        cmbMnt4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt4MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt4);
        cmbMnt4.setBounds(668, 514, 45, 23);

        cmbDtk4.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk4.setName("cmbDtk4"); // NOI18N
        cmbDtk4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk4MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk4);
        cmbDtk4.setBounds(720, 514, 45, 23);

        jLabel246.setForeground(new java.awt.Color(0, 0, 0));
        jLabel246.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel246.setText("Wita");
        jLabel246.setName("jLabel246"); // NOI18N
        FormInput.add(jLabel246);
        jLabel246.setBounds(772, 514, 50, 23);

        jLabel247.setForeground(new java.awt.Color(0, 0, 0));
        jLabel247.setText("Keluar Air-Air :");
        jLabel247.setName("jLabel247"); // NOI18N
        FormInput.add(jLabel247);
        jLabel247.setBounds(0, 542, 120, 23);

        cmbKeluarAir.setBackground(new java.awt.Color(245, 253, 240));
        cmbKeluarAir.setForeground(new java.awt.Color(0, 0, 0));
        cmbKeluarAir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKeluarAir.setLightWeightPopupEnabled(false);
        cmbKeluarAir.setName("cmbKeluarAir"); // NOI18N
        cmbKeluarAir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKeluarAirActionPerformed(evt);
            }
        });
        FormInput.add(cmbKeluarAir);
        cmbKeluarAir.setBounds(125, 542, 60, 23);

        cmbJnsKeluarAir.setBackground(new java.awt.Color(245, 253, 240));
        cmbJnsKeluarAir.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsKeluarAir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Banyak", "Sedikit - sedikit" }));
        cmbJnsKeluarAir.setLightWeightPopupEnabled(false);
        cmbJnsKeluarAir.setName("cmbJnsKeluarAir"); // NOI18N
        FormInput.add(cmbJnsKeluarAir);
        cmbJnsKeluarAir.setBounds(280, 542, 105, 23);

        jLabel248.setForeground(new java.awt.Color(0, 0, 0));
        jLabel248.setText("Mulai Tgl. : ");
        jLabel248.setName("jLabel248"); // NOI18N
        FormInput.add(jLabel248);
        jLabel248.setBounds(390, 542, 65, 23);

        TtglKeluarAir.setEditable(false);
        TtglKeluarAir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
        TtglKeluarAir.setDisplayFormat("dd-MM-yyyy");
        TtglKeluarAir.setName("TtglKeluarAir"); // NOI18N
        TtglKeluarAir.setOpaque(false);
        TtglKeluarAir.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglKeluarAir);
        TtglKeluarAir.setBounds(458, 542, 90, 23);

        jLabel249.setForeground(new java.awt.Color(0, 0, 0));
        jLabel249.setText("Jam : ");
        jLabel249.setName("jLabel249"); // NOI18N
        FormInput.add(jLabel249);
        jLabel249.setBounds(550, 542, 45, 23);

        cmbJam5.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam5.setName("cmbJam5"); // NOI18N
        cmbJam5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam5MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam5);
        cmbJam5.setBounds(600, 542, 45, 23);

        cmbMnt5.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt5.setName("cmbMnt5"); // NOI18N
        cmbMnt5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt5MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt5);
        cmbMnt5.setBounds(653, 542, 45, 23);

        cmbDtk5.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk5.setName("cmbDtk5"); // NOI18N
        cmbDtk5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk5MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk5);
        cmbDtk5.setBounds(705, 542, 45, 23);

        jLabel250.setForeground(new java.awt.Color(0, 0, 0));
        jLabel250.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel250.setText("Wita");
        jLabel250.setName("jLabel250"); // NOI18N
        FormInput.add(jLabel250);
        jLabel250.setBounds(757, 542, 50, 23);

        cmbKeluhanKeluarAir.setBackground(new java.awt.Color(245, 253, 240));
        cmbKeluhanKeluarAir.setForeground(new java.awt.Color(0, 0, 0));
        cmbKeluhanKeluarAir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Jernih", "Keruh", "Meconium" }));
        cmbKeluhanKeluarAir.setLightWeightPopupEnabled(false);
        cmbKeluhanKeluarAir.setName("cmbKeluhanKeluarAir"); // NOI18N
        FormInput.add(cmbKeluhanKeluarAir);
        cmbKeluhanKeluarAir.setBounds(192, 542, 80, 23);

        jLabel251.setForeground(new java.awt.Color(0, 0, 0));
        jLabel251.setText("Pergerakan Janin 2 Jam Terakhir :");
        jLabel251.setName("jLabel251"); // NOI18N
        FormInput.add(jLabel251);
        jLabel251.setBounds(0, 570, 190, 23);

        cmbPergerakan.setBackground(new java.awt.Color(245, 253, 240));
        cmbPergerakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPergerakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbPergerakan.setLightWeightPopupEnabled(false);
        cmbPergerakan.setName("cmbPergerakan"); // NOI18N
        cmbPergerakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPergerakanActionPerformed(evt);
            }
        });
        FormInput.add(cmbPergerakan);
        cmbPergerakan.setBounds(196, 570, 80, 23);

        Tpergerakan.setForeground(new java.awt.Color(0, 0, 0));
        Tpergerakan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tpergerakan.setName("Tpergerakan"); // NOI18N
        Tpergerakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpergerakanKeyPressed(evt);
            }
        });
        FormInput.add(Tpergerakan);
        Tpergerakan.setBounds(283, 570, 55, 23);

        jLabel252.setForeground(new java.awt.Color(0, 0, 0));
        jLabel252.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel252.setText("X");
        jLabel252.setName("jLabel252"); // NOI18N
        FormInput.add(jLabel252);
        jLabel252.setBounds(345, 570, 30, 23);

        jLabel253.setForeground(new java.awt.Color(0, 0, 0));
        jLabel253.setText("Pusing :");
        jLabel253.setName("jLabel253"); // NOI18N
        FormInput.add(jLabel253);
        jLabel253.setBounds(0, 598, 120, 23);

        cmbPusing.setBackground(new java.awt.Color(245, 253, 240));
        cmbPusing.setForeground(new java.awt.Color(0, 0, 0));
        cmbPusing.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPusing.setLightWeightPopupEnabled(false);
        cmbPusing.setName("cmbPusing"); // NOI18N
        cmbPusing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPusingActionPerformed(evt);
            }
        });
        FormInput.add(cmbPusing);
        cmbPusing.setBounds(125, 598, 60, 23);

        jLabel254.setForeground(new java.awt.Color(0, 0, 0));
        jLabel254.setText("Mulai Tgl. : ");
        jLabel254.setName("jLabel254"); // NOI18N
        FormInput.add(jLabel254);
        jLabel254.setBounds(192, 598, 65, 23);

        TtglPusing.setEditable(false);
        TtglPusing.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
        TtglPusing.setDisplayFormat("dd-MM-yyyy");
        TtglPusing.setName("TtglPusing"); // NOI18N
        TtglPusing.setOpaque(false);
        TtglPusing.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglPusing);
        TtglPusing.setBounds(262, 598, 90, 23);

        jLabel255.setForeground(new java.awt.Color(0, 0, 0));
        jLabel255.setText("Jam : ");
        jLabel255.setName("jLabel255"); // NOI18N
        FormInput.add(jLabel255);
        jLabel255.setBounds(352, 598, 45, 23);

        cmbJam6.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam6.setName("cmbJam6"); // NOI18N
        cmbJam6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam6MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam6);
        cmbJam6.setBounds(402, 598, 45, 23);

        cmbMnt6.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt6.setName("cmbMnt6"); // NOI18N
        cmbMnt6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt6MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt6);
        cmbMnt6.setBounds(455, 598, 45, 23);

        cmbDtk6.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk6.setName("cmbDtk6"); // NOI18N
        cmbDtk6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk6MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk6);
        cmbDtk6.setBounds(507, 598, 45, 23);

        jLabel256.setForeground(new java.awt.Color(0, 0, 0));
        jLabel256.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel256.setText("Wita");
        jLabel256.setName("jLabel256"); // NOI18N
        FormInput.add(jLabel256);
        jLabel256.setBounds(559, 598, 50, 23);

        jLabel257.setForeground(new java.awt.Color(0, 0, 0));
        jLabel257.setText("Nyeri Ulu Hati :");
        jLabel257.setName("jLabel257"); // NOI18N
        FormInput.add(jLabel257);
        jLabel257.setBounds(0, 626, 120, 23);

        cmbNyeriUlu.setBackground(new java.awt.Color(245, 253, 240));
        cmbNyeriUlu.setForeground(new java.awt.Color(0, 0, 0));
        cmbNyeriUlu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbNyeriUlu.setLightWeightPopupEnabled(false);
        cmbNyeriUlu.setName("cmbNyeriUlu"); // NOI18N
        cmbNyeriUlu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNyeriUluActionPerformed(evt);
            }
        });
        FormInput.add(cmbNyeriUlu);
        cmbNyeriUlu.setBounds(125, 626, 60, 23);

        jLabel258.setForeground(new java.awt.Color(0, 0, 0));
        jLabel258.setText("Mulai Tgl. : ");
        jLabel258.setName("jLabel258"); // NOI18N
        FormInput.add(jLabel258);
        jLabel258.setBounds(192, 626, 65, 23);

        TtglNyeriUlu.setEditable(false);
        TtglNyeriUlu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
        TtglNyeriUlu.setDisplayFormat("dd-MM-yyyy");
        TtglNyeriUlu.setName("TtglNyeriUlu"); // NOI18N
        TtglNyeriUlu.setOpaque(false);
        TtglNyeriUlu.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglNyeriUlu);
        TtglNyeriUlu.setBounds(262, 626, 90, 23);

        jLabel259.setForeground(new java.awt.Color(0, 0, 0));
        jLabel259.setText("Jam : ");
        jLabel259.setName("jLabel259"); // NOI18N
        FormInput.add(jLabel259);
        jLabel259.setBounds(352, 626, 45, 23);

        cmbJam7.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam7.setName("cmbJam7"); // NOI18N
        cmbJam7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam7MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam7);
        cmbJam7.setBounds(402, 626, 45, 23);

        cmbMnt7.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt7.setName("cmbMnt7"); // NOI18N
        cmbMnt7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt7MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt7);
        cmbMnt7.setBounds(455, 626, 45, 23);

        cmbDtk7.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk7.setName("cmbDtk7"); // NOI18N
        cmbDtk7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk7MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk7);
        cmbDtk7.setBounds(507, 626, 45, 23);

        jLabel260.setForeground(new java.awt.Color(0, 0, 0));
        jLabel260.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel260.setText("Wita");
        jLabel260.setName("jLabel260"); // NOI18N
        FormInput.add(jLabel260);
        jLabel260.setBounds(559, 626, 50, 23);

        jLabel261.setForeground(new java.awt.Color(0, 0, 0));
        jLabel261.setText("Pandangan Kabur :");
        jLabel261.setName("jLabel261"); // NOI18N
        FormInput.add(jLabel261);
        jLabel261.setBounds(0, 654, 120, 23);

        cmbPandangan.setBackground(new java.awt.Color(245, 253, 240));
        cmbPandangan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPandangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPandangan.setLightWeightPopupEnabled(false);
        cmbPandangan.setName("cmbPandangan"); // NOI18N
        cmbPandangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPandanganActionPerformed(evt);
            }
        });
        FormInput.add(cmbPandangan);
        cmbPandangan.setBounds(125, 654, 60, 23);

        jLabel262.setForeground(new java.awt.Color(0, 0, 0));
        jLabel262.setText("Mulai Tgl. : ");
        jLabel262.setName("jLabel262"); // NOI18N
        FormInput.add(jLabel262);
        jLabel262.setBounds(192, 654, 65, 23);

        TtglPandangan.setEditable(false);
        TtglPandangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
        TtglPandangan.setDisplayFormat("dd-MM-yyyy");
        TtglPandangan.setName("TtglPandangan"); // NOI18N
        TtglPandangan.setOpaque(false);
        TtglPandangan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglPandangan);
        TtglPandangan.setBounds(262, 654, 90, 23);

        jLabel263.setForeground(new java.awt.Color(0, 0, 0));
        jLabel263.setText("Jam : ");
        jLabel263.setName("jLabel263"); // NOI18N
        FormInput.add(jLabel263);
        jLabel263.setBounds(352, 654, 45, 23);

        cmbJam8.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam8.setName("cmbJam8"); // NOI18N
        cmbJam8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam8MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam8);
        cmbJam8.setBounds(402, 654, 45, 23);

        cmbMnt8.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt8.setName("cmbMnt8"); // NOI18N
        cmbMnt8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt8MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt8);
        cmbMnt8.setBounds(455, 654, 45, 23);

        cmbDtk8.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk8.setName("cmbDtk8"); // NOI18N
        cmbDtk8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk8MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk8);
        cmbDtk8.setBounds(507, 654, 45, 23);

        jLabel264.setForeground(new java.awt.Color(0, 0, 0));
        jLabel264.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel264.setText("Wita");
        jLabel264.setName("jLabel264"); // NOI18N
        FormInput.add(jLabel264);
        jLabel264.setBounds(559, 654, 50, 23);

        jLabel265.setForeground(new java.awt.Color(0, 0, 0));
        jLabel265.setText("Odema :");
        jLabel265.setName("jLabel265"); // NOI18N
        FormInput.add(jLabel265);
        jLabel265.setBounds(0, 682, 120, 23);

        cmbOdema.setBackground(new java.awt.Color(245, 253, 240));
        cmbOdema.setForeground(new java.awt.Color(0, 0, 0));
        cmbOdema.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbOdema.setLightWeightPopupEnabled(false);
        cmbOdema.setName("cmbOdema"); // NOI18N
        cmbOdema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbOdemaActionPerformed(evt);
            }
        });
        FormInput.add(cmbOdema);
        cmbOdema.setBounds(125, 682, 60, 23);

        jLabel266.setForeground(new java.awt.Color(0, 0, 0));
        jLabel266.setText("Mulai Tgl. : ");
        jLabel266.setName("jLabel266"); // NOI18N
        FormInput.add(jLabel266);
        jLabel266.setBounds(192, 682, 65, 23);

        TtglOdema.setEditable(false);
        TtglOdema.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
        TtglOdema.setDisplayFormat("dd-MM-yyyy");
        TtglOdema.setName("TtglOdema"); // NOI18N
        TtglOdema.setOpaque(false);
        TtglOdema.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglOdema);
        TtglOdema.setBounds(262, 682, 90, 23);

        jLabel267.setForeground(new java.awt.Color(0, 0, 0));
        jLabel267.setText("Di : ");
        jLabel267.setName("jLabel267"); // NOI18N
        FormInput.add(jLabel267);
        jLabel267.setBounds(352, 682, 45, 23);

        cmbOdemaDi.setBackground(new java.awt.Color(245, 253, 240));
        cmbOdemaDi.setForeground(new java.awt.Color(0, 0, 0));
        cmbOdemaDi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Wajah", "Kaki", "Tangan", "Seluruh Tubuh", "Kaki & Tangan", "Kaki & Wajah", "Wajah & Tangan", "Wajah & Kaki", "Kaki, Tangan & Wajah" }));
        cmbOdemaDi.setLightWeightPopupEnabled(false);
        cmbOdemaDi.setName("cmbOdemaDi"); // NOI18N
        FormInput.add(cmbOdemaDi);
        cmbOdemaDi.setBounds(402, 682, 138, 23);

        jLabel268.setForeground(new java.awt.Color(0, 0, 0));
        jLabel268.setText("Mual :");
        jLabel268.setName("jLabel268"); // NOI18N
        FormInput.add(jLabel268);
        jLabel268.setBounds(0, 710, 120, 23);

        cmbMual.setBackground(new java.awt.Color(245, 253, 240));
        cmbMual.setForeground(new java.awt.Color(0, 0, 0));
        cmbMual.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMual.setLightWeightPopupEnabled(false);
        cmbMual.setName("cmbMual"); // NOI18N
        cmbMual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMualActionPerformed(evt);
            }
        });
        FormInput.add(cmbMual);
        cmbMual.setBounds(125, 710, 60, 23);

        jLabel269.setForeground(new java.awt.Color(0, 0, 0));
        jLabel269.setText("Mulai Tgl. : ");
        jLabel269.setName("jLabel269"); // NOI18N
        FormInput.add(jLabel269);
        jLabel269.setBounds(192, 710, 65, 23);

        TtglMual.setEditable(false);
        TtglMual.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
        TtglMual.setDisplayFormat("dd-MM-yyyy");
        TtglMual.setName("TtglMual"); // NOI18N
        TtglMual.setOpaque(false);
        TtglMual.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglMual);
        TtglMual.setBounds(262, 710, 90, 23);

        jLabel270.setForeground(new java.awt.Color(0, 0, 0));
        jLabel270.setText("Jam : ");
        jLabel270.setName("jLabel270"); // NOI18N
        FormInput.add(jLabel270);
        jLabel270.setBounds(352, 710, 45, 23);

        cmbJam9.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam9.setName("cmbJam9"); // NOI18N
        cmbJam9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam9MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam9);
        cmbJam9.setBounds(402, 710, 45, 23);

        cmbMnt9.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt9.setName("cmbMnt9"); // NOI18N
        cmbMnt9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt9MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt9);
        cmbMnt9.setBounds(455, 710, 45, 23);

        cmbDtk9.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk9.setName("cmbDtk9"); // NOI18N
        cmbDtk9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk9MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk9);
        cmbDtk9.setBounds(507, 710, 45, 23);

        jLabel271.setForeground(new java.awt.Color(0, 0, 0));
        jLabel271.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel271.setText("Wita");
        jLabel271.setName("jLabel271"); // NOI18N
        FormInput.add(jLabel271);
        jLabel271.setBounds(559, 710, 50, 23);

        jLabel272.setForeground(new java.awt.Color(0, 0, 0));
        jLabel272.setText("Muntah :");
        jLabel272.setName("jLabel272"); // NOI18N
        FormInput.add(jLabel272);
        jLabel272.setBounds(0, 738, 120, 23);

        cmbMuntah.setBackground(new java.awt.Color(245, 253, 240));
        cmbMuntah.setForeground(new java.awt.Color(0, 0, 0));
        cmbMuntah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMuntah.setLightWeightPopupEnabled(false);
        cmbMuntah.setName("cmbMuntah"); // NOI18N
        cmbMuntah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMuntahActionPerformed(evt);
            }
        });
        FormInput.add(cmbMuntah);
        cmbMuntah.setBounds(125, 738, 60, 23);

        jLabel273.setForeground(new java.awt.Color(0, 0, 0));
        jLabel273.setText("Mulai Tgl. : ");
        jLabel273.setName("jLabel273"); // NOI18N
        FormInput.add(jLabel273);
        jLabel273.setBounds(192, 738, 65, 23);

        TtglMuntah.setEditable(false);
        TtglMuntah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
        TtglMuntah.setDisplayFormat("dd-MM-yyyy");
        TtglMuntah.setName("TtglMuntah"); // NOI18N
        TtglMuntah.setOpaque(false);
        TtglMuntah.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglMuntah);
        TtglMuntah.setBounds(262, 738, 90, 23);

        jLabel274.setForeground(new java.awt.Color(0, 0, 0));
        jLabel274.setText("Jam : ");
        jLabel274.setName("jLabel274"); // NOI18N
        FormInput.add(jLabel274);
        jLabel274.setBounds(352, 738, 45, 23);

        cmbJam10.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam10.setName("cmbJam10"); // NOI18N
        cmbJam10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam10MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam10);
        cmbJam10.setBounds(402, 738, 45, 23);

        cmbMnt10.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt10.setName("cmbMnt10"); // NOI18N
        cmbMnt10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt10MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt10);
        cmbMnt10.setBounds(455, 738, 45, 23);

        cmbDtk10.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk10.setName("cmbDtk10"); // NOI18N
        cmbDtk10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk10MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk10);
        cmbDtk10.setBounds(507, 738, 45, 23);

        jLabel275.setForeground(new java.awt.Color(0, 0, 0));
        jLabel275.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel275.setText("Wita");
        jLabel275.setName("jLabel275"); // NOI18N
        FormInput.add(jLabel275);
        jLabel275.setBounds(559, 738, 50, 23);

        jLabel276.setForeground(new java.awt.Color(0, 0, 0));
        jLabel276.setText("Batuk :");
        jLabel276.setName("jLabel276"); // NOI18N
        FormInput.add(jLabel276);
        jLabel276.setBounds(0, 766, 120, 23);

        cmbBatuk.setBackground(new java.awt.Color(245, 253, 240));
        cmbBatuk.setForeground(new java.awt.Color(0, 0, 0));
        cmbBatuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbBatuk.setLightWeightPopupEnabled(false);
        cmbBatuk.setName("cmbBatuk"); // NOI18N
        cmbBatuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBatukActionPerformed(evt);
            }
        });
        FormInput.add(cmbBatuk);
        cmbBatuk.setBounds(125, 766, 60, 23);

        jLabel277.setForeground(new java.awt.Color(0, 0, 0));
        jLabel277.setText("Mulai Tgl. : ");
        jLabel277.setName("jLabel277"); // NOI18N
        FormInput.add(jLabel277);
        jLabel277.setBounds(192, 766, 65, 23);

        TtglBatuk.setEditable(false);
        TtglBatuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
        TtglBatuk.setDisplayFormat("dd-MM-yyyy");
        TtglBatuk.setName("TtglBatuk"); // NOI18N
        TtglBatuk.setOpaque(false);
        TtglBatuk.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglBatuk);
        TtglBatuk.setBounds(262, 766, 90, 23);

        jLabel278.setForeground(new java.awt.Color(0, 0, 0));
        jLabel278.setText("Jam : ");
        jLabel278.setName("jLabel278"); // NOI18N
        FormInput.add(jLabel278);
        jLabel278.setBounds(352, 766, 45, 23);

        cmbJam11.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam11.setName("cmbJam11"); // NOI18N
        cmbJam11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam11MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam11);
        cmbJam11.setBounds(402, 766, 45, 23);

        cmbMnt11.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt11.setName("cmbMnt11"); // NOI18N
        cmbMnt11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt11MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt11);
        cmbMnt11.setBounds(455, 766, 45, 23);

        cmbDtk11.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk11.setName("cmbDtk11"); // NOI18N
        cmbDtk11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk11MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk11);
        cmbDtk11.setBounds(507, 766, 45, 23);

        jLabel279.setForeground(new java.awt.Color(0, 0, 0));
        jLabel279.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel279.setText("Wita");
        jLabel279.setName("jLabel279"); // NOI18N
        FormInput.add(jLabel279);
        jLabel279.setBounds(559, 766, 50, 23);

        jLabel280.setForeground(new java.awt.Color(0, 0, 0));
        jLabel280.setText("Pilek :");
        jLabel280.setName("jLabel280"); // NOI18N
        FormInput.add(jLabel280);
        jLabel280.setBounds(0, 794, 120, 23);

        cmbPilek.setBackground(new java.awt.Color(245, 253, 240));
        cmbPilek.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilek.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPilek.setLightWeightPopupEnabled(false);
        cmbPilek.setName("cmbPilek"); // NOI18N
        cmbPilek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPilekActionPerformed(evt);
            }
        });
        FormInput.add(cmbPilek);
        cmbPilek.setBounds(125, 794, 60, 23);

        jLabel281.setForeground(new java.awt.Color(0, 0, 0));
        jLabel281.setText("Mulai Tgl. : ");
        jLabel281.setName("jLabel281"); // NOI18N
        FormInput.add(jLabel281);
        jLabel281.setBounds(192, 794, 65, 23);

        TtglPilek.setEditable(false);
        TtglPilek.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
        TtglPilek.setDisplayFormat("dd-MM-yyyy");
        TtglPilek.setName("TtglPilek"); // NOI18N
        TtglPilek.setOpaque(false);
        TtglPilek.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglPilek);
        TtglPilek.setBounds(262, 794, 90, 23);

        jLabel282.setForeground(new java.awt.Color(0, 0, 0));
        jLabel282.setText("Jam : ");
        jLabel282.setName("jLabel282"); // NOI18N
        FormInput.add(jLabel282);
        jLabel282.setBounds(352, 794, 45, 23);

        cmbJam12.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam12.setName("cmbJam12"); // NOI18N
        cmbJam12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam12MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam12);
        cmbJam12.setBounds(402, 794, 45, 23);

        cmbMnt12.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt12.setName("cmbMnt12"); // NOI18N
        cmbMnt12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt12MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt12);
        cmbMnt12.setBounds(455, 794, 45, 23);

        cmbDtk12.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk12.setName("cmbDtk12"); // NOI18N
        cmbDtk12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk12MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk12);
        cmbDtk12.setBounds(507, 794, 45, 23);

        jLabel283.setForeground(new java.awt.Color(0, 0, 0));
        jLabel283.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel283.setText("Wita");
        jLabel283.setName("jLabel283"); // NOI18N
        FormInput.add(jLabel283);
        jLabel283.setBounds(559, 794, 50, 23);

        jLabel284.setForeground(new java.awt.Color(0, 0, 0));
        jLabel284.setText("Demam :");
        jLabel284.setName("jLabel284"); // NOI18N
        FormInput.add(jLabel284);
        jLabel284.setBounds(0, 822, 120, 23);

        cmbDemam.setBackground(new java.awt.Color(245, 253, 240));
        cmbDemam.setForeground(new java.awt.Color(0, 0, 0));
        cmbDemam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbDemam.setLightWeightPopupEnabled(false);
        cmbDemam.setName("cmbDemam"); // NOI18N
        cmbDemam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDemamActionPerformed(evt);
            }
        });
        FormInput.add(cmbDemam);
        cmbDemam.setBounds(125, 822, 60, 23);

        jLabel285.setForeground(new java.awt.Color(0, 0, 0));
        jLabel285.setText("Mulai Tgl. : ");
        jLabel285.setName("jLabel285"); // NOI18N
        FormInput.add(jLabel285);
        jLabel285.setBounds(192, 822, 65, 23);

        TtglDemam.setEditable(false);
        TtglDemam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
        TtglDemam.setDisplayFormat("dd-MM-yyyy");
        TtglDemam.setName("TtglDemam"); // NOI18N
        TtglDemam.setOpaque(false);
        TtglDemam.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglDemam);
        TtglDemam.setBounds(262, 822, 90, 23);

        jLabel286.setForeground(new java.awt.Color(0, 0, 0));
        jLabel286.setText("Jam : ");
        jLabel286.setName("jLabel286"); // NOI18N
        FormInput.add(jLabel286);
        jLabel286.setBounds(352, 822, 45, 23);

        cmbJam13.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam13.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam13.setName("cmbJam13"); // NOI18N
        cmbJam13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam13MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam13);
        cmbJam13.setBounds(402, 822, 45, 23);

        cmbMnt13.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt13.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt13.setName("cmbMnt13"); // NOI18N
        cmbMnt13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt13MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt13);
        cmbMnt13.setBounds(455, 822, 45, 23);

        cmbDtk13.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk13.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk13.setName("cmbDtk13"); // NOI18N
        cmbDtk13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk13MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk13);
        cmbDtk13.setBounds(507, 822, 45, 23);

        jLabel287.setForeground(new java.awt.Color(0, 0, 0));
        jLabel287.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel287.setText("Wita");
        jLabel287.setName("jLabel287"); // NOI18N
        FormInput.add(jLabel287);
        jLabel287.setBounds(559, 822, 50, 23);

        jLabel288.setForeground(new java.awt.Color(0, 0, 0));
        jLabel288.setText("Riwayat Perjalanan Jauh :");
        jLabel288.setName("jLabel288"); // NOI18N
        FormInput.add(jLabel288);
        jLabel288.setBounds(0, 850, 160, 23);

        cmbRiwPerjalanan.setBackground(new java.awt.Color(245, 253, 240));
        cmbRiwPerjalanan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwPerjalanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbRiwPerjalanan.setLightWeightPopupEnabled(false);
        cmbRiwPerjalanan.setName("cmbRiwPerjalanan"); // NOI18N
        cmbRiwPerjalanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwPerjalananActionPerformed(evt);
            }
        });
        FormInput.add(cmbRiwPerjalanan);
        cmbRiwPerjalanan.setBounds(166, 850, 60, 23);

        TketRiwPerjalanan.setForeground(new java.awt.Color(0, 0, 0));
        TketRiwPerjalanan.setName("TketRiwPerjalanan"); // NOI18N
        TketRiwPerjalanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketRiwPerjalananKeyPressed(evt);
            }
        });
        FormInput.add(TketRiwPerjalanan);
        TketRiwPerjalanan.setBounds(235, 850, 620, 23);

        jLabel289.setForeground(new java.awt.Color(0, 0, 0));
        jLabel289.setText("Vaksin Covid 19 :");
        jLabel289.setName("jLabel289"); // NOI18N
        FormInput.add(jLabel289);
        jLabel289.setBounds(0, 878, 160, 23);

        cmbVaksin.setBackground(new java.awt.Color(245, 253, 240));
        cmbVaksin.setForeground(new java.awt.Color(0, 0, 0));
        cmbVaksin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbVaksin.setLightWeightPopupEnabled(false);
        cmbVaksin.setName("cmbVaksin"); // NOI18N
        cmbVaksin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVaksinActionPerformed(evt);
            }
        });
        FormInput.add(cmbVaksin);
        cmbVaksin.setBounds(166, 878, 60, 23);

        TketVaksin.setForeground(new java.awt.Color(0, 0, 0));
        TketVaksin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TketVaksin.setName("TketVaksin"); // NOI18N
        TketVaksin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketVaksinKeyPressed(evt);
            }
        });
        FormInput.add(TketVaksin);
        TketVaksin.setBounds(235, 878, 55, 23);

        jLabel290.setForeground(new java.awt.Color(0, 0, 0));
        jLabel290.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel290.setText("X");
        jLabel290.setName("jLabel290"); // NOI18N
        FormInput.add(jLabel290);
        jLabel290.setBounds(296, 878, 30, 23);

        jLabel291.setForeground(new java.awt.Color(0, 0, 0));
        jLabel291.setText("Periksa Ketempat Bidan :");
        jLabel291.setName("jLabel291"); // NOI18N
        FormInput.add(jLabel291);
        jLabel291.setBounds(0, 906, 160, 23);

        cmbPeriksa.setBackground(new java.awt.Color(245, 253, 240));
        cmbPeriksa.setForeground(new java.awt.Color(0, 0, 0));
        cmbPeriksa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPeriksa.setLightWeightPopupEnabled(false);
        cmbPeriksa.setName("cmbPeriksa"); // NOI18N
        cmbPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPeriksaActionPerformed(evt);
            }
        });
        FormInput.add(cmbPeriksa);
        cmbPeriksa.setBounds(166, 906, 60, 23);

        jLabel292.setForeground(new java.awt.Color(0, 0, 0));
        jLabel292.setText("Hasil / Riwayat Pemeriksaan Bidan :");
        jLabel292.setName("jLabel292"); // NOI18N
        FormInput.add(jLabel292);
        jLabel292.setBounds(230, 906, 180, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        TketHasilPemeriksaan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TketHasilPemeriksaan.setColumns(20);
        TketHasilPemeriksaan.setRows(5);
        TketHasilPemeriksaan.setName("TketHasilPemeriksaan"); // NOI18N
        TketHasilPemeriksaan.setPreferredSize(new java.awt.Dimension(162, 2000));
        TketHasilPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketHasilPemeriksaanKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(TketHasilPemeriksaan);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(415, 906, 440, 70);

        jLabel293.setForeground(new java.awt.Color(0, 0, 0));
        jLabel293.setText("Ibu ANC :");
        jLabel293.setName("jLabel293"); // NOI18N
        FormInput.add(jLabel293);
        jLabel293.setBounds(0, 982, 160, 23);

        cmbAnc.setBackground(new java.awt.Color(245, 253, 240));
        cmbAnc.setForeground(new java.awt.Color(0, 0, 0));
        cmbAnc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbAnc.setLightWeightPopupEnabled(false);
        cmbAnc.setName("cmbAnc"); // NOI18N
        cmbAnc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAncActionPerformed(evt);
            }
        });
        FormInput.add(cmbAnc);
        cmbAnc.setBounds(166, 982, 60, 23);

        jLabel294.setForeground(new java.awt.Color(0, 0, 0));
        jLabel294.setText("Di : ");
        jLabel294.setName("jLabel294"); // NOI18N
        FormInput.add(jLabel294);
        jLabel294.setBounds(226, 982, 30, 23);

        cmbAncDi.setBackground(new java.awt.Color(245, 253, 240));
        cmbAncDi.setForeground(new java.awt.Color(0, 0, 0));
        cmbAncDi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "PKM", "Bidan" }));
        cmbAncDi.setLightWeightPopupEnabled(false);
        cmbAncDi.setName("cmbAncDi"); // NOI18N
        FormInput.add(cmbAncDi);
        cmbAncDi.setBounds(260, 982, 60, 23);

        TjlhAnc.setForeground(new java.awt.Color(0, 0, 0));
        TjlhAnc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TjlhAnc.setName("TjlhAnc"); // NOI18N
        TjlhAnc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjlhAncKeyPressed(evt);
            }
        });
        FormInput.add(TjlhAnc);
        TjlhAnc.setBounds(325, 982, 55, 23);

        jLabel295.setForeground(new java.awt.Color(0, 0, 0));
        jLabel295.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel295.setText("X");
        jLabel295.setName("jLabel295"); // NOI18N
        FormInput.add(jLabel295);
        jLabel295.setBounds(385, 982, 20, 23);

        jLabel296.setForeground(new java.awt.Color(0, 0, 0));
        jLabel296.setText("Dengan dr. : ");
        jLabel296.setName("jLabel296"); // NOI18N
        FormInput.add(jLabel296);
        jLabel296.setBounds(400, 982, 75, 23);

        TnmDokter1.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter1.setName("TnmDokter1"); // NOI18N
        TnmDokter1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmDokter1KeyPressed(evt);
            }
        });
        FormInput.add(TnmDokter1);
        TnmDokter1.setBounds(480, 982, 375, 23);

        BtnDokter1.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter1.setMnemonic('1');
        BtnDokter1.setToolTipText("Alt+1");
        BtnDokter1.setName("BtnDokter1"); // NOI18N
        BtnDokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter1);
        BtnDokter1.setBounds(855, 982, 28, 23);

        TjlhDokter1.setForeground(new java.awt.Color(0, 0, 0));
        TjlhDokter1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TjlhDokter1.setName("TjlhDokter1"); // NOI18N
        TjlhDokter1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjlhDokter1KeyPressed(evt);
            }
        });
        FormInput.add(TjlhDokter1);
        TjlhDokter1.setBounds(885, 982, 55, 23);

        jLabel297.setForeground(new java.awt.Color(0, 0, 0));
        jLabel297.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel297.setText("X");
        jLabel297.setName("jLabel297"); // NOI18N
        FormInput.add(jLabel297);
        jLabel297.setBounds(945, 982, 20, 23);

        jLabel298.setForeground(new java.awt.Color(0, 0, 0));
        jLabel298.setText("Dengan dr. : ");
        jLabel298.setName("jLabel298"); // NOI18N
        FormInput.add(jLabel298);
        jLabel298.setBounds(400, 1010, 75, 23);

        TnmDokter2.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter2.setName("TnmDokter2"); // NOI18N
        TnmDokter2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmDokter2KeyPressed(evt);
            }
        });
        FormInput.add(TnmDokter2);
        TnmDokter2.setBounds(480, 1010, 375, 23);

        BtnDokter2.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokter2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter2.setMnemonic('1');
        BtnDokter2.setToolTipText("Alt+1");
        BtnDokter2.setName("BtnDokter2"); // NOI18N
        BtnDokter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter2ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter2);
        BtnDokter2.setBounds(855, 1010, 28, 23);

        TjlhDokter2.setForeground(new java.awt.Color(0, 0, 0));
        TjlhDokter2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TjlhDokter2.setName("TjlhDokter2"); // NOI18N
        TjlhDokter2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjlhDokter2KeyPressed(evt);
            }
        });
        FormInput.add(TjlhDokter2);
        TjlhDokter2.setBounds(885, 1010, 55, 23);

        jLabel299.setForeground(new java.awt.Color(0, 0, 0));
        jLabel299.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel299.setText("X");
        jLabel299.setName("jLabel299"); // NOI18N
        FormInput.add(jLabel299);
        jLabel299.setBounds(945, 1010, 20, 23);

        jLabel300.setForeground(new java.awt.Color(0, 0, 0));
        jLabel300.setText("Dengan dr. : ");
        jLabel300.setName("jLabel300"); // NOI18N
        FormInput.add(jLabel300);
        jLabel300.setBounds(400, 1038, 75, 23);

        TnmDokter3.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter3.setName("TnmDokter3"); // NOI18N
        TnmDokter3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmDokter3KeyPressed(evt);
            }
        });
        FormInput.add(TnmDokter3);
        TnmDokter3.setBounds(480, 1038, 375, 23);

        BtnDokter3.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokter3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter3.setMnemonic('1');
        BtnDokter3.setToolTipText("Alt+1");
        BtnDokter3.setName("BtnDokter3"); // NOI18N
        BtnDokter3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter3ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter3);
        BtnDokter3.setBounds(855, 1038, 28, 23);

        TjlhDokter3.setForeground(new java.awt.Color(0, 0, 0));
        TjlhDokter3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TjlhDokter3.setName("TjlhDokter3"); // NOI18N
        TjlhDokter3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjlhDokter3KeyPressed(evt);
            }
        });
        FormInput.add(TjlhDokter3);
        TjlhDokter3.setBounds(885, 1038, 55, 23);

        jLabel301.setForeground(new java.awt.Color(0, 0, 0));
        jLabel301.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel301.setText("X");
        jLabel301.setName("jLabel301"); // NOI18N
        FormInput.add(jLabel301);
        jLabel301.setBounds(945, 1038, 20, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("RIWAYAT KEHAMILAN SEKARANG");
        jLabel122.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(0, 1066, 220, 23);

        jLabel302.setForeground(new java.awt.Color(0, 0, 0));
        jLabel302.setText("HPHT :");
        jLabel302.setName("jLabel302"); // NOI18N
        FormInput.add(jLabel302);
        jLabel302.setBounds(0, 1094, 120, 23);

        Thpht.setForeground(new java.awt.Color(0, 0, 0));
        Thpht.setName("Thpht"); // NOI18N
        Thpht.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThphtKeyPressed(evt);
            }
        });
        FormInput.add(Thpht);
        Thpht.setBounds(125, 1094, 310, 23);

        jLabel303.setForeground(new java.awt.Color(0, 0, 0));
        jLabel303.setText("HPL : ");
        jLabel303.setName("jLabel303"); // NOI18N
        FormInput.add(jLabel303);
        jLabel303.setBounds(435, 1094, 40, 23);

        Thpl.setForeground(new java.awt.Color(0, 0, 0));
        Thpl.setName("Thpl"); // NOI18N
        Thpl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThplKeyPressed(evt);
            }
        });
        FormInput.add(Thpl);
        Thpl.setBounds(480, 1094, 220, 23);

        jLabel304.setForeground(new java.awt.Color(0, 0, 0));
        jLabel304.setText("UK :");
        jLabel304.setName("jLabel304"); // NOI18N
        FormInput.add(jLabel304);
        jLabel304.setBounds(700, 1094, 30, 23);

        Tuk.setForeground(new java.awt.Color(0, 0, 0));
        Tuk.setName("Tuk"); // NOI18N
        Tuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TukKeyPressed(evt);
            }
        });
        FormInput.add(Tuk);
        Tuk.setBounds(735, 1094, 80, 23);

        jLabel305.setForeground(new java.awt.Color(0, 0, 0));
        jLabel305.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel305.setText("mg.");
        jLabel305.setName("jLabel305"); // NOI18N
        FormInput.add(jLabel305);
        jLabel305.setBounds(820, 1094, 30, 23);

        jLabel306.setForeground(new java.awt.Color(0, 0, 0));
        jLabel306.setText("BB Sebelum Hamil :");
        jLabel306.setName("jLabel306"); // NOI18N
        FormInput.add(jLabel306);
        jLabel306.setBounds(0, 1122, 120, 23);

        TbbSebelum.setForeground(new java.awt.Color(0, 0, 0));
        TbbSebelum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TbbSebelum.setName("TbbSebelum"); // NOI18N
        TbbSebelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbSebelumKeyPressed(evt);
            }
        });
        FormInput.add(TbbSebelum);
        TbbSebelum.setBounds(125, 1122, 60, 23);

        jLabel307.setForeground(new java.awt.Color(0, 0, 0));
        jLabel307.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel307.setText("Kg.    BB Terakhir :");
        jLabel307.setName("jLabel307"); // NOI18N
        FormInput.add(jLabel307);
        jLabel307.setBounds(190, 1122, 93, 23);

        TbbTerakhir.setForeground(new java.awt.Color(0, 0, 0));
        TbbTerakhir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TbbTerakhir.setName("TbbTerakhir"); // NOI18N
        TbbTerakhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbTerakhirKeyPressed(evt);
            }
        });
        FormInput.add(TbbTerakhir);
        TbbTerakhir.setBounds(286, 1122, 60, 23);

        jLabel308.setForeground(new java.awt.Color(0, 0, 0));
        jLabel308.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel308.setText("Kg.    TBI :");
        jLabel308.setName("jLabel308"); // NOI18N
        FormInput.add(jLabel308);
        jLabel308.setBounds(350, 1122, 56, 23);

        Ttbi.setForeground(new java.awt.Color(0, 0, 0));
        Ttbi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ttbi.setName("Ttbi"); // NOI18N
        Ttbi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbiKeyPressed(evt);
            }
        });
        FormInput.add(Ttbi);
        Ttbi.setBounds(407, 1122, 60, 23);

        jLabel309.setForeground(new java.awt.Color(0, 0, 0));
        jLabel309.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel309.setText("Cm.");
        jLabel309.setName("jLabel309"); // NOI18N
        FormInput.add(jLabel309);
        jLabel309.setBounds(475, 1122, 30, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("RIWAYAT HAID");
        jLabel123.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(0, 1150, 140, 23);

        jLabel310.setForeground(new java.awt.Color(0, 0, 0));
        jLabel310.setText("Umur Pertama Kali Haid :");
        jLabel310.setName("jLabel310"); // NOI18N
        FormInput.add(jLabel310);
        jLabel310.setBounds(0, 1178, 150, 23);

        TumurPertama.setForeground(new java.awt.Color(0, 0, 0));
        TumurPertama.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TumurPertama.setName("TumurPertama"); // NOI18N
        TumurPertama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurPertamaKeyPressed(evt);
            }
        });
        FormInput.add(TumurPertama);
        TumurPertama.setBounds(155, 1178, 50, 23);

        jLabel311.setForeground(new java.awt.Color(0, 0, 0));
        jLabel311.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel311.setText("tahun       Lamanya Haid :");
        jLabel311.setName("jLabel311"); // NOI18N
        FormInput.add(jLabel311);
        jLabel311.setBounds(210, 1178, 127, 23);

        TlamaHaid.setForeground(new java.awt.Color(0, 0, 0));
        TlamaHaid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TlamaHaid.setName("TlamaHaid"); // NOI18N
        TlamaHaid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlamaHaidKeyPressed(evt);
            }
        });
        FormInput.add(TlamaHaid);
        TlamaHaid.setBounds(340, 1178, 50, 23);

        jLabel312.setForeground(new java.awt.Color(0, 0, 0));
        jLabel312.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel312.setText("hari     Berapa Kali Ganti Pembalut :");
        jLabel312.setName("jLabel312"); // NOI18N
        FormInput.add(jLabel312);
        jLabel312.setBounds(396, 1178, 175, 23);

        Tberapa.setForeground(new java.awt.Color(0, 0, 0));
        Tberapa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tberapa.setName("Tberapa"); // NOI18N
        Tberapa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TberapaKeyPressed(evt);
            }
        });
        FormInput.add(Tberapa);
        Tberapa.setBounds(570, 1178, 50, 23);

        jLabel313.setForeground(new java.awt.Color(0, 0, 0));
        jLabel313.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel313.setText("x / hari");
        jLabel313.setName("jLabel313"); // NOI18N
        FormInput.add(jLabel313);
        jLabel313.setBounds(625, 1178, 50, 23);

        jLabel314.setForeground(new java.awt.Color(0, 0, 0));
        jLabel314.setText("Keluhan Waktu Haid :");
        jLabel314.setName("jLabel314"); // NOI18N
        FormInput.add(jLabel314);
        jLabel314.setBounds(0, 1206, 150, 23);

        cmbKeluhanWaktu.setBackground(new java.awt.Color(245, 253, 240));
        cmbKeluhanWaktu.setForeground(new java.awt.Color(0, 0, 0));
        cmbKeluhanWaktu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbKeluhanWaktu.setLightWeightPopupEnabled(false);
        cmbKeluhanWaktu.setName("cmbKeluhanWaktu"); // NOI18N
        cmbKeluhanWaktu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKeluhanWaktuActionPerformed(evt);
            }
        });
        FormInput.add(cmbKeluhanWaktu);
        cmbKeluhanWaktu.setBounds(155, 1206, 80, 23);

        chkDismen.setBackground(new java.awt.Color(255, 255, 250));
        chkDismen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDismen.setForeground(new java.awt.Color(0, 0, 0));
        chkDismen.setText("Dismenorhoe");
        chkDismen.setBorderPainted(true);
        chkDismen.setBorderPaintedFlat(true);
        chkDismen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDismen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDismen.setName("chkDismen"); // NOI18N
        chkDismen.setOpaque(false);
        chkDismen.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDismen);
        chkDismen.setBounds(244, 1206, 90, 23);

        chkSpoting.setBackground(new java.awt.Color(255, 255, 250));
        chkSpoting.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSpoting.setForeground(new java.awt.Color(0, 0, 0));
        chkSpoting.setText("Spotting");
        chkSpoting.setBorderPainted(true);
        chkSpoting.setBorderPaintedFlat(true);
        chkSpoting.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSpoting.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSpoting.setName("chkSpoting"); // NOI18N
        chkSpoting.setOpaque(false);
        chkSpoting.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSpoting);
        chkSpoting.setBounds(350, 1206, 70, 23);

        chkMenor.setBackground(new java.awt.Color(255, 255, 250));
        chkMenor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMenor.setForeground(new java.awt.Color(0, 0, 0));
        chkMenor.setText("Menorhagia");
        chkMenor.setBorderPainted(true);
        chkMenor.setBorderPaintedFlat(true);
        chkMenor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMenor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMenor.setName("chkMenor"); // NOI18N
        chkMenor.setOpaque(false);
        chkMenor.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMenor);
        chkMenor.setBounds(430, 1206, 85, 23);

        chkMetro.setBackground(new java.awt.Color(255, 255, 250));
        chkMetro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMetro.setForeground(new java.awt.Color(0, 0, 0));
        chkMetro.setText("Metrorhagia");
        chkMetro.setBorderPainted(true);
        chkMetro.setBorderPaintedFlat(true);
        chkMetro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMetro.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMetro.setName("chkMetro"); // NOI18N
        chkMetro.setOpaque(false);
        chkMetro.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMetro);
        chkMetro.setBounds(525, 1206, 85, 23);

        chkKeluhanLain.setBackground(new java.awt.Color(255, 255, 250));
        chkKeluhanLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKeluhanLain.setForeground(new java.awt.Color(0, 0, 0));
        chkKeluhanLain.setText("Lainnya");
        chkKeluhanLain.setBorderPainted(true);
        chkKeluhanLain.setBorderPaintedFlat(true);
        chkKeluhanLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKeluhanLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKeluhanLain.setName("chkKeluhanLain"); // NOI18N
        chkKeluhanLain.setOpaque(false);
        chkKeluhanLain.setPreferredSize(new java.awt.Dimension(175, 23));
        chkKeluhanLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkKeluhanLainActionPerformed(evt);
            }
        });
        FormInput.add(chkKeluhanLain);
        chkKeluhanLain.setBounds(620, 1206, 65, 23);

        TkeluhanLain.setForeground(new java.awt.Color(0, 0, 0));
        TkeluhanLain.setName("TkeluhanLain"); // NOI18N
        TkeluhanLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeluhanLainKeyPressed(evt);
            }
        });
        FormInput.add(TkeluhanLain);
        TkeluhanLain.setBounds(685, 1206, 170, 23);

        jLabel315.setForeground(new java.awt.Color(0, 0, 0));
        jLabel315.setText("Riwayat Peny. Dahulu :");
        jLabel315.setName("jLabel315"); // NOI18N
        FormInput.add(jLabel315);
        jLabel315.setBounds(0, 1234, 150, 23);

        cmbRiwPenDahulu.setBackground(new java.awt.Color(245, 253, 240));
        cmbRiwPenDahulu.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwPenDahulu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbRiwPenDahulu.setLightWeightPopupEnabled(false);
        cmbRiwPenDahulu.setName("cmbRiwPenDahulu"); // NOI18N
        cmbRiwPenDahulu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwPenDahuluActionPerformed(evt);
            }
        });
        FormInput.add(cmbRiwPenDahulu);
        cmbRiwPenDahulu.setBounds(155, 1234, 80, 23);

        chkHipertensiDahulu.setBackground(new java.awt.Color(255, 255, 250));
        chkHipertensiDahulu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHipertensiDahulu.setForeground(new java.awt.Color(0, 0, 0));
        chkHipertensiDahulu.setText("Hipertensi");
        chkHipertensiDahulu.setBorderPainted(true);
        chkHipertensiDahulu.setBorderPaintedFlat(true);
        chkHipertensiDahulu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHipertensiDahulu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHipertensiDahulu.setName("chkHipertensiDahulu"); // NOI18N
        chkHipertensiDahulu.setOpaque(false);
        chkHipertensiDahulu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHipertensiDahulu);
        chkHipertensiDahulu.setBounds(244, 1234, 90, 23);

        chkDmDahulu.setBackground(new java.awt.Color(255, 255, 250));
        chkDmDahulu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDmDahulu.setForeground(new java.awt.Color(0, 0, 0));
        chkDmDahulu.setText("DM");
        chkDmDahulu.setBorderPainted(true);
        chkDmDahulu.setBorderPaintedFlat(true);
        chkDmDahulu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDmDahulu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDmDahulu.setName("chkDmDahulu"); // NOI18N
        chkDmDahulu.setOpaque(false);
        chkDmDahulu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDmDahulu);
        chkDmDahulu.setBounds(350, 1234, 50, 23);

        chkJantungDahulu.setBackground(new java.awt.Color(255, 255, 250));
        chkJantungDahulu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJantungDahulu.setForeground(new java.awt.Color(0, 0, 0));
        chkJantungDahulu.setText("Jantung");
        chkJantungDahulu.setBorderPainted(true);
        chkJantungDahulu.setBorderPaintedFlat(true);
        chkJantungDahulu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJantungDahulu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJantungDahulu.setName("chkJantungDahulu"); // NOI18N
        chkJantungDahulu.setOpaque(false);
        chkJantungDahulu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkJantungDahulu);
        chkJantungDahulu.setBounds(430, 1234, 85, 23);

        chkAsmaDahulu.setBackground(new java.awt.Color(255, 255, 250));
        chkAsmaDahulu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsmaDahulu.setForeground(new java.awt.Color(0, 0, 0));
        chkAsmaDahulu.setText("Asma");
        chkAsmaDahulu.setBorderPainted(true);
        chkAsmaDahulu.setBorderPaintedFlat(true);
        chkAsmaDahulu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsmaDahulu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsmaDahulu.setName("chkAsmaDahulu"); // NOI18N
        chkAsmaDahulu.setOpaque(false);
        chkAsmaDahulu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAsmaDahulu);
        chkAsmaDahulu.setBounds(525, 1234, 60, 23);

        chkLainDahulu.setBackground(new java.awt.Color(255, 255, 250));
        chkLainDahulu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainDahulu.setForeground(new java.awt.Color(0, 0, 0));
        chkLainDahulu.setText("Lainnya");
        chkLainDahulu.setBorderPainted(true);
        chkLainDahulu.setBorderPaintedFlat(true);
        chkLainDahulu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainDahulu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainDahulu.setName("chkLainDahulu"); // NOI18N
        chkLainDahulu.setOpaque(false);
        chkLainDahulu.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainDahulu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainDahuluActionPerformed(evt);
            }
        });
        FormInput.add(chkLainDahulu);
        chkLainDahulu.setBounds(620, 1234, 65, 23);

        TlainDahulu.setForeground(new java.awt.Color(0, 0, 0));
        TlainDahulu.setName("TlainDahulu"); // NOI18N
        TlainDahulu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainDahuluKeyPressed(evt);
            }
        });
        FormInput.add(TlainDahulu);
        TlainDahulu.setBounds(685, 1234, 170, 23);

        jLabel316.setForeground(new java.awt.Color(0, 0, 0));
        jLabel316.setText("Riwayat Peny. Keluarga :");
        jLabel316.setName("jLabel316"); // NOI18N
        FormInput.add(jLabel316);
        jLabel316.setBounds(0, 1262, 150, 23);

        cmbRiwPenKeluarga.setBackground(new java.awt.Color(245, 253, 240));
        cmbRiwPenKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwPenKeluarga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbRiwPenKeluarga.setLightWeightPopupEnabled(false);
        cmbRiwPenKeluarga.setName("cmbRiwPenKeluarga"); // NOI18N
        cmbRiwPenKeluarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwPenKeluargaActionPerformed(evt);
            }
        });
        FormInput.add(cmbRiwPenKeluarga);
        cmbRiwPenKeluarga.setBounds(155, 1262, 80, 23);

        chkHipertensiKeluarga.setBackground(new java.awt.Color(255, 255, 250));
        chkHipertensiKeluarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHipertensiKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        chkHipertensiKeluarga.setText("Hipertensi");
        chkHipertensiKeluarga.setBorderPainted(true);
        chkHipertensiKeluarga.setBorderPaintedFlat(true);
        chkHipertensiKeluarga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHipertensiKeluarga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHipertensiKeluarga.setName("chkHipertensiKeluarga"); // NOI18N
        chkHipertensiKeluarga.setOpaque(false);
        chkHipertensiKeluarga.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHipertensiKeluarga);
        chkHipertensiKeluarga.setBounds(244, 1262, 90, 23);

        chkDmKeluarga.setBackground(new java.awt.Color(255, 255, 250));
        chkDmKeluarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDmKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        chkDmKeluarga.setText("DM");
        chkDmKeluarga.setBorderPainted(true);
        chkDmKeluarga.setBorderPaintedFlat(true);
        chkDmKeluarga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDmKeluarga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDmKeluarga.setName("chkDmKeluarga"); // NOI18N
        chkDmKeluarga.setOpaque(false);
        chkDmKeluarga.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDmKeluarga);
        chkDmKeluarga.setBounds(350, 1262, 50, 23);

        chkJantungKeluarga.setBackground(new java.awt.Color(255, 255, 250));
        chkJantungKeluarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJantungKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        chkJantungKeluarga.setText("Jantung");
        chkJantungKeluarga.setBorderPainted(true);
        chkJantungKeluarga.setBorderPaintedFlat(true);
        chkJantungKeluarga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJantungKeluarga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJantungKeluarga.setName("chkJantungKeluarga"); // NOI18N
        chkJantungKeluarga.setOpaque(false);
        chkJantungKeluarga.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkJantungKeluarga);
        chkJantungKeluarga.setBounds(430, 1262, 85, 23);

        chkAsmaKeluarga.setBackground(new java.awt.Color(255, 255, 250));
        chkAsmaKeluarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsmaKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        chkAsmaKeluarga.setText("Asma");
        chkAsmaKeluarga.setBorderPainted(true);
        chkAsmaKeluarga.setBorderPaintedFlat(true);
        chkAsmaKeluarga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsmaKeluarga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsmaKeluarga.setName("chkAsmaKeluarga"); // NOI18N
        chkAsmaKeluarga.setOpaque(false);
        chkAsmaKeluarga.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAsmaKeluarga);
        chkAsmaKeluarga.setBounds(525, 1262, 60, 23);

        chkLainKeluarga.setBackground(new java.awt.Color(255, 255, 250));
        chkLainKeluarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        chkLainKeluarga.setText("Lainnya");
        chkLainKeluarga.setBorderPainted(true);
        chkLainKeluarga.setBorderPaintedFlat(true);
        chkLainKeluarga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainKeluarga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainKeluarga.setName("chkLainKeluarga"); // NOI18N
        chkLainKeluarga.setOpaque(false);
        chkLainKeluarga.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainKeluarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainKeluargaActionPerformed(evt);
            }
        });
        FormInput.add(chkLainKeluarga);
        chkLainKeluarga.setBounds(620, 1262, 65, 23);

        TlainKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        TlainKeluarga.setName("TlainKeluarga"); // NOI18N
        TlainKeluarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainKeluargaKeyPressed(evt);
            }
        });
        FormInput.add(TlainKeluarga);
        TlainKeluarga.setBounds(685, 1262, 170, 23);

        jLabel317.setForeground(new java.awt.Color(0, 0, 0));
        jLabel317.setText("Riwayat Ginekologi :");
        jLabel317.setName("jLabel317"); // NOI18N
        FormInput.add(jLabel317);
        jLabel317.setBounds(0, 1290, 150, 23);

        cmbRiwGinekologi.setBackground(new java.awt.Color(245, 253, 240));
        cmbRiwGinekologi.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwGinekologi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbRiwGinekologi.setLightWeightPopupEnabled(false);
        cmbRiwGinekologi.setName("cmbRiwGinekologi"); // NOI18N
        cmbRiwGinekologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwGinekologiActionPerformed(evt);
            }
        });
        FormInput.add(cmbRiwGinekologi);
        cmbRiwGinekologi.setBounds(155, 1290, 80, 23);

        TriwGinekologi.setForeground(new java.awt.Color(0, 0, 0));
        TriwGinekologi.setName("TriwGinekologi"); // NOI18N
        TriwGinekologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwGinekologiKeyPressed(evt);
            }
        });
        FormInput.add(TriwGinekologi);
        TriwGinekologi.setBounds(244, 1290, 611, 23);

        jLabel318.setForeground(new java.awt.Color(0, 0, 0));
        jLabel318.setText("Riwayat KB :");
        jLabel318.setName("jLabel318"); // NOI18N
        FormInput.add(jLabel318);
        jLabel318.setBounds(0, 1318, 150, 23);

        chkPil.setBackground(new java.awt.Color(255, 255, 250));
        chkPil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPil.setForeground(new java.awt.Color(0, 0, 0));
        chkPil.setText("Pil, Lama : ");
        chkPil.setBorderPainted(true);
        chkPil.setBorderPaintedFlat(true);
        chkPil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPil.setName("chkPil"); // NOI18N
        chkPil.setOpaque(false);
        chkPil.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPilActionPerformed(evt);
            }
        });
        FormInput.add(chkPil);
        chkPil.setBounds(155, 1318, 75, 23);

        TlamaPil.setForeground(new java.awt.Color(0, 0, 0));
        TlamaPil.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TlamaPil.setName("TlamaPil"); // NOI18N
        TlamaPil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlamaPilKeyPressed(evt);
            }
        });
        FormInput.add(TlamaPil);
        TlamaPil.setBounds(230, 1318, 50, 23);

        cmbSatLamaPil.setBackground(new java.awt.Color(245, 253, 240));
        cmbSatLamaPil.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatLamaPil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bulan", "Tahun" }));
        cmbSatLamaPil.setLightWeightPopupEnabled(false);
        cmbSatLamaPil.setName("cmbSatLamaPil"); // NOI18N
        FormInput.add(cmbSatLamaPil);
        cmbSatLamaPil.setBounds(285, 1318, 65, 23);

        chkSuntik1.setBackground(new java.awt.Color(255, 255, 250));
        chkSuntik1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSuntik1.setForeground(new java.awt.Color(0, 0, 0));
        chkSuntik1.setText("Suntik 1 Bulan, Lama : ");
        chkSuntik1.setBorderPainted(true);
        chkSuntik1.setBorderPaintedFlat(true);
        chkSuntik1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSuntik1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSuntik1.setName("chkSuntik1"); // NOI18N
        chkSuntik1.setOpaque(false);
        chkSuntik1.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSuntik1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSuntik1ActionPerformed(evt);
            }
        });
        FormInput.add(chkSuntik1);
        chkSuntik1.setBounds(155, 1346, 130, 23);

        TlamaSuntik1.setForeground(new java.awt.Color(0, 0, 0));
        TlamaSuntik1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TlamaSuntik1.setName("TlamaSuntik1"); // NOI18N
        TlamaSuntik1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlamaSuntik1KeyPressed(evt);
            }
        });
        FormInput.add(TlamaSuntik1);
        TlamaSuntik1.setBounds(285, 1346, 50, 23);

        cmbSatLamaSuntik1.setBackground(new java.awt.Color(245, 253, 240));
        cmbSatLamaSuntik1.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatLamaSuntik1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bulan", "Tahun" }));
        cmbSatLamaSuntik1.setLightWeightPopupEnabled(false);
        cmbSatLamaSuntik1.setName("cmbSatLamaSuntik1"); // NOI18N
        FormInput.add(cmbSatLamaSuntik1);
        cmbSatLamaSuntik1.setBounds(342, 1346, 65, 23);

        chkSuntik3.setBackground(new java.awt.Color(255, 255, 250));
        chkSuntik3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSuntik3.setForeground(new java.awt.Color(0, 0, 0));
        chkSuntik3.setText("Suntik 3 Bulan, Lama : ");
        chkSuntik3.setBorderPainted(true);
        chkSuntik3.setBorderPaintedFlat(true);
        chkSuntik3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSuntik3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSuntik3.setName("chkSuntik3"); // NOI18N
        chkSuntik3.setOpaque(false);
        chkSuntik3.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSuntik3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSuntik3ActionPerformed(evt);
            }
        });
        FormInput.add(chkSuntik3);
        chkSuntik3.setBounds(155, 1374, 130, 23);

        TlamaSuntik3.setForeground(new java.awt.Color(0, 0, 0));
        TlamaSuntik3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TlamaSuntik3.setName("TlamaSuntik3"); // NOI18N
        TlamaSuntik3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlamaSuntik3KeyPressed(evt);
            }
        });
        FormInput.add(TlamaSuntik3);
        TlamaSuntik3.setBounds(285, 1374, 50, 23);

        cmbSatLamaSuntik3.setBackground(new java.awt.Color(245, 253, 240));
        cmbSatLamaSuntik3.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatLamaSuntik3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bulan", "Tahun" }));
        cmbSatLamaSuntik3.setLightWeightPopupEnabled(false);
        cmbSatLamaSuntik3.setName("cmbSatLamaSuntik3"); // NOI18N
        FormInput.add(cmbSatLamaSuntik3);
        cmbSatLamaSuntik3.setBounds(342, 1374, 65, 23);

        chkImplan.setBackground(new java.awt.Color(255, 255, 250));
        chkImplan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkImplan.setForeground(new java.awt.Color(0, 0, 0));
        chkImplan.setText("Implan, Lama : ");
        chkImplan.setBorderPainted(true);
        chkImplan.setBorderPaintedFlat(true);
        chkImplan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkImplan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkImplan.setName("chkImplan"); // NOI18N
        chkImplan.setOpaque(false);
        chkImplan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkImplan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkImplanActionPerformed(evt);
            }
        });
        FormInput.add(chkImplan);
        chkImplan.setBounds(460, 1318, 95, 23);

        TlamaImplan.setForeground(new java.awt.Color(0, 0, 0));
        TlamaImplan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TlamaImplan.setName("TlamaImplan"); // NOI18N
        TlamaImplan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlamaImplanKeyPressed(evt);
            }
        });
        FormInput.add(TlamaImplan);
        TlamaImplan.setBounds(556, 1318, 50, 23);

        cmbSatLamaImplan.setBackground(new java.awt.Color(245, 253, 240));
        cmbSatLamaImplan.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatLamaImplan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bulan", "Tahun" }));
        cmbSatLamaImplan.setLightWeightPopupEnabled(false);
        cmbSatLamaImplan.setName("cmbSatLamaImplan"); // NOI18N
        FormInput.add(cmbSatLamaImplan);
        cmbSatLamaImplan.setBounds(615, 1318, 65, 23);

        chkIud.setBackground(new java.awt.Color(255, 255, 250));
        chkIud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIud.setForeground(new java.awt.Color(0, 0, 0));
        chkIud.setText("IUD, Lama : ");
        chkIud.setBorderPainted(true);
        chkIud.setBorderPaintedFlat(true);
        chkIud.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIud.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIud.setName("chkIud"); // NOI18N
        chkIud.setOpaque(false);
        chkIud.setPreferredSize(new java.awt.Dimension(175, 23));
        chkIud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkIudActionPerformed(evt);
            }
        });
        FormInput.add(chkIud);
        chkIud.setBounds(460, 1346, 82, 23);

        TlamaIud.setForeground(new java.awt.Color(0, 0, 0));
        TlamaIud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TlamaIud.setName("TlamaIud"); // NOI18N
        TlamaIud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlamaIudKeyPressed(evt);
            }
        });
        FormInput.add(TlamaIud);
        TlamaIud.setBounds(543, 1346, 50, 23);

        cmbSatLamaIud.setBackground(new java.awt.Color(245, 253, 240));
        cmbSatLamaIud.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatLamaIud.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bulan", "Tahun" }));
        cmbSatLamaIud.setLightWeightPopupEnabled(false);
        cmbSatLamaIud.setName("cmbSatLamaIud"); // NOI18N
        FormInput.add(cmbSatLamaIud);
        cmbSatLamaIud.setBounds(602, 1346, 65, 23);

        chkTidakPernah.setBackground(new java.awt.Color(255, 255, 250));
        chkTidakPernah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTidakPernah.setForeground(new java.awt.Color(0, 0, 0));
        chkTidakPernah.setText("Tidak Pernah KB");
        chkTidakPernah.setBorderPainted(true);
        chkTidakPernah.setBorderPaintedFlat(true);
        chkTidakPernah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidakPernah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidakPernah.setName("chkTidakPernah"); // NOI18N
        chkTidakPernah.setOpaque(false);
        chkTidakPernah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTidakPernah);
        chkTidakPernah.setBounds(460, 1374, 110, 23);

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setText("RIWAYAT KEHAMILAN, PERSALINAN DAN NIFAS YANG LALU");
        jLabel124.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(0, 1402, 360, 23);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbRiwayat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRiwayat.setName("tbRiwayat"); // NOI18N
        tbRiwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatMouseClicked(evt);
            }
        });
        tbRiwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbRiwayat);

        FormInput.add(Scroll1);
        Scroll1.setBounds(40, 1544, 820, 140);

        BtnTambahRiwayat.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahRiwayat.setText("Tambah");
        BtnTambahRiwayat.setToolTipText("Tambah Riwayat Kehamilan, Persalinan & Nifas Yang Lalu");
        BtnTambahRiwayat.setName("BtnTambahRiwayat"); // NOI18N
        BtnTambahRiwayat.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahRiwayatActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahRiwayat);
        BtnTambahRiwayat.setBounds(870, 1544, 90, 30);

        BtnSimpanRiwayat.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanRiwayat.setText("Simpan");
        BtnSimpanRiwayat.setToolTipText("Simpan Riwayat Kehamilan, Persalinan & Nifas Yang Lalu");
        BtnSimpanRiwayat.setName("BtnSimpanRiwayat"); // NOI18N
        BtnSimpanRiwayat.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnSimpanRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanRiwayatActionPerformed(evt);
            }
        });
        FormInput.add(BtnSimpanRiwayat);
        BtnSimpanRiwayat.setBounds(870, 1580, 90, 30);

        BtnHapusRiwayat.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusRiwayat.setText("Hapus");
        BtnHapusRiwayat.setToolTipText("Hapus Riwayat Kehamilan, Persalinan & Nifas Yang Lalu");
        BtnHapusRiwayat.setName("BtnHapusRiwayat"); // NOI18N
        BtnHapusRiwayat.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusRiwayatActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusRiwayat);
        BtnHapusRiwayat.setBounds(870, 1616, 90, 30);

        BtnGantiRiwayat.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiRiwayat.setText("Ganti");
        BtnGantiRiwayat.setToolTipText("Ganti Riwayat Kehamilan, Persalinan & Nifas Yang Lalu");
        BtnGantiRiwayat.setName("BtnGantiRiwayat"); // NOI18N
        BtnGantiRiwayat.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiRiwayatActionPerformed(evt);
            }
        });
        FormInput.add(BtnGantiRiwayat);
        BtnGantiRiwayat.setBounds(870, 1652, 90, 30);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setText("RIWAYAT PSIKOSOSIAL DAN SPIRITUAL");
        jLabel125.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(0, 1690, 270, 23);

        jLabel328.setForeground(new java.awt.Color(0, 0, 0));
        jLabel328.setText("Status Perkawinan :");
        jLabel328.setName("jLabel328"); // NOI18N
        FormInput.add(jLabel328);
        jLabel328.setBounds(0, 1718, 120, 23);

        cmbSttsPerkawinan.setBackground(new java.awt.Color(245, 253, 240));
        cmbSttsPerkawinan.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsPerkawinan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kawin", "Belum Kawin", "Janda" }));
        cmbSttsPerkawinan.setLightWeightPopupEnabled(false);
        cmbSttsPerkawinan.setName("cmbSttsPerkawinan"); // NOI18N
        cmbSttsPerkawinan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSttsPerkawinanActionPerformed(evt);
            }
        });
        FormInput.add(cmbSttsPerkawinan);
        cmbSttsPerkawinan.setBounds(125, 1718, 95, 23);

        jLabel329.setForeground(new java.awt.Color(0, 0, 0));
        jLabel329.setText("Jumlah Perkawinan :");
        jLabel329.setName("jLabel329"); // NOI18N
        FormInput.add(jLabel329);
        jLabel329.setBounds(220, 1718, 120, 23);

        chkIstri.setBackground(new java.awt.Color(255, 255, 250));
        chkIstri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIstri.setForeground(new java.awt.Color(0, 0, 0));
        chkIstri.setText("Istri");
        chkIstri.setBorderPainted(true);
        chkIstri.setBorderPaintedFlat(true);
        chkIstri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIstri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIstri.setName("chkIstri"); // NOI18N
        chkIstri.setOpaque(false);
        chkIstri.setPreferredSize(new java.awt.Dimension(175, 23));
        chkIstri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkIstriActionPerformed(evt);
            }
        });
        FormInput.add(chkIstri);
        chkIstri.setBounds(347, 1718, 50, 23);

        cmbJlhIstri.setBackground(new java.awt.Color(245, 253, 240));
        cmbJlhIstri.setForeground(new java.awt.Color(0, 0, 0));
        cmbJlhIstri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1 X", "2 X", "3 X" }));
        cmbJlhIstri.setLightWeightPopupEnabled(false);
        cmbJlhIstri.setName("cmbJlhIstri"); // NOI18N
        FormInput.add(cmbJlhIstri);
        cmbJlhIstri.setBounds(403, 1718, 50, 23);

        chkSuami.setBackground(new java.awt.Color(255, 255, 250));
        chkSuami.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSuami.setForeground(new java.awt.Color(0, 0, 0));
        chkSuami.setText("Suami");
        chkSuami.setBorderPainted(true);
        chkSuami.setBorderPaintedFlat(true);
        chkSuami.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSuami.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSuami.setName("chkSuami"); // NOI18N
        chkSuami.setOpaque(false);
        chkSuami.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSuami.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSuamiActionPerformed(evt);
            }
        });
        FormInput.add(chkSuami);
        chkSuami.setBounds(500, 1718, 60, 23);

        cmbJlhSuami.setBackground(new java.awt.Color(245, 253, 240));
        cmbJlhSuami.setForeground(new java.awt.Color(0, 0, 0));
        cmbJlhSuami.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1 X", "2 X", "3 X" }));
        cmbJlhSuami.setLightWeightPopupEnabled(false);
        cmbJlhSuami.setName("cmbJlhSuami"); // NOI18N
        FormInput.add(cmbJlhSuami);
        cmbJlhSuami.setBounds(565, 1718, 50, 23);

        jLabel330.setForeground(new java.awt.Color(0, 0, 0));
        jLabel330.setText("Usia Pertama Kali Nikah :");
        jLabel330.setName("jLabel330"); // NOI18N
        FormInput.add(jLabel330);
        jLabel330.setBounds(0, 1746, 150, 23);

        TusiaPertama.setForeground(new java.awt.Color(0, 0, 0));
        TusiaPertama.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TusiaPertama.setName("TusiaPertama"); // NOI18N
        TusiaPertama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TusiaPertamaKeyPressed(evt);
            }
        });
        FormInput.add(TusiaPertama);
        TusiaPertama.setBounds(155, 1746, 50, 23);

        jLabel331.setForeground(new java.awt.Color(0, 0, 0));
        jLabel331.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel331.setText("Tahun       Usia Perkawinan :");
        jLabel331.setName("jLabel331"); // NOI18N
        FormInput.add(jLabel331);
        jLabel331.setBounds(210, 1746, 140, 23);

        TusiaPerkawinan.setForeground(new java.awt.Color(0, 0, 0));
        TusiaPerkawinan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TusiaPerkawinan.setName("TusiaPerkawinan"); // NOI18N
        TusiaPerkawinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TusiaPerkawinanKeyPressed(evt);
            }
        });
        FormInput.add(TusiaPerkawinan);
        TusiaPerkawinan.setBounds(353, 1746, 50, 23);

        jLabel332.setForeground(new java.awt.Color(0, 0, 0));
        jLabel332.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel332.setText("Tahun       Keluarga Terdekat :");
        jLabel332.setName("jLabel332"); // NOI18N
        FormInput.add(jLabel332);
        jLabel332.setBounds(410, 1746, 150, 23);

        TklgTerdekat.setForeground(new java.awt.Color(0, 0, 0));
        TklgTerdekat.setName("TklgTerdekat"); // NOI18N
        TklgTerdekat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TklgTerdekatKeyPressed(evt);
            }
        });
        FormInput.add(TklgTerdekat);
        TklgTerdekat.setBounds(560, 1746, 160, 23);

        jLabel333.setForeground(new java.awt.Color(0, 0, 0));
        jLabel333.setText("Hubungan :");
        jLabel333.setName("jLabel333"); // NOI18N
        FormInput.add(jLabel333);
        jLabel333.setBounds(720, 1746, 75, 23);

        ThubKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        ThubKeluarga.setName("ThubKeluarga"); // NOI18N
        ThubKeluarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThubKeluargaKeyPressed(evt);
            }
        });
        FormInput.add(ThubKeluarga);
        ThubKeluarga.setBounds(800, 1746, 150, 23);

        jLabel334.setForeground(new java.awt.Color(0, 0, 0));
        jLabel334.setText("Tinggal Dengan :");
        jLabel334.setName("jLabel334"); // NOI18N
        FormInput.add(jLabel334);
        jLabel334.setBounds(0, 1774, 120, 23);

        chkOrtu.setBackground(new java.awt.Color(255, 255, 250));
        chkOrtu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkOrtu.setForeground(new java.awt.Color(0, 0, 0));
        chkOrtu.setText("Orang Tua");
        chkOrtu.setBorderPainted(true);
        chkOrtu.setBorderPaintedFlat(true);
        chkOrtu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkOrtu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkOrtu.setName("chkOrtu"); // NOI18N
        chkOrtu.setOpaque(false);
        chkOrtu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkOrtu);
        chkOrtu.setBounds(125, 1774, 80, 23);

        chkTinggalSuami.setBackground(new java.awt.Color(255, 255, 250));
        chkTinggalSuami.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTinggalSuami.setForeground(new java.awt.Color(0, 0, 0));
        chkTinggalSuami.setText("Suami");
        chkTinggalSuami.setBorderPainted(true);
        chkTinggalSuami.setBorderPaintedFlat(true);
        chkTinggalSuami.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTinggalSuami.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTinggalSuami.setName("chkTinggalSuami"); // NOI18N
        chkTinggalSuami.setOpaque(false);
        chkTinggalSuami.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTinggalSuami);
        chkTinggalSuami.setBounds(213, 1774, 60, 23);

        chkAnak.setBackground(new java.awt.Color(255, 255, 250));
        chkAnak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAnak.setForeground(new java.awt.Color(0, 0, 0));
        chkAnak.setText("Anak");
        chkAnak.setBorderPainted(true);
        chkAnak.setBorderPaintedFlat(true);
        chkAnak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAnak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAnak.setName("chkAnak"); // NOI18N
        chkAnak.setOpaque(false);
        chkAnak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAnak);
        chkAnak.setBounds(280, 1774, 60, 23);

        chkTinggalSendiri.setBackground(new java.awt.Color(255, 255, 250));
        chkTinggalSendiri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTinggalSendiri.setForeground(new java.awt.Color(0, 0, 0));
        chkTinggalSendiri.setText("Sendiri");
        chkTinggalSendiri.setBorderPainted(true);
        chkTinggalSendiri.setBorderPaintedFlat(true);
        chkTinggalSendiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTinggalSendiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTinggalSendiri.setName("chkTinggalSendiri"); // NOI18N
        chkTinggalSendiri.setOpaque(false);
        chkTinggalSendiri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTinggalSendiri);
        chkTinggalSendiri.setBounds(350, 1774, 60, 23);

        jLabel335.setForeground(new java.awt.Color(0, 0, 0));
        jLabel335.setText("Curiga Penganiayaan / Penelantaran :");
        jLabel335.setName("jLabel335"); // NOI18N
        FormInput.add(jLabel335);
        jLabel335.setBounds(420, 1774, 200, 23);

        cmbCuriga.setBackground(new java.awt.Color(245, 253, 240));
        cmbCuriga.setForeground(new java.awt.Color(0, 0, 0));
        cmbCuriga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbCuriga.setLightWeightPopupEnabled(false);
        cmbCuriga.setName("cmbCuriga"); // NOI18N
        FormInput.add(cmbCuriga);
        cmbCuriga.setBounds(627, 1774, 60, 23);

        jLabel336.setForeground(new java.awt.Color(0, 0, 0));
        jLabel336.setText("Kegiatan Ibadah :");
        jLabel336.setName("jLabel336"); // NOI18N
        FormInput.add(jLabel336);
        jLabel336.setBounds(688, 1774, 105, 23);

        cmbKegiatan.setBackground(new java.awt.Color(245, 253, 240));
        cmbKegiatan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKegiatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Tidak Normal" }));
        cmbKegiatan.setLightWeightPopupEnabled(false);
        cmbKegiatan.setName("cmbKegiatan"); // NOI18N
        FormInput.add(cmbKegiatan);
        cmbKegiatan.setBounds(800, 1774, 95, 23);

        jLabel337.setForeground(new java.awt.Color(0, 0, 0));
        jLabel337.setText("Status Emosional :");
        jLabel337.setName("jLabel337"); // NOI18N
        FormInput.add(jLabel337);
        jLabel337.setBounds(0, 1802, 120, 23);

        cmbSttsEmosional.setBackground(new java.awt.Color(245, 253, 240));
        cmbSttsEmosional.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsEmosional.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Tidak Semangat", "Tertekan", "Depresi", "Cemas", "Sulit Tidur" }));
        cmbSttsEmosional.setLightWeightPopupEnabled(false);
        cmbSttsEmosional.setName("cmbSttsEmosional"); // NOI18N
        FormInput.add(cmbSttsEmosional);
        cmbSttsEmosional.setBounds(125, 1802, 110, 23);

        jLabel338.setForeground(new java.awt.Color(0, 0, 0));
        jLabel338.setText("Status Ekonomi :");
        jLabel338.setName("jLabel338"); // NOI18N
        FormInput.add(jLabel338);
        jLabel338.setBounds(240, 1802, 100, 23);

        chkAsuransi.setBackground(new java.awt.Color(255, 255, 250));
        chkAsuransi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsuransi.setForeground(new java.awt.Color(0, 0, 0));
        chkAsuransi.setText("Asuransi");
        chkAsuransi.setBorderPainted(true);
        chkAsuransi.setBorderPaintedFlat(true);
        chkAsuransi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsuransi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsuransi.setName("chkAsuransi"); // NOI18N
        chkAsuransi.setOpaque(false);
        chkAsuransi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAsuransi);
        chkAsuransi.setBounds(348, 1802, 70, 23);

        chkJaminan.setBackground(new java.awt.Color(255, 255, 250));
        chkJaminan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJaminan.setForeground(new java.awt.Color(0, 0, 0));
        chkJaminan.setText("Jaminan");
        chkJaminan.setBorderPainted(true);
        chkJaminan.setBorderPaintedFlat(true);
        chkJaminan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJaminan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJaminan.setName("chkJaminan"); // NOI18N
        chkJaminan.setOpaque(false);
        chkJaminan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkJaminan);
        chkJaminan.setBounds(430, 1802, 70, 23);

        chkBiaya.setBackground(new java.awt.Color(255, 255, 250));
        chkBiaya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBiaya.setForeground(new java.awt.Color(0, 0, 0));
        chkBiaya.setText("Biaya Sendiri");
        chkBiaya.setBorderPainted(true);
        chkBiaya.setBorderPaintedFlat(true);
        chkBiaya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBiaya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBiaya.setName("chkBiaya"); // NOI18N
        chkBiaya.setOpaque(false);
        chkBiaya.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBiaya);
        chkBiaya.setBounds(510, 1802, 90, 23);

        chkSttsLain.setBackground(new java.awt.Color(255, 255, 250));
        chkSttsLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSttsLain.setForeground(new java.awt.Color(0, 0, 0));
        chkSttsLain.setText("Lainnya");
        chkSttsLain.setBorderPainted(true);
        chkSttsLain.setBorderPaintedFlat(true);
        chkSttsLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSttsLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSttsLain.setName("chkSttsLain"); // NOI18N
        chkSttsLain.setOpaque(false);
        chkSttsLain.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSttsLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSttsLainActionPerformed(evt);
            }
        });
        FormInput.add(chkSttsLain);
        chkSttsLain.setBounds(610, 1802, 65, 23);

        TsttsLainEkonomi.setForeground(new java.awt.Color(0, 0, 0));
        TsttsLainEkonomi.setName("TsttsLainEkonomi"); // NOI18N
        TsttsLainEkonomi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsttsLainEkonomiKeyPressed(evt);
            }
        });
        FormInput.add(TsttsLainEkonomi);
        TsttsLainEkonomi.setBounds(675, 1802, 275, 23);

        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setText("PEMERIKSAAN OBSTETRI");
        jLabel126.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(0, 1830, 180, 23);

        jLabel339.setForeground(new java.awt.Color(0, 0, 0));
        jLabel339.setText("Leopold 1 :");
        jLabel339.setName("jLabel339"); // NOI18N
        FormInput.add(jLabel339);
        jLabel339.setBounds(0, 1858, 120, 23);

        Tleo1.setForeground(new java.awt.Color(0, 0, 0));
        Tleo1.setName("Tleo1"); // NOI18N
        Tleo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tleo1KeyPressed(evt);
            }
        });
        FormInput.add(Tleo1);
        Tleo1.setBounds(125, 1858, 825, 23);

        jLabel340.setForeground(new java.awt.Color(0, 0, 0));
        jLabel340.setText("Leopold 2 :");
        jLabel340.setName("jLabel340"); // NOI18N
        FormInput.add(jLabel340);
        jLabel340.setBounds(0, 1886, 120, 23);

        Tleo2.setForeground(new java.awt.Color(0, 0, 0));
        Tleo2.setName("Tleo2"); // NOI18N
        Tleo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tleo2KeyPressed(evt);
            }
        });
        FormInput.add(Tleo2);
        Tleo2.setBounds(125, 1886, 825, 23);

        jLabel341.setForeground(new java.awt.Color(0, 0, 0));
        jLabel341.setText("Leopold 3 :");
        jLabel341.setName("jLabel341"); // NOI18N
        FormInput.add(jLabel341);
        jLabel341.setBounds(0, 1914, 120, 23);

        Tleo3.setForeground(new java.awt.Color(0, 0, 0));
        Tleo3.setName("Tleo3"); // NOI18N
        Tleo3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tleo3KeyPressed(evt);
            }
        });
        FormInput.add(Tleo3);
        Tleo3.setBounds(125, 1914, 825, 23);

        jLabel342.setForeground(new java.awt.Color(0, 0, 0));
        jLabel342.setText("Leopold 4 :");
        jLabel342.setName("jLabel342"); // NOI18N
        FormInput.add(jLabel342);
        jLabel342.setBounds(0, 1942, 120, 23);

        Tleo4.setForeground(new java.awt.Color(0, 0, 0));
        Tleo4.setName("Tleo4"); // NOI18N
        Tleo4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tleo4KeyPressed(evt);
            }
        });
        FormInput.add(Tleo4);
        Tleo4.setBounds(125, 1942, 825, 23);

        jLabel343.setForeground(new java.awt.Color(0, 0, 0));
        jLabel343.setText("Bandle Ring :");
        jLabel343.setName("jLabel343"); // NOI18N
        FormInput.add(jLabel343);
        jLabel343.setBounds(0, 1970, 120, 23);

        cmbBandle.setBackground(new java.awt.Color(245, 253, 240));
        cmbBandle.setForeground(new java.awt.Color(0, 0, 0));
        cmbBandle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbBandle.setLightWeightPopupEnabled(false);
        cmbBandle.setName("cmbBandle"); // NOI18N
        FormInput.add(cmbBandle);
        cmbBandle.setBounds(125, 1970, 60, 23);

        jLabel344.setForeground(new java.awt.Color(0, 0, 0));
        jLabel344.setText("Perut Tegang Terus Menerus Seperti Papan :");
        jLabel344.setName("jLabel344"); // NOI18N
        FormInput.add(jLabel344);
        jLabel344.setBounds(185, 1970, 240, 23);

        cmbPerutTegang.setBackground(new java.awt.Color(245, 253, 240));
        cmbPerutTegang.setForeground(new java.awt.Color(0, 0, 0));
        cmbPerutTegang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPerutTegang.setLightWeightPopupEnabled(false);
        cmbPerutTegang.setName("cmbPerutTegang"); // NOI18N
        FormInput.add(cmbPerutTegang);
        cmbPerutTegang.setBounds(434, 1970, 60, 23);

        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setText("PEMERIKSAAN GINEKOLOGI");
        jLabel127.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(0, 1998, 180, 23);

        jLabel345.setForeground(new java.awt.Color(0, 0, 0));
        jLabel345.setText("Palpasi :");
        jLabel345.setName("jLabel345"); // NOI18N
        FormInput.add(jLabel345);
        jLabel345.setBounds(0, 2026, 120, 23);

        Tpalpasi.setForeground(new java.awt.Color(0, 0, 0));
        Tpalpasi.setName("Tpalpasi"); // NOI18N
        Tpalpasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpalpasiKeyPressed(evt);
            }
        });
        FormInput.add(Tpalpasi);
        Tpalpasi.setBounds(125, 2026, 340, 23);

        jLabel346.setForeground(new java.awt.Color(0, 0, 0));
        jLabel346.setText("Teraba Massa :");
        jLabel346.setName("jLabel346"); // NOI18N
        FormInput.add(jLabel346);
        jLabel346.setBounds(0, 2054, 120, 23);

        cmbTeraba.setBackground(new java.awt.Color(245, 253, 240));
        cmbTeraba.setForeground(new java.awt.Color(0, 0, 0));
        cmbTeraba.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbTeraba.setLightWeightPopupEnabled(false);
        cmbTeraba.setName("cmbTeraba"); // NOI18N
        cmbTeraba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTerabaActionPerformed(evt);
            }
        });
        FormInput.add(cmbTeraba);
        cmbTeraba.setBounds(125, 2054, 60, 23);

        jLabel347.setForeground(new java.awt.Color(0, 0, 0));
        jLabel347.setText("Sebesar : ");
        jLabel347.setName("jLabel347"); // NOI18N
        FormInput.add(jLabel347);
        jLabel347.setBounds(185, 2054, 70, 23);

        Tsebesar.setForeground(new java.awt.Color(0, 0, 0));
        Tsebesar.setName("Tsebesar"); // NOI18N
        Tsebesar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsebesarKeyPressed(evt);
            }
        });
        FormInput.add(Tsebesar);
        Tsebesar.setBounds(255, 2054, 210, 23);

        jLabel348.setForeground(new java.awt.Color(0, 0, 0));
        jLabel348.setText("Goyang :");
        jLabel348.setName("jLabel348"); // NOI18N
        FormInput.add(jLabel348);
        jLabel348.setBounds(465, 2026, 80, 23);

        cmbGoyang.setBackground(new java.awt.Color(245, 253, 240));
        cmbGoyang.setForeground(new java.awt.Color(0, 0, 0));
        cmbGoyang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbGoyang.setLightWeightPopupEnabled(false);
        cmbGoyang.setName("cmbGoyang"); // NOI18N
        FormInput.add(cmbGoyang);
        cmbGoyang.setBounds(552, 2026, 60, 23);

        jLabel349.setForeground(new java.awt.Color(0, 0, 0));
        jLabel349.setText("Nyeri Tekan :");
        jLabel349.setName("jLabel349"); // NOI18N
        FormInput.add(jLabel349);
        jLabel349.setBounds(465, 2054, 80, 23);

        cmbNyeriTekan.setBackground(new java.awt.Color(245, 253, 240));
        cmbNyeriTekan.setForeground(new java.awt.Color(0, 0, 0));
        cmbNyeriTekan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbNyeriTekan.setLightWeightPopupEnabled(false);
        cmbNyeriTekan.setName("cmbNyeriTekan"); // NOI18N
        FormInput.add(cmbNyeriTekan);
        cmbNyeriTekan.setBounds(552, 2054, 60, 23);

        jLabel350.setForeground(new java.awt.Color(0, 0, 0));
        jLabel350.setText("VT Pembukaan :");
        jLabel350.setName("jLabel350"); // NOI18N
        FormInput.add(jLabel350);
        jLabel350.setBounds(612, 2026, 100, 23);

        TvtPembukaan.setForeground(new java.awt.Color(0, 0, 0));
        TvtPembukaan.setName("TvtPembukaan"); // NOI18N
        TvtPembukaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TvtPembukaanKeyPressed(evt);
            }
        });
        FormInput.add(TvtPembukaan);
        TvtPembukaan.setBounds(717, 2026, 235, 23);

        jLabel351.setForeground(new java.awt.Color(0, 0, 0));
        jLabel351.setText("VT Nyeri Goyang :");
        jLabel351.setName("jLabel351"); // NOI18N
        FormInput.add(jLabel351);
        jLabel351.setBounds(612, 2054, 100, 23);

        cmbVtNyeri.setBackground(new java.awt.Color(245, 253, 240));
        cmbVtNyeri.setForeground(new java.awt.Color(0, 0, 0));
        cmbVtNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbVtNyeri.setLightWeightPopupEnabled(false);
        cmbVtNyeri.setName("cmbVtNyeri"); // NOI18N
        FormInput.add(cmbVtNyeri);
        cmbVtNyeri.setBounds(717, 2054, 60, 23);

        jLabel225.setForeground(new java.awt.Color(0, 0, 0));
        jLabel225.setText("TFU :");
        jLabel225.setName("jLabel225"); // NOI18N
        FormInput.add(jLabel225);
        jLabel225.setBounds(0, 2082, 120, 23);

        Ttfu.setForeground(new java.awt.Color(0, 0, 0));
        Ttfu.setName("Ttfu"); // NOI18N
        Ttfu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtfuKeyPressed(evt);
            }
        });
        FormInput.add(Ttfu);
        Ttfu.setBounds(125, 2082, 70, 23);

        jLabel226.setForeground(new java.awt.Color(0, 0, 0));
        jLabel226.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel226.setText("Cm.         His/Kontraksi :");
        jLabel226.setName("jLabel226"); // NOI18N
        FormInput.add(jLabel226);
        jLabel226.setBounds(204, 2082, 120, 23);

        jLabel352.setForeground(new java.awt.Color(0, 0, 0));
        jLabel352.setText("Taksiran Berat Janin :");
        jLabel352.setName("jLabel352"); // NOI18N
        FormInput.add(jLabel352);
        jLabel352.setBounds(0, 2110, 120, 23);

        Ttaksiran.setForeground(new java.awt.Color(0, 0, 0));
        Ttaksiran.setName("Ttaksiran"); // NOI18N
        Ttaksiran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtaksiranKeyPressed(evt);
            }
        });
        FormInput.add(Ttaksiran);
        Ttaksiran.setBounds(125, 2110, 70, 23);

        jLabel353.setForeground(new java.awt.Color(0, 0, 0));
        jLabel353.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel353.setText("gram       Durasi :");
        jLabel353.setName("jLabel353"); // NOI18N
        FormInput.add(jLabel353);
        jLabel353.setBounds(204, 2110, 86, 23);

        ThisKontraksi.setForeground(new java.awt.Color(0, 0, 0));
        ThisKontraksi.setName("ThisKontraksi"); // NOI18N
        ThisKontraksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThisKontraksiKeyPressed(evt);
            }
        });
        FormInput.add(ThisKontraksi);
        ThisKontraksi.setBounds(325, 2082, 70, 23);

        jLabel354.setForeground(new java.awt.Color(0, 0, 0));
        jLabel354.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel354.setText("x / 10 menit");
        jLabel354.setName("jLabel354"); // NOI18N
        FormInput.add(jLabel354);
        jLabel354.setBounds(400, 2082, 65, 23);

        cmbHis.setBackground(new java.awt.Color(245, 253, 240));
        cmbHis.setForeground(new java.awt.Color(0, 0, 0));
        cmbHis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Teratur", "Tidak Teratur", "Terus Menerus" }));
        cmbHis.setLightWeightPopupEnabled(false);
        cmbHis.setName("cmbHis"); // NOI18N
        FormInput.add(cmbHis);
        cmbHis.setBounds(470, 2082, 105, 23);

        Tdurasi.setForeground(new java.awt.Color(0, 0, 0));
        Tdurasi.setName("Tdurasi"); // NOI18N
        Tdurasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdurasiKeyPressed(evt);
            }
        });
        FormInput.add(Tdurasi);
        Tdurasi.setBounds(293, 2110, 70, 23);

        jLabel355.setForeground(new java.awt.Color(0, 0, 0));
        jLabel355.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel355.setText("detik");
        jLabel355.setName("jLabel355"); // NOI18N
        FormInput.add(jLabel355);
        jLabel355.setBounds(370, 2110, 40, 23);

        cmbDurasi.setBackground(new java.awt.Color(245, 253, 240));
        cmbDurasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbDurasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kuat", "Sedang", "Lemah" }));
        cmbDurasi.setLightWeightPopupEnabled(false);
        cmbDurasi.setName("cmbDurasi"); // NOI18N
        FormInput.add(cmbDurasi);
        cmbDurasi.setBounds(410, 2110, 70, 23);

        jLabel356.setForeground(new java.awt.Color(0, 0, 0));
        jLabel356.setText("Auskultasi : DJJ");
        jLabel356.setName("jLabel356"); // NOI18N
        FormInput.add(jLabel356);
        jLabel356.setBounds(480, 2110, 90, 23);

        Tauskultasi.setForeground(new java.awt.Color(0, 0, 0));
        Tauskultasi.setName("Tauskultasi"); // NOI18N
        Tauskultasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TauskultasiKeyPressed(evt);
            }
        });
        FormInput.add(Tauskultasi);
        Tauskultasi.setBounds(580, 2110, 70, 23);

        jLabel357.setForeground(new java.awt.Color(0, 0, 0));
        jLabel357.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel357.setText("x / menit");
        jLabel357.setName("jLabel357"); // NOI18N
        FormInput.add(jLabel357);
        jLabel357.setBounds(655, 2110, 60, 23);

        jLabel358.setForeground(new java.awt.Color(0, 0, 0));
        jLabel358.setText("Pemeriksaan Genitalia :");
        jLabel358.setName("jLabel358"); // NOI18N
        FormInput.add(jLabel358);
        jLabel358.setBounds(0, 2138, 140, 23);

        chkBersih.setBackground(new java.awt.Color(255, 255, 250));
        chkBersih.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBersih.setForeground(new java.awt.Color(0, 0, 0));
        chkBersih.setText("Bersih");
        chkBersih.setBorderPainted(true);
        chkBersih.setBorderPaintedFlat(true);
        chkBersih.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBersih.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBersih.setName("chkBersih"); // NOI18N
        chkBersih.setOpaque(false);
        chkBersih.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBersih);
        chkBersih.setBounds(147, 2138, 60, 23);

        chkOedema.setBackground(new java.awt.Color(255, 255, 250));
        chkOedema.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkOedema.setForeground(new java.awt.Color(0, 0, 0));
        chkOedema.setText("Oedema");
        chkOedema.setBorderPainted(true);
        chkOedema.setBorderPaintedFlat(true);
        chkOedema.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkOedema.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkOedema.setName("chkOedema"); // NOI18N
        chkOedema.setOpaque(false);
        chkOedema.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkOedema);
        chkOedema.setBounds(215, 2138, 70, 23);

        chkRuftur.setBackground(new java.awt.Color(255, 255, 250));
        chkRuftur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRuftur.setForeground(new java.awt.Color(0, 0, 0));
        chkRuftur.setText("Ruftur");
        chkRuftur.setBorderPainted(true);
        chkRuftur.setBorderPaintedFlat(true);
        chkRuftur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRuftur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRuftur.setName("chkRuftur"); // NOI18N
        chkRuftur.setOpaque(false);
        chkRuftur.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkRuftur);
        chkRuftur.setBounds(294, 2138, 60, 23);

        chkCandi.setBackground(new java.awt.Color(255, 255, 250));
        chkCandi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCandi.setForeground(new java.awt.Color(0, 0, 0));
        chkCandi.setText("Candiloma");
        chkCandi.setBorderPainted(true);
        chkCandi.setBorderPaintedFlat(true);
        chkCandi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCandi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCandi.setName("chkCandi"); // NOI18N
        chkCandi.setOpaque(false);
        chkCandi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCandi);
        chkCandi.setBounds(365, 2138, 80, 23);

        chkLainPemeriksaan.setBackground(new java.awt.Color(255, 255, 250));
        chkLainPemeriksaan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        chkLainPemeriksaan.setText("Lainnya");
        chkLainPemeriksaan.setBorderPainted(true);
        chkLainPemeriksaan.setBorderPaintedFlat(true);
        chkLainPemeriksaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainPemeriksaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainPemeriksaan.setName("chkLainPemeriksaan"); // NOI18N
        chkLainPemeriksaan.setOpaque(false);
        chkLainPemeriksaan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainPemeriksaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainPemeriksaanActionPerformed(evt);
            }
        });
        FormInput.add(chkLainPemeriksaan);
        chkLainPemeriksaan.setBounds(455, 2138, 70, 23);

        TlainPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        TlainPemeriksaan.setName("TlainPemeriksaan"); // NOI18N
        TlainPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainPemeriksaanKeyPressed(evt);
            }
        });
        FormInput.add(TlainPemeriksaan);
        TlainPemeriksaan.setBounds(525, 2138, 330, 23);

        jLabel359.setForeground(new java.awt.Color(0, 0, 0));
        jLabel359.setText("Periksa Dalam (Obstetri) :");
        jLabel359.setName("jLabel359"); // NOI18N
        FormInput.add(jLabel359);
        jLabel359.setBounds(0, 2166, 140, 23);

        TperiksaDalam.setForeground(new java.awt.Color(0, 0, 0));
        TperiksaDalam.setName("TperiksaDalam"); // NOI18N
        TperiksaDalam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TperiksaDalamKeyPressed(evt);
            }
        });
        FormInput.add(TperiksaDalam);
        TperiksaDalam.setBounds(145, 2166, 805, 23);

        jLabel360.setForeground(new java.awt.Color(0, 0, 0));
        jLabel360.setText("Inspekulo :");
        jLabel360.setName("jLabel360"); // NOI18N
        FormInput.add(jLabel360);
        jLabel360.setBounds(0, 2194, 120, 23);

        cmbInspekulo.setBackground(new java.awt.Color(245, 253, 240));
        cmbInspekulo.setForeground(new java.awt.Color(0, 0, 0));
        cmbInspekulo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbInspekulo.setLightWeightPopupEnabled(false);
        cmbInspekulo.setName("cmbInspekulo"); // NOI18N
        cmbInspekulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInspekuloActionPerformed(evt);
            }
        });
        FormInput.add(cmbInspekulo);
        cmbInspekulo.setBounds(125, 2194, 60, 23);

        jLabel361.setForeground(new java.awt.Color(0, 0, 0));
        jLabel361.setText("Hasil :");
        jLabel361.setName("jLabel361"); // NOI18N
        FormInput.add(jLabel361);
        jLabel361.setBounds(185, 2194, 50, 23);

        ThasilInspekulo.setForeground(new java.awt.Color(0, 0, 0));
        ThasilInspekulo.setName("ThasilInspekulo"); // NOI18N
        ThasilInspekulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThasilInspekuloKeyPressed(evt);
            }
        });
        FormInput.add(ThasilInspekulo);
        ThasilInspekulo.setBounds(240, 2194, 710, 23);

        jLabel362.setForeground(new java.awt.Color(0, 0, 0));
        jLabel362.setText("Diagnosis Sementara :");
        jLabel362.setName("jLabel362"); // NOI18N
        FormInput.add(jLabel362);
        jLabel362.setBounds(0, 2222, 140, 23);

        Tdiagnosis.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosis.setName("Tdiagnosis"); // NOI18N
        Tdiagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosisKeyPressed(evt);
            }
        });
        FormInput.add(Tdiagnosis);
        Tdiagnosis.setBounds(145, 2222, 680, 23);

        jLabel363.setForeground(new java.awt.Color(0, 0, 0));
        jLabel363.setText("ICD 10 :");
        jLabel363.setName("jLabel363"); // NOI18N
        FormInput.add(jLabel363);
        jLabel363.setBounds(825, 2222, 50, 23);

        Ticd.setForeground(new java.awt.Color(0, 0, 0));
        Ticd.setName("Ticd"); // NOI18N
        Ticd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TicdKeyPressed(evt);
            }
        });
        FormInput.add(Ticd);
        Ticd.setBounds(880, 2222, 70, 23);

        jLabel364.setForeground(new java.awt.Color(0, 0, 0));
        jLabel364.setText("Planning :");
        jLabel364.setName("jLabel364"); // NOI18N
        FormInput.add(jLabel364);
        jLabel364.setBounds(0, 2250, 120, 23);

        scrollPane10.setName("scrollPane10"); // NOI18N

        Tplaning.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tplaning.setColumns(20);
        Tplaning.setRows(5);
        Tplaning.setName("Tplaning"); // NOI18N
        Tplaning.setPreferredSize(new java.awt.Dimension(162, 2000));
        scrollPane10.setViewportView(Tplaning);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(125, 2250, 825, 150);

        jLabel319.setForeground(new java.awt.Color(0, 0, 0));
        jLabel319.setText("Tahun Partus :");
        jLabel319.setName("jLabel319"); // NOI18N
        FormInput.add(jLabel319);
        jLabel319.setBounds(0, 1430, 120, 23);

        TthnPartus.setForeground(new java.awt.Color(0, 0, 0));
        TthnPartus.setName("TthnPartus"); // NOI18N
        TthnPartus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TthnPartusKeyPressed(evt);
            }
        });
        FormInput.add(TthnPartus);
        TthnPartus.setBounds(125, 1430, 60, 23);

        jLabel323.setForeground(new java.awt.Color(0, 0, 0));
        jLabel323.setText("Penolong Persalinan :");
        jLabel323.setName("jLabel323"); // NOI18N
        FormInput.add(jLabel323);
        jLabel323.setBounds(400, 1430, 120, 23);

        Tpenolong.setForeground(new java.awt.Color(0, 0, 0));
        Tpenolong.setName("Tpenolong"); // NOI18N
        Tpenolong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpenolongKeyPressed(evt);
            }
        });
        FormInput.add(Tpenolong);
        Tpenolong.setBounds(525, 1430, 220, 23);

        jLabel320.setForeground(new java.awt.Color(0, 0, 0));
        jLabel320.setText("Tempat Partus :");
        jLabel320.setName("jLabel320"); // NOI18N
        FormInput.add(jLabel320);
        jLabel320.setBounds(0, 1458, 120, 23);

        TtempatPartus.setForeground(new java.awt.Color(0, 0, 0));
        TtempatPartus.setName("TtempatPartus"); // NOI18N
        TtempatPartus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtempatPartusKeyPressed(evt);
            }
        });
        FormInput.add(TtempatPartus);
        TtempatPartus.setBounds(125, 1458, 270, 23);

        jLabel324.setForeground(new java.awt.Color(0, 0, 0));
        jLabel324.setText("Penyulit :");
        jLabel324.setName("jLabel324"); // NOI18N
        FormInput.add(jLabel324);
        jLabel324.setBounds(400, 1458, 120, 23);

        Tpenyulit.setForeground(new java.awt.Color(0, 0, 0));
        Tpenyulit.setName("Tpenyulit"); // NOI18N
        Tpenyulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpenyulitKeyPressed(evt);
            }
        });
        FormInput.add(Tpenyulit);
        Tpenyulit.setBounds(525, 1458, 220, 23);

        jLabel321.setForeground(new java.awt.Color(0, 0, 0));
        jLabel321.setText("Umur Hamil :");
        jLabel321.setName("jLabel321"); // NOI18N
        FormInput.add(jLabel321);
        jLabel321.setBounds(0, 1486, 120, 23);

        TumurHamil.setForeground(new java.awt.Color(0, 0, 0));
        TumurHamil.setName("TumurHamil"); // NOI18N
        TumurHamil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurHamilKeyPressed(evt);
            }
        });
        FormInput.add(TumurHamil);
        TumurHamil.setBounds(125, 1486, 90, 23);

        jLabel325.setForeground(new java.awt.Color(0, 0, 0));
        jLabel325.setText("Jenis Kelamin :");
        jLabel325.setName("jLabel325"); // NOI18N
        FormInput.add(jLabel325);
        jLabel325.setBounds(400, 1486, 120, 23);

        cmbJenkel.setBackground(new java.awt.Color(245, 253, 240));
        cmbJenkel.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenkel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Laki-laki", "Perempuan" }));
        cmbJenkel.setLightWeightPopupEnabled(false);
        cmbJenkel.setName("cmbJenkel"); // NOI18N
        FormInput.add(cmbJenkel);
        cmbJenkel.setBounds(525, 1486, 90, 23);

        jLabel322.setForeground(new java.awt.Color(0, 0, 0));
        jLabel322.setText("Jenis Persalinan :");
        jLabel322.setName("jLabel322"); // NOI18N
        FormInput.add(jLabel322);
        jLabel322.setBounds(0, 1514, 120, 23);

        TjnsPersalinan.setForeground(new java.awt.Color(0, 0, 0));
        TjnsPersalinan.setName("TjnsPersalinan"); // NOI18N
        TjnsPersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjnsPersalinanKeyPressed(evt);
            }
        });
        FormInput.add(TjnsPersalinan);
        TjnsPersalinan.setBounds(125, 1514, 270, 23);

        jLabel326.setForeground(new java.awt.Color(0, 0, 0));
        jLabel326.setText("Berat Lahir :");
        jLabel326.setName("jLabel326"); // NOI18N
        FormInput.add(jLabel326);
        jLabel326.setBounds(400, 1514, 120, 23);

        TbrtLahir.setForeground(new java.awt.Color(0, 0, 0));
        TbrtLahir.setName("TbrtLahir"); // NOI18N
        TbrtLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbrtLahirKeyPressed(evt);
            }
        });
        FormInput.add(TbrtLahir);
        TbrtLahir.setBounds(525, 1514, 90, 23);

        jLabel327.setForeground(new java.awt.Color(0, 0, 0));
        jLabel327.setText("Keadaan Anak Sekarang :");
        jLabel327.setName("jLabel327"); // NOI18N
        FormInput.add(jLabel327);
        jLabel327.setBounds(745, 1430, 150, 23);

        scrollPane9.setName("scrollPane9"); // NOI18N

        TkeadaanAnak.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TkeadaanAnak.setColumns(20);
        TkeadaanAnak.setRows(5);
        TkeadaanAnak.setName("TkeadaanAnak"); // NOI18N
        TkeadaanAnak.setPreferredSize(new java.awt.Dimension(162, 2000));
        TkeadaanAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeadaanAnakKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(TkeadaanAnak);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(760, 1458, 285, 75);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        panelTombol.setName("panelTombol"); // NOI18N
        panelTombol.setPreferredSize(new java.awt.Dimension(44, 54));
        panelTombol.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelTombol.add(BtnSimpan);

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
        panelTombol.add(BtnBatal);

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
        panelTombol.add(BtnHapus);

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
        panelTombol.add(BtnEdit);

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
        panelTombol.add(BtnPrint);

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
        panelTombol.add(BtnKeluar);

        BtnHalaman.setForeground(new java.awt.Color(0, 0, 0));
        BtnHalaman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnHalaman.setMnemonic('H');
        BtnHalaman.setText("Halaman 2");
        BtnHalaman.setToolTipText("Alt+H");
        BtnHalaman.setName("BtnHalaman"); // NOI18N
        BtnHalaman.setPreferredSize(new java.awt.Dimension(120, 30));
        BtnHalaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHalamanActionPerformed(evt);
            }
        });
        panelTombol.add(BtnHalaman);

        internalFrame2.add(panelTombol, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Input Asesmen hal. 1", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbAsesmen.setAutoCreateRowSorter(true);
        tbAsesmen.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAsesmen.setName("tbAsesmen"); // NOI18N
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

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
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

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-08-2025" }));
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
        BtnCari.setMnemonic('3');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+3");
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

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 23));
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
        panelGlass9.add(BtnAll);

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

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setMnemonic('H');
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Alt+H");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnHapus1);

        BtnPrint1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setText("Cetak");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnPrint1);

        BtnHalaman1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHalaman1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnHalaman1.setMnemonic('H');
        BtnHalaman1.setText("Halaman 2");
        BtnHalaman1.setToolTipText("Alt+H");
        BtnHalaman1.setName("BtnHalaman1"); // NOI18N
        BtnHalaman1.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnHalaman1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHalaman1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnHalaman1);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnKeluar1);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Asesmen hal. 1", internalFrame3);

        internalFrame4.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll19.setName("Scroll19"); // NOI18N
        Scroll19.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll19.setViewportView(LoadHTML1);

        internalFrame4.add(Scroll19, java.awt.BorderLayout.CENTER);

        panelGlass2.setName("panelGlass2"); // NOI18N
        panelGlass2.setPreferredSize(new java.awt.Dimension(44, 47));
        panelGlass2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        BtnHalaman2.setForeground(new java.awt.Color(0, 0, 0));
        BtnHalaman2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnHalaman2.setMnemonic('H');
        BtnHalaman2.setText("Halaman 2");
        BtnHalaman2.setToolTipText("Alt+H");
        BtnHalaman2.setName("BtnHalaman2"); // NOI18N
        BtnHalaman2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHalaman2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHalaman2ActionPerformed(evt);
            }
        });
        panelGlass2.add(BtnHalaman2);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('6');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+6");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        panelGlass2.add(BtnCari1);

        BtnKeluar2.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setMnemonic('K');
        BtnKeluar2.setText("Keluar");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        panelGlass2.add(BtnKeluar2);

        internalFrame4.add(panelGlass2, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Preview Asesmen Awal Kebidanan (hal. 1 & 2)", internalFrame4);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            cekData();
            if (Sequel.menyimpantf("asesmen_awal_kebidanan1", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?", "No.Rawat", 195, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                        TnamaSuami.getText(), TumurSuami.getText(), TpekerjaanSuami.getText(), TalamatSuami.getText(), cmbAgamaSuami.getSelectedItem().toString(), TalasanMskRS.getText(),
                        Ttd.getText(), Tnadi.getText(), Trespi.getText(), Tsuhu.getText(), Tkesadaran.getText(), Tsaturasi.getText(), sendiri, rujukan, cmbJnsRujukan.getSelectedItem().toString(),
                        TketRujukan.getText(), pkm, TketPkm.getText(), spog, rsLain, TketRsLain.getText(), Tgr.getText(), Tpr.getText(), Ta.getText(), Thamil.getText(), Tgpapah.getText(),
                        Tdengan.getText(), cmbPerut.getSelectedItem().toString(), cmbKeluhanPerut.getSelectedItem().toString(), Valid.SetTgl(TtglPerut.getSelectedItem() + ""),
                        cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), cmbKeluar.getSelectedItem().toString(), cmbKeluhanKeluar.getSelectedItem().toString(),
                        Valid.SetTgl(TtglKeluar.getSelectedItem() + ""), cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem(), cmbDarah.getSelectedItem().toString(),
                        cmbKeluhanDarah.getSelectedItem().toString(), cmbJnsDarah.getSelectedItem().toString(), Valid.SetTgl(TtglDarah.getSelectedItem() + ""), cmbJam4.getSelectedItem() + ":" + cmbMnt4.getSelectedItem() + ":" + cmbDtk4.getSelectedItem(),
                        cmbKeluarAir.getSelectedItem().toString(), cmbKeluhanKeluarAir.getSelectedItem().toString(), cmbJnsKeluarAir.getSelectedItem().toString(), Valid.SetTgl(TtglKeluarAir.getSelectedItem() + ""),
                        cmbJam5.getSelectedItem() + ":" + cmbMnt5.getSelectedItem() + ":" + cmbDtk5.getSelectedItem(), cmbPergerakan.getSelectedItem().toString(), Tpergerakan.getText(),
                        cmbPusing.getSelectedItem().toString(), Valid.SetTgl(TtglPusing.getSelectedItem() + ""), cmbJam6.getSelectedItem() + ":" + cmbMnt6.getSelectedItem() + ":" + cmbDtk6.getSelectedItem(),
                        cmbNyeriUlu.getSelectedItem().toString(), Valid.SetTgl(TtglNyeriUlu.getSelectedItem() + ""), cmbJam7.getSelectedItem() + ":" + cmbMnt7.getSelectedItem() + ":" + cmbDtk7.getSelectedItem(),
                        cmbPandangan.getSelectedItem().toString(), Valid.SetTgl(TtglPandangan.getSelectedItem() + ""), cmbJam8.getSelectedItem() + ":" + cmbMnt8.getSelectedItem() + ":" + cmbDtk8.getSelectedItem(),
                        cmbOdema.getSelectedItem().toString(), Valid.SetTgl(TtglOdema.getSelectedItem() + ""), cmbOdemaDi.getSelectedItem().toString(), cmbMual.getSelectedItem().toString(),
                        Valid.SetTgl(TtglMual.getSelectedItem() + ""), cmbJam9.getSelectedItem() + ":" + cmbMnt9.getSelectedItem() + ":" + cmbDtk9.getSelectedItem(), cmbMuntah.getSelectedItem().toString(),
                        Valid.SetTgl(TtglMuntah.getSelectedItem() + ""), cmbJam10.getSelectedItem() + ":" + cmbMnt10.getSelectedItem() + ":" + cmbDtk10.getSelectedItem(), cmbBatuk.getSelectedItem().toString(),
                        Valid.SetTgl(TtglBatuk.getSelectedItem() + ""), cmbJam11.getSelectedItem() + ":" + cmbMnt11.getSelectedItem() + ":" + cmbDtk11.getSelectedItem(), cmbPilek.getSelectedItem().toString(),
                        Valid.SetTgl(TtglPilek.getSelectedItem() + ""), cmbJam12.getSelectedItem() + ":" + cmbMnt12.getSelectedItem() + ":" + cmbDtk12.getSelectedItem(), cmbDemam.getSelectedItem().toString(),
                        Valid.SetTgl(TtglDemam.getSelectedItem() + ""), cmbJam13.getSelectedItem() + ":" + cmbMnt13.getSelectedItem() + ":" + cmbDtk13.getSelectedItem(), cmbRiwPerjalanan.getSelectedItem().toString(),
                        TketRiwPerjalanan.getText(), cmbVaksin.getSelectedItem().toString(), TketVaksin.getText(), cmbPeriksa.getSelectedItem().toString(), ThasilInspekulo.getText(), cmbAnc.getSelectedItem().toString(),
                        cmbAncDi.getSelectedItem().toString(), TjlhAnc.getText(), TnmDokter1.getText(), TjlhDokter1.getText(), TnmDokter2.getText(), TjlhDokter2.getText(), TnmDokter3.getText(),
                        TjlhDokter3.getText(), Thpht.getText(), Thpl.getText(), Tuk.getText(), TbbSebelum.getText(), TbbTerakhir.getText(), Ttbi.getText(), TumurPertama.getText(), TlamaHaid.getText(),
                        Tberapa.getText(), cmbKeluhanWaktu.getSelectedItem().toString(), dismen, spoting, menor, metro, lainKeluhanHaid, TkeluhanLain.getText(), cmbRiwPenDahulu.getSelectedItem().toString(),
                        hipertensiDahulu, dmDahulu, jantungDahulu, asmaDahulu, lainyaDahulu, TlainDahulu.getText(), cmbRiwPenKeluarga.getSelectedItem().toString(), hipertensiKeluarga, dmKeluarga,
                        jantungKeluarga, asmaKeluarga, lainyaKeluarga, TlainKeluarga.getText(), cmbRiwGinekologi.getSelectedItem().toString(), TriwGinekologi.getText(), pil, TlamaPil.getText(),
                        cmbSatLamaPil.getSelectedItem().toString(), suntik1, TlamaSuntik1.getText(), cmbSatLamaSuntik1.getSelectedItem().toString(), suntik3, TlamaSuntik3.getText(), cmbSatLamaSuntik3.getSelectedItem().toString(),
                        implan, TlamaImplan.getText(), cmbSatLamaImplan.getSelectedItem().toString(), iud, TlamaIud.getText(), cmbSatLamaIud.getSelectedItem().toString(), tidakKb,
                        cmbSttsPerkawinan.getSelectedItem().toString(), istriKawin, suamiKawin, cmbJlhIstri.getSelectedItem().toString(), cmbJlhSuami.getSelectedItem().toString(), TusiaPertama.getText(),
                        TusiaPerkawinan.getText(), TklgTerdekat.getText(), ThubKeluarga.getText(), orangTua, suami, anak, tinggalSendiri, cmbCuriga.getSelectedItem().toString(), cmbKegiatan.getSelectedItem().toString(),
                        cmbSttsEmosional.getSelectedItem().toString(), asuransi, jaminan, biayaSendiri, lainStatusEkonomi, TsttsLainEkonomi.getText(), Tleo1.getText(), Tleo2.getText(),
                        Tleo3.getText(), Tleo4.getText(), cmbBandle.getSelectedItem().toString(), cmbPerutTegang.getSelectedItem().toString(), Tpalpasi.getText(), cmbTeraba.getSelectedItem().toString(),
                        Tsebesar.getText(), cmbGoyang.getSelectedItem().toString(), cmbNyeriTekan.getSelectedItem().toString(), TvtPembukaan.getText(), cmbVtNyeri.getSelectedItem().toString(), Ttfu.getText(),
                        Ttaksiran.getText(), ThisKontraksi.getText(), cmbHis.getSelectedItem().toString(), Tdurasi.getText(), cmbDurasi.getSelectedItem().toString(), Tauskultasi.getText(), bersih, oedema,
                        ruftur, candiloma, lainPemeriksaanGeni, TlainPemeriksaan.getText(), TperiksaDalam.getText(), cmbInspekulo.getSelectedItem().toString(), ThasilInspekulo.getText(), Tdiagnosis.getText(),
                        Ticd.getText(), Tplaning.getText(), Sequel.cariIsi("select now()")
                    }) == true) {

                if (tbRiwayat.getRowCount() != 0) {
                    for (i = 0; i < tbRiwayat.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("riwayat_kehamilan_asesmen_awal_kebidanan",
                                "'" + tbRiwayat.getValueAt(i, 0).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 1).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 2).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 3).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 4).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 5).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 6).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 7).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 8).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 9).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 10).toString() + "'", "Data Riwayat Kehamilan");
                    }
                }

                TCari.setText(TNoRw.getText());
                emptTeks();
                TabRawat.setSelectedIndex(1);
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
            hapus();
//            if (akses.getadmin() == true) {
//                hapus();
//            } else {
//                if (Sequel.cariIsi("select nip_bidan_dp from asesmen_awal_kebidanan2 where no_rawat='" + TNoRw.getText() + "'").equals(akses.getkode())) {
//                    ganti();
//                } else {
//                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh " + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString() + ", karena beliau yang menyimpan datanya..!!");
//                }
//            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel..!!");
            emptTeks();
            TabRawat.setSelectedIndex(1);
            tampil();
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            if (tbAsesmen.getSelectedRow() > -1) {
                ganti();
                
//                if (akses.getadmin() == true) {
//                    ganti();
//                } else {
//                    if (Sequel.cariIsi("select nip_bidan_dp from asesmen_awal_kebidanan2 where no_rawat='" + TNoRw.getText() + "'").equals(akses.getkode())) {
//                        ganti();
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh " + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString() + ", karena beliau yang menyimpan datanya..!!");
//                    }
//                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel..!!");
                emptTeks();
                TabRawat.setSelectedIndex(1);
                tampil();
            }
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
        dispose();
        halaman2.Tutup();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbAsesmen.getSelectedRow() > -1 || Sequel.cariInteger("select count(-1) from asesmen_awal_kebidanan1 where no_rawat='" + TNoRw.getText() + "'") > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {
                psLaprm = koneksi.prepareStatement("SELECT ak1.*, ak2.*, pg1.nama nmBidan, pg2.nama nmDokter, pg3.nama nmBidanDp, p.nm_pasien, "
                        + "p.no_rkm_medis, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', ',kb.nm_kab) almtPasien, "
                        + "concat(rp.umurdaftar,' ',rp.sttsumur) umurPasien, p.pekerjaan, p.agama FROM asesmen_awal_kebidanan1 ak1 "
                        + "inner join reg_periksa rp on rp.no_rawat=ak1.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join kelurahan kl on kl.kd_kel=p.kd_kel inner join kecamatan kc on kc.kd_kec=p.kd_kec inner join kabupaten kb on kb.kd_kab=p.kd_kab "
                        + "left join asesmen_awal_kebidanan2 ak2 on ak1.no_rawat=ak2.no_rawat left join pegawai pg1 on pg1.nik=ak2.nip_bidan "
                        + "left join pegawai pg2 on pg2.nik=ak2.nip_dokter left join pegawai pg3 on pg3.nik=ak2.nip_bidan_dp where ak1.no_rawat='" + TNoRw.getText() + "'");
                try {
                    rsLaprm = psLaprm.executeQuery();
                    while (rsLaprm.next()) {
                        Map<String, Object> param = new HashMap<>();
                        param.put("namars", akses.getnamars());
                        param.put("logo", Sequel.cariGambar("select logo from setting"));                        
                        param.put("norm", rsLaprm.getString("no_rkm_medis"));
                        param.put("nmpasien", rsLaprm.getString("nm_pasien"));
                        param.put("tgllahir", rsLaprm.getString("tglLahir"));                        
                        param.put("ruangRwt", rsLaprm.getString("ruang_rawat"));
                        param.put("tglAses", Valid.SetTglINDONESIA(rsLaprm.getString("tgl_asesmen")));
                        param.put("jamAses", rsLaprm.getString("jam_asesmen").substring(0, 5) + " Wita");

                        param.put("nmPasien", rsLaprm.getString("nm_pasien"));
                        param.put("umurPasien", rsLaprm.getString("umurPasien"));
                        param.put("pekerPasien", rsLaprm.getString("pekerjaan"));
                        param.put("almtPasien", rsLaprm.getString("almtPasien"));
                        param.put("agmaPasien", rsLaprm.getString("agama"));

                        if (rsLaprm.getString("nm_suami").equals("")) {
                            param.put("nmSuami", "..............");
                        } else {
                            param.put("nmSuami", rsLaprm.getString("nm_suami"));
                        }

                        if (rsLaprm.getString("umur_suami").equals("")) {
                            param.put("umurSuami", "...... tahun");
                        } else {
                            param.put("umurSuami", rsLaprm.getString("umur_suami") + " tahun");
                        }

                        if (rsLaprm.getString("pekerjaan_suami").equals("")) {
                            param.put("pekerSuami", "..............");
                        } else {
                            param.put("pekerSuami", rsLaprm.getString("pekerjaan_suami"));
                        }

                        if (rsLaprm.getString("alamat_suami").equals("")) {
                            param.put("almtSuami", "..............");
                        } else {
                            param.put("almtSuami", rsLaprm.getString("alamat_suami"));
                        }

                        if (rsLaprm.getString("agama_suami").equals("-")) {
                            param.put("agmaSuami", "..............");
                        } else {
                            param.put("agmaSuami", rsLaprm.getString("agama_suami"));
                        }

                        if (rsLaprm.getString("alasan_masuk").equals("")) {
                            param.put("alasanMrs", "..............");
                        } else {
                            param.put("alasanMrs", rsLaprm.getString("alasan_masuk"));
                        }

                        if (rsLaprm.getString("td").equals("")) {
                            param.put("td", ".......... mmHg");
                        } else {
                            param.put("td", rsLaprm.getString("td") + " mmHg");
                        }

                        if (rsLaprm.getString("nadi").equals("")) {
                            param.put("nadi", ".......... x/menit");
                        } else {
                            param.put("nadi", rsLaprm.getString("nadi") + " x/menit");
                        }

                        if (rsLaprm.getString("respirasi").equals("")) {
                            param.put("respi", ".......... x/menit");
                        } else {
                            param.put("respi", rsLaprm.getString("respirasi") + " x/menit");
                        }

                        if (rsLaprm.getString("suhu").equals("")) {
                            param.put("suhu", "....... °C");
                        } else {
                            param.put("suhu", rsLaprm.getString("suhu") + " °C");
                        }
                        
                        if (rsLaprm.getString("kesadaran").equals("")) {
                            param.put("kesadaran", "..............");
                        } else {
                            param.put("kesadaran", rsLaprm.getString("kesadaran"));
                        }

                        if (rsLaprm.getString("saturasi").equals("")) {
                            param.put("saturasi", "....... %");
                        } else {
                            param.put("saturasi", rsLaprm.getString("saturasi") + " %");
                        }

                        if (rsLaprm.getString("cek_sendiri").equals("ya")) {
                            param.put("caraSendiri", "V");
                        } else {
                            param.put("caraSendiri", "");
                        }

                        if (rsLaprm.getString("cek_rujukan").equals("ya")) {
                            param.put("caraRujukan", "V");
                            if (rsLaprm.getString("ket_jns_rujukan").equals("")) {
                                param.put("caraDataRujukan", "Rujukan : " + rsLaprm.getString("jns_rujukan"));
                            } else {
                                param.put("caraDataRujukan", "Rujukan : " + rsLaprm.getString("jns_rujukan") + " " + rsLaprm.getString("ket_jns_rujukan"));
                            }
                        } else {
                            param.put("caraRujukan", "");
                            param.put("caraDataRujukan", "Rujukan");
                        }

                        if (rsLaprm.getString("cek_pkm").equals("ya")) {
                            param.put("caraPkm", "V");
                            if (rsLaprm.getString("ket_pkm").equals("")) {
                                param.put("caraDataPkm", "PKM");
                            } else {
                                param.put("caraDataPkm", "PKM (" + rsLaprm.getString("ket_pkm") + ")");
                            }
                        } else {
                            param.put("caraPkm", "");
                            param.put("caraDataPkm", "PKM");
                        }
                        
                        if (rsLaprm.getString("cek_spog").equals("ya")) {
                            param.put("caraSpog", "V");
                        } else {
                            param.put("caraSpog", "");
                        }

                        if (rsLaprm.getString("cek_rs_lain").equals("ya")) {
                            param.put("caraRsLain", "V");
                            if (rsLaprm.getString("ket_rs_lain").equals("")) {
                                param.put("caraDataRsLain", "RS Lain");
                            } else {
                                param.put("caraDataRsLain", "RS Lain (" + rsLaprm.getString("ket_rs_lain") + ")");
                            }
                        } else {
                            param.put("caraRsLain", "");
                            param.put("caraDataRsLain", "RS Lain");
                        }

                        String gr = "", pr = "", a = "", hamil = "";
                        if (rsLaprm.getString("gr").equals("")) {
                            gr = "Gr ......., ";
                        } else {
                            gr = "Gr " + rsLaprm.getString("gr") + ", ";
                        }
                        
                        if (rsLaprm.getString("pr").equals("")) {
                            pr = "Pr ......., ";
                        } else {
                            pr = "Pr " + rsLaprm.getString("pr") + ", ";
                        }
                        
                        if (rsLaprm.getString("a").equals("")) {
                            a = "A ......., ";
                        } else {
                            a = "A " + rsLaprm.getString("a") + ", ";
                        }

                        if (rsLaprm.getString("hamil").equals("")) {
                            hamil = "Hamil ....... minggu";
                        } else {
                            hamil = "Hamil " + rsLaprm.getString("hamil") + " minggu";
                        }
                        
                        param.put("grpra", gr + pr + a + hamil);
             
                        if (rsLaprm.getString("gpapah").equals("")) {
                            param.put("gpapah", "..............");
                        } else {
                            param.put("gpapah", rsLaprm.getString("gpapah"));
                        }
             
                        if (rsLaprm.getString("dengan").equals("")) {
                            param.put("dengan", "..............");
                        } else {
                            param.put("dengan", rsLaprm.getString("dengan"));
                        }

                        if (rsLaprm.getString("perut").equals("Ya")) {
                            param.put("perut", rsLaprm.getString("perut") + " " + rsLaprm.getString("keluhan_perut") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_perut")) + ", jam " + rsLaprm.getString("jam_perut").substring(0, 5) + " Wita");
                        } else {
                            param.put("perut", rsLaprm.getString("perut"));
                        }
             
                        if (rsLaprm.getString("keluar").equals("Ya")) {
                            param.put("keluar", rsLaprm.getString("keluar") + " " + rsLaprm.getString("keluhan_keluar") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_keluar_lendir")) + ", jam " + rsLaprm.getString("jam_keluar_lendir").substring(0, 5) + " Wita");
                        } else {
                            param.put("keluar", rsLaprm.getString("keluar"));
                        }

                        if (rsLaprm.getString("darah").equals("Ya")) {
                            param.put("darah", rsLaprm.getString("darah") + " " + rsLaprm.getString("keluhan_darah") + " " + rsLaprm.getString("jns_darah") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_darah")) + ", jam " + rsLaprm.getString("jam_darah").substring(0, 5) + " Wita");
                        } else {
                            param.put("darah", rsLaprm.getString("darah"));
                        }
                        
                        if (rsLaprm.getString("keluar_air").equals("Ya")) {
                            param.put("klrAir", rsLaprm.getString("keluar_air") + " " + rsLaprm.getString("keluhan_keluar_air") + " " + rsLaprm.getString("jns_keluar_air") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_keluar_air")) + ", jam " + rsLaprm.getString("jam_keluar_air").substring(0, 5) + " Wita");
                        } else {
                            param.put("klrAir", rsLaprm.getString("keluar_air"));
                        }

                        if (rsLaprm.getString("pergerakan_janin_2jam_terakhir").equals("Ada")) {
                            if (rsLaprm.getString("ket_pergerakan_janin_2jam_terakhir").equals("")) {
                                param.put("pergerakan", rsLaprm.getString("pergerakan_janin_2jam_terakhir"));
                            } else {
                                param.put("pergerakan", rsLaprm.getString("pergerakan_janin_2jam_terakhir") + ", " + rsLaprm.getString("ket_pergerakan_janin_2jam_terakhir") + " X");
                            }
                        } else {
                            param.put("pergerakan", rsLaprm.getString("pergerakan_janin_2jam_terakhir"));
                        }
                        
                        if (rsLaprm.getString("pusing").equals("Ya")) {
                            param.put("pusing", rsLaprm.getString("pusing") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_pusing")) + ", jam " + rsLaprm.getString("jam_pusing").substring(0, 5) + " Wita");
                        } else {
                            param.put("pusing", rsLaprm.getString("pusing"));
                        }

                        if (rsLaprm.getString("nyeri_ulu_hati").equals("Ya")) {
                            param.put("nyeriUlu", rsLaprm.getString("nyeri_ulu_hati") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_nyeri_ulu_hati")) + ", jam " + rsLaprm.getString("jam_nyeri_ulu_hati").substring(0, 5) + " Wita");
                        } else {
                            param.put("nyeriUlu", rsLaprm.getString("nyeri_ulu_hati"));
                        }
                        
                        if (rsLaprm.getString("pandangan_kabur").equals("Ya")) {
                            param.put("pandangan", rsLaprm.getString("pandangan_kabur") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_pandangan_kabur")) + ", jam " + rsLaprm.getString("jam_pandangan_kabur").substring(0, 5) + " Wita");
                        } else {
                            param.put("pandangan", rsLaprm.getString("pandangan_kabur"));
                        }

                        if (rsLaprm.getString("odema").equals("Ya")) {
                            param.put("odemma", rsLaprm.getString("odema") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_odema")) + ", di " + rsLaprm.getString("odema_di"));
                        } else {
                            param.put("odemma", rsLaprm.getString("odema"));
                        }
             
                        if (rsLaprm.getString("mual").equals("Ya")) {
                            param.put("mual", rsLaprm.getString("mual") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_mual")) + ", jam " + rsLaprm.getString("jam_mual").substring(0, 5) + " Wita");
                        } else {
                            param.put("mual", rsLaprm.getString("mual"));
                        }
                        
                        if (rsLaprm.getString("muntah").equals("Ya")) {
                            param.put("muntah", rsLaprm.getString("muntah") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_muntah")) + ", jam " + rsLaprm.getString("jam_muntah").substring(0, 5) + " Wita");
                        } else {
                            param.put("muntah", rsLaprm.getString("muntah"));
                        }
                        
                        if (rsLaprm.getString("batuk").equals("Ya")) {
                            param.put("batuk", rsLaprm.getString("batuk") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_batuk")) + ", jam " + rsLaprm.getString("jam_batuk").substring(0, 5) + " Wita");
                        } else {
                            param.put("batuk", rsLaprm.getString("batuk"));
                        }
                        
                        if (rsLaprm.getString("pilek").equals("Ya")) {
                            param.put("pilek", rsLaprm.getString("pilek") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_pilek")) + ", jam " + rsLaprm.getString("jam_pilek").substring(0, 5) + " Wita");
                        } else {
                            param.put("pilek", rsLaprm.getString("pilek"));
                        }

                        if (rsLaprm.getString("demam").equals("Ya")) {
                            param.put("demam", rsLaprm.getString("demam") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_demam")) + ", jam " + rsLaprm.getString("jam_demam").substring(0, 5) + " Wita");
                        } else {
                            param.put("demam", rsLaprm.getString("demam"));
                        }

                        if (rsLaprm.getString("riw_perjalanan_jauh").equals("Ya")) {
                            if (rsLaprm.getString("ket_riw_perjalanan_jauh").equals("")) {
                                param.put("riwPerjalanan", rsLaprm.getString("riw_perjalanan_jauh"));
                            } else {
                                param.put("riwPerjalanan", rsLaprm.getString("riw_perjalanan_jauh") + ", " + rsLaprm.getString("ket_riw_perjalanan_jauh"));
                            }
                        } else {
                            param.put("riwPerjalanan", rsLaprm.getString("riw_perjalanan_jauh"));
                        }

                        if (rsLaprm.getString("vaksin_covid19").equals("Ya")) {
                            if (rsLaprm.getString("jlh_vaksin_covid19").equals("")) {
                                param.put("vaksin", rsLaprm.getString("vaksin_covid19"));
                            } else {
                                param.put("vaksin", rsLaprm.getString("vaksin_covid19") + ", " + rsLaprm.getString("jlh_vaksin_covid19") + " X");
                            }
                        } else {
                            param.put("vaksin", rsLaprm.getString("vaksin_covid19"));
                        }

                        if (rsLaprm.getString("periksa_ketempat_bidan").equals("Ya")) {
                            if (rsLaprm.getString("hasil_pemeriksaan_bidan").equals("")) {
                                param.put("periksa", rsLaprm.getString("periksa_ketempat_bidan") + ", Hasil / Riwayat Pemeriksaan Bidan : -");
                            } else {
                                param.put("periksa", rsLaprm.getString("periksa_ketempat_bidan") + ", Hasil / Riwayat Pemeriksaan Bidan : " + rsLaprm.getString("hasil_pemeriksaan_bidan"));
                            }
                        } else {
                            param.put("periksa", rsLaprm.getString("periksa_ketempat_bidan"));
                        }
                        
                        if (rsLaprm.getString("ibu_anc").equals("Ya")) {
                            if (rsLaprm.getString("jlh_anc").equals("")) {
                                param.put("ibuAnc", rsLaprm.getString("ibu_anc") + ", di " + rsLaprm.getString("jns_anc"));
                            } else {
                                param.put("ibuAnc", rsLaprm.getString("ibu_anc") + ", di " + rsLaprm.getString("jns_anc") + " : " + rsLaprm.getString("jlh_anc") + " X");
                            }
                        } else {
                            param.put("ibuAnc", rsLaprm.getString("ibu_anc"));
                        }

                        if (!rsLaprm.getString("dengan_dokter1").equals("")) {
                            if (rsLaprm.getString("jlh_dengan_dokter1").equals("")) {
                                param.put("dokter1", rsLaprm.getString("dengan_dokter1"));
                            } else {
                                param.put("dokter1", rsLaprm.getString("dengan_dokter1") + " (" + rsLaprm.getString("jlh_dengan_dokter1") + " X)");
                            }
                        } else {
                            param.put("dokter1", "..............");
                        }
                        
                        if (!rsLaprm.getString("dengan_dokter2").equals("")) {
                            if (rsLaprm.getString("jlh_dengan_dokter2").equals("")) {
                                param.put("dokter2", rsLaprm.getString("dengan_dokter2"));
                            } else {
                                param.put("dokter2", rsLaprm.getString("dengan_dokter2") + " (" + rsLaprm.getString("jlh_dengan_dokter2") + " X)");
                            }
                        } else {
                            param.put("dokter2", "..............");
                        }
                        
                        if (!rsLaprm.getString("dengan_dokter3").equals("")) {
                            if (rsLaprm.getString("jlh_dengan_dokter3").equals("")) {
                                param.put("dokter3", rsLaprm.getString("dengan_dokter3"));
                            } else {
                                param.put("dokter3", rsLaprm.getString("dengan_dokter3") + " (" + rsLaprm.getString("jlh_dengan_dokter3") + " X)");
                            }
                        } else {
                            param.put("dokter3", "..............");
                        }

                        if (rsLaprm.getString("hpht").equals("")) {
                            param.put("hpht", "..............");
                        } else {
                            param.put("hpht", rsLaprm.getString("hpht"));
                        }

                        if (rsLaprm.getString("hpl").equals("")) {
                            param.put("hpl", "..............");
                        } else {
                            param.put("hpl", rsLaprm.getString("hpl"));
                        }

                        if (rsLaprm.getString("uk").equals("")) {
                            param.put("uk", "........ mg");
                        } else {
                            param.put("uk", rsLaprm.getString("uk") + " mg");
                        }

                        if (rsLaprm.getString("bb_sebelum_hamil").equals("")) {
                            param.put("bbSebelum", "........ Kg");
                        } else {
                            param.put("bbSebelum", rsLaprm.getString("bb_sebelum_hamil") + " Kg");
                        }

                        if (rsLaprm.getString("bb_terakhir").equals("")) {
                            param.put("bbTerakhir", "........ Kg");
                        } else {
                            param.put("bbTerakhir", rsLaprm.getString("bb_terakhir") + " Kg");
                        }

                        if (rsLaprm.getString("tbi").equals("")) {
                            param.put("tbi", "........ Cm");
                        } else {
                            param.put("tbi", rsLaprm.getString("tbi") + " Cm");
                        }

                        if (rsLaprm.getString("umur_pertama_haid").equals("")) {
                            param.put("umurPertama", "........ tahun");
                        } else {
                            param.put("umurPertama", rsLaprm.getString("umur_pertama_haid") + " tahun");
                        }

                        if (rsLaprm.getString("lama_haid").equals("")) {
                            param.put("lamaHaid", "........ hari");
                        } else {
                            param.put("lamaHaid", rsLaprm.getString("lama_haid") + " hari");
                        }

                        if (rsLaprm.getString("berapa_kali_ganti_pembalut").equals("")) {
                            param.put("berapa", "........ x / hari");
                        } else {
                            param.put("berapa", rsLaprm.getString("berapa_kali_ganti_pembalut") + " x / hari");
                        }

                        param.put("keluhanWaktu", rsLaprm.getString("keluhan_waktu_haid"));

                        if (rsLaprm.getString("cek_dismen").equals("ya")) {
                            param.put("dismen", "V");
                        } else {
                            param.put("dismen", "");
                        }

                        if (rsLaprm.getString("cek_spoting").equals("ya")) {
                            param.put("spot", "V");
                        } else {
                            param.put("spot", "");
                        }

                        if (rsLaprm.getString("cek_menor").equals("ya")) {
                            param.put("menor", "V");
                        } else {
                            param.put("menor", "");
                        }

                        if (rsLaprm.getString("cek_metro").equals("ya")) {
                            param.put("metro", "V");
                        } else {
                            param.put("metro", "");
                        }

                        if (rsLaprm.getString("cek_lain_keluhan_haid").equals("ya")) {
                            param.put("cekKelLain", "V");
                            if (rsLaprm.getString("ket_lain_keluhan_haid").equals("")) {
                                param.put("dataKelLain", "Lainnya");
                            } else {
                                param.put("dataKelLain", "Lainnya (" + rsLaprm.getString("ket_lain_keluhan_haid") + ")");
                            }
                        } else {
                            param.put("cekKelLain", "");
                            param.put("dataKelLain", "Lainnya");
                        }

                        param.put("riwPenyDahulu", rsLaprm.getString("riw_penyakit_dahulu"));

                        if (rsLaprm.getString("cek_hipertensi_dahulu").equals("ya")) {
                            param.put("cekHiper1", "V");
                        } else {
                            param.put("cekHiper1", "");
                        }
             
                        if (rsLaprm.getString("cek_dm_dahulu").equals("ya")) {
                            param.put("cekDm1", "V");
                        } else {
                            param.put("cekDm1", "");
                        }

                        if (rsLaprm.getString("cek_jantung_dahulu").equals("ya")) {
                            param.put("cekJantung1", "V");
                        } else {
                            param.put("cekJantung1", "");
                        }

                        if (rsLaprm.getString("cek_asma_dahulu").equals("ya")) {
                            param.put("cekAsma1", "V");
                        } else {
                            param.put("cekAsma1", "");
                        }

                        if (rsLaprm.getString("cek_lainya_dahulu").equals("ya")) {
                            param.put("cekLainDahulu1", "V");
                            if (rsLaprm.getString("ket_lain_penyakit_dahulu").equals("")) {
                                param.put("dataLainDahulu1", "Lainnya");
                            } else {
                                param.put("dataLainDahulu1", "Lainnya (" + rsLaprm.getString("ket_lain_penyakit_dahulu") + ")");
                            }
                        } else {
                            param.put("cekLainDahulu1", "");
                            param.put("dataLainDahulu1", "Lainnya");
                        }

                        param.put("riwPenyKlg", rsLaprm.getString("riw_penyakit_keluarga"));
                        
                        if (rsLaprm.getString("cek_hipertensi_keluarga").equals("ya")) {
                            param.put("cekHiper2", "V");
                        } else {
                            param.put("cekHiper2", "");
                        }

                        if (rsLaprm.getString("cek_dm_keluarga").equals("ya")) {
                            param.put("cekDm2", "V");
                        } else {
                            param.put("cekDm2", "");
                        }

                        if (rsLaprm.getString("cek_jantung_keluarga").equals("ya")) {
                            param.put("cekJantung2", "V");
                        } else {
                            param.put("cekJantung2", "");
                        }

                        if (rsLaprm.getString("cek_asma_keluarga").equals("ya")) {
                            param.put("cekAsma2", "V");
                        } else {
                            param.put("cekAsma2", "");
                        }

                        if (rsLaprm.getString("cek_lainya_keluarga").equals("ya")) {
                            param.put("cekLainKlg2", "V");
                            if (rsLaprm.getString("ket_lain_penyakit_keluarga").equals("")) {
                                param.put("dataLainKlg2", "Lainnya");
                            } else {
                                param.put("dataLainKlg2", "Lainnya (" + rsLaprm.getString("ket_lain_penyakit_keluarga") + ")");
                            }
                        } else {
                            param.put("cekLainKlg2", "");
                            param.put("dataLainKlg2", "Lainnya");
                        }

                        if (rsLaprm.getString("riw_ginekologi").equals("Ada")) {
                            if (rsLaprm.getString("ket_ginekologi").equals("")) {
                                param.put("riwGine", rsLaprm.getString("riw_ginekologi"));
                            } else {
                                param.put("riwGine", rsLaprm.getString("riw_ginekologi") + ", " + rsLaprm.getString("ket_ginekologi"));
                            }
                        } else {
                            param.put("riwGine", rsLaprm.getString("riw_ginekologi"));
                        }

                        if (rsLaprm.getString("cek_pil").equals("ya")) {
                            param.put("cekPil", "V");
                            if (rsLaprm.getString("lama_pil").equals("")) {
                                param.put("dataPil", "Pil, Lama : ...... " + rsLaprm.getString("satuan_lama_pil"));
                            } else {
                                param.put("dataPil", "Pil, Lama : " + rsLaprm.getString("lama_pil") + " " + rsLaprm.getString("satuan_lama_pil"));
                            }
                        } else {
                            param.put("cekPil", "");
                            param.put("dataPil", "Pil");
                        }

                        if (rsLaprm.getString("cek_suntik1").equals("ya")) {
                            param.put("cekSuntik1", "V");
                            if (rsLaprm.getString("lama_suntik1").equals("")) {
                                param.put("dataSuntik1", "Suntik 1 Bulan, Lama : ...... " + rsLaprm.getString("satuan_lama_suntik1"));
                            } else {
                                param.put("dataSuntik1", "Suntik 1 Bulan, Lama : " + rsLaprm.getString("lama_suntik1") + " " + rsLaprm.getString("satuan_lama_suntik1"));
                            }
                        } else {
                            param.put("cekSuntik1", "");
                            param.put("dataSuntik1", "Suntik 1 Bulan");
                        }

                        if (rsLaprm.getString("cek_suntik3").equals("ya")) {
                            param.put("cekSuntik3", "V");
                            if (rsLaprm.getString("lama_suntik3").equals("")) {
                                param.put("dataSuntik3", "Suntik 3 Bulan, Lama : ...... " + rsLaprm.getString("satuan_lama_suntik3"));
                            } else {
                                param.put("dataSuntik3", "Suntik 3 Bulan, Lama : " + rsLaprm.getString("lama_suntik3") + " " + rsLaprm.getString("satuan_lama_suntik3"));
                            }
                        } else {
                            param.put("cekSuntik3", "");
                            param.put("dataSuntik3", "Suntik 3 Bulan");
                        }

                        if (rsLaprm.getString("cek_implan").equals("ya")) {
                            param.put("cekImplan", "V");
                            if (rsLaprm.getString("lama_implan").equals("")) {
                                param.put("dataImplan", "Implan, Lama : ...... " + rsLaprm.getString("satuan_lama_implan"));
                            } else {
                                param.put("dataImplan", "Implan, Lama : " + rsLaprm.getString("lama_implan") + " " + rsLaprm.getString("satuan_lama_implan"));
                            }
                        } else {
                            param.put("cekImplan", "");
                            param.put("dataImplan", "Implan");
                        }

                        if (rsLaprm.getString("cek_iud").equals("ya")) {
                            param.put("cekIud", "V");
                            if (rsLaprm.getString("lama_iud").equals("")) {
                                param.put("dataIud", "IUD, Lama : ...... " + rsLaprm.getString("satuan_lama_iud"));
                            } else {
                                param.put("dataIud", "IUD, Lama : " + rsLaprm.getString("lama_iud") + " " + rsLaprm.getString("satuan_lama_iud"));
                            }
                        } else {
                            param.put("cekIud", "");
                            param.put("dataIud", "IUD");
                        }

                        if (rsLaprm.getString("cek_tidak_kb").equals("ya")) {
                            param.put("cektdkPernahKb", "V");
                        } else {
                            param.put("cektdkPernahKb", "");
                        }

                        param.put("sttsPerkawinan", rsLaprm.getString("status_perkawinan"));

                        if (rsLaprm.getString("cek_istri_kawin").equals("ya")) {
                            param.put("cekIstri", "V");
                            if (!rsLaprm.getString("jlh_perkawinan_istri").equals("-")) {
                                param.put("dataIstri", "Istri (" + rsLaprm.getString("jlh_perkawinan_istri") + ")");
                            } else {
                                param.put("dataIstri", "Istri");
                            }
                        } else {
                            param.put("cekIstri", "");
                            param.put("dataIstri", "Istri");
                        }

                        if (rsLaprm.getString("cek_suami_kawin").equals("ya")) {
                            param.put("cekSuami", "V");
                            if (!rsLaprm.getString("jlh_perkawinan_suami").equals("-")) {
                                param.put("dataSuami", " Suami (" + rsLaprm.getString("jlh_perkawinan_suami") + ")");
                            } else {
                                param.put("dataSuami", " Suami");
                            }
                        } else {
                            param.put("cekSuami", "");
                            param.put("dataSuami", " Suami");
                        }

                        if (rsLaprm.getString("usia_pertama_nikah").equals("")) {
                            param.put("usiaPertama", "....... tahun");
                        } else {
                            param.put("usiaPertama", rsLaprm.getString("usia_pertama_nikah") + " tahun");
                        }

                        if (rsLaprm.getString("usia_perkawinan").equals("")) {
                            param.put("usiaPerkawinan", "....... tahun");
                        } else {
                            param.put("usiaPerkawinan", rsLaprm.getString("usia_perkawinan") + " tahun");
                        }

                        if (rsLaprm.getString("keluarga_terdekat").equals("")) {
                            param.put("klgTerdekat", ".............");
                        } else {
                            param.put("klgTerdekat", rsLaprm.getString("keluarga_terdekat"));
                        }

                        if (rsLaprm.getString("hubungan").equals("")) {
                            param.put("hubKlg", ".............");
                        } else {
                            param.put("hubKlg", rsLaprm.getString("hubungan"));
                        }

                        if (rsLaprm.getString("cek_orang_tua").equals("ya")) {
                            param.put("cekOrtu", "V");
                        } else {
                            param.put("cekOrtu", "");
                        }

                        if (rsLaprm.getString("cek_suami").equals("ya")) {
                            param.put("cekTglSuami", "V");
                        } else {
                            param.put("cekTglSuami", "");
                        }

                        if (rsLaprm.getString("cek_anak").equals("ya")) {
                            param.put("cekAnak", "V");
                        } else {
                            param.put("cekAnak", "");
                        }

                        if (rsLaprm.getString("cek_tinggal_sendiri").equals("ya")) {
                            param.put("cekTglSendiri", "V");
                        } else {
                            param.put("cekTglSendiri", "");
                        }

                        param.put("curiga", rsLaprm.getString("curiga_penganiayaan"));
                        param.put("kegIbadah", rsLaprm.getString("kegiatan_ibadah"));
                        param.put("sttsEmosional", rsLaprm.getString("status_emosional"));

                        if (rsLaprm.getString("cek_asuransi").equals("ya")) {
                            param.put("cekAsuransi", "V");
                        } else {
                            param.put("cekAsuransi", "");
                        }

                        if (rsLaprm.getString("cek_jaminan").equals("ya")) {
                            param.put("cekJaminan", "V");
                        } else {
                            param.put("cekJaminan", "");
                        }

                        if (rsLaprm.getString("cek_biaya_sendiri").equals("ya")) {
                            param.put("cekBySendiri", "V");
                        } else {
                            param.put("cekBySendiri", "");
                        }

                        if (rsLaprm.getString("cek_lain_status_ekonomi").equals("ya")) {
                            param.put("cekSttsKLain", "V");
                            if (rsLaprm.getString("ket_lain_status_ekonomi").equals("")) {
                                param.put("dataSttsKLain", "Lainnya");
                            } else {
                                param.put("dataSttsKLain", "Lainnya (" + rsLaprm.getString("ket_lain_status_ekonomi") + ")");
                            }
                        } else {
                            param.put("cekSttsKLain", "");
                            param.put("dataSttsKLain", "Lainnya");
                        }
                        
                        if (rsLaprm.getString("leopold1").equals("")) {
                            param.put("leo1", "-");
                        } else {
                            param.put("leo1", rsLaprm.getString("leopold1"));
                        }

                        if (rsLaprm.getString("leopold2").equals("")) {
                            param.put("leo2", "-");
                        } else {
                            param.put("leo2", rsLaprm.getString("leopold2"));
                        }

                        if (rsLaprm.getString("leopold3").equals("")) {
                            param.put("leo3", "-");
                        } else {
                            param.put("leo3", rsLaprm.getString("leopold3"));
                        }

                        if (rsLaprm.getString("leopold4").equals("")) {
                            param.put("leo4", "-");
                        } else {
                            param.put("leo4", rsLaprm.getString("leopold4"));
                        }

                        param.put("bandle", rsLaprm.getString("bandle_ring"));
                        param.put("perutTegang", rsLaprm.getString("perut_tegang"));

                        if (rsLaprm.getString("palpasi").equals("")) {
                            param.put("palpasi", "-");
                        } else {
                            param.put("palpasi", rsLaprm.getString("palpasi"));
                        }

                        param.put("goyang", rsLaprm.getString("goyang"));

                        if (rsLaprm.getString("vt_pembukaan").equals("")) {
                            param.put("vtPembukaan", "-");
                        } else {
                            param.put("vtPembukaan", rsLaprm.getString("vt_pembukaan"));
                        }

                        if (rsLaprm.getString("teraba_massa").equals("Ya")) {
                            if (rsLaprm.getString("sebesar").equals("")) {
                                param.put("teraba", rsLaprm.getString("teraba_massa"));
                            } else {
                                param.put("teraba", rsLaprm.getString("teraba_massa") + ", Sebesar : " + rsLaprm.getString("sebesar"));
                            }
                        } else {
                            param.put("teraba", rsLaprm.getString("teraba_massa"));
                        }

                        param.put("nyeriTekan", rsLaprm.getString("nyeri_tekan"));
                        param.put("vtNyeri", rsLaprm.getString("vt_nyeri_goyang"));

                        if (rsLaprm.getString("tfu").equals("")) {
                            param.put("tfu", "...... Cm");
                        } else {
                            param.put("tfu", rsLaprm.getString("tfu") + " Cm");
                        }

                        if (rsLaprm.getString("his_kontraksi").equals("")) {
                            param.put("his", "...... x / 10 menit");
                        } else {
                            param.put("his", rsLaprm.getString("his_kontraksi") + " x / 10 menit");
                        }

                        param.put("ketHis", rsLaprm.getString("jns_his_kontraksi"));

                        if (rsLaprm.getString("taksiran_berat_janin").equals("")) {
                            param.put("taksiran", "...... gram");
                        } else {
                            param.put("taksiran", rsLaprm.getString("taksiran_berat_janin") + " gram");
                        }

                        if (rsLaprm.getString("durasi").equals("")) {
                            param.put("durasi", "...... detik");
                        } else {
                            param.put("durasi", rsLaprm.getString("durasi") + " detik");
                        }

                        param.put("ketDurasi", rsLaprm.getString("jns_durasi"));

                        if (rsLaprm.getString("auskultasi").equals("")) {
                            param.put("auskultasi", "...... x / menit");
                        } else {
                            param.put("auskultasi", rsLaprm.getString("auskultasi") + " x / menit");
                        }

                        if (rsLaprm.getString("cek_bersih").equals("ya")) {
                            param.put("bersih", "V");
                        } else {
                            param.put("bersih", "");
                        }

                        if (rsLaprm.getString("cek_oedema").equals("ya")) {
                            param.put("oedema", "V");
                        } else {
                            param.put("oedema", "");
                        }

                        if (rsLaprm.getString("cek_ruftur").equals("ya")) {
                            param.put("ruftur", "V");
                        } else {
                            param.put("ruftur", "");
                        }

                        if (rsLaprm.getString("cek_candiloma").equals("ya")) {
                            param.put("candi", "V");
                        } else {
                            param.put("candi", "");
                        }

                        if (rsLaprm.getString("cek_lain_pemeriksaan_geni").equals("ya")) {
                            param.put("lainPemeriksaan", "V");
                            if (rsLaprm.getString("ket_lain_pemeriksaan_geni").equals("")) {
                                param.put("dataLainPemeriksaan", "Lainnya");
                            } else {
                                param.put("dataLainPemeriksaan", "Lainnya (" + rsLaprm.getString("ket_lain_pemeriksaan_geni") + ")");
                            }
                        } else {
                            param.put("lainPemeriksaan", "");
                            param.put("dataLainPemeriksaan", "Lainnya");
                        }

                        if (rsLaprm.getString("periksa_dalam_obstetri").equals("ya")) {
                            param.put("periksaDlm", "-");
                        } else {
                            param.put("periksaDlm", rsLaprm.getString("periksa_dalam_obstetri"));
                        }

                        if (rsLaprm.getString("inspekulo").equals("Ya")) {
                            if (rsLaprm.getString("hasil_inspekulo").equals("")) {
                                param.put("inspekulo", rsLaprm.getString("inspekulo"));
                            } else {
                                param.put("inspekulo", rsLaprm.getString("inspekulo") + ", Hasil : " + rsLaprm.getString("hasil_inspekulo"));
                            }
                        } else {
                            param.put("inspekulo", rsLaprm.getString("inspekulo"));
                        }

                        if (rsLaprm.getString("diagnosis_sementara").equals("")) {
                            param.put("diagnosis", "-");
                        } else {
                            param.put("diagnosis", rsLaprm.getString("diagnosis_sementara"));
                        }

                        if (rsLaprm.getString("icd_10").equals("")) {
                            param.put("icd", "-");
                        } else {
                            param.put("icd", rsLaprm.getString("icd_10"));
                        }

                        if (rsLaprm.getString("planing").equals("")) {
                            param.put("planing", "-");
                        } else {
                            param.put("planing", rsLaprm.getString("planing"));
                        }

                        Valid.MyReport("rptAsesmenAwalKebidanan1.jasper", "report", "::[ Asesmen Awal Kebidanan hal. 1 ]::",
                                "select date(now()) tgl", param);

                        Valid.MyReport("rptAsesmenAwalKebidanan2.jasper", "report", "::[ Asesmen Awal Kebidanan hal. 2 ]::",
                                "select * from riwayat_kehamilan_asesmen_awal_kebidanan where no_rawat='" + TNoRw.getText() + "'", param);

                        //halaman 2
                        if (Sequel.cariInteger("select count(-1) from asesmen_awal_kebidanan2 where no_rawat='" + TNoRw.getText() + "'") > 0) {
                            if (rsLaprm.getString("nyeri").equals("Ya")) {
                                if (rsLaprm.getString("lokasi_nyeri").equals("")) {
                                    param.put("AsesNyeri", rsLaprm.getString("nyeri") + ", Lokasi : ..........");
                                } else {
                                    param.put("AsesNyeri", rsLaprm.getString("nyeri") + ", Lokasi : " + rsLaprm.getString("lokasi_nyeri"));
                                }                                
                            } else {
                                param.put("AsesNyeri", rsLaprm.getString("nyeri"));
                            }

                            param.put("AsesJenis", rsLaprm.getString("jenis"));
                            param.put("AsesSkala", rsLaprm.getString("skala_nyeri"));

                            if (rsLaprm.getString("provocation").equals("Lainnya")) {
                                if (rsLaprm.getString("ket_lain_provocation").equals("")) {
                                    param.put("AsesProvo", rsLaprm.getString("provocation") + " ..........");
                                } else {
                                    param.put("AsesProvo", rsLaprm.getString("provocation") + " : " + rsLaprm.getString("ket_lain_provocation"));
                                }
                            } else {
                                param.put("AsesProvo", rsLaprm.getString("provocation"));
                            }
                            
                            if (rsLaprm.getString("quality").equals("Lainnya")) {
                                if (rsLaprm.getString("ket_lain_quality").equals("")) {
                                    param.put("AsesQuality", rsLaprm.getString("quality") + " ..........");
                                } else {
                                    param.put("AsesQuality", rsLaprm.getString("quality") + " : " + rsLaprm.getString("ket_lain_quality"));
                                }
                            } else {
                                param.put("AsesQuality", rsLaprm.getString("quality"));
                            }
                            
                            param.put("AsesRadia", rsLaprm.getString("radiation"));
                            param.put("AsesSever", rsLaprm.getString("severity"));
                            
                            if (!rsLaprm.getString("time").equals("-")) {
                                param.put("AsesTime", rsLaprm.getString("time") + ", Lama : " + rsLaprm.getString("time_lama"));
                            } else {
                                param.put("AsesTime", rsLaprm.getString("time"));
                            }
                            
                            //hitung skor gizi
                            int skorA = 0, skorB = 0, skorC = 0, skorTotal = 0;
                            if (rsLaprm.getString("gizi_1").equals("Tidak") || rsLaprm.getString("gizi_1").equals("Ya ada penurunan BB sebanyak :")) {
                                skorA = 0;                                
                            } else if (rsLaprm.getString("gizi_1").equals("Tidak Yakin (ada tanda : baju menjadi longgar)")) {
                                skorA = 2;
                            }
                            
                            if (rsLaprm.getString("gizi_1ya").equals("-")) {
                                skorB = 0;
                            } else if (rsLaprm.getString("gizi_1ya").equals("1 - 5 Kg")) {
                                skorB = 1;
                            } else if (rsLaprm.getString("gizi_1ya").equals("6 - 10 Kg") || rsLaprm.getString("gizi_1ya").equals("Tidak tahu berapa Kg penurunanya")) {
                                skorB = 2;
                            } else if (rsLaprm.getString("gizi_1ya").equals("11 - 15 Kg")) {
                                skorB = 3;
                            } else if (rsLaprm.getString("gizi_1ya").equals("> 15 Kg")) {
                                skorB = 4;
                            }
                            
                            if (rsLaprm.getString("gizi_2").equals("Tidak")) {
                                skorC = 0;
                            } else if (rsLaprm.getString("gizi_2").equals("Ya")) {
                                skorC = 1;
                            }
                            
                            skorTotal = skorA + skorB + skorC;
                            param.put("gizi1", rsLaprm.getString("gizi_1") + " (Skor : " + skorA + ")");
                            param.put("gizi1Ya", rsLaprm.getString("gizi_1ya") + " (Skor : " + skorB + ")");
                            param.put("gizi2", rsLaprm.getString("gizi_2") + " (Skor : " + skorC + ")");
                            
                            if (skorTotal == 0 || skorTotal == 1) {
                                param.put("kesGizi", "Pasien tidak beresiko malnutrisi");
                            } else if (skorTotal >= 2) {
                                param.put("kesGizi", "Skor >= 2, pasien beresiko malnutrisi, konsul ke Ahli Gizi");
                            }
                            //---------------------------------------
                            
                            if (rsLaprm.getString("cek_tidak_ada").equals("ya")) {
                                param.put("cekRiwTdkAda", "V");
                            } else {
                                param.put("cekRiwTdkAda", "");
                            }
                            
                            if (rsLaprm.getString("cek_tidak_diketahui").equals("ya")) {
                                param.put("cekRiwTdkDik", "V");
                            } else {
                                param.put("cekRiwTdkDik", "");
                            }
                            
                            if (rsLaprm.getString("cek_alergi_obat").equals("ya")) {
                                param.put("cekriwAlerObat", "V");
                                if (rsLaprm.getString("ket_alergi_obat").equals("")) {
                                    param.put("ketAlerObat", "Alergi Obat");
                                } else {
                                    param.put("ketAlerObat", "Alergi Obat : " + rsLaprm.getString("ket_alergi_obat"));
                                }
                                
                                if (rsLaprm.getString("ket_reaksi_alergi_obat").equals("")) {
                                    param.put("ketReakAlerObat", "Reaksi ..........");
                                } else {
                                    param.put("ketReakAlerObat", "Reaksi : " + rsLaprm.getString("ket_reaksi_alergi_obat"));
                                }                                
                            } else {
                                param.put("cekriwAlerObat", "");
                                param.put("ketAlerObat", "Alergi Obat");
                                param.put("ketReakAlerObat", "Reaksi ..........");
                            }
                            
                            if (rsLaprm.getString("cek_alergi_makanan").equals("ya")) {
                                param.put("cekriwAlerMak", "V");
                                if (rsLaprm.getString("ket_alergi_makanan").equals("")) {
                                    param.put("ketAlerMak", "Alergi Makanan");
                                } else {
                                    param.put("ketAlerMak", "Alergi Makanan : " + rsLaprm.getString("ket_alergi_makanan"));
                                }
                                
                                if (rsLaprm.getString("ket_reaksi_alergi_makanan").equals("")) {
                                    param.put("ketReakAlerMak", "Reaksi ..........");
                                } else {
                                    param.put("ketReakAlerMak", "Reaksi : " + rsLaprm.getString("ket_reaksi_alergi_makanan"));
                                }                                
                            } else {
                                param.put("cekriwAlerMak", "");
                                param.put("ketAlerMak", "Alergi Makanan");
                                param.put("ketReakAlerMak", "Reaksi ..........");
                            }
                            
                            if (rsLaprm.getString("cek_alergi_lainya").equals("ya")) {
                                param.put("cekriwAlerLain", "V");
                                if (rsLaprm.getString("ket_alergi_lainya").equals("")) {
                                    param.put("ketAlerLain", "Alergi Lainnya");
                                } else {
                                    param.put("ketAlerLain", "Alergi Lainnya : " + rsLaprm.getString("ket_alergi_lainya"));
                                }
                                
                                if (rsLaprm.getString("ket_reaksi_alergi_lainya").equals("")) {
                                    param.put("ketReakAlerLain", "Reaksi ..........");
                                } else {
                                    param.put("ketReakAlerLain", "Reaksi : " + rsLaprm.getString("ket_reaksi_alergi_lainya"));
                                }                                
                            } else {
                                param.put("cekriwAlerLain", "");
                                param.put("ketAlerLain", "Alergi Lainnya");
                                param.put("ketReakAlerLain", "Reaksi ..........");
                            }
                            
                            if (rsLaprm.getString("cek_gelang_tanda").equals("ya")) {
                                param.put("cekGelang", "V");
                            } else {
                                param.put("cekGelang", "");
                            }
                            
                            if (rsLaprm.getString("cek_alergi_diberitahukan_dokter").equals("ya")) {
                                param.put("cekDokter", "V");
                            } else {
                                param.put("cekDokter", "");
                            }
                            
                            if (rsLaprm.getString("cek_alergi_diberitahukan_farmasis").equals("ya")) {
                                param.put("cekFarmasi", "V");
                            } else {
                                param.put("cekFarmasi", "");
                            }
                            
                            if (rsLaprm.getString("cek_alergi_diberitahukan_ahliGizi").equals("ya")) {
                                param.put("cekAhliGz", "V");
                            } else {
                                param.put("cekAhliGz", "");
                            }
                            
                            //hitung skor resiko jatuh
                            int skorRJ = 0, skorKS = 0, skorAB = 0, skorT = 0, skorGB = 0, skorSM = 0, totSkorRJ = 0;
                            if (rsLaprm.getString("riw_jatuh_resiko_jatuh").equals("-") || rsLaprm.getString("riw_jatuh_resiko_jatuh").equals("Tidak ada atau >= 3 bulan")) {
                                skorRJ = 0;
                            } else if (rsLaprm.getString("riw_jatuh_resiko_jatuh").equals("< dari 3 bulan")) {
                                skorRJ = 25;
                            }
                            
                            if (rsLaprm.getString("kondisi_kesehatan").equals("-") || rsLaprm.getString("kondisi_kesehatan").equals("< diagnosa penyakit")) {
                                skorKS = 0;
                            } else if (rsLaprm.getString("kondisi_kesehatan").equals("> diagnosa penyakit")) {
                                skorKS = 15;
                            }
                            
                            if (rsLaprm.getString("alat_bantu_resiko_jatuh").equals("-") || rsLaprm.getString("alat_bantu_resiko_jatuh").equals("Tidak ada/kursi roda/tirah baring")) {
                                skorAB = 0;
                            } else if (rsLaprm.getString("alat_bantu_resiko_jatuh").equals("Berpegangan pada perabot")) {
                                skorAB = 30;
                            } else if (rsLaprm.getString("alat_bantu_resiko_jatuh").equals("Tongkat/alat penopang")) {
                                skorAB = 15;
                            }
                            
                            if (rsLaprm.getString("terapi_IV").equals("-") || rsLaprm.getString("terapi_IV").equals("Tidak")) {
                                skorT = 0;
                            } else if (rsLaprm.getString("terapi_IV").equals("Terapi IV terus menerus")) {
                                skorT = 20;
                            }
                            
                            if (rsLaprm.getString("gaya_berjalan").equals("-") || rsLaprm.getString("gaya_berjalan").equals("Normal/tirah baring/immobilisasi")) {
                                skorGB = 0;
                            } else if (rsLaprm.getString("gaya_berjalan").equals("Kerusakan/terganggu")) {
                                skorGB = 20;
                            } else if (rsLaprm.getString("gaya_berjalan").equals("Lemah")) {
                                skorGB = 10;
                            }
                            
                            if (rsLaprm.getString("status_mental").equals("-") || rsLaprm.getString("status_mental").equals("Sadar kemampuan diri sendiri")) {
                                skorSM = 0;
                            } else if (rsLaprm.getString("status_mental").equals("Lupa keterbatasan yang dimiliki")) {
                                skorSM = 15;
                            }
                            
                            totSkorRJ = skorRJ + skorKS + skorAB + skorT + skorGB + skorSM;
                            param.put("asesRiwJatuh", rsLaprm.getString("riw_jatuh_resiko_jatuh"));
                            param.put("skorAsesRJ", skorRJ);
                            param.put("asesKonKes", rsLaprm.getString("kondisi_kesehatan"));
                            param.put("skorAsesKS", skorKS);
                            param.put("asesAlatBan", rsLaprm.getString("alat_bantu_resiko_jatuh"));
                            param.put("skorAsesAB", skorAB);
                            param.put("asesTer", rsLaprm.getString("terapi_IV"));
                            param.put("skorAsesT", skorT);
                            param.put("asesGayBer", rsLaprm.getString("gaya_berjalan"));
                            param.put("skorAsesGB", skorGB);
                            param.put("asesStaMen", rsLaprm.getString("status_mental"));
                            param.put("skorAsesSM", skorSM);
                            
                            if (totSkorRJ == 0 && totSkorRJ <= 24) {
                                param.put("kesResJatuh", "Skor 0-24 Resiko Rendah");
                            } else if (totSkorRJ >= 25 && totSkorRJ <= 45) {
                                param.put("kesResJatuh", "Skor 25-45 Resiko Sedang");
                            } else if (totSkorRJ >= 46) {
                                param.put("kesResJatuh", "Skor > 45 Resiko Tinggi");
                            }
                            //---------------------------------------
                            
                            if (rsLaprm.getString("alat_bantu").equals("")) {
                                param.put("alatBan", "-");
                            } else {
                                param.put("alatBan", rsLaprm.getString("alat_bantu"));
                            }
                            
                            if (rsLaprm.getString("prothesis").equals("")) {
                                param.put("protesis", "-");
                            } else {
                                param.put("protesis", rsLaprm.getString("prothesis"));
                            }
                            
                            if (rsLaprm.getString("cacat_tubuh").equals("")) {
                                param.put("cacat", "-");
                            } else {
                                param.put("cacat", rsLaprm.getString("cacat_tubuh"));
                            }
                            
                            param.put("adl", rsLaprm.getString("adl"));
                            param.put("fungRiwJat", rsLaprm.getString("riwayat_jatuh"));
                            param.put("fungNmBidan", Sequel.cariIsi("select nama from pegawai where nip='" + rsLaprm.getString("nip_bidan") + "'"));
                            param.put("fungNip", rsLaprm.getString("nip_bidan"));
                            
                            if (rsLaprm.getString("cek_ya").equals("ya")) {
                                param.put("cekYa", "V");
                            } else {
                                param.put("cekYa", "");
                            }
                            
                            if (rsLaprm.getString("cek_pendengaran").equals("ya")) {
                                param.put("cekDengar", "V");
                            } else {
                                param.put("cekDengar", "");
                            }
                            
                            if (rsLaprm.getString("cek_penglihatan").equals("ya")) {
                                param.put("cekLihat", "V");
                            } else {
                                param.put("cekLihat", "");
                            }
                            
                            if (rsLaprm.getString("cek_kognitif").equals("ya")) {
                                param.put("cekKognitif", "V");
                            } else {
                                param.put("cekKognitif", "");
                            }
                            
                            if (rsLaprm.getString("cek_fisik").equals("ya")) {
                                param.put("cekFisik", "V");
                            } else {
                                param.put("cekFisik", "");
                            }
                            
                            if (rsLaprm.getString("cek_budaya").equals("ya")) {
                                param.put("cekBudaya", "V");
                            } else {
                                param.put("cekBudaya", "");
                            }
                            
                            if (rsLaprm.getString("cek_emosi").equals("ya")) {
                                param.put("cekEmosi", "V");
                            } else {
                                param.put("cekEmosi", "");
                            }
                            
                            if (rsLaprm.getString("cek_bahasa").equals("ya")) {
                                param.put("cekBahasa", "V");
                            } else {
                                param.put("cekBahasa", "");
                            }
                            
                            if (rsLaprm.getString("cek_lain_hambatan").equals("ya")) {
                                param.put("cekLainHam", "V");
                                if (rsLaprm.getString("ket_lain_hambatan").equals("")) {
                                    param.put("ketLainHam", "Lainnya ...........");
                                } else {
                                    param.put("ketLainHam", "Lainnya : " + rsLaprm.getString("ket_lain_hambatan"));
                                }
                            } else {
                                param.put("cekLainHam", "");
                                param.put("ketLainHam", "Lainnya ...........");
                            }
                            
                            if (rsLaprm.getString("dibutuhkan_penerjemah").equals("Ya")) {
                                if (rsLaprm.getString("sebutkan").equals("")) {
                                    param.put("dibutuhkan", rsLaprm.getString("dibutuhkan_penerjemah") + ", sebutkan .........");
                                } else {
                                    param.put("dibutuhkan", rsLaprm.getString("dibutuhkan_penerjemah") + ", sebutkan : " + rsLaprm.getString("sebutkan"));
                                }
                            } else {
                                param.put("dibutuhkan", rsLaprm.getString("dibutuhkan_penerjemah"));
                            }
                            
                            param.put("bahasaIsya", rsLaprm.getString("bahasa_isyarat"));
                            
                            if (rsLaprm.getString("cek_diagnosa").equals("ya")) {
                                param.put("cekDiagnosa", "V");
                            } else {
                                param.put("cekDiagnosa", "");
                            }
                            
                            if (rsLaprm.getString("cek_tindakan_keperawatan").equals("ya")) {
                                param.put("cekTindakan", "V");
                                if (rsLaprm.getString("ket_tindakan_keperawatan").equals("")) {
                                    param.put("ketTindakan", "Tindakan Keperawatan ............");
                                } else {
                                    param.put("ketTindakan", "Tindakan Keperawatan : " + rsLaprm.getString("ket_tindakan_keperawatan"));
                                }
                            } else {
                                param.put("cekTindakan", "");
                                param.put("ketTindakan", "Tindakan Keperawatan ............");
                            }
                            
                            if (rsLaprm.getString("cek_lain_kebutuhan_edukasi").equals("ya")) {
                                param.put("cekLainKeb", "V");
                                if (rsLaprm.getString("ket_lain_kebutuhan_edukasi").equals("")) {
                                    param.put("ketLainKeb", "Lain-lain, Sebutkan ............");
                                } else {
                                    param.put("ketLainKeb", "Lain-lain, Sebutkan : " + rsLaprm.getString("ket_lain_kebutuhan_edukasi"));
                                }
                            } else {
                                param.put("cekLainKeb", "");
                                param.put("ketLainKeb", "Lain-lain, Sebutkan ............");
                            }
                            
                            if (rsLaprm.getString("cek_obat_obatan").equals("ya")) {
                                param.put("cekObatan", "V");
                            } else {
                                param.put("cekObatan", "");
                            }
                            
                            if (rsLaprm.getString("cek_rehabilitasi").equals("ya")) {
                                param.put("cekRehab", "V");
                            } else {
                                param.put("cekRehab", "");
                            }
                            
                            if (rsLaprm.getString("cek_diet").equals("ya")) {
                                param.put("cekDiet", "V");
                            } else {
                                param.put("cekDiet", "");
                            }
                            
                            if (rsLaprm.getString("cek_manajemen_nyeri").equals("ya")) {
                                param.put("cekMana", "V");
                            } else {
                                param.put("cekMana", "");
                            }
                            
                            if (rsLaprm.getString("cek_pasien").equals("ya")) {
                                param.put("cekPasien", "V");
                            } else {
                                param.put("cekPasien", "");
                            }
                            
                            if (rsLaprm.getString("cek_keluarga_pasien").equals("ya")) {
                                param.put("cekKlgPasien", "V");
                                if (rsLaprm.getString("nama_keluarga_pasien").equals("")) {
                                    param.put("nmKlgPasien", "Keluarga Pasien, Nama : ........");
                                } else {
                                    param.put("nmKlgPasien", "Keluarga Pasien, Nama : " + rsLaprm.getString("nama_keluarga_pasien"));
                                }    
                            } else {
                                param.put("cekKlgPasien", "");
                                param.put("nmKlgPasien", "Keluarga Pasien, Nama : ........");
                            }
                            
                            if (rsLaprm.getString("cek_tidak_dapat").equals("ya")) {
                                param.put("cekTdkDapat", "V");
                                if (rsLaprm.getString("ket_tidak_dapat").equals("")) {
                                    param.put("ketTdkDapat", "Tidak Dapat Memberikan Edukasi Kepada Pasien Atau Keluarga, Karena : ........");
                                } else {
                                    param.put("ketTdkDapat", "Tidak Dapat Memberikan Edukasi Kepada Pasien Atau Keluarga, Karena : " + rsLaprm.getString("ket_tidak_dapat"));
                                }    
                            } else {
                                param.put("cekTdkDapat", "");
                                param.put("ketTdkDapat", "Tidak Dapat Memberikan Edukasi Kepada Pasien Atau Keluarga, Karena : ........");
                            }
                            
                            param.put("tglEdukasi", Valid.SetTglINDONESIA(rsLaprm.getString("tgl_edukasi")));
                            param.put("jamEdukasi", rsLaprm.getString("jam_edukasi").substring(0, 5) + " Wita");
                            param.put("nmDokter", Sequel.cariIsi("select nama from pegawai where nip='" + rsLaprm.getString("nip_dokter") + "'"));
                            param.put("nipDokter", rsLaprm.getString("nip_dokter"));
                            
                            if (rsLaprm.getString("cek_identifikasi1").equals("ya")) {
                                param.put("cekIden1", "V");
                            } else {
                                param.put("cekIden1", "");
                            }
                            
                            if (rsLaprm.getString("cek_identifikasi2").equals("ya")) {
                                param.put("cekIden2", "V");
                            } else {
                                param.put("cekIden2", "");
                            }
                            
                            if (rsLaprm.getString("cek_identifikasi3").equals("ya")) {
                                param.put("cekIden3", "V");
                            } else {
                                param.put("cekIden3", "");
                            }
                            
                            if (rsLaprm.getString("cek_identifikasi4").equals("ya")) {
                                param.put("cekIden4", "V");
                            } else {
                                param.put("cekIden4", "");
                            }
                            
                            if (rsLaprm.getString("cek_identifikasi5").equals("ya")) {
                                param.put("cekIden5", "V");
                            } else {
                                param.put("cekIden5", "");
                            }
                            
                            if (rsLaprm.getString("cek_identifikasi6").equals("ya")) {
                                param.put("cekIden6", "V");
                            } else {
                                param.put("cekIden6", "");
                            }
                            
                            if (rsLaprm.getString("cek_identifikasi7").equals("ya")) {
                                param.put("cekIden7", "V");
                            } else {
                                param.put("cekIden7", "");
                            }
                            
                            if (rsLaprm.getString("cek_identifikasi8").equals("ya")) {
                                param.put("cekIden8", "V");
                            } else {
                                param.put("cekIden8", "");
                            }
                            
                            if (rsLaprm.getString("cek_identifikasi9").equals("ya")) {
                                param.put("cekIden9", "V");
                            } else {
                                param.put("cekIden9", "");
                            }
                            
                            if (rsLaprm.getString("cek_identifikasi10").equals("ya")) {
                                param.put("cekIden10", "V");
                            } else {
                                param.put("cekIden10", "");
                            }
                            
                            if (rsLaprm.getString("memerlukan").equals("")) {
                                param.put("memer", "-");
                            } else {
                                param.put("memer", rsLaprm.getString("memerlukan"));
                            }
                            
                            param.put("mpp", rsLaprm.getString("mpp"));
                            param.put("dp", rsLaprm.getString("dp"));
                            param.put("tglDp", Valid.SetTglINDONESIA(rsLaprm.getString("tgl_dp")));
                            
                            if (rsLaprm.getString("nm_keluarga_pasien").equals("")) {
                                param.put("nmKeluarga", "-");
                            } else {
                                param.put("nmKeluarga", rsLaprm.getString("nm_keluarga_pasien"));
                            }
                            
                            param.put("namaBidanDp", Sequel.cariIsi("select nama from pegawai where nip='" + rsLaprm.getString("nip_bidan_dp") + "'"));
                            param.put("nipBidanDp", rsLaprm.getString("nip_bidan_dp"));
                            
//                            Valid.MyReport("rptAsesmenAwalKebidanan3.jasper", "report", "::[ Asesmen Awal Kebidanan hal. 3 ]::",
//                                "select date(now()) tgl", param);
                        }

                        TCari.setText(TNoRw.getText());
                        emptTeks();
                        TabRawat.setSelectedIndex(1);
                        tampil();
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rsLaprm != null) {
                        rsLaprm.close();
                    }
                    if (psLaprm != null) {
                        psLaprm.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel..!!");
            emptTeks();
            TabRawat.setSelectedIndex(1);
            tampil();
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
        }
}//GEN-LAST:event_tbAsesmenMouseClicked

    private void tbAsesmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAsesmenKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbAsesmenKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            Valid.SetTgl(DTPCari1, "2025-07-13");
            tampil();
        } else if (TabRawat.getSelectedIndex() == 2) {
            if (tbAsesmen.getSelectedRow() > -1 || Sequel.cariInteger("select count(-1) from asesmen_awal_kebidanan1 where no_rawat='" + TNoRw.getText() + "'") > 0) {
                tampilPreview();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel..!!");
                emptTeks();
                TabRawat.setSelectedIndex(1);
                tampil();
            }
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (Sequel.cariInteger("select count(-1) from asesmen_awal_kebidanan1 where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from asesmen_awal_kebidanan1 where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void TkeadaanAnakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeadaanAnakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnSimpanRiwayatActionPerformed(null);
            TthnPartus.requestFocus();
        }
    }//GEN-LAST:event_TkeadaanAnakKeyPressed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void TnamaSuamiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnamaSuamiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TumurSuami.requestFocus();
        }
    }//GEN-LAST:event_TnamaSuamiKeyPressed

    private void TumurSuamiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurSuamiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TpekerjaanSuami.requestFocus();
        }
    }//GEN-LAST:event_TumurSuamiKeyPressed

    private void TpekerjaanSuamiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpekerjaanSuamiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TalamatSuami.requestFocus();
        }
    }//GEN-LAST:event_TpekerjaanSuamiKeyPressed

    private void chkAlamatSamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAlamatSamaActionPerformed
        TalamatSuami.setText("");
        if (chkAlamatSama.isSelected() == true) {
            TalamatSuami.setText(TalamatPasien.getText());
        } else {
            TalamatSuami.setText("");
            TalamatSuami.requestFocus();
        }
    }//GEN-LAST:event_chkAlamatSamaActionPerformed

    private void TalamatSuamiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalamatSuamiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbAgamaSuami.requestFocus();
        }
    }//GEN-LAST:event_TalamatSuamiKeyPressed

    private void TtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnadi.requestFocus();
        }
    }//GEN-LAST:event_TtdKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trespi.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TrespiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrespiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsuhu.requestFocus();
        }
    }//GEN-LAST:event_TrespiKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsaturasi.requestFocus();
        }
    }//GEN-LAST:event_TsuhuKeyPressed

    private void TsaturasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsaturasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkesadaran.requestFocus();
        }
    }//GEN-LAST:event_TsaturasiKeyPressed

    private void TkesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesadaranKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSendiri.requestFocus();
        }
    }//GEN-LAST:event_TkesadaranKeyPressed

    private void TalasanMskRSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalasanMskRSKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttd.requestFocus();
        }
    }//GEN-LAST:event_TalasanMskRSKeyPressed

    private void TketRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketRujukanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSpog.requestFocus();
        }
    }//GEN-LAST:event_TketRujukanKeyPressed

    private void TketPkmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketPkmKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkRsLain.requestFocus();
        }
    }//GEN-LAST:event_TketPkmKeyPressed

    private void TketRsLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketRsLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tgr.requestFocus();
        }
    }//GEN-LAST:event_TketRsLainKeyPressed

    private void chkRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRujukanActionPerformed
        cmbJnsRujukan.setSelectedIndex(0);
        TketRujukan.setText("");
        if (chkRujukan.isSelected() == true) {
            cmbJnsRujukan.setEnabled(true);
            TketRujukan.setEnabled(true);
            cmbJnsRujukan.requestFocus();
            
            TketPkm.setEnabled(false);
            TketPkm.setText("");
            TketRsLain.setEnabled(false);
            TketRsLain.setText("");
        } else {
            cmbJnsRujukan.setEnabled(false);
            TketRujukan.setEnabled(false);
        }
    }//GEN-LAST:event_chkRujukanActionPerformed

    private void chkPkmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPkmActionPerformed
        TketPkm.setText("");
        if (chkPkm.isSelected() == true) {
            TketPkm.setEnabled(true);
            TketPkm.requestFocus();
            
            cmbJnsRujukan.setEnabled(false);
            TketRujukan.setEnabled(false);
            cmbJnsRujukan.setSelectedIndex(0);
            TketRujukan.setText("");
            TketRsLain.setEnabled(false);
            TketRsLain.setText("");
        } else {
            TketPkm.setEnabled(false);
        }
    }//GEN-LAST:event_chkPkmActionPerformed

    private void chkRsLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRsLainActionPerformed
        TketRsLain.setText("");
        if (chkRsLain.isSelected() == true) {
            TketRsLain.setEnabled(true);
            TketRsLain.requestFocus();
            
            cmbJnsRujukan.setEnabled(false);
            TketRujukan.setEnabled(false);
            cmbJnsRujukan.setSelectedIndex(0);
            TketRujukan.setText("");
            TketPkm.setEnabled(false);
            TketPkm.setText("");
        } else {
            TketRsLain.setEnabled(false);
        }
    }//GEN-LAST:event_chkRsLainActionPerformed

    private void TgrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpr.requestFocus();
        }
    }//GEN-LAST:event_TgrKeyPressed

    private void TprKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ta.requestFocus();
        }
    }//GEN-LAST:event_TprKeyPressed

    private void TaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Thamil.requestFocus();
        }
    }//GEN-LAST:event_TaKeyPressed

    private void ThamilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThamilKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tgpapah.requestFocus();
        }
    }//GEN-LAST:event_ThamilKeyPressed

    private void TgpapahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgpapahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdengan.requestFocus();
        }
    }//GEN-LAST:event_TgpapahKeyPressed

    private void TdenganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdenganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPerut.requestFocus();
        }
    }//GEN-LAST:event_TdenganKeyPressed

    private void cmbJam2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam2MouseReleased
        AutoCompleteDecorator.decorate(cmbJam2);
    }//GEN-LAST:event_cmbJam2MouseReleased

    private void cmbMnt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt2MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt2);
    }//GEN-LAST:event_cmbMnt2MouseReleased

    private void cmbDtk2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk2);
    }//GEN-LAST:event_cmbDtk2MouseReleased

    private void cmbPerutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPerutActionPerformed
        cmbKeluhanPerut.setSelectedIndex(0);
        TtglPerut.setDate(new Date());
        cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk2.setSelectedIndex(0);
        if (cmbPerut.getSelectedIndex() == 1) {
            cmbKeluhanPerut.setEnabled(true);
            TtglPerut.setEnabled(true);
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
            cmbKeluhanPerut.requestFocus();
        } else {
            cmbKeluhanPerut.setEnabled(false);
            TtglPerut.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPerutActionPerformed

    private void cmbKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKeluarActionPerformed
        cmbKeluhanKeluar.setSelectedIndex(0);
        TtglKeluar.setDate(new Date());
        cmbJam3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk3.setSelectedIndex(0);
        if (cmbKeluar.getSelectedIndex() == 1) {
            cmbKeluhanKeluar.setEnabled(true);
            TtglKeluar.setEnabled(true);
            cmbJam3.setEnabled(true);
            cmbMnt3.setEnabled(true);
            cmbDtk3.setEnabled(true);
            cmbKeluhanKeluar.requestFocus();
        } else {
            cmbKeluhanKeluar.setEnabled(false);
            TtglKeluar.setEnabled(false);
            cmbJam3.setEnabled(false);
            cmbMnt3.setEnabled(false);
            cmbDtk3.setEnabled(false);
        }
    }//GEN-LAST:event_cmbKeluarActionPerformed

    private void cmbJam3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam3MouseReleased
        AutoCompleteDecorator.decorate(cmbJam3);
    }//GEN-LAST:event_cmbJam3MouseReleased

    private void cmbMnt3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt3MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt3);
    }//GEN-LAST:event_cmbMnt3MouseReleased

    private void cmbDtk3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk3MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk3);
    }//GEN-LAST:event_cmbDtk3MouseReleased

    private void cmbDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDarahActionPerformed
        cmbKeluhanDarah.setSelectedIndex(0);
        cmbJnsDarah.setSelectedIndex(0);
        TtglDarah.setDate(new Date());
        cmbJam4.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt4.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk4.setSelectedIndex(0);
        if (cmbDarah.getSelectedIndex() == 1) {
            cmbKeluhanDarah.setEnabled(true);
            cmbJnsDarah.setEnabled(true);
            TtglDarah.setEnabled(true);
            cmbJam4.setEnabled(true);
            cmbMnt4.setEnabled(true);
            cmbDtk4.setEnabled(true);
            cmbKeluhanDarah.requestFocus();
        } else {
            cmbKeluhanDarah.setEnabled(false);
            cmbJnsDarah.setEnabled(false);
            TtglDarah.setEnabled(false);
            cmbJam4.setEnabled(false);
            cmbMnt4.setEnabled(false);
            cmbDtk4.setEnabled(false);
        }
    }//GEN-LAST:event_cmbDarahActionPerformed

    private void cmbJam4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam4MouseReleased
        AutoCompleteDecorator.decorate(cmbJam4);
    }//GEN-LAST:event_cmbJam4MouseReleased

    private void cmbMnt4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt4MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt4);
    }//GEN-LAST:event_cmbMnt4MouseReleased

    private void cmbDtk4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk4MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk4);
    }//GEN-LAST:event_cmbDtk4MouseReleased

    private void cmbKeluarAirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKeluarAirActionPerformed
        cmbKeluhanKeluarAir.setSelectedIndex(0);
        cmbJnsKeluarAir.setSelectedIndex(0);
        TtglKeluarAir.setDate(new Date());
        cmbJam5.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt5.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk5.setSelectedIndex(0);
        if (cmbKeluarAir.getSelectedIndex() == 1) {
            cmbKeluhanKeluarAir.setEnabled(true);
            cmbJnsKeluarAir.setEnabled(true);
            TtglKeluarAir.setEnabled(true);
            cmbJam5.setEnabled(true);
            cmbMnt5.setEnabled(true);
            cmbDtk5.setEnabled(true);
            cmbJnsKeluarAir.requestFocus();
        } else {
            cmbKeluhanKeluarAir.setEnabled(false);
            cmbJnsKeluarAir.setEnabled(false);
            TtglKeluarAir.setEnabled(false);
            cmbJam5.setEnabled(false);
            cmbMnt5.setEnabled(false);
            cmbDtk5.setEnabled(false);
        }
    }//GEN-LAST:event_cmbKeluarAirActionPerformed

    private void cmbJam5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam5MouseReleased
        AutoCompleteDecorator.decorate(cmbJam5);
    }//GEN-LAST:event_cmbJam5MouseReleased

    private void cmbMnt5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt5MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt5);
    }//GEN-LAST:event_cmbMnt5MouseReleased

    private void cmbDtk5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk5MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk5);
    }//GEN-LAST:event_cmbDtk5MouseReleased

    private void cmbPergerakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPergerakanActionPerformed
        Tpergerakan.setText("");
        if (cmbPergerakan.getSelectedIndex() == 1) {
            Tpergerakan.setEnabled(true);
            Tpergerakan.requestFocus();
        } else {
            Tpergerakan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPergerakanActionPerformed

    private void TpergerakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpergerakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPusing.requestFocus();
        }
    }//GEN-LAST:event_TpergerakanKeyPressed

    private void cmbPusingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPusingActionPerformed
        TtglPusing.setDate(new Date());
        cmbJam6.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt6.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk6.setSelectedIndex(0);
        if (cmbPusing.getSelectedIndex() == 1) {
            TtglPusing.setEnabled(true);
            cmbJam6.setEnabled(true);
            cmbMnt6.setEnabled(true);
            cmbDtk6.setEnabled(true);
            TtglPusing.requestFocus();
        } else {
            TtglPusing.setEnabled(false);
            cmbJam6.setEnabled(false);
            cmbMnt6.setEnabled(false);
            cmbDtk6.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPusingActionPerformed

    private void cmbJam6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam6MouseReleased
        AutoCompleteDecorator.decorate(cmbJam6);
    }//GEN-LAST:event_cmbJam6MouseReleased

    private void cmbMnt6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt6MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt6);
    }//GEN-LAST:event_cmbMnt6MouseReleased

    private void cmbDtk6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk6MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk6);
    }//GEN-LAST:event_cmbDtk6MouseReleased

    private void cmbNyeriUluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNyeriUluActionPerformed
        TtglNyeriUlu.setDate(new Date());
        cmbJam7.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt7.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk7.setSelectedIndex(0);
        if (cmbNyeriUlu.getSelectedIndex() == 1) {
            TtglNyeriUlu.setEnabled(true);
            cmbJam7.setEnabled(true);
            cmbMnt7.setEnabled(true);
            cmbDtk7.setEnabled(true);
            TtglNyeriUlu.requestFocus();
        } else {
            TtglNyeriUlu.setEnabled(false);
            cmbJam7.setEnabled(false);
            cmbMnt7.setEnabled(false);
            cmbDtk7.setEnabled(false);
        }
    }//GEN-LAST:event_cmbNyeriUluActionPerformed

    private void cmbJam7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam7MouseReleased
        AutoCompleteDecorator.decorate(cmbJam7);
    }//GEN-LAST:event_cmbJam7MouseReleased

    private void cmbMnt7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt7MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt7);
    }//GEN-LAST:event_cmbMnt7MouseReleased

    private void cmbDtk7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk7MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk7);
    }//GEN-LAST:event_cmbDtk7MouseReleased

    private void cmbPandanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPandanganActionPerformed
        TtglPandangan.setDate(new Date());
        cmbJam8.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt8.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk8.setSelectedIndex(0);
        if (cmbPandangan.getSelectedIndex() == 1) {
            TtglPandangan.setEnabled(true);
            cmbJam8.setEnabled(true);
            cmbMnt8.setEnabled(true);
            cmbDtk8.setEnabled(true);
            TtglPandangan.requestFocus();
        } else {
            TtglPandangan.setEnabled(false);
            cmbJam8.setEnabled(false);
            cmbMnt8.setEnabled(false);
            cmbDtk8.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPandanganActionPerformed

    private void cmbJam8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam8MouseReleased
        AutoCompleteDecorator.decorate(cmbJam8);
    }//GEN-LAST:event_cmbJam8MouseReleased

    private void cmbMnt8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt8MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt8);
    }//GEN-LAST:event_cmbMnt8MouseReleased

    private void cmbDtk8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk8MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk8);
    }//GEN-LAST:event_cmbDtk8MouseReleased

    private void cmbOdemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOdemaActionPerformed
        TtglOdema.setDate(new Date());
        cmbOdemaDi.setSelectedIndex(0);
        if (cmbOdema.getSelectedIndex() == 1) {
            TtglOdema.setEnabled(true);
            cmbOdemaDi.setEnabled(true);
            TtglOdema.requestFocus();
        } else {
            TtglOdema.setEnabled(false);
            cmbOdemaDi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbOdemaActionPerformed

    private void cmbMualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMualActionPerformed
        TtglMual.setDate(new Date());
        cmbJam9.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt9.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk9.setSelectedIndex(0);
        if (cmbMual.getSelectedIndex() == 1) {
            TtglMual.setEnabled(true);
            cmbJam9.setEnabled(true);
            cmbMnt9.setEnabled(true);
            cmbDtk9.setEnabled(true);
            TtglMual.requestFocus();
        } else {
            TtglMual.setEnabled(false);
            cmbJam9.setEnabled(false);
            cmbMnt9.setEnabled(false);
            cmbDtk9.setEnabled(false);
        }
    }//GEN-LAST:event_cmbMualActionPerformed

    private void cmbJam9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam9MouseReleased
        AutoCompleteDecorator.decorate(cmbJam9);
    }//GEN-LAST:event_cmbJam9MouseReleased

    private void cmbMnt9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt9MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt9);
    }//GEN-LAST:event_cmbMnt9MouseReleased

    private void cmbDtk9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk9MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk9);
    }//GEN-LAST:event_cmbDtk9MouseReleased

    private void cmbMuntahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMuntahActionPerformed
        TtglMuntah.setDate(new Date());
        cmbJam10.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt10.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk10.setSelectedIndex(0);
        if (cmbMuntah.getSelectedIndex() == 1) {
            TtglMuntah.setEnabled(true);
            cmbJam10.setEnabled(true);
            cmbMnt10.setEnabled(true);
            cmbDtk10.setEnabled(true);
            TtglMuntah.requestFocus();
        } else {
            TtglMuntah.setEnabled(false);
            cmbJam10.setEnabled(false);
            cmbMnt10.setEnabled(false);
            cmbDtk10.setEnabled(false);
        }
    }//GEN-LAST:event_cmbMuntahActionPerformed

    private void cmbJam10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam10MouseReleased
        AutoCompleteDecorator.decorate(cmbJam10);
    }//GEN-LAST:event_cmbJam10MouseReleased

    private void cmbMnt10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt10MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt10);
    }//GEN-LAST:event_cmbMnt10MouseReleased

    private void cmbDtk10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk10MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk10);
    }//GEN-LAST:event_cmbDtk10MouseReleased

    private void cmbBatukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBatukActionPerformed
        TtglBatuk.setDate(new Date());
        cmbJam11.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt11.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk11.setSelectedIndex(0);
        if (cmbBatuk.getSelectedIndex() == 1) {
            TtglBatuk.setEnabled(true);
            cmbJam11.setEnabled(true);
            cmbMnt11.setEnabled(true);
            cmbDtk11.setEnabled(true);
            TtglBatuk.requestFocus();
        } else {
            TtglBatuk.setEnabled(false);
            cmbJam11.setEnabled(false);
            cmbMnt11.setEnabled(false);
            cmbDtk11.setEnabled(false);
        }
    }//GEN-LAST:event_cmbBatukActionPerformed

    private void cmbJam11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam11MouseReleased
        AutoCompleteDecorator.decorate(cmbJam11);
    }//GEN-LAST:event_cmbJam11MouseReleased

    private void cmbMnt11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt11MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt11);
    }//GEN-LAST:event_cmbMnt11MouseReleased

    private void cmbDtk11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk11MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk11);
    }//GEN-LAST:event_cmbDtk11MouseReleased

    private void cmbPilekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPilekActionPerformed
        TtglPilek.setDate(new Date());
        cmbJam12.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt12.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk12.setSelectedIndex(0);
        if (cmbPilek.getSelectedIndex() == 1) {
            TtglPilek.setEnabled(true);
            cmbJam12.setEnabled(true);
            cmbMnt12.setEnabled(true);
            cmbDtk12.setEnabled(true);
            TtglPilek.requestFocus();
        } else {
            TtglPilek.setEnabled(false);
            cmbJam12.setEnabled(false);
            cmbMnt12.setEnabled(false);
            cmbDtk12.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPilekActionPerformed

    private void cmbJam12MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam12MouseReleased
        AutoCompleteDecorator.decorate(cmbJam12);
    }//GEN-LAST:event_cmbJam12MouseReleased

    private void cmbMnt12MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt12MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt12);
    }//GEN-LAST:event_cmbMnt12MouseReleased

    private void cmbDtk12MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk12MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk12);
    }//GEN-LAST:event_cmbDtk12MouseReleased

    private void cmbDemamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDemamActionPerformed
        TtglDemam.setDate(new Date());
        cmbJam13.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt13.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk13.setSelectedIndex(0);
        if (cmbDemam.getSelectedIndex() == 1) {
            TtglDemam.setEnabled(true);
            cmbJam13.setEnabled(true);
            cmbMnt13.setEnabled(true);
            cmbDtk13.setEnabled(true);
            TtglDemam.requestFocus();
        } else {
            TtglDemam.setEnabled(false);
            cmbJam13.setEnabled(false);
            cmbMnt13.setEnabled(false);
            cmbDtk13.setEnabled(false);
        }
    }//GEN-LAST:event_cmbDemamActionPerformed

    private void cmbJam13MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam13MouseReleased
        AutoCompleteDecorator.decorate(cmbJam13);
    }//GEN-LAST:event_cmbJam13MouseReleased

    private void cmbMnt13MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt13MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt13);
    }//GEN-LAST:event_cmbMnt13MouseReleased

    private void cmbDtk13MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk13MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk13);
    }//GEN-LAST:event_cmbDtk13MouseReleased

    private void cmbRiwPerjalananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwPerjalananActionPerformed
        TketRiwPerjalanan.setText("");
        if (cmbRiwPerjalanan.getSelectedIndex() == 1) {
            TketRiwPerjalanan.setEnabled(true);
            TketRiwPerjalanan.requestFocus();
        } else {
            TketRiwPerjalanan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRiwPerjalananActionPerformed

    private void TketRiwPerjalananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketRiwPerjalananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbVaksin.requestFocus();
        }
    }//GEN-LAST:event_TketRiwPerjalananKeyPressed

    private void cmbVaksinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVaksinActionPerformed
        TketVaksin.setText("");
        if (cmbVaksin.getSelectedIndex() == 1) {
            TketVaksin.setEnabled(true);
            TketVaksin.requestFocus();
        } else {
            TketVaksin.setEnabled(false);
        }
    }//GEN-LAST:event_cmbVaksinActionPerformed

    private void TketVaksinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketVaksinKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPeriksa.requestFocus();
        }
    }//GEN-LAST:event_TketVaksinKeyPressed

    private void cmbPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPeriksaActionPerformed
        TketHasilPemeriksaan.setText("");
        if (cmbPeriksa.getSelectedIndex() == 1) {
            TketHasilPemeriksaan.setEnabled(true);
            TketHasilPemeriksaan.requestFocus();
        } else {
            TketHasilPemeriksaan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPeriksaActionPerformed

    private void TketHasilPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketHasilPemeriksaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbAnc.requestFocus();
        }
    }//GEN-LAST:event_TketHasilPemeriksaanKeyPressed

    private void cmbAncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAncActionPerformed
        cmbAncDi.setSelectedIndex(0);
        TjlhAnc.setText("");
        TnmDokter1.setText("");
        TnmDokter2.setText("");
        TnmDokter3.setText("");
        TjlhDokter1.setText("");
        TjlhDokter2.setText("");
        TjlhDokter3.setText("");
        if (cmbAnc.getSelectedIndex() == 1) {
            cmbAncDi.setEnabled(true);
            TjlhAnc.setEnabled(true);
            TnmDokter1.setEnabled(true);
            TnmDokter2.setEnabled(true);
            TnmDokter3.setEnabled(true);
            TjlhDokter1.setEnabled(true);
            TjlhDokter2.setEnabled(true);
            TjlhDokter3.setEnabled(true);
            BtnDokter1.setEnabled(true);
            BtnDokter2.setEnabled(true);
            BtnDokter3.setEnabled(true);
            cmbAncDi.requestFocus();
        } else {
            cmbAncDi.setEnabled(false);
            TjlhAnc.setEnabled(false);
            TnmDokter1.setEnabled(false);
            TnmDokter2.setEnabled(false);
            TnmDokter3.setEnabled(false);
            TjlhDokter1.setEnabled(false);
            TjlhDokter2.setEnabled(false);
            TjlhDokter3.setEnabled(false);
            BtnDokter1.setEnabled(false);
            BtnDokter2.setEnabled(false);
            BtnDokter3.setEnabled(false);
        }
    }//GEN-LAST:event_cmbAncActionPerformed

    private void TjlhAncKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjlhAncKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmDokter1.requestFocus();
        }
    }//GEN-LAST:event_TjlhAncKeyPressed

    private void TnmDokter1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmDokter1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjlhDokter1.requestFocus();
        }
    }//GEN-LAST:event_TnmDokter1KeyPressed

    private void BtnDokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter1ActionPerformed
        pilihan = 0;
        pilihan = 1;
        akses.setform("RMAsesmenAwalKebidanan1");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokter1ActionPerformed

    private void TjlhDokter1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjlhDokter1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmDokter2.requestFocus();
        }
    }//GEN-LAST:event_TjlhDokter1KeyPressed

    private void TnmDokter2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmDokter2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjlhDokter2.requestFocus();
        }
    }//GEN-LAST:event_TnmDokter2KeyPressed

    private void BtnDokter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter2ActionPerformed
        pilihan = 0;
        pilihan = 2;
        akses.setform("RMAsesmenAwalKebidanan1");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokter2ActionPerformed

    private void TjlhDokter2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjlhDokter2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmDokter3.requestFocus();
        }
    }//GEN-LAST:event_TjlhDokter2KeyPressed

    private void TnmDokter3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmDokter3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjlhDokter3.requestFocus();
        }
    }//GEN-LAST:event_TnmDokter3KeyPressed

    private void BtnDokter3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter3ActionPerformed
        pilihan = 0;
        pilihan = 3;
        akses.setform("RMAsesmenAwalKebidanan1");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokter3ActionPerformed

    private void TjlhDokter3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjlhDokter3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Thpht.requestFocus();
        }
    }//GEN-LAST:event_TjlhDokter3KeyPressed

    private void ThphtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThphtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Thpl.requestFocus();
        }
    }//GEN-LAST:event_ThphtKeyPressed

    private void ThplKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThplKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tuk.requestFocus();
        }
    }//GEN-LAST:event_ThplKeyPressed

    private void TukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TukKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TbbSebelum.requestFocus();
        }
    }//GEN-LAST:event_TukKeyPressed

    private void TbbSebelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbSebelumKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TbbTerakhir.requestFocus();
        }
    }//GEN-LAST:event_TbbSebelumKeyPressed

    private void TbbTerakhirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbTerakhirKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttbi.requestFocus();
        }
    }//GEN-LAST:event_TbbTerakhirKeyPressed

    private void TtbiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TumurPertama.requestFocus();
        }
    }//GEN-LAST:event_TtbiKeyPressed

    private void TumurPertamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurPertamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TlamaHaid.requestFocus();
        }
    }//GEN-LAST:event_TumurPertamaKeyPressed

    private void TlamaHaidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlamaHaidKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tberapa.requestFocus();
        }
    }//GEN-LAST:event_TlamaHaidKeyPressed

    private void TberapaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TberapaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbKeluhanWaktu.requestFocus();
        }
    }//GEN-LAST:event_TberapaKeyPressed

    private void cmbKeluhanWaktuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKeluhanWaktuActionPerformed
        chkDismen.setSelected(false);
        chkSpoting.setSelected(false);
        chkMenor.setSelected(false);
        chkMetro.setSelected(false);
        chkKeluhanLain.setSelected(false);
        TkeluhanLain.setText("");
        if (cmbKeluhanWaktu.getSelectedIndex() == 1) {
            chkDismen.setEnabled(true);
            chkSpoting.setEnabled(true);
            chkMenor.setEnabled(true);
            chkMetro.setEnabled(true);
            chkKeluhanLain.setEnabled(true);
            TkeluhanLain.setEnabled(false);
            chkDismen.requestFocus();
        } else {
            chkDismen.setEnabled(false);
            chkSpoting.setEnabled(false);
            chkMenor.setEnabled(false);
            chkMetro.setEnabled(false);
            chkKeluhanLain.setEnabled(false);
            TkeluhanLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbKeluhanWaktuActionPerformed

    private void TkeluhanLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeluhanLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbRiwPenDahulu.requestFocus();
        }
    }//GEN-LAST:event_TkeluhanLainKeyPressed

    private void chkKeluhanLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkKeluhanLainActionPerformed
        TkeluhanLain.setText("");
        if (chkKeluhanLain.isSelected() == true) {
            TkeluhanLain.setEnabled(true);
            TkeluhanLain.requestFocus();
        } else {
            TkeluhanLain.setEnabled(false);
        }
    }//GEN-LAST:event_chkKeluhanLainActionPerformed

    private void cmbRiwPenDahuluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwPenDahuluActionPerformed
        chkHipertensiDahulu.setSelected(false);
        chkDmDahulu.setSelected(false);
        chkJantungDahulu.setSelected(false);
        chkAsmaDahulu.setSelected(false);
        chkLainDahulu.setSelected(false);
        TlainDahulu.setText("");
        if (cmbRiwPenDahulu.getSelectedIndex() == 1) {
            chkHipertensiDahulu.setEnabled(true);
            chkDmDahulu.setEnabled(true);
            chkJantungDahulu.setEnabled(true);
            chkAsmaDahulu.setEnabled(true);
            chkLainDahulu.setEnabled(true);
            TlainDahulu.setEnabled(false);
            chkHipertensiDahulu.requestFocus();
        } else {
            chkHipertensiDahulu.setEnabled(false);
            chkDmDahulu.setEnabled(false);
            chkJantungDahulu.setEnabled(false);
            chkAsmaDahulu.setEnabled(false);
            chkLainDahulu.setEnabled(false);
            TlainDahulu.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRiwPenDahuluActionPerformed

    private void chkLainDahuluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainDahuluActionPerformed
        TlainDahulu.setText("");
        if (chkLainDahulu.isSelected() == true) {
            TlainDahulu.setEnabled(true);
            TlainDahulu.requestFocus();
        } else {
            TlainDahulu.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainDahuluActionPerformed

    private void TlainDahuluKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainDahuluKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbRiwPenKeluarga.requestFocus();
        }
    }//GEN-LAST:event_TlainDahuluKeyPressed

    private void cmbRiwPenKeluargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwPenKeluargaActionPerformed
        chkHipertensiKeluarga.setSelected(false);
        chkDmKeluarga.setSelected(false);
        chkJantungKeluarga.setSelected(false);
        chkAsmaKeluarga.setSelected(false);
        chkLainKeluarga.setSelected(false);
        TlainKeluarga.setText("");
        if (cmbRiwPenKeluarga.getSelectedIndex() == 1) {
            chkHipertensiKeluarga.setEnabled(true);
            chkDmKeluarga.setEnabled(true);
            chkJantungKeluarga.setEnabled(true);
            chkAsmaKeluarga.setEnabled(true);
            chkLainKeluarga.setEnabled(true);
            TlainKeluarga.setEnabled(false);
            chkHipertensiKeluarga.requestFocus();
        } else {
            chkHipertensiKeluarga.setEnabled(false);
            chkDmKeluarga.setEnabled(false);
            chkJantungKeluarga.setEnabled(false);
            chkAsmaKeluarga.setEnabled(false);
            chkLainKeluarga.setEnabled(false);
            TlainKeluarga.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRiwPenKeluargaActionPerformed

    private void chkLainKeluargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainKeluargaActionPerformed
        TlainKeluarga.setText("");
        if (chkLainKeluarga.isSelected() == true) {
            TlainKeluarga.setEnabled(true);
            TlainKeluarga.requestFocus();
        } else {
            TlainKeluarga.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainKeluargaActionPerformed

    private void TlainKeluargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainKeluargaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbRiwGinekologi.requestFocus();
        }
    }//GEN-LAST:event_TlainKeluargaKeyPressed

    private void cmbRiwGinekologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwGinekologiActionPerformed
        TriwGinekologi.setText("");
        if (cmbRiwGinekologi.getSelectedIndex() == 1) {
            TriwGinekologi.setEnabled(true);
            TriwGinekologi.requestFocus();
        } else {            
            TriwGinekologi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRiwGinekologiActionPerformed

    private void TriwGinekologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwGinekologiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkPil.requestFocus();
        }
    }//GEN-LAST:event_TriwGinekologiKeyPressed

    private void TlamaPilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlamaPilKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSatLamaPil.requestFocus();
        }
    }//GEN-LAST:event_TlamaPilKeyPressed

    private void chkPilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPilActionPerformed
        TlamaPil.setText("");
        cmbSatLamaPil.setSelectedIndex(0);
        if (chkPil.isSelected() == true) {
            TlamaPil.setEnabled(true);
            cmbSatLamaPil.setEnabled(true);
            TlamaPil.requestFocus();
        } else {
            TlamaPil.setEnabled(false);
            cmbSatLamaPil.setEnabled(false);
        }
    }//GEN-LAST:event_chkPilActionPerformed

    private void chkSuntik1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSuntik1ActionPerformed
        TlamaSuntik1.setText("");
        cmbSatLamaSuntik1.setSelectedIndex(0);
        if (chkSuntik1.isSelected() == true) {
            TlamaSuntik1.setEnabled(true);
            cmbSatLamaSuntik1.setEnabled(true);
            TlamaSuntik1.requestFocus();
        } else {
            TlamaSuntik1.setEnabled(false);
            cmbSatLamaSuntik1.setEnabled(false);
        }
    }//GEN-LAST:event_chkSuntik1ActionPerformed

    private void TlamaSuntik1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlamaSuntik1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSatLamaSuntik1.requestFocus();
        }
    }//GEN-LAST:event_TlamaSuntik1KeyPressed

    private void chkSuntik3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSuntik3ActionPerformed
        TlamaSuntik3.setText("");
        cmbSatLamaSuntik3.setSelectedIndex(0);
        if (chkSuntik3.isSelected() == true) {
            TlamaSuntik3.setEnabled(true);
            cmbSatLamaSuntik3.setEnabled(true);
            TlamaSuntik3.requestFocus();
        } else {
            TlamaSuntik3.setEnabled(false);
            cmbSatLamaSuntik3.setEnabled(false);
        }
    }//GEN-LAST:event_chkSuntik3ActionPerformed

    private void TlamaSuntik3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlamaSuntik3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSatLamaSuntik3.requestFocus();
        }
    }//GEN-LAST:event_TlamaSuntik3KeyPressed

    private void chkImplanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkImplanActionPerformed
        TlamaImplan.setText("");
        cmbSatLamaImplan.setSelectedIndex(0);
        if (chkImplan.isSelected() == true) {
            TlamaImplan.setEnabled(true);
            cmbSatLamaImplan.setEnabled(true);
            TlamaImplan.requestFocus();
        } else {
            TlamaImplan.setEnabled(false);
            cmbSatLamaImplan.setEnabled(false);
        }
    }//GEN-LAST:event_chkImplanActionPerformed

    private void TlamaImplanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlamaImplanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSatLamaImplan.requestFocus();
        }
    }//GEN-LAST:event_TlamaImplanKeyPressed

    private void chkIudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkIudActionPerformed
        TlamaIud.setText("");
        cmbSatLamaIud.setSelectedIndex(0);
        if (chkIud.isSelected() == true) {
            TlamaIud.setEnabled(true);
            cmbSatLamaIud.setEnabled(true);
            TlamaIud.requestFocus();
        } else {
            TlamaIud.setEnabled(false);
            cmbSatLamaIud.setEnabled(false);
        }
    }//GEN-LAST:event_chkIudActionPerformed

    private void TlamaIudKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlamaIudKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSatLamaIud.requestFocus();
        }
    }//GEN-LAST:event_TlamaIudKeyPressed

    private void TthnPartusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TthnPartusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtempatPartus.requestFocus();
        }
    }//GEN-LAST:event_TthnPartusKeyPressed

    private void TtempatPartusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtempatPartusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TumurHamil.requestFocus();
        }
    }//GEN-LAST:event_TtempatPartusKeyPressed

    private void TumurHamilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurHamilKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjnsPersalinan.requestFocus();
        }
    }//GEN-LAST:event_TumurHamilKeyPressed

    private void TjnsPersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjnsPersalinanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpenolong.requestFocus();
        }
    }//GEN-LAST:event_TjnsPersalinanKeyPressed

    private void TpenolongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpenolongKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpenyulit.requestFocus();
        }
    }//GEN-LAST:event_TpenolongKeyPressed

    private void TpenyulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpenyulitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJenkel.requestFocus();
        }
    }//GEN-LAST:event_TpenyulitKeyPressed

    private void TbrtLahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbrtLahirKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TkeadaanAnak.requestFocus();
        }
    }//GEN-LAST:event_TbrtLahirKeyPressed

    private void tbRiwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatMouseClicked
        if (tabModeRiwayat.getRowCount() != 0) {
            try {
                getDataRiwayat();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwayatMouseClicked

    private void tbRiwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatKeyPressed
        if (tabModeRiwayat.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRiwayat();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatKeyPressed

    private void BtnTambahRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahRiwayatActionPerformed
        TthnPartus.setText("");
        TtempatPartus.setText("");
        TumurHamil.setText("");
        TjnsPersalinan.setText("");
        Tpenolong.setText("");
        Tpenyulit.setText("");
        cmbJenkel.setSelectedIndex(0);
        TbrtLahir.setText("");
        TkeadaanAnak.setText("");
    }//GEN-LAST:event_BtnTambahRiwayatActionPerformed

    private void BtnSimpanRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanRiwayatActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (cmbJenkel.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu jns. kelaminnya dengan benar..!!");
            cmbJenkel.requestFocus();
        } else {
            tabModeRiwayat.addRow(new String[]{TNoRw.getText(), TthnPartus.getText(), TtempatPartus.getText(),
                TumurHamil.getText(), TjnsPersalinan.getText(), Tpenolong.getText(), Tpenyulit.getText(),
                cmbJenkel.getSelectedItem().toString(), TbrtLahir.getText(), TkeadaanAnak.getText(), Sequel.cariIsi("select now()")
            });
            BtnTambahRiwayatActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpanRiwayatActionPerformed

    private void BtnHapusRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusRiwayatActionPerformed
        if (tbRiwayat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data riwayat kehamilan, persalinan & nifas yang bisa dihapus..!!");
        } else {
            if (tbRiwayat.getSelectedRow() > -1) {
                tabModeRiwayat.removeRow(tbRiwayat.getSelectedRow());
                BtnTambahRiwayatActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel riwayat kehamilan, persalinan & nifas..!!");
                tbRiwayat.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusRiwayatActionPerformed

    private void BtnGantiRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiRiwayatActionPerformed
        if (tbRiwayat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data riwayat kehamilan, persalinan & nifas yang bisa diganti..!!");
        } else {
            if (tbRiwayat.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (cmbJenkel.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu jns. kelaminnya dengan benar..!!");
                    cmbJenkel.requestFocus();
                } else {
                    tabModeRiwayat.addRow(new String[]{TNoRw.getText(), TthnPartus.getText(), TtempatPartus.getText(),
                        TumurHamil.getText(), TjnsPersalinan.getText(), Tpenolong.getText(), Tpenyulit.getText(),
                        cmbJenkel.getSelectedItem().toString(), TbrtLahir.getText(), TkeadaanAnak.getText(), Sequel.cariIsi("select now()")
                    });

                    tabModeRiwayat.removeRow(tbRiwayat.getSelectedRow());
                    BtnTambahRiwayatActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel riwayat kehamilan, persalinan & nifas..!!");
                tbRiwayat.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiRiwayatActionPerformed

    private void chkIstriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkIstriActionPerformed
        cmbJlhIstri.setSelectedIndex(0);
        if (chkIstri.isSelected() == true) {
            cmbJlhIstri.setEnabled(true);
            cmbJlhIstri.requestFocus();
        } else {
            cmbJlhIstri.setEnabled(false);
        }
    }//GEN-LAST:event_chkIstriActionPerformed

    private void chkSuamiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSuamiActionPerformed
        cmbJlhSuami.setSelectedIndex(0);
        if (chkSuami.isSelected() == true) {
            cmbJlhSuami.setEnabled(true);
            cmbJlhSuami.requestFocus();
        } else {
            cmbJlhSuami.setEnabled(false);
        }
    }//GEN-LAST:event_chkSuamiActionPerformed

    private void TusiaPertamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TusiaPertamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TusiaPerkawinan.requestFocus();
        }
    }//GEN-LAST:event_TusiaPertamaKeyPressed

    private void TusiaPerkawinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TusiaPerkawinanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TklgTerdekat.requestFocus();
        }
    }//GEN-LAST:event_TusiaPerkawinanKeyPressed

    private void TklgTerdekatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TklgTerdekatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ThubKeluarga.requestFocus();
        }
    }//GEN-LAST:event_TklgTerdekatKeyPressed

    private void ThubKeluargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThubKeluargaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkOrtu.requestFocus();
        }
    }//GEN-LAST:event_ThubKeluargaKeyPressed

    private void TsttsLainEkonomiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsttsLainEkonomiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tleo1.requestFocus();
        }
    }//GEN-LAST:event_TsttsLainEkonomiKeyPressed

    private void chkSttsLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSttsLainActionPerformed
        TsttsLainEkonomi.setText("");
        if (chkSttsLain.isSelected() == true) {
            TsttsLainEkonomi.setEnabled(true);
            TsttsLainEkonomi.requestFocus();
        } else {
            TsttsLainEkonomi.setEnabled(false);
        }
    }//GEN-LAST:event_chkSttsLainActionPerformed

    private void Tleo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tleo1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tleo2.requestFocus();
        }
    }//GEN-LAST:event_Tleo1KeyPressed

    private void Tleo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tleo2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tleo3.requestFocus();
        }
    }//GEN-LAST:event_Tleo2KeyPressed

    private void Tleo3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tleo3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tleo4.requestFocus();
        }
    }//GEN-LAST:event_Tleo3KeyPressed

    private void Tleo4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tleo4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbBandle.requestFocus();
        }
    }//GEN-LAST:event_Tleo4KeyPressed

    private void TpalpasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpalpasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbTeraba.requestFocus();
        }
    }//GEN-LAST:event_TpalpasiKeyPressed

    private void TsebesarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsebesarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbGoyang.requestFocus();
        }
    }//GEN-LAST:event_TsebesarKeyPressed

    private void cmbTerabaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTerabaActionPerformed
        Tsebesar.setText("");
        if (cmbTeraba.getSelectedIndex() == 1) {
            Tsebesar.setEnabled(true);
            Tsebesar.requestFocus();
        } else {
            Tsebesar.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTerabaActionPerformed

    private void TvtPembukaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TvtPembukaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbVtNyeri.requestFocus();
        }
    }//GEN-LAST:event_TvtPembukaanKeyPressed

    private void TtfuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtfuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttaksiran.requestFocus();
        }
    }//GEN-LAST:event_TtfuKeyPressed

    private void TtaksiranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtaksiranKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ThisKontraksi.requestFocus();
        }
    }//GEN-LAST:event_TtaksiranKeyPressed

    private void ThisKontraksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThisKontraksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbHis.requestFocus();
        }
    }//GEN-LAST:event_ThisKontraksiKeyPressed

    private void TdurasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdurasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbDurasi.requestFocus();
        }
    }//GEN-LAST:event_TdurasiKeyPressed

    private void TauskultasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TauskultasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkBersih.requestFocus();
        }
    }//GEN-LAST:event_TauskultasiKeyPressed

    private void chkLainPemeriksaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainPemeriksaanActionPerformed
        TlainPemeriksaan.setText("");
        if (chkLainPemeriksaan.isSelected() == true) {
            TlainPemeriksaan.setEnabled(true);
            TlainPemeriksaan.requestFocus();
        } else {
            TlainPemeriksaan.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainPemeriksaanActionPerformed

    private void TlainPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainPemeriksaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TperiksaDalam.requestFocus();
        }
    }//GEN-LAST:event_TlainPemeriksaanKeyPressed

    private void TperiksaDalamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TperiksaDalamKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbInspekulo.requestFocus();
        }
    }//GEN-LAST:event_TperiksaDalamKeyPressed

    private void cmbInspekuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbInspekuloActionPerformed
        ThasilInspekulo.setText("");
        if (cmbInspekulo.getSelectedIndex() == 1) {
            ThasilInspekulo.setEnabled(true);
            ThasilInspekulo.requestFocus();
        } else {
            ThasilInspekulo.setEnabled(false);
        }
    }//GEN-LAST:event_cmbInspekuloActionPerformed

    private void ThasilInspekuloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThasilInspekuloKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdiagnosis.requestFocus();
        }
    }//GEN-LAST:event_ThasilInspekuloKeyPressed

    private void TdiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ticd.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosisKeyPressed

    private void TicdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TicdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tplaning.requestFocus();
        }
    }//GEN-LAST:event_TicdKeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        BtnPrintActionPerformed(null);
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        BtnHapusActionPerformed(null);
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnHalamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHalamanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu salah satu datanya pada tabel ....!!");
            TabRawat.setSelectedIndex(1);
            tampil();
        } else if (Sequel.cariInteger("select count(-1) from asesmen_awal_kebidanan1 where no_rawat='" + TNoRw.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan isi dan simpan dulu datanya utk. asesmen halaman 1 ....!!");
        } else {
            akses.setform("RMAsesmenAwalKebidanan1");
            halaman2.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            halaman2.setLocationRelativeTo(internalFrame1);
            halaman2.emptTeks();
            halaman2.isCek();
            halaman2.setData(TNoRw.getText(), stsrwt);
            halaman2.setVisible(true);
        }
    }//GEN-LAST:event_BtnHalamanActionPerformed

    private void BtnHalaman1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHalaman1ActionPerformed
        BtnHalamanActionPerformed(null);
    }//GEN-LAST:event_BtnHalaman1ActionPerformed

    private void cmbSttsPerkawinanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSttsPerkawinanActionPerformed
        chkIstri.setSelected(false);
        chkSuami.setSelected(false);
        cmbJlhIstri.setSelectedIndex(0);
        cmbJlhSuami.setSelectedIndex(0);
        if (cmbSttsPerkawinan.getSelectedIndex() == 0 || cmbSttsPerkawinan.getSelectedIndex() == 2) {
            chkIstri.setEnabled(false);
            chkSuami.setEnabled(false);
            cmbJlhIstri.setEnabled(false);
            cmbJlhSuami.setEnabled(false);
        } else {
            chkIstri.setEnabled(true);
            chkSuami.setEnabled(true);
            cmbJlhIstri.setEnabled(true);
            cmbJlhSuami.setEnabled(true);
            chkIstri.requestFocus();
        }
    }//GEN-LAST:event_cmbSttsPerkawinanActionPerformed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilPreview();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar2ActionPerformed

    private void BtnHalaman2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHalaman2ActionPerformed
        BtnHalamanActionPerformed(null);
    }//GEN-LAST:event_BtnHalaman2ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenAwalKebidanan1 dialog = new RMAsesmenAwalKebidanan1(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnDokter1;
    private widget.Button BtnDokter2;
    private widget.Button BtnDokter3;
    private widget.Button BtnEdit;
    private widget.Button BtnGantiRiwayat;
    private widget.Button BtnHalaman;
    private widget.Button BtnHalaman1;
    private widget.Button BtnHalaman2;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnHapusRiwayat;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar2;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpanRiwayat;
    private widget.Button BtnTambahRiwayat;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.editorpane LoadHTML1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll19;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Ta;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox TagamaPasien;
    private widget.TextBox TalamatPasien;
    private widget.TextBox TalamatSuami;
    private widget.TextBox TalasanMskRS;
    private widget.TextBox Tauskultasi;
    private widget.TextBox TbbSebelum;
    private widget.TextBox TbbTerakhir;
    private widget.TextBox Tberapa;
    private widget.TextBox TbrtLahir;
    private widget.TextBox Tdengan;
    private widget.TextBox Tdiagnosis;
    private widget.TextBox Tdurasi;
    private widget.TextBox Tgpapah;
    private widget.TextBox Tgr;
    private widget.TextBox Thamil;
    private widget.TextBox ThasilInspekulo;
    private widget.TextBox ThisKontraksi;
    private widget.TextBox Thpht;
    private widget.TextBox Thpl;
    private widget.TextBox ThubKeluarga;
    private widget.TextBox Ticd;
    private widget.TextBox TjlhAnc;
    private widget.TextBox TjlhDokter1;
    private widget.TextBox TjlhDokter2;
    private widget.TextBox TjlhDokter3;
    private widget.TextBox TjnsPersalinan;
    private widget.TextArea TkeadaanAnak;
    private widget.TextBox TkeluhanLain;
    private widget.TextBox Tkesadaran;
    private widget.TextArea TketHasilPemeriksaan;
    private widget.TextBox TketPkm;
    private widget.TextBox TketRiwPerjalanan;
    private widget.TextBox TketRsLain;
    private widget.TextBox TketRujukan;
    private widget.TextBox TketVaksin;
    private widget.TextBox TklgTerdekat;
    private widget.TextBox TlainDahulu;
    private widget.TextBox TlainKeluarga;
    private widget.TextBox TlainPemeriksaan;
    private widget.TextBox TlamaHaid;
    private widget.TextBox TlamaImplan;
    private widget.TextBox TlamaIud;
    private widget.TextBox TlamaPil;
    private widget.TextBox TlamaSuntik1;
    private widget.TextBox TlamaSuntik3;
    private widget.TextBox Tleo1;
    private widget.TextBox Tleo2;
    private widget.TextBox Tleo3;
    private widget.TextBox Tleo4;
    private widget.TextBox Tnadi;
    private widget.TextBox TnamaSuami;
    private widget.TextBox TnmDokter1;
    private widget.TextBox TnmDokter2;
    private widget.TextBox TnmDokter3;
    private widget.TextBox Tpalpasi;
    private widget.TextBox TpekerjaanPasien;
    private widget.TextBox TpekerjaanSuami;
    private widget.TextBox Tpenolong;
    private widget.TextBox Tpenyulit;
    private widget.TextBox Tpergerakan;
    private widget.TextBox TperiksaDalam;
    private widget.TextArea Tplaning;
    private widget.TextBox Tpr;
    private widget.TextBox Trespi;
    private widget.TextBox TrgRawat;
    private widget.TextBox TriwGinekologi;
    private widget.TextBox Tsaturasi;
    private widget.TextBox Tsebesar;
    private widget.TextBox TsttsLainEkonomi;
    private widget.TextBox Tsuhu;
    private widget.TextBox Ttaksiran;
    private widget.TextBox Ttbi;
    private widget.TextBox Ttd;
    private widget.TextBox TtempatPartus;
    private widget.TextBox Ttfu;
    private widget.Tanggal TtglAsesmen;
    private widget.Tanggal TtglBatuk;
    private widget.Tanggal TtglDarah;
    private widget.Tanggal TtglDemam;
    private widget.Tanggal TtglKeluar;
    private widget.Tanggal TtglKeluarAir;
    private widget.Tanggal TtglMual;
    private widget.Tanggal TtglMuntah;
    private widget.Tanggal TtglNyeriUlu;
    private widget.Tanggal TtglOdema;
    private widget.Tanggal TtglPandangan;
    private widget.Tanggal TtglPerut;
    private widget.Tanggal TtglPilek;
    private widget.Tanggal TtglPusing;
    private widget.TextBox TthnPartus;
    private widget.TextBox Tuk;
    private widget.TextBox TumurHamil;
    private widget.TextBox TumurPasien;
    private widget.TextBox TumurPertama;
    private widget.TextBox TumurSuami;
    private widget.TextBox TusiaPerkawinan;
    private widget.TextBox TusiaPertama;
    private widget.TextBox TvtPembukaan;
    private javax.swing.ButtonGroup buttonGroup1;
    public widget.CekBox chkAlamatSama;
    public widget.CekBox chkAnak;
    public widget.CekBox chkAsmaDahulu;
    public widget.CekBox chkAsmaKeluarga;
    public widget.CekBox chkAsuransi;
    public widget.CekBox chkBersih;
    public widget.CekBox chkBiaya;
    public widget.CekBox chkCandi;
    public widget.CekBox chkDismen;
    public widget.CekBox chkDmDahulu;
    public widget.CekBox chkDmKeluarga;
    public widget.CekBox chkHipertensiDahulu;
    public widget.CekBox chkHipertensiKeluarga;
    public widget.CekBox chkImplan;
    public widget.CekBox chkIstri;
    public widget.CekBox chkIud;
    public widget.CekBox chkJaminan;
    public widget.CekBox chkJantungDahulu;
    public widget.CekBox chkJantungKeluarga;
    public widget.CekBox chkKeluhanLain;
    public widget.CekBox chkLainDahulu;
    public widget.CekBox chkLainKeluarga;
    public widget.CekBox chkLainPemeriksaan;
    public widget.CekBox chkMenor;
    public widget.CekBox chkMetro;
    public widget.CekBox chkOedema;
    public widget.CekBox chkOrtu;
    public widget.CekBox chkPil;
    public widget.CekBox chkPkm;
    public widget.CekBox chkRsLain;
    public widget.CekBox chkRuftur;
    public widget.CekBox chkRujukan;
    public widget.CekBox chkSendiri;
    public widget.CekBox chkSpog;
    public widget.CekBox chkSpoting;
    public widget.CekBox chkSttsLain;
    public widget.CekBox chkSuami;
    public widget.CekBox chkSuntik1;
    public widget.CekBox chkSuntik3;
    public widget.CekBox chkTidakPernah;
    public widget.CekBox chkTinggalSendiri;
    public widget.CekBox chkTinggalSuami;
    private widget.ComboBox cmbAgamaSuami;
    private widget.ComboBox cmbAnc;
    private widget.ComboBox cmbAncDi;
    private widget.ComboBox cmbBandle;
    private widget.ComboBox cmbBatuk;
    private widget.ComboBox cmbCuriga;
    private widget.ComboBox cmbDarah;
    private widget.ComboBox cmbDemam;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk10;
    private widget.ComboBox cmbDtk11;
    private widget.ComboBox cmbDtk12;
    private widget.ComboBox cmbDtk13;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbDtk3;
    private widget.ComboBox cmbDtk4;
    private widget.ComboBox cmbDtk5;
    private widget.ComboBox cmbDtk6;
    private widget.ComboBox cmbDtk7;
    private widget.ComboBox cmbDtk8;
    private widget.ComboBox cmbDtk9;
    private widget.ComboBox cmbDurasi;
    private widget.ComboBox cmbGoyang;
    private widget.ComboBox cmbHis;
    private widget.ComboBox cmbInspekulo;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam10;
    private widget.ComboBox cmbJam11;
    private widget.ComboBox cmbJam12;
    private widget.ComboBox cmbJam13;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbJam3;
    private widget.ComboBox cmbJam4;
    private widget.ComboBox cmbJam5;
    private widget.ComboBox cmbJam6;
    private widget.ComboBox cmbJam7;
    private widget.ComboBox cmbJam8;
    private widget.ComboBox cmbJam9;
    private widget.ComboBox cmbJenkel;
    private widget.ComboBox cmbJlhIstri;
    private widget.ComboBox cmbJlhSuami;
    private widget.ComboBox cmbJnsDarah;
    private widget.ComboBox cmbJnsKeluarAir;
    private widget.ComboBox cmbJnsRujukan;
    private widget.ComboBox cmbKegiatan;
    private widget.ComboBox cmbKeluar;
    private widget.ComboBox cmbKeluarAir;
    private widget.ComboBox cmbKeluhanDarah;
    private widget.ComboBox cmbKeluhanKeluar;
    private widget.ComboBox cmbKeluhanKeluarAir;
    private widget.ComboBox cmbKeluhanPerut;
    private widget.ComboBox cmbKeluhanWaktu;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt10;
    private widget.ComboBox cmbMnt11;
    private widget.ComboBox cmbMnt12;
    private widget.ComboBox cmbMnt13;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbMnt3;
    private widget.ComboBox cmbMnt4;
    private widget.ComboBox cmbMnt5;
    private widget.ComboBox cmbMnt6;
    private widget.ComboBox cmbMnt7;
    private widget.ComboBox cmbMnt8;
    private widget.ComboBox cmbMnt9;
    private widget.ComboBox cmbMual;
    private widget.ComboBox cmbMuntah;
    private widget.ComboBox cmbNyeriTekan;
    private widget.ComboBox cmbNyeriUlu;
    private widget.ComboBox cmbOdema;
    private widget.ComboBox cmbOdemaDi;
    private widget.ComboBox cmbPandangan;
    private widget.ComboBox cmbPergerakan;
    private widget.ComboBox cmbPeriksa;
    private widget.ComboBox cmbPerut;
    private widget.ComboBox cmbPerutTegang;
    private widget.ComboBox cmbPilek;
    private widget.ComboBox cmbPusing;
    private widget.ComboBox cmbRiwGinekologi;
    private widget.ComboBox cmbRiwPenDahulu;
    private widget.ComboBox cmbRiwPenKeluarga;
    private widget.ComboBox cmbRiwPerjalanan;
    private widget.ComboBox cmbSatLamaImplan;
    private widget.ComboBox cmbSatLamaIud;
    private widget.ComboBox cmbSatLamaPil;
    private widget.ComboBox cmbSatLamaSuntik1;
    private widget.ComboBox cmbSatLamaSuntik3;
    private widget.ComboBox cmbSttsEmosional;
    private widget.ComboBox cmbSttsPerkawinan;
    private widget.ComboBox cmbTeraba;
    private widget.ComboBox cmbVaksin;
    private widget.ComboBox cmbVtNyeri;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel119;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel19;
    private widget.Label jLabel205;
    private widget.Label jLabel206;
    private widget.Label jLabel207;
    private widget.Label jLabel208;
    private widget.Label jLabel209;
    private widget.Label jLabel21;
    private widget.Label jLabel210;
    private widget.Label jLabel211;
    private widget.Label jLabel212;
    private widget.Label jLabel213;
    private widget.Label jLabel214;
    private widget.Label jLabel215;
    private widget.Label jLabel216;
    private widget.Label jLabel217;
    private widget.Label jLabel218;
    private widget.Label jLabel219;
    private widget.Label jLabel220;
    private widget.Label jLabel221;
    private widget.Label jLabel222;
    private widget.Label jLabel223;
    private widget.Label jLabel224;
    private widget.Label jLabel225;
    private widget.Label jLabel226;
    private widget.Label jLabel227;
    private widget.Label jLabel228;
    private widget.Label jLabel229;
    private widget.Label jLabel230;
    private widget.Label jLabel231;
    private widget.Label jLabel232;
    private widget.Label jLabel233;
    private widget.Label jLabel234;
    private widget.Label jLabel235;
    private widget.Label jLabel236;
    private widget.Label jLabel237;
    private widget.Label jLabel238;
    private widget.Label jLabel239;
    private widget.Label jLabel240;
    private widget.Label jLabel241;
    private widget.Label jLabel242;
    private widget.Label jLabel243;
    private widget.Label jLabel244;
    private widget.Label jLabel245;
    private widget.Label jLabel246;
    private widget.Label jLabel247;
    private widget.Label jLabel248;
    private widget.Label jLabel249;
    private widget.Label jLabel250;
    private widget.Label jLabel251;
    private widget.Label jLabel252;
    private widget.Label jLabel253;
    private widget.Label jLabel254;
    private widget.Label jLabel255;
    private widget.Label jLabel256;
    private widget.Label jLabel257;
    private widget.Label jLabel258;
    private widget.Label jLabel259;
    private widget.Label jLabel260;
    private widget.Label jLabel261;
    private widget.Label jLabel262;
    private widget.Label jLabel263;
    private widget.Label jLabel264;
    private widget.Label jLabel265;
    private widget.Label jLabel266;
    private widget.Label jLabel267;
    private widget.Label jLabel268;
    private widget.Label jLabel269;
    private widget.Label jLabel270;
    private widget.Label jLabel271;
    private widget.Label jLabel272;
    private widget.Label jLabel273;
    private widget.Label jLabel274;
    private widget.Label jLabel275;
    private widget.Label jLabel276;
    private widget.Label jLabel277;
    private widget.Label jLabel278;
    private widget.Label jLabel279;
    private widget.Label jLabel280;
    private widget.Label jLabel281;
    private widget.Label jLabel282;
    private widget.Label jLabel283;
    private widget.Label jLabel284;
    private widget.Label jLabel285;
    private widget.Label jLabel286;
    private widget.Label jLabel287;
    private widget.Label jLabel288;
    private widget.Label jLabel289;
    private widget.Label jLabel290;
    private widget.Label jLabel291;
    private widget.Label jLabel292;
    private widget.Label jLabel293;
    private widget.Label jLabel294;
    private widget.Label jLabel295;
    private widget.Label jLabel296;
    private widget.Label jLabel297;
    private widget.Label jLabel298;
    private widget.Label jLabel299;
    private widget.Label jLabel300;
    private widget.Label jLabel301;
    private widget.Label jLabel302;
    private widget.Label jLabel303;
    private widget.Label jLabel304;
    private widget.Label jLabel305;
    private widget.Label jLabel306;
    private widget.Label jLabel307;
    private widget.Label jLabel308;
    private widget.Label jLabel309;
    private widget.Label jLabel310;
    private widget.Label jLabel311;
    private widget.Label jLabel312;
    private widget.Label jLabel313;
    private widget.Label jLabel314;
    private widget.Label jLabel315;
    private widget.Label jLabel316;
    private widget.Label jLabel317;
    private widget.Label jLabel318;
    private widget.Label jLabel319;
    private widget.Label jLabel320;
    private widget.Label jLabel321;
    private widget.Label jLabel322;
    private widget.Label jLabel323;
    private widget.Label jLabel324;
    private widget.Label jLabel325;
    private widget.Label jLabel326;
    private widget.Label jLabel327;
    private widget.Label jLabel328;
    private widget.Label jLabel329;
    private widget.Label jLabel330;
    private widget.Label jLabel331;
    private widget.Label jLabel332;
    private widget.Label jLabel333;
    private widget.Label jLabel334;
    private widget.Label jLabel335;
    private widget.Label jLabel336;
    private widget.Label jLabel337;
    private widget.Label jLabel338;
    private widget.Label jLabel339;
    private widget.Label jLabel340;
    private widget.Label jLabel341;
    private widget.Label jLabel342;
    private widget.Label jLabel343;
    private widget.Label jLabel344;
    private widget.Label jLabel345;
    private widget.Label jLabel346;
    private widget.Label jLabel347;
    private widget.Label jLabel348;
    private widget.Label jLabel349;
    private widget.Label jLabel350;
    private widget.Label jLabel351;
    private widget.Label jLabel352;
    private widget.Label jLabel353;
    private widget.Label jLabel354;
    private widget.Label jLabel355;
    private widget.Label jLabel356;
    private widget.Label jLabel357;
    private widget.Label jLabel358;
    private widget.Label jLabel359;
    private widget.Label jLabel360;
    private widget.Label jLabel361;
    private widget.Label jLabel362;
    private widget.Label jLabel363;
    private widget.Label jLabel364;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.panelisi panelGlass2;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelTombol;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbAsesmen;
    private widget.Table tbRiwayat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT ak1.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tglLahir, DATE_FORMAT(ak1.tgl_asesmen,'%d-%m-%Y') tglAses, "
                    + "concat(rp.umurdaftar,' ',rp.sttsumur) umurPas, p.pekerjaan, p.agama, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamatPas, "
                    + "p.stts_nikah, ifnull(pg1.nama,'-') nmBidan, ifnull(pg2.nama,'-') nmDokter, ifnull(ak2.status_rawat,'-') status_rawat FROM asesmen_awal_kebidanan1 ak1 "
                    + "inner join reg_periksa rp on rp.no_rawat=ak1.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join kelurahan kl on kl.kd_kel=p.kd_kel "
                    + "inner join kecamatan kc on kc.kd_kec=p.kd_kec inner join kabupaten kb on kb.kd_kab=p.kd_kab "
                    + "left join asesmen_awal_kebidanan2 ak2 on ak2.no_rawat=ak1.no_rawat left join pegawai pg1 on pg1.nik=ak2.nip_bidan_dp left join pegawai pg2 on pg2.nik=ak2.nip_dokter where "
                    + "ak1.tgl_asesmen between ? and ? and ak1.no_rawat like ? or "
                    + "ak1.tgl_asesmen between ? and ? and p.no_rkm_medis like ? or "
                    + "ak1.tgl_asesmen between ? and ? and p.nm_pasien like ? or "
                    + "ak1.tgl_asesmen between ? and ? and concat(rp.umurdaftar,' ',rp.sttsumur) like ? or "
                    + "ak1.tgl_asesmen between ? and ? and p.pekerjaan like ? or "
                    + "ak1.tgl_asesmen between ? and ? and p.agama like ? or "
                    + "ak1.tgl_asesmen between ? and ? and concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) like ? or "
                    + "ak1.tgl_asesmen between ? and ? and p.stts_nikah like ? or "
                    + "ak1.tgl_asesmen between ? and ? and pg1.nama like ? or "
                    + "ak1.tgl_asesmen between ? and ? and pg2.nama like ? order by ak1.tgl_asesmen desc");         
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
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglLahir"),                        
                        rs.getString("ruang_rawat"),                        
                        rs.getString("tglAses"),
                        rs.getString("umurPas"),
                        rs.getString("pekerjaan"),
                        rs.getString("agama"),
                        rs.getString("alamatPas"),
                        rs.getString("stts_nikah"),
                        rs.getString("nmBidan"),
                        rs.getString("nmDokter"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("jam_asesmen"),
                        rs.getString("nm_suami"),
                        rs.getString("umur_suami"),
                        rs.getString("pekerjaan_suami"),
                        rs.getString("alamat_suami"),
                        rs.getString("agama_suami"),
                        rs.getString("alasan_masuk"),
                        rs.getString("td"),
                        rs.getString("nadi"),
                        rs.getString("respirasi"),
                        rs.getString("suhu"),
                        rs.getString("kesadaran"),
                        rs.getString("saturasi"),
                        rs.getString("cek_sendiri"),
                        rs.getString("cek_rujukan"),
                        rs.getString("jns_rujukan"),
                        rs.getString("ket_jns_rujukan"),
                        rs.getString("cek_pkm"),
                        rs.getString("ket_pkm"),
                        rs.getString("cek_spog"),
                        rs.getString("cek_rs_lain"),
                        rs.getString("ket_rs_lain"),
                        rs.getString("gr"),
                        rs.getString("pr"),
                        rs.getString("a"),
                        rs.getString("hamil"),
                        rs.getString("gpapah"),
                        rs.getString("dengan"),
                        rs.getString("perut"),
                        rs.getString("keluhan_perut"),
                        rs.getString("tgl_perut"),
                        rs.getString("jam_perut"),
                        rs.getString("keluar"),
                        rs.getString("keluhan_keluar"),
                        rs.getString("tgl_keluar_lendir"),
                        rs.getString("jam_keluar_lendir"),
                        rs.getString("darah"),
                        rs.getString("keluhan_darah"),
                        rs.getString("jns_darah"),
                        rs.getString("tgl_darah"),
                        rs.getString("jam_darah"),
                        rs.getString("keluar_air"),
                        rs.getString("keluhan_keluar_air"),
                        rs.getString("jns_keluar_air"),
                        rs.getString("tgl_keluar_air"),
                        rs.getString("jam_keluar_air"),
                        rs.getString("pergerakan_janin_2jam_terakhir"),
                        rs.getString("ket_pergerakan_janin_2jam_terakhir"),
                        rs.getString("pusing"),
                        rs.getString("tgl_pusing"),
                        rs.getString("jam_pusing"),
                        rs.getString("nyeri_ulu_hati"),
                        rs.getString("tgl_nyeri_ulu_hati"),
                        rs.getString("jam_nyeri_ulu_hati"),
                        rs.getString("pandangan_kabur"),
                        rs.getString("tgl_pandangan_kabur"),
                        rs.getString("jam_pandangan_kabur"),
                        rs.getString("odema"),
                        rs.getString("tgl_odema"),
                        rs.getString("odema_di"),
                        rs.getString("mual"),
                        rs.getString("tgl_mual"),
                        rs.getString("jam_mual"),
                        rs.getString("muntah"),
                        rs.getString("tgl_muntah"),
                        rs.getString("jam_muntah"),
                        rs.getString("batuk"),
                        rs.getString("tgl_batuk"),
                        rs.getString("jam_batuk"),
                        rs.getString("pilek"),
                        rs.getString("tgl_pilek"),
                        rs.getString("jam_pilek"),
                        rs.getString("demam"),
                        rs.getString("tgl_demam"),
                        rs.getString("jam_demam"),
                        rs.getString("riw_perjalanan_jauh"),
                        rs.getString("ket_riw_perjalanan_jauh"),
                        rs.getString("vaksin_covid19"),
                        rs.getString("jlh_vaksin_covid19"),
                        rs.getString("periksa_ketempat_bidan"),
                        rs.getString("hasil_pemeriksaan_bidan"),
                        rs.getString("ibu_anc"),
                        rs.getString("jns_anc"),
                        rs.getString("jlh_anc"),
                        rs.getString("dengan_dokter1"),
                        rs.getString("jlh_dengan_dokter1"),
                        rs.getString("dengan_dokter2"),
                        rs.getString("jlh_dengan_dokter2"),
                        rs.getString("dengan_dokter3"),
                        rs.getString("jlh_dengan_dokter3"),
                        rs.getString("hpht"),
                        rs.getString("hpl"),
                        rs.getString("uk"),
                        rs.getString("bb_sebelum_hamil"),
                        rs.getString("bb_terakhir"),
                        rs.getString("tbi"),
                        rs.getString("umur_pertama_haid"),
                        rs.getString("lama_haid"),
                        rs.getString("berapa_kali_ganti_pembalut"),
                        rs.getString("keluhan_waktu_haid"),
                        rs.getString("cek_dismen"),
                        rs.getString("cek_spoting"),
                        rs.getString("cek_menor"),
                        rs.getString("cek_metro"),
                        rs.getString("cek_lain_keluhan_haid"),
                        rs.getString("ket_lain_keluhan_haid"),
                        rs.getString("riw_penyakit_dahulu"),
                        rs.getString("cek_hipertensi_dahulu"),
                        rs.getString("cek_dm_dahulu"),
                        rs.getString("cek_jantung_dahulu"),
                        rs.getString("cek_asma_dahulu"),
                        rs.getString("cek_lainya_dahulu"),
                        rs.getString("ket_lain_penyakit_dahulu"),
                        rs.getString("riw_penyakit_keluarga"),
                        rs.getString("cek_hipertensi_keluarga"),
                        rs.getString("cek_dm_keluarga"),
                        rs.getString("cek_jantung_keluarga"),
                        rs.getString("cek_asma_keluarga"),
                        rs.getString("cek_lainya_keluarga"),
                        rs.getString("ket_lain_penyakit_keluarga"),
                        rs.getString("riw_ginekologi"),
                        rs.getString("ket_ginekologi"),
                        rs.getString("cek_pil"),
                        rs.getString("lama_pil"),
                        rs.getString("satuan_lama_pil"),
                        rs.getString("cek_suntik1"),
                        rs.getString("lama_suntik1"),
                        rs.getString("satuan_lama_suntik1"),
                        rs.getString("cek_suntik3"),
                        rs.getString("lama_suntik3"),
                        rs.getString("satuan_lama_suntik3"),
                        rs.getString("cek_implan"),
                        rs.getString("lama_implan"),
                        rs.getString("satuan_lama_implan"),
                        rs.getString("cek_iud"),
                        rs.getString("lama_iud"),
                        rs.getString("satuan_lama_iud"),
                        rs.getString("cek_tidak_kb"),
                        rs.getString("status_perkawinan"),
                        rs.getString("cek_istri_kawin"),
                        rs.getString("cek_suami_kawin"),
                        rs.getString("jlh_perkawinan_istri"),
                        rs.getString("jlh_perkawinan_suami"),
                        rs.getString("usia_pertama_nikah"),
                        rs.getString("usia_perkawinan"),
                        rs.getString("keluarga_terdekat"),
                        rs.getString("hubungan"),
                        rs.getString("cek_orang_tua"),
                        rs.getString("cek_suami"),
                        rs.getString("cek_anak"),
                        rs.getString("cek_tinggal_sendiri"),
                        rs.getString("curiga_penganiayaan"),
                        rs.getString("kegiatan_ibadah"),
                        rs.getString("status_emosional"),
                        rs.getString("cek_asuransi"),
                        rs.getString("cek_jaminan"),
                        rs.getString("cek_biaya_sendiri"),
                        rs.getString("cek_lain_status_ekonomi"),
                        rs.getString("ket_lain_status_ekonomi"),
                        rs.getString("leopold1"),
                        rs.getString("leopold2"),
                        rs.getString("leopold3"),
                        rs.getString("leopold4"),
                        rs.getString("bandle_ring"),
                        rs.getString("perut_tegang"),
                        rs.getString("palpasi"),
                        rs.getString("teraba_massa"),
                        rs.getString("sebesar"),
                        rs.getString("goyang"),
                        rs.getString("nyeri_tekan"),
                        rs.getString("vt_pembukaan"),
                        rs.getString("vt_nyeri_goyang"),
                        rs.getString("tfu"),
                        rs.getString("taksiran_berat_janin"),
                        rs.getString("his_kontraksi"),
                        rs.getString("jns_his_kontraksi"),
                        rs.getString("durasi"),
                        rs.getString("jns_durasi"),
                        rs.getString("auskultasi"),
                        rs.getString("cek_bersih"),
                        rs.getString("cek_oedema"),
                        rs.getString("cek_ruftur"),
                        rs.getString("cek_candiloma"),
                        rs.getString("cek_lain_pemeriksaan_geni"),
                        rs.getString("ket_lain_pemeriksaan_geni"),
                        rs.getString("periksa_dalam_obstetri"),
                        rs.getString("inspekulo"),
                        rs.getString("hasil_inspekulo"),
                        rs.getString("diagnosis_sementara"),
                        rs.getString("icd_10"),
                        rs.getString("planing"),
                        rs.getString("waktu_simpan"),
                        rs.getString("status_rawat")
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
        TtglAsesmen.setDate(new Date());
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        TnamaSuami.setText("");
        TumurSuami.setText("");
        TpekerjaanSuami.setText("");
        TalamatSuami.setText("");
        chkAlamatSama.setSelected(false);
        cmbAgamaSuami.setSelectedIndex(0);
        TalasanMskRS.setText("");
        Ttd.setText("");
        Tnadi.setText("");
        Trespi.setText("");
        Tsuhu.setText("");
        Tsaturasi.setText("");
        Tkesadaran.setText("");
        chkSendiri.setSelected(false);
        chkRujukan.setSelected(false);        
        cmbJnsRujukan.setSelectedIndex(0);
        TketRujukan.setText("");
        cmbJnsRujukan.setEnabled(false);
        TketRujukan.setEnabled(false);
        chkSpog.setSelected(false);
        chkPkm.setSelected(false);
        TketPkm.setText("");
        TketPkm.setEnabled(false);
        chkRsLain.setSelected(false);
        TketRsLain.setText("");
        TketRsLain.setEnabled(false);
        buttonGroup1.clearSelection();
        Tgr.setText("");
        Tpr.setText("");
        Ta.setText("");
        Thamil.setText("");
        Tgpapah.setText("");
        Tdengan.setText("");
        
        cmbPerut.setSelectedIndex(0);
        cmbKeluhanPerut.setSelectedIndex(0);
        TtglPerut.setDate(new Date());
        cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk2.setSelectedIndex(0);
        cmbKeluhanPerut.setEnabled(false);
        TtglPerut.setEnabled(false);
        cmbJam2.setEnabled(false);
        cmbMnt2.setEnabled(false);
        cmbDtk2.setEnabled(false);
        
        cmbKeluar.setSelectedIndex(0);
        cmbKeluhanKeluar.setSelectedIndex(0);
        TtglKeluar.setDate(new Date());
        cmbJam3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk3.setSelectedIndex(0);
        cmbKeluhanKeluar.setEnabled(false);
        TtglKeluar.setEnabled(false);
        cmbJam3.setEnabled(false);
        cmbMnt3.setEnabled(false);
        cmbDtk3.setEnabled(false);
        
        cmbDarah.setSelectedIndex(0);
        cmbKeluhanDarah.setSelectedIndex(0);
        cmbJnsDarah.setSelectedIndex(0);
        TtglDarah.setDate(new Date());
        cmbJam4.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt4.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk4.setSelectedIndex(0);
        cmbKeluhanDarah.setEnabled(false);
        cmbJnsDarah.setEnabled(false);
        TtglDarah.setEnabled(false);
        cmbJam4.setEnabled(false);
        cmbMnt4.setEnabled(false);
        cmbDtk4.setEnabled(false);
        
        cmbKeluarAir.setSelectedIndex(0);
        cmbKeluhanKeluarAir.setSelectedIndex(0);
        cmbJnsKeluarAir.setSelectedIndex(0);
        TtglKeluarAir.setDate(new Date());
        cmbJam5.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt5.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk5.setSelectedIndex(0);
        cmbKeluhanKeluarAir.setEnabled(false);
        cmbJnsKeluarAir.setEnabled(false);
        TtglKeluarAir.setEnabled(false);
        cmbJam5.setEnabled(false);
        cmbMnt5.setEnabled(false);
        cmbDtk5.setEnabled(false);
        
        cmbPergerakan.setSelectedIndex(0);
        Tpergerakan.setText("");
        Tpergerakan.setEnabled(false);
        
        cmbPusing.setSelectedIndex(0);
        TtglPusing.setDate(new Date());
        cmbJam6.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt6.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk6.setSelectedIndex(0);
        TtglPusing.setEnabled(false);
        cmbJam6.setEnabled(false);
        cmbMnt6.setEnabled(false);
        cmbDtk6.setEnabled(false);
        
        cmbNyeriUlu.setSelectedIndex(0);
        TtglNyeriUlu.setDate(new Date());
        cmbJam7.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt7.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk7.setSelectedIndex(0);
        TtglNyeriUlu.setEnabled(false);
        cmbJam7.setEnabled(false);
        cmbMnt7.setEnabled(false);
        cmbDtk7.setEnabled(false);
        
        cmbPandangan.setSelectedIndex(0);
        TtglPandangan.setDate(new Date());
        cmbJam8.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt8.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk8.setSelectedIndex(0);
        TtglPandangan.setEnabled(false);
        cmbJam8.setEnabled(false);
        cmbMnt8.setEnabled(false);
        cmbDtk8.setEnabled(false);
        
        cmbOdema.setSelectedIndex(0);
        TtglOdema.setDate(new Date());
        cmbOdemaDi.setSelectedIndex(0);
        TtglOdema.setEnabled(false);
        cmbOdemaDi.setEnabled(false);
        
        cmbMual.setSelectedIndex(0);
        TtglMual.setDate(new Date());
        cmbJam9.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt9.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk9.setSelectedIndex(0);
        TtglMual.setEnabled(false);
        cmbJam9.setEnabled(false);
        cmbMnt9.setEnabled(false);
        cmbDtk9.setEnabled(false);

        cmbMuntah.setSelectedIndex(0);
        TtglMuntah.setDate(new Date());
        cmbJam10.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt10.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk10.setSelectedIndex(0);
        TtglMuntah.setEnabled(false);
        cmbJam10.setEnabled(false);
        cmbMnt10.setEnabled(false);
        cmbDtk10.setEnabled(false);
        
        cmbBatuk.setSelectedIndex(0);
        TtglBatuk.setDate(new Date());
        cmbJam11.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt11.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk11.setSelectedIndex(0);
        TtglBatuk.setEnabled(false);
        cmbJam11.setEnabled(false);
        cmbMnt11.setEnabled(false);
        cmbDtk11.setEnabled(false);
        
        cmbPilek.setSelectedIndex(0);
        TtglPilek.setDate(new Date());
        cmbJam12.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt12.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk12.setSelectedIndex(0);
        TtglPilek.setEnabled(false);
        cmbJam12.setEnabled(false);
        cmbMnt12.setEnabled(false);
        cmbDtk12.setEnabled(false);
        
        cmbDemam.setSelectedIndex(0);
        TtglDemam.setDate(new Date());
        cmbJam13.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt13.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk13.setSelectedIndex(0);
        TtglDemam.setEnabled(false);
        cmbJam13.setEnabled(false);
        cmbMnt13.setEnabled(false);
        cmbDtk13.setEnabled(false);
        
        cmbRiwPerjalanan.setSelectedIndex(0);
        TketRiwPerjalanan.setText("");
        TketRiwPerjalanan.setEnabled(false);
        
        cmbVaksin.setSelectedIndex(0);
        TketVaksin.setText("");
        TketVaksin.setEnabled(false);
        
        cmbPeriksa.setSelectedIndex(0);
        TketHasilPemeriksaan.setText("");
        TketHasilPemeriksaan.setEnabled(false);
    
        cmbAnc.setSelectedIndex(0);
        cmbAncDi.setSelectedIndex(0);
        TjlhAnc.setText("");
        TnmDokter1.setText("");
        TnmDokter2.setText("");
        TnmDokter3.setText("");
        TjlhDokter1.setText("");
        TjlhDokter2.setText("");
        TjlhDokter3.setText("");
        cmbAncDi.setEnabled(false);
        TjlhAnc.setEnabled(false);
        TnmDokter1.setEnabled(false);
        TnmDokter2.setEnabled(false);
        TnmDokter3.setEnabled(false);
        TjlhDokter1.setEnabled(false);
        TjlhDokter2.setEnabled(false);
        TjlhDokter3.setEnabled(false);
        BtnDokter1.setEnabled(false);
        BtnDokter2.setEnabled(false);
        BtnDokter3.setEnabled(false);
        
        Thpht.setText("");
        Thpl.setText("");
        Tuk.setText("");
        TbbSebelum.setText("");
        TbbTerakhir.setText("");
        Ttbi.setText("");
        TumurPertama.setText("");
        TlamaHaid.setText("");
        Tberapa.setText("");
        
        cmbKeluhanWaktu.setSelectedIndex(0);
        chkDismen.setSelected(false);
        chkSpoting.setSelected(false);
        chkMenor.setSelected(false);
        chkMetro.setSelected(false);
        chkKeluhanLain.setSelected(false);
        TkeluhanLain.setText("");
        chkDismen.setEnabled(false);
        chkSpoting.setEnabled(false);
        chkMenor.setEnabled(false);
        chkMetro.setEnabled(false);
        chkKeluhanLain.setEnabled(false);
        TkeluhanLain.setEnabled(false);
        
        cmbRiwPenDahulu.setSelectedIndex(0);
        chkHipertensiDahulu.setSelected(false);
        chkDmDahulu.setSelected(false);
        chkJantungDahulu.setSelected(false);
        chkAsmaDahulu.setSelected(false);
        chkLainDahulu.setSelected(false);
        TlainDahulu.setText("");
        chkHipertensiDahulu.setEnabled(false);
        chkDmDahulu.setEnabled(false);
        chkJantungDahulu.setEnabled(false);
        chkAsmaDahulu.setEnabled(false);
        chkLainDahulu.setEnabled(false);
        TlainDahulu.setEnabled(false);
        
        cmbRiwPenKeluarga.setSelectedIndex(0);
        chkHipertensiKeluarga.setSelected(false);
        chkDmKeluarga.setSelected(false);
        chkJantungKeluarga.setSelected(false);
        chkAsmaKeluarga.setSelected(false);
        chkLainKeluarga.setSelected(false);
        TlainKeluarga.setText("");
        chkHipertensiKeluarga.setEnabled(false);
        chkDmKeluarga.setEnabled(false);
        chkJantungKeluarga.setEnabled(false);
        chkAsmaKeluarga.setEnabled(false);
        chkLainKeluarga.setEnabled(false);
        TlainKeluarga.setEnabled(false);
        
        cmbRiwGinekologi.setSelectedIndex(0);
        TriwGinekologi.setText("");
        TriwGinekologi.setEnabled(false);
        
        chkPil.setSelected(false);
        TlamaPil.setText("");
        cmbSatLamaPil.setSelectedIndex(0);
        TlamaPil.setEnabled(false);
        cmbSatLamaPil.setEnabled(false);
        
        chkSuntik1.setSelected(false);
        TlamaSuntik1.setText("");
        cmbSatLamaSuntik1.setSelectedIndex(0);
        TlamaSuntik1.setEnabled(false);
        cmbSatLamaSuntik1.setEnabled(false);
        
        chkSuntik3.setSelected(false);
        TlamaSuntik3.setText("");
        cmbSatLamaSuntik3.setSelectedIndex(0);
        TlamaSuntik3.setEnabled(false);
        cmbSatLamaSuntik3.setEnabled(false);
        
        chkImplan.setSelected(false);
        TlamaImplan.setText("");
        cmbSatLamaImplan.setSelectedIndex(0);
        TlamaImplan.setEnabled(false);
        cmbSatLamaImplan.setEnabled(false);
        
        chkIud.setSelected(false);
        TlamaIud.setText("");
        cmbSatLamaIud.setSelectedIndex(0);
        TlamaIud.setEnabled(false);
        cmbSatLamaIud.setEnabled(false);
        
        chkTidakPernah.setSelected(false);
        
        BtnTambahRiwayatActionPerformed(null);
        Valid.tabelKosong(tabModeRiwayat);
        
        cmbSttsPerkawinan.setSelectedIndex(0);
        chkIstri.setSelected(false);
        chkIstri.setEnabled(false);
        cmbJlhIstri.setSelectedIndex(0);
        cmbJlhIstri.setEnabled(false);
        
        chkSuami.setSelected(false);
        chkSuami.setEnabled(false);
        cmbJlhSuami.setSelectedIndex(0);
        cmbJlhSuami.setEnabled(false);
        
        TusiaPertama.setText("");
        TusiaPerkawinan.setText("");
        TklgTerdekat.setText("");
        ThubKeluarga.setText("");
        
        chkOrtu.setSelected(false);
        chkTinggalSuami.setSelected(false);
        chkAnak.setSelected(false);
        chkTinggalSendiri.setSelected(false);        
        cmbCuriga.setSelectedIndex(0);        
        cmbKegiatan.setSelectedIndex(0);
        cmbSttsEmosional.setSelectedIndex(0);
        
        chkAsuransi.setSelected(false);
        chkJaminan.setSelected(false);
        chkBiaya.setSelected(false);
        chkSttsLain.setSelected(false);
        TsttsLainEkonomi.setText("");
        TsttsLainEkonomi.setEnabled(false);
        
        Tleo1.setText("");        
        Tleo2.setText("");
        Tleo3.setText("");
        Tleo4.setText("");
        cmbBandle.setSelectedIndex(0);
        cmbPerutTegang.setSelectedIndex(0);
        
        Tpalpasi.setText("");
        cmbTeraba.setSelectedIndex(0);
        Tsebesar.setText("");
        Tsebesar.setEnabled(false);
        cmbGoyang.setSelectedIndex(0);
        cmbNyeriTekan.setSelectedIndex(0);
        TvtPembukaan.setText("");
        cmbVtNyeri.setSelectedIndex(0);
        Ttfu.setText("");
        Ttaksiran.setText("");
        ThisKontraksi.setText("");
        cmbHis.setSelectedIndex(0);
        Tdurasi.setText("");
        cmbDurasi.setSelectedIndex(0);
        Tauskultasi.setText("");
        
        chkBersih.setSelected(false);
        chkOedema.setSelected(false);
        chkRuftur.setSelected(false);
        chkCandi.setSelected(false);
        chkLainPemeriksaan.setSelected(false);        
        TlainPemeriksaan.setText("");
        TlainPemeriksaan.setEnabled(false);
        TperiksaDalam.setText("");
        
        cmbInspekulo.setSelectedIndex(0);
        ThasilInspekulo.setText("");
        ThasilInspekulo.setEnabled(false);
        Tdiagnosis.setText("");
        Ticd.setText("");
        Tplaning.setText("");
    }    

    private void getData() {
        variabelBersih();        
        if(tbAsesmen.getSelectedRow()!= -1){
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString());
            Valid.SetTgl(TtglAsesmen, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString());
            cmbJam1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString().substring(6, 8));
            TumurPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 6).toString());
            TpekerjaanPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 7).toString());
            TagamaPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 8).toString());
            TalamatPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 9).toString());
            TnamaSuami.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString());
            TumurSuami.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString());
            TpekerjaanSuami.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString());
            TalamatSuami.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 19).toString());
            cmbAgamaSuami.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString());
            TalasanMskRS.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString());
            Ttd.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString());
            Tnadi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString());
            Trespi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString());
            Tsuhu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString());
            Tkesadaran.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString());
            Tsaturasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString());
            sendiri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString();
            rujukan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString();
            cmbJnsRujukan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString());
            TketRujukan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString());
            pkm = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 32).toString();
            TketPkm.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 33).toString());
            spog = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString();
            rsLain = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString();
            TketRsLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString());
            Tgr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString());
            Tpr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString());
            Ta.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString());
            Thamil.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString());
            Tgpapah.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString());
            Tdengan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString());
            cmbPerut.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString());
            cmbKeluhanPerut.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString());
            Valid.SetTgl(TtglPerut, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString());
            cmbJam2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString().substring(0, 2));
            cmbMnt2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString().substring(3, 5));
            cmbDtk2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString().substring(6, 8));
            cmbKeluar.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString());
            cmbKeluhanKeluar.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString());
            Valid.SetTgl(TtglKeluar, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString());
            cmbJam3.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString().substring(0, 2));
            cmbMnt3.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString().substring(3, 5));
            cmbDtk3.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString().substring(6, 8));
            cmbDarah.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString());
            cmbKeluhanDarah.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString());
            cmbJnsDarah.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString());
            Valid.SetTgl(TtglDarah, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString());
            cmbJam4.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString().substring(0, 2));
            cmbMnt4.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString().substring(3, 5));
            cmbDtk4.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString().substring(6, 8));
            cmbKeluarAir.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString());
            cmbKeluhanKeluarAir.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString());
            cmbJnsKeluarAir.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString());
            Valid.SetTgl(TtglKeluarAir, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString());
            cmbJam5.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString().substring(0, 2));
            cmbMnt5.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString().substring(3, 5));
            cmbDtk5.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString().substring(6, 8));
            cmbPergerakan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString());
            Tpergerakan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 62).toString());
            cmbPusing.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString());
            Valid.SetTgl(TtglPusing, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 64).toString());
            cmbJam6.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString().substring(0, 2));
            cmbMnt6.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString().substring(3, 5));
            cmbDtk6.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString().substring(6, 8));
            cmbNyeriUlu.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 66).toString());
            Valid.SetTgl(TtglNyeriUlu, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString());
            cmbJam7.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString().substring(0, 2));
            cmbMnt7.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString().substring(3, 5));
            cmbDtk7.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString().substring(6, 8));
            cmbPandangan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString());
            Valid.SetTgl(TtglPandangan, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString());
            cmbJam8.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString().substring(0, 2));
            cmbMnt8.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString().substring(3, 5));
            cmbDtk8.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString().substring(6, 8));
            cmbOdema.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString());
            Valid.SetTgl(TtglOdema, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString());
            cmbOdemaDi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString());            
            cmbMual.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString());
            Valid.SetTgl(TtglMual, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString());
            cmbJam9.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString().substring(0, 2));
            cmbMnt9.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString().substring(3, 5));
            cmbDtk9.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString().substring(6, 8));            
            cmbMuntah.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString());
            Valid.SetTgl(TtglMuntah, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 79).toString());
            cmbJam10.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 80).toString().substring(0, 2));
            cmbMnt10.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 80).toString().substring(3, 5));
            cmbDtk10.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 80).toString().substring(6, 8));            
            cmbBatuk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 81).toString());
            Valid.SetTgl(TtglBatuk, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 82).toString());
            cmbJam11.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 83).toString().substring(0, 2));
            cmbMnt11.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 83).toString().substring(3, 5));
            cmbDtk11.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 83).toString().substring(6, 8));            
            cmbPilek.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 84).toString());
            Valid.SetTgl(TtglPilek, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 85).toString());
            cmbJam12.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 86).toString().substring(0, 2));
            cmbMnt12.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 86).toString().substring(3, 5));
            cmbDtk12.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 86).toString().substring(6, 8));            
            cmbDemam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 87).toString());
            Valid.SetTgl(TtglDemam, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 88).toString());
            cmbJam13.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 89).toString().substring(0, 2));
            cmbMnt13.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 89).toString().substring(3, 5));
            cmbDtk13.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 89).toString().substring(6, 8));            
            cmbRiwPerjalanan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 90).toString());
            TketRiwPerjalanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 91).toString());
            cmbVaksin.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 92).toString());
            TketVaksin.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 93).toString());
            cmbPeriksa.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString());
            TketHasilPemeriksaan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 95).toString());            
            cmbAnc.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 96).toString());
            cmbAncDi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 97).toString());
            TjlhAnc.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 98).toString());            
            TnmDokter1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 99).toString());
            TjlhDokter1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 100).toString());
            TnmDokter2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString());
            TjlhDokter2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 102).toString());
            TnmDokter3.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 103).toString());
            TjlhDokter3.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 104).toString());            
            Thpht.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 105).toString());
            Thpl.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 106).toString());
            Tuk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 107).toString());            
            TbbSebelum.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 108).toString());
            TbbTerakhir.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 109).toString());
            Ttbi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 110).toString());            
            TumurPertama.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 111).toString());
            TlamaHaid.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 112).toString());
            Tberapa.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 113).toString());            
            cmbKeluhanWaktu.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 114).toString());
            dismen = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 115).toString();
            spoting = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 116).toString();
            menor = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 117).toString();
            metro = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 118).toString();
            lainKeluhanHaid = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 119).toString();
            TkeluhanLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 120).toString());            
            cmbRiwPenDahulu.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 121).toString());
            hipertensiDahulu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 122).toString();
            dmDahulu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 123).toString();
            jantungDahulu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 124).toString();
            asmaDahulu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 125).toString();
            lainyaDahulu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 126).toString();
            TlainDahulu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 127).toString());            
            cmbRiwPenKeluarga.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 128).toString());
            hipertensiKeluarga = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 129).toString();
            dmKeluarga = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 130).toString();
            jantungKeluarga = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 131).toString();
            asmaKeluarga = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 132).toString();
            lainyaKeluarga = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 133).toString();
            TlainKeluarga.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 134).toString());            
            cmbRiwGinekologi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 135).toString());
            TriwGinekologi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 136).toString());            
            pil = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 137).toString();
            TlamaPil.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 138).toString());
            cmbSatLamaPil.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 139).toString());            
            suntik1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 140).toString();
            TlamaSuntik1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 141).toString());
            cmbSatLamaSuntik1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 142).toString());            
            suntik3 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 143).toString();
            TlamaSuntik3.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 144).toString());
            cmbSatLamaSuntik3.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 145).toString());
            implan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 146).toString();
            TlamaImplan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 147).toString());
            cmbSatLamaImplan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 148).toString());
            iud = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 149).toString();
            TlamaIud.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 150).toString());
            cmbSatLamaIud.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 151).toString());
            tidakKb = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 152).toString();
            BtnTambahRiwayatActionPerformed(null);
            tampilRiwayat();
            cmbSttsPerkawinan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 153).toString());
            istriKawin = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 154).toString();
            suamiKawin = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 155).toString();
            cmbJlhIstri.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 156).toString());
            cmbJlhSuami.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 157).toString());
            TusiaPertama.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 158).toString());
            TusiaPerkawinan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 159).toString());
            TklgTerdekat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 160).toString());
            ThubKeluarga.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 161).toString());
            orangTua = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 162).toString();
            suami = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 163).toString();
            anak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 164).toString();
            tinggalSendiri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 165).toString();
            cmbCuriga.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 166).toString());
            cmbKegiatan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 167).toString());
            cmbSttsEmosional.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 168).toString());
            asuransi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 169).toString();
            jaminan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 170).toString();
            biayaSendiri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 171).toString();
            lainStatusEkonomi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 172).toString();
            TsttsLainEkonomi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 173).toString());            
            Tleo1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 174).toString());
            Tleo2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 175).toString());
            Tleo3.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 176).toString());
            Tleo4.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 177).toString());
            cmbBandle.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 178).toString());
            cmbPerutTegang.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 179).toString());
            Tpalpasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 180).toString());
            cmbTeraba.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 181).toString());
            Tsebesar.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 182).toString());
            cmbGoyang.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 183).toString());
            cmbNyeriTekan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 184).toString());
            TvtPembukaan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 185).toString());
            cmbVtNyeri.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 186).toString());
            Ttfu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 187).toString());
            Ttaksiran.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 188).toString());
            ThisKontraksi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 189).toString());
            cmbHis.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 190).toString());
            Tdurasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 191).toString());
            cmbDurasi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 192).toString());
            Tauskultasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 193).toString());
            bersih = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 194).toString();
            oedema = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 195).toString();
            ruftur = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 196).toString();
            candiloma = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 197).toString();
            lainPemeriksaanGeni = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 198).toString();
            TlainPemeriksaan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 199).toString());
            TperiksaDalam.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 200).toString());
            cmbInspekulo.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 201).toString());
            ThasilInspekulo.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 202).toString());
            Tdiagnosis.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 203).toString());
            Ticd.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 204).toString());
            Tplaning.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 205).toString());
            wktSimpan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 206).toString();
            stsrwt = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 207).toString();
            dataCek();
        }
    }

    public void setData(String norwt, String unit, String sttsRawat) {
        TNoRw.setText(norwt);        
        TrgRawat.setText(unit);
        stsrwt = sttsRawat;
        TCari.setText(norwt);
        DTPCari2.setDate(new Date());
        isPasien();
        tampil();
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnEdit.setEnabled(akses.getcppt());
    }

    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from asesmen_awal_kebidanan1 where no_rawat=?", 1, new String[]{
                tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
            }) == true) {
                Sequel.meghapus("riwayat_kehamilan_asesmen_awal_kebidanan", "no_rawat", tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
                Sequel.meghapus("asesmen_awal_kebidanan2", "no_rawat", tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
                
                TCari.setText(TNoRw.getText());
                emptTeks();
                TabRawat.setSelectedIndex(1);
                tampil();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        } else {
            TCari.setText(TNoRw.getText());
            emptTeks();
            TabRawat.setSelectedIndex(1);
            tampil();
        }
    }

    private void ganti() {
        cekData();
        if (Sequel.mengedittf("asesmen_awal_kebidanan1", "no_rawat=?", "ruang_rawat=?, tgl_asesmen=?, jam_asesmen=?, nm_suami=?, umur_suami=?, pekerjaan_suami=?, "
                + "alamat_suami=?, agama_suami=?, alasan_masuk=?, td=?, nadi=?, respirasi=?, suhu=?, kesadaran=?, saturasi=?, cek_sendiri=?, cek_rujukan=?, jns_rujukan=?, ket_jns_rujukan=?, "
                + "cek_pkm=?, ket_pkm=?, cek_spog=?, cek_rs_lain=?, ket_rs_lain=?, gr=?, pr=?, a=?, hamil=?, gpapah=?, dengan=?, perut=?, keluhan_perut=?, tgl_perut=?, jam_perut=?, "
                + "keluar=?, keluhan_keluar=?, tgl_keluar_lendir=?, jam_keluar_lendir=?, darah=?, keluhan_darah=?, jns_darah=?, tgl_darah=?, jam_darah=?, keluar_air=?, keluhan_keluar_air=?, "
                + "jns_keluar_air=?, tgl_keluar_air=?, jam_keluar_air=?, pergerakan_janin_2jam_terakhir=?, ket_pergerakan_janin_2jam_terakhir=?, pusing=?, tgl_pusing=?, jam_pusing=?, "
                + "nyeri_ulu_hati=?, tgl_nyeri_ulu_hati=?, jam_nyeri_ulu_hati=?, pandangan_kabur=?, tgl_pandangan_kabur=?, jam_pandangan_kabur=?, odema=?, tgl_odema=?, odema_di=?, mual=?, "
                + "tgl_mual=?, jam_mual=?, muntah=?, tgl_muntah=?, jam_muntah=?, batuk=?, tgl_batuk=?, jam_batuk=?, pilek=?, tgl_pilek=?, jam_pilek=?, demam=?, tgl_demam=?, jam_demam=?, "
                + "riw_perjalanan_jauh=?, ket_riw_perjalanan_jauh=?, vaksin_covid19=?, jlh_vaksin_covid19=?, periksa_ketempat_bidan=?, hasil_pemeriksaan_bidan=?, ibu_anc=?, jns_anc=?, "
                + "jlh_anc=?, dengan_dokter1=?, jlh_dengan_dokter1=?, dengan_dokter2=?, jlh_dengan_dokter2=?, dengan_dokter3=?, jlh_dengan_dokter3=?, hpht=?, hpl=?, uk=?, bb_sebelum_hamil=?, "
                + "bb_terakhir=?, tbi=?, umur_pertama_haid=?, lama_haid=?, berapa_kali_ganti_pembalut=?, keluhan_waktu_haid=?, cek_dismen=?, cek_spoting=?, cek_menor=?, cek_metro=?, "
                + "cek_lain_keluhan_haid=?, ket_lain_keluhan_haid=?, riw_penyakit_dahulu=?, cek_hipertensi_dahulu=?, cek_dm_dahulu=?, cek_jantung_dahulu=?, cek_asma_dahulu=?, cek_lainya_dahulu=?, "
                + "ket_lain_penyakit_dahulu=?, riw_penyakit_keluarga=?, cek_hipertensi_keluarga=?, cek_dm_keluarga=?, cek_jantung_keluarga=?, cek_asma_keluarga=?, cek_lainya_keluarga=?, "
                + "ket_lain_penyakit_keluarga=?, riw_ginekologi=?, ket_ginekologi=?, cek_pil=?, lama_pil=?, satuan_lama_pil=?, cek_suntik1=?, lama_suntik1=?, satuan_lama_suntik1=?, cek_suntik3=?, "
                + "lama_suntik3=?, satuan_lama_suntik3=?, cek_implan=?, lama_implan=?, satuan_lama_implan=?, cek_iud=?, lama_iud=?, satuan_lama_iud=?, cek_tidak_kb=?, status_perkawinan=?, "
                + "cek_istri_kawin=?, cek_suami_kawin=?, jlh_perkawinan_istri=?, jlh_perkawinan_suami=?, usia_pertama_nikah=?, usia_perkawinan=?, keluarga_terdekat=?, hubungan=?, cek_orang_tua=?, "
                + "cek_suami=?, cek_anak=?, cek_tinggal_sendiri=?, curiga_penganiayaan=?, kegiatan_ibadah=?, status_emosional=?, cek_asuransi=?, cek_jaminan=?, cek_biaya_sendiri=?, "
                + "cek_lain_status_ekonomi=?, ket_lain_status_ekonomi=?, leopold1=?, leopold2=?, leopold3=?, leopold4=?, bandle_ring=?, perut_tegang=?, palpasi=?, teraba_massa=?, sebesar=?, "
                + "goyang=?, nyeri_tekan=?, vt_pembukaan=?, vt_nyeri_goyang=?, tfu=?, taksiran_berat_janin=?, his_kontraksi=?, jns_his_kontraksi=?, durasi=?, jns_durasi=?, auskultasi=?, "
                + "cek_bersih=?, cek_oedema=?, cek_ruftur=?, cek_candiloma=?, cek_lain_pemeriksaan_geni=?, ket_lain_pemeriksaan_geni=?, periksa_dalam_obstetri=?, inspekulo=?, hasil_inspekulo=?, "
                + "diagnosis_sementara=?, icd_10=?, planing=?", 194, new String[]{
                    TrgRawat.getText(), Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                    TnamaSuami.getText(), TumurSuami.getText(), TpekerjaanSuami.getText(), TalamatSuami.getText(), cmbAgamaSuami.getSelectedItem().toString(), TalasanMskRS.getText(),
                    Ttd.getText(), Tnadi.getText(), Trespi.getText(), Tsuhu.getText(), Tkesadaran.getText(), Tsaturasi.getText(), sendiri, rujukan, cmbJnsRujukan.getSelectedItem().toString(),
                    TketRujukan.getText(), pkm, TketPkm.getText(), spog, rsLain, TketRsLain.getText(), Tgr.getText(), Tpr.getText(), Ta.getText(), Thamil.getText(), Tgpapah.getText(),
                    Tdengan.getText(), cmbPerut.getSelectedItem().toString(), cmbKeluhanPerut.getSelectedItem().toString(), Valid.SetTgl(TtglPerut.getSelectedItem() + ""),
                    cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), cmbKeluar.getSelectedItem().toString(), cmbKeluhanKeluar.getSelectedItem().toString(),
                    Valid.SetTgl(TtglKeluar.getSelectedItem() + ""), cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem(), cmbDarah.getSelectedItem().toString(),
                    cmbKeluhanDarah.getSelectedItem().toString(), cmbJnsDarah.getSelectedItem().toString(), Valid.SetTgl(TtglDarah.getSelectedItem() + ""), cmbJam4.getSelectedItem() + ":" + cmbMnt4.getSelectedItem() + ":" + cmbDtk4.getSelectedItem(),
                    cmbKeluarAir.getSelectedItem().toString(), cmbKeluhanKeluarAir.getSelectedItem().toString(), cmbJnsKeluarAir.getSelectedItem().toString(), Valid.SetTgl(TtglKeluarAir.getSelectedItem() + ""),
                    cmbJam5.getSelectedItem() + ":" + cmbMnt5.getSelectedItem() + ":" + cmbDtk5.getSelectedItem(), cmbPergerakan.getSelectedItem().toString(), Tpergerakan.getText(),
                    cmbPusing.getSelectedItem().toString(), Valid.SetTgl(TtglPusing.getSelectedItem() + ""), cmbJam6.getSelectedItem() + ":" + cmbMnt6.getSelectedItem() + ":" + cmbDtk6.getSelectedItem(),
                    cmbNyeriUlu.getSelectedItem().toString(), Valid.SetTgl(TtglNyeriUlu.getSelectedItem() + ""), cmbJam7.getSelectedItem() + ":" + cmbMnt7.getSelectedItem() + ":" + cmbDtk7.getSelectedItem(),
                    cmbPandangan.getSelectedItem().toString(), Valid.SetTgl(TtglPandangan.getSelectedItem() + ""), cmbJam8.getSelectedItem() + ":" + cmbMnt8.getSelectedItem() + ":" + cmbDtk8.getSelectedItem(),
                    cmbOdema.getSelectedItem().toString(), Valid.SetTgl(TtglOdema.getSelectedItem() + ""), cmbOdemaDi.getSelectedItem().toString(), cmbMual.getSelectedItem().toString(),
                    Valid.SetTgl(TtglMual.getSelectedItem() + ""), cmbJam9.getSelectedItem() + ":" + cmbMnt9.getSelectedItem() + ":" + cmbDtk9.getSelectedItem(), cmbMuntah.getSelectedItem().toString(),
                    Valid.SetTgl(TtglMuntah.getSelectedItem() + ""), cmbJam10.getSelectedItem() + ":" + cmbMnt10.getSelectedItem() + ":" + cmbDtk10.getSelectedItem(), cmbBatuk.getSelectedItem().toString(),
                    Valid.SetTgl(TtglBatuk.getSelectedItem() + ""), cmbJam11.getSelectedItem() + ":" + cmbMnt11.getSelectedItem() + ":" + cmbDtk11.getSelectedItem(), cmbPilek.getSelectedItem().toString(),
                    Valid.SetTgl(TtglPilek.getSelectedItem() + ""), cmbJam12.getSelectedItem() + ":" + cmbMnt12.getSelectedItem() + ":" + cmbDtk12.getSelectedItem(), cmbDemam.getSelectedItem().toString(),
                    Valid.SetTgl(TtglDemam.getSelectedItem() + ""), cmbJam13.getSelectedItem() + ":" + cmbMnt13.getSelectedItem() + ":" + cmbDtk13.getSelectedItem(), cmbRiwPerjalanan.getSelectedItem().toString(),
                    TketRiwPerjalanan.getText(), cmbVaksin.getSelectedItem().toString(), TketVaksin.getText(), cmbPeriksa.getSelectedItem().toString(), ThasilInspekulo.getText(), cmbAnc.getSelectedItem().toString(),
                    cmbAncDi.getSelectedItem().toString(), TjlhAnc.getText(), TnmDokter1.getText(), TjlhDokter1.getText(), TnmDokter2.getText(), TjlhDokter2.getText(), TnmDokter3.getText(),
                    TjlhDokter3.getText(), Thpht.getText(), Thpl.getText(), Tuk.getText(), TbbSebelum.getText(), TbbTerakhir.getText(), Ttbi.getText(), TumurPertama.getText(), TlamaHaid.getText(),
                    Tberapa.getText(), cmbKeluhanWaktu.getSelectedItem().toString(), dismen, spoting, menor, metro, lainKeluhanHaid, TkeluhanLain.getText(), cmbRiwPenDahulu.getSelectedItem().toString(),
                    hipertensiDahulu, dmDahulu, jantungDahulu, asmaDahulu, lainyaDahulu, TlainDahulu.getText(), cmbRiwPenKeluarga.getSelectedItem().toString(), hipertensiKeluarga, dmKeluarga,
                    jantungKeluarga, asmaKeluarga, lainyaKeluarga, TlainKeluarga.getText(), cmbRiwGinekologi.getSelectedItem().toString(), TriwGinekologi.getText(), pil, TlamaPil.getText(),
                    cmbSatLamaPil.getSelectedItem().toString(), suntik1, TlamaSuntik1.getText(), cmbSatLamaSuntik1.getSelectedItem().toString(), suntik3, TlamaSuntik3.getText(), cmbSatLamaSuntik3.getSelectedItem().toString(),
                    implan, TlamaImplan.getText(), cmbSatLamaImplan.getSelectedItem().toString(), iud, TlamaIud.getText(), cmbSatLamaIud.getSelectedItem().toString(), tidakKb,
                    cmbSttsPerkawinan.getSelectedItem().toString(), istriKawin, suamiKawin, cmbJlhIstri.getSelectedItem().toString(), cmbJlhSuami.getSelectedItem().toString(), TusiaPertama.getText(),
                    TusiaPerkawinan.getText(), TklgTerdekat.getText(), ThubKeluarga.getText(), orangTua, suami, anak, tinggalSendiri, cmbCuriga.getSelectedItem().toString(), cmbKegiatan.getSelectedItem().toString(),
                    cmbSttsEmosional.getSelectedItem().toString(), asuransi, jaminan, biayaSendiri, lainStatusEkonomi, TsttsLainEkonomi.getText(), Tleo1.getText(), Tleo2.getText(),
                    Tleo3.getText(), Tleo4.getText(), cmbBandle.getSelectedItem().toString(), cmbPerutTegang.getSelectedItem().toString(), Tpalpasi.getText(), cmbTeraba.getSelectedItem().toString(),
                    Tsebesar.getText(), cmbGoyang.getSelectedItem().toString(), cmbNyeriTekan.getSelectedItem().toString(), TvtPembukaan.getText(), cmbVtNyeri.getSelectedItem().toString(), Ttfu.getText(),
                    Ttaksiran.getText(), ThisKontraksi.getText(), cmbHis.getSelectedItem().toString(), Tdurasi.getText(), cmbDurasi.getSelectedItem().toString(), Tauskultasi.getText(), bersih, oedema,
                    ruftur, candiloma, lainPemeriksaanGeni, TlainPemeriksaan.getText(), TperiksaDalam.getText(), cmbInspekulo.getSelectedItem().toString(), ThasilInspekulo.getText(), Tdiagnosis.getText(),
                    Ticd.getText(), Tplaning.getText(), tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                }) == true) {

            if (tbRiwayat.getRowCount() != 0) {
                if (Sequel.queryu2tf("delete from riwayat_kehamilan_asesmen_awal_kebidanan where no_rawat=?", 1, new String[]{
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                }) == true) {
                    for (i = 0; i < tbRiwayat.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("riwayat_kehamilan_asesmen_awal_kebidanan",
                                "'" + tbRiwayat.getValueAt(i, 0).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 1).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 2).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 3).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 4).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 5).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 6).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 7).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 8).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 9).toString() + "',"
                                + "'" + tbRiwayat.getValueAt(i, 10).toString() + "'", "Data Riwayat Kehamilan");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }

            TCari.setText(TNoRw.getText());
            emptTeks();
            TabRawat.setSelectedIndex(1);
            tampil();
        }
    }
    
    private void getRiwayat() {
        wktSimpan = "";
        if (tbRiwayat.getSelectedRow() != -1) {
            TthnPartus.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
            TumurHamil.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 3).toString());
            TjnsPersalinan.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 4).toString());
            Tpenyulit.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 6).toString());
            TbrtLahir.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 8).toString());
            wktSimpan = tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 10).toString();
        }
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabModeRiwayat);
        try {
            ps1 = koneksi.prepareStatement("select * from riwayat_kehamilan_asesmen_awal_kebidanan where no_rawat='" + TNoRw.getText() + "' order by waktu_simpan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabModeRiwayat.addRow(new String[]{
                        rs1.getString("no_rawat"),
                        rs1.getString("tahun_partus"),
                        rs1.getString("tempat_partus"),
                        rs1.getString("umur_hamil"),
                        rs1.getString("jns_persalinan"),
                        rs1.getString("penolong_persalinan"),
                        rs1.getString("penyulit"),
                        rs1.getString("jk"),
                        rs1.getString("bb"),
                        rs1.getString("keadaan_anak_skrng"),
                        rs1.getString("waktu_simpan")
                    });
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
    
    private void cekData() {
        if (chkSendiri.isSelected() == true) {
            sendiri = "ya";
        } else {
            sendiri = "tidak";
        }
        
        if (chkRujukan.isSelected() == true) {
            rujukan = "ya";
        } else {
            rujukan = "tidak";
        }
        
        if (chkPkm.isSelected() == true) {
            pkm = "ya";
        } else {
            pkm = "tidak";
        }
        
        if (chkSpog.isSelected() == true) {
            spog = "ya";
        } else {
            spog = "tidak";
        }
        
        if (chkRsLain.isSelected() == true) {
            rsLain = "ya";
        } else {
            rsLain = "tidak";
        }
        
        if (chkDismen.isSelected() == true) {
            dismen = "ya";
        } else {
            dismen = "tidak";
        }
        
        if (chkSpoting.isSelected() == true) {
            spoting = "ya";
        } else {
            spoting = "tidak";
        }
        
        if (chkMenor.isSelected() == true) {
            menor = "ya";
        } else {
            menor = "tidak";
        }
        
        if (chkMetro.isSelected() == true) {
            metro = "ya";
        } else {
            metro = "tidak";
        }
        
        if (chkKeluhanLain.isSelected() == true) {
            lainKeluhanHaid = "ya";
        } else {
            lainKeluhanHaid = "tidak";
        }
        
        if (chkHipertensiDahulu.isSelected() == true) {
            hipertensiDahulu = "ya";
        } else {
            hipertensiDahulu = "tidak";
        }
        
        if (chkDmDahulu.isSelected() == true) {
            dmDahulu = "ya";
        } else {
            dmDahulu = "tidak";
        }
        
        if (chkJantungDahulu.isSelected() == true) {
            jantungDahulu = "ya";
        } else {
            jantungDahulu = "tidak";
        }
        
        if (chkAsmaDahulu.isSelected() == true) {
            asmaDahulu = "ya";
        } else {
            asmaDahulu = "tidak";
        }
        
        if (chkLainDahulu.isSelected() == true) {
            lainyaDahulu = "ya";
        } else {
            lainyaDahulu = "tidak";
        }
        
        if (chkHipertensiKeluarga.isSelected() == true) {
            hipertensiKeluarga = "ya";
        } else {
            hipertensiKeluarga = "tidak";
        }
        
        if (chkDmKeluarga.isSelected() == true) {
            dmKeluarga = "ya";
        } else {
            dmKeluarga = "tidak";
        }
        
        if (chkJantungKeluarga.isSelected() == true) {
            jantungKeluarga = "ya";
        } else {
            jantungKeluarga = "tidak";
        }
        
        if (chkAsmaKeluarga.isSelected() == true) {
            asmaKeluarga = "ya";
        } else {
            asmaKeluarga = "tidak";
        }
        
        if (chkLainKeluarga.isSelected() == true) {
            lainyaKeluarga = "ya";
        } else {
            lainyaKeluarga = "tidak";
        }
        
        if (chkPil.isSelected() == true) {
            pil = "ya";
        } else {
            pil = "tidak";
        }
        
        if (chkSuntik1.isSelected() == true) {
            suntik1 = "ya";
        } else {
            suntik1 = "tidak";
        }
        
        if (chkSuntik3.isSelected() == true) {
            suntik3 = "ya";
        } else {
            suntik3 = "tidak";
        }
        
        if (chkImplan.isSelected() == true) {
            implan = "ya";
        } else {
            implan = "tidak";
        }
        
        if (chkIud.isSelected() == true) {
            iud = "ya";
        } else {
            iud = "tidak";
        }
        
        if (chkTidakPernah.isSelected() == true) {
            tidakKb = "ya";
        } else {
            tidakKb = "tidak";
        }
        
        if (chkIstri.isSelected() == true) {
            istriKawin = "ya";
        } else {
            istriKawin = "tidak";
        }
        
        if (chkSuami.isSelected() == true) {
            suamiKawin = "ya";
        } else {
            suamiKawin = "tidak";
        }
        
        if (chkOrtu.isSelected() == true) {
            orangTua = "ya";
        } else {
            orangTua = "tidak";
        }
        
        if (chkTinggalSuami.isSelected() == true) {
            suami = "ya";
        } else {
            suami = "tidak";
        }
        
        if (chkAnak.isSelected() == true) {
            anak = "ya";
        } else {
            anak = "tidak";
        }
        
        if (chkTinggalSendiri.isSelected() == true) {
            tinggalSendiri = "ya";
        } else {
            tinggalSendiri = "tidak";
        }
        
        if (chkAsuransi.isSelected() == true) {
            asuransi = "ya";
        } else {
            asuransi = "tidak";
        }
        
        if (chkJaminan.isSelected() == true) {
            jaminan = "ya";
        } else {
            jaminan = "tidak";
        }
        
        if (chkBiaya.isSelected() == true) {
            biayaSendiri = "ya";
        } else {
            biayaSendiri = "tidak";
        }
        
        if (chkSttsLain.isSelected() == true) {
            lainStatusEkonomi = "ya";
        } else {
            lainStatusEkonomi = "tidak";
        }
        
        if (chkBersih.isSelected() == true) {
            bersih = "ya";
        } else {
            bersih = "tidak";
        }
        
        if (chkOedema.isSelected() == true) {
            oedema = "ya";
        } else {
            oedema = "tidak";
        }
        
        if (chkRuftur.isSelected() == true) {
            ruftur = "ya";
        } else {
            ruftur = "tidak";
        }
        
        if (chkCandi.isSelected() == true) {
            candiloma = "ya";
        } else {
            candiloma = "tidak";
        }
        
        if (chkLainPemeriksaan.isSelected() == true) {
            lainPemeriksaanGeni = "ya";
        } else {
            lainPemeriksaanGeni = "tidak";
        }
        
        if (chkAlamatSama.isSelected() == true) {
            alamatSama = "ya";
        } else {
            alamatSama = "tidak";
        }
    }
    
    private void dataCek() {
        if (sendiri.equals("ya")) {
            chkSendiri.setSelected(true);
        } else {
            chkSendiri.setSelected(false);
        }
        
        if (rujukan.equals("ya")) {
            chkRujukan.setSelected(true);
            cmbJnsRujukan.setEnabled(true);
            TketRujukan.setEnabled(true);
        } else {
            chkRujukan.setSelected(false);
            cmbJnsRujukan.setEnabled(false);
            TketRujukan.setEnabled(false);
        }
        
        if (pkm.equals("ya")) {
            chkPkm.setSelected(true);
            TketPkm.setEnabled(true);
        } else {
            chkPkm.setSelected(false);
            TketPkm.setEnabled(false);
        }
        
        if (spog.equals("ya")) {
            chkSpog.setSelected(true);
        } else {
            chkSpog.setSelected(false);
        }
        
        if (rsLain.equals("ya")) {
            chkRsLain.setSelected(true);
            TketRsLain.setEnabled(true);
        } else {
            chkRsLain.setSelected(false);
            TketRsLain.setEnabled(false);
        }
        
        if (dismen.equals("ya")) {
            chkDismen.setSelected(true);
        } else {
            chkDismen.setSelected(false);
        }
        
        if (cmbPerut.getSelectedIndex() == 1) {
            cmbKeluhanPerut.setEnabled(true);
            TtglPerut.setEnabled(true);
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
        } else {
            cmbKeluhanPerut.setEnabled(false);
            TtglPerut.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
        }
        
        if (cmbKeluar.getSelectedIndex() == 1) {
            cmbKeluhanKeluar.setEnabled(true);
            TtglKeluar.setEnabled(true);
            cmbJam3.setEnabled(true);
            cmbMnt3.setEnabled(true);
            cmbDtk3.setEnabled(true);
        } else {
            cmbKeluhanKeluar.setEnabled(false);
            TtglKeluar.setEnabled(false);
            cmbJam3.setEnabled(false);
            cmbMnt3.setEnabled(false);
            cmbDtk3.setEnabled(false);
        }
        
        if (cmbDarah.getSelectedIndex() == 1) {
            cmbKeluhanDarah.setEnabled(true);
            cmbJnsDarah.setEnabled(true);
            TtglDarah.setEnabled(true);
            cmbJam4.setEnabled(true);
            cmbMnt4.setEnabled(true);
            cmbDtk4.setEnabled(true);
        } else {
            cmbKeluhanDarah.setEnabled(false);
            cmbJnsDarah.setEnabled(false);
            TtglDarah.setEnabled(false);
            cmbJam4.setEnabled(false);
            cmbMnt4.setEnabled(false);
            cmbDtk4.setEnabled(false);
        }
        
        if (cmbKeluarAir.getSelectedIndex() == 1) {
            cmbKeluhanKeluarAir.setEnabled(true);
            cmbJnsKeluarAir.setEnabled(true);
            TtglKeluarAir.setEnabled(true);
            cmbJam5.setEnabled(true);
            cmbMnt5.setEnabled(true);
            cmbDtk5.setEnabled(true);
        } else {
            cmbKeluhanKeluarAir.setEnabled(false);
            cmbJnsKeluarAir.setEnabled(false);
            TtglKeluarAir.setEnabled(false);
            cmbJam5.setEnabled(false);
            cmbMnt5.setEnabled(false);
            cmbDtk5.setEnabled(false);
        }
        
        if (cmbPergerakan.getSelectedIndex() == 1) {
            Tpergerakan.setEnabled(true);
        } else {
            Tpergerakan.setEnabled(false);
        }
        
        if (cmbPusing.getSelectedIndex() == 1) {
            TtglPusing.setEnabled(true);
            cmbJam6.setEnabled(true);
            cmbMnt6.setEnabled(true);
            cmbDtk6.setEnabled(true);
        } else {
            TtglPusing.setEnabled(false);
            cmbJam6.setEnabled(false);
            cmbMnt6.setEnabled(false);
            cmbDtk6.setEnabled(false);
        }
        
        if (cmbNyeriUlu.getSelectedIndex() == 1) {
            TtglNyeriUlu.setEnabled(true);
            cmbJam7.setEnabled(true);
            cmbMnt7.setEnabled(true);
            cmbDtk7.setEnabled(true);
        } else {
            TtglNyeriUlu.setEnabled(false);
            cmbJam7.setEnabled(false);
            cmbMnt7.setEnabled(false);
            cmbDtk7.setEnabled(false);
        }
        
        if (cmbPandangan.getSelectedIndex() == 1) {
            TtglPandangan.setEnabled(true);
            cmbJam8.setEnabled(true);
            cmbMnt8.setEnabled(true);
            cmbDtk8.setEnabled(true);
        } else {
            TtglPandangan.setEnabled(false);
            cmbJam8.setEnabled(false);
            cmbMnt8.setEnabled(false);
            cmbDtk8.setEnabled(false);
        }
        
        if (cmbOdema.getSelectedIndex() == 1) {
            TtglOdema.setEnabled(true);
            cmbOdemaDi.setEnabled(true);
        } else {
            TtglOdema.setEnabled(false);
            cmbOdemaDi.setEnabled(false);
        }
        
        if (cmbMual.getSelectedIndex() == 1) {
            TtglMual.setEnabled(true);
            cmbJam9.setEnabled(true);
            cmbMnt9.setEnabled(true);
            cmbDtk9.setEnabled(true);
        } else {
            TtglMual.setEnabled(false);
            cmbJam9.setEnabled(false);
            cmbMnt9.setEnabled(false);
            cmbDtk9.setEnabled(false);
        }
        
        if (cmbMuntah.getSelectedIndex() == 1) {
            TtglMuntah.setEnabled(true);
            cmbJam10.setEnabled(true);
            cmbMnt10.setEnabled(true);
            cmbDtk10.setEnabled(true);
        } else {
            TtglMuntah.setEnabled(false);
            cmbJam10.setEnabled(false);
            cmbMnt10.setEnabled(false);
            cmbDtk10.setEnabled(false);
        }
        
        if (cmbBatuk.getSelectedIndex() == 1) {
            TtglBatuk.setEnabled(true);
            cmbJam11.setEnabled(true);
            cmbMnt11.setEnabled(true);
            cmbDtk11.setEnabled(true);
        } else {
            TtglBatuk.setEnabled(false);
            cmbJam11.setEnabled(false);
            cmbMnt11.setEnabled(false);
            cmbDtk11.setEnabled(false);
        }
        
        if (cmbPilek.getSelectedIndex() == 1) {
            TtglPilek.setEnabled(true);
            cmbJam12.setEnabled(true);
            cmbMnt12.setEnabled(true);
            cmbDtk12.setEnabled(true);
        } else {
            TtglPilek.setEnabled(false);
            cmbJam12.setEnabled(false);
            cmbMnt12.setEnabled(false);
            cmbDtk12.setEnabled(false);
        }
        
        if (cmbDemam.getSelectedIndex() == 1) {
            TtglDemam.setEnabled(true);
            cmbJam13.setEnabled(true);
            cmbMnt13.setEnabled(true);
            cmbDtk13.setEnabled(true);
        } else {
            TtglDemam.setEnabled(false);
            cmbJam13.setEnabled(false);
            cmbMnt13.setEnabled(false);
            cmbDtk13.setEnabled(false);
        }
        
        if (cmbRiwPerjalanan.getSelectedIndex() == 1) {
            TketRiwPerjalanan.setEnabled(true);
        } else {
            TketRiwPerjalanan.setEnabled(false);
        }
        
        if (cmbVaksin.getSelectedIndex() == 1) {
            TketVaksin.setEnabled(true);
        } else {
            TketVaksin.setEnabled(false);
        }
        
        if (cmbPeriksa.getSelectedIndex() == 1) {
            TketHasilPemeriksaan.setEnabled(true);
        } else {
            TketHasilPemeriksaan.setEnabled(false);
        }
        
        if (cmbAnc.getSelectedIndex() == 1) {
            cmbAncDi.setEnabled(true);
            TjlhAnc.setEnabled(true);
            TnmDokter1.setEnabled(true);
            TnmDokter2.setEnabled(true);
            TnmDokter3.setEnabled(true);
            TjlhDokter1.setEnabled(true);
            TjlhDokter2.setEnabled(true);
            TjlhDokter3.setEnabled(true);
            BtnDokter1.setEnabled(true);
            BtnDokter2.setEnabled(true);
            BtnDokter3.setEnabled(true);
        } else {
            cmbAncDi.setEnabled(false);
            TjlhAnc.setEnabled(false);
            TnmDokter1.setEnabled(false);
            TnmDokter2.setEnabled(false);
            TnmDokter3.setEnabled(false);
            TjlhDokter1.setEnabled(false);
            TjlhDokter2.setEnabled(false);
            TjlhDokter3.setEnabled(false);
            BtnDokter1.setEnabled(false);
            BtnDokter2.setEnabled(false);
            BtnDokter3.setEnabled(false);
        }
        
        if (cmbKeluhanWaktu.getSelectedIndex() == 1) {
            chkDismen.setEnabled(true);
            chkSpoting.setEnabled(true);
            chkMenor.setEnabled(true);
            chkMetro.setEnabled(true);
            chkKeluhanLain.setEnabled(true);
            TkeluhanLain.setEnabled(false);
        } else {
            chkDismen.setEnabled(false);
            chkSpoting.setEnabled(false);
            chkMenor.setEnabled(false);
            chkMetro.setEnabled(false);
            chkKeluhanLain.setEnabled(false);
            TkeluhanLain.setEnabled(false);
        }
        
        if (dismen.equals("ya")) {
            chkDismen.setSelected(true);
        } else {
            chkDismen.setSelected(false);
        }
        
        if (spoting.equals("ya")) {
            chkSpoting.setSelected(true);
        } else {
            chkSpoting.setSelected(false);
        }
        
        if (menor.equals("ya")) {
            chkMenor.setSelected(true);
        } else {
            chkMenor.setSelected(false);
        }
        
        if (metro.equals("ya")) {
            chkMetro.setSelected(true);
        } else {
            chkMetro.setSelected(false);
        }
        
        if (lainKeluhanHaid.equals("ya")) {
            chkKeluhanLain.setSelected(true);
            TkeluhanLain.setEnabled(true);
        } else {
            chkKeluhanLain.setSelected(false);
            TkeluhanLain.setEnabled(false);
        }
        
        if (cmbRiwPenDahulu.getSelectedIndex() == 1) {
            chkHipertensiDahulu.setEnabled(true);
            chkDmDahulu.setEnabled(true);
            chkJantungDahulu.setEnabled(true);
            chkAsmaDahulu.setEnabled(true);
            chkLainDahulu.setEnabled(true);
            TlainDahulu.setEnabled(false);
        } else {
            chkHipertensiDahulu.setEnabled(false);
            chkDmDahulu.setEnabled(false);
            chkJantungDahulu.setEnabled(false);
            chkAsmaDahulu.setEnabled(false);
            chkLainDahulu.setEnabled(false);
            TlainDahulu.setEnabled(false);
        }
        
        if (hipertensiDahulu.equals("ya")) {
            chkHipertensiDahulu.setSelected(true);
        } else {
            chkHipertensiDahulu.setSelected(false);
        }
        
        if (dmDahulu.equals("ya")) {
            chkDmDahulu.setSelected(true);
        } else {
            chkDmDahulu.setSelected(false);
        }
        
        if (jantungDahulu.equals("ya")) {
            chkJantungDahulu.setSelected(true);
        } else {
            chkJantungDahulu.setSelected(false);
        }
        
        if (asmaDahulu.equals("ya")) {
            chkAsmaDahulu.setSelected(true);
        } else {
            chkAsmaDahulu.setSelected(false);
        }
        
        if (lainyaDahulu.equals("ya")) {
            chkLainDahulu.setSelected(true);
            TlainDahulu.setEnabled(true);
        } else {
            chkLainDahulu.setSelected(false);
            TlainDahulu.setEnabled(false);
        }
        
        if (cmbRiwPenKeluarga.getSelectedIndex() == 1) {
            chkHipertensiKeluarga.setEnabled(true);
            chkDmKeluarga.setEnabled(true);
            chkJantungKeluarga.setEnabled(true);
            chkAsmaKeluarga.setEnabled(true);
            chkLainKeluarga.setEnabled(true);
            TlainKeluarga.setEnabled(false);
        } else {
            chkHipertensiKeluarga.setEnabled(false);
            chkDmKeluarga.setEnabled(false);
            chkJantungKeluarga.setEnabled(false);
            chkAsmaKeluarga.setEnabled(false);
            chkLainKeluarga.setEnabled(false);
            TlainKeluarga.setEnabled(false);
        }
        
        if (hipertensiKeluarga.equals("ya")) {
            chkHipertensiKeluarga.setSelected(true);
        } else {
            chkHipertensiKeluarga.setSelected(false);
        }
        
        if (dmKeluarga.equals("ya")) {
            chkDmKeluarga.setSelected(true);
        } else {
            chkDmKeluarga.setSelected(false);
        }
        
        if (jantungKeluarga.equals("ya")) {
            chkJantungKeluarga.setSelected(true);
        } else {
            chkJantungKeluarga.setSelected(false);
        }
        
        if (asmaKeluarga.equals("ya")) {
            chkAsmaKeluarga.setSelected(true);
        } else {
            chkAsmaKeluarga.setSelected(false);
        }
        
        if (lainyaKeluarga.equals("ya")) {
            chkLainKeluarga.setSelected(true);
            TlainKeluarga.setEnabled(true);
        } else {
            chkLainKeluarga.setSelected(false);
            TlainKeluarga.setEnabled(false);
        }
        
        if (cmbRiwGinekologi.getSelectedIndex() == 1) {            
            TriwGinekologi.setEnabled(true);
        } else {            
            TriwGinekologi.setEnabled(false);
        }
        
        if (pil.equals("ya")) {
            chkPil.setSelected(true);
            TlamaPil.setEnabled(true);
            cmbSatLamaPil.setEnabled(true);
        } else {
            chkPil.setSelected(false);
            TlamaPil.setEnabled(false);
            cmbSatLamaPil.setEnabled(false);
        }
        
        if (suntik1.equals("ya")) {
            chkSuntik1.setSelected(true);
            TlamaSuntik1.setEnabled(true);
            cmbSatLamaSuntik1.setEnabled(true);
        } else {
            chkSuntik1.setSelected(false);
            TlamaSuntik1.setEnabled(false);
            cmbSatLamaSuntik1.setEnabled(false);
        }
        
        if (suntik3.equals("ya")) {
            chkSuntik3.setSelected(true);
            TlamaSuntik3.setEnabled(true);
            cmbSatLamaSuntik3.setEnabled(true);
        } else {
            chkSuntik3.setSelected(false);
            TlamaSuntik3.setEnabled(false);
            cmbSatLamaSuntik3.setEnabled(false);
        }
        
        if (implan.equals("ya")) {
            chkImplan.setSelected(true);
            TlamaImplan.setEnabled(true);
            cmbSatLamaImplan.setEnabled(true);
        } else {
            chkImplan.setSelected(false);
            TlamaImplan.setEnabled(false);
            cmbSatLamaImplan.setEnabled(false);
        }
        
        if (iud.equals("ya")) {
            chkIud.setSelected(true);
            TlamaIud.setEnabled(true);
            cmbSatLamaIud.setEnabled(true);
        } else {
            chkIud.setSelected(false);
            TlamaIud.setEnabled(false);
            cmbSatLamaIud.setEnabled(false);
        }
        
        if (tidakKb.equals("ya")) {
            chkTidakPernah.setSelected(true);
        } else {
            chkTidakPernah.setSelected(false);
        }
        
        if (cmbSttsPerkawinan.getSelectedIndex() == 0 || cmbSttsPerkawinan.getSelectedIndex() == 2) {
            chkIstri.setEnabled(false);
            chkSuami.setEnabled(false);
            cmbJlhIstri.setEnabled(false);
            cmbJlhSuami.setEnabled(false);
        } else {
            chkIstri.setEnabled(true);
            chkSuami.setEnabled(true);
            cmbJlhIstri.setEnabled(true);
            cmbJlhSuami.setEnabled(true);
        }
        
        if (istriKawin.equals("ya")) {
            chkIstri.setSelected(true);
        } else {
            chkIstri.setSelected(false);
        }
        
        if (suamiKawin.equals("ya")) {
            chkSuami.setSelected(true);
        } else {
            chkSuami.setSelected(false);
        }
        
        if (orangTua.equals("ya")) {
            chkOrtu.setSelected(true);
        } else {
            chkOrtu.setSelected(false);
        }
        
        if (suami.equals("ya")) {
            chkTinggalSuami.setSelected(true);
        } else {
            chkTinggalSuami.setSelected(false);
        }
        
        if (anak.equals("ya")) {
            chkAnak.setSelected(true);
        } else {
            chkAnak.setSelected(false);
        }
        
        if (tinggalSendiri.equals("ya")) {
            chkTinggalSendiri.setSelected(true);
        } else {
            chkTinggalSendiri.setSelected(false);
        }
        
        if (asuransi.equals("ya")) {
            chkAsuransi.setSelected(true);
        } else {
            chkAsuransi.setSelected(false);
        }
        
        if (jaminan.equals("ya")) {
            chkJaminan.setSelected(true);
        } else {
            chkJaminan.setSelected(false);
        }
        
        if (biayaSendiri.equals("ya")) {
            chkBiaya.setSelected(true);
        } else {
            chkBiaya.setSelected(false);
        }
        
        if (lainStatusEkonomi.equals("ya")) {
            chkSttsLain.setSelected(true);
            TsttsLainEkonomi.setEnabled(true);
        } else {
            chkSttsLain.setSelected(false);
            TsttsLainEkonomi.setEnabled(false);
        }
        
        if (cmbTeraba.getSelectedIndex() == 1) {
            Tsebesar.setEnabled(true);
        } else {
            Tsebesar.setEnabled(false);
        }
        
        if (bersih.equals("ya")) {
            chkBersih.setSelected(true);
        } else {
            chkBersih.setSelected(false);
        }
        
        if (oedema.equals("ya")) {
            chkOedema.setSelected(true);
        } else {
            chkOedema.setSelected(false);
        }
        
        if (ruftur.equals("ya")) {
            chkRuftur.setSelected(true);
        } else {
            chkRuftur.setSelected(false);
        }
        
        if (candiloma.equals("ya")) {
            chkCandi.setSelected(true);
        } else {
            chkCandi.setSelected(false);
        }
        
        if (lainPemeriksaanGeni.equals("ya")) {
            chkLainPemeriksaan.setSelected(true);
            TlainPemeriksaan.setEnabled(true);
        } else {
            chkLainPemeriksaan.setSelected(false);
            TlainPemeriksaan.setEnabled(false);
        }
        
        if (cmbInspekulo.getSelectedIndex() == 1) {
            ThasilInspekulo.setEnabled(true);
        } else {
            ThasilInspekulo.setEnabled(false);
        }
    }
    
    private void isPasien() {
        try {
            ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, concat(rp.umurdaftar,' ',rp.sttsumur) umurPx, "
                    + "ifnull(p.pekerjaan,'-') pekerjaanPx, p.agama, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamatPx, "
                    + "if(p.keluarga='SUAMI',p.namakeluarga,'') nmSuami, if(p.keluarga='SUAMI',ifnull(p.umur_pj,''),'') umurSuami, "
                    + "if(p.keluarga='SUAMI',ifnull(p.pekerjaanpj,''),'') pekerjaanSuami, p.stts_nikah, rp.tgl_registrasi, rp.jam_reg from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join kelurahan kl on kl.kd_kel=p.kd_kel "
                    + "inner join kecamatan kc on kc.kd_kec=p.kd_kec inner join kabupaten kb on kb.kd_kab=p.kd_kab where rp.no_rawat=?");
            try {
                ps2.setString(1, TNoRw.getText());
                rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    TNoRM.setText(rs2.getString("no_rkm_medis"));
                    TPasien.setText(rs2.getString("nm_pasien"));
                    Valid.SetTgl(TtglAsesmen, rs2.getString("tgl_registrasi"));
                    cmbJam1.setSelectedItem(rs2.getString("jam_reg").toString().substring(0, 2));
                    cmbMnt1.setSelectedItem(rs2.getString("jam_reg").toString().toString().substring(3, 5));
                    cmbDtk1.setSelectedItem(rs2.getString("jam_reg").toString().toString().substring(6, 8));
                    TumurPasien.setText(rs2.getString("umurPx"));
                    TpekerjaanPasien.setText(rs2.getString("pekerjaanPx"));
                    TagamaPasien.setText(rs2.getString("agama"));
                    TalamatPasien.setText(rs2.getString("alamatPx"));
                    TnamaSuami.setText(rs2.getString("nmSuami"));                    
                    TumurSuami.setText(rs2.getString("umurSuami"));                    
                    TpekerjaanSuami.setText(rs2.getString("pekerjaanSuami"));
                    cmbAgamaSuami.setSelectedItem(rs2.getString("agama"));
                    Valid.SetTgl(DTPCari1, rs2.getString("tgl_registrasi"));
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
            System.out.println("Notif : " + e);
        }
    }
    
    private void getDataRiwayat() {
        if (tbRiwayat.getSelectedRow() != -1) {
            TthnPartus.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
            TtempatPartus.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 2).toString());
            TumurHamil.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 3).toString());
            TjnsPersalinan.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 4).toString());
            Tpenolong.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString());
            Tpenyulit.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 6).toString());
            cmbJenkel.setSelectedItem(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString());
            TbrtLahir.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 8).toString());
            TkeadaanAnak.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 9).toString());
        }
    }
    
    private void variabelBersih() {
        wktSimpan = "";
        stsrwt = "";
        sendiri = "";
        rujukan = "";
        pkm = "";
        spog = "";
        rsLain = "";
        dismen = "";
        spoting = "";
        menor = "";
        metro = "";
        lainKeluhanHaid = "";
        hipertensiDahulu = "";
        dmDahulu = "";
        jantungDahulu = "";
        asmaDahulu = "";
        lainyaDahulu = "";
        hipertensiKeluarga = "";
        dmKeluarga = "";
        jantungKeluarga = "";
        asmaKeluarga = "";
        lainyaKeluarga = "";
        pil = "";
        suntik1 = "";
        suntik3 = "";
        implan = "";
        iud = "";
        tidakKb = "";
        istriKawin = "";
        suamiKawin = "";
        orangTua = "";
        suami = "";
        anak = "";
        tinggalSendiri = "";
        asuransi = "";
        jaminan = "";
        biayaSendiri = "";
        lainStatusEkonomi = "";
        bersih = "";
        oedema = "";
        ruftur = "";
        candiloma = "";
        lainPemeriksaanGeni = "";
        alamatSama = "";
    }
    
    private void tampilPreview() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rsPrev = koneksi.prepareStatement("SELECT ak1.*, ak2.*, pg1.nama nmBidan, pg2.nama nmDokter, pg3.nama nmBidanDp, concat(p.nm_pasien,' (No. RM : ',p.no_rkm_medis,')') nm_pasien, "
                        + "concat(rp.umurdaftar,' ',rp.sttsumur,' (Tgl. Lahir : ',date_format(p.tgl_lahir,'%d-%m-%Y'),')') umurPasien, p.pekerjaan, p.agama, "
                        + "concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', Kab. ',kb.nm_kab) almtPasien, time_format(ak1.jam_asesmen,'%H:%i') jamAses "
                        + "FROM asesmen_awal_kebidanan1 ak1 inner join reg_periksa rp on rp.no_rawat=ak1.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join kelurahan kl on kl.kd_kel=p.kd_kel inner join kecamatan kc on kc.kd_kec=p.kd_kec inner join kabupaten kb on kb.kd_kab=p.kd_kab "
                        + "left join asesmen_awal_kebidanan2 ak2 on ak1.no_rawat=ak2.no_rawat left join pegawai pg1 on pg1.nik=ak2.nip_bidan "
                        + "left join pegawai pg2 on pg2.nik=ak2.nip_dokter left join pegawai pg3 on pg3.nik=ak2.nip_bidan_dp where ak1.no_rawat='" + TNoRw.getText() + "'").executeQuery();
                if (rsPrev.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='79%'>"
                            + "<table width='100%' border='0' colspan='8' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + "<tr align='center'>"
                            + "<td valign='top' colspan='6' bgcolor='#f8fdf3'>Ruangan</td>"
                            + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Tanggal</td>"
                            + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Pukul</td>"
                            + "</tr>");
                    rsPrev.beforeFirst();
                    while (rsPrev.next()) {
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='6'>" + rsPrev.getString("ruang_rawat") + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + Valid.SetTglINDONESIA(rsPrev.getString("tgl_asesmen")) + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + rsPrev.getString("jamAses") + " Wita</td>"
                                + "</tr>");

                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' align='center' colspan='8' bgcolor='#f8fdf3'><span style='font-weight:bold'>IDENTITAS PASIEN</span></td>"
                                + "<tr align='center'>"
                                + "<td valign='top' colspan='4' align='left'><span style='font-weight:bold'>Pasien</span></td>"                                
                                + "<td valign='top' colspan='4' align='left'><span style='font-weight:bold'>Suami</span></td>"
                                + "</tr>");

                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Nama</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("nm_pasien") + "</td>"
                                + "<td valign='top'>Nama</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("nm_suami") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Umur</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("umurPasien") + "</td>"
                                + "<td valign='top'>Umur</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("umur_suami") + " tahun</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Pekerjaan</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("pekerjaan") + "</td>"
                                + "<td valign='top'>Pekerjaan</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("pekerjaan_suami") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Alamat</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("almtPasien") + "</td>"
                                + "<td valign='top'>Alamat</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("alamat_suami") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Agama</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("agama") + "</td>"
                                + "<td valign='top'>Agama</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("agama_suami") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><span style='font-weight:bold'>Alasan Masuk RS</span></td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("alasan_masuk") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='8' bgcolor='#f8fdf3'><span style='font-weight:bold'>Keadaan Umum</span></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='2' bgcolor='#f8fdf3' align='center'>Tekanan Darah</td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'>Nadi</td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'>Respirasi</td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'>Suhu</td>"
                                + "<td valign='top' colspan='2' bgcolor='#f8fdf3' align='center'>Kesadaran</td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'>Saturasi</td>"                                
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"                                
                                + "<td valign='top' colspan='2' align='center'>" + rsPrev.getString("td") + " mmHg</td>"
                                + "<td valign='top' colspan='1' align='center'>" + rsPrev.getString("nadi") + " x/menit</td>"
                                + "<td valign='top' colspan='1' align='center'>" + rsPrev.getString("respirasi") + " x/menit</td>"
                                + "<td valign='top' colspan='1' align='center'>" + rsPrev.getString("suhu") + " °C</td>"
                                + "<td valign='top' colspan='2' align='center'>" + rsPrev.getString("kesadaran") + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + rsPrev.getString("saturasi") + " %</td>"
                                + "</tr>");
                        
                        String prevSendiri = "", prevRujukan = "", prevPKM = "", prevSpog = "", prevRsLain = "";
                        if (rsPrev.getString("cek_sendiri").equals("ya")) {
                            prevSendiri = "Sendiri, ";
                        } else {
                            prevSendiri = "";
                        }

                        if (rsPrev.getString("cek_rujukan").equals("ya")) {
                            if (rsPrev.getString("ket_jns_rujukan").equals("")) {
                                prevRujukan = "Rujukan " + rsPrev.getString("jns_rujukan") + ", ";
                            } else {
                                prevRujukan = "Rujukan " + rsPrev.getString("jns_rujukan") + " : " + rsPrev.getString("ket_jns_rujukan") + ", ";
                            }
                        } else {
                            prevRujukan = "";
                        }
                        
                        if (rsPrev.getString("cek_pkm").equals("ya")) {
                            if (rsPrev.getString("ket_pkm").equals("")) {
                                prevPKM = "PKM, ";
                            } else {
                                prevPKM = "PKM : " + rsPrev.getString("ket_pkm") + ", ";
                            }
                        } else {
                            prevPKM = "";
                        }
                        
                        if (rsPrev.getString("cek_spog").equals("ya")) {
                            prevSpog = "SPGO, ";
                        } else {
                            prevSpog = "";
                        }
                        
                        if (rsPrev.getString("cek_rs_lain").equals("ya")) {
                            if (rsPrev.getString("ket_rs_lain").equals("")) {
                                prevRsLain = "RS Lain";
                            } else {
                                prevRsLain = "RS Lain : " + rsPrev.getString("ket_rs_lain");
                            }
                        } else {
                            prevRsLain = "";
                        }
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><span style='font-weight:bold'>Cara Pasien Datang</span></td>"
                                + "<td valign='top' colspan='7'>: " + prevSendiri + prevRujukan + prevPKM + prevSpog + prevRsLain + "</td>"
                                + "</tr>");
                        
                        String prevGr = "", prevPr = "", prevA = "", prevHamil = "", prevGpapah = "";
                        if (rsPrev.getString("gr").equals("")) {
                            prevGr = "Gr ........, ";
                        } else {
                            prevGr = "Gr " + rsPrev.getString("gr") + ", ";
                        }
                        
                        if (rsPrev.getString("pr").equals("")) {
                            prevPr = "Pr ........, ";
                        } else {
                            prevPr = "Pr " + rsPrev.getString("pr") + ", ";
                        }
                        
                        if (rsPrev.getString("a").equals("")) {
                            prevA = "A ........, ";
                        } else {
                            prevA = "A " + rsPrev.getString("a") + ", ";
                        }
                        
                        if (rsPrev.getString("hamil").equals("")) {
                            prevHamil = "Hamil : ........ Minggu, ";
                        } else {
                            prevHamil = "Hamil : " + rsPrev.getString("hamil") + " Minggu, ";
                        }
                        
                        if (rsPrev.getString("gpapah").equals("")) {
                            prevGpapah = "G.PAPAH : ........";
                        } else {
                            prevGpapah = "G.PAPAH : " + rsPrev.getString("gpapah");
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"                                
                                + "<td valign='top' colspan='8'>" + prevGr + prevPr + prevA + prevHamil + prevGpapah + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Dengan</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("dengan") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='8' bgcolor='#f8fdf3'><span style='font-weight:bold'>Keluhan</span></td>"
                                + "</tr>");
                        
                        String prevPerut = "", nilaiPerut = "", nilaiNyeriUlu = "", prevKeluar = "", nilaiKeluar = "", nilaiPandang = "", prevDarah = "", nilaiDarah = "",
                                jnsDrh = "", nilaiOdema = "", odemaDi = "", nilaiKeluarAir = "", klrAir = "", jnsKlrAir = "", nilaiMual = "", nilaiMuntah = "", nilaiPerge = "",
                                nilaiPusing = "", nilaiBatuk = "", nilaiPilek = "", nilaiDemam = "", nilaiPerjalanan = "", ketPerjalanan = "", nilaiVaksin = "", ketVaksin = "",
                                ketPeriksaBidan = "", nilaiIbu = "", prevAnc = "", ketDokter1 = "", ketDokter2 = "", ketDokter3 = "", dr1 = "", dr2 = "", dr3 = "";
                        if (rsPrev.getString("perut").equals("Ya")) {
                            if (rsPrev.getString("keluhan_perut").equals("-")) {
                                prevPerut = "Perut Mulai";
                            } else {
                                prevPerut = "Perut " + rsPrev.getString("keluhan_perut") + " Mulai";
                            }
                            nilaiPerut = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_perut")) + ", Jam : " + rsPrev.getString("jam_perut").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("perut").equals("Tidak")) {
                            prevPerut = "Perut Mules/Nyeri Mulai";
                            nilaiPerut = ": Tidak";
                        } else {
                            prevPerut = "Perut Mules/Nyeri Mulai";
                            nilaiPerut = ": -";
                        }
                        
                        if (rsPrev.getString("nyeri_ulu_hati").equals("Ya")) {
                            nilaiNyeriUlu = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_nyeri_ulu_hati")) + ", Jam : " + rsPrev.getString("jam_nyeri_ulu_hati").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("nyeri_ulu_hati").equals("Tidak")) {
                            nilaiNyeriUlu = ": Tidak";
                        } else {
                            nilaiNyeriUlu = ": -";
                        }
                        
                        if (rsPrev.getString("keluar").equals("Ya")) {
                            if (rsPrev.getString("keluhan_keluar").equals("-")) {
                                prevKeluar = "Keluar ?";
                            } else {
                                prevKeluar = "Keluar " + rsPrev.getString("keluhan_keluar");
                            }
                            nilaiKeluar = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_keluar_lendir")) + ", Jam : " + rsPrev.getString("jam_keluar_lendir").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("keluar").equals("Tidak")) {
                            prevKeluar = "Keluar lendir darah/darah";
                            nilaiKeluar = ": Tidak";
                        } else {
                            prevKeluar = "Keluar lendir darah/darah";
                            nilaiKeluar = ": -";
                        }
                        
                        if (rsPrev.getString("pandangan_kabur").equals("Ya")) {
                            nilaiPandang = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_pandangan_kabur")) + ", Jam : " + rsPrev.getString("jam_pandangan_kabur").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("pandangan_kabur").equals("Tidak")) {
                            nilaiPandang = ": Tidak";
                        } else {
                            nilaiPandang = ": -";
                        }
                        
                        if (rsPrev.getString("darah").equals("Ya")) {
                            if (rsPrev.getString("keluhan_darah").equals("-")) {
                                prevDarah = "Darah ?";
                            } else {
                                prevDarah = "Darah " + rsPrev.getString("keluhan_darah");
                            }
                            
                            if (rsPrev.getString("jns_darah").equals("-")) {
                                jnsDrh = ": Ya, ? mulai tgl. ";
                            } else {
                                jnsDrh = ": Ya, " + rsPrev.getString("jns_darah") + " mulai tgl. ";
                            }
                            nilaiDarah = jnsDrh + Valid.SetTglINDONESIA(rsPrev.getString("tgl_darah")) + ", Jam : " + rsPrev.getString("jam_darah").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("darah").equals("Tidak")) {
                            prevDarah = "Darah encer/segar/bergumpal";
                            nilaiDarah = ": Tidak";
                        } else {
                            prevDarah = "Darah encer/segar/bergumpal";
                            nilaiDarah = ": -";
                        }
                        
                        if (rsPrev.getString("odema").equals("Ya")) {
                            if (rsPrev.getString("odema_di").equals("-")) {
                                odemaDi = ", ?";
                            } else {
                                odemaDi = ", di " + rsPrev.getString("odema_di");
                            }
                            nilaiOdema = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_odema")) + odemaDi;
                        } else if (rsPrev.getString("odema").equals("Tidak")) {
                            nilaiOdema = ": Tidak";
                        } else {
                            nilaiOdema = ": -";
                        }
                        
                        if (rsPrev.getString("keluar_air").equals("Ya")) {
                            if (rsPrev.getString("jns_keluar_air").equals("-")) {
                                jnsKlrAir = ": Ya, ? mulai tgl. ";
                            } else {
                                jnsKlrAir = ": Ya, " + rsPrev.getString("jns_keluar_air") + " mulai tgl. ";
                            }
                            
                            if (rsPrev.getString("keluhan_keluar_air").equals("-")) {
                                klrAir = ", ?";
                            } else {
                                klrAir = ", " + rsPrev.getString("keluhan_keluar_air");
                            }
                            nilaiKeluarAir = jnsKlrAir + Valid.SetTglINDONESIA(rsPrev.getString("tgl_keluar_air")) + ", Jam : " + rsPrev.getString("jam_keluar_air").substring(0, 5) + " Wita" + klrAir;
                        } else if (rsPrev.getString("keluar_air").equals("Tidak")) {                            
                            nilaiKeluarAir = ": Tidak";
                        } else {                            
                            nilaiKeluarAir = ": -";
                        }
                        
                        if (rsPrev.getString("mual").equals("Ya")) {
                            nilaiMual = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_mual")) + ", Jam : " + rsPrev.getString("jam_mual").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("mual").equals("Tidak")) {
                            nilaiMual = ": Tidak";
                        } else {
                            nilaiMual = ": -";
                        }
                        
                        if (rsPrev.getString("pergerakan_janin_2jam_terakhir").equals("Ada")) {
                            if (rsPrev.getString("ket_pergerakan_janin_2jam_terakhir").equals("")) {
                                nilaiPerge = ": Ada";
                            } else {
                                nilaiPerge = ": Ada, " + rsPrev.getString("ket_pergerakan_janin_2jam_terakhir") + " X";
                            }                            
                        } else if (rsPrev.getString("pergerakan_janin_2jam_terakhir").equals("Tidak Ada")) {
                            nilaiPerge = ": Tidak Ada";
                        } else {
                            nilaiPerge = ": -";
                        }
                        
                        if (rsPrev.getString("muntah").equals("Ya")) {
                            nilaiMuntah = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_muntah")) + ", Jam : " + rsPrev.getString("jam_muntah").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("muntah").equals("Tidak")) {
                            nilaiMuntah = ": Tidak";
                        } else {
                            nilaiMuntah = ": -";
                        }
                        
                        if (rsPrev.getString("pusing").equals("Ya")) {
                            nilaiPusing = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_pusing")) + ", Jam : " + rsPrev.getString("jam_pusing").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("pusing").equals("Tidak")) {
                            nilaiPusing = ": Tidak";
                        } else {
                            nilaiPusing = ": -";
                        }
                        
                        if (rsPrev.getString("batuk").equals("Ya")) {
                            nilaiBatuk = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_batuk")) + ", Jam : " + rsPrev.getString("jam_batuk").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("batuk").equals("Tidak")) {
                            nilaiBatuk = ": Tidak";
                        } else {
                            nilaiBatuk = ": -";
                        }
                        
                        if (rsPrev.getString("pilek").equals("Ya")) {
                            nilaiPilek = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_pilek")) + ", Jam : " + rsPrev.getString("jam_pilek").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("pilek").equals("Tidak")) {
                            nilaiPilek = ": Tidak";
                        } else {
                            nilaiPilek = ": -";
                        }
                        
                        if (rsPrev.getString("demam").equals("Ya")) {
                            nilaiDemam = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_demam")) + ", Jam : " + rsPrev.getString("jam_demam").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("demam").equals("Tidak")) {
                            nilaiDemam = ": Tidak";
                        } else {
                            nilaiDemam = ": -";
                        }
                        
                        if (rsPrev.getString("riw_perjalanan_jauh").equals("Ya")) {
                            if (rsPrev.getString("ket_riw_perjalanan_jauh").equals("")) {
                                ketPerjalanan = "";
                            } else {
                                ketPerjalanan = ", " + rsPrev.getString("ket_riw_perjalanan_jauh");
                            }
                            nilaiPerjalanan = ": Ya" + ketPerjalanan;
                        } else if (rsPrev.getString("riw_perjalanan_jauh").equals("Tidak")) {
                            nilaiPerjalanan = ": Tidak";
                        } else {
                            nilaiPerjalanan = ": -";
                        }
                        
                        if (rsPrev.getString("vaksin_covid19").equals("Ya")) {
                            if (rsPrev.getString("jlh_vaksin_covid19").equals("")) {
                                ketVaksin = "";
                            } else {
                                ketVaksin = ", " + rsPrev.getString("jlh_vaksin_covid19") + " X";
                            }
                            nilaiVaksin = ": Ya" + ketVaksin;
                        } else if (rsPrev.getString("vaksin_covid19").equals("Tidak")) {
                            nilaiVaksin = ": Tidak";
                        } else {
                            nilaiVaksin = ": -";
                        }
                        
                        if (rsPrev.getString("hasil_pemeriksaan_bidan").equals("")) {
                            ketPeriksaBidan = ": -";
                        } else {
                            ketPeriksaBidan = ": " + rsPrev.getString("hasil_pemeriksaan_bidan");
                        }

                        if (rsPrev.getString("ibu_anc").equals("Ya")) {
                            if (rsPrev.getString("jns_anc").equals("-")) {
                                prevAnc = "Ibu ANC di ?";
                            } else {
                                prevAnc = "Ibu ANC di " + rsPrev.getString("jns_anc");
                            }

                            if (rsPrev.getString("jlh_anc").equals("")) {
                                nilaiIbu = ": Ya, -";
                            } else {
                                nilaiIbu = ": Ya, " + rsPrev.getString("jlh_anc") + " X";
                            }
                            
                            if (rsPrev.getString("dengan_dokter1").equals("")) {
                                dr1 = "";
                            } else {
                                dr1 = ", Dengan : " + rsPrev.getString("dengan_dokter1");
                            }
                            
                            if (rsPrev.getString("jlh_dengan_dokter1").equals("")) {
                                ketDokter1 = "";
                            } else {
                                ketDokter1 = ", " + rsPrev.getString("jlh_dengan_dokter1") + " X";
                            }
                            
                            if (rsPrev.getString("dengan_dokter2").equals("")) {
                                dr2 = "";
                            } else {
                                dr2 = ", Dengan : " + rsPrev.getString("dengan_dokter2");
                            }
                            
                            if (rsPrev.getString("jlh_dengan_dokter2").equals("")) {
                                ketDokter2 = "";
                            } else {
                                ketDokter2 = ", " + rsPrev.getString("jlh_dengan_dokter2") + " X";
                            }
                            
                            if (rsPrev.getString("dengan_dokter3").equals("")) {
                                dr3 = "";
                            } else {
                                dr3 = ", Dengan : " + rsPrev.getString("dengan_dokter3");
                            }
                            
                            if (rsPrev.getString("jlh_dengan_dokter3").equals("")) {
                                ketDokter3 = "";
                            } else {
                                ketDokter3 = ", " + rsPrev.getString("jlh_dengan_dokter3") + " X";
                            }
                        } else if (rsPrev.getString("ibu_anc").equals("Tidak")) {
                            prevAnc = "Ibu ANC di PKM/Bidan";
                            nilaiIbu = ": Tidak";
                        } else {
                            prevAnc = "Ibu ANC di PKM/Bidan";
                            nilaiIbu = ": -";
                        }
                        
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>" + prevPerut + "</td>"
                                + "<td valign='top' colspan='3'>" + nilaiPerut + "</td>"
                                + "<td valign='top'>Nyeri Ulu Hati</td>"
                                + "<td valign='top' colspan='3'>" + nilaiNyeriUlu + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>" + prevKeluar + "</td>"
                                + "<td valign='top' colspan='3'>" + nilaiKeluar + "</td>"
                                + "<td valign='top'>Pandangan Kabur</td>"
                                + "<td valign='top' colspan='3'>" + nilaiPandang + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>" + prevDarah + "</td>"
                                + "<td valign='top' colspan='3'>" + nilaiDarah + "</td>"
                                + "<td valign='top'>Odema</td>"
                                + "<td valign='top' colspan='3'>" + nilaiOdema + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Keluar air-air</td>"
                                + "<td valign='top' colspan='3'>" + nilaiKeluarAir + "</td>"
                                + "<td valign='top'>Mual</td>"
                                + "<td valign='top' colspan='3'>" + nilaiMual + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Pergerakan Janin 2 Jam Terakhir</td>"
                                + "<td valign='top' colspan='3'>" + nilaiPerge + "</td>"
                                + "<td valign='top'>Muntah</td>"
                                + "<td valign='top' colspan='3'>" + nilaiMuntah + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Pusing</td>"
                                + "<td valign='top' colspan='3'>" + nilaiPusing + "</td>"
                                + "<td valign='top'>Batuk</td>"
                                + "<td valign='top' colspan='3'>" + nilaiBatuk + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Pilek</td>"
                                + "<td valign='top' colspan='3'>" + nilaiPilek + "</td>"
                                + "<td valign='top'>Demam</td>"
                                + "<td valign='top' colspan='3'>" + nilaiDemam + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Riwayat Perjalanan Jauh</td>"
                                + "<td valign='top' colspan='3'>" + nilaiPerjalanan + "</td>"
                                + "<td valign='top'>Vaksin COVID 19</td>"
                                + "<td valign='top' colspan='3'>" + nilaiVaksin + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Periksa Ketempat Bidan</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("periksa_ketempat_bidan") + ", Hasil / Riwayat Pemeriksaan Bidan " + ketPeriksaBidan + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>" + prevAnc + "</td>"
                                + "<td valign='top' colspan='7'>" + nilaiIbu + dr1 + ketDokter1 + dr2 + ketDokter2 + dr3 + ketDokter3 + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>RIWAYAT KEHAMILAN SEKARANG</span></td>"
                                + "</tr>");
                        
                        String prevHpht = "", prevHpl = "", prevUk = "", prevBbBelum = "", prevBbTerakhir = "", prevTbi = "";
                        if (rsPrev.getString("hpht").equals("")) {
                            prevHpht = "HPHT : ........, ";
                        } else {
                            prevHpht = "HPHT : " + rsPrev.getString("hpht") + ", ";
                        }
                        
                        if (rsPrev.getString("hpl").equals("")) {
                            prevHpl = "HPL : ........, ";
                        } else {
                            prevHpl = "HPL : " + rsPrev.getString("hpl") + ", ";
                        }
                        
                        if (rsPrev.getString("uk").equals("")) {
                            prevUk = "UK : ........, ";
                        } else {
                            prevUk = "UK : " + rsPrev.getString("uk") + " mg, ";
                        }
                        
                        if (rsPrev.getString("bb_sebelum_hamil").equals("")) {
                            prevBbBelum = "BB Sebelum Hamil : ........ Kg, ";
                        } else {
                            prevBbBelum = "BB Sebelum Hamil : " + rsPrev.getString("bb_sebelum_hamil") + " Kg, ";
                        }
                        
                        if (rsPrev.getString("bb_terakhir").equals("")) {
                            prevBbTerakhir = "BB Terakhir : ........ Kg, ";
                        } else {
                            prevBbTerakhir = "BB Terakhir : " + rsPrev.getString("bb_terakhir")+" Kg, ";
                        }
                        
                        if (rsPrev.getString("tbi").equals("")) {
                            prevTbi = "TBI : ........ Cm, ";
                        } else {
                            prevTbi = "TBI : " + rsPrev.getString("tbi")+" Cm";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"                                
                                + "<td valign='top' colspan='8'>" + prevHpht + prevHpl + prevUk + prevBbBelum + prevBbTerakhir + prevTbi + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='8' bgcolor='#f8fdf3'><span style='font-weight:bold'>Riwayat Haid</span></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'>Umur Pertama Kali Haid</td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'>Lamanya Haid</td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'>Berapa Kali Ganti Pembalut</td>"
                                + "<td valign='top' colspan='5' bgcolor='#f8fdf3' align='left'>Keluhan Waktu Haid</td>"
                                + "</tr>");
                        
                        String prevUmurPertama = "", prevLamaHaid = "", prevBerapa = "", prevKelWaktuHaid = "", prevDismen = "", prevSpot = "", prevMenor = "", prevMetro = "", 
                                prevLainRiwHaid = "", ketLainRiwHaid = "";
                        if (rsPrev.getString("umur_pertama_haid").equals("")) {
                            prevUmurPertama = "-";
                        } else {
                            prevUmurPertama = rsPrev.getString("umur_pertama_haid") + " tahun";
                        }
                        
                        if (rsPrev.getString("lama_haid").equals("")) {
                            prevLamaHaid = "-";
                        } else {
                            prevLamaHaid = rsPrev.getString("lama_haid") + " hari";
                        }
                        
                        if (rsPrev.getString("berapa_kali_ganti_pembalut").equals("")) {
                            prevBerapa = "-";
                        } else {
                            prevBerapa = rsPrev.getString("berapa_kali_ganti_pembalut") + " x/hari";
                        }
                        
                        if (rsPrev.getString("keluhan_waktu_haid").equals("Ada")) {
                            if (rsPrev.getString("cek_dismen").equals("ya")) {
                                prevDismen = "Dismenorhoe, ";
                            } else {
                                prevDismen = "";
                            }
                            
                            if (rsPrev.getString("cek_spoting").equals("ya")) {
                                prevSpot = "Spotting, ";
                            } else {
                                prevSpot = "";
                            }
                            
                            if (rsPrev.getString("cek_menor").equals("ya")) {
                                prevMenor = "Menorhagia, ";
                            } else {
                                prevMenor = "";
                            }
                            
                            if (rsPrev.getString("cek_metro").equals("ya")) {
                                prevMetro = "Metrorhagia, ";
                            } else {
                                prevMetro = "";
                            }
                            
                            if (rsPrev.getString("cek_lain_keluhan_haid").equals("ya")) {                                
                                if (rsPrev.getString("ket_lain_keluhan_haid").equals("")) {
                                    ketLainRiwHaid = "?";
                                } else {
                                    ketLainRiwHaid = rsPrev.getString("ket_lain_keluhan_haid");
                                }
                                prevLainRiwHaid = "Lainnya : " + ketLainRiwHaid;
                            } else {
                                prevLainRiwHaid = "";
                            }
                            prevKelWaktuHaid = "Ada, " + prevDismen + prevSpot + prevMenor + prevMetro + prevLainRiwHaid;
                        } else {
                            prevKelWaktuHaid = "Tidak Ada";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' align='center'>" + prevUmurPertama + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + prevLamaHaid + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + prevBerapa + "</td>"
                                + "<td valign='top' colspan='5' align='left'>" + prevKelWaktuHaid + "</td>"
                                + "</tr>");
                        
                        String prevRiwPenDahulu = "", prevHiperRPD = "", prevDmRPD = "", prevJantungRPD = "", prevAsmaRPD = "", prevRiwLainRPD = "", ketRiwLainRPD = "";
                        if (rsPrev.getString("riw_penyakit_dahulu").equals("Ada")) {
                            if (rsPrev.getString("cek_hipertensi_dahulu").equals("ya")) {
                                prevHiperRPD = "Hipertensi, ";
                            } else {
                                prevHiperRPD = "";
                            }
                            
                            if (rsPrev.getString("cek_dm_dahulu").equals("ya")) {
                                prevDmRPD = "DM, ";
                            } else {
                                prevDmRPD = "";
                            }
                            
                            if (rsPrev.getString("cek_jantung_dahulu").equals("ya")) {
                                prevJantungRPD = "Jantung, ";
                            } else {
                                prevJantungRPD = "";
                            }
                            
                            if (rsPrev.getString("cek_asma_dahulu").equals("ya")) {
                                prevAsmaRPD = "Asma, ";
                            } else {
                                prevAsmaRPD = "";
                            }
                            
                            if (rsPrev.getString("cek_lainya_dahulu").equals("ya")) {                                
                                if (rsPrev.getString("ket_lain_penyakit_dahulu").equals("")) {
                                    ketRiwLainRPD = "?";
                                } else {
                                    ketRiwLainRPD = rsPrev.getString("ket_lain_penyakit_dahulu");
                                }
                                prevRiwLainRPD = "Lainnya : " + ketRiwLainRPD;
                            } else {
                                prevRiwLainRPD = "";
                            }
                            prevRiwPenDahulu = "Ada, " + prevHiperRPD + prevDmRPD + prevJantungRPD + prevAsmaRPD + prevRiwLainRPD;
                        } else {
                            prevRiwPenDahulu = "Tidak Ada";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><b>Riwayat Penyakit Dahulu</b></td>"
                                + "<td valign='top' colspan='7'>: " + prevRiwPenDahulu + "</td>"
                                + "</tr>");
                        
                        String prevRiwPenKlg = "", prevHiperKlg = "", prevDmKlg = "", prevJantungKlg = "", prevAsmaKlg = "", prevRiwLainKlg = "", ketRiwLainKlg = "";
                        if (rsPrev.getString("riw_penyakit_keluarga").equals("Ada")) {
                            if (rsPrev.getString("cek_hipertensi_keluarga").equals("ya")) {
                                prevHiperKlg = "Hipertensi, ";
                            } else {
                                prevHiperKlg = "";
                            }
                            
                            if (rsPrev.getString("cek_dm_keluarga").equals("ya")) {
                                prevDmKlg = "DM, ";
                            } else {
                                prevDmKlg = "";
                            }
                            
                            if (rsPrev.getString("cek_jantung_keluarga").equals("ya")) {
                                prevJantungKlg = "Jantung, ";
                            } else {
                                prevJantungKlg = "";
                            }
                            
                            if (rsPrev.getString("cek_asma_keluarga").equals("ya")) {
                                prevAsmaKlg = "Asma, ";
                            } else {
                                prevAsmaKlg = "";
                            }
                            
                            if (rsPrev.getString("cek_lainya_keluarga").equals("ya")) {
                                if (rsPrev.getString("ket_lain_penyakit_keluarga").equals("")) {
                                    ketRiwLainKlg = "?";
                                } else {
                                    ketRiwLainKlg = rsPrev.getString("ket_lain_penyakit_keluarga");
                                }
                                prevRiwLainKlg = "Lainnya : " + ketRiwLainKlg;
                            } else {
                                prevRiwLainKlg = "";
                            }
                            prevRiwPenKlg = "Ada, " + prevHiperKlg + prevDmKlg + prevJantungKlg + prevAsmaKlg + prevRiwLainKlg;
                        } else {
                            prevRiwPenKlg = "Tidak Ada";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><b>Riwayat Penyakit Keluarga</b></td>"
                                + "<td valign='top' colspan='7'>: " + prevRiwPenKlg + "</td>"
                                + "</tr>");
                                                
                        String prevRiwGinekologi = "", ketRiwGine = "";
                        if (rsPrev.getString("riw_ginekologi").equals("Ada")) {
                            if (rsPrev.getString("ket_ginekologi").equals("")) {
                                ketRiwGine = " .......";
                            } else {
                                ketRiwGine = ", " + rsPrev.getString("ket_ginekologi");
                            }

                            prevRiwGinekologi = "Ada" + ketRiwGine;
                        } else {
                            prevRiwGinekologi = "Tidak Ada";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><b>Riwayat Ginekologi</b></td>"
                                + "<td valign='top' colspan='7'>: " + prevRiwGinekologi + "</td>"
                                + "</tr>");
                        
                        String prevPil = "", prevSuntik1 = "", prevSuntik3 = "", prevImplan = "", prevIud = "", prevTdkKb = "", lamaPil = "", lamaSuntik1 = "", lamaSuntik3 = "",
                                lamaImplan = "", lamaIud = "", satPil = "", satSuntik1 = "", satSuntik3 = "", satImplan = "", satIud = "";
                        
                        if (rsPrev.getString("cek_pil").equals("ya")) {
                            if (rsPrev.getString("lama_pil").equals("")) {
                                lamaPil = ".....";
                            } else {
                                if (rsPrev.getString("satuan_lama_pil").equals("-")) {
                                    satPil = ", ";
                                } else {
                                    satPil = rsPrev.getString("satuan_lama_pil");
                                }
                                lamaPil = rsPrev.getString("lama_pil") + " " + satPil + ", ";
                            }                            
                            prevPil = "Pil, lama : " + lamaPil;
                        } else {
                            prevPil = "";
                        }
                        
                        if (rsPrev.getString("cek_suntik1").equals("ya")) {
                            if (rsPrev.getString("lama_suntik1").equals("")) {
                                lamaSuntik1 = ".....";
                            } else {
                                if (rsPrev.getString("satuan_lama_suntik1").equals("-")) {
                                    satSuntik1 = ", ";
                                } else {
                                    satSuntik1 = rsPrev.getString("satuan_lama_suntik1");
                                }
                                lamaSuntik1 = rsPrev.getString("lama_suntik1") + " " + satSuntik1 + ", ";
                            }                            
                            prevSuntik1 = "Suntik 1 bulan, lama : " + lamaSuntik1;
                        } else {
                            prevSuntik1 = "";
                        }
                        
                        if (rsPrev.getString("cek_suntik3").equals("ya")) {
                            if (rsPrev.getString("lama_suntik3").equals("")) {
                                lamaSuntik3 = ".....";
                            } else {
                                if (rsPrev.getString("satuan_lama_suntik3").equals("-")) {
                                    satSuntik3 = ", ";
                                } else {
                                    satSuntik3 = rsPrev.getString("satuan_lama_suntik3");
                                }
                                lamaSuntik3 = rsPrev.getString("lama_suntik3") + " " + satSuntik3 + ", ";
                            }                            
                            prevSuntik3 = "Suntik 3 bulan, lama : " + lamaSuntik3;
                        } else {
                            prevSuntik3 = "";
                        }
                        
                        if (rsPrev.getString("cek_implan").equals("ya")) {
                            if (rsPrev.getString("lama_implan").equals("")) {
                                lamaImplan = ".....";
                            } else {
                                if (rsPrev.getString("satuan_lama_implan").equals("-")) {
                                    satImplan = ", ";
                                } else {
                                    satImplan = rsPrev.getString("satuan_lama_implan");
                                }
                                lamaImplan = rsPrev.getString("lama_implan") + " " + satImplan + ", ";
                            }                            
                            prevImplan = "Implan, lama : " + lamaImplan;
                        } else {
                            prevImplan = "";
                        }
                        
                        if (rsPrev.getString("cek_iud").equals("ya")) {
                            if (rsPrev.getString("lama_iud").equals("")) {
                                lamaIud = ".....";
                            } else {
                                if (rsPrev.getString("satuan_lama_iud").equals("-")) {
                                    satIud = ", ";
                                } else {
                                    satIud = rsPrev.getString("satuan_lama_iud");
                                }
                                lamaIud = rsPrev.getString("lama_iud") + " " + satIud + ", ";
                            }                            
                            prevIud = "IUD, lama : " + lamaIud;
                        } else {
                            prevIud = "";
                        }
                        
                        if (rsPrev.getString("cek_tidak_kb").equals("ya")) {
                            prevTdkKb = "Tidak pernah KB";
                        } else {
                            prevTdkKb = "";
                        }                        
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><b>Riwayat KB</b></td>"
                                + "<td valign='top' colspan='7'>: " + prevPil + prevSuntik1 + prevSuntik3 + prevImplan + prevIud + prevTdkKb + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>RIWAYAT KEHAMILAN, PERSALINAN DAN NIFAS YANG LALU</span></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Tahun Partus</td>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Tempat Partus</td>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Umur Hamil</td>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Jenis Persalinan</td>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Penolong Persalinan</td>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Penyulit</td>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Jenis Kelamin/Berat Lahir</td>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Keadaan Anak Sekarang</td>"
                                + "</tr>");

                        try {
                            ps3 = koneksi.prepareStatement("select * from riwayat_kehamilan_asesmen_awal_kebidanan where no_rawat='" + rsPrev.getString("no_rawat") + "' order by waktu_simpan");
                            try {
                                rs3 = ps3.executeQuery();
                                while (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' align='center'>" + rs3.getString("tahun_partus") + "</td>"
                                            + "<td valign='top' align='left'>" + rs3.getString("tempat_partus") + "</td>"
                                            + "<td valign='top' align='left'>" + rs3.getString("umur_hamil") + "</td>"
                                            + "<td valign='top' align='left'>" + rs3.getString("jns_persalinan") + "</td>"
                                            + "<td valign='top' align='left'>" + rs3.getString("penolong_persalinan") + "</td>"
                                            + "<td valign='top' align='left'>" + rs3.getString("penyulit") + "</td>"
                                            + "<td valign='top' align='left'>" + rs3.getString("jk") + " (" + rs3.getString("bb") + ")</td>"
                                            + "<td valign='top' align='left'>" + rs3.getString("keadaan_anak_skrng") + "</td>"
                                            + "</tr>");
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
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>RIWAYAT PSIKOSOSIAL DAN SPIRITUAL</span></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Status Perkawinan</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("status_perkawinan") + "</td>"
                                + "</tr>");
                        
                        String prevIstri = "", prevSuami = "";
                        if (rsPrev.getString("cek_istri_kawin").equals("ya")) {
                            prevIstri = "Istri (" + rsPrev.getString("jlh_perkawinan_istri") + "), ";
                        } else {
                            prevIstri = "-";
                        }
                        
                        if (rsPrev.getString("cek_suami_kawin").equals("ya")) {
                            prevSuami = "Suami (" + rsPrev.getString("jlh_perkawinan_suami") + ")";
                        } else {
                            prevSuami = "-";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Jumlah Perkawinan</td>"
                                + "<td valign='top' colspan='7'>: " + prevIstri + prevSuami + "</td>"
                                + "</tr>");
                        
                        String prevUsiaPertamaNikah = "", prevUsiaPerkawinan = "";
                        if (rsPrev.getString("usia_pertama_nikah").equals("")) {
                            prevUsiaPertamaNikah = "...... Tahun, Usia Perkawinan : ";
                        } else {
                            prevUsiaPertamaNikah = rsPrev.getString("usia_pertama_nikah") + " Tahun, Usia Perkawinan : ";
                        }
                        
                        if (rsPrev.getString("usia_perkawinan").equals("")) {
                            prevUsiaPerkawinan = "...... Tahun";
                        } else {
                            prevUsiaPerkawinan = rsPrev.getString("usia_perkawinan") + " Tahun";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Usia Pertama Kali Nikah</td>"
                                + "<td valign='top' colspan='7'>: " + prevUsiaPertamaNikah + prevUsiaPerkawinan + "</td>"
                                + "</tr>");
                        
                        String prevKlgDekat = "", prevHub = "", prevOrtu = "", prevTglSuami = "", prevTglAnak = "", prevTglSendiri = "";
                        if (rsPrev.getString("keluarga_terdekat").equals("")) {
                            prevKlgDekat = "......, Hubungan ";
                        } else {
                            prevKlgDekat = rsPrev.getString("keluarga_terdekat") + ", Hubungan ";
                        }
                        
                        if (rsPrev.getString("hubungan").equals("")) {
                            prevHub = "......, Tinggal Dengan : ";
                        } else {
                            prevHub = rsPrev.getString("hubungan") + ", Tinggal Dengan : ";
                        }
                        
                        if (rsPrev.getString("cek_orang_tua").equals("ya")) {
                            prevOrtu = "Orang Tua, ";
                        } else {
                            prevOrtu = "";
                        }
                        
                        if (rsPrev.getString("cek_suami").equals("ya")) {
                            prevTglSuami = "Suami, ";
                        } else {
                            prevTglSuami = "";
                        }
                        
                        if (rsPrev.getString("cek_anak").equals("ya")) {
                            prevTglAnak = "Anak, ";
                        } else {
                            prevTglAnak = "";
                        }
                        
                        if (rsPrev.getString("cek_tinggal_sendiri").equals("ya")) {
                            prevTglSendiri = "Sendiri";
                        } else {
                            prevTglSendiri = "";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Keluarga Terdekat</td>"
                                + "<td valign='top' colspan='7'>: " + prevKlgDekat + prevHub + prevOrtu + prevTglSuami + prevTglAnak + prevTglSendiri + ", Curiga Penganiayaan / Penelantaran : " + rsPrev.getString("curiga_penganiayaan") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Kegiatan Ibadah</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("kegiatan_ibadah") + ", Status Emosional : " + rsPrev.getString("status_emosional") + "</td>"
                                + "</tr>");
                        
                        String prevAsuransi = "", prevJaminan = "", prevBySendiri = "", prevLainSttsEko = "";
                        if (rsPrev.getString("cek_asuransi").equals("ya")) {
                            prevAsuransi = "Asuransi, ";
                        } else {
                            prevAsuransi = "";
                        }
                        
                        if (rsPrev.getString("cek_jaminan").equals("ya")) {
                            prevJaminan = "Jaminan, ";
                        } else {
                            prevJaminan = "";
                        }
                        
                        if (rsPrev.getString("cek_biaya_sendiri").equals("ya")) {
                            prevBySendiri = "Biaya Sendiri, ";
                        } else {
                            prevBySendiri = "";
                        }
                        
                        if (rsPrev.getString("cek_lain_status_ekonomi").equals("ya")) {
                            if (rsPrev.getString("ket_lain_status_ekonomi").equals("")) {
                                prevLainSttsEko = "Lainnya : .......";
                            } else {
                                prevLainSttsEko = "Lainnya : " + rsPrev.getString("ket_lain_status_ekonomi");
                            }                            
                        } else {
                            prevLainSttsEko = "";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Status Ekonomi</td>"
                                + "<td valign='top' colspan='7'>: " + prevAsuransi + prevJaminan + prevBySendiri + prevLainSttsEko + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"                                
                                + "<td valign='top' colspan='6' bgcolor='#f8fdf3' align='left'><span style='font-weight:bold'>Pemeriksaan OBSTETRI</span></td>"                                
                                + "<td valign='top' colspan='2' bgcolor='#f8fdf3' align='left'><span style='font-weight:bold'>Pemeriksaan GINEKOLOGI</span></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Leopold 1</td>"
                                + "<td valign='top' colspan='5'>: " + rsPrev.getString("leopold1") + "</td>"
                                + "<td valign='top' colspan='1'>Palpasi</td>"
                                + "<td valign='top' colspan='1'>: " + rsPrev.getString("palpasi") + "</td>"
                                + "</tr>");

                        String prevSebesar = "";
                        if (rsPrev.getString("sebesar").equals("")) {
                            prevSebesar = ", Sebesar ........";
                        } else {
                            prevSebesar = ", Sebesar " + rsPrev.getString("sebesar");
                        }

                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Leopold 2</td>"
                                + "<td valign='top' colspan='5'>: " + rsPrev.getString("leopold2") + "</td>"
                                + "<td valign='top' colspan='1'>Teraba Massa</td>"
                                + "<td valign='top' colspan='1'>: " + rsPrev.getString("teraba_massa") + prevSebesar + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Leopold 3</td>"
                                + "<td valign='top' colspan='5'>: " + rsPrev.getString("leopold3") + "</td>"
                                + "<td valign='top' colspan='1'>Goyang</td>"
                                + "<td valign='top' colspan='1'>: " + rsPrev.getString("goyang") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Leopold 4</td>"
                                + "<td valign='top' colspan='5'>: " + rsPrev.getString("leopold4") + "</td>"
                                + "<td valign='top' colspan='1'>Nyeri Tekan</td>"
                                + "<td valign='top' colspan='1'>: " + rsPrev.getString("nyeri_tekan") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Bandle Ring</td>"
                                + "<td valign='top' colspan='5'>: " + rsPrev.getString("bandle_ring") + "</td>"
                                + "<td valign='top' colspan='1'>VT Pembukaan</td>"
                                + "<td valign='top' colspan='1'>: " + rsPrev.getString("vt_pembukaan") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='6'>Perut Tegang Terus Menerus Seperti Papan : " + rsPrev.getString("perut_tegang") + "</td>"
                                + "<td valign='top' colspan='1'>VT Nyeri Goyang</td>"
                                + "<td valign='top' colspan='1'>: " + rsPrev.getString("vt_nyeri_goyang") + "</td>"
                                + "</tr>");

                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>TFU</span></td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>Taksiran Berat Janin</span></td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>His/Kontraksi</span></td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>Durasi</span></td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>Auskultasi</span></td>"
                                + "<td valign='top' colspan='3' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>Pemeriksaan Genitalia</span></td>"
                                + "</tr>");
                        
                        String prevTfu = "", prevTaksiran = "", prevHis = "", prevJnsHis = "", prevDurasi = "", prevJnsDurasi = "", prevAus = "", prevGeni = "",
                                prevBrsh = "", prevOdema = "", prevRuftur = "", prevCandi = "", prevLainGeni = "", prevInspekulo = "";
                        if (rsPrev.getString("tfu").equals("")) {
                            prevTfu = "-";
                        } else {
                            prevTfu = rsPrev.getString("tfu") + " Cm.";
                        }
                        
                        if (rsPrev.getString("taksiran_berat_janin").equals("")) {
                            prevTaksiran = "-";
                        } else {
                            prevTaksiran = rsPrev.getString("taksiran_berat_janin") + " gram";
                        }
                        
                        if (rsPrev.getString("jns_his_kontraksi").equals("-")) {
                            prevJnsHis = "";
                        } else {
                            prevJnsHis = " (" + rsPrev.getString("jns_his_kontraksi") + ")";
                        }
                        
                        if (rsPrev.getString("his_kontraksi").equals("")) {
                            prevHis = "-" + prevJnsHis;
                        } else {
                            prevHis = rsPrev.getString("his_kontraksi") + " x/10 menit" + prevJnsHis;
                        }
                        
                        if (rsPrev.getString("jns_durasi").equals("-")) {
                            prevJnsDurasi = "";
                        } else {
                            prevJnsDurasi = " (" + rsPrev.getString("jns_durasi") + ")";
                        }
                        
                        if (rsPrev.getString("durasi").equals("")) {
                            prevDurasi = "-" + prevJnsDurasi;
                        } else {
                            prevDurasi = rsPrev.getString("durasi") + " detik" + prevJnsDurasi;
                        }
                        
                        if (rsPrev.getString("auskultasi").equals("")) {
                            prevAus = "-";
                        } else {
                            prevAus = "DJJ " + rsPrev.getString("auskultasi") + " x/mnt";
                        }
                        
                        if (rsPrev.getString("cek_bersih").equals("ya")) {
                            prevBrsh = "Bersih, ";
                        } else {
                            prevBrsh = "";
                        }
                        
                        if (rsPrev.getString("cek_oedema").equals("ya")) {
                            prevOdema = "Oedema, ";
                        } else {
                            prevOdema = "";
                        }
                        
                        if (rsPrev.getString("cek_ruftur").equals("ya")) {
                            prevRuftur = "Ruftur, ";
                        } else {
                            prevRuftur = "";
                        }
                        
                        if (rsPrev.getString("cek_candiloma").equals("ya")) {
                            prevCandi = "Candiloma, ";
                        } else {
                            prevCandi = "";
                        }
                        
                        if (rsPrev.getString("cek_lain_pemeriksaan_geni").equals("ya")) {
                            if (rsPrev.getString("ket_lain_pemeriksaan_geni").equals("")) {
                                prevLainGeni = "Lainnya : ......";
                            } else {
                                prevLainGeni = "Lainnya : " + rsPrev.getString("ket_lain_pemeriksaan_geni");
                            }                            
                        } else {
                            prevLainGeni = "";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' align='center'>" + prevTfu + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + prevTaksiran + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + prevHis + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + prevDurasi + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + prevAus + "</td>"
                                + "<td valign='top' colspan='3' align='left'>" + prevBrsh + prevOdema + prevRuftur + prevCandi + prevLainGeni + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><b>Pemeriksaan Dalam (Obstetri)</b></td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("periksa_dalam_obstetri") + "</td>"
                                + "</tr>");
                        
                        if (rsPrev.getString("inspekulo").equals("-")) {
                            prevInspekulo = "-";
                        } else {
                            if (rsPrev.getString("hasil_inspekulo").equals("")) {
                                prevInspekulo = rsPrev.getString("inspekulo");
                            } else {
                                prevInspekulo = rsPrev.getString("inspekulo") + ", Hasil : " + rsPrev.getString("hasil_inspekulo");
                            }
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><b>Inspekulo</b></td>"
                                + "<td valign='top' colspan='7'>: " + prevInspekulo + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><b>DIAGNOSIS SEMENTARA</b></td>"
                                + "<td valign='top' colspan='5'>: " + rsPrev.getString("diagnosis_sementara") + "</td>"
                                + "<td valign='top' colspan='1'><b>ICD-10</b></td>"
                                + "<td valign='top' colspan='1'>: " + rsPrev.getString("icd_10") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><b>PLANNING</b></td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("planing") + "</td>"
                                + "</tr>");

                        //ini mulai halaman 2
                        if (Sequel.cariInteger("select count(-1) from asesmen_awal_kebidanan2 where no_rawat='" + rsPrev.getString("no_rawat") + "'") > 0) {
                            String prevNyeri = "", prevProvo = "", prevQuality = "", prevGambar = "", ipGambar = "";
                            try {
                                //cek atau ping ip addres
                                ipGambar = "192.168.0.230";
                                InetAddress inet = InetAddress.getByName(ipGambar);

                                    //ping sukses timeout 100 ms (0.1 detik)
                                if (inet.isReachable(100)) {
                                    prevGambar = "http://192.168.0.230:7183/img-rme/skala_nyeri.png";
                                    //ping gagal
                                } else {
                                    prevGambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/skala_nyeri.png";
                                }
                            } catch (Exception e) {
                                System.out.println("Notif : " + e);
                            }

                            if (rsPrev.getString("nyeri").equals("Ya")) {
                                if (rsPrev.getString("lokasi_nyeri").equals("")) {
                                    prevNyeri = "Ya, Lokasi : -";
                                } else {
                                    prevNyeri = "Ya, Lokasi : " + rsPrev.getString("lokasi_nyeri");
                                }
                            } else {
                                prevNyeri = rsPrev.getString("nyeri");
                            }

                            if (rsPrev.getString("provocation").equals("Lainnya")) {
                                if (rsPrev.getString("ket_lain_provocation").equals("")) {
                                    prevProvo = "Lainnya : -";
                                } else {
                                    prevProvo = "Lainnya : " + rsPrev.getString("ket_lain_provocation");
                                }
                            } else {
                                prevProvo = rsPrev.getString("provocation");
                            }

                            if (rsPrev.getString("quality").equals("Lainnya")) {
                                if (rsPrev.getString("ket_lain_quality").equals("")) {
                                    prevQuality = "Lainnya : -";
                                } else {
                                    prevQuality = "Lainnya : " + rsPrev.getString("ket_lain_quality");
                                }
                            } else {
                                prevQuality = rsPrev.getString("quality");
                            }

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>ASSESMEN NYERI</span></td>"
                                    + "</tr>");

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1'>Nyeri</td>"
                                    + "<td valign='top' colspan='5'>: " + prevNyeri + "</td>"
                                    + "<td valign='top' colspan='1'>Jenis : " + rsPrev.getString("jenis") + "</td>"
                                    + "<td valign='top' colspan='1'>Skala : " + rsPrev.getString("skala_nyeri") + "</td>"
                                    + "</tr>");

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='middle' colspan='1'>Provocation</td>"
                                    + "<td valign='middle' colspan='4'>: Faktor yang memperburuk rasa nyeri " + prevProvo + "</td>"
                                    + "<td valign='middle' colspan='3' rowspan='5'><img src='" + prevGambar + "' width='500' alt='Skala Nyeri'></td>"
                                    + "</tr>");

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='middle' colspan='1'>Quality</td>"
                                    + "<td valign='middle' colspan='4'>: Rasa nyeri seperti " + prevQuality + "</td>"
                                    + "</tr>");

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='middle' colspan='1'>Radiation</td>"
                                    + "<td valign='middle' colspan='4'>: Nyeri menjalar ke bagian tubuh yang lain " + rsPrev.getString("radiation") + "</td>"
                                    + "</tr>");

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='middle' colspan='1'>Severity</td>"
                                    + "<td valign='middle' colspan='4'>: Tingkat keparahan nyeri " + rsPrev.getString("severity") + "</td>"
                                    + "</tr>");

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='middle' colspan='1'>Time</td>"
                                    + "<td valign='middle' colspan='4'>: Nyeri berlangsung " + rsPrev.getString("time") + ", Lama : " + rsPrev.getString("time_lama").replaceAll("<", "&lt;").replaceAll(">", "&gt;") + "</td>"
                                    + "</tr>");

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='4' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>SKRINING GIZI AWAL</span></td>"
                                    + "<td valign='top' colspan='4' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>RIWAYAT ALERGI</span></td>"
                                    + "</tr>");
                            
                            String prevRiwAlerAda = "", prevRiwAlerTdk = "";
                            if (rsPrev.getString("cek_tidak_ada").equals("ya")) {
                                prevRiwAlerAda = "Tidak Ada, ";
                            } else {
                                prevRiwAlerAda = "";
                            }
                            
                            if (rsPrev.getString("cek_tidak_diketahui").equals("ya")) {
                                prevRiwAlerTdk = "Tidak Diketahui";
                            } else {
                                prevRiwAlerTdk = "";
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='4' align='left'>1. Apakah pasien mengalami penurunan BB yang tidak direncanakan/tidak diinginkan dalam 6 bulan terakhir ?</td>"
                                    + "<td valign='top' colspan='4' align='left'>" + prevRiwAlerAda + prevRiwAlerTdk + "</td>"
                                    + "</tr>");
                            
                            String prevLihatSkorGz1 = "", prevSkorGz1 = "", prevLihaSkorYaGz1 = "", prevSkorYaGz1 = "", prevAlergiObat = "", prevKetAlerObat = "", prevReakAlerObat = "",
                                    prevAlergiMak = "", prevKetAlerMak = "", prevReakAlerMak = "", prevAlergiLai = "", prevKetAlerLai = "", prevReakAlerLai = "", prevSkorGz2 = "",
                                    prevGelang = "", prevKesimSkriningGZ = "", prevTotSkorGZ = "", prevDiberitauDr = "", prevDiberitauFar = "", prevDiberitauAGz = "";
                            if (rsPrev.getString("gizi_1").equals("Tidak") || rsPrev.getString("gizi_1").equals("Ya ada penurunan BB sebanyak :")) {
                                prevSkorGz1 = "0";
                            } else if (rsPrev.getString("gizi_1").equals("Tidak Yakin (ada tanda : baju menjadi longgar)")) {
                                prevSkorGz1 = "2";
                            }
                            
                            if (rsPrev.getString("gizi_1").equals("Ya ada penurunan BB sebanyak :")) {
                                prevLihatSkorGz1 = "";
                            } else {
                                prevLihatSkorGz1 = ": Skor (" + prevSkorGz1 + ")";
                            }
                            
                            if (rsPrev.getString("gizi_1ya").equals("-")) {
                                prevSkorYaGz1 = "0";
                            } else if (rsPrev.getString("gizi_1ya").equals("1 - 5 Kg")) {
                                prevSkorYaGz1 = "1";
                            } else if (rsPrev.getString("gizi_1ya").equals("6 - 10 Kg")) {
                                prevSkorYaGz1 = "2";
                            } else if (rsPrev.getString("gizi_1ya").equals("11 - 15 Kg")) {
                                prevSkorYaGz1 = "3";
                            } else if (rsPrev.getString("gizi_1ya").equals("> 15 Kg")) {
                                prevSkorYaGz1 = "4";
                            } else if (rsPrev.getString("gizi_1ya").equals("Tidak tahu berapa Kg penurunanya")) {
                                prevSkorYaGz1 = "2";
                            }
                            
                            if (rsPrev.getString("gizi_1ya").equals("-")) {
                                prevLihaSkorYaGz1 = "-";
                            } else {
                                prevLihaSkorYaGz1 = ": Skor (" + prevSkorYaGz1 + ")";
                            }
                            
                            if (rsPrev.getString("gizi_2").equals("Tidak")) {
                                prevSkorGz2 = "0";
                            } else {
                                prevSkorGz2 = "1";
                            }
                            
                            if (rsPrev.getString("cek_alergi_obat").equals("ya")) {
                                if (rsPrev.getString("ket_alergi_obat").equals("")) {
                                    prevKetAlerObat = "";
                                } else {
                                    prevKetAlerObat = " : " + rsPrev.getString("ket_alergi_obat");
                                }

                                if (rsPrev.getString("ket_reaksi_alergi_obat").equals("")) {
                                    prevReakAlerObat = "";
                                } else {
                                    prevReakAlerObat = ", Reaksi : " + rsPrev.getString("ket_reaksi_alergi_obat");
                                }
                                prevAlergiObat = "Alergi Obat" + prevKetAlerObat + prevReakAlerObat;
                            } else {
                                prevAlergiObat = "Alergi Obat ................";
                            }
                            
                            if (rsPrev.getString("cek_alergi_makanan").equals("ya")) {
                                if (rsPrev.getString("ket_alergi_makanan").equals("")) {
                                    prevKetAlerMak = "";
                                } else {
                                    prevKetAlerMak = " : " + rsPrev.getString("ket_alergi_makanan");
                                }

                                if (rsPrev.getString("ket_reaksi_alergi_makanan").equals("")) {
                                    prevReakAlerMak = "";
                                } else {
                                    prevReakAlerMak = ", Reaksi : " + rsPrev.getString("ket_reaksi_alergi_makanan");
                                }
                                prevAlergiMak = "Alergi Makanan" + prevKetAlerMak + prevReakAlerMak;
                            } else {
                                prevAlergiMak = "Alergi Makanan ................";
                            }
                            
                            if (rsPrev.getString("cek_alergi_lainya").equals("ya")) {
                                if (rsPrev.getString("ket_alergi_lainya").equals("")) {
                                    prevKetAlerLai = "";
                                } else {
                                    prevKetAlerLai = " : " + rsPrev.getString("ket_alergi_lainya");
                                }

                                if (rsPrev.getString("ket_reaksi_alergi_lainya").equals("")) {
                                    prevReakAlerLai = "";
                                } else {
                                    prevReakAlerLai = ", Reaksi : " + rsPrev.getString("ket_reaksi_alergi_lainya");
                                }
                                prevAlergiLai = "Alergi Lainnya" + prevKetAlerLai + prevReakAlerLai;
                            } else {
                                prevAlergiLai = "Alergi Lainnya ................";
                            }
                            
                            if (rsPrev.getString("cek_gelang_tanda").equals("ya")) {
                                prevGelang = "Gelang Tanda Alergi Dipasang (Warna Merah)";
                            } else {
                                prevGelang = "-";
                            }
                            
                            if (rsPrev.getString("cek_alergi_diberitahukan_dokter").equals("ya")) {
                                prevDiberitauDr = "Dokter, ";
                            } else {
                                prevDiberitauDr = "";
                            }
                            
                            if (rsPrev.getString("cek_alergi_diberitahukan_farmasis").equals("ya")) {
                                prevDiberitauFar = "Farmasis / Apoteker, ";
                            } else {
                                prevDiberitauFar = "";
                            }
                            
                            if (rsPrev.getString("cek_alergi_diberitahukan_ahliGizi").equals("ya")) {
                                prevDiberitauAGz = "Ahli Gizi";
                            } else {
                                prevDiberitauAGz = "";
                            }
                                
                            int A, B, C, Total;
                            A = Integer.parseInt(prevSkorGz1);
                            B = Integer.parseInt(prevSkorYaGz1);
                            C = Integer.parseInt(prevSkorGz2);

                            Total = 0;
                            Total = A + B + C;
                            prevTotSkorGZ = Valid.SetAngka2(Total);

                            if (Total == 0 || Total == 1) {
                                prevKesimSkriningGZ = "Pasien tidak beresiko malnutrisi";
                            } else if (Total >= 2) {
                                prevKesimSkriningGZ = "Skor >= 2, pasien beresiko malnutrisi, konsul ke Ahli Gizi";
                            }

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='3' align='left'>" + rsPrev.getString("gizi_1") + "</td>"
                                    + "<td valign='top' colspan='1' align='left'>" + prevLihatSkorGz1 + "</td>"
                                    + "<td valign='top' colspan='4' align='left'>" + prevAlergiObat + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='3' align='left'>" + rsPrev.getString("gizi_1ya").replaceAll("<", "&lt;").replaceAll(">", "&gt;") + "</td>"
                                    + "<td valign='top' colspan='1' align='left'>" + prevLihaSkorYaGz1 + "</td>"
                                    + "<td valign='top' colspan='4' align='left'>" + prevAlergiMak + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='4' align='left'>2. Apakah asupan makan pasien berkurang karena penurunan nafsu makan / kesulitan menerima makanan ?</td>"
                                    + "<td valign='top' colspan='4' align='left'>" + prevAlergiLai + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='3' align='left'>" + rsPrev.getString("gizi_2") + "</td>"
                                    + "<td valign='top' colspan='1' align='left'>: Skor (" + prevSkorGz2 + ")</td>"
                                    + "<td valign='top' colspan='4' align='left'>" + prevGelang + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='3' align='right'>Total Skor</td>"
                                    + "<td valign='top' colspan='1' align='left'>: " + prevTotSkorGZ + "</td>"
                                    + "<td valign='top' colspan='4' align='left'>Alergi Diberitahukan Kepada : " + prevDiberitauDr + prevDiberitauFar + prevDiberitauAGz + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' align='left'>Kesimpulan Skrining Gizi : " + prevKesimSkriningGZ.replaceAll("<", "&lt;").replaceAll(">", "&gt;") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>ASSESMEN RESIKO JATUH MORSE</span></td>"
                                    + "</tr>");
                            
                            String resJatuh = "", kondisi = "", resAlatBantu = "", terapi = "", gaya = "", sttsMental = "";
                            if (rsPrev.getString("riw_jatuh_resiko_jatuh").equals("-") || rsPrev.getString("riw_jatuh_resiko_jatuh").equals("Tidak ada atau >= 3 bulan")) {
                                resJatuh = "0";
                            } else {
                                resJatuh = "25";
                            }

                            if (rsPrev.getString("kondisi_kesehatan").equals("-") || rsPrev.getString("kondisi_kesehatan").equals("< diagnosa penyakit")) {
                                kondisi = "0";
                            } else {
                                kondisi = "15";
                            }

                            if (rsPrev.getString("alat_bantu_resiko_jatuh").equals("-") || rsPrev.getString("alat_bantu_resiko_jatuh").equals("Tidak ada/kursi roda/tirah baring")) {
                                resAlatBantu = "0";
                            } else if (rsPrev.getString("alat_bantu_resiko_jatuh").equals("Berpegangan pada perabot")) {
                                resAlatBantu = "30";
                            } else if (rsPrev.getString("alat_bantu_resiko_jatuh").equals("Tongkat/alat penopang")) {
                                resAlatBantu = "15";
                            }

                            if (rsPrev.getString("terapi_IV").equals("-") || rsPrev.getString("terapi_IV").equals("Tidak")) {
                                terapi = "0";
                            } else {
                                terapi = "20";
                            }

                            if (rsPrev.getString("gaya_berjalan").equals("-") || rsPrev.getString("gaya_berjalan").equals("Normal/tirah baring/immobilisasi")) {
                                gaya = "0";
                            } else if (rsPrev.getString("gaya_berjalan").equals("Kerusakan/terganggu")) {
                                gaya = "20";
                            } else if (rsPrev.getString("gaya_berjalan").equals("Lemah")) {
                                gaya = "10";
                            }

                            if (rsPrev.getString("status_mental").equals("-") || rsPrev.getString("status_mental").equals("Sadar kemampuan diri sendiri")) {
                                sttsMental = "0";
                            } else {
                                sttsMental = "15";
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>Resiko Jatuh</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("riw_jatuh_resiko_jatuh").replaceAll("<", "&lt;").replaceAll(">", "&gt;") + " (Skor : " + resJatuh + ")</td>"
                                    + "<td valign='top' colspan='1' align='left'>Terapi IV</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("terapi_IV") + " (Skor : " + terapi + ")</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>Kondisi Kesehatan</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("kondisi_kesehatan").replaceAll("<", "&lt;").replaceAll(">", "&gt;") + " (Skor : " + kondisi + ")</td>"
                                    + "<td valign='top' colspan='1' align='left'>Gaya Berjalan</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("gaya_berjalan") + " (Skor : " + gaya + ")</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>Alat Bantu</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("alat_bantu_resiko_jatuh") + " (Skor : " + resAlatBantu + ")</td>"
                                    + "<td valign='top' colspan='1' align='left'>Status Mental</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("status_mental") + " (Skor : " + sttsMental + ")</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>Jumlah Skor</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("jumlah_skor") + "</td>"
                                    + "<td valign='top' colspan='2' align='left'>Kesimpulan Assesmen Resiko Jatuh Morse</td>"
                                    + "<td valign='top' colspan='2' align='left'>: " + rsPrev.getString("kesimpulan_resiko_jatuh").replaceAll(">", "&gt;") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>FUNGSIONAL</span></td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>1. Alat Bantu</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("alat_bantu") + "</td>"
                                    + "<td valign='top' colspan='1' align='left'>4. ADL</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("adl") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>2. Prothesis</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("prothesis") + "</td>"
                                    + "<td valign='top' colspan='2' align='left'>5. Riwayat Jatuh Dalam 3 Bulan Terakhir ?</td>"
                                    + "<td valign='top' colspan='2' align='left'>: " + rsPrev.getString("riwayat_jatuh") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>3. Cacat Tubuh</td>"
                                    + "<td valign='top' colspan='7' align='left'>: " + rsPrev.getString("cacat_tubuh") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'><b>Nama Bidan</b></td>"
                                    + "<td valign='top' colspan='7' align='left'>: " + Sequel.cariIsi("select nama from pegawai where nik='" + rsPrev.getString("nip_bidan") + "'") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>KEBUTUHAN KOMUNIKASI DAN EDUKASI</span></td>"
                                    + "</tr>");

                            String prevYaHambatan = "", prevHamPend = "", prevHamPeng = "", prevHamKog = "", prevHamFis = "", prevHamBud = "", prevHamEmo = "", prevHamBah = "",
                                    prevHamLain = "", prevHamLainKet = "", prevSebutkan = "";
                            if (rsPrev.getString("cek_ya").equals("ya")) {
                                if (rsPrev.getString("cek_pendengaran").equals("ya")) {
                                    prevHamPend = "Pendengaran, ";
                                } else {
                                    prevHamPend = "";
                                }
                                
                                if (rsPrev.getString("cek_penglihatan").equals("ya")) {
                                    prevHamPeng = "Penglihatan, ";
                                } else {
                                    prevHamPeng = "";
                                }
                                
                                if (rsPrev.getString("cek_kognitif").equals("ya")) {
                                    prevHamKog = "Kognitif, ";
                                } else {
                                    prevHamKog = "";
                                }
                                
                                if (rsPrev.getString("cek_fisik").equals("ya")) {
                                    prevHamFis = "Fisik, ";
                                } else {
                                    prevHamFis = "";
                                }
                                
                                if (rsPrev.getString("cek_budaya").equals("ya")) {
                                    prevHamBud = "Budaya, ";
                                } else {
                                    prevHamBud = "";
                                }
                                
                                if (rsPrev.getString("cek_emosi").equals("ya")) {
                                    prevHamEmo = "Emosi, ";
                                } else {
                                    prevHamEmo = "";
                                }
                                
                                if (rsPrev.getString("cek_bahasa").equals("ya")) {
                                    prevHamBah = "Bahasa, ";
                                } else {
                                    prevHamBah = "";
                                }
                                
                                if (rsPrev.getString("cek_lain_hambatan").equals("ya")) {
                                    if (rsPrev.getString("ket_lain_hambatan").equals("")) {
                                        prevHamLainKet = "";
                                    } else {
                                        prevHamLainKet = " (" + rsPrev.getString("ket_lain_hambatan") + ")";
                                    }
                                    prevHamLain = "Lainnya" + prevHamLainKet;
                                } else {
                                    prevHamLain = "";
                                }
                                prevYaHambatan = "Ya, Jika Ya : " + prevHamPend + prevHamPeng + prevHamKog + prevHamFis + prevHamBud + prevHamEmo + prevHamBah + prevHamLain;
                            } else {
                                prevYaHambatan = "-";
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='2' align='left'>Terdapat Hambatan Dalam Pembelajaran</td>"
                                    + "<td valign='top' colspan='6' align='left'>: " + prevYaHambatan + "</td>"
                                    + "</tr>");
                            
                            if (rsPrev.getString("sebutkan").equals("")) {
                                prevSebutkan = "";
                            } else {
                                prevSebutkan = ", Sebutkan : " + rsPrev.getString("sebutkan");
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='2' align='left'>Dibutuhkan Penerjemah</td>"
                                    + "<td valign='top' colspan='6' align='left'>: " + rsPrev.getString("dibutuhkan_penerjemah") + prevSebutkan + ", Bahasa Isyarat : " + rsPrev.getString("bahasa_isyarat") + "</td>"
                                    + "</tr>");

                            String prevEduDiag = "", prevEduTin = "", prevEduTinKet = "", prevEduObat = "", prevEduReh = "", prevEduDiet = "", prevEduMan = "", prevEduLain = "", prevEduLainKet = "";
                            if (rsPrev.getString("cek_diagnosa").equals("ya")) {
                                prevEduDiag = "Diagnosa dan Manajemen Penyakit, ";
                            } else {
                                prevEduDiag = "";
                            }
                            
                            if (rsPrev.getString("cek_tindakan_keperawatan").equals("ya")) {
                                if (rsPrev.getString("ket_tindakan_keperawatan").equals("")) {
                                    prevEduTinKet = ", ";
                                } else {
                                    prevEduTinKet = " (" + rsPrev.getString("ket_tindakan_keperawatan") + "), ";
                                }
                                prevEduTin = "Tindakan Keperawatan" + prevEduTinKet;
                            } else {
                                prevEduTin = "";
                            }
                            
                            if (rsPrev.getString("cek_lain_kebutuhan_edukasi").equals("ya")) {
                                if (rsPrev.getString("ket_lain_kebutuhan_edukasi").equals("")) {
                                    prevEduLainKet = "";
                                } else {
                                    prevEduLainKet = " (" + rsPrev.getString("ket_lain_kebutuhan_edukasi") + ")";
                                }
                                prevEduLain = "Lain-lain : Sebutkan" + prevEduLainKet;
                            } else {
                                prevEduLain = "";
                            }
                            
                            if (rsPrev.getString("cek_obat_obatan").equals("ya")) {
                                prevEduObat = "Obat-obatan / Terapi, ";
                            } else {
                                prevEduObat = "";
                            }
                            
                            if (rsPrev.getString("cek_rehabilitasi").equals("ya")) {
                                prevEduReh = "Rehabilitasi, ";
                            } else {
                                prevEduReh = "";
                            }
                            
                            if (rsPrev.getString("cek_diet").equals("ya")) {
                                prevEduDiet = "Diet dan Nutrisi, ";
                            } else {
                                prevEduDiet = "";
                            }
                            
                            if (rsPrev.getString("cek_manajemen_nyeri").equals("ya")) {
                                prevEduMan = "Manajemen Nyeri, ";
                            } else {
                                prevEduMan = "";
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='2' align='left'>Kebutuhan Edukasi</td>"
                                    + "<td valign='top' colspan='6' align='left'>: " + prevEduDiag + prevEduObat + prevEduDiet + prevEduTin + prevEduReh + prevEduMan + prevEduLain + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='left'><span style='font-weight:bold'>EDUKASI PASIEN</span></td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' align='left'>Edukasi Awal Disampaikan Tentang Diagnosis, Rencana, Dan Tujuan Terapi Kepada :</td>"
                                    + "</tr>");
                            
                            String prevEpasPas = "", prevEpasKlgPas = "", prevEpasKlgPasKet = "", prevEpasTidak = "", prevEpasTidakKet = "";
                            if (rsPrev.getString("cek_pasien").equals("ya")) {
                                prevEpasPas = "Pasien, ";
                            } else {
                                prevEpasPas = "";
                            }
                            
                            if (rsPrev.getString("cek_keluarga_pasien").equals("ya")) {
                                if (rsPrev.getString("nama_keluarga_pasien").equals("")) {
                                    prevEpasKlgPasKet = "-";
                                } else {
                                    prevEpasKlgPasKet = rsPrev.getString("nama_keluarga_pasien");
                                }
                                prevEpasKlgPas = "Keluarga Pasien, Nama : " + prevEpasKlgPasKet + ", ";
                            } else {
                                prevEpasKlgPas = "";
                            }
                            
                            if (rsPrev.getString("cek_tidak_dapat").equals("ya")) {
                                if (rsPrev.getString("ket_tidak_dapat").equals("")) {
                                    prevEpasTidakKet = "-";
                                } else {
                                    prevEpasTidakKet = rsPrev.getString("ket_tidak_dapat");
                                }
                                prevEpasTidak = "Tidak Dapat Memberikan Edukasi Kepada Pasien Atau Keluarga, Karena : " + prevEpasTidakKet;
                            } else {
                                prevEpasTidak = "";
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' align='left'>" + prevEpasPas + prevEpasKlgPas + prevEpasTidak + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>Tanggal & Jam</td>"
                                    + "<td valign='top' colspan='2' align='left'>: " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_edukasi")) + ", Jam : " + rsPrev.getString("jam_edukasi").substring(0, 5) + " Wita</td>"
                                    + "<td valign='top' colspan='1' align='left'>Nama Dokter</td>"
                                    + "<td valign='top' colspan='4' align='left'>: " + Sequel.cariIsi("select nama from pegawai where nik='" + rsPrev.getString("nip_dokter") + "'") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>LEMBAR DISCHARGE PLANNING</span></td>"
                                    + "</tr>");
                            
                            String prevIden1 = "", prevIden2 = "", prevIden3 = "", prevIden4 = "", prevIden5 = "", prevIden6 = "", prevIden7 = "", prevIden8 = "", prevIden9 = "", prevIden10 = "";
                            if (rsPrev.getString("cek_identifikasi1").equals("ya")) {
                                prevIden1 = "Pasien dengan keterbatasan kognitif, ketergantungan ADL tinggi,<br>";
                            } else {
                                prevIden1 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi2").equals("ya")) {
                                prevIden2 = "Wanita usia rentan (Ibu hamil, Ibu menyusui, Lansia),<br>";
                            } else {
                                prevIden2 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi3").equals("ya")) {
                                prevIden3 = "Pasien dengan resiko tinggi (Infeksi kejang, Penurunan kesadaran),<br>";
                            } else {
                                prevIden3 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi4").equals("ya")) {
                                prevIden4 = "Potensi komplain tinggi,<br>";
                            } else {
                                prevIden4 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi5").equals("ya")) {
                                prevIden5 = "Pasien dengan penyakit kronis, katastropik (Penyakit Degenerative) terminal,<br>";
                            } else {
                                prevIden5 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi6").equals("ya")) {
                                prevIden6 = "Sering masuk IGD, readmisi RS,<br>";
                            } else {
                                prevIden6 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi7").equals("ya")) {
                                prevIden7 = "Perkiraan asuhan dengan biaya tinggi,<br>";
                            } else {
                                prevIden7 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi8").equals("ya")) {
                                prevIden8 = "Pasien tanpa keluarga / terlantar, tinggal sendiri,<br>";
                            } else {
                                prevIden8 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi9").equals("ya")) {
                                prevIden9 = "Kasus yang melebihi rata-rata lama dirawat,<br>";
                            } else {
                                prevIden9 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi10").equals("ya")) {
                                prevIden10 = "Kasus yang membutuhkan kontinuitas pelayanan, rencana pemulangan penting / beresiko";
                            } else {
                                prevIden10 = "";
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>Identifikasi, Seleksi / Skrining Pasien</td>"
                                    + "<td valign='top' colspan='7' align='left'>" + prevIden1 + prevIden2 + prevIden3 + prevIden4 + prevIden5 + prevIden6 + prevIden7 + prevIden8 + prevIden9 + prevIden10 + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>Memerlukan</td>"
                                    + "<td valign='top' colspan='7' align='left'>: " + rsPrev.getString("memerlukan") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>Manajer Pelayanan Pasien</td>"
                                    + "<td valign='top' colspan='1' align='left'>: " + rsPrev.getString("mpp") + "</td>"
                                    + "<td valign='top' colspan='1' align='left'>Discharge Planning</td>"
                                    + "<td valign='top' colspan='5' align='left'>: " + rsPrev.getString("dp") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='5'></td>"
                                    + "<td valign='top' colspan='3' align='center'>Martapura, " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_dp")) + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='5'></td>"
                                    + "<td valign='top' colspan='1' align='left'>Nama Pasien / Keluarga Pasien</td>"
                                    + "<td valign='top' colspan='2' align='left'>: " + rsPrev.getString("nm_keluarga_pasien") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='5'></td>"
                                    + "<td valign='top' colspan='1' align='left'>Nama Bidan</td>"
                                    + "<td valign='top' colspan='2' align='left'>: " + Sequel.cariIsi("select nama from pegawai where nik='" + rsPrev.getString("nip_bidan_dp") + "'") + "</td>"
                                    + "</tr>");
                        }
                    }
                    
                    htmlContent.append(
                            "</table>"
                            + "</td>"
                            + "</tr>");
                }
                
                LoadHTML1.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' colspan='8' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsPrev != null) {
                    rsPrev.close();
                }
            }            
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
}
