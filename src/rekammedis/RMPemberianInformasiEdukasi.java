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
public final class RMPemberianInformasiEdukasi extends javax.swing.JDialog {
    private DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private String nip = "", bahasa = "", pendengaran = "", masalahPenglihatan = "", hilangMemori = "", tidakAdaPartisipasi = "", 
            secaraFisiologi = "", tidakDitemukanHambatan = "", cemas = "", emosi = "", kognitif = "", motifasiBuruk = "", bahasaIndonesia = "", 
            bahasaDaerah = "", bahasaInggris = "", bahasaLainnya = "", prosesPenyakit = "", pengobatan = "", alatBantuMedis = "", lainLain = "", 
            terapiObat = "", nutrisi = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPemberianInformasiEdukasi(java.awt.Frame parent, boolean modal) {
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
            "resiko_kebutuhan", "masalah_keperawatan_lain", "tgl_rencana1", "jam_rencana1", "tgl_rencana2", "jam_rencana2", "nip_perawat", "waktu_simpan", "cek_tgllahir_ibu", "spo2"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbPenilaian.setModel(tabMode);
        tbPenilaian.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPenilaian.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 192; i++) {
            TableColumn column = tbPenilaian.getColumnModel().getColumn(i);
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
            }
        }
        tbPenilaian.setDefaultRenderer(Object.class, new WarnaTable());
        
        Tkapan.setDocument(new batasInput((int) 200).getKata(Tkapan));
        Tdaerah.setDocument(new batasInput((int) 200).getKata(Tdaerah));
        TbhsLainya.setDocument(new batasInput((int) 200).getKata(TbhsLainya));
        Tperlu.setDocument(new batasInput((int) 200).getKata(Tperlu));
        TPotensialLain.setDocument(new batasInput((int) 200).getKata(TPotensialLain));        
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
//                    nip = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();                    
//                    TnmPerawat.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
//                    BtnPerawat.requestFocus();
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

        internalFrame1 = new widget.InternalFrame();
        TabEdukasi = new javax.swing.JTabbedPane();
        FormAsesmen = new widget.InternalFrame();
        ScrollTriase1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel5 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel146 = new widget.Label();
        chkBahasa = new widget.CekBox();
        chkPendengaran = new widget.CekBox();
        chkMasalah = new widget.CekBox();
        chkHilangMemori = new widget.CekBox();
        chkTidakAda = new widget.CekBox();
        chkSecara = new widget.CekBox();
        chkTidakDitemukan = new widget.CekBox();
        chkCemas = new widget.CekBox();
        chkEmosi = new widget.CekBox();
        chkKognitif = new widget.CekBox();
        chkMotivasi = new widget.CekBox();
        jLabel147 = new widget.Label();
        jLabel148 = new widget.Label();
        cmbBicara = new widget.ComboBox();
        Tkapan = new widget.TextBox();
        jLabel149 = new widget.Label();
        chkBhsIndonesia = new widget.CekBox();
        cmbBhsIndo = new widget.ComboBox();
        chkDaerah = new widget.CekBox();
        Tdaerah = new widget.TextBox();
        chkBhsInggris = new widget.CekBox();
        cmbBhsInggris = new widget.ComboBox();
        chkBhsLainya = new widget.CekBox();
        TbhsLainya = new widget.TextBox();
        jLabel150 = new widget.Label();
        cmbPerlu = new widget.ComboBox();
        Tperlu = new widget.TextBox();
        jLabel151 = new widget.Label();
        Tpnd = new widget.TextBox();
        jLabel152 = new widget.Label();
        Tagama = new widget.TextBox();
        jLabel153 = new widget.Label();
        cmbNilaiPasien = new widget.ComboBox();
        jLabel154 = new widget.Label();
        cmbKesediaan = new widget.ComboBox();
        jLabel155 = new widget.Label();
        chkProses = new widget.CekBox();
        chkPengobatan = new widget.CekBox();
        chkAlatBantu = new widget.CekBox();
        chkTerapi = new widget.CekBox();
        chkNutrisi = new widget.CekBox();
        chkPotensialLain = new widget.CekBox();
        TPotensialLain = new widget.TextBox();
        jLabel156 = new widget.Label();
        jLabel157 = new widget.Label();
        jLabel158 = new widget.Label();
        jLabel159 = new widget.Label();
        jLabel160 = new widget.Label();
        jLabel161 = new widget.Label();
        jLabel162 = new widget.Label();
        jLabel163 = new widget.Label();
        jLabel164 = new widget.Label();
        jLabel165 = new widget.Label();
        jLabel166 = new widget.Label();
        cmbPenggunaanHerbal = new widget.ComboBox();
        cmbVegetarian = new widget.ComboBox();
        cmbMenolakVaksin = new widget.ComboBox();
        cmbKepercayaan = new widget.ComboBox();
        cmbPuasa = new widget.ComboBox();
        cmbMenolakDilakukan = new widget.ComboBox();
        cmbMenolakPulang = new widget.ComboBox();
        cmbMenolakDilayani = new widget.ComboBox();
        cmbTidakMemakan = new widget.ComboBox();
        cmbLainLain = new widget.ComboBox();
        Scroll1 = new widget.ScrollPane();
        tbPemberian = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel20 = new widget.Label();
        DTPCari3 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel8 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        jLabel9 = new widget.Label();
        LCount1 = new widget.Label();
        internalFrame4 = new widget.InternalFrame();
        ScrollTriase2 = new widget.ScrollPane();
        FormInput1 = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw1 = new widget.TextBox();
        TPasien1 = new widget.TextBox();
        TNoRM1 = new widget.TextBox();
        jLabel11 = new widget.Label();
        TrgRawat1 = new widget.TextBox();
        Scroll = new widget.ScrollPane();
        tbPenilaian = new widget.Table();
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
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pemberian Informasi Dan Edukasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabEdukasi.setBackground(new java.awt.Color(255, 255, 254));
        TabEdukasi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabEdukasi.setName("TabEdukasi"); // NOI18N
        TabEdukasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabEdukasiMouseClicked(evt);
            }
        });

        FormAsesmen.setBorder(null);
        FormAsesmen.setName("FormAsesmen"); // NOI18N
        FormAsesmen.setLayout(new java.awt.BorderLayout(1, 1));

        ScrollTriase1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        ScrollTriase1.setName("ScrollTriase1"); // NOI18N
        ScrollTriase1.setOpaque(true);
        ScrollTriase1.setPreferredSize(new java.awt.Dimension(102, 420));

        FormInput.setBorder(null);
        FormInput.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis..!!");
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 718));
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

        jLabel146.setForeground(new java.awt.Color(0, 0, 0));
        jLabel146.setText("Pengkajian Hambatan :");
        jLabel146.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(0, 66, 150, 23);

        chkBahasa.setBackground(new java.awt.Color(255, 255, 250));
        chkBahasa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBahasa.setForeground(new java.awt.Color(0, 0, 0));
        chkBahasa.setText("Bahasa");
        chkBahasa.setBorderPainted(true);
        chkBahasa.setBorderPaintedFlat(true);
        chkBahasa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBahasa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBahasa.setName("chkBahasa"); // NOI18N
        chkBahasa.setOpaque(false);
        chkBahasa.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBahasa);
        chkBahasa.setBounds(160, 66, 80, 23);

        chkPendengaran.setBackground(new java.awt.Color(255, 255, 250));
        chkPendengaran.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPendengaran.setForeground(new java.awt.Color(0, 0, 0));
        chkPendengaran.setText("Pendengaran");
        chkPendengaran.setBorderPainted(true);
        chkPendengaran.setBorderPaintedFlat(true);
        chkPendengaran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPendengaran.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPendengaran.setName("chkPendengaran"); // NOI18N
        chkPendengaran.setOpaque(false);
        chkPendengaran.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPendengaran);
        chkPendengaran.setBounds(160, 94, 100, 23);

        chkMasalah.setBackground(new java.awt.Color(255, 255, 250));
        chkMasalah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMasalah.setForeground(new java.awt.Color(0, 0, 0));
        chkMasalah.setText("Masalah Penglihatan");
        chkMasalah.setBorderPainted(true);
        chkMasalah.setBorderPaintedFlat(true);
        chkMasalah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMasalah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMasalah.setName("chkMasalah"); // NOI18N
        chkMasalah.setOpaque(false);
        chkMasalah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMasalah);
        chkMasalah.setBounds(160, 122, 130, 23);

        chkHilangMemori.setBackground(new java.awt.Color(255, 255, 250));
        chkHilangMemori.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHilangMemori.setForeground(new java.awt.Color(0, 0, 0));
        chkHilangMemori.setText("Hilang Memori");
        chkHilangMemori.setBorderPainted(true);
        chkHilangMemori.setBorderPaintedFlat(true);
        chkHilangMemori.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHilangMemori.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHilangMemori.setName("chkHilangMemori"); // NOI18N
        chkHilangMemori.setOpaque(false);
        chkHilangMemori.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHilangMemori);
        chkHilangMemori.setBounds(160, 150, 100, 23);

        chkTidakAda.setBackground(new java.awt.Color(255, 255, 250));
        chkTidakAda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTidakAda.setForeground(new java.awt.Color(0, 0, 0));
        chkTidakAda.setText("Tidak Ada Partisipasi Dari Caregive");
        chkTidakAda.setBorderPainted(true);
        chkTidakAda.setBorderPaintedFlat(true);
        chkTidakAda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidakAda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidakAda.setName("chkTidakAda"); // NOI18N
        chkTidakAda.setOpaque(false);
        chkTidakAda.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTidakAda);
        chkTidakAda.setBounds(160, 178, 200, 23);

        chkSecara.setBackground(new java.awt.Color(255, 255, 250));
        chkSecara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSecara.setForeground(new java.awt.Color(0, 0, 0));
        chkSecara.setText("Secara Fisiologi Tidak Mampu Belajar");
        chkSecara.setBorderPainted(true);
        chkSecara.setBorderPaintedFlat(true);
        chkSecara.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSecara.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSecara.setName("chkSecara"); // NOI18N
        chkSecara.setOpaque(false);
        chkSecara.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSecara);
        chkSecara.setBounds(160, 206, 200, 23);

        chkTidakDitemukan.setBackground(new java.awt.Color(255, 255, 250));
        chkTidakDitemukan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTidakDitemukan.setForeground(new java.awt.Color(0, 0, 0));
        chkTidakDitemukan.setText("Tidak Ditemukan Hambatan Belajar");
        chkTidakDitemukan.setBorderPainted(true);
        chkTidakDitemukan.setBorderPaintedFlat(true);
        chkTidakDitemukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidakDitemukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidakDitemukan.setName("chkTidakDitemukan"); // NOI18N
        chkTidakDitemukan.setOpaque(false);
        chkTidakDitemukan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTidakDitemukan);
        chkTidakDitemukan.setBounds(400, 66, 200, 23);

        chkCemas.setBackground(new java.awt.Color(255, 255, 250));
        chkCemas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCemas.setForeground(new java.awt.Color(0, 0, 0));
        chkCemas.setText("Cemas");
        chkCemas.setBorderPainted(true);
        chkCemas.setBorderPaintedFlat(true);
        chkCemas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCemas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCemas.setName("chkCemas"); // NOI18N
        chkCemas.setOpaque(false);
        chkCemas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCemas);
        chkCemas.setBounds(400, 94, 70, 23);

        chkEmosi.setBackground(new java.awt.Color(255, 255, 250));
        chkEmosi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEmosi.setForeground(new java.awt.Color(0, 0, 0));
        chkEmosi.setText("Emosi");
        chkEmosi.setBorderPainted(true);
        chkEmosi.setBorderPaintedFlat(true);
        chkEmosi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEmosi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEmosi.setName("chkEmosi"); // NOI18N
        chkEmosi.setOpaque(false);
        chkEmosi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkEmosi);
        chkEmosi.setBounds(400, 122, 60, 23);

        chkKognitif.setBackground(new java.awt.Color(255, 255, 250));
        chkKognitif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKognitif.setForeground(new java.awt.Color(0, 0, 0));
        chkKognitif.setText("Kognitif");
        chkKognitif.setBorderPainted(true);
        chkKognitif.setBorderPaintedFlat(true);
        chkKognitif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKognitif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKognitif.setName("chkKognitif"); // NOI18N
        chkKognitif.setOpaque(false);
        chkKognitif.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKognitif);
        chkKognitif.setBounds(400, 150, 70, 23);

        chkMotivasi.setBackground(new java.awt.Color(255, 255, 250));
        chkMotivasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMotivasi.setForeground(new java.awt.Color(0, 0, 0));
        chkMotivasi.setText("Motivasi Buruk");
        chkMotivasi.setBorderPainted(true);
        chkMotivasi.setBorderPaintedFlat(true);
        chkMotivasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMotivasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMotivasi.setName("chkMotivasi"); // NOI18N
        chkMotivasi.setOpaque(false);
        chkMotivasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMotivasi);
        chkMotivasi.setBounds(400, 178, 110, 23);

        jLabel147.setForeground(new java.awt.Color(0, 0, 0));
        jLabel147.setText("Pengkajian : ");
        jLabel147.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput.add(jLabel147);
        jLabel147.setBounds(0, 234, 170, 23);

        jLabel148.setForeground(new java.awt.Color(0, 0, 0));
        jLabel148.setText("Bicara : ");
        jLabel148.setName("jLabel148"); // NOI18N
        FormInput.add(jLabel148);
        jLabel148.setBounds(0, 262, 170, 23);

        cmbBicara.setForeground(new java.awt.Color(0, 0, 0));
        cmbBicara.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Serangan Awal Gangguan", "Bicara, Kapan" }));
        cmbBicara.setName("cmbBicara"); // NOI18N
        cmbBicara.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbBicara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBicaraActionPerformed(evt);
            }
        });
        FormInput.add(cmbBicara);
        cmbBicara.setBounds(174, 262, 160, 23);

        Tkapan.setBackground(new java.awt.Color(245, 250, 240));
        Tkapan.setForeground(new java.awt.Color(0, 0, 0));
        Tkapan.setName("Tkapan"); // NOI18N
        Tkapan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkapanKeyPressed(evt);
            }
        });
        FormInput.add(Tkapan);
        Tkapan.setBounds(342, 262, 380, 23);

        jLabel149.setForeground(new java.awt.Color(0, 0, 0));
        jLabel149.setText("Bahasa Sehari - Hari : ");
        jLabel149.setName("jLabel149"); // NOI18N
        FormInput.add(jLabel149);
        jLabel149.setBounds(0, 290, 170, 23);

        chkBhsIndonesia.setBackground(new java.awt.Color(255, 255, 250));
        chkBhsIndonesia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBhsIndonesia.setForeground(new java.awt.Color(0, 0, 0));
        chkBhsIndonesia.setText("Indonesia");
        chkBhsIndonesia.setBorderPainted(true);
        chkBhsIndonesia.setBorderPaintedFlat(true);
        chkBhsIndonesia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBhsIndonesia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBhsIndonesia.setName("chkBhsIndonesia"); // NOI18N
        chkBhsIndonesia.setOpaque(false);
        chkBhsIndonesia.setPreferredSize(new java.awt.Dimension(175, 23));
        chkBhsIndonesia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBhsIndonesiaActionPerformed(evt);
            }
        });
        FormInput.add(chkBhsIndonesia);
        chkBhsIndonesia.setBounds(174, 290, 75, 23);

        cmbBhsIndo.setForeground(new java.awt.Color(0, 0, 0));
        cmbBhsIndo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Aktif", "Pasif" }));
        cmbBhsIndo.setName("cmbBhsIndo"); // NOI18N
        cmbBhsIndo.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBhsIndo);
        cmbBhsIndo.setBounds(255, 290, 60, 23);

        chkDaerah.setBackground(new java.awt.Color(255, 255, 250));
        chkDaerah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDaerah.setForeground(new java.awt.Color(0, 0, 0));
        chkDaerah.setText("Daerah");
        chkDaerah.setBorderPainted(true);
        chkDaerah.setBorderPaintedFlat(true);
        chkDaerah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDaerah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDaerah.setName("chkDaerah"); // NOI18N
        chkDaerah.setOpaque(false);
        chkDaerah.setPreferredSize(new java.awt.Dimension(175, 23));
        chkDaerah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDaerahActionPerformed(evt);
            }
        });
        FormInput.add(chkDaerah);
        chkDaerah.setBounds(174, 318, 75, 23);

        Tdaerah.setBackground(new java.awt.Color(245, 250, 240));
        Tdaerah.setForeground(new java.awt.Color(0, 0, 0));
        Tdaerah.setName("Tdaerah"); // NOI18N
        Tdaerah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdaerahKeyPressed(evt);
            }
        });
        FormInput.add(Tdaerah);
        Tdaerah.setBounds(255, 318, 467, 23);

        chkBhsInggris.setBackground(new java.awt.Color(255, 255, 250));
        chkBhsInggris.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBhsInggris.setForeground(new java.awt.Color(0, 0, 0));
        chkBhsInggris.setText("Inggris");
        chkBhsInggris.setBorderPainted(true);
        chkBhsInggris.setBorderPaintedFlat(true);
        chkBhsInggris.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBhsInggris.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBhsInggris.setName("chkBhsInggris"); // NOI18N
        chkBhsInggris.setOpaque(false);
        chkBhsInggris.setPreferredSize(new java.awt.Dimension(175, 23));
        chkBhsInggris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBhsInggrisActionPerformed(evt);
            }
        });
        FormInput.add(chkBhsInggris);
        chkBhsInggris.setBounds(342, 290, 60, 23);

        cmbBhsInggris.setForeground(new java.awt.Color(0, 0, 0));
        cmbBhsInggris.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Aktif", "Pasif" }));
        cmbBhsInggris.setName("cmbBhsInggris"); // NOI18N
        cmbBhsInggris.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBhsInggris);
        cmbBhsInggris.setBounds(408, 290, 60, 23);

        chkBhsLainya.setBackground(new java.awt.Color(255, 255, 250));
        chkBhsLainya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBhsLainya.setForeground(new java.awt.Color(0, 0, 0));
        chkBhsLainya.setText("Lainnya");
        chkBhsLainya.setBorderPainted(true);
        chkBhsLainya.setBorderPaintedFlat(true);
        chkBhsLainya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBhsLainya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBhsLainya.setName("chkBhsLainya"); // NOI18N
        chkBhsLainya.setOpaque(false);
        chkBhsLainya.setPreferredSize(new java.awt.Dimension(175, 23));
        chkBhsLainya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBhsLainyaActionPerformed(evt);
            }
        });
        FormInput.add(chkBhsLainya);
        chkBhsLainya.setBounds(174, 346, 75, 23);

        TbhsLainya.setBackground(new java.awt.Color(245, 250, 240));
        TbhsLainya.setForeground(new java.awt.Color(0, 0, 0));
        TbhsLainya.setName("TbhsLainya"); // NOI18N
        TbhsLainya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbhsLainyaKeyPressed(evt);
            }
        });
        FormInput.add(TbhsLainya);
        TbhsLainya.setBounds(255, 346, 467, 23);

        jLabel150.setForeground(new java.awt.Color(0, 0, 0));
        jLabel150.setText("Perlu Penerjemah : ");
        jLabel150.setName("jLabel150"); // NOI18N
        FormInput.add(jLabel150);
        jLabel150.setBounds(0, 374, 170, 23);

        cmbPerlu.setForeground(new java.awt.Color(0, 0, 0));
        cmbPerlu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya, Bahasa Asing" }));
        cmbPerlu.setName("cmbPerlu"); // NOI18N
        cmbPerlu.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbPerlu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPerluActionPerformed(evt);
            }
        });
        FormInput.add(cmbPerlu);
        cmbPerlu.setBounds(174, 374, 120, 23);

        Tperlu.setBackground(new java.awt.Color(245, 250, 240));
        Tperlu.setForeground(new java.awt.Color(0, 0, 0));
        Tperlu.setName("Tperlu"); // NOI18N
        Tperlu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TperluKeyPressed(evt);
            }
        });
        FormInput.add(Tperlu);
        Tperlu.setBounds(302, 374, 420, 23);

        jLabel151.setForeground(new java.awt.Color(0, 0, 0));
        jLabel151.setText("Tingkat Pendidikan : ");
        jLabel151.setName("jLabel151"); // NOI18N
        FormInput.add(jLabel151);
        jLabel151.setBounds(0, 402, 170, 23);

        Tpnd.setEditable(false);
        Tpnd.setBackground(new java.awt.Color(245, 250, 240));
        Tpnd.setForeground(new java.awt.Color(0, 0, 0));
        Tpnd.setName("Tpnd"); // NOI18N
        FormInput.add(Tpnd);
        Tpnd.setBounds(174, 402, 60, 23);

        jLabel152.setForeground(new java.awt.Color(0, 0, 0));
        jLabel152.setText("Agama :");
        jLabel152.setName("jLabel152"); // NOI18N
        FormInput.add(jLabel152);
        jLabel152.setBounds(240, 402, 50, 23);

        Tagama.setEditable(false);
        Tagama.setBackground(new java.awt.Color(245, 250, 240));
        Tagama.setForeground(new java.awt.Color(0, 0, 0));
        Tagama.setName("Tagama"); // NOI18N
        FormInput.add(Tagama);
        Tagama.setBounds(295, 402, 250, 23);

        jLabel153.setForeground(new java.awt.Color(0, 0, 0));
        jLabel153.setText("Nilai - Nilai Pasien Dan Budaya : ");
        jLabel153.setName("jLabel153"); // NOI18N
        FormInput.add(jLabel153);
        jLabel153.setBounds(0, 430, 170, 23);

        cmbNilaiPasien.setForeground(new java.awt.Color(0, 0, 0));
        cmbNilaiPasien.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Modern", "Moderat", "Konvensional" }));
        cmbNilaiPasien.setName("cmbNilaiPasien"); // NOI18N
        cmbNilaiPasien.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbNilaiPasien);
        cmbNilaiPasien.setBounds(174, 430, 100, 23);

        jLabel154.setForeground(new java.awt.Color(0, 0, 0));
        jLabel154.setText("Kesediaan Menerima Informasi :");
        jLabel154.setName("jLabel154"); // NOI18N
        FormInput.add(jLabel154);
        jLabel154.setBounds(280, 430, 170, 23);

        cmbKesediaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesediaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKesediaan.setName("cmbKesediaan"); // NOI18N
        cmbKesediaan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKesediaan);
        cmbKesediaan.setBounds(456, 430, 60, 23);

        jLabel155.setForeground(new java.awt.Color(0, 0, 0));
        jLabel155.setText("Potensial Kebutuhan Pembelajaran : ");
        jLabel155.setName("jLabel155"); // NOI18N
        FormInput.add(jLabel155);
        jLabel155.setBounds(0, 458, 200, 23);

        chkProses.setBackground(new java.awt.Color(255, 255, 250));
        chkProses.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkProses.setForeground(new java.awt.Color(0, 0, 0));
        chkProses.setText("Proses Penyakit");
        chkProses.setBorderPainted(true);
        chkProses.setBorderPaintedFlat(true);
        chkProses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkProses.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkProses.setName("chkProses"); // NOI18N
        chkProses.setOpaque(false);
        chkProses.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkProses);
        chkProses.setBounds(205, 458, 120, 23);

        chkPengobatan.setBackground(new java.awt.Color(255, 255, 250));
        chkPengobatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPengobatan.setForeground(new java.awt.Color(0, 0, 0));
        chkPengobatan.setText("Pengobatan/Tindakan");
        chkPengobatan.setBorderPainted(true);
        chkPengobatan.setBorderPaintedFlat(true);
        chkPengobatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPengobatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPengobatan.setName("chkPengobatan"); // NOI18N
        chkPengobatan.setOpaque(false);
        chkPengobatan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPengobatan);
        chkPengobatan.setBounds(205, 486, 134, 23);

        chkAlatBantu.setBackground(new java.awt.Color(255, 255, 250));
        chkAlatBantu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAlatBantu.setForeground(new java.awt.Color(0, 0, 0));
        chkAlatBantu.setText("Alat Bantu Medis");
        chkAlatBantu.setBorderPainted(true);
        chkAlatBantu.setBorderPaintedFlat(true);
        chkAlatBantu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAlatBantu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAlatBantu.setName("chkAlatBantu"); // NOI18N
        chkAlatBantu.setOpaque(false);
        chkAlatBantu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAlatBantu);
        chkAlatBantu.setBounds(205, 514, 120, 23);

        chkTerapi.setBackground(new java.awt.Color(255, 255, 250));
        chkTerapi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTerapi.setForeground(new java.awt.Color(0, 0, 0));
        chkTerapi.setText("Terapi/Obat");
        chkTerapi.setBorderPainted(true);
        chkTerapi.setBorderPaintedFlat(true);
        chkTerapi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTerapi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTerapi.setName("chkTerapi"); // NOI18N
        chkTerapi.setOpaque(false);
        chkTerapi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTerapi);
        chkTerapi.setBounds(350, 458, 100, 23);

        chkNutrisi.setBackground(new java.awt.Color(255, 255, 250));
        chkNutrisi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNutrisi.setForeground(new java.awt.Color(0, 0, 0));
        chkNutrisi.setText("Nutrisi");
        chkNutrisi.setBorderPainted(true);
        chkNutrisi.setBorderPaintedFlat(true);
        chkNutrisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNutrisi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNutrisi.setName("chkNutrisi"); // NOI18N
        chkNutrisi.setOpaque(false);
        chkNutrisi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNutrisi);
        chkNutrisi.setBounds(350, 486, 60, 23);

        chkPotensialLain.setBackground(new java.awt.Color(255, 255, 250));
        chkPotensialLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPotensialLain.setForeground(new java.awt.Color(0, 0, 0));
        chkPotensialLain.setText("Lain-Lain, Jelaskan");
        chkPotensialLain.setBorderPainted(true);
        chkPotensialLain.setBorderPaintedFlat(true);
        chkPotensialLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPotensialLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPotensialLain.setName("chkPotensialLain"); // NOI18N
        chkPotensialLain.setOpaque(false);
        chkPotensialLain.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPotensialLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPotensialLainActionPerformed(evt);
            }
        });
        FormInput.add(chkPotensialLain);
        chkPotensialLain.setBounds(350, 514, 120, 23);

        TPotensialLain.setBackground(new java.awt.Color(245, 250, 240));
        TPotensialLain.setForeground(new java.awt.Color(0, 0, 0));
        TPotensialLain.setName("TPotensialLain"); // NOI18N
        FormInput.add(TPotensialLain);
        TPotensialLain.setBounds(472, 514, 250, 23);

        jLabel156.setForeground(new java.awt.Color(0, 0, 0));
        jLabel156.setText("Identifikasi Nilai - Nilai Dan Kepercayaan :");
        jLabel156.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel156.setName("jLabel156"); // NOI18N
        FormInput.add(jLabel156);
        jLabel156.setBounds(0, 542, 260, 23);

        jLabel157.setForeground(new java.awt.Color(0, 0, 0));
        jLabel157.setText("Penggunaan Herbal / Jamu :");
        jLabel157.setName("jLabel157"); // NOI18N
        FormInput.add(jLabel157);
        jLabel157.setBounds(0, 570, 230, 23);

        jLabel158.setForeground(new java.awt.Color(0, 0, 0));
        jLabel158.setText("Vegetarian :");
        jLabel158.setName("jLabel158"); // NOI18N
        FormInput.add(jLabel158);
        jLabel158.setBounds(0, 598, 230, 23);

        jLabel159.setForeground(new java.awt.Color(0, 0, 0));
        jLabel159.setText("Menolak Vaksinasi :");
        jLabel159.setName("jLabel159"); // NOI18N
        FormInput.add(jLabel159);
        jLabel159.setBounds(0, 626, 230, 23);

        jLabel160.setForeground(new java.awt.Color(0, 0, 0));
        jLabel160.setText("Kepercayaan Terhadap Mistik/Hal-Hal Gaib :");
        jLabel160.setName("jLabel160"); // NOI18N
        FormInput.add(jLabel160);
        jLabel160.setBounds(0, 654, 230, 23);

        jLabel161.setForeground(new java.awt.Color(0, 0, 0));
        jLabel161.setText("Puasa Pada Hari Tertentu :");
        jLabel161.setName("jLabel161"); // NOI18N
        FormInput.add(jLabel161);
        jLabel161.setBounds(0, 682, 230, 23);

        jLabel162.setForeground(new java.awt.Color(0, 0, 0));
        jLabel162.setText("Menolak Dilakukan Transfusi Darah :");
        jLabel162.setName("jLabel162"); // NOI18N
        FormInput.add(jLabel162);
        jLabel162.setBounds(300, 570, 330, 23);

        jLabel163.setForeground(new java.awt.Color(0, 0, 0));
        jLabel163.setText("Menolak Pulang Hari Tertentu :");
        jLabel163.setName("jLabel163"); // NOI18N
        FormInput.add(jLabel163);
        jLabel163.setBounds(300, 598, 330, 23);

        jLabel164.setForeground(new java.awt.Color(0, 0, 0));
        jLabel164.setText("Menolak Dilayani Oleh Petugas Laki-Laki Pada Pasien Perempuan :");
        jLabel164.setName("jLabel164"); // NOI18N
        FormInput.add(jLabel164);
        jLabel164.setBounds(300, 626, 330, 23);

        jLabel165.setForeground(new java.awt.Color(0, 0, 0));
        jLabel165.setText("Tidak Memakan Suatu Jenis Makanan Tertentu, Misal Daging Sapi, Ikan Tidak Bersisik, dll :");
        jLabel165.setName("jLabel165"); // NOI18N
        FormInput.add(jLabel165);
        jLabel165.setBounds(300, 654, 440, 23);

        jLabel166.setForeground(new java.awt.Color(0, 0, 0));
        jLabel166.setText("Lain - Lain :");
        jLabel166.setName("jLabel166"); // NOI18N
        FormInput.add(jLabel166);
        jLabel166.setBounds(300, 682, 330, 23);

        cmbPenggunaanHerbal.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenggunaanHerbal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPenggunaanHerbal.setName("cmbPenggunaanHerbal"); // NOI18N
        cmbPenggunaanHerbal.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbPenggunaanHerbal);
        cmbPenggunaanHerbal.setBounds(236, 570, 60, 23);

        cmbVegetarian.setForeground(new java.awt.Color(0, 0, 0));
        cmbVegetarian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbVegetarian.setName("cmbVegetarian"); // NOI18N
        cmbVegetarian.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbVegetarian);
        cmbVegetarian.setBounds(236, 598, 60, 23);

        cmbMenolakVaksin.setForeground(new java.awt.Color(0, 0, 0));
        cmbMenolakVaksin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMenolakVaksin.setName("cmbMenolakVaksin"); // NOI18N
        cmbMenolakVaksin.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMenolakVaksin);
        cmbMenolakVaksin.setBounds(236, 626, 60, 23);

        cmbKepercayaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKepercayaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKepercayaan.setName("cmbKepercayaan"); // NOI18N
        cmbKepercayaan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKepercayaan);
        cmbKepercayaan.setBounds(236, 654, 60, 23);

        cmbPuasa.setForeground(new java.awt.Color(0, 0, 0));
        cmbPuasa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPuasa.setName("cmbPuasa"); // NOI18N
        cmbPuasa.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbPuasa);
        cmbPuasa.setBounds(236, 682, 60, 23);

        cmbMenolakDilakukan.setForeground(new java.awt.Color(0, 0, 0));
        cmbMenolakDilakukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMenolakDilakukan.setName("cmbMenolakDilakukan"); // NOI18N
        cmbMenolakDilakukan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMenolakDilakukan);
        cmbMenolakDilakukan.setBounds(635, 570, 60, 23);

        cmbMenolakPulang.setForeground(new java.awt.Color(0, 0, 0));
        cmbMenolakPulang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMenolakPulang.setName("cmbMenolakPulang"); // NOI18N
        cmbMenolakPulang.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMenolakPulang);
        cmbMenolakPulang.setBounds(635, 598, 60, 23);

        cmbMenolakDilayani.setForeground(new java.awt.Color(0, 0, 0));
        cmbMenolakDilayani.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMenolakDilayani.setName("cmbMenolakDilayani"); // NOI18N
        cmbMenolakDilayani.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMenolakDilayani);
        cmbMenolakDilayani.setBounds(635, 626, 60, 23);

        cmbTidakMemakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbTidakMemakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbTidakMemakan.setName("cmbTidakMemakan"); // NOI18N
        cmbTidakMemakan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbTidakMemakan);
        cmbTidakMemakan.setBounds(747, 654, 60, 23);

        cmbLainLain.setForeground(new java.awt.Color(0, 0, 0));
        cmbLainLain.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbLainLain.setName("cmbLainLain"); // NOI18N
        cmbLainLain.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbLainLain);
        cmbLainLain.setBounds(635, 682, 60, 23);

        ScrollTriase1.setViewportView(FormInput);

        FormAsesmen.add(ScrollTriase1, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbPemberian.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemberian.setName("tbPemberian"); // NOI18N
        tbPemberian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemberianMouseClicked(evt);
            }
        });
        tbPemberian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemberianKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbPemberian);

        FormAsesmen.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Tgl. Pemberian :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(jLabel20);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-03-2025" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari3);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d.");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel22);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-03-2025" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari4);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Key Word :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel8);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass10.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('3');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+3");
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
        panelGlass10.add(BtnCari1);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Record :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel9);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount1);

        FormAsesmen.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        TabEdukasi.addTab("Informasi Edukasi", FormAsesmen);

        internalFrame4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        ScrollTriase2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        ScrollTriase2.setName("ScrollTriase2"); // NOI18N
        ScrollTriase2.setOpaque(true);
        ScrollTriase2.setPreferredSize(new java.awt.Dimension(102, 420));

        FormInput1.setBorder(null);
        FormInput1.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis..!!");
        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(870, 718));
        FormInput1.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput1.add(jLabel10);
        jLabel10.setBounds(0, 10, 110, 23);

        TNoRw1.setEditable(false);
        TNoRw1.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw1.setName("TNoRw1"); // NOI18N
        FormInput1.add(TNoRw1);
        TNoRw1.setBounds(114, 10, 122, 23);

        TPasien1.setEditable(false);
        TPasien1.setBackground(new java.awt.Color(245, 250, 240));
        TPasien1.setForeground(new java.awt.Color(0, 0, 0));
        TPasien1.setName("TPasien1"); // NOI18N
        FormInput1.add(TPasien1);
        TPasien1.setBounds(315, 10, 407, 23);

        TNoRM1.setEditable(false);
        TNoRM1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM1.setName("TNoRM1"); // NOI18N
        FormInput1.add(TNoRM1);
        TNoRM1.setBounds(240, 10, 70, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Ruang Rawat : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput1.add(jLabel11);
        jLabel11.setBounds(0, 38, 110, 23);

        TrgRawat1.setEditable(false);
        TrgRawat1.setBackground(new java.awt.Color(245, 250, 240));
        TrgRawat1.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat1.setName("TrgRawat1"); // NOI18N
        FormInput1.add(TrgRawat1);
        TrgRawat1.setBounds(114, 38, 608, 23);

        ScrollTriase2.setViewportView(FormInput1);

        internalFrame4.add(ScrollTriase2, java.awt.BorderLayout.PAGE_START);

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPenilaian.setAutoCreateRowSorter(true);
        tbPenilaian.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPenilaian.setName("tbPenilaian"); // NOI18N
        tbPenilaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPenilaianMouseClicked(evt);
            }
        });
        tbPenilaian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPenilaianKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPenilaian);

        internalFrame4.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Penilaian :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-03-2025" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-03-2025" }));
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

        TabEdukasi.addTab("Penilaian Pemberian Pendidikan Kesehatan", internalFrame4);

        internalFrame1.add(TabEdukasi, java.awt.BorderLayout.CENTER);

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
            cekDataPemberian();
//            if (Sequel.menyimpantf("asesmen_keperawatan_perinatologi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
//                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
//                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 183, new String[]{
//                        TNoRw.getText(), TrgRawat.getText(), cmbBicara.getSelectedItem().toString(), TlainSumber.getText(), cmbRujukan.getSelectedItem().toString(),
//                        cmbJnsRujukan.getSelectedItem().toString(), TdiagnosaRujukan.getText(), Tkeluhan.getText(), TnmIdentitas.getText(), cmbPendidikan.getSelectedItem().toString(),
//                        Tpekerjaan.getText(), cmbAgama.getSelectedItem().toString(), Talamat.getText(), Tbbl.getText(), Tpb.getText(), Tlk.getText(), Tld.getText(),
//                        Tlp.getText(), Tll.getText(), Tkk.getText(), Tnadi.getText(), Trr.getText(), Tsuhu.getText(), jernih, keruh, lumpur, hijau, berbau, kering,
//                        cmbAnus.getSelectedItem().toString(), cmbBab.getSelectedItem().toString(), cmbBak.getSelectedItem().toString(), Tkelainan.getText(), TanakKe.getText(),
//                        TumurHamil.getText(), cmbRiwPenyakitIbu.getSelectedItem().toString(), hipertensi, dm, pms, tbc, asma, hepB, lainRiwayatIbu, TlainRiwayat.getText(),
//                        cmbMasih.getSelectedItem().toString(), Tobat.getText(), TdiagnosaIbu.getText(), Valid.SetTgl(TtglLahir.getSelectedItem() + ""), jamlahiribu,
//                        TkeadaanSaat.getText(), Tas.getText(), spontan, vakum, forcep, sectio, lainCara, TlainCara.getText(), segar, layu, simpul, ibuDemam, kpd24, ketuban, 
//                        chorio, fetal, kpd12, asfiksia, bblr, isk, uk, gemeli, keputihan, suhuIbu, cmbRiwAlergi.getSelectedItem().toString(), obat, TobatAlergi.getText(), 
//                        makanan, TmakananAlergi.getText(), lainRiwayatAlergi, TlainyaAlergi.getText(), Treaksi.getText(), cmbMasalah.getSelectedItem().toString(), 
//                        cmbAdaPerkawinan.getSelectedItem().toString(), TlainPerkawinan.getText(), cmbMengalami.getSelectedItem().toString(), cmbAdaMengalami.getSelectedItem().toString(), 
//                        cmbDialami.getSelectedItem().toString(), cmbTrauma.getSelectedItem().toString(), TjelaskanTrauma.getText(), cmbGangguan.getSelectedItem().toString(), 
//                        cmbKonsultasi.getSelectedItem().toString(), cmbPenerimaan.getSelectedItem().toString(), suami, orangTua, keluarga, lainDukungan, TlainDukungan.getText(), 
//                        cmbStatusNikah.getSelectedItem().toString(), TkaliMenikah.getText(), cmbHubungan.getSelectedItem().toString(), cmbTinggal.getSelectedItem().toString(), 
//                        TlainTinggal.getText(), cmbTempat.getSelectedItem().toString(), TlainTempat.getText(), TnmKerabat.getText(), ThubKerabat.getText(), TtelpKerabat.getText(), 
//                        TkegiatanAgama.getText(), TkegiatanSpiritual.getText(), cmbNyeri.getSelectedItem().toString(), cmbCrying.getSelectedItem().toString(), 
//                        cmbRequires.getSelectedItem().toString(), cmbIncreased.getSelectedItem().toString(), cmbExpresion.getSelectedItem().toString(), 
//                        cmbSleepless.getSelectedItem().toString(), TkesimpulanNyeri.getText(), sikap0, sikap1, sikap2, sikap3, sikap4, persegi_1, persegi0, persegi1, persegi2, 
//                        persegi3, persegi4, rekoli0, rekoli1, rekoli2, rekoli3, rekoli4, sudut_1, sudut0, sudut1, sudut2, sudut3, sudut4, sudut5, tanda_1, tanda0, tanda1, tanda2, 
//                        tanda3, tanda4, tumit_1, tumit0, tumit1, tumit2, tumit3, tumit4, cmbFisikKulit.getSelectedItem().toString(), cmbFisikPayudara.getSelectedItem().toString(), 
//                        cmbFisikMata.getSelectedItem().toString(), cmbFisikGenPria.getSelectedItem().toString(), cmbFisikGenWanita.getSelectedItem().toString(), 
//                        cmbFisikLanugo.getSelectedItem().toString(), cmbFisikPlantar.getSelectedItem().toString(), hipotermi, resikoHipotermi, hipertermi, pola, nyeri, kerusakan, 
//                        resikoKerusakan, kebutuhan, ikterik, gangguan, bersihan, resikoBersihan, perubahan, kelebihan, resikoKelebihan, resikoKebutuhan, TmasalahLain.getText(), 
//                        Valid.SetTgl(TtglRencana1.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), 
//                        Valid.SetTgl(TtglRencana2.getSelectedItem() + ""), cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), nip, 
//                        Sequel.cariIsi("select now()"), cekTglLahir, Tspo.getText()
//                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Assesmen Keperawatan Perinatologi", "Simpan");
                TabEdukasi.setSelectedIndex(1);
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();                
//            }
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
        if (tbPenilaian.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapus();
            } else {
                if (nip.equals(akses.getkode())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh perawat yang bernama " + tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 10).toString() + " ..!!");
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
            if (tbPenilaian.getSelectedRow() > -1) {
                if (akses.getadmin() == true) {
                    ganti();
                } else {
                    if (nip.equals(akses.getkode())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh perawat yang bernama " + tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 10).toString() + " ..!!");
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
        if (tbPenilaian.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));

            Valid.MyReport("rptAsesmenKeperawatanPerinatologi2.jasper", "report", "::[ Asesmen Keperawatan Perinatologi Hal. 2 ]::",
                    "SELECT now() tanggal", param);
            Valid.MyReport("rptAsesmenKeperawatanPerinatologi1.jasper", "report", "::[ Asesmen Keperawatan Perinatologi Hal. 1 ]::",
                    "SELECT now() tanggal", param);            
            
            TabEdukasi.setSelectedIndex(1);
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
            TabEdukasi.setSelectedIndex(1);
            tampil();
        } else if (Sequel.cariInteger("select count(-1) from asesmen_keperawatan_perinatologi where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabEdukasi.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void TabEdukasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabEdukasiMouseClicked
        if (TabEdukasi.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabEdukasiMouseClicked

    private void tbPenilaianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPenilaianKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPenilaianKeyPressed

    private void tbPenilaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPenilaianMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {                
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbPenilaian.getSelectedColumn() == 0)) {
                TabEdukasi.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tbPenilaianMouseClicked

    private void cmbBicaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBicaraActionPerformed
        Tkapan.setText("");
        if (cmbBicara.getSelectedIndex() == 3) {
            Tkapan.setEnabled(true);
            Tkapan.requestFocus();
        } else {
            Tkapan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbBicaraActionPerformed

    private void chkBhsIndonesiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBhsIndonesiaActionPerformed
        cmbBhsIndo.setSelectedIndex(0);
        if (chkBhsIndonesia.isSelected() == true) {
            cmbBhsIndo.setEnabled(true);
            cmbBhsIndo.requestFocus();
        } else {
            cmbBhsIndo.setEnabled(false);
        }
    }//GEN-LAST:event_chkBhsIndonesiaActionPerformed

    private void TkapanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkapanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkBhsIndonesia.requestFocus();
        }
    }//GEN-LAST:event_TkapanKeyPressed

    private void chkDaerahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDaerahActionPerformed
        Tdaerah.setText("");
        if (chkDaerah.isSelected() == true) {
            Tdaerah.setEnabled(true);
            Tdaerah.requestFocus();
        } else {
            Tdaerah.setEnabled(false);
        }
    }//GEN-LAST:event_chkDaerahActionPerformed

    private void chkBhsInggrisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBhsInggrisActionPerformed
        cmbBhsInggris.setSelectedIndex(0);
        if (chkBhsInggris.isSelected() == true) {
            cmbBhsInggris.setEnabled(true);
            cmbBhsInggris.requestFocus();
        } else {
            cmbBhsInggris.setEnabled(false);
        }
    }//GEN-LAST:event_chkBhsInggrisActionPerformed

    private void TdaerahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdaerahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkBhsLainya.requestFocus();
        }
    }//GEN-LAST:event_TdaerahKeyPressed

    private void chkBhsLainyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBhsLainyaActionPerformed
        TbhsLainya.setText("");
        if (chkBhsLainya.isSelected() == true) {
            TbhsLainya.setEnabled(true);
            TbhsLainya.requestFocus();
        } else {
            TbhsLainya.setEnabled(false);
        }
    }//GEN-LAST:event_chkBhsLainyaActionPerformed

    private void TbhsLainyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbhsLainyaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPerlu.requestFocus();
        }
    }//GEN-LAST:event_TbhsLainyaKeyPressed

    private void cmbPerluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPerluActionPerformed
        Tperlu.setText("");
        if (cmbPerlu.getSelectedIndex() == 2) {
            Tperlu.setEnabled(true);
            Tperlu.requestFocus();
        } else {
            Tperlu.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPerluActionPerformed

    private void chkPotensialLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPotensialLainActionPerformed
        TPotensialLain.setText("");
        if (chkPotensialLain.isSelected() == true) {
            TPotensialLain.setEnabled(true);
            TPotensialLain.requestFocus();
        } else {
            TPotensialLain.setEnabled(false);
        }
    }//GEN-LAST:event_chkPotensialLainActionPerformed

    private void TperluKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TperluKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbNilaiPasien.requestFocus();
        }
    }//GEN-LAST:event_TperluKeyPressed

    private void tbPemberianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemberianMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPemberianMouseClicked

    private void tbPemberianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemberianKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPemberianKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari1KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPemberianInformasiEdukasi dialog = new RMPemberianInformasiEdukasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.InternalFrame FormAsesmen;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.Label LCount;
    private widget.Label LCount1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane ScrollTriase1;
    private widget.ScrollPane ScrollTriase2;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRM1;
    private widget.TextBox TNoRw;
    private widget.TextBox TNoRw1;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private widget.TextBox TPotensialLain;
    private javax.swing.JTabbedPane TabEdukasi;
    private widget.TextBox Tagama;
    private widget.TextBox TbhsLainya;
    private widget.TextBox Tdaerah;
    private widget.TextBox Tkapan;
    private widget.TextBox Tperlu;
    private widget.TextBox Tpnd;
    private widget.TextBox TrgRawat;
    private widget.TextBox TrgRawat1;
    public widget.CekBox chkAlatBantu;
    public widget.CekBox chkBahasa;
    public widget.CekBox chkBhsIndonesia;
    public widget.CekBox chkBhsInggris;
    public widget.CekBox chkBhsLainya;
    public widget.CekBox chkCemas;
    public widget.CekBox chkDaerah;
    public widget.CekBox chkEmosi;
    public widget.CekBox chkHilangMemori;
    public widget.CekBox chkKognitif;
    public widget.CekBox chkMasalah;
    public widget.CekBox chkMotivasi;
    public widget.CekBox chkNutrisi;
    public widget.CekBox chkPendengaran;
    public widget.CekBox chkPengobatan;
    public widget.CekBox chkPotensialLain;
    public widget.CekBox chkProses;
    public widget.CekBox chkSecara;
    public widget.CekBox chkTerapi;
    public widget.CekBox chkTidakAda;
    public widget.CekBox chkTidakDitemukan;
    private widget.ComboBox cmbBhsIndo;
    private widget.ComboBox cmbBhsInggris;
    private widget.ComboBox cmbBicara;
    private widget.ComboBox cmbKepercayaan;
    private widget.ComboBox cmbKesediaan;
    private widget.ComboBox cmbLainLain;
    private widget.ComboBox cmbMenolakDilakukan;
    private widget.ComboBox cmbMenolakDilayani;
    private widget.ComboBox cmbMenolakPulang;
    private widget.ComboBox cmbMenolakVaksin;
    private widget.ComboBox cmbNilaiPasien;
    private widget.ComboBox cmbPenggunaanHerbal;
    private widget.ComboBox cmbPerlu;
    private widget.ComboBox cmbPuasa;
    private widget.ComboBox cmbTidakMemakan;
    private widget.ComboBox cmbVegetarian;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
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
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbPemberian;
    private widget.Table tbPenilaian;
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
                        rs.getString("cek_tgllahir_ibu"),
                        rs.getString("spo2")
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
        chkBahasa.setSelected(false);
        chkPendengaran.setSelected(false);
        chkMasalah.setSelected(false);
        chkHilangMemori.setSelected(false);
        chkTidakAda.setSelected(false);
        chkSecara.setSelected(false);
        chkTidakDitemukan.setSelected(false);
        chkCemas.setSelected(false);
        chkEmosi.setSelected(false);
        chkKognitif.setSelected(false);
        chkMotivasi.setSelected(false);
        cmbBicara.setSelectedIndex(0);        
        Tkapan.setText("");
        Tkapan.setEnabled(false);
        chkBhsIndonesia.setSelected(false);
        cmbBhsIndo.setSelectedIndex(0);
        cmbBhsIndo.setEnabled(false);
        chkBhsInggris.setSelected(false);
        cmbBhsInggris.setSelectedIndex(0);
        cmbBhsInggris.setEnabled(false);
        chkDaerah.setSelected(false);
        Tdaerah.setText("");
        Tdaerah.setEnabled(false);
        chkBhsLainya.setSelected(false);
        TbhsLainya.setText("");
        TbhsLainya.setEnabled(false);
        cmbPerlu.setSelectedIndex(0);
        Tperlu.setText("");
        Tperlu.setEnabled(false);
        cmbNilaiPasien.setSelectedIndex(0);
        cmbKesediaan.setSelectedIndex(0);
        chkProses.setSelected(false);
        chkPengobatan.setSelected(false);
        chkAlatBantu.setSelected(false);
        chkTerapi.setSelected(false);
        chkNutrisi.setSelected(false);
        chkPotensialLain.setSelected(false);
        TPotensialLain.setText("");
        TPotensialLain.setEnabled(false);
        cmbPenggunaanHerbal.setSelectedIndex(0);
        cmbVegetarian.setSelectedIndex(0);
        cmbMenolakVaksin.setSelectedIndex(0);
        cmbKepercayaan.setSelectedIndex(0);
        cmbPuasa.setSelectedIndex(0);
        cmbMenolakDilakukan.setSelectedIndex(0);
        cmbMenolakPulang.setSelectedIndex(0);
        cmbMenolakDilayani.setSelectedIndex(0);
        cmbTidakMemakan.setSelectedIndex(0);
        cmbLainLain.setSelectedIndex(0);
    }
    
    public void setData(String norwt, String rgrawat) {
        TNoRw.setText(norwt);
        TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norwt + "'"));
        TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        TrgRawat.setText(rgrawat);
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(new Date());
        TCari.setText(norwt);
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnPrint.setEnabled(akses.getcppt());
        BtnEdit.setEnabled(akses.getcppt());
        
//        if (akses.getjml2() >= 1) {
//            nip = akses.getkode();            
//            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPerawat, nip);
//            if (TnmPerawat.getText().equals("")) {
//                nip = "-";
//                TnmPerawat.setText("-");
//            }
//        }  
    }
    
    private void getData() {
        variabelBersih();
        if (tbPenilaian.getSelectedRow() != -1) {
            TNoRw.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 0).toString());
            TNoRM.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 1).toString());
            TPasien.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 5).toString());
//            Valid.SetTgl(TtglRencana1, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 184).toString());
            
            dataCekPemberian();
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from asesmen_keperawatan_perinatologi where no_rawat=?", 1, new String[]{
                tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 0).toString()
            }) == true) {
                tampil();
                emptTeks();
                TabEdukasi.setSelectedIndex(1);              
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        } else {
            tampil();
            emptTeks();
        }
    }
    
    private void ganti() {
        cekDataPemberian();
//        if (Sequel.mengedittf("asesmen_keperawatan_perinatologi", "no_rawat=?", "sumber_data=?, ket_lain_sumber_data=?, rujukan=?, jenis_rujukan=?, "
//                + "diagnosa_rujukan=?, keluhan=?, nm_identitas=?, pendidikan=?, pekerjaan=?, agama=?, alamat=?, bbl=?, pb=?, lk=?, ld=?, lp=?, ll=?, kk=?, nadi=?, rr=?, "
//                + "suhu=?, jernih=?, keruh=?, lumpur=?, hijau=?, berbau=?, kering=?, anus=?, bab=?, bak=?, kelainan_bawaan=?, anak_ke=?, umur_kehamilan=?, riwayat_penyakit_ibu=?, "
//                + "hipertensi=?, dm=?, pms=?, tbc=?, asma=?, hepatitis_b=?, lain_riwayat=?, ket_lain_riwayat=?, masih_pengobatan=?, obat=?, diagnosa_ibu=?, tgl_lahir=?, jam_lahir=?, "
//                + "keadaan_saat_lahir=?, ket_as=?, spontan=?, vakum=?, forcep=?, sectio=?, lain_persalinan=?, ket_lain_persalinan=?, segar=?, layu=?, simpul=?, ibu_demam=?, kpd24=?, "
//                + "ketuban=?, chorio=?, fetal=?, kpd12=?, asfiksia=?, bblr=?, isk=?, uk=?, gameli=?, keputihan=?, suhu_ibu=?, riwayat_alergi=?, obat_riwayat=?, ket_obat_riwayat=?, "
//                + "makanan=?, ket_makanan=?, lain_riwayat_alergi=?, ket_lain_riwayat_alergi=?, reaksi=?, masalah_perkawinan=?, ada_perkawinan=?, perkawinan_lain=?, mengalami_kekerasan=?, "
//                + "kekerasan_fisik=?, mencederai=?, trauma=?, ket_trauma=?, gangguan_tidur=?, konsultasi_psikiater=?, penerimaan_terhadap_kondisi=?, dukungan_suami=?, dukungan_orang_tua=?, "
//                + "dukungan_keluarga=?, dukungan_lain=?, ket_dukungan_lain=?, status_pernikahan=?, menikah=?, hubungan_pasien=?, tinggal_bersama=?, ket_lain_tinggal_bersama=?, tempat_tinggal=?, "
//                + "ket_lain_tempat_tinggal=?, nm_kerabat=?, hubungan_kerabat=?, no_tlp=?, kegiatan_keagamaan=?, kegiatan_spriritual=?, nyeri=?, crying=?, requires=?, increased=?, expresion=?, "
//                + "sleepless=?, kesimpulan_penilaian=?, sikap_tubuh1=?, sikap_tubuh2=?, sikap_tubuh3=?, sikap_tubuh4=?, sikap_tubuh5=?, persegi_jendela1=?, persegi_jendela2=?, persegi_jendela3=?, "
//                + "persegi_jendela4=?, persegi_jendela5=?, persegi_jendela6=?, rekoli_lengan1=?, rekoli_lengan2=?, rekoli_lengan3=?, rekoli_lengan4=?, rekoli_lengan5=?, sudut1=?, sudut2=?, "
//                + "sudut3=?, sudut4=?, sudut5=?, sudut6=?, sudut7=?, tanda_selempang1=?, tanda_selempang2=?, tanda_selempang3=?, tanda_selempang4=?, tanda_selempang5=?, tanda_selempang6=?, "
//                + "tumit1=?, tumit2=?, tumit3=?, tumit4=?, tumit5=?, tumit6=?, fisik_kulit=?, fisik_payudara=?, fisik_mata=?, fisik_genital_pria=?, fisik_genital_wanita=?, fisik_lanugo=?, "
//                + "fisik_plantar=?, hipotermi=?, resiko_hipotermi=?, hipertermi=?, pola_nafas=?, nyeri_masalah_keperawatan=?, kerusakan=?, resiko_kerusakan=?, kebutuhan=?, ikterik=?, "
//                + "gangguan_motilitas=?, bersihan=?, resiko_bersihan=?, perubahan_perfusi=?, kelebihan=?, resiko_kelebihan=?, resiko_kebutuhan=?, masalah_keperawatan_lain=?, tgl_rencana1=?, "
//                + "jam_rencana1=?, tgl_rencana2=?, jam_rencana2=?, nip_perawat=?, cek_tgllahir_ibu=?, spo2=?", 181, new String[]{
//                    cmbBicara.getSelectedItem().toString(), TlainSumber.getText(), cmbRujukan.getSelectedItem().toString(),
//                    cmbJnsRujukan.getSelectedItem().toString(), TdiagnosaRujukan.getText(), Tkeluhan.getText(), TnmIdentitas.getText(), cmbPendidikan.getSelectedItem().toString(),
//                    Tpekerjaan.getText(), cmbAgama.getSelectedItem().toString(), Talamat.getText(), Tbbl.getText(), Tpb.getText(), Tlk.getText(), Tld.getText(),
//                    Tlp.getText(), Tll.getText(), Tkk.getText(), Tnadi.getText(), Trr.getText(), Tsuhu.getText(), jernih, keruh, lumpur, hijau, berbau, kering,
//                    cmbAnus.getSelectedItem().toString(), cmbBab.getSelectedItem().toString(), cmbBak.getSelectedItem().toString(), Tkelainan.getText(), TanakKe.getText(),
//                    TumurHamil.getText(), cmbRiwPenyakitIbu.getSelectedItem().toString(), hipertensi, dm, pms, tbc, asma, hepB, lainRiwayatIbu, TlainRiwayat.getText(),
//                    cmbMasih.getSelectedItem().toString(), Tobat.getText(), TdiagnosaIbu.getText(), Valid.SetTgl(TtglLahir.getSelectedItem() + ""), jamlahiribu, 
//                    TkeadaanSaat.getText(), Tas.getText(), spontan, vakum, forcep, sectio, lainCara, TlainCara.getText(), segar, layu, simpul, ibuDemam, kpd24, ketuban, 
//                    chorio, fetal, kpd12, asfiksia, bblr, isk, uk, gemeli, keputihan, suhuIbu, cmbRiwAlergi.getSelectedItem().toString(), obat, TobatAlergi.getText(), makanan, 
//                    TmakananAlergi.getText(), lainRiwayatAlergi, TlainyaAlergi.getText(), Treaksi.getText(), cmbMasalah.getSelectedItem().toString(), cmbAdaPerkawinan.getSelectedItem().toString(), 
//                    TlainPerkawinan.getText(), cmbMengalami.getSelectedItem().toString(), cmbAdaMengalami.getSelectedItem().toString(), cmbDialami.getSelectedItem().toString(), 
//                    cmbTrauma.getSelectedItem().toString(), TjelaskanTrauma.getText(), cmbGangguan.getSelectedItem().toString(), cmbKonsultasi.getSelectedItem().toString(), 
//                    cmbPenerimaan.getSelectedItem().toString(), suami, orangTua, keluarga, lainDukungan, TlainDukungan.getText(), cmbStatusNikah.getSelectedItem().toString(), 
//                    TkaliMenikah.getText(), cmbHubungan.getSelectedItem().toString(), cmbTinggal.getSelectedItem().toString(), TlainTinggal.getText(), cmbTempat.getSelectedItem().toString(),
//                    TlainTempat.getText(), TnmKerabat.getText(), ThubKerabat.getText(), TtelpKerabat.getText(), TkegiatanAgama.getText(), TkegiatanSpiritual.getText(),
//                    cmbNyeri.getSelectedItem().toString(), cmbCrying.getSelectedItem().toString(), cmbRequires.getSelectedItem().toString(), cmbIncreased.getSelectedItem().toString(),
//                    cmbExpresion.getSelectedItem().toString(), cmbSleepless.getSelectedItem().toString(), TkesimpulanNyeri.getText(), sikap0, sikap1, sikap2, sikap3, sikap4,
//                    persegi_1, persegi0, persegi1, persegi2, persegi3, persegi4, rekoli0, rekoli1, rekoli2, rekoli3, rekoli4, sudut_1, sudut0, sudut1, sudut2, sudut3, sudut4, sudut5,
//                    tanda_1, tanda0, tanda1, tanda2, tanda3, tanda4, tumit_1, tumit0, tumit1, tumit2, tumit3, tumit4, cmbFisikKulit.getSelectedItem().toString(),
//                    cmbFisikPayudara.getSelectedItem().toString(), cmbFisikMata.getSelectedItem().toString(), cmbFisikGenPria.getSelectedItem().toString(),
//                    cmbFisikGenWanita.getSelectedItem().toString(), cmbFisikLanugo.getSelectedItem().toString(), cmbFisikPlantar.getSelectedItem().toString(),
//                    hipotermi, resikoHipotermi, hipertermi, pola, nyeri, kerusakan, resikoKerusakan, kebutuhan, ikterik, gangguan, bersihan, resikoBersihan, perubahan,
//                    kelebihan, resikoKelebihan, resikoKebutuhan, TmasalahLain.getText(), Valid.SetTgl(TtglRencana1.getSelectedItem() + ""),
//                    cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), Valid.SetTgl(TtglRencana2.getSelectedItem() + ""),
//                    cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), nip, cekTglLahir, Tspo.getText(),
//                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
//                }) == true) {

            Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Assesmen Keperawatan Perinatologi", "Ganti");
            TabEdukasi.setSelectedIndex(1);
            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
//        }
    }
    
    private void cekDataPemberian() {
        if (chkBahasa.isSelected() == true) {
            bahasa = "ya";
        } else {
            bahasa = "tidak";
        }
        
        if (chkPendengaran.isSelected() == true) {
            pendengaran = "ya";
        } else {
            pendengaran = "tidak";
        }
        
        if (chkMasalah.isSelected() == true) {
            masalahPenglihatan = "ya";
        } else {
            masalahPenglihatan = "tidak";
        }
        
        if (chkHilangMemori.isSelected() == true) {
            hilangMemori = "ya";
        } else {
            hilangMemori = "tidak";
        }
        
        if (chkTidakAda.isSelected() == true) {
            tidakAdaPartisipasi = "ya";
        } else {
            tidakAdaPartisipasi = "tidak";
        }
        
        if (chkSecara.isSelected() == true) {
            secaraFisiologi = "ya";
        } else {
            secaraFisiologi = "tidak";
        }
        
        if (chkTidakDitemukan.isSelected() == true) {
            tidakDitemukanHambatan = "ya";
        } else {
            tidakDitemukanHambatan = "tidak";
        }
        
        if (chkCemas.isSelected() == true) {
            cemas = "ya";
        } else {
            cemas = "tidak";
        }
        
        if (chkEmosi.isSelected() == true) {
            emosi = "ya";
        } else {
            emosi = "tidak";
        }
        
        if (chkKognitif.isSelected() == true) {
            kognitif = "ya";
        } else {
            kognitif = "tidak";
        }
        
        if (chkMotivasi.isSelected() == true) {
            motifasiBuruk = "ya";
        } else {
            motifasiBuruk = "tidak";
        }
        
        if (chkBhsIndonesia.isSelected() == true) {
            bahasaIndonesia = "ya";
        } else {
            bahasaIndonesia = "tidak";
        }
        
        if (chkDaerah.isSelected() == true) {
            bahasaDaerah = "ya";
        } else {
            bahasaDaerah = "tidak";
        }
        
        if (chkBhsInggris.isSelected() == true) {
            bahasaInggris = "ya";
        } else {
            bahasaInggris = "tidak";
        }
        
        if (chkBhsLainya.isSelected() == true) {
            bahasaLainnya = "ya";
        } else {
            bahasaLainnya = "tidak";
        }
        
        if (chkProses.isSelected() == true) {
            prosesPenyakit = "ya";
        } else {
            prosesPenyakit = "tidak";
        }
        
        if (chkPengobatan.isSelected() == true) {
            pengobatan = "ya";
        } else {
            pengobatan = "tidak";
        }
        
        if (chkAlatBantu.isSelected() == true) {
            alatBantuMedis = "ya";
        } else {
            alatBantuMedis = "tidak";
        }
        
        if (chkPotensialLain.isSelected() == true) {
            lainLain = "ya";
        } else {
            lainLain = "tidak";
        }
        
        if (chkTerapi.isSelected() == true) {
            terapiObat = "ya";
        } else {
            terapiObat = "tidak";
        }
        
        if (chkNutrisi.isSelected() == true) {
            nutrisi = "ya";
        } else {
            nutrisi = "tidak";
        }
    }
    
    private void dataCekPemberian() {
        if (bahasa.equals("ya")) {
            chkBahasa.setSelected(true);
        } else {
            chkBahasa.setSelected(false);
        }
        
        if (pendengaran.equals("ya")) {
            chkPendengaran.setSelected(true);
        } else {
            chkPendengaran.setSelected(false);
        }
        
        if (masalahPenglihatan.equals("ya")) {
            chkMasalah.setSelected(true);
        } else {
            chkMasalah.setSelected(false);
        }
        
        if (hilangMemori.equals("ya")) {
            chkHilangMemori.setSelected(true);
        } else {
            chkHilangMemori.setSelected(false);
        }
        
        if (tidakAdaPartisipasi.equals("ya")) {
            chkTidakAda.setSelected(true);
        } else {
            chkTidakAda.setSelected(false);
        }
        
        if (secaraFisiologi.equals("ya")) {
            chkSecara.setSelected(true);
        } else {
            chkSecara.setSelected(false);
        }
        
        if (tidakDitemukanHambatan.equals("ya")) {
            chkTidakDitemukan.setSelected(true);
        } else {
            chkTidakDitemukan.setSelected(false);
        }
        
        if (cemas.equals("ya")) {
            chkCemas.setSelected(true);
        } else {
            chkCemas.setSelected(false);
        }
        
        if (emosi.equals("ya")) {
            chkEmosi.setSelected(true);
        } else {
            chkEmosi.setSelected(false);
        }
        
        if (kognitif.equals("ya")) {
            chkKognitif.setSelected(true);
        } else {
            chkKognitif.setSelected(false);
        }
        
        if (motifasiBuruk.equals("ya")) {
            chkMotivasi.setSelected(true);
        } else {
            chkMotivasi.setSelected(false);
        }
        
        if (bahasaIndonesia.equals("ya")) {
            chkBhsIndonesia.setSelected(true);
            cmbBhsIndo.setEnabled(true);
        } else {
            chkBhsIndonesia.setSelected(false);
            cmbBhsIndo.setEnabled(false);
        }
        
        if (bahasaDaerah.equals("ya")) {
            chkDaerah.setSelected(true);
            Tdaerah.setEnabled(true);
        } else {
            chkDaerah.setSelected(false);
            Tdaerah.setEnabled(false);
        }
        
        if (bahasaInggris.equals("ya")) {
            chkBhsInggris.setSelected(true);
            cmbBhsInggris.setEnabled(true);
        } else {
            chkBhsInggris.setSelected(false);
            cmbBhsInggris.setEnabled(false);
        }
        
        if (bahasaLainnya.equals("ya")) {
            chkBhsLainya.setSelected(true);
            TbhsLainya.setEnabled(true);
        } else {
            chkBhsLainya.setSelected(false);
            TbhsLainya.setEnabled(false);
        }
        
        if (prosesPenyakit.equals("ya")) {
            chkProses.setSelected(true);
        } else {
            chkProses.setSelected(false);
        }
        
        if (pengobatan.equals("ya")) {
            chkPengobatan.setSelected(true);
        } else {
            chkPengobatan.setSelected(false);
        }
        
        if (alatBantuMedis.equals("ya")) {
            chkAlatBantu.setSelected(true);
        } else {
            chkAlatBantu.setSelected(false);
        }
        
        if (lainLain.equals("ya")) {
            chkPotensialLain.setSelected(true);
            TPotensialLain.setEnabled(true);
        } else {
            chkPotensialLain.setSelected(false);
            TPotensialLain.setEnabled(false);
        }
        
        if (terapiObat.equals("ya")) {
            chkTerapi.setSelected(true);
        } else {
            chkTerapi.setSelected(false);
        }
        
        if (nutrisi.equals("ya")) {
            chkNutrisi.setSelected(true);
        } else {
            chkNutrisi.setSelected(false);
        }
    }
    
    private void variabelBersih() {
        nip = "";
        bahasa = "";
        pendengaran = "";
        masalahPenglihatan = "";
        hilangMemori = "";
        tidakAdaPartisipasi = "";
        secaraFisiologi = "";
        tidakDitemukanHambatan = "";
        cemas = "";
        emosi = "";
        kognitif = "";
        motifasiBuruk = "";
        bahasaIndonesia = "";
        bahasaDaerah = "";
        bahasaInggris = "";
        bahasaLainnya = "";
        prosesPenyakit = "";
        pengobatan = "";
        alatBantuMedis = "";
        lainLain = "";
        terapiObat = "";
        nutrisi = "";
    }
    
    public void setTampil(){
       TabEdukasi.setSelectedIndex(1);
       tampil();
    }
}