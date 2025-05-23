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
import laporan.DlgHasilPenunjangMedis;
import laporan.DlgPenyakit;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgCariPeriksaRadiologi;
import simrskhanza.DlgNotepad;

/**
 *
 * @author perpustakaan
 */
public final class RMStatusKakiDiabetes extends javax.swing.JDialog {
    private DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7;
    private int i = 0, x = 0;
    private String nip = "", nipDokter = "", traumaMekanik = "", traumaKimia = "", traumaTermis = "", spontan = "", penyebabLain = "",
            tersandung = "", memakaiSepatu = "", tertusuk = "", dllSebutkanMekanik = "", terkenaZat = "", terkenaAirPanas = "", terkenaPemanas = "",
            dllSebutkanTermis = "", mata = "", ginjal = "", penyakitJantung = "", hipertensi = "", strok = "", pad = "", nonUlkus = "", ulkus = "",
            ulkusGangen = "", selulitis = "", derajat0 = "", derajat1 = "", derajat2 = "", derajat3 = "", derajat4 = "", derajat5 = "", ronsenKaki = "",
            surgical = "", chemical = "", biology = "", hidrocol = "", foam = "", allginate = "", silver = "", cadexomer = "", madu = "",
            modernDresingLain = "";
    private String riwObat = "", nmObat = "", riwUlkus = "";

    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMStatusKakiDiabetes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        
        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Jenis Rawat", "Tgl. Masuk", "Lama Rawat", "Tipe Diabetes", "Nama Perawat", "Nama Dokter",
            "jns_rawat", "tb", "bb", "bmi", "tensi", "tgl_masuk", "lama_rawat", "tipe_diabetes", "tipe_diabet_lain", "lama_diketahui", "merokok", "merokok_ya",
            "merokok_mantan", "lama_luka", "satuan_lama_luka", "riwayat_edukasi", "jns_alas_kaki", "jns_alas_kaki_sepatu", "trauma_mekanik", "trauma_kimia", "trauma_termis",
            "spontan", "penyebab_lain", "ket_penyebab_lain", "tersandung", "memakai_sepatu", "tertusuk", "dll_sebutkan_mekanik", "ket_dll_sebutkan_mekanik", "terkena_zat",
            "ket_terkena_zat", "terkena_air_panas", "terkena_pemanas", "dll_sebutkan_termis", "ket_dll_sebutkan_termis", "riwayat_ulkus", "kiri", "jari_kaki_kiri_ke",
            "trans_kiri_tahun", "kanan", "jari_kaki_kanan_ke", "trans_kanan_tahun", "mata", "ginjal", "penyakit_jantung", "hipertensi", "strok", "pad", "riwayat_mata",
            "terapi_mata_tahun", "riwayat_ginjal", "non_ulkus", "ulkus", "ulkus_gangen", "selulitis", "deskripsi_dorsal_kanan", "deskripsi_plantar_kanan", "deskripsi_plantar_kiri",
            "deskripsi_dorsal_kiri", "kulit_kanan_kering", "kulit_kiri_kering", "kulit_kanan_tumit", "kulit_kiri_tumit", "kulit_kanan_bulu", "kulit_kiri_bulu", "kulit_kanan_tinea",
            "kulit_kiri_tinea", "kulit_kanan_kalus", "kulit_kiri_kalus", "kulit_kanan_korn", "kulit_kiri_korn", "kulit_kanan_hiperpig", "kulit_kiri_hiperpig",
            "kulit_kanan_edema", "kulit_kiri_edema", "kulit_kanan_healed", "kulit_kiri_healed", "kuku_kanan_menebal", "kuku_kiri_menebal", "kuku_kanan_infeksi",
            "kuku_kiri_infeksi", "kuku_kanan_perubahan", "kuku_kiri_perubahan", "kuku_kanan_rapuh", "kuku_kiri_rapuh", "kuku_kanan_ingro", "kuku_kiri_ingro",
            "kuku_kanan_atrofi", "kuku_kiri_atrofi", "kuku_kanan_lain", "kuku_kiri_lain", "telapak_kanan_hallux", "telapak_kiri_hallux", "telapak_kanan_pel",
            "telapak_kiri_pel", "telapak_kanan_char", "telapak_kiri_char", "jari_kanan_hammer", "jari_kiri_hammer", "jari_kanan_claw", "jari_kiri_claw",
            "jari_kanan_hiper", "jari_kiri_hiper", "jari_kanan_maser", "jari_kiri_maser", "jari_kanan_lain", "jari_kiri_lain", "ket_jari_kanan_lain", "ket_jari_kiri_lain",
            "dorsalis_kaki_kanan", "dorsalis_kaki_kiri", "tibialis_kaki_kanan", "tibialis_kaki_kiri", "brachialis", "dorsalis_pedis", "skor_abi", "monofilamen_kanan",
            "monofilamen_kiri", "garputala_kanan", "garputala_kiri", "reflex_kanan", "reflex_kiri", "derajat0", "derajat1", "derajat2", "derajat3", "derajat4", "derajat5",
            "pemeriksaan_lab", "ronsen_kaki", "ronsen_kaki_tgl", "kesimpulan_ronsen", "osteomielitis", "lokasi", "kes_ronsen_thorax", "kes_ekg", "usg_dopler", "surgical",
            "chemical", "biology", "hidrocol", "foam", "allginate", "silver", "cadexomer", "madu", "modern_dresing_lain", "ket_modern_dresing_lain", "tgl_data",
            "nip_perawat", "nip_dokter", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbPasien.setModel(tabMode);
        tbPasien.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 163; i++) {
            TableColumn column = tbPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(90);                
            } else if (i == 9) {
                column.setPreferredWidth(220);
            } else if (i == 10) {
                column.setPreferredWidth(220);
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
            } 
        }
        tbPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "no_rawat", "Obat", "Jenis", "Dosis", "Lama", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwPengobatan.setModel(tabMode1);
        tbRiwPengobatan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwPengobatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 6; i++) {
            TableColumn column = tbRiwPengobatan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(77);
            } else if (i == 2) {
                column.setPreferredWidth(265);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(85);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRiwPengobatan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new String[]{
            "no_rawat", "Tahun", "Lokasi", "Penyebab", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwLuka.setModel(tabMode2);
        tbRiwLuka.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwLuka.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 5; i++) {
            TableColumn column = tbRiwLuka.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(280);
            } else if (i == 3) {
                column.setPreferredWidth(300);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRiwLuka.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new String[]{
            "no_rawat", "Lokasi Kelainan", "Kanan", "Kiri", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbDeformitas.setModel(tabMode3);
        tbDeformitas.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDeformitas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 5; i++) {
            TableColumn column = tbDeformitas.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(140);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDeformitas.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode4 = new DefaultTableModel(null, new String[]{
            "no_rawat", "Bakteri", "Sensitif", "Resisten", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbMikro.setModel(tabMode4);
        tbMikro.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbMikro.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 5; i++) {
            TableColumn column = tbMikro.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbMikro.setDefaultRenderer(Object.class, new WarnaTable());
        
        Ttb.setDocument(new batasInput((int) 7).getKata(Ttb));
        Tbb.setDocument(new batasInput((int) 7).getKata(Tbb));
        Tbmi.setDocument(new batasInput((int) 7).getKata(Tbmi));
        Ttensi.setDocument(new batasInput((int) 7).getKata(Ttensi));
        TlamaRawat.setDocument(new batasInput((byte) 3).getOnlyAngka(TlamaRawat));
        TtipeDiabetLain.setDocument(new batasInput((int) 50).getKata(TtipeDiabetLain));
        TlamaDiketahui.setDocument(new batasInput((int) 7).getKata(TlamaDiketahui));
        Tjenis.setDocument(new batasInput((int) 100).getKata(Tjenis));
        Tdosis.setDocument(new batasInput((int) 100).getKata(Tdosis));
        Tlama.setDocument(new batasInput((int) 30).getKata(Tlama));        
        TmerokokYa.setDocument(new batasInput((int) 7).getKata(TmerokokYa));
        TmerokokMantan.setDocument(new batasInput((int) 7).getKata(TmerokokMantan));
        TlamaLuka.setDocument(new batasInput((int) 7).getKata(TlamaLuka));        
        Tsepatu.setDocument(new batasInput((int) 50).getKata(Tsepatu));        
        TdllSebutkanMekanik.setDocument(new batasInput((int) 50).getKata(TdllSebutkanMekanik));
        TterkenaZat.setDocument(new batasInput((int) 200).getKata(TterkenaZat));        
        TdllSebutkanTermis.setDocument(new batasInput((int) 50).getKata(TdllSebutkanTermis));
        TlainSebutkan.setDocument(new batasInput((int) 200).getKata(TlainSebutkan));        
        Ttahun.setDocument(new batasInput((byte) 4).getOnlyAngka(Ttahun));
        Tlokasi.setDocument(new batasInput((int) 150).getKata(Tlokasi));        
        Tpenyebab.setDocument(new batasInput((int) 255).getKata(Tpenyebab));
        TjariKiri.setDocument(new batasInput((byte) 3).getOnlyAngka(TjariKiri));
        TtransKiri.setDocument(new batasInput((byte) 4).getOnlyAngka(TtransKiri));
        TjariKanan.setDocument(new batasInput((byte) 3).getOnlyAngka(TjariKanan));
        TtransKanan.setDocument(new batasInput((byte) 4).getOnlyAngka(TtransKanan));
        TlaserTahun.setDocument(new batasInput((byte) 4).getOnlyAngka(TlaserTahun));        
        TdeforKanan.setDocument(new batasInput((int) 255).getKata(TdeforKanan));
        TdeforKiri.setDocument(new batasInput((int) 255).getKata(TdeforKiri));        
        TketLainKanan.setDocument(new batasInput((int) 200).getKata(TketLainKanan));
        TketLainKiri.setDocument(new batasInput((int) 200).getKata(TketLainKiri));        
        TtdsBra.setDocument(new batasInput((int) 7).getKata(TtdsBra));        
        TtdsDor.setDocument(new batasInput((int) 7).getKata(TtdsDor));        
        TkesRonsen.setDocument(new batasInput((int) 200).getKata(TkesRonsen));
        TlokRonsen.setDocument(new batasInput((int) 200).getKata(TlokRonsen));        
        TlainModern.setDocument(new batasInput((int) 200).getKata(TlainModern));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        
//        if(koneksiDB.cariCepat().equals("aktif")){
//            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
//                @Override
//                public void insertUpdate(DocumentEvent e) {
//                    if(TCari.getText().length()>2){
//                        tampil();
//                    }
//                }
//                @Override
//                public void removeUpdate(DocumentEvent e) {
//                    if(TCari.getText().length()>2){
//                        tampil();
//                    }
//                }
//                @Override
//                public void changedUpdate(DocumentEvent e) {
//                    if(TCari.getText().length()>2){
//                        tampil();
//                    }
//                }
//            });
//        }
        
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMStatusKakiDiabetes")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        nipDokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                        TnmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
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
        MnDokumenJangMed = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        FormData = new widget.InternalFrame();
        ScrollTriase1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel99 = new widget.Label();
        TnmPerawat = new widget.TextBox();
        BtnPerawat = new widget.Button();
        jLabel146 = new widget.Label();
        BtnDokter = new widget.Button();
        TnmDokter = new widget.TextBox();
        jLabel5 = new widget.Label();
        jLabel8 = new widget.Label();
        Tjenkel = new widget.TextBox();
        jLabel9 = new widget.Label();
        Tusia = new widget.TextBox();
        jLabel10 = new widget.Label();
        Tpnd = new widget.TextBox();
        jLabel11 = new widget.Label();
        Talamat = new widget.TextBox();
        jLabel12 = new widget.Label();
        TnoTelp = new widget.TextBox();
        jLabel13 = new widget.Label();
        Ttb = new widget.TextBox();
        jLabel14 = new widget.Label();
        Tbb = new widget.TextBox();
        jLabel15 = new widget.Label();
        Tbmi = new widget.TextBox();
        jLabel16 = new widget.Label();
        Ttensi = new widget.TextBox();
        jLabel17 = new widget.Label();
        Tsuku = new widget.TextBox();
        jLabel18 = new widget.Label();
        TtglMasuk = new widget.Tanggal();
        jLabel20 = new widget.Label();
        TlamaRawat = new widget.TextBox();
        jLabel22 = new widget.Label();
        cmbJnsRawat = new widget.ComboBox();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        jLabel25 = new widget.Label();
        cmbTipeDiabet = new widget.ComboBox();
        TtipeDiabetLain = new widget.TextBox();
        jLabel26 = new widget.Label();
        TlamaDiketahui = new widget.TextBox();
        jLabel27 = new widget.Label();
        jLabel28 = new widget.Label();
        jLabel29 = new widget.Label();
        cmbObat = new widget.ComboBox();
        jLabel30 = new widget.Label();
        Tjenis = new widget.TextBox();
        jLabel31 = new widget.Label();
        Tdosis = new widget.TextBox();
        jLabel32 = new widget.Label();
        Tlama = new widget.TextBox();
        Scroll1 = new widget.ScrollPane();
        tbRiwPengobatan = new widget.Table();
        BtnTambahObat = new widget.Button();
        BtnSimpanObat = new widget.Button();
        BtnHapusObat = new widget.Button();
        BtnGantiObat = new widget.Button();
        jLabel33 = new widget.Label();
        cmbMerokok = new widget.ComboBox();
        jLabel34 = new widget.Label();
        TmerokokYa = new widget.TextBox();
        jLabel35 = new widget.Label();
        TmerokokMantan = new widget.TextBox();
        jLabel36 = new widget.Label();
        jLabel37 = new widget.Label();
        TlamaLuka = new widget.TextBox();
        cmbSatuan = new widget.ComboBox();
        jLabel38 = new widget.Label();
        cmbRiwEdukasi = new widget.ComboBox();
        jLabel39 = new widget.Label();
        cmbJnsAlas = new widget.ComboBox();
        Tsepatu = new widget.TextBox();
        jLabel40 = new widget.Label();
        chkTraumaMekanik = new widget.CekBox();
        chkTersandung = new widget.CekBox();
        chkMemakaiSepatu = new widget.CekBox();
        chkTertusuk = new widget.CekBox();
        chkDllsebutkanMekanik = new widget.CekBox();
        TdllSebutkanMekanik = new widget.TextBox();
        chkTraumaKimia = new widget.CekBox();
        chkTerkenaZat = new widget.CekBox();
        TterkenaZat = new widget.TextBox();
        chkTraumaTermis = new widget.CekBox();
        chkTerkenaAir = new widget.CekBox();
        chkTerkenaPemanas = new widget.CekBox();
        chkDllsebutkanTermis = new widget.CekBox();
        TdllSebutkanTermis = new widget.TextBox();
        chkSpontan = new widget.CekBox();
        chkLainLain = new widget.CekBox();
        TlainSebutkan = new widget.TextBox();
        jLabel41 = new widget.Label();
        cmbRiwUlkus = new widget.ComboBox();
        jLabel42 = new widget.Label();
        Ttahun = new widget.TextBox();
        jLabel43 = new widget.Label();
        Tlokasi = new widget.TextBox();
        jLabel44 = new widget.Label();
        Tpenyebab = new widget.TextBox();
        Scroll2 = new widget.ScrollPane();
        tbRiwLuka = new widget.Table();
        BtnTambahLuka = new widget.Button();
        BtnSimpanLuka = new widget.Button();
        BtnHapusLuka = new widget.Button();
        BtnGantiLuka = new widget.Button();
        jLabel45 = new widget.Label();
        cmbRiwAmputasiKiri = new widget.ComboBox();
        jLabel46 = new widget.Label();
        TjariKiri = new widget.TextBox();
        jLabel47 = new widget.Label();
        TtransKiri = new widget.TextBox();
        cmbRiwAmputasiKanan = new widget.ComboBox();
        jLabel48 = new widget.Label();
        TjariKanan = new widget.TextBox();
        jLabel49 = new widget.Label();
        TtransKanan = new widget.TextBox();
        jLabel50 = new widget.Label();
        BtnBMI = new widget.Button();
        jLabel51 = new widget.Label();
        chkMata = new widget.CekBox();
        chkGinjal = new widget.CekBox();
        chkPenyJantung = new widget.CekBox();
        chkHipertensi = new widget.CekBox();
        chkStrok = new widget.CekBox();
        chkPad = new widget.CekBox();
        cmbMata = new widget.ComboBox();
        jLabel52 = new widget.Label();
        TlaserTahun = new widget.TextBox();
        cmbGinjal = new widget.ComboBox();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        chkNonUlkus = new widget.CekBox();
        chkUlkus = new widget.CekBox();
        chkUlkusGangen = new widget.CekBox();
        chkSelulitis = new widget.CekBox();
        jLabel55 = new widget.Label();
        PanelWall = new usu.widget.glass.PanelGlass();
        jLabel56 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        TdorsalKanan = new widget.TextArea();
        jLabel57 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        TplantarKanan = new widget.TextArea();
        PanelWall1 = new usu.widget.glass.PanelGlass();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        TdorsalKiri = new widget.TextArea();
        PanelWall2 = new usu.widget.glass.PanelGlass();
        jLabel60 = new widget.Label();
        scrollPane17 = new widget.ScrollPane();
        TplantarKiri = new widget.TextArea();
        PanelWall3 = new usu.widget.glass.PanelGlass();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        cmbLokasi = new widget.ComboBox();
        jLabel63 = new widget.Label();
        TdeforKanan = new widget.TextBox();
        jLabel64 = new widget.Label();
        TdeforKiri = new widget.TextBox();
        Scroll3 = new widget.ScrollPane();
        tbDeformitas = new widget.Table();
        BtnTambahDefor = new widget.Button();
        BtnSimpanDefor = new widget.Button();
        BtnHapusDefor = new widget.Button();
        BtnGantiDefor = new widget.Button();
        jLabel65 = new widget.Label();
        jLabel66 = new widget.Label();
        jLabel67 = new widget.Label();
        jLabel68 = new widget.Label();
        jLabel69 = new widget.Label();
        jLabel70 = new widget.Label();
        jLabel71 = new widget.Label();
        jLabel72 = new widget.Label();
        jLabel73 = new widget.Label();
        jLabel74 = new widget.Label();
        jLabel75 = new widget.Label();
        cmbKulKananKering = new widget.ComboBox();
        cmbKulKananTumit = new widget.ComboBox();
        cmbKulKananBulu = new widget.ComboBox();
        cmbKulKananTinea = new widget.ComboBox();
        cmbKulKananKalus = new widget.ComboBox();
        cmbKulKananKorn = new widget.ComboBox();
        cmbKulKananHiper = new widget.ComboBox();
        cmbKulKananEdema = new widget.ComboBox();
        cmbKulKananHealed = new widget.ComboBox();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        cmbKulKiriKering = new widget.ComboBox();
        cmbKulKiriTumit = new widget.ComboBox();
        cmbKulKiriBulu = new widget.ComboBox();
        cmbKulKiriTinea = new widget.ComboBox();
        cmbKulKiriKalus = new widget.ComboBox();
        cmbKulKiriKorn = new widget.ComboBox();
        cmbKulKiriHiper = new widget.ComboBox();
        cmbKulKiriEdema = new widget.ComboBox();
        cmbKulKiriHealed = new widget.ComboBox();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel78 = new widget.Label();
        jLabel79 = new widget.Label();
        jLabel80 = new widget.Label();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        jLabel84 = new widget.Label();
        jLabel85 = new widget.Label();
        jLabel86 = new widget.Label();
        cmbKukKananMenebal = new widget.ComboBox();
        cmbKukKananInfeksi = new widget.ComboBox();
        cmbKukKananPerubahan = new widget.ComboBox();
        cmbKukKananRapuh = new widget.ComboBox();
        cmbKukKananIngro = new widget.ComboBox();
        cmbKukKananAtrofi = new widget.ComboBox();
        cmbKukKananLain = new widget.ComboBox();
        jLabel87 = new widget.Label();
        cmbKukKiriMenebal = new widget.ComboBox();
        cmbKukKiriInfeksi = new widget.ComboBox();
        cmbKukKiriPerubahan = new widget.ComboBox();
        cmbKukKiriRapuh = new widget.ComboBox();
        cmbKukKiriIngro = new widget.ComboBox();
        cmbKukKiriAtrofi = new widget.ComboBox();
        cmbKukKiriLain = new widget.ComboBox();
        jLabel88 = new widget.Label();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        jLabel91 = new widget.Label();
        jLabel92 = new widget.Label();
        jLabel93 = new widget.Label();
        cmbTelKananHallu = new widget.ComboBox();
        cmbTelKiriHallu = new widget.ComboBox();
        cmbTelKananPel = new widget.ComboBox();
        cmbTelKiriPel = new widget.ComboBox();
        cmbTelKananChar = new widget.ComboBox();
        cmbTelKiriChar = new widget.ComboBox();
        jLabel94 = new widget.Label();
        jLabel95 = new widget.Label();
        jLabel96 = new widget.Label();
        jLabel97 = new widget.Label();
        jLabel98 = new widget.Label();
        jLabel100 = new widget.Label();
        jLabel101 = new widget.Label();
        jLabel102 = new widget.Label();
        cmbJarKananHamer = new widget.ComboBox();
        cmbJarKiriHamer = new widget.ComboBox();
        cmbJarKananClaw = new widget.ComboBox();
        cmbJarKiriClaw = new widget.ComboBox();
        cmbJarKananHiper = new widget.ComboBox();
        cmbJarKiriHiper = new widget.ComboBox();
        cmbJarKananMas = new widget.ComboBox();
        cmbJarKiriMas = new widget.ComboBox();
        cmbJarKananLain = new widget.ComboBox();
        cmbJarKiriLain = new widget.ComboBox();
        jLabel103 = new widget.Label();
        TketLainKanan = new widget.TextBox();
        jLabel104 = new widget.Label();
        TketLainKiri = new widget.TextBox();
        jLabel105 = new widget.Label();
        jLabel106 = new widget.Label();
        cmbDorsalisPedKanan = new widget.ComboBox();
        jLabel107 = new widget.Label();
        cmbDorsalisPedKiri = new widget.ComboBox();
        jLabel108 = new widget.Label();
        cmbTibialisKanan = new widget.ComboBox();
        jLabel109 = new widget.Label();
        cmbTibialisKiri = new widget.ComboBox();
        jLabel110 = new widget.Label();
        jLabel111 = new widget.Label();
        jLabel112 = new widget.Label();
        TtdsBra = new widget.TextBox();
        jLabel113 = new widget.Label();
        TtdsDor = new widget.TextBox();
        jLabel114 = new widget.Label();
        TskorAbi = new widget.TextBox();
        jLabel115 = new widget.Label();
        jLabel116 = new widget.Label();
        jLabel117 = new widget.Label();
        jLabel118 = new widget.Label();
        jLabel119 = new widget.Label();
        jLabel120 = new widget.Label();
        jLabel121 = new widget.Label();
        cmbMonoKanan = new widget.ComboBox();
        cmbMonoKiri = new widget.ComboBox();
        cmbGarKanan = new widget.ComboBox();
        cmbGarKiri = new widget.ComboBox();
        cmbRefKanan = new widget.ComboBox();
        cmbRefKiri = new widget.ComboBox();
        jLabel122 = new widget.Label();
        jLabel123 = new widget.Label();
        chkDerajat0 = new widget.CekBox();
        chkDerajat1 = new widget.CekBox();
        chkDerajat2 = new widget.CekBox();
        chkDerajat3 = new widget.CekBox();
        chkDerajat4 = new widget.CekBox();
        chkDerajat5 = new widget.CekBox();
        jLabel124 = new widget.Label();
        jLabel125 = new widget.Label();
        scrollPane18 = new widget.ScrollPane();
        TpemeriksaanLab = new widget.TextArea();
        BtnPasteHasil = new widget.Button();
        jLabel126 = new widget.Label();
        chkTglRonsen = new widget.CekBox();
        TtglRonsen = new widget.Tanggal();
        jLabel127 = new widget.Label();
        TkesRonsen = new widget.TextBox();
        jLabel128 = new widget.Label();
        cmbOsteo = new widget.ComboBox();
        jLabel129 = new widget.Label();
        TlokRonsen = new widget.TextBox();
        jLabel130 = new widget.Label();
        jLabel131 = new widget.Label();
        Tbakteri = new widget.TextBox();
        jLabel132 = new widget.Label();
        Tsensitif = new widget.TextBox();
        jLabel133 = new widget.Label();
        Tresisten = new widget.TextBox();
        Scroll4 = new widget.ScrollPane();
        tbMikro = new widget.Table();
        jLabel134 = new widget.Label();
        BtnTambahMikro = new widget.Button();
        BtnSimpanMikro = new widget.Button();
        BtnHapusMikro = new widget.Button();
        BtnGantiMikro = new widget.Button();
        jLabel135 = new widget.Label();
        TkesRonsenTorax = new widget.TextBox();
        jLabel136 = new widget.Label();
        TkesEkg = new widget.TextBox();
        jLabel137 = new widget.Label();
        TusgDopler = new widget.TextBox();
        jLabel138 = new widget.Label();
        jLabel139 = new widget.Label();
        chkSurgical = new widget.CekBox();
        chkChemical = new widget.CekBox();
        chkBiology = new widget.CekBox();
        jLabel140 = new widget.Label();
        chkHydro = new widget.CekBox();
        chkFoam = new widget.CekBox();
        chkAlgin = new widget.CekBox();
        chkSilver = new widget.CekBox();
        chkCadex = new widget.CekBox();
        chkMadu = new widget.CekBox();
        chkLainModern = new widget.CekBox();
        TlainModern = new widget.TextBox();
        jLabel141 = new widget.Label();
        TtglSimpan = new widget.Tanggal();
        chkSaya = new widget.CekBox();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel142 = new widget.Label();
        jLabel143 = new widget.Label();
        jLabel144 = new widget.Label();
        jLabel145 = new widget.Label();
        internalFrame4 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPasien = new widget.Table();
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

        MnHasilPemeriksaanPenunjang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanPenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanPenunjang.setText("Hasil Pemeriksaan Penunjang");
        MnHasilPemeriksaanPenunjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanPenunjang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanPenunjang.setIconTextGap(5);
        MnHasilPemeriksaanPenunjang.setName("MnHasilPemeriksaanPenunjang"); // NOI18N
        MnHasilPemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(190, 26));
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Status Kaki Diabetes ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        FormData.setBorder(null);
        FormData.setName("FormData"); // NOI18N
        FormData.setLayout(new java.awt.BorderLayout(1, 1));

        ScrollTriase1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        ScrollTriase1.setName("ScrollTriase1"); // NOI18N
        ScrollTriase1.setOpaque(true);
        ScrollTriase1.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBorder(null);
        FormInput.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis..!!");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 3821));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 38, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 38, 122, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(315, 38, 407, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(240, 38, 70, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Nama Perawat : ");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(0, 3758, 150, 23);

        TnmPerawat.setEditable(false);
        TnmPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawat.setToolTipText("Alt+C");
        TnmPerawat.setName("TnmPerawat"); // NOI18N
        TnmPerawat.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TnmPerawat);
        TnmPerawat.setBounds(152, 3758, 430, 23);

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
        BtnPerawat.setBounds(582, 3758, 28, 23);

        jLabel146.setForeground(new java.awt.Color(0, 0, 0));
        jLabel146.setText("Dokter Pemeriksa : ");
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(0, 3786, 150, 23);

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
        BtnDokter.setBounds(582, 3786, 28, 23);

        TnmDokter.setEditable(false);
        TnmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter.setToolTipText("Alt+C");
        TnmDokter.setName("TnmDokter"); // NOI18N
        TnmDokter.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TnmDokter);
        TnmDokter.setBounds(152, 3786, 430, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("I. Data Dasar");
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(30, 10, 150, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Jenis Kelamin : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 66, 110, 23);

        Tjenkel.setEditable(false);
        Tjenkel.setBackground(new java.awt.Color(245, 250, 240));
        Tjenkel.setForeground(new java.awt.Color(0, 0, 0));
        Tjenkel.setName("Tjenkel"); // NOI18N
        FormInput.add(Tjenkel);
        Tjenkel.setBounds(114, 66, 122, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Usia : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(240, 66, 40, 23);

        Tusia.setEditable(false);
        Tusia.setForeground(new java.awt.Color(0, 0, 0));
        Tusia.setName("Tusia"); // NOI18N
        FormInput.add(Tusia);
        Tusia.setBounds(282, 66, 70, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Pendidikan Terakhir : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(354, 66, 120, 23);

        Tpnd.setEditable(false);
        Tpnd.setBackground(new java.awt.Color(245, 250, 240));
        Tpnd.setForeground(new java.awt.Color(0, 0, 0));
        Tpnd.setName("Tpnd"); // NOI18N
        FormInput.add(Tpnd);
        Tpnd.setBounds(477, 66, 245, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Alamat : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 94, 110, 23);

        Talamat.setEditable(false);
        Talamat.setBackground(new java.awt.Color(245, 250, 240));
        Talamat.setForeground(new java.awt.Color(0, 0, 0));
        Talamat.setName("Talamat"); // NOI18N
        FormInput.add(Talamat);
        Talamat.setBounds(112, 94, 610, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("No. Telpn : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 122, 110, 23);

        TnoTelp.setEditable(false);
        TnoTelp.setBackground(new java.awt.Color(245, 250, 240));
        TnoTelp.setForeground(new java.awt.Color(0, 0, 0));
        TnoTelp.setName("TnoTelp"); // NOI18N
        FormInput.add(TnoTelp);
        TnoTelp.setBounds(112, 122, 170, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tinggi Badan :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(280, 122, 80, 23);

        Ttb.setForeground(new java.awt.Color(0, 0, 0));
        Ttb.setName("Ttb"); // NOI18N
        Ttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbKeyPressed(evt);
            }
        });
        FormInput.add(Ttb);
        Ttb.setBounds(365, 122, 50, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Cm.   Berat Badan :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(420, 122, 100, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        FormInput.add(Tbb);
        Tbb.setBounds(520, 122, 50, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Kg.   BMI :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(575, 122, 55, 23);

        Tbmi.setBackground(new java.awt.Color(245, 250, 240));
        Tbmi.setForeground(new java.awt.Color(0, 0, 0));
        Tbmi.setName("Tbmi"); // NOI18N
        Tbmi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbmiKeyPressed(evt);
            }
        });
        FormInput.add(Tbmi);
        Tbmi.setBounds(632, 122, 50, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Tekanan Darah : ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 150, 110, 23);

        Ttensi.setBackground(new java.awt.Color(245, 250, 240));
        Ttensi.setForeground(new java.awt.Color(0, 0, 0));
        Ttensi.setName("Ttensi"); // NOI18N
        Ttensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtensiKeyPressed(evt);
            }
        });
        FormInput.add(Ttensi);
        Ttensi.setBounds(112, 150, 75, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("mmHg     Ras/Suku :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(193, 150, 100, 23);

        Tsuku.setEditable(false);
        Tsuku.setForeground(new java.awt.Color(0, 0, 0));
        Tsuku.setName("Tsuku"); // NOI18N
        FormInput.add(Tsuku);
        Tsuku.setBounds(295, 150, 200, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tgl. Masuk :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(495, 150, 70, 23);

        TtglMasuk.setEditable(false);
        TtglMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-05-2025" }));
        TtglMasuk.setDisplayFormat("dd-MM-yyyy");
        TtglMasuk.setName("TtglMasuk"); // NOI18N
        TtglMasuk.setOpaque(false);
        TtglMasuk.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglMasuk);
        TtglMasuk.setBounds(570, 150, 90, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Lama Dirawat :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(213, 178, 85, 23);

        TlamaRawat.setBackground(new java.awt.Color(245, 250, 240));
        TlamaRawat.setForeground(new java.awt.Color(0, 0, 0));
        TlamaRawat.setName("TlamaRawat"); // NOI18N
        TlamaRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlamaRawatKeyPressed(evt);
            }
        });
        FormInput.add(TlamaRawat);
        TlamaRawat.setBounds(305, 178, 50, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Jenis Rawat : ");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 178, 110, 23);

        cmbJnsRawat.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsRawat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Rawat Inap", "Rawat Jalan" }));
        cmbJnsRawat.setName("cmbJnsRawat"); // NOI18N
        cmbJnsRawat.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbJnsRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJnsRawatActionPerformed(evt);
            }
        });
        FormInput.add(cmbJnsRawat);
        cmbJnsRawat.setBounds(112, 178, 100, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("hari");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(360, 178, 30, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("II. Anamnesis");
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(30, 206, 150, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("1. Tipe Diabetes : ");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 234, 200, 23);

        cmbTipeDiabet.setForeground(new java.awt.Color(0, 0, 0));
        cmbTipeDiabet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tipe 1", "Tipe 2", "Lainnya" }));
        cmbTipeDiabet.setName("cmbTipeDiabet"); // NOI18N
        cmbTipeDiabet.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbTipeDiabet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipeDiabetActionPerformed(evt);
            }
        });
        FormInput.add(cmbTipeDiabet);
        cmbTipeDiabet.setBounds(205, 234, 70, 23);

        TtipeDiabetLain.setBackground(new java.awt.Color(245, 250, 240));
        TtipeDiabetLain.setForeground(new java.awt.Color(0, 0, 0));
        TtipeDiabetLain.setName("TtipeDiabetLain"); // NOI18N
        TtipeDiabetLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtipeDiabetLainKeyPressed(evt);
            }
        });
        FormInput.add(TtipeDiabetLain);
        TtipeDiabetLain.setBounds(282, 234, 440, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("2. Lama Diketahui Diabetes : ");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 262, 200, 23);

        TlamaDiketahui.setBackground(new java.awt.Color(245, 250, 240));
        TlamaDiketahui.setForeground(new java.awt.Color(0, 0, 0));
        TlamaDiketahui.setName("TlamaDiketahui"); // NOI18N
        TlamaDiketahui.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlamaDiketahuiKeyPressed(evt);
            }
        });
        FormInput.add(TlamaDiketahui);
        TlamaDiketahui.setBounds(205, 262, 50, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("tahun (pembulatan ke bawah)");
        jLabel27.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(260, 262, 160, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("3. Riwayat Pengobatan Diabetes : ");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(0, 290, 200, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Obat : ");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(198, 290, 45, 23);

        cmbObat.setForeground(new java.awt.Color(0, 0, 0));
        cmbObat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "OHO", "Insulin", "GLP-1 R.A", "Lain-Lain" }));
        cmbObat.setName("cmbObat"); // NOI18N
        cmbObat.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbObatActionPerformed(evt);
            }
        });
        FormInput.add(cmbObat);
        cmbObat.setBounds(244, 290, 86, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Jenis : ");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(330, 290, 50, 23);

        Tjenis.setBackground(new java.awt.Color(245, 250, 240));
        Tjenis.setForeground(new java.awt.Color(0, 0, 0));
        Tjenis.setName("Tjenis"); // NOI18N
        Tjenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjenisKeyPressed(evt);
            }
        });
        FormInput.add(Tjenis);
        Tjenis.setBounds(382, 290, 340, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Dosis : ");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(330, 318, 50, 23);

        Tdosis.setBackground(new java.awt.Color(245, 250, 240));
        Tdosis.setForeground(new java.awt.Color(0, 0, 0));
        Tdosis.setName("Tdosis"); // NOI18N
        Tdosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdosisKeyPressed(evt);
            }
        });
        FormInput.add(Tdosis);
        Tdosis.setBounds(382, 318, 200, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Lama : ");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(580, 318, 50, 23);

        Tlama.setBackground(new java.awt.Color(245, 250, 240));
        Tlama.setForeground(new java.awt.Color(0, 0, 0));
        Tlama.setName("Tlama"); // NOI18N
        Tlama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlamaKeyPressed(evt);
            }
        });
        FormInput.add(Tlama);
        Tlama.setBounds(632, 318, 90, 23);

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Riwayat Pengobatan Diabates ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setComponentPopupMenu(jPopupMenu1);
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbRiwPengobatan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRiwPengobatan.setComponentPopupMenu(jPopupMenu1);
        tbRiwPengobatan.setName("tbRiwPengobatan"); // NOI18N
        tbRiwPengobatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwPengobatanMouseClicked(evt);
            }
        });
        tbRiwPengobatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwPengobatanKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbRiwPengobatan);

        FormInput.add(Scroll1);
        Scroll1.setBounds(40, 346, 680, 180);

        BtnTambahObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahObat.setText("Tambah");
        BtnTambahObat.setToolTipText("Tambah Riwayat Pengobatan");
        BtnTambahObat.setName("BtnTambahObat"); // NOI18N
        BtnTambahObat.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahObatActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahObat);
        BtnTambahObat.setBounds(725, 346, 90, 30);

        BtnSimpanObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanObat.setText("Simpan");
        BtnSimpanObat.setToolTipText("Simpan Riwayat Pengobatan");
        BtnSimpanObat.setName("BtnSimpanObat"); // NOI18N
        BtnSimpanObat.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnSimpanObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanObatActionPerformed(evt);
            }
        });
        FormInput.add(BtnSimpanObat);
        BtnSimpanObat.setBounds(725, 385, 90, 30);

        BtnHapusObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusObat.setText("Hapus");
        BtnHapusObat.setToolTipText("Hapus Riwayat Pengobatan");
        BtnHapusObat.setName("BtnHapusObat"); // NOI18N
        BtnHapusObat.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusObatActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusObat);
        BtnHapusObat.setBounds(725, 424, 90, 30);

        BtnGantiObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiObat.setText("Ganti");
        BtnGantiObat.setToolTipText("Ganti Riwayat Pengobatan");
        BtnGantiObat.setName("BtnGantiObat"); // NOI18N
        BtnGantiObat.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiObatActionPerformed(evt);
            }
        });
        FormInput.add(BtnGantiObat);
        BtnGantiObat.setBounds(725, 463, 90, 30);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("4. Merokok : ");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(0, 540, 200, 23);

        cmbMerokok.setForeground(new java.awt.Color(0, 0, 0));
        cmbMerokok.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak", "Mantan" }));
        cmbMerokok.setName("cmbMerokok"); // NOI18N
        cmbMerokok.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbMerokok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMerokokActionPerformed(evt);
            }
        });
        FormInput.add(cmbMerokok);
        cmbMerokok.setBounds(205, 540, 70, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Ya : ");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(275, 540, 50, 23);

        TmerokokYa.setBackground(new java.awt.Color(245, 250, 240));
        TmerokokYa.setForeground(new java.awt.Color(0, 0, 0));
        TmerokokYa.setName("TmerokokYa"); // NOI18N
        TmerokokYa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmerokokYaKeyPressed(evt);
            }
        });
        FormInput.add(TmerokokYa);
        TmerokokYa.setBounds(327, 540, 50, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("batang/hari        Mantan : ");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(385, 540, 130, 23);

        TmerokokMantan.setBackground(new java.awt.Color(245, 250, 240));
        TmerokokMantan.setForeground(new java.awt.Color(0, 0, 0));
        TmerokokMantan.setName("TmerokokMantan"); // NOI18N
        TmerokokMantan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmerokokMantanKeyPressed(evt);
            }
        });
        FormInput.add(TmerokokMantan);
        TmerokokMantan.setBounds(516, 540, 50, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("tahun lalu");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(572, 540, 60, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("5. Lama luka : ");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(0, 568, 200, 23);

        TlamaLuka.setBackground(new java.awt.Color(245, 250, 240));
        TlamaLuka.setForeground(new java.awt.Color(0, 0, 0));
        TlamaLuka.setName("TlamaLuka"); // NOI18N
        TlamaLuka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlamaLukaKeyPressed(evt);
            }
        });
        FormInput.add(TlamaLuka);
        TlamaLuka.setBounds(205, 568, 50, 23);

        cmbSatuan.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "hari", "bulan", "tahun" }));
        cmbSatuan.setName("cmbSatuan"); // NOI18N
        cmbSatuan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbSatuan);
        cmbSatuan.setBounds(260, 568, 65, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("6. Riwayat Edukasi Kaki DM : ");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(330, 568, 160, 23);

        cmbRiwEdukasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwEdukasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbRiwEdukasi.setName("cmbRiwEdukasi"); // NOI18N
        cmbRiwEdukasi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbRiwEdukasi);
        cmbRiwEdukasi.setBounds(495, 568, 60, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("7. Jenis Alas Kaki : ");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(0, 596, 200, 23);

        cmbJnsAlas.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsAlas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sandal", "Kets", "Sepatu", "Alas Kaki Khusus", "Tanpa Alas Kaki" }));
        cmbJnsAlas.setName("cmbJnsAlas"); // NOI18N
        cmbJnsAlas.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbJnsAlas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJnsAlasActionPerformed(evt);
            }
        });
        FormInput.add(cmbJnsAlas);
        cmbJnsAlas.setBounds(205, 596, 115, 23);

        Tsepatu.setBackground(new java.awt.Color(245, 250, 240));
        Tsepatu.setForeground(new java.awt.Color(0, 0, 0));
        Tsepatu.setName("Tsepatu"); // NOI18N
        Tsepatu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsepatuKeyPressed(evt);
            }
        });
        FormInput.add(Tsepatu);
        Tsepatu.setBounds(327, 596, 395, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("8. Penyebab : ");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 624, 200, 23);

        chkTraumaMekanik.setBackground(new java.awt.Color(255, 255, 250));
        chkTraumaMekanik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTraumaMekanik.setForeground(new java.awt.Color(0, 0, 0));
        chkTraumaMekanik.setText("1. Trauma Mekanik : ");
        chkTraumaMekanik.setBorderPainted(true);
        chkTraumaMekanik.setBorderPaintedFlat(true);
        chkTraumaMekanik.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTraumaMekanik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTraumaMekanik.setName("chkTraumaMekanik"); // NOI18N
        chkTraumaMekanik.setOpaque(false);
        chkTraumaMekanik.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTraumaMekanik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTraumaMekanikActionPerformed(evt);
            }
        });
        FormInput.add(chkTraumaMekanik);
        chkTraumaMekanik.setBounds(0, 652, 200, 23);

        chkTersandung.setBackground(new java.awt.Color(255, 255, 250));
        chkTersandung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTersandung.setForeground(new java.awt.Color(0, 0, 0));
        chkTersandung.setText("Tersandung");
        chkTersandung.setBorderPainted(true);
        chkTersandung.setBorderPaintedFlat(true);
        chkTersandung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTersandung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTersandung.setName("chkTersandung"); // NOI18N
        chkTersandung.setOpaque(false);
        chkTersandung.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTersandung);
        chkTersandung.setBounds(205, 652, 90, 23);

        chkMemakaiSepatu.setBackground(new java.awt.Color(255, 255, 250));
        chkMemakaiSepatu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMemakaiSepatu.setForeground(new java.awt.Color(0, 0, 0));
        chkMemakaiSepatu.setText("Memakai Sepatu Sempit");
        chkMemakaiSepatu.setBorderPainted(true);
        chkMemakaiSepatu.setBorderPaintedFlat(true);
        chkMemakaiSepatu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMemakaiSepatu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMemakaiSepatu.setName("chkMemakaiSepatu"); // NOI18N
        chkMemakaiSepatu.setOpaque(false);
        chkMemakaiSepatu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMemakaiSepatu);
        chkMemakaiSepatu.setBounds(305, 652, 145, 23);

        chkTertusuk.setBackground(new java.awt.Color(255, 255, 250));
        chkTertusuk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTertusuk.setForeground(new java.awt.Color(0, 0, 0));
        chkTertusuk.setText("Tertusuk paku/duri");
        chkTertusuk.setBorderPainted(true);
        chkTertusuk.setBorderPaintedFlat(true);
        chkTertusuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTertusuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTertusuk.setName("chkTertusuk"); // NOI18N
        chkTertusuk.setOpaque(false);
        chkTertusuk.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTertusuk);
        chkTertusuk.setBounds(460, 652, 120, 23);

        chkDllsebutkanMekanik.setBackground(new java.awt.Color(255, 255, 250));
        chkDllsebutkanMekanik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDllsebutkanMekanik.setForeground(new java.awt.Color(0, 0, 0));
        chkDllsebutkanMekanik.setText("Dll, Sebutkan");
        chkDllsebutkanMekanik.setBorderPainted(true);
        chkDllsebutkanMekanik.setBorderPaintedFlat(true);
        chkDllsebutkanMekanik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDllsebutkanMekanik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDllsebutkanMekanik.setName("chkDllsebutkanMekanik"); // NOI18N
        chkDllsebutkanMekanik.setOpaque(false);
        chkDllsebutkanMekanik.setPreferredSize(new java.awt.Dimension(175, 23));
        chkDllsebutkanMekanik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDllsebutkanMekanikActionPerformed(evt);
            }
        });
        FormInput.add(chkDllsebutkanMekanik);
        chkDllsebutkanMekanik.setBounds(205, 680, 90, 23);

        TdllSebutkanMekanik.setBackground(new java.awt.Color(245, 250, 240));
        TdllSebutkanMekanik.setForeground(new java.awt.Color(0, 0, 0));
        TdllSebutkanMekanik.setName("TdllSebutkanMekanik"); // NOI18N
        TdllSebutkanMekanik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdllSebutkanMekanikKeyPressed(evt);
            }
        });
        FormInput.add(TdllSebutkanMekanik);
        TdllSebutkanMekanik.setBounds(297, 680, 425, 23);

        chkTraumaKimia.setBackground(new java.awt.Color(255, 255, 250));
        chkTraumaKimia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTraumaKimia.setForeground(new java.awt.Color(0, 0, 0));
        chkTraumaKimia.setText("2. Trauma Kimia : ");
        chkTraumaKimia.setBorderPainted(true);
        chkTraumaKimia.setBorderPaintedFlat(true);
        chkTraumaKimia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTraumaKimia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTraumaKimia.setName("chkTraumaKimia"); // NOI18N
        chkTraumaKimia.setOpaque(false);
        chkTraumaKimia.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTraumaKimia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTraumaKimiaActionPerformed(evt);
            }
        });
        FormInput.add(chkTraumaKimia);
        chkTraumaKimia.setBounds(0, 708, 200, 23);

        chkTerkenaZat.setBackground(new java.awt.Color(255, 255, 250));
        chkTerkenaZat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTerkenaZat.setForeground(new java.awt.Color(0, 0, 0));
        chkTerkenaZat.setText("Terkena Zat Kimia, Sebutkan");
        chkTerkenaZat.setBorderPainted(true);
        chkTerkenaZat.setBorderPaintedFlat(true);
        chkTerkenaZat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTerkenaZat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTerkenaZat.setName("chkTerkenaZat"); // NOI18N
        chkTerkenaZat.setOpaque(false);
        chkTerkenaZat.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTerkenaZat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTerkenaZatActionPerformed(evt);
            }
        });
        FormInput.add(chkTerkenaZat);
        chkTerkenaZat.setBounds(205, 708, 165, 23);

        TterkenaZat.setBackground(new java.awt.Color(245, 250, 240));
        TterkenaZat.setForeground(new java.awt.Color(0, 0, 0));
        TterkenaZat.setName("TterkenaZat"); // NOI18N
        TterkenaZat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TterkenaZatKeyPressed(evt);
            }
        });
        FormInput.add(TterkenaZat);
        TterkenaZat.setBounds(372, 708, 350, 23);

        chkTraumaTermis.setBackground(new java.awt.Color(255, 255, 250));
        chkTraumaTermis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTraumaTermis.setForeground(new java.awt.Color(0, 0, 0));
        chkTraumaTermis.setText("3. Trauma Termis : ");
        chkTraumaTermis.setBorderPainted(true);
        chkTraumaTermis.setBorderPaintedFlat(true);
        chkTraumaTermis.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTraumaTermis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTraumaTermis.setName("chkTraumaTermis"); // NOI18N
        chkTraumaTermis.setOpaque(false);
        chkTraumaTermis.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTraumaTermis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTraumaTermisActionPerformed(evt);
            }
        });
        FormInput.add(chkTraumaTermis);
        chkTraumaTermis.setBounds(0, 736, 200, 23);

        chkTerkenaAir.setBackground(new java.awt.Color(255, 255, 250));
        chkTerkenaAir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTerkenaAir.setForeground(new java.awt.Color(0, 0, 0));
        chkTerkenaAir.setText("Terkena Air Panas");
        chkTerkenaAir.setBorderPainted(true);
        chkTerkenaAir.setBorderPaintedFlat(true);
        chkTerkenaAir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTerkenaAir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTerkenaAir.setName("chkTerkenaAir"); // NOI18N
        chkTerkenaAir.setOpaque(false);
        chkTerkenaAir.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTerkenaAir);
        chkTerkenaAir.setBounds(205, 736, 115, 23);

        chkTerkenaPemanas.setBackground(new java.awt.Color(255, 255, 250));
        chkTerkenaPemanas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTerkenaPemanas.setForeground(new java.awt.Color(0, 0, 0));
        chkTerkenaPemanas.setText("Terkena Pemanas Listrik");
        chkTerkenaPemanas.setBorderPainted(true);
        chkTerkenaPemanas.setBorderPaintedFlat(true);
        chkTerkenaPemanas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTerkenaPemanas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTerkenaPemanas.setName("chkTerkenaPemanas"); // NOI18N
        chkTerkenaPemanas.setOpaque(false);
        chkTerkenaPemanas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTerkenaPemanas);
        chkTerkenaPemanas.setBounds(330, 736, 160, 23);

        chkDllsebutkanTermis.setBackground(new java.awt.Color(255, 255, 250));
        chkDllsebutkanTermis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDllsebutkanTermis.setForeground(new java.awt.Color(0, 0, 0));
        chkDllsebutkanTermis.setText("Dll, Sebutkan");
        chkDllsebutkanTermis.setBorderPainted(true);
        chkDllsebutkanTermis.setBorderPaintedFlat(true);
        chkDllsebutkanTermis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDllsebutkanTermis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDllsebutkanTermis.setName("chkDllsebutkanTermis"); // NOI18N
        chkDllsebutkanTermis.setOpaque(false);
        chkDllsebutkanTermis.setPreferredSize(new java.awt.Dimension(175, 23));
        chkDllsebutkanTermis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDllsebutkanTermisActionPerformed(evt);
            }
        });
        FormInput.add(chkDllsebutkanTermis);
        chkDllsebutkanTermis.setBounds(205, 764, 90, 23);

        TdllSebutkanTermis.setBackground(new java.awt.Color(245, 250, 240));
        TdllSebutkanTermis.setForeground(new java.awt.Color(0, 0, 0));
        TdllSebutkanTermis.setName("TdllSebutkanTermis"); // NOI18N
        TdllSebutkanTermis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdllSebutkanTermisKeyPressed(evt);
            }
        });
        FormInput.add(TdllSebutkanTermis);
        TdllSebutkanTermis.setBounds(297, 764, 425, 23);

        chkSpontan.setBackground(new java.awt.Color(255, 255, 250));
        chkSpontan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSpontan.setForeground(new java.awt.Color(0, 0, 0));
        chkSpontan.setText("4. Spontan   ");
        chkSpontan.setBorderPainted(true);
        chkSpontan.setBorderPaintedFlat(true);
        chkSpontan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkSpontan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSpontan.setName("chkSpontan"); // NOI18N
        chkSpontan.setOpaque(false);
        chkSpontan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSpontan);
        chkSpontan.setBounds(0, 792, 200, 23);

        chkLainLain.setBackground(new java.awt.Color(255, 255, 250));
        chkLainLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainLain.setForeground(new java.awt.Color(0, 0, 0));
        chkLainLain.setText("5. Lain-lain, Sebutkan");
        chkLainLain.setBorderPainted(true);
        chkLainLain.setBorderPaintedFlat(true);
        chkLainLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainLain.setName("chkLainLain"); // NOI18N
        chkLainLain.setOpaque(false);
        chkLainLain.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainLainActionPerformed(evt);
            }
        });
        FormInput.add(chkLainLain);
        chkLainLain.setBounds(205, 792, 130, 23);

        TlainSebutkan.setBackground(new java.awt.Color(245, 250, 240));
        TlainSebutkan.setForeground(new java.awt.Color(0, 0, 0));
        TlainSebutkan.setName("TlainSebutkan"); // NOI18N
        TlainSebutkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainSebutkanKeyPressed(evt);
            }
        });
        FormInput.add(TlainSebutkan);
        TlainSebutkan.setBounds(337, 792, 385, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("9. Riwayat Luka/Ulkus : ");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(0, 820, 200, 23);

        cmbRiwUlkus.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwUlkus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbRiwUlkus.setName("cmbRiwUlkus"); // NOI18N
        cmbRiwUlkus.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRiwUlkus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwUlkusActionPerformed(evt);
            }
        });
        FormInput.add(cmbRiwUlkus);
        cmbRiwUlkus.setBounds(205, 820, 60, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Tahun :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(265, 820, 50, 23);

        Ttahun.setBackground(new java.awt.Color(245, 250, 240));
        Ttahun.setForeground(new java.awt.Color(0, 0, 0));
        Ttahun.setName("Ttahun"); // NOI18N
        Ttahun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtahunKeyPressed(evt);
            }
        });
        FormInput.add(Ttahun);
        Ttahun.setBounds(319, 820, 70, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Lokasi :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(390, 820, 50, 23);

        Tlokasi.setBackground(new java.awt.Color(245, 250, 240));
        Tlokasi.setForeground(new java.awt.Color(0, 0, 0));
        Tlokasi.setName("Tlokasi"); // NOI18N
        Tlokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlokasiKeyPressed(evt);
            }
        });
        FormInput.add(Tlokasi);
        Tlokasi.setBounds(447, 820, 275, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Penyebab :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(225, 848, 90, 23);

        Tpenyebab.setBackground(new java.awt.Color(245, 250, 240));
        Tpenyebab.setForeground(new java.awt.Color(0, 0, 0));
        Tpenyebab.setName("Tpenyebab"); // NOI18N
        Tpenyebab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpenyebabKeyPressed(evt);
            }
        });
        FormInput.add(Tpenyebab);
        Tpenyebab.setBounds(319, 848, 403, 23);

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Riwayat Luka/Ulkus ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll2.setComponentPopupMenu(jPopupMenu1);
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbRiwLuka.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRiwLuka.setComponentPopupMenu(jPopupMenu1);
        tbRiwLuka.setName("tbRiwLuka"); // NOI18N
        tbRiwLuka.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwLukaMouseClicked(evt);
            }
        });
        tbRiwLuka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwLukaKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbRiwLuka);

        FormInput.add(Scroll2);
        Scroll2.setBounds(40, 876, 680, 170);

        BtnTambahLuka.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahLuka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahLuka.setText("Tambah");
        BtnTambahLuka.setToolTipText("Tambah Riwayat Luka/Ulkus");
        BtnTambahLuka.setName("BtnTambahLuka"); // NOI18N
        BtnTambahLuka.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahLuka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahLukaActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahLuka);
        BtnTambahLuka.setBounds(725, 876, 90, 30);

        BtnSimpanLuka.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanLuka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanLuka.setText("Simpan");
        BtnSimpanLuka.setToolTipText("Simpan Riwayat Luka/Ulkus");
        BtnSimpanLuka.setName("BtnSimpanLuka"); // NOI18N
        BtnSimpanLuka.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnSimpanLuka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanLukaActionPerformed(evt);
            }
        });
        FormInput.add(BtnSimpanLuka);
        BtnSimpanLuka.setBounds(725, 915, 90, 30);

        BtnHapusLuka.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusLuka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusLuka.setText("Hapus");
        BtnHapusLuka.setToolTipText("Hapus Riwayat Luka/Ulkus");
        BtnHapusLuka.setName("BtnHapusLuka"); // NOI18N
        BtnHapusLuka.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusLuka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusLukaActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusLuka);
        BtnHapusLuka.setBounds(725, 954, 90, 30);

        BtnGantiLuka.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiLuka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiLuka.setText("Ganti");
        BtnGantiLuka.setToolTipText("Ganti Riwayat Luka/Ulkus");
        BtnGantiLuka.setName("BtnGantiLuka"); // NOI18N
        BtnGantiLuka.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiLuka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiLukaActionPerformed(evt);
            }
        });
        FormInput.add(BtnGantiLuka);
        BtnGantiLuka.setBounds(725, 993, 90, 30);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("10. Riwayat Amputasi : ");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(0, 1058, 150, 23);

        cmbRiwAmputasiKiri.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwAmputasiKiri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "dibawah lutut", "atas lutut", "jari kaki ke", "Transmetatarsal, tahun" }));
        cmbRiwAmputasiKiri.setName("cmbRiwAmputasiKiri"); // NOI18N
        cmbRiwAmputasiKiri.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRiwAmputasiKiri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwAmputasiKiriActionPerformed(evt);
            }
        });
        FormInput.add(cmbRiwAmputasiKiri);
        cmbRiwAmputasiKiri.setBounds(205, 1058, 145, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Jari Kaki Ke :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(350, 1058, 80, 23);

        TjariKiri.setBackground(new java.awt.Color(245, 250, 240));
        TjariKiri.setForeground(new java.awt.Color(0, 0, 0));
        TjariKiri.setName("TjariKiri"); // NOI18N
        TjariKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjariKiriKeyPressed(evt);
            }
        });
        FormInput.add(TjariKiri);
        TjariKiri.setBounds(436, 1058, 50, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Transmetatarsal, tahun : ");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(490, 1058, 130, 23);

        TtransKiri.setBackground(new java.awt.Color(245, 250, 240));
        TtransKiri.setForeground(new java.awt.Color(0, 0, 0));
        TtransKiri.setName("TtransKiri"); // NOI18N
        TtransKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtransKiriKeyPressed(evt);
            }
        });
        FormInput.add(TtransKiri);
        TtransKiri.setBounds(623, 1058, 50, 23);

        cmbRiwAmputasiKanan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwAmputasiKanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "dibawah lutut", "atas lutut", "jari kaki ke", "Transmetatarsal, tahun" }));
        cmbRiwAmputasiKanan.setName("cmbRiwAmputasiKanan"); // NOI18N
        cmbRiwAmputasiKanan.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRiwAmputasiKanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwAmputasiKananActionPerformed(evt);
            }
        });
        FormInput.add(cmbRiwAmputasiKanan);
        cmbRiwAmputasiKanan.setBounds(205, 1086, 145, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Jari Kaki Ke :");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(350, 1086, 80, 23);

        TjariKanan.setBackground(new java.awt.Color(245, 250, 240));
        TjariKanan.setForeground(new java.awt.Color(0, 0, 0));
        TjariKanan.setName("TjariKanan"); // NOI18N
        TjariKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjariKananKeyPressed(evt);
            }
        });
        FormInput.add(TjariKanan);
        TjariKanan.setBounds(436, 1086, 50, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Transmetatarsal, tahun : ");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(490, 1086, 130, 23);

        TtransKanan.setBackground(new java.awt.Color(245, 250, 240));
        TtransKanan.setForeground(new java.awt.Color(0, 0, 0));
        TtransKanan.setName("TtransKanan"); // NOI18N
        TtransKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtransKananKeyPressed(evt);
            }
        });
        FormInput.add(TtransKanan);
        TtransKanan.setBounds(623, 1086, 50, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("III. Riwayat Komplikasi/Penyakit Penyerta");
        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(30, 1114, 290, 23);

        BtnBMI.setForeground(new java.awt.Color(0, 0, 0));
        BtnBMI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/satuan.png"))); // NOI18N
        BtnBMI.setText("Hitung BMI");
        BtnBMI.setName("BtnBMI"); // NOI18N
        BtnBMI.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnBMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBMIActionPerformed(evt);
            }
        });
        BtnBMI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBMIKeyPressed(evt);
            }
        });
        FormInput.add(BtnBMI);
        BtnBMI.setBounds(725, 122, 110, 30);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("kg/m²");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(687, 122, 35, 23);

        chkMata.setBackground(new java.awt.Color(255, 255, 250));
        chkMata.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMata.setForeground(new java.awt.Color(0, 0, 0));
        chkMata.setText("Mata : ");
        chkMata.setBorderPainted(true);
        chkMata.setBorderPaintedFlat(true);
        chkMata.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkMata.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMata.setName("chkMata"); // NOI18N
        chkMata.setOpaque(false);
        chkMata.setPreferredSize(new java.awt.Dimension(175, 23));
        chkMata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMataActionPerformed(evt);
            }
        });
        FormInput.add(chkMata);
        chkMata.setBounds(0, 1142, 200, 23);

        chkGinjal.setBackground(new java.awt.Color(255, 255, 250));
        chkGinjal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGinjal.setForeground(new java.awt.Color(0, 0, 0));
        chkGinjal.setText("Ginjal : ");
        chkGinjal.setBorderPainted(true);
        chkGinjal.setBorderPaintedFlat(true);
        chkGinjal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkGinjal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGinjal.setName("chkGinjal"); // NOI18N
        chkGinjal.setOpaque(false);
        chkGinjal.setPreferredSize(new java.awt.Dimension(175, 23));
        chkGinjal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkGinjalActionPerformed(evt);
            }
        });
        FormInput.add(chkGinjal);
        chkGinjal.setBounds(0, 1170, 200, 23);

        chkPenyJantung.setBackground(new java.awt.Color(255, 255, 250));
        chkPenyJantung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPenyJantung.setForeground(new java.awt.Color(0, 0, 0));
        chkPenyJantung.setText("Penyakit Jantung Koroner");
        chkPenyJantung.setBorderPainted(true);
        chkPenyJantung.setBorderPaintedFlat(true);
        chkPenyJantung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPenyJantung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPenyJantung.setName("chkPenyJantung"); // NOI18N
        chkPenyJantung.setOpaque(false);
        chkPenyJantung.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPenyJantung);
        chkPenyJantung.setBounds(320, 1170, 150, 23);

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
        chkHipertensi.setBounds(480, 1170, 80, 23);

        chkStrok.setBackground(new java.awt.Color(255, 255, 250));
        chkStrok.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkStrok.setForeground(new java.awt.Color(0, 0, 0));
        chkStrok.setText("Stroke");
        chkStrok.setBorderPainted(true);
        chkStrok.setBorderPaintedFlat(true);
        chkStrok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkStrok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkStrok.setName("chkStrok"); // NOI18N
        chkStrok.setOpaque(false);
        chkStrok.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkStrok);
        chkStrok.setBounds(567, 1170, 60, 23);

        chkPad.setBackground(new java.awt.Color(255, 255, 250));
        chkPad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPad.setForeground(new java.awt.Color(0, 0, 0));
        chkPad.setText("PAD");
        chkPad.setBorderPainted(true);
        chkPad.setBorderPaintedFlat(true);
        chkPad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPad.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPad.setName("chkPad"); // NOI18N
        chkPad.setOpaque(false);
        chkPad.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPad);
        chkPad.setBounds(637, 1170, 50, 23);

        cmbMata.setForeground(new java.awt.Color(0, 0, 0));
        cmbMata.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "NPDR", "PDR", "Terapi laser tahun", "Kebutaan", "Tidak ada data" }));
        cmbMata.setName("cmbMata"); // NOI18N
        cmbMata.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbMata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMataActionPerformed(evt);
            }
        });
        FormInput.add(cmbMata);
        cmbMata.setBounds(205, 1142, 120, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Terapi laser tahun :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(325, 1142, 110, 23);

        TlaserTahun.setBackground(new java.awt.Color(245, 250, 240));
        TlaserTahun.setForeground(new java.awt.Color(0, 0, 0));
        TlaserTahun.setName("TlaserTahun"); // NOI18N
        TlaserTahun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlaserTahunKeyPressed(evt);
            }
        });
        FormInput.add(TlaserTahun);
        TlaserTahun.setBounds(440, 1142, 50, 23);

        cmbGinjal.setForeground(new java.awt.Color(0, 0, 0));
        cmbGinjal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Nefropati DM", "Gagal Ginjal", "Hemodialisis", "Tidak ada data" }));
        cmbGinjal.setName("cmbGinjal"); // NOI18N
        cmbGinjal.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbGinjal);
        cmbGinjal.setBounds(205, 1170, 105, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("IV. Pemeriksaan Fisik");
        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(30, 1198, 290, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("a. Jenis Luka : ");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(0, 1226, 110, 23);

        chkNonUlkus.setBackground(new java.awt.Color(255, 255, 250));
        chkNonUlkus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNonUlkus.setForeground(new java.awt.Color(0, 0, 0));
        chkNonUlkus.setText("Non-Ulkus");
        chkNonUlkus.setBorderPainted(true);
        chkNonUlkus.setBorderPaintedFlat(true);
        chkNonUlkus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNonUlkus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNonUlkus.setName("chkNonUlkus"); // NOI18N
        chkNonUlkus.setOpaque(false);
        chkNonUlkus.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNonUlkus);
        chkNonUlkus.setBounds(112, 1226, 80, 23);

        chkUlkus.setBackground(new java.awt.Color(255, 255, 250));
        chkUlkus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkUlkus.setForeground(new java.awt.Color(0, 0, 0));
        chkUlkus.setText("Ulkus");
        chkUlkus.setBorderPainted(true);
        chkUlkus.setBorderPaintedFlat(true);
        chkUlkus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkUlkus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkUlkus.setName("chkUlkus"); // NOI18N
        chkUlkus.setOpaque(false);
        chkUlkus.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkUlkus);
        chkUlkus.setBounds(200, 1226, 55, 23);

        chkUlkusGangen.setBackground(new java.awt.Color(255, 255, 250));
        chkUlkusGangen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkUlkusGangen.setForeground(new java.awt.Color(0, 0, 0));
        chkUlkusGangen.setText("Ulkus & Gangen");
        chkUlkusGangen.setBorderPainted(true);
        chkUlkusGangen.setBorderPaintedFlat(true);
        chkUlkusGangen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkUlkusGangen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkUlkusGangen.setName("chkUlkusGangen"); // NOI18N
        chkUlkusGangen.setOpaque(false);
        chkUlkusGangen.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkUlkusGangen);
        chkUlkusGangen.setBounds(265, 1226, 105, 23);

        chkSelulitis.setBackground(new java.awt.Color(255, 255, 250));
        chkSelulitis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSelulitis.setForeground(new java.awt.Color(0, 0, 0));
        chkSelulitis.setText("Selulitis");
        chkSelulitis.setBorderPainted(true);
        chkSelulitis.setBorderPaintedFlat(true);
        chkSelulitis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSelulitis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSelulitis.setName("chkSelulitis"); // NOI18N
        chkSelulitis.setOpaque(false);
        chkSelulitis.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSelulitis);
        chkSelulitis.setBounds(380, 1226, 70, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Kanan : ");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(0, 1254, 110, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/dorsal_kanan.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(112, 1282, 110, 140);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Deskripsi / Penjelasan Dorsal Kanan : ");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(230, 1254, 190, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        TdorsalKanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TdorsalKanan.setColumns(20);
        TdorsalKanan.setRows(5);
        TdorsalKanan.setName("TdorsalKanan"); // NOI18N
        TdorsalKanan.setPreferredSize(new java.awt.Dimension(162, 2000));
        TdorsalKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdorsalKananKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(TdorsalKanan);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(230, 1282, 490, 140);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Deskripsi / Penjelasan Plantar Kanan : ");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(230, 1427, 200, 23);

        scrollPane15.setName("scrollPane15"); // NOI18N

        TplantarKanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TplantarKanan.setColumns(20);
        TplantarKanan.setRows(5);
        TplantarKanan.setName("TplantarKanan"); // NOI18N
        TplantarKanan.setPreferredSize(new java.awt.Dimension(162, 2000));
        TplantarKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TplantarKananKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(TplantarKanan);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(230, 1455, 490, 140);

        PanelWall1.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/plantar_kanan.png"))); // NOI18N
        PanelWall1.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall1.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall1.setRound(false);
        PanelWall1.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall1.setLayout(null);
        FormInput.add(PanelWall1);
        PanelWall1.setBounds(112, 1455, 110, 140);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Kiri : ");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(0, 1600, 110, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Deskripsi / Penjelasan Dorsal Kiri : ");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(230, 1600, 190, 23);

        scrollPane16.setName("scrollPane16"); // NOI18N

        TdorsalKiri.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TdorsalKiri.setColumns(20);
        TdorsalKiri.setRows(5);
        TdorsalKiri.setName("TdorsalKiri"); // NOI18N
        TdorsalKiri.setPreferredSize(new java.awt.Dimension(162, 2000));
        TdorsalKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdorsalKiriKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(TdorsalKiri);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(230, 1628, 490, 140);

        PanelWall2.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall2.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/dorsal_kiri.png"))); // NOI18N
        PanelWall2.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall2.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall2.setRound(false);
        PanelWall2.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall2.setLayout(null);
        FormInput.add(PanelWall2);
        PanelWall2.setBounds(112, 1628, 110, 140);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Deskripsi / Penjelasan Plantar Kiri : ");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(230, 1773, 190, 23);

        scrollPane17.setName("scrollPane17"); // NOI18N

        TplantarKiri.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TplantarKiri.setColumns(20);
        TplantarKiri.setRows(5);
        TplantarKiri.setName("TplantarKiri"); // NOI18N
        TplantarKiri.setPreferredSize(new java.awt.Dimension(162, 2000));
        TplantarKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TplantarKiriKeyPressed(evt);
            }
        });
        scrollPane17.setViewportView(TplantarKiri);

        FormInput.add(scrollPane17);
        scrollPane17.setBounds(230, 1801, 490, 140);

        PanelWall3.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall3.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/plantar_kiri.png"))); // NOI18N
        PanelWall3.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall3.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall3.setRound(false);
        PanelWall3.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall3.setLayout(null);
        FormInput.add(PanelWall3);
        PanelWall3.setBounds(112, 1801, 110, 140);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("b. Deformitas : ");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(0, 1947, 110, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Lokasi Kelainan : ");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(112, 1947, 100, 23);

        cmbLokasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbLokasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Plantar Jari Kaki", "Plantar MTP", "Plantar midfoot", "Sheel", "Maleolus Lateralis", "Dorsum Pedis", "Kuku" }));
        cmbLokasi.setName("cmbLokasi"); // NOI18N
        cmbLokasi.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbLokasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLokasiActionPerformed(evt);
            }
        });
        FormInput.add(cmbLokasi);
        cmbLokasi.setBounds(215, 1947, 120, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Kanan : ");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(112, 1975, 100, 23);

        TdeforKanan.setBackground(new java.awt.Color(245, 250, 240));
        TdeforKanan.setForeground(new java.awt.Color(0, 0, 0));
        TdeforKanan.setName("TdeforKanan"); // NOI18N
        TdeforKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdeforKananKeyPressed(evt);
            }
        });
        FormInput.add(TdeforKanan);
        TdeforKanan.setBounds(215, 1975, 507, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Kiri : ");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(112, 2003, 100, 23);

        TdeforKiri.setBackground(new java.awt.Color(245, 250, 240));
        TdeforKiri.setForeground(new java.awt.Color(0, 0, 0));
        TdeforKiri.setName("TdeforKiri"); // NOI18N
        TdeforKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdeforKiriKeyPressed(evt);
            }
        });
        FormInput.add(TdeforKiri);
        TdeforKiri.setBounds(215, 2003, 507, 23);

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Deformitas ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll3.setComponentPopupMenu(jPopupMenu1);
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbDeformitas.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDeformitas.setComponentPopupMenu(jPopupMenu1);
        tbDeformitas.setName("tbDeformitas"); // NOI18N
        tbDeformitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDeformitasMouseClicked(evt);
            }
        });
        tbDeformitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDeformitasKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbDeformitas);

        FormInput.add(Scroll3);
        Scroll3.setBounds(40, 2031, 680, 150);

        BtnTambahDefor.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahDefor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahDefor.setText("Tambah");
        BtnTambahDefor.setToolTipText("Tambah Deformitas");
        BtnTambahDefor.setName("BtnTambahDefor"); // NOI18N
        BtnTambahDefor.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahDefor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahDeforActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahDefor);
        BtnTambahDefor.setBounds(725, 2031, 90, 30);

        BtnSimpanDefor.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanDefor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanDefor.setText("Simpan");
        BtnSimpanDefor.setToolTipText("Simpan Deformitas");
        BtnSimpanDefor.setName("BtnSimpanDefor"); // NOI18N
        BtnSimpanDefor.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnSimpanDefor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanDeforActionPerformed(evt);
            }
        });
        FormInput.add(BtnSimpanDefor);
        BtnSimpanDefor.setBounds(725, 2070, 90, 30);

        BtnHapusDefor.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusDefor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusDefor.setText("Hapus");
        BtnHapusDefor.setToolTipText("Hapus Deformitas");
        BtnHapusDefor.setName("BtnHapusDefor"); // NOI18N
        BtnHapusDefor.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusDefor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusDeforActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusDefor);
        BtnHapusDefor.setBounds(725, 2109, 90, 30);

        BtnGantiDefor.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiDefor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiDefor.setText("Ganti");
        BtnGantiDefor.setToolTipText("Ganti Deformitas");
        BtnGantiDefor.setName("BtnGantiDefor"); // NOI18N
        BtnGantiDefor.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiDefor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiDeforActionPerformed(evt);
            }
        });
        FormInput.add(BtnGantiDefor);
        BtnGantiDefor.setBounds(725, 2148, 90, 30);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("c. Inspeksi Kaki : ");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 2185, 110, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Kulit Kaki : ");
        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 2213, 200, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Kering/bersisik : ");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 2241, 200, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Tumit pecah-pecah : ");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 2269, 200, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Bulu rambut menipis : ");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 2297, 200, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Tinea pedis : ");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 2325, 200, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Kalus : ");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 2353, 200, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Korn : ");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 2381, 200, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Hiperpigmentasi : ");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 2409, 200, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Edema : ");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 2437, 200, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Healed Ulcer : ");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 2465, 200, 23);

        cmbKulKananKering.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKananKering.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKananKering.setName("cmbKulKananKering"); // NOI18N
        cmbKulKananKering.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKananKering);
        cmbKulKananKering.setBounds(205, 2241, 60, 23);

        cmbKulKananTumit.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKananTumit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKananTumit.setName("cmbKulKananTumit"); // NOI18N
        cmbKulKananTumit.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKananTumit);
        cmbKulKananTumit.setBounds(205, 2269, 60, 23);

        cmbKulKananBulu.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKananBulu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKananBulu.setName("cmbKulKananBulu"); // NOI18N
        cmbKulKananBulu.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKananBulu);
        cmbKulKananBulu.setBounds(205, 2297, 60, 23);

        cmbKulKananTinea.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKananTinea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKananTinea.setName("cmbKulKananTinea"); // NOI18N
        cmbKulKananTinea.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKananTinea);
        cmbKulKananTinea.setBounds(205, 2325, 60, 23);

        cmbKulKananKalus.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKananKalus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKananKalus.setName("cmbKulKananKalus"); // NOI18N
        cmbKulKananKalus.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKananKalus);
        cmbKulKananKalus.setBounds(205, 2353, 60, 23);

        cmbKulKananKorn.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKananKorn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKananKorn.setName("cmbKulKananKorn"); // NOI18N
        cmbKulKananKorn.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKananKorn);
        cmbKulKananKorn.setBounds(205, 2381, 60, 23);

        cmbKulKananHiper.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKananHiper.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKananHiper.setName("cmbKulKananHiper"); // NOI18N
        cmbKulKananHiper.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKananHiper);
        cmbKulKananHiper.setBounds(205, 2409, 60, 23);

        cmbKulKananEdema.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKananEdema.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKananEdema.setName("cmbKulKananEdema"); // NOI18N
        cmbKulKananEdema.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKananEdema);
        cmbKulKananEdema.setBounds(205, 2437, 60, 23);

        cmbKulKananHealed.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKananHealed.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKananHealed.setName("cmbKulKananHealed"); // NOI18N
        cmbKulKananHealed.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKananHealed);
        cmbKulKananHealed.setBounds(205, 2465, 60, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("KANAN");
        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(205, 2213, 60, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("KIRI");
        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(280, 2213, 60, 23);

        cmbKulKiriKering.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKiriKering.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKiriKering.setName("cmbKulKiriKering"); // NOI18N
        cmbKulKiriKering.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKiriKering);
        cmbKulKiriKering.setBounds(280, 2241, 60, 23);

        cmbKulKiriTumit.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKiriTumit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKiriTumit.setName("cmbKulKiriTumit"); // NOI18N
        cmbKulKiriTumit.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKiriTumit);
        cmbKulKiriTumit.setBounds(280, 2269, 60, 23);

        cmbKulKiriBulu.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKiriBulu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKiriBulu.setName("cmbKulKiriBulu"); // NOI18N
        cmbKulKiriBulu.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKiriBulu);
        cmbKulKiriBulu.setBounds(280, 2297, 60, 23);

        cmbKulKiriTinea.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKiriTinea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKiriTinea.setName("cmbKulKiriTinea"); // NOI18N
        cmbKulKiriTinea.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKiriTinea);
        cmbKulKiriTinea.setBounds(280, 2325, 60, 23);

        cmbKulKiriKalus.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKiriKalus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKiriKalus.setName("cmbKulKiriKalus"); // NOI18N
        cmbKulKiriKalus.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKiriKalus);
        cmbKulKiriKalus.setBounds(280, 2353, 60, 23);

        cmbKulKiriKorn.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKiriKorn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKiriKorn.setName("cmbKulKiriKorn"); // NOI18N
        cmbKulKiriKorn.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKiriKorn);
        cmbKulKiriKorn.setBounds(280, 2381, 60, 23);

        cmbKulKiriHiper.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKiriHiper.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKiriHiper.setName("cmbKulKiriHiper"); // NOI18N
        cmbKulKiriHiper.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKiriHiper);
        cmbKulKiriHiper.setBounds(280, 2409, 60, 23);

        cmbKulKiriEdema.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKiriEdema.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKiriEdema.setName("cmbKulKiriEdema"); // NOI18N
        cmbKulKiriEdema.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKiriEdema);
        cmbKulKiriEdema.setBounds(280, 2437, 60, 23);

        cmbKulKiriHealed.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulKiriHealed.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKulKiriHealed.setName("cmbKulKiriHealed"); // NOI18N
        cmbKulKiriHealed.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKulKiriHealed);
        cmbKulKiriHealed.setBounds(280, 2465, 60, 23);

        jSeparator16.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator16.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator16.setName("jSeparator16"); // NOI18N
        FormInput.add(jSeparator16);
        jSeparator16.setBounds(350, 2213, 1, 455);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Kuku Kaki : ");
        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(355, 2213, 120, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Menebal : ");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(355, 2241, 120, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Infeksi : ");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(355, 2269, 120, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Perubahan Warna : ");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(355, 2297, 120, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Rapuh : ");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(355, 2325, 120, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Ingrowing nail : ");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(355, 2353, 120, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Atrofi : ");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(355, 2381, 120, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Lain-lain : ");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(355, 2409, 120, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("KANAN");
        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(479, 2213, 60, 23);

        cmbKukKananMenebal.setForeground(new java.awt.Color(0, 0, 0));
        cmbKukKananMenebal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKukKananMenebal.setName("cmbKukKananMenebal"); // NOI18N
        cmbKukKananMenebal.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKukKananMenebal);
        cmbKukKananMenebal.setBounds(479, 2241, 60, 23);

        cmbKukKananInfeksi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKukKananInfeksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKukKananInfeksi.setName("cmbKukKananInfeksi"); // NOI18N
        cmbKukKananInfeksi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKukKananInfeksi);
        cmbKukKananInfeksi.setBounds(479, 2269, 60, 23);

        cmbKukKananPerubahan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKukKananPerubahan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKukKananPerubahan.setName("cmbKukKananPerubahan"); // NOI18N
        cmbKukKananPerubahan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKukKananPerubahan);
        cmbKukKananPerubahan.setBounds(479, 2297, 60, 23);

        cmbKukKananRapuh.setForeground(new java.awt.Color(0, 0, 0));
        cmbKukKananRapuh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKukKananRapuh.setName("cmbKukKananRapuh"); // NOI18N
        cmbKukKananRapuh.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKukKananRapuh);
        cmbKukKananRapuh.setBounds(479, 2325, 60, 23);

        cmbKukKananIngro.setForeground(new java.awt.Color(0, 0, 0));
        cmbKukKananIngro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKukKananIngro.setName("cmbKukKananIngro"); // NOI18N
        cmbKukKananIngro.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKukKananIngro);
        cmbKukKananIngro.setBounds(479, 2353, 60, 23);

        cmbKukKananAtrofi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKukKananAtrofi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKukKananAtrofi.setName("cmbKukKananAtrofi"); // NOI18N
        cmbKukKananAtrofi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKukKananAtrofi);
        cmbKukKananAtrofi.setBounds(479, 2381, 60, 23);

        cmbKukKananLain.setForeground(new java.awt.Color(0, 0, 0));
        cmbKukKananLain.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKukKananLain.setName("cmbKukKananLain"); // NOI18N
        cmbKukKananLain.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKukKananLain);
        cmbKukKananLain.setBounds(479, 2409, 60, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("KIRI");
        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(550, 2213, 60, 23);

        cmbKukKiriMenebal.setForeground(new java.awt.Color(0, 0, 0));
        cmbKukKiriMenebal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKukKiriMenebal.setName("cmbKukKiriMenebal"); // NOI18N
        cmbKukKiriMenebal.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKukKiriMenebal);
        cmbKukKiriMenebal.setBounds(550, 2241, 60, 23);

        cmbKukKiriInfeksi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKukKiriInfeksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKukKiriInfeksi.setName("cmbKukKiriInfeksi"); // NOI18N
        cmbKukKiriInfeksi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKukKiriInfeksi);
        cmbKukKiriInfeksi.setBounds(550, 2269, 60, 23);

        cmbKukKiriPerubahan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKukKiriPerubahan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKukKiriPerubahan.setName("cmbKukKiriPerubahan"); // NOI18N
        cmbKukKiriPerubahan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKukKiriPerubahan);
        cmbKukKiriPerubahan.setBounds(550, 2297, 60, 23);

        cmbKukKiriRapuh.setForeground(new java.awt.Color(0, 0, 0));
        cmbKukKiriRapuh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKukKiriRapuh.setName("cmbKukKiriRapuh"); // NOI18N
        cmbKukKiriRapuh.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKukKiriRapuh);
        cmbKukKiriRapuh.setBounds(550, 2325, 60, 23);

        cmbKukKiriIngro.setForeground(new java.awt.Color(0, 0, 0));
        cmbKukKiriIngro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKukKiriIngro.setName("cmbKukKiriIngro"); // NOI18N
        cmbKukKiriIngro.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKukKiriIngro);
        cmbKukKiriIngro.setBounds(550, 2353, 60, 23);

        cmbKukKiriAtrofi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKukKiriAtrofi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKukKiriAtrofi.setName("cmbKukKiriAtrofi"); // NOI18N
        cmbKukKiriAtrofi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKukKiriAtrofi);
        cmbKukKiriAtrofi.setBounds(550, 2381, 60, 23);

        cmbKukKiriLain.setForeground(new java.awt.Color(0, 0, 0));
        cmbKukKiriLain.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKukKiriLain.setName("cmbKukKiriLain"); // NOI18N
        cmbKukKiriLain.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKukKiriLain);
        cmbKukKiriLain.setBounds(550, 2409, 60, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Telapak Kaki : ");
        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(0, 2503, 200, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel89.setText("KANAN");
        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(205, 2503, 60, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("KIRI");
        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(280, 2503, 60, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Hallux toe : ");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(0, 2531, 200, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Pel Planus : ");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(0, 2559, 200, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Charcot foot : ");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(0, 2587, 200, 23);

        cmbTelKananHallu.setForeground(new java.awt.Color(0, 0, 0));
        cmbTelKananHallu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbTelKananHallu.setName("cmbTelKananHallu"); // NOI18N
        cmbTelKananHallu.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbTelKananHallu);
        cmbTelKananHallu.setBounds(205, 2531, 60, 23);

        cmbTelKiriHallu.setForeground(new java.awt.Color(0, 0, 0));
        cmbTelKiriHallu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbTelKiriHallu.setName("cmbTelKiriHallu"); // NOI18N
        cmbTelKiriHallu.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbTelKiriHallu);
        cmbTelKiriHallu.setBounds(280, 2531, 60, 23);

        cmbTelKananPel.setForeground(new java.awt.Color(0, 0, 0));
        cmbTelKananPel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbTelKananPel.setName("cmbTelKananPel"); // NOI18N
        cmbTelKananPel.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbTelKananPel);
        cmbTelKananPel.setBounds(205, 2559, 60, 23);

        cmbTelKiriPel.setForeground(new java.awt.Color(0, 0, 0));
        cmbTelKiriPel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbTelKiriPel.setName("cmbTelKiriPel"); // NOI18N
        cmbTelKiriPel.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbTelKiriPel);
        cmbTelKiriPel.setBounds(280, 2559, 60, 23);

        cmbTelKananChar.setForeground(new java.awt.Color(0, 0, 0));
        cmbTelKananChar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbTelKananChar.setName("cmbTelKananChar"); // NOI18N
        cmbTelKananChar.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbTelKananChar);
        cmbTelKananChar.setBounds(205, 2587, 60, 23);

        cmbTelKiriChar.setForeground(new java.awt.Color(0, 0, 0));
        cmbTelKiriChar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbTelKiriChar.setName("cmbTelKiriChar"); // NOI18N
        cmbTelKiriChar.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbTelKiriChar);
        cmbTelKiriChar.setBounds(280, 2587, 60, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Jari Kaki : ");
        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(355, 2447, 120, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setText("KANAN");
        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(479, 2447, 60, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel96.setText("KIRI");
        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(550, 2447, 60, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Hammer toe : ");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(355, 2475, 120, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Claw toe : ");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(355, 2503, 120, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Hiperekstensi : ");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(355, 2531, 120, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Maserasi interdigital : ");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(355, 2559, 120, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Lain-lain (sebutkan) : ");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(355, 2587, 120, 23);

        cmbJarKananHamer.setForeground(new java.awt.Color(0, 0, 0));
        cmbJarKananHamer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbJarKananHamer.setName("cmbJarKananHamer"); // NOI18N
        cmbJarKananHamer.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbJarKananHamer);
        cmbJarKananHamer.setBounds(479, 2475, 60, 23);

        cmbJarKiriHamer.setForeground(new java.awt.Color(0, 0, 0));
        cmbJarKiriHamer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbJarKiriHamer.setName("cmbJarKiriHamer"); // NOI18N
        cmbJarKiriHamer.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbJarKiriHamer);
        cmbJarKiriHamer.setBounds(550, 2475, 60, 23);

        cmbJarKananClaw.setForeground(new java.awt.Color(0, 0, 0));
        cmbJarKananClaw.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbJarKananClaw.setName("cmbJarKananClaw"); // NOI18N
        cmbJarKananClaw.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbJarKananClaw);
        cmbJarKananClaw.setBounds(479, 2503, 60, 23);

        cmbJarKiriClaw.setForeground(new java.awt.Color(0, 0, 0));
        cmbJarKiriClaw.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbJarKiriClaw.setName("cmbJarKiriClaw"); // NOI18N
        cmbJarKiriClaw.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbJarKiriClaw);
        cmbJarKiriClaw.setBounds(550, 2503, 60, 23);

        cmbJarKananHiper.setForeground(new java.awt.Color(0, 0, 0));
        cmbJarKananHiper.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbJarKananHiper.setName("cmbJarKananHiper"); // NOI18N
        cmbJarKananHiper.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbJarKananHiper);
        cmbJarKananHiper.setBounds(479, 2531, 60, 23);

        cmbJarKiriHiper.setForeground(new java.awt.Color(0, 0, 0));
        cmbJarKiriHiper.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbJarKiriHiper.setName("cmbJarKiriHiper"); // NOI18N
        cmbJarKiriHiper.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbJarKiriHiper);
        cmbJarKiriHiper.setBounds(550, 2531, 60, 23);

        cmbJarKananMas.setForeground(new java.awt.Color(0, 0, 0));
        cmbJarKananMas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbJarKananMas.setName("cmbJarKananMas"); // NOI18N
        cmbJarKananMas.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbJarKananMas);
        cmbJarKananMas.setBounds(479, 2559, 60, 23);

        cmbJarKiriMas.setForeground(new java.awt.Color(0, 0, 0));
        cmbJarKiriMas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbJarKiriMas.setName("cmbJarKiriMas"); // NOI18N
        cmbJarKiriMas.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbJarKiriMas);
        cmbJarKiriMas.setBounds(550, 2559, 60, 23);

        cmbJarKananLain.setForeground(new java.awt.Color(0, 0, 0));
        cmbJarKananLain.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbJarKananLain.setName("cmbJarKananLain"); // NOI18N
        cmbJarKananLain.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbJarKananLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJarKananLainActionPerformed(evt);
            }
        });
        FormInput.add(cmbJarKananLain);
        cmbJarKananLain.setBounds(479, 2587, 60, 23);

        cmbJarKiriLain.setForeground(new java.awt.Color(0, 0, 0));
        cmbJarKiriLain.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbJarKiriLain.setName("cmbJarKiriLain"); // NOI18N
        cmbJarKiriLain.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbJarKiriLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJarKiriLainActionPerformed(evt);
            }
        });
        FormInput.add(cmbJarKiriLain);
        cmbJarKiriLain.setBounds(550, 2587, 60, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Ket. Lain-lain Kanan : ");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(355, 2615, 120, 23);

        TketLainKanan.setBackground(new java.awt.Color(245, 250, 240));
        TketLainKanan.setForeground(new java.awt.Color(0, 0, 0));
        TketLainKanan.setName("TketLainKanan"); // NOI18N
        TketLainKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketLainKananKeyPressed(evt);
            }
        });
        FormInput.add(TketLainKanan);
        TketLainKanan.setBounds(479, 2615, 243, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Ket. Lain-lain Kiri : ");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(355, 2643, 120, 23);

        TketLainKiri.setBackground(new java.awt.Color(245, 250, 240));
        TketLainKiri.setForeground(new java.awt.Color(0, 0, 0));
        TketLainKiri.setName("TketLainKiri"); // NOI18N
        TketLainKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketLainKiriKeyPressed(evt);
            }
        });
        FormInput.add(TketLainKiri);
        TketLainKiri.setBounds(479, 2643, 243, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("V. Pemeriksaan Vaskular");
        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(30, 2671, 290, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("a. A. Dorsalis Pedis (KAKI KANAN) : ");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(0, 2699, 230, 23);

        cmbDorsalisPedKanan.setForeground(new java.awt.Color(0, 0, 0));
        cmbDorsalisPedKanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Lemah", "Tidak teraba" }));
        cmbDorsalisPedKanan.setName("cmbDorsalisPedKanan"); // NOI18N
        cmbDorsalisPedKanan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbDorsalisPedKanan);
        cmbDorsalisPedKanan.setBounds(235, 2699, 95, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("Dorsalis Pedis (KAKI KIRI) : ");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(330, 2699, 160, 23);

        cmbDorsalisPedKiri.setForeground(new java.awt.Color(0, 0, 0));
        cmbDorsalisPedKiri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Lemah", "Tidak teraba" }));
        cmbDorsalisPedKiri.setName("cmbDorsalisPedKiri"); // NOI18N
        cmbDorsalisPedKiri.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbDorsalisPedKiri);
        cmbDorsalisPedKiri.setBounds(495, 2699, 95, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("b. A. Tibialis Posterior (KAKI KANAN) : ");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(0, 2727, 230, 23);

        cmbTibialisKanan.setForeground(new java.awt.Color(0, 0, 0));
        cmbTibialisKanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Lemah", "Tidak teraba" }));
        cmbTibialisKanan.setName("cmbTibialisKanan"); // NOI18N
        cmbTibialisKanan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbTibialisKanan);
        cmbTibialisKanan.setBounds(235, 2727, 95, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("Tibialis Posterior (KAKI KIRI) : ");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(330, 2727, 160, 23);

        cmbTibialisKiri.setForeground(new java.awt.Color(0, 0, 0));
        cmbTibialisKiri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Lemah", "Tidak teraba" }));
        cmbTibialisKiri.setName("cmbTibialisKiri"); // NOI18N
        cmbTibialisKiri.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbTibialisKiri);
        cmbTibialisKiri.setBounds(495, 2727, 95, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("c. Pemeriksaan ABI : ");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(0, 2755, 230, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("TDS A. Brachialis : ");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(235, 2755, 120, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("TDS A. Dorsalis Pedis : ");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(235, 2783, 120, 23);

        TtdsBra.setBackground(new java.awt.Color(245, 250, 240));
        TtdsBra.setForeground(new java.awt.Color(0, 0, 0));
        TtdsBra.setName("TtdsBra"); // NOI18N
        TtdsBra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdsBraKeyPressed(evt);
            }
        });
        FormInput.add(TtdsBra);
        TtdsBra.setBounds(357, 2755, 75, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("mmHg      Skor ABI = TDS A. Dorsalis Pedis");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(437, 2755, 220, 23);

        TtdsDor.setBackground(new java.awt.Color(245, 250, 240));
        TtdsDor.setForeground(new java.awt.Color(0, 0, 0));
        TtdsDor.setName("TtdsDor"); // NOI18N
        TtdsDor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdsDorKeyPressed(evt);
            }
        });
        FormInput.add(TtdsDor);
        TtdsDor.setBounds(357, 2783, 75, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel114.setText("=");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(650, 2763, 20, 23);

        TskorAbi.setBackground(new java.awt.Color(245, 250, 240));
        TskorAbi.setForeground(new java.awt.Color(0, 0, 0));
        TskorAbi.setName("TskorAbi"); // NOI18N
        TskorAbi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorAbiKeyPressed(evt);
            }
        });
        FormInput.add(TskorAbi);
        TskorAbi.setBounds(670, 2763, 75, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel115.setText("mmHg");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(750, 2763, 40, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel116.setText("VI. Pemeriksaan Neuropati");
        jLabel116.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(30, 2811, 290, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel117.setText("KAKI KANAN");
        jLabel117.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(155, 2839, 95, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel118.setText("KAKI KIRI");
        jLabel118.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(260, 2839, 95, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("Monofilamen 10 g : ");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(0, 2867, 150, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("Garputala 128 Hz : ");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(0, 2895, 150, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("Reflex tendo Achilles : ");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(0, 2923, 150, 23);

        cmbMonoKanan.setForeground(new java.awt.Color(0, 0, 0));
        cmbMonoKanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Tidak Normal" }));
        cmbMonoKanan.setName("cmbMonoKanan"); // NOI18N
        cmbMonoKanan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMonoKanan);
        cmbMonoKanan.setBounds(155, 2867, 95, 23);

        cmbMonoKiri.setForeground(new java.awt.Color(0, 0, 0));
        cmbMonoKiri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Tidak Normal" }));
        cmbMonoKiri.setName("cmbMonoKiri"); // NOI18N
        cmbMonoKiri.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMonoKiri);
        cmbMonoKiri.setBounds(260, 2867, 95, 23);

        cmbGarKanan.setForeground(new java.awt.Color(0, 0, 0));
        cmbGarKanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Lemah", "Negatif" }));
        cmbGarKanan.setName("cmbGarKanan"); // NOI18N
        cmbGarKanan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbGarKanan);
        cmbGarKanan.setBounds(155, 2895, 95, 23);

        cmbGarKiri.setForeground(new java.awt.Color(0, 0, 0));
        cmbGarKiri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Lemah", "Negatif" }));
        cmbGarKiri.setName("cmbGarKiri"); // NOI18N
        cmbGarKiri.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbGarKiri);
        cmbGarKiri.setBounds(260, 2895, 95, 23);

        cmbRefKanan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRefKanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Lemah", "Negatif" }));
        cmbRefKanan.setName("cmbRefKanan"); // NOI18N
        cmbRefKanan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbRefKanan);
        cmbRefKanan.setBounds(155, 2923, 95, 23);

        cmbRefKiri.setForeground(new java.awt.Color(0, 0, 0));
        cmbRefKiri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Lemah", "Negatif" }));
        cmbRefKiri.setName("cmbRefKiri"); // NOI18N
        cmbRefKiri.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbRefKiri);
        cmbRefKiri.setBounds(260, 2923, 95, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel122.setText("VII. Deraja Luka");
        jLabel122.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(385, 2811, 290, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel123.setText("Klasifikasi Ulkus Wagner : ");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(385, 2839, 150, 23);

        chkDerajat0.setBackground(new java.awt.Color(255, 255, 250));
        chkDerajat0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDerajat0.setForeground(new java.awt.Color(0, 0, 0));
        chkDerajat0.setText("0 : Tidak ada luka terbuka, mungkin terdapat deformitas atau selulitis");
        chkDerajat0.setBorderPainted(true);
        chkDerajat0.setBorderPaintedFlat(true);
        chkDerajat0.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDerajat0.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDerajat0.setName("chkDerajat0"); // NOI18N
        chkDerajat0.setOpaque(false);
        chkDerajat0.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDerajat0);
        chkDerajat0.setBounds(385, 2867, 530, 23);

        chkDerajat1.setBackground(new java.awt.Color(255, 255, 250));
        chkDerajat1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDerajat1.setForeground(new java.awt.Color(0, 0, 0));
        chkDerajat1.setText("1 : Ulkus diabetes superfisial (partial and full thickness)");
        chkDerajat1.setBorderPainted(true);
        chkDerajat1.setBorderPaintedFlat(true);
        chkDerajat1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDerajat1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDerajat1.setName("chkDerajat1"); // NOI18N
        chkDerajat1.setOpaque(false);
        chkDerajat1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDerajat1);
        chkDerajat1.setBounds(385, 2895, 530, 23);

        chkDerajat2.setBackground(new java.awt.Color(255, 255, 250));
        chkDerajat2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDerajat2.setForeground(new java.awt.Color(0, 0, 0));
        chkDerajat2.setText("2 : Ulkus meluas sampai ligament, tendon, kapsula sendi atau fasia dalam tanpa abses atau osteomielitis");
        chkDerajat2.setBorderPainted(true);
        chkDerajat2.setBorderPaintedFlat(true);
        chkDerajat2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDerajat2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDerajat2.setName("chkDerajat2"); // NOI18N
        chkDerajat2.setOpaque(false);
        chkDerajat2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDerajat2);
        chkDerajat2.setBounds(385, 2923, 530, 23);

        chkDerajat3.setBackground(new java.awt.Color(255, 255, 250));
        chkDerajat3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDerajat3.setForeground(new java.awt.Color(0, 0, 0));
        chkDerajat3.setText("3 : Ulkus dalam dengan abses, osteomielitis, atau sepsis sendi");
        chkDerajat3.setBorderPainted(true);
        chkDerajat3.setBorderPaintedFlat(true);
        chkDerajat3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDerajat3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDerajat3.setName("chkDerajat3"); // NOI18N
        chkDerajat3.setOpaque(false);
        chkDerajat3.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDerajat3);
        chkDerajat3.setBounds(385, 2951, 530, 23);

        chkDerajat4.setBackground(new java.awt.Color(255, 255, 250));
        chkDerajat4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDerajat4.setForeground(new java.awt.Color(0, 0, 0));
        chkDerajat4.setText("4 : Gangren yang terbatas pada kaki bagian depan atau tumit");
        chkDerajat4.setToolTipText("");
        chkDerajat4.setBorderPainted(true);
        chkDerajat4.setBorderPaintedFlat(true);
        chkDerajat4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDerajat4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDerajat4.setName("chkDerajat4"); // NOI18N
        chkDerajat4.setOpaque(false);
        chkDerajat4.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDerajat4);
        chkDerajat4.setBounds(385, 2979, 530, 23);

        chkDerajat5.setBackground(new java.awt.Color(255, 255, 250));
        chkDerajat5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDerajat5.setForeground(new java.awt.Color(0, 0, 0));
        chkDerajat5.setText("5 : Gangren yang meluas meliputi seluruh kaki");
        chkDerajat5.setToolTipText("");
        chkDerajat5.setBorderPainted(true);
        chkDerajat5.setBorderPaintedFlat(true);
        chkDerajat5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDerajat5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDerajat5.setName("chkDerajat5"); // NOI18N
        chkDerajat5.setOpaque(false);
        chkDerajat5.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDerajat5);
        chkDerajat5.setBounds(385, 3007, 530, 23);

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel124.setText("VIII. Pemeriksaan Penunjang");
        jLabel124.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(30, 3035, 290, 23);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setText("Laboratorium rutin : ");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(0, 3063, 150, 23);

        scrollPane18.setName("scrollPane18"); // NOI18N

        TpemeriksaanLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TpemeriksaanLab.setColumns(20);
        TpemeriksaanLab.setRows(5);
        TpemeriksaanLab.setName("TpemeriksaanLab"); // NOI18N
        TpemeriksaanLab.setPreferredSize(new java.awt.Dimension(162, 10000));
        TpemeriksaanLab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpemeriksaanLabKeyPressed(evt);
            }
        });
        scrollPane18.setViewportView(TpemeriksaanLab);

        FormInput.add(scrollPane18);
        scrollPane18.setBounds(152, 3063, 650, 140);

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
        BtnPasteHasil.setBounds(810, 3063, 100, 23);

        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setText("Rontgen Kaki : ");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(0, 3210, 150, 23);

        chkTglRonsen.setBackground(new java.awt.Color(255, 255, 250));
        chkTglRonsen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglRonsen.setForeground(new java.awt.Color(0, 0, 0));
        chkTglRonsen.setText("Tanggal : ");
        chkTglRonsen.setToolTipText("");
        chkTglRonsen.setBorderPainted(true);
        chkTglRonsen.setBorderPaintedFlat(true);
        chkTglRonsen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglRonsen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglRonsen.setName("chkTglRonsen"); // NOI18N
        chkTglRonsen.setOpaque(false);
        chkTglRonsen.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglRonsen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglRonsenActionPerformed(evt);
            }
        });
        FormInput.add(chkTglRonsen);
        chkTglRonsen.setBounds(152, 3210, 80, 23);

        TtglRonsen.setEditable(false);
        TtglRonsen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-05-2025" }));
        TtglRonsen.setDisplayFormat("dd-MM-yyyy");
        TtglRonsen.setName("TtglRonsen"); // NOI18N
        TtglRonsen.setOpaque(false);
        TtglRonsen.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglRonsen);
        TtglRonsen.setBounds(235, 3210, 90, 23);

        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setText("Kesimpulan : ");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(152, 3238, 80, 23);

        TkesRonsen.setBackground(new java.awt.Color(245, 250, 240));
        TkesRonsen.setForeground(new java.awt.Color(0, 0, 0));
        TkesRonsen.setName("TkesRonsen"); // NOI18N
        TkesRonsen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesRonsenKeyPressed(evt);
            }
        });
        FormInput.add(TkesRonsen);
        TkesRonsen.setBounds(235, 3238, 557, 23);

        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setText("Osteomielitis : ");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(152, 3266, 80, 23);

        cmbOsteo.setForeground(new java.awt.Color(0, 0, 0));
        cmbOsteo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbOsteo.setName("cmbOsteo"); // NOI18N
        cmbOsteo.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbOsteo);
        cmbOsteo.setBounds(235, 3266, 60, 23);

        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setText("Lokasi : ");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(297, 3266, 57, 23);

        TlokRonsen.setBackground(new java.awt.Color(245, 250, 240));
        TlokRonsen.setForeground(new java.awt.Color(0, 0, 0));
        TlokRonsen.setName("TlokRonsen"); // NOI18N
        TlokRonsen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlokRonsenKeyPressed(evt);
            }
        });
        FormInput.add(TlokRonsen);
        TlokRonsen.setBounds(357, 3266, 435, 23);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setText("Mikrobiologi : ");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(0, 3294, 150, 23);

        jLabel131.setForeground(new java.awt.Color(0, 0, 0));
        jLabel131.setText("Bakteri : ");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(152, 3294, 80, 23);

        Tbakteri.setBackground(new java.awt.Color(245, 250, 240));
        Tbakteri.setForeground(new java.awt.Color(0, 0, 0));
        Tbakteri.setName("Tbakteri"); // NOI18N
        Tbakteri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbakteriKeyPressed(evt);
            }
        });
        FormInput.add(Tbakteri);
        Tbakteri.setBounds(235, 3294, 437, 23);

        jLabel132.setForeground(new java.awt.Color(0, 0, 0));
        jLabel132.setText("Sensitif : ");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(152, 3322, 80, 23);

        Tsensitif.setBackground(new java.awt.Color(245, 250, 240));
        Tsensitif.setForeground(new java.awt.Color(0, 0, 0));
        Tsensitif.setName("Tsensitif"); // NOI18N
        Tsensitif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsensitifKeyPressed(evt);
            }
        });
        FormInput.add(Tsensitif);
        Tsensitif.setBounds(235, 3322, 437, 23);

        jLabel133.setForeground(new java.awt.Color(0, 0, 0));
        jLabel133.setText("Resisten : ");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(152, 3350, 80, 23);

        Tresisten.setBackground(new java.awt.Color(245, 250, 240));
        Tresisten.setForeground(new java.awt.Color(0, 0, 0));
        Tresisten.setName("Tresisten"); // NOI18N
        Tresisten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TresistenKeyPressed(evt);
            }
        });
        FormInput.add(Tresisten);
        Tresisten.setBounds(235, 3350, 437, 23);

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Mikrobiologi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll4.setComponentPopupMenu(jPopupMenu1);
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbMikro.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbMikro.setComponentPopupMenu(jPopupMenu1);
        tbMikro.setName("tbMikro"); // NOI18N
        tbMikro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMikroMouseClicked(evt);
            }
        });
        tbMikro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMikroKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbMikro);

        FormInput.add(Scroll4);
        Scroll4.setBounds(40, 3378, 680, 150);

        jLabel134.setForeground(new java.awt.Color(0, 0, 0));
        jLabel134.setText("(dalam tiga bulan terakhir)   ");
        jLabel134.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(0, 3078, 150, 23);

        BtnTambahMikro.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahMikro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahMikro.setText("Tambah");
        BtnTambahMikro.setToolTipText("Tambah Deformitas");
        BtnTambahMikro.setName("BtnTambahMikro"); // NOI18N
        BtnTambahMikro.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahMikro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahMikroActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahMikro);
        BtnTambahMikro.setBounds(725, 3378, 90, 30);

        BtnSimpanMikro.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanMikro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanMikro.setText("Simpan");
        BtnSimpanMikro.setToolTipText("Simpan Deformitas");
        BtnSimpanMikro.setName("BtnSimpanMikro"); // NOI18N
        BtnSimpanMikro.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnSimpanMikro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanMikroActionPerformed(evt);
            }
        });
        FormInput.add(BtnSimpanMikro);
        BtnSimpanMikro.setBounds(725, 3417, 90, 30);

        BtnHapusMikro.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusMikro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusMikro.setText("Hapus");
        BtnHapusMikro.setToolTipText("Hapus Deformitas");
        BtnHapusMikro.setName("BtnHapusMikro"); // NOI18N
        BtnHapusMikro.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusMikro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusMikroActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusMikro);
        BtnHapusMikro.setBounds(725, 3456, 90, 30);

        BtnGantiMikro.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiMikro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiMikro.setText("Ganti");
        BtnGantiMikro.setToolTipText("Ganti Deformitas");
        BtnGantiMikro.setName("BtnGantiMikro"); // NOI18N
        BtnGantiMikro.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiMikro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiMikroActionPerformed(evt);
            }
        });
        FormInput.add(BtnGantiMikro);
        BtnGantiMikro.setBounds(725, 3495, 90, 30);

        jLabel135.setForeground(new java.awt.Color(0, 0, 0));
        jLabel135.setText("Rontgen Thorax (Kes.) : ");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(0, 3534, 150, 23);

        TkesRonsenTorax.setBackground(new java.awt.Color(245, 250, 240));
        TkesRonsenTorax.setForeground(new java.awt.Color(0, 0, 0));
        TkesRonsenTorax.setName("TkesRonsenTorax"); // NOI18N
        TkesRonsenTorax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesRonsenToraxKeyPressed(evt);
            }
        });
        FormInput.add(TkesRonsenTorax);
        TkesRonsenTorax.setBounds(152, 3534, 570, 23);

        jLabel136.setForeground(new java.awt.Color(0, 0, 0));
        jLabel136.setText("EKG (Kes.) : ");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(0, 3562, 150, 23);

        TkesEkg.setBackground(new java.awt.Color(245, 250, 240));
        TkesEkg.setForeground(new java.awt.Color(0, 0, 0));
        TkesEkg.setName("TkesEkg"); // NOI18N
        TkesEkg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesEkgKeyPressed(evt);
            }
        });
        FormInput.add(TkesEkg);
        TkesEkg.setBounds(152, 3562, 570, 23);

        jLabel137.setForeground(new java.awt.Color(0, 0, 0));
        jLabel137.setText("USG Dopler : ");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(0, 3590, 150, 23);

        TusgDopler.setBackground(new java.awt.Color(245, 250, 240));
        TusgDopler.setForeground(new java.awt.Color(0, 0, 0));
        TusgDopler.setName("TusgDopler"); // NOI18N
        FormInput.add(TusgDopler);
        TusgDopler.setBounds(152, 3590, 570, 23);

        jLabel138.setForeground(new java.awt.Color(0, 0, 0));
        jLabel138.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel138.setText("IX. Tata Laksana Rawat Luka");
        jLabel138.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(30, 3618, 290, 23);

        jLabel139.setForeground(new java.awt.Color(0, 0, 0));
        jLabel139.setText("Debridement : ");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(0, 3646, 150, 23);

        chkSurgical.setBackground(new java.awt.Color(255, 255, 250));
        chkSurgical.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSurgical.setForeground(new java.awt.Color(0, 0, 0));
        chkSurgical.setText("Surgical");
        chkSurgical.setBorderPainted(true);
        chkSurgical.setBorderPaintedFlat(true);
        chkSurgical.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSurgical.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSurgical.setName("chkSurgical"); // NOI18N
        chkSurgical.setOpaque(false);
        chkSurgical.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSurgical);
        chkSurgical.setBounds(152, 3646, 70, 23);

        chkChemical.setBackground(new java.awt.Color(255, 255, 250));
        chkChemical.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkChemical.setForeground(new java.awt.Color(0, 0, 0));
        chkChemical.setText("Chemical");
        chkChemical.setBorderPainted(true);
        chkChemical.setBorderPaintedFlat(true);
        chkChemical.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkChemical.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkChemical.setName("chkChemical"); // NOI18N
        chkChemical.setOpaque(false);
        chkChemical.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkChemical);
        chkChemical.setBounds(230, 3646, 70, 23);

        chkBiology.setBackground(new java.awt.Color(255, 255, 250));
        chkBiology.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBiology.setForeground(new java.awt.Color(0, 0, 0));
        chkBiology.setText("Biology");
        chkBiology.setBorderPainted(true);
        chkBiology.setBorderPaintedFlat(true);
        chkBiology.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBiology.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBiology.setName("chkBiology"); // NOI18N
        chkBiology.setOpaque(false);
        chkBiology.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBiology);
        chkBiology.setBounds(310, 3646, 70, 23);

        jLabel140.setForeground(new java.awt.Color(0, 0, 0));
        jLabel140.setText("Modern Dressing : ");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(0, 3674, 150, 23);

        chkHydro.setBackground(new java.awt.Color(255, 255, 250));
        chkHydro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHydro.setForeground(new java.awt.Color(0, 0, 0));
        chkHydro.setText("Hydrocolloid");
        chkHydro.setBorderPainted(true);
        chkHydro.setBorderPaintedFlat(true);
        chkHydro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHydro.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHydro.setName("chkHydro"); // NOI18N
        chkHydro.setOpaque(false);
        chkHydro.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHydro);
        chkHydro.setBounds(152, 3674, 90, 23);

        chkFoam.setBackground(new java.awt.Color(255, 255, 250));
        chkFoam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFoam.setForeground(new java.awt.Color(0, 0, 0));
        chkFoam.setText("Foam");
        chkFoam.setBorderPainted(true);
        chkFoam.setBorderPaintedFlat(true);
        chkFoam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFoam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFoam.setName("chkFoam"); // NOI18N
        chkFoam.setOpaque(false);
        chkFoam.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkFoam);
        chkFoam.setBounds(250, 3674, 60, 23);

        chkAlgin.setBackground(new java.awt.Color(255, 255, 250));
        chkAlgin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAlgin.setForeground(new java.awt.Color(0, 0, 0));
        chkAlgin.setText("Allginate");
        chkAlgin.setBorderPainted(true);
        chkAlgin.setBorderPaintedFlat(true);
        chkAlgin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAlgin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAlgin.setName("chkAlgin"); // NOI18N
        chkAlgin.setOpaque(false);
        chkAlgin.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAlgin);
        chkAlgin.setBounds(315, 3674, 70, 23);

        chkSilver.setBackground(new java.awt.Color(255, 255, 250));
        chkSilver.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSilver.setForeground(new java.awt.Color(0, 0, 0));
        chkSilver.setText("Silver Sulfadiazine");
        chkSilver.setBorderPainted(true);
        chkSilver.setBorderPaintedFlat(true);
        chkSilver.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSilver.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSilver.setName("chkSilver"); // NOI18N
        chkSilver.setOpaque(false);
        chkSilver.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSilver);
        chkSilver.setBounds(393, 3674, 115, 23);

        chkCadex.setBackground(new java.awt.Color(255, 255, 250));
        chkCadex.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCadex.setForeground(new java.awt.Color(0, 0, 0));
        chkCadex.setText("Cadexomer");
        chkCadex.setBorderPainted(true);
        chkCadex.setBorderPaintedFlat(true);
        chkCadex.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCadex.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCadex.setName("chkCadex"); // NOI18N
        chkCadex.setOpaque(false);
        chkCadex.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCadex);
        chkCadex.setBounds(515, 3674, 85, 23);

        chkMadu.setBackground(new java.awt.Color(255, 255, 250));
        chkMadu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMadu.setForeground(new java.awt.Color(0, 0, 0));
        chkMadu.setText("Madu");
        chkMadu.setBorderPainted(true);
        chkMadu.setBorderPaintedFlat(true);
        chkMadu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMadu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMadu.setName("chkMadu"); // NOI18N
        chkMadu.setOpaque(false);
        chkMadu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMadu);
        chkMadu.setBounds(610, 3674, 60, 23);

        chkLainModern.setBackground(new java.awt.Color(255, 255, 250));
        chkLainModern.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainModern.setForeground(new java.awt.Color(0, 0, 0));
        chkLainModern.setText("Lain-Lain, Sebutkan");
        chkLainModern.setBorderPainted(true);
        chkLainModern.setBorderPaintedFlat(true);
        chkLainModern.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainModern.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainModern.setName("chkLainModern"); // NOI18N
        chkLainModern.setOpaque(false);
        chkLainModern.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainModern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainModernActionPerformed(evt);
            }
        });
        FormInput.add(chkLainModern);
        chkLainModern.setBounds(152, 3702, 122, 23);

        TlainModern.setBackground(new java.awt.Color(245, 250, 240));
        TlainModern.setForeground(new java.awt.Color(0, 0, 0));
        TlainModern.setName("TlainModern"); // NOI18N
        FormInput.add(TlainModern);
        TlainModern.setBounds(277, 3702, 445, 23);

        jLabel141.setForeground(new java.awt.Color(0, 0, 0));
        jLabel141.setText("Disimpan Tgl. : ");
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(0, 3730, 150, 23);

        TtglSimpan.setEditable(false);
        TtglSimpan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-05-2025" }));
        TtglSimpan.setDisplayFormat("dd-MM-yyyy");
        TtglSimpan.setName("TtglSimpan"); // NOI18N
        TtglSimpan.setOpaque(false);
        TtglSimpan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglSimpan);
        TtglSimpan.setBounds(152, 3730, 90, 23);

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
        chkSaya.setBounds(620, 3758, 90, 23);

        jSeparator17.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator17.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator17.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator17.setName("jSeparator17"); // NOI18N
        FormInput.add(jSeparator17);
        jSeparator17.setBounds(537, 2775, 110, 1);

        jLabel142.setForeground(new java.awt.Color(0, 0, 0));
        jLabel142.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel142.setText("TDS A. Brachialis");
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput.add(jLabel142);
        jLabel142.setBounds(540, 2774, 100, 23);

        jLabel143.setForeground(new java.awt.Color(0, 0, 0));
        jLabel143.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel143.setText("mmHg");
        jLabel143.setName("jLabel143"); // NOI18N
        FormInput.add(jLabel143);
        jLabel143.setBounds(437, 2783, 40, 23);

        jLabel144.setForeground(new java.awt.Color(0, 0, 0));
        jLabel144.setText("Kiri : ");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput.add(jLabel144);
        jLabel144.setBounds(150, 1058, 50, 23);

        jLabel145.setForeground(new java.awt.Color(0, 0, 0));
        jLabel145.setText("Kanan : ");
        jLabel145.setName("jLabel145"); // NOI18N
        FormInput.add(jLabel145);
        jLabel145.setBounds(150, 1086, 50, 23);

        ScrollTriase1.setViewportView(FormInput);

        FormData.add(ScrollTriase1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Data", FormData);

        internalFrame4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPasien.setAutoCreateRowSorter(true);
        tbPasien.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPasien.setComponentPopupMenu(jPopupMenu1);
        tbPasien.setName("tbPasien"); // NOI18N
        tbPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPasienMouseClicked(evt);
            }
        });
        tbPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPasienKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPasien);

        internalFrame4.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Asesmen :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-05-2025" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-05-2025" }));
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

        TabRawat.addTab("Data Pasien Diabetes", internalFrame4);

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
            if (Sequel.menyimpantf("data_dasar_kaki_diabetes", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 153, new String[]{
                        TNoRw.getText(), cmbJnsRawat.getSelectedItem().toString(), Ttb.getText(), Tbb.getText(), Tbmi.getText(), Ttensi.getText(),
                        Valid.SetTgl(TtglMasuk.getSelectedItem() + ""), TlamaRawat.getText(), cmbTipeDiabet.getSelectedItem().toString(), TtipeDiabetLain.getText(),
                        TlamaDiketahui.getText(), cmbMerokok.getSelectedItem().toString(), TmerokokYa.getText(), TmerokokMantan.getText(), TlamaLuka.getText(),
                        cmbSatuan.getSelectedItem().toString(), cmbRiwEdukasi.getSelectedItem().toString(), cmbJnsAlas.getSelectedItem().toString(), Tsepatu.getText(),
                        traumaMekanik, traumaKimia, traumaTermis, spontan, penyebabLain, TlainSebutkan.getText(), tersandung, memakaiSepatu, tertusuk, dllSebutkanMekanik,
                        TdllSebutkanMekanik.getText(), terkenaZat, TterkenaZat.getText(), terkenaAirPanas, terkenaPemanas, dllSebutkanTermis, TdllSebutkanTermis.getText(),
                        cmbRiwUlkus.getSelectedItem().toString(), cmbRiwAmputasiKiri.getSelectedItem().toString(), TjariKiri.getText(), TtransKiri.getText(),
                        cmbRiwAmputasiKanan.getSelectedItem().toString(), TjariKanan.getText(), TtransKanan.getText(), mata, ginjal, penyakitJantung,
                        hipertensi, strok, pad, cmbMata.getSelectedItem().toString(), TlaserTahun.getText(), cmbGinjal.getSelectedItem().toString(), nonUlkus, ulkus,
                        ulkusGangen, selulitis, TdorsalKanan.getText(), TplantarKanan.getText(), TplantarKiri.getText(), TdorsalKiri.getText(),
                        cmbKulKananKering.getSelectedItem().toString(), cmbKulKiriKering.getSelectedItem().toString(), cmbKulKananTumit.getSelectedItem().toString(),
                        cmbKulKiriTumit.getSelectedItem().toString(), cmbKulKananBulu.getSelectedItem().toString(), cmbKulKiriBulu.getSelectedItem().toString(),
                        cmbKulKananTinea.getSelectedItem().toString(), cmbKulKiriTinea.getSelectedItem().toString(), cmbKulKananKalus.getSelectedItem().toString(),
                        cmbKulKiriKalus.getSelectedItem().toString(), cmbKulKananKorn.getSelectedItem().toString(), cmbKulKiriKorn.getSelectedItem().toString(),
                        cmbKulKananHiper.getSelectedItem().toString(), cmbKulKiriHiper.getSelectedItem().toString(), cmbKulKananEdema.getSelectedItem().toString(),
                        cmbKulKiriEdema.getSelectedItem().toString(), cmbKulKananHealed.getSelectedItem().toString(), cmbKulKiriHealed.getSelectedItem().toString(),
                        cmbKukKananMenebal.getSelectedItem().toString(), cmbKukKiriMenebal.getSelectedItem().toString(), cmbKukKananInfeksi.getSelectedItem().toString(),
                        cmbKukKiriInfeksi.getSelectedItem().toString(), cmbKukKananPerubahan.getSelectedItem().toString(), cmbKukKiriPerubahan.getSelectedItem().toString(),
                        cmbKukKananRapuh.getSelectedItem().toString(), cmbKukKiriRapuh.getSelectedItem().toString(), cmbKukKananIngro.getSelectedItem().toString(),
                        cmbKukKiriIngro.getSelectedItem().toString(), cmbKukKananAtrofi.getSelectedItem().toString(), cmbKukKiriAtrofi.getSelectedItem().toString(),
                        cmbKukKananLain.getSelectedItem().toString(), cmbKukKiriLain.getSelectedItem().toString(), cmbTelKananHallu.getSelectedItem().toString(),
                        cmbTelKiriHallu.getSelectedItem().toString(), cmbTelKananPel.getSelectedItem().toString(), cmbTelKiriPel.getSelectedItem().toString(),
                        cmbTelKananChar.getSelectedItem().toString(), cmbTelKiriChar.getSelectedItem().toString(), cmbJarKananHamer.getSelectedItem().toString(),
                        cmbJarKiriHamer.getSelectedItem().toString(), cmbJarKananClaw.getSelectedItem().toString(), cmbJarKiriClaw.getSelectedItem().toString(),
                        cmbJarKananHiper.getSelectedItem().toString(), cmbJarKiriHiper.getSelectedItem().toString(), cmbJarKananMas.getSelectedItem().toString(),
                        cmbJarKiriMas.getSelectedItem().toString(), cmbJarKananLain.getSelectedItem().toString(), cmbJarKiriLain.getSelectedItem().toString(),
                        TketLainKanan.getText(), TketLainKiri.getText(), cmbDorsalisPedKanan.getSelectedItem().toString(), cmbDorsalisPedKiri.getSelectedItem().toString(),
                        cmbTibialisKanan.getSelectedItem().toString(), cmbTibialisKiri.getSelectedItem().toString(), TtdsBra.getText(), TtdsDor.getText(), TskorAbi.getText(),
                        cmbMonoKanan.getSelectedItem().toString(), cmbMonoKiri.getSelectedItem().toString(), cmbGarKanan.getSelectedItem().toString(),
                        cmbGarKiri.getSelectedItem().toString(), cmbRefKanan.getSelectedItem().toString(), cmbRefKiri.getSelectedItem().toString(), derajat0, derajat1,
                        derajat2, derajat3, derajat4, derajat5, TpemeriksaanLab.getText(), ronsenKaki, Valid.SetTgl(TtglRonsen.getSelectedItem() + ""), TkesRonsen.getText(),
                        cmbOsteo.getSelectedItem().toString(), TlokRonsen.getText(), TkesRonsenTorax.getText(), TkesEkg.getText(), TusgDopler.getText(), surgical, chemical,
                        biology, hidrocol, foam, allginate, silver, cadexomer, madu, modernDresingLain, TlainModern.getText(), Valid.SetTgl(TtglSimpan.getSelectedItem() + ""),
                        nip, nipDokter, Sequel.cariIsi("select now()")
                    }) == true) {

                if (tbRiwPengobatan.getRowCount() != 0) {
                    for (i = 0; i < tbRiwPengobatan.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("riwayat_pengobatan_kaki_diabetes",
                                "'" + tbRiwPengobatan.getValueAt(i, 0).toString() + "',"
                                + "'" + tbRiwPengobatan.getValueAt(i, 1).toString() + "',"
                                + "'" + tbRiwPengobatan.getValueAt(i, 2).toString() + "',"
                                + "'" + tbRiwPengobatan.getValueAt(i, 3).toString() + "',"
                                + "'" + tbRiwPengobatan.getValueAt(i, 4).toString() + "',"
                                + "'" + tbRiwPengobatan.getValueAt(i, 5).toString() + "'", "Riwayat Pengobatan");
                    }
                }
                
                if (tbRiwLuka.getRowCount() != 0) {
                    for (i = 0; i < tbRiwLuka.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("riwayat_ulkus_kaki_diabetes",
                                "'" + tbRiwLuka.getValueAt(i, 0).toString() + "',"
                                + "'" + tbRiwLuka.getValueAt(i, 1).toString() + "',"
                                + "'" + tbRiwLuka.getValueAt(i, 2).toString() + "',"
                                + "'" + tbRiwLuka.getValueAt(i, 3).toString() + "',"                                
                                + "'" + tbRiwLuka.getValueAt(i, 4).toString() + "'", "Riwayat Luka/Ulkus");
                    }
                }
                
                if (tbDeformitas.getRowCount() != 0) {
                    for (i = 0; i < tbDeformitas.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("deformitas_kaki_diabetes",
                                "'" + tbDeformitas.getValueAt(i, 0).toString() + "',"
                                + "'" + tbDeformitas.getValueAt(i, 1).toString() + "',"
                                + "'" + tbDeformitas.getValueAt(i, 2).toString() + "',"
                                + "'" + tbDeformitas.getValueAt(i, 3).toString() + "',"                                
                                + "'" + tbDeformitas.getValueAt(i, 4).toString() + "'", "Deformitas");
                    }
                }
                
                if (tbMikro.getRowCount() != 0) {
                    for (i = 0; i < tbMikro.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("mikrobiologi_kaki_diabetes",
                                "'" + tbMikro.getValueAt(i, 0).toString() + "',"
                                + "'" + tbMikro.getValueAt(i, 1).toString() + "',"
                                + "'" + tbMikro.getValueAt(i, 2).toString() + "',"
                                + "'" + tbMikro.getValueAt(i, 3).toString() + "',"                                
                                + "'" + tbMikro.getValueAt(i, 4).toString() + "'", "Mikrobiologi");
                    }
                }

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Status Kaki Diabetes", "Simpan");
                TabRawat.setSelectedIndex(1);
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();                
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

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
        if (tbPasien.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapus();
            } else {
                if (nip.equals(akses.getkode())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh perawat yang bernama " + tbPasien.getValueAt(tbPasien.getSelectedRow(), 10).toString() + " ..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (tbPasien.getSelectedRow() > -1) {
                ganti();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
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
        if (tbPasien.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            //data dasar
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            param.put("jenkel", Tjenkel.getText());
            param.put("usia", Tusia.getText());
            param.put("pendidikan", Tpnd.getText());
            param.put("alamat", Talamat.getText());
            param.put("notelp", TnoTelp.getText());
            param.put("tbBB", Ttb.getText() + " Cm/" + Tbb.getText() + " Kg");
            param.put("bmi", Tbmi.getText() + " kg/m²");
            param.put("teknnDarah", Ttensi.getText() + " mmHg");
            param.put("ras", Tsuku.getText());
            param.put("tglMasuk", TtglMasuk.getSelectedItem().toString());
            param.put("jnsRawat", cmbJnsRawat.getSelectedItem().toString());
            
            if (cmbJnsRawat.getSelectedIndex() == 1) {
                if (TlamaRawat.getText().equals("")) {
                    param.put("lamaRwt", "-");
                } else {
                    param.put("lamaRwt", TlamaRawat.getText() + " hari");
                }
            } else {
                param.put("lamaRwt", "-");
            }
            
            //anamnesis
            if (cmbTipeDiabet.getSelectedIndex() == 3) {
                if (TtipeDiabetLain.getText().equals("")) {
                    param.put("tipeDiabet", cmbTipeDiabet.getSelectedItem().toString() + " : -");
                } else {
                    param.put("tipeDiabet", cmbTipeDiabet.getSelectedItem().toString() + " : " + TtipeDiabetLain.getText());
                }                
            } else {
                param.put("tipeDiabet", cmbTipeDiabet.getSelectedItem().toString());
            }
            
            if (TlamaDiketahui.getText().equals("")) {
                param.put("lamaDiketahui", "-");
            } else {
                param.put("lamaDiketahui", TlamaDiketahui.getText() + " tahun (pembulatan ke bawah)");
            }

            if (Sequel.cariInteger("select count(-1) from riwayat_pengobatan_kaki_diabetes where no_rawat='" + TNoRw.getText() + "'") > 0) {
                dataRiwPengobatan();
                param.put("riwayatPengobatan", riwObat + "\n");
            } else {
                param.put("riwayatPengobatan", "");
            }

            if (cmbMerokok.getSelectedIndex() == 1) {
                if (TmerokokYa.getText().equals("")) {
                    param.put("merokok", cmbMerokok.getSelectedItem().toString());
                } else {
                    param.put("merokok", cmbMerokok.getSelectedItem().toString() + " (" + TmerokokYa.getText() + " batang/hari)");
                }
            } else if (cmbMerokok.getSelectedIndex() == 3) {
                if (TmerokokMantan.getText().equals("")) {
                    param.put("merokok", cmbMerokok.getSelectedItem().toString());
                } else {
                    param.put("merokok", cmbMerokok.getSelectedItem().toString() + " (" + TmerokokMantan.getText() + " tahun lalu)");
                }
            } else {
                param.put("merokok", cmbMerokok.getSelectedItem().toString());
            }
            
            if (TlamaLuka.getText().equals("")) {
                param.put("lamaLuka", "-");
            } else {
                if (cmbSatuan.getSelectedIndex() == 0) {
                    param.put("lamaLuka", TlamaLuka.getText());
                } else {
                    param.put("lamaLuka", TlamaLuka.getText() + " " + cmbSatuan.getSelectedItem().toString());
                }
            }
            
            param.put("riwEdukasiDM", cmbRiwEdukasi.getSelectedItem().toString());
            
            if (cmbJnsAlas.getSelectedIndex() == 3) {
                if (Tsepatu.getText().equals("")) {
                    param.put("jnsAlas", cmbJnsAlas.getSelectedItem().toString());
                } else {
                    param.put("jnsAlas", cmbJnsAlas.getSelectedItem().toString() + " (" + Tsepatu.getText() + ")");
                }
            } else {
                param.put("jnsAlas", cmbJnsAlas.getSelectedItem().toString());
            }
            
            if (chkTraumaMekanik.isSelected() == true) {
                param.put("traumaMekanik", "V");
            } else {
                param.put("traumaMekanik", "");
            }
            
            if (chkTersandung.isSelected() == true) {
                param.put("tersandung", "V");
            } else {
                param.put("tersandung", "");
            }
            
            if (chkMemakaiSepatu.isSelected() == true) {
                param.put("makaiSepatu", "V");
            } else {
                param.put("makaiSepatu", "");
            }
            
            if (chkTertusuk.isSelected() == true) {
                param.put("tertusuk", "V");
            } else {
                param.put("tertusuk", "");
            }
            
            if (chkDllsebutkanMekanik.isSelected() == true) {
                param.put("dllSebutMekanik", "V");
                if (TdllSebutkanMekanik.getText().equals("")) {
                    param.put("kalimatdllSebutMekanik", "Dll, Sebutkan .......");
                } else {
                    param.put("kalimatdllSebutMekanik", "Dll, Sebutkan " + TdllSebutkanMekanik.getText());
                }
            } else {
                param.put("dllSebutMekanik", "");
                param.put("kalimatdllSebutMekanik", "Dll, Sebutkan .......");
            }
            
            if (chkTraumaKimia.isSelected() == true) {
                param.put("traumaKimia", "V");
            } else {
                param.put("traumaKimia", "");
            }
            
            if (chkTerkenaZat.isSelected() == true) {
                param.put("terkenaZat", "V");
                if (TterkenaZat.getText().equals("")) {
                    param.put("KalimatterkenaZat", "Terkena Zat Kimia, Sebutkan .......");
                } else {
                    param.put("KalimatterkenaZat", "Terkena Zat Kimia, Sebutkan " + TterkenaZat.getText());
                }
            } else {
                param.put("terkenaZat", "");
                param.put("KalimatterkenaZat", "Terkena Zat Kimia, Sebutkan .......");
            }
            
            if (chkTraumaTermis.isSelected() == true) {
                param.put("traumaTermis", "V");
            } else {
                param.put("traumaTermis", "");
            }
            
            if (chkTerkenaAir.isSelected() == true) {
                param.put("terkenaAir", "V");
            } else {
                param.put("terkenaAir", "");
            }
            
            if (chkTerkenaPemanas.isSelected() == true) {
                param.put("terkenaPemanas", "V");
            } else {
                param.put("terkenaPemanas", "");
            }
            
            if (chkDllsebutkanTermis.isSelected() == true) {
                param.put("dllSebutTermis", "V");
                if (TdllSebutkanTermis.getText().equals("")) {
                    param.put("KalimatdllSebutTermis", "Dll, Sebutkan .........");
                } else {
                    param.put("KalimatdllSebutTermis", "Dll, Sebutkan " + TdllSebutkanTermis.getText());
                }
            } else {
                param.put("dllSebutTermis", "");
                param.put("KalimatdllSebutTermis", "Dll, Sebutkan .........");
            }
            
            if (chkSpontan.isSelected() == true) {
                param.put("spontan", "V");
            } else {
                param.put("spontan", "");
            }
            
            if (chkLainLain.isSelected() == true) {
                param.put("penyebabLain", "V");
                if (TlainSebutkan.getText().equals("")) {
                    param.put("KalimatpenyebabLain", "5. Lain-lain, Sebutkan .........");
                } else {
                    param.put("KalimatpenyebabLain", "5. Lain-lain, Sebutkan " + TlainSebutkan.getText());
                }
            } else {
                param.put("penyebabLain", "");
                param.put("KalimatpenyebabLain", "5. Lain-lain, Sebutkan .........");
            }
            
            param.put("riwUlkus", cmbRiwUlkus.getSelectedItem().toString());
            
            if (cmbRiwUlkus.getSelectedIndex() == 1) {                
                if (Sequel.cariInteger("select count(-1) from riwayat_ulkus_kaki_diabetes where no_rawat='" + TNoRw.getText() + "'") > 0) {
                    dataRiwUlkus();
                    param.put("DatariwUlkus", riwUlkus + "\n");
                } else {
                    param.put("DatariwUlkus", "");
                }                
            } else {
                param.put("DatariwUlkus", "");
            }

            if (cmbRiwAmputasiKiri.getSelectedIndex() == 3) {
                if (TjariKiri.getText().equals("")) {
                    param.put("KataamputasiKiri", cmbRiwAmputasiKiri.getSelectedItem().toString() + " .........");
                } else {
                    param.put("KataamputasiKiri", cmbRiwAmputasiKiri.getSelectedItem().toString() + " " + TjariKiri.getText());
                }
            } else if (cmbRiwAmputasiKiri.getSelectedIndex() == 4) {
                if (TtransKiri.getText().equals("")) {
                    param.put("KataamputasiKiri", cmbRiwAmputasiKiri.getSelectedItem().toString() + " .........");
                } else {
                    param.put("KataamputasiKiri", cmbRiwAmputasiKiri.getSelectedItem().toString() + " " + TtransKiri.getText());
                }
            } else {
                param.put("KataamputasiKiri", cmbRiwAmputasiKiri.getSelectedItem().toString());
            }

            if (cmbRiwAmputasiKanan.getSelectedIndex() == 3) {
                if (TjariKanan.getText().equals("")) {
                    param.put("KataamputasiKanan", cmbRiwAmputasiKanan.getSelectedItem().toString() + " .........");
                } else {
                    param.put("KataamputasiKanan", cmbRiwAmputasiKanan.getSelectedItem().toString() + " " + TjariKanan.getText());
                }
            } else if (cmbRiwAmputasiKanan.getSelectedIndex() == 4) {
                if (TtransKanan.getText().equals("")) {
                    param.put("KataamputasiKanan", cmbRiwAmputasiKanan.getSelectedItem().toString() + " .........");
                } else {
                    param.put("KataamputasiKanan", cmbRiwAmputasiKanan.getSelectedItem().toString() + " " + TtransKanan.getText());
                }
            } else {
                param.put("KataamputasiKanan", cmbRiwAmputasiKanan.getSelectedItem().toString());
            }
            
            //riwayat komplikasi
            if (chkMata.isSelected() == true) {
                param.put("mata", "V");
                if (cmbMata.getSelectedIndex() == 4) {
                    if (TlaserTahun.getText().equals("")) {
                        param.put("Katamata", cmbMata.getSelectedItem().toString() + " ..........");
                    } else {
                        param.put("Katamata", cmbMata.getSelectedItem().toString() + " " + TlaserTahun.getText());
                    }
                } else {
                    param.put("Katamata", cmbMata.getSelectedItem().toString());
                }
            } else {
                param.put("mata", "");
                param.put("Katamata", cmbMata.getSelectedItem().toString());
            }
            
            if (chkGinjal.isSelected() == true) {
                param.put("ginjal", "V");
            } else {
                param.put("ginjal", "");
            }
            
            param.put("KataGinjal", cmbGinjal.getSelectedItem().toString());
            
            if (chkPenyJantung.isSelected() == true) {
                param.put("penJantung", "V");
            } else {
                param.put("penJantung", "");
            }
            
            if (chkHipertensi.isSelected() == true) {
                param.put("hipertensi", "V");
            } else {
                param.put("hipertensi", "");
            }
            
            if (chkStrok.isSelected() == true) {
                param.put("strok", "V");
            } else {
                param.put("strok", "");
            }
            
            if (chkPad.isSelected() == true) {
                param.put("pad", "V");
            } else {
                param.put("pad", "");
            }
            
            
                     
            param.put("petugas", TnmPerawat.getText());
            param.put("verifikator", TnmDokter.getText());
            
            Valid.MyReport("rptAsesmenKeperawatanPerinatologi2.jasper", "report", "::[ Asesmen Keperawatan Perinatologi Hal. 2 ]::",
                    "SELECT now() tanggal", param);
            Valid.MyReport("rptAsesmenKeperawatanPerinatologi1.jasper", "report", "::[ Asesmen Keperawatan Perinatologi Hal. 1 ]::",
                    "SELECT now() tanggal", param);            
            
            TabRawat.setSelectedIndex(1);
            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
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
        if (Sequel.cariInteger("select count(-1) from data_dasar_kaki_diabetes where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
            tampil();
        } else if (Sequel.cariInteger("select count(-1) from data_dasar_kaki_diabetes where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void tbPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPasienKeyPressed

    private void tbPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPasienMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {                
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbPasien.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tbPasienMouseClicked

    private void BtnPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatActionPerformed
        akses.setform("RMStatusKakiDiabetes");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatActionPerformed

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMStatusKakiDiabetes");
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
            akses.setform("RMStatusKakiDiabetes");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        akses.setform("RMStatusKakiDiabetes");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void tbRiwPengobatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwPengobatanMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataObat();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwPengobatanMouseClicked

    private void tbRiwPengobatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwPengobatanKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataObat();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwPengobatanKeyPressed

    private void BtnTambahObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahObatActionPerformed
        cmbObat.setSelectedIndex(0);
        Tjenis.setText("");
        Tdosis.setText("");
        Tlama.setText("");
    }//GEN-LAST:event_BtnTambahObatActionPerformed

    private void BtnSimpanObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanObatActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (cmbObat.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu obatnya dengan benar..!!");
            cmbObat.requestFocus();
        } else {
            tabMode1.addRow(new String[]{TNoRw.getText(), cmbObat.getSelectedItem().toString(), Tjenis.getText(),
                Tdosis.getText(), Tlama.getText(), Sequel.cariIsi("select now()")
            });
            BtnTambahObatActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpanObatActionPerformed

    private void BtnHapusObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusObatActionPerformed
        if (tbRiwPengobatan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data riwayat pengobatan yang bisa dihapus..!!");
        } else {
            if (tbRiwPengobatan.getSelectedRow() > -1) {
                tabMode1.removeRow(tbRiwPengobatan.getSelectedRow());
                BtnTambahObatActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel riwayat pengobatan..!!");
                tbRiwPengobatan.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusObatActionPerformed

    private void BtnGantiObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiObatActionPerformed
        if (tbRiwPengobatan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data riwayat pengobatan yang bisa diganti..!!");
        } else {
            if (tbRiwPengobatan.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (cmbObat.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu obatnya dengan benar..!!");
                    cmbObat.requestFocus();
                } else {
                    tabMode1.addRow(new String[]{TNoRw.getText(), cmbObat.getSelectedItem().toString(), Tjenis.getText(),
                        Tdosis.getText(), Tlama.getText(), Sequel.cariIsi("select now()")
                    });
                    
                    tabMode1.removeRow(tbRiwPengobatan.getSelectedRow());
                    BtnTambahObatActionPerformed(null);
                }                
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel riwayat pengobatan..!!");
                tbRiwPengobatan.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiObatActionPerformed

    private void cmbTipeDiabetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipeDiabetActionPerformed
        TtipeDiabetLain.setText("");
        if (cmbTipeDiabet.getSelectedIndex() == 3) {
            TtipeDiabetLain.setEnabled(true);
            TtipeDiabetLain.requestFocus();
        } else {
            TtipeDiabetLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTipeDiabetActionPerformed

    private void TtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Ttb.getText().contains(",") == true) {
                Ttb.setText(Ttb.getText().replaceAll(",", "."));
            }
            BtnBMIActionPerformed(null);
            Tbb.requestFocus();
        }
    }//GEN-LAST:event_TtbKeyPressed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Ttb.getText().contains(",") == true) {
                Ttb.setText(Ttb.getText().replaceAll(",", "."));
            }

            if (Tbb.getText().contains(",") == true) {
                Tbb.setText(Tbb.getText().replaceAll(",", "."));
            }
            BtnBMIActionPerformed(null);
            BtnBMI.requestFocus();
        }
    }//GEN-LAST:event_TbbKeyPressed

    private void TbmiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbmiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnBMIActionPerformed(null);
            Ttensi.requestFocus();
        }
    }//GEN-LAST:event_TbmiKeyPressed

    private void TtensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtensiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglMasuk.requestFocus();
        }
    }//GEN-LAST:event_TtensiKeyPressed

    private void TlamaRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlamaRawatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbTipeDiabet.requestFocus();
        }
    }//GEN-LAST:event_TlamaRawatKeyPressed

    private void TtipeDiabetLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtipeDiabetLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TlamaDiketahui.requestFocus();
        }
    }//GEN-LAST:event_TtipeDiabetLainKeyPressed

    private void TlamaDiketahuiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlamaDiketahuiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbObat.requestFocus();
        }
    }//GEN-LAST:event_TlamaDiketahuiKeyPressed

    private void TjenisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjenisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdosis.requestFocus();
        }
    }//GEN-LAST:event_TjenisKeyPressed

    private void TdosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlama.requestFocus();
        }
    }//GEN-LAST:event_TdosisKeyPressed

    private void TlamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpanObatActionPerformed(null);
            cmbObat.requestFocus();
        }
    }//GEN-LAST:event_TlamaKeyPressed

    private void cmbMerokokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMerokokActionPerformed
        TmerokokYa.setText("");
        TmerokokMantan.setText("");
        if (cmbMerokok.getSelectedIndex() == 1) {
            TmerokokYa.setEnabled(true);
            TmerokokMantan.setEnabled(false);
            TmerokokYa.requestFocus();            
        } else if (cmbMerokok.getSelectedIndex() == 3) {
            TmerokokYa.setEnabled(false);
            TmerokokMantan.setEnabled(true);
            TmerokokMantan.requestFocus();
        } else {
            TmerokokYa.setEnabled(false);
            TmerokokMantan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbMerokokActionPerformed

    private void TmerokokYaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmerokokYaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TlamaLuka.requestFocus();
        }
    }//GEN-LAST:event_TmerokokYaKeyPressed

    private void TmerokokMantanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmerokokMantanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TlamaLuka.requestFocus();
        }
    }//GEN-LAST:event_TmerokokMantanKeyPressed

    private void TlamaLukaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlamaLukaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSatuan.requestFocus();
        }
    }//GEN-LAST:event_TlamaLukaKeyPressed

    private void cmbJnsAlasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJnsAlasActionPerformed
        Tsepatu.setText("");
        if (cmbJnsAlas.getSelectedIndex() == 3) {
            Tsepatu.setEnabled(true);
            Tsepatu.requestFocus();
        } else {
            Tsepatu.setEnabled(false);
        }
    }//GEN-LAST:event_cmbJnsAlasActionPerformed

    private void TsepatuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsepatuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTraumaMekanik.requestFocus();
        }
    }//GEN-LAST:event_TsepatuKeyPressed

    private void TdllSebutkanMekanikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdllSebutkanMekanikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTraumaKimia.requestFocus();
        }
    }//GEN-LAST:event_TdllSebutkanMekanikKeyPressed

    private void chkDllsebutkanMekanikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDllsebutkanMekanikActionPerformed
        TdllSebutkanMekanik.setText("");
        if (chkDllsebutkanMekanik.isSelected() == true) {
            TdllSebutkanMekanik.setEnabled(true);
            TdllSebutkanMekanik.requestFocus();
        } else {
            TdllSebutkanMekanik.setEnabled(false);
        }
    }//GEN-LAST:event_chkDllsebutkanMekanikActionPerformed

    private void chkTraumaMekanikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTraumaMekanikActionPerformed
        chkTersandung.setSelected(false);
        chkMemakaiSepatu.setSelected(false);
        chkTertusuk.setSelected(false);
        chkDllsebutkanMekanik.setSelected(false);
        TdllSebutkanMekanik.setText("");
        if (chkTraumaMekanik.isSelected() == true) {
            chkTersandung.setEnabled(true);
            chkMemakaiSepatu.setEnabled(true);
            chkTertusuk.setEnabled(true);
            chkDllsebutkanMekanik.setEnabled(true);
            TdllSebutkanMekanik.setEnabled(false);
            chkTersandung.requestFocus();
        } else {
            chkTersandung.setEnabled(false);
            chkMemakaiSepatu.setEnabled(false);
            chkTertusuk.setEnabled(false);
            chkDllsebutkanMekanik.setEnabled(false);
            TdllSebutkanMekanik.setEnabled(false);
        }
    }//GEN-LAST:event_chkTraumaMekanikActionPerformed

    private void chkTraumaKimiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTraumaKimiaActionPerformed
        chkTerkenaZat.setSelected(false);
        TterkenaZat.setText("");
        if (chkTraumaKimia.isSelected() == true) {
            chkTerkenaZat.setEnabled(true);
            TterkenaZat.setEnabled(false);
            chkTerkenaZat.requestFocus();
        } else {
            chkTerkenaZat.setEnabled(false);
            TterkenaZat.setEnabled(false);
        }
    }//GEN-LAST:event_chkTraumaKimiaActionPerformed

    private void chkTerkenaZatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTerkenaZatActionPerformed
        TterkenaZat.setText("");
        if (chkTerkenaZat.isSelected() == true) {
            TterkenaZat.setEnabled(true);
            TterkenaZat.requestFocus();
        } else {
            TterkenaZat.setEnabled(false);
        }
    }//GEN-LAST:event_chkTerkenaZatActionPerformed

    private void TterkenaZatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TterkenaZatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTraumaTermis.requestFocus();
        }
    }//GEN-LAST:event_TterkenaZatKeyPressed

    private void chkTraumaTermisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTraumaTermisActionPerformed
        chkTerkenaAir.setSelected(false);
        chkTerkenaPemanas.setSelected(false);
        chkDllsebutkanTermis.setSelected(false);
        TdllSebutkanTermis.setText("");
        if (chkTraumaTermis.isSelected() == true) {
            chkTerkenaAir.setEnabled(true);
            chkTerkenaPemanas.setEnabled(true);
            chkDllsebutkanTermis.setEnabled(true);
            TdllSebutkanTermis.setEnabled(false);
            chkTerkenaAir.requestFocus();
        } else {
            chkTerkenaAir.setEnabled(false);
            chkTerkenaPemanas.setEnabled(false);
            chkDllsebutkanTermis.setEnabled(false);
            TdllSebutkanTermis.setEnabled(false);
        }
    }//GEN-LAST:event_chkTraumaTermisActionPerformed

    private void chkDllsebutkanTermisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDllsebutkanTermisActionPerformed
        TdllSebutkanTermis.setText("");
        if (chkDllsebutkanTermis.isSelected() == true) {
            TdllSebutkanTermis.setEnabled(true);
            TdllSebutkanTermis.requestFocus();
        } else {
            TdllSebutkanTermis.setEnabled(false);
        }
    }//GEN-LAST:event_chkDllsebutkanTermisActionPerformed

    private void TdllSebutkanTermisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdllSebutkanTermisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSpontan.requestFocus();
        }
    }//GEN-LAST:event_TdllSebutkanTermisKeyPressed

    private void chkLainLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainLainActionPerformed
        TlainSebutkan.setText("");
        if (chkLainLain.isSelected() == true) {
            TlainSebutkan.setEnabled(true);
            TlainSebutkan.requestFocus();
        } else {
            TlainSebutkan.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainLainActionPerformed

    private void TlainSebutkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainSebutkanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbRiwUlkus.requestFocus();
        }
    }//GEN-LAST:event_TlainSebutkanKeyPressed

    private void TtahunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtahunKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlokasi.requestFocus();
        }
    }//GEN-LAST:event_TtahunKeyPressed

    private void TlokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlokasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpenyebab.requestFocus();
        }
    }//GEN-LAST:event_TlokasiKeyPressed

    private void TpenyebabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpenyebabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpanLukaActionPerformed(null);
            Ttahun.requestFocus();
        }
    }//GEN-LAST:event_TpenyebabKeyPressed

    private void tbRiwLukaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwLukaMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataLuka();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwLukaMouseClicked

    private void tbRiwLukaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwLukaKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataLuka();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwLukaKeyPressed

    private void BtnTambahLukaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahLukaActionPerformed
        Ttahun.setText("");
        Tlokasi.setText("");
        Tpenyebab.setText("");
    }//GEN-LAST:event_BtnTambahLukaActionPerformed

    private void BtnSimpanLukaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanLukaActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (Ttahun.getText().equals("")) {
            Valid.textKosong(Ttahun, "Tahun");
            Ttahun.requestFocus();
        } else {
            tabMode2.addRow(new String[]{TNoRw.getText(), Ttahun.getText(),
                Tlokasi.getText(), Tpenyebab.getText(), Sequel.cariIsi("select now()")
            });
            BtnTambahLukaActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpanLukaActionPerformed

    private void BtnHapusLukaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusLukaActionPerformed
        if (tbRiwLuka.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data riwayat luka/ulkus yang bisa dihapus..!!");
        } else {
            if (tbRiwLuka.getSelectedRow() > -1) {
                tabMode2.removeRow(tbRiwLuka.getSelectedRow());
                BtnTambahLukaActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel riwayat luka/ulkus..!!");
                tbRiwLuka.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusLukaActionPerformed

    private void BtnGantiLukaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiLukaActionPerformed
        if (tbRiwLuka.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data riwayat luka/ulkus yang bisa diganti..!!");
        } else {
            if (tbRiwLuka.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (Ttahun.getText().equals("")) {
                    Valid.textKosong(Ttahun, "Tahun");
                    Ttahun.requestFocus();
                } else {
                    tabMode2.addRow(new String[]{TNoRw.getText(), Ttahun.getText(),
                        Tlokasi.getText(), Tpenyebab.getText(), Sequel.cariIsi("select now()")
                    });
                    
                    tabMode2.removeRow(tbRiwLuka.getSelectedRow());
                    BtnTambahLukaActionPerformed(null);
                }                
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel riwayat luka/ulkus..!!");
                tbRiwLuka.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiLukaActionPerformed

    private void TjariKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjariKiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkMata.requestFocus();
        }
    }//GEN-LAST:event_TjariKiriKeyPressed

    private void TtransKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtransKiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkMata.requestFocus();
        }
    }//GEN-LAST:event_TtransKiriKeyPressed

    private void cmbRiwAmputasiKiriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwAmputasiKiriActionPerformed
        TjariKiri.setText("");
        TtransKiri.setText("");
        if (cmbRiwAmputasiKiri.getSelectedIndex() == 3) {
            TjariKiri.setEnabled(true);
            TtransKiri.setEnabled(false);
            TjariKiri.requestFocus();
        } else if (cmbRiwAmputasiKiri.getSelectedIndex() == 4) {
            TjariKiri.setEnabled(false);
            TtransKiri.setEnabled(true);
            TtransKiri.requestFocus();
        } else {
            TjariKiri.setEnabled(false);
            TtransKiri.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRiwAmputasiKiriActionPerformed

    private void cmbRiwAmputasiKananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwAmputasiKananActionPerformed
        TjariKanan.setText("");
        TtransKanan.setText("");
        if (cmbRiwAmputasiKanan.getSelectedIndex() == 3) {
            TjariKanan.setEnabled(true);
            TtransKanan.setEnabled(false);
            TjariKanan.requestFocus();
        } else if (cmbRiwAmputasiKanan.getSelectedIndex() == 4) {
            TjariKanan.setEnabled(false);
            TtransKanan.setEnabled(true);
            TtransKanan.requestFocus();
        } else {
            TjariKanan.setEnabled(false);
            TtransKanan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRiwAmputasiKananActionPerformed

    private void TjariKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjariKananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkMata.requestFocus();
        }
    }//GEN-LAST:event_TjariKananKeyPressed

    private void TtransKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtransKananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkMata.requestFocus();
        }
    }//GEN-LAST:event_TtransKananKeyPressed

    private void BtnBMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBMIActionPerformed
        hitungBMI();
    }//GEN-LAST:event_BtnBMIActionPerformed

    private void BtnBMIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBMIKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBMIActionPerformed(null);
        }
    }//GEN-LAST:event_BtnBMIKeyPressed

    private void chkMataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMataActionPerformed
        cmbMata.setSelectedIndex(0);
        TlaserTahun.setText("");
        if (chkMata.isSelected() == true) {
            cmbMata.setEnabled(true);
            TlaserTahun.setEnabled(false);
            cmbMata.requestFocus();
        } else {
            cmbMata.setEnabled(false);
            TlaserTahun.setEnabled(false);
        }
    }//GEN-LAST:event_chkMataActionPerformed

    private void chkGinjalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkGinjalActionPerformed
        cmbGinjal.setSelectedIndex(0);
        if (chkGinjal.isSelected() == true) {
            cmbGinjal.setEnabled(true);
            cmbGinjal.requestFocus();
        } else {
            cmbGinjal.setEnabled(false);
        }
    }//GEN-LAST:event_chkGinjalActionPerformed

    private void cmbMataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMataActionPerformed
        TlaserTahun.setText("");
        if (cmbMata.getSelectedIndex() == 4) {
            TlaserTahun.setEnabled(true);
            TlaserTahun.requestFocus();
        } else {
            TlaserTahun.setEnabled(false);
        }
    }//GEN-LAST:event_cmbMataActionPerformed

    private void TlaserTahunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlaserTahunKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkGinjal.requestFocus();
        }
    }//GEN-LAST:event_TlaserTahunKeyPressed

    private void TdorsalKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdorsalKananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TplantarKanan.requestFocus();
        }
    }//GEN-LAST:event_TdorsalKananKeyPressed

    private void TplantarKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TplantarKananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TdorsalKiri.requestFocus();
        }
    }//GEN-LAST:event_TplantarKananKeyPressed

    private void TdorsalKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdorsalKiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TplantarKiri.requestFocus();
        }
    }//GEN-LAST:event_TdorsalKiriKeyPressed

    private void TplantarKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TplantarKiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbLokasi.requestFocus();
        }
    }//GEN-LAST:event_TplantarKiriKeyPressed

    private void TdeforKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdeforKananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdeforKiri.requestFocus();
        }
    }//GEN-LAST:event_TdeforKananKeyPressed

    private void TdeforKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdeforKiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpanDeforActionPerformed(null);
            cmbLokasi.requestFocus();
        }
    }//GEN-LAST:event_TdeforKiriKeyPressed

    private void tbDeformitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDeformitasMouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getDataDefor();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDeformitasMouseClicked

    private void tbDeformitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDeformitasKeyPressed
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataDefor();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDeformitasKeyPressed

    private void BtnTambahDeforActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahDeforActionPerformed
        cmbLokasi.setSelectedIndex(0);
        TdeforKanan.setText("");
        TdeforKiri.setText("");
    }//GEN-LAST:event_BtnTambahDeforActionPerformed

    private void BtnSimpanDeforActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanDeforActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (cmbLokasi.getSelectedIndex() == 0) {
            Valid.textKosong(cmbLokasi, "Lokasi Kelainan");
            cmbLokasi.requestFocus();
        } else {
            tabMode3.addRow(new String[]{TNoRw.getText(), cmbLokasi.getSelectedItem().toString(),
                TdeforKanan.getText(), TdeforKiri.getText(), Sequel.cariIsi("select now()")
            });
            BtnTambahDeforActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpanDeforActionPerformed

    private void BtnHapusDeforActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusDeforActionPerformed
        if (tbDeformitas.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data deformitas yang bisa dihapus..!!");
        } else {
            if (tbDeformitas.getSelectedRow() > -1) {
                tabMode3.removeRow(tbDeformitas.getSelectedRow());
                BtnTambahDeforActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel deformitas..!!");
                tbDeformitas.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusDeforActionPerformed

    private void BtnGantiDeforActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiDeforActionPerformed
        if (tbDeformitas.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data deformitas yang bisa diganti..!!");
        } else {
            if (tbDeformitas.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (cmbLokasi.getSelectedIndex() == 0) {
                    Valid.textKosong(cmbLokasi, "Lokasi Kelainan");
                    cmbLokasi.requestFocus();
                } else {
                    tabMode3.addRow(new String[]{TNoRw.getText(), cmbLokasi.getSelectedItem().toString(),
                        TdeforKanan.getText(), TdeforKiri.getText(), Sequel.cariIsi("select now()")
                    });
                    
                    tabMode3.removeRow(tbDeformitas.getSelectedRow());
                    BtnTambahDeforActionPerformed(null);
                }                
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel deformitas..!!");
                tbDeformitas.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiDeforActionPerformed

    private void TketLainKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketLainKananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbDorsalisPedKanan.requestFocus();
        }
    }//GEN-LAST:event_TketLainKananKeyPressed

    private void TketLainKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketLainKiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbDorsalisPedKanan.requestFocus();
        }
    }//GEN-LAST:event_TketLainKiriKeyPressed

    private void cmbJarKananLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJarKananLainActionPerformed
        TketLainKanan.setText("");
        if (cmbJarKananLain.getSelectedIndex() == 1) {
            TketLainKanan.setEnabled(true);
            TketLainKanan.requestFocus();
        } else {
            TketLainKanan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbJarKananLainActionPerformed

    private void cmbJarKiriLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJarKiriLainActionPerformed
        TketLainKiri.setText("");
        if (cmbJarKiriLain.getSelectedIndex() == 1) {
            TketLainKiri.setEnabled(true);
            TketLainKiri.requestFocus();
        } else {
            TketLainKiri.setEnabled(false);
        }
    }//GEN-LAST:event_cmbJarKiriLainActionPerformed

    private void TtdsBraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdsBraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtdsDor.requestFocus();
        }
    }//GEN-LAST:event_TtdsBraKeyPressed

    private void TtdsDorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdsDorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TskorAbi.requestFocus();
        }
    }//GEN-LAST:event_TtdsDorKeyPressed

    private void TskorAbiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorAbiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbMonoKanan.requestFocus();
        }
    }//GEN-LAST:event_TskorAbiKeyPressed

    private void TpemeriksaanLabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpemeriksaanLabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            chkTglRonsen.requestFocus();
        }
    }//GEN-LAST:event_TpemeriksaanLabKeyPressed

    private void BtnPasteHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteHasilActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (TpemeriksaanLab.getText().equals("")) {
                TpemeriksaanLab.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                TpemeriksaanLab.setText(TpemeriksaanLab.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteHasilActionPerformed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMStatusKakiDiabetes");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void chkTglRonsenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglRonsenActionPerformed
        TtglRonsen.setDate(new Date());
        TkesRonsen.setText("");
        cmbOsteo.setSelectedIndex(0);
        TlokRonsen.setText("");
        if (chkTglRonsen.isSelected() == true) {
            TtglRonsen.setEnabled(true);
            TkesRonsen.setEnabled(true);
            cmbOsteo.setEnabled(true);
            TlokRonsen.setEnabled(true);
            TtglRonsen.requestFocus();
        } else {
            TtglRonsen.setEnabled(false);
            TkesRonsen.setEnabled(false);
            cmbOsteo.setEnabled(false);
            TlokRonsen.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglRonsenActionPerformed

    private void TkesRonsenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesRonsenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbOsteo.requestFocus();
        }
    }//GEN-LAST:event_TkesRonsenKeyPressed

    private void TlokRonsenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlokRonsenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tbakteri.requestFocus();
        }
    }//GEN-LAST:event_TlokRonsenKeyPressed

    private void TbakteriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbakteriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsensitif.requestFocus();
        }
    }//GEN-LAST:event_TbakteriKeyPressed

    private void TsensitifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsensitifKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tresisten.requestFocus();
        }
    }//GEN-LAST:event_TsensitifKeyPressed

    private void TresistenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TresistenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpanMikroActionPerformed(null);
            Tbakteri.requestFocus();
        }
    }//GEN-LAST:event_TresistenKeyPressed

    private void tbMikroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMikroMouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                getDataMikro();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbMikroMouseClicked

    private void tbMikroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMikroKeyPressed
        if (tabMode4.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataMikro();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbMikroKeyPressed

    private void BtnTambahMikroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahMikroActionPerformed
        Tbakteri.setText("");
        Tsensitif.setText("");
        Tresisten.setText("");
    }//GEN-LAST:event_BtnTambahMikroActionPerformed

    private void BtnSimpanMikroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanMikroActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            tabMode4.addRow(new String[]{TNoRw.getText(), Tbakteri.getText(),
                Tsensitif.getText(), Tresisten.getText(), Sequel.cariIsi("select now()")
            });
            BtnTambahMikroActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpanMikroActionPerformed

    private void BtnHapusMikroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusMikroActionPerformed
        if (tbMikro.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data mikrobiologi yang bisa dihapus..!!");
        } else {
            if (tbMikro.getSelectedRow() > -1) {
                tabMode4.removeRow(tbMikro.getSelectedRow());
                BtnTambahMikroActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel mikrobiologi..!!");
                tbMikro.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusMikroActionPerformed

    private void BtnGantiMikroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiMikroActionPerformed
        if (tbMikro.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data mikrobiologi yang bisa diganti..!!");
        } else {
            if (tbMikro.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else {
                    tabMode4.addRow(new String[]{TNoRw.getText(), Tbakteri.getText(),
                        Tsensitif.getText(), Tresisten.getText(), Sequel.cariIsi("select now()")
                    });
                    
                    tabMode4.removeRow(tbMikro.getSelectedRow());
                    BtnTambahMikroActionPerformed(null);
                }                
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel mikrobiologi..!!");
                tbMikro.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiMikroActionPerformed

    private void TkesRonsenToraxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesRonsenToraxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TkesEkg.requestFocus();
        }
    }//GEN-LAST:event_TkesRonsenToraxKeyPressed

    private void TkesEkgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesEkgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TusgDopler.requestFocus();
        }
    }//GEN-LAST:event_TkesEkgKeyPressed

    private void chkLainModernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainModernActionPerformed
        TlainModern.setText("");
        if (chkLainModern.isSelected() == true) {
            TlainModern.setEnabled(true);
            TlainModern.requestFocus();
        } else {
            TlainModern.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainModernActionPerformed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                nip = "-";
                TnmPerawat.setText("-");
            } else {
                nip = akses.getkode();
                TnmPerawat.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip + "'"));
            }
        } else {
            nip = "-";
            TnmPerawat.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void cmbJnsRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJnsRawatActionPerformed
        TlamaRawat.setText("");
        if (cmbJnsRawat.getSelectedIndex() == 1) {
            TlamaRawat.setEnabled(true);
            TlamaRawat.requestFocus();
        } else {
            TlamaRawat.setEnabled(false);
        }
    }//GEN-LAST:event_cmbJnsRawatActionPerformed

    private void cmbRiwUlkusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwUlkusActionPerformed
        Ttahun.setText("");
        Tlokasi.setText("");
        Tpenyebab.setText("");
        Valid.tabelKosong(tabMode2);
        
        if (cmbRiwUlkus.getSelectedIndex() == 1) {
            Ttahun.setEnabled(true);
            Tlokasi.setEnabled(true);
            Tpenyebab.setEnabled(true);
            
            BtnTambahLuka.setEnabled(true);
            BtnSimpanLuka.setEnabled(true);
            BtnHapusLuka.setEnabled(true);
            BtnGantiLuka.setEnabled(true);
            Ttahun.requestFocus();
        } else {
            Ttahun.setEnabled(false);
            Tlokasi.setEnabled(false);
            Tpenyebab.setEnabled(false);
            
            BtnTambahLuka.setEnabled(false);
            BtnSimpanLuka.setEnabled(false);
            BtnHapusLuka.setEnabled(false);
            BtnGantiLuka.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRiwUlkusActionPerformed

    private void cmbObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbObatActionPerformed
        Tjenis.requestFocus();
    }//GEN-LAST:event_cmbObatActionPerformed

    private void cmbLokasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLokasiActionPerformed
        TdeforKanan.requestFocus();
    }//GEN-LAST:event_cmbLokasiActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMStatusKakiDiabetes dialog = new RMStatusKakiDiabetes(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBMI;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnGantiDefor;
    private widget.Button BtnGantiLuka;
    private widget.Button BtnGantiMikro;
    private widget.Button BtnGantiObat;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusDefor;
    private widget.Button BtnHapusLuka;
    private widget.Button BtnHapusMikro;
    private widget.Button BtnHapusObat;
    private widget.Button BtnKeluar;
    private widget.Button BtnNotepad;
    private widget.Button BtnPasteHasil;
    private widget.Button BtnPerawat;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpanDefor;
    private widget.Button BtnSimpanLuka;
    private widget.Button BtnSimpanMikro;
    private widget.Button BtnSimpanObat;
    private widget.Button BtnTambahDefor;
    private widget.Button BtnTambahLuka;
    private widget.Button BtnTambahMikro;
    private widget.Button BtnTambahObat;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.InternalFrame FormData;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private usu.widget.glass.PanelGlass PanelWall;
    private usu.widget.glass.PanelGlass PanelWall1;
    private usu.widget.glass.PanelGlass PanelWall2;
    private usu.widget.glass.PanelGlass PanelWall3;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane ScrollTriase1;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Talamat;
    private widget.TextBox Tbakteri;
    private widget.TextBox Tbb;
    private widget.TextBox Tbmi;
    private widget.TextBox TdeforKanan;
    private widget.TextBox TdeforKiri;
    private widget.TextBox TdllSebutkanMekanik;
    private widget.TextBox TdllSebutkanTermis;
    private widget.TextArea TdorsalKanan;
    private widget.TextArea TdorsalKiri;
    private widget.TextBox Tdosis;
    private widget.TextBox TjariKanan;
    private widget.TextBox TjariKiri;
    private widget.TextBox Tjenis;
    private widget.TextBox Tjenkel;
    private widget.TextBox TkesEkg;
    private widget.TextBox TkesRonsen;
    private widget.TextBox TkesRonsenTorax;
    private widget.TextBox TketLainKanan;
    private widget.TextBox TketLainKiri;
    private widget.TextBox TlainModern;
    private widget.TextBox TlainSebutkan;
    private widget.TextBox Tlama;
    private widget.TextBox TlamaDiketahui;
    private widget.TextBox TlamaLuka;
    private widget.TextBox TlamaRawat;
    private widget.TextBox TlaserTahun;
    private widget.TextBox TlokRonsen;
    private widget.TextBox Tlokasi;
    private widget.TextBox TmerokokMantan;
    private widget.TextBox TmerokokYa;
    private widget.TextBox TnmDokter;
    private widget.TextBox TnmPerawat;
    private widget.TextBox TnoTelp;
    private widget.TextArea TpemeriksaanLab;
    private widget.TextBox Tpenyebab;
    private widget.TextArea TplantarKanan;
    private widget.TextArea TplantarKiri;
    private widget.TextBox Tpnd;
    private widget.TextBox Tresisten;
    private widget.TextBox Tsensitif;
    private widget.TextBox Tsepatu;
    private widget.TextBox TskorAbi;
    private widget.TextBox Tsuku;
    private widget.TextBox Ttahun;
    private widget.TextBox Ttb;
    private widget.TextBox TtdsBra;
    private widget.TextBox TtdsDor;
    private widget.TextBox Ttensi;
    private widget.TextBox TterkenaZat;
    private widget.Tanggal TtglMasuk;
    private widget.Tanggal TtglRonsen;
    private widget.Tanggal TtglSimpan;
    private widget.TextBox TtipeDiabetLain;
    private widget.TextBox TtransKanan;
    private widget.TextBox TtransKiri;
    private widget.TextBox TusgDopler;
    private widget.TextBox Tusia;
    public widget.CekBox chkAlgin;
    public widget.CekBox chkBiology;
    public widget.CekBox chkCadex;
    public widget.CekBox chkChemical;
    public widget.CekBox chkDerajat0;
    public widget.CekBox chkDerajat1;
    public widget.CekBox chkDerajat2;
    public widget.CekBox chkDerajat3;
    public widget.CekBox chkDerajat4;
    public widget.CekBox chkDerajat5;
    public widget.CekBox chkDllsebutkanMekanik;
    public widget.CekBox chkDllsebutkanTermis;
    public widget.CekBox chkFoam;
    public widget.CekBox chkGinjal;
    public widget.CekBox chkHipertensi;
    public widget.CekBox chkHydro;
    public widget.CekBox chkLainLain;
    public widget.CekBox chkLainModern;
    public widget.CekBox chkMadu;
    public widget.CekBox chkMata;
    public widget.CekBox chkMemakaiSepatu;
    public widget.CekBox chkNonUlkus;
    public widget.CekBox chkPad;
    public widget.CekBox chkPenyJantung;
    private widget.CekBox chkSaya;
    public widget.CekBox chkSelulitis;
    public widget.CekBox chkSilver;
    public widget.CekBox chkSpontan;
    public widget.CekBox chkStrok;
    public widget.CekBox chkSurgical;
    public widget.CekBox chkTerkenaAir;
    public widget.CekBox chkTerkenaPemanas;
    public widget.CekBox chkTerkenaZat;
    public widget.CekBox chkTersandung;
    public widget.CekBox chkTertusuk;
    public widget.CekBox chkTglRonsen;
    public widget.CekBox chkTraumaKimia;
    public widget.CekBox chkTraumaMekanik;
    public widget.CekBox chkTraumaTermis;
    public widget.CekBox chkUlkus;
    public widget.CekBox chkUlkusGangen;
    private widget.ComboBox cmbDorsalisPedKanan;
    private widget.ComboBox cmbDorsalisPedKiri;
    private widget.ComboBox cmbGarKanan;
    private widget.ComboBox cmbGarKiri;
    private widget.ComboBox cmbGinjal;
    private widget.ComboBox cmbJarKananClaw;
    private widget.ComboBox cmbJarKananHamer;
    private widget.ComboBox cmbJarKananHiper;
    private widget.ComboBox cmbJarKananLain;
    private widget.ComboBox cmbJarKananMas;
    private widget.ComboBox cmbJarKiriClaw;
    private widget.ComboBox cmbJarKiriHamer;
    private widget.ComboBox cmbJarKiriHiper;
    private widget.ComboBox cmbJarKiriLain;
    private widget.ComboBox cmbJarKiriMas;
    private widget.ComboBox cmbJnsAlas;
    private widget.ComboBox cmbJnsRawat;
    private widget.ComboBox cmbKukKananAtrofi;
    private widget.ComboBox cmbKukKananInfeksi;
    private widget.ComboBox cmbKukKananIngro;
    private widget.ComboBox cmbKukKananLain;
    private widget.ComboBox cmbKukKananMenebal;
    private widget.ComboBox cmbKukKananPerubahan;
    private widget.ComboBox cmbKukKananRapuh;
    private widget.ComboBox cmbKukKiriAtrofi;
    private widget.ComboBox cmbKukKiriInfeksi;
    private widget.ComboBox cmbKukKiriIngro;
    private widget.ComboBox cmbKukKiriLain;
    private widget.ComboBox cmbKukKiriMenebal;
    private widget.ComboBox cmbKukKiriPerubahan;
    private widget.ComboBox cmbKukKiriRapuh;
    private widget.ComboBox cmbKulKananBulu;
    private widget.ComboBox cmbKulKananEdema;
    private widget.ComboBox cmbKulKananHealed;
    private widget.ComboBox cmbKulKananHiper;
    private widget.ComboBox cmbKulKananKalus;
    private widget.ComboBox cmbKulKananKering;
    private widget.ComboBox cmbKulKananKorn;
    private widget.ComboBox cmbKulKananTinea;
    private widget.ComboBox cmbKulKananTumit;
    private widget.ComboBox cmbKulKiriBulu;
    private widget.ComboBox cmbKulKiriEdema;
    private widget.ComboBox cmbKulKiriHealed;
    private widget.ComboBox cmbKulKiriHiper;
    private widget.ComboBox cmbKulKiriKalus;
    private widget.ComboBox cmbKulKiriKering;
    private widget.ComboBox cmbKulKiriKorn;
    private widget.ComboBox cmbKulKiriTinea;
    private widget.ComboBox cmbKulKiriTumit;
    private widget.ComboBox cmbLokasi;
    private widget.ComboBox cmbMata;
    private widget.ComboBox cmbMerokok;
    private widget.ComboBox cmbMonoKanan;
    private widget.ComboBox cmbMonoKiri;
    private widget.ComboBox cmbObat;
    private widget.ComboBox cmbOsteo;
    private widget.ComboBox cmbRefKanan;
    private widget.ComboBox cmbRefKiri;
    private widget.ComboBox cmbRiwAmputasiKanan;
    private widget.ComboBox cmbRiwAmputasiKiri;
    private widget.ComboBox cmbRiwEdukasi;
    private widget.ComboBox cmbRiwUlkus;
    private widget.ComboBox cmbSatuan;
    private widget.ComboBox cmbTelKananChar;
    private widget.ComboBox cmbTelKananHallu;
    private widget.ComboBox cmbTelKananPel;
    private widget.ComboBox cmbTelKiriChar;
    private widget.ComboBox cmbTelKiriHallu;
    private widget.ComboBox cmbTelKiriPel;
    private widget.ComboBox cmbTibialisKanan;
    private widget.ComboBox cmbTibialisKiri;
    private widget.ComboBox cmbTipeDiabet;
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
    private widget.Label jLabel8;
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
    private widget.Label jLabel9;
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
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.ScrollPane scrollPane18;
    private widget.Table tbDeformitas;
    private widget.Table tbMikro;
    private widget.Table tbPasien;
    private widget.Table tbRiwLuka;
    private widget.Table tbRiwPengobatan;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT dd.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                    + "date_format(dd.tgl_masuk,'%d-%m-%Y') tglMsk, pg1.nama nmPerawat, pg2.nama nmDokter FROM data_dasar_kaki_diabetes dd "
                    + "inner join reg_periksa rp on rp.no_rawat=dd.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg1 on pg1.nik=dd.nip_perawat inner join pegawai pg2 on pg2.nik=dd.nip_dokter where "
                    + "dd.tgl_data between ? and ? and dd.no_rawat like ? or "
                    + "dd.tgl_data between ? and ? and p.no_rkm_medis like ? or "
                    + "dd.tgl_data between ? and ? and p.nm_pasien like ? or "
                    + "dd.tgl_data between ? and ? and dd.jns_rawat like ? or "
                    + "dd.tgl_data between ? and ? and dd.tipe_diabetes like ? or "
                    + "dd.tgl_data between ? and ? and pg1.nama like ? or "
                    + "dd.tgl_data between ? and ? and pg2.nama like ? order by dd.waktu_simpan desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jenkel"),
                        rs.getString("tglLahir"),                        
                        rs.getString("jns_rawat"),                        
                        rs.getString("tglMsk"),                        
                        rs.getString("lama_rawat"),
                        rs.getString("tipe_diabetes"),
                        rs.getString("nmPerawat"),
                        rs.getString("nmDokter"),
                        rs.getString("jns_rawat"),
                        rs.getString("tb"),
                        rs.getString("bb"),
                        rs.getString("bmi"),
                        rs.getString("tensi"),
                        rs.getString("tgl_masuk"),
                        rs.getString("lama_rawat"),
                        rs.getString("tipe_diabetes"),
                        rs.getString("tipe_diabet_lain"),
                        rs.getString("lama_diketahui"),
                        rs.getString("merokok"),
                        rs.getString("merokok_ya"),
                        rs.getString("merokok_mantan"),
                        rs.getString("lama_luka"),
                        rs.getString("satuan_lama_luka"),
                        rs.getString("riwayat_edukasi"),
                        rs.getString("jns_alas_kaki"),
                        rs.getString("jns_alas_kaki_sepatu"),
                        rs.getString("trauma_mekanik"),
                        rs.getString("trauma_kimia"),
                        rs.getString("trauma_termis"),
                        rs.getString("spontan"),
                        rs.getString("penyebab_lain"),
                        rs.getString("ket_penyebab_lain"),
                        rs.getString("tersandung"),
                        rs.getString("memakai_sepatu"),
                        rs.getString("tertusuk"),
                        rs.getString("dll_sebutkan_mekanik"),
                        rs.getString("ket_dll_sebutkan_mekanik"),
                        rs.getString("terkena_zat"),
                        rs.getString("ket_terkena_zat"),
                        rs.getString("terkena_air_panas"),
                        rs.getString("terkena_pemanas"),
                        rs.getString("dll_sebutkan_termis"),
                        rs.getString("ket_dll_sebutkan_termis"),
                        rs.getString("riwayat_ulkus"),                        
                        rs.getString("kiri"),
                        rs.getString("jari_kaki_kiri_ke"),
                        rs.getString("trans_kiri_tahun"),                        
                        rs.getString("kanan"),
                        rs.getString("jari_kaki_kanan_ke"),
                        rs.getString("trans_kanan_tahun"),
                        rs.getString("mata"),
                        rs.getString("ginjal"),
                        rs.getString("penyakit_jantung"),
                        rs.getString("hipertensi"),
                        rs.getString("strok"),
                        rs.getString("pad"),
                        rs.getString("riwayat_mata"),
                        rs.getString("terapi_mata_tahun"),
                        rs.getString("riwayat_ginjal"),
                        rs.getString("non_ulkus"),
                        rs.getString("ulkus"),
                        rs.getString("ulkus_gangen"),
                        rs.getString("selulitis"),                        
                        rs.getString("deskripsi_dorsal_kanan"),                        
                        rs.getString("deskripsi_plantar_kanan"),                        
                        rs.getString("deskripsi_plantar_kiri"),                        
                        rs.getString("deskripsi_dorsal_kiri"),
                        rs.getString("kulit_kanan_kering"),
                        rs.getString("kulit_kiri_kering"),
                        rs.getString("kulit_kanan_tumit"),
                        rs.getString("kulit_kiri_tumit"),
                        rs.getString("kulit_kanan_bulu"),
                        rs.getString("kulit_kiri_bulu"),
                        rs.getString("kulit_kanan_tinea"),
                        rs.getString("kulit_kiri_tinea"),
                        rs.getString("kulit_kanan_kalus"),
                        rs.getString("kulit_kiri_kalus"),
                        rs.getString("kulit_kanan_korn"),
                        rs.getString("kulit_kiri_korn"),
                        rs.getString("kulit_kanan_hiperpig"),
                        rs.getString("kulit_kiri_hiperpig"),
                        rs.getString("kulit_kanan_edema"),
                        rs.getString("kulit_kiri_edema"),
                        rs.getString("kulit_kanan_healed"),
                        rs.getString("kulit_kiri_healed"),
                        rs.getString("kuku_kanan_menebal"),
                        rs.getString("kuku_kiri_menebal"),
                        rs.getString("kuku_kanan_infeksi"),
                        rs.getString("kuku_kiri_infeksi"),
                        rs.getString("kuku_kanan_perubahan"),
                        rs.getString("kuku_kiri_perubahan"),
                        rs.getString("kuku_kanan_rapuh"),
                        rs.getString("kuku_kiri_rapuh"),
                        rs.getString("kuku_kanan_ingro"),
                        rs.getString("kuku_kiri_ingro"),
                        rs.getString("kuku_kanan_atrofi"),
                        rs.getString("kuku_kiri_atrofi"),
                        rs.getString("kuku_kanan_lain"),
                        rs.getString("kuku_kiri_lain"),
                        rs.getString("telapak_kanan_hallux"),
                        rs.getString("telapak_kiri_hallux"),
                        rs.getString("telapak_kanan_pel"),
                        rs.getString("telapak_kiri_pel"),
                        rs.getString("telapak_kanan_char"),
                        rs.getString("telapak_kiri_char"),
                        rs.getString("jari_kanan_hammer"),
                        rs.getString("jari_kiri_hammer"),
                        rs.getString("jari_kanan_claw"),
                        rs.getString("jari_kiri_claw"),
                        rs.getString("jari_kanan_hiper"),
                        rs.getString("jari_kiri_hiper"),
                        rs.getString("jari_kanan_maser"),
                        rs.getString("jari_kiri_maser"),
                        rs.getString("jari_kanan_lain"),
                        rs.getString("jari_kiri_lain"),
                        rs.getString("ket_jari_kanan_lain"),
                        rs.getString("ket_jari_kiri_lain"),
                        rs.getString("dorsalis_kaki_kanan"),
                        rs.getString("dorsalis_kaki_kiri"),
                        rs.getString("tibialis_kaki_kanan"),
                        rs.getString("tibialis_kaki_kiri"),
                        rs.getString("brachialis"),
                        rs.getString("dorsalis_pedis"),
                        rs.getString("skor_abi"),
                        rs.getString("monofilamen_kanan"),
                        rs.getString("monofilamen_kiri"),
                        rs.getString("garputala_kanan"),
                        rs.getString("garputala_kiri"),
                        rs.getString("reflex_kanan"),
                        rs.getString("reflex_kiri"),
                        rs.getString("derajat0"),
                        rs.getString("derajat1"),
                        rs.getString("derajat2"),
                        rs.getString("derajat3"),
                        rs.getString("derajat4"),
                        rs.getString("derajat5"),
                        rs.getString("pemeriksaan_lab"),
                        rs.getString("ronsen_kaki"),
                        rs.getString("ronsen_kaki_tgl"),
                        rs.getString("kesimpulan_ronsen"),
                        rs.getString("osteomielitis"),
                        rs.getString("lokasi"),
                        rs.getString("kes_ronsen_thorax"),
                        rs.getString("kes_ekg"),
                        rs.getString("usg_dopler"),
                        rs.getString("surgical"),
                        rs.getString("chemical"),
                        rs.getString("biology"),
                        rs.getString("hidrocol"),
                        rs.getString("foam"),
                        rs.getString("allginate"),
                        rs.getString("silver"),
                        rs.getString("cadexomer"),
                        rs.getString("madu"),
                        rs.getString("modern_dresing_lain"),
                        rs.getString("ket_modern_dresing_lain"),
                        rs.getString("tgl_data"),
                        rs.getString("nip_perawat"),
                        rs.getString("nip_dokter"),
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
        Tbb.setText("");
        Ttb.setText("");
        hitungBMI();
        Ttensi.setText("");
        TtglMasuk.setDate(new Date());
        cmbJnsRawat.setSelectedIndex(0);
        TlamaRawat.setText("");
        TlamaRawat.setEnabled(false);
        cmbTipeDiabet.setSelectedIndex(0);
        TtipeDiabetLain.setText("");
        TtipeDiabetLain.setEnabled(false);
        TlamaDiketahui.setText("");
        BtnTambahObatActionPerformed(null);
        cmbMerokok.setSelectedIndex(0);
        TmerokokYa.setText("");
        TmerokokMantan.setText("");
        TmerokokYa.setEnabled(false);
        TmerokokMantan.setEnabled(false);
        TlamaLuka.setText("");
        cmbSatuan.setSelectedIndex(0);
        cmbRiwEdukasi.setSelectedIndex(0);
        cmbJnsAlas.setSelectedIndex(0);
        Tsepatu.setText("");
        Tsepatu.setEnabled(false);
        
        chkTraumaMekanik.setSelected(false);
        chkTersandung.setSelected(false);
        chkMemakaiSepatu.setSelected(false);
        chkTertusuk.setSelected(false);
        chkDllsebutkanMekanik.setSelected(false);
        TdllSebutkanMekanik.setText("");
        chkTersandung.setEnabled(false);
        chkMemakaiSepatu.setEnabled(false);
        chkTertusuk.setEnabled(false);
        chkDllsebutkanMekanik.setEnabled(false);
        TdllSebutkanMekanik.setEnabled(false);
        
        chkTraumaKimia.setSelected(false);
        chkTerkenaZat.setSelected(false);
        TterkenaZat.setText("");
        chkTerkenaZat.setEnabled(false);
        TterkenaZat.setEnabled(false);
        
        chkTraumaTermis.setSelected(false);
        chkTerkenaAir.setSelected(false);
        chkTerkenaPemanas.setSelected(false);
        chkDllsebutkanTermis.setSelected(false);
        TdllSebutkanTermis.setText("");
        chkTerkenaAir.setEnabled(false);
        chkTerkenaPemanas.setEnabled(false);
        chkDllsebutkanTermis.setEnabled(false);
        TdllSebutkanTermis.setEnabled(false);
        
        chkSpontan.setSelected(false);
        chkLainLain.setSelected(false);
        TlainSebutkan.setText("");
        TlainSebutkan.setEnabled(false);
        
        cmbRiwUlkus.setSelectedIndex(0);
        Ttahun.setText("");
        Tlokasi.setText("");
        Tpenyebab.setText("");
        Ttahun.setEnabled(false);
        Tlokasi.setEnabled(false);
        Tpenyebab.setEnabled(false);
        BtnTambahLuka.setEnabled(false);
        BtnSimpanLuka.setEnabled(false);
        BtnHapusLuka.setEnabled(false);
        BtnGantiLuka.setEnabled(false);

        cmbRiwAmputasiKiri.setSelectedIndex(0);
        TjariKiri.setText("");
        TtransKiri.setText("");
        cmbRiwAmputasiKiri.setEnabled(true);
        TjariKiri.setEnabled(false);
        TtransKiri.setEnabled(false);
        
        cmbRiwAmputasiKanan.setSelectedIndex(0);
        TjariKanan.setText("");
        TtransKanan.setText("");
        cmbRiwAmputasiKanan.setEnabled(true);
        TjariKanan.setEnabled(false);
        TtransKanan.setEnabled(false);
        
        chkMata.setSelected(false);
        cmbMata.setSelectedIndex(0);
        TlaserTahun.setText("");
        cmbMata.setEnabled(false);
        TlaserTahun.setEnabled(false);
        
        chkGinjal.setSelected(false);
        cmbGinjal.setSelectedIndex(0);
        cmbGinjal.setEnabled(false);
        chkPenyJantung.setSelected(false);
        chkHipertensi.setSelected(false);
        chkStrok.setSelected(false);
        chkPad.setSelected(false);
        chkNonUlkus.setSelected(false);
        chkUlkus.setSelected(false);
        chkUlkusGangen.setSelected(false);
        chkSelulitis.setSelected(false);
        
        TdorsalKanan.setText("");
        TdorsalKanan.setEnabled(true);
        
        TplantarKanan.setText("");
        TplantarKanan.setEnabled(true);
        
        TdorsalKiri.setText("");
        TdorsalKiri.setEnabled(true);
        
        TplantarKiri.setText("");
        TplantarKiri.setEnabled(true);
        BtnTambahDeforActionPerformed(null);
        
        cmbKulKananKering.setSelectedIndex(0);
        cmbKulKananTumit.setSelectedIndex(0);
        cmbKulKananBulu.setSelectedIndex(0);
        cmbKulKananTinea.setSelectedIndex(0);
        cmbKulKananKalus.setSelectedIndex(0);
        cmbKulKananKorn.setSelectedIndex(0);
        cmbKulKananHiper.setSelectedIndex(0);
        cmbKulKananEdema.setSelectedIndex(0);
        cmbKulKananHealed.setSelectedIndex(0);
        cmbKulKiriKering.setSelectedIndex(0);
        cmbKulKiriTumit.setSelectedIndex(0);
        cmbKulKiriBulu.setSelectedIndex(0);
        cmbKulKiriTinea.setSelectedIndex(0);
        cmbKulKiriKalus.setSelectedIndex(0);
        cmbKulKiriKorn.setSelectedIndex(0);
        cmbKulKiriHiper.setSelectedIndex(0);
        cmbKulKiriEdema.setSelectedIndex(0);
        cmbKulKiriHealed.setSelectedIndex(0);
        
        cmbKukKananMenebal.setSelectedIndex(0);
        cmbKukKananInfeksi.setSelectedIndex(0);
        cmbKukKananPerubahan.setSelectedIndex(0);
        cmbKukKananRapuh.setSelectedIndex(0);
        cmbKukKananIngro.setSelectedIndex(0);
        cmbKukKananAtrofi.setSelectedIndex(0);
        cmbKukKananLain.setSelectedIndex(0);
        cmbKukKiriMenebal.setSelectedIndex(0);
        cmbKukKiriInfeksi.setSelectedIndex(0);
        cmbKukKiriPerubahan.setSelectedIndex(0);
        cmbKukKiriRapuh.setSelectedIndex(0);
        cmbKukKiriIngro.setSelectedIndex(0);
        cmbKukKiriAtrofi.setSelectedIndex(0);
        cmbKukKiriLain.setSelectedIndex(0);
        
        cmbTelKananHallu.setSelectedIndex(0);
        cmbTelKananPel.setSelectedIndex(0);
        cmbTelKananChar.setSelectedIndex(0);
        cmbTelKiriHallu.setSelectedIndex(0);
        cmbTelKiriPel.setSelectedIndex(0);
        cmbTelKiriChar.setSelectedIndex(0);
        
        cmbJarKananHamer.setSelectedIndex(0);
        cmbJarKananClaw.setSelectedIndex(0);
        cmbJarKananHiper.setSelectedIndex(0);
        cmbJarKananMas.setSelectedIndex(0);
        cmbJarKananLain.setSelectedIndex(0);
        cmbJarKiriHamer.setSelectedIndex(0);
        cmbJarKiriClaw.setSelectedIndex(0);
        cmbJarKiriHiper.setSelectedIndex(0);
        cmbJarKiriMas.setSelectedIndex(0);
        cmbJarKiriLain.setSelectedIndex(0);
        TketLainKanan.setText("");
        TketLainKanan.setEnabled(false);
        TketLainKiri.setText("");
        TketLainKiri.setEnabled(false);
        
        cmbDorsalisPedKanan.setSelectedIndex(0);
        cmbDorsalisPedKiri.setSelectedIndex(0);
        cmbTibialisKanan.setSelectedIndex(0);
        cmbTibialisKiri.setSelectedIndex(0);
        TtdsBra.setText("");
        TtdsDor.setText("");
        TskorAbi.setText("");
        
        cmbMonoKanan.setSelectedIndex(0);
        cmbMonoKiri.setSelectedIndex(0);
        cmbGarKanan.setSelectedIndex(0);
        cmbGarKiri.setSelectedIndex(0);
        cmbRefKanan.setSelectedIndex(0);
        cmbRefKiri.setSelectedIndex(0);
        
        chkDerajat0.setSelected(false);
        chkDerajat1.setSelected(false);
        chkDerajat2.setSelected(false);
        chkDerajat3.setSelected(false);
        chkDerajat4.setSelected(false);
        chkDerajat5.setSelected(false);
        
        TpemeriksaanLab.setText("");
        chkTglRonsen.setSelected(false);
        TtglRonsen.setDate(new Date());
        TkesRonsen.setText("");
        cmbOsteo.setSelectedIndex(0);
        TlokRonsen.setText("");
        TtglRonsen.setEnabled(false);
        TkesRonsen.setEnabled(false);
        cmbOsteo.setEnabled(false);
        TlokRonsen.setEnabled(false);
        BtnTambahMikroActionPerformed(null);
        
        TkesRonsenTorax.setText("");
        TkesEkg.setText("");
        TusgDopler.setText("");        
        chkSurgical.setSelected(false);
        chkChemical.setSelected(false);
        chkBiology.setSelected(false);
        chkHydro.setSelected(false);
        chkFoam.setSelected(false);
        chkAlgin.setSelected(false);
        chkSilver.setSelected(false);
        chkCadex.setSelected(false);
        chkMadu.setSelected(false);
        chkLainModern.setSelected(false);
        TlainModern.setText("");
        TlainModern.setEnabled(false);
        TtglSimpan.setDate(new Date());
        chkSaya.setSelected(false);
        nipDokter = "-";
        TnmDokter.setText("-");
        
        Valid.tabelKosong(tabMode1);
        Valid.tabelKosong(tabMode2);
        Valid.tabelKosong(tabMode3);
        Valid.tabelKosong(tabMode4);
    }
    
    public void setData(String norwt, String norm, String stts) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        isPasien();
        Tusia.setText(Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur,'.') from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
        Valid.SetTgl(TtglMasuk, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(new Date());
        TCari.setText(norwt);

        if (stts.equals("ralan")) {
            cmbJnsRawat.setSelectedIndex(2);
            TlamaRawat.setEnabled(false);
        } else if (stts.equals("ranap")) {
            cmbJnsRawat.setSelectedIndex(1);
            TlamaRawat.setEnabled(true);
        } else {
            cmbJnsRawat.setSelectedIndex(0);
            TlamaRawat.setEnabled(false);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        BtnHapus.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        BtnPrint.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        
        if (akses.getjml2() >= 1) {
            nip = akses.getkode();            
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPerawat, nip);
            if (TnmPerawat.getText().equals("")) {
                nip = "-";
                TnmPerawat.setText("-");
            }
        }  
    }
    
    private void getData() {
        variabelBersih();
        BtnTambahObatActionPerformed(null);
        BtnTambahLukaActionPerformed(null);
        BtnTambahDeforActionPerformed(null);
        BtnTambahMikroActionPerformed(null);
        if (tbPasien.getSelectedRow() != -1) {
            TNoRw.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString());
            TNoRM.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 1).toString());
            TPasien.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 2).toString());
            Tjenkel.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 3).toString());
            Tusia.setText(Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur,'.') from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
            Tpnd.setText(Sequel.cariIsi("select pnd from pasien pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Talamat.setText(Sequel.cariIsi("SELECT concat(p.alamat,', Kel. ',kl.nm_kel,', Kec.',kc.nm_kec,', Kab. ',kb.nm_kab) FROM pasien p "
                    + "inner join kelurahan kl on kl.kd_kel=p.kd_kel inner join kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "inner join kabupaten kb on kb.kd_kab=p.kd_kab where p.no_rkm_medis='" + TNoRM.getText() + "'"));
            TnoTelp.setText(Sequel.cariIsi("select no_tlp from pasien pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Ttb.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 12).toString());
            Tbb.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 13).toString());
            Tbmi.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 14).toString());
            Ttensi.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 15).toString());
            Tsuku.setText(Sequel.cariIsi("select sb.nama_suku_bangsa from pasien p inner join suku_bangsa sb on sb.id=p.suku_bangsa "
                    + "where p.no_rkm_medis='" + TNoRM.getText() + "'"));            
            Valid.SetTgl(TtglMasuk, tbPasien.getValueAt(tbPasien.getSelectedRow(), 16).toString());
            cmbJnsRawat.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 11).toString());
            TlamaRawat.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 17).toString());
            cmbTipeDiabet.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 18).toString());
            TtipeDiabetLain.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 19).toString());
            TlamaDiketahui.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 20).toString());
            tampilRiwPengobatan();
            cmbMerokok.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 21).toString());
            TmerokokYa.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 22).toString());
            TmerokokMantan.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 23).toString());
            TlamaLuka.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 24).toString());
            cmbSatuan.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 25).toString());
            cmbRiwEdukasi.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 26).toString());
            cmbJnsAlas.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 27).toString());
            Tsepatu.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 28).toString());
            traumaMekanik = tbPasien.getValueAt(tbPasien.getSelectedRow(), 29).toString();
            traumaKimia = tbPasien.getValueAt(tbPasien.getSelectedRow(), 30).toString();
            traumaTermis = tbPasien.getValueAt(tbPasien.getSelectedRow(), 31).toString();
            spontan = tbPasien.getValueAt(tbPasien.getSelectedRow(), 32).toString();
            penyebabLain = tbPasien.getValueAt(tbPasien.getSelectedRow(), 33).toString();
            TlainSebutkan.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 34).toString());
            tersandung = tbPasien.getValueAt(tbPasien.getSelectedRow(), 35).toString();
            memakaiSepatu = tbPasien.getValueAt(tbPasien.getSelectedRow(), 36).toString();
            tertusuk = tbPasien.getValueAt(tbPasien.getSelectedRow(), 37).toString();
            dllSebutkanMekanik = tbPasien.getValueAt(tbPasien.getSelectedRow(), 38).toString();
            TdllSebutkanMekanik.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 39).toString());
            terkenaZat = tbPasien.getValueAt(tbPasien.getSelectedRow(), 40).toString();
            TterkenaZat.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 41).toString());
            terkenaAirPanas = tbPasien.getValueAt(tbPasien.getSelectedRow(), 42).toString();
            terkenaPemanas = tbPasien.getValueAt(tbPasien.getSelectedRow(), 43).toString();
            dllSebutkanTermis = tbPasien.getValueAt(tbPasien.getSelectedRow(), 44).toString();
            TdllSebutkanTermis.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 45).toString());
            cmbRiwUlkus.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 46).toString());
            tampilRiwLuka();
            cmbRiwAmputasiKiri.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 47).toString());
            TjariKiri.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 48).toString());
            TtransKiri.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 49).toString());            
            cmbRiwAmputasiKanan.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 50).toString());
            TjariKanan.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 51).toString());
            TtransKanan.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 52).toString());
            mata = tbPasien.getValueAt(tbPasien.getSelectedRow(), 53).toString();
            ginjal = tbPasien.getValueAt(tbPasien.getSelectedRow(), 54).toString();
            penyakitJantung = tbPasien.getValueAt(tbPasien.getSelectedRow(), 55).toString();
            hipertensi = tbPasien.getValueAt(tbPasien.getSelectedRow(), 56).toString();
            strok = tbPasien.getValueAt(tbPasien.getSelectedRow(), 57).toString();
            pad = tbPasien.getValueAt(tbPasien.getSelectedRow(), 58).toString();
            cmbMata.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 59).toString());
            TlaserTahun.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 60).toString());
            cmbGinjal.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 61).toString());
            nonUlkus = tbPasien.getValueAt(tbPasien.getSelectedRow(), 62).toString();
            ulkus = tbPasien.getValueAt(tbPasien.getSelectedRow(), 63).toString();
            ulkusGangen = tbPasien.getValueAt(tbPasien.getSelectedRow(), 64).toString();
            selulitis = tbPasien.getValueAt(tbPasien.getSelectedRow(), 65).toString();
            TdorsalKanan.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 66).toString());
            TplantarKanan.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 67).toString());
            TplantarKiri.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 68).toString());
            TdorsalKiri.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 69).toString());            
            tampilDeformitas();
            cmbKulKananKering.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 70).toString());
            cmbKulKiriKering.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 71).toString());
            cmbKulKananTumit.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 72).toString());
            cmbKulKiriTumit.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 73).toString());
            cmbKulKananBulu.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 74).toString());
            cmbKulKiriBulu.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 75).toString());
            cmbKulKananTinea.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 76).toString());
            cmbKulKiriTinea.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 77).toString());
            cmbKulKananKalus.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 78).toString());
            cmbKulKiriKalus.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 79).toString());
            cmbKulKananKorn.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 80).toString());
            cmbKulKiriKorn.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 81).toString());
            cmbKulKananHiper.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 82).toString());
            cmbKulKiriHiper.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 83).toString());
            cmbKulKananEdema.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 84).toString());
            cmbKulKiriEdema.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 85).toString());
            cmbKulKananHealed.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 86).toString());
            cmbKulKiriHealed.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 87).toString());            
            cmbKukKananMenebal.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 88).toString());
            cmbKukKiriMenebal.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 89).toString());
            cmbKukKananInfeksi.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 90).toString());
            cmbKukKiriInfeksi.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 91).toString());
            cmbKukKananPerubahan.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 92).toString());
            cmbKukKiriPerubahan.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 93).toString());
            cmbKukKananRapuh.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 94).toString());
            cmbKukKiriRapuh.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 95).toString());
            cmbKukKananIngro.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 96).toString());
            cmbKukKiriIngro.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 97).toString());
            cmbKukKananAtrofi.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 98).toString());
            cmbKukKiriAtrofi.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 99).toString());
            cmbKukKananLain.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 100).toString());
            cmbKukKiriLain.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(),101).toString());
            cmbTelKananHallu.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 102).toString());
            cmbTelKiriHallu.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 103).toString());
            cmbTelKananPel.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 104).toString());
            cmbTelKiriPel.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 105).toString());
            cmbTelKananChar.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 106).toString());
            cmbTelKiriChar.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 107).toString());
            cmbJarKananHamer.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 108).toString());
            cmbJarKiriHamer.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 109).toString());
            cmbJarKananClaw.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 110).toString());
            cmbJarKiriClaw.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 111).toString());
            cmbJarKananHiper.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 112).toString());
            cmbJarKiriHiper.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 113).toString());
            cmbJarKananMas.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 114).toString());
            cmbJarKiriMas.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 115).toString());
            cmbJarKananLain.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 116).toString());
            cmbJarKiriLain.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 117).toString());
            TketLainKanan.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 118).toString());
            TketLainKiri.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 119).toString());            
            cmbDorsalisPedKanan.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 120).toString());
            cmbDorsalisPedKiri.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 121).toString());
            cmbTibialisKanan.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 122).toString());
            cmbTibialisKiri.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 123).toString());
            TtdsBra.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 124).toString());
            TtdsDor.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 125).toString());
            TskorAbi.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 126).toString());
            cmbMonoKanan.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 127).toString());
            cmbMonoKiri.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 128).toString());
            cmbGarKanan.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 129).toString());
            cmbGarKiri.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 130).toString());
            cmbRefKanan.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 131).toString());
            cmbRefKiri.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 132).toString());
            derajat0 = tbPasien.getValueAt(tbPasien.getSelectedRow(), 133).toString();            
            derajat1 = tbPasien.getValueAt(tbPasien.getSelectedRow(), 134).toString();
            derajat2 = tbPasien.getValueAt(tbPasien.getSelectedRow(), 135).toString();
            derajat3 = tbPasien.getValueAt(tbPasien.getSelectedRow(), 136).toString();
            derajat4 = tbPasien.getValueAt(tbPasien.getSelectedRow(), 137).toString();
            derajat5 = tbPasien.getValueAt(tbPasien.getSelectedRow(), 138).toString();
            TpemeriksaanLab.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 139).toString());
            ronsenKaki = tbPasien.getValueAt(tbPasien.getSelectedRow(), 140).toString();
            Valid.SetTgl(TtglRonsen, tbPasien.getValueAt(tbPasien.getSelectedRow(), 141).toString());
            TkesRonsen.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 142).toString());
            cmbOsteo.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 143).toString());
            TlokRonsen.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 144).toString());
            tampilMikrobiologi();            
            TkesRonsenTorax.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 145).toString());
            TkesEkg.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 146).toString());
            TusgDopler.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 147).toString());
            surgical = tbPasien.getValueAt(tbPasien.getSelectedRow(), 148).toString();
            chemical = tbPasien.getValueAt(tbPasien.getSelectedRow(), 149).toString();
            biology = tbPasien.getValueAt(tbPasien.getSelectedRow(), 150).toString();
            hidrocol = tbPasien.getValueAt(tbPasien.getSelectedRow(), 151).toString();
            foam = tbPasien.getValueAt(tbPasien.getSelectedRow(), 152).toString();
            allginate = tbPasien.getValueAt(tbPasien.getSelectedRow(), 153).toString();
            silver = tbPasien.getValueAt(tbPasien.getSelectedRow(), 154).toString();
            cadexomer = tbPasien.getValueAt(tbPasien.getSelectedRow(), 155).toString();
            madu = tbPasien.getValueAt(tbPasien.getSelectedRow(), 156).toString();
            modernDresingLain = tbPasien.getValueAt(tbPasien.getSelectedRow(), 157).toString();
            TlainModern.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 158).toString());
            Valid.SetTgl(TtglSimpan, tbPasien.getValueAt(tbPasien.getSelectedRow(), 159).toString());
            nip = tbPasien.getValueAt(tbPasien.getSelectedRow(), 160).toString();
            nipDokter = tbPasien.getValueAt(tbPasien.getSelectedRow(), 161).toString();
            TnmPerawat.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 9).toString());
            TnmDokter.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 10).toString());            
            dataCek();
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from data_dasar_kaki_diabetes where no_rawat=?", 1, new String[]{
                tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString()
            }) == true) {
                Sequel.meghapus("riwayat_pengobatan_kaki_diabetes", "no_rawat", tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString());
                Sequel.meghapus("riwayat_ulkus_kaki_diabetes", "no_rawat", tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString());
                Sequel.meghapus("deformitas_kaki_diabetes", "no_rawat", tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString());
                Sequel.meghapus("mikrobiologi_kaki_diabetes", "no_rawat", tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString());
                
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
        if (Sequel.mengedittf("data_dasar_kaki_diabetes", "no_rawat=?", "jns_rawat=?, tb=?, bb=?, bmi=?, tensi=?, tgl_masuk=?, lama_rawat=?, tipe_diabetes=?, "
                + "tipe_diabet_lain=?, lama_diketahui=?, merokok=?, merokok_ya=?, merokok_mantan=?, lama_luka=?, satuan_lama_luka=?, riwayat_edukasi=?, jns_alas_kaki=?, "
                + "jns_alas_kaki_sepatu=?, trauma_mekanik=?, trauma_kimia=?, trauma_termis=?, spontan=?, penyebab_lain=?, ket_penyebab_lain=?, tersandung=?, memakai_sepatu=?, "
                + "tertusuk=?, dll_sebutkan_mekanik=?, ket_dll_sebutkan_mekanik=?, terkena_zat=?, ket_terkena_zat=?, terkena_air_panas=?, terkena_pemanas=?, dll_sebutkan_termis=?, "
                + "ket_dll_sebutkan_termis=?, riwayat_ulkus=?, kiri=?, jari_kaki_kiri_ke=?, trans_kiri_tahun=?, kanan=?, jari_kaki_kanan_ke=?, trans_kanan_tahun=?, mata=?, ginjal=?, "
                + "penyakit_jantung=?, hipertensi=?, strok=?, pad=?, riwayat_mata=?, terapi_mata_tahun=?, riwayat_ginjal=?, non_ulkus=?, ulkus=?, ulkus_gangen=?, selulitis=?, "
                + "deskripsi_dorsal_kanan=?, deskripsi_plantar_kanan=?, deskripsi_plantar_kiri=?, deskripsi_dorsal_kiri=?, kulit_kanan_kering=?, kulit_kiri_kering=?, kulit_kanan_tumit=?, "
                + "kulit_kiri_tumit=?, kulit_kanan_bulu=?, kulit_kiri_bulu=?, kulit_kanan_tinea=?, kulit_kiri_tinea=?, kulit_kanan_kalus=?, kulit_kiri_kalus=?, kulit_kanan_korn=?, "
                + "kulit_kiri_korn=?, kulit_kanan_hiperpig=?, kulit_kiri_hiperpig=?, kulit_kanan_edema=?, kulit_kiri_edema=?, kulit_kanan_healed=?, kulit_kiri_healed=?, kuku_kanan_menebal=?, "
                + "kuku_kiri_menebal=?, kuku_kanan_infeksi=?, kuku_kiri_infeksi=?, kuku_kanan_perubahan=?, kuku_kiri_perubahan=?, kuku_kanan_rapuh=?, kuku_kiri_rapuh=?, kuku_kanan_ingro=?, "
                + "kuku_kiri_ingro=?, kuku_kanan_atrofi=?, kuku_kiri_atrofi=?, kuku_kanan_lain=?, kuku_kiri_lain=?, telapak_kanan_hallux=?, telapak_kiri_hallux=?, telapak_kanan_pel=?, "
                + "telapak_kiri_pel=?, telapak_kanan_char=?, telapak_kiri_char=?, jari_kanan_hammer=?, jari_kiri_hammer=?, jari_kanan_claw=?, jari_kiri_claw=?, jari_kanan_hiper=?, "
                + "jari_kiri_hiper=?, jari_kanan_maser=?, jari_kiri_maser=?, jari_kanan_lain=?, jari_kiri_lain=?, ket_jari_kanan_lain=?, ket_jari_kiri_lain=?, dorsalis_kaki_kanan=?, "
                + "dorsalis_kaki_kiri=?, tibialis_kaki_kanan=?, tibialis_kaki_kiri=?, brachialis=?, dorsalis_pedis=?, skor_abi=?, monofilamen_kanan=?, monofilamen_kiri=?, garputala_kanan=?, "
                + "garputala_kiri=?, reflex_kanan=?, reflex_kiri=?, derajat0=?, derajat1=?, derajat2=?, derajat3=?, derajat4=?, derajat5=?, pemeriksaan_lab=?, ronsen_kaki=?, "
                + "ronsen_kaki_tgl=?, kesimpulan_ronsen=?, osteomielitis=?, lokasi=?, kes_ronsen_thorax=?, kes_ekg=?, usg_dopler=?, surgical=?, chemical=?, biology=?, hidrocol=?, foam=?, "
                + "allginate=?, silver=?, cadexomer=?, madu=?, modern_dresing_lain=?, ket_modern_dresing_lain=?, tgl_data=?, nip_perawat=?, nip_dokter=?", 152, new String[]{
                    cmbJnsRawat.getSelectedItem().toString(), Ttb.getText(), Tbb.getText(), Tbmi.getText(), Ttensi.getText(),
                    Valid.SetTgl(TtglMasuk.getSelectedItem() + ""), TlamaRawat.getText(), cmbTipeDiabet.getSelectedItem().toString(), TtipeDiabetLain.getText(),
                    TlamaDiketahui.getText(), cmbMerokok.getSelectedItem().toString(), TmerokokYa.getText(), TmerokokMantan.getText(), TlamaLuka.getText(),
                    cmbSatuan.getSelectedItem().toString(), cmbRiwEdukasi.getSelectedItem().toString(), cmbJnsAlas.getSelectedItem().toString(), Tsepatu.getText(),
                    traumaMekanik, traumaKimia, traumaTermis, spontan, penyebabLain, TlainSebutkan.getText(), tersandung, memakaiSepatu, tertusuk, dllSebutkanMekanik,
                    TdllSebutkanMekanik.getText(), terkenaZat, TterkenaZat.getText(), terkenaAirPanas, terkenaPemanas, dllSebutkanTermis, TdllSebutkanTermis.getText(),
                    cmbRiwUlkus.getSelectedItem().toString(), cmbRiwAmputasiKiri.getSelectedItem().toString(), TjariKiri.getText(), TtransKiri.getText(),
                    cmbRiwAmputasiKanan.getSelectedItem().toString(), TjariKanan.getText(), TtransKanan.getText(), mata, ginjal, penyakitJantung,
                    hipertensi, strok, pad, cmbMata.getSelectedItem().toString(), TlaserTahun.getText(), cmbGinjal.getSelectedItem().toString(), nonUlkus, ulkus,
                    ulkusGangen, selulitis, TdorsalKanan.getText(), TplantarKanan.getText(), TplantarKiri.getText(), TdorsalKiri.getText(),
                    cmbKulKananKering.getSelectedItem().toString(), cmbKulKiriKering.getSelectedItem().toString(), cmbKulKananTumit.getSelectedItem().toString(),
                    cmbKulKiriTumit.getSelectedItem().toString(), cmbKulKananBulu.getSelectedItem().toString(), cmbKulKiriBulu.getSelectedItem().toString(),
                    cmbKulKananTinea.getSelectedItem().toString(), cmbKulKiriTinea.getSelectedItem().toString(), cmbKulKananKalus.getSelectedItem().toString(),
                    cmbKulKiriKalus.getSelectedItem().toString(), cmbKulKananKorn.getSelectedItem().toString(), cmbKulKiriKorn.getSelectedItem().toString(),
                    cmbKulKananHiper.getSelectedItem().toString(), cmbKulKiriHiper.getSelectedItem().toString(), cmbKulKananEdema.getSelectedItem().toString(),
                    cmbKulKiriEdema.getSelectedItem().toString(), cmbKulKananHealed.getSelectedItem().toString(), cmbKulKiriHealed.getSelectedItem().toString(),
                    cmbKukKananMenebal.getSelectedItem().toString(), cmbKukKiriMenebal.getSelectedItem().toString(), cmbKukKananInfeksi.getSelectedItem().toString(),
                    cmbKukKiriInfeksi.getSelectedItem().toString(), cmbKukKananPerubahan.getSelectedItem().toString(), cmbKukKiriPerubahan.getSelectedItem().toString(),
                    cmbKukKananRapuh.getSelectedItem().toString(), cmbKukKiriRapuh.getSelectedItem().toString(), cmbKukKananIngro.getSelectedItem().toString(),
                    cmbKukKiriIngro.getSelectedItem().toString(), cmbKukKananAtrofi.getSelectedItem().toString(), cmbKukKiriAtrofi.getSelectedItem().toString(),
                    cmbKukKananLain.getSelectedItem().toString(), cmbKukKiriLain.getSelectedItem().toString(), cmbTelKananHallu.getSelectedItem().toString(),
                    cmbTelKiriHallu.getSelectedItem().toString(), cmbTelKananPel.getSelectedItem().toString(), cmbTelKiriPel.getSelectedItem().toString(),
                    cmbTelKananChar.getSelectedItem().toString(), cmbTelKiriChar.getSelectedItem().toString(), cmbJarKananHamer.getSelectedItem().toString(),
                    cmbJarKiriHamer.getSelectedItem().toString(), cmbJarKananClaw.getSelectedItem().toString(), cmbJarKiriClaw.getSelectedItem().toString(),
                    cmbJarKananHiper.getSelectedItem().toString(), cmbJarKiriHiper.getSelectedItem().toString(), cmbJarKananMas.getSelectedItem().toString(),
                    cmbJarKiriMas.getSelectedItem().toString(), cmbJarKananLain.getSelectedItem().toString(), cmbJarKiriLain.getSelectedItem().toString(),
                    TketLainKanan.getText(), TketLainKiri.getText(), cmbDorsalisPedKanan.getSelectedItem().toString(), cmbDorsalisPedKiri.getSelectedItem().toString(),
                    cmbTibialisKanan.getSelectedItem().toString(), cmbTibialisKiri.getSelectedItem().toString(), TtdsBra.getText(), TtdsDor.getText(), TskorAbi.getText(),
                    cmbMonoKanan.getSelectedItem().toString(), cmbMonoKiri.getSelectedItem().toString(), cmbGarKanan.getSelectedItem().toString(),
                    cmbGarKiri.getSelectedItem().toString(), cmbRefKanan.getSelectedItem().toString(), cmbRefKiri.getSelectedItem().toString(), derajat0, derajat1,
                    derajat2, derajat3, derajat4, derajat5, TpemeriksaanLab.getText(), ronsenKaki, Valid.SetTgl(TtglRonsen.getSelectedItem() + ""), TkesRonsen.getText(),
                    cmbOsteo.getSelectedItem().toString(), TlokRonsen.getText(), TkesRonsenTorax.getText(), TkesEkg.getText(), TusgDopler.getText(), surgical, chemical,
                    biology, hidrocol, foam, allginate, silver, cadexomer, madu, modernDresingLain, TlainModern.getText(), Valid.SetTgl(TtglSimpan.getSelectedItem() + ""),
                    nip, nipDokter,
                    tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString()
                }) == true) {

            Sequel.meghapus("riwayat_pengobatan_kaki_diabetes", "no_rawat", tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString());
            Sequel.meghapus("riwayat_ulkus_kaki_diabetes", "no_rawat", tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString());
            Sequel.meghapus("deformitas_kaki_diabetes", "no_rawat", tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString());
            Sequel.meghapus("mikrobiologi_kaki_diabetes", "no_rawat", tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString());

            if (tbRiwPengobatan.getRowCount() != 0) {
                for (i = 0; i < tbRiwPengobatan.getRowCount(); i++) {
                    Sequel.menyimpanIgnore("riwayat_pengobatan_kaki_diabetes",
                            "'" + tbRiwPengobatan.getValueAt(i, 0).toString() + "',"
                            + "'" + tbRiwPengobatan.getValueAt(i, 1).toString() + "',"
                            + "'" + tbRiwPengobatan.getValueAt(i, 2).toString() + "',"
                            + "'" + tbRiwPengobatan.getValueAt(i, 3).toString() + "',"
                            + "'" + tbRiwPengobatan.getValueAt(i, 4).toString() + "',"
                            + "'" + tbRiwPengobatan.getValueAt(i, 5).toString() + "'", "Riwayat Pengobatan");
                }
            }

            if (tbRiwLuka.getRowCount() != 0) {
                for (i = 0; i < tbRiwLuka.getRowCount(); i++) {
                    Sequel.menyimpanIgnore("riwayat_ulkus_kaki_diabetes",
                            "'" + tbRiwLuka.getValueAt(i, 0).toString() + "',"
                            + "'" + tbRiwLuka.getValueAt(i, 1).toString() + "',"
                            + "'" + tbRiwLuka.getValueAt(i, 2).toString() + "',"
                            + "'" + tbRiwLuka.getValueAt(i, 3).toString() + "',"
                            + "'" + tbRiwLuka.getValueAt(i, 4).toString() + "'", "Riwayat Luka/Ulkus");
                }
            }

            if (tbDeformitas.getRowCount() != 0) {
                for (i = 0; i < tbDeformitas.getRowCount(); i++) {
                    Sequel.menyimpanIgnore("deformitas_kaki_diabetes",
                            "'" + tbDeformitas.getValueAt(i, 0).toString() + "',"
                            + "'" + tbDeformitas.getValueAt(i, 1).toString() + "',"
                            + "'" + tbDeformitas.getValueAt(i, 2).toString() + "',"
                            + "'" + tbDeformitas.getValueAt(i, 3).toString() + "',"
                            + "'" + tbDeformitas.getValueAt(i, 4).toString() + "'", "Deformitas");
                }
            }

            if (tbMikro.getRowCount() != 0) {
                for (i = 0; i < tbMikro.getRowCount(); i++) {
                    Sequel.menyimpanIgnore("mikrobiologi_kaki_diabetes",
                            "'" + tbMikro.getValueAt(i, 0).toString() + "',"
                            + "'" + tbMikro.getValueAt(i, 1).toString() + "',"
                            + "'" + tbMikro.getValueAt(i, 2).toString() + "',"
                            + "'" + tbMikro.getValueAt(i, 3).toString() + "',"
                            + "'" + tbMikro.getValueAt(i, 4).toString() + "'", "Mikrobiologi");
                }
            }

            Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Status Kaki Diabetes", "Ganti");
            TabRawat.setSelectedIndex(1);
            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
        }
    }
    
    private void cekData() {
        if (chkTraumaMekanik.isSelected() == true) {
            traumaMekanik = "ya";
        } else {
            traumaMekanik = "tidak";
        }
        
        if (chkTraumaKimia.isSelected() == true) {
            traumaKimia = "ya";
        } else {
            traumaKimia = "tidak";
        }
        
        if (chkTraumaTermis.isSelected() == true) {
            traumaTermis = "ya";
        } else {
            traumaTermis = "tidak";
        }
        
        if (chkSpontan.isSelected() == true) {
            spontan = "ya";
        } else {
            spontan = "tidak";
        }
        
        if (chkLainLain.isSelected() == true) {
            penyebabLain = "ya";
        } else {
            penyebabLain = "tidak";
        }
        
        if (chkTersandung.isSelected() == true) {
            tersandung = "ya";
        } else {
            tersandung = "tidak";
        }
        
        if (chkMemakaiSepatu.isSelected() == true) {
            memakaiSepatu = "ya";
        } else {
            memakaiSepatu = "tidak";
        }
        
        if (chkTertusuk.isSelected() == true) {
            tertusuk = "ya";
        } else {
            tertusuk = "tidak";
        }
        
        if (chkDllsebutkanMekanik.isSelected() == true) {
            dllSebutkanMekanik = "ya";
        } else {
            dllSebutkanMekanik = "tidak";
        }
        
        if (chkTerkenaZat.isSelected() == true) {
            terkenaZat = "ya";
        } else {
            terkenaZat = "tidak";
        }
        
        if (chkTerkenaAir.isSelected() == true) {
            terkenaAirPanas = "ya";
        } else {
            terkenaAirPanas = "tidak";
        }
        
        if (chkTerkenaPemanas.isSelected() == true) {
            terkenaPemanas = "ya";
        } else {
            terkenaPemanas = "tidak";
        }
        
        if (chkDllsebutkanTermis.isSelected() == true) {
            dllSebutkanTermis = "ya";
        } else {
            dllSebutkanTermis = "tidak";
        }
        
        if (chkMata.isSelected() == true) {
            mata = "ya";
        } else {
            mata = "tidak";
        }
        
        if (chkGinjal.isSelected() == true) {
            ginjal = "ya";
        } else {
            ginjal = "tidak";
        }
        
        if (chkPenyJantung.isSelected() == true) {
            penyakitJantung = "ya";
        } else {
            penyakitJantung = "tidak";
        }
        
        if (chkHipertensi.isSelected() == true) {
            hipertensi = "ya";
        } else {
            hipertensi = "tidak";
        }
        
        if (chkStrok.isSelected() == true) {
            strok = "ya";
        } else {
            strok = "tidak";
        }
        
        if (chkPad.isSelected() == true) {
            pad = "ya";
        } else {
            pad = "tidak";
        }
        
        if (chkNonUlkus.isSelected() == true) {
            nonUlkus = "ya";
        } else {
            nonUlkus = "tidak";
        }
        
        if (chkUlkus.isSelected() == true) {
            ulkus = "ya";
        } else {
            ulkus = "tidak";
        }
        
        if (chkUlkusGangen.isSelected() == true) {
            ulkusGangen = "ya";
        } else {
            ulkusGangen = "tidak";
        }
        
        if (chkSelulitis.isSelected() == true) {
            selulitis = "ya";
        } else {
            selulitis = "tidak";
        }
        
        if (chkDerajat0.isSelected() == true) {
            derajat0 = "ya";
        } else {
            derajat0 = "tidak";
        }
        
        if (chkDerajat1.isSelected() == true) {
            derajat1 = "ya";
        } else {
            derajat1 = "tidak";
        }
        
        if (chkDerajat2.isSelected() == true) {
            derajat2 = "ya";
        } else {
            derajat2 = "tidak";
        }
        
        if (chkDerajat3.isSelected() == true) {
            derajat3 = "ya";
        } else {
            derajat3 = "tidak";
        }
        
        if (chkDerajat4.isSelected() == true) {
            derajat4 = "ya";
        } else {
            derajat4 = "tidak";
        }
        
        if (chkDerajat5.isSelected() == true) {
            derajat5 = "ya";
        } else {
            derajat5 = "tidak";
        }
        
        if (chkTglRonsen.isSelected() == true) {
            ronsenKaki = "ya";
        } else {
            ronsenKaki = "tidak";
        }
        
        if (chkSurgical.isSelected() == true) {
            surgical = "ya";
        } else {
            surgical = "tidak";
        }
        
        if (chkChemical.isSelected() == true) {
            chemical = "ya";
        } else {
            chemical = "tidak";
        }
        
        if (chkBiology.isSelected() == true) {
            biology = "ya";
        } else {
            biology = "tidak";
        }
        
        if (chkHydro.isSelected() == true) {
            hidrocol = "ya";
        } else {
            hidrocol = "tidak";
        }
        
        if (chkFoam.isSelected() == true) {
            foam = "ya";
        } else {
            foam = "tidak";
        }
        
        if (chkAlgin.isSelected() == true) {
            allginate = "ya";
        } else {
            allginate = "tidak";
        }
        
        if (chkSilver.isSelected() == true) {
            silver = "ya";
        } else {
            silver = "tidak";
        }
        
        if (chkCadex.isSelected() == true) {
            cadexomer = "ya";
        } else {
            cadexomer = "tidak";
        }
        
        if (chkMadu.isSelected() == true) {
            madu = "ya";
        } else {
            madu = "tidak";
        }
        
        if (chkLainModern.isSelected() == true) {
            modernDresingLain = "ya";
        } else {
            modernDresingLain = "tidak";
        }
    }
    
    private void dataCek() {
        if (cmbJnsRawat.getSelectedIndex() == 1) {
            TlamaRawat.setEnabled(true);
        } else {
            TlamaRawat.setEnabled(false);
        }
        
        if (cmbTipeDiabet.getSelectedIndex() == 3) {
            TtipeDiabetLain.setEnabled(true);
        } else {
            TtipeDiabetLain.setEnabled(false);
        }
        
        if (cmbJnsAlas.getSelectedIndex() == 3) {
            Tsepatu.setEnabled(true);
        } else {
            Tsepatu.setEnabled(false);
        }
        
        if (traumaMekanik.equals("ya")) {
            chkTraumaMekanik.setSelected(true);
            chkTersandung.setEnabled(true);
            chkMemakaiSepatu.setEnabled(true);
            chkTertusuk.setEnabled(true);
            chkDllsebutkanMekanik.setEnabled(true);
        } else {
            chkTraumaMekanik.setSelected(false);
            chkTersandung.setEnabled(false);
            chkMemakaiSepatu.setEnabled(false);
            chkTertusuk.setEnabled(false);
            chkDllsebutkanMekanik.setEnabled(false);
        }
        
        if (tersandung.equals("ya")) {
            chkTersandung.setSelected(true);
        } else {
            chkTersandung.setSelected(false);
        }
        
        if (memakaiSepatu.equals("ya")) {
            chkMemakaiSepatu.setSelected(true);
        } else {
            chkMemakaiSepatu.setSelected(false);
        }
        
        if (tertusuk.equals("ya")) {
            chkTertusuk.setSelected(true);
        } else {
            chkTertusuk.setSelected(false);
        }
        
        if (dllSebutkanMekanik.equals("ya")) {
            chkDllsebutkanMekanik.setSelected(true);
            TdllSebutkanMekanik.setEnabled(true);
        } else {
            chkDllsebutkanMekanik.setSelected(false);
            TdllSebutkanMekanik.setEnabled(false);
        }
        
        if (traumaKimia.equals("ya")) {
            chkTraumaKimia.setSelected(true);
            chkTerkenaZat.setEnabled(true);
        } else {
            chkTraumaKimia.setSelected(false);
            chkTerkenaZat.setEnabled(false);
        }
        
        if (terkenaZat.equals("ya")) {
            chkTerkenaZat.setSelected(true);
            TterkenaZat.setEnabled(true);
        } else {
            chkTerkenaZat.setSelected(false);
            TterkenaZat.setEnabled(false);
        }
        
        if (traumaTermis.equals("ya")) {
            chkTraumaTermis.setSelected(true);
            chkTerkenaAir.setEnabled(true);
            chkTerkenaPemanas.setEnabled(true);
            chkDllsebutkanTermis.setEnabled(true);
        } else {
            chkTraumaTermis.setSelected(false);
            chkTerkenaAir.setEnabled(false);
            chkTerkenaPemanas.setEnabled(false);
            chkDllsebutkanTermis.setEnabled(false);
        }
        
        if (terkenaAirPanas.equals("ya")) {
            chkTerkenaAir.setSelected(true);
        } else {
            chkTerkenaAir.setSelected(false);
        }
        
        if (terkenaPemanas.equals("ya")) {
            chkTerkenaPemanas.setSelected(true);
        } else {
            chkTerkenaPemanas.setSelected(false);
        }
        
        if (dllSebutkanTermis.equals("ya")) {
            chkDllsebutkanTermis.setSelected(true);
            TdllSebutkanTermis.setEnabled(true);
        } else {
            chkDllsebutkanTermis.setSelected(false);
            TdllSebutkanTermis.setEnabled(false);
        }
        
        if (spontan.equals("ya")) {
            chkSpontan.setSelected(true);
        } else {
            chkSpontan.setSelected(false);
        }
        
        if (penyebabLain.equals("ya")) {
            chkLainLain.setSelected(true);
            TlainSebutkan.setEnabled(true);
        } else {
            chkLainLain.setSelected(false);
            TlainSebutkan.setEnabled(false);
        }
        
        if (cmbRiwUlkus.getSelectedIndex() == 1) {
            Ttahun.setEnabled(true);
            Tlokasi.setEnabled(true);
            Tpenyebab.setEnabled(true);
            
            BtnTambahLuka.setEnabled(true);
            BtnSimpanLuka.setEnabled(true);
            BtnHapusLuka.setEnabled(true);
            BtnGantiLuka.setEnabled(true);
        } else {
            Ttahun.setEnabled(false);
            Tlokasi.setEnabled(false);
            Tpenyebab.setEnabled(false);
            
            BtnTambahLuka.setEnabled(false);
            BtnSimpanLuka.setEnabled(false);
            BtnHapusLuka.setEnabled(false);
            BtnGantiLuka.setEnabled(false);
        }
        
        if (cmbRiwAmputasiKiri.getSelectedIndex() == 3) {
            TjariKiri.setEnabled(true);
            TtransKiri.setEnabled(false);
        } else if (cmbRiwAmputasiKiri.getSelectedIndex() == 4) {
            TjariKiri.setEnabled(false);
            TtransKiri.setEnabled(true);
        } else {
            TjariKiri.setEnabled(false);
            TtransKiri.setEnabled(false);
        }
        
        if (cmbRiwAmputasiKanan.getSelectedIndex() == 3) {
            TjariKanan.setEnabled(true);
            TtransKanan.setEnabled(false);
        } else if (cmbRiwAmputasiKanan.getSelectedIndex() == 4) {
            TjariKanan.setEnabled(false);
            TtransKanan.setEnabled(true);
        } else {
            TjariKanan.setEnabled(false);
            TtransKanan.setEnabled(false);
        }
        
        if (mata.equals("ya")) {
            chkMata.setSelected(true);
            cmbMata.setEnabled(true);            
        } else {
            chkMata.setSelected(false);
            cmbMata.setEnabled(false);
        }

        if (cmbMata.getSelectedIndex() == 4) {
            TlaserTahun.setEnabled(true);
        } else {
            TlaserTahun.setEnabled(false);
        }
        
        if (ginjal.equals("ya")) {
            chkGinjal.setSelected(true);
            cmbGinjal.setEnabled(true);
        } else {
            chkGinjal.setSelected(false);
            cmbGinjal.setEnabled(false);
        }
        
        if (penyakitJantung.equals("ya")) {
            chkPenyJantung.setSelected(true);
        } else {
            chkPenyJantung.setSelected(false);
        }
        
        if (hipertensi.equals("ya")) {
            chkHipertensi.setSelected(true);
        } else {
            chkHipertensi.setSelected(false);
        }
        
        if (strok.equals("ya")) {
            chkStrok.setSelected(true);
        } else {
            chkStrok.setSelected(false);
        }
        
        if (pad.equals("ya")) {
            chkPad.setSelected(true);
        } else {
            chkPad.setSelected(false);
        }
        
        if (nonUlkus.equals("ya")) {
            chkNonUlkus.setSelected(true);
        } else {
            chkNonUlkus.setSelected(false);
        }
        
        if (ulkus.equals("ya")) {
            chkUlkus.setSelected(true);
        } else {
            chkUlkus.setSelected(false);
        }
        
        if (ulkusGangen.equals("ya")) {
            chkUlkusGangen.setSelected(true);
        } else {
            chkUlkusGangen.setSelected(false);
        }
        
        if (selulitis.equals("ya")) {
            chkSelulitis.setSelected(true);
        } else {
            chkSelulitis.setSelected(false);
        }
        
        if (derajat0.equals("ya")) {
            chkDerajat0.setSelected(true);
        } else {
            chkDerajat0.setSelected(false);
        }
        
        if (derajat1.equals("ya")) {
            chkDerajat1.setSelected(true);
        } else {
            chkDerajat1.setSelected(false);
        }
        
        if (derajat2.equals("ya")) {
            chkDerajat2.setSelected(true);
        } else {
            chkDerajat2.setSelected(false);
        }
        
        if (derajat3.equals("ya")) {
            chkDerajat3.setSelected(true);
        } else {
            chkDerajat3.setSelected(false);
        }
        
        if (derajat4.equals("ya")) {
            chkDerajat4.setSelected(true);
        } else {
            chkDerajat4.setSelected(false);
        }
        
        if (derajat5.equals("ya")) {
            chkDerajat5.setSelected(true);
        } else {
            chkDerajat5.setSelected(false);
        }

        if (ronsenKaki.equals("ya")) {
            chkTglRonsen.setSelected(true);
            TtglRonsen.setEnabled(true);
            TkesRonsen.setEnabled(true);
            cmbOsteo.setEnabled(true);
            TlokRonsen.setEnabled(true);
        } else {
            chkTglRonsen.setSelected(false);
            TtglRonsen.setEnabled(false);
            TkesRonsen.setEnabled(false);
            cmbOsteo.setEnabled(false);
            TlokRonsen.setEnabled(false);
        }
        
        if (surgical.equals("ya")) {
            chkSurgical.setSelected(true);
        } else {
            chkSurgical.setSelected(false);
        }
        
        if (chemical.equals("ya")) {
            chkChemical.setSelected(true);
        } else {
            chkChemical.setSelected(false);
        }
        
        if (biology.equals("ya")) {
            chkBiology.setSelected(true);
        } else {
            chkBiology.setSelected(false);
        }
        
        if (hidrocol.equals("ya")) {
            chkHydro.setSelected(true);
        } else {
            chkHydro.setSelected(false);
        }
        
        if (foam.equals("ya")) {
            chkFoam.setSelected(true);
        } else {
            chkFoam.setSelected(false);
        }
        
        if (allginate.equals("ya")) {
            chkAlgin.setSelected(true);
        } else {
            chkAlgin.setSelected(false);
        }
        
        if (silver.equals("ya")) {
            chkSilver.setSelected(true);
        } else {
            chkSilver.setSelected(false);
        }
        
        if (cadexomer.equals("ya")) {
            chkCadex.setSelected(true);
        } else {
            chkCadex.setSelected(false);
        }
        
        if (madu.equals("ya")) {
            chkMadu.setSelected(true);
        } else {
            chkMadu.setSelected(false);
        }
        
        if (modernDresingLain.equals("ya")) {
            chkLainModern.setSelected(true);
            TlainModern.setEnabled(true);
        } else {
            chkLainModern.setSelected(false);
            TlainModern.setEnabled(false);
        }
    }
        
    private void variabelBersih() {
        nip = "";
        nipDokter = "";
        traumaMekanik = "";
        traumaKimia = "";
        traumaTermis = "";
        spontan = "";
        penyebabLain = "";
        tersandung = "";
        memakaiSepatu = "";
        tertusuk = "";
        dllSebutkanMekanik = "";
        terkenaZat = "";
        terkenaAirPanas = "";
        terkenaPemanas = "";
        dllSebutkanTermis = "";
        mata = "";
        ginjal = "";
        penyakitJantung = "";
        hipertensi = "";
        strok = "";
        pad = "";
        nonUlkus = "";
        ulkus = "";
        ulkusGangen = "";
        selulitis = "";
        derajat0 = "";
        derajat1 = "";
        derajat2 = "";
        derajat3 = "";
        derajat4 = "";
        derajat5 = "";
        ronsenKaki = "";
        surgical = "";
        chemical = "";
        biology = "";
        hidrocol = "";
        foam = "";
        allginate = "";
        silver = "";
        cadexomer = "";
        madu = "";
        modernDresingLain = "";
    }
    
    public void setTampil(){
       TabRawat.setSelectedIndex(1);
       tampil();
    }
    
    private void getDataObat() {
        if (tbRiwPengobatan.getSelectedRow() != -1) {
            cmbObat.setSelectedItem(tbRiwPengobatan.getValueAt(tbRiwPengobatan.getSelectedRow(), 1).toString());
            Tjenis.setText(tbRiwPengobatan.getValueAt(tbRiwPengobatan.getSelectedRow(), 2).toString());
            Tdosis.setText(tbRiwPengobatan.getValueAt(tbRiwPengobatan.getSelectedRow(), 3).toString());
            Tlama.setText(tbRiwPengobatan.getValueAt(tbRiwPengobatan.getSelectedRow(), 4).toString());
        }
    }
    
    private void getDataLuka() {
        if (tbRiwLuka.getSelectedRow() != -1) {
            Ttahun.setText(tbRiwLuka.getValueAt(tbRiwLuka.getSelectedRow(), 1).toString());
            Tlokasi.setText(tbRiwLuka.getValueAt(tbRiwLuka.getSelectedRow(), 2).toString());
            Tpenyebab.setText(tbRiwLuka.getValueAt(tbRiwLuka.getSelectedRow(), 3).toString());
        }
    }
    
    private void getDataDefor() {
        if (tbDeformitas.getSelectedRow() != -1) {
            cmbLokasi.setSelectedItem(tbDeformitas.getValueAt(tbDeformitas.getSelectedRow(), 1).toString());
            TdeforKanan.setText(tbDeformitas.getValueAt(tbDeformitas.getSelectedRow(), 2).toString());
            TdeforKiri.setText(tbDeformitas.getValueAt(tbDeformitas.getSelectedRow(), 3).toString());
        }
    }
    
    private void getDataMikro() {
        if (tbMikro.getSelectedRow() != -1) {
            Tbakteri.setText(tbMikro.getValueAt(tbMikro.getSelectedRow(), 1).toString());
            Tsensitif.setText(tbMikro.getValueAt(tbMikro.getSelectedRow(), 2).toString());
            Tresisten.setText(tbMikro.getValueAt(tbMikro.getSelectedRow(), 3).toString());
        }
    }

    private void hitungBMI() {
        try {
            double A = 0, B = 0, C = 0, D = 0, hasil = 0;
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
            //tinggi badan diubah ke meter (cm ke m = cm dibagi 100)
            C = B / 100;
            D = C * C;            
            hasil = A / D;
            
            if (Valid.SetAngka4(hasil).equals("NaN") || Valid.SetAngka4(hasil).equals("∞")
                    || Tbb.getText().equals("") || Ttb.getText().equals("")) {
                Tbmi.setText("0");
            } else {
                Tbmi.setText(Valid.SetAngka4(hasil));
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka berat badan & tinggi badannya,    \n"
                    + "jika menggunakan koma, gantilah tanda koma dengan titik sebagai komanya !!");
            Tbmi.setText("");
        }
    }
    
    private void isPasien() {
        try {
            ps1 = koneksi.prepareStatement("SELECT p.*, if(p.jk='L','Laki-laki','Perempuan') jenkel, "
                    + "concat(p.alamat,', Kel. ',kl.nm_kel,', Kec.',kc.nm_kec,', Kab. ',kb.nm_kab) alamatPx, sb.nama_suku_bangsa FROM pasien p "
                    + "inner join kelurahan kl on kl.kd_kel=p.kd_kel inner join kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "inner join kabupaten kb on kb.kd_kab=p.kd_kab inner join suku_bangsa sb on sb.id=p.suku_bangsa "
                    + "where p.no_rkm_medis='" + TNoRM.getText() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    TPasien.setText(rs1.getString("nm_pasien"));
                    Tjenkel.setText(rs1.getString("jenkel"));                    
                    Tpnd.setText(rs1.getString("pnd"));
                    Talamat.setText(rs1.getString("alamatPx"));
                    TnoTelp.setText(rs1.getString("no_tlp"));
                    Tsuku.setText(rs1.getString("nama_suku_bangsa"));
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
    
    private void tampilRiwPengobatan() {
        Valid.tabelKosong(tabMode1);
        try {
            ps2 = koneksi.prepareStatement("SELECT * FROM riwayat_pengobatan_kaki_diabetes where "
                    + "no_rawat='" + TNoRw.getText() + "' order by waktu_simpan");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode1.addRow(new String[]{
                        rs2.getString("no_rawat"),
                        rs2.getString("obat"),
                        rs2.getString("jenis"),
                        rs2.getString("dosis"),
                        rs2.getString("lama"),
                        rs2.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
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
    
    private void tampilRiwLuka() {
        Valid.tabelKosong(tabMode2);
        try {
            ps3 = koneksi.prepareStatement("SELECT * FROM riwayat_ulkus_kaki_diabetes where "
                    + "no_rawat='" + TNoRw.getText() + "' order by waktu_simpan");
            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabMode2.addRow(new String[]{
                        rs3.getString("no_rawat"),
                        rs3.getString("tahun"),
                        rs3.getString("lokasi"),
                        rs3.getString("penyebab"),
                        rs3.getString("waktu_simpan")
                    });
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
    }
    
    private void tampilDeformitas() {
        Valid.tabelKosong(tabMode3);
        try {
            ps4 = koneksi.prepareStatement("SELECT * FROM deformitas_kaki_diabetes where "
                    + "no_rawat='" + TNoRw.getText() + "' order by waktu_simpan");
            try {
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    tabMode3.addRow(new String[]{
                        rs4.getString("no_rawat"),
                        rs4.getString("lokasi"),
                        rs4.getString("kanan"),
                        rs4.getString("kiri"),
                        rs4.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilMikrobiologi() {
        Valid.tabelKosong(tabMode4);
        try {
            ps5 = koneksi.prepareStatement("SELECT * FROM mikrobiologi_kaki_diabetes where "
                    + "no_rawat='" + TNoRw.getText() + "' order by waktu_simpan");
            try {
                rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    tabMode4.addRow(new String[]{
                        rs5.getString("no_rawat"),
                        rs5.getString("bakteri"),
                        rs5.getString("sensitif"),
                        rs5.getString("resisten"),
                        rs5.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs5 != null) {
                    rs5.close();
                }
                if (ps5 != null) {
                    ps5.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void dataRiwPengobatan() {
        riwObat = "";
        nmObat = "";
        try {
            ps6 = koneksi.prepareStatement("SELECT * FROM riwayat_pengobatan_kaki_diabetes where "
                    + "no_rawat='" + TNoRw.getText() + "' order by waktu_simpan");
            try {
                rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    nmObat = Sequel.cariIsi("select obat from riwayat_pengobatan_kaki_diabetes where "
                            + "no_rawat='" + rs6.getString("no_rawat") + "' group by obat order by waktu_simpan limit 1");

                    if (riwObat.equals("")) {
                        riwObat = "Obat (" + nmObat + ") :\n"
                                + "Jenis : " + rs6.getString("jenis") + ", Dosis : " + rs6.getString("dosis") + ", Lama : " + rs6.getString("lama");
                    } else {
                        riwObat = riwObat + "\n\nObat (" + nmObat + ") :\n"
                                + "Jenis : " + rs6.getString("jenis") + ", Dosis : " + rs6.getString("dosis") + ", Lama : " + rs6.getString("lama");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs6 != null) {
                    rs6.close();
                }
                if (ps6 != null) {
                    ps6.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void dataRiwUlkus() {
        riwUlkus = "";
        try {
            ps7 = koneksi.prepareStatement("SELECT * FROM riwayat_ulkus_kaki_diabetes where "
                    + "no_rawat='" + TNoRw.getText() + "' order by waktu_simpan");
            try {
                rs7 = ps7.executeQuery();
                while (rs7.next()) {
                    if (riwUlkus.equals("")) {
                        riwUlkus = "Tahun : " + rs7.getString("tahun") + ", Lokasi : " + rs7.getString("lokasi") + ", Penyebab : " + rs7.getString("penyebab");
                    } else {
                        riwUlkus = riwUlkus + "\nTahun : " + rs7.getString("tahun") + ", Lokasi : " + rs7.getString("lokasi") + ", Penyebab : " + rs7.getString("penyebab");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs7 != null) {
                    rs7.close();
                }
                if (ps7 != null) {
                    ps7.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
