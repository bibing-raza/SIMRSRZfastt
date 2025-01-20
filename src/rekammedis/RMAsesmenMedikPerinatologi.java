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
import laporan.DlgHasilPenunjangMedis;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author perpustakaan
 */
public final class RMAsesmenMedikPerinatologi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, pscppt, psrestor;
    private ResultSet rs, rs1, rs2, rscppt, rsrestor;
    private int i = 0, x = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String user = "", dataKonfirmasi = "", kodekamar = "", hipertensi = "", diabet = "", jantung = "", strok = "", asma = "", kejang = "", hati = "",
            kanker = "", tb = "", pms = "", perdarahan = "", ginjal = "", lainRiwayat = "", turgor = "", sianosisKulit = "", perdarahanKulit = "",
            ikterusPos = "", ikterusNeg = "", kramer = "", hematoma = "", sklere = "", kutis = "", lainKulit = "", simetrisKepala = "",
            asimetrisKepala = "", cepal = "", caput = "", anen = "", micros = "", hidro = "", lainKepala = "", datar = "", cembung = "",
            cekung = "", lainUub = "", normalMata = "", anemia = "", ikterus = "", sekretMata = "", LainMata = "", normalTht = "",
            nch = "", sianosisTht = "", sekretTht = "", lainTht = "", normalMulut = "", labioS = "", labioP = "", labioG = "",
            mukosa = "", reflek = "", lainMulut = "", normalLeher = "", torti = "", benjolKanan = "", benjolKiri = "", lainLeher = "",
            simetrisDada = "", tidakSimetris = "", retraksiPos = "", retraksiNeg = "", sesak = "", merintih = "", sianosisDada = "",
            lainDada = "", bj = "", murni = "", tidakMurni = "", reguler = "", tidakReguler = "", bunyi = "", vesikuler = "", ronchi = "",
            wezing = "", stridor = "", lainParu = "", supel = "", disten = "", bising = "", hepar = "", limpa = "", nyeri = "", masaPos = "",
            masaNeg = "", uk = "", lokasi = "", segar = "", layu = "", lainTali = "", normalPunggung = "", spina = "", gibus = "",
            lainPunggung = "", sex = "", kelainanUro = "", bak = "", bab = "", simetrisEks = "", asimetrisEks = "", reflekMoroPos = "",
            reflekMoroNeg = "", lainEks = "", edema = "", kelainanEks = "";

    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsesmenMedikPerinatologi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Jenis Kelamin", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Asesmen", "Jam Asesmen", "Dokter Memeriksa",
            "keluhan", "riw_penyakit_dahulu", "hipertensi", "diabetes", "jantung", "stroke", "asma", "kejang", "hati", "kanker", "tb", "pms", "perdarahan",
            "ginjal", "lain_lain", "ket_lain_lain", "kondisi_saat_lahir", "ket_as", "gerak", "tangis", "warna_kulit", "hr", "suhu", "rr", "saturasi",
            "capilary_refill", "bbl", "pb", "lk", "ld", "lp", "lla", "turgor", "ket_turgor", "sianosis_kulit", "perdarahan_kulit", "ikterus_positif",
            "ikterus_negatif", "krammer", "ket_krammer", "hematoma", "sklerema", "kutis", "lainya_kulit", "ket_lainya_kulit", "simetris_kapala",
            "asimetris_kepala", "cephal_hematom", "caput_succedaneum", "anensefali", "microsefal", "hydrosefalus", "lainya_kepala", "ket_lainya_kepala",
            "datar", "cembung", "cekung", "lainya_uub", "ket_lainya_uub", "normal_mata", "anemia", "ikterus_mata", "sekret_mata", "lainya_mata",
            "ket_lainya_mata", "normal_tht", "nch", "sianosis_tht", "sekret_tht", "lainya_tht", "ket_lainya_tht", "normal_mulut", "labioschisis",
            "labiopalatoschisis", "labiognatopalatoschisis", "mucosa_warna", "ket_warna", "reflek_hisap", "ket_reflek_hisap", "lainya_mulut", "ket_lainya_mulut",
            "normal_leher", "tortikolis", "benjolan_kanan", "benjolan_kiri", "lainya_leher", "ket_lainya_leher", "simetris_dada", "tidak_simetris",
            "retraksi_positif", "retraksi_negatif", "ket_retraksi", "sesak", "merintih", "sianosis_dada", "lainya_dada", "ket_lainya_dada", "bji", "murni",
            "tidak_murni", "reguler", "tidak_reguler", "bunyi_tambahan", "ket_bunyi_tambahan", "vesikuler", "ronchi", "whezing", "stridor", "lainya_paru",
            "ket_lainya_paru", "supel", "distensi", "bising_usus", "pembesaran_hepar", "pembesaran_limpa", "nyeri", "ket_nyeri", "massa_positif",
            "massa_negatif", "uk", "ket_uk", "lokasi", "ket_lokasi", "segar", "layu", "lainya_tali_pusat", "ket_lainya_tali_pusat", "normal_punggung",
            "spina", "gibus", "lainya_punggung", "ket_lainya_punggung", "sex", "ket_sex", "kelainan_urogenitalia", "ket_kelainan_urogenitalia", "bak",
            "ket_bak", "anus", "bab", "ket_bab", "simetris_ekstremitas", "asimetris_ekstremitas", "reflek_moro_positif", "reflek_moro_negatif",
            "lainya_ekstremitas", "ket_lainya_ekstremitas", "edema", "kelainan_ekstremitas", "ket_kelainan_ekstremitas", "pemeriksaan_penunjang",
            "diagnosa_kerja", "diagnosa_banding", "pengobatan", "diet", "rencana", "tgl_asesmen", "jam_asesmen", "nip_dpjp", "waktu_simpan"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 169; i++) {
            TableColumn column = tbAsesmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(250);
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
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable()); 
        
        tabModeCppt=new DefaultTableModel(null, new Object[]{
            "Tgl. CPPT", "Jam CPPT", "Jenis Bagian", "DPJP Konsulen", "Jenis PPA",
            "Nama PPA", "Shift", "hasil", "instruksi", "no_rawat", "tgl_cppt", "jam_cppt"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbCPPT.setModel(tabModeCppt);
        tbCPPT.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCPPT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbCPPT.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(40);
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
            } 
        }
        tbCPPT.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Dilakukan Oleh", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Asesmen", "Tgl. Eksekusi", "Status Data"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayat.setModel(tabMode1);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(130);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } 
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TketLainLain.setDocument(new batasInput((int) 140).getKata(TketLainLain));
        TketAs.setDocument(new batasInput((int) 140).getKata(TketAs));
        Tgerak.setDocument(new batasInput((int) 140).getKata(Tgerak));
        Ttangis.setDocument(new batasInput((int) 140).getKata(Ttangis));
        TwarnaKulit.setDocument(new batasInput((int) 140).getKata(TwarnaKulit));
        Thr.setDocument(new batasInput((int) 7).getKata(Thr));
        Tsuhu.setDocument(new batasInput((int) 7).getKata(Tsuhu));
        Trr.setDocument(new batasInput((int) 7).getKata(Trr));
        Tsaturasi.setDocument(new batasInput((int) 7).getKata(Tsaturasi));
        Tbbl.setDocument(new batasInput((int) 7).getKata(Tbbl));
        Tpb.setDocument(new batasInput((int) 7).getKata(Tpb));
        Tlk.setDocument(new batasInput((int) 7).getKata(Tlk));
        Tld.setDocument(new batasInput((int) 7).getKata(Tld));
        Tlp.setDocument(new batasInput((int) 7).getKata(Tlp));
        Tlla.setDocument(new batasInput((int) 7).getKata(Tlla));
        Tturgor.setDocument(new batasInput((int) 140).getKata(Tturgor));
        Tkramer.setDocument(new batasInput((int) 140).getKata(Tkramer));
        TKetLainKulit.setDocument(new batasInput((int) 140).getKata(TKetLainKulit));
        TKetLainKepala.setDocument(new batasInput((int) 140).getKata(TKetLainKepala));
        TKetLainUUB.setDocument(new batasInput((int) 140).getKata(TKetLainUUB));
        TKetLainMata.setDocument(new batasInput((int) 140).getKata(TKetLainMata));
        TKetLainTHT.setDocument(new batasInput((int) 140).getKata(TKetLainTHT));
        TMukosa.setDocument(new batasInput((int) 140).getKata(TMukosa));
        TReflek.setDocument(new batasInput((int) 140).getKata(TReflek));
        TKetLainMulut.setDocument(new batasInput((int) 140).getKata(TKetLainMulut));
        TKetLainLeher.setDocument(new batasInput((int) 140).getKata(TKetLainLeher));
        TKetRetraksi.setDocument(new batasInput((int) 140).getKata(TKetRetraksi));
        TKetLainDada.setDocument(new batasInput((int) 140).getKata(TKetLainDada));
        TKetBunyi.setDocument(new batasInput((int) 140).getKata(TKetBunyi));
        TKetLainParu.setDocument(new batasInput((int) 140).getKata(TKetLainParu));
        TNyeri.setDocument(new batasInput((int) 140).getKata(TNyeri));
        Tuk.setDocument(new batasInput((int) 140).getKata(Tuk));
        Tlokasi.setDocument(new batasInput((int) 140).getKata(Tlokasi));
        TKetLainTali.setDocument(new batasInput((int) 140).getKata(TKetLainTali));
        TKetLainPunggung.setDocument(new batasInput((int) 140).getKata(TKetLainPunggung));
        Tsex.setDocument(new batasInput((int) 140).getKata(Tsex));
        TkelainanUro.setDocument(new batasInput((int) 140).getKata(TkelainanUro));
        Tbak.setDocument(new batasInput((int) 140).getKata(Tbak));
        Tbab.setDocument(new batasInput((int) 140).getKata(Tbab));
        TKetLainEks.setDocument(new batasInput((int) 140).getKata(TKetLainEks));
        TkelainanEks.setDocument(new batasInput((int) 140).getKata(TkelainanEks));        
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
                if (akses.getform().equals("RMAsesmenMedikPerinatologi")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        Tnip.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        TnmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter.requestFocus();
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
        
        ChkAccor.setSelected(false);
        isMenu();
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
        MnRiwayatData = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnHapus = new javax.swing.JMenuItem();
        WindowRiwayat = new javax.swing.JDialog();
        internalFrame13 = new widget.InternalFrame();
        internalFrame18 = new widget.InternalFrame();
        internalFrame17 = new widget.InternalFrame();
        jLabel30 = new widget.Label();
        DTPCari3 = new widget.Tanggal();
        jLabel31 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel32 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        jLabel33 = new widget.Label();
        LCount1 = new widget.Label();
        internalFrame19 = new widget.InternalFrame();
        BtnAll1 = new widget.Button();
        BtnRestor = new widget.Button();
        BtnCloseIn10 = new widget.Button();
        Scroll6 = new widget.ScrollPane();
        tbRiwayat = new widget.Table();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnResep = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel34 = new widget.Label();
        Tnip = new widget.TextBox();
        TnmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel12 = new widget.Label();
        TtglAsesmen = new widget.Tanggal();
        jLabel13 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel14 = new widget.Label();
        Tjk = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel64 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        Tkeluhan = new widget.TextArea();
        jLabel65 = new widget.Label();
        jLabel66 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        TriwPenyakitDahulu = new widget.TextArea();
        jLabel67 = new widget.Label();
        chkHipertensi = new widget.CekBox();
        chkDiabetes = new widget.CekBox();
        chkJantung = new widget.CekBox();
        chkStrok = new widget.CekBox();
        chkAsma = new widget.CekBox();
        chkKejang = new widget.CekBox();
        chkHati = new widget.CekBox();
        chkKanker = new widget.CekBox();
        chkTB = new widget.CekBox();
        chkPMS = new widget.CekBox();
        chkPerdarahan = new widget.CekBox();
        chkGinjal = new widget.CekBox();
        chkLainLain = new widget.CekBox();
        TketLainLain = new widget.TextBox();
        jLabel68 = new widget.Label();
        jLabel69 = new widget.Label();
        jLabel70 = new widget.Label();
        cmbKondisi = new widget.ComboBox();
        jLabel15 = new widget.Label();
        TketAs = new widget.TextBox();
        jLabel71 = new widget.Label();
        Tgerak = new widget.TextBox();
        jLabel72 = new widget.Label();
        Ttangis = new widget.TextBox();
        jLabel73 = new widget.Label();
        TwarnaKulit = new widget.TextBox();
        jLabel74 = new widget.Label();
        Thr = new widget.TextBox();
        jLabel75 = new widget.Label();
        Tsuhu = new widget.TextBox();
        jLabel36 = new widget.Label();
        Trr = new widget.TextBox();
        jLabel76 = new widget.Label();
        Tsaturasi = new widget.TextBox();
        jLabel37 = new widget.Label();
        jLabel77 = new widget.Label();
        cmbCapilary = new widget.ComboBox();
        jLabel78 = new widget.Label();
        Tbbl = new widget.TextBox();
        jLabel38 = new widget.Label();
        Tpb = new widget.TextBox();
        jLabel79 = new widget.Label();
        Tlk = new widget.TextBox();
        jLabel80 = new widget.Label();
        Tld = new widget.TextBox();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        Tlp = new widget.TextBox();
        jLabel39 = new widget.Label();
        Tlla = new widget.TextBox();
        jLabel40 = new widget.Label();
        jLabel83 = new widget.Label();
        jLabel84 = new widget.Label();
        chkTurgor = new widget.CekBox();
        Tturgor = new widget.TextBox();
        chkSianosisKulit = new widget.CekBox();
        chkPerdarahanKulit = new widget.CekBox();
        chkIkterusPositif = new widget.CekBox();
        chkIkterusNegatif = new widget.CekBox();
        chkKrammer = new widget.CekBox();
        Tkramer = new widget.TextBox();
        chkHematoma = new widget.CekBox();
        chkSklerema = new widget.CekBox();
        chkKutisMarmorata = new widget.CekBox();
        chkLainKulit = new widget.CekBox();
        TKetLainKulit = new widget.TextBox();
        jLabel85 = new widget.Label();
        chkSimetrisKepala = new widget.CekBox();
        chkAsimetrisKepala = new widget.CekBox();
        chkCephal = new widget.CekBox();
        chkCaput = new widget.CekBox();
        chkAnensefali = new widget.CekBox();
        chkMicrosefal = new widget.CekBox();
        chkhydrosefalus = new widget.CekBox();
        chkLainKepala = new widget.CekBox();
        TKetLainKepala = new widget.TextBox();
        jLabel86 = new widget.Label();
        chkDatar = new widget.CekBox();
        chkCembung = new widget.CekBox();
        chkCekung = new widget.CekBox();
        chkLainUUB = new widget.CekBox();
        TKetLainUUB = new widget.TextBox();
        jLabel87 = new widget.Label();
        chkNormalMata = new widget.CekBox();
        chkAnemia = new widget.CekBox();
        chkIkterusMata = new widget.CekBox();
        chkSekretMata = new widget.CekBox();
        chkLainMata = new widget.CekBox();
        TKetLainMata = new widget.TextBox();
        jLabel88 = new widget.Label();
        chkNormalTHT = new widget.CekBox();
        chkNCH = new widget.CekBox();
        chkSianosisTHT = new widget.CekBox();
        chkSekretTHT = new widget.CekBox();
        chkLainTHT = new widget.CekBox();
        TKetLainTHT = new widget.TextBox();
        jLabel89 = new widget.Label();
        chkNormalMulut = new widget.CekBox();
        chkLabioschisis = new widget.CekBox();
        chkLabiopalatos = new widget.CekBox();
        chkLabiog = new widget.CekBox();
        chkMukosa = new widget.CekBox();
        TMukosa = new widget.TextBox();
        chkReflek = new widget.CekBox();
        TReflek = new widget.TextBox();
        chkLainMulut = new widget.CekBox();
        TKetLainMulut = new widget.TextBox();
        jLabel90 = new widget.Label();
        chkNormalLeher = new widget.CekBox();
        chkTortikolis = new widget.CekBox();
        chkBenjolanKanan = new widget.CekBox();
        chkBenjolanKiri = new widget.CekBox();
        chkLainLeher = new widget.CekBox();
        TKetLainLeher = new widget.TextBox();
        jLabel91 = new widget.Label();
        chkSimetrisDada = new widget.CekBox();
        chkTidakSimetris = new widget.CekBox();
        chkRetraksiPositif = new widget.CekBox();
        chkRetraksiNegatif = new widget.CekBox();
        TKetRetraksi = new widget.TextBox();
        chkSesak = new widget.CekBox();
        chkMerintih = new widget.CekBox();
        chkSianosisDada = new widget.CekBox();
        chkLainDada = new widget.CekBox();
        TKetLainDada = new widget.TextBox();
        jLabel92 = new widget.Label();
        chkBji = new widget.CekBox();
        chkMurni = new widget.CekBox();
        chkTidakMurni = new widget.CekBox();
        chkReguler = new widget.CekBox();
        chkBunyi = new widget.CekBox();
        TKetBunyi = new widget.TextBox();
        chkTidakReguler = new widget.CekBox();
        jLabel93 = new widget.Label();
        chkVesikuler = new widget.CekBox();
        chkRonchi = new widget.CekBox();
        chkWhezing = new widget.CekBox();
        chkStridor = new widget.CekBox();
        chkLainParu = new widget.CekBox();
        TKetLainParu = new widget.TextBox();
        jLabel94 = new widget.Label();
        chkSupel = new widget.CekBox();
        chkDistensi = new widget.CekBox();
        chkBising = new widget.CekBox();
        chkPembesaranHepar = new widget.CekBox();
        chkNyeri = new widget.CekBox();
        TNyeri = new widget.TextBox();
        chkPembesaranLimpa = new widget.CekBox();
        chkMasaPositif = new widget.CekBox();
        chkMasaNegatif = new widget.CekBox();
        chkUK = new widget.CekBox();
        Tuk = new widget.TextBox();
        chkLokasi = new widget.CekBox();
        Tlokasi = new widget.TextBox();
        jLabel95 = new widget.Label();
        chkSegar = new widget.CekBox();
        chkLayu = new widget.CekBox();
        chkLainTali = new widget.CekBox();
        TKetLainTali = new widget.TextBox();
        jLabel96 = new widget.Label();
        chkNormalPunggung = new widget.CekBox();
        chkSpina = new widget.CekBox();
        chkGibus = new widget.CekBox();
        chkLainPunggung = new widget.CekBox();
        TKetLainPunggung = new widget.TextBox();
        jLabel97 = new widget.Label();
        chkSex = new widget.CekBox();
        Tsex = new widget.TextBox();
        chkKelainanUro = new widget.CekBox();
        TkelainanUro = new widget.TextBox();
        chkBAK = new widget.CekBox();
        Tbak = new widget.TextBox();
        jLabel98 = new widget.Label();
        chkBAB = new widget.CekBox();
        Tbab = new widget.TextBox();
        cmbAnus = new widget.ComboBox();
        jLabel99 = new widget.Label();
        chkSimetrisEks = new widget.CekBox();
        chkAsimetrisEks = new widget.CekBox();
        chkReflekMoroPositif = new widget.CekBox();
        chkReflekMoroNegatif = new widget.CekBox();
        chkLainEks = new widget.CekBox();
        TKetLainEks = new widget.TextBox();
        chkEdema = new widget.CekBox();
        chkKelainanEks = new widget.CekBox();
        TkelainanEks = new widget.TextBox();
        jLabel100 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        Tpemerikaaan = new widget.TextArea();
        jLabel101 = new widget.Label();
        scrollPane17 = new widget.ScrollPane();
        TdiagnosaKerja = new widget.TextArea();
        scrollPane18 = new widget.ScrollPane();
        TdiagnosaBanding = new widget.TextArea();
        jLabel102 = new widget.Label();
        scrollPane19 = new widget.ScrollPane();
        Tpengobatan = new widget.TextArea();
        jLabel103 = new widget.Label();
        scrollPane20 = new widget.ScrollPane();
        Tdiet = new widget.TextArea();
        jLabel104 = new widget.Label();
        scrollPane21 = new widget.ScrollPane();
        Trencana = new widget.TextArea();
        jLabel105 = new widget.Label();
        BtnPasteHasil = new widget.Button();
        TtglLahir = new widget.TextBox();
        jLabel16 = new widget.Label();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        Scroll4 = new widget.ScrollPane();
        tbCPPT = new widget.Table();
        panelGlass14 = new widget.panelisi();
        scrollPane5 = new widget.ScrollPane();
        Thasil = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();
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

        MnRiwayatData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRiwayatData.setText("Riwayat Data Terhapus/Diganti");
        MnRiwayatData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRiwayatData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRiwayatData.setIconTextGap(5);
        MnRiwayatData.setName("MnRiwayatData"); // NOI18N
        MnRiwayatData.setPreferredSize(new java.awt.Dimension(195, 26));
        MnRiwayatData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatDataActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRiwayatData);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapus.setText("Hapus Riwayat Data");
        MnHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapus.setIconTextGap(5);
        MnHapus.setName("MnHapus"); // NOI18N
        MnHapus.setPreferredSize(new java.awt.Dimension(150, 26));
        MnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHapus);

        WindowRiwayat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayat.setName("WindowRiwayat"); // NOI18N
        WindowRiwayat.setUndecorated(true);
        WindowRiwayat.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Asesmen Medik Perinatologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame13.setLayout(new java.awt.BorderLayout());

        internalFrame18.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame18.setName("internalFrame18"); // NOI18N
        internalFrame18.setPreferredSize(new java.awt.Dimension(400, 88));
        internalFrame18.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame18.setLayout(new java.awt.BorderLayout());

        internalFrame17.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame17.setName("internalFrame17"); // NOI18N
        internalFrame17.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame17.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 7, 9));

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tanggal :");
        jLabel30.setName("jLabel30"); // NOI18N
        jLabel30.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame17.add(jLabel30);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-01-2025" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari3);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("s.d.");
        jLabel31.setName("jLabel31"); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(23, 23));
        internalFrame17.add(jLabel31);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-01-2025" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari4);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Key Word :");
        jLabel32.setName("jLabel32"); // NOI18N
        jLabel32.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame17.add(jLabel32);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        internalFrame17.add(TCari2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('1');
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setToolTipText("Alt+1");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        BtnCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari2KeyPressed(evt);
            }
        });
        internalFrame17.add(BtnCari2);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Record :");
        jLabel33.setName("jLabel33"); // NOI18N
        jLabel33.setPreferredSize(new java.awt.Dimension(65, 23));
        internalFrame17.add(jLabel33);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        internalFrame17.add(LCount1);

        internalFrame18.add(internalFrame17, java.awt.BorderLayout.CENTER);

        internalFrame19.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame19.setName("internalFrame19"); // NOI18N
        internalFrame19.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame19.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 7, 9));

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('2');
        BtnAll1.setText("Semua Data");
        BtnAll1.setToolTipText("Alt+2");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll1ActionPerformed(evt);
            }
        });
        BtnAll1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll1KeyPressed(evt);
            }
        });
        internalFrame19.add(BtnAll1);

        BtnRestor.setForeground(new java.awt.Color(0, 0, 0));
        BtnRestor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnRestor.setMnemonic('U');
        BtnRestor.setText("Restore");
        BtnRestor.setToolTipText("Alt+U");
        BtnRestor.setName("BtnRestor"); // NOI18N
        BtnRestor.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnRestor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRestorActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnRestor);

        BtnCloseIn10.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn10.setMnemonic('U');
        BtnCloseIn10.setText("Tutup");
        BtnCloseIn10.setToolTipText("Alt+U");
        BtnCloseIn10.setName("BtnCloseIn10"); // NOI18N
        BtnCloseIn10.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnCloseIn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn10ActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnCloseIn10);

        internalFrame18.add(internalFrame19, java.awt.BorderLayout.PAGE_END);

        internalFrame13.add(internalFrame18, java.awt.BorderLayout.PAGE_END);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbRiwayat.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/direstore");
        tbRiwayat.setComponentPopupMenu(jPopupMenu2);
        tbRiwayat.setName("tbRiwayat"); // NOI18N
        Scroll6.setViewportView(tbRiwayat);

        internalFrame13.add(Scroll6, java.awt.BorderLayout.CENTER);

        WindowRiwayat.getContentPane().add(internalFrame13, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Medik Perinatologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        BtnResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Vial-Pills.png"))); // NOI18N
        BtnResep.setMnemonic('R');
        BtnResep.setText("Resep Obat");
        BtnResep.setToolTipText("Alt+R");
        BtnResep.setName("BtnResep"); // NOI18N
        BtnResep.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResepActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnResep);

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
        FormInput.setToolTipText("Klik Kanan Pada Area Ini Untuk Melihat Hasil Pemeriksaan Penunjang");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2055));
        FormInput.setLayout(null);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(319, 10, 410, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(247, 10, 70, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 110, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Dokter Memeriksa :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 2021, 140, 23);

        Tnip.setEditable(false);
        Tnip.setForeground(new java.awt.Color(0, 0, 0));
        Tnip.setName("Tnip"); // NOI18N
        FormInput.add(Tnip);
        Tnip.setBounds(147, 2021, 170, 23);

        TnmDokter.setEditable(false);
        TnmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter.setName("TnmDokter"); // NOI18N
        FormInput.add(TnmDokter);
        TnmDokter.setBounds(320, 2021, 410, 23);

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
        BtnDokter.setBounds(735, 2021, 28, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Assesmen :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 38, 110, 23);

        TtglAsesmen.setEditable(false);
        TtglAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-01-2025" }));
        TtglAsesmen.setDisplayFormat("dd-MM-yyyy");
        TtglAsesmen.setName("TtglAsesmen"); // NOI18N
        TtglAsesmen.setOpaque(false);
        TtglAsesmen.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglAsesmen);
        TtglAsesmen.setBounds(115, 38, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jam Assesmen :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(205, 38, 100, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(312, 38, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(365, 38, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(418, 38, 45, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Jenis Kelamin :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(535, 38, 110, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setHighlighter(null);
        Tjk.setName("Tjk"); // NOI18N
        FormInput.add(Tjk);
        Tjk.setBounds(649, 38, 80, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 66, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(115, 66, 460, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("1. KELUHAN UTAMA :");
        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 94, 150, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        Tkeluhan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkeluhan.setColumns(20);
        Tkeluhan.setRows(5);
        Tkeluhan.setName("Tkeluhan"); // NOI18N
        Tkeluhan.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tkeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeluhanKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Tkeluhan);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(157, 94, 573, 80);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("2. RIWAYAT PENYAKIT :");
        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 180, 150, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("a. Riwayat Penyakit Dahulu :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 208, 170, 23);

        scrollPane15.setName("scrollPane15"); // NOI18N

        TriwPenyakitDahulu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TriwPenyakitDahulu.setColumns(20);
        TriwPenyakitDahulu.setRows(5);
        TriwPenyakitDahulu.setName("TriwPenyakitDahulu"); // NOI18N
        TriwPenyakitDahulu.setPreferredSize(new java.awt.Dimension(162, 2000));
        TriwPenyakitDahulu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwPenyakitDahuluKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(TriwPenyakitDahulu);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(175, 208, 555, 80);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("b. Riwayat Penyakit Dalam Keluarga :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 294, 210, 23);

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
        chkHipertensi.setBounds(115, 322, 80, 23);

        chkDiabetes.setBackground(new java.awt.Color(255, 255, 250));
        chkDiabetes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDiabetes.setForeground(new java.awt.Color(0, 0, 0));
        chkDiabetes.setText("Diabetes");
        chkDiabetes.setBorderPainted(true);
        chkDiabetes.setBorderPaintedFlat(true);
        chkDiabetes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDiabetes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDiabetes.setName("chkDiabetes"); // NOI18N
        chkDiabetes.setOpaque(false);
        chkDiabetes.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDiabetes);
        chkDiabetes.setBounds(204, 322, 75, 23);

        chkJantung.setBackground(new java.awt.Color(255, 255, 250));
        chkJantung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJantung.setForeground(new java.awt.Color(0, 0, 0));
        chkJantung.setText("Jantung");
        chkJantung.setBorderPainted(true);
        chkJantung.setBorderPaintedFlat(true);
        chkJantung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJantung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJantung.setName("chkJantung"); // NOI18N
        chkJantung.setOpaque(false);
        chkJantung.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkJantung);
        chkJantung.setBounds(290, 322, 70, 23);

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
        chkStrok.setBounds(370, 322, 60, 23);

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
        chkAsma.setBounds(440, 322, 60, 23);

        chkKejang.setBackground(new java.awt.Color(255, 255, 250));
        chkKejang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKejang.setForeground(new java.awt.Color(0, 0, 0));
        chkKejang.setText("Kejang");
        chkKejang.setBorderPainted(true);
        chkKejang.setBorderPaintedFlat(true);
        chkKejang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKejang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKejang.setName("chkKejang"); // NOI18N
        chkKejang.setOpaque(false);
        chkKejang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKejang);
        chkKejang.setBounds(507, 322, 60, 23);

        chkHati.setBackground(new java.awt.Color(255, 255, 250));
        chkHati.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHati.setForeground(new java.awt.Color(0, 0, 0));
        chkHati.setText("Hati");
        chkHati.setBorderPainted(true);
        chkHati.setBorderPaintedFlat(true);
        chkHati.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHati.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHati.setName("chkHati"); // NOI18N
        chkHati.setOpaque(false);
        chkHati.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHati);
        chkHati.setBounds(577, 322, 50, 23);

        chkKanker.setBackground(new java.awt.Color(255, 255, 250));
        chkKanker.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKanker.setForeground(new java.awt.Color(0, 0, 0));
        chkKanker.setText("Kanker");
        chkKanker.setBorderPainted(true);
        chkKanker.setBorderPaintedFlat(true);
        chkKanker.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKanker.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKanker.setName("chkKanker"); // NOI18N
        chkKanker.setOpaque(false);
        chkKanker.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKanker);
        chkKanker.setBounds(115, 350, 80, 23);

        chkTB.setBackground(new java.awt.Color(255, 255, 250));
        chkTB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTB.setForeground(new java.awt.Color(0, 0, 0));
        chkTB.setText("TB");
        chkTB.setBorderPainted(true);
        chkTB.setBorderPaintedFlat(true);
        chkTB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTB.setName("chkTB"); // NOI18N
        chkTB.setOpaque(false);
        chkTB.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTB);
        chkTB.setBounds(204, 350, 75, 23);

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
        chkPMS.setBounds(290, 350, 70, 23);

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
        chkPerdarahan.setBounds(370, 350, 90, 23);

        chkGinjal.setBackground(new java.awt.Color(255, 255, 250));
        chkGinjal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGinjal.setForeground(new java.awt.Color(0, 0, 0));
        chkGinjal.setText("Ginjal");
        chkGinjal.setBorderPainted(true);
        chkGinjal.setBorderPaintedFlat(true);
        chkGinjal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGinjal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGinjal.setName("chkGinjal"); // NOI18N
        chkGinjal.setOpaque(false);
        chkGinjal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGinjal);
        chkGinjal.setBounds(470, 350, 55, 23);

        chkLainLain.setBackground(new java.awt.Color(255, 255, 250));
        chkLainLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainLain.setForeground(new java.awt.Color(0, 0, 0));
        chkLainLain.setText("Lain-lain :");
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
        chkLainLain.setBounds(532, 350, 70, 23);

        TketLainLain.setForeground(new java.awt.Color(0, 0, 0));
        TketLainLain.setName("TketLainLain"); // NOI18N
        TketLainLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketLainLainKeyPressed(evt);
            }
        });
        FormInput.add(TketLainLain);
        TketLainLain.setBounds(604, 350, 125, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("3. PEMERIKSAAN FISIK :");
        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 378, 150, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("a. Tanda Vital :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 406, 107, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Kondisi Saat Lahir :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 434, 140, 23);

        cmbKondisi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKondisi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Segera Menangis", "Tidak Segera Menangis" }));
        cmbKondisi.setName("cmbKondisi"); // NOI18N
        cmbKondisi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKondisi);
        cmbKondisi.setBounds(147, 434, 145, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("AS :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(295, 434, 40, 23);

        TketAs.setForeground(new java.awt.Color(0, 0, 0));
        TketAs.setName("TketAs"); // NOI18N
        TketAs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketAsKeyPressed(evt);
            }
        });
        FormInput.add(TketAs);
        TketAs.setBounds(339, 434, 390, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Gerak :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 462, 140, 23);

        Tgerak.setForeground(new java.awt.Color(0, 0, 0));
        Tgerak.setName("Tgerak"); // NOI18N
        Tgerak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgerakKeyPressed(evt);
            }
        });
        FormInput.add(Tgerak);
        Tgerak.setBounds(147, 462, 131, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Tangis :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(280, 462, 55, 23);

        Ttangis.setForeground(new java.awt.Color(0, 0, 0));
        Ttangis.setName("Ttangis"); // NOI18N
        Ttangis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtangisKeyPressed(evt);
            }
        });
        FormInput.add(Ttangis);
        Ttangis.setBounds(339, 462, 131, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Warna Kulit :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(470, 462, 77, 23);

        TwarnaKulit.setForeground(new java.awt.Color(0, 0, 0));
        TwarnaKulit.setName("TwarnaKulit"); // NOI18N
        TwarnaKulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TwarnaKulitKeyPressed(evt);
            }
        });
        FormInput.add(TwarnaKulit);
        TwarnaKulit.setBounds(554, 462, 175, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("HR :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 490, 140, 23);

        Thr.setForeground(new java.awt.Color(0, 0, 0));
        Thr.setName("Thr"); // NOI18N
        Thr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThrKeyPressed(evt);
            }
        });
        FormInput.add(Thr);
        Thr.setBounds(147, 490, 60, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel75.setText("x/menit     Suhu :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(213, 490, 85, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        FormInput.add(Tsuhu);
        Tsuhu.setBounds(300, 490, 60, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("°C    RR :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(366, 490, 50, 23);

        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        FormInput.add(Trr);
        Trr.setBounds(416, 490, 60, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("x/menit     Saturasi O2 :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(480, 490, 120, 23);

        Tsaturasi.setForeground(new java.awt.Color(0, 0, 0));
        Tsaturasi.setName("Tsaturasi"); // NOI18N
        Tsaturasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsaturasiKeyPressed(evt);
            }
        });
        FormInput.add(Tsaturasi);
        Tsaturasi.setBounds(600, 490, 60, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("%");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(666, 490, 30, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Capillary Refill :");
        jLabel77.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 518, 140, 23);

        cmbCapilary.setForeground(new java.awt.Color(0, 0, 0));
        cmbCapilary.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "< 3 Detik", "> 3 Detik" }));
        cmbCapilary.setName("cmbCapilary"); // NOI18N
        cmbCapilary.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbCapilary);
        cmbCapilary.setBounds(147, 518, 78, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("BBL :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(230, 518, 40, 23);

        Tbbl.setForeground(new java.awt.Color(0, 0, 0));
        Tbbl.setName("Tbbl"); // NOI18N
        Tbbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbblKeyPressed(evt);
            }
        });
        FormInput.add(Tbbl);
        Tbbl.setBounds(275, 518, 60, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("gram    PB :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(340, 518, 60, 23);

        Tpb.setForeground(new java.awt.Color(0, 0, 0));
        Tpb.setName("Tpb"); // NOI18N
        Tpb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpbKeyPressed(evt);
            }
        });
        FormInput.add(Tpb);
        Tpb.setBounds(400, 518, 60, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("cm    LK :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(465, 518, 48, 23);

        Tlk.setForeground(new java.awt.Color(0, 0, 0));
        Tlk.setName("Tlk"); // NOI18N
        Tlk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlkKeyPressed(evt);
            }
        });
        FormInput.add(Tlk);
        Tlk.setBounds(516, 518, 60, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("cm    LD :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(580, 518, 48, 23);

        Tld.setForeground(new java.awt.Color(0, 0, 0));
        Tld.setName("Tld"); // NOI18N
        Tld.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TldKeyPressed(evt);
            }
        });
        FormInput.add(Tld);
        Tld.setBounds(630, 518, 60, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel81.setText("cm");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(695, 518, 30, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("LP :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 546, 140, 23);

        Tlp.setForeground(new java.awt.Color(0, 0, 0));
        Tlp.setName("Tlp"); // NOI18N
        Tlp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlpKeyPressed(evt);
            }
        });
        FormInput.add(Tlp);
        Tlp.setBounds(147, 546, 60, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("cm      LLA :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(213, 546, 55, 23);

        Tlla.setForeground(new java.awt.Color(0, 0, 0));
        Tlla.setName("Tlla"); // NOI18N
        Tlla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TllaKeyPressed(evt);
            }
        });
        FormInput.add(Tlla);
        Tlla.setBounds(275, 546, 60, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("cm");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(340, 546, 30, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("b. Pemeriksaan Umum :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(0, 574, 150, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Kulit :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(0, 602, 140, 23);

        chkTurgor.setBackground(new java.awt.Color(255, 255, 250));
        chkTurgor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTurgor.setForeground(new java.awt.Color(0, 0, 0));
        chkTurgor.setText("Turgor :");
        chkTurgor.setBorderPainted(true);
        chkTurgor.setBorderPaintedFlat(true);
        chkTurgor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTurgor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTurgor.setName("chkTurgor"); // NOI18N
        chkTurgor.setOpaque(false);
        chkTurgor.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTurgor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTurgorActionPerformed(evt);
            }
        });
        FormInput.add(chkTurgor);
        chkTurgor.setBounds(147, 602, 64, 23);

        Tturgor.setForeground(new java.awt.Color(0, 0, 0));
        Tturgor.setName("Tturgor"); // NOI18N
        Tturgor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TturgorKeyPressed(evt);
            }
        });
        FormInput.add(Tturgor);
        Tturgor.setBounds(213, 602, 140, 23);

        chkSianosisKulit.setBackground(new java.awt.Color(255, 255, 250));
        chkSianosisKulit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSianosisKulit.setForeground(new java.awt.Color(0, 0, 0));
        chkSianosisKulit.setText("Sianosis");
        chkSianosisKulit.setBorderPainted(true);
        chkSianosisKulit.setBorderPaintedFlat(true);
        chkSianosisKulit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSianosisKulit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSianosisKulit.setName("chkSianosisKulit"); // NOI18N
        chkSianosisKulit.setOpaque(false);
        chkSianosisKulit.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSianosisKulit);
        chkSianosisKulit.setBounds(360, 602, 64, 23);

        chkPerdarahanKulit.setBackground(new java.awt.Color(255, 255, 250));
        chkPerdarahanKulit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerdarahanKulit.setForeground(new java.awt.Color(0, 0, 0));
        chkPerdarahanKulit.setText("Perdarahan");
        chkPerdarahanKulit.setBorderPainted(true);
        chkPerdarahanKulit.setBorderPaintedFlat(true);
        chkPerdarahanKulit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerdarahanKulit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerdarahanKulit.setName("chkPerdarahanKulit"); // NOI18N
        chkPerdarahanKulit.setOpaque(false);
        chkPerdarahanKulit.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerdarahanKulit);
        chkPerdarahanKulit.setBounds(430, 602, 86, 23);

        chkIkterusPositif.setBackground(new java.awt.Color(255, 255, 250));
        chkIkterusPositif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIkterusPositif.setForeground(new java.awt.Color(0, 0, 0));
        chkIkterusPositif.setText("Ikterus : +");
        chkIkterusPositif.setBorderPainted(true);
        chkIkterusPositif.setBorderPaintedFlat(true);
        chkIkterusPositif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIkterusPositif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIkterusPositif.setName("chkIkterusPositif"); // NOI18N
        chkIkterusPositif.setOpaque(false);
        chkIkterusPositif.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIkterusPositif);
        chkIkterusPositif.setBounds(523, 602, 80, 23);

        chkIkterusNegatif.setBackground(new java.awt.Color(255, 255, 250));
        chkIkterusNegatif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIkterusNegatif.setForeground(new java.awt.Color(0, 0, 0));
        chkIkterusNegatif.setText("Ikterus : -");
        chkIkterusNegatif.setBorderPainted(true);
        chkIkterusNegatif.setBorderPaintedFlat(true);
        chkIkterusNegatif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIkterusNegatif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIkterusNegatif.setName("chkIkterusNegatif"); // NOI18N
        chkIkterusNegatif.setOpaque(false);
        chkIkterusNegatif.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIkterusNegatif);
        chkIkterusNegatif.setBounds(614, 602, 80, 23);

        chkKrammer.setBackground(new java.awt.Color(255, 255, 250));
        chkKrammer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKrammer.setForeground(new java.awt.Color(0, 0, 0));
        chkKrammer.setText("Krammer :");
        chkKrammer.setBorderPainted(true);
        chkKrammer.setBorderPaintedFlat(true);
        chkKrammer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKrammer.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKrammer.setName("chkKrammer"); // NOI18N
        chkKrammer.setOpaque(false);
        chkKrammer.setPreferredSize(new java.awt.Dimension(175, 23));
        chkKrammer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkKrammerActionPerformed(evt);
            }
        });
        FormInput.add(chkKrammer);
        chkKrammer.setBounds(147, 630, 74, 23);

        Tkramer.setForeground(new java.awt.Color(0, 0, 0));
        Tkramer.setName("Tkramer"); // NOI18N
        Tkramer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkramerKeyPressed(evt);
            }
        });
        FormInput.add(Tkramer);
        Tkramer.setBounds(223, 630, 200, 23);

        chkHematoma.setBackground(new java.awt.Color(255, 255, 250));
        chkHematoma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHematoma.setForeground(new java.awt.Color(0, 0, 0));
        chkHematoma.setText("Hematoma");
        chkHematoma.setBorderPainted(true);
        chkHematoma.setBorderPaintedFlat(true);
        chkHematoma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHematoma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHematoma.setName("chkHematoma"); // NOI18N
        chkHematoma.setOpaque(false);
        chkHematoma.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHematoma);
        chkHematoma.setBounds(430, 630, 80, 23);

        chkSklerema.setBackground(new java.awt.Color(255, 255, 250));
        chkSklerema.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSklerema.setForeground(new java.awt.Color(0, 0, 0));
        chkSklerema.setText("Sklerema");
        chkSklerema.setBorderPainted(true);
        chkSklerema.setBorderPaintedFlat(true);
        chkSklerema.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSklerema.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSklerema.setName("chkSklerema"); // NOI18N
        chkSklerema.setOpaque(false);
        chkSklerema.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSklerema);
        chkSklerema.setBounds(523, 630, 75, 23);

        chkKutisMarmorata.setBackground(new java.awt.Color(255, 255, 250));
        chkKutisMarmorata.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKutisMarmorata.setForeground(new java.awt.Color(0, 0, 0));
        chkKutisMarmorata.setText("Kutis Marmorata");
        chkKutisMarmorata.setBorderPainted(true);
        chkKutisMarmorata.setBorderPaintedFlat(true);
        chkKutisMarmorata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKutisMarmorata.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKutisMarmorata.setName("chkKutisMarmorata"); // NOI18N
        chkKutisMarmorata.setOpaque(false);
        chkKutisMarmorata.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKutisMarmorata);
        chkKutisMarmorata.setBounds(614, 630, 103, 23);

        chkLainKulit.setBackground(new java.awt.Color(255, 255, 250));
        chkLainKulit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainKulit.setForeground(new java.awt.Color(0, 0, 0));
        chkLainKulit.setText("Lainnya :");
        chkLainKulit.setBorderPainted(true);
        chkLainKulit.setBorderPaintedFlat(true);
        chkLainKulit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainKulit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainKulit.setName("chkLainKulit"); // NOI18N
        chkLainKulit.setOpaque(false);
        chkLainKulit.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainKulit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainKulitActionPerformed(evt);
            }
        });
        FormInput.add(chkLainKulit);
        chkLainKulit.setBounds(147, 658, 70, 23);

        TKetLainKulit.setForeground(new java.awt.Color(0, 0, 0));
        TKetLainKulit.setName("TKetLainKulit"); // NOI18N
        TKetLainKulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetLainKulitKeyPressed(evt);
            }
        });
        FormInput.add(TKetLainKulit);
        TKetLainKulit.setBounds(218, 658, 517, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Kepala :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(0, 686, 140, 23);

        chkSimetrisKepala.setBackground(new java.awt.Color(255, 255, 250));
        chkSimetrisKepala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSimetrisKepala.setForeground(new java.awt.Color(0, 0, 0));
        chkSimetrisKepala.setText("Simetris");
        chkSimetrisKepala.setBorderPainted(true);
        chkSimetrisKepala.setBorderPaintedFlat(true);
        chkSimetrisKepala.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSimetrisKepala.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSimetrisKepala.setName("chkSimetrisKepala"); // NOI18N
        chkSimetrisKepala.setOpaque(false);
        chkSimetrisKepala.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSimetrisKepala);
        chkSimetrisKepala.setBounds(147, 686, 64, 23);

        chkAsimetrisKepala.setBackground(new java.awt.Color(255, 255, 250));
        chkAsimetrisKepala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsimetrisKepala.setForeground(new java.awt.Color(0, 0, 0));
        chkAsimetrisKepala.setText("Asimetris");
        chkAsimetrisKepala.setBorderPainted(true);
        chkAsimetrisKepala.setBorderPaintedFlat(true);
        chkAsimetrisKepala.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsimetrisKepala.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsimetrisKepala.setName("chkAsimetrisKepala"); // NOI18N
        chkAsimetrisKepala.setOpaque(false);
        chkAsimetrisKepala.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAsimetrisKepala);
        chkAsimetrisKepala.setBounds(220, 686, 70, 23);

        chkCephal.setBackground(new java.awt.Color(255, 255, 250));
        chkCephal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCephal.setForeground(new java.awt.Color(0, 0, 0));
        chkCephal.setText("Cephal Hematom");
        chkCephal.setBorderPainted(true);
        chkCephal.setBorderPaintedFlat(true);
        chkCephal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCephal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCephal.setName("chkCephal"); // NOI18N
        chkCephal.setOpaque(false);
        chkCephal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCephal);
        chkCephal.setBounds(300, 686, 110, 23);

        chkCaput.setBackground(new java.awt.Color(255, 255, 250));
        chkCaput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCaput.setForeground(new java.awt.Color(0, 0, 0));
        chkCaput.setText("Caput Succedaneum");
        chkCaput.setBorderPainted(true);
        chkCaput.setBorderPaintedFlat(true);
        chkCaput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCaput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCaput.setName("chkCaput"); // NOI18N
        chkCaput.setOpaque(false);
        chkCaput.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCaput);
        chkCaput.setBounds(420, 686, 130, 23);

        chkAnensefali.setBackground(new java.awt.Color(255, 255, 250));
        chkAnensefali.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAnensefali.setForeground(new java.awt.Color(0, 0, 0));
        chkAnensefali.setText("Anensefali");
        chkAnensefali.setBorderPainted(true);
        chkAnensefali.setBorderPaintedFlat(true);
        chkAnensefali.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAnensefali.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAnensefali.setName("chkAnensefali"); // NOI18N
        chkAnensefali.setOpaque(false);
        chkAnensefali.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAnensefali);
        chkAnensefali.setBounds(560, 686, 80, 23);

        chkMicrosefal.setBackground(new java.awt.Color(255, 255, 250));
        chkMicrosefal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMicrosefal.setForeground(new java.awt.Color(0, 0, 0));
        chkMicrosefal.setText("Microsefal");
        chkMicrosefal.setBorderPainted(true);
        chkMicrosefal.setBorderPaintedFlat(true);
        chkMicrosefal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMicrosefal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMicrosefal.setName("chkMicrosefal"); // NOI18N
        chkMicrosefal.setOpaque(false);
        chkMicrosefal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMicrosefal);
        chkMicrosefal.setBounds(650, 686, 80, 23);

        chkhydrosefalus.setBackground(new java.awt.Color(255, 255, 250));
        chkhydrosefalus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkhydrosefalus.setForeground(new java.awt.Color(0, 0, 0));
        chkhydrosefalus.setText("Hydrosefalus");
        chkhydrosefalus.setBorderPainted(true);
        chkhydrosefalus.setBorderPaintedFlat(true);
        chkhydrosefalus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkhydrosefalus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkhydrosefalus.setName("chkhydrosefalus"); // NOI18N
        chkhydrosefalus.setOpaque(false);
        chkhydrosefalus.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkhydrosefalus);
        chkhydrosefalus.setBounds(147, 714, 89, 23);

        chkLainKepala.setBackground(new java.awt.Color(255, 255, 250));
        chkLainKepala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainKepala.setForeground(new java.awt.Color(0, 0, 0));
        chkLainKepala.setText("Lainnya :");
        chkLainKepala.setBorderPainted(true);
        chkLainKepala.setBorderPaintedFlat(true);
        chkLainKepala.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainKepala.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainKepala.setName("chkLainKepala"); // NOI18N
        chkLainKepala.setOpaque(false);
        chkLainKepala.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainKepala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainKepalaActionPerformed(evt);
            }
        });
        FormInput.add(chkLainKepala);
        chkLainKepala.setBounds(244, 714, 70, 23);

        TKetLainKepala.setForeground(new java.awt.Color(0, 0, 0));
        TKetLainKepala.setName("TKetLainKepala"); // NOI18N
        TKetLainKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetLainKepalaKeyPressed(evt);
            }
        });
        FormInput.add(TKetLainKepala);
        TKetLainKepala.setBounds(315, 714, 420, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("UUB :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 742, 140, 23);

        chkDatar.setBackground(new java.awt.Color(255, 255, 250));
        chkDatar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDatar.setForeground(new java.awt.Color(0, 0, 0));
        chkDatar.setText("Datar");
        chkDatar.setBorderPainted(true);
        chkDatar.setBorderPaintedFlat(true);
        chkDatar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDatar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDatar.setName("chkDatar"); // NOI18N
        chkDatar.setOpaque(false);
        chkDatar.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDatar);
        chkDatar.setBounds(147, 742, 64, 23);

        chkCembung.setBackground(new java.awt.Color(255, 255, 250));
        chkCembung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCembung.setForeground(new java.awt.Color(0, 0, 0));
        chkCembung.setText("Cembung");
        chkCembung.setBorderPainted(true);
        chkCembung.setBorderPaintedFlat(true);
        chkCembung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCembung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCembung.setName("chkCembung"); // NOI18N
        chkCembung.setOpaque(false);
        chkCembung.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCembung);
        chkCembung.setBounds(220, 742, 70, 23);

        chkCekung.setBackground(new java.awt.Color(255, 255, 250));
        chkCekung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCekung.setForeground(new java.awt.Color(0, 0, 0));
        chkCekung.setText("Cekung");
        chkCekung.setBorderPainted(true);
        chkCekung.setBorderPaintedFlat(true);
        chkCekung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCekung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCekung.setName("chkCekung"); // NOI18N
        chkCekung.setOpaque(false);
        chkCekung.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCekung);
        chkCekung.setBounds(300, 742, 65, 23);

        chkLainUUB.setBackground(new java.awt.Color(255, 255, 250));
        chkLainUUB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainUUB.setForeground(new java.awt.Color(0, 0, 0));
        chkLainUUB.setText("Lainnya :");
        chkLainUUB.setBorderPainted(true);
        chkLainUUB.setBorderPaintedFlat(true);
        chkLainUUB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainUUB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainUUB.setName("chkLainUUB"); // NOI18N
        chkLainUUB.setOpaque(false);
        chkLainUUB.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainUUB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainUUBActionPerformed(evt);
            }
        });
        FormInput.add(chkLainUUB);
        chkLainUUB.setBounds(375, 742, 70, 23);

        TKetLainUUB.setForeground(new java.awt.Color(0, 0, 0));
        TKetLainUUB.setName("TKetLainUUB"); // NOI18N
        TKetLainUUB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetLainUUBKeyPressed(evt);
            }
        });
        FormInput.add(TKetLainUUB);
        TKetLainUUB.setBounds(445, 742, 290, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Mata :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 770, 140, 23);

        chkNormalMata.setBackground(new java.awt.Color(255, 255, 250));
        chkNormalMata.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNormalMata.setForeground(new java.awt.Color(0, 0, 0));
        chkNormalMata.setText("Normal");
        chkNormalMata.setBorderPainted(true);
        chkNormalMata.setBorderPaintedFlat(true);
        chkNormalMata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNormalMata.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNormalMata.setName("chkNormalMata"); // NOI18N
        chkNormalMata.setOpaque(false);
        chkNormalMata.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNormalMata);
        chkNormalMata.setBounds(147, 770, 64, 23);

        chkAnemia.setBackground(new java.awt.Color(255, 255, 250));
        chkAnemia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAnemia.setForeground(new java.awt.Color(0, 0, 0));
        chkAnemia.setText("Anemia");
        chkAnemia.setBorderPainted(true);
        chkAnemia.setBorderPaintedFlat(true);
        chkAnemia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAnemia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAnemia.setName("chkAnemia"); // NOI18N
        chkAnemia.setOpaque(false);
        chkAnemia.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAnemia);
        chkAnemia.setBounds(220, 770, 70, 23);

        chkIkterusMata.setBackground(new java.awt.Color(255, 255, 250));
        chkIkterusMata.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIkterusMata.setForeground(new java.awt.Color(0, 0, 0));
        chkIkterusMata.setText("Ikterus");
        chkIkterusMata.setBorderPainted(true);
        chkIkterusMata.setBorderPaintedFlat(true);
        chkIkterusMata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIkterusMata.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIkterusMata.setName("chkIkterusMata"); // NOI18N
        chkIkterusMata.setOpaque(false);
        chkIkterusMata.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIkterusMata);
        chkIkterusMata.setBounds(300, 770, 68, 23);

        chkSekretMata.setBackground(new java.awt.Color(255, 255, 250));
        chkSekretMata.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSekretMata.setForeground(new java.awt.Color(0, 0, 0));
        chkSekretMata.setText("Sekret");
        chkSekretMata.setBorderPainted(true);
        chkSekretMata.setBorderPaintedFlat(true);
        chkSekretMata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSekretMata.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSekretMata.setName("chkSekretMata"); // NOI18N
        chkSekretMata.setOpaque(false);
        chkSekretMata.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSekretMata);
        chkSekretMata.setBounds(375, 770, 60, 23);

        chkLainMata.setBackground(new java.awt.Color(255, 255, 250));
        chkLainMata.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainMata.setForeground(new java.awt.Color(0, 0, 0));
        chkLainMata.setText("Lainnya :");
        chkLainMata.setBorderPainted(true);
        chkLainMata.setBorderPaintedFlat(true);
        chkLainMata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainMata.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainMata.setName("chkLainMata"); // NOI18N
        chkLainMata.setOpaque(false);
        chkLainMata.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainMata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainMataActionPerformed(evt);
            }
        });
        FormInput.add(chkLainMata);
        chkLainMata.setBounds(444, 770, 70, 23);

        TKetLainMata.setForeground(new java.awt.Color(0, 0, 0));
        TKetLainMata.setName("TKetLainMata"); // NOI18N
        TKetLainMata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetLainMataKeyPressed(evt);
            }
        });
        FormInput.add(TKetLainMata);
        TKetLainMata.setBounds(515, 770, 220, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("THT :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(0, 798, 140, 23);

        chkNormalTHT.setBackground(new java.awt.Color(255, 255, 250));
        chkNormalTHT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNormalTHT.setForeground(new java.awt.Color(0, 0, 0));
        chkNormalTHT.setText("Normal");
        chkNormalTHT.setBorderPainted(true);
        chkNormalTHT.setBorderPaintedFlat(true);
        chkNormalTHT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNormalTHT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNormalTHT.setName("chkNormalTHT"); // NOI18N
        chkNormalTHT.setOpaque(false);
        chkNormalTHT.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNormalTHT);
        chkNormalTHT.setBounds(147, 798, 64, 23);

        chkNCH.setBackground(new java.awt.Color(255, 255, 250));
        chkNCH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNCH.setForeground(new java.awt.Color(0, 0, 0));
        chkNCH.setText("NCH");
        chkNCH.setBorderPainted(true);
        chkNCH.setBorderPaintedFlat(true);
        chkNCH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNCH.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNCH.setName("chkNCH"); // NOI18N
        chkNCH.setOpaque(false);
        chkNCH.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNCH);
        chkNCH.setBounds(220, 798, 70, 23);

        chkSianosisTHT.setBackground(new java.awt.Color(255, 255, 250));
        chkSianosisTHT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSianosisTHT.setForeground(new java.awt.Color(0, 0, 0));
        chkSianosisTHT.setText("Sianosis");
        chkSianosisTHT.setBorderPainted(true);
        chkSianosisTHT.setBorderPaintedFlat(true);
        chkSianosisTHT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSianosisTHT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSianosisTHT.setName("chkSianosisTHT"); // NOI18N
        chkSianosisTHT.setOpaque(false);
        chkSianosisTHT.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSianosisTHT);
        chkSianosisTHT.setBounds(300, 798, 68, 23);

        chkSekretTHT.setBackground(new java.awt.Color(255, 255, 250));
        chkSekretTHT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSekretTHT.setForeground(new java.awt.Color(0, 0, 0));
        chkSekretTHT.setText("Sekret");
        chkSekretTHT.setBorderPainted(true);
        chkSekretTHT.setBorderPaintedFlat(true);
        chkSekretTHT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSekretTHT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSekretTHT.setName("chkSekretTHT"); // NOI18N
        chkSekretTHT.setOpaque(false);
        chkSekretTHT.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSekretTHT);
        chkSekretTHT.setBounds(375, 798, 60, 23);

        chkLainTHT.setBackground(new java.awt.Color(255, 255, 250));
        chkLainTHT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainTHT.setForeground(new java.awt.Color(0, 0, 0));
        chkLainTHT.setText("Lainnya :");
        chkLainTHT.setBorderPainted(true);
        chkLainTHT.setBorderPaintedFlat(true);
        chkLainTHT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainTHT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainTHT.setName("chkLainTHT"); // NOI18N
        chkLainTHT.setOpaque(false);
        chkLainTHT.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainTHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainTHTActionPerformed(evt);
            }
        });
        FormInput.add(chkLainTHT);
        chkLainTHT.setBounds(444, 798, 70, 23);

        TKetLainTHT.setForeground(new java.awt.Color(0, 0, 0));
        TKetLainTHT.setName("TKetLainTHT"); // NOI18N
        TKetLainTHT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetLainTHTKeyPressed(evt);
            }
        });
        FormInput.add(TKetLainTHT);
        TKetLainTHT.setBounds(515, 798, 220, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Mulut :");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 826, 140, 23);

        chkNormalMulut.setBackground(new java.awt.Color(255, 255, 250));
        chkNormalMulut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNormalMulut.setForeground(new java.awt.Color(0, 0, 0));
        chkNormalMulut.setText("Normal");
        chkNormalMulut.setBorderPainted(true);
        chkNormalMulut.setBorderPaintedFlat(true);
        chkNormalMulut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNormalMulut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNormalMulut.setName("chkNormalMulut"); // NOI18N
        chkNormalMulut.setOpaque(false);
        chkNormalMulut.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNormalMulut);
        chkNormalMulut.setBounds(147, 826, 64, 23);

        chkLabioschisis.setBackground(new java.awt.Color(255, 255, 250));
        chkLabioschisis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLabioschisis.setForeground(new java.awt.Color(0, 0, 0));
        chkLabioschisis.setText("Labioschisis");
        chkLabioschisis.setBorderPainted(true);
        chkLabioschisis.setBorderPaintedFlat(true);
        chkLabioschisis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLabioschisis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLabioschisis.setName("chkLabioschisis"); // NOI18N
        chkLabioschisis.setOpaque(false);
        chkLabioschisis.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLabioschisis);
        chkLabioschisis.setBounds(220, 826, 85, 23);

        chkLabiopalatos.setBackground(new java.awt.Color(255, 255, 250));
        chkLabiopalatos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLabiopalatos.setForeground(new java.awt.Color(0, 0, 0));
        chkLabiopalatos.setText("Labiopalatoschisis");
        chkLabiopalatos.setBorderPainted(true);
        chkLabiopalatos.setBorderPaintedFlat(true);
        chkLabiopalatos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLabiopalatos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLabiopalatos.setName("chkLabiopalatos"); // NOI18N
        chkLabiopalatos.setOpaque(false);
        chkLabiopalatos.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLabiopalatos);
        chkLabiopalatos.setBounds(315, 826, 114, 23);

        chkLabiog.setBackground(new java.awt.Color(255, 255, 250));
        chkLabiog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLabiog.setForeground(new java.awt.Color(0, 0, 0));
        chkLabiog.setText("Labiognatopalatoschisis");
        chkLabiog.setBorderPainted(true);
        chkLabiog.setBorderPaintedFlat(true);
        chkLabiog.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLabiog.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLabiog.setName("chkLabiog"); // NOI18N
        chkLabiog.setOpaque(false);
        chkLabiog.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLabiog);
        chkLabiog.setBounds(440, 826, 140, 23);

        chkMukosa.setBackground(new java.awt.Color(255, 255, 250));
        chkMukosa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMukosa.setForeground(new java.awt.Color(0, 0, 0));
        chkMukosa.setText("Mukosa : Warna :");
        chkMukosa.setBorderPainted(true);
        chkMukosa.setBorderPaintedFlat(true);
        chkMukosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMukosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMukosa.setName("chkMukosa"); // NOI18N
        chkMukosa.setOpaque(false);
        chkMukosa.setPreferredSize(new java.awt.Dimension(175, 23));
        chkMukosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMukosaActionPerformed(evt);
            }
        });
        FormInput.add(chkMukosa);
        chkMukosa.setBounds(147, 854, 110, 23);

        TMukosa.setForeground(new java.awt.Color(0, 0, 0));
        TMukosa.setName("TMukosa"); // NOI18N
        TMukosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMukosaKeyPressed(evt);
            }
        });
        FormInput.add(TMukosa);
        TMukosa.setBounds(260, 854, 140, 23);

        chkReflek.setBackground(new java.awt.Color(255, 255, 250));
        chkReflek.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkReflek.setForeground(new java.awt.Color(0, 0, 0));
        chkReflek.setText("Refleks Hisap :");
        chkReflek.setBorderPainted(true);
        chkReflek.setBorderPaintedFlat(true);
        chkReflek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkReflek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkReflek.setName("chkReflek"); // NOI18N
        chkReflek.setOpaque(false);
        chkReflek.setPreferredSize(new java.awt.Dimension(175, 23));
        chkReflek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkReflekActionPerformed(evt);
            }
        });
        FormInput.add(chkReflek);
        chkReflek.setBounds(406, 854, 95, 23);

        TReflek.setForeground(new java.awt.Color(0, 0, 0));
        TReflek.setName("TReflek"); // NOI18N
        TReflek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TReflekKeyPressed(evt);
            }
        });
        FormInput.add(TReflek);
        TReflek.setBounds(505, 854, 230, 23);

        chkLainMulut.setBackground(new java.awt.Color(255, 255, 250));
        chkLainMulut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainMulut.setForeground(new java.awt.Color(0, 0, 0));
        chkLainMulut.setText("Lainnya :");
        chkLainMulut.setBorderPainted(true);
        chkLainMulut.setBorderPaintedFlat(true);
        chkLainMulut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainMulut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainMulut.setName("chkLainMulut"); // NOI18N
        chkLainMulut.setOpaque(false);
        chkLainMulut.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainMulut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainMulutActionPerformed(evt);
            }
        });
        FormInput.add(chkLainMulut);
        chkLainMulut.setBounds(147, 882, 70, 23);

        TKetLainMulut.setForeground(new java.awt.Color(0, 0, 0));
        TKetLainMulut.setName("TKetLainMulut"); // NOI18N
        TKetLainMulut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetLainMulutKeyPressed(evt);
            }
        });
        FormInput.add(TKetLainMulut);
        TKetLainMulut.setBounds(218, 882, 517, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Leher :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(0, 910, 140, 23);

        chkNormalLeher.setBackground(new java.awt.Color(255, 255, 250));
        chkNormalLeher.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNormalLeher.setForeground(new java.awt.Color(0, 0, 0));
        chkNormalLeher.setText("Normal");
        chkNormalLeher.setBorderPainted(true);
        chkNormalLeher.setBorderPaintedFlat(true);
        chkNormalLeher.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNormalLeher.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNormalLeher.setName("chkNormalLeher"); // NOI18N
        chkNormalLeher.setOpaque(false);
        chkNormalLeher.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNormalLeher);
        chkNormalLeher.setBounds(147, 910, 64, 23);

        chkTortikolis.setBackground(new java.awt.Color(255, 255, 250));
        chkTortikolis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTortikolis.setForeground(new java.awt.Color(0, 0, 0));
        chkTortikolis.setText("Tortikolis");
        chkTortikolis.setBorderPainted(true);
        chkTortikolis.setBorderPaintedFlat(true);
        chkTortikolis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTortikolis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTortikolis.setName("chkTortikolis"); // NOI18N
        chkTortikolis.setOpaque(false);
        chkTortikolis.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTortikolis);
        chkTortikolis.setBounds(220, 910, 70, 23);

        chkBenjolanKanan.setBackground(new java.awt.Color(255, 255, 250));
        chkBenjolanKanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBenjolanKanan.setForeground(new java.awt.Color(0, 0, 0));
        chkBenjolanKanan.setText("Benjolan Dikanan");
        chkBenjolanKanan.setBorderPainted(true);
        chkBenjolanKanan.setBorderPaintedFlat(true);
        chkBenjolanKanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBenjolanKanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBenjolanKanan.setName("chkBenjolanKanan"); // NOI18N
        chkBenjolanKanan.setOpaque(false);
        chkBenjolanKanan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBenjolanKanan);
        chkBenjolanKanan.setBounds(300, 910, 110, 23);

        chkBenjolanKiri.setBackground(new java.awt.Color(255, 255, 250));
        chkBenjolanKiri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBenjolanKiri.setForeground(new java.awt.Color(0, 0, 0));
        chkBenjolanKiri.setText("Benjolan Dikiri");
        chkBenjolanKiri.setBorderPainted(true);
        chkBenjolanKiri.setBorderPaintedFlat(true);
        chkBenjolanKiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBenjolanKiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBenjolanKiri.setName("chkBenjolanKiri"); // NOI18N
        chkBenjolanKiri.setOpaque(false);
        chkBenjolanKiri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBenjolanKiri);
        chkBenjolanKiri.setBounds(420, 910, 95, 23);

        chkLainLeher.setBackground(new java.awt.Color(255, 255, 250));
        chkLainLeher.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainLeher.setForeground(new java.awt.Color(0, 0, 0));
        chkLainLeher.setText("Lainnya :");
        chkLainLeher.setBorderPainted(true);
        chkLainLeher.setBorderPaintedFlat(true);
        chkLainLeher.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainLeher.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainLeher.setName("chkLainLeher"); // NOI18N
        chkLainLeher.setOpaque(false);
        chkLainLeher.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainLeher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainLeherActionPerformed(evt);
            }
        });
        FormInput.add(chkLainLeher);
        chkLainLeher.setBounds(524, 910, 70, 23);

        TKetLainLeher.setForeground(new java.awt.Color(0, 0, 0));
        TKetLainLeher.setName("TKetLainLeher"); // NOI18N
        TKetLainLeher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetLainLeherKeyPressed(evt);
            }
        });
        FormInput.add(TKetLainLeher);
        TKetLainLeher.setBounds(595, 910, 140, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Dada :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(0, 938, 140, 23);

        chkSimetrisDada.setBackground(new java.awt.Color(255, 255, 250));
        chkSimetrisDada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSimetrisDada.setForeground(new java.awt.Color(0, 0, 0));
        chkSimetrisDada.setText("Simetris");
        chkSimetrisDada.setBorderPainted(true);
        chkSimetrisDada.setBorderPaintedFlat(true);
        chkSimetrisDada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSimetrisDada.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSimetrisDada.setName("chkSimetrisDada"); // NOI18N
        chkSimetrisDada.setOpaque(false);
        chkSimetrisDada.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSimetrisDada);
        chkSimetrisDada.setBounds(147, 938, 64, 23);

        chkTidakSimetris.setBackground(new java.awt.Color(255, 255, 250));
        chkTidakSimetris.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTidakSimetris.setForeground(new java.awt.Color(0, 0, 0));
        chkTidakSimetris.setText("Tidak Simetris");
        chkTidakSimetris.setBorderPainted(true);
        chkTidakSimetris.setBorderPaintedFlat(true);
        chkTidakSimetris.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidakSimetris.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidakSimetris.setName("chkTidakSimetris"); // NOI18N
        chkTidakSimetris.setOpaque(false);
        chkTidakSimetris.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTidakSimetris);
        chkTidakSimetris.setBounds(220, 938, 90, 23);

        chkRetraksiPositif.setBackground(new java.awt.Color(255, 255, 250));
        chkRetraksiPositif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRetraksiPositif.setForeground(new java.awt.Color(0, 0, 0));
        chkRetraksiPositif.setText("Retraksi : +");
        chkRetraksiPositif.setBorderPainted(true);
        chkRetraksiPositif.setBorderPaintedFlat(true);
        chkRetraksiPositif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRetraksiPositif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRetraksiPositif.setName("chkRetraksiPositif"); // NOI18N
        chkRetraksiPositif.setOpaque(false);
        chkRetraksiPositif.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkRetraksiPositif);
        chkRetraksiPositif.setBounds(320, 938, 83, 23);

        chkRetraksiNegatif.setBackground(new java.awt.Color(255, 255, 250));
        chkRetraksiNegatif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRetraksiNegatif.setForeground(new java.awt.Color(0, 0, 0));
        chkRetraksiNegatif.setText("Retraksi : -, Di");
        chkRetraksiNegatif.setBorderPainted(true);
        chkRetraksiNegatif.setBorderPaintedFlat(true);
        chkRetraksiNegatif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRetraksiNegatif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRetraksiNegatif.setName("chkRetraksiNegatif"); // NOI18N
        chkRetraksiNegatif.setOpaque(false);
        chkRetraksiNegatif.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkRetraksiNegatif);
        chkRetraksiNegatif.setBounds(420, 938, 95, 23);

        TKetRetraksi.setForeground(new java.awt.Color(0, 0, 0));
        TKetRetraksi.setName("TKetRetraksi"); // NOI18N
        TKetRetraksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetRetraksiKeyPressed(evt);
            }
        });
        FormInput.add(TKetRetraksi);
        TKetRetraksi.setBounds(515, 938, 220, 23);

        chkSesak.setBackground(new java.awt.Color(255, 255, 250));
        chkSesak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSesak.setForeground(new java.awt.Color(0, 0, 0));
        chkSesak.setText("Sesak");
        chkSesak.setBorderPainted(true);
        chkSesak.setBorderPaintedFlat(true);
        chkSesak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSesak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSesak.setName("chkSesak"); // NOI18N
        chkSesak.setOpaque(false);
        chkSesak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSesak);
        chkSesak.setBounds(147, 966, 64, 23);

        chkMerintih.setBackground(new java.awt.Color(255, 255, 250));
        chkMerintih.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMerintih.setForeground(new java.awt.Color(0, 0, 0));
        chkMerintih.setText("Merintih");
        chkMerintih.setBorderPainted(true);
        chkMerintih.setBorderPaintedFlat(true);
        chkMerintih.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMerintih.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMerintih.setName("chkMerintih"); // NOI18N
        chkMerintih.setOpaque(false);
        chkMerintih.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMerintih);
        chkMerintih.setBounds(220, 966, 70, 23);

        chkSianosisDada.setBackground(new java.awt.Color(255, 255, 250));
        chkSianosisDada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSianosisDada.setForeground(new java.awt.Color(0, 0, 0));
        chkSianosisDada.setText("Sianosis");
        chkSianosisDada.setBorderPainted(true);
        chkSianosisDada.setBorderPaintedFlat(true);
        chkSianosisDada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSianosisDada.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSianosisDada.setName("chkSianosisDada"); // NOI18N
        chkSianosisDada.setOpaque(false);
        chkSianosisDada.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSianosisDada);
        chkSianosisDada.setBounds(300, 966, 65, 23);

        chkLainDada.setBackground(new java.awt.Color(255, 255, 250));
        chkLainDada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainDada.setForeground(new java.awt.Color(0, 0, 0));
        chkLainDada.setText("Lainnya :");
        chkLainDada.setBorderPainted(true);
        chkLainDada.setBorderPaintedFlat(true);
        chkLainDada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainDada.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainDada.setName("chkLainDada"); // NOI18N
        chkLainDada.setOpaque(false);
        chkLainDada.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainDada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainDadaActionPerformed(evt);
            }
        });
        FormInput.add(chkLainDada);
        chkLainDada.setBounds(374, 966, 70, 23);

        TKetLainDada.setForeground(new java.awt.Color(0, 0, 0));
        TKetLainDada.setName("TKetLainDada"); // NOI18N
        TKetLainDada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetLainDadaKeyPressed(evt);
            }
        });
        FormInput.add(TKetLainDada);
        TKetLainDada.setBounds(445, 966, 290, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Jantung :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(0, 994, 140, 23);

        chkBji.setBackground(new java.awt.Color(255, 255, 250));
        chkBji.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBji.setForeground(new java.awt.Color(0, 0, 0));
        chkBji.setText("BJ I & II");
        chkBji.setBorderPainted(true);
        chkBji.setBorderPaintedFlat(true);
        chkBji.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBji.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBji.setName("chkBji"); // NOI18N
        chkBji.setOpaque(false);
        chkBji.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBji);
        chkBji.setBounds(147, 994, 64, 23);

        chkMurni.setBackground(new java.awt.Color(255, 255, 250));
        chkMurni.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMurni.setForeground(new java.awt.Color(0, 0, 0));
        chkMurni.setText("Murni");
        chkMurni.setBorderPainted(true);
        chkMurni.setBorderPaintedFlat(true);
        chkMurni.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMurni.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMurni.setName("chkMurni"); // NOI18N
        chkMurni.setOpaque(false);
        chkMurni.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMurni);
        chkMurni.setBounds(220, 994, 70, 23);

        chkTidakMurni.setBackground(new java.awt.Color(255, 255, 250));
        chkTidakMurni.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTidakMurni.setForeground(new java.awt.Color(0, 0, 0));
        chkTidakMurni.setText("Tidak Murni");
        chkTidakMurni.setBorderPainted(true);
        chkTidakMurni.setBorderPaintedFlat(true);
        chkTidakMurni.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidakMurni.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidakMurni.setName("chkTidakMurni"); // NOI18N
        chkTidakMurni.setOpaque(false);
        chkTidakMurni.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTidakMurni);
        chkTidakMurni.setBounds(300, 994, 80, 23);

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
        chkReguler.setBounds(388, 994, 65, 23);

        chkBunyi.setBackground(new java.awt.Color(255, 255, 250));
        chkBunyi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBunyi.setForeground(new java.awt.Color(0, 0, 0));
        chkBunyi.setText("Bunyi Tambahan :");
        chkBunyi.setBorderPainted(true);
        chkBunyi.setBorderPaintedFlat(true);
        chkBunyi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBunyi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBunyi.setName("chkBunyi"); // NOI18N
        chkBunyi.setOpaque(false);
        chkBunyi.setPreferredSize(new java.awt.Dimension(175, 23));
        chkBunyi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBunyiActionPerformed(evt);
            }
        });
        FormInput.add(chkBunyi);
        chkBunyi.setBounds(147, 1022, 110, 23);

        TKetBunyi.setForeground(new java.awt.Color(0, 0, 0));
        TKetBunyi.setName("TKetBunyi"); // NOI18N
        TKetBunyi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetBunyiKeyPressed(evt);
            }
        });
        FormInput.add(TKetBunyi);
        TKetBunyi.setBounds(260, 1022, 475, 23);

        chkTidakReguler.setBackground(new java.awt.Color(255, 255, 250));
        chkTidakReguler.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTidakReguler.setForeground(new java.awt.Color(0, 0, 0));
        chkTidakReguler.setText("Tidak Reguler");
        chkTidakReguler.setBorderPainted(true);
        chkTidakReguler.setBorderPaintedFlat(true);
        chkTidakReguler.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidakReguler.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidakReguler.setName("chkTidakReguler"); // NOI18N
        chkTidakReguler.setOpaque(false);
        chkTidakReguler.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTidakReguler);
        chkTidakReguler.setBounds(459, 994, 90, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Paru :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(0, 1050, 140, 23);

        chkVesikuler.setBackground(new java.awt.Color(255, 255, 250));
        chkVesikuler.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkVesikuler.setForeground(new java.awt.Color(0, 0, 0));
        chkVesikuler.setText("Vesikuler");
        chkVesikuler.setBorderPainted(true);
        chkVesikuler.setBorderPaintedFlat(true);
        chkVesikuler.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkVesikuler.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkVesikuler.setName("chkVesikuler"); // NOI18N
        chkVesikuler.setOpaque(false);
        chkVesikuler.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkVesikuler);
        chkVesikuler.setBounds(147, 1050, 64, 23);

        chkRonchi.setBackground(new java.awt.Color(255, 255, 250));
        chkRonchi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRonchi.setForeground(new java.awt.Color(0, 0, 0));
        chkRonchi.setText("Ronchi");
        chkRonchi.setBorderPainted(true);
        chkRonchi.setBorderPaintedFlat(true);
        chkRonchi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRonchi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRonchi.setName("chkRonchi"); // NOI18N
        chkRonchi.setOpaque(false);
        chkRonchi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkRonchi);
        chkRonchi.setBounds(220, 1050, 70, 23);

        chkWhezing.setBackground(new java.awt.Color(255, 255, 250));
        chkWhezing.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkWhezing.setForeground(new java.awt.Color(0, 0, 0));
        chkWhezing.setText("Wheezing");
        chkWhezing.setBorderPainted(true);
        chkWhezing.setBorderPaintedFlat(true);
        chkWhezing.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkWhezing.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkWhezing.setName("chkWhezing"); // NOI18N
        chkWhezing.setOpaque(false);
        chkWhezing.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkWhezing);
        chkWhezing.setBounds(300, 1050, 76, 23);

        chkStridor.setBackground(new java.awt.Color(255, 255, 250));
        chkStridor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkStridor.setForeground(new java.awt.Color(0, 0, 0));
        chkStridor.setText("Stridor");
        chkStridor.setBorderPainted(true);
        chkStridor.setBorderPaintedFlat(true);
        chkStridor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkStridor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkStridor.setName("chkStridor"); // NOI18N
        chkStridor.setOpaque(false);
        chkStridor.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkStridor);
        chkStridor.setBounds(386, 1050, 60, 23);

        chkLainParu.setBackground(new java.awt.Color(255, 255, 250));
        chkLainParu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainParu.setForeground(new java.awt.Color(0, 0, 0));
        chkLainParu.setText("Lainnya :");
        chkLainParu.setBorderPainted(true);
        chkLainParu.setBorderPaintedFlat(true);
        chkLainParu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainParu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainParu.setName("chkLainParu"); // NOI18N
        chkLainParu.setOpaque(false);
        chkLainParu.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainParu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainParuActionPerformed(evt);
            }
        });
        FormInput.add(chkLainParu);
        chkLainParu.setBounds(454, 1050, 70, 23);

        TKetLainParu.setForeground(new java.awt.Color(0, 0, 0));
        TKetLainParu.setName("TKetLainParu"); // NOI18N
        TKetLainParu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetLainParuKeyPressed(evt);
            }
        });
        FormInput.add(TKetLainParu);
        TKetLainParu.setBounds(525, 1050, 210, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Perut :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(0, 1078, 140, 23);

        chkSupel.setBackground(new java.awt.Color(255, 255, 250));
        chkSupel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSupel.setForeground(new java.awt.Color(0, 0, 0));
        chkSupel.setText("Supel/Flat");
        chkSupel.setBorderPainted(true);
        chkSupel.setBorderPaintedFlat(true);
        chkSupel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSupel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSupel.setName("chkSupel"); // NOI18N
        chkSupel.setOpaque(false);
        chkSupel.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSupel);
        chkSupel.setBounds(147, 1078, 74, 23);

        chkDistensi.setBackground(new java.awt.Color(255, 255, 250));
        chkDistensi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDistensi.setForeground(new java.awt.Color(0, 0, 0));
        chkDistensi.setText("Distensi");
        chkDistensi.setBorderPainted(true);
        chkDistensi.setBorderPaintedFlat(true);
        chkDistensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDistensi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDistensi.setName("chkDistensi"); // NOI18N
        chkDistensi.setOpaque(false);
        chkDistensi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDistensi);
        chkDistensi.setBounds(230, 1078, 64, 23);

        chkBising.setBackground(new java.awt.Color(255, 255, 250));
        chkBising.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBising.setForeground(new java.awt.Color(0, 0, 0));
        chkBising.setText("Bising Usus");
        chkBising.setBorderPainted(true);
        chkBising.setBorderPaintedFlat(true);
        chkBising.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBising.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBising.setName("chkBising"); // NOI18N
        chkBising.setOpaque(false);
        chkBising.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBising);
        chkBising.setBounds(300, 1078, 79, 23);

        chkPembesaranHepar.setBackground(new java.awt.Color(255, 255, 250));
        chkPembesaranHepar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPembesaranHepar.setForeground(new java.awt.Color(0, 0, 0));
        chkPembesaranHepar.setText("Pembesaran Hepar");
        chkPembesaranHepar.setBorderPainted(true);
        chkPembesaranHepar.setBorderPaintedFlat(true);
        chkPembesaranHepar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPembesaranHepar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPembesaranHepar.setName("chkPembesaranHepar"); // NOI18N
        chkPembesaranHepar.setOpaque(false);
        chkPembesaranHepar.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPembesaranHepar);
        chkPembesaranHepar.setBounds(386, 1078, 120, 23);

        chkNyeri.setBackground(new java.awt.Color(255, 255, 250));
        chkNyeri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNyeri.setForeground(new java.awt.Color(0, 0, 0));
        chkNyeri.setText("Nyeri Tekan, Regio :");
        chkNyeri.setBorderPainted(true);
        chkNyeri.setBorderPaintedFlat(true);
        chkNyeri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNyeri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNyeri.setName("chkNyeri"); // NOI18N
        chkNyeri.setOpaque(false);
        chkNyeri.setPreferredSize(new java.awt.Dimension(175, 23));
        chkNyeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNyeriActionPerformed(evt);
            }
        });
        FormInput.add(chkNyeri);
        chkNyeri.setBounds(147, 1106, 124, 23);

        TNyeri.setForeground(new java.awt.Color(0, 0, 0));
        TNyeri.setName("TNyeri"); // NOI18N
        TNyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNyeriKeyPressed(evt);
            }
        });
        FormInput.add(TNyeri);
        TNyeri.setBounds(274, 1106, 290, 23);

        chkPembesaranLimpa.setBackground(new java.awt.Color(255, 255, 250));
        chkPembesaranLimpa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPembesaranLimpa.setForeground(new java.awt.Color(0, 0, 0));
        chkPembesaranLimpa.setText("Pembesaran Limpa");
        chkPembesaranLimpa.setBorderPainted(true);
        chkPembesaranLimpa.setBorderPaintedFlat(true);
        chkPembesaranLimpa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPembesaranLimpa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPembesaranLimpa.setName("chkPembesaranLimpa"); // NOI18N
        chkPembesaranLimpa.setOpaque(false);
        chkPembesaranLimpa.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPembesaranLimpa);
        chkPembesaranLimpa.setBounds(516, 1078, 120, 23);

        chkMasaPositif.setBackground(new java.awt.Color(255, 255, 250));
        chkMasaPositif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMasaPositif.setForeground(new java.awt.Color(0, 0, 0));
        chkMasaPositif.setText("Massa : +");
        chkMasaPositif.setBorderPainted(true);
        chkMasaPositif.setBorderPaintedFlat(true);
        chkMasaPositif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMasaPositif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMasaPositif.setName("chkMasaPositif"); // NOI18N
        chkMasaPositif.setOpaque(false);
        chkMasaPositif.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMasaPositif);
        chkMasaPositif.setBounds(570, 1106, 76, 23);

        chkMasaNegatif.setBackground(new java.awt.Color(255, 255, 250));
        chkMasaNegatif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMasaNegatif.setForeground(new java.awt.Color(0, 0, 0));
        chkMasaNegatif.setText("Massa : -");
        chkMasaNegatif.setBorderPainted(true);
        chkMasaNegatif.setBorderPaintedFlat(true);
        chkMasaNegatif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMasaNegatif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMasaNegatif.setName("chkMasaNegatif"); // NOI18N
        chkMasaNegatif.setOpaque(false);
        chkMasaNegatif.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMasaNegatif);
        chkMasaNegatif.setBounds(655, 1106, 76, 23);

        chkUK.setBackground(new java.awt.Color(255, 255, 250));
        chkUK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkUK.setForeground(new java.awt.Color(0, 0, 0));
        chkUK.setText("UK :");
        chkUK.setBorderPainted(true);
        chkUK.setBorderPaintedFlat(true);
        chkUK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkUK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkUK.setName("chkUK"); // NOI18N
        chkUK.setOpaque(false);
        chkUK.setPreferredSize(new java.awt.Dimension(175, 23));
        chkUK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkUKActionPerformed(evt);
            }
        });
        FormInput.add(chkUK);
        chkUK.setBounds(147, 1134, 45, 23);

        Tuk.setForeground(new java.awt.Color(0, 0, 0));
        Tuk.setName("Tuk"); // NOI18N
        Tuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TukKeyPressed(evt);
            }
        });
        FormInput.add(Tuk);
        Tuk.setBounds(194, 1134, 240, 23);

        chkLokasi.setBackground(new java.awt.Color(255, 255, 250));
        chkLokasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLokasi.setForeground(new java.awt.Color(0, 0, 0));
        chkLokasi.setText("Lokasi :");
        chkLokasi.setBorderPainted(true);
        chkLokasi.setBorderPaintedFlat(true);
        chkLokasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLokasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLokasi.setName("chkLokasi"); // NOI18N
        chkLokasi.setOpaque(false);
        chkLokasi.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLokasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLokasiActionPerformed(evt);
            }
        });
        FormInput.add(chkLokasi);
        chkLokasi.setBounds(454, 1134, 60, 23);

        Tlokasi.setForeground(new java.awt.Color(0, 0, 0));
        Tlokasi.setName("Tlokasi"); // NOI18N
        Tlokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlokasiKeyPressed(evt);
            }
        });
        FormInput.add(Tlokasi);
        Tlokasi.setBounds(517, 1134, 218, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Tali Pusat :");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(0, 1162, 140, 23);

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
        chkSegar.setBounds(147, 1162, 64, 23);

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
        chkLayu.setBounds(220, 1162, 50, 23);

        chkLainTali.setBackground(new java.awt.Color(255, 255, 250));
        chkLainTali.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainTali.setForeground(new java.awt.Color(0, 0, 0));
        chkLainTali.setText("Lainnya :");
        chkLainTali.setBorderPainted(true);
        chkLainTali.setBorderPaintedFlat(true);
        chkLainTali.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainTali.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainTali.setName("chkLainTali"); // NOI18N
        chkLainTali.setOpaque(false);
        chkLainTali.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainTali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainTaliActionPerformed(evt);
            }
        });
        FormInput.add(chkLainTali);
        chkLainTali.setBounds(284, 1162, 70, 23);

        TKetLainTali.setForeground(new java.awt.Color(0, 0, 0));
        TKetLainTali.setName("TKetLainTali"); // NOI18N
        TKetLainTali.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetLainTaliKeyPressed(evt);
            }
        });
        FormInput.add(TKetLainTali);
        TKetLainTali.setBounds(355, 1162, 380, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Punggung :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(0, 1190, 140, 23);

        chkNormalPunggung.setBackground(new java.awt.Color(255, 255, 250));
        chkNormalPunggung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNormalPunggung.setForeground(new java.awt.Color(0, 0, 0));
        chkNormalPunggung.setText("Normal");
        chkNormalPunggung.setBorderPainted(true);
        chkNormalPunggung.setBorderPaintedFlat(true);
        chkNormalPunggung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNormalPunggung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNormalPunggung.setName("chkNormalPunggung"); // NOI18N
        chkNormalPunggung.setOpaque(false);
        chkNormalPunggung.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNormalPunggung);
        chkNormalPunggung.setBounds(147, 1190, 64, 23);

        chkSpina.setBackground(new java.awt.Color(255, 255, 250));
        chkSpina.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSpina.setForeground(new java.awt.Color(0, 0, 0));
        chkSpina.setText("Spina Bifida");
        chkSpina.setBorderPainted(true);
        chkSpina.setBorderPaintedFlat(true);
        chkSpina.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSpina.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSpina.setName("chkSpina"); // NOI18N
        chkSpina.setOpaque(false);
        chkSpina.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSpina);
        chkSpina.setBounds(220, 1190, 80, 23);

        chkGibus.setBackground(new java.awt.Color(255, 255, 250));
        chkGibus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGibus.setForeground(new java.awt.Color(0, 0, 0));
        chkGibus.setText("Gibus");
        chkGibus.setBorderPainted(true);
        chkGibus.setBorderPaintedFlat(true);
        chkGibus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGibus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGibus.setName("chkGibus"); // NOI18N
        chkGibus.setOpaque(false);
        chkGibus.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGibus);
        chkGibus.setBounds(310, 1190, 54, 23);

        chkLainPunggung.setBackground(new java.awt.Color(255, 255, 250));
        chkLainPunggung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainPunggung.setForeground(new java.awt.Color(0, 0, 0));
        chkLainPunggung.setText("Lainnya :");
        chkLainPunggung.setBorderPainted(true);
        chkLainPunggung.setBorderPaintedFlat(true);
        chkLainPunggung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainPunggung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainPunggung.setName("chkLainPunggung"); // NOI18N
        chkLainPunggung.setOpaque(false);
        chkLainPunggung.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainPunggung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainPunggungActionPerformed(evt);
            }
        });
        FormInput.add(chkLainPunggung);
        chkLainPunggung.setBounds(370, 1190, 70, 23);

        TKetLainPunggung.setForeground(new java.awt.Color(0, 0, 0));
        TKetLainPunggung.setName("TKetLainPunggung"); // NOI18N
        TKetLainPunggung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetLainPunggungKeyPressed(evt);
            }
        });
        FormInput.add(TKetLainPunggung);
        TKetLainPunggung.setBounds(440, 1190, 295, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Urogenitalia :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(0, 1218, 140, 23);

        chkSex.setBackground(new java.awt.Color(255, 255, 250));
        chkSex.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSex.setForeground(new java.awt.Color(0, 0, 0));
        chkSex.setText("Sex :");
        chkSex.setBorderPainted(true);
        chkSex.setBorderPaintedFlat(true);
        chkSex.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSex.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSex.setName("chkSex"); // NOI18N
        chkSex.setOpaque(false);
        chkSex.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSexActionPerformed(evt);
            }
        });
        FormInput.add(chkSex);
        chkSex.setBounds(147, 1218, 50, 23);

        Tsex.setForeground(new java.awt.Color(0, 0, 0));
        Tsex.setName("Tsex"); // NOI18N
        Tsex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsexKeyPressed(evt);
            }
        });
        FormInput.add(Tsex);
        Tsex.setBounds(198, 1218, 110, 23);

        chkKelainanUro.setBackground(new java.awt.Color(255, 255, 250));
        chkKelainanUro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKelainanUro.setForeground(new java.awt.Color(0, 0, 0));
        chkKelainanUro.setText("Kelainan :");
        chkKelainanUro.setBorderPainted(true);
        chkKelainanUro.setBorderPaintedFlat(true);
        chkKelainanUro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKelainanUro.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKelainanUro.setName("chkKelainanUro"); // NOI18N
        chkKelainanUro.setOpaque(false);
        chkKelainanUro.setPreferredSize(new java.awt.Dimension(175, 23));
        chkKelainanUro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkKelainanUroActionPerformed(evt);
            }
        });
        FormInput.add(chkKelainanUro);
        chkKelainanUro.setBounds(315, 1218, 70, 23);

        TkelainanUro.setForeground(new java.awt.Color(0, 0, 0));
        TkelainanUro.setName("TkelainanUro"); // NOI18N
        TkelainanUro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkelainanUroKeyPressed(evt);
            }
        });
        FormInput.add(TkelainanUro);
        TkelainanUro.setBounds(386, 1218, 130, 23);

        chkBAK.setBackground(new java.awt.Color(255, 255, 250));
        chkBAK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBAK.setForeground(new java.awt.Color(0, 0, 0));
        chkBAK.setText("BAK :");
        chkBAK.setBorderPainted(true);
        chkBAK.setBorderPaintedFlat(true);
        chkBAK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBAK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBAK.setName("chkBAK"); // NOI18N
        chkBAK.setOpaque(false);
        chkBAK.setPreferredSize(new java.awt.Dimension(175, 23));
        chkBAK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBAKActionPerformed(evt);
            }
        });
        FormInput.add(chkBAK);
        chkBAK.setBounds(520, 1218, 50, 23);

        Tbak.setForeground(new java.awt.Color(0, 0, 0));
        Tbak.setName("Tbak"); // NOI18N
        Tbak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbakKeyPressed(evt);
            }
        });
        FormInput.add(Tbak);
        Tbak.setBounds(575, 1218, 160, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Anus :");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(0, 1246, 140, 23);

        chkBAB.setBackground(new java.awt.Color(255, 255, 250));
        chkBAB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBAB.setForeground(new java.awt.Color(0, 0, 0));
        chkBAB.setText("BAB :");
        chkBAB.setBorderPainted(true);
        chkBAB.setBorderPaintedFlat(true);
        chkBAB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBAB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBAB.setName("chkBAB"); // NOI18N
        chkBAB.setOpaque(false);
        chkBAB.setPreferredSize(new java.awt.Dimension(175, 23));
        chkBAB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBABActionPerformed(evt);
            }
        });
        FormInput.add(chkBAB);
        chkBAB.setBounds(235, 1246, 50, 23);

        Tbab.setForeground(new java.awt.Color(0, 0, 0));
        Tbab.setName("Tbab"); // NOI18N
        Tbab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbabKeyPressed(evt);
            }
        });
        FormInput.add(Tbab);
        Tbab.setBounds(285, 1246, 450, 23);

        cmbAnus.setForeground(new java.awt.Color(0, 0, 0));
        cmbAnus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbAnus.setName("cmbAnus"); // NOI18N
        cmbAnus.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbAnus);
        cmbAnus.setBounds(147, 1246, 80, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Ekstremitas :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(0, 1274, 140, 23);

        chkSimetrisEks.setBackground(new java.awt.Color(255, 255, 250));
        chkSimetrisEks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSimetrisEks.setForeground(new java.awt.Color(0, 0, 0));
        chkSimetrisEks.setText("Simetris");
        chkSimetrisEks.setBorderPainted(true);
        chkSimetrisEks.setBorderPaintedFlat(true);
        chkSimetrisEks.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSimetrisEks.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSimetrisEks.setName("chkSimetrisEks"); // NOI18N
        chkSimetrisEks.setOpaque(false);
        chkSimetrisEks.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSimetrisEks);
        chkSimetrisEks.setBounds(147, 1274, 64, 23);

        chkAsimetrisEks.setBackground(new java.awt.Color(255, 255, 250));
        chkAsimetrisEks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsimetrisEks.setForeground(new java.awt.Color(0, 0, 0));
        chkAsimetrisEks.setText("Asimetris");
        chkAsimetrisEks.setBorderPainted(true);
        chkAsimetrisEks.setBorderPaintedFlat(true);
        chkAsimetrisEks.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsimetrisEks.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsimetrisEks.setName("chkAsimetrisEks"); // NOI18N
        chkAsimetrisEks.setOpaque(false);
        chkAsimetrisEks.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAsimetrisEks);
        chkAsimetrisEks.setBounds(220, 1274, 70, 23);

        chkReflekMoroPositif.setBackground(new java.awt.Color(255, 255, 250));
        chkReflekMoroPositif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkReflekMoroPositif.setForeground(new java.awt.Color(0, 0, 0));
        chkReflekMoroPositif.setText("Refleks Moro : +");
        chkReflekMoroPositif.setBorderPainted(true);
        chkReflekMoroPositif.setBorderPaintedFlat(true);
        chkReflekMoroPositif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkReflekMoroPositif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkReflekMoroPositif.setName("chkReflekMoroPositif"); // NOI18N
        chkReflekMoroPositif.setOpaque(false);
        chkReflekMoroPositif.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkReflekMoroPositif);
        chkReflekMoroPositif.setBounds(300, 1274, 110, 23);

        chkReflekMoroNegatif.setBackground(new java.awt.Color(255, 255, 250));
        chkReflekMoroNegatif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkReflekMoroNegatif.setForeground(new java.awt.Color(0, 0, 0));
        chkReflekMoroNegatif.setText("Refleks Moro : -");
        chkReflekMoroNegatif.setBorderPainted(true);
        chkReflekMoroNegatif.setBorderPaintedFlat(true);
        chkReflekMoroNegatif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkReflekMoroNegatif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkReflekMoroNegatif.setName("chkReflekMoroNegatif"); // NOI18N
        chkReflekMoroNegatif.setOpaque(false);
        chkReflekMoroNegatif.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkReflekMoroNegatif);
        chkReflekMoroNegatif.setBounds(420, 1274, 105, 23);

        chkLainEks.setBackground(new java.awt.Color(255, 255, 250));
        chkLainEks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainEks.setForeground(new java.awt.Color(0, 0, 0));
        chkLainEks.setText("Lainnya :");
        chkLainEks.setBorderPainted(true);
        chkLainEks.setBorderPaintedFlat(true);
        chkLainEks.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainEks.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainEks.setName("chkLainEks"); // NOI18N
        chkLainEks.setOpaque(false);
        chkLainEks.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainEks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainEksActionPerformed(evt);
            }
        });
        FormInput.add(chkLainEks);
        chkLainEks.setBounds(532, 1274, 70, 23);

        TKetLainEks.setForeground(new java.awt.Color(0, 0, 0));
        TKetLainEks.setName("TKetLainEks"); // NOI18N
        TKetLainEks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKetLainEksKeyPressed(evt);
            }
        });
        FormInput.add(TKetLainEks);
        TKetLainEks.setBounds(605, 1274, 130, 23);

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
        chkEdema.setBounds(147, 1302, 64, 23);

        chkKelainanEks.setBackground(new java.awt.Color(255, 255, 250));
        chkKelainanEks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKelainanEks.setForeground(new java.awt.Color(0, 0, 0));
        chkKelainanEks.setText("Kelainan :");
        chkKelainanEks.setBorderPainted(true);
        chkKelainanEks.setBorderPaintedFlat(true);
        chkKelainanEks.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKelainanEks.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKelainanEks.setName("chkKelainanEks"); // NOI18N
        chkKelainanEks.setOpaque(false);
        chkKelainanEks.setPreferredSize(new java.awt.Dimension(175, 23));
        chkKelainanEks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkKelainanEksActionPerformed(evt);
            }
        });
        FormInput.add(chkKelainanEks);
        chkKelainanEks.setBounds(220, 1302, 70, 23);

        TkelainanEks.setForeground(new java.awt.Color(0, 0, 0));
        TkelainanEks.setName("TkelainanEks"); // NOI18N
        TkelainanEks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkelainanEksKeyPressed(evt);
            }
        });
        FormInput.add(TkelainanEks);
        TkelainanEks.setBounds(293, 1302, 442, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("4. PEMERIKSAAN PENUNJANG PRE RAWAT INAP :");
        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(0, 1330, 290, 23);

        scrollPane16.setName("scrollPane16"); // NOI18N

        Tpemerikaaan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tpemerikaaan.setColumns(20);
        Tpemerikaaan.setRows(5);
        Tpemerikaaan.setName("Tpemerikaaan"); // NOI18N
        Tpemerikaaan.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tpemerikaaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpemerikaaanKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(Tpemerikaaan);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(40, 1358, 690, 230);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("5. DIAGNOSA KERJA :");
        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(0, 1594, 160, 23);

        scrollPane17.setName("scrollPane17"); // NOI18N

        TdiagnosaKerja.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TdiagnosaKerja.setColumns(20);
        TdiagnosaKerja.setRows(5);
        TdiagnosaKerja.setName("TdiagnosaKerja"); // NOI18N
        TdiagnosaKerja.setPreferredSize(new java.awt.Dimension(162, 2000));
        TdiagnosaKerja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaKerjaKeyPressed(evt);
            }
        });
        scrollPane17.setViewportView(TdiagnosaKerja);

        FormInput.add(scrollPane17);
        scrollPane17.setBounds(165, 1594, 565, 80);

        scrollPane18.setName("scrollPane18"); // NOI18N

        TdiagnosaBanding.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TdiagnosaBanding.setColumns(20);
        TdiagnosaBanding.setRows(5);
        TdiagnosaBanding.setName("TdiagnosaBanding"); // NOI18N
        TdiagnosaBanding.setPreferredSize(new java.awt.Dimension(162, 2000));
        TdiagnosaBanding.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaBandingKeyPressed(evt);
            }
        });
        scrollPane18.setViewportView(TdiagnosaBanding);

        FormInput.add(scrollPane18);
        scrollPane18.setBounds(165, 1680, 565, 80);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("6. DIAGNOSA BANDING :");
        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(0, 1680, 160, 23);

        scrollPane19.setName("scrollPane19"); // NOI18N

        Tpengobatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tpengobatan.setColumns(20);
        Tpengobatan.setRows(5);
        Tpengobatan.setName("Tpengobatan"); // NOI18N
        Tpengobatan.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tpengobatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpengobatanKeyPressed(evt);
            }
        });
        scrollPane19.setViewportView(Tpengobatan);

        FormInput.add(scrollPane19);
        scrollPane19.setBounds(165, 1765, 565, 80);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("7. PENGOBATAN :");
        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(0, 1765, 160, 23);

        scrollPane20.setName("scrollPane20"); // NOI18N

        Tdiet.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tdiet.setColumns(20);
        Tdiet.setRows(5);
        Tdiet.setName("Tdiet"); // NOI18N
        Tdiet.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tdiet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdietKeyPressed(evt);
            }
        });
        scrollPane20.setViewportView(Tdiet);

        FormInput.add(scrollPane20);
        scrollPane20.setBounds(165, 1850, 565, 80);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("8. DIET :");
        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(0, 1850, 160, 23);

        scrollPane21.setName("scrollPane21"); // NOI18N

        Trencana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Trencana.setColumns(20);
        Trencana.setRows(5);
        Trencana.setName("Trencana"); // NOI18N
        Trencana.setPreferredSize(new java.awt.Dimension(162, 2000));
        Trencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrencanaKeyPressed(evt);
            }
        });
        scrollPane21.setViewportView(Trencana);

        FormInput.add(scrollPane21);
        scrollPane21.setBounds(165, 1936, 565, 80);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("9. RENCANA :");
        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(0, 1936, 160, 23);

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
        BtnPasteHasil.setBounds(740, 1358, 100, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        FormInput.add(TtglLahir);
        TtglLahir.setBounds(649, 66, 80, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Tgl. Lahir :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(575, 66, 70, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(900, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setToolTipText("Silahkan Klik Untuk Membaca CPPT");
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(22, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormMenu.setBackground(new java.awt.Color(250, 250, 245));
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(150, 483));
        FormMenu.setLayout(new java.awt.GridLayout(1, 2));

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " CPPT ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbCPPT.setToolTipText("Silahkan klik untuk memilih data yang dibaca cpptnya");
        tbCPPT.setName("tbCPPT"); // NOI18N
        tbCPPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCPPTMouseClicked(evt);
            }
        });
        tbCPPT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCPPTKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbCPPT);

        FormMenu.add(Scroll4);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass14.setLayout(new java.awt.BorderLayout());

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Hasil Pemeriksaan, Analisa, Rencana, Penatalaksanaan Pasien ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N
        scrollPane5.setPreferredSize(new java.awt.Dimension(212, 450));

        Thasil.setEditable(false);
        Thasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Thasil.setColumns(20);
        Thasil.setRows(5);
        Thasil.setToolTipText("Silahkan klik kanan utk. copy data CPPT hasil pemeriksaan");
        Thasil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Thasil.setName("Thasil"); // NOI18N
        Thasil.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane5.setViewportView(Thasil);

        panelGlass14.add(scrollPane5, java.awt.BorderLayout.PAGE_START);

        scrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Instruksi Tenaga Kesehatan Termasuk Pasca Bedah/Prosedur ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setPreferredSize(new java.awt.Dimension(212, 150));

        Tinstruksi.setEditable(false);
        Tinstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinstruksi.setColumns(20);
        Tinstruksi.setRows(5);
        Tinstruksi.setToolTipText("Silahkan klik kanan utk. copy data CPPT instruksi nakes");
        Tinstruksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane4.setViewportView(Tinstruksi);

        panelGlass14.add(scrollPane4, java.awt.BorderLayout.CENTER);

        FormMenu.add(panelGlass14);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);

        internalFrame2.add(PanelAccor, java.awt.BorderLayout.EAST);

        TabRawat.addTab("Input Assesmen", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

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

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Pengkajian :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-01-2025" }));
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

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-01-2025" }));
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
            try {
                if (Sequel.menyimpantf("asesmen_medik_perinatologi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 162, new String[]{
                            TNoRw.getText(), TrgRawat.getText(), Tkeluhan.getText(), TriwPenyakitDahulu.getText(), hipertensi, diabet, jantung, strok, asma, kejang,
                            hati, kanker, tb, pms, perdarahan, ginjal, lainRiwayat, TketLainLain.getText(), cmbKondisi.getSelectedItem().toString(), TketAs.getText(),
                            Tgerak.getText(), Ttangis.getText(), TwarnaKulit.getText(), Thr.getText(), Tsuhu.getText(), Trr.getText(), Tsaturasi.getText(),
                            cmbCapilary.getSelectedItem().toString(), Tbbl.getText(), Tpb.getText(), Tlk.getText(), Tld.getText(), Tlp.getText(), Tlla.getText(),
                            turgor, Tturgor.getText(), sianosisKulit, perdarahanKulit, ikterusPos, ikterusNeg, kramer, Tkramer.getText(), hematoma, sklere, kutis,
                            lainKulit, TKetLainKulit.getText(), simetrisKepala, asimetrisKepala, cepal, caput, anen, micros, hidro, lainKepala, TKetLainKepala.getText(),
                            datar, cembung, cekung, lainUub, TKetLainUUB.getText(), normalMata, anemia, ikterus, sekretMata, LainMata, TKetLainMata.getText(),
                            normalTht, nch, sianosisTht, sekretTht, lainTht, TKetLainTHT.getText(), normalMulut, labioS, labioP, labioG, mukosa, TMukosa.getText(),
                            reflek, TReflek.getText(), lainMulut, TKetLainMulut.getText(), normalLeher, torti, benjolKanan, benjolKiri, lainLeher, TKetLainLeher.getText(),
                            simetrisDada, tidakSimetris, retraksiPos, retraksiNeg, TKetRetraksi.getText(), sesak, merintih, sianosisDada, lainDada, TKetLainDada.getText(),
                            bj, murni, tidakMurni, reguler, tidakReguler, bunyi, TKetBunyi.getText(), vesikuler, ronchi, wezing, stridor, lainParu, TKetLainParu.getText(),
                            supel, disten, bising, hepar, limpa, nyeri, TNyeri.getText(), masaPos, masaNeg, uk, Tuk.getText(), lokasi, Tlokasi.getText(), segar, layu,
                            lainTali, TKetLainTali.getText(), normalPunggung, spina, gibus, lainPunggung, TKetLainPunggung.getText(), sex, Tsex.getText(), kelainanUro,
                            TkelainanUro.getText(), bak, Tbak.getText(), cmbAnus.getSelectedItem().toString(), bab, Tbab.getText(), simetrisEks, asimetrisEks,
                            reflekMoroPos, reflekMoroNeg, lainEks, TKetLainEks.getText(), edema, kelainanEks, TkelainanEks.getText(), Valid.mysql_real_escape_stringERM(Tpemerikaaan.getText()),
                            TdiagnosaKerja.getText(), TdiagnosaBanding.getText(), Tpengobatan.getText(), Tdiet.getText(), Trencana.getText(), Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""),
                            cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), Tnip.getText(), Sequel.cariIsi("select now()")
                        }) == true) {

                    TCari.setText(TNoRw.getText());
                    TabRawat.setSelectedIndex(1);
                    emptTeks();
                    tampil();
                }
            } catch (Exception e) {
                System.out.println("Simpan Asesmen Medik Perinatologi : " + e);
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
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            if (tbAsesmen.getSelectedRow() > -1) {
                user = "";
                if (akses.getadmin() == true) {
                    user = "-";
                } else {
                    user = akses.getkode();
                }

                gantiDisimpan();
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
        WindowRiwayat.dispose();
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
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));

            param.put("keluhan", Tkeluhan.getText() + "\n");
            param.put("riwayatPenyakit", TriwPenyakitDahulu.getText() + "\n");
            
            if (chkHipertensi.isSelected() == true) {
                param.put("hipertensi", "V");
            } else {
                param.put("hipertensi", "");
            }
            
            if (chkDiabetes.isSelected() == true) {
                param.put("diabet", "V");
            } else {
                param.put("diabet", "");
            }
            
            if (chkJantung.isSelected() == true) {
                param.put("jantung", "V");
            } else {
                param.put("jantung", "");
            }
            
            if (chkStrok.isSelected() == true) {
                param.put("strok", "V");
            } else {
                param.put("strok", "");
            }
            
            if (chkAsma.isSelected() == true) {
                param.put("asma", "V");
            } else {
                param.put("asma", "");
            }
            
            if (chkKejang.isSelected() == true) {
                param.put("kejang", "V");
            } else {
                param.put("kejang", "");
            }
            
            if (chkHati.isSelected() == true) {
                param.put("hati", "V");
            } else {
                param.put("hati", "");
            }
            
            if (chkKanker.isSelected() == true) {
                param.put("kanker", "V");
            } else {
                param.put("kanker", "");
            }
            
            if (chkTB.isSelected() == true) {
                param.put("tb", "V");
            } else {
                param.put("tb", "");
            }
            
            if (chkPMS.isSelected() == true) {
                param.put("pms", "V");
            } else {
                param.put("pms", "");
            }
            
            if (chkPerdarahan.isSelected() == true) {
                param.put("perdarahan", "V");
            } else {
                param.put("perdarahan", "");
            }
            
            if (chkGinjal.isSelected() == true) {
                param.put("ginjal", "V");
            } else {
                param.put("ginjal", "");
            }
            
            if (chkLainLain.isSelected() == true) {
                param.put("LainRiwayat", "V");
                param.put("ketLainRiwayat", "Lain-lain : " + TketLainLain.getText());
            } else {
                param.put("LainRiwayat", "");
                param.put("ketLainRiwayat", "Lain-lain : ........");
            }
            
            param.put("kondisi", cmbKondisi.getSelectedItem().toString());
            
            if (TketAs.getText().equals("")) {
                param.put("ketAS", "AS : .......");
            } else {
                param.put("ketAS", "AS : " + TketAs.getText());
            }
            
            if (Tgerak.getText().equals("")) {
                param.put("gerak", "Gerak : .......");
            } else {
                param.put("gerak", "Gerak : " + Tgerak.getText());
            }

            if (Ttangis.getText().equals("")) {
                param.put("tangis", "Tangis : .......");
            } else {
                param.put("tangis", "Tangis : " + Ttangis.getText());
            }
            
            if (TwarnaKulit.getText().equals("")) {
                param.put("warna", "Warna Kulit : .......");
            } else {
                param.put("warna", "Warna Kulit : " + TwarnaKulit.getText());
            }
            
            param.put("hr", "HR : " + Thr.getText() + " x/menit");
            param.put("suhu", "Suhu : " + Tsuhu.getText() + " °C");
            param.put("rr", "RR : " + Trr.getText() + " x/menit");
            param.put("saturasi", "Sat O2" + Tsaturasi.getText() + " %");
            param.put("capilary", "Capillary Refill : " + cmbCapilary.getSelectedItem().toString());
            param.put("bbl", "BBL : " + Tbbl.getText() + " gram");
            param.put("pb", "PB : " + Tpb.getText() + " cm");
            param.put("lk", "LK : " + Tlk.getText() + " cm");
            param.put("ld", "LD : " + Tld.getText() + " cm");
            param.put("lp", "LP : " + Tlp.getText() + " cm");
            param.put("lla", "LLA : " + Tlla.getText() + " cm");

            if (chkTurgor.isSelected() == true) {
                param.put("turgor", "V");
                param.put("ketTurgor", "Turgor : " + Tturgor.getText());
            } else {
                param.put("turgor", "");
                param.put("ketTurgor", "Turgor : .......");
            }
            
            if (chkSianosisKulit.isSelected() == true) {
                param.put("sianosisKulit", "V");
            } else {
                param.put("sianosisKulit", "");
            }
            
            if (chkPerdarahanKulit.isSelected() == true) {
                param.put("perdarahanKulit", "V");
            } else {
                param.put("perdarahanKulit", "");
            }

            if (chkIkterusPositif.isSelected() == true) {
                param.put("ikterusPos", "V");
            } else {
                param.put("ikterusPos", "");
            }
            
            if (chkIkterusNegatif.isSelected() == true) {
                param.put("ikterusNeg", "V");
            } else {
                param.put("ikterusNeg", "");
            }
            
            if (chkKrammer.isSelected() == true) {
                param.put("kramer", "V");
                param.put("ketKramer", "Krammer : " + Tkramer.getText());
            } else {
                param.put("kramer", "");
                param.put("ketKramer", "Krammer : .......");
            }
            
            if (chkHematoma.isSelected() == true) {
                param.put("hematoma", "V");
            } else {
                param.put("hematoma", "");
            }
            
            if (chkSklerema.isSelected() == true) {
                param.put("sklere", "V");
            } else {
                param.put("sklere", "");
            }
            
            if (chkKutisMarmorata.isSelected() == true) {
                param.put("kutis", "V");
            } else {
                param.put("kutis", "");
            }
            
            if (chkLainKulit.isSelected() == true) {
                param.put("lainKulit", "V");
                param.put("ketLainKulit", "Lainnya : " + TKetLainKulit.getText());
            } else {
                param.put("lainKulit", "");
                param.put("ketLainKulit", "Lainnya : .......");
            }
            
            if (chkSimetrisKepala.isSelected() == true) {
                param.put("simetrisKepala", "V");
            } else {
                param.put("simetrisKepala", "");
            }
            
            if (chkAsimetrisKepala.isSelected() == true) {
                param.put("asimetrisKepala", "V");
            } else {
                param.put("asimetrisKepala", "");
            }
            
            if (chkCephal.isSelected() == true) {
                param.put("cepal", "V");
            } else {
                param.put("cepal", "");
            }
            
            if (chkCaput.isSelected() == true) {
                param.put("caput", "V");
            } else {
                param.put("caput", "");
            }
            
            if (chkAnensefali.isSelected() == true) {
                param.put("anen", "V");
            } else {
                param.put("anen", "");
            }
            
            if (chkMicrosefal.isSelected() == true) {
                param.put("micros", "V");
            } else {
                param.put("micros", "");
            }
            
            if (chkhydrosefalus.isSelected() == true) {
                param.put("hidro", "V");
            } else {
                param.put("hidro", "");
            }
            
            if (chkLainKepala.isSelected() == true) {
                param.put("lainKepala", "V");
                param.put("ketLainKepala", "Lainnya : " + TKetLainKepala.getText());
            } else {
                param.put("lainKepala", "");
                param.put("ketLainKepala", "Lainnya : .......");
            }
            
            if (chkDatar.isSelected() == true) {
                param.put("datar", "V");
            } else {
                param.put("datar", "");
            }
            
            if (chkCembung.isSelected() == true) {
                param.put("cembung", "V");
            } else {
                param.put("cembung", "");
            }
            
            if (chkCekung.isSelected() == true) {
                param.put("cekung", "V");
            } else {
                param.put("cekung", "");
            }
            
            if (chkLainUUB.isSelected() == true) {
                param.put("lainUUB", "V");
                param.put("ketLainUUB", "Lainnya : " + TKetLainUUB.getText());
            } else {
                param.put("lainUUB", "");
                param.put("ketLainUUB", "Lainnya : .......");
            }
            
            if (chkNormalMata.isSelected() == true) {
                param.put("normalMata", "V");
            } else {
                param.put("normalMata", "");
            }
            
            if (chkAnemia.isSelected() == true) {
                param.put("anemia", "V");
            } else {
                param.put("anemia", "");
            }
            
            if (chkIkterusMata.isSelected() == true) {
                param.put("ikterusMata", "V");
            } else {
                param.put("ikterusMata", "");
            }
            
            if (chkSekretMata.isSelected() == true) {
                param.put("sekretMata", "V");
            } else {
                param.put("sekretMata", "");
            }
            
            if (chkLainMata.isSelected() == true) {
                param.put("lainMata", "V");
                param.put("ketLainMata", "Lainnya : " + TKetLainMata.getText());
            } else {
                param.put("lainMata", "");
                param.put("ketLainMata", "Lainnya : .......");
            }
            
            if (chkNormalTHT.isSelected() == true) {
                param.put("normalTHT", "V");
            } else {
                param.put("normalTHT", "");
            }
            
            if (chkNCH.isSelected() == true) {
                param.put("nch", "V");
            } else {
                param.put("nch", "");
            }
            
            if (chkSianosisTHT.isSelected() == true) {
                param.put("sianosisTHT", "V");
            } else {
                param.put("sianosisTHT", "");
            }
            
            if (chkSekretTHT.isSelected() == true) {
                param.put("sekretTHT", "V");
            } else {
                param.put("sekretTHT", "");
            }
            
            if (chkLainTHT.isSelected() == true) {
                param.put("lainTHT", "V");
                param.put("ketLainTHT", "Lainnya : " + TKetLainTHT.getText());
            } else {
                param.put("lainTHT", "");
                param.put("ketLainTHT", "Lainnya : .......");
            }
            
            if (chkNormalMulut.isSelected() == true) {
                param.put("normalMulut", "V");
            } else {
                param.put("normalMulut", "");
            }
            
            if (chkLabioschisis.isSelected() == true) {
                param.put("labioS", "V");
            } else {
                param.put("labioS", "");
            }
            
            if (chkLabiopalatos.isSelected() == true) {
                param.put("labioP", "V");
            } else {
                param.put("labioP", "");
            }
            
            if (chkLabiog.isSelected() == true) {
                param.put("labioG", "V");
            } else {
                param.put("labioG", "");
            }
            
            if (chkMukosa.isSelected() == true) {
                param.put("mukosa", "V");
                param.put("ketMukosa", "Mukosa : Warna : " + TMukosa.getText());
            } else {
                param.put("mukosa", "");
                param.put("ketMukosa", "Mukosa : Warna : .......");
            }
            
            if (chkReflek.isSelected() == true) {
                param.put("reflek", "V");
                param.put("ketReflek", "Reflek Hisap : " + TReflek.getText());
            } else {
                param.put("reflek", "");
                param.put("ketReflek", "Reflek Hisap : .......");
            }
            
            if (chkLainMulut.isSelected() == true) {
                param.put("lainMulut", "V");
                param.put("ketLainMulut", "Lainnya : " + TKetLainMulut.getText());
            } else {
                param.put("lainMulut", "");
                param.put("ketLainMulut", "Lainnya : .......");
            }
            
            if (chkNormalLeher.isSelected() == true) {
                param.put("normalLeher", "V");
            } else {
                param.put("normalLeher", "");
            }
            
            if (chkTortikolis.isSelected() == true) {
                param.put("torti", "V");
            } else {
                param.put("torti", "");
            }
            
            if (chkBenjolanKanan.isSelected() == true) {
                param.put("benjolKanan", "V");
            } else {
                param.put("benjolKanan", "");
            }
            
            if (chkBenjolanKiri.isSelected() == true) {
                param.put("benjolKiri", "V");
            } else {
                param.put("benjolKiri", "");
            }
            
            if (chkLainLeher.isSelected() == true) {
                param.put("lainLeher", "V");
                param.put("ketLainLeher", "Lainnya : " + TKetLainLeher.getText());
            } else {
                param.put("lainLeher", "");
                param.put("ketLainLeher", "Lainnya : .......");
            }
            
            if (chkSimetrisDada.isSelected() == true) {
                param.put("simetrisDada", "V");
            } else {
                param.put("simetrisDada", "");
            }
            
            if (chkTidakSimetris.isSelected() == true) {
                param.put("tidakSimetris", "V");
            } else {
                param.put("tidakSimetris", "");
            }
            
            if (chkRetraksiPositif.isSelected() == true) {
                param.put("retraksiPos", "V");
            } else {
                param.put("retraksiPos", "");
            }
            
            if (chkRetraksiNegatif.isSelected() == true) {
                param.put("retraksiNeg", "V");
            } else {
                param.put("retraksiNeg", "");
            }
            
            if (TKetRetraksi.getText().equals("")) {
                param.put("dadaDi", "di .......");
            } else {
                param.put("dadaDi", "di " + TKetRetraksi.getText());
            }
            
            if (chkSesak.isSelected() == true) {
                param.put("sesak", "V");
            } else {
                param.put("sesak", "");
            }
            
            if (chkMerintih.isSelected() == true) {
                param.put("merintih", "V");
            } else {
                param.put("merintih", "");
            }
            
            if (chkSianosisDada.isSelected() == true) {
                param.put("sianosisDada", "V");
            } else {
                param.put("sianosisDada", "");
            }
            
            if (chkLainDada.isSelected() == true) {
                param.put("lainDada", "V");
                param.put("ketLainDada", "Lainnya : " + TKetLainDada.getText());
            } else {
                param.put("lainDada", "");
                param.put("ketLainDada", "Lainnya : .......");
            }
            
            if (chkBji.isSelected() == true) {
                param.put("bj", "V");
            } else {
                param.put("bj", "");
            }
            
            if (chkMurni.isSelected() == true) {
                param.put("murni", "V");
            } else {
                param.put("murni", "");
            }
            
            if (chkTidakMurni.isSelected() == true) {
                param.put("tidakMurni", "V");
            } else {
                param.put("tidakMurni", "");
            }
            
            if (chkReguler.isSelected() == true) {
                param.put("reguler", "V");
            } else {
                param.put("reguler", "");
            }
            
            if (chkTidakReguler.isSelected() == true) {
                param.put("tidakReguler", "V");
            } else {
                param.put("tidakReguler", "");
            }
            
            if (chkBunyi.isSelected() == true) {
                param.put("bunyi", "V");
                param.put("ketBunyi", "Bunyi Tambahan : " + TKetBunyi.getText());
            } else {
                param.put("bunyi", "");
                param.put("ketBunyi", "Bunyi Tambahan : .......");
            }
            
            if (chkVesikuler.isSelected() == true) {
                param.put("vesikuler", "V");
            } else {
                param.put("vesikuler", "");
            }
            
            if (chkRonchi.isSelected() == true) {
                param.put("ronci", "V");
            } else {
                param.put("ronci", "");
            }
            
            if (chkWhezing.isSelected() == true) {
                param.put("wezing", "V");
            } else {
                param.put("wezing", "");
            }
            
            if (chkStridor.isSelected() == true) {
                param.put("stridor", "V");
            } else {
                param.put("stridor", "");
            }
            
            if (chkLainParu.isSelected() == true) {
                param.put("lainParu", "V");
                param.put("ketLainParu", "Lainnya : " + TKetLainParu.getText());
            } else {
                param.put("lainParu", "");
                param.put("ketLainParu", "Lainnya : .......");
            }
            
            if (chkSupel.isSelected() == true) {
                param.put("supel", "V");
            } else {
                param.put("supel", "");
            }
            
            if (chkDistensi.isSelected() == true) {
                param.put("disten", "V");
            } else {
                param.put("disten", "");
            }
            
            if (chkBising.isSelected() == true) {
                param.put("bising", "V");
            } else {
                param.put("bising", "");
            }
            
            if (chkPembesaranHepar.isSelected() == true) {
                param.put("hepar", "V");
            } else {
                param.put("hepar", "");
            }
            
            if (chkPembesaranLimpa.isSelected() == true) {
                param.put("limpa", "V");
            } else {
                param.put("limpa", "");
            }
            
            if (chkNyeri.isSelected() == true) {
                param.put("nyeri", "V");
                param.put("ketNyeri", "Nyeri tekan, regio : " + TNyeri.getText());
            } else {
                param.put("nyeri", "");
                param.put("ketNyeri", "Nyeri tekan, regio : .......");
            }
            
            if (chkMasaPositif.isSelected() == true) {
                param.put("masaPos", "V");
            } else {
                param.put("masaPos", "");
            }
            
            if (chkMasaNegatif.isSelected() == true) {
                param.put("masaNeg", "V");
            } else {
                param.put("masaNeg", "");
            }
            
            if (chkUK.isSelected() == true) {
                param.put("uk", "V");
                param.put("ketUK", "UK : " + Tuk.getText());
            } else {
                param.put("uk", "");
                param.put("ketUK", "UK : .......");
            }
            
            if (chkLokasi.isSelected() == true) {
                param.put("lokasi", "V");
                param.put("ketLokasi", "Lokasi : " + Tlokasi.getText());
            } else {
                param.put("lokasi", "");
                param.put("ketLokasi", "Lokasi : .......");
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
            
            if (chkLainTali.isSelected() == true) {
                param.put("lainTali", "V");
                param.put("ketLainTali", "Lainnya : " + TKetLainTali.getText());
            } else {
                param.put("lainTali", "");
                param.put("ketLainTali", "Lainnya : .......");
            }
            
            if (chkNormalPunggung.isSelected() == true) {
                param.put("normalPunggung", "V");
            } else {
                param.put("normalPunggung", "");
            }
            
            if (chkSpina.isSelected() == true) {
                param.put("spina", "V");
            } else {
                param.put("spina", "");
            }
            
            if (chkGibus.isSelected() == true) {
                param.put("gibus", "V");
            } else {
                param.put("gibus", "");
            }
            
            if (chkLainPunggung.isSelected() == true) {
                param.put("lainPunggung", "V");
                param.put("ketLainPunggung", "Lainnya : " + TKetLainPunggung.getText());
            } else {
                param.put("lainPunggung", "");
                param.put("ketLainPunggung", "Lainnya : .......");
            }
            
            if (chkSex.isSelected() == true) {
                param.put("sex", "V");
                param.put("ketSex", "Sex : " + Tsex.getText());
            } else {
                param.put("sex", "");
                param.put("ketSex", "Sex : .......");
            }
            
            if (chkKelainanUro.isSelected() == true) {
                param.put("kelainanUro", "V");
                param.put("ketKelainanUro", "Kelainan : " + TkelainanUro.getText());
            } else {
                param.put("kelainanUro", "");
                param.put("ketKelainanUro", "Kelainan : .......");
            }
            
            if (chkBAK.isSelected() == true) {
                param.put("bak", "V");
                param.put("ketBAK", "BAK : " + Tbak.getText());
            } else {
                param.put("bak", "");
                param.put("ketBAK", "BAK : .......");
            }
            
            param.put("anus", cmbAnus.getSelectedItem().toString());
            
            if (chkBAB.isSelected() == true) {
                param.put("bab", "V");
                param.put("ketBAB", "BAB : " + Tbab.getText());
            } else {
                param.put("bab", "");
                param.put("ketBAB", "BAB : .......");
            }
            
            if (chkSimetrisEks.isSelected() == true) {
                param.put("simetrisEks", "V");
            } else {
                param.put("simetrisEks", "");
            }
            
            if (chkAsimetrisEks.isSelected() == true) {
                param.put("asimetrisEks", "V");
            } else {
                param.put("asimetrisEks", "");
            }
            
            if (chkReflekMoroPositif.isSelected() == true) {
                param.put("reflekMoroPos", "V");
            } else {
                param.put("reflekMoroPos", "");
            }
            
            if (chkReflekMoroNegatif.isSelected() == true) {
                param.put("reflekMoroNeg", "V");
            } else {
                param.put("reflekMoroNeg", "");
            }
            
            if (chkLainEks.isSelected() == true) {
                param.put("lainEks", "V");
                param.put("ketLainEks", "Lainnya : " + TKetLainEks.getText());
            } else {
                param.put("lainEks", "");
                param.put("ketLainEks", "Lainnya : .......");
            }
            
            if (chkEdema.isSelected() == true) {
                param.put("edema", "V");
            } else {
                param.put("edema", "");
            }
            
            if (chkKelainanEks.isSelected() == true) {
                param.put("kelainanEks", "V");
                param.put("ketKelainanEks", "Kelainan : " + TkelainanEks.getText());
            } else {
                param.put("kelainanEks", "");
                param.put("ketKelainanEks", "Kelainan : .......");
            }
            
            param.put("pemeriksaan", Tpemerikaaan.getText() + "\n");
            param.put("diagnosaKerja", TdiagnosaKerja.getText() + "\n");
            param.put("diagnosaBanding", TdiagnosaBanding.getText() + "\n");
            param.put("pengobatan", Tpengobatan.getText() + "\n");
            param.put("diet", Tdiet.getText() + "\n");
            param.put("rencana", Trencana.getText() + "\n");

            param.put("tanggal", "Tanggal " + Valid.SetTglINDONESIA(Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""))
                    + ", Jam : " + cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " Wita");
            param.put("dokter", "(" + TnmDokter.getText() + ")");

            Valid.MyReport("rptCetakAsesmenMedikPerinatologi2.jasper", "report", "::[ Laporan Asesmen Medik Perinatologi hal. 2 ]::",
                    "SELECT now() tanggal", param);
            Valid.MyReport("rptCetakAsesmenMedikPerinatologi1.jasper", "report", "::[ Laporan Asesmen Medik Perinatologi hal. 1 ]::",
                    "SELECT now() tanggal", param);
            
            emptTeks();            
            TabRawat.setSelectedIndex(1);
            tampil();            
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
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
        if (TabRawat.getSelectedIndex() == 0) {
            ChkAccor.setSelected(false);
            isMenu();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (Sequel.cariInteger("select count(-1) from asesmen_medik_perinatologi where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from asesmen_medik_perinatologi where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        isMenu();
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void tbCPPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCPPTMouseClicked
        if (tabModeCppt.getRowCount() != 0) {
            try {
                getDataCppt();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbCPPTMouseClicked

    private void tbCPPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyPressed
        if (tabModeCppt.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataCppt();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbCPPTKeyPressed

    private void MnRiwayatDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatDataActionPerformed
        ChkAccor.setSelected(false);
        isMenu();

        DTPCari3.setDate(new Date());
        DTPCari4.setDate(new Date());
        TCari2.setText(TNoRM.getText());        
        BtnCari2ActionPerformed(null);
        WindowRiwayat.setSize(985, internalFrame1.getHeight() - 40);
        WindowRiwayat.setLocationRelativeTo(internalFrame1);
        WindowRiwayat.setAlwaysOnTop(false);
        WindowRiwayat.setVisible(true);
    }//GEN-LAST:event_MnRiwayatDataActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn10.requestFocus();
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilRiwayat();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari2ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari2, BtnAll1);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari2.setText("");
        tampilRiwayat();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAll1ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari2, TCari2);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnRestorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRestorActionPerformed
        if (tbRiwayat.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data yang dipilih & telah " + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 6).toString()
                    + " akan dikembalikan/restore..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 6).toString().equals("DIHAPUS")) {
                    if (Sequel.cariInteger("select count(-1) from asesmen_medik_perinatologi where "
                            + "no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString() + "'") > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Proses kembalikan/restore data gagal, krn. sudah ada datanya dg. no. rawat yg. sama..!!");
                    } else {
                        kembalikanData();
                        TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                        tampil();
                        emptTeks();
                        TabRawat.setSelectedIndex(1);
                    }
                } else {
                    kembalikanDataDiganti();
                    TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                    tampil();
                    emptTeks();
                    TabRawat.setSelectedIndex(1);
                }
            }
        } else {
            WindowRiwayat.setSize(1043, internalFrame1.getHeight() - 40);
            WindowRiwayat.setLocationRelativeTo(internalFrame1);
            WindowRiwayat.setAlwaysOnTop(false);
            WindowRiwayat.setVisible(true);
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnRestorActionPerformed

    private void BtnCloseIn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn10ActionPerformed
        WindowRiwayat.dispose();
        TCari2.setText("");
    }//GEN-LAST:event_BtnCloseIn10ActionPerformed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMAsesmenMedikPerinatologi");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        ChkAccor.setSelected(false);
        isMenu();

        akses.setform("RMAsesmenMedikPerinatologi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void TkeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeluhanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TriwPenyakitDahulu.requestFocus();
        }
    }//GEN-LAST:event_TkeluhanKeyPressed

    private void TriwPenyakitDahuluKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwPenyakitDahuluKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            chkHipertensi.requestFocus();
        }
    }//GEN-LAST:event_TriwPenyakitDahuluKeyPressed

    private void TpemerikaaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpemerikaaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TdiagnosaKerja.requestFocus();
        }
    }//GEN-LAST:event_TpemerikaaanKeyPressed

    private void TdiagnosaKerjaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaKerjaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TdiagnosaBanding.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaKerjaKeyPressed

    private void TdiagnosaBandingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaBandingKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tpengobatan.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaBandingKeyPressed

    private void TpengobatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpengobatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tdiet.requestFocus();
        }
    }//GEN-LAST:event_TpengobatanKeyPressed

    private void TdietKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdietKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Trencana.requestFocus();
        }
    }//GEN-LAST:event_TdietKeyPressed

    private void TrencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrencanaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnDokter.requestFocus();
        }
    }//GEN-LAST:event_TrencanaKeyPressed

    private void chkLainLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainLainActionPerformed
        TketLainLain.setText("");
        if (chkLainLain.isSelected() == true) {
            TketLainLain.setEnabled(true);
            TketLainLain.requestFocus();
        } else {
            TketLainLain.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainLainActionPerformed

    private void chkTurgorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTurgorActionPerformed
        Tturgor.setText("");
        if (chkTurgor.isSelected() == true) {
            Tturgor.setEnabled(true);
            Tturgor.requestFocus();
        } else {
            Tturgor.setEnabled(false);
        }
    }//GEN-LAST:event_chkTurgorActionPerformed

    private void chkKrammerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkKrammerActionPerformed
        Tkramer.setText("");
        if (chkKrammer.isSelected() == true) {
            Tkramer.setEnabled(true);
            Tkramer.requestFocus();
        } else {
            Tkramer.setEnabled(false);
        }
    }//GEN-LAST:event_chkKrammerActionPerformed

    private void chkLainKulitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainKulitActionPerformed
        TKetLainKulit.setText("");
        if (chkLainKulit.isSelected() == true) {
            TKetLainKulit.setEnabled(true);
            TKetLainKulit.requestFocus();
        } else {
            TKetLainKulit.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainKulitActionPerformed

    private void chkLainKepalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainKepalaActionPerformed
        TKetLainKepala.setText("");
        if (chkLainKepala.isSelected() == true) {
            TKetLainKepala.setEnabled(true);
            TKetLainKepala.requestFocus();
        } else {
            TKetLainKepala.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainKepalaActionPerformed

    private void chkLainUUBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainUUBActionPerformed
        TKetLainUUB.setText("");
        if (chkLainUUB.isSelected() == true) {
            TKetLainUUB.setEnabled(true);
            TKetLainUUB.requestFocus();
        } else {
            TKetLainUUB.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainUUBActionPerformed

    private void chkLainMataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainMataActionPerformed
        TKetLainMata.setText("");
        if (chkLainMata.isSelected() == true) {
            TKetLainMata.setEnabled(true);
            TKetLainMata.requestFocus();
        } else {
            TKetLainMata.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainMataActionPerformed

    private void chkLainTHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainTHTActionPerformed
        TKetLainTHT.setText("");
        if (chkLainTHT.isSelected() == true) {
            TKetLainTHT.setEnabled(true);
            TKetLainTHT.requestFocus();
        } else {
            TKetLainTHT.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainTHTActionPerformed

    private void chkMukosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMukosaActionPerformed
        TMukosa.setText("");
        if (chkMukosa.isSelected() == true) {
            TMukosa.setEnabled(true);
            TMukosa.requestFocus();
        } else {
            TMukosa.setEnabled(false);
        }
    }//GEN-LAST:event_chkMukosaActionPerformed

    private void chkReflekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkReflekActionPerformed
        TReflek.setText("");
        if (chkReflek.isSelected() == true) {
            TReflek.setEnabled(true);
            TReflek.requestFocus();
        } else {
            TReflek.setEnabled(false);
        }
    }//GEN-LAST:event_chkReflekActionPerformed

    private void chkLainMulutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainMulutActionPerformed
        TKetLainMulut.setText("");
        if (chkLainMulut.isSelected() == true) {
            TKetLainMulut.setEnabled(true);
            TKetLainMulut.requestFocus();
        } else {
            TKetLainMulut.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainMulutActionPerformed

    private void chkLainLeherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainLeherActionPerformed
        TKetLainLeher.setText("");
        if (chkLainLeher.isSelected() == true) {
            TKetLainLeher.setEnabled(true);
            TKetLainLeher.requestFocus();
        } else {
            TKetLainLeher.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainLeherActionPerformed

    private void chkLainDadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainDadaActionPerformed
        TKetLainDada.setText("");
        if (chkLainDada.isSelected() == true) {
            TKetLainDada.setEnabled(true);
            TKetLainDada.requestFocus();
        } else {
            TKetLainDada.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainDadaActionPerformed

    private void chkBunyiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBunyiActionPerformed
        TKetBunyi.setText("");
        if (chkBunyi.isSelected() == true) {
            TKetBunyi.setEnabled(true);
            TKetBunyi.requestFocus();
        } else {
            TKetBunyi.setEnabled(false);
        }
    }//GEN-LAST:event_chkBunyiActionPerformed

    private void chkLainParuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainParuActionPerformed
        TKetLainParu.setText("");
        if (chkLainParu.isSelected() == true) {
            TKetLainParu.setEnabled(true);
            TKetLainParu.requestFocus();
        } else {
            TKetLainParu.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainParuActionPerformed

    private void chkNyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNyeriActionPerformed
        TNyeri.setText("");
        if (chkNyeri.isSelected() == true) {
            TNyeri.setEnabled(true);
            TNyeri.requestFocus();
        } else {
            TNyeri.setEnabled(false);
        }
    }//GEN-LAST:event_chkNyeriActionPerformed

    private void chkUKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkUKActionPerformed
        Tuk.setText("");
        if (chkUK.isSelected() == true) {
            Tuk.setEnabled(true);
            Tuk.requestFocus();
        } else {
            Tuk.setEnabled(false);
        }
    }//GEN-LAST:event_chkUKActionPerformed

    private void chkLokasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLokasiActionPerformed
        Tlokasi.setText("");
        if (chkLokasi.isSelected() == true) {
            Tlokasi.setEnabled(true);
            Tlokasi.requestFocus();
        } else {
            Tlokasi.setEnabled(false);
        }
    }//GEN-LAST:event_chkLokasiActionPerformed

    private void chkLainTaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainTaliActionPerformed
        TKetLainTali.setText("");
        if (chkLainTali.isSelected() == true) {
            TKetLainTali.setEnabled(true);
            TKetLainTali.requestFocus();
        } else {
            TKetLainTali.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainTaliActionPerformed

    private void chkLainPunggungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainPunggungActionPerformed
        TKetLainPunggung.setText("");
        if (chkLainPunggung.isSelected() == true) {
            TKetLainPunggung.setEnabled(true);
            TKetLainPunggung.requestFocus();
        } else {
            TKetLainPunggung.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainPunggungActionPerformed

    private void chkSexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSexActionPerformed
        Tsex.setText("");
        if (chkSex.isSelected() == true) {
            Tsex.setEnabled(true);
            Tsex.requestFocus();
        } else {
            Tsex.setEnabled(false);
        }
    }//GEN-LAST:event_chkSexActionPerformed

    private void chkKelainanUroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkKelainanUroActionPerformed
        TkelainanUro.setText("");
        if (chkKelainanUro.isSelected() == true) {
            TkelainanUro.setEnabled(true);
            TkelainanUro.requestFocus();
        } else {
            TkelainanUro.setEnabled(false);
        }
    }//GEN-LAST:event_chkKelainanUroActionPerformed

    private void chkBAKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBAKActionPerformed
        Tbak.setText("");
        if (chkBAK.isSelected() == true) {
            Tbak.setEnabled(true);
            Tbak.requestFocus();
        } else {
            Tbak.setEnabled(false);
        }
    }//GEN-LAST:event_chkBAKActionPerformed

    private void chkBABActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBABActionPerformed
        Tbab.setText("");
        if (chkBAB.isSelected() == true) {
            Tbab.setEnabled(true);
            Tbab.requestFocus();
        } else {
            Tbab.setEnabled(false);
        }
    }//GEN-LAST:event_chkBABActionPerformed

    private void chkLainEksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainEksActionPerformed
        TKetLainEks.setText("");
        if (chkLainEks.isSelected() == true) {
            TKetLainEks.setEnabled(true);
            TKetLainEks.requestFocus();
        } else {
            TKetLainEks.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainEksActionPerformed

    private void chkKelainanEksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkKelainanEksActionPerformed
        TkelainanEks.setText("");
        if (chkKelainanEks.isSelected() == true) {
            TkelainanEks.setEnabled(true);
            TkelainanEks.requestFocus();
        } else {
            TkelainanEks.setEnabled(false);
        }
    }//GEN-LAST:event_chkKelainanEksActionPerformed

    private void TketLainLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketLainLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbKondisi.requestFocus();
        }
    }//GEN-LAST:event_TketLainLainKeyPressed

    private void TketAsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketAsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tgerak.requestFocus();
        }
    }//GEN-LAST:event_TketAsKeyPressed

    private void TgerakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgerakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttangis.requestFocus();
        }
    }//GEN-LAST:event_TgerakKeyPressed

    private void TtangisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtangisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TwarnaKulit.requestFocus();
        }
    }//GEN-LAST:event_TtangisKeyPressed

    private void TwarnaKulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TwarnaKulitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Thr.requestFocus();
        }
    }//GEN-LAST:event_TwarnaKulitKeyPressed

    private void ThrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsuhu.requestFocus();
        }
    }//GEN-LAST:event_ThrKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trr.requestFocus();
        }
    }//GEN-LAST:event_TsuhuKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsaturasi.requestFocus();
        }
    }//GEN-LAST:event_TrrKeyPressed

    private void TsaturasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsaturasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbCapilary.requestFocus();
        }
    }//GEN-LAST:event_TsaturasiKeyPressed

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
            Tlla.requestFocus();
        }
    }//GEN-LAST:event_TlpKeyPressed

    private void TllaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TllaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTurgor.requestFocus();
        }
    }//GEN-LAST:event_TllaKeyPressed

    private void TturgorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TturgorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSianosisKulit.requestFocus();
        }
    }//GEN-LAST:event_TturgorKeyPressed

    private void TkramerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkramerKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkHematoma.requestFocus();
        }
    }//GEN-LAST:event_TkramerKeyPressed

    private void TKetLainKulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetLainKulitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSimetrisKepala.requestFocus();
        }
    }//GEN-LAST:event_TKetLainKulitKeyPressed

    private void TKetLainKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetLainKepalaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkDatar.requestFocus();
        }
    }//GEN-LAST:event_TKetLainKepalaKeyPressed

    private void TKetLainUUBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetLainUUBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkNormalMata.requestFocus();
        }
    }//GEN-LAST:event_TKetLainUUBKeyPressed

    private void TKetLainMataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetLainMataKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkNormalTHT.requestFocus();
        }
    }//GEN-LAST:event_TKetLainMataKeyPressed

    private void TKetLainTHTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetLainTHTKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkNormalMulut.requestFocus();
        }
    }//GEN-LAST:event_TKetLainTHTKeyPressed

    private void TMukosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMukosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkReflek.requestFocus();
        }
    }//GEN-LAST:event_TMukosaKeyPressed

    private void TReflekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TReflekKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkLainMulut.requestFocus();
        }
    }//GEN-LAST:event_TReflekKeyPressed

    private void TKetLainMulutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetLainMulutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkNormalLeher.requestFocus();
        }
    }//GEN-LAST:event_TKetLainMulutKeyPressed

    private void TKetLainLeherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetLainLeherKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSimetrisDada.requestFocus();
        }
    }//GEN-LAST:event_TKetLainLeherKeyPressed

    private void TKetRetraksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetRetraksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSesak.requestFocus();
        }
    }//GEN-LAST:event_TKetRetraksiKeyPressed

    private void TKetLainDadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetLainDadaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkBji.requestFocus();
        }
    }//GEN-LAST:event_TKetLainDadaKeyPressed

    private void TKetBunyiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetBunyiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkVesikuler.requestFocus();
        }
    }//GEN-LAST:event_TKetBunyiKeyPressed

    private void TKetLainParuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetLainParuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSupel.requestFocus();
        }
    }//GEN-LAST:event_TKetLainParuKeyPressed

    private void TNyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNyeriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkMasaPositif.requestFocus();
        }
    }//GEN-LAST:event_TNyeriKeyPressed

    private void TukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TukKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkLokasi.requestFocus();
        }
    }//GEN-LAST:event_TukKeyPressed

    private void TlokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlokasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSegar.requestFocus();
        }
    }//GEN-LAST:event_TlokasiKeyPressed

    private void TKetLainTaliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetLainTaliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkNormalPunggung.requestFocus();
        }
    }//GEN-LAST:event_TKetLainTaliKeyPressed

    private void TKetLainPunggungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetLainPunggungKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSex.requestFocus();
        }
    }//GEN-LAST:event_TKetLainPunggungKeyPressed

    private void TsexKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsexKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkKelainanUro.requestFocus();
        }
    }//GEN-LAST:event_TsexKeyPressed

    private void TkelainanUroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkelainanUroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkBAK.requestFocus();
        }
    }//GEN-LAST:event_TkelainanUroKeyPressed

    private void TbakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbAnus.requestFocus();
        }
    }//GEN-LAST:event_TbakKeyPressed

    private void TbabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSimetrisEks.requestFocus();
        }
    }//GEN-LAST:event_TbabKeyPressed

    private void TKetLainEksKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKetLainEksKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkEdema.requestFocus();
        }
    }//GEN-LAST:event_TKetLainEksKeyPressed

    private void TkelainanEksKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkelainanEksKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpemerikaaan.requestFocus();
        }
    }//GEN-LAST:event_TkelainanEksKeyPressed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMAsesmenMedikPerinatologi");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void BtnPasteHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteHasilActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (Tpemerikaaan.getText().equals("")) {
                Tpemerikaaan.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                Tpemerikaaan.setText(Tpemerikaaan.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteHasilActionPerformed

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        kodekamar = "";
        kodekamar = Sequel.cariIsi("select ki.kd_kamar from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
            + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
            + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMAsesmenMedikPerinatologi");
        DlgCatatanResep form = new DlgCatatanResep(null, false);
        form.isCek();
        form.setData(TNoRw.getText(), "ranap");
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        ChkAccor.setSelected(false);
        isMenu();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnResepActionPerformed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (tbRiwayat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data riwayat asesmen medik perinatologi masih kosong...!!!");
            tbRiwayat.requestFocus();
        } else {
            if (tbRiwayat.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from asesmen_medik_perinatologi_histori where waktu_eksekusi=?", 1, new String[]{
                        tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString()
                    }) == true) {
                        tampilRiwayat();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                } else {
                    tampilRiwayat();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbRiwayat.requestFocus();
            }
        }
    }//GEN-LAST:event_MnHapusActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenMedikPerinatologi dialog = new RMAsesmenMedikPerinatologi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll1;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari2;
    private widget.Button BtnCloseIn10;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPasteHasil;
    private widget.Button BtnPrint;
    private widget.Button BtnResep;
    private widget.Button BtnRestor;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAccor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMenu;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHapus;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private javax.swing.JMenuItem MnRiwayatData;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll6;
    private widget.TextBox TCari;
    private widget.TextBox TCari2;
    private widget.TextBox TKetBunyi;
    private widget.TextBox TKetLainDada;
    private widget.TextBox TKetLainEks;
    private widget.TextBox TKetLainKepala;
    private widget.TextBox TKetLainKulit;
    private widget.TextBox TKetLainLeher;
    private widget.TextBox TKetLainMata;
    private widget.TextBox TKetLainMulut;
    private widget.TextBox TKetLainParu;
    private widget.TextBox TKetLainPunggung;
    private widget.TextBox TKetLainTHT;
    private widget.TextBox TKetLainTali;
    private widget.TextBox TKetLainUUB;
    private widget.TextBox TKetRetraksi;
    private widget.TextBox TMukosa;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TNyeri;
    private widget.TextBox TPasien;
    private widget.TextBox TReflek;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tbab;
    private widget.TextBox Tbak;
    private widget.TextBox Tbbl;
    private widget.TextArea TdiagnosaBanding;
    private widget.TextArea TdiagnosaKerja;
    private widget.TextArea Tdiet;
    private widget.TextBox Tgerak;
    private widget.TextArea Thasil;
    private widget.TextBox Thr;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tjk;
    private widget.TextBox TkelainanEks;
    private widget.TextBox TkelainanUro;
    private widget.TextArea Tkeluhan;
    private widget.TextBox TketAs;
    private widget.TextBox TketLainLain;
    private widget.TextBox Tkramer;
    private widget.TextBox Tld;
    private widget.TextBox Tlk;
    private widget.TextBox Tlla;
    private widget.TextBox Tlokasi;
    private widget.TextBox Tlp;
    private widget.TextBox Tnip;
    private widget.TextBox TnmDokter;
    private widget.TextBox Tpb;
    private widget.TextArea Tpemerikaaan;
    private widget.TextArea Tpengobatan;
    private widget.TextArea Trencana;
    private widget.TextBox TrgRawat;
    private widget.TextArea TriwPenyakitDahulu;
    private widget.TextBox Trr;
    private widget.TextBox Tsaturasi;
    private widget.TextBox Tsex;
    private widget.TextBox Tsuhu;
    private widget.TextBox Ttangis;
    private widget.Tanggal TtglAsesmen;
    private widget.TextBox TtglLahir;
    private widget.TextBox Tturgor;
    private widget.TextBox Tuk;
    private widget.TextBox TwarnaKulit;
    private javax.swing.JDialog WindowRiwayat;
    public widget.CekBox chkAnemia;
    public widget.CekBox chkAnensefali;
    public widget.CekBox chkAsimetrisEks;
    public widget.CekBox chkAsimetrisKepala;
    public widget.CekBox chkAsma;
    public widget.CekBox chkBAB;
    public widget.CekBox chkBAK;
    public widget.CekBox chkBenjolanKanan;
    public widget.CekBox chkBenjolanKiri;
    public widget.CekBox chkBising;
    public widget.CekBox chkBji;
    public widget.CekBox chkBunyi;
    public widget.CekBox chkCaput;
    public widget.CekBox chkCekung;
    public widget.CekBox chkCembung;
    public widget.CekBox chkCephal;
    public widget.CekBox chkDatar;
    public widget.CekBox chkDiabetes;
    public widget.CekBox chkDistensi;
    public widget.CekBox chkEdema;
    public widget.CekBox chkGibus;
    public widget.CekBox chkGinjal;
    public widget.CekBox chkHati;
    public widget.CekBox chkHematoma;
    public widget.CekBox chkHipertensi;
    public widget.CekBox chkIkterusMata;
    public widget.CekBox chkIkterusNegatif;
    public widget.CekBox chkIkterusPositif;
    public widget.CekBox chkJantung;
    public widget.CekBox chkKanker;
    public widget.CekBox chkKejang;
    public widget.CekBox chkKelainanEks;
    public widget.CekBox chkKelainanUro;
    public widget.CekBox chkKrammer;
    public widget.CekBox chkKutisMarmorata;
    public widget.CekBox chkLabiog;
    public widget.CekBox chkLabiopalatos;
    public widget.CekBox chkLabioschisis;
    public widget.CekBox chkLainDada;
    public widget.CekBox chkLainEks;
    public widget.CekBox chkLainKepala;
    public widget.CekBox chkLainKulit;
    public widget.CekBox chkLainLain;
    public widget.CekBox chkLainLeher;
    public widget.CekBox chkLainMata;
    public widget.CekBox chkLainMulut;
    public widget.CekBox chkLainParu;
    public widget.CekBox chkLainPunggung;
    public widget.CekBox chkLainTHT;
    public widget.CekBox chkLainTali;
    public widget.CekBox chkLainUUB;
    public widget.CekBox chkLayu;
    public widget.CekBox chkLokasi;
    public widget.CekBox chkMasaNegatif;
    public widget.CekBox chkMasaPositif;
    public widget.CekBox chkMerintih;
    public widget.CekBox chkMicrosefal;
    public widget.CekBox chkMukosa;
    public widget.CekBox chkMurni;
    public widget.CekBox chkNCH;
    public widget.CekBox chkNormalLeher;
    public widget.CekBox chkNormalMata;
    public widget.CekBox chkNormalMulut;
    public widget.CekBox chkNormalPunggung;
    public widget.CekBox chkNormalTHT;
    public widget.CekBox chkNyeri;
    public widget.CekBox chkPMS;
    public widget.CekBox chkPembesaranHepar;
    public widget.CekBox chkPembesaranLimpa;
    public widget.CekBox chkPerdarahan;
    public widget.CekBox chkPerdarahanKulit;
    public widget.CekBox chkReflek;
    public widget.CekBox chkReflekMoroNegatif;
    public widget.CekBox chkReflekMoroPositif;
    public widget.CekBox chkReguler;
    public widget.CekBox chkRetraksiNegatif;
    public widget.CekBox chkRetraksiPositif;
    public widget.CekBox chkRonchi;
    public widget.CekBox chkSegar;
    public widget.CekBox chkSekretMata;
    public widget.CekBox chkSekretTHT;
    public widget.CekBox chkSesak;
    public widget.CekBox chkSex;
    public widget.CekBox chkSianosisDada;
    public widget.CekBox chkSianosisKulit;
    public widget.CekBox chkSianosisTHT;
    public widget.CekBox chkSimetrisDada;
    public widget.CekBox chkSimetrisEks;
    public widget.CekBox chkSimetrisKepala;
    public widget.CekBox chkSklerema;
    public widget.CekBox chkSpina;
    public widget.CekBox chkStridor;
    public widget.CekBox chkStrok;
    public widget.CekBox chkSupel;
    public widget.CekBox chkTB;
    public widget.CekBox chkTidakMurni;
    public widget.CekBox chkTidakReguler;
    public widget.CekBox chkTidakSimetris;
    public widget.CekBox chkTortikolis;
    public widget.CekBox chkTurgor;
    public widget.CekBox chkUK;
    public widget.CekBox chkVesikuler;
    public widget.CekBox chkWhezing;
    public widget.CekBox chkhydrosefalus;
    private widget.ComboBox cmbAnus;
    private widget.ComboBox cmbCapilary;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbKondisi;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
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
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.ScrollPane scrollPane18;
    private widget.ScrollPane scrollPane19;
    private widget.ScrollPane scrollPane20;
    private widget.ScrollPane scrollPane21;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbAsesmen;
    private widget.Table tbCPPT;
    private widget.Table tbRiwayat;
    // End of variables declaration//GEN-END:variables

     private void tampil() {
        Valid.tabelKosong(tabMode);
         try {
             ps = koneksi.prepareStatement("SELECT am.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, "
                     + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllahir, DATE_FORMAT(am.tgl_asesmen,'%d-%m-%Y') tglAses, TIME_FORMAT(am.jam_asesmen,'%H:%i') jamAses, "
                     + "pg.nama nmDokter from asesmen_medik_perinatologi am inner join reg_periksa rp on rp.no_rawat=am.no_rawat "
                     + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=am.nip_dpjp WHERE "
                     + "am.tgl_asesmen BETWEEN ? AND ? AND rp.no_rawat LIKE ? OR "
                     + "am.tgl_asesmen BETWEEN ? AND ? AND p.no_rkm_medis LIKE ? OR "
                     + "am.tgl_asesmen BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                     + "am.tgl_asesmen BETWEEN ? AND ? AND pg.nama LIKE ? ORDER BY am.tgl_asesmen");
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
                        rs.getString("jenkel"),
                        rs.getString("tgllahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglAses"),
                        rs.getString("jamAses"),
                        rs.getString("nmDokter"),                        
                        rs.getString("keluhan"),
                        rs.getString("riw_penyakit_dahulu"),
                        rs.getString("hipertensi"),
                        rs.getString("diabetes"),
                        rs.getString("jantung"),
                        rs.getString("stroke"),
                        rs.getString("asma"),
                        rs.getString("kejang"),
                        rs.getString("hati"),
                        rs.getString("kanker"),
                        rs.getString("tb"),
                        rs.getString("pms"),
                        rs.getString("perdarahan"),
                        rs.getString("ginjal"),
                        rs.getString("lain_lain"),
                        rs.getString("ket_lain_lain"),
                        rs.getString("kondisi_saat_lahir"),
                        rs.getString("ket_as"),
                        rs.getString("gerak"),
                        rs.getString("tangis"),
                        rs.getString("warna_kulit"),
                        rs.getString("hr"),
                        rs.getString("suhu"),
                        rs.getString("rr"),
                        rs.getString("saturasi"),
                        rs.getString("capilary_refill"),
                        rs.getString("bbl"),
                        rs.getString("pb"),
                        rs.getString("lk"),
                        rs.getString("ld"),
                        rs.getString("lp"),
                        rs.getString("lla"),
                        rs.getString("turgor"),
                        rs.getString("ket_turgor"),
                        rs.getString("sianosis_kulit"),
                        rs.getString("perdarahan_kulit"),
                        rs.getString("ikterus_positif"),
                        rs.getString("ikterus_negatif"),
                        rs.getString("krammer"),
                        rs.getString("ket_krammer"),
                        rs.getString("hematoma"),
                        rs.getString("sklerema"),
                        rs.getString("kutis"),
                        rs.getString("lainya_kulit"),
                        rs.getString("ket_lainya_kulit"),
                        rs.getString("simetris_kapala"),
                        rs.getString("asimetris_kepala"),
                        rs.getString("cephal_hematom"),
                        rs.getString("caput_succedaneum"),
                        rs.getString("anensefali"),
                        rs.getString("microsefal"),
                        rs.getString("hydrosefalus"),
                        rs.getString("lainya_kepala"),
                        rs.getString("ket_lainya_kepala"),
                        rs.getString("datar"),
                        rs.getString("cembung"),
                        rs.getString("cekung"),
                        rs.getString("lainya_uub"),
                        rs.getString("ket_lainya_uub"),
                        rs.getString("normal_mata"),
                        rs.getString("anemia"),
                        rs.getString("ikterus_mata"),
                        rs.getString("sekret_mata"),
                        rs.getString("lainya_mata"),
                        rs.getString("ket_lainya_mata"),
                        rs.getString("normal_tht"),
                        rs.getString("nch"),
                        rs.getString("sianosis_tht"),
                        rs.getString("sekret_tht"),
                        rs.getString("lainya_tht"),
                        rs.getString("ket_lainya_tht"),
                        rs.getString("normal_mulut"),
                        rs.getString("labioschisis"),
                        rs.getString("labiopalatoschisis"),
                        rs.getString("labiognatopalatoschisis"),
                        rs.getString("mucosa_warna"),
                        rs.getString("ket_warna"),
                        rs.getString("reflek_hisap"),
                        rs.getString("ket_reflek_hisap"),
                        rs.getString("lainya_mulut"),
                        rs.getString("ket_lainya_mulut"),
                        rs.getString("normal_leher"),
                        rs.getString("tortikolis"),
                        rs.getString("benjolan_kanan"),
                        rs.getString("benjolan_kiri"),
                        rs.getString("lainya_leher"),
                        rs.getString("ket_lainya_leher"),
                        rs.getString("simetris_dada"),
                        rs.getString("tidak_simetris"),
                        rs.getString("retraksi_positif"),
                        rs.getString("retraksi_negatif"),
                        rs.getString("ket_retraksi"),
                        rs.getString("sesak"),
                        rs.getString("merintih"),
                        rs.getString("sianosis_dada"),
                        rs.getString("lainya_dada"),
                        rs.getString("ket_lainya_dada"),
                        rs.getString("bji"),
                        rs.getString("murni"),
                        rs.getString("tidak_murni"),
                        rs.getString("reguler"),
                        rs.getString("tidak_reguler"),
                        rs.getString("bunyi_tambahan"),
                        rs.getString("ket_bunyi_tambahan"),
                        rs.getString("vesikuler"),
                        rs.getString("ronchi"),
                        rs.getString("whezing"),
                        rs.getString("stridor"),
                        rs.getString("lainya_paru"),
                        rs.getString("ket_lainya_paru"),
                        rs.getString("supel"),
                        rs.getString("distensi"),
                        rs.getString("bising_usus"),
                        rs.getString("pembesaran_hepar"),
                        rs.getString("pembesaran_limpa"),
                        rs.getString("nyeri"),
                        rs.getString("ket_nyeri"),
                        rs.getString("massa_positif"),
                        rs.getString("massa_negatif"),
                        rs.getString("uk"),
                        rs.getString("ket_uk"),
                        rs.getString("lokasi"),
                        rs.getString("ket_lokasi"),
                        rs.getString("segar"),
                        rs.getString("layu"),
                        rs.getString("lainya_tali_pusat"),
                        rs.getString("ket_lainya_tali_pusat"),
                        rs.getString("normal_punggung"),
                        rs.getString("spina"),
                        rs.getString("gibus"),
                        rs.getString("lainya_punggung"),
                        rs.getString("ket_lainya_punggung"),
                        rs.getString("sex"),
                        rs.getString("ket_sex"),
                        rs.getString("kelainan_urogenitalia"),
                        rs.getString("ket_kelainan_urogenitalia"),
                        rs.getString("bak"),
                        rs.getString("ket_bak"),
                        rs.getString("anus"),
                        rs.getString("bab"),
                        rs.getString("ket_bab"),
                        rs.getString("simetris_ekstremitas"),
                        rs.getString("asimetris_ekstremitas"),
                        rs.getString("reflek_moro_positif"),
                        rs.getString("reflek_moro_negatif"),
                        rs.getString("lainya_ekstremitas"),
                        rs.getString("ket_lainya_ekstremitas"),
                        rs.getString("edema"),
                        rs.getString("kelainan_ekstremitas"),
                        rs.getString("ket_kelainan_ekstremitas"),
                        rs.getString("pemeriksaan_penunjang"),
                        rs.getString("diagnosa_kerja"),
                        rs.getString("diagnosa_banding"),
                        rs.getString("pengobatan"),
                        rs.getString("diet"),
                        rs.getString("rencana"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("jam_asesmen"),
                        rs.getString("nip_dpjp"),
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
        TtglAsesmen.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        
        Tkeluhan.setText("");
        TriwPenyakitDahulu.setText("");
        variabelBersih();
        chkHipertensi.setSelected(false);
        chkDiabetes.setSelected(false);
        chkJantung.setSelected(false);
        chkStrok.setSelected(false);
        chkAsma.setSelected(false);
        chkKejang.setSelected(false);
        chkHati.setSelected(false);
        chkKanker.setSelected(false);
        chkTB.setSelected(false);
        chkPMS.setSelected(false);
        chkPerdarahan.setSelected(false);
        chkGinjal.setSelected(false);
        chkLainLain.setSelected(false);
        TketLainLain.setText("");
        TketLainLain.setEnabled(false);
        
        cmbKondisi.setSelectedIndex(0);
        TketAs.setText("");
        Tgerak.setText("");
        Ttangis.setText("");
        TwarnaKulit.setText("");
        Thr.setText("");
        Tsuhu.setText("");
        Trr.setText("");
        Tsaturasi.setText("");
        cmbCapilary.setSelectedIndex(0);
        Tbbl.setText("");
        Tlk.setText("");
        Tld.setText("");
        Tlp.setText("");
        Tlla.setText("");
        
        chkTurgor.setSelected(false);
        Tturgor.setText("");
        Tturgor.setEnabled(false);
        chkSianosisKulit.setSelected(false);
        chkPerdarahanKulit.setSelected(false);
        chkIkterusNegatif.setSelected(false);
        chkIkterusPositif.setSelected(false);
        chkKrammer.setSelected(false);
        Tkramer.setText("");
        Tkramer.setEnabled(false);
        chkHematoma.setSelected(false);
        chkSklerema.setSelected(false);
        chkKutisMarmorata.setSelected(false);
        chkLainKulit.setSelected(false);
        TKetLainKulit.setText("");
        TKetLainKulit.setEnabled(false);
        
        chkSimetrisKepala.setSelected(false);
        chkAsimetrisKepala.setSelected(false);
        chkCephal.setSelected(false);
        chkCaput.setSelected(false);
        chkAnensefali.setSelected(false);
        chkMicrosefal.setSelected(false);
        chkhydrosefalus.setSelected(false);
        chkLainKepala.setSelected(false);
        TKetLainKepala.setText("");
        TKetLainKepala.setEnabled(false);
        
        chkDatar.setSelected(false);
        chkCembung.setSelected(false);
        chkCekung.setSelected(false);
        chkLainUUB.setSelected(false);
        TKetLainUUB.setText("");
        TKetLainUUB.setEnabled(false);
        
        chkNormalMata.setSelected(false);
        chkAnemia.setSelected(false);
        chkIkterusMata.setSelected(false);
        chkSekretMata.setSelected(false);
        chkLainMata.setSelected(false);
        TKetLainMata.setText("");
        TKetLainMata.setEnabled(false);
        
        chkNormalTHT.setSelected(false);
        chkNCH.setSelected(false);
        chkSianosisTHT.setSelected(false);
        chkSekretTHT.setSelected(false);
        chkLainTHT.setSelected(false);
        TKetLainTHT.setText("");
        TKetLainTHT.setEnabled(false);
        
        chkNormalMulut.setSelected(false);
        chkLabioschisis.setSelected(false);
        chkLabiopalatos.setSelected(false);
        chkLabiog.setSelected(false);
        chkMukosa.setSelected(false);
        TMukosa.setText("");
        TMukosa.setEnabled(false);
        chkReflek.setSelected(false);
        TReflek.setText("");
        TReflek.setEnabled(false);
        chkLainMulut.setSelected(false);
        TKetLainMulut.setText("");
        TKetLainMulut.setEnabled(false);
        
        chkNormalLeher.setSelected(false);
        chkTortikolis.setSelected(false);
        chkBenjolanKanan.setSelected(false);
        chkBenjolanKiri.setSelected(false);
        chkLainLeher.setSelected(false);
        TKetLainLeher.setText("");
        TKetLainLeher.setEnabled(false);
        
        chkSimetrisDada.setSelected(false);
        chkTidakSimetris.setSelected(false);
        chkRetraksiPositif.setSelected(false);
        chkRetraksiNegatif.setSelected(false);
        TKetRetraksi.setText("");
        chkSesak.setSelected(false);
        chkMerintih.setSelected(false);
        chkSianosisDada.setSelected(false);
        chkLainDada.setSelected(false);
        TKetLainDada.setText("");
        TKetLainDada.setEnabled(false);
        
        chkBji.setSelected(false);
        chkMurni.setSelected(false);
        chkTidakMurni.setSelected(false);
        chkReguler.setSelected(false);
        chkTidakReguler.setSelected(false);
        chkBunyi.setSelected(false);
        TKetBunyi.setText("");
        TKetBunyi.setEnabled(false);
        
        chkVesikuler.setSelected(false);
        chkRonchi.setSelected(false);
        chkWhezing.setSelected(false);
        chkStridor.setSelected(false);
        chkLainParu.setSelected(false);
        TKetLainParu.setText("");
        TKetLainParu.setEnabled(false);
        
        chkSupel.setSelected(false);
        chkDistensi.setSelected(false);
        chkBising.setSelected(false);
        chkPembesaranHepar.setSelected(false);
        chkPembesaranLimpa.setSelected(false);
        chkNyeri.setSelected(false);
        TNyeri.setText("");
        TNyeri.setEnabled(false);
        chkMasaPositif.setSelected(false);
        chkMasaNegatif.setSelected(false);
        chkUK.setSelected(false);
        Tuk.setText("");
        Tuk.setEnabled(false);
        chkLokasi.setSelected(false);
        Tlokasi.setText("");
        Tlokasi.setEnabled(false);
        
        chkSegar.setSelected(false);
        chkLayu.setSelected(false);
        chkLainTali.setSelected(false);
        TKetLainTali.setText("");
        TKetLainTali.setEnabled(false);
        
        chkNormalPunggung.setSelected(false);
        chkSpina.setSelected(false);
        chkGibus.setSelected(false);
        chkLainPunggung.setSelected(false);
        TKetLainPunggung.setText("");
        TKetLainPunggung.setEnabled(false);
        
        chkSex.setSelected(false);
        Tsex.setText("");
        Tsex.setEnabled(false);
        chkKelainanUro.setSelected(false);
        TkelainanUro.setText("");
        TkelainanUro.setEnabled(false);
        chkBAK.setSelected(false);
        Tbak.setText("");
        Tbak.setEnabled(false);
        
        cmbAnus.setSelectedIndex(0);
        chkBAB.setSelected(false);
        Tbab.setText("");
        Tbab.setEnabled(false);
        
        chkSimetrisEks.setSelected(false);
        chkAsimetrisEks.setSelected(false);
        chkReflekMoroPositif.setSelected(false);
        chkReflekMoroNegatif.setSelected(false);
        chkLainEks.setSelected(false);
        TKetLainEks.setText("");
        TKetLainEks.setEnabled(false);
        chkEdema.setSelected(false);
        chkKelainanEks.setSelected(false);
        TkelainanEks.setText("");
        TkelainanEks.setEnabled(false);
        
        Tpemerikaaan.setText("");
        TdiagnosaKerja.setText("");
        TdiagnosaBanding.setText("");
        Tpengobatan.setText("");
        Tdiet.setText("");
        Trencana.setText("");
        
        Tnip.setText("-");
        TnmDokter.setText("-");
        user = "";
    }

    private void getData() {
        variabelBersih();
        if (tbAsesmen.getSelectedRow() != -1) {
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 2).toString());
            Valid.SetTgl(TtglAsesmen, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 165).toString());
            cmbJam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 166).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 166).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 166).toString().substring(6, 8));
            Tjk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 3).toString());
            TtglLahir.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 4).toString());
            TrgRawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 5).toString());
            Tkeluhan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 9).toString());
            TriwPenyakitDahulu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString());
            hipertensi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString();
            diabet = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString();
            jantung = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString();
            strok = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString();
            asma = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString();
            kejang = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString();
            hati = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString();
            kanker = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString();
            tb = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 19).toString();
            pms = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString();
            perdarahan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString();
            ginjal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString();
            lainRiwayat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString();
            TketLainLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString());
            cmbKondisi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString());
            TketAs.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString());
            Tgerak.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString());
            Ttangis.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString());
            TwarnaKulit.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString());
            Thr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString());
            Tsuhu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString());
            Trr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 32).toString());
            Tsaturasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 33).toString());
            cmbCapilary.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString());
            Tbbl.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString());
            Tpb.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString());
            Tlk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString());
            Tld.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString());
            Tlp.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString());
            Tlla.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString());
            turgor = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString();
            Tturgor.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString());
            sianosisKulit = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString();
            perdarahanKulit = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString();
            ikterusPos = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString();
            ikterusNeg = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString();
            kramer = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString();
            Tkramer.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString());
            hematoma = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString();
            sklere = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString();
            kutis = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString();
            lainKulit = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString();
            TKetLainKulit.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString());
            simetrisKepala = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString();
            asimetrisKepala = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString();
            cepal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString();
            caput = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString();
            anen = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString();
            micros = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString();
            hidro = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString();
            lainKepala = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString();
            TKetLainKepala.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 62).toString());
            datar = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString();
            cembung = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 64).toString();
            cekung = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString();
            lainUub = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 66).toString();
            TKetLainUUB.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString());
            normalMata = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString();
            anemia = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString();
            ikterus = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString();
            sekretMata = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString();
            LainMata = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString();
            TKetLainMata.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString());
            normalTht = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString();
            nch = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString();
            sianosisTht = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString();
            sekretTht = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString();
            lainTht = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString();
            TKetLainTHT.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 79).toString());
            normalMulut = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 80).toString();
            labioS = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 81).toString();
            labioP = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 82).toString();
            labioG = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 83).toString();
            mukosa = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 84).toString();
            TMukosa.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 85).toString());
            reflek = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 86).toString();
            TReflek.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 87).toString());
            lainMulut = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 88).toString();
            TKetLainMulut.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 89).toString());
            normalLeher = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 90).toString();
            torti = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 91).toString();
            benjolKanan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 92).toString();
            benjolKiri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 93).toString();
            lainLeher = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString();
            TKetLainLeher.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 95).toString());
            simetrisDada = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 96).toString();
            tidakSimetris = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 97).toString();
            retraksiPos = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 98).toString();
            retraksiNeg = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 99).toString();
            TKetRetraksi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 100).toString());
            sesak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString();
            merintih = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 102).toString();
            sianosisDada = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 103).toString();
            lainDada = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 104).toString();
            TKetLainDada.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 105).toString());
            bj = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 106).toString();
            murni = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 107).toString();
            tidakMurni = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 108).toString();
            reguler = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 109).toString();
            tidakReguler = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 110).toString();
            bunyi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 111).toString();
            TKetBunyi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 112).toString());
            vesikuler = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 113).toString();
            ronchi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 114).toString();
            wezing = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 115).toString();
            stridor = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 116).toString();
            lainParu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 117).toString();
            TKetLainParu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 118).toString());
            supel = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 119).toString();
            disten = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 120).toString();
            bising = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 121).toString();
            hepar = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 122).toString();
            limpa = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 123).toString();
            nyeri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 124).toString();
            TNyeri.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 125).toString());
            masaPos = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 126).toString();
            masaNeg = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 127).toString();
            uk = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 128).toString();
            Tuk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 129).toString());
            lokasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 130).toString();
            Tlokasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 131).toString());
            segar = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 132).toString();
            layu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 133).toString();
            lainTali = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 134).toString();
            TKetLainTali.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 135).toString());
            normalPunggung = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 136).toString();
            spina = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 137).toString();
            gibus = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 138).toString();
            lainPunggung = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 139).toString();
            TKetLainPunggung.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 140).toString());
            sex = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 141).toString();
            Tsex.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 142).toString());
            kelainanUro = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 143).toString();
            TkelainanUro.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 144).toString());
            bak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 145).toString();
            Tbak.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 146).toString());
            cmbAnus.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 147).toString());
            bab = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 148).toString();
            Tbab.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 149).toString());
            simetrisEks = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 150).toString();
            asimetrisEks = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 151).toString();
            reflekMoroPos = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 152).toString();
            reflekMoroNeg = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 153).toString();
            lainEks = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 154).toString();
            TKetLainEks.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 155).toString());
            edema = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 156).toString();
            kelainanEks = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 157).toString();
            TkelainanEks.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 158).toString());
            Tpemerikaaan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 159).toString());
            TdiagnosaKerja.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 160).toString());
            TdiagnosaBanding.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 161).toString());
            Tpengobatan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 162).toString());
            Tdiet.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 163).toString());
            Trencana.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 164).toString());
            Tnip.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 167).toString());            
            TnmDokter.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 8).toString());
            dataCek();
        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement("SELECT rp.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-Laki','Perempuan') jk, "
                    + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllahir, rp.tgl_registrasi, rp.jam_reg "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "WHERE rp.no_rawat = ?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    Tjk.setText(rs.getString("jk"));
                    TtglLahir.setText(rs.getString("tgllahir"));
//                    Valid.SetTgl(TtglAsesmen, rs.getString("tgl_registrasi"));
//                    cmbJam.setSelectedItem(rs.getString("jam_reg").toString().substring(0, 2));
//                    cmbMnt.setSelectedItem(rs.getString("jam_reg").toString().substring(3, 5));
//                    cmbDtk.setSelectedItem(rs.getString("jam_reg").toString().substring(6, 8));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
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
    
    public void setNoRm(String norwt, String rgrawat) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(new Date());
        TrgRawat.setText(rgrawat);
        isRawat();
        tampil();        
    }    
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getasesmen_medik_anak_ranap());
        BtnHapus.setEnabled(akses.getasesmen_medik_anak_ranap());
        BtnEdit.setEnabled(akses.getasesmen_medik_anak_ranap());
        BtnPrint.setEnabled(akses.getasesmen_medik_anak_ranap());
        MnRiwayatData.setEnabled(akses.getadmin());
        
        if (akses.getjml2() >= 1) {            
            Tnip.setText(akses.getkode());    
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmDokter, Tnip.getText());
            if (TnmDokter.getText().equals("")) {
                Tnip.setText("");
            }
        }        
    }

    public void setTampil(){
       TabRawat.setSelectedIndex(1);
       tampil();
    }
    
    private void ganti() {
        cekData();
        try {
            if (Sequel.mengedittf("asesmen_medik_perinatologi", "no_rawat=?",
                    "keluhan=?, riw_penyakit_dahulu=?, hipertensi=?, diabetes=?, jantung=?, stroke=?, asma=?, kejang=?, "
                    + "hati=?, kanker=?, tb=?, pms=?, perdarahan=?, ginjal=?, lain_lain=?, ket_lain_lain=?, kondisi_saat_lahir=?, "
                    + "ket_as=?, gerak=?, tangis=?, warna_kulit=?, hr=?, suhu=?, rr=?, saturasi=?, capilary_refill=?, bbl=?, pb=?, "
                    + "lk=?, ld=?, lp=?, lla=?, turgor=?, ket_turgor=?, sianosis_kulit=?, perdarahan_kulit=?, ikterus_positif=?, "
                    + "ikterus_negatif=?, krammer=?, ket_krammer=?, hematoma=?, sklerema=?, kutis=?, lainya_kulit=?, ket_lainya_kulit=?, "
                    + "simetris_kapala=?, asimetris_kepala=?, cephal_hematom=?, caput_succedaneum=?, anensefali=?, microsefal=?, "
                    + "hydrosefalus=?, lainya_kepala=?, ket_lainya_kepala=?, datar=?, cembung=?, cekung=?, lainya_uub=?, "
                    + "ket_lainya_uub=?, normal_mata=?, anemia=?, ikterus_mata=?, sekret_mata=?, lainya_mata=?, ket_lainya_mata=?, "
                    + "normal_tht=?, nch=?, sianosis_tht=?, sekret_tht=?, lainya_tht=?, ket_lainya_tht=?, normal_mulut=?, "
                    + "labioschisis=?, labiopalatoschisis=?, labiognatopalatoschisis=?, mucosa_warna=?, ket_warna=?, reflek_hisap=?, "
                    + "ket_reflek_hisap=?, lainya_mulut=?, ket_lainya_mulut=?, normal_leher=?, tortikolis=?, benjolan_kanan=?, "
                    + "benjolan_kiri=?, lainya_leher=?, ket_lainya_leher=?, simetris_dada=?, tidak_simetris=?, retraksi_positif=?, "
                    + "retraksi_negatif=?, ket_retraksi=?, sesak=?, merintih=?, sianosis_dada=?, lainya_dada=?, ket_lainya_dada=?, "
                    + "bji=?, murni=?, tidak_murni=?, reguler=?, tidak_reguler=?, bunyi_tambahan=?, ket_bunyi_tambahan=?, vesikuler=?, "
                    + "ronchi=?, whezing=?, stridor=?, lainya_paru=?, ket_lainya_paru=?, supel=?, distensi=?, bising_usus=?, "
                    + "pembesaran_hepar=?, pembesaran_limpa=?, nyeri=?, ket_nyeri=?, massa_positif=?, massa_negatif=?, uk=?, ket_uk=?, "
                    + "lokasi=?, ket_lokasi=?, segar=?, layu=?, lainya_tali_pusat=?, ket_lainya_tali_pusat=?, normal_punggung=?, "
                    + "spina=?, gibus=?, lainya_punggung=?, ket_lainya_punggung=?, sex=?, ket_sex=?, kelainan_urogenitalia=?, "
                    + "ket_kelainan_urogenitalia=?, bak=?, ket_bak=?, anus=?, bab=?, ket_bab=?, simetris_ekstremitas=?, "
                    + "asimetris_ekstremitas=?, reflek_moro_positif=?, reflek_moro_negatif=?, lainya_ekstremitas=?, "
                    + "ket_lainya_ekstremitas=?, edema=?, kelainan_ekstremitas=?, ket_kelainan_ekstremitas=?, pemeriksaan_penunjang=?, "
                    + "diagnosa_kerja=?, diagnosa_banding=?, pengobatan=?, diet=?, rencana=?, tgl_asesmen=?, jam_asesmen=?, nip_dpjp=?", 160, new String[]{
                        Tkeluhan.getText(), TriwPenyakitDahulu.getText(), hipertensi, diabet, jantung, strok, asma, kejang,
                        hati, kanker, tb, pms, perdarahan, ginjal, lainRiwayat, TketLainLain.getText(), cmbKondisi.getSelectedItem().toString(), TketAs.getText(),
                        Tgerak.getText(), Ttangis.getText(), TwarnaKulit.getText(), Thr.getText(), Tsuhu.getText(), Trr.getText(), Tsaturasi.getText(),
                        cmbCapilary.getSelectedItem().toString(), Tbbl.getText(), Tpb.getText(), Tlk.getText(), Tld.getText(), Tlp.getText(), Tlla.getText(),
                        turgor, Tturgor.getText(), sianosisKulit, perdarahanKulit, ikterusPos, ikterusNeg, kramer, Tkramer.getText(), hematoma, sklere, kutis,
                        lainKulit, TKetLainKulit.getText(), simetrisKepala, asimetrisKepala, cepal, caput, anen, micros, hidro, lainKepala, TKetLainKepala.getText(),
                        datar, cembung, cekung, lainUub, TKetLainUUB.getText(), normalMata, anemia, ikterus, sekretMata, LainMata, TKetLainMata.getText(),
                        normalTht, nch, sianosisTht, sekretTht, lainTht, TKetLainTHT.getText(), normalMulut, labioS, labioP, labioG, mukosa, TMukosa.getText(),
                        reflek, TReflek.getText(), lainMulut, TKetLainMulut.getText(), normalLeher, torti, benjolKanan, benjolKiri, lainLeher, TKetLainLeher.getText(),
                        simetrisDada, tidakSimetris, retraksiPos, retraksiNeg, TKetRetraksi.getText(), sesak, merintih, sianosisDada, lainDada, TKetLainDada.getText(),
                        bj, murni, tidakMurni, reguler, tidakReguler, bunyi, TKetBunyi.getText(), vesikuler, ronchi, wezing, stridor, lainParu, TKetLainParu.getText(),
                        supel, disten, bising, hepar, limpa, nyeri, TNyeri.getText(), masaPos, masaNeg, uk, Tuk.getText(), lokasi, Tlokasi.getText(), segar, layu,
                        lainTali, TKetLainTali.getText(), normalPunggung, spina, gibus, lainPunggung, TKetLainPunggung.getText(), sex, Tsex.getText(), kelainanUro,
                        TkelainanUro.getText(), bak, Tbak.getText(), cmbAnus.getSelectedItem().toString(), bab, Tbab.getText(), simetrisEks, asimetrisEks,
                        reflekMoroPos, reflekMoroNeg, lainEks, TKetLainEks.getText(), edema, kelainanEks, TkelainanEks.getText(), Valid.mysql_real_escape_stringERM(Tpemerikaaan.getText()),
                        TdiagnosaKerja.getText(), TdiagnosaBanding.getText(), Tpengobatan.getText(), Tdiet.getText(), Trencana.getText(),
                        Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        Tnip.getText(), tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                    }) == true) {

                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                TabRawat.setSelectedIndex(1);
            }
        } catch (Exception e) {
            System.out.println("Ganti Asesmen Medik Perinatologi : " + e);
        }
    }
    
    private void cekData() {
        //riwayat penyakit keluarga
        if (chkHipertensi.isSelected() == true) {
            hipertensi = "ya";
        } else {
            hipertensi = "tidak";
        }
        
        if (chkDiabetes.isSelected() == true) {
            diabet = "ya";
        } else {
            diabet = "tidak";
        }
        
        if (chkJantung.isSelected() == true) {
            jantung = "ya";
        } else {
            jantung = "tidak";
        }
        
        if (chkStrok.isSelected() == true) {
            strok = "ya";
        } else {
            strok = "tidak";
        }
        
        if (chkAsma.isSelected() == true) {
            asma = "ya";
        } else {
            asma = "tidak";
        }
        
        if (chkKejang.isSelected() == true) {
            kejang = "ya";
        } else {
            kejang = "tidak";
        }
        
        if (chkHati.isSelected() == true) {
            hati = "ya";
        } else {
            hati = "tidak";
        }
        
        if (chkKanker.isSelected() == true) {
            kanker = "ya";
        } else {
            kanker = "tidak";
        }
        
        if (chkTB.isSelected() == true) {
            tb = "ya";
        } else {
            tb = "tidak";
        }
        
        if (chkPMS.isSelected() == true) {
            pms = "ya";
        } else {
            pms = "tidak";
        }
        
        if (chkPerdarahan.isSelected() == true) {
            perdarahan = "ya";
        } else {
            perdarahan = "tidak";
        }
        
        if (chkGinjal.isSelected() == true) {
            ginjal = "ya";
        } else {
            ginjal = "tidak";
        }
        
        if (chkLainLain.isSelected() == true) {
            lainRiwayat = "ya";
        } else {
            lainRiwayat = "tidak";
        }
        
        //pemeriksaan umum (kulit)
        if (chkTurgor.isSelected() == true) {
            turgor = "ya";
        } else {
            turgor = "tidak";
        }
        
        if (chkSianosisKulit.isSelected() == true) {
            sianosisKulit = "ya";
        } else {
            sianosisKulit = "tidak";
        }
        
        if (chkPerdarahanKulit.isSelected() == true) {
            perdarahanKulit = "ya";
        } else {
            perdarahanKulit = "tidak";
        }
        
        if (chkIkterusPositif.isSelected() == true) {
            ikterusPos = "ya";
        } else {
            ikterusPos = "tidak";
        }
        
        if (chkIkterusNegatif.isSelected() == true) {
            ikterusNeg = "ya";
        } else {
            ikterusNeg = "tidak";
        }
        
        if (chkKrammer.isSelected() == true) {
            kramer = "ya";
        } else {
            kramer = "tidak";
        }
        
        if (chkHematoma.isSelected() == true) {
            hematoma = "ya";
        } else {
            hematoma = "tidak";
        }
        
        if (chkSklerema.isSelected() == true) {
            sklere = "ya";
        } else {
            sklere = "tidak";
        }
        
        if (chkKutisMarmorata.isSelected() == true) {
            kutis = "ya";
        } else {
            kutis = "tidak";
        }
        
        if (chkLainKulit.isSelected() == true) {
            lainKulit = "ya";
        } else {
            lainKulit = "tidak";
        }
        
        //pemeriksaan umum (kepala)
        if (chkSimetrisKepala.isSelected() == true) {
            simetrisKepala = "ya";
        } else {
            simetrisKepala = "tidak";
        }
        
        if (chkAsimetrisKepala.isSelected() == true) {
            asimetrisKepala = "ya";
        } else {
            asimetrisKepala = "tidak";
        }
        
        if (chkCephal.isSelected() == true) {
            cepal = "ya";
        } else {
            cepal = "tidak";
        }
        
        if (chkCaput.isSelected() == true) {
            caput = "ya";
        } else {
            caput = "tidak";
        }
        
        if (chkAnensefali.isSelected() == true) {
            anen = "ya";
        } else {
            anen = "tidak";
        }
        
        if (chkMicrosefal.isSelected() == true) {
            micros = "ya";
        } else {
            micros = "tidak";
        }
        
        if (chkhydrosefalus.isSelected() == true) {
            hidro = "ya";
        } else {
            hidro = "tidak";
        }
        
        if (chkLainKepala.isSelected() == true) {
            lainKepala = "ya";
        } else {
            lainKepala = "tidak";
        }
        
        //pemeriksaan umum (uub)
        if (chkDatar.isSelected() == true) {
            datar = "ya";
        } else {
            datar = "tidak";
        }
        
        if (chkCembung.isSelected() == true) {
            cembung = "ya";
        } else {
            cembung = "tidak";
        }
        
        if (chkCekung.isSelected() == true) {
            cekung = "ya";
        } else {
            cekung = "tidak";
        }
        
        if (chkLainUUB.isSelected() == true) {
            lainUub = "ya";
        } else {
            lainUub = "tidak";
        }
        
        //pemeriksaan umum (mata)
        if (chkNormalMata.isSelected() == true) {
            normalMata = "ya";
        } else {
            normalMata = "tidak";
        }
        
        if (chkAnemia.isSelected() == true) {
            anemia = "ya";
        } else {
            anemia = "tidak";
        }
        
        if (chkIkterusMata.isSelected() == true) {
            ikterus = "ya";
        } else {
            ikterus = "tidak";
        }
        
        if (chkSekretMata.isSelected() == true) {
            sekretMata = "ya";
        } else {
            sekretMata = "tidak";
        }
        
        if (chkLainMata.isSelected() == true) {
            LainMata = "ya";
        } else {
            LainMata = "tidak";
        }
        
        //pemeriksaan umum (tht)
        if (chkNormalTHT.isSelected() == true) {
            normalTht = "ya";
        } else {
            normalTht = "tidak";
        }
        
        if (chkNCH.isSelected() == true) {
            nch = "ya";
        } else {
            nch = "tidak";
        }
        
        if (chkSianosisTHT.isSelected() == true) {
            sianosisTht = "ya";
        } else {
            sianosisTht = "tidak";
        }
        
        if (chkSekretTHT.isSelected() == true) {
            sekretTht = "ya";
        } else {
            sekretTht = "tidak";
        }
        
        if (chkLainTHT.isSelected() == true) {
            lainTht = "ya";
        } else {
            lainTht = "tidak";
        }
        
        //pemeriksaan umum (mulut)
        if (chkNormalMulut.isSelected() == true) {
            normalMulut = "ya";
        } else {
            normalMulut = "tidak";
        }
        
        if (chkLabioschisis.isSelected() == true) {
            labioS = "ya";
        } else {
            labioS = "tidak";
        }
        
        if (chkLabiopalatos.isSelected() == true) {
            labioP = "ya";
        } else {
            labioP = "tidak";
        }
        
        if (chkLabiog.isSelected() == true) {
            labioG = "ya";
        } else {
            labioG = "tidak";
        }
        
        if (chkMukosa.isSelected() == true) {
            mukosa = "ya";
        } else {
            mukosa = "tidak";
        }
        
        if (chkReflek.isSelected() == true) {
            reflek = "ya";
        } else {
            reflek = "tidak";
        }
        
        if (chkLainMulut.isSelected() == true) {
            lainMulut = "ya";
        } else {
            lainMulut = "tidak";
        }
        
        //pemeriksaan umum (leher)
        if (chkNormalLeher.isSelected() == true) {
            normalLeher = "ya";
        } else {
            normalLeher = "tidak";
        }
        
        if (chkTortikolis.isSelected() == true) {
            torti = "ya";
        } else {
            torti = "tidak";
        }
        
        if (chkBenjolanKanan.isSelected() == true) {
            benjolKanan = "ya";
        } else {
            benjolKanan = "tidak";
        }
        
        if (chkBenjolanKiri.isSelected() == true) {
            benjolKiri = "ya";
        } else {
            benjolKiri = "tidak";
        }
        
        if (chkLainLeher.isSelected() == true) {
            lainLeher = "ya";
        } else {
            lainLeher = "tidak";
        }
        
        //pemeriksaan umum (dada)
        if (chkSimetrisDada.isSelected() == true) {
            simetrisDada = "ya";
        } else {
            simetrisDada = "tidak";
        }
        
        if (chkTidakSimetris.isSelected() == true) {
            tidakSimetris = "ya";
        } else {
            tidakSimetris = "tidak";
        }
        
        if (chkRetraksiNegatif.isSelected() == true) {
            retraksiNeg = "ya";
        } else {
            retraksiNeg = "tidak";
        }
        
        if (chkRetraksiPositif.isSelected() == true) {
            retraksiPos = "ya";
        } else {
            retraksiPos = "tidak";
        }
        
        if (chkSesak.isSelected() == true) {
            sesak = "ya";
        } else {
            sesak = "tidak";
        }
        
        if (chkMerintih.isSelected() == true) {
            merintih = "ya";
        } else {
            merintih = "tidak";
        }
        
        if (chkSianosisDada.isSelected() == true) {
            sianosisDada = "ya";
        } else {
            sianosisDada = "tidak";
        }
        
        if (chkLainDada.isSelected() == true) {
            lainDada = "ya";
        } else {
            lainDada = "tidak";
        }
        
        //pemeriksaan umum (jantung)
        if (chkBji.isSelected() == true) {
            bj = "ya";
        } else {
            bj = "tidak";
        }
        
        if (chkMurni.isSelected() == true) {
            murni = "ya";
        } else {
            murni = "tidak";
        }
        
        if (chkTidakMurni.isSelected() == true) {
            tidakMurni = "ya";
        } else {
            tidakMurni = "tidak";
        }
        
        if (chkReguler.isSelected() == true) {
            reguler = "ya";
        } else {
            reguler = "tidak";
        }
        
        if (chkTidakReguler.isSelected() == true) {
            tidakReguler = "ya";
        } else {
            tidakReguler = "tidak";
        }
        
        if (chkBunyi.isSelected() == true) {
            bunyi = "ya";
        } else {
            bunyi = "tidak";
        }
        
        //pemeriksaan umum (paru)
        if (chkVesikuler.isSelected() == true) {
            vesikuler = "ya";
        } else {
            vesikuler = "tidak";
        }
        
        if (chkRonchi.isSelected() == true) {
            ronchi = "ya";
        } else {
            ronchi = "tidak";
        }
        
        if (chkWhezing.isSelected() == true) {
            wezing = "ya";
        } else {
            wezing = "tidak";
        }
        
        if (chkStridor.isSelected() == true) {
            stridor = "ya";
        } else {
            stridor = "tidak";
        }
        
        if (chkLainParu.isSelected() == true) {
            lainParu = "ya";
        } else {
            lainParu = "tidak";
        }
        
        //pemeriksaan umum (perut)
        if (chkSupel.isSelected() == true) {
            supel = "ya";
        } else {
            supel = "tidak";
        }
        
        if (chkDistensi.isSelected() == true) {
            disten = "ya";
        } else {
            disten = "tidak";
        }
        
        if (chkBising.isSelected() == true) {
            bising = "ya";
        } else {
            bising = "tidak";
        }
        
        if (chkPembesaranHepar.isSelected() == true) {
            hepar = "ya";
        } else {
            hepar = "tidak";
        }
        
        if (chkPembesaranLimpa.isSelected() == true) {
            limpa = "ya";
        } else {
            limpa = "tidak";
        }
        
        if (chkNyeri.isSelected() == true) {
            nyeri = "ya";
        } else {
            nyeri = "tidak";
        }
        
        if (chkMasaNegatif.isSelected() == true) {
            masaNeg = "ya";
        } else {
            masaNeg = "tidak";
        }
        
        if (chkMasaPositif.isSelected() == true) {
            masaPos = "ya";
        } else {
            masaPos = "tidak";
        }
        
        if (chkUK.isSelected() == true) {
            uk = "ya";
        } else {
            uk = "tidak";
        }
        
        if (chkLokasi.isSelected() == true) {
            lokasi = "ya";
        } else {
            lokasi = "tidak";
        }
        
        //pemeriksaan umum (tali pusat)
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
        
        if (chkLainTali.isSelected() == true) {
            lainTali = "ya";
        } else {
            lainTali = "tidak";
        }
        
        //pemeriksaan umum (punggung)
        if (chkNormalPunggung.isSelected() == true) {
            normalPunggung = "ya";
        } else {
            normalPunggung = "tidak";
        }
        
        if (chkSpina.isSelected() == true) {
            spina = "ya";
        } else {
            spina = "tidak";
        }
        
        if (chkGibus.isSelected() == true) {
            gibus = "ya";
        } else {
            gibus = "tidak";
        }
        
        if (chkLainPunggung.isSelected() == true) {
            lainPunggung = "ya";
        } else {
            lainPunggung = "tidak";
        }
        
        //pemeriksaan umum (urogenitalia)
        if (chkSex.isSelected() == true) {
            sex = "ya";
        } else {
            sex = "tidak";
        }
        
        if (chkKelainanUro.isSelected() == true) {
            kelainanUro = "ya";
        } else {
            kelainanUro = "tidak";
        }
        
        if (chkBAK.isSelected() == true) {
            bak = "ya";
        } else {
            bak = "tidak";
        }
        
        //pemeriksaan umum (anus)
        if (chkBAB.isSelected() == true) {
            bab = "ya";
        } else {
            bab = "tidak";
        }
        
        //pemeriksaan umum (ekstremitas)
        if (chkSimetrisEks.isSelected() == true) {
            simetrisEks = "ya";
        } else {
            simetrisEks = "tidak";
        }
        
        if (chkAsimetrisEks.isSelected() == true) {
            asimetrisEks = "ya";
        } else {
            asimetrisEks = "tidak";
        }
        
        if (chkReflekMoroNegatif.isSelected() == true) {
            reflekMoroNeg = "ya";
        } else {
            reflekMoroNeg = "tidak";
        }
        
        if (chkReflekMoroPositif.isSelected() == true) {
            reflekMoroPos = "ya";
        } else {
            reflekMoroPos = "tidak";
        }
        
        if (chkLainEks.isSelected() == true) {
            lainEks = "ya";
        } else {
            lainEks = "tidak";
        }
        
        if (chkEdema.isSelected() == true) {
            edema = "ya";
        } else {
            edema = "tidak";
        }
        
        if (chkKelainanEks.isSelected() == true) {
            kelainanEks = "ya";
        } else {
            kelainanEks = "tidak";
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            user = "";
            if (akses.getadmin() == true) {
                user = "-";
            } else {
                user = akses.getkode();
            }
            
            hapusDisimpan();
            if (Sequel.queryu2tf("delete from asesmen_medik_perinatologi where no_rawat=?", 1, new String[]{
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
    }
    
    private void dataCek() {
        //riwayat penyakit keluarga        
        if (hipertensi.equals("ya")) {
            chkHipertensi.setSelected(true);
        } else {
            chkHipertensi.setSelected(false);
        }
        
        if (diabet.equals("ya")) {
            chkDiabetes.setSelected(true);
        } else {
            chkDiabetes.setSelected(false);
        }
        
        if (jantung.equals("ya")) {
            chkJantung.setSelected(true);
        } else {
            chkJantung.setSelected(false);
        }
        
        if (strok.equals("ya")) {
            chkStrok.setSelected(true);
        } else {
            chkStrok.setSelected(false);
        }
        
        if (asma.equals("ya")) {
            chkAsma.setSelected(true);
        } else {
            chkAsma.setSelected(false);
        }
        
        if (kejang.equals("ya")) {
            chkKejang.setSelected(true);
        } else {
            chkKejang.setSelected(false);
        }
        
        if (hati.equals("ya")) {
            chkHati.setSelected(true);
        } else {
            chkHati.setSelected(false);
        }
        
        if (kanker.equals("ya")) {
            chkKanker.setSelected(true);
        } else {
            chkKanker.setSelected(false);
        }
        
        if (tb.equals("ya")) {
            chkTB.setSelected(true);
        } else {
            chkTB.setSelected(false);
        }
        
        if (pms.equals("ya")) {
            chkPMS.setSelected(true);
        } else {
            chkPMS.setSelected(false);
        }
        
        if (perdarahan.equals("ya")) {
            chkPerdarahan.setSelected(true);
        } else {
            chkPerdarahan.setSelected(false);
        }
        
        if (ginjal.equals("ya")) {
            chkGinjal.setSelected(true);
        } else {
            chkGinjal.setSelected(false);
        }
        
        if (lainRiwayat.equals("ya")) {
            chkLainLain.setSelected(true);
            TketLainLain.setEnabled(true);
        } else {
            chkLainLain.setSelected(false);
            TketLainLain.setEnabled(false);
        }
        
        //pemeriksaan umum (kulit)
        if (turgor.equals("ya")) {
            chkTurgor.setSelected(true);
            Tturgor.setEnabled(true);
        } else {
            chkTurgor.setSelected(false);
            Tturgor.setEnabled(false);
        }
        
        if (sianosisKulit.equals("ya")) {
            chkSianosisKulit.setSelected(true);
        } else {
            chkSianosisKulit.setSelected(false);
        }
        
        if (perdarahanKulit.equals("ya")) {
            chkPerdarahanKulit.setSelected(true);
        } else {
            chkPerdarahanKulit.setSelected(false);
        }
        
        if (ikterusPos.equals("ya")) {
            chkIkterusPositif.setSelected(true);
        } else {
            chkIkterusPositif.setSelected(false);
        }
        
        if (ikterusNeg.equals("ya")) {
            chkIkterusNegatif.setSelected(true);
        } else {
            chkIkterusNegatif.setSelected(false);
        }
        
        if (kramer.equals("ya")) {
            chkKrammer.setSelected(true);
            Tkramer.setEnabled(true);
        } else {
            chkKrammer.setSelected(false);
            Tkramer.setEnabled(false);
        }
        
        if (hematoma.equals("ya")) {
            chkHematoma.setSelected(true);
        } else {
            chkHematoma.setSelected(false);
        }
        
        if (sklere.equals("ya")) {
            chkSklerema.setSelected(true);
        } else {
            chkSklerema.setSelected(false);
        }
        
        if (kutis.equals("ya")) {
            chkKutisMarmorata.setSelected(true);
        } else {
            chkKutisMarmorata.setSelected(false);
        }
        
        if (lainKulit.equals("ya")) {
            chkLainKulit.setSelected(true);
            TKetLainKulit.setEnabled(true);
        } else {
            chkLainKulit.setSelected(false);
            TKetLainKulit.setEnabled(false);
        }        
        
        //pemeriksaan umum (kepala)
        if (simetrisKepala.equals("ya")) {
            chkSimetrisKepala.setSelected(true);
        } else {
            chkSimetrisKepala.setSelected(false);
        }
        
        if (asimetrisKepala.equals("ya")) {
            chkAsimetrisKepala.setSelected(true);
        } else {
            chkAsimetrisKepala.setSelected(false);
        }
        
        if (cepal.equals("ya")) {
            chkCephal.setSelected(true);
        } else {
            chkCephal.setSelected(false);
        }
        
        if (caput.equals("ya")) {
            chkCaput.setSelected(true);
        } else {
            chkCaput.setSelected(false);
        }
        
        if (anen.equals("ya")) {
            chkAnensefali.setSelected(true);
        } else {
            chkAnensefali.setSelected(false);
        }
        
        if (micros.equals("ya")) {
            chkMicrosefal.setSelected(true);
        } else {
            chkMicrosefal.setSelected(false);
        }
        
        if (hidro.equals("ya")) {
            chkhydrosefalus.setSelected(true);
        } else {
            chkhydrosefalus.setSelected(false);
        }
        
        if (lainKepala.equals("ya")) {
            chkLainKepala.setSelected(true);
            TKetLainKepala.setEnabled(true);
        } else {
            chkLainKepala.setSelected(false);
            TKetLainKepala.setEnabled(false);
        }
        
        //pemeriksaan umum (uub)
        if (datar.equals("ya")) {
            chkDatar.setSelected(true);
        } else {
            chkDatar.setSelected(false);
        }
        
        if (cembung.equals("ya")) {
            chkCembung.setSelected(true);
        } else {
            chkCembung.setSelected(false);
        }
        
        if (cekung.equals("ya")) {
            chkCekung.setSelected(true);
        } else {
            chkCekung.setSelected(false);
        }
        
        if (lainUub.equals("ya")) {
            chkLainUUB.setSelected(true);
            TKetLainUUB.setEnabled(true);
        } else {
            chkLainUUB.setSelected(false);
            TKetLainUUB.setEnabled(false);
        }
        
        //pemeriksaan umum (mata)
        if (normalMata.equals("ya")) {
            chkNormalMata.setSelected(true);
        } else {
            chkNormalMata.setSelected(false);
        }
        
        if (anemia.equals("ya")) {
            chkAnemia.setSelected(true);
        } else {
            chkAnemia.setSelected(false);
        }
        
        if (ikterus.equals("ya")) {
            chkIkterusMata.setSelected(true);
        } else {
            chkIkterusMata.setSelected(false);
        }
        
        if (sekretMata.equals("ya")) {
            chkSekretMata.setSelected(true);
        } else {
            chkSekretMata.setSelected(false);
        }
        
        if (LainMata.equals("ya")) {
            chkLainMata.setSelected(true);
            TKetLainMata.setEnabled(true);
        } else {
            chkLainMata.setSelected(false);
            TKetLainMata.setEnabled(false);
        }
        
        //pemeriksaan umum (tht)
        if (normalTht.equals("ya")) {
            chkNormalTHT.setSelected(true);
        } else {
            chkNormalTHT.setSelected(false);
        }
        
        if (nch.equals("ya")) {
            chkNCH.setSelected(true);
        } else {
            chkNCH.setSelected(false);
        }
        
        if (sianosisTht.equals("ya")) {
            chkSianosisTHT.setSelected(true);
        } else {
            chkSianosisTHT.setSelected(false);
        }
        
        if (sekretTht.equals("ya")) {
            chkSekretTHT.setSelected(true);
        } else {
            chkSekretTHT.setSelected(false);
        }
        
        if (lainTht.equals("ya")) {
            chkLainTHT.setSelected(true);
            TKetLainTHT.setEnabled(true);
        } else {
            chkLainTHT.setSelected(false);
            TKetLainTHT.setEnabled(false);
        }
        
        //pemeriksaan umum (mulut)
        if (normalMulut.equals("ya")) {
            chkNormalMulut.setSelected(true);
        } else {
            chkNormalMulut.setSelected(false);
        }
        
        if (labioS.equals("ya")) {
            chkLabioschisis.setSelected(true);
        } else {
            chkLabioschisis.setSelected(false);
        }
        
        if (labioP.equals("ya")) {
            chkLabiopalatos.setSelected(true);
        } else {
            chkLabiopalatos.setSelected(false);
        }
        
        if (labioG.equals("ya")) {
            chkLabiog.setSelected(true);
        } else {
            chkLabiog.setSelected(false);
        }
        
        if (mukosa.equals("ya")) {
            chkMukosa.setSelected(true);
            TMukosa.setEnabled(true);
        } else {
            chkMukosa.setSelected(false);
            TMukosa.setEnabled(false);
        }
        
        if (reflek.equals("ya")) {
            chkReflek.setSelected(true);
            TReflek.setEnabled(true);
        } else {
            chkReflek.setSelected(false);
            TReflek.setEnabled(false);
        }
        
        if (lainMulut.equals("ya")) {
            chkLainMulut.setSelected(true);
            TKetLainMulut.setEnabled(true);
        } else {
            chkLainMulut.setSelected(false);
            TKetLainMulut.setEnabled(false);
        }
        
        //pemeriksaan umum (leher)
        if (normalLeher.equals("ya")) {
            chkNormalLeher.setSelected(true);
        } else {
            chkNormalLeher.setSelected(false);
        }
        
        if (torti.equals("ya")) {
            chkTortikolis.setSelected(true);
        } else {
            chkTortikolis.setSelected(false);
        }
        
        if (benjolKanan.equals("ya")) {
            chkBenjolanKanan.setSelected(true);
        } else {
            chkBenjolanKanan.setSelected(false);
        }
        
        if (benjolKiri.equals("ya")) {
            chkBenjolanKiri.setSelected(true);
        } else {
            chkBenjolanKiri.setSelected(false);
        }
        
        if (lainLeher.equals("ya")) {
            chkLainLeher.setSelected(true);
            TKetLainLeher.setEnabled(true);
        } else {
            chkLainLeher.setSelected(false);
            TKetLainLeher.setEnabled(false);
        }
        
        //pemeriksaan umum (dada)
        if (simetrisDada.equals("ya")) {
            chkSimetrisDada.setSelected(true);
        } else {
            chkSimetrisDada.setSelected(false);
        }
        
        if (tidakSimetris.equals("ya")) {
            chkTidakSimetris.setSelected(true);
        } else {
            chkTidakSimetris.setSelected(false);
        }
        
        if (retraksiPos.equals("ya")) {
            chkRetraksiPositif.setSelected(true);
        } else {
            chkRetraksiPositif.setSelected(false);
        }
        
        if (retraksiNeg.equals("ya")) {
            chkRetraksiNegatif.setSelected(true);
        } else {
            chkRetraksiNegatif.setSelected(false);
        }
        
        if (sesak.equals("ya")) {
            chkSesak.setSelected(true);
        } else {
            chkSesak.setSelected(false);
        }
        
        if (merintih.equals("ya")) {
            chkMerintih.setSelected(true);
        } else {
            chkMerintih.setSelected(false);
        }
        
        if (sianosisDada.equals("ya")) {
            chkSianosisDada.setSelected(true);
        } else {
            chkSianosisDada.setSelected(false);
        }
        
        if (lainDada.equals("ya")) {
            chkLainDada.setSelected(true);
            TKetLainDada.setEnabled(true);
        } else {
            chkLainDada.setSelected(false);
            TKetLainDada.setEnabled(false);
        }
        
        //pemeriksaan umum (jantung)
        if (bj.equals("ya")) {
            chkBji.setSelected(true);
        } else {
            chkBji.setSelected(false);
        }
        
        if (murni.equals("ya")) {
            chkMurni.setSelected(true);
        } else {
            chkMurni.setSelected(false);
        }
        
        if (tidakMurni.equals("ya")) {
            chkTidakMurni.setSelected(true);
        } else {
            chkTidakMurni.setSelected(false);
        }
        
        if (reguler.equals("ya")) {
            chkReguler.setSelected(true);
        } else {
            chkReguler.setSelected(false);
        }
        
        if (tidakReguler.equals("ya")) {
            chkTidakReguler.setSelected(true);
        } else {
            chkTidakReguler.setSelected(false);
        }
        
        if (bunyi.equals("ya")) {
            chkBunyi.setSelected(true);
            TKetBunyi.setEnabled(true);
        } else {
            chkBunyi.setSelected(false);
            TKetBunyi.setEnabled(false);
        }
        
        //pemeriksaan umum (paru)
        if (vesikuler.equals("ya")) {
            chkVesikuler.setSelected(true);
        } else {
            chkVesikuler.setSelected(false);
        }
        
        if (ronchi.equals("ya")) {
            chkRonchi.setSelected(true);
        } else {
            chkRonchi.setSelected(false);
        }
        
        if (wezing.equals("ya")) {
            chkWhezing.setSelected(true);
        } else {
            chkWhezing.setSelected(false);
        }
        
        if (stridor.equals("ya")) {
            chkStridor.setSelected(true);
        } else {
            chkStridor.setSelected(false);
        }
        
        if (lainParu.equals("ya")) {
            chkLainParu.setSelected(true);
            TKetLainParu.setEnabled(true);
        } else {
            chkLainParu.setSelected(false);
            TKetLainParu.setEnabled(false);
        }
        
        //pemeriksaan umum (perut)
        if (supel.equals("ya")) {
            chkSupel.setSelected(true);
        } else {
            chkSupel.setSelected(false);
        }
        
        if (disten.equals("ya")) {
            chkDistensi.setSelected(true);
        } else {
            chkDistensi.setSelected(false);
        }
        
        if (bising.equals("ya")) {
            chkBising.setSelected(true);
        } else {
            chkBising.setSelected(false);
        }
        
        if (hepar.equals("ya")) {
            chkPembesaranHepar.setSelected(true);
        } else {
            chkPembesaranHepar.setSelected(false);
        }
        
        if (limpa.equals("ya")) {
            chkPembesaranLimpa.setSelected(true);
        } else {
            chkPembesaranLimpa.setSelected(false);
        }
        
        if (nyeri.equals("ya")) {
            chkNyeri.setSelected(true);
            TNyeri.setEnabled(true);
        } else {
            chkNyeri.setSelected(false);
            TNyeri.setEnabled(false);
        }
        
        if (masaPos.equals("ya")) {
            chkMasaPositif.setSelected(true);
        } else {
            chkMasaPositif.setSelected(false);
        }
        
        if (masaNeg.equals("ya")) {
            chkMasaNegatif.setSelected(true);
        } else {
            chkMasaNegatif.setSelected(false);
        }
        
        if (uk.equals("ya")) {
            chkUK.setSelected(true);
            Tuk.setEnabled(true);
        } else {
            chkUK.setSelected(false);
            Tuk.setEnabled(false);
        }
        
        if (lokasi.equals("ya")) {
            chkLokasi.setSelected(true);
            Tlokasi.setEnabled(true);
        } else {
            chkLokasi.setSelected(false);
            Tlokasi.setEnabled(false);
        }
        
        //pemeriksaan umum (tali pusat)
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
        
        if (lainTali.equals("ya")) {
            chkLainTali.setSelected(true);
            TKetLainTali.setEnabled(true);
        } else {
            chkLainTali.setSelected(false);
            TKetLainTali.setEnabled(false);
        }
        
        //pemeriksaan umum (punggung)
        if (normalPunggung.equals("ya")) {
            chkNormalPunggung.setSelected(true);
        } else {
            chkNormalPunggung.setSelected(false);
        }
        
        if (spina.equals("ya")) {
            chkSpina.setSelected(true);
        } else {
            chkSpina.setSelected(false);
        }
        
        if (gibus.equals("ya")) {
            chkGibus.setSelected(true);
        } else {
            chkGibus.setSelected(false);
        }
        
        if (lainPunggung.equals("ya")) {
            chkLainPunggung.setSelected(true);
            TKetLainPunggung.setEnabled(true);
        } else {
            chkLainPunggung.setSelected(false);
            TKetLainPunggung.setEnabled(false);
        }
        
        //pemeriksaan umum (urogenitalia)
        if (sex.equals("ya")) {
            chkSex.setSelected(true);
            Tsex.setEnabled(true);
        } else {
            chkSex.setSelected(false);
            Tsex.setEnabled(false);
        }
        
        if (kelainanUro.equals("ya")) {
            chkKelainanUro.setSelected(true);
            TkelainanUro.setEnabled(true);
        } else {
            chkKelainanUro.setSelected(false);
            TkelainanUro.setEnabled(false);
        }
        
        if (bak.equals("ya")) {
            chkBAK.setSelected(true);
            Tbak.setEnabled(true);
        } else {
            chkBAK.setSelected(false);
            Tbak.setEnabled(false);
        }
        
        //pemeriksaan umum (anus)
        if (bab.equals("ya")) {
            chkBAB.setSelected(true);
            Tbab.setEnabled(true);
        } else {
            chkBAB.setSelected(false);
            Tbab.setEnabled(false);
        }
        
        //pemeriksaan umum (ekstremitas)
        if (simetrisEks.equals("ya")) {
            chkSimetrisEks.setSelected(true);
        } else {
            chkSimetrisEks.setSelected(false);
        }
        
        if (asimetrisEks.equals("ya")) {
            chkAsimetrisEks.setSelected(true);
        } else {
            chkAsimetrisEks.setSelected(false);
        }
        
        if (reflekMoroPos.equals("ya")) {
            chkReflekMoroPositif.setSelected(true);
        } else {
            chkReflekMoroPositif.setSelected(false);
        }
        
        if (reflekMoroNeg.equals("ya")) {
            chkReflekMoroNegatif.setSelected(true);
        } else {
            chkReflekMoroNegatif.setSelected(false);
        }
        
        if (lainEks.equals("ya")) {
            chkLainEks.setSelected(true);
            TKetLainEks.setEnabled(true);
        } else {
            chkLainEks.setSelected(false);
            TKetLainEks.setEnabled(false);
        }
        
        if (edema.equals("ya")) {
            chkEdema.setSelected(true);
        } else {
            chkEdema.setSelected(false);
        }
        
        if (kelainanEks.equals("ya")) {
            chkKelainanEks.setSelected(true);
            TkelainanEks.setEnabled(true);
        } else {
            chkKelainanEks.setSelected(false);
            TkelainanEks.setEnabled(false);
        }
    }
    
    public void isMenu() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(900, HEIGHT));
            FormMenu.setVisible(true);
            ChkAccor.setVisible(true);
            Thasil.setText("");
            Tinstruksi.setText("");
            tampilCppt();
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(22, HEIGHT));
            FormMenu.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }
    
    private void tampilCppt() {
        Valid.tabelKosong(tabModeCppt);
        try {
            pscppt = koneksi.prepareStatement("SELECT c.verifikasi, DATE_FORMAT(c.tgl_cppt,'%d-%m-%Y') tgl, if(c.cek_jam='ya',TIME_FORMAT(c.jam_cppt,'%H:%i'),'-') jam, "
                    + "c.jenis_bagian, pg1.nama nmdpjp, c.jenis_ppa, pg2.nama nmppa, c.cppt_shift, c.hasil_pemeriksaan, "
                    + "c.instruksi_nakes, c.waktu_simpan, c.no_rawat, c.tgl_cppt, c.jam_cppt from cppt c "
                    + "inner join pegawai pg1 on pg1.nik=c.nip_konsulen "
                    + "inner join pegawai pg2 on pg2.nik=c.nip_ppa where "
                    + "c.flag_hapus='tidak' and c.status='ranap' and c.no_rawat='" + TNoRw.getText() + "' order by c.tgl_cppt, c.jam_cppt");
            try {
                rscppt = pscppt.executeQuery();                
                while (rscppt.next()) {
                    tabModeCppt.addRow(new String[]{
                        rscppt.getString("tgl"),
                        rscppt.getString("jam"),
                        rscppt.getString("jenis_bagian"),
                        rscppt.getString("nmdpjp"),
                        rscppt.getString("jenis_ppa"),
                        rscppt.getString("nmppa"),
                        rscppt.getString("cppt_shift"),
                        rscppt.getString("hasil_pemeriksaan"),
                        rscppt.getString("instruksi_nakes"),
                        rscppt.getString("no_rawat"),
                        rscppt.getString("tgl_cppt"),
                        rscppt.getString("jam_cppt")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscppt != null) {
                    rscppt.close();
                }
                if (pscppt != null) {
                    pscppt.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataCppt() {
        dataKonfirmasi = "";
        
        if (tbCPPT.getSelectedRow() != -1) {
            Thasil.setText("Tgl. CPPT : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 0).toString() + ", Jam : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 1).toString() + " WITA\n\n"
                    + "" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString());
            
            //konfirmasi terapi
            if (Sequel.cariInteger("select count(-1) from cppt_konfirmasi_terapi where "
                    + "no_rawat='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString() + "' "
                    + "and tgl_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString() + "' "
                    + "and cppt_shift='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString() + "' "
                    + "and jam_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString() + "'") > 0) {

                tampilKonfirmasi(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString());
                
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                }
            } else {
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString());
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")");
                }
            }
        }
    }
    
    private void tampilKonfirmasi(String norwt, String tglcppt, String sift, String jamcppt) {
        try {
            ps1 = koneksi.prepareStatement("select pg1.nama ptgs, date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i') jamlapor, "
                    + "pg2.nama dpjp, date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i') jamverif from cppt_konfirmasi_terapi ck "
                    + "inner join pegawai pg1 on pg1.nik=ck.nip_petugas_konfir inner join pegawai pg2 on pg2.nik=ck.nip_dpjp_konfir where "
                    + "ck.no_rawat = '" + norwt + "' and ck.tgl_cppt='" + tglcppt + "' and ck.cppt_shift='" + sift + "' "
                    + "and ck.jam_cppt='" + jamcppt + "' order by ck.waktu_simpan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    if (dataKonfirmasi.equals("")) {
                        dataKonfirmasi = "Tgl. Lapor : " + rs1.getString("tgllapor") + ", Jam : " + rs1.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs1.getString("tglverif") + ", Jam : " + rs1.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs1.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs1.getString("dpjp");
                    } else {
                        dataKonfirmasi = dataKonfirmasi + "\n\nTgl. Lapor : " + rs1.getString("tgllapor") + ", Jam : " + rs1.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs1.getString("tglverif") + ", Jam : " + rs1.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs1.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs1.getString("dpjp");
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
    
    private void hapusDisimpan() {
        cekData();
        try {
            if (Sequel.menyimpantf("asesmen_medik_perinatologi_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 165, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), Tkeluhan.getText(), TriwPenyakitDahulu.getText(), hipertensi, diabet, jantung, strok, asma, kejang,
                        hati, kanker, tb, pms, perdarahan, ginjal, lainRiwayat, TketLainLain.getText(), cmbKondisi.getSelectedItem().toString(), TketAs.getText(),
                        Tgerak.getText(), Ttangis.getText(), TwarnaKulit.getText(), Thr.getText(), Tsuhu.getText(), Trr.getText(), Tsaturasi.getText(),
                        cmbCapilary.getSelectedItem().toString(), Tbbl.getText(), Tpb.getText(), Tlk.getText(), Tld.getText(), Tlp.getText(), Tlla.getText(),
                        turgor, Tturgor.getText(), sianosisKulit, perdarahanKulit, ikterusPos, ikterusNeg, kramer, Tkramer.getText(), hematoma, sklere, kutis,
                        lainKulit, TKetLainKulit.getText(), simetrisKepala, asimetrisKepala, cepal, caput, anen, micros, hidro, lainKepala, TKetLainKepala.getText(),
                        datar, cembung, cekung, lainUub, TKetLainUUB.getText(), normalMata, anemia, ikterus, sekretMata, LainMata, TKetLainMata.getText(),
                        normalTht, nch, sianosisTht, sekretTht, lainTht, TKetLainTHT.getText(), normalMulut, labioS, labioP, labioG, mukosa, TMukosa.getText(),
                        reflek, TReflek.getText(), lainMulut, TKetLainMulut.getText(), normalLeher, torti, benjolKanan, benjolKiri, lainLeher, TKetLainLeher.getText(),
                        simetrisDada, tidakSimetris, retraksiPos, retraksiNeg, TKetRetraksi.getText(), sesak, merintih, sianosisDada, lainDada, TKetLainDada.getText(),
                        bj, murni, tidakMurni, reguler, tidakReguler, bunyi, TKetBunyi.getText(), vesikuler, ronchi, wezing, stridor, lainParu, TKetLainParu.getText(),
                        supel, disten, bising, hepar, limpa, nyeri, TNyeri.getText(), masaPos, masaNeg, uk, Tuk.getText(), lokasi, Tlokasi.getText(), segar, layu,
                        lainTali, TKetLainTali.getText(), normalPunggung, spina, gibus, lainPunggung, TKetLainPunggung.getText(), sex, Tsex.getText(), kelainanUro,
                        TkelainanUro.getText(), bak, Tbak.getText(), cmbAnus.getSelectedItem().toString(), bab, Tbab.getText(), simetrisEks, asimetrisEks,
                        reflekMoroPos, reflekMoroNeg, lainEks, TKetLainEks.getText(), edema, kelainanEks, TkelainanEks.getText(), Valid.mysql_real_escape_stringERM(Tpemerikaaan.getText()),
                        TdiagnosaKerja.getText(), TdiagnosaBanding.getText(), Tpengobatan.getText(), Tdiet.getText(), Trencana.getText(), Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""),
                        cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), Tnip.getText(), tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 168).toString(),
                        "hapus", user, Sequel.cariIsi("select now()")
                    }) == true) {
                System.out.println("Asesmen Medik Perinatologi Dihapus Berhasil Tersimpan Sebagai Data Histori..!!");
            }
        } catch (Exception e) {
            System.out.println("Hapus Histori Asesmen Medik Perinatologi : " + e);
        }
    }
    
    private void gantiDisimpan() {
        cekData();
        try {
            if (Sequel.menyimpantf("asesmen_medik_perinatologi_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 165, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), Tkeluhan.getText(), TriwPenyakitDahulu.getText(), hipertensi, diabet, jantung, strok, asma, kejang,
                        hati, kanker, tb, pms, perdarahan, ginjal, lainRiwayat, TketLainLain.getText(), cmbKondisi.getSelectedItem().toString(), TketAs.getText(),
                        Tgerak.getText(), Ttangis.getText(), TwarnaKulit.getText(), Thr.getText(), Tsuhu.getText(), Trr.getText(), Tsaturasi.getText(),
                        cmbCapilary.getSelectedItem().toString(), Tbbl.getText(), Tpb.getText(), Tlk.getText(), Tld.getText(), Tlp.getText(), Tlla.getText(),
                        turgor, Tturgor.getText(), sianosisKulit, perdarahanKulit, ikterusPos, ikterusNeg, kramer, Tkramer.getText(), hematoma, sklere, kutis,
                        lainKulit, TKetLainKulit.getText(), simetrisKepala, asimetrisKepala, cepal, caput, anen, micros, hidro, lainKepala, TKetLainKepala.getText(),
                        datar, cembung, cekung, lainUub, TKetLainUUB.getText(), normalMata, anemia, ikterus, sekretMata, LainMata, TKetLainMata.getText(),
                        normalTht, nch, sianosisTht, sekretTht, lainTht, TKetLainTHT.getText(), normalMulut, labioS, labioP, labioG, mukosa, TMukosa.getText(),
                        reflek, TReflek.getText(), lainMulut, TKetLainMulut.getText(), normalLeher, torti, benjolKanan, benjolKiri, lainLeher, TKetLainLeher.getText(),
                        simetrisDada, tidakSimetris, retraksiPos, retraksiNeg, TKetRetraksi.getText(), sesak, merintih, sianosisDada, lainDada, TKetLainDada.getText(),
                        bj, murni, tidakMurni, reguler, tidakReguler, bunyi, TKetBunyi.getText(), vesikuler, ronchi, wezing, stridor, lainParu, TKetLainParu.getText(),
                        supel, disten, bising, hepar, limpa, nyeri, TNyeri.getText(), masaPos, masaNeg, uk, Tuk.getText(), lokasi, Tlokasi.getText(), segar, layu,
                        lainTali, TKetLainTali.getText(), normalPunggung, spina, gibus, lainPunggung, TKetLainPunggung.getText(), sex, Tsex.getText(), kelainanUro,
                        TkelainanUro.getText(), bak, Tbak.getText(), cmbAnus.getSelectedItem().toString(), bab, Tbab.getText(), simetrisEks, asimetrisEks,
                        reflekMoroPos, reflekMoroNeg, lainEks, TKetLainEks.getText(), edema, kelainanEks, TkelainanEks.getText(), Valid.mysql_real_escape_stringERM(Tpemerikaaan.getText()),
                        TdiagnosaKerja.getText(), TdiagnosaBanding.getText(), Tpengobatan.getText(), Tdiet.getText(), Trencana.getText(), Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""),
                        cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), Tnip.getText(), tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 168).toString(),
                        "ganti", user, Sequel.cariIsi("select now()")
                    }) == true) {
                System.out.println("Asesmen Medik Perinatologi Diganti Berhasil Tersimpan Sebagai Data Histori..!!");
            }
        } catch (Exception e) {
            System.out.println("Ganti Histori Asesmen Medik Perinatologi : " + e);
        }
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode1);
        try {
            psrestor = koneksi.prepareStatement("SELECT IF(pg.nama='-','Admin Utama',pg.nama) pelaku, a.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                    + "a.tgl_asesmen, a.waktu_eksekusi, upper(concat('DI',a.status_data)) sttsdata FROM asesmen_medik_perinatologi_histori a "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = a.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg ON pg.nik = a.nik_eksekutor WHERE "
                    + "a.tgl_asesmen between ? and ? and pg.nama like ? or "
                    + "a.tgl_asesmen between ? and ? and a.no_rawat like ? or "
                    + "a.tgl_asesmen between ? and ? and p.no_rkm_medis like ? or "
                    + "a.tgl_asesmen between ? and ? and a.status_data like ? or "
                    + "a.tgl_asesmen between ? and ? and p.nm_pasien like ? order by a.tgl_asesmen desc");
            try {
                psrestor.setString(1, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(2, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(3, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(4, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(5, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(6, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(7, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(8, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(9, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(10, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(11, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(12, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(13, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(14, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(15, "%" + TCari2.getText().trim() + "%");
                rsrestor = psrestor.executeQuery();
                while (rsrestor.next()) {
                    tabMode1.addRow(new String[]{
                        rsrestor.getString("pelaku"),
                        rsrestor.getString("no_rawat"),
                        rsrestor.getString("no_rkm_medis"),
                        rsrestor.getString("nm_pasien"),
                        rsrestor.getString("tgl_asesmen"),
                        rsrestor.getString("waktu_eksekusi"),
                        rsrestor.getString("sttsdata")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rsrestor != null) {
                    rsrestor.close();
                }
                if (psrestor != null) {
                    psrestor.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode1.getRowCount());
    }
    
    private void kembalikanData() {
        try {
            ps2 = koneksi.prepareStatement("select * from asesmen_medik_perinatologi_histori where "
                    + "waktu_eksekusi='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString() + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    try {
                        if (Sequel.menyimpantf("asesmen_medik_perinatologi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 162, new String[]{
                                    rs2.getString("no_rawat"),
                                    rs2.getString("ruang_rawat"),
                                    rs2.getString("keluhan"),
                                    rs2.getString("riw_penyakit_dahulu"),
                                    rs2.getString("hipertensi"),
                                    rs2.getString("diabetes"),
                                    rs2.getString("jantung"),
                                    rs2.getString("stroke"),
                                    rs2.getString("asma"),
                                    rs2.getString("kejang"),
                                    rs2.getString("hati"),
                                    rs2.getString("kanker"),
                                    rs2.getString("tb"),
                                    rs2.getString("pms"),
                                    rs2.getString("perdarahan"),
                                    rs2.getString("ginjal"),
                                    rs2.getString("lain_lain"),
                                    rs2.getString("ket_lain_lain"),
                                    rs2.getString("kondisi_saat_lahir"),
                                    rs2.getString("ket_as"),
                                    rs2.getString("gerak"),
                                    rs2.getString("tangis"),
                                    rs2.getString("warna_kulit"),
                                    rs2.getString("hr"),
                                    rs2.getString("suhu"),
                                    rs2.getString("rr"),
                                    rs2.getString("saturasi"),
                                    rs2.getString("capilary_refill"),
                                    rs2.getString("bbl"),
                                    rs2.getString("pb"),
                                    rs2.getString("lk"),
                                    rs2.getString("ld"),
                                    rs2.getString("lp"),
                                    rs2.getString("lla"),
                                    rs2.getString("turgor"),
                                    rs2.getString("ket_turgor"),
                                    rs2.getString("sianosis_kulit"),
                                    rs2.getString("perdarahan_kulit"),
                                    rs2.getString("ikterus_positif"),
                                    rs2.getString("ikterus_negatif"),
                                    rs2.getString("krammer"),
                                    rs2.getString("ket_krammer"),
                                    rs2.getString("hematoma"),
                                    rs2.getString("sklerema"),
                                    rs2.getString("kutis"),
                                    rs2.getString("lainya_kulit"),
                                    rs2.getString("ket_lainya_kulit"),
                                    rs2.getString("simetris_kapala"),
                                    rs2.getString("asimetris_kepala"),
                                    rs2.getString("cephal_hematom"),
                                    rs2.getString("caput_succedaneum"),
                                    rs2.getString("anensefali"),
                                    rs2.getString("microsefal"),
                                    rs2.getString("hydrosefalus"),
                                    rs2.getString("lainya_kepala"),
                                    rs2.getString("ket_lainya_kepala"),
                                    rs2.getString("datar"),
                                    rs2.getString("cembung"),
                                    rs2.getString("cekung"),
                                    rs2.getString("lainya_uub"),
                                    rs2.getString("ket_lainya_uub"),
                                    rs2.getString("normal_mata"),
                                    rs2.getString("anemia"),
                                    rs2.getString("ikterus_mata"),
                                    rs2.getString("sekret_mata"),
                                    rs2.getString("lainya_mata"),
                                    rs2.getString("ket_lainya_mata"),
                                    rs2.getString("normal_tht"),
                                    rs2.getString("nch"),
                                    rs2.getString("sianosis_tht"),
                                    rs2.getString("sekret_tht"),
                                    rs2.getString("lainya_tht"),
                                    rs2.getString("ket_lainya_tht"),
                                    rs2.getString("normal_mulut"),
                                    rs2.getString("labioschisis"),
                                    rs2.getString("labiopalatoschisis"),
                                    rs2.getString("labiognatopalatoschisis"),
                                    rs2.getString("mucosa_warna"),
                                    rs2.getString("ket_warna"),
                                    rs2.getString("reflek_hisap"),
                                    rs2.getString("ket_reflek_hisap"),
                                    rs2.getString("lainya_mulut"),
                                    rs2.getString("ket_lainya_mulut"),
                                    rs2.getString("normal_leher"),
                                    rs2.getString("tortikolis"),
                                    rs2.getString("benjolan_kanan"),
                                    rs2.getString("benjolan_kiri"),
                                    rs2.getString("lainya_leher"),
                                    rs2.getString("ket_lainya_leher"),
                                    rs2.getString("simetris_dada"),
                                    rs2.getString("tidak_simetris"),
                                    rs2.getString("retraksi_positif"),
                                    rs2.getString("retraksi_negatif"),
                                    rs2.getString("ket_retraksi"),
                                    rs2.getString("sesak"),
                                    rs2.getString("merintih"),
                                    rs2.getString("sianosis_dada"),
                                    rs2.getString("lainya_dada"),
                                    rs2.getString("ket_lainya_dada"),
                                    rs2.getString("bji"),
                                    rs2.getString("murni"),
                                    rs2.getString("tidak_murni"),
                                    rs2.getString("reguler"),
                                    rs2.getString("tidak_reguler"),
                                    rs2.getString("bunyi_tambahan"),
                                    rs2.getString("ket_bunyi_tambahan"),
                                    rs2.getString("vesikuler"),
                                    rs2.getString("ronchi"),
                                    rs2.getString("whezing"),
                                    rs2.getString("stridor"),
                                    rs2.getString("lainya_paru"),
                                    rs2.getString("ket_lainya_paru"),
                                    rs2.getString("supel"),
                                    rs2.getString("distensi"),
                                    rs2.getString("bising_usus"),
                                    rs2.getString("pembesaran_hepar"),
                                    rs2.getString("pembesaran_limpa"),
                                    rs2.getString("nyeri"),
                                    rs2.getString("ket_nyeri"),
                                    rs2.getString("massa_positif"),
                                    rs2.getString("massa_negatif"),
                                    rs2.getString("uk"),
                                    rs2.getString("ket_uk"),
                                    rs2.getString("lokasi"),
                                    rs2.getString("ket_lokasi"),
                                    rs2.getString("segar"),
                                    rs2.getString("layu"),
                                    rs2.getString("lainya_tali_pusat"),
                                    rs2.getString("ket_lainya_tali_pusat"),
                                    rs2.getString("normal_punggung"),
                                    rs2.getString("spina"),
                                    rs2.getString("gibus"),
                                    rs2.getString("lainya_punggung"),
                                    rs2.getString("ket_lainya_punggung"),
                                    rs2.getString("sex"),
                                    rs2.getString("ket_sex"),
                                    rs2.getString("kelainan_urogenitalia"),
                                    rs2.getString("ket_kelainan_urogenitalia"),
                                    rs2.getString("bak"),
                                    rs2.getString("ket_bak"),
                                    rs2.getString("anus"),
                                    rs2.getString("bab"),
                                    rs2.getString("ket_bab"),
                                    rs2.getString("simetris_ekstremitas"),
                                    rs2.getString("asimetris_ekstremitas"),
                                    rs2.getString("reflek_moro_positif"),
                                    rs2.getString("reflek_moro_negatif"),
                                    rs2.getString("lainya_ekstremitas"),
                                    rs2.getString("ket_lainya_ekstremitas"),
                                    rs2.getString("edema"),
                                    rs2.getString("kelainan_ekstremitas"),
                                    rs2.getString("ket_kelainan_ekstremitas"),
                                    Valid.mysql_real_escape_stringERM(rs2.getString("pemeriksaan_penunjang")),
                                    rs2.getString("diagnosa_kerja"),
                                    rs2.getString("diagnosa_banding"),
                                    rs2.getString("pengobatan"),
                                    rs2.getString("diet"),
                                    rs2.getString("rencana"),
                                    rs2.getString("tgl_asesmen"),
                                    rs2.getString("jam_asesmen"),
                                    rs2.getString("nip_dpjp"),
                                    rs2.getString("waktu_simpan")
                                }) == true) {
                            System.out.println("Proses mengembalikan data berhasil..!!");
                        }
                    } catch (Exception e) {
                        System.out.println("Simpan : " + e);
                    }
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void kembalikanDataDiganti() {
        if (Sequel.queryu2tf("delete from asesmen_medik_perinatologi where no_rawat=?", 1, new String[]{
            tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString()
        }) == true) {
            kembalikanData();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal dikembalikan..!!");
        }
    }
    
    private void variabelBersih() {
        user = "";
        dataKonfirmasi = "";
        hipertensi = "";
        diabet = "";
        jantung = "";
        strok = "";
        asma = "";
        kejang = "";
        hati = "";
        kanker = "";
        tb = "";
        pms = "";
        perdarahan = "";
        ginjal = "";
        lainRiwayat = "";
        turgor = "";
        sianosisKulit = "";
        perdarahanKulit = "";
        ikterusPos = "";
        ikterusNeg = "";
        kramer = "";
        hematoma = "";
        sklere = "";
        kutis = "";
        lainKulit = "";
        simetrisKepala = "";
        asimetrisKepala = "";
        cepal = "";
        caput = "";
        anen = "";
        micros = "";
        hidro = "";
        lainKepala = "";
        datar = "";
        cembung = "";
        cekung = "";
        lainUub = "";
        normalMata = "";
        anemia = "";
        ikterus = "";
        sekretMata = "";
        LainMata = "";
        normalTht = "";
        nch = "";
        sianosisTht = "";
        sekretTht = "";
        lainTht = "";
        normalMulut = "";
        labioS = "";
        labioP = "";
        labioG = "";
        mukosa = "";
        reflek = "";
        lainMulut = "";
        normalLeher = "";
        torti = "";
        benjolKanan = "";
        benjolKiri = "";
        lainLeher = "";
        simetrisDada = "";
        tidakSimetris = "";
        retraksiPos = "";
        retraksiNeg = "";
        sesak = "";
        merintih = "";
        sianosisDada = "";
        lainDada = "";
        bj = "";
        murni = "";
        tidakMurni = "";
        reguler = "";
        tidakReguler = "";
        bunyi = "";
        vesikuler = "";
        ronchi = "";
        wezing = "";
        stridor = "";
        lainParu = "";
        supel = "";
        disten = "";
        bising = "";
        hepar = "";
        limpa = "";
        nyeri = "";
        masaPos = "";
        masaNeg = "";
        uk = "";
        lokasi = "";
        segar = "";
        layu = "";
        lainTali = "";
        normalPunggung = "";
        spina = "";
        gibus = "";
        lainPunggung = "";
        sex = "";
        kelainanUro = "";
        bak = "";
        bab = "";
        simetrisEks = "";
        asimetrisEks = "";
        reflekMoroPos = "";
        reflekMoroNeg = "";
        lainEks = "";
        edema = "";
        kelainanEks = "";
    }
}
