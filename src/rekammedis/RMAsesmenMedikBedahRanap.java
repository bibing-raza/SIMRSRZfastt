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
import laporan.DlgHasilPenunjangMedis;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author perpustakaan
 */
public final class RMAsesmenMedikBedahRanap extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, pscppt, psrestor;
    private ResultSet rs, rs1, rs2, rscppt, rsrestor;
    private int i = 0, x = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String user = "", dataKonfirmasi = "", kodekamar = "", napza = "", alkohol = "", pejalan = "", spdGayung = "", spdMotor = "", mobil = "",
            jatuh = "", pohon = "", gedung = "", lainJatuh = "", lukaTembak = "", lukaTusuk = "", lukaHancur = "", lukaBakar = "",
            lainLuka = "", jelasKanan = "", menurunKanan = "", ronciKanan = "", wezingKanan = "", jelasKiri = "", menurunKiri = "", ronciKiri = "", 
            wezingKiri = "", suhu = "", nasal = "", nrm = "", lainSatur = "", kuat = "", lemah = "", reguler = "", ireguler = "",
            hangat = "", panas = "", dingin = "", normal = "", kering = "", lembab = "", alert = "", verbal = "", pain = "", unres = "",
            laserasi = "", abrasi = "", hema = "", kontu = "", dislok = "", lukaDingin = "", lukaTembak7 = "", lukaTusuk8 = "", lukaBakar9 = "",
            edema = "", amput = "", avul = "", nyeri = "", frTerbuka = "", frTertutup = "", lainLokasi = "";

    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsesmenMedikBedahRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Asesmen", "Jam", "Verifikasi DPJP", "Ruang Perawatan",
            "riwayat", "riw_alergi", "riw_medikasi", "riw_penyakit_lain", "makanan_terakhir", "cek_pengaruh_napza", "cek_pengaruh_alkohol", "jenis_napza",
            "jenis_alkohol", "kejadian_lain", "pejalan_kaki", "sepeda_gayung", "sepeda_motor", "mobil", "ket_pejalan_kaki", "ket_sepeda_gayung", "ket_sepeda_motor",
            "ket_mobil", "jatuh", "ket_jatuh", "pohon", "gedung", "lainnya_jatuh", "ket_lain_jatuh", "luka_tembak", "luka_tusuk", "luka_hancur", "luka_bakar", "lainnya_luka",
            "ket_lain_luka", "pelindung_leher", "alat_bantu_nafas", "infus", "pengobatan", "ket_pelindung_leher", "ket_alat_bantu_nafas", "ket_infus", "ket_pengobatan",
            "lain_tindakan", "airway", "trachea", "airway_resusitasi", "airway_reevaluasi", "dada_simetris", "sesak_nafas", "respirasi", "krepitasi", "suara_kanan",
            "kanan_jelas", "kanan_ronchi", "kanan_menurun", "kanan_wheezing", "suara_kiri", "kiri_jelas", "kiri_ronchi", "kiri_menurun", "kiri_wheezing", "saturasi",
            "suhu_ruangan", "nrm", "nazal_canule", "lain_pada", "ket_lain_pada", "breathing_asesmen", "breathing_resusitasi", "breathing_reevaluasi", "tensi", "nadi",
            "kuat", "lemah", "reguler", "ireguler", "suhu_axila", "suhu_rectal", "temp_hangat", "temp_panas", "temp_dingin", "kulit_normal", "kulit_kering", "kulit_lembab_basah",
            "circulation_asesmen", "circulation_resusitasi", "circulation_reevaluasi", "frekuensi_pernafasan", "usaha_bernafas", "tekanan_darah", "pengisian_kapiler",
            "gcs", "alert", "verbal", "pain", "unresponsive", "exposure", "pupil_kanan", "pupil_kiri", "kanan_cepat", "kanan_konstriksi", "kanan_lambat", "kanan_dilatasi",
            "kanan_tak_bereaksi", "kiri_cepat", "kiri_konstriksi", "kiri_lambat", "kiri_dilatasi", "kiri_tak_bereaksi", "laserasi", "abrasi", "hematoma", "kontusio", "dislokasi",
            "luka_dingin", "lokasi_luka_tembak", "lokasi_luka_tusuk", "lokasi_luka_bakar", "edema", "amputasi", "avulse", "nyeri", "fraktur_terbuka", "fraktur_tertutup",
            "lokasi_lain", "ket_lokasi_lain", "deskripsi_anamnesis", "tgl_asesmen", "jam_asesmen", "nip_dpjp", "waktu_simpan"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 134; i++) {
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
                column.setPreferredWidth(60);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(250);
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
        
        TPejalan.setDocument(new batasInput((int) 200).getKata(TPejalan));
        TSepedaGayung.setDocument(new batasInput((int) 200).getKata(TSepedaGayung));
        TSepedaMotor.setDocument(new batasInput((int) 200).getKata(TSepedaMotor));
        TMobil.setDocument(new batasInput((int) 200).getKata(TMobil));
        Tjatuh.setDocument(new batasInput((int) 7).getKata(Tjatuh));
        TLainJatuh.setDocument(new batasInput((int) 200).getKata(TLainJatuh));
        TLainLuka.setDocument(new batasInput((int) 200).getKata(TLainLuka));
        TPelindung.setDocument(new batasInput((int) 200).getKata(TPelindung));
        TAlat.setDocument(new batasInput((int) 200).getKata(TAlat));
        TInfus.setDocument(new batasInput((int) 200).getKata(TInfus));
        TPengobatan.setDocument(new batasInput((int) 200).getKata(TPengobatan));
        TresusitasiAirway.setDocument(new batasInput((int) 200).getKata(TresusitasiAirway));
        TreevaluasiAirway.setDocument(new batasInput((int) 200).getKata(TreevaluasiAirway));
        Trespi.setDocument(new batasInput((int) 7).getKata(Trespi));
        Tsaturasi.setDocument(new batasInput((int) 7).getKata(Tsaturasi));
        TLainPada.setDocument(new batasInput((int) 200).getKata(TLainPada));
        TasesmenBreating.setDocument(new batasInput((int) 200).getKata(TasesmenBreating));
        TresusBreating.setDocument(new batasInput((int) 200).getKata(TresusBreating));
        TreevaluasiBreating.setDocument(new batasInput((int) 200).getKata(TreevaluasiBreating));
        Ttensi.setDocument(new batasInput((int) 7).getKata(Ttensi));
        Tnadi.setDocument(new batasInput((int) 7).getKata(Tnadi));
        TsuhuAxila.setDocument(new batasInput((int) 7).getKata(TsuhuAxila));
        TsuhuRectal.setDocument(new batasInput((int) 7).getKata(TsuhuRectal));
        TasesmenCircul.setDocument(new batasInput((int) 200).getKata(TasesmenCircul));
        TresusCircul.setDocument(new batasInput((int) 200).getKata(TresusCircul));
        TreevaluasiCircul.setDocument(new batasInput((int) 200).getKata(TreevaluasiCircul));
        TpupilKanan.setDocument(new batasInput((int) 7).getKata(TpupilKanan));
        TpupilKiri.setDocument(new batasInput((int) 7).getKata(TpupilKiri));        
        TcepatKanan.setDocument(new batasInput((int) 200).getKata(TcepatKanan));
        TkonstriksiKanan.setDocument(new batasInput((int) 200).getKata(TkonstriksiKanan));
        TlambatKanan.setDocument(new batasInput((int) 200).getKata(TlambatKanan));
        TdilatasiKanan.setDocument(new batasInput((int) 200).getKata(TdilatasiKanan));
        TtakKanan.setDocument(new batasInput((int) 200).getKata(TtakKanan));
        TcepatKiri.setDocument(new batasInput((int) 200).getKata(TcepatKiri));
        TkonstriksiKiri.setDocument(new batasInput((int) 200).getKata(TkonstriksiKiri));
        TlambatKiri.setDocument(new batasInput((int) 200).getKata(TlambatKiri));
        TdilatasiKiri.setDocument(new batasInput((int) 200).getKata(TdilatasiKiri));
        TtakKiri.setDocument(new batasInput((int) 200).getKata(TtakKiri));
        TlainLokasi.setDocument(new batasInput((int) 200).getKata(TlainLokasi));        
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
                if (akses.getform().equals("RMAsesmenMedikBedahRanap")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        TnipDpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        TnmDpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDpjp.requestFocus();
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
        label19 = new widget.Label();
        TsuhuAxila = new widget.TextBox();
        jLabel23 = new widget.Label();
        Tjatuh = new widget.TextBox();
        label104 = new widget.Label();
        Tsaturasi = new widget.TextBox();
        Trespi = new widget.TextBox();
        label106 = new widget.Label();
        jLabel34 = new widget.Label();
        TnipDpjp = new widget.TextBox();
        TnmDpjp = new widget.TextBox();
        BtnDpjp = new widget.Button();
        jLabel12 = new widget.Label();
        TtglAsesmen = new widget.Tanggal();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel65 = new widget.Label();
        jLabel67 = new widget.Label();
        jLabel24 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel68 = new widget.Label();
        cmbRiwayat = new widget.ComboBox();
        jLabel69 = new widget.Label();
        cmbAlergi = new widget.ComboBox();
        jLabel66 = new widget.Label();
        cmbMedikasi = new widget.ComboBox();
        jLabel70 = new widget.Label();
        cmbPenyakit = new widget.ComboBox();
        jLabel25 = new widget.Label();
        jLabel71 = new widget.Label();
        chkNapza = new widget.CekBox();
        TNapza = new widget.TextBox();
        jLabel72 = new widget.Label();
        chkAlkohol = new widget.CekBox();
        TAlkohol = new widget.TextBox();
        TKejadian = new widget.TextBox();
        jLabel73 = new widget.Label();
        jLabel74 = new widget.Label();
        chkPejalan = new widget.CekBox();
        chkSepedaGayung = new widget.CekBox();
        chkSepedaMotor = new widget.CekBox();
        chkMobil = new widget.CekBox();
        jLabel26 = new widget.Label();
        jLabel27 = new widget.Label();
        jLabel28 = new widget.Label();
        jLabel29 = new widget.Label();
        TPejalan = new widget.TextBox();
        TSepedaGayung = new widget.TextBox();
        TSepedaMotor = new widget.TextBox();
        TMobil = new widget.TextBox();
        jLabel75 = new widget.Label();
        chkJatuh = new widget.CekBox();
        chkPohon = new widget.CekBox();
        chkGedung = new widget.CekBox();
        chkLainJatuh = new widget.CekBox();
        TLainJatuh = new widget.TextBox();
        chkLukaTembak = new widget.CekBox();
        chkLukaTusuk = new widget.CekBox();
        chkLukaHancur = new widget.CekBox();
        chkLukaBakar = new widget.CekBox();
        chkLainLuka = new widget.CekBox();
        TLainLuka = new widget.TextBox();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        cmbPelindung = new widget.ComboBox();
        jLabel78 = new widget.Label();
        cmbAlat = new widget.ComboBox();
        jLabel79 = new widget.Label();
        cmbInfus = new widget.ComboBox();
        jLabel80 = new widget.Label();
        cmbPengobatan = new widget.ComboBox();
        TPelindung = new widget.TextBox();
        TAlat = new widget.TextBox();
        TInfus = new widget.TextBox();
        TPengobatan = new widget.TextBox();
        jLabel81 = new widget.Label();
        TLainTindakan = new widget.TextBox();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        jLabel84 = new widget.Label();
        cmbAirway = new widget.ComboBox();
        jLabel85 = new widget.Label();
        cmbTrachea = new widget.ComboBox();
        jLabel86 = new widget.Label();
        TresusitasiAirway = new widget.TextBox();
        jLabel87 = new widget.Label();
        TreevaluasiAirway = new widget.TextBox();
        jLabel88 = new widget.Label();
        jLabel89 = new widget.Label();
        cmbDada = new widget.ComboBox();
        jLabel90 = new widget.Label();
        cmbSesak = new widget.ComboBox();
        jLabel91 = new widget.Label();
        cmbKrepitasi = new widget.ComboBox();
        jLabel92 = new widget.Label();
        cmbSuaraKanan = new widget.ComboBox();
        chkJelasKanan = new widget.CekBox();
        chkMenurunKanan = new widget.CekBox();
        chkRonchiKanan = new widget.CekBox();
        chkWezingKanan = new widget.CekBox();
        jLabel93 = new widget.Label();
        cmbSuaraKiri = new widget.ComboBox();
        chkJelasKiri = new widget.CekBox();
        chkMenurunKiri = new widget.CekBox();
        chkRonchiKiri = new widget.CekBox();
        chkWezingKiri = new widget.CekBox();
        jLabel94 = new widget.Label();
        chkSuhuRuangan = new widget.CekBox();
        chkNasal = new widget.CekBox();
        chkNRM = new widget.CekBox();
        chkLainPada = new widget.CekBox();
        TLainPada = new widget.TextBox();
        jLabel95 = new widget.Label();
        TasesmenBreating = new widget.TextBox();
        jLabel96 = new widget.Label();
        TresusBreating = new widget.TextBox();
        jLabel97 = new widget.Label();
        TreevaluasiBreating = new widget.TextBox();
        jLabel98 = new widget.Label();
        jLabel99 = new widget.Label();
        Ttensi = new widget.TextBox();
        jLabel35 = new widget.Label();
        label107 = new widget.Label();
        chkKuat = new widget.CekBox();
        chkLemah = new widget.CekBox();
        chkReguler = new widget.CekBox();
        chkIreguler = new widget.CekBox();
        jLabel100 = new widget.Label();
        TsuhuRectal = new widget.TextBox();
        jLabel36 = new widget.Label();
        chkHangat = new widget.CekBox();
        chkPanas = new widget.CekBox();
        chkDingin = new widget.CekBox();
        jLabel101 = new widget.Label();
        chkNormal = new widget.CekBox();
        chkKering = new widget.CekBox();
        chkLembab = new widget.CekBox();
        jLabel102 = new widget.Label();
        TasesmenCircul = new widget.TextBox();
        jLabel103 = new widget.Label();
        TresusCircul = new widget.TextBox();
        jLabel104 = new widget.Label();
        TreevaluasiCircul = new widget.TextBox();
        jLabel105 = new widget.Label();
        jLabel106 = new widget.Label();
        cmbFrekuensi = new widget.ComboBox();
        jLabel107 = new widget.Label();
        TskorA = new widget.TextBox();
        jLabel108 = new widget.Label();
        cmbUsaha = new widget.ComboBox();
        jLabel109 = new widget.Label();
        TskorB = new widget.TextBox();
        jLabel110 = new widget.Label();
        cmbTekanan = new widget.ComboBox();
        jLabel111 = new widget.Label();
        TskorC = new widget.TextBox();
        jLabel112 = new widget.Label();
        cmbPengisian = new widget.ComboBox();
        jLabel113 = new widget.Label();
        TskorD = new widget.TextBox();
        jLabel114 = new widget.Label();
        cmbGCS = new widget.ComboBox();
        jLabel115 = new widget.Label();
        TskorE = new widget.TextBox();
        jLabel116 = new widget.Label();
        TskorTotal = new widget.TextBox();
        jLabel117 = new widget.Label();
        chkAlert = new widget.CekBox();
        chkVerbal = new widget.CekBox();
        chkPain = new widget.CekBox();
        chkUnrespon = new widget.CekBox();
        jLabel118 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        Texposur = new widget.TextArea();
        jLabel119 = new widget.Label();
        jLabel120 = new widget.Label();
        TpupilKanan = new widget.TextBox();
        label108 = new widget.Label();
        jLabel121 = new widget.Label();
        TcepatKanan = new widget.TextBox();
        jLabel122 = new widget.Label();
        TkonstriksiKanan = new widget.TextBox();
        jLabel123 = new widget.Label();
        TlambatKanan = new widget.TextBox();
        jLabel124 = new widget.Label();
        TdilatasiKanan = new widget.TextBox();
        jLabel125 = new widget.Label();
        TtakKanan = new widget.TextBox();
        jLabel126 = new widget.Label();
        jLabel127 = new widget.Label();
        jLabel128 = new widget.Label();
        jLabel129 = new widget.Label();
        jLabel130 = new widget.Label();
        TcepatKiri = new widget.TextBox();
        TkonstriksiKiri = new widget.TextBox();
        TlambatKiri = new widget.TextBox();
        TdilatasiKiri = new widget.TextBox();
        TtakKiri = new widget.TextBox();
        TpupilKiri = new widget.TextBox();
        label109 = new widget.Label();
        jLabel131 = new widget.Label();
        jLabel132 = new widget.Label();
        PanelWall = new usu.widget.glass.PanelGlass();
        jLabel133 = new widget.Label();
        chkLaserasi = new widget.CekBox();
        chkAbrasi = new widget.CekBox();
        chkHematoma = new widget.CekBox();
        chkKontusio = new widget.CekBox();
        chkDislokasi = new widget.CekBox();
        chkLukaDingin = new widget.CekBox();
        chkLukaTembak7 = new widget.CekBox();
        chkLukaTusuk8 = new widget.CekBox();
        chkLukaBakar9 = new widget.CekBox();
        chkEdema = new widget.CekBox();
        chkAmputasi = new widget.CekBox();
        chkAvulse = new widget.CekBox();
        chkNyeri = new widget.CekBox();
        chkFrakturTerbuka = new widget.CekBox();
        chkFrakturTertutup = new widget.CekBox();
        chkLainlain = new widget.CekBox();
        TlainLokasi = new widget.TextBox();
        jLabel134 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        Tdeskripsi = new widget.TextArea();
        jLabel135 = new widget.Label();
        jLabel13 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel37 = new widget.Label();
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

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Assesmen Medik Bedah Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-12-2024" }));
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

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-12-2024" }));
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Medik Bedah Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        FormInput.setToolTipText("Klik kanan untuk melihat hasil pemeriksaan penunjang medis");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2261));
        FormInput.setLayout(null);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
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

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("Kejadian - Kejadian Yang Lain (Event) :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label19);
        label19.setBounds(0, 178, 210, 23);

        TsuhuAxila.setForeground(new java.awt.Color(0, 0, 0));
        TsuhuAxila.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TsuhuAxila.setName("TsuhuAxila"); // NOI18N
        TsuhuAxila.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuAxilaKeyPressed(evt);
            }
        });
        FormInput.add(TsuhuAxila);
        TsuhuAxila.setBounds(145, 1018, 48, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("°C    Suhu Rectal :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(197, 1018, 90, 23);

        Tjatuh.setForeground(new java.awt.Color(0, 0, 0));
        Tjatuh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tjatuh.setName("Tjatuh"); // NOI18N
        FormInput.add(Tjatuh);
        Tjatuh.setBounds(200, 346, 50, 23);

        label104.setForeground(new java.awt.Color(0, 0, 0));
        label104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label104.setText("meter dari :");
        label104.setName("label104"); // NOI18N
        label104.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label104);
        label104.setBounds(255, 346, 60, 23);

        Tsaturasi.setForeground(new java.awt.Color(0, 0, 0));
        Tsaturasi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tsaturasi.setName("Tsaturasi"); // NOI18N
        FormInput.add(Tsaturasi);
        Tsaturasi.setBounds(145, 850, 50, 23);

        Trespi.setForeground(new java.awt.Color(0, 0, 0));
        Trespi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Trespi.setName("Trespi"); // NOI18N
        Trespi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrespiKeyPressed(evt);
            }
        });
        FormInput.add(Trespi);
        Trespi.setBounds(430, 766, 50, 23);

        label106.setForeground(new java.awt.Color(0, 0, 0));
        label106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label106.setText("x/menit    Krepitasi :");
        label106.setName("label106"); // NOI18N
        label106.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label106);
        label106.setBounds(484, 766, 100, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Verifikasi DPJP :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 2228, 140, 23);

        TnipDpjp.setEditable(false);
        TnipDpjp.setForeground(new java.awt.Color(0, 0, 0));
        TnipDpjp.setName("TnipDpjp"); // NOI18N
        FormInput.add(TnipDpjp);
        TnipDpjp.setBounds(145, 2228, 170, 23);

        TnmDpjp.setEditable(false);
        TnmDpjp.setForeground(new java.awt.Color(0, 0, 0));
        TnmDpjp.setName("TnmDpjp"); // NOI18N
        FormInput.add(TnmDpjp);
        TnmDpjp.setBounds(318, 2228, 410, 23);

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
        BtnDpjp.setBounds(730, 2228, 28, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tanggal :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(218, 2200, 60, 23);

        TtglAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-12-2024" }));
        TtglAsesmen.setDisplayFormat("dd-MM-yyyy");
        TtglAsesmen.setName("TtglAsesmen"); // NOI18N
        TtglAsesmen.setOpaque(false);
        TtglAsesmen.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglAsesmen);
        TtglAsesmen.setBounds(284, 2200, 90, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(195, 122, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(246, 122, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(297, 122, 45, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 38, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(115, 38, 458, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Alergi (Allergy) :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 94, 110, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Makanan Terakhir (Last Meal) : Jam ");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 122, 190, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("%     Pada :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(200, 850, 60, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(288, 990, 60, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("RIWAYAT AMPLE :");
        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 66, 130, 23);

        cmbRiwayat.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwayat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Trauma", "Non Trauma" }));
        cmbRiwayat.setName("cmbRiwayat"); // NOI18N
        cmbRiwayat.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbRiwayat);
        cmbRiwayat.setBounds(638, 38, 90, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Riwayat :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(574, 38, 60, 23);

        cmbAlergi.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlergi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak Ada", "Ya" }));
        cmbAlergi.setName("cmbAlergi"); // NOI18N
        cmbAlergi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbAlergi);
        cmbAlergi.setBounds(115, 94, 80, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Medikasi (Medication) :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(200, 94, 120, 23);

        cmbMedikasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbMedikasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak Ada", "Ya" }));
        cmbMedikasi.setName("cmbMedikasi"); // NOI18N
        cmbMedikasi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMedikasi);
        cmbMedikasi.setBounds(325, 94, 80, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Penyakit Lain / Penyerta (Past Illness) :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(410, 94, 210, 23);

        cmbPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenyakit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak Ada", "Ya" }));
        cmbPenyakit.setName("cmbPenyakit"); // NOI18N
        cmbPenyakit.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbPenyakit);
        cmbPenyakit.setBounds(625, 94, 80, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("WITA");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(348, 122, 37, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Pengaruh NAPZA :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(390, 122, 105, 23);

        chkNapza.setBackground(new java.awt.Color(255, 255, 250));
        chkNapza.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNapza.setForeground(new java.awt.Color(0, 0, 0));
        chkNapza.setText("Ya, Jenis");
        chkNapza.setBorderPainted(true);
        chkNapza.setBorderPaintedFlat(true);
        chkNapza.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNapza.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNapza.setName("chkNapza"); // NOI18N
        chkNapza.setOpaque(false);
        chkNapza.setPreferredSize(new java.awt.Dimension(175, 23));
        chkNapza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNapzaActionPerformed(evt);
            }
        });
        FormInput.add(chkNapza);
        chkNapza.setBounds(500, 122, 70, 23);

        TNapza.setForeground(new java.awt.Color(0, 0, 0));
        TNapza.setName("TNapza"); // NOI18N
        TNapza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNapzaKeyPressed(evt);
            }
        });
        FormInput.add(TNapza);
        TNapza.setBounds(574, 122, 155, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Pengaruh Alkohol :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(390, 150, 105, 23);

        chkAlkohol.setBackground(new java.awt.Color(255, 255, 250));
        chkAlkohol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAlkohol.setForeground(new java.awt.Color(0, 0, 0));
        chkAlkohol.setText("Ya, Jenis");
        chkAlkohol.setBorderPainted(true);
        chkAlkohol.setBorderPaintedFlat(true);
        chkAlkohol.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAlkohol.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAlkohol.setName("chkAlkohol"); // NOI18N
        chkAlkohol.setOpaque(false);
        chkAlkohol.setPreferredSize(new java.awt.Dimension(175, 23));
        chkAlkohol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAlkoholActionPerformed(evt);
            }
        });
        FormInput.add(chkAlkohol);
        chkAlkohol.setBounds(500, 150, 70, 23);

        TAlkohol.setForeground(new java.awt.Color(0, 0, 0));
        TAlkohol.setName("TAlkohol"); // NOI18N
        TAlkohol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlkoholKeyPressed(evt);
            }
        });
        FormInput.add(TAlkohol);
        TAlkohol.setBounds(574, 150, 155, 23);

        TKejadian.setForeground(new java.awt.Color(0, 0, 0));
        TKejadian.setName("TKejadian"); // NOI18N
        TKejadian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKejadianKeyPressed(evt);
            }
        });
        FormInput.add(TKejadian);
        TKejadian.setBounds(214, 178, 515, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("INFORMASI SEBELUM MASUK RUMAH SAKIT :");
        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 206, 270, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Kecelakaan Lalu Lintas :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 234, 140, 23);

        chkPejalan.setBackground(new java.awt.Color(255, 255, 250));
        chkPejalan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPejalan.setForeground(new java.awt.Color(0, 0, 0));
        chkPejalan.setText("Pejalan Kaki");
        chkPejalan.setBorderPainted(true);
        chkPejalan.setBorderPaintedFlat(true);
        chkPejalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPejalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPejalan.setName("chkPejalan"); // NOI18N
        chkPejalan.setOpaque(false);
        chkPejalan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPejalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPejalanActionPerformed(evt);
            }
        });
        FormInput.add(chkPejalan);
        chkPejalan.setBounds(145, 234, 105, 23);

        chkSepedaGayung.setBackground(new java.awt.Color(255, 255, 250));
        chkSepedaGayung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSepedaGayung.setForeground(new java.awt.Color(0, 0, 0));
        chkSepedaGayung.setText("Sepeda Gayung");
        chkSepedaGayung.setBorderPainted(true);
        chkSepedaGayung.setBorderPaintedFlat(true);
        chkSepedaGayung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSepedaGayung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSepedaGayung.setName("chkSepedaGayung"); // NOI18N
        chkSepedaGayung.setOpaque(false);
        chkSepedaGayung.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSepedaGayung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSepedaGayungActionPerformed(evt);
            }
        });
        FormInput.add(chkSepedaGayung);
        chkSepedaGayung.setBounds(145, 262, 105, 23);

        chkSepedaMotor.setBackground(new java.awt.Color(255, 255, 250));
        chkSepedaMotor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSepedaMotor.setForeground(new java.awt.Color(0, 0, 0));
        chkSepedaMotor.setText("Sepeda Motor");
        chkSepedaMotor.setBorderPainted(true);
        chkSepedaMotor.setBorderPaintedFlat(true);
        chkSepedaMotor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSepedaMotor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSepedaMotor.setName("chkSepedaMotor"); // NOI18N
        chkSepedaMotor.setOpaque(false);
        chkSepedaMotor.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSepedaMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSepedaMotorActionPerformed(evt);
            }
        });
        FormInput.add(chkSepedaMotor);
        chkSepedaMotor.setBounds(145, 290, 105, 23);

        chkMobil.setBackground(new java.awt.Color(255, 255, 250));
        chkMobil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMobil.setForeground(new java.awt.Color(0, 0, 0));
        chkMobil.setText("Mobil");
        chkMobil.setBorderPainted(true);
        chkMobil.setBorderPaintedFlat(true);
        chkMobil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMobil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMobil.setName("chkMobil"); // NOI18N
        chkMobil.setOpaque(false);
        chkMobil.setPreferredSize(new java.awt.Dimension(175, 23));
        chkMobil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMobilActionPerformed(evt);
            }
        });
        FormInput.add(chkMobil);
        chkMobil.setBounds(145, 318, 105, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("X");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(252, 234, 15, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("X");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(252, 262, 15, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("X");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(252, 290, 15, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("X");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(252, 318, 15, 23);

        TPejalan.setForeground(new java.awt.Color(0, 0, 0));
        TPejalan.setName("TPejalan"); // NOI18N
        TPejalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPejalanKeyPressed(evt);
            }
        });
        FormInput.add(TPejalan);
        TPejalan.setBounds(269, 234, 460, 23);

        TSepedaGayung.setForeground(new java.awt.Color(0, 0, 0));
        TSepedaGayung.setName("TSepedaGayung"); // NOI18N
        TSepedaGayung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSepedaGayungKeyPressed(evt);
            }
        });
        FormInput.add(TSepedaGayung);
        TSepedaGayung.setBounds(269, 262, 460, 23);

        TSepedaMotor.setForeground(new java.awt.Color(0, 0, 0));
        TSepedaMotor.setName("TSepedaMotor"); // NOI18N
        TSepedaMotor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSepedaMotorKeyPressed(evt);
            }
        });
        FormInput.add(TSepedaMotor);
        TSepedaMotor.setBounds(269, 290, 460, 23);

        TMobil.setForeground(new java.awt.Color(0, 0, 0));
        TMobil.setName("TMobil"); // NOI18N
        TMobil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMobilKeyPressed(evt);
            }
        });
        FormInput.add(TMobil);
        TMobil.setBounds(269, 318, 460, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Kecelakaan Lainnya :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 346, 140, 23);

        chkJatuh.setBackground(new java.awt.Color(255, 255, 250));
        chkJatuh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJatuh.setForeground(new java.awt.Color(0, 0, 0));
        chkJatuh.setText("Jatuh");
        chkJatuh.setBorderPainted(true);
        chkJatuh.setBorderPaintedFlat(true);
        chkJatuh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJatuh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJatuh.setName("chkJatuh"); // NOI18N
        chkJatuh.setOpaque(false);
        chkJatuh.setPreferredSize(new java.awt.Dimension(175, 23));
        chkJatuh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJatuhActionPerformed(evt);
            }
        });
        FormInput.add(chkJatuh);
        chkJatuh.setBounds(145, 346, 55, 23);

        chkPohon.setBackground(new java.awt.Color(255, 255, 250));
        chkPohon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPohon.setForeground(new java.awt.Color(0, 0, 0));
        chkPohon.setText("Pohon");
        chkPohon.setBorderPainted(true);
        chkPohon.setBorderPaintedFlat(true);
        chkPohon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPohon.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPohon.setName("chkPohon"); // NOI18N
        chkPohon.setOpaque(false);
        chkPohon.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPohon);
        chkPohon.setBounds(315, 346, 55, 23);

        chkGedung.setBackground(new java.awt.Color(255, 255, 250));
        chkGedung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGedung.setForeground(new java.awt.Color(0, 0, 0));
        chkGedung.setText("Gedung");
        chkGedung.setBorderPainted(true);
        chkGedung.setBorderPaintedFlat(true);
        chkGedung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGedung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGedung.setName("chkGedung"); // NOI18N
        chkGedung.setOpaque(false);
        chkGedung.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGedung);
        chkGedung.setBounds(380, 346, 63, 23);

        chkLainJatuh.setBackground(new java.awt.Color(255, 255, 250));
        chkLainJatuh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainJatuh.setForeground(new java.awt.Color(0, 0, 0));
        chkLainJatuh.setText("Lainnya");
        chkLainJatuh.setBorderPainted(true);
        chkLainJatuh.setBorderPaintedFlat(true);
        chkLainJatuh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainJatuh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainJatuh.setName("chkLainJatuh"); // NOI18N
        chkLainJatuh.setOpaque(false);
        chkLainJatuh.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainJatuh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainJatuhActionPerformed(evt);
            }
        });
        FormInput.add(chkLainJatuh);
        chkLainJatuh.setBounds(450, 346, 63, 23);

        TLainJatuh.setForeground(new java.awt.Color(0, 0, 0));
        TLainJatuh.setName("TLainJatuh"); // NOI18N
        TLainJatuh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLainJatuhKeyPressed(evt);
            }
        });
        FormInput.add(TLainJatuh);
        TLainJatuh.setBounds(519, 346, 210, 23);

        chkLukaTembak.setBackground(new java.awt.Color(255, 255, 250));
        chkLukaTembak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLukaTembak.setForeground(new java.awt.Color(0, 0, 0));
        chkLukaTembak.setText("Luka Tembak");
        chkLukaTembak.setBorderPainted(true);
        chkLukaTembak.setBorderPaintedFlat(true);
        chkLukaTembak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLukaTembak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLukaTembak.setName("chkLukaTembak"); // NOI18N
        chkLukaTembak.setOpaque(false);
        chkLukaTembak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLukaTembak);
        chkLukaTembak.setBounds(145, 374, 90, 23);

        chkLukaTusuk.setBackground(new java.awt.Color(255, 255, 250));
        chkLukaTusuk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLukaTusuk.setForeground(new java.awt.Color(0, 0, 0));
        chkLukaTusuk.setText("Luka Tusuk");
        chkLukaTusuk.setBorderPainted(true);
        chkLukaTusuk.setBorderPaintedFlat(true);
        chkLukaTusuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLukaTusuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLukaTusuk.setName("chkLukaTusuk"); // NOI18N
        chkLukaTusuk.setOpaque(false);
        chkLukaTusuk.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLukaTusuk);
        chkLukaTusuk.setBounds(260, 374, 90, 23);

        chkLukaHancur.setBackground(new java.awt.Color(255, 255, 250));
        chkLukaHancur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLukaHancur.setForeground(new java.awt.Color(0, 0, 0));
        chkLukaHancur.setText("Luka Hancur (Crushed)");
        chkLukaHancur.setBorderPainted(true);
        chkLukaHancur.setBorderPaintedFlat(true);
        chkLukaHancur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLukaHancur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLukaHancur.setName("chkLukaHancur"); // NOI18N
        chkLukaHancur.setOpaque(false);
        chkLukaHancur.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLukaHancur);
        chkLukaHancur.setBounds(145, 402, 140, 23);

        chkLukaBakar.setBackground(new java.awt.Color(255, 255, 250));
        chkLukaBakar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLukaBakar.setForeground(new java.awt.Color(0, 0, 0));
        chkLukaBakar.setText("Luka Bakar");
        chkLukaBakar.setBorderPainted(true);
        chkLukaBakar.setBorderPaintedFlat(true);
        chkLukaBakar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLukaBakar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLukaBakar.setName("chkLukaBakar"); // NOI18N
        chkLukaBakar.setOpaque(false);
        chkLukaBakar.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLukaBakar);
        chkLukaBakar.setBounds(293, 402, 80, 23);

        chkLainLuka.setBackground(new java.awt.Color(255, 255, 250));
        chkLainLuka.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainLuka.setForeground(new java.awt.Color(0, 0, 0));
        chkLainLuka.setText("Lainnya");
        chkLainLuka.setBorderPainted(true);
        chkLainLuka.setBorderPaintedFlat(true);
        chkLainLuka.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainLuka.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainLuka.setName("chkLainLuka"); // NOI18N
        chkLainLuka.setOpaque(false);
        chkLainLuka.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainLuka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainLukaActionPerformed(evt);
            }
        });
        FormInput.add(chkLainLuka);
        chkLainLuka.setBounds(383, 402, 65, 23);

        TLainLuka.setForeground(new java.awt.Color(0, 0, 0));
        TLainLuka.setName("TLainLuka"); // NOI18N
        TLainLuka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLainLukaKeyPressed(evt);
            }
        });
        FormInput.add(TLainLuka);
        TLainLuka.setBounds(449, 402, 280, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("TINDAKAN SEBELUM KE RUMAH SAKIT :");
        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 430, 270, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Pelindung Leher :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 458, 140, 23);

        cmbPelindung.setForeground(new java.awt.Color(0, 0, 0));
        cmbPelindung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbPelindung.setName("cmbPelindung"); // NOI18N
        cmbPelindung.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbPelindung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPelindungActionPerformed(evt);
            }
        });
        FormInput.add(cmbPelindung);
        cmbPelindung.setBounds(145, 458, 60, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Alat Bantu Nafas :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(0, 486, 140, 23);

        cmbAlat.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbAlat.setName("cmbAlat"); // NOI18N
        cmbAlat.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbAlat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAlatActionPerformed(evt);
            }
        });
        FormInput.add(cmbAlat);
        cmbAlat.setBounds(145, 486, 60, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Infus :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 514, 140, 23);

        cmbInfus.setForeground(new java.awt.Color(0, 0, 0));
        cmbInfus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbInfus.setName("cmbInfus"); // NOI18N
        cmbInfus.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbInfus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInfusActionPerformed(evt);
            }
        });
        FormInput.add(cmbInfus);
        cmbInfus.setBounds(145, 514, 60, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Pengobatan :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(0, 542, 140, 23);

        cmbPengobatan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPengobatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbPengobatan.setName("cmbPengobatan"); // NOI18N
        cmbPengobatan.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbPengobatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPengobatanActionPerformed(evt);
            }
        });
        FormInput.add(cmbPengobatan);
        cmbPengobatan.setBounds(145, 542, 60, 23);

        TPelindung.setForeground(new java.awt.Color(0, 0, 0));
        TPelindung.setName("TPelindung"); // NOI18N
        TPelindung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPelindungKeyPressed(evt);
            }
        });
        FormInput.add(TPelindung);
        TPelindung.setBounds(209, 458, 520, 23);

        TAlat.setForeground(new java.awt.Color(0, 0, 0));
        TAlat.setName("TAlat"); // NOI18N
        TAlat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlatKeyPressed(evt);
            }
        });
        FormInput.add(TAlat);
        TAlat.setBounds(209, 486, 520, 23);

        TInfus.setForeground(new java.awt.Color(0, 0, 0));
        TInfus.setName("TInfus"); // NOI18N
        TInfus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TInfusKeyPressed(evt);
            }
        });
        FormInput.add(TInfus);
        TInfus.setBounds(209, 514, 520, 23);

        TPengobatan.setForeground(new java.awt.Color(0, 0, 0));
        TPengobatan.setName("TPengobatan"); // NOI18N
        TPengobatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPengobatanKeyPressed(evt);
            }
        });
        FormInput.add(TPengobatan);
        TPengobatan.setBounds(209, 542, 520, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Lain - Lain :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 570, 140, 23);

        TLainTindakan.setForeground(new java.awt.Color(0, 0, 0));
        TLainTindakan.setName("TLainTindakan"); // NOI18N
        TLainTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLainTindakanKeyPressed(evt);
            }
        });
        FormInput.add(TLainTindakan);
        TLainTindakan.setBounds(145, 570, 584, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("PRIMARY SURVEY :");
        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 598, 140, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("A. Airway");
        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(0, 626, 140, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Airway :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(0, 654, 140, 23);

        cmbAirway.setForeground(new java.awt.Color(0, 0, 0));
        cmbAirway.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bebas", "Tersumbat" }));
        cmbAirway.setName("cmbAirway"); // NOI18N
        cmbAirway.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbAirway);
        cmbAirway.setBounds(145, 654, 85, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Trachea Ditengah :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(230, 654, 110, 23);

        cmbTrachea.setForeground(new java.awt.Color(0, 0, 0));
        cmbTrachea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbTrachea.setName("cmbTrachea"); // NOI18N
        cmbTrachea.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbTrachea);
        cmbTrachea.setBounds(345, 654, 60, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Resusitasi :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 682, 140, 23);

        TresusitasiAirway.setForeground(new java.awt.Color(0, 0, 0));
        TresusitasiAirway.setName("TresusitasiAirway"); // NOI18N
        TresusitasiAirway.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TresusitasiAirwayKeyPressed(evt);
            }
        });
        FormInput.add(TresusitasiAirway);
        TresusitasiAirway.setBounds(145, 682, 584, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Re - Evaluasi :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 710, 140, 23);

        TreevaluasiAirway.setForeground(new java.awt.Color(0, 0, 0));
        TreevaluasiAirway.setName("TreevaluasiAirway"); // NOI18N
        TreevaluasiAirway.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreevaluasiAirwayKeyPressed(evt);
            }
        });
        FormInput.add(TreevaluasiAirway);
        TreevaluasiAirway.setBounds(145, 710, 584, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("B. Breathing");
        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(0, 738, 140, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Dada Simetris :");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 766, 140, 23);

        cmbDada.setForeground(new java.awt.Color(0, 0, 0));
        cmbDada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbDada.setName("cmbDada"); // NOI18N
        cmbDada.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbDada);
        cmbDada.setBounds(145, 766, 60, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Sesak Nafas :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(205, 766, 85, 23);

        cmbSesak.setForeground(new java.awt.Color(0, 0, 0));
        cmbSesak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbSesak.setName("cmbSesak"); // NOI18N
        cmbSesak.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbSesak);
        cmbSesak.setBounds(295, 766, 60, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Respirasi :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(355, 766, 70, 23);

        cmbKrepitasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKrepitasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbKrepitasi.setName("cmbKrepitasi"); // NOI18N
        cmbKrepitasi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKrepitasi);
        cmbKrepitasi.setBounds(585, 766, 60, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Suara Nafas : Kanan");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(0, 794, 140, 23);

        cmbSuaraKanan.setForeground(new java.awt.Color(0, 0, 0));
        cmbSuaraKanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        cmbSuaraKanan.setName("cmbSuaraKanan"); // NOI18N
        cmbSuaraKanan.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbSuaraKanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSuaraKananActionPerformed(evt);
            }
        });
        FormInput.add(cmbSuaraKanan);
        cmbSuaraKanan.setBounds(145, 794, 80, 23);

        chkJelasKanan.setBackground(new java.awt.Color(255, 255, 250));
        chkJelasKanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJelasKanan.setForeground(new java.awt.Color(0, 0, 0));
        chkJelasKanan.setText("Jelas");
        chkJelasKanan.setBorderPainted(true);
        chkJelasKanan.setBorderPaintedFlat(true);
        chkJelasKanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJelasKanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJelasKanan.setName("chkJelasKanan"); // NOI18N
        chkJelasKanan.setOpaque(false);
        chkJelasKanan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkJelasKanan);
        chkJelasKanan.setBounds(230, 794, 55, 23);

        chkMenurunKanan.setBackground(new java.awt.Color(255, 255, 250));
        chkMenurunKanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMenurunKanan.setForeground(new java.awt.Color(0, 0, 0));
        chkMenurunKanan.setText("Menurun");
        chkMenurunKanan.setBorderPainted(true);
        chkMenurunKanan.setBorderPaintedFlat(true);
        chkMenurunKanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMenurunKanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMenurunKanan.setName("chkMenurunKanan"); // NOI18N
        chkMenurunKanan.setOpaque(false);
        chkMenurunKanan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMenurunKanan);
        chkMenurunKanan.setBounds(290, 794, 70, 23);

        chkRonchiKanan.setBackground(new java.awt.Color(255, 255, 250));
        chkRonchiKanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRonchiKanan.setForeground(new java.awt.Color(0, 0, 0));
        chkRonchiKanan.setText("Ronchi");
        chkRonchiKanan.setBorderPainted(true);
        chkRonchiKanan.setBorderPaintedFlat(true);
        chkRonchiKanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRonchiKanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRonchiKanan.setName("chkRonchiKanan"); // NOI18N
        chkRonchiKanan.setOpaque(false);
        chkRonchiKanan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkRonchiKanan);
        chkRonchiKanan.setBounds(365, 794, 60, 23);

        chkWezingKanan.setBackground(new java.awt.Color(255, 255, 250));
        chkWezingKanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkWezingKanan.setForeground(new java.awt.Color(0, 0, 0));
        chkWezingKanan.setText("Wheezing");
        chkWezingKanan.setBorderPainted(true);
        chkWezingKanan.setBorderPaintedFlat(true);
        chkWezingKanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkWezingKanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkWezingKanan.setName("chkWezingKanan"); // NOI18N
        chkWezingKanan.setOpaque(false);
        chkWezingKanan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkWezingKanan);
        chkWezingKanan.setBounds(432, 794, 80, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Suara Nafas : Kiri");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(0, 822, 140, 23);

        cmbSuaraKiri.setForeground(new java.awt.Color(0, 0, 0));
        cmbSuaraKiri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        cmbSuaraKiri.setName("cmbSuaraKiri"); // NOI18N
        cmbSuaraKiri.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbSuaraKiri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSuaraKiriActionPerformed(evt);
            }
        });
        FormInput.add(cmbSuaraKiri);
        cmbSuaraKiri.setBounds(145, 822, 80, 23);

        chkJelasKiri.setBackground(new java.awt.Color(255, 255, 250));
        chkJelasKiri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJelasKiri.setForeground(new java.awt.Color(0, 0, 0));
        chkJelasKiri.setText("Jelas");
        chkJelasKiri.setBorderPainted(true);
        chkJelasKiri.setBorderPaintedFlat(true);
        chkJelasKiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJelasKiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJelasKiri.setName("chkJelasKiri"); // NOI18N
        chkJelasKiri.setOpaque(false);
        chkJelasKiri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkJelasKiri);
        chkJelasKiri.setBounds(230, 822, 55, 23);

        chkMenurunKiri.setBackground(new java.awt.Color(255, 255, 250));
        chkMenurunKiri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMenurunKiri.setForeground(new java.awt.Color(0, 0, 0));
        chkMenurunKiri.setText("Menurun");
        chkMenurunKiri.setBorderPainted(true);
        chkMenurunKiri.setBorderPaintedFlat(true);
        chkMenurunKiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMenurunKiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMenurunKiri.setName("chkMenurunKiri"); // NOI18N
        chkMenurunKiri.setOpaque(false);
        chkMenurunKiri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMenurunKiri);
        chkMenurunKiri.setBounds(290, 822, 70, 23);

        chkRonchiKiri.setBackground(new java.awt.Color(255, 255, 250));
        chkRonchiKiri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRonchiKiri.setForeground(new java.awt.Color(0, 0, 0));
        chkRonchiKiri.setText("Ronchi");
        chkRonchiKiri.setBorderPainted(true);
        chkRonchiKiri.setBorderPaintedFlat(true);
        chkRonchiKiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRonchiKiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRonchiKiri.setName("chkRonchiKiri"); // NOI18N
        chkRonchiKiri.setOpaque(false);
        chkRonchiKiri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkRonchiKiri);
        chkRonchiKiri.setBounds(365, 822, 60, 23);

        chkWezingKiri.setBackground(new java.awt.Color(255, 255, 250));
        chkWezingKiri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkWezingKiri.setForeground(new java.awt.Color(0, 0, 0));
        chkWezingKiri.setText("Wheezing");
        chkWezingKiri.setBorderPainted(true);
        chkWezingKiri.setBorderPaintedFlat(true);
        chkWezingKiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkWezingKiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkWezingKiri.setName("chkWezingKiri"); // NOI18N
        chkWezingKiri.setOpaque(false);
        chkWezingKiri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkWezingKiri);
        chkWezingKiri.setBounds(432, 822, 80, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Saturasi O2 :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(0, 850, 140, 23);

        chkSuhuRuangan.setBackground(new java.awt.Color(255, 255, 250));
        chkSuhuRuangan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSuhuRuangan.setForeground(new java.awt.Color(0, 0, 0));
        chkSuhuRuangan.setText("Suhu Ruangan");
        chkSuhuRuangan.setBorderPainted(true);
        chkSuhuRuangan.setBorderPaintedFlat(true);
        chkSuhuRuangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSuhuRuangan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSuhuRuangan.setName("chkSuhuRuangan"); // NOI18N
        chkSuhuRuangan.setOpaque(false);
        chkSuhuRuangan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSuhuRuangan);
        chkSuhuRuangan.setBounds(263, 850, 100, 23);

        chkNasal.setBackground(new java.awt.Color(255, 255, 250));
        chkNasal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNasal.setForeground(new java.awt.Color(0, 0, 0));
        chkNasal.setText("Nasal Canule");
        chkNasal.setBorderPainted(true);
        chkNasal.setBorderPaintedFlat(true);
        chkNasal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNasal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNasal.setName("chkNasal"); // NOI18N
        chkNasal.setOpaque(false);
        chkNasal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNasal);
        chkNasal.setBounds(370, 850, 90, 23);

        chkNRM.setBackground(new java.awt.Color(255, 255, 250));
        chkNRM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNRM.setForeground(new java.awt.Color(0, 0, 0));
        chkNRM.setText("NRM");
        chkNRM.setBorderPainted(true);
        chkNRM.setBorderPaintedFlat(true);
        chkNRM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNRM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNRM.setName("chkNRM"); // NOI18N
        chkNRM.setOpaque(false);
        chkNRM.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNRM);
        chkNRM.setBounds(470, 850, 50, 23);

        chkLainPada.setBackground(new java.awt.Color(255, 255, 250));
        chkLainPada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainPada.setForeground(new java.awt.Color(0, 0, 0));
        chkLainPada.setText("Lainnya");
        chkLainPada.setBorderPainted(true);
        chkLainPada.setBorderPaintedFlat(true);
        chkLainPada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainPada.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainPada.setName("chkLainPada"); // NOI18N
        chkLainPada.setOpaque(false);
        chkLainPada.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainPada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainPadaActionPerformed(evt);
            }
        });
        FormInput.add(chkLainPada);
        chkLainPada.setBounds(525, 850, 65, 23);

        TLainPada.setForeground(new java.awt.Color(0, 0, 0));
        TLainPada.setName("TLainPada"); // NOI18N
        TLainPada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLainPadaKeyPressed(evt);
            }
        });
        FormInput.add(TLainPada);
        TLainPada.setBounds(594, 850, 135, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Assesment :");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(0, 878, 140, 23);

        TasesmenBreating.setForeground(new java.awt.Color(0, 0, 0));
        TasesmenBreating.setName("TasesmenBreating"); // NOI18N
        TasesmenBreating.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TasesmenBreatingKeyPressed(evt);
            }
        });
        FormInput.add(TasesmenBreating);
        TasesmenBreating.setBounds(145, 878, 584, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Resusitasi :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(0, 906, 140, 23);

        TresusBreating.setForeground(new java.awt.Color(0, 0, 0));
        TresusBreating.setName("TresusBreating"); // NOI18N
        TresusBreating.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TresusBreatingKeyPressed(evt);
            }
        });
        FormInput.add(TresusBreating);
        TresusBreating.setBounds(145, 906, 584, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Re - Evaluasi :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(0, 934, 140, 23);

        TreevaluasiBreating.setForeground(new java.awt.Color(0, 0, 0));
        TreevaluasiBreating.setName("TreevaluasiBreating"); // NOI18N
        TreevaluasiBreating.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreevaluasiBreatingKeyPressed(evt);
            }
        });
        FormInput.add(TreevaluasiBreating);
        TreevaluasiBreating.setBounds(145, 934, 584, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("C. Circulation");
        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(0, 962, 140, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Tensi :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(0, 990, 140, 23);

        Ttensi.setForeground(new java.awt.Color(0, 0, 0));
        Ttensi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ttensi.setName("Ttensi"); // NOI18N
        Ttensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtensiKeyPressed(evt);
            }
        });
        FormInput.add(Ttensi);
        Ttensi.setBounds(145, 990, 65, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("mmHg    Nadi :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(215, 990, 75, 23);

        label107.setForeground(new java.awt.Color(0, 0, 0));
        label107.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label107.setText("x/menit");
        label107.setName("label107"); // NOI18N
        label107.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label107);
        label107.setBounds(353, 990, 43, 23);

        chkKuat.setBackground(new java.awt.Color(255, 255, 250));
        chkKuat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKuat.setForeground(new java.awt.Color(0, 0, 0));
        chkKuat.setText("Kuat");
        chkKuat.setBorderPainted(true);
        chkKuat.setBorderPaintedFlat(true);
        chkKuat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKuat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKuat.setName("chkKuat"); // NOI18N
        chkKuat.setOpaque(false);
        chkKuat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKuat);
        chkKuat.setBounds(400, 990, 50, 23);

        chkLemah.setBackground(new java.awt.Color(255, 255, 250));
        chkLemah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLemah.setForeground(new java.awt.Color(0, 0, 0));
        chkLemah.setText("Lemah");
        chkLemah.setBorderPainted(true);
        chkLemah.setBorderPaintedFlat(true);
        chkLemah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLemah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLemah.setName("chkLemah"); // NOI18N
        chkLemah.setOpaque(false);
        chkLemah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLemah);
        chkLemah.setBounds(456, 990, 60, 23);

        chkReguler.setBackground(new java.awt.Color(255, 255, 250));
        chkReguler.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkReguler.setForeground(new java.awt.Color(0, 0, 0));
        chkReguler.setText("Reguler");
        chkReguler.setToolTipText("");
        chkReguler.setBorderPainted(true);
        chkReguler.setBorderPaintedFlat(true);
        chkReguler.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkReguler.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkReguler.setName("chkReguler"); // NOI18N
        chkReguler.setOpaque(false);
        chkReguler.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkReguler);
        chkReguler.setBounds(520, 990, 65, 23);

        chkIreguler.setBackground(new java.awt.Color(255, 255, 250));
        chkIreguler.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIreguler.setForeground(new java.awt.Color(0, 0, 0));
        chkIreguler.setText("Ireguler");
        chkIreguler.setToolTipText("");
        chkIreguler.setBorderPainted(true);
        chkIreguler.setBorderPaintedFlat(true);
        chkIreguler.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIreguler.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIreguler.setName("chkIreguler"); // NOI18N
        chkIreguler.setOpaque(false);
        chkIreguler.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIreguler);
        chkIreguler.setBounds(590, 990, 65, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Suhu Axilla :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(0, 1018, 140, 23);

        TsuhuRectal.setForeground(new java.awt.Color(0, 0, 0));
        TsuhuRectal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TsuhuRectal.setName("TsuhuRectal"); // NOI18N
        TsuhuRectal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuRectalKeyPressed(evt);
            }
        });
        FormInput.add(TsuhuRectal);
        TsuhuRectal.setBounds(290, 1018, 48, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("°C    Temperatur Kulit :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(342, 1018, 114, 23);

        chkHangat.setBackground(new java.awt.Color(255, 255, 250));
        chkHangat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHangat.setForeground(new java.awt.Color(0, 0, 0));
        chkHangat.setText("Hangat");
        chkHangat.setBorderPainted(true);
        chkHangat.setBorderPaintedFlat(true);
        chkHangat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHangat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHangat.setName("chkHangat"); // NOI18N
        chkHangat.setOpaque(false);
        chkHangat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHangat);
        chkHangat.setBounds(456, 1018, 60, 23);

        chkPanas.setBackground(new java.awt.Color(255, 255, 250));
        chkPanas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPanas.setForeground(new java.awt.Color(0, 0, 0));
        chkPanas.setText("Panas");
        chkPanas.setToolTipText("");
        chkPanas.setBorderPainted(true);
        chkPanas.setBorderPaintedFlat(true);
        chkPanas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPanas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPanas.setName("chkPanas"); // NOI18N
        chkPanas.setOpaque(false);
        chkPanas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPanas);
        chkPanas.setBounds(520, 1018, 65, 23);

        chkDingin.setBackground(new java.awt.Color(255, 255, 250));
        chkDingin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDingin.setForeground(new java.awt.Color(0, 0, 0));
        chkDingin.setText("Dingin");
        chkDingin.setToolTipText("");
        chkDingin.setBorderPainted(true);
        chkDingin.setBorderPaintedFlat(true);
        chkDingin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDingin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDingin.setName("chkDingin"); // NOI18N
        chkDingin.setOpaque(false);
        chkDingin.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDingin);
        chkDingin.setBounds(590, 1018, 65, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Gambaran Kulit :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(0, 1046, 140, 23);

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
        chkNormal.setBounds(145, 1046, 60, 23);

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
        chkKering.setBounds(210, 1046, 60, 23);

        chkLembab.setBackground(new java.awt.Color(255, 255, 250));
        chkLembab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLembab.setForeground(new java.awt.Color(0, 0, 0));
        chkLembab.setText("Lembab/Basah");
        chkLembab.setBorderPainted(true);
        chkLembab.setBorderPaintedFlat(true);
        chkLembab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLembab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLembab.setName("chkLembab"); // NOI18N
        chkLembab.setOpaque(false);
        chkLembab.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLembab);
        chkLembab.setBounds(280, 1046, 100, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Assesment :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(0, 1074, 140, 23);

        TasesmenCircul.setForeground(new java.awt.Color(0, 0, 0));
        TasesmenCircul.setName("TasesmenCircul"); // NOI18N
        TasesmenCircul.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TasesmenCirculKeyPressed(evt);
            }
        });
        FormInput.add(TasesmenCircul);
        TasesmenCircul.setBounds(145, 1074, 584, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Resusitasi :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(0, 1102, 140, 23);

        TresusCircul.setForeground(new java.awt.Color(0, 0, 0));
        TresusCircul.setName("TresusCircul"); // NOI18N
        TresusCircul.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TresusCirculKeyPressed(evt);
            }
        });
        FormInput.add(TresusCircul);
        TresusCircul.setBounds(145, 1102, 584, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Re - Evaluasi :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(0, 1130, 140, 23);

        TreevaluasiCircul.setForeground(new java.awt.Color(0, 0, 0));
        TreevaluasiCircul.setName("TreevaluasiCircul"); // NOI18N
        TreevaluasiCircul.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreevaluasiCirculKeyPressed(evt);
            }
        });
        FormInput.add(TreevaluasiCircul);
        TreevaluasiCircul.setBounds(145, 1130, 584, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("TRAUMA SCORE :");
        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(0, 1158, 140, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("A. Frekuensi Pernafasan :");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(0, 1186, 200, 23);

        cmbFrekuensi.setForeground(new java.awt.Color(0, 0, 0));
        cmbFrekuensi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0", "< 10", "> 35", "25 - 35", "10 - 25" }));
        cmbFrekuensi.setName("cmbFrekuensi"); // NOI18N
        cmbFrekuensi.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFrekuensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFrekuensiActionPerformed(evt);
            }
        });
        FormInput.add(cmbFrekuensi);
        cmbFrekuensi.setBounds(205, 1186, 70, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("Score :");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(305, 1186, 50, 23);

        TskorA.setEditable(false);
        TskorA.setForeground(new java.awt.Color(0, 0, 0));
        TskorA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorA.setText("0");
        TskorA.setName("TskorA"); // NOI18N
        FormInput.add(TskorA);
        TskorA.setBounds(359, 1186, 50, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("B. Usaha Bernafas :");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(0, 1214, 200, 23);

        cmbUsaha.setForeground(new java.awt.Color(0, 0, 0));
        cmbUsaha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Dangkal" }));
        cmbUsaha.setName("cmbUsaha"); // NOI18N
        cmbUsaha.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbUsaha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsahaActionPerformed(evt);
            }
        });
        FormInput.add(cmbUsaha);
        cmbUsaha.setBounds(205, 1214, 70, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("Score :");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(305, 1214, 50, 23);

        TskorB.setEditable(false);
        TskorB.setForeground(new java.awt.Color(0, 0, 0));
        TskorB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorB.setText("0");
        TskorB.setName("TskorB"); // NOI18N
        FormInput.add(TskorB);
        TskorB.setBounds(359, 1214, 50, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("C. Tekanan Darah :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(0, 1242, 200, 23);

        cmbTekanan.setForeground(new java.awt.Color(0, 0, 0));
        cmbTekanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0", "1 - 49 mmHg", "50 - 69 mmHg", "70 - 89 mmHg", "> 89 mmHg" }));
        cmbTekanan.setName("cmbTekanan"); // NOI18N
        cmbTekanan.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbTekanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTekananActionPerformed(evt);
            }
        });
        FormInput.add(cmbTekanan);
        cmbTekanan.setBounds(205, 1242, 100, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Score :");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(305, 1242, 50, 23);

        TskorC.setEditable(false);
        TskorC.setForeground(new java.awt.Color(0, 0, 0));
        TskorC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorC.setText("0");
        TskorC.setName("TskorC"); // NOI18N
        FormInput.add(TskorC);
        TskorC.setBounds(359, 1242, 50, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("D. Pengisian Kapiler :");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(0, 1270, 200, 23);

        cmbPengisian.setForeground(new java.awt.Color(0, 0, 0));
        cmbPengisian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak Ada", "> 2 dtk", "< 2 dtk" }));
        cmbPengisian.setName("cmbPengisian"); // NOI18N
        cmbPengisian.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbPengisian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPengisianActionPerformed(evt);
            }
        });
        FormInput.add(cmbPengisian);
        cmbPengisian.setBounds(205, 1270, 80, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("Score :");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(305, 1270, 50, 23);

        TskorD.setEditable(false);
        TskorD.setForeground(new java.awt.Color(0, 0, 0));
        TskorD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorD.setText("0");
        TskorD.setName("TskorD"); // NOI18N
        FormInput.add(TskorD);
        TskorD.setBounds(359, 1270, 50, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("E. Glasgow Coma Score (GCS) :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(0, 1298, 200, 23);

        cmbGCS.setForeground(new java.awt.Color(0, 0, 0));
        cmbGCS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "3 - 4", "5 - 7", "8 - 10", "11 - 13", "14 - 15" }));
        cmbGCS.setName("cmbGCS"); // NOI18N
        cmbGCS.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbGCS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGCSActionPerformed(evt);
            }
        });
        FormInput.add(cmbGCS);
        cmbGCS.setBounds(205, 1298, 70, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("Score :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(305, 1298, 50, 23);

        TskorE.setEditable(false);
        TskorE.setForeground(new java.awt.Color(0, 0, 0));
        TskorE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorE.setText("0");
        TskorE.setName("TskorE"); // NOI18N
        FormInput.add(TskorE);
        TskorE.setBounds(359, 1298, 50, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("Total Trauma Score (A+B+C+D+E) :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(0, 1326, 355, 23);

        TskorTotal.setEditable(false);
        TskorTotal.setForeground(new java.awt.Color(0, 0, 0));
        TskorTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorTotal.setText("0");
        TskorTotal.setName("TskorTotal"); // NOI18N
        FormInput.add(TskorTotal);
        TskorTotal.setBounds(359, 1326, 50, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("D. Disability");
        jLabel117.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(0, 1354, 140, 23);

        chkAlert.setBackground(new java.awt.Color(255, 255, 250));
        chkAlert.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAlert.setForeground(new java.awt.Color(0, 0, 0));
        chkAlert.setText("Alert");
        chkAlert.setBorderPainted(true);
        chkAlert.setBorderPaintedFlat(true);
        chkAlert.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAlert.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAlert.setName("chkAlert"); // NOI18N
        chkAlert.setOpaque(false);
        chkAlert.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAlert);
        chkAlert.setBounds(145, 1354, 60, 23);

        chkVerbal.setBackground(new java.awt.Color(255, 255, 250));
        chkVerbal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkVerbal.setForeground(new java.awt.Color(0, 0, 0));
        chkVerbal.setText("Verbal Response");
        chkVerbal.setBorderPainted(true);
        chkVerbal.setBorderPaintedFlat(true);
        chkVerbal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkVerbal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkVerbal.setName("chkVerbal"); // NOI18N
        chkVerbal.setOpaque(false);
        chkVerbal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkVerbal);
        chkVerbal.setBounds(210, 1354, 110, 23);

        chkPain.setBackground(new java.awt.Color(255, 255, 250));
        chkPain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPain.setForeground(new java.awt.Color(0, 0, 0));
        chkPain.setText("Pain Respone");
        chkPain.setBorderPainted(true);
        chkPain.setBorderPaintedFlat(true);
        chkPain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPain.setName("chkPain"); // NOI18N
        chkPain.setOpaque(false);
        chkPain.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPain);
        chkPain.setBounds(328, 1354, 100, 23);

        chkUnrespon.setBackground(new java.awt.Color(255, 255, 250));
        chkUnrespon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkUnrespon.setForeground(new java.awt.Color(0, 0, 0));
        chkUnrespon.setText("Unresponsive");
        chkUnrespon.setBorderPainted(true);
        chkUnrespon.setBorderPaintedFlat(true);
        chkUnrespon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkUnrespon.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkUnrespon.setName("chkUnrespon"); // NOI18N
        chkUnrespon.setOpaque(false);
        chkUnrespon.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkUnrespon);
        chkUnrespon.setBounds(440, 1354, 100, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("E. Exposure");
        jLabel118.setToolTipText("");
        jLabel118.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(0, 1382, 140, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        Texposur.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Texposur.setColumns(20);
        Texposur.setRows(5);
        Texposur.setName("Texposur"); // NOI18N
        Texposur.setPreferredSize(new java.awt.Dimension(162, 2000));
        Texposur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TexposurKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Texposur);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(145, 1382, 584, 110);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("REAKSI PUPIL :");
        jLabel119.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(0, 1498, 140, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("Kanan :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(145, 1498, 50, 23);

        TpupilKanan.setForeground(new java.awt.Color(0, 0, 0));
        TpupilKanan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TpupilKanan.setName("TpupilKanan"); // NOI18N
        TpupilKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpupilKananKeyPressed(evt);
            }
        });
        FormInput.add(TpupilKanan);
        TpupilKanan.setBounds(198, 1498, 60, 23);

        label108.setForeground(new java.awt.Color(0, 0, 0));
        label108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label108.setText("(mm)");
        label108.setName("label108"); // NOI18N
        label108.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label108);
        label108.setBounds(264, 1498, 35, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("Cepat :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(115, 1526, 80, 23);

        TcepatKanan.setForeground(new java.awt.Color(0, 0, 0));
        TcepatKanan.setName("TcepatKanan"); // NOI18N
        TcepatKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcepatKananKeyPressed(evt);
            }
        });
        FormInput.add(TcepatKanan);
        TcepatKanan.setBounds(198, 1526, 210, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("Konstriksi :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(115, 1554, 80, 23);

        TkonstriksiKanan.setForeground(new java.awt.Color(0, 0, 0));
        TkonstriksiKanan.setName("TkonstriksiKanan"); // NOI18N
        TkonstriksiKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkonstriksiKananKeyPressed(evt);
            }
        });
        FormInput.add(TkonstriksiKanan);
        TkonstriksiKanan.setBounds(198, 1554, 210, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("Lambat :");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(115, 1582, 80, 23);

        TlambatKanan.setForeground(new java.awt.Color(0, 0, 0));
        TlambatKanan.setName("TlambatKanan"); // NOI18N
        TlambatKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlambatKananKeyPressed(evt);
            }
        });
        FormInput.add(TlambatKanan);
        TlambatKanan.setBounds(198, 1582, 210, 23);

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setText("Dilatasi :");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(115, 1610, 80, 23);

        TdilatasiKanan.setForeground(new java.awt.Color(0, 0, 0));
        TdilatasiKanan.setName("TdilatasiKanan"); // NOI18N
        TdilatasiKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdilatasiKananKeyPressed(evt);
            }
        });
        FormInput.add(TdilatasiKanan);
        TdilatasiKanan.setBounds(198, 1610, 210, 23);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setText("Tak Bereaksi :");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(115, 1638, 80, 23);

        TtakKanan.setForeground(new java.awt.Color(0, 0, 0));
        TtakKanan.setName("TtakKanan"); // NOI18N
        TtakKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtakKananKeyPressed(evt);
            }
        });
        FormInput.add(TtakKanan);
        TtakKanan.setBounds(198, 1638, 210, 23);

        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setText("Cepat :");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(410, 1526, 80, 23);

        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setText("Konstriksi :");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(410, 1554, 80, 23);

        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setText("Lambat :");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(410, 1582, 80, 23);

        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setText("Dilatasi :");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(410, 1610, 80, 23);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setText("Tak Bereaksi :");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(410, 1638, 80, 23);

        TcepatKiri.setForeground(new java.awt.Color(0, 0, 0));
        TcepatKiri.setName("TcepatKiri"); // NOI18N
        TcepatKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcepatKiriKeyPressed(evt);
            }
        });
        FormInput.add(TcepatKiri);
        TcepatKiri.setBounds(495, 1526, 210, 23);

        TkonstriksiKiri.setForeground(new java.awt.Color(0, 0, 0));
        TkonstriksiKiri.setName("TkonstriksiKiri"); // NOI18N
        TkonstriksiKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkonstriksiKiriKeyPressed(evt);
            }
        });
        FormInput.add(TkonstriksiKiri);
        TkonstriksiKiri.setBounds(495, 1554, 210, 23);

        TlambatKiri.setForeground(new java.awt.Color(0, 0, 0));
        TlambatKiri.setName("TlambatKiri"); // NOI18N
        TlambatKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlambatKiriKeyPressed(evt);
            }
        });
        FormInput.add(TlambatKiri);
        TlambatKiri.setBounds(495, 1582, 210, 23);

        TdilatasiKiri.setForeground(new java.awt.Color(0, 0, 0));
        TdilatasiKiri.setName("TdilatasiKiri"); // NOI18N
        TdilatasiKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdilatasiKiriKeyPressed(evt);
            }
        });
        FormInput.add(TdilatasiKiri);
        TdilatasiKiri.setBounds(495, 1610, 210, 23);

        TtakKiri.setForeground(new java.awt.Color(0, 0, 0));
        TtakKiri.setName("TtakKiri"); // NOI18N
        TtakKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtakKiriKeyPressed(evt);
            }
        });
        FormInput.add(TtakKiri);
        TtakKiri.setBounds(495, 1638, 210, 23);

        TpupilKiri.setForeground(new java.awt.Color(0, 0, 0));
        TpupilKiri.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TpupilKiri.setName("TpupilKiri"); // NOI18N
        TpupilKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpupilKiriKeyPressed(evt);
            }
        });
        FormInput.add(TpupilKiri);
        TpupilKiri.setBounds(495, 1498, 60, 23);

        label109.setForeground(new java.awt.Color(0, 0, 0));
        label109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label109.setText("(mm)");
        label109.setName("label109"); // NOI18N
        label109.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label109);
        label109.setBounds(560, 1498, 35, 23);

        jLabel131.setForeground(new java.awt.Color(0, 0, 0));
        jLabel131.setText("Kiri :");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(440, 1498, 50, 23);

        jLabel132.setForeground(new java.awt.Color(0, 0, 0));
        jLabel132.setText("SECONDARY SURVEY : ANAMNESIS DAN PEMERIKSAAN FISIK");
        jLabel132.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(0, 1666, 350, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anatomi_tubuh_manusia.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(50, 1694, 320, 500);

        jLabel133.setForeground(new java.awt.Color(0, 0, 0));
        jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel133.setText("Penomoran Lokasi Luka, Fraktur dan Trauma Lainnya (Beserta Ukuran Luka) :");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(380, 1694, 380, 23);

        chkLaserasi.setBackground(new java.awt.Color(255, 255, 250));
        chkLaserasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLaserasi.setForeground(new java.awt.Color(0, 0, 0));
        chkLaserasi.setText("1. Laserasi");
        chkLaserasi.setBorderPainted(true);
        chkLaserasi.setBorderPaintedFlat(true);
        chkLaserasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLaserasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLaserasi.setName("chkLaserasi"); // NOI18N
        chkLaserasi.setOpaque(false);
        chkLaserasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLaserasi);
        chkLaserasi.setBounds(390, 1722, 130, 23);

        chkAbrasi.setBackground(new java.awt.Color(255, 255, 250));
        chkAbrasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAbrasi.setForeground(new java.awt.Color(0, 0, 0));
        chkAbrasi.setText("2. Abrasi / Excoriasi");
        chkAbrasi.setBorderPainted(true);
        chkAbrasi.setBorderPaintedFlat(true);
        chkAbrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAbrasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAbrasi.setName("chkAbrasi"); // NOI18N
        chkAbrasi.setOpaque(false);
        chkAbrasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAbrasi);
        chkAbrasi.setBounds(390, 1750, 130, 23);

        chkHematoma.setBackground(new java.awt.Color(255, 255, 250));
        chkHematoma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHematoma.setForeground(new java.awt.Color(0, 0, 0));
        chkHematoma.setText("3. Hematoma");
        chkHematoma.setBorderPainted(true);
        chkHematoma.setBorderPaintedFlat(true);
        chkHematoma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHematoma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHematoma.setName("chkHematoma"); // NOI18N
        chkHematoma.setOpaque(false);
        chkHematoma.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHematoma);
        chkHematoma.setBounds(390, 1778, 130, 23);

        chkKontusio.setBackground(new java.awt.Color(255, 255, 250));
        chkKontusio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKontusio.setForeground(new java.awt.Color(0, 0, 0));
        chkKontusio.setText("4. Kontusio");
        chkKontusio.setBorderPainted(true);
        chkKontusio.setBorderPaintedFlat(true);
        chkKontusio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKontusio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKontusio.setName("chkKontusio"); // NOI18N
        chkKontusio.setOpaque(false);
        chkKontusio.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKontusio);
        chkKontusio.setBounds(390, 1806, 130, 23);

        chkDislokasi.setBackground(new java.awt.Color(255, 255, 250));
        chkDislokasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDislokasi.setForeground(new java.awt.Color(0, 0, 0));
        chkDislokasi.setText("5. Dislokasi");
        chkDislokasi.setBorderPainted(true);
        chkDislokasi.setBorderPaintedFlat(true);
        chkDislokasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDislokasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDislokasi.setName("chkDislokasi"); // NOI18N
        chkDislokasi.setOpaque(false);
        chkDislokasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDislokasi);
        chkDislokasi.setBounds(390, 1834, 130, 23);

        chkLukaDingin.setBackground(new java.awt.Color(255, 255, 250));
        chkLukaDingin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLukaDingin.setForeground(new java.awt.Color(0, 0, 0));
        chkLukaDingin.setText("6. Luka Dingin");
        chkLukaDingin.setBorderPainted(true);
        chkLukaDingin.setBorderPaintedFlat(true);
        chkLukaDingin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLukaDingin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLukaDingin.setName("chkLukaDingin"); // NOI18N
        chkLukaDingin.setOpaque(false);
        chkLukaDingin.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLukaDingin);
        chkLukaDingin.setBounds(390, 1862, 130, 23);

        chkLukaTembak7.setBackground(new java.awt.Color(255, 255, 250));
        chkLukaTembak7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLukaTembak7.setForeground(new java.awt.Color(0, 0, 0));
        chkLukaTembak7.setText("7. Luka Tembak");
        chkLukaTembak7.setBorderPainted(true);
        chkLukaTembak7.setBorderPaintedFlat(true);
        chkLukaTembak7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLukaTembak7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLukaTembak7.setName("chkLukaTembak7"); // NOI18N
        chkLukaTembak7.setOpaque(false);
        chkLukaTembak7.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLukaTembak7);
        chkLukaTembak7.setBounds(390, 1890, 130, 23);

        chkLukaTusuk8.setBackground(new java.awt.Color(255, 255, 250));
        chkLukaTusuk8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLukaTusuk8.setForeground(new java.awt.Color(0, 0, 0));
        chkLukaTusuk8.setText("8. Luka Tusuk");
        chkLukaTusuk8.setBorderPainted(true);
        chkLukaTusuk8.setBorderPaintedFlat(true);
        chkLukaTusuk8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLukaTusuk8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLukaTusuk8.setName("chkLukaTusuk8"); // NOI18N
        chkLukaTusuk8.setOpaque(false);
        chkLukaTusuk8.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLukaTusuk8);
        chkLukaTusuk8.setBounds(390, 1918, 130, 23);

        chkLukaBakar9.setBackground(new java.awt.Color(255, 255, 250));
        chkLukaBakar9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLukaBakar9.setForeground(new java.awt.Color(0, 0, 0));
        chkLukaBakar9.setText("9. Luka Bakar");
        chkLukaBakar9.setBorderPainted(true);
        chkLukaBakar9.setBorderPaintedFlat(true);
        chkLukaBakar9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLukaBakar9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLukaBakar9.setName("chkLukaBakar9"); // NOI18N
        chkLukaBakar9.setOpaque(false);
        chkLukaBakar9.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLukaBakar9);
        chkLukaBakar9.setBounds(528, 1722, 130, 23);

        chkEdema.setBackground(new java.awt.Color(255, 255, 250));
        chkEdema.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEdema.setForeground(new java.awt.Color(0, 0, 0));
        chkEdema.setText("10. Edema");
        chkEdema.setBorderPainted(true);
        chkEdema.setBorderPaintedFlat(true);
        chkEdema.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEdema.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEdema.setName("chkEdema"); // NOI18N
        chkEdema.setOpaque(false);
        chkEdema.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkEdema);
        chkEdema.setBounds(528, 1750, 130, 23);

        chkAmputasi.setBackground(new java.awt.Color(255, 255, 250));
        chkAmputasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAmputasi.setForeground(new java.awt.Color(0, 0, 0));
        chkAmputasi.setText("11. Amputasi");
        chkAmputasi.setBorderPainted(true);
        chkAmputasi.setBorderPaintedFlat(true);
        chkAmputasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAmputasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAmputasi.setName("chkAmputasi"); // NOI18N
        chkAmputasi.setOpaque(false);
        chkAmputasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAmputasi);
        chkAmputasi.setBounds(528, 1778, 130, 23);

        chkAvulse.setBackground(new java.awt.Color(255, 255, 250));
        chkAvulse.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAvulse.setForeground(new java.awt.Color(0, 0, 0));
        chkAvulse.setText("12. Avulse");
        chkAvulse.setBorderPainted(true);
        chkAvulse.setBorderPaintedFlat(true);
        chkAvulse.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAvulse.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAvulse.setName("chkAvulse"); // NOI18N
        chkAvulse.setOpaque(false);
        chkAvulse.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAvulse);
        chkAvulse.setBounds(528, 1806, 130, 23);

        chkNyeri.setBackground(new java.awt.Color(255, 255, 250));
        chkNyeri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNyeri.setForeground(new java.awt.Color(0, 0, 0));
        chkNyeri.setText("13. Nyeri");
        chkNyeri.setBorderPainted(true);
        chkNyeri.setBorderPaintedFlat(true);
        chkNyeri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNyeri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNyeri.setName("chkNyeri"); // NOI18N
        chkNyeri.setOpaque(false);
        chkNyeri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNyeri);
        chkNyeri.setBounds(528, 1834, 130, 23);

        chkFrakturTerbuka.setBackground(new java.awt.Color(255, 255, 250));
        chkFrakturTerbuka.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFrakturTerbuka.setForeground(new java.awt.Color(0, 0, 0));
        chkFrakturTerbuka.setText("14. Fraktur Terbuka");
        chkFrakturTerbuka.setBorderPainted(true);
        chkFrakturTerbuka.setBorderPaintedFlat(true);
        chkFrakturTerbuka.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFrakturTerbuka.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFrakturTerbuka.setName("chkFrakturTerbuka"); // NOI18N
        chkFrakturTerbuka.setOpaque(false);
        chkFrakturTerbuka.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkFrakturTerbuka);
        chkFrakturTerbuka.setBounds(528, 1862, 130, 23);

        chkFrakturTertutup.setBackground(new java.awt.Color(255, 255, 250));
        chkFrakturTertutup.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFrakturTertutup.setForeground(new java.awt.Color(0, 0, 0));
        chkFrakturTertutup.setText("15. Fraktur Tertutup");
        chkFrakturTertutup.setBorderPainted(true);
        chkFrakturTertutup.setBorderPaintedFlat(true);
        chkFrakturTertutup.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFrakturTertutup.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFrakturTertutup.setName("chkFrakturTertutup"); // NOI18N
        chkFrakturTertutup.setOpaque(false);
        chkFrakturTertutup.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkFrakturTertutup);
        chkFrakturTertutup.setBounds(528, 1890, 130, 23);

        chkLainlain.setBackground(new java.awt.Color(255, 255, 250));
        chkLainlain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainlain.setForeground(new java.awt.Color(0, 0, 0));
        chkLainlain.setText("16. Lain - Lain");
        chkLainlain.setBorderPainted(true);
        chkLainlain.setBorderPaintedFlat(true);
        chkLainlain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainlain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainlain.setName("chkLainlain"); // NOI18N
        chkLainlain.setOpaque(false);
        chkLainlain.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainlain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainlainActionPerformed(evt);
            }
        });
        FormInput.add(chkLainlain);
        chkLainlain.setBounds(528, 1918, 130, 23);

        TlainLokasi.setForeground(new java.awt.Color(0, 0, 0));
        TlainLokasi.setName("TlainLokasi"); // NOI18N
        TlainLokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainLokasiKeyPressed(evt);
            }
        });
        FormInput.add(TlainLokasi);
        TlainLokasi.setBounds(528, 1946, 240, 23);

        jLabel134.setForeground(new java.awt.Color(0, 0, 0));
        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel134.setText("Deskripsi / Penjelasan Anamnesis dan Pemeriksaan Fisik :");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(389, 1974, 280, 23);

        scrollPane15.setName("scrollPane15"); // NOI18N

        Tdeskripsi.setBorder(null);
        Tdeskripsi.setColumns(20);
        Tdeskripsi.setRows(5);
        Tdeskripsi.setName("Tdeskripsi"); // NOI18N
        Tdeskripsi.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tdeskripsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdeskripsiKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(Tdeskripsi);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(389, 1995, 380, 200);

        jLabel135.setForeground(new java.awt.Color(0, 0, 0));
        jLabel135.setText("Yang Melakukan Pengkajian :");
        jLabel135.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(0, 2200, 210, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jam :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(380, 2200, 40, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(425, 2200, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(475, 2200, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(525, 2200, 45, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("WITA");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(577, 2200, 37, 23);

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
        jLabel19.setText("Tgl. Assesment :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-12-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-12-2024" }));
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
            if (Sequel.menyimpantf("asesmen_medik_bedah_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 129, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), cmbRiwayat.getSelectedItem().toString(), cmbAlergi.getSelectedItem().toString(), cmbMedikasi.getSelectedItem().toString(),
                        cmbPenyakit.getSelectedItem().toString(), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), napza, alkohol,
                        TNapza.getText(), TAlkohol.getText(), TKejadian.getText(), pejalan, spdGayung, spdMotor, mobil, TPejalan.getText(), TSepedaGayung.getText(), TSepedaMotor.getText(),
                        TMobil.getText(), jatuh, Tjatuh.getText(), pohon, gedung, lainJatuh, TLainJatuh.getText(), lukaTembak, lukaTusuk, lukaHancur, lukaBakar, lainLuka, TLainLuka.getText(),
                        cmbPelindung.getSelectedItem().toString(), cmbAlat.getSelectedItem().toString(), cmbInfus.getSelectedItem().toString(), cmbPengobatan.getSelectedItem().toString(),
                        TPelindung.getText(), TAlat.getText(), TInfus.getText(), TPengobatan.getText(), TLainTindakan.getText(), cmbAirway.getSelectedItem().toString(),
                        cmbTrachea.getSelectedItem().toString(), TresusitasiAirway.getText(), TreevaluasiAirway.getText(), cmbDada.getSelectedItem().toString(), cmbSesak.getSelectedItem().toString(),
                        Trespi.getText(), cmbKrepitasi.getSelectedItem().toString(), cmbSuaraKanan.getSelectedItem().toString(), jelasKanan, ronciKanan, menurunKanan, wezingKanan,
                        cmbSuaraKiri.getSelectedItem().toString(), jelasKiri, ronciKiri, menurunKiri, wezingKiri, Tsaturasi.getText(), suhu, nrm, nasal, lainSatur, TLainPada.getText(),
                        TasesmenBreating.getText(), TresusBreating.getText(), TreevaluasiBreating.getText(), Ttensi.getText(), Tnadi.getText(), kuat, lemah, reguler, ireguler,
                        TsuhuAxila.getText(), TsuhuRectal.getText(), hangat, panas, dingin, normal, kering, lembab, TasesmenCircul.getText(), TresusCircul.getText(), TreevaluasiCircul.getText(),
                        cmbFrekuensi.getSelectedItem().toString(), cmbUsaha.getSelectedItem().toString(), cmbTekanan.getSelectedItem().toString(), cmbPengisian.getSelectedItem().toString(),
                        cmbGCS.getSelectedItem().toString(), alert, verbal, pain, unres, Texposur.getText(), TpupilKanan.getText(), TpupilKiri.getText(), TcepatKanan.getText(),
                        TkonstriksiKanan.getText(), TlambatKanan.getText(), TdilatasiKanan.getText(), TtakKanan.getText(), TcepatKiri.getText(), TkonstriksiKiri.getText(), TlambatKiri.getText(),
                        TdilatasiKiri.getText(), TtakKiri.getText(), laserasi, abrasi, hema, kontu, dislok, lukaDingin, lukaTembak7, lukaTusuk8, lukaBakar9, edema, amput, avul, nyeri,
                        frTerbuka, frTertutup, lainLokasi, TlainLokasi.getText(), Tdeskripsi.getText(), Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""),
                        cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), TnipDpjp.getText(), Sequel.cariIsi("select now()")
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Assesmen Medik Bedah Rawat Inap", "Simpan");
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
            
            param.put("riwayat", cmbRiwayat.getSelectedItem().toString());
            param.put("alergi", cmbAlergi.getSelectedItem().toString());
            param.put("medikasi", cmbMedikasi.getSelectedItem().toString());
            param.put("penyakit", cmbPenyakit.getSelectedItem().toString());
            param.put("makanTerakhir", cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " Wita");

            if (chkNapza.isSelected() == true) {
                param.put("napza", "V");
                param.put("jenisNapza", "Ya, Jenis " + TNapza.getText());
            } else {
                param.put("napza", "");
                param.put("jenisNapza", "Ya, Jenis ........");
            }
            
            if (chkAlkohol.isSelected() == true) {
                param.put("alkohol", "V");
                param.put("jenisAlkohol", "Ya, Jenis " + TAlkohol.getText());
            } else {
                param.put("alkohol", "");
                param.put("jenisAlkohol", "Ya, Jenis ........");
            }
            
            param.put("kejadian", TKejadian.getText());
            
            if (chkPejalan.isSelected() == true) {
                param.put("pejalan", "V");
                param.put("ketPejalan", TPejalan.getText());
            } else {
                param.put("pejalan", "");
                param.put("ketPejalan", "....................");
            }
            
            if (chkSepedaGayung.isSelected() == true) {
                param.put("sepedaGayung", "V");
                param.put("ketSepedaGayung", TSepedaGayung.getText());
            } else {
                param.put("sepedaGayung", "");
                param.put("ketSepedaGayung", "....................");
            }
            
            if (chkSepedaMotor.isSelected() == true) {
                param.put("sepedaMotor", "V");
                param.put("ketSepedaMotor", TSepedaMotor.getText());
            } else {
                param.put("sepedaMotor", "");
                param.put("ketSepedaMotor", "....................");
            }
            
            if (chkMobil.isSelected() == true) {
                param.put("mobil", "V");
                param.put("ketMobil", TMobil.getText());
            } else {
                param.put("mobil", "");
                param.put("ketMobil", "....................");
            }
            
            if (chkJatuh.isSelected() == true) {
                param.put("jatuh", "V");
                param.put("ketJatuh", "Jatuh " + Tjatuh.getText() + " meter dari :");
            } else {
                param.put("jatuh", "");
                param.put("ketJatuh", "Jatuh .........  meter dari :");
            }
            
            if (chkPohon.isSelected() == true) {
                param.put("pohon", "V");
            } else {
                param.put("pohon", "");
            }
            
            if (chkGedung.isSelected() == true) {
                param.put("gedung", "V");
            } else {
                param.put("gedung", "");
            }
            
            if (chkLainJatuh.isSelected() == true) {
                param.put("lainJatuh", "V");
                param.put("ketLainJatuh", "Lainnya " + TLainJatuh.getText());
            } else {
                param.put("lainJatuh", "");
                param.put("ketLainJatuh", "Lainnya ....................");
            }
            
            if (chkLukaTembak.isSelected() == true) {
                param.put("lukaTembak", "V");
            } else {
                param.put("lukaTembak", "");
            }
            
            if (chkLukaTusuk.isSelected() == true) {
                param.put("lukaTusuk", "V");
            } else {
                param.put("lukaTusuk", "");
            }
            
            if (chkLukaHancur.isSelected() == true) {
                param.put("lukaHancur", "V");
            } else {
                param.put("lukaHancur", "");
            }
            
            if (chkLukaBakar.isSelected() == true) {
                param.put("lukaBakar", "V");
            } else {
                param.put("lukaBakar", "");
            }
            
            if (chkLainLuka.isSelected() == true) {
                param.put("lainLuka", "V");
                param.put("ketLainLuka", "Lainnya " + TLainLuka.getText());
            } else {
                param.put("lainLuka", "");
                param.put("ketLainLuka", "Lainnya ....................");
            }
            
            if (cmbPelindung.getSelectedIndex() == 1) {
                param.put("pelindung", cmbPelindung.getSelectedItem().toString() + ", " + TPelindung.getText());
            } else {
                param.put("pelindung", cmbPelindung.getSelectedItem().toString());
            }
            
            if (cmbAlat.getSelectedIndex() == 1) {
                param.put("alat", cmbAlat.getSelectedItem().toString() + ", " + TAlat.getText());
            } else {
                param.put("alat", cmbAlat.getSelectedItem().toString());
            }
            
            if (cmbInfus.getSelectedIndex() == 1) {
                param.put("infus", cmbInfus.getSelectedItem().toString() + ", " + TInfus.getText());
            } else {
                param.put("infus", cmbInfus.getSelectedItem().toString());
            }
            
            if (cmbPengobatan.getSelectedIndex() == 1) {
                param.put("pengobatan", cmbPengobatan.getSelectedItem().toString() + ", " + TPengobatan.getText());
            } else {
                param.put("pengobatan", cmbPengobatan.getSelectedItem().toString());
            }
            
            param.put("lainTindakan", TLainTindakan.getText());
            param.put("airway", cmbAirway.getSelectedItem().toString());
            param.put("trachea", cmbTrachea.getSelectedItem().toString());
            param.put("resusitasiAirway", TresusitasiAirway.getText());
            param.put("reevaluasiAirway", TreevaluasiAirway.getText());
            param.put("dada", cmbDada.getSelectedItem().toString());
            param.put("sesak", cmbSesak.getSelectedItem().toString());
            param.put("respi", Trespi.getText() + " x/menit");
            param.put("krepitasi", cmbKrepitasi.getSelectedItem().toString());
            param.put("suaraKanan", cmbSuaraKanan.getSelectedItem().toString());

            if (chkJelasKanan.isSelected() == true) {
                param.put("jelasKanan", "V");
            } else {
                param.put("jelasKanan", "");
            }

            if (chkMenurunKanan.isSelected() == true) {
                param.put("menurunKanan", "V");
            } else {
                param.put("menurunKanan", "");
            }
            
            if (chkRonchiKanan.isSelected() == true) {
                param.put("ronciKanan", "V");
            } else {
                param.put("ronciKanan", "");
            }
            
            if (chkWezingKanan.isSelected() == true) {
                param.put("wezingKanan", "V");
            } else {
                param.put("wezingKanan", "");
            }
            
            param.put("suaraKiri", cmbSuaraKiri.getSelectedItem().toString());

            if (chkJelasKiri.isSelected() == true) {
                param.put("jelasKiri", "V");
            } else {
                param.put("jelasKiri", "");
            }

            if (chkMenurunKiri.isSelected() == true) {
                param.put("menurunKiri", "V");
            } else {
                param.put("menurunKiri", "");
            }
            
            if (chkRonchiKiri.isSelected() == true) {
                param.put("ronciKiri", "V");
            } else {
                param.put("ronciKiri", "");
            }
            
            if (chkWezingKiri.isSelected() == true) {
                param.put("wezingKiri", "V");
            } else {
                param.put("wezingKiri", "");
            }

            param.put("saturasi", Tsaturasi.getText() + " %   Pada ");

            if (chkSuhuRuangan.isSelected() == true) {
                param.put("suhu", "V");
            } else {
                param.put("suhu", "");
            }
            
            if (chkNasal.isSelected() == true) {
                param.put("nasal", "V");
            } else {
                param.put("nasal", "");
            }
            
            if (chkNRM.isSelected() == true) {
                param.put("nrm", "V");
            } else {
                param.put("nrm", "");
            }
            
            if (chkLainPada.isSelected() == true) {
                param.put("lainPada", "V");
                param.put("ketLainPada", "Lainnya " + TLainPada.getText());
            } else {
                param.put("lainPada", "");
                param.put("ketLainPada", "Lainnya ....................");
            }
            
            param.put("asesmenBreathing", TasesmenBreating.getText());
            param.put("resusBreathing", TresusBreating.getText());
            param.put("reevalusiBreathing", TreevaluasiBreating.getText());
            
            if (Ttensi.getText().equals("")) {
                param.put("tensi", "...../..... mmHg");
            } else {
                param.put("tensi", Ttensi.getText() + " mmHg");
            }
            
            if (Tnadi.getText().equals("")) {
                param.put("nadi", "..... x/menit");
            } else {
                param.put("nadi", Tnadi.getText() + " x/menit");
            }
            
            if (chkKuat.isSelected() == true) {
                param.put("kuat", "V");
            } else {
                param.put("kuat", "");
            }
            
            if (chkLemah.isSelected() == true) {
                param.put("lemah", "V");
            } else {
                param.put("lemah", "");
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
            
            if (TsuhuAxila.getText().equals("")) {
                param.put("suhuAxila", "..... °C");
            } else {
                param.put("suhuAxila", TsuhuAxila.getText() + " °C");
            }
            
            if (TsuhuRectal.getText().equals("")) {
                param.put("suhuRectal", "..... °C");
            } else {
                param.put("suhuRectal", TsuhuRectal.getText() + " °C");
            }
            
            if (chkHangat.isSelected() == true) {
                param.put("hangat", "V");
            } else {
                param.put("hangat", "");
            }
            
            if (chkPanas.isSelected() == true) {
                param.put("panas", "V");
            } else {
                param.put("panas", "");
            }
            
            if (chkDingin.isSelected() == true) {
                param.put("dingin", "V");
            } else {
                param.put("dingin", "");
            }
            
            if (chkNormal.isSelected() == true) {
                param.put("normal", "V");
            } else {
                param.put("normal", "");
            }
            
            if (chkKering.isSelected() == true) {
                param.put("kering", "V");
            } else {
                param.put("kering", "");
            }
            
            if (chkLembab.isSelected() == true) {
                param.put("lembab", "V");
            } else {
                param.put("lembab", "");
            }
            
            param.put("asesmenCircul", TasesmenCircul.getText());
            param.put("resusCircul", TresusCircul.getText());
            param.put("reevaluasiCircul", TreevaluasiCircul.getText());
            param.put("frekuensi", cmbFrekuensi.getSelectedItem().toString());
            param.put("usaha", cmbUsaha.getSelectedItem().toString());
            param.put("tekanan", cmbTekanan.getSelectedItem().toString());
            param.put("pengisian", cmbPengisian.getSelectedItem().toString());
            param.put("gcs", cmbGCS.getSelectedItem().toString());
            param.put("skorA", TskorA.getText());
            param.put("skorB", TskorB.getText());
            param.put("skorC", TskorC.getText());
            param.put("skorD", TskorD.getText());
            param.put("skorE", TskorE.getText());
            param.put("totalSkor", TskorTotal.getText());        
            
            if (chkAlert.isSelected() == true) {
                param.put("alert", "V");
            } else {
                param.put("alert", "");
            }
            
            if (chkVerbal.isSelected() == true) {
                param.put("verbal", "V");
            } else {
                param.put("verbal", "");
            }
            
            if (chkPain.isSelected() == true) {
                param.put("pain", "V");
            } else {
                param.put("pain", "");
            }
            
            if (chkUnrespon.isSelected() == true) {
                param.put("unrespon", "V");
            } else {
                param.put("unrespon", "");
            }
            
            if (Texposur.getText().equals("")) {
                param.put("exposure", "............");
            } else {
                param.put("exposure", Texposur.getText());
            }
            
            if (TpupilKanan.getText().equals("")) {
                param.put("pupilKanan", "........ (mm)");
            } else {
                param.put("pupilKanan", TpupilKanan.getText() + " (mm)");
            }
            
            param.put("cepatKanan", TcepatKanan.getText());
            param.put("kontriksiKanan", TkonstriksiKanan.getText());
            param.put("lambatKanan", TlambatKanan.getText());
            param.put("dilatasiKanan", TdilatasiKanan.getText());
            param.put("takKanan", TtakKanan.getText());
            
            if (TpupilKiri.getText().equals("")) {
                param.put("pupilKiri", "........ (mm)");
            } else {
                param.put("pupilKiri", TpupilKiri.getText() + " (mm)");
            }
            
            param.put("cepatKiri", TcepatKiri.getText());
            param.put("kontriksiKiri", TkonstriksiKiri.getText());
            param.put("lambatKiri", TlambatKiri.getText());
            param.put("dilatasiKiri", TdilatasiKiri.getText());
            param.put("takKiri", TtakKiri.getText());
            
            if (chkLaserasi.isSelected() == true) {
                param.put("laserasi", "V");
            } else {
                param.put("laserasi", "");
            }
            
            if (chkAbrasi.isSelected() == true) {
                param.put("abrasi", "V");
            } else {
                param.put("abrasi", "");
            }
            
            if (chkHematoma.isSelected() == true) {
                param.put("hematoma", "V");
            } else {
                param.put("hematoma", "");
            }
            
            if (chkKontusio.isSelected() == true) {
                param.put("kontusio", "V");
            } else {
                param.put("kontusio", "");
            }
            
            if (chkDislokasi.isSelected() == true) {
                param.put("dislokasi", "V");
            } else {
                param.put("dislokasi", "");
            }
            
            if (chkLukaDingin.isSelected() == true) {
                param.put("lukaDingin", "V");
            } else {
                param.put("lukaDingin", "");
            }
            
            if (chkLukaTembak7.isSelected() == true) {
                param.put("lukaTembak7", "V");
            } else {
                param.put("lukaTembak7", "");
            }
            
            if (chkLukaTusuk8.isSelected() == true) {
                param.put("lukaTusuk8", "V");
            } else {
                param.put("lukaTusuk8", "");
            }
            
            if (chkLukaBakar9.isSelected() == true) {
                param.put("lukaBakar9", "V");
            } else {
                param.put("lukaBakar9", "");
            }
            
            if (chkEdema.isSelected() == true) {
                param.put("edema", "V");
            } else {
                param.put("edema", "");
            }
            
            if (chkAmputasi.isSelected() == true) {
                param.put("amputasi", "V");
            } else {
                param.put("amputasi", "");
            }
            
            if (chkAvulse.isSelected() == true) {
                param.put("avulse", "V");
            } else {
                param.put("avulse", "");
            }

            if (chkNyeri.isSelected() == true) {
                param.put("nyeri", "V");
            } else {
                param.put("nyeri", "");
            }
            
            if (chkFrakturTerbuka.isSelected() == true) {
                param.put("frakturTerbuka", "V");
            } else {
                param.put("frakturTerbuka", "");
            }
            
            if (chkFrakturTertutup.isSelected() == true) {
                param.put("frakturTertutup", "V");
            } else {
                param.put("frakturTertutup", "");
            }
            
            if (chkLainlain.isSelected() == true) {
                param.put("lainlain", "V");
                param.put("ketLainlain", "16. Lainnya " + TlainLokasi.getText());
            } else {
                param.put("lainlain", "");
                param.put("ketLainlain", "16. Lainnya .............");
            }
            
            param.put("deskripsi", Tdeskripsi.getText() + "\n");
            param.put("tanggal", "Tanggal / Jam : " + Valid.SetTglINDONESIA(Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""))
                    + ", Jam : " + cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " WITA");
            param.put("dpjp", "(" + TnmDpjp.getText() + ")");
            
            Valid.MyReport("rptCetakAsesmenMedikBedahRanap2.jasper", "report", "::[ Laporan Asesmen Medik Bedah Rawat Inap hal. 2 ]::",
                    "SELECT now() tanggal", param);
            Valid.MyReport("rptCetakAsesmenMedikBedahRanap1.jasper", "report", "::[ Laporan Asesmen Medik Bedah Rawat Inap hal. 1 ]::",
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
        if (Sequel.cariInteger("select count(-1) from asesmen_medik_bedah_ranap where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from asesmen_medik_bedah_ranap where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void ChkMerokokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkMerokokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkMerokokActionPerformed

    private void ChkAsma1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAsma1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAsma1ActionPerformed

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
                    if (Sequel.cariInteger("select count(-1) from asesmen_medik_bedah_ranap where "
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
            akses.setform("RMAsesmenMedikBedahRanap");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void BtnDpjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDpjpActionPerformed
        ChkAccor.setSelected(false);
        isMenu();

        akses.setform("RMAsesmenMedikBedahRanap");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDpjpActionPerformed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void TexposurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TexposurKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TpupilKanan.requestFocus();
        }
    }//GEN-LAST:event_TexposurKeyPressed

    private void TdeskripsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdeskripsiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TtglAsesmen.requestFocus();
        }
    }//GEN-LAST:event_TdeskripsiKeyPressed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void chkNapzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNapzaActionPerformed
        TNapza.setText("");
        if (chkNapza.isSelected() == true) {
            TNapza.setEnabled(true);
            TNapza.requestFocus();
        } else {
            TNapza.setEnabled(false);
        }
    }//GEN-LAST:event_chkNapzaActionPerformed

    private void chkAlkoholActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAlkoholActionPerformed
        TAlkohol.setText("");
        if (chkAlkohol.isSelected() == true) {
            TAlkohol.setEnabled(true);
            TAlkohol.requestFocus();
        } else {
            TAlkohol.setEnabled(false);
        }
    }//GEN-LAST:event_chkAlkoholActionPerformed

    private void TNapzaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNapzaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkAlkohol.requestFocus();
        }
    }//GEN-LAST:event_TNapzaKeyPressed

    private void TAlkoholKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlkoholKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TKejadian.requestFocus();
        }
    }//GEN-LAST:event_TAlkoholKeyPressed

    private void TKejadianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKejadianKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TPejalan.requestFocus();
        }
    }//GEN-LAST:event_TKejadianKeyPressed

    private void chkPejalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPejalanActionPerformed
        TPejalan.setText("");
        if (chkPejalan.isSelected() == true) {
            TPejalan.setEnabled(true);
            TPejalan.requestFocus();
        } else {
            TPejalan.setEnabled(false);
        }
    }//GEN-LAST:event_chkPejalanActionPerformed

    private void chkSepedaGayungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSepedaGayungActionPerformed
        TSepedaGayung.setText("");
        if (chkSepedaGayung.isSelected() == true) {
            TSepedaGayung.setEnabled(true);
            TSepedaGayung.requestFocus();
        } else {
            TSepedaGayung.setEnabled(false);
        }
    }//GEN-LAST:event_chkSepedaGayungActionPerformed

    private void chkSepedaMotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSepedaMotorActionPerformed
        TSepedaMotor.setText("");
        if (chkSepedaMotor.isSelected() == true) {
            TSepedaMotor.setEnabled(true);
            TSepedaMotor.requestFocus();
        } else {
            TSepedaMotor.setEnabled(false);
        }
    }//GEN-LAST:event_chkSepedaMotorActionPerformed

    private void chkMobilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMobilActionPerformed
        TMobil.setText("");
        if (chkMobil.isSelected() == true) {
            TMobil.setEnabled(true);
            TMobil.requestFocus();
        } else {
            TMobil.setEnabled(false);
        }
    }//GEN-LAST:event_chkMobilActionPerformed

    private void TPejalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPejalanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSepedaGayung.requestFocus();
        }
    }//GEN-LAST:event_TPejalanKeyPressed

    private void TSepedaGayungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSepedaGayungKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSepedaMotor.requestFocus();
        }
    }//GEN-LAST:event_TSepedaGayungKeyPressed

    private void TSepedaMotorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSepedaMotorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TMobil.requestFocus();
        }
    }//GEN-LAST:event_TSepedaMotorKeyPressed

    private void TMobilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMobilKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tjatuh.requestFocus();
        }
    }//GEN-LAST:event_TMobilKeyPressed

    private void chkJatuhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJatuhActionPerformed
        Tjatuh.setText("");
        chkPohon.setSelected(false);
        chkGedung.setSelected(false);
        chkLainJatuh.setSelected(false);
        TLainJatuh.setText("");
        TLainJatuh.setEnabled(false);
        
        if (chkJatuh.isSelected() == true) {
            Tjatuh.setEnabled(true);
            chkPohon.setEnabled(true);
            chkGedung.setEnabled(true);
            chkLainJatuh.setEnabled(true);
            Tjatuh.requestFocus();
        } else {
            Tjatuh.setEnabled(false);
            chkPohon.setEnabled(false);
            chkGedung.setEnabled(false);
            chkLainJatuh.setEnabled(false);
        }
    }//GEN-LAST:event_chkJatuhActionPerformed

    private void chkLainJatuhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainJatuhActionPerformed
        TLainJatuh.setText("");
        if (chkLainJatuh.isSelected() == true) {
            TLainJatuh.setEnabled(true);
            TLainJatuh.requestFocus();
        } else {
            TLainJatuh.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainJatuhActionPerformed

    private void TLainJatuhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLainJatuhKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkLukaTembak.requestFocus();
        }
    }//GEN-LAST:event_TLainJatuhKeyPressed

    private void chkLainLukaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainLukaActionPerformed
        TLainLuka.setText("");
        if (chkLainLuka.isSelected() == true) {
            TLainLuka.setEnabled(true);
            TLainLuka.requestFocus();
        } else {
            TLainLuka.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainLukaActionPerformed

    private void TLainLukaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLainLukaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPelindung.requestFocus();
        }
    }//GEN-LAST:event_TLainLukaKeyPressed

    private void TPelindungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPelindungKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbAlat.requestFocus();
        }
    }//GEN-LAST:event_TPelindungKeyPressed

    private void TAlatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbInfus.requestFocus();
        }
    }//GEN-LAST:event_TAlatKeyPressed

    private void TInfusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TInfusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPengobatan.requestFocus();
        }
    }//GEN-LAST:event_TInfusKeyPressed

    private void TPengobatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPengobatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TLainTindakan.requestFocus();
        }
    }//GEN-LAST:event_TPengobatanKeyPressed

    private void cmbPelindungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPelindungActionPerformed
        TPelindung.setText("");
        if (cmbPelindung.getSelectedIndex() == 1) {
            TPelindung.setEnabled(true);
            TPelindung.requestFocus();
        } else {
            TPelindung.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPelindungActionPerformed

    private void cmbAlatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAlatActionPerformed
        TAlat.setText("");
        if (cmbAlat.getSelectedIndex() == 1) {
            TAlat.setEnabled(true);
            TAlat.requestFocus();
        } else {
            TAlat.setEnabled(false);
        }
    }//GEN-LAST:event_cmbAlatActionPerformed

    private void cmbInfusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbInfusActionPerformed
        TInfus.setText("");
        if (cmbInfus.getSelectedIndex() == 1) {
            TInfus.setEnabled(true);
            TInfus.requestFocus();
        } else {
            TInfus.setEnabled(false);
        }
    }//GEN-LAST:event_cmbInfusActionPerformed

    private void cmbPengobatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPengobatanActionPerformed
        TPengobatan.setText("");
        if (cmbPengobatan.getSelectedIndex() == 1) {
            TPengobatan.setEnabled(true);
            TPengobatan.requestFocus();
        } else {
            TPengobatan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPengobatanActionPerformed

    private void cmbSuaraKananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSuaraKananActionPerformed
        chkJelasKanan.setSelected(false);
        chkMenurunKanan.setSelected(false);
        chkRonchiKanan.setSelected(false);
        chkWezingKanan.setSelected(false);

        if (cmbSuaraKanan.getSelectedIndex() == 1) {
            chkJelasKanan.setEnabled(true);
            chkMenurunKanan.setEnabled(true);
            chkRonchiKanan.setEnabled(true);
            chkWezingKanan.setEnabled(true);
            chkJelasKanan.requestFocus();
        } else {
            chkJelasKanan.setEnabled(false);
            chkMenurunKanan.setEnabled(false);
            chkRonchiKanan.setEnabled(false);
            chkWezingKanan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbSuaraKananActionPerformed

    private void cmbSuaraKiriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSuaraKiriActionPerformed
        chkJelasKiri.setSelected(false);
        chkMenurunKiri.setSelected(false);
        chkRonchiKiri.setSelected(false);
        chkWezingKiri.setSelected(false);

        if (cmbSuaraKiri.getSelectedIndex() == 1) {
            chkJelasKiri.setEnabled(true);
            chkMenurunKiri.setEnabled(true);
            chkRonchiKiri.setEnabled(true);
            chkWezingKiri.setEnabled(true);
            chkJelasKiri.requestFocus();
        } else {
            chkJelasKiri.setEnabled(false);
            chkMenurunKiri.setEnabled(false);
            chkRonchiKiri.setEnabled(false);
            chkWezingKiri.setEnabled(false);
        }
    }//GEN-LAST:event_cmbSuaraKiriActionPerformed

    private void chkLainPadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainPadaActionPerformed
        TLainPada.setText("");
        if (chkLainPada.isSelected() == true) {
            TLainPada.setEnabled(true);
            TLainPada.requestFocus();
        } else {
            TLainPada.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainPadaActionPerformed

    private void TLainTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLainTindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbAirway.requestFocus();
        }
    }//GEN-LAST:event_TLainTindakanKeyPressed

    private void TresusitasiAirwayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TresusitasiAirwayKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TreevaluasiAirway.requestFocus();
        }
    }//GEN-LAST:event_TresusitasiAirwayKeyPressed

    private void TreevaluasiAirwayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreevaluasiAirwayKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbDada.requestFocus();
        }
    }//GEN-LAST:event_TreevaluasiAirwayKeyPressed

    private void TrespiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrespiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbKrepitasi.requestFocus();
        }
    }//GEN-LAST:event_TrespiKeyPressed

    private void TLainPadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLainPadaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TasesmenBreating.requestFocus();
        }
    }//GEN-LAST:event_TLainPadaKeyPressed

    private void TasesmenBreatingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TasesmenBreatingKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TresusBreating.requestFocus();
        }
    }//GEN-LAST:event_TasesmenBreatingKeyPressed

    private void TresusBreatingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TresusBreatingKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TreevaluasiBreating.requestFocus();
        }
    }//GEN-LAST:event_TresusBreatingKeyPressed

    private void TreevaluasiBreatingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreevaluasiBreatingKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttensi.requestFocus();
        }
    }//GEN-LAST:event_TreevaluasiBreatingKeyPressed

    private void TtensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtensiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnadi.requestFocus();
        }
    }//GEN-LAST:event_TtensiKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkKuat.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TsuhuAxilaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuAxilaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TsuhuRectal.requestFocus();
        }
    }//GEN-LAST:event_TsuhuAxilaKeyPressed

    private void TsuhuRectalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuRectalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkHangat.requestFocus();
        }
    }//GEN-LAST:event_TsuhuRectalKeyPressed

    private void TasesmenCirculKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TasesmenCirculKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TresusCircul.requestFocus();
        }
    }//GEN-LAST:event_TasesmenCirculKeyPressed

    private void TresusCirculKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TresusCirculKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TreevaluasiCircul.requestFocus();
        }
    }//GEN-LAST:event_TresusCirculKeyPressed

    private void TreevaluasiCirculKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreevaluasiCirculKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbFrekuensi.requestFocus();
        }
    }//GEN-LAST:event_TreevaluasiCirculKeyPressed

    private void TpupilKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpupilKananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TcepatKanan.requestFocus();
        }
    }//GEN-LAST:event_TpupilKananKeyPressed

    private void TcepatKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcepatKananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TkonstriksiKanan.requestFocus();
        }
    }//GEN-LAST:event_TcepatKananKeyPressed

    private void TkonstriksiKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkonstriksiKananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TlambatKanan.requestFocus();
        }
    }//GEN-LAST:event_TkonstriksiKananKeyPressed

    private void TlambatKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlambatKananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdilatasiKanan.requestFocus();
        }
    }//GEN-LAST:event_TlambatKananKeyPressed

    private void TdilatasiKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdilatasiKananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtakKanan.requestFocus();
        }
    }//GEN-LAST:event_TdilatasiKananKeyPressed

    private void TtakKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtakKananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TpupilKiri.requestFocus();
        }
    }//GEN-LAST:event_TtakKananKeyPressed

    private void TpupilKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpupilKiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TcepatKiri.requestFocus();
        }
    }//GEN-LAST:event_TpupilKiriKeyPressed

    private void TcepatKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcepatKiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TkonstriksiKiri.requestFocus();
        }
    }//GEN-LAST:event_TcepatKiriKeyPressed

    private void TkonstriksiKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkonstriksiKiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TlambatKiri.requestFocus();
        }
    }//GEN-LAST:event_TkonstriksiKiriKeyPressed

    private void TlambatKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlambatKiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdilatasiKiri.requestFocus();
        }
    }//GEN-LAST:event_TlambatKiriKeyPressed

    private void TdilatasiKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdilatasiKiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtakKiri.requestFocus();
        }
    }//GEN-LAST:event_TdilatasiKiriKeyPressed

    private void TtakKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtakKiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkLaserasi.requestFocus();
        }
    }//GEN-LAST:event_TtakKiriKeyPressed

    private void chkLainlainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainlainActionPerformed
        TlainLokasi.setText("");
        if (chkLainlain.isSelected() == true) {
            TlainLokasi.setEnabled(true);
            TlainLokasi.requestFocus();
        } else {
            TlainLokasi.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainlainActionPerformed

    private void TlainLokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainLokasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdeskripsi.requestFocus();
        }
    }//GEN-LAST:event_TlainLokasiKeyPressed

    private void cmbFrekuensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFrekuensiActionPerformed
        if (cmbFrekuensi.getSelectedIndex() == 0 || cmbFrekuensi.getSelectedIndex() == 1) {
            TskorA.setText("0");
        } else if (cmbFrekuensi.getSelectedIndex() == 2) {
            TskorA.setText("1");
        } else if (cmbFrekuensi.getSelectedIndex() == 3) {
            TskorA.setText("2");
        } else if (cmbFrekuensi.getSelectedIndex() == 4) {
            TskorA.setText("3");
        } else if (cmbFrekuensi.getSelectedIndex() == 5) {
            TskorA.setText("4");
        }
        hitungSkor();
    }//GEN-LAST:event_cmbFrekuensiActionPerformed

    private void cmbUsahaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsahaActionPerformed
        if (cmbUsaha.getSelectedIndex() == 0 || cmbUsaha.getSelectedIndex() == 2) {
            TskorB.setText("0");
        } else if (cmbUsaha.getSelectedIndex() == 1) {
            TskorB.setText("1");
        }
        hitungSkor();
    }//GEN-LAST:event_cmbUsahaActionPerformed

    private void cmbTekananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTekananActionPerformed
        if (cmbTekanan.getSelectedIndex() == 0 || cmbTekanan.getSelectedIndex() == 1) {
            TskorC.setText("0");
        } else if (cmbTekanan.getSelectedIndex() == 2) {
            TskorC.setText("1");
        } else if (cmbTekanan.getSelectedIndex() == 3) {
            TskorC.setText("2");
        } else if (cmbTekanan.getSelectedIndex() == 4) {
            TskorC.setText("3");
        } else if (cmbTekanan.getSelectedIndex() == 5) {
            TskorC.setText("4");
        }        
        hitungSkor();
    }//GEN-LAST:event_cmbTekananActionPerformed

    private void cmbPengisianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPengisianActionPerformed
        if (cmbPengisian.getSelectedIndex() == 0 || cmbPengisian.getSelectedIndex() == 1) {
            TskorD.setText("0");
        } else if (cmbPengisian.getSelectedIndex() == 2) {
            TskorD.setText("1");
        } else if (cmbPengisian.getSelectedIndex() == 3) {
            TskorD.setText("2");
        }
        hitungSkor();
    }//GEN-LAST:event_cmbPengisianActionPerformed

    private void cmbGCSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGCSActionPerformed
        if (cmbGCS.getSelectedIndex() == 0) {
            TskorE.setText("0");
        } else if (cmbGCS.getSelectedIndex() == 1) {
            TskorE.setText("1");
        } else if (cmbGCS.getSelectedIndex() == 2) {
            TskorE.setText("2");
        } else if (cmbGCS.getSelectedIndex() == 3) {
            TskorE.setText("3");
        } else if (cmbGCS.getSelectedIndex() == 4) {
            TskorE.setText("4");
        } else if (cmbGCS.getSelectedIndex() == 5) {
            TskorE.setText("5");
        }
        hitungSkor();
    }//GEN-LAST:event_cmbGCSActionPerformed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMAsesmenMedikBedahRanap");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (tbRiwayat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data riwayat asesmen medik bedah rawat inap masih kosong...!!!");
            tbRiwayat.requestFocus();
        } else {
            if (tbRiwayat.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from asesmen_medik_bedah_ranap_histori where waktu_eksekusi=?", 1, new String[]{
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

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        kodekamar = "";
        kodekamar = Sequel.cariIsi("select ki.kd_kamar from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
            + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
            + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMAsesmenMedikBedahRanap");
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

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenMedikBedahRanap dialog = new RMAsesmenMedikBedahRanap(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDpjp;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
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
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll6;
    private widget.TextBox TAlat;
    private widget.TextBox TAlkohol;
    private widget.TextBox TCari;
    private widget.TextBox TCari2;
    private widget.TextBox TInfus;
    private widget.TextBox TKejadian;
    private widget.TextBox TLainJatuh;
    private widget.TextBox TLainLuka;
    private widget.TextBox TLainPada;
    private widget.TextBox TLainTindakan;
    private widget.TextBox TMobil;
    private widget.TextBox TNapza;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPejalan;
    private widget.TextBox TPelindung;
    private widget.TextBox TPengobatan;
    private widget.TextBox TSepedaGayung;
    private widget.TextBox TSepedaMotor;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox TasesmenBreating;
    private widget.TextBox TasesmenCircul;
    private widget.TextBox TcepatKanan;
    private widget.TextBox TcepatKiri;
    private widget.TextArea Tdeskripsi;
    private widget.TextBox TdilatasiKanan;
    private widget.TextBox TdilatasiKiri;
    private widget.TextArea Texposur;
    private widget.TextArea Thasil;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tjatuh;
    private widget.TextBox TkonstriksiKanan;
    private widget.TextBox TkonstriksiKiri;
    private widget.TextBox TlainLokasi;
    private widget.TextBox TlambatKanan;
    private widget.TextBox TlambatKiri;
    private widget.TextBox Tnadi;
    private widget.TextBox TnipDpjp;
    private widget.TextBox TnmDpjp;
    private widget.TextBox TpupilKanan;
    private widget.TextBox TpupilKiri;
    private widget.TextBox TreevaluasiAirway;
    private widget.TextBox TreevaluasiBreating;
    private widget.TextBox TreevaluasiCircul;
    private widget.TextBox Trespi;
    private widget.TextBox TresusBreating;
    private widget.TextBox TresusCircul;
    private widget.TextBox TresusitasiAirway;
    private widget.TextBox TrgRawat;
    private widget.TextBox Tsaturasi;
    private widget.TextBox TskorA;
    private widget.TextBox TskorB;
    private widget.TextBox TskorC;
    private widget.TextBox TskorD;
    private widget.TextBox TskorE;
    private widget.TextBox TskorTotal;
    private widget.TextBox TsuhuAxila;
    private widget.TextBox TsuhuRectal;
    private widget.TextBox TtakKanan;
    private widget.TextBox TtakKiri;
    private widget.TextBox Ttensi;
    private widget.Tanggal TtglAsesmen;
    private javax.swing.JDialog WindowRiwayat;
    public widget.CekBox chkAbrasi;
    public widget.CekBox chkAlert;
    public widget.CekBox chkAlkohol;
    public widget.CekBox chkAmputasi;
    public widget.CekBox chkAvulse;
    public widget.CekBox chkDingin;
    public widget.CekBox chkDislokasi;
    public widget.CekBox chkEdema;
    public widget.CekBox chkFrakturTerbuka;
    public widget.CekBox chkFrakturTertutup;
    public widget.CekBox chkGedung;
    public widget.CekBox chkHangat;
    public widget.CekBox chkHematoma;
    public widget.CekBox chkIreguler;
    public widget.CekBox chkJatuh;
    public widget.CekBox chkJelasKanan;
    public widget.CekBox chkJelasKiri;
    public widget.CekBox chkKering;
    public widget.CekBox chkKontusio;
    public widget.CekBox chkKuat;
    public widget.CekBox chkLainJatuh;
    public widget.CekBox chkLainLuka;
    public widget.CekBox chkLainPada;
    public widget.CekBox chkLainlain;
    public widget.CekBox chkLaserasi;
    public widget.CekBox chkLemah;
    public widget.CekBox chkLembab;
    public widget.CekBox chkLukaBakar;
    public widget.CekBox chkLukaBakar9;
    public widget.CekBox chkLukaDingin;
    public widget.CekBox chkLukaHancur;
    public widget.CekBox chkLukaTembak;
    public widget.CekBox chkLukaTembak7;
    public widget.CekBox chkLukaTusuk;
    public widget.CekBox chkLukaTusuk8;
    public widget.CekBox chkMenurunKanan;
    public widget.CekBox chkMenurunKiri;
    public widget.CekBox chkMobil;
    public widget.CekBox chkNRM;
    public widget.CekBox chkNapza;
    public widget.CekBox chkNasal;
    public widget.CekBox chkNormal;
    public widget.CekBox chkNyeri;
    public widget.CekBox chkPain;
    public widget.CekBox chkPanas;
    public widget.CekBox chkPejalan;
    public widget.CekBox chkPohon;
    public widget.CekBox chkReguler;
    public widget.CekBox chkRonchiKanan;
    public widget.CekBox chkRonchiKiri;
    public widget.CekBox chkSepedaGayung;
    public widget.CekBox chkSepedaMotor;
    public widget.CekBox chkSuhuRuangan;
    public widget.CekBox chkUnrespon;
    public widget.CekBox chkVerbal;
    public widget.CekBox chkWezingKanan;
    public widget.CekBox chkWezingKiri;
    private widget.ComboBox cmbAirway;
    private widget.ComboBox cmbAlat;
    private widget.ComboBox cmbAlergi;
    private widget.ComboBox cmbDada;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbFrekuensi;
    private widget.ComboBox cmbGCS;
    private widget.ComboBox cmbInfus;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbKrepitasi;
    private widget.ComboBox cmbMedikasi;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbPelindung;
    private widget.ComboBox cmbPengisian;
    private widget.ComboBox cmbPengobatan;
    private widget.ComboBox cmbPenyakit;
    private widget.ComboBox cmbRiwayat;
    private widget.ComboBox cmbSesak;
    private widget.ComboBox cmbSuaraKanan;
    private widget.ComboBox cmbSuaraKiri;
    private widget.ComboBox cmbTekanan;
    private widget.ComboBox cmbTrachea;
    private widget.ComboBox cmbUsaha;
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
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
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
    private widget.Label jLabel6;
    private widget.Label jLabel63;
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
    private widget.Label label104;
    private widget.Label label106;
    private widget.Label label107;
    private widget.Label label108;
    private widget.Label label109;
    private widget.Label label19;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbAsesmen;
    private widget.Table tbCPPT;
    private widget.Table tbRiwayat;
    // End of variables declaration//GEN-END:variables

     private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT am.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(am.tgl_asesmen,'%d/%m/%Y') tgl, "
                    + "TIME_FORMAT(am.jam_asesmen,'%H:%i') jam, pg.nama nmdpjp FROM asesmen_medik_bedah_ranap am "
                    + "inner join reg_periksa rp on rp.no_rawat=am.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=am.nip_dpjp WHERE "
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
                        rs.getString("tgl"),
                        rs.getString("jam"),
                        rs.getString("nmdpjp"),
                        rs.getString("ruang_rawat"),
                        rs.getString("riwayat"),
                        rs.getString("riw_alergi"),
                        rs.getString("riw_medikasi"),
                        rs.getString("riw_penyakit_lain"),
                        rs.getString("makanan_terakhir"),
                        rs.getString("cek_pengaruh_napza"),
                        rs.getString("cek_pengaruh_alkohol"),
                        rs.getString("jenis_napza"),
                        rs.getString("jenis_alkohol"),
                        rs.getString("kejadian_lain"),
                        rs.getString("pejalan_kaki"),
                        rs.getString("sepeda_gayung"),
                        rs.getString("sepeda_motor"),
                        rs.getString("mobil"),
                        rs.getString("ket_pejalan_kaki"),
                        rs.getString("ket_sepeda_gayung"),
                        rs.getString("ket_sepeda_motor"),
                        rs.getString("ket_mobil"),
                        rs.getString("jatuh"),
                        rs.getString("ket_jatuh"),
                        rs.getString("pohon"),
                        rs.getString("gedung"),
                        rs.getString("lainnya_jatuh"),
                        rs.getString("ket_lain_jatuh"),
                        rs.getString("luka_tembak"),
                        rs.getString("luka_tusuk"),
                        rs.getString("luka_hancur"),
                        rs.getString("luka_bakar"),
                        rs.getString("lainnya_luka"),
                        rs.getString("ket_lain_luka"),
                        rs.getString("pelindung_leher"),
                        rs.getString("alat_bantu_nafas"),
                        rs.getString("infus"),
                        rs.getString("pengobatan"),
                        rs.getString("ket_pelindung_leher"),
                        rs.getString("ket_alat_bantu_nafas"),
                        rs.getString("ket_infus"),
                        rs.getString("ket_pengobatan"),
                        rs.getString("lain_tindakan"),
                        rs.getString("airway"),
                        rs.getString("trachea"),
                        rs.getString("airway_resusitasi"),
                        rs.getString("airway_reevaluasi"),
                        rs.getString("dada_simetris"),
                        rs.getString("sesak_nafas"),
                        rs.getString("respirasi"),
                        rs.getString("krepitasi"),
                        rs.getString("suara_kanan"),
                        rs.getString("kanan_jelas"),
                        rs.getString("kanan_ronchi"),
                        rs.getString("kanan_menurun"),
                        rs.getString("kanan_wheezing"),
                        rs.getString("suara_kiri"),
                        rs.getString("kiri_jelas"),
                        rs.getString("kiri_ronchi"),
                        rs.getString("kiri_menurun"),
                        rs.getString("kiri_wheezing"),
                        rs.getString("saturasi"),
                        rs.getString("suhu_ruangan"),
                        rs.getString("nrm"),
                        rs.getString("nazal_canule"),
                        rs.getString("lain_pada"),
                        rs.getString("ket_lain_pada"),
                        rs.getString("breathing_asesmen"),
                        rs.getString("breathing_resusitasi"),
                        rs.getString("breathing_reevaluasi"),
                        rs.getString("tensi"),
                        rs.getString("nadi"),
                        rs.getString("kuat"),
                        rs.getString("lemah"),
                        rs.getString("reguler"),
                        rs.getString("ireguler"),
                        rs.getString("suhu_axila"),
                        rs.getString("suhu_rectal"),
                        rs.getString("temp_hangat"),
                        rs.getString("temp_panas"),
                        rs.getString("temp_dingin"),
                        rs.getString("kulit_normal"),
                        rs.getString("kulit_kering"),
                        rs.getString("kulit_lembab_basah"),
                        rs.getString("circulation_asesmen"),
                        rs.getString("circulation_resusitasi"),
                        rs.getString("circulation_reevaluasi"),
                        rs.getString("frekuensi_pernafasan"),
                        rs.getString("usaha_bernafas"),
                        rs.getString("tekanan_darah"),
                        rs.getString("pengisian_kapiler"),
                        rs.getString("gcs"),
                        rs.getString("alert"),
                        rs.getString("verbal"),
                        rs.getString("pain"),
                        rs.getString("unresponsive"),
                        rs.getString("exposure"),
                        rs.getString("pupil_kanan"),
                        rs.getString("pupil_kiri"),
                        rs.getString("kanan_cepat"),
                        rs.getString("kanan_konstriksi"),
                        rs.getString("kanan_lambat"),
                        rs.getString("kanan_dilatasi"),
                        rs.getString("kanan_tak_bereaksi"),
                        rs.getString("kiri_cepat"),
                        rs.getString("kiri_konstriksi"),
                        rs.getString("kiri_lambat"),
                        rs.getString("kiri_dilatasi"),
                        rs.getString("kiri_tak_bereaksi"),
                        rs.getString("laserasi"),
                        rs.getString("abrasi"),
                        rs.getString("hematoma"),
                        rs.getString("kontusio"),
                        rs.getString("dislokasi"),
                        rs.getString("luka_dingin"),
                        rs.getString("lokasi_luka_tembak"),
                        rs.getString("lokasi_luka_tusuk"),
                        rs.getString("lokasi_luka_bakar"),
                        rs.getString("edema"),
                        rs.getString("amputasi"),
                        rs.getString("avulse"),
                        rs.getString("nyeri"),
                        rs.getString("fraktur_terbuka"),
                        rs.getString("fraktur_tertutup"),
                        rs.getString("lokasi_lain"),
                        rs.getString("ket_lokasi_lain"),
                        rs.getString("deskripsi_anamnesis"),
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
        cmbRiwayat.setSelectedIndex(0);
        cmbAlergi.setSelectedIndex(0);
        cmbMedikasi.setSelectedIndex(0);
        cmbPenyakit.setSelectedIndex(0);
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        chkNapza.setSelected(false);
        TNapza.setText("");
        TNapza.setEnabled(false);
        chkAlkohol.setSelected(false);
        TAlkohol.setText("");
        TAlkohol.setEnabled(false);
        TKejadian.setText("");
        chkPejalan.setSelected(false);
        chkSepedaGayung.setSelected(false);
        chkSepedaMotor.setSelected(false);
        chkMobil.setSelected(false);
        TPejalan.setText("");
        TSepedaGayung.setText("");
        TSepedaMotor.setText("");
        TMobil.setText("");
        TPejalan.setEnabled(false);
        TSepedaGayung.setEnabled(false);
        TSepedaMotor.setEnabled(false);
        TMobil.setEnabled(false);
        chkJatuh.setSelected(false);
        Tjatuh.setText("");
        Tjatuh.setEnabled(false);
        chkPohon.setSelected(false);
        chkGedung.setSelected(false);
        chkLainJatuh.setSelected(false);
        chkPohon.setEnabled(false);
        chkGedung.setEnabled(false);
        chkLainJatuh.setEnabled(false);
        TLainJatuh.setText("");
        TLainJatuh.setEnabled(false);
        chkLukaTembak.setSelected(false);
        chkLukaTusuk.setSelected(false);
        chkLukaHancur.setSelected(false);
        chkLukaBakar.setSelected(false);
        chkLainLuka.setSelected(false);
        TLainLuka.setText("");
        TLainLuka.setEnabled(false);
        cmbPelindung.setSelectedIndex(0);
        cmbAlat.setSelectedIndex(0);
        cmbInfus.setSelectedIndex(0);
        cmbPengobatan.setSelectedIndex(0);        
        TPelindung.setText("");
        TAlat.setText("");
        TInfus.setText("");
        TPengobatan.setText("");
        TPelindung.setEnabled(false);
        TAlat.setEnabled(false);
        TInfus.setEnabled(false);
        TPengobatan.setEnabled(false);
        TLainTindakan.setText("");
        cmbAirway.setSelectedIndex(0);
        cmbTrachea.setSelectedIndex(0);
        TresusitasiAirway.setText("");
        TreevaluasiAirway.setText("");
        cmbDada.setSelectedIndex(0);
        cmbSesak.setSelectedIndex(0);
        Trespi.setText("");
        cmbKrepitasi.setSelectedIndex(0);        
        cmbSuaraKanan.setSelectedIndex(0);
        chkJelasKanan.setSelected(false);
        chkMenurunKanan.setSelected(false);
        chkRonchiKanan.setSelected(false);
        chkWezingKanan.setSelected(false);
        chkJelasKanan.setEnabled(false);
        chkMenurunKanan.setEnabled(false);
        chkRonchiKanan.setEnabled(false);
        chkWezingKanan.setEnabled(false);
        cmbSuaraKiri.setSelectedIndex(0);
        chkJelasKiri.setSelected(false);
        chkMenurunKiri.setSelected(false);
        chkRonchiKiri.setSelected(false);
        chkWezingKiri.setSelected(false);
        chkJelasKiri.setEnabled(false);
        chkMenurunKiri.setEnabled(false);
        chkRonchiKiri.setEnabled(false);
        chkWezingKiri.setEnabled(false);
        Tsaturasi.setText("");
        chkSuhuRuangan.setSelected(false);
        chkNasal.setSelected(false);
        chkNRM.setSelected(false);
        chkLainPada.setSelected(false);
        TLainPada.setText("");
        TLainPada.setEnabled(false);
        TasesmenBreating.setText("");
        TresusBreating.setText("");
        TreevaluasiBreating.setText("");
        Ttensi.setText("");
        Tnadi.setText("");
        chkKuat.setSelected(false);
        chkLemah.setSelected(false);
        chkReguler.setSelected(false);
        chkIreguler.setSelected(false);
        TsuhuAxila.setText("");
        TsuhuRectal.setText("");
        chkHangat.setSelected(false);
        chkPanas.setSelected(false);
        chkDingin.setSelected(false);
        chkNormal.setSelected(false);
        chkKering.setSelected(false);
        chkLembab.setSelected(false);
        TasesmenCircul.setText("");
        TresusCircul.setText("");
        TreevaluasiCircul.setText("");
        cmbFrekuensi.setSelectedIndex(0);
        cmbUsaha.setSelectedIndex(0);
        cmbTekanan.setSelectedIndex(0);
        cmbPengisian.setSelectedIndex(0);
        cmbGCS.setSelectedIndex(0);
        TskorA.setText("0");
        TskorB.setText("0");
        TskorC.setText("0");
        TskorD.setText("0");
        TskorE.setText("0");
        TskorTotal.setText("0");
        chkAlert.setSelected(false);
        chkVerbal.setSelected(false);
        chkPain.setSelected(false);
        chkUnrespon.setSelected(false);
        Texposur.setText("");        
        TpupilKanan.setText("");
        TcepatKanan.setText("");
        TkonstriksiKanan.setText("");
        TlambatKanan.setText("");
        TdilatasiKanan.setText("");
        TtakKanan.setText("");
        TpupilKiri.setText("");
        TcepatKiri.setText("");
        TkonstriksiKiri.setText("");
        TlambatKiri.setText("");
        TdilatasiKiri.setText("");
        TtakKiri.setText("");
        
        chkLaserasi.setSelected(false);
        chkAbrasi.setSelected(false);
        chkHematoma.setSelected(false);
        chkKontusio.setSelected(false);
        chkDislokasi.setSelected(false);
        chkLukaDingin.setSelected(false);
        chkLukaTembak7.setSelected(false);
        chkLukaTusuk8.setSelected(false);
        chkLukaBakar9.setSelected(false);
        chkEdema.setSelected(false);
        chkAmputasi.setSelected(false);
        chkAvulse.setSelected(false);
        chkNyeri.setSelected(false);
        chkFrakturTerbuka.setSelected(false);
        chkFrakturTertutup.setSelected(false);
        chkLainlain.setSelected(false);
        TlainLokasi.setText("");
        TlainLokasi.setEnabled(false);
        Tdeskripsi.setText("");
        TtglAsesmen.setDate(new Date());
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);        
        TnipDpjp.setText("-");
        TnmDpjp.setText("-");
        user = "";
    }

    private void getData() {
        variabelBersih();
        if (tbAsesmen.getSelectedRow() != -1) {
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 6).toString());
            cmbRiwayat.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 7).toString());
            cmbAlergi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 8).toString());
            cmbMedikasi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 9).toString());
            cmbPenyakit.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString());
            cmbJam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(),11).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString().substring(6, 8));
            napza = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString();
            alkohol = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString();
            TNapza.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString());
            TAlkohol.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString());
            TKejadian.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString());
            pejalan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString();
            spdGayung = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString();
            spdMotor = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 19).toString();
            mobil = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString();
            TPejalan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString());
            TSepedaGayung.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString());
            TSepedaMotor.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString());
            TMobil.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString());
            jatuh = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString();
            Tjatuh.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString());
            pohon = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString();
            gedung = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString();
            lainJatuh = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString();
            TLainJatuh.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString());
            lukaTembak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString();
            lukaTusuk = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 32).toString();
            lukaHancur = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 33).toString();
            lukaBakar = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString();
            lainLuka = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString();
            TLainLuka.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString());
            cmbPelindung.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString());
            cmbAlat.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString());
            cmbInfus.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString());
            cmbPengobatan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString());
            TPelindung.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString());
            TAlat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString());
            TInfus.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString());
            TPengobatan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString());
            TLainTindakan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString());
            cmbAirway.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString());
            cmbTrachea.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString());
            TresusitasiAirway.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString());
            TreevaluasiAirway.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString());
            cmbDada.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString());
            cmbSesak.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString());
            Trespi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString());
            cmbKrepitasi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString());
            cmbSuaraKanan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString());
            jelasKanan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString();
            ronciKanan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString();
            menurunKanan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString();
            wezingKanan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString();
            cmbSuaraKiri.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString());
            jelasKiri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString();
            ronciKiri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString();
            menurunKiri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 62).toString();
            wezingKiri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString();
            Tsaturasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 64).toString());
            suhu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString();
            nrm = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 66).toString();
            nasal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString();
            lainSatur = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString();
            TLainPada.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString());
            TasesmenBreating.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString());
            TresusBreating.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString());
            TreevaluasiBreating.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString());
            Ttensi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString());
            Tnadi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString());
            kuat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString();
            lemah = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString();
            reguler = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString();
            ireguler = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString();
            TsuhuAxila.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 79).toString());
            TsuhuRectal.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 80).toString());
            hangat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 81).toString();
            panas = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 82).toString();
            dingin = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 83).toString();
            normal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 84).toString();
            kering = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 85).toString();
            lembab = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 86).toString();
            TasesmenCircul.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 87).toString());
            TresusCircul.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 88).toString());
            TreevaluasiCircul.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 89).toString());
            cmbFrekuensi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 90).toString());
            cmbUsaha.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 91).toString());
            cmbTekanan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 92).toString());
            cmbPengisian.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 93).toString());
            cmbGCS.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString());
            alert = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 95).toString();
            verbal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 96).toString();
            pain = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 97).toString();
            unres = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 98).toString();
            Texposur.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 99).toString());
            TpupilKanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 100).toString());
            TpupilKiri.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString());
            TcepatKanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 102).toString());
            TkonstriksiKanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 103).toString());
            TlambatKanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 104).toString());
            TdilatasiKanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 105).toString());
            TtakKanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 106).toString());
            TcepatKiri.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 107).toString());
            TkonstriksiKiri.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 108).toString());
            TlambatKiri.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 109).toString());
            TdilatasiKiri.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 110).toString());
            TtakKiri.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 111).toString());
            laserasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 112).toString();
            abrasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 113).toString();
            hema = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 114).toString();
            kontu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 115).toString();
            dislok = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 116).toString();
            lukaDingin = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 117).toString();
            lukaTembak7 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 118).toString();
            lukaTusuk8 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 119).toString();
            lukaBakar9 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 120).toString();
            edema = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 121).toString();
            amput = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 122).toString();
            avul = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 123).toString();
            nyeri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 124).toString();
            frTerbuka = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 125).toString();
            frTertutup = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 126).toString();
            lainLokasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 127).toString();
            TlainLokasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 128).toString());
            Tdeskripsi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 129).toString());            
            Valid.SetTgl(TtglAsesmen, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 130).toString());
            cmbJam1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(),131).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 131).toString().substring(3, 5));            
            cmbDtk1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 131).toString().substring(6, 8));
            TnipDpjp.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 132).toString());
            TnmDpjp.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 5).toString());
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
        BtnSimpan.setEnabled(akses.getasesmen_medik_bedah_ranap());
        BtnHapus.setEnabled(akses.getasesmen_medik_bedah_ranap());
        BtnEdit.setEnabled(akses.getasesmen_medik_bedah_ranap());
        BtnPrint.setEnabled(akses.getasesmen_medik_bedah_ranap());
        MnRiwayatData.setEnabled(akses.getadmin());
        
        if (akses.getjml2() >= 1) {
            TnipDpjp.setText(akses.getkode());    
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmDpjp, TnipDpjp.getText());
            if (TnmDpjp.getText().equals("")) {
                TnipDpjp.setText("");
            }
        }        
    }

    public void setTampil(){
       TabRawat.setSelectedIndex(1);
       tampil();
    }
    
    private void ganti() {
        cekData();
        if (Sequel.mengedittf("asesmen_medik_bedah_ranap", "no_rawat=?", "ruang_rawat=?, riwayat=?, riw_alergi=?, riw_medikasi=?, riw_penyakit_lain=?, makanan_terakhir=?, "
                + "cek_pengaruh_napza=?, cek_pengaruh_alkohol=?, jenis_napza=?, jenis_alkohol=?, kejadian_lain=?, pejalan_kaki=?, sepeda_gayung=?, sepeda_motor=?, mobil=?, ket_pejalan_kaki=?, "
                + "ket_sepeda_gayung=?, ket_sepeda_motor=?, ket_mobil=?, jatuh=?, ket_jatuh=?, pohon=?, gedung=?, lainnya_jatuh=?, ket_lain_jatuh=?, luka_tembak=?, luka_tusuk=?, luka_hancur=?, "
                + "luka_bakar=?, lainnya_luka=?, ket_lain_luka=?, pelindung_leher=?, alat_bantu_nafas=?, infus=?, pengobatan=?, ket_pelindung_leher=?, ket_alat_bantu_nafas=?, ket_infus=?, "
                + "ket_pengobatan=?, lain_tindakan=?, airway=?, trachea=?, airway_resusitasi=?, airway_reevaluasi=?, dada_simetris=?, sesak_nafas=?, respirasi=?, krepitasi=?, suara_kanan=?, "
                + "kanan_jelas=?, kanan_ronchi=?, kanan_menurun=?, kanan_wheezing=?, suara_kiri=?, kiri_jelas=?, kiri_ronchi=?, kiri_menurun=?, kiri_wheezing=?, saturasi=?, suhu_ruangan=?, "
                + "nrm=?, nazal_canule=?, lain_pada=?, ket_lain_pada=?, breathing_asesmen=?, breathing_resusitasi=?, breathing_reevaluasi=?, tensi=?, nadi=?, kuat=?, lemah=?, reguler=?, "
                + "ireguler=?, suhu_axila=?, suhu_rectal=?, temp_hangat=?, temp_panas=?, temp_dingin=?, kulit_normal=?, kulit_kering=?, kulit_lembab_basah=?, circulation_asesmen=?, "
                + "circulation_resusitasi=?, circulation_reevaluasi=?, frekuensi_pernafasan=?, usaha_bernafas=?, tekanan_darah=?, pengisian_kapiler=?, gcs=?, alert=?, verbal=?, pain=?, "
                + "unresponsive=?, exposure=?, pupil_kanan=?, pupil_kiri=?, kanan_cepat=?, kanan_konstriksi=?, kanan_lambat=?, kanan_dilatasi=?, kanan_tak_bereaksi=?, kiri_cepat=?, "
                + "kiri_konstriksi=?, kiri_lambat=?, kiri_dilatasi=?, kiri_tak_bereaksi=?, laserasi=?, abrasi=?, hematoma=?, kontusio=?, dislokasi=?, luka_dingin=?, lokasi_luka_tembak=?, "
                + "lokasi_luka_tusuk=?, lokasi_luka_bakar=?, edema=?, amputasi=?, avulse=?, nyeri=?, fraktur_terbuka=?, fraktur_tertutup=?, lokasi_lain=?, ket_lokasi_lain=?, deskripsi_anamnesis=?, "
                + "tgl_asesmen=?, jam_asesmen=?, nip_dpjp=?", 128, new String[]{
                    TrgRawat.getText(), cmbRiwayat.getSelectedItem().toString(), cmbAlergi.getSelectedItem().toString(), cmbMedikasi.getSelectedItem().toString(),
                    cmbPenyakit.getSelectedItem().toString(), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), napza, alkohol,
                    TNapza.getText(), TAlkohol.getText(), TKejadian.getText(), pejalan, spdGayung, spdMotor, mobil, TPejalan.getText(), TSepedaGayung.getText(), TSepedaMotor.getText(),
                    TMobil.getText(), jatuh, Tjatuh.getText(), pohon, gedung, lainJatuh, TLainJatuh.getText(), lukaTembak, lukaTusuk, lukaHancur, lukaBakar, lainLuka, TLainLuka.getText(),
                    cmbPelindung.getSelectedItem().toString(), cmbAlat.getSelectedItem().toString(), cmbInfus.getSelectedItem().toString(), cmbPengobatan.getSelectedItem().toString(),
                    TPelindung.getText(), TAlat.getText(), TInfus.getText(), TPengobatan.getText(), TLainTindakan.getText(), cmbAirway.getSelectedItem().toString(),
                    cmbTrachea.getSelectedItem().toString(), TresusitasiAirway.getText(), TreevaluasiAirway.getText(), cmbDada.getSelectedItem().toString(), cmbSesak.getSelectedItem().toString(),
                    Trespi.getText(), cmbKrepitasi.getSelectedItem().toString(), cmbSuaraKanan.getSelectedItem().toString(), jelasKanan, ronciKanan, menurunKanan, wezingKanan,
                    cmbSuaraKiri.getSelectedItem().toString(), jelasKiri, ronciKiri, menurunKiri, wezingKiri, Tsaturasi.getText(), suhu, nrm, nasal, lainSatur, TLainPada.getText(),
                    TasesmenBreating.getText(), TresusBreating.getText(), TreevaluasiBreating.getText(), Ttensi.getText(), Tnadi.getText(), kuat, lemah, reguler, ireguler,
                    TsuhuAxila.getText(), TsuhuRectal.getText(), hangat, panas, dingin, normal, kering, lembab, TasesmenCircul.getText(), TresusCircul.getText(), TreevaluasiCircul.getText(),
                    cmbFrekuensi.getSelectedItem().toString(), cmbUsaha.getSelectedItem().toString(), cmbTekanan.getSelectedItem().toString(), cmbPengisian.getSelectedItem().toString(),
                    cmbGCS.getSelectedItem().toString(), alert, verbal, pain, unres, Texposur.getText(), TpupilKanan.getText(), TpupilKiri.getText(), TcepatKanan.getText(),
                    TkonstriksiKanan.getText(), TlambatKanan.getText(), TdilatasiKanan.getText(), TtakKanan.getText(), TcepatKiri.getText(), TkonstriksiKiri.getText(), TlambatKiri.getText(),
                    TdilatasiKiri.getText(), TtakKiri.getText(), laserasi, abrasi, hema, kontu, dislok, lukaDingin, lukaTembak7, lukaTusuk8, lukaBakar9, edema, amput, avul, nyeri,
                    frTerbuka, frTertutup, lainLokasi, TlainLokasi.getText(), Tdeskripsi.getText(), Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""),
                    cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), TnipDpjp.getText(),
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                }) == true) {

            Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Assesmen Medik Bedah Rawat Inap", "Ganti");
            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
            TabRawat.setSelectedIndex(1);
        }
    }
    
    private void cekData() {        
        if (chkNapza.isSelected() == true) {
            napza = "ya";
        } else {
            napza = "tidak";
        }
        
        if (chkAlkohol.isSelected() == true) {
            alkohol = "ya";
        } else {
            alkohol = "tidak";
        }
        
        if (chkPejalan.isSelected() == true) {
            pejalan = "ya";
        } else {
            pejalan = "tidak";
        }
        
        if (chkSepedaGayung.isSelected() == true) {
            spdGayung = "ya";
        } else {
            spdGayung = "tidak";
        }
        
        if (chkSepedaMotor.isSelected() == true) {
            spdMotor = "ya";
        } else {
            spdMotor = "tidak";
        }
        
        if (chkMobil.isSelected() == true) {
            mobil = "ya";
        } else {
            mobil = "tidak";
        }        
        
        if (chkJatuh.isSelected() == true) {
            jatuh = "ya";
        } else {
            jatuh = "tidak";
        }
        
        if (chkPohon.isSelected() == true) {
            pohon = "ya";
        } else {
            pohon = "tidak";
        }       
        
        if (chkGedung.isSelected() == true) {
            gedung = "ya";
        } else {
            gedung = "tidak";
        }
        
        if (chkLainJatuh.isSelected() == true) {
            lainJatuh = "ya";
        } else {
            lainJatuh = "tidak";
        }
        
        if (chkLukaTembak.isSelected() == true) {
            lukaTembak = "ya";
        } else {
            lukaTembak = "tidak";
        }        

        if (chkLukaTusuk.isSelected() == true) {
            lukaTusuk = "ya";
        } else {
            lukaTusuk = "tidak";
        }
        
        if (chkLukaHancur.isSelected() == true) {
            lukaHancur = "ya";
        } else {
            lukaHancur = "tidak";
        }
        
        if (chkLukaBakar.isSelected() == true) {
            lukaBakar = "ya";
        } else {
            lukaBakar = "tidak";
        }
        
        if (chkLainLuka.isSelected() == true) {
            lainLuka = "ya";
        } else {
            lainLuka = "tidak";
        }
        
        if (chkJelasKanan.isSelected() == true) {
            jelasKanan = "ya";
        } else {
            jelasKanan = "tidak";
        }
        
        if (chkMenurunKanan.isSelected() == true) {
            menurunKanan = "ya";
        } else {
            menurunKanan = "tidak";
        }
        
        if (chkRonchiKanan.isSelected() == true) {
            ronciKanan = "ya";
        } else {
            ronciKanan = "tidak";
        }
        
        if (chkWezingKanan.isSelected() == true) {
            wezingKanan = "ya";
        } else {
            wezingKanan = "tidak";
        }
        
        if (chkJelasKiri.isSelected() == true) {
            jelasKiri = "ya";
        } else {
            jelasKiri = "tidak";
        }
        
        if (chkMenurunKiri.isSelected() == true) {
            menurunKiri = "ya";
        } else {
            menurunKiri = "tidak";
        }        
        
        if (chkRonchiKiri.isSelected() == true) {
            ronciKiri = "ya";
        } else {
            ronciKiri = "tidak";
        }
        
        if (chkWezingKiri.isSelected() == true) {
            wezingKiri = "ya";
        } else {
            wezingKiri = "tidak";
        }
        
        if (chkSuhuRuangan.isSelected() == true) {
            suhu = "ya";
        } else {
            suhu = "tidak";
        }
        
        if (chkNasal.isSelected() == true) {
            nasal = "ya";
        } else {
            nasal = "tidak";
        }
        
        if (chkNRM.isSelected() == true) {
            nrm = "ya";
        } else {
            nrm = "tidak";
        }
        
        if (chkLainPada.isSelected() == true) {
            lainSatur = "ya";
        } else {
            lainSatur = "tidak";
        }        
        
        if (chkKuat.isSelected() == true) {
            kuat = "ya";
        } else {
            kuat = "tidak";
        }
        
        if (chkLemah.isSelected() == true) {
            lemah = "ya";
        } else {
            lemah = "tidak";
        }
        
        if (chkReguler.isSelected() == true) {
            reguler = "ya";
        } else {
            reguler = "tidak";
        }
        
        if (chkIreguler.isSelected() == true) {
            ireguler = "ya";
        } else {
            ireguler = "tidak";
        }
        
        if (chkHangat.isSelected() == true) {
            hangat = "ya";
        } else {
            hangat = "tidak";
        }
        
        if (chkPanas.isSelected() == true) {
            panas = "ya";
        } else {
            panas = "tidak";
        }
        
        if (chkDingin.isSelected() == true) {
            dingin = "ya";
        } else {
            dingin = "tidak";
        }
        
        if (chkNormal.isSelected() == true) {
            normal = "ya";
        } else {
            normal = "tidak";
        }
        
        if (chkKering.isSelected() == true) {
            kering = "ya";
        } else {
            kering = "tidak";
        }
        
        if (chkLembab.isSelected() == true) {
            lembab = "ya";
        } else {
            lembab = "tidak";
        }
        
        if (chkAlert.isSelected() == true) {
            alert = "ya";
        } else {
            alert = "tidak";
        }
        
        if (chkVerbal.isSelected() == true) {
            verbal = "ya";
        } else {
            verbal = "tidak";
        }
        
        if (chkPain.isSelected() == true) {
            pain = "ya";
        } else {
            pain = "tidak";
        }
        
        if (chkUnrespon.isSelected() == true) {
            unres = "ya";
        } else {
            unres = "tidak";
        }
        
        if (chkLaserasi.isSelected() == true) {
            laserasi = "ya";
        } else {
            laserasi = "tidak";
        }
        
        if (chkAbrasi.isSelected() == true) {
            abrasi = "ya";
        } else {
            abrasi = "tidak";
        }
        
        if (chkHematoma.isSelected() == true) {
            hema = "ya";
        } else {
            hema = "tidak";
        }
        
        if (chkKontusio.isSelected() == true) {
            kontu = "ya";
        } else {
            kontu = "tidak";
        }
        
        if (chkDislokasi.isSelected() == true) {
            dislok = "ya";
        } else {
            dislok = "tidak";
        }
        
        if (chkLukaDingin.isSelected() == true) {
            lukaDingin = "ya";
        } else {
            lukaDingin = "tidak";
        }
        
        if (chkLukaTembak7.isSelected() == true) {
            lukaTembak7 = "ya";
        } else {
            lukaTembak7 = "tidak";
        }
        
        if (chkLukaTusuk8.isSelected() == true) {
            lukaTusuk8 = "ya";
        } else {
            lukaTusuk8 = "tidak";
        }
        
        if (chkLukaBakar9.isSelected() == true) {
            lukaBakar9 = "ya";
        } else {
            lukaBakar9 = "tidak";
        }
        
        if (chkEdema.isSelected() == true) {
            edema = "ya";
        } else {
            edema = "tidak";
        }
        
        if (chkAmputasi.isSelected() == true) {
            amput = "ya";
        } else {
            amput = "tidak";
        }
        
        if (chkAvulse.isSelected() == true) {
            avul = "ya";
        } else {
            avul = "tidak";
        }
        
        if (chkNyeri.isSelected() == true) {
            nyeri = "ya";
        } else {
            nyeri = "tidak";
        }
        
        if (chkFrakturTerbuka.isSelected() == true) {
            frTerbuka = "ya";
        } else {
            frTerbuka = "tidak";
        }
        
        if (chkFrakturTertutup.isSelected() == true) {
            frTertutup = "ya";
        } else {
            frTertutup = "tidak";
        }
        
        if (chkLainlain.isSelected() == true) {
            lainLokasi = "ya";
        } else {
            lainLokasi = "tidak";
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
            if (Sequel.queryu2tf("delete from asesmen_medik_bedah_ranap where no_rawat=?", 1, new String[]{
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
        if (napza.equals("ya")) {
            chkNapza.setSelected(true);
            TNapza.setEnabled(true);
        } else {
            chkNapza.setSelected(false);
            TNapza.setEnabled(false);
        }
        
        if (alkohol.equals("ya")) {
            chkAlkohol.setSelected(true);
            TAlkohol.setEnabled(true);
        } else {
            chkAlkohol.setSelected(false);
            TAlkohol.setEnabled(false);
        }
        
        if (pejalan.equals("ya")) {
            chkPejalan.setSelected(true);
            TPejalan.setEnabled(true);
        } else {
            chkPejalan.setSelected(false);
            TPejalan.setEnabled(false);
        }
        
        if (spdGayung.equals("ya")) {
            chkSepedaGayung.setSelected(true);
            TSepedaGayung.setEnabled(true);
        } else {
            chkSepedaGayung.setSelected(false);
            TSepedaGayung.setEnabled(false);
        }
        
        if (spdMotor.equals("ya")) {
            chkSepedaMotor.setSelected(true);
            TSepedaMotor.setEnabled(true);
        } else {
            chkSepedaMotor.setSelected(false);
            TSepedaMotor.setEnabled(false);
        }
        
        if (mobil.equals("ya")) {
            chkMobil.setSelected(true);
            TMobil.setEnabled(true);
        } else {
            chkMobil.setSelected(false);
            TMobil.setEnabled(false);
        }        
        
        if (jatuh.equals("ya")) {
            chkJatuh.setSelected(true);
            Tjatuh.setEnabled(true);
            chkPohon.setEnabled(true);
            chkGedung.setEnabled(true);
            chkLainJatuh.setEnabled(true);
            
            if (pohon.equals("ya")) {
                chkPohon.setSelected(true);
            } else {
                chkPohon.setSelected(false);
            }
            
            if (gedung.equals("ya")) {
                chkGedung.setSelected(true);
            } else {
                chkGedung.setSelected(false);
            }
            
            if (lainJatuh.equals("ya")) {
                chkLainJatuh.setSelected(true);
                TLainJatuh.setEnabled(true);
            } else {
                chkLainJatuh.setSelected(false);
                TLainJatuh.setEnabled(false);
            }
        } else {
            chkJatuh.setSelected(false);
            chkPohon.setSelected(false);
            chkGedung.setSelected(false);
            chkLainJatuh.setSelected(false);
            
            Tjatuh.setEnabled(false);
            chkPohon.setEnabled(false);
            chkGedung.setEnabled(false);
            chkLainJatuh.setEnabled(false);
            TLainJatuh.setEnabled(false);
        }
        
        if (lukaTembak.equals("ya")) {
            chkLukaTembak.setSelected(true);
        } else {
            chkLukaTembak.setSelected(false);
        }
        
        if (lukaTusuk.equals("ya")) {
            chkLukaTusuk.setSelected(true);
        } else {
            chkLukaTusuk.setSelected(false);
        }        
        
        if (lukaHancur.equals("ya")) {
            chkLukaHancur.setSelected(true);
        } else {
            chkLukaHancur.setSelected(false);
        }
        
        if (lukaBakar.equals("ya")) {
            chkLukaBakar.setSelected(true);
        } else {
            chkLukaBakar.setSelected(false);
        }
        
        if (lainLuka.equals("ya")) {
            chkLainLuka.setSelected(true);
            TLainLuka.setEnabled(true);
        } else {
            chkLainLuka.setSelected(false);
            TLainLuka.setEnabled(false);
        }
        
        if (cmbPelindung.getSelectedIndex() == 1) {
            TPelindung.setEnabled(true);
        } else {
            TPelindung.setEnabled(false);
        }
        
        if (cmbAlat.getSelectedIndex() == 1) {
            TAlat.setEnabled(true);
        } else {
            TAlat.setEnabled(false);
        }
        
        if (cmbInfus.getSelectedIndex() == 1) {
            TInfus.setEnabled(true);
        } else {
            TInfus.setEnabled(false);
        }
        
        if (cmbPengobatan.getSelectedIndex() == 1) {
            TPengobatan.setEnabled(true);
        } else {
            TPengobatan.setEnabled(false);
        }
        
        if (cmbSuaraKanan.getSelectedIndex() == 1) {
            chkJelasKanan.setEnabled(true);
            chkMenurunKanan.setEnabled(true);
            chkRonchiKanan.setEnabled(true);
            chkWezingKanan.setEnabled(true);
            
            if (jelasKanan.equals("ya")) {
                chkJelasKanan.setSelected(true);
            } else {
                chkJelasKanan.setSelected(false);
            }
            
            if (menurunKanan.equals("ya")) {
                chkMenurunKanan.setSelected(true);
            } else {
                chkMenurunKanan.setSelected(false);
            }
            
            if (ronciKanan.equals("ya")) {
                chkRonchiKanan.setSelected(true);
            } else {
                chkRonchiKanan.setSelected(false);
            }
            
            if (wezingKanan.equals("ya")) {
                chkWezingKanan.setSelected(true);
            } else {
                chkWezingKanan.setSelected(false);
            }
        } else {
            chkJelasKanan.setEnabled(false);
            chkMenurunKanan.setEnabled(false);
            chkRonchiKanan.setEnabled(false);
            chkWezingKanan.setEnabled(false);
            
            chkJelasKanan.setSelected(false);
            chkMenurunKanan.setSelected(false);
            chkRonchiKanan.setSelected(false);
            chkWezingKanan.setSelected(false);
        }
        
        if (cmbSuaraKiri.getSelectedIndex() == 1) {
            chkJelasKiri.setEnabled(true);
            chkMenurunKiri.setEnabled(true);
            chkRonchiKiri.setEnabled(true);
            chkWezingKiri.setEnabled(true);
            
            if (jelasKiri.equals("ya")) {
                chkJelasKiri.setSelected(true);
            } else {
                chkJelasKiri.setSelected(false);
            }
            
            if (menurunKiri.equals("ya")) {
                chkMenurunKiri.setSelected(true);
            } else {
                chkMenurunKiri.setSelected(false);
            }
            
            if (ronciKiri.equals("ya")) {
                chkRonchiKiri.setSelected(true);
            } else {
                chkRonchiKiri.setSelected(false);
            }
            
            if (wezingKiri.equals("ya")) {
                chkWezingKiri.setSelected(true);
            } else {
                chkWezingKiri.setSelected(false);
            }
        } else {
            chkJelasKiri.setEnabled(false);
            chkMenurunKiri.setEnabled(false);
            chkRonchiKiri.setEnabled(false);
            chkWezingKiri.setEnabled(false);
            
            chkJelasKiri.setSelected(false);
            chkMenurunKiri.setSelected(false);
            chkRonchiKiri.setSelected(false);
            chkWezingKiri.setSelected(false);
        }
        
        if (suhu.equals("ya")) {
            chkSuhuRuangan.setSelected(true);
        } else {
            chkSuhuRuangan.setSelected(false);
        }
        
        if (nasal.equals("ya")) {
            chkNasal.setSelected(true);
        } else {
            chkNasal.setSelected(false);
        }
        
        if (nrm.equals("ya")) {
            chkNRM.setSelected(true);
        } else {
            chkNRM.setSelected(false);
        }
        
        if (lainSatur.equals("ya")) {
            chkLainPada.setSelected(true);
            TLainPada.setEnabled(true);
        } else {
            chkLainPada.setSelected(false);
            TLainPada.setEnabled(false);
        }        
        
        if (kuat.equals("ya")) {
            chkKuat.setSelected(true);
        } else {
            chkKuat.setSelected(false);
        }
        
        if (lemah.equals("ya")) {
            chkLemah.setSelected(true);
        } else {
            chkLemah.setSelected(false);
        }
        
        if (reguler.equals("ya")) {
            chkReguler.setSelected(true);
        } else {
            chkReguler.setSelected(false);
        }
        
        if (ireguler.equals("ya")) {
            chkIreguler.setSelected(true);
        } else {
            chkIreguler.setSelected(false);
        }
        
        if (hangat.equals("ya")) {
            chkHangat.setSelected(true);
        } else {
            chkHangat.setSelected(false);
        }
        
        if (panas.equals("ya")) {
            chkPanas.setSelected(true);
        } else {
            chkPanas.setSelected(false);
        }
        
        if (dingin.equals("ya")) {
            chkDingin.setSelected(true);
        } else {
            chkDingin.setSelected(false);
        }
        
        if (normal.equals("ya")) {
            chkNormal.setSelected(true);
        } else {
            chkNormal.setSelected(false);
        }
        
        if (kering.equals("ya")) {
            chkKering.setSelected(true);
        } else {
            chkKering.setSelected(false);
        }
        
        if (lembab.equals("ya")) {
            chkLembab.setSelected(true);
        } else {
            chkLembab.setSelected(false);
        }
        
        if (cmbFrekuensi.getSelectedIndex() == 0 || cmbFrekuensi.getSelectedIndex() == 1) {
            TskorA.setText("0");
        } else if (cmbFrekuensi.getSelectedIndex() == 2) {
            TskorA.setText("1");
        } else if (cmbFrekuensi.getSelectedIndex() == 3) {
            TskorA.setText("2");
        } else if (cmbFrekuensi.getSelectedIndex() == 4) {
            TskorA.setText("3");
        } else if (cmbFrekuensi.getSelectedIndex() == 5) {
            TskorA.setText("4");
        }
        
        if (cmbUsaha.getSelectedIndex() == 0 || cmbUsaha.getSelectedIndex() == 2) {
            TskorB.setText("0");
        } else if (cmbUsaha.getSelectedIndex() == 1) {
            TskorB.setText("1");
        }
        
        if (cmbTekanan.getSelectedIndex() == 0 || cmbTekanan.getSelectedIndex() == 1) {
            TskorC.setText("0");
        } else if (cmbTekanan.getSelectedIndex() == 2) {
            TskorC.setText("1");
        } else if (cmbTekanan.getSelectedIndex() == 3) {
            TskorC.setText("2");
        } else if (cmbTekanan.getSelectedIndex() == 4) {
            TskorC.setText("3");
        } else if (cmbTekanan.getSelectedIndex() == 5) {
            TskorC.setText("4");
        }
        
        if (cmbPengisian.getSelectedIndex() == 0 || cmbPengisian.getSelectedIndex() == 1) {
            TskorD.setText("0");
        } else if (cmbPengisian.getSelectedIndex() == 2) {
            TskorD.setText("1");
        } else if (cmbPengisian.getSelectedIndex() == 3) {
            TskorD.setText("2");
        }
        
        if (cmbGCS.getSelectedIndex() == 0) {
            TskorE.setText("0");
        } else if (cmbGCS.getSelectedIndex() == 1) {
            TskorE.setText("1");
        } else if (cmbGCS.getSelectedIndex() == 2) {
            TskorE.setText("2");
        } else if (cmbGCS.getSelectedIndex() == 3) {
            TskorE.setText("3");
        } else if (cmbGCS.getSelectedIndex() == 4) {
            TskorE.setText("4");
        } else if (cmbGCS.getSelectedIndex() == 5) {
            TskorE.setText("5");
        }        
        hitungSkor();
        
        if (alert.equals("ya")) {
            chkAlert.setSelected(true);
        } else {
            chkAlert.setSelected(false);
        }
        
        if (verbal.equals("ya")) {
            chkVerbal.setSelected(true);
        } else {
            chkVerbal.setSelected(false);
        }
        
        if (pain.equals("ya")) {
            chkPain.setSelected(true);
        } else {
            chkPain.setSelected(false);
        }
        
        if (unres.equals("ya")) {
            chkUnrespon.setSelected(true);
        } else {
            chkUnrespon.setSelected(false);
        }
        
        if (laserasi.equals("ya")) {
            chkLaserasi.setSelected(true);
        } else {
            chkLaserasi.setSelected(false);
        }
        
        if (abrasi.equals("ya")) {
            chkAbrasi.setSelected(true);
        } else {
            chkAbrasi.setSelected(false);
        }
        
        if (hema.equals("ya")) {
            chkHematoma.setSelected(true);
        } else {
            chkHematoma.setSelected(false);
        }
        
        if (kontu.equals("ya")) {
            chkKontusio.setSelected(true);
        } else {
            chkKontusio.setSelected(false);
        }
        
        if (dislok.equals("ya")) {
            chkDislokasi.setSelected(true);
        } else {
            chkDislokasi.setSelected(false);
        }
        
        if (lukaDingin.equals("ya")) {
            chkLukaDingin.setSelected(true);
        } else {
            chkLukaDingin.setSelected(false);
        }
        
        if (lukaTembak7.equals("ya")) {
            chkLukaTembak7.setSelected(true);
        } else {
            chkLukaTembak7.setSelected(false);
        }
        
        if (lukaTusuk8.equals("ya")) {
            chkLukaTusuk8.setSelected(true);
        } else {
            chkLukaTusuk8.setSelected(false);
        }
        
        if (lukaBakar9.equals("ya")) {
            chkLukaBakar9.setSelected(true);
        } else {
            chkLukaBakar9.setSelected(false);
        }
        
        if (edema.equals("ya")) {
            chkEdema.setSelected(true);
        } else {
            chkEdema.setSelected(false);
        }
        
        if (amput.equals("ya")) {
            chkAmputasi.setSelected(true);
        } else {
            chkAmputasi.setSelected(false);
        }
        
        if (avul.equals("ya")) {
            chkAvulse.setSelected(true);
        } else {
            chkAvulse.setSelected(false);
        }
        
        if (nyeri.equals("ya")) {
            chkNyeri.setSelected(true);
        } else {
            chkNyeri.setSelected(false);
        }
        
        if (frTerbuka.equals("ya")) {
            chkFrakturTerbuka.setSelected(true);
        } else {
            chkFrakturTerbuka.setSelected(false);
        }
        
        if (frTertutup.equals("ya")) {
            chkFrakturTertutup.setSelected(true);
        } else {
            chkFrakturTertutup.setSelected(false);
        }
        
        if (lainLokasi.equals("ya")) {
            chkLainlain.setSelected(true);
            TlainLokasi.setEnabled(true);
        } else {
            chkLainlain.setSelected(false);
            TlainLokasi.setEnabled(false);
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
        if (Sequel.menyimpantf("asesmen_medik_bedah_ranap_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
                + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 132, new String[]{
                    TNoRw.getText(), TrgRawat.getText(), cmbRiwayat.getSelectedItem().toString(), cmbAlergi.getSelectedItem().toString(), cmbMedikasi.getSelectedItem().toString(),
                    cmbPenyakit.getSelectedItem().toString(), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), napza, alkohol,
                    TNapza.getText(), TAlkohol.getText(), TKejadian.getText(), pejalan, spdGayung, spdMotor, mobil, TPejalan.getText(), TSepedaGayung.getText(), TSepedaMotor.getText(),
                    TMobil.getText(), jatuh, Tjatuh.getText(), pohon, gedung, lainJatuh, TLainJatuh.getText(), lukaTembak, lukaTusuk, lukaHancur, lukaBakar, lainLuka, TLainLuka.getText(),
                    cmbPelindung.getSelectedItem().toString(), cmbAlat.getSelectedItem().toString(), cmbInfus.getSelectedItem().toString(), cmbPengobatan.getSelectedItem().toString(),
                    TPelindung.getText(), TAlat.getText(), TInfus.getText(), TPengobatan.getText(), TLainTindakan.getText(), cmbAirway.getSelectedItem().toString(),
                    cmbTrachea.getSelectedItem().toString(), TresusitasiAirway.getText(), TreevaluasiAirway.getText(), cmbDada.getSelectedItem().toString(), cmbSesak.getSelectedItem().toString(),
                    Trespi.getText(), cmbKrepitasi.getSelectedItem().toString(), cmbSuaraKanan.getSelectedItem().toString(), jelasKanan, ronciKanan, menurunKanan, wezingKanan,
                    cmbSuaraKiri.getSelectedItem().toString(), jelasKiri, ronciKiri, menurunKiri, wezingKiri, Tsaturasi.getText(), suhu, nrm, nasal, lainSatur, TLainPada.getText(),
                    TasesmenBreating.getText(), TresusBreating.getText(), TreevaluasiBreating.getText(), Ttensi.getText(), Tnadi.getText(), kuat, lemah, reguler, ireguler,
                    TsuhuAxila.getText(), TsuhuRectal.getText(), hangat, panas, dingin, normal, kering, lembab, TasesmenCircul.getText(), TresusCircul.getText(), TreevaluasiCircul.getText(),
                    cmbFrekuensi.getSelectedItem().toString(), cmbUsaha.getSelectedItem().toString(), cmbTekanan.getSelectedItem().toString(), cmbPengisian.getSelectedItem().toString(),
                    cmbGCS.getSelectedItem().toString(), alert, verbal, pain, unres, Texposur.getText(), TpupilKanan.getText(), TpupilKiri.getText(), TcepatKanan.getText(),
                    TkonstriksiKanan.getText(), TlambatKanan.getText(), TdilatasiKanan.getText(), TtakKanan.getText(), TcepatKiri.getText(), TkonstriksiKiri.getText(), TlambatKiri.getText(),
                    TdilatasiKiri.getText(), TtakKiri.getText(), laserasi, abrasi, hema, kontu, dislok, lukaDingin, lukaTembak7, lukaTusuk8, lukaBakar9, edema, amput, avul, nyeri,
                    frTerbuka, frTertutup, lainLokasi, TlainLokasi.getText(), Tdeskripsi.getText(), Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""),
                    cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), TnipDpjp.getText(), tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 133).toString(),
                    "hapus", user, Sequel.cariIsi("select now()")
                }) == true) {
            System.out.println("Asesmen Medik Bedah Dihapus Berhasil Tersimpan Sebagai Data Histori..!!");
        }
    }
    
    private void gantiDisimpan() {
        cekData();
        if (Sequel.menyimpantf("asesmen_medik_bedah_ranap_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
                + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 132, new String[]{
                    TNoRw.getText(), TrgRawat.getText(), cmbRiwayat.getSelectedItem().toString(), cmbAlergi.getSelectedItem().toString(), cmbMedikasi.getSelectedItem().toString(),
                    cmbPenyakit.getSelectedItem().toString(), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), napza, alkohol,
                    TNapza.getText(), TAlkohol.getText(), TKejadian.getText(), pejalan, spdGayung, spdMotor, mobil, TPejalan.getText(), TSepedaGayung.getText(), TSepedaMotor.getText(),
                    TMobil.getText(), jatuh, Tjatuh.getText(), pohon, gedung, lainJatuh, TLainJatuh.getText(), lukaTembak, lukaTusuk, lukaHancur, lukaBakar, lainLuka, TLainLuka.getText(),
                    cmbPelindung.getSelectedItem().toString(), cmbAlat.getSelectedItem().toString(), cmbInfus.getSelectedItem().toString(), cmbPengobatan.getSelectedItem().toString(),
                    TPelindung.getText(), TAlat.getText(), TInfus.getText(), TPengobatan.getText(), TLainTindakan.getText(), cmbAirway.getSelectedItem().toString(),
                    cmbTrachea.getSelectedItem().toString(), TresusitasiAirway.getText(), TreevaluasiAirway.getText(), cmbDada.getSelectedItem().toString(), cmbSesak.getSelectedItem().toString(),
                    Trespi.getText(), cmbKrepitasi.getSelectedItem().toString(), cmbSuaraKanan.getSelectedItem().toString(), jelasKanan, ronciKanan, menurunKanan, wezingKanan,
                    cmbSuaraKiri.getSelectedItem().toString(), jelasKiri, ronciKiri, menurunKiri, wezingKiri, Tsaturasi.getText(), suhu, nrm, nasal, lainSatur, TLainPada.getText(),
                    TasesmenBreating.getText(), TresusBreating.getText(), TreevaluasiBreating.getText(), Ttensi.getText(), Tnadi.getText(), kuat, lemah, reguler, ireguler,
                    TsuhuAxila.getText(), TsuhuRectal.getText(), hangat, panas, dingin, normal, kering, lembab, TasesmenCircul.getText(), TresusCircul.getText(), TreevaluasiCircul.getText(),
                    cmbFrekuensi.getSelectedItem().toString(), cmbUsaha.getSelectedItem().toString(), cmbTekanan.getSelectedItem().toString(), cmbPengisian.getSelectedItem().toString(),
                    cmbGCS.getSelectedItem().toString(), alert, verbal, pain, unres, Texposur.getText(), TpupilKanan.getText(), TpupilKiri.getText(), TcepatKanan.getText(),
                    TkonstriksiKanan.getText(), TlambatKanan.getText(), TdilatasiKanan.getText(), TtakKanan.getText(), TcepatKiri.getText(), TkonstriksiKiri.getText(), TlambatKiri.getText(),
                    TdilatasiKiri.getText(), TtakKiri.getText(), laserasi, abrasi, hema, kontu, dislok, lukaDingin, lukaTembak7, lukaTusuk8, lukaBakar9, edema, amput, avul, nyeri,
                    frTerbuka, frTertutup, lainLokasi, TlainLokasi.getText(), Tdeskripsi.getText(), Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""),
                    cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), TnipDpjp.getText(), tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 133).toString(),
                    "ganti", user, Sequel.cariIsi("select now()")
                }) == true) {
            System.out.println("Asesmen Medik Bedah Diganti Berhasil Tersimpan Sebagai Data Histori..!!");
        }
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode1);
        try {
            psrestor = koneksi.prepareStatement("SELECT IF(pg.nama='-','Admin Utama',pg.nama) pelaku, a.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                    + "a.tgl_asesmen, a.waktu_eksekusi, upper(concat('DI',a.status_data)) sttsdata FROM asesmen_medik_bedah_ranap_histori a "
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
            ps2 = koneksi.prepareStatement("select * from asesmen_medik_bedah_ranap_histori where "
                    + "waktu_eksekusi='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString() + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    try {
                        if (Sequel.menyimpantf("asesmen_medik_bedah_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 129, new String[]{
                                    rs2.getString("no_rawat"),
                                    rs2.getString("ruang_rawat"),
                                    rs2.getString("riwayat"),
                                    rs2.getString("riw_alergi"),
                                    rs2.getString("riw_medikasi"),
                                    rs2.getString("riw_penyakit_lain"),
                                    rs2.getString("makanan_terakhir"),
                                    rs2.getString("cek_pengaruh_napza"),
                                    rs2.getString("cek_pengaruh_alkohol"),
                                    rs2.getString("jenis_napza"),
                                    rs2.getString("jenis_alkohol"),
                                    rs2.getString("kejadian_lain"),
                                    rs2.getString("pejalan_kaki"),
                                    rs2.getString("sepeda_gayung"),
                                    rs2.getString("sepeda_motor"),
                                    rs2.getString("mobil"),
                                    rs2.getString("ket_pejalan_kaki"),
                                    rs2.getString("ket_sepeda_gayung"),
                                    rs2.getString("ket_sepeda_motor"),
                                    rs2.getString("ket_mobil"),
                                    rs2.getString("jatuh"),
                                    rs2.getString("ket_jatuh"),
                                    rs2.getString("pohon"),
                                    rs2.getString("gedung"),
                                    rs2.getString("lainnya_jatuh"),
                                    rs2.getString("ket_lain_jatuh"),
                                    rs2.getString("luka_tembak"),
                                    rs2.getString("luka_tusuk"),
                                    rs2.getString("luka_hancur"),
                                    rs2.getString("luka_bakar"),
                                    rs2.getString("lainnya_luka"),
                                    rs2.getString("ket_lain_luka"),
                                    rs2.getString("pelindung_leher"),
                                    rs2.getString("alat_bantu_nafas"),
                                    rs2.getString("infus"),
                                    rs2.getString("pengobatan"),
                                    rs2.getString("ket_pelindung_leher"),
                                    rs2.getString("ket_alat_bantu_nafas"),
                                    rs2.getString("ket_infus"),
                                    rs2.getString("ket_pengobatan"),
                                    rs2.getString("lain_tindakan"),
                                    rs2.getString("airway"),
                                    rs2.getString("trachea"),
                                    rs2.getString("airway_resusitasi"),
                                    rs2.getString("airway_reevaluasi"),
                                    rs2.getString("dada_simetris"),
                                    rs2.getString("sesak_nafas"),
                                    rs2.getString("respirasi"),
                                    rs2.getString("krepitasi"),
                                    rs2.getString("suara_kanan"),
                                    rs2.getString("kanan_jelas"),
                                    rs2.getString("kanan_ronchi"),
                                    rs2.getString("kanan_menurun"),
                                    rs2.getString("kanan_wheezing"),
                                    rs2.getString("suara_kiri"),
                                    rs2.getString("kiri_jelas"),
                                    rs2.getString("kiri_ronchi"),
                                    rs2.getString("kiri_menurun"),
                                    rs2.getString("kiri_wheezing"),
                                    rs2.getString("saturasi"),
                                    rs2.getString("suhu_ruangan"),
                                    rs2.getString("nrm"),
                                    rs2.getString("nazal_canule"),
                                    rs2.getString("lain_pada"),
                                    rs2.getString("ket_lain_pada"),
                                    rs2.getString("breathing_asesmen"),
                                    rs2.getString("breathing_resusitasi"),
                                    rs2.getString("breathing_reevaluasi"),
                                    rs2.getString("tensi"),
                                    rs2.getString("nadi"),
                                    rs2.getString("kuat"),
                                    rs2.getString("lemah"),
                                    rs2.getString("reguler"),
                                    rs2.getString("ireguler"),
                                    rs2.getString("suhu_axila"),
                                    rs2.getString("suhu_rectal"),
                                    rs2.getString("temp_hangat"),
                                    rs2.getString("temp_panas"),
                                    rs2.getString("temp_dingin"),
                                    rs2.getString("kulit_normal"),
                                    rs2.getString("kulit_kering"),
                                    rs2.getString("kulit_lembab_basah"),
                                    rs2.getString("circulation_asesmen"),
                                    rs2.getString("circulation_resusitasi"),
                                    rs2.getString("circulation_reevaluasi"),
                                    rs2.getString("frekuensi_pernafasan"),
                                    rs2.getString("usaha_bernafas"),
                                    rs2.getString("tekanan_darah"),
                                    rs2.getString("pengisian_kapiler"),
                                    rs2.getString("gcs"),
                                    rs2.getString("alert"),
                                    rs2.getString("verbal"),
                                    rs2.getString("pain"),
                                    rs2.getString("unresponsive"),
                                    rs2.getString("exposure"),
                                    rs2.getString("pupil_kanan"),
                                    rs2.getString("pupil_kiri"),
                                    rs2.getString("kanan_cepat"),
                                    rs2.getString("kanan_konstriksi"),
                                    rs2.getString("kanan_lambat"),
                                    rs2.getString("kanan_dilatasi"),
                                    rs2.getString("kanan_tak_bereaksi"),
                                    rs2.getString("kiri_cepat"),
                                    rs2.getString("kiri_konstriksi"),
                                    rs2.getString("kiri_lambat"),
                                    rs2.getString("kiri_dilatasi"),
                                    rs2.getString("kiri_tak_bereaksi"),
                                    rs2.getString("laserasi"),
                                    rs2.getString("abrasi"),
                                    rs2.getString("hematoma"),
                                    rs2.getString("kontusio"),
                                    rs2.getString("dislokasi"),
                                    rs2.getString("luka_dingin"),
                                    rs2.getString("lokasi_luka_tembak"),
                                    rs2.getString("lokasi_luka_tusuk"),
                                    rs2.getString("lokasi_luka_bakar"),
                                    rs2.getString("edema"),
                                    rs2.getString("amputasi"),
                                    rs2.getString("avulse"),
                                    rs2.getString("nyeri"),
                                    rs2.getString("fraktur_terbuka"),
                                    rs2.getString("fraktur_tertutup"),
                                    rs2.getString("lokasi_lain"),
                                    rs2.getString("ket_lokasi_lain"),
                                    rs2.getString("deskripsi_anamnesis"),
                                    rs2.getString("tgl_asesmen"),
                                    rs2.getString("jam_asesmen"),
                                    rs2.getString("nip_dpjp"),
                                    rs2.getString("waktu_simpan")
                                }) == true) {
                            System.out.println("Proses mengembalikan/restore data berhasil..!!");
                            JOptionPane.showMessageDialog(rootPane, "Proses mengembalikan/restore data berhasil..!!");
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
        if (Sequel.queryu2tf("delete from asesmen_medik_bedah_ranap where no_rawat=?", 1, new String[]{
            tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString()
        }) == true) {
            kembalikanData();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal dikembalikan..!!");
        }
    }
    
    private void variabelBersih() {
        napza = "";
        alkohol = "";
        pejalan = "";
        spdGayung = "";
        spdMotor = "";
        mobil = "";
        jatuh = "";
        pohon = "";
        gedung = "";
        lainJatuh = "";
        lukaTembak = "";
        lukaTusuk = "";
        lukaHancur = "";
        lukaBakar = "";
        lainLuka = "";
        jelasKanan = "";
        menurunKanan = "";
        ronciKanan = "";
        wezingKanan = "";
        jelasKiri = "";
        menurunKiri = "";
        ronciKiri = "";
        wezingKiri = "";
        suhu = "";
        nasal = "";
        nrm = "";
        lainSatur = "";
        kuat = "";
        lemah = "";
        reguler = "";
        ireguler = "";
        hangat = "";
        panas = "";
        dingin = "";
        normal = "";
        kering = "";
        lembab = "";
        alert = "";
        verbal = "";
        pain = "";
        unres = "";
        laserasi = "";
        abrasi = "";
        hema = "";
        kontu = "";
        dislok = "";
        lukaDingin = "";
        lukaTembak7 = "";
        lukaTusuk8 = "";
        lukaBakar9 = "";
        edema = "";
        amput = "";
        avul = "";
        nyeri = "";
        frTerbuka = "";
        frTertutup = "";
        lainLokasi = "";
        user = "";
    }
    
    private void hitungSkor() {
        int a, b, c, d, e, total;
        if (TskorA.getText().equals("")) {
            TskorA.setText("0");
        } else {
            TskorA.setText(TskorA.getText());
        }
        
        if (TskorB.getText().equals("")) {
            TskorB.setText("0");
        } else {
            TskorB.setText(TskorB.getText());
        }
        
        if (TskorC.getText().equals("")) {
            TskorC.setText("0");
        } else {
            TskorC.setText(TskorC.getText());
        }
        
        if (TskorD.getText().equals("")) {
            TskorD.setText("0");
        } else {
            TskorD.setText(TskorD.getText());
        }
        
        if (TskorE.getText().equals("")) {
            TskorE.setText("0");
        } else {
            TskorE.setText(TskorE.getText());
        }
        
        a = Integer.parseInt(TskorA.getText());
        b = Integer.parseInt(TskorB.getText());
        c = Integer.parseInt(TskorC.getText());
        d = Integer.parseInt(TskorD.getText());
        e = Integer.parseInt(TskorE.getText());
        
        total = a + b + c + d + e;
        TskorTotal.setText(Valid.SetAngka2(total));
    }
}
