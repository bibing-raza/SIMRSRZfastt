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

package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.swing.text.Document;
import kepegawaian.DlgCariPetugas;
import laporan.DlgPenyakit;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariPeriksaRadiologi;
import simrskhanza.DlgNotepad;

/**
 *
 * @author perpustakaan
 */
public final class RMAsesmenKeperawatanAnakRanap extends javax.swing.JDialog {
    private DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);    
    private PreparedStatement ps, ps1, ps3;
    private ResultSet rs, rs1, rs3;
    private int i = 0, x = 0, skor = 0;
    private String nip = "", kdkamar = "", alergiObat = "", alergiMakanan = "", alergiLain = "", gelang = "", campak = "",
            batukRejan = "", sakitTenggorokan = "", diare = "", sesak = "", difteri = "", kuning = "", lainnya = "", tetanus = "",
            demamTifoid = "", kejang = "", urtikaria = "", tbc = "", cacing = "", ibadah = "", cekgizi1 = "", cekgizi2 = "", 
            cekgizi3 = "", cekgizi4 = "", obatObatan = "", perawatanLuka = "", manajemenNyeri = "", diet = "", fisio = "", 
            hipertermi = "", nyeri = "", resiko = "", kelebihan = "", bersihkan = "", pola = "", gangguan = "", cemas = "", 
            ketidakseimbangan = "", perubahan = "", penurunan = "", kerusakan = "", intoleransi = "", kurang = "", resikojatuh = "", 
            skorFix = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsesmenKeperawatanAnakRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Masuk Ruangan", "Tgl. Masuk", "Jam Masuk", "Tiba Dg. Cara", "Melalui", "Keluhan Utama",
            "Tgl. Asesmen", "Jam", "Nama Perawat",
            "kd_kamar_msk", "tgl_msk_ruangan", "jam_msk_ruangan", "tiba_diruang", "tiba_diruang_lainnya", "msk_melalui", "keluhan_utama", "riwayat_alergi", "alergi_obat",
            "nm_alergi_obat", "reaksi_alergi_obat", "alergi_makanan", "nm_alergi_makanan", "reaksi_alergi_makanan", "alergi_lainnya", "nm_alergi_lainnya", "reaksi_alergi_lainnya",
            "pasang_gelang_tanda", "alergi_diberitahukan", "riwayat_penyakit_sekarang", "campak", "diare", "difteri", "tetanus", "kejang", "tbc", "batuk_rejan", "sesak",
            "kuning", "demam_tifoid", "urtikaria", "cacing", "sakit_tenggorokan", "lainya", "kalimat_lainya", "kesadaran", "gcs_e", "gcs_m", "gcs_v", "tensi", "temp",
            "hr", "rr", "bb_sebelum_skt", "bb_msk_rs", "tb", "imt", "crt", "spo2", "pernafasan", "penglihatan", "pendengaran", "mulut", "reflek_menelan", "bicara",
            "defekasi", "miksi", "gastrointestinal", "pola_tidur", "makan", "berpakaian", "buang_air", "mandi", "berpindah", "kesimpulan", "keluarga_terdekat",
            "hubungan", "tinggal_dengan", "tinggal_dengan_lain", "curiga_penganiayaan", "butuh_bantuan_ibadah", "status_emosional", "hepatitis_b_umur1",
            "hepatitis_b_umur2", "hepatitis_b_umur3", "hepatitis_b_umur4", "hepatitis_b_umur_ulang", "polio_umur1", "polio_umur2", "polio_umur3", "polio_umur4",
            "polio_umur_ulang", "bcg_umur_dasar", "bcg_umur_ulang", "dpt_umur1", "dpt_umur2", "dpt_umur3", "dpt_umur_ulang", "meningitis_umur1", "meningitis_umur2",
            "meningitis_umur3", "meningitis_umur_ulang", "campak_umur_dasar", "campak_umur_ulang", "mk_menegakkan_kepala", "mk_tengkurang", "mk_duduk", "mk_berjalan",
            "mh_menggenggam_mainan", "mh_mencari_benda", "mh_mencoret", "mh_mengenal_warna", "bicara_berceloteh", "bicara_mengucapkan", "bicara_menyebutkan",
            "bicara_menyebut_kata", "se_tersenyum", "se_bermain", "se_mengenal", "se_makan_minum", "saat_ini_sekolah", "kelas", "onset", "provocation", "provocation_lain",
            "quality", "quality_lain", "radiation", "severity", "time", "time_lama", "relief", "relief_lain", "asociated_sign", "asociated_sign_lain", "skala_nyeri",
            "skrining_gizi_1", "skrining_gizi_2", "skrining_gizi_3", "skrining_gizi_4", "tindakan_pencegahan", "obat_obatan", "perawatan_luka", "manajemen_lain",
            "menejemen_nyeri", "diet_nutrisi", "fisioterapi", "rehabilitasi_lain", "hipertermi", "nyeri", "kurang_volume_cairan", "lebih_volume_cairan",
            "bersihkan_jln_nfs_tdk_efektif", "pola_nfs_tdk_efektif", "gangguan_pertukaran_gas", "cemas", "ketidakseimbangan_nutrisi", "perubahan_perfusi",
            "penurunan_curah_jantung", "kerusakan_integritas", "intoleransi_aktifitas", "kurang_perawatan_diri", "daftar_masalah_lain", "tgl_asesmen", "jam_asesmen",
            "nip_perawat", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 170; i++) {
            TableColumn column = tbAsesmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(80);                
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(75);
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
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1=new DefaultTableModel(null,new Object[]{
                "#", "KODE", "ASESMEN", "FAKTOR RESIKO", "SKALA / POIN", "SKOR"
            }){
             @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbFaktorResiko.setModel(tabMode1);
        tbFaktorResiko.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbFaktorResiko.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbFaktorResiko.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setPreferredWidth(245);
            } else if (i == 4) {
                column.setPreferredWidth(315);
            } else if (i == 5) {
                column.setPreferredWidth(40);
            }
        }
        tbFaktorResiko.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        Tkesadaran.setDocument(new batasInput((int) 180).getKata(Tkesadaran));
        Tgcse.setDocument(new batasInput((int) 5).getKata(Tgcse));
        Tgcsm.setDocument(new batasInput((int) 5).getKata(Tgcsm));
        Tgcsv.setDocument(new batasInput((int) 5).getKata(Tgcsv));
        Ttensi.setDocument(new batasInput((int) 7).getKata(Ttensi));
        Ttemp.setDocument(new batasInput((int) 7).getKata(Ttemp));
        Thr.setDocument(new batasInput((int) 7).getKata(Thr));
        Trr.setDocument(new batasInput((int) 7).getKata(Trr));
        TbbBelum.setDocument(new batasInput((int) 7).getKata(TbbBelum));
        TbbMasuk.setDocument(new batasInput((int) 7).getKata(TbbMasuk));
        Ttb.setDocument(new batasInput((int) 7).getKata(Ttb));
        Timt.setDocument(new batasInput((int) 7).getKata(Timt));
        Tcrt.setDocument(new batasInput((int) 7).getKata(Tcrt));
        Tspo.setDocument(new batasInput((int) 7).getKata(Tspo));
        TKlgDekat.setDocument(new batasInput((int) 150).getKata(TKlgDekat));
        Thubungan.setDocument(new batasInput((int) 150).getKata(Thubungan));
        TtglDenganLain.setDocument(new batasInput((int) 150).getKata(TtglDenganLain));
        TnmAlergiObat.setDocument(new batasInput((int) 200).getKata(TnmAlergiObat));
        TnmAlergiMakanan.setDocument(new batasInput((int) 200).getKata(TnmAlergiMakanan));
        TnmAlergiLain.setDocument(new batasInput((int) 200).getKata(TnmAlergiLain));        
        Thepa1.setDocument(new batasInput((int) 7).getKata(Thepa1));
        Thepa2.setDocument(new batasInput((int) 7).getKata(Thepa2));
        Thepa3.setDocument(new batasInput((int) 7).getKata(Thepa3));
        Thepa4.setDocument(new batasInput((int) 7).getKata(Thepa4));
        ThepaUlang.setDocument(new batasInput((int) 7).getKata(ThepaUlang));        
        Tpolio1.setDocument(new batasInput((int) 7).getKata(Tpolio1));
        Tpolio2.setDocument(new batasInput((int) 7).getKata(Tpolio2));
        Tpolio3.setDocument(new batasInput((int) 7).getKata(Tpolio3));
        Tpolio4.setDocument(new batasInput((int) 7).getKata(Tpolio4));
        TpolioUlang.setDocument(new batasInput((int) 7).getKata(TpolioUlang));        
        TbcgDasar.setDocument(new batasInput((int) 7).getKata(TbcgDasar));
        TbcgUlang.setDocument(new batasInput((int) 7).getKata(TbcgUlang));        
        Tdpt1.setDocument(new batasInput((int) 7).getKata(Tdpt1));
        Tdpt2.setDocument(new batasInput((int) 7).getKata(Tdpt2));
        Tdpt3.setDocument(new batasInput((int) 7).getKata(Tdpt3));
        TdptUlang.setDocument(new batasInput((int) 7).getKata(TdptUlang));        
        Tmening1.setDocument(new batasInput((int) 7).getKata(Tmening1));
        Tmening2.setDocument(new batasInput((int) 7).getKata(Tmening2));
        Tmening3.setDocument(new batasInput((int) 7).getKata(Tmening3));
        TmeningUlang.setDocument(new batasInput((int) 7).getKata(TmeningUlang));        
        TcampakDasar.setDocument(new batasInput((int) 7).getKata(TcampakDasar));
        TcampakUlang.setDocument(new batasInput((int) 7).getKata(TcampakUlang));        
        TtegakanKepala.setDocument(new batasInput((int) 7).getKata(TtegakanKepala));
        Ttengkurap.setDocument(new batasInput((int) 7).getKata(Ttengkurap));
        Tduduk.setDocument(new batasInput((int) 7).getKata(Tduduk));
        Tberjalan.setDocument(new batasInput((int) 7).getKata(Tberjalan));        
        Tgenggam.setDocument(new batasInput((int) 7).getKata(Tgenggam));
        Tmencari.setDocument(new batasInput((int) 7).getKata(Tmencari));
        Tmencoret.setDocument(new batasInput((int) 7).getKata(Tmencoret));
        Tmengenal24.setDocument(new batasInput((int) 7).getKata(Tmengenal24));        
        Tberceloteh.setDocument(new batasInput((int) 7).getKata(Tberceloteh));
        Tmengucapkan.setDocument(new batasInput((int) 7).getKata(Tmengucapkan));
        Tmenyebutkan.setDocument(new batasInput((int) 7).getKata(Tmenyebutkan));
        Tmenyebut36.setDocument(new batasInput((int) 7).getKata(Tmenyebut36));        
        Ttersenyum.setDocument(new batasInput((int) 7).getKata(Ttersenyum));
        Tbermain.setDocument(new batasInput((int) 7).getKata(Tbermain));
        TmengenalAnggota.setDocument(new batasInput((int) 7).getKata(TmengenalAnggota));
        Tmakan.setDocument(new batasInput((int) 7).getKata(Tmakan));
        Tkelas.setDocument(new batasInput((int) 15).getKata(Tkelas));
        
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
                if (petugas.getTable().getSelectedRow() != -1) {
                    nip = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();                    
                    TnmPerawat.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    BtnPerawat.requestFocus();
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
        MnDokumenJangMed = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        FormAsesmen = new widget.InternalFrame();
        ScrollTriase1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel18 = new widget.Label();
        jLabel5 = new widget.Label();
        Truangan = new widget.TextBox();
        TtglMsk = new widget.Tanggal();
        jLabel20 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel8 = new widget.Label();
        cmbTiba = new widget.ComboBox();
        TtibaLain = new widget.TextBox();
        jLabel9 = new widget.Label();
        cmbMasuk = new widget.ComboBox();
        jLabel10 = new widget.Label();
        Tkeluhan = new widget.TextBox();
        jLabel11 = new widget.Label();
        cmbRiwAlergi = new widget.ComboBox();
        ChkAlergiObat = new widget.CekBox();
        ChkAlergiMakanan = new widget.CekBox();
        ChkAlergiLainya = new widget.CekBox();
        ChkGelang = new widget.CekBox();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        TreaksiObat = new widget.TextBox();
        TreaksiMakanan = new widget.TextBox();
        TreaksiLain = new widget.TextBox();
        jLabel15 = new widget.Label();
        cmbAlergiDiberitahu = new widget.ComboBox();
        jLabel16 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        TriwPenyktSkg = new widget.TextArea();
        jLabel17 = new widget.Label();
        jLabel22 = new widget.Label();
        Tkesadaran = new widget.TextBox();
        jLabel23 = new widget.Label();
        Tgcse = new widget.TextBox();
        jLabel24 = new widget.Label();
        Tgcsm = new widget.TextBox();
        jLabel25 = new widget.Label();
        Tgcsv = new widget.TextBox();
        jLabel26 = new widget.Label();
        Ttensi = new widget.TextBox();
        jLabel27 = new widget.Label();
        Ttemp = new widget.TextBox();
        jLabel28 = new widget.Label();
        Thr = new widget.TextBox();
        jLabel29 = new widget.Label();
        Trr = new widget.TextBox();
        jLabel30 = new widget.Label();
        TbbBelum = new widget.TextBox();
        jLabel31 = new widget.Label();
        jLabel32 = new widget.Label();
        TbbMasuk = new widget.TextBox();
        jLabel33 = new widget.Label();
        Ttb = new widget.TextBox();
        jLabel34 = new widget.Label();
        Timt = new widget.TextBox();
        jLabel35 = new widget.Label();
        Tcrt = new widget.TextBox();
        jLabel36 = new widget.Label();
        Tspo = new widget.TextBox();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        jLabel41 = new widget.Label();
        jLabel42 = new widget.Label();
        jLabel43 = new widget.Label();
        cmbPernafasan = new widget.ComboBox();
        cmbPenglihatan = new widget.ComboBox();
        cmbPendengaran = new widget.ComboBox();
        cmbMulut = new widget.ComboBox();
        cmbReflek = new widget.ComboBox();
        jLabel44 = new widget.Label();
        jLabel45 = new widget.Label();
        jLabel46 = new widget.Label();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        cmbBicara = new widget.ComboBox();
        cmbDefekasi = new widget.ComboBox();
        cmbMiksi = new widget.ComboBox();
        cmbGastro = new widget.ComboBox();
        cmbPola = new widget.ComboBox();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        cmbMakan = new widget.ComboBox();
        cmbBerpakaian = new widget.ComboBox();
        cmbBuang = new widget.ComboBox();
        cmbMandi = new widget.ComboBox();
        cmbBerpindah = new widget.ComboBox();
        jLabel54 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        Tkesimpulan = new widget.TextArea();
        jLabel55 = new widget.Label();
        jLabel57 = new widget.Label();
        TKlgDekat = new widget.TextBox();
        jLabel58 = new widget.Label();
        Thubungan = new widget.TextBox();
        jLabel59 = new widget.Label();
        TnoTelp = new widget.TextBox();
        jLabel60 = new widget.Label();
        cmbTinggal = new widget.ComboBox();
        TtglDenganLain = new widget.TextBox();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        cmbCuriga = new widget.ComboBox();
        ChkIbadah = new widget.CekBox();
        jLabel63 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        jLabel74 = new widget.Label();
        TotSkorGizi = new widget.TextBox();
        kesimpulanGizi = new widget.TextArea();
        jLabel65 = new widget.Label();
        PanelWall = new usu.widget.glass.PanelGlass();
        jLabel66 = new widget.Label();
        jLabel67 = new widget.Label();
        Tonset = new widget.TextBox();
        jLabel68 = new widget.Label();
        jLabel70 = new widget.Label();
        cmbProvo = new widget.ComboBox();
        Tprovo = new widget.TextBox();
        jLabel71 = new widget.Label();
        jLabel72 = new widget.Label();
        cmbQuality = new widget.ComboBox();
        Tquality = new widget.TextBox();
        jLabel73 = new widget.Label();
        jLabel76 = new widget.Label();
        cmbRadia = new widget.ComboBox();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        cmbSever = new widget.ComboBox();
        jLabel79 = new widget.Label();
        jLabel80 = new widget.Label();
        cmbTime = new widget.ComboBox();
        jLabel81 = new widget.Label();
        cmbLama = new widget.ComboBox();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        cmbRelief = new widget.ComboBox();
        Trelief = new widget.TextBox();
        jLabel84 = new widget.Label();
        jLabel85 = new widget.Label();
        cmbAsso = new widget.ComboBox();
        Tasso = new widget.TextBox();
        Scroll8 = new widget.ScrollPane();
        tbFaktorResiko = new widget.Table();
        kesimpulanResikoJatuh = new widget.TextArea();
        jLabel97 = new widget.Label();
        TotSkorRJ = new widget.TextBox();
        label12 = new widget.Label();
        TCariResiko = new widget.TextBox();
        BtnCariResiko = new widget.Button();
        BtnAllResiko = new widget.Button();
        jLabel86 = new widget.Label();
        TabPencegahanAnak = new javax.swing.JTabbedPane();
        panelBiasa8 = new widget.PanelBiasa();
        cegahA = new widget.TextArea();
        panelBiasa10 = new widget.PanelBiasa();
        cegahB = new widget.TextArea();
        jLabel100 = new widget.Label();
        cmbSkala = new widget.ComboBox();
        jLabel140 = new widget.Label();
        cmbTindakanCegah = new widget.ComboBox();
        jLabel88 = new widget.Label();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        ChkObat = new widget.CekBox();
        ChkPerawatan = new widget.CekBox();
        TmanajemenLain = new widget.TextBox();
        jLabel91 = new widget.Label();
        ChkManajemen = new widget.CekBox();
        ChkDiet = new widget.CekBox();
        ChkFisioterapi = new widget.CekBox();
        TrehabLain = new widget.TextBox();
        jLabel92 = new widget.Label();
        jLabel93 = new widget.Label();
        ChkHipertermi = new widget.CekBox();
        ChkNyeri = new widget.CekBox();
        ChkResiko = new widget.CekBox();
        ChkKelebihan = new widget.CekBox();
        ChkBersihkan = new widget.CekBox();
        ChkPola = new widget.CekBox();
        ChkGangguan = new widget.CekBox();
        ChkCemas = new widget.CekBox();
        ChkKetidakseimbangan = new widget.CekBox();
        ChkPerubahan = new widget.CekBox();
        ChkPenurunan = new widget.CekBox();
        ChkKerusakan = new widget.CekBox();
        ChkIntoleransi = new widget.CekBox();
        ChkKurang = new widget.CekBox();
        jLabel94 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        TmasalahLain = new widget.TextArea();
        jLabel95 = new widget.Label();
        TtglAses = new widget.Tanggal();
        jLabel96 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel99 = new widget.Label();
        TnmPerawat = new widget.TextBox();
        BtnPerawat = new widget.Button();
        BtnIMT = new widget.Button();
        TnmAlergiObat = new widget.TextBox();
        TnmAlergiMakanan = new widget.TextBox();
        TnmAlergiLain = new widget.TextBox();
        jLabel101 = new widget.Label();
        jLabel102 = new widget.Label();
        jLabel103 = new widget.Label();
        ChkCampak = new widget.CekBox();
        ChkBatuk = new widget.CekBox();
        ChkSakit = new widget.CekBox();
        ChkDiare = new widget.CekBox();
        ChkSesak = new widget.CekBox();
        ChkDifteri = new widget.CekBox();
        ChkKuning = new widget.CekBox();
        ChkLainya = new widget.CekBox();
        ChkTetanus = new widget.CekBox();
        ChkDemam = new widget.CekBox();
        ChkKejang = new widget.CekBox();
        ChkUrtikaria = new widget.CekBox();
        ChkTbc = new widget.CekBox();
        ChkCacing = new widget.CekBox();
        Tlainnya = new widget.TextBox();
        jLabel56 = new widget.Label();
        jLabel104 = new widget.Label();
        jLabel105 = new widget.Label();
        jLabel106 = new widget.Label();
        jLabel107 = new widget.Label();
        jLabel108 = new widget.Label();
        jLabel109 = new widget.Label();
        jLabel110 = new widget.Label();
        jLabel111 = new widget.Label();
        Thepa1 = new widget.TextBox();
        Thepa2 = new widget.TextBox();
        Thepa3 = new widget.TextBox();
        Thepa4 = new widget.TextBox();
        ThepaUlang = new widget.TextBox();
        jLabel112 = new widget.Label();
        Tpolio1 = new widget.TextBox();
        Tpolio2 = new widget.TextBox();
        Tpolio3 = new widget.TextBox();
        Tpolio4 = new widget.TextBox();
        TpolioUlang = new widget.TextBox();
        TbcgDasar = new widget.TextBox();
        TbcgUlang = new widget.TextBox();
        Tdpt1 = new widget.TextBox();
        Tdpt2 = new widget.TextBox();
        Tdpt3 = new widget.TextBox();
        TdptUlang = new widget.TextBox();
        Tmening1 = new widget.TextBox();
        Tmening2 = new widget.TextBox();
        Tmening3 = new widget.TextBox();
        TmeningUlang = new widget.TextBox();
        TcampakDasar = new widget.TextBox();
        TcampakUlang = new widget.TextBox();
        jLabel113 = new widget.Label();
        jLabel114 = new widget.Label();
        jLabel115 = new widget.Label();
        jLabel116 = new widget.Label();
        jLabel117 = new widget.Label();
        jLabel118 = new widget.Label();
        jLabel119 = new widget.Label();
        TtegakanKepala = new widget.TextBox();
        Ttengkurap = new widget.TextBox();
        Tduduk = new widget.TextBox();
        Tberjalan = new widget.TextBox();
        jLabel120 = new widget.Label();
        jLabel121 = new widget.Label();
        jLabel122 = new widget.Label();
        jLabel123 = new widget.Label();
        jLabel124 = new widget.Label();
        Tgenggam = new widget.TextBox();
        Tmencari = new widget.TextBox();
        Tmencoret = new widget.TextBox();
        Tmengenal24 = new widget.TextBox();
        jLabel125 = new widget.Label();
        jLabel126 = new widget.Label();
        jLabel127 = new widget.Label();
        jLabel128 = new widget.Label();
        jLabel129 = new widget.Label();
        jLabel130 = new widget.Label();
        jLabel131 = new widget.Label();
        Tberceloteh = new widget.TextBox();
        Tmengucapkan = new widget.TextBox();
        Tmenyebutkan = new widget.TextBox();
        Tmenyebut36 = new widget.TextBox();
        jLabel132 = new widget.Label();
        jLabel133 = new widget.Label();
        jLabel134 = new widget.Label();
        jLabel135 = new widget.Label();
        jLabel136 = new widget.Label();
        jLabel137 = new widget.Label();
        jLabel138 = new widget.Label();
        Ttersenyum = new widget.TextBox();
        Tbermain = new widget.TextBox();
        TmengenalAnggota = new widget.TextBox();
        Tmakan = new widget.TextBox();
        jLabel139 = new widget.Label();
        jLabel141 = new widget.Label();
        jLabel142 = new widget.Label();
        jLabel143 = new widget.Label();
        cmbSekolah = new widget.ComboBox();
        jLabel144 = new widget.Label();
        Tkelas = new widget.TextBox();
        jLabel145 = new widget.Label();
        jLabel146 = new widget.Label();
        ChkGizi1 = new widget.CekBox();
        ChkGizi2 = new widget.CekBox();
        labelChkGizi2 = new widget.Label();
        ChkGizi3 = new widget.CekBox();
        labelChkGizi3A = new widget.Label();
        labelChkGizi3B = new widget.Label();
        ChkGizi4 = new widget.CekBox();
        labelChkGizi41 = new widget.Label();
        labelChkGizi42 = new widget.Label();
        labelChkGizi43 = new widget.Label();
        labelChkGizi44 = new widget.Label();
        labelChkGizi45 = new widget.Label();
        labelChkGizi46 = new widget.Label();
        labelChkGizi47 = new widget.Label();
        labelChkGizi48 = new widget.Label();
        labelChkGizi49 = new widget.Label();
        labelChkGizi410 = new widget.Label();
        labelChkGizi411 = new widget.Label();
        labelChkGizi412 = new widget.Label();
        labelChkGizi413 = new widget.Label();
        labelChkGizi414 = new widget.Label();
        TskorGizi1 = new widget.TextBox();
        TskorGizi2 = new widget.TextBox();
        TskorGizi3 = new widget.TextBox();
        TskorGizi4 = new widget.TextBox();
        jLabel164 = new widget.Label();
        internalFrame4 = new widget.InternalFrame();
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
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnNotepad = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnDokumenJangMed.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokumenJangMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokumenJangMed.setText("Dokumen Penunjang Medis");
        MnDokumenJangMed.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokumenJangMed.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokumenJangMed.setIconTextGap(5);
        MnDokumenJangMed.setName("MnDokumenJangMed"); // NOI18N
        MnDokumenJangMed.setPreferredSize(new java.awt.Dimension(190, 26));
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Keperawatan Anak ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        FormAsesmen.setBorder(null);
        FormAsesmen.setName("FormAsesmen"); // NOI18N
        FormAsesmen.setLayout(new java.awt.BorderLayout(1, 1));

        ScrollTriase1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        ScrollTriase1.setName("ScrollTriase1"); // NOI18N
        ScrollTriase1.setOpaque(true);
        ScrollTriase1.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBorder(null);
        FormInput.setToolTipText("");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2610));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 135, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(136, 10, 122, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(332, 10, 390, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(260, 10, 70, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tanggal : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(558, 38, 70, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Masuk Ruang : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 135, 23);

        Truangan.setEditable(false);
        Truangan.setBackground(new java.awt.Color(245, 250, 240));
        Truangan.setForeground(new java.awt.Color(0, 0, 0));
        Truangan.setName("Truangan"); // NOI18N
        FormInput.add(Truangan);
        Truangan.setBounds(136, 38, 420, 23);

        TtglMsk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-04-2024" }));
        TtglMsk.setDisplayFormat("dd-MM-yyyy");
        TtglMsk.setName("TtglMsk"); // NOI18N
        TtglMsk.setOpaque(false);
        TtglMsk.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglMsk);
        TtglMsk.setBounds(630, 38, 90, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jam : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(722, 38, 40, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(765, 38, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(816, 38, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(868, 38, 45, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tiba Diruang Dengan Cara : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 66, 163, 23);

        cmbTiba.setForeground(new java.awt.Color(0, 0, 0));
        cmbTiba.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Jalan", "Kursi Roda", "Brankar", "Lainnya" }));
        cmbTiba.setName("cmbTiba"); // NOI18N
        cmbTiba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTibaActionPerformed(evt);
            }
        });
        FormInput.add(cmbTiba);
        cmbTiba.setBounds(165, 66, 85, 23);

        TtibaLain.setBackground(new java.awt.Color(245, 250, 240));
        TtibaLain.setForeground(new java.awt.Color(0, 0, 0));
        TtibaLain.setName("TtibaLain"); // NOI18N
        TtibaLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtibaLainKeyPressed(evt);
            }
        });
        FormInput.add(TtibaLain);
        TtibaLain.setBounds(256, 66, 300, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Masuk Melalui : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(560, 66, 90, 23);

        cmbMasuk.setForeground(new java.awt.Color(0, 0, 0));
        cmbMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "IGD", "Poliklinik" }));
        cmbMasuk.setName("cmbMasuk"); // NOI18N
        FormInput.add(cmbMasuk);
        cmbMasuk.setBounds(653, 66, 75, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("KELUHAN UTAMA : ");
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 94, 135, 23);

        Tkeluhan.setBackground(new java.awt.Color(245, 250, 240));
        Tkeluhan.setForeground(new java.awt.Color(0, 0, 0));
        Tkeluhan.setName("Tkeluhan"); // NOI18N
        Tkeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeluhanKeyPressed(evt);
            }
        });
        FormInput.add(Tkeluhan);
        Tkeluhan.setBounds(136, 94, 780, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("RIWAYAT ALERGI : ");
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 122, 135, 23);

        cmbRiwAlergi.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwAlergi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak ada", "Tidak diketahui" }));
        cmbRiwAlergi.setName("cmbRiwAlergi"); // NOI18N
        FormInput.add(cmbRiwAlergi);
        cmbRiwAlergi.setBounds(136, 122, 110, 23);

        ChkAlergiObat.setBackground(new java.awt.Color(255, 255, 250));
        ChkAlergiObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        ChkAlergiObat.setText("Alergi Obat ");
        ChkAlergiObat.setBorderPainted(true);
        ChkAlergiObat.setBorderPaintedFlat(true);
        ChkAlergiObat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkAlergiObat.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkAlergiObat.setName("ChkAlergiObat"); // NOI18N
        ChkAlergiObat.setOpaque(false);
        ChkAlergiObat.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkAlergiObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAlergiObatActionPerformed(evt);
            }
        });
        FormInput.add(ChkAlergiObat);
        ChkAlergiObat.setBounds(15, 150, 115, 23);

        ChkAlergiMakanan.setBackground(new java.awt.Color(255, 255, 250));
        ChkAlergiMakanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAlergiMakanan.setForeground(new java.awt.Color(0, 0, 0));
        ChkAlergiMakanan.setText("Alergi Makanan ");
        ChkAlergiMakanan.setBorderPainted(true);
        ChkAlergiMakanan.setBorderPaintedFlat(true);
        ChkAlergiMakanan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkAlergiMakanan.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkAlergiMakanan.setName("ChkAlergiMakanan"); // NOI18N
        ChkAlergiMakanan.setOpaque(false);
        ChkAlergiMakanan.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkAlergiMakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAlergiMakananActionPerformed(evt);
            }
        });
        FormInput.add(ChkAlergiMakanan);
        ChkAlergiMakanan.setBounds(15, 178, 115, 23);

        ChkAlergiLainya.setBackground(new java.awt.Color(255, 255, 250));
        ChkAlergiLainya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAlergiLainya.setForeground(new java.awt.Color(0, 0, 0));
        ChkAlergiLainya.setText("Alergi Lainnya ");
        ChkAlergiLainya.setBorderPainted(true);
        ChkAlergiLainya.setBorderPaintedFlat(true);
        ChkAlergiLainya.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkAlergiLainya.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkAlergiLainya.setName("ChkAlergiLainya"); // NOI18N
        ChkAlergiLainya.setOpaque(false);
        ChkAlergiLainya.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkAlergiLainya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAlergiLainyaActionPerformed(evt);
            }
        });
        FormInput.add(ChkAlergiLainya);
        ChkAlergiLainya.setBounds(15, 206, 115, 23);

        ChkGelang.setBackground(new java.awt.Color(255, 255, 250));
        ChkGelang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGelang.setForeground(new java.awt.Color(0, 0, 0));
        ChkGelang.setText("Gelang Tanda Alergi Dipasang (Warna Merah)");
        ChkGelang.setBorderPainted(true);
        ChkGelang.setBorderPaintedFlat(true);
        ChkGelang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkGelang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGelang.setName("ChkGelang"); // NOI18N
        ChkGelang.setOpaque(false);
        ChkGelang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkGelang);
        ChkGelang.setBounds(15, 234, 270, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Reaksi : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(466, 150, 50, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Reaksi : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(466, 178, 50, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Reaksi : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(466, 206, 50, 23);

        TreaksiObat.setBackground(new java.awt.Color(245, 250, 240));
        TreaksiObat.setForeground(new java.awt.Color(0, 0, 0));
        TreaksiObat.setName("TreaksiObat"); // NOI18N
        TreaksiObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreaksiObatKeyPressed(evt);
            }
        });
        FormInput.add(TreaksiObat);
        TreaksiObat.setBounds(516, 150, 400, 23);

        TreaksiMakanan.setBackground(new java.awt.Color(245, 250, 240));
        TreaksiMakanan.setForeground(new java.awt.Color(0, 0, 0));
        TreaksiMakanan.setName("TreaksiMakanan"); // NOI18N
        TreaksiMakanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreaksiMakananKeyPressed(evt);
            }
        });
        FormInput.add(TreaksiMakanan);
        TreaksiMakanan.setBounds(516, 178, 400, 23);

        TreaksiLain.setBackground(new java.awt.Color(245, 250, 240));
        TreaksiLain.setForeground(new java.awt.Color(0, 0, 0));
        TreaksiLain.setName("TreaksiLain"); // NOI18N
        FormInput.add(TreaksiLain);
        TreaksiLain.setBounds(516, 206, 400, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Alergi Diberitahukan Kepada : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(300, 234, 170, 23);

        cmbAlergiDiberitahu.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlergiDiberitahu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Dokter", "Farmasis/Apoteker", "Ahli Gizi" }));
        cmbAlergiDiberitahu.setName("cmbAlergiDiberitahu"); // NOI18N
        FormInput.add(cmbAlergiDiberitahu);
        cmbAlergiDiberitahu.setBounds(473, 234, 130, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("RIWAYAT PENYAKIT  ");
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 262, 163, 23);

        scrollPane3.setName("scrollPane3"); // NOI18N

        TriwPenyktSkg.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TriwPenyktSkg.setColumns(20);
        TriwPenyktSkg.setRows(5);
        TriwPenyktSkg.setName("TriwPenyktSkg"); // NOI18N
        TriwPenyktSkg.setPreferredSize(new java.awt.Dimension(162, 700));
        TriwPenyktSkg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwPenyktSkgKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(TriwPenyktSkg);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(165, 262, 750, 70);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("KEADAAN UMUM : ");
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 422, 135, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Kesadaran : ");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 437, 135, 23);

        Tkesadaran.setBackground(new java.awt.Color(245, 250, 240));
        Tkesadaran.setForeground(new java.awt.Color(0, 0, 0));
        Tkesadaran.setName("Tkesadaran"); // NOI18N
        Tkesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesadaranKeyPressed(evt);
            }
        });
        FormInput.add(Tkesadaran);
        Tkesadaran.setBounds(136, 437, 510, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("GCS : E ");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(650, 437, 50, 23);

        Tgcse.setBackground(new java.awt.Color(245, 250, 240));
        Tgcse.setForeground(new java.awt.Color(0, 0, 0));
        Tgcse.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tgcse.setName("Tgcse"); // NOI18N
        Tgcse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcseKeyPressed(evt);
            }
        });
        FormInput.add(Tgcse);
        Tgcse.setBounds(705, 437, 50, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("M");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(755, 437, 20, 23);

        Tgcsm.setBackground(new java.awt.Color(245, 250, 240));
        Tgcsm.setForeground(new java.awt.Color(0, 0, 0));
        Tgcsm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tgcsm.setName("Tgcsm"); // NOI18N
        Tgcsm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsmKeyPressed(evt);
            }
        });
        FormInput.add(Tgcsm);
        Tgcsm.setBounds(775, 437, 50, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("V");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(825, 437, 20, 23);

        Tgcsv.setBackground(new java.awt.Color(245, 250, 240));
        Tgcsv.setForeground(new java.awt.Color(0, 0, 0));
        Tgcsv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tgcsv.setName("Tgcsv"); // NOI18N
        Tgcsv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsvKeyPressed(evt);
            }
        });
        FormInput.add(Tgcsv);
        Tgcsv.setBounds(845, 437, 50, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Tensi : ");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 465, 135, 23);

        Ttensi.setBackground(new java.awt.Color(245, 250, 240));
        Ttensi.setForeground(new java.awt.Color(0, 0, 0));
        Ttensi.setName("Ttensi"); // NOI18N
        Ttensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtensiKeyPressed(evt);
            }
        });
        FormInput.add(Ttensi);
        Ttensi.setBounds(136, 465, 70, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("mmHg       Temp : ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(210, 465, 90, 23);

        Ttemp.setBackground(new java.awt.Color(245, 250, 240));
        Ttemp.setForeground(new java.awt.Color(0, 0, 0));
        Ttemp.setName("Ttemp"); // NOI18N
        Ttemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtempKeyPressed(evt);
            }
        });
        FormInput.add(Ttemp);
        Ttemp.setBounds(304, 465, 70, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("°C      HR : ");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(380, 465, 55, 23);

        Thr.setBackground(new java.awt.Color(245, 250, 240));
        Thr.setForeground(new java.awt.Color(0, 0, 0));
        Thr.setName("Thr"); // NOI18N
        Thr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThrKeyPressed(evt);
            }
        });
        FormInput.add(Thr);
        Thr.setBounds(436, 465, 70, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("x/mnt      RR : ");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(510, 465, 70, 23);

        Trr.setBackground(new java.awt.Color(245, 250, 240));
        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        FormInput.add(Trr);
        Trr.setBounds(583, 465, 70, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("x/mnt      BB Sebelum Sakit : ");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(660, 465, 140, 23);

        TbbBelum.setBackground(new java.awt.Color(245, 250, 240));
        TbbBelum.setForeground(new java.awt.Color(0, 0, 0));
        TbbBelum.setName("TbbBelum"); // NOI18N
        TbbBelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbBelumKeyPressed(evt);
            }
        });
        FormInput.add(TbbBelum);
        TbbBelum.setBounds(798, 465, 55, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Kg.");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(858, 465, 30, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("BB Masuk RS : ");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(0, 493, 135, 23);

        TbbMasuk.setBackground(new java.awt.Color(245, 250, 240));
        TbbMasuk.setForeground(new java.awt.Color(0, 0, 0));
        TbbMasuk.setToolTipText("Jika pakai koma ganti dengan titik, sebagai gantinya koma");
        TbbMasuk.setName("TbbMasuk"); // NOI18N
        TbbMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbMasukKeyPressed(evt);
            }
        });
        FormInput.add(TbbMasuk);
        TbbMasuk.setBounds(136, 493, 70, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Kg.           TB :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(210, 493, 73, 23);

        Ttb.setBackground(new java.awt.Color(245, 250, 240));
        Ttb.setForeground(new java.awt.Color(0, 0, 0));
        Ttb.setToolTipText("Jika pakai koma ganti dengan titik, sebagai gantinya koma");
        Ttb.setName("Ttb"); // NOI18N
        Ttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbKeyPressed(evt);
            }
        });
        FormInput.add(Ttb);
        Ttb.setBounds(283, 493, 60, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Cm");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(348, 493, 20, 23);

        Timt.setBackground(new java.awt.Color(245, 250, 240));
        Timt.setForeground(new java.awt.Color(0, 0, 0));
        Timt.setName("Timt"); // NOI18N
        Timt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TimtKeyPressed(evt);
            }
        });
        FormInput.add(Timt);
        Timt.setBounds(501, 493, 68, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Kg.      CRT : ");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(575, 493, 65, 23);

        Tcrt.setBackground(new java.awt.Color(245, 250, 240));
        Tcrt.setForeground(new java.awt.Color(0, 0, 0));
        Tcrt.setName("Tcrt"); // NOI18N
        Tcrt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcrtKeyPressed(evt);
            }
        });
        FormInput.add(Tcrt);
        Tcrt.setBounds(642, 493, 70, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("detik       SPO2 : ");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(719, 493, 81, 23);

        Tspo.setBackground(new java.awt.Color(245, 250, 240));
        Tspo.setForeground(new java.awt.Color(0, 0, 0));
        Tspo.setName("Tspo"); // NOI18N
        Tspo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TspoKeyPressed(evt);
            }
        });
        FormInput.add(Tspo);
        Tspo.setBounds(800, 493, 55, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("%");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(860, 493, 30, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("PENILAIAN FISIK : ");
        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(0, 521, 135, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Pernafasan : ");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(0, 536, 135, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Penglihatan : ");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 564, 135, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Pendengaran : ");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(0, 592, 135, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Mulut : ");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(0, 620, 135, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Reflek Menelan : ");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(0, 648, 135, 23);

        cmbPernafasan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPernafasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Batuk", "Sesak" }));
        cmbPernafasan.setName("cmbPernafasan"); // NOI18N
        FormInput.add(cmbPernafasan);
        cmbPernafasan.setBounds(136, 536, 70, 23);

        cmbPenglihatan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenglihatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Baik", "Rusak", "Alat Bantu" }));
        cmbPenglihatan.setName("cmbPenglihatan"); // NOI18N
        FormInput.add(cmbPenglihatan);
        cmbPenglihatan.setBounds(136, 564, 85, 23);

        cmbPendengaran.setForeground(new java.awt.Color(0, 0, 0));
        cmbPendengaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Baik", "Rusak", "Alat Bantu" }));
        cmbPendengaran.setName("cmbPendengaran"); // NOI18N
        FormInput.add(cmbPendengaran);
        cmbPendengaran.setBounds(136, 592, 85, 23);

        cmbMulut.setForeground(new java.awt.Color(0, 0, 0));
        cmbMulut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bersih", "Kotor", "Lain-lain" }));
        cmbMulut.setName("cmbMulut"); // NOI18N
        FormInput.add(cmbMulut);
        cmbMulut.setBounds(136, 620, 85, 23);

        cmbReflek.setForeground(new java.awt.Color(0, 0, 0));
        cmbReflek.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Sulit", "Rusak" }));
        cmbReflek.setName("cmbReflek"); // NOI18N
        FormInput.add(cmbReflek);
        cmbReflek.setBounds(136, 648, 70, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Bicara : ");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(228, 536, 105, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Defekasi : ");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(228, 564, 105, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Miksi : ");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(228, 592, 105, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Gastrointestinal : ");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(228, 620, 105, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Pola Tidur : ");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(228, 648, 105, 23);

        cmbBicara.setForeground(new java.awt.Color(0, 0, 0));
        cmbBicara.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Gangguan" }));
        cmbBicara.setName("cmbBicara"); // NOI18N
        FormInput.add(cmbBicara);
        cmbBicara.setBounds(336, 536, 82, 23);

        cmbDefekasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbDefekasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Konstipasi", "Diare", "Inkontinensia Alvi" }));
        cmbDefekasi.setName("cmbDefekasi"); // NOI18N
        FormInput.add(cmbDefekasi);
        cmbDefekasi.setBounds(336, 564, 119, 23);

        cmbMiksi.setForeground(new java.awt.Color(0, 0, 0));
        cmbMiksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Retensi", "Inkontinensia Urin" }));
        cmbMiksi.setName("cmbMiksi"); // NOI18N
        FormInput.add(cmbMiksi);
        cmbMiksi.setBounds(336, 592, 121, 23);

        cmbGastro.setForeground(new java.awt.Color(0, 0, 0));
        cmbGastro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Refluks", "Nausea", "Muntah" }));
        cmbGastro.setName("cmbGastro"); // NOI18N
        FormInput.add(cmbGastro);
        cmbGastro.setBounds(336, 620, 70, 23);

        cmbPola.setForeground(new java.awt.Color(0, 0, 0));
        cmbPola.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Masalah" }));
        cmbPola.setName("cmbPola"); // NOI18N
        FormInput.add(cmbPola);
        cmbPola.setBounds(336, 648, 75, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Makan : ");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(460, 536, 80, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Berpakaian : ");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(460, 564, 80, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Buang Air : ");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(460, 592, 80, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Mandi : ");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(460, 620, 80, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Berpindah : ");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(460, 648, 80, 23);

        cmbMakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbMakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mandiri", "Dibantu Alat", "Dibantu Orang", "Dibantu Total" }));
        cmbMakan.setName("cmbMakan"); // NOI18N
        FormInput.add(cmbMakan);
        cmbMakan.setBounds(543, 536, 103, 23);

        cmbBerpakaian.setForeground(new java.awt.Color(0, 0, 0));
        cmbBerpakaian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mandiri", "Dibantu Alat", "Dibantu Orang", "Dibantu Total" }));
        cmbBerpakaian.setName("cmbBerpakaian"); // NOI18N
        FormInput.add(cmbBerpakaian);
        cmbBerpakaian.setBounds(543, 564, 103, 23);

        cmbBuang.setForeground(new java.awt.Color(0, 0, 0));
        cmbBuang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mandiri", "Dibantu Alat", "Dibantu Orang", "Dibantu Total" }));
        cmbBuang.setName("cmbBuang"); // NOI18N
        FormInput.add(cmbBuang);
        cmbBuang.setBounds(543, 592, 103, 23);

        cmbMandi.setForeground(new java.awt.Color(0, 0, 0));
        cmbMandi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mandiri", "Dibantu Alat", "Dibantu Orang", "Dibantu Total" }));
        cmbMandi.setName("cmbMandi"); // NOI18N
        FormInput.add(cmbMandi);
        cmbMandi.setBounds(543, 620, 103, 23);

        cmbBerpindah.setForeground(new java.awt.Color(0, 0, 0));
        cmbBerpindah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mandiri", "Dibantu Alat", "Dibantu Orang", "Dibantu Total" }));
        cmbBerpindah.setName("cmbBerpindah"); // NOI18N
        FormInput.add(cmbBerpindah);
        cmbBerpindah.setBounds(543, 648, 103, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Kesimpulan : ");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(650, 536, 80, 23);

        scrollPane4.setName("scrollPane4"); // NOI18N

        Tkesimpulan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkesimpulan.setColumns(20);
        Tkesimpulan.setRows(5);
        Tkesimpulan.setName("Tkesimpulan"); // NOI18N
        Tkesimpulan.setPreferredSize(new java.awt.Dimension(162, 700));
        Tkesimpulan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesimpulanKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(Tkesimpulan);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(660, 560, 260, 110);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("PENGKAJIAN SOSIAL DAN PSIKOLOGIS : ");
        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(0, 683, 250, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Keluarga Terdekat : ");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(0, 704, 135, 23);

        TKlgDekat.setBackground(new java.awt.Color(245, 250, 240));
        TKlgDekat.setForeground(new java.awt.Color(0, 0, 0));
        TKlgDekat.setName("TKlgDekat"); // NOI18N
        TKlgDekat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKlgDekatKeyPressed(evt);
            }
        });
        FormInput.add(TKlgDekat);
        TKlgDekat.setBounds(136, 704, 170, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Hubungan : ");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(305, 704, 80, 23);

        Thubungan.setBackground(new java.awt.Color(245, 250, 240));
        Thubungan.setForeground(new java.awt.Color(0, 0, 0));
        Thubungan.setName("Thubungan"); // NOI18N
        Thubungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThubunganKeyPressed(evt);
            }
        });
        FormInput.add(Thubungan);
        Thubungan.setBounds(390, 704, 230, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("No. Telpn : ");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(620, 704, 70, 23);

        TnoTelp.setEditable(false);
        TnoTelp.setBackground(new java.awt.Color(245, 250, 240));
        TnoTelp.setForeground(new java.awt.Color(0, 0, 0));
        TnoTelp.setName("TnoTelp"); // NOI18N
        FormInput.add(TnoTelp);
        TnoTelp.setBounds(693, 704, 150, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Tinggal Dengan  : ");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(0, 732, 135, 23);

        cmbTinggal.setForeground(new java.awt.Color(0, 0, 0));
        cmbTinggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Orang Tua", "Suami", "Istri", "Suami/Istri", "Anak", "Sendiri", "Lainnya" }));
        cmbTinggal.setName("cmbTinggal"); // NOI18N
        cmbTinggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTinggalActionPerformed(evt);
            }
        });
        FormInput.add(cmbTinggal);
        cmbTinggal.setBounds(136, 732, 86, 23);

        TtglDenganLain.setBackground(new java.awt.Color(245, 250, 240));
        TtglDenganLain.setForeground(new java.awt.Color(0, 0, 0));
        TtglDenganLain.setName("TtglDenganLain"); // NOI18N
        TtglDenganLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtglDenganLainKeyPressed(evt);
            }
        });
        FormInput.add(TtglDenganLain);
        TtglDenganLain.setBounds(230, 732, 290, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Kegiatan Ibadah : ");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(0, 760, 135, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Curiga Penganiayaan / Penelantaran : ");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(520, 732, 200, 23);

        cmbCuriga.setForeground(new java.awt.Color(0, 0, 0));
        cmbCuriga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbCuriga.setName("cmbCuriga"); // NOI18N
        FormInput.add(cmbCuriga);
        cmbCuriga.setBounds(723, 732, 60, 23);

        ChkIbadah.setBackground(new java.awt.Color(255, 255, 250));
        ChkIbadah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkIbadah.setForeground(new java.awt.Color(0, 0, 0));
        ChkIbadah.setText("Membutuhkan Bantuan Dalam Beribadah");
        ChkIbadah.setBorderPainted(true);
        ChkIbadah.setBorderPaintedFlat(true);
        ChkIbadah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkIbadah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkIbadah.setName("ChkIbadah"); // NOI18N
        ChkIbadah.setOpaque(false);
        ChkIbadah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkIbadah);
        ChkIbadah.setBounds(136, 760, 225, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Status Emosional : ");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(620, 760, 100, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Tidak Semangat", "Rasa Tertekan", "Depresi", "Cemas", "Sulit Tidur", "Cepat Lelah", "Sulit Konsentrasi", "Merasa Bersalah" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        FormInput.add(cmbStatus);
        cmbStatus.setBounds(723, 760, 113, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Total Skor :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(580, 2197, 70, 23);

        TotSkorGizi.setEditable(false);
        TotSkorGizi.setForeground(new java.awt.Color(0, 0, 0));
        TotSkorGizi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotSkorGizi.setText("0");
        TotSkorGizi.setFocusTraversalPolicyProvider(true);
        TotSkorGizi.setName("TotSkorGizi"); // NOI18N
        FormInput.add(TotSkorGizi);
        TotSkorGizi.setBounds(655, 2197, 40, 23);

        kesimpulanGizi.setEditable(false);
        kesimpulanGizi.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kesimpulan Skrining Gizi : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanGizi.setColumns(20);
        kesimpulanGizi.setRows(5);
        kesimpulanGizi.setName("kesimpulanGizi"); // NOI18N
        FormInput.add(kesimpulanGizi);
        kesimpulanGizi.setBounds(513, 2225, 350, 65);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("ASESMEN NYERI :");
        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 1205, 135, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/skala_nyeri.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(30, 1225, 540, 240);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Onset");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 1473, 100, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText(": Nyeri atau ketidaknyamanan saat muncul");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(110, 1473, 220, 23);

        Tonset.setBackground(new java.awt.Color(245, 250, 240));
        Tonset.setForeground(new java.awt.Color(0, 0, 0));
        Tonset.setName("Tonset"); // NOI18N
        Tonset.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TonsetKeyPressed(evt);
            }
        });
        FormInput.add(Tonset);
        Tonset.setBounds(335, 1473, 600, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Provocation");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 1501, 100, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText(": Faktor yang memperburuk rasa nyeri");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(110, 1501, 220, 23);

        cmbProvo.setForeground(new java.awt.Color(0, 0, 0));
        cmbProvo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Cahaya", "Gelap", "Gerakan", "Berbaring", "Lainnya" }));
        cmbProvo.setName("cmbProvo"); // NOI18N
        cmbProvo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvoActionPerformed(evt);
            }
        });
        FormInput.add(cmbProvo);
        cmbProvo.setBounds(335, 1501, 80, 23);

        Tprovo.setBackground(new java.awt.Color(245, 250, 240));
        Tprovo.setForeground(new java.awt.Color(0, 0, 0));
        Tprovo.setName("Tprovo"); // NOI18N
        Tprovo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprovoKeyPressed(evt);
            }
        });
        FormInput.add(Tprovo);
        Tprovo.setBounds(420, 1501, 515, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Quality");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 1529, 100, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel72.setText(": Rasa nyeri seperti");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(110, 1529, 220, 23);

        cmbQuality.setForeground(new java.awt.Color(0, 0, 0));
        cmbQuality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ditusuk", "Dipukul", "Berdenyut", "Ditikam", "Kram", "Ditarik", "Dibakar", "Tajam", "Lainnya" }));
        cmbQuality.setName("cmbQuality"); // NOI18N
        cmbQuality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbQualityActionPerformed(evt);
            }
        });
        FormInput.add(cmbQuality);
        cmbQuality.setBounds(335, 1529, 81, 23);

        Tquality.setBackground(new java.awt.Color(245, 250, 240));
        Tquality.setForeground(new java.awt.Color(0, 0, 0));
        Tquality.setName("Tquality"); // NOI18N
        Tquality.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TqualityKeyPressed(evt);
            }
        });
        FormInput.add(Tquality);
        Tquality.setBounds(420, 1529, 515, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Radiation");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 1557, 100, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText(": Nyeri menjalar ke bagian tubuh yang lain");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(110, 1557, 220, 23);

        cmbRadia.setForeground(new java.awt.Color(0, 0, 0));
        cmbRadia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbRadia.setName("cmbRadia"); // NOI18N
        FormInput.add(cmbRadia);
        cmbRadia.setBounds(335, 1557, 60, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Severity");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 1585, 100, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText(": Tingkat keparahan nyeri");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(110, 1585, 220, 23);

        cmbSever.setForeground(new java.awt.Color(0, 0, 0));
        cmbSever.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak Nyeri", "Ringan", "Sedang", "Berat" }));
        cmbSever.setName("cmbSever"); // NOI18N
        FormInput.add(cmbSever);
        cmbSever.setBounds(335, 1585, 86, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Time");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 1613, 100, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText(": Nyeri berlangsung");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(110, 1613, 220, 23);

        cmbTime.setForeground(new java.awt.Color(0, 0, 0));
        cmbTime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Terus Menerus", "Hilang Timbul" }));
        cmbTime.setName("cmbTime"); // NOI18N
        FormInput.add(cmbTime);
        cmbTime.setBounds(335, 1613, 105, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Lama  : ");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(440, 1613, 50, 23);

        cmbLama.setForeground(new java.awt.Color(0, 0, 0));
        cmbLama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "< 30 Menit", "> 30 Menit" }));
        cmbLama.setName("cmbLama"); // NOI18N
        FormInput.add(cmbLama);
        cmbLama.setBounds(495, 1613, 86, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Relief");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 1641, 100, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText(": Yang membuat nyeri berkurang");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(110, 1641, 220, 23);

        cmbRelief.setForeground(new java.awt.Color(0, 0, 0));
        cmbRelief.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Kompres Hangat", "Kompres Dingin", "Farmako Terapi", "Lainnya" }));
        cmbRelief.setName("cmbRelief"); // NOI18N
        cmbRelief.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbReliefActionPerformed(evt);
            }
        });
        FormInput.add(cmbRelief);
        cmbRelief.setBounds(335, 1641, 112, 23);

        Trelief.setBackground(new java.awt.Color(245, 250, 240));
        Trelief.setForeground(new java.awt.Color(0, 0, 0));
        Trelief.setName("Trelief"); // NOI18N
        Trelief.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreliefKeyPressed(evt);
            }
        });
        FormInput.add(Trelief);
        Trelief.setBounds(454, 1641, 482, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Associated Sign");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(0, 1669, 100, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel85.setText(": Efek dari nyeri");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(110, 1669, 220, 23);

        cmbAsso.setForeground(new java.awt.Color(0, 0, 0));
        cmbAsso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Mual / Muntah", "Gangguan Tidur", "Nafsu Makan Menurun", "Emosi", "Aktivitas Berkurang", "Lainnya" }));
        cmbAsso.setName("cmbAsso"); // NOI18N
        cmbAsso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAssoActionPerformed(evt);
            }
        });
        FormInput.add(cmbAsso);
        cmbAsso.setBounds(335, 1669, 139, 23);

        Tasso.setBackground(new java.awt.Color(245, 250, 240));
        Tasso.setForeground(new java.awt.Color(0, 0, 0));
        Tasso.setName("Tasso"); // NOI18N
        Tasso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TassoKeyPressed(evt);
            }
        });
        FormInput.add(Tasso);
        Tasso.setBounds(480, 1669, 455, 23);

        Scroll8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " ASSESMEN RESIKO JATUH HUMPTY DUMPTY : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        Scroll8.setName("Scroll8"); // NOI18N

        tbFaktorResiko.setName("tbFaktorResiko"); // NOI18N
        tbFaktorResiko.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaktorResikoMouseClicked(evt);
            }
        });
        tbFaktorResiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaktorResikoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbFaktorResikoKeyReleased(evt);
            }
        });
        Scroll8.setViewportView(tbFaktorResiko);

        FormInput.add(Scroll8);
        Scroll8.setBounds(10, 1697, 650, 143);

        kesimpulanResikoJatuh.setEditable(false);
        kesimpulanResikoJatuh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kesimpulan Skor Resiko Jatuh : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanResikoJatuh.setColumns(20);
        kesimpulanResikoJatuh.setRows(5);
        kesimpulanResikoJatuh.setName("kesimpulanResikoJatuh"); // NOI18N
        FormInput.add(kesimpulanResikoJatuh);
        kesimpulanResikoJatuh.setBounds(663, 1697, 270, 50);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Jumlah Skor Resiko Jatuh :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(663, 1750, 140, 23);

        TotSkorRJ.setEditable(false);
        TotSkorRJ.setForeground(new java.awt.Color(0, 0, 0));
        TotSkorRJ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotSkorRJ.setText("0");
        TotSkorRJ.setFocusTraversalPolicyProvider(true);
        TotSkorRJ.setName("TotSkorRJ"); // NOI18N
        FormInput.add(TotSkorRJ);
        TotSkorRJ.setBounds(808, 1750, 40, 23);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Key Word :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label12);
        label12.setBounds(665, 1778, 60, 23);

        TCariResiko.setForeground(new java.awt.Color(0, 0, 0));
        TCariResiko.setToolTipText("Alt+C");
        TCariResiko.setName("TCariResiko"); // NOI18N
        TCariResiko.setPreferredSize(new java.awt.Dimension(140, 23));
        TCariResiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariResikoKeyPressed(evt);
            }
        });
        FormInput.add(TCariResiko);
        TCariResiko.setBounds(730, 1778, 200, 23);

        BtnCariResiko.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariResiko.setMnemonic('1');
        BtnCariResiko.setToolTipText("Alt+1");
        BtnCariResiko.setName("BtnCariResiko"); // NOI18N
        BtnCariResiko.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariResiko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariResikoActionPerformed(evt);
            }
        });
        BtnCariResiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariResikoKeyPressed(evt);
            }
        });
        FormInput.add(BtnCariResiko);
        BtnCariResiko.setBounds(933, 1778, 28, 23);

        BtnAllResiko.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllResiko.setMnemonic('2');
        BtnAllResiko.setToolTipText("2Alt+2");
        BtnAllResiko.setName("BtnAllResiko"); // NOI18N
        BtnAllResiko.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllResiko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllResikoActionPerformed(evt);
            }
        });
        BtnAllResiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllResikoKeyPressed(evt);
            }
        });
        FormInput.add(BtnAllResiko);
        BtnAllResiko.setBounds(970, 1778, 28, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Tindakan Pencegahan Resiko Jatuh :");
        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 1843, 230, 23);

        TabPencegahanAnak.setBackground(new java.awt.Color(255, 255, 254));
        TabPencegahanAnak.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabPencegahanAnak.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPencegahanAnak.setName("TabPencegahanAnak"); // NOI18N

        panelBiasa8.setName("panelBiasa8"); // NOI18N
        panelBiasa8.setLayout(new java.awt.BorderLayout());

        cegahA.setEditable(false);
        cegahA.setBackground(new java.awt.Color(255, 255, 255));
        cegahA.setColumns(20);
        cegahA.setRows(5);
        cegahA.setText("1. Orientasi lingkungan\n2. Posisi tempat tidur rendah dan terkunci\n3. Rel tempat tidur dipasang (dinaikkan)\n4. Pencahayaan adekuat\n5. Edukasi pencegahan jatuh");
        cegahA.setName("cegahA"); // NOI18N
        cegahA.setOpaque(true);
        panelBiasa8.add(cegahA, java.awt.BorderLayout.CENTER);

        TabPencegahanAnak.addTab("Pencegahan Umum (A)", panelBiasa8);

        panelBiasa10.setName("panelBiasa10"); // NOI18N
        panelBiasa10.setLayout(new java.awt.BorderLayout());

        cegahB.setEditable(false);
        cegahB.setBackground(new java.awt.Color(255, 255, 255));
        cegahB.setColumns(20);
        cegahB.setRows(5);
        cegahB.setText("1. Lakukan semua pencegahan umum A\n2. Beri tanda segitiga warna kuning pada bed/RM\n3. Beri tanda identifikasi dengan pin/kancing kuning pada gelang identitas\n4. Kunjungi dan monitor setiap 1 jam\n5. Libatkan keluarga untuk mengawasi pasien");
        cegahB.setName("cegahB"); // NOI18N
        cegahB.setOpaque(true);
        panelBiasa10.add(cegahB, java.awt.BorderLayout.CENTER);

        TabPencegahanAnak.addTab("Pencegahan Resiko Tinggi (B)", panelBiasa10);

        FormInput.add(TabPencegahanAnak);
        TabPencegahanAnak.setBounds(25, 1863, 390, 128);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Skala Nyeri : ");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(580, 1442, 80, 23);

        cmbSkala.setForeground(new java.awt.Color(0, 0, 0));
        cmbSkala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cmbSkala.setName("cmbSkala"); // NOI18N
        FormInput.add(cmbSkala);
        cmbSkala.setBounds(665, 1442, 45, 23);

        jLabel140.setForeground(new java.awt.Color(0, 0, 0));
        jLabel140.setText("Tindakan Pencegahan Resiko Jatuh :");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(420, 1965, 190, 23);

        cmbTindakanCegah.setForeground(new java.awt.Color(0, 0, 0));
        cmbTindakanCegah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "A", "B" }));
        cmbTindakanCegah.setName("cmbTindakanCegah"); // NOI18N
        cmbTindakanCegah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTindakanCegahActionPerformed(evt);
            }
        });
        FormInput.add(cmbTindakanCegah);
        cmbTindakanCegah.setBounds(615, 1965, 40, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("KEBUTUHAN EDUKASI (DIKAJI PADA PASIEN DAN KELUARGA) :");
        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(0, 2309, 380, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Manajemen : ");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 2329, 100, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Rehabilitasi : ");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(0, 2357, 100, 23);

        ChkObat.setBackground(new java.awt.Color(255, 255, 250));
        ChkObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObat.setForeground(new java.awt.Color(0, 0, 0));
        ChkObat.setText("Obat - Obatan");
        ChkObat.setBorderPainted(true);
        ChkObat.setBorderPaintedFlat(true);
        ChkObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkObat.setName("ChkObat"); // NOI18N
        ChkObat.setOpaque(false);
        ChkObat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkObat);
        ChkObat.setBounds(104, 2329, 100, 23);

        ChkPerawatan.setBackground(new java.awt.Color(255, 255, 250));
        ChkPerawatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPerawatan.setForeground(new java.awt.Color(0, 0, 0));
        ChkPerawatan.setText("Perawatan Luka");
        ChkPerawatan.setBorderPainted(true);
        ChkPerawatan.setBorderPaintedFlat(true);
        ChkPerawatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPerawatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPerawatan.setName("ChkPerawatan"); // NOI18N
        ChkPerawatan.setOpaque(false);
        ChkPerawatan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPerawatan);
        ChkPerawatan.setBounds(223, 2329, 104, 23);

        TmanajemenLain.setForeground(new java.awt.Color(0, 0, 0));
        TmanajemenLain.setToolTipText("Alt+C");
        TmanajemenLain.setName("TmanajemenLain"); // NOI18N
        TmanajemenLain.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TmanajemenLain);
        TmanajemenLain.setBounds(400, 2329, 530, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Lainnya : ");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(337, 2329, 60, 23);

        ChkManajemen.setBackground(new java.awt.Color(255, 255, 250));
        ChkManajemen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkManajemen.setForeground(new java.awt.Color(0, 0, 0));
        ChkManajemen.setText("Manajemen Nyeri");
        ChkManajemen.setBorderPainted(true);
        ChkManajemen.setBorderPaintedFlat(true);
        ChkManajemen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkManajemen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkManajemen.setName("ChkManajemen"); // NOI18N
        ChkManajemen.setOpaque(false);
        ChkManajemen.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkManajemen);
        ChkManajemen.setBounds(104, 2357, 110, 23);

        ChkDiet.setBackground(new java.awt.Color(255, 255, 250));
        ChkDiet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDiet.setForeground(new java.awt.Color(0, 0, 0));
        ChkDiet.setText("Diet dan Nutrisi");
        ChkDiet.setBorderPainted(true);
        ChkDiet.setBorderPaintedFlat(true);
        ChkDiet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDiet.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDiet.setName("ChkDiet"); // NOI18N
        ChkDiet.setOpaque(false);
        ChkDiet.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDiet);
        ChkDiet.setBounds(223, 2357, 104, 23);

        ChkFisioterapi.setBackground(new java.awt.Color(255, 255, 250));
        ChkFisioterapi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkFisioterapi.setForeground(new java.awt.Color(0, 0, 0));
        ChkFisioterapi.setText("Fisioterapi");
        ChkFisioterapi.setBorderPainted(true);
        ChkFisioterapi.setBorderPaintedFlat(true);
        ChkFisioterapi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkFisioterapi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkFisioterapi.setName("ChkFisioterapi"); // NOI18N
        ChkFisioterapi.setOpaque(false);
        ChkFisioterapi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkFisioterapi);
        ChkFisioterapi.setBounds(340, 2357, 80, 23);

        TrehabLain.setForeground(new java.awt.Color(0, 0, 0));
        TrehabLain.setToolTipText("Alt+C");
        TrehabLain.setName("TrehabLain"); // NOI18N
        TrehabLain.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TrehabLain);
        TrehabLain.setBounds(490, 2357, 440, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Lainnya : ");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(428, 2357, 60, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("DAFTAR MASALAH :");
        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(0, 2385, 140, 23);

        ChkHipertermi.setBackground(new java.awt.Color(255, 255, 250));
        ChkHipertermi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkHipertermi.setForeground(new java.awt.Color(0, 0, 0));
        ChkHipertermi.setText("Hipertermi");
        ChkHipertermi.setBorderPainted(true);
        ChkHipertermi.setBorderPaintedFlat(true);
        ChkHipertermi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkHipertermi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHipertermi.setName("ChkHipertermi"); // NOI18N
        ChkHipertermi.setOpaque(false);
        ChkHipertermi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkHipertermi);
        ChkHipertermi.setBounds(30, 2405, 110, 23);

        ChkNyeri.setBackground(new java.awt.Color(255, 255, 250));
        ChkNyeri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNyeri.setForeground(new java.awt.Color(0, 0, 0));
        ChkNyeri.setText("Nyeri");
        ChkNyeri.setBorderPainted(true);
        ChkNyeri.setBorderPaintedFlat(true);
        ChkNyeri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNyeri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNyeri.setName("ChkNyeri"); // NOI18N
        ChkNyeri.setOpaque(false);
        ChkNyeri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkNyeri);
        ChkNyeri.setBounds(30, 2433, 110, 23);

        ChkResiko.setBackground(new java.awt.Color(255, 255, 250));
        ChkResiko.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkResiko.setForeground(new java.awt.Color(0, 0, 0));
        ChkResiko.setText("(Resiko) Kurang Volume Cairan");
        ChkResiko.setBorderPainted(true);
        ChkResiko.setBorderPaintedFlat(true);
        ChkResiko.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkResiko.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkResiko.setName("ChkResiko"); // NOI18N
        ChkResiko.setOpaque(false);
        ChkResiko.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkResiko);
        ChkResiko.setBounds(30, 2461, 180, 23);

        ChkKelebihan.setBackground(new java.awt.Color(255, 255, 250));
        ChkKelebihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKelebihan.setForeground(new java.awt.Color(0, 0, 0));
        ChkKelebihan.setText("Kelebihan Volume Cairan");
        ChkKelebihan.setBorderPainted(true);
        ChkKelebihan.setBorderPaintedFlat(true);
        ChkKelebihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKelebihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKelebihan.setName("ChkKelebihan"); // NOI18N
        ChkKelebihan.setOpaque(false);
        ChkKelebihan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKelebihan);
        ChkKelebihan.setBounds(30, 2489, 180, 23);

        ChkBersihkan.setBackground(new java.awt.Color(255, 255, 250));
        ChkBersihkan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBersihkan.setForeground(new java.awt.Color(0, 0, 0));
        ChkBersihkan.setText("Bersihkan Jalan Nafas Tidak Efektif");
        ChkBersihkan.setBorderPainted(true);
        ChkBersihkan.setBorderPaintedFlat(true);
        ChkBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBersihkan.setName("ChkBersihkan"); // NOI18N
        ChkBersihkan.setOpaque(false);
        ChkBersihkan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkBersihkan);
        ChkBersihkan.setBounds(30, 2517, 200, 23);

        ChkPola.setBackground(new java.awt.Color(255, 255, 250));
        ChkPola.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPola.setForeground(new java.awt.Color(0, 0, 0));
        ChkPola.setText("Pola Nafas Tidak Efektif");
        ChkPola.setBorderPainted(true);
        ChkPola.setBorderPaintedFlat(true);
        ChkPola.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPola.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPola.setName("ChkPola"); // NOI18N
        ChkPola.setOpaque(false);
        ChkPola.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPola);
        ChkPola.setBounds(30, 2545, 150, 23);

        ChkGangguan.setBackground(new java.awt.Color(255, 255, 250));
        ChkGangguan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGangguan.setForeground(new java.awt.Color(0, 0, 0));
        ChkGangguan.setText("Gangguan Pertukaran Gas");
        ChkGangguan.setBorderPainted(true);
        ChkGangguan.setBorderPaintedFlat(true);
        ChkGangguan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGangguan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGangguan.setName("ChkGangguan"); // NOI18N
        ChkGangguan.setOpaque(false);
        ChkGangguan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkGangguan);
        ChkGangguan.setBounds(250, 2405, 160, 23);

        ChkCemas.setBackground(new java.awt.Color(255, 255, 250));
        ChkCemas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCemas.setForeground(new java.awt.Color(0, 0, 0));
        ChkCemas.setText("Cemas");
        ChkCemas.setBorderPainted(true);
        ChkCemas.setBorderPaintedFlat(true);
        ChkCemas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCemas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCemas.setName("ChkCemas"); // NOI18N
        ChkCemas.setOpaque(false);
        ChkCemas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkCemas);
        ChkCemas.setBounds(250, 2433, 110, 23);

        ChkKetidakseimbangan.setBackground(new java.awt.Color(255, 255, 250));
        ChkKetidakseimbangan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKetidakseimbangan.setForeground(new java.awt.Color(0, 0, 0));
        ChkKetidakseimbangan.setText("Ketidakseimbangan Nutrisi : Kurang Dari Kebutuhan Tubuh");
        ChkKetidakseimbangan.setBorderPainted(true);
        ChkKetidakseimbangan.setBorderPaintedFlat(true);
        ChkKetidakseimbangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKetidakseimbangan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKetidakseimbangan.setName("ChkKetidakseimbangan"); // NOI18N
        ChkKetidakseimbangan.setOpaque(false);
        ChkKetidakseimbangan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKetidakseimbangan);
        ChkKetidakseimbangan.setBounds(250, 2461, 317, 23);

        ChkPerubahan.setBackground(new java.awt.Color(255, 255, 250));
        ChkPerubahan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPerubahan.setForeground(new java.awt.Color(0, 0, 0));
        ChkPerubahan.setText("Perubahan Perfusi Jaringan Tidak Efektif");
        ChkPerubahan.setBorderPainted(true);
        ChkPerubahan.setBorderPaintedFlat(true);
        ChkPerubahan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPerubahan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPerubahan.setName("ChkPerubahan"); // NOI18N
        ChkPerubahan.setOpaque(false);
        ChkPerubahan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPerubahan);
        ChkPerubahan.setBounds(250, 2489, 230, 23);

        ChkPenurunan.setBackground(new java.awt.Color(255, 255, 250));
        ChkPenurunan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPenurunan.setForeground(new java.awt.Color(0, 0, 0));
        ChkPenurunan.setText("Penurunan Curah Jantung");
        ChkPenurunan.setBorderPainted(true);
        ChkPenurunan.setBorderPaintedFlat(true);
        ChkPenurunan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPenurunan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPenurunan.setName("ChkPenurunan"); // NOI18N
        ChkPenurunan.setOpaque(false);
        ChkPenurunan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPenurunan);
        ChkPenurunan.setBounds(250, 2517, 160, 23);

        ChkKerusakan.setBackground(new java.awt.Color(255, 255, 250));
        ChkKerusakan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKerusakan.setForeground(new java.awt.Color(0, 0, 0));
        ChkKerusakan.setText("Kerusakan Integritas Kulit / Jaringan");
        ChkKerusakan.setBorderPainted(true);
        ChkKerusakan.setBorderPaintedFlat(true);
        ChkKerusakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKerusakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKerusakan.setName("ChkKerusakan"); // NOI18N
        ChkKerusakan.setOpaque(false);
        ChkKerusakan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKerusakan);
        ChkKerusakan.setBounds(250, 2545, 210, 23);

        ChkIntoleransi.setBackground(new java.awt.Color(255, 255, 250));
        ChkIntoleransi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkIntoleransi.setForeground(new java.awt.Color(0, 0, 0));
        ChkIntoleransi.setText("Intoleransi Aktifitas");
        ChkIntoleransi.setBorderPainted(true);
        ChkIntoleransi.setBorderPaintedFlat(true);
        ChkIntoleransi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkIntoleransi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkIntoleransi.setName("ChkIntoleransi"); // NOI18N
        ChkIntoleransi.setOpaque(false);
        ChkIntoleransi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkIntoleransi);
        ChkIntoleransi.setBounds(578, 2405, 130, 23);

        ChkKurang.setBackground(new java.awt.Color(255, 255, 250));
        ChkKurang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKurang.setForeground(new java.awt.Color(0, 0, 0));
        ChkKurang.setText("Kurang Perawatan Diri");
        ChkKurang.setBorderPainted(true);
        ChkKurang.setBorderPaintedFlat(true);
        ChkKurang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKurang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKurang.setName("ChkKurang"); // NOI18N
        ChkKurang.setOpaque(false);
        ChkKurang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKurang);
        ChkKurang.setBounds(578, 2433, 140, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("Masalah Keperawatan Lain : ");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(578, 2461, 150, 23);

        scrollPane5.setName("scrollPane5"); // NOI18N

        TmasalahLain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TmasalahLain.setColumns(20);
        TmasalahLain.setRows(5);
        TmasalahLain.setName("TmasalahLain"); // NOI18N
        TmasalahLain.setPreferredSize(new java.awt.Dimension(162, 700));
        TmasalahLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmasalahLainKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(TmasalahLain);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(578, 2483, 340, 83);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Tgl. Asesmen : ");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(0, 2573, 100, 23);

        TtglAses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-04-2024" }));
        TtglAses.setDisplayFormat("dd-MM-yyyy");
        TtglAses.setName("TtglAses"); // NOI18N
        TtglAses.setOpaque(false);
        TtglAses.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglAses);
        TtglAses.setBounds(103, 2573, 90, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Jam : ");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(195, 2573, 40, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(238, 2573, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(290, 2573, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(343, 2573, 45, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Nama Perawat : ");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(390, 2573, 100, 23);

        TnmPerawat.setEditable(false);
        TnmPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawat.setToolTipText("Alt+C");
        TnmPerawat.setName("TnmPerawat"); // NOI18N
        TnmPerawat.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TnmPerawat);
        TnmPerawat.setBounds(490, 2573, 430, 23);

        BtnPerawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPerawat.setMnemonic('2');
        BtnPerawat.setToolTipText("Alt+2");
        BtnPerawat.setName("BtnPerawat"); // NOI18N
        BtnPerawat.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerawatActionPerformed(evt);
            }
        });
        FormInput.add(BtnPerawat);
        BtnPerawat.setBounds(920, 2573, 28, 23);

        BtnIMT.setForeground(new java.awt.Color(0, 0, 0));
        BtnIMT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnIMT.setText("Hitung IMT : ");
        BtnIMT.setToolTipText("");
        BtnIMT.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnIMT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnIMT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnIMT.setName("BtnIMT"); // NOI18N
        BtnIMT.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnIMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIMTActionPerformed(evt);
            }
        });
        BtnIMT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnIMTKeyPressed(evt);
            }
        });
        FormInput.add(BtnIMT);
        BtnIMT.setBounds(375, 493, 120, 23);

        TnmAlergiObat.setBackground(new java.awt.Color(245, 250, 240));
        TnmAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        TnmAlergiObat.setName("TnmAlergiObat"); // NOI18N
        TnmAlergiObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmAlergiObatKeyPressed(evt);
            }
        });
        FormInput.add(TnmAlergiObat);
        TnmAlergiObat.setBounds(136, 150, 330, 23);

        TnmAlergiMakanan.setBackground(new java.awt.Color(245, 250, 240));
        TnmAlergiMakanan.setForeground(new java.awt.Color(0, 0, 0));
        TnmAlergiMakanan.setName("TnmAlergiMakanan"); // NOI18N
        TnmAlergiMakanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmAlergiMakananKeyPressed(evt);
            }
        });
        FormInput.add(TnmAlergiMakanan);
        TnmAlergiMakanan.setBounds(136, 178, 330, 23);

        TnmAlergiLain.setBackground(new java.awt.Color(245, 250, 240));
        TnmAlergiLain.setForeground(new java.awt.Color(0, 0, 0));
        TnmAlergiLain.setName("TnmAlergiLain"); // NOI18N
        TnmAlergiLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmAlergiLainKeyPressed(evt);
            }
        });
        FormInput.add(TnmAlergiLain);
        TnmAlergiLain.setBounds(136, 206, 330, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("SEKARANG : ");
        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(0, 277, 163, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("RIWAYAT PENYAKIT  ");
        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(0, 338, 163, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("TERDAHULU : ");
        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(0, 353, 163, 23);

        ChkCampak.setBackground(new java.awt.Color(255, 255, 250));
        ChkCampak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCampak.setForeground(new java.awt.Color(0, 0, 0));
        ChkCampak.setText("Campak");
        ChkCampak.setBorderPainted(true);
        ChkCampak.setBorderPaintedFlat(true);
        ChkCampak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCampak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCampak.setName("ChkCampak"); // NOI18N
        ChkCampak.setOpaque(false);
        ChkCampak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkCampak);
        ChkCampak.setBounds(165, 338, 120, 23);

        ChkBatuk.setBackground(new java.awt.Color(255, 255, 250));
        ChkBatuk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBatuk.setForeground(new java.awt.Color(0, 0, 0));
        ChkBatuk.setText("Batuk Rejan");
        ChkBatuk.setBorderPainted(true);
        ChkBatuk.setBorderPaintedFlat(true);
        ChkBatuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBatuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBatuk.setName("ChkBatuk"); // NOI18N
        ChkBatuk.setOpaque(false);
        ChkBatuk.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkBatuk);
        ChkBatuk.setBounds(165, 366, 120, 23);

        ChkSakit.setBackground(new java.awt.Color(255, 255, 250));
        ChkSakit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSakit.setForeground(new java.awt.Color(0, 0, 0));
        ChkSakit.setText("Sakit Tenggorokan");
        ChkSakit.setBorderPainted(true);
        ChkSakit.setBorderPaintedFlat(true);
        ChkSakit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSakit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSakit.setName("ChkSakit"); // NOI18N
        ChkSakit.setOpaque(false);
        ChkSakit.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkSakit);
        ChkSakit.setBounds(165, 394, 120, 23);

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
        ChkDiare.setBounds(295, 338, 60, 23);

        ChkSesak.setBackground(new java.awt.Color(255, 255, 250));
        ChkSesak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSesak.setForeground(new java.awt.Color(0, 0, 0));
        ChkSesak.setText("Sesak");
        ChkSesak.setBorderPainted(true);
        ChkSesak.setBorderPaintedFlat(true);
        ChkSesak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSesak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSesak.setName("ChkSesak"); // NOI18N
        ChkSesak.setOpaque(false);
        ChkSesak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkSesak);
        ChkSesak.setBounds(295, 366, 60, 23);

        ChkDifteri.setBackground(new java.awt.Color(255, 255, 250));
        ChkDifteri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDifteri.setForeground(new java.awt.Color(0, 0, 0));
        ChkDifteri.setText("Difteri");
        ChkDifteri.setBorderPainted(true);
        ChkDifteri.setBorderPaintedFlat(true);
        ChkDifteri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDifteri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDifteri.setName("ChkDifteri"); // NOI18N
        ChkDifteri.setOpaque(false);
        ChkDifteri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDifteri);
        ChkDifteri.setBounds(365, 338, 70, 23);

        ChkKuning.setBackground(new java.awt.Color(255, 255, 250));
        ChkKuning.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKuning.setForeground(new java.awt.Color(0, 0, 0));
        ChkKuning.setText("Kuning");
        ChkKuning.setBorderPainted(true);
        ChkKuning.setBorderPaintedFlat(true);
        ChkKuning.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKuning.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKuning.setName("ChkKuning"); // NOI18N
        ChkKuning.setOpaque(false);
        ChkKuning.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKuning);
        ChkKuning.setBounds(365, 366, 70, 23);

        ChkLainya.setBackground(new java.awt.Color(255, 255, 250));
        ChkLainya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLainya.setForeground(new java.awt.Color(0, 0, 0));
        ChkLainya.setText("Lainnya");
        ChkLainya.setBorderPainted(true);
        ChkLainya.setBorderPaintedFlat(true);
        ChkLainya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLainya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLainya.setName("ChkLainya"); // NOI18N
        ChkLainya.setOpaque(false);
        ChkLainya.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkLainya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkLainyaActionPerformed(evt);
            }
        });
        FormInput.add(ChkLainya);
        ChkLainya.setBounds(365, 394, 66, 23);

        ChkTetanus.setBackground(new java.awt.Color(255, 255, 250));
        ChkTetanus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTetanus.setForeground(new java.awt.Color(0, 0, 0));
        ChkTetanus.setText("Tetanus");
        ChkTetanus.setBorderPainted(true);
        ChkTetanus.setBorderPaintedFlat(true);
        ChkTetanus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTetanus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTetanus.setName("ChkTetanus"); // NOI18N
        ChkTetanus.setOpaque(false);
        ChkTetanus.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTetanus);
        ChkTetanus.setBounds(440, 338, 100, 23);

        ChkDemam.setBackground(new java.awt.Color(255, 255, 250));
        ChkDemam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDemam.setForeground(new java.awt.Color(0, 0, 0));
        ChkDemam.setText("Demam Tifoid");
        ChkDemam.setBorderPainted(true);
        ChkDemam.setBorderPaintedFlat(true);
        ChkDemam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDemam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDemam.setName("ChkDemam"); // NOI18N
        ChkDemam.setOpaque(false);
        ChkDemam.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDemam);
        ChkDemam.setBounds(440, 366, 100, 23);

        ChkKejang.setBackground(new java.awt.Color(255, 255, 250));
        ChkKejang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKejang.setForeground(new java.awt.Color(0, 0, 0));
        ChkKejang.setText("Kejang");
        ChkKejang.setBorderPainted(true);
        ChkKejang.setBorderPaintedFlat(true);
        ChkKejang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKejang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKejang.setName("ChkKejang"); // NOI18N
        ChkKejang.setOpaque(false);
        ChkKejang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKejang);
        ChkKejang.setBounds(545, 338, 110, 23);

        ChkUrtikaria.setBackground(new java.awt.Color(255, 255, 250));
        ChkUrtikaria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkUrtikaria.setForeground(new java.awt.Color(0, 0, 0));
        ChkUrtikaria.setText("Urtikaria/Liman");
        ChkUrtikaria.setBorderPainted(true);
        ChkUrtikaria.setBorderPaintedFlat(true);
        ChkUrtikaria.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkUrtikaria.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkUrtikaria.setName("ChkUrtikaria"); // NOI18N
        ChkUrtikaria.setOpaque(false);
        ChkUrtikaria.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkUrtikaria);
        ChkUrtikaria.setBounds(545, 366, 110, 23);

        ChkTbc.setBackground(new java.awt.Color(255, 255, 250));
        ChkTbc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTbc.setForeground(new java.awt.Color(0, 0, 0));
        ChkTbc.setText("TBC");
        ChkTbc.setBorderPainted(true);
        ChkTbc.setBorderPaintedFlat(true);
        ChkTbc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTbc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTbc.setName("ChkTbc"); // NOI18N
        ChkTbc.setOpaque(false);
        ChkTbc.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTbc);
        ChkTbc.setBounds(660, 338, 70, 23);

        ChkCacing.setBackground(new java.awt.Color(255, 255, 250));
        ChkCacing.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCacing.setForeground(new java.awt.Color(0, 0, 0));
        ChkCacing.setText("Cacing");
        ChkCacing.setBorderPainted(true);
        ChkCacing.setBorderPaintedFlat(true);
        ChkCacing.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCacing.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCacing.setName("ChkCacing"); // NOI18N
        ChkCacing.setOpaque(false);
        ChkCacing.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkCacing);
        ChkCacing.setBounds(660, 366, 70, 23);

        Tlainnya.setBackground(new java.awt.Color(245, 250, 240));
        Tlainnya.setForeground(new java.awt.Color(0, 0, 0));
        Tlainnya.setName("Tlainnya"); // NOI18N
        Tlainnya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainnyaKeyPressed(evt);
            }
        });
        FormInput.add(Tlainnya);
        Tlainnya.setBounds(431, 394, 485, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("RIWAYAT IMUNISASI : ");
        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(0, 788, 150, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Jenis Vaksin : ");
        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(0, 810, 135, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Hepatitis B : ");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(0, 838, 135, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("Polio : ");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(0, 866, 135, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("BCG : ");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(0, 894, 135, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("DPT : ");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(0, 922, 135, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("Meningitis : ");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(0, 950, 135, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("Campak : ");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(0, 978, 135, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel111.setText("Dasar (Umur)");
        jLabel111.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(136, 810, 250, 23);

        Thepa1.setBackground(new java.awt.Color(245, 250, 240));
        Thepa1.setForeground(new java.awt.Color(0, 0, 0));
        Thepa1.setName("Thepa1"); // NOI18N
        Thepa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Thepa1KeyPressed(evt);
            }
        });
        FormInput.add(Thepa1);
        Thepa1.setBounds(136, 838, 60, 23);

        Thepa2.setBackground(new java.awt.Color(245, 250, 240));
        Thepa2.setForeground(new java.awt.Color(0, 0, 0));
        Thepa2.setName("Thepa2"); // NOI18N
        Thepa2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Thepa2KeyPressed(evt);
            }
        });
        FormInput.add(Thepa2);
        Thepa2.setBounds(200, 838, 60, 23);

        Thepa3.setBackground(new java.awt.Color(245, 250, 240));
        Thepa3.setForeground(new java.awt.Color(0, 0, 0));
        Thepa3.setName("Thepa3"); // NOI18N
        Thepa3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Thepa3KeyPressed(evt);
            }
        });
        FormInput.add(Thepa3);
        Thepa3.setBounds(265, 838, 60, 23);

        Thepa4.setBackground(new java.awt.Color(245, 250, 240));
        Thepa4.setForeground(new java.awt.Color(0, 0, 0));
        Thepa4.setName("Thepa4"); // NOI18N
        Thepa4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Thepa4KeyPressed(evt);
            }
        });
        FormInput.add(Thepa4);
        Thepa4.setBounds(330, 838, 60, 23);

        ThepaUlang.setBackground(new java.awt.Color(245, 250, 240));
        ThepaUlang.setForeground(new java.awt.Color(0, 0, 0));
        ThepaUlang.setName("ThepaUlang"); // NOI18N
        ThepaUlang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThepaUlangKeyPressed(evt);
            }
        });
        FormInput.add(ThepaUlang);
        ThepaUlang.setBounds(395, 838, 60, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("Ulangan (Umur)");
        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(396, 810, 110, 23);

        Tpolio1.setBackground(new java.awt.Color(245, 250, 240));
        Tpolio1.setForeground(new java.awt.Color(0, 0, 0));
        Tpolio1.setName("Tpolio1"); // NOI18N
        Tpolio1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tpolio1KeyPressed(evt);
            }
        });
        FormInput.add(Tpolio1);
        Tpolio1.setBounds(136, 866, 60, 23);

        Tpolio2.setBackground(new java.awt.Color(245, 250, 240));
        Tpolio2.setForeground(new java.awt.Color(0, 0, 0));
        Tpolio2.setName("Tpolio2"); // NOI18N
        Tpolio2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tpolio2KeyPressed(evt);
            }
        });
        FormInput.add(Tpolio2);
        Tpolio2.setBounds(200, 866, 60, 23);

        Tpolio3.setBackground(new java.awt.Color(245, 250, 240));
        Tpolio3.setForeground(new java.awt.Color(0, 0, 0));
        Tpolio3.setName("Tpolio3"); // NOI18N
        Tpolio3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tpolio3KeyPressed(evt);
            }
        });
        FormInput.add(Tpolio3);
        Tpolio3.setBounds(265, 866, 60, 23);

        Tpolio4.setBackground(new java.awt.Color(245, 250, 240));
        Tpolio4.setForeground(new java.awt.Color(0, 0, 0));
        Tpolio4.setName("Tpolio4"); // NOI18N
        Tpolio4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tpolio4KeyPressed(evt);
            }
        });
        FormInput.add(Tpolio4);
        Tpolio4.setBounds(330, 866, 60, 23);

        TpolioUlang.setBackground(new java.awt.Color(245, 250, 240));
        TpolioUlang.setForeground(new java.awt.Color(0, 0, 0));
        TpolioUlang.setName("TpolioUlang"); // NOI18N
        TpolioUlang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpolioUlangKeyPressed(evt);
            }
        });
        FormInput.add(TpolioUlang);
        TpolioUlang.setBounds(395, 866, 60, 23);

        TbcgDasar.setBackground(new java.awt.Color(245, 250, 240));
        TbcgDasar.setForeground(new java.awt.Color(0, 0, 0));
        TbcgDasar.setName("TbcgDasar"); // NOI18N
        TbcgDasar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbcgDasarKeyPressed(evt);
            }
        });
        FormInput.add(TbcgDasar);
        TbcgDasar.setBounds(136, 894, 60, 23);

        TbcgUlang.setBackground(new java.awt.Color(245, 250, 240));
        TbcgUlang.setForeground(new java.awt.Color(0, 0, 0));
        TbcgUlang.setName("TbcgUlang"); // NOI18N
        TbcgUlang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbcgUlangKeyPressed(evt);
            }
        });
        FormInput.add(TbcgUlang);
        TbcgUlang.setBounds(395, 894, 60, 23);

        Tdpt1.setBackground(new java.awt.Color(245, 250, 240));
        Tdpt1.setForeground(new java.awt.Color(0, 0, 0));
        Tdpt1.setName("Tdpt1"); // NOI18N
        Tdpt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdpt1KeyPressed(evt);
            }
        });
        FormInput.add(Tdpt1);
        Tdpt1.setBounds(136, 922, 60, 23);

        Tdpt2.setBackground(new java.awt.Color(245, 250, 240));
        Tdpt2.setForeground(new java.awt.Color(0, 0, 0));
        Tdpt2.setName("Tdpt2"); // NOI18N
        Tdpt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdpt2KeyPressed(evt);
            }
        });
        FormInput.add(Tdpt2);
        Tdpt2.setBounds(200, 922, 60, 23);

        Tdpt3.setBackground(new java.awt.Color(245, 250, 240));
        Tdpt3.setForeground(new java.awt.Color(0, 0, 0));
        Tdpt3.setName("Tdpt3"); // NOI18N
        Tdpt3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdpt3KeyPressed(evt);
            }
        });
        FormInput.add(Tdpt3);
        Tdpt3.setBounds(265, 922, 60, 23);

        TdptUlang.setBackground(new java.awt.Color(245, 250, 240));
        TdptUlang.setForeground(new java.awt.Color(0, 0, 0));
        TdptUlang.setName("TdptUlang"); // NOI18N
        TdptUlang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdptUlangKeyPressed(evt);
            }
        });
        FormInput.add(TdptUlang);
        TdptUlang.setBounds(395, 922, 60, 23);

        Tmening1.setBackground(new java.awt.Color(245, 250, 240));
        Tmening1.setForeground(new java.awt.Color(0, 0, 0));
        Tmening1.setName("Tmening1"); // NOI18N
        Tmening1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tmening1KeyPressed(evt);
            }
        });
        FormInput.add(Tmening1);
        Tmening1.setBounds(136, 950, 60, 23);

        Tmening2.setBackground(new java.awt.Color(245, 250, 240));
        Tmening2.setForeground(new java.awt.Color(0, 0, 0));
        Tmening2.setName("Tmening2"); // NOI18N
        Tmening2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tmening2KeyPressed(evt);
            }
        });
        FormInput.add(Tmening2);
        Tmening2.setBounds(200, 950, 60, 23);

        Tmening3.setBackground(new java.awt.Color(245, 250, 240));
        Tmening3.setForeground(new java.awt.Color(0, 0, 0));
        Tmening3.setName("Tmening3"); // NOI18N
        Tmening3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tmening3KeyPressed(evt);
            }
        });
        FormInput.add(Tmening3);
        Tmening3.setBounds(265, 950, 60, 23);

        TmeningUlang.setBackground(new java.awt.Color(245, 250, 240));
        TmeningUlang.setForeground(new java.awt.Color(0, 0, 0));
        TmeningUlang.setName("TmeningUlang"); // NOI18N
        TmeningUlang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmeningUlangKeyPressed(evt);
            }
        });
        FormInput.add(TmeningUlang);
        TmeningUlang.setBounds(395, 950, 60, 23);

        TcampakDasar.setBackground(new java.awt.Color(245, 250, 240));
        TcampakDasar.setForeground(new java.awt.Color(0, 0, 0));
        TcampakDasar.setName("TcampakDasar"); // NOI18N
        TcampakDasar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcampakDasarKeyPressed(evt);
            }
        });
        FormInput.add(TcampakDasar);
        TcampakDasar.setBounds(136, 978, 60, 23);

        TcampakUlang.setBackground(new java.awt.Color(245, 250, 240));
        TcampakUlang.setForeground(new java.awt.Color(0, 0, 0));
        TcampakUlang.setName("TcampakUlang"); // NOI18N
        TcampakUlang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcampakUlangKeyPressed(evt);
            }
        });
        FormInput.add(TcampakUlang);
        TcampakUlang.setBounds(395, 978, 60, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("RIWAYAT PERKEMBANGAN : ");
        jLabel113.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(0, 1006, 180, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel114.setText("Motorik Kasar");
        jLabel114.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(0, 1022, 200, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel115.setText("Kemampuan");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(0, 1037, 135, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("Menegakkan Kepala : ");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(0, 1065, 135, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("Tengkurap : ");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(0, 1093, 135, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("Duduk : ");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(0, 1121, 135, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("Berjalan : ");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(0, 1149, 135, 23);

        TtegakanKepala.setBackground(new java.awt.Color(245, 250, 240));
        TtegakanKepala.setForeground(new java.awt.Color(0, 0, 0));
        TtegakanKepala.setName("TtegakanKepala"); // NOI18N
        TtegakanKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtegakanKepalaKeyPressed(evt);
            }
        });
        FormInput.add(TtegakanKepala);
        TtegakanKepala.setBounds(136, 1065, 60, 23);

        Ttengkurap.setBackground(new java.awt.Color(245, 250, 240));
        Ttengkurap.setForeground(new java.awt.Color(0, 0, 0));
        Ttengkurap.setName("Ttengkurap"); // NOI18N
        Ttengkurap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtengkurapKeyPressed(evt);
            }
        });
        FormInput.add(Ttengkurap);
        Ttengkurap.setBounds(136, 1093, 60, 23);

        Tduduk.setBackground(new java.awt.Color(245, 250, 240));
        Tduduk.setForeground(new java.awt.Color(0, 0, 0));
        Tduduk.setName("Tduduk"); // NOI18N
        Tduduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdudukKeyPressed(evt);
            }
        });
        FormInput.add(Tduduk);
        Tduduk.setBounds(136, 1121, 60, 23);

        Tberjalan.setBackground(new java.awt.Color(245, 250, 240));
        Tberjalan.setForeground(new java.awt.Color(0, 0, 0));
        Tberjalan.setName("Tberjalan"); // NOI18N
        Tberjalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TberjalanKeyPressed(evt);
            }
        });
        FormInput.add(Tberjalan);
        Tberjalan.setBounds(136, 1149, 60, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel120.setText("Umur");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(136, 1037, 60, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("Menggenggam Mainan : ");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(200, 1065, 135, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("Mencari benda/mainan : ");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(200, 1093, 135, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("Mencoret-coret kertas : ");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(200, 1121, 135, 23);

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setText("Mengenal 2 - 4 warna : ");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(200, 1149, 135, 23);

        Tgenggam.setBackground(new java.awt.Color(245, 250, 240));
        Tgenggam.setForeground(new java.awt.Color(0, 0, 0));
        Tgenggam.setName("Tgenggam"); // NOI18N
        Tgenggam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgenggamKeyPressed(evt);
            }
        });
        FormInput.add(Tgenggam);
        Tgenggam.setBounds(336, 1065, 60, 23);

        Tmencari.setBackground(new java.awt.Color(245, 250, 240));
        Tmencari.setForeground(new java.awt.Color(0, 0, 0));
        Tmencari.setName("Tmencari"); // NOI18N
        Tmencari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmencariKeyPressed(evt);
            }
        });
        FormInput.add(Tmencari);
        Tmencari.setBounds(336, 1093, 60, 23);

        Tmencoret.setBackground(new java.awt.Color(245, 250, 240));
        Tmencoret.setForeground(new java.awt.Color(0, 0, 0));
        Tmencoret.setName("Tmencoret"); // NOI18N
        Tmencoret.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmencoretKeyPressed(evt);
            }
        });
        FormInput.add(Tmencoret);
        Tmencoret.setBounds(336, 1121, 60, 23);

        Tmengenal24.setBackground(new java.awt.Color(245, 250, 240));
        Tmengenal24.setForeground(new java.awt.Color(0, 0, 0));
        Tmengenal24.setName("Tmengenal24"); // NOI18N
        Tmengenal24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tmengenal24KeyPressed(evt);
            }
        });
        FormInput.add(Tmengenal24);
        Tmengenal24.setBounds(336, 1149, 60, 23);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel125.setText("Kemampuan");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(200, 1037, 135, 23);

        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel126.setText("Umur");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(336, 1037, 60, 23);

        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel127.setText("Motorik Halus");
        jLabel127.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(200, 1022, 200, 23);

        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setText("Berceloteh tanpa arti : ");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(400, 1065, 200, 23);

        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setText("Mengucapkan ma ma/da da : ");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(400, 1093, 200, 23);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setText("Menyebutkan bagian tubuhnya : ");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(400, 1121, 200, 23);

        jLabel131.setForeground(new java.awt.Color(0, 0, 0));
        jLabel131.setText("Menyebut 3 - 6 kata yang punya arti : ");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(400, 1149, 200, 23);

        Tberceloteh.setBackground(new java.awt.Color(245, 250, 240));
        Tberceloteh.setForeground(new java.awt.Color(0, 0, 0));
        Tberceloteh.setName("Tberceloteh"); // NOI18N
        Tberceloteh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbercelotehKeyPressed(evt);
            }
        });
        FormInput.add(Tberceloteh);
        Tberceloteh.setBounds(602, 1065, 60, 23);

        Tmengucapkan.setBackground(new java.awt.Color(245, 250, 240));
        Tmengucapkan.setForeground(new java.awt.Color(0, 0, 0));
        Tmengucapkan.setName("Tmengucapkan"); // NOI18N
        Tmengucapkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmengucapkanKeyPressed(evt);
            }
        });
        FormInput.add(Tmengucapkan);
        Tmengucapkan.setBounds(602, 1093, 60, 23);

        Tmenyebutkan.setBackground(new java.awt.Color(245, 250, 240));
        Tmenyebutkan.setForeground(new java.awt.Color(0, 0, 0));
        Tmenyebutkan.setName("Tmenyebutkan"); // NOI18N
        Tmenyebutkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmenyebutkanKeyPressed(evt);
            }
        });
        FormInput.add(Tmenyebutkan);
        Tmenyebutkan.setBounds(602, 1121, 60, 23);

        Tmenyebut36.setBackground(new java.awt.Color(245, 250, 240));
        Tmenyebut36.setForeground(new java.awt.Color(0, 0, 0));
        Tmenyebut36.setName("Tmenyebut36"); // NOI18N
        Tmenyebut36.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tmenyebut36KeyPressed(evt);
            }
        });
        FormInput.add(Tmenyebut36);
        Tmenyebut36.setBounds(602, 1149, 60, 23);

        jLabel132.setForeground(new java.awt.Color(0, 0, 0));
        jLabel132.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel132.setText("Kemampuan");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(400, 1037, 200, 23);

        jLabel133.setForeground(new java.awt.Color(0, 0, 0));
        jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel133.setText("Umur");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(602, 1037, 60, 23);

        jLabel134.setForeground(new java.awt.Color(0, 0, 0));
        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel134.setText("Bicara");
        jLabel134.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(400, 1022, 260, 23);

        jLabel135.setForeground(new java.awt.Color(0, 0, 0));
        jLabel135.setText("Tersenyum : ");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(667, 1065, 155, 23);

        jLabel136.setForeground(new java.awt.Color(0, 0, 0));
        jLabel136.setText("Bermain tepuk/cilukba : ");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(667, 1093, 155, 23);

        jLabel137.setForeground(new java.awt.Color(0, 0, 0));
        jLabel137.setText("Mengenal anggota keluarga : ");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(667, 1121, 155, 23);

        jLabel138.setForeground(new java.awt.Color(0, 0, 0));
        jLabel138.setText("Makan dan minum sendiri : ");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(667, 1149, 155, 23);

        Ttersenyum.setBackground(new java.awt.Color(245, 250, 240));
        Ttersenyum.setForeground(new java.awt.Color(0, 0, 0));
        Ttersenyum.setName("Ttersenyum"); // NOI18N
        Ttersenyum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtersenyumKeyPressed(evt);
            }
        });
        FormInput.add(Ttersenyum);
        Ttersenyum.setBounds(824, 1065, 60, 23);

        Tbermain.setBackground(new java.awt.Color(245, 250, 240));
        Tbermain.setForeground(new java.awt.Color(0, 0, 0));
        Tbermain.setName("Tbermain"); // NOI18N
        Tbermain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbermainKeyPressed(evt);
            }
        });
        FormInput.add(Tbermain);
        Tbermain.setBounds(824, 1093, 60, 23);

        TmengenalAnggota.setBackground(new java.awt.Color(245, 250, 240));
        TmengenalAnggota.setForeground(new java.awt.Color(0, 0, 0));
        TmengenalAnggota.setName("TmengenalAnggota"); // NOI18N
        TmengenalAnggota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmengenalAnggotaKeyPressed(evt);
            }
        });
        FormInput.add(TmengenalAnggota);
        TmengenalAnggota.setBounds(824, 1121, 60, 23);

        Tmakan.setBackground(new java.awt.Color(245, 250, 240));
        Tmakan.setForeground(new java.awt.Color(0, 0, 0));
        Tmakan.setName("Tmakan"); // NOI18N
        Tmakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmakanKeyPressed(evt);
            }
        });
        FormInput.add(Tmakan);
        Tmakan.setBounds(824, 1149, 60, 23);

        jLabel139.setForeground(new java.awt.Color(0, 0, 0));
        jLabel139.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel139.setText("Kemampuan");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(667, 1037, 155, 23);

        jLabel141.setForeground(new java.awt.Color(0, 0, 0));
        jLabel141.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel141.setText("Umur");
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(824, 1037, 60, 23);

        jLabel142.setForeground(new java.awt.Color(0, 0, 0));
        jLabel142.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel142.setText("Sosial Emosional");
        jLabel142.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput.add(jLabel142);
        jLabel142.setBounds(667, 1022, 220, 23);

        jLabel143.setForeground(new java.awt.Color(0, 0, 0));
        jLabel143.setText("Saat ini sekolah : ");
        jLabel143.setName("jLabel143"); // NOI18N
        FormInput.add(jLabel143);
        jLabel143.setBounds(0, 1177, 135, 23);

        cmbSekolah.setForeground(new java.awt.Color(0, 0, 0));
        cmbSekolah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbSekolah.setName("cmbSekolah"); // NOI18N
        FormInput.add(cmbSekolah);
        cmbSekolah.setBounds(136, 1177, 60, 23);

        jLabel144.setForeground(new java.awt.Color(0, 0, 0));
        jLabel144.setText("Kelas : ");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput.add(jLabel144);
        jLabel144.setBounds(200, 1177, 50, 23);

        Tkelas.setBackground(new java.awt.Color(245, 250, 240));
        Tkelas.setForeground(new java.awt.Color(0, 0, 0));
        Tkelas.setName("Tkelas"); // NOI18N
        Tkelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkelasKeyPressed(evt);
            }
        });
        FormInput.add(Tkelas);
        Tkelas.setBounds(250, 1177, 70, 23);

        jLabel145.setForeground(new java.awt.Color(0, 0, 0));
        jLabel145.setText("SKRINING GIZI : ");
        jLabel145.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel145.setName("jLabel145"); // NOI18N
        FormInput.add(jLabel145);
        jLabel145.setBounds(0, 1993, 120, 23);

        jLabel146.setForeground(new java.awt.Color(0, 0, 0));
        jLabel146.setText("ANAK (berdasarkan modifikasi from STRONGKids)");
        jLabel146.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(0, 2009, 310, 23);

        ChkGizi1.setBackground(new java.awt.Color(255, 255, 250));
        ChkGizi1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGizi1.setForeground(new java.awt.Color(0, 0, 0));
        ChkGizi1.setText("1. Terdapat penurunan berat badan atau berat badan menetap (pada bayi < 1 tahun) selama >= 2 bulan");
        ChkGizi1.setBorderPainted(true);
        ChkGizi1.setBorderPaintedFlat(true);
        ChkGizi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGizi1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGizi1.setName("ChkGizi1"); // NOI18N
        ChkGizi1.setOpaque(false);
        ChkGizi1.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkGizi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkGizi1ActionPerformed(evt);
            }
        });
        FormInput.add(ChkGizi1);
        ChkGizi1.setBounds(30, 2037, 550, 23);

        ChkGizi2.setBackground(new java.awt.Color(255, 255, 250));
        ChkGizi2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGizi2.setForeground(new java.awt.Color(0, 0, 0));
        ChkGizi2.setText("2. Terdapat tanda-tanda klinis gangguan gizi (tampak kurus, gemuk, pendek, edema, moon face, tampak tua, iga gambang,");
        ChkGizi2.setBorderPainted(true);
        ChkGizi2.setBorderPaintedFlat(true);
        ChkGizi2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGizi2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGizi2.setName("ChkGizi2"); // NOI18N
        ChkGizi2.setOpaque(false);
        ChkGizi2.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkGizi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkGizi2ActionPerformed(evt);
            }
        });
        FormInput.add(ChkGizi2);
        ChkGizi2.setBounds(30, 2065, 620, 23);

        labelChkGizi2.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi2.setText("baggy pant, anoreksia) selama 1 bulan terakhir");
        labelChkGizi2.setName("labelChkGizi2"); // NOI18N
        FormInput.add(labelChkGizi2);
        labelChkGizi2.setBounds(64, 2081, 260, 23);

        ChkGizi3.setBackground(new java.awt.Color(255, 255, 250));
        ChkGizi3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGizi3.setForeground(new java.awt.Color(0, 0, 0));
        ChkGizi3.setText("3. Terdapat salah satu penyakit/kondisi yang beresiko mengakibatkan malnutrisi berikut : ");
        ChkGizi3.setBorderPainted(true);
        ChkGizi3.setBorderPaintedFlat(true);
        ChkGizi3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGizi3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGizi3.setName("ChkGizi3"); // NOI18N
        ChkGizi3.setOpaque(false);
        ChkGizi3.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkGizi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkGizi3ActionPerformed(evt);
            }
        });
        FormInput.add(ChkGizi3);
        ChkGizi3.setBounds(30, 2109, 460, 23);

        labelChkGizi3A.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi3A.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi3A.setText("* Diare berat (> 5 x/hari) dan atau muntah (> 3 x/hari)");
        labelChkGizi3A.setName("labelChkGizi3A"); // NOI18N
        FormInput.add(labelChkGizi3A);
        labelChkGizi3A.setBounds(64, 2125, 300, 23);

        labelChkGizi3B.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi3B.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi3B.setText("* Penurunan asupan makanan selama lebih dari 7 hari");
        labelChkGizi3B.setName("labelChkGizi3B"); // NOI18N
        FormInput.add(labelChkGizi3B);
        labelChkGizi3B.setBounds(64, 2141, 300, 23);

        ChkGizi4.setBackground(new java.awt.Color(255, 255, 250));
        ChkGizi4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGizi4.setForeground(new java.awt.Color(0, 0, 0));
        ChkGizi4.setText("4. Terdapat penyakit-penyakit atau keadaan yang meningkatkan resiko malnutrisi antara lain : ");
        ChkGizi4.setBorderPainted(true);
        ChkGizi4.setBorderPaintedFlat(true);
        ChkGizi4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGizi4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGizi4.setName("ChkGizi4"); // NOI18N
        ChkGizi4.setOpaque(false);
        ChkGizi4.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkGizi4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkGizi4ActionPerformed(evt);
            }
        });
        FormInput.add(ChkGizi4);
        ChkGizi4.setBounds(30, 2169, 490, 23);

        labelChkGizi41.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi41.setText("* Diare kronik > 2 minggu");
        labelChkGizi41.setName("labelChkGizi41"); // NOI18N
        FormInput.add(labelChkGizi41);
        labelChkGizi41.setBounds(64, 2185, 210, 23);

        labelChkGizi42.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi42.setText("* Penyakit Jantung bawaan (tersangka)");
        labelChkGizi42.setName("labelChkGizi42"); // NOI18N
        FormInput.add(labelChkGizi42);
        labelChkGizi42.setBounds(64, 2201, 210, 23);

        labelChkGizi43.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi43.setText("* Infeksi HIV (tersangka)");
        labelChkGizi43.setName("labelChkGizi43"); // NOI18N
        FormInput.add(labelChkGizi43);
        labelChkGizi43.setBounds(64, 2217, 210, 23);

        labelChkGizi44.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi44.setText("* Kelainan anatomi bawaan");
        labelChkGizi44.setName("labelChkGizi44"); // NOI18N
        FormInput.add(labelChkGizi44);
        labelChkGizi44.setBounds(64, 2233, 210, 23);

        labelChkGizi45.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi45.setText("* Kelainan metabolisme bawaan");
        labelChkGizi45.setName("labelChkGizi45"); // NOI18N
        FormInput.add(labelChkGizi45);
        labelChkGizi45.setBounds(64, 2249, 210, 23);

        labelChkGizi46.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi46.setText("* Retardasi mental");
        labelChkGizi46.setName("labelChkGizi46"); // NOI18N
        FormInput.add(labelChkGizi46);
        labelChkGizi46.setBounds(64, 2265, 210, 23);

        labelChkGizi47.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi47.setText("* Keterlambatan perkembangan");
        labelChkGizi47.setName("labelChkGizi47"); // NOI18N
        FormInput.add(labelChkGizi47);
        labelChkGizi47.setBounds(64, 2281, 210, 23);

        labelChkGizi48.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi48.setText("* Kanker (tersangka)");
        labelChkGizi48.setName("labelChkGizi48"); // NOI18N
        FormInput.add(labelChkGizi48);
        labelChkGizi48.setBounds(300, 2185, 170, 23);

        labelChkGizi49.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi49.setText("* Penyakit hati/ginjal kronik");
        labelChkGizi49.setName("labelChkGizi49"); // NOI18N
        FormInput.add(labelChkGizi49);
        labelChkGizi49.setBounds(300, 2201, 170, 23);

        labelChkGizi410.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi410.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi410.setText("* TB Paru");
        labelChkGizi410.setName("labelChkGizi410"); // NOI18N
        FormInput.add(labelChkGizi410);
        labelChkGizi410.setBounds(300, 2217, 170, 23);

        labelChkGizi411.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi411.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi411.setText("* Rencana/pasca operasi mayor");
        labelChkGizi411.setName("labelChkGizi411"); // NOI18N
        FormInput.add(labelChkGizi411);
        labelChkGizi411.setBounds(300, 2233, 170, 23);

        labelChkGizi412.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi412.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi412.setText("* Luka bakar luas");
        labelChkGizi412.setName("labelChkGizi412"); // NOI18N
        FormInput.add(labelChkGizi412);
        labelChkGizi412.setBounds(300, 2249, 170, 23);

        labelChkGizi413.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi413.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi413.setText("* Terpasang stoma");
        labelChkGizi413.setName("labelChkGizi413"); // NOI18N
        FormInput.add(labelChkGizi413);
        labelChkGizi413.setBounds(300, 2265, 170, 23);

        labelChkGizi414.setForeground(new java.awt.Color(0, 0, 0));
        labelChkGizi414.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChkGizi414.setText("* Trauma");
        labelChkGizi414.setName("labelChkGizi414"); // NOI18N
        FormInput.add(labelChkGizi414);
        labelChkGizi414.setBounds(300, 2281, 170, 23);

        TskorGizi1.setEditable(false);
        TskorGizi1.setForeground(new java.awt.Color(0, 0, 0));
        TskorGizi1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorGizi1.setText("0");
        TskorGizi1.setFocusTraversalPolicyProvider(true);
        TskorGizi1.setName("TskorGizi1"); // NOI18N
        FormInput.add(TskorGizi1);
        TskorGizi1.setBounds(655, 2037, 40, 23);

        TskorGizi2.setEditable(false);
        TskorGizi2.setForeground(new java.awt.Color(0, 0, 0));
        TskorGizi2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorGizi2.setText("0");
        TskorGizi2.setFocusTraversalPolicyProvider(true);
        TskorGizi2.setName("TskorGizi2"); // NOI18N
        FormInput.add(TskorGizi2);
        TskorGizi2.setBounds(655, 2065, 40, 23);

        TskorGizi3.setEditable(false);
        TskorGizi3.setForeground(new java.awt.Color(0, 0, 0));
        TskorGizi3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorGizi3.setText("0");
        TskorGizi3.setFocusTraversalPolicyProvider(true);
        TskorGizi3.setName("TskorGizi3"); // NOI18N
        FormInput.add(TskorGizi3);
        TskorGizi3.setBounds(655, 2109, 40, 23);

        TskorGizi4.setEditable(false);
        TskorGizi4.setForeground(new java.awt.Color(0, 0, 0));
        TskorGizi4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorGizi4.setText("0");
        TskorGizi4.setFocusTraversalPolicyProvider(true);
        TskorGizi4.setName("TskorGizi4"); // NOI18N
        FormInput.add(TskorGizi4);
        TskorGizi4.setBounds(655, 2169, 40, 23);

        jLabel164.setForeground(new java.awt.Color(0, 0, 0));
        jLabel164.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel164.setText("Skor");
        jLabel164.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel164.setName("jLabel164"); // NOI18N
        FormInput.add(jLabel164);
        jLabel164.setBounds(645, 2009, 60, 23);

        ScrollTriase1.setViewportView(FormInput);

        FormAsesmen.add(ScrollTriase1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Asesmen", FormAsesmen);

        internalFrame4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbAsesmen.setAutoCreateRowSorter(true);
        tbAsesmen.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAsesmen.setComponentPopupMenu(jPopupMenu1);
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

        internalFrame4.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Asesmen :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-04-2024" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-04-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        internalFrame4.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Asesmen", internalFrame4);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

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

        BtnNotepad.setForeground(new java.awt.Color(0, 0, 0));
        BtnNotepad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnNotepad.setMnemonic('N');
        BtnNotepad.setText("Notepad");
        BtnNotepad.setToolTipText("Alt+N");
        BtnNotepad.setName("BtnNotepad"); // NOI18N
        BtnNotepad.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNotepad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNotepadActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnNotepad);

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
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            cekData();
            if (Sequel.menyimpantf("penilaian_awal_keperawatan_anak_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?", "No.Rawat", 158, new String[]{
                        TNoRw.getText(), kdkamar, Valid.SetTgl(TtglMsk.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        cmbTiba.getSelectedItem().toString(), TtibaLain.getText(), cmbMasuk.getSelectedItem().toString(), Tkeluhan.getText(), cmbRiwAlergi.getSelectedItem().toString(),
                        alergiObat, TnmAlergiObat.getText(), TreaksiObat.getText(), alergiMakanan, TnmAlergiMakanan.getText(), TreaksiMakanan.getText(), alergiLain, TnmAlergiLain.getText(), 
                        TreaksiLain.getText(), gelang, cmbAlergiDiberitahu.getSelectedItem().toString(), TriwPenyktSkg.getText(), campak, diare, difteri, tetanus, kejang, tbc, batukRejan,
                        sesak, kuning, demamTifoid, urtikaria, cacing, sakitTenggorokan, lainnya, Tlainnya.getText(), Tkesadaran.getText(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(), 
                        Ttensi.getText(), Ttemp.getText(), Thr.getText(), Trr.getText(), TbbBelum.getText(), TbbMasuk.getText().trim(), Ttb.getText().trim(), Timt.getText().trim(), Tcrt.getText(), 
                        Tspo.getText(), cmbPernafasan.getSelectedItem().toString(), cmbPenglihatan.getSelectedItem().toString(), cmbPendengaran.getSelectedItem().toString(), cmbMulut.getSelectedItem().toString(),
                        cmbReflek.getSelectedItem().toString(), cmbBicara.getSelectedItem().toString(), cmbDefekasi.getSelectedItem().toString(), cmbMiksi.getSelectedItem().toString(), 
                        cmbGastro.getSelectedItem().toString(), cmbPola.getSelectedItem().toString(), cmbMakan.getSelectedItem().toString(), cmbBerpakaian.getSelectedItem().toString(), 
                        cmbBuang.getSelectedItem().toString(), cmbMandi.getSelectedItem().toString(), cmbBerpindah.getSelectedItem().toString(), Tkesimpulan.getText(), TKlgDekat.getText(), 
                        Thubungan.getText(), cmbTinggal.getSelectedItem().toString(), TtglDenganLain.getText(), cmbCuriga.getSelectedItem().toString(), ibadah, cmbStatus.getSelectedItem().toString(), 
                        Thepa1.getText(), Thepa2.getText(), Thepa3.getText(), Thepa4.getText(), ThepaUlang.getText(), Tpolio1.getText(), Tpolio2.getText(), Tpolio3.getText(), Tpolio4.getText(),
                        TpolioUlang.getText(), TbcgDasar.getText(), TbcgUlang.getText(), Tdpt1.getText(), Tdpt2.getText(), Tdpt3.getText(), TdptUlang.getText(), Tmening1.getText(),
                        Tmening2.getText(), Tmening3.getText(), TmeningUlang.getText(), TcampakDasar.getText(), TcampakUlang.getText(), TtegakanKepala.getText(), Ttengkurap.getText(),
                        Tduduk.getText(), Tberjalan.getText(), Tgenggam.getText(), Tmencari.getText(), Tmencoret.getText(), Tmengenal24.getText(), Tberceloteh.getText(), Tmengucapkan.getText(),
                        Tmenyebutkan.getText(), Tmenyebut36.getText(), Ttersenyum.getText(), Tbermain.getText(), Tmengenal24.getText(), Tmakan.getText(), cmbSekolah.getSelectedItem().toString(),
                        Tkelas.getText(), Tonset.getText(), cmbProvo.getSelectedItem().toString(), Tprovo.getText(), cmbQuality.getSelectedItem().toString(), Tquality.getText(),
                        cmbRadia.getSelectedItem().toString(), cmbSever.getSelectedItem().toString(), cmbTime.getSelectedItem().toString(), cmbLama.getSelectedItem().toString(),
                        cmbRelief.getSelectedItem().toString(), Trelief.getText(), cmbAsso.getSelectedItem().toString(), Tasso.getText(), cmbSkala.getSelectedItem().toString(),
                        cekgizi1, cekgizi2, cekgizi3, cekgizi4, cmbTindakanCegah.getSelectedItem().toString(), obatObatan, perawatanLuka, TmanajemenLain.getText(), manajemenNyeri, 
                        diet, fisio, TrehabLain.getText(), hipertermi, nyeri, resiko, kelebihan, bersihkan, pola, gangguan, cemas, ketidakseimbangan, perubahan, penurunan, kerusakan, 
                        intoleransi, kurang, TmasalahLain.getText(), Valid.SetTgl(TtglAses.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                        nip, Sequel.cariIsi("select now()")
                    }) == true) {

                //simpan faktor resiko
                for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
                    if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.menyimpan2("penilaian_awal_keperawatan_anak_ranap_resiko", "?,?", 2, new String[]{
                            TNoRw.getText(), tbFaktorResiko.getValueAt(i, 1).toString()});
                    }
                }

                emptTeks();
                TCari.setText(TNoRw.getText());                
                tampil();
                TabRawat.setSelectedIndex(1);
                tampilFaktorResiko();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
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
                if (nip.equals(akses.getkode())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh perawat yang bernama " + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString() + " ..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (tbAsesmen.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
                    if (nip.equals(akses.getkode())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh perawat yang bernama " + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString() + " ..!!");
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
            param.put("ruangan", Truangan.getText() + ", Tanggal : " + TtglMsk.getSelectedItem().toString() + ", Pukul : " + cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " WITA");
            
            if (cmbTiba.getSelectedIndex() == 4) {
                param.put("tibaDiruang", cmbTiba.getSelectedItem().toString() + " (" + TtibaLain.getText() + ")");
            } else {
                param.put("tibaDiruang", cmbTiba.getSelectedItem().toString());
            }
            
            param.put("mskMelalui", cmbMasuk.getSelectedItem().toString());
            param.put("keluhan", Tkeluhan.getText());
            param.put("riwayatAlergi", cmbRiwAlergi.getSelectedItem().toString());
            
            if (alergiObat.equals("ya")) {
                param.put("cekObat", "V");
                param.put("alergiObat", TnmAlergiObat.getText() + ", Reaksi : " + TreaksiObat.getText());
            } else {
                param.put("cekObat", "");
                param.put("alergiObat", " - , Reaksi : -");
            }
            
            if (alergiMakanan.equals("ya")) {
                param.put("cekMakan", "V");
                param.put("alergiMakan", TnmAlergiMakanan.getText() + ", Reaksi : " + TreaksiMakanan.getText());
            } else {
                param.put("cekMakan", "");
                param.put("alergiMakan", " - , Reaksi : -");
            }
            
            if (alergiLain.equals("ya")) {
                param.put("cekLain", "V");
                param.put("alergiLain", TnmAlergiLain.getText() + ", Reaksi : " + TreaksiLain.getText());
            } else {
                param.put("cekLain", "");
                param.put("alergiLain", " - , Reaksi : -");
            }
            
            if (gelang.equals("ya")) {
                param.put("gelang", "V");
            } else {
                param.put("gelang", "");
            }
            
            param.put("alergiDiberitau", cmbAlergiDiberitahu.getSelectedItem().toString());
            param.put("riwayatPS", TriwPenyktSkg.getText());
            
            if (campak.equals("ya")) {
                param.put("campak", "V");
            } else {
                param.put("campak", "");
            }
            
            if (batukRejan.equals("ya")) {
                param.put("batukRejan", "V");
            } else {
                param.put("batukRejan", "");
            }
            
            if (sakitTenggorokan.equals("ya")) {
                param.put("sakitTenggorokan", "V");
            } else {
                param.put("sakitTenggorokan", "");
            }
            
            if (diare.equals("ya")) {
                param.put("diare", "V");
            } else {
                param.put("diare", "");
            }
            
            if (sesak.equals("ya")) {
                param.put("sesak", "V");
            } else {
                param.put("sesak", "");
            }
            
            if (difteri.equals("ya")) {
                param.put("difteri", "V");
            } else {
                param.put("difteri", "");
            }
            
            if (kuning.equals("ya")) {
                param.put("kuning", "V");
            } else {
                param.put("kuning", "");
            }
            
            if (tetanus.equals("ya")) {
                param.put("tetanus", "V");
            } else {
                param.put("tetanus", "");
            }
            
            if (demamTifoid.equals("ya")) {
                param.put("demamTifoid", "V");
            } else {
                param.put("demamTifoid", "");
            }
            
            if (kejang.equals("ya")) {
                param.put("kejang", "V");
            } else {
                param.put("kejang", "");
            }
            
            if (urtikaria.equals("ya")) {
                param.put("urtikaria", "V");
            } else {
                param.put("urtikaria", "");
            }
            
            if (tbc.equals("ya")) {
                param.put("tbc", "V");
            } else {
                param.put("tbc", "");
            }
            
            if (cacing.equals("ya")) {
                param.put("cacing", "V");
            } else {
                param.put("cacing", "");
            }
            
            if (lainnya.equals("ya")) {
                param.put("lainnya", "V");
                param.put("kalimat_lainnya", "Lainnya : " + Tlainnya.getText());
            } else {
                param.put("lainnya", "");
                param.put("kalimat_lainnya", "Lainnya");
            }
            
            param.put("kesadaran", Tkesadaran.getText());
            param.put("gcs", Tgcse.getText() + " M : " + Tgcsm.getText() + " V : " + Tgcsv.getText());
            param.put("tensi", Ttensi.getText() + " mmHg");
            param.put("temp", Ttemp.getText() + " °C");
            param.put("hr", Thr.getText() + " x/mnt");
            param.put("rr", Trr.getText() + " x/mnt");
            param.put("BBbelum", TbbBelum.getText() + " Kg");
            param.put("BBmasuk", TbbMasuk.getText() + " Kg");
            param.put("tb", Ttb.getText() + " Cm");
            param.put("imt", Timt.getText() + " Kg");
            param.put("crt", Tcrt.getText() + " detik");
            param.put("spo", Tspo.getText() + " %");
            
            param.put("pernafasan", cmbPernafasan.getSelectedItem().toString());
            param.put("penglihatan", cmbPenglihatan.getSelectedItem().toString());
            param.put("pendengaran", cmbPendengaran.getSelectedItem().toString());
            param.put("mulut", cmbMulut.getSelectedItem().toString());
            param.put("reflek", cmbReflek.getSelectedItem().toString());
            param.put("bicara", cmbBicara.getSelectedItem().toString());
            param.put("defekasi", cmbDefekasi.getSelectedItem().toString());
            param.put("miksi", cmbMiksi.getSelectedItem().toString());
            param.put("gastro", cmbGastro.getSelectedItem().toString());
            param.put("pola", cmbPola.getSelectedItem().toString());
            param.put("makan", cmbMakan.getSelectedItem().toString());
            param.put("berpakaian", cmbBerpakaian.getSelectedItem().toString());
            param.put("buang", cmbBuang.getSelectedItem().toString());
            param.put("mandi", cmbMandi.getSelectedItem().toString());
            param.put("berpindah", cmbBerpindah.getSelectedItem().toString());
            param.put("kesimpulan", Tkesimpulan.getText());
            
            param.put("keluarga", TKlgDekat.getText());
            param.put("hubungan", Thubungan.getText());
            param.put("notelp", TnoTelp.getText());
            
            if (cmbTinggal.getSelectedIndex() == 7) {
                param.put("tinggalDengan", cmbTinggal.getSelectedItem().toString() + " (" + TtglDenganLain.getText() + ")");
            } else {
                param.put("tinggalDengan", cmbTinggal.getSelectedItem().toString());
            }
            
            param.put("curiga", cmbCuriga.getSelectedItem().toString());
            
            if (ibadah.equals("ya")) {
                param.put("ibadah", "V");
            } else {
                param.put("ibadah", "");
            }
            
            param.put("emosi", cmbStatus.getSelectedItem().toString());
            param.put("umurHepa1", Thepa1.getText());
            param.put("umurHepa2", Thepa2.getText());
            param.put("umurHepa3", Thepa3.getText());
            param.put("umurHepa4", Thepa4.getText());
            param.put("umurHepaUlang", ThepaUlang.getText());
            
            param.put("umurPolio1", Tpolio1.getText());
            param.put("umurPolio2", Tpolio2.getText());
            param.put("umurPolio3", Tpolio3.getText());
            param.put("umurPolio4", Tpolio4.getText());
            param.put("umurPolioUlang", TpolioUlang.getText());
            
            param.put("umurBcg1", TbcgDasar.getText());
            param.put("umurBcg2", TbcgUlang.getText());
            
            param.put("umurDpt1", Tdpt1.getText());
            param.put("umurDpt2", Tdpt2.getText());
            param.put("umurDpt3", Tdpt3.getText());
            param.put("umurDptUlang", TdptUlang.getText());
            
            param.put("umurMening1", Tmening1.getText());
            param.put("umurMening2", Tmening2.getText());
            param.put("umurMening3", Tmening3.getText());
            param.put("umurMeningUlang", TmeningUlang.getText());
            
            param.put("umurCampak1", TcampakDasar.getText());
            param.put("umurCampak2", TcampakUlang.getText());
            
            param.put("umurTegakKepala", TtegakanKepala.getText());
            param.put("umurTengkurap", Ttengkurap.getText());
            param.put("umurDuduk", Tduduk.getText());
            param.put("umurBerjalan", Tberjalan.getText());
            
            param.put("umurGenggam", Tgenggam.getText());
            param.put("umurMencari", Tmencari.getText());
            param.put("umurMencoret", Tmencoret.getText());
            param.put("umurMengenal24", Tmengenal24.getText());
            
            param.put("umurCeloteh", Tberceloteh.getText());
            param.put("umurUcapkan", Tmengucapkan.getText());
            param.put("umurMenyebutkan", Tmenyebutkan.getText());
            param.put("umurMenyebut36", Tmenyebut36.getText());
            
            param.put("umurTersenyum", Ttersenyum.getText());
            param.put("umurBermain", Tbermain.getText());
            param.put("umurMengenalAnggota", TmengenalAnggota.getText());
            param.put("umurMakan", Tmakan.getText());
            
            param.put("sekolah", cmbSekolah.getSelectedItem().toString());
            param.put("kelas", Tkelas.getText());
            
            param.put("skala", cmbSkala.getSelectedItem().toString());
            param.put("onset", Tonset.getText());

            if (cmbProvo.getSelectedIndex() == 5) {
                param.put("provo", cmbProvo.getSelectedItem().toString() + " (" + Tprovo.getText() + ")");
            } else {
                param.put("provo", cmbProvo.getSelectedItem().toString());
            }
            
            if (cmbQuality.getSelectedIndex() == 9) {
                param.put("quality", cmbQuality.getSelectedItem().toString() + " (" + Tquality.getText() + ")");
            } else {
                param.put("quality", cmbQuality.getSelectedItem().toString());
            }
            
            param.put("radiation", cmbRadia.getSelectedItem().toString());
            param.put("sever", cmbSever.getSelectedItem().toString());
            param.put("time", cmbTime.getSelectedItem().toString() + ", Lama : " + cmbLama.getSelectedItem().toString());
            
            if (cmbRelief.getSelectedIndex() == 4) {
                param.put("relief", cmbRelief.getSelectedItem().toString() + " (" + Trelief.getText() + ")");
            } else {
                param.put("relief", cmbRelief.getSelectedItem().toString());
            }
            
            if (cmbAsso.getSelectedIndex() == 6) {
                param.put("asso", cmbAsso.getSelectedItem().toString() + " (" + Tasso.getText() + ")");
            } else {
                param.put("asso", cmbAsso.getSelectedItem().toString());
            }

            //faktor resiko jatuh
            try {
                ps3 = koneksi.prepareStatement("select m.kode_resiko, concat('Faktor Resiko : ',m.faktor_resiko,', Skala : ',m.skala,', Skor (',m.skor,')') resiko "
                        + "FROM master_faktor_resiko_igd m INNER JOIN penilaian_awal_keperawatan_anak_ranap_resiko pm ON pm.kode_resiko = m.kode_resiko "
                        + "WHERE m.asesmen = 'Anak Ranap' and pm.no_rawat=? ORDER BY pm.kode_resiko");
                try {
                    ps3.setString(1, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        resikojatuh = rs3.getString("resiko") + "\n" + resikojatuh;
                    }
                    
                    if (resikojatuh.endsWith("\n")) {
                        resikojatuh = resikojatuh.substring(0, resikojatuh.length() - 1);
                    }

                } catch (Exception e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs3 != null) {
                        rs3.close();
                    }
                    if (ps3 != null) {
                        ps3.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            }
            
            param.put("resikoJatuh", resikojatuh);
            param.put("TotSkorResikoJatuh", "Jumlah Skor Resiko Jatuh : " + TotSkorRJ.getText());
            param.put("KesResikoJatuh", "Kesimpulan Skor Resiko Jatuh : " + kesimpulanResikoJatuh.getText());

            if (cmbTindakanCegah.getSelectedIndex() == 0) {
                param.put("JudultindakanRJ", "Tindakan Pencegahan Resiko Jatuh");
                param.put("IsitindakanRJ", "-");
            } else if (cmbTindakanCegah.getSelectedIndex() == 1) {
                param.put("JudultindakanRJ", "Tindakan Pencegahan Resiko Jatuh\nPencegahan Umum (A)");
                param.put("IsitindakanRJ", cegahA.getText());
            } else if (cmbTindakanCegah.getSelectedIndex() == 2) {
                param.put("JudultindakanRJ", "Tindakan Pencegahan Resiko Jatuh\nPencegahan Resiko Tinggi (B)");
                param.put("IsitindakanRJ", cegahB.getText());
            }
            
            //skrining gizi
            param.put("kalimatSkrining", ChkGizi1.getText() + " (Skor : " + TskorGizi1.getText() + ")\n\n"
                    + ChkGizi2.getText() + " (Skor : " + TskorGizi2.getText() + ")\n" + labelChkGizi2.getText() + "\n\n"
                    + ChkGizi3.getText() + " (Skor : " + TskorGizi3.getText() + ")\n" + labelChkGizi3A.getText() + "\n" + labelChkGizi3B.getText() + "\n\n"
                    + ChkGizi4.getText() + " (Skor : " + TskorGizi4.getText() + ")\n"
                    + labelChkGizi41.getText() + "\n"
                    + labelChkGizi42.getText() + "\n"
                    + labelChkGizi43.getText() + "\n"
                    + labelChkGizi44.getText() + "\n"
                    + labelChkGizi45.getText() + "\n"
                    + labelChkGizi46.getText() + "\n"
                    + labelChkGizi47.getText() + "\n"
                    + labelChkGizi48.getText() + "\n"
                    + labelChkGizi49.getText() + "\n"
                    + labelChkGizi410.getText() + "\n"
                    + labelChkGizi411.getText() + "\n"
                    + labelChkGizi412.getText() + "\n"
                    + labelChkGizi413.getText() + "\n"
                    + labelChkGizi414.getText() + "\n\n"
                    + "TOTAL SKOR : " + TotSkorGizi.getText() + "\nKesimpulan Skrining Gizi : " + kesimpulanGizi.getText() + "\n");
            
            if (obatObatan.equals("ya")) {
                param.put("obat", "V");
            } else {
                param.put("obat", "");
            }
            
            if (perawatanLuka.equals("ya")) {
                param.put("rawatluka", "V");
            } else {
                param.put("rawatluka", "");
            }
            
            param.put("manajemenLain", TmanajemenLain.getText());
            
            if (manajemenNyeri.equals("ya")) {
                param.put("manajNyeri", "V");
            } else {
                param.put("manajNyeri", "");
            }
            
            if (diet.equals("ya")) {
                param.put("diet", "V");
            } else {
                param.put("diet", "");
            }
            
            if (fisio.equals("ya")) {
                param.put("fisio", "V");
            } else {
                param.put("fisio", "");
            }
            
            param.put("rehabLain", TrehabLain.getText());
            
            if (hipertermi.equals("ya")) {
                param.put("hipertermi", "V");
            } else {
                param.put("hipertermi", "");
            }
            
            if (nyeri.equals("ya")) {
                param.put("nyeri", "V");
            } else {
                param.put("nyeri", "");
            }
            
            if (resiko.equals("ya")) {
                param.put("resiko", "V");
            } else {
                param.put("resiko", "");
            }
            
            if (kelebihan.equals("ya")) {
                param.put("kelebihan", "V");
            } else {
                param.put("kelebihan", "");
            }
            
            if (bersihkan.equals("ya")) {
                param.put("bersihkan", "V");
            } else {
                param.put("bersihkan", "");
            }
            
            if (pola.equals("ya")) {
                param.put("polaNafas", "V");
            } else {
                param.put("polaNafas", "");
            }
            
            if (gangguan.equals("ya")) {
                param.put("gangguan", "V");
            } else {
                param.put("gangguan", "");
            }
            
            if (cemas.equals("ya")) {
                param.put("cemas", "V");
            } else {
                param.put("cemas", "");
            }
            
            if (ketidakseimbangan.equals("ya")) {
                param.put("ketidakseimbangan", "V");
            } else {
                param.put("ketidakseimbangan", "");
            }
            
            if (perubahan.equals("ya")) {
                param.put("perubahan", "V");
            } else {
                param.put("perubahan", "");
            }
            
            if (penurunan.equals("ya")) {
                param.put("penurunan", "V");
            } else {
                param.put("penurunan", "");
            }
            
            if (kerusakan.equals("ya")) {
                param.put("kerusakan", "V");
            } else {
                param.put("kerusakan", "");
            }
            
            if (intoleransi.equals("ya")) {
                param.put("intol", "V");
            } else {
                param.put("intol", "");
            }
            
            if (kurang.equals("ya")) {
                param.put("kurang", "V");
            } else {
                param.put("kurang", "");
            }
            
            param.put("masalahLain", TmasalahLain.getText());
            param.put("tglAsesmen", TtglAses.getSelectedItem().toString());
            param.put("jamAsesmen", cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " WITA");
            param.put("perawat", "(" + TnmPerawat.getText() + ")");
            
            Valid.MyReport("rptAsesmenKeperawatanAnak1.jasper", "report", "::[ Asesmen Keperawatan Anak Rawat Inap Hal. 1 ]::",
                    "SELECT now() tanggal", param);
//            Valid.MyReport("rptAsesmenKeperawatanAnak2.jasper", "report", "::[ Asesmen Keperawatan Anak Rawat Inap Hal. 2 ]::",
//                    "SELECT now() tanggal", param);
            
            TabRawat.setSelectedIndex(1);
            tampilFaktorResiko();
            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih dulu datanya pada tabel terlebih dahulu..!!!!");
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampilFaktorResiko();
        tampil();
        
        if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_anak_ranap where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_anak_ranap where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

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

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void cmbTibaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTibaActionPerformed
        TtibaLain.setText("");
        if (cmbTiba.getSelectedIndex() == 4) {
            TtibaLain.setEnabled(true);
            TtibaLain.requestFocus();
        } else {
            TtibaLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTibaActionPerformed

    private void ChkAlergiObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAlergiObatActionPerformed
        TreaksiObat.setText("");
        TnmAlergiObat.setText("");
        
        if (ChkAlergiObat.isSelected() == true) {
            TreaksiObat.setEnabled(true);
            TnmAlergiObat.setEnabled(true);
            TnmAlergiObat.requestFocus();
        } else {
            TreaksiObat.setEnabled(false);
            TnmAlergiObat.setEnabled(false);
        }
    }//GEN-LAST:event_ChkAlergiObatActionPerformed

    private void ChkAlergiMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAlergiMakananActionPerformed
        TreaksiMakanan.setText("");
        TnmAlergiMakanan.setText("");
        
        if (ChkAlergiMakanan.isSelected() == true) {
            TreaksiMakanan.setEnabled(true);
            TnmAlergiMakanan.setEnabled(true);
            TnmAlergiMakanan.requestFocus();
        } else {
            TreaksiMakanan.setEnabled(false);
            TnmAlergiMakanan.setEnabled(false);
        }
    }//GEN-LAST:event_ChkAlergiMakananActionPerformed

    private void ChkAlergiLainyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAlergiLainyaActionPerformed
        TreaksiLain.setText("");
        TnmAlergiLain.setText("");
        
        if (ChkAlergiLainya.isSelected() == true) {
            TreaksiLain.setEnabled(true);
            TnmAlergiLain.setEnabled(true);
            TnmAlergiLain.requestFocus();
        } else {
            TreaksiLain.setEnabled(false);
            TnmAlergiLain.setEnabled(false);
        }
    }//GEN-LAST:event_ChkAlergiLainyaActionPerformed

    private void TriwPenyktSkgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwPenyktSkgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            ChkCampak.requestFocus();
        }
    }//GEN-LAST:event_TriwPenyktSkgKeyPressed

    private void TtibaLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtibaLainKeyPressed
        Valid.pindah(evt, cmbTiba, cmbMasuk);
    }//GEN-LAST:event_TtibaLainKeyPressed

    private void TkeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeluhanKeyPressed
        Valid.pindah(evt, cmbMasuk, cmbRiwAlergi);
    }//GEN-LAST:event_TkeluhanKeyPressed

    private void TkesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesadaranKeyPressed
        Valid.pindah(evt, TriwPenyktSkg, Tgcse);
    }//GEN-LAST:event_TkesadaranKeyPressed

    private void TgcseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcseKeyPressed
        Valid.pindah(evt, Tkeluhan, Tgcsm);
    }//GEN-LAST:event_TgcseKeyPressed

    private void TgcsmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsmKeyPressed
        Valid.pindah(evt, Tgcse, Tgcsv);
    }//GEN-LAST:event_TgcsmKeyPressed

    private void TgcsvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsvKeyPressed
        Valid.pindah(evt, Tgcsm, Ttensi);
    }//GEN-LAST:event_TgcsvKeyPressed

    private void TtensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtensiKeyPressed
        Valid.pindah(evt, Tgcsv, Ttemp);
    }//GEN-LAST:event_TtensiKeyPressed

    private void TtempKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtempKeyPressed
        Valid.pindah(evt, Ttensi, Thr);
    }//GEN-LAST:event_TtempKeyPressed

    private void ThrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThrKeyPressed
        Valid.pindah(evt, Ttemp, Trr);
    }//GEN-LAST:event_ThrKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        Valid.pindah(evt, Thr, TbbBelum);
    }//GEN-LAST:event_TrrKeyPressed

    private void TbbBelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbBelumKeyPressed
        Valid.pindah(evt, Trr, TbbMasuk);
    }//GEN-LAST:event_TbbBelumKeyPressed

    private void TbbMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbMasukKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (TbbMasuk.getText().contains(",") == true) {
                TbbMasuk.setText(TbbMasuk.getText().replaceAll(",", "."));
            }
            Ttb.requestFocus();
        }
    }//GEN-LAST:event_TbbMasukKeyPressed

    private void TtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (TbbMasuk.getText().contains(",") == true) {
                TbbMasuk.setText(TbbMasuk.getText().replaceAll(",", "."));
            }
            
            if (Ttb.getText().contains(",") == true) {
                Ttb.setText(Ttb.getText().replaceAll(",", "."));
            }
            BtnIMTActionPerformed(null);
        }
    }//GEN-LAST:event_TtbKeyPressed

    private void TimtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimtKeyPressed
        Valid.pindah(evt, Ttb, Tcrt);
    }//GEN-LAST:event_TimtKeyPressed

    private void TcrtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcrtKeyPressed
        Valid.pindah(evt, Timt, Tspo);
    }//GEN-LAST:event_TcrtKeyPressed

    private void TspoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TspoKeyPressed
        Valid.pindah(evt, Tcrt, cmbPernafasan);
    }//GEN-LAST:event_TspoKeyPressed

    private void TkesimpulanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesimpulanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TKlgDekat.requestFocus();
        }
    }//GEN-LAST:event_TkesimpulanKeyPressed

    private void TKlgDekatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKlgDekatKeyPressed
        Valid.pindah(evt, Tkesimpulan, Thubungan);
    }//GEN-LAST:event_TKlgDekatKeyPressed

    private void ThubunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThubunganKeyPressed
        Valid.pindah(evt, Tkesimpulan, cmbTinggal);
    }//GEN-LAST:event_ThubunganKeyPressed

    private void TtglDenganLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtglDenganLainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TtglDenganLainKeyPressed

    private void cmbTinggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTinggalActionPerformed
        TtglDenganLain.setText("");
        if (cmbTinggal.getSelectedIndex() == 7) {
            TtglDenganLain.setEnabled(true);
            TtglDenganLain.requestFocus();
        } else {
            TtglDenganLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTinggalActionPerformed

    private void TonsetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TonsetKeyPressed
        Valid.pindah(evt, Tonset, cmbProvo);
    }//GEN-LAST:event_TonsetKeyPressed

    private void cmbProvoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvoActionPerformed
        Tprovo.setText("");
        if (cmbProvo.getSelectedIndex() == 5) {
            Tprovo.setEnabled(true);
            Tprovo.requestFocus();
        } else {
            Tprovo.setEnabled(false);
        }
    }//GEN-LAST:event_cmbProvoActionPerformed

    private void TprovoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprovoKeyPressed
        Valid.pindah(evt, cmbProvo, cmbQuality);
    }//GEN-LAST:event_TprovoKeyPressed

    private void cmbQualityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbQualityActionPerformed
        Tquality.setText("");
        if (cmbQuality.getSelectedIndex() == 9) {
            Tquality.setEnabled(true);
            Tquality.requestFocus();
        } else {
            Tquality.setEnabled(false);
        }
    }//GEN-LAST:event_cmbQualityActionPerformed

    private void TqualityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TqualityKeyPressed
        Valid.pindah(evt, cmbQuality, cmbRadia);
    }//GEN-LAST:event_TqualityKeyPressed

    private void TreliefKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreliefKeyPressed
        Valid.pindah(evt, cmbRelief, cmbAsso);
    }//GEN-LAST:event_TreliefKeyPressed

    private void cmbReliefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbReliefActionPerformed
        Trelief.setText("");
        if (cmbRelief.getSelectedIndex() == 4) {
            Trelief.setEnabled(true);
            Trelief.requestFocus();
        } else {
            Trelief.setEnabled(false);
        }
    }//GEN-LAST:event_cmbReliefActionPerformed

    private void cmbAssoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAssoActionPerformed
        Tasso.setText("");
        if (cmbAsso.getSelectedIndex() == 6) {
            Tasso.setEnabled(true);
            Tasso.requestFocus();
        } else {
            Tasso.setEnabled(false);
        }
    }//GEN-LAST:event_cmbAssoActionPerformed

    private void TassoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TassoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TassoKeyPressed

    private void tbFaktorResikoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaktorResikoMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                hitungSkorRJ();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaktorResikoMouseClicked

    private void tbFaktorResikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaktorResikoKeyPressed
        if(tabMode1.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCariResiko.setText("");
                TCariResiko.requestFocus();
            }
        }
    }//GEN-LAST:event_tbFaktorResikoKeyPressed

    private void tbFaktorResikoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaktorResikoKeyReleased
        if(tabMode1.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    hitungSkorRJ();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbFaktorResikoKeyReleased

    private void TCariResikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariResikoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilFaktorResiko();
        }
    }//GEN-LAST:event_TCariResikoKeyPressed

    private void BtnCariResikoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariResikoActionPerformed
        tampilFaktorResiko();
        hitungSkorRJ();
    }//GEN-LAST:event_BtnCariResikoActionPerformed

    private void BtnCariResikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariResikoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilFaktorResiko();
        }
    }//GEN-LAST:event_BtnCariResikoKeyPressed

    private void BtnAllResikoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllResikoActionPerformed
        TCariResiko.setText("");
        tampilFaktorResiko();
        hitungSkorRJ();
    }//GEN-LAST:event_BtnAllResikoActionPerformed

    private void BtnAllResikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllResikoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllResikoActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCariResiko, TCariResiko);
        }
    }//GEN-LAST:event_BtnAllResikoKeyPressed

    private void cmbTindakanCegahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTindakanCegahActionPerformed
        if (cmbTindakanCegah.getSelectedIndex() == 1) {
            TabPencegahanAnak.setSelectedIndex(0);
        } else if (cmbTindakanCegah.getSelectedIndex() == 2) {
            TabPencegahanAnak.setSelectedIndex(1);
        } else {
            TabPencegahanAnak.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cmbTindakanCegahActionPerformed

    private void TmasalahLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmasalahLainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TmasalahLainKeyPressed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void BtnPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatActionPerformed
        akses.setform("RMAsesmenKeperawatanDewasaRanap");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatActionPerformed

    private void BtnIMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIMTActionPerformed
        hitungIMT();
    }//GEN-LAST:event_BtnIMTActionPerformed

    private void BtnIMTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnIMTKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnIMTActionPerformed(null);
        } 
    }//GEN-LAST:event_BtnIMTKeyPressed

    private void TnmAlergiObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmAlergiObatKeyPressed
        Valid.pindah(evt, TnmAlergiObat, TreaksiObat);
    }//GEN-LAST:event_TnmAlergiObatKeyPressed

    private void TnmAlergiMakananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmAlergiMakananKeyPressed
        Valid.pindah(evt, TnmAlergiMakanan, TreaksiMakanan);
    }//GEN-LAST:event_TnmAlergiMakananKeyPressed

    private void TnmAlergiLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmAlergiLainKeyPressed
        Valid.pindah(evt, TnmAlergiLain, TreaksiLain);
    }//GEN-LAST:event_TnmAlergiLainKeyPressed

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMAsesmenKeperawatanDewasaRanap");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMAsesmenKeperawatanDewasaRanap");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void ChkLainyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkLainyaActionPerformed
        Tlainnya.setText("");
        if (ChkLainya.isSelected() == true) {
            Tlainnya.setEnabled(true);
            Tlainnya.requestFocus();
        } else {
            Tlainnya.setEnabled(false);
        }
    }//GEN-LAST:event_ChkLainyaActionPerformed

    private void Thepa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Thepa1KeyPressed
        Valid.pindah(evt, Thepa1, Thepa2);
    }//GEN-LAST:event_Thepa1KeyPressed

    private void Thepa2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Thepa2KeyPressed
        Valid.pindah(evt, Thepa1, Thepa3);
    }//GEN-LAST:event_Thepa2KeyPressed

    private void Thepa3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Thepa3KeyPressed
        Valid.pindah(evt, Thepa2, Thepa4);
    }//GEN-LAST:event_Thepa3KeyPressed

    private void Thepa4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Thepa4KeyPressed
        Valid.pindah(evt, Thepa3, ThepaUlang);
    }//GEN-LAST:event_Thepa4KeyPressed

    private void ThepaUlangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThepaUlangKeyPressed
        Valid.pindah(evt, Thepa4, Tpolio1);
    }//GEN-LAST:event_ThepaUlangKeyPressed

    private void Tpolio1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tpolio1KeyPressed
        Valid.pindah(evt, ThepaUlang, Tpolio2);
    }//GEN-LAST:event_Tpolio1KeyPressed

    private void Tpolio2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tpolio2KeyPressed
        Valid.pindah(evt, Tpolio1, Tpolio3);
    }//GEN-LAST:event_Tpolio2KeyPressed

    private void Tpolio3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tpolio3KeyPressed
        Valid.pindah(evt, Tpolio2, Tpolio4);
    }//GEN-LAST:event_Tpolio3KeyPressed

    private void Tpolio4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tpolio4KeyPressed
        Valid.pindah(evt, Tpolio3, TpolioUlang);
    }//GEN-LAST:event_Tpolio4KeyPressed

    private void TpolioUlangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpolioUlangKeyPressed
        Valid.pindah(evt, Tpolio4, TbcgDasar);
    }//GEN-LAST:event_TpolioUlangKeyPressed

    private void TbcgDasarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbcgDasarKeyPressed
        Valid.pindah(evt, TpolioUlang, TbcgUlang);
    }//GEN-LAST:event_TbcgDasarKeyPressed

    private void TbcgUlangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbcgUlangKeyPressed
        Valid.pindah(evt, TbcgDasar, Tdpt1);
    }//GEN-LAST:event_TbcgUlangKeyPressed

    private void Tdpt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdpt1KeyPressed
        Valid.pindah(evt, TbcgUlang, Tdpt2);
    }//GEN-LAST:event_Tdpt1KeyPressed

    private void Tdpt2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdpt2KeyPressed
        Valid.pindah(evt, Tdpt1, Tdpt3);
    }//GEN-LAST:event_Tdpt2KeyPressed

    private void Tdpt3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdpt3KeyPressed
        Valid.pindah(evt, Tdpt2, TdptUlang);
    }//GEN-LAST:event_Tdpt3KeyPressed

    private void TdptUlangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdptUlangKeyPressed
        Valid.pindah(evt, Tdpt3, Tmening1);
    }//GEN-LAST:event_TdptUlangKeyPressed

    private void Tmening1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tmening1KeyPressed
        Valid.pindah(evt, TdptUlang, Tmening2);
    }//GEN-LAST:event_Tmening1KeyPressed

    private void Tmening2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tmening2KeyPressed
        Valid.pindah(evt, Tmening1, Tmening3);
    }//GEN-LAST:event_Tmening2KeyPressed

    private void Tmening3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tmening3KeyPressed
        Valid.pindah(evt, Tmening2, TmeningUlang);
    }//GEN-LAST:event_Tmening3KeyPressed

    private void TmeningUlangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmeningUlangKeyPressed
        Valid.pindah(evt, Tmening3, TcampakDasar);
    }//GEN-LAST:event_TmeningUlangKeyPressed

    private void TcampakDasarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcampakDasarKeyPressed
        Valid.pindah(evt, TmeningUlang, TcampakUlang);
    }//GEN-LAST:event_TcampakDasarKeyPressed

    private void TcampakUlangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcampakUlangKeyPressed
        Valid.pindah(evt, TcampakDasar, TtegakanKepala);
    }//GEN-LAST:event_TcampakUlangKeyPressed

    private void TtegakanKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtegakanKepalaKeyPressed
        Valid.pindah(evt, TtegakanKepala, Ttengkurap);
    }//GEN-LAST:event_TtegakanKepalaKeyPressed

    private void TtengkurapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtengkurapKeyPressed
        Valid.pindah(evt, TtegakanKepala, Tduduk);
    }//GEN-LAST:event_TtengkurapKeyPressed

    private void TdudukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdudukKeyPressed
        Valid.pindah(evt, Ttengkurap, Tberjalan);
    }//GEN-LAST:event_TdudukKeyPressed

    private void TberjalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TberjalanKeyPressed
        Valid.pindah(evt, Tduduk, Tgenggam);
    }//GEN-LAST:event_TberjalanKeyPressed

    private void TgenggamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgenggamKeyPressed
        Valid.pindah(evt, Tberjalan, Tmencari);
    }//GEN-LAST:event_TgenggamKeyPressed

    private void TmencariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmencariKeyPressed
        Valid.pindah(evt, Tgenggam, Tmencoret);
    }//GEN-LAST:event_TmencariKeyPressed

    private void TmencoretKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmencoretKeyPressed
        Valid.pindah(evt, Tmencari, Tmengenal24);
    }//GEN-LAST:event_TmencoretKeyPressed

    private void Tmengenal24KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tmengenal24KeyPressed
        Valid.pindah(evt, Tmencoret, Tberceloteh);
    }//GEN-LAST:event_Tmengenal24KeyPressed

    private void TbercelotehKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbercelotehKeyPressed
        Valid.pindah(evt, Tmengenal24, Tmengucapkan);
    }//GEN-LAST:event_TbercelotehKeyPressed

    private void TmengucapkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmengucapkanKeyPressed
        Valid.pindah(evt, Tberceloteh, Tmenyebutkan);
    }//GEN-LAST:event_TmengucapkanKeyPressed

    private void TmenyebutkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmenyebutkanKeyPressed
        Valid.pindah(evt, Tmengucapkan, Tmenyebut36);
    }//GEN-LAST:event_TmenyebutkanKeyPressed

    private void Tmenyebut36KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tmenyebut36KeyPressed
        Valid.pindah(evt, Tmenyebutkan, Ttersenyum);
    }//GEN-LAST:event_Tmenyebut36KeyPressed

    private void TtersenyumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtersenyumKeyPressed
        Valid.pindah(evt, Tmenyebut36, Tbermain);
    }//GEN-LAST:event_TtersenyumKeyPressed

    private void TbermainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbermainKeyPressed
        Valid.pindah(evt, Ttersenyum, TmengenalAnggota);
    }//GEN-LAST:event_TbermainKeyPressed

    private void TmengenalAnggotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmengenalAnggotaKeyPressed
        Valid.pindah(evt, Tbermain, Tmakan);
    }//GEN-LAST:event_TmengenalAnggotaKeyPressed

    private void TmakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmakanKeyPressed
        Valid.pindah(evt, TmengenalAnggota, cmbSekolah);
    }//GEN-LAST:event_TmakanKeyPressed

    private void TkelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkelasKeyPressed
        Valid.pindah(evt, cmbSekolah, cmbSkala);
    }//GEN-LAST:event_TkelasKeyPressed

    private void ChkGizi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkGizi1ActionPerformed
        if (ChkGizi1.isSelected() == true) {
            TskorGizi1.setText("1");
        } else {
            TskorGizi1.setText("0");
        }
        hitungSkorGizi();
    }//GEN-LAST:event_ChkGizi1ActionPerformed

    private void ChkGizi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkGizi2ActionPerformed
        if (ChkGizi2.isSelected() == true) {
            TskorGizi2.setText("1");
        } else {
            TskorGizi2.setText("0");
        }
        hitungSkorGizi();
    }//GEN-LAST:event_ChkGizi2ActionPerformed

    private void ChkGizi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkGizi3ActionPerformed
        if (ChkGizi3.isSelected() == true) {
            TskorGizi3.setText("1");
        } else {
            TskorGizi3.setText("0");
        }
        hitungSkorGizi();
    }//GEN-LAST:event_ChkGizi3ActionPerformed

    private void ChkGizi4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkGizi4ActionPerformed
        if (ChkGizi4.isSelected() == true) {
            TskorGizi4.setText("2");
        } else {
            TskorGizi4.setText("0");
        }
        hitungSkorGizi();
    }//GEN-LAST:event_ChkGizi4ActionPerformed

    private void TreaksiObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreaksiObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ChkAlergiMakanan.requestFocus();
        }
    }//GEN-LAST:event_TreaksiObatKeyPressed

    private void TreaksiMakananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreaksiMakananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ChkAlergiLainya.requestFocus();
        }
    }//GEN-LAST:event_TreaksiMakananKeyPressed

    private void TlainnyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainnyaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkesadaran.requestFocus();
        }
    }//GEN-LAST:event_TlainnyaKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenKeperawatanAnakRanap dialog = new RMAsesmenKeperawatanAnakRanap(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAllResiko;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCariResiko;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnIMT;
    private widget.Button BtnKeluar;
    private widget.Button BtnNotepad;
    private widget.Button BtnPerawat;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAlergiLainya;
    public widget.CekBox ChkAlergiMakanan;
    public widget.CekBox ChkAlergiObat;
    public widget.CekBox ChkBatuk;
    public widget.CekBox ChkBersihkan;
    public widget.CekBox ChkCacing;
    public widget.CekBox ChkCampak;
    public widget.CekBox ChkCemas;
    public widget.CekBox ChkDemam;
    public widget.CekBox ChkDiare;
    public widget.CekBox ChkDiet;
    public widget.CekBox ChkDifteri;
    public widget.CekBox ChkFisioterapi;
    public widget.CekBox ChkGangguan;
    public widget.CekBox ChkGelang;
    public widget.CekBox ChkGizi1;
    public widget.CekBox ChkGizi2;
    public widget.CekBox ChkGizi3;
    public widget.CekBox ChkGizi4;
    public widget.CekBox ChkHipertermi;
    public widget.CekBox ChkIbadah;
    public widget.CekBox ChkIntoleransi;
    public widget.CekBox ChkKejang;
    public widget.CekBox ChkKelebihan;
    public widget.CekBox ChkKerusakan;
    public widget.CekBox ChkKetidakseimbangan;
    public widget.CekBox ChkKuning;
    public widget.CekBox ChkKurang;
    public widget.CekBox ChkLainya;
    public widget.CekBox ChkManajemen;
    public widget.CekBox ChkNyeri;
    public widget.CekBox ChkObat;
    public widget.CekBox ChkPenurunan;
    public widget.CekBox ChkPerawatan;
    public widget.CekBox ChkPerubahan;
    public widget.CekBox ChkPola;
    public widget.CekBox ChkResiko;
    public widget.CekBox ChkSakit;
    public widget.CekBox ChkSesak;
    public widget.CekBox ChkTbc;
    public widget.CekBox ChkTetanus;
    public widget.CekBox ChkUrtikaria;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.InternalFrame FormAsesmen;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane ScrollTriase1;
    private widget.TextBox TCari;
    private widget.TextBox TCariResiko;
    private widget.TextBox TKlgDekat;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabPencegahanAnak;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tasso;
    private widget.TextBox TbbBelum;
    private widget.TextBox TbbMasuk;
    private widget.TextBox TbcgDasar;
    private widget.TextBox TbcgUlang;
    private widget.TextBox Tberceloteh;
    private widget.TextBox Tberjalan;
    private widget.TextBox Tbermain;
    private widget.TextBox TcampakDasar;
    private widget.TextBox TcampakUlang;
    private widget.TextBox Tcrt;
    private widget.TextBox Tdpt1;
    private widget.TextBox Tdpt2;
    private widget.TextBox Tdpt3;
    private widget.TextBox TdptUlang;
    private widget.TextBox Tduduk;
    private widget.TextBox Tgcse;
    private widget.TextBox Tgcsm;
    private widget.TextBox Tgcsv;
    private widget.TextBox Tgenggam;
    private widget.TextBox Thepa1;
    private widget.TextBox Thepa2;
    private widget.TextBox Thepa3;
    private widget.TextBox Thepa4;
    private widget.TextBox ThepaUlang;
    private widget.TextBox Thr;
    private widget.TextBox Thubungan;
    private widget.TextBox Timt;
    private widget.TextBox Tkelas;
    private widget.TextBox Tkeluhan;
    private widget.TextBox Tkesadaran;
    private widget.TextArea Tkesimpulan;
    private widget.TextBox Tlainnya;
    private widget.TextBox Tmakan;
    private widget.TextBox TmanajemenLain;
    private widget.TextArea TmasalahLain;
    private widget.TextBox Tmencari;
    private widget.TextBox Tmencoret;
    private widget.TextBox Tmengenal24;
    private widget.TextBox TmengenalAnggota;
    private widget.TextBox Tmengucapkan;
    private widget.TextBox Tmening1;
    private widget.TextBox Tmening2;
    private widget.TextBox Tmening3;
    private widget.TextBox TmeningUlang;
    private widget.TextBox Tmenyebut36;
    private widget.TextBox Tmenyebutkan;
    private widget.TextBox TnmAlergiLain;
    private widget.TextBox TnmAlergiMakanan;
    private widget.TextBox TnmAlergiObat;
    private widget.TextBox TnmPerawat;
    private widget.TextBox TnoTelp;
    private widget.TextBox Tonset;
    private widget.TextBox TotSkorGizi;
    private widget.TextBox TotSkorRJ;
    private widget.TextBox Tpolio1;
    private widget.TextBox Tpolio2;
    private widget.TextBox Tpolio3;
    private widget.TextBox Tpolio4;
    private widget.TextBox TpolioUlang;
    private widget.TextBox Tprovo;
    private widget.TextBox Tquality;
    private widget.TextBox TreaksiLain;
    private widget.TextBox TreaksiMakanan;
    private widget.TextBox TreaksiObat;
    private widget.TextBox TrehabLain;
    private widget.TextBox Trelief;
    private widget.TextArea TriwPenyktSkg;
    private widget.TextBox Trr;
    private widget.TextBox Truangan;
    private widget.TextBox TskorGizi1;
    private widget.TextBox TskorGizi2;
    private widget.TextBox TskorGizi3;
    private widget.TextBox TskorGizi4;
    private widget.TextBox Tspo;
    private widget.TextBox Ttb;
    private widget.TextBox TtegakanKepala;
    private widget.TextBox Ttemp;
    private widget.TextBox Ttengkurap;
    private widget.TextBox Ttensi;
    private widget.TextBox Ttersenyum;
    private widget.Tanggal TtglAses;
    private widget.TextBox TtglDenganLain;
    private widget.Tanggal TtglMsk;
    private widget.TextBox TtibaLain;
    private widget.TextArea cegahA;
    private widget.TextArea cegahB;
    private widget.ComboBox cmbAlergiDiberitahu;
    private widget.ComboBox cmbAsso;
    private widget.ComboBox cmbBerpakaian;
    private widget.ComboBox cmbBerpindah;
    private widget.ComboBox cmbBicara;
    private widget.ComboBox cmbBuang;
    private widget.ComboBox cmbCuriga;
    private widget.ComboBox cmbDefekasi;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbGastro;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbLama;
    private widget.ComboBox cmbMakan;
    private widget.ComboBox cmbMandi;
    private widget.ComboBox cmbMasuk;
    private widget.ComboBox cmbMiksi;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMulut;
    private widget.ComboBox cmbPendengaran;
    private widget.ComboBox cmbPenglihatan;
    private widget.ComboBox cmbPernafasan;
    private widget.ComboBox cmbPola;
    private widget.ComboBox cmbProvo;
    private widget.ComboBox cmbQuality;
    private widget.ComboBox cmbRadia;
    private widget.ComboBox cmbReflek;
    private widget.ComboBox cmbRelief;
    private widget.ComboBox cmbRiwAlergi;
    private widget.ComboBox cmbSekolah;
    private widget.ComboBox cmbSever;
    private widget.ComboBox cmbSkala;
    private widget.ComboBox cmbStatus;
    private widget.ComboBox cmbTiba;
    private widget.ComboBox cmbTime;
    private widget.ComboBox cmbTindakanCegah;
    private widget.ComboBox cmbTinggal;
    private widget.InternalFrame internalFrame1;
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
    private widget.Label jLabel11;
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
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel136;
    private widget.Label jLabel137;
    private widget.Label jLabel138;
    private widget.Label jLabel139;
    private widget.Label jLabel14;
    private widget.Label jLabel140;
    private widget.Label jLabel141;
    private widget.Label jLabel142;
    private widget.Label jLabel143;
    private widget.Label jLabel144;
    private widget.Label jLabel145;
    private widget.Label jLabel146;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel164;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
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
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel9;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextArea kesimpulanGizi;
    private widget.TextArea kesimpulanResikoJatuh;
    private widget.Label label12;
    private widget.Label labelChkGizi2;
    private widget.Label labelChkGizi3A;
    private widget.Label labelChkGizi3B;
    private widget.Label labelChkGizi41;
    private widget.Label labelChkGizi410;
    private widget.Label labelChkGizi411;
    private widget.Label labelChkGizi412;
    private widget.Label labelChkGizi413;
    private widget.Label labelChkGizi414;
    private widget.Label labelChkGizi42;
    private widget.Label labelChkGizi43;
    private widget.Label labelChkGizi44;
    private widget.Label labelChkGizi45;
    private widget.Label labelChkGizi46;
    private widget.Label labelChkGizi47;
    private widget.Label labelChkGizi48;
    private widget.Label labelChkGizi49;
    private widget.PanelBiasa panelBiasa10;
    private widget.PanelBiasa panelBiasa8;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbAsesmen;
    private widget.Table tbFaktorResiko;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT pa.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                    + "date_format(pa.tgl_msk_ruangan,'%d-%m-%Y') tglmsk, time_format(pa.jam_msk_ruangan,'%H:%i') jammsk, concat(pa.tiba_diruang,' ',pa.tiba_diruang_lainnya) tiba, "
                    + "pa.msk_melalui, pa.keluhan_utama, date_format(pa.tgl_asesmen,'%d-%m-%Y') tglases, time_format(pa.jam_asesmen,'%H:%i') jamases, pg.nama perawat "
                    + "FROM penilaian_awal_keperawatan_anak_ranap pa INNER JOIN reg_periksa rp ON rp.no_rawat = pa.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN pegawai pg ON pg.nik = pa.nip_perawat where "
                    + "pa.tgl_asesmen between ? and ? and pa.no_rawat like ? or "
                    + "pa.tgl_asesmen between ? and ? and p.no_rkm_medis like ? or "
                    + "pa.tgl_asesmen between ? and ? and p.nm_pasien like ? or "
                    + "pa.tgl_asesmen between ? and ? and pa.keluhan_utama like ? or "
                    + "pa.tgl_asesmen between ? and ? and pg.nama like ? order by pa.waktu_simpan desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgllahir"),
                        Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + rs.getString("kd_kamar_msk") + "'"),
                        rs.getString("tglmsk"),
                        rs.getString("jammsk"),
                        rs.getString("tiba"),
                        rs.getString("msk_melalui"),
                        rs.getString("keluhan_utama"),
                        rs.getString("tglases"),
                        rs.getString("jamases"),
                        rs.getString("perawat"),
                        rs.getString("kd_kamar_msk"),
                        rs.getString("tgl_msk_ruangan"),
                        rs.getString("jam_msk_ruangan"),
                        rs.getString("tiba_diruang"),
                        rs.getString("tiba_diruang_lainnya"),
                        rs.getString("msk_melalui"),
                        rs.getString("keluhan_utama"),
                        rs.getString("riwayat_alergi"),
                        rs.getString("alergi_obat"),
                        rs.getString("nm_alergi_obat"),
                        rs.getString("reaksi_alergi_obat"),
                        rs.getString("alergi_makanan"),
                        rs.getString("nm_alergi_makanan"),
                        rs.getString("reaksi_alergi_makanan"),
                        rs.getString("alergi_lainnya"),
                        rs.getString("nm_alergi_lainnya"),
                        rs.getString("reaksi_alergi_lainnya"),
                        rs.getString("pasang_gelang_tanda"),
                        rs.getString("alergi_diberitahukan"),
                        rs.getString("riwayat_penyakit_sekarang"),
                        rs.getString("campak"),
                        rs.getString("diare"),
                        rs.getString("difteri"),
                        rs.getString("tetanus"),
                        rs.getString("kejang"),
                        rs.getString("tbc"),
                        rs.getString("batuk_rejan"),
                        rs.getString("sesak"),
                        rs.getString("kuning"),
                        rs.getString("demam_tifoid"),
                        rs.getString("urtikaria"),
                        rs.getString("cacing"),
                        rs.getString("sakit_tenggorokan"),
                        rs.getString("lainya"),
                        rs.getString("kalimat_lainya"),
                        rs.getString("kesadaran"),
                        rs.getString("gcs_e"),
                        rs.getString("gcs_m"),
                        rs.getString("gcs_v"),
                        rs.getString("tensi"),
                        rs.getString("temp"),
                        rs.getString("hr"),
                        rs.getString("rr"),
                        rs.getString("bb_sebelum_skt"),
                        rs.getString("bb_msk_rs"),
                        rs.getString("tb"),
                        rs.getString("imt"),
                        rs.getString("crt"),
                        rs.getString("spo2"),
                        rs.getString("pernafasan"),
                        rs.getString("penglihatan"),
                        rs.getString("pendengaran"),
                        rs.getString("mulut"),
                        rs.getString("reflek_menelan"),
                        rs.getString("bicara"),
                        rs.getString("defekasi"),
                        rs.getString("miksi"),
                        rs.getString("gastrointestinal"),
                        rs.getString("pola_tidur"),
                        rs.getString("makan"),
                        rs.getString("berpakaian"),
                        rs.getString("buang_air"),
                        rs.getString("mandi"),
                        rs.getString("berpindah"),
                        rs.getString("kesimpulan"),
                        rs.getString("keluarga_terdekat"),
                        rs.getString("hubungan"),
                        rs.getString("tinggal_dengan"),
                        rs.getString("tinggal_dengan_lain"),
                        rs.getString("curiga_penganiayaan"),
                        rs.getString("butuh_bantuan_ibadah"),
                        rs.getString("status_emosional"),
                        rs.getString("hepatitis_b_umur1"),
                        rs.getString("hepatitis_b_umur2"),
                        rs.getString("hepatitis_b_umur3"),
                        rs.getString("hepatitis_b_umur4"),
                        rs.getString("hepatitis_b_umur_ulang"),
                        rs.getString("polio_umur1"),
                        rs.getString("polio_umur2"),
                        rs.getString("polio_umur3"),
                        rs.getString("polio_umur4"),
                        rs.getString("polio_umur_ulang"),
                        rs.getString("bcg_umur_dasar"),
                        rs.getString("bcg_umur_ulang"),
                        rs.getString("dpt_umur1"),
                        rs.getString("dpt_umur2"),
                        rs.getString("dpt_umur3"),
                        rs.getString("dpt_umur_ulang"),
                        rs.getString("meningitis_umur1"),
                        rs.getString("meningitis_umur2"),
                        rs.getString("meningitis_umur3"),
                        rs.getString("meningitis_umur_ulang"),
                        rs.getString("campak_umur_dasar"),
                        rs.getString("campak_umur_ulang"),
                        rs.getString("mk_menegakkan_kepala"),
                        rs.getString("mk_tengkurap"),
                        rs.getString("mk_duduk"),
                        rs.getString("mk_berjalan"),
                        rs.getString("mh_menggenggam_mainan"),
                        rs.getString("mh_mencari_benda"),
                        rs.getString("mh_mencoret"),
                        rs.getString("mh_mengenal_warna"),
                        rs.getString("bicara_berceloteh"),
                        rs.getString("bicara_mengucapkan"),
                        rs.getString("bicara_menyebutkan"),
                        rs.getString("bicara_menyebut_kata"),
                        rs.getString("se_tersenyum"),
                        rs.getString("se_bermain"),
                        rs.getString("se_mengenal"),
                        rs.getString("se_makan_minum"),
                        rs.getString("saat_ini_sekolah"),
                        rs.getString("kelas"),
                        rs.getString("onset"),
                        rs.getString("provocation"),
                        rs.getString("provocation_lain"),
                        rs.getString("quality"),
                        rs.getString("quality_lain"),
                        rs.getString("radiation"),
                        rs.getString("severity"),
                        rs.getString("time"),
                        rs.getString("time_lama"),
                        rs.getString("relief"),
                        rs.getString("relief_lain"),
                        rs.getString("asociated_sign"),
                        rs.getString("asociated_sign_lain"),
                        rs.getString("skala_nyeri"),
                        rs.getString("skrining_gizi_1"),
                        rs.getString("skrining_gizi_2"),
                        rs.getString("skrining_gizi_3"),
                        rs.getString("skrining_gizi_4"),
                        rs.getString("tindakan_pencegahan"),
                        rs.getString("obat_obatan"),
                        rs.getString("perawatan_luka"),
                        rs.getString("manajemen_lain"),
                        rs.getString("menejemen_nyeri"),
                        rs.getString("diet_nutrisi"),
                        rs.getString("fisioterapi"),
                        rs.getString("rehabilitasi_lain"),
                        rs.getString("hipertermi"),
                        rs.getString("nyeri"),
                        rs.getString("kurang_volume_cairan"),
                        rs.getString("lebih_volume_cairan"),
                        rs.getString("bersihkan_jln_nfs_tdk_efektif"),
                        rs.getString("pola_nfs_tdk_efektif"),
                        rs.getString("gangguan_pertukaran_gas"),
                        rs.getString("cemas"),
                        rs.getString("ketidakseimbangan_nutrisi"),
                        rs.getString("perubahan_perfusi"),
                        rs.getString("penurunan_curah_jantung"),
                        rs.getString("kerusakan_integritas"),
                        rs.getString("intoleransi_aktifitas"),
                        rs.getString("kurang_perawatan_diri"),
                        rs.getString("daftar_masalah_lain"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("jam_asesmen"),
                        rs.getString("nip_perawat"),
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
    
    public void emptTeks(){
        TtglMsk.setDate(new Date());
        cmbJam.setSelectedIndex(0);
        cmbMnt.setSelectedIndex(0);
        cmbDtk.setSelectedIndex(0);
        cmbTiba.setSelectedIndex(0);
        TtibaLain.setText("");
        TtibaLain.setEnabled(false);
        Tkeluhan.setText("");
        cmbRiwAlergi.setSelectedIndex(0);
        alergiObat = "";
        alergiMakanan = "";
        alergiLain = "";
        gelang = "";
        ChkAlergiObat.setSelected(false);
        ChkAlergiMakanan.setSelected(false);
        ChkAlergiLainya.setSelected(false);
        TnmAlergiObat.setText("");
        TnmAlergiMakanan.setText("");
        TnmAlergiLain.setText("");
        TnmAlergiObat.setEnabled(false);
        TnmAlergiMakanan.setEnabled(false);
        TnmAlergiLain.setEnabled(false);        
        ChkGelang.setSelected(false);
        TreaksiObat.setText("");
        TreaksiMakanan.setText("");
        TreaksiLain.setText("");
        TreaksiObat.setEnabled(false);
        TreaksiMakanan.setEnabled(false);
        TreaksiLain.setEnabled(false);
        cmbAlergiDiberitahu.setSelectedIndex(0);
        TriwPenyktSkg.setText("");
        
        ChkCampak.setSelected(false);
        ChkBatuk.setSelected(false);
        ChkSakit.setSelected(false);
        ChkDiare.setSelected(false);
        ChkSesak.setSelected(false);
        ChkDifteri.setSelected(false);
        ChkKuning.setSelected(false);
        ChkLainya.setSelected(false);
        Tlainnya.setText("");
        Tlainnya.setEnabled(false);
        ChkTetanus.setSelected(false);
        ChkDemam.setSelected(false);
        ChkKejang.setSelected(false);
        ChkUrtikaria.setSelected(false);
        ChkTbc.setSelected(false);
        ChkCacing.setSelected(false);

        campak = "";
        batukRejan = "";
        sakitTenggorokan = "";
        diare = "";
        sesak = "";
        difteri = "";
        kuning = "";
        lainnya = "";
        tetanus = "";
        demamTifoid = "";
        kejang = "";
        urtikaria = "";
        tbc = "";
        cacing = "";
        
        Tkesadaran.setText("");
        Tgcse.setText("");
        Tgcsm.setText("");
        Tgcsv.setText("");
        Ttensi.setText("");
        Ttemp.setText("");
        Thr.setText("");
        Trr.setText("");
        TbbBelum.setText("");
        TbbMasuk.setText("");
        Ttb.setText("");
        Timt.setText("");
        Tcrt.setText("");
        Tspo.setText("");
        cmbPernafasan.setSelectedIndex(0);
        cmbPenglihatan.setSelectedIndex(0);
        cmbPendengaran.setSelectedIndex(0);
        cmbMulut.setSelectedIndex(0);
        cmbReflek.setSelectedIndex(0);
        cmbBicara.setSelectedIndex(0);
        cmbDefekasi.setSelectedIndex(0);
        cmbMiksi.setSelectedIndex(0);
        cmbGastro.setSelectedIndex(0);
        cmbPola.setSelectedIndex(0);
        cmbMakan.setSelectedIndex(0);
        cmbBerpakaian.setSelectedIndex(0);
        cmbBuang.setSelectedIndex(0);
        cmbMandi.setSelectedIndex(0);
        cmbBerpindah.setSelectedIndex(0);
        Tkesimpulan.setText("");
        TKlgDekat.setText("");
        Thubungan.setText("");
        cmbTinggal.setSelectedIndex(0);
        TtglDenganLain.setText("");
        TtglDenganLain.setEnabled(false);
        cmbCuriga.setSelectedIndex(0);
        ibadah = "";
        ChkIbadah.setSelected(false);
        cmbStatus.setSelectedIndex(0);        
        Thepa1.setText("");
        Thepa2.setText("");
        Thepa3.setText("");
        Thepa4.setText("");
        ThepaUlang.setText("");        
        Tpolio1.setText("");
        Tpolio2.setText("");
        Tpolio3.setText("");
        Tpolio4.setText("");
        TpolioUlang.setText("");        
        TbcgDasar.setText("");
        TbcgUlang.setText("");        
        Tdpt1.setText("");
        Tdpt2.setText("");
        Tdpt3.setText("");
        TdptUlang.setText("");        
        Tmening1.setText("");
        Tmening2.setText("");
        Tmening3.setText("");
        TmeningUlang.setText("");        
        TcampakDasar.setText("");
        TcampakUlang.setText("");        
        TtegakanKepala.setText("");
        Ttengkurap.setText("");
        Tduduk.setText("");
        Tberjalan.setText("");        
        Tgenggam.setText("");
        Tmencari.setText("");
        Tmencoret.setText("");
        Tmengenal24.setText("");        
        Tberceloteh.setText("");
        Tmengucapkan.setText("");
        Tmenyebutkan.setText("");
        Tmenyebut36.setText("");        
        Ttersenyum.setText("");
        Tbermain.setText("");
        TmengenalAnggota.setText("");
        Tmakan.setText("");        
        cmbSekolah.setSelectedIndex(0);
        Tkelas.setText("");        
        ChkGizi1.setSelected(false);
        ChkGizi2.setSelected(false);
        ChkGizi3.setSelected(false);
        ChkGizi4.setSelected(false);
        
        cekgizi1 = "";
        cekgizi2 = "";
        cekgizi3 = "";
        cekgizi4 = "";
        
        TskorGizi1.setText("0");
        TskorGizi2.setText("0");
        TskorGizi3.setText("0");
        TskorGizi4.setText("0");        
        kesimpulanGizi.setText("");
        TotSkorGizi.setText("0");
        
        Tonset.setText("");
        cmbProvo.setSelectedIndex(0);
        Tprovo.setText("");
        Tprovo.setEnabled(false);
        cmbQuality.setSelectedIndex(0);
        Tquality.setText("");
        Tquality.setEnabled(false);
        cmbRadia.setSelectedIndex(0);
        cmbSever.setSelectedIndex(0);
        cmbTime.setSelectedIndex(0);
        cmbLama.setSelectedIndex(0);
        cmbRelief.setSelectedIndex(0);
        Trelief.setText("");
        Trelief.setEnabled(false);
        cmbAsso.setSelectedIndex(0);
        Tasso.setText("");
        Tasso.setEnabled(false);
        cmbSkala.setSelectedIndex(0);  
        
        TotSkorRJ.setText("0");
        kesimpulanResikoJatuh.setText("");
        BtnAllResikoActionPerformed(null);
        cmbTindakanCegah.setSelectedIndex(0);
        
        obatObatan = "";
        perawatanLuka = "";
        ChkObat.setSelected(false);
        ChkPerawatan.setSelected(false);
        TmanajemenLain.setText("");
        manajemenNyeri = "";
        diet = "";
        fisio = "";
        ChkManajemen.setSelected(false);
        ChkDiet.setSelected(false);
        ChkFisioterapi.setSelected(false);
        TrehabLain.setText("");
        
        hipertermi = "";
        nyeri = "";
        resiko = "";
        kelebihan = "";
        bersihkan = "";
        pola = "";
        gangguan = "";
        cemas = "";
        ketidakseimbangan = "";
        perubahan = "";
        penurunan = "";
        kerusakan = "";
        intoleransi = "";
        kurang = "";
        
        ChkHipertermi.setSelected(false);
        ChkNyeri.setSelected(false);
        ChkResiko.setSelected(false);
        ChkKelebihan.setSelected(false);
        ChkBersihkan.setSelected(false);
        ChkPola.setSelected(false);
        ChkGangguan.setSelected(false);
        ChkCemas.setSelected(false);
        ChkKetidakseimbangan.setSelected(false);
        ChkPerubahan.setSelected(false);
        ChkPenurunan.setSelected(false);
        ChkKerusakan.setSelected(false);
        ChkIntoleransi.setSelected(false);
        ChkKurang.setSelected(false);
        TmasalahLain.setText("");
        
        TtglAses.setDate(new Date());
        cmbJam1.setSelectedIndex(0);
        cmbMnt1.setSelectedIndex(0);
        cmbDtk1.setSelectedIndex(0);
        nip = "-";
        TnmPerawat.setText("-");
    }
    
    public void setData(String norwt, String kdkmr) {
        TNoRw.setText(norwt);
        TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norwt + "'"));
        TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        kdkamar = kdkmr;
        Truangan.setText(Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + kdkmr + "'"));
        TnoTelp.setText(Sequel.cariIsi("select ifnull(no_tlp,'0') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(new Date());
        
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        
        if (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat='" + norwt + "'") == 0) {
            cmbMasuk.setSelectedIndex(0);
        } else {
            cmbMasuk.setSelectedIndex(1);
        }
        TCari.setText(norwt);
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnPrint.setEnabled(akses.getcppt());
        BtnEdit.setEnabled(akses.getcppt());
        
        if (akses.getjml2() >= 1) {            
//            BtnPerawat.setEnabled(false);
            nip = akses.getkode();            
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPerawat, nip);
            if (TnmPerawat.getText().equals("")) {
                nip = "";
//                JOptionPane.showMessageDialog(null, "User login bukan dokter...!!");
            }
        }  
    }
    
    private void getData() {
        bersihkanString();
        if (tbAsesmen.getSelectedRow() != -1) {
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 2).toString());
            kdkamar = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString();
            Truangan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 4).toString());
            Valid.SetTgl(TtglMsk, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString());
            cmbJam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString().substring(6, 8));
            cmbTiba.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString());
            TtibaLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString());
            cmbMasuk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString());
            Tkeluhan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 19).toString());
            cmbRiwAlergi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString());
            alergiObat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString();
            TnmAlergiObat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString());
            TreaksiObat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString());            
            alergiMakanan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString();
            TnmAlergiMakanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString());
            TreaksiMakanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString());            
            alergiLain = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString();
            TnmAlergiLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString());
            TreaksiLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString());            
            gelang = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString();
            cmbAlergiDiberitahu.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString());            
            TriwPenyktSkg.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 32).toString());            
            campak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 33).toString();
            diare = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString();
            difteri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString();
            tetanus = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString();
            kejang = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString();
            tbc = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString();
            batukRejan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString();
            sesak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString();
            kuning = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString();
            demamTifoid = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString();
            urtikaria = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString();
            cacing = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString();
            sakitTenggorokan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString();
            lainnya = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString();
            Tlainnya.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString());            
            Tkesadaran.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString());
            Tgcse.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString());
            Tgcsm.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString());
            Tgcsv.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString());            
            Ttensi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString());
            Ttemp.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString());
            Thr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString());
            Trr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString());
            TbbBelum.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString());
            TbbMasuk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString());
            Ttb.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString());
            Timt.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString());
            Tcrt.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString());
            Tspo.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString());
            cmbPernafasan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 62).toString());
            cmbPenglihatan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString());
            cmbPendengaran.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 64).toString());
            cmbMulut.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString());
            cmbReflek.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 66).toString());
            cmbBicara.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString());
            cmbDefekasi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString());
            cmbMiksi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString());
            cmbGastro.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString());
            cmbPola.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString());
            cmbMakan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString());            
            cmbBerpakaian.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString());
            cmbBuang.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString());
            cmbMandi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString());
            cmbBerpindah.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString());
            Tkesimpulan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString());
            TKlgDekat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString());
            TnoTelp.setText(Sequel.cariIsi("select ifnull(no_tlp,'0') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Thubungan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 79).toString());
            cmbTinggal.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 80).toString());
            TtglDenganLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 81).toString());
            cmbCuriga.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 82).toString());
            ibadah = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 83).toString();
            cmbStatus.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 84).toString());
            Thepa1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 85).toString());
            Thepa2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 86).toString());
            Thepa3.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 87).toString());
            Thepa4.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 88).toString());
            ThepaUlang.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 89).toString());
            Tpolio1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 90).toString());
            Tpolio2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 91).toString());
            Tpolio3.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 92).toString());
            Tpolio4.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 93).toString());
            TpolioUlang.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString());
            TbcgDasar.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 95).toString());
            TbcgUlang.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 96).toString());
            Tdpt1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 97).toString());
            Tdpt2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 98).toString());
            Tdpt3.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 99).toString());
            TdptUlang.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 100).toString());            
            Tmening1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString());
            Tmening2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 102).toString());
            Tmening3.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 103).toString());
            TmeningUlang.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 104).toString());            
            TcampakDasar.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 105).toString());
            TcampakUlang.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 106).toString());            
            TtegakanKepala.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 107).toString());
            Ttengkurap.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 108).toString());
            Tduduk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 109).toString());
            Tberjalan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 110).toString());            
            Tgenggam.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 111).toString());
            Tmencari.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 112).toString());
            Tmencoret.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 113).toString());
            Tmengenal24.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 114).toString());            
            Tberceloteh.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 115).toString());
            Tmengucapkan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 116).toString());
            Tmenyebutkan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 117).toString());
            Tmenyebut36.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 118).toString());            
            Ttersenyum.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 119).toString());
            Tbermain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 120).toString());
            TmengenalAnggota.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 121).toString());
            Tmakan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 122).toString());            
            cmbSekolah.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 123).toString());
            Tkelas.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 124).toString());
            Tonset.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 125).toString());
            cmbProvo.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 126).toString());
            Tprovo.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 127).toString());
            cmbQuality.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 128).toString());
            Tquality.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 129).toString());
            cmbRadia.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 130).toString());
            cmbSever.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 131).toString());
            cmbTime.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 132).toString());
            cmbLama.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 133).toString());
            cmbRelief.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 134).toString());            
            Trelief.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 135).toString());
            cmbAsso.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 136).toString());
            Tasso.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 137).toString());
            cmbSkala.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 138).toString());            
            cekgizi1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 139).toString();
            cekgizi2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 140).toString();
            cekgizi3 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 141).toString();
            cekgizi4 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 142).toString();            
            cmbTindakanCegah.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 143).toString());
            obatObatan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 144).toString();
            perawatanLuka = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 145).toString();            
            TmanajemenLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 146).toString());
            manajemenNyeri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 147).toString();
            diet = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 148).toString();
            fisio = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 149).toString();            
            TrehabLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 150).toString());
            hipertermi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 151).toString();
            nyeri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 152).toString();
            resiko = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 153).toString();
            kelebihan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 154).toString();
            bersihkan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 155).toString();
            pola = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 156).toString();
            gangguan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 157).toString();            
            cemas = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 158).toString();
            ketidakseimbangan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 159).toString();
            perubahan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 160).toString();
            penurunan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 161).toString();
            kerusakan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 162).toString();
            intoleransi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 163).toString();
            kurang = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 164).toString();            
            TmasalahLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 165).toString());
            Valid.SetTgl(TtglAses, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 166).toString());            
            cmbJam1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 167).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 167).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 167).toString().substring(6, 8));
            nip = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 168).toString();
            TnmPerawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString());
            dataCek();
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from penilaian_awal_keperawatan_anak_ranap where no_rawat=?", 1, new String[]{
                tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
            }) == true) {
                Sequel.meghapus("penilaian_awal_keperawatan_anak_ranap_resiko", "no_rawat",
                        tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());                
                
                tampil();
                emptTeks();
                tampilFaktorResiko();
                TabRawat.setSelectedIndex(1);              
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        }
    }
    
    private void ganti() {
        cekData();
        if (Sequel.mengedittf("penilaian_awal_keperawatan_anak_ranap", "no_rawat=?", "kd_kamar_msk=?, tgl_msk_ruangan=?, jam_msk_ruangan=?, tiba_diruang=?, tiba_diruang_lainnya=?, msk_melalui=?, keluhan_utama=?, riwayat_alergi=?, alergi_obat=?, "
                + "nm_alergi_obat=?, reaksi_alergi_obat=?, alergi_makanan=?, nm_alergi_makanan=?, reaksi_alergi_makanan=?, alergi_lainnya=?, nm_alergi_lainnya=?, reaksi_alergi_lainnya=?, "
                + "pasang_gelang_tanda=?, alergi_diberitahukan=?, riwayat_penyakit_sekarang=?, campak=?, diare=?, difteri=?, tetanus=?, kejang=?, tbc=?, batuk_rejan=?, sesak=?, "
                + "kuning=?, demam_tifoid=?, urtikaria=?, cacing=?, sakit_tenggorokan=?, lainya=?, kalimat_lainya=?, kesadaran=?, gcs_e=?, gcs_m=?, gcs_v=?, tensi=?, temp=?, "
                + "hr=?, rr=?, bb_sebelum_skt=?, bb_msk_rs=?, tb=?, imt=?, crt=?, spo2=?, pernafasan=?, penglihatan=?, pendengaran=?, mulut=?, reflek_menelan=?, bicara=?, "
                + "defekasi=?, miksi=?, gastrointestinal=?, pola_tidur=?, makan=?, berpakaian=?, buang_air=?, mandi=?, berpindah=?, kesimpulan=?, keluarga_terdekat=?, hubungan=?, "
                + "tinggal_dengan=?, tinggal_dengan_lain=?, curiga_penganiayaan=?, butuh_bantuan_ibadah=?, status_emosional=?, hepatitis_b_umur1=?, hepatitis_b_umur2=?, "
                + "hepatitis_b_umur3=?, hepatitis_b_umur4=?, hepatitis_b_umur_ulang=?, polio_umur1=?, polio_umur2=?, polio_umur3=?, polio_umur4=?, polio_umur_ulang=?, "
                + "bcg_umur_dasar=?, bcg_umur_ulang=?, dpt_umur1=?, dpt_umur2=?, dpt_umur3=?, dpt_umur_ulang=?, meningitis_umur1=?, meningitis_umur2=?, meningitis_umur3=?, "
                + "meningitis_umur_ulang=?, campak_umur_dasar=?, campak_umur_ulang=?, mk_menegakkan_kepala=?, mk_tengkurap=?, mk_duduk=?, mk_berjalan=?, mh_menggenggam_mainan=?, "
                + "mh_mencari_benda=?, mh_mencoret=?, mh_mengenal_warna=?, bicara_berceloteh=?, bicara_mengucapkan=?, bicara_menyebutkan=?, bicara_menyebut_kata=?, se_tersenyum=?, "
                + "se_bermain=?, se_mengenal=?, se_makan_minum=?, saat_ini_sekolah=?, kelas=?, onset=?, provocation=?, provocation_lain=?, quality=?, quality_lain=?, radiation=?, "
                + "severity=?, time=?, time_lama=?, relief=?, relief_lain=?, asociated_sign=?, asociated_sign_lain=?, skala_nyeri=?, skrining_gizi_1=?, skrining_gizi_2=?, "
                + "skrining_gizi_3=?, skrining_gizi_4=?, tindakan_pencegahan=?, obat_obatan=?, perawatan_luka=?, manajemen_lain=?, menejemen_nyeri=?, diet_nutrisi=?, "
                + "fisioterapi=?, rehabilitasi_lain=?, hipertermi=?, nyeri=?, kurang_volume_cairan=?, lebih_volume_cairan=?, bersihkan_jln_nfs_tdk_efektif=?, pola_nfs_tdk_efektif=?, "
                + "gangguan_pertukaran_gas=?, cemas=?, ketidakseimbangan_nutrisi=?, perubahan_perfusi=?, penurunan_curah_jantung=?, kerusakan_integritas=?, intoleransi_aktifitas=?, "
                + "kurang_perawatan_diri=?, daftar_masalah_lain=?, tgl_asesmen=?, jam_asesmen=?, nip_perawat=?", 157, new String[]{
                    kdkamar, Valid.SetTgl(TtglMsk.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                    cmbTiba.getSelectedItem().toString(), TtibaLain.getText(), cmbMasuk.getSelectedItem().toString(), Tkeluhan.getText(), cmbRiwAlergi.getSelectedItem().toString(),
                    alergiObat, TnmAlergiObat.getText(), TreaksiObat.getText(), alergiMakanan, TnmAlergiMakanan.getText(), TreaksiMakanan.getText(), alergiLain, TnmAlergiLain.getText(),
                    TreaksiLain.getText(), gelang, cmbAlergiDiberitahu.getSelectedItem().toString(), TriwPenyktSkg.getText(), campak, diare, difteri, tetanus, kejang, tbc, batukRejan,
                    sesak, kuning, demamTifoid, urtikaria, cacing, sakitTenggorokan, lainnya, Tlainnya.getText(), Tkesadaran.getText(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(),
                    Ttensi.getText(), Ttemp.getText(), Thr.getText(), Trr.getText(), TbbBelum.getText(), TbbMasuk.getText().trim(), Ttb.getText().trim(), Timt.getText().trim(), Tcrt.getText(),
                    Tspo.getText(), cmbPernafasan.getSelectedItem().toString(), cmbPenglihatan.getSelectedItem().toString(), cmbPendengaran.getSelectedItem().toString(), cmbMulut.getSelectedItem().toString(),
                    cmbReflek.getSelectedItem().toString(), cmbBicara.getSelectedItem().toString(), cmbDefekasi.getSelectedItem().toString(), cmbMiksi.getSelectedItem().toString(),
                    cmbGastro.getSelectedItem().toString(), cmbPola.getSelectedItem().toString(), cmbMakan.getSelectedItem().toString(), cmbBerpakaian.getSelectedItem().toString(),
                    cmbBuang.getSelectedItem().toString(), cmbMandi.getSelectedItem().toString(), cmbBerpindah.getSelectedItem().toString(), Tkesimpulan.getText(), TKlgDekat.getText(),
                    Thubungan.getText(), cmbTinggal.getSelectedItem().toString(), TtglDenganLain.getText(), cmbCuriga.getSelectedItem().toString(), ibadah, cmbStatus.getSelectedItem().toString(),
                    Thepa1.getText(), Thepa2.getText(), Thepa3.getText(), Thepa4.getText(), ThepaUlang.getText(), Tpolio1.getText(), Tpolio2.getText(), Tpolio3.getText(), Tpolio4.getText(),
                    TpolioUlang.getText(), TbcgDasar.getText(), TbcgUlang.getText(), Tdpt1.getText(), Tdpt2.getText(), Tdpt3.getText(), TdptUlang.getText(), Tmening1.getText(),
                    Tmening2.getText(), Tmening3.getText(), TmeningUlang.getText(), TcampakDasar.getText(), TcampakUlang.getText(), TtegakanKepala.getText(), Ttengkurap.getText(),
                    Tduduk.getText(), Tberjalan.getText(), Tgenggam.getText(), Tmencari.getText(), Tmencoret.getText(), Tmengenal24.getText(), Tberceloteh.getText(), Tmengucapkan.getText(),
                    Tmenyebutkan.getText(), Tmenyebut36.getText(), Ttersenyum.getText(), Tbermain.getText(), Tmengenal24.getText(), Tmakan.getText(), cmbSekolah.getSelectedItem().toString(),
                    Tkelas.getText(), Tonset.getText(), cmbProvo.getSelectedItem().toString(), Tprovo.getText(), cmbQuality.getSelectedItem().toString(), Tquality.getText(),
                    cmbRadia.getSelectedItem().toString(), cmbSever.getSelectedItem().toString(), cmbTime.getSelectedItem().toString(), cmbLama.getSelectedItem().toString(),
                    cmbRelief.getSelectedItem().toString(), Trelief.getText(), cmbAsso.getSelectedItem().toString(), Tasso.getText(), cmbSkala.getSelectedItem().toString(),
                    cekgizi1, cekgizi2, cekgizi3, cekgizi4, cmbTindakanCegah.getSelectedItem().toString(), obatObatan, perawatanLuka, TmanajemenLain.getText(), manajemenNyeri,
                    diet, fisio, TrehabLain.getText(), hipertermi, nyeri, resiko, kelebihan, bersihkan, pola, gangguan, cemas, ketidakseimbangan, perubahan, penurunan, kerusakan,
                    intoleransi, kurang, TmasalahLain.getText(), Valid.SetTgl(TtglAses.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                    nip, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                }) == true) {

            Sequel.meghapus("penilaian_awal_keperawatan_anak_ranap_resiko", "no_rawat",
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            
            //simpan faktor resiko
            for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
                if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("penilaian_awal_keperawatan_anak_ranap_resiko", "?,?", 2, new String[]{
                        TNoRw.getText(), tbFaktorResiko.getValueAt(i, 1).toString()});
                }
            }

            TabRawat.setSelectedIndex(1);
            tampilFaktorResiko();
            tampil();
            emptTeks();
        }
    }
    
    private void cekData() {
        if (ChkAlergiObat.isSelected() == true) {
            alergiObat = "ya";
        } else {
            alergiObat = "tidak";
        }
        
        if (ChkAlergiMakanan.isSelected() == true) {
            alergiMakanan = "ya";
        } else {
            alergiMakanan = "tidak";
        }
        
        if (ChkAlergiLainya.isSelected() == true) {
            alergiLain = "ya";
        } else {
            alergiLain = "tidak";
        }
        
        if (ChkGelang.isSelected() == true) {
            gelang = "ya";
        } else {
            gelang = "tidak";
        }
        
        if (ChkCampak.isSelected() == true) {
            campak = "ya";
        } else {
            campak = "tidak";
        }
        
        if (ChkBatuk.isSelected() == true) {
            batukRejan = "ya";
        } else {
            batukRejan = "tidak";
        }
        
        if (ChkSakit.isSelected() == true) {
            sakitTenggorokan = "ya";
        } else {
            sakitTenggorokan = "tidak";
        }
        
        if (ChkDiare.isSelected() == true) {
            diare = "ya";
        } else {
            diare = "tidak";
        }
        
        if (ChkSesak.isSelected() == true) {
            sesak = "ya";
        } else {
            sesak = "tidak";
        }
        
        if (ChkDifteri.isSelected() == true) {
            difteri = "ya";
        } else {
            difteri = "tidak";
        }
        
        if (ChkKuning.isSelected() == true) {
            kuning = "ya";
        } else {
            kuning = "tidak";
        }
        
        if (ChkLainya.isSelected() == true) {
            lainnya = "ya";
        } else {
            lainnya = "tidak";
        }
        
        if (ChkTetanus.isSelected() == true) {
            tetanus = "ya";
        } else {
            tetanus = "tidak";
        }
        
        if (ChkDemam.isSelected() == true) {
            demamTifoid = "ya";
        } else {
            demamTifoid = "tidak";
        }
        
        if (ChkKejang.isSelected() == true) {
            kejang = "ya";
        } else {
            kejang = "tidak";
        }
        
        if (ChkUrtikaria.isSelected() == true) {
            urtikaria = "ya";
        } else {
            urtikaria = "tidak";
        }
        
        if (ChkTbc.isSelected() == true) {
            tbc = "ya";
        } else {
            tbc = "tidak";
        }
        
        if (ChkCacing.isSelected() == true) {
            cacing = "ya";
        } else {
            cacing = "tidak";
        }
        
        if (ChkIbadah.isSelected() == true) {
            ibadah = "ya";
        } else {
            ibadah = "tidak";
        }
        
        if (ChkGizi1.isSelected() == true) {
            cekgizi1 = "ya";
        } else {
            cekgizi1 = "tidak";
        }
        
        if (ChkGizi2.isSelected() == true) {
            cekgizi2 = "ya";
        } else {
            cekgizi2 = "tidak";
        }
        
        if (ChkGizi3.isSelected() == true) {
            cekgizi3 = "ya";
        } else {
            cekgizi3 = "tidak";
        }
        
        if (ChkGizi4.isSelected() == true) {
            cekgizi4 = "ya";
        } else {
            cekgizi4 = "tidak";
        }
        
        if (ChkObat.isSelected() == true) {
            obatObatan = "ya";
        } else {
            obatObatan = "tidak";
        }
        
        if (ChkPerawatan.isSelected() == true) {
            perawatanLuka = "ya";
        } else {
            perawatanLuka = "tidak";
        }
        
        if (ChkManajemen.isSelected() == true) {
            manajemenNyeri = "ya";
        } else {
            manajemenNyeri = "tidak";
        }
        
        if (ChkDiet.isSelected() == true) {
            diet = "ya";
        } else {
            diet = "tidak";
        }
        
        if (ChkFisioterapi.isSelected() == true) {
            fisio = "ya";
        } else {
            fisio = "tidak";
        }
        
        if (ChkHipertermi.isSelected() == true) {
            hipertermi = "ya";
        } else {
            hipertermi = "tidak";
        }
        
        if (ChkNyeri.isSelected() == true) {
            nyeri = "ya";
        } else {
            nyeri = "tidak";
        }
        
        if (ChkResiko.isSelected() == true) {
            resiko = "ya";
        } else {
            resiko = "tidak";
        }
        
        if (ChkKelebihan.isSelected() == true) {
            kelebihan = "ya";
        } else {
            kelebihan = "tidak";
        }
        
        if (ChkBersihkan.isSelected() == true) {
            bersihkan = "ya";
        } else {
            bersihkan = "tidak";
        }
        
        if (ChkPola.isSelected() == true) {
            pola = "ya";
        } else {
            pola = "tidak";
        }
        
        if (ChkGangguan.isSelected() == true) {
            gangguan = "ya";
        } else {
            gangguan = "tidak";
        }
        
        if (ChkCemas.isSelected() == true) {
            cemas = "ya";
        } else {
            cemas = "tidak";
        }
        
        if (ChkKetidakseimbangan.isSelected() == true) {
            ketidakseimbangan = "ya";
        } else {
            ketidakseimbangan = "tidak";
        }
        
        if (ChkPerubahan.isSelected() == true) {
            perubahan = "ya";
        } else {
            perubahan = "tidak";
        }
        
        if (ChkPenurunan.isSelected() == true) {
            penurunan = "ya";
        } else {
            penurunan = "tidak";
        }
        
        if (ChkKerusakan.isSelected() == true) {
            kerusakan = "ya";
        } else {
            kerusakan = "tidak";
        }
        
        if (ChkIntoleransi.isSelected() == true) {
            intoleransi = "ya";
        } else {
            intoleransi = "tidak";
        }
        
        if (ChkKurang.isSelected() == true) {
            kurang = "ya";
        } else {
            kurang = "tidak";
        }
    }
    
    private void dataCek() {
        //tiba diruang
        if (cmbTiba.getSelectedIndex() == 4) {
            TtibaLain.setEnabled(true);
        } else {
            TtibaLain.setEnabled(false);
        }
        
        //riwayat alergi
        if (alergiObat.equals("ya")) {
            ChkAlergiObat.setSelected(true);
            TreaksiObat.setEnabled(true);
            TnmAlergiObat.setEnabled(true);
        } else {
            ChkAlergiObat.setSelected(false);
            TreaksiObat.setEnabled(false);
            TnmAlergiObat.setEnabled(false);
        }
        
        if (alergiMakanan.equals("ya")) {
            ChkAlergiMakanan.setSelected(true);
            TreaksiMakanan.setEnabled(true);
            TnmAlergiMakanan.setEnabled(true);
        } else {
            ChkAlergiMakanan.setSelected(false);
            TreaksiMakanan.setEnabled(false);
            TnmAlergiMakanan.setEnabled(false);
        }
        
        if (alergiLain.equals("ya")) {
            ChkAlergiLainya.setSelected(true);
            TreaksiLain.setEnabled(true);
            TnmAlergiLain.setEnabled(true);
        } else {
            ChkAlergiLainya.setSelected(false);
            TreaksiLain.setEnabled(false);
            TnmAlergiLain.setEnabled(false);
        }
        
        if (gelang.equals("ya")) {
            ChkGelang.setSelected(true);
        } else {
            ChkGelang.setSelected(false);
        }
        
        //riwayat penyakit dahulu
        if (campak.equals("ya")) {
            ChkCampak.setSelected(true);
        } else {
            ChkCampak.setSelected(false);
        }
        
        if (batukRejan.equals("ya")) {
            ChkBatuk.setSelected(true);
        } else {
            ChkBatuk.setSelected(false);
        }
        
        if (sakitTenggorokan.equals("ya")) {
            ChkSakit.setSelected(true);
        } else {
            ChkSakit.setSelected(false);
        }
        
        if (diare.equals("ya")) {
            ChkDiare.setSelected(true);
        } else {
            ChkDiare.setSelected(false);
        }
        
        if (sesak.equals("ya")) {
            ChkSesak.setSelected(true);
        } else {
            ChkSesak.setSelected(false);
        }
        
        if (difteri.equals("ya")) {
            ChkDifteri.setSelected(true);
        } else {
            ChkDifteri.setSelected(false);
        }
        
        if (kuning.equals("ya")) {
            ChkKuning.setSelected(true);
        } else {
            ChkKuning.setSelected(false);
        }
        
        if (lainnya.equals("ya")) {
            ChkLainya.setSelected(true);
            Tlainnya.setEnabled(true);
        } else {
            ChkLainya.setSelected(false);
            Tlainnya.setEnabled(false);
        }
        
        if (tetanus.equals("ya")) {
            ChkTetanus.setSelected(true);
        } else {
            ChkTetanus.setSelected(false);
        }
        
        if (demamTifoid.equals("ya")) {
            ChkDemam.setSelected(true);
        } else {
            ChkDemam.setSelected(false);
        }
        
        if (kejang.equals("ya")) {
            ChkKejang.setSelected(true);
        } else {
            ChkKejang.setSelected(false);
        }
        
        if (urtikaria.equals("ya")) {
            ChkUrtikaria.setSelected(true);
        } else {
            ChkUrtikaria.setSelected(false);
        }
        
        if (tbc.equals("ya")) {
            ChkTbc.setSelected(true);
        } else {
            ChkTbc.setSelected(false);
        }
        
        if (cacing.equals("ya")) {
            ChkCacing.setSelected(true);
        } else {
            ChkCacing.setSelected(false);
        }
        
        if (ibadah.equals("ya")) {
            ChkIbadah.setSelected(true);
        } else {
            ChkIbadah.setSelected(false);
        }
        
        //skrining gizi
        if (cekgizi1.equals("ya")) {
            ChkGizi1.setSelected(true);
            TskorGizi1.setText("1");
        } else {
            ChkGizi1.setSelected(false);
            TskorGizi1.setText("0");
        }
        
        if (cekgizi2.equals("ya")) {
            ChkGizi2.setSelected(true);
            TskorGizi2.setText("1");
        } else {
            ChkGizi2.setSelected(false);
            TskorGizi2.setText("0");
        }
        
        if (cekgizi3.equals("ya")) {
            ChkGizi3.setSelected(true);
            TskorGizi3.setText("1");
        } else {
            ChkGizi3.setSelected(false);
            TskorGizi3.setText("0");
        }
        
        if (cekgizi4.equals("ya")) {
            ChkGizi4.setSelected(true);
            TskorGizi4.setText("2");
        } else {
            ChkGizi4.setSelected(false);
            TskorGizi4.setText("0");
        }
        hitungSkorGizi();

        //tindakan pencegahan resiko jatuh
        if (cmbTindakanCegah.getSelectedIndex() == 0 || cmbTindakanCegah.getSelectedIndex() == 1) {
            TabPencegahanAnak.setSelectedIndex(0);
        } else if (cmbTindakanCegah.getSelectedIndex() == 2) {
            TabPencegahanAnak.setSelectedIndex(1);
        } else {
            TabPencegahanAnak.setSelectedIndex(0);
        }
        
        //tinggal dengan
        if (cmbTinggal.getSelectedIndex() == 7) {
            TtglDenganLain.setEnabled(true);
        } else {
            TtglDenganLain.setEnabled(false);
        }
        
        //provocation
        if (cmbProvo.getSelectedIndex() == 5) {
            Tprovo.setEnabled(true);
        } else {
            Tprovo.setEnabled(false);
        }
        
        //quality
        if (cmbQuality.getSelectedIndex() == 9) {
            Tquality.setEnabled(true);
        } else {
            Tquality.setEnabled(false);
        }
        
        //relief
        if (cmbRelief.getSelectedIndex() == 4) {
            Trelief.setEnabled(true);
        } else {
            Trelief.setEnabled(false);
        }
        
        //associated sign
        if (cmbAsso.getSelectedIndex() == 6) {
            Tasso.setEnabled(true);
        } else {
            Tasso.setEnabled(false);
        }
        
        //faktor resiko
        try {
            Valid.tabelKosong(tabMode1);
                ps1 = koneksi.prepareStatement("select m.kode_resiko, m.faktor_resiko, m.skala, m.skor, m.asesmen FROM master_faktor_resiko_igd m "
                        + "INNER JOIN penilaian_awal_keperawatan_anak_ranap_resiko pa ON pa.kode_resiko = m.kode_resiko "
                        + "WHERE m.asesmen = 'Anak Ranap' and pa.no_rawat=? ORDER BY pa.kode_resiko");
            try {
                ps1.setString(1, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        true,
                        rs1.getString("kode_resiko"),
                        rs1.getString("asesmen"),
                        rs1.getString("faktor_resiko"),
                        rs1.getString("skala"),
                        rs1.getString("skor")
                    });
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
        hitungSkorRJ();
        
        //kebutuhan edukasi
        if (obatObatan.equals("ya")) {
            ChkObat.setSelected(true);
        } else {
            ChkObat.setSelected(false);
        }
        
        if (perawatanLuka.equals("ya")) {
            ChkPerawatan.setSelected(true);
        } else {
            ChkPerawatan.setSelected(false);
        }
        
        if (manajemenNyeri.equals("ya")) {
            ChkManajemen.setSelected(true);
        } else {
            ChkManajemen.setSelected(false);
        }
        
        if (diet.equals("ya")) {
            ChkDiet.setSelected(true);
        } else {
            ChkDiet.setSelected(false);
        }
        
        if (fisio.equals("ya")) {
            ChkFisioterapi.setSelected(true);
        } else {
            ChkFisioterapi.setSelected(false);
        }
        
        //daftar masalah
        if (hipertermi.equals("ya")) {
            ChkHipertermi.setSelected(true);
        } else {
            ChkHipertermi.setSelected(false);
        }
        
        if (nyeri.equals("ya")) {
            ChkNyeri.setSelected(true);
        } else {
            ChkNyeri.setSelected(false);
        }
        
        if (resiko.equals("ya")) {
            ChkResiko.setSelected(true);
        } else {
            ChkResiko.setSelected(false);
        }
        
        if (kelebihan.equals("ya")) {
            ChkKelebihan.setSelected(true);
        } else {
            ChkKelebihan.setSelected(false);
        }
        
        if (bersihkan.equals("ya")) {
            ChkBersihkan.setSelected(true);
        } else {
            ChkBersihkan.setSelected(false);
        }
        
        if (pola.equals("ya")) {
            ChkPola.setSelected(true);
        } else {
            ChkPola.setSelected(false);
        }
        
        if (gangguan.equals("ya")) {
            ChkGangguan.setSelected(true);
        } else {
            ChkGangguan.setSelected(false);
        }
        
        if (cemas.equals("ya")) {
            ChkCemas.setSelected(true);
        } else {
            ChkCemas.setSelected(false);
        }
        
        if (ketidakseimbangan.equals("ya")) {
            ChkKetidakseimbangan.setSelected(true);
        } else {
            ChkKetidakseimbangan.setSelected(false);
        }
        
        if (perubahan.equals("ya")) {
            ChkPerubahan.setSelected(true);
        } else {
            ChkPerubahan.setSelected(false);
        }
        
        if (penurunan.equals("ya")) {
            ChkPenurunan.setSelected(true);
        } else {
            ChkPenurunan.setSelected(false);
        }
        
        if (kerusakan.equals("ya")) {
            ChkKerusakan.setSelected(true);
        } else {
            ChkKerusakan.setSelected(false);
        }
        
        if (intoleransi.equals("ya")) {
            ChkIntoleransi.setSelected(true);
        } else {
            ChkIntoleransi.setSelected(false);
        }
        
        if (kurang.equals("ya")) {
            ChkKurang.setSelected(true);
        } else {
            ChkKurang.setSelected(false);
        }
    }
    
    private void hitungSkorGizi() {
        int A, B, C, D, Total;
        A = Integer.parseInt(TskorGizi1.getText());
        B = Integer.parseInt(TskorGizi2.getText());
        C = Integer.parseInt(TskorGizi3.getText());
        D = Integer.parseInt(TskorGizi4.getText());

        Total = 0;
        Total = A + B + C + D;
        TotSkorGizi.setText(Valid.SetAngka2(Total));

        if (Total == 0) {
            kesimpulanGizi.setText("Tidak beresiko malnutrisi");
        } else if (Total >=1 && Total <=3) {
            kesimpulanGizi.setText("Resiko malnutrisi sedang, perlu pemantauan");
        } else if (Total >= 4) {
            kesimpulanGizi.setText("Resiko malnutrisi berat, perlu pemantauan lanjutan oleh Tim Gizi/Dietsien");
        }
    }
    
    private void hitungSkorRJ() {
        skor = 0;
        for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
            if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                skor = skor + Integer.parseInt(tbFaktorResiko.getValueAt(i, 5).toString());
            }
        }
        
        TotSkorRJ.setText(Valid.SetAngka2(skor));
        if (skor >= 7 && skor <= 11) {
            kesimpulanResikoJatuh.setText("Resiko Rendah (7 s.d 11)");
        } else if (skor >= 12 && skor <=23) {
            kesimpulanResikoJatuh.setText("Resiko Tinggi");
        } else {
            kesimpulanResikoJatuh.setText("Skor minimal 7, maksimal 23");
        }
    }
    
    public void tampilFaktorResiko() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("select kode_resiko, faktor_resiko, skala, skor, asesmen from master_faktor_resiko_igd where "
                    + "asesmen = 'Anak Ranap' and faktor_resiko like ? or "
                    + "asesmen = 'Anak Ranap' and skala like ? order by kode_resiko");
            try {
                ps1.setString(1, "%" + TCariResiko.getText().trim() + "%");
                ps1.setString(2, "%" + TCariResiko.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        false,
                        rs1.getString("kode_resiko"),
                        rs1.getString("asesmen"),
                        rs1.getString("faktor_resiko"),
                        rs1.getString("skala"),
                        rs1.getString("skor")
                    });
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
    
    private void hitungIMT() {
        try {
            double A, B, C, D, Total;
            if (TbbMasuk.getText().equals("")) {
                TbbMasuk.setText("0");
            }

            if (Ttb.getText().equals("")) {
                Ttb.setText("0");
            }
            
            if (TbbMasuk.getText().contains(",") == true) {
                TbbMasuk.setText(TbbMasuk.getText().replaceAll(",", "."));
            }
            
            if (Ttb.getText().contains(",") == true) {
                Ttb.setText(Ttb.getText().replaceAll(",", "."));
            }

            A = Double.parseDouble(TbbMasuk.getText());
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
            
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka BB masuk RS & tinggi badannya,    \n"
                    + "jika menggunakan koma, gantilah tanda koma dengan titik sebagai komanya !!");
        }
    }
    
    private void bersihkanString() {
        skorFix = "";
        resikojatuh = "";
        nip = "";
        kdkamar = "";
        alergiObat = "";
        alergiMakanan = "";
        alergiLain = "";
        gelang = "";
        
        campak = "";
        batukRejan = "";
        sakitTenggorokan = "";
        diare = "";
        sesak = "";
        difteri = "";
        kuning = "";
        lainnya = "";
        tetanus = "";
        demamTifoid = "";
        kejang = "";
        urtikaria = "";
        tbc = "";
        cacing = "";
        
        cekgizi1 = "";
        cekgizi2 = "";
        cekgizi3 = "";
        cekgizi4 = "";
        
        ibadah = "";
        obatObatan = "";
        perawatanLuka = "";
        manajemenNyeri = "";
        diet = "";
        fisio = "";
        hipertermi = "";
        nyeri = "";
        resiko = "";
        kelebihan = "";
        bersihkan = "";
        pola = "";
        gangguan = "";
        cemas = "";
        ketidakseimbangan = "";
        perubahan = "";
        penurunan = "";
        kerusakan = "";
        intoleransi = "";
        kurang = "";
    }
}
