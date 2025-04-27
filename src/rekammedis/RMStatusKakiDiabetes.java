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
public final class RMStatusKakiDiabetes extends javax.swing.JDialog {
    private DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0, pilihan = 0;
    private String nip = "", jernih = "", keruh = "", lumpur = "", hijau = "", berbau = "", kering = "", hipertensi = "", dm = "", pms = "", tbc = "", asma = "",
            hepB = "", lainRiwayatIbu = "", spontan = "", vakum = "", forcep = "", sectio = "", lainCara = "", segar = "", layu = "", simpul = "", ibuDemam = "",
            kpd24 = "", ketuban = "", chorio = "", fetal = "", kpd12 = "", asfiksia = "", bblr = "", isk = "", uk = "", gemeli = "", keputihan = "", suhuIbu = "",
            obat = "", makanan = "", lainRiwayatAlergi = "", suami = "", orangTua = "", keluarga = "", lainDukungan = "", sikap0 = "", sikap1 = "", sikap2 = "",
            sikap3 = "", sikap4 = "", persegi_1 = "", persegi0 = "", persegi1 = "", persegi2 = "", persegi3 = "", persegi4 = "", rekoli0 = "", rekoli1 = "",
            rekoli2 = "", rekoli3 = "", rekoli4 = "", sudut_1 = "", sudut0 = "", sudut1 = "", sudut2 = "", sudut3 = "", sudut4 = "", sudut5 = "", tanda_1 = "", 
            tanda0 = "", tanda1 = "", tanda2 = "", tanda3 = "", tanda4 = "", tumit_1 = "", tumit0 = "", tumit1 = "", tumit2 = "", tumit3 = "", tumit4 = "", hipotermi = "",
            resikoHipotermi = "", hipertermi = "", pola = "", nyeri = "", kerusakan = "", resikoKerusakan = "", kebutuhan = "", ikterik = "", gangguan = "",
            bersihan = "", resikoBersihan = "", perubahan = "", kelebihan = "", resikoKelebihan = "", resikoKebutuhan = "", cekTglLahir = "", jamlahiribu = "",
            nipVerifikator = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMStatusKakiDiabetes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        
        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Rencana 1", "Jam Rencana 1", "Tgl. Rencana 2", "Jam Rencana 2", "Nama Perawat",
            "sumber_data", "ket_lain_sumber_data", "rujukan", "jenis_rujukan", "diagnosa_rujukan", "keluhan", "nm_identitas", "pendidikan", "pekerjaan", "agama", "alamat",
            "bbl", "pb", "lk", "ld", "lp", "ll", "kk", "nadi", "rr", "suhu", "jernih", "keruh", "lumpur", "hijau", "berbau", "kering", "anus", "bab", "bak", "kelainan_bawaan", "anak_ke",
            "umur_kehamilan", "riwayat_penyakit_ibu", "hipertensi", "dm", "pms", "tbc", "asma", "hepatitis_b", "lain_riwayat", "ket_lain_riwayat", "masih_pengobatan", "obat",
            "diagnosa_ibu", "tgl_lahir", "jam_lahir", "keadaan_saat_lahir", "ket_as", "spontan", "vakum", "forcep", "sectio", "lain_persalinan", "ket_lain_persalinan", "segar", "layu",
            "simpul", "ibu_demam", "kpd24", "ketuban", "chorio", "fetal", "kpd12", "asfiksia", "bblr", "isk", "uk", "gameli", "keputihan", "suhu_ibu", "riwayat_alergi", "obat_riwayat",
            "ket_obat_riwayat", "makanan", "ket_makanan", "lain_riwayat_alergi", "ket_lain_riwayat_alergi", "reaksi", "masalah_perkawinan", "ada_perkawinan", "perkawinan_lain",
            "mengalami_kekerasan", "kekerasan_fisik", "mencederai", "trauma", "ket_trauma", "gangguan_tidur", "konsultasi_psikiater", "penerimaan_terhadap_kondisi", "dukungan_suami",
            "dukungan_orang_tua", "dukungan_keluarga", "dukungan_lain", "ket_dukungan_lain", "status_pernikahan", "menikah", "hubungan_pasien", "tinggal_bersama",
            "ket_lain_tinggal_bersama", "tempat_tinggal", "ket_lain_tempat_tinggal", "nm_kerabat", "hubungan_kerabat", "no_tlp", "kegiatan_keagamaan", "kegiatan_spriritual",
            "nyeri", "crying", "requires", "increased", "expresion", "sleepless", "kesimpulan_penilaian", "sikap_tubuh1", "sikap_tubuh2", "sikap_tubuh3", "sikap_tubuh4", "sikap_tubuh5",
            "persegi_jendela1", "persegi_jendela2", "persegi_jendela3", "persegi_jendela4", "persegi_jendela5", "persegi_jendela6", "rekoli_lengan1", "rekoli_lengan2", "rekoli_lengan3",
            "rekoli_lengan4", "rekoli_lengan5", "sudut1", "sudut2", "sudut3", "sudut4", "sudut5", "sudut6", "sudut7", "tanda_selempang1", "tanda_selempang2", "tanda_selempang3",
            "tanda_selempang4", "tanda_selempang5", "tanda_selempang6", "tumit1", "tumit2", "tumit3", "tumit4", "tumit5", "tumit6", "fisik_kulit", "fisik_payudara", "fisik_mata",
            "fisik_genital_pria", "fisik_genital_wanita", "fisik_lanugo", "fisik_plantar", "hipotermi", "resiko_hipotermi", "hipertermi", "pola_nafas", "nyeri_masalah_keperawatan",
            "kerusakan", "resiko_kerusakan", "kebutuhan", "ikterik", "gangguan_motilitas", "bersihan", "resiko_bersihan", "perubahan_perfusi", "kelebihan", "resiko_kelebihan",
            "resiko_kebutuhan", "masalah_keperawatan_lain", "tgl_rencana1", "jam_rencana1", "tgl_rencana2", "jam_rencana2", "nip_perawat", "waktu_simpan", "cek_tgllahir_ibu", "spo2",
            "nip_verifikator", "nmVerifikator"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 194; i++) {
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
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(90);                
            } else if (i == 9) {
                column.setPreferredWidth(90);
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
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());
        
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
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(75);
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
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(250);
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
                column.setPreferredWidth(150);
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
        TlukaLama.setDocument(new batasInput((int) 7).getKata(TlukaLama));        
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
        
        Tas.setDocument(new batasInput((int) 140).getKata(Tas));
        TlainCara.setDocument(new batasInput((int) 140).getKata(TlainCara));
        TobatAlergi.setDocument(new batasInput((int) 140).getKata(TobatAlergi));
        TmakananAlergi.setDocument(new batasInput((int) 140).getKata(TmakananAlergi));
        TlainyaAlergi.setDocument(new batasInput((int) 140).getKata(TlainyaAlergi));
        TlainPerkawinan.setDocument(new batasInput((int) 140).getKata(TlainPerkawinan));
        TjelaskanTrauma.setDocument(new batasInput((int) 140).getKata(TjelaskanTrauma));
        TlainDukungan.setDocument(new batasInput((int) 140).getKata(TlainDukungan));
        TkaliMenikah.setDocument(new batasInput((byte) 7).getOnlyAngka(TkaliMenikah));
        TlainTinggal.setDocument(new batasInput((int) 140).getKata(TlainTinggal));
        TlainTempat.setDocument(new batasInput((int) 140).getKata(TlainTempat));
        TnmKerabat.setDocument(new batasInput((int) 40).getKata(TnmKerabat));
        ThubKerabat.setDocument(new batasInput((int) 40).getKata(ThubKerabat));
        TtelpKerabat.setDocument(new batasInput((byte) 16).getOnlyAngka(TtelpKerabat));
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
                if (pilihan == 1) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nip = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPerawat.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPerawat.requestFocus();
                    }
                } else if (pilihan == 2) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipVerifikator = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmVerifikator.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnVerifikator.requestFocus();
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
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
        TnmVerifikator = new widget.TextBox();
        BtnVerifikator = new widget.Button();
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
        TlukaLama = new widget.TextBox();
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
        chkAmputasiKiri = new widget.CekBox();
        cmbRiwAmputasiKiri = new widget.ComboBox();
        jLabel46 = new widget.Label();
        TjariKiri = new widget.TextBox();
        jLabel47 = new widget.Label();
        TtransKiri = new widget.TextBox();
        chkAmputasiKanan = new widget.CekBox();
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
        chkDorsalKanan = new widget.CekBox();
        PanelWall = new usu.widget.glass.PanelGlass();
        jLabel56 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        TdorsalKanan = new widget.TextArea();
        chkPlantarKanan = new widget.CekBox();
        jLabel57 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        TplantarKanan = new widget.TextArea();
        PanelWall1 = new usu.widget.glass.PanelGlass();
        jLabel58 = new widget.Label();
        chkDorsalKiri = new widget.CekBox();
        jLabel59 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        TdorsalKiri = new widget.TextArea();
        PanelWall2 = new usu.widget.glass.PanelGlass();
        chkPlantarKiri = new widget.CekBox();
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
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2843));
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
        jLabel99.setBounds(0, 2755, 125, 23);

        TnmPerawat.setEditable(false);
        TnmPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawat.setToolTipText("Alt+C");
        TnmPerawat.setName("TnmPerawat"); // NOI18N
        TnmPerawat.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TnmPerawat);
        TnmPerawat.setBounds(128, 2755, 430, 23);

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
        BtnPerawat.setBounds(560, 2755, 28, 23);

        jLabel146.setForeground(new java.awt.Color(0, 0, 0));
        jLabel146.setText("Diverifikasi Oleh : ");
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(0, 2811, 125, 23);

        TnmVerifikator.setEditable(false);
        TnmVerifikator.setForeground(new java.awt.Color(0, 0, 0));
        TnmVerifikator.setToolTipText("Alt+C");
        TnmVerifikator.setName("TnmVerifikator"); // NOI18N
        TnmVerifikator.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TnmVerifikator);
        TnmVerifikator.setBounds(128, 2811, 430, 23);

        BtnVerifikator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnVerifikator.setMnemonic('2');
        BtnVerifikator.setToolTipText("Alt+2");
        BtnVerifikator.setName("BtnVerifikator"); // NOI18N
        BtnVerifikator.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnVerifikator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVerifikatorActionPerformed(evt);
            }
        });
        FormInput.add(BtnVerifikator);
        BtnVerifikator.setBounds(560, 2811, 28, 23);

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
        TtglMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2025" }));
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

        tbRiwPengobatan.setAutoCreateRowSorter(true);
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
        Scroll1.setBounds(40, 346, 680, 190);

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

        TlukaLama.setBackground(new java.awt.Color(245, 250, 240));
        TlukaLama.setForeground(new java.awt.Color(0, 0, 0));
        TlukaLama.setName("TlukaLama"); // NOI18N
        TlukaLama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlukaLamaKeyPressed(evt);
            }
        });
        FormInput.add(TlukaLama);
        TlukaLama.setBounds(205, 568, 50, 23);

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

        tbRiwLuka.setAutoCreateRowSorter(true);
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
        Scroll2.setBounds(40, 876, 680, 150);

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
        jLabel45.setBounds(0, 1030, 200, 23);

        chkAmputasiKiri.setBackground(new java.awt.Color(255, 255, 250));
        chkAmputasiKiri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAmputasiKiri.setForeground(new java.awt.Color(0, 0, 0));
        chkAmputasiKiri.setText("Kiri : ");
        chkAmputasiKiri.setBorderPainted(true);
        chkAmputasiKiri.setBorderPaintedFlat(true);
        chkAmputasiKiri.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkAmputasiKiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAmputasiKiri.setName("chkAmputasiKiri"); // NOI18N
        chkAmputasiKiri.setOpaque(false);
        chkAmputasiKiri.setPreferredSize(new java.awt.Dimension(175, 23));
        chkAmputasiKiri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAmputasiKiriActionPerformed(evt);
            }
        });
        FormInput.add(chkAmputasiKiri);
        chkAmputasiKiri.setBounds(0, 1058, 200, 23);

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

        chkAmputasiKanan.setBackground(new java.awt.Color(255, 255, 250));
        chkAmputasiKanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAmputasiKanan.setForeground(new java.awt.Color(0, 0, 0));
        chkAmputasiKanan.setText("Kanan : ");
        chkAmputasiKanan.setBorderPainted(true);
        chkAmputasiKanan.setBorderPaintedFlat(true);
        chkAmputasiKanan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkAmputasiKanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAmputasiKanan.setName("chkAmputasiKanan"); // NOI18N
        chkAmputasiKanan.setOpaque(false);
        chkAmputasiKanan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkAmputasiKanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAmputasiKananActionPerformed(evt);
            }
        });
        FormInput.add(chkAmputasiKanan);
        chkAmputasiKanan.setBounds(0, 1086, 200, 23);

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
        BtnBMI.setBounds(725, 122, 100, 30);

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

        chkDorsalKanan.setBackground(new java.awt.Color(255, 255, 250));
        chkDorsalKanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDorsalKanan.setForeground(new java.awt.Color(0, 0, 0));
        chkDorsalKanan.setText("Dorsal Kanan");
        chkDorsalKanan.setBorderPainted(true);
        chkDorsalKanan.setBorderPaintedFlat(true);
        chkDorsalKanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDorsalKanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDorsalKanan.setName("chkDorsalKanan"); // NOI18N
        chkDorsalKanan.setOpaque(false);
        chkDorsalKanan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkDorsalKanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDorsalKananActionPerformed(evt);
            }
        });
        FormInput.add(chkDorsalKanan);
        chkDorsalKanan.setBounds(112, 1254, 93, 23);

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

        chkPlantarKanan.setBackground(new java.awt.Color(255, 255, 250));
        chkPlantarKanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPlantarKanan.setForeground(new java.awt.Color(0, 0, 0));
        chkPlantarKanan.setText("Plantar Kanan");
        chkPlantarKanan.setBorderPainted(true);
        chkPlantarKanan.setBorderPaintedFlat(true);
        chkPlantarKanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPlantarKanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPlantarKanan.setName("chkPlantarKanan"); // NOI18N
        chkPlantarKanan.setOpaque(false);
        chkPlantarKanan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPlantarKanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPlantarKananActionPerformed(evt);
            }
        });
        FormInput.add(chkPlantarKanan);
        chkPlantarKanan.setBounds(112, 1427, 93, 23);

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

        chkDorsalKiri.setBackground(new java.awt.Color(255, 255, 250));
        chkDorsalKiri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDorsalKiri.setForeground(new java.awt.Color(0, 0, 0));
        chkDorsalKiri.setText("Dorsal Kiri");
        chkDorsalKiri.setBorderPainted(true);
        chkDorsalKiri.setBorderPaintedFlat(true);
        chkDorsalKiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDorsalKiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDorsalKiri.setName("chkDorsalKiri"); // NOI18N
        chkDorsalKiri.setOpaque(false);
        chkDorsalKiri.setPreferredSize(new java.awt.Dimension(175, 23));
        chkDorsalKiri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDorsalKiriActionPerformed(evt);
            }
        });
        FormInput.add(chkDorsalKiri);
        chkDorsalKiri.setBounds(112, 1600, 93, 23);

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

        chkPlantarKiri.setBackground(new java.awt.Color(255, 255, 250));
        chkPlantarKiri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPlantarKiri.setForeground(new java.awt.Color(0, 0, 0));
        chkPlantarKiri.setText("Plantar Kiri");
        chkPlantarKiri.setBorderPainted(true);
        chkPlantarKiri.setBorderPaintedFlat(true);
        chkPlantarKiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPlantarKiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPlantarKiri.setName("chkPlantarKiri"); // NOI18N
        chkPlantarKiri.setOpaque(false);
        chkPlantarKiri.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPlantarKiri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPlantarKiriActionPerformed(evt);
            }
        });
        FormInput.add(chkPlantarKiri);
        chkPlantarKiri.setBounds(112, 1773, 93, 23);

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

        tbDeformitas.setAutoCreateRowSorter(true);
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

        ScrollTriase1.setViewportView(FormInput);

        FormData.add(ScrollTriase1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Data", FormData);

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

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2025" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2025" }));
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
            if (Sequel.menyimpantf("asesmen_keperawatan_perinatologi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 184, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), cmbSumber.getSelectedItem().toString(), TlainSumber.getText(), cmbRujukan.getSelectedItem().toString(),
                        cmbJnsRujukan.getSelectedItem().toString(), TdiagnosaRujukan.getText(), Tkeluhan.getText(), TnmIdentitas.getText(), cmbPendidikan.getSelectedItem().toString(),
                        Tpekerjaan.getText(), cmbAgama.getSelectedItem().toString(), Talamat.getText(), Tbbl.getText(), Tpb.getText(), Tlk.getText(), Tld.getText(),
                        Tlp.getText(), Tll.getText(), Tkk.getText(), Tnadi.getText(), Trr.getText(), Tsuhu.getText(), jernih, keruh, lumpur, hijau, berbau, kering,
                        cmbAnus.getSelectedItem().toString(), cmbBab.getSelectedItem().toString(), cmbBak.getSelectedItem().toString(), Tkelainan.getText(), TanakKe.getText(),
                        TumurHamil.getText(), cmbRiwPenyakitIbu.getSelectedItem().toString(), hipertensi, dm, pms, tbc, asma, hepB, lainRiwayatIbu, TlainRiwayat.getText(),
                        cmbMasih.getSelectedItem().toString(), Tobat.getText(), TdiagnosaIbu.getText(), Valid.SetTgl(TtglLahir.getSelectedItem() + ""), jamlahiribu,
                        TkeadaanSaat.getText(), Tas.getText(), spontan, vakum, forcep, sectio, lainCara, TlainCara.getText(), segar, layu, simpul, ibuDemam, kpd24, ketuban, 
                        chorio, fetal, kpd12, asfiksia, bblr, isk, uk, gemeli, keputihan, suhuIbu, cmbRiwAlergi.getSelectedItem().toString(), obat, TobatAlergi.getText(), 
                        makanan, TmakananAlergi.getText(), lainRiwayatAlergi, TlainyaAlergi.getText(), Treaksi.getText(), cmbMasalah.getSelectedItem().toString(), 
                        cmbAdaPerkawinan.getSelectedItem().toString(), TlainPerkawinan.getText(), cmbMengalami.getSelectedItem().toString(), cmbAdaMengalami.getSelectedItem().toString(), 
                        cmbDialami.getSelectedItem().toString(), cmbTrauma.getSelectedItem().toString(), TjelaskanTrauma.getText(), cmbGangguan.getSelectedItem().toString(), 
                        cmbKonsultasi.getSelectedItem().toString(), cmbPenerimaan.getSelectedItem().toString(), suami, orangTua, keluarga, lainDukungan, TlainDukungan.getText(), 
                        cmbStatusNikah.getSelectedItem().toString(), TkaliMenikah.getText(), cmbHubungan.getSelectedItem().toString(), cmbTinggal.getSelectedItem().toString(), 
                        TlainTinggal.getText(), cmbTempat.getSelectedItem().toString(), TlainTempat.getText(), TnmKerabat.getText(), ThubKerabat.getText(), TtelpKerabat.getText(), 
                        TkegiatanAgama.getText(), TkegiatanSpiritual.getText(), cmbNyeri.getSelectedItem().toString(), cmbCrying.getSelectedItem().toString(), 
                        cmbRequires.getSelectedItem().toString(), cmbIncreased.getSelectedItem().toString(), cmbExpresion.getSelectedItem().toString(), 
                        cmbSleepless.getSelectedItem().toString(), TkesimpulanNyeri.getText(), sikap0, sikap1, sikap2, sikap3, sikap4, persegi_1, persegi0, persegi1, persegi2, 
                        persegi3, persegi4, rekoli0, rekoli1, rekoli2, rekoli3, rekoli4, sudut_1, sudut0, sudut1, sudut2, sudut3, sudut4, sudut5, tanda_1, tanda0, tanda1, tanda2, 
                        tanda3, tanda4, tumit_1, tumit0, tumit1, tumit2, tumit3, tumit4, cmbFisikKulit.getSelectedItem().toString(), cmbFisikPayudara.getSelectedItem().toString(), 
                        cmbFisikMata.getSelectedItem().toString(), cmbFisikGenPria.getSelectedItem().toString(), cmbFisikGenWanita.getSelectedItem().toString(), 
                        cmbFisikLanugo.getSelectedItem().toString(), cmbFisikPlantar.getSelectedItem().toString(), hipotermi, resikoHipotermi, hipertermi, pola, nyeri, kerusakan, 
                        resikoKerusakan, kebutuhan, ikterik, gangguan, bersihan, resikoBersihan, perubahan, kelebihan, resikoKelebihan, resikoKebutuhan, TdorsalKanan.getText(), 
                        Valid.SetTgl(TtglRencana1.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), 
                        Valid.SetTgl(TtglRencana2.getSelectedItem() + ""), cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), nip, 
                        Sequel.cariIsi("select now()"), cekTglLahir, Tspo.getText(), nipVerifikator
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Assesmen Keperawatan Perinatologi", "Simpan");
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
        if (tbAsesmen.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapus();
            } else {
                if (nip.equals(akses.getkode())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh perawat yang bernama " + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString() + " ..!!");
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
            if (tbAsesmen.getSelectedRow() > -1) {
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
        if (tbAsesmen.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));

            if (cmbSumber.getSelectedIndex() == 3) {
                param.put("sumberData", cmbSumber.getSelectedItem().toString() + " : " + TlainSumber.getText());
            } else {
                param.put("sumberData", cmbSumber.getSelectedItem().toString());
            }
            
            if (cmbRujukan.getSelectedIndex() == 1) {
                param.put("rujukan", cmbRujukan.getSelectedItem().toString() + " (" + cmbJnsRujukan.getSelectedItem().toString() + ")");
            } else {
                param.put("rujukan", cmbRujukan.getSelectedItem().toString());
            }
            
            if (TdiagnosaRujukan.getText().equals("")) {
                param.put("diagRujukan", "-");
            } else {
                param.put("diagRujukan", TdiagnosaRujukan.getText());
            }
            
            if (Tkeluhan.getText().equals("")) {
                param.put("keluhan", "-");
            } else {
                param.put("keluhan", Tkeluhan.getText());
            }
            
            if (TnmIdentitas.getText().equals("")) {
                param.put("nmIdentitas", "-");
            } else {
                param.put("nmIdentitas", TnmIdentitas.getText());
            }
            
            param.put("pendidikan", cmbPendidikan.getSelectedItem().toString());
            
            if (Tpekerjaan.getText().equals("")) {
                param.put("pekerjaan", "-");
            } else {
                param.put("pekerjaan", Tpekerjaan.getText());
            }
            
            param.put("agama", cmbAgama.getSelectedItem().toString());
            
            if (Talamat.getText().equals("")) {
                param.put("alamat", "-");
            } else {
                param.put("alamat", Talamat.getText());
            }
            
            param.put("bbl", Tbbl.getText() + " gram");
            param.put("pb", Tpb.getText() + " cm.");
            param.put("lk", Tlk.getText() + " cm.");
            param.put("ld", Tld.getText() + " cm.");
            param.put("lp", Tlp.getText() + " cm.");
            param.put("spo2", Tspo.getText() + " %");
            param.put("ll", Tll.getText() + " cm.");
            param.put("kk", Tkk.getText() + " cm.");
            param.put("nadi", Tnadi.getText() + " x/menit");
            param.put("rr", Trr.getText() + " x/menit");
            param.put("suhu", Tsuhu.getText() + " °C");
            
            if (chkJernih.isSelected() == true) {
                param.put("jernih", "V");
            } else {
                param.put("jernih", "");
            }
            
            if (chkKeruh.isSelected() == true) {
                param.put("keruh", "V");
            } else {
                param.put("keruh", "");
            }
            
            if (chkLumpur.isSelected() == true) {
                param.put("lumpur", "V");
            } else {
                param.put("lumpur", "");
            }
            
            if (chkHijau.isSelected() == true) {
                param.put("hijau", "V");
            } else {
                param.put("hijau", "");
            }
            
            if (chkBerbau.isSelected() == true) {
                param.put("berbau", "V");
            } else {
                param.put("berbau", "");
            }
            
            if (chkKering.isSelected() == true) {
                param.put("kering", "V");
            } else {
                param.put("kering", "");
            }
            
            param.put("anus", cmbAnus.getSelectedItem().toString());
            param.put("bab", cmbBab.getSelectedItem().toString());
            param.put("bak", cmbBak.getSelectedItem().toString());
            param.put("kelainan", Tkelainan.getText());
            param.put("jenkel", cmbJenkel.getSelectedItem().toString());
            
            if (TanakKe.getText().equals("")) {
                param.put("anakKe", "-");
            } else {
                param.put("anakKe", TanakKe.getText());
            }
            
            if (TumurHamil.getText().equals("")) {
                param.put("umurHamil", "-");
            } else {
                param.put("umurHamil", TumurHamil.getText() + " Minggu");
            }
            
            param.put("riwPenyakitIbu", cmbRiwPenyakitIbu.getSelectedItem().toString() + " (");

            if (chkTraumaMekanik.isSelected() == true) {
                param.put("hipertensi", "V");
            } else {
                param.put("hipertensi", "");
            }
            
            if (chkDM.isSelected() == true) {
                param.put("dm", "V");
            } else {
                param.put("dm", "");
            }
            
            if (chkPMS.isSelected() == true) {
                param.put("pms", "V");
            } else {
                param.put("pms", "");
            }
            
            if (chkTBC.isSelected() == true) {
                param.put("tbc", "V");
            } else {
                param.put("tbc", "");
            }
            
            if (chkAsma.isSelected() == true) {
                param.put("asma", "V");
            } else {
                param.put("asma", "");
            }
            
            if (chkHepB.isSelected() == true) {
                param.put("hepB", "V");
            } else {
                param.put("hepB", "");
            }
            
            if (chkLainyaRiwayat.isSelected() == true) {
                param.put("lainRiwayat", "V");
                param.put("ketlainRiwayat", "Lainnya " + TlainRiwayat.getText() + ")");
            } else {
                param.put("lainRiwayat", "");
                param.put("ketlainRiwayat", "Lainnya ....)");
            }
            
            if (cmbMasih.getSelectedIndex() == 2) {                
                param.put("masih", cmbMasih.getSelectedItem().toString() + ", Obat " + Tobat.getText());
            } else {
                param.put("masih", cmbMasih.getSelectedItem().toString());
            }
            
            if (TdiagnosaIbu.getText().equals("")) {
                param.put("diagIbu", "-");
            } else {
                param.put("diagIbu", TdiagnosaIbu.getText());
            }
            
            if (chkTgllahirIbu.isSelected() == true) {
                param.put("tglLahirIbu", TtglLahir.getSelectedItem().toString() + ", Jam : " + cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString());
            } else {
                param.put("tglLahirIbu", "- , Jam : -");
            }
            
            if (TkeadaanSaat.getText().equals("")) {
                param.put("keadaanSaat", "-");
            } else {
                param.put("keadaanSaat", TkeadaanSaat.getText());
            }
            
            if (Tas.getText().equals("")) {
                param.put("as", "-");
            } else {
                param.put("as", Tas.getText());
            }            
            
            if (chkSpontan.isSelected() == true) {
                param.put("spontan", "V");
            } else {
                param.put("spontan", "");
            }
            
            if (chkVakum.isSelected() == true) {
                param.put("vakum", "V");
            } else {
                param.put("vakum", "");
            }
            
            if (chkForcep.isSelected() == true) {
                param.put("forcep", "V");
            } else {
                param.put("forcep", "");
            }
            
            if (chkSectio.isSelected() == true) {
                param.put("sectio", "V");
            } else {
                param.put("sectio", "");
            }
            
            if (chkLainyaCara.isSelected() == true) {
                param.put("lainCara", "V");
                param.put("ketlainCara", "Lainnya : " + TlainCara.getText());
            } else {
                param.put("lainCara", "");
                param.put("ketlainCara", "Lainnya : ....");
            }
            
            if (chkSegar.isSelected() == true) {
                param.put("segar", "V");
            } else {
                param.put("segar", "");
            }
            
            if (chkLayu.isSelected() == true) {
                param.put("layu", "V");
            } else {
                param.put("layu", "");
            }
            
            if (chkSimpul.isSelected() == true) {
                param.put("simpul", "V");
            } else {
                param.put("simpul", "");
            }
            
            if (chkIbuDemam.isSelected() == true) {
                param.put("ibuDemam", "V");
            } else {
                param.put("ibuDemam", "");
            }
            
            if (chkKpd24.isSelected() == true) {
                param.put("kpd24", "V");
            } else {
                param.put("kpd24", "");
            }
            
            if (chkKetuban.isSelected() == true) {
                param.put("ketuban", "V");
            } else {
                param.put("ketuban", "");
            }
            
            if (chkChorio.isSelected() == true) {
                param.put("chorio", "V");
            } else {
                param.put("chorio", "");
            }
            
            if (chkFetal.isSelected() == true) {
                param.put("fetal", "V");
            } else {
                param.put("fetal", "");
            }
            
            if (chkKpd12.isSelected() == true) {
                param.put("kpd12", "V");
            } else {
                param.put("kpd12", "");
            }
            
            if (chkAsfiksia.isSelected() == true) {
                param.put("asfiksia", "V");
            } else {
                param.put("asfiksia", "");
            }
            
            if (chkBblr.isSelected() == true) {
                param.put("bblr", "V");
            } else {
                param.put("bblr", "");
            }
            
            if (chkIsk.isSelected() == true) {
                param.put("isk", "V");
            } else {
                param.put("isk", "");
            }
            
            if (chkUK.isSelected() == true) {
                param.put("uk", "V");
            } else {
                param.put("uk", "");
            }
            
            if (chkGemeli.isSelected() == true) {
                param.put("gemeli", "V");
            } else {
                param.put("gemeli", "");
            }
            
            if (chkKeputihan.isSelected() == true) {
                param.put("keputihan", "V");
            } else {
                param.put("keputihan", "");
            }
            
            if (chkSuhuIbu.isSelected() == true) {
                param.put("suhuIbu", "V");
            } else {
                param.put("suhuIbu", "");
            }
            
            param.put("riwAlergi", cmbRiwAlergi.getSelectedItem().toString());
            
            if (chkObatAlergi.isSelected() == true) {
                param.put("obatAlergi", "V");
                if (TobatAlergi.getText().equals("")) {
                    param.put("ketobatAlergi", "Obat ....");
                } else {
                    param.put("ketobatAlergi", "Obat (" + TobatAlergi.getText() + ")");
                }                
            } else {
                param.put("obatAlergi", "");
                param.put("ketobatAlergi", "Obat ....");
            }
            
            if (chkMakananAlergi.isSelected() == true) {
                param.put("makanan", "V");
                if (TmakananAlergi.getText().equals("")) {
                    param.put("ketMakanan", "Makanan ....");
                } else {
                    param.put("ketMakanan", "Makanan (" + TmakananAlergi.getText() + ")");
                }                
            } else {
                param.put("makanan", "");
                param.put("ketMakanan", "Makanan ....");
            }
            
            if (chkLainAlergi.isSelected() == true) {
                param.put("lainAlergi", "V");
                if (TlainyaAlergi.getText().equals("")) {
                    param.put("ketlainAlergi", "Lainnya ....");
                } else {
                    param.put("ketlainAlergi", "Lainnya (" + TlainyaAlergi.getText() + ")");
                }                
            } else {
                param.put("lainAlergi", "");
                param.put("ketlainAlergi", "Lainnya ....");
            }
            
            if (Treaksi.getText().equals("")) {
                param.put("reaksi", "Reaksi : .......");
            } else {
                param.put("reaksi", "Reaksi : " + Treaksi.getText());
            }            
            
            if (cmbMasalah.getSelectedIndex() == 1) {
                if (cmbAdaPerkawinan.getSelectedIndex() == 4) {
                    param.put("masalah", cmbMasalah.getSelectedItem().toString() + " : " + cmbAdaPerkawinan.getSelectedItem().toString() + " : " + TlainPerkawinan.getText());
                } else {
                    param.put("masalah", cmbMasalah.getSelectedItem().toString() + " : " + cmbAdaPerkawinan.getSelectedItem().toString());
                }
            } else {
                param.put("masalah", cmbMasalah.getSelectedItem().toString());
            }
            
            if (cmbMengalami.getSelectedIndex() == 1) {
                param.put("mengalami", cmbMengalami.getSelectedItem().toString() + " : " + cmbAdaMengalami.getSelectedItem().toString() + " : " + cmbDialami.getSelectedItem().toString());
            } else {
                param.put("mengalami", cmbMengalami.getSelectedItem().toString());
            }
            
            if (cmbTrauma.getSelectedIndex() == 1) {
                if (TjelaskanTrauma.getText().equals("")) {
                    param.put("trauma", cmbTrauma.getSelectedItem().toString());
                } else {
                    param.put("trauma", cmbTrauma.getSelectedItem().toString() + ", Jelaskan : " + TjelaskanTrauma.getText());
                }
            } else {
                param.put("trauma", cmbTrauma.getSelectedItem().toString());
            }
            
            param.put("gangguanTidur", cmbGangguan.getSelectedItem().toString());
            param.put("konsultasi", cmbKonsultasi.getSelectedItem().toString());
            param.put("penerimaan", cmbPenerimaan.getSelectedItem().toString());
            
            if (chkSuami.isSelected() == true) {
                param.put("suami", "V");
            } else {
                param.put("suami", "");
            }
            
            if (chkOrangTua.isSelected() == true) {
                param.put("orangTua", "V");
            } else {
                param.put("orangTua", "");
            }
            
            if (chkKeluarga.isSelected() == true) {
                param.put("keluarga", "V");
            } else {
                param.put("keluarga", "");
            }
            
            if (chkLainDukungan.isSelected() == true) {
                param.put("lainDukungan", "V");
                param.put("ketlainDukungan", "Lain - Lain (" + TlainDukungan.getText() + ")");
            } else {
                param.put("lainDukungan", "");
                param.put("ketlainDukungan", "Lain - Lain");
            }
            
            if (cmbStatusNikah.getSelectedIndex() == 2) {
                if (TkaliMenikah.getText().equals("")) {
                    param.put("sttsPernikahan", cmbStatusNikah.getSelectedItem().toString());
                } else {
                    param.put("sttsPernikahan", cmbStatusNikah.getSelectedItem().toString() + " : " + TkaliMenikah.getText() + " kali");
                }
            } else {
                param.put("sttsPernikahan", cmbStatusNikah.getSelectedItem().toString());
            }
            
            param.put("hubAnggotaKlg", cmbHubungan.getSelectedItem().toString());
            
            if (cmbTinggal.getSelectedIndex() == 7) {
                if (TlainTinggal.getText().equals("")) {
                    param.put("tinggalBersama", cmbTinggal.getSelectedItem().toString());
                } else {
                    param.put("tinggalBersama", cmbTinggal.getSelectedItem().toString() + " : " + TlainTinggal.getText());
                }            
            } else {
                param.put("tinggalBersama", cmbTinggal.getSelectedItem().toString());
            }

            if (cmbTempat.getSelectedIndex() == 4) {
                if (TlainTempat.getText().equals("")) {
                    param.put("tempatTinggal", cmbTempat.getSelectedItem().toString());
                } else {
                    param.put("tempatTinggal", cmbTempat.getSelectedItem().toString() + ", " + TlainTempat.getText());
                }
            } else {
                param.put("tempatTinggal", cmbTempat.getSelectedItem().toString());
            }
            
            if (TnmKerabat.getText().equals("")) {
                param.put("nmKerabat", "-");
            } else {
                param.put("nmKerabat", TnmKerabat.getText());
            }
            
            if (ThubKerabat.getText().equals("")) {
                param.put("hubKerabat", "-");
            } else {
                param.put("hubKerabat", ThubKerabat.getText());
            }
            
            if (TtelpKerabat.getText().equals("")) {
                param.put("telpKerabat", "-");
            } else {
                param.put("telpKerabat", TtelpKerabat.getText());
            }
            
            if (TkegiatanAgama.getText().equals("")) {
                param.put("kegAgama", "-");
            } else {
                param.put("kegAgama", TkegiatanAgama.getText());
            }
            
            if (TkegiatanSpiritual.getText().equals("")) {
                param.put("kegSpiritual", "-");
            } else {
                param.put("kegSpiritual", TkegiatanSpiritual.getText());
            }
            
            param.put("nyeri", cmbNyeri.getSelectedItem().toString());
            param.put("crying", cmbCrying.getSelectedItem().toString());
            param.put("requires", cmbRequires.getSelectedItem().toString());
            param.put("increas", cmbIncreased.getSelectedItem().toString());
            param.put("express", cmbExpresion.getSelectedItem().toString());
            param.put("sleep", cmbSleepless.getSelectedItem().toString());            
            param.put("nilaicrying", TnilaiCrying.getText());
            param.put("nilairequires", TnilaiRequires.getText());
            param.put("nilaiincreas", TnilaiIncreased.getText());
            param.put("nilaiexpress", TnilaiExpresion.getText());
            param.put("nilaisleep", TnilaiSleepless.getText());
            param.put("nilaiTotNyeri", TtotNilaiNyeri.getText());
            param.put("kesimpulanNyeri", TkesimpulanNyeri.getText());
            
            param.put("nilaiSikap", TnilaiSikap.getText());
            param.put("nilaiPersegi", TnilaiPersegi.getText());
            param.put("nilaiRekoli", TnilaiRekoli.getText());
            param.put("nilaiSudut", TnilaiSudut.getText());
            param.put("nilaiTanda", TnilaiTanda.getText());
            param.put("nilaiTumit", TnilaiTumit.getText());
            param.put("nilaiTotNeo", TtotNilaiNeomuskular.getText());
            
            param.put("kulit", cmbFisikKulit.getSelectedItem().toString());
            param.put("payudara", cmbFisikPayudara.getSelectedItem().toString());
            param.put("mata", cmbFisikMata.getSelectedItem().toString());
            param.put("genPria", cmbFisikGenPria.getSelectedItem().toString());
            param.put("genWanita", cmbFisikGenWanita.getSelectedItem().toString());
            param.put("lanugo", cmbFisikLanugo.getSelectedItem().toString());
            param.put("plantar", cmbFisikPlantar.getSelectedItem().toString());            
            param.put("nilaiKulit", TnilaiKulit.getText());
            param.put("nilaiPayudara", TnilaiPayudara.getText());
            param.put("nilaiMata", TnilaiMata.getText());
            param.put("nilaiGenPria", TnilaiGenPria.getText());
            param.put("nilaiGenWanita", TnilaiGenWanita.getText());
            param.put("nilaiLanugo", TnilaiLanugo.getText());
            param.put("nilaiPlantar", TnilaiPlantar.getText());
            param.put("nilaiTotFisik", TtotNilaiFisik.getText());
            
            param.put("nilaiNeo", TnilaiNeo.getText());
            param.put("nilaiFisik", TnilaiFisik.getText());
            param.put("nilaiSkor", TnilaiSkor.getText());
            param.put("kesimpulanSkor", TkesimpulanSkor.getText());
            
            if (chkHipotermi.isSelected() == true) {
                param.put("hipotermi", "V");
            } else {
                param.put("hipotermi", "");
            }
            
            if (chkResikoHipotermi.isSelected() == true) {
                param.put("resHipotermi", "V");
            } else {
                param.put("resHipotermi", "");
            }
            
            if (chkHipertermi.isSelected() == true) {
                param.put("hipertermi", "V");
            } else {
                param.put("hipertermi", "");
            }
            
            if (chkPolaNafas.isSelected() == true) {
                param.put("pola", "V");
            } else {
                param.put("pola", "");
            }
            
            if (chkNyeri.isSelected() == true) {
                param.put("masalahNyeri", "V");
            } else {
                param.put("masalahNyeri", "");
            }
            
            if (chkKerusakan.isSelected() == true) {
                param.put("kerusakan", "V");
            } else {
                param.put("kerusakan", "");
            }
            
            if (chkResikoKerusakan.isSelected() == true) {
                param.put("resKerusakan", "V");
            } else {
                param.put("resKerusakan", "");
            }
            
            if (chkKebutuhan.isSelected() == true) {
                param.put("kebutuhan", "V");
            } else {
                param.put("kebutuhan", "");
            }
            
            if (chkIkterik.isSelected() == true) {
                param.put("ikterik", "V");
            } else {
                param.put("ikterik", "");
            }
            
            if (chkGangguan.isSelected() == true) {
                param.put("masalahGangguan", "V");
            } else {
                param.put("masalahGangguan", "");
            }
            
            if (chkBersihan.isSelected() == true) {
                param.put("bersihan", "V");
            } else {
                param.put("bersihan", "");
            }
            
            if (chkResikoBersihan.isSelected() == true) {
                param.put("resBersihan", "V");
            } else {
                param.put("resBersihan", "");
            }
            
            if (chkPerubahan.isSelected() == true) {
                param.put("perubahan", "V");
            } else {
                param.put("perubahan", "");
            }
            
            if (chkKelebihan.isSelected() == true) {
                param.put("kelebihan", "V");
            } else {
                param.put("kelebihan", "");
            }
            
            if (chkResikoKelebihan.isSelected() == true) {
                param.put("resKelebihan", "V");
            } else {
                param.put("resKelebihan", "");
            }
            
            if (chkResikoKebutuhan.isSelected() == true) {
                param.put("resKebutuhan", "V");
            } else {
                param.put("resKebutuhan", "");
            }
            
            if (TdorsalKanan.getText().equals("")) {
                param.put("masalahKep", "-\n");
            } else {
                param.put("masalahKep", TdorsalKanan.getText() + "\n");
            }
            
            param.put("tglRen1", Valid.SetTglINDONESIA(Valid.SetTgl(TtglRencana1.getSelectedItem() + "")));
            param.put("tglRen2", Valid.SetTglINDONESIA(Valid.SetTgl(TtglRencana2.getSelectedItem() + "")));
            param.put("jamRen1", cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " Wita");
            param.put("jamRen2", cmbJam2.getSelectedItem().toString() + ":" + cmbMnt2.getSelectedItem().toString() + " Wita");            
            param.put("petugas", TnmPerawat.getText());
            param.put("verifikator", TnmVerifikator.getText());
            
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
        if (Sequel.cariInteger("select count(-1) from asesmen_keperawatan_perinatologi where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
            tampil();
        } else if (Sequel.cariInteger("select count(-1) from asesmen_keperawatan_perinatologi where no_rawat='" + TNoRw.getText() + "'") == 0) {
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

    private void BtnPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatActionPerformed
        pilihan = 0;
        pilihan = 1;
        akses.setform("RMAsesmenKeperawatanPerinatologi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatActionPerformed

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMAsesmenKeperawatanPerinatologi");
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
            akses.setform("RMAsesmenKeperawatanPerinatologi");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void BtnVerifikatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVerifikatorActionPerformed
        pilihan = 0;
        pilihan = 2;
        akses.setform("RMAsesmenKeperawatanPerinatologi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnVerifikatorActionPerformed

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
        cmbObat.requestFocus();
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
            BtnSimpanObat.requestFocus();
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
            TlukaLama.requestFocus();
        }
    }//GEN-LAST:event_TmerokokYaKeyPressed

    private void TmerokokMantanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmerokokMantanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TlukaLama.requestFocus();
        }
    }//GEN-LAST:event_TmerokokMantanKeyPressed

    private void TlukaLamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlukaLamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSatuan.requestFocus();
        }
    }//GEN-LAST:event_TlukaLamaKeyPressed

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
            BtnSimpanLuka.requestFocus();
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
        Ttahun.requestFocus();
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

    private void chkAmputasiKiriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAmputasiKiriActionPerformed
        cmbRiwAmputasiKiri.setSelectedIndex(0);
        TjariKiri.setText("");
        TtransKiri.setText("");
        if (chkAmputasiKiri.isSelected() == true) {
            cmbRiwAmputasiKiri.setEnabled(true);
            TjariKiri.setEnabled(false);
            TtransKiri.setEnabled(false);
            cmbRiwAmputasiKiri.requestFocus();
        } else {
            cmbRiwAmputasiKiri.setEnabled(false);
            TjariKiri.setEnabled(false);
            TtransKiri.setEnabled(false);
        }
    }//GEN-LAST:event_chkAmputasiKiriActionPerformed

    private void chkAmputasiKananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAmputasiKananActionPerformed
        cmbRiwAmputasiKanan.setSelectedIndex(0);
        TjariKanan.setText("");
        TtransKanan.setText("");
        if (chkAmputasiKanan.isSelected() == true) {
            cmbRiwAmputasiKanan.setEnabled(true);
            TjariKanan.setEnabled(false);
            TtransKanan.setEnabled(false);
            cmbRiwAmputasiKanan.requestFocus();
        } else {
            cmbRiwAmputasiKanan.setEnabled(false);
            TjariKanan.setEnabled(false);
            TtransKanan.setEnabled(false);
        }
    }//GEN-LAST:event_chkAmputasiKananActionPerformed

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
            chkPlantarKanan.requestFocus();
        }
    }//GEN-LAST:event_TdorsalKananKeyPressed

    private void chkDorsalKananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDorsalKananActionPerformed
        TdorsalKanan.setText("");
        if (chkDorsalKanan.isSelected() == true) {
            TdorsalKanan.setEnabled(true);
            TdorsalKanan.requestFocus();
        } else {
            TdorsalKanan.setEnabled(false);
        }
    }//GEN-LAST:event_chkDorsalKananActionPerformed

    private void chkPlantarKananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPlantarKananActionPerformed
        TplantarKanan.setText("");
        if (chkPlantarKanan.isSelected() == true) {
            TplantarKanan.setEnabled(true);
            TplantarKanan.requestFocus();
        } else {
            TplantarKanan.setEnabled(false);
        }
    }//GEN-LAST:event_chkPlantarKananActionPerformed

    private void TplantarKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TplantarKananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            chkDorsalKiri.requestFocus();
        }
    }//GEN-LAST:event_TplantarKananKeyPressed

    private void chkDorsalKiriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDorsalKiriActionPerformed
        TdorsalKiri.setText("");
        if (chkDorsalKiri.isSelected() == true) {
            TdorsalKiri.setEnabled(true);
            TdorsalKiri.requestFocus();
        } else {
            TdorsalKiri.setEnabled(false);
        }
    }//GEN-LAST:event_chkDorsalKiriActionPerformed

    private void TdorsalKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdorsalKiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            chkPlantarKiri.requestFocus();
        }
    }//GEN-LAST:event_TdorsalKiriKeyPressed

    private void chkPlantarKiriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPlantarKiriActionPerformed
        TplantarKiri.setText("");
        if (chkPlantarKiri.isSelected() == true) {
            TplantarKiri.setEnabled(true);
            TplantarKiri.requestFocus();
        } else {
            TplantarKiri.setEnabled(false);
        }
    }//GEN-LAST:event_chkPlantarKiriActionPerformed

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
            BtnSimpanDefor.requestFocus();
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
    private widget.Button BtnEdit;
    private widget.Button BtnGantiDefor;
    private widget.Button BtnGantiLuka;
    private widget.Button BtnGantiObat;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusDefor;
    private widget.Button BtnHapusLuka;
    private widget.Button BtnHapusObat;
    private widget.Button BtnKeluar;
    private widget.Button BtnNotepad;
    private widget.Button BtnPerawat;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpanDefor;
    private widget.Button BtnSimpanLuka;
    private widget.Button BtnSimpanObat;
    private widget.Button BtnTambahDefor;
    private widget.Button BtnTambahLuka;
    private widget.Button BtnTambahObat;
    private widget.Button BtnVerifikator;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.InternalFrame FormData;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private usu.widget.glass.PanelGlass PanelWall;
    private usu.widget.glass.PanelGlass PanelWall1;
    private usu.widget.glass.PanelGlass PanelWall2;
    private usu.widget.glass.PanelGlass PanelWall3;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane ScrollTriase1;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Talamat;
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
    private widget.TextBox TlainSebutkan;
    private widget.TextBox Tlama;
    private widget.TextBox TlamaDiketahui;
    private widget.TextBox TlamaRawat;
    private widget.TextBox TlaserTahun;
    private widget.TextBox Tlokasi;
    private widget.TextBox TlukaLama;
    private widget.TextBox TmerokokMantan;
    private widget.TextBox TmerokokYa;
    private widget.TextBox TnmPerawat;
    private widget.TextBox TnmVerifikator;
    private widget.TextBox TnoTelp;
    private widget.TextBox Tpenyebab;
    private widget.TextArea TplantarKanan;
    private widget.TextArea TplantarKiri;
    private widget.TextBox Tpnd;
    private widget.TextBox Tsepatu;
    private widget.TextBox Tsuku;
    private widget.TextBox Ttahun;
    private widget.TextBox Ttb;
    private widget.TextBox Ttensi;
    private widget.TextBox TterkenaZat;
    private widget.Tanggal TtglMasuk;
    private widget.TextBox TtipeDiabetLain;
    private widget.TextBox TtransKanan;
    private widget.TextBox TtransKiri;
    private widget.TextBox Tusia;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    public widget.CekBox chkAmputasiKanan;
    public widget.CekBox chkAmputasiKiri;
    public widget.CekBox chkDllsebutkanMekanik;
    public widget.CekBox chkDllsebutkanTermis;
    public widget.CekBox chkDorsalKanan;
    public widget.CekBox chkDorsalKiri;
    public widget.CekBox chkGinjal;
    public widget.CekBox chkHipertensi;
    public widget.CekBox chkLainLain;
    public widget.CekBox chkMata;
    public widget.CekBox chkMemakaiSepatu;
    public widget.CekBox chkNonUlkus;
    public widget.CekBox chkPad;
    public widget.CekBox chkPenyJantung;
    public widget.CekBox chkPlantarKanan;
    public widget.CekBox chkPlantarKiri;
    public widget.CekBox chkSelulitis;
    public widget.CekBox chkSpontan;
    public widget.CekBox chkStrok;
    public widget.CekBox chkTerkenaAir;
    public widget.CekBox chkTerkenaPemanas;
    public widget.CekBox chkTerkenaZat;
    public widget.CekBox chkTersandung;
    public widget.CekBox chkTertusuk;
    public widget.CekBox chkTraumaKimia;
    public widget.CekBox chkTraumaMekanik;
    public widget.CekBox chkTraumaTermis;
    public widget.CekBox chkUlkus;
    public widget.CekBox chkUlkusGangen;
    private widget.ComboBox cmbGinjal;
    private widget.ComboBox cmbJnsAlas;
    private widget.ComboBox cmbJnsRawat;
    private widget.ComboBox cmbLokasi;
    private widget.ComboBox cmbMata;
    private widget.ComboBox cmbMerokok;
    private widget.ComboBox cmbObat;
    private widget.ComboBox cmbRiwAmputasiKanan;
    private widget.ComboBox cmbRiwAmputasiKiri;
    private widget.ComboBox cmbRiwEdukasi;
    private widget.ComboBox cmbRiwUlkus;
    private widget.ComboBox cmbSatuan;
    private widget.ComboBox cmbTipeDiabet;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
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
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.Table tbAsesmen;
    private widget.Table tbDeformitas;
    private widget.Table tbRiwLuka;
    private widget.Table tbRiwPengobatan;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT ak.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d/%m/%Y') tglLahir, "
                    + "date_format(ak.tgl_rencana1,'%d/%m/%Y') tglRen1, time_format(ak.jam_rencana1,'%H:%i Wita') jamRen1, date_format(ak.tgl_rencana2,'%d/%m/%Y') tglRen2, "
                    + "time_format(ak.jam_rencana2,'%H:%i Wita') jamRen2, pg1.nama nmPerawat, pg2.nama nmVerifikator FROM asesmen_keperawatan_perinatologi ak "
                    + "inner join reg_periksa rp on rp.no_rawat=ak.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg1 on pg1.nik=ak.nip_perawat inner join pegawai pg2 on pg2.nik=ak.nip_verifikator where "
                    + "date(ak.waktu_simpan) between ? and ? and ak.no_rawat like ? or "
                    + "date(ak.waktu_simpan) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(ak.waktu_simpan) between ? and ? and p.nm_pasien like ? or "
                    + "date(ak.waktu_simpan) between ? and ? and ak.ruang_rawat like ? or "
                    + "date(ak.waktu_simpan) between ? and ? and pg1.nama like ? or "
                    + "date(ak.waktu_simpan) between ? and ? and pg2.nama like ? order by ak.waktu_simpan desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jenkel"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglRen1"),
                        rs.getString("jamRen1"),
                        rs.getString("tglRen2"),
                        rs.getString("jamRen2"),
                        rs.getString("nmPerawat"),
                        rs.getString("sumber_data"),
                        rs.getString("ket_lain_sumber_data"),
                        rs.getString("rujukan"),
                        rs.getString("jenis_rujukan"),
                        rs.getString("diagnosa_rujukan"),
                        rs.getString("keluhan"),
                        rs.getString("nm_identitas"),
                        rs.getString("pendidikan"),
                        rs.getString("pekerjaan"),
                        rs.getString("agama"),
                        rs.getString("alamat"),
                        rs.getString("bbl"),
                        rs.getString("pb"),
                        rs.getString("lk"),
                        rs.getString("ld"),
                        rs.getString("lp"),
                        rs.getString("ll"),
                        rs.getString("kk"),
                        rs.getString("nadi"),
                        rs.getString("rr"),
                        rs.getString("suhu"),
                        rs.getString("jernih"),
                        rs.getString("keruh"),
                        rs.getString("lumpur"),
                        rs.getString("hijau"),
                        rs.getString("berbau"),
                        rs.getString("kering"),
                        rs.getString("anus"),
                        rs.getString("bab"),
                        rs.getString("bak"),
                        rs.getString("kelainan_bawaan"),
                        rs.getString("anak_ke"),
                        rs.getString("umur_kehamilan"),
                        rs.getString("riwayat_penyakit_ibu"),
                        rs.getString("hipertensi"),
                        rs.getString("dm"),
                        rs.getString("pms"),
                        rs.getString("tbc"),
                        rs.getString("asma"),
                        rs.getString("hepatitis_b"),
                        rs.getString("lain_riwayat"),
                        rs.getString("ket_lain_riwayat"),
                        rs.getString("masih_pengobatan"),
                        rs.getString("obat"),
                        rs.getString("diagnosa_ibu"),
                        rs.getString("tgl_lahir"),
                        rs.getString("jam_lahir"),
                        rs.getString("keadaan_saat_lahir"),
                        rs.getString("ket_as"),
                        rs.getString("spontan"),
                        rs.getString("vakum"),
                        rs.getString("forcep"),
                        rs.getString("sectio"),
                        rs.getString("lain_persalinan"),
                        rs.getString("ket_lain_persalinan"),
                        rs.getString("segar"),
                        rs.getString("layu"),
                        rs.getString("simpul"),
                        rs.getString("ibu_demam"),
                        rs.getString("kpd24"),
                        rs.getString("ketuban"),
                        rs.getString("chorio"),
                        rs.getString("fetal"),
                        rs.getString("kpd12"),
                        rs.getString("asfiksia"),
                        rs.getString("bblr"),
                        rs.getString("isk"),
                        rs.getString("uk"),
                        rs.getString("gameli"),
                        rs.getString("keputihan"),
                        rs.getString("suhu_ibu"),
                        rs.getString("riwayat_alergi"),
                        rs.getString("obat_riwayat"),
                        rs.getString("ket_obat_riwayat"),
                        rs.getString("makanan"),
                        rs.getString("ket_makanan"),
                        rs.getString("lain_riwayat_alergi"),
                        rs.getString("ket_lain_riwayat_alergi"),
                        rs.getString("reaksi"),
                        rs.getString("masalah_perkawinan"),
                        rs.getString("ada_perkawinan"),
                        rs.getString("perkawinan_lain"),
                        rs.getString("mengalami_kekerasan"),
                        rs.getString("kekerasan_fisik"),
                        rs.getString("mencederai"),
                        rs.getString("trauma"),
                        rs.getString("ket_trauma"),
                        rs.getString("gangguan_tidur"),
                        rs.getString("konsultasi_psikiater"),
                        rs.getString("penerimaan_terhadap_kondisi"),
                        rs.getString("dukungan_suami"),
                        rs.getString("dukungan_orang_tua"),
                        rs.getString("dukungan_keluarga"),
                        rs.getString("dukungan_lain"),
                        rs.getString("ket_dukungan_lain"),
                        rs.getString("status_pernikahan"),
                        rs.getString("menikah"),
                        rs.getString("hubungan_pasien"),
                        rs.getString("tinggal_bersama"),
                        rs.getString("ket_lain_tinggal_bersama"),
                        rs.getString("tempat_tinggal"),
                        rs.getString("ket_lain_tempat_tinggal"),
                        rs.getString("nm_kerabat"),
                        rs.getString("hubungan_kerabat"),
                        rs.getString("no_tlp"),
                        rs.getString("kegiatan_keagamaan"),
                        rs.getString("kegiatan_spriritual"),
                        rs.getString("nyeri"),
                        rs.getString("crying"),
                        rs.getString("requires"),
                        rs.getString("increased"),
                        rs.getString("expresion"),
                        rs.getString("sleepless"),
                        rs.getString("kesimpulan_penilaian"),
                        rs.getString("sikap_tubuh1"),
                        rs.getString("sikap_tubuh2"),
                        rs.getString("sikap_tubuh3"),
                        rs.getString("sikap_tubuh4"),
                        rs.getString("sikap_tubuh5"),
                        rs.getString("persegi_jendela1"),
                        rs.getString("persegi_jendela2"),
                        rs.getString("persegi_jendela3"),
                        rs.getString("persegi_jendela4"),
                        rs.getString("persegi_jendela5"),
                        rs.getString("persegi_jendela6"),
                        rs.getString("rekoli_lengan1"),
                        rs.getString("rekoli_lengan2"),
                        rs.getString("rekoli_lengan3"),
                        rs.getString("rekoli_lengan4"),
                        rs.getString("rekoli_lengan5"),
                        rs.getString("sudut1"),
                        rs.getString("sudut2"),
                        rs.getString("sudut3"),
                        rs.getString("sudut4"),
                        rs.getString("sudut5"),
                        rs.getString("sudut6"),
                        rs.getString("sudut7"),
                        rs.getString("tanda_selempang1"),
                        rs.getString("tanda_selempang2"),
                        rs.getString("tanda_selempang3"),
                        rs.getString("tanda_selempang4"),
                        rs.getString("tanda_selempang5"),
                        rs.getString("tanda_selempang6"),
                        rs.getString("tumit1"),
                        rs.getString("tumit2"),
                        rs.getString("tumit3"),
                        rs.getString("tumit4"),
                        rs.getString("tumit5"),
                        rs.getString("tumit6"),
                        rs.getString("fisik_kulit"),
                        rs.getString("fisik_payudara"),
                        rs.getString("fisik_mata"),
                        rs.getString("fisik_genital_pria"),
                        rs.getString("fisik_genital_wanita"),
                        rs.getString("fisik_lanugo"),
                        rs.getString("fisik_plantar"),
                        rs.getString("hipotermi"),
                        rs.getString("resiko_hipotermi"),
                        rs.getString("hipertermi"),
                        rs.getString("pola_nafas"),
                        rs.getString("nyeri_masalah_keperawatan"),
                        rs.getString("kerusakan"),
                        rs.getString("resiko_kerusakan"),
                        rs.getString("kebutuhan"),
                        rs.getString("ikterik"),
                        rs.getString("gangguan_motilitas"),
                        rs.getString("bersihan"),
                        rs.getString("resiko_bersihan"),
                        rs.getString("perubahan_perfusi"),
                        rs.getString("kelebihan"),
                        rs.getString("resiko_kelebihan"),
                        rs.getString("resiko_kebutuhan"),
                        rs.getString("masalah_keperawatan_lain"),
                        rs.getString("tgl_rencana1"),
                        rs.getString("jam_rencana1"),
                        rs.getString("tgl_rencana2"),
                        rs.getString("jam_rencana2"),
                        rs.getString("nip_perawat"),
                        rs.getString("waktu_simpan"),
                        rs.getString("cek_tgllahir_ibu"),
                        rs.getString("spo2"),                        
                        rs.getString("nip_verifikator"),
                        rs.getString("nmVerifikator")
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
        cmbSumber.setSelectedIndex(0);
        TlainSumber.setEnabled(false);
        TlainSumber.setText("");
        cmbRujukan.setSelectedIndex(0);
        cmbJnsRujukan.setEnabled(false);
        cmbJnsRujukan.setSelectedIndex(0);
        TdiagnosaRujukan.setText("");
        Tkeluhan.setText("");
        TnmIdentitas.setText("");
        cmbPendidikan.setSelectedIndex(0);
        Tpekerjaan.setText("");
        cmbAgama.setSelectedIndex(0);
        Talamat.setText("");
        Tbbl.setText("");
        Tpb.setText("");
        Tlk.setText("");
        Tld.setText("");
        Tlp.setText("");
        Tll.setText("");
        Tspo.setText("");
        Tkk.setText("");
        Tnadi.setText("");
        Trr.setText("");
        Tsuhu.setText("");
        chkJernih.setSelected(false);
        chkKeruh.setSelected(false);
        chkLumpur.setSelected(false);
        chkHijau.setSelected(false);
        chkBerbau.setSelected(false);
        chkKering.setSelected(false);
        cmbAnus.setSelectedIndex(0);
        cmbBab.setSelectedIndex(0);
        cmbBak.setSelectedIndex(0);
        Tkelainan.setText("");
        TanakKe.setText("");
        TumurHamil.setText("");
        cmbRiwPenyakitIbu.setSelectedIndex(0);
        chkTraumaMekanik.setEnabled(false);
        chkDM.setEnabled(false);
        chkPMS.setEnabled(false);
        chkTBC.setEnabled(false);
        chkAsma.setEnabled(false);
        chkHepB.setEnabled(false);
        chkLainyaRiwayat.setEnabled(false);
        chkTraumaMekanik.setSelected(false);
        chkDM.setSelected(false);
        chkPMS.setSelected(false);
        chkTBC.setSelected(false);
        chkAsma.setSelected(false);
        chkHepB.setSelected(false);        
        chkLainyaRiwayat.setSelected(false);
        TlainRiwayat.setEnabled(false);
        TlainRiwayat.setText("");
        cmbMasih.setSelectedIndex(0);
        Tobat.setEnabled(false);
        Tobat.setText("");
        TdiagnosaIbu.setText("");
        chkTgllahirIbu.setSelected(false);
        TtglLahir.setEnabled(false);
        cmbJam.setEnabled(false);
        cmbMnt.setEnabled(false);
        cmbDtk.setEnabled(false);
        TtglLahir.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        TkeadaanSaat.setText("");
        Tas.setText("");
        chkSpontan.setSelected(false);
        chkVakum.setSelected(false);
        chkForcep.setSelected(false);
        chkSectio.setSelected(false);
        chkLainyaCara.setSelected(false);
        TlainCara.setEnabled(false);
        TlainCara.setText("");
        chkSegar.setSelected(false);
        chkLayu.setSelected(false);
        chkSimpul.setSelected(false);
        chkIbuDemam.setSelected(false);
        chkKpd24.setSelected(false);
        chkKetuban.setSelected(false);
        chkChorio.setSelected(false);
        chkFetal.setSelected(false);
        chkKpd12.setSelected(false);
        chkAsfiksia.setSelected(false);
        chkBblr.setSelected(false);
        chkIsk.setSelected(false);
        chkUK.setSelected(false);
        chkGemeli.setSelected(false);
        chkKeputihan.setSelected(false);
        chkSuhuIbu.setSelected(false);
        cmbRiwAlergi.setSelectedIndex(0);
        chkObatAlergi.setEnabled(false);
        chkMakananAlergi.setEnabled(false);
        chkLainAlergi.setEnabled(false);
        TobatAlergi.setEnabled(false);
        TmakananAlergi.setEnabled(false);
        TlainyaAlergi.setEnabled(false);        
        Treaksi.setEnabled(false);        
        chkObatAlergi.setSelected(false);
        chkMakananAlergi.setSelected(false);
        chkLainAlergi.setSelected(false);
        TobatAlergi.setText("");
        TmakananAlergi.setText("");
        TlainyaAlergi.setText("");
        Treaksi.setText("");
        cmbMasalah.setSelectedIndex(0);
        cmbAdaPerkawinan.setEnabled(false);
        TlainPerkawinan.setEnabled(false);
        cmbAdaPerkawinan.setSelectedIndex(0);
        TlainPerkawinan.setText("");
        cmbMengalami.setSelectedIndex(0);
        cmbAdaMengalami.setEnabled(false);
        cmbDialami.setEnabled(false);
        cmbAdaMengalami.setSelectedIndex(0);
        cmbDialami.setSelectedIndex(0);
        cmbTrauma.setSelectedIndex(0);
        TjelaskanTrauma.setEnabled(false);
        TjelaskanTrauma.setText("");
        cmbGangguan.setSelectedIndex(0);
        cmbKonsultasi.setSelectedIndex(0);
        cmbPenerimaan.setSelectedIndex(0);
        chkSuami.setSelected(false);
        chkOrangTua.setSelected(false);
        chkKeluarga.setSelected(false);
        chkLainDukungan.setSelected(false);
        TlainDukungan.setEnabled(false);
        TlainDukungan.setText("");
        cmbStatusNikah.setSelectedIndex(0);
        TkaliMenikah.setEnabled(false);
        TkaliMenikah.setText("");
        cmbHubungan.setSelectedIndex(0);
        cmbTinggal.setSelectedIndex(0);
        TlainTinggal.setEnabled(false);
        TlainTinggal.setText("");
        cmbTempat.setSelectedIndex(0);
        TlainTempat.setEnabled(false);
        TlainTempat.setText("");
        TnmKerabat.setText("");
        ThubKerabat.setText("");
        TtelpKerabat.setText("");
        TkegiatanAgama.setText("");
        TkegiatanSpiritual.setText("");
        cmbNyeri.setSelectedIndex(0);
        cmbCrying.setEnabled(false);
        cmbRequires.setEnabled(false);
        cmbIncreased.setEnabled(false);
        cmbExpresion.setEnabled(false);
        cmbSleepless.setEnabled(false);
        cmbCrying.setSelectedIndex(0);
        cmbRequires.setSelectedIndex(0);
        cmbIncreased.setSelectedIndex(0);
        cmbExpresion.setSelectedIndex(0);
        cmbSleepless.setSelectedIndex(0);
        TnilaiCrying.setText("");
        TnilaiRequires.setText("");
        TnilaiIncreased.setText("");
        TnilaiExpresion.setText("");
        TnilaiSleepless.setText("");
        TtotNilaiNyeri.setText("0");
        TkesimpulanNyeri.setText("-");
        chkSikap0.setSelected(false);
        chkSikap1.setSelected(false);
        chkSikap2.setSelected(false);
        chkSikap3.setSelected(false);
        chkSikap4.setSelected(false);
        chkPersegi_1.setSelected(false);
        chkPersegi0.setSelected(false);
        chkPersegi1.setSelected(false);
        chkPersegi2.setSelected(false);
        chkPersegi3.setSelected(false);
        chkPersegi4.setSelected(false);
        chkRekoli0.setSelected(false);
        chkRekoli1.setSelected(false);
        chkRekoli2.setSelected(false);
        chkRekoli3.setSelected(false);
        chkRekoli4.setSelected(false);
        chkSudut_1.setSelected(false);
        chkSudut0.setSelected(false);
        chkSudut1.setSelected(false);
        chkSudut2.setSelected(false);
        chkSudut3.setSelected(false);
        chkSudut4.setSelected(false);
        chkSudut5.setSelected(false);
        chkTanda_1.setSelected(false);
        chkTanda0.setSelected(false);
        chkTanda1.setSelected(false);
        chkTanda2.setSelected(false);
        chkTanda3.setSelected(false);
        chkTanda4.setSelected(false);
        chkTumit_1.setSelected(false);
        chkTumit0.setSelected(false);
        chkTumit1.setSelected(false);
        chkTumit2.setSelected(false);
        chkTumit3.setSelected(false);
        chkTumit4.setSelected(false);        
        cmbFisikKulit.setSelectedIndex(0);
        cmbFisikPayudara.setSelectedIndex(0);
        cmbFisikMata.setSelectedIndex(0);
        cmbFisikGenPria.setSelectedIndex(0);
        cmbFisikGenWanita.setSelectedIndex(0);
        cmbFisikLanugo.setSelectedIndex(0);
        cmbFisikPlantar.setSelectedIndex(0);        
        hitungNilaiNyeri();
        hitungNilaiNeomuskular();
        TnilaiSikap.setText("");
        TnilaiPersegi.setText("");
        TnilaiRekoli.setText("");
        TnilaiSudut.setText("");
        TnilaiTanda.setText("");
        TnilaiTumit.setText("");
        TtotNilaiNeomuskular.setText("0");
        TnilaiNeo.setText("0");
        hitungNilaiFisik();
        chkHipotermi.setSelected(false);
        chkResikoHipotermi.setSelected(false);
        chkHipertermi.setSelected(false);
        chkPolaNafas.setSelected(false);
        chkNyeri.setSelected(false);
        chkKerusakan.setSelected(false);
        chkResikoKerusakan.setSelected(false);
        chkKebutuhan.setSelected(false);
        chkIkterik.setSelected(false);
        chkGangguan.setSelected(false);
        chkBersihan.setSelected(false);
        chkResikoBersihan.setSelected(false);
        chkPerubahan.setSelected(false);
        chkKelebihan.setSelected(false);
        chkResikoKelebihan.setSelected(false);
        chkResikoKebutuhan.setSelected(false);
        TdorsalKanan.setText("");        
        TtglRencana1.setDate(new Date());
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        TtglRencana2.setDate(new Date());
        cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk2.setSelectedIndex(0);
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        buttonGroup4.clearSelection();
        buttonGroup5.clearSelection();
        buttonGroup6.clearSelection();
    }
    
    public void setData(String norwt, String rgrawat) {
        TNoRw.setText(norwt);
        TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norwt + "'"));
        TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        TrgRawat.setText(rgrawat);
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(new Date());
        nipVerifikator = "-";
        TnmVerifikator.setText("-");

        if (Sequel.cariIsi("select jk from pasien where no_rkm_medis='" + TNoRM.getText() + "'").equals("L")) {
            cmbJenkel.setSelectedIndex(0);
        } else {
            cmbJenkel.setSelectedIndex(1);
        }
        TCari.setText(norwt);
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnPrint.setEnabled(akses.getcppt());
        BtnEdit.setEnabled(akses.getcppt());
        
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
        if (tbAsesmen.getSelectedRow() != -1) {
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 2).toString());
            cmbJenkel.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 3).toString());
            TrgRawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 5).toString());
            Valid.SetTgl(TtglRencana1, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 184).toString());            
            cmbJam1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 185).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 185).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 185).toString().substring(6, 8));            
            Valid.SetTgl(TtglRencana2, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 186).toString());
            cmbJam2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 187).toString().substring(0, 2));
            cmbMnt2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 187).toString().substring(3, 5));
            cmbDtk2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 187).toString().substring(6, 8));
            TnmPerawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString());
            cmbSumber.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString());
            TlainSumber.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString());
            cmbRujukan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString());
            cmbJnsRujukan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString());
            TdiagnosaRujukan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString());
            Tkeluhan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString());
            TnmIdentitas.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString());
            cmbPendidikan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString());
            Tpekerjaan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 19).toString());
            cmbAgama.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString());
            Talamat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString());
            Tbbl.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString());
            Tpb.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString());
            Tlk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString());
            Tld.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString());
            Tlp.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString());
            Tll.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString());
            Tkk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString());
            Tnadi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString());
            Trr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString());
            Tsuhu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString());
            jernih = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 32).toString();
            keruh = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 33).toString();
            lumpur = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString();
            hijau = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString();
            berbau = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString();
            kering = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString();
            cmbAnus.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString());
            cmbBab.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString());
            cmbBak.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString());
            Tkelainan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString());
            TanakKe.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString());
            TumurHamil.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString());
            cmbRiwPenyakitIbu.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString());
            hipertensi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString();
            dm = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString();
            pms = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString();
            tbc = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString();
            asma = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString();
            hepB = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString();
            lainRiwayatIbu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString();
            TlainRiwayat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString());
            cmbMasih.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString());
            Tobat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString());
            TdiagnosaIbu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString());
            Valid.SetTgl(TtglLahir, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString());
            cmbJam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString().substring(6, 8));
            TkeadaanSaat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString());
            Tas.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString());
            spontan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString();
            vakum = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString();
            forcep = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 62).toString();
            sectio = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString();
            lainCara = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 64).toString();
            TlainCara.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString());
            segar = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 66).toString();
            layu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString();
            simpul = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString();            
            ibuDemam = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString();
            kpd24 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString();
            ketuban = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString();
            chorio = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString();
            fetal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString();            
            kpd12 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString();
            asfiksia = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString();
            bblr = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString();
            isk = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString();
            uk = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString();
            gemeli = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 79).toString();
            keputihan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 80).toString();
            suhuIbu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 81).toString();
            cmbRiwAlergi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 82).toString());            
            obat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 83).toString();
            TobatAlergi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 84).toString());
            makanan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 85).toString();
            TmakananAlergi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 86).toString());
            lainRiwayatAlergi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 87).toString();
            TlainyaAlergi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 88).toString());
            Treaksi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 89).toString());
            cmbMasalah.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 90).toString());
            cmbAdaPerkawinan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 91).toString());
            TlainPerkawinan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 92).toString());
            cmbMengalami.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 93).toString());
            cmbAdaMengalami.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString());
            cmbDialami.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 95).toString());
            cmbTrauma.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 96).toString());
            TjelaskanTrauma.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 97).toString());
            cmbGangguan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 98).toString());
            cmbKonsultasi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 99).toString());
            cmbPenerimaan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 100).toString());            
            suami = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString();
            orangTua = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 102).toString();
            keluarga = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 103).toString();
            lainDukungan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 104).toString();
            TlainDukungan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 105).toString());
            cmbStatusNikah.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 106).toString());            
            TkaliMenikah.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 107).toString());
            cmbHubungan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 108).toString());            
            cmbTinggal.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 109).toString());
            TlainTinggal.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 110).toString());
            cmbTempat.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 111).toString());            
            TlainTempat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 112).toString());
            TnmKerabat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 113).toString());
            ThubKerabat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 114).toString());
            TtelpKerabat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 115).toString());
            TkegiatanAgama.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 116).toString());
            TkegiatanSpiritual.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 117).toString());
            cmbNyeri.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 118).toString());            
            cmbCrying.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 119).toString());
            cmbRequires.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 120).toString());
            cmbIncreased.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 121).toString());
            cmbExpresion.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 122).toString());
            cmbSleepless.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 123).toString());
            hitungNilaiNyeri();
            sikap0 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 125).toString();
            sikap1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 126).toString();
            sikap2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 127).toString();
            sikap3 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 128).toString();
            sikap4 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 129).toString();            
            persegi_1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 130).toString();
            persegi0 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 131).toString();
            persegi1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 132).toString();
            persegi2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 133).toString();
            persegi3 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 134).toString();
            persegi4 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 135).toString();            
            rekoli0 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 136).toString();
            rekoli1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 137).toString();
            rekoli2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 138).toString();
            rekoli3 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 139).toString();
            rekoli4 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 140).toString();            
            sudut_1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 141).toString();
            sudut0 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 142).toString();
            sudut1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 143).toString();
            sudut2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 144).toString();
            sudut3 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 145).toString();
            sudut4 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 146).toString();
            sudut5 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 147).toString();            
            tanda_1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 148).toString();
            tanda0 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 149).toString();
            tanda1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 150).toString();
            tanda2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 151).toString();
            tanda3 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 152).toString();
            tanda4 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 153).toString();            
            tumit_1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 154).toString();
            tumit0 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 155).toString();
            tumit1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 156).toString();
            tumit2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 157).toString();
            tumit3 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 158).toString();
            tumit4 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 159).toString();            
            cmbFisikKulit.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 160).toString());
            cmbFisikPayudara.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 161).toString());
            cmbFisikMata.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 162).toString());
            cmbFisikGenPria.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 163).toString());
            cmbFisikGenWanita.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 164).toString());
            cmbFisikLanugo.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 165).toString());
            cmbFisikPlantar.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 166).toString());            
            hipotermi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 167).toString();
            resikoHipotermi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 168).toString();
            hipertermi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 169).toString();
            pola = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 170).toString();
            nyeri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 171).toString();
            kerusakan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 172).toString();
            resikoKerusakan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 173).toString();
            kebutuhan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 174).toString();            
            ikterik = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 175).toString();
            gangguan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 176).toString();
            bersihan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 177).toString();
            resikoBersihan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 178).toString();
            perubahan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 179).toString();
            kelebihan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 180).toString();
            resikoKelebihan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 181).toString();
            resikoKebutuhan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 182).toString();
            TdorsalKanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 183).toString());
            nip = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 188).toString();
            cekTglLahir = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 190).toString();
            Tspo.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 191).toString());
            nipVerifikator = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 192).toString();
            TnmVerifikator.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 193).toString());
            dataCek();
            hitungNilaiNeomuskular();
            hitungNilaiFisik();
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from asesmen_keperawatan_perinatologi where no_rawat=?", 1, new String[]{
                tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
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
        if (Sequel.mengedittf("asesmen_keperawatan_perinatologi", "no_rawat=?", "sumber_data=?, ket_lain_sumber_data=?, rujukan=?, jenis_rujukan=?, "
                + "diagnosa_rujukan=?, keluhan=?, nm_identitas=?, pendidikan=?, pekerjaan=?, agama=?, alamat=?, bbl=?, pb=?, lk=?, ld=?, lp=?, ll=?, kk=?, nadi=?, rr=?, "
                + "suhu=?, jernih=?, keruh=?, lumpur=?, hijau=?, berbau=?, kering=?, anus=?, bab=?, bak=?, kelainan_bawaan=?, anak_ke=?, umur_kehamilan=?, riwayat_penyakit_ibu=?, "
                + "hipertensi=?, dm=?, pms=?, tbc=?, asma=?, hepatitis_b=?, lain_riwayat=?, ket_lain_riwayat=?, masih_pengobatan=?, obat=?, diagnosa_ibu=?, tgl_lahir=?, jam_lahir=?, "
                + "keadaan_saat_lahir=?, ket_as=?, spontan=?, vakum=?, forcep=?, sectio=?, lain_persalinan=?, ket_lain_persalinan=?, segar=?, layu=?, simpul=?, ibu_demam=?, kpd24=?, "
                + "ketuban=?, chorio=?, fetal=?, kpd12=?, asfiksia=?, bblr=?, isk=?, uk=?, gameli=?, keputihan=?, suhu_ibu=?, riwayat_alergi=?, obat_riwayat=?, ket_obat_riwayat=?, "
                + "makanan=?, ket_makanan=?, lain_riwayat_alergi=?, ket_lain_riwayat_alergi=?, reaksi=?, masalah_perkawinan=?, ada_perkawinan=?, perkawinan_lain=?, mengalami_kekerasan=?, "
                + "kekerasan_fisik=?, mencederai=?, trauma=?, ket_trauma=?, gangguan_tidur=?, konsultasi_psikiater=?, penerimaan_terhadap_kondisi=?, dukungan_suami=?, dukungan_orang_tua=?, "
                + "dukungan_keluarga=?, dukungan_lain=?, ket_dukungan_lain=?, status_pernikahan=?, menikah=?, hubungan_pasien=?, tinggal_bersama=?, ket_lain_tinggal_bersama=?, tempat_tinggal=?, "
                + "ket_lain_tempat_tinggal=?, nm_kerabat=?, hubungan_kerabat=?, no_tlp=?, kegiatan_keagamaan=?, kegiatan_spriritual=?, nyeri=?, crying=?, requires=?, increased=?, expresion=?, "
                + "sleepless=?, kesimpulan_penilaian=?, sikap_tubuh1=?, sikap_tubuh2=?, sikap_tubuh3=?, sikap_tubuh4=?, sikap_tubuh5=?, persegi_jendela1=?, persegi_jendela2=?, persegi_jendela3=?, "
                + "persegi_jendela4=?, persegi_jendela5=?, persegi_jendela6=?, rekoli_lengan1=?, rekoli_lengan2=?, rekoli_lengan3=?, rekoli_lengan4=?, rekoli_lengan5=?, sudut1=?, sudut2=?, "
                + "sudut3=?, sudut4=?, sudut5=?, sudut6=?, sudut7=?, tanda_selempang1=?, tanda_selempang2=?, tanda_selempang3=?, tanda_selempang4=?, tanda_selempang5=?, tanda_selempang6=?, "
                + "tumit1=?, tumit2=?, tumit3=?, tumit4=?, tumit5=?, tumit6=?, fisik_kulit=?, fisik_payudara=?, fisik_mata=?, fisik_genital_pria=?, fisik_genital_wanita=?, fisik_lanugo=?, "
                + "fisik_plantar=?, hipotermi=?, resiko_hipotermi=?, hipertermi=?, pola_nafas=?, nyeri_masalah_keperawatan=?, kerusakan=?, resiko_kerusakan=?, kebutuhan=?, ikterik=?, "
                + "gangguan_motilitas=?, bersihan=?, resiko_bersihan=?, perubahan_perfusi=?, kelebihan=?, resiko_kelebihan=?, resiko_kebutuhan=?, masalah_keperawatan_lain=?, tgl_rencana1=?, "
                + "jam_rencana1=?, tgl_rencana2=?, jam_rencana2=?, nip_perawat=?, cek_tgllahir_ibu=?, spo2=?, nip_verifikator=?", 182, new String[]{
                    cmbSumber.getSelectedItem().toString(), TlainSumber.getText(), cmbRujukan.getSelectedItem().toString(),
                    cmbJnsRujukan.getSelectedItem().toString(), TdiagnosaRujukan.getText(), Tkeluhan.getText(), TnmIdentitas.getText(), cmbPendidikan.getSelectedItem().toString(),
                    Tpekerjaan.getText(), cmbAgama.getSelectedItem().toString(), Talamat.getText(), Tbbl.getText(), Tpb.getText(), Tlk.getText(), Tld.getText(),
                    Tlp.getText(), Tll.getText(), Tkk.getText(), Tnadi.getText(), Trr.getText(), Tsuhu.getText(), jernih, keruh, lumpur, hijau, berbau, kering,
                    cmbAnus.getSelectedItem().toString(), cmbBab.getSelectedItem().toString(), cmbBak.getSelectedItem().toString(), Tkelainan.getText(), TanakKe.getText(),
                    TumurHamil.getText(), cmbRiwPenyakitIbu.getSelectedItem().toString(), hipertensi, dm, pms, tbc, asma, hepB, lainRiwayatIbu, TlainRiwayat.getText(),
                    cmbMasih.getSelectedItem().toString(), Tobat.getText(), TdiagnosaIbu.getText(), Valid.SetTgl(TtglLahir.getSelectedItem() + ""), jamlahiribu, 
                    TkeadaanSaat.getText(), Tas.getText(), spontan, vakum, forcep, sectio, lainCara, TlainCara.getText(), segar, layu, simpul, ibuDemam, kpd24, ketuban, 
                    chorio, fetal, kpd12, asfiksia, bblr, isk, uk, gemeli, keputihan, suhuIbu, cmbRiwAlergi.getSelectedItem().toString(), obat, TobatAlergi.getText(), makanan, 
                    TmakananAlergi.getText(), lainRiwayatAlergi, TlainyaAlergi.getText(), Treaksi.getText(), cmbMasalah.getSelectedItem().toString(), cmbAdaPerkawinan.getSelectedItem().toString(), 
                    TlainPerkawinan.getText(), cmbMengalami.getSelectedItem().toString(), cmbAdaMengalami.getSelectedItem().toString(), cmbDialami.getSelectedItem().toString(), 
                    cmbTrauma.getSelectedItem().toString(), TjelaskanTrauma.getText(), cmbGangguan.getSelectedItem().toString(), cmbKonsultasi.getSelectedItem().toString(), 
                    cmbPenerimaan.getSelectedItem().toString(), suami, orangTua, keluarga, lainDukungan, TlainDukungan.getText(), cmbStatusNikah.getSelectedItem().toString(), 
                    TkaliMenikah.getText(), cmbHubungan.getSelectedItem().toString(), cmbTinggal.getSelectedItem().toString(), TlainTinggal.getText(), cmbTempat.getSelectedItem().toString(),
                    TlainTempat.getText(), TnmKerabat.getText(), ThubKerabat.getText(), TtelpKerabat.getText(), TkegiatanAgama.getText(), TkegiatanSpiritual.getText(),
                    cmbNyeri.getSelectedItem().toString(), cmbCrying.getSelectedItem().toString(), cmbRequires.getSelectedItem().toString(), cmbIncreased.getSelectedItem().toString(),
                    cmbExpresion.getSelectedItem().toString(), cmbSleepless.getSelectedItem().toString(), TkesimpulanNyeri.getText(), sikap0, sikap1, sikap2, sikap3, sikap4,
                    persegi_1, persegi0, persegi1, persegi2, persegi3, persegi4, rekoli0, rekoli1, rekoli2, rekoli3, rekoli4, sudut_1, sudut0, sudut1, sudut2, sudut3, sudut4, sudut5,
                    tanda_1, tanda0, tanda1, tanda2, tanda3, tanda4, tumit_1, tumit0, tumit1, tumit2, tumit3, tumit4, cmbFisikKulit.getSelectedItem().toString(),
                    cmbFisikPayudara.getSelectedItem().toString(), cmbFisikMata.getSelectedItem().toString(), cmbFisikGenPria.getSelectedItem().toString(),
                    cmbFisikGenWanita.getSelectedItem().toString(), cmbFisikLanugo.getSelectedItem().toString(), cmbFisikPlantar.getSelectedItem().toString(),
                    hipotermi, resikoHipotermi, hipertermi, pola, nyeri, kerusakan, resikoKerusakan, kebutuhan, ikterik, gangguan, bersihan, resikoBersihan, perubahan,
                    kelebihan, resikoKelebihan, resikoKebutuhan, TdorsalKanan.getText(), Valid.SetTgl(TtglRencana1.getSelectedItem() + ""),
                    cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), Valid.SetTgl(TtglRencana2.getSelectedItem() + ""),
                    cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), nip, cekTglLahir, Tspo.getText(), nipVerifikator,
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                }) == true) {

            Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Assesmen Keperawatan Perinatologi", "Ganti");
            TabRawat.setSelectedIndex(1);
            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
        }
    }
    
    private void cekData() {
        if (nipVerifikator.equals("")) {
            nipVerifikator = "-";
        }

        //amnion
        if (chkJernih.isSelected() == true) {
            jernih = "ya";
        } else {
            jernih = "tidak";
        }
        
        if (chkKeruh.isSelected() == true) {
            keruh = "ya";
        } else {
            keruh = "tidak";
        }
        
        if (chkLumpur.isSelected() == true) {
            lumpur = "ya";
        } else {
            lumpur = "tidak";
        }
        
        if (chkHijau.isSelected() == true) {
            hijau = "ya";
        } else {
            hijau = "tidak";
        }
        
        if (chkBerbau.isSelected() == true) {
            berbau = "ya";
        } else {
            berbau = "tidak";
        }
        
        if (chkKering.isSelected() == true) {
            kering = "ya";
        } else {
            kering = "tidak";
        }
        
        //riwayat penyakit ibu
        if (chkTraumaMekanik.isSelected() == true) {
            hipertensi = "ya";
        } else {
            hipertensi = "tidak";
        }
        
        if (chkDM.isSelected() == true) {
            dm = "ya";
        } else {
            dm = "tidak";
        }
        
        if (chkPMS.isSelected() == true) {
            pms = "ya";
        } else {
            pms = "tidak";
        }
        
        if (chkTBC.isSelected() == true) {
            tbc = "ya";
        } else {
            tbc = "tidak";
        }
        
        if (chkAsma.isSelected() == true) {
            asma = "ya";
        } else {
            asma = "tidak";
        }
        
        if (chkHepB.isSelected() == true) {
            hepB = "ya";
        } else {
            hepB = "tidak";
        }
        
        if (chkLainyaRiwayat.isSelected() == true) {
            lainRiwayatIbu = "ya";
        } else {
            lainRiwayatIbu = "tidak";
        }
        
        if (chkTgllahirIbu.isSelected() == true) {
            cekTglLahir = "ya";
            jamlahiribu = cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem();
        } else {
            cekTglLahir = "tidak";
            jamlahiribu = "00:00:00";
        }
        
        //cara persalinan
        if (chkSpontan.isSelected() == true) {
            spontan = "ya";
        } else {
            spontan = "tidak";
        }
        
        if (chkVakum.isSelected() == true) {
            vakum = "ya";
        } else {
            vakum = "tidak";
        }
        
        if (chkForcep.isSelected() == true) {
            forcep = "ya";
        } else {
            forcep = "tidak";
        }
        
        if (chkSectio.isSelected() == true) {
            sectio = "ya";
        } else {
            sectio = "tidak";
        }
        
        if (chkLainyaCara.isSelected() == true) {
            lainCara = "ya";
        } else {
            lainCara = "tidak";
        }
        
        //tali pusat
        if (chkSegar.isSelected() == true) {
            segar = "ya";
        } else {
            segar = "tidak";
        }
        
        if (chkLayu.isSelected() == true) {
            layu = "ya";
        } else {
            layu = "tidak";
        }
        
        if (chkSimpul.isSelected() == true) {
            simpul = "ya";
        } else {
            simpul = "tidak";
        }
        
        //mayor
        if (chkIbuDemam.isSelected() == true) {
            ibuDemam = "ya";
        } else {
            ibuDemam = "tidak";
        }
        
        if (chkKpd24.isSelected() == true) {
            kpd24 = "ya";
        } else {
            kpd24 = "tidak";
        }
        
        if (chkKetuban.isSelected() == true) {
            ketuban = "ya";
        } else {
            ketuban = "tidak";
        }
        
        if (chkChorio.isSelected() == true) {
            chorio = "ya";
        } else {
            chorio = "tidak";
        }
        
        if (chkFetal.isSelected() == true) {
            fetal = "ya";
        } else {
            fetal = "tidak";
        }
        
        //minor
        if (chkKpd12.isSelected() == true) {
            kpd12 = "ya";
        } else {
            kpd12 = "tidak";
        }
        
        if (chkAsfiksia.isSelected() == true) {
            asfiksia = "ya";
        } else {
            asfiksia = "tidak";
        }
        
        if (chkBblr.isSelected() == true) {
            bblr = "ya";
        } else {
            bblr = "tidak";
        }
        
        if (chkIsk.isSelected() == true) {
            isk = "ya";
        } else {
            isk = "tidak";
        }
        
        if (chkUK.isSelected() == true) {
            uk = "ya";
        } else {
            uk = "tidak";
        }
        
        if (chkGemeli.isSelected() == true) {
            gemeli = "ya";
        } else {
            gemeli = "tidak";
        }
        
        if (chkKeputihan.isSelected() == true) {
            keputihan = "ya";
        } else {
            keputihan = "tidak";
        }
        
        if (chkSuhuIbu.isSelected() == true) {
            suhuIbu = "ya";
        } else {
            suhuIbu = "tidak";
        }
        
        //riwayat alergi
        if (chkObatAlergi.isSelected() == true) {
            obat = "ya";
        } else {
            obat = "tidak";
        }
        
        if (chkMakananAlergi.isSelected() == true) {
            makanan = "ya";
        } else {
            makanan = "tidak";
        }
        
        if (chkLainAlergi.isSelected() == true) {
            lainRiwayatAlergi = "ya";
        } else {
            lainRiwayatAlergi = "tidak";
        }
        
        //dukungan sosial
        if (chkSuami.isSelected() == true) {
            suami = "ya";
        } else {
            suami = "tidak";
        }
        
        if (chkOrangTua.isSelected() == true) {
            orangTua = "ya";
        } else {
            orangTua = "tidak";
        }
        
        if (chkKeluarga.isSelected() == true) {
            keluarga = "ya";
        } else {
            keluarga = "tidak";
        }
        
        if (chkLainDukungan.isSelected() == true) {
            lainDukungan = "ya";
        } else {
            lainDukungan = "tidak";
        }
        
        //sikap tubuh
        if (chkSikap0.isSelected() == true) {
            sikap0 = "ya";
        } else {
            sikap0 = "tidak";
        }
        
        if (chkSikap1.isSelected() == true) {
            sikap1 = "ya";
        } else {
            sikap1 = "tidak";
        }
        
        if (chkSikap2.isSelected() == true) {
            sikap2 = "ya";
        } else {
            sikap2 = "tidak";
        }
        
        if (chkSikap3.isSelected() == true) {
            sikap3 = "ya";
        } else {
            sikap3 = "tidak";
        }
        
        if (chkSikap4.isSelected() == true) {
            sikap4 = "ya";
        } else {
            sikap4 = "tidak";
        }
        
        //persegi jendela
        if (chkPersegi_1.isSelected() == true) {
            persegi_1 = "ya";
        } else {
            persegi_1 = "tidak";
        }
        
        if (chkPersegi0.isSelected() == true) {
            persegi0 = "ya";
        } else {
            persegi0 = "tidak";
        }
        
        if (chkPersegi1.isSelected() == true) {
            persegi1 = "ya";
        } else {
            persegi1 = "tidak";
        }
        
        if (chkPersegi2.isSelected() == true) {
            persegi2 = "ya";
        } else {
            persegi2 = "tidak";
        }
        
        if (chkPersegi3.isSelected() == true) {
            persegi3 = "ya";
        } else {
            persegi3 = "tidak";
        }
        
        if (chkPersegi4.isSelected() == true) {
            persegi4 = "ya";
        } else {
            persegi4 = "tidak";
        }
        
        //rekoli lengan
        if (chkRekoli0.isSelected() == true) {
            rekoli0 = "ya";
        } else {
            rekoli0 = "tidak";
        }
        
        if (chkRekoli1.isSelected() == true) {
            rekoli1 = "ya";
        } else {
            rekoli1 = "tidak";
        }
        
        if (chkRekoli2.isSelected() == true) {
            rekoli2 = "ya";
        } else {
            rekoli2 = "tidak";
        }
        
        if (chkRekoli3.isSelected() == true) {
            rekoli3 = "ya";
        } else {
            rekoli3 = "tidak";
        }
        
        if (chkRekoli4.isSelected() == true) {
            rekoli4 = "ya";
        } else {
            rekoli4 = "tidak";
        }
        
        //sudut popliteal
        if (chkSudut_1.isSelected() == true) {
            sudut_1 = "ya";
        } else {
            sudut_1 = "tidak";
        }
        
        if (chkSudut0.isSelected() == true) {
            sudut0 = "ya";
        } else {
            sudut0 = "tidak";
        }
        
        if (chkSudut1.isSelected() == true) {
            sudut1 = "ya";
        } else {
            sudut1 = "tidak";
        }
        
        if (chkSudut2.isSelected() == true) {
            sudut2 = "ya";
        } else {
            sudut2 = "tidak";
        }
        
        if (chkSudut3.isSelected() == true) {
            sudut3 = "ya";
        } else {
            sudut3 = "tidak";
        }
        
        if (chkSudut4.isSelected() == true) {
            sudut4 = "ya";
        } else {
            sudut4 = "tidak";
        }
        
        if (chkSudut5.isSelected() == true) {
            sudut5 = "ya";
        } else {
            sudut5 = "tidak";
        }
        
        //tanda selempang
        if (chkTanda_1.isSelected() == true) {
            tanda_1 = "ya";
        } else {
            tanda_1 = "tidak";
        }
        
        if (chkTanda0.isSelected() == true) {
            tanda0 = "ya";
        } else {
            tanda0 = "tidak";
        }
        
        if (chkTanda1.isSelected() == true) {
            tanda1 = "ya";
        } else {
            tanda1 = "tidak";
        }
        
        if (chkTanda2.isSelected() == true) {
            tanda2 = "ya";
        } else {
            tanda2 = "tidak";
        }
        
        if (chkTanda3.isSelected() == true) {
            tanda3 = "ya";
        } else {
            tanda3 = "tidak";
        }
        
        if (chkTanda4.isSelected() == true) {
            tanda4 = "ya";
        } else {
            tanda4 = "tidak";
        }
        
        //tumit kekuping
        if (chkTumit_1.isSelected() == true) {
            tumit_1 = "ya";
        } else {
            tumit_1 = "tidak";
        }
        
        if (chkTumit0.isSelected() == true) {
            tumit0 = "ya";
        } else {
            tumit0 = "tidak";
        }
        
        if (chkTumit1.isSelected() == true) {
            tumit1 = "ya";
        } else {
            tumit1 = "tidak";
        }
        
        if (chkTumit2.isSelected() == true) {
            tumit2 = "ya";
        } else {
            tumit2 = "tidak";
        }
        
        if (chkTumit3.isSelected() == true) {
            tumit3 = "ya";
        } else {
            tumit3 = "tidak";
        }
        
        if (chkTumit4.isSelected() == true) {
            tumit4 = "ya";
        } else {
            tumit4 = "tidak";
        }
        
        //masalah keperawatan
        if (chkHipotermi.isSelected() == true) {
            hipotermi = "ya";
        } else {
            hipotermi = "tidak";
        }
        
        if (chkResikoHipotermi.isSelected() == true) {
            resikoHipotermi = "ya";
        } else {
            resikoHipotermi = "tidak";
        }
        
        if (chkHipertermi.isSelected() == true) {
            hipertermi = "ya";
        } else {
            hipertermi = "tidak";
        }
        
        if (chkPolaNafas.isSelected() == true) {
            pola = "ya";
        } else {
            pola = "tidak";
        }
        
        if (chkNyeri.isSelected() == true) {
            nyeri = "ya";
        } else {
            nyeri = "tidak";
        }
        
        if (chkKerusakan.isSelected() == true) {
            kerusakan = "ya";
        } else {
            kerusakan = "tidak";
        }
        
        if (chkResikoKerusakan.isSelected() == true) {
            resikoKerusakan = "ya";
        } else {
            resikoKerusakan = "tidak";
        }
        
        if (chkKebutuhan.isSelected() == true) {
            kebutuhan = "ya";
        } else {
            kebutuhan = "tidak";
        }
        
        if (chkIkterik.isSelected() == true) {
            ikterik = "ya";
        } else {
            ikterik = "tidak";
        }
        
        if (chkGangguan.isSelected() == true) {
            gangguan = "ya";
        } else {
            gangguan = "tidak";
        }
        
        if (chkBersihan.isSelected() == true) {
            bersihan = "ya";
        } else {
            bersihan = "tidak";
        }
        
        if (chkResikoBersihan.isSelected() == true) {
            resikoBersihan = "ya";
        } else {
            resikoBersihan = "tidak";
        }
        
        if (chkPerubahan.isSelected() == true) {
            perubahan = "ya";
        } else {
            perubahan = "tidak";
        }
        
        if (chkKelebihan.isSelected() == true) {
            kelebihan = "ya";
        } else {
            kelebihan = "tidak";
        }
        
        if (chkResikoKelebihan.isSelected() == true) {
            resikoKelebihan = "ya";
        } else {
            resikoKelebihan = "tidak";
        }
        
        if (chkResikoKebutuhan.isSelected() == true) {
            resikoKebutuhan = "ya";
        } else {
            resikoKebutuhan = "tidak";
        }
    }
    
    private void dataCek() {
        //combo box
        if (cmbSumber.getSelectedIndex() == 3) {
            TlainSumber.setEnabled(true);
        } else {
            TlainSumber.setEnabled(false);
        }
        
        if (cmbRujukan.getSelectedIndex() == 1) {
            cmbJnsRujukan.setEnabled(true);
        } else {
            cmbJnsRujukan.setEnabled(false);
        }
        
        if (cmbRiwPenyakitIbu.getSelectedIndex() == 2) {
            chkTraumaMekanik.setEnabled(true);
            chkDM.setEnabled(true);
            chkPMS.setEnabled(true);
            chkTBC.setEnabled(true);
            chkAsma.setEnabled(true);
            chkHepB.setEnabled(true);
            chkLainyaRiwayat.setEnabled(true);
        } else {
            chkTraumaMekanik.setEnabled(false);
            chkDM.setEnabled(false);
            chkPMS.setEnabled(false);
            chkTBC.setEnabled(false);
            chkAsma.setEnabled(false);
            chkHepB.setEnabled(false);
            chkLainyaRiwayat.setEnabled(false);
        }
        
        if (cmbMasih.getSelectedIndex() == 2) {
            Tobat.setEnabled(true);
        } else {
            Tobat.setEnabled(false);
        }
        
        if (cmbRiwAlergi.getSelectedIndex() == 2) {
            chkObatAlergi.setEnabled(true);
            chkMakananAlergi.setEnabled(true);
            chkLainAlergi.setEnabled(true);
            Treaksi.setEnabled(true);
        } else {
            chkObatAlergi.setEnabled(false);
            chkMakananAlergi.setEnabled(false);
            chkLainAlergi.setEnabled(false); 
            Treaksi.setEnabled(false);
        }
        
        if (cmbMasalah.getSelectedIndex() == 1) {
            cmbAdaPerkawinan.setEnabled(true);
            TlainPerkawinan.setEnabled(true);
        } else {
            cmbAdaPerkawinan.setEnabled(false);
            TlainPerkawinan.setEnabled(false);
        }
        
        if (cmbAdaPerkawinan.getSelectedIndex() == 4) {
            TlainPerkawinan.setEnabled(true);
        } else {
            TlainPerkawinan.setEnabled(false);
        }
        
        if (cmbMengalami.getSelectedIndex() == 1) {
            cmbAdaMengalami.setEnabled(true);
            cmbDialami.setEnabled(true);
        } else {
            cmbAdaMengalami.setEnabled(false);
            cmbDialami.setEnabled(false);
        }
        
        if (cmbTrauma.getSelectedIndex() == 1) {
            TjelaskanTrauma.setEnabled(true);
        } else {
            TjelaskanTrauma.setEnabled(false);
        }
        
        if (cmbStatusNikah.getSelectedIndex() == 2) {
            TkaliMenikah.setEnabled(true);
        } else {
            TkaliMenikah.setEnabled(false);
        }
        
        if (cmbTinggal.getSelectedIndex() == 7) {
            TlainTinggal.setEnabled(true);
        } else {
            TlainTinggal.setEnabled(false);
        }
        
        if (cmbTempat.getSelectedIndex() == 4) {
            TlainTempat.setEnabled(true);
        } else {
            TlainTempat.setEnabled(false);
        }
        
        if (cmbNyeri.getSelectedIndex() == 2) {
            cmbCrying.setEnabled(true);
            cmbRequires.setEnabled(true);
            cmbIncreased.setEnabled(true);
            cmbExpresion.setEnabled(true);
            cmbSleepless.setEnabled(true);
        } else {
            cmbCrying.setEnabled(false);
            cmbRequires.setEnabled(false);
            cmbIncreased.setEnabled(false);
            cmbExpresion.setEnabled(false);
            cmbSleepless.setEnabled(false);
        }
        
        //amnion
        if (jernih.equals("ya")) {
            chkJernih.setSelected(true);
        } else {
            chkJernih.setSelected(false);
        }
        
        if (keruh.equals("ya")) {
            chkKeruh.setSelected(true);
        } else {
            chkKeruh.setSelected(false);
        }
        
        if (lumpur.equals("ya")) {
            chkLumpur.setSelected(true);
        } else {
            chkLumpur.setSelected(false);
        }
        
        if (hijau.equals("ya")) {
            chkHijau.setSelected(true);
        } else {
            chkHijau.setSelected(false);
        }
        
        if (berbau.equals("ya")) {
            chkBerbau.setSelected(true);
        } else {
            chkBerbau.setSelected(false);
        }
        
        if (kering.equals("ya")) {
            chkKering.setSelected(true);
        } else {
            chkKering.setSelected(false);
        }
        
        //riwayat penyakit ibu
        if (hipertensi.equals("ya")) {
            chkTraumaMekanik.setSelected(true);
        } else {
            chkTraumaMekanik.setSelected(false);
        }
        
        if (dm.equals("ya")) {
            chkDM.setSelected(true);
        } else {
            chkDM.setSelected(false);
        }
        
        if (pms.equals("ya")) {
            chkPMS.setSelected(true);
        } else {
            chkPMS.setSelected(false);
        }
        
        if (tbc.equals("ya")) {
            chkTBC.setSelected(true);
        } else {
            chkTBC.setSelected(false);
        }
        
        if (asma.equals("ya")) {
            chkAsma.setSelected(true);
        } else {
            chkAsma.setSelected(false);
        }
        
        if (hepB.equals("ya")) {
            chkHepB.setSelected(true);
        } else {
            chkHepB.setSelected(false);
        }
        
        if (lainRiwayatIbu.equals("ya")) {
            chkLainyaRiwayat.setSelected(true);
            TlainRiwayat.setEnabled(true);
        } else {
            chkLainyaRiwayat.setSelected(false);
            TlainRiwayat.setEnabled(false);
        }
        
        if (cekTglLahir.equals("ya")) {
            chkTgllahirIbu.setSelected(true);
            TtglLahir.setEnabled(true);
            cmbJam.setEnabled(true);
            cmbMnt.setEnabled(true);
            cmbDtk.setEnabled(true);
        } else {
            chkTgllahirIbu.setSelected(false);
            TtglLahir.setEnabled(false);
            cmbJam.setEnabled(false);
            cmbMnt.setEnabled(false);
            cmbDtk.setEnabled(false);
        }

        //cara persalinan
        if (spontan.equals("ya")) {
            chkSpontan.setSelected(true);
        } else {
            chkSpontan.setSelected(false);
        }
        
        if (vakum.equals("ya")) {
            chkVakum.setSelected(true);
        } else {
            chkVakum.setSelected(false);
        }
        
        if (forcep.equals("ya")) {
            chkForcep.setSelected(true);
        } else {
            chkForcep.setSelected(false);
        }
        
        if (sectio.equals("ya")) {
            chkSectio.setSelected(true);
        } else {
            chkSectio.setSelected(false);
        }
        
        if (lainCara.equals("ya")) {
            chkLainyaCara.setSelected(true);
            TlainCara.setEnabled(true);
        } else {
            chkLainyaCara.setSelected(false);
            TlainCara.setEnabled(false);
        }
        
        //tali pusat
        if (segar.equals("ya")) {
            chkSegar.setSelected(true);
        } else {
            chkSegar.setSelected(false);
        }
        
        if (layu.equals("ya")) {
            chkLayu.setSelected(true);
        } else {
            chkLayu.setSelected(false);
        }
        
        if (simpul.equals("ya")) {
            chkSimpul.setSelected(true);
        } else {
            chkSimpul.setSelected(false);
        }
        
        //mayor
        if (ibuDemam.equals("ya")) {
            chkIbuDemam.setSelected(true);
        } else {
            chkIbuDemam.setSelected(false);
        }
        
        if (kpd24.equals("ya")) {
            chkKpd24.setSelected(true);
        } else {
            chkKpd24.setSelected(false);
        }
        
        if (ketuban.equals("ya")) {
            chkKetuban.setSelected(true);
        } else {
            chkKetuban.setSelected(false);
        }
        
        if (chorio.equals("ya")) {
            chkChorio.setSelected(true);
        } else {
            chkChorio.setSelected(false);
        }
        
        if (fetal.equals("ya")) {
            chkFetal.setSelected(true);
        } else {
            chkFetal.setSelected(false);
        }
        
        //minor
        if (kpd12.equals("ya")) {
            chkKpd12.setSelected(true);
        } else {
            chkKpd12.setSelected(false);
        }
        
        if (asfiksia.equals("ya")) {
            chkAsfiksia.setSelected(true);
        } else {
            chkAsfiksia.setSelected(false);
        }
        
        if (bblr.equals("ya")) {
            chkBblr.setSelected(true);
        } else {
            chkBblr.setSelected(false);
        }
        
        if (isk.equals("ya")) {
            chkIsk.setSelected(true);
        } else {
            chkIsk.setSelected(false);
        }
        
        if (uk.equals("ya")) {
            chkUK.setSelected(true);
        } else {
            chkUK.setSelected(false);
        }
        
        if (gemeli.equals("ya")) {
            chkGemeli.setSelected(true);
        } else {
            chkGemeli.setSelected(false);
        }
        
        if (keputihan.equals("ya")) {
            chkKeputihan.setSelected(true);
        } else {
            chkKeputihan.setSelected(false);
        }
        
        if (suhuIbu.equals("ya")) {
            chkSuhuIbu.setSelected(true);
        } else {
            chkSuhuIbu.setSelected(false);
        }
        
        //riwayat alergi
        if (obat.equals("ya")) {
            chkObatAlergi.setSelected(true);
            TobatAlergi.setEnabled(true);
        } else {
            chkObatAlergi.setSelected(false);
            TobatAlergi.setEnabled(false);
        }
        
        if (makanan.equals("ya")) {
            chkMakananAlergi.setSelected(true);
            TmakananAlergi.setEnabled(true);
        } else {
            chkMakananAlergi.setSelected(false);
            TmakananAlergi.setEnabled(false);
        }
        
        if (lainRiwayatAlergi.equals("ya")) {
            chkLainAlergi.setSelected(true);
            TlainyaAlergi.setEnabled(true);
        } else {
            chkLainAlergi.setSelected(false);
            TlainyaAlergi.setEnabled(false);
        }
        
        //dukungan sosial
        if (suami.equals("ya")) {
            chkSuami.setSelected(true);
        } else {
            chkSuami.setSelected(false);
        }
        
        if (orangTua.equals("ya")) {
            chkOrangTua.setSelected(true);
        } else {
            chkOrangTua.setSelected(false);
        }
        
        if (keluarga.equals("ya")) {
            chkKeluarga.setSelected(true);
        } else {
            chkKeluarga.setSelected(false);
        }
        
        if (lainDukungan.equals("ya")) {
            chkLainDukungan.setSelected(true);
            TlainDukungan.setEnabled(true);
        } else {
            chkLainDukungan.setSelected(false);
            TlainDukungan.setEnabled(false);
        }
        
        //sikap tubuh
        if (sikap0.equals("ya")) {
            chkSikap0.setSelected(true);
        } else {
            chkSikap0.setSelected(false);
        }
        
        if (sikap1.equals("ya")) {
            chkSikap1.setSelected(true);
        } else {
            chkSikap1.setSelected(false);
        }
        
        if (sikap2.equals("ya")) {
            chkSikap2.setSelected(true);
        } else {
            chkSikap2.setSelected(false);
        }
        
        if (sikap3.equals("ya")) {
            chkSikap3.setSelected(true);
        } else {
            chkSikap3.setSelected(false);
        }
        
        if (sikap4.equals("ya")) {
            chkSikap4.setSelected(true);
        } else {
            chkSikap4.setSelected(false);
        }
        
        //persegi jendela
        if (persegi_1.equals("ya")) {
            chkPersegi_1.setSelected(true);
        } else {
            chkPersegi_1.setSelected(false);
        }
        
        if (persegi0.equals("ya")) {
            chkPersegi0.setSelected(true);
        } else {
            chkPersegi0.setSelected(false);
        }
        
        if (persegi1.equals("ya")) {
            chkPersegi1.setSelected(true);
        } else {
            chkPersegi1.setSelected(false);
        }
        
        if (persegi2.equals("ya")) {
            chkPersegi2.setSelected(true);
        } else {
            chkPersegi2.setSelected(false);
        }
        
        if (persegi3.equals("ya")) {
            chkPersegi3.setSelected(true);
        } else {
            chkPersegi3.setSelected(false);
        }
        
        if (persegi4.equals("ya")) {
            chkPersegi4.setSelected(true);
        } else {
            chkPersegi4.setSelected(false);
        }
        
        //rekoli lengan
        if (rekoli0.equals("ya")) {
            chkRekoli0.setSelected(true);
        } else {
            chkRekoli0.setSelected(false);
        }
        
        if (rekoli1.equals("ya")) {
            chkRekoli1.setSelected(true);
        } else {
            chkRekoli1.setSelected(false);
        }
        
        if (rekoli2.equals("ya")) {
            chkRekoli2.setSelected(true);
        } else {
            chkRekoli2.setSelected(false);
        }
        
        if (rekoli3.equals("ya")) {
            chkRekoli3.setSelected(true);
        } else {
            chkRekoli3.setSelected(false);
        }
        
        if (rekoli4.equals("ya")) {
            chkRekoli4.setSelected(true);
        } else {
            chkRekoli4.setSelected(false);
        }
        
        //sudut popliteal
        if (sudut_1.equals("ya")) {
            chkSudut_1.setSelected(true);
        } else {
            chkSudut_1.setSelected(false);
        }
        
        if (sudut0.equals("ya")) {
            chkSudut0.setSelected(true);
        } else {
            chkSudut0.setSelected(false);
        }
        
        if (sudut1.equals("ya")) {
            chkSudut1.setSelected(true);
        } else {
            chkSudut1.setSelected(false);
        }
        
        if (sudut2.equals("ya")) {
            chkSudut2.setSelected(true);
        } else {
            chkSudut2.setSelected(false);
        }
        
        if (sudut3.equals("ya")) {
            chkSudut3.setSelected(true);
        } else {
            chkSudut3.setSelected(false);
        }
        
        if (sudut4.equals("ya")) {
            chkSudut4.setSelected(true);
        } else {
            chkSudut4.setSelected(false);
        }
        
        if (sudut5.equals("ya")) {
            chkSudut5.setSelected(true);
        } else {
            chkSudut5.setSelected(false);
        }
        
        //tanda selempang
        if (tanda_1.equals("ya")) {
            chkTanda_1.setSelected(true);
        } else {
            chkTanda_1.setSelected(false);
        }
        
        if (tanda0.equals("ya")) {
            chkTanda0.setSelected(true);
        } else {
            chkTanda0.setSelected(false);
        }
        
        if (tanda1.equals("ya")) {
            chkTanda1.setSelected(true);
        } else {
            chkTanda1.setSelected(false);
        }
        
        if (tanda2.equals("ya")) {
            chkTanda2.setSelected(true);
        } else {
            chkTanda2.setSelected(false);
        }
        
        if (tanda3.equals("ya")) {
            chkTanda3.setSelected(true);
        } else {
            chkTanda3.setSelected(false);
        }
        
        if (tanda4.equals("ya")) {
            chkTanda4.setSelected(true);
        } else {
            chkTanda4.setSelected(false);
        }
        
        //tumit kekuping
        if (tumit_1.equals("ya")) {
            chkTumit_1.setSelected(true);
        } else {
            chkTumit_1.setSelected(false);
        }
        
        if (tumit0.equals("ya")) {
            chkTumit0.setSelected(true);
        } else {
            chkTumit0.setSelected(false);
        }
        
        if (tumit1.equals("ya")) {
            chkTumit1.setSelected(true);
        } else {
            chkTumit1.setSelected(false);
        }
        
        if (tumit2.equals("ya")) {
            chkTumit2.setSelected(true);
        } else {
            chkTumit2.setSelected(false);
        }
        
        if (tumit3.equals("ya")) {
            chkTumit3.setSelected(true);
        } else {
            chkTumit3.setSelected(false);
        }
        
        if (tumit4.equals("ya")) {
            chkTumit4.setSelected(true);
        } else {
            chkTumit4.setSelected(false);
        }
        
        //masalah keperawataan
        if (hipotermi.equals("ya")) {
            chkHipotermi.setSelected(true);
        } else {
            chkHipotermi.setSelected(false);
        }
        
        if (resikoHipotermi.equals("ya")) {
            chkResikoHipotermi.setSelected(true);
        } else {
            chkResikoHipotermi.setSelected(false);
        }
        
        if (hipertermi.equals("ya")) {
            chkHipertermi.setSelected(true);
        } else {
            chkHipertermi.setSelected(false);
        }
        
        if (pola.equals("ya")) {
            chkPolaNafas.setSelected(true);
        } else {
            chkPolaNafas.setSelected(false);
        }
        
        if (nyeri.equals("ya")) {
            chkNyeri.setSelected(true);
        } else {
            chkNyeri.setSelected(false);
        }
        
        if (kerusakan.equals("ya")) {
            chkKerusakan.setSelected(true);
        } else {
            chkKerusakan.setSelected(false);
        }
        
        if (resikoKerusakan.equals("ya")) {
            chkResikoKerusakan.setSelected(true);
        } else {
            chkResikoKerusakan.setSelected(false);
        }
        
        if (kebutuhan.equals("ya")) {
            chkKebutuhan.setSelected(true);
        } else {
            chkKebutuhan.setSelected(false);
        }
        
        if (ikterik.equals("ya")) {
            chkIkterik.setSelected(true);
        } else {
            chkIkterik.setSelected(false);
        }
        
        if (gangguan.equals("ya")) {
            chkGangguan.setSelected(true);
        } else {
            chkGangguan.setSelected(false);
        }
        
        if (bersihan.equals("ya")) {
            chkBersihan.setSelected(true);
        } else {
            chkBersihan.setSelected(false);
        }
        
        if (resikoBersihan.equals("ya")) {
            chkResikoBersihan.setSelected(true);
        } else {
            chkResikoBersihan.setSelected(false);
        }
        
        if (perubahan.equals("ya")) {
            chkPerubahan.setSelected(true);
        } else {
            chkPerubahan.setSelected(false);
        }
        
        if (kelebihan.equals("ya")) {
            chkKelebihan.setSelected(true);
        } else {
            chkKelebihan.setSelected(false);
        }
        
        if (resikoKelebihan.equals("ya")) {
            chkResikoKelebihan.setSelected(true);
        } else {
            chkResikoKelebihan.setSelected(false);
        }
        
        if (resikoKebutuhan.equals("ya")) {
            chkResikoKebutuhan.setSelected(true);
        } else {
            chkResikoKebutuhan.setSelected(false);
        }
    }
    
    private void variabelBersih() {
        nip = "";
        nipVerifikator = "";
        jernih = "";
        keruh = "";
        lumpur = "";
        hijau = "";
        berbau = "";
        kering = "";
        hipertensi = "";
        dm = "";
        pms = "";
        tbc = "";
        asma = "";
        hepB = "";
        lainRiwayatIbu = "";
        spontan = "";
        vakum = "";
        forcep = "";
        sectio = "";
        lainCara = "";
        segar = "";
        layu = "";
        simpul = "";
        ibuDemam = "";
        kpd24 = "";
        ketuban = "";
        chorio = "";
        fetal = "";
        kpd12 = "";
        asfiksia = "";
        bblr = "";
        isk = "";
        uk = "";
        gemeli = "";
        keputihan = "";
        suhuIbu = "";
        obat = "";
        makanan = "";
        lainRiwayatAlergi = "";
        suami = "";
        orangTua = "";
        keluarga = "";
        lainDukungan = "";
        sikap0 = "";
        sikap1 = "";
        sikap2 = "";
        sikap3 = "";
        sikap4 = "";
        persegi_1 = "";
        persegi0 = "";
        persegi1 = "";
        persegi2 = "";
        persegi3 = "";
        persegi4 = "";
        rekoli0 = "";
        rekoli1 = "";
        rekoli2 = "";
        rekoli3 = "";
        rekoli4 = "";
        sudut_1 = "";
        sudut0 = "";
        sudut1 = "";
        sudut2 = "";
        sudut3 = "";
        sudut4 = "";
        sudut5 = "";
        tanda_1 = "";
        tanda0 = "";
        tanda1 = "";
        tanda2 = "";
        tanda3 = "";
        tanda4 = "";
        tumit_1 = "";
        tumit0 = "";
        tumit1 = "";
        tumit2 = "";
        tumit3 = "";
        tumit4 = "";
        hipotermi = "";
        resikoHipotermi = "";
        hipertermi = "";
        pola = "";
        nyeri = "";
        kerusakan = "";
        resikoKerusakan = "";
        kebutuhan = "";
        ikterik = "";
        gangguan = "";
        bersihan = "";
        resikoBersihan = "";
        perubahan = "";
        kelebihan = "";
        resikoKelebihan = "";
        resikoKebutuhan = "";
        cekTglLahir = "";
        jamlahiribu = "";
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

    private void hitungBMI() {
        try {
            double A = 0, B = 0, C = 0, D = 0, Total = 0;
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
            C = B * B;
            D = A / C;
            
            if (Valid.SetAngka4(Total).equals("NaN") || Tbb.getText().equals("") || Ttb.getText().equals("")) {
                Tbmi.setText("0");
            } else {
                Tbmi.setText(Valid.SetAngka4(Total));
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka berat badan & tinggi badannya,    \n"
                    + "jika menggunakan koma, gantilah tanda koma dengan titik sebagai komanya !!");
            Tbmi.setText("");
        }
    }
}
