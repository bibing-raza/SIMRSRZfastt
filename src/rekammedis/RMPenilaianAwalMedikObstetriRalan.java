/*
 * Kontribusi dari Bibing, RSUD Ratu Zalecha
 */

package rekammedis;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCatatanResep;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
import javax.swing.text.Document;
import kepegawaian.DlgCariPegawai;
import kepegawaian.DlgCariPetugas;
import laporan.DlgPenyakit;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author perpustakaan
 */
public final class RMPenilaianAwalMedikObstetriRalan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, ps4;
    private ResultSet rs, rs1, rs2, rs3, rs4;
    private int i = 0, x = 0, pilihan = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgPenyakit icd10 = new DlgPenyakit(null, false);
    private String cervical = "", rjp = "", defribrilasi = "", intubasi = "", vtp = "", dekompresi = "", balut = "", kateter = "",
            ngt = "", infus = "", obat = "", tidakada = "", paten = "", obsP = "", deformitas = "", contusio = "", penetrasi = "", tenderness = "",
            swelling = "", ekskoriasi = "", abrasi = "", burn = "", laserasi = "", tdk_tmpk_jls = "", obsT = "", traumaJlnNfs = "", resikoAspirasi = "",
            bendaAsing = "", meninggal = "", ganggnafas = "", itemDipilih = "", mengeluh_prt = "",
            hipertensi1 = "", dm1 = "", jantung1 = "", asma1 = "", lainya1 = "", hipertensi2 = "", dm2 = "", jantung2 = "", asma2 = "", lainya2 = "",
            pil = "", suntik1 = "", suntik3 = "", implan = "", dismen = "", spoting = "", menor = "", metro = "", nyeri = "", bandle = "",
            his = "", teratur = "", tdk_teratur = "", terus = "", kuat = "", sedang = "", lemah = "", bersih = "", odema = "", ruptur = "",
            candi = "", jamkeluar = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPenilaianAwalMedikObstetriRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.RM", "Nama Pasien", "Tgl. Lahir", "Tgl. Asesmen", "tanggal", "cervival", "rjp", "defribilasi", "intubasi", "vtp", "dekompresi",
            "balut", "kateter", "ngt", "infus", "obat", "ket_obat", "tidak_ada", "paten", "gangguan_jalan_nafas", "obstruksi_partial", "data_obstruksi_partial",
            "obstruksi_total", "trauma_jalan_nafas", "trauma", "resiko_aspirasi", "aspirasi", "benda_asing", "ket_benda_asing", "kesimpulan_jalan_nafas", "pernafasan",
            "data_pernafasan", "gerakan_dada", "tipe_pernafasan", "kesimpulan_pernafasan", "nadi_1", "kulit_mukosa", "akral_1", "crt", "kesimpulan_sirkulasi", "gcs_e",
            "gcs_v", "gcs_m", "pupil", "diameter_kanan", "diameter_kiri", "reflek_cahaya", "meningeal_sign", "lateralisasi", "deformitas", "contusio", "penetrasi",
            "tenderness", "swelling", "ekskoriasi", "abrasi", "burn", "laserasi", "tidak_tampak_jelas", "alasan_masuk", "cara_datang", "ket_rujukan_bidan", "ket_pkm",
            "ket_spog", "ket_rs_lain", "dengan_gr", "dengan_pr", "dengan_a", "hamil", "ket_dengan", "cek_mengeluh_perut", "tgl_mengeluh_perut", "keluar_lendir",
            "tgl_lendir_ya", "keluar_air", "keluar_air_ya", "tgl_air_ya", "periksa_bidan", "hasil_periksa_bidan", "mengeluh_pusing", "tgl_pusing", "nyeri_ulu_hati",
            "tgl_nyeri", "pandangan_kabur", "tgl_pandangan", "mual_muntah", "tgl_mual_muntah", "batuk_pilek", "tgl_batuk_pilek", "demam", "tgl_demam", "riw_jalan_jauh",
            "ket_riw_jalan_jauh", "os_anc_bidan", "dengan_spog1", "jml_spog1", "dengan_spog2", "jml_spog2", "hipertensi1", "diabetes1", "jantung1", "asma1", "lainnya1",
            "ket_lainnya1", "hipertensi2", "diabetes2", "jantung2", "asma2", "lainnya2", "ket_lainnya2", "riw_ginekologi", "pil", "lama_pil", "satuan_lama", "suntik_1_bln",
            "lama_1_bln", "satuan_suntik1", "suntik_3_bln", "lama_3_bln", "satuan_suntik3", "implan", "lama_implan", "satuan_implan", "riwayat_kb_lain", "hpht", "hpl",
            "uk", "uk_usg", "tgl_usg", "bb_blm_hamil", "bb_stlh_hamil", "tbi", "bmi", "lila", "dismenorhoe", "spoting", "menorrhagia", "metrohagia", "keluhan_lain",
            "leopold1", "leopold2", "leopold3", "leopold4", "nyeri_tekan", "bandle_ring", "tfu", "taksiran_bb_janin", "his_kontraksi", "ket_his_kontraksi", "teratur",
            "tdk_teratur", "trs_menerus", "durasi", "kuat", "sedang", "lemah", "auskultasi", "bersih", "oedema", "ruptur", "candiloma", "pemeriksaan_genitalia_lain",
            "inspeksi", "konsistensi", "periksa_dlm_obstetri", "inspekulum", "diagnosis_sementara", "icd_10", "rencana_instruksi", "planing", "telah_diberikan_informasi_edukasi",
            "rencana_asuhan_diharapkan", "nip_pemberi_edukasi", "Nama Penerima Edukasi", "nip_dokter", "tgl_keluar_ponek", "opname_diruang", "indikasi_msk", "dipulangkan_kontrol_ke",
            "dirujuk_ke", "alasan_dirujuk", "meninggal", "jam_meninggal", "penyebab", "k_u", "td", "hr", "rr", "temp", "spo2", "gcs_pulang", "nip_bidan", "nip_dpjp",
            "Nama Petugas Edukasi", "Nama Dokter", "Nama Bidan", "Nama DPJP", "nadi_2", "akral_2", "cekjamkeluar", "tglkeluar", "jamkeluar"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 203; i++) {
            TableColumn column = tbAsesmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(160);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(120);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
                column.setPreferredWidth(250);
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
                column.setPreferredWidth(250);
            } else if (i == 195) {
                column.setPreferredWidth(250);
            } else if (i == 196) {
                column.setPreferredWidth(250);
            } else if (i == 197) {
                column.setPreferredWidth(250);
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
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"No. RM", "Nama Pasien", "Data Template"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTemplate.setModel(tabMode1);
        tbTemplate.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTemplate.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbTemplate.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            }
        }
        tbTemplate.setDefaultRenderer(Object.class, new WarnaTable());  
        
        TgcsE.setDocument(new batasInput((int) 7).getKata(TgcsE));
        TgcsV.setDocument(new batasInput((int) 7).getKata(TgcsV));
        TgcsM.setDocument(new batasInput((int) 7).getKata(TgcsM));
        TDiam_kanan.setDocument(new batasInput((int) 4).getKata(TDiam_kanan));
        TDiam_kiri.setDocument(new batasInput((int) 4).getKata(TDiam_kiri));
        TReflek.setDocument(new batasInput((int) 7).getKata(TReflek));
        TMeningeal.setDocument(new batasInput((int) 120).getKata(TMeningeal));
        Tgr.setDocument(new batasInput((int) 50).getKata(Tgr));
        Tpr.setDocument(new batasInput((int) 50).getKata(Tpr));
        Ta.setDocument(new batasInput((int) 50).getKata(Ta));
        Thamil.setDocument(new batasInput((byte) 2).getOnlyAngka(Thamil));
        Tspog1.setDocument(new batasInput((int) 200).getKata(Tspog1));
        Tspog2.setDocument(new batasInput((int) 200).getKata(Tspog2));
        TJspog1.setDocument(new batasInput((byte) 3).getOnlyAngka(TJspog1));
        TJspog2.setDocument(new batasInput((byte) 3).getOnlyAngka(TJspog2));
        Tpil.setDocument(new batasInput((byte) 3).getOnlyAngka(Tpil));
        Tsuntik1.setDocument(new batasInput((byte) 3).getOnlyAngka(Tsuntik1));
        Tsuntik3.setDocument(new batasInput((byte) 3).getOnlyAngka(Tsuntik3));
        Timplan.setDocument(new batasInput((byte) 3).getOnlyAngka(Timplan));        
        Thpht.setDocument(new batasInput((int) 100).getKata(Thpht));
        Thpl.setDocument(new batasInput((int) 100).getKata(Thpl));
        Tuk.setDocument(new batasInput((int) 100).getKata(Tuk));
        Tukusg.setDocument(new batasInput((int) 100).getKata(Tukusg));
        TbbBlmHamil.setDocument(new batasInput((byte) 3).getOnlyAngka(TbbBlmHamil));
        TbbStlhHamil.setDocument(new batasInput((byte) 3).getOnlyAngka(TbbStlhHamil));
        Ttbi.setDocument(new batasInput((byte) 3).getOnlyAngka(Ttbi));
        Tbmi.setDocument(new batasInput((int) 100).getKata(Tbmi));
        Tlila.setDocument(new batasInput((int) 100).getKata(Tlila));
        Ttfu.setDocument(new batasInput((byte) 3).getOnlyAngka(Ttfu));
        TbbJanin.setDocument(new batasInput((byte) 5).getOnlyAngka(TbbJanin));
        ThisKontraksi.setDocument(new batasInput((int) 7).getKata(ThisKontraksi));
        Tdurasi.setDocument(new batasInput((int) 7).getKata(Tdurasi));
        Tauskultasi.setDocument(new batasInput((int) 7).getKata(Tauskultasi));        
        Ticd.setDocument(new batasInput((int) 10).getKata(Ticd));
        nmpenerima.setDocument(new batasInput((int) 160).getKata(nmpenerima));
        Tindikasi.setDocument(new batasInput((int) 200).getKata(Tindikasi));
        Tdipulangkan.setDocument(new batasInput((int) 200).getKata(Tdipulangkan));
        Tdirujuk.setDocument(new batasInput((int) 200).getKata(Tdirujuk));
        Tpenyebab.setDocument(new batasInput((int) 200).getKata(Tpenyebab));
        Ttd.setDocument(new batasInput((int) 7).getKata(Ttd));
        Thr.setDocument(new batasInput((int) 7).getKata(Thr));
        Trr.setDocument(new batasInput((int) 7).getKata(Trr));
        Ttemp.setDocument(new batasInput((int) 7).getKata(Ttemp));
        Tspo2.setDocument(new batasInput((int) 7).getKata(Tspo2));
        Tgcs.setDocument(new batasInput((int) 7).getKata(Tgcs));
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (pilihan == 1) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        KdBidan.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        NmBidan.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    }
                } else if (pilihan == 2) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        kdpetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        nmpetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    }
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMPenilaianAwalMedikObstetriRalan")) {
                    if (pilihan == 1) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            kddpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            nmdpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        }
                        BtnDpjp.requestFocus();
                    } else if (pilihan == 2) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            kddokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            nmdokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        }
                        BtnDokter.requestFocus();
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
        
        icd10.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (icd10.getTable().getSelectedRow() != -1) {
                    x = JOptionPane.showConfirmDialog(rootPane, "Apakah deskripsi ICD 10 akan ditambahkan juga utk. diagnosa medis sementara..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        Ticd.setText(icd10.getTable().getValueAt(icd10.getTable().getSelectedRow(), 1).toString());
                        if (TDiagSementara.getText().equals("")) {
                            TDiagSementara.setText(icd10.getTable().getValueAt(icd10.getTable().getSelectedRow(), 3).toString());
                        } else {
                            TDiagSementara.setText(TDiagSementara.getText() + " (" + icd10.getTable().getValueAt(icd10.getTable().getSelectedRow(), 3).toString() + ")");
                        }
                        btnICD.requestFocus();
                    } else {
                        Ticd.setText(icd10.getTable().getValueAt(icd10.getTable().getSelectedRow(), 1).toString());
                        btnICD.requestFocus();
                    }
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
        
        icd10.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("RMPenilaianAwalMedikObstetriRalan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        icd10.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
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

        WindowTemplate = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        jLabel9 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCopas = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        Scroll1 = new widget.ScrollPane();
        tbTemplate = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        Ttemplate = new widget.TextArea();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnResep = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jSeparator1 = new javax.swing.JSeparator();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        label14 = new widget.Label();
        KdBidan = new widget.TextBox();
        NmBidan = new widget.TextBox();
        BtnBidan = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel10 = new widget.Label();
        label11 = new widget.Label();
        TglAsesmen = new widget.Tanggal();
        jLabel98 = new widget.Label();
        jLabel12 = new widget.Label();
        label15 = new widget.Label();
        kddpjp = new widget.TextBox();
        nmdpjp = new widget.TextBox();
        BtnDpjp = new widget.Button();
        ChkCervical = new widget.CekBox();
        ChkRJP = new widget.CekBox();
        ChkDefribilasi = new widget.CekBox();
        ChkIntubasi = new widget.CekBox();
        ChkVTP = new widget.CekBox();
        ChkDekompresi = new widget.CekBox();
        ChkBalut = new widget.CekBox();
        ChkKateter = new widget.CekBox();
        ChkNGT = new widget.CekBox();
        ChkInfus = new widget.CekBox();
        ChkObat = new widget.CekBox();
        TObat = new widget.TextBox();
        ChkTidak = new widget.CekBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel99 = new widget.Label();
        jLabel100 = new widget.Label();
        ChkPaten = new widget.CekBox();
        ChkObstruksiP = new widget.CekBox();
        ChkObstruksiT = new widget.CekBox();
        ChkTrauma = new widget.CekBox();
        cmbTrauma = new widget.ComboBox();
        ChkResiko = new widget.CekBox();
        cmbResiko = new widget.ComboBox();
        ChkBendaAsing = new widget.CekBox();
        TBendaAsing = new widget.TextBox();
        jLabel101 = new widget.Label();
        cmbKesJalanNafas = new widget.ComboBox();
        jLabel102 = new widget.Label();
        cmbPernapasan = new widget.ComboBox();
        jLabel103 = new widget.Label();
        cmbGerakanDada = new widget.ComboBox();
        jLabel104 = new widget.Label();
        cmbTipePernapasan = new widget.ComboBox();
        jLabel105 = new widget.Label();
        cmbKesPernapasan = new widget.ComboBox();
        jLabel106 = new widget.Label();
        jLabel107 = new widget.Label();
        cmbNadi1 = new widget.ComboBox();
        jLabel108 = new widget.Label();
        cmbKulit = new widget.ComboBox();
        jLabel109 = new widget.Label();
        cmbAkral1 = new widget.ComboBox();
        jLabel110 = new widget.Label();
        cmbCRT = new widget.ComboBox();
        jLabel111 = new widget.Label();
        cmbKesSirkulasi = new widget.ComboBox();
        jLabel118 = new widget.Label();
        jLabel119 = new widget.Label();
        jLabel120 = new widget.Label();
        cmbPupil = new widget.ComboBox();
        jLabel121 = new widget.Label();
        TDiam_kanan = new widget.TextBox();
        jLabel122 = new widget.Label();
        TReflek = new widget.TextBox();
        jLabel123 = new widget.Label();
        TMeningeal = new widget.TextBox();
        jLabel124 = new widget.Label();
        cmbLater = new widget.ComboBox();
        jLabel125 = new widget.Label();
        jLabel164 = new widget.Label();
        scrollPane9 = new widget.ScrollPane();
        TDiagSementara = new widget.TextArea();
        jLabel165 = new widget.Label();
        Ticd = new widget.TextBox();
        jLabel166 = new widget.Label();
        jLabel167 = new widget.Label();
        jLabel168 = new widget.Label();
        Tedukasi = new widget.TextBox();
        jLabel169 = new widget.Label();
        Trencana = new widget.TextBox();
        jLabel170 = new widget.Label();
        kdpetugas = new widget.TextBox();
        nmpetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel171 = new widget.Label();
        nmpenerima = new widget.TextBox();
        jLabel172 = new widget.Label();
        kddokter = new widget.TextBox();
        nmdokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel173 = new widget.Label();
        jLabel174 = new widget.Label();
        jLabel175 = new widget.Label();
        cmbRuangan = new widget.ComboBox();
        jLabel176 = new widget.Label();
        Tindikasi = new widget.TextBox();
        jLabel177 = new widget.Label();
        Tdipulangkan = new widget.TextBox();
        jLabel178 = new widget.Label();
        Tdirujuk = new widget.TextBox();
        jLabel179 = new widget.Label();
        scrollPane10 = new widget.ScrollPane();
        TAlasanDirujuk = new widget.TextArea();
        ChkMeninggal = new widget.CekBox();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel180 = new widget.Label();
        Tpenyebab = new widget.TextBox();
        jLabel181 = new widget.Label();
        Tku = new widget.TextBox();
        jLabel182 = new widget.Label();
        Ttd = new widget.TextBox();
        jLabel183 = new widget.Label();
        Thr = new widget.TextBox();
        jLabel184 = new widget.Label();
        jLabel185 = new widget.Label();
        Trr = new widget.TextBox();
        Ttemp = new widget.TextBox();
        jLabel186 = new widget.Label();
        jLabel187 = new widget.Label();
        Tspo2 = new widget.TextBox();
        Tgcs = new widget.TextBox();
        cmbObstruksi = new widget.ComboBox();
        ChkDeformitas = new widget.CekBox();
        ChkContusio = new widget.CekBox();
        ChkPenetrasi = new widget.CekBox();
        ChkTenderness = new widget.CekBox();
        ChkSwelling = new widget.CekBox();
        ChkEkskoriasi = new widget.CekBox();
        ChkAbrasi = new widget.CekBox();
        ChkBurn = new widget.CekBox();
        ChkLaserasi = new widget.CekBox();
        ChkTdkTampk = new widget.CekBox();
        cmbReguler = new widget.ComboBox();
        cmbRencana = new widget.ComboBox();
        jLabel188 = new widget.Label();
        btnICD = new widget.Button();
        jLabel126 = new widget.Label();
        TgcsE = new widget.TextBox();
        jLabel190 = new widget.Label();
        TgcsV = new widget.TextBox();
        jLabel191 = new widget.Label();
        TgcsM = new widget.TextBox();
        jLabel192 = new widget.Label();
        TDiam_kiri = new widget.TextBox();
        jLabel193 = new widget.Label();
        jLabel194 = new widget.Label();
        scrollPane12 = new widget.ScrollPane();
        Tplaning = new widget.TextArea();
        ChkGangNafas = new widget.CekBox();
        jLabel112 = new widget.Label();
        Talasan_msk = new widget.TextBox();
        jLabel113 = new widget.Label();
        cmbCara_datang = new widget.ComboBox();
        jLabel114 = new widget.Label();
        Trujukan_bidan = new widget.TextBox();
        jLabel115 = new widget.Label();
        Tpkm = new widget.TextBox();
        jLabel116 = new widget.Label();
        Tspog = new widget.TextBox();
        jLabel117 = new widget.Label();
        Trs_lain = new widget.TextBox();
        jLabel127 = new widget.Label();
        Tgr = new widget.TextBox();
        jLabel128 = new widget.Label();
        Tpr = new widget.TextBox();
        jLabel129 = new widget.Label();
        Ta = new widget.TextBox();
        jLabel130 = new widget.Label();
        Thamil = new widget.TextBox();
        jLabel131 = new widget.Label();
        Tket_dengan = new widget.TextBox();
        jLabel132 = new widget.Label();
        TglPerutMules = new widget.Tanggal();
        ChkPerutMules = new widget.CekBox();
        jLabel133 = new widget.Label();
        cmbKeluarLendir = new widget.ComboBox();
        jLabel134 = new widget.Label();
        TglLendirYa = new widget.Tanggal();
        jLabel135 = new widget.Label();
        cmbKeluarAir = new widget.ComboBox();
        cmbKeluarAirYa = new widget.ComboBox();
        jLabel136 = new widget.Label();
        TglAirYa = new widget.Tanggal();
        jLabel137 = new widget.Label();
        cmbPeriksaBidan = new widget.ComboBox();
        jLabel138 = new widget.Label();
        scrollPane11 = new widget.ScrollPane();
        THasilPeriksaBidan = new widget.TextArea();
        jLabel139 = new widget.Label();
        cmbMengeluhPusing = new widget.ComboBox();
        jLabel140 = new widget.Label();
        TglPusing = new widget.Tanggal();
        jLabel141 = new widget.Label();
        cmbNyeriUlu = new widget.ComboBox();
        jLabel142 = new widget.Label();
        TglNyeri = new widget.Tanggal();
        jLabel143 = new widget.Label();
        cmbPandangan = new widget.ComboBox();
        jLabel144 = new widget.Label();
        TglPandangan = new widget.Tanggal();
        jLabel145 = new widget.Label();
        cmbMual = new widget.ComboBox();
        jLabel146 = new widget.Label();
        TglMual = new widget.Tanggal();
        jLabel147 = new widget.Label();
        cmbBatuk = new widget.ComboBox();
        jLabel148 = new widget.Label();
        TglBatuk = new widget.Tanggal();
        jLabel149 = new widget.Label();
        cmbDemam = new widget.ComboBox();
        jLabel150 = new widget.Label();
        TglDemam = new widget.Tanggal();
        jLabel151 = new widget.Label();
        cmbRiwJlnJauh = new widget.ComboBox();
        TriwJlnJauh = new widget.TextBox();
        jLabel152 = new widget.Label();
        cmbOs = new widget.ComboBox();
        jLabel153 = new widget.Label();
        Tspog1 = new widget.TextBox();
        jLabel154 = new widget.Label();
        TJspog1 = new widget.TextBox();
        jLabel155 = new widget.Label();
        jLabel156 = new widget.Label();
        Tspog2 = new widget.TextBox();
        jLabel157 = new widget.Label();
        TJspog2 = new widget.TextBox();
        jLabel158 = new widget.Label();
        jLabel159 = new widget.Label();
        ChkHipertensi1 = new widget.CekBox();
        ChkDM1 = new widget.CekBox();
        ChkJantung1 = new widget.CekBox();
        ChkAsma1 = new widget.CekBox();
        ChkLainya1 = new widget.CekBox();
        TketLainya1 = new widget.TextBox();
        jLabel160 = new widget.Label();
        ChkHipertensi2 = new widget.CekBox();
        ChkDM2 = new widget.CekBox();
        ChkJantung2 = new widget.CekBox();
        ChkAsma2 = new widget.CekBox();
        ChkLainya2 = new widget.CekBox();
        TketLainya2 = new widget.TextBox();
        jLabel161 = new widget.Label();
        TriwGine = new widget.TextBox();
        jLabel162 = new widget.Label();
        ChkPil = new widget.CekBox();
        Tpil = new widget.TextBox();
        cmbSatuanPil = new widget.ComboBox();
        ChkSuntik1 = new widget.CekBox();
        Tsuntik1 = new widget.TextBox();
        cmbSatuanSuntik1 = new widget.ComboBox();
        ChkSuntik3 = new widget.CekBox();
        Tsuntik3 = new widget.TextBox();
        cmbSatuanSuntik3 = new widget.ComboBox();
        ChkImplan = new widget.CekBox();
        Timplan = new widget.TextBox();
        cmbSatuanImplan = new widget.ComboBox();
        TriwKBlain = new widget.TextBox();
        jLabel163 = new widget.Label();
        jLabel189 = new widget.Label();
        Thpht = new widget.TextBox();
        jLabel195 = new widget.Label();
        Thpl = new widget.TextBox();
        jLabel196 = new widget.Label();
        Tuk = new widget.TextBox();
        jLabel197 = new widget.Label();
        jLabel198 = new widget.Label();
        Tukusg = new widget.TextBox();
        jLabel199 = new widget.Label();
        tglUSG = new widget.Tanggal();
        jLabel200 = new widget.Label();
        TbbBlmHamil = new widget.TextBox();
        jLabel201 = new widget.Label();
        TbbStlhHamil = new widget.TextBox();
        jLabel202 = new widget.Label();
        jLabel203 = new widget.Label();
        Ttbi = new widget.TextBox();
        jLabel204 = new widget.Label();
        Tbmi = new widget.TextBox();
        jLabel205 = new widget.Label();
        Tlila = new widget.TextBox();
        jLabel206 = new widget.Label();
        ChkDismen = new widget.CekBox();
        ChkSpoting = new widget.CekBox();
        ChkMenor = new widget.CekBox();
        ChkMetro = new widget.CekBox();
        TkeluhanLain = new widget.TextBox();
        jLabel207 = new widget.Label();
        jLabel208 = new widget.Label();
        Tleopold1 = new widget.TextBox();
        jLabel209 = new widget.Label();
        Tleopold2 = new widget.TextBox();
        jLabel210 = new widget.Label();
        Tleopold3 = new widget.TextBox();
        jLabel211 = new widget.Label();
        Tleopold4 = new widget.TextBox();
        ChkNyeriTekan = new widget.CekBox();
        ChkBandle = new widget.CekBox();
        jLabel212 = new widget.Label();
        jLabel213 = new widget.Label();
        Ttfu = new widget.TextBox();
        jLabel214 = new widget.Label();
        TbbJanin = new widget.TextBox();
        jLabel215 = new widget.Label();
        ChkHis = new widget.CekBox();
        ThisKontraksi = new widget.TextBox();
        jLabel216 = new widget.Label();
        ChkTeratur = new widget.CekBox();
        ChkTdkTeratur = new widget.CekBox();
        ChkTrsMenerus = new widget.CekBox();
        jLabel217 = new widget.Label();
        Tdurasi = new widget.TextBox();
        jLabel218 = new widget.Label();
        ChkKuat = new widget.CekBox();
        ChkSedang = new widget.CekBox();
        ChkLemah = new widget.CekBox();
        jLabel219 = new widget.Label();
        Tauskultasi = new widget.TextBox();
        jLabel220 = new widget.Label();
        jLabel221 = new widget.Label();
        ChkBersih = new widget.CekBox();
        ChkOedema = new widget.CekBox();
        ChkRuptur = new widget.CekBox();
        ChkCandiloma = new widget.CekBox();
        Tgenitalia_lain = new widget.TextBox();
        jLabel222 = new widget.Label();
        Tinspeksi = new widget.TextBox();
        jLabel223 = new widget.Label();
        jLabel224 = new widget.Label();
        Tperiksa_dlm = new widget.TextBox();
        jLabel225 = new widget.Label();
        Tinspekulum = new widget.TextBox();
        jLabel47 = new widget.Label();
        cmbKonsistensi = new widget.ComboBox();
        cmbNadi2 = new widget.ComboBox();
        cmbAkral2 = new widget.ComboBox();
        BtnHasil = new widget.Button();
        BtnDiagnosis = new widget.Button();
        BtnPlaning = new widget.Button();
        BtnAlasan = new widget.Button();
        ChkJamKeluar = new widget.CekBox();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        TglKeluar = new widget.Tanggal();
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

        WindowTemplate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTemplate.setName("WindowTemplate"); // NOI18N
        WindowTemplate.setUndecorated(true);
        WindowTemplate.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Asesmen Medik Obstetri ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame4.setLayout(new java.awt.BorderLayout());

        panelisi4.setBackground(new java.awt.Color(255, 150, 255));
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Key Word :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel9.setRequestFocusEnabled(false);
        panelisi4.add(jLabel9);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi4.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+1");
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
        panelisi4.add(BtnCari1);

        BtnCopas.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopas.setMnemonic('U');
        BtnCopas.setText("Copy & Paste");
        BtnCopas.setToolTipText("Alt+U");
        BtnCopas.setName("BtnCopas"); // NOI18N
        BtnCopas.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCopas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopasActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCopas);

        BtnCloseIn1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn1.setMnemonic('U');
        BtnCloseIn1.setText("Tutup");
        BtnCloseIn1.setToolTipText("Alt+U");
        BtnCloseIn1.setName("BtnCloseIn1"); // NOI18N
        BtnCloseIn1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn1ActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCloseIn1);

        internalFrame4.add(panelisi4, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 250));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTemplate.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTemplate.setName("tbTemplate"); // NOI18N
        tbTemplate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTemplateMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbTemplate);

        jPanel1.add(Scroll1);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        Ttemplate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Baca Template Dipilih ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Ttemplate.setColumns(20);
        Ttemplate.setRows(5);
        Ttemplate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ttemplate.setName("Ttemplate"); // NOI18N
        Scroll3.setViewportView(Ttemplate);

        jPanel1.add(Scroll3);

        internalFrame4.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        WindowTemplate.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Medik Obstetri ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        BtnResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Vial-Pills.png"))); // NOI18N
        BtnResep.setMnemonic('R');
        BtnResep.setText("Resep Obat");
        BtnResep.setToolTipText("Alt+R");
        BtnResep.setName("BtnResep"); // NOI18N
        BtnResep.setPreferredSize(new java.awt.Dimension(120, 30));
        BtnResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResepActionPerformed(evt);
            }
        });
        BtnResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnResepKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnResep);

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

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis");
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2375));
        FormInput.setLayout(null);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 120, 880, 1);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(157, 30, 131, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(362, 30, 300, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(290, 30, 70, 23);

        label14.setForeground(new java.awt.Color(0, 0, 0));
        label14.setText("Bidan Yg. Menyerahkan :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(0, 90, 150, 23);

        KdBidan.setEditable(false);
        KdBidan.setForeground(new java.awt.Color(0, 0, 0));
        KdBidan.setName("KdBidan"); // NOI18N
        KdBidan.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdBidan);
        KdBidan.setBounds(157, 90, 100, 23);

        NmBidan.setEditable(false);
        NmBidan.setForeground(new java.awt.Color(0, 0, 0));
        NmBidan.setName("NmBidan"); // NOI18N
        NmBidan.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmBidan);
        NmBidan.setBounds(260, 90, 366, 23);

        BtnBidan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnBidan.setMnemonic('2');
        BtnBidan.setToolTipText("Alt+2");
        BtnBidan.setName("BtnBidan"); // NOI18N
        BtnBidan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnBidan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBidanActionPerformed(evt);
            }
        });
        FormInput.add(BtnBidan);
        BtnBidan.setBounds(630, 90, 28, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Lahir : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(665, 30, 80, 23);

        TglLahir.setEditable(false);
        TglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(747, 30, 80, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 30, 150, 23);

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tgl. Asesmen : ");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(665, 60, 80, 23);

        TglAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023 17:28:00" }));
        TglAsesmen.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsesmen.setName("TglAsesmen"); // NOI18N
        TglAsesmen.setOpaque(false);
        FormInput.add(TglAsesmen);
        TglAsesmen.setBounds(747, 60, 135, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setText("INTERVENSI PREHOSPITAL");
        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(10, 120, 330, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("MULAI PENANGANAN :");
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(10, 8, 130, 23);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("Mengetahui DPJP :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(0, 60, 150, 23);

        kddpjp.setEditable(false);
        kddpjp.setForeground(new java.awt.Color(0, 0, 0));
        kddpjp.setName("kddpjp"); // NOI18N
        kddpjp.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(kddpjp);
        kddpjp.setBounds(157, 60, 100, 23);

        nmdpjp.setEditable(false);
        nmdpjp.setForeground(new java.awt.Color(0, 0, 0));
        nmdpjp.setName("nmdpjp"); // NOI18N
        nmdpjp.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(nmdpjp);
        nmdpjp.setBounds(260, 60, 366, 23);

        BtnDpjp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDpjp.setMnemonic('2');
        BtnDpjp.setToolTipText("Alt+2");
        BtnDpjp.setName("BtnDpjp"); // NOI18N
        BtnDpjp.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDpjp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDpjpActionPerformed(evt);
            }
        });
        FormInput.add(BtnDpjp);
        BtnDpjp.setBounds(630, 60, 28, 23);

        ChkCervical.setBackground(new java.awt.Color(255, 255, 250));
        ChkCervical.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCervical.setForeground(new java.awt.Color(0, 0, 0));
        ChkCervical.setText("Cervical Collar");
        ChkCervical.setBorderPainted(true);
        ChkCervical.setBorderPaintedFlat(true);
        ChkCervical.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCervical.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCervical.setName("ChkCervical"); // NOI18N
        ChkCervical.setOpaque(false);
        ChkCervical.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkCervical);
        ChkCervical.setBounds(20, 150, 95, 23);

        ChkRJP.setBackground(new java.awt.Color(255, 255, 250));
        ChkRJP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkRJP.setForeground(new java.awt.Color(0, 0, 0));
        ChkRJP.setText("RJP");
        ChkRJP.setBorderPainted(true);
        ChkRJP.setBorderPaintedFlat(true);
        ChkRJP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkRJP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkRJP.setName("ChkRJP"); // NOI18N
        ChkRJP.setOpaque(false);
        ChkRJP.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkRJP);
        ChkRJP.setBounds(20, 180, 95, 23);

        ChkDefribilasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkDefribilasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDefribilasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkDefribilasi.setText("Defribrilasi");
        ChkDefribilasi.setBorderPainted(true);
        ChkDefribilasi.setBorderPaintedFlat(true);
        ChkDefribilasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDefribilasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDefribilasi.setName("ChkDefribilasi"); // NOI18N
        ChkDefribilasi.setOpaque(false);
        ChkDefribilasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDefribilasi);
        ChkDefribilasi.setBounds(130, 150, 80, 23);

        ChkIntubasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkIntubasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkIntubasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkIntubasi.setText("Intubasi");
        ChkIntubasi.setBorderPainted(true);
        ChkIntubasi.setBorderPaintedFlat(true);
        ChkIntubasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkIntubasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkIntubasi.setName("ChkIntubasi"); // NOI18N
        ChkIntubasi.setOpaque(false);
        ChkIntubasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkIntubasi);
        ChkIntubasi.setBounds(130, 180, 80, 23);

        ChkVTP.setBackground(new java.awt.Color(255, 255, 250));
        ChkVTP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkVTP.setForeground(new java.awt.Color(0, 0, 0));
        ChkVTP.setText("VTP");
        ChkVTP.setBorderPainted(true);
        ChkVTP.setBorderPaintedFlat(true);
        ChkVTP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkVTP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkVTP.setName("ChkVTP"); // NOI18N
        ChkVTP.setOpaque(false);
        ChkVTP.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkVTP);
        ChkVTP.setBounds(220, 150, 80, 23);

        ChkDekompresi.setBackground(new java.awt.Color(255, 255, 250));
        ChkDekompresi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDekompresi.setForeground(new java.awt.Color(0, 0, 0));
        ChkDekompresi.setText("Dekompresi Jarum/WSD*");
        ChkDekompresi.setBorderPainted(true);
        ChkDekompresi.setBorderPaintedFlat(true);
        ChkDekompresi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDekompresi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDekompresi.setName("ChkDekompresi"); // NOI18N
        ChkDekompresi.setOpaque(false);
        ChkDekompresi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDekompresi);
        ChkDekompresi.setBounds(220, 180, 150, 23);

        ChkBalut.setBackground(new java.awt.Color(255, 255, 250));
        ChkBalut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBalut.setForeground(new java.awt.Color(0, 0, 0));
        ChkBalut.setText("Balut / Bidai*");
        ChkBalut.setBorderPainted(true);
        ChkBalut.setBorderPaintedFlat(true);
        ChkBalut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBalut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBalut.setName("ChkBalut"); // NOI18N
        ChkBalut.setOpaque(false);
        ChkBalut.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkBalut);
        ChkBalut.setBounds(380, 150, 90, 23);

        ChkKateter.setBackground(new java.awt.Color(255, 255, 250));
        ChkKateter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKateter.setForeground(new java.awt.Color(0, 0, 0));
        ChkKateter.setText("Kateter Urin");
        ChkKateter.setBorderPainted(true);
        ChkKateter.setBorderPaintedFlat(true);
        ChkKateter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKateter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKateter.setName("ChkKateter"); // NOI18N
        ChkKateter.setOpaque(false);
        ChkKateter.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKateter);
        ChkKateter.setBounds(380, 180, 90, 23);

        ChkNGT.setBackground(new java.awt.Color(255, 255, 250));
        ChkNGT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNGT.setForeground(new java.awt.Color(0, 0, 0));
        ChkNGT.setText("NGT");
        ChkNGT.setBorderPainted(true);
        ChkNGT.setBorderPaintedFlat(true);
        ChkNGT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNGT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNGT.setName("ChkNGT"); // NOI18N
        ChkNGT.setOpaque(false);
        ChkNGT.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkNGT);
        ChkNGT.setBounds(480, 150, 50, 23);

        ChkInfus.setBackground(new java.awt.Color(255, 255, 250));
        ChkInfus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkInfus.setForeground(new java.awt.Color(0, 0, 0));
        ChkInfus.setText("Infus");
        ChkInfus.setBorderPainted(true);
        ChkInfus.setBorderPaintedFlat(true);
        ChkInfus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInfus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInfus.setName("ChkInfus"); // NOI18N
        ChkInfus.setOpaque(false);
        ChkInfus.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkInfus);
        ChkInfus.setBounds(480, 180, 50, 23);

        ChkObat.setBackground(new java.awt.Color(255, 255, 250));
        ChkObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObat.setForeground(new java.awt.Color(0, 0, 0));
        ChkObat.setText("Obat");
        ChkObat.setBorderPainted(true);
        ChkObat.setBorderPaintedFlat(true);
        ChkObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkObat.setName("ChkObat"); // NOI18N
        ChkObat.setOpaque(false);
        ChkObat.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkObatActionPerformed(evt);
            }
        });
        FormInput.add(ChkObat);
        ChkObat.setBounds(540, 150, 50, 23);

        TObat.setForeground(new java.awt.Color(0, 0, 0));
        TObat.setName("TObat"); // NOI18N
        FormInput.add(TObat);
        TObat.setBounds(594, 150, 260, 23);

        ChkTidak.setBackground(new java.awt.Color(255, 255, 250));
        ChkTidak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTidak.setForeground(new java.awt.Color(0, 0, 0));
        ChkTidak.setText("Tidak Ada");
        ChkTidak.setBorderPainted(true);
        ChkTidak.setBorderPaintedFlat(true);
        ChkTidak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTidak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTidak.setName("ChkTidak"); // NOI18N
        ChkTidak.setOpaque(false);
        ChkTidak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTidak);
        ChkTidak.setBounds(540, 180, 90, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 210, 880, 1);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText("SURVEI PRIMER");
        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(10, 210, 330, 23);

        jLabel100.setForeground(new java.awt.Color(0, 51, 204));
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("Jalan Nafas :");
        jLabel100.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(20, 230, 110, 23);

        ChkPaten.setBackground(new java.awt.Color(255, 255, 250));
        ChkPaten.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPaten.setForeground(new java.awt.Color(0, 0, 0));
        ChkPaten.setText("Paten");
        ChkPaten.setBorderPainted(true);
        ChkPaten.setBorderPaintedFlat(true);
        ChkPaten.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPaten.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPaten.setName("ChkPaten"); // NOI18N
        ChkPaten.setOpaque(false);
        ChkPaten.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPaten);
        ChkPaten.setBounds(20, 250, 60, 23);

        ChkObstruksiP.setBackground(new java.awt.Color(255, 255, 250));
        ChkObstruksiP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObstruksiP.setForeground(new java.awt.Color(0, 0, 0));
        ChkObstruksiP.setText("Obstruksi Partial");
        ChkObstruksiP.setBorderPainted(true);
        ChkObstruksiP.setBorderPaintedFlat(true);
        ChkObstruksiP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkObstruksiP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkObstruksiP.setName("ChkObstruksiP"); // NOI18N
        ChkObstruksiP.setOpaque(false);
        ChkObstruksiP.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkObstruksiP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkObstruksiPActionPerformed(evt);
            }
        });
        FormInput.add(ChkObstruksiP);
        ChkObstruksiP.setBounds(20, 280, 100, 23);

        ChkObstruksiT.setBackground(new java.awt.Color(255, 255, 250));
        ChkObstruksiT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObstruksiT.setForeground(new java.awt.Color(0, 0, 0));
        ChkObstruksiT.setText("Obstruksi Total");
        ChkObstruksiT.setBorderPainted(true);
        ChkObstruksiT.setBorderPaintedFlat(true);
        ChkObstruksiT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkObstruksiT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkObstruksiT.setName("ChkObstruksiT"); // NOI18N
        ChkObstruksiT.setOpaque(false);
        ChkObstruksiT.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkObstruksiT);
        ChkObstruksiT.setBounds(20, 310, 100, 23);

        ChkTrauma.setBackground(new java.awt.Color(255, 255, 250));
        ChkTrauma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTrauma.setForeground(new java.awt.Color(0, 0, 0));
        ChkTrauma.setText("Trauma Jalan Nafas :");
        ChkTrauma.setBorderPainted(true);
        ChkTrauma.setBorderPaintedFlat(true);
        ChkTrauma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTrauma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTrauma.setName("ChkTrauma"); // NOI18N
        ChkTrauma.setOpaque(false);
        ChkTrauma.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkTrauma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkTraumaActionPerformed(evt);
            }
        });
        FormInput.add(ChkTrauma);
        ChkTrauma.setBounds(20, 340, 123, 23);

        cmbTrauma.setForeground(new java.awt.Color(0, 0, 0));
        cmbTrauma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Fasial", "Leher", "Inhalasi" }));
        cmbTrauma.setName("cmbTrauma"); // NOI18N
        FormInput.add(cmbTrauma);
        cmbTrauma.setBounds(145, 340, 70, 23);

        ChkResiko.setBackground(new java.awt.Color(255, 255, 250));
        ChkResiko.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkResiko.setForeground(new java.awt.Color(0, 0, 0));
        ChkResiko.setText("Resiko Aspirasi :");
        ChkResiko.setBorderPainted(true);
        ChkResiko.setBorderPaintedFlat(true);
        ChkResiko.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkResiko.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkResiko.setName("ChkResiko"); // NOI18N
        ChkResiko.setOpaque(false);
        ChkResiko.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkResiko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkResikoActionPerformed(evt);
            }
        });
        FormInput.add(ChkResiko);
        ChkResiko.setBounds(20, 370, 100, 23);

        cmbResiko.setForeground(new java.awt.Color(0, 0, 0));
        cmbResiko.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Perdarahan", "Muntahan" }));
        cmbResiko.setName("cmbResiko"); // NOI18N
        FormInput.add(cmbResiko);
        cmbResiko.setBounds(122, 370, 92, 23);

        ChkBendaAsing.setBackground(new java.awt.Color(255, 255, 250));
        ChkBendaAsing.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBendaAsing.setForeground(new java.awt.Color(0, 0, 0));
        ChkBendaAsing.setText("Benda Asing : ");
        ChkBendaAsing.setBorderPainted(true);
        ChkBendaAsing.setBorderPaintedFlat(true);
        ChkBendaAsing.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBendaAsing.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBendaAsing.setName("ChkBendaAsing"); // NOI18N
        ChkBendaAsing.setOpaque(false);
        ChkBendaAsing.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkBendaAsing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkBendaAsingActionPerformed(evt);
            }
        });
        FormInput.add(ChkBendaAsing);
        ChkBendaAsing.setBounds(20, 400, 88, 23);

        TBendaAsing.setForeground(new java.awt.Color(0, 0, 0));
        TBendaAsing.setName("TBendaAsing"); // NOI18N
        FormInput.add(TBendaAsing);
        TBendaAsing.setBounds(110, 400, 280, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Kesimpulan Jalan Nafas :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(20, 430, 130, 23);

        cmbKesJalanNafas.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesJalanNafas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Aman", "Mengancam Jiwa" }));
        cmbKesJalanNafas.setName("cmbKesJalanNafas"); // NOI18N
        FormInput.add(cmbKesJalanNafas);
        cmbKesJalanNafas.setBounds(155, 430, 115, 23);

        jLabel102.setForeground(new java.awt.Color(0, 51, 204));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel102.setText("Pernapasan :");
        jLabel102.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(295, 230, 80, 23);

        cmbPernapasan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPernapasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Spontan", "Tidak Spontan" }));
        cmbPernapasan.setName("cmbPernapasan"); // NOI18N
        cmbPernapasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPernapasanActionPerformed(evt);
            }
        });
        FormInput.add(cmbPernapasan);
        cmbPernapasan.setBounds(295, 250, 105, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Gerakan Dada :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(270, 280, 130, 23);

        cmbGerakanDada.setForeground(new java.awt.Color(0, 0, 0));
        cmbGerakanDada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Simetris", "Asimetris", "Jejas dinding dada kanan", "Jejas dinding dada kiri", "Jejas dinding dada kanan & kiri" }));
        cmbGerakanDada.setName("cmbGerakanDada"); // NOI18N
        FormInput.add(cmbGerakanDada);
        cmbGerakanDada.setBounds(405, 280, 183, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Tipe Pernapasan :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(270, 310, 130, 23);

        cmbTipePernapasan.setForeground(new java.awt.Color(0, 0, 0));
        cmbTipePernapasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Normal", "Kussmaul", "Biot", "Apneustic", "Retraktif", "Takipneu", "Hiperventilasi", "Cheyne Stoke", "Flare" }));
        cmbTipePernapasan.setName("cmbTipePernapasan"); // NOI18N
        FormInput.add(cmbTipePernapasan);
        cmbTipePernapasan.setBounds(405, 310, 100, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Kesimpulan Pernapasan :");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(270, 340, 130, 23);

        cmbKesPernapasan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesPernapasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Aman", "Mengancam Jiwa" }));
        cmbKesPernapasan.setName("cmbKesPernapasan"); // NOI18N
        FormInput.add(cmbKesPernapasan);
        cmbKesPernapasan.setBounds(405, 340, 115, 23);

        jLabel106.setForeground(new java.awt.Color(0, 51, 204));
        jLabel106.setText("Sirkulasi :");
        jLabel106.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(630, 230, 80, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("Nadi :");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(630, 250, 80, 23);

        cmbNadi1.setForeground(new java.awt.Color(0, 0, 0));
        cmbNadi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Reguler", "Irreguler" }));
        cmbNadi1.setName("cmbNadi1"); // NOI18N
        FormInput.add(cmbNadi1);
        cmbNadi1.setBounds(715, 250, 80, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("Kulit / Mukosa :");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(630, 280, 80, 23);

        cmbKulit.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Normal", "Jaundice", "Berkeringat", "Pucat", "Cyanosis" }));
        cmbKulit.setName("cmbKulit"); // NOI18N
        FormInput.add(cmbKulit);
        cmbKulit.setBounds(715, 280, 90, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("Akral :");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(630, 310, 80, 23);

        cmbAkral1.setForeground(new java.awt.Color(0, 0, 0));
        cmbAkral1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Hangat", "Dingin" }));
        cmbAkral1.setName("cmbAkral1"); // NOI18N
        FormInput.add(cmbAkral1);
        cmbAkral1.setBounds(715, 310, 70, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("CRT :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(630, 340, 80, 23);

        cmbCRT.setForeground(new java.awt.Color(0, 0, 0));
        cmbCRT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "< 2 detik", "> 2 detik" }));
        cmbCRT.setName("cmbCRT"); // NOI18N
        FormInput.add(cmbCRT);
        cmbCRT.setBounds(715, 340, 80, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Kesimpulan Sirkulasi :");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(600, 370, 110, 23);

        cmbKesSirkulasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesSirkulasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Aman", "Mengancam Jiwa" }));
        cmbKesSirkulasi.setName("cmbKesSirkulasi"); // NOI18N
        FormInput.add(cmbKesSirkulasi);
        cmbKesSirkulasi.setBounds(715, 370, 115, 23);

        jLabel118.setForeground(new java.awt.Color(0, 51, 204));
        jLabel118.setText("Disabilitas :");
        jLabel118.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(370, 420, 80, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("GCS :");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(450, 420, 40, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("Pupil :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(400, 450, 90, 23);

        cmbPupil.setForeground(new java.awt.Color(0, 0, 0));
        cmbPupil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Isokor", "Anisokor" }));
        cmbPupil.setName("cmbPupil"); // NOI18N
        FormInput.add(cmbPupil);
        cmbPupil.setBounds(494, 450, 75, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("Diameter (Kanan) :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(580, 450, 110, 23);

        TDiam_kanan.setForeground(new java.awt.Color(0, 0, 0));
        TDiam_kanan.setName("TDiam_kanan"); // NOI18N
        TDiam_kanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiam_kananKeyPressed(evt);
            }
        });
        FormInput.add(TDiam_kanan);
        TDiam_kanan.setBounds(695, 450, 45, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("Reflek Cahaya :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(400, 480, 90, 23);

        TReflek.setForeground(new java.awt.Color(0, 0, 0));
        TReflek.setName("TReflek"); // NOI18N
        TReflek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TReflekKeyPressed(evt);
            }
        });
        FormInput.add(TReflek);
        TReflek.setBounds(494, 480, 80, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("Meningeal Signs :");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(400, 510, 90, 23);

        TMeningeal.setForeground(new java.awt.Color(0, 0, 0));
        TMeningeal.setName("TMeningeal"); // NOI18N
        TMeningeal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMeningealKeyPressed(evt);
            }
        });
        FormInput.add(TMeningeal);
        TMeningeal.setBounds(494, 510, 330, 23);

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setText("Lateralisasi :");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(400, 540, 90, 23);

        cmbLater.setForeground(new java.awt.Color(0, 0, 0));
        cmbLater.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak Ada", "Kanan", "Kiri" }));
        cmbLater.setName("cmbLater"); // NOI18N
        FormInput.add(cmbLater);
        cmbLater.setBounds(494, 540, 80, 23);

        jLabel125.setForeground(new java.awt.Color(0, 51, 204));
        jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel125.setText("Eksposur :");
        jLabel125.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(20, 460, 70, 23);

        jLabel164.setForeground(new java.awt.Color(0, 0, 0));
        jLabel164.setText("Diagnosis Medis Sementara / Masalah :");
        jLabel164.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel164.setName("jLabel164"); // NOI18N
        FormInput.add(jLabel164);
        jLabel164.setBounds(0, 1738, 250, 23);

        scrollPane9.setName("scrollPane9"); // NOI18N

        TDiagSementara.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TDiagSementara.setColumns(20);
        TDiagSementara.setRows(5);
        TDiagSementara.setName("TDiagSementara"); // NOI18N
        TDiagSementara.setPreferredSize(new java.awt.Dimension(162, 200));
        TDiagSementara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagSementaraKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(TDiagSementara);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(25, 1758, 680, 70);

        jLabel165.setForeground(new java.awt.Color(0, 0, 0));
        jLabel165.setText("ICD-10 :");
        jLabel165.setName("jLabel165"); // NOI18N
        FormInput.add(jLabel165);
        jLabel165.setBounds(705, 1758, 60, 23);

        Ticd.setForeground(new java.awt.Color(0, 0, 0));
        Ticd.setName("Ticd"); // NOI18N
        Ticd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TicdKeyPressed(evt);
            }
        });
        FormInput.add(Ticd);
        Ticd.setBounds(770, 1758, 80, 23);

        jLabel166.setForeground(new java.awt.Color(0, 0, 0));
        jLabel166.setText("Rencana / Instruksi :");
        jLabel166.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel166.setName("jLabel166"); // NOI18N
        FormInput.add(jLabel166);
        jLabel166.setBounds(0, 1835, 140, 23);

        jLabel167.setForeground(new java.awt.Color(0, 0, 0));
        jLabel167.setText("Telah Diberikan Informasi / Edukasi Tentang :");
        jLabel167.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel167.setName("jLabel167"); // NOI18N
        FormInput.add(jLabel167);
        jLabel167.setBounds(0, 1953, 280, 23);

        jLabel168.setForeground(new java.awt.Color(0, 0, 0));
        jLabel168.setText("Informasi / Edukasi Tentang :");
        jLabel168.setName("jLabel168"); // NOI18N
        FormInput.add(jLabel168);
        jLabel168.setBounds(10, 1973, 150, 23);

        Tedukasi.setForeground(new java.awt.Color(0, 0, 0));
        Tedukasi.setName("Tedukasi"); // NOI18N
        Tedukasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TedukasiKeyPressed(evt);
            }
        });
        FormInput.add(Tedukasi);
        Tedukasi.setBounds(165, 1973, 710, 23);

        jLabel169.setForeground(new java.awt.Color(0, 0, 0));
        jLabel169.setText("Rencana Asuhan Diharapkan :");
        jLabel169.setName("jLabel169"); // NOI18N
        FormInput.add(jLabel169);
        jLabel169.setBounds(10, 2003, 150, 23);

        Trencana.setForeground(new java.awt.Color(0, 0, 0));
        Trencana.setName("Trencana"); // NOI18N
        Trencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrencanaKeyPressed(evt);
            }
        });
        FormInput.add(Trencana);
        Trencana.setBounds(165, 2003, 710, 23);

        jLabel170.setForeground(new java.awt.Color(0, 0, 0));
        jLabel170.setText("Pemberi Edukasi / Informasi :");
        jLabel170.setName("jLabel170"); // NOI18N
        FormInput.add(jLabel170);
        jLabel170.setBounds(10, 2033, 150, 23);

        kdpetugas.setEditable(false);
        kdpetugas.setForeground(new java.awt.Color(0, 0, 0));
        kdpetugas.setName("kdpetugas"); // NOI18N
        FormInput.add(kdpetugas);
        kdpetugas.setBounds(165, 2033, 110, 23);

        nmpetugas.setEditable(false);
        nmpetugas.setForeground(new java.awt.Color(0, 0, 0));
        nmpetugas.setName("nmpetugas"); // NOI18N
        FormInput.add(nmpetugas);
        nmpetugas.setBounds(280, 2033, 260, 23);

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
        BtnPetugas.setBounds(540, 2033, 28, 23);

        jLabel171.setForeground(new java.awt.Color(0, 0, 0));
        jLabel171.setText("Penerima Edukasi :");
        jLabel171.setName("jLabel171"); // NOI18N
        FormInput.add(jLabel171);
        jLabel171.setBounds(575, 2033, 100, 23);

        nmpenerima.setForeground(new java.awt.Color(0, 0, 0));
        nmpenerima.setName("nmpenerima"); // NOI18N
        FormInput.add(nmpenerima);
        nmpenerima.setBounds(680, 2033, 195, 23);

        jLabel172.setForeground(new java.awt.Color(0, 0, 0));
        jLabel172.setText("Nama Dokter : ");
        jLabel172.setName("jLabel172"); // NOI18N
        FormInput.add(jLabel172);
        jLabel172.setBounds(10, 2063, 150, 23);

        kddokter.setEditable(false);
        kddokter.setForeground(new java.awt.Color(0, 0, 0));
        kddokter.setName("kddokter"); // NOI18N
        FormInput.add(kddokter);
        kddokter.setBounds(165, 2063, 110, 23);

        nmdokter.setEditable(false);
        nmdokter.setForeground(new java.awt.Color(0, 0, 0));
        nmdokter.setName("nmdokter"); // NOI18N
        FormInput.add(nmdokter);
        nmdokter.setBounds(280, 2063, 260, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(540, 2063, 28, 23);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 2093, 880, 1);

        jLabel173.setForeground(new java.awt.Color(0, 0, 0));
        jLabel173.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel173.setText("PASIEN KELUAR PONEK");
        jLabel173.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel173.setName("jLabel173"); // NOI18N
        FormInput.add(jLabel173);
        jLabel173.setBounds(10, 2093, 200, 23);

        jLabel174.setForeground(new java.awt.Color(0, 0, 0));
        jLabel174.setText("Tgl. & Jam Keluar :");
        jLabel174.setName("jLabel174"); // NOI18N
        FormInput.add(jLabel174);
        jLabel174.setBounds(10, 2113, 125, 23);

        jLabel175.setForeground(new java.awt.Color(0, 0, 0));
        jLabel175.setText("Opname Diruangan :");
        jLabel175.setName("jLabel175"); // NOI18N
        FormInput.add(jLabel175);
        jLabel175.setBounds(10, 2173, 125, 23);

        cmbRuangan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "AS-SAMI/1", "AS-SAMI/2" }));
        cmbRuangan.setName("cmbRuangan"); // NOI18N
        cmbRuangan.setPreferredSize(new java.awt.Dimension(145, 23));
        cmbRuangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbRuanganMouseReleased(evt);
            }
        });
        FormInput.add(cmbRuangan);
        cmbRuangan.setBounds(140, 2173, 170, 23);

        jLabel176.setForeground(new java.awt.Color(0, 0, 0));
        jLabel176.setText("Indikasi Masuk :");
        jLabel176.setName("jLabel176"); // NOI18N
        FormInput.add(jLabel176);
        jLabel176.setBounds(10, 2203, 125, 23);

        Tindikasi.setForeground(new java.awt.Color(0, 0, 0));
        Tindikasi.setName("Tindikasi"); // NOI18N
        Tindikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindikasiKeyPressed(evt);
            }
        });
        FormInput.add(Tindikasi);
        Tindikasi.setBounds(140, 2203, 330, 23);

        jLabel177.setForeground(new java.awt.Color(0, 0, 0));
        jLabel177.setText("Dipulangkan, Kontrol Ke :");
        jLabel177.setName("jLabel177"); // NOI18N
        FormInput.add(jLabel177);
        jLabel177.setBounds(10, 2233, 125, 23);

        Tdipulangkan.setForeground(new java.awt.Color(0, 0, 0));
        Tdipulangkan.setName("Tdipulangkan"); // NOI18N
        Tdipulangkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdipulangkanKeyPressed(evt);
            }
        });
        FormInput.add(Tdipulangkan);
        Tdipulangkan.setBounds(140, 2233, 330, 23);

        jLabel178.setForeground(new java.awt.Color(0, 0, 0));
        jLabel178.setText("Dirujuk Ke :");
        jLabel178.setName("jLabel178"); // NOI18N
        FormInput.add(jLabel178);
        jLabel178.setBounds(10, 2263, 125, 23);

        Tdirujuk.setForeground(new java.awt.Color(0, 0, 0));
        Tdirujuk.setName("Tdirujuk"); // NOI18N
        Tdirujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdirujukKeyPressed(evt);
            }
        });
        FormInput.add(Tdirujuk);
        Tdirujuk.setBounds(140, 2263, 330, 23);

        jLabel179.setForeground(new java.awt.Color(0, 0, 0));
        jLabel179.setText("Alasan Dirujuk :");
        jLabel179.setName("jLabel179"); // NOI18N
        FormInput.add(jLabel179);
        jLabel179.setBounds(10, 2293, 125, 23);

        scrollPane10.setName("scrollPane10"); // NOI18N

        TAlasanDirujuk.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TAlasanDirujuk.setColumns(20);
        TAlasanDirujuk.setRows(5);
        TAlasanDirujuk.setName("TAlasanDirujuk"); // NOI18N
        TAlasanDirujuk.setPreferredSize(new java.awt.Dimension(162, 110));
        TAlasanDirujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlasanDirujukKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(TAlasanDirujuk);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(140, 2293, 690, 70);

        ChkMeninggal.setBackground(new java.awt.Color(255, 255, 250));
        ChkMeninggal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMeninggal.setForeground(new java.awt.Color(0, 0, 0));
        ChkMeninggal.setText("Meninggal Jam :");
        ChkMeninggal.setBorderPainted(true);
        ChkMeninggal.setBorderPaintedFlat(true);
        ChkMeninggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkMeninggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMeninggal.setName("ChkMeninggal"); // NOI18N
        ChkMeninggal.setOpaque(false);
        ChkMeninggal.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkMeninggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkMeninggalActionPerformed(evt);
            }
        });
        FormInput.add(ChkMeninggal);
        ChkMeninggal.setBounds(411, 2143, 100, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(515, 2143, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(565, 2143, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(615, 2143, 45, 23);

        jLabel180.setForeground(new java.awt.Color(0, 0, 0));
        jLabel180.setText("Penyebab :");
        jLabel180.setName("jLabel180"); // NOI18N
        FormInput.add(jLabel180);
        jLabel180.setBounds(420, 2173, 90, 23);

        Tpenyebab.setForeground(new java.awt.Color(0, 0, 0));
        Tpenyebab.setName("Tpenyebab"); // NOI18N
        Tpenyebab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpenyebabKeyPressed(evt);
            }
        });
        FormInput.add(Tpenyebab);
        Tpenyebab.setBounds(515, 2173, 360, 23);

        jLabel181.setForeground(new java.awt.Color(0, 0, 0));
        jLabel181.setText("K/u :");
        jLabel181.setName("jLabel181"); // NOI18N
        FormInput.add(jLabel181);
        jLabel181.setBounds(470, 2203, 40, 23);

        Tku.setForeground(new java.awt.Color(0, 0, 0));
        Tku.setName("Tku"); // NOI18N
        Tku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkuKeyPressed(evt);
            }
        });
        FormInput.add(Tku);
        Tku.setBounds(515, 2203, 360, 23);

        jLabel182.setForeground(new java.awt.Color(0, 0, 0));
        jLabel182.setText("TD :");
        jLabel182.setName("jLabel182"); // NOI18N
        FormInput.add(jLabel182);
        jLabel182.setBounds(470, 2233, 40, 23);

        Ttd.setForeground(new java.awt.Color(0, 0, 0));
        Ttd.setName("Ttd"); // NOI18N
        Ttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdKeyPressed(evt);
            }
        });
        FormInput.add(Ttd);
        Ttd.setBounds(515, 2233, 70, 23);

        jLabel183.setForeground(new java.awt.Color(0, 0, 0));
        jLabel183.setText("HR :");
        jLabel183.setName("jLabel183"); // NOI18N
        FormInput.add(jLabel183);
        jLabel183.setBounds(470, 2263, 40, 23);

        Thr.setForeground(new java.awt.Color(0, 0, 0));
        Thr.setName("Thr"); // NOI18N
        Thr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThrKeyPressed(evt);
            }
        });
        FormInput.add(Thr);
        Thr.setBounds(515, 2263, 70, 23);

        jLabel184.setForeground(new java.awt.Color(0, 0, 0));
        jLabel184.setText("RR :");
        jLabel184.setName("jLabel184"); // NOI18N
        FormInput.add(jLabel184);
        jLabel184.setBounds(590, 2233, 40, 23);

        jLabel185.setForeground(new java.awt.Color(0, 0, 0));
        jLabel185.setText("Temp :");
        jLabel185.setName("jLabel185"); // NOI18N
        FormInput.add(jLabel185);
        jLabel185.setBounds(590, 2263, 40, 23);

        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        FormInput.add(Trr);
        Trr.setBounds(636, 2233, 70, 23);

        Ttemp.setForeground(new java.awt.Color(0, 0, 0));
        Ttemp.setName("Ttemp"); // NOI18N
        Ttemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtempKeyPressed(evt);
            }
        });
        FormInput.add(Ttemp);
        Ttemp.setBounds(636, 2263, 70, 23);

        jLabel186.setForeground(new java.awt.Color(0, 0, 0));
        jLabel186.setText("SpO2 :");
        jLabel186.setName("jLabel186"); // NOI18N
        FormInput.add(jLabel186);
        jLabel186.setBounds(708, 2233, 40, 23);

        jLabel187.setForeground(new java.awt.Color(0, 0, 0));
        jLabel187.setText("GCS :");
        jLabel187.setName("jLabel187"); // NOI18N
        FormInput.add(jLabel187);
        jLabel187.setBounds(708, 2263, 40, 23);

        Tspo2.setForeground(new java.awt.Color(0, 0, 0));
        Tspo2.setName("Tspo2"); // NOI18N
        Tspo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tspo2KeyPressed(evt);
            }
        });
        FormInput.add(Tspo2);
        Tspo2.setBounds(754, 2233, 70, 23);

        Tgcs.setForeground(new java.awt.Color(0, 0, 0));
        Tgcs.setName("Tgcs"); // NOI18N
        Tgcs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsKeyPressed(evt);
            }
        });
        FormInput.add(Tgcs);
        Tgcs.setBounds(754, 2263, 70, 23);

        cmbObstruksi.setForeground(new java.awt.Color(0, 0, 0));
        cmbObstruksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Stridor", "Snoring", "Gurgling", "Wheezing" }));
        cmbObstruksi.setName("cmbObstruksi"); // NOI18N
        FormInput.add(cmbObstruksi);
        cmbObstruksi.setBounds(125, 280, 80, 23);

        ChkDeformitas.setBackground(new java.awt.Color(255, 255, 250));
        ChkDeformitas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDeformitas.setForeground(new java.awt.Color(0, 0, 0));
        ChkDeformitas.setText("Deformitas");
        ChkDeformitas.setBorderPainted(true);
        ChkDeformitas.setBorderPaintedFlat(true);
        ChkDeformitas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDeformitas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDeformitas.setName("ChkDeformitas"); // NOI18N
        ChkDeformitas.setOpaque(false);
        ChkDeformitas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDeformitas);
        ChkDeformitas.setBounds(20, 480, 80, 23);

        ChkContusio.setBackground(new java.awt.Color(255, 255, 250));
        ChkContusio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkContusio.setForeground(new java.awt.Color(0, 0, 0));
        ChkContusio.setText("Contusio");
        ChkContusio.setBorderPainted(true);
        ChkContusio.setBorderPaintedFlat(true);
        ChkContusio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkContusio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkContusio.setName("ChkContusio"); // NOI18N
        ChkContusio.setOpaque(false);
        ChkContusio.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkContusio);
        ChkContusio.setBounds(20, 510, 80, 23);

        ChkPenetrasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkPenetrasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPenetrasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkPenetrasi.setText("Penetrasi");
        ChkPenetrasi.setBorderPainted(true);
        ChkPenetrasi.setBorderPaintedFlat(true);
        ChkPenetrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPenetrasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPenetrasi.setName("ChkPenetrasi"); // NOI18N
        ChkPenetrasi.setOpaque(false);
        ChkPenetrasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPenetrasi);
        ChkPenetrasi.setBounds(20, 540, 80, 23);

        ChkTenderness.setBackground(new java.awt.Color(255, 255, 250));
        ChkTenderness.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTenderness.setForeground(new java.awt.Color(0, 0, 0));
        ChkTenderness.setText("Tenderness");
        ChkTenderness.setBorderPainted(true);
        ChkTenderness.setBorderPaintedFlat(true);
        ChkTenderness.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTenderness.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTenderness.setName("ChkTenderness"); // NOI18N
        ChkTenderness.setOpaque(false);
        ChkTenderness.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTenderness);
        ChkTenderness.setBounds(105, 480, 80, 23);

        ChkSwelling.setBackground(new java.awt.Color(255, 255, 250));
        ChkSwelling.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSwelling.setForeground(new java.awt.Color(0, 0, 0));
        ChkSwelling.setText("Swelling");
        ChkSwelling.setBorderPainted(true);
        ChkSwelling.setBorderPaintedFlat(true);
        ChkSwelling.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSwelling.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSwelling.setName("ChkSwelling"); // NOI18N
        ChkSwelling.setOpaque(false);
        ChkSwelling.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkSwelling);
        ChkSwelling.setBounds(105, 510, 80, 23);

        ChkEkskoriasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkEkskoriasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkEkskoriasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkEkskoriasi.setText("Ekskoriasi");
        ChkEkskoriasi.setBorderPainted(true);
        ChkEkskoriasi.setBorderPaintedFlat(true);
        ChkEkskoriasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkEkskoriasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkEkskoriasi.setName("ChkEkskoriasi"); // NOI18N
        ChkEkskoriasi.setOpaque(false);
        ChkEkskoriasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkEkskoriasi);
        ChkEkskoriasi.setBounds(105, 540, 80, 23);

        ChkAbrasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkAbrasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAbrasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkAbrasi.setText("Abrasi");
        ChkAbrasi.setBorderPainted(true);
        ChkAbrasi.setBorderPaintedFlat(true);
        ChkAbrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAbrasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAbrasi.setName("ChkAbrasi"); // NOI18N
        ChkAbrasi.setOpaque(false);
        ChkAbrasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAbrasi);
        ChkAbrasi.setBounds(195, 480, 65, 23);

        ChkBurn.setBackground(new java.awt.Color(255, 255, 250));
        ChkBurn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBurn.setForeground(new java.awt.Color(0, 0, 0));
        ChkBurn.setText("Burn");
        ChkBurn.setBorderPainted(true);
        ChkBurn.setBorderPaintedFlat(true);
        ChkBurn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBurn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBurn.setName("ChkBurn"); // NOI18N
        ChkBurn.setOpaque(false);
        ChkBurn.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkBurn);
        ChkBurn.setBounds(195, 510, 65, 23);

        ChkLaserasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkLaserasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLaserasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkLaserasi.setText("Laserasi");
        ChkLaserasi.setBorderPainted(true);
        ChkLaserasi.setBorderPaintedFlat(true);
        ChkLaserasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLaserasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLaserasi.setName("ChkLaserasi"); // NOI18N
        ChkLaserasi.setOpaque(false);
        ChkLaserasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkLaserasi);
        ChkLaserasi.setBounds(195, 540, 65, 23);

        ChkTdkTampk.setBackground(new java.awt.Color(255, 255, 250));
        ChkTdkTampk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTdkTampk.setForeground(new java.awt.Color(0, 0, 0));
        ChkTdkTampk.setText("Tidak Tampak Jelas");
        ChkTdkTampk.setBorderPainted(true);
        ChkTdkTampk.setBorderPaintedFlat(true);
        ChkTdkTampk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTdkTampk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTdkTampk.setName("ChkTdkTampk"); // NOI18N
        ChkTdkTampk.setOpaque(false);
        ChkTdkTampk.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTdkTampk);
        ChkTdkTampk.setBounds(270, 540, 120, 23);

        cmbReguler.setForeground(new java.awt.Color(0, 0, 0));
        cmbReguler.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Reguler", "Irreguler" }));
        cmbReguler.setName("cmbReguler"); // NOI18N
        FormInput.add(cmbReguler);
        cmbReguler.setBounds(405, 250, 80, 23);

        cmbRencana.setForeground(new java.awt.Color(0, 0, 0));
        cmbRencana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Kuratif", "Paliatif", "Rehabilitatif" }));
        cmbRencana.setName("cmbRencana"); // NOI18N
        cmbRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRencanaActionPerformed(evt);
            }
        });
        FormInput.add(cmbRencana);
        cmbRencana.setBounds(20, 1855, 100, 23);

        jLabel188.setForeground(new java.awt.Color(0, 0, 0));
        jLabel188.setText("Planing : ");
        jLabel188.setName("jLabel188"); // NOI18N
        FormInput.add(jLabel188);
        jLabel188.setBounds(125, 1855, 60, 23);

        btnICD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnICD.setMnemonic('2');
        btnICD.setToolTipText("Alt+2");
        btnICD.setName("btnICD"); // NOI18N
        btnICD.setPreferredSize(new java.awt.Dimension(28, 23));
        btnICD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnICDActionPerformed(evt);
            }
        });
        FormInput.add(btnICD);
        btnICD.setBounds(850, 1758, 28, 23);

        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel126.setText("E :");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(494, 420, 16, 23);

        TgcsE.setForeground(new java.awt.Color(0, 0, 0));
        TgcsE.setName("TgcsE"); // NOI18N
        TgcsE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsEKeyPressed(evt);
            }
        });
        FormInput.add(TgcsE);
        TgcsE.setBounds(510, 420, 80, 23);

        jLabel190.setForeground(new java.awt.Color(0, 0, 0));
        jLabel190.setText("V :");
        jLabel190.setName("jLabel190"); // NOI18N
        FormInput.add(jLabel190);
        jLabel190.setBounds(590, 420, 25, 23);

        TgcsV.setForeground(new java.awt.Color(0, 0, 0));
        TgcsV.setName("TgcsV"); // NOI18N
        TgcsV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsVKeyPressed(evt);
            }
        });
        FormInput.add(TgcsV);
        TgcsV.setBounds(620, 420, 80, 23);

        jLabel191.setForeground(new java.awt.Color(0, 0, 0));
        jLabel191.setText("M :");
        jLabel191.setName("jLabel191"); // NOI18N
        FormInput.add(jLabel191);
        jLabel191.setBounds(700, 420, 25, 23);

        TgcsM.setForeground(new java.awt.Color(0, 0, 0));
        TgcsM.setName("TgcsM"); // NOI18N
        TgcsM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsMKeyPressed(evt);
            }
        });
        FormInput.add(TgcsM);
        TgcsM.setBounds(730, 420, 80, 23);

        jLabel192.setForeground(new java.awt.Color(0, 0, 0));
        jLabel192.setText("Diameter (Kiri) :");
        jLabel192.setName("jLabel192"); // NOI18N
        FormInput.add(jLabel192);
        jLabel192.setBounds(580, 480, 110, 23);

        TDiam_kiri.setForeground(new java.awt.Color(0, 0, 0));
        TDiam_kiri.setName("TDiam_kiri"); // NOI18N
        TDiam_kiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiam_kiriKeyPressed(evt);
            }
        });
        FormInput.add(TDiam_kiri);
        TDiam_kiri.setBounds(695, 480, 45, 23);

        jLabel193.setForeground(new java.awt.Color(0, 0, 0));
        jLabel193.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel193.setText("mm");
        jLabel193.setName("jLabel193"); // NOI18N
        FormInput.add(jLabel193);
        jLabel193.setBounds(745, 450, 30, 23);

        jLabel194.setForeground(new java.awt.Color(0, 0, 0));
        jLabel194.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel194.setText("mm");
        jLabel194.setName("jLabel194"); // NOI18N
        FormInput.add(jLabel194);
        jLabel194.setBounds(745, 480, 30, 23);

        scrollPane12.setName("scrollPane12"); // NOI18N

        Tplaning.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tplaning.setColumns(20);
        Tplaning.setRows(5);
        Tplaning.setName("Tplaning"); // NOI18N
        Tplaning.setPreferredSize(new java.awt.Dimension(162, 200));
        Tplaning.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TplaningKeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(Tplaning);

        FormInput.add(scrollPane12);
        scrollPane12.setBounds(190, 1855, 685, 90);

        ChkGangNafas.setBackground(new java.awt.Color(255, 255, 250));
        ChkGangNafas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGangNafas.setForeground(new java.awt.Color(0, 0, 0));
        ChkGangNafas.setText("Gangguan Jalan Nafas");
        ChkGangNafas.setBorderPainted(true);
        ChkGangNafas.setBorderPaintedFlat(true);
        ChkGangNafas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGangNafas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGangNafas.setName("ChkGangNafas"); // NOI18N
        ChkGangNafas.setOpaque(false);
        ChkGangNafas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkGangNafas);
        ChkGangNafas.setBounds(100, 250, 140, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("Alasan Masuk : ");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(20, 570, 90, 23);

        Talasan_msk.setForeground(new java.awt.Color(0, 0, 0));
        Talasan_msk.setName("Talasan_msk"); // NOI18N
        Talasan_msk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Talasan_mskKeyPressed(evt);
            }
        });
        FormInput.add(Talasan_msk);
        Talasan_msk.setBounds(110, 570, 720, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("Cara Pasien Datang : ");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(20, 600, 110, 23);

        cmbCara_datang.setForeground(new java.awt.Color(0, 0, 0));
        cmbCara_datang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Sendiri", "Rujukan Bidan/BPM", "PKM", "SpOG", "RS Lain" }));
        cmbCara_datang.setName("cmbCara_datang"); // NOI18N
        cmbCara_datang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCara_datangActionPerformed(evt);
            }
        });
        FormInput.add(cmbCara_datang);
        cmbCara_datang.setBounds(133, 600, 130, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("Rujukan Bidan/BPM : ");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(264, 600, 115, 23);

        Trujukan_bidan.setForeground(new java.awt.Color(0, 0, 0));
        Trujukan_bidan.setName("Trujukan_bidan"); // NOI18N
        Trujukan_bidan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Trujukan_bidanKeyPressed(evt);
            }
        });
        FormInput.add(Trujukan_bidan);
        Trujukan_bidan.setBounds(380, 600, 450, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("PKM : ");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(264, 630, 115, 23);

        Tpkm.setForeground(new java.awt.Color(0, 0, 0));
        Tpkm.setName("Tpkm"); // NOI18N
        Tpkm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpkmKeyPressed(evt);
            }
        });
        FormInput.add(Tpkm);
        Tpkm.setBounds(380, 630, 450, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("Dokter Spesialis (SpOG) : ");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(239, 660, 140, 23);

        Tspog.setForeground(new java.awt.Color(0, 0, 0));
        Tspog.setName("Tspog"); // NOI18N
        Tspog.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TspogKeyPressed(evt);
            }
        });
        FormInput.add(Tspog);
        Tspog.setBounds(380, 660, 450, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("RS Lain : ");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(264, 690, 115, 23);

        Trs_lain.setForeground(new java.awt.Color(0, 0, 0));
        Trs_lain.setName("Trs_lain"); // NOI18N
        Trs_lain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Trs_lainKeyPressed(evt);
            }
        });
        FormInput.add(Trs_lain);
        Trs_lain.setBounds(380, 690, 450, 23);

        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setText("Dengan Gr : ");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(20, 720, 110, 23);

        Tgr.setForeground(new java.awt.Color(0, 0, 0));
        Tgr.setName("Tgr"); // NOI18N
        Tgr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgrKeyPressed(evt);
            }
        });
        FormInput.add(Tgr);
        Tgr.setBounds(133, 720, 70, 23);

        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setText("Pr : ");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(205, 720, 30, 23);

        Tpr.setForeground(new java.awt.Color(0, 0, 0));
        Tpr.setName("Tpr"); // NOI18N
        Tpr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprKeyPressed(evt);
            }
        });
        FormInput.add(Tpr);
        Tpr.setBounds(237, 720, 70, 23);

        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setText("A : ");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(310, 720, 30, 23);

        Ta.setForeground(new java.awt.Color(0, 0, 0));
        Ta.setName("Ta"); // NOI18N
        Ta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TaKeyPressed(evt);
            }
        });
        FormInput.add(Ta);
        Ta.setBounds(344, 720, 70, 23);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setText("Hamil : ");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(417, 720, 50, 23);

        Thamil.setForeground(new java.awt.Color(0, 0, 0));
        Thamil.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Thamil.setName("Thamil"); // NOI18N
        Thamil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThamilKeyPressed(evt);
            }
        });
        FormInput.add(Thamil);
        Thamil.setBounds(470, 720, 40, 23);

        jLabel131.setForeground(new java.awt.Color(0, 0, 0));
        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel131.setText("mg dengan : ");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(515, 720, 64, 23);

        Tket_dengan.setForeground(new java.awt.Color(0, 0, 0));
        Tket_dengan.setName("Tket_dengan"); // NOI18N
        FormInput.add(Tket_dengan);
        Tket_dengan.setBounds(580, 720, 250, 23);

        jLabel132.setForeground(new java.awt.Color(0, 0, 0));
        jLabel132.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel132.setText("Keluhan :");
        jLabel132.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(20, 750, 60, 23);

        TglPerutMules.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023 17:28:01" }));
        TglPerutMules.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglPerutMules.setName("TglPerutMules"); // NOI18N
        TglPerutMules.setOpaque(false);
        FormInput.add(TglPerutMules);
        TglPerutMules.setBounds(265, 770, 136, 23);

        ChkPerutMules.setBackground(new java.awt.Color(255, 255, 250));
        ChkPerutMules.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPerutMules.setForeground(new java.awt.Color(0, 0, 0));
        ChkPerutMules.setText("Pasien mengeluh perut mules / nyeri mulai tgl. ");
        ChkPerutMules.setBorderPainted(true);
        ChkPerutMules.setBorderPaintedFlat(true);
        ChkPerutMules.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPerutMules.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPerutMules.setName("ChkPerutMules"); // NOI18N
        ChkPerutMules.setOpaque(false);
        ChkPerutMules.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkPerutMules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPerutMulesActionPerformed(evt);
            }
        });
        FormInput.add(ChkPerutMules);
        ChkPerutMules.setBounds(20, 770, 242, 23);

        jLabel133.setForeground(new java.awt.Color(0, 0, 0));
        jLabel133.setText("Keluar lendir darah : ");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(407, 770, 110, 23);

        cmbKeluarLendir.setForeground(new java.awt.Color(0, 0, 0));
        cmbKeluarLendir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbKeluarLendir.setName("cmbKeluarLendir"); // NOI18N
        cmbKeluarLendir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKeluarLendirActionPerformed(evt);
            }
        });
        FormInput.add(cmbKeluarLendir);
        cmbKeluarLendir.setBounds(521, 770, 60, 23);

        jLabel134.setForeground(new java.awt.Color(0, 0, 0));
        jLabel134.setText("Mulai Tgl. ");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(583, 770, 60, 23);

        TglLendirYa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023 17:28:01" }));
        TglLendirYa.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglLendirYa.setName("TglLendirYa"); // NOI18N
        TglLendirYa.setOpaque(false);
        FormInput.add(TglLendirYa);
        TglLendirYa.setBounds(645, 770, 136, 23);

        jLabel135.setForeground(new java.awt.Color(0, 0, 0));
        jLabel135.setText("Keluar air-air : ");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(20, 800, 90, 23);

        cmbKeluarAir.setForeground(new java.awt.Color(0, 0, 0));
        cmbKeluarAir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbKeluarAir.setName("cmbKeluarAir"); // NOI18N
        cmbKeluarAir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKeluarAirActionPerformed(evt);
            }
        });
        FormInput.add(cmbKeluarAir);
        cmbKeluarAir.setBounds(113, 800, 60, 23);

        cmbKeluarAirYa.setForeground(new java.awt.Color(0, 0, 0));
        cmbKeluarAirYa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Banyak", "Sedikit-sedikit" }));
        cmbKeluarAirYa.setName("cmbKeluarAirYa"); // NOI18N
        FormInput.add(cmbKeluarAirYa);
        cmbKeluarAirYa.setBounds(180, 800, 100, 23);

        jLabel136.setForeground(new java.awt.Color(0, 0, 0));
        jLabel136.setText("Mulai Tgl. ");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(280, 800, 60, 23);

        TglAirYa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023 17:28:01" }));
        TglAirYa.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAirYa.setName("TglAirYa"); // NOI18N
        TglAirYa.setOpaque(false);
        FormInput.add(TglAirYa);
        TglAirYa.setBounds(343, 800, 136, 23);

        jLabel137.setForeground(new java.awt.Color(0, 0, 0));
        jLabel137.setText("Periksa Ketempat Bidan : ");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(477, 800, 140, 23);

        cmbPeriksaBidan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPeriksaBidan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbPeriksaBidan.setName("cmbPeriksaBidan"); // NOI18N
        cmbPeriksaBidan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPeriksaBidanActionPerformed(evt);
            }
        });
        FormInput.add(cmbPeriksaBidan);
        cmbPeriksaBidan.setBounds(620, 800, 60, 23);

        jLabel138.setForeground(new java.awt.Color(0, 0, 0));
        jLabel138.setText("Hasil / Riwayat Pemeriksaan Bidan : ");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(20, 830, 190, 23);

        scrollPane11.setName("scrollPane11"); // NOI18N

        THasilPeriksaBidan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        THasilPeriksaBidan.setColumns(20);
        THasilPeriksaBidan.setRows(5);
        THasilPeriksaBidan.setName("THasilPeriksaBidan"); // NOI18N
        THasilPeriksaBidan.setPreferredSize(new java.awt.Dimension(162, 200));
        THasilPeriksaBidan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THasilPeriksaBidanKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(THasilPeriksaBidan);

        FormInput.add(scrollPane11);
        scrollPane11.setBounds(212, 830, 619, 70);

        jLabel139.setForeground(new java.awt.Color(0, 0, 0));
        jLabel139.setText("Pasien Mengeluh Pusing : ");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(20, 908, 140, 23);

        cmbMengeluhPusing.setForeground(new java.awt.Color(0, 0, 0));
        cmbMengeluhPusing.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbMengeluhPusing.setName("cmbMengeluhPusing"); // NOI18N
        cmbMengeluhPusing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMengeluhPusingActionPerformed(evt);
            }
        });
        FormInput.add(cmbMengeluhPusing);
        cmbMengeluhPusing.setBounds(165, 908, 60, 23);

        jLabel140.setForeground(new java.awt.Color(0, 0, 0));
        jLabel140.setText("Mulai Tgl. ");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(227, 908, 60, 23);

        TglPusing.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023 17:28:01" }));
        TglPusing.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglPusing.setName("TglPusing"); // NOI18N
        TglPusing.setOpaque(false);
        FormInput.add(TglPusing);
        TglPusing.setBounds(290, 908, 136, 23);

        jLabel141.setForeground(new java.awt.Color(0, 0, 0));
        jLabel141.setText("Nyeri Ulu Hati : ");
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(428, 908, 90, 23);

        cmbNyeriUlu.setForeground(new java.awt.Color(0, 0, 0));
        cmbNyeriUlu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbNyeriUlu.setName("cmbNyeriUlu"); // NOI18N
        cmbNyeriUlu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNyeriUluActionPerformed(evt);
            }
        });
        FormInput.add(cmbNyeriUlu);
        cmbNyeriUlu.setBounds(520, 908, 60, 23);

        jLabel142.setForeground(new java.awt.Color(0, 0, 0));
        jLabel142.setText("Mulai Tgl. ");
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput.add(jLabel142);
        jLabel142.setBounds(583, 908, 60, 23);

        TglNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023 17:28:01" }));
        TglNyeri.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglNyeri.setName("TglNyeri"); // NOI18N
        TglNyeri.setOpaque(false);
        FormInput.add(TglNyeri);
        TglNyeri.setBounds(645, 908, 136, 23);

        jLabel143.setForeground(new java.awt.Color(0, 0, 0));
        jLabel143.setText("Pandangan Kabur : ");
        jLabel143.setName("jLabel143"); // NOI18N
        FormInput.add(jLabel143);
        jLabel143.setBounds(20, 938, 140, 23);

        cmbPandangan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPandangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbPandangan.setName("cmbPandangan"); // NOI18N
        cmbPandangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPandanganActionPerformed(evt);
            }
        });
        FormInput.add(cmbPandangan);
        cmbPandangan.setBounds(165, 938, 60, 23);

        jLabel144.setForeground(new java.awt.Color(0, 0, 0));
        jLabel144.setText("Mulai Tgl. ");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput.add(jLabel144);
        jLabel144.setBounds(227, 938, 60, 23);

        TglPandangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023 17:28:01" }));
        TglPandangan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglPandangan.setName("TglPandangan"); // NOI18N
        TglPandangan.setOpaque(false);
        FormInput.add(TglPandangan);
        TglPandangan.setBounds(290, 938, 136, 23);

        jLabel145.setForeground(new java.awt.Color(0, 0, 0));
        jLabel145.setText("Mual / Muntah : ");
        jLabel145.setName("jLabel145"); // NOI18N
        FormInput.add(jLabel145);
        jLabel145.setBounds(428, 938, 90, 23);

        cmbMual.setForeground(new java.awt.Color(0, 0, 0));
        cmbMual.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbMual.setName("cmbMual"); // NOI18N
        cmbMual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMualActionPerformed(evt);
            }
        });
        FormInput.add(cmbMual);
        cmbMual.setBounds(520, 938, 60, 23);

        jLabel146.setForeground(new java.awt.Color(0, 0, 0));
        jLabel146.setText("Mulai Tgl. ");
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(583, 938, 60, 23);

        TglMual.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023 17:28:01" }));
        TglMual.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglMual.setName("TglMual"); // NOI18N
        TglMual.setOpaque(false);
        FormInput.add(TglMual);
        TglMual.setBounds(645, 938, 136, 23);

        jLabel147.setForeground(new java.awt.Color(0, 0, 0));
        jLabel147.setText("Batuk / Pilek : ");
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput.add(jLabel147);
        jLabel147.setBounds(20, 968, 140, 23);

        cmbBatuk.setForeground(new java.awt.Color(0, 0, 0));
        cmbBatuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbBatuk.setName("cmbBatuk"); // NOI18N
        cmbBatuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBatukActionPerformed(evt);
            }
        });
        FormInput.add(cmbBatuk);
        cmbBatuk.setBounds(165, 968, 60, 23);

        jLabel148.setForeground(new java.awt.Color(0, 0, 0));
        jLabel148.setText("Mulai Tgl. ");
        jLabel148.setName("jLabel148"); // NOI18N
        FormInput.add(jLabel148);
        jLabel148.setBounds(227, 968, 60, 23);

        TglBatuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023 17:28:01" }));
        TglBatuk.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglBatuk.setName("TglBatuk"); // NOI18N
        TglBatuk.setOpaque(false);
        FormInput.add(TglBatuk);
        TglBatuk.setBounds(290, 968, 136, 23);

        jLabel149.setForeground(new java.awt.Color(0, 0, 0));
        jLabel149.setText("Demam : ");
        jLabel149.setName("jLabel149"); // NOI18N
        FormInput.add(jLabel149);
        jLabel149.setBounds(428, 968, 90, 23);

        cmbDemam.setForeground(new java.awt.Color(0, 0, 0));
        cmbDemam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbDemam.setName("cmbDemam"); // NOI18N
        cmbDemam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDemamActionPerformed(evt);
            }
        });
        FormInput.add(cmbDemam);
        cmbDemam.setBounds(520, 968, 60, 23);

        jLabel150.setForeground(new java.awt.Color(0, 0, 0));
        jLabel150.setText("Mulai Tgl. ");
        jLabel150.setName("jLabel150"); // NOI18N
        FormInput.add(jLabel150);
        jLabel150.setBounds(583, 968, 60, 23);

        TglDemam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023 17:28:01" }));
        TglDemam.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglDemam.setName("TglDemam"); // NOI18N
        TglDemam.setOpaque(false);
        FormInput.add(TglDemam);
        TglDemam.setBounds(645, 968, 136, 23);

        jLabel151.setForeground(new java.awt.Color(0, 0, 0));
        jLabel151.setText("Riwayat Perjalanan Jauh : ");
        jLabel151.setName("jLabel151"); // NOI18N
        FormInput.add(jLabel151);
        jLabel151.setBounds(20, 998, 140, 23);

        cmbRiwJlnJauh.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwJlnJauh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbRiwJlnJauh.setName("cmbRiwJlnJauh"); // NOI18N
        cmbRiwJlnJauh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwJlnJauhActionPerformed(evt);
            }
        });
        FormInput.add(cmbRiwJlnJauh);
        cmbRiwJlnJauh.setBounds(165, 998, 60, 23);

        TriwJlnJauh.setForeground(new java.awt.Color(0, 0, 0));
        TriwJlnJauh.setName("TriwJlnJauh"); // NOI18N
        TriwJlnJauh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwJlnJauhKeyPressed(evt);
            }
        });
        FormInput.add(TriwJlnJauh);
        TriwJlnJauh.setBounds(230, 998, 610, 23);

        jLabel152.setForeground(new java.awt.Color(0, 0, 0));
        jLabel152.setText("Os ANC dengan PKM / Bidan : ");
        jLabel152.setName("jLabel152"); // NOI18N
        FormInput.add(jLabel152);
        jLabel152.setBounds(10, 1028, 150, 23);

        cmbOs.setForeground(new java.awt.Color(0, 0, 0));
        cmbOs.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbOs.setName("cmbOs"); // NOI18N
        FormInput.add(cmbOs);
        cmbOs.setBounds(165, 1028, 60, 23);

        jLabel153.setForeground(new java.awt.Color(0, 0, 0));
        jLabel153.setText("Dengan dr. ");
        jLabel153.setName("jLabel153"); // NOI18N
        FormInput.add(jLabel153);
        jLabel153.setBounds(227, 1028, 70, 23);

        Tspog1.setForeground(new java.awt.Color(0, 0, 0));
        Tspog1.setName("Tspog1"); // NOI18N
        Tspog1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tspog1KeyPressed(evt);
            }
        });
        FormInput.add(Tspog1);
        Tspog1.setBounds(300, 1028, 230, 23);

        jLabel154.setForeground(new java.awt.Color(0, 0, 0));
        jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel154.setText("SpOG, ");
        jLabel154.setName("jLabel154"); // NOI18N
        FormInput.add(jLabel154);
        jLabel154.setBounds(535, 1028, 37, 23);

        TJspog1.setForeground(new java.awt.Color(0, 0, 0));
        TJspog1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TJspog1.setName("TJspog1"); // NOI18N
        TJspog1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TJspog1KeyPressed(evt);
            }
        });
        FormInput.add(TJspog1);
        TJspog1.setBounds(574, 1028, 34, 23);

        jLabel155.setForeground(new java.awt.Color(0, 0, 0));
        jLabel155.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel155.setText("X");
        jLabel155.setName("jLabel155"); // NOI18N
        FormInput.add(jLabel155);
        jLabel155.setBounds(615, 1028, 15, 23);

        jLabel156.setForeground(new java.awt.Color(0, 0, 0));
        jLabel156.setText("Dengan dr. ");
        jLabel156.setName("jLabel156"); // NOI18N
        FormInput.add(jLabel156);
        jLabel156.setBounds(227, 1058, 70, 23);

        Tspog2.setForeground(new java.awt.Color(0, 0, 0));
        Tspog2.setName("Tspog2"); // NOI18N
        Tspog2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tspog2KeyPressed(evt);
            }
        });
        FormInput.add(Tspog2);
        Tspog2.setBounds(300, 1058, 230, 23);

        jLabel157.setForeground(new java.awt.Color(0, 0, 0));
        jLabel157.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel157.setText("SpOG, ");
        jLabel157.setName("jLabel157"); // NOI18N
        FormInput.add(jLabel157);
        jLabel157.setBounds(535, 1058, 37, 23);

        TJspog2.setForeground(new java.awt.Color(0, 0, 0));
        TJspog2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TJspog2.setName("TJspog2"); // NOI18N
        FormInput.add(TJspog2);
        TJspog2.setBounds(574, 1058, 34, 23);

        jLabel158.setForeground(new java.awt.Color(0, 0, 0));
        jLabel158.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel158.setText("X");
        jLabel158.setName("jLabel158"); // NOI18N
        FormInput.add(jLabel158);
        jLabel158.setBounds(615, 1058, 15, 23);

        jLabel159.setForeground(new java.awt.Color(0, 0, 0));
        jLabel159.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel159.setText("Riwayat Penyakit Dahulu :");
        jLabel159.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel159.setName("jLabel159"); // NOI18N
        FormInput.add(jLabel159);
        jLabel159.setBounds(20, 1088, 160, 23);

        ChkHipertensi1.setBackground(new java.awt.Color(255, 255, 250));
        ChkHipertensi1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkHipertensi1.setForeground(new java.awt.Color(0, 0, 0));
        ChkHipertensi1.setText("Hipertensi");
        ChkHipertensi1.setBorderPainted(true);
        ChkHipertensi1.setBorderPaintedFlat(true);
        ChkHipertensi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkHipertensi1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHipertensi1.setName("ChkHipertensi1"); // NOI18N
        ChkHipertensi1.setOpaque(false);
        ChkHipertensi1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkHipertensi1);
        ChkHipertensi1.setBounds(20, 1108, 80, 23);

        ChkDM1.setBackground(new java.awt.Color(255, 255, 250));
        ChkDM1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDM1.setForeground(new java.awt.Color(0, 0, 0));
        ChkDM1.setText("Diabetes Mellitus");
        ChkDM1.setBorderPainted(true);
        ChkDM1.setBorderPaintedFlat(true);
        ChkDM1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDM1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDM1.setName("ChkDM1"); // NOI18N
        ChkDM1.setOpaque(false);
        ChkDM1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDM1);
        ChkDM1.setBounds(110, 1108, 110, 23);

        ChkJantung1.setBackground(new java.awt.Color(255, 255, 250));
        ChkJantung1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkJantung1.setForeground(new java.awt.Color(0, 0, 0));
        ChkJantung1.setText("Jantung");
        ChkJantung1.setBorderPainted(true);
        ChkJantung1.setBorderPaintedFlat(true);
        ChkJantung1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkJantung1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkJantung1.setName("ChkJantung1"); // NOI18N
        ChkJantung1.setOpaque(false);
        ChkJantung1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkJantung1);
        ChkJantung1.setBounds(226, 1108, 70, 23);

        ChkAsma1.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsma1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsma1.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsma1.setText("Asma");
        ChkAsma1.setBorderPainted(true);
        ChkAsma1.setBorderPaintedFlat(true);
        ChkAsma1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsma1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsma1.setName("ChkAsma1"); // NOI18N
        ChkAsma1.setOpaque(false);
        ChkAsma1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAsma1);
        ChkAsma1.setBounds(305, 1108, 55, 23);

        ChkLainya1.setBackground(new java.awt.Color(255, 255, 250));
        ChkLainya1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLainya1.setForeground(new java.awt.Color(0, 0, 0));
        ChkLainya1.setText("Lainnya");
        ChkLainya1.setBorderPainted(true);
        ChkLainya1.setBorderPaintedFlat(true);
        ChkLainya1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLainya1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLainya1.setName("ChkLainya1"); // NOI18N
        ChkLainya1.setOpaque(false);
        ChkLainya1.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkLainya1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkLainya1ActionPerformed(evt);
            }
        });
        FormInput.add(ChkLainya1);
        ChkLainya1.setBounds(370, 1108, 60, 23);

        TketLainya1.setForeground(new java.awt.Color(0, 0, 0));
        TketLainya1.setName("TketLainya1"); // NOI18N
        FormInput.add(TketLainya1);
        TketLainya1.setBounds(430, 1108, 410, 23);

        jLabel160.setForeground(new java.awt.Color(0, 0, 0));
        jLabel160.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel160.setText("Riwayat Penyakit Keluarga :");
        jLabel160.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel160.setName("jLabel160"); // NOI18N
        FormInput.add(jLabel160);
        jLabel160.setBounds(20, 1138, 180, 23);

        ChkHipertensi2.setBackground(new java.awt.Color(255, 255, 250));
        ChkHipertensi2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkHipertensi2.setForeground(new java.awt.Color(0, 0, 0));
        ChkHipertensi2.setText("Hipertensi");
        ChkHipertensi2.setBorderPainted(true);
        ChkHipertensi2.setBorderPaintedFlat(true);
        ChkHipertensi2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkHipertensi2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHipertensi2.setName("ChkHipertensi2"); // NOI18N
        ChkHipertensi2.setOpaque(false);
        ChkHipertensi2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkHipertensi2);
        ChkHipertensi2.setBounds(20, 1158, 80, 23);

        ChkDM2.setBackground(new java.awt.Color(255, 255, 250));
        ChkDM2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDM2.setForeground(new java.awt.Color(0, 0, 0));
        ChkDM2.setText("Diabetes Mellitus");
        ChkDM2.setBorderPainted(true);
        ChkDM2.setBorderPaintedFlat(true);
        ChkDM2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDM2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDM2.setName("ChkDM2"); // NOI18N
        ChkDM2.setOpaque(false);
        ChkDM2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDM2);
        ChkDM2.setBounds(110, 1158, 110, 23);

        ChkJantung2.setBackground(new java.awt.Color(255, 255, 250));
        ChkJantung2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkJantung2.setForeground(new java.awt.Color(0, 0, 0));
        ChkJantung2.setText("Jantung");
        ChkJantung2.setBorderPainted(true);
        ChkJantung2.setBorderPaintedFlat(true);
        ChkJantung2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkJantung2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkJantung2.setName("ChkJantung2"); // NOI18N
        ChkJantung2.setOpaque(false);
        ChkJantung2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkJantung2);
        ChkJantung2.setBounds(226, 1158, 70, 23);

        ChkAsma2.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsma2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsma2.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsma2.setText("Asma");
        ChkAsma2.setBorderPainted(true);
        ChkAsma2.setBorderPaintedFlat(true);
        ChkAsma2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsma2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsma2.setName("ChkAsma2"); // NOI18N
        ChkAsma2.setOpaque(false);
        ChkAsma2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAsma2);
        ChkAsma2.setBounds(305, 1158, 55, 23);

        ChkLainya2.setBackground(new java.awt.Color(255, 255, 250));
        ChkLainya2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLainya2.setForeground(new java.awt.Color(0, 0, 0));
        ChkLainya2.setText("Lainnya");
        ChkLainya2.setBorderPainted(true);
        ChkLainya2.setBorderPaintedFlat(true);
        ChkLainya2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLainya2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLainya2.setName("ChkLainya2"); // NOI18N
        ChkLainya2.setOpaque(false);
        ChkLainya2.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkLainya2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkLainya2ActionPerformed(evt);
            }
        });
        FormInput.add(ChkLainya2);
        ChkLainya2.setBounds(370, 1158, 60, 23);

        TketLainya2.setForeground(new java.awt.Color(0, 0, 0));
        TketLainya2.setName("TketLainya2"); // NOI18N
        FormInput.add(TketLainya2);
        TketLainya2.setBounds(430, 1158, 410, 23);

        jLabel161.setForeground(new java.awt.Color(0, 0, 0));
        jLabel161.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel161.setText("Riwayat Penyakit Ginekologi :");
        jLabel161.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel161.setName("jLabel161"); // NOI18N
        FormInput.add(jLabel161);
        jLabel161.setBounds(20, 1188, 173, 23);

        TriwGine.setForeground(new java.awt.Color(0, 0, 0));
        TriwGine.setName("TriwGine"); // NOI18N
        FormInput.add(TriwGine);
        TriwGine.setBounds(195, 1188, 645, 23);

        jLabel162.setForeground(new java.awt.Color(0, 0, 0));
        jLabel162.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel162.setText("Riwayat KB :");
        jLabel162.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel162.setName("jLabel162"); // NOI18N
        FormInput.add(jLabel162);
        jLabel162.setBounds(20, 1218, 72, 23);

        ChkPil.setBackground(new java.awt.Color(255, 255, 250));
        ChkPil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPil.setForeground(new java.awt.Color(0, 0, 0));
        ChkPil.setText("Pil, Lama : ");
        ChkPil.setBorderPainted(true);
        ChkPil.setBorderPaintedFlat(true);
        ChkPil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPil.setName("ChkPil"); // NOI18N
        ChkPil.setOpaque(false);
        ChkPil.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkPil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPilActionPerformed(evt);
            }
        });
        FormInput.add(ChkPil);
        ChkPil.setBounds(97, 1218, 71, 23);

        Tpil.setForeground(new java.awt.Color(0, 0, 0));
        Tpil.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tpil.setName("Tpil"); // NOI18N
        Tpil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpilKeyPressed(evt);
            }
        });
        FormInput.add(Tpil);
        Tpil.setBounds(170, 1218, 40, 23);

        cmbSatuanPil.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuanPil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "bulan", "tahun" }));
        cmbSatuanPil.setName("cmbSatuanPil"); // NOI18N
        FormInput.add(cmbSatuanPil);
        cmbSatuanPil.setBounds(215, 1218, 65, 23);

        ChkSuntik1.setBackground(new java.awt.Color(255, 255, 250));
        ChkSuntik1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSuntik1.setForeground(new java.awt.Color(0, 0, 0));
        ChkSuntik1.setText("Suntik 1 bulan, Lama :");
        ChkSuntik1.setBorderPainted(true);
        ChkSuntik1.setBorderPaintedFlat(true);
        ChkSuntik1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSuntik1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSuntik1.setName("ChkSuntik1"); // NOI18N
        ChkSuntik1.setOpaque(false);
        ChkSuntik1.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkSuntik1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSuntik1ActionPerformed(evt);
            }
        });
        FormInput.add(ChkSuntik1);
        ChkSuntik1.setBounds(288, 1218, 130, 23);

        Tsuntik1.setForeground(new java.awt.Color(0, 0, 0));
        Tsuntik1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tsuntik1.setName("Tsuntik1"); // NOI18N
        Tsuntik1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tsuntik1KeyPressed(evt);
            }
        });
        FormInput.add(Tsuntik1);
        Tsuntik1.setBounds(418, 1218, 40, 23);

        cmbSatuanSuntik1.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuanSuntik1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "bulan", "tahun" }));
        cmbSatuanSuntik1.setName("cmbSatuanSuntik1"); // NOI18N
        FormInput.add(cmbSatuanSuntik1);
        cmbSatuanSuntik1.setBounds(465, 1218, 65, 23);

        ChkSuntik3.setBackground(new java.awt.Color(255, 255, 250));
        ChkSuntik3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSuntik3.setForeground(new java.awt.Color(0, 0, 0));
        ChkSuntik3.setText("Suntik 3 bulan, Lama :");
        ChkSuntik3.setBorderPainted(true);
        ChkSuntik3.setBorderPaintedFlat(true);
        ChkSuntik3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSuntik3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSuntik3.setName("ChkSuntik3"); // NOI18N
        ChkSuntik3.setOpaque(false);
        ChkSuntik3.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkSuntik3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSuntik3ActionPerformed(evt);
            }
        });
        FormInput.add(ChkSuntik3);
        ChkSuntik3.setBounds(537, 1218, 130, 23);

        Tsuntik3.setForeground(new java.awt.Color(0, 0, 0));
        Tsuntik3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tsuntik3.setName("Tsuntik3"); // NOI18N
        Tsuntik3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tsuntik3KeyPressed(evt);
            }
        });
        FormInput.add(Tsuntik3);
        Tsuntik3.setBounds(668, 1218, 40, 23);

        cmbSatuanSuntik3.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuanSuntik3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "bulan", "tahun" }));
        cmbSatuanSuntik3.setName("cmbSatuanSuntik3"); // NOI18N
        FormInput.add(cmbSatuanSuntik3);
        cmbSatuanSuntik3.setBounds(715, 1218, 65, 23);

        ChkImplan.setBackground(new java.awt.Color(255, 255, 250));
        ChkImplan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkImplan.setForeground(new java.awt.Color(0, 0, 0));
        ChkImplan.setText("Implant, Lama : ");
        ChkImplan.setBorderPainted(true);
        ChkImplan.setBorderPaintedFlat(true);
        ChkImplan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkImplan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkImplan.setName("ChkImplan"); // NOI18N
        ChkImplan.setOpaque(false);
        ChkImplan.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkImplan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkImplanActionPerformed(evt);
            }
        });
        FormInput.add(ChkImplan);
        ChkImplan.setBounds(97, 1248, 97, 23);

        Timplan.setForeground(new java.awt.Color(0, 0, 0));
        Timplan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Timplan.setName("Timplan"); // NOI18N
        Timplan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TimplanKeyPressed(evt);
            }
        });
        FormInput.add(Timplan);
        Timplan.setBounds(198, 1248, 40, 23);

        cmbSatuanImplan.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuanImplan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "bulan", "tahun" }));
        cmbSatuanImplan.setName("cmbSatuanImplan"); // NOI18N
        FormInput.add(cmbSatuanImplan);
        cmbSatuanImplan.setBounds(244, 1248, 65, 23);

        TriwKBlain.setForeground(new java.awt.Color(0, 0, 0));
        TriwKBlain.setName("TriwKBlain"); // NOI18N
        TriwKBlain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwKBlainKeyPressed(evt);
            }
        });
        FormInput.add(TriwKBlain);
        TriwKBlain.setBounds(315, 1248, 525, 23);

        jLabel163.setForeground(new java.awt.Color(0, 0, 0));
        jLabel163.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel163.setText("Riwayat Kehamilan Sekarang :");
        jLabel163.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel163.setName("jLabel163"); // NOI18N
        FormInput.add(jLabel163);
        jLabel163.setBounds(20, 1278, 179, 23);

        jLabel189.setForeground(new java.awt.Color(0, 0, 0));
        jLabel189.setText("HPHT : ");
        jLabel189.setName("jLabel189"); // NOI18N
        FormInput.add(jLabel189);
        jLabel189.setBounds(20, 1298, 50, 23);

        Thpht.setForeground(new java.awt.Color(0, 0, 0));
        Thpht.setName("Thpht"); // NOI18N
        Thpht.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThphtKeyPressed(evt);
            }
        });
        FormInput.add(Thpht);
        Thpht.setBounds(73, 1298, 200, 23);

        jLabel195.setForeground(new java.awt.Color(0, 0, 0));
        jLabel195.setText("HPL : ");
        jLabel195.setName("jLabel195"); // NOI18N
        FormInput.add(jLabel195);
        jLabel195.setBounds(280, 1298, 40, 23);

        Thpl.setForeground(new java.awt.Color(0, 0, 0));
        Thpl.setName("Thpl"); // NOI18N
        Thpl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThplKeyPressed(evt);
            }
        });
        FormInput.add(Thpl);
        Thpl.setBounds(324, 1298, 200, 23);

        jLabel196.setForeground(new java.awt.Color(0, 0, 0));
        jLabel196.setText("UK : ");
        jLabel196.setName("jLabel196"); // NOI18N
        FormInput.add(jLabel196);
        jLabel196.setBounds(530, 1298, 40, 23);

        Tuk.setForeground(new java.awt.Color(0, 0, 0));
        Tuk.setName("Tuk"); // NOI18N
        Tuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TukKeyPressed(evt);
            }
        });
        FormInput.add(Tuk);
        Tuk.setBounds(575, 1298, 200, 23);

        jLabel197.setForeground(new java.awt.Color(0, 0, 0));
        jLabel197.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel197.setText("minggu");
        jLabel197.setName("jLabel197"); // NOI18N
        FormInput.add(jLabel197);
        jLabel197.setBounds(780, 1298, 40, 23);

        jLabel198.setForeground(new java.awt.Color(0, 0, 0));
        jLabel198.setText("UK (USG) : ");
        jLabel198.setName("jLabel198"); // NOI18N
        FormInput.add(jLabel198);
        jLabel198.setBounds(20, 1328, 60, 23);

        Tukusg.setForeground(new java.awt.Color(0, 0, 0));
        Tukusg.setName("Tukusg"); // NOI18N
        Tukusg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TukusgKeyPressed(evt);
            }
        });
        FormInput.add(Tukusg);
        Tukusg.setBounds(83, 1328, 200, 23);

        jLabel199.setForeground(new java.awt.Color(0, 0, 0));
        jLabel199.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel199.setText("minggu, Tgl. USG :");
        jLabel199.setName("jLabel199"); // NOI18N
        FormInput.add(jLabel199);
        jLabel199.setBounds(290, 1328, 93, 23);

        tglUSG.setEditable(false);
        tglUSG.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023" }));
        tglUSG.setDisplayFormat("dd-MM-yyyy");
        tglUSG.setName("tglUSG"); // NOI18N
        tglUSG.setOpaque(false);
        tglUSG.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(tglUSG);
        tglUSG.setBounds(387, 1328, 90, 23);

        jLabel200.setForeground(new java.awt.Color(0, 0, 0));
        jLabel200.setText("BB Sebelum Hamil : ");
        jLabel200.setName("jLabel200"); // NOI18N
        FormInput.add(jLabel200);
        jLabel200.setBounds(480, 1328, 110, 23);

        TbbBlmHamil.setForeground(new java.awt.Color(0, 0, 0));
        TbbBlmHamil.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TbbBlmHamil.setName("TbbBlmHamil"); // NOI18N
        TbbBlmHamil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbBlmHamilKeyPressed(evt);
            }
        });
        FormInput.add(TbbBlmHamil);
        TbbBlmHamil.setBounds(594, 1328, 50, 23);

        jLabel201.setForeground(new java.awt.Color(0, 0, 0));
        jLabel201.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel201.setText("Kg.  BB Setelah Hamil : ");
        jLabel201.setName("jLabel201"); // NOI18N
        FormInput.add(jLabel201);
        jLabel201.setBounds(650, 1328, 113, 23);

        TbbStlhHamil.setForeground(new java.awt.Color(0, 0, 0));
        TbbStlhHamil.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TbbStlhHamil.setName("TbbStlhHamil"); // NOI18N
        TbbStlhHamil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbStlhHamilKeyPressed(evt);
            }
        });
        FormInput.add(TbbStlhHamil);
        TbbStlhHamil.setBounds(763, 1328, 50, 23);

        jLabel202.setForeground(new java.awt.Color(0, 0, 0));
        jLabel202.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel202.setText("Kg.");
        jLabel202.setName("jLabel202"); // NOI18N
        FormInput.add(jLabel202);
        jLabel202.setBounds(818, 1328, 25, 23);

        jLabel203.setForeground(new java.awt.Color(0, 0, 0));
        jLabel203.setText("TBI : ");
        jLabel203.setName("jLabel203"); // NOI18N
        FormInput.add(jLabel203);
        jLabel203.setBounds(20, 1358, 50, 23);

        Ttbi.setForeground(new java.awt.Color(0, 0, 0));
        Ttbi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ttbi.setName("Ttbi"); // NOI18N
        Ttbi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbiKeyPressed(evt);
            }
        });
        FormInput.add(Ttbi);
        Ttbi.setBounds(73, 1358, 50, 23);

        jLabel204.setForeground(new java.awt.Color(0, 0, 0));
        jLabel204.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel204.setText("Cm.  BMI : ");
        jLabel204.setName("jLabel204"); // NOI18N
        FormInput.add(jLabel204);
        jLabel204.setBounds(130, 1358, 55, 23);

        Tbmi.setForeground(new java.awt.Color(0, 0, 0));
        Tbmi.setName("Tbmi"); // NOI18N
        Tbmi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbmiKeyPressed(evt);
            }
        });
        FormInput.add(Tbmi);
        Tbmi.setBounds(186, 1358, 180, 23);

        jLabel205.setForeground(new java.awt.Color(0, 0, 0));
        jLabel205.setText("LILA : ");
        jLabel205.setName("jLabel205"); // NOI18N
        FormInput.add(jLabel205);
        jLabel205.setBounds(370, 1358, 50, 23);

        Tlila.setForeground(new java.awt.Color(0, 0, 0));
        Tlila.setName("Tlila"); // NOI18N
        FormInput.add(Tlila);
        Tlila.setBounds(424, 1358, 180, 23);

        jLabel206.setForeground(new java.awt.Color(0, 0, 0));
        jLabel206.setText("Keluhan : ");
        jLabel206.setName("jLabel206"); // NOI18N
        FormInput.add(jLabel206);
        jLabel206.setBounds(20, 1388, 72, 23);

        ChkDismen.setBackground(new java.awt.Color(255, 255, 250));
        ChkDismen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDismen.setForeground(new java.awt.Color(0, 0, 0));
        ChkDismen.setText("Dismenorhoe");
        ChkDismen.setBorderPainted(true);
        ChkDismen.setBorderPaintedFlat(true);
        ChkDismen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDismen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDismen.setName("ChkDismen"); // NOI18N
        ChkDismen.setOpaque(false);
        ChkDismen.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDismen);
        ChkDismen.setBounds(97, 1388, 85, 23);

        ChkSpoting.setBackground(new java.awt.Color(255, 255, 250));
        ChkSpoting.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSpoting.setForeground(new java.awt.Color(0, 0, 0));
        ChkSpoting.setText("Spotting");
        ChkSpoting.setBorderPainted(true);
        ChkSpoting.setBorderPaintedFlat(true);
        ChkSpoting.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSpoting.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSpoting.setName("ChkSpoting"); // NOI18N
        ChkSpoting.setOpaque(false);
        ChkSpoting.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkSpoting);
        ChkSpoting.setBounds(190, 1388, 67, 23);

        ChkMenor.setBackground(new java.awt.Color(255, 255, 250));
        ChkMenor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMenor.setForeground(new java.awt.Color(0, 0, 0));
        ChkMenor.setText("Menorrhagia");
        ChkMenor.setBorderPainted(true);
        ChkMenor.setBorderPaintedFlat(true);
        ChkMenor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMenor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMenor.setName("ChkMenor"); // NOI18N
        ChkMenor.setOpaque(false);
        ChkMenor.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkMenor);
        ChkMenor.setBounds(265, 1388, 90, 23);

        ChkMetro.setBackground(new java.awt.Color(255, 255, 250));
        ChkMetro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMetro.setForeground(new java.awt.Color(0, 0, 0));
        ChkMetro.setText("Metrohagia");
        ChkMetro.setBorderPainted(true);
        ChkMetro.setBorderPaintedFlat(true);
        ChkMetro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMetro.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMetro.setName("ChkMetro"); // NOI18N
        ChkMetro.setOpaque(false);
        ChkMetro.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkMetro);
        ChkMetro.setBounds(362, 1388, 80, 23);

        TkeluhanLain.setForeground(new java.awt.Color(0, 0, 0));
        TkeluhanLain.setName("TkeluhanLain"); // NOI18N
        TkeluhanLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeluhanLainKeyPressed(evt);
            }
        });
        FormInput.add(TkeluhanLain);
        TkeluhanLain.setBounds(450, 1388, 390, 23);

        jLabel207.setForeground(new java.awt.Color(0, 0, 0));
        jLabel207.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel207.setText("Pemeriksaan OBSTETRI : ");
        jLabel207.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel207.setName("jLabel207"); // NOI18N
        FormInput.add(jLabel207);
        jLabel207.setBounds(20, 1418, 150, 23);

        jLabel208.setForeground(new java.awt.Color(0, 0, 0));
        jLabel208.setText("Leopold 1 : ");
        jLabel208.setName("jLabel208"); // NOI18N
        FormInput.add(jLabel208);
        jLabel208.setBounds(170, 1418, 60, 23);

        Tleopold1.setForeground(new java.awt.Color(0, 0, 0));
        Tleopold1.setName("Tleopold1"); // NOI18N
        Tleopold1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tleopold1KeyPressed(evt);
            }
        });
        FormInput.add(Tleopold1);
        Tleopold1.setBounds(233, 1418, 607, 23);

        jLabel209.setForeground(new java.awt.Color(0, 0, 0));
        jLabel209.setText("Leopold 2 : ");
        jLabel209.setName("jLabel209"); // NOI18N
        FormInput.add(jLabel209);
        jLabel209.setBounds(170, 1448, 60, 23);

        Tleopold2.setForeground(new java.awt.Color(0, 0, 0));
        Tleopold2.setName("Tleopold2"); // NOI18N
        Tleopold2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tleopold2KeyPressed(evt);
            }
        });
        FormInput.add(Tleopold2);
        Tleopold2.setBounds(233, 1448, 607, 23);

        jLabel210.setForeground(new java.awt.Color(0, 0, 0));
        jLabel210.setText("Leopold 3 : ");
        jLabel210.setName("jLabel210"); // NOI18N
        FormInput.add(jLabel210);
        jLabel210.setBounds(170, 1478, 60, 23);

        Tleopold3.setForeground(new java.awt.Color(0, 0, 0));
        Tleopold3.setName("Tleopold3"); // NOI18N
        Tleopold3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tleopold3KeyPressed(evt);
            }
        });
        FormInput.add(Tleopold3);
        Tleopold3.setBounds(233, 1478, 607, 23);

        jLabel211.setForeground(new java.awt.Color(0, 0, 0));
        jLabel211.setText("Leopold 4 : ");
        jLabel211.setName("jLabel211"); // NOI18N
        FormInput.add(jLabel211);
        jLabel211.setBounds(170, 1508, 60, 23);

        Tleopold4.setForeground(new java.awt.Color(0, 0, 0));
        Tleopold4.setName("Tleopold4"); // NOI18N
        Tleopold4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tleopold4KeyPressed(evt);
            }
        });
        FormInput.add(Tleopold4);
        Tleopold4.setBounds(233, 1508, 607, 23);

        ChkNyeriTekan.setBackground(new java.awt.Color(255, 255, 250));
        ChkNyeriTekan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNyeriTekan.setForeground(new java.awt.Color(0, 0, 0));
        ChkNyeriTekan.setText("Nyeri Tekan");
        ChkNyeriTekan.setBorderPainted(true);
        ChkNyeriTekan.setBorderPaintedFlat(true);
        ChkNyeriTekan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNyeriTekan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNyeriTekan.setName("ChkNyeriTekan"); // NOI18N
        ChkNyeriTekan.setOpaque(false);
        ChkNyeriTekan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkNyeriTekan);
        ChkNyeriTekan.setBounds(20, 1448, 80, 23);

        ChkBandle.setBackground(new java.awt.Color(255, 255, 250));
        ChkBandle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBandle.setForeground(new java.awt.Color(0, 0, 0));
        ChkBandle.setText("Bandle Ring");
        ChkBandle.setBorderPainted(true);
        ChkBandle.setBorderPaintedFlat(true);
        ChkBandle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBandle.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBandle.setName("ChkBandle"); // NOI18N
        ChkBandle.setOpaque(false);
        ChkBandle.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkBandle);
        ChkBandle.setBounds(20, 1478, 80, 23);

        jLabel212.setForeground(new java.awt.Color(0, 0, 0));
        jLabel212.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel212.setText("SURVEI SEKUNDER");
        jLabel212.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel212.setName("jLabel212"); // NOI18N
        FormInput.add(jLabel212);
        jLabel212.setBounds(10, 1538, 120, 23);

        jLabel213.setForeground(new java.awt.Color(0, 0, 0));
        jLabel213.setText("TFU : ");
        jLabel213.setName("jLabel213"); // NOI18N
        FormInput.add(jLabel213);
        jLabel213.setBounds(20, 1558, 50, 23);

        Ttfu.setForeground(new java.awt.Color(0, 0, 0));
        Ttfu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ttfu.setName("Ttfu"); // NOI18N
        Ttfu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtfuKeyPressed(evt);
            }
        });
        FormInput.add(Ttfu);
        Ttfu.setBounds(73, 1558, 50, 23);

        jLabel214.setForeground(new java.awt.Color(0, 0, 0));
        jLabel214.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel214.setText("Cm.   Taksiran Berat Janin : ");
        jLabel214.setName("jLabel214"); // NOI18N
        FormInput.add(jLabel214);
        jLabel214.setBounds(130, 1558, 137, 23);

        TbbJanin.setForeground(new java.awt.Color(0, 0, 0));
        TbbJanin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TbbJanin.setName("TbbJanin"); // NOI18N
        FormInput.add(TbbJanin);
        TbbJanin.setBounds(267, 1558, 50, 23);

        jLabel215.setForeground(new java.awt.Color(0, 0, 0));
        jLabel215.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel215.setText("gram.");
        jLabel215.setName("jLabel215"); // NOI18N
        FormInput.add(jLabel215);
        jLabel215.setBounds(323, 1558, 30, 23);

        ChkHis.setBackground(new java.awt.Color(255, 255, 250));
        ChkHis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkHis.setForeground(new java.awt.Color(0, 0, 0));
        ChkHis.setText("His / Kontraksi : ");
        ChkHis.setBorderPainted(true);
        ChkHis.setBorderPaintedFlat(true);
        ChkHis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkHis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHis.setName("ChkHis"); // NOI18N
        ChkHis.setOpaque(false);
        ChkHis.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkHis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkHisActionPerformed(evt);
            }
        });
        FormInput.add(ChkHis);
        ChkHis.setBounds(362, 1558, 98, 23);

        ThisKontraksi.setForeground(new java.awt.Color(0, 0, 0));
        ThisKontraksi.setName("ThisKontraksi"); // NOI18N
        FormInput.add(ThisKontraksi);
        ThisKontraksi.setBounds(460, 1558, 70, 23);

        jLabel216.setForeground(new java.awt.Color(0, 0, 0));
        jLabel216.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel216.setText("x/10 mnt");
        jLabel216.setName("jLabel216"); // NOI18N
        FormInput.add(jLabel216);
        jLabel216.setBounds(535, 1558, 45, 23);

        ChkTeratur.setBackground(new java.awt.Color(255, 255, 250));
        ChkTeratur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTeratur.setForeground(new java.awt.Color(0, 0, 0));
        ChkTeratur.setText("Teratur");
        ChkTeratur.setBorderPainted(true);
        ChkTeratur.setBorderPaintedFlat(true);
        ChkTeratur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTeratur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTeratur.setName("ChkTeratur"); // NOI18N
        ChkTeratur.setOpaque(false);
        ChkTeratur.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTeratur);
        ChkTeratur.setBounds(590, 1558, 60, 23);

        ChkTdkTeratur.setBackground(new java.awt.Color(255, 255, 250));
        ChkTdkTeratur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTdkTeratur.setForeground(new java.awt.Color(0, 0, 0));
        ChkTdkTeratur.setText("Tidak Teratur");
        ChkTdkTeratur.setBorderPainted(true);
        ChkTdkTeratur.setBorderPaintedFlat(true);
        ChkTdkTeratur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTdkTeratur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTdkTeratur.setName("ChkTdkTeratur"); // NOI18N
        ChkTdkTeratur.setOpaque(false);
        ChkTdkTeratur.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTdkTeratur);
        ChkTdkTeratur.setBounds(655, 1558, 85, 23);

        ChkTrsMenerus.setBackground(new java.awt.Color(255, 255, 250));
        ChkTrsMenerus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTrsMenerus.setForeground(new java.awt.Color(0, 0, 0));
        ChkTrsMenerus.setText("Terus Menerus");
        ChkTrsMenerus.setBorderPainted(true);
        ChkTrsMenerus.setBorderPaintedFlat(true);
        ChkTrsMenerus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTrsMenerus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTrsMenerus.setName("ChkTrsMenerus"); // NOI18N
        ChkTrsMenerus.setOpaque(false);
        ChkTrsMenerus.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTrsMenerus);
        ChkTrsMenerus.setBounds(747, 1558, 93, 23);

        jLabel217.setForeground(new java.awt.Color(0, 0, 0));
        jLabel217.setText("Durasi : ");
        jLabel217.setName("jLabel217"); // NOI18N
        FormInput.add(jLabel217);
        jLabel217.setBounds(20, 1588, 50, 23);

        Tdurasi.setForeground(new java.awt.Color(0, 0, 0));
        Tdurasi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdurasi.setName("Tdurasi"); // NOI18N
        FormInput.add(Tdurasi);
        Tdurasi.setBounds(73, 1588, 50, 23);

        jLabel218.setForeground(new java.awt.Color(0, 0, 0));
        jLabel218.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel218.setText("detik");
        jLabel218.setName("jLabel218"); // NOI18N
        FormInput.add(jLabel218);
        jLabel218.setBounds(130, 1588, 30, 23);

        ChkKuat.setBackground(new java.awt.Color(255, 255, 250));
        ChkKuat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKuat.setForeground(new java.awt.Color(0, 0, 0));
        ChkKuat.setText("Kuat");
        ChkKuat.setBorderPainted(true);
        ChkKuat.setBorderPaintedFlat(true);
        ChkKuat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKuat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKuat.setName("ChkKuat"); // NOI18N
        ChkKuat.setOpaque(false);
        ChkKuat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKuat);
        ChkKuat.setBounds(164, 1588, 45, 23);

        ChkSedang.setBackground(new java.awt.Color(255, 255, 250));
        ChkSedang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSedang.setForeground(new java.awt.Color(0, 0, 0));
        ChkSedang.setText("Sedang");
        ChkSedang.setBorderPainted(true);
        ChkSedang.setBorderPaintedFlat(true);
        ChkSedang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSedang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSedang.setName("ChkSedang"); // NOI18N
        ChkSedang.setOpaque(false);
        ChkSedang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkSedang);
        ChkSedang.setBounds(217, 1588, 60, 23);

        ChkLemah.setBackground(new java.awt.Color(255, 255, 250));
        ChkLemah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLemah.setForeground(new java.awt.Color(0, 0, 0));
        ChkLemah.setText("Lemah");
        ChkLemah.setBorderPainted(true);
        ChkLemah.setBorderPaintedFlat(true);
        ChkLemah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLemah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLemah.setName("ChkLemah"); // NOI18N
        ChkLemah.setOpaque(false);
        ChkLemah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkLemah);
        ChkLemah.setBounds(283, 1588, 52, 23);

        jLabel219.setForeground(new java.awt.Color(0, 0, 0));
        jLabel219.setText("Auskultasi : DJJ ");
        jLabel219.setName("jLabel219"); // NOI18N
        FormInput.add(jLabel219);
        jLabel219.setBounds(340, 1588, 90, 23);

        Tauskultasi.setForeground(new java.awt.Color(0, 0, 0));
        Tauskultasi.setName("Tauskultasi"); // NOI18N
        FormInput.add(Tauskultasi);
        Tauskultasi.setBounds(435, 1588, 70, 23);

        jLabel220.setForeground(new java.awt.Color(0, 0, 0));
        jLabel220.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel220.setText("x/mnt");
        jLabel220.setName("jLabel220"); // NOI18N
        FormInput.add(jLabel220);
        jLabel220.setBounds(510, 1588, 34, 23);

        jLabel221.setForeground(new java.awt.Color(0, 0, 0));
        jLabel221.setText("Pemeriksaan Genitalia : ");
        jLabel221.setName("jLabel221"); // NOI18N
        FormInput.add(jLabel221);
        jLabel221.setBounds(20, 1618, 130, 23);

        ChkBersih.setBackground(new java.awt.Color(255, 255, 250));
        ChkBersih.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBersih.setForeground(new java.awt.Color(0, 0, 0));
        ChkBersih.setText("Bersih");
        ChkBersih.setBorderPainted(true);
        ChkBersih.setBorderPaintedFlat(true);
        ChkBersih.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBersih.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBersih.setName("ChkBersih"); // NOI18N
        ChkBersih.setOpaque(false);
        ChkBersih.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkBersih);
        ChkBersih.setBounds(152, 1618, 50, 23);

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
        ChkOedema.setBounds(208, 1618, 60, 23);

        ChkRuptur.setBackground(new java.awt.Color(255, 255, 250));
        ChkRuptur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkRuptur.setForeground(new java.awt.Color(0, 0, 0));
        ChkRuptur.setText("Ruptur");
        ChkRuptur.setBorderPainted(true);
        ChkRuptur.setBorderPaintedFlat(true);
        ChkRuptur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkRuptur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkRuptur.setName("ChkRuptur"); // NOI18N
        ChkRuptur.setOpaque(false);
        ChkRuptur.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkRuptur);
        ChkRuptur.setBounds(277, 1618, 54, 23);

        ChkCandiloma.setBackground(new java.awt.Color(255, 255, 250));
        ChkCandiloma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCandiloma.setForeground(new java.awt.Color(0, 0, 0));
        ChkCandiloma.setText("Candiloma");
        ChkCandiloma.setBorderPainted(true);
        ChkCandiloma.setBorderPaintedFlat(true);
        ChkCandiloma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCandiloma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCandiloma.setName("ChkCandiloma"); // NOI18N
        ChkCandiloma.setOpaque(false);
        ChkCandiloma.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkCandiloma);
        ChkCandiloma.setBounds(338, 1618, 72, 23);

        Tgenitalia_lain.setForeground(new java.awt.Color(0, 0, 0));
        Tgenitalia_lain.setName("Tgenitalia_lain"); // NOI18N
        Tgenitalia_lain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgenitalia_lainKeyPressed(evt);
            }
        });
        FormInput.add(Tgenitalia_lain);
        Tgenitalia_lain.setBounds(420, 1618, 420, 23);

        jLabel222.setForeground(new java.awt.Color(0, 0, 0));
        jLabel222.setText("Inspeksi : ");
        jLabel222.setName("jLabel222"); // NOI18N
        FormInput.add(jLabel222);
        jLabel222.setBounds(20, 1648, 60, 23);

        Tinspeksi.setForeground(new java.awt.Color(0, 0, 0));
        Tinspeksi.setName("Tinspeksi"); // NOI18N
        Tinspeksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinspeksiKeyPressed(evt);
            }
        });
        FormInput.add(Tinspeksi);
        Tinspeksi.setBounds(85, 1648, 755, 23);

        jLabel223.setForeground(new java.awt.Color(0, 0, 0));
        jLabel223.setText("Konsistensi : ");
        jLabel223.setName("jLabel223"); // NOI18N
        FormInput.add(jLabel223);
        jLabel223.setBounds(20, 1678, 70, 23);

        jLabel224.setForeground(new java.awt.Color(0, 0, 0));
        jLabel224.setText("Periksa Dalam (Obstetri) : ");
        jLabel224.setName("jLabel224"); // NOI18N
        FormInput.add(jLabel224);
        jLabel224.setBounds(220, 1678, 150, 23);

        Tperiksa_dlm.setForeground(new java.awt.Color(0, 0, 0));
        Tperiksa_dlm.setName("Tperiksa_dlm"); // NOI18N
        Tperiksa_dlm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tperiksa_dlmKeyPressed(evt);
            }
        });
        FormInput.add(Tperiksa_dlm);
        Tperiksa_dlm.setBounds(370, 1678, 470, 23);

        jLabel225.setForeground(new java.awt.Color(0, 0, 0));
        jLabel225.setText("Inspekulum : ");
        jLabel225.setName("jLabel225"); // NOI18N
        FormInput.add(jLabel225);
        jLabel225.setBounds(20, 1708, 70, 23);

        Tinspekulum.setForeground(new java.awt.Color(0, 0, 0));
        Tinspekulum.setName("Tinspekulum"); // NOI18N
        Tinspekulum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinspekulumKeyPressed(evt);
            }
        });
        FormInput.add(Tinspekulum);
        Tinspekulum.setBounds(90, 1708, 750, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("(Klik kanan pada halaman ini untuk melihat hasil pemeriksaan penunjang)");
        jLabel47.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(120, 1538, 360, 23);

        cmbKonsistensi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKonsistensi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Encer", "Kental", "Bergumpal Gumpal" }));
        cmbKonsistensi.setName("cmbKonsistensi"); // NOI18N
        FormInput.add(cmbKonsistensi);
        cmbKonsistensi.setBounds(90, 1678, 124, 23);

        cmbNadi2.setForeground(new java.awt.Color(0, 0, 0));
        cmbNadi2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Kuat", "Lemah" }));
        cmbNadi2.setName("cmbNadi2"); // NOI18N
        FormInput.add(cmbNadi2);
        cmbNadi2.setBounds(800, 250, 70, 23);

        cmbAkral2.setForeground(new java.awt.Color(0, 0, 0));
        cmbAkral2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Kering", "Basah" }));
        cmbAkral2.setName("cmbAkral2"); // NOI18N
        FormInput.add(cmbAkral2);
        cmbAkral2.setBounds(790, 310, 70, 23);

        BtnHasil.setForeground(new java.awt.Color(0, 0, 0));
        BtnHasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnHasil.setMnemonic('2');
        BtnHasil.setText("Template");
        BtnHasil.setToolTipText("Alt+2");
        BtnHasil.setName("BtnHasil"); // NOI18N
        BtnHasil.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHasilActionPerformed(evt);
            }
        });
        FormInput.add(BtnHasil);
        BtnHasil.setBounds(840, 830, 100, 23);

        BtnDiagnosis.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagnosis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagnosis.setMnemonic('2');
        BtnDiagnosis.setText("Template");
        BtnDiagnosis.setToolTipText("Alt+2");
        BtnDiagnosis.setName("BtnDiagnosis"); // NOI18N
        BtnDiagnosis.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDiagnosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagnosisActionPerformed(evt);
            }
        });
        FormInput.add(BtnDiagnosis);
        BtnDiagnosis.setBounds(713, 1788, 100, 23);

        BtnPlaning.setForeground(new java.awt.Color(0, 0, 0));
        BtnPlaning.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPlaning.setMnemonic('2');
        BtnPlaning.setText("Template");
        BtnPlaning.setToolTipText("Alt+2");
        BtnPlaning.setName("BtnPlaning"); // NOI18N
        BtnPlaning.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPlaning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPlaningActionPerformed(evt);
            }
        });
        FormInput.add(BtnPlaning);
        BtnPlaning.setBounds(80, 1885, 100, 23);

        BtnAlasan.setForeground(new java.awt.Color(0, 0, 0));
        BtnAlasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAlasan.setMnemonic('2');
        BtnAlasan.setText("Template");
        BtnAlasan.setToolTipText("Alt+2");
        BtnAlasan.setName("BtnAlasan"); // NOI18N
        BtnAlasan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAlasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlasanActionPerformed(evt);
            }
        });
        FormInput.add(BtnAlasan);
        BtnAlasan.setBounds(840, 2293, 100, 23);

        ChkJamKeluar.setBackground(new java.awt.Color(255, 255, 250));
        ChkJamKeluar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkJamKeluar.setForeground(new java.awt.Color(0, 0, 0));
        ChkJamKeluar.setText("Jam Keluar :");
        ChkJamKeluar.setBorderPainted(true);
        ChkJamKeluar.setBorderPaintedFlat(true);
        ChkJamKeluar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkJamKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkJamKeluar.setName("ChkJamKeluar"); // NOI18N
        ChkJamKeluar.setOpaque(false);
        ChkJamKeluar.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkJamKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJamKeluarActionPerformed(evt);
            }
        });
        FormInput.add(ChkJamKeluar);
        ChkJamKeluar.setBounds(10, 2143, 125, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(140, 2143, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(190, 2143, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(240, 2143, 45, 23);

        TglKeluar.setEditable(false);
        TglKeluar.setDisplayFormat("dd-MM-yyyy");
        TglKeluar.setName("TglKeluar"); // NOI18N
        FormInput.add(TglKeluar);
        TglKeluar.setBounds(140, 2113, 100, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Assesmen", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

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
        jLabel19.setText("Tgl. Asuhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023" }));
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
        } else if (kddpjp.getText().equals("")) {
            Valid.textKosong(kddpjp, "Mengetahui DPJP");
            BtnDpjp.requestFocus();
        } else {            
            simpan();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        Ttd.setText("");
        Trr.setText("");
        Ttemp.setText("");
        emptTeks();
        ChkMeninggal.setSelected(false);
        cmbJam.setSelectedIndex(0);
        cmbMnt.setSelectedIndex(0);
        cmbDtk.setSelectedIndex(0);

        cmbJam.setEnabled(false);
        cmbMnt.setEnabled(false);
        cmbDtk.setEnabled(false);
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
            if (akses.getkode().equals("Admin Utama")) {
                hapus();
            } else {
                if (kddpjp.getText().equals(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 193).toString())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh DPJP yang bersangkutan..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
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
        } else if (kddpjp.getText().equals("")) {
            Valid.textKosong(kddpjp, "Mengetahui DPJP");
            BtnDpjp.requestFocus();
        } else {
            if (tbAsesmen.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
                    if (kddpjp.getText().equals(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 193).toString())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh DPJP yang bersangkutan..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
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
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());            
            param.put("logo", Sequel.cariGambar("select logo from setting"));            
            
            if (ChkPerutMules.isSelected() == true) {
                param.put("perut_mules", "Pasien mengeluh perut mules/nyeri mulai tgl. "
                        + Sequel.cariIsi("select concat(date_format(tgl_mengeluh_perut,'%d-%m-%Y'),', jam : ',time_format(tgl_mengeluh_perut,'%H:%i')) "
                                + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRw.getText() + "'"));
            } else {
                param.put("perut_mules", "Pasien mengeluh perut mules/nyeri mulai tgl. -, jam : -");
            }
            
            if (cmbKeluarLendir.getSelectedIndex() == 1) {
                param.put("keluar_lendir", "Keluar lendir darah : " + cmbKeluarLendir.getSelectedItem().toString() + ", mulai tgl. "
                        + Sequel.cariIsi("select concat(date_format(tgl_lendir_ya,'%d-%m-%Y'),', jam : ',time_format(tgl_lendir_ya,'%H:%i')) "
                                + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRw.getText() + "'"));
            } else {
                param.put("keluar_lendir", "Keluar lendir darah : " + cmbKeluarLendir.getSelectedItem().toString() + ", mulai tgl. -, jam : -");
            }
            
            if (cmbKeluarAir.getSelectedIndex() == 1) {
                param.put("keluar_air", "Keluar air-air : " + cmbKeluarAir.getSelectedItem().toString() + ", " + cmbKeluarAirYa.getSelectedItem().toString() + " mulai tgl. "
                        + Sequel.cariIsi("select concat(date_format(tgl_air_ya,'%d-%m-%Y'),', jam : ',time_format(tgl_air_ya,'%H:%i')) "
                                + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRw.getText() + "'"));
            } else {
                param.put("keluar_air", "Keluar air-air : " + cmbKeluarAir.getSelectedItem().toString() + ", mulai tgl. -, jam : -");
            }
            
            if (cmbMengeluhPusing.getSelectedIndex() == 1) {
                param.put("mengeluh_pusing", "Pasien mengeluh pusing : Ya, mulai tgl. "
                        + Sequel.cariIsi("select concat(date_format(tgl_pusing,'%d-%m-%Y'),', jam : ',time_format(tgl_pusing,'%H:%i')) "
                                + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRw.getText() + "'"));
            } else {
                param.put("mengeluh_pusing", "Pasien mengeluh pusing : " + cmbMengeluhPusing.getSelectedItem().toString() + ", mulai tgl. -, jam : -");
            }
            
            if (cmbNyeriUlu.getSelectedIndex() == 1) {
                param.put("nyeri_ulu", "Nyeri Ulu Hati : Ya, mulai tgl. "
                        + Sequel.cariIsi("select concat(date_format(tgl_nyeri,'%d-%m-%Y'),', jam : ',time_format(tgl_nyeri,'%H:%i')) "
                                + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRw.getText() + "'"));
            } else {
                param.put("nyeri_ulu", "Nyeri Ulu Hati : " + cmbNyeriUlu.getSelectedItem().toString() + ", mulai tgl. -, jam : -");
            }
            
            if (cmbPandangan.getSelectedIndex() == 1) {
                param.put("pandangan", "Pandangan kabur : Ya, mulai tgl. "
                        + Sequel.cariIsi("select concat(date_format(tgl_pandangan,'%d-%m-%Y'),', jam : ',time_format(tgl_pandangan,'%H:%i')) "
                                + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRw.getText() + "'"));
            } else {
                param.put("pandangan", "Pandangan kabur : " + cmbPandangan.getSelectedItem().toString() + ", mulai tgl. -, jam : -");
            }
            
            if (cmbMual.getSelectedIndex() == 1) {
                param.put("mual", "Mual/Muntah : Ya, mulai tgl. "
                        + Sequel.cariIsi("select concat(date_format(tgl_mual_muntah,'%d-%m-%Y'),', jam : ',time_format(tgl_mual_muntah,'%H:%i')) "
                                + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRw.getText() + "'"));
            } else {
                param.put("mual", "Mual/Muntah : " + cmbMual.getSelectedItem().toString() + ", mulai tgl. -, jam : -");
            }
            
            if (cmbBatuk.getSelectedIndex() == 1) {
                param.put("batuk", "Batuk/Pilek : Ya, mulai tgl. "
                        + Sequel.cariIsi("select concat(date_format(tgl_batuk_pilek,'%d-%m-%Y'),', jam : ',time_format(tgl_batuk_pilek,'%H:%i')) "
                                + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRw.getText() + "'"));
            } else {
                param.put("batuk", "Batuk/Pilek : " + cmbBatuk.getSelectedItem().toString() + ", mulai tgl. -, jam : -");
            }
            
            if (cmbDemam.getSelectedIndex() == 1) {
                param.put("demam", "Demam : Ya, mulai tgl. "
                        + Sequel.cariIsi("select concat(date_format(tgl_demam,'%d-%m-%Y'),', jam : ',time_format(tgl_demam,'%H:%i')) "
                                + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRw.getText() + "'"));
            } else {
                param.put("demam", "Demam : " + cmbDemam.getSelectedItem().toString() + ", mulai tgl. -, jam : -");
            }
            
            if (ChkPil.isSelected() == true) {
                param.put("cek_pil", "V");
                param.put("pil", "Pil, lama : " + Tpil.getText() + " " + cmbSatuanPil.getSelectedItem().toString());
            } else {
                param.put("cek_pil", "");
                param.put("pil", "Pil, lama : -");
            }
            
            if (ChkSuntik1.isSelected() == true) {
                param.put("cek_suntik1", "V");
                param.put("suntik1", "Suntik 1 bulan, lama : " + Tsuntik1.getText() + " " + cmbSatuanSuntik1.getSelectedItem().toString());
            } else {
                param.put("cek_suntik1", "");
                param.put("suntik1", "Suntik 1 bulan, lama : -");
            }
            
            if (ChkSuntik3.isSelected() == true) {
                param.put("cek_suntik3", "V");
                param.put("suntik3", "Suntik 3 bulan, lama : " + Tsuntik3.getText() + " " + cmbSatuanSuntik3.getSelectedItem().toString());
            } else {
                param.put("cek_suntik3", "");
                param.put("suntik3", "Suntik 3 bulan, lama : -");
            }
            
            if (ChkImplan.isSelected() == true) {
                param.put("cek_implan", "V");
                param.put("implan", "Implant, lama : " + Timplan.getText() + " " + cmbSatuanImplan.getSelectedItem().toString());
            } else {
                param.put("cek_implan", "");
                param.put("implan", "Implant, lama : -");
            }
            
            if (!Tukusg.getText().equals("")) {
                param.put("uk_usg", "UK (USG) : " + Tukusg.getText() + " minggu, tgl. USG : " + tglUSG.getSelectedItem().toString());
            } else {
                param.put("uk_usg", "UK (USG) : - minggu, tgl. USG : -");
            }
            
            if (cmbCara_datang.getSelectedIndex() == 1) {
                param.put("cara_datang", cmbCara_datang.getSelectedItem().toString());
            } else if (cmbCara_datang.getSelectedIndex() == 2) {
                param.put("cara_datang", cmbCara_datang.getSelectedItem().toString() + " : " + Trujukan_bidan.getText());
            } else if (cmbCara_datang.getSelectedIndex() == 3) {
                param.put("cara_datang", cmbCara_datang.getSelectedItem().toString() + " : " + Tpkm.getText());
            } else if (cmbCara_datang.getSelectedIndex() == 4) {
                param.put("cara_datang", Tspog.getText() + " SPOG");
            } else if (cmbCara_datang.getSelectedIndex() == 5) {
                param.put("cara_datang", cmbCara_datang.getSelectedItem().toString() + " : " + Trs_lain.getText());
            } else {
                param.put("cara_datang", "-");
            }
            
            if (ChkHis.isSelected() == true) {
                param.put("cek_his", "V");
                param.put("his", "His/kontraksi : " + ThisKontraksi.getText() + " x/10 mnt");
            } else {
                param.put("cek_his", "");
                param.put("his", "His/kontraksi : ....  x/10 mnt");
            }
            
            if (ChkMeninggal.isSelected() == true) {
                param.put("jam_meninggal", cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + ":" + cmbDtk.getSelectedItem().toString());                
            } else {
                param.put("jam_meninggal", "-");
            }
            
            if (ChkJamKeluar.isSelected() == true) {
                param.put("jam_keluar", cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + ":" + cmbDtk1.getSelectedItem().toString());   
            } else {
                param.put("jam_keluar", "-");
            }
            
            Valid.MyReport("rptCetakPenilaianAwalMedisObstetriRalan.jasper", "report", "::[ Laporan Asesmen Medik Obstetri hal. 1 ]::",
                    "SELECT p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllhr, "
                    + "CONCAT('Tanggal : ',DATE_FORMAT(pa.tanggal,'%d-%m-%Y'),'  Pukul : ',TIME_FORMAT(pa.tanggal,'%H:%i')) mulai_penang, "
                    + "IF(pa.cervival='ya','V','') cer, IF(pa.rjp='ya','V','') rjp, IF(pa.defribilasi='ya','V','') def, IF(pa.intubasi='ya','V','') intu, IF(pa.vtp='ya','V','') vtp, "
                    + "IF(pa.dekompresi='ya','V','') dek, IF(pa.balut='ya','V','') bal, IF(pa.kateter='ya','V','') kat, IF(pa.ngt='ya','V','') ngt, IF(pa.infus='ya','V','') inf, "
                    + "IF(pa.obat='ya','V','') obt, IF(pa.tidak_ada='ya','V','') tdk_ada, pa.ket_obat, IF(pa.gangguan_jalan_nafas='ya','V','') ggnfs, IF(pa.paten='ya','V','') pat, "
                    + "IF(pa.obstruksi_partial='ya','V','') obsp, pa.data_obstruksi_partial, IF(pa.obstruksi_total='ya','V','') obst, IF(pa.trauma_jalan_nafas='ya','V','') traumajln, "
                    + "pa.trauma, IF(pa.resiko_aspirasi='ya','V','') res, pa.aspirasi, IF(pa.benda_asing='ya','V','') ben, pa.ket_benda_asing, pa.kesimpulan_jalan_nafas, "
                    + "pa.pernafasan, pa.data_pernafasan, pa.gerakan_dada, pa.tipe_pernafasan, pa.kesimpulan_pernafasan, pa.nadi_1, pa.kulit_mukosa, "
                    + "pa.akral_1, pa.crt, pa.kesimpulan_sirkulasi, pa.gcs_e, pa.gcs_v, pa.gcs_m, pa.pupil, pa.diameter_kanan, pa.diameter_kiri, pa.reflek_cahaya, "
                    + "pa.meningeal_sign, pa.lateralisasi, IF(pa.deformitas='ya','V','') def, IF(pa.contusio='ya','V','') con, IF(pa.penerima_edukasi='ya','V','') pen, "
                    + "IF(pa.tenderness='ya','V','') ten, IF(pa.swelling='ya','V','') swe, IF(pa.ekskoriasi='ya','V','') eks, IF(pa.abrasi='ya','V','') abr, "
                    + "IF(pa.burn='ya','V','') bur, IF(pa.laserasi='ya','V','') las, IF(pa.tidak_tampak_jelas='ya','V','') tdktampak, pa.alasan_masuk, "
                    + "pa.dengan_gr, pa.dengan_pr, pa.dengan_a, pa.hamil, pa.ket_dengan, "
                    + "IF(pa.periksa_bidan='Ya',CONCAT('Periksa ketempat bidan : Ya, Hasil / Riwayat Pemeriksaan Bidan : ',pa.hasil_periksa_bidan),CONCAT('Periksa ketempat bidan : ',pa.periksa_bidan,', Hasil / Riwayat Pemeriksaan Bidan : -')) prksa_bidan, "
                    + "IF(pa.riw_jalan_jauh='Ya',CONCAT('Riwayat perjalanan jauh : Ya, ',pa.ket_riw_jalan_jauh),CONCAT('Riwayat perjalanan jauh : ',pa.riw_jalan_jauh,', -')) riw_jln_jauh, "
                    + "pa.os_anc_bidan, pa.dengan_spog1, pa.jml_spog1, pa.dengan_spog2, pa.jml_spog2, IF(pa.hipertensi1='ya','V','') hip1, "
                    + "IF(pa.diabetes1='ya','V','') dm1, IF(pa.jantung1='ya','V','') jan1, IF(pa.asma1='ya','V','') asm1, IF(pa.lainnya1='ya','V','') lain1, "
                    + "pa.ket_lainnya1, IF(pa.hipertensi2='ya','V','') hip2, IF(pa.diabetes2='ya','V','') dm2, IF(pa.jantung2='ya','V','') jan2, IF(pa.asma2='ya','V','') asm2, "
                    + "IF(pa.lainnya2='ya','V','') lain2, pa.ket_lainnya2, pa.riw_ginekologi, pa.riwayat_kb_lain, pa.hpht, pa.hpl, pa.uk, pa.bb_blm_hamil, "
                    + "pa.bb_stlh_hamil, pa.tbi, pa.bmi, pa.lila, IF(pa.dismenorhoe='ya','V','') dismen, IF(pa.spoting='ya','V','') spot, IF(pa.menorrhagia='ya','V','') menor, "
                    + "IF(pa.metrohagia='ya','V','') metro, pa.keluhan_lain, pa.leopold1, pa.leopold2, pa.leopold3, pa.leopold4, IF(pa.nyeri_tekan='ya','V','') nyeri, "
                    + "IF(pa.bandle_ring='ya','V','') band, pa.nadi_2, pa.akral_2 FROM penilaian_awal_medis_obstetri_ralan pa INNER JOIN reg_periksa rp ON rp.no_rawat=pa.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis where pa.no_rawat='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString() + "'", param);

            Valid.MyReport("rptCetakPenilaianAwalMedisObstetriRalan1.jasper", "report", "::[ Laporan Asesmen Medik Obstetri hal. 2 ]::",
                    "SELECT pa.tfu, pa.taksiran_bb_janin, IF(pa.teratur='ya','V','')teratur, IF(pa.tdk_teratur='ya','V','')tdkteratur, IF(pa.trs_menerus='ya','V','')trsmenerus, "
                    + "pa.durasi, IF(pa.kuat='ya','V','')kuat, IF(pa.sedang='ya','V','')sedang, IF(pa.lemah='ya','V','')lemah, pa.auskultasi, IF(pa.bersih='ya','V','')bersih, "
                    + "IF(pa.oedema='ya','V','')odema, IF(pa.ruptur='ya','V','')ruptur, IF(pa.candiloma='ya','V','')candi, pa.pemeriksaan_genitalia_lain, pa.inspeksi, pa.konsistensi, "
                    + "pa.periksa_dlm_obstetri, pa.inspekulum, pa.diagnosis_sementara, pa.icd_10, pa.rencana_instruksi, pa.planing, pa.telah_diberikan_informasi_edukasi, "
                    + "pa.rencana_asuhan_diharapkan, p1.nama pemberi_edukasi, pa.penerima_edukasi, p2.nama nmdokter, DATE_FORMAT(pa.tgl_keluar_ponek,'%d-%m-%Y') tglkeluar, "
                    + "pa.opname_diruang, pa.indikasi_msk, pa.dipulangkan_kontrol_ke, pa.dirujuk_ke, pa.alasan_dirujuk, pa.penyebab, pa.k_u, pa.td, pa.hr, pa.rr, pa.temp, "
                    + "pa.spo2, pa.gcs_pulang, p3.nama nmbidan, p4.nama nmdpjp FROM penilaian_awal_medis_obstetri_ralan pa "
                    + "inner join pegawai p1 on p1.nik=pa.nip_pemberi_edukasi inner join pegawai p2 on p2.nik=pa.nip_dokter "
                    + "inner join pegawai p3 on p3.nik=pa.nip_bidan inner join pegawai p4 on p4.nik=pa.nip_dpjp where "
                    + "pa.no_rawat='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString() + "'", param);
            
            Ttd.setText("");
            Trr.setText("");
            Ttemp.setText("");
            emptTeks();
            ChkMeninggal.setSelected(false);
            cmbJam.setSelectedIndex(0);
            cmbMnt.setSelectedIndex(0);
            cmbDtk.setSelectedIndex(0);

            cmbJam.setEnabled(false);
            cmbMnt.setEnabled(false);
            cmbDtk.setEnabled(false);
            TabRawat.setSelectedIndex(1);
            tampil();            
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data terlebih dahulu..!!!!");
        }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
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

    private void BtnBidanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBidanActionPerformed
        pilihan = 1;
        akses.setform("RMPenilaianAwalMedikObstetriRalan");        
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnBidanActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'igd' and nm_gedung<>'-' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", cmbRuangan);
        
        if (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void BtnDpjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDpjpActionPerformed
        pilihan = 1;
        akses.setform("RMPenilaianAwalMedikObstetriRalan");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDpjpActionPerformed

    private void ChkMerokokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkMerokokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkMerokokActionPerformed

    private void TDiam_kananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiam_kananKeyPressed
        Valid.pindah(evt, cmbPupil, TDiam_kiri);
    }//GEN-LAST:event_TDiam_kananKeyPressed

    private void TDiagSementaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagSementaraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Ticd.requestFocus();
        }
    }//GEN-LAST:event_TDiagSementaraKeyPressed

    private void TicdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TicdKeyPressed
        Valid.pindah(evt, TDiagSementara, cmbRencana);
    }//GEN-LAST:event_TicdKeyPressed

    private void TedukasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TedukasiKeyPressed
        Valid.pindah(evt, cmbRencana, Trencana);
    }//GEN-LAST:event_TedukasiKeyPressed

    private void TrencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrencanaKeyPressed
        Valid.pindah(evt, Tedukasi, BtnPetugas);
    }//GEN-LAST:event_TrencanaKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        pilihan = 2;
        akses.setform("RMPenilaianAwalMedikObstetriRalan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        pilihan = 2;
        akses.setform("RMPenilaianAwalMedikObstetriRalan");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void cmbRuanganMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuanganMouseReleased
        AutoCompleteDecorator.decorate(cmbRuangan);
    }//GEN-LAST:event_cmbRuanganMouseReleased

    private void TindikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindikasiKeyPressed
        Valid.pindah(evt, cmbRuangan, Tdipulangkan);
    }//GEN-LAST:event_TindikasiKeyPressed

    private void TdipulangkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdipulangkanKeyPressed
        Valid.pindah(evt, Tindikasi, Tdirujuk);
    }//GEN-LAST:event_TdipulangkanKeyPressed

    private void TdirujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdirujukKeyPressed
        Valid.pindah(evt, Tdipulangkan, TAlasanDirujuk);
    }//GEN-LAST:event_TdirujukKeyPressed

    private void TAlasanDirujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlasanDirujukKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbJam.requestFocus();
        }
    }//GEN-LAST:event_TAlasanDirujukKeyPressed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void TpenyebabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpenyebabKeyPressed
        Valid.pindah(evt, cmbDtk, Tku);
    }//GEN-LAST:event_TpenyebabKeyPressed

    private void TkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkuKeyPressed
        Valid.pindah(evt, Tpenyebab, Ttd);
    }//GEN-LAST:event_TkuKeyPressed

    private void TtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdKeyPressed
        Valid.pindah(evt, Tku, Thr);
    }//GEN-LAST:event_TtdKeyPressed

    private void ThrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThrKeyPressed
        Valid.pindah(evt, Ttd, Trr);
    }//GEN-LAST:event_ThrKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        Valid.pindah(evt, Thr, Ttemp);
    }//GEN-LAST:event_TrrKeyPressed

    private void TtempKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtempKeyPressed
        Valid.pindah(evt, Trr, Tspo2);
    }//GEN-LAST:event_TtempKeyPressed

    private void Tspo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tspo2KeyPressed
        Valid.pindah(evt, Ttemp, Tgcs);
    }//GEN-LAST:event_Tspo2KeyPressed

    private void TgcsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsKeyPressed
        Valid.pindah(evt, Tspo2, TglKeluar);
    }//GEN-LAST:event_TgcsKeyPressed

    private void ChkObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkObatActionPerformed
        if (ChkObat.isSelected() == true) {
            TObat.setEnabled(true);
            TObat.requestFocus();
        } else {
            TObat.setText("");
            TObat.setEnabled(false);
        }
    }//GEN-LAST:event_ChkObatActionPerformed

    private void ChkTraumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTraumaActionPerformed
        if (ChkTrauma.isSelected() == true) {
            cmbTrauma.setEnabled(true);
        } else {
            cmbTrauma.setSelectedIndex(0);
            cmbTrauma.setEnabled(false);
        }
    }//GEN-LAST:event_ChkTraumaActionPerformed

    private void ChkResikoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkResikoActionPerformed
        if (ChkResiko.isSelected() == true) {
            cmbResiko.setEnabled(true);
        } else {
            cmbResiko.setSelectedIndex(0);
            cmbResiko.setEnabled(false);
        }
    }//GEN-LAST:event_ChkResikoActionPerformed

    private void ChkBendaAsingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkBendaAsingActionPerformed
        if (ChkBendaAsing.isSelected() == true) {
            TBendaAsing.setEnabled(true);
        } else {
            TBendaAsing.setText("");
            TBendaAsing.setEnabled(false);
        }
    }//GEN-LAST:event_ChkBendaAsingActionPerformed

    private void ChkMeninggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkMeninggalActionPerformed
        if (ChkMeninggal.isSelected() == true) {
            cmbJam.setEnabled(true);
            cmbMnt.setEnabled(true);
            cmbDtk.setEnabled(true);
        } else {
            cmbJam.setSelectedIndex(0);
            cmbMnt.setSelectedIndex(0);
            cmbDtk.setSelectedIndex(0);
            
            cmbJam.setEnabled(false);
            cmbMnt.setEnabled(false);
            cmbDtk.setEnabled(false);
        }
    }//GEN-LAST:event_ChkMeninggalActionPerformed

    private void ChkObstruksiPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkObstruksiPActionPerformed
        if (ChkObstruksiP.isSelected() == true) {
            cmbObstruksi.setEnabled(true);
        } else {
            cmbObstruksi.setSelectedIndex(0);
            cmbObstruksi.setEnabled(false);
        }
    }//GEN-LAST:event_ChkObstruksiPActionPerformed

    private void cmbPernapasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPernapasanActionPerformed
        if (cmbPernapasan.getSelectedIndex() != 0) {
            cmbReguler.setSelectedIndex(0);
            cmbReguler.setEnabled(true);
            cmbReguler.requestFocus();
        } else {
            cmbReguler.setSelectedIndex(0);
            cmbReguler.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPernapasanActionPerformed

    private void cmbRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRencanaActionPerformed
        if (cmbRencana.getSelectedIndex() != 0) {
            Tplaning.setText("");
            Tplaning.setEnabled(true);
            BtnPlaning.setEnabled(true);
            Tplaning.requestFocus();
        } else {
            Tplaning.setText("");
            BtnPlaning.setEnabled(false);
            Tplaning.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRencanaActionPerformed

    private void TReflekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TReflekKeyPressed
        Valid.pindah(evt, TDiam_kiri, TMeningeal);
    }//GEN-LAST:event_TReflekKeyPressed

    private void TMeningealKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMeningealKeyPressed
        Valid.pindah(evt, TReflek, cmbLater);
    }//GEN-LAST:event_TMeningealKeyPressed

    private void btnICDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnICDActionPerformed
        akses.setform("RMPenilaianAwalMedikObstetriRalan");
        icd10.isCek();
        icd10.emptTeks();
        icd10.ChkInput.setSelected(false);
        icd10.isForm();
        icd10.setSize(983, internalFrame1.getHeight() - 40);
        icd10.setLocationRelativeTo(internalFrame1);
        icd10.setAlwaysOnTop(false);
        icd10.setVisible(true);
    }//GEN-LAST:event_btnICDActionPerformed

    private void TgcsEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsEKeyPressed
        Valid.pindah(evt, cmbKesSirkulasi, TgcsV);
    }//GEN-LAST:event_TgcsEKeyPressed

    private void TgcsVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsVKeyPressed
        Valid.pindah(evt, TgcsE, TgcsM);
    }//GEN-LAST:event_TgcsVKeyPressed

    private void TgcsMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsMKeyPressed
        Valid.pindah(evt, TgcsV, cmbPupil);
    }//GEN-LAST:event_TgcsMKeyPressed

    private void TDiam_kiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiam_kiriKeyPressed
        Valid.pindah(evt, TDiam_kanan, TReflek);
    }//GEN-LAST:event_TDiam_kiriKeyPressed

    private void TplaningKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TplaningKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tedukasi.requestFocus();
        }
    }//GEN-LAST:event_TplaningKeyPressed

    private void cmbCara_datangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCara_datangActionPerformed
        if (cmbCara_datang.getSelectedIndex() == 0 || cmbCara_datang.getSelectedIndex() == 1) {
            Trujukan_bidan.setEnabled(false);
            Tpkm.setEnabled(false);
            Tspog.setEnabled(false);
            Trs_lain.setEnabled(false);
            
            Trujukan_bidan.setText("");
            Tpkm.setText("");
            Tspog.setText("");
            Trs_lain.setText("");
        } else if (cmbCara_datang.getSelectedIndex() == 2) {
            Trujukan_bidan.setEnabled(true);
            Tpkm.setEnabled(false);
            Tspog.setEnabled(false);
            Trs_lain.setEnabled(false);
            
            Trujukan_bidan.setText("");
            Trujukan_bidan.requestFocus();
            Tpkm.setText("");
            Tspog.setText("");
            Trs_lain.setText("");
        } else if (cmbCara_datang.getSelectedIndex() == 3) {
            Trujukan_bidan.setEnabled(false);
            Tpkm.setEnabled(true);
            Tspog.setEnabled(false);
            Trs_lain.setEnabled(false);
            
            Trujukan_bidan.setText("");            
            Tpkm.setText("");
            Tpkm.requestFocus();
            Tspog.setText("");
            Trs_lain.setText("");
        } else if (cmbCara_datang.getSelectedIndex() == 4) {
            Trujukan_bidan.setEnabled(false);
            Tpkm.setEnabled(false);
            Tspog.setEnabled(true);
            Trs_lain.setEnabled(false);
            
            Trujukan_bidan.setText("");            
            Tpkm.setText("");            
            Tspog.setText("");
            Tspog.requestFocus();
            Trs_lain.setText("");
        } else if (cmbCara_datang.getSelectedIndex() == 5) {
            Trujukan_bidan.setEnabled(false);
            Tpkm.setEnabled(false);
            Tspog.setEnabled(false);
            Trs_lain.setEnabled(true);
            
            Trujukan_bidan.setText("");            
            Tpkm.setText("");            
            Tspog.setText("");            
            Trs_lain.setText("");
            Trs_lain.requestFocus();
        }
    }//GEN-LAST:event_cmbCara_datangActionPerformed

    private void Talasan_mskKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Talasan_mskKeyPressed
        Valid.pindah(evt, cmbLater, cmbCara_datang);
    }//GEN-LAST:event_Talasan_mskKeyPressed

    private void TgrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgrKeyPressed
        Valid.pindah(evt, cmbCara_datang, Tpr);
    }//GEN-LAST:event_TgrKeyPressed

    private void TprKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprKeyPressed
        Valid.pindah(evt, Tgr, Ta);
    }//GEN-LAST:event_TprKeyPressed

    private void TaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TaKeyPressed
        Valid.pindah(evt, Tpr, Thamil);
    }//GEN-LAST:event_TaKeyPressed

    private void ThamilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThamilKeyPressed
        Valid.pindah(evt, Ta, Tket_dengan);
    }//GEN-LAST:event_ThamilKeyPressed

    private void ChkPerutMulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPerutMulesActionPerformed
        if (ChkPerutMules.isSelected() == true) {
            TglPerutMules.setEnabled(true);
            TglPerutMules.requestFocus();
        } else {
            TglPerutMules.setEnabled(false);
            TglPerutMules.setDate(new Date());
        }
    }//GEN-LAST:event_ChkPerutMulesActionPerformed

    private void cmbKeluarLendirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKeluarLendirActionPerformed
        if(cmbKeluarLendir.getSelectedIndex() == 1) {
            TglLendirYa.setEnabled(true);
            TglLendirYa.requestFocus();
        } else {
            TglLendirYa.setEnabled(false);
            TglLendirYa.setDate(new Date());
        }
    }//GEN-LAST:event_cmbKeluarLendirActionPerformed

    private void cmbKeluarAirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKeluarAirActionPerformed
        if (cmbKeluarAir.getSelectedIndex() == 1) {
            cmbKeluarAirYa.setEnabled(true);
            TglAirYa.setEnabled(true);
            cmbKeluarAirYa.setSelectedIndex(0);
        } else {
            cmbKeluarAirYa.setEnabled(false);
            TglAirYa.setEnabled(false);
            TglAirYa.setDate(new Date());
            cmbKeluarAirYa.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cmbKeluarAirActionPerformed

    private void cmbPeriksaBidanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPeriksaBidanActionPerformed
        if (cmbPeriksaBidan.getSelectedIndex() == 1) {
            BtnHasil.setEnabled(true);
            THasilPeriksaBidan.setEnabled(true);
            THasilPeriksaBidan.setText("");
            THasilPeriksaBidan.requestFocus();
        } else {
            BtnHasil.setEnabled(false);
            THasilPeriksaBidan.setEnabled(false);
            THasilPeriksaBidan.setText("");
        }
    }//GEN-LAST:event_cmbPeriksaBidanActionPerformed

    private void THasilPeriksaBidanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THasilPeriksaBidanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbMengeluhPusing.requestFocus();
        }
    }//GEN-LAST:event_THasilPeriksaBidanKeyPressed

    private void cmbMengeluhPusingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMengeluhPusingActionPerformed
        if (cmbMengeluhPusing.getSelectedIndex() == 1) {
            TglPusing.setEnabled(true);
            TglPusing.setDate(new Date());
            TglPusing.requestFocus();
        } else {
            TglPusing.setEnabled(false);
            TglPusing.setDate(new Date());
        }
    }//GEN-LAST:event_cmbMengeluhPusingActionPerformed

    private void cmbNyeriUluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNyeriUluActionPerformed
        if (cmbNyeriUlu.getSelectedIndex() == 1) {
            TglNyeri.setEnabled(true);
            TglNyeri.setDate(new Date());
            TglNyeri.requestFocus();
        } else {
            TglNyeri.setEnabled(false);
            TglNyeri.setDate(new Date());
        }
    }//GEN-LAST:event_cmbNyeriUluActionPerformed

    private void cmbPandanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPandanganActionPerformed
        if (cmbPandangan.getSelectedIndex() == 1) {
            TglPandangan.setEnabled(true);
            TglPandangan.setDate(new Date());
            TglPandangan.requestFocus();
        } else {
            TglPandangan.setEnabled(false);
            TglPandangan.setDate(new Date());
        }
    }//GEN-LAST:event_cmbPandanganActionPerformed

    private void cmbMualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMualActionPerformed
        if (cmbMual.getSelectedIndex() == 1) {
            TglMual.setEnabled(true);
            TglMual.setDate(new Date());
            TglMual.requestFocus();
        } else {
            TglMual.setEnabled(false);
            TglMual.setDate(new Date());
        }
    }//GEN-LAST:event_cmbMualActionPerformed

    private void cmbBatukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBatukActionPerformed
        if (cmbBatuk.getSelectedIndex() == 1) {
            TglBatuk.setEnabled(true);
            TglBatuk.setDate(new Date());
            TglBatuk.requestFocus();
        } else {
            TglBatuk.setEnabled(false);
            TglBatuk.setDate(new Date());
        }
    }//GEN-LAST:event_cmbBatukActionPerformed

    private void cmbDemamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDemamActionPerformed
        if (cmbDemam.getSelectedIndex() == 1) {
            TglDemam.setEnabled(true);
            TglDemam.setDate(new Date());
            TglDemam.requestFocus();
        } else {
            TglDemam.setEnabled(false);
            TglDemam.setDate(new Date());
        }
    }//GEN-LAST:event_cmbDemamActionPerformed

    private void cmbRiwJlnJauhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwJlnJauhActionPerformed
        if (cmbRiwJlnJauh.getSelectedIndex() == 1) {
            TriwJlnJauh.setEnabled(true);
            TriwJlnJauh.setText("");
            TriwJlnJauh.requestFocus();
        } else {
            TriwJlnJauh.setEnabled(false);
            TriwJlnJauh.setText("");
        }
    }//GEN-LAST:event_cmbRiwJlnJauhActionPerformed

    private void ChkLainya1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkLainya1ActionPerformed
        if (ChkLainya1.isSelected() == true) {
            TketLainya1.setText("");
            TketLainya1.setEnabled(true);
            TketLainya1.requestFocus();
        } else {
            TketLainya1.setText("");
            TketLainya1.setEnabled(false);
        }
    }//GEN-LAST:event_ChkLainya1ActionPerformed

    private void ChkLainya2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkLainya2ActionPerformed
        if (ChkLainya2.isSelected() == true) {
            TketLainya2.setText("");
            TketLainya2.setEnabled(true);
            TketLainya2.requestFocus();
        } else {
            TketLainya2.setText("");
            TketLainya2.setEnabled(false);
        }
    }//GEN-LAST:event_ChkLainya2ActionPerformed

    private void ChkPilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPilActionPerformed
        if (ChkPil.isSelected() == true) {
            Tpil.setEnabled(true);
            cmbSatuanPil.setEnabled(true);
            Tpil.setText("");
            cmbSatuanPil.setSelectedIndex(0);
            Tpil.requestFocus();
        } else {
            Tpil.setEnabled(false);
            cmbSatuanPil.setEnabled(false);
            Tpil.setText("");
            cmbSatuanPil.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkPilActionPerformed

    private void ChkSuntik1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSuntik1ActionPerformed
        if (ChkSuntik1.isSelected() == true) {
            Tsuntik1.setEnabled(true);
            cmbSatuanSuntik1.setEnabled(true);
            Tsuntik1.setText("");
            cmbSatuanSuntik1.setSelectedIndex(0);
            Tsuntik1.requestFocus();
        } else {
            Tsuntik1.setEnabled(false);
            cmbSatuanSuntik1.setEnabled(false);
            Tsuntik1.setText("");
            cmbSatuanSuntik1.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkSuntik1ActionPerformed

    private void ChkSuntik3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSuntik3ActionPerformed
        if (ChkSuntik3.isSelected() == true) {
            Tsuntik3.setEnabled(true);
            cmbSatuanSuntik3.setEnabled(true);
            Tsuntik3.setText("");
            cmbSatuanSuntik3.setSelectedIndex(0);
            Tsuntik3.requestFocus();
        } else {
            Tsuntik3.setEnabled(false);
            cmbSatuanSuntik3.setEnabled(false);
            Tsuntik3.setText("");
            cmbSatuanSuntik3.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkSuntik3ActionPerformed

    private void ChkImplanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkImplanActionPerformed
        if (ChkImplan.isSelected() == true) {
            Timplan.setEnabled(true);
            cmbSatuanImplan.setEnabled(true);
            Timplan.setText("");
            cmbSatuanImplan.setSelectedIndex(0);
            Timplan.requestFocus();
        } else {
            Timplan.setEnabled(false);
            cmbSatuanImplan.setEnabled(false);
            Timplan.setText("");
            cmbSatuanImplan.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkImplanActionPerformed

    private void ChkHisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkHisActionPerformed
        if (ChkHis.isSelected() == true) {
            ThisKontraksi.setText("");
            ThisKontraksi.setEnabled(true);
            ThisKontraksi.requestFocus();
        } else {
            ThisKontraksi.setText("");
            ThisKontraksi.setEnabled(false);
        }
    }//GEN-LAST:event_ChkHisActionPerformed

    private void Trujukan_bidanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Trujukan_bidanKeyPressed
        Valid.pindah(evt, cmbCara_datang, Tgr);
    }//GEN-LAST:event_Trujukan_bidanKeyPressed

    private void TpkmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpkmKeyPressed
        Valid.pindah(evt, cmbCara_datang, Tgr);
    }//GEN-LAST:event_TpkmKeyPressed

    private void TspogKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TspogKeyPressed
        Valid.pindah(evt, cmbCara_datang, Tgr);
    }//GEN-LAST:event_TspogKeyPressed

    private void Trs_lainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Trs_lainKeyPressed
        Valid.pindah(evt, cmbCara_datang, Tgr);
    }//GEN-LAST:event_Trs_lainKeyPressed

    private void TriwJlnJauhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwJlnJauhKeyPressed
        Valid.pindah(evt, cmbRiwJlnJauh, cmbOs);
    }//GEN-LAST:event_TriwJlnJauhKeyPressed

    private void Tspog1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tspog1KeyPressed
        Valid.pindah(evt, cmbOs, TJspog1);
    }//GEN-LAST:event_Tspog1KeyPressed

    private void Tspog2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tspog2KeyPressed
        Valid.pindah(evt, TJspog1, TJspog2);
    }//GEN-LAST:event_Tspog2KeyPressed

    private void TJspog1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TJspog1KeyPressed
        Valid.pindah(evt, TJspog1, Tspog2);
    }//GEN-LAST:event_TJspog1KeyPressed

    private void TpilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpilKeyPressed
        Valid.pindah(evt, cmbOs, cmbSatuanPil);
    }//GEN-LAST:event_TpilKeyPressed

    private void Tsuntik1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tsuntik1KeyPressed
        Valid.pindah(evt, cmbOs, cmbSatuanSuntik1);
    }//GEN-LAST:event_Tsuntik1KeyPressed

    private void Tsuntik3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tsuntik3KeyPressed
        Valid.pindah(evt, cmbOs, cmbSatuanSuntik3);
    }//GEN-LAST:event_Tsuntik3KeyPressed

    private void TimplanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimplanKeyPressed
        Valid.pindah(evt, cmbOs, cmbSatuanImplan);
    }//GEN-LAST:event_TimplanKeyPressed

    private void TriwKBlainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwKBlainKeyPressed
        Valid.pindah(evt, cmbSatuanImplan, Thpht);
    }//GEN-LAST:event_TriwKBlainKeyPressed

    private void ThphtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThphtKeyPressed
        Valid.pindah(evt, TriwKBlain, Thpl);
    }//GEN-LAST:event_ThphtKeyPressed

    private void ThplKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThplKeyPressed
        Valid.pindah(evt, Thpht, Tuk);
    }//GEN-LAST:event_ThplKeyPressed

    private void TukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TukKeyPressed
        Valid.pindah(evt, Thpl, Tukusg);
    }//GEN-LAST:event_TukKeyPressed

    private void TukusgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TukusgKeyPressed
        Valid.pindah(evt, Tuk, tglUSG);
    }//GEN-LAST:event_TukusgKeyPressed

    private void TbbBlmHamilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbBlmHamilKeyPressed
        Valid.pindah(evt, tglUSG, TbbStlhHamil);
    }//GEN-LAST:event_TbbBlmHamilKeyPressed

    private void TbbStlhHamilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbStlhHamilKeyPressed
        Valid.pindah(evt, TbbBlmHamil, Ttbi);
    }//GEN-LAST:event_TbbStlhHamilKeyPressed

    private void TtbiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbiKeyPressed
        Valid.pindah(evt, TbbStlhHamil, Tbmi);
    }//GEN-LAST:event_TtbiKeyPressed

    private void TbmiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbmiKeyPressed
        Valid.pindah(evt, Ttbi, Tlila);
    }//GEN-LAST:event_TbmiKeyPressed

    private void TkeluhanLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeluhanLainKeyPressed
        Valid.pindah(evt, cmbOs, Tleopold1);
    }//GEN-LAST:event_TkeluhanLainKeyPressed

    private void Tleopold1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tleopold1KeyPressed
        Valid.pindah(evt, Tleopold1, Tleopold2);
    }//GEN-LAST:event_Tleopold1KeyPressed

    private void Tleopold2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tleopold2KeyPressed
        Valid.pindah(evt, Tleopold1, Tleopold3);
    }//GEN-LAST:event_Tleopold2KeyPressed

    private void Tleopold3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tleopold3KeyPressed
        Valid.pindah(evt, Tleopold2, Tleopold4);
    }//GEN-LAST:event_Tleopold3KeyPressed

    private void Tleopold4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tleopold4KeyPressed
        Valid.pindah(evt, Tleopold3, Ttfu);
    }//GEN-LAST:event_Tleopold4KeyPressed

    private void TtfuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtfuKeyPressed
        Valid.pindah(evt, Tleopold4, TbbJanin);
    }//GEN-LAST:event_TtfuKeyPressed

    private void Tgenitalia_lainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgenitalia_lainKeyPressed
        Valid.pindah(evt, Tleopold1, Tinspeksi);
    }//GEN-LAST:event_Tgenitalia_lainKeyPressed

    private void TinspeksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TinspeksiKeyPressed
        Valid.pindah(evt, Tgenitalia_lain, cmbKonsistensi);
    }//GEN-LAST:event_TinspeksiKeyPressed

    private void Tperiksa_dlmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tperiksa_dlmKeyPressed
        Valid.pindah(evt, cmbKonsistensi, Tinspekulum);
    }//GEN-LAST:event_Tperiksa_dlmKeyPressed

    private void TinspekulumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TinspekulumKeyPressed
        Valid.pindah(evt, Tperiksa_dlm, btnICD);
    }//GEN-LAST:event_TinspekulumKeyPressed

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMPenilaianAwalMedikObstetriRalan");
            DlgCatatanResep form = new DlgCatatanResep(null, false);
            form.isCek();
            form.setData(TNoRw.getText(), "ralan");
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnResepActionPerformed

    private void BtnResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnResepKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnResepKeyPressed

    private void BtnHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHasilActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 1;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Hasil/Riwayat Pemeriksaan Bidan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnHasilActionPerformed

    private void BtnDiagnosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagnosisActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 2;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Diagnosis Medis Sementara ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnDiagnosisActionPerformed

    private void BtnPlaningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPlaningActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 3;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Planing ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnPlaningActionPerformed

    private void BtnAlasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlasanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 4;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Alasan Dirujuk ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnAlasanActionPerformed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilTemplate();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnCopasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopasActionPerformed
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah data ini akan dipakai..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            copas();
            WindowTemplate.dispose();
        }
    }//GEN-LAST:event_BtnCopasActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowTemplate.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void tbTemplateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTemplateMouseClicked
        if(tabMode1.getRowCount() != 0) {
            try {
                if (tbTemplate.getSelectedRow() != -1) {
                    Ttemplate.setText(tbTemplate.getValueAt(tbTemplate.getSelectedRow(), 2).toString());
                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTemplateMouseClicked

    private void ChkJamKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJamKeluarActionPerformed
        if (ChkJamKeluar.isSelected() == true) {
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
        } else {
            cmbJam1.setSelectedIndex(0);
            cmbMnt1.setSelectedIndex(0);
            cmbDtk1.setSelectedIndex(0);

            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
        }
    }//GEN-LAST:event_ChkJamKeluarActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianAwalMedikObstetriRalan dialog = new RMPenilaianAwalMedikObstetriRalan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAlasan;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnBidan;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCopas;
    private widget.Button BtnDiagnosis;
    private widget.Button BtnDokter;
    private widget.Button BtnDpjp;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnHasil;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas;
    private widget.Button BtnPlaning;
    private widget.Button BtnPrint;
    private widget.Button BtnResep;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAbrasi;
    public widget.CekBox ChkAsma1;
    public widget.CekBox ChkAsma2;
    public widget.CekBox ChkBalut;
    public widget.CekBox ChkBandle;
    public widget.CekBox ChkBendaAsing;
    public widget.CekBox ChkBersih;
    public widget.CekBox ChkBurn;
    public widget.CekBox ChkCandiloma;
    public widget.CekBox ChkCervical;
    public widget.CekBox ChkContusio;
    public widget.CekBox ChkDM1;
    public widget.CekBox ChkDM2;
    public widget.CekBox ChkDeformitas;
    public widget.CekBox ChkDefribilasi;
    public widget.CekBox ChkDekompresi;
    public widget.CekBox ChkDismen;
    public widget.CekBox ChkEkskoriasi;
    public widget.CekBox ChkGangNafas;
    public widget.CekBox ChkHipertensi1;
    public widget.CekBox ChkHipertensi2;
    public widget.CekBox ChkHis;
    public widget.CekBox ChkImplan;
    public widget.CekBox ChkInfus;
    public widget.CekBox ChkIntubasi;
    public widget.CekBox ChkJamKeluar;
    public widget.CekBox ChkJantung1;
    public widget.CekBox ChkJantung2;
    public widget.CekBox ChkKateter;
    public widget.CekBox ChkKuat;
    public widget.CekBox ChkLainya1;
    public widget.CekBox ChkLainya2;
    public widget.CekBox ChkLaserasi;
    public widget.CekBox ChkLemah;
    public widget.CekBox ChkMeninggal;
    public widget.CekBox ChkMenor;
    public widget.CekBox ChkMetro;
    public widget.CekBox ChkNGT;
    public widget.CekBox ChkNyeriTekan;
    public widget.CekBox ChkObat;
    public widget.CekBox ChkObstruksiP;
    public widget.CekBox ChkObstruksiT;
    public widget.CekBox ChkOedema;
    public widget.CekBox ChkPaten;
    public widget.CekBox ChkPenetrasi;
    public widget.CekBox ChkPerutMules;
    public widget.CekBox ChkPil;
    public widget.CekBox ChkRJP;
    public widget.CekBox ChkResiko;
    public widget.CekBox ChkRuptur;
    public widget.CekBox ChkSedang;
    public widget.CekBox ChkSpoting;
    public widget.CekBox ChkSuntik1;
    public widget.CekBox ChkSuntik3;
    public widget.CekBox ChkSwelling;
    public widget.CekBox ChkTdkTampk;
    public widget.CekBox ChkTdkTeratur;
    public widget.CekBox ChkTenderness;
    public widget.CekBox ChkTeratur;
    public widget.CekBox ChkTidak;
    public widget.CekBox ChkTrauma;
    public widget.CekBox ChkTrsMenerus;
    public widget.CekBox ChkVTP;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.TextBox KdBidan;
    private widget.Label LCount;
    private widget.TextBox NmBidan;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll3;
    private widget.TextArea TAlasanDirujuk;
    private widget.TextBox TBendaAsing;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextArea TDiagSementara;
    private widget.TextBox TDiam_kanan;
    private widget.TextBox TDiam_kiri;
    private widget.TextArea THasilPeriksaBidan;
    private widget.TextBox TJspog1;
    private widget.TextBox TJspog2;
    private widget.TextBox TMeningeal;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TObat;
    private widget.TextBox TPasien;
    private widget.TextBox TReflek;
    private widget.TextBox Ta;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Talasan_msk;
    private widget.TextBox Tauskultasi;
    private widget.TextBox TbbBlmHamil;
    private widget.TextBox TbbJanin;
    private widget.TextBox TbbStlhHamil;
    private widget.TextBox Tbmi;
    private widget.TextBox Tdipulangkan;
    private widget.TextBox Tdirujuk;
    private widget.TextBox Tdurasi;
    private widget.TextBox Tedukasi;
    private widget.TextBox Tgcs;
    private widget.TextBox TgcsE;
    private widget.TextBox TgcsM;
    private widget.TextBox TgcsV;
    private widget.TextBox Tgenitalia_lain;
    private widget.Tanggal TglAirYa;
    private widget.Tanggal TglAsesmen;
    private widget.Tanggal TglBatuk;
    private widget.Tanggal TglDemam;
    private widget.Tanggal TglKeluar;
    private widget.TextBox TglLahir;
    private widget.Tanggal TglLendirYa;
    private widget.Tanggal TglMual;
    private widget.Tanggal TglNyeri;
    private widget.Tanggal TglPandangan;
    private widget.Tanggal TglPerutMules;
    private widget.Tanggal TglPusing;
    private widget.TextBox Tgr;
    private widget.TextBox Thamil;
    private widget.TextBox ThisKontraksi;
    private widget.TextBox Thpht;
    private widget.TextBox Thpl;
    private widget.TextBox Thr;
    private widget.TextBox Ticd;
    private widget.TextBox Timplan;
    private widget.TextBox Tindikasi;
    private widget.TextBox Tinspeksi;
    private widget.TextBox Tinspekulum;
    private widget.TextBox TkeluhanLain;
    private widget.TextBox TketLainya1;
    private widget.TextBox TketLainya2;
    private widget.TextBox Tket_dengan;
    private widget.TextBox Tku;
    private widget.TextBox Tleopold1;
    private widget.TextBox Tleopold2;
    private widget.TextBox Tleopold3;
    private widget.TextBox Tleopold4;
    private widget.TextBox Tlila;
    private widget.TextBox Tpenyebab;
    private widget.TextBox Tperiksa_dlm;
    private widget.TextBox Tpil;
    private widget.TextBox Tpkm;
    private widget.TextArea Tplaning;
    private widget.TextBox Tpr;
    private widget.TextBox Trencana;
    private widget.TextBox TriwGine;
    private widget.TextBox TriwJlnJauh;
    private widget.TextBox TriwKBlain;
    private widget.TextBox Trr;
    private widget.TextBox Trs_lain;
    private widget.TextBox Trujukan_bidan;
    private widget.TextBox Tspo2;
    private widget.TextBox Tspog;
    private widget.TextBox Tspog1;
    private widget.TextBox Tspog2;
    private widget.TextBox Tsuntik1;
    private widget.TextBox Tsuntik3;
    private widget.TextBox Ttbi;
    private widget.TextBox Ttd;
    private widget.TextBox Ttemp;
    private widget.TextArea Ttemplate;
    private widget.TextBox Ttfu;
    private widget.TextBox Tuk;
    private widget.TextBox Tukusg;
    private javax.swing.JDialog WindowTemplate;
    private widget.Button btnICD;
    private widget.ComboBox cmbAkral1;
    private widget.ComboBox cmbAkral2;
    private widget.ComboBox cmbBatuk;
    private widget.ComboBox cmbCRT;
    private widget.ComboBox cmbCara_datang;
    private widget.ComboBox cmbDemam;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbGerakanDada;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbKeluarAir;
    private widget.ComboBox cmbKeluarAirYa;
    private widget.ComboBox cmbKeluarLendir;
    private widget.ComboBox cmbKesJalanNafas;
    private widget.ComboBox cmbKesPernapasan;
    private widget.ComboBox cmbKesSirkulasi;
    private widget.ComboBox cmbKonsistensi;
    private widget.ComboBox cmbKulit;
    private widget.ComboBox cmbLater;
    private widget.ComboBox cmbMengeluhPusing;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMual;
    private widget.ComboBox cmbNadi1;
    private widget.ComboBox cmbNadi2;
    private widget.ComboBox cmbNyeriUlu;
    private widget.ComboBox cmbObstruksi;
    private widget.ComboBox cmbOs;
    private widget.ComboBox cmbPandangan;
    private widget.ComboBox cmbPeriksaBidan;
    private widget.ComboBox cmbPernapasan;
    private widget.ComboBox cmbPupil;
    private widget.ComboBox cmbReguler;
    private widget.ComboBox cmbRencana;
    private widget.ComboBox cmbResiko;
    private widget.ComboBox cmbRiwJlnJauh;
    private widget.ComboBox cmbRuangan;
    private widget.ComboBox cmbSatuanImplan;
    private widget.ComboBox cmbSatuanPil;
    private widget.ComboBox cmbSatuanSuntik1;
    private widget.ComboBox cmbSatuanSuntik3;
    private widget.ComboBox cmbTipePernapasan;
    private widget.ComboBox cmbTrauma;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
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
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel136;
    private widget.Label jLabel137;
    private widget.Label jLabel138;
    private widget.Label jLabel139;
    private widget.Label jLabel140;
    private widget.Label jLabel141;
    private widget.Label jLabel142;
    private widget.Label jLabel143;
    private widget.Label jLabel144;
    private widget.Label jLabel145;
    private widget.Label jLabel146;
    private widget.Label jLabel147;
    private widget.Label jLabel148;
    private widget.Label jLabel149;
    private widget.Label jLabel150;
    private widget.Label jLabel151;
    private widget.Label jLabel152;
    private widget.Label jLabel153;
    private widget.Label jLabel154;
    private widget.Label jLabel155;
    private widget.Label jLabel156;
    private widget.Label jLabel157;
    private widget.Label jLabel158;
    private widget.Label jLabel159;
    private widget.Label jLabel160;
    private widget.Label jLabel161;
    private widget.Label jLabel162;
    private widget.Label jLabel163;
    private widget.Label jLabel164;
    private widget.Label jLabel165;
    private widget.Label jLabel166;
    private widget.Label jLabel167;
    private widget.Label jLabel168;
    private widget.Label jLabel169;
    private widget.Label jLabel170;
    private widget.Label jLabel171;
    private widget.Label jLabel172;
    private widget.Label jLabel173;
    private widget.Label jLabel174;
    private widget.Label jLabel175;
    private widget.Label jLabel176;
    private widget.Label jLabel177;
    private widget.Label jLabel178;
    private widget.Label jLabel179;
    private widget.Label jLabel180;
    private widget.Label jLabel181;
    private widget.Label jLabel182;
    private widget.Label jLabel183;
    private widget.Label jLabel184;
    private widget.Label jLabel185;
    private widget.Label jLabel186;
    private widget.Label jLabel187;
    private widget.Label jLabel188;
    private widget.Label jLabel189;
    private widget.Label jLabel19;
    private widget.Label jLabel190;
    private widget.Label jLabel191;
    private widget.Label jLabel192;
    private widget.Label jLabel193;
    private widget.Label jLabel194;
    private widget.Label jLabel195;
    private widget.Label jLabel196;
    private widget.Label jLabel197;
    private widget.Label jLabel198;
    private widget.Label jLabel199;
    private widget.Label jLabel200;
    private widget.Label jLabel201;
    private widget.Label jLabel202;
    private widget.Label jLabel203;
    private widget.Label jLabel204;
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
    private widget.Label jLabel47;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private widget.TextBox kddokter;
    private widget.TextBox kddpjp;
    private widget.TextBox kdpetugas;
    private widget.Label label11;
    private widget.Label label14;
    private widget.Label label15;
    private widget.TextBox nmdokter;
    private widget.TextBox nmdpjp;
    private widget.TextBox nmpenerima;
    private widget.TextBox nmpetugas;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbAsesmen;
    private widget.Table tbTemplate;
    private widget.Tanggal tglUSG;
    // End of variables declaration//GEN-END:variables

     private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tglLhr, "
                    + "date_format(pm.tanggal,'%d-%m-%Y %H:%i') tglases, pg1.nama nm_pemberi, "
                    + "pg2.nama nm_dokter, pg3.nama nm_bidan, pg4.nama nm_dpjp, DATE_FORMAT(pm.tgl_keluar_ponek,'%Y-%m-%d') tglklr, "
                    + "DATE_FORMAT(pm.tgl_keluar_ponek,'%H:%i:%s') jamklr, pm.* FROM reg_periksa rp "
                    + "inner join penilaian_awal_medis_obstetri_ralan pm on pm.no_rawat=rp.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "left join pegawai pg1 on pg1.nik=pm.nip_pemberi_edukasi "
                    + "left join pegawai pg2 on pg2.nik=pm.nip_dokter "
                    + "left join pegawai pg3 on pg3.nik=pm.nip_bidan "
                    + "left join pegawai pg4 on pg4.nik=pm.nip_dpjp WHERE "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND rp.no_rawat LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND p.no_rkm_medis LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND pm.nip_bidan LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND pm.nip_dpjp LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND pm.nip_pemberi_edukasi LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND pg1.nama LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND pg2.nama LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND pg3.nama LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND pg4.nama LIKE ? ORDER BY pm.tanggal");
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
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText() + "%");
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText() + "%");
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText() + "%");
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText() + "%");
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText() + "%");                
                ps.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(30, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),                        
                        rs.getString("tglLhr"),
                        rs.getString("tglases"),
                        rs.getString("tanggal"),
                        rs.getString("cervival"),
                        rs.getString("rjp"),
                        rs.getString("defribilasi"),
                        rs.getString("intubasi"),
                        rs.getString("vtp"),
                        rs.getString("dekompresi"),
                        rs.getString("balut"),
                        rs.getString("kateter"),
                        rs.getString("ngt"),
                        rs.getString("infus"),
                        rs.getString("obat"),
                        rs.getString("ket_obat"),
                        rs.getString("tidak_ada"),
                        rs.getString("paten"),
                        rs.getString("gangguan_jalan_nafas"),
                        rs.getString("obstruksi_partial"),
                        rs.getString("data_obstruksi_partial"),
                        rs.getString("obstruksi_total"),
                        rs.getString("trauma_jalan_nafas"),
                        rs.getString("trauma"),
                        rs.getString("resiko_aspirasi"),
                        rs.getString("aspirasi"),
                        rs.getString("benda_asing"),
                        rs.getString("ket_benda_asing"),
                        rs.getString("kesimpulan_jalan_nafas"),
                        rs.getString("pernafasan"),
                        rs.getString("data_pernafasan"),
                        rs.getString("gerakan_dada"),
                        rs.getString("tipe_pernafasan"),
                        rs.getString("kesimpulan_pernafasan"),
                        rs.getString("nadi_1"),
                        rs.getString("kulit_mukosa"),
                        rs.getString("akral_1"),
                        rs.getString("crt"),
                        rs.getString("kesimpulan_sirkulasi"),
                        rs.getString("gcs_e"),
                        rs.getString("gcs_v"),
                        rs.getString("gcs_m"),
                        rs.getString("pupil"),
                        rs.getString("diameter_kanan"),
                        rs.getString("diameter_kiri"),
                        rs.getString("reflek_cahaya"),
                        rs.getString("meningeal_sign"),
                        rs.getString("lateralisasi"),
                        rs.getString("deformitas"),
                        rs.getString("contusio"),
                        rs.getString("penetrasi"),
                        rs.getString("tenderness"),
                        rs.getString("swelling"),
                        rs.getString("ekskoriasi"),
                        rs.getString("abrasi"),
                        rs.getString("burn"),
                        rs.getString("laserasi"),
                        rs.getString("tidak_tampak_jelas"),
                        rs.getString("alasan_masuk"),
                        rs.getString("cara_datang"),
                        rs.getString("ket_rujukan_bidan"),
                        rs.getString("ket_pkm"),
                        rs.getString("ket_spog"),
                        rs.getString("ket_rs_lain"),
                        rs.getString("dengan_gr"),
                        rs.getString("dengan_pr"),
                        rs.getString("dengan_a"),
                        rs.getString("hamil"),
                        rs.getString("ket_dengan"),
                        rs.getString("cek_mengeluh_perut"),
                        rs.getString("tgl_mengeluh_perut"),
                        rs.getString("keluar_lendir"),
                        rs.getString("tgl_lendir_ya"),
                        rs.getString("keluar_air"),
                        rs.getString("keluar_air_ya"),
                        rs.getString("tgl_air_ya"),
                        rs.getString("periksa_bidan"),
                        rs.getString("hasil_periksa_bidan"),
                        rs.getString("mengeluh_pusing"),
                        rs.getString("tgl_pusing"),
                        rs.getString("nyeri_ulu_hati"),
                        rs.getString("tgl_nyeri"),
                        rs.getString("pandangan_kabur"),
                        rs.getString("tgl_pandangan"),
                        rs.getString("mual_muntah"),
                        rs.getString("tgl_mual_muntah"),
                        rs.getString("batuk_pilek"),
                        rs.getString("tgl_batuk_pilek"),
                        rs.getString("demam"),
                        rs.getString("tgl_demam"),
                        rs.getString("riw_jalan_jauh"),
                        rs.getString("ket_riw_jalan_jauh"),
                        rs.getString("os_anc_bidan"),
                        rs.getString("dengan_spog1"),
                        rs.getString("jml_spog1"),
                        rs.getString("dengan_spog2"),
                        rs.getString("jml_spog2"),
                        rs.getString("hipertensi1"),
                        rs.getString("diabetes1"),
                        rs.getString("jantung1"),
                        rs.getString("asma1"),
                        rs.getString("lainnya1"),
                        rs.getString("ket_lainnya1"),
                        rs.getString("hipertensi2"),
                        rs.getString("diabetes2"),
                        rs.getString("jantung2"),
                        rs.getString("asma2"),
                        rs.getString("lainnya2"),
                        rs.getString("ket_lainnya2"),
                        rs.getString("riw_ginekologi"),
                        rs.getString("pil"),
                        rs.getString("lama_pil"),
                        rs.getString("satuan_pil"),
                        rs.getString("suntik_1_bln"),
                        rs.getString("lama_1_bln"),
                        rs.getString("satuan_suntik1"),
                        rs.getString("suntik_3_bln"),
                        rs.getString("lama_3_bln"),
                        rs.getString("satuan_suntik3"),
                        rs.getString("implan"),
                        rs.getString("lama_implan"),
                        rs.getString("satuan_implan"),
                        rs.getString("riwayat_kb_lain"),
                        rs.getString("hpht"),
                        rs.getString("hpl"),
                        rs.getString("uk"),
                        rs.getString("uk_usg"),
                        rs.getString("tgl_usg"),
                        rs.getString("bb_blm_hamil"),
                        rs.getString("bb_stlh_hamil"),
                        rs.getString("tbi"),
                        rs.getString("bmi"),
                        rs.getString("lila"),
                        rs.getString("dismenorhoe"),
                        rs.getString("spoting"),
                        rs.getString("menorrhagia"),
                        rs.getString("metrohagia"),
                        rs.getString("keluhan_lain"),
                        rs.getString("leopold1"),
                        rs.getString("leopold2"),
                        rs.getString("leopold3"),
                        rs.getString("leopold4"),
                        rs.getString("nyeri_tekan"),
                        rs.getString("bandle_ring"),
                        rs.getString("tfu"),
                        rs.getString("taksiran_bb_janin"),
                        rs.getString("his_kontraksi"),
                        rs.getString("ket_his_kontraksi"),
                        rs.getString("teratur"),
                        rs.getString("tdk_teratur"),
                        rs.getString("trs_menerus"),
                        rs.getString("durasi"),
                        rs.getString("kuat"),
                        rs.getString("sedang"),
                        rs.getString("lemah"),
                        rs.getString("auskultasi"),
                        rs.getString("bersih"),
                        rs.getString("oedema"),
                        rs.getString("ruptur"),
                        rs.getString("candiloma"),
                        rs.getString("pemeriksaan_genitalia_lain"),
                        rs.getString("inspeksi"),
                        rs.getString("konsistensi"),
                        rs.getString("periksa_dlm_obstetri"),
                        rs.getString("inspekulum"),
                        rs.getString("diagnosis_sementara"),
                        rs.getString("icd_10"),
                        rs.getString("rencana_instruksi"),
                        rs.getString("planing"),
                        rs.getString("telah_diberikan_informasi_edukasi"),
                        rs.getString("rencana_asuhan_diharapkan"),
                        rs.getString("nip_pemberi_edukasi"),
                        rs.getString("penerima_edukasi"),
                        rs.getString("nip_dokter"),
                        rs.getString("tgl_keluar_ponek"),
                        rs.getString("opname_diruang"),
                        rs.getString("indikasi_msk"),
                        rs.getString("dipulangkan_kontrol_ke"),
                        rs.getString("dirujuk_ke"),
                        rs.getString("alasan_dirujuk"),
                        rs.getString("meninggal"),
                        rs.getString("jam_meninggal"),
                        rs.getString("penyebab"),
                        rs.getString("k_u"),
                        rs.getString("td"),
                        rs.getString("hr"),
                        rs.getString("rr"),
                        rs.getString("temp"),
                        rs.getString("spo2"),
                        rs.getString("gcs_pulang"),
                        rs.getString("nip_bidan"),
                        rs.getString("nip_dpjp"),                        
                        rs.getString("nm_pemberi"),
                        rs.getString("nm_dokter"),
                        rs.getString("nm_bidan"),
                        rs.getString("nm_dpjp"),
                        rs.getString("nadi_2"),
                        rs.getString("akral_2"),                        
                        rs.getString("cek_jam_keluar"),
                        rs.getString("tglklr"),
                        rs.getString("jamklr")
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
        TglAsesmen.setDate(new Date());
        KdBidan.setText("-");
        NmBidan.setText("-");        
        ChkCervical.setSelected(false);
        ChkRJP.setSelected(false);
        ChkDefribilasi.setSelected(false);
        ChkIntubasi.setSelected(false);
        ChkVTP.setSelected(false);
        ChkDekompresi.setSelected(false);
        ChkBalut.setSelected(false);
        ChkKateter.setSelected(false);
        ChkNGT.setSelected(false);
        ChkInfus.setSelected(false);
        ChkObat.setSelected(false);
        TObat.setText("");
        TObat.setEnabled(false);
        ChkTidak.setSelected(false);
        ChkGangNafas.setSelected(false);
        ChkPaten.setSelected(false);
        ChkObstruksiP.setSelected(false);
        cmbObstruksi.setSelectedIndex(0);
        cmbObstruksi.setEnabled(false);
        ChkObstruksiT.setSelected(false);
        ChkTrauma.setSelected(false);
        cmbTrauma.setSelectedIndex(0);
        cmbTrauma.setEnabled(false);
        ChkResiko.setSelected(false);
        cmbResiko.setSelectedIndex(0);
        cmbResiko.setEnabled(false);
        ChkBendaAsing.setSelected(false);
        TBendaAsing.setText("");
        TBendaAsing.setEnabled(false);
        cmbKesJalanNafas.setSelectedIndex(0);
        cmbPernapasan.setSelectedIndex(0);
        cmbReguler.setSelectedIndex(0);
        cmbReguler.setEnabled(false);
        cmbGerakanDada.setSelectedIndex(0);
        cmbTipePernapasan.setSelectedIndex(0);
        cmbKesPernapasan.setSelectedIndex(0);
        cmbNadi1.setSelectedIndex(0);
        cmbNadi2.setSelectedIndex(0);
        cmbKulit.setSelectedIndex(0);
        cmbAkral1.setSelectedIndex(0);
        cmbAkral2.setSelectedIndex(0);
        cmbCRT.setSelectedIndex(0);
        cmbKesSirkulasi.setSelectedIndex(0);
        TgcsE.setText("");
        TgcsV.setText("");
        TgcsM.setText("");        
        cmbPupil.setSelectedIndex(0);
        TDiam_kanan.setText("");
        TDiam_kiri.setText("");
        TReflek.setText("");
        TMeningeal.setText("");
        cmbLater.setSelectedIndex(0);
        Talasan_msk.setText("");
        cmbCara_datang.setSelectedIndex(0);
        Trujukan_bidan.setEnabled(false);
        Tpkm.setEnabled(false);
        Tspog.setEnabled(false);
        Trs_lain.setEnabled(false);
        Trujukan_bidan.setText("");
        Tpkm.setText("");
        Tspog.setText("");
        Trs_lain.setText("");
        Tgr.setText("");
        Tpr.setText("");
        Ta.setText("");
        Thamil.setText("");
        Tket_dengan.setText("");
        ChkPerutMules.setSelected(false);
        TglPerutMules.setDate(new Date());
        TglPerutMules.setEnabled(false);
        cmbKeluarLendir.setSelectedIndex(0);
        TglLendirYa.setDate(new Date());
        TglLendirYa.setEnabled(false);
        cmbKeluarAir.setSelectedIndex(0);
        cmbKeluarAirYa.setSelectedIndex(0);
        cmbKeluarAirYa.setEnabled(false);
        TglAirYa.setDate(new Date());
        TglAirYa.setEnabled(false);
        cmbPeriksaBidan.setSelectedIndex(0);
        THasilPeriksaBidan.setEnabled(false);
        THasilPeriksaBidan.setText("");
        cmbMengeluhPusing.setSelectedIndex(0);
        TglPusing.setDate(new Date());
        TglPusing.setEnabled(false);
        cmbNyeriUlu.setSelectedIndex(0);
        TglNyeri.setDate(new Date());
        TglNyeri.setEnabled(false);
        cmbPandangan.setSelectedIndex(0);
        TglPandangan.setDate(new Date());
        TglPandangan.setEnabled(false);
        cmbMual.setSelectedIndex(0);
        TglMual.setDate(new Date());
        TglMual.setEnabled(false);
        cmbBatuk.setSelectedIndex(0);
        TglBatuk.setDate(new Date());
        TglBatuk.setEnabled(false);
        cmbDemam.setSelectedIndex(0);
        TglDemam.setDate(new Date());
        TglDemam.setEnabled(false);
        cmbRiwJlnJauh.setSelectedIndex(0);
        TriwJlnJauh.setEnabled(false);
        TriwJlnJauh.setText("");
        cmbOs.setSelectedIndex(0);
        Tspog1.setText("");
        TJspog1.setText("");
        Tspog2.setText("");
        TJspog2.setText("");
        ChkHipertensi1.setSelected(false);
        ChkDM1.setSelected(false);
        ChkJantung1.setSelected(false);
        ChkAsma1.setSelected(false);
        ChkLainya1.setSelected(false);
        TketLainya1.setText("");
        TketLainya1.setEnabled(false);        
        ChkHipertensi2.setSelected(false);
        ChkDM2.setSelected(false);
        ChkJantung2.setSelected(false);
        ChkAsma2.setSelected(false);
        ChkLainya2.setSelected(false);
        TketLainya2.setText("");
        TketLainya2.setEnabled(false);
        TriwGine.setText("");        
        ChkPil.setSelected(false);
        Tpil.setText("");
        Tpil.setEnabled(false);
        cmbSatuanPil.setSelectedIndex(0);
        cmbSatuanPil.setEnabled(false);        
        ChkSuntik1.setSelected(false);
        Tsuntik1.setText("");
        Tsuntik1.setEnabled(false);
        cmbSatuanSuntik1.setSelectedIndex(0);
        cmbSatuanSuntik1.setEnabled(false);        
        ChkSuntik3.setSelected(false);
        Tsuntik3.setText("");
        Tsuntik3.setEnabled(false);
        cmbSatuanSuntik3.setSelectedIndex(0);
        cmbSatuanSuntik3.setEnabled(false);        
        ChkImplan.setSelected(false);
        Timplan.setText("");
        Timplan.setEnabled(false);
        cmbSatuanImplan.setSelectedIndex(0);
        cmbSatuanImplan.setEnabled(false);
        TriwKBlain.setText("");
        Thpht.setText("");
        Thpl.setText("");
        Tuk.setText("");
        Tukusg.setText("");
        tglUSG.setDate(new Date());
        TbbBlmHamil.setText("");
        TbbStlhHamil.setText("");
        Ttbi.setText("");
        Tbmi.setText("");
        Tlila.setText("");
        ChkDismen.setSelected(false);
        ChkSpoting.setSelected(false);
        ChkMenor.setSelected(false);
        ChkMetro.setSelected(false);
        TkeluhanLain.setText("");
        Tleopold1.setText("");
        Tleopold2.setText("");
        Tleopold3.setText("");
        Tleopold4.setText("");
        ChkNyeriTekan.setSelected(false);
        ChkBandle.setSelected(false);
        Ttfu.setText("");
        TbbJanin.setText("");
        ChkHis.setSelected(false);
        ThisKontraksi.setText("");
        ThisKontraksi.setEnabled(false);
        ChkTeratur.setSelected(false);
        ChkTdkTeratur.setSelected(false);
        ChkTrsMenerus.setSelected(false);
        ChkKuat.setSelected(false);
        ChkSedang.setSelected(false);
        ChkLemah.setSelected(false);
        Tauskultasi.setText("");        
        ChkBersih.setSelected(false);
        ChkOedema.setSelected(false);
        ChkRuptur.setSelected(false);
        ChkCandiloma.setSelected(false);
        Tgenitalia_lain.setText("");
        Tinspeksi.setText("");        
        Tperiksa_dlm.setText("");
        Tinspekulum.setText("");
        
        TDiagSementara.setText("");
        Ticd.setText("");
        cmbRencana.setSelectedIndex(0);
        Tplaning.setText("");
        Tplaning.setEnabled(false);        
        Tedukasi.setText("");
        Trencana.setText("");
        kdpetugas.setText("-");
        nmpetugas.setText("-");
        nmpenerima.setText("-");
        TglKeluar.setDate(new Date());
        cmbRuangan.setSelectedIndex(0);
        Tindikasi.setText("");
        Tdipulangkan.setText(""); 
        Tdirujuk.setText("");
        TAlasanDirujuk.setText("");
        Tpenyebab.setText("");
        Tku.setText("");
        Thr.setText("");
        Tspo2.setText("");
        Tgcs.setText("");        
        
        ChkDeformitas.setSelected(false);
        ChkContusio.setSelected(false);
        ChkPenetrasi.setSelected(false);
        ChkTenderness.setSelected(false);
        ChkSwelling.setSelected(false);
        ChkEkskoriasi.setSelected(false);
        ChkAbrasi.setSelected(false);
        ChkBurn.setSelected(false);
        ChkLaserasi.setSelected(false);
        ChkTdkTampk.setSelected(false);
        
        BtnHasil.setEnabled(false);
        BtnPlaning.setEnabled(false);
        
        ChkJamKeluar.setSelected(false);
        cmbJam1.setSelectedIndex(0);
        cmbMnt1.setSelectedIndex(0);
        cmbDtk1.setSelectedIndex(0);
        cmbJam1.setEnabled(false);
        cmbMnt1.setEnabled(false);
        cmbDtk1.setEnabled(false);
    }

    private void getData() {
        stringBersih();        
        if (tbAsesmen.getSelectedRow() != -1) {
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 2).toString());
            TglLahir.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 3).toString());
            Valid.SetTgl2(TglAsesmen, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 5).toString());
            cervical = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 6).toString();
            rjp = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 7).toString();
            defribrilasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 8).toString();
            intubasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 9).toString();
            vtp = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString();
            dekompresi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString();
            balut = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString();
            kateter = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString();
            ngt = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString();
            infus = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString();
            obat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString();
            TObat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString());
            tidakada = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString();
            paten = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 19).toString();
            ganggnafas = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString();
            obsP = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString();
            cmbObstruksi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString());
            obsT = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString();
            traumaJlnNfs = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString();
            cmbTrauma.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString());
            resikoAspirasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString();
            cmbResiko.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString());
            bendaAsing = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString();
            TBendaAsing.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString());
            cmbKesJalanNafas.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString());
            cmbPernapasan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString());
            cmbReguler.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 32).toString());
            cmbGerakanDada.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 33).toString());
            cmbTipePernapasan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString());
            cmbKesPernapasan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString());
            cmbNadi1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString());
            cmbKulit.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString());
            cmbAkral1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString());
            cmbCRT.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString());
            cmbKesSirkulasi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString());
            TgcsE.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString());
            TgcsV.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString());
            TgcsM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString());
            cmbPupil.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString());
            TDiam_kanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString());
            TDiam_kiri.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString());
            TReflek.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString());
            TMeningeal.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString());
            cmbLater.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString());
            deformitas = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString();
            contusio = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString();
            penetrasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString();
            tenderness = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString();
            swelling = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString();
            ekskoriasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString();
            abrasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString();
            burn = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString();
            laserasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString();
            tdk_tmpk_jls = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString();
            Talasan_msk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString());
            cmbCara_datang.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString());
            Trujukan_bidan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 62).toString());
            Tpkm.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString());
            Tspog.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 64).toString());
            Trs_lain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString());
            Tgr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 66).toString());
            Tpr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString());
            Ta.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString());
            Thamil.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString());
            Tket_dengan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString());            
            mengeluh_prt = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString();
            Valid.SetTgl2(TglPerutMules, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString());
            cmbKeluarLendir.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString());
            Valid.SetTgl2(TglLendirYa, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString());
            cmbKeluarAir.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString());
            cmbKeluarAirYa.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString());
            Valid.SetTgl2(TglAirYa, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString());
            cmbPeriksaBidan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString());
            THasilPeriksaBidan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 79).toString());
            cmbMengeluhPusing.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 80).toString());
            Valid.SetTgl2(TglPusing, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 81).toString());            
            cmbNyeriUlu.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 82).toString());
            Valid.SetTgl2(TglNyeri, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 83).toString());            
            cmbPandangan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 84).toString());
            Valid.SetTgl2(TglPandangan, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 85).toString());            
            cmbMual.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 86).toString());
            Valid.SetTgl2(TglMual, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 87).toString());            
            cmbBatuk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 88).toString());
            Valid.SetTgl2(TglBatuk, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 89).toString());            
            cmbDemam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 90).toString());
            Valid.SetTgl2(TglDemam, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 91).toString());
            cmbRiwJlnJauh.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 92).toString());
            TriwJlnJauh.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 93).toString());
            cmbOs.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString());
            Tspog1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 95).toString());
            TJspog1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 96).toString());
            Tspog2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 97).toString());
            TJspog2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 98).toString());            
            hipertensi1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 99).toString();
            dm1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 100).toString();
            jantung1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString();
            asma1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 102).toString();
            lainya1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 103).toString();
            TketLainya1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 104).toString());            
            hipertensi2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 105).toString();
            dm2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 106).toString();
            jantung2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 107).toString();
            asma2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 108).toString();
            lainya2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 109).toString();
            TketLainya2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 110).toString());
            TriwGine.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 111).toString());
            pil = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 112).toString();
            Tpil.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 113).toString());
            cmbSatuanPil.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 114).toString());            
            suntik1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 115).toString();
            Tsuntik1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 116).toString());
            cmbSatuanSuntik1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 117).toString());            
            suntik3 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 118).toString();
            Tsuntik3.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 119).toString());
            cmbSatuanSuntik3.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 120).toString());            
            implan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 121).toString();
            Timplan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 122).toString());
            cmbSatuanImplan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 123).toString());
            TriwKBlain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 124).toString());            
            Thpht.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 125).toString());
            Thpl.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 126).toString());
            Tuk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 127).toString());
            Tukusg.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 128).toString());
            Valid.SetTgl(tglUSG, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 129).toString());
            TbbBlmHamil.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 130).toString());
            TbbStlhHamil.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 131).toString());            
            Ttbi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 132).toString());
            Tbmi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 133).toString());            
            Tlila.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 134).toString());            
            dismen = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 135).toString();
            spoting = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 136).toString();
            menor = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 137).toString();
            metro = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 138).toString();
            TkeluhanLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 139).toString());
            Tleopold1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 140).toString());
            Tleopold2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 141).toString());
            Tleopold3.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 142).toString());
            Tleopold4.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 143).toString());
            nyeri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 144).toString();
            bandle = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 145).toString();
            Ttfu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 146).toString());
            TbbJanin.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 147).toString());
            his = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 148).toString();
            ThisKontraksi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 149).toString());            
            teratur = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 150).toString();
            tdk_teratur = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 151).toString();
            terus = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 152).toString();
            Tdurasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 153).toString());            
            kuat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 154).toString();
            sedang = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 155).toString();
            lemah = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 156).toString();
            Tauskultasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 157).toString());            
            bersih = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 158).toString();
            odema = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 159).toString();
            ruptur = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 160).toString();
            candi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 161).toString();
            Tgenitalia_lain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 162).toString());
            Tinspeksi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 163).toString());
            cmbKonsistensi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 164).toString());
            Tperiksa_dlm.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 165).toString());
            Tinspekulum.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 166).toString());
            TDiagSementara.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 167).toString());
            Ticd.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 168).toString());
            cmbRencana.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 169).toString());
            Tplaning.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 170).toString());
            Tedukasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 171).toString());
            Trencana.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 172).toString());
            kdpetugas.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 173).toString());
            nmpetugas.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 194).toString());
            nmpenerima.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 174).toString());
            kddokter.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 175).toString());
            nmdokter.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 195).toString());
            Valid.SetTgl2(TglKeluar, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 176).toString());
            cmbRuangan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 177).toString());
            Tindikasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 178).toString());
            Tdipulangkan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 179).toString());
            Tdirujuk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 180).toString());
            TAlasanDirujuk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 181).toString());
            meninggal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 182).toString();
            cmbJam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 183).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 183).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 183).toString().substring(6, 8));
            Tpenyebab.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 184).toString());
            Tku.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 185).toString());
            Ttd.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 186).toString());
            Thr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 187).toString());
            Trr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 188).toString());
            Ttemp.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 189).toString());
            Tspo2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 190).toString());
            Tgcs.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 191).toString());
            KdBidan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 192).toString());
            NmBidan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 196).toString());
            kddpjp.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 193).toString());
            nmdpjp.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 197).toString());   
            cmbNadi2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 198).toString());
            cmbAkral2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 199).toString());            
            jamkeluar = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 200).toString();
            Valid.SetTgl(TglKeluar, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 201).toString());            
            cmbJam1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 202).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 202).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 202).toString().substring(6, 8));
            dataCek();
        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement("select rp.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgl_lahir, "
                    +"rp.tgl_registrasi, ifnull(r.rujuk_ke,'') rujuk_ke, ifnull(r.keterangan,'') ket_rujuk, "
                    + "ifnull(pm.jam,'00:00:00') jam_mati, IFNULL(pa.tensi, '') td, "
                    + "IFNULL(pa.respirasi, '') nafas, IFNULL(pa.suhu_tubuh, '') suhu FROM reg_periksa rp "
                    + "INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis left join rujuk r on r.no_rawat=rp.no_rawat "
                    + "left join pasien_mati pm on pm.no_rkm_medis=rp.no_rkm_medis LEFT JOIN pemeriksaan_ralan pa ON pa.no_rawat = rp.no_rawat "
                    + "WHERE rp.no_rawat = ?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TglLahir.setText(rs.getString("tgl_lahir")); 
                    Tdirujuk.setText(rs.getString("rujuk_ke"));
                    TAlasanDirujuk.setText(rs.getString("ket_rujuk"));
                    
                    Ttd.setText(rs.getString("td"));
                    Trr.setText(rs.getString("nafas"));
                    Ttemp.setText(rs.getString("suhu"));
                    
                    if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + rs.getString("no_rkm_medis") + "'") == 0) {
                        ChkMeninggal.setSelected(false);
                        cmbJam.setSelectedIndex(0);
                        cmbMnt.setSelectedIndex(0);
                        cmbDtk.setSelectedIndex(0);
                        
                        cmbJam.setEnabled(false);
                        cmbMnt.setEnabled(false);
                        cmbDtk.setEnabled(false);
                    } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + rs.getString("no_rkm_medis") + "'") > 0) {
                        ChkMeninggal.setSelected(true);
                        cmbJam.setSelectedItem(rs.getString("jam_mati").toString().substring(0, 2));
                        cmbMnt.setSelectedItem(rs.getString("jam_mati").toString().substring(3, 5));
                        cmbDtk.setSelectedItem(rs.getString("jam_mati").toString().substring(6, 8));
                        
                        cmbJam.setEnabled(true);
                        cmbMnt.setEnabled(true);
                        cmbDtk.setEnabled(true);
                    }                    
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
            System.out.println("Notif : " + e);
        }
    }
    
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);
        isRawat();
        tampil();        
    }    
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getpenilaian_awal_medis_ralan_kebidanan());
        BtnHapus.setEnabled(akses.getpenilaian_awal_medis_ralan_kebidanan());
        BtnEdit.setEnabled(akses.getpenilaian_awal_medis_ralan_kebidanan());
        BtnPrint.setEnabled(akses.getpenilaian_awal_medis_ralan_kebidanan());
        BtnResep.setEnabled(akses.getresep_dokter());
        
        if (akses.getjml2() >= 1) {            
            BtnDpjp.setEnabled(false);            
            kddpjp.setText(akses.getkode());
            kddokter.setText(akses.getkode());
            Sequel.cariIsi("select nama from petugas where nip=?", nmdpjp, kddpjp.getText());
            nmdokter.setText(nmdpjp.getText());
            if (nmdpjp.getText().equals("")) {
                kddpjp.setText("");
                kddokter.setText("");
                JOptionPane.showMessageDialog(null, "User login bukan dokter...!!");
            }
        }        
    }

    public void setTampil(){
       TabRawat.setSelectedIndex(1);
       tampil();
    }
    
    private void ganti() {
        cekData();        
        if (Sequel.mengedittf("penilaian_awal_medis_obstetri_ralan", "no_rawat=?", "no_rawat=?, tanggal=?, cervival=?, rjp=?, defribilasi=?, intubasi=?, vtp=?, "
                + "dekompresi=?, balut=?, kateter=?, ngt=?, infus=?, obat=?, ket_obat=?, tidak_ada=?, paten=?, gangguan_jalan_nafas=?, obstruksi_partial=?, "
                + "data_obstruksi_partial=?, obstruksi_total=?, trauma_jalan_nafas=?, trauma=?, resiko_aspirasi=?, aspirasi=?, benda_asing=?, ket_benda_asing=?, "
                + "kesimpulan_jalan_nafas=?, pernafasan=?, data_pernafasan=?, gerakan_dada=?, tipe_pernafasan=?, kesimpulan_pernafasan=?, nadi_1=?, kulit_mukosa=?, "
                + "akral_1=?, crt=?, kesimpulan_sirkulasi=?, gcs_e=?, gcs_v=?, gcs_m=?, pupil=?, diameter_kanan=?, diameter_kiri=?, reflek_cahaya=?, meningeal_sign=?, "
                + "lateralisasi=?, deformitas=?, contusio=?, penetrasi=?, tenderness=?, swelling=?, ekskoriasi=?, abrasi=?, burn=?, laserasi=?, tidak_tampak_jelas=?, "
                + "alasan_masuk=?, cara_datang=?, ket_rujukan_bidan=?, ket_pkm=?, ket_spog=?, ket_rs_lain=?, dengan_gr=?, dengan_pr=?, dengan_a=?, hamil=?, ket_dengan=?, "
                + "cek_mengeluh_perut=?, tgl_mengeluh_perut=?, keluar_lendir=?, tgl_lendir_ya=?, keluar_air=?, keluar_air_ya=?, tgl_air_ya=?, periksa_bidan=?, "
                + "hasil_periksa_bidan=?, mengeluh_pusing=?, tgl_pusing=?, nyeri_ulu_hati=?, tgl_nyeri=?, pandangan_kabur=?, tgl_pandangan=?, mual_muntah=?, "
                + "tgl_mual_muntah=?, batuk_pilek=?, tgl_batuk_pilek=?, demam=?, tgl_demam=?, riw_jalan_jauh=?, ket_riw_jalan_jauh=?, os_anc_bidan=?, dengan_spog1=?, "
                + "jml_spog1=?, dengan_spog2=?, jml_spog2=?, hipertensi1=?, diabetes1=?, jantung1=?, asma1=?, lainnya1=?, ket_lainnya1=?, hipertensi2=?, diabetes2=?, "
                + "jantung2=?, asma2=?, lainnya2=?, ket_lainnya2=?, riw_ginekologi=?, pil=?, lama_pil=?, satuan_pil=?, suntik_1_bln=?, lama_1_bln=?, satuan_suntik1=?, "
                + "suntik_3_bln=?, lama_3_bln=?, satuan_suntik3=?, implan=?, lama_implan=?, satuan_implan=?, riwayat_kb_lain=?, hpht=?, hpl=?, uk=?, uk_usg=?, tgl_usg=?, "
                + "bb_blm_hamil=?, bb_stlh_hamil=?, tbi=?, bmi=?, lila=?, dismenorhoe=?, spoting=?, menorrhagia=?, metrohagia=?, keluhan_lain=?, leopold1=?, leopold2=?, "
                + "leopold3=?, leopold4=?, nyeri_tekan=?, bandle_ring=?, tfu=?, taksiran_bb_janin=?, his_kontraksi=?, ket_his_kontraksi=?, teratur=?, tdk_teratur=?, "
                + "trs_menerus=?, durasi=?, kuat=?, sedang=?, lemah=?, auskultasi=?, bersih=?, oedema=?, ruptur=?, candiloma=?, pemeriksaan_genitalia_lain=?, inspeksi=?, "
                + "konsistensi=?, periksa_dlm_obstetri=?, inspekulum=?, diagnosis_sementara=?, icd_10=?, rencana_instruksi=?, planing=?, telah_diberikan_informasi_edukasi=?, "
                + "rencana_asuhan_diharapkan=?, nip_pemberi_edukasi=?, penerima_edukasi=?, nip_dokter=?, tgl_keluar_ponek=?, opname_diruang=?, indikasi_msk=?, "
                + "dipulangkan_kontrol_ke=?, dirujuk_ke=?, alasan_dirujuk=?, meninggal=?, jam_meninggal=?, penyebab=?, k_u=?, td=?, hr=?, rr=?, temp=?, spo2=?, gcs_pulang=?, "
                + "nip_bidan=?, nip_dpjp=?, nadi_2=?, akral_2=?, cek_jam_keluar=?", 194, new String[]{
                    TNoRw.getText(), Valid.SetTgl(TglAsesmen.getSelectedItem() + "") + " " + TglAsesmen.getSelectedItem().toString().substring(11, 19), cervical, rjp, defribrilasi, intubasi, vtp, dekompresi, balut, kateter, ngt, infus, obat,
                    TObat.getText(), tidakada, paten, ganggnafas, obsP, cmbObstruksi.getSelectedItem().toString(), obsT, traumaJlnNfs, cmbTrauma.getSelectedItem().toString(), resikoAspirasi, cmbResiko.getSelectedItem().toString(), bendaAsing, TBendaAsing.getText(),
                    cmbKesJalanNafas.getSelectedItem().toString(), cmbPernapasan.getSelectedItem().toString(), cmbReguler.getSelectedItem().toString(), cmbGerakanDada.getSelectedItem().toString(), cmbTipePernapasan.getSelectedItem().toString(),
                    cmbKesPernapasan.getSelectedItem().toString(), cmbNadi1.getSelectedItem().toString(), cmbKulit.getSelectedItem().toString(), cmbAkral1.getSelectedItem().toString(), cmbCRT.getSelectedItem().toString(), cmbKesSirkulasi.getSelectedItem().toString(),
                    TgcsE.getText(), TgcsV.getText(), TgcsM.getText(), cmbPupil.getSelectedItem().toString(), TDiam_kanan.getText(), TDiam_kiri.getText(), TReflek.getText(), TMeningeal.getText(), cmbLater.getSelectedItem().toString(),
                    deformitas, contusio, penetrasi, tenderness, swelling, ekskoriasi, abrasi, burn, laserasi, tdk_tmpk_jls, Talasan_msk.getText(), cmbCara_datang.getSelectedItem().toString(), Trujukan_bidan.getText(), Tpkm.getText(), Tspog.getText(),
                    Trs_lain.getText(), Tgr.getText(), Tpr.getText(), Ta.getText(), Thamil.getText(), Tket_dengan.getText(), mengeluh_prt, Valid.SetTgl(TglPerutMules.getSelectedItem() + "") + " " + TglPerutMules.getSelectedItem().toString().substring(11, 19),
                    cmbKeluarLendir.getSelectedItem().toString(), Valid.SetTgl(TglLendirYa.getSelectedItem() + "") + " " + TglLendirYa.getSelectedItem().toString().substring(11, 19), cmbKeluarAir.getSelectedItem().toString(), cmbKeluarAirYa.getSelectedItem().toString(),
                    Valid.SetTgl(TglAirYa.getSelectedItem() + "") + " " + TglAirYa.getSelectedItem().toString().substring(11, 19), cmbPeriksaBidan.getSelectedItem().toString(), THasilPeriksaBidan.getText(), cmbMengeluhPusing.getSelectedItem().toString(),
                    Valid.SetTgl(TglPusing.getSelectedItem() + "") + " " + TglPusing.getSelectedItem().toString().substring(11, 19), cmbNyeriUlu.getSelectedItem().toString(), Valid.SetTgl(TglNyeri.getSelectedItem() + "") + " " + TglNyeri.getSelectedItem().toString().substring(11, 19),
                    cmbPandangan.getSelectedItem().toString(), Valid.SetTgl(TglPandangan.getSelectedItem() + "") + " " + TglPandangan.getSelectedItem().toString().substring(11, 19), cmbMual.getSelectedItem().toString(), Valid.SetTgl(TglMual.getSelectedItem() + "") + " " + TglMual.getSelectedItem().toString().substring(11, 19),
                    cmbBatuk.getSelectedItem().toString(), Valid.SetTgl(TglBatuk.getSelectedItem() + "") + " " + TglBatuk.getSelectedItem().toString().substring(11, 19), cmbDemam.getSelectedItem().toString(), Valid.SetTgl(TglDemam.getSelectedItem() + "") + " " + TglDemam.getSelectedItem().toString().substring(11, 19),
                    cmbRiwJlnJauh.getSelectedItem().toString(), TriwJlnJauh.getText(), cmbOs.getSelectedItem().toString(), Tspog1.getText(), TJspog1.getText(), Tspog2.getText(), TJspog2.getText(), hipertensi1, dm1, jantung1, asma1, lainya1, TketLainya1.getText(),
                    hipertensi2, dm2, jantung2, asma2, lainya2, TketLainya2.getText(), TriwGine.getText(), pil, Tpil.getText(), cmbSatuanPil.getSelectedItem().toString(), suntik1, Tsuntik1.getText(), cmbSatuanSuntik1.getSelectedItem().toString(), suntik3, Tsuntik3.getText(),
                    cmbSatuanSuntik3.getSelectedItem().toString(), implan, Timplan.getText(), cmbSatuanImplan.getSelectedItem().toString(), TriwKBlain.getText(), Thpht.getText(), Thpl.getText(), Tuk.getText(), Tukusg.getText(), Valid.SetTgl(tglUSG.getSelectedItem() + ""),
                    TbbBlmHamil.getText(), TbbStlhHamil.getText(), Ttbi.getText(), Tbmi.getText(), Tlila.getText(), dismen, spoting, menor, metro, TkeluhanLain.getText(), Tleopold1.getText(), Tleopold2.getText(), Tleopold3.getText(), Tleopold4.getText(),
                    nyeri, bandle, Ttfu.getText(), TbbJanin.getText(), his, ThisKontraksi.getText(), teratur, tdk_teratur, terus, Tdurasi.getText(), kuat, sedang, lemah, Tauskultasi.getText(), bersih, odema, ruptur, candi, Tgenitalia_lain.getText(),
                    Tinspeksi.getText(), cmbKonsistensi.getSelectedItem().toString(), Tperiksa_dlm.getText(), Tinspekulum.getText(), TDiagSementara.getText(), Ticd.getText(), cmbRencana.getSelectedItem().toString(), Tplaning.getText(), Tedukasi.getText(),
                    Trencana.getText(), kdpetugas.getText(), nmpenerima.getText(), kddokter.getText(), Valid.SetTgl(TglKeluar.getSelectedItem() + "") + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), cmbRuangan.getSelectedItem().toString(),
                    Tindikasi.getText(), Tdipulangkan.getText(), Tdirujuk.getText(), TAlasanDirujuk.getText(), meninggal, cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), Tpenyebab.getText(), Tku.getText(),
                    Ttd.getText(), Thr.getText(), Trr.getText(), Ttemp.getText(), Tspo2.getText(), Tgcs.getText(), KdBidan.getText(), kddpjp.getText(), cmbNadi2.getSelectedItem().toString(), cmbAkral2.getSelectedItem().toString(), jamkeluar,
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                }) == true) {
            TCari.setText(TNoRw.getText());
            tampil();
            Ttd.setText("");
            Trr.setText("");
            Ttemp.setText("");
            emptTeks();
            ChkMeninggal.setSelected(false);
            cmbJam.setSelectedIndex(0);
            cmbMnt.setSelectedIndex(0);
            cmbDtk.setSelectedIndex(0);

            cmbJam.setEnabled(false);
            cmbMnt.setEnabled(false);
            cmbDtk.setEnabled(false);
            TabRawat.setSelectedIndex(1);
        }
    }

    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from penilaian_awal_medis_obstetri_ralan where no_rawat=?", 1, new String[]{
                tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
            }) == true) {                             
                tampil();
                Ttd.setText("");
                Trr.setText("");
                Ttemp.setText("");
                emptTeks();
                ChkMeninggal.setSelected(false);
                cmbJam.setSelectedIndex(0);
                cmbMnt.setSelectedIndex(0);
                cmbDtk.setSelectedIndex(0);

                cmbJam.setEnabled(false);
                cmbMnt.setEnabled(false);
                cmbDtk.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        }
    }
    
    private void simpan() {
        cekData();        
        if (Sequel.menyimpantf("penilaian_awal_medis_obstetri_ralan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 194, new String[]{
            TNoRw.getText(), Valid.SetTgl(TglAsesmen.getSelectedItem() + "") + " " + TglAsesmen.getSelectedItem().toString().substring(11, 19), cervical, rjp, defribrilasi, intubasi, vtp, dekompresi, balut, kateter, ngt, infus, obat,
            TObat.getText(), tidakada, paten, ganggnafas, obsP, cmbObstruksi.getSelectedItem().toString(), obsT, traumaJlnNfs, cmbTrauma.getSelectedItem().toString(), resikoAspirasi, cmbResiko.getSelectedItem().toString(), bendaAsing, TBendaAsing.getText(),
            cmbKesJalanNafas.getSelectedItem().toString(), cmbPernapasan.getSelectedItem().toString(), cmbReguler.getSelectedItem().toString(), cmbGerakanDada.getSelectedItem().toString(), cmbTipePernapasan.getSelectedItem().toString(), 
            cmbKesPernapasan.getSelectedItem().toString(), cmbNadi1.getSelectedItem().toString(), cmbKulit.getSelectedItem().toString(), cmbAkral1.getSelectedItem().toString(), cmbCRT.getSelectedItem().toString(), cmbKesSirkulasi.getSelectedItem().toString(), 
            TgcsE.getText(), TgcsV.getText(), TgcsM.getText(), cmbPupil.getSelectedItem().toString(), TDiam_kanan.getText(), TDiam_kiri.getText(), TReflek.getText(), TMeningeal.getText(), cmbLater.getSelectedItem().toString(),
            deformitas, contusio, penetrasi, tenderness, swelling, ekskoriasi, abrasi, burn, laserasi, tdk_tmpk_jls, Talasan_msk.getText(), cmbCara_datang.getSelectedItem().toString(), Trujukan_bidan.getText(), Tpkm.getText(), Tspog.getText(),
            Trs_lain.getText(), Tgr.getText(), Tpr.getText(), Ta.getText(), Thamil.getText(), Tket_dengan.getText(), mengeluh_prt, Valid.SetTgl(TglPerutMules.getSelectedItem() + "") + " " + TglPerutMules.getSelectedItem().toString().substring(11, 19), 
            cmbKeluarLendir.getSelectedItem().toString(), Valid.SetTgl(TglLendirYa.getSelectedItem() + "") + " " + TglLendirYa.getSelectedItem().toString().substring(11, 19), cmbKeluarAir.getSelectedItem().toString(), cmbKeluarAirYa.getSelectedItem().toString(), 
            Valid.SetTgl(TglAirYa.getSelectedItem() + "") + " " + TglAirYa.getSelectedItem().toString().substring(11, 19), cmbPeriksaBidan.getSelectedItem().toString(), THasilPeriksaBidan.getText(), cmbMengeluhPusing.getSelectedItem().toString(), 
            Valid.SetTgl(TglPusing.getSelectedItem() + "") + " " + TglPusing.getSelectedItem().toString().substring(11, 19), cmbNyeriUlu.getSelectedItem().toString(), Valid.SetTgl(TglNyeri.getSelectedItem() + "") + " " + TglNyeri.getSelectedItem().toString().substring(11, 19),
            cmbPandangan.getSelectedItem().toString(), Valid.SetTgl(TglPandangan.getSelectedItem() + "") + " " + TglPandangan.getSelectedItem().toString().substring(11, 19), cmbMual.getSelectedItem().toString(), Valid.SetTgl(TglMual.getSelectedItem() + "") + " " + TglMual.getSelectedItem().toString().substring(11, 19), 
            cmbBatuk.getSelectedItem().toString(), Valid.SetTgl(TglBatuk.getSelectedItem() + "") + " " + TglBatuk.getSelectedItem().toString().substring(11, 19), cmbDemam.getSelectedItem().toString(), Valid.SetTgl(TglDemam.getSelectedItem() + "") + " " + TglDemam.getSelectedItem().toString().substring(11, 19), 
            cmbRiwJlnJauh.getSelectedItem().toString(), TriwJlnJauh.getText(), cmbOs.getSelectedItem().toString(), Tspog1.getText(), TJspog1.getText(), Tspog2.getText(), TJspog2.getText(), hipertensi1, dm1, jantung1, asma1, lainya1, TketLainya1.getText(),
            hipertensi2, dm2, jantung2, asma2, lainya2, TketLainya2.getText(), TriwGine.getText(), pil, Tpil.getText(), cmbSatuanPil.getSelectedItem().toString(), suntik1, Tsuntik1.getText(), cmbSatuanSuntik1.getSelectedItem().toString(), suntik3, Tsuntik3.getText(), 
            cmbSatuanSuntik3.getSelectedItem().toString(), implan, Timplan.getText(), cmbSatuanImplan.getSelectedItem().toString(), TriwKBlain.getText(), Thpht.getText(), Thpl.getText(), Tuk.getText(), Tukusg.getText(), Valid.SetTgl(tglUSG.getSelectedItem() + ""), 
            TbbBlmHamil.getText(), TbbStlhHamil.getText(), Ttbi.getText(), Tbmi.getText(), Tlila.getText(), dismen, spoting, menor, metro, TkeluhanLain.getText(), Tleopold1.getText(), Tleopold2.getText(), Tleopold3.getText(), Tleopold4.getText(), 
            nyeri, bandle, Ttfu.getText(), TbbJanin.getText(), his, ThisKontraksi.getText(), teratur, tdk_teratur, terus, Tdurasi.getText(), kuat, sedang, lemah, Tauskultasi.getText(), bersih, odema, ruptur, candi, Tgenitalia_lain.getText(), 
            Tinspeksi.getText(), cmbKonsistensi.getSelectedItem().toString(), Tperiksa_dlm.getText(), Tinspekulum.getText(), TDiagSementara.getText(), Ticd.getText(), cmbRencana.getSelectedItem().toString(), Tplaning.getText(), Tedukasi.getText(), 
            Trencana.getText(), kdpetugas.getText(), nmpenerima.getText(), kddokter.getText(), Valid.SetTgl(TglKeluar.getSelectedItem() + "") + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), cmbRuangan.getSelectedItem().toString(),
            Tindikasi.getText(), Tdipulangkan.getText(), Tdirujuk.getText(), TAlasanDirujuk.getText(), meninggal, cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), Tpenyebab.getText(), Tku.getText(), 
            Ttd.getText(), Thr.getText(), Trr.getText(), Ttemp.getText(), Tspo2.getText(), Tgcs.getText(), KdBidan.getText(), kddpjp.getText(), cmbNadi2.getSelectedItem().toString(), cmbAkral2.getSelectedItem().toString(), jamkeluar,
            Sequel.cariIsi("select now()")
        }) == true) {
            Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "stts='Sudah Diperiksa Dokter'");
            TCari.setText(TNoRw.getText());
            Ttd.setText("");
            Trr.setText("");
            Ttemp.setText("");
            emptTeks();
            ChkMeninggal.setSelected(false);
            cmbJam.setSelectedIndex(0);
            cmbMnt.setSelectedIndex(0);
            cmbDtk.setSelectedIndex(0);

            cmbJam.setEnabled(false);
            cmbMnt.setEnabled(false);
            cmbDtk.setEnabled(false);            
            TabRawat.setSelectedIndex(1);
            tampil();
        }
    }
    
    private void cekData() {        
        if (ChkCervical.isSelected() == true) {
            cervical = "ya";
        } else {
            cervical = "tidak";
        }
        
        if (ChkRJP.isSelected() == true) {
            rjp = "ya";
        } else {
            rjp = "tidak";
        }
        
        if (ChkDefribilasi.isSelected() == true) {
            defribrilasi = "ya";
        } else {
            defribrilasi = "tidak";
        }
        
        if (ChkIntubasi.isSelected() == true) {
            intubasi = "ya";
        } else {
            intubasi = "tidak";
        }
        
        if (ChkVTP.isSelected() == true) {
            vtp = "ya";
        } else {
            vtp = "tidak";
        }
        
        if (ChkDekompresi.isSelected() == true) {
            dekompresi = "ya";
        } else {
            dekompresi = "tidak";
        }
        
        if (ChkBalut.isSelected() == true) {
            balut = "ya";
        } else {
            balut = "tidak";
        }
        
        if (ChkKateter.isSelected() == true) {
            kateter = "ya";
        } else {
            kateter = "tidak";
        }
        
        if (ChkNGT.isSelected() == true) {
            ngt = "ya";
        } else {
            ngt = "tidak";
        }
        
        if (ChkInfus.isSelected() == true) {
            infus = "ya";
        } else {
            infus = "tidak";
        }
        
        if (ChkObat.isSelected() == true) {
            obat = "ya";
        } else {
            obat = "tidak";
        }
        
        if (ChkTidak.isSelected() == true) {
            tidakada = "ya";
        } else {
            tidakada = "tidak";
        }
        
        if (ChkGangNafas.isSelected() == true) {
            ganggnafas = "ya";
        } else {
            ganggnafas = "tidak";
        }
        
        if (ChkPaten.isSelected() == true) {
            paten = "ya";
        } else {
            paten = "tidak";
        }
        
        if (ChkObstruksiP.isSelected() == true) {
            obsP = "ya";
        } else {
            obsP = "tidak";
        }
        
        if (ChkObstruksiT.isSelected() == true) {
            obsT = "ya";
        } else {
            obsT = "tidak";
        }
        
        if (ChkTrauma.isSelected() == true) {
            traumaJlnNfs = "ya";
        } else {
            traumaJlnNfs = "tidak";
        }
        
        if (ChkResiko.isSelected() == true) {
            resikoAspirasi = "ya";
        } else {
            resikoAspirasi = "tidak";
        }
        
        if (ChkBendaAsing.isSelected() == true) {
            bendaAsing = "ya";
        } else {
            bendaAsing = "tidak";
        }
        
        if (ChkDeformitas.isSelected() == true) {
            deformitas = "ya";
        } else {
            deformitas = "tidak";
        }
        
        if (ChkContusio.isSelected() == true) {
            contusio = "ya";
        } else {
            contusio = "tidak";
        }
        
        if (ChkPenetrasi.isSelected() == true) {
            penetrasi = "ya";
        } else {
            penetrasi = "tidak";
        }
        
        if (ChkTenderness.isSelected() == true) {
            tenderness = "ya";
        } else {
            tenderness = "tidak";
        }
        
        if (ChkSwelling.isSelected() == true) {
            swelling = "ya";
        } else {
            swelling = "tidak";
        }
        
        if (ChkEkskoriasi.isSelected() == true) {
            ekskoriasi = "ya";
        } else {
            ekskoriasi = "tidak";
        }
        
        if (ChkAbrasi.isSelected() == true) {
            abrasi = "ya";
        } else {
            abrasi = "tidak";
        }
        
        if (ChkBurn.isSelected() == true) {
            burn = "ya";
        } else {
            burn = "tidak";
        }
        
        if (ChkLaserasi.isSelected() == true) {
            laserasi = "ya";
        } else {
            laserasi = "tidak";
        }
        
        if (ChkTdkTampk.isSelected() == true) {
            tdk_tmpk_jls = "ya";
        } else {
            tdk_tmpk_jls = "tidak";
        }
        
        if (ChkPerutMules.isSelected() == true) {
            mengeluh_prt = "ya";
        } else {
            mengeluh_prt = "tidak";
        }
        
        if (ChkHipertensi1.isSelected() == true) {
            hipertensi1 = "ya";
        } else {
            hipertensi1 = "tidak";
        }
        
        if (ChkDM1.isSelected() == true) {
            dm1 = "ya";
        } else {
            dm1 = "tidak";
        }
        
        if (ChkJantung1.isSelected() == true) {
            jantung1 = "ya";
        } else {
            jantung1 = "tidak";
        }
        
        if (ChkAsma1.isSelected() == true) {
            asma1 = "ya";
        } else {
            asma1 = "tidak";
        }
        
        if (ChkLainya1.isSelected() == true) {
            lainya1 = "ya";
        } else {
            lainya1 = "tidak";
        }
        
        if (ChkHipertensi2.isSelected() == true) {
            hipertensi2 = "ya";
        } else {
            hipertensi2 = "tidak";
        }
        
        if (ChkDM2.isSelected() == true) {
            dm2 = "ya";
        } else {
            dm2 = "tidak";
        }
        
        if (ChkJantung2.isSelected() == true) {
            jantung2 = "ya";
        } else {
            jantung2 = "tidak";
        }
        
        if (ChkAsma2.isSelected() == true) {
            asma2 = "ya";
        } else {
            asma2 = "tidak";
        }
        
        if (ChkLainya2.isSelected() == true) {
            lainya2 = "ya";
        } else {
            lainya2 = "tidak";
        }
        
        if (ChkPil.isSelected() == true) {
            pil = "ya";
        } else {
            pil = "tidak";
        }
        
        if (ChkSuntik1.isSelected() == true) {
            suntik1 = "ya";
        } else {
            suntik1 = "tidak";
        }

        if (ChkSuntik3.isSelected() == true) {
            suntik3 = "ya";
        } else {
            suntik3 = "tidak";
        }
        
        if (ChkImplan.isSelected() == true) {
            implan = "ya";
        } else {
            implan = "tidak";
        }
        
        if (ChkDismen.isSelected() == true) {
            dismen = "ya";
        } else {
            dismen = "tidak";
        }
        
        if (ChkSpoting.isSelected() == true) {
            spoting = "ya";
        } else {
            spoting = "tidak";
        }
        
        if (ChkMenor.isSelected() == true) {
            menor = "ya";
        } else {
            menor = "tidak";
        }
        
        if (ChkMetro.isSelected() == true) {
            metro = "ya";
        } else {
            metro = "tidak";
        }
        
        if (ChkNyeriTekan.isSelected() == true) {
            nyeri = "ya";
        } else {
            nyeri = "tidak";
        }
        
        if (ChkBandle.isSelected() == true) {
            bandle = "ya";
        } else {
            bandle = "tidak";
        }
        
        if (ChkHis.isSelected() == true) {
            his = "ya";
        } else {
            his = "tidak";
        }
        
        if (ChkTeratur.isSelected() == true) {
            teratur = "ya";
        } else {
            teratur = "tidak";
        }
        
        if (ChkTdkTeratur.isSelected() == true) {
            tdk_teratur = "ya";
        } else {
            tdk_teratur = "tidak";
        }
        
        if (ChkTrsMenerus.isSelected() == true) {
            terus = "ya";
        } else {
            terus = "tidak";
        }
        
        if (ChkKuat.isSelected() == true) {
            kuat = "ya";
        } else {
            kuat = "tidak";
        }
        
        if (ChkSedang.isSelected() == true) {
            sedang = "ya";
        } else {
            sedang = "tidak";
        }
        
        if (ChkLemah.isSelected() == true) {
            lemah = "ya";
        } else {
            lemah = "tidak";
        }
        
        if (ChkBersih.isSelected() == true) {
            bersih = "ya";
        } else {
            bersih = "tidak";
        }
        
        if (ChkOedema.isSelected() == true) {
            odema = "ya";
        } else {
            odema = "tidak";
        }
        
        if (ChkRuptur.isSelected() == true) {
            ruptur = "ya";
        } else {
            ruptur = "tidak";
        }
        
        if (ChkCandiloma.isSelected() == true) {
            candi = "ya";
        } else {
            candi = "tidak";
        }

        if (ChkMeninggal.isSelected() == true) {
            meninggal = "ya";
        } else {
            meninggal = "tidak";
        }
        
        if (ChkJamKeluar.isSelected() == true) {
            jamkeluar = "ya";
        } else {
            jamkeluar = "tidak";
        }
    }
    
    private void dataCek() {
        if (cervical.equals("ya")) {
            ChkCervical.setSelected(true);
        } else {
            ChkCervical.setSelected(false);
        }
        
        if (rjp.equals("ya")) {
            ChkRJP.setSelected(true);
        } else {
            ChkRJP.setSelected(false);
        }
        
        if (defribrilasi.equals("ya")) {
            ChkDefribilasi.setSelected(true);
        } else {
            ChkDefribilasi.setSelected(false);
        }
        
        if (intubasi.equals("ya")) {
            ChkIntubasi.setSelected(true);
        } else {
            ChkIntubasi.setSelected(false);
        }
        
        if (vtp.equals("ya")) {
            ChkVTP.setSelected(true);
        } else {
            ChkVTP.setSelected(false);
        }
        
        if (dekompresi.equals("ya")) {
            ChkDekompresi.setSelected(true);
        } else {
            ChkDekompresi.setSelected(false);
        }
        
        if (balut.equals("ya")) {
            ChkBalut.setSelected(true);
        } else {
            ChkBalut.setSelected(false);
        }
        
        if (kateter.equals("ya")) {
            ChkKateter.setSelected(true);
        } else {
            ChkKateter.setSelected(false);
        }
        
        if (ngt.equals("ya")) {
            ChkNGT.setSelected(true);
        } else {
            ChkNGT.setSelected(false);
        }
        
        if (infus.equals("ya")) {
            ChkInfus.setSelected(true);
        } else {
            ChkInfus.setSelected(false);
        }
        
        if (obat.equals("ya")) {
            ChkObat.setSelected(true);
        } else {
            ChkObat.setSelected(false);
        }
        
        if (tidakada.equals("ya")) {
            ChkTidak.setSelected(true);
        } else {
            ChkTidak.setSelected(false);
        }
        
        if (ganggnafas.equals("ya")) {
            ChkGangNafas.setSelected(true);
        } else {
            ChkGangNafas.setSelected(false);
        }
        
        if (paten.equals("ya")) {
            ChkPaten.setSelected(true);
        } else {
            ChkPaten.setSelected(false);
        }
        
        if (obsP.equals("ya")) {
            ChkObstruksiP.setSelected(true);
        } else {
            ChkObstruksiP.setSelected(false);
        }
        
        if (obsT.equals("ya")) {
            ChkObstruksiT.setSelected(true);
        } else {
            ChkObstruksiT.setSelected(false);
        }
        
        if (traumaJlnNfs.equals("ya")) {
            ChkTrauma.setSelected(true);
        } else {
            ChkTrauma.setSelected(false);
        }
        
        if (resikoAspirasi.equals("ya")) {
            ChkResiko.setSelected(true);
        } else {
            ChkResiko.setSelected(false);
        }
        
        if (bendaAsing.equals("ya")) {
            ChkBendaAsing.setSelected(true);
        } else {
            ChkBendaAsing.setSelected(false);
        }
        
        if (deformitas.equals("ya")) {
            ChkDeformitas.setSelected(true);
        } else {
            ChkDeformitas.setSelected(false);
        }
        
        if (contusio.equals("ya")) {
            ChkContusio.setSelected(true);
        } else {
            ChkContusio.setSelected(false);
        }
        
        if (penetrasi.equals("ya")) {
            ChkPenetrasi.setSelected(true);
        } else {
            ChkPenetrasi.setSelected(false);
        }
        
        if (tenderness.equals("ya")) {
            ChkTenderness.setSelected(true);
        } else {
            ChkTenderness.setSelected(false);
        }
        
        if (swelling.equals("ya")) {
            ChkSwelling.setSelected(true);
        } else {
            ChkSwelling.setSelected(false);
        }
        
        if (ekskoriasi.equals("ya")) {
            ChkEkskoriasi.setSelected(true);
        } else {
            ChkEkskoriasi.setSelected(false);
        }
        
        if (abrasi.equals("ya")) {
            ChkAbrasi.setSelected(true);
        } else {
            ChkAbrasi.setSelected(false);
        }
        
        if (burn.equals("ya")) {
            ChkBurn.setSelected(true);
        } else {
            ChkBurn.setSelected(false);
        }
        
        if (laserasi.equals("ya")) {
            ChkLaserasi.setSelected(true);
        } else {
            ChkLaserasi.setSelected(false);
        }
        
        if (tdk_tmpk_jls.equals("ya")) {
            ChkTdkTampk.setSelected(true);
        } else {
            ChkTdkTampk.setSelected(false);
        }
        
        if (mengeluh_prt.equals("ya")) {
            ChkPerutMules.setSelected(true);
        } else {
            ChkPerutMules.setSelected(false);
        }
                
        if (hipertensi1.equals("ya")) {
            ChkHipertensi1.setSelected(true);
        } else {
            ChkHipertensi1.setSelected(false);
        }
        
        if (dm1.equals("ya")) {
            ChkDM1.setSelected(true);
        } else {
            ChkDM1.setSelected(false);
        }
        
        if (jantung1.equals("ya")) {
            ChkJantung1.setSelected(true);
        } else {
            ChkJantung1.setSelected(false);
        }
        
        if (asma1.equals("ya")) {
            ChkAsma1.setSelected(true);
        } else {
            ChkAsma1.setSelected(false);
        }
        
        if (lainya1.equals("ya")) {
            ChkLainya1.setSelected(true);
        } else {
            ChkLainya1.setSelected(false);
        }
        
        if (hipertensi2.equals("ya")) {
            ChkHipertensi2.setSelected(true);
        } else {
            ChkHipertensi2.setSelected(false);
        }
        
        if (dm2.equals("ya")) {
            ChkDM2.setSelected(true);
        } else {
            ChkDM2.setSelected(false);
        }
        
        if (jantung2.equals("ya")) {
            ChkJantung2.setSelected(true);
        } else {
            ChkJantung2.setSelected(false);
        }
        
        if (asma2.equals("ya")) {
            ChkAsma2.setSelected(true);
        } else {
            ChkAsma2.setSelected(false);
        }
        
        if (lainya2.equals("ya")) {
            ChkLainya2.setSelected(true);
        } else {
            ChkLainya2.setSelected(false);
        }
        
        if (pil.equals("ya")) {
            ChkPil.setSelected(true);
        } else {
            ChkPil.setSelected(false);
        }
        
        if (suntik1.equals("ya")) {
            ChkSuntik1.setSelected(true);
        } else {
            ChkSuntik1.setSelected(false);
        }
        
        if (suntik3.equals("ya")) {
            ChkSuntik3.setSelected(true);
        } else {
            ChkSuntik3.setSelected(false);
        }

        if (implan.equals("ya")) {
            ChkImplan.setSelected(true);
        } else {
            ChkImplan.setSelected(false);
        }
        
        if (dismen.equals("ya")) {
            ChkDismen.setSelected(true);
        } else {
            ChkDismen.setSelected(false);
        }
        
        if (spoting.equals("ya")) {
            ChkSpoting.setSelected(true);
        } else {
            ChkSpoting.setSelected(false);
        }
        
        if (menor.equals("ya")) {
            ChkMenor.setSelected(true);
        } else {
            ChkMenor.setSelected(false);
        }
        
        if (metro.equals("ya")) {
            ChkMetro.setSelected(true);
        } else {
            ChkMetro.setSelected(false);
        }
        
        if (nyeri.equals("ya")) {
            ChkNyeriTekan.setSelected(true);
        } else {
            ChkNyeriTekan.setSelected(false);
        }
        
        if (bandle.equals("ya")) {
            ChkBandle.setSelected(true);
        } else {
            ChkBandle.setSelected(false);
        }
        
        if (his.equals("ya")) {
            ChkHis.setSelected(true);
        } else {
            ChkHis.setSelected(false);
        }
        
        if (teratur.equals("ya")) {
            ChkTeratur.setSelected(true);
        } else {
            ChkTeratur.setSelected(false);
        }
        
        if (tdk_teratur.equals("ya")) {
            ChkTdkTeratur.setSelected(true);
        } else {
            ChkTdkTeratur.setSelected(false);
        }
        
        if (terus.equals("ya")) {
            ChkTrsMenerus.setSelected(true);
        } else {
            ChkTrsMenerus.setSelected(false);
        }
        
        if (kuat.equals("ya")) {
            ChkKuat.setSelected(true);
        } else {
            ChkKuat.setSelected(false);
        }
        
        if (sedang.equals("ya")) {
            ChkSedang.setSelected(true);
        } else {
            ChkSedang.setSelected(false);
        }
        
        if (lemah.equals("ya")) {
            ChkLemah.setSelected(true);
        } else {
            ChkLemah.setSelected(false);
        }
        
        if (bersih.equals("ya")) {
            ChkBersih.setSelected(true);
        } else {
            ChkBersih.setSelected(false);
        }
        
        if (odema.equals("ya")) {
            ChkOedema.setSelected(true);
        } else {
            ChkOedema.setSelected(false);
        }
        
        if (ruptur.equals("ya")) {
            ChkRuptur.setSelected(true);
        } else {
            ChkRuptur.setSelected(false);
        }
        
        if (candi.equals("ya")) {
            ChkCandiloma.setSelected(true);
        } else {
            ChkCandiloma.setSelected(false);
        }
        
        if (meninggal.equals("ya")) {
            ChkMeninggal.setSelected(true);
        } else {
            ChkMeninggal.setSelected(false);
        }
        
        if (jamkeluar.equals("ya")) {
            ChkJamKeluar.setSelected(true);
        } else {
            ChkJamKeluar.setSelected(false);
        }
        
        //------------------------------------------------------------
        if (ChkObat.isSelected() == true) {
            TObat.setEnabled(true);
        } else {
            TObat.setText("");
            TObat.setEnabled(false);
        }
        
        if (ChkTrauma.isSelected() == true) {
            cmbTrauma.setEnabled(true);
        } else {
            cmbTrauma.setSelectedIndex(0);
            cmbTrauma.setEnabled(false);
        }
        
        if (ChkResiko.isSelected() == true) {
            cmbResiko.setEnabled(true);
        } else {
            cmbResiko.setSelectedIndex(0);
            cmbResiko.setEnabled(false);
        }
        
        if (ChkBendaAsing.isSelected() == true) {
            TBendaAsing.setEnabled(true);
        } else {
            TBendaAsing.setText("");
            TBendaAsing.setEnabled(false);
        }
        
        if (cmbCara_datang.getSelectedIndex() == 2) {
            Trujukan_bidan.setEnabled(true);
            Tpkm.setEnabled(false);
            Tspog.setEnabled(false);
            Trs_lain.setEnabled(false);            
            
            Tpkm.setText("");
            Tspog.setText("");
            Trs_lain.setText("");
        } else if (cmbCara_datang.getSelectedIndex() == 3) {
            Trujukan_bidan.setEnabled(false);
            Tpkm.setEnabled(true);
            Tspog.setEnabled(false);
            Trs_lain.setEnabled(false);
            
            Trujukan_bidan.setText("");            
            Tspog.setText("");
            Trs_lain.setText("");
        } else if (cmbCara_datang.getSelectedIndex() == 4) {
            Trujukan_bidan.setEnabled(false);
            Tpkm.setEnabled(false);
            Tspog.setEnabled(true);
            Trs_lain.setEnabled(false);
            
            Trujukan_bidan.setText("");
            Tpkm.setText("");            
            Trs_lain.setText("");
        } else if (cmbCara_datang.getSelectedIndex() == 5) {
            Trujukan_bidan.setEnabled(false);
            Tpkm.setEnabled(false);
            Tspog.setEnabled(false);
            Trs_lain.setEnabled(true);
            
            Trujukan_bidan.setText("");
            Tpkm.setText("");
            Tspog.setText("");            
        } else {
            Trujukan_bidan.setEnabled(false);
            Tpkm.setEnabled(false);
            Tspog.setEnabled(false);
            Trs_lain.setEnabled(false);
            
            Trujukan_bidan.setText("");
            Tpkm.setText("");
            Tspog.setText("");
            Trs_lain.setText("");
        }
        
        if (ChkPerutMules.isSelected() == true) {
            TglPerutMules.setEnabled(true);
        } else {
            TglPerutMules.setEnabled(false);
            TglPerutMules.setDate(new Date());
        }
        
        if (cmbKeluarLendir.getSelectedIndex() == 1) {
            TglLendirYa.setEnabled(true);
        } else {
            TglLendirYa.setEnabled(false);
            TglLendirYa.setDate(new Date());
        }
        
        if (cmbKeluarAir.getSelectedIndex() == 1) {
            cmbKeluarAirYa.setEnabled(true);
            TglAirYa.setEnabled(true);
        } else {
            cmbKeluarAirYa.setEnabled(false);
            TglAirYa.setEnabled(false);
            
            cmbKeluarAir.setSelectedIndex(0);
            TglAirYa.setDate(new Date());
        }
        
        if (cmbPeriksaBidan.getSelectedIndex() == 1) {
            THasilPeriksaBidan.setEnabled(true);
            BtnHasil.setEnabled(true);
        } else {
            BtnHasil.setEnabled(false);
            THasilPeriksaBidan.setEnabled(false);
            THasilPeriksaBidan.setText("");
        }
        
        if (cmbMengeluhPusing.getSelectedIndex() == 1) {
            TglPusing.setEnabled(true);
        } else {
            TglPusing.setEnabled(false);
            TglPusing.setDate(new Date());
        }
        
        if (cmbNyeriUlu.getSelectedIndex() == 1) {
            TglNyeri.setEnabled(true);
        } else {
            TglNyeri.setEnabled(false);
            TglNyeri.setDate(new Date());
        }
        
        if (cmbPandangan.getSelectedIndex() == 1) {
            TglPandangan.setEnabled(true);
        } else {
            TglPandangan.setEnabled(false);
            TglPandangan.setDate(new Date());
        }
        
        if (cmbMual.getSelectedIndex() == 1) {
            TglMual.setEnabled(true);
        } else {
            TglMual.setEnabled(false);
            TglMual.setDate(new Date());
        }
        
        if (cmbBatuk.getSelectedIndex() == 1) {
            TglBatuk.setEnabled(true);
        } else {
            TglBatuk.setEnabled(false);
            TglBatuk.setDate(new Date());
        }
        
        if (cmbDemam.getSelectedIndex() == 1) {
            TglDemam.setEnabled(true);
        } else {
            TglDemam.setEnabled(false);
            TglDemam.setDate(new Date());
        }
        
        if (cmbRiwJlnJauh.getSelectedIndex() == 1) {
            TriwJlnJauh.setEnabled(true);
        } else {
            TriwJlnJauh.setEnabled(false);
            TriwJlnJauh.setText("");
        }
        
        if (ChkLainya1.isSelected() == true) {
            TketLainya1.setEnabled(true);
        } else {
            TketLainya1.setEnabled(false);
            TketLainya1.setText("");
        }
        
        if (ChkLainya2.isSelected() == true) {
            TketLainya2.setEnabled(true);
        } else {
            TketLainya2.setEnabled(false);
            TketLainya2.setText("");
        }
        
        if (ChkPil.isSelected() == true) {
            Tpil.setEnabled(true);
            cmbSatuanPil.setEnabled(true);
        } else {
            Tpil.setEnabled(false);
            cmbSatuanPil.setEnabled(false);
            
            Tpil.setText("");
            cmbSatuanPil.setSelectedIndex(0);
        }
        
        if (ChkSuntik1.isSelected() == true) {
            Tsuntik1.setEnabled(true);
            cmbSatuanSuntik1.setEnabled(true);
        } else {
            Tsuntik1.setEnabled(false);
            cmbSatuanSuntik1.setEnabled(false);
            
            Tsuntik1.setText("");
            cmbSatuanSuntik1.setSelectedIndex(0);
        }
        
        if (ChkSuntik3.isSelected() == true) {
            Tsuntik3.setEnabled(true);
            cmbSatuanSuntik3.setEnabled(true);
        } else {
            Tsuntik3.setEnabled(false);
            cmbSatuanSuntik3.setEnabled(false);
            
            Tsuntik3.setText("");
            cmbSatuanSuntik3.setSelectedIndex(0);
        }
        
        if (ChkImplan.isSelected() == true) {
            Timplan.setEnabled(true);
            cmbSatuanImplan.setEnabled(true);
        } else {
            Timplan.setEnabled(false);
            cmbSatuanImplan.setEnabled(false);
            
            Timplan.setText("");
            cmbSatuanImplan.setSelectedIndex(0);
        }
        
        if (ChkHis.isSelected() == true) {
            ThisKontraksi.setEnabled(true);
        } else {
            ThisKontraksi.setEnabled(false);
            ThisKontraksi.setText("");
        }
        
        if (cmbRencana.getSelectedIndex() != 0) {
            Tplaning.setEnabled(true);
            BtnPlaning.setEnabled(true);
        } else {
            Tplaning.setText("");
            BtnPlaning.setEnabled(false);
            Tplaning.setEnabled(false);
        }
        
        if (ChkObstruksiP.isSelected() == true) {
            cmbObstruksi.setEnabled(true);
        } else {
            cmbObstruksi.setSelectedIndex(0);
            cmbObstruksi.setEnabled(false);
        }
        
        if (cmbPernapasan.getSelectedIndex() != 0) {
            cmbReguler.setEnabled(true);
        } else {
            cmbReguler.setSelectedIndex(0);
            cmbReguler.setEnabled(false);
        }
        
        if (ChkMeninggal.isSelected() == true) {
            cmbJam.setEnabled(true);
            cmbMnt.setEnabled(true);
            cmbDtk.setEnabled(true);
        } else {
            cmbJam.setSelectedIndex(0);
            cmbMnt.setSelectedIndex(0);
            cmbDtk.setSelectedIndex(0);
            
            cmbJam.setEnabled(false);
            cmbMnt.setEnabled(false);
            cmbDtk.setEnabled(false);
        }
        
        if (ChkJamKeluar.isSelected() == true) {
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
        } else {
            cmbJam1.setSelectedIndex(0);
            cmbMnt1.setSelectedIndex(0);
            cmbDtk1.setSelectedIndex(0);
            
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
        }
    }

    private void stringBersih() {
        cervical = "";
        rjp = "";
        defribrilasi = "";
        intubasi = "";
        vtp = "";
        dekompresi = "";
        balut = "";
        kateter = "";
        ngt = "";
        infus = "";
        obat = "";
        tidakada = "";
        paten = "";
        obsP = "";
        deformitas = "";
        contusio = "";
        penetrasi = "";
        tenderness = "";
        swelling = "";
        ekskoriasi = "";
        abrasi = "";
        burn = "";
        laserasi = "";
        tdk_tmpk_jls = "";
        obsT = "";
        traumaJlnNfs = "";
        resikoAspirasi = "";
        bendaAsing = "";
        meninggal = "";
        ganggnafas = "";
        itemDipilih = "";
        mengeluh_prt = "";
        hipertensi1 = "";
        dm1 = "";
        jantung1 = "";
        asma1 = "";
        lainya1 = "";
        hipertensi2 = "";
        dm2 = "";
        jantung2 = "";
        asma2 = "";
        lainya2 = "";
        pil = "";
        suntik1 = "";
        suntik3 = "";
        implan = "";
        dismen = "";
        spoting = "";
        menor = "";
        metro = "";
        nyeri = "";
        bandle = "";
        his = "";
        teratur = "";
        tdk_teratur = "";
        terus = "";
        kuat = "";
        sedang = "";
        lemah = "";
        bersih = "";
        odema = "";
        ruptur = "";
        candi = "";
        jamkeluar = "";
    }
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode1);
        try {
            if (pilihan == 1) {
                ps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from penilaian_awal_medis_obstetri_ralan pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.hasil_periksa_bidan<>'' and p.no_rkm_medis like ? OR "
                        + "pa.hasil_periksa_bidan<>'' and p.nm_pasien like ? OR "
                        + "pa.hasil_periksa_bidan<>'' and pa.hasil_periksa_bidan like ? ORDER BY pa.tanggal desc limit 20");
            } else if (pilihan == 2) {
                ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from penilaian_awal_medis_obstetri_ralan pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.diagnosis_sementara<>'' and p.no_rkm_medis like ? OR "
                        + "pa.diagnosis_sementara<>'' and p.nm_pasien like ? OR "
                        + "pa.diagnosis_sementara<>'' and pa.diagnosis_sementara like ? ORDER BY pa.tanggal desc limit 20");
            } else if (pilihan == 3) {
                ps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from penilaian_awal_medis_obstetri_ralan pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.planing<>'' and p.no_rkm_medis like ? OR "
                        + "pa.planing<>'' and p.nm_pasien like ? OR "
                        + "pa.planing<>'' and pa.planing like ? ORDER BY pa.tanggal desc limit 20");
            } else if (pilihan == 4) {
                ps4 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from penilaian_awal_medis_obstetri_ralan pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.alasan_dirujuk<>'' and p.no_rkm_medis like ? OR "
                        + "pa.alasan_dirujuk<>'' and p.nm_pasien like ? OR "
                        + "pa.alasan_dirujuk<>'' and pa.alasan_dirujuk like ? ORDER BY pa.tanggal desc limit 20");
            } 
            try {
                if (pilihan == 1) {
                    ps1.setString(1, "%" + TCari1.getText() + "%");
                    ps1.setString(2, "%" + TCari1.getText() + "%");
                    ps1.setString(3, "%" + TCari1.getText() + "%");
                    rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        tabMode1.addRow(new String[]{
                            rs1.getString("no_rkm_medis"),
                            rs1.getString("nm_pasien"),
                            rs1.getString("hasil_periksa_bidan")
                        });
                    }
                } else if (pilihan == 2) {
                    ps2.setString(1, "%" + TCari1.getText() + "%");
                    ps2.setString(2, "%" + TCari1.getText() + "%");
                    ps2.setString(3, "%" + TCari1.getText() + "%");
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        tabMode1.addRow(new String[]{
                            rs2.getString("no_rkm_medis"),
                            rs2.getString("nm_pasien"),
                            rs2.getString("diagnosis_sementara")
                        });
                    }
                } else if (pilihan == 3) {
                    ps3.setString(1, "%" + TCari1.getText() + "%");
                    ps3.setString(2, "%" + TCari1.getText() + "%");
                    ps3.setString(3, "%" + TCari1.getText() + "%");
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        tabMode1.addRow(new String[]{
                            rs3.getString("no_rkm_medis"),
                            rs3.getString("nm_pasien"),
                            rs3.getString("planing")
                        });
                    }                    
                } else if (pilihan == 4) {
                    ps4.setString(1, "%" + TCari1.getText() + "%");
                    ps4.setString(2, "%" + TCari1.getText() + "%");
                    ps4.setString(3, "%" + TCari1.getText() + "%");
                    rs4 = ps4.executeQuery();
                    while (rs4.next()) {
                        tabMode1.addRow(new String[]{
                            rs4.getString("no_rkm_medis"),
                            rs4.getString("nm_pasien"),                            
                            rs4.getString("alasan_dirujuk")
                        });
                    }
                } 
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                } else if (rs2 != null) {
                    rs2.close();
                } else if (rs3 != null) {
                    rs3.close();
                } else if (rs4 != null) {
                    rs4.close();
                }
                
                if (ps1 != null) {
                    ps1.close();
                } else if (ps2 != null) {
                    ps2.close();
                } else if (ps3 != null) {
                    ps3.close();
                } else if (ps4 != null) {
                    ps4.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copas() {
        if (pilihan == 1) {
            THasilPeriksaBidan.setText(Ttemplate.getText());
        } else if (pilihan == 2) {
            TDiagSementara.setText(Ttemplate.getText());
        } else if (pilihan == 3) {
            Tplaning.setText(Ttemplate.getText());
        } else if (pilihan == 4) {
            TAlasanDirujuk.setText(Ttemplate.getText());
        } 
    }
}