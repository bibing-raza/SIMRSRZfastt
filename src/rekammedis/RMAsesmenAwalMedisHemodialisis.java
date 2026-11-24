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
import inventory.DlgCatatanResepBiasaAntibiotik;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import laporan.DlgHasilPenunjangMedis;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;
import simrskhanza.frmUtama;

/**
 *
 * @author perpustakaan
 */
public final class RMAsesmenAwalMedisHemodialisis extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private int i = 0, x = 0, pilihan = 0;
    private DlgCariDokter dokter;
    private DlgCariPetugas petugas;
    private String nipDokter = "", nipSuper = "", nipPengisi = "", cekHipertensiRPD = "", cekDMRPD = "", cekPjk = "", cekAsmaRPD = "", cekStrok = "", cekLiver = "",
            cekGinjal = "", cekTbParu = "", cekLainRPD = "", cekHipertensiRPK = "", cekDMRPK = "", cekJantung = "", cekAsmaRPK = "", cekLainRPK = "", cekAnisokor = "",
            cekIsokor = "", cekSimetris = "", cekAsimetris = "", cekReguler = "", cekIreguler = "", cekHangat = "", cekDingin = "", cekTglPelak = "", cekRenal = "",
            cekTerapi = "", cekDialisis = "", cekSled = "", cekCrrt = "", cekHf = "", cekCito = "", cekElektif = "", cekPrepar = "", cekTraveling = "", cekRegulerTndkn = "",
            cekLow = "", cekHigh = "", cekAVShunt = "", cekGraft = "", cekTunel = "", cekTempor = "", cekFemoral = "", tglReg = "", jamReg = "", sttsrwt = "";
    private frmUtama formUtama;

    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsesmenAwalMedisHemodialisis(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Assesmen", "Jam", "Ruang Perawatan", "Dokter Memeriksa", "Supervisor/Koordinator Jaga", "Diisi Oleh",
            "ruang_rawat", "tgl_asesmen", "jam_asesmen", "rujukan", "ya_rujukan", "ket_ya_rujukan", "diagnosa_rujukan", "tidak_rujukan", "ket_diantar", "nip_dokter_memeriksa",
            "nip_supervisor", "tgl_anamnesa", "jam_anamnesa", "keluhan", "riw_penyakit_sekarang", "hipertensi", "dm", "pjk", "asma", "stroke", "liver", "ginjal", "tb_paru",
            "lainya", "ket_lainya", "pernah_dirawat", "ket_kapan", "ket_dimana", "diagnosis", "riwkel_hipertensi", "riwkel_dm", "riwkel_jantung", "riwkel_asma", "riwkel_lainya",
            "ket_riwkel_lainya", "riw_alergi", "nyeri", "ket_lokasi", "ket_intensitas", "skor", "jenis", "keadaan_umum", "gizi", "gcs_e", "gcs_m", "gcs_v", "tindakan_resus", "bb",
            "tb", "tensi", "nadi", "respirasi", "axila", "rektal", "anemis", "ket_anemis_ada", "ikterik", "ket_anemis_ikterik", "anisokor", "isokor", "diameter", "udem_palpebra",
            "tonsil", "faring", "lidah", "bibir", "jvp", "kelenjar_limfo", "ket_kelenjar_limfo_ada", "kaku_kuduk", "simetris", "asimetris", "ket_asimetris", "s1s2", "reguler",
            "ireguler", "murmur", "lain_lain_murmur", "suara_nafas", "ronchi", "ket_ronchi_ada", "whezing", "ket_whezing_ada", "distended", "meteorismus", "peristaltik", "asites",
            "nyeri_tekan", "ket_lokasi_nyeri_tekan", "hepar", "lien", "hangat", "dingin", "udem", "ket_udem_ada", "lain_lain_udem", "pemeriksaan_penunjang", "diag_utama",
            "diag_komplikasi", "diag_tambahan", "cek_tgl_pelaksanaan", "tgl_pelaksanaan", "renal", "terapi", "dialisis", "sled", "crrt", "hf", "cito", "elektif", "preparatif",
            "traveling", "reguler_tindakan", "lama_hd", "qb", "qd", "up_goal", "luas_membrane", "low_fix", "high_flux", "antikuagulan", "av_shunt", "graft", "tunnel", "temporer",
            "femoral", "profiling", "tranfusi_darah", "eritropoetin", "nip_pengisi", "waktu_simpan"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 140; i++) {
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
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setPreferredWidth(230);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setPreferredWidth(220);
            } else if (i == 8) {
                column.setPreferredWidth(220);
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
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "no_rawat", "Nama Obat", "Dosis", "Waktu Penggunaan Terakhir", "waktu_simpan"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode1);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 5; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setPreferredWidth(130);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TketYaLainya.setDocument(new batasInput((int) 100).getKata(TketYaLainya));
        TdiagRujukan.setDocument(new batasInput((int) 200).getKata(TdiagRujukan));
        Tdiantar.setDocument(new batasInput((int) 100).getKata(Tdiantar));
        Tkeluhan.setDocument(new batasInput((int) 200).getKata(Tkeluhan));
        TketLainRPD.setDocument(new batasInput((int) 100).getKata(TketLainRPD));
        Tkapan.setDocument(new batasInput((int) 100).getKata(Tkapan));
        Tdimana.setDocument(new batasInput((int) 100).getKata(Tdimana));
        Tdiagnosis.setDocument(new batasInput((int) 100).getKata(Tdiagnosis));
        TketLainRPK.setDocument(new batasInput((int) 100).getKata(TketLainRPK));
        TriwAlergi.setDocument(new batasInput((int) 200).getKata(TriwAlergi));
        Tlokasi.setDocument(new batasInput((int) 100).getKata(Tlokasi));
        Tintensitas.setDocument(new batasInput((int) 100).getKata(Tintensitas));
        Tskor.setDocument(new batasInput((byte) 5).getOnlyAngka(Tskor));
        TgcsE.setDocument(new batasInput((byte) 5).getOnlyAngka(TgcsE));
        TgcsM.setDocument(new batasInput((byte) 5).getOnlyAngka(TgcsM));
        TgcsV.setDocument(new batasInput((byte) 5).getOnlyAngka(TgcsV));        
        Tbb.setDocument(new batasInput((int) 5).getKata(Tbb));
        Ttb.setDocument(new batasInput((int) 5).getKata(Ttb));
        Ttd.setDocument(new batasInput((int) 10).getKata(Ttd));
        Tnadi.setDocument(new batasInput((int) 10).getKata(Tnadi));
        Trespi.setDocument(new batasInput((int) 10).getKata(Trespi));
        Taxila.setDocument(new batasInput((int) 5).getKata(Taxila));
        Trektal.setDocument(new batasInput((int) 5).getKata(Trektal));
        TketAnemis.setDocument(new batasInput((int) 100).getKata(TketAnemis));
        TketIkterik.setDocument(new batasInput((int) 100).getKata(TketIkterik));
        Tdiameter.setDocument(new batasInput((int) 10).getKata(Tdiameter));
        Ttonsil.setDocument(new batasInput((int) 100).getKata(Ttonsil));
        Tfaring.setDocument(new batasInput((int) 100).getKata(Tfaring));
        Tlidah.setDocument(new batasInput((int) 100).getKata(Tlidah));
        Tbibir.setDocument(new batasInput((int) 100).getKata(Tbibir));
        Tjvp.setDocument(new batasInput((int) 100).getKata(Tjvp));
        TketKelenjar.setDocument(new batasInput((int) 100).getKata(TketKelenjar));
        TketAsimetris.setDocument(new batasInput((int) 100).getKata(TketAsimetris));
        Ts1s2.setDocument(new batasInput((int) 100).getKata(Ts1s2));
        TketMurmur.setDocument(new batasInput((int) 100).getKata(TketMurmur));
        TlainlainMurmur.setDocument(new batasInput((int) 200).getKata(TlainlainMurmur));
        TsuaraNafas.setDocument(new batasInput((int) 100).getKata(TsuaraNafas));
        TketRonchi.setDocument(new batasInput((int) 100).getKata(TketRonchi));
        TketWhezing.setDocument(new batasInput((int) 100).getKata(TketWhezing));
        TlokasiNyeriTekan.setDocument(new batasInput((int) 100).getKata(TlokasiNyeriTekan));
        Thepar.setDocument(new batasInput((int) 100).getKata(Thepar));
        Tlien.setDocument(new batasInput((int) 100).getKata(Tlien));
        TketUdem.setDocument(new batasInput((int) 100).getKata(TketUdem));
        TlainlainUdem.setDocument(new batasInput((int) 200).getKata(TlainlainUdem));
        TlamaHd.setDocument(new batasInput((int) 10).getKata(TlamaHd));
        Tqb.setDocument(new batasInput((int) 10).getKata(Tqb));
        Tqd.setDocument(new batasInput((int) 10).getKata(Tqd));
        TupGoal.setDocument(new batasInput((int) 10).getKata(TupGoal));
        Tluas.setDocument(new batasInput((int) 10).getKata(Tluas));
        Tantikoagulan.setDocument(new batasInput((int) 50).getKata(Tantikoagulan));
        Tprofiling.setDocument(new batasInput((int) 200).getKata(Tprofiling));
        Ttransfusi.setDocument(new batasInput((int) 200).getKata(Ttransfusi));
        Teritropoetin.setDocument(new batasInput((int) 200).getKata(Teritropoetin));        
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
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        jLabel68 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnResep = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel10 = new widget.Label();
        label19 = new widget.Label();
        label98 = new widget.Label();
        jLabel12 = new widget.Label();
        TtglAses = new widget.Tanggal();
        jLabel13 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel65 = new widget.Label();
        chkHipertensiRPD = new widget.CekBox();
        chkPjk = new widget.CekBox();
        chkDmRPD = new widget.CekBox();
        chkAsmaRPD = new widget.CekBox();
        chkStroke = new widget.CekBox();
        chkLiver = new widget.CekBox();
        cmbRujukan = new widget.ComboBox();
        cmbYaRujukan = new widget.ComboBox();
        TketYaLainya = new widget.TextBox();
        jLabel64 = new widget.Label();
        TdiagRujukan = new widget.TextBox();
        cmbTidakRujukan = new widget.ComboBox();
        Tdiantar = new widget.TextBox();
        TnmDokter = new widget.TextBox();
        label22 = new widget.Label();
        TnmSupervisor = new widget.TextBox();
        jLabel66 = new widget.Label();
        Tkeluhan = new widget.TextBox();
        jLabel67 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        TriwPenyktSkg = new widget.TextArea();
        jLabel14 = new widget.Label();
        TtglAnam = new widget.Tanggal();
        jLabel15 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel69 = new widget.Label();
        chkGinjal = new widget.CekBox();
        chkTBparu = new widget.CekBox();
        chkLainRPD = new widget.CekBox();
        TketLainRPD = new widget.TextBox();
        jLabel16 = new widget.Label();
        cmbPernah = new widget.ComboBox();
        jLabel17 = new widget.Label();
        Tkapan = new widget.TextBox();
        jLabel18 = new widget.Label();
        Tdimana = new widget.TextBox();
        jLabel20 = new widget.Label();
        Tdiagnosis = new widget.TextBox();
        jLabel70 = new widget.Label();
        jLabel22 = new widget.Label();
        TnmObat = new widget.TextBox();
        jLabel23 = new widget.Label();
        Tdosis = new widget.TextBox();
        jLabel24 = new widget.Label();
        TwktPenggunaan = new widget.TextBox();
        Scroll8 = new widget.ScrollPane();
        tbObat = new widget.Table();
        jLabel71 = new widget.Label();
        chkHipertensiRPK = new widget.CekBox();
        chkDmRPK = new widget.CekBox();
        chkJantung = new widget.CekBox();
        chkAsmaRPK = new widget.CekBox();
        chkLainRPK = new widget.CekBox();
        TketLainRPK = new widget.TextBox();
        jLabel72 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        TriwAlergi = new widget.TextArea();
        label99 = new widget.Label();
        jLabel73 = new widget.Label();
        cmbNyeri = new widget.ComboBox();
        jLabel25 = new widget.Label();
        Tlokasi = new widget.TextBox();
        jLabel26 = new widget.Label();
        Tintensitas = new widget.TextBox();
        jLabel74 = new widget.Label();
        Tskor = new widget.TextBox();
        jLabel27 = new widget.Label();
        cmbJenis = new widget.ComboBox();
        label100 = new widget.Label();
        jLabel75 = new widget.Label();
        cmbKeadaan = new widget.ComboBox();
        jLabel76 = new widget.Label();
        cmbGizi = new widget.ComboBox();
        jLabel77 = new widget.Label();
        TgcsE = new widget.TextBox();
        jLabel78 = new widget.Label();
        TgcsM = new widget.TextBox();
        jLabel79 = new widget.Label();
        TgcsV = new widget.TextBox();
        jLabel80 = new widget.Label();
        cmbTindakan = new widget.ComboBox();
        jLabel81 = new widget.Label();
        Tbb = new widget.TextBox();
        jLabel28 = new widget.Label();
        Ttb = new widget.TextBox();
        jLabel29 = new widget.Label();
        Ttd = new widget.TextBox();
        jLabel30 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel31 = new widget.Label();
        jLabel82 = new widget.Label();
        Trespi = new widget.TextBox();
        jLabel32 = new widget.Label();
        Taxila = new widget.TextBox();
        jLabel33 = new widget.Label();
        Trektal = new widget.TextBox();
        jLabel35 = new widget.Label();
        label101 = new widget.Label();
        jLabel83 = new widget.Label();
        cmbAnemis = new widget.ComboBox();
        TketAnemis = new widget.TextBox();
        jLabel84 = new widget.Label();
        cmbIkterik = new widget.ComboBox();
        TketIkterik = new widget.TextBox();
        jLabel85 = new widget.Label();
        chkAnisokor = new widget.CekBox();
        chkIsokor = new widget.CekBox();
        Tdiameter = new widget.TextBox();
        jLabel36 = new widget.Label();
        cmbUdemPalpe = new widget.ComboBox();
        jLabel86 = new widget.Label();
        Ttonsil = new widget.TextBox();
        jLabel37 = new widget.Label();
        Tfaring = new widget.TextBox();
        jLabel87 = new widget.Label();
        Tlidah = new widget.TextBox();
        jLabel38 = new widget.Label();
        Tbibir = new widget.TextBox();
        jLabel88 = new widget.Label();
        Tjvp = new widget.TextBox();
        cmbKelenjar = new widget.ComboBox();
        TketKelenjar = new widget.TextBox();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        cmbKaku = new widget.ComboBox();
        jLabel91 = new widget.Label();
        chkSimetris = new widget.CekBox();
        chkAsimetris = new widget.CekBox();
        TketAsimetris = new widget.TextBox();
        jLabel92 = new widget.Label();
        Ts1s2 = new widget.TextBox();
        chkReguler = new widget.CekBox();
        chkIreguler = new widget.CekBox();
        TketMurmur = new widget.TextBox();
        jLabel93 = new widget.Label();
        TlainlainMurmur = new widget.TextBox();
        jLabel94 = new widget.Label();
        TsuaraNafas = new widget.TextBox();
        jLabel95 = new widget.Label();
        cmbRonchi = new widget.ComboBox();
        TketRonchi = new widget.TextBox();
        jLabel96 = new widget.Label();
        cmbWhezing = new widget.ComboBox();
        TketWhezing = new widget.TextBox();
        cmbDistended = new widget.ComboBox();
        jLabel97 = new widget.Label();
        jLabel98 = new widget.Label();
        cmbMeteor = new widget.ComboBox();
        jLabel99 = new widget.Label();
        cmbPeristaltik = new widget.ComboBox();
        jLabel100 = new widget.Label();
        cmbAsites = new widget.ComboBox();
        jLabel101 = new widget.Label();
        cmbNyeriTekan = new widget.ComboBox();
        TlokasiNyeriTekan = new widget.TextBox();
        jLabel102 = new widget.Label();
        jLabel103 = new widget.Label();
        Thepar = new widget.TextBox();
        jLabel39 = new widget.Label();
        Tlien = new widget.TextBox();
        jLabel104 = new widget.Label();
        chkHangat = new widget.CekBox();
        chkDingin = new widget.CekBox();
        cmbUdemExtremitas = new widget.ComboBox();
        TketUdem = new widget.TextBox();
        jLabel105 = new widget.Label();
        TlainlainUdem = new widget.TextBox();
        PanelWall = new usu.widget.glass.PanelGlass();
        PanelWall1 = new usu.widget.glass.PanelGlass();
        scrollPane5 = new widget.ScrollPane();
        TpemeriksaanPenunjang = new widget.TextArea();
        TdiagUtama = new widget.TextBox();
        jLabel106 = new widget.Label();
        jLabel107 = new widget.Label();
        TdiagKomplikasi = new widget.TextBox();
        jLabel108 = new widget.Label();
        TdiagTambahan = new widget.TextBox();
        label102 = new widget.Label();
        label103 = new widget.Label();
        TtglPelaksanaan = new widget.Tanggal();
        jLabel110 = new widget.Label();
        chkRenal = new widget.CekBox();
        chkTerapi = new widget.CekBox();
        jLabel111 = new widget.Label();
        chkDialisisKon = new widget.CekBox();
        chkSled = new widget.CekBox();
        chkCrrt = new widget.CekBox();
        chkHf = new widget.CekBox();
        jLabel112 = new widget.Label();
        chkCito = new widget.CekBox();
        chkElektif = new widget.CekBox();
        chkPreparatif = new widget.CekBox();
        chkTraveling = new widget.CekBox();
        chkRegulerTindk = new widget.CekBox();
        label104 = new widget.Label();
        jLabel113 = new widget.Label();
        TlamaHd = new widget.TextBox();
        jLabel114 = new widget.Label();
        Tqb = new widget.TextBox();
        jLabel115 = new widget.Label();
        Tqd = new widget.TextBox();
        jLabel116 = new widget.Label();
        TupGoal = new widget.TextBox();
        jLabel117 = new widget.Label();
        Tluas = new widget.TextBox();
        chkLowFix = new widget.CekBox();
        chkHigh = new widget.CekBox();
        Tantikoagulan = new widget.TextBox();
        jLabel118 = new widget.Label();
        chkAvShunt = new widget.CekBox();
        chkGraft = new widget.CekBox();
        chkTunnel = new widget.CekBox();
        chkTemporer = new widget.CekBox();
        chkFemoral = new widget.CekBox();
        jLabel119 = new widget.Label();
        Tprofiling = new widget.TextBox();
        jLabel120 = new widget.Label();
        Ttransfusi = new widget.TextBox();
        jLabel121 = new widget.Label();
        Teritropoetin = new widget.TextBox();
        TnmPengisi = new widget.TextBox();
        jLabel122 = new widget.Label();
        BtnDokter = new widget.Button();
        BtnSupervisor = new widget.Button();
        BtnPengisi = new widget.Button();
        chkTglPelaksanaan = new widget.CekBox();
        BtnPasteHasil = new widget.Button();
        jLabel47 = new widget.Label();
        BtnBaru = new widget.Button();
        BtnTambah = new widget.Button();
        BtnHapusObat = new widget.Button();
        BtnGantiTtv = new widget.Button();
        chkSaya = new widget.CekBox();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Awal Medis Hemodialisis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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

        cmbPilihCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak.setName("cmbPilihCetak"); // NOI18N
        cmbPilihCetak.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass8.add(cmbPilihCetak);

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

        BtnResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Vial-Pills.png"))); // NOI18N
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
        FormInput.setToolTipText("Silahkan klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis.");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2272));
        FormInput.setLayout(null);

        TNoRw.setEditable(false);
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(319, 10, 410, 23);

        TNoRM.setEditable(false);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(247, 10, 70, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 110, 23);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("<html><div style=\"text-align: right;\">Dokter Yang :<br>Memeriksa&nbsp;&nbsp;</div></html>");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label19);
        label19.setBounds(0, 178, 110, 30);

        label98.setForeground(new java.awt.Color(0, 0, 0));
        label98.setText("ANAMNESA");
        label98.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label98.setName("label98"); // NOI18N
        label98.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label98);
        label98.setBounds(0, 234, 130, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Asesmen :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 38, 110, 23);

        TtglAses.setEditable(false);
        TtglAses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-11-2026" }));
        TtglAses.setDisplayFormat("dd-MM-yyyy");
        TtglAses.setName("TtglAses"); // NOI18N
        TtglAses.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglAses);
        TtglAses.setBounds(115, 38, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jam Asesmen :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(205, 38, 100, 23);

        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(312, 38, 45, 23);

        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(365, 38, 45, 23);

        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(418, 38, 45, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 66, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(115, 66, 615, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Rujukan :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 94, 110, 23);

        chkHipertensiRPD.setBackground(new java.awt.Color(255, 255, 250));
        chkHipertensiRPD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHipertensiRPD.setText("Hipertensi");
        chkHipertensiRPD.setBorderPainted(true);
        chkHipertensiRPD.setBorderPaintedFlat(true);
        chkHipertensiRPD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHipertensiRPD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHipertensiRPD.setName("chkHipertensiRPD"); // NOI18N
        chkHipertensiRPD.setOpaque(false);
        chkHipertensiRPD.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHipertensiRPD);
        chkHipertensiRPD.setBounds(115, 394, 75, 23);

        chkPjk.setBackground(new java.awt.Color(255, 255, 250));
        chkPjk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPjk.setText("PJK");
        chkPjk.setBorderPainted(true);
        chkPjk.setBorderPaintedFlat(true);
        chkPjk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPjk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPjk.setName("chkPjk"); // NOI18N
        chkPjk.setOpaque(false);
        chkPjk.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPjk);
        chkPjk.setBounds(250, 394, 45, 23);

        chkDmRPD.setBackground(new java.awt.Color(255, 255, 250));
        chkDmRPD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDmRPD.setText("DM");
        chkDmRPD.setBorderPainted(true);
        chkDmRPD.setBorderPaintedFlat(true);
        chkDmRPD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDmRPD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDmRPD.setName("chkDmRPD"); // NOI18N
        chkDmRPD.setOpaque(false);
        chkDmRPD.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDmRPD);
        chkDmRPD.setBounds(200, 394, 40, 23);

        chkAsmaRPD.setBackground(new java.awt.Color(255, 255, 250));
        chkAsmaRPD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsmaRPD.setText("Asma");
        chkAsmaRPD.setBorderPainted(true);
        chkAsmaRPD.setBorderPaintedFlat(true);
        chkAsmaRPD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsmaRPD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsmaRPD.setName("chkAsmaRPD"); // NOI18N
        chkAsmaRPD.setOpaque(false);
        chkAsmaRPD.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAsmaRPD);
        chkAsmaRPD.setBounds(304, 394, 53, 23);

        chkStroke.setBackground(new java.awt.Color(255, 255, 250));
        chkStroke.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkStroke.setText("Stroke");
        chkStroke.setBorderPainted(true);
        chkStroke.setBorderPaintedFlat(true);
        chkStroke.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkStroke.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkStroke.setName("chkStroke"); // NOI18N
        chkStroke.setOpaque(false);
        chkStroke.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkStroke);
        chkStroke.setBounds(365, 394, 60, 23);

        chkLiver.setBackground(new java.awt.Color(255, 255, 250));
        chkLiver.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLiver.setText("Liver");
        chkLiver.setBorderPainted(true);
        chkLiver.setBorderPaintedFlat(true);
        chkLiver.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLiver.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLiver.setName("chkLiver"); // NOI18N
        chkLiver.setOpaque(false);
        chkLiver.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLiver);
        chkLiver.setBounds(435, 394, 50, 23);

        cmbRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya, dari", "Tidak" }));
        cmbRujukan.setName("cmbRujukan"); // NOI18N
        cmbRujukan.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRujukanActionPerformed(evt);
            }
        });
        FormInput.add(cmbRujukan);
        cmbRujukan.setBounds(115, 94, 70, 23);

        cmbYaRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "RS", "Puskesmas", "Praktek", "Lainnya" }));
        cmbYaRujukan.setName("cmbYaRujukan"); // NOI18N
        cmbYaRujukan.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbYaRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbYaRujukanActionPerformed(evt);
            }
        });
        FormInput.add(cmbYaRujukan);
        cmbYaRujukan.setBounds(190, 94, 85, 23);

        TketYaLainya.setName("TketYaLainya"); // NOI18N
        TketYaLainya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketYaLainyaKeyPressed(evt);
            }
        });
        FormInput.add(TketYaLainya);
        TketYaLainya.setBounds(280, 94, 450, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Diagnosa Rujukan :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 122, 110, 23);

        TdiagRujukan.setName("TdiagRujukan"); // NOI18N
        TdiagRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagRujukanKeyPressed(evt);
            }
        });
        FormInput.add(TdiagRujukan);
        TdiagRujukan.setBounds(115, 122, 615, 23);

        cmbTidakRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Datang Sendiri", "Diantar" }));
        cmbTidakRujukan.setName("cmbTidakRujukan"); // NOI18N
        cmbTidakRujukan.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbTidakRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTidakRujukanActionPerformed(evt);
            }
        });
        FormInput.add(cmbTidakRujukan);
        cmbTidakRujukan.setBounds(115, 150, 100, 23);

        Tdiantar.setName("Tdiantar"); // NOI18N
        Tdiantar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiantarKeyPressed(evt);
            }
        });
        FormInput.add(Tdiantar);
        Tdiantar.setBounds(220, 150, 510, 23);

        TnmDokter.setEditable(false);
        TnmDokter.setName("TnmDokter"); // NOI18N
        FormInput.add(TnmDokter);
        TnmDokter.setBounds(115, 178, 360, 23);

        label22.setForeground(new java.awt.Color(0, 0, 0));
        label22.setText("<html><div style=\"text-align: right;\">Supervisor / :<br>Koordinator Jaga&nbsp;&nbsp;</div></html>");
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label22);
        label22.setBounds(0, 206, 110, 30);

        TnmSupervisor.setEditable(false);
        TnmSupervisor.setName("TnmSupervisor"); // NOI18N
        FormInput.add(TnmSupervisor);
        TnmSupervisor.setBounds(115, 206, 360, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("1. Keluhan Utama :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 290, 110, 23);

        Tkeluhan.setName("Tkeluhan"); // NOI18N
        Tkeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeluhanKeyPressed(evt);
            }
        });
        FormInput.add(Tkeluhan);
        Tkeluhan.setBounds(115, 290, 615, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("<html><div style=\"text-align: right;\">2. Riwayat :<br>Penyakit Sekarang&nbsp;&nbsp;</div></html>");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 318, 110, 30);

        scrollPane3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane3.setName("scrollPane3"); // NOI18N

        TriwPenyktSkg.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TriwPenyktSkg.setColumns(20);
        TriwPenyktSkg.setRows(5);
        TriwPenyktSkg.setName("TriwPenyktSkg"); // NOI18N
        TriwPenyktSkg.setPreferredSize(new java.awt.Dimension(162, 2000));
        TriwPenyktSkg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwPenyktSkgKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(TriwPenyktSkg);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(115, 318, 615, 70);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tgl. Anamnesa :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 262, 110, 23);

        TtglAnam.setEditable(false);
        TtglAnam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-11-2026" }));
        TtglAnam.setDisplayFormat("dd-MM-yyyy");
        TtglAnam.setName("TtglAnam"); // NOI18N
        TtglAnam.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglAnam);
        TtglAnam.setBounds(115, 262, 90, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Jam Anamnesa :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(205, 262, 100, 23);

        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(312, 262, 45, 23);

        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(365, 262, 45, 23);

        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(418, 262, 45, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("<html><div style=\"text-align: right;\">3. Riwayat :<br>Penyakit Dahulu&nbsp;&nbsp;</div></html>");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 394, 110, 30);

        chkGinjal.setBackground(new java.awt.Color(255, 255, 250));
        chkGinjal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGinjal.setText("Ginjal");
        chkGinjal.setBorderPainted(true);
        chkGinjal.setBorderPaintedFlat(true);
        chkGinjal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGinjal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGinjal.setName("chkGinjal"); // NOI18N
        chkGinjal.setOpaque(false);
        chkGinjal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGinjal);
        chkGinjal.setBounds(496, 394, 50, 23);

        chkTBparu.setBackground(new java.awt.Color(255, 255, 250));
        chkTBparu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTBparu.setText("TB Paru");
        chkTBparu.setBorderPainted(true);
        chkTBparu.setBorderPaintedFlat(true);
        chkTBparu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTBparu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTBparu.setName("chkTBparu"); // NOI18N
        chkTBparu.setOpaque(false);
        chkTBparu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTBparu);
        chkTBparu.setBounds(555, 394, 64, 23);

        chkLainRPD.setBackground(new java.awt.Color(255, 255, 250));
        chkLainRPD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainRPD.setText("Lain - Lain :");
        chkLainRPD.setBorderPainted(true);
        chkLainRPD.setBorderPaintedFlat(true);
        chkLainRPD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainRPD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainRPD.setName("chkLainRPD"); // NOI18N
        chkLainRPD.setOpaque(false);
        chkLainRPD.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainRPD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainRPDActionPerformed(evt);
            }
        });
        FormInput.add(chkLainRPD);
        chkLainRPD.setBounds(625, 394, 80, 23);

        TketLainRPD.setName("TketLainRPD"); // NOI18N
        TketLainRPD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketLainRPDKeyPressed(evt);
            }
        });
        FormInput.add(TketLainRPD);
        TketLainRPD.setBounds(705, 394, 130, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Pernah Dirawat :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(115, 422, 100, 23);

        cmbPernah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPernah.setName("cmbPernah"); // NOI18N
        cmbPernah.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbPernah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPernahActionPerformed(evt);
            }
        });
        FormInput.add(cmbPernah);
        cmbPernah.setBounds(220, 422, 55, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Kapan :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(275, 422, 60, 23);

        Tkapan.setName("Tkapan"); // NOI18N
        Tkapan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkapanKeyPressed(evt);
            }
        });
        FormInput.add(Tkapan);
        Tkapan.setBounds(340, 422, 390, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Dimana :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(275, 450, 60, 23);

        Tdimana.setName("Tdimana"); // NOI18N
        Tdimana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdimanaKeyPressed(evt);
            }
        });
        FormInput.add(Tdimana);
        Tdimana.setBounds(340, 450, 390, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Diagnosis :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(265, 478, 70, 23);

        Tdiagnosis.setName("Tdiagnosis"); // NOI18N
        Tdiagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosisKeyPressed(evt);
            }
        });
        FormInput.add(Tdiagnosis);
        Tdiagnosis.setBounds(340, 478, 390, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("4. Riwayat Pengobatan (Termasuk Yang Sedang Dikonsumsi) :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 506, 335, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Nama Obat :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(115, 534, 80, 23);

        TnmObat.setName("TnmObat"); // NOI18N
        TnmObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmObatKeyPressed(evt);
            }
        });
        FormInput.add(TnmObat);
        TnmObat.setBounds(200, 534, 530, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Dosis :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(115, 562, 80, 23);

        Tdosis.setName("Tdosis"); // NOI18N
        Tdosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdosisKeyPressed(evt);
            }
        });
        FormInput.add(Tdosis);
        Tdosis.setBounds(200, 562, 320, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Waktu Penggunaan Terakhir :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(35, 590, 160, 23);

        TwktPenggunaan.setName("TwktPenggunaan"); // NOI18N
        FormInput.add(TwktPenggunaan);
        TwktPenggunaan.setBounds(200, 590, 320, 23);

        Scroll8.setName("Scroll8"); // NOI18N

        tbObat.setName("tbObat"); // NOI18N
        tbObat.getTableHeader().setReorderingAllowed(false);
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        Scroll8.setViewportView(tbObat);

        FormInput.add(Scroll8);
        Scroll8.setBounds(115, 618, 615, 143);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("<html><div style=\"text-align: right;\">5. Riwayat :<br>Penyakit Keluarga&nbsp;&nbsp;</div></html>");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 767, 110, 30);

        chkHipertensiRPK.setBackground(new java.awt.Color(255, 255, 250));
        chkHipertensiRPK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHipertensiRPK.setText("Hipertensi");
        chkHipertensiRPK.setBorderPainted(true);
        chkHipertensiRPK.setBorderPaintedFlat(true);
        chkHipertensiRPK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHipertensiRPK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHipertensiRPK.setName("chkHipertensiRPK"); // NOI18N
        chkHipertensiRPK.setOpaque(false);
        chkHipertensiRPK.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHipertensiRPK);
        chkHipertensiRPK.setBounds(115, 767, 75, 23);

        chkDmRPK.setBackground(new java.awt.Color(255, 255, 250));
        chkDmRPK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDmRPK.setText("DM");
        chkDmRPK.setBorderPainted(true);
        chkDmRPK.setBorderPaintedFlat(true);
        chkDmRPK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDmRPK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDmRPK.setName("chkDmRPK"); // NOI18N
        chkDmRPK.setOpaque(false);
        chkDmRPK.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDmRPK);
        chkDmRPK.setBounds(200, 767, 40, 23);

        chkJantung.setBackground(new java.awt.Color(255, 255, 250));
        chkJantung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJantung.setText("Jantung");
        chkJantung.setBorderPainted(true);
        chkJantung.setBorderPaintedFlat(true);
        chkJantung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJantung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJantung.setName("chkJantung"); // NOI18N
        chkJantung.setOpaque(false);
        chkJantung.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkJantung);
        chkJantung.setBounds(250, 767, 65, 23);

        chkAsmaRPK.setBackground(new java.awt.Color(255, 255, 250));
        chkAsmaRPK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsmaRPK.setText("Asma");
        chkAsmaRPK.setBorderPainted(true);
        chkAsmaRPK.setBorderPaintedFlat(true);
        chkAsmaRPK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsmaRPK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsmaRPK.setName("chkAsmaRPK"); // NOI18N
        chkAsmaRPK.setOpaque(false);
        chkAsmaRPK.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAsmaRPK);
        chkAsmaRPK.setBounds(325, 767, 53, 23);

        chkLainRPK.setBackground(new java.awt.Color(255, 255, 250));
        chkLainRPK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainRPK.setText("Lainnya :");
        chkLainRPK.setBorderPainted(true);
        chkLainRPK.setBorderPaintedFlat(true);
        chkLainRPK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainRPK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainRPK.setName("chkLainRPK"); // NOI18N
        chkLainRPK.setOpaque(false);
        chkLainRPK.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainRPK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainRPKActionPerformed(evt);
            }
        });
        FormInput.add(chkLainRPK);
        chkLainRPK.setBounds(387, 767, 68, 23);

        TketLainRPK.setName("TketLainRPK"); // NOI18N
        TketLainRPK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketLainRPKKeyPressed(evt);
            }
        });
        FormInput.add(TketLainRPK);
        TketLainRPK.setBounds(457, 767, 210, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("6. Riwayat Alergi :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 795, 110, 23);

        scrollPane4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane4.setName("scrollPane4"); // NOI18N

        TriwAlergi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TriwAlergi.setColumns(20);
        TriwAlergi.setRows(5);
        TriwAlergi.setName("TriwAlergi"); // NOI18N
        TriwAlergi.setPreferredSize(new java.awt.Dimension(162, 2000));
        TriwAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwAlergiKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(TriwAlergi);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(115, 795, 615, 70);

        label99.setForeground(new java.awt.Color(0, 0, 0));
        label99.setText("PENILAIAN NYERI");
        label99.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label99.setName("label99"); // NOI18N
        label99.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label99);
        label99.setBounds(0, 870, 130, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Nyeri :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 898, 110, 23);

        cmbNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbNyeri.setName("cmbNyeri"); // NOI18N
        cmbNyeri.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbNyeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNyeriActionPerformed(evt);
            }
        });
        FormInput.add(cmbNyeri);
        cmbNyeri.setBounds(115, 898, 55, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Lokasi :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(170, 898, 60, 23);

        Tlokasi.setName("Tlokasi"); // NOI18N
        Tlokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlokasiKeyPressed(evt);
            }
        });
        FormInput.add(Tlokasi);
        Tlokasi.setBounds(235, 898, 200, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Intensitas :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(435, 898, 70, 23);

        Tintensitas.setName("Tintensitas"); // NOI18N
        Tintensitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TintensitasKeyPressed(evt);
            }
        });
        FormInput.add(Tintensitas);
        Tintensitas.setBounds(510, 898, 220, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Skor :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 926, 110, 23);

        Tskor.setName("Tskor"); // NOI18N
        Tskor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorKeyPressed(evt);
            }
        });
        FormInput.add(Tskor);
        Tskor.setBounds(115, 926, 40, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("(Metode VAS)      Jenis :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(163, 926, 120, 23);

        cmbJenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Akut", "Kronis" }));
        cmbJenis.setName("cmbJenis"); // NOI18N
        cmbJenis.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbJenis);
        cmbJenis.setBounds(285, 926, 60, 23);

        label100.setForeground(new java.awt.Color(0, 0, 0));
        label100.setText("TANDA VITAL");
        label100.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label100.setName("label100"); // NOI18N
        label100.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label100);
        label100.setBounds(0, 954, 130, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Keadaan Umum :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 982, 110, 23);

        cmbKeadaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Baik", "Sedang", "Lemah", "Jelek" }));
        cmbKeadaan.setName("cmbKeadaan"); // NOI18N
        cmbKeadaan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKeadaan);
        cmbKeadaan.setBounds(115, 982, 70, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Gizi :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(185, 982, 40, 23);

        cmbGizi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Baik", "Kurang", "Buruk" }));
        cmbGizi.setName("cmbGizi"); // NOI18N
        cmbGizi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbGizi);
        cmbGizi.setBounds(230, 982, 65, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("GCS : E :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(300, 982, 60, 23);

        TgcsE.setName("TgcsE"); // NOI18N
        TgcsE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsEKeyPressed(evt);
            }
        });
        FormInput.add(TgcsE);
        TgcsE.setBounds(365, 982, 40, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("M :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(405, 982, 30, 23);

        TgcsM.setName("TgcsM"); // NOI18N
        TgcsM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsMKeyPressed(evt);
            }
        });
        FormInput.add(TgcsM);
        TgcsM.setBounds(434, 982, 40, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setText("V :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(475, 982, 30, 23);

        TgcsV.setName("TgcsV"); // NOI18N
        TgcsV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsVKeyPressed(evt);
            }
        });
        FormInput.add(TgcsV);
        TgcsV.setBounds(504, 982, 40, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Tindakan Resusitasi :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(545, 982, 120, 23);

        cmbTindakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbTindakan.setName("cmbTindakan"); // NOI18N
        cmbTindakan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbTindakan);
        cmbTindakan.setBounds(670, 982, 55, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("BB :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 1010, 110, 23);

        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        FormInput.add(Tbb);
        Tbb.setBounds(115, 1010, 60, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Kg.     TB :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(183, 1010, 54, 23);

        Ttb.setName("Ttb"); // NOI18N
        Ttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbKeyPressed(evt);
            }
        });
        FormInput.add(Ttb);
        Ttb.setBounds(240, 1010, 60, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Cm.      Tekanan Darah :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(305, 1010, 120, 23);

        Ttd.setName("Ttd"); // NOI18N
        Ttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdKeyPressed(evt);
            }
        });
        FormInput.add(Ttd);
        Ttd.setBounds(427, 1010, 70, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("mmHg      Nadi :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(504, 1010, 80, 23);

        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(585, 1010, 70, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("x/mnt");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(663, 1010, 40, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Respirasi :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 1038, 110, 23);

        Trespi.setName("Trespi"); // NOI18N
        Trespi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrespiKeyPressed(evt);
            }
        });
        FormInput.add(Trespi);
        Trespi.setBounds(115, 1038, 60, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("x/mnt       Suhu Axila / Rektal :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(183, 1038, 150, 23);

        Taxila.setName("Taxila"); // NOI18N
        Taxila.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TaxilaKeyPressed(evt);
            }
        });
        FormInput.add(Taxila);
        Taxila.setBounds(335, 1038, 60, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("°C / :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(400, 1038, 30, 23);

        Trektal.setName("Trektal"); // NOI18N
        Trektal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrektalKeyPressed(evt);
            }
        });
        FormInput.add(Trektal);
        Trektal.setBounds(432, 1038, 60, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("°C");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(500, 1038, 30, 23);

        label101.setForeground(new java.awt.Color(0, 0, 0));
        label101.setText("PEMERIKSAAN FISIK");
        label101.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label101.setName("label101"); // NOI18N
        label101.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label101);
        label101.setBounds(0, 1066, 130, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Mata (Anemis) :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(0, 1094, 110, 23);

        cmbAnemis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ada" }));
        cmbAnemis.setName("cmbAnemis"); // NOI18N
        cmbAnemis.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbAnemis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAnemisActionPerformed(evt);
            }
        });
        FormInput.add(cmbAnemis);
        cmbAnemis.setBounds(115, 1094, 55, 23);

        TketAnemis.setName("TketAnemis"); // NOI18N
        TketAnemis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketAnemisKeyPressed(evt);
            }
        });
        FormInput.add(TketAnemis);
        TketAnemis.setBounds(175, 1094, 555, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Ikterik :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(0, 1122, 110, 23);

        cmbIkterik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ada" }));
        cmbIkterik.setName("cmbIkterik"); // NOI18N
        cmbIkterik.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbIkterik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbIkterikActionPerformed(evt);
            }
        });
        FormInput.add(cmbIkterik);
        cmbIkterik.setBounds(115, 1122, 55, 23);

        TketIkterik.setName("TketIkterik"); // NOI18N
        TketIkterik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketIkterikKeyPressed(evt);
            }
        });
        FormInput.add(TketIkterik);
        TketIkterik.setBounds(175, 1122, 555, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Pupil :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(0, 1150, 110, 23);

        chkAnisokor.setBackground(new java.awt.Color(255, 255, 250));
        chkAnisokor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAnisokor.setText("Anisokor");
        chkAnisokor.setBorderPainted(true);
        chkAnisokor.setBorderPaintedFlat(true);
        chkAnisokor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAnisokor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAnisokor.setName("chkAnisokor"); // NOI18N
        chkAnisokor.setOpaque(false);
        chkAnisokor.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAnisokor);
        chkAnisokor.setBounds(115, 1150, 70, 23);

        chkIsokor.setBackground(new java.awt.Color(255, 255, 250));
        chkIsokor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIsokor.setText("Isokor,       Diameter :");
        chkIsokor.setBorderPainted(true);
        chkIsokor.setBorderPaintedFlat(true);
        chkIsokor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIsokor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIsokor.setName("chkIsokor"); // NOI18N
        chkIsokor.setOpaque(false);
        chkIsokor.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIsokor);
        chkIsokor.setBounds(195, 1150, 130, 23);

        Tdiameter.setName("Tdiameter"); // NOI18N
        Tdiameter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiameterKeyPressed(evt);
            }
        });
        FormInput.add(Tdiameter);
        Tdiameter.setBounds(325, 1150, 70, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("mm       Udem Palpebrae :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(403, 1150, 125, 23);

        cmbUdemPalpe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ada" }));
        cmbUdemPalpe.setName("cmbUdemPalpe"); // NOI18N
        cmbUdemPalpe.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbUdemPalpe);
        cmbUdemPalpe.setBounds(532, 1150, 55, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("THT : Tonsil :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 1178, 110, 23);

        Ttonsil.setName("Ttonsil"); // NOI18N
        Ttonsil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtonsilKeyPressed(evt);
            }
        });
        FormInput.add(Ttonsil);
        Ttonsil.setBounds(115, 1178, 290, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Faring :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(405, 1178, 60, 23);

        Tfaring.setName("Tfaring"); // NOI18N
        Tfaring.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TfaringKeyPressed(evt);
            }
        });
        FormInput.add(Tfaring);
        Tfaring.setBounds(470, 1178, 260, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Lidah :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 1206, 110, 23);

        Tlidah.setName("Tlidah"); // NOI18N
        Tlidah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlidahKeyPressed(evt);
            }
        });
        FormInput.add(Tlidah);
        Tlidah.setBounds(115, 1206, 290, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Bibir :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(405, 1206, 60, 23);

        Tbibir.setName("Tbibir"); // NOI18N
        Tbibir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbibirKeyPressed(evt);
            }
        });
        FormInput.add(Tbibir);
        Tbibir.setBounds(470, 1206, 260, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Leher : JVP :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(0, 1234, 110, 23);

        Tjvp.setName("Tjvp"); // NOI18N
        Tjvp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjvpKeyPressed(evt);
            }
        });
        FormInput.add(Tjvp);
        Tjvp.setBounds(115, 1234, 290, 23);

        cmbKelenjar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ada" }));
        cmbKelenjar.setName("cmbKelenjar"); // NOI18N
        cmbKelenjar.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbKelenjar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKelenjarActionPerformed(evt);
            }
        });
        FormInput.add(cmbKelenjar);
        cmbKelenjar.setBounds(115, 1262, 55, 23);

        TketKelenjar.setName("TketKelenjar"); // NOI18N
        TketKelenjar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketKelenjarKeyPressed(evt);
            }
        });
        FormInput.add(TketKelenjar);
        TketKelenjar.setBounds(175, 1262, 415, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("<html><div style=\"text-align: right;\">Pembesaran :<br>Kelenjar Limfo&nbsp;&nbsp;</div></html>");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 1262, 110, 30);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Kaku Kuduk :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(590, 1262, 80, 23);

        cmbKaku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ada" }));
        cmbKaku.setName("cmbKaku"); // NOI18N
        cmbKaku.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKaku);
        cmbKaku.setBounds(675, 1262, 55, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Thorax :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(0, 1290, 110, 23);

        chkSimetris.setBackground(new java.awt.Color(255, 255, 250));
        chkSimetris.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSimetris.setText("Simetris");
        chkSimetris.setBorderPainted(true);
        chkSimetris.setBorderPaintedFlat(true);
        chkSimetris.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSimetris.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSimetris.setName("chkSimetris"); // NOI18N
        chkSimetris.setOpaque(false);
        chkSimetris.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSimetris);
        chkSimetris.setBounds(115, 1290, 60, 23);

        chkAsimetris.setBackground(new java.awt.Color(255, 255, 250));
        chkAsimetris.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsimetris.setText("Asimetris :");
        chkAsimetris.setBorderPainted(true);
        chkAsimetris.setBorderPaintedFlat(true);
        chkAsimetris.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsimetris.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsimetris.setName("chkAsimetris"); // NOI18N
        chkAsimetris.setOpaque(false);
        chkAsimetris.setPreferredSize(new java.awt.Dimension(175, 23));
        chkAsimetris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAsimetrisActionPerformed(evt);
            }
        });
        FormInput.add(chkAsimetris);
        chkAsimetris.setBounds(185, 1290, 75, 23);

        TketAsimetris.setName("TketAsimetris"); // NOI18N
        TketAsimetris.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketAsimetrisKeyPressed(evt);
            }
        });
        FormInput.add(TketAsimetris);
        TketAsimetris.setBounds(260, 1290, 470, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Cor : S1/S2 :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(0, 1318, 110, 23);

        Ts1s2.setName("Ts1s2"); // NOI18N
        Ts1s2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Ts1s2KeyPressed(evt);
            }
        });
        FormInput.add(Ts1s2);
        Ts1s2.setBounds(115, 1318, 290, 23);

        chkReguler.setBackground(new java.awt.Color(255, 255, 250));
        chkReguler.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkReguler.setText("Reguler");
        chkReguler.setBorderPainted(true);
        chkReguler.setBorderPaintedFlat(true);
        chkReguler.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkReguler.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkReguler.setName("chkReguler"); // NOI18N
        chkReguler.setOpaque(false);
        chkReguler.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkReguler);
        chkReguler.setBounds(410, 1318, 60, 23);

        chkIreguler.setBackground(new java.awt.Color(255, 255, 250));
        chkIreguler.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIreguler.setText("Ireguler, Murmur :");
        chkIreguler.setBorderPainted(true);
        chkIreguler.setBorderPaintedFlat(true);
        chkIreguler.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIreguler.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIreguler.setName("chkIreguler"); // NOI18N
        chkIreguler.setOpaque(false);
        chkIreguler.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIreguler);
        chkIreguler.setBounds(480, 1318, 110, 23);

        TketMurmur.setName("TketMurmur"); // NOI18N
        TketMurmur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketMurmurKeyPressed(evt);
            }
        });
        FormInput.add(TketMurmur);
        TketMurmur.setBounds(590, 1318, 140, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Lain - Lain :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(0, 1346, 110, 23);

        TlainlainMurmur.setName("TlainlainMurmur"); // NOI18N
        TlainlainMurmur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainlainMurmurKeyPressed(evt);
            }
        });
        FormInput.add(TlainlainMurmur);
        TlainlainMurmur.setBounds(115, 1346, 615, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("<html><div style=\"text-align: right;\">Pulmo :<br>Suara Nafas&nbsp;&nbsp;</div></html>");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(0, 1374, 110, 30);

        TsuaraNafas.setName("TsuaraNafas"); // NOI18N
        TsuaraNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuaraNafasKeyPressed(evt);
            }
        });
        FormInput.add(TsuaraNafas);
        TsuaraNafas.setBounds(115, 1374, 290, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Ronchi :");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(405, 1374, 60, 23);

        cmbRonchi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ada" }));
        cmbRonchi.setName("cmbRonchi"); // NOI18N
        cmbRonchi.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRonchi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRonchiActionPerformed(evt);
            }
        });
        FormInput.add(cmbRonchi);
        cmbRonchi.setBounds(470, 1374, 55, 23);

        TketRonchi.setName("TketRonchi"); // NOI18N
        TketRonchi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketRonchiKeyPressed(evt);
            }
        });
        FormInput.add(TketRonchi);
        TketRonchi.setBounds(530, 1374, 200, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Wheezing :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(0, 1402, 110, 23);

        cmbWhezing.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ada" }));
        cmbWhezing.setName("cmbWhezing"); // NOI18N
        cmbWhezing.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbWhezing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbWhezingActionPerformed(evt);
            }
        });
        FormInput.add(cmbWhezing);
        cmbWhezing.setBounds(115, 1402, 55, 23);

        TketWhezing.setName("TketWhezing"); // NOI18N
        TketWhezing.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketWhezingKeyPressed(evt);
            }
        });
        FormInput.add(TketWhezing);
        TketWhezing.setBounds(175, 1402, 555, 23);

        cmbDistended.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ada" }));
        cmbDistended.setName("cmbDistended"); // NOI18N
        cmbDistended.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbDistended);
        cmbDistended.setBounds(115, 1430, 55, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("<html><div style=\"text-align: right;\">Abdomen :<br>Distended&nbsp;&nbsp;</div></html>");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(0, 1430, 110, 30);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Meteorismus :");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(170, 1430, 90, 23);

        cmbMeteor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ada" }));
        cmbMeteor.setName("cmbMeteor"); // NOI18N
        cmbMeteor.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMeteor);
        cmbMeteor.setBounds(265, 1430, 55, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Peristaltik :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(320, 1430, 70, 23);

        cmbPeristaltik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Meningkat", "Menurun", "Tidak Ada" }));
        cmbPeristaltik.setName("cmbPeristaltik"); // NOI18N
        cmbPeristaltik.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbPeristaltik);
        cmbPeristaltik.setBounds(395, 1430, 80, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Asites :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(475, 1430, 60, 23);

        cmbAsites.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ada" }));
        cmbAsites.setName("cmbAsites"); // NOI18N
        cmbAsites.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbAsites);
        cmbAsites.setBounds(540, 1430, 55, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Nyeri Tekan :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(0, 1458, 110, 23);

        cmbNyeriTekan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ada" }));
        cmbNyeriTekan.setName("cmbNyeriTekan"); // NOI18N
        cmbNyeriTekan.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbNyeriTekan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNyeriTekanActionPerformed(evt);
            }
        });
        FormInput.add(cmbNyeriTekan);
        cmbNyeriTekan.setBounds(115, 1458, 55, 23);

        TlokasiNyeriTekan.setName("TlokasiNyeriTekan"); // NOI18N
        TlokasiNyeriTekan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlokasiNyeriTekanKeyPressed(evt);
            }
        });
        FormInput.add(TlokasiNyeriTekan);
        TlokasiNyeriTekan.setBounds(235, 1458, 495, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Lokasi :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(170, 1458, 60, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Hepar:");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(0, 1486, 110, 23);

        Thepar.setName("Thepar"); // NOI18N
        Thepar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TheparKeyPressed(evt);
            }
        });
        FormInput.add(Thepar);
        Thepar.setBounds(115, 1486, 290, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Lien :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(405, 1486, 50, 23);

        Tlien.setName("Tlien"); // NOI18N
        Tlien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlienKeyPressed(evt);
            }
        });
        FormInput.add(Tlien);
        Tlien.setBounds(460, 1486, 270, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Extremitas :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(0, 1514, 110, 23);

        chkHangat.setBackground(new java.awt.Color(255, 255, 250));
        chkHangat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHangat.setText("Hangat");
        chkHangat.setBorderPainted(true);
        chkHangat.setBorderPaintedFlat(true);
        chkHangat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHangat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHangat.setName("chkHangat"); // NOI18N
        chkHangat.setOpaque(false);
        chkHangat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHangat);
        chkHangat.setBounds(115, 1514, 60, 23);

        chkDingin.setBackground(new java.awt.Color(255, 255, 250));
        chkDingin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDingin.setText("Dingin       Udem :");
        chkDingin.setBorderPainted(true);
        chkDingin.setBorderPaintedFlat(true);
        chkDingin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDingin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDingin.setName("chkDingin"); // NOI18N
        chkDingin.setOpaque(false);
        chkDingin.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDingin);
        chkDingin.setBounds(185, 1514, 107, 23);

        cmbUdemExtremitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ada" }));
        cmbUdemExtremitas.setName("cmbUdemExtremitas"); // NOI18N
        cmbUdemExtremitas.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbUdemExtremitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUdemExtremitasActionPerformed(evt);
            }
        });
        FormInput.add(cmbUdemExtremitas);
        cmbUdemExtremitas.setBounds(295, 1514, 55, 23);

        TketUdem.setName("TketUdem"); // NOI18N
        TketUdem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketUdemKeyPressed(evt);
            }
        });
        FormInput.add(TketUdem);
        TketUdem.setBounds(355, 1514, 375, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Lain - Lain :");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(0, 1542, 110, 23);

        TlainlainUdem.setName("TlainlainUdem"); // NOI18N
        TlainlainUdem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainlainUdemKeyPressed(evt);
            }
        });
        FormInput.add(TlainlainUdem);
        TlainlainUdem.setBounds(115, 1542, 615, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/paru_form.jpg"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(750, 1290, 160, 160);

        PanelWall1.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/abdomen_form.jpg"))); // NOI18N
        PanelWall1.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall1.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall1.setRound(false);
        PanelWall1.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall1.setLayout(null);
        FormInput.add(PanelWall1);
        PanelWall1.setBounds(750, 1458, 160, 160);

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Pemeriksaan Penunjang : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N

        TpemeriksaanPenunjang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TpemeriksaanPenunjang.setColumns(20);
        TpemeriksaanPenunjang.setRows(5);
        TpemeriksaanPenunjang.setName("TpemeriksaanPenunjang"); // NOI18N
        TpemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(162, 2000));
        TpemeriksaanPenunjang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpemeriksaanPenunjangKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(TpemeriksaanPenunjang);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(115, 1587, 615, 224);

        TdiagUtama.setName("TdiagUtama"); // NOI18N
        TdiagUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagUtamaKeyPressed(evt);
            }
        });
        FormInput.add(TdiagUtama);
        TdiagUtama.setBounds(115, 1841, 615, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("<html><div style=\"text-align: right;\">Diagnosis Utama :<br>(Ginjal)&nbsp;&nbsp;</div></html>");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(0, 1841, 110, 30);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("Diag. Komplikasi :");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(0, 1869, 110, 23);

        TdiagKomplikasi.setName("TdiagKomplikasi"); // NOI18N
        TdiagKomplikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagKomplikasiKeyPressed(evt);
            }
        });
        FormInput.add(TdiagKomplikasi);
        TdiagKomplikasi.setBounds(115, 1869, 615, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("Diag. Tambahan :");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(0, 1897, 110, 23);

        TdiagTambahan.setName("TdiagTambahan"); // NOI18N
        TdiagTambahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagTambahanKeyPressed(evt);
            }
        });
        FormInput.add(TdiagTambahan);
        TdiagTambahan.setBounds(115, 1897, 615, 23);

        label102.setForeground(new java.awt.Color(0, 0, 0));
        label102.setText("DIAGNOSIS");
        label102.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label102.setName("label102"); // NOI18N
        label102.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label102);
        label102.setBounds(0, 1813, 130, 23);

        label103.setForeground(new java.awt.Color(0, 0, 0));
        label103.setText("TINDAKAN DIALISIS");
        label103.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label103.setName("label103"); // NOI18N
        label103.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label103);
        label103.setBounds(0, 1925, 130, 23);

        TtglPelaksanaan.setEditable(false);
        TtglPelaksanaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-11-2026" }));
        TtglPelaksanaan.setDisplayFormat("dd-MM-yyyy");
        TtglPelaksanaan.setName("TtglPelaksanaan"); // NOI18N
        TtglPelaksanaan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglPelaksanaan);
        TtglPelaksanaan.setBounds(140, 1953, 90, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("Tujuan Dialisis :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(230, 1953, 100, 23);

        chkRenal.setBackground(new java.awt.Color(255, 255, 250));
        chkRenal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRenal.setText("Renal Support");
        chkRenal.setBorderPainted(true);
        chkRenal.setBorderPaintedFlat(true);
        chkRenal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRenal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRenal.setName("chkRenal"); // NOI18N
        chkRenal.setOpaque(false);
        chkRenal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkRenal);
        chkRenal.setBounds(335, 1953, 95, 23);

        chkTerapi.setBackground(new java.awt.Color(255, 255, 250));
        chkTerapi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTerapi.setText("Terapi Pengganti Ginjal");
        chkTerapi.setBorderPainted(true);
        chkTerapi.setBorderPaintedFlat(true);
        chkTerapi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTerapi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTerapi.setName("chkTerapi"); // NOI18N
        chkTerapi.setOpaque(false);
        chkTerapi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTerapi);
        chkTerapi.setBounds(440, 1953, 140, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Metode Dialisis :");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(0, 1981, 110, 23);

        chkDialisisKon.setBackground(new java.awt.Color(255, 255, 250));
        chkDialisisKon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDialisisKon.setText("Dialisis Konvensional");
        chkDialisisKon.setBorderPainted(true);
        chkDialisisKon.setBorderPaintedFlat(true);
        chkDialisisKon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDialisisKon.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDialisisKon.setName("chkDialisisKon"); // NOI18N
        chkDialisisKon.setOpaque(false);
        chkDialisisKon.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDialisisKon);
        chkDialisisKon.setBounds(115, 1981, 125, 23);

        chkSled.setBackground(new java.awt.Color(255, 255, 250));
        chkSled.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSled.setText("SLED");
        chkSled.setBorderPainted(true);
        chkSled.setBorderPaintedFlat(true);
        chkSled.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSled.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSled.setName("chkSled"); // NOI18N
        chkSled.setOpaque(false);
        chkSled.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSled);
        chkSled.setBounds(249, 1981, 50, 23);

        chkCrrt.setBackground(new java.awt.Color(255, 255, 250));
        chkCrrt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCrrt.setText("CRRT");
        chkCrrt.setBorderPainted(true);
        chkCrrt.setBorderPaintedFlat(true);
        chkCrrt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCrrt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCrrt.setName("chkCrrt"); // NOI18N
        chkCrrt.setOpaque(false);
        chkCrrt.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCrrt);
        chkCrrt.setBounds(310, 1981, 55, 23);

        chkHf.setBackground(new java.awt.Color(255, 255, 250));
        chkHf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHf.setText("HF");
        chkHf.setBorderPainted(true);
        chkHf.setBorderPaintedFlat(true);
        chkHf.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHf.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHf.setName("chkHf"); // NOI18N
        chkHf.setOpaque(false);
        chkHf.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHf);
        chkHf.setBounds(375, 1981, 50, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("Jenis Tindakan :");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(0, 2009, 110, 23);

        chkCito.setBackground(new java.awt.Color(255, 255, 250));
        chkCito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCito.setText("Cito");
        chkCito.setBorderPainted(true);
        chkCito.setBorderPaintedFlat(true);
        chkCito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCito.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCito.setName("chkCito"); // NOI18N
        chkCito.setOpaque(false);
        chkCito.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCito);
        chkCito.setBounds(115, 2009, 47, 23);

        chkElektif.setBackground(new java.awt.Color(255, 255, 250));
        chkElektif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkElektif.setText("Elektif");
        chkElektif.setBorderPainted(true);
        chkElektif.setBorderPaintedFlat(true);
        chkElektif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkElektif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkElektif.setName("chkElektif"); // NOI18N
        chkElektif.setOpaque(false);
        chkElektif.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkElektif);
        chkElektif.setBounds(170, 2009, 60, 23);

        chkPreparatif.setBackground(new java.awt.Color(255, 255, 250));
        chkPreparatif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPreparatif.setText("Preparatif");
        chkPreparatif.setBorderPainted(true);
        chkPreparatif.setBorderPaintedFlat(true);
        chkPreparatif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPreparatif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPreparatif.setName("chkPreparatif"); // NOI18N
        chkPreparatif.setOpaque(false);
        chkPreparatif.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPreparatif);
        chkPreparatif.setBounds(238, 2009, 77, 23);

        chkTraveling.setBackground(new java.awt.Color(255, 255, 250));
        chkTraveling.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTraveling.setText("Traveling");
        chkTraveling.setBorderPainted(true);
        chkTraveling.setBorderPaintedFlat(true);
        chkTraveling.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTraveling.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTraveling.setName("chkTraveling"); // NOI18N
        chkTraveling.setOpaque(false);
        chkTraveling.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTraveling);
        chkTraveling.setBounds(324, 2009, 70, 23);

        chkRegulerTindk.setBackground(new java.awt.Color(255, 255, 250));
        chkRegulerTindk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRegulerTindk.setText("Reguler");
        chkRegulerTindk.setBorderPainted(true);
        chkRegulerTindk.setBorderPaintedFlat(true);
        chkRegulerTindk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRegulerTindk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRegulerTindk.setName("chkRegulerTindk"); // NOI18N
        chkRegulerTindk.setOpaque(false);
        chkRegulerTindk.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkRegulerTindk);
        chkRegulerTindk.setBounds(404, 2009, 70, 23);

        label104.setForeground(new java.awt.Color(0, 0, 0));
        label104.setText("PERESEPAN HEMODIALISIS");
        label104.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label104.setName("label104"); // NOI18N
        label104.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label104);
        label104.setBounds(0, 2037, 170, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("Lama HD :");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(0, 2065, 110, 23);

        TlamaHd.setName("TlamaHd"); // NOI18N
        TlamaHd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlamaHdKeyPressed(evt);
            }
        });
        FormInput.add(TlamaHd);
        TlamaHd.setBounds(115, 2065, 110, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("QB :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(225, 2065, 40, 23);

        Tqb.setName("Tqb"); // NOI18N
        Tqb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TqbKeyPressed(evt);
            }
        });
        FormInput.add(Tqb);
        Tqb.setBounds(270, 2065, 110, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("QD :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(380, 2065, 40, 23);

        Tqd.setName("Tqd"); // NOI18N
        Tqd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TqdKeyPressed(evt);
            }
        });
        FormInput.add(Tqd);
        Tqd.setBounds(425, 2065, 110, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("UP Goal :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(535, 2065, 60, 23);

        TupGoal.setName("TupGoal"); // NOI18N
        TupGoal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TupGoalKeyPressed(evt);
            }
        });
        FormInput.add(TupGoal);
        TupGoal.setBounds(600, 2065, 130, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("Luas Membrane :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(0, 2093, 110, 23);

        Tluas.setName("Tluas"); // NOI18N
        Tluas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TluasKeyPressed(evt);
            }
        });
        FormInput.add(Tluas);
        Tluas.setBounds(115, 2093, 110, 23);

        chkLowFix.setBackground(new java.awt.Color(255, 255, 250));
        chkLowFix.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLowFix.setText("Low Fix");
        chkLowFix.setBorderPainted(true);
        chkLowFix.setBorderPaintedFlat(true);
        chkLowFix.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLowFix.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLowFix.setName("chkLowFix"); // NOI18N
        chkLowFix.setOpaque(false);
        chkLowFix.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLowFix);
        chkLowFix.setBounds(232, 2093, 64, 23);

        chkHigh.setBackground(new java.awt.Color(255, 255, 250));
        chkHigh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHigh.setText("High Flux      Antikoagulan :");
        chkHigh.setBorderPainted(true);
        chkHigh.setBorderPaintedFlat(true);
        chkHigh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHigh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHigh.setName("chkHigh"); // NOI18N
        chkHigh.setOpaque(false);
        chkHigh.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHigh);
        chkHigh.setBounds(303, 2093, 155, 23);

        Tantikoagulan.setName("Tantikoagulan"); // NOI18N
        Tantikoagulan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TantikoagulanKeyPressed(evt);
            }
        });
        FormInput.add(Tantikoagulan);
        Tantikoagulan.setBounds(460, 2093, 270, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("Akses Vaskular :");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(0, 2121, 110, 23);

        chkAvShunt.setBackground(new java.awt.Color(255, 255, 250));
        chkAvShunt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAvShunt.setText("AV Shunt");
        chkAvShunt.setBorderPainted(true);
        chkAvShunt.setBorderPaintedFlat(true);
        chkAvShunt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAvShunt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAvShunt.setName("chkAvShunt"); // NOI18N
        chkAvShunt.setOpaque(false);
        chkAvShunt.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAvShunt);
        chkAvShunt.setBounds(115, 2121, 70, 23);

        chkGraft.setBackground(new java.awt.Color(255, 255, 250));
        chkGraft.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGraft.setText("Graft");
        chkGraft.setBorderPainted(true);
        chkGraft.setBorderPaintedFlat(true);
        chkGraft.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGraft.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGraft.setName("chkGraft"); // NOI18N
        chkGraft.setOpaque(false);
        chkGraft.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGraft);
        chkGraft.setBounds(195, 2121, 50, 23);

        chkTunnel.setBackground(new java.awt.Color(255, 255, 250));
        chkTunnel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTunnel.setText("Tunnel CDL");
        chkTunnel.setBorderPainted(true);
        chkTunnel.setBorderPaintedFlat(true);
        chkTunnel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTunnel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTunnel.setName("chkTunnel"); // NOI18N
        chkTunnel.setOpaque(false);
        chkTunnel.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTunnel);
        chkTunnel.setBounds(255, 2121, 80, 23);

        chkTemporer.setBackground(new java.awt.Color(255, 255, 250));
        chkTemporer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTemporer.setText("Temporer CDL");
        chkTemporer.setBorderPainted(true);
        chkTemporer.setBorderPaintedFlat(true);
        chkTemporer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTemporer.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTemporer.setName("chkTemporer"); // NOI18N
        chkTemporer.setOpaque(false);
        chkTemporer.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTemporer);
        chkTemporer.setBounds(345, 2121, 97, 23);

        chkFemoral.setBackground(new java.awt.Color(255, 255, 250));
        chkFemoral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFemoral.setText("Femoral");
        chkFemoral.setBorderPainted(true);
        chkFemoral.setBorderPaintedFlat(true);
        chkFemoral.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFemoral.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFemoral.setName("chkFemoral"); // NOI18N
        chkFemoral.setOpaque(false);
        chkFemoral.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkFemoral);
        chkFemoral.setBounds(450, 2121, 70, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("Profilling :");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(0, 2149, 110, 23);

        Tprofiling.setName("Tprofiling"); // NOI18N
        Tprofiling.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprofilingKeyPressed(evt);
            }
        });
        FormInput.add(Tprofiling);
        Tprofiling.setBounds(115, 2149, 615, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("Transfusi Darah :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(0, 2177, 110, 23);

        Ttransfusi.setName("Ttransfusi"); // NOI18N
        Ttransfusi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtransfusiKeyPressed(evt);
            }
        });
        FormInput.add(Ttransfusi);
        Ttransfusi.setBounds(115, 2177, 615, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("Eritropoetin :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(0, 2205, 110, 23);

        Teritropoetin.setName("Teritropoetin"); // NOI18N
        Teritropoetin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TeritropoetinKeyPressed(evt);
            }
        });
        FormInput.add(Teritropoetin);
        Teritropoetin.setBounds(115, 2205, 615, 23);

        TnmPengisi.setEditable(false);
        TnmPengisi.setName("TnmPengisi"); // NOI18N
        FormInput.add(TnmPengisi);
        TnmPengisi.setBounds(115, 2233, 360, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("Diisi Oleh :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(0, 2233, 110, 23);

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
        BtnDokter.setBounds(480, 178, 28, 23);

        BtnSupervisor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSupervisor.setMnemonic('2');
        BtnSupervisor.setToolTipText("Alt+2");
        BtnSupervisor.setName("BtnSupervisor"); // NOI18N
        BtnSupervisor.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSupervisorActionPerformed(evt);
            }
        });
        FormInput.add(BtnSupervisor);
        BtnSupervisor.setBounds(480, 206, 28, 23);

        BtnPengisi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPengisi.setMnemonic('2');
        BtnPengisi.setToolTipText("Alt+2");
        BtnPengisi.setName("BtnPengisi"); // NOI18N
        BtnPengisi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPengisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPengisiActionPerformed(evt);
            }
        });
        FormInput.add(BtnPengisi);
        BtnPengisi.setBounds(480, 2233, 28, 23);

        chkTglPelaksanaan.setBackground(new java.awt.Color(255, 255, 250));
        chkTglPelaksanaan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglPelaksanaan.setText("Tgl. Pelaksanaan : ");
        chkTglPelaksanaan.setBorderPainted(true);
        chkTglPelaksanaan.setBorderPaintedFlat(true);
        chkTglPelaksanaan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglPelaksanaan.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkTglPelaksanaan.setName("chkTglPelaksanaan"); // NOI18N
        chkTglPelaksanaan.setOpaque(false);
        chkTglPelaksanaan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglPelaksanaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglPelaksanaanActionPerformed(evt);
            }
        });
        FormInput.add(chkTglPelaksanaan);
        chkTglPelaksanaan.setBounds(5, 1953, 125, 23);

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
        BtnPasteHasil.setBounds(750, 1630, 100, 30);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("(Klik kanan pada halaman ini untuk melihat hasil pemeriksaan penunjang)");
        jLabel47.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(115, 1568, 380, 23);

        BtnBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaru.setText("Baru");
        BtnBaru.setToolTipText("Data Obat Baru");
        BtnBaru.setName("BtnBaru"); // NOI18N
        BtnBaru.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruActionPerformed(evt);
            }
        });
        FormInput.add(BtnBaru);
        BtnBaru.setBounds(740, 618, 90, 30);

        BtnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambah.setText("Tambah");
        BtnTambah.setToolTipText("Tambah Data Obat");
        BtnTambah.setName("BtnTambah"); // NOI18N
        BtnTambah.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambah);
        BtnTambah.setBounds(740, 657, 90, 30);

        BtnHapusObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusObat.setText("Hapus");
        BtnHapusObat.setToolTipText("Hapus Obat");
        BtnHapusObat.setName("BtnHapusObat"); // NOI18N
        BtnHapusObat.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusObatActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusObat);
        BtnHapusObat.setBounds(740, 696, 90, 30);

        BtnGantiTtv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiTtv.setText("Ganti");
        BtnGantiTtv.setToolTipText("Ganti Obat");
        BtnGantiTtv.setName("BtnGantiTtv"); // NOI18N
        BtnGantiTtv.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiTtv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiTtvActionPerformed(evt);
            }
        });
        FormInput.add(BtnGantiTtv);
        BtnGantiTtv.setBounds(740, 735, 90, 30);

        chkSaya.setBackground(new java.awt.Color(255, 255, 250));
        chkSaya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSaya.setText("Saya Sendiri");
        chkSaya.setBorderPainted(true);
        chkSaya.setBorderPaintedFlat(true);
        chkSaya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya.setName("chkSaya"); // NOI18N
        chkSaya.setOpaque(false);
        chkSaya.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaActionPerformed(evt);
            }
        });
        FormInput.add(chkSaya);
        chkSaya.setBounds(516, 178, 100, 23);

        scrollInput.setViewportView(FormInput);

        TabRawat.addTab("Input Assesmen", scrollInput);

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
        jLabel19.setText("Tgl. Pengkajian :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-11-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-11-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

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
            if (Sequel.menyimpantf("asesmen_awal_medis_hemodialisis", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?", "No.Rawat", 133, new String[]{
                        TNoRw.getText(), sttsrwt, TrgRawat.getText(), Valid.SetTgl(TtglAses.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        cmbRujukan.getSelectedItem().toString(), cmbYaRujukan.getSelectedItem().toString(), TketYaLainya.getText(), TdiagRujukan.getText(), cmbTidakRujukan.getSelectedItem().toString(),
                        Tdiantar.getText(), nipDokter, nipSuper, Valid.SetTgl(TtglAnam.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                        Tkeluhan.getText(), TriwPenyktSkg.getText(), cekHipertensiRPD, cekDMRPD, cekPjk, cekAsmaRPD, cekStrok, cekLiver, cekGinjal, cekTbParu, cekLainRPD,
                        TketLainRPD.getText(), cmbPernah.getSelectedItem().toString(), Tkapan.getText(), Tdimana.getText(), Tdiagnosis.getText(), cekHipertensiRPK, cekDMRPK, cekJantung,
                        cekAsmaRPK, cekLainRPK, TketLainRPK.getText(), TriwAlergi.getText(), cmbNyeri.getSelectedItem().toString(), Tlokasi.getText(), Tintensitas.getText(),
                        Tskor.getText(), cmbJenis.getSelectedItem().toString(), cmbKeadaan.getSelectedItem().toString(), cmbGizi.getSelectedItem().toString(), TgcsE.getText(),
                        TgcsM.getText(), TgcsV.getText(), cmbTindakan.getSelectedItem().toString(), Tbb.getText(), Ttb.getText(), Ttd.getText(), Tnadi.getText(), Trespi.getText(),
                        Taxila.getText(), Trektal.getText(), cmbAnemis.getSelectedItem().toString(), TketAnemis.getText(), cmbIkterik.getSelectedItem().toString(), TketIkterik.getText(),
                        cekAnisokor, cekIsokor, Tdiameter.getText(), cmbUdemPalpe.getSelectedItem().toString(), Ttonsil.getText(), Tfaring.getText(), Tlidah.getText(), Tbibir.getText(),
                        Tjvp.getText(), cmbKelenjar.getSelectedItem().toString(), TketKelenjar.getText(), cmbKaku.getSelectedItem().toString(), cekSimetris, cekAsimetris, TketAsimetris.getText(),
                        Ts1s2.getText(), cekReguler, cekIreguler, TketMurmur.getText(), TlainlainMurmur.getText(), TsuaraNafas.getText(), cmbRonchi.getSelectedItem().toString(),
                        TketRonchi.getText(), cmbWhezing.getSelectedItem().toString(), TketWhezing.getText(), cmbDistended.getSelectedItem().toString(), cmbMeteor.getSelectedItem().toString(),
                        cmbPeristaltik.getSelectedItem().toString(), cmbAsites.getSelectedItem().toString(), cmbNyeriTekan.getSelectedItem().toString(), TlokasiNyeriTekan.getText(), Thepar.getText(),
                        Tlien.getText(), cekHangat, cekDingin, cmbUdemExtremitas.getSelectedItem().toString(), TketUdem.getText(), TlainlainUdem.getText(), TpemeriksaanPenunjang.getText(),
                        TdiagUtama.getText(), TdiagKomplikasi.getText(), TdiagTambahan.getText(), cekTglPelak, Valid.SetTgl(TtglPelaksanaan.getSelectedItem() + ""), cekRenal, cekTerapi,
                        cekDialisis, cekSled, cekCrrt, cekHf, cekCito, cekElektif, cekPrepar, cekTraveling, cekRegulerTndkn, TlamaHd.getText(), Tqb.getText(), Tqd.getText(), TupGoal.getText(),
                        Tluas.getText(), cekLow, cekHigh, Tantikoagulan.getText(), cekAVShunt, cekGraft, cekTunel, cekTempor, cekFemoral, Tprofiling.getText(), Ttransfusi.getText(),
                        Teritropoetin.getText(), nipPengisi, Sequel.cariIsi("select now()")
                    }) == true) {

                //simpan riwayat pengobatan
                if (tbObat.getRowCount() != 0) {
                    for (i = 0; i < tbObat.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("obat_asesmen_awal_medis_hemodialisis",
                                "'" + TNoRw.getText() + "',"
                                + "'" + tbObat.getValueAt(i, 1).toString() + "',"
                                + "'" + tbObat.getValueAt(i, 2).toString() + "',"
                                + "'" + tbObat.getValueAt(i, 3).toString() + "',"
                                + "'" + tbObat.getValueAt(i, 4).toString() + "'", "Riwayat Data Pengobatan/Obat");
                    }
                }
                
                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Assesmen Awal Medis Hemodialisis", "Simpan");
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
            tampil();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from asesmen_awal_medis_hemodialisis where no_rawat=?", 1, new String[]{
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
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            if (tbAsesmen.getSelectedRow() > -1) {
                cekData();
                if (Sequel.mengedittf("asesmen_awal_medis_hemodialisis", "no_rawat=?", "tgl_asesmen=?, jam_asesmen=?, rujukan=?, ya_rujukan=?, ket_ya_rujukan=?, diagnosa_rujukan=?, "
                        + "tidak_rujukan=?, ket_diantar=?, nip_dokter_memeriksa=?, nip_supervisor=?, tgl_anamnesa=?, jam_anamnesa=?, keluhan=?, riw_penyakit_sekarang=?, hipertensi=?, "
                        + "dm=?, pjk=?, asma=?, stroke=?, liver=?, ginjal=?, tb_paru=?, lainya=?, ket_lainya=?, pernah_dirawat=?, ket_kapan=?, ket_dimana=?, diagnosis=?, "
                        + "riwkel_hipertensi=?, riwkel_dm=?, riwkel_jantung=?, riwkel_asma=?, riwkel_lainya=?, ket_riwkel_lainya=?, riw_alergi=?, nyeri=?, ket_lokasi=?, ket_intensitas=?, "
                        + "skor=?, jenis=?, keadaan_umum=?, gizi=?, gcs_e=?, gcs_m=?, gcs_v=?, tindakan_resus=?, bb=?, tb=?, tensi=?, nadi=?, respirasi=?, axila=?, rektal=?, anemis=?, "
                        + "ket_anemis_ada=?, ikterik=?, ket_anemis_ikterik=?, anisokor=?, isokor=?, diameter=?, udem_palpebra=?, tonsil=?, faring=?, lidah=?, bibir=?, jvp=?, "
                        + "kelenjar_limfo=?, ket_kelenjar_limfo_ada=?, kaku_kuduk=?, simetris=?, asimetris=?, ket_asimetris=?, s1s2=?, reguler=?, ireguler=?, murmur=?, lain_lain_murmur=?, "
                        + "suara_nafas=?, ronchi=?, ket_ronchi_ada=?, whezing=?, ket_whezing_ada=?, distended=?, meteorismus=?, peristaltik=?, asites=?, nyeri_tekan=?, "
                        + "ket_lokasi_nyeri_tekan=?, hepar=?, lien=?, hangat=?, dingin=?, udem=?, ket_udem_ada=?, lain_lain_udem=?, pemeriksaan_penunjang=?, diag_utama=?, diag_komplikasi=?, "
                        + "diag_tambahan=?, cek_tgl_pelaksanaan=?, tgl_pelaksanaan=?, renal=?, terapi=?, dialisis=?, sled=?, crrt=?, hf=?, cito=?, elektif=?, preparatif=?, traveling=?, "
                        + "reguler_tindakan=?, lama_hd=?, qb=?, qd=?, up_goal=?, luas_membrane=?, low_fix=?, high_flux=?, antikuagulan=?, av_shunt=?, graft=?, tunnel=?, temporer=?, "
                        + "femoral=?, profiling=?, tranfusi_darah=?, eritropoetin=?, nip_pengisi=?", 130, new String[]{
                            Valid.SetTgl(TtglAses.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            cmbRujukan.getSelectedItem().toString(), cmbYaRujukan.getSelectedItem().toString(), TketYaLainya.getText(), TdiagRujukan.getText(), cmbTidakRujukan.getSelectedItem().toString(),
                            Tdiantar.getText(), nipDokter, nipSuper, Valid.SetTgl(TtglAnam.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                            Tkeluhan.getText(), TriwPenyktSkg.getText(), cekHipertensiRPD, cekDMRPD, cekPjk, cekAsmaRPD, cekStrok, cekLiver, cekGinjal, cekTbParu, cekLainRPD,
                            TketLainRPD.getText(), cmbPernah.getSelectedItem().toString(), Tkapan.getText(), Tdimana.getText(), Tdiagnosis.getText(), cekHipertensiRPK, cekDMRPK, cekJantung,
                            cekAsmaRPK, cekLainRPK, TketLainRPK.getText(), TriwAlergi.getText(), cmbNyeri.getSelectedItem().toString(), Tlokasi.getText(), Tintensitas.getText(),
                            Tskor.getText(), cmbJenis.getSelectedItem().toString(), cmbKeadaan.getSelectedItem().toString(), cmbGizi.getSelectedItem().toString(), TgcsE.getText(),
                            TgcsM.getText(), TgcsV.getText(), cmbTindakan.getSelectedItem().toString(), Tbb.getText(), Ttb.getText(), Ttd.getText(), Tnadi.getText(), Trespi.getText(),
                            Taxila.getText(), Trektal.getText(), cmbAnemis.getSelectedItem().toString(), TketAnemis.getText(), cmbIkterik.getSelectedItem().toString(), TketIkterik.getText(),
                            cekAnisokor, cekIsokor, Tdiameter.getText(), cmbUdemPalpe.getSelectedItem().toString(), Ttonsil.getText(), Tfaring.getText(), Tlidah.getText(), Tbibir.getText(),
                            Tjvp.getText(), cmbKelenjar.getSelectedItem().toString(), TketKelenjar.getText(), cmbKaku.getSelectedItem().toString(), cekSimetris, cekAsimetris, TketAsimetris.getText(),
                            Ts1s2.getText(), cekReguler, cekIreguler, TketMurmur.getText(), TlainlainMurmur.getText(), TsuaraNafas.getText(), cmbRonchi.getSelectedItem().toString(),
                            TketRonchi.getText(), cmbWhezing.getSelectedItem().toString(), TketWhezing.getText(), cmbDistended.getSelectedItem().toString(), cmbMeteor.getSelectedItem().toString(),
                            cmbPeristaltik.getSelectedItem().toString(), cmbAsites.getSelectedItem().toString(), cmbNyeriTekan.getSelectedItem().toString(), TlokasiNyeriTekan.getText(), Thepar.getText(),
                            Tlien.getText(), cekHangat, cekDingin, cmbUdemExtremitas.getSelectedItem().toString(), TketUdem.getText(), TlainlainUdem.getText(), TpemeriksaanPenunjang.getText(),
                            TdiagUtama.getText(), TdiagKomplikasi.getText(), TdiagTambahan.getText(), cekTglPelak, Valid.SetTgl(TtglPelaksanaan.getSelectedItem() + ""), cekRenal, cekTerapi,
                            cekDialisis, cekSled, cekCrrt, cekHf, cekCito, cekElektif, cekPrepar, cekTraveling, cekRegulerTndkn, TlamaHd.getText(), Tqb.getText(), Tqd.getText(), TupGoal.getText(),
                            Tluas.getText(), cekLow, cekHigh, Tantikoagulan.getText(), cekAVShunt, cekGraft, cekTunel, cekTempor, cekFemoral, Tprofiling.getText(), Ttransfusi.getText(),
                            Teritropoetin.getText(), nipPengisi,
                            tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                        }) == true) {

                    //simpan riwayat pengobatan                    
                    if (tbObat.getRowCount() != 0) {
                        Sequel.meghapus("obat_asesmen_awal_medis_hemodialisis", "no_rawat", tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
                        for (i = 0; i < tbObat.getRowCount(); i++) {
                            Sequel.menyimpanIgnore("obat_asesmen_awal_medis_hemodialisis",
                                    "'" + TNoRw.getText() + "',"
                                    + "'" + tbObat.getValueAt(i, 1).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 2).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 3).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 4).toString() + "'", "Riwayat Data Pengobatan/Obat");
                        }
                    }

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Assesmen Awal Medis Hemodialisis", "Ganti");
                    TCari.setText(TNoRw.getText());
                    tampil();
                    emptTeks();
                    TabRawat.setSelectedIndex(1);
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
            
            param.put("ruangRawat", TrgRawat.getText());
            param.put("tglAsesmen", Valid.SetTglINDONESIA(Valid.SetTgl(TtglAses.getSelectedItem() + "")));
            param.put("jamAsesmen", cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " WITA");
            
            if (cmbRujukan.getSelectedIndex() == 1) {
                if (TketYaLainya.getText().equals("")) {
                    param.put("rujukan", cmbRujukan.getSelectedItem().toString() + ", " + cmbYaRujukan.getSelectedItem().toString() + " ................");
                } else {
                    param.put("rujukan", cmbRujukan.getSelectedItem().toString() + ", " + cmbYaRujukan.getSelectedItem().toString() + " : " + TketYaLainya.getText());
                }
            } else if (cmbRujukan.getSelectedIndex() == 2) {
                if (Tdiantar.getText().equals("")) {
                    param.put("rujukan", cmbRujukan.getSelectedItem().toString() + ", " + cmbTidakRujukan.getSelectedItem().toString() + " ................");
                } else {
                    param.put("rujukan", cmbRujukan.getSelectedItem().toString() + ", " + cmbTidakRujukan.getSelectedItem().toString() + " : " + Tdiantar.getText());
                }
            } else {
                param.put("rujukan", cmbRujukan.getSelectedItem().toString());
            }
            
            if (TdiagRujukan.getText().equals("")) {
                param.put("diagnosaRujukan", "................");
            } else {
                param.put("diagnosaRujukan", TdiagRujukan.getText());
            }
            
            param.put("dokterMemeriksa", TnmDokter.getText());
            param.put("supervisor", TnmSupervisor.getText());
            param.put("tglAnam", Valid.SetTglINDONESIA(Valid.SetTgl(TtglAnam.getSelectedItem() + "")));
            param.put("jamAnam", cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " WITA");
            
            if (Tkeluhan.getText().equals("")) {
                param.put("keluhan", "................");
            } else {
                param.put("keluhan", Tkeluhan.getText());
            }
            
            if (TriwPenyktSkg.getText().equals("")) {
                param.put("riwPenyakitSekarang", "................");
            } else {
                param.put("riwPenyakitSekarang", TriwPenyktSkg.getText());
            }
            
            if (chkHipertensiRPD.isSelected() == true) {
                param.put("hiperRPD", "V");
            } else {
                param.put("hiperRPD", "");
            }
            
            if (chkDmRPD.isSelected() == true) {
                param.put("dmRPD", "V");
            } else {
                param.put("dmRPD", "");
            }
            
            if (chkPjk.isSelected() == true) {
                param.put("pjk", "V");
            } else {
                param.put("pjk", "");
            }
            
            if (chkAsmaRPD.isSelected() == true) {
                param.put("asmaRPD", "V");
            } else {
                param.put("asmaRPD", "");
            }
            
            if (chkStroke.isSelected() == true) {
                param.put("strok", "V");
            } else {
                param.put("strok", "");
            }
            
            if (chkLiver.isSelected() == true) {
                param.put("liver", "V");
            } else {
                param.put("liver", "");
            }
            
            if (chkGinjal.isSelected() == true) {
                param.put("ginjal", "V");
            } else {
                param.put("ginjal", "");
            }
            
            if (chkTBparu.isSelected() == true) {
                param.put("tbParu", "V");
            } else {
                param.put("tbParu", "");
            }
            
            if (chkLainRPD.isSelected() == true) {
                param.put("lainRPD", "V");
                if (TketLainRPD.getText().equals("")) {
                    param.put("ketlainRPD", "Lain-lain ................");
                } else {
                    param.put("ketlainRPD", "Lain-lain : " + TketLainRPD.getText());
                }
            } else {
                param.put("lainRPD", "");
                param.put("ketlainRPD", "Lain-lain");
            }

            param.put("pernahDirawat", cmbPernah.getSelectedItem().toString());
            if (Tkapan.getText().equals("")) {
                param.put("kapan", "................");
            } else {
                param.put("kapan", Tkapan.getText());
            }

            if (Tdimana.getText().equals("")) {
                param.put("dimana", "................");
            } else {
                param.put("dimana", Tdimana.getText());
            }

            if (Tdiagnosis.getText().equals("")) {
                param.put("diagnosis", "................");
            } else {
                param.put("diagnosis", Tdiagnosis.getText());
            }
            
            //data Obat
            List<Map<String, ?>> dataObat = new ArrayList<>();
            for (int i = 0; i < tabMode1.getRowCount(); i++) {
                Map<String, Object> row = new HashMap<>();
                row.put("namaObat", tabMode1.getValueAt(i, 1).toString());
                row.put("dosiss", tabMode1.getValueAt(i, 2).toString());
                row.put("waktuPenggunaan", tabMode1.getValueAt(i, 3).toString());
                dataObat.add(row);
            }
            
            JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(dataObat);
            
            if (chkHipertensiRPK.isSelected() == true) {
                param.put("hiperRPK", "V");
            } else {
                param.put("hiperRPK", "");
            }
            
            if (chkDmRPK.isSelected() == true) {
                param.put("dmRPK", "V");
            } else {
                param.put("dmRPK", "");
            }
            
            if (chkJantung.isSelected() == true) {
                param.put("jantung", "V");
            } else {
                param.put("jantung", "");
            }
            
            if (chkAsmaRPK.isSelected() == true) {
                param.put("asmaRPK", "V");
            } else {
                param.put("asmaRPK", "");
            }
            
            if (chkLainRPK.isSelected() == true) {
                param.put("lainRPK", "V");
                if (TketLainRPK.getText().equals("")) {
                    param.put("ketlainRPK", "Lain-lain ................");
                } else {
                    param.put("ketlainRPK", "Lain-lain : " + TketLainRPK.getText());
                }
            } else {
                param.put("lainRPK", "");
                param.put("ketlainRPK", "Lain-lain");
            }
            
            if (TriwAlergi.getText().equals("")) {
                param.put("riwAlergi", "................");
            } else {
                param.put("riwAlergi", TriwAlergi.getText());
            }

            if (cmbNyeri.getSelectedIndex() == 1) {
                String lokasi = "", intensitas = "";
                if (Tlokasi.getText().equals("")) {
                    lokasi = ", Lokasi : ................";
                } else {
                    lokasi = ", Lokasi : " + Tlokasi.getText();
                }

                if (Tintensitas.getText().equals("")) {
                    intensitas = ", Intensitas : ................";
                } else {
                    intensitas = ", Intensitas : " + Tintensitas.getText();
                }
                param.put("nyeri", cmbNyeri.getSelectedItem().toString() + lokasi + intensitas);
            } else {
                param.put("nyeri", cmbNyeri.getSelectedItem().toString());
            }
            
            if (Tskor.getText().equals("")) {
                param.put("skor", "......");
            } else {
                param.put("skor", Tskor.getText());
            }
            
            param.put("jenis", cmbJenis.getSelectedItem().toString());
            param.put("keadaanUmum", cmbKeadaan.getSelectedItem().toString());
            param.put("gizi", cmbGizi.getSelectedItem().toString());
            
            if (TgcsE.getText().equals("")) {
                param.put("gcsE", "......");
            } else {
                param.put("gcsE", TgcsE.getText());
            }
            
            if (TgcsM.getText().equals("")) {
                param.put("gcsM", "......");
            } else {
                param.put("gcsM", TgcsM.getText());
            }
            
            if (TgcsV.getText().equals("")) {
                param.put("gcsV", "......");
            } else {
                param.put("gcsV", TgcsV.getText());
            }
            
            param.put("tindakan", cmbTindakan.getSelectedItem().toString());
            
            if (Tbb.getText().equals("")) {
                param.put("bb", "..........");
            } else {
                param.put("bb", Tbb.getText());
            }
            
            if (Ttb.getText().equals("")) {
                param.put("tb", "..........");
            } else {
                param.put("tb", Ttb.getText());
            }
            
            if (Ttd.getText().equals("")) {
                param.put("td", "..........");
            } else {
                param.put("td", Ttd.getText());
            }
            
            if (Tnadi.getText().equals("")) {
                param.put("nadi", "..........");
            } else {
                param.put("nadi", Tnadi.getText());
            }
            
            if (Trespi.getText().equals("")) {
                param.put("respi", "..........");
            } else {
                param.put("respi", Trespi.getText());
            }
            
            if (Taxila.getText().equals("")) {
                param.put("axila", "..........");
            } else {
                param.put("axila", Taxila.getText());
            }
            
            if (Trektal.getText().equals("")) {
                param.put("rektal", "..........");
            } else {
                param.put("rektal", Trektal.getText());
            }
            
            if (cmbAnemis.getSelectedIndex() == 2) {
                if (TketAnemis.getText().equals("")) {
                    param.put("anemis", cmbAnemis.getSelectedItem().toString() + ", ................");
                } else {
                    param.put("anemis", cmbAnemis.getSelectedItem().toString() + ", " + TketAnemis.getText());
                }
            } else {
                param.put("anemis", cmbAnemis.getSelectedItem().toString());
            }
            
            if (cmbIkterik.getSelectedIndex() == 2) {
                if (TketIkterik.getText().equals("")) {
                    param.put("ikterik", cmbIkterik.getSelectedItem().toString() + ", ................");
                } else {
                    param.put("ikterik", cmbIkterik.getSelectedItem().toString() + ", " + TketIkterik.getText());
                }
            } else {
                param.put("ikterik", cmbIkterik.getSelectedItem().toString());
            }
            
            if (chkAnisokor.isSelected() == true) {
                param.put("anisokor", "V");
            } else {
                param.put("anisokor", "");
            }
            
            if (chkIsokor.isSelected() == true) {
                param.put("isokor", "V");
            } else {
                param.put("isokor", "");
            }
            
            if (Tdiameter.getText().equals("")) {
                param.put("diameter", "..........");
            } else {
                param.put("diameter", Tdiameter.getText());
            }
            
            param.put("udemPalpe", cmbUdemPalpe.getSelectedItem().toString());
            
            if (Ttonsil.getText().equals("")) {
                param.put("tonsil", "................");
            } else {
                param.put("tonsil", Ttonsil.getText());
            }
            
            if (Tfaring.getText().equals("")) {
                param.put("faring", "................");
            } else {
                param.put("faring", Tfaring.getText());
            }
            
            if (Tlidah.getText().equals("")) {
                param.put("lidah", "................");
            } else {
                param.put("lidah", Tlidah.getText());
            }
            
            if (Tbibir.getText().equals("")) {
                param.put("bibir", "................");
            } else {
                param.put("bibir", Tbibir.getText());
            }
            
            if (Tjvp.getText().equals("")) {
                param.put("jvp", "................");
            } else {
                param.put("jvp", Tjvp.getText());
            }
            
            if (cmbKelenjar.getSelectedIndex() == 2) {
                if (TketKelenjar.getText().equals("")) {
                    param.put("kelenjar", cmbKelenjar.getSelectedItem().toString() + ", ................");
                } else {
                    param.put("kelenjar", cmbKelenjar.getSelectedItem().toString() + ", " + TketKelenjar.getText());
                }
            } else {
                param.put("kelenjar", cmbKelenjar.getSelectedItem().toString());
            }
            
            param.put("kaku", cmbKaku.getSelectedItem().toString());
            
            if (chkSimetris.isSelected() == true) {
                param.put("simetris", "V");
            } else {
                param.put("simetris", "");
            }
            
            if (chkAsimetris.isSelected() == true) {
                param.put("asimetris", "V");
                if (TketAsimetris.getText().equals("")) {
                    param.put("ketAsimetris", "Asimetris : ................");
                } else {
                    param.put("ketAsimetris", "Asimetris : " + TketAsimetris.getText());
                }
            } else {
                param.put("asimetris", "");
                param.put("ketAsimetris", "Asimetris");
            }
            
            if (Ts1s2.getText().equals("")) {
                param.put("s1s2", "................");
            } else {
                param.put("s1s2", Ts1s2.getText());
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
            
            if (TketMurmur.getText().equals("")) {
                param.put("murmur", "................");
            } else {
                param.put("murmur", TketMurmur.getText());
            }
            
            if (TlainlainMurmur.getText().equals("")) {
                param.put("lainMurmur", "................");
            } else {
                param.put("lainMurmur", TlainlainMurmur.getText());
            }
            
            if (TsuaraNafas.getText().equals("")) {
                param.put("suaraNafas", "................");
            } else {
                param.put("suaraNafas", TsuaraNafas.getText());
            }
            
            if (cmbRonchi.getSelectedIndex() == 2) {
                if (TketRonchi.getText().equals("")) {
                    param.put("ronchi", cmbRonchi.getSelectedItem().toString() + ", ................");
                } else {
                    param.put("ronchi", cmbRonchi.getSelectedItem().toString() + ", " + TketRonchi.getText());
                }
            } else {
                param.put("ronchi", cmbRonchi.getSelectedItem().toString());
            }
            
            if (cmbWhezing.getSelectedIndex() == 2) {
                if (TketWhezing.getText().equals("")) {
                    param.put("whezing", cmbWhezing.getSelectedItem().toString() + ", ................");
                } else {
                    param.put("whezing", cmbWhezing.getSelectedItem().toString() + ", " + TketWhezing.getText());
                }
            } else {
                param.put("whezing", cmbWhezing.getSelectedItem().toString());
            }
            
            param.put("distended", cmbDistended.getSelectedItem().toString());
            param.put("meteor", cmbMeteor.getSelectedItem().toString());
            param.put("peristaltik", cmbPeristaltik.getSelectedItem().toString());
            param.put("asites", cmbAsites.getSelectedItem().toString());
            
            if (cmbNyeriTekan.getSelectedIndex() == 2) {
                if (TlokasiNyeriTekan.getText().equals("")) {
                    param.put("nyeriTekan", cmbNyeriTekan.getSelectedItem().toString() + ", ................");
                } else {
                    param.put("nyeriTekan", cmbNyeriTekan.getSelectedItem().toString() + ", " + TlokasiNyeriTekan.getText());
                }
            } else {
                param.put("nyeriTekan", cmbNyeriTekan.getSelectedItem().toString());
            }
            
            if (Thepar.getText().equals("")) {
                param.put("hepar", "................");
            } else {
                param.put("hepar", Thepar.getText());
            }
            
            if (Tlien.getText().equals("")) {
                param.put("lien", "................");
            } else {
                param.put("lien", Tlien.getText());
            }
            
            if (chkHangat.isSelected() == true) {
                param.put("hangat", "V");
            } else {
                param.put("hangat", "");
            }
            
            if (chkDingin.isSelected() == true) {
                param.put("dingin", "V");
            } else {
                param.put("dingin", "");
            }
            
            if (cmbUdemExtremitas.getSelectedIndex() == 2) {
                if (TketUdem.getText().equals("")) {
                    param.put("udemExtrem", cmbUdemExtremitas.getSelectedItem().toString() + ", ................");
                } else {
                    param.put("udemExtrem", cmbUdemExtremitas.getSelectedItem().toString() + ", " + TketUdem.getText());
                }
            } else {
                param.put("udemExtrem", cmbUdemExtremitas.getSelectedItem().toString());
            }
            
            if (TlainlainUdem.getText().equals("")) {
                param.put("lainlainUdem", "................");
            } else {
                param.put("lainlainUdem", TlainlainUdem.getText());
            }
            
            if (TpemeriksaanPenunjang.getText().equals("")) {
                param.put("pemerPenunjang", "................");
            } else {
                param.put("pemerPenunjang", TpemeriksaanPenunjang.getText());
            }
            
            if (TdiagUtama.getText().equals("")) {
                param.put("diagUtama", "................");
            } else {
                param.put("diagUtama", TdiagUtama.getText());
            }
            
            if (TdiagKomplikasi.getText().equals("")) {
                param.put("diagKomplikasi", "................");
            } else {
                param.put("diagKomplikasi", TdiagKomplikasi.getText());
            }
            
            if (TdiagTambahan.getText().equals("")) {
                param.put("diagTambahan", "................");
            } else {
                param.put("diagTambahan", TdiagTambahan.getText());
            }
            
            if (chkTglPelaksanaan.isSelected() == true) {
                param.put("tglPelaksanaan", Valid.SetTglINDONESIA(Valid.SetTgl(TtglPelaksanaan.getSelectedItem() + "")));
            } else {
                param.put("tglPelaksanaan", "................");
            }
            
            if (chkRenal.isSelected() == true) {
                param.put("renal", "V");
            } else {
                param.put("renal", "");
            }
            
            if (chkTerapi.isSelected() == true) {
                param.put("terapi", "V");
            } else {
                param.put("terapi", "");
            }
            
            if (chkDialisisKon.isSelected() == true) {
                param.put("dialisis", "V");
            } else {
                param.put("dialisis", "");
            }
            
            if (chkSled.isSelected() == true) {
                param.put("sled", "V");
            } else {
                param.put("sled", "");
            }
            
            if (chkCrrt.isSelected() == true) {
                param.put("crrt", "V");
            } else {
                param.put("crrt", "");
            }
            
            if (chkHf.isSelected() == true) {
                param.put("hf", "V");
            } else {
                param.put("hf", "");
            }
            
            if (chkCito.isSelected() == true) {
                param.put("cito", "V");
            } else {
                param.put("cito", "");
            }
            
            if (chkElektif.isSelected() == true) {
                param.put("elektif", "V");
            } else {
                param.put("elektif", "");
            }
            
            if (chkPreparatif.isSelected() == true) {
                param.put("preparatif", "V");
            } else {
                param.put("preparatif", "");
            }
            
            if (chkTraveling.isSelected() == true) {
                param.put("traveling", "V");
            } else {
                param.put("traveling", "");
            }
            
            if (chkRegulerTindk.isSelected() == true) {
                param.put("regTindakan", "V");
            } else {
                param.put("regTindakan", "");
            }
            
            if (TlamaHd.getText().equals("")) {
                param.put("lamaHD", "..........");
            } else {
                param.put("lamaHD", TlamaHd.getText());
            }
            
            if (Tqb.getText().equals("")) {
                param.put("qb", "..........");
            } else {
                param.put("qb", Tqb.getText());
            }
            
            if (Tqd.getText().equals("")) {
                param.put("qd", "..........");
            } else {
                param.put("qd", Tqd.getText());
            }
            
            if (TupGoal.getText().equals("")) {
                param.put("upGoal", "..........");
            } else {
                param.put("upGoal", TupGoal.getText());
            }
            
            if (Tluas.getText().equals("")) {
                param.put("luas", "..........");
            } else {
                param.put("luas", Tluas.getText());
            }
            
            if (chkLowFix.isSelected() == true) {
                param.put("low", "V");
            } else {
                param.put("low", "");
            }
            
            if (chkHigh.isSelected() == true) {
                param.put("high", "V");
            } else {
                param.put("high", "");
            }
            
            if (Tantikoagulan.getText().equals("")) {
                param.put("antiko", "................");
            } else {
                param.put("antiko", Tantikoagulan.getText());
            }
            
            if (chkAvShunt.isSelected() == true) {
                param.put("avShunt", "V");
            } else {
                param.put("avShunt", "");
            }
            
            if (chkGraft.isSelected() == true) {
                param.put("graft", "V");
            } else {
                param.put("graft", "");
            }
            
            if (chkTunnel.isSelected() == true) {
                param.put("tunel", "V");
            } else {
                param.put("tunel", "");
            }
            
            if (chkTemporer.isSelected() == true) {
                param.put("tempor", "V");
            } else {
                param.put("tempor", "");
            }
            
            if (chkFemoral.isSelected() == true) {
                param.put("femo", "V");
            } else {
                param.put("femo", "");
            }
            
            if (Tprofiling.getText().equals("")) {
                param.put("profil", "................");
            } else {
                param.put("profil", Tprofiling.getText());
            }
            
            if (Ttransfusi.getText().equals("")) {
                param.put("transfusi", "................");
            } else {
                param.put("transfusi", Ttransfusi.getText());
            }
            
            if (Teritropoetin.getText().equals("")) {
                param.put("eri", "................");
            } else {
                param.put("eri", Teritropoetin.getText());
            }
            
            param.put("namaDiisi", TnmPengisi.getText());
            
            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isi = "", tglSimpan = "", jamSimpan = "";
                tglSimpan = Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from asesmen_awal_medis_hemodialisis where "
                        + "no_rawat='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString() + "'");
                jamSimpan = Sequel.cariIsi("select time(waktu_simpan) from asesmen_awal_medis_hemodialisis where "
                        + "no_rawat='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString() + "'");
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));

                if (nipPengisi.equals("") || nipPengisi.equals("-") || nipPengisi.equals("--")) {
                    param.put("lokasiQr", "");
                } else {
                    isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Assesmen Awal Medis Hemodialisis", TnmPengisi.getText(),
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                    param.put("lokasiQr", Sequel.cariFolderTte() + File.separator + "QRTte.jpg");
                }

                Valid.MyReportDataSource("rptAsesmenAwalMedisHDQr1.jasper", "report", "::[ Assesmen Awal Medis Hemodialisis (hal. 1) ]::", dataSource, param);
                Valid.MyReport("rptAsesmenAwalMedisHDQr2.jasper", "report", "::[ Assesmen Awal Medis Hemodialisis (hal. 2) ]::",
                        "SELECT now() tanggal", param);

                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            } else {                
                Valid.MyReportDataSource("rptAsesmenAwalMedisHD1.jasper", "report", "::[ Assesmen Awal Medis Hemodialisis (hal. 1) ]::", dataSource, param);
                Valid.MyReport("rptAsesmenAwalMedisHD2.jasper", "report", "::[ Assesmen Awal Medis Hemodialisis (hal. 2) ]::",
                        "SELECT now() tanggal", param);
            }
            BtnBatalActionPerformed(null);
            TabRawat.setSelectedIndex(1);
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
        if (Sequel.cariInteger("select count(-1) from asesmen_awal_medis_hemodialisis where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
            tampil();
        } else if (Sequel.cariInteger("select count(-1) from asesmen_awal_medis_hemodialisis where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMAsesmenAwalMedisHemodialisis");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void TriwPenyktSkgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwPenyktSkgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            chkHipertensiRPD.requestFocus();
        }
    }//GEN-LAST:event_TriwPenyktSkgKeyPressed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataObat();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObatMouseClicked

    private void TriwAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwAlergiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbNyeri.requestFocus();
        }
    }//GEN-LAST:event_TriwAlergiKeyPressed

    private void TpemeriksaanPenunjangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpemeriksaanPenunjangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TdiagUtama.requestFocus();
        }
    }//GEN-LAST:event_TpemeriksaanPenunjangKeyPressed

    private void TketYaLainyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketYaLainyaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdiagRujukan.requestFocus();
        }
    }//GEN-LAST:event_TketYaLainyaKeyPressed

    private void TdiagRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagRujukanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cmbTidakRujukan.isEnabled() == true) {
                cmbTidakRujukan.requestFocus();
            } else {
                BtnDokter.requestFocus();
            }
        }
    }//GEN-LAST:event_TdiagRujukanKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        initDokter();
        akses.setform("RMAsesmenAwalMedisHemodialisis");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSupervisorActionPerformed
        pilihan = 0;
        pilihan = 1;
        initPetugas();
        akses.setform("RMAsesmenAwalMedisHemodialisis");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnSupervisorActionPerformed

    private void BtnPengisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPengisiActionPerformed
        pilihan = 0;
        pilihan = 2;
        initPetugas();
        akses.setform("RMAsesmenAwalMedisHemodialisis");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPengisiActionPerformed

    private void TdiantarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiantarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnDokter.requestFocus();
        }
    }//GEN-LAST:event_TdiantarKeyPressed

    private void TkeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeluhanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TriwPenyktSkg.requestFocus();
        }
    }//GEN-LAST:event_TkeluhanKeyPressed

    private void TketLainRPDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketLainRPDKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPernah.requestFocus();
        }
    }//GEN-LAST:event_TketLainRPDKeyPressed

    private void TkapanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkapanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdimana.requestFocus();
        }
    }//GEN-LAST:event_TkapanKeyPressed

    private void TdimanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdimanaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdiagnosis.requestFocus();
        }
    }//GEN-LAST:event_TdimanaKeyPressed

    private void TdiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmObat.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosisKeyPressed

    private void TnmObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdosis.requestFocus();
        }
    }//GEN-LAST:event_TnmObatKeyPressed

    private void TdosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TwktPenggunaan.requestFocus();
        }
    }//GEN-LAST:event_TdosisKeyPressed

    private void TketLainRPKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketLainRPKKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TriwAlergi.requestFocus();
        }
    }//GEN-LAST:event_TketLainRPKKeyPressed

    private void TlokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlokasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tintensitas.requestFocus();
        }
    }//GEN-LAST:event_TlokasiKeyPressed

    private void TintensitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TintensitasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tskor.requestFocus();
        }
    }//GEN-LAST:event_TintensitasKeyPressed

    private void TskorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJenis.requestFocus();
        }
    }//GEN-LAST:event_TskorKeyPressed

    private void TgcsEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsEKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TgcsM.requestFocus();
        }
    }//GEN-LAST:event_TgcsEKeyPressed

    private void TgcsMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsMKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TgcsV.requestFocus();
        }
    }//GEN-LAST:event_TgcsMKeyPressed

    private void TgcsVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsVKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbTindakan.requestFocus();
        }
    }//GEN-LAST:event_TgcsVKeyPressed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttb.requestFocus();
        }
    }//GEN-LAST:event_TbbKeyPressed

    private void TtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttd.requestFocus();
        }
    }//GEN-LAST:event_TtbKeyPressed

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
            Taxila.requestFocus();
        }
    }//GEN-LAST:event_TrespiKeyPressed

    private void TaxilaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TaxilaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trektal.requestFocus();
        }
    }//GEN-LAST:event_TaxilaKeyPressed

    private void TrektalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrektalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbAnemis.requestFocus();
        }
    }//GEN-LAST:event_TrektalKeyPressed

    private void TketAnemisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketAnemisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbIkterik.requestFocus();
        }
    }//GEN-LAST:event_TketAnemisKeyPressed

    private void TketIkterikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketIkterikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkAnisokor.requestFocus();
        }
    }//GEN-LAST:event_TketIkterikKeyPressed

    private void TdiameterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiameterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbUdemPalpe.requestFocus();
        }
    }//GEN-LAST:event_TdiameterKeyPressed

    private void TtonsilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtonsilKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tfaring.requestFocus();
        }
    }//GEN-LAST:event_TtonsilKeyPressed

    private void TfaringKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfaringKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlidah.requestFocus();
        }
    }//GEN-LAST:event_TfaringKeyPressed

    private void TlidahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlidahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tbibir.requestFocus();
        }
    }//GEN-LAST:event_TlidahKeyPressed

    private void TbibirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbibirKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tjvp.requestFocus();
        }
    }//GEN-LAST:event_TbibirKeyPressed

    private void TjvpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjvpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbKelenjar.requestFocus();
        }
    }//GEN-LAST:event_TjvpKeyPressed

    private void TketKelenjarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketKelenjarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbKaku.requestFocus();
        }
    }//GEN-LAST:event_TketKelenjarKeyPressed

    private void TketAsimetrisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketAsimetrisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ts1s2.requestFocus();
        }
    }//GEN-LAST:event_TketAsimetrisKeyPressed

    private void Ts1s2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ts1s2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkReguler.requestFocus();
        }
    }//GEN-LAST:event_Ts1s2KeyPressed

    private void TketMurmurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketMurmurKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TlainlainMurmur.requestFocus();
        }
    }//GEN-LAST:event_TketMurmurKeyPressed

    private void TlainlainMurmurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainlainMurmurKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TsuaraNafas.requestFocus();
        }
    }//GEN-LAST:event_TlainlainMurmurKeyPressed

    private void TsuaraNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuaraNafasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbRonchi.requestFocus();
        }
    }//GEN-LAST:event_TsuaraNafasKeyPressed

    private void TketRonchiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketRonchiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbWhezing.requestFocus();
        }
    }//GEN-LAST:event_TketRonchiKeyPressed

    private void TketWhezingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketWhezingKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbDistended.requestFocus();
        }
    }//GEN-LAST:event_TketWhezingKeyPressed

    private void TlokasiNyeriTekanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlokasiNyeriTekanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Thepar.requestFocus();
        }
    }//GEN-LAST:event_TlokasiNyeriTekanKeyPressed

    private void TheparKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TheparKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlien.requestFocus();
        }
    }//GEN-LAST:event_TheparKeyPressed

    private void TlienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkHangat.requestFocus();
        }
    }//GEN-LAST:event_TlienKeyPressed

    private void TketUdemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketUdemKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TlainlainUdem.requestFocus();
        }
    }//GEN-LAST:event_TketUdemKeyPressed

    private void TlainlainUdemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainlainUdemKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TpemeriksaanPenunjang.requestFocus();
        }
    }//GEN-LAST:event_TlainlainUdemKeyPressed

    private void TdiagUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagUtamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdiagKomplikasi.requestFocus();
        }
    }//GEN-LAST:event_TdiagUtamaKeyPressed

    private void TdiagKomplikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagKomplikasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdiagTambahan.requestFocus();
        }
    }//GEN-LAST:event_TdiagKomplikasiKeyPressed

    private void TdiagTambahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagTambahanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTglPelaksanaan.requestFocus();
        }
    }//GEN-LAST:event_TdiagTambahanKeyPressed

    private void TlamaHdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlamaHdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tqb.requestFocus();
        }
    }//GEN-LAST:event_TlamaHdKeyPressed

    private void TqbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TqbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tqd.requestFocus();
        }
    }//GEN-LAST:event_TqbKeyPressed

    private void TqdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TqdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TupGoal.requestFocus();
        }
    }//GEN-LAST:event_TqdKeyPressed

    private void TupGoalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TupGoalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tluas.requestFocus();
        }
    }//GEN-LAST:event_TupGoalKeyPressed

    private void TluasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TluasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkLowFix.requestFocus();
        }
    }//GEN-LAST:event_TluasKeyPressed

    private void TantikoagulanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TantikoagulanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkAvShunt.requestFocus();
        }
    }//GEN-LAST:event_TantikoagulanKeyPressed

    private void TprofilingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprofilingKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttransfusi.requestFocus();
        }
    }//GEN-LAST:event_TprofilingKeyPressed

    private void TtransfusiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtransfusiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Teritropoetin.requestFocus();
        }
    }//GEN-LAST:event_TtransfusiKeyPressed

    private void TeritropoetinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TeritropoetinKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnPengisi.requestFocus();
        }
    }//GEN-LAST:event_TeritropoetinKeyPressed

    private void cmbRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRujukanActionPerformed
        cmbYaRujukan.setSelectedIndex(0);
        TketYaLainya.setText("");
        TdiagRujukan.setText("");
        cmbTidakRujukan.setSelectedIndex(0);
        Tdiantar.setText("");
        
        if (cmbRujukan.getSelectedIndex() == 1) {
            cmbYaRujukan.setEnabled(true);
            cmbYaRujukan.requestFocus();
            TketYaLainya.setEnabled(false);
            TdiagRujukan.setEnabled(true);
            cmbTidakRujukan.setEnabled(false);
            Tdiantar.setEnabled(false);
        } else if (cmbRujukan.getSelectedIndex() == 2) {
            cmbYaRujukan.setEnabled(false);
            TketYaLainya.setEnabled(false);
            TdiagRujukan.setEnabled(false);
            cmbTidakRujukan.setEnabled(true);
            cmbTidakRujukan.requestFocus();
            Tdiantar.setEnabled(false);
        } else {
            cmbYaRujukan.setEnabled(false);
            TketYaLainya.setEnabled(false);
            TdiagRujukan.setEnabled(false);
            cmbTidakRujukan.setEnabled(false);
            Tdiantar.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRujukanActionPerformed

    private void cmbYaRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbYaRujukanActionPerformed
        TketYaLainya.setText("");
        if (cmbYaRujukan.getSelectedIndex() == 0) {
            TketYaLainya.setEnabled(false);            
        } else {
            TketYaLainya.setEnabled(true);
            TketYaLainya.requestFocus();
        }
    }//GEN-LAST:event_cmbYaRujukanActionPerformed

    private void cmbTidakRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTidakRujukanActionPerformed
        Tdiantar.setText("");
        if (cmbTidakRujukan.getSelectedIndex() == 2) {
            Tdiantar.setEnabled(true);
            Tdiantar.requestFocus();
        } else {
            Tdiantar.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTidakRujukanActionPerformed

    private void chkLainRPDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainRPDActionPerformed
        TketLainRPD.setText("");
        if (chkLainRPD.isSelected() == true) {
            TketLainRPD.setEnabled(true);
            TketLainRPD.requestFocus();
        } else {
            TketLainRPD.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainRPDActionPerformed

    private void cmbPernahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPernahActionPerformed
        Tkapan.setText("");
        Tdimana.setText("");
        Tdiagnosis.setText("");
        if (cmbPernah.getSelectedIndex() == 1) {
            Tkapan.setEnabled(true);
            Tkapan.requestFocus();
            Tdimana.setEnabled(true);
            Tdiagnosis.setEnabled(true);
        } else {
            Tkapan.setEnabled(false);
            Tdimana.setEnabled(false);
            Tdiagnosis.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPernahActionPerformed

    private void chkLainRPKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainRPKActionPerformed
        TketLainRPK.setText("");
        if (chkLainRPK.isSelected() == true) {
            TketLainRPK.setEnabled(true);
            TketLainRPK.requestFocus();
        } else {
            TketLainRPK.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainRPKActionPerformed

    private void cmbNyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNyeriActionPerformed
        Tlokasi.setText("");
        Tintensitas.setText("");
        if (cmbNyeri.getSelectedIndex() == 1) {
            Tlokasi.setEnabled(true);
            Tlokasi.requestFocus();
            Tintensitas.setEnabled(true);
        } else {
            Tlokasi.setEnabled(false);
            Tintensitas.setEnabled(false);
        }
    }//GEN-LAST:event_cmbNyeriActionPerformed

    private void cmbAnemisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAnemisActionPerformed
        TketAnemis.setText("");
        if (cmbAnemis.getSelectedIndex() == 2) {
            TketAnemis.setEnabled(true);
            TketAnemis.requestFocus();
        } else {
            TketAnemis.setEnabled(false);
        }
    }//GEN-LAST:event_cmbAnemisActionPerformed

    private void cmbIkterikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbIkterikActionPerformed
        TketIkterik.setText("");
        if (cmbIkterik.getSelectedIndex() == 2) {
            TketIkterik.setEnabled(true);
            TketIkterik.requestFocus();
        } else {
            TketIkterik.setEnabled(false);
        }
    }//GEN-LAST:event_cmbIkterikActionPerformed

    private void cmbKelenjarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKelenjarActionPerformed
        TketKelenjar.setText("");
        if (cmbKelenjar.getSelectedIndex() == 2) {
            TketKelenjar.setEnabled(true);
            TketKelenjar.requestFocus();
        } else {
            TketKelenjar.setEnabled(false);
        }
    }//GEN-LAST:event_cmbKelenjarActionPerformed

    private void chkAsimetrisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAsimetrisActionPerformed
        TketAsimetris.setText("");
        if (chkAsimetris.isSelected() == true) {
            TketAsimetris.setEnabled(true);
            TketAsimetris.requestFocus();
        } else {
            TketAsimetris.setEnabled(false);
        }
    }//GEN-LAST:event_chkAsimetrisActionPerformed

    private void cmbRonchiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRonchiActionPerformed
        TketRonchi.setText("");
        if (cmbRonchi.getSelectedIndex() == 2) {
            TketRonchi.setEnabled(true);
            TketRonchi.requestFocus();
        } else {
            TketRonchi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRonchiActionPerformed

    private void cmbNyeriTekanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNyeriTekanActionPerformed
        TlokasiNyeriTekan.setText("");
        if (cmbNyeriTekan.getSelectedIndex() == 2) {
            TlokasiNyeriTekan.setEnabled(true);
            TlokasiNyeriTekan.requestFocus();
        } else {
            TlokasiNyeriTekan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbNyeriTekanActionPerformed

    private void cmbUdemExtremitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUdemExtremitasActionPerformed
        TketUdem.setText("");
        if (cmbUdemExtremitas.getSelectedIndex() == 2) {
            TketUdem.setEnabled(true);
            TketUdem.requestFocus();
        } else {
            TketUdem.setEnabled(false);
        }
    }//GEN-LAST:event_cmbUdemExtremitasActionPerformed

    private void chkTglPelaksanaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglPelaksanaanActionPerformed
        TtglPelaksanaan.setDate(new Date());
        chkRenal.setSelected(false);
        chkTerapi.setSelected(false);
        chkDialisisKon.setSelected(false);
        chkSled.setSelected(false);
        chkCrrt.setSelected(false);
        chkHf.setSelected(false);
        chkCito.setSelected(false);
        chkElektif.setSelected(false);
        chkPreparatif.setSelected(false);
        chkTraveling.setSelected(false);
        chkRegulerTindk.setSelected(false);

        if (chkTglPelaksanaan.isSelected() == true) {
            TtglPelaksanaan.setEnabled(true);
            TtglPelaksanaan.requestFocus();
            chkRenal.setEnabled(true);
            chkTerapi.setEnabled(true);
            chkDialisisKon.setEnabled(true);
            chkSled.setEnabled(true);
            chkCrrt.setEnabled(true);
            chkHf.setEnabled(true);
            chkCito.setEnabled(true);
            chkElektif.setEnabled(true);
            chkPreparatif.setEnabled(true);
            chkTraveling.setEnabled(true);
            chkRegulerTindk.setEnabled(true);
        } else {
            TtglPelaksanaan.setEnabled(false);
            chkRenal.setEnabled(false);
            chkTerapi.setEnabled(false);
            chkDialisisKon.setEnabled(false);
            chkSled.setEnabled(false);
            chkCrrt.setEnabled(false);
            chkHf.setEnabled(false);
            chkCito.setEnabled(false);
            chkElektif.setEnabled(false);
            chkPreparatif.setEnabled(false);
            chkTraveling.setEnabled(false);
            chkRegulerTindk.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglPelaksanaanActionPerformed

    private void cmbWhezingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbWhezingActionPerformed
        TketWhezing.setText("");
        if (cmbWhezing.getSelectedIndex() == 2) {
            TketWhezing.setEnabled(true);
            TketWhezing.requestFocus();
        } else {
            TketWhezing.setEnabled(false);
        }
    }//GEN-LAST:event_cmbWhezingActionPerformed

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

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMAsesmenAwalMedisHemodialisis");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void BtnBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruActionPerformed
        emptTeksRiwayatObat();
        TnmObat.requestFocus();
    }//GEN-LAST:event_BtnBaruActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            if (!TnmObat.getText().equals("") && !Tdosis.getText().equals("") && !TwktPenggunaan.getText().equals("")) {
                tabMode1.addRow(new String[]{TNoRw.getText(), TnmObat.getText(), Tdosis.getText(),
                    TwktPenggunaan.getText(), Sequel.cariIsi("select now()")});
                BtnBaruActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnTambahActionPerformed

    private void BtnHapusObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusObatActionPerformed
        if (tbObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data obat yang bisa dihapus.");
        } else {
            if (tbObat.getSelectedRow() > -1) {
                int row = tbObat.convertRowIndexToModel(tbObat.getSelectedRow());
                tabMode1.removeRow(row);
                BtnBaruActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel obat.");
                tbObat.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusObatActionPerformed

    private void BtnGantiTtvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiTtvActionPerformed
        if (tbObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data obat yang bisa diganti.");
        } else {
            if (tbObat.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else {
                    int row = tbObat.convertRowIndexToModel(tbObat.getSelectedRow());
                    tabMode1.setValueAt(TNoRw.getText(), row, 0);
                    tabMode1.setValueAt(TnmObat.getText(), row, 1);
                    tabMode1.setValueAt(Tdosis.getText(), row, 2);
                    tabMode1.setValueAt(TwktPenggunaan.getText(), row, 3);
                    tabMode1.setValueAt(Sequel.cariIsi("select now()"), row, 4);

                    BtnBaruActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel obat.");
                tbObat.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiTtvActionPerformed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "'") > 0) {
                nipDokter = akses.getkode();
                TnmDokter.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipDokter + "'"));
            } else {
                nipDokter = "-";
                TnmDokter.setText("-");
            }
        } else {
            nipDokter = "-";
            TnmDokter.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMAsesmenAwalMedisHemodialisis");
        DlgCatatanResepBiasaAntibiotik form = new DlgCatatanResepBiasaAntibiotik(null, false);
        form.isCek();
        form.setData(TNoRw.getText(), "ranap");
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnResepActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenAwalMedisHemodialisis dialog = new RMAsesmenAwalMedisHemodialisis(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBaru;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnGantiTtv;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusObat;
    private widget.Button BtnKeluar;
    private widget.Button BtnPasteHasil;
    private widget.Button BtnPengisi;
    private widget.Button BtnPrint;
    private widget.Button BtnResep;
    private widget.Button BtnSimpan;
    private widget.Button BtnSupervisor;
    private widget.Button BtnTambah;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private usu.widget.glass.PanelGlass PanelWall;
    private usu.widget.glass.PanelGlass PanelWall1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll8;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tantikoagulan;
    private widget.TextBox Taxila;
    private widget.TextBox Tbb;
    private widget.TextBox Tbibir;
    private widget.TextBox TdiagKomplikasi;
    private widget.TextBox TdiagRujukan;
    private widget.TextBox TdiagTambahan;
    private widget.TextBox TdiagUtama;
    private widget.TextBox Tdiagnosis;
    private widget.TextBox Tdiameter;
    private widget.TextBox Tdiantar;
    private widget.TextBox Tdimana;
    private widget.TextBox Tdosis;
    private widget.TextBox Teritropoetin;
    private widget.TextBox Tfaring;
    private widget.TextBox TgcsE;
    private widget.TextBox TgcsM;
    private widget.TextBox TgcsV;
    private widget.TextBox Thepar;
    private widget.TextBox Tintensitas;
    private widget.TextBox Tjvp;
    private widget.TextBox Tkapan;
    private widget.TextBox Tkeluhan;
    private widget.TextBox TketAnemis;
    private widget.TextBox TketAsimetris;
    private widget.TextBox TketIkterik;
    private widget.TextBox TketKelenjar;
    private widget.TextBox TketLainRPD;
    private widget.TextBox TketLainRPK;
    private widget.TextBox TketMurmur;
    private widget.TextBox TketRonchi;
    private widget.TextBox TketUdem;
    private widget.TextBox TketWhezing;
    private widget.TextBox TketYaLainya;
    private widget.TextBox TlainlainMurmur;
    private widget.TextBox TlainlainUdem;
    private widget.TextBox TlamaHd;
    private widget.TextBox Tlidah;
    private widget.TextBox Tlien;
    private widget.TextBox Tlokasi;
    private widget.TextBox TlokasiNyeriTekan;
    private widget.TextBox Tluas;
    private widget.TextBox Tnadi;
    private widget.TextBox TnmDokter;
    private widget.TextBox TnmObat;
    private widget.TextBox TnmPengisi;
    private widget.TextBox TnmSupervisor;
    private widget.TextArea TpemeriksaanPenunjang;
    private widget.TextBox Tprofiling;
    private widget.TextBox Tqb;
    private widget.TextBox Tqd;
    private widget.TextBox Trektal;
    private widget.TextBox Trespi;
    private widget.TextBox TrgRawat;
    private widget.TextArea TriwAlergi;
    private widget.TextArea TriwPenyktSkg;
    private widget.TextBox Ts1s2;
    private widget.TextBox Tskor;
    private widget.TextBox TsuaraNafas;
    private widget.TextBox Ttb;
    private widget.TextBox Ttd;
    private widget.Tanggal TtglAnam;
    private widget.Tanggal TtglAses;
    private widget.Tanggal TtglPelaksanaan;
    private widget.TextBox Ttonsil;
    private widget.TextBox Ttransfusi;
    private widget.TextBox TupGoal;
    private widget.TextBox TwktPenggunaan;
    public widget.CekBox chkAnisokor;
    public widget.CekBox chkAsimetris;
    public widget.CekBox chkAsmaRPD;
    public widget.CekBox chkAsmaRPK;
    public widget.CekBox chkAvShunt;
    public widget.CekBox chkCito;
    public widget.CekBox chkCrrt;
    public widget.CekBox chkDialisisKon;
    public widget.CekBox chkDingin;
    public widget.CekBox chkDmRPD;
    public widget.CekBox chkDmRPK;
    public widget.CekBox chkElektif;
    public widget.CekBox chkFemoral;
    public widget.CekBox chkGinjal;
    public widget.CekBox chkGraft;
    public widget.CekBox chkHangat;
    public widget.CekBox chkHf;
    public widget.CekBox chkHigh;
    public widget.CekBox chkHipertensiRPD;
    public widget.CekBox chkHipertensiRPK;
    public widget.CekBox chkIreguler;
    public widget.CekBox chkIsokor;
    public widget.CekBox chkJantung;
    public widget.CekBox chkLainRPD;
    public widget.CekBox chkLainRPK;
    public widget.CekBox chkLiver;
    public widget.CekBox chkLowFix;
    public widget.CekBox chkPjk;
    public widget.CekBox chkPreparatif;
    public widget.CekBox chkReguler;
    public widget.CekBox chkRegulerTindk;
    public widget.CekBox chkRenal;
    public widget.CekBox chkSaya;
    public widget.CekBox chkSimetris;
    public widget.CekBox chkSled;
    public widget.CekBox chkStroke;
    public widget.CekBox chkTBparu;
    public widget.CekBox chkTemporer;
    public widget.CekBox chkTerapi;
    public widget.CekBox chkTglPelaksanaan;
    public widget.CekBox chkTraveling;
    public widget.CekBox chkTunnel;
    private widget.ComboBox cmbAnemis;
    private widget.ComboBox cmbAsites;
    private widget.ComboBox cmbDistended;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbGizi;
    private widget.ComboBox cmbIkterik;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJenis;
    private widget.ComboBox cmbKaku;
    private widget.ComboBox cmbKeadaan;
    private widget.ComboBox cmbKelenjar;
    private widget.ComboBox cmbMeteor;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbNyeri;
    private widget.ComboBox cmbNyeriTekan;
    private widget.ComboBox cmbPeristaltik;
    private widget.ComboBox cmbPernah;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbRonchi;
    private widget.ComboBox cmbRujukan;
    private widget.ComboBox cmbTidakRujukan;
    private widget.ComboBox cmbTindakan;
    private widget.ComboBox cmbUdemExtremitas;
    private widget.ComboBox cmbUdemPalpe;
    private widget.ComboBox cmbWhezing;
    private widget.ComboBox cmbYaRujukan;
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
    private widget.Label jLabel13;
    private widget.Label jLabel14;
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
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
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
    private widget.Label label100;
    private widget.Label label101;
    private widget.Label label102;
    private widget.Label label103;
    private widget.Label label104;
    private widget.Label label19;
    private widget.Label label22;
    private widget.Label label98;
    private widget.Label label99;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbAsesmen;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

     private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select aa.*, p.no_rkm_medis, p.nm_pasien, date_format(aa.tgl_asesmen,'%d-%m-%Y') tglAses, time_format(aa.jam_asesmen,'%H:%i WITA') jamAses, "
                    + "pg1.nama nmDokter, pg2.nama nmSuper, pg3.nama nmPengisi from asesmen_awal_medis_hemodialisis aa "
                    + "inner join reg_periksa rp on rp.no_rawat=aa.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg1 on pg1.nik=aa.nip_dokter_memeriksa inner join pegawai pg2 on pg2.nik=aa.nip_supervisor "
                    + "inner join pegawai pg3 on pg3.nik=aa.nip_pengisi WHERE "
                    + "aa.tgl_asesmen BETWEEN ? AND ? AND aa.no_rawat LIKE ? OR "
                    + "aa.tgl_asesmen BETWEEN ? AND ? AND p.no_rkm_medis LIKE ? OR "
                    + "aa.tgl_asesmen BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                    + "aa.tgl_asesmen BETWEEN ? AND ? AND pg1.nama LIKE ? OR "
                    + "aa.tgl_asesmen BETWEEN ? AND ? AND pg2.nama LIKE ? OR "
                    + "aa.tgl_asesmen BETWEEN ? AND ? AND pg3.nama LIKE ? ORDER BY aa.tgl_asesmen desc, aa.jam_asesmen desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglAses"),
                        rs.getString("jamAses"),
                        rs.getString("ruang_rawat"),
                        rs.getString("nmDokter"),
                        rs.getString("nmSuper"),
                        rs.getString("nmPengisi"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("jam_asesmen"),
                        rs.getString("rujukan"),
                        rs.getString("ya_rujukan"),
                        rs.getString("ket_ya_rujukan"),
                        rs.getString("diagnosa_rujukan"),
                        rs.getString("tidak_rujukan"),
                        rs.getString("ket_diantar"),
                        rs.getString("nip_dokter_memeriksa"),
                        rs.getString("nip_supervisor"),
                        rs.getString("tgl_anamnesa"),
                        rs.getString("jam_anamnesa"),
                        rs.getString("keluhan"),
                        rs.getString("riw_penyakit_sekarang"),
                        rs.getString("hipertensi"),
                        rs.getString("dm"),
                        rs.getString("pjk"),
                        rs.getString("asma"),
                        rs.getString("stroke"),
                        rs.getString("liver"),
                        rs.getString("ginjal"),
                        rs.getString("tb_paru"),
                        rs.getString("lainya"),
                        rs.getString("ket_lainya"),
                        rs.getString("pernah_dirawat"),
                        rs.getString("ket_kapan"),
                        rs.getString("ket_dimana"),
                        rs.getString("diagnosis"),
                        rs.getString("riwkel_hipertensi"),
                        rs.getString("riwkel_dm"),
                        rs.getString("riwkel_jantung"),
                        rs.getString("riwkel_asma"),
                        rs.getString("riwkel_lainya"),
                        rs.getString("ket_riwkel_lainya"),
                        rs.getString("riw_alergi"),
                        rs.getString("nyeri"),
                        rs.getString("ket_lokasi"),
                        rs.getString("ket_intensitas"),
                        rs.getString("skor"),
                        rs.getString("jenis"),
                        rs.getString("keadaan_umum"),
                        rs.getString("gizi"),
                        rs.getString("gcs_e"),
                        rs.getString("gcs_m"),
                        rs.getString("gcs_v"),
                        rs.getString("tindakan_resus"),
                        rs.getString("bb"),
                        rs.getString("tb"),
                        rs.getString("tensi"),
                        rs.getString("nadi"),
                        rs.getString("respirasi"),
                        rs.getString("axila"),
                        rs.getString("rektal"),
                        rs.getString("anemis"),
                        rs.getString("ket_anemis_ada"),
                        rs.getString("ikterik"),
                        rs.getString("ket_anemis_ikterik"),
                        rs.getString("anisokor"),
                        rs.getString("isokor"),
                        rs.getString("diameter"),
                        rs.getString("udem_palpebra"),
                        rs.getString("tonsil"),
                        rs.getString("faring"),
                        rs.getString("lidah"),
                        rs.getString("bibir"),
                        rs.getString("jvp"),
                        rs.getString("kelenjar_limfo"),
                        rs.getString("ket_kelenjar_limfo_ada"),
                        rs.getString("kaku_kuduk"),
                        rs.getString("simetris"),
                        rs.getString("asimetris"),
                        rs.getString("ket_asimetris"),
                        rs.getString("s1s2"),
                        rs.getString("reguler"),
                        rs.getString("ireguler"),
                        rs.getString("murmur"),
                        rs.getString("lain_lain_murmur"),
                        rs.getString("suara_nafas"),
                        rs.getString("ronchi"),
                        rs.getString("ket_ronchi_ada"),
                        rs.getString("whezing"),
                        rs.getString("ket_whezing_ada"),
                        rs.getString("distended"),
                        rs.getString("meteorismus"),
                        rs.getString("peristaltik"),
                        rs.getString("asites"),
                        rs.getString("nyeri_tekan"),
                        rs.getString("ket_lokasi_nyeri_tekan"),
                        rs.getString("hepar"),
                        rs.getString("lien"),
                        rs.getString("hangat"),
                        rs.getString("dingin"),
                        rs.getString("udem"),
                        rs.getString("ket_udem_ada"),
                        rs.getString("lain_lain_udem"),
                        rs.getString("pemeriksaan_penunjang"),
                        rs.getString("diag_utama"),
                        rs.getString("diag_komplikasi"),
                        rs.getString("diag_tambahan"),
                        rs.getString("cek_tgl_pelaksanaan"),
                        rs.getString("tgl_pelaksanaan"),
                        rs.getString("renal"),
                        rs.getString("terapi"),
                        rs.getString("dialisis"),
                        rs.getString("sled"),
                        rs.getString("crrt"),
                        rs.getString("hf"),
                        rs.getString("cito"),
                        rs.getString("elektif"),
                        rs.getString("preparatif"),
                        rs.getString("traveling"),
                        rs.getString("reguler_tindakan"),
                        rs.getString("lama_hd"),
                        rs.getString("qb"),
                        rs.getString("qd"),
                        rs.getString("up_goal"),
                        rs.getString("luas_membrane"),
                        rs.getString("low_fix"),
                        rs.getString("high_flux"),
                        rs.getString("antikuagulan"),
                        rs.getString("av_shunt"),
                        rs.getString("graft"),
                        rs.getString("tunnel"),
                        rs.getString("temporer"),
                        rs.getString("femoral"),
                        rs.getString("profiling"),
                        rs.getString("tranfusi_darah"),
                        rs.getString("eritropoetin"),
                        rs.getString("nip_pengisi"),
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
        TtglAses.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        cmbRujukan.setSelectedIndex(0);
        cmbYaRujukan.setSelectedIndex(0);
        TketYaLainya.setText("");
        TdiagRujukan.setText("");
        cmbTidakRujukan.setSelectedIndex(0);
        Tdiantar.setText("");
        cmbYaRujukan.setEnabled(false);
        TketYaLainya.setEnabled(false);
        TdiagRujukan.setEnabled(false);
        cmbTidakRujukan.setEnabled(false);
        Tdiantar.setEnabled(false);
        nipDokter = "-";
        TnmDokter.setText("-");
        nipSuper = "-";
        TnmSupervisor.setText("-");
        TtglAnam.setDate(new Date());
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        Tkeluhan.setText("");
        TriwPenyktSkg.setText("");
        chkHipertensiRPD.setSelected(false);
        chkDmRPD.setSelected(false);
        chkPjk.setSelected(false);
        chkAsmaRPD.setSelected(false);
        chkStroke.setSelected(false);
        chkLiver.setSelected(false);
        chkGinjal.setSelected(false);
        chkTBparu.setSelected(false);
        chkLainRPD.setSelected(false);
        TketLainRPD.setText("");
        TketLainRPD.setEnabled(false);
        cmbPernah.setSelectedIndex(0);
        Tkapan.setText("");
        Tdimana.setText("");
        Tdiagnosis.setText("");
        Tkapan.setEnabled(false);
        Tdimana.setEnabled(false);
        Tdiagnosis.setEnabled(false);
        emptTeksRiwayatObat();
        Valid.tabelKosong(tabMode1);
        chkHipertensiRPK.setSelected(false);
        chkDmRPK.setSelected(false);
        chkJantung.setSelected(false);
        chkAsmaRPK.setSelected(false);
        chkLainRPK.setSelected(false);
        TketLainRPK.setText("");
        TketLainRPK.setEnabled(false);
        TriwAlergi.setText("");
        cmbNyeri.setSelectedIndex(0);
        Tlokasi.setText("");
        Tintensitas.setText("");
        Tlokasi.setEnabled(false);
        Tintensitas.setEnabled(false);
        Tskor.setText("");
        cmbJenis.setSelectedIndex(0);
        cmbKeadaan.setSelectedIndex(0);
        cmbGizi.setSelectedIndex(0);
        TgcsE.setText("");
        TgcsM.setText("");
        TgcsV.setText("");
        cmbTindakan.setSelectedIndex(0);
        Tbb.setText("");
        Ttb.setText("");
        Ttd.setText("");
        Tnadi.setText("");
        Trespi.setText("");
        Taxila.setText("");
        Trektal.setText("");
        cmbAnemis.setSelectedIndex(0);
        TketAnemis.setText("");
        TketAnemis.setEnabled(false);
        cmbIkterik.setSelectedIndex(0);
        TketIkterik.setText("");
        TketIkterik.setEnabled(false);
        chkAnisokor.setSelected(false);
        chkIsokor.setSelected(false);
        Tdiameter.setText("");
        cmbUdemPalpe.setSelectedIndex(0);
        Ttonsil.setText("");
        Tfaring.setText("");
        Tlidah.setText("");
        Tbibir.setText("");
        Tjvp.setText("");
        cmbKelenjar.setSelectedIndex(0);
        TketKelenjar.setText("");
        TketKelenjar.setEnabled(false);
        cmbKaku.setSelectedIndex(0);
        chkSimetris.setSelected(false);
        chkAsimetris.setSelected(false);
        TketAsimetris.setText("");
        TketAsimetris.setEnabled(false);
        Ts1s2.setText("");
        chkReguler.setSelected(false);
        chkIreguler.setSelected(false);
        TketMurmur.setText("");
        TlainlainMurmur.setText("");
        TsuaraNafas.setText("");
        cmbRonchi.setSelectedIndex(0);
        TketRonchi.setText("");
        TketRonchi.setEnabled(false);
        cmbWhezing.setSelectedIndex(0);
        TketWhezing.setText("");
        TketWhezing.setEnabled(false);
        cmbDistended.setSelectedIndex(0);
        cmbMeteor.setSelectedIndex(0);
        cmbPeristaltik.setSelectedIndex(0);
        cmbAsites.setSelectedIndex(0);
        cmbNyeriTekan.setSelectedIndex(0);
        TlokasiNyeriTekan.setText("");
        TlokasiNyeriTekan.setEnabled(false);
        Thepar.setText("");
        Tlien.setText("");
        chkHangat.setSelected(false);
        chkDingin.setSelected(false);
        cmbUdemExtremitas.setSelectedIndex(0);
        TketUdem.setText("");
        TketUdem.setEnabled(false);
        TlainlainUdem.setText("");
        TpemeriksaanPenunjang.setText("");
        TdiagUtama.setText("");
        TdiagKomplikasi.setText("");
        TdiagTambahan.setText("");
        chkTglPelaksanaan.setSelected(false);
        TtglPelaksanaan.setDate(new Date());
        chkRenal.setSelected(false);
        chkTerapi.setSelected(false);
        chkDialisisKon.setSelected(false);
        chkSled.setSelected(false);
        chkCrrt.setSelected(false);
        chkHf.setSelected(false);
        chkCito.setSelected(false);
        chkElektif.setSelected(false);
        chkPreparatif.setSelected(false);
        chkTraveling.setSelected(false);
        chkRegulerTindk.setSelected(false);
        TtglPelaksanaan.setEnabled(false);
        chkRenal.setEnabled(false);
        chkTerapi.setEnabled(false);
        chkDialisisKon.setEnabled(false);
        chkSled.setEnabled(false);
        chkCrrt.setEnabled(false);
        chkHf.setEnabled(false);
        chkCito.setEnabled(false);
        chkElektif.setEnabled(false);
        chkPreparatif.setEnabled(false);
        chkTraveling.setEnabled(false);
        chkRegulerTindk.setEnabled(false);        
        TlamaHd.setText("");
        Tqb.setText("");
        Tqd.setText("");
        TupGoal.setText("");
        Tluas.setText("");
        chkLowFix.setSelected(false);
        chkHigh.setSelected(false);
        Tantikoagulan.setText("");
        chkAvShunt.setSelected(false);
        chkGraft.setSelected(false);
        chkTunnel.setSelected(false);
        chkTemporer.setSelected(false);
        chkFemoral.setSelected(false);
        Tprofiling.setText("");
        Ttransfusi.setText("");
        Teritropoetin.setText("");
        nipPengisi = "-";
        TnmPengisi.setText("-");
        chkSaya.setSelected(false);
    }

    private void getData() {
        variabelBersih();
        if (tbAsesmen.getSelectedRow() != -1) {
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            isPasien();            
            Valid.SetTgl(TtglAses, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString());
            cmbJam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(),11).toString().substring(6, 8));
            TrgRawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 9).toString());
            cmbRujukan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString());
            cmbYaRujukan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString());
            TketYaLainya.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString());
            TdiagRujukan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString());
            cmbTidakRujukan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString());
            Tdiantar.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString());
            nipDokter = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString();
            TnmDokter.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 6).toString());
            nipSuper = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 19).toString();
            TnmSupervisor.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 7).toString());
            Valid.SetTgl(TtglAnam, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString());
            cmbJam1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(),21).toString().substring(6, 8));
            Tkeluhan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString());
            TriwPenyktSkg.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString());
            cekHipertensiRPD = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString();
            cekDMRPD = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString();
            cekPjk = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString();
            cekAsmaRPD = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString();
            cekStrok = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString();
            cekLiver = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString();
            cekGinjal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString();
            cekTbParu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString();
            cekLainRPD = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 32).toString();
            TketLainRPD.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 33).toString());
            cmbPernah.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString());
            Tkapan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString());
            Tdimana.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString());
            Tdiagnosis.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString());
            emptTeksRiwayatObat();
            tampilObat();
            cekHipertensiRPK = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString();
            cekDMRPK = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString();
            cekJantung = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString();
            cekAsmaRPK = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString();
            cekLainRPK = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString();
            TketLainRPK.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString());
            TriwAlergi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString());
            cmbNyeri.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString());
            Tlokasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString());
            Tintensitas.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString());
            Tskor.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString());
            cmbJenis.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString());
            cmbKeadaan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString());
            cmbGizi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString());
            TgcsE.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString());
            TgcsM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString());
            TgcsV.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString());
            cmbTindakan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString());
            Tbb.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString());
            Ttb.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString());
            Ttd.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString());
            Tnadi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString());
            Trespi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString());
            Taxila.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString());
            Trektal.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 62).toString());
            cmbAnemis.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString());
            TketAnemis.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 64).toString());
            cmbIkterik.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString());
            TketIkterik.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 66).toString());
            cekAnisokor = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString();
            cekIsokor = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString();
            Tdiameter.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString());
            cmbUdemPalpe.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString());            
            Ttonsil.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString());
            Tfaring.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString());
            Tlidah.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString());
            Tbibir.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString());
            Tjvp.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString());
            cmbKelenjar.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString());
            TketKelenjar.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString());
            cmbKaku.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString());
            cekSimetris = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 79).toString();
            cekAsimetris = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 80).toString();
            TketAsimetris.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 81).toString());
            Ts1s2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 82).toString());
            cekReguler = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 83).toString();
            cekIreguler = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 84).toString();
            TketMurmur.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 85).toString());
            TlainlainMurmur.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 86).toString());
            TsuaraNafas.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 87).toString());
            cmbRonchi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 88).toString());
            TketRonchi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 89).toString());
            cmbWhezing.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 90).toString());
            TketWhezing.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 91).toString());
            cmbDistended.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 92).toString());
            cmbMeteor.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 93).toString());
            cmbPeristaltik.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString());
            cmbAsites.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 95).toString());
            cmbNyeriTekan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 96).toString());
            TlokasiNyeriTekan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 97).toString());
            Thepar.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 98).toString());
            Tlien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 99).toString());            
            cekHangat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 100).toString();
            cekDingin = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString();
            cmbUdemExtremitas.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 102).toString());
            TketUdem.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 103).toString());
            TlainlainUdem.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 104).toString());            
            TpemeriksaanPenunjang.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 105).toString());
            TdiagUtama.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 106).toString());
            TdiagKomplikasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 107).toString());
            TdiagTambahan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 108).toString());
            cekTglPelak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 109).toString();
            Valid.SetTgl(TtglPelaksanaan, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 110).toString());            
            cekRenal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 111).toString();
            cekTerapi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 112).toString();
            cekDialisis = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 113).toString();
            cekSled = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 114).toString();
            cekCrrt = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 115).toString();
            cekHf = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 116).toString();            
            cekCito = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 117).toString();
            cekElektif = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 118).toString();
            cekPrepar = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 119).toString();
            cekTraveling = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 120).toString();
            cekRegulerTndkn = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 121).toString();            
            TlamaHd.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 122).toString());
            Tqb.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 123).toString());
            Tqd.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 124).toString());
            TupGoal.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 125).toString());
            Tluas.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 126).toString());
            cekLow = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 127).toString();            
            cekHigh = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 128).toString();
            Tantikoagulan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 129).toString());            
            cekAVShunt = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 130).toString();            
            cekGraft = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 131).toString();
            cekTunel = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 132).toString();            
            cekTempor = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 133).toString();
            cekFemoral = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 134).toString();            
            Tprofiling.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 135).toString());
            Ttransfusi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 136).toString());
            Teritropoetin.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 137).toString());
            nipPengisi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 138).toString();
            TnmPengisi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 8).toString());
            dataCek();
        }
    }
    
    public void setData(String norw, String sttsRawat, String unit) {
        TNoRw.setText(norw);
        TrgRawat.setText(unit);
        sttsrwt = sttsRawat;
        isPasien();
        Valid.SetTgl(TtglAses, tglReg.toString());
        cmbJam.setSelectedItem(jamReg.toString().substring(0, 2));
        cmbMnt.setSelectedItem(jamReg.toString().substring(3, 5));
        cmbDtk.setSelectedItem(jamReg.toString().substring(6, 8));
        
        Valid.SetTgl(TtglAnam, tglReg.toString());
        cmbJam1.setSelectedItem(jamReg.toString().substring(0, 2));
        cmbMnt1.setSelectedItem(jamReg.toString().substring(3, 5));
        cmbDtk1.setSelectedItem(jamReg.toString().substring(6, 8));

        TCari.setText(norw);
        Valid.SetTgl(DTPCari1, tglReg);    
    }    
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.gethemodialisa());
        BtnHapus.setEnabled(akses.gethemodialisa());
        BtnEdit.setEnabled(akses.gethemodialisa());
        
        if (akses.getjml2() >= 1) {
            nipPengisi = akses.getkode();
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPengisi, nipPengisi);
            if (TnmPengisi.getText().equals("")) {
                nipPengisi = "";
            }
        }        
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
                    if (akses.getform().equals("RMAsesmenAwalMedisHemodialisis")) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nipDokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                            TnmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDokter.requestFocus();
                            chkSaya.setSelected(false);
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
    
    private void initPetugas() {
        if (petugas == null) {
            petugas = new DlgCariPetugas(null, false);

            petugas.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    if (pilihan == 1) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            nipSuper = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                            TnmSupervisor.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnSupervisor.requestFocus();
                        }
                    } else if (pilihan == 2) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            nipPengisi = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                            TnmPengisi.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnPengisi.requestFocus();
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
    
    private void emptTeksRiwayatObat() {
        TnmObat.setText("");
        Tdosis.setText("");
        TwktPenggunaan.setText("");
    }
    
    private void variabelBersih() {
        nipDokter = "";
        nipSuper = "";
        nipPengisi = "";
        cekHipertensiRPD = "";
        cekDMRPD = "";
        cekPjk = "";
        cekAsmaRPD = "";
        cekStrok = "";
        cekLiver = "";
        cekGinjal = "";
        cekTbParu = "";
        cekLainRPD = "";
        cekHipertensiRPK = "";
        cekDMRPK = "";
        cekJantung = "";
        cekAsmaRPK = "";
        cekLainRPK = "";
        cekAnisokor = "";
        cekIsokor = "";
        cekSimetris = "";
        cekAsimetris = "";
        cekReguler = "";
        cekIreguler = "";
        cekHangat = "";
        cekDingin = "";
        cekTglPelak = "";
        cekRenal = "";
        cekTerapi = "";
        cekDialisis = "";
        cekSled = "";
        cekCrrt = "";
        cekHf = "";
        cekCito = "";
        cekElektif = "";
        cekPrepar = "";
        cekTraveling = "";
        cekRegulerTndkn = "";
        cekLow = "";
        cekHigh = "";
        cekAVShunt = "";
        cekGraft = "";
        cekTunel = "";
        cekTempor = "";
        cekFemoral = "";
        tglReg = "";
        jamReg = "";
        sttsrwt = "";
    }
    
    private void cekData() {
        if (chkHipertensiRPD.isSelected() == true) {
            cekHipertensiRPD = "ya";
        } else {
            cekHipertensiRPD = "tidak";
        }
        
        if (chkDmRPD.isSelected() == true) {
            cekDMRPD = "ya";
        } else {
            cekDMRPD = "tidak";
        }
        
        if (chkPjk.isSelected() == true) {
            cekPjk = "ya";
        } else {
            cekPjk = "tidak";
        }
        
        if (chkAsmaRPD.isSelected() == true) {
            cekAsmaRPD = "ya";
        } else {
            cekAsmaRPD = "tidak";
        }
        
        if (chkStroke.isSelected() == true) {
            cekStrok = "ya";
        } else {
            cekStrok = "tidak";
        }
        
        if (chkLiver.isSelected() == true) {
            cekLiver = "ya";
        } else {
            cekLiver = "tidak";
        }
        
        if (chkGinjal.isSelected() == true) {
            cekGinjal = "ya";
        } else {
            cekGinjal = "tidak";
        }
        
        if (chkTBparu.isSelected() == true) {
            cekTbParu = "ya";
        } else {
            cekTbParu = "tidak";
        }
        
        if (chkLainRPD.isSelected() == true) {
            cekLainRPD = "ya";
        } else {
            cekLainRPD = "tidak";
        }
        
        if (chkHipertensiRPK.isSelected() == true) {
            cekHipertensiRPK = "ya";
        } else {
            cekHipertensiRPK = "tidak";
        }
        
        if (chkDmRPK.isSelected() == true) {
            cekDMRPK = "ya";
        } else {
            cekDMRPK = "tidak";
        }
        
        if (chkJantung.isSelected() == true) {
            cekJantung = "ya";
        } else {
            cekJantung = "tidak";
        }
        
        if (chkAsmaRPK.isSelected() == true) {
            cekAsmaRPK = "ya";
        } else {
            cekAsmaRPK = "tidak";
        }
        
        if (chkLainRPK.isSelected() == true) {
            cekLainRPK = "ya";
        } else {
            cekLainRPK = "tidak";
        }
        
        if (chkAnisokor.isSelected() == true) {
            cekAnisokor = "ya";
        } else {
            cekAnisokor = "tidak";
        }
        
        if (chkIsokor.isSelected() == true) {
            cekIsokor = "ya";
        } else {
            cekIsokor = "tidak";
        }
        
        if (chkSimetris.isSelected() == true) {
            cekSimetris = "ya";
        } else {
            cekSimetris = "tidak";
        }
        
        if (chkAsimetris.isSelected() == true) {
            cekAsimetris = "ya";
        } else {
            cekAsimetris = "tidak";
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
        
        if (chkHangat.isSelected() == true) {
            cekHangat = "ya";
        } else {
            cekHangat = "tidak";
        }
        
        if (chkDingin.isSelected() == true) {
            cekDingin = "ya";
        } else {
            cekDingin = "tidak";
        }
        
        if (chkTglPelaksanaan.isSelected() == true) {
            cekTglPelak = "ya";
        } else {
            cekTglPelak = "tidak";
        }
        
        if (chkRenal.isSelected() == true) {
            cekRenal = "ya";
        } else {
            cekRenal = "tidak";
        }
        
        if (chkTerapi.isSelected() == true) {
            cekTerapi = "ya";
        } else {
            cekTerapi = "tidak";
        }
        
        if (chkDialisisKon.isSelected() == true) {
            cekDialisis = "ya";
        } else {
            cekDialisis = "tidak";
        }
        
        if (chkSled.isSelected() == true) {
            cekSled = "ya";
        } else {
            cekSled = "tidak";
        }
        
        if (chkCrrt.isSelected() == true) {
            cekCrrt = "ya";
        } else {
            cekCrrt = "tidak";
        }
        
        if (chkHf.isSelected() == true) {
            cekHf = "ya";
        } else {
            cekHf = "tidak";
        }
        
        if (chkCito.isSelected() == true) {
            cekCito = "ya";
        } else {
            cekCito = "tidak";
        }
        
        if (chkElektif.isSelected() == true) {
            cekElektif = "ya";
        } else {
            cekElektif = "tidak";
        }
        
        if (chkPreparatif.isSelected() == true) {
            cekPrepar = "ya";
        } else {
            cekPrepar = "tidak";
        }
        
        if (chkTraveling.isSelected() == true) {
            cekTraveling = "ya";
        } else {
            cekTraveling = "tidak";
        }
        
        if (chkRegulerTindk.isSelected() == true) {
            cekRegulerTndkn = "ya";
        } else {
            cekRegulerTndkn = "tidak";
        }
        
        if (chkLowFix.isSelected() == true) {
            cekLow = "ya";
        } else {
            cekLow = "tidak";
        }
        
        if (chkHigh.isSelected() == true) {
            cekHigh = "ya";
        } else {
            cekHigh = "tidak";
        }
        
        if (chkAvShunt.isSelected() == true) {
            cekAVShunt = "ya";
        } else {
            cekAVShunt = "tidak";
        }
        
        if (chkGraft.isSelected() == true) {
            cekGraft = "ya";
        } else {
            cekGraft = "tidak";
        }
        
        if (chkTunnel.isSelected() == true) {
            cekTunel = "ya";
        } else {
            cekTunel = "tidak";
        }
        
        if (chkTemporer.isSelected() == true) {
            cekTempor = "ya";
        } else {
            cekTempor = "tidak";
        }
        
        if (chkFemoral.isSelected() == true) {
            cekFemoral = "ya";
        } else {
            cekFemoral = "tidak";
        }
    }
    
    private void isPasien() {
        try {
            ps1 = koneksi.prepareStatement("select p.*, rp.tgl_registrasi, rp.jam_reg from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join penjab pj on pj.kd_pj=rp.kd_pj where rp.no_rawat='" + TNoRw.getText() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    TNoRM.setText(rs1.getString("no_rkm_medis"));
                    TPasien.setText(rs1.getString("nm_pasien"));
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
    
    private void tampilObat() {
        Valid.tabelKosong(tabMode1);
        try {
            ps2 = koneksi.prepareStatement("select * from obat_asesmen_awal_medis_hemodialisis "
                    + "where no_rawat='" + TNoRw.getText() + "' order by waktu_simpan");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode1.addRow(new String[]{
                        rs2.getString("no_rawat"),
                        rs2.getString("nm_obat"),
                        rs2.getString("dosis"),
                        rs2.getString("waktu_penggunaan_terakhir"),
                        rs2.getString("waktu_simpan")                        
                    });
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
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void dataCek() {
        chkSaya.setSelected(false);
        if (cmbRujukan.getSelectedIndex() == 1) {
            cmbYaRujukan.setEnabled(true);
            TketYaLainya.setEnabled(false);
            TdiagRujukan.setEnabled(true);
            cmbTidakRujukan.setEnabled(false);
            Tdiantar.setEnabled(false);
        } else if (cmbRujukan.getSelectedIndex() == 2) {
            cmbYaRujukan.setEnabled(false);
            TketYaLainya.setEnabled(false);
            TdiagRujukan.setEnabled(false);
            cmbTidakRujukan.setEnabled(true);
            cmbTidakRujukan.requestFocus();
            Tdiantar.setEnabled(false);
        } else {
            cmbYaRujukan.setEnabled(false);
            TketYaLainya.setEnabled(false);
            TdiagRujukan.setEnabled(false);
            cmbTidakRujukan.setEnabled(false);
            Tdiantar.setEnabled(false);
        }
        
        if (cmbYaRujukan.getSelectedIndex() == 0) {
            TketYaLainya.setEnabled(false);            
        } else {
            TketYaLainya.setEnabled(true);
        }
        
        if (cmbTidakRujukan.getSelectedIndex() == 2) {
            Tdiantar.setEnabled(true);
        } else {
            Tdiantar.setEnabled(false);
        }
        
        if (cekHipertensiRPD.equals("ya")) {
            chkHipertensiRPD.setSelected(true);
        } else {
            chkHipertensiRPD.setSelected(false);
        }
        
        if (cekDMRPD.equals("ya")) {
            chkDmRPD.setSelected(true);
        } else {
            chkDmRPD.setSelected(false);
        }
        
        if (cekPjk.equals("ya")) {
            chkPjk.setSelected(true);
        } else {
            chkPjk.setSelected(false);
        }
        
        if (cekAsmaRPD.equals("ya")) {
            chkAsmaRPD.setSelected(true);
        } else {
            chkAsmaRPD.setSelected(false);
        }
        
        if (cekStrok.equals("ya")) {
            chkStroke.setSelected(true);
        } else {
            chkStroke.setSelected(false);
        }
        
        if (cekLiver.equals("ya")) {
            chkLiver.setSelected(true);
        } else {
            chkLiver.setSelected(false);
        }
        
        if (cekGinjal.equals("ya")) {
            chkGinjal.setSelected(true);
        } else {
            chkGinjal.setSelected(false);
        }
        
        if (cekTbParu.equals("ya")) {
            chkTBparu.setSelected(true);
        } else {
            chkTBparu.setSelected(false);
        }
        
        if (cekLainRPD.equals("ya")) {
            chkLainRPD.setSelected(true);
            TketLainRPD.setEnabled(true);
        } else {
            chkLainRPD.setSelected(false);
            TketLainRPD.setEnabled(false);
        }
        
        if (cmbPernah.getSelectedIndex() == 1) {
            Tkapan.setEnabled(true);
            Tdimana.setEnabled(true);
            Tdiagnosis.setEnabled(true);
        } else {
            Tkapan.setEnabled(false);
            Tdimana.setEnabled(false);
            Tdiagnosis.setEnabled(false);
        }
        
        if (cekHipertensiRPK.equals("ya")) {
            chkHipertensiRPK.setSelected(true);
        } else {
            chkHipertensiRPK.setSelected(false);
        }
        
        if (cekDMRPK.equals("ya")) {
            chkDmRPK.setSelected(true);
        } else {
            chkDmRPK.setSelected(false);
        }
        
        if (cekJantung.equals("ya")) {
            chkJantung.setSelected(true);
        } else {
            chkJantung.setSelected(false);
        }
        
        if (cekAsmaRPK.equals("ya")) {
            chkAsmaRPK.setSelected(true);
        } else {
            chkAsmaRPK.setSelected(false);
        }
        
        if (cekLainRPK.equals("ya")) {
            chkLainRPK.setSelected(true);
            TketLainRPK.setEnabled(true);
        } else {
            chkLainRPK.setSelected(false);
            TketLainRPK.setEnabled(false);
        }
        
        if (cmbNyeri.getSelectedIndex() == 1) {
            Tlokasi.setEnabled(true);
            Tintensitas.setEnabled(true);
        } else {
            Tlokasi.setEnabled(false);
            Tintensitas.setEnabled(false);
        }
        
        if (cmbAnemis.getSelectedIndex() == 2) {
            TketAnemis.setEnabled(true);
        } else {
            TketAnemis.setEnabled(false);
        }
        
        if (cmbIkterik.getSelectedIndex() == 2) {
            TketIkterik.setEnabled(true);
        } else {
            TketIkterik.setEnabled(false);
        }
        
        if (cekAnisokor.equals("ya")) {
            chkAnisokor.setSelected(true);
        } else {
            chkAnisokor.setSelected(false);
        }
        
        if (cekIsokor.equals("ya")) {
            chkIsokor.setSelected(true);
        } else {
            chkIsokor.setSelected(false);
        }
        
        if (cmbKelenjar.getSelectedIndex() == 2) {
            TketKelenjar.setEnabled(true);
        } else {
            TketKelenjar.setEnabled(false);
        }
        
        if (cekSimetris.equals("ya")) {
            chkSimetris.setSelected(true);
        } else {
            chkSimetris.setSelected(false);
        }
        
        if (cekAsimetris.equals("ya")) {
            chkAsimetris.setSelected(true);
            TketAsimetris.setEnabled(true);
        } else {
            chkAsimetris.setSelected(false);
            TketAsimetris.setEnabled(false);
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
        
        if (cmbRonchi.getSelectedIndex() == 2) {
            TketRonchi.setEnabled(true);
        } else {
            TketRonchi.setEnabled(false);
        }
        
        if (cmbWhezing.getSelectedIndex() == 2) {
            TketWhezing.setEnabled(true);
        } else {
            TketWhezing.setEnabled(false);
        }
        
        if (cmbNyeriTekan.getSelectedIndex() == 2) {
            TlokasiNyeriTekan.setEnabled(true);
        } else {
            TlokasiNyeriTekan.setEnabled(false);
        }
        
        if (cekHangat.equals("ya")) {
            chkHangat.setSelected(true);
        } else {
            chkHangat.setSelected(false);
        }
        
        if (cekDingin.equals("ya")) {
            chkDingin.setSelected(true);
        } else {
            chkDingin.setSelected(false);
        }
        
        if (cmbUdemExtremitas.getSelectedIndex() == 2) {
            TketUdem.setEnabled(true);
        } else {
            TketUdem.setEnabled(false);
        }
        
        if (cekTglPelak.equals("ya")) {
            chkTglPelaksanaan.setSelected(true);
        } else {
            chkTglPelaksanaan.setSelected(false);
        }
        
        if (chkTglPelaksanaan.isSelected() == true) {
            TtglPelaksanaan.setEnabled(true);
            chkRenal.setEnabled(true);
            chkTerapi.setEnabled(true);
            chkDialisisKon.setEnabled(true);
            chkSled.setEnabled(true);
            chkCrrt.setEnabled(true);
            chkHf.setEnabled(true);
            chkCito.setEnabled(true);
            chkElektif.setEnabled(true);
            chkPreparatif.setEnabled(true);
            chkTraveling.setEnabled(true);
            chkRegulerTindk.setEnabled(true);
        } else {
            TtglPelaksanaan.setEnabled(false);
            chkRenal.setEnabled(false);
            chkTerapi.setEnabled(false);
            chkDialisisKon.setEnabled(false);
            chkSled.setEnabled(false);
            chkCrrt.setEnabled(false);
            chkHf.setEnabled(false);
            chkCito.setEnabled(false);
            chkElektif.setEnabled(false);
            chkPreparatif.setEnabled(false);
            chkTraveling.setEnabled(false);
            chkRegulerTindk.setEnabled(false);
        }
        
        if (cekRenal.equals("ya")) {
            chkRenal.setSelected(true);
        } else {
            chkRenal.setSelected(false);
        }
        
        if (cekTerapi.equals("ya")) {
            chkTerapi.setSelected(true);
        } else {
            chkTerapi.setSelected(false);
        }
        
        if (cekDialisis.equals("ya")) {
            chkDialisisKon.setSelected(true);
        } else {
            chkDialisisKon.setSelected(false);
        }
        
        if (cekSled.equals("ya")) {
            chkSled.setSelected(true);
        } else {
            chkSled.setSelected(false);
        }
        
        if (cekCrrt.equals("ya")) {
            chkCrrt.setSelected(true);
        } else {
            chkCrrt.setSelected(false);
        }
        
        if (cekHf.equals("ya")) {
            chkHf.setSelected(true);
        } else {
            chkHf.setSelected(false);
        }
        
        if (cekCito.equals("ya")) {
            chkCito.setSelected(true);
        } else {
            chkCito.setSelected(false);
        }
        
        if (cekElektif.equals("ya")) {
            chkElektif.setSelected(true);
        } else {
            chkElektif.setSelected(false);
        }
        
        if (cekPrepar.equals("ya")) {
            chkPreparatif.setSelected(true);
        } else {
            chkPreparatif.setSelected(false);
        }
        
        if (cekTraveling.equals("ya")) {
            chkTraveling.setSelected(true);
        } else {
            chkTraveling.setSelected(false);
        }
        
        if (cekRegulerTndkn.equals("ya")) {
            chkRegulerTindk.setSelected(true);
        } else {
            chkRegulerTindk.setSelected(false);
        }
        
        if (cekLow.equals("ya")) {
            chkLowFix.setSelected(true);
        } else {
            chkLowFix.setSelected(false);
        }
        
        if (cekHigh.equals("ya")) {
            chkHigh.setSelected(true);
        } else {
            chkHigh.setSelected(false);
        }
        
        if (cekAVShunt.equals("ya")) {
            chkAvShunt.setSelected(true);
        } else {
            chkAvShunt.setSelected(false);
        }
        
        if (cekGraft.equals("ya")) {
            chkGraft.setSelected(true);
        } else {
            chkGraft.setSelected(false);
        }
        
        if (cekTunel.equals("ya")) {
            chkTunnel.setSelected(true);
        } else {
            chkTunnel.setSelected(false);
        }
        
        if (cekTempor.equals("ya")) {
            chkTemporer.setSelected(true);
        } else {
            chkTemporer.setSelected(false);
        }
        
        if (cekFemoral.equals("ya")) {
            chkFemoral.setSelected(true);
        } else {
            chkFemoral.setSelected(false);
        }
    }
    
    private void getDataObat() {
        if (tbObat.getSelectedRow() != -1) {
            TnmObat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            Tdosis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            TwktPenggunaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
        }
    }
    
    public void awalData() {
        if (Sequel.cariInteger("select count(-1) from asesmen_awal_medis_hemodialisis where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
            tampil();
        } else if (Sequel.cariInteger("select count(-1) from asesmen_awal_medis_hemodialisis where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }
}