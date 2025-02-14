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
public final class RMAsesmenKeperawatanPerinatologi extends javax.swing.JDialog {
    private DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private String nip = "", jernih = "", keruh = "", lumpur = "", hijau = "", berbau = "", kering = "", hipertensi = "", dm = "", pms = "", tbc = "", asma = "",
            hepB = "", lainRiwayatIbu = "", spontan = "", vakum = "", forcep = "", sectio = "", lainCara = "", segar = "", layu = "", simpul = "", ibuDemam = "",
            kpd24 = "", ketuban = "", chorio = "", fetal = "", kpd12 = "", asfiksia = "", bblr = "", isk = "", uk = "", gemeli = "", keputihan = "", suhuIbu = "",
            obat = "", makanan = "", lainRiwayatAlergi = "", suami = "", orangTua = "", keluarga = "", lainDukungan = "", sikap0 = "", sikap1 = "", sikap2 = "",
            sikap3 = "", sikap4 = "", persegi_1 = "", persegi0 = "", persegi1 = "", persegi2 = "", persegi3 = "", persegi4 = "", rekoli0 = "", rekoli1 = "",
            rekoli2 = "", rekoli3 = "", rekoli4 = "", sudut_1 = "", sudut0 = "", sudut1 = "", sudut2 = "", sudut3 = "", sudut4 = "", sudut5 = "", tanda_1 = "", 
            tanda0 = "", tanda1 = "", tanda2 = "", tanda3 = "", tanda4 = "", tumit_1 = "", tumit0 = "", tumit1 = "", tumit2 = "", tumit3 = "", tumit4 = "", hipotermi = "",
            resikoHipotermi = "", hipertermi = "", pola = "", nyeri = "", kerusakan = "", resikoKerusakan = "", kebutuhan = "", ikterik = "", gangguan = "",
            bersihan = "", resikoBersihan = "", perubahan = "", kelebihan = "", resikoKelebihan = "", resikoKebutuhan = "", cekTglLahir = "", jamlahiribu = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsesmenKeperawatanPerinatologi(java.awt.Frame parent, boolean modal) {
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
            "resiko_kebutuhan", "masalah_keperawatan_lain", "tgl_rencana1", "jam_rencana1", "tgl_rencana2", "jam_rencana2", "nip_perawat", "waktu_simpan", "cek_tgllahir_ibu"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 191; i++) {
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
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());
        
        TlainSumber.setDocument(new batasInput((int) 140).getKata(TlainSumber));
        TdiagnosaRujukan.setDocument(new batasInput((int) 255).getKata(TdiagnosaRujukan));
        TnmIdentitas.setDocument(new batasInput((int) 40).getKata(TnmIdentitas));
        Tpekerjaan.setDocument(new batasInput((int) 100).getKata(Tpekerjaan));
        Tbbl.setDocument(new batasInput((int) 7).getKata(Tbbl));
        Tpb.setDocument(new batasInput((int) 7).getKata(Tpb));
        Tlk.setDocument(new batasInput((int) 7).getKata(Tlk));
        Tld.setDocument(new batasInput((int) 7).getKata(Tld));
        Tlp.setDocument(new batasInput((int) 7).getKata(Tlp));
        Tll.setDocument(new batasInput((int) 7).getKata(Tll));
        Tkk.setDocument(new batasInput((int) 7).getKata(Tkk));
        Tnadi.setDocument(new batasInput((int) 7).getKata(Tnadi));
        Trr.setDocument(new batasInput((int) 7).getKata(Trr));
        Tsuhu.setDocument(new batasInput((int) 7).getKata(Tsuhu));
        Tkelainan.setDocument(new batasInput((int) 140).getKata(Tkelainan));
        TanakKe.setDocument(new batasInput((byte) 7).getOnlyAngka(TanakKe));
        TumurHamil.setDocument(new batasInput((int) 7).getKata(TumurHamil));
        TlainRiwayat.setDocument(new batasInput((int) 140).getKata(TlainRiwayat));
        TkeadaanSaat.setDocument(new batasInput((int) 140).getKata(TkeadaanSaat));
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
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
        TrgRawat = new widget.TextBox();
        TtglRencana2 = new widget.Tanggal();
        jLabel20 = new widget.Label();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        jLabel95 = new widget.Label();
        TtglRencana1 = new widget.Tanggal();
        jLabel96 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel99 = new widget.Label();
        TnmPerawat = new widget.TextBox();
        BtnPerawat = new widget.Button();
        jLabel8 = new widget.Label();
        cmbSumber = new widget.ComboBox();
        TlainSumber = new widget.TextBox();
        jLabel9 = new widget.Label();
        cmbRujukan = new widget.ComboBox();
        jLabel10 = new widget.Label();
        cmbJnsRujukan = new widget.ComboBox();
        jLabel11 = new widget.Label();
        TdiagnosaRujukan = new widget.TextBox();
        jLabel12 = new widget.Label();
        Tkeluhan = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        TnmIdentitas = new widget.TextBox();
        jLabel15 = new widget.Label();
        jLabel16 = new widget.Label();
        Tpekerjaan = new widget.TextBox();
        jLabel17 = new widget.Label();
        cmbPendidikan = new widget.ComboBox();
        cmbAgama = new widget.ComboBox();
        jLabel22 = new widget.Label();
        Talamat = new widget.TextBox();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        Tbbl = new widget.TextBox();
        jLabel25 = new widget.Label();
        Tpb = new widget.TextBox();
        jLabel26 = new widget.Label();
        Tlk = new widget.TextBox();
        jLabel27 = new widget.Label();
        Tld = new widget.TextBox();
        jLabel28 = new widget.Label();
        Tlp = new widget.TextBox();
        jLabel29 = new widget.Label();
        jLabel30 = new widget.Label();
        jLabel31 = new widget.Label();
        Tll = new widget.TextBox();
        Tkk = new widget.TextBox();
        jLabel32 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel33 = new widget.Label();
        Trr = new widget.TextBox();
        jLabel34 = new widget.Label();
        Tsuhu = new widget.TextBox();
        jLabel35 = new widget.Label();
        jLabel36 = new widget.Label();
        chkJernih = new widget.CekBox();
        chkKeruh = new widget.CekBox();
        chkLumpur = new widget.CekBox();
        chkHijau = new widget.CekBox();
        chkBerbau = new widget.CekBox();
        chkKering = new widget.CekBox();
        jLabel37 = new widget.Label();
        cmbAnus = new widget.ComboBox();
        jLabel38 = new widget.Label();
        cmbBab = new widget.ComboBox();
        jLabel39 = new widget.Label();
        cmbBak = new widget.ComboBox();
        jLabel40 = new widget.Label();
        Tkelainan = new widget.TextBox();
        jLabel41 = new widget.Label();
        jLabel42 = new widget.Label();
        jLabel43 = new widget.Label();
        jLabel44 = new widget.Label();
        TanakKe = new widget.TextBox();
        jLabel45 = new widget.Label();
        TumurHamil = new widget.TextBox();
        jLabel46 = new widget.Label();
        jLabel47 = new widget.Label();
        cmbRiwPenyakitIbu = new widget.ComboBox();
        chkHipertensi = new widget.CekBox();
        chkDM = new widget.CekBox();
        chkPMS = new widget.CekBox();
        chkTBC = new widget.CekBox();
        chkAsma = new widget.CekBox();
        chkHepB = new widget.CekBox();
        chkLainyaRiwayat = new widget.CekBox();
        TlainRiwayat = new widget.TextBox();
        jLabel48 = new widget.Label();
        cmbMasih = new widget.ComboBox();
        jLabel49 = new widget.Label();
        Tobat = new widget.TextBox();
        jLabel50 = new widget.Label();
        jLabel51 = new widget.Label();
        TdiagnosaIbu = new widget.TextBox();
        TtglLahir = new widget.Tanggal();
        jLabel53 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel54 = new widget.Label();
        TkeadaanSaat = new widget.TextBox();
        jLabel55 = new widget.Label();
        Tas = new widget.TextBox();
        jLabel56 = new widget.Label();
        chkSpontan = new widget.CekBox();
        chkVakum = new widget.CekBox();
        chkForcep = new widget.CekBox();
        chkSectio = new widget.CekBox();
        chkLainyaCara = new widget.CekBox();
        TlainCara = new widget.TextBox();
        jLabel57 = new widget.Label();
        chkSegar = new widget.CekBox();
        chkLayu = new widget.CekBox();
        chkSimpul = new widget.CekBox();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        chkIbuDemam = new widget.CekBox();
        chkKpd24 = new widget.CekBox();
        chkKetuban = new widget.CekBox();
        chkChorio = new widget.CekBox();
        chkFetal = new widget.CekBox();
        jLabel60 = new widget.Label();
        chkKpd12 = new widget.CekBox();
        chkAsfiksia = new widget.CekBox();
        chkBblr = new widget.CekBox();
        chkIsk = new widget.CekBox();
        chkUK = new widget.CekBox();
        chkGemeli = new widget.CekBox();
        chkKeputihan = new widget.CekBox();
        chkSuhuIbu = new widget.CekBox();
        jLabel61 = new widget.Label();
        cmbRiwAlergi = new widget.ComboBox();
        chkObatAlergi = new widget.CekBox();
        TobatAlergi = new widget.TextBox();
        chkMakananAlergi = new widget.CekBox();
        TmakananAlergi = new widget.TextBox();
        chkLainAlergi = new widget.CekBox();
        TlainyaAlergi = new widget.TextBox();
        jLabel62 = new widget.Label();
        Treaksi = new widget.TextBox();
        jLabel63 = new widget.Label();
        jLabel64 = new widget.Label();
        cmbMasalah = new widget.ComboBox();
        cmbAdaPerkawinan = new widget.ComboBox();
        TlainPerkawinan = new widget.TextBox();
        jLabel65 = new widget.Label();
        cmbMengalami = new widget.ComboBox();
        cmbAdaMengalami = new widget.ComboBox();
        cmbDialami = new widget.ComboBox();
        jLabel66 = new widget.Label();
        cmbTrauma = new widget.ComboBox();
        TjelaskanTrauma = new widget.TextBox();
        jLabel67 = new widget.Label();
        jLabel68 = new widget.Label();
        cmbGangguan = new widget.ComboBox();
        jLabel69 = new widget.Label();
        cmbKonsultasi = new widget.ComboBox();
        jLabel70 = new widget.Label();
        cmbPenerimaan = new widget.ComboBox();
        jLabel71 = new widget.Label();
        chkSuami = new widget.CekBox();
        chkOrangTua = new widget.CekBox();
        chkKeluarga = new widget.CekBox();
        chkLainDukungan = new widget.CekBox();
        TlainDukungan = new widget.TextBox();
        jLabel72 = new widget.Label();
        jLabel73 = new widget.Label();
        cmbStatusNikah = new widget.ComboBox();
        TkaliMenikah = new widget.TextBox();
        jLabel74 = new widget.Label();
        jLabel75 = new widget.Label();
        cmbHubungan = new widget.ComboBox();
        jLabel76 = new widget.Label();
        cmbTinggal = new widget.ComboBox();
        TlainTinggal = new widget.TextBox();
        jLabel77 = new widget.Label();
        cmbTempat = new widget.ComboBox();
        TlainTempat = new widget.TextBox();
        jLabel78 = new widget.Label();
        jLabel79 = new widget.Label();
        TnmKerabat = new widget.TextBox();
        jLabel80 = new widget.Label();
        ThubKerabat = new widget.TextBox();
        jLabel81 = new widget.Label();
        TtelpKerabat = new widget.TextBox();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        TkegiatanAgama = new widget.TextBox();
        jLabel84 = new widget.Label();
        TkegiatanSpiritual = new widget.TextBox();
        jLabel85 = new widget.Label();
        jLabel86 = new widget.Label();
        cmbNyeri = new widget.ComboBox();
        jLabel87 = new widget.Label();
        jLabel88 = new widget.Label();
        cmbCrying = new widget.ComboBox();
        TnilaiCrying = new widget.TextBox();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        cmbRequires = new widget.ComboBox();
        jLabel91 = new widget.Label();
        TnilaiRequires = new widget.TextBox();
        jLabel92 = new widget.Label();
        cmbIncreased = new widget.ComboBox();
        jLabel93 = new widget.Label();
        TnilaiIncreased = new widget.TextBox();
        jLabel94 = new widget.Label();
        cmbExpresion = new widget.ComboBox();
        jLabel97 = new widget.Label();
        TnilaiExpresion = new widget.TextBox();
        jLabel98 = new widget.Label();
        cmbSleepless = new widget.ComboBox();
        jLabel100 = new widget.Label();
        TnilaiSleepless = new widget.TextBox();
        jLabel101 = new widget.Label();
        TtotNilaiNyeri = new widget.TextBox();
        jLabel102 = new widget.Label();
        TkesimpulanNyeri = new widget.TextBox();
        jLabel103 = new widget.Label();
        jLabel104 = new widget.Label();
        PanelWall = new usu.widget.glass.PanelGlass();
        chkSikap0 = new widget.CekBox();
        jLabel105 = new widget.Label();
        chkSikap1 = new widget.CekBox();
        PanelWall1 = new usu.widget.glass.PanelGlass();
        chkSikap2 = new widget.CekBox();
        PanelWall2 = new usu.widget.glass.PanelGlass();
        chkSikap3 = new widget.CekBox();
        PanelWall3 = new usu.widget.glass.PanelGlass();
        chkSikap4 = new widget.CekBox();
        PanelWall4 = new usu.widget.glass.PanelGlass();
        TnilaiSikap = new widget.TextBox();
        jLabel107 = new widget.Label();
        jLabel108 = new widget.Label();
        jLabel109 = new widget.Label();
        jLabel110 = new widget.Label();
        jLabel111 = new widget.Label();
        jLabel112 = new widget.Label();
        jLabel113 = new widget.Label();
        PanelWall5 = new usu.widget.glass.PanelGlass();
        PanelWall6 = new usu.widget.glass.PanelGlass();
        PanelWall7 = new usu.widget.glass.PanelGlass();
        PanelWall8 = new usu.widget.glass.PanelGlass();
        PanelWall9 = new usu.widget.glass.PanelGlass();
        PanelWall10 = new usu.widget.glass.PanelGlass();
        jLabel114 = new widget.Label();
        jLabel115 = new widget.Label();
        jLabel116 = new widget.Label();
        chkPersegi0 = new widget.CekBox();
        chkPersegi1 = new widget.CekBox();
        chkPersegi2 = new widget.CekBox();
        chkPersegi3 = new widget.CekBox();
        chkPersegi4 = new widget.CekBox();
        chkPersegi_1 = new widget.CekBox();
        TnilaiPersegi = new widget.TextBox();
        PanelWall12 = new usu.widget.glass.PanelGlass();
        PanelWall13 = new usu.widget.glass.PanelGlass();
        PanelWall14 = new usu.widget.glass.PanelGlass();
        PanelWall15 = new usu.widget.glass.PanelGlass();
        PanelWall16 = new usu.widget.glass.PanelGlass();
        PanelWall18 = new usu.widget.glass.PanelGlass();
        PanelWall19 = new usu.widget.glass.PanelGlass();
        PanelWall20 = new usu.widget.glass.PanelGlass();
        PanelWall21 = new usu.widget.glass.PanelGlass();
        PanelWall22 = new usu.widget.glass.PanelGlass();
        PanelWall23 = new usu.widget.glass.PanelGlass();
        PanelWall24 = new usu.widget.glass.PanelGlass();
        chkRekoli0 = new widget.CekBox();
        chkRekoli1 = new widget.CekBox();
        chkRekoli2 = new widget.CekBox();
        chkRekoli3 = new widget.CekBox();
        chkRekoli4 = new widget.CekBox();
        jLabel118 = new widget.Label();
        TnilaiRekoli = new widget.TextBox();
        chkSudut0 = new widget.CekBox();
        chkSudut1 = new widget.CekBox();
        chkSudut2 = new widget.CekBox();
        chkSudut3 = new widget.CekBox();
        chkSudut4 = new widget.CekBox();
        chkSudut5 = new widget.CekBox();
        chkSudut_1 = new widget.CekBox();
        TnilaiSudut = new widget.TextBox();
        jLabel121 = new widget.Label();
        PanelWall25 = new usu.widget.glass.PanelGlass();
        PanelWall26 = new usu.widget.glass.PanelGlass();
        PanelWall27 = new usu.widget.glass.PanelGlass();
        PanelWall28 = new usu.widget.glass.PanelGlass();
        PanelWall29 = new usu.widget.glass.PanelGlass();
        PanelWall30 = new usu.widget.glass.PanelGlass();
        chkTanda_1 = new widget.CekBox();
        chkTanda0 = new widget.CekBox();
        chkTanda1 = new widget.CekBox();
        chkTanda2 = new widget.CekBox();
        chkTanda3 = new widget.CekBox();
        chkTanda4 = new widget.CekBox();
        TnilaiTanda = new widget.TextBox();
        jLabel123 = new widget.Label();
        PanelWall31 = new usu.widget.glass.PanelGlass();
        PanelWall32 = new usu.widget.glass.PanelGlass();
        PanelWall33 = new usu.widget.glass.PanelGlass();
        PanelWall34 = new usu.widget.glass.PanelGlass();
        PanelWall35 = new usu.widget.glass.PanelGlass();
        PanelWall36 = new usu.widget.glass.PanelGlass();
        chkTumit_1 = new widget.CekBox();
        chkTumit0 = new widget.CekBox();
        chkTumit1 = new widget.CekBox();
        chkTumit2 = new widget.CekBox();
        chkTumit3 = new widget.CekBox();
        chkTumit4 = new widget.CekBox();
        TnilaiTumit = new widget.TextBox();
        jLabel125 = new widget.Label();
        jLabel126 = new widget.Label();
        TtotNilaiNeomuskular = new widget.TextBox();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel127 = new widget.Label();
        jLabel106 = new widget.Label();
        jLabel117 = new widget.Label();
        cmbFisikKulit = new widget.ComboBox();
        jLabel119 = new widget.Label();
        cmbFisikPayudara = new widget.ComboBox();
        jLabel120 = new widget.Label();
        cmbFisikMata = new widget.ComboBox();
        jLabel122 = new widget.Label();
        cmbFisikGenPria = new widget.ComboBox();
        jLabel124 = new widget.Label();
        cmbFisikGenWanita = new widget.ComboBox();
        jLabel128 = new widget.Label();
        cmbFisikLanugo = new widget.ComboBox();
        jLabel129 = new widget.Label();
        cmbFisikPlantar = new widget.ComboBox();
        jLabel130 = new widget.Label();
        TnilaiKulit = new widget.TextBox();
        jLabel131 = new widget.Label();
        TnilaiPayudara = new widget.TextBox();
        jLabel132 = new widget.Label();
        TnilaiMata = new widget.TextBox();
        jLabel133 = new widget.Label();
        TnilaiGenPria = new widget.TextBox();
        TnilaiGenWanita = new widget.TextBox();
        jLabel134 = new widget.Label();
        jLabel135 = new widget.Label();
        TnilaiLanugo = new widget.TextBox();
        jLabel136 = new widget.Label();
        TnilaiPlantar = new widget.TextBox();
        jLabel137 = new widget.Label();
        TtotNilaiFisik = new widget.TextBox();
        jLabel138 = new widget.Label();
        jLabel139 = new widget.Label();
        TnilaiNeo = new widget.TextBox();
        jLabel140 = new widget.Label();
        TnilaiFisik = new widget.TextBox();
        jLabel141 = new widget.Label();
        TnilaiSkor = new widget.TextBox();
        jLabel142 = new widget.Label();
        TkesimpulanSkor = new widget.TextBox();
        jLabel143 = new widget.Label();
        chkHipotermi = new widget.CekBox();
        chkResikoHipotermi = new widget.CekBox();
        chkHipertermi = new widget.CekBox();
        chkPolaNafas = new widget.CekBox();
        chkNyeri = new widget.CekBox();
        chkKerusakan = new widget.CekBox();
        chkResikoKerusakan = new widget.CekBox();
        chkKebutuhan = new widget.CekBox();
        chkIkterik = new widget.CekBox();
        chkGangguan = new widget.CekBox();
        chkBersihan = new widget.CekBox();
        chkResikoBersihan = new widget.CekBox();
        chkPerubahan = new widget.CekBox();
        chkKelebihan = new widget.CekBox();
        chkResikoKelebihan = new widget.CekBox();
        chkResikoKebutuhan = new widget.CekBox();
        jLabel144 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        TmasalahLain = new widget.TextArea();
        jLabel145 = new widget.Label();
        cmbJenkel = new widget.ComboBox();
        chkTgllahirIbu = new widget.CekBox();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Keperawatan Perinatologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        FormInput.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis..!!");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2787));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 122, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(315, 10, 407, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(240, 10, 70, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tanggal : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(568, 2727, 60, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Ruang Rawat : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setBackground(new java.awt.Color(245, 250, 240));
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(114, 38, 608, 23);

        TtglRencana2.setEditable(false);
        TtglRencana2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-02-2025" }));
        TtglRencana2.setDisplayFormat("dd-MM-yyyy");
        TtglRencana2.setName("TtglRencana2"); // NOI18N
        TtglRencana2.setOpaque(false);
        TtglRencana2.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglRencana2);
        TtglRencana2.setBounds(630, 2727, 90, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jam : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(722, 2727, 40, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(765, 2727, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(816, 2727, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(868, 2727, 45, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Tanggal : ");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(0, 2727, 125, 23);

        TtglRencana1.setEditable(false);
        TtglRencana1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-02-2025" }));
        TtglRencana1.setDisplayFormat("dd-MM-yyyy");
        TtglRencana1.setName("TtglRencana1"); // NOI18N
        TtglRencana1.setOpaque(false);
        TtglRencana1.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglRencana1);
        TtglRencana1.setBounds(128, 2727, 90, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Jam : ");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(220, 2727, 40, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(263, 2727, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(315, 2727, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(368, 2727, 45, 23);

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

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Sumber Data : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 66, 110, 23);

        cmbSumber.setForeground(new java.awt.Color(0, 0, 0));
        cmbSumber.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Pasien", "Keluarga", "Lainnya" }));
        cmbSumber.setName("cmbSumber"); // NOI18N
        cmbSumber.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbSumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSumberActionPerformed(evt);
            }
        });
        FormInput.add(cmbSumber);
        cmbSumber.setBounds(114, 66, 75, 23);

        TlainSumber.setBackground(new java.awt.Color(245, 250, 240));
        TlainSumber.setForeground(new java.awt.Color(0, 0, 0));
        TlainSumber.setName("TlainSumber"); // NOI18N
        TlainSumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainSumberKeyPressed(evt);
            }
        });
        FormInput.add(TlainSumber);
        TlainSumber.setBounds(195, 66, 527, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jenis Rujukan : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(200, 94, 90, 23);

        cmbRujukan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbRujukan.setName("cmbRujukan"); // NOI18N
        cmbRujukan.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRujukanActionPerformed(evt);
            }
        });
        FormInput.add(cmbRujukan);
        cmbRujukan.setBounds(114, 94, 80, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Rujukan : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 94, 110, 23);

        cmbJnsRujukan.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "RS", "Puskesmas", "Dokter" }));
        cmbJnsRujukan.setName("cmbJnsRujukan"); // NOI18N
        cmbJnsRujukan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbJnsRujukan);
        cmbJnsRujukan.setBounds(293, 94, 86, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Diagnosa Rujukan : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 122, 110, 23);

        TdiagnosaRujukan.setBackground(new java.awt.Color(245, 250, 240));
        TdiagnosaRujukan.setForeground(new java.awt.Color(0, 0, 0));
        TdiagnosaRujukan.setName("TdiagnosaRujukan"); // NOI18N
        TdiagnosaRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaRujukanKeyPressed(evt);
            }
        });
        FormInput.add(TdiagnosaRujukan);
        TdiagnosaRujukan.setBounds(114, 122, 610, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("1. KELUHAN UTAMA :");
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 150, 160, 23);

        Tkeluhan.setBackground(new java.awt.Color(245, 250, 240));
        Tkeluhan.setForeground(new java.awt.Color(0, 0, 0));
        Tkeluhan.setName("Tkeluhan"); // NOI18N
        Tkeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeluhanKeyPressed(evt);
            }
        });
        FormInput.add(Tkeluhan);
        Tkeluhan.setBounds(164, 150, 560, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("2. IDENTITAS (Orang Tua/Keluarga)");
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 178, 246, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Nama : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 206, 110, 23);

        TnmIdentitas.setBackground(new java.awt.Color(245, 250, 240));
        TnmIdentitas.setForeground(new java.awt.Color(0, 0, 0));
        TnmIdentitas.setName("TnmIdentitas"); // NOI18N
        TnmIdentitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmIdentitasKeyPressed(evt);
            }
        });
        FormInput.add(TnmIdentitas);
        TnmIdentitas.setBounds(114, 206, 610, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Pendidikan : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 234, 110, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Pekerjaan :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(220, 234, 70, 23);

        Tpekerjaan.setBackground(new java.awt.Color(245, 250, 240));
        Tpekerjaan.setForeground(new java.awt.Color(0, 0, 0));
        Tpekerjaan.setName("Tpekerjaan"); // NOI18N
        Tpekerjaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpekerjaanKeyPressed(evt);
            }
        });
        FormInput.add(Tpekerjaan);
        Tpekerjaan.setBounds(294, 234, 430, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Agama : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 262, 110, 23);

        cmbPendidikan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPendidikan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak Sekolah", "TK", "SD", "SMP", "SMA", "D1", "D2", "D3", "D4", "S1", "S2", "S3" }));
        cmbPendidikan.setName("cmbPendidikan"); // NOI18N
        FormInput.add(cmbPendidikan);
        cmbPendidikan.setBounds(114, 234, 100, 23);

        cmbAgama.setBackground(new java.awt.Color(245, 253, 240));
        cmbAgama.setForeground(new java.awt.Color(0, 0, 0));
        cmbAgama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "ISLAM", "KRISTEN", "KATOLIK", "HINDU", "BUDHA", "KONG HU CHU" }));
        cmbAgama.setLightWeightPopupEnabled(false);
        cmbAgama.setName("cmbAgama"); // NOI18N
        FormInput.add(cmbAgama);
        cmbAgama.setBounds(114, 262, 130, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Alamat : ");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(245, 262, 60, 23);

        Talamat.setBackground(new java.awt.Color(245, 250, 240));
        Talamat.setForeground(new java.awt.Color(0, 0, 0));
        Talamat.setName("Talamat"); // NOI18N
        Talamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalamatKeyPressed(evt);
            }
        });
        FormInput.add(Talamat);
        Talamat.setBounds(308, 262, 416, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("3. PEMERIKSAAN FISIK");
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 290, 170, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("BBL : ");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 318, 110, 23);

        Tbbl.setBackground(new java.awt.Color(245, 250, 240));
        Tbbl.setForeground(new java.awt.Color(0, 0, 0));
        Tbbl.setName("Tbbl"); // NOI18N
        Tbbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbblKeyPressed(evt);
            }
        });
        FormInput.add(Tbbl);
        Tbbl.setBounds(114, 318, 50, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("gram     PB :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(170, 318, 60, 23);

        Tpb.setBackground(new java.awt.Color(245, 250, 240));
        Tpb.setForeground(new java.awt.Color(0, 0, 0));
        Tpb.setName("Tpb"); // NOI18N
        Tpb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpbKeyPressed(evt);
            }
        });
        FormInput.add(Tpb);
        Tpb.setBounds(234, 318, 60, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("cm      LK : ");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(300, 318, 53, 23);

        Tlk.setBackground(new java.awt.Color(245, 250, 240));
        Tlk.setForeground(new java.awt.Color(0, 0, 0));
        Tlk.setName("Tlk"); // NOI18N
        Tlk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlkKeyPressed(evt);
            }
        });
        FormInput.add(Tlk);
        Tlk.setBounds(355, 318, 60, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("cm      LD : ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(420, 318, 53, 23);

        Tld.setBackground(new java.awt.Color(245, 250, 240));
        Tld.setForeground(new java.awt.Color(0, 0, 0));
        Tld.setName("Tld"); // NOI18N
        Tld.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TldKeyPressed(evt);
            }
        });
        FormInput.add(Tld);
        Tld.setBounds(475, 318, 60, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("cm      LP : ");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(540, 318, 53, 23);

        Tlp.setBackground(new java.awt.Color(245, 250, 240));
        Tlp.setForeground(new java.awt.Color(0, 0, 0));
        Tlp.setName("Tlp"); // NOI18N
        Tlp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlpKeyPressed(evt);
            }
        });
        FormInput.add(Tlp);
        Tlp.setBounds(595, 318, 60, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("cm");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(660, 318, 20, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("LL : ");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(0, 346, 110, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("cm     KK :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(170, 346, 50, 23);

        Tll.setBackground(new java.awt.Color(245, 250, 240));
        Tll.setForeground(new java.awt.Color(0, 0, 0));
        Tll.setName("Tll"); // NOI18N
        Tll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TllKeyPressed(evt);
            }
        });
        FormInput.add(Tll);
        Tll.setBounds(114, 346, 50, 23);

        Tkk.setBackground(new java.awt.Color(245, 250, 240));
        Tkk.setForeground(new java.awt.Color(0, 0, 0));
        Tkk.setName("Tkk"); // NOI18N
        Tkk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkkKeyPressed(evt);
            }
        });
        FormInput.add(Tkk);
        Tkk.setBounds(220, 346, 60, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("cm      Nadi : ");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(285, 346, 63, 23);

        Tnadi.setBackground(new java.awt.Color(245, 250, 240));
        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(350, 346, 60, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("x/menit     RR : ");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(415, 346, 77, 23);

        Trr.setBackground(new java.awt.Color(245, 250, 240));
        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        FormInput.add(Trr);
        Trr.setBounds(490, 346, 60, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("x/menit    Suhu : ");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(555, 346, 83, 23);

        Tsuhu.setBackground(new java.awt.Color(245, 250, 240));
        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        FormInput.add(Tsuhu);
        Tsuhu.setBounds(637, 346, 60, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("°C");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(702, 346, 20, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Amnion : ");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(0, 374, 110, 23);

        chkJernih.setBackground(new java.awt.Color(255, 255, 250));
        chkJernih.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJernih.setForeground(new java.awt.Color(0, 0, 0));
        chkJernih.setText("Jernih");
        chkJernih.setBorderPainted(true);
        chkJernih.setBorderPaintedFlat(true);
        chkJernih.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJernih.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJernih.setName("chkJernih"); // NOI18N
        chkJernih.setOpaque(false);
        chkJernih.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkJernih);
        chkJernih.setBounds(114, 374, 60, 23);

        chkKeruh.setBackground(new java.awt.Color(255, 255, 250));
        chkKeruh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKeruh.setForeground(new java.awt.Color(0, 0, 0));
        chkKeruh.setText("Keruh");
        chkKeruh.setBorderPainted(true);
        chkKeruh.setBorderPaintedFlat(true);
        chkKeruh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKeruh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKeruh.setName("chkKeruh"); // NOI18N
        chkKeruh.setOpaque(false);
        chkKeruh.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKeruh);
        chkKeruh.setBounds(180, 374, 60, 23);

        chkLumpur.setBackground(new java.awt.Color(255, 255, 250));
        chkLumpur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLumpur.setForeground(new java.awt.Color(0, 0, 0));
        chkLumpur.setText("Lumpur");
        chkLumpur.setBorderPainted(true);
        chkLumpur.setBorderPaintedFlat(true);
        chkLumpur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLumpur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLumpur.setName("chkLumpur"); // NOI18N
        chkLumpur.setOpaque(false);
        chkLumpur.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLumpur);
        chkLumpur.setBounds(250, 374, 60, 23);

        chkHijau.setBackground(new java.awt.Color(255, 255, 250));
        chkHijau.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHijau.setForeground(new java.awt.Color(0, 0, 0));
        chkHijau.setText("Hijau");
        chkHijau.setBorderPainted(true);
        chkHijau.setBorderPaintedFlat(true);
        chkHijau.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHijau.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHijau.setName("chkHijau"); // NOI18N
        chkHijau.setOpaque(false);
        chkHijau.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHijau);
        chkHijau.setBounds(320, 374, 50, 23);

        chkBerbau.setBackground(new java.awt.Color(255, 255, 250));
        chkBerbau.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBerbau.setForeground(new java.awt.Color(0, 0, 0));
        chkBerbau.setText("Berbau");
        chkBerbau.setBorderPainted(true);
        chkBerbau.setBorderPaintedFlat(true);
        chkBerbau.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBerbau.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBerbau.setName("chkBerbau"); // NOI18N
        chkBerbau.setOpaque(false);
        chkBerbau.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBerbau);
        chkBerbau.setBounds(380, 374, 60, 23);

        chkKering.setBackground(new java.awt.Color(255, 255, 250));
        chkKering.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKering.setForeground(new java.awt.Color(0, 0, 0));
        chkKering.setText("Kering");
        chkKering.setBorderPainted(true);
        chkKering.setBorderPaintedFlat(true);
        chkKering.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKering.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKering.setName("chkKering"); // NOI18N
        chkKering.setOpaque(false);
        chkKering.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKering);
        chkKering.setBounds(450, 374, 60, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Anus : ");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(0, 402, 110, 23);

        cmbAnus.setForeground(new java.awt.Color(0, 0, 0));
        cmbAnus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbAnus.setName("cmbAnus"); // NOI18N
        cmbAnus.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbAnus);
        cmbAnus.setBounds(114, 402, 80, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("BAB : ");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(200, 402, 40, 23);

        cmbBab.setForeground(new java.awt.Color(0, 0, 0));
        cmbBab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbBab.setName("cmbBab"); // NOI18N
        cmbBab.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBab);
        cmbBab.setBounds(243, 402, 80, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("BAK : ");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(330, 402, 40, 23);

        cmbBak.setForeground(new java.awt.Color(0, 0, 0));
        cmbBak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbBak.setName("cmbBak"); // NOI18N
        cmbBak.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBak);
        cmbBak.setBounds(373, 402, 80, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Kelainan Bawaan : ");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 430, 110, 23);

        Tkelainan.setBackground(new java.awt.Color(245, 250, 240));
        Tkelainan.setForeground(new java.awt.Color(0, 0, 0));
        Tkelainan.setName("Tkelainan"); // NOI18N
        Tkelainan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkelainanKeyPressed(evt);
            }
        });
        FormInput.add(Tkelainan);
        Tkelainan.setBounds(114, 430, 420, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Jenis Kelamin : ");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(541, 430, 90, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("4. RIWAYAT KESEHATAN");
        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(0, 458, 170, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("a. Riwayat Prenatal");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(0, 486, 140, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Anak Ke : ");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(0, 514, 110, 23);

        TanakKe.setBackground(new java.awt.Color(245, 250, 240));
        TanakKe.setForeground(new java.awt.Color(0, 0, 0));
        TanakKe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TanakKe.setName("TanakKe"); // NOI18N
        TanakKe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanakKeKeyPressed(evt);
            }
        });
        FormInput.add(TanakKe);
        TanakKe.setBounds(114, 514, 50, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Umur Kehamilan : ");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(165, 514, 110, 23);

        TumurHamil.setBackground(new java.awt.Color(245, 250, 240));
        TumurHamil.setForeground(new java.awt.Color(0, 0, 0));
        TumurHamil.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TumurHamil.setName("TumurHamil"); // NOI18N
        TumurHamil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurHamilKeyPressed(evt);
            }
        });
        FormInput.add(TumurHamil);
        TumurHamil.setBounds(275, 514, 60, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("Minggu");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(340, 514, 40, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Riwayat Penyakit Ibu : ");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(0, 542, 170, 23);

        cmbRiwPenyakitIbu.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwPenyakitIbu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbRiwPenyakitIbu.setName("cmbRiwPenyakitIbu"); // NOI18N
        cmbRiwPenyakitIbu.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRiwPenyakitIbu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwPenyakitIbuActionPerformed(evt);
            }
        });
        FormInput.add(cmbRiwPenyakitIbu);
        cmbRiwPenyakitIbu.setBounds(172, 542, 60, 23);

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
        chkHipertensi.setBounds(240, 542, 80, 23);

        chkDM.setBackground(new java.awt.Color(255, 255, 250));
        chkDM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDM.setForeground(new java.awt.Color(0, 0, 0));
        chkDM.setText("DM");
        chkDM.setBorderPainted(true);
        chkDM.setBorderPaintedFlat(true);
        chkDM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDM.setName("chkDM"); // NOI18N
        chkDM.setOpaque(false);
        chkDM.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDM);
        chkDM.setBounds(330, 542, 40, 23);

        chkPMS.setBackground(new java.awt.Color(255, 255, 250));
        chkPMS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPMS.setForeground(new java.awt.Color(0, 0, 0));
        chkPMS.setText("PMS");
        chkPMS.setBorderPainted(true);
        chkPMS.setBorderPaintedFlat(true);
        chkPMS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPMS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPMS.setName("chkPMS"); // NOI18N
        chkPMS.setOpaque(false);
        chkPMS.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPMS);
        chkPMS.setBounds(380, 542, 45, 23);

        chkTBC.setBackground(new java.awt.Color(255, 255, 250));
        chkTBC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTBC.setForeground(new java.awt.Color(0, 0, 0));
        chkTBC.setText("TBC");
        chkTBC.setBorderPainted(true);
        chkTBC.setBorderPaintedFlat(true);
        chkTBC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTBC.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTBC.setName("chkTBC"); // NOI18N
        chkTBC.setOpaque(false);
        chkTBC.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTBC);
        chkTBC.setBounds(434, 542, 50, 23);

        chkAsma.setBackground(new java.awt.Color(255, 255, 250));
        chkAsma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsma.setForeground(new java.awt.Color(0, 0, 0));
        chkAsma.setText("Asma");
        chkAsma.setBorderPainted(true);
        chkAsma.setBorderPaintedFlat(true);
        chkAsma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsma.setName("chkAsma"); // NOI18N
        chkAsma.setOpaque(false);
        chkAsma.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAsma);
        chkAsma.setBounds(490, 542, 50, 23);

        chkHepB.setBackground(new java.awt.Color(255, 255, 250));
        chkHepB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHepB.setForeground(new java.awt.Color(0, 0, 0));
        chkHepB.setText("Hep B");
        chkHepB.setBorderPainted(true);
        chkHepB.setBorderPaintedFlat(true);
        chkHepB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHepB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHepB.setName("chkHepB"); // NOI18N
        chkHepB.setOpaque(false);
        chkHepB.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHepB);
        chkHepB.setBounds(550, 542, 56, 23);

        chkLainyaRiwayat.setBackground(new java.awt.Color(255, 255, 250));
        chkLainyaRiwayat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainyaRiwayat.setForeground(new java.awt.Color(0, 0, 0));
        chkLainyaRiwayat.setText("Lainnya");
        chkLainyaRiwayat.setBorderPainted(true);
        chkLainyaRiwayat.setBorderPaintedFlat(true);
        chkLainyaRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainyaRiwayat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainyaRiwayat.setName("chkLainyaRiwayat"); // NOI18N
        chkLainyaRiwayat.setOpaque(false);
        chkLainyaRiwayat.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainyaRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainyaRiwayatActionPerformed(evt);
            }
        });
        FormInput.add(chkLainyaRiwayat);
        chkLainyaRiwayat.setBounds(240, 570, 63, 23);

        TlainRiwayat.setBackground(new java.awt.Color(245, 250, 240));
        TlainRiwayat.setForeground(new java.awt.Color(0, 0, 0));
        TlainRiwayat.setName("TlainRiwayat"); // NOI18N
        TlainRiwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainRiwayatKeyPressed(evt);
            }
        });
        FormInput.add(TlainRiwayat);
        TlainRiwayat.setBounds(304, 570, 420, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Masih Pengobatan : ");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(0, 598, 170, 23);

        cmbMasih.setForeground(new java.awt.Color(0, 0, 0));
        cmbMasih.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbMasih.setName("cmbMasih"); // NOI18N
        cmbMasih.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbMasih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMasihActionPerformed(evt);
            }
        });
        FormInput.add(cmbMasih);
        cmbMasih.setBounds(172, 598, 60, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Obat : ");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(235, 598, 40, 23);

        Tobat.setBackground(new java.awt.Color(245, 250, 240));
        Tobat.setForeground(new java.awt.Color(0, 0, 0));
        Tobat.setName("Tobat"); // NOI18N
        Tobat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatKeyPressed(evt);
            }
        });
        FormInput.add(Tobat);
        Tobat.setBounds(279, 598, 445, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("b. Riwayat Intranatal");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(0, 626, 140, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Diagnosa Ibu : ");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(0, 654, 110, 23);

        TdiagnosaIbu.setBackground(new java.awt.Color(245, 250, 240));
        TdiagnosaIbu.setForeground(new java.awt.Color(0, 0, 0));
        TdiagnosaIbu.setName("TdiagnosaIbu"); // NOI18N
        TdiagnosaIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaIbuKeyPressed(evt);
            }
        });
        FormInput.add(TdiagnosaIbu);
        TdiagnosaIbu.setBounds(114, 654, 610, 23);

        TtglLahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-02-2025" }));
        TtglLahir.setDisplayFormat("dd-MM-yyyy");
        TtglLahir.setName("TtglLahir"); // NOI18N
        TtglLahir.setOpaque(false);
        TtglLahir.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglLahir);
        TtglLahir.setBounds(114, 682, 90, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Jam :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(205, 682, 40, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(250, 682, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(302, 682, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(355, 682, 45, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Keadaan Saat Lahir :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(400, 682, 120, 23);

        TkeadaanSaat.setBackground(new java.awt.Color(245, 250, 240));
        TkeadaanSaat.setForeground(new java.awt.Color(0, 0, 0));
        TkeadaanSaat.setName("TkeadaanSaat"); // NOI18N
        TkeadaanSaat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeadaanSaatKeyPressed(evt);
            }
        });
        FormInput.add(TkeadaanSaat);
        TkeadaanSaat.setBounds(524, 682, 200, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("AS :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(480, 710, 40, 23);

        Tas.setBackground(new java.awt.Color(245, 250, 240));
        Tas.setForeground(new java.awt.Color(0, 0, 0));
        Tas.setName("Tas"); // NOI18N
        Tas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TasKeyPressed(evt);
            }
        });
        FormInput.add(Tas);
        Tas.setBounds(524, 710, 200, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Cara Persalinan : ");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(0, 738, 110, 23);

        chkSpontan.setBackground(new java.awt.Color(255, 255, 250));
        chkSpontan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSpontan.setForeground(new java.awt.Color(0, 0, 0));
        chkSpontan.setText("Spontan");
        chkSpontan.setBorderPainted(true);
        chkSpontan.setBorderPaintedFlat(true);
        chkSpontan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSpontan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSpontan.setName("chkSpontan"); // NOI18N
        chkSpontan.setOpaque(false);
        chkSpontan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSpontan);
        chkSpontan.setBounds(114, 738, 70, 23);

        chkVakum.setBackground(new java.awt.Color(255, 255, 250));
        chkVakum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkVakum.setForeground(new java.awt.Color(0, 0, 0));
        chkVakum.setText("Vakum");
        chkVakum.setBorderPainted(true);
        chkVakum.setBorderPaintedFlat(true);
        chkVakum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkVakum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkVakum.setName("chkVakum"); // NOI18N
        chkVakum.setOpaque(false);
        chkVakum.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkVakum);
        chkVakum.setBounds(190, 738, 60, 23);

        chkForcep.setBackground(new java.awt.Color(255, 255, 250));
        chkForcep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkForcep.setForeground(new java.awt.Color(0, 0, 0));
        chkForcep.setText("Forcep Ekstraksi");
        chkForcep.setBorderPainted(true);
        chkForcep.setBorderPaintedFlat(true);
        chkForcep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkForcep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkForcep.setName("chkForcep"); // NOI18N
        chkForcep.setOpaque(false);
        chkForcep.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkForcep);
        chkForcep.setBounds(255, 738, 104, 23);

        chkSectio.setBackground(new java.awt.Color(255, 255, 250));
        chkSectio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSectio.setForeground(new java.awt.Color(0, 0, 0));
        chkSectio.setText("Sectio Caesarea");
        chkSectio.setBorderPainted(true);
        chkSectio.setBorderPaintedFlat(true);
        chkSectio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSectio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSectio.setName("chkSectio"); // NOI18N
        chkSectio.setOpaque(false);
        chkSectio.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSectio);
        chkSectio.setBounds(369, 738, 104, 23);

        chkLainyaCara.setBackground(new java.awt.Color(255, 255, 250));
        chkLainyaCara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainyaCara.setForeground(new java.awt.Color(0, 0, 0));
        chkLainyaCara.setText("Lainnya");
        chkLainyaCara.setBorderPainted(true);
        chkLainyaCara.setBorderPaintedFlat(true);
        chkLainyaCara.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainyaCara.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainyaCara.setName("chkLainyaCara"); // NOI18N
        chkLainyaCara.setOpaque(false);
        chkLainyaCara.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainyaCara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainyaCaraActionPerformed(evt);
            }
        });
        FormInput.add(chkLainyaCara);
        chkLainyaCara.setBounds(480, 738, 63, 23);

        TlainCara.setBackground(new java.awt.Color(245, 250, 240));
        TlainCara.setForeground(new java.awt.Color(0, 0, 0));
        TlainCara.setName("TlainCara"); // NOI18N
        TlainCara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainCaraKeyPressed(evt);
            }
        });
        FormInput.add(TlainCara);
        TlainCara.setBounds(544, 738, 180, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Tali Pusat : ");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(0, 766, 110, 23);

        chkSegar.setBackground(new java.awt.Color(255, 255, 250));
        chkSegar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSegar.setForeground(new java.awt.Color(0, 0, 0));
        chkSegar.setText("Segar");
        chkSegar.setBorderPainted(true);
        chkSegar.setBorderPaintedFlat(true);
        chkSegar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSegar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSegar.setName("chkSegar"); // NOI18N
        chkSegar.setOpaque(false);
        chkSegar.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSegar);
        chkSegar.setBounds(114, 766, 60, 23);

        chkLayu.setBackground(new java.awt.Color(255, 255, 250));
        chkLayu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLayu.setForeground(new java.awt.Color(0, 0, 0));
        chkLayu.setText("Layu");
        chkLayu.setBorderPainted(true);
        chkLayu.setBorderPaintedFlat(true);
        chkLayu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLayu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLayu.setName("chkLayu"); // NOI18N
        chkLayu.setOpaque(false);
        chkLayu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLayu);
        chkLayu.setBounds(190, 766, 50, 23);

        chkSimpul.setBackground(new java.awt.Color(255, 255, 250));
        chkSimpul.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSimpul.setForeground(new java.awt.Color(0, 0, 0));
        chkSimpul.setText("Simpul");
        chkSimpul.setBorderPainted(true);
        chkSimpul.setBorderPaintedFlat(true);
        chkSimpul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSimpul.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSimpul.setName("chkSimpul"); // NOI18N
        chkSimpul.setOpaque(false);
        chkSimpul.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSimpul);
        chkSimpul.setBounds(255, 766, 60, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("c. Faktor Resiko Infeksi");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(0, 794, 140, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Mayor : ");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(0, 822, 110, 23);

        chkIbuDemam.setBackground(new java.awt.Color(255, 255, 250));
        chkIbuDemam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIbuDemam.setForeground(new java.awt.Color(0, 0, 0));
        chkIbuDemam.setText("Ibu Demam > 38 °C");
        chkIbuDemam.setBorderPainted(true);
        chkIbuDemam.setBorderPaintedFlat(true);
        chkIbuDemam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIbuDemam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIbuDemam.setName("chkIbuDemam"); // NOI18N
        chkIbuDemam.setOpaque(false);
        chkIbuDemam.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIbuDemam);
        chkIbuDemam.setBounds(114, 822, 120, 23);

        chkKpd24.setBackground(new java.awt.Color(255, 255, 250));
        chkKpd24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKpd24.setForeground(new java.awt.Color(0, 0, 0));
        chkKpd24.setText("KPD > 24 Jam");
        chkKpd24.setBorderPainted(true);
        chkKpd24.setBorderPaintedFlat(true);
        chkKpd24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKpd24.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKpd24.setName("chkKpd24"); // NOI18N
        chkKpd24.setOpaque(false);
        chkKpd24.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKpd24);
        chkKpd24.setBounds(242, 822, 93, 23);

        chkKetuban.setBackground(new java.awt.Color(255, 255, 250));
        chkKetuban.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKetuban.setForeground(new java.awt.Color(0, 0, 0));
        chkKetuban.setText("Ketuban Hijau");
        chkKetuban.setBorderPainted(true);
        chkKetuban.setBorderPaintedFlat(true);
        chkKetuban.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKetuban.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKetuban.setName("chkKetuban"); // NOI18N
        chkKetuban.setOpaque(false);
        chkKetuban.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKetuban);
        chkKetuban.setBounds(342, 822, 95, 23);

        chkChorio.setBackground(new java.awt.Color(255, 255, 250));
        chkChorio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkChorio.setForeground(new java.awt.Color(0, 0, 0));
        chkChorio.setText("Chorioamnionitis");
        chkChorio.setBorderPainted(true);
        chkChorio.setBorderPaintedFlat(true);
        chkChorio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkChorio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkChorio.setName("chkChorio"); // NOI18N
        chkChorio.setOpaque(false);
        chkChorio.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkChorio);
        chkChorio.setBounds(445, 822, 107, 23);

        chkFetal.setBackground(new java.awt.Color(255, 255, 250));
        chkFetal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFetal.setForeground(new java.awt.Color(0, 0, 0));
        chkFetal.setText("Fetal Distress");
        chkFetal.setBorderPainted(true);
        chkFetal.setBorderPaintedFlat(true);
        chkFetal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFetal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFetal.setName("chkFetal"); // NOI18N
        chkFetal.setOpaque(false);
        chkFetal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkFetal);
        chkFetal.setBounds(560, 822, 100, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Minor : ");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(0, 850, 110, 23);

        chkKpd12.setBackground(new java.awt.Color(255, 255, 250));
        chkKpd12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKpd12.setForeground(new java.awt.Color(0, 0, 0));
        chkKpd12.setText("KPD < 12 Jam");
        chkKpd12.setBorderPainted(true);
        chkKpd12.setBorderPaintedFlat(true);
        chkKpd12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKpd12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKpd12.setName("chkKpd12"); // NOI18N
        chkKpd12.setOpaque(false);
        chkKpd12.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKpd12);
        chkKpd12.setBounds(114, 850, 90, 23);

        chkAsfiksia.setBackground(new java.awt.Color(255, 255, 250));
        chkAsfiksia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsfiksia.setForeground(new java.awt.Color(0, 0, 0));
        chkAsfiksia.setText("Asfiksia");
        chkAsfiksia.setBorderPainted(true);
        chkAsfiksia.setBorderPaintedFlat(true);
        chkAsfiksia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsfiksia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsfiksia.setName("chkAsfiksia"); // NOI18N
        chkAsfiksia.setOpaque(false);
        chkAsfiksia.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAsfiksia);
        chkAsfiksia.setBounds(212, 850, 64, 23);

        chkBblr.setBackground(new java.awt.Color(255, 255, 250));
        chkBblr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBblr.setForeground(new java.awt.Color(0, 0, 0));
        chkBblr.setText("BBLR");
        chkBblr.setBorderPainted(true);
        chkBblr.setBorderPaintedFlat(true);
        chkBblr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBblr.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBblr.setName("chkBblr"); // NOI18N
        chkBblr.setOpaque(false);
        chkBblr.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBblr);
        chkBblr.setBounds(284, 850, 50, 23);

        chkIsk.setBackground(new java.awt.Color(255, 255, 250));
        chkIsk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIsk.setForeground(new java.awt.Color(0, 0, 0));
        chkIsk.setText("ISK");
        chkIsk.setBorderPainted(true);
        chkIsk.setBorderPaintedFlat(true);
        chkIsk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIsk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIsk.setName("chkIsk"); // NOI18N
        chkIsk.setOpaque(false);
        chkIsk.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIsk);
        chkIsk.setBounds(340, 850, 45, 23);

        chkUK.setBackground(new java.awt.Color(255, 255, 250));
        chkUK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkUK.setForeground(new java.awt.Color(0, 0, 0));
        chkUK.setText("UK < 37 Minggu");
        chkUK.setBorderPainted(true);
        chkUK.setBorderPaintedFlat(true);
        chkUK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkUK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkUK.setName("chkUK"); // NOI18N
        chkUK.setOpaque(false);
        chkUK.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkUK);
        chkUK.setBounds(390, 850, 102, 23);

        chkGemeli.setBackground(new java.awt.Color(255, 255, 250));
        chkGemeli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGemeli.setForeground(new java.awt.Color(0, 0, 0));
        chkGemeli.setText("Gemeli");
        chkGemeli.setBorderPainted(true);
        chkGemeli.setBorderPaintedFlat(true);
        chkGemeli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGemeli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGemeli.setName("chkGemeli"); // NOI18N
        chkGemeli.setOpaque(false);
        chkGemeli.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGemeli);
        chkGemeli.setBounds(500, 850, 55, 23);

        chkKeputihan.setBackground(new java.awt.Color(255, 255, 250));
        chkKeputihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKeputihan.setForeground(new java.awt.Color(0, 0, 0));
        chkKeputihan.setText("Keputihan");
        chkKeputihan.setBorderPainted(true);
        chkKeputihan.setBorderPaintedFlat(true);
        chkKeputihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKeputihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKeputihan.setName("chkKeputihan"); // NOI18N
        chkKeputihan.setOpaque(false);
        chkKeputihan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKeputihan);
        chkKeputihan.setBounds(563, 850, 75, 23);

        chkSuhuIbu.setBackground(new java.awt.Color(255, 255, 250));
        chkSuhuIbu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSuhuIbu.setForeground(new java.awt.Color(0, 0, 0));
        chkSuhuIbu.setText("Suhu Ibu > 37 °C");
        chkSuhuIbu.setBorderPainted(true);
        chkSuhuIbu.setBorderPaintedFlat(true);
        chkSuhuIbu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSuhuIbu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSuhuIbu.setName("chkSuhuIbu"); // NOI18N
        chkSuhuIbu.setOpaque(false);
        chkSuhuIbu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSuhuIbu);
        chkSuhuIbu.setBounds(645, 850, 110, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("d. Riwayat Alergi (Pada Orang Tua : Ayah/Ibu ; Saudara Kandung) :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(0, 878, 360, 23);

        cmbRiwAlergi.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwAlergi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbRiwAlergi.setName("cmbRiwAlergi"); // NOI18N
        cmbRiwAlergi.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRiwAlergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwAlergiActionPerformed(evt);
            }
        });
        FormInput.add(cmbRiwAlergi);
        cmbRiwAlergi.setBounds(365, 878, 60, 23);

        chkObatAlergi.setBackground(new java.awt.Color(255, 255, 250));
        chkObatAlergi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkObatAlergi.setForeground(new java.awt.Color(0, 0, 0));
        chkObatAlergi.setText("Obat");
        chkObatAlergi.setBorderPainted(true);
        chkObatAlergi.setBorderPaintedFlat(true);
        chkObatAlergi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkObatAlergi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkObatAlergi.setName("chkObatAlergi"); // NOI18N
        chkObatAlergi.setOpaque(false);
        chkObatAlergi.setPreferredSize(new java.awt.Dimension(175, 23));
        chkObatAlergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkObatAlergiActionPerformed(evt);
            }
        });
        FormInput.add(chkObatAlergi);
        chkObatAlergi.setBounds(114, 906, 50, 23);

        TobatAlergi.setBackground(new java.awt.Color(245, 250, 240));
        TobatAlergi.setForeground(new java.awt.Color(0, 0, 0));
        TobatAlergi.setName("TobatAlergi"); // NOI18N
        TobatAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatAlergiKeyPressed(evt);
            }
        });
        FormInput.add(TobatAlergi);
        TobatAlergi.setBounds(169, 906, 555, 23);

        chkMakananAlergi.setBackground(new java.awt.Color(255, 255, 250));
        chkMakananAlergi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMakananAlergi.setForeground(new java.awt.Color(0, 0, 0));
        chkMakananAlergi.setText("Makanan");
        chkMakananAlergi.setBorderPainted(true);
        chkMakananAlergi.setBorderPaintedFlat(true);
        chkMakananAlergi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMakananAlergi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMakananAlergi.setName("chkMakananAlergi"); // NOI18N
        chkMakananAlergi.setOpaque(false);
        chkMakananAlergi.setPreferredSize(new java.awt.Dimension(175, 23));
        chkMakananAlergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMakananAlergiActionPerformed(evt);
            }
        });
        FormInput.add(chkMakananAlergi);
        chkMakananAlergi.setBounds(114, 934, 70, 23);

        TmakananAlergi.setBackground(new java.awt.Color(245, 250, 240));
        TmakananAlergi.setForeground(new java.awt.Color(0, 0, 0));
        TmakananAlergi.setName("TmakananAlergi"); // NOI18N
        TmakananAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmakananAlergiKeyPressed(evt);
            }
        });
        FormInput.add(TmakananAlergi);
        TmakananAlergi.setBounds(189, 934, 535, 23);

        chkLainAlergi.setBackground(new java.awt.Color(255, 255, 250));
        chkLainAlergi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainAlergi.setForeground(new java.awt.Color(0, 0, 0));
        chkLainAlergi.setText("Lainnya");
        chkLainAlergi.setBorderPainted(true);
        chkLainAlergi.setBorderPaintedFlat(true);
        chkLainAlergi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainAlergi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainAlergi.setName("chkLainAlergi"); // NOI18N
        chkLainAlergi.setOpaque(false);
        chkLainAlergi.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainAlergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainAlergiActionPerformed(evt);
            }
        });
        FormInput.add(chkLainAlergi);
        chkLainAlergi.setBounds(114, 962, 65, 23);

        TlainyaAlergi.setBackground(new java.awt.Color(245, 250, 240));
        TlainyaAlergi.setForeground(new java.awt.Color(0, 0, 0));
        TlainyaAlergi.setName("TlainyaAlergi"); // NOI18N
        TlainyaAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainyaAlergiKeyPressed(evt);
            }
        });
        FormInput.add(TlainyaAlergi);
        TlainyaAlergi.setBounds(184, 962, 540, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Reaksi : ");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(0, 990, 110, 23);

        Treaksi.setBackground(new java.awt.Color(245, 250, 240));
        Treaksi.setForeground(new java.awt.Color(0, 0, 0));
        Treaksi.setName("Treaksi"); // NOI18N
        Treaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreaksiKeyPressed(evt);
            }
        });
        FormInput.add(Treaksi);
        Treaksi.setBounds(114, 990, 610, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("5. KEBUTUHAN PSIKOLOGIS (Untuk Orang Tua : Ayah/Ibu)");
        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 1018, 350, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Masalah Perkawinan :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 1046, 170, 23);

        cmbMasalah.setForeground(new java.awt.Color(0, 0, 0));
        cmbMasalah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbMasalah.setName("cmbMasalah"); // NOI18N
        cmbMasalah.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbMasalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMasalahActionPerformed(evt);
            }
        });
        FormInput.add(cmbMasalah);
        cmbMasalah.setBounds(177, 1046, 80, 23);

        cmbAdaPerkawinan.setForeground(new java.awt.Color(0, 0, 0));
        cmbAdaPerkawinan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Cerai", "Istri Baru", "Simpanan", "Lain-lain" }));
        cmbAdaPerkawinan.setName("cmbAdaPerkawinan"); // NOI18N
        cmbAdaPerkawinan.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbAdaPerkawinan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAdaPerkawinanActionPerformed(evt);
            }
        });
        FormInput.add(cmbAdaPerkawinan);
        cmbAdaPerkawinan.setBounds(264, 1046, 80, 23);

        TlainPerkawinan.setBackground(new java.awt.Color(245, 250, 240));
        TlainPerkawinan.setForeground(new java.awt.Color(0, 0, 0));
        TlainPerkawinan.setName("TlainPerkawinan"); // NOI18N
        TlainPerkawinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainPerkawinanKeyPressed(evt);
            }
        });
        FormInput.add(TlainPerkawinan);
        TlainPerkawinan.setBounds(349, 1046, 375, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Mengalami Kekerasan Fisik :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 1074, 170, 23);

        cmbMengalami.setForeground(new java.awt.Color(0, 0, 0));
        cmbMengalami.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbMengalami.setName("cmbMengalami"); // NOI18N
        cmbMengalami.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbMengalami.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMengalamiActionPerformed(evt);
            }
        });
        FormInput.add(cmbMengalami);
        cmbMengalami.setBounds(177, 1074, 80, 23);

        cmbAdaMengalami.setForeground(new java.awt.Color(0, 0, 0));
        cmbAdaMengalami.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mencederai Diri", "Mencederai Orang Lain" }));
        cmbAdaMengalami.setName("cmbAdaMengalami"); // NOI18N
        cmbAdaMengalami.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbAdaMengalami);
        cmbAdaMengalami.setBounds(264, 1074, 145, 23);

        cmbDialami.setForeground(new java.awt.Color(0, 0, 0));
        cmbDialami.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Pernah", "Tidak Pernah" }));
        cmbDialami.setName("cmbDialami"); // NOI18N
        cmbDialami.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbDialami);
        cmbDialami.setBounds(415, 1074, 95, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Trauma Dalam Kehidupan :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 1102, 170, 23);

        cmbTrauma.setForeground(new java.awt.Color(0, 0, 0));
        cmbTrauma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbTrauma.setName("cmbTrauma"); // NOI18N
        cmbTrauma.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbTrauma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTraumaActionPerformed(evt);
            }
        });
        FormInput.add(cmbTrauma);
        cmbTrauma.setBounds(177, 1102, 80, 23);

        TjelaskanTrauma.setBackground(new java.awt.Color(245, 250, 240));
        TjelaskanTrauma.setForeground(new java.awt.Color(0, 0, 0));
        TjelaskanTrauma.setName("TjelaskanTrauma"); // NOI18N
        TjelaskanTrauma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjelaskanTraumaKeyPressed(evt);
            }
        });
        FormInput.add(TjelaskanTrauma);
        TjelaskanTrauma.setBounds(329, 1102, 395, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Jelaskan : ");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(260, 1102, 66, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Gangguan Tidur :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 1130, 170, 23);

        cmbGangguan.setForeground(new java.awt.Color(0, 0, 0));
        cmbGangguan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbGangguan.setName("cmbGangguan"); // NOI18N
        cmbGangguan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbGangguan);
        cmbGangguan.setBounds(177, 1130, 80, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Konsultasi Dengan Psikologi/Psikiater :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(260, 1130, 200, 23);

        cmbKonsultasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKonsultasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbKonsultasi.setName("cmbKonsultasi"); // NOI18N
        cmbKonsultasi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKonsultasi);
        cmbKonsultasi.setBounds(465, 1130, 80, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Penerimaan Terhadap Kondisi Bayi Saat Ini :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 1158, 240, 23);

        cmbPenerimaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenerimaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Menerima", "Tidak Menerima" }));
        cmbPenerimaan.setName("cmbPenerimaan"); // NOI18N
        cmbPenerimaan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbPenerimaan);
        cmbPenerimaan.setBounds(247, 1158, 108, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Dukungan Sosial Dari :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 1186, 170, 23);

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
        FormInput.add(chkSuami);
        chkSuami.setBounds(177, 1186, 55, 23);

        chkOrangTua.setBackground(new java.awt.Color(255, 255, 250));
        chkOrangTua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkOrangTua.setForeground(new java.awt.Color(0, 0, 0));
        chkOrangTua.setText("Orang Tua");
        chkOrangTua.setBorderPainted(true);
        chkOrangTua.setBorderPaintedFlat(true);
        chkOrangTua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkOrangTua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkOrangTua.setName("chkOrangTua"); // NOI18N
        chkOrangTua.setOpaque(false);
        chkOrangTua.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkOrangTua);
        chkOrangTua.setBounds(240, 1186, 80, 23);

        chkKeluarga.setBackground(new java.awt.Color(255, 255, 250));
        chkKeluarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        chkKeluarga.setText("Keluarga");
        chkKeluarga.setBorderPainted(true);
        chkKeluarga.setBorderPaintedFlat(true);
        chkKeluarga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKeluarga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKeluarga.setName("chkKeluarga"); // NOI18N
        chkKeluarga.setOpaque(false);
        chkKeluarga.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKeluarga);
        chkKeluarga.setBounds(330, 1186, 70, 23);

        chkLainDukungan.setBackground(new java.awt.Color(255, 255, 250));
        chkLainDukungan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainDukungan.setForeground(new java.awt.Color(0, 0, 0));
        chkLainDukungan.setText("Lain - Lain");
        chkLainDukungan.setBorderPainted(true);
        chkLainDukungan.setBorderPaintedFlat(true);
        chkLainDukungan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainDukungan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainDukungan.setName("chkLainDukungan"); // NOI18N
        chkLainDukungan.setOpaque(false);
        chkLainDukungan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainDukungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainDukunganActionPerformed(evt);
            }
        });
        FormInput.add(chkLainDukungan);
        chkLainDukungan.setBounds(410, 1186, 75, 23);

        TlainDukungan.setBackground(new java.awt.Color(245, 250, 240));
        TlainDukungan.setForeground(new java.awt.Color(0, 0, 0));
        TlainDukungan.setName("TlainDukungan"); // NOI18N
        TlainDukungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainDukunganKeyPressed(evt);
            }
        });
        FormInput.add(TlainDukungan);
        TlainDukungan.setBounds(486, 1186, 238, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("6. RIWAYAT SOSIAL EKONOMI DAN SPIRITUAL (Orang Tua)");
        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 1214, 350, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("a. Status Pernikahan :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 1242, 160, 23);

        cmbStatusNikah.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatusNikah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Single", "Menikah", "Bercerai" }));
        cmbStatusNikah.setName("cmbStatusNikah"); // NOI18N
        cmbStatusNikah.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbStatusNikah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusNikahActionPerformed(evt);
            }
        });
        FormInput.add(cmbStatusNikah);
        cmbStatusNikah.setBounds(164, 1242, 75, 23);

        TkaliMenikah.setBackground(new java.awt.Color(245, 250, 240));
        TkaliMenikah.setForeground(new java.awt.Color(0, 0, 0));
        TkaliMenikah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TkaliMenikah.setName("TkaliMenikah"); // NOI18N
        TkaliMenikah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkaliMenikahKeyPressed(evt);
            }
        });
        FormInput.add(TkaliMenikah);
        TkaliMenikah.setBounds(245, 1242, 40, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("Kali");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(290, 1242, 30, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("b. Status Sosial : Hubungan Pasien Dengan Anggota Keluarga :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 1270, 355, 23);

        cmbHubungan.setForeground(new java.awt.Color(0, 0, 0));
        cmbHubungan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Baik", "Tidak Baik" }));
        cmbHubungan.setName("cmbHubungan"); // NOI18N
        cmbHubungan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbHubungan);
        cmbHubungan.setBounds(360, 1270, 80, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Tinggal Bersama :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 1298, 160, 23);

        cmbTinggal.setForeground(new java.awt.Color(0, 0, 0));
        cmbTinggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Suami", "Istri", "Suami/Istri", "Anak", "Orang Tua", "Sendiri", "Lain-lain" }));
        cmbTinggal.setName("cmbTinggal"); // NOI18N
        cmbTinggal.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbTinggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTinggalActionPerformed(evt);
            }
        });
        FormInput.add(cmbTinggal);
        cmbTinggal.setBounds(164, 1298, 85, 23);

        TlainTinggal.setBackground(new java.awt.Color(245, 250, 240));
        TlainTinggal.setForeground(new java.awt.Color(0, 0, 0));
        TlainTinggal.setName("TlainTinggal"); // NOI18N
        TlainTinggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainTinggalKeyPressed(evt);
            }
        });
        FormInput.add(TlainTinggal);
        TlainTinggal.setBounds(254, 1298, 470, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Tempat Tinggal :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 1326, 160, 23);

        cmbTempat.setForeground(new java.awt.Color(0, 0, 0));
        cmbTempat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Rumah", "Apartemen", "Panti", "Lainnya" }));
        cmbTempat.setName("cmbTempat"); // NOI18N
        cmbTempat.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbTempat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTempatActionPerformed(evt);
            }
        });
        FormInput.add(cmbTempat);
        cmbTempat.setBounds(164, 1326, 85, 23);

        TlainTempat.setBackground(new java.awt.Color(245, 250, 240));
        TlainTempat.setForeground(new java.awt.Color(0, 0, 0));
        TlainTempat.setName("TlainTempat"); // NOI18N
        TlainTempat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainTempatKeyPressed(evt);
            }
        });
        FormInput.add(TlainTempat);
        TlainTempat.setBounds(254, 1326, 470, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Kerabat Terdekat Yang Dapat Dihubungi :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(0, 1354, 250, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Nama :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 1382, 160, 23);

        TnmKerabat.setBackground(new java.awt.Color(245, 250, 240));
        TnmKerabat.setForeground(new java.awt.Color(0, 0, 0));
        TnmKerabat.setName("TnmKerabat"); // NOI18N
        TnmKerabat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmKerabatKeyPressed(evt);
            }
        });
        FormInput.add(TnmKerabat);
        TnmKerabat.setBounds(164, 1382, 560, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Hubungan :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(0, 1410, 160, 23);

        ThubKerabat.setBackground(new java.awt.Color(245, 250, 240));
        ThubKerabat.setForeground(new java.awt.Color(0, 0, 0));
        ThubKerabat.setName("ThubKerabat"); // NOI18N
        ThubKerabat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThubKerabatKeyPressed(evt);
            }
        });
        FormInput.add(ThubKerabat);
        ThubKerabat.setBounds(164, 1410, 340, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Telepon :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(510, 1410, 60, 23);

        TtelpKerabat.setBackground(new java.awt.Color(245, 250, 240));
        TtelpKerabat.setForeground(new java.awt.Color(0, 0, 0));
        TtelpKerabat.setName("TtelpKerabat"); // NOI18N
        TtelpKerabat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtelpKerabatKeyPressed(evt);
            }
        });
        FormInput.add(TtelpKerabat);
        TtelpKerabat.setBounds(574, 1410, 150, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("c. Status Spiritual :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 1438, 160, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Kegiatan Keagamaan Yang Biasa Dilakukan :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(0, 1466, 250, 23);

        TkegiatanAgama.setBackground(new java.awt.Color(245, 250, 240));
        TkegiatanAgama.setForeground(new java.awt.Color(0, 0, 0));
        TkegiatanAgama.setName("TkegiatanAgama"); // NOI18N
        TkegiatanAgama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkegiatanAgamaKeyPressed(evt);
            }
        });
        FormInput.add(TkegiatanAgama);
        TkegiatanAgama.setBounds(254, 1466, 470, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Kegiatan Spiritual Yang Dibutuhkan Selama Perawatan :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(0, 1494, 300, 23);

        TkegiatanSpiritual.setBackground(new java.awt.Color(245, 250, 240));
        TkegiatanSpiritual.setForeground(new java.awt.Color(0, 0, 0));
        TkegiatanSpiritual.setName("TkegiatanSpiritual"); // NOI18N
        TkegiatanSpiritual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkegiatanSpiritualKeyPressed(evt);
            }
        });
        FormInput.add(TkegiatanSpiritual);
        TkegiatanSpiritual.setBounds(304, 1494, 420, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("7. PENILAIAN NYERI NEONATUS");
        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(0, 1522, 200, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Nyeri : ");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 1550, 80, 23);

        cmbNyeri.setForeground(new java.awt.Color(0, 0, 0));
        cmbNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbNyeri.setName("cmbNyeri"); // NOI18N
        cmbNyeri.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbNyeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNyeriActionPerformed(evt);
            }
        });
        FormInput.add(cmbNyeri);
        cmbNyeri.setBounds(82, 1550, 60, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Penilaian : ");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 1578, 80, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("1. Crying : ");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(82, 1578, 80, 23);

        cmbCrying.setForeground(new java.awt.Color(0, 0, 0));
        cmbCrying.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak ada tangisan/tangisan tidak melengking", "Tangisan melengking tapi bayi mudah dihibur", "Tangisan melengking dan bayi tidak mudah dihibur" }));
        cmbCrying.setName("cmbCrying"); // NOI18N
        cmbCrying.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbCrying.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCryingActionPerformed(evt);
            }
        });
        FormInput.add(cmbCrying);
        cmbCrying.setBounds(165, 1578, 280, 23);

        TnilaiCrying.setEditable(false);
        TnilaiCrying.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiCrying.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiCrying.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiCrying.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiCrying.setName("TnilaiCrying"); // NOI18N
        FormInput.add(TnilaiCrying);
        TnilaiCrying.setBounds(564, 1578, 40, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Nilai : ");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(522, 1578, 40, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("2. Requires : ");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(82, 1606, 80, 23);

        cmbRequires.setForeground(new java.awt.Color(0, 0, 0));
        cmbRequires.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak perlu oksigen", "Perlu oksigen < 30", "Perlu oksigen > 30" }));
        cmbRequires.setName("cmbRequires"); // NOI18N
        cmbRequires.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRequires.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRequiresActionPerformed(evt);
            }
        });
        FormInput.add(cmbRequires);
        cmbRequires.setBounds(165, 1606, 125, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Nilai : ");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(522, 1606, 40, 23);

        TnilaiRequires.setEditable(false);
        TnilaiRequires.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiRequires.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiRequires.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiRequires.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiRequires.setName("TnilaiRequires"); // NOI18N
        FormInput.add(TnilaiRequires);
        TnilaiRequires.setBounds(564, 1606, 40, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("3. Increased : ");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(82, 1634, 80, 23);

        cmbIncreased.setForeground(new java.awt.Color(0, 0, 0));
        cmbIncreased.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Detak jantung dan tekanan darah tidak berubah dari base line", "Detak jantung dan tekanan darah meningkat < 20 dari base line", "Detak jantung dan tekanan darah meningkat > 20 dari base line" }));
        cmbIncreased.setName("cmbIncreased"); // NOI18N
        cmbIncreased.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbIncreased.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbIncreasedActionPerformed(evt);
            }
        });
        FormInput.add(cmbIncreased);
        cmbIncreased.setBounds(165, 1634, 340, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Nilai : ");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(522, 1634, 40, 23);

        TnilaiIncreased.setEditable(false);
        TnilaiIncreased.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiIncreased.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiIncreased.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiIncreased.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiIncreased.setName("TnilaiIncreased"); // NOI18N
        FormInput.add(TnilaiIncreased);
        TnilaiIncreased.setBounds(564, 1634, 40, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("4. Expression : ");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(72, 1662, 90, 23);

        cmbExpresion.setForeground(new java.awt.Color(0, 0, 0));
        cmbExpresion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak ada seringal", "Ada seringal" }));
        cmbExpresion.setName("cmbExpresion"); // NOI18N
        cmbExpresion.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbExpresion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbExpresionActionPerformed(evt);
            }
        });
        FormInput.add(cmbExpresion);
        cmbExpresion.setBounds(165, 1662, 120, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Nilai : ");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(522, 1662, 40, 23);

        TnilaiExpresion.setEditable(false);
        TnilaiExpresion.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiExpresion.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiExpresion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiExpresion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiExpresion.setName("TnilaiExpresion"); // NOI18N
        FormInput.add(TnilaiExpresion);
        TnilaiExpresion.setBounds(564, 1662, 40, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("5. Sleepless : ");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(82, 1690, 80, 23);

        cmbSleepless.setForeground(new java.awt.Color(0, 0, 0));
        cmbSleepless.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bayi secara terus menerus tidur", "Bayi terbangun pada interval berulang", "Bayi terjaga/terbangun terus menerus" }));
        cmbSleepless.setName("cmbSleepless"); // NOI18N
        cmbSleepless.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbSleepless.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSleeplessActionPerformed(evt);
            }
        });
        FormInput.add(cmbSleepless);
        cmbSleepless.setBounds(165, 1690, 220, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Nilai : ");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(522, 1690, 40, 23);

        TnilaiSleepless.setEditable(false);
        TnilaiSleepless.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiSleepless.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiSleepless.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiSleepless.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiSleepless.setName("TnilaiSleepless"); // NOI18N
        FormInput.add(TnilaiSleepless);
        TnilaiSleepless.setBounds(564, 1690, 40, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Total Nilai :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(610, 1690, 60, 23);

        TtotNilaiNyeri.setEditable(false);
        TtotNilaiNyeri.setBackground(new java.awt.Color(245, 250, 240));
        TtotNilaiNyeri.setForeground(new java.awt.Color(0, 0, 0));
        TtotNilaiNyeri.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TtotNilaiNyeri.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TtotNilaiNyeri.setName("TtotNilaiNyeri"); // NOI18N
        FormInput.add(TtotNilaiNyeri);
        TtotNilaiNyeri.setBounds(674, 1690, 50, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Kesimpulan Penilaian Nyeri : ");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(0, 1718, 162, 23);

        TkesimpulanNyeri.setEditable(false);
        TkesimpulanNyeri.setBackground(new java.awt.Color(245, 250, 240));
        TkesimpulanNyeri.setForeground(new java.awt.Color(0, 0, 0));
        TkesimpulanNyeri.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TkesimpulanNyeri.setName("TkesimpulanNyeri"); // NOI18N
        FormInput.add(TkesimpulanNyeri);
        TkesimpulanNyeri.setBounds(165, 1718, 560, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("8. PENILAIAN UMUR KEHAMILAN");
        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(0, 1746, 200, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Maturitas Neomuskular : ");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(0, 1774, 162, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/sikap_tubuh0.jpg"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(269, 1815, 60, 60);

        chkSikap0.setBackground(new java.awt.Color(255, 255, 250));
        chkSikap0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkSikap0);
        chkSikap0.setForeground(new java.awt.Color(0, 0, 0));
        chkSikap0.setBorderPainted(true);
        chkSikap0.setBorderPaintedFlat(true);
        chkSikap0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSikap0.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSikap0.setName("chkSikap0"); // NOI18N
        chkSikap0.setOpaque(false);
        chkSikap0.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSikap0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSikap0ActionPerformed(evt);
            }
        });
        FormInput.add(chkSikap0);
        chkSikap0.setBounds(238, 1833, 25, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Sikap Tubuh : ");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(0, 1830, 125, 23);

        chkSikap1.setBackground(new java.awt.Color(255, 255, 250));
        chkSikap1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkSikap1);
        chkSikap1.setForeground(new java.awt.Color(0, 0, 0));
        chkSikap1.setBorderPainted(true);
        chkSikap1.setBorderPaintedFlat(true);
        chkSikap1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSikap1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSikap1.setName("chkSikap1"); // NOI18N
        chkSikap1.setOpaque(false);
        chkSikap1.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSikap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSikap1ActionPerformed(evt);
            }
        });
        FormInput.add(chkSikap1);
        chkSikap1.setBounds(345, 1833, 25, 23);

        PanelWall1.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/sikap_tubuh1.jpg"))); // NOI18N
        PanelWall1.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall1.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall1.setRound(false);
        PanelWall1.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall1.setLayout(null);
        FormInput.add(PanelWall1);
        PanelWall1.setBounds(375, 1815, 60, 60);

        chkSikap2.setBackground(new java.awt.Color(255, 255, 250));
        chkSikap2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkSikap2);
        chkSikap2.setForeground(new java.awt.Color(0, 0, 0));
        chkSikap2.setBorderPainted(true);
        chkSikap2.setBorderPaintedFlat(true);
        chkSikap2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSikap2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSikap2.setName("chkSikap2"); // NOI18N
        chkSikap2.setOpaque(false);
        chkSikap2.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSikap2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSikap2ActionPerformed(evt);
            }
        });
        FormInput.add(chkSikap2);
        chkSikap2.setBounds(450, 1833, 25, 23);

        PanelWall2.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall2.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/sikap_tubuh2.jpg"))); // NOI18N
        PanelWall2.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall2.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall2.setRound(false);
        PanelWall2.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall2.setLayout(null);
        FormInput.add(PanelWall2);
        PanelWall2.setBounds(481, 1815, 60, 60);

        chkSikap3.setBackground(new java.awt.Color(255, 255, 250));
        chkSikap3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkSikap3);
        chkSikap3.setForeground(new java.awt.Color(0, 0, 0));
        chkSikap3.setBorderPainted(true);
        chkSikap3.setBorderPaintedFlat(true);
        chkSikap3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSikap3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSikap3.setName("chkSikap3"); // NOI18N
        chkSikap3.setOpaque(false);
        chkSikap3.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSikap3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSikap3ActionPerformed(evt);
            }
        });
        FormInput.add(chkSikap3);
        chkSikap3.setBounds(555, 1833, 25, 23);

        PanelWall3.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall3.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/sikap_tubuh3.jpg"))); // NOI18N
        PanelWall3.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall3.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall3.setRound(false);
        PanelWall3.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall3.setLayout(null);
        FormInput.add(PanelWall3);
        PanelWall3.setBounds(585, 1815, 60, 60);

        chkSikap4.setBackground(new java.awt.Color(255, 255, 250));
        chkSikap4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkSikap4);
        chkSikap4.setForeground(new java.awt.Color(0, 0, 0));
        chkSikap4.setBorderPainted(true);
        chkSikap4.setBorderPaintedFlat(true);
        chkSikap4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSikap4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSikap4.setName("chkSikap4"); // NOI18N
        chkSikap4.setOpaque(false);
        chkSikap4.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSikap4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSikap4ActionPerformed(evt);
            }
        });
        FormInput.add(chkSikap4);
        chkSikap4.setBounds(660, 1833, 25, 23);

        PanelWall4.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall4.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/sikap_tubuh4.jpg"))); // NOI18N
        PanelWall4.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall4.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall4.setRound(false);
        PanelWall4.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall4.setLayout(null);
        FormInput.add(PanelWall4);
        PanelWall4.setBounds(690, 1815, 60, 60);

        TnilaiSikap.setEditable(false);
        TnilaiSikap.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiSikap.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiSikap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiSikap.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiSikap.setName("TnilaiSikap"); // NOI18N
        FormInput.add(TnilaiSikap);
        TnilaiSikap.setBounds(870, 1830, 40, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel107.setText("-1");
        jLabel107.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(185, 1790, 20, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel108.setText("0");
        jLabel108.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(293, 1790, 20, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel109.setText("1");
        jLabel109.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(395, 1790, 20, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel110.setText("2");
        jLabel110.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(500, 1790, 20, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel111.setText("3");
        jLabel111.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(604, 1790, 20, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel112.setText("4");
        jLabel112.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(708, 1790, 20, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel113.setText("5");
        jLabel113.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(812, 1790, 20, 23);

        PanelWall5.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall5.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/persegi0.jpg"))); // NOI18N
        PanelWall5.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall5.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall5.setRound(false);
        PanelWall5.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall5.setLayout(null);
        FormInput.add(PanelWall5);
        PanelWall5.setBounds(269, 1880, 60, 60);

        PanelWall6.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall6.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/persegi1.jpg"))); // NOI18N
        PanelWall6.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall6.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall6.setRound(false);
        PanelWall6.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall6.setLayout(null);
        FormInput.add(PanelWall6);
        PanelWall6.setBounds(375, 1880, 60, 60);

        PanelWall7.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall7.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/persegi2.jpg"))); // NOI18N
        PanelWall7.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall7.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall7.setRound(false);
        PanelWall7.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall7.setLayout(null);
        FormInput.add(PanelWall7);
        PanelWall7.setBounds(481, 1880, 60, 60);

        PanelWall8.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall8.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/persegi3.jpg"))); // NOI18N
        PanelWall8.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall8.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall8.setRound(false);
        PanelWall8.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall8.setLayout(null);
        FormInput.add(PanelWall8);
        PanelWall8.setBounds(585, 1880, 60, 60);

        PanelWall9.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall9.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/persegi4.jpg"))); // NOI18N
        PanelWall9.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall9.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall9.setRound(false);
        PanelWall9.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall9.setLayout(null);
        FormInput.add(PanelWall9);
        PanelWall9.setBounds(690, 1880, 60, 60);

        PanelWall10.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall10.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/persegi-1.jpg"))); // NOI18N
        PanelWall10.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall10.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall10.setRound(false);
        PanelWall10.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall10.setLayout(null);
        FormInput.add(PanelWall10);
        PanelWall10.setBounds(163, 1880, 60, 60);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("Persegi Jendela  ");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(0, 1883, 125, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("(Pergelangan  ");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(0, 1899, 125, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("Tangan) : ");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(0, 1916, 125, 23);

        chkPersegi0.setBackground(new java.awt.Color(255, 255, 250));
        chkPersegi0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup2.add(chkPersegi0);
        chkPersegi0.setForeground(new java.awt.Color(0, 0, 0));
        chkPersegi0.setBorderPainted(true);
        chkPersegi0.setBorderPaintedFlat(true);
        chkPersegi0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPersegi0.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPersegi0.setName("chkPersegi0"); // NOI18N
        chkPersegi0.setOpaque(false);
        chkPersegi0.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPersegi0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPersegi0ActionPerformed(evt);
            }
        });
        FormInput.add(chkPersegi0);
        chkPersegi0.setBounds(238, 1898, 25, 23);

        chkPersegi1.setBackground(new java.awt.Color(255, 255, 250));
        chkPersegi1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup2.add(chkPersegi1);
        chkPersegi1.setForeground(new java.awt.Color(0, 0, 0));
        chkPersegi1.setBorderPainted(true);
        chkPersegi1.setBorderPaintedFlat(true);
        chkPersegi1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPersegi1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPersegi1.setName("chkPersegi1"); // NOI18N
        chkPersegi1.setOpaque(false);
        chkPersegi1.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPersegi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPersegi1ActionPerformed(evt);
            }
        });
        FormInput.add(chkPersegi1);
        chkPersegi1.setBounds(345, 1898, 25, 23);

        chkPersegi2.setBackground(new java.awt.Color(255, 255, 250));
        chkPersegi2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup2.add(chkPersegi2);
        chkPersegi2.setForeground(new java.awt.Color(0, 0, 0));
        chkPersegi2.setBorderPainted(true);
        chkPersegi2.setBorderPaintedFlat(true);
        chkPersegi2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPersegi2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPersegi2.setName("chkPersegi2"); // NOI18N
        chkPersegi2.setOpaque(false);
        chkPersegi2.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPersegi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPersegi2ActionPerformed(evt);
            }
        });
        FormInput.add(chkPersegi2);
        chkPersegi2.setBounds(450, 1898, 25, 23);

        chkPersegi3.setBackground(new java.awt.Color(255, 255, 250));
        chkPersegi3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup2.add(chkPersegi3);
        chkPersegi3.setForeground(new java.awt.Color(0, 0, 0));
        chkPersegi3.setBorderPainted(true);
        chkPersegi3.setBorderPaintedFlat(true);
        chkPersegi3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPersegi3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPersegi3.setName("chkPersegi3"); // NOI18N
        chkPersegi3.setOpaque(false);
        chkPersegi3.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPersegi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPersegi3ActionPerformed(evt);
            }
        });
        FormInput.add(chkPersegi3);
        chkPersegi3.setBounds(555, 1898, 25, 23);

        chkPersegi4.setBackground(new java.awt.Color(255, 255, 250));
        chkPersegi4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup2.add(chkPersegi4);
        chkPersegi4.setForeground(new java.awt.Color(0, 0, 0));
        chkPersegi4.setBorderPainted(true);
        chkPersegi4.setBorderPaintedFlat(true);
        chkPersegi4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPersegi4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPersegi4.setName("chkPersegi4"); // NOI18N
        chkPersegi4.setOpaque(false);
        chkPersegi4.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPersegi4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPersegi4ActionPerformed(evt);
            }
        });
        FormInput.add(chkPersegi4);
        chkPersegi4.setBounds(660, 1898, 25, 23);

        chkPersegi_1.setBackground(new java.awt.Color(255, 255, 250));
        chkPersegi_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup2.add(chkPersegi_1);
        chkPersegi_1.setForeground(new java.awt.Color(0, 0, 0));
        chkPersegi_1.setBorderPainted(true);
        chkPersegi_1.setBorderPaintedFlat(true);
        chkPersegi_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPersegi_1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPersegi_1.setName("chkPersegi_1"); // NOI18N
        chkPersegi_1.setOpaque(false);
        chkPersegi_1.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPersegi_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPersegi_1ActionPerformed(evt);
            }
        });
        FormInput.add(chkPersegi_1);
        chkPersegi_1.setBounds(133, 1898, 25, 23);

        TnilaiPersegi.setEditable(false);
        TnilaiPersegi.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiPersegi.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiPersegi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiPersegi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiPersegi.setName("TnilaiPersegi"); // NOI18N
        FormInput.add(TnilaiPersegi);
        TnilaiPersegi.setBounds(870, 1898, 40, 23);

        PanelWall12.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall12.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/rekoli0.jpg"))); // NOI18N
        PanelWall12.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall12.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall12.setRound(false);
        PanelWall12.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall12.setLayout(null);
        FormInput.add(PanelWall12);
        PanelWall12.setBounds(269, 1945, 60, 60);

        PanelWall13.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall13.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/rekoli1.jpg"))); // NOI18N
        PanelWall13.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall13.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall13.setRound(false);
        PanelWall13.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall13.setLayout(null);
        FormInput.add(PanelWall13);
        PanelWall13.setBounds(375, 1945, 60, 60);

        PanelWall14.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall14.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/rekoli2.jpg"))); // NOI18N
        PanelWall14.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall14.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall14.setRound(false);
        PanelWall14.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall14.setLayout(null);
        FormInput.add(PanelWall14);
        PanelWall14.setBounds(481, 1945, 60, 60);

        PanelWall15.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall15.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/rekoli3.jpg"))); // NOI18N
        PanelWall15.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall15.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall15.setRound(false);
        PanelWall15.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall15.setLayout(null);
        FormInput.add(PanelWall15);
        PanelWall15.setBounds(585, 1945, 60, 60);

        PanelWall16.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall16.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/rekoli4.jpg"))); // NOI18N
        PanelWall16.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall16.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall16.setRound(false);
        PanelWall16.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall16.setLayout(null);
        FormInput.add(PanelWall16);
        PanelWall16.setBounds(690, 1945, 60, 60);

        PanelWall18.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall18.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/sudut-1.jpg"))); // NOI18N
        PanelWall18.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall18.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall18.setRound(false);
        PanelWall18.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall18.setLayout(null);
        FormInput.add(PanelWall18);
        PanelWall18.setBounds(163, 2010, 60, 60);

        PanelWall19.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall19.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/sudut0.jpg"))); // NOI18N
        PanelWall19.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall19.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall19.setRound(false);
        PanelWall19.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall19.setLayout(null);
        FormInput.add(PanelWall19);
        PanelWall19.setBounds(269, 2010, 60, 60);

        PanelWall20.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall20.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/sudut1.jpg"))); // NOI18N
        PanelWall20.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall20.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall20.setRound(false);
        PanelWall20.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall20.setLayout(null);
        FormInput.add(PanelWall20);
        PanelWall20.setBounds(375, 2010, 60, 60);

        PanelWall21.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall21.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/sudut2.jpg"))); // NOI18N
        PanelWall21.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall21.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall21.setRound(false);
        PanelWall21.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall21.setLayout(null);
        FormInput.add(PanelWall21);
        PanelWall21.setBounds(481, 2010, 60, 60);

        PanelWall22.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall22.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/sudut3.jpg"))); // NOI18N
        PanelWall22.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall22.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall22.setRound(false);
        PanelWall22.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall22.setLayout(null);
        FormInput.add(PanelWall22);
        PanelWall22.setBounds(585, 2010, 60, 60);

        PanelWall23.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall23.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/sudut4.jpg"))); // NOI18N
        PanelWall23.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall23.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall23.setRound(false);
        PanelWall23.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall23.setLayout(null);
        FormInput.add(PanelWall23);
        PanelWall23.setBounds(690, 2010, 60, 60);

        PanelWall24.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall24.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/sudut5.jpg"))); // NOI18N
        PanelWall24.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall24.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall24.setRound(false);
        PanelWall24.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall24.setLayout(null);
        FormInput.add(PanelWall24);
        PanelWall24.setBounds(795, 2010, 60, 60);

        chkRekoli0.setBackground(new java.awt.Color(255, 255, 250));
        chkRekoli0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup3.add(chkRekoli0);
        chkRekoli0.setForeground(new java.awt.Color(0, 0, 0));
        chkRekoli0.setBorderPainted(true);
        chkRekoli0.setBorderPaintedFlat(true);
        chkRekoli0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkRekoli0.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkRekoli0.setName("chkRekoli0"); // NOI18N
        chkRekoli0.setOpaque(false);
        chkRekoli0.setPreferredSize(new java.awt.Dimension(175, 23));
        chkRekoli0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRekoli0ActionPerformed(evt);
            }
        });
        FormInput.add(chkRekoli0);
        chkRekoli0.setBounds(238, 1963, 25, 23);

        chkRekoli1.setBackground(new java.awt.Color(255, 255, 250));
        chkRekoli1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup3.add(chkRekoli1);
        chkRekoli1.setForeground(new java.awt.Color(0, 0, 0));
        chkRekoli1.setBorderPainted(true);
        chkRekoli1.setBorderPaintedFlat(true);
        chkRekoli1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkRekoli1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkRekoli1.setName("chkRekoli1"); // NOI18N
        chkRekoli1.setOpaque(false);
        chkRekoli1.setPreferredSize(new java.awt.Dimension(175, 23));
        chkRekoli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRekoli1ActionPerformed(evt);
            }
        });
        FormInput.add(chkRekoli1);
        chkRekoli1.setBounds(345, 1963, 25, 23);

        chkRekoli2.setBackground(new java.awt.Color(255, 255, 250));
        chkRekoli2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup3.add(chkRekoli2);
        chkRekoli2.setForeground(new java.awt.Color(0, 0, 0));
        chkRekoli2.setBorderPainted(true);
        chkRekoli2.setBorderPaintedFlat(true);
        chkRekoli2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkRekoli2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkRekoli2.setName("chkRekoli2"); // NOI18N
        chkRekoli2.setOpaque(false);
        chkRekoli2.setPreferredSize(new java.awt.Dimension(175, 23));
        chkRekoli2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRekoli2ActionPerformed(evt);
            }
        });
        FormInput.add(chkRekoli2);
        chkRekoli2.setBounds(450, 1963, 25, 23);

        chkRekoli3.setBackground(new java.awt.Color(255, 255, 250));
        chkRekoli3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup3.add(chkRekoli3);
        chkRekoli3.setForeground(new java.awt.Color(0, 0, 0));
        chkRekoli3.setBorderPainted(true);
        chkRekoli3.setBorderPaintedFlat(true);
        chkRekoli3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkRekoli3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkRekoli3.setName("chkRekoli3"); // NOI18N
        chkRekoli3.setOpaque(false);
        chkRekoli3.setPreferredSize(new java.awt.Dimension(175, 23));
        chkRekoli3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRekoli3ActionPerformed(evt);
            }
        });
        FormInput.add(chkRekoli3);
        chkRekoli3.setBounds(555, 1963, 25, 23);

        chkRekoli4.setBackground(new java.awt.Color(255, 255, 250));
        chkRekoli4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup3.add(chkRekoli4);
        chkRekoli4.setForeground(new java.awt.Color(0, 0, 0));
        chkRekoli4.setBorderPainted(true);
        chkRekoli4.setBorderPaintedFlat(true);
        chkRekoli4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkRekoli4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkRekoli4.setName("chkRekoli4"); // NOI18N
        chkRekoli4.setOpaque(false);
        chkRekoli4.setPreferredSize(new java.awt.Dimension(175, 23));
        chkRekoli4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRekoli4ActionPerformed(evt);
            }
        });
        FormInput.add(chkRekoli4);
        chkRekoli4.setBounds(660, 1963, 25, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("Rekoli Lengan : ");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(0, 1963, 125, 23);

        TnilaiRekoli.setEditable(false);
        TnilaiRekoli.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiRekoli.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiRekoli.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiRekoli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiRekoli.setName("TnilaiRekoli"); // NOI18N
        FormInput.add(TnilaiRekoli);
        TnilaiRekoli.setBounds(870, 1963, 40, 23);

        chkSudut0.setBackground(new java.awt.Color(255, 255, 250));
        chkSudut0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup4.add(chkSudut0);
        chkSudut0.setForeground(new java.awt.Color(0, 0, 0));
        chkSudut0.setBorderPainted(true);
        chkSudut0.setBorderPaintedFlat(true);
        chkSudut0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSudut0.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSudut0.setName("chkSudut0"); // NOI18N
        chkSudut0.setOpaque(false);
        chkSudut0.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSudut0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSudut0ActionPerformed(evt);
            }
        });
        FormInput.add(chkSudut0);
        chkSudut0.setBounds(238, 2029, 25, 23);

        chkSudut1.setBackground(new java.awt.Color(255, 255, 250));
        chkSudut1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup4.add(chkSudut1);
        chkSudut1.setForeground(new java.awt.Color(0, 0, 0));
        chkSudut1.setBorderPainted(true);
        chkSudut1.setBorderPaintedFlat(true);
        chkSudut1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSudut1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSudut1.setName("chkSudut1"); // NOI18N
        chkSudut1.setOpaque(false);
        chkSudut1.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSudut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSudut1ActionPerformed(evt);
            }
        });
        FormInput.add(chkSudut1);
        chkSudut1.setBounds(345, 2029, 25, 23);

        chkSudut2.setBackground(new java.awt.Color(255, 255, 250));
        chkSudut2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup4.add(chkSudut2);
        chkSudut2.setForeground(new java.awt.Color(0, 0, 0));
        chkSudut2.setBorderPainted(true);
        chkSudut2.setBorderPaintedFlat(true);
        chkSudut2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSudut2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSudut2.setName("chkSudut2"); // NOI18N
        chkSudut2.setOpaque(false);
        chkSudut2.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSudut2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSudut2ActionPerformed(evt);
            }
        });
        FormInput.add(chkSudut2);
        chkSudut2.setBounds(450, 2029, 25, 23);

        chkSudut3.setBackground(new java.awt.Color(255, 255, 250));
        chkSudut3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup4.add(chkSudut3);
        chkSudut3.setForeground(new java.awt.Color(0, 0, 0));
        chkSudut3.setBorderPainted(true);
        chkSudut3.setBorderPaintedFlat(true);
        chkSudut3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSudut3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSudut3.setName("chkSudut3"); // NOI18N
        chkSudut3.setOpaque(false);
        chkSudut3.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSudut3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSudut3ActionPerformed(evt);
            }
        });
        FormInput.add(chkSudut3);
        chkSudut3.setBounds(555, 2029, 25, 23);

        chkSudut4.setBackground(new java.awt.Color(255, 255, 250));
        chkSudut4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup4.add(chkSudut4);
        chkSudut4.setForeground(new java.awt.Color(0, 0, 0));
        chkSudut4.setBorderPainted(true);
        chkSudut4.setBorderPaintedFlat(true);
        chkSudut4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSudut4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSudut4.setName("chkSudut4"); // NOI18N
        chkSudut4.setOpaque(false);
        chkSudut4.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSudut4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSudut4ActionPerformed(evt);
            }
        });
        FormInput.add(chkSudut4);
        chkSudut4.setBounds(660, 2029, 25, 23);

        chkSudut5.setBackground(new java.awt.Color(255, 255, 250));
        chkSudut5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup4.add(chkSudut5);
        chkSudut5.setForeground(new java.awt.Color(0, 0, 0));
        chkSudut5.setBorderPainted(true);
        chkSudut5.setBorderPaintedFlat(true);
        chkSudut5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSudut5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSudut5.setName("chkSudut5"); // NOI18N
        chkSudut5.setOpaque(false);
        chkSudut5.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSudut5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSudut5ActionPerformed(evt);
            }
        });
        FormInput.add(chkSudut5);
        chkSudut5.setBounds(765, 2029, 25, 23);

        chkSudut_1.setBackground(new java.awt.Color(255, 255, 250));
        chkSudut_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup4.add(chkSudut_1);
        chkSudut_1.setForeground(new java.awt.Color(0, 0, 0));
        chkSudut_1.setBorderPainted(true);
        chkSudut_1.setBorderPaintedFlat(true);
        chkSudut_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSudut_1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSudut_1.setName("chkSudut_1"); // NOI18N
        chkSudut_1.setOpaque(false);
        chkSudut_1.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSudut_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSudut_1ActionPerformed(evt);
            }
        });
        FormInput.add(chkSudut_1);
        chkSudut_1.setBounds(133, 2029, 25, 23);

        TnilaiSudut.setEditable(false);
        TnilaiSudut.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiSudut.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiSudut.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiSudut.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiSudut.setName("TnilaiSudut"); // NOI18N
        FormInput.add(TnilaiSudut);
        TnilaiSudut.setBounds(870, 2031, 40, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("Sudut Popliteal : ");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(0, 2031, 125, 23);

        PanelWall25.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall25.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/tanda-1.jpg"))); // NOI18N
        PanelWall25.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall25.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall25.setRound(false);
        PanelWall25.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall25.setLayout(null);
        FormInput.add(PanelWall25);
        PanelWall25.setBounds(163, 2078, 60, 60);

        PanelWall26.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall26.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/tanda0.jpg"))); // NOI18N
        PanelWall26.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall26.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall26.setRound(false);
        PanelWall26.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall26.setLayout(null);
        FormInput.add(PanelWall26);
        PanelWall26.setBounds(269, 2078, 60, 60);

        PanelWall27.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall27.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/tanda1.jpg"))); // NOI18N
        PanelWall27.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall27.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall27.setRound(false);
        PanelWall27.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall27.setLayout(null);
        FormInput.add(PanelWall27);
        PanelWall27.setBounds(375, 2078, 60, 60);

        PanelWall28.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall28.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/tanda2.jpg"))); // NOI18N
        PanelWall28.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall28.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall28.setRound(false);
        PanelWall28.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall28.setLayout(null);
        FormInput.add(PanelWall28);
        PanelWall28.setBounds(481, 2078, 60, 60);

        PanelWall29.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall29.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/tanda3.jpg"))); // NOI18N
        PanelWall29.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall29.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall29.setRound(false);
        PanelWall29.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall29.setLayout(null);
        FormInput.add(PanelWall29);
        PanelWall29.setBounds(585, 2078, 60, 60);

        PanelWall30.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall30.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/tanda4.jpg"))); // NOI18N
        PanelWall30.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall30.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall30.setRound(false);
        PanelWall30.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall30.setLayout(null);
        FormInput.add(PanelWall30);
        PanelWall30.setBounds(690, 2078, 60, 60);

        chkTanda_1.setBackground(new java.awt.Color(255, 255, 250));
        chkTanda_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup5.add(chkTanda_1);
        chkTanda_1.setForeground(new java.awt.Color(0, 0, 0));
        chkTanda_1.setBorderPainted(true);
        chkTanda_1.setBorderPaintedFlat(true);
        chkTanda_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkTanda_1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkTanda_1.setName("chkTanda_1"); // NOI18N
        chkTanda_1.setOpaque(false);
        chkTanda_1.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTanda_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTanda_1ActionPerformed(evt);
            }
        });
        FormInput.add(chkTanda_1);
        chkTanda_1.setBounds(133, 2097, 25, 23);

        chkTanda0.setBackground(new java.awt.Color(255, 255, 250));
        chkTanda0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup5.add(chkTanda0);
        chkTanda0.setForeground(new java.awt.Color(0, 0, 0));
        chkTanda0.setBorderPainted(true);
        chkTanda0.setBorderPaintedFlat(true);
        chkTanda0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkTanda0.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkTanda0.setName("chkTanda0"); // NOI18N
        chkTanda0.setOpaque(false);
        chkTanda0.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTanda0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTanda0ActionPerformed(evt);
            }
        });
        FormInput.add(chkTanda0);
        chkTanda0.setBounds(238, 2097, 25, 23);

        chkTanda1.setBackground(new java.awt.Color(255, 255, 250));
        chkTanda1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup5.add(chkTanda1);
        chkTanda1.setForeground(new java.awt.Color(0, 0, 0));
        chkTanda1.setBorderPainted(true);
        chkTanda1.setBorderPaintedFlat(true);
        chkTanda1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkTanda1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkTanda1.setName("chkTanda1"); // NOI18N
        chkTanda1.setOpaque(false);
        chkTanda1.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTanda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTanda1ActionPerformed(evt);
            }
        });
        FormInput.add(chkTanda1);
        chkTanda1.setBounds(345, 2097, 25, 23);

        chkTanda2.setBackground(new java.awt.Color(255, 255, 250));
        chkTanda2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup5.add(chkTanda2);
        chkTanda2.setForeground(new java.awt.Color(0, 0, 0));
        chkTanda2.setBorderPainted(true);
        chkTanda2.setBorderPaintedFlat(true);
        chkTanda2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkTanda2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkTanda2.setName("chkTanda2"); // NOI18N
        chkTanda2.setOpaque(false);
        chkTanda2.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTanda2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTanda2ActionPerformed(evt);
            }
        });
        FormInput.add(chkTanda2);
        chkTanda2.setBounds(450, 2097, 25, 23);

        chkTanda3.setBackground(new java.awt.Color(255, 255, 250));
        chkTanda3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup5.add(chkTanda3);
        chkTanda3.setForeground(new java.awt.Color(0, 0, 0));
        chkTanda3.setBorderPainted(true);
        chkTanda3.setBorderPaintedFlat(true);
        chkTanda3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkTanda3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkTanda3.setName("chkTanda3"); // NOI18N
        chkTanda3.setOpaque(false);
        chkTanda3.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTanda3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTanda3ActionPerformed(evt);
            }
        });
        FormInput.add(chkTanda3);
        chkTanda3.setBounds(555, 2097, 25, 23);

        chkTanda4.setBackground(new java.awt.Color(255, 255, 250));
        chkTanda4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup5.add(chkTanda4);
        chkTanda4.setForeground(new java.awt.Color(0, 0, 0));
        chkTanda4.setBorderPainted(true);
        chkTanda4.setBorderPaintedFlat(true);
        chkTanda4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkTanda4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkTanda4.setName("chkTanda4"); // NOI18N
        chkTanda4.setOpaque(false);
        chkTanda4.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTanda4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTanda4ActionPerformed(evt);
            }
        });
        FormInput.add(chkTanda4);
        chkTanda4.setBounds(660, 2097, 25, 23);

        TnilaiTanda.setEditable(false);
        TnilaiTanda.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiTanda.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiTanda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiTanda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiTanda.setName("TnilaiTanda"); // NOI18N
        FormInput.add(TnilaiTanda);
        TnilaiTanda.setBounds(870, 2099, 40, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("Tanda Selempang : ");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(0, 2099, 125, 23);

        PanelWall31.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall31.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/tumit-1.jpg"))); // NOI18N
        PanelWall31.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall31.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall31.setRound(false);
        PanelWall31.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall31.setLayout(null);
        FormInput.add(PanelWall31);
        PanelWall31.setBounds(163, 2146, 60, 60);

        PanelWall32.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall32.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/tumit0.jpg"))); // NOI18N
        PanelWall32.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall32.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall32.setRound(false);
        PanelWall32.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall32.setLayout(null);
        FormInput.add(PanelWall32);
        PanelWall32.setBounds(269, 2146, 60, 60);

        PanelWall33.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall33.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/tumit1.jpg"))); // NOI18N
        PanelWall33.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall33.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall33.setRound(false);
        PanelWall33.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall33.setLayout(null);
        FormInput.add(PanelWall33);
        PanelWall33.setBounds(375, 2146, 60, 60);

        PanelWall34.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall34.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/tumit2.jpg"))); // NOI18N
        PanelWall34.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall34.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall34.setRound(false);
        PanelWall34.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall34.setLayout(null);
        FormInput.add(PanelWall34);
        PanelWall34.setBounds(481, 2146, 60, 60);

        PanelWall35.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall35.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/tumit3.jpg"))); // NOI18N
        PanelWall35.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall35.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall35.setRound(false);
        PanelWall35.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall35.setLayout(null);
        FormInput.add(PanelWall35);
        PanelWall35.setBounds(585, 2146, 60, 60);

        PanelWall36.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall36.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/tumit4.jpg"))); // NOI18N
        PanelWall36.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall36.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall36.setRound(false);
        PanelWall36.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall36.setLayout(null);
        FormInput.add(PanelWall36);
        PanelWall36.setBounds(690, 2146, 60, 60);

        chkTumit_1.setBackground(new java.awt.Color(255, 255, 250));
        chkTumit_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup6.add(chkTumit_1);
        chkTumit_1.setForeground(new java.awt.Color(0, 0, 0));
        chkTumit_1.setBorderPainted(true);
        chkTumit_1.setBorderPaintedFlat(true);
        chkTumit_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkTumit_1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkTumit_1.setName("chkTumit_1"); // NOI18N
        chkTumit_1.setOpaque(false);
        chkTumit_1.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTumit_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTumit_1ActionPerformed(evt);
            }
        });
        FormInput.add(chkTumit_1);
        chkTumit_1.setBounds(133, 2165, 25, 23);

        chkTumit0.setBackground(new java.awt.Color(255, 255, 250));
        chkTumit0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup6.add(chkTumit0);
        chkTumit0.setForeground(new java.awt.Color(0, 0, 0));
        chkTumit0.setBorderPainted(true);
        chkTumit0.setBorderPaintedFlat(true);
        chkTumit0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkTumit0.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkTumit0.setName("chkTumit0"); // NOI18N
        chkTumit0.setOpaque(false);
        chkTumit0.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTumit0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTumit0ActionPerformed(evt);
            }
        });
        FormInput.add(chkTumit0);
        chkTumit0.setBounds(238, 2165, 25, 23);

        chkTumit1.setBackground(new java.awt.Color(255, 255, 250));
        chkTumit1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup6.add(chkTumit1);
        chkTumit1.setForeground(new java.awt.Color(0, 0, 0));
        chkTumit1.setBorderPainted(true);
        chkTumit1.setBorderPaintedFlat(true);
        chkTumit1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkTumit1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkTumit1.setName("chkTumit1"); // NOI18N
        chkTumit1.setOpaque(false);
        chkTumit1.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTumit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTumit1ActionPerformed(evt);
            }
        });
        FormInput.add(chkTumit1);
        chkTumit1.setBounds(345, 2165, 25, 23);

        chkTumit2.setBackground(new java.awt.Color(255, 255, 250));
        chkTumit2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup6.add(chkTumit2);
        chkTumit2.setForeground(new java.awt.Color(0, 0, 0));
        chkTumit2.setBorderPainted(true);
        chkTumit2.setBorderPaintedFlat(true);
        chkTumit2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkTumit2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkTumit2.setName("chkTumit2"); // NOI18N
        chkTumit2.setOpaque(false);
        chkTumit2.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTumit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTumit2ActionPerformed(evt);
            }
        });
        FormInput.add(chkTumit2);
        chkTumit2.setBounds(450, 2165, 25, 23);

        chkTumit3.setBackground(new java.awt.Color(255, 255, 250));
        chkTumit3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup6.add(chkTumit3);
        chkTumit3.setForeground(new java.awt.Color(0, 0, 0));
        chkTumit3.setBorderPainted(true);
        chkTumit3.setBorderPaintedFlat(true);
        chkTumit3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkTumit3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkTumit3.setName("chkTumit3"); // NOI18N
        chkTumit3.setOpaque(false);
        chkTumit3.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTumit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTumit3ActionPerformed(evt);
            }
        });
        FormInput.add(chkTumit3);
        chkTumit3.setBounds(555, 2165, 25, 23);

        chkTumit4.setBackground(new java.awt.Color(255, 255, 250));
        chkTumit4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup6.add(chkTumit4);
        chkTumit4.setForeground(new java.awt.Color(0, 0, 0));
        chkTumit4.setBorderPainted(true);
        chkTumit4.setBorderPaintedFlat(true);
        chkTumit4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkTumit4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkTumit4.setName("chkTumit4"); // NOI18N
        chkTumit4.setOpaque(false);
        chkTumit4.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTumit4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTumit4ActionPerformed(evt);
            }
        });
        FormInput.add(chkTumit4);
        chkTumit4.setBounds(660, 2165, 25, 23);

        TnilaiTumit.setEditable(false);
        TnilaiTumit.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiTumit.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiTumit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiTumit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiTumit.setName("TnilaiTumit"); // NOI18N
        FormInput.add(TnilaiTumit);
        TnilaiTumit.setBounds(870, 2167, 40, 23);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setText("Tumit Ke Kuping : ");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(0, 2167, 125, 23);

        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setText("Total Nilai : ");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(800, 2195, 68, 23);

        TtotNilaiNeomuskular.setEditable(false);
        TtotNilaiNeomuskular.setBackground(new java.awt.Color(245, 250, 240));
        TtotNilaiNeomuskular.setForeground(new java.awt.Color(0, 0, 0));
        TtotNilaiNeomuskular.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TtotNilaiNeomuskular.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TtotNilaiNeomuskular.setName("TtotNilaiNeomuskular"); // NOI18N
        FormInput.add(TtotNilaiNeomuskular);
        TtotNilaiNeomuskular.setBounds(870, 2195, 40, 23);

        jSeparator7.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator7.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput.add(jSeparator7);
        jSeparator7.setBounds(30, 1813, 880, 1);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(30, 1877, 880, 1);

        jSeparator9.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator9.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(30, 1943, 880, 1);

        jSeparator10.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator10.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(30, 2007, 880, 1);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(30, 2075, 880, 1);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(30, 2143, 880, 1);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(30, 2210, 767, 1);

        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel127.setText("Nilai");
        jLabel127.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(870, 1790, 40, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("9. MATURITAS FISIK");
        jLabel106.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(0, 2223, 160, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("Kulit : ");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(0, 2251, 162, 23);

        cmbFisikKulit.setForeground(new java.awt.Color(0, 0, 0));
        cmbFisikKulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Lengket-friabel-transparan", "Gelatinus merah translusen", "Merah halus, tampak gambaran vena", "Permukaan terkelupas &/ ruam tampak bb vena", "Pecah2 daerah gundul, vena sangat sedikit", "Parchment terberal dlm, tak terlihat vena", "Leathery cracked wrinkled" }));
        cmbFisikKulit.setName("cmbFisikKulit"); // NOI18N
        cmbFisikKulit.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFisikKulit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFisikKulitActionPerformed(evt);
            }
        });
        FormInput.add(cmbFisikKulit);
        cmbFisikKulit.setBounds(165, 2251, 260, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("Payudara : ");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(0, 2279, 162, 23);

        cmbFisikPayudara.setForeground(new java.awt.Color(0, 0, 0));
        cmbFisikPayudara.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Impercep-tible", "Sedikit perceptible", "Aerola rata, tanpa bantalan", "Aerola agak menonjol, bantalan 1-2 mm", "Aerola menonjol, bantalan 3-4mm", "Aerola sangat menonjol, bantalan 5-10 mm" }));
        cmbFisikPayudara.setName("cmbFisikPayudara"); // NOI18N
        cmbFisikPayudara.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFisikPayudara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFisikPayudaraActionPerformed(evt);
            }
        });
        FormInput.add(cmbFisikPayudara);
        cmbFisikPayudara.setBounds(165, 2279, 240, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("Mata / Telinga : ");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(0, 2307, 162, 23);

        cmbFisikMata.setForeground(new java.awt.Color(0, 0, 0));
        cmbFisikMata.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kerapatan kelopak; longgar : -1 rapat : -2", "Kelopak terbuka daun telinga rata, ttp terlipat", "Sedikit melengkung, lunak, rekoil lambat", "Lengkung, terbentuk baik, lunak, tapi rekoil baik", "Bentuk & kekerasan sudah baik, rekoil langsung", "TI rawan cukup tebal, daun telinga sudah kaku" }));
        cmbFisikMata.setName("cmbFisikMata"); // NOI18N
        cmbFisikMata.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFisikMata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFisikMataActionPerformed(evt);
            }
        });
        FormInput.add(cmbFisikMata);
        cmbFisikMata.setBounds(165, 2307, 260, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("Genital (Pria) : ");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(0, 2335, 162, 23);

        cmbFisikGenPria.setForeground(new java.awt.Color(0, 0, 0));
        cmbFisikGenPria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Skrotum rata, halus", "Skrotum kosong, guratan kulit halus", "Testis dibagian atas kanal, guratan kulit jarang", "Testis sudah turun, terlihat guratan cukup jelas", "Testis jelas dalam skrotum, juga cukup jelas", "Testis sudah bergelayut, ruga cukup dalam" }));
        cmbFisikGenPria.setName("cmbFisikGenPria"); // NOI18N
        cmbFisikGenPria.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFisikGenPria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFisikGenPriaActionPerformed(evt);
            }
        });
        FormInput.add(cmbFisikGenPria);
        cmbFisikGenPria.setBounds(165, 2335, 260, 23);

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setText("Genital (Wanita) : ");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(0, 2363, 162, 23);

        cmbFisikGenWanita.setForeground(new java.awt.Color(0, 0, 0));
        cmbFisikGenWanita.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Klitoris menonjol, labia rata", "Klitoris menonjol, labia minor kecil", "Klitoris menonjol, labia minor membesar", "Labia minor dan mayor sama menonjol", "Labia mayor besar, labia minor kecil", "Labia mayor menutup klitoris & labia minor" }));
        cmbFisikGenWanita.setName("cmbFisikGenWanita"); // NOI18N
        cmbFisikGenWanita.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFisikGenWanita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFisikGenWanitaActionPerformed(evt);
            }
        });
        FormInput.add(cmbFisikGenWanita);
        cmbFisikGenWanita.setBounds(165, 2363, 235, 23);

        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setText("Lanugo : ");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(0, 2391, 162, 23);

        cmbFisikLanugo.setForeground(new java.awt.Color(0, 0, 0));
        cmbFisikLanugo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak ada", "Jarang", "Banyak", "Tipis", "Terdapat daerah tidak berambut", "Banyak daerah yang tidak berambut" }));
        cmbFisikLanugo.setName("cmbFisikLanugo"); // NOI18N
        cmbFisikLanugo.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFisikLanugo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFisikLanugoActionPerformed(evt);
            }
        });
        FormInput.add(cmbFisikLanugo);
        cmbFisikLanugo.setBounds(165, 2391, 210, 23);

        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setText("Plantar : ");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(0, 2419, 162, 23);

        cmbFisikPlantar.setForeground(new java.awt.Color(0, 0, 0));
        cmbFisikPlantar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tumit, jari kaki -1: 40-50 mm -2 : <40 mm", "> 50 mm tidak ada lipatan", "Tanda merah pudar", "Hanya terdapat lipatan anterior transversal", "Lipatan anterior 2/3", "Lipatan hampir pada seluruh telapak" }));
        cmbFisikPlantar.setName("cmbFisikPlantar"); // NOI18N
        cmbFisikPlantar.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFisikPlantar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFisikPlantarActionPerformed(evt);
            }
        });
        FormInput.add(cmbFisikPlantar);
        cmbFisikPlantar.setBounds(165, 2419, 245, 23);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setText("Nilai : ");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(432, 2251, 40, 23);

        TnilaiKulit.setEditable(false);
        TnilaiKulit.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiKulit.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiKulit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiKulit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiKulit.setName("TnilaiKulit"); // NOI18N
        FormInput.add(TnilaiKulit);
        TnilaiKulit.setBounds(474, 2251, 40, 23);

        jLabel131.setForeground(new java.awt.Color(0, 0, 0));
        jLabel131.setText("Nilai : ");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(432, 2279, 40, 23);

        TnilaiPayudara.setEditable(false);
        TnilaiPayudara.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiPayudara.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiPayudara.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiPayudara.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiPayudara.setName("TnilaiPayudara"); // NOI18N
        FormInput.add(TnilaiPayudara);
        TnilaiPayudara.setBounds(474, 2279, 40, 23);

        jLabel132.setForeground(new java.awt.Color(0, 0, 0));
        jLabel132.setText("Nilai : ");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(432, 2307, 40, 23);

        TnilaiMata.setEditable(false);
        TnilaiMata.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiMata.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiMata.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiMata.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiMata.setName("TnilaiMata"); // NOI18N
        FormInput.add(TnilaiMata);
        TnilaiMata.setBounds(474, 2307, 40, 23);

        jLabel133.setForeground(new java.awt.Color(0, 0, 0));
        jLabel133.setText("Nilai : ");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(432, 2335, 40, 23);

        TnilaiGenPria.setEditable(false);
        TnilaiGenPria.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiGenPria.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiGenPria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiGenPria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiGenPria.setName("TnilaiGenPria"); // NOI18N
        FormInput.add(TnilaiGenPria);
        TnilaiGenPria.setBounds(474, 2335, 40, 23);

        TnilaiGenWanita.setEditable(false);
        TnilaiGenWanita.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiGenWanita.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiGenWanita.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiGenWanita.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiGenWanita.setName("TnilaiGenWanita"); // NOI18N
        FormInput.add(TnilaiGenWanita);
        TnilaiGenWanita.setBounds(474, 2363, 40, 23);

        jLabel134.setForeground(new java.awt.Color(0, 0, 0));
        jLabel134.setText("Nilai : ");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(432, 2363, 40, 23);

        jLabel135.setForeground(new java.awt.Color(0, 0, 0));
        jLabel135.setText("Nilai : ");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(432, 2391, 40, 23);

        TnilaiLanugo.setEditable(false);
        TnilaiLanugo.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiLanugo.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiLanugo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiLanugo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiLanugo.setName("TnilaiLanugo"); // NOI18N
        FormInput.add(TnilaiLanugo);
        TnilaiLanugo.setBounds(474, 2391, 40, 23);

        jLabel136.setForeground(new java.awt.Color(0, 0, 0));
        jLabel136.setText("Nilai : ");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(432, 2419, 40, 23);

        TnilaiPlantar.setEditable(false);
        TnilaiPlantar.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiPlantar.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiPlantar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiPlantar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiPlantar.setName("TnilaiPlantar"); // NOI18N
        FormInput.add(TnilaiPlantar);
        TnilaiPlantar.setBounds(474, 2419, 40, 23);

        jLabel137.setForeground(new java.awt.Color(0, 0, 0));
        jLabel137.setText("Total Nilai : ");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(520, 2419, 70, 23);

        TtotNilaiFisik.setEditable(false);
        TtotNilaiFisik.setBackground(new java.awt.Color(245, 250, 240));
        TtotNilaiFisik.setForeground(new java.awt.Color(0, 0, 0));
        TtotNilaiFisik.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TtotNilaiFisik.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TtotNilaiFisik.setName("TtotNilaiFisik"); // NOI18N
        FormInput.add(TtotNilaiFisik);
        TtotNilaiFisik.setBounds(593, 2419, 50, 23);

        jLabel138.setForeground(new java.awt.Color(0, 0, 0));
        jLabel138.setText("Skor : Neomuskular + Fisik");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(520, 2251, 139, 23);

        jLabel139.setForeground(new java.awt.Color(0, 0, 0));
        jLabel139.setText("Skor :");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(520, 2279, 40, 23);

        TnilaiNeo.setEditable(false);
        TnilaiNeo.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiNeo.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiNeo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiNeo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiNeo.setName("TnilaiNeo"); // NOI18N
        FormInput.add(TnilaiNeo);
        TnilaiNeo.setBounds(563, 2279, 40, 23);

        jLabel140.setForeground(new java.awt.Color(0, 0, 0));
        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel140.setText("+");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(605, 2279, 20, 23);

        TnilaiFisik.setEditable(false);
        TnilaiFisik.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiFisik.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiFisik.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiFisik.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiFisik.setName("TnilaiFisik"); // NOI18N
        FormInput.add(TnilaiFisik);
        TnilaiFisik.setBounds(626, 2279, 40, 23);

        jLabel141.setForeground(new java.awt.Color(0, 0, 0));
        jLabel141.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel141.setText("=");
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(666, 2279, 20, 23);

        TnilaiSkor.setEditable(false);
        TnilaiSkor.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiSkor.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiSkor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiSkor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiSkor.setName("TnilaiSkor"); // NOI18N
        FormInput.add(TnilaiSkor);
        TnilaiSkor.setBounds(688, 2279, 40, 23);

        jLabel142.setForeground(new java.awt.Color(0, 0, 0));
        jLabel142.setText("Kesimpulan :");
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput.add(jLabel142);
        jLabel142.setBounds(520, 2307, 73, 23);

        TkesimpulanSkor.setEditable(false);
        TkesimpulanSkor.setBackground(new java.awt.Color(245, 250, 240));
        TkesimpulanSkor.setForeground(new java.awt.Color(0, 0, 0));
        TkesimpulanSkor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TkesimpulanSkor.setName("TkesimpulanSkor"); // NOI18N
        FormInput.add(TkesimpulanSkor);
        TkesimpulanSkor.setBounds(597, 2307, 200, 23);

        jLabel143.setForeground(new java.awt.Color(0, 0, 0));
        jLabel143.setText("10. DAFTAR MASALAH KEPERAWATAN");
        jLabel143.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel143.setName("jLabel143"); // NOI18N
        FormInput.add(jLabel143);
        jLabel143.setBounds(0, 2447, 240, 23);

        chkHipotermi.setBackground(new java.awt.Color(255, 255, 250));
        chkHipotermi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHipotermi.setForeground(new java.awt.Color(0, 0, 0));
        chkHipotermi.setText("Hipotermi");
        chkHipotermi.setBorderPainted(true);
        chkHipotermi.setBorderPaintedFlat(true);
        chkHipotermi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHipotermi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHipotermi.setName("chkHipotermi"); // NOI18N
        chkHipotermi.setOpaque(false);
        chkHipotermi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHipotermi);
        chkHipotermi.setBounds(55, 2475, 210, 23);

        chkResikoHipotermi.setBackground(new java.awt.Color(255, 255, 250));
        chkResikoHipotermi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkResikoHipotermi.setForeground(new java.awt.Color(0, 0, 0));
        chkResikoHipotermi.setText("Resiko Hipotermi");
        chkResikoHipotermi.setBorderPainted(true);
        chkResikoHipotermi.setBorderPaintedFlat(true);
        chkResikoHipotermi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkResikoHipotermi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkResikoHipotermi.setName("chkResikoHipotermi"); // NOI18N
        chkResikoHipotermi.setOpaque(false);
        chkResikoHipotermi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkResikoHipotermi);
        chkResikoHipotermi.setBounds(55, 2503, 210, 23);

        chkHipertermi.setBackground(new java.awt.Color(255, 255, 250));
        chkHipertermi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHipertermi.setForeground(new java.awt.Color(0, 0, 0));
        chkHipertermi.setText("Hipertermi");
        chkHipertermi.setBorderPainted(true);
        chkHipertermi.setBorderPaintedFlat(true);
        chkHipertermi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHipertermi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHipertermi.setName("chkHipertermi"); // NOI18N
        chkHipertermi.setOpaque(false);
        chkHipertermi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHipertermi);
        chkHipertermi.setBounds(55, 2531, 210, 23);

        chkPolaNafas.setBackground(new java.awt.Color(255, 255, 250));
        chkPolaNafas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPolaNafas.setForeground(new java.awt.Color(0, 0, 0));
        chkPolaNafas.setText("Pola Nafas Tidak Efektif");
        chkPolaNafas.setBorderPainted(true);
        chkPolaNafas.setBorderPaintedFlat(true);
        chkPolaNafas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPolaNafas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPolaNafas.setName("chkPolaNafas"); // NOI18N
        chkPolaNafas.setOpaque(false);
        chkPolaNafas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPolaNafas);
        chkPolaNafas.setBounds(55, 2559, 210, 23);

        chkNyeri.setBackground(new java.awt.Color(255, 255, 250));
        chkNyeri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNyeri.setForeground(new java.awt.Color(0, 0, 0));
        chkNyeri.setText("Nyeri");
        chkNyeri.setBorderPainted(true);
        chkNyeri.setBorderPaintedFlat(true);
        chkNyeri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNyeri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNyeri.setName("chkNyeri"); // NOI18N
        chkNyeri.setOpaque(false);
        chkNyeri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNyeri);
        chkNyeri.setBounds(55, 2587, 210, 23);

        chkKerusakan.setBackground(new java.awt.Color(255, 255, 250));
        chkKerusakan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKerusakan.setForeground(new java.awt.Color(0, 0, 0));
        chkKerusakan.setText("Kerusakan Integritas Kulit/Jaringan");
        chkKerusakan.setBorderPainted(true);
        chkKerusakan.setBorderPaintedFlat(true);
        chkKerusakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKerusakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKerusakan.setName("chkKerusakan"); // NOI18N
        chkKerusakan.setOpaque(false);
        chkKerusakan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKerusakan);
        chkKerusakan.setBounds(55, 2615, 210, 23);

        chkResikoKerusakan.setBackground(new java.awt.Color(255, 255, 250));
        chkResikoKerusakan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkResikoKerusakan.setForeground(new java.awt.Color(0, 0, 0));
        chkResikoKerusakan.setText("Resiko Kerusakan Integritas Jaringan");
        chkResikoKerusakan.setBorderPainted(true);
        chkResikoKerusakan.setBorderPaintedFlat(true);
        chkResikoKerusakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkResikoKerusakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkResikoKerusakan.setName("chkResikoKerusakan"); // NOI18N
        chkResikoKerusakan.setOpaque(false);
        chkResikoKerusakan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkResikoKerusakan);
        chkResikoKerusakan.setBounds(55, 2643, 210, 23);

        chkKebutuhan.setBackground(new java.awt.Color(255, 255, 250));
        chkKebutuhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKebutuhan.setForeground(new java.awt.Color(0, 0, 0));
        chkKebutuhan.setText("Kebutuhan Cairan Tidak Terpenuhi");
        chkKebutuhan.setBorderPainted(true);
        chkKebutuhan.setBorderPaintedFlat(true);
        chkKebutuhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKebutuhan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKebutuhan.setName("chkKebutuhan"); // NOI18N
        chkKebutuhan.setOpaque(false);
        chkKebutuhan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKebutuhan);
        chkKebutuhan.setBounds(55, 2671, 210, 23);

        chkIkterik.setBackground(new java.awt.Color(255, 255, 250));
        chkIkterik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIkterik.setForeground(new java.awt.Color(0, 0, 0));
        chkIkterik.setText("Ikterik");
        chkIkterik.setBorderPainted(true);
        chkIkterik.setBorderPaintedFlat(true);
        chkIkterik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIkterik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIkterik.setName("chkIkterik"); // NOI18N
        chkIkterik.setOpaque(false);
        chkIkterik.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIkterik);
        chkIkterik.setBounds(290, 2475, 210, 23);

        chkGangguan.setBackground(new java.awt.Color(255, 255, 250));
        chkGangguan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGangguan.setForeground(new java.awt.Color(0, 0, 0));
        chkGangguan.setText("Gangguan Motilitas Usus");
        chkGangguan.setBorderPainted(true);
        chkGangguan.setBorderPaintedFlat(true);
        chkGangguan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGangguan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGangguan.setName("chkGangguan"); // NOI18N
        chkGangguan.setOpaque(false);
        chkGangguan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGangguan);
        chkGangguan.setBounds(290, 2503, 210, 23);

        chkBersihan.setBackground(new java.awt.Color(255, 255, 250));
        chkBersihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBersihan.setForeground(new java.awt.Color(0, 0, 0));
        chkBersihan.setText("Bersihan Jalan Nafas Tidak Efektif");
        chkBersihan.setBorderPainted(true);
        chkBersihan.setBorderPaintedFlat(true);
        chkBersihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBersihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBersihan.setName("chkBersihan"); // NOI18N
        chkBersihan.setOpaque(false);
        chkBersihan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBersihan);
        chkBersihan.setBounds(290, 2531, 210, 23);

        chkResikoBersihan.setBackground(new java.awt.Color(255, 255, 250));
        chkResikoBersihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkResikoBersihan.setForeground(new java.awt.Color(0, 0, 0));
        chkResikoBersihan.setText("Resiko Bersihan Nafas Tidak Efektif");
        chkResikoBersihan.setBorderPainted(true);
        chkResikoBersihan.setBorderPaintedFlat(true);
        chkResikoBersihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkResikoBersihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkResikoBersihan.setName("chkResikoBersihan"); // NOI18N
        chkResikoBersihan.setOpaque(false);
        chkResikoBersihan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkResikoBersihan);
        chkResikoBersihan.setBounds(290, 2559, 210, 23);

        chkPerubahan.setBackground(new java.awt.Color(255, 255, 250));
        chkPerubahan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerubahan.setForeground(new java.awt.Color(0, 0, 0));
        chkPerubahan.setText("Perubahan Perfusi Jaringan Tidak Efektif");
        chkPerubahan.setBorderPainted(true);
        chkPerubahan.setBorderPaintedFlat(true);
        chkPerubahan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerubahan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerubahan.setName("chkPerubahan"); // NOI18N
        chkPerubahan.setOpaque(false);
        chkPerubahan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerubahan);
        chkPerubahan.setBounds(290, 2587, 230, 23);

        chkKelebihan.setBackground(new java.awt.Color(255, 255, 250));
        chkKelebihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKelebihan.setForeground(new java.awt.Color(0, 0, 0));
        chkKelebihan.setText("Kelebihan Volume Jaringan");
        chkKelebihan.setBorderPainted(true);
        chkKelebihan.setBorderPaintedFlat(true);
        chkKelebihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKelebihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKelebihan.setName("chkKelebihan"); // NOI18N
        chkKelebihan.setOpaque(false);
        chkKelebihan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKelebihan);
        chkKelebihan.setBounds(290, 2615, 210, 23);

        chkResikoKelebihan.setBackground(new java.awt.Color(255, 255, 250));
        chkResikoKelebihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkResikoKelebihan.setForeground(new java.awt.Color(0, 0, 0));
        chkResikoKelebihan.setText("Resiko Kelebihan Volume Cairan");
        chkResikoKelebihan.setBorderPainted(true);
        chkResikoKelebihan.setBorderPaintedFlat(true);
        chkResikoKelebihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkResikoKelebihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkResikoKelebihan.setName("chkResikoKelebihan"); // NOI18N
        chkResikoKelebihan.setOpaque(false);
        chkResikoKelebihan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkResikoKelebihan);
        chkResikoKelebihan.setBounds(290, 2643, 210, 23);

        chkResikoKebutuhan.setBackground(new java.awt.Color(255, 255, 250));
        chkResikoKebutuhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkResikoKebutuhan.setForeground(new java.awt.Color(0, 0, 0));
        chkResikoKebutuhan.setText("Resiko Kebutuhan Cairan Tidak Terpenuhi");
        chkResikoKebutuhan.setBorderPainted(true);
        chkResikoKebutuhan.setBorderPaintedFlat(true);
        chkResikoKebutuhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkResikoKebutuhan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkResikoKebutuhan.setName("chkResikoKebutuhan"); // NOI18N
        chkResikoKebutuhan.setOpaque(false);
        chkResikoKebutuhan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkResikoKebutuhan);
        chkResikoKebutuhan.setBounds(290, 2671, 240, 23);

        jLabel144.setForeground(new java.awt.Color(0, 0, 0));
        jLabel144.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel144.setText("Masalah Keperawatan Lainnya :");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput.add(jLabel144);
        jLabel144.setBounds(545, 2475, 170, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        TmasalahLain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TmasalahLain.setColumns(20);
        TmasalahLain.setRows(5);
        TmasalahLain.setName("TmasalahLain"); // NOI18N
        TmasalahLain.setPreferredSize(new java.awt.Dimension(162, 2000));
        TmasalahLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmasalahLainKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(TmasalahLain);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(545, 2503, 370, 190);

        jLabel145.setForeground(new java.awt.Color(0, 0, 0));
        jLabel145.setText("Disusun Rencana Keperawatan :");
        jLabel145.setName("jLabel145"); // NOI18N
        FormInput.add(jLabel145);
        jLabel145.setBounds(0, 2699, 200, 23);

        cmbJenkel.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenkel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-laki", "Perempuan" }));
        cmbJenkel.setName("cmbJenkel"); // NOI18N
        cmbJenkel.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbJenkel);
        cmbJenkel.setBounds(635, 430, 90, 23);

        chkTgllahirIbu.setBackground(new java.awt.Color(255, 255, 250));
        chkTgllahirIbu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTgllahirIbu.setForeground(new java.awt.Color(0, 0, 0));
        chkTgllahirIbu.setText("Tgl. Lahir : ");
        chkTgllahirIbu.setBorderPainted(true);
        chkTgllahirIbu.setBorderPaintedFlat(true);
        chkTgllahirIbu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTgllahirIbu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTgllahirIbu.setName("chkTgllahirIbu"); // NOI18N
        chkTgllahirIbu.setOpaque(false);
        chkTgllahirIbu.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTgllahirIbu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTgllahirIbuActionPerformed(evt);
            }
        });
        FormInput.add(chkTgllahirIbu);
        chkTgllahirIbu.setBounds(22, 682, 90, 23);

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

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-02-2025" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-02-2025" }));
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
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 182, new String[]{
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
                        resikoKerusakan, kebutuhan, ikterik, gangguan, bersihan, resikoBersihan, perubahan, kelebihan, resikoKelebihan, resikoKebutuhan, TmasalahLain.getText(), 
                        Valid.SetTgl(TtglRencana1.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), 
                        Valid.SetTgl(TtglRencana2.getSelectedItem() + ""), cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), nip, 
                        Sequel.cariIsi("select now()"), cekTglLahir
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
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (tbAsesmen.getSelectedRow() > -1) {
                if (akses.getadmin() == true) {
                    ganti();
                } else {
                    if (nip.equals(akses.getkode())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh perawat yang bernama " + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString() + " ..!!");
                    }
                }
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

            if (chkHipertensi.isSelected() == true) {
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
            
            if (TmasalahLain.getText().equals("")) {
                param.put("masalahKep", "-\n");
            } else {
                param.put("masalahKep", TmasalahLain.getText() + "\n");
            }
            
            param.put("tglRen1", Valid.SetTglINDONESIA(Valid.SetTgl(TtglRencana1.getSelectedItem() + "")));
            param.put("tglRen2", Valid.SetTglINDONESIA(Valid.SetTgl(TtglRencana2.getSelectedItem() + "")));
            param.put("jamRen1", cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " Wita");
            param.put("jamRen2", cmbJam2.getSelectedItem().toString() + ":" + cmbMnt2.getSelectedItem().toString() + " Wita");            
            param.put("petugas", TnmPerawat.getText());
            
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

    private void cmbJam2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam2MouseReleased
        AutoCompleteDecorator.decorate(cmbJam2);
    }//GEN-LAST:event_cmbJam2MouseReleased

    private void cmbMnt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt2MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt2);
    }//GEN-LAST:event_cmbMnt2MouseReleased

    private void cmbDtk2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk2);
    }//GEN-LAST:event_cmbDtk2MouseReleased

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

    private void cmbSumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSumberActionPerformed
        TlainSumber.setText("");
        if (cmbSumber.getSelectedIndex() == 3) {
            TlainSumber.setEnabled(true);
            TlainSumber.requestFocus();
        } else {
            TlainSumber.setEnabled(false);
        }
    }//GEN-LAST:event_cmbSumberActionPerformed

    private void TlainSumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainSumberKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbRujukan.requestFocus();
        }
    }//GEN-LAST:event_TlainSumberKeyPressed

    private void cmbRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRujukanActionPerformed
        cmbJnsRujukan.setSelectedIndex(0);
        if (cmbRujukan.getSelectedIndex() == 1) {
            cmbJnsRujukan.setEnabled(true);
            cmbJnsRujukan.requestFocus();
        } else {
            cmbJnsRujukan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRujukanActionPerformed

    private void TdiagnosaRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaRujukanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkeluhan.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaRujukanKeyPressed

    private void TnmIdentitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmIdentitasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPendidikan.requestFocus();
        }
    }//GEN-LAST:event_TnmIdentitasKeyPressed

    private void TkeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeluhanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmIdentitas.requestFocus();
        }
    }//GEN-LAST:event_TkeluhanKeyPressed

    private void TpekerjaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpekerjaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbAgama.requestFocus();
        }
    }//GEN-LAST:event_TpekerjaanKeyPressed

    private void TalamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalamatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tbbl.requestFocus();
        }
    }//GEN-LAST:event_TalamatKeyPressed

    private void TbblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbblKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpb.requestFocus();
        }
    }//GEN-LAST:event_TbblKeyPressed

    private void TpbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlk.requestFocus();
        }
    }//GEN-LAST:event_TpbKeyPressed

    private void TlkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlkKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tld.requestFocus();
        }
    }//GEN-LAST:event_TlkKeyPressed

    private void TldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlp.requestFocus();
        }
    }//GEN-LAST:event_TldKeyPressed

    private void TlpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tll.requestFocus();
        }
    }//GEN-LAST:event_TlpKeyPressed

    private void TllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkk.requestFocus();
        }
    }//GEN-LAST:event_TllKeyPressed

    private void TkkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkkKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnadi.requestFocus();
        }
    }//GEN-LAST:event_TkkKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trr.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsuhu.requestFocus();
        }
    }//GEN-LAST:event_TrrKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkJernih.requestFocus();
        }
    }//GEN-LAST:event_TsuhuKeyPressed

    private void TkelainanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkelainanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TanakKe.requestFocus();
        }
    }//GEN-LAST:event_TkelainanKeyPressed

    private void TanakKeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanakKeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TumurHamil.requestFocus();
        }
    }//GEN-LAST:event_TanakKeKeyPressed

    private void TumurHamilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurHamilKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbRiwPenyakitIbu.requestFocus();
        }
    }//GEN-LAST:event_TumurHamilKeyPressed

    private void TlainRiwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainRiwayatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbMasih.requestFocus();
        }
    }//GEN-LAST:event_TlainRiwayatKeyPressed

    private void cmbRiwPenyakitIbuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwPenyakitIbuActionPerformed
        chkHipertensi.setSelected(false);
        chkDM.setSelected(false);
        chkPMS.setSelected(false);
        chkTBC.setSelected(false);
        chkAsma.setSelected(false);
        chkHepB.setSelected(false);
        chkLainyaRiwayat.setSelected(false);
        TlainRiwayat.setEnabled(false);
        
        if (cmbRiwPenyakitIbu.getSelectedIndex() == 2) {
            chkHipertensi.setEnabled(true);
            chkDM.setEnabled(true);
            chkPMS.setEnabled(true);
            chkTBC.setEnabled(true);
            chkAsma.setEnabled(true);
            chkHepB.setEnabled(true);
            chkLainyaRiwayat.setEnabled(true);
            chkHipertensi.requestFocus();
        } else {
            chkHipertensi.setEnabled(false);
            chkDM.setEnabled(false);
            chkPMS.setEnabled(false);
            chkTBC.setEnabled(false);
            chkAsma.setEnabled(false);
            chkHepB.setEnabled(false);
            chkLainyaRiwayat.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRiwPenyakitIbuActionPerformed

    private void chkLainyaRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainyaRiwayatActionPerformed
        TlainRiwayat.setText("");
        if (chkLainyaRiwayat.isSelected() == true) {
            TlainRiwayat.setEnabled(true);
            TlainRiwayat.requestFocus();
        } else {
            TlainRiwayat.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainyaRiwayatActionPerformed

    private void TobatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdiagnosaIbu.requestFocus();
        }
    }//GEN-LAST:event_TobatKeyPressed

    private void cmbMasihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMasihActionPerformed
        Tobat.setText("");
        if (cmbMasih.getSelectedIndex() == 2) {
            Tobat.setEnabled(true);
            Tobat.requestFocus();
        } else {
            Tobat.setEnabled(false);
        }
    }//GEN-LAST:event_cmbMasihActionPerformed

    private void TdiagnosaIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaIbuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTgllahirIbu.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaIbuKeyPressed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void TkeadaanSaatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeadaanSaatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tas.requestFocus();
        }
    }//GEN-LAST:event_TkeadaanSaatKeyPressed

    private void TasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSpontan.requestFocus();
        }
    }//GEN-LAST:event_TasKeyPressed

    private void TlainCaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainCaraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSegar.requestFocus();
        }
    }//GEN-LAST:event_TlainCaraKeyPressed

    private void chkLainyaCaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainyaCaraActionPerformed
        TlainCara.setText("");
        if (chkLainyaCara.isSelected() == true) {
            TlainCara.setEnabled(true);
            TlainCara.requestFocus();
        } else {
            TlainCara.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainyaCaraActionPerformed

    private void cmbRiwAlergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwAlergiActionPerformed
        chkObatAlergi.setSelected(false);
        chkMakananAlergi.setSelected(false);
        chkLainAlergi.setSelected(false);
        TobatAlergi.setEnabled(false);
        TmakananAlergi.setEnabled(false);
        TlainyaAlergi.setEnabled(false);        
        TobatAlergi.setText("");
        TmakananAlergi.setText("");
        TlainyaAlergi.setText("");
        Treaksi.setText("");
        
        if (cmbRiwAlergi.getSelectedIndex() == 2) {
            chkObatAlergi.setEnabled(true);
            chkMakananAlergi.setEnabled(true);
            chkLainAlergi.setEnabled(true);
            Treaksi.setEnabled(true);
            chkObatAlergi.requestFocus();
        } else {
            chkObatAlergi.setEnabled(false);
            chkMakananAlergi.setEnabled(false);
            chkLainAlergi.setEnabled(false); 
            Treaksi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRiwAlergiActionPerformed

    private void TobatAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatAlergiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkMakananAlergi.requestFocus();
        }
    }//GEN-LAST:event_TobatAlergiKeyPressed

    private void TmakananAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmakananAlergiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkLainAlergi.requestFocus();
        }
    }//GEN-LAST:event_TmakananAlergiKeyPressed

    private void TlainyaAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainyaAlergiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Treaksi.requestFocus();
        }
    }//GEN-LAST:event_TlainyaAlergiKeyPressed

    private void chkObatAlergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkObatAlergiActionPerformed
        TobatAlergi.setText("");
        if (chkObatAlergi.isSelected() == true) {
            TobatAlergi.setEnabled(true);
            TobatAlergi.requestFocus();
        } else {
            TobatAlergi.setEnabled(false);
        }
    }//GEN-LAST:event_chkObatAlergiActionPerformed

    private void chkMakananAlergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMakananAlergiActionPerformed
        TmakananAlergi.setText("");
        if (chkMakananAlergi.isSelected() == true) {
            TmakananAlergi.setEnabled(true);
            TmakananAlergi.requestFocus();
        } else {
            TmakananAlergi.setEnabled(false);
        }
    }//GEN-LAST:event_chkMakananAlergiActionPerformed

    private void chkLainAlergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainAlergiActionPerformed
        TlainyaAlergi.setText("");
        if (chkLainAlergi.isSelected() == true) {
            TlainyaAlergi.setEnabled(true);
            TlainyaAlergi.requestFocus();
        } else {
            TlainyaAlergi.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainAlergiActionPerformed

    private void TreaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreaksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbMasalah.requestFocus();
        }
    }//GEN-LAST:event_TreaksiKeyPressed

    private void TlainPerkawinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainPerkawinanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbMengalami.requestFocus();
        }
    }//GEN-LAST:event_TlainPerkawinanKeyPressed

    private void cmbAdaPerkawinanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAdaPerkawinanActionPerformed
        TlainPerkawinan.setText("");
        if (cmbAdaPerkawinan.getSelectedIndex() == 4) {
            TlainPerkawinan.setEnabled(true);
            TlainPerkawinan.requestFocus();
        } else {
            TlainPerkawinan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbAdaPerkawinanActionPerformed

    private void cmbMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMasalahActionPerformed
        cmbAdaPerkawinan.setSelectedIndex(0);
        TlainPerkawinan.setText("");

        if (cmbMasalah.getSelectedIndex() == 1) {
            cmbAdaPerkawinan.setEnabled(true);
            TlainPerkawinan.setEnabled(true);
            cmbAdaPerkawinan.requestFocus();
        } else {
            cmbAdaPerkawinan.setEnabled(false);
            TlainPerkawinan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbMasalahActionPerformed

    private void cmbMengalamiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMengalamiActionPerformed
        cmbAdaMengalami.setSelectedIndex(0);
        cmbDialami.setSelectedIndex(0);
        
        if (cmbMengalami.getSelectedIndex() == 1) {
            cmbAdaMengalami.setEnabled(true);
            cmbDialami.setEnabled(true);
            cmbAdaMengalami.requestFocus();
        } else {
            cmbAdaMengalami.setEnabled(false);
            cmbDialami.setEnabled(false);
        }
    }//GEN-LAST:event_cmbMengalamiActionPerformed

    private void cmbTraumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTraumaActionPerformed
        TjelaskanTrauma.setText("");
        if (cmbTrauma.getSelectedIndex() == 1) {
            TjelaskanTrauma.setEnabled(true);
            TjelaskanTrauma.requestFocus();
        } else {
            TjelaskanTrauma.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTraumaActionPerformed

    private void TjelaskanTraumaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjelaskanTraumaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbGangguan.requestFocus();
        }
    }//GEN-LAST:event_TjelaskanTraumaKeyPressed

    private void TlainDukunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainDukunganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbStatusNikah.requestFocus();
        }
    }//GEN-LAST:event_TlainDukunganKeyPressed

    private void chkLainDukunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainDukunganActionPerformed
        TlainDukungan.setText("");
        if (chkLainDukungan.isSelected() == true) {
            TlainDukungan.setEnabled(true);
            TlainDukungan.requestFocus();
        } else {
            TlainDukungan.setEnabled(false);
        }                  
    }//GEN-LAST:event_chkLainDukunganActionPerformed

    private void TkaliMenikahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkaliMenikahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbHubungan.requestFocus();
        }
    }//GEN-LAST:event_TkaliMenikahKeyPressed

    private void cmbStatusNikahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusNikahActionPerformed
        TkaliMenikah.setText("");
        if (cmbStatusNikah.getSelectedIndex() == 2) {
            TkaliMenikah.setEnabled(true);
            TkaliMenikah.requestFocus();
        } else {
            TkaliMenikah.setEnabled(false);
        }
    }//GEN-LAST:event_cmbStatusNikahActionPerformed

    private void cmbTinggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTinggalActionPerformed
        TlainTinggal.setText("");
        if (cmbTinggal.getSelectedIndex() == 7) {
            TlainTinggal.setEnabled(true);
            TlainTinggal.requestFocus();
        } else {
            TlainTinggal.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTinggalActionPerformed

    private void TlainTinggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainTinggalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbTempat.requestFocus();
        }
    }//GEN-LAST:event_TlainTinggalKeyPressed

    private void cmbTempatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTempatActionPerformed
        TlainTempat.setText("");
        if (cmbTempat.getSelectedIndex() == 4) {
            TlainTempat.setEnabled(true);
            TlainTempat.requestFocus();
        } else {
            TlainTempat.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTempatActionPerformed

    private void TlainTempatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainTempatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmKerabat.requestFocus();
        }
    }//GEN-LAST:event_TlainTempatKeyPressed

    private void TnmKerabatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmKerabatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ThubKerabat.requestFocus();
        }
    }//GEN-LAST:event_TnmKerabatKeyPressed

    private void ThubKerabatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThubKerabatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtelpKerabat.requestFocus();
        }
    }//GEN-LAST:event_ThubKerabatKeyPressed

    private void TtelpKerabatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtelpKerabatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TkegiatanAgama.requestFocus();
        }
    }//GEN-LAST:event_TtelpKerabatKeyPressed

    private void TkegiatanAgamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkegiatanAgamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TkegiatanSpiritual.requestFocus();
        }
    }//GEN-LAST:event_TkegiatanAgamaKeyPressed

    private void TkegiatanSpiritualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkegiatanSpiritualKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbNyeri.requestFocus();
        }
    }//GEN-LAST:event_TkegiatanSpiritualKeyPressed

    private void cmbNyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNyeriActionPerformed
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
        
        if (cmbNyeri.getSelectedIndex() == 2) {
            cmbCrying.setEnabled(true);
            cmbRequires.setEnabled(true);
            cmbIncreased.setEnabled(true);
            cmbExpresion.setEnabled(true);
            cmbSleepless.setEnabled(true);
            cmbCrying.requestFocus();
        } else {
            cmbCrying.setEnabled(false);
            cmbRequires.setEnabled(false);
            cmbIncreased.setEnabled(false);
            cmbExpresion.setEnabled(false);
            cmbSleepless.setEnabled(false);
        }
    }//GEN-LAST:event_cmbNyeriActionPerformed

    private void cmbCryingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCryingActionPerformed
        hitungNilaiNyeri();
    }//GEN-LAST:event_cmbCryingActionPerformed

    private void cmbRequiresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRequiresActionPerformed
        hitungNilaiNyeri();
    }//GEN-LAST:event_cmbRequiresActionPerformed

    private void cmbIncreasedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbIncreasedActionPerformed
        hitungNilaiNyeri();
    }//GEN-LAST:event_cmbIncreasedActionPerformed

    private void cmbExpresionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbExpresionActionPerformed
        hitungNilaiNyeri();
    }//GEN-LAST:event_cmbExpresionActionPerformed

    private void cmbSleeplessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSleeplessActionPerformed
        hitungNilaiNyeri();
    }//GEN-LAST:event_cmbSleeplessActionPerformed

    private void chkSikap0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSikap0ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkSikap0ActionPerformed

    private void chkSikap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSikap1ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkSikap1ActionPerformed

    private void chkSikap2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSikap2ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkSikap2ActionPerformed

    private void chkSikap3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSikap3ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkSikap3ActionPerformed

    private void chkSikap4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSikap4ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkSikap4ActionPerformed

    private void chkPersegi_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPersegi_1ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkPersegi_1ActionPerformed

    private void chkPersegi0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPersegi0ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkPersegi0ActionPerformed

    private void chkPersegi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPersegi1ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkPersegi1ActionPerformed

    private void chkPersegi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPersegi2ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkPersegi2ActionPerformed

    private void chkPersegi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPersegi3ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkPersegi3ActionPerformed

    private void chkPersegi4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPersegi4ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkPersegi4ActionPerformed

    private void chkRekoli0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRekoli0ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkRekoli0ActionPerformed

    private void chkRekoli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRekoli1ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkRekoli1ActionPerformed

    private void chkRekoli2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRekoli2ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkRekoli2ActionPerformed

    private void chkRekoli3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRekoli3ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkRekoli3ActionPerformed

    private void chkRekoli4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRekoli4ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkRekoli4ActionPerformed

    private void chkSudut_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSudut_1ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkSudut_1ActionPerformed

    private void chkSudut0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSudut0ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkSudut0ActionPerformed

    private void chkSudut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSudut1ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkSudut1ActionPerformed

    private void chkSudut2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSudut2ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkSudut2ActionPerformed

    private void chkSudut3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSudut3ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkSudut3ActionPerformed

    private void chkSudut4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSudut4ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkSudut4ActionPerformed

    private void chkSudut5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSudut5ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkSudut5ActionPerformed

    private void chkTanda_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTanda_1ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkTanda_1ActionPerformed

    private void chkTanda0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTanda0ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkTanda0ActionPerformed

    private void chkTanda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTanda1ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkTanda1ActionPerformed

    private void chkTanda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTanda2ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkTanda2ActionPerformed

    private void chkTanda3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTanda3ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkTanda3ActionPerformed

    private void chkTanda4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTanda4ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkTanda4ActionPerformed

    private void chkTumit_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTumit_1ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkTumit_1ActionPerformed

    private void chkTumit0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTumit0ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkTumit0ActionPerformed

    private void chkTumit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTumit1ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkTumit1ActionPerformed

    private void chkTumit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTumit2ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkTumit2ActionPerformed

    private void chkTumit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTumit3ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkTumit3ActionPerformed

    private void chkTumit4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTumit4ActionPerformed
        hitungNilaiNeomuskular();
    }//GEN-LAST:event_chkTumit4ActionPerformed

    private void cmbFisikKulitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFisikKulitActionPerformed
        hitungNilaiFisik();
    }//GEN-LAST:event_cmbFisikKulitActionPerformed

    private void cmbFisikPayudaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFisikPayudaraActionPerformed
        hitungNilaiFisik();
    }//GEN-LAST:event_cmbFisikPayudaraActionPerformed

    private void cmbFisikMataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFisikMataActionPerformed
        hitungNilaiFisik();
    }//GEN-LAST:event_cmbFisikMataActionPerformed

    private void cmbFisikGenPriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFisikGenPriaActionPerformed
        hitungNilaiFisik();
    }//GEN-LAST:event_cmbFisikGenPriaActionPerformed

    private void cmbFisikGenWanitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFisikGenWanitaActionPerformed
        hitungNilaiFisik();
    }//GEN-LAST:event_cmbFisikGenWanitaActionPerformed

    private void cmbFisikLanugoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFisikLanugoActionPerformed
        hitungNilaiFisik();
    }//GEN-LAST:event_cmbFisikLanugoActionPerformed

    private void cmbFisikPlantarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFisikPlantarActionPerformed
        hitungNilaiFisik();
    }//GEN-LAST:event_cmbFisikPlantarActionPerformed

    private void TmasalahLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmasalahLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TtglRencana1.requestFocus();
        }
    }//GEN-LAST:event_TmasalahLainKeyPressed

    private void chkTgllahirIbuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTgllahirIbuActionPerformed
        TtglLahir.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        
        if (chkTgllahirIbu.isSelected() == true) {
            TtglLahir.setEnabled(true);
            cmbJam.setEnabled(true);
            cmbMnt.setEnabled(true);
            cmbDtk.setEnabled(true);
            TtglLahir.requestFocus();
        } else {
            TtglLahir.setEnabled(false);
            cmbJam.setEnabled(false);
            cmbMnt.setEnabled(false);
            cmbDtk.setEnabled(false);
        }
    }//GEN-LAST:event_chkTgllahirIbuActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenKeperawatanPerinatologi dialog = new RMAsesmenKeperawatanPerinatologi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnNotepad;
    private widget.Button BtnPerawat;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.InternalFrame FormAsesmen;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private usu.widget.glass.PanelGlass PanelWall;
    private usu.widget.glass.PanelGlass PanelWall1;
    private usu.widget.glass.PanelGlass PanelWall10;
    private usu.widget.glass.PanelGlass PanelWall12;
    private usu.widget.glass.PanelGlass PanelWall13;
    private usu.widget.glass.PanelGlass PanelWall14;
    private usu.widget.glass.PanelGlass PanelWall15;
    private usu.widget.glass.PanelGlass PanelWall16;
    private usu.widget.glass.PanelGlass PanelWall18;
    private usu.widget.glass.PanelGlass PanelWall19;
    private usu.widget.glass.PanelGlass PanelWall2;
    private usu.widget.glass.PanelGlass PanelWall20;
    private usu.widget.glass.PanelGlass PanelWall21;
    private usu.widget.glass.PanelGlass PanelWall22;
    private usu.widget.glass.PanelGlass PanelWall23;
    private usu.widget.glass.PanelGlass PanelWall24;
    private usu.widget.glass.PanelGlass PanelWall25;
    private usu.widget.glass.PanelGlass PanelWall26;
    private usu.widget.glass.PanelGlass PanelWall27;
    private usu.widget.glass.PanelGlass PanelWall28;
    private usu.widget.glass.PanelGlass PanelWall29;
    private usu.widget.glass.PanelGlass PanelWall3;
    private usu.widget.glass.PanelGlass PanelWall30;
    private usu.widget.glass.PanelGlass PanelWall31;
    private usu.widget.glass.PanelGlass PanelWall32;
    private usu.widget.glass.PanelGlass PanelWall33;
    private usu.widget.glass.PanelGlass PanelWall34;
    private usu.widget.glass.PanelGlass PanelWall35;
    private usu.widget.glass.PanelGlass PanelWall36;
    private usu.widget.glass.PanelGlass PanelWall4;
    private usu.widget.glass.PanelGlass PanelWall5;
    private usu.widget.glass.PanelGlass PanelWall6;
    private usu.widget.glass.PanelGlass PanelWall7;
    private usu.widget.glass.PanelGlass PanelWall8;
    private usu.widget.glass.PanelGlass PanelWall9;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane ScrollTriase1;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Talamat;
    private widget.TextBox TanakKe;
    private widget.TextBox Tas;
    private widget.TextBox Tbbl;
    private widget.TextBox TdiagnosaIbu;
    private widget.TextBox TdiagnosaRujukan;
    private widget.TextBox ThubKerabat;
    private widget.TextBox TjelaskanTrauma;
    private widget.TextBox TkaliMenikah;
    private widget.TextBox TkeadaanSaat;
    private widget.TextBox TkegiatanAgama;
    private widget.TextBox TkegiatanSpiritual;
    private widget.TextBox Tkelainan;
    private widget.TextBox Tkeluhan;
    private widget.TextBox TkesimpulanNyeri;
    private widget.TextBox TkesimpulanSkor;
    private widget.TextBox Tkk;
    private widget.TextBox TlainCara;
    private widget.TextBox TlainDukungan;
    private widget.TextBox TlainPerkawinan;
    private widget.TextBox TlainRiwayat;
    private widget.TextBox TlainSumber;
    private widget.TextBox TlainTempat;
    private widget.TextBox TlainTinggal;
    private widget.TextBox TlainyaAlergi;
    private widget.TextBox Tld;
    private widget.TextBox Tlk;
    private widget.TextBox Tll;
    private widget.TextBox Tlp;
    private widget.TextBox TmakananAlergi;
    private widget.TextArea TmasalahLain;
    private widget.TextBox Tnadi;
    private widget.TextBox TnilaiCrying;
    private widget.TextBox TnilaiExpresion;
    private widget.TextBox TnilaiFisik;
    private widget.TextBox TnilaiGenPria;
    private widget.TextBox TnilaiGenWanita;
    private widget.TextBox TnilaiIncreased;
    private widget.TextBox TnilaiKulit;
    private widget.TextBox TnilaiLanugo;
    private widget.TextBox TnilaiMata;
    private widget.TextBox TnilaiNeo;
    private widget.TextBox TnilaiPayudara;
    private widget.TextBox TnilaiPersegi;
    private widget.TextBox TnilaiPlantar;
    private widget.TextBox TnilaiRekoli;
    private widget.TextBox TnilaiRequires;
    private widget.TextBox TnilaiSikap;
    private widget.TextBox TnilaiSkor;
    private widget.TextBox TnilaiSleepless;
    private widget.TextBox TnilaiSudut;
    private widget.TextBox TnilaiTanda;
    private widget.TextBox TnilaiTumit;
    private widget.TextBox TnmIdentitas;
    private widget.TextBox TnmKerabat;
    private widget.TextBox TnmPerawat;
    private widget.TextBox Tobat;
    private widget.TextBox TobatAlergi;
    private widget.TextBox Tpb;
    private widget.TextBox Tpekerjaan;
    private widget.TextBox Treaksi;
    private widget.TextBox TrgRawat;
    private widget.TextBox Trr;
    private widget.TextBox Tsuhu;
    private widget.TextBox TtelpKerabat;
    private widget.Tanggal TtglLahir;
    private widget.Tanggal TtglRencana1;
    private widget.Tanggal TtglRencana2;
    private widget.TextBox TtotNilaiFisik;
    private widget.TextBox TtotNilaiNeomuskular;
    private widget.TextBox TtotNilaiNyeri;
    private widget.TextBox TumurHamil;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    public widget.CekBox chkAsfiksia;
    public widget.CekBox chkAsma;
    public widget.CekBox chkBblr;
    public widget.CekBox chkBerbau;
    public widget.CekBox chkBersihan;
    public widget.CekBox chkChorio;
    public widget.CekBox chkDM;
    public widget.CekBox chkFetal;
    public widget.CekBox chkForcep;
    public widget.CekBox chkGangguan;
    public widget.CekBox chkGemeli;
    public widget.CekBox chkHepB;
    public widget.CekBox chkHijau;
    public widget.CekBox chkHipertensi;
    public widget.CekBox chkHipertermi;
    public widget.CekBox chkHipotermi;
    public widget.CekBox chkIbuDemam;
    public widget.CekBox chkIkterik;
    public widget.CekBox chkIsk;
    public widget.CekBox chkJernih;
    public widget.CekBox chkKebutuhan;
    public widget.CekBox chkKelebihan;
    public widget.CekBox chkKeluarga;
    public widget.CekBox chkKeputihan;
    public widget.CekBox chkKering;
    public widget.CekBox chkKeruh;
    public widget.CekBox chkKerusakan;
    public widget.CekBox chkKetuban;
    public widget.CekBox chkKpd12;
    public widget.CekBox chkKpd24;
    public widget.CekBox chkLainAlergi;
    public widget.CekBox chkLainDukungan;
    public widget.CekBox chkLainyaCara;
    public widget.CekBox chkLainyaRiwayat;
    public widget.CekBox chkLayu;
    public widget.CekBox chkLumpur;
    public widget.CekBox chkMakananAlergi;
    public widget.CekBox chkNyeri;
    public widget.CekBox chkObatAlergi;
    public widget.CekBox chkOrangTua;
    public widget.CekBox chkPMS;
    public widget.CekBox chkPersegi0;
    public widget.CekBox chkPersegi1;
    public widget.CekBox chkPersegi2;
    public widget.CekBox chkPersegi3;
    public widget.CekBox chkPersegi4;
    public widget.CekBox chkPersegi_1;
    public widget.CekBox chkPerubahan;
    public widget.CekBox chkPolaNafas;
    public widget.CekBox chkRekoli0;
    public widget.CekBox chkRekoli1;
    public widget.CekBox chkRekoli2;
    public widget.CekBox chkRekoli3;
    public widget.CekBox chkRekoli4;
    public widget.CekBox chkResikoBersihan;
    public widget.CekBox chkResikoHipotermi;
    public widget.CekBox chkResikoKebutuhan;
    public widget.CekBox chkResikoKelebihan;
    public widget.CekBox chkResikoKerusakan;
    public widget.CekBox chkSectio;
    public widget.CekBox chkSegar;
    public widget.CekBox chkSikap0;
    public widget.CekBox chkSikap1;
    public widget.CekBox chkSikap2;
    public widget.CekBox chkSikap3;
    public widget.CekBox chkSikap4;
    public widget.CekBox chkSimpul;
    public widget.CekBox chkSpontan;
    public widget.CekBox chkSuami;
    public widget.CekBox chkSudut0;
    public widget.CekBox chkSudut1;
    public widget.CekBox chkSudut2;
    public widget.CekBox chkSudut3;
    public widget.CekBox chkSudut4;
    public widget.CekBox chkSudut5;
    public widget.CekBox chkSudut_1;
    public widget.CekBox chkSuhuIbu;
    public widget.CekBox chkTBC;
    public widget.CekBox chkTanda0;
    public widget.CekBox chkTanda1;
    public widget.CekBox chkTanda2;
    public widget.CekBox chkTanda3;
    public widget.CekBox chkTanda4;
    public widget.CekBox chkTanda_1;
    public widget.CekBox chkTgllahirIbu;
    public widget.CekBox chkTumit0;
    public widget.CekBox chkTumit1;
    public widget.CekBox chkTumit2;
    public widget.CekBox chkTumit3;
    public widget.CekBox chkTumit4;
    public widget.CekBox chkTumit_1;
    public widget.CekBox chkUK;
    public widget.CekBox chkVakum;
    private widget.ComboBox cmbAdaMengalami;
    private widget.ComboBox cmbAdaPerkawinan;
    private widget.ComboBox cmbAgama;
    private widget.ComboBox cmbAnus;
    private widget.ComboBox cmbBab;
    private widget.ComboBox cmbBak;
    private widget.ComboBox cmbCrying;
    private widget.ComboBox cmbDialami;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbExpresion;
    private widget.ComboBox cmbFisikGenPria;
    private widget.ComboBox cmbFisikGenWanita;
    private widget.ComboBox cmbFisikKulit;
    private widget.ComboBox cmbFisikLanugo;
    private widget.ComboBox cmbFisikMata;
    private widget.ComboBox cmbFisikPayudara;
    private widget.ComboBox cmbFisikPlantar;
    private widget.ComboBox cmbGangguan;
    private widget.ComboBox cmbHubungan;
    private widget.ComboBox cmbIncreased;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbJenkel;
    private widget.ComboBox cmbJnsRujukan;
    private widget.ComboBox cmbKonsultasi;
    private widget.ComboBox cmbMasalah;
    private widget.ComboBox cmbMasih;
    private widget.ComboBox cmbMengalami;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbNyeri;
    private widget.ComboBox cmbPendidikan;
    private widget.ComboBox cmbPenerimaan;
    private widget.ComboBox cmbRequires;
    private widget.ComboBox cmbRiwAlergi;
    private widget.ComboBox cmbRiwPenyakitIbu;
    private widget.ComboBox cmbRujukan;
    private widget.ComboBox cmbSleepless;
    private widget.ComboBox cmbStatusNikah;
    private widget.ComboBox cmbSumber;
    private widget.ComboBox cmbTempat;
    private widget.ComboBox cmbTinggal;
    private widget.ComboBox cmbTrauma;
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
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane14;
    private widget.Table tbAsesmen;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT ak.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d/%m/%Y') tglLahir, "
                    + "date_format(ak.tgl_rencana1,'%d/%m/%Y') tglRen1, time_format(ak.jam_rencana1,'%H:%i Wita') jamRen1, date_format(ak.tgl_rencana2,'%d/%m/%Y') tglRen2, "
                    + "time_format(ak.jam_rencana2,'%H:%i Wita') jamRen2, pg.nama nmPerawat FROM asesmen_keperawatan_perinatologi ak "
                    + "inner join reg_periksa rp on rp.no_rawat=ak.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=ak.nip_perawat where "
                    + "date(ak.waktu_simpan) between ? and ? and ak.no_rawat like ? or "
                    + "date(ak.waktu_simpan) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(ak.waktu_simpan) between ? and ? and p.nm_pasien like ? or "
                    + "date(ak.waktu_simpan) between ? and ? and ak.ruang_rawat like ? or "
                    + "date(ak.waktu_simpan) between ? and ? and pg.nama like ? order by ak.waktu_simpan desc");
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
                        rs.getString("cek_tgllahir_ibu")
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
        chkHipertensi.setEnabled(false);
        chkDM.setEnabled(false);
        chkPMS.setEnabled(false);
        chkTBC.setEnabled(false);
        chkAsma.setEnabled(false);
        chkHepB.setEnabled(false);
        chkLainyaRiwayat.setEnabled(false);
        chkHipertensi.setSelected(false);
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
        TmasalahLain.setText("");        
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
                nip = "";
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
            TmasalahLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 183).toString());            
            nip = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 188).toString();
            cekTglLahir = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 190).toString();
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
                + "jam_rencana1=?, tgl_rencana2=?, jam_rencana2=?, nip_perawat=?, cek_tgllahir_ibu=?", 180, new String[]{
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
                    kelebihan, resikoKelebihan, resikoKebutuhan, TmasalahLain.getText(), Valid.SetTgl(TtglRencana1.getSelectedItem() + ""),
                    cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), Valid.SetTgl(TtglRencana2.getSelectedItem() + ""),
                    cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), nip, cekTglLahir,
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
        if (chkHipertensi.isSelected() == true) {
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
            chkHipertensi.setEnabled(true);
            chkDM.setEnabled(true);
            chkPMS.setEnabled(true);
            chkTBC.setEnabled(true);
            chkAsma.setEnabled(true);
            chkHepB.setEnabled(true);
            chkLainyaRiwayat.setEnabled(true);
        } else {
            chkHipertensi.setEnabled(false);
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
            chkHipertensi.setSelected(true);
        } else {
            chkHipertensi.setSelected(false);
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
    
    private void hitungNilaiNyeri() {
        int a = 0, b = 0, c = 0, d = 0, e = 0, hasilNilai = 0;
        
        //crying
        if (cmbCrying.getSelectedIndex() == 0 || cmbCrying.getSelectedIndex() == 1) {
            a = 0;            
        } else if (cmbCrying.getSelectedIndex() == 2) {
            a = 1;
        } else if (cmbCrying.getSelectedIndex() == 3) {
            a = 2;
        }
        
        //requires
        if (cmbRequires.getSelectedIndex() == 0 || cmbRequires.getSelectedIndex() == 1) {
            b = 0;            
        } else if (cmbRequires.getSelectedIndex() == 2) {
            b = 1;
        } else if (cmbRequires.getSelectedIndex() == 3) {
            b = 2;
        }
        
        //increased
        if (cmbIncreased.getSelectedIndex() == 0 || cmbIncreased.getSelectedIndex() == 1) {
            c = 0;            
        } else if (cmbIncreased.getSelectedIndex() == 2) {
            c = 1;
        } else if (cmbIncreased.getSelectedIndex() == 3) {
            c = 2;
        }
        
        //expresion
        if (cmbExpresion.getSelectedIndex() == 0 || cmbExpresion.getSelectedIndex() == 1) {
            d = 0;            
        } else if (cmbExpresion.getSelectedIndex() == 2) {
            d = 1;
        }
        
        //sleepless
        if (cmbSleepless.getSelectedIndex() == 0 || cmbSleepless.getSelectedIndex() == 1) {
            e = 0;            
        } else if (cmbSleepless.getSelectedIndex() == 2) {
            e = 1;
        } else if (cmbSleepless.getSelectedIndex() == 3) {
            e = 2;
        }
        
        //proses menghitung
        if (cmbCrying.getSelectedIndex() == 0) {
            TnilaiCrying.setText("");
        } else {
            TnilaiCrying.setText(Valid.SetAngka2(a));
        }
        
        if (cmbRequires.getSelectedIndex() == 0) {
            TnilaiRequires.setText("");
        } else {
            TnilaiRequires.setText(Valid.SetAngka2(b));
        }
        
        if (cmbIncreased.getSelectedIndex() == 0) {
            TnilaiIncreased.setText("");
        } else {
            TnilaiIncreased.setText(Valid.SetAngka2(c));
        }
        
        if (cmbExpresion.getSelectedIndex() == 0) {
            TnilaiExpresion.setText("");
        } else {
            TnilaiExpresion.setText(Valid.SetAngka2(d));
        }
        
        if (cmbSleepless.getSelectedIndex() == 0) {
            TnilaiSleepless.setText("");
        } else {
            TnilaiSleepless.setText(Valid.SetAngka2(e));
        }
        
        hasilNilai = a + b + c + d + e;
        TtotNilaiNyeri.setText(Valid.SetAngka2(hasilNilai));
        
        //kesimpulan penilaian
        if (hasilNilai == 0) {
            TkesimpulanNyeri.setText("-");
        } else if (hasilNilai >= 1 && hasilNilai <= 3) {
            TkesimpulanNyeri.setText("Nyeri ringan, analgesik oral");
        } else if (hasilNilai >= 4) {
            TkesimpulanNyeri.setText("Nyeri sedang, perlu analgesik injeksi");
        }
    }
    
    private void hitungNilaiNeomuskular() {
        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, hasilNilai = 0;
        
        //sikap tubuh
        if (chkSikap0.isSelected() == true) {
            a = 0;
        } else if (chkSikap1.isSelected() == true) {
            a = 1;
        } else if (chkSikap2.isSelected() == true) {
            a = 2;
        } else if (chkSikap3.isSelected() == true) {
            a = 3;
        } else if (chkSikap4.isSelected() == true) {
            a = 4;
        }
        
        //persegi jendela
        if (chkPersegi_1.isSelected() == true) {
            b = -1;
        } else if (chkPersegi0.isSelected() == true) {
            b = 0;
        } else if (chkPersegi1.isSelected() == true) {
            b = 1;
        } else if (chkPersegi2.isSelected() == true) {
            b = 2;
        } else if (chkPersegi3.isSelected() == true) {
            b = 3;
        } else if (chkPersegi4.isSelected() == true) {
            b = 4;
        }
        
        //rekoli lengan
        if (chkRekoli0.isSelected() == true) {
            c = 0;
        } else if (chkRekoli1.isSelected() == true) {
            c = 1;
        } else if (chkRekoli2.isSelected() == true) {
            c = 2;
        } else if (chkRekoli3.isSelected() == true) {
            c = 3;
        } else if (chkRekoli4.isSelected() == true) {
            c = 4;
        }
        
        //sudut popliteal
        if (chkSudut_1.isSelected() == true) {
            d = -1;
        } else if (chkSudut0.isSelected() == true) {
            d = 0;
        } else if (chkSudut1.isSelected() == true) {
            d = 1;
        } else if (chkSudut2.isSelected() == true) {
            d = 2;
        } else if (chkSudut3.isSelected() == true) {
            d = 3;
        } else if (chkSudut4.isSelected() == true) {
            d = 4;
        } else if (chkSudut5.isSelected() == true) {
            d = 5;
        }
        
        //tanda selempang
        if (chkTanda_1.isSelected() == true) {
            e = -1;
        } else if (chkTanda0.isSelected() == true) {
            e = 0;
        } else if (chkTanda1.isSelected() == true) {
            e = 1;
        } else if (chkTanda2.isSelected() == true) {
            e = 2;
        } else if (chkTanda3.isSelected() == true) {
            e = 3;
        } else if (chkTanda4.isSelected() == true) {
            e = 4;
        }
        
        //tumit kekuping
        if (chkTumit_1.isSelected() == true) {
            f = -1;
        } else if (chkTumit0.isSelected() == true) {
            f = 0;
        } else if (chkTumit1.isSelected() == true) {
            f = 1;
        } else if (chkTumit2.isSelected() == true) {
            f = 2;
        } else if (chkTumit3.isSelected() == true) {
            f = 3;
        } else if (chkTumit4.isSelected() == true) {
            f = 4;
        }
        
        //proses menghitung
        if (chkSikap0.isSelected() == false && chkSikap1.isSelected() == false && chkSikap2.isSelected() == false
                && chkSikap3.isSelected() == false && chkSikap4.isSelected() == false) {
            TnilaiSikap.setText("");
        } else {
            TnilaiSikap.setText(Valid.SetAngka2(a));
        }
        
        if (chkPersegi_1.isSelected() == false && chkPersegi0.isSelected() == false && chkPersegi1.isSelected() == false
                && chkPersegi2.isSelected() == false && chkPersegi3.isSelected() == false && chkPersegi4.isSelected() == false) {
            TnilaiPersegi.setText("");
        } else {
            TnilaiPersegi.setText(Valid.SetAngka2(b));
        }
        
        if (chkRekoli0.isSelected() == false && chkRekoli1.isSelected() == false && chkRekoli2.isSelected() == false
                && chkRekoli3.isSelected() == false && chkRekoli4.isSelected() == false) {
            TnilaiRekoli.setText("");
        } else {
            TnilaiRekoli.setText(Valid.SetAngka2(c));
        }
        
        if (chkSudut_1.isSelected() == false && chkSudut0.isSelected() == false && chkSudut1.isSelected() == false && chkSudut2.isSelected() == false
                && chkSudut3.isSelected() == false && chkSudut4.isSelected() == false && chkSudut5.isSelected() == false) {
            TnilaiSudut.setText("");
        } else {
            TnilaiSudut.setText(Valid.SetAngka2(d));
        }
        
        if (chkTanda_1.isSelected() == false && chkTanda0.isSelected() == false && chkTanda1.isSelected() == false && chkTanda2.isSelected() == false
                && chkTanda3.isSelected() == false && chkTanda4.isSelected() == false) {
            TnilaiTanda.setText("");
        } else {
            TnilaiTanda.setText(Valid.SetAngka2(e));
        }
        
        if (chkTumit_1.isSelected() == false && chkTumit0.isSelected() == false && chkTumit1.isSelected() == false && chkTumit2.isSelected() == false 
                && chkTumit3.isSelected() == false && chkTumit4.isSelected() == false) {
            TnilaiTumit.setText("");
        } else {
            TnilaiTumit.setText(Valid.SetAngka2(f));
        }        
        
        hasilNilai = a + b + c + d + e + f;
        TtotNilaiNeomuskular.setText(Valid.SetAngka2(hasilNilai));
        TnilaiNeo.setText(Valid.SetAngka2(hasilNilai));
        hitungSkor();
    }
    
    private void hitungNilaiFisik() {
        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, hasilNilai = 0;
        
        //kulit
        if (cmbFisikKulit.getSelectedIndex() == 0) {
            a = 0;
        } else if (cmbFisikKulit.getSelectedIndex() == 1) {
            a = -1;
        } else if (cmbFisikKulit.getSelectedIndex() == 2) {
            a = 0;
        } else if (cmbFisikKulit.getSelectedIndex() == 3) {
            a = 1;
        } else if (cmbFisikKulit.getSelectedIndex() == 4) {
            a = 2;
        } else if (cmbFisikKulit.getSelectedIndex() == 5) {
            a = 3;
        } else if (cmbFisikKulit.getSelectedIndex() == 6) {
            a = 4;
        } else if (cmbFisikKulit.getSelectedIndex() == 7) {
            a = 5;
        }
        
        //payudara
        if (cmbFisikPayudara.getSelectedIndex() == 0) {
            b = 0;
        } else if (cmbFisikPayudara.getSelectedIndex() == 1) {
            b = -1;
        } else if (cmbFisikPayudara.getSelectedIndex() == 2) {
            b = 0;
        } else if (cmbFisikPayudara.getSelectedIndex() == 3) {
            b = 1;
        } else if (cmbFisikPayudara.getSelectedIndex() == 4) {
            b = 2;
        } else if (cmbFisikPayudara.getSelectedIndex() == 5) {
            b = 3;
        } else if (cmbFisikPayudara.getSelectedIndex() == 6) {
            b = 4;
        }
        
        //mata
        if (cmbFisikMata.getSelectedIndex() == 0) {
            c = 0;
        } else if (cmbFisikMata.getSelectedIndex() == 1) {
            c = -1;
        } else if (cmbFisikMata.getSelectedIndex() == 2) {
            c = 0;
        } else if (cmbFisikMata.getSelectedIndex() == 3) {
            c = 1;
        } else if (cmbFisikMata.getSelectedIndex() == 4) {
            c = 2;
        } else if (cmbFisikMata.getSelectedIndex() == 5) {
            c = 3;
        } else if (cmbFisikMata.getSelectedIndex() == 6) {
            c = 4;
        }
        
        //genital pria
        if (cmbFisikGenPria.getSelectedIndex() == 0) {
            d = 0;
        } else if (cmbFisikGenPria.getSelectedIndex() == 1) {
            d = -1;
        } else if (cmbFisikGenPria.getSelectedIndex() == 2) {
            d = 0;
        } else if (cmbFisikGenPria.getSelectedIndex() == 3) {
            d = 1;
        } else if (cmbFisikGenPria.getSelectedIndex() == 4) {
            d = 2;
        } else if (cmbFisikGenPria.getSelectedIndex() == 5) {
            d = 3;
        } else if (cmbFisikGenPria.getSelectedIndex() == 6) {
            d = 4;
        }
        
        //genital wanita
        if (cmbFisikGenWanita.getSelectedIndex() == 0) {
            e = 0;
        } else if (cmbFisikGenWanita.getSelectedIndex() == 1) {
            e = -1;
        } else if (cmbFisikGenWanita.getSelectedIndex() == 2) {
            e = 0;
        } else if (cmbFisikGenWanita.getSelectedIndex() == 3) {
            e = 1;
        } else if (cmbFisikGenWanita.getSelectedIndex() == 4) {
            e = 2;
        } else if (cmbFisikGenWanita.getSelectedIndex() == 5) {
            e = 3;
        } else if (cmbFisikGenWanita.getSelectedIndex() == 6) {
            e = 4;
        }
        
        //lanugo
        if (cmbFisikLanugo.getSelectedIndex() == 0) {
            f = 0;
        } else if (cmbFisikLanugo.getSelectedIndex() == 1) {
            f = -1;
        } else if (cmbFisikLanugo.getSelectedIndex() == 2) {
            f = 0;
        } else if (cmbFisikLanugo.getSelectedIndex() == 3) {
            f = 1;
        } else if (cmbFisikLanugo.getSelectedIndex() == 4) {
            f = 2;
        } else if (cmbFisikLanugo.getSelectedIndex() == 5) {
            f = 3;
        } else if (cmbFisikLanugo.getSelectedIndex() == 6) {
            f = 4;
        }
        
        //plantar
        if (cmbFisikPlantar.getSelectedIndex() == 0) {
            g = 0;
        } else if (cmbFisikPlantar.getSelectedIndex() == 1) {
            g = -1;
        } else if (cmbFisikPlantar.getSelectedIndex() == 2) {
            g = 0;
        } else if (cmbFisikPlantar.getSelectedIndex() == 3) {
            g = 1;
        } else if (cmbFisikPlantar.getSelectedIndex() == 4) {
            g = 2;
        } else if (cmbFisikPlantar.getSelectedIndex() == 5) {
            g = 3;
        } else if (cmbFisikPlantar.getSelectedIndex() == 6) {
            g = 4;
        }
        
        //proses menghitung
        if (cmbFisikKulit.getSelectedIndex() == 0) {
            TnilaiKulit.setText("");
        } else {
            TnilaiKulit.setText(Valid.SetAngka2(a));
        }
        
        if (cmbFisikPayudara.getSelectedIndex() == 0) {
            TnilaiPayudara.setText("");
        } else {
            TnilaiPayudara.setText(Valid.SetAngka2(b));
        }
        
        if (cmbFisikMata.getSelectedIndex() == 0) {
            TnilaiMata.setText("");
        } else {
            TnilaiMata.setText(Valid.SetAngka2(c));
        }
        
        if (cmbFisikGenPria.getSelectedIndex() == 0) {
            TnilaiGenPria.setText("");
        } else {
            TnilaiGenPria.setText(Valid.SetAngka2(d));
        }
        
        if (cmbFisikGenWanita.getSelectedIndex() == 0) {
            TnilaiGenWanita.setText("");
        } else {
            TnilaiGenWanita.setText(Valid.SetAngka2(e));
        }
        
        if (cmbFisikLanugo.getSelectedIndex() == 0) {
            TnilaiLanugo.setText("");
        } else {
            TnilaiLanugo.setText(Valid.SetAngka2(f));
        }
        
        if (cmbFisikPlantar.getSelectedIndex() == 0) {
            TnilaiPlantar.setText("");
        } else {
            TnilaiPlantar.setText(Valid.SetAngka2(g));
        }
        
        hasilNilai = a + b + c + d + e + f + g;
        TtotNilaiFisik.setText(Valid.SetAngka2(hasilNilai));
        TnilaiFisik.setText(Valid.SetAngka2(hasilNilai));
        hitungSkor();
    }
    
    private void hitungSkor() {
        int a = 0, b = 0, hasilNilai = 0;
        
        if (TnilaiNeo.getText().equals("")) {
            TnilaiNeo.setText("0");
        } else {
            TnilaiNeo.setText(TnilaiNeo.getText());
        }
        
        if (TnilaiFisik.getText().equals("")) {
            TnilaiFisik.setText("0");
        } else {
            TnilaiFisik.setText(TnilaiFisik.getText());
        }
        
        a = Integer.parseInt(TnilaiNeo.getText());
        b = Integer.parseInt(TnilaiFisik.getText());
        hasilNilai = a + b;
        TnilaiSkor.setText(Valid.SetAngka2(hasilNilai));
        
            //skor -10
        if (hasilNilai >= -10 && hasilNilai <= -4) {
            TkesimpulanSkor.setText("Skor " + TnilaiSkor.getText() + ", Minggu 20");
            //skor -5
        } else if (hasilNilai >= -5 && hasilNilai <= -1) {
            TkesimpulanSkor.setText("Skor " + TnilaiSkor.getText() + ", Minggu 22");
            //skor 0
        } else if (hasilNilai >= 0 && hasilNilai <= 4) {
            TkesimpulanSkor.setText("Skor " + TnilaiSkor.getText() + ", Minggu 24");
            //skor 5
        } else if (hasilNilai >= 5 && hasilNilai <= 9) {
            TkesimpulanSkor.setText("Skor " + TnilaiSkor.getText() + ", Minggu 26");
            //skor 10
        } else if (hasilNilai >= 10 && hasilNilai <= 14) {
            TkesimpulanSkor.setText("Skor " + TnilaiSkor.getText() + ", Minggu 28");
            //skor 15
        } else if (hasilNilai >= 15 && hasilNilai <= 19) {
            TkesimpulanSkor.setText("Skor " + TnilaiSkor.getText() + ", Minggu 30");
            //skor 20
        } else if (hasilNilai >= 20 && hasilNilai <= 24) {
            TkesimpulanSkor.setText("Skor " + TnilaiSkor.getText() + ", Minggu 32");
            //skor 25
        } else if (hasilNilai >= 25 && hasilNilai <= 29) {
            TkesimpulanSkor.setText("Skor " + TnilaiSkor.getText() + ", Minggu 34");
            //skor 30
        } else if (hasilNilai >= 30 && hasilNilai <= 34) {
            TkesimpulanSkor.setText("Skor " + TnilaiSkor.getText() + ", Minggu 36");
            //skor 35
        } else if (hasilNilai >= 35 && hasilNilai <= 39) {
            TkesimpulanSkor.setText("Skor " + TnilaiSkor.getText() + ", Minggu 38");
            //skor 40
        } else if (hasilNilai >= 40 && hasilNilai <= 44) {
            TkesimpulanSkor.setText("Skor " + TnilaiSkor.getText() + ", Minggu 40");
            //skor 45
        } else if (hasilNilai >= 45 && hasilNilai <= 49) {
            TkesimpulanSkor.setText("Skor " + TnilaiSkor.getText() + ", Minggu 42");
            //skor 50
        } else if (hasilNilai >= 50) {
            TkesimpulanSkor.setText("Skor " + TnilaiSkor.getText() + ", Minggu 44");
        } else {
            TkesimpulanSkor.setText("Skor -, Minggu -");
        }
    }
    
    private void variabelBersih() {
        nip = "";
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
}