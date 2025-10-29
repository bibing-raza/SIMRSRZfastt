package simrskhanza;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jna.platform.win32.OaIdl;
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
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import laporan.DlgDiagnosaPenyakit;
import laporan.DlgHasilPenunjangMedis;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import rekammedis.DlgMasterJenisDokumenJangMed;
import rekammedis.DlgVerifikasiCPPT;
import rekammedis.RMDokumenPenunjangMedis;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class DlgRingkasanPulangRanap extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabModeResiko, tabModeCppt, tabModeLis, tabModeHasilLab, 
            tabModeHasilCopy, tabModeRad, tabModeJangMed, tabModePembaca, tabModePembaca1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9, psPasien, psdiag, pspros, psLaprm, psFakIGD, psRes, pscppt, 
            psLab1, psLabA, psLabB, psLabC, psRad, psFile, psRDO, psRDU, psRDD, psRDM;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rsPasien, rsdiag, rspros, rsLaprm, rsFakIGD, rsRes, rscppt, rsLab1, rsLabA, rsLabB, rsLabC,
            rsRad, rsDok, rsFile, rsDiag, rsDiag1, rsObat, rsTHT, rsLISMaster, rsLIS1, rsLIS2, rsLIS3, rsDiabet, rsRDO, rsRDU, rsRDD, rsRDM;
    private int i = 0, x = 0, totskorTriase = 0, skorGZ1 = 0, skorYaGZ1 = 0, skorGZ2 = 0, skor = 0, pilihan = 0, urut = 0, cekPilihanRehab = 0,
            y = 0, w = 0, lisM = 0, lis1 = 0, lis2 = 0;
    public DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private String kontrolPoli = "", cekTgl = "", diagnosa = "", tindakan = "", kodekamar = "", skorAsesIGD = "", kesimpulanGZanak = "",
            kesimpulanGZDewasa = "", faktorresikoigd = "", TotSkorGZD = "", TotSkorGZA = "", TotSkorRJ = "", kesimpulanResikoJatuh = "", 
            nmgedung = "", a = "", host = "", tipeDiabet = "", merokok = "", lmLuka = "", jnsAlas = "", traMekanik = "", traKimia = "", traTermis = "",
            spontan = "", lainPenyebab = "", tersandung = "", memakai = "", tertusuk = "", dllTraMeka = "", terkenaAir = "", terkenaPemanas = "",
            dllTraTermis = "", amputasiKiri = "", amputasiKanan = "", mataDiabet = "", ginjal = "", pnyJantung = "", hipertensi = "", strok = "", pad = "",
            nonUlkus = "", ulkus = "", ulkusGang = "", sellu = "", jarKakiKanan = "", jarKakiKiri = "", der0 = "", der1 = "", der2 = "", der3 = "", 
            der4 = "", der5 = "", surgi = "", chemi = "", bio = "", hydro = "", foam = "", algi = "", silver = "", cadex = "", madu = "", lainModern = "", 
            debri = "", modernDres = "";
    private String anemis = "", ikterik = "", pupil = "", dia_kanan = "", dia_kiri = "", udem_palpe = "", tonsil = "", faring = "", satur = "",
            lidah = "", bibir = "", jvp = "", limfe = "", kuduk = "", thorak = "", cor = "", reguler = "", ireguler = "", lain1 = "", nafas = "",
            ronci = "", whezing = "", disten = "", meteo = "", peris = "", asites = "", nyeri = "", hepar = "", lien = "", extrem = "", udem = "",
            lain2 = "", dataKonfirmasi = "", host_port = "", requestJson12 = "", stringbalik = "",
            poinA = "", keadaan_umum = "", kesadaran = "", gcs = "", tensi = "", suhu = "", nadi = "", kualitas = "", napas = "", poinB = "", bb = "",
            bbpersen = "", bbpbpersen = "", pb = "", pbpersen = "", lla = "", lk = "", turgor = "", sianosis = "", perdarahan_kulit = "", ikterus = "",
            kalimat_ikterus = "", hematoma = "", sklerema = "", kutis = "", marmorata = "", lainya_kulit = "", poinC = "", bentuk = "", rambut = "",
            mata = "", telinga = "", hidung = "", mulut = "", poinDE = "", leher = "", bentuk_dada = "", retraksi_dada = "", inspeksi_jan = "",
            palpasi_jan = "", perkusi_jan = "", auskultasi_jan = "", inspeksi_par = "", palpasi_par = "", perkusi_par = "", auskultasi_par = "",
            poinF = "", inspeksi_per = "", palpasi_per = "", perkusi_per = "", auskultasi_per = "", poinG = "", umum = "", neurologis = "", poinH = "",
            susunan = "", tanda = "", genitalia = "", anus = "", nipPenyimpan = "", nipExecutor = "",
            keadaanUmum = "", gizi = "", gcsDewasa = "", tindakanResus = "", beratBdn = "", tinggiBdn = "", td = "", nadiDewasa = "",
            respi = "", suhuAxila = "", suhuRektal = "";
    private String turgorPeri = "", sianosisKulitPeri = "", perdarahanKulitPeri = "", ikterusPosPeri = "", ikterusNegPeri = "", kramerPeri = "", hematomaPeri = "",
            sklerePeri = "", kutisPeri = "", lainKulitPeri = "", simetrisKepalaPeri = "", asimetrisKepalaPeri = "", cepalPeri = "", caputPeri = "", anenPeri = "",
            microsPeri = "", hidroPeri = "", lainKepalaPeri = "", datarPeri = "", cembungPeri = "", cekungPeri = "", lainUubPeri = "", normalMataPeri = "",
            anemiaPeri = "", ikterusPeri = "", sekretMataPeri = "", LainMataPeri = "", normalThtPeri = "", nchPeri = "", sianosisThtPeri = "",
            sekretThtPeri = "", lainThtPeri = "", normalMulutPeri = "", labioSPeri = "", labioPPeri = "", labioGPeri = "", mukosaPeri = "",
            reflekPeri = "", lainMulutPeri = "", normalLeherPeri = "", tortiPeri = "", benjolKananPeri = "", benjolKiriPeri = "", lainLeherPeri = "",
            simetrisDadaPeri = "", tidakSimetrisPeri = "", retraksiPosPeri = "", retraksiNegPeri = "", sesakPeri = "", merintihPeri = "", sianosisDadaPeri = "",
            lainDadaPeri = "", bjPeri = "", murniPeri = "", tidakMurniPeri = "", regulerPeri = "", tidakRegulerPeri = "", bunyiPeri = "", vesikulerPeri = "",
            ronchiPeri = "", wezingPeri = "", stridorPeri = "", lainParuPeri = "", supelPeri = "", distenPeri = "", bisingPeri = "", heparPeri = "",
            limpaPeri = "", nyeriPeri = "", masaPosPeri = "", masaNegPeri = "", ukPeri = "", lokasiPeri = "", segarPeri = "", layuPeri = "", lainTaliPeri = "",
            normalPunggungPeri = "", spinaPeri = "", gibusPeri = "", lainPunggungPeri = "", sexPeri = "", kelainanUroPeri = "", bakPeri = "", babPeri = "",
            simetrisEksPeri = "", asimetrisEksPeri = "", reflekMoroPosPeri = "", reflekMoroNegPeri = "", lainEksPeri = "", edemaPeri = "", kelainanEksPeri = "",
            kondisiLahir = "", ketAS = "", gerak = "", tangis = "", warnaKulit = "", hrPeri = "", suhuPeri = "", rrPeri = "", satuPeri = "", capilari = "",
            bblPeri = "", pbPeri = "", lkPeri = "", ldPeri = "", lpPeri = "", llaPeri = "", poinI = "", poinJ = "", poinK = "", poinL = "", poinM = "", poinN = "",
            poinO = "", poinP = "", poinQ = "", poinR = "", ketRetraksi = "", anusPeri = "";
    private String noLIS = "", cekLIS = "", ketLIS = "", tglLIS = "", jamLIS = "", drpengirim = "", tglPeriksaLIS = "", jamPeriksaLIS = "",
            hasilDipilih = "", kdItem = "", norawat = "", tglhasil = "", jamhasil = "", nmpemeriksaan = "", link = "", nipDpjpAwal = "";
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private JsonNode root;
    private JsonNode response;
    private ObjectMapper mapper = new ObjectMapper();

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgRingkasanPulangRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Jns. Kelamin", "Tgl. Masuk", "Tgl. Pulang", "Ruang/Kelas Rawat",
            "Dokter Pengirim", "Cara Bayar", "Alasan Masuk Dirawat", "Ringkasan Riwayat Penyakit", "Pemeriksaan Fisik", "Pemeriksaan Penunjang Diagnostik",
            "Terapi Pengobatan", "Diagnosa Utama/Primer", "Diagnosa Sekunder", "Tindakan Prosedur", "Kondisi Wkt. Pulang", "Keadaan Umum", "Kesadaran", "GCS",
            "Tekanan Darah", "Suhu", "Nadi", "Frekuensi Nafas", "Catatan Penting", "Terapi Pulang", "Pengobatan Lanjutan", "Dokter Luar", "Tgl. Kontrol Poli",
            "Nama DPJP Pasien", "cektgl", "edukasi", "png_jawab_px", "nip_penyimpan", "hasil_pemeriksaan", "Data Resume Disimpan Oleh"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRingkasan.setModel(tabMode);
        tbRingkasan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRingkasan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 38; i++) {
            TableColumn column = tbRingkasan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(350);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {
                column.setPreferredWidth(150);
            } else if (i == 10) {
                column.setPreferredWidth(350);
            } else if (i == 11) {
                column.setPreferredWidth(350);
            } else if (i == 12) {
                column.setPreferredWidth(350);
            } else if (i == 13) {
                column.setPreferredWidth(350);
            } else if (i == 14) {
                column.setPreferredWidth(350);
            } else if (i == 15) {
                column.setPreferredWidth(350);
            } else if (i == 16) {
                column.setPreferredWidth(350);
            } else if (i == 17) {
                column.setPreferredWidth(350);
            } else if (i == 18) {
                column.setPreferredWidth(160);
            } else if (i == 19) {
                column.setPreferredWidth(350);
            } else if (i == 20) {
                column.setPreferredWidth(350);
            } else if (i == 21) {
                column.setPreferredWidth(90);
            } else if (i == 22) {
                column.setPreferredWidth(90);
            } else if (i == 23) {
                column.setPreferredWidth(90);
            } else if (i == 24) {
                column.setPreferredWidth(90);
            } else if (i == 25) {
                column.setPreferredWidth(90);
            } else if (i == 26) {
                column.setPreferredWidth(350);
            } else if (i == 27) {
                column.setPreferredWidth(350);
            } else if (i == 28) {
                column.setPreferredWidth(140);
            } else if (i == 29) {
                column.setPreferredWidth(200);
            } else if (i == 30) {
                column.setPreferredWidth(95);
            } else if (i == 31) {
                column.setPreferredWidth(200);
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
                column.setPreferredWidth(250);
            }
        }
        tbRingkasan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeResiko=new DefaultTableModel(null,new Object[]{
                "#", "KODE", "ASESMEN", "FAKTOR RESIKO", "SKALA", "SKOR"
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
        
        tbFaktorResiko.setModel(tabModeResiko);
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
                column.setPreferredWidth(67);
            } else if (i == 3) {
                column.setPreferredWidth(265);
            } else if (i == 4) {
                column.setPreferredWidth(265);
            } else if (i == 5) {
                column.setPreferredWidth(40);
            }
        }
        tbFaktorResiko.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModePembaca = new DefaultTableModel(null, new String[]{
            "No. LIS", "Dokter Pembaca", "Tgl. Periksa", "Jam Periksa", "Tgl. Baca", "Jam Baca"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPembacaLIS.setModel(tabModePembaca);
        tbPembacaLIS.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPembacaLIS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 6; i++) {
            TableColumn column = tbPembacaLIS.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(350);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            }
        }
        tbPembacaLIS.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPembacaLIS.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPembacaLIS.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbPembacaLIS.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbPembacaLIS.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbPembacaLIS.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        
        tabModePembaca1 = new DefaultTableModel(null, new String[]{
            "kd_jenis_prw", "Nama Pemeriksaaan Rad.", "Dokter Pembaca", "Tgl. Periksa", "Jam Periksa", "Tgl. Baca", "Jam Baca"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPembacaRad.setModel(tabModePembaca1);
        tbPembacaRad.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPembacaRad.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 7; i++) {
            TableColumn column = tbPembacaRad.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(220);
            } else if (i == 2) {
                column.setPreferredWidth(350);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            }
        }
        tbPembacaRad.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPembacaRad.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbPembacaRad.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbPembacaRad.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbPembacaRad.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Jns. Kelamin", "Tgl. MRS", "Tgl. Pulang",
            "Stts. Pulang", "Nama DPJP", "Ruang Rawat", "Ringkasan Pulang", "Cara Bayar", "Dokter Pengirim", "nmgedung"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPasien.setModel(tabMode1);
        tbPasien.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 14; i++) {
            TableColumn column = tbPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(120);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setPreferredWidth(200);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            } else if (i == 11) {
                column.setPreferredWidth(110);
            } else if (i == 12) {
                column.setPreferredWidth(220);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        
        tabModeLis = new DefaultTableModel(null, new Object[]{
            "Pasien", "No. LIS", "Keterangan Hasil Lab.", "cekok", "norawat", "Tgl. Periksa",
            "Jam Periksa", "Dokter Pengirim", "Jns. Rawat"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbLIS.setModel(tabModeLis);
        tbLIS.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbLIS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 9; i++) {
            TableColumn column = tbLIS.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(260);
            } else if (i == 8) {
                column.setPreferredWidth(80);
            }
        }
        tbLIS.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeHasilLab = new DefaultTableModel(null, new Object[]{
            "Cek", "Jenis Pemeriksaan/Item", "Nilai Hasil", "Satuan", "Flag Kode", "Nilai Rujukan", "Waktu Selesai", "Metode Pemeriksaan"}) {
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbHasil.setModel(tabModeHasilLab);
        tbHasil.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbHasil.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            TableColumn column = tbHasil.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(90);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(160);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbHasil.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeHasilCopy = new DefaultTableModel(null, new Object[]{
            "Jenis Pemeriksaan/Item", "Nilai Hasil", "Satuan",}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbHasilCopy.setModel(tabModeHasilCopy);
        tbHasilCopy.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbHasilCopy.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 3; i++) {
            TableColumn column = tbHasilCopy.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } 
        }
        tbHasilCopy.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRad = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien", "Jns. Rawat", "Pemeriksaan Rad.", "Dokter Perujuk", "Tgl. Periksa", "Jam Periksa",
            "no_rawat", "kd_jenis_prw", "tgl_periksa"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRadiologi.setModel(tabModeRad);
        tbRadiologi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRadiologi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 10; i++) {
            TableColumn column = tbRadiologi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(65);
            } else if (i == 1) {
                column.setPreferredWidth(260);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(260);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRadiologi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeJangMed = new DefaultTableModel(null, new Object[]{
            "id_file", "Nama Pemeriksaan", "Tgl. Upload", "Jam", "Nama File", "Petugas Yang Upload", "nip_petugas"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbHapus.setModel(tabModeJangMed);
        tbHapus.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbHapus.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbHapus.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(160);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbHapus.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "Dilakukan Oleh", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Hapus", "Tgl. Eksekusi"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayat.setModel(tabMode2);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 6; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(280);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            }
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TTensi.setDocument(new batasInput((int) 15).getKata(TTensi));
        TSuhu.setDocument(new batasInput((int) 15).getKata(TSuhu));
        TNadi.setDocument(new batasInput((int) 15).getKata(TNadi));
        TFrekuensiNafas.setDocument(new batasInput((int) 15).getKata(TFrekuensiNafas));
        Tgcs.setDocument(new batasInput((int) 100).getKata(Tgcs));
        TDokterLuar.setDocument(new batasInput((int) 150).getKata(TDokterLuar));
        TNmDokter.setDocument(new batasInput((int) 150).getKata(TNmDokter));
        TKlgPasien.setDocument(new batasInput((int) 150).getKata(TKlgPasien));
        noreg.setDocument(new batasInput((byte) 16).getKata(noreg));

        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampil();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampil();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampil();}
            });
        } 
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRingkasanPulangRanap")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        if (pilihan == 1) {
                            TNmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        } else if (pilihan == 2) {
                            kddpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            nmdpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            btnDPJP.requestFocus();
                        } else if (pilihan == 3) {
                            kddokter1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            nmdokter1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            btnDokter1.requestFocus();
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
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRingkasanPulangRanap")) {
                    if (poli.getTable().getSelectedRow() != -1) {
                        kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                        TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                        kdpoli.requestFocus();
                        isCek();
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
        
        try {
            psLabA = koneksi.prepareStatement("SELECT lhp.kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "GROUP BY lhp.kategori_pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut");
            
            psLabB = koneksi.prepareStatement("SELECT lhp.sub_kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "and lhp.kategori_pemeriksaan_nama=? GROUP BY lhp.sub_kategori_pemeriksaan_nama "
                    + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.sub_kategori_pemeriksaan_nama desc,lhp.pemeriksaan_no_urut");
            
            psLabC = koneksi.prepareStatement("SELECT lhp.pemeriksaan_nama, lhp.nilai_hasil, lhp.satuan, lhp.flag_kode, "
                    + "lhp.nilai_rujukan, DATE_FORMAT(lhdp.waktu_insert,'%d/%m/%Y - %H:%i:%s') wkt_selesai, lhp.metode "
                    + "FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp ON lhp.no_lab = lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp ON lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "and lhp.sub_kategori_pemeriksaan_nama=? and lhp.kategori_pemeriksaan_nama=? GROUP BY lhp.pemeriksaan_nama "
                    + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut");
        } catch (Exception e) {
            System.out.println(e);
        }
        
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
        
        LoadHTML2.setEditable(true);        
        LoadHTML2.setEditorKit(kit);        
        LoadHTML2.setDocument(doc);
        LoadHTML2.setEditable(false);        
        LoadHTML2.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
        } catch (Exception e) {
            System.out.println("simrskhanza.DlgRingkasanPulangRanap.<init>() : " + e);
        }
        
        try {
            link = koneksiDB.HOSTport();
        } catch (Exception e) {
            System.out.println("E : " + e);
        }
        
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
        MnIGD = new javax.swing.JMenu();
        MnTriase = new javax.swing.JMenuItem();
        MnAsesmenMedikIGD = new javax.swing.JMenuItem();
        MnAsesmenKeperawatanIGD = new javax.swing.JMenuItem();
        MnAsesmenMedikObstetriIGD = new javax.swing.JMenuItem();
        MnAsesmenKebidanan = new javax.swing.JMenuItem();
        MnDiagnosa = new javax.swing.JMenuItem();
        MnCetakRingkasan = new javax.swing.JMenuItem();
        MnGantiDokterSimpan = new javax.swing.JMenuItem();
        MnRiwayatData = new javax.swing.JMenuItem();
        MnBersihkanStringSampah = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnHapusDipilih = new javax.swing.JMenuItem();
        MnHapusSemua = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        MnJenisDokumen = new javax.swing.JMenuItem();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        MnRehabMedik = new javax.swing.JMenuItem();
        TabTindakanPencegahan = new javax.swing.JTabbedPane();
        panelBiasa6 = new widget.PanelBiasa();
        TabPencegahanDewasa = new javax.swing.JTabbedPane();
        panelBiasa8 = new widget.PanelBiasa();
        dewasaA = new widget.TextArea();
        panelBiasa9 = new widget.PanelBiasa();
        dewasaB = new widget.TextArea();
        panelBiasa10 = new widget.PanelBiasa();
        dewasaC = new widget.TextArea();
        panelBiasa7 = new widget.PanelBiasa();
        TabPencegahanAnak = new javax.swing.JTabbedPane();
        panelBiasa14 = new widget.PanelBiasa();
        anakA = new widget.TextArea();
        panelBiasa15 = new widget.PanelBiasa();
        anakB = new widget.TextArea();
        Scroll8 = new widget.ScrollPane();
        tbFaktorResiko = new widget.Table();
        WindowTTE = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        jLabel60 = new widget.Label();
        kddokter = new widget.TextBox();
        TDokter = new widget.TextBox();
        jLabel61 = new widget.Label();
        Tpaspras = new widget.PasswordBox();
        panelisi4 = new widget.panelisi();
        BtnSimpan1 = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        WindowPasien = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        Scroll9 = new widget.ScrollPane();
        tbPasien = new widget.Table();
        panelisi6 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnAll1 = new widget.Button();
        BtnCloseIn2 = new widget.Button();
        WindowDPJPranap = new javax.swing.JDialog();
        internalFrame15 = new widget.InternalFrame();
        BtnCloseIn10 = new widget.Button();
        BtnSimpan6 = new widget.Button();
        jLabel62 = new widget.Label();
        kddpjp = new widget.TextBox();
        nmdpjp = new widget.TextBox();
        btnDPJP = new widget.Button();
        WindowDokterPenyimpan = new javax.swing.JDialog();
        internalFrame16 = new widget.InternalFrame();
        BtnCloseIn11 = new widget.Button();
        BtnSimpan7 = new widget.Button();
        jLabel63 = new widget.Label();
        kddokter1 = new widget.TextBox();
        nmdokter1 = new widget.TextBox();
        btnDokter1 = new widget.Button();
        WindowHapusDokJangMed = new javax.swing.JDialog();
        internalFrame32 = new widget.InternalFrame();
        internalFrame33 = new widget.InternalFrame();
        internalFrame34 = new widget.InternalFrame();
        jLabel71 = new widget.Label();
        TCari5 = new widget.TextBox();
        BtnCari7 = new widget.Button();
        jLabel72 = new widget.Label();
        LCount2 = new widget.Label();
        internalFrame35 = new widget.InternalFrame();
        ChkDokumen1 = new widget.CekBox();
        BtnHapusFile = new widget.Button();
        BtnKeluar2 = new widget.Button();
        Scroll29 = new widget.ScrollPane();
        tbHapus = new widget.Table();
        WindowRehabMedik = new javax.swing.JDialog();
        internalFrame37 = new widget.InternalFrame();
        BtnCloseIn9 = new widget.Button();
        BtnSimpan8 = new widget.Button();
        jLabel77 = new widget.Label();
        cmbRM = new widget.ComboBox();
        WindowRiwayat = new javax.swing.JDialog();
        internalFrame13 = new widget.InternalFrame();
        internalFrame18 = new widget.InternalFrame();
        internalFrame17 = new widget.InternalFrame();
        jLabel101 = new widget.Label();
        DTPCari3 = new widget.Tanggal();
        jLabel102 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel103 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        jLabel104 = new widget.Label();
        LCount1 = new widget.Label();
        internalFrame19 = new widget.InternalFrame();
        BtnAll2 = new widget.Button();
        BtnRestor = new widget.Button();
        BtnCloseIn12 = new widget.Button();
        Scroll6 = new widget.ScrollPane();
        tbRiwayat = new widget.Table();
        internalFrame1 = new widget.InternalFrame();
        TabRingkasan = new widget.TabPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        panelisi1 = new widget.panelisi();
        jLabel4 = new widget.Label();
        TNoRM = new widget.TextBox();
        TNoRW = new widget.TextBox();
        jLabel8 = new widget.Label();
        TNmPasien = new widget.TextBox();
        TTglLhr = new widget.TextBox();
        jLabel10 = new widget.Label();
        TJK = new widget.TextBox();
        jLabel11 = new widget.Label();
        TTglMsk = new widget.TextBox();
        jLabel12 = new widget.Label();
        TTglPulang = new widget.TextBox();
        jLabel13 = new widget.Label();
        TRuangrawat = new widget.TextBox();
        jLabel14 = new widget.Label();
        TCaraBayar = new widget.TextBox();
        jLabel15 = new widget.Label();
        TNmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel43 = new widget.Label();
        Tdpjp = new widget.TextBox();
        jLabel45 = new widget.Label();
        jLabel44 = new widget.Label();
        Scroll16 = new widget.ScrollPane();
        TAlasanDirawat = new widget.TextArea();
        jLabel16 = new widget.Label();
        Scroll17 = new widget.ScrollPane();
        TRingkasanRiwayat = new widget.TextArea();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        Scroll26 = new widget.ScrollPane();
        TPemeriksaanFisik = new widget.TextArea();
        jLabel40 = new widget.Label();
        Scroll18 = new widget.ScrollPane();
        TPemeriksaanPenunjang = new widget.TextArea();
        jLabel19 = new widget.Label();
        jLabel20 = new widget.Label();
        Scroll19 = new widget.ScrollPane();
        TTerapiPengobatan = new widget.TextArea();
        jLabel21 = new widget.Label();
        jLabel22 = new widget.Label();
        jLabel23 = new widget.Label();
        Scroll20 = new widget.ScrollPane();
        TDiagUtama = new widget.TextArea();
        jLabel28 = new widget.Label();
        jLabel24 = new widget.Label();
        Scroll21 = new widget.ScrollPane();
        TDiagSekunder = new widget.TextArea();
        jLabel25 = new widget.Label();
        Scroll22 = new widget.ScrollPane();
        TTindakan = new widget.TextArea();
        jLabel26 = new widget.Label();
        Scroll23 = new widget.ScrollPane();
        TKeadaanumum = new widget.TextArea();
        jLabel27 = new widget.Label();
        Scroll24 = new widget.ScrollPane();
        TKesadaran = new widget.TextArea();
        jLabel29 = new widget.Label();
        jLabel30 = new widget.Label();
        TTensi = new widget.TextBox();
        jLabel31 = new widget.Label();
        TSuhu = new widget.TextBox();
        jLabel32 = new widget.Label();
        TNadi = new widget.TextBox();
        jLabel49 = new widget.Label();
        jLabel33 = new widget.Label();
        TFrekuensiNafas = new widget.TextBox();
        jLabel37 = new widget.Label();
        Tgcs = new widget.TextBox();
        jLabel34 = new widget.Label();
        cmbLanjutan = new widget.ComboBox();
        jLabel35 = new widget.Label();
        TDokterLuar = new widget.TextBox();
        chkTglKontrol = new widget.CekBox();
        TglKontrol = new widget.Tanggal();
        jLabel41 = new widget.Label();
        jLabel36 = new widget.Label();
        Scroll25 = new widget.ScrollPane();
        TCatatan = new widget.TextArea();
        jLabel39 = new widget.Label();
        jLabel42 = new widget.Label();
        Scroll27 = new widget.ScrollPane();
        TTerapiPulang = new widget.TextArea();
        jLabel50 = new widget.Label();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        Tedukasi = new widget.TextBox();
        jLabel55 = new widget.Label();
        jLabel56 = new widget.Label();
        TKlgPasien = new widget.TextBox();
        jLabel57 = new widget.Label();
        BtnPastePenunjang = new widget.Button();
        jLabel38 = new widget.Label();
        jLabel46 = new widget.Label();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        Scroll28 = new widget.ScrollPane();
        THasil = new widget.TextArea();
        BtnPasteHasil = new widget.Button();
        jLabel5 = new widget.Label();
        noreg = new widget.TextBox();
        jml_noreg = new widget.Label();
        BtnPasteTerapiPulang = new widget.Button();
        BtnPasien = new widget.Button();
        BtnNamaDPJP = new widget.Button();
        cmbAsesmen = new widget.ComboBox();
        jLabel7 = new widget.Label();
        cmbKondisiWP = new widget.ComboBox();
        jLabel64 = new widget.Label();
        jLabel65 = new widget.Label();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        Scroll3 = new widget.ScrollPane();
        tbCPPT = new widget.Table();
        panelGlass14 = new widget.panelisi();
        scrollPane5 = new widget.ScrollPane();
        Thasil = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnNotepad = new widget.Button();
        BtnResep = new widget.Button();
        BtnKeluar = new widget.Button();
        BtnTTE = new widget.Button();
        internalFrame4 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbRingkasan = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel47 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel48 = new widget.Label();
        LCount = new widget.Label();
        BtnHapus1 = new widget.Button();
        BtnPrint1 = new widget.Button();
        BtnResep1 = new widget.Button();
        BtnVerif = new widget.Button();
        BtnTTE1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        internalFrame29 = new widget.InternalFrame();
        PanelInput = new javax.swing.JPanel();
        panelGlass11 = new widget.panelisi();
        Scroll12 = new widget.ScrollPane();
        tbLIS = new widget.Table();
        Scroll31 = new widget.ScrollPane();
        tbPembacaLIS = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel66 = new widget.Label();
        TCari3 = new widget.TextBox();
        jLabel67 = new widget.Label();
        cmbHlm = new widget.ComboBox();
        BtnCari4 = new widget.Button();
        panelGlass15 = new widget.panelisi();
        Scroll1 = new widget.ScrollPane();
        tbHasil = new widget.Table();
        Scroll13 = new widget.ScrollPane();
        tbHasilCopy = new widget.Table();
        panelGlass16 = new widget.panelisi();
        BtnConteng = new widget.Button();
        BtnHapus2 = new widget.Button();
        BtnCopy = new widget.Button();
        BtnUlangiCopyLab = new widget.Button();
        BtnKeluar5 = new widget.Button();
        internalFrame30 = new widget.InternalFrame();
        FormInput2 = new widget.PanelBiasa();
        panelGlass32 = new widget.panelisi();
        Scroll14 = new widget.ScrollPane();
        tbRadiologi = new widget.Table();
        Scroll32 = new widget.ScrollPane();
        tbPembacaRad = new widget.Table();
        panelGlass17 = new widget.panelisi();
        jLabel68 = new widget.Label();
        TCari4 = new widget.TextBox();
        jLabel69 = new widget.Label();
        cmbHlm1 = new widget.ComboBox();
        BtnCari5 = new widget.Button();
        Scroll11 = new widget.ScrollPane();
        HasilPeriksa = new widget.TextArea();
        panelGlass18 = new widget.panelisi();
        BtnCopy1 = new widget.Button();
        BtnUlangiCopyRad = new widget.Button();
        BtnKeluar6 = new widget.Button();
        internalFrame31 = new widget.InternalFrame();
        panelGlass30 = new widget.panelisi();
        jLabel70 = new widget.Label();
        TNoRw1 = new widget.TextBox();
        TNoRm1 = new widget.TextBox();
        TPasien1 = new widget.TextBox();
        Scroll15 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        panelGlass19 = new widget.panelisi();
        ChkDokumen = new widget.CekBox();
        BtnUpload = new widget.Button();
        BtnHapus3 = new widget.Button();
        BtnCari6 = new widget.Button();
        BtnKeluar7 = new widget.Button();
        panelGlass20 = new widget.panelisi();
        panelGlass21 = new widget.panelisi();
        PanelWallpublic = new usu.widget.glass.PanelGlass();
        panelGlass22 = new widget.panelisi();
        PanelWallwifi = new usu.widget.glass.PanelGlass();
        internalFrame36 = new widget.InternalFrame();
        panelGlass29 = new widget.panelisi();
        jLabel73 = new widget.Label();
        TNoRw2 = new widget.TextBox();
        TNoRm2 = new widget.TextBox();
        TPasien2 = new widget.TextBox();
        Scroll30 = new widget.ScrollPane();
        LoadHTML2 = new widget.editorpane();
        panelGlass27 = new widget.panelisi();
        panelGlass26 = new widget.panelisi();
        ChkTanggal = new widget.CekBox();
        DTPCari5 = new widget.Tanggal();
        jLabel74 = new widget.Label();
        DTPCari6 = new widget.Tanggal();
        jLabel75 = new widget.Label();
        kdpoli = new widget.TextBox();
        TPoli = new widget.TextBox();
        BtnUnit = new widget.Button();
        BtnHapusPoli = new widget.Button();
        panelGlass28 = new widget.panelisi();
        ChkLihat = new widget.CekBox();
        cmbBulan = new widget.ComboBox();
        jLabel76 = new widget.Label();
        BtnCari8 = new widget.Button();
        label_rehab = new widget.Label();
        BtnKeluar8 = new widget.Button();

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

        MnIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnIGD.setText("RM Gawat Darurat (IGD)");
        MnIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnIGD.setIconTextGap(5);
        MnIGD.setName("MnIGD"); // NOI18N
        MnIGD.setOpaque(true);
        MnIGD.setPreferredSize(new java.awt.Dimension(190, 26));

        MnTriase.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTriase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTriase.setText("Triase IGD");
        MnTriase.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTriase.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTriase.setIconTextGap(5);
        MnTriase.setName("MnTriase"); // NOI18N
        MnTriase.setPreferredSize(new java.awt.Dimension(190, 26));
        MnTriase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTriaseActionPerformed(evt);
            }
        });
        MnIGD.add(MnTriase);

        MnAsesmenMedikIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsesmenMedikIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsesmenMedikIGD.setText("Assesmen Medik IGD");
        MnAsesmenMedikIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsesmenMedikIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsesmenMedikIGD.setIconTextGap(5);
        MnAsesmenMedikIGD.setName("MnAsesmenMedikIGD"); // NOI18N
        MnAsesmenMedikIGD.setPreferredSize(new java.awt.Dimension(190, 26));
        MnAsesmenMedikIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsesmenMedikIGDActionPerformed(evt);
            }
        });
        MnIGD.add(MnAsesmenMedikIGD);

        MnAsesmenKeperawatanIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsesmenKeperawatanIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsesmenKeperawatanIGD.setText("Assesmen Keperawatan IGD");
        MnAsesmenKeperawatanIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsesmenKeperawatanIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsesmenKeperawatanIGD.setIconTextGap(5);
        MnAsesmenKeperawatanIGD.setName("MnAsesmenKeperawatanIGD"); // NOI18N
        MnAsesmenKeperawatanIGD.setPreferredSize(new java.awt.Dimension(190, 26));
        MnAsesmenKeperawatanIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsesmenKeperawatanIGDActionPerformed(evt);
            }
        });
        MnIGD.add(MnAsesmenKeperawatanIGD);

        MnAsesmenMedikObstetriIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsesmenMedikObstetriIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsesmenMedikObstetriIGD.setText("Asesmen Medik Obstetri");
        MnAsesmenMedikObstetriIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsesmenMedikObstetriIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsesmenMedikObstetriIGD.setIconTextGap(5);
        MnAsesmenMedikObstetriIGD.setName("MnAsesmenMedikObstetriIGD"); // NOI18N
        MnAsesmenMedikObstetriIGD.setPreferredSize(new java.awt.Dimension(190, 26));
        MnAsesmenMedikObstetriIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsesmenMedikObstetriIGDActionPerformed(evt);
            }
        });
        MnIGD.add(MnAsesmenMedikObstetriIGD);

        MnAsesmenKebidanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsesmenKebidanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsesmenKebidanan.setText("Asesmen Kebidanan (Ponek)");
        MnAsesmenKebidanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsesmenKebidanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsesmenKebidanan.setIconTextGap(5);
        MnAsesmenKebidanan.setName("MnAsesmenKebidanan"); // NOI18N
        MnAsesmenKebidanan.setPreferredSize(new java.awt.Dimension(190, 26));
        MnAsesmenKebidanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsesmenKebidananActionPerformed(evt);
            }
        });
        MnIGD.add(MnAsesmenKebidanan);

        jPopupMenu1.add(MnIGD);

        MnDiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDiagnosa.setText("Diagnosa Pasien (ICD)");
        MnDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiagnosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiagnosa.setIconTextGap(5);
        MnDiagnosa.setName("MnDiagnosa"); // NOI18N
        MnDiagnosa.setPreferredSize(new java.awt.Dimension(190, 26));
        MnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiagnosaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDiagnosa);

        MnCetakRingkasan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakRingkasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakRingkasan.setText("Cetak Ringkasan Pulang");
        MnCetakRingkasan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakRingkasan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakRingkasan.setIconTextGap(5);
        MnCetakRingkasan.setName("MnCetakRingkasan"); // NOI18N
        MnCetakRingkasan.setPreferredSize(new java.awt.Dimension(190, 26));
        MnCetakRingkasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakRingkasanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakRingkasan);

        MnGantiDokterSimpan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiDokterSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiDokterSimpan.setText("Ganti Dokter Penyimpan Data");
        MnGantiDokterSimpan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGantiDokterSimpan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGantiDokterSimpan.setIconTextGap(5);
        MnGantiDokterSimpan.setName("MnGantiDokterSimpan"); // NOI18N
        MnGantiDokterSimpan.setPreferredSize(new java.awt.Dimension(190, 26));
        MnGantiDokterSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiDokterSimpanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnGantiDokterSimpan);

        MnRiwayatData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRiwayatData.setText("Riwayat Data Terhapus");
        MnRiwayatData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRiwayatData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRiwayatData.setIconTextGap(5);
        MnRiwayatData.setName("MnRiwayatData"); // NOI18N
        MnRiwayatData.setPreferredSize(new java.awt.Dimension(190, 26));
        MnRiwayatData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatDataActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRiwayatData);

        MnBersihkanStringSampah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBersihkanStringSampah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBersihkanStringSampah.setText("Bersihkan String Sampah");
        MnBersihkanStringSampah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBersihkanStringSampah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBersihkanStringSampah.setIconTextGap(5);
        MnBersihkanStringSampah.setName("MnBersihkanStringSampah"); // NOI18N
        MnBersihkanStringSampah.setPreferredSize(new java.awt.Dimension(190, 26));
        MnBersihkanStringSampah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBersihkanStringSampahActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBersihkanStringSampah);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnHapusDipilih.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusDipilih.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusDipilih.setText("Hapus Pemeriksaan Dipilih");
        MnHapusDipilih.setName("MnHapusDipilih"); // NOI18N
        MnHapusDipilih.setPreferredSize(new java.awt.Dimension(208, 26));
        MnHapusDipilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusDipilihActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHapusDipilih);

        MnHapusSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        MnHapusSemua.setText("Hapus Semua Pemeriksaan Dipilih");
        MnHapusSemua.setName("MnHapusSemua"); // NOI18N
        MnHapusSemua.setPreferredSize(new java.awt.Dimension(208, 26));
        MnHapusSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusSemuaActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHapusSemua);

        jPopupMenu3.setName("jPopupMenu3"); // NOI18N

        MnJenisDokumen.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJenisDokumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnJenisDokumen.setText("Master Jenis Dokumen");
        MnJenisDokumen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnJenisDokumen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnJenisDokumen.setIconTextGap(5);
        MnJenisDokumen.setName("MnJenisDokumen"); // NOI18N
        MnJenisDokumen.setPreferredSize(new java.awt.Dimension(190, 26));
        MnJenisDokumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJenisDokumenActionPerformed(evt);
            }
        });
        jPopupMenu3.add(MnJenisDokumen);

        jPopupMenu4.setName("jPopupMenu4"); // NOI18N

        MnRehabMedik.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRehabMedik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRehabMedik.setText("Pilihan Rehabilitasi Medik");
        MnRehabMedik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRehabMedik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRehabMedik.setIconTextGap(5);
        MnRehabMedik.setName("MnRehabMedik"); // NOI18N
        MnRehabMedik.setPreferredSize(new java.awt.Dimension(170, 26));
        MnRehabMedik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRehabMedikActionPerformed(evt);
            }
        });
        jPopupMenu4.add(MnRehabMedik);

        TabTindakanPencegahan.setBackground(new java.awt.Color(255, 255, 254));
        TabTindakanPencegahan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabTindakanPencegahan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabTindakanPencegahan.setName("TabTindakanPencegahan"); // NOI18N

        panelBiasa6.setName("panelBiasa6"); // NOI18N
        panelBiasa6.setLayout(new java.awt.BorderLayout());

        TabPencegahanDewasa.setBackground(new java.awt.Color(255, 255, 254));
        TabPencegahanDewasa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabPencegahanDewasa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPencegahanDewasa.setName("TabPencegahanDewasa"); // NOI18N

        panelBiasa8.setName("panelBiasa8"); // NOI18N
        panelBiasa8.setLayout(new java.awt.BorderLayout());

        dewasaA.setEditable(false);
        dewasaA.setBackground(new java.awt.Color(255, 255, 255));
        dewasaA.setColumns(20);
        dewasaA.setRows(5);
        dewasaA.setText("1. Oreintasi lingkungan\n2. Posisi tempat tidur rendah dan terkunci\n3. Rel tempat tidur dipasang (dinaikkan)\n4. Pencahayaan adekuat\n5. Edukasi pencegahan jatuh");
        dewasaA.setName("dewasaA"); // NOI18N
        dewasaA.setOpaque(true);
        panelBiasa8.add(dewasaA, java.awt.BorderLayout.CENTER);

        TabPencegahanDewasa.addTab("Pencegahan Umum (A)", panelBiasa8);

        panelBiasa9.setName("panelBiasa9"); // NOI18N
        panelBiasa9.setLayout(new java.awt.BorderLayout());

        dewasaB.setEditable(false);
        dewasaB.setBackground(new java.awt.Color(255, 255, 255));
        dewasaB.setColumns(20);
        dewasaB.setRows(5);
        dewasaB.setText("1. Lakukan semua pencegahan umum (A)\n2. Menawarkan bantuan untuk ambulansi\n3. Beri tanda identifikasi dengan pin/kancing kuning pada gelang identitas");
        dewasaB.setName("dewasaB"); // NOI18N
        dewasaB.setOpaque(true);
        panelBiasa9.add(dewasaB, java.awt.BorderLayout.CENTER);

        TabPencegahanDewasa.addTab("Pencegahan Resiko Sedang (B)", panelBiasa9);

        panelBiasa10.setName("panelBiasa10"); // NOI18N
        panelBiasa10.setLayout(new java.awt.BorderLayout());

        dewasaC.setEditable(false);
        dewasaC.setBackground(new java.awt.Color(255, 255, 255));
        dewasaC.setColumns(20);
        dewasaC.setRows(5);
        dewasaC.setText("1. Lakukan semua pencegahan umum A dan B\n2. Beri tanda segitiga warna kuning pada bed pasien\n3. Kunjungi dan monitor setiap 1 jam\n4. Pastikan pasien menggunakan alat bantu\n5. Libatkan keluarga untuk mengawasi pasien");
        dewasaC.setName("dewasaC"); // NOI18N
        dewasaC.setOpaque(true);
        panelBiasa10.add(dewasaC, java.awt.BorderLayout.CENTER);

        TabPencegahanDewasa.addTab("Pencegahan Resiko Tinggi (C)", panelBiasa10);

        panelBiasa6.add(TabPencegahanDewasa, java.awt.BorderLayout.CENTER);

        TabTindakanPencegahan.addTab("DEWASA", panelBiasa6);

        panelBiasa7.setName("panelBiasa7"); // NOI18N
        panelBiasa7.setLayout(new java.awt.BorderLayout());

        TabPencegahanAnak.setBackground(new java.awt.Color(255, 255, 254));
        TabPencegahanAnak.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabPencegahanAnak.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPencegahanAnak.setName("TabPencegahanAnak"); // NOI18N

        panelBiasa14.setName("panelBiasa14"); // NOI18N
        panelBiasa14.setLayout(new java.awt.BorderLayout());

        anakA.setEditable(false);
        anakA.setBackground(new java.awt.Color(255, 255, 255));
        anakA.setColumns(20);
        anakA.setRows(5);
        anakA.setText("1. Oreintasi lingkungan\n2. Posisi tempat tidur rendah dan terkunci\n3. Rel tempat tidur dipasang (dinaikkan)\n4. Bel & barang pribadi dalam jangkauan\n5. Pencahayaan adekuat\n6. Edukasi pencegahan jatuh");
        anakA.setName("anakA"); // NOI18N
        anakA.setOpaque(true);
        panelBiasa14.add(anakA, java.awt.BorderLayout.CENTER);

        TabPencegahanAnak.addTab("Pencegahan Umum (A)", panelBiasa14);

        panelBiasa15.setName("panelBiasa15"); // NOI18N
        panelBiasa15.setLayout(new java.awt.BorderLayout());

        anakB.setEditable(false);
        anakB.setBackground(new java.awt.Color(255, 255, 255));
        anakB.setColumns(20);
        anakB.setRows(5);
        anakB.setText("1. Lakukan semua pencegahan umum (A)\n2. Beri tanda segitiga warna kuning pada bed/RM\n3. Beri tanda identifikasi dengan pin/kancing kuning pada gelang identitas\n4. Kunjungi dan monitor setiap 1 jam\n5. Libatkan keluarga untuk mengawasi pasien");
        anakB.setName("anakB"); // NOI18N
        anakB.setOpaque(true);
        panelBiasa15.add(anakB, java.awt.BorderLayout.CENTER);

        TabPencegahanAnak.addTab("Pencegahan Resiko Tinggi (B)", panelBiasa15);

        panelBiasa7.add(TabPencegahanAnak, java.awt.BorderLayout.CENTER);

        TabTindakanPencegahan.addTab("ANAK", panelBiasa7);

        Scroll8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " ASSESMEN RESIKO JATUH : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbFaktorResiko.setName("tbFaktorResiko"); // NOI18N
        Scroll8.setViewportView(tbFaktorResiko);

        WindowTTE.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTTE.setName("WindowTTE"); // NOI18N
        WindowTTE.setUndecorated(true);
        WindowTTE.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Bubuhkan Tanda Tangan Elektronik (TTE) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelisi3.setBackground(new java.awt.Color(255, 150, 255));
        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 70));
        panelisi3.setLayout(null);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Nama DPJP : ");
        jLabel60.setName("jLabel60"); // NOI18N
        panelisi3.add(jLabel60);
        jLabel60.setBounds(0, 10, 100, 23);

        kddokter.setEditable(false);
        kddokter.setForeground(new java.awt.Color(0, 0, 0));
        kddokter.setName("kddokter"); // NOI18N
        panelisi3.add(kddokter);
        kddokter.setBounds(100, 10, 90, 23);

        TDokter.setEditable(false);
        TDokter.setForeground(new java.awt.Color(0, 0, 0));
        TDokter.setName("TDokter"); // NOI18N
        panelisi3.add(TDokter);
        TDokter.setBounds(193, 10, 271, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Passphrase : ");
        jLabel61.setName("jLabel61"); // NOI18N
        panelisi3.add(jLabel61);
        jLabel61.setBounds(0, 38, 100, 23);

        Tpaspras.setForeground(new java.awt.Color(0, 0, 0));
        Tpaspras.setToolTipText("Silahkan masukkan Passphrase");
        Tpaspras.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tpaspras.setName("Tpaspras"); // NOI18N
        panelisi3.add(Tpaspras);
        Tpaspras.setBounds(100, 38, 364, 23);

        internalFrame3.add(panelisi3, java.awt.BorderLayout.CENTER);

        panelisi4.setBackground(new java.awt.Color(255, 150, 255));
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 47));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 9));

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Export.png"))); // NOI18N
        BtnSimpan1.setText("Submit");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        panelisi4.add(BtnSimpan1);

        BtnCloseIn1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
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

        internalFrame3.add(panelisi4, java.awt.BorderLayout.PAGE_END);

        WindowTTE.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        WindowPasien.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowPasien.setName("WindowPasien"); // NOI18N
        WindowPasien.setUndecorated(true);
        WindowPasien.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pasien Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        tbPasien.setToolTipText("Silahkan klik pilih salah satu data pasiennya");
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
        Scroll9.setViewportView(tbPasien);

        internalFrame5.add(Scroll9, java.awt.BorderLayout.CENTER);

        panelisi6.setBackground(new java.awt.Color(255, 150, 255));
        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi6.add(jLabel6);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi6.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
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
        panelisi6.add(BtnCari1);

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
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
        panelisi6.add(BtnAll1);

        BtnCloseIn2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn2.setText("Tutup");
        BtnCloseIn2.setToolTipText("Alt+U");
        BtnCloseIn2.setName("BtnCloseIn2"); // NOI18N
        BtnCloseIn2.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnCloseIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn2ActionPerformed(evt);
            }
        });
        panelisi6.add(BtnCloseIn2);

        internalFrame5.add(panelisi6, java.awt.BorderLayout.PAGE_END);

        WindowPasien.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowDPJPranap.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDPJPranap.setName("WindowDPJPranap"); // NOI18N
        WindowDPJPranap.setUndecorated(true);
        WindowDPJPranap.setResizable(false);

        internalFrame15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ DPJP Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame15.setName("internalFrame15"); // NOI18N
        internalFrame15.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame15.setLayout(null);

        BtnCloseIn10.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn10.setText("Tutup");
        BtnCloseIn10.setToolTipText("Alt+U");
        BtnCloseIn10.setName("BtnCloseIn10"); // NOI18N
        BtnCloseIn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn10ActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnCloseIn10);
        BtnCloseIn10.setBounds(480, 60, 100, 30);

        BtnSimpan6.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan6.setText("Simpan");
        BtnSimpan6.setToolTipText("Alt+S");
        BtnSimpan6.setName("BtnSimpan6"); // NOI18N
        BtnSimpan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan6ActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnSimpan6);
        BtnSimpan6.setBounds(370, 60, 100, 30);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Nama DPJP :");
        jLabel62.setName("jLabel62"); // NOI18N
        internalFrame15.add(jLabel62);
        jLabel62.setBounds(0, 32, 77, 23);

        kddpjp.setEditable(false);
        kddpjp.setForeground(new java.awt.Color(0, 0, 0));
        kddpjp.setName("kddpjp"); // NOI18N
        internalFrame15.add(kddpjp);
        kddpjp.setBounds(81, 32, 100, 23);

        nmdpjp.setEditable(false);
        nmdpjp.setForeground(new java.awt.Color(0, 0, 0));
        nmdpjp.setName("nmdpjp"); // NOI18N
        internalFrame15.add(nmdpjp);
        nmdpjp.setBounds(183, 32, 380, 23);

        btnDPJP.setForeground(new java.awt.Color(0, 0, 0));
        btnDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDPJP.setToolTipText("ALt+7");
        btnDPJP.setName("btnDPJP"); // NOI18N
        btnDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDPJPActionPerformed(evt);
            }
        });
        internalFrame15.add(btnDPJP);
        btnDPJP.setBounds(565, 32, 28, 23);

        WindowDPJPranap.getContentPane().add(internalFrame15, java.awt.BorderLayout.CENTER);

        WindowDokterPenyimpan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDokterPenyimpan.setName("WindowDokterPenyimpan"); // NOI18N
        WindowDokterPenyimpan.setUndecorated(true);
        WindowDokterPenyimpan.setResizable(false);

        internalFrame16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Dokter Penyimpan Data Resume Medis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame16.setName("internalFrame16"); // NOI18N
        internalFrame16.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame16.setLayout(null);

        BtnCloseIn11.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn11.setText("Tutup");
        BtnCloseIn11.setToolTipText("Alt+U");
        BtnCloseIn11.setName("BtnCloseIn11"); // NOI18N
        BtnCloseIn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn11ActionPerformed(evt);
            }
        });
        internalFrame16.add(BtnCloseIn11);
        BtnCloseIn11.setBounds(480, 60, 100, 30);

        BtnSimpan7.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan7.setText("Simpan");
        BtnSimpan7.setToolTipText("Alt+S");
        BtnSimpan7.setName("BtnSimpan7"); // NOI18N
        BtnSimpan7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan7ActionPerformed(evt);
            }
        });
        internalFrame16.add(BtnSimpan7);
        BtnSimpan7.setBounds(370, 60, 100, 30);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Nama Dokter :");
        jLabel63.setName("jLabel63"); // NOI18N
        internalFrame16.add(jLabel63);
        jLabel63.setBounds(0, 32, 97, 23);

        kddokter1.setEditable(false);
        kddokter1.setForeground(new java.awt.Color(0, 0, 0));
        kddokter1.setName("kddokter1"); // NOI18N
        internalFrame16.add(kddokter1);
        kddokter1.setBounds(101, 32, 100, 23);

        nmdokter1.setEditable(false);
        nmdokter1.setForeground(new java.awt.Color(0, 0, 0));
        nmdokter1.setName("nmdokter1"); // NOI18N
        internalFrame16.add(nmdokter1);
        nmdokter1.setBounds(203, 32, 380, 23);

        btnDokter1.setForeground(new java.awt.Color(0, 0, 0));
        btnDokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter1.setToolTipText("ALt+7");
        btnDokter1.setName("btnDokter1"); // NOI18N
        btnDokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter1ActionPerformed(evt);
            }
        });
        internalFrame16.add(btnDokter1);
        btnDokter1.setBounds(585, 32, 28, 23);

        WindowDokterPenyimpan.getContentPane().add(internalFrame16, java.awt.BorderLayout.CENTER);

        WindowHapusDokJangMed.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowHapusDokJangMed.setName("WindowHapusDokJangMed"); // NOI18N
        WindowHapusDokJangMed.setUndecorated(true);
        WindowHapusDokJangMed.setResizable(false);

        internalFrame32.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Hapus File Dokumen Penunjang Medis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame32.setName("internalFrame32"); // NOI18N
        internalFrame32.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame32.setLayout(new java.awt.BorderLayout());

        internalFrame33.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame33.setName("internalFrame33"); // NOI18N
        internalFrame33.setPreferredSize(new java.awt.Dimension(400, 88));
        internalFrame33.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame33.setLayout(new java.awt.BorderLayout());

        internalFrame34.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame34.setName("internalFrame34"); // NOI18N
        internalFrame34.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame34.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame34.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 7, 9));

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Key Word :");
        jLabel71.setName("jLabel71"); // NOI18N
        jLabel71.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame34.add(jLabel71);

        TCari5.setForeground(new java.awt.Color(0, 0, 0));
        TCari5.setName("TCari5"); // NOI18N
        TCari5.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari5KeyPressed(evt);
            }
        });
        internalFrame34.add(TCari5);

        BtnCari7.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari7.setText("Tampilkan Data");
        BtnCari7.setToolTipText("Alt+1");
        BtnCari7.setName("BtnCari7"); // NOI18N
        BtnCari7.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari7ActionPerformed(evt);
            }
        });
        BtnCari7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari7KeyPressed(evt);
            }
        });
        internalFrame34.add(BtnCari7);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Record :");
        jLabel72.setName("jLabel72"); // NOI18N
        jLabel72.setPreferredSize(new java.awt.Dimension(55, 23));
        internalFrame34.add(jLabel72);

        LCount2.setForeground(new java.awt.Color(0, 0, 0));
        LCount2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount2.setText("0");
        LCount2.setName("LCount2"); // NOI18N
        LCount2.setPreferredSize(new java.awt.Dimension(50, 23));
        internalFrame34.add(LCount2);

        internalFrame33.add(internalFrame34, java.awt.BorderLayout.CENTER);

        internalFrame35.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame35.setName("internalFrame35"); // NOI18N
        internalFrame35.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame35.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame35.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 7, 9));

        ChkDokumen1.setBackground(new java.awt.Color(255, 255, 250));
        ChkDokumen1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDokumen1.setForeground(new java.awt.Color(0, 0, 0));
        ChkDokumen1.setText("Semua Dokumen Penunjang Medis");
        ChkDokumen1.setBorderPainted(true);
        ChkDokumen1.setBorderPaintedFlat(true);
        ChkDokumen1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDokumen1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDokumen1.setName("ChkDokumen1"); // NOI18N
        ChkDokumen1.setOpaque(false);
        ChkDokumen1.setPreferredSize(new java.awt.Dimension(210, 23));
        ChkDokumen1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkDokumen1ActionPerformed(evt);
            }
        });
        internalFrame35.add(ChkDokumen1);

        BtnHapusFile.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusFile.setText("Setuju Dihapus");
        BtnHapusFile.setToolTipText("Alt+D");
        BtnHapusFile.setName("BtnHapusFile"); // NOI18N
        BtnHapusFile.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnHapusFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusFileActionPerformed(evt);
            }
        });
        internalFrame35.add(BtnHapusFile);

        BtnKeluar2.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setText("Keluar");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        BtnKeluar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar2KeyPressed(evt);
            }
        });
        internalFrame35.add(BtnKeluar2);

        internalFrame33.add(internalFrame35, java.awt.BorderLayout.PAGE_END);

        internalFrame32.add(internalFrame33, java.awt.BorderLayout.PAGE_END);

        Scroll29.setName("Scroll29"); // NOI18N
        Scroll29.setOpaque(true);

        tbHapus.setToolTipText("Silahkan pilih salah satu data yang mau dihapus");
        tbHapus.setName("tbHapus"); // NOI18N
        Scroll29.setViewportView(tbHapus);

        internalFrame32.add(Scroll29, java.awt.BorderLayout.CENTER);

        WindowHapusDokJangMed.getContentPane().add(internalFrame32, java.awt.BorderLayout.CENTER);

        WindowRehabMedik.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRehabMedik.setName("WindowRehabMedik"); // NOI18N
        WindowRehabMedik.setUndecorated(true);
        WindowRehabMedik.setResizable(false);

        internalFrame37.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pilihan Rehabilitasi Medik ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame37.setName("internalFrame37"); // NOI18N
        internalFrame37.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame37.setLayout(null);

        BtnCloseIn9.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn9.setText("Tutup");
        BtnCloseIn9.setToolTipText("Alt+U");
        BtnCloseIn9.setName("BtnCloseIn9"); // NOI18N
        BtnCloseIn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn9ActionPerformed(evt);
            }
        });
        internalFrame37.add(BtnCloseIn9);
        BtnCloseIn9.setBounds(410, 30, 100, 30);

        BtnSimpan8.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan8.setText("Simpan");
        BtnSimpan8.setToolTipText("Alt+S");
        BtnSimpan8.setName("BtnSimpan8"); // NOI18N
        BtnSimpan8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan8ActionPerformed(evt);
            }
        });
        internalFrame37.add(BtnSimpan8);
        BtnSimpan8.setBounds(300, 30, 100, 30);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Jenis Rehabilitasi Medik : ");
        jLabel77.setName("jLabel77"); // NOI18N
        internalFrame37.add(jLabel77);
        jLabel77.setBounds(0, 32, 150, 23);

        cmbRM.setForeground(new java.awt.Color(0, 0, 0));
        cmbRM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "FISIOTERAPI", "OKUPASI TERAPI", "TERAPI WICARA" }));
        cmbRM.setName("cmbRM"); // NOI18N
        internalFrame37.add(cmbRM);
        cmbRM.setBounds(155, 32, 140, 23);

        WindowRehabMedik.getContentPane().add(internalFrame37, java.awt.BorderLayout.CENTER);

        WindowRiwayat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayat.setName("WindowRiwayat"); // NOI18N
        WindowRiwayat.setUndecorated(true);
        WindowRiwayat.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Ringkasan Pulang Pasien Terhapus ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Tgl. Terhapus :");
        jLabel101.setName("jLabel101"); // NOI18N
        jLabel101.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(jLabel101);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-09-2025" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari3);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("s.d.");
        jLabel102.setName("jLabel102"); // NOI18N
        jLabel102.setPreferredSize(new java.awt.Dimension(23, 23));
        internalFrame17.add(jLabel102);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-09-2025" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari4);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Key Word :");
        jLabel103.setName("jLabel103"); // NOI18N
        jLabel103.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame17.add(jLabel103);

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

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Record :");
        jLabel104.setName("jLabel104"); // NOI18N
        jLabel104.setPreferredSize(new java.awt.Dimension(65, 23));
        internalFrame17.add(jLabel104);

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

        BtnAll2.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll2.setText("Semua Data");
        BtnAll2.setToolTipText("Alt+2");
        BtnAll2.setName("BtnAll2"); // NOI18N
        BtnAll2.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll2ActionPerformed(evt);
            }
        });
        BtnAll2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll2KeyPressed(evt);
            }
        });
        internalFrame19.add(BtnAll2);

        BtnRestor.setForeground(new java.awt.Color(0, 0, 0));
        BtnRestor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
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

        BtnCloseIn12.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn12.setText("Tutup");
        BtnCloseIn12.setToolTipText("Alt+U");
        BtnCloseIn12.setName("BtnCloseIn12"); // NOI18N
        BtnCloseIn12.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnCloseIn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn12ActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnCloseIn12);

        internalFrame18.add(internalFrame19, java.awt.BorderLayout.PAGE_END);

        internalFrame13.add(internalFrame18, java.awt.BorderLayout.PAGE_END);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbRiwayat.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/direstore");
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ringkasan Pulang Pasien Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRingkasan.setForeground(new java.awt.Color(0, 0, 0));
        TabRingkasan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRingkasan.setName("TabRingkasan"); // NOI18N
        TabRingkasan.setPreferredSize(new java.awt.Dimension(400, 210));
        TabRingkasan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRingkasanMouseClicked(evt);
            }
        });

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 200));

        panelisi1.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis");
        panelisi1.setComponentPopupMenu(jPopupMenu1);
        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(1087, 1340));
        panelisi1.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Pasien :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelisi1.add(jLabel4);
        jLabel4.setBounds(0, 8, 100, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setToolTipText("");
        TNoRM.setName("TNoRM"); // NOI18N
        panelisi1.add(TNoRM);
        TNoRM.setBounds(240, 8, 75, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setToolTipText("");
        TNoRW.setName("TNoRW"); // NOI18N
        panelisi1.add(TNoRW);
        TNoRW.setBounds(105, 8, 131, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelisi1.add(jLabel8);
        jLabel8.setBounds(0, 36, 100, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setToolTipText("");
        TNmPasien.setName("TNmPasien"); // NOI18N
        panelisi1.add(TNmPasien);
        TNmPasien.setBounds(318, 8, 410, 23);

        TTglLhr.setEditable(false);
        TTglLhr.setForeground(new java.awt.Color(0, 0, 0));
        TTglLhr.setToolTipText("");
        TTglLhr.setHighlighter(null);
        TTglLhr.setName("TTglLhr"); // NOI18N
        panelisi1.add(TTglLhr);
        TTglLhr.setBounds(105, 36, 100, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Jns. Kelamin :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelisi1.add(jLabel10);
        jLabel10.setBounds(210, 36, 70, 23);

        TJK.setEditable(false);
        TJK.setForeground(new java.awt.Color(0, 0, 0));
        TJK.setToolTipText("");
        TJK.setHighlighter(null);
        TJK.setName("TJK"); // NOI18N
        panelisi1.add(TJK);
        TJK.setBounds(285, 36, 80, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tgl. Masuk :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelisi1.add(jLabel11);
        jLabel11.setBounds(365, 36, 74, 23);

        TTglMsk.setEditable(false);
        TTglMsk.setForeground(new java.awt.Color(0, 0, 0));
        TTglMsk.setToolTipText("");
        TTglMsk.setHighlighter(null);
        TTglMsk.setName("TTglMsk"); // NOI18N
        panelisi1.add(TTglMsk);
        TTglMsk.setBounds(445, 36, 100, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Pulang :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelisi1.add(jLabel12);
        jLabel12.setBounds(547, 36, 75, 23);

        TTglPulang.setEditable(false);
        TTglPulang.setForeground(new java.awt.Color(0, 0, 0));
        TTglPulang.setToolTipText("");
        TTglPulang.setHighlighter(null);
        TTglPulang.setName("TTglPulang"); // NOI18N
        panelisi1.add(TTglPulang);
        TTglPulang.setBounds(627, 36, 100, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Ruang/Kelas :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelisi1.add(jLabel13);
        jLabel13.setBounds(0, 64, 100, 23);

        TRuangrawat.setEditable(false);
        TRuangrawat.setForeground(new java.awt.Color(0, 0, 0));
        TRuangrawat.setToolTipText("");
        TRuangrawat.setHighlighter(null);
        TRuangrawat.setName("TRuangrawat"); // NOI18N
        panelisi1.add(TRuangrawat);
        TRuangrawat.setBounds(105, 64, 260, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Cara Bayar :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelisi1.add(jLabel14);
        jLabel14.setBounds(365, 64, 74, 23);

        TCaraBayar.setEditable(false);
        TCaraBayar.setForeground(new java.awt.Color(0, 0, 0));
        TCaraBayar.setToolTipText("");
        TCaraBayar.setHighlighter(null);
        TCaraBayar.setName("TCaraBayar"); // NOI18N
        panelisi1.add(TCaraBayar);
        TCaraBayar.setBounds(445, 64, 282, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Dokter Pengirim :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelisi1.add(jLabel15);
        jLabel15.setBounds(0, 120, 100, 23);

        TNmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TNmDokter.setToolTipText("");
        TNmDokter.setName("TNmDokter"); // NOI18N
        TNmDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNmDokterKeyPressed(evt);
            }
        });
        panelisi1.add(TNmDokter);
        TNmDokter.setBounds(105, 120, 350, 23);

        BtnDokter.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setToolTipText("Alt+X");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        panelisi1.add(BtnDokter);
        BtnDokter.setBounds(460, 120, 30, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Nama DPJP :");
        jLabel43.setName("jLabel43"); // NOI18N
        panelisi1.add(jLabel43);
        jLabel43.setBounds(0, 92, 100, 23);

        Tdpjp.setEditable(false);
        Tdpjp.setForeground(new java.awt.Color(0, 0, 0));
        Tdpjp.setToolTipText("");
        Tdpjp.setHighlighter(null);
        Tdpjp.setName("Tdpjp"); // NOI18N
        panelisi1.add(Tdpjp);
        Tdpjp.setBounds(105, 92, 350, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("* MOHON UNTUK TIDAK MENGGUNAKAN SINGKATAN DALAM PENGETIKAN DIAGNOSIS DAN TINDAKAN");
        jLabel45.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel45.setName("jLabel45"); // NOI18N
        panelisi1.add(jLabel45);
        jLabel45.setBounds(10, 148, 510, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("* MOHON UNTUK DIKETIK DENGAN RAPI DAN DAPAT DIBACA DENGAN JELAS");
        jLabel44.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel44.setName("jLabel44"); // NOI18N
        panelisi1.add(jLabel44);
        jLabel44.setBounds(10, 162, 400, 23);

        Scroll16.setName("Scroll16"); // NOI18N
        Scroll16.setOpaque(true);

        TAlasanDirawat.setColumns(20);
        TAlasanDirawat.setRows(5);
        TAlasanDirawat.setName("TAlasanDirawat"); // NOI18N
        TAlasanDirawat.setPreferredSize(new java.awt.Dimension(190, 2000));
        TAlasanDirawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlasanDirawatKeyPressed(evt);
            }
        });
        Scroll16.setViewportView(TAlasanDirawat);

        panelisi1.add(Scroll16);
        Scroll16.setBounds(135, 190, 590, 80);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Alasan Masuk Dirawat :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelisi1.add(jLabel16);
        jLabel16.setBounds(0, 190, 130, 23);

        Scroll17.setName("Scroll17"); // NOI18N
        Scroll17.setOpaque(true);

        TRingkasanRiwayat.setColumns(20);
        TRingkasanRiwayat.setRows(5);
        TRingkasanRiwayat.setName("TRingkasanRiwayat"); // NOI18N
        TRingkasanRiwayat.setPreferredSize(new java.awt.Dimension(190, 2000));
        TRingkasanRiwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRingkasanRiwayatKeyPressed(evt);
            }
        });
        Scroll17.setViewportView(TRingkasanRiwayat);

        panelisi1.add(Scroll17);
        Scroll17.setBounds(135, 275, 590, 80);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Ringkasan Riwayat ");
        jLabel17.setName("jLabel17"); // NOI18N
        panelisi1.add(jLabel17);
        jLabel17.setBounds(0, 275, 130, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Penyakit :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelisi1.add(jLabel18);
        jLabel18.setBounds(0, 290, 130, 23);

        Scroll26.setName("Scroll26"); // NOI18N
        Scroll26.setOpaque(true);

        TPemeriksaanFisik.setColumns(20);
        TPemeriksaanFisik.setRows(5);
        TPemeriksaanFisik.setName("TPemeriksaanFisik"); // NOI18N
        TPemeriksaanFisik.setPreferredSize(new java.awt.Dimension(190, 2000));
        TPemeriksaanFisik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaanFisikKeyPressed(evt);
            }
        });
        Scroll26.setViewportView(TPemeriksaanFisik);

        panelisi1.add(Scroll26);
        Scroll26.setBounds(135, 360, 590, 220);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Pemeriksaan Fisik :");
        jLabel40.setName("jLabel40"); // NOI18N
        panelisi1.add(jLabel40);
        jLabel40.setBounds(0, 360, 130, 23);

        Scroll18.setName("Scroll18"); // NOI18N
        Scroll18.setOpaque(true);

        TPemeriksaanPenunjang.setColumns(20);
        TPemeriksaanPenunjang.setRows(5);
        TPemeriksaanPenunjang.setName("TPemeriksaanPenunjang"); // NOI18N
        TPemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(190, 14000));
        TPemeriksaanPenunjang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaanPenunjangKeyPressed(evt);
            }
        });
        Scroll18.setViewportView(TPemeriksaanPenunjang);

        panelisi1.add(Scroll18);
        Scroll18.setBounds(135, 585, 590, 740);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Pemeriksaan Penunjang");
        jLabel19.setName("jLabel19"); // NOI18N
        panelisi1.add(jLabel19);
        jLabel19.setBounds(0, 585, 130, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Diagnostik/Laboratorium :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelisi1.add(jLabel20);
        jLabel20.setBounds(0, 600, 130, 23);

        Scroll19.setName("Scroll19"); // NOI18N
        Scroll19.setOpaque(true);

        TTerapiPengobatan.setColumns(20);
        TTerapiPengobatan.setRows(5);
        TTerapiPengobatan.setName("TTerapiPengobatan"); // NOI18N
        TTerapiPengobatan.setPreferredSize(new java.awt.Dimension(190, 2000));
        TTerapiPengobatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTerapiPengobatanKeyPressed(evt);
            }
        });
        Scroll19.setViewportView(TTerapiPengobatan);

        panelisi1.add(Scroll19);
        Scroll19.setBounds(865, 190, 590, 80);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Terapi Pengobatan ");
        jLabel21.setName("jLabel21"); // NOI18N
        panelisi1.add(jLabel21);
        jLabel21.setBounds(730, 190, 130, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("selama dirawat & efek ");
        jLabel22.setName("jLabel22"); // NOI18N
        panelisi1.add(jLabel22);
        jLabel22.setBounds(730, 205, 130, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("samping (bila ada) :");
        jLabel23.setName("jLabel23"); // NOI18N
        panelisi1.add(jLabel23);
        jLabel23.setBounds(730, 220, 130, 23);

        Scroll20.setName("Scroll20"); // NOI18N
        Scroll20.setOpaque(true);

        TDiagUtama.setColumns(20);
        TDiagUtama.setRows(5);
        TDiagUtama.setName("TDiagUtama"); // NOI18N
        TDiagUtama.setPreferredSize(new java.awt.Dimension(190, 2000));
        TDiagUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagUtamaKeyPressed(evt);
            }
        });
        Scroll20.setViewportView(TDiagUtama);

        panelisi1.add(Scroll20);
        Scroll20.setBounds(865, 275, 590, 80);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Diagnosa Utama/ ");
        jLabel28.setName("jLabel28"); // NOI18N
        panelisi1.add(jLabel28);
        jLabel28.setBounds(730, 275, 130, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Primer :");
        jLabel24.setName("jLabel24"); // NOI18N
        panelisi1.add(jLabel24);
        jLabel24.setBounds(730, 290, 130, 23);

        Scroll21.setName("Scroll21"); // NOI18N
        Scroll21.setOpaque(true);

        TDiagSekunder.setColumns(20);
        TDiagSekunder.setRows(5);
        TDiagSekunder.setName("TDiagSekunder"); // NOI18N
        TDiagSekunder.setPreferredSize(new java.awt.Dimension(190, 2000));
        TDiagSekunder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagSekunderKeyPressed(evt);
            }
        });
        Scroll21.setViewportView(TDiagSekunder);

        panelisi1.add(Scroll21);
        Scroll21.setBounds(865, 360, 590, 70);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Diagnosa Sekunder :");
        jLabel25.setName("jLabel25"); // NOI18N
        panelisi1.add(jLabel25);
        jLabel25.setBounds(730, 360, 130, 23);

        Scroll22.setName("Scroll22"); // NOI18N
        Scroll22.setOpaque(true);

        TTindakan.setColumns(20);
        TTindakan.setRows(5);
        TTindakan.setName("TTindakan"); // NOI18N
        TTindakan.setPreferredSize(new java.awt.Dimension(190, 2000));
        TTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTindakanKeyPressed(evt);
            }
        });
        Scroll22.setViewportView(TTindakan);

        panelisi1.add(Scroll22);
        Scroll22.setBounds(865, 495, 590, 80);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Tindakan Prosedur :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelisi1.add(jLabel26);
        jLabel26.setBounds(730, 495, 130, 23);

        Scroll23.setName("Scroll23"); // NOI18N
        Scroll23.setOpaque(true);

        TKeadaanumum.setColumns(20);
        TKeadaanumum.setRows(5);
        TKeadaanumum.setName("TKeadaanumum"); // NOI18N
        TKeadaanumum.setPreferredSize(new java.awt.Dimension(190, 2000));
        TKeadaanumum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKeadaanumumKeyPressed(evt);
            }
        });
        Scroll23.setViewportView(TKeadaanumum);

        panelisi1.add(Scroll23);
        Scroll23.setBounds(865, 580, 590, 80);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Keadaan Umum :");
        jLabel27.setName("jLabel27"); // NOI18N
        panelisi1.add(jLabel27);
        jLabel27.setBounds(730, 580, 130, 23);

        Scroll24.setName("Scroll24"); // NOI18N
        Scroll24.setOpaque(true);

        TKesadaran.setColumns(20);
        TKesadaran.setRows(5);
        TKesadaran.setName("TKesadaran"); // NOI18N
        TKesadaran.setPreferredSize(new java.awt.Dimension(190, 2000));
        TKesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKesadaranKeyPressed(evt);
            }
        });
        Scroll24.setViewportView(TKesadaran);

        panelisi1.add(Scroll24);
        Scroll24.setBounds(865, 665, 590, 80);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Kesadaran :");
        jLabel29.setName("jLabel29"); // NOI18N
        panelisi1.add(jLabel29);
        jLabel29.setBounds(730, 665, 130, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tanda Vital :");
        jLabel30.setName("jLabel30"); // NOI18N
        panelisi1.add(jLabel30);
        jLabel30.setBounds(730, 780, 130, 23);

        TTensi.setForeground(new java.awt.Color(0, 0, 0));
        TTensi.setToolTipText("");
        TTensi.setName("TTensi"); // NOI18N
        TTensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTensiKeyPressed(evt);
            }
        });
        panelisi1.add(TTensi);
        TTensi.setBounds(865, 800, 70, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Suhu :");
        jLabel31.setName("jLabel31"); // NOI18N
        panelisi1.add(jLabel31);
        jLabel31.setBounds(990, 800, 50, 23);

        TSuhu.setForeground(new java.awt.Color(0, 0, 0));
        TSuhu.setToolTipText("");
        TSuhu.setName("TSuhu"); // NOI18N
        TSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSuhuKeyPressed(evt);
            }
        });
        panelisi1.add(TSuhu);
        TSuhu.setBounds(1045, 800, 70, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Nadi :");
        jLabel32.setName("jLabel32"); // NOI18N
        panelisi1.add(jLabel32);
        jLabel32.setBounds(1145, 800, 50, 23);

        TNadi.setForeground(new java.awt.Color(0, 0, 0));
        TNadi.setToolTipText("");
        TNadi.setName("TNadi"); // NOI18N
        TNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNadiKeyPressed(evt);
            }
        });
        panelisi1.add(TNadi);
        TNadi.setBounds(1200, 800, 70, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Tekanan darah :");
        jLabel49.setName("jLabel49"); // NOI18N
        panelisi1.add(jLabel49);
        jLabel49.setBounds(730, 800, 130, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Frekuensi Nafas :");
        jLabel33.setName("jLabel33"); // NOI18N
        panelisi1.add(jLabel33);
        jLabel33.setBounds(730, 828, 130, 23);

        TFrekuensiNafas.setForeground(new java.awt.Color(0, 0, 0));
        TFrekuensiNafas.setToolTipText("");
        TFrekuensiNafas.setName("TFrekuensiNafas"); // NOI18N
        TFrekuensiNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TFrekuensiNafasKeyPressed(evt);
            }
        });
        panelisi1.add(TFrekuensiNafas);
        TFrekuensiNafas.setBounds(865, 828, 70, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("GCS :");
        jLabel37.setName("jLabel37"); // NOI18N
        panelisi1.add(jLabel37);
        jLabel37.setBounds(990, 828, 50, 23);

        Tgcs.setForeground(new java.awt.Color(0, 0, 0));
        Tgcs.setToolTipText("");
        Tgcs.setName("Tgcs"); // NOI18N
        Tgcs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsKeyPressed(evt);
            }
        });
        panelisi1.add(Tgcs);
        Tgcs.setBounds(1045, 828, 235, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Pengobatan Lanjutan :");
        jLabel34.setName("jLabel34"); // NOI18N
        panelisi1.add(jLabel34);
        jLabel34.setBounds(730, 856, 130, 23);

        cmbLanjutan.setForeground(new java.awt.Color(0, 0, 0));
        cmbLanjutan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Poliklinik", "RS Lain", "Puskesmas", "Dokter Luar" }));
        cmbLanjutan.setName("cmbLanjutan"); // NOI18N
        cmbLanjutan.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbLanjutan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLanjutanActionPerformed(evt);
            }
        });
        cmbLanjutan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbLanjutanKeyPressed(evt);
            }
        });
        panelisi1.add(cmbLanjutan);
        cmbLanjutan.setBounds(865, 856, 110, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Nama Dokter :");
        jLabel35.setName("jLabel35"); // NOI18N
        panelisi1.add(jLabel35);
        jLabel35.setBounds(975, 856, 85, 23);

        TDokterLuar.setForeground(new java.awt.Color(0, 0, 0));
        TDokterLuar.setToolTipText("");
        TDokterLuar.setName("TDokterLuar"); // NOI18N
        TDokterLuar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDokterLuarKeyPressed(evt);
            }
        });
        panelisi1.add(TDokterLuar);
        TDokterLuar.setBounds(1065, 856, 385, 23);

        chkTglKontrol.setBackground(new java.awt.Color(242, 242, 242));
        chkTglKontrol.setBorder(null);
        chkTglKontrol.setForeground(new java.awt.Color(0, 0, 0));
        chkTglKontrol.setText("Tgl. Kontrol Poli :");
        chkTglKontrol.setBorderPainted(true);
        chkTglKontrol.setBorderPaintedFlat(true);
        chkTglKontrol.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglKontrol.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglKontrol.setName("chkTglKontrol"); // NOI18N
        chkTglKontrol.setOpaque(false);
        chkTglKontrol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglKontrolActionPerformed(evt);
            }
        });
        panelisi1.add(chkTglKontrol);
        chkTglKontrol.setBounds(730, 884, 130, 23);

        TglKontrol.setEditable(false);
        TglKontrol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-09-2025" }));
        TglKontrol.setDisplayFormat("dd-MM-yyyy");
        TglKontrol.setName("TglKontrol"); // NOI18N
        TglKontrol.setOpaque(false);
        TglKontrol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglKontrolKeyPressed(evt);
            }
        });
        panelisi1.add(TglKontrol);
        TglKontrol.setBounds(865, 884, 90, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Kondisi Waktu Pulang :");
        jLabel41.setName("jLabel41"); // NOI18N
        panelisi1.add(jLabel41);
        jLabel41.setBounds(956, 884, 130, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Catatan Penting ");
        jLabel36.setName("jLabel36"); // NOI18N
        panelisi1.add(jLabel36);
        jLabel36.setBounds(730, 1075, 130, 23);

        Scroll25.setName("Scroll25"); // NOI18N
        Scroll25.setOpaque(true);

        TCatatan.setColumns(20);
        TCatatan.setRows(5);
        TCatatan.setName("TCatatan"); // NOI18N
        TCatatan.setPreferredSize(new java.awt.Dimension(190, 2000));
        TCatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCatatanKeyPressed(evt);
            }
        });
        Scroll25.setViewportView(TCatatan);

        panelisi1.add(Scroll25);
        Scroll25.setBounds(865, 1075, 590, 71);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("(kondisi saat ini) :");
        jLabel39.setName("jLabel39"); // NOI18N
        panelisi1.add(jLabel39);
        jLabel39.setBounds(730, 1090, 130, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Terapi Pulang :");
        jLabel42.setName("jLabel42"); // NOI18N
        panelisi1.add(jLabel42);
        jLabel42.setBounds(730, 1150, 130, 23);

        Scroll27.setName("Scroll27"); // NOI18N
        Scroll27.setOpaque(true);

        TTerapiPulang.setColumns(20);
        TTerapiPulang.setRows(5);
        TTerapiPulang.setName("TTerapiPulang"); // NOI18N
        TTerapiPulang.setPreferredSize(new java.awt.Dimension(190, 8000));
        TTerapiPulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTerapiPulangKeyPressed(evt);
            }
        });
        Scroll27.setViewportView(TTerapiPulang);

        panelisi1.add(Scroll27);
        Scroll27.setBounds(865, 1150, 590, 175);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("mmHg");
        jLabel50.setName("jLabel50"); // NOI18N
        panelisi1.add(jLabel50);
        jLabel50.setBounds(940, 800, 40, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("x/menit");
        jLabel51.setName("jLabel51"); // NOI18N
        panelisi1.add(jLabel51);
        jLabel51.setBounds(940, 828, 40, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("°C");
        jLabel52.setName("jLabel52"); // NOI18N
        panelisi1.add(jLabel52);
        jLabel52.setBounds(1120, 800, 30, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("x/menit");
        jLabel53.setName("jLabel53"); // NOI18N
        panelisi1.add(jLabel53);
        jLabel53.setBounds(1275, 800, 40, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Edukasi Yang Diberikan :");
        jLabel54.setName("jLabel54"); // NOI18N
        panelisi1.add(jLabel54);
        jLabel54.setBounds(730, 751, 130, 23);

        Tedukasi.setForeground(new java.awt.Color(0, 0, 0));
        Tedukasi.setToolTipText("");
        Tedukasi.setName("Tedukasi"); // NOI18N
        Tedukasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TedukasiKeyPressed(evt);
            }
        });
        panelisi1.add(Tedukasi);
        Tedukasi.setBounds(865, 751, 590, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Pasien / Keluarga / :");
        jLabel55.setName("jLabel55"); // NOI18N
        panelisi1.add(jLabel55);
        jLabel55.setBounds(730, 435, 130, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Penanggung Jawab  ");
        jLabel56.setName("jLabel56"); // NOI18N
        panelisi1.add(jLabel56);
        jLabel56.setBounds(720, 450, 140, 23);

        TKlgPasien.setForeground(new java.awt.Color(0, 0, 0));
        TKlgPasien.setToolTipText("");
        TKlgPasien.setName("TKlgPasien"); // NOI18N
        TKlgPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKlgPasienKeyPressed(evt);
            }
        });
        panelisi1.add(TKlgPasien);
        TKlgPasien.setBounds(865, 435, 590, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Pasien  ");
        jLabel57.setName("jLabel57"); // NOI18N
        panelisi1.add(jLabel57);
        jLabel57.setBounds(720, 465, 140, 23);

        BtnPastePenunjang.setForeground(new java.awt.Color(0, 0, 0));
        BtnPastePenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPastePenunjang.setText("Paste");
        BtnPastePenunjang.setToolTipText("Alt+L");
        BtnPastePenunjang.setName("BtnPastePenunjang"); // NOI18N
        BtnPastePenunjang.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPastePenunjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPastePenunjangActionPerformed(evt);
            }
        });
        panelisi1.add(BtnPastePenunjang);
        BtnPastePenunjang.setBounds(40, 630, 90, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Hasil Pemeriksaan, :");
        jLabel38.setName("jLabel38"); // NOI18N
        panelisi1.add(jLabel38);
        jLabel38.setBounds(730, 912, 130, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Analisa, Rencana,  ");
        jLabel46.setName("jLabel46"); // NOI18N
        panelisi1.add(jLabel46);
        jLabel46.setBounds(730, 927, 130, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Penatalaksanaan  ");
        jLabel58.setName("jLabel58"); // NOI18N
        panelisi1.add(jLabel58);
        jLabel58.setBounds(730, 942, 130, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Pasien  ");
        jLabel59.setName("jLabel59"); // NOI18N
        panelisi1.add(jLabel59);
        jLabel59.setBounds(730, 957, 130, 23);

        Scroll28.setName("Scroll28"); // NOI18N
        Scroll28.setOpaque(true);

        THasil.setColumns(20);
        THasil.setRows(5);
        THasil.setName("THasil"); // NOI18N
        THasil.setPreferredSize(new java.awt.Dimension(190, 10000));
        THasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THasilKeyPressed(evt);
            }
        });
        Scroll28.setViewportView(THasil);

        panelisi1.add(Scroll28);
        Scroll28.setBounds(865, 919, 590, 150);

        BtnPasteHasil.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasteHasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPasteHasil.setText("Paste");
        BtnPasteHasil.setToolTipText("Alt+L");
        BtnPasteHasil.setName("BtnPasteHasil"); // NOI18N
        BtnPasteHasil.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPasteHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasteHasilActionPerformed(evt);
            }
        });
        panelisi1.add(BtnPasteHasil);
        BtnPasteHasil.setBounds(780, 987, 80, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("No. Reg. TB : ");
        jLabel5.setName("jLabel5"); // NOI18N
        panelisi1.add(jLabel5);
        jLabel5.setBounds(500, 92, 80, 23);

        noreg.setForeground(new java.awt.Color(0, 0, 0));
        noreg.setToolTipText("");
        noreg.setName("noreg"); // NOI18N
        noreg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noregKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                noregKeyReleased(evt);
            }
        });
        panelisi1.add(noreg);
        noreg.setBounds(582, 92, 145, 23);

        jml_noreg.setForeground(new java.awt.Color(0, 0, 0));
        jml_noreg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jml_noreg.setText("Jumlah No. Reg. TB : 0 digit");
        jml_noreg.setName("jml_noreg"); // NOI18N
        panelisi1.add(jml_noreg);
        jml_noreg.setBounds(582, 120, 190, 23);

        BtnPasteTerapiPulang.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasteTerapiPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPasteTerapiPulang.setText("Paste");
        BtnPasteTerapiPulang.setToolTipText("Alt+L");
        BtnPasteTerapiPulang.setName("BtnPasteTerapiPulang"); // NOI18N
        BtnPasteTerapiPulang.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPasteTerapiPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasteTerapiPulangActionPerformed(evt);
            }
        });
        panelisi1.add(BtnPasteTerapiPulang);
        BtnPasteTerapiPulang.setBounds(780, 1180, 80, 23);

        BtnPasien.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/barralan.png"))); // NOI18N
        BtnPasien.setText("Pasien Yang Lain");
        BtnPasien.setToolTipText("Alt+P");
        BtnPasien.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnPasien.setName("BtnPasien"); // NOI18N
        BtnPasien.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasienActionPerformed(evt);
            }
        });
        panelisi1.add(BtnPasien);
        BtnPasien.setBounds(730, 8, 150, 23);

        BtnNamaDPJP.setForeground(new java.awt.Color(0, 0, 0));
        BtnNamaDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnNamaDPJP.setToolTipText("Alt+X");
        BtnNamaDPJP.setName("BtnNamaDPJP"); // NOI18N
        BtnNamaDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNamaDPJPActionPerformed(evt);
            }
        });
        panelisi1.add(BtnNamaDPJP);
        BtnNamaDPJP.setBounds(460, 92, 30, 23);

        cmbAsesmen.setForeground(new java.awt.Color(0, 0, 0));
        cmbAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Asesmen Medik Dewasa", "Asesmen Medik Anak", "Asesmen Medik Bedah", "Asesmen Medik Perinatologi" }));
        cmbAsesmen.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cmbAsesmen.setName("cmbAsesmen"); // NOI18N
        cmbAsesmen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAsesmenActionPerformed(evt);
            }
        });
        panelisi1.add(cmbAsesmen);
        cmbAsesmen.setBounds(582, 162, 196, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Lihat Data : ");
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        panelisi1.add(jLabel7);
        jLabel7.setBounds(500, 162, 80, 23);

        cmbKondisiWP.setForeground(new java.awt.Color(0, 0, 0));
        cmbKondisiWP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Dirujuk", "APS", "Meninggal >= 48 Jam", "Meninggal < 48 Jam", "Sembuh/BLPL", "Kabur" }));
        cmbKondisiWP.setName("cmbKondisiWP"); // NOI18N
        cmbKondisiWP.setPreferredSize(new java.awt.Dimension(55, 28));
        panelisi1.add(cmbKondisiWP);
        cmbKondisiWP.setBounds(1090, 884, 140, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Saat Selesai  ");
        jLabel64.setName("jLabel64"); // NOI18N
        panelisi1.add(jLabel64);
        jLabel64.setBounds(730, 595, 130, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Perawatan  ");
        jLabel65.setName("jLabel65"); // NOI18N
        panelisi1.add(jLabel65);
        jLabel65.setBounds(730, 610, 130, 23);

        Scroll2.setViewportView(panelisi1);

        internalFrame2.add(Scroll2, java.awt.BorderLayout.CENTER);

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

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " CPPT ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

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
        Scroll3.setViewportView(tbCPPT);

        FormMenu.add(Scroll3);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass14.setLayout(new java.awt.BorderLayout());

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Hasil Pemeriksaan, Analisa, Rencana, Penatalaksanaan Pasien ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N
        scrollPane5.setPreferredSize(new java.awt.Dimension(212, 350));

        Thasil.setEditable(false);
        Thasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Thasil.setColumns(20);
        Thasil.setRows(5);
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
        Tinstruksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane4.setViewportView(Tinstruksi);

        panelGlass14.add(scrollPane4, java.awt.BorderLayout.CENTER);

        FormMenu.add(panelGlass14);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);

        internalFrame2.add(PanelAccor, java.awt.BorderLayout.EAST);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 54));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
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

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setText("Ganti");
        BtnGanti.setToolTipText("Alt+G");
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        BtnGanti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnGanti);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
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

        BtnNotepad.setForeground(new java.awt.Color(0, 0, 0));
        BtnNotepad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
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

        BtnResep.setForeground(new java.awt.Color(0, 0, 0));
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

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
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

        BtnTTE.setForeground(new java.awt.Color(0, 0, 0));
        BtnTTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/clear24.png"))); // NOI18N
        BtnTTE.setText("Bubuhkan TTE");
        BtnTTE.setToolTipText("Alt+T");
        BtnTTE.setName("BtnTTE"); // NOI18N
        BtnTTE.setPreferredSize(new java.awt.Dimension(140, 30));
        BtnTTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTTEActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnTTE);

        internalFrame2.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        TabRingkasan.addTab("Input Data", internalFrame2);

        internalFrame4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout());

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbRingkasan.setToolTipText("Silahkan klik untuk memilih data yang ataupun dihapus");
        tbRingkasan.setComponentPopupMenu(jPopupMenu1);
        tbRingkasan.setName("tbRingkasan"); // NOI18N
        tbRingkasan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRingkasanMouseClicked(evt);
            }
        });
        tbRingkasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRingkasanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbRingkasan);

        internalFrame4.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 47));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Key Word :");
        jLabel47.setName("jLabel47"); // NOI18N
        jLabel47.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel47);

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
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+T");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 30));
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
        panelGlass9.add(BtnAll);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Record :");
        jLabel48.setName("jLabel48"); // NOI18N
        jLabel48.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel48);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Alt+H");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnHapus1);

        BtnPrint1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint1.setText("Cetak");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnPrint1);

        BtnResep1.setForeground(new java.awt.Color(0, 0, 0));
        BtnResep1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Vial-Pills.png"))); // NOI18N
        BtnResep1.setText("Resep Obat");
        BtnResep1.setToolTipText("Alt+R");
        BtnResep1.setName("BtnResep1"); // NOI18N
        BtnResep1.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnResep1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResep1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnResep1);

        BtnVerif.setForeground(new java.awt.Color(0, 0, 0));
        BtnVerif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/checked.png"))); // NOI18N
        BtnVerif.setText("Verifikasi CPPT");
        BtnVerif.setToolTipText("Alt+V");
        BtnVerif.setName("BtnVerif"); // NOI18N
        BtnVerif.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnVerif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVerifActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnVerif);

        BtnTTE1.setForeground(new java.awt.Color(0, 0, 0));
        BtnTTE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/clear24.png"))); // NOI18N
        BtnTTE1.setText("Bubuhkan TTE");
        BtnTTE1.setToolTipText("Alt+T");
        BtnTTE1.setName("BtnTTE1"); // NOI18N
        BtnTTE1.setPreferredSize(new java.awt.Dimension(140, 30));
        BtnTTE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTTE1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnTTE1);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnKeluar1);

        internalFrame4.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRingkasan.addTab("Data Ringkasan Pulang", internalFrame4);

        internalFrame29.setBorder(null);
        internalFrame29.setName("internalFrame29"); // NOI18N
        internalFrame29.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 280));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass11.setLayout(new java.awt.GridLayout(1, 2));

        Scroll12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Nomor Pemeriksaan Lab. :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll12.setName("Scroll12"); // NOI18N
        Scroll12.setOpaque(true);

        tbLIS.setAutoCreateRowSorter(true);
        tbLIS.setToolTipText("Silahkan klik salah satu datanya untuk melihat hasil pemeriksaannya");
        tbLIS.setName("tbLIS"); // NOI18N
        tbLIS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLISMouseClicked(evt);
            }
        });
        tbLIS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbLISKeyPressed(evt);
            }
        });
        Scroll12.setViewportView(tbLIS);

        panelGlass11.add(Scroll12);

        Scroll31.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Pembaca Hasil Pemeriksaan Lab. :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll31.setName("Scroll31"); // NOI18N
        Scroll31.setOpaque(true);

        tbPembacaLIS.setName("tbPembacaLIS"); // NOI18N
        Scroll31.setViewportView(tbPembacaLIS);

        panelGlass11.add(Scroll31);

        PanelInput.add(panelGlass11, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 7));

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Key Word :");
        jLabel66.setName("jLabel66"); // NOI18N
        jLabel66.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel66);

        TCari3.setForeground(new java.awt.Color(0, 0, 0));
        TCari3.setName("TCari3"); // NOI18N
        TCari3.setPreferredSize(new java.awt.Dimension(190, 23));
        TCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari3KeyPressed(evt);
            }
        });
        panelGlass10.add(TCari3);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Limit Data :");
        jLabel67.setName("jLabel67"); // NOI18N
        jLabel67.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel67);

        cmbHlm.setForeground(new java.awt.Color(0, 0, 0));
        cmbHlm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" }));
        cmbHlm.setName("cmbHlm"); // NOI18N
        cmbHlm.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass10.add(cmbHlm);

        BtnCari4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari4.setText("Tampilkan Data");
        BtnCari4.setToolTipText("Alt+6");
        BtnCari4.setName("BtnCari4"); // NOI18N
        BtnCari4.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari4ActionPerformed(evt);
            }
        });
        BtnCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari4KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnCari4);

        PanelInput.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        internalFrame29.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass15.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Hasil Pemeriksaan Laboratorium :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbHasil.setToolTipText("Silahkan conteng item hasil pemeriksaan yang akan di copy");
        tbHasil.setName("tbHasil"); // NOI18N
        tbHasil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHasilMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbHasil);

        panelGlass15.add(Scroll1);

        Scroll13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Pemeriksaan Laboratorium Dipilih/Dicopy :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll13.setName("Scroll13"); // NOI18N
        Scroll13.setOpaque(true);

        tbHasilCopy.setToolTipText("Silahkan klik kanan utk. menghapus pemeriksaan lab. yg. sdh. dipilih");
        tbHasilCopy.setComponentPopupMenu(jPopupMenu2);
        tbHasilCopy.setName("tbHasilCopy"); // NOI18N
        Scroll13.setViewportView(tbHasilCopy);

        panelGlass15.add(Scroll13);

        internalFrame29.add(panelGlass15, java.awt.BorderLayout.CENTER);

        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(55, 47));
        panelGlass16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnConteng.setForeground(new java.awt.Color(0, 0, 0));
        BtnConteng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnConteng.setText("Conteng Semua");
        BtnConteng.setToolTipText("Alt+G");
        BtnConteng.setName("BtnConteng"); // NOI18N
        BtnConteng.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnConteng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnContengActionPerformed(evt);
            }
        });
        panelGlass16.add(BtnConteng);

        BtnHapus2.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapus2.setText("Hapus Conteng");
        BtnHapus2.setToolTipText("Alt+M");
        BtnHapus2.setName("BtnHapus2"); // NOI18N
        BtnHapus2.setPreferredSize(new java.awt.Dimension(140, 30));
        BtnHapus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus2ActionPerformed(evt);
            }
        });
        panelGlass16.add(BtnHapus2);

        BtnCopy.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopy.setText("Copy Hasil");
        BtnCopy.setToolTipText("Alt+U");
        BtnCopy.setName("BtnCopy"); // NOI18N
        BtnCopy.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopyActionPerformed(evt);
            }
        });
        panelGlass16.add(BtnCopy);

        BtnUlangiCopyLab.setForeground(new java.awt.Color(0, 0, 0));
        BtnUlangiCopyLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnUlangiCopyLab.setText("Ulangi Copy Hasil");
        BtnUlangiCopyLab.setToolTipText("Alt+U");
        BtnUlangiCopyLab.setName("BtnUlangiCopyLab"); // NOI18N
        BtnUlangiCopyLab.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnUlangiCopyLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUlangiCopyLabActionPerformed(evt);
            }
        });
        panelGlass16.add(BtnUlangiCopyLab);

        BtnKeluar5.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar5.setText("Keluar");
        BtnKeluar5.setToolTipText("Alt+K");
        BtnKeluar5.setName("BtnKeluar5"); // NOI18N
        BtnKeluar5.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar5ActionPerformed(evt);
            }
        });
        panelGlass16.add(BtnKeluar5);

        internalFrame29.add(panelGlass16, java.awt.BorderLayout.PAGE_END);

        TabRingkasan.addTab("Pemeriksaan LAB.", internalFrame29);

        internalFrame30.setBorder(null);
        internalFrame30.setName("internalFrame30"); // NOI18N
        internalFrame30.setLayout(new java.awt.BorderLayout());

        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(190, 250));
        FormInput2.setLayout(new java.awt.BorderLayout());

        panelGlass32.setName("panelGlass32"); // NOI18N
        panelGlass32.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass32.setLayout(new java.awt.GridLayout(1, 2));

        Scroll14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Pemeriksaan Radiologi :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll14.setName("Scroll14"); // NOI18N
        Scroll14.setOpaque(true);

        tbRadiologi.setAutoCreateRowSorter(true);
        tbRadiologi.setToolTipText("Silahkan klik salah satu datanya untuk membaca hasil ekspertisenya");
        tbRadiologi.setName("tbRadiologi"); // NOI18N
        tbRadiologi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRadiologiMouseClicked(evt);
            }
        });
        tbRadiologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRadiologiKeyPressed(evt);
            }
        });
        Scroll14.setViewportView(tbRadiologi);

        panelGlass32.add(Scroll14);

        Scroll32.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Pembaca Hasil Pemeriksaan Radiologi :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll32.setName("Scroll32"); // NOI18N
        Scroll32.setOpaque(true);

        tbPembacaRad.setName("tbPembacaRad"); // NOI18N
        Scroll32.setViewportView(tbPembacaRad);

        panelGlass32.add(Scroll32);

        FormInput2.add(panelGlass32, java.awt.BorderLayout.CENTER);

        panelGlass17.setName("panelGlass17"); // NOI18N
        panelGlass17.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 7));

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Key Word :");
        jLabel68.setName("jLabel68"); // NOI18N
        jLabel68.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(jLabel68);

        TCari4.setForeground(new java.awt.Color(0, 0, 0));
        TCari4.setName("TCari4"); // NOI18N
        TCari4.setPreferredSize(new java.awt.Dimension(190, 23));
        TCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari4KeyPressed(evt);
            }
        });
        panelGlass17.add(TCari4);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Limit Data :");
        jLabel69.setName("jLabel69"); // NOI18N
        jLabel69.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass17.add(jLabel69);

        cmbHlm1.setForeground(new java.awt.Color(0, 0, 0));
        cmbHlm1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" }));
        cmbHlm1.setName("cmbHlm1"); // NOI18N
        cmbHlm1.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass17.add(cmbHlm1);

        BtnCari5.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari5.setText("Tampilkan Data");
        BtnCari5.setToolTipText("Alt+6");
        BtnCari5.setName("BtnCari5"); // NOI18N
        BtnCari5.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari5ActionPerformed(evt);
            }
        });
        BtnCari5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari5KeyPressed(evt);
            }
        });
        panelGlass17.add(BtnCari5);

        FormInput2.add(panelGlass17, java.awt.BorderLayout.PAGE_END);

        internalFrame30.add(FormInput2, java.awt.BorderLayout.PAGE_START);

        Scroll11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Hasil Expertise Radiologi :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);

        HasilPeriksa.setEditable(false);
        HasilPeriksa.setColumns(20);
        HasilPeriksa.setRows(5);
        HasilPeriksa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        HasilPeriksa.setName("HasilPeriksa"); // NOI18N
        Scroll11.setViewportView(HasilPeriksa);

        internalFrame30.add(Scroll11, java.awt.BorderLayout.CENTER);

        panelGlass18.setName("panelGlass18"); // NOI18N
        panelGlass18.setPreferredSize(new java.awt.Dimension(55, 47));
        panelGlass18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnCopy1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopy1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopy1.setText("Copy Hasil");
        BtnCopy1.setToolTipText("Alt+U");
        BtnCopy1.setName("BtnCopy1"); // NOI18N
        BtnCopy1.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCopy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopy1ActionPerformed(evt);
            }
        });
        panelGlass18.add(BtnCopy1);

        BtnUlangiCopyRad.setForeground(new java.awt.Color(0, 0, 0));
        BtnUlangiCopyRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnUlangiCopyRad.setText("Ulangi Copy Hasil");
        BtnUlangiCopyRad.setToolTipText("Alt+U");
        BtnUlangiCopyRad.setName("BtnUlangiCopyRad"); // NOI18N
        BtnUlangiCopyRad.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnUlangiCopyRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUlangiCopyRadActionPerformed(evt);
            }
        });
        panelGlass18.add(BtnUlangiCopyRad);

        BtnKeluar6.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar6.setText("Keluar");
        BtnKeluar6.setToolTipText("Alt+K");
        BtnKeluar6.setName("BtnKeluar6"); // NOI18N
        BtnKeluar6.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar6ActionPerformed(evt);
            }
        });
        panelGlass18.add(BtnKeluar6);

        internalFrame30.add(panelGlass18, java.awt.BorderLayout.PAGE_END);

        TabRingkasan.addTab("Pemeriksaan Radiologi (Expertise)", internalFrame30);

        internalFrame31.setBorder(null);
        internalFrame31.setName("internalFrame31"); // NOI18N
        internalFrame31.setLayout(new java.awt.BorderLayout());

        panelGlass30.setName("panelGlass30"); // NOI18N
        panelGlass30.setPreferredSize(new java.awt.Dimension(44, 45));
        panelGlass30.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Pasien :");
        jLabel70.setName("jLabel70"); // NOI18N
        jLabel70.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass30.add(jLabel70);

        TNoRw1.setEditable(false);
        TNoRw1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw1.setName("TNoRw1"); // NOI18N
        TNoRw1.setPreferredSize(new java.awt.Dimension(122, 24));
        panelGlass30.add(TNoRw1);

        TNoRm1.setEditable(false);
        TNoRm1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm1.setName("TNoRm1"); // NOI18N
        TNoRm1.setPreferredSize(new java.awt.Dimension(70, 24));
        panelGlass30.add(TNoRm1);

        TPasien1.setEditable(false);
        TPasien1.setForeground(new java.awt.Color(0, 0, 0));
        TPasien1.setName("TPasien1"); // NOI18N
        TPasien1.setPreferredSize(new java.awt.Dimension(407, 24));
        panelGlass30.add(TPasien1);

        internalFrame31.add(panelGlass30, java.awt.BorderLayout.PAGE_START);

        Scroll15.setName("Scroll15"); // NOI18N
        Scroll15.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setComponentPopupMenu(jPopupMenu3);
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll15.setViewportView(LoadHTML1);

        internalFrame31.add(Scroll15, java.awt.BorderLayout.CENTER);

        panelGlass19.setToolTipText("Klik kanan disini untuk menambahkan jenis dokumen penunjang baru");
        panelGlass19.setName("panelGlass19"); // NOI18N
        panelGlass19.setPreferredSize(new java.awt.Dimension(44, 47));
        panelGlass19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 12));

        ChkDokumen.setBackground(new java.awt.Color(255, 255, 250));
        ChkDokumen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDokumen.setForeground(new java.awt.Color(0, 0, 0));
        ChkDokumen.setText("Semua Dokumen Penunjang Medis");
        ChkDokumen.setBorderPainted(true);
        ChkDokumen.setBorderPaintedFlat(true);
        ChkDokumen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDokumen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDokumen.setName("ChkDokumen"); // NOI18N
        ChkDokumen.setOpaque(false);
        ChkDokumen.setPreferredSize(new java.awt.Dimension(210, 23));
        ChkDokumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkDokumenActionPerformed(evt);
            }
        });
        panelGlass19.add(ChkDokumen);

        BtnUpload.setForeground(new java.awt.Color(0, 0, 0));
        BtnUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnUpload.setText("Upload Dokumen");
        BtnUpload.setToolTipText("Alt+U");
        BtnUpload.setName("BtnUpload"); // NOI18N
        BtnUpload.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUploadActionPerformed(evt);
            }
        });
        panelGlass19.add(BtnUpload);

        BtnHapus3.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus3.setText("Hapus Dokumen");
        BtnHapus3.setToolTipText("Alt+H");
        BtnHapus3.setName("BtnHapus3"); // NOI18N
        BtnHapus3.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnHapus3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus3ActionPerformed(evt);
            }
        });
        panelGlass19.add(BtnHapus3);

        BtnCari6.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari6.setText("Tampilkan Data");
        BtnCari6.setToolTipText("Alt+1");
        BtnCari6.setName("BtnCari6"); // NOI18N
        BtnCari6.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari6ActionPerformed(evt);
            }
        });
        BtnCari6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari6KeyPressed(evt);
            }
        });
        panelGlass19.add(BtnCari6);

        BtnKeluar7.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar7.setText("Keluar");
        BtnKeluar7.setToolTipText("Alt+K");
        BtnKeluar7.setName("BtnKeluar7"); // NOI18N
        BtnKeluar7.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar7ActionPerformed(evt);
            }
        });
        panelGlass19.add(BtnKeluar7);

        internalFrame31.add(panelGlass19, java.awt.BorderLayout.PAGE_END);

        panelGlass20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Scan QRCode Untuk Upload File ]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panelGlass20.setName("panelGlass20"); // NOI18N
        panelGlass20.setPreferredSize(new java.awt.Dimension(210, 422));
        panelGlass20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        panelGlass21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51), 2), "[ Koneksi Paket Data ]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 0, 51))); // NOI18N
        panelGlass21.setToolTipText("");
        panelGlass21.setName("panelGlass21"); // NOI18N
        panelGlass21.setPreferredSize(new java.awt.Dimension(160, 160));
        panelGlass21.setLayout(new java.awt.BorderLayout());

        PanelWallpublic.setBackground(new java.awt.Color(29, 29, 29));
        PanelWallpublic.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/qrcode_upload_dok_jangmed.png"))); // NOI18N
        PanelWallpublic.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWallpublic.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWallpublic.setRound(false);
        PanelWallpublic.setToolTipText("");
        PanelWallpublic.setWarna(new java.awt.Color(110, 110, 110));
        PanelWallpublic.setLayout(null);
        panelGlass21.add(PanelWallpublic, java.awt.BorderLayout.CENTER);

        panelGlass20.add(panelGlass21);

        panelGlass22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2), "[ Koneksi Wifi RS ]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 255))); // NOI18N
        panelGlass22.setToolTipText("");
        panelGlass22.setName("panelGlass22"); // NOI18N
        panelGlass22.setPreferredSize(new java.awt.Dimension(160, 160));
        panelGlass22.setLayout(new java.awt.BorderLayout());

        PanelWallwifi.setBackground(new java.awt.Color(29, 29, 29));
        PanelWallwifi.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/qrcode_upload_dok_jangmed_wifi.png"))); // NOI18N
        PanelWallwifi.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWallwifi.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWallwifi.setRound(false);
        PanelWallwifi.setToolTipText("");
        PanelWallwifi.setWarna(new java.awt.Color(110, 110, 110));
        PanelWallwifi.setLayout(null);
        panelGlass22.add(PanelWallwifi, java.awt.BorderLayout.CENTER);

        panelGlass20.add(panelGlass22);

        internalFrame31.add(panelGlass20, java.awt.BorderLayout.EAST);

        TabRingkasan.addTab("Dokumen Penunjang Medis", internalFrame31);

        internalFrame36.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame36.setName("internalFrame36"); // NOI18N
        internalFrame36.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass29.setName("panelGlass29"); // NOI18N
        panelGlass29.setPreferredSize(new java.awt.Dimension(44, 45));
        panelGlass29.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Pasien :");
        jLabel73.setName("jLabel73"); // NOI18N
        jLabel73.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass29.add(jLabel73);

        TNoRw2.setEditable(false);
        TNoRw2.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw2.setName("TNoRw2"); // NOI18N
        TNoRw2.setPreferredSize(new java.awt.Dimension(122, 24));
        panelGlass29.add(TNoRw2);

        TNoRm2.setEditable(false);
        TNoRm2.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm2.setName("TNoRm2"); // NOI18N
        TNoRm2.setPreferredSize(new java.awt.Dimension(70, 24));
        panelGlass29.add(TNoRm2);

        TPasien2.setEditable(false);
        TPasien2.setForeground(new java.awt.Color(0, 0, 0));
        TPasien2.setName("TPasien2"); // NOI18N
        TPasien2.setPreferredSize(new java.awt.Dimension(407, 24));
        panelGlass29.add(TPasien2);

        internalFrame36.add(panelGlass29, java.awt.BorderLayout.PAGE_START);

        Scroll30.setName("Scroll30"); // NOI18N
        Scroll30.setOpaque(true);

        LoadHTML2.setBorder(null);
        LoadHTML2.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML2.setComponentPopupMenu(jPopupMenu4);
        LoadHTML2.setName("LoadHTML2"); // NOI18N
        Scroll30.setViewportView(LoadHTML2);

        internalFrame36.add(Scroll30, java.awt.BorderLayout.CENTER);

        panelGlass27.setName("panelGlass27"); // NOI18N
        panelGlass27.setPreferredSize(new java.awt.Dimension(44, 100));
        panelGlass27.setLayout(new java.awt.BorderLayout());

        panelGlass26.setName("panelGlass26"); // NOI18N
        panelGlass26.setPreferredSize(new java.awt.Dimension(44, 47));
        panelGlass26.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        ChkTanggal.setBorder(null);
        ChkTanggal.setForeground(new java.awt.Color(0, 0, 0));
        ChkTanggal.setText("Tgl.Rawat :");
        ChkTanggal.setBorderPainted(true);
        ChkTanggal.setBorderPaintedFlat(true);
        ChkTanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkTanggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTanggal.setName("ChkTanggal"); // NOI18N
        ChkTanggal.setOpaque(false);
        ChkTanggal.setPreferredSize(new java.awt.Dimension(85, 23));
        ChkTanggal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkTanggalItemStateChanged(evt);
            }
        });
        ChkTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkTanggalActionPerformed(evt);
            }
        });
        panelGlass26.add(ChkTanggal);

        DTPCari5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-09-2025" }));
        DTPCari5.setDisplayFormat("dd-MM-yyyy");
        DTPCari5.setName("DTPCari5"); // NOI18N
        DTPCari5.setOpaque(false);
        DTPCari5.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass26.add(DTPCari5);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("s.d.");
        jLabel74.setName("jLabel74"); // NOI18N
        jLabel74.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass26.add(jLabel74);

        DTPCari6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-09-2025" }));
        DTPCari6.setDisplayFormat("dd-MM-yyyy");
        DTPCari6.setName("DTPCari6"); // NOI18N
        DTPCari6.setOpaque(false);
        DTPCari6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass26.add(DTPCari6);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Poliklinik : ");
        jLabel75.setName("jLabel75"); // NOI18N
        jLabel75.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass26.add(jLabel75);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.setPreferredSize(new java.awt.Dimension(55, 24));
        panelGlass26.add(kdpoli);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TPoli.setName("TPoli"); // NOI18N
        TPoli.setPreferredSize(new java.awt.Dimension(280, 24));
        panelGlass26.add(TPoli);

        BtnUnit.setForeground(new java.awt.Color(0, 0, 0));
        BtnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnUnit.setToolTipText("ALt+4");
        BtnUnit.setName("BtnUnit"); // NOI18N
        BtnUnit.setPreferredSize(new java.awt.Dimension(30, 25));
        BtnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnitActionPerformed(evt);
            }
        });
        panelGlass26.add(BtnUnit);

        BtnHapusPoli.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusPoli.setToolTipText("Kosongkan Poliklinik Yang Telah Dipilih");
        BtnHapusPoli.setName("BtnHapusPoli"); // NOI18N
        BtnHapusPoli.setPreferredSize(new java.awt.Dimension(30, 25));
        BtnHapusPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusPoliActionPerformed(evt);
            }
        });
        panelGlass26.add(BtnHapusPoli);

        panelGlass27.add(panelGlass26, java.awt.BorderLayout.CENTER);

        panelGlass28.setName("panelGlass28"); // NOI18N
        panelGlass28.setPreferredSize(new java.awt.Dimension(44, 47));
        panelGlass28.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        ChkLihat.setBorder(null);
        ChkLihat.setForeground(new java.awt.Color(0, 0, 0));
        ChkLihat.setText("Lihat Riwayat Perawatan : ");
        ChkLihat.setBorderPainted(true);
        ChkLihat.setBorderPaintedFlat(true);
        ChkLihat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkLihat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLihat.setName("ChkLihat"); // NOI18N
        ChkLihat.setOpaque(false);
        ChkLihat.setPreferredSize(new java.awt.Dimension(155, 23));
        ChkLihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkLihatActionPerformed(evt);
            }
        });
        panelGlass28.add(ChkLihat);

        cmbBulan.setForeground(new java.awt.Color(0, 0, 0));
        cmbBulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cmbBulan.setName("cmbBulan"); // NOI18N
        cmbBulan.setPreferredSize(new java.awt.Dimension(45, 23));
        cmbBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBulanActionPerformed(evt);
            }
        });
        panelGlass28.add(cmbBulan);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("Bulan Yang Lalu");
        jLabel76.setName("jLabel76"); // NOI18N
        jLabel76.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass28.add(jLabel76);

        BtnCari8.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari8.setText("Tampilkan Data");
        BtnCari8.setToolTipText("Alt+6");
        BtnCari8.setName("BtnCari8"); // NOI18N
        BtnCari8.setPreferredSize(new java.awt.Dimension(130, 25));
        BtnCari8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari8ActionPerformed(evt);
            }
        });
        panelGlass28.add(BtnCari8);

        label_rehab.setForeground(new java.awt.Color(0, 0, 0));
        label_rehab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_rehab.setText("label_rehab");
        label_rehab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label_rehab.setName("label_rehab"); // NOI18N
        label_rehab.setPreferredSize(new java.awt.Dimension(460, 23));
        panelGlass28.add(label_rehab);

        BtnKeluar8.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar8.setText("Keluar");
        BtnKeluar8.setToolTipText("Alt+K");
        BtnKeluar8.setName("BtnKeluar8"); // NOI18N
        BtnKeluar8.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar8ActionPerformed(evt);
            }
        });
        panelGlass28.add(BtnKeluar8);

        panelGlass27.add(panelGlass28, java.awt.BorderLayout.PAGE_END);

        internalFrame36.add(panelGlass27, java.awt.BorderLayout.PAGE_END);

        TabRingkasan.addTab("Ringkasan Riwayat Rawat Jalan", internalFrame36);

        internalFrame1.add(TabRingkasan, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "nomor rawat");
        } else {
            kontrolPoli = "";
            cekTgl = "";
            nipPenyimpan = "";
            if (chkTglKontrol.isSelected() == false) {
                kontrolPoli = "0000-00-00";
                cekTgl = "tidak";
            } else {
                kontrolPoli = Valid.SetTgl(TglKontrol.getSelectedItem() + "");
                cekTgl = "ya";
            }
            
            if (TKlgPasien.getText().equals("")) {
                TKlgPasien.setText("-");
            } else {
                TKlgPasien.setText(TKlgPasien.getText());
            }
            
            if (TNmDokter.getText().equals("")) {
                TNmDokter.setText("-");
            } else {
                TNmDokter.setText(TNmDokter.getText());
            }
            
            if (akses.getadmin() == true) {
                nipPenyimpan = "-";
            } else {
                nipPenyimpan = akses.getkode();
            }

            try {
                if (Sequel.menyimpantf("ringkasan_pulang_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Ringkasan Pulang Pasien Rawat Inap", 28, new String[]{
                    TNoRW.getText(), TAlasanDirawat.getText(), TRingkasanRiwayat.getText(), Valid.mysql_real_escape_stringERM(TPemeriksaanFisik.getText()),
                    Valid.mysql_real_escape_stringERM(TPemeriksaanPenunjang.getText()), TTerapiPengobatan.getText(), TDiagUtama.getText(),
                    TDiagSekunder.getText(), TKeadaanumum.getText(), TKesadaran.getText(), TTensi.getText(), TSuhu.getText(), TNadi.getText(), TFrekuensiNafas.getText(),
                    TCatatan.getText(), TTerapiPulang.getText(), cmbLanjutan.getSelectedItem().toString(), kontrolPoli, TNmDokter.getText(), Tgcs.getText(),
                    TTindakan.getText(), TDokterLuar.getText(), cekTgl, Tedukasi.getText().replaceAll("'", ""), TKlgPasien.getText(), nipPenyimpan, THasil.getText(),
                    cmbKondisiWP.getSelectedItem().toString()
                }) == true) {
                    if (nmgedung.equals("AL-HAKIM/PARU")) {
                        if (noreg.getText().length() == 16) {
                            Sequel.simpanReplaceInto("nomor_reg_tb", "'" + TNoRM.getText() + "','" + noreg.getText() + "'", "No. Registrasi Pasien TB");
                        }
                    }

                    Sequel.SimpanHistoriRekamMedis(TNoRW.getText(), "Ringkasan Pulang Pasien Rawat Inap", "Simpan");
                    TCari.setText(TNoRW.getText());
                    emptTeks();
                    tampil();
                    TabRingkasan.setSelectedIndex(1);
                }
            } catch (Exception e) {
                System.out.println("Simpan Ringkasan Pulang Pasien : " + e);
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, TNmDokter, BtnBatal);
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
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "nomor rawat");
        } else {
            kontrolPoli = "";
            cekTgl = "";
            if (chkTglKontrol.isSelected() == false) {
                kontrolPoli = "0000-00-00";
                cekTgl = "tidak";
            } else {
                kontrolPoli = Valid.SetTgl(TglKontrol.getSelectedItem() + "");
                cekTgl = "ya";
            }
            
            if (TKlgPasien.getText().equals("")) {
                TKlgPasien.setText("-");
            } else {
                TKlgPasien.setText(TKlgPasien.getText());
            }
            
            if (TNmDokter.getText().equals("")) {
                TNmDokter.setText("-");
            } else {
                TNmDokter.setText(TNmDokter.getText());
            }

            try {
                Sequel.mengedit("ringkasan_pulang_ranap", "no_rawat='" + TNoRW.getText() + "'", "alasan_masuk_dirawat='" + TAlasanDirawat.getText() + "', "
                        + "ringkasan_riwayat_penyakit='" + TRingkasanRiwayat.getText() + "', pemeriksaan_fisik='" + Valid.mysql_real_escape_stringERM(TPemeriksaanFisik.getText()) + "', "
                        + "pemeriksaan_penunjang='" + Valid.mysql_real_escape_stringERM(TPemeriksaanPenunjang.getText()) + "',terapi_pengobatan='" + TTerapiPengobatan.getText() + "',"
                        + "diagnosa_utama='" + TDiagUtama.getText() + "',diagnosa_sekunder='" + TDiagSekunder.getText() + "',keadaan_umum='" + TKeadaanumum.getText() + "',"
                        + "kesadaran='" + TKesadaran.getText() + "',tekanan_darah='" + TTensi.getText() + "',suhu='" + TSuhu.getText() + "',nadi='" + TNadi.getText() + "',"
                        + "frekuensi_nafas='" + TFrekuensiNafas.getText() + "',catatan_penting='" + TCatatan.getText() + "',terapi_pulang='" + TTerapiPulang.getText() + "',"
                        + "pengobatan_dilanjutkan='" + cmbLanjutan.getSelectedItem().toString() + "',tgl_kontrol_poliklinik='" + kontrolPoli + "',"
                        + "nm_dokter_pengirim='" + TNmDokter.getText() + "',GCS='" + Tgcs.getText() + "',tindakan_prosedur='" + TTindakan.getText() + "',"
                        + "dokter_luar_lanjutan='" + TDokterLuar.getText() + "',cek_tgl_kontrol='" + cekTgl + "',edukasi='" + Tedukasi.getText().replaceAll("'", "") + "',"
                        + "penanggung_jwb_pasien='" + TKlgPasien.getText() + "',hasil_pemeriksaan='" + THasil.getText() + "',stts_pulang='" + cmbKondisiWP.getSelectedItem().toString() + "'");

                if (nmgedung.equals("AL-HAKIM/PARU")) {
                    if (noreg.getText().length() == 16) {
                        Sequel.simpanReplaceInto("nomor_reg_tb", "'" + TNoRM.getText() + "','" + noreg.getText() + "'", "No. Registrasi Pasien TB");
                    }
                }

                Sequel.SimpanHistoriRekamMedis(TNoRW.getText(), "Ringkasan Pulang Pasien Rawat Inap", "Ganti");
                TCari.setText(TNoRW.getText());
                emptTeks();
                tampil();
                TabRingkasan.setSelectedIndex(1);
            } catch (Exception e) {
                System.out.println("Ganti Ringkasan Pulang Pasien : " + e);
            }
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah Ringkasan Pulang/Resume Medis sudah tersimpan, selesai diisi/diperbaiki..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            dispose();
            WindowTTE.dispose();
            WindowPasien.dispose();
            WindowDokterPenyimpan.dispose();
            WindowRehabMedik.dispose();
        }
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnBatal, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnCari, TNmDokter);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbRingkasanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRingkasanMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbRingkasanMouseClicked

    private void tbRingkasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRingkasanKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbRingkasanKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (Sequel.cariInteger("select count(-1) from ringkasan_pulang_ranap where no_rawat='" + TNoRW.getText() + "'") > 0) {
            TabRingkasan.setSelectedIndex(1);
            tampil();
        } else if (Sequel.cariInteger("select count(-1) from ringkasan_pulang_ranap where no_rawat='" + TNoRW.getText() + "'") == 0) {
            TabRingkasan.setSelectedIndex(0);
        }
        
        noreg.setText(Sequel.cariIsi("select ifnull(id_tb_03,'') from nomor_reg_tb where no_rkm_medis='" + TNoRM.getText() + "'"));
        if (nmgedung.equals("AL-HAKIM/PARU")) {
            noreg.setEnabled(true);
        } else {
            noreg.setEnabled(false);
        }
        
        i = 0;
        i = noreg.getText().length();
        jml_noreg.setText("Jumlah No. Reg. TB : " + i + " digit");
    }//GEN-LAST:event_formWindowOpened

    private void TabRingkasanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRingkasanMouseClicked
        if (TabRingkasan.getSelectedIndex() == 1) {
            tampil();
            ChkAccor.setSelected(false);
            isMenu();
        } else if (TabRingkasan.getSelectedIndex() == 2) {
            if (!TNoRW.getText().equals("")) {
                TCari3.setText("");
                tampilLIS();
            } else {
                Valid.tabelKosong(tabModeLis);
                Valid.tabelKosong(tabModeHasilLab);
                Valid.tabelKosong(tabModeHasilCopy);                
                if (tbRingkasan.getSelectedRow() > -1) {
                    TCari3.setText("");
                    tampilLIS();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih datanya terlebih dahulu pada tabel..!!");
                    TabRingkasan.setSelectedIndex(1);
                    tbRingkasan.requestFocus();
                }
            }
        } else if (TabRingkasan.getSelectedIndex() == 3) {
            if (!TNoRW.getText().equals("")) {
                TCari4.setText("");                
                tampilItem();
            } else {
                Valid.tabelKosong(tabModeRad);
                HasilPeriksa.setText("");
                if (tbRingkasan.getSelectedRow() > -1) {
                    TCari4.setText("");
                    tampilItem();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih datanya terlebih dahulu pada tabel..!!");
                    TabRingkasan.setSelectedIndex(1);
                    tbRingkasan.requestFocus();
                }
            }   
        } else if (TabRingkasan.getSelectedIndex() == 4) {
            if (!TNoRW.getText().equals("")) {
                cekPasien();
                TNoRw1.setText(TNoRW.getText());
                TNoRm1.setText(TNoRM.getText());
                TPasien1.setText(TNmPasien.getText());
                ChkDokumen.setSelected(false);
                tampilDokJangMed();
                if (akses.getadmin() == true) {
                    MnJenisDokumen.setEnabled(true);
                } else {
                    MnJenisDokumen.setEnabled(false);
                }
            } else {
                LoadHTML1.setText("");
                if (tbRingkasan.getSelectedRow() > -1) {
                    cekPasien();
                    TNoRw1.setText(TNoRW.getText());
                    TNoRm1.setText(TNoRM.getText());
                    TPasien1.setText(TNmPasien.getText());
                    ChkDokumen.setSelected(false);
                    tampilDokJangMed();
                    if (akses.getadmin() == true) {
                        MnJenisDokumen.setEnabled(true);
                    } else {
                        MnJenisDokumen.setEnabled(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih datanya terlebih dahulu pada tabel..!!");
                    TabRingkasan.setSelectedIndex(1);
                    tbRingkasan.requestFocus();
                }
            }
        } else if (TabRingkasan.getSelectedIndex() == 5) {
            if (!TNoRW.getText().equals("")) {
                cekPasien();
                TNoRw2.setText(TNoRW.getText());
                TNoRm2.setText(TNoRM.getText());
                TPasien2.setText(TNmPasien.getText());
                klikRiwayatRalan();
            } else {
                LoadHTML2.setText("");
                if (tbRingkasan.getSelectedRow() > -1) {
                    cekPasien();
                    TNoRw2.setText(TNoRW.getText());
                    TNoRm2.setText(TNoRM.getText());
                    TPasien2.setText(TNmPasien.getText());
                    klikRiwayatRalan();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih datanya terlebih dahulu pada tabel..!!");
                    TabRingkasan.setSelectedIndex(1);
                    tbRingkasan.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_TabRingkasanMouseClicked

    private void TNmDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNmDokterKeyPressed
        Valid.pindah(evt, TNmDokter, TAlasanDirawat);
    }//GEN-LAST:event_TNmDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        ChkAccor.setSelected(false);
        isMenu();
        
        pilihan = 1;
        akses.setform("DlgRingkasanPulangRanap");
        dokter.isCek();
        dokter.TCari.requestFocus();
        dokter.setSize(1045, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
        dokter.emptTeks();
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void TAlasanDirawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlasanDirawatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TRingkasanRiwayat.requestFocus();
        }
    }//GEN-LAST:event_TAlasanDirawatKeyPressed

    private void TRingkasanRiwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRingkasanRiwayatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TPemeriksaanFisik.requestFocus();
        }
    }//GEN-LAST:event_TRingkasanRiwayatKeyPressed

    private void TPemeriksaanPenunjangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemeriksaanPenunjangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TTerapiPengobatan.requestFocus();
        }
    }//GEN-LAST:event_TPemeriksaanPenunjangKeyPressed

    private void TTerapiPengobatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTerapiPengobatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TDiagUtama.requestFocus();
        }
    }//GEN-LAST:event_TTerapiPengobatanKeyPressed

    private void TDiagUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagUtamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TDiagSekunder.requestFocus();
        }
    }//GEN-LAST:event_TDiagUtamaKeyPressed

    private void TDiagSekunderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagSekunderKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TKlgPasien.requestFocus();
        }
    }//GEN-LAST:event_TDiagSekunderKeyPressed

    private void TTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TKeadaanumum.requestFocus();
        }
    }//GEN-LAST:event_TTindakanKeyPressed

    private void TKeadaanumumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKeadaanumumKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TKesadaran.requestFocus();
        }
    }//GEN-LAST:event_TKeadaanumumKeyPressed

    private void TKesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKesadaranKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tedukasi.requestFocus();
        }
    }//GEN-LAST:event_TKesadaranKeyPressed

    private void TTensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTensiKeyPressed
        Valid.pindah(evt, TTensi, TSuhu);
    }//GEN-LAST:event_TTensiKeyPressed

    private void TSuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSuhuKeyPressed
        Valid.pindah(evt, TTensi, TNadi);
    }//GEN-LAST:event_TSuhuKeyPressed

    private void TNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNadiKeyPressed
        Valid.pindah(evt, TSuhu, TFrekuensiNafas);
    }//GEN-LAST:event_TNadiKeyPressed

    private void TFrekuensiNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFrekuensiNafasKeyPressed
        Valid.pindah(evt, TNadi, Tgcs);
    }//GEN-LAST:event_TFrekuensiNafasKeyPressed

    private void cmbLanjutanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbLanjutanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLanjutanKeyPressed

    private void TglKontrolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglKontrolKeyPressed
//        Valid.pindah(evt, TKdPrw, cmbJam);
    }//GEN-LAST:event_TglKontrolKeyPressed

    private void TgcsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsKeyPressed
        Valid.pindah(evt, TFrekuensiNafas, cmbLanjutan);
    }//GEN-LAST:event_TgcsKeyPressed

    private void TCatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCatatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TTerapiPulang.requestFocus();
        }
    }//GEN-LAST:event_TCatatanKeyPressed

    private void TPemeriksaanFisikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemeriksaanFisikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TPemeriksaanPenunjang.requestFocus();
        }
    }//GEN-LAST:event_TPemeriksaanFisikKeyPressed

    private void TTerapiPulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTerapiPulangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_TTerapiPulangKeyPressed

    private void cmbLanjutanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLanjutanActionPerformed
        if (cmbLanjutan.getSelectedItem().toString().equals("Dokter Luar")) {
            TDokterLuar.requestFocus();
            TDokterLuar.setEditable(true);
        } else {
            TDokterLuar.setEditable(false);
            TDokterLuar.setText("");
        }
    }//GEN-LAST:event_cmbLanjutanActionPerformed

    private void MnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiagnosaActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu ringkasan pulang pasiennya...!!!");
            TCari.requestFocus();
        } else {
            DlgDiagnosaPenyakit resep = new DlgDiagnosaPenyakit(null, false);
            resep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            resep.setLocationRelativeTo(internalFrame1);
            resep.isCek();
            resep.setNoRm(TNoRW.getText(), Valid.SetTgl2(Sequel.cariIsi("select date(now())")), "Ranap");
            resep.tampilDiagStatistik();
            resep.tampilDiagInadrg();
            resep.setVisible(true);
        }
    }//GEN-LAST:event_MnDiagnosaActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbRingkasan.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 35).toString().equals(akses.getkode())
                        || tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 35).toString().equals("-")) {
                    simpanHistory();
                    if (Sequel.queryu2tf("delete from ringkasan_pulang_ranap where no_rawat=?", 1, new String[]{
                        tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 0).toString()
                    }) == true) {
                        Sequel.SimpanHistoriRekamMedis(TNoRW.getText(), "Ringkasan Pulang Pasien Rawat Inap", "Hapus");
                        TCari.setText(TNoRW.getText());
                        tampil();
                        emptTeks();
                        TabRingkasan.setSelectedIndex(1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Maaf, resume medis pasien ini hanya bisa dihapus oleh "
                            + tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 37).toString() + ".");
                    tampil();
                    emptTeks();
                }
            } else {
                TCari.setText(TNoRW.getText());
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih datanya terlebih dahulu pada tabel..!!");
            TabRingkasan.setSelectedIndex(1);
            tbRingkasan.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void MnCetakRingkasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakRingkasanActionPerformed
        if (tbRingkasan.getSelectedRow() > -1) {
            diagnosa = "";
            tindakan = "";
            
            //simpan diagnosa sekunder ICD-10------------->>
            try {
                psdiag = koneksi.prepareStatement("SELECT dp.kd_penyakit icd_sekunder, py.ciri_ciri diag_sekunder FROM diagnosa_pasien dp "
                        + "INNER JOIN penyakit py ON py.kd_penyakit = dp.kd_penyakit "
                        + "WHERE dp.no_rawat like '%" + TNoRW.getText() + "%' AND dp.prioritas <> 1 AND dp. STATUS = 'ranap'");
                try {
                    rsdiag = psdiag.executeQuery();
                    i = 1;
                    while (rsdiag.next()) {
                        if (diagnosa.equals("")) {
                            diagnosa = i + ". " + rsdiag.getString("diag_sekunder") + " (ICD 10 : " + rsdiag.getString("icd_sekunder") + ")";
                        } else {
                            diagnosa = diagnosa + "\n" + i + ". " + rsdiag.getString("diag_sekunder") + " (ICD 10 : " + rsdiag.getString("icd_sekunder") + ")";
                        }
                        i++;
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }

            //simpan tindakan prosedur ------------->>
            try {
                pspros = koneksi.prepareStatement("SELECT pp.kode, i.deskripsi_panjang FROM prosedur_pasien pp INNER JOIN icd9 i ON i.kode = pp.kode "
                        + "WHERE pp.no_rawat like '%" + TNoRW.getText() + "%' AND pp. STATUS = 'ranap'");
                try {
                    rspros = pspros.executeQuery();
                    i = 1;
                    while (rspros.next()) {
                        if (tindakan.equals("")) {
                            tindakan = i + ". " + rspros.getString("deskripsi_panjang") + " (ICD 9 CM : " + rspros.getString("kode") + ")";
                        } else {
                            tindakan = tindakan + "\n" + i + ". " + rspros.getString("deskripsi_panjang") + " (ICD 9 CM : " + rspros.getString("kode") + ")";
                        }
                        i++;
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));                    
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TNmPasien.getText());
            param.put("tgllahir", TTglLhr.getText());
            param.put("jk", TJK.getText());
            param.put("tglmsk", TTglMsk.getText());
            param.put("tglplg", TTglPulang.getText());
            param.put("rgrawat", TRuangrawat.getText());
            param.put("crbayar", TCaraBayar.getText());
            param.put("drDPJP", Tdpjp.getText());
            param.put("nmdokter", TNmDokter.getText());            
            param.put("alasan", TAlasanDirawat.getText());
            param.put("ringkasan", TRingkasanRiwayat.getText());
            param.put("fisik", TPemeriksaanFisik.getText());
            param.put("penunjang", TPemeriksaanPenunjang.getText());
            param.put("terapi", TTerapiPengobatan.getText());            
            param.put("diagnosaUtama", TDiagUtama.getText());
            param.put("diagnosaSekunder", TDiagSekunder.getText());
            param.put("diagnosaSekunderList", diagnosa);
            param.put("tindakan", TTindakan.getText());
            param.put("tindakanList", tindakan);            
            param.put("png_jawab_px", TKlgPasien.getText());
            param.put("kondisiPlg", cmbKondisiWP.getSelectedItem().toString());
            param.put("keadaanumum", TKeadaanumum.getText());
            param.put("kesadaran", TKesadaran.getText() + ", GCS : " + Tgcs.getText());
            param.put("tandavital", "Tekanan Darah : " + TTensi.getText() + " mmHg, Suhu : " + TSuhu.getText() + " °C, Nadi : " + TNadi.getText() + " x/mnt, Frekuensi Nafas : " + TFrekuensiNafas.getText() + " x/mnt");
            param.put("edukasi", Tedukasi.getText());
            param.put("catatanPenting", TCatatan.getText());
            param.put("terapiPlg", TTerapiPulang.getText());
            param.put("pengobatan", cmbLanjutan.getSelectedItem().toString() + " " + TDokterLuar.getText());

            if (chkTglKontrol.isSelected() == false) {
                param.put("tglkontrolpoli", "-");
            } else {
                param.put("tglkontrolpoli", Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_kontrol_poliklinik from ringkasan_pulang_ranap where no_rawat='" + TNoRW.getText() + "'")));
            }

            param.put("tglRingkasan", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("select if(tgl_keluar='0000-00-00',date(now()),tgl_keluar) from kamar_inap where "
                    + "no_rawat='" + TNoRW.getText() + "' and stts_pulang<>'Pindah Kamar' order by tgl_masuk desc, jam_masuk desc limit 1")));
            param.put("jamRingkasan", "Jam          : " + Sequel.cariIsi("select if(jam_keluar='00:00:00',time_format(time(now()),'%H:%i'),time_format(jam_keluar,'%H:%i')) from kamar_inap where "
                    + "no_rawat='" + TNoRW.getText() + "' and stts_pulang<>'Pindah Kamar' order by tgl_masuk desc, jam_masuk desc limit 1") + " WITA");

            Valid.MyReport("rptRingkasanPulangRanap.jasper", "report", "::[ Lembar Ringkasan Pulang Pasien Rawat Inap ]::",
                    "select date(now())", param);
            this.setCursor(Cursor.getDefaultCursor());
            
            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih salah satu data pada tabel terlebih dahulu..!!!!");
            TabRingkasan.setSelectedIndex(1);
            tbRingkasan.requestFocus();
        }
    }//GEN-LAST:event_MnCetakRingkasanActionPerformed

    private void chkTglKontrolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglKontrolActionPerformed
        if (chkTglKontrol.isSelected() == true) {
            TglKontrol.setEnabled(true);
            TglKontrol.requestFocus();
            TglKontrol.setDate(new Date());
        } else {
            TglKontrol.setEnabled(false);
            TglKontrol.setDate(new Date());
        }
    }//GEN-LAST:event_chkTglKontrolActionPerformed

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

    private void TDokterLuarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDokterLuarKeyPressed
        Valid.pindah(evt, cmbLanjutan, TCatatan);
    }//GEN-LAST:event_TDokterLuarKeyPressed

    private void TedukasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TedukasiKeyPressed
        Valid.pindah(evt, TKesadaran, TTensi);
    }//GEN-LAST:event_TedukasiKeyPressed

    private void TKlgPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKlgPasienKeyPressed
        Valid.pindah(evt, TDiagSekunder, TTindakan);
    }//GEN-LAST:event_TKlgPasienKeyPressed

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        if (tbRingkasan.getSelectedRow() > -1) {
            kodekamar = "";
            kodekamar = Sequel.cariIsi("select ki.kd_kamar from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRW.getText() + "' "
                    + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");

            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRingkasanPulangRanap");
            DlgCatatanResep form = new DlgCatatanResep(null, false);
            form.isCek();
            form.setData(TNoRW.getText(), "ranap");
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            
            TCari.setText(TNoRW.getText());
            tampil();
            emptTeks();
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih datanya terlebih dahulu pada tabel..!!");
            TabRingkasan.setSelectedIndex(1);
            tbRingkasan.requestFocus();
        }
    }//GEN-LAST:event_BtnResepActionPerformed

    private void BtnVerifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVerifActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien belum dipilih..!!!!");
        } else {
            if (akses.getadmin() == true) {
                DlgVerifikasiCPPT verif = new DlgVerifikasiCPPT(null, false);
                verif.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                verif.setLocationRelativeTo(internalFrame1);
                verif.setData(TNoRW.getText(), "ranap");
                verif.setVisible(true);
                
                TCari.setText(TNoRW.getText());
                tampil();
                emptTeks();
            } else if (akses.getkode().equals(Sequel.cariIsi("select kd_dokter from dpjp_ranap where no_rawat='" + TNoRW.getText() + "'"))) {
                DlgVerifikasiCPPT verif = new DlgVerifikasiCPPT(null, false);
                verif.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                verif.setLocationRelativeTo(internalFrame1);
                verif.setData(TNoRW.getText(), "ranap");
                verif.setVisible(true);
                
                TCari.setText(TNoRW.getText());
                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, "Verifikasi CPPT hanya dilakukan oleh DPJP pasien tersebut...!!!");
                TCari.setText(TNoRW.getText());
                tampil();
                emptTeks();
            }
        }
    }//GEN-LAST:event_BtnVerifActionPerformed

    private void MnTriaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTriaseActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from triase_igd where no_rawat='" + TNoRW.getText() + "'") > 0) {
                cetakDataTriase();
            } else {
                JOptionPane.showMessageDialog(null, "Data triase IGD tidak ditemukan...!!!");
            }
        }
    }//GEN-LAST:event_MnTriaseActionPerformed

    private void MnAsesmenMedikIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsesmenMedikIGDActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat='" + TNoRW.getText() + "'") > 0) {
                cetakAsesMedikIGD();
            } else {
                JOptionPane.showMessageDialog(null, "Data asesmen medik IGD tidak ditemukan...!!!");
            }
        }
    }//GEN-LAST:event_MnAsesmenMedikIGDActionPerformed

    private void MnAsesmenKeperawatanIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsesmenKeperawatanIGDActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_igdrz where no_rawat='" + TNoRW.getText() + "'") > 0) {
                cetakAsesKepIGD();
            } else {
                JOptionPane.showMessageDialog(null, "Data asesmen keperawatan IGD tidak ditemukan...!!!");
            }
        }
    }//GEN-LAST:event_MnAsesmenKeperawatanIGDActionPerformed

    private void MnAsesmenMedikObstetriIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsesmenMedikObstetriIGDActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRW.getText() + "'") > 0) {
                cetakAsesMedikObs();
            } else {
                JOptionPane.showMessageDialog(null, "Data asesmen medik obstetri pasien IGD tidak ditemukan...!!!");
            }
        }
    }//GEN-LAST:event_MnAsesmenMedikObstetriIGDActionPerformed

    private void MnAsesmenKebidananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsesmenKebidananActionPerformed
        JOptionPane.showMessageDialog(null, "Segera tayang (Comming Soon)...!!!");
    }//GEN-LAST:event_MnAsesmenKebidananActionPerformed

    private void BtnPastePenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPastePenunjangActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu hasil pemeriksaan lab. yg. dipilih..!!!!");
        } else {
            if (TPemeriksaanPenunjang.getText().equals("")) {
                TPemeriksaanPenunjang.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                TPemeriksaanPenunjang.setText(TPemeriksaanPenunjang.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPastePenunjangActionPerformed

    private void THasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THasilKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TCatatan.requestFocus();
        }
    }//GEN-LAST:event_THasilKeyPressed

    private void BtnPasteHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteHasilActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu hasil pemeriksaan..!!!!");
        } else {
            if (THasil.getText().equals("")) {
                THasil.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                THasil.setText(THasil.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteHasilActionPerformed

    private void noregKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noregKeyPressed
        i = 0;
        i = noreg.getText().length();
        jml_noreg.setText("Jumlah No. Reg. TB : " + i + " digit");
    }//GEN-LAST:event_noregKeyPressed

    private void noregKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noregKeyReleased
        i = 0;
        i = noreg.getText().length();
        jml_noreg.setText("Jumlah No. Reg. TB : " + i + " digit");
    }//GEN-LAST:event_noregKeyReleased

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("DlgRingkasanPulangRanap");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    private void BtnPasteTerapiPulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteTerapiPulangActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu resep pulangnya..!!!!");
        } else {
            if (TTerapiPulang.getText().equals("")) {
                TTerapiPulang.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                TTerapiPulang.setText(TTerapiPulang.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteTerapiPulangActionPerformed

    private void BtnTTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTTEActionPerformed
        if (akses.getadmin() == true) {
            WindowTTE.setSize(505, 143);
            WindowTTE.setLocationRelativeTo(internalFrame1);
            WindowTTE.setVisible(true);
            kddokter.setText(Sequel.cariIsi("select no_ktp from pegawai where nik = '" + akses.getkode() + "'"));
            TDokter.setText(Sequel.cariIsi("select nama from pegawai where nik = '" + akses.getkode() + "'"));
        } else {
            JOptionPane.showMessageDialog(null, "Untuk saat ini belum bisa difungsikan, masih menunggu sosialisasi dari manajemen..!!");
        }
    }//GEN-LAST:event_BtnTTEActionPerformed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        String data = "";
        if (tbRingkasan.getSelectedRow() > -1) {
            if (Sequel.cariInteger("select count(-1) from rme_file_upload where no_rawat = '" + TNoRW.getText() + "' and jenis_pemeriksaan = 'RSM1' and stts_data = '1'") > 0) {
                JOptionPane.showMessageDialog(null, "Dokumen sudah diverifikasi,...!!!");
            } else {
                if (tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 35).toString().equals(akses.getkode())) {
                    diagnosa = "";
                    tindakan = "";

                    //simpan diagnosa sekunder ICD-10------------->>
                    try {
                        psdiag = koneksi.prepareStatement("SELECT dp.kd_penyakit icd_sekunder, py.ciri_ciri diag_sekunder FROM diagnosa_pasien dp "
                                + "INNER JOIN penyakit py ON py.kd_penyakit = dp.kd_penyakit "
                                + "WHERE dp.no_rawat like '%" + TNoRW.getText() + "%' AND dp.prioritas <> 1 AND dp. STATUS = 'ranap'");
                        try {
                            rsdiag = psdiag.executeQuery();
                            i = 1;
                            while (rsdiag.next()) {
                                if (diagnosa.equals("")) {
                                    diagnosa = i + ". " + rsdiag.getString("diag_sekunder") + " (ICD 10 : " + rsdiag.getString("icd_sekunder") + ")";
                                } else {
                                    diagnosa = diagnosa + "\n" + i + ". " + rsdiag.getString("diag_sekunder") + " (ICD 10 : " + rsdiag.getString("icd_sekunder") + ")";
                                }
                                i++;
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }

                    //simpan tindakan prosedur ------------->>
                    try {
                        pspros = koneksi.prepareStatement("SELECT pp.kode, i.deskripsi_panjang FROM prosedur_pasien pp INNER JOIN icd9 i ON i.kode = pp.kode "
                                + "WHERE pp.no_rawat like '%" + TNoRW.getText() + "%' AND pp. STATUS = 'ranap'");
                        try {
                            rspros = pspros.executeQuery();
                            i = 1;
                            while (rspros.next()) {
                                if (tindakan.equals("")) {
                                    tindakan = i + ". " + rspros.getString("deskripsi_panjang") + " (ICD 9 CM : " + rspros.getString("kode") + ")";
                                } else {
                                    tindakan = tindakan + "\n" + i + ". " + rspros.getString("deskripsi_panjang") + " (ICD 9 CM : " + rspros.getString("kode") + ")";
                                }
                                i++;
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }

                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    param.put("norm", TNoRM.getText());
                    param.put("nmpasien", TNmPasien.getText());
                    param.put("tgllahir", TTglLhr.getText());
                    param.put("jk", TJK.getText());
                    param.put("tglmsk", TTglMsk.getText());
                    param.put("tglplg", TTglPulang.getText());
                    param.put("rgrawat", TRuangrawat.getText());
                    param.put("crbayar", TCaraBayar.getText());
                    param.put("drDPJP", Tdpjp.getText());
                    param.put("nmdokter", TNmDokter.getText());
                    param.put("alasan", TAlasanDirawat.getText());
                    param.put("ringkasan", TRingkasanRiwayat.getText());
                    param.put("fisik", TPemeriksaanFisik.getText());
                    param.put("penunjang", TPemeriksaanPenunjang.getText());
                    param.put("terapi", TTerapiPengobatan.getText());
                    param.put("diagnosaUtama", TDiagUtama.getText());
                    param.put("diagnosaSekunder", TDiagSekunder.getText());
                    param.put("diagnosaSekunderList", diagnosa);
                    param.put("tindakan", TTindakan.getText());
                    param.put("tindakanList", tindakan);
                    param.put("png_jawab_px", TKlgPasien.getText());
                    param.put("kondisiPlg", cmbKondisiWP.getSelectedItem().toString());
                    param.put("keadaanumum", TKeadaanumum.getText());
                    param.put("kesadaran", TKesadaran.getText() + ", GCS : " + Tgcs.getText());
                    param.put("tandavital", "Tekanan Darah : " + TTensi.getText() + " mmHg, Suhu : " + TSuhu.getText() + " °C, Nadi : " + TNadi.getText() + " x/mnt, Frekuensi Nafas : " + TFrekuensiNafas.getText() + " x/mnt");
                    param.put("edukasi", Tedukasi.getText());
                    param.put("catatanPenting", TCatatan.getText());
                    param.put("terapiPlg", TTerapiPulang.getText());
                    param.put("pengobatan", cmbLanjutan.getSelectedItem().toString() + " " + TDokterLuar.getText());

                    if (chkTglKontrol.isSelected() == false) {
                        param.put("tglkontrolpoli", "-");
                    } else {
                        param.put("tglkontrolpoli", Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_kontrol_poliklinik from ringkasan_pulang_ranap where no_rawat='" + TNoRW.getText() + "'")));
                    }

                    param.put("tglRingkasan", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("select if(tgl_keluar='0000-00-00',date(now()),tgl_keluar) from kamar_inap where "
                            + "no_rawat='" + TNoRW.getText() + "' and stts_pulang<>'Pindah Kamar' order by tgl_masuk desc, jam_masuk desc limit 1")));
                    param.put("jamRingkasan", "Jam          : " + Sequel.cariIsi("select if(jam_keluar='00:00:00',time_format(time(now()),'%H:%i'),time_format(jam_keluar,'%H:%i')) from kamar_inap where "
                            + "no_rawat='" + TNoRW.getText() + "' and stts_pulang<>'Pindah Kamar' order by tgl_masuk desc, jam_masuk desc limit 1") + " WITA");

                    data = Valid.saveToPDFTte("rptRingkasanPulangRanapEnc.jasper", "report", TNoRW.getText(), param);

                    try {
                        byte[] input_file = Files.readAllBytes(Paths.get(data));
                        byte[] encodedBytes = Base64.getEncoder().encode(input_file);
                        String encodedString = new String(encodedBytes);

                        mengunggahFile(TNoRW.getText().replaceAll("/", "") + ".pdf", encodedString, TNoRW.getText(), kddokter.getText(), "RSM1", Tpaspras.getText(), Sequel.cariIsi("select status_lanjut from reg_periksa where no_rawat = '" + TNoRW.getText() + "'"), akses.getkode(), TNoRM.getText());
//                    mengunggahFile(TNoRW.getText().replaceAll("/", "") + ".pdf", encodedString, TNoRW.getText(), "0803202100007062", "RSM1", "Hantek1234.!", Sequel.cariIsi("select status_lanjut from reg_periksa where no_rawat = '" + TNoRW.getText() + "'"), akses.getkode(), TNoRM.getText());
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                    this.setCursor(Cursor.getDefaultCursor());

                    tampil();
                    emptTeks();
                    WindowTTE.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, DPJP pada ringkasan pulang/resum medis pasien ini berbeda dengan yang login..!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih salah satu data pada tabel terlebih dahulu..!!!!");
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowTTE.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void BtnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasienActionPerformed
        ChkAccor.setSelected(false);
        isMenu();
        
        WindowPasien.setSize(873, internalFrame1.getHeight() - 40);
        WindowPasien.setLocationRelativeTo(internalFrame1);
        WindowPasien.setVisible(true);
        TCari1.setText(TNoRM.getText());
        tampilPasien();
    }//GEN-LAST:event_BtnPasienActionPerformed

    private void BtnCloseIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn2ActionPerformed
        WindowPasien.dispose();
    }//GEN-LAST:event_BtnCloseIn2ActionPerformed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } 
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilPasien();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void tbPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPasienMouseClicked
        if (tabMode1.getRowCount() != 0) {
            if (evt.getClickCount() == 2) {
                getDataPasien();
                WindowPasien.dispose();
                cekDpjp();
            }
        }
    }//GEN-LAST:event_tbPasienMouseClicked

    private void tbPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN) | (evt.getKeyCode() == KeyEvent.VK_SPACE)) {
                try {
                    getDataPasien();
                    WindowPasien.dispose();
                    cekDpjp();                    
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPasienKeyPressed

    private void BtnCloseIn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn10ActionPerformed
        WindowDPJPranap.dispose();
        kddpjp.setText("-");
        nmdpjp.setText("-");
        Tdpjp.setText(Sequel.cariIsi("SELECT ifnull(d.nm_dokter,'-') FROM reg_periksa rp LEFT JOIN dpjp_ranap dr ON dr.no_rawat=rp.no_rawat "
                + "LEFT JOIN dokter d ON d.kd_dokter = dr.kd_dokter WHERE rp.no_rawat = '" + TNoRW.getText() + "'"));
    }//GEN-LAST:event_BtnCloseIn10ActionPerformed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
        if (kddpjp.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu DPJP nya...!");
            btnDPJP.requestFocus();
        } else if (kddpjp.getText().equals("-") || kddpjp.getText().equals("--")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu DPJP nya dengan benar...!");
            btnDPJP.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from dpjp_ranap where no_rawat='" + TNoRW.getText() + "'") > 0) {
                if (Sequel.menyimpantf("perubahan_dpjp_ranap", "?,?,?,?,?,?", "No.Rawat", 6, new String[]{
                    TNoRW.getText(), nipDpjpAwal, kddpjp.getText(), Sequel.cariIsi("select date(now())"),
                    "Perubahan menyesuaikan dengan resume medis", Sequel.cariIsi("select now()")
                }) == true) {
                    Sequel.queryu("delete from dpjp_ranap where no_rawat='" + TNoRW.getText() + "'");
                    Sequel.menyimpan("dpjp_ranap", "'" + TNoRW.getText() + "','" + kddpjp.getText() + "'", "DPJP");
                    BtnCloseIn10ActionPerformed(null);
                }
            } else {
                Sequel.menyimpanPesanGagalnyaDiTerminal("dpjp_ranap", "?,?", "DPJP Rawat Inap", 2, new String[]{
                    TNoRW.getText(), kddpjp.getText()
                });
                BtnCloseIn10ActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void btnDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDPJPActionPerformed
        pilihan = 2;
        akses.setform("DlgRingkasanPulangRanap");
        dokter.isCek();
        dokter.TCari.requestFocus();
        dokter.setSize(1045, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
        dokter.emptTeks();
    }//GEN-LAST:event_btnDPJPActionPerformed

    private void BtnNamaDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNamaDPJPActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu pasiennya...!");
        } else {
            nipDpjpAwal = "";
            ChkAccor.setSelected(false);
            isMenu();

            WindowDPJPranap.setSize(615, 110);
            WindowDPJPranap.setLocationRelativeTo(internalFrame1);
            WindowDPJPranap.setVisible(true);
            kddpjp.setText(Sequel.cariIsi("select ifnull(kd_dokter,'') from dpjp_ranap where no_rawat='" + TNoRW.getText() + "'"));
            nipDpjpAwal = kddpjp.getText();
            if (kddpjp.getText().equals("")) {
                nmdpjp.setText("");
            } else {
                nmdpjp.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddpjp.getText() + "'"));
            }
            btnDPJP.requestFocus();
        }
    }//GEN-LAST:event_BtnNamaDPJPActionPerformed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari1.setText("");
        tampilPasien();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAll1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRW.getText().trim().equals("") || TNmPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            akses.setform("DlgRingkasanPulangRanap");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRW.getText(), TNmPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        MnCetakRingkasanActionPerformed(null);
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRW.getText().trim().equals("") || TNmPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRingkasanPulangRanap");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRW.getText(), TNoRM.getText(), TNmPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

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

    private void cmbAsesmenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAsesmenActionPerformed
        if (cmbAsesmen.getSelectedIndex() == 0) {
            TTerapiPengobatan.setText("");
            TDiagUtama.setText("");
            TAlasanDirawat.setText("");
            TRingkasanRiwayat.setText("");
            TPemeriksaanFisik.setText("");
        } else if (cmbAsesmen.getSelectedIndex() == 1) {
            if (Sequel.cariInteger("select count(-1) from asesmen_medik_dewasa_ranap where no_rawat='" + TNoRW.getText() + "'") > 0) {
                tampilAsesmenDewasa();
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, asesmen medik dewasa utk. pasien ini belum diisi..!!!!");
                cmbAsesmen.setSelectedIndex(0);
                TTerapiPengobatan.setText("");
                TDiagUtama.setText("");
                TDiagSekunder.setText("");
                TAlasanDirawat.setText("");
                TRingkasanRiwayat.setText("");
                TPemeriksaanFisik.setText("");
            }
        } else if (cmbAsesmen.getSelectedIndex() == 2) {
            if (Sequel.cariInteger("select count(-1) from asesmen_medik_anak_ranap where no_rawat='" + TNoRW.getText() + "'") > 0) {
                tampilAsesmenAnak();
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, asesmen medik anak utk. pasien ini belum diisi..!!!!");
                cmbAsesmen.setSelectedIndex(0);
                TTerapiPengobatan.setText("");
                TDiagUtama.setText("");
                TAlasanDirawat.setText("");
                TRingkasanRiwayat.setText("");
                TPemeriksaanFisik.setText("");
            }
        } else if (cmbAsesmen.getSelectedIndex() == 3) {
            if (akses.getadmin() == true) {
                if (Sequel.cariInteger("select count(-1) from asesmen_medik_bedah_ranap where no_rawat='" + TNoRW.getText() + "'") > 0) {
//                tampilAsesmenAnak();
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, asesmen medik bedah utk. pasien ini belum diisi..!!!!");
                    cmbAsesmen.setSelectedIndex(0);
                    TTerapiPengobatan.setText("");
                    TDiagUtama.setText("");
                    TAlasanDirawat.setText("");
                    TRingkasanRiwayat.setText("");
                    TPemeriksaanFisik.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, masih dalam proses dikerjakan..!!!!");
            }
        } else if (cmbAsesmen.getSelectedIndex() == 4) {
            if (Sequel.cariInteger("select count(-1) from asesmen_medik_perinatologi where no_rawat='" + TNoRW.getText() + "'") > 0) {
                tampilAsesmenPerinatologi();
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, asesmen medik perinatologi utk. pasien ini belum diisi..!!!!");
                cmbAsesmen.setSelectedIndex(0);
                TTerapiPengobatan.setText("");
                TDiagUtama.setText("");
                TAlasanDirawat.setText("");
                TRingkasanRiwayat.setText("");
                TPemeriksaanFisik.setText("");
            }
        }
    }//GEN-LAST:event_cmbAsesmenActionPerformed

    private void BtnCloseIn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn11ActionPerformed
        WindowDokterPenyimpan.dispose();
    }//GEN-LAST:event_BtnCloseIn11ActionPerformed

    private void BtnSimpan7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan7ActionPerformed
        if (kddokter1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu nama dokternya...!");
            btnDokter1.requestFocus();
        } else if (kddokter1.getText().equals("-") || kddokter1.getText().equals("--")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu nama dokternya dengan benar...!");
            btnDokter1.requestFocus();
        } else {
            Sequel.mengedit("ringkasan_pulang_ranap", "no_rawat='" + TNoRW.getText() + "'", "nip_penyimpan='" + kddokter1.getText() + "'");
            BtnCloseIn11ActionPerformed(null);
            TCari.setText(TNoRW.getText());
            emptTeks();
            tampil();
            TabRingkasan.setSelectedIndex(1);
        }
    }//GEN-LAST:event_BtnSimpan7ActionPerformed

    private void btnDokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter1ActionPerformed
        pilihan = 3;
        akses.setform("DlgRingkasanPulangRanap");
        dokter.isCek();
        dokter.TCari.requestFocus();
        dokter.setSize(1045, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
        dokter.emptTeks();
    }//GEN-LAST:event_btnDokter1ActionPerformed

    private void MnGantiDokterSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiDokterSimpanActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "nomor rawat");
        } else {
            if (tbRingkasan.getSelectedRow() > -1) {
                ChkAccor.setSelected(false);
                isMenu();

                WindowDokterPenyimpan.setSize(641, 110);
                WindowDokterPenyimpan.setLocationRelativeTo(internalFrame1);
                WindowDokterPenyimpan.setVisible(true);
                kddokter1.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 35).toString());
                nmdokter1.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 37).toString());
                btnDokter1.requestFocus();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih datanya terlebih dahulu pada tabel..!!");
                tbRingkasan.requestFocus();
            }
        }
    }//GEN-LAST:event_MnGantiDokterSimpanActionPerformed

    private void tbLISMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLISMouseClicked
        if (tabModeLis.getRowCount() != 0) {
            try {
                getDataLis();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbLISMouseClicked

    private void tbLISKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbLISKeyPressed
        if (tabModeLis.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataLis();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbLISKeyPressed

    private void TCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari4ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari4.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCari3KeyPressed

    private void BtnCari4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari4ActionPerformed
        Valid.tabelKosong(tabModeHasilLab);
        tampilLIS();
    }//GEN-LAST:event_BtnCari4ActionPerformed

    private void BtnCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari4ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari4KeyPressed

    private void tbHasilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHasilMouseClicked
        if (tabModeHasilLab.getRowCount() != 0) {
            try {

                if (tbHasil.getValueAt(tbHasil.getSelectedRow(), 0).toString().equals("true")) {
                    tabModeHasilCopy.addRow(new Object[]{
                        tbHasil.getValueAt(tbHasil.getSelectedRow(), 1).toString(),
                        tbHasil.getValueAt(tbHasil.getSelectedRow(), 2).toString(),
                        tbHasil.getValueAt(tbHasil.getSelectedRow(), 3).toString()
                    });
                }

            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbHasilMouseClicked

    private void BtnContengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnContengActionPerformed
        if (tabModeHasilLab.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Hasil pemeriksaan laboratorium belum dipilih...!!!!");
        } else {
            tampilLIS();
            for (i = 0; i < tbHasil.getRowCount(); i++) {
                tbHasil.setValueAt(Boolean.TRUE, i, 0);
            }

            try {
                for (i = 0; i < tbHasil.getRowCount(); i++) {
                    if (tbHasil.getValueAt(i, 0).toString().equals("true")) {
                        tabModeHasilCopy.addRow(new Object[]{
                            tbHasil.getValueAt(i, 1).toString(),
                            tbHasil.getValueAt(i, 2).toString(),
                            tbHasil.getValueAt(i, 3).toString()
                        });
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }//GEN-LAST:event_BtnContengActionPerformed

    private void BtnHapus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus2ActionPerformed
        if (tabModeHasilLab.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Hasil pemeriksaan laboratorium belum dipilih...!!!!");
        } else {
            tampilLIS();
            for (i = 0; i < tbHasil.getRowCount(); i++) {
                tbHasil.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_BtnHapus2ActionPerformed

    private void BtnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopyActionPerformed
        if (tabModeHasilCopy.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Hasil pemeriksaan laboratorium yg. dipilih utk. dicopy belum ada...!!!!");
        } else {
            akses.setCopyData("");
            hasilDipilih = "";
            try {
                for (i = 0; i < tbHasilCopy.getRowCount(); i++) {
                    if (hasilDipilih.equals("")) {
                        hasilDipilih = tbHasilCopy.getValueAt(i, 0).toString() + " "
                        + tbHasilCopy.getValueAt(i, 1).toString() + " "
                        + tbHasilCopy.getValueAt(i, 2).toString();
                    } else {
                        hasilDipilih = hasilDipilih + "\n" + tbHasilCopy.getValueAt(i, 0).toString() + " "
                        + tbHasilCopy.getValueAt(i, 1).toString() + " "
                        + tbHasilCopy.getValueAt(i, 2).toString();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }

            akses.setCopyData(hasilDipilih);
        }
    }//GEN-LAST:event_BtnCopyActionPerformed

    private void BtnUlangiCopyLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUlangiCopyLabActionPerformed
        Valid.tabelKosong(tabModeHasilLab);
        Valid.tabelKosong(tabModeHasilCopy);
        TCari3.setText("");
        tampilLIS();
    }//GEN-LAST:event_BtnUlangiCopyLabActionPerformed

    private void BtnKeluar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar5ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar5ActionPerformed

    private void MnHapusDipilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusDipilihActionPerformed
        if (tbHasilCopy.getSelectedRow() > -1) {
            tabModeHasilCopy.removeRow(tbHasilCopy.getSelectedRow());
            BtnHapus2ActionPerformed(null);
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik pilih salah satu datanya dulu..!!!!");
            tbHasilCopy.requestFocus();
        }
    }//GEN-LAST:event_MnHapusDipilihActionPerformed

    private void MnHapusSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusSemuaActionPerformed
        if (tabModeHasilCopy.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tidak ada data pemeriksaan lab. yang dipilih utk. dicopy..!!!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah semua data pemeriksaan lab. yang dipilih akan dihapus semua..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                Valid.tabelKosong(tabModeHasilCopy);
                BtnHapus2ActionPerformed(null);
            }
        }
    }//GEN-LAST:event_MnHapusSemuaActionPerformed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        BtnHapusActionPerformed(null);
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        BtnPrintActionPerformed(null);
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnResep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResep1ActionPerformed
        BtnResepActionPerformed(null);
    }//GEN-LAST:event_BtnResep1ActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnTTE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTTE1ActionPerformed
        BtnTTEActionPerformed(null);
    }//GEN-LAST:event_BtnTTE1ActionPerformed

    private void TCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari5ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari5.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCari4KeyPressed

    private void BtnCari5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari5ActionPerformed
        HasilPeriksa.setText("");
        tampilItem();
    }//GEN-LAST:event_BtnCari5ActionPerformed

    private void BtnCari5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari5ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari5KeyPressed

    private void BtnCopy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopy1ActionPerformed
        if (HasilPeriksa.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu tanggal hasil pemeriksaan radiologinya...!!!!");
            tbRadiologi.requestFocus();
        } else {
            akses.setCopyData("");
            akses.setCopyData(HasilPeriksa.getText());
        }
    }//GEN-LAST:event_BtnCopy1ActionPerformed

    private void BtnUlangiCopyRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUlangiCopyRadActionPerformed
        TCari4.setText("");
        HasilPeriksa.setText("");
        tampilItem();
    }//GEN-LAST:event_BtnUlangiCopyRadActionPerformed

    private void BtnKeluar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar6ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar6ActionPerformed

    private void ChkDokumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkDokumenActionPerformed
        tampilDokJangMed();
    }//GEN-LAST:event_ChkDokumenActionPerformed

    private void BtnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUploadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            Valid.panggilUrlRME("/rmelokal/");
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }

        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnUploadActionPerformed

    private void BtnHapus3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus3ActionPerformed
        if (LoadHTML1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data yg. akan dihapus tidak ditemukan..!!");
        } else {
            WindowHapusDokJangMed.setSize(737, internalFrame1.getHeight() - 40);
            WindowHapusDokJangMed.setLocationRelativeTo(internalFrame1);
            WindowHapusDokJangMed.setAlwaysOnTop(false);
            WindowHapusDokJangMed.setVisible(true);
            TCari5.setText("");
            BtnCari7ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnHapus3ActionPerformed

    private void BtnCari6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari6ActionPerformed
        tampilDokJangMed();
    }//GEN-LAST:event_BtnCari6ActionPerformed

    private void BtnCari6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari6KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari6KeyPressed

    private void BtnKeluar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar7ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar7ActionPerformed

    private void MnJenisDokumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJenisDokumenActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("DlgRingkasanPulangRanap");
        DlgMasterJenisDokumenJangMed form = new DlgMasterJenisDokumenJangMed(null, false);
        form.setSize(626, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJenisDokumenActionPerformed

    private void TCari5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari7ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari7.requestFocus();
        }
    }//GEN-LAST:event_TCari5KeyPressed

    private void BtnCari7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari7ActionPerformed
        tampilFile();
    }//GEN-LAST:event_BtnCari7ActionPerformed

    private void BtnCari7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari7KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari7ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari7KeyPressed

    private void ChkDokumen1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkDokumen1ActionPerformed
        tampilFile();
    }//GEN-LAST:event_ChkDokumen1ActionPerformed

    private void BtnHapusFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusFileActionPerformed
        if (tbHapus.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin file ini akan dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    Sequel.mengedit("rme_file_upload", "id_file=?", "stts_data=?", 2, new String[]{
                        "0", tbHapus.getValueAt(tbHapus.getSelectedRow(), 0).toString()
                    });
                    tampilFile();
                }
            } else {
                if (akses.getkode().equals(tbHapus.getValueAt(tbHapus.getSelectedRow(), 6).toString())) {
                    x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin file ini akan dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        Sequel.mengedit("rme_file_upload", "id_file=?", "stts_data=?", 2, new String[]{
                            "0", tbHapus.getValueAt(tbHapus.getSelectedRow(), 0).toString()
                        });
                        tampilFile();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "File gagal terhapus, petugas yang upload tdk. sama dg. petugas yg. login..!!");
                    tbHapus.requestFocus();
                    tampilFile();
                }
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
            tbHapus.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusFileActionPerformed

    private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
        WindowHapusDokJangMed.dispose();
        tampilDokJangMed();
    }//GEN-LAST:event_BtnKeluar2ActionPerformed

    private void BtnKeluar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            WindowHapusDokJangMed.dispose();
        }
    }//GEN-LAST:event_BtnKeluar2KeyPressed

    private void ChkTanggalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkTanggalItemStateChanged
        klikRiwayatRalan();
    }//GEN-LAST:event_ChkTanggalItemStateChanged

    private void ChkTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTanggalActionPerformed
        ChkLihat.setSelected(false);
        cmbBulan.setSelectedIndex(0);
        cmbBulan.setEnabled(false);
    }//GEN-LAST:event_ChkTanggalActionPerformed

    private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
        TPoli.setText("");
        kdpoli.setText("");

        akses.setform("DlgRingkasanPulangRanap");
        poli.isCek();
        poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnUnitActionPerformed

    private void BtnHapusPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusPoliActionPerformed
        kdpoli.setText("");
        TPoli.setText("");
        klikRiwayatRalan();
    }//GEN-LAST:event_BtnHapusPoliActionPerformed

    private void ChkLihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkLihatActionPerformed
        cmbBulan.setSelectedIndex(0);
        cmbBulan.setEnabled(false);

        if (ChkLihat.isSelected() == true) {
            if (kdpoli.getText().equals("")) {
                //khusus dr. rully
                if (akses.getkode().equals("197807242003121005")) {
                    lihatRingkasan();
                    cmbBulan.setEnabled(true);
                    cmbBulan.requestFocus();
                    ChkTanggal.setSelected(false);
                } else {
                    x = JOptionPane.showConfirmDialog(rootPane, "Jika poliklinik tdk. dipilih maka data ringkasan riwayat rawat jalan akan memerlukan      \n"
                        + "waktu beberapa menit utk. ditampilkan, apakah prosesnya akan dilanjutkan..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        lihatRingkasan();
                        cmbBulan.setEnabled(true);
                        cmbBulan.requestFocus();
                        ChkTanggal.setSelected(false);
                    } else {
                        tampilRingkasan();
                        ChkLihat.setSelected(false);
                        cmbBulan.setSelectedIndex(0);
                        cmbBulan.setEnabled(false);
                        ChkTanggal.setSelected(false);
                    }
                }
            } else {
                lihatRingkasan();
                cmbBulan.setEnabled(true);
                cmbBulan.requestFocus();
            }
        } else {
            tampilRingkasan();
            ChkLihat.setSelected(false);
            cmbBulan.setSelectedIndex(0);
            cmbBulan.setEnabled(false);
        }
    }//GEN-LAST:event_ChkLihatActionPerformed

    private void cmbBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBulanActionPerformed
        lihatRingkasan();
    }//GEN-LAST:event_cmbBulanActionPerformed

    private void BtnCari8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari8ActionPerformed
        klikRiwayatRalan();
    }//GEN-LAST:event_BtnCari8ActionPerformed

    private void BtnKeluar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar8ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar8ActionPerformed

    private void MnRehabMedikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRehabMedikActionPerformed
        cekPilihanRehab = 0;

        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
        } else if (!kdpoli.getText().equals("IRM") && !kdpoli.getText().equals("IRS")) {
            JOptionPane.showMessageDialog(rootPane, "Hanya utk. pasien yg. berkunjung ke poliklinik rehabilitasi medik...!!!!");
        } else {
            cekPilihanRehab = Sequel.cariInteger("select count(-1) from data_rehab_medik where no_rawat='" + TNoRW.getText() + "'");

            if (cekPilihanRehab == 0) {
                cmbRM.setSelectedIndex(0);
                cmbRM.requestFocus();
            } else if (cekPilihanRehab > 0) {
                cmbRM.setSelectedItem(Sequel.cariIsi("select jns_rehabmedik from data_rehab_medik where no_rawat='" + TNoRW.getText() + "'"));
            }

            WindowRehabMedik.setSize(535, 84);
            WindowRehabMedik.setLocationRelativeTo(internalFrame1);
            WindowRehabMedik.setAlwaysOnTop(false);
            WindowRehabMedik.setVisible(true);
        }
    }//GEN-LAST:event_MnRehabMedikActionPerformed

    private void BtnCloseIn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn9ActionPerformed
        WindowRehabMedik.dispose();
    }//GEN-LAST:event_BtnCloseIn9ActionPerformed

    private void BtnSimpan8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan8ActionPerformed
        cekPilihanRehab = 0;
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "No. Rawat");
        }

        if (cmbRM.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu pilihan jenis rehabilitasi medik dg. benar...!!!!");
            cmbRM.requestFocus();
        } else {
            cekPilihanRehab = Sequel.cariInteger("select count(-1) from data_rehab_medik where no_rawat='" + TNoRW.getText() + "'");

            if (cekPilihanRehab == 0) {
                Sequel.menyimpan("data_rehab_medik", "?,?", "Jenis Rehabilitasi Medik", 2, new String[]{TNoRW.getText(), cmbRM.getSelectedItem().toString()});
            } else if (cekPilihanRehab > 0) {
                Sequel.mengedit("data_rehab_medik", "no_rawat=?", " jns_rehabmedik=?", 2, new String[]{cmbRM.getSelectedItem().toString(), TNoRW.getText()});
            }

            cekRehabMedik();
            WindowRehabMedik.dispose();
        }
    }//GEN-LAST:event_BtnSimpan8ActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn12.requestFocus();
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

    private void BtnAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll2ActionPerformed
        TCari2.setText("");
        tampilRiwayat();
    }//GEN-LAST:event_BtnAll2ActionPerformed

    private void BtnAll2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAll1ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari2, TCari2);
        }
    }//GEN-LAST:event_BtnAll2KeyPressed

    private void BtnRestorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRestorActionPerformed
        if (tbRiwayat.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data yang dipilih & telah terhapus akan dikembalikan/restore..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.cariInteger("select count(-1) from ringkasan_pulang_ranap where "
                        + "no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString() + "'") > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Proses kembalikan/restore data gagal, krn. sudah ada datanya dg. no. rawat yg. sama..!!");
                    tampilRiwayat();
                } else {
                    kembalikanData();
                    TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                    tampil();
                    emptTeks();
                    TabRingkasan.setSelectedIndex(1);
                }
            } else {
                tampilRiwayat();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
            tbRiwayat.requestFocus();
        }
    }//GEN-LAST:event_BtnRestorActionPerformed

    private void BtnCloseIn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn12ActionPerformed
        WindowRiwayat.dispose();
        TCari2.setText("");
    }//GEN-LAST:event_BtnCloseIn12ActionPerformed

    private void MnRiwayatDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatDataActionPerformed
        DTPCari3.setDate(new Date());
        DTPCari4.setDate(new Date());
        TCari2.setText(TNoRM.getText());
        BtnCari2ActionPerformed(null);
        WindowRiwayat.setSize(949, internalFrame1.getHeight() - 40);
        WindowRiwayat.setLocationRelativeTo(internalFrame1);
        WindowRiwayat.setAlwaysOnTop(false);
        WindowRiwayat.setVisible(true);
    }//GEN-LAST:event_MnRiwayatDataActionPerformed

    private void MnBersihkanStringSampahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBersihkanStringSampahActionPerformed
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah string sampah ini (\\\\\\\\,\\\\,false) akan dibersihkan..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            Sequel.queryu("UPDATE ringkasan_pulang_ranap SET pemeriksaan_penunjang = REPLACE(pemeriksaan_penunjang, 'false', ' ') WHERE no_rawat='" + TNoRW.getText() + "' and pemeriksaan_penunjang LIKE '%false%'");
            Sequel.queryu("UPDATE ringkasan_pulang_ranap SET pemeriksaan_penunjang = REPLACE(pemeriksaan_penunjang, '\\\\''', ' ') WHERE no_rawat='" + TNoRW.getText() + "' and pemeriksaan_penunjang LIKE '%\\\\''%'");
            Sequel.queryu("UPDATE ringkasan_pulang_ranap_histori SET pemeriksaan_penunjang = REPLACE(pemeriksaan_penunjang, 'false', ' ') WHERE no_rawat='" + TNoRW.getText() + "' and pemeriksaan_penunjang LIKE '%false%'");
            Sequel.queryu("UPDATE ringkasan_pulang_ranap_histori SET pemeriksaan_penunjang = REPLACE(pemeriksaan_penunjang, '\\\\''', ' ') WHERE no_rawat='" + TNoRW.getText() + "' and pemeriksaan_penunjang LIKE '%\\\\''%'");
            
            JOptionPane.showMessageDialog(null, "Proses selesai, affected row bisa dicek dikotak hitam/terminal/console...!");
        }
    }//GEN-LAST:event_MnBersihkanStringSampahActionPerformed

    private void tbRadiologiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRadiologiMouseClicked
        if (tabModeRad.getRowCount() != 0) {
            try {
                getDataRadiologi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRadiologiMouseClicked

    private void tbRadiologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRadiologiKeyPressed
        if (tabModeRad.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRadiologi();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRadiologiKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRingkasanPulangRanap dialog = new DlgRingkasanPulangRanap(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll2;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCari4;
    private widget.Button BtnCari5;
    private widget.Button BtnCari6;
    private widget.Button BtnCari7;
    private widget.Button BtnCari8;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn10;
    private widget.Button BtnCloseIn11;
    private widget.Button BtnCloseIn12;
    private widget.Button BtnCloseIn2;
    private widget.Button BtnCloseIn9;
    private widget.Button BtnConteng;
    private widget.Button BtnCopy;
    private widget.Button BtnCopy1;
    public widget.Button BtnDokter;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnHapus2;
    private widget.Button BtnHapus3;
    private widget.Button BtnHapusFile;
    private widget.Button BtnHapusPoli;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar2;
    private widget.Button BtnKeluar5;
    private widget.Button BtnKeluar6;
    private widget.Button BtnKeluar7;
    private widget.Button BtnKeluar8;
    public widget.Button BtnNamaDPJP;
    private widget.Button BtnNotepad;
    private widget.Button BtnPasien;
    private widget.Button BtnPasteHasil;
    private widget.Button BtnPastePenunjang;
    private widget.Button BtnPasteTerapiPulang;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnResep;
    private widget.Button BtnResep1;
    private widget.Button BtnRestor;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan6;
    private widget.Button BtnSimpan7;
    private widget.Button BtnSimpan8;
    private widget.Button BtnTTE;
    private widget.Button BtnTTE1;
    private widget.Button BtnUlangiCopyLab;
    private widget.Button BtnUlangiCopyRad;
    private widget.Button BtnUnit;
    private widget.Button BtnUpload;
    private widget.Button BtnVerif;
    public widget.CekBox ChkAccor;
    public widget.CekBox ChkDokumen;
    public widget.CekBox ChkDokumen1;
    private widget.CekBox ChkLihat;
    private widget.CekBox ChkTanggal;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.Tanggal DTPCari5;
    private widget.Tanggal DTPCari6;
    private widget.PanelBiasa FormInput2;
    private widget.PanelBiasa FormMenu;
    private widget.TextArea HasilPeriksa;
    private widget.Label LCount;
    private widget.Label LCount1;
    private widget.Label LCount2;
    private widget.editorpane LoadHTML1;
    private widget.editorpane LoadHTML2;
    private javax.swing.JMenuItem MnAsesmenKebidanan;
    private javax.swing.JMenuItem MnAsesmenKeperawatanIGD;
    private javax.swing.JMenuItem MnAsesmenMedikIGD;
    private javax.swing.JMenuItem MnAsesmenMedikObstetriIGD;
    private javax.swing.JMenuItem MnBersihkanStringSampah;
    private javax.swing.JMenuItem MnCetakRingkasan;
    private javax.swing.JMenuItem MnDiagnosa;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnGantiDokterSimpan;
    private javax.swing.JMenuItem MnHapusDipilih;
    private javax.swing.JMenuItem MnHapusSemua;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private javax.swing.JMenu MnIGD;
    private javax.swing.JMenuItem MnJenisDokumen;
    private javax.swing.JMenuItem MnRehabMedik;
    private javax.swing.JMenuItem MnRiwayatData;
    private javax.swing.JMenuItem MnTriase;
    private widget.PanelBiasa PanelAccor;
    private javax.swing.JPanel PanelInput;
    private usu.widget.glass.PanelGlass PanelWallpublic;
    private usu.widget.glass.PanelGlass PanelWallwifi;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll13;
    private widget.ScrollPane Scroll14;
    private widget.ScrollPane Scroll15;
    private widget.ScrollPane Scroll16;
    private widget.ScrollPane Scroll17;
    private widget.ScrollPane Scroll18;
    private widget.ScrollPane Scroll19;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll20;
    private widget.ScrollPane Scroll21;
    private widget.ScrollPane Scroll22;
    private widget.ScrollPane Scroll23;
    private widget.ScrollPane Scroll24;
    private widget.ScrollPane Scroll25;
    private widget.ScrollPane Scroll26;
    private widget.ScrollPane Scroll27;
    private widget.ScrollPane Scroll28;
    private widget.ScrollPane Scroll29;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll30;
    private widget.ScrollPane Scroll31;
    private widget.ScrollPane Scroll32;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.TextArea TAlasanDirawat;
    private widget.TextBox TCaraBayar;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TCari2;
    private widget.TextBox TCari3;
    private widget.TextBox TCari4;
    private widget.TextBox TCari5;
    private widget.TextArea TCatatan;
    private widget.TextArea TDiagSekunder;
    private widget.TextArea TDiagUtama;
    private widget.TextBox TDokter;
    private widget.TextBox TDokterLuar;
    private widget.TextBox TFrekuensiNafas;
    private widget.TextArea THasil;
    private widget.TextBox TJK;
    private widget.TextArea TKeadaanumum;
    private widget.TextArea TKesadaran;
    private widget.TextBox TKlgPasien;
    private widget.TextBox TNadi;
    public widget.TextBox TNmDokter;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox TNoRm1;
    private widget.TextBox TNoRm2;
    private widget.TextBox TNoRw1;
    private widget.TextBox TNoRw2;
    private widget.TextBox TPasien1;
    private widget.TextBox TPasien2;
    private widget.TextArea TPemeriksaanFisik;
    private widget.TextArea TPemeriksaanPenunjang;
    private widget.TextBox TPoli;
    private widget.TextArea TRingkasanRiwayat;
    private widget.TextBox TRuangrawat;
    private widget.TextBox TSuhu;
    private widget.TextBox TTensi;
    private widget.TextArea TTerapiPengobatan;
    private widget.TextArea TTerapiPulang;
    private widget.TextBox TTglLhr;
    private widget.TextBox TTglMsk;
    private widget.TextBox TTglPulang;
    private widget.TextArea TTindakan;
    private javax.swing.JTabbedPane TabPencegahanAnak;
    private javax.swing.JTabbedPane TabPencegahanDewasa;
    private widget.TabPane TabRingkasan;
    private javax.swing.JTabbedPane TabTindakanPencegahan;
    private widget.TextBox Tdpjp;
    private widget.TextBox Tedukasi;
    private widget.TextBox Tgcs;
    private widget.Tanggal TglKontrol;
    private widget.TextArea Thasil;
    private widget.TextArea Tinstruksi;
    private widget.PasswordBox Tpaspras;
    private javax.swing.JDialog WindowDPJPranap;
    private javax.swing.JDialog WindowDokterPenyimpan;
    private javax.swing.JDialog WindowHapusDokJangMed;
    private javax.swing.JDialog WindowPasien;
    private javax.swing.JDialog WindowRehabMedik;
    private javax.swing.JDialog WindowRiwayat;
    private javax.swing.JDialog WindowTTE;
    private widget.TextArea anakA;
    private widget.TextArea anakB;
    private widget.Button btnDPJP;
    private widget.Button btnDokter1;
    private widget.CekBox chkTglKontrol;
    private widget.ComboBox cmbAsesmen;
    private widget.ComboBox cmbBulan;
    private widget.ComboBox cmbHlm;
    private widget.ComboBox cmbHlm1;
    private widget.ComboBox cmbKondisiWP;
    private widget.ComboBox cmbLanjutan;
    private widget.ComboBox cmbRM;
    private widget.TextArea dewasaA;
    private widget.TextArea dewasaB;
    private widget.TextArea dewasaC;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame15;
    private widget.InternalFrame internalFrame16;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame29;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame30;
    private widget.InternalFrame internalFrame31;
    private widget.InternalFrame internalFrame32;
    private widget.InternalFrame internalFrame33;
    private widget.InternalFrame internalFrame34;
    private widget.InternalFrame internalFrame35;
    private widget.InternalFrame internalFrame36;
    private widget.InternalFrame internalFrame37;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
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
    private widget.Label jLabel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private widget.Label jml_noreg;
    private widget.TextBox kddokter;
    private widget.TextBox kddokter1;
    private widget.TextBox kddpjp;
    private widget.TextBox kdpoli;
    private widget.Label label_rehab;
    private widget.TextBox nmdokter1;
    private widget.TextBox nmdpjp;
    private widget.TextBox noreg;
    private widget.PanelBiasa panelBiasa10;
    private widget.PanelBiasa panelBiasa14;
    private widget.PanelBiasa panelBiasa15;
    private widget.PanelBiasa panelBiasa6;
    private widget.PanelBiasa panelBiasa7;
    private widget.PanelBiasa panelBiasa8;
    private widget.PanelBiasa panelBiasa9;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass17;
    private widget.panelisi panelGlass18;
    private widget.panelisi panelGlass19;
    private widget.panelisi panelGlass20;
    private widget.panelisi panelGlass21;
    private widget.panelisi panelGlass22;
    private widget.panelisi panelGlass26;
    private widget.panelisi panelGlass27;
    private widget.panelisi panelGlass28;
    private widget.panelisi panelGlass29;
    private widget.panelisi panelGlass30;
    private widget.panelisi panelGlass32;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi6;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbCPPT;
    private widget.Table tbFaktorResiko;
    private widget.Table tbHapus;
    private widget.Table tbHasil;
    private widget.Table tbHasilCopy;
    private widget.Table tbLIS;
    private widget.Table tbPasien;
    private widget.Table tbPembacaLIS;
    private widget.Table tbPembacaRad;
    private widget.Table tbRadiologi;
    private widget.Table tbRingkasan;
    private widget.Table tbRiwayat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT rr.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, IF(p.jk='L','Laki-laki','Perempuan') jk, "
                    + "b.nm_bangsal, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_msk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                    + "IF(rr.nm_dokter_pengirim='','-',rr.nm_dokter_pengirim) dr_pengirim, pj.png_jawab, rr.alasan_masuk_dirawat, rr.ringkasan_riwayat_penyakit, "
                    + "rr.pemeriksaan_fisik, rr.pemeriksaan_penunjang, rr.terapi_pengobatan, rr.diagnosa_utama, rr.diagnosa_sekunder, rr.tindakan_prosedur, "
                    + "rr.keadaan_umum, rr.kesadaran, rr.gcs, rr.tekanan_darah, rr.suhu, rr.nadi, rr.frekuensi_nafas, rr.catatan_penting, "
                    + "rr.terapi_pulang, rr.pengobatan_dilanjutkan, rr.dokter_luar_lanjutan dr_luar, rr.tgl_kontrol_poliklinik tgl_kontrol, ifnull(d.nm_dokter,'-') dpjp, "
                    + "rr.cek_tgl_kontrol, rr.edukasi, rr.penanggung_jwb_pasien, rr.nip_penyimpan, ifnull(rr.hasil_pemeriksaan,'') hasil_pemeriksaan, "
                    + "pg.nama disimpan_oleh, rr.stts_pulang FROM ringkasan_pulang_ranap rr INNER JOIN kamar_inap ki on ki.no_rawat=rr.no_rawat "
                    + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN reg_periksa rp on rp.no_rawat=rr.no_rawat "
                    + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN pegawai pg on pg.nik=rr.nip_penyimpan "
                    + "LEFT JOIN dpjp_ranap dr on dr.no_rawat=ki.no_rawat LEFT JOIN dokter d on d.kd_dokter=dr.kd_dokter where "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.no_rawat like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and p.no_rkm_medis like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and p.nm_pasien like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and IF (p.jk = 'L','Laki-laki','Perempuan') like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and b.nm_bangsal like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and IF (rr.nm_dokter_pengirim = '','-',rr.nm_dokter_pengirim) like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and pj.png_jawab like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.alasan_masuk_dirawat like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.ringkasan_riwayat_penyakit like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.pemeriksaan_fisik like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.pemeriksaan_penunjang like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.terapi_pengobatan like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.diagnosa_utama like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.diagnosa_sekunder like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.tindakan_prosedur like ? or "                    
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.keadaan_umum like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.kesadaran like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.gcs like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.tekanan_darah like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.suhu like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.nadi like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.frekuensi_nafas like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.catatan_penting like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.terapi_pulang like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.pengobatan_dilanjutkan like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.dokter_luar_lanjutan like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.tgl_kontrol_poliklinik like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.stts_pulang like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and d.nm_dokter like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.penanggung_jwb_pasien like ? ORDER BY ki.tgl_masuk desc, ki.jam_masuk desc, rr.no_rawat desc LIMIT 100");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, "%" + TCari.getText().trim() + "%");
                ps.setString(8, "%" + TCari.getText().trim() + "%");
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, "%" + TCari.getText().trim() + "%");                
                ps.setString(11, "%" + TCari.getText().trim() + "%");
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, "%" + TCari.getText().trim() + "%");
                ps.setString(14, "%" + TCari.getText().trim() + "%");
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, "%" + TCari.getText().trim() + "%");
                ps.setString(17, "%" + TCari.getText().trim() + "%");
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, "%" + TCari.getText().trim() + "%");
                ps.setString(20, "%" + TCari.getText().trim() + "%");                
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, "%" + TCari.getText().trim() + "%");
                ps.setString(23, "%" + TCari.getText().trim() + "%");
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                ps.setString(25, "%" + TCari.getText().trim() + "%");
                ps.setString(26, "%" + TCari.getText().trim() + "%");
                ps.setString(27, "%" + TCari.getText().trim() + "%");
                ps.setString(28, "%" + TCari.getText().trim() + "%");
                ps.setString(29, "%" + TCari.getText().trim() + "%");
                ps.setString(30, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();               
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl_lhr"),
                        rs.getString("jk"),                        
                        rs.getString("tgl_msk"),
                        rs.getString("tgl_pulang"),
                        rs.getString("nm_bangsal"),
                        rs.getString("dr_pengirim"),
                        rs.getString("png_jawab"),
                        rs.getString("alasan_masuk_dirawat"),
                        rs.getString("ringkasan_riwayat_penyakit"),
                        rs.getString("pemeriksaan_fisik"),
                        rs.getString("pemeriksaan_penunjang"),
                        rs.getString("terapi_pengobatan"),
                        rs.getString("diagnosa_utama"),
                        rs.getString("diagnosa_sekunder"),
                        rs.getString("tindakan_prosedur"),
                        rs.getString("stts_pulang"),
                        rs.getString("keadaan_umum"),
                        rs.getString("kesadaran"),
                        rs.getString("gcs"),
                        rs.getString("tekanan_darah"),
                        rs.getString("suhu"),
                        rs.getString("nadi"),
                        rs.getString("frekuensi_nafas"),
                        rs.getString("catatan_penting"),
                        rs.getString("terapi_pulang"),
                        rs.getString("pengobatan_dilanjutkan"),
                        rs.getString("dr_luar"),
                        rs.getString("tgl_kontrol"),
                        rs.getString("dpjp"),
                        rs.getString("cek_tgl_kontrol"),
                        rs.getString("edukasi"),
                        rs.getString("penanggung_jwb_pasien"),
                        rs.getString("nip_penyimpan"),
                        rs.getString("hasil_pemeriksaan"),
                        rs.getString("disimpan_oleh")
                    });                    
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgRingkasanPulangRanap.tampil() : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {  
        TNoRM.setText("");
        TNoRW.setText("");
        TNmPasien.setText("");
        TTglLhr.setText("");
        TJK.setText("");
        TTglMsk.setText("");
        TTglPulang.setText("");
        TRuangrawat.setText("");
        TCaraBayar.setText("");
        Tdpjp.setText("");
        TNmDokter.setText("");
        TAlasanDirawat.setText("");
        TRingkasanRiwayat.setText("");
        TPemeriksaanFisik.setText("");
        TPemeriksaanPenunjang.setText("");
        TTerapiPengobatan.setText("");
        TDiagUtama.setText("");
        TDiagSekunder.setText("");
        TTindakan.setText("");
        TKeadaanumum.setText("");
        TKesadaran.setText("");
        TTensi.setText("");
        TSuhu.setText("");
        TNadi.setText("");
        TFrekuensiNafas.setText("");
        Tgcs.setText("");
        cmbLanjutan.setSelectedIndex(0);
        TDokterLuar.setText("");
        cmbKondisiWP.setSelectedIndex(0);
        TCatatan.setText("");
        TTerapiPulang.setText("");
        chkTglKontrol.setSelected(false);
        TglKontrol.setDate(new Date());
        TglKontrol.setEnabled(false);
        Tedukasi.setText("");
        TKlgPasien.setText("");
        THasil.setText("");
        nmgedung = "";
        kddokter1.setText("");
        nmdokter1.setText("");
        noreg.setText(Sequel.cariIsi("select ifnull(id_tb_03,'') from nomor_reg_tb where no_rkm_medis='" + TNoRM.getText() + "'"));
        cmbAsesmen.setSelectedIndex(0);
        
        if (nmgedung.equals("AL-HAKIM/PARU")) {
            noreg.setEnabled(true);
        } else {
            noreg.setEnabled(false);
        }
    }

    private void getData() {
        cekTgl = "";
        nmgedung = "";
        
        if (tbRingkasan.getSelectedRow() != -1) {
            TNoRW.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 0).toString());
            TNoRM.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 1).toString());
            TNmPasien.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 2).toString());
            TTglLhr.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 3).toString());
            TJK.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 4).toString());
            TTglMsk.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 5).toString());
            TTglPulang.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 6).toString().replaceAll("00-00-0000", "-"));
            TRuangrawat.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 7).toString());
            TCaraBayar.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 9).toString());
            TNmDokter.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 8).toString());
            TAlasanDirawat.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 10).toString());
            TRingkasanRiwayat.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 11).toString());
            TPemeriksaanFisik.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 12).toString());
            TPemeriksaanPenunjang.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 13).toString());
            TTerapiPengobatan.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 14).toString());
            TDiagUtama.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 15).toString());
            TDiagSekunder.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 16).toString());
            TTindakan.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 17).toString());
            cmbKondisiWP.setSelectedItem(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 18).toString());
            TKeadaanumum.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 19).toString());
            TKesadaran.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 20).toString());
            TTensi.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 22).toString());
            TSuhu.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 23).toString());
            TNadi.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 24).toString());
            TFrekuensiNafas.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 25).toString());
            Tgcs.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 21).toString());
            cmbLanjutan.setSelectedItem(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 28).toString());
            TDokterLuar.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 29).toString());            
            TCatatan.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 26).toString());
            TTerapiPulang.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 27).toString());
            Tdpjp.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 31).toString());
            cekTgl = tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 32).toString();            
            Tedukasi.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 33).toString());
            TKlgPasien.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 34).toString());
            THasil.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 36).toString());
            nmgedung = Sequel.cariIsi("select b.nm_gedung from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRW.getText() + "' "
                    + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");         
            noreg.setText(Sequel.cariIsi("select ifnull(id_tb_03,'') from nomor_reg_tb where no_rkm_medis='" + TNoRM.getText() + "'"));
            
            if (nmgedung.equals("AL-HAKIM/PARU")) {
                noreg.setEnabled(true);
            } else {
                noreg.setEnabled(false);
            }
   
            if (cekTgl.equals("tidak")) {
                chkTglKontrol.setSelected(false);
                TglKontrol.setDate(new Date());
                TglKontrol.setEnabled(false);
            } else {
                chkTglKontrol.setSelected(true);
                Valid.SetTgl(TglKontrol, tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 30).toString());
                TglKontrol.setEnabled(true);
            }            
        }
    }
    
    public void setPasien(String norawat) {
        TNoRW.setText(norawat);
        TCari.setText(norawat);
        cekPasien();
        
        cekPilihanRehab = 0;
        cekPilihanRehab = Sequel.cariInteger("select count(-1) from data_rehab_medik where no_rawat='" + norawat + "'");
        if (cekPilihanRehab == 0) {
            label_rehab.setVisible(false);
            label_rehab.setText("");
        } else if (cekPilihanRehab > 0) {
            label_rehab.setVisible(true);
            label_rehab.setText("Jenis Rehabilitasi Medik : " + Sequel.cariIsi("select jns_rehabmedik from data_rehab_medik where no_rawat='" + norawat + "'"));
        }
    }
    
    public void cekPasien() {
        nmgedung = "";
        try {
            psPasien = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, "
                    + "IF(p.jk='L','Laki-laki','Perempuan') jk, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_msk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                    + "b.nm_bangsal, pj.png_jawab, ki.stts_pulang, ifnull(d.nm_dokter,'-') dpjp, b.nm_gedung, d1.nm_dokter dokter_ralan from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join kamar_inap ki on ki.no_rawat=rp.no_rawat "
                    + "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal inner join penjab pj ON pj.kd_pj = rp.kd_pj "
                    + "INNER JOIN dokter d1 on d1.kd_dokter=rp.kd_dokter left join dpjp_ranap dr on dr.no_rawat=ki.no_rawat left join dokter d on d.kd_dokter=dr.kd_dokter where "
                    + "rp.no_rawat like '%" + TNoRW.getText() + "%' and ki.stts_pulang<>'Pindah Kamar' order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");
            try {
                rsPasien = psPasien.executeQuery();              
                while (rsPasien.next()) {
                    TNoRM.setText(rsPasien.getString("no_rkm_medis"));
                    TNmPasien.setText(rsPasien.getString("nm_pasien"));
                    TTglLhr.setText(rsPasien.getString("tgl_lhr"));
                    TJK.setText(rsPasien.getString("jk"));
                    TTglMsk.setText(rsPasien.getString("tgl_msk"));
                    TTglPulang.setText(rsPasien.getString("tgl_pulang").replaceAll("00-00-0000", "-"));
                    TRuangrawat.setText(rsPasien.getString("nm_bangsal"));
                    TCaraBayar.setText(rsPasien.getString("png_jawab"));
                    cmbKondisiWP.setSelectedItem(rsPasien.getString("stts_pulang"));
                    Tdpjp.setText(rsPasien.getString("dpjp"));
                    nmgedung = rsPasien.getString("nm_gedung");
                    TNmDokter.setText(rsPasien.getString("dokter_ralan"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsPasien != null) {
                    rsPasien.close();
                }
                if (psPasien != null) {
                    psPasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getringkasanpulangranap());
        BtnHapus.setEnabled(akses.getringkasanpulangranap());
        BtnGanti.setEnabled(akses.getringkasanpulangranap());
        MnRiwayatData.setEnabled(akses.getadmin());
        MnBersihkanStringSampah.setEnabled(akses.getadmin());
    }
    
    private void cetakDataTriase() {
        totskorTriase = 0;
        try {
            psLaprm = koneksi.prepareStatement("select * from triase_igd where no_rawat='" + TNoRW.getText() + "'");
            try {
                rsLaprm = psLaprm.executeQuery();
                while (rsLaprm.next()) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));

                    if (rsLaprm.getString("alasan_kedatangan").equals("")) {
                        param.put("alasan_kedatangan", "-");
                    } else if (rsLaprm.getString("alasan_kedatangan").equals("Datang Sendiri") || rsLaprm.getString("alasan_kedatangan").equals("Polisi")) {
                        param.put("alasan_kedatangan", rsLaprm.getString("alasan_kedatangan"));
                    } else if (rsLaprm.getString("alasan_kedatangan").equals("Rujukan, dari")) {
                        param.put("alasan_kedatangan", "Rujukan, dari : " + rsLaprm.getString("rujukan_dari"));
                    } else if (rsLaprm.getString("alasan_kedatangan").equals("Dijemput oleh")) {
                        param.put("alasan_kedatangan", "Dijemput oleh : " + rsLaprm.getString("dijemput_oleh"));
                    }

                    if (rsLaprm.getString("kendaraan").equals("")) {
                        param.put("kendaraan", "-");
                    } else if (rsLaprm.getString("kendaraan").equals("Ambulance")) {
                        param.put("kendaraan", rsLaprm.getString("kendaraan"));
                    } else if (rsLaprm.getString("kendaraan").equals("Kendaraan bukan ambulance")) {
                        param.put("kendaraan", "Kendaraan bukan ambulance, jelaskan : " + rsLaprm.getString("bukan_ambulan"));
                    }

                    if (rsLaprm.getString("kll_tunggal").equals("ya")) {
                        param.put("kll_tunggal", "KLL Tunggal Tempat Kejadian " + rsLaprm.getString("kll_tunggal_tmpt_kejadian") + " Tanggal Kejadian "
                                + Sequel.cariIsi("select date_format(kll_tunggal_tanggal,'%d-%m-%Y    Pukul : %H:%i') from triase_igd "
                                        + "where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("kll_tunggal", "KLL Tunggal");
                    }

                    if (rsLaprm.getString("kll_versus").equals("ya")) {
                        param.put("kll", "KLL " + rsLaprm.getString("versus1") + " Vs. " + rsLaprm.getString("versus2") + " Tempat Kejadian "
                                + rsLaprm.getString("kll_tmpt_kejadian") + " Tanggal Kejadian " + Sequel.cariIsi("select date_format(kll_tanggal,'%d-%m-%Y    Pukul : %H:%i') from triase_igd "
                                + "where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("kll", "KLL");
                    }

                    if (rsLaprm.getString("jatuh").equals("ya")) {
                        param.put("jatuh", "Jatuh dari ketinggian, Jelaskan : " + rsLaprm.getString("ket_jatuh"));
                    } else {
                        param.put("jatuh", "Jatuh dari ketinggian,");
                    }

                    if (rsLaprm.getString("luka_bakar").equals("ya")) {
                        param.put("luka", "Luka bakar, Jelaskan : " + rsLaprm.getString("ket_luka_bakar"));
                    } else {
                        param.put("luka", "Luka bakar,");
                    }

                    if (rsLaprm.getString("trauma_listrik").equals("ya")) {
                        param.put("trauma_listrik", "Trauma listrik, Jelaskan : " + rsLaprm.getString("ket_trauma_listrik"));
                    } else {
                        param.put("trauma_listrik", "Trauma listrik,");
                    }

                    if (rsLaprm.getString("trauma_zat_kimia").equals("ya")) {
                        param.put("trauma_zat", "Trauma zat kimia, Jelaskan : " + rsLaprm.getString("ket_trauma_zat_kimia"));
                    } else {
                        param.put("trauma_zat", "Trauma zat kimia,");
                    }

                    if (rsLaprm.getString("trauma_lain").equals("ya")) {
                        param.put("trauma_lain", "Trauma lainnya (" + rsLaprm.getString("ket_trauma_lain") + ")");
                    } else {
                        param.put("trauma_lain", "Trauma lainnya");
                    }

                    if (rsLaprm.getString("pacs1").equals("ya")) {
                        param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 1");
                    } else if (rsLaprm.getString("pacs2").equals("ya")) {
                        param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 2");
                    } else if (rsLaprm.getString("pacs3").equals("ya")) {
                        param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 3");
                    } else if (rsLaprm.getString("pacs4").equals("ya")) {
                        param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 4");
                    } else {
                        param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : -");
                    }

                    totskorTriase = Integer.parseInt(rsLaprm.getString("total_skor"));
                    if (totskorTriase >= 5) {
                        param.put("total5", "V");
                        param.put("total24", "");
                        param.put("total01", "");
                    } else if (totskorTriase >= 2 && totskorTriase <= 4) {
                        param.put("total5", "");
                        param.put("total24", "V");
                        param.put("total01", "");
                    } else if (totskorTriase >= 0 && totskorTriase <= 1) {
                        param.put("total5", "");
                        param.put("total24", "");
                        param.put("total01", "V");
                    } else {
                        param.put("total5", "");
                        param.put("total24", "");
                        param.put("total01", "");
                    }

                    Valid.MyReport("rptTriaseIGD.jasper", "report", "::[ Laporan Data Triase IGD ]::",
                            "SELECT ti.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, date_format(ti.tanggal,'Tanggal : %d-%m-%Y    Pukul : %H:%i') kontak_awal, "
                            + "if(ti.cara_masuk='','-',ti.cara_masuk) cr_msk, ti.sudah_terpasang, concat('Nama : ',ti.nm_pengantar,'    No. Telp : ',ti.telp_pengantar) iden_pengntar, "
                            + "ti.kasus, ti.keluhan_utama, if(ti.kesadaran='','KESADARAN : -',concat('KESADARAN : ',ti.kesadaran)) kesadaran, ti.td, ti.nadi, ti.napas, ti.temperatur, "
                            + "ti.saturasi, ti.nyeri, ti.vas, if(ti.skor0_sadar_penuh='ya','V','') skor0_sadar, if(ti.skor0_100='ya','V','') skor0_100, if(ti.skor0_101='ya','V','') skor0_101, "
                            + "if(ti.skor0_19='ya','V','') skor0_19, if(ti.skor0_35_3='ya','V','') skor0_35, if(ti.skor0_96_100='ya','V','') skor0_96, if(ti.skor1_102='ya','V','') skor1_102, "
                            + "if(ti.skor1_20_21='ya','V','') skor1_20, if(ti.skor1_94_95='ya','V','') skor1_94, if(ti.skor2_99='ya','V','') skor2_99, if(ti.skor2_22='ya','V','') skor2_22, "
                            + "if(ti.skor2_92_93='ya','V','') skor2_92, if(ti.skor3_selain='ya','V','') skor3_selain, if(ti.skor3_35_3='ya','V','') skor3_35, if(ti.skor3_92='ya','V','') skor3_92, "
                            + "ti.catatan, ti.pukul, if(ti.triase_resusitasi='ya','V','') resus, if(ti.triase_non_resusitasi='ya','V','') nonresus, if(ti.triase_klinik='ya','V','') klinik, "
                            + "if(ti.triase_doa='ya','V','') doa, pg.nama petgas, if(ti.kll_tunggal='ya','V','') kll_tunggal, if(ti.kll_versus='ya','V','') kll_versus, if(ti.jatuh='ya','V','') jatuh, "
                            + "if(ti.luka_bakar='ya','V','') luka, if(ti.trauma_listrik='ya','V','') trauma_listrik, if(ti.trauma_zat_kimia='ya','V','') trauma_zat, if(ti.trauma_lain='ya','V','') trauma_lain, "
                            + "ti.bb, ti.tb from triase_igd ti inner join reg_periksa rp on rp.no_rawat=ti.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "INNER JOIN pegawai pg on nik=ti.nip_petugas where ti.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);
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
    }
    
    private void cetakAsesMedikIGD() {
        skorAsesIGD = "";
        try {
            psLaprm = koneksi.prepareStatement("select *, date_format(tgl_keluar_igd,'%H:%i:%s') jamklr from penilaian_awal_medis_igd where no_rawat='" + TNoRW.getText() + "'");
            try {
                rsLaprm = psLaprm.executeQuery();
                while (rsLaprm.next()) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));

                    //hitung skor
                    int A, B, C, D, E, hasil;
                    A = Integer.parseInt(rsLaprm.getString("frekuensi_nafas"));
                    B = Integer.parseInt(rsLaprm.getString("retraksi"));
                    C = Integer.parseInt(rsLaprm.getString("sianosis"));
                    D = Integer.parseInt(rsLaprm.getString("air_entry"));
                    E = Integer.parseInt(rsLaprm.getString("merintih"));

                    hasil = 0;
                    hasil = A + B + C + D + E;
                    skorAsesIGD = Valid.SetAngka2(hasil);
                    param.put("jlhSkor", skorAsesIGD);

                    if (rsLaprm.getString("meninggal").equals("ya")) {
                        param.put("jam_meninggal", rsLaprm.getString("jam_meninggal"));
                    } else {
                        param.put("jam_meninggal", "-");
                    }

                    if (rsLaprm.getString("cek_jam_keluar").equals("ya")) {
                        param.put("jam_keluar", rsLaprm.getString("jamklr"));
                    } else {
                        param.put("jam_keluar", "-");
                    }

                    Valid.MyReport("rptCetakPenilaianAwalMedisIGD.jasper", "report", "::[ Laporan Penilaian Awal Medis IGD hal. 1 ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                            + "date_format(pa.tanggal,'Tanggal : %d-%m-%Y    Pukul : %H:%i') mulai_penanganan, if(pa.cervival='ya','V','') cer, "
                            + "if(pa.rjp='ya','V','') rjp, if(pa.defribilasi='ya','V','') def, if(pa.intubasi='ya','V','') intu, if(pa.vtp='ya','V','') vtp, if(pa.dekompresi='ya','V','') dek, "
                            + "if(pa.balut='ya','V','') bal, if(pa.kateter='ya','V','') kat, if(pa.ngt='ya','V','') ngt, if(pa.infus='ya','V','') infs, if(pa.obat='ya','V','') obt, pa.ket_obat, "
                            + "if(pa.tidak_ada='ya','V','') tdk, if(pa.gangguan_jalan_nafas='ya','V','') ggnfs, if(pa.paten='ya','V','') pat, if(pa.obstruksi_partial='ya','V','') obsp, if(pa.data_obstruksi_partial='','-',pa.data_obstruksi_partial) data_obsp, "
                            + "if(pa.obstruksi_total='ya','V','') obst, if(pa.trauma_jalan_nafas='ya','V','') trauma_jln_nfs, pa.trauma, if(pa.resiko_aspirasi='ya','V','') res, pa.aspirasi, "
                            + "if(pa.benda_asing='ya','V','') ben, pa.ket_benda_asing, pa.kesimpulan_jalan_nafas, pa.pernafasan, pa.data_pernafasan, pa.gerakan_dada, pa.tipe_pernafasan, "
                            + "pa.kesimpulan_pernafasan, pa.nadi_1, pa.kulit_mukosa, pa.akral_1, pa.crt, pa.kesimpulan_sirkulasi, pa.cukup_bulan, pa.cairan_amnion, pa.pernafasan_menangis, pa.tonus, "
                            + "pa.skor_apgar, pa.gcs_e, pa.pupil, pa.diameter_kanan, pa.reflek_cahaya, pa.meningeal_sign, pa.literasi, if(pa.deformitas='ya','V','') defo, if(pa.contusio='ya','V','') con, "
                            + "if(pa.penetrasi='ya','V','') pen, if(pa.tenderness='ya','V','') ten, if(pa.swelling='ya','V','') swe, if(pa.ekskoriasi='ya','V','') eks, if(pa.abrasi='ya','V','') abr, "
                            + "if(pa.burn='ya','V','') bur, if(pa.laserasi='ya','V','') las, if(pa.tidak_tampak_jelas='ya','V','') tdk_tmpk, pa.frekuensi_nafas, pa.retraksi, pa.sianosis, pa.air_entry, "
                            + "pa.merintih, pa.Alergi, if(pa.hipertensi='ya','V','') hip, if(pa.diabetes='ya','V','') dm, if(pa.jantung='ya','V','') jan, pa.riwayat_penyakit_lain, "
                            + "if(pa.merokok='ya','V','') mer, pa.kebiasaan_lain, pa.anamnesis, pa.pemeriksaan_fisik, pa.konjungtiva, pa.sklera, pa.bibir_lidah, pa.mukosa, pa.deviasi, pa.jvp, "
                            + "pa.lnn, pa.tiroid, pa.survei_jantung, pa.survei_paru, pa.survei_abdomen, pa.survei_punggung, pa.survei_ekstremitas, pa.laboratorium, pa.x_ray, "
                            + "pa.ecg, pa.ct_scan, pa.usg, pa.lainnya_penunjang, pa.diag_medis_sementara, pa.icd_10, pa.rencana_instruksi, pa.ket_rencana_instruksi, pa.telah_diberikan_informasi_edukasi, "
                            + "pa.rencana_asuhan_diharapkan, pg1.nama pemberi_edukasi, pa.penerima_edukasi, pg2.nama nm_dokter, date_format(pa.tgl_keluar_igd,'%d-%m-%Y') tglkeluar, "
                            + "date_format(pa.tgl_keluar_igd,'%H:%i') jamkeluar, pa.opname_diruang, pa.indikasi_msk, pa.dipulangkan_kontrol_ke, pa.dirujuk_ke, pa.alasan_dirujuk, pa.penyebab, "
                            + "pa.k_u, pa.td, pa.hr, pa.rr, pa.temp, pa.spo2, pa.gcs_pulang, pg3.nama nm_perawat, pg4.nama nm_dpjp, pa.gcs_v, pa.gcs_m, pa.diameter_kiri, pa.nadi_2, pa.akral_2 "
                            + "from penilaian_awal_medis_igd pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                            + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis left join pegawai pg1 on pg1.nik=pa.nip_pemberi_edukasi "
                            + "left join pegawai pg2 on pg2.nik=pa.nip_dokter left join pegawai pg3 on pg3.nik=pa.nip_perawat "
                            + "left join pegawai pg4 on pg4.nik=pa.nip_dpjp where pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);

                    Valid.MyReport("rptCetakPenilaianAwalMedisIGD1.jasper", "report", "::[ Laporan Penilaian Awal Medis IGD hal. 2 ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                            + "date_format(pa.tanggal,'Tanggal : %d-%m-%Y    Pukul : %H:%i') mulai_penanganan, if(pa.cervival='ya','V','') cer, "
                            + "if(pa.rjp='ya','V','') rjp, if(pa.defribilasi='ya','V','') def, if(pa.intubasi='ya','V','') intu, if(pa.vtp='ya','V','') vtp, if(pa.dekompresi='ya','V','') dek, "
                            + "if(pa.balut='ya','V','') bal, if(pa.kateter='ya','V','') kat, if(pa.ngt='ya','V','') ngt, if(pa.infus='ya','V','') infs, if(pa.obat='ya','V','') obt, pa.ket_obat, "
                            + "if(pa.tidak_ada='ya','V','') tdk, if(pa.gangguan_jalan_nafas='ya','V','') ggnfs, if(pa.paten='ya','V','') pat, if(pa.obstruksi_partial='ya','V','') obsp, if(pa.data_obstruksi_partial='','-',pa.data_obstruksi_partial) data_obsp, "
                            + "if(pa.obstruksi_total='ya','V','') obst, if(pa.trauma_jalan_nafas='ya','V','') trauma_jln_nfs, pa.trauma, if(pa.resiko_aspirasi='ya','V','') res, pa.aspirasi, "
                            + "if(pa.benda_asing='ya','V','') ben, pa.ket_benda_asing, pa.kesimpulan_jalan_nafas, pa.pernafasan, pa.data_pernafasan, pa.gerakan_dada, pa.tipe_pernafasan, "
                            + "pa.kesimpulan_pernafasan, pa.nadi_1, pa.kulit_mukosa, pa.akral_1, pa.crt, pa.kesimpulan_sirkulasi, pa.cukup_bulan, pa.cairan_amnion, pa.pernafasan_menangis, pa.tonus, "
                            + "pa.skor_apgar, pa.gcs_e, pa.pupil, pa.diameter_kanan, pa.reflek_cahaya, pa.meningeal_sign, pa.literasi, if(pa.deformitas='ya','V','') defo, if(pa.contusio='ya','V','') con, "
                            + "if(pa.penetrasi='ya','V','') pen, if(pa.tenderness='ya','V','') ten, if(pa.swelling='ya','V','') swe, if(pa.ekskoriasi='ya','V','') eks, if(pa.abrasi='ya','V','') abr, "
                            + "if(pa.burn='ya','V','') bur, if(pa.laserasi='ya','V','') las, if(pa.tidak_tampak_jelas='ya','V','') tdk_tmpk, pa.frekuensi_nafas, pa.retraksi, pa.sianosis, pa.air_entry, "
                            + "pa.merintih, pa.Alergi, if(pa.hipertensi='ya','V','') hip, if(pa.diabetes='ya','V','') dm, if(pa.jantung='ya','V','') jan, pa.riwayat_penyakit_lain, "
                            + "if(pa.merokok='ya','V','') mer, pa.kebiasaan_lain, pa.anamnesis, pa.pemeriksaan_fisik, pa.konjungtiva, pa.sklera, pa.bibir_lidah, pa.mukosa, pa.deviasi, pa.jvp, "
                            + "pa.lnn, pa.tiroid, pa.survei_jantung, pa.survei_paru, pa.survei_abdomen, pa.survei_punggung, pa.survei_ekstremitas, pa.laboratorium, pa.x_ray, "
                            + "pa.ecg, pa.ct_scan, pa.usg, pa.lainnya_penunjang, pa.diag_medis_sementara, pa.icd_10, pa.rencana_instruksi, pa.ket_rencana_instruksi, pa.telah_diberikan_informasi_edukasi, "
                            + "pa.rencana_asuhan_diharapkan, ifnull(pg1.nama,'-') pemberi_edukasi, pa.penerima_edukasi, ifnull(pg2.nama,'-') nm_dokter, date_format(pa.tgl_keluar_igd,'%d-%m-%Y') tglkeluar, "
                            + "date_format(pa.tgl_keluar_igd,'%H:%i') jamkeluar, pa.opname_diruang, pa.indikasi_msk, pa.dipulangkan_kontrol_ke, pa.dirujuk_ke, pa.alasan_dirujuk, pa.penyebab, "
                            + "pa.k_u, pa.td, pa.hr, pa.rr, pa.temp, pa.spo2, pa.gcs_pulang, pg3.nama nm_perawat, pg4.nama nm_dpjp, ifnull(pa.ket_gambar,'-') ket_gambar, pa.gcs_v, pa.gcs_m, pa.diameter_kiri, "
                            + "SUBSTRING_INDEX( pa.ket_rencana_instruksi, '\n', 5 ) renc1, SUBSTRING_INDEX( pa.ket_rencana_instruksi, SUBSTRING_INDEX( pa.ket_rencana_instruksi, '\n', 5 ),- 1 ) renc2 "
                            + "from penilaian_awal_medis_igd pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                            + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis left join pegawai pg1 on pg1.nik=pa.nip_pemberi_edukasi "
                            + "left join pegawai pg2 on pg2.nik=pa.nip_dokter left join pegawai pg3 on pg3.nik=pa.nip_perawat "
                            + "left join pegawai pg4 on pg4.nik=pa.nip_dpjp where pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);
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
    }
    
    private void cetakAsesKepIGD() {
        try {
            psLaprm = koneksi.prepareStatement("select * from penilaian_awal_keperawatan_igdrz where no_rawat='" + TNoRW.getText() + "'");
            try {
                rsLaprm = psLaprm.executeQuery();
                while (rsLaprm.next()) {
                    if (rsLaprm.getString("gizi_dewasa1").equals("Tidak")) {
                        skorGZ1 = 0;
                    } else if (rsLaprm.getString("gizi_dewasa1").equals("Tidak Tahu/tidak yakin (ada tanda : baju menjadi longgar)")) {
                        skorGZ1 = 1;
                    } else if (rsLaprm.getString("gizi_dewasa1").equals("Ya, ada penurunan BB sebanyak")) {
                        skorGZ1 = 0;
                    }

                    if (rsLaprm.getString("gizi_dewasa1ya").equals("-")) {
                        skorYaGZ1 = 0;
                    } else if (rsLaprm.getString("gizi_dewasa1ya").equals("1 - 5 Kg")) {
                        skorYaGZ1 = 1;
                    } else if (rsLaprm.getString("gizi_dewasa1ya").equals("6 - 10 Kg")) {
                        skorYaGZ1 = 2;
                    } else if (rsLaprm.getString("gizi_dewasa1ya").equals("11 - 15 Kg")) {
                        skorYaGZ1 = 3;
                    } else if (rsLaprm.getString("gizi_dewasa1ya").equals("15 Kg")) {
                        skorYaGZ1 = 4;
                    } else if (rsLaprm.getString("gizi_dewasa1ya").equals("Tidak tahu berapa Kg penurunanya")) {
                        skorYaGZ1 = 2;
                    }

                    if (rsLaprm.getString("gizi_dewasa2").equals("Tidak")) {
                        skorGZ2 = 0;
                    } else if (rsLaprm.getString("gizi_dewasa2").equals("Ya")) {
                        skorGZ2 = 1;
                    }

                    //hitung skor skrining
                    int A1, B1, C1, TotD, A2, B2, C2, D2, TotA;
                    A1 = skorGZ1;
                    B1 = skorYaGZ1;
                    C1 = skorGZ2;

                    A2 = Integer.parseInt(rsLaprm.getString("gizi_anak1"));
                    B2 = Integer.parseInt(rsLaprm.getString("gizi_anak2"));
                    C2 = Integer.parseInt(rsLaprm.getString("gizi_anak3"));
                    D2 = Integer.parseInt(rsLaprm.getString("gizi_anak_penyakit"));

                    TotD = 0;
                    TotA = 0;

                    TotD = A1 + B1 + C1;
                    TotA = A2 + B2 + C2 + D2;
                    TotSkorGZD = Valid.SetAngka2(TotD);
                    TotSkorGZA = Valid.SetAngka2(TotA);

                    if (rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                        kesimpulanGZanak = "";
                        if (TotD == 0 || TotD == 1) {
                            kesimpulanGZDewasa = "0 - 1 : tidak beresiko malnutrisi";
                        } else if (TotD >= 2) {
                            kesimpulanGZDewasa = ">= 2 : beresiko malnutrisi, perlu pemantauan lanjutan oleh Tim Gizi/Dietisien";
                        }
                    }

                    if (rsLaprm.getString("skrining_gizi").equals("anak")) {
                        kesimpulanGZDewasa = "";
                        if (TotA == 0) {
                            kesimpulanGZanak = "0 : tidak beresiko malnutrisi";
                        } else if (TotA >= 1 && TotA <= 3) {
                            kesimpulanGZanak = "1 - 3 : resiko malnutrisi sedang, perlu pemantauan";
                        } else if (TotA >= 4) {
                            kesimpulanGZanak = ">= 4 : resiko malnutrisi berat, perlu pemantauan lanjutan oleh Tim Gizi/Dietisien";
                        }
                    }

                    //tindakan pencegahan resiko jatuh
                    if (rsLaprm.getString("cegah_resiko_jatuh").equals("Dewasa")) {
                        TabTindakanPencegahan.setSelectedIndex(0);
                        if (rsLaprm.getString("tindakan_pencegahan").equals("A")) {
                            TabPencegahanDewasa.setSelectedIndex(0);
                        } else if (rsLaprm.getString("tindakan_pencegahan").equals("B")) {
                            TabPencegahanDewasa.setSelectedIndex(1);
                        } else if (rsLaprm.getString("tindakan_pencegahan").equals("C")) {
                            TabPencegahanDewasa.setSelectedIndex(2);
                        } else {
                            TabPencegahanDewasa.setSelectedIndex(0);
                        }
                    } else if (rsLaprm.getString("cegah_resiko_jatuh").equals("Anak")) {
                        TabTindakanPencegahan.setSelectedIndex(1);
                        if (rsLaprm.getString("tindakan_pencegahan").equals("A")) {
                            TabPencegahanAnak.setSelectedIndex(0);
                        } else if (rsLaprm.getString("tindakan_pencegahan").equals("B")) {
                            TabPencegahanAnak.setSelectedIndex(1);
                        } else {
                            TabPencegahanAnak.setSelectedIndex(0);
                        }
                    } else {
                        TabTindakanPencegahan.setSelectedIndex(0);
                        TabPencegahanDewasa.setSelectedIndex(0);
                    }

                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));

                    if (rsLaprm.getString("skrining_gizi").equals("anak")) {
                        param.put("jenisSkrining", "ANAK (berdasarkan modifikasi form STRONG Kids)");
                        param.put("kalimatSkrining", "1. Terdapat penurunan BB atau BB menetap (pada bayi < 1 tahun) selama >= 2 bulan                 Skor (" + rsLaprm.getString("gizi_anak1") + ")\n\n"
                                + "2. Terdapat tanda-tanda klinis gangguan gizi (tampak kurus, gemuk, pendek, edema, moon face,   Skor (" + rsLaprm.getString("gizi_anak2") + ")\n"
                                + "tampak tua, iga gambang, baggy pant, anoreksia) selama 1 bulan terakhir\n\n"
                                + "3. Terdapat salah satu penyakit/kondisi yg. beresiko mengakibatkan malnutrisi berikut :                 Skor (" + rsLaprm.getString("gizi_anak3") + ")\n"
                                + "* Diare berat (> 5x/hari) dan atau muntah (> 3x/hari)\n"
                                + "* Penurunan asupan makanan selama lebih dari 7 hari\n\n"
                                + "Terdapat penyakit-penyakit / keadaan yg. meningkatkan resiko malnutrisi antara lain :                   Skor (" + rsLaprm.getString("gizi_anak_penyakit") + ")\n"
                                + "* Diare kronik > 2 minggu                          * Penyakit hati/ginjal kronik\n"
                                + "* Penyakit jantung bawaan (tersangka)        * TB Paru\n"
                                + "* Infeksi HIV (tersangka)                            * Renca/paska operasimayor\n"
                                + "* Kelainan anatomi bawaan                         * Luka bakar luas\n"
                                + "* Kelainan metabolisme bawaan                  * Terpasang stoma\n"
                                + "* Retardasi mental                                     * Trauma\n"
                                + "* Keterlambatan perkembangan                  * Lain-lain : " + rsLaprm.getString("gizi_anak_penyakit_lain") + "\n"
                                + "* Kanker (tersangka)\n"
                                + "_______________________________________________________________________\n"
                                + "Total Skor : (" + TotSkorGZA + ")\n"
                                + "Kesimpulan Skrining Gizi Anak :\n"
                                + kesimpulanGZanak + "\n");
                        param.put("resikoJatuh", "Anak (Skala Humpty Dumpty)");
                        param.put("tindakanRJ", "ANAK");
                        if (TabPencegahanAnak.getSelectedIndex() == 0) {
                            param.put("JudultindakanRJ", "Pencegahan Umum (A)");
                            param.put("IsitindakanRJ", anakA.getText());
                        } else if (TabPencegahanAnak.getSelectedIndex() == 1) {
                            param.put("JudultindakanRJ", "Pencegahan Resiko Tinggi (B)");
                            param.put("IsitindakanRJ", anakB.getText());
                        }

                    } else if (rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                        param.put("jenisSkrining", "DEWASA (Modifikasi MST)");
                        param.put("kalimatSkrining", "1. Apakah pasien mengalami penurunan BB yang tidak direncanakan/tidak diinginkan dalam 6 bulan terakhir ?\n"
                                + rsLaprm.getString("gizi_dewasa1") + "   Skor (" + skorGZ1 + ")\n"
                                + rsLaprm.getString("gizi_dewasa1ya") + "   Skor (" + skorYaGZ1 + ")\n\n"
                                + "2. Apakah asupan makan pasien berkurang karena penurunan nafsu makan / kesulitan menerima makanan ?\n"
                                + rsLaprm.getString("gizi_dewasa2") + "   Skor (" + skorGZ2 + ")\n"
                                + "_______________________________________________________________________\n"
                                + "Total Skor : (" + TotSkorGZD + ")\n"
                                + "Kesimpulan Skrining Gizi Dewasa :\n"
                                + kesimpulanGZDewasa + "\n");
                        param.put("resikoJatuh", "Dewasa (Skala Morse)");
                        param.put("tindakanRJ", "DEWASA");
                        if (TabPencegahanDewasa.getSelectedIndex() == 0) {
                            param.put("JudultindakanRJ", "Pencegahan Umum (A)");
                            param.put("IsitindakanRJ", dewasaA.getText());
                        } else if (TabPencegahanDewasa.getSelectedIndex() == 1) {
                            param.put("JudultindakanRJ", "Pencegahan Resiko Sedang (B)");
                            param.put("IsitindakanRJ", dewasaB.getText());
                        } else if (TabPencegahanDewasa.getSelectedIndex() == 2) {
                            param.put("JudultindakanRJ", "Pencegahan Resiko Tinggi (C)");
                            param.put("IsitindakanRJ", dewasaC.getText());
                        }

                    } else if (!rsLaprm.getString("skrining_gizi").equals("anak") && !rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                        param.put("jenisSkrining", "");
                        param.put("kalimatSkrining", "");
                        param.put("resikoJatuh", "");
                        param.put("tindakanRJ", "");
                        param.put("JudultindakanRJ", "");
                        param.put("IsitindakanRJ", "");
                    }

                    //data faktor resiko
                    try {
                        faktorresikoigd = "";
                        psFakIGD = koneksi.prepareStatement("select m.kode_resiko, concat('Faktor : ',m.faktor_resiko,', Skala : ',m.skala,', Skor (',m.skor,')') resiko "
                                + "FROM master_faktor_resiko_igd m INNER JOIN penilaian_awal_keperawatan_igd_resiko pm ON pm.kode_resiko = m.kode_resiko "
                                + "WHERE pm.no_rawat=? ORDER BY pm.kode_resiko");
                        try {
                            psFakIGD.setString(1, rsLaprm.getString("no_rawat"));
                            rsFakIGD = psFakIGD.executeQuery();
                            while (rsFakIGD.next()) {
                                faktorresikoigd = rsFakIGD.getString("resiko") + "\n" + faktorresikoigd;
                            }

                            if (faktorresikoigd.endsWith("\n")) {
                                faktorresikoigd = faktorresikoigd.substring(0, faktorresikoigd.length() - 1);
                            }

                        } catch (Exception e) {
                            System.out.println("Notif : " + e);
                        } finally {
                            if (rsFakIGD != null) {
                                rsFakIGD.close();
                            }
                            if (psFakIGD != null) {
                                psFakIGD.close();
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                    }

                    //cek faktor resiko                    
                    try {
                        Valid.tabelKosong(tabModeResiko);
                        if (rsLaprm.getString("skrining_gizi").equals("anak")) {
                            psRes = koneksi.prepareStatement("select m.kode_resiko, m.faktor_resiko, m.skala, m.skor, m.asesmen FROM master_faktor_resiko_igd m "
                                    + "INNER JOIN penilaian_awal_keperawatan_igd_resiko pa ON pa.kode_resiko = m.kode_resiko "
                                    + "WHERE m.asesmen = 'anak' and pa.no_rawat=? ORDER BY pa.kode_resiko");
                        } else if (rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                            psRes = koneksi.prepareStatement("select m.kode_resiko, m.faktor_resiko, m.skala, m.skor, m.asesmen FROM master_faktor_resiko_igd m "
                                    + "INNER JOIN penilaian_awal_keperawatan_igd_resiko pa ON pa.kode_resiko = m.kode_resiko "
                                    + "WHERE m.asesmen = 'dewasa' and pa.no_rawat=? ORDER BY pa.kode_resiko");
                        } else {
                            psRes = koneksi.prepareStatement("select m.kode_resiko, m.faktor_resiko, m.skala, m.skor, m.asesmen FROM master_faktor_resiko_igd m "
                                    + "INNER JOIN penilaian_awal_keperawatan_igd_resiko pa ON pa.kode_resiko = m.kode_resiko "
                                    + "WHERE pa.no_rawat=? ORDER BY pa.kode_resiko");
                        }
                        try {
                            psRes.setString(1, rsLaprm.getString("no_rawat"));
                            rsRes = psRes.executeQuery();
                            while (rsRes.next()) {
                                tabModeResiko.addRow(new Object[]{
                                    true,
                                    rsRes.getString("kode_resiko"),
                                    rsRes.getString("asesmen"),
                                    rsRes.getString("faktor_resiko"),
                                    rsRes.getString("skala"),
                                    rsRes.getString("skor")
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : " + e);
                        } finally {
                            if (rsRes != null) {
                                rsRes.close();
                            }
                            if (psRes != null) {
                                psRes.close();
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                    }

                    //hitung skor faktor resiko
                    skor = 0;
                    for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
                        if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                            skor = skor + Integer.parseInt(tbFaktorResiko.getValueAt(i, 5).toString());
                        }
                    }

                    TotSkorRJ = Valid.SetAngka2(skor);
                    if (!rsLaprm.getString("skrining_gizi").equals("dewasa") && !rsLaprm.getString("skrining_gizi").equals("anak")) {
                        TotSkorRJ = "0";
                        kesimpulanResikoJatuh = "";
                    }

                    //asesmen dewasa
                    if (rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                        if (skor >= 45) {
                            kesimpulanResikoJatuh = "Resiko Tinggi : >= 45, pasang kancing berwarna kuning";
                        } else if (skor >= 25 && skor <= 44) {
                            kesimpulanResikoJatuh = "Resiko Sedang : 25-44, pasang kancing berwarna kuning";
                        } else if (skor >= 0 && skor <= 24) {
                            kesimpulanResikoJatuh = "Resiko Rendah : 0-24";
                        }
                    }

                    //asesmen anak
                    if (rsLaprm.getString("skrining_gizi").equals("anak")) {
                        if (skor >= 12) {
                            kesimpulanResikoJatuh = "Resiko Tinggi : >= 12, pasang kancing penanda berwarna kuning";
                        } else if (skor >= 7 && skor <= 11) {
                            kesimpulanResikoJatuh = "Resiko Rendah : 7-11";
                        } else if (skor >= 0 && skor <= 6) {
                            kesimpulanResikoJatuh = "";
                        }
                    }

                    if (!rsLaprm.getString("skrining_gizi").equals("anak") && !rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                        param.put("dataResiko", "");
                        param.put("TotSkorResikoJatuh", "");
                        param.put("KesResikoJatuh", "");
                    } else {
                        param.put("dataResiko", faktorresikoigd);
                        param.put("TotSkorResikoJatuh", "Total Skor : (" + TotSkorRJ + ")");
                        param.put("KesResikoJatuh", "Kesimpulan Skor Resiko Jatuh :\n" + kesimpulanResikoJatuh);
                    }

                    Valid.MyReport("rptCetakPenilaianAwalKeperawatanIGD1.jasper", "report", "::[ Laporan Penilaian Awal Keperawatan IGD hal. 2 ]::",
                            "SELECT if(pa.nyeri='Ya',concat(pa.nyeri,', Lokasi : ',pa.ya_nyeri_lokasi),pa.nyeri) nyeri, pa.jenis_nyeri, "
                            + "if(pa.provocation='Lainnya',concat(pa.provocation,', ',pa.provocation_lain),pa.provocation) provokes, if(pa.quality='Lainnya',concat(pa.quality,', ',pa.quality_lain),pa.quality) quality, "
                            + "pa.radiation radiasi, pa.severity severity_nyeri, concat(pa.time,', Lama : ',pa.time_lama) time_nyeri, pa.skala_nyeri, pa.diagnosa_keperawatan, pa.tindakan_keperawatan, pa.evaluasi_keperawatan, "
                            + "if(pa.identifikasi1='ya','V','') iden1, if(pa.identifikasi2='ya','V','') iden2, if(pa.identifikasi3='ya','V','') iden3, if(pa.identifikasi4='ya','V','') iden4, if(pa.identifikasi5='ya','V','') iden5, "
                            + "if(pa.identifikasi6='ya','V','') iden6, if(pa.identifikasi7='ya','V','') iden7, if(pa.identifikasi8='ya','V','') iden8, if(pa.identifikasi9='ya','V','') iden9, if(pa.identifikasi10='ya','V','') iden10, "
                            + "if(pa.manajer_pelayanan='ya','V','') mpp, if(pa.discharge_planing='ya','V','') dp, concat('Tanggal, ',date_format(pa.tgl_verifikasi,'%d-%m-%Y'),'   Jam ',TIME_FORMAT(pa.tgl_verifikasi,'%H:%i:%S')) tglverif, "
                            + "pg1.nama dr_dpjp, pg2.nama perawat from penilaian_awal_keperawatan_igdrz pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "inner join pegawai pg1 on pg1.nik=pa.nip_dpjp inner join pegawai pg2 on pg2.nik=pa.nip_perawat where "
                            + "pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);

                    Valid.MyReport("rptCetakPenilaianAwalKeperawatanIGD.jasper", "report", "::[ Laporan Penilaian Awal Keperawatan IGD hal. 1 ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, concat('Tanggal : ', date_format(pa.tanggal,'%d-%m-%Y'),', Pukul : ',time_format(pa.tanggal,'%H:%i-%S')) tgl, "
                            + "pa.keluhan_utama, pa.td tensi, pa.nadi, pa.nafas, pa.suhu, pa.bb, pa.tb, pa.asesmen_psikologis psikologis, if(pa.masalah_perilaku='Ada',concat(pa.masalah_perilaku,', Sebutkan : ',pa.sebutkan_perilaku),pa.masalah_perilaku) perilaku, "
                            + "p.stts_nikah, pa.hubungan_pasien hubungan, if(pa.tempat_tinggal='Lainnya',concat(pa.tempat_tinggal,', ',pa.lainya_tempt_tgl),pa.tempat_tinggal) tmpttgl, p.pekerjaan, "
                            + "pa.alat_bantu, if(pa.cacat_tubuh='Ada',concat(pa.cacat_tubuh,', ',pa.ada_cacat_tubuh),pa.cacat_tubuh) cacat, "
                            + "pa.riwayat_alergi, if(pa.alergi_obat='ya','V','') aler_obat, if(pa.alergi_obat='ya',pa.reaksi_alergi_obat,'') reak_obat, if(pa.alergi_makanan='ya','V','') aler_mak, "
                            + "if(pa.alergi_makanan='ya',pa.reaksi_alergi_makanan,'') reak_mak, if(pa.alergi_lainnya='ya','V','') aler_lain, if(pa.alergi_lainnya='ya',pa.reaksi_alergi_lainnya,'') reak_lain, "
                            + "if(pa.pin_kancing='ya','V','') pin, pa.alergi_diberitahukan, if(pa.nyeri='Ya',concat(pa.nyeri,', Lokasi : ',pa.ya_nyeri_lokasi),pa.nyeri) nyeri, pa.jenis_nyeri, "
                            + "if(pa.provocation='Lainnya',concat(pa.provocation,', ',pa.provocation_lain),pa.provocation) provokes, if(pa.quality='Lainnya',concat(pa.quality,', ',pa.quality_lain),pa.quality) quality, "
                            + "pa.radiation radiasi, pa.severity severity_nyeri, concat(pa.time,', Lama : ',pa.time_lama) time_nyeri, pa.diagnosa_keperawatan, pa.tindakan_keperawatan, pa.evaluasi_keperawatan, if(pa.identifikasi1='ya','V','') iden1, "
                            + "if(pa.identifikasi2='ya','V','') iden2, if(pa.identifikasi3='ya','V','') iden3, if(pa.identifikasi4='ya','V','') iden4, if(pa.identifikasi5='ya','V','') iden5, if(pa.identifikasi6='ya','V','') iden6, "
                            + "if(pa.identifikasi7='ya','V','') iden7, if(pa.identifikasi8='ya','V','') iden8, if(pa.identifikasi9='ya','V','') iden9, if(pa.identifikasi10='ya','V','') iden10, "
                            + "if(pa.manajer_pelayanan='ya','V','') mpp, if(pa.discharge_planing='ya','V','') dp from penilaian_awal_keperawatan_igdrz pa "
                            + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                            + "pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);
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
    }
    
    private void cetakAsesMedikObs() {
        try {
            psLaprm = koneksi.prepareStatement("select *, date_format(tgl_keluar_ponek,'%H:%i:%s') jamklr "
                    + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRW.getText() + "'");
            try {
                rsLaprm = psLaprm.executeQuery();
                while (rsLaprm.next()) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));

                    if (rsLaprm.getString("cek_mengeluh_perut").equals("ya")) {
                        param.put("perut_mules", "Pasien mengeluh perut mules/nyeri mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_mengeluh_perut,'%d-%m-%Y'),', jam : ',time_format(tgl_mengeluh_perut,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("perut_mules", "Pasien mengeluh perut mules/nyeri mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("keluar_lendir").equals("Ya")) {
                        param.put("keluar_lendir", "Keluar lendir darah : " + rsLaprm.getString("keluar_lendir") + ", mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_lendir_ya,'%d-%m-%Y'),', jam : ',time_format(tgl_lendir_ya,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("keluar_lendir", "Keluar lendir darah : " + rsLaprm.getString("keluar_lendir") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("keluar_air").equals("Ya")) {
                        param.put("keluar_air", "Keluar air-air : " + rsLaprm.getString("keluar_air") + ", " + rsLaprm.getString("keluar_air_ya") + " mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_air_ya,'%d-%m-%Y'),', jam : ',time_format(tgl_air_ya,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("keluar_air", "Keluar air-air : " + rsLaprm.getString("keluar_air") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("mengeluh_pusing").equals("Ya")) {
                        param.put("mengeluh_pusing", "Pasien mengeluh pusing : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_pusing,'%d-%m-%Y'),', jam : ',time_format(tgl_pusing,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("mengeluh_pusing", "Pasien mengeluh pusing : " + rsLaprm.getString("mengeluh_pusing") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("nyeri_ulu_hati").equals("Ya")) {
                        param.put("nyeri_ulu", "Nyeri Ulu Hati : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_nyeri,'%d-%m-%Y'),', jam : ',time_format(tgl_nyeri,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("nyeri_ulu", "Nyeri Ulu Hati : " + rsLaprm.getString("nyeri_ulu_hati") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("pandangan_kabur").equals("Ya")) {
                        param.put("pandangan", "Pandangan kabur : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_pandangan,'%d-%m-%Y'),', jam : ',time_format(tgl_pandangan,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("pandangan", "Pandangan kabur : " + rsLaprm.getString("pandangan_kabur") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("mual_muntah").equals("Ya")) {
                        param.put("mual", "Mual/Muntah : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_mual_muntah,'%d-%m-%Y'),', jam : ',time_format(tgl_mual_muntah,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("mual", "Mual/Muntah : " + rsLaprm.getString("mual_muntah") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("batuk_pilek").equals("Ya")) {
                        param.put("batuk", "Batuk/Pilek : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_batuk_pilek,'%d-%m-%Y'),', jam : ',time_format(tgl_batuk_pilek,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("batuk", "Batuk/Pilek : " + rsLaprm.getString("batuk_pilek") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("demam").equals("Ya")) {
                        param.put("demam", "Demam : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_demam,'%d-%m-%Y'),', jam : ',time_format(tgl_demam,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("demam", "Demam : " + rsLaprm.getString("demam") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("pil").equals("ya")) {
                        param.put("cek_pil", "V");
                        param.put("pil", "Pil, lama : " + rsLaprm.getString("lama_pil") + " " + rsLaprm.getString("satuan_pil"));
                    } else {
                        param.put("cek_pil", "");
                        param.put("pil", "Pil, lama : -");
                    }

                    if (rsLaprm.getString("suntik_1_bln").equals("ya")) {
                        param.put("cek_suntik1", "V");
                        param.put("suntik1", "Suntik 1 bulan, lama : " + rsLaprm.getString("lama_1_bln") + " " + rsLaprm.getString("satuan_suntik1"));
                    } else {
                        param.put("cek_suntik1", "");
                        param.put("suntik1", "Suntik 1 bulan, lama : -");
                    }

                    if (rsLaprm.getString("suntik_3_bln").equals("ya")) {
                        param.put("cek_suntik3", "V");
                        param.put("suntik3", "Suntik 3 bulan, lama : " + rsLaprm.getString("lama_3_bln") + " " + rsLaprm.getString("satuan_suntik3"));
                    } else {
                        param.put("cek_suntik3", "");
                        param.put("suntik3", "Suntik 3 bulan, lama : -");
                    }

                    if (rsLaprm.getString("implan").equals("ya")) {
                        param.put("cek_implan", "V");
                        param.put("implan", "Implant, lama : " + rsLaprm.getString("lama_implan") + " " + rsLaprm.getString("satuan_implan"));
                    } else {
                        param.put("cek_implan", "");
                        param.put("implan", "Implant, lama : -");
                    }

                    if (!rsLaprm.getString("uk_usg").equals("")) {
                        param.put("uk_usg", "UK (USG) : " + rsLaprm.getString("uk_usg") + " minggu, tgl. USG : "
                                + Sequel.cariIsi("select date_format(tgl_usg,'%d-%m-%Y') from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("uk_usg", "UK (USG) : - minggu, tgl. USG : -");
                    }

                    if (rsLaprm.getString("cara_datang").equals("Sendiri")) {
                        param.put("cara_datang", rsLaprm.getString("cara_datang"));
                    } else if (rsLaprm.getString("cara_datang").equals("Rujukan Bidan/BPM")) {
                        param.put("cara_datang", rsLaprm.getString("cara_datang") + " : " + rsLaprm.getString("ket_rujukan_bidan"));
                    } else if (rsLaprm.getString("cara_datang").equals("PKM")) {
                        param.put("cara_datang", rsLaprm.getString("cara_datang") + " : " + rsLaprm.getString("ket_pkm"));
                    } else if (rsLaprm.getString("cara_datang").equals("SpOG")) {
                        param.put("cara_datang", rsLaprm.getString("ket_spog") + " SPOG");
                    } else if (rsLaprm.getString("cara_datang").equals("RS Lain")) {
                        param.put("cara_datang", rsLaprm.getString("cara_datang") + " : " + rsLaprm.getString("ket_rs_lain"));
                    } else {
                        param.put("cara_datang", "-");
                    }

                    if (rsLaprm.getString("his_kontraksi").equals("ya")) {
                        param.put("cek_his", "V");
                        param.put("his", "His/kontraksi : " + rsLaprm.getString("ket_his_kontraksi") + " x/10 mnt");
                    } else {
                        param.put("cek_his", "");
                        param.put("his", "His/kontraksi : ....  x/10 mnt");
                    }

                    if (rsLaprm.getString("meninggal").equals("ya")) {
                        param.put("jam_meninggal", rsLaprm.getString("jam_meninggal"));
                    } else {
                        param.put("jam_meninggal", "-");
                    }

                    if (rsLaprm.getString("cek_jam_keluar").equals("ya")) {
                        param.put("jam_keluar", rsLaprm.getString("jamklr"));
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
                            + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis where pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);

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
                            + "pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);
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
    }
    
    private void tampilAsesmenDewasa() {
        emptPemeriksaanFisik();
        try {
            ps1 = koneksi.prepareStatement("select *, if(diagnosis1<>'',diagnosis1,'') diag1, if(diagnosis2<>'',diagnosis2,'') diag2, "
                    + "if(diagnosis3<>'',diagnosis3,'') diag3, if(diagnosis4<>'',diagnosis4,'') diag4, if(diagnosis5<>'',diagnosis5,'') diag5, "
                    + "if(diagnosis6<>'',diagnosis6,'') diag6, if(diagnosis7<>'',diagnosis7,'') diag7 from asesmen_medik_dewasa_ranap where "
                    + "no_rawat='" + TNoRW.getText() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    //tanda vital
                    if (rs1.getString("keadaan_umum").equals("")) {
                        keadaanUmum = "";
                    } else {
                        keadaanUmum = "Keadaan Umum : " + rs1.getString("keadaan_umum") + ", ";
                    }
                    
                    if (rs1.getString("gizi").equals("")) {
                        gizi = "";
                    } else {
                        gizi = "Gizi : " + rs1.getString("gizi") + ", ";
                    }
                    
                    if (rs1.getString("gcs_e").equals("") && rs1.getString("gcs_m").equals("") && rs1.getString("gcs_v").equals("")) {
                        gcsDewasa = "";
                    } else {
                        gcsDewasa = "GCS : E " + rs1.getString("gcs_e") + ", M " + rs1.getString("gcs_m") + ", V " + rs1.getString("gcs_v") + ", ";
                    }
                    
                    if (rs1.getString("tindakan_resus").equals("tidak")) {
                        tindakanResus = "";
                    } else {
                        tindakanResus = "Tindakan Resusitasi : Ya, ";
                    }
                    
                    if (rs1.getString("bb").equals("")) {
                        beratBdn = "";
                    } else {
                        beratBdn = "Berat Badan : " + rs1.getString("bb") + " Kg., ";
                    }
                    
                    if (rs1.getString("tb").equals("")) {
                        tinggiBdn = "";
                    } else {
                        tinggiBdn = "Tinggi Badan : " + rs1.getString("tb") + " Cm., ";
                    }
                    
                    if (rs1.getString("td").equals("")) {
                        td = "";
                    } else {
                        td = "Tekanan Darah : " + rs1.getString("td") + " mmHg, ";
                    }
                    
                    if (rs1.getString("nadi").equals("")) {
                        nadiDewasa = "";
                    } else {
                        nadiDewasa = "Nadi : " + rs1.getString("nadi") + " x/mnt, ";
                    }
                    
                    if (rs1.getString("respirasi").equals("")) {
                        respi = "";
                    } else {
                        respi = "Respirasi : " + rs1.getString("respirasi") + " x/mnt, ";
                    }
                    
                    if (rs1.getString("suhu_axila").equals("")) {
                        suhuAxila = "";
                    } else {
                        suhuAxila = "Suhu Axilla : " + rs1.getString("suhu_axila") + " °C, ";
                    }
                    
                    if (rs1.getString("suhu_rektal").equals("")) {
                        suhuRektal = "";
                    } else {
                        suhuRektal = "Suhu Rectal : " + rs1.getString("suhu_rektal") + " °C, ";
                    }
                    
                    //pemeriksaan fisik
                    if (rs1.getString("mata_anemis").equals("")) {
                        anemis = "";
                    } else {
                        anemis = "Mata : Anemis : " + rs1.getString("mata_anemis") + ", ";
                    }
                    
                    if (rs1.getString("ikterik").equals("")) {
                        ikterik = "";
                    } else {
                        ikterik = "Ikterik : " + rs1.getString("ikterik") + ", ";
                    }
                    
                    if (rs1.getString("pupil").equals("")) {
                        pupil = "";
                    } else {
                        pupil = "Pupil : " + rs1.getString("pupil") + ", ";
                    }
                    
                    if (rs1.getString("diameter_kanan").equals("")) {
                        dia_kanan = "";
                    } else {
                        dia_kanan = "Diameter Kanan : " + rs1.getString("diameter_kanan") + " mm, ";
                    }
                    
                    if (rs1.getString("diameter_kiri").equals("")) {
                        dia_kiri = "";
                    } else {
                        dia_kiri = "Diameter Kiri : " + rs1.getString("diameter_kiri") + " mm, ";
                    }
                    
                    if (rs1.getString("udem_palpebra").equals("")) {
                        udem_palpe = "";
                    } else {
                        udem_palpe = "Udem Palpebrae : " + rs1.getString("udem_palpebra") + ", ";
                    }
                    
                    if (rs1.getString("tonsil").equals("")) {
                        tonsil = "";
                    } else {
                        tonsil = "THT : Tonsil : " + rs1.getString("tonsil") + ", ";
                    }
                    
                    if (rs1.getString("faring").equals("")) {
                        faring = "";
                    } else {
                        faring = "Faring : " + rs1.getString("faring") + ", ";
                    }
                    
                    if (rs1.getString("saturasi").equals("")) {
                        satur = "";
                    } else {
                        satur = "Saturasi O2 : " + rs1.getString("saturasi") + " %, ";
                    }
                    
                    if (rs1.getString("lidah").equals("")) {
                        lidah = "";
                    } else {
                        lidah = "Lidah : " + rs1.getString("lidah") + ", ";
                    }
                    
                    if (rs1.getString("bibir").equals("")) {
                        bibir = "";
                    } else {
                        bibir = "Bibir : " + rs1.getString("bibir") + ", ";
                    }
                    
                    if (rs1.getString("jvp").equals("")) {
                        jvp = "";
                    } else {
                        jvp = "Leher : JVP : " + rs1.getString("jvp") + ", ";
                    }
                    
                    if (rs1.getString("kelenjar_limfe").equals("")) {
                        limfe = "";
                    } else if (rs1.getString("kelenjar_limfe").equals("Ada")) {
                        limfe = "Pembesaran Kelenjar Limfe : " + rs1.getString("kelenjar_limfe") + " (" + rs1.getString("ket_ada_kelenjar") + "), ";
                    } else if (rs1.getString("kelenjar_limfe").equals("Tidak")) {
                        limfe = "Pembesaran Kelenjar Limfe : " + rs1.getString("kelenjar_limfe") + ", ";
                    }
                    
                    if (rs1.getString("kaku_kuduk").equals("")) {
                        kuduk = "";
                    } else {
                        kuduk = "Kaku Kuduk : " + rs1.getString("kaku_kuduk") + ", ";
                    }
                    
                    if (rs1.getString("thoraks").equals("")) {
                        thorak = "";
                    } else if (rs1.getString("thoraks").equals("Asimetris")) {
                        thorak = "Thoraks : " + rs1.getString("thoraks") + " (" + rs1.getString("ket_asimetris") + "), ";
                    } else if (rs1.getString("thoraks").equals("Simetris")) {
                        thorak = "Thoraks : " + rs1.getString("thoraks") + ", ";
                    }
                    
                    if (rs1.getString("cor_s1s2").equals("")) {
                        cor = "";
                    } else {
                        cor = "Cor : S1/S2 " + rs1.getString("cor_s1s2") + ", ";
                    }
                    
                    if (rs1.getString("reguler").equals("tidak")) {
                        reguler = "";
                    } else {
                        reguler = "Reguler : Ya, ";
                    }
                    
                    if (rs1.getString("ireguler").equals("tidak")) {
                        ireguler = "";
                    } else {
                        ireguler = "Ireguler, Murmur : Ya (" + rs1.getString("murmur") + "), ";
                    }
                    
                    if (rs1.getString("lain_lain").equals("")) {
                        lain1 = "";
                    } else {
                        lain1 = "Lain-lain : " + rs1.getString("lain_lain") + ", ";
                    }
                    
                    if (rs1.getString("suara_nafas").equals("")) {
                        nafas = "";
                    } else {
                        nafas = "Pulmo : Suara Nafas : " + rs1.getString("suara_nafas") + ", ";
                    }
                    
                    if (rs1.getString("ronchi").equals("")) {
                        ronci = "";
                    } else if (rs1.getString("ronchi").equals("Ada")) {
                        ronci = "Ronchi : " + rs1.getString("ronchi") + " (" + rs1.getString("ket_ronchi") + "), ";
                    } else if (rs1.getString("ronchi").equals("Tidak")) {
                        ronci = "Ronchi : " + rs1.getString("ronchi") + ", ";
                    }

                    if (rs1.getString("wheezing").equals("")) {
                        whezing = "";
                    } else if (rs1.getString("wheezing").equals("Ada")) {
                        whezing = "Wheezing : " + rs1.getString("wheezing") + " (" + rs1.getString("ket_wheezing") + "), ";
                    } else if (rs1.getString("wheezing").equals("Tidak")) {
                        whezing = "Wheezing : " + rs1.getString("wheezing") + ", ";
                    }
                    
                    if (rs1.getString("distended").equals("")) {
                        disten = "";
                    } else {
                        disten = "Abdomen : Distended : " + rs1.getString("distended") + ", ";
                    }
                    
                    if (rs1.getString("meteorismus").equals("")) {
                        meteo = "";
                    } else {
                        meteo = "Meteorismus : " + rs1.getString("meteorismus") + ", ";
                    }
                    
                    if (rs1.getString("peristaltik").equals("")) {
                        peris = "";
                    } else {
                        peris = "Peristaltik : " + rs1.getString("peristaltik") + ", ";
                    }
                    
                    if (rs1.getString("asites").equals("")) {
                        asites = "";
                    } else {
                        asites = "Asites : " + rs1.getString("asites") + ", ";
                    }
                    
                    if (rs1.getString("nyeri_tekan").equals("")) {
                        nyeri = "";
                    } else if (rs1.getString("nyeri_tekan").equals("Ada")) {
                        nyeri = "Nyeri Tekan : " + rs1.getString("nyeri_tekan") + " (" + rs1.getString("lokasi") + "), ";
                    } else if (rs1.getString("nyeri_tekan").equals("Tidak")) {
                        nyeri = "Nyeri Tekan : " + rs1.getString("nyeri_tekan") + ", ";
                    }
                    
                    if (rs1.getString("hepar").equals("")) {
                        hepar = "";
                    } else {
                        hepar = "Hepar : " + rs1.getString("hepar") + ", ";
                    }
                    
                    if (rs1.getString("lien").equals("")) {
                        lien = "";
                    } else {
                        lien = "Lien : " + rs1.getString("lien") + ", ";
                    }
                    
                    if (rs1.getString("extremitas").equals("")) {
                        extrem = "";
                    } else {
                        extrem = "Extremitas : " + rs1.getString("extremitas") + ", ";
                    }
                    
                    if (rs1.getString("udem").equals("")) {
                        udem = "";
                    } else if (rs1.getString("udem").equals("Ada")) {
                        udem = "Udem : " + rs1.getString("udem") + " (" + rs1.getString("ket_udem") + "), ";
                    } else if (rs1.getString("udem").equals("Tidak")) {
                        udem = "Udem : " + rs1.getString("udem") + ", ";
                    }
                    
                    if (rs1.getString("pemeriksaan_fisik_lain").equals("")) {
                        lain2 = "";
                    } else {
                        lain2 = "Pemeriksaan lain : " + rs1.getString("pemeriksaan_fisik_lain");
                    }

                    if (TPemeriksaanFisik.getText().equals("")) {
                        TPemeriksaanFisik.setText(keadaanUmum + gizi + gcsDewasa + tindakanResus + beratBdn + tinggiBdn + td
                                + nadiDewasa + respi + suhuAxila + suhuRektal 
                                + anemis + ikterik + pupil + dia_kanan + dia_kiri + udem_palpe + tonsil + faring
                                + satur + lidah + bibir + jvp + limfe + kuduk + thorak + cor + reguler + ireguler + lain1 + nafas
                                + ronci + whezing + disten + meteo + peris + asites + nyeri + hepar + lien + extrem + udem + lain2);
                    } else {
                        TPemeriksaanFisik.setText(TPemeriksaanFisik.getText() + "\n\n" + keadaanUmum + gizi + gcsDewasa + tindakanResus 
                                + beratBdn + tinggiBdn + td + nadiDewasa + respi + suhuAxila + suhuRektal
                                + anemis + ikterik + pupil + dia_kanan + dia_kiri + udem_palpe + tonsil + faring + satur + lidah + bibir 
                                + jvp + limfe + kuduk + thorak + cor + reguler + ireguler + lain1 + nafas + ronci + whezing + disten 
                                + meteo + peris + asites + nyeri + hepar + lien + extrem + udem + lain2);
                    }
                    //-------------------------------------------------------------------------------------------------------------------
                    
                    if (TTerapiPengobatan.getText().equals("")) {
                        TTerapiPengobatan.setText(rs1.getString("rencana_kerja"));
                    } else {
                        TTerapiPengobatan.setText(TTerapiPengobatan.getText() + "\n\n" + rs1.getString("rencana_kerja"));
                    }
                    
//                    if (TDiagUtama.getText().equals("")) {
//                        TDiagUtama.setText(rs1.getString("diag1") + "\n"
//                                + rs1.getString("diag2") + "\n"
//                                + rs1.getString("diag3") + "\n"
//                                + rs1.getString("diag4") + "\n"
//                                + rs1.getString("diag5") + "\n"
//                                + rs1.getString("diag6") + "\n"
//                                + rs1.getString("diag7"));
//                    } else {
//                        TDiagUtama.setText(TDiagUtama.getText() + "\n\n" 
//                                + rs1.getString("diag1") + "\n"
//                                + rs1.getString("diag2") + "\n"
//                                + rs1.getString("diag3") + "\n"
//                                + rs1.getString("diag4") + "\n"
//                                + rs1.getString("diag5") + "\n"
//                                + rs1.getString("diag6") + "\n"
//                                + rs1.getString("diag7"));
//                    }
                    
                    if (TDiagUtama.getText().equals("")) {
                        TDiagUtama.setText(rs1.getString("diagnosa_primer"));
                    } else {
                        TDiagUtama.setText(TDiagUtama.getText() + "\n\n" + rs1.getString("diagnosa_primer"));
                    }
                    
                    if (TDiagSekunder.getText().equals("")) {
                        TDiagSekunder.setText(rs1.getString("diagnosa_sekunder"));
                    } else {
                        TDiagSekunder.setText(TDiagSekunder.getText() + "\n\n" + rs1.getString("diagnosa_sekunder"));
                    }
                    
                    if (TAlasanDirawat.getText().equals("")) {
                        TAlasanDirawat.setText(rs1.getString("keluhan_utama"));
                    } else {
                        TAlasanDirawat.setText(TAlasanDirawat.getText() + "\n\n" + rs1.getString("keluhan_utama"));
                    }
                    
                    if (TRingkasanRiwayat.getText().equals("")) {
                        TRingkasanRiwayat.setText(rs1.getString("riw_penyakit_sekarang"));
                    } else {
                        TRingkasanRiwayat.setText(TRingkasanRiwayat.getText() + "\n\n" + rs1.getString("riw_penyakit_sekarang"));
                    }
                    
                    if (TPemeriksaanPenunjang.getText().equals("")) {
                        TPemeriksaanPenunjang.setText(rs1.getString("hasil_pemeriksaan"));
                    } else {
                        TPemeriksaanPenunjang.setText(TPemeriksaanPenunjang.getText() + "\n\n" + rs1.getString("hasil_pemeriksaan"));
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
    
    private void tampilAsesmenPerinatologi() {
        emptPemeriksaanFisik();
        try {
            ps5 = koneksi.prepareStatement("select * from asesmen_medik_perinatologi where no_rawat='" + TNoRW.getText() + "'");
            try {
                rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    if (TAlasanDirawat.getText().equals("")) {
                        TAlasanDirawat.setText(rs5.getString("keluhan"));
                    } else {
                        TAlasanDirawat.setText(TAlasanDirawat.getText() + "\n\n" + rs5.getString("keluhan"));
                    }
                    
                    if (TRingkasanRiwayat.getText().equals("")) {
                        TRingkasanRiwayat.setText(rs5.getString("riw_penyakit_dahulu"));
                    } else {
                        TRingkasanRiwayat.setText(TRingkasanRiwayat.getText() + "\n\n" + rs5.getString("riw_penyakit_dahulu"));
                    }
                    
                    //pemeriksaan fisik
                    //tanda vital ----->>
                    if (rs5.getString("kondisi_saat_lahir").equals("-")) {
                        kondisiLahir = "";
                    } else {
                        kondisiLahir = "Kondisi Saat Lahir : " + rs5.getString("kondisi_saat_lahir") + ", ";
                    }
                    
                    if (rs5.getString("ket_as").equals("")) {
                        ketAS = "";
                    } else {
                        ketAS = "AS : " + rs5.getString("ket_as") + "\n\n";
                    }
                    
                    if (rs5.getString("gerak").equals("")) {
                        gerak = "";
                    } else {
                        gerak = "Gerak : " + rs5.getString("gerak") + ", ";
                    }
                    
                    if (rs5.getString("tangis").equals("")) {
                        tangis = "";
                    } else {
                        tangis = "Tangis : " + rs5.getString("tangis") + ", ";
                    }
                    
                    if (rs5.getString("warna_kulit").equals("")) {
                        warnaKulit = "";
                    } else {
                        warnaKulit = "Warna Kulit : " + rs5.getString("warna_kulit") + ", ";
                    }
                    
                    if (rs5.getString("hr").equals("")) {
                        hrPeri = "";
                    } else {
                        hrPeri = "HR : " + rs5.getString("hr") + " x/menit, ";
                    }
                    
                    if (rs5.getString("suhu").equals("")) {
                        suhuPeri = "";
                    } else {
                        suhuPeri = "Suhu : " + rs5.getString("suhu") + " °C, ";
                    }
                    
                    if (rs5.getString("rr").equals("")) {
                        rrPeri = "";
                    } else {
                        rrPeri = "RR : " + rs5.getString("rr") + " x/menit, ";
                    }
                    
                    if (rs5.getString("saturasi").equals("")) {
                        satuPeri = "";
                    } else {
                        satuPeri = "Sat O2 : " + rs5.getString("saturasi") + " %, ";
                    }
                    
                    if (rs5.getString("capilary_refill").equals("-")) {
                        capilari = "";
                    } else {
                        capilari = "Capillary Refill : " + rs5.getString("capilary_refill") + ", ";
                    }
                    
                    if (rs5.getString("bbl").equals("")) {
                        bblPeri = "";
                    } else {
                        bblPeri = "BBL : " + rs5.getString("bbl") + " gram, ";
                    }
                    
                    if (rs5.getString("pb").equals("")) {
                        pbPeri = "";
                    } else {
                        pbPeri = "PB : " + rs5.getString("pb") + " cm, ";
                    }
                    
                    if (rs5.getString("lk").equals("")) {
                        lkPeri = "";
                    } else {
                        lkPeri = "LK : " + rs5.getString("lk") + " cm, ";
                    }
                    
                    if (rs5.getString("ld").equals("")) {
                        ldPeri = "";
                    } else {
                        ldPeri = "LD : " + rs5.getString("ld") + " cm, ";
                    }
                    
                    if (rs5.getString("lp").equals("")) {
                        lpPeri = "";
                    } else {
                        lpPeri = "LP : " + rs5.getString("lp") + " cm, ";
                    }
                    
                    if (rs5.getString("lla").equals("")) {
                        llaPeri = "";
                    } else {
                        llaPeri = "LLA : " + rs5.getString("lla") + " cm";
                    }

                    poinA = kondisiLahir + ketAS + "a. Tanda Vital :\n" + gerak + tangis + warnaKulit + hrPeri + suhuPeri + rrPeri + satuPeri + capilari
                            + bblPeri + pbPeri + lkPeri + ldPeri + lpPeri + llaPeri + "\n\n";
                    
                    //pemeriksaan umum ----->>
                    //kulit
                    if (rs5.getString("turgor").equals("tidak")) {
                        turgorPeri = "";
                    } else {
                        if (rs5.getString("ket_turgor").equals("")) {
                            turgorPeri = "Turgor, ";
                        } else {
                            turgorPeri = "Turgor (" + rs5.getString("ket_turgor") + "), ";
                        }
                    }
                    
                    if (rs5.getString("sianosis_kulit").equals("tidak")) {
                        sianosisKulitPeri = "";
                    } else {
                        sianosisKulitPeri = "Sianosis, ";
                    }
                    
                    if (rs5.getString("perdarahan_kulit").equals("tidak")) {
                        perdarahanKulitPeri = "";
                    } else {
                        perdarahanKulitPeri = "Perdarahan, ";
                    }
                    
                    if (rs5.getString("ikterus_positif").equals("tidak")) {
                        ikterusPosPeri = "";
                    } else {
                        ikterusPosPeri = "Ikterus : +, ";
                    }
                    
                    if (rs5.getString("ikterus_negatif").equals("tidak")) {
                        ikterusNegPeri = "";
                    } else {
                        ikterusNegPeri = "Ikterus : -, ";
                    }
                    
                    if (rs5.getString("krammer").equals("tidak")) {
                        kramerPeri = "";
                    } else {
                        if (rs5.getString("ket_krammer").equals("")) {
                            kramerPeri = "Krammer, ";
                        } else {
                            kramerPeri = "Krammer (" + rs5.getString("ket_krammer") + "), ";
                        }
                    }
                    
                    if (rs5.getString("hematoma").equals("tidak")) {
                        hematomaPeri = "";
                    } else {
                        hematomaPeri = "Hematoma, ";
                    }
                    
                    if (rs5.getString("sklerema").equals("tidak")) {
                        sklerePeri = "";
                    } else {
                        sklerePeri = "Sklerema, ";
                    }
                    
                    if (rs5.getString("kutis").equals("tidak")) {
                        kutisPeri = "";
                    } else {
                        kutisPeri = "Kutis Marmorata, ";
                    }
                    
                    if (rs5.getString("lainya_kulit").equals("tidak")) {
                        lainKulitPeri = "";
                    } else {
                        if (rs5.getString("ket_lainya_kulit").equals("")) {
                            lainKulitPeri = "Lainnya";
                        } else {
                            lainKulitPeri = "Lainnya (" + rs5.getString("ket_lainya_kulit") + ")";
                        }
                    }
                    
                    poinB = turgorPeri + sianosisKulitPeri + perdarahanKulitPeri + ikterusPosPeri + ikterusNegPeri + kramerPeri + hematomaPeri
                            + sklerePeri + kutisPeri + lainKulitPeri + "\n";
                    
                    //kepala
                    if (rs5.getString("simetris_kapala").equals("tidak")) {
                        simetrisKepalaPeri = "";
                    } else {
                        simetrisKepalaPeri = "Simetris, ";
                    }
                    
                    if (rs5.getString("asimetris_kepala").equals("tidak")) {
                        asimetrisKepalaPeri = "";
                    } else {
                        asimetrisKepalaPeri = "Asimetris, ";
                    }
                    
                    if (rs5.getString("cephal_hematom").equals("tidak")) {
                        cepalPeri = "";
                    } else {
                        cepalPeri = "Cephal Hematom, ";
                    }
                    
                    if (rs5.getString("caput_succedaneum").equals("tidak")) {
                        caputPeri = "";
                    } else {
                        caputPeri = "Caput Succedaneum, ";
                    }
                    
                    if (rs5.getString("anensefali").equals("tidak")) {
                        anenPeri = "";
                    } else {
                        anenPeri = "Anensefali, ";
                    }
                    
                    if (rs5.getString("microsefal").equals("tidak")) {
                        microsPeri = "";
                    } else {
                        microsPeri = "Microsefal, ";
                    }
                    
                    if (rs5.getString("hydrosefalus").equals("tidak")) {
                        hidroPeri = "";
                    } else {
                        hidroPeri = "Hydrosefalus, ";
                    }
                    
                    if (rs5.getString("lainya_kepala").equals("tidak")) {
                        lainKepalaPeri = "";
                    } else {
                        if (rs5.getString("ket_lainya_kepala").equals("")) {
                            lainKepalaPeri = "Lainnya";
                        } else {
                            lainKepalaPeri = "Lainnya (" + rs5.getString("ket_lainya_kepala") + ")";
                        }
                    }
                    
                    poinC = simetrisKepalaPeri + asimetrisKepalaPeri + cepalPeri + caputPeri + anenPeri + microsPeri + hidroPeri + lainKepalaPeri + "\n";
                    
                    //uub
                    if (rs5.getString("datar").equals("tidak")) {
                        datarPeri = "";
                    } else {
                        datarPeri = "Datar, ";
                    }
                    
                    if (rs5.getString("cembung").equals("tidak")) {
                        cembungPeri = "";
                    } else {
                        cembungPeri = "Cembung, ";
                    }
                    
                    if (rs5.getString("cekung").equals("tidak")) {
                        cekungPeri = "";
                    } else {
                        cekungPeri = "Cekung, ";
                    }
                    
                    if (rs5.getString("lainya_uub").equals("tidak")) {
                        lainUubPeri = "";
                    } else {
                        if (rs5.getString("ket_lainya_uub").equals("")) {
                            lainUubPeri = "Lainnya";
                        } else {
                            lainUubPeri = "Lainnya (" + rs5.getString("ket_lainya_uub") + ")";
                        }
                    }
                    
                    poinDE = datarPeri + cembungPeri + cekungPeri + lainUubPeri + "\n";
                    
                    //mata
                    if (rs5.getString("normal_mata").equals("tidak")) {
                        normalMataPeri = "";
                    } else {
                        normalMataPeri = "Normal, ";
                    }
                    
                    if (rs5.getString("anemia").equals("tidak")) {
                        anemiaPeri = "";
                    } else {
                        anemiaPeri = "Anemia, ";
                    }
                    
                    if (rs5.getString("ikterus_mata").equals("tidak")) {
                        ikterusPeri = "";
                    } else {
                        ikterusPeri = "Ikterus, ";
                    }
                    
                    if (rs5.getString("sekret_mata").equals("tidak")) {
                        sekretMataPeri = "";
                    } else {
                        sekretMataPeri = "Sekret, ";
                    }
                    
                    if (rs5.getString("lainya_mata").equals("tidak")) {
                        LainMataPeri = "";
                    } else {
                        if (rs5.getString("ket_lainya_mata").equals("")) {
                            LainMataPeri = "Lainnya";
                        } else {
                            LainMataPeri = "Lainnya (" + rs5.getString("ket_lainya_mata") + ")";
                        }
                    }
                    
                    poinF = normalMataPeri + anemiaPeri + ikterusPeri + sekretMataPeri + LainMataPeri + "\n";
                    
                    //tht
                    if (rs5.getString("normal_tht").equals("tidak")) {
                        normalThtPeri = "";
                    } else {
                        normalThtPeri = "Normal, ";
                    }
                    
                    if (rs5.getString("nch").equals("tidak")) {
                        nchPeri = "";
                    } else {
                        nchPeri = "NCH, ";
                    }
                    
                    if (rs5.getString("sianosis_tht").equals("tidak")) {
                        sianosisThtPeri = "";
                    } else {
                        sianosisThtPeri = "Sianosis, ";
                    }
                    
                    if (rs5.getString("sekret_tht").equals("tidak")) {
                        sekretThtPeri = "";
                    } else {
                        sekretThtPeri = "Sekret, ";
                    }
                   
                    if (rs5.getString("lainya_tht").equals("tidak")) {
                        lainThtPeri = "";
                    } else {
                        if (rs5.getString("ket_lainya_tht").equals("")) {
                            lainThtPeri = "Lainnya";
                        } else {
                            lainThtPeri = "Lainnya (" + rs5.getString("ket_lainya_tht") + ")";
                        }
                    }
                    
                    poinG = normalThtPeri + nchPeri + sianosisThtPeri + sekretThtPeri + lainThtPeri + "\n";
                    
                    //mulut
                    if (rs5.getString("normal_mulut").equals("tidak")) {
                        normalMulutPeri = "";
                    } else {
                        normalMulutPeri = "Normal, ";
                    }
                    
                    if (rs5.getString("labioschisis").equals("tidak")) {
                        labioSPeri = "";
                    } else {
                        labioSPeri = "Labioschisis, ";
                    }
                    
                    if (rs5.getString("labiopalatoschisis").equals("tidak")) {
                        labioPPeri = "";
                    } else {
                        labioPPeri = "Labiopalatoschisis, ";
                    }
                    
                    if (rs5.getString("labiognatopalatoschisis").equals("tidak")) {
                        labioGPeri = "";
                    } else {
                        labioGPeri = "Labiognatopalatoschisis, ";
                    }
                    
                    if (rs5.getString("mucosa_warna").equals("tidak")) {
                        mukosaPeri = "";
                    } else {
                        if (rs5.getString("ket_warna").equals("")) {
                            mukosaPeri = "Mukosa : Warna -, ";
                        } else {
                            mukosaPeri = "Mukosa : Warna (" + rs5.getString("ket_warna") + "), ";
                        }
                    }
                    
                    if (rs5.getString("reflek_hisap").equals("tidak")) {
                        reflekPeri = "";
                    } else {
                        if (rs5.getString("ket_reflek_hisap").equals("")) {
                            reflekPeri = "Refleks Hisap, ";
                        } else {
                            reflekPeri = "Refleks Hisap (" + rs5.getString("ket_reflek_hisap") + "), ";
                        }
                    }
                    
                    if (rs5.getString("lainya_mulut").equals("tidak")) {
                        lainMulutPeri = "";
                    } else {
                        if (rs5.getString("ket_lainya_mulut").equals("")) {
                            lainMulutPeri = "Lainnya";
                        } else {
                            lainMulutPeri = "Lainnya (" + rs5.getString("ket_lainya_mulut") + ")";
                        }
                    }
                    
                    poinH = normalMulutPeri + labioSPeri + labioPPeri + labioGPeri + mukosaPeri + reflekPeri + lainMulutPeri + "\n";
                    
                    //leher
                    if (rs5.getString("normal_leher").equals("tidak")) {
                        normalLeherPeri = "";
                    } else {
                        normalLeherPeri = "Normal, ";
                    }
                    
                    if (rs5.getString("tortikolis").equals("tidak")) {
                        tortiPeri = "";
                    } else {
                        tortiPeri = "Tortikolis, ";
                    }
                    
                    if (rs5.getString("benjolan_kanan").equals("tidak")) {
                        benjolKananPeri = "";
                    } else {
                        benjolKananPeri = "Benjolan Dikanan, ";
                    }
                    
                    if (rs5.getString("benjolan_kiri").equals("tidak")) {
                        benjolKiriPeri = "";
                    } else {
                        benjolKiriPeri = "Benjolan Dikiri, ";
                    }
                    
                    if (rs5.getString("lainya_leher").equals("tidak")) {
                        lainLeherPeri = "";
                    } else {
                        if (rs5.getString("ket_lainya_leher").equals("")) {
                            lainLeherPeri = "Lainnya";
                        } else {
                            lainLeherPeri = "Lainnya (" + rs5.getString("ket_lainya_leher") + ")";
                        }
                    }
                    
                    poinI = normalLeherPeri + tortiPeri + benjolKananPeri + benjolKiriPeri + lainLeherPeri + "\n";
                    
                    //dada
                    if (rs5.getString("simetris_dada").equals("tidak")) {
                        simetrisDadaPeri = "";
                    } else {
                        simetrisDadaPeri = "Simetris, ";
                    }
                    
                    if (rs5.getString("tidak_simetris").equals("tidak")) {
                        tidakSimetrisPeri = "";
                    } else {
                        tidakSimetrisPeri = "Tidak Simetris, ";
                    }
                    
                    if (rs5.getString("retraksi_positif").equals("tidak")) {
                        retraksiPosPeri = "";
                    } else {
                        retraksiPosPeri = "Retraksi : +, ";
                    }
                    
                    if (rs5.getString("retraksi_negatif").equals("tidak")) {
                        retraksiNegPeri = "";
                    } else {
                        retraksiNegPeri = "Retraksi : -, ";
                    }
                    
                    if (rs5.getString("ket_retraksi").equals("")) {
                        ketRetraksi = "";
                    } else {
                        ketRetraksi = "di " + rs5.getString("ket_retraksi") + ", ";
                    }
                    
                    if (rs5.getString("sesak").equals("tidak")) {
                        sesakPeri = "";
                    } else {
                        sesakPeri = "Sesak, ";
                    }
                    
                    if (rs5.getString("merintih").equals("tidak")) {
                        merintihPeri = "";
                    } else {
                        merintihPeri = "Merintih, ";
                    }
                    
                    if (rs5.getString("sianosis_dada").equals("tidak")) {
                        sianosisDadaPeri = "";
                    } else {
                        sianosisDadaPeri = "Sianosis, ";
                    }
                    
                    if (rs5.getString("lainya_dada").equals("tidak")) {
                        lainDadaPeri = "";
                    } else {
                        if (rs5.getString("ket_lainya_dada").equals("")) {
                            lainDadaPeri = "Lainnya";
                        } else {
                            lainDadaPeri = "Lainnya (" + rs5.getString("ket_lainya_dada") + ")";
                        }
                    }
                    
                    poinJ = simetrisDadaPeri + tidakSimetrisPeri + retraksiPosPeri + retraksiNegPeri + ketRetraksi + sesakPeri + merintihPeri + sianosisDadaPeri + lainDadaPeri + "\n";
                    
                    //jantung
                    if (rs5.getString("bji").equals("tidak")) {
                        bjPeri = "";
                    } else {
                        bjPeri = "BJ I & II, ";
                    }
                    
                    if (rs5.getString("murni").equals("tidak")) {
                        murniPeri = "";
                    } else {
                        murniPeri = "Murni, ";
                    }
                    
                    if (rs5.getString("tidak_murni").equals("tidak")) {
                        tidakMurniPeri = "";
                    } else {
                        tidakMurniPeri = "Tidak Murni, ";
                    }

                    if (rs5.getString("reguler").equals("tidak")) {
                        regulerPeri = "";
                    } else {
                        regulerPeri = "Reguler, ";
                    }
                    
                    if (rs5.getString("tidak_reguler").equals("tidak")) {
                        tidakRegulerPeri = "";
                    } else {
                        tidakRegulerPeri = "Tidak Reguler, ";
                    }
                    
                    if (rs5.getString("bunyi_tambahan").equals("tidak")) {
                        bunyiPeri = "";
                    } else {
                        if (rs5.getString("ket_bunyi_tambahan").equals("")) {
                            bunyiPeri = "Bunyi Tambahan";
                        } else {
                            bunyiPeri = "Bunyi Tambahan (" + rs5.getString("ket_bunyi_tambahan") + ")";
                        }
                    }
                    
                    poinK = bjPeri + murniPeri + tidakMurniPeri + regulerPeri + tidakRegulerPeri + bunyiPeri + "\n";
                    
                    //paru
                    if (rs5.getString("vesikuler").equals("tidak")) {
                        vesikulerPeri = "";
                    } else {
                        vesikulerPeri = "Vesikuler, ";
                    }
                    
                    if (rs5.getString("ronchi").equals("tidak")) {
                        ronchiPeri = "";
                    } else {
                        ronchiPeri = "Ronchi, ";
                    }
                    
                    if (rs5.getString("whezing").equals("tidak")) {
                        wezingPeri = "";
                    } else {
                        wezingPeri = "Wheezing, ";
                    }
                    
                    if (rs5.getString("stridor").equals("tidak")) {
                        stridorPeri = "";
                    } else {
                        stridorPeri = "Stridor, ";
                    }
                    
                    if (rs5.getString("lainya_paru").equals("tidak")) {
                        lainParuPeri = "";
                    } else {
                        if (rs5.getString("ket_lainya_paru").equals("")) {
                            lainParuPeri = "Lainnya";
                        } else {
                            lainParuPeri = "Lainnya (" + rs5.getString("ket_lainya_paru") + ")";
                        }
                    }
                    
                    poinL = vesikulerPeri + ronchiPeri + wezingPeri + stridorPeri + lainParuPeri + "\n";
                    
                    //perut
                    if (rs5.getString("supel").equals("tidak")) {
                        supelPeri = "";
                    } else {
                        supelPeri = "Supel/Flat, ";
                    }
                    
                    if (rs5.getString("distensi").equals("tidak")) {
                        distenPeri = "";
                    } else {
                        distenPeri = "Distensi, ";
                    }
                    
                    if (rs5.getString("bising_usus").equals("tidak")) {
                        bisingPeri = "";
                    } else {
                        bisingPeri = "Bising Usus, ";
                    }
                    
                    if (rs5.getString("pembesaran_hepar").equals("tidak")) {
                        heparPeri = "";
                    } else {
                        heparPeri = "Pembesaran Hepar, ";
                    }
                    
                    if (rs5.getString("pembesaran_limpa").equals("tidak")) {
                        limpaPeri = "";
                    } else {
                        limpaPeri = "Pembesaran Limpa, ";
                    }
                    
                    if (rs5.getString("nyeri").equals("tidak")) {
                        nyeriPeri = "";
                    } else {
                        if (rs5.getString("ket_nyeri").equals("")) {
                            nyeriPeri = "Nyeri Tekan, Regio :, ";
                        } else {
                            nyeriPeri = "Nyeri Tekan, Regio (" + rs5.getString("ket_nyeri") + "), ";
                        }
                    }
                    
                    if (rs5.getString("massa_positif").equals("tidak")) {
                        masaPosPeri = "";
                    } else {
                        masaPosPeri = "Massa : +, ";
                    }
                    
                    if (rs5.getString("massa_negatif").equals("tidak")) {
                        masaNegPeri = "";
                    } else {
                        masaNegPeri = "Massa : -, ";
                    }
                    
                    if (rs5.getString("uk").equals("tidak")) {
                        ukPeri = "";
                    } else {
                        if (rs5.getString("ket_uk").equals("")) {
                            ukPeri = "UK :, ";
                        } else {
                            ukPeri = "UK (" + rs5.getString("ket_uk") + "), ";
                        }
                    }
                    
                    if (rs5.getString("lokasi").equals("tidak")) {
                        lokasiPeri = "";
                    } else {
                        if (rs5.getString("ket_lokasi").equals("")) {
                            lokasiPeri = "Lokasi :, ";
                        } else {
                            lokasiPeri = "Lokasi (" + rs5.getString("ket_lokasi") + ")";
                        }
                    }
                    
                    poinM = supelPeri + distenPeri + bisingPeri + heparPeri + limpaPeri + nyeriPeri + masaPosPeri + masaNegPeri + ukPeri + lokasiPeri + "\n";
                    
                    //tali pusat
                    if (rs5.getString("segar").equals("tidak")) {
                        segarPeri = "";
                    } else {
                        segarPeri = "Segar, ";
                    }
                    
                    if (rs5.getString("layu").equals("tidak")) {
                        layuPeri = "";
                    } else {
                        layuPeri = "Layu, ";
                    }
                    
                    if (rs5.getString("lainya_tali_pusat").equals("tidak")) {
                        lainTaliPeri = "";
                    } else {
                        if (rs5.getString("ket_lainya_tali_pusat").equals("")) {
                            lainTaliPeri = "Lainnya";
                        } else {
                            lainTaliPeri = "Lainnya (" + rs5.getString("ket_lainya_tali_pusat") + ")";
                        }
                    }
                    
                    poinN = segarPeri + layuPeri + lainTaliPeri + "\n";
                    
                    //punggung
                    if (rs5.getString("normal_punggung").equals("tidak")) {
                        normalPunggungPeri = "";
                    } else {
                        normalPunggungPeri = "Normal, ";
                    }
                    
                    if (rs5.getString("spina").equals("tidak")) {
                        spinaPeri = "";
                    } else {
                        spinaPeri = "Spina Bifida, ";
                    }
                    
                    if (rs5.getString("gibus").equals("tidak")) {
                        gibusPeri = "";
                    } else {
                        gibusPeri = "Gibus, ";
                    }
                    
                    if (rs5.getString("lainya_punggung").equals("tidak")) {
                        lainPunggungPeri = "";
                    } else {
                        if (rs5.getString("ket_lainya_punggung").equals("")) {
                            lainPunggungPeri = "Lainnya";
                        } else {
                            lainPunggungPeri = "Lainnya (" + rs5.getString("ket_lainya_punggung") + ")";
                        }
                    }
                    
                    poinO = normalPunggungPeri + spinaPeri + gibusPeri + lainPunggungPeri + "\n";
                    
                    //urogenitalia
                    if (rs5.getString("sex").equals("tidak")) {
                        sexPeri = "";
                    } else {
                        if (rs5.getString("ket_sex").equals("")) {
                            sexPeri = "Sex, ";
                        } else {
                            sexPeri = "Sex (" + rs5.getString("ket_sex") + "), ";
                        }
                    }
                    
                    if (rs5.getString("kelainan_urogenitalia").equals("tidak")) {
                        kelainanUroPeri = "";
                    } else {
                        if (rs5.getString("ket_kelainan_urogenitalia").equals("")) {
                            kelainanUroPeri = "Kelainan, ";
                        } else {
                            kelainanUroPeri = "Kelainan (" + rs5.getString("ket_kelainan_urogenitalia") + "), ";
                        }
                    }
                    
                    if (rs5.getString("bak").equals("tidak")) {
                        bakPeri = "";
                    } else {
                        if (rs5.getString("ket_bak").equals("")) {
                            bakPeri = "BAK";
                        } else {
                            bakPeri = "BAK (" + rs5.getString("ket_bak") + ")";
                        }
                    }
                    
                    poinP = sexPeri + kelainanUroPeri + bakPeri + "\n";
                    
                    //anus
                    if (rs5.getString("anus").equals("-")) {
                        anusPeri = "";
                    } else {
                        anusPeri = rs5.getString("anus") + ", ";
                    }
                    
                    if (rs5.getString("bab").equals("tidak")) {
                        babPeri = "";
                    } else {
                        if (rs5.getString("ket_bab").equals("")) {
                            babPeri = "BAK";
                        } else {
                            babPeri = "BAK (" + rs5.getString("ket_bab") + ")";
                        }
                    }
                    
                    poinQ = anusPeri + babPeri + "\n";
                    
                    //ekstremitas
                    if (rs5.getString("simetris_ekstremitas").equals("tidak")) {
                        simetrisEksPeri = "";
                    } else {
                        simetrisEksPeri = "Simetris, ";
                    }
                    
                    if (rs5.getString("asimetris_ekstremitas").equals("tidak")) {
                        asimetrisEksPeri = "";
                    } else {
                        asimetrisEksPeri = "Asimetris, ";
                    }
                    
                    if (rs5.getString("reflek_moro_positif").equals("tidak")) {
                        reflekMoroPosPeri = "";
                    } else {
                        reflekMoroPosPeri = "Refleks Moro : +, ";
                    }
                    
                    if (rs5.getString("reflek_moro_negatif").equals("tidak")) {
                        reflekMoroNegPeri = "";
                    } else {
                        reflekMoroNegPeri = "Refleks Moro : -, ";
                    }
                    
                    if (rs5.getString("lainya_ekstremitas").equals("tidak")) {
                        lainEksPeri = "";
                    } else {
                        if (rs5.getString("ket_lainya_ekstremitas").equals("")) {
                            lainEksPeri = "Lainnya, ";
                        } else {
                            lainEksPeri = "Lainnya (" + rs5.getString("ket_lainya_ekstremitas") + "), ";
                        }
                    }
                    
                    if (rs5.getString("edema").equals("tidak")) {
                        edemaPeri = "";
                    } else {
                        edemaPeri = "Edema, ";
                    }
                    
                    if (rs5.getString("kelainan_ekstremitas").equals("tidak")) {
                        kelainanEksPeri = "";
                    } else {
                        if (rs5.getString("ket_kelainan_ekstremitas").equals("")) {
                            kelainanEksPeri = "Kelainan";
                        } else {
                            kelainanEksPeri = "Kelainan (" + rs5.getString("ket_kelainan_ekstremitas") + ")";
                        }
                    }
                    
                    poinR = simetrisEksPeri + asimetrisEksPeri + reflekMoroPosPeri + reflekMoroNegPeri + lainEksPeri + edemaPeri + kelainanEksPeri + "\n";
                    
                    //-------------------------------------------------------------------------------------------------------------------
                    if (TPemeriksaanFisik.getText().equals("")) {
                        TPemeriksaanFisik.setText(poinA + "b. Pemeriksaan Umum :\nKulit : " + poinB + "Kepala : " + poinC + "UUB : " + poinDE + "Mata : " + poinF
                                + "THT : " + poinG + "Mulut : " + poinH + "Leher : " + poinI + "Dada : " + poinJ + "Jantung : " + poinK + "Paru : " + poinL
                                + "Perut : " + poinM + "Tali Pusat : " + poinN + "Punggung : " + poinO + "Urogenitalia : " + poinP + "Anus : " + poinQ
                                + "Ekstremitas : " + poinR);
                    } else {
                        TPemeriksaanFisik.setText(TPemeriksaanFisik.getText() + "\n\n" + poinA + "b. Pemeriksaan Umum :\nKulit : " + poinB + "Kepala : " + poinC
                                + "UUB : " + poinDE + "Mata : " + poinF + "THT : " + poinG + "Mulut : " + poinH + "Leher : " + poinI + "Dada : " + poinJ
                                + "Jantung : " + poinK + "Paru : " + poinL + "Perut : " + poinM + "Tali Pusat : " + poinN + "Punggung : " + poinO
                                + "Urogenitalia : " + poinP + "Anus : " + poinQ + "Ekstremitas : " + poinR);
                    }
                    
                    if (TPemeriksaanPenunjang.getText().equals("")) {
                        TPemeriksaanPenunjang.setText(rs5.getString("pemeriksaan_penunjang"));
                    } else {
                        TPemeriksaanPenunjang.setText(TPemeriksaanPenunjang.getText() + "\n\n" + rs5.getString("pemeriksaan_penunjang"));
                    }
                    
                    if (TDiagUtama.getText().equals("")) {
                        TDiagUtama.setText("Diagnosa Kerja : " + rs5.getString("diagnosa_kerja") + "\n\nDiagnosa Banding : " + rs5.getString("diagnosa_banding"));
                    } else {
                        TDiagUtama.setText(TDiagUtama.getText() + "\n\nDiagnosa Kerja : " + rs5.getString("diagnosa_kerja") + "\n\nDiagnosa Banding : " + rs5.getString("diagnosa_banding"));
                    }
                    
                    if (TTerapiPengobatan.getText().equals("")) {
                        TTerapiPengobatan.setText(rs5.getString("pengobatan"));
                    } else {
                        TTerapiPengobatan.setText(TTerapiPengobatan.getText() + "\n\n" + rs5.getString("pengobatan"));
                    }
                    
                    if (THasil.getText().equals("")) {
                        THasil.setText(rs5.getString("rencana"));
                    } else {
                        THasil.setText(THasil.getText() + "\n\n" + rs5.getString("rencana"));
                    }
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
    
    private void tampilAsesmenAnak() {
        emptPemeriksaanFisik();
        try {
            ps4 = koneksi.prepareStatement("select * from asesmen_medik_anak_ranap where no_rawat='" + TNoRW.getText() + "'");
            try {
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    if (TAlasanDirawat.getText().equals("")) {
                        TAlasanDirawat.setText(rs4.getString("keluhan_utama"));
                    } else {
                        TAlasanDirawat.setText(TAlasanDirawat.getText() + "\n\n" + rs4.getString("keluhan_utama"));
                    }
                    
                    if (TRingkasanRiwayat.getText().equals("")) {
                        TRingkasanRiwayat.setText(rs4.getString("riw_penyakit_sekarang"));
                    } else {
                        TRingkasanRiwayat.setText(TRingkasanRiwayat.getText() + "\n\n" + rs4.getString("riw_penyakit_sekarang"));
                    }
                    
                    if (TTerapiPengobatan.getText().equals("")) {
                        TTerapiPengobatan.setText(rs4.getString("rencana"));
                    } else {
                        TTerapiPengobatan.setText(TTerapiPengobatan.getText() + "\n\n" + rs4.getString("rencana"));
                    }
                    
                    if (TDiagUtama.getText().equals("")) {
                        TDiagUtama.setText(rs4.getString("diagnosa_kerja_diagnosa_banding"));
                    } else {
                        TDiagUtama.setText(TDiagUtama.getText() + "\n\n" + rs4.getString("diagnosa_kerja_diagnosa_banding"));
                    }
                    //-------------------------------------------------------------------------------------------------------------------
                    
                    //pemeriksaan fisik
                    if (rs4.getString("keadaan_umum").equals("")) {
                        keadaan_umum = "";
                    } else {
                        keadaan_umum = "Keadaan Umum : " + rs4.getString("keadaan_umum") + ", ";
                    }
                    
                    if (rs4.getString("kesadaran").equals("")) {
                        kesadaran = "";
                    } else {
                        kesadaran = "Kesadaran : " + rs4.getString("kesadaran") + ", ";
                    }
                    
                    gcs = "GCS : E " + rs4.getString("gcs_e") + ", V : " + rs4.getString("gcs_v") + ", M : " + rs4.getString("gcs_m");
                    poinA = keadaan_umum + kesadaran + gcs + "\n";
                    
                    if (rs4.getString("tensi").equals("")) {
                        tensi = "";
                    } else {
                        tensi = "Tensi : " + rs4.getString("tensi") + " mmHg, ";
                    }
                    
                    if (rs4.getString("suhu").equals("")) {
                        suhu = "";
                    } else {
                        suhu = "Suhu : " + rs4.getString("suhu") + " °C";
                    }
                    
                    if (rs4.getString("nadi").equals("")) {
                        nadi = "";
                    } else {
                        nadi = "Nadi : " + rs4.getString("nadi") + " x/menit, ";
                    }
                    
                    if (rs4.getString("kualitas").equals("")) {
                        kualitas = "";
                    } else {
                        kualitas = "Kualitas : " + rs4.getString("kualitas") + ", ";
                    }
                    
                    if (rs4.getString("napas").equals("")) {
                        napas = "";
                    } else {
                        napas = "Napas : " + rs4.getString("napas") + " x/menit ";
                    }
                    
                    if (rs4.getString("bb").equals("")) {
                        bb = "";
                    } else {
                        bb = "BB : " + rs4.getString("bb") + " Kg. ";
                    }
                    
                    if (rs4.getString("bb_persen").equals("")) {
                        bbpersen = "";
                    } else {
                        bbpersen = "(" + rs4.getString("bb_persen") + " % BB/U), ";
                    }
                    
                    if (rs4.getString("bbpbtb_persen").equals("")) {
                        bbpbpersen = "";
                    } else {
                        bbpbpersen = "BB/PB-TB : " + rs4.getString("bbpbtb_persen") + " % BB/PB-TB, ";
                    }
                    
                    if (rs4.getString("pbtb").equals("")) {
                        pb = "";
                    } else {
                        pb = "PB/TB : " + rs4.getString("pbtb") + " Cm. ";
                    }
                    
                    if (rs4.getString("pbtb_persen").equals("")) {
                        pbpersen = "";
                    } else {
                        pbpersen = "(" + rs4.getString("pbtb_persen") + " % PB-TB/U) ";
                    }
                    
                    if (rs4.getString("lla").equals("")) {
                        lla = "";
                    } else {
                        lla = "LLA : " + rs4.getString("lla") + " Cm. ";
                    }
                    
                    if (rs4.getString("lk").equals("")) {
                        lk = "";
                    } else {
                        lk = "LK : " + rs4.getString("lk") + " Cm. ";
                    }
                    
                    poinB = "Pengukuran Tanda Vital : " + tensi + suhu + "\n" + nadi + kualitas + napas + "\n"
                            + bb + bbpersen + pb + pbpersen + "\n"
                            + bbpbpersen + lla + lk + "\n";
                    
                    if (rs4.getString("turgor").equals("tidak")) {
                        turgor = "";
                    } else {
                        turgor = "Turgor, ";
                    }
                    
                    if (rs4.getString("sianosis").equals("tidak")) {
                        sianosis = "";
                    } else {
                        sianosis = "Sianosis, ";
                    }
                    
                    if (rs4.getString("perdarahan_kulit").equals("tidak")) {
                        perdarahan_kulit = "";
                    } else {
                        perdarahan_kulit = "Perdarahan, ";
                    }
                    
                    if (rs4.getString("ikterus").equals("tidak")) {
                        ikterus = "";
                        kalimat_ikterus = "";
                    } else {
                        ikterus = "Ikterus : " + rs4.getString("kalimat_ikterus") + ", ";
                    }
                    
                    if (rs4.getString("hematoma").equals("tidak")) {
                        hematoma = "";
                    } else {
                        hematoma = "Hematoma, ";
                    }
                    
                    if (rs4.getString("sklerema").equals("tidak")) {
                        sklerema = "";
                    } else {
                        sklerema = "Sklerema, ";
                    }
                    
                    if (rs4.getString("kutis").equals("tidak")) {
                        kutis = "";
                    } else {
                        kutis = "Kutis, ";
                    }
                    
                    if (rs4.getString("marmorata").equals("tidak")) {
                        marmorata = "";
                    } else {
                        marmorata = "Marmorata, ";
                    }
                    
                    if (rs4.getString("kalimat_lainya_kulit").equals("")) {
                        lainya_kulit = "";
                    } else {
                        lainya_kulit = "Lainnya : " + rs4.getString("kalimat_lainya_kulit") + " ";
                    }
                    
                    poinC = "Kulit : " + turgor + sianosis + perdarahan_kulit + ikterus + hematoma
                            + sklerema + kutis + marmorata + lainya_kulit + "\n";
                    
                    if (rs4.getString("kepala_bentuk").equals("")) {
                        bentuk = "";
                    } else {
                        bentuk = "Bentuk : " + rs4.getString("kepala_bentuk") + ", ";
                    }
                    
                    if (rs4.getString("kepala_rambut").equals("")) {
                        rambut = "";
                    } else {
                        rambut = "Rambut : " + rs4.getString("kepala_rambut") + ", ";
                    }
                    
                    if (rs4.getString("kepala_mata").equals("")) {
                        mata = "";
                    } else {
                        mata = "Mata : " + rs4.getString("kepala_mata") + ", ";
                    }
                    
                    if (rs4.getString("kepala_telinga").equals("")) {
                        telinga = "";
                    } else {
                        telinga = "Telinga : " + rs4.getString("kepala_telinga") + ", ";
                    }
                    
                    if (rs4.getString("kepala_hidung").equals("")) {
                        hidung = "";
                    } else {
                        hidung = "Hidung : " + rs4.getString("kepala_hidung") + ", ";
                    }
                    
                    if (rs4.getString("kepala_mulut").equals("")) {
                        mulut = "";
                    } else {
                        mulut = "Mulut : " + rs4.getString("kepala_mulut") + ", ";
                    }
                    
                    if (rs4.getString("kepala_lidah").equals("")) {
                        lidah = "";
                    } else {
                        lidah = "Lidah : " + rs4.getString("kepala_lidah") + ", ";
                    }
                    
                    if (rs4.getString("kepala_faring").equals("")) {
                        faring = "";
                    } else {
                        faring = "Faring : " + rs4.getString("kepala_faring");
                    }
                    
                    if (rs4.getString("leher").equals("")) {
                        leher = "";
                    } else {
                        leher = "Leher : " + rs4.getString("leher");
                    }
                    
                    poinDE = "Kepala : " + bentuk + rambut + mata + telinga + hidung + mulut + lidah + faring + "\n" + leher + "\n";
                    
                    if (rs4.getString("dada_bentuk").equals("")) {
                        bentuk_dada = "";
                    } else {
                        bentuk_dada = "Bentuk : " + rs4.getString("dada_bentuk") + ", ";
                    }
                    
                    if (rs4.getString("dada_retraksi").equals("")) {
                        retraksi_dada = "";
                    } else {
                        retraksi_dada = "Retraksi : " + rs4.getString("dada_retraksi") + "\n";
                    }
                    
                    if (rs4.getString("jantung_inspeksi").equals("")) {
                        inspeksi_jan = "";
                    } else {
                        inspeksi_jan = "- Inspeksi : " + rs4.getString("jantung_inspeksi") + "\n";
                    }
                    
                    if (rs4.getString("jantung_palpasi").equals("")) {
                        palpasi_jan = "";
                    } else {
                        palpasi_jan = "- Palpasi : " + rs4.getString("jantung_palpasi") + "\n";
                    }
                    
                    if (rs4.getString("jantung_perkusi").equals("")) {
                        perkusi_jan = "";
                    } else {
                        perkusi_jan = "- Perkusi : " + rs4.getString("jantung_perkusi") + "\n";
                    }
                    
                    if (rs4.getString("jantung_auskultasi").equals("")) {
                        auskultasi_jan = "";
                    } else {
                        auskultasi_jan = "- Auskultasi : " + rs4.getString("jantung_auskultasi") + "\n";
                    }
                    
                    if (rs4.getString("paru_inspeksi").equals("")) {
                        inspeksi_par = "";
                    } else {
                        inspeksi_par = "- Inspeksi : " + rs4.getString("paru_inspeksi") + "\n";
                    }
                    
                    if (rs4.getString("paru_palpasi").equals("")) {
                        palpasi_par = "";
                    } else {
                        palpasi_par = "- Palpasi : " + rs4.getString("paru_palpasi") + "\n";
                    }
                    
                    if (rs4.getString("paru_perkusi").equals("")) {
                        perkusi_par = "";
                    } else {
                        perkusi_par = "- Perkusi : " + rs4.getString("paru_perkusi") + "\n";
                    }
                    
                    if (rs4.getString("paru_auskultasi").equals("")) {
                        auskultasi_par = "";
                    } else {
                        auskultasi_par = "- Auskultasi : " + rs4.getString("paru_auskultasi") + "\n";
                    }
                    
                    poinF = "Dinding Dada :\n" + bentuk_dada + retraksi_dada
                            + "Jantung :\n" + inspeksi_jan + palpasi_jan + perkusi_jan + auskultasi_jan
                            + "Paru :\n" + inspeksi_par + palpasi_par + perkusi_par + auskultasi_par;
                    
                    if (rs4.getString("perut_inspeksi").equals("")) {
                        inspeksi_per = "";
                    } else {
                        inspeksi_per = "- Inspeksi : " + rs4.getString("perut_inspeksi") + "\n";
                    }
                    
                    if (rs4.getString("perut_palpasi").equals("")) {
                        palpasi_per = "";
                    } else {
                        palpasi_per = "- Palpasi : " + rs4.getString("perut_palpasi") + "\n";
                    }
                    
                    if (rs4.getString("perut_perkusi").equals("")) {
                        perkusi_per = "";
                    } else {
                        perkusi_per = "- Perkusi : " + rs4.getString("perut_perkusi") + "\n";
                    }
                    
                    if (rs4.getString("perut_auskultasi").equals("")) {
                        auskultasi_per = "";
                    } else {
                        auskultasi_per = "- Auskultasi : " + rs4.getString("perut_auskultasi") + "\n";
                    }
                    
                    poinG = "Perut : \n" + inspeksi_per + palpasi_per + perkusi_per + auskultasi_per;
                    
                    if (rs4.getString("ekstremitas_umum").equals("")) {
                        umum = "";
                    } else {
                        umum = "- Umum : " + rs4.getString("ekstremitas_umum") + "\n";
                    }
                    
                    if (rs4.getString("ekstremitas_neurologis").equals("")) {
                        neurologis = "";
                    } else {
                        neurologis = "- Neurologis : " + rs4.getString("ekstremitas_neurologis") + "\n";
                    }
                    
                    poinH = "Ekstremitas : \n" + umum + neurologis;
                    
                    if (rs4.getString("susunan_saraf_pusat").equals("")) {
                        susunan = "";
                    } else {
                        susunan = "Susunan Saraf Pusat : " + rs4.getString("susunan_saraf_pusat") + "\n";
                    }
                    
                    if (rs4.getString("tanda_meningen").equals("")) {
                        tanda = "";
                    } else {
                        tanda = "Tanda-tanda Meningen : " + rs4.getString("tanda_meningen") + "\n";
                    }
                    
                    if (rs4.getString("genitalia").equals("")) {
                        genitalia = "";
                    } else {
                        genitalia = "Genitalia : " + rs4.getString("genitalia") + "\n";
                    }
                    
                    if (rs4.getString("anus").equals("")) {
                        anus = "";
                    } else {
                        anus = "Anus : " + rs4.getString("anus");
                    }
                    //-------------------------------------------------------------------------------------------------------------------
                    
                    if (TPemeriksaanFisik.getText().equals("")) {
                        TPemeriksaanFisik.setText(poinA + poinB + "\n" + poinC + poinDE + "\n" + poinF + "\n" + poinG
                                + "\n" + poinH + "\n" + susunan + tanda + genitalia + anus);
                    } else {
                        TPemeriksaanFisik.setText(TPemeriksaanFisik.getText() + "\n\n" + poinA + poinB + "\n" + poinC
                                + poinDE + "\n" + poinF + "\n" + poinG + "\n" + poinH + "\n" + susunan + tanda + genitalia + anus);
                    }
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
    
    private void emptPemeriksaanFisik() {
        //pemeriksaan fisik dewasa
        anemis = "";
        ikterik = "";
        pupil = "";
        dia_kanan = "";
        dia_kiri = "";
        udem_palpe = "";
        tonsil = "";
        faring = "";
        satur = "";
        lidah = "";
        bibir = "";
        jvp = "";
        limfe = "";
        kuduk = "";
        thorak = "";
        cor = "";
        reguler = "";
        ireguler = "";
        lain1 = "";
        nafas = "";
        ronci = "";
        whezing = "";
        disten = "";
        meteo = "";
        peris = "";
        asites = "";
        nyeri = "";
        hepar = "";
        lien = "";
        extrem = "";
        udem = "";
        lain2 = "";
        
        //pemeriksaan fisik anak
        poinA = "";
        keadaan_umum = "";
        kesadaran = "";
        gcs = "";
        tensi = "";
        suhu = "";
        nadi = "";
        kualitas = "";
        napas = "";
        bb = "";
        bbpersen = "";
        bbpbpersen = "";
        pb = "";
        pbpersen = "";
        lla = "";
        lk = "";
        poinB = "";
        turgor = "";
        sianosis = "";
        perdarahan_kulit = "";
        ikterus = "";
        kalimat_ikterus = "";
        hematoma = "";
        sklerema = "";
        kutis = "";
        marmorata = "";
        lainya_kulit = "";
        poinC = "";
        bentuk = "";
        rambut = "";
        mata = "";
        telinga = "";
        hidung = "";
        mulut = "";
        poinDE = "";
        leher = "";
        bentuk_dada = "";
        retraksi_dada = "";
        inspeksi_jan = "";
        palpasi_jan = "";
        perkusi_jan = "";
        auskultasi_jan = "";
        inspeksi_par = "";
        palpasi_par = "";
        perkusi_par = "";
        auskultasi_par = "";
        poinF = "";
        inspeksi_per = "";
        palpasi_per = "";
        perkusi_per = "";
        auskultasi_per = "";
        poinG = "";
        umum = "";
        neurologis = "";
        poinH = "";
        susunan = "";
        tanda = "";
        genitalia = "";
        anus = "";
        
        //pemeriksaan fisik perinatologi        
        turgorPeri = "";
        sianosisKulitPeri = "";
        perdarahanKulitPeri = "";
        ikterusPosPeri = "";
        ikterusNegPeri = "";
        kramerPeri = "";
        hematomaPeri = "";
        sklerePeri = "";
        kutisPeri = "";
        lainKulitPeri = "";
        simetrisKepalaPeri = "";
        asimetrisKepalaPeri = "";
        cepalPeri = "";
        caputPeri = "";
        anenPeri = "";
        microsPeri = "";
        hidroPeri = "";
        lainKepalaPeri = "";
        datarPeri = "";
        cembungPeri = "";
        cekungPeri = "";
        lainUubPeri = "";
        normalMataPeri = "";
        anemiaPeri = "";
        ikterusPeri = "";
        sekretMataPeri = "";
        LainMataPeri = "";
        normalThtPeri = "";
        nchPeri = "";
        sianosisThtPeri = "";
        sekretThtPeri = "";
        lainThtPeri = "";
        normalMulutPeri = "";
        labioSPeri = "";
        labioPPeri = "";
        labioGPeri = "";
        mukosaPeri = "";
        reflekPeri = "";
        lainMulutPeri = "";
        normalLeherPeri = "";
        tortiPeri = "";
        benjolKananPeri = "";
        benjolKiriPeri = "";
        lainLeherPeri = "";
        simetrisDadaPeri = "";
        tidakSimetrisPeri = "";
        retraksiPosPeri = "";
        retraksiNegPeri = "";
        sesakPeri = "";
        merintihPeri = "";
        sianosisDadaPeri = "";
        lainDadaPeri = "";
        bjPeri = "";
        murniPeri = "";
        tidakMurniPeri = "";
        regulerPeri = "";
        tidakRegulerPeri = "";
        bunyiPeri = "";
        vesikulerPeri = "";
        ronchiPeri = "";
        wezingPeri = "";
        stridorPeri = "";
        lainParuPeri = "";
        supelPeri = "";
        distenPeri = "";
        bisingPeri = "";
        heparPeri = "";
        limpaPeri = "";
        nyeriPeri = "";
        masaPosPeri = "";
        masaNegPeri = "";
        ukPeri = "";
        lokasiPeri = "";
        segarPeri = "";
        layuPeri = "";
        lainTaliPeri = "";
        normalPunggungPeri = "";
        spinaPeri = "";
        gibusPeri = "";
        lainPunggungPeri = "";
        sexPeri = "";
        kelainanUroPeri = "";
        bakPeri = "";
        babPeri = "";
        simetrisEksPeri = "";
        asimetrisEksPeri = "";
        reflekMoroPosPeri = "";
        reflekMoroNegPeri = "";
        lainEksPeri = "";
        edemaPeri = "";
        kelainanEksPeri = "";
        kondisiLahir = "";
        ketAS = "";
        gerak = "";
        tangis = "";
        warnaKulit = "";
        hrPeri = "";
        suhuPeri = "";
        rrPeri = "";
        satuPeri = "";
        capilari = "";
        bblPeri = "";
        pbPeri = "";
        lkPeri = "";
        ldPeri = "";
        lpPeri = "";
        llaPeri = "";
        poinI = "";
        poinJ = "";
        poinK = "";
        poinL = "";
        poinM = "";
        poinN = "";
        poinO = "";
        poinP = "";
        poinQ = "";
        poinR = "";
        ketRetraksi = "";
        anusPeri = "";
    }

    private void tampilPasien() {
        Valid.tabelKosong(tabMode1);
        try {
            ps2 = koneksi.prepareStatement("select rp.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                    + "if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(rp.tgl_registrasi,'%d-%m-%Y') tglmsk, "
                    + "if(ki.stts_pulang not in ('-','Pindah Kamar'),date_format(ki.tgl_keluar,'%d-%m-%Y'),'-') tglplg, "
                    + "ki.stts_pulang, ifnull(d2.nm_dokter,'-') dpjp, if(r.no_rawat is null,'Belum Ada','Sudah Ada') ringkasanplg, "
                    + "pj.png_jawab, d1.nm_dokter dr_pengirim from reg_periksa rp "
                    + "inner join kamar_inap ki on ki.no_rawat=rp.no_rawat inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join penjab pj on pj.kd_pj=rp.kd_pj inner join dokter d1 on d1.kd_dokter=rp.kd_dokter "
                    + "left join ringkasan_pulang_ranap r on r.no_rawat=rp.no_rawat left join dpjp_ranap dr on dr.no_rawat=ki.no_rawat "
                    + "left join dokter d2 on d2.kd_dokter=dr.kd_dokter where "
                    + "rp.no_rawat like ? or "
                    + "p.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or "
                    + "if(p.jk='L','Laki-laki','Perempuan') like ? or "
                    + "ki.stts_pulang like ? or "
                    + "ifnull(d2.nm_dokter,'-') like ? or "
                    + "if(r.no_rawat is null,'Belum Ada','Sudah Ada') like ? order by rp.tgl_registrasi desc limit 100");
            try {
                ps2.setString(1, "%" + TCari1.getText().trim() + "%");
                ps2.setString(2, "%" + TCari1.getText().trim() + "%");
                ps2.setString(3, "%" + TCari1.getText().trim() + "%");
                ps2.setString(4, "%" + TCari1.getText().trim() + "%");
                ps2.setString(5, "%" + TCari1.getText().trim() + "%");
                ps2.setString(6, "%" + TCari1.getText().trim() + "%");
                ps2.setString(7, "%" + TCari1.getText().trim() + "%");
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode1.addRow(new String[]{                        
                        rs2.getString("no_rawat"),
                        rs2.getString("no_rkm_medis"),
                        rs2.getString("nm_pasien"),
                        rs2.getString("tgllahir"),
                        rs2.getString("jenkel"),
                        rs2.getString("tglmsk"),
                        rs2.getString("tglplg"),
                        rs2.getString("stts_pulang"),
                        rs2.getString("dpjp"),
                        Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                        + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + rs2.getString("no_rawat") + "' "
                        + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"),
                        rs2.getString("ringkasanplg"),
                        rs2.getString("png_jawab"),
                        rs2.getString("dr_pengirim"),
                        Sequel.cariIsi("select b.nm_gedung from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                        + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + rs2.getString("no_rawat") + "' "
                        + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1")
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void getDataPasien() {
        nmgedung = "";
        if (tbPasien.getSelectedRow() != -1) {
            TNoRW.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString());
            TNoRM.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 1).toString());
            TNmPasien.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 2).toString());
            TTglLhr.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 3).toString());
            TJK.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 4).toString());
            TTglMsk.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 5).toString());
            TTglPulang.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 6).toString());
            TRuangrawat.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 9).toString());
            TCaraBayar.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 11).toString());
            Tdpjp.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 8).toString());
            TNmDokter.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 12).toString());
            nmgedung = tbPasien.getValueAt(tbPasien.getSelectedRow(), 13).toString();
            
            //cek no.reg SITB
            noreg.setText(Sequel.cariIsi("select ifnull(id_tb_03,'') from nomor_reg_tb where no_rkm_medis='" + TNoRM.getText() + "'"));
            if (nmgedung.equals("AL-HAKIM/PARU")) {
                noreg.setEnabled(true);
            } else {
                noreg.setEnabled(false);
            }

            i = 0;
            i = noreg.getText().length();
            jml_noreg.setText("Jumlah No. Reg. TB : " + i + " digit");
        }
    }
    
    private void cekDpjp() {
        if (Tdpjp.getText().equals("-")) {
            x = JOptionPane.showConfirmDialog(rootPane, "DPJP pasien ini belum ditentukan, apakah DPJP nya akan dipilih dulu..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                WindowDPJPranap.setSize(615, 110);
                WindowDPJPranap.setLocationRelativeTo(internalFrame1);
                WindowDPJPranap.setVisible(true);
                kddpjp.setText(Sequel.cariIsi("select ifnull(kd_dokter,'') from dpjp_ranap where no_rawat='" + TNoRW.getText() + "'"));
                if (kddpjp.getText().equals("")) {
                    nmdpjp.setText("");
                } else {
                    nmdpjp.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddpjp.getText() + "'"));
                }
                btnDPJP.requestFocus();
            }
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
                    + "c.flag_hapus='tidak' and c.status='ranap' and c.no_rawat='" + TNoRW.getText() + "' order by c.tgl_cppt, c.jam_cppt");
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
            ps3 = koneksi.prepareStatement("select pg1.nama ptgs, date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i') jamlapor, "
                    + "pg2.nama dpjp, date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i') jamverif from cppt_konfirmasi_terapi ck "
                    + "inner join pegawai pg1 on pg1.nik=ck.nip_petugas_konfir inner join pegawai pg2 on pg2.nik=ck.nip_dpjp_konfir where "
                    + "ck.no_rawat = '" + norwt + "' and ck.tgl_cppt='" + tglcppt + "' and ck.cppt_shift='" + sift + "' "
                    + "and ck.jam_cppt='" + jamcppt + "' order by ck.waktu_simpan");
            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    if (dataKonfirmasi.equals("")) {
                        dataKonfirmasi = "Tgl. Lapor : " + rs3.getString("tgllapor") + ", Jam : " + rs3.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs3.getString("tglverif") + ", Jam : " + rs3.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs3.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs3.getString("dpjp");
                    } else {
                        dataKonfirmasi = dataKonfirmasi + "\n\nTgl. Lapor : " + rs3.getString("tgllapor") + ", Jam : " + rs3.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs3.getString("tglverif") + ", Jam : " + rs3.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs3.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs3.getString("dpjp");
                    }
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
    
    public void mengunggahFile(String namaFile, String enkodePDF, String noRawat, String nik, String kode,
            String pwd, String stts, String ptgs, String rm) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson12
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"kirim_berkas\"},"
                    + "\"data\": {"
                    + "\"no_rawat\": \"" + noRawat + "\","
                    + "\"nik\": \"" + nik + "\","
                    + "\"password\": \"" + pwd + "\","
                    + "\"jns_dokumen\": \"" + kode + "\","
                    + "\"nama_file\": \"" + namaFile + "\","
                    + "\"status_rawat\": \"" + stts + "\","
                    + "\"petugas\": \"" + ptgs + "\","
                    + "\"nomr\": \"" + rm + "\","
                    + "\"file_base64\": \"" + enkodePDF + "\""
                    + "}}";
            
            Properties prop = new Properties();
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            host_port = Sequel.decXML(prop.getProperty("HOSTport"), prop.getProperty("KEY"));
            System.out.println("JSON : " + requestJson12);
            requestEntity = new HttpEntity(requestJson12, headers);
//            stringbalik = getRest().exchange("https://sirsraza.banjarkab.go.id/ws-tte-simrs/kirim.php", HttpMethod.POST, requestEntity, String.class).getBody();
            stringbalik = getRest().exchange("http://" + host_port + "/ws-tte-simrs/kirim.php", HttpMethod.POST, requestEntity, String.class).getBody();
//            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);
//            JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            if (root.path("status").asBoolean() == true) {
                JOptionPane.showMessageDialog(null, root.path("msg").asText());
//                System.out.println("Pesan Status Unggah File : " + root.path("msg").asText());
            } else {
                JOptionPane.showMessageDialog(null, root.path("msg").asText());
//                System.out.println("Pesan Status Unggah File : " + root.path("msg").asText());
            }
            Sequel.menyimpan("log_tte", "'" + noRawat + "',CURRENT_TIMESTAMP,'" + kode + "','" + root.path("msg").asText() + "'");
        } catch (Exception erornya) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan (" + erornya + "), silakan ulangi lagi,..!!");
//            System.out.println("Notifikasi : " + erornya);
            Sequel.menyimpan("log_tte", "'" + noRawat + "',CURRENT_TIMESTAMP,'" + kode + "','" + erornya + "'");
//            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
//                JOptionPane.showMessageDialog(null, erornya);                
//            }
        }
    }

    public RestTemplate getRest() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers = {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }
            }
        };
        sslContext.init(null, trustManagers, new SecureRandom());
        SSLSocketFactory sslFactory = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme = new Scheme("https", 443, sslFactory);
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        return new RestTemplate(factory);
    }
    
    private void tampilLIS() {
        Valid.tabelKosong(tabModeLis);
        try {
            psLab1 = koneksi.prepareStatement("SELECT CONCAT(p.no_rkm_medis,' - ',p.nm_pasien) pasien, lr.no_lab nolis, "
                    + "IF(lh.no_lab IS NULL,'Petugas Lab. belum mengirim hasil','Hasil pemeriksaan Lab. bisa dibaca') hasil_lab, "
                    + "IFNULL(lh.no_lab,'') cekOK, pl.no_rawat, IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%d-%m-%Y'),DATE_FORMAT(pl.tgl_periksa,'%d-%m-%Y')) tgl, "
                    + "IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%H:%i:%s'),DATE_FORMAT(pl.jam,'%H:%i:%s')) jam, "
                    + "IFNULL(lh.dokter_pengirim,'') dokter_pengirim, if(rp.status_lanjut='Ralan','R. Jalan','R. Inap') stts_lnjut FROM lis_reg lr "
                    + "LEFT JOIN reg_periksa rp ON rp.no_rawat=lr.no_rawat LEFT JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                    + "LEFT JOIN periksa_lab pl ON pl.no_rawat=lr.no_rawat LEFT JOIN lis_hasil_data_pasien lh ON lh.no_lab=lr.no_lab WHERE "
                    + "rp.no_rkm_medis like ? and lr.no_lab like ? or "
                    + "rp.no_rkm_medis like ? and IF(lh.no_lab IS NULL,'Petugas Lab. belum mengirim hasil','Hasil Lab. bisa dicetak') like ? or "
                    + "rp.no_rkm_medis like ? and pl.no_rawat like ? or "
                    + "rp.no_rkm_medis like ? and IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%d-%m-%Y'),DATE_FORMAT(pl.tgl_periksa,'%d-%m-%Y')) like ? or "
                    + "rp.no_rkm_medis like ? and IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%H:%i:%s'),DATE_FORMAT(pl.jam,'%H:%i:%s')) like ? or "
                    + "rp.no_rkm_medis like ? and lh.dokter_pengirim like ? or "
                    + "rp.no_rkm_medis like ? and if(rp.status_lanjut='Ralan','R. Jalan','R. Inap') like ? "
                    + "GROUP BY lr.no_lab ORDER BY rp.no_rawat desc, tgl DESC, jam DESC, hasil_lab limit " + cmbHlm.getSelectedItem().toString() + "");
            try {
                psLab1.setString(1, "%" + TNoRM.getText() + "%");
                psLab1.setString(2, "%" + TCari3.getText().trim() + "%");
                psLab1.setString(3, "%" + TNoRM.getText() + "%");
                psLab1.setString(4, "%" + TCari3.getText().trim() + "%");
                psLab1.setString(5, "%" + TNoRM.getText() + "%");
                psLab1.setString(6, "%" + TCari3.getText().trim() + "%");
                psLab1.setString(7, "%" + TNoRM.getText() + "%");
                psLab1.setString(8, "%" + TCari3.getText().trim() + "%");
                psLab1.setString(9, "%" + TNoRM.getText() + "%");
                psLab1.setString(10, "%" + TCari3.getText().trim() + "%");                
                psLab1.setString(11, "%" + TNoRM.getText() + "%");
                psLab1.setString(12, "%" + TCari3.getText().trim() + "%");
                psLab1.setString(13, "%" + TNoRM.getText() + "%");
                psLab1.setString(14, "%" + TCari3.getText().trim() + "%");
                rsLab1 = psLab1.executeQuery();
                while (rsLab1.next()) {
                    tabModeLis.addRow(new Object[]{
                        rsLab1.getString("pasien"),
                        rsLab1.getString("nolis"),
                        rsLab1.getString("hasil_lab"),
                        rsLab1.getString("cekOK"),
                        rsLab1.getString("no_rawat"),
                        rsLab1.getString("tgl"),
                        rsLab1.getString("jam"),
                        rsLab1.getString("dokter_pengirim"),
                        rsLab1.getString("stts_lnjut")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsLab1 != null) {
                    rsLab1.close();
                }
                if (psLab1 != null) {
                    psLab1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataLis() {
        Valid.tabelKosong(tabModeHasilLab);
        noLIS = "";
        cekLIS = "";
        ketLIS = "";
        tglLIS = "";
        jamLIS = "";
        drpengirim = "";
        tglPeriksaLIS = "";
        jamPeriksaLIS = "";

        if (tbLIS.getSelectedRow() != -1) {
            noLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 1).toString();
            ketLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 2).toString();
            cekLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 3).toString();
            tglLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 5).toString();
            jamLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 6).toString();
            drpengirim = tbLIS.getValueAt(tbLIS.getSelectedRow(), 7).toString();
            tglPeriksaLIS = Sequel.cariIsi("SELECT DATE(waktu_reg_lab) FROM lis_hasil_data_pasien WHERE no_lab='" + noLIS + "'");
            jamPeriksaLIS = Sequel.cariIsi("SELECT TIME(waktu_reg_lab) FROM lis_hasil_data_pasien WHERE no_lab='" + noLIS + "'");
            tampilHasil(noLIS);
            tampilPembaca(tbLIS.getValueAt(tbLIS.getSelectedRow(), 4).toString(), noLIS, 
                    Valid.SetTgl(tbLIS.getValueAt(tbLIS.getSelectedRow(), 5).toString() + ""), jamLIS);
        }
    }
    
    private void tampilHasil(String nolisDipilih) {
        try {
            Sequel.queryu("delete from temporary_lis");
            Valid.tabelKosong(tabModeHasilLab);
            psLabA.setString(1, nolisDipilih);
            rsLabA = psLabA.executeQuery();
            while (rsLabA.next()) {    
                Sequel.menyimpan("temporary_lis", "'" + rsLabA.getString("kategori_pemeriksaan_nama") + "','','','',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Kategori Pemeriksaan");
                tabModeHasilLab.addRow(new Object[]{false, rsLabA.getString("kategori_pemeriksaan_nama"), "", "", "", "", "", ""});

                psLabB.setString(1, nolisDipilih);
                psLabB.setString(2, rsLabA.getString("kategori_pemeriksaan_nama"));
                rsLabB = psLabB.executeQuery();
                while (rsLabB.next()) {
                    Sequel.menyimpan("temporary_lis", "'   " + rsLabB.getString("sub_kategori_pemeriksaan_nama") + "','','','',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Sub Kategori Pemeriksaan");
                    tabModeHasilLab.addRow(new Object[]{false, "   "+rsLabB.getString("sub_kategori_pemeriksaan_nama"), "", "", "", "", "", ""});
                    
                    psLabC.setString(1, nolisDipilih);
                    psLabC.setString(2, rsLabB.getString("sub_kategori_pemeriksaan_nama"));
                    psLabC.setString(3, rsLabA.getString("kategori_pemeriksaan_nama"));
                    
                    rsLabC = psLabC.executeQuery();
                    while (rsLabC.next()) {
                        Sequel.menyimpan("temporary_lis", "'     " + Valid.mysql_real_escape_string(rsLabC.getString("pemeriksaan_nama")) + "',"
                                + "'" + Valid.mysql_real_escape_string(rsLabC.getString("nilai_hasil")) + "',"
                                + "'" + Valid.mysql_real_escape_string(rsLabC.getString("satuan")) + "',"
                                + "'" + rsLabC.getString("flag_kode") + "',"
                                + "'" + Valid.mysql_real_escape_string(rsLabC.getString("nilai_rujukan")) + "',"
                                + "'" + rsLabC.getString("wkt_selesai") + "',"
                                + "'" + Valid.mysql_real_escape_string(rsLabC.getString("metode")) + "',"
                                + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Hasil Pemeriksaan");
                        tabModeHasilLab.addRow(new Object[]{
                            false,
                            "     " + rsLabC.getString("pemeriksaan_nama"),
                            rsLabC.getString("nilai_hasil"),
                            rsLabC.getString("satuan"),
                            rsLabC.getString("flag_kode"),
                            rsLabC.getString("nilai_rujukan"),
                            rsLabC.getString("wkt_selesai"),
                            rsLabC.getString("metode")
                        });
                        
                        if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "' and status='1' "
                                + "and (nm_dokter like '%dr.%' or nm_dokter like '%drg.%')") > 0) {
                            Sequel.menyimpanIgnore("pembaca_hasil_lab",
                                    "'" + tbLIS.getValueAt(tbLIS.getSelectedRow(), 4).toString() + "',"
                                    + "'" + akses.getkode() + "',"
                                    + "'" + tbLIS.getValueAt(tbLIS.getSelectedRow(), 1).toString() + "',"
                                    + "'" + Valid.SetTgl(tbLIS.getValueAt(tbLIS.getSelectedRow(), 5).toString() + "") + "',"
                                    + "'" + tbLIS.getValueAt(tbLIS.getSelectedRow(), 6).toString() + "',"
                                    + "'" + Sequel.cariIsi("select now()") + "'", "Pembaca Hasil Lab.");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void tampilItem() {
    Valid.tabelKosong(tabModeRad);
        try {
            psRad = koneksi.prepareStatement("select p.no_rkm_medis, p.nm_pasien, if(rp.status_lanjut='ralan','R. Jalan','R. Inap') jns_rwt, j.nm_perawatan, "
                    + "date_format(pr.tgl_periksa,'%d-%m-%Y') tglnya, pr.jam, pr.no_rawat, pr.kd_jenis_prw, pr.tgl_periksa, pg.nama dr_perujuk FROM periksa_radiologi pr "
                    + "inner join jns_perawatan_radiologi j on j.kd_jenis_prw=pr.kd_jenis_prw inner join reg_periksa rp on rp.no_rawat=pr.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=pr.dokter_perujuk where "
                    + "rp.no_rkm_medis like ? and if(rp.status_lanjut='ralan','R. Jalan','R. Inap') like ? or "
                    + "rp.no_rkm_medis like ? and j.nm_perawatan like ? or "
                    + "rp.no_rkm_medis like ? and pg.nama like ? or "
                    + "rp.no_rkm_medis like ? and date_format(pr.tgl_periksa,'%d-%m-%Y') like ? or "
                    + "rp.no_rkm_medis like ? and pr.no_rawat like ? "
                    + "ORDER BY rp.no_rawat desc, pr.tgl_periksa desc, pr.jam desc limit " + cmbHlm1.getSelectedItem().toString() + "");

            try {
                psRad.setString(1, "%" + TNoRM.getText() + "%");
                psRad.setString(2, "%" + TCari4.getText().trim() + "%");
                psRad.setString(3, "%" + TNoRM.getText() + "%");
                psRad.setString(4, "%" + TCari4.getText().trim() + "%");
                psRad.setString(5, "%" + TNoRM.getText() + "%");
                psRad.setString(6, "%" + TCari4.getText().trim() + "%");
                psRad.setString(7, "%" + TNoRM.getText() + "%");
                psRad.setString(8, "%" + TCari4.getText().trim() + "%");
                psRad.setString(9, "%" + TNoRM.getText() + "%");
                psRad.setString(10, "%" + TCari4.getText().trim() + "%");
                rsRad = psRad.executeQuery();                
                while (rsRad.next()) {
                    tabModeRad.addRow(new Object[]{                        
                        rsRad.getString("no_rkm_medis"),
                        rsRad.getString("nm_pasien"),
                        rsRad.getString("jns_rwt"),                        
                        rsRad.getString("nm_perawatan"),
                        rsRad.getString("dr_perujuk"),
                        rsRad.getString("tglnya"),
                        rsRad.getString("jam"),
                        rsRad.getString("no_rawat"),
                        rsRad.getString("kd_jenis_prw"),
                        rsRad.getString("tgl_periksa")
                    });
                }                
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (rsRad != null) {
                    rsRad.close();
                }
                if (psRad != null) {
                    psRad.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataRadiologi() {
        kdItem = "";
        norawat = "";
        tglhasil = "";
        jamhasil = "";
        nmpemeriksaan = "";

        if (tbRadiologi.getSelectedRow() != -1) {
            nmpemeriksaan = tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 3).toString();
            jamhasil = tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 6).toString();
            norawat = tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 7).toString();
            kdItem = tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 8).toString();
            tglhasil = tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 9).toString();            
            deskripsiHasil();
            tampilPembacaRad(norawat, kdItem, tglhasil, jamhasil);
        }
    }
    
    private void deskripsiHasil() {
        if (Sequel.cariInteger("select count(-1) from hasil_radiologi where no_rawat='" + norawat + "' and "
                + "tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'") == 0) {
            HasilPeriksa.setText("Hasil expertise radiologi belum dikirim ke SIMRS..!!");
        } else {
            Scroll11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Hasil Expertise Radiologi [ " + nmpemeriksaan + " ] :.",
                    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)));
            
            HasilPeriksa.setText(Sequel.cariIsi("select hasil from hasil_radiologi where "
                    + "no_rawat like '%" + norawat + "%' and "
                    + "tgl_periksa like '%" + tglhasil + "%' and "
                    + "jam like '%" + jamhasil + "%' and "
                    + "kd_jenis_prw like '%" + kdItem + "%'"));

            if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "' and status='1' "
                    + "and (nm_dokter like '%dr.%' or nm_dokter like '%drg.%')") > 0) {
                Sequel.menyimpanIgnore("pembaca_hasil_radiologi",
                        "'" + tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 7).toString() + "',"
                        + "'" + akses.getkode() + "',"
                        + "'" + tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 8).toString() + "',"
                        + "'" + tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 9).toString() + "',"
                        + "'" + tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 6).toString() + "',"
                        + "'" + Sequel.cariIsi("select now()") + "'", "Pembaca hasil radiologi");
            }
        }
    }
    
    private void tampilPembacaRad(String norw, String kdPemriksaan, String tgl, String jam) {
        Valid.tabelKosong(tabModePembaca1);
        try {
            ps9 = koneksi.prepareStatement("SELECT ph.*, p.nama, date_format(ph.tgl_periksa,'%d-%m-%Y') tglPeriksa, date_format(ph.waktu_simpan,'%d-%m-%Y') tglBaca, "
                    + "time_format(ph.waktu_simpan,'%H:%i:%s') jamBaca, jp.nm_perawatan FROM pembaca_hasil_radiologi ph inner join pegawai p on p.nik=ph.kd_dokter "
                    + "inner join jns_perawatan_radiologi jp on jp.kd_jenis_prw=ph.kd_jenis_prw where "
                    + "ph.no_rawat='" + norw + "' and ph.kd_jenis_prw='" + kdPemriksaan + "' and ph.tgl_periksa='" + tgl + "' "
                    + "and ph.jam_periksa='" + jam + "' order by ph.waktu_simpan");
            try {
                rs9 = ps9.executeQuery();
                while (rs9.next()) {
                    tabModePembaca1.addRow(new String[]{
                        rs9.getString("kd_jenis_prw"),
                        rs9.getString("nm_perawatan"),
                        rs9.getString("nama"),
                        rs9.getString("tglPeriksa"),
                        rs9.getString("jam_periksa"),
                        rs9.getString("tglBaca"),
                        rs9.getString("jamBaca")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs9 != null) {
                    rs9.close();
                }
                if (ps9 != null) {
                    ps9.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilDokJangMed() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                if (ChkDokumen.isSelected() == true) {
                    rsDok = koneksi.prepareStatement("SELECT rf.id_file, rf.no_rawat, rf.nama_file_ori, date_format(rf.tgl_upload,'%d/%m/%Y') tglUpload, "
                            + "time_format(rf.tgl_upload,'%H:%i') jam, rj.nama_pemeriksaan, ifnull(pg.nama,'-') nmpetugas, rf.jenis_pemeriksaan kode, "
                            + "rf.petugas nip from rme_file_upload rf inner join rme_jenis_pemeriksaan rj on rj.kode_jenis_pemeriksaan=rf.jenis_pemeriksaan "
                            + "left join pegawai pg on pg.nik=rf.petugas where rf.nomr='" + TNoRM.getText() + "' and rf.stts_data='1' "
                            + "order by rf.tgl_upload desc").executeQuery();
                } else {
                    rsDok = koneksi.prepareStatement("SELECT rf.id_file, rf.no_rawat, rf.nama_file_ori, date_format(rf.tgl_upload,'%d/%m/%Y') tglUpload, "
                            + "time_format(rf.tgl_upload,'%H:%i') jam, rj.nama_pemeriksaan, ifnull(pg.nama,'-') nmpetugas, rf.jenis_pemeriksaan kode, "
                            + "rf.petugas nip from rme_file_upload rf inner join rme_jenis_pemeriksaan rj on rj.kode_jenis_pemeriksaan=rf.jenis_pemeriksaan "
                            + "left join pegawai pg on pg.nik=rf.petugas where rf.no_rawat='" + TNoRW.getText() + "' and rf.stts_data='1' "
                            + "order by rf.tgl_upload desc").executeQuery();
                }

                urut = 1;
                if (rsDok.next()) {
                    htmlContent.append(
                            "<table width='100%' class='isi'>"
                            + "<thead>"
                            + "<tr class='isi'>"
                            + "<td align='center' bgcolor='#f8fdf3'><b>No.</b></td>"
                            + "<td align='center' bgcolor='#f8fdf3'><b>Kode File</b></td>"
                            + "<td align='center' bgcolor='#f8fdf3'><b>Nama Pemeriksaan</b></td>"
                            + "<td align='center' bgcolor='#f8fdf3'><b>Tgl. Upload</b></td>"
                            + "<td align='center' bgcolor='#f8fdf3'><b>Jam</b></td>"
                            + "<td align='center' bgcolor='#f8fdf3'><b>Nama File</b></td>"
                            + "<td align='center' bgcolor='#f8fdf3'><b>Petugas Yang Upload</b></td>"
                            + "</tr>"
                            + "</thead>"
                            + "<tbody>"
                    );

                    rsDok.beforeFirst();
                    while (rsDok.next()) {
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' width='20px'>" + urut + ".</td>"
                                + "<td valign='top' align='center'>" + rsDok.getString("id_file") + "</td>"
                                + "<td valign='top'>" + rsDok.getString("nama_pemeriksaan") + "</td>"
                                + "<td valign='top' align='center'>" + rsDok.getString("tglUpload") + "</td>"
                                + "<td valign='top' align='center'>" + rsDok.getString("jam") + " Wita" + "</td>"
                                + "<td valign='top'>" + rsDok.getString("nama_file_ori") + " " + "<a href='" + link + "rme/view/?id=" + rsDok.getString("id_file") + "'>[Klik Untuk Membuka Gambar]</a></td>"
                                + "<td valign='top'>" + rsDok.getString("nmpetugas") + "</td>"
                                + "</tr>"
                        );
                        urut++;
                    }
                    htmlContent.append(
                            "</tbody>"
                            + "</table>"
                    );
                }
                LoadHTML1.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsDok != null) {
                    rsDok.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void tampilFile() {
        Valid.tabelKosong(tabModeJangMed);
        try {
            if (ChkDokumen1.isSelected() == true) {
                psFile = koneksi.prepareStatement("SELECT rf.id_file, rf.nama_file_ori, date_format(rf.tgl_upload,'%d/%m/%Y') tglUpload, "
                        + "time(rf.tgl_upload) jam, rj.nama_pemeriksaan, ifnull(pg.nama,'-') nmpetugas, rf.petugas nip from rme_file_upload rf "
                        + "inner join rme_jenis_pemeriksaan rj on rj.kode_jenis_pemeriksaan=rf.jenis_pemeriksaan left join pegawai pg on pg.nik=rf.petugas where "
                        + "rf.nomr='" + TNoRM.getText() + "' and rf.stts_data='1' and rj.nama_pemeriksaan like ? or "
                        + "rf.nomr='" + TNoRM.getText() + "' and rf.stts_data='1' and ifnull(pg.nama,'-') like ? or "
                        + "rf.nomr='" + TNoRM.getText() + "' and rf.stts_data='1' and rf.petugas like ? "
                        + "order by rf.tgl_upload desc");
            } else {
                psFile = koneksi.prepareStatement("SELECT rf.id_file, rf.nama_file_ori, date_format(rf.tgl_upload,'%d/%m/%Y') tglUpload, "
                        + "time(rf.tgl_upload) jam, rj.nama_pemeriksaan, ifnull(pg.nama,'-') nmpetugas, rf.petugas nip from rme_file_upload rf "
                        + "inner join rme_jenis_pemeriksaan rj on rj.kode_jenis_pemeriksaan=rf.jenis_pemeriksaan left join pegawai pg on pg.nik=rf.petugas where "
                        + "rf.no_rawat='" + TNoRW.getText() + "' and rf.stts_data='1' and rj.nama_pemeriksaan like ? or "
                        + "rf.no_rawat='" + TNoRW.getText() + "' and rf.stts_data='1' and ifnull(pg.nama,'-') like ? or "
                        + "rf.no_rawat='" + TNoRW.getText() + "' and rf.stts_data='1' and rf.petugas like ? "
                        + "order by rf.tgl_upload desc");
            }
            try {
                psFile.setString(1, "%" + TCari5.getText().trim() + "%");
                psFile.setString(2, "%" + TCari5.getText().trim() + "%");
                psFile.setString(3, "%" + TCari5.getText().trim() + "%");
                rsFile = psFile.executeQuery();
                while (rsFile.next()) {
                    tabModeJangMed.addRow(new String[]{
                        rsFile.getString("id_file"),
                        rsFile.getString("nama_pemeriksaan"),
                        rsFile.getString("tglUpload"),
                        rsFile.getString("jam"),
                        rsFile.getString("nama_file_ori"),
                        rsFile.getString("nmpetugas"),
                        rsFile.getString("nip")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rsFile != null) {
                    rsFile.close();
                }
                if (psFile != null) {
                    psFile.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount2.setText("" + tabModeJangMed.getRowCount());
    }
    
    private void klikRiwayatRalan() {
        cekPilihanRehab = 0;
        ChkLihat.setEnabled(true);
//        cmbBulan.setEnabled(false);
        ChkLihat.setSelected(false);
        cmbBulan.setSelectedIndex(0);
        cekPilihanRehab = Sequel.cariInteger("select count(-1) from data_rehab_medik where no_rawat='" + TNoRW.getText() + "'");

        if (kdpoli.getText().equals("IRM") && kdpoli.getText().equals("IRS")) {
            if (akses.getadmin() == true || akses.getkode().equals("D0000029")) {
                ChkLihatActionPerformed(null);
            } else {
                if (cekPilihanRehab == 0) {
                    TabRingkasan.setSelectedIndex(5);
                    JOptionPane.showMessageDialog(rootPane, "Silahkan tentukan pilihan jenis rehabilitasi mediknya dulu...!!!!");
                } else if (cekPilihanRehab > 0) {
                    ChkLihatActionPerformed(null);
                }
            }

            //khusus dr. rully    
        } else if (akses.getkode().equals("197807242003121005")) {
            kdpoli.setText("");
            TPoli.setText("");
            ChkLihatActionPerformed(null);
        } else {
            kdpoli.setText(kdpoli.getText());
            TPoli.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
            ChkLihatActionPerformed(null);
        }
    }
    
    private void lihatRingkasan() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (!kdpoli.getText().equals("")) {
                a = " and reg_periksa.kd_poli='" + kdpoli.getText() + "'";
            } else {
                a = "";
            }
            StringBuilder htmlContent = new StringBuilder();
            try {
                StringBuilder sb1 = new StringBuilder();
                sb1.append("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, ");
                sb1.append("tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar, sb.nama_suku_bangsa from pasien ");
                sb1.append("inner join kelurahan inner join kecamatan inner join kabupaten on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and ");
                sb1.append("pasien.kd_kab=kabupaten.kd_kab inner join suku_bangsa sb on sb.id=pasien.suku_bangsa where pasien.no_rkm_medis='" + TNoRM.getText() + "' order by pasien.no_rkm_medis desc");
                rs = koneksi.prepareStatement(sb1.toString()).executeQuery();
                
                y = 1;
                while (rs.next()) {
                    try {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,");
                        sb2.append("reg_periksa.kd_dokter,dokter.nm_dokter,IF(reg_periksa.kd_poli='IRM',CONCAT(poliklinik.nm_poli,' - ',IFNULL(data_rehab_medik.jns_rehabmedik,'FISIOTERAPI')),poliklinik.nm_poli) nm_poli,");
                        sb2.append("reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,");
                        sb2.append("penjab.png_jawab, reg_periksa.kd_poli, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur,'.') usia from reg_periksa ");
                        sb2.append("inner join dokter inner join poliklinik inner join penjab on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj ");
                        sb2.append("and reg_periksa.kd_poli=poliklinik.kd_poli LEFT JOIN data_rehab_medik ON data_rehab_medik.no_rawat = reg_periksa.no_rawat where ");
                        sb2.append("stts<>'Batal' and reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and ");
                        sb2.append("reg_periksa.tgl_registrasi between DATE_SUB(date(now()), INTERVAL " + cmbBulan.getSelectedItem().toString() + " MONTH) and NOW()" + a);
                        rs2 = koneksi.prepareStatement(sb2.toString()).executeQuery();
                        
                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Kunjungan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + " (" + rs2.getString("nm_dokter") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + " (" + rs2.getString("png_jawab") + ")</td>"
                                    + "</tr>"
                            );
                            urut++;

                            //menampilkan catatan diagnosa
//                            try {
//                                rsDiag=koneksi.prepareStatement(
//                                        "Select ifnull(diagnosa,'-') diagnosa from pemeriksaan_ralan "+                                        
//                                        "where no_rawat='"+rs2.getString("no_rawat")+"'").executeQuery();
//                                if(rsDiag.next()){
//                                    htmlContent.append(
//                                            "<tr class='isi'>"+ 
//                                "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Catatan Diagnosa</td>"+
//                                "<td valign='top' width='1%' align='center'>:</td>"+
//                                "<td valign='top' width='79%'>"+rsDiag.getString("diagnosa")+"</td>"+
//                                      "</tr>");
//                                }                                    
//                            } catch (Exception e) {
//                                System.out.println("Notifikasi : "+e);
//                            } finally{
//                                if(rsDiag!=null){
//                                    rsDiag.close();
//                                }
//                            }
                            //menampilkan rencana follow up dokter
                            try {
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append("Select ifnull(rencana_follow_up,'-') rencana_follow_up from pemeriksaan_ralan ");
                                sb3.append("where no_rawat='" + rs2.getString("no_rawat") + "'");
                                rsDiag = koneksi.prepareStatement(sb3.toString()).executeQuery();
                                
                                if (rsDiag.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rencana Follow Up Dari Dokter</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>" + rsDiag.getString("rencana_follow_up").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsDiag != null) {
                                    rsDiag.close();
                                }
                            }

                            //menampilkan rencana follow up perawat/bidan
                            try {
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append("Select ifnull(rencana_follow_up,'-') rencana_follow_up from pemeriksaan_ralan_petugas ");
                                sb4.append("where no_rawat='" + rs2.getString("no_rawat") + "'");
                                rsDiag1 = koneksi.prepareStatement(sb4.toString()).executeQuery();
                                
                                if (rsDiag1.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rencana Follow Up Dari Perawat/Bidan</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>" + rsDiag1.getString("rencana_follow_up").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsDiag1 != null) {
                                    rsDiag1.close();
                                }
                            }

                            //menampilkan catatan Resep Obat
                            try {
                                StringBuilder sb5 = new StringBuilder();
                                sb5.append("Select nama_obat,status from catatan_resep where no_rawat='" + rs2.getString("no_rawat") + "'");
                                rsObat = koneksi.prepareStatement(sb5.toString()).executeQuery();

                                if (rsObat.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Catatan Resep Obat</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='85%' bgcolor='#f8fdf3'>Nama Obat</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Status</td></tr>");

                                    rsObat.beforeFirst();
                                    w = 1;
                                    while (rsObat.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rsObat.getString("nama_obat") + "</td>"
                                                + "<td valign='top'>" + rsObat.getString("status") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsObat != null) {
                                    rsObat.close();
                                }
                            }

                            //menampilkan diagnosa penyakit                            
                            try {
                                StringBuilder sb6 = new StringBuilder();
                                sb6.append("select diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien.status ");
                                sb6.append("from diagnosa_pasien inner join penyakit ");
                                sb6.append("on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit ");
                                sb6.append("where diagnosa_pasien.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb6.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Diagnosa ICD-10</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Kode ICD-10</td>"
                                            + "<td valign='top' width='85%' bgcolor='#f8fdf3'>Deskripsi Diagnosa ICD-10</td>"
                                            + "</tr>");

                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("kd_penyakit") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_penyakit") + "</td>"
                                                + "</tr>");

                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan prosedur tindakan
                            try {
                                StringBuilder sb7 = new StringBuilder();
                                sb7.append("select prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.status ");
                                sb7.append("from prosedur_pasien inner join icd9 on prosedur_pasien.kode=icd9.kode ");
                                sb7.append("where prosedur_pasien.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb7.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Prosedur Tindakan/ICD-9</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Kode ICD-9</td>"
                                            + "<td valign='top' width='85%' bgcolor='#f8fdf3'>Deskripsi Tindakan/Prosedur ICD-9</td>"
                                            + "</tr>");

                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("kode") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("deskripsi_panjang") + "</td>"
                                                + "</tr>");

                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan riwayat pemeriksaan ralan dokter
                            try {
                                StringBuilder sb8 = new StringBuilder();
                                sb8.append("select pemeriksaan_ralan.suhu_tubuh,pemeriksaan_ralan.tensi,pemeriksaan_ralan.nadi,pemeriksaan_ralan.respirasi,");
                                sb8.append("pemeriksaan_ralan.tinggi,pemeriksaan_ralan.berat,pemeriksaan_ralan.gcs,pemeriksaan_ralan.keluhan, ");
                                sb8.append("pemeriksaan_ralan.pemeriksaan,pemeriksaan_ralan.alergi,ifnull(pemeriksaan_ralan.diagnosa,'-') diagnosa, ");
                                sb8.append("ifnull(pemeriksaan_ralan.rincian_tindakan,'-') rincian_tindakan, ifnull(pemeriksaan_ralan.terapi,'-') terapi, ");
                                sb8.append("ifnull(pemeriksaan_ralan.spo2,'-') spo2 from pemeriksaan_ralan where ");
                                sb8.append("pemeriksaan_ralan.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb8.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Dokter</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='2%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='7%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Keluhan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Diagnosa</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Alergi</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Suhu(C)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tensi</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Berat(Kg)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>SPO2</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Rincian Tindakan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Terapi</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("diagnosa").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("alergi").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("suhu_tubuh") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tensi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nadi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("respirasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tinggi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("berat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("gcs") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("spo2") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("rincian_tindakan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("terapi").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan pemeriksaan THT
                            if (rs2.getString("kd_poli").equals("THT")) {
                                try {
                                    StringBuilder sb9 = new StringBuilder();
                                    sb9.append("Select ifnull(nama_pemeriksaan,'-') namanya, ifnull(hasil_pemeriksaan,'-') hasilnya ");
                                    sb9.append("from pemeriksaan_tht where no_rawat='" + rs2.getString("no_rawat") + "'");
                                    rsTHT = koneksi.prepareStatement(sb9.toString()).executeQuery();
                                    
                                    if (rsTHT.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Tindakan THT</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>Nama Pemeriksaan : <span style='font-weight:bold'>" + rsTHT.getString("namanya").toUpperCase().replaceAll("(\r\n|\r|\n|\n\r)", "<br/>") + "</span>"
                                                + "<br/><br/><span style='font-weight:bold'>Hasil Pemeriksaan : </span><br/>" + rsTHT.getString("hasilnya").replaceAll("(\r\n|\r|\n|\n\r)", "<br/>") + "</td>"
                                                + "</tr>");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rsTHT != null) {
                                        rsTHT.close();
                                    }
                                }
                            }
                            
                            //menampilkan status kaki diabetes
                            if (Sequel.cariInteger("select count(-1) from data_dasar_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "'") > 0) {
                                try {
                                    StringBuilder sbk = new StringBuilder();
                                    sbk.append("Select *, date_format(tgl_masuk,'%d/%m/%Y') tglmsk, if(lama_rawat='','-',concat(lama_rawat,' hari')) lmrwt, ");
                                    sbk.append("if(lama_diketahui='','-',concat(lama_diketahui,' tahun (pembulatan ke bawah)')) lmDiketahui from data_dasar_kaki_diabetes ");
                                    sbk.append("where no_rawat='" + rs2.getString("no_rawat") + "'");
                                    rsDiabet = koneksi.prepareStatement(sbk.toString()).executeQuery();
                                    if (rsDiabet.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"                                                
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status Kaki Diabetes</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>"                                                
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                                + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>I. Data Dasar</span></td>"
                                                + "<tr align='center'>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Usia</td>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>TB/BB</td>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>BMI</td>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tensi</td>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Ras/Suku</td>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tgl. Masuk</td>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"                                                        
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Lama Rawat</td>"
                                                + "</tr>");
                                        rsDiabet.beforeFirst();
                                        while (rsDiabet.next()) {
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top'>" + rs2.getString("usia") + "</td>"
                                                    + "<td valign='top'>" + rsDiabet.getString("tb") + " Cm/" + rsDiabet.getString("bb") + " Kg</td>"
                                                    + "<td valign='top'>" + rsDiabet.getString("bmi") + " kg/m²</td>"
                                                    + "<td valign='top'>" + rsDiabet.getString("tensi") + " mmHg</td>"
                                                    + "<td valign='top'>" + rs.getString("nama_suku_bangsa") + "</td>"
                                                    + "<td valign='top'>" + rsDiabet.getString("tglmsk") + "</td>"
                                                    + "<td valign='top'>" + rsDiabet.getString("jns_rawat") + "</td>"
                                                    + "<td valign='top'>" + rsDiabet.getString("lmrwt") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>II. Anamnesis</span></td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Tipe Diabetes</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Lama Diketahui Diabetes</td>"                                                    
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("tipe_diabetes").equals("Lainnya")) {
                                                if (rsDiabet.getString("tipe_diabet_lain").equals("")) {
                                                    tipeDiabet = rsDiabet.getString("tipe_diabetes") + " : -";
                                                } else {
                                                    tipeDiabet = rsDiabet.getString("tipe_diabetes") + " : " + rsDiabet.getString("tipe_diabet_lain");
                                                }
                                            } else {
                                                tipeDiabet = rsDiabet.getString("tipe_diabetes");
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"                                                    
                                                    + "<td valign='top' colspan='3'>" + tipeDiabet + "</td>"
                                                    + "<td valign='top' colspan='2'>" + rsDiabet.getString("lmDiketahui") + "</td>"
                                                    + "</tr>");
                                            
                                            if (Sequel.cariInteger("select count(-1) from riwayat_pengobatan_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "'") > 0) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='8'>Riwayat Pengobatan Diabetes :</td>"
                                                        + "<tr align='center'>"
                                                        + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Obat</td>"
                                                        + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Jenis</td>"
                                                        + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Dosis</td>"
                                                        + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Lama</td>"
                                                        + "</tr>");
                                                
                                                //riwayat penggunaan obat
                                                try {
                                                    psRDO = koneksi.prepareStatement("SELECT * FROM riwayat_pengobatan_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "' order by waktu_simpan");
                                                    try {
                                                        rsRDO = psRDO.executeQuery();
                                                        while (rsRDO.next()) {
                                                            htmlContent.append(
                                                                    "<tr>"
                                                                    + "<td valign='top' colspan='2'>" + rsRDO.getString("obat") + "</td>"
                                                                    + "<td valign='top' colspan='2'>" + rsRDO.getString("jenis") + "</td>"
                                                                    + "<td valign='top' colspan='2'>" + rsRDO.getString("dosis") + "</td>"
                                                                    + "<td valign='top' colspan='2'>" + rsRDO.getString("lama") + "</td>"
                                                                    + "</tr>");
                                                        }
                                                    } catch (Exception e) {
                                                        System.out.println("Notifikasi : " + e);
                                                    } finally {
                                                        if (rsRDO != null) {
                                                            rsRDO.close();
                                                        }
                                                        if (psRDO != null) {
                                                            psRDO.close();
                                                        }
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("Notifikasi : " + e);
                                                }
                                                //---------------------------------
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr align='center'>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Merokok</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Lama Luka</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Riwayat Edukasi Kaki DM</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Jenis Alas Kaki</td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("merokok").equals("Ya")) {
                                                if (rsDiabet.getString("merokok_ya").equals("")) {
                                                    merokok = rsDiabet.getString("merokok");
                                                } else {
                                                    merokok = rsDiabet.getString("merokok") + " (" + rsDiabet.getString("merokok_ya") + " batang/hari)";
                                                }
                                            } else if (rsDiabet.getString("merokok").equals("Mantan")) {
                                                if (rsDiabet.getString("merokok_mantan").equals("")) {
                                                    merokok = rsDiabet.getString("merokok");
                                                } else {
                                                    merokok = rsDiabet.getString("merokok") + " (" + rsDiabet.getString("merokok_mantan") + " tahun lalu)";
                                                }
                                            } else {
                                                merokok = rsDiabet.getString("merokok");
                                            }
                                            
                                            if (rsDiabet.getString("lama_luka").equals("")) {
                                                lmLuka = "-";
                                            } else {
                                                if (rsDiabet.getString("satuan_lama_luka").equals("-")) {
                                                    lmLuka = rsDiabet.getString("lama_luka");
                                                } else {
                                                    lmLuka = rsDiabet.getString("lama_luka") + " " + rsDiabet.getString("satuan_lama_luka");
                                                }
                                            }
                                            
                                            if (rsDiabet.getString("jns_alas_kaki").equals("Sepatu")) {
                                                if (rsDiabet.getString("jns_alas_kaki_sepatu").equals("")) {
                                                    jnsAlas = rsDiabet.getString("jns_alas_kaki");
                                                } else {
                                                    jnsAlas = rsDiabet.getString("jns_alas_kaki") + " (" + rsDiabet.getString("jns_alas_kaki_sepatu") + " )";
                                                }
                                            } else {
                                                jnsAlas = rsDiabet.getString("jns_alas_kaki");
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"                                                    
                                                    + "<td valign='top' colspan='2'>" + merokok + "</td>"
                                                    + "<td valign='top' colspan='2'>" + lmLuka + "</td>"
                                                    + "<td valign='top' colspan='2'>" + rsDiabet.getString("riwayat_edukasi") + "</td>"
                                                    + "<td valign='top' colspan='2'>" + jnsAlas + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'>Penyebab :</td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Trauma Mekanik</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Trauma Kimia</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Trauma Termis</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Spontan</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Lain-Lain</td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("trauma_mekanik").equals("ya")) {
                                                if (rsDiabet.getString("tersandung").equals("ya")) {
                                                    tersandung = "Tersandung, ";
                                                } else {
                                                    tersandung = "";
                                                }
                                                
                                                if (rsDiabet.getString("memakai_sepatu").equals("ya")) {
                                                    memakai = "Memakai sepatu sempit, ";
                                                } else {
                                                    memakai = "";
                                                }
                                                
                                                if (rsDiabet.getString("tertusuk").equals("ya")) {
                                                    tertusuk = "Tertusuk paku/duri, ";
                                                } else {
                                                    tertusuk = "";
                                                }
                                                
                                                if (rsDiabet.getString("dll_sebutkan_mekanik").equals("ya")) {
                                                    if (rsDiabet.getString("ket_dll_sebutkan_mekanik").equals("")) {
                                                        dllTraMeka = "-";
                                                    } else {
                                                        dllTraMeka = rsDiabet.getString("ket_dll_sebutkan_mekanik");
                                                    }                                                    
                                                } else {
                                                    dllTraMeka = "";
                                                }
                                                traMekanik = tersandung + memakai + tertusuk + dllTraMeka;
                                            } else {
                                                traMekanik = "-";
                                            }

                                            if (rsDiabet.getString("trauma_kimia").equals("ya")) {
                                                if (rsDiabet.getString("terkena_zat").equals("ya")) {
                                                    if (rsDiabet.getString("ket_terkena_zat").equals("")) {
                                                        traKimia = "Terkena zat kimia";
                                                    } else {
                                                        traKimia = "Terkena zat kimia (" + rsDiabet.getString("ket_terkena_zat") + ")";
                                                    }
                                                } else {
                                                    traKimia = "-";
                                                }
                                            } else {
                                                traKimia = "-";
                                            }
                                            
                                            if (rsDiabet.getString("trauma_termis").equals("ya")) {
                                                if (rsDiabet.getString("terkena_air_panas").equals("ya")) {
                                                    terkenaAir = "Terkena air panas, ";
                                                } else {
                                                    terkenaAir = "";
                                                }
                                                
                                                if (rsDiabet.getString("terkena_pemanas").equals("ya")) {
                                                    terkenaPemanas = "Terkena pemanas listrik, ";
                                                } else {
                                                    terkenaPemanas = "";
                                                }

                                                if (rsDiabet.getString("dll_sebutkan_termis").equals("ya")) {
                                                    if (rsDiabet.getString("ket_dll_sebutkan_termis").equals("")) {
                                                        dllTraTermis = "-";
                                                    } else {
                                                        dllTraTermis = rsDiabet.getString("ket_dll_sebutkan_termis");
                                                    }                    
                                                } else {
                                                    dllTraTermis = "";
                                                }
                                                traTermis = terkenaAir + terkenaPemanas + dllTraTermis;
                                            } else {
                                                traTermis = "-";
                                            }
                                            
                                            if (rsDiabet.getString("spontan").equals("ya")) {
                                                spontan = "Ya";
                                            } else {
                                                spontan = "-";
                                            }
                                            
                                            if (rsDiabet.getString("penyebab_lain").equals("ya")) {
                                                if (rsDiabet.getString("ket_penyebab_lain").equals("")) {
                                                    lainPenyebab = "-";
                                                } else {
                                                    lainPenyebab = rsDiabet.getString("ket_penyebab_lain");
                                                }
                                            } else {
                                                lainPenyebab = "-";
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"                                                    
                                                    + "<td valign='top' colspan='2'>" + traMekanik + "</td>"
                                                    + "<td valign='top' colspan='2'>" + traKimia + "</td>"
                                                    + "<td valign='top' colspan='2'>" + traTermis + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + spontan + "</td>"
                                                    + "<td valign='top' colspan='1'>" + lainPenyebab + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'>Riwayat Luka/Ulkus : " + rsDiabet.getString("riwayat_ulkus") + "</td>"
                                                    + "</tr>");

                                            if (Sequel.cariInteger("select count(-1) from riwayat_ulkus_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "'") > 0) {
                                                htmlContent.append(
                                                        "<tr align='center'>"
                                                        + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Tahun</td>"
                                                        + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Lokasi</td>"
                                                        + "<td valign='top' colspan='4' bgcolor='#f8fdf3'>Penyebab</td>"
                                                        + "</tr>");

                                                //riwayat luka/ulkus
                                                try {
                                                    psRDU = koneksi.prepareStatement("SELECT * FROM riwayat_ulkus_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "' order by waktu_simpan");
                                                    try {
                                                        rsRDU = psRDU.executeQuery();
                                                        while (rsRDU.next()) {
                                                            htmlContent.append(
                                                                    "<tr>"
                                                                    + "<td valign='top' align='center' colspan='1'>" + rsRDU.getString("tahun") + "</td>"
                                                                    + "<td valign='top' colspan='3'>" + rsRDU.getString("lokasi") + "</td>"
                                                                    + "<td valign='top' colspan='4'>" + rsRDU.getString("penyebab") + "</td>"
                                                                    + "</tr>");
                                                        }
                                                    } catch (Exception e) {
                                                        System.out.println("Notifikasi : " + e);
                                                    } finally {
                                                        if (rsRDU != null) {
                                                            rsRDU.close();
                                                        }
                                                        if (psRDU != null) {
                                                            psRDU.close();
                                                        }
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("Notifikasi : " + e);
                                                }
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'>Riwayat Amputasi :</td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Kiri</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Kanan</td>"
                                                    + "</tr>");

                                            if (rsDiabet.getString("kiri").equals("jari kaki ke")) {
                                                if (rsDiabet.getString("jari_kaki_kiri_ke").equals("")) {
                                                    amputasiKiri = rsDiabet.getString("kiri");
                                                } else {
                                                    amputasiKiri = rsDiabet.getString("kiri") + " " + rsDiabet.getString("jari_kaki_kiri_ke");
                                                }
                                            } else if (rsDiabet.getString("kiri").equals("Transmetatarsal, tahun")) {
                                                if (rsDiabet.getString("trans_kiri_tahun").equals("")) {
                                                    amputasiKiri = rsDiabet.getString("kiri");
                                                } else {
                                                    amputasiKiri = rsDiabet.getString("kiri") + " " + rsDiabet.getString("trans_kiri_tahun");
                                                }
                                            } else {
                                                amputasiKiri = rsDiabet.getString("kiri");
                                            }
                                            
                                            if (rsDiabet.getString("kanan").equals("jari kaki ke")) {
                                                if (rsDiabet.getString("jari_kaki_kanan_ke").equals("")) {
                                                    amputasiKanan = rsDiabet.getString("kanan");
                                                } else {
                                                    amputasiKanan = rsDiabet.getString("kanan") + " " + rsDiabet.getString("jari_kaki_kanan_ke");
                                                }
                                            } else if (rsDiabet.getString("kanan").equals("Transmetatarsal, tahun")) {
                                                if (rsDiabet.getString("trans_kanan_tahun").equals("")) {
                                                    amputasiKanan = rsDiabet.getString("kanan");
                                                } else {
                                                    amputasiKanan = rsDiabet.getString("kanan") + " " + rsDiabet.getString("trans_kanan_tahun");
                                                }
                                            } else {
                                                amputasiKanan = rsDiabet.getString("kanan");
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='2'>" + amputasiKiri + "</td>"
                                                    + "<td valign='top' colspan='2'>" + amputasiKanan + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>III. Riwayat Komplikasi/Penyakit Penyerta</span></td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Mata</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Ginjal</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Penyakit Jantung Koroner</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Hipertensi</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Stroke</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>PAD</td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("mata").equals("ya")) {
                                                if (rsDiabet.getString("riwayat_mata").equals("Terapi laser tahun")) {
                                                    if (rsDiabet.getString("terapi_mata_tahun").equals("")) {
                                                        mataDiabet = rsDiabet.getString("riwayat_mata") + "(-)";
                                                    } else {
                                                        mataDiabet = rsDiabet.getString("riwayat_mata") + " " + rsDiabet.getString("terapi_mata_tahun");
                                                    }
                                                } else {
                                                    mataDiabet = rsDiabet.getString("riwayat_mata");
                                                }
                                            } else {
                                                mataDiabet = "-";
                                            }
                                            
                                            if (rsDiabet.getString("ginjal").equals("ya")) {
                                                ginjal = rsDiabet.getString("riwayat_ginjal");
                                            } else {
                                                ginjal = "-";
                                            }
                                            
                                            if (rsDiabet.getString("penyakit_jantung").equals("ya")) {
                                                pnyJantung = "Ya";
                                            } else {
                                                pnyJantung = "-";
                                            }
                                            
                                            if (rsDiabet.getString("hipertensi").equals("ya")) {
                                                hipertensi = "Ya";
                                            } else {
                                                hipertensi = "-";
                                            }
                                            
                                            if (rsDiabet.getString("strok").equals("ya")) {
                                                strok = "Ya";
                                            } else {
                                                strok = "-";
                                            }
                                            
                                            if (rsDiabet.getString("pad").equals("ya")) {
                                                pad = "Ya";
                                            } else {
                                                pad = "-";
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='2'>" + mataDiabet + "</td>"
                                                    + "<td valign='top' colspan='2'>" + ginjal + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + pnyJantung + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + hipertensi + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + strok + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + pad + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>IV. Pemeriksaan Fisik</span><br>a. Jenis Luka :</br></td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Non Ulkus</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Ulkus</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Ulkus & Gangen</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Selulitis</td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("non_ulkus").equals("ya")) {
                                                nonUlkus = "Ya";
                                            } else {
                                                nonUlkus = "-";
                                            }
                                            
                                            if (rsDiabet.getString("ulkus").equals("ya")) {
                                                ulkus = "Ya";
                                            } else {
                                                ulkus = "-";
                                            }
                                            
                                            if (rsDiabet.getString("ulkus_gangen").equals("ya")) {
                                                ulkusGang = "Ya";
                                            } else {
                                                ulkusGang = "-";
                                            }
                                            
                                            if (rsDiabet.getString("selulitis").equals("ya")) {
                                                sellu = "Ya";
                                            } else {
                                                sellu = "-";
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' align='center' colspan='1'>" + nonUlkus + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + ulkus + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + ulkusGang + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + sellu + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr align='center'>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Penjelasan Dorsal Kanan</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Penjelasan Plantar Kanan</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Penjelasan Dorsal Kiri</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Penjelasan Plantar Kiri</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='2'>" + rsDiabet.getString("deskripsi_dorsal_kanan") + "</td>"
                                                    + "<td valign='top' colspan='2'>" + rsDiabet.getString("deskripsi_plantar_kanan") + "</td>"
                                                    + "<td valign='top' colspan='2'>" + rsDiabet.getString("deskripsi_dorsal_kiri") + "</td>"
                                                    + "<td valign='top' colspan='2'>" + rsDiabet.getString("deskripsi_plantar_kiri") + "</td>"
                                                    + "</tr>");
                                            
                                            if (Sequel.cariInteger("select count(-1) from deformitas_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "'") > 0) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='8'>b. Deformitas :</td>"
                                                        + "<tr align='center'>"
                                                        + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Lokasi Kelainan</td>"
                                                        + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Kanan</td>"
                                                        + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Kiri</td>"
                                                        + "</tr>");

                                                //deformitas
                                                try {
                                                    psRDD = koneksi.prepareStatement("SELECT * FROM deformitas_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "' order by waktu_simpan");
                                                    try {
                                                        rsRDD = psRDD.executeQuery();
                                                        i = 1;
                                                        while (rsRDD.next()) {
                                                            htmlContent.append(
                                                                    "<tr>"
                                                                    + "<td valign='top' colspan='2'>" + i + ". " + rsRDD.getString("lokasi") + "</td>"
                                                                    + "<td valign='top' colspan='3'>" + rsRDD.getString("kanan") + "</td>"
                                                                    + "<td valign='top' colspan='3'>" + rsRDD.getString("kiri") + "</td>"
                                                                    + "</tr>");
                                                            i++;
                                                        }
                                                    } catch (Exception e) {
                                                        System.out.println("Notifikasi : " + e);
                                                    } finally {
                                                        if (rsRDD != null) {
                                                            rsRDD.close();
                                                        }
                                                        if (psRDD != null) {
                                                            psRDD.close();
                                                        }
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("Notifikasi : " + e);
                                                }
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='3' align='left'>c. Inspeksi Kaki</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Kaki Kanan</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Kaki Kiri</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='5'><span style='font-weight:bold'>Kulit Kaki</span></td>"
                                                        + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Kering/bersisik</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_kering") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_kering") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Tumit pecah-pecah</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_tumit") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_tumit") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Bulu rambut menipis</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_bulu") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_bulu") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Tinea pedis</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_tinea") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_tinea") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Kalus</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_kalus") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_kalus") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Korn</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_korn") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_korn") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Hiperpigmentasi</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_hiperpig") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_hiperpig") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Edema</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_edema") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_edema") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Healed Ulcer</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_healed") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_healed") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='5'><span style='font-weight:bold'>Kuku Kaki</span></td>"
                                                        + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Menebal</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kanan_menebal") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kiri_menebal") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Infeksi</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kanan_infeksi") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kiri_infeksi") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Perubahan Warna</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kanan_perubahan") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kiri_perubahan") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Rapuh</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kanan_rapuh") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kiri_rapuh") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Ingrowing nail</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kanan_ingro") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kiri_ingro") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Atrofi</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kanan_atrofi") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kiri_atrofi") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Lain-lain</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kanan_lain") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kiri_lain") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='5'><span style='font-weight:bold'>Telapak Kaki</span></td>"
                                                        + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Hallux toe</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("telapak_kanan_hallux") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("telapak_kiri_hallux") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Pel Planus</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("telapak_kanan_pel") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("telapak_kiri_pel") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Charcot foot</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("telapak_kanan_char") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("telapak_kiri_char") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='5'><span style='font-weight:bold'>Jari Kaki</span></td>"
                                                        + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Hammer toe</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kanan_hammer") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kiri_hammer") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Claw toe</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kanan_claw") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kiri_claw") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Hiperekstensi</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kanan_hiper") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kiri_hiper") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Maserasi interdigital</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kanan_maser") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kiri_maser") + "</td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("ket_jari_kanan_lain").equals("")) {
                                                jarKakiKanan = rsDiabet.getString("jari_kanan_lain");
                                            } else {
                                                jarKakiKanan = rsDiabet.getString("jari_kanan_lain") + " (" + rsDiabet.getString("ket_jari_kanan_lain") + ")";
                                            }
                                            
                                            if (rsDiabet.getString("ket_jari_kiri_lain").equals("")) {
                                                jarKakiKiri = rsDiabet.getString("jari_kiri_lain");
                                            } else {
                                                jarKakiKiri = rsDiabet.getString("jari_kiri_lain") + " (" + rsDiabet.getString("ket_jari_kiri_lain") + ")";
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Lain-lain (sebutkan)</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + jarKakiKanan + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + jarKakiKiri + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>V. Pemeriksaan Vaskular</span></td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='2'></td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Kaki Kanan</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Kaki Kiri</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='2'>A. Dorsalis Pedis</td>"
                                                    + "<td valign='top' colspan='1'>" + rsDiabet.getString("dorsalis_kaki_kanan") + "</td>"
                                                    + "<td valign='top' colspan='1'>" + rsDiabet.getString("dorsalis_kaki_kiri") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='2'>A. Tibialis Posterior</td>"
                                                    + "<td valign='top' colspan='1'>" + rsDiabet.getString("tibialis_kaki_kanan") + "</td>"
                                                    + "<td valign='top' colspan='1'>" + rsDiabet.getString("tibialis_kaki_kiri") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'>Pemeriksaan ABI :</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='1'>TDS A. Brachialis</td>"
                                                    + "<td valign='top' colspan='1'>: " + rsDiabet.getString("brachialis") + " mmHg</td>"
                                                    + "<td valign='top' colspan='1'></td>"
                                                    + "<td valign='top' colspan='1'>TDS A. Dorsalis pedis</td>"
                                                    + "<td valign='top' colspan='1'>: " + rsDiabet.getString("dorsalis_pedis") + " mmHg</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='1'>Score ABI :</td>"
                                                    + "<td valign='top' colspan='1'><span style='text-decoration:underline'>TDS A. Dorsalis pedis</span><br>TDS A. Brachialis</br></td>"
                                                    + "<td valign='top' colspan='1'><span style='text-decoration:underline'>" + rsDiabet.getString("dorsalis_pedis") + " mmHg</span><br>" + rsDiabet.getString("brachialis") + " mmHg</br></td>"
                                                    + "<td valign='top' colspan='1'>= " + rsDiabet.getString("skor_abi") + " mmHg</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>VI. Pemeriksaan Neuropati</span></td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='1'></td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Kaki Kanan</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Kaki Kiri</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='1'>Monofilamen 10 g</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("monofilamen_kanan") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("monofilamen_kiri") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='1'>Garputala 128 Hz</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("garputala_kanan") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("garputala_kiri") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='1'>Reflex tendo Achilles</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("reflex_kanan") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("reflex_kiri") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>VII. Deraja Luka</span><br>Klasifikasi Ulkus Wagner</br></td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Checklist</td>"
                                                    + "<td valign='top' colspan='7' bgcolor='#f8fdf3'>Deskripsi</td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("derajat0").equals("ya")) {
                                                der0 = "<input type='checkbox' checked>";
                                            } else {
                                                der0 = "<input type='checkbox' disabled>";
                                            }
                                            
                                            if (rsDiabet.getString("derajat1").equals("ya")) {
                                                der1 = "<input type='checkbox' checked>";
                                            } else {
                                                der1 = "<input type='checkbox' disabled>";
                                            }
                                            
                                            if (rsDiabet.getString("derajat2").equals("ya")) {
                                                der2 = "<input type='checkbox' checked>";
                                            } else {
                                                der2 = "<input type='checkbox' disabled>";
                                            }
                                            
                                            if (rsDiabet.getString("derajat3").equals("ya")) {
                                                der3 = "<input type='checkbox' checked>";
                                            } else {
                                                der3 = "<input type='checkbox' disabled>";
                                            }
                                            
                                            if (rsDiabet.getString("derajat4").equals("ya")) {
                                                der4 = "<input type='checkbox' checked>";
                                            } else {
                                                der4 = "<input type='checkbox' disabled>";
                                            }
                                            
                                            if (rsDiabet.getString("derajat5").equals("ya")) {
                                                der5 = "<input type='checkbox' checked>";
                                            } else {
                                                der5 = "<input type='checkbox' disabled>";
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' align='center' colspan='1'>" + der0 + "</td>"
                                                    + "<td valign='top' colspan='7'>0 : Tidak ada luka terbuka, mungkin terdapat deformitas atau selulitis</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' align='center' colspan='1'>" + der1 + "</td>"
                                                    + "<td valign='top' colspan='7'>1 : Ulkus diabetes superfisial (partial and full thickness)</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' align='center' colspan='1'>" + der2 + "</td>"
                                                    + "<td valign='top' colspan='7'>2 : Ulkus meluas sampai ligament, tendon, kapsula sendi atau fasia dalam tanpa abses atau osteomielitis</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' align='center' colspan='1'>" + der3 + "</td>"
                                                    + "<td valign='top' colspan='7'>3 : Ulkus dalam dengan abses, osteomielitis, atau sepsis sendi</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' align='center' colspan='1'>" + der4 + "</td>"
                                                    + "<td valign='top' colspan='7'>4 : Gangren yang terbatas pada kaki bagian depan atau tumit</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' align='center' colspan='1'>" + der5 + "</td>"
                                                    + "<td valign='top' colspan='7'>5 : Gangren yang meluas meliputi seluruh kaki</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>VIII. Pemeriksaan Penunjang</span></td>"
                                                    + "<tr align='left'>"
                                                    + "<td valign='top' colspan='8' bgcolor='#f8fdf3'>Laboratorium rutin (dalam 3 bulan terakhir)</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='8'>" + rsDiabet.getString("pemeriksaan_lab").replaceAll(":&lt", ": kurang dari ").replaceAll(" &lt", " kurang dari ").replaceAll("\n", "<br/>") + "<br/></td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("ronsen_kaki").equals("ya")) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='8'>Rontgen Kaki : </td>"
                                                        + "<tr align='center'>"
                                                        + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Tanggal</td>"
                                                        + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Kesimpulan</td>"
                                                        + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Osteomielitis</td>"
                                                        + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Lokasi</td>"
                                                        + "</tr>");

                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' colspan='1'>" + Valid.SetTglINDONESIA(rsDiabet.getString("ronsen_kaki_tgl")) + "</td>"
                                                        + "<td valign='top' colspan='3'>" + rsDiabet.getString("kesimpulan_ronsen") + "</td>"
                                                        + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("osteomielitis") + "</td>"
                                                        + "<td valign='top' colspan='3'>" + rsDiabet.getString("lokasi") + "</td>"
                                                        + "</tr>");
                                            }
                                            
                                            if (Sequel.cariInteger("select count(-1) from mikrobiologi_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "'") > 0) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='8'>Mikrobiologi :</td>"
                                                        + "<tr align='center'>"
                                                        + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Bakteri</td>"
                                                        + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Sensitif</td>"
                                                        + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Resisten</td>"
                                                        + "</tr>");
                                                
                                                //mikrobiologi
                                                try {
                                                    psRDM = koneksi.prepareStatement("SELECT * FROM mikrobiologi_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "' order by waktu_simpan");
                                                    try {
                                                        rsRDM = psRDM.executeQuery();
                                                        i = 1;
                                                        while (rsRDM.next()) {
                                                            htmlContent.append(
                                                                    "<tr>"
                                                                    + "<td valign='top' colspan='2'>" + i + ". " + rsRDM.getString("bakteri") + "</td>"
                                                                    + "<td valign='top' colspan='3'>" + rsRDM.getString("sensitif") + "</td>"
                                                                    + "<td valign='top' colspan='3'>" + rsRDM.getString("resisten") + "</td>"
                                                                    + "</tr>");
                                                            i++;
                                                        }
                                                    } catch (Exception e) {
                                                        System.out.println("Notifikasi : " + e);
                                                    } finally {
                                                        if (rsRDM != null) {
                                                            rsRDM.close();
                                                        }
                                                        if (psRDM != null) {
                                                            psRDM.close();
                                                        }
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("Notifikasi : " + e);
                                                }
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr align='center'>"
                                                    + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Rontgen Thorax (Kesimpulan)</td>"
                                                    + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>EKG (Kesimpulan)</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>USG Dopler</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>" + rsDiabet.getString("kes_ronsen_thorax") + "</td>"
                                                    + "<td valign='top' colspan='3'>" + rsDiabet.getString("kes_ekg") + "</td>"
                                                    + "<td valign='top' colspan='2'>" + rsDiabet.getString("usg_dopler") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>IX. Tata Laksana Rawat Luka</span></td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Debridement</td>"
                                                    + "<td valign='top' colspan='6' bgcolor='#f8fdf3'>Modern Dressing</td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("surgical").equals("ya")) {
                                                surgi = "Surgical, ";
                                            } else {
                                                surgi = "";
                                            }

                                            if (rsDiabet.getString("chemical").equals("ya")) {
                                                chemi = "Chemical, ";
                                            } else {
                                                chemi = "";
                                            }

                                            if (rsDiabet.getString("biology").equals("ya")) {
                                                bio = "Biology";
                                            } else {
                                                bio = "";
                                            }
                                            debri = surgi + chemi + bio;
                                            
                                            if (rsDiabet.getString("hidrocol").equals("ya")) {
                                                hydro = "Hydrocolloid, ";
                                            } else {
                                                hydro = "";
                                            }
                                            
                                            if (rsDiabet.getString("foam").equals("ya")) {
                                                foam = "Foam, ";
                                            } else {
                                                foam = "";
                                            }
                                            
                                            if (rsDiabet.getString("allginate").equals("ya")) {
                                                algi = "Allginate, ";
                                            } else {
                                                algi = "";
                                            }
                                            
                                            if (rsDiabet.getString("silver").equals("ya")) {
                                                silver = "Silver Sulfadiazine, ";
                                            } else {
                                                silver = "";
                                            }
                                            
                                            if (rsDiabet.getString("cadexomer").equals("ya")) {
                                                cadex = "Cadexomer, ";
                                            } else {
                                                cadex = "";
                                            }

                                            if (rsDiabet.getString("madu").equals("ya")) {
                                                madu = "Madu, ";
                                            } else {
                                                madu = "";
                                            }

                                            if (rsDiabet.getString("modern_dresing_lain").equals("ya")) {
                                                if (rsDiabet.getString("ket_modern_dresing_lain").equals("")) {
                                                    lainModern = "Lain-lain (-)";
                                                } else {
                                                    lainModern = "Lain-lain (" + rsDiabet.getString("ket_modern_dresing_lain") + ")";
                                                }
                                            } else {
                                                lainModern = "";
                                            }
                                            modernDres = hydro + foam + algi + silver + cadex + madu + lainModern;

                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='2'>" + debri + "</td>"
                                                    + "<td valign='top' colspan='6'>" + modernDres + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='4' bgcolor='#f8fdf3'>Perawat</td>"
                                                    + "<td valign='top' colspan='4' bgcolor='#f8fdf3'>Dokter Pemeriksa</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='4'>" + Sequel.cariIsi("select ifnull(nama,'-') from pegawai where nik='" + rsDiabet.getString("nip_perawat") + "'") + "</td>"
                                                    + "<td valign='top' colspan='4'>" + Sequel.cariIsi("select ifnull(nama,'-') from pegawai where nik='" + rsDiabet.getString("nip_dokter") + "'") + "</td>"
                                                    + "</tr>");
                                        }
                                        htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rsDiabet != null) {
                                        rsDiabet.close();
                                    }
                                }
                            }

                            //menampilkan reasesmen pemeriksaan
                            if (Sequel.cariIsi("select reasesmen from pemeriksaan_ralan WHERE no_rawat='" + rs2.getString("no_rawat") + "'").equals("1")) {
                                try {
                                    StringBuilder sb10 = new StringBuilder();
                                    sb10.append("SELECT kesimpulan, rekomendasi FROM pemeriksaan_ralan WHERE no_rawat='" + rs2.getString("no_rawat") + "'");
                                    rs3 = koneksi.prepareStatement(sb10.toString()).executeQuery();

                                    if (rs3.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Reasesmen Pemeriksaan Dokter</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                                + "<tr align='center'>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Kesimpulan</td>"
                                                + "<td valign='top' width='13%' bgcolor='#f8fdf3'>Rekomendasi</td>"
                                                + "</tr>"
                                        );
                                        rs3.beforeFirst();
                                        while (rs3.next()) {
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top'>" + rs3.getString("kesimpulan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "<br><br></td>"
                                                    + "<td valign='top'>" + rs3.getString("rekomendasi").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "<br><br></td>"
                                                    + "</tr>");
                                        }
                                        htmlContent.append(
                                                "</table>"
                                                + "</td>"
                                                + "</tr>");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rs3 != null) {
                                        rs3.close();
                                    }
                                }
                            }

                            //menampilkan konsul internal poliklinik
                            try {
                                StringBuilder sb11 = new StringBuilder();
                                sb11.append("select sk.*, p.no_rkm_medis, p.nm_pasien, pl1.nm_poli poliAwal, d.nm_dokter, date_format(sk.tgl_permintaan_konsul,'%d/%m/%Y') tglKonsul,");
                                sb11.append("pl2.nm_poli poliTujuan, DATE_FORMAT(sk.tgl_permintaan_konsul,'%d-%m-%Y') tglKonsul, if(sk.tgl_menjawab='0000-00-00','-',sk.tgl_menjawab) tgljawab, ");
                                sb11.append("date_format(sk.tgl_menjawab,'%d/%m/%Y') tglmenjawab, date_format(sk.tgl_konsul_ulang,'%d/%m/%Y') tglkonsululang from surat_konsul_unit_ralan sk ");
                                sb11.append("inner join reg_periksa rp on rp.no_rawat=sk.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis ");
                                sb11.append("inner join poliklinik pl1 on pl1.kd_poli=sk.kd_poli inner join poliklinik pl2 on pl2.kd_poli=sk.kd_poli_pembalas ");
                                sb11.append("inner join dokter d on d.kd_dokter=sk.kd_dokter_pembalas WHERE sk.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb11.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Konsultasi Internal Poliklinik</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Poliklinik Tujuan</td>"
                                            + "<td valign='top' width='13%' bgcolor='#f8fdf3'>Jns. Konsul</td>"
                                            + "<td valign='top' width='13%' bgcolor='#f8fdf3'>Tgl. Konsul</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Tujuan Konsul</td>"
                                            + "<td valign='top' width='150%' bgcolor='#f8fdf3'>Permintaan/Ket. Konsul</td>"
                                            + "<td valign='top' width='150%' bgcolor='#f8fdf3'>Uraian Jawaban Konsul</td>"
                                            + "<td valign='top' width='24%' bgcolor='#f8fdf3'>Dijawab Tgl.</td>"
                                            + "<td valign='top' width='24%' bgcolor='#f8fdf3'>Tgl. Konsul Ulang</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Dijawab Oleh Dokter</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        String tujuan = "", tgljwb = "", tglkonsulUlang = "", cekJawaban = "";
                                        if (rs3.getString("tujuan").equals("Lainnya")) {
                                            tujuan = "<td valign='top'>" + rs3.getString("tujuan") + " (" + rs3.getString("ket_tujuan_lain") + ")</td>";
                                        } else {
                                            tujuan = "<td valign='top'>" + rs3.getString("tujuan") + "</td>";
                                        }

                                        if (rs3.getString("tgljawab").equals("-")) {
                                            tgljwb = "<td valign='top' align='center'>-</td>";
                                        } else {
                                            tgljwb = "<td valign='top' align='center'>" + rs3.getString("tglmenjawab") + "</td>";
                                        }

                                        if (rs3.getString("konsul_ulang").equals("tidak")) {
                                            tglkonsulUlang = "<td valign='top' align='center'>-</td>";
                                        } else {
                                            tglkonsulUlang = "<td valign='top' align='center'>" + rs3.getString("tglkonsululang") + "</td>";
                                        }

                                        if (rs3.getString("no_rawat_pembalas").equals("-")) {
                                            cekJawaban = "<td valign='top'>-</td>";
                                        } else {
                                            if (rs3.getString("kasus_ditemukan").equals("")) {
                                                cekJawaban = "<td valign='top'>" + rs3.getString("ket_klinis_jawaban").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "<br><br></td>";
                                            } else {
                                                cekJawaban = "<td valign='top'>Ditemukan kasus : " + rs3.getString("kasus_ditemukan") + "<br><br>" + rs3.getString("ket_klinis_jawaban").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "<br><br></td>";
                                            }
                                        }

                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("poliTujuan") + "</td>"
                                                + "<td valign='top' align='center'>" + rs3.getString("jenis_konsul") + "</td>"
                                                + "<td valign='top' align='center'>" + rs3.getString("tglKonsul") + "</td>"
                                                + tujuan
                                                + "<td valign='top'>" + rs3.getString("keterangan_klinis").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "<br><br></td>"
                                                + cekJawaban
                                                + tgljwb
                                                + tglkonsulUlang
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan rujukan internal poliklinik
                            try {
                                StringBuilder sb12 = new StringBuilder();
                                sb12.append("SELECT ifnull(pl.nm_poli,'-') ke_poli, ifnull(DATE_FORMAT(ri.tgl_rencana_dirujuk,'%d-%m-%Y'),'-') tgl_dirujuk, ");
                                sb12.append("ifnull(ri.keterangan,'-') keterangan, ifnull(ri.keterangan_balasan,'-') jwbn, ifnull(d.nm_dokter,'') drMenjawab FROM rujukan_internal_poli ri ");
                                sb12.append("INNER JOIN poliklinik pl on pl.kd_poli=ri.kd_poli_pembalas left join dokter d on d.kd_dokter=ri.kd_dokter_pembalas ");
                                sb12.append("WHERE ri.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb12.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dirujuk Internal Ke</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Poliklinik/Inst.</td>"
                                            + "<td valign='top' width='13%' bgcolor='#f8fdf3'>Renc. Dirujuk</td>"
                                            + "<td valign='top' width='150%' bgcolor='#f8fdf3'>Isi/Pesan Rujukan</td>"
                                            + "<td valign='top' width='150%' bgcolor='#f8fdf3'>Balasan/Jawaban Rujukan</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("ke_poli") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_dirujuk") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keterangan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("jwbn").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "<br><br>Ttd.<br>" + rs3.getString("drMenjawab") + "</td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan riwayat pemeriksaan ralan petugas
                            try {
                                StringBuilder sb13 = new StringBuilder();
                                sb13.append("select pemeriksaan_ralan_petugas.suhu_tubuh,pemeriksaan_ralan_petugas.tensi,pemeriksaan_ralan_petugas.nadi,pemeriksaan_ralan_petugas.respirasi,");
                                sb13.append("pemeriksaan_ralan_petugas.tinggi,pemeriksaan_ralan_petugas.berat,pemeriksaan_ralan_petugas.gcs,pemeriksaan_ralan_petugas.keluhan, ");
                                sb13.append("pemeriksaan_ralan_petugas.pemeriksaan,pemeriksaan_ralan_petugas.alergi,ifnull(pemeriksaan_ralan_petugas.diagnosa,'-') diagnosa, ");
                                sb13.append("ifnull(pemeriksaan_ralan_petugas.rincian_tindakan,'-') rincian_tindakan, ");
                                sb13.append("ifnull(pemeriksaan_ralan_petugas.terapi,'-') terapi, ifnull(pemeriksaan_ralan_petugas.spo2,'-') spo2 from pemeriksaan_ralan_petugas where ");
                                sb13.append("pemeriksaan_ralan_petugas.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb13.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Petugas</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='2%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='14%' bgcolor='#f8fdf3'>Keluhan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Diagnosa</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Alergi</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Suhu(C)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tensi</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Berat(Kg)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>SPO2</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Rincian Tindakan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Terapi</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("diagnosa").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("alergi").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("suhu_tubuh") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tensi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nadi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("respirasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tinggi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("berat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("gcs") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("spo2") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("rincian_tindakan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("terapi").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //hasil pemeriksaan laboratorium LIS
                            try {
                                StringBuilder sb14 = new StringBuilder();
                                sb14.append("SELECT no_lab FROM lis_reg WHERE no_rawat='" + rs2.getString("no_rawat") + "' ORDER BY no_lab");
                                rsLISMaster = koneksi.prepareStatement(sb14.toString()).executeQuery();

                                if (rsLISMaster.next()) {
                                    rsLISMaster.beforeFirst();
                                    lisM = 1;
                                    while (rsLISMaster.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Laboratorium</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                                + "<tr><td valign='top' colspan='6'>No. Lab. : " + rsLISMaster.getString("no_lab") + "</td></td></tr>"
                                                + "<tr align='center'>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Jenis Pemeriksaan/Item</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Metode Pemeriksaan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Hasil</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Rujukan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Satuan</td>"
                                                + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Flag Kode</td>"
                                                + "</tr>"
                                        );

                                        StringBuilder sb15 = new StringBuilder();
                                        sb15.append("SELECT ifnull(kategori_pemeriksaan_nama,'') kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab ");
                                        sb15.append("WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lr.no_lab ='" + rsLISMaster.getString("no_lab") + "' GROUP BY lhp.kategori_pemeriksaan_nama ");
                                        sb15.append("ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut, lhp.pemeriksaan_no_urut");
                                        rsLIS1 = koneksi.prepareStatement(sb15.toString()).executeQuery();

                                        if (rsLIS1.next()) {
                                            rsLIS1.beforeFirst();
                                            w = 1;
                                            while (rsLIS1.next()) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top'>" + rsLIS1.getString("kategori_pemeriksaan_nama") + "</td>"
                                                        + "</tr>");

                                                StringBuilder sb16 = new StringBuilder();
                                                sb16.append("SELECT ifnull(lhp.sub_kategori_pemeriksaan_nama,'') sub_kategori_pemeriksaan_nama FROM lis_reg lr ");
                                                sb16.append("LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab ");
                                                sb16.append("WHERE lr.no_lab='" + rsLISMaster.getString("no_lab") + "' and lhp.kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' ");
                                                sb16.append("GROUP BY lhp.sub_kategori_pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut, ");
                                                sb16.append("lhp.sub_kategori_pemeriksaan_nama desc, lhp.pemeriksaan_no_urut");
                                                rsLIS2 = koneksi.prepareStatement(sb16.toString()).executeQuery();
                                                
                                                if (rsLIS2.next()) {
                                                    rsLIS2.beforeFirst();
                                                    lis1 = 1;
                                                    while (rsLIS2.next()) {
                                                        htmlContent.append(
                                                                "<tr>"
                                                                + "<td valign='top'>&emsp;" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "</td>"
                                                                + "</tr>");

                                                        StringBuilder sb17 = new StringBuilder();
                                                        sb17.append("SELECT ifnull(lhp.pemeriksaan_nama,'') pemeriksaan_nama, lhp.metode, lhp.nilai_hasil, lhp.nilai_rujukan, ");
                                                        sb17.append("lhp.satuan, lhp.flag_kode FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab ");
                                                        sb17.append("LEFT JOIN lis_hasil_data_pasien lhdp ON lhdp.no_lab=lr.no_lab WHERE lr.no_lab='" + rsLISMaster.getString("no_lab") + "' and ");
                                                        sb17.append("lhp.sub_kategori_pemeriksaan_nama='" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "' and ");
                                                        sb17.append("lhp.kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' GROUP BY lhp.pemeriksaan_nama ");
                                                        sb17.append("ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut, lhp.pemeriksaan_no_urut");
                                                        rsLIS3 = koneksi.prepareStatement(sb17.toString()).executeQuery();
                                                        
                                                        if (rsLIS3.next()) {
                                                            rsLIS3.beforeFirst();
                                                            lis2 = 1;
                                                            while (rsLIS3.next()) {
                                                                htmlContent.append(
                                                                        "<tr>"
                                                                        + "<td valign='top'>&emsp;&emsp;" + rsLIS3.getString("pemeriksaan_nama") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("metode") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_hasil") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_rujukan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("satuan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("flag_kode") + "</td>"
                                                                        + "</tr>");
                                                                lis2++;
                                                            }
                                                        }
                                                        lis1++;
                                                    }
                                                }
                                                w++;
                                            }
                                            htmlContent.append(
                                                    "</table><br/>");
                                        }
                                    }
                                }

                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsLIS1 != null) {
                                    rsLIS1.close();
                                }
                            }

                            //hasil pemeriksaan radiologi
                            try {
                                StringBuilder sb18 = new StringBuilder();
                                sb18.append("SELECT date_format(pr.tgl_periksa, '%d-%m-%Y') tgl_periksa, date_format(pr.jam, '%h:%i %p') jam, ");
                                sb18.append("ifnull(jpr.nm_perawatan,'-') nm_pemeriksaan, ifnull(hr.diag_klinis_radiologi, '-') diag_klinis_radiologi, ");
                                sb18.append("ifnull(hr.hasil, '-') hasil FROM periksa_radiologi pr INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw ");
                                sb18.append("LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.kd_jenis_prw=pr.kd_jenis_prw AND hr.tgl_periksa=pr.tgl_periksa AND hr.jam=pr.jam ");
                                sb18.append("WHERE pr.no_rawat='" + rs2.getString("no_rawat") + "' ORDER BY pr.tgl_periksa, pr.jam");
                                rs3 = koneksi.prepareStatement(sb18.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Radiologi</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tgl. Periksa</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Diagnosa Klinis</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Item/Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Bacaan/Hasil Pemeriksaan</td>"
                                            + "</tr>"
                                    );

                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("diag_klinis_radiologi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("hasil").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan tarif klaim inacbg ralan
                            try {
                                StringBuilder sb19 = new StringBuilder();
                                sb19.append("SELECT ifnull(enc.no_rawat,'') no_rawat, ifnull(enc.klaim_final,'') klaim_final, ifnull(eg.cbg_desc,'') cbg_desc, ");
                                sb19.append("IFNULL(egsc.desc,'-') topup_desc, concat('Rp. ',format(ifnull(eg.cbg_tarif,''),0)) cbg_tarif, ");
                                sb19.append("concat('Rp. ',IFNULL(format(egsc.tarif,0),0)) topup_tarif, concat('Rp. ',IFNULL(format(eg.cbg_tarif+egsc.tarif,0),format(eg.cbg_tarif,0))) total_trf_grp, ");
                                sb19.append("concat('Rp. ',format(ifnull(esc.tarif_obat,''),0)) by_obat_real, CONCAT(FORMAT((esc.tarif_obat/IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100,2),' ','%') perc_pakai_obat, ");
                                sb19.append("IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=40,'#00ff00', ");
                                sb19.append("IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100>40 AND (esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=80,'#ff8040','#ff3333')) warna_sel ");
                                sb19.append("FROM eklaim_new_claim enc INNER JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep INNER JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep ");
                                sb19.append("INNER JOIN reg_periksa rp ON rp.no_rawat=enc.no_rawat INNER JOIN poliklinik p ON p.kd_poli=rp.kd_poli INNER JOIN dokter d ON d.kd_dokter=rp.kd_dokter ");
                                sb19.append("LEFT JOIN eklaim_grouping_spc_cmg egsc ON egsc.no_sep=enc.no_sep WHERE rp.status_lanjut='Ralan' and enc.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb19.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarif Klaim INACBG</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Status Klaim</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Deskripsi CBG</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Deskripsi TopUp</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Tarif CBG</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>TopUp Tarif</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Total Tarif Grouping</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Biaya Real Obat</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Pemakaian Obat</td>"
                                            + "</tr>"
                                    );

                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("klaim_final") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("total_trf_grp") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("by_obat_real") + "</td>"
                                                + "<td valign='top' bgcolor='" + rs3.getString("warna_sel") + "'><b>" + rs3.getString("perc_pakai_obat") + "</b></td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //biaya administrasi
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Biaya & Perawatan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>"
                                    + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    + "<tr>"
                                    + "<td valign='top' width='89%'>Administrasi</td>"
                                    + "<td valign='top' width='1%' align='right'>:</td>"
                                    + "<td valign='top' width='10%' align='right'>" + Valid.SetAngka(rs2.getDouble("biaya_reg")) + "</td>"
                                    + "</tr>"
                                    + "</table>"
                            );

                            //tindakan dokter ralan
                            try {
                                StringBuilder sb20 = new StringBuilder();
                                sb20.append("select rawat_jl_dr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,rawat_jl_dr.biaya_rawat ");
                                sb20.append("from rawat_jl_dr inner join jns_perawatan inner join dokter ");
                                sb20.append("on rawat_jl_dr.kd_jenis_prw=jns_perawatan.kd_jenis_prw ");
                                sb20.append("and rawat_jl_dr.kd_dokter=dokter.kd_dokter where rawat_jl_dr.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb20.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Jalan Dokter</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan paramedis ralan
                            try {
                                StringBuilder sb21 = new StringBuilder();
                                sb21.append("select rawat_jl_pr.kd_jenis_prw,jns_perawatan.nm_perawatan,petugas.nama,rawat_jl_pr.biaya_rawat ");
                                sb21.append("from rawat_jl_pr inner join jns_perawatan inner join petugas ");
                                sb21.append("on rawat_jl_pr.kd_jenis_prw=jns_perawatan.kd_jenis_prw ");
                                sb21.append("and rawat_jl_pr.nip=petugas.nip where rawat_jl_pr.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb21.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Jalan Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan ralan dokter dan paramedis
                            try {
                                StringBuilder sb22 = new StringBuilder();
                                sb22.append("select rawat_jl_drpr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,petugas.nama,rawat_jl_drpr.biaya_rawat ");
                                sb22.append("from rawat_jl_drpr inner join jns_perawatan inner join dokter inner join petugas ");
                                sb22.append("on rawat_jl_drpr.kd_jenis_prw=jns_perawatan.kd_jenis_prw and rawat_jl_drpr.nip=petugas.nip ");
                                sb22.append("and rawat_jl_drpr.kd_dokter=dokter.kd_dokter where rawat_jl_drpr.no_rawat='" + rs2.getString("no_rawat") + "'");                                
                                rs3 = koneksi.prepareStatement(sb22.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Tindakan Rawat Jalan Dokter & Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='25%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //operasi
                            try {
                                StringBuilder sb23 = new StringBuilder();
                                sb23.append("select DATE_FORMAT(operasi.tgl_operasi,'%d-%m-%Y %h:%i %p') tgl_operasi,operasi.jenis_anasthesi,operasi.operator1, operasi.operator2, operasi.operator3, operasi.asisten_operator1,");
                                sb23.append("operasi.asisten_operator2, operasi.instrumen, operasi.dokter_anak, operasi.perawaat_resusitas, ");
                                sb23.append("operasi.dokter_anestesi, operasi.asisten_anestesi, operasi.bidan, operasi.bidan2, operasi.bidan3, operasi.perawat_luar, operasi.omloop,");
                                sb23.append("operasi.omloop2,operasi.omloop3,operasi.dokter_pjanak,operasi.dokter_umum, ");
                                sb23.append("operasi.kode_paket,paket_operasi.nm_perawatan, operasi.biayaoperator1, operasi.biayaoperator2, operasi.biayaoperator3, ");
                                sb23.append("operasi.biayaasisten_operator1, operasi.biayaasisten_operator2, operasi.biayainstrumen, ");
                                sb23.append("operasi.biayadokter_anak, operasi.biayaperawaat_resusitas, operasi.biayadokter_anestesi, ");
                                sb23.append("operasi.biayaasisten_anestesi, operasi.biayabidan,operasi.biayabidan2,operasi.biayabidan3, operasi.biayaperawat_luar, operasi.biayaalat,");
                                sb23.append("operasi.biayasewaok,operasi.akomodasi,operasi.bagian_rs,operasi.biaya_omloop,operasi.biaya_omloop2,operasi.biaya_omloop3,");
                                sb23.append("operasi.biayasarpras,operasi.biaya_dokter_pjanak,operasi.biaya_dokter_umum,");
                                sb23.append("(operasi.biayaoperator1+operasi.biayaoperator2+operasi.biayaoperator3+");
                                sb23.append("operasi.biayaasisten_operator1+operasi.biayaasisten_operator2+operasi.biayainstrumen+");
                                sb23.append("operasi.biayadokter_anak+operasi.biayaperawaat_resusitas+operasi.biayadokter_anestesi+");
                                sb23.append("operasi.biayaasisten_anestesi+operasi.biayabidan+operasi.biayabidan2+operasi.biayabidan3+operasi.biayaperawat_luar+operasi.biayaalat+");
                                sb23.append("operasi.biayasewaok+operasi.akomodasi+operasi.bagian_rs+operasi.biaya_omloop+operasi.biaya_omloop2+operasi.biaya_omloop3+");
                                sb23.append("operasi.biayasarpras+operasi.biaya_dokter_pjanak+operasi.biaya_dokter_umum) as total from operasi inner join paket_operasi ");
                                sb23.append("on operasi.kode_paket=paket_operasi.kode_paket where operasi.no_rawat='" + rs2.getString("no_rawat") + "' order by operasi.tgl_operasi");
                                rs3 = koneksi.prepareStatement(sb23.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Operasi/VK</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Tindakan</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Anastesi</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_operasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + " (");
                                        if (rs3.getDouble("biayaoperator1") > 0) {
                                            htmlContent.append("Operator 1 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator1")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaoperator2") > 0) {
                                            htmlContent.append("Operator 2 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaoperator3") > 0) {
                                            htmlContent.append("Operator 3 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator3")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_operator1") > 0) {
                                            htmlContent.append("Asisten Operator 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_operator1")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_operator2") > 0) {
                                            htmlContent.append("Asisten Operator 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_operator2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayainstrumen") > 0) {
                                            htmlContent.append("Instrumen : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("instrumen")) + ", ");
                                        }
                                        if (rs3.getDouble("biayadokter_anak") > 0) {
                                            htmlContent.append("Dokter Anak : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_anak")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaperawaat_resusitas") > 0) {
                                            htmlContent.append("Perawat Resusitas : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("perawaat_resusitas")) + ", ");
                                        }
                                        if (rs3.getDouble("biayadokter_anestesi") > 0) {
                                            htmlContent.append("Dokter Anestesi : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_anestesi")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_anestesi") > 0) {
                                            htmlContent.append("Asisten Anestesi : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_anestesi")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan") > 0) {
                                            htmlContent.append("Bidan 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan2") > 0) {
                                            htmlContent.append("Bidan 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan3") > 0) {
                                            htmlContent.append("Bidan 3 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan3")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaperawat_luar") > 0) {
                                            htmlContent.append("Perawat Luar : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("perawat_luar")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop") > 0) {
                                            htmlContent.append("Onloop 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop2") > 0) {
                                            htmlContent.append("Onloop 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop2")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop3") > 0) {
                                            htmlContent.append("Onloop 3 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop3")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_dokter_pjanak") > 0) {
                                            htmlContent.append("Dokter Pj Anak : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_pjanak")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_dokter_umum") > 0) {
                                            htmlContent.append("Dokter Umum : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_umum")) + ", ");
                                        }
                                        htmlContent.append(
                                                ")</td>"
                                                + "<td valign='top'>" + rs3.getString("jenis_anasthesi") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan pemeriksaan radiologi
                            try {
                                StringBuilder sb24 = new StringBuilder();
                                sb24.append("select date_format(periksa_radiologi.tgl_periksa,'%d-%m-%Y') tgl_periksa,date_format(periksa_radiologi.jam,'%h:%i %p') jam,periksa_radiologi.kd_jenis_prw, ");
                                sb24.append("jns_perawatan_radiologi.nm_perawatan,petugas.nama,periksa_radiologi.biaya,periksa_radiologi.dokter_perujuk,dokter.nm_dokter ");
                                sb24.append("from periksa_radiologi inner join jns_perawatan_radiologi inner join petugas inner join dokter ");
                                sb24.append("on periksa_radiologi.kd_jenis_prw=jns_perawatan_radiologi.kd_jenis_prw and periksa_radiologi.kd_dokter=dokter.kd_dokter ");
                                sb24.append("and periksa_radiologi.nip=petugas.nip  where periksa_radiologi.no_rawat='" + rs2.getString("no_rawat") + "' ");
                                sb24.append("order by periksa_radiologi.tgl_periksa,periksa_radiologi.jam");
                                rs3 = koneksi.prepareStatement(sb24.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemeriksaan Radiologi</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tgl. Pemeriksaan</td>"
                                            + "<td valign='top' width='26%' bgcolor='#f8fdf3'>Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Dokter Pemeriksa Rad.</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Petugas</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //gambar pemeriksaan radiologi
                            try {
                                host = Sequel.decXML(prop.getProperty("HOST"), prop.getProperty("KEY"));
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(tgl_periksa,'%d-%m-%Y') tgl_periksa,date_format(jam,'%h:%i %p') jam, "
                                        + "lokasi_gambar from gambar_radiologi where no_rawat='" + rs2.getString("no_rawat") + "' order by tgl_periksa,jam").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='3'>Gambar Radiologi</td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Gambar Radiologi</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'><a href='http://" + host + ":" + prop.getProperty("PORTWEB") + "/" + prop.getProperty("HYBRIDWEB") + "/radiologi/" + rs3.getString("lokasi_gambar") + "'>" + rs3.getString("lokasi_gambar").replaceAll("pages/upload/", "") + "</a></td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan pemeriksaan laborat
                            try {
                                StringBuilder sb25 = new StringBuilder();
                                sb25.append("SELECT DISTINCT dp.no_rawat, d.nm_dokter, pt.nama, '' nm_perawatan, '' Pemeriksaan, '' qty, '' total ");
                                sb25.append("FROM detail_periksa_lab dp INNER JOIN periksa_lab pl ON pl.no_rawat = dp.no_rawat ");
                                sb25.append("INNER JOIN dokter d ON d.kd_dokter = pl.kd_dokter INNER JOIN petugas pt ON pt.nip = pl.nip ");
                                sb25.append("WHERE dp.no_rawat = '" + rs2.getString("no_rawat") + "' UNION ALL ");
                                sb25.append("SELECT dp.no_rawat, '', '',j.nm_perawatan, tl.Pemeriksaan, count(dp.kd_jenis_prw) qty, sum(tl.biaya_item) total ");
                                sb25.append("FROM detail_periksa_lab dp LEFT JOIN jns_perawatan_lab j ON dp.kd_jenis_prw = j.kd_jenis_prw ");
                                sb25.append("LEFT JOIN template_laboratorium tl ON dp.id_template = tl.id_template ");
                                sb25.append("WHERE dp.no_rawat = '" + rs2.getString("no_rawat") + "' GROUP BY dp.no_rawat, j.nm_perawatan, tl.Pemeriksaan");
                                rs3 = koneksi.prepareStatement(sb25.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemeriksaan Laboratorium</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='18%' bgcolor='#f8fdf3'>Dokter Pnggng. Jwb. Lab.</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Nama Petugas</td>"
                                            + "<td valign='top' width='16%' bgcolor='#f8fdf3'>Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Item Pemeriksaan</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Qty.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya/Tarif</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("Pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("qty") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>"
                                        );
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //pemberian obat
                            try {
                                StringBuilder sb26 = new StringBuilder();
                                sb26.append("select date_format(detail_pemberian_obat.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,date_format(detail_pemberian_obat.jam,'%h:%i %p') jam,databarang.kode_sat, ");
                                sb26.append("detail_pemberian_obat.kode_brng,detail_pemberian_obat.jml,detail_pemberian_obat.total,");
                                sb26.append("databarang.nama_brng from detail_pemberian_obat inner join databarang ");
                                sb26.append("on detail_pemberian_obat.kode_brng=databarang.kode_brng ");
                                sb26.append("where detail_pemberian_obat.no_rawat='" + rs2.getString("no_rawat") + "' order by detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam");
                                rs3 = koneksi.prepareStatement(sb26.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemberian Obat/BHP/Alkes</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='35%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Aturan Pakai</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jml") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top'>" + Sequel.cariIsi("select concat('Aturan pakai : ',aturan1,' ',aturan2,' ',aturan3,', Waktu : ',waktu1,' ',waktu2,', Keterangan : ',keterangan,', Masa simpan : ',waktu_simpan) "
                                                        + "from aturan_pakai where tgl_perawatan='" + rs3.getString("tgl_perawatan") + "' and "
                                                        + "jam='" + rs3.getString("jam") + "' and no_rawat='" + rs2.getString("no_rawat") + "' and "
                                                        + "kode_brng='" + rs3.getString("kode_brng") + "'") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //pemberian obat Operasi
                            try {
                                StringBuilder sb27 = new StringBuilder();
                                sb27.append("select date_format(beri_obat_operasi.tanggal,'%d-%m-%Y') tanggal,beri_obat_operasi.kd_obat,beri_obat_operasi.hargasatuan,obatbhp_ok.kode_sat, ");
                                sb27.append("beri_obat_operasi.jumlah, obatbhp_ok.nm_obat,(beri_obat_operasi.hargasatuan*beri_obat_operasi.jumlah) as total ");
                                sb27.append("from beri_obat_operasi inner join obatbhp_ok  on  beri_obat_operasi.kd_obat=obatbhp_ok.kd_obat ");
                                sb27.append("where beri_obat_operasi.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb27.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Penggunaan Obat/BHP Operasi</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Obat/BHP</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tanggal") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_obat") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jumlah") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Resep Pulang
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select resep_pulang.kode_brng,databarang.nama_brng,resep_pulang.dosis,resep_pulang.jml_barang, "
                                        + "databarang.kode_sat,resep_pulang.dosis,resep_pulang.total from resep_pulang inner join databarang "
                                        + "on resep_pulang.kode_brng=databarang.kode_brng where "
                                        + "resep_pulang.no_rawat='" + rs2.getString("no_rawat") + "' order by databarang.nama_brng").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Resep Pulang</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Dosis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("dosis") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jml_barang") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Retur Obat
                            try {
                                StringBuilder sb28 = new StringBuilder();
                                sb28.append("select databarang.kode_brng,databarang.nama_brng,detreturjual.kode_sat,detreturjual.h_retur, ");
                                sb28.append("(detreturjual.jml_retur * -1) as jumlah,(detreturjual.subtotal * -1) as total from detreturjual ");
                                sb28.append("inner join databarang inner join returjual on detreturjual.kode_brng=databarang.kode_brng ");
                                sb28.append("and returjual.no_retur_jual=detreturjual.no_retur_jual where returjual.no_retur_jual='" + rs2.getString("no_rawat") + "' order by databarang.nama_brng");
                                rs3 = koneksi.prepareStatement(sb28.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='3'>Retur Obat</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='65%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jumlah") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Tambahan Biaya
                            try {
                                StringBuilder sb29 = new StringBuilder();
                                sb29.append("select nama_biaya, besar_biaya from tambahan_biaya where no_rawat='" + rs2.getString("no_rawat") + "' order by nama_biaya");
                                rs3 = koneksi.prepareStatement(sb29.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='2'>Tambahan Biaya</td><td valign='top' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='84%' bgcolor='#f8fdf3'>Nama Tambahan</td>"
                                            + "<td valign='top' width='1%' bgcolor='#f8fdf3'></td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_biaya") + "</td>"
                                                + "<td valign='top'></td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("besar_biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Pengurangan Biaya
                            try {
                                StringBuilder sb30 = new StringBuilder();
                                sb30.append("select nama_pengurangan, (-1*besar_pengurangan) as besar_pengurangan from pengurangan_biaya where no_rawat='" + rs2.getString("no_rawat") + "' order by nama_pengurangan");
                                rs3 = koneksi.prepareStatement(sb30.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='2'>Potongan Biaya</td><td valign='top' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='84%' bgcolor='#f8fdf3'>Nama Potongan</td>"
                                            + "<td valign='top' width='1%' bgcolor='#f8fdf3'></td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_pengurangan") + "</td>"
                                                + "<td valign='top'></td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("besar_pengurangan")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3' bgcolor='#7eccb9'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                
                LoadHTML2.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void tampilRingkasan() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (!kdpoli.getText().equals("")) {
                a = " and reg_periksa.kd_poli='" + kdpoli.getText() + "'";
            } else {
                a = "";
            }
            StringBuilder htmlContent = new StringBuilder();
            try {
                StringBuilder sb1 = new StringBuilder();
                sb1.append("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, ");
                sb1.append("tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar, sb.nama_suku_bangsa from pasien ");
                sb1.append("inner join kelurahan inner join kecamatan inner join kabupaten on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and ");
                sb1.append("pasien.kd_kab=kabupaten.kd_kab inner join suku_bangsa sb on sb.id=pasien.suku_bangsa where pasien.no_rkm_medis='" + TNoRM.getText() + "' order by pasien.no_rkm_medis desc");
                rs = koneksi.prepareStatement(sb1.toString()).executeQuery();

                y = 1;
                while (rs.next()) {
                    try {
                        StringBuilder sb2 = new StringBuilder();
                        if (ChkTanggal.isSelected() == true) {
                            sb2.append("select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,");
                            sb2.append("reg_periksa.kd_dokter,dokter.nm_dokter,IF(reg_periksa.kd_poli='IRM',CONCAT(poliklinik.nm_poli,' - ',IFNULL(data_rehab_medik.jns_rehabmedik,'FISIOTERAPI')),poliklinik.nm_poli) nm_poli,");
                            sb2.append("reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,");
                            sb2.append("penjab.png_jawab, reg_periksa.kd_poli, concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur,'.') usia from reg_periksa ");
                            sb2.append("inner join dokter inner join poliklinik inner join penjab ");
                            sb2.append("on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj ");
                            sb2.append("and reg_periksa.kd_poli=poliklinik.kd_poli LEFT JOIN data_rehab_medik ON data_rehab_medik.no_rawat = reg_periksa.no_rawat where ");
                            sb2.append("stts<>'Batal' and reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and ");
                            sb2.append("reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari5.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari6.getSelectedItem() + "") + "'" + a);
                            rs2 = koneksi.prepareStatement(sb2.toString()).executeQuery();
                        } else {
                            sb2.append("select * from (select a.no_reg, a.no_rawat,date_format(a.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(a.jam_reg,'%h:%i %p') jam_reg,a.kd_dokter,a.nm_dokter,");
                            sb2.append("a.nm_poli,a.p_jawab,a.almt_pj,a.hubunganpj,a.biaya_reg,if(a.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,a.png_jawab, ");
                            sb2.append("a.tgl_registrasi tglReg, a.jam_reg jamReg, a.kd_poli, a.usia from (select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,");
                            sb2.append("reg_periksa.kd_dokter,dokter.nm_dokter,IF(reg_periksa.kd_poli='IRM',CONCAT(poliklinik.nm_poli,' - ',IFNULL(data_rehab_medik.jns_rehabmedik,'FISIOTERAPI')),poliklinik.nm_poli) nm_poli,");
                            sb2.append("reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.status_lanjut,penjab.png_jawab, reg_periksa.kd_poli, ");
                            sb2.append("concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur,'.') usia from reg_periksa inner join dokter inner join poliklinik inner join penjab ");
                            sb2.append("on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj ");
                            sb2.append("and reg_periksa.kd_poli=poliklinik.kd_poli LEFT JOIN data_rehab_medik ON data_rehab_medik.no_rawat = reg_periksa.no_rawat where ");
                            sb2.append("stts<>'Batal' and reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "'" + a + ") as a ");
                            sb2.append("ORDER BY a.tgl_registrasi desc, a.jam_reg desc limit 3) as a order by a.tglReg, a.jamReg");
                            rs2 = koneksi.prepareStatement(sb2.toString()).executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Kunjungan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + " (" + rs2.getString("nm_dokter") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + " (" + rs2.getString("png_jawab") + ")</td>"
                                    + "</tr>"
                            );
                            urut++;

                            //menampilkan catatan diagnosa
//                            try {
//                                rsDiag=koneksi.prepareStatement(
//                                        "Select ifnull(diagnosa,'-') diagnosa from pemeriksaan_ralan "+                                        
//                                        "where no_rawat='"+rs2.getString("no_rawat")+"'").executeQuery();
//                                if(rsDiag.next()){
//                                    htmlContent.append(
//                                            "<tr class='isi'>"+ 
//                                "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Catatan Diagnosa</td>"+
//                                "<td valign='top' width='1%' align='center'>:</td>"+
//                                "<td valign='top' width='79%'>"+rsDiag.getString("diagnosa")+"</td>"+
//                                      "</tr>");
//                                }                                    
//                            } catch (Exception e) {
//                                System.out.println("Notifikasi : "+e);
//                            } finally{
//                                if(rsDiag!=null){
//                                    rsDiag.close();
//                                }
//                            }
                            //menampilkan rencana follow up dokter
                            try {
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append("Select ifnull(rencana_follow_up,'-') rencana_follow_up from pemeriksaan_ralan ");
                                sb3.append("where no_rawat='" + rs2.getString("no_rawat") + "'");
                                rsDiag = koneksi.prepareStatement(sb3.toString()).executeQuery();
                                
                                if (rsDiag.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rencana Follow Up Dari Dokter</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>" + rsDiag.getString("rencana_follow_up").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsDiag != null) {
                                    rsDiag.close();
                                }
                            }

                            //menampilkan rencana follow up perawat/bidan
                            try {
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append("Select ifnull(rencana_follow_up,'-') rencana_follow_up from pemeriksaan_ralan_petugas ");
                                sb4.append("where no_rawat='" + rs2.getString("no_rawat") + "'");
                                rsDiag1 = koneksi.prepareStatement(sb4.toString()).executeQuery();
                                
                                if (rsDiag1.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rencana Follow Up Dari Perawat/Bidan</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>" + rsDiag1.getString("rencana_follow_up").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsDiag1 != null) {
                                    rsDiag1.close();
                                }
                            }

                            //menampilkan catatan Resep Obat
                            try {
                                StringBuilder sb5 = new StringBuilder();
                                sb5.append("Select nama_obat,status from catatan_resep where no_rawat='" + rs2.getString("no_rawat") + "'");
                                rsObat = koneksi.prepareStatement(sb5.toString()).executeQuery();

                                if (rsObat.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Catatan Resep Obat</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='85%' bgcolor='#f8fdf3'>Nama Obat</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Status</td></tr>");

                                    rsObat.beforeFirst();
                                    w = 1;
                                    while (rsObat.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rsObat.getString("nama_obat") + "</td>"
                                                + "<td valign='top'>" + rsObat.getString("status") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsObat != null) {
                                    rsObat.close();
                                }
                            }

                            //menampilkan diagnosa penyakit                            
                            try {
                                StringBuilder sb6 = new StringBuilder();
                                sb6.append("select diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien.status ");
                                sb6.append("from diagnosa_pasien inner join penyakit ");
                                sb6.append("on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit ");
                                sb6.append("where diagnosa_pasien.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb6.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Diagnosa ICD-10</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Kode ICD-10</td>"
                                            + "<td valign='top' width='85%' bgcolor='#f8fdf3'>Deskripsi Diagnosa ICD-10</td>"
                                            + "</tr>");

                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("kd_penyakit") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_penyakit") + "</td>"
                                                + "</tr>");

                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan prosedur tindakan
                            try {
                                StringBuilder sb7 = new StringBuilder();
                                sb7.append("select prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.status ");
                                sb7.append("from prosedur_pasien inner join icd9 on prosedur_pasien.kode=icd9.kode ");
                                sb7.append("where prosedur_pasien.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb7.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Prosedur Tindakan/ICD-9</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Kode ICD-9</td>"
                                            + "<td valign='top' width='85%' bgcolor='#f8fdf3'>Deskripsi Tindakan/Prosedur ICD-9</td>"
                                            + "</tr>");

                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("kode") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("deskripsi_panjang") + "</td>"
                                                + "</tr>");

                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan riwayat pemeriksaan ralan dokter
                            try {
                                StringBuilder sb8 = new StringBuilder();
                                sb8.append("select pemeriksaan_ralan.suhu_tubuh,pemeriksaan_ralan.tensi,pemeriksaan_ralan.nadi,pemeriksaan_ralan.respirasi,");
                                sb8.append("pemeriksaan_ralan.tinggi,pemeriksaan_ralan.berat,pemeriksaan_ralan.gcs,pemeriksaan_ralan.keluhan, ");
                                sb8.append("pemeriksaan_ralan.pemeriksaan,pemeriksaan_ralan.alergi,ifnull(pemeriksaan_ralan.diagnosa,'-') diagnosa, ");
                                sb8.append("ifnull(pemeriksaan_ralan.rincian_tindakan,'-') rincian_tindakan, ifnull(pemeriksaan_ralan.terapi,'-') terapi, ");
                                sb8.append("ifnull(pemeriksaan_ralan.spo2,'-') spo2 from pemeriksaan_ralan where ");
                                sb8.append("pemeriksaan_ralan.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb8.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Dokter</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='2%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='7%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Keluhan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Diagnosa</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Alergi</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Suhu(C)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tensi</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Berat(Kg)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>SPO2</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Rincian Tindakan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Terapi</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("diagnosa").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("alergi").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("suhu_tubuh") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tensi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nadi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("respirasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tinggi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("berat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("gcs") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("spo2") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("rincian_tindakan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("terapi").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan pemeriksaan THT
                            if (rs2.getString("kd_poli").equals("THT")) {
                                try {
                                    StringBuilder sb9 = new StringBuilder();
                                    sb9.append("Select ifnull(nama_pemeriksaan,'-') namanya, ifnull(hasil_pemeriksaan,'-') hasilnya ");
                                    sb9.append("from pemeriksaan_tht where no_rawat='" + rs2.getString("no_rawat") + "'");
                                    rsTHT = koneksi.prepareStatement(sb9.toString()).executeQuery();
                                    
                                    if (rsTHT.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Tindakan THT</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>Nama Pemeriksaan : <span style='font-weight:bold'>" + rsTHT.getString("namanya").toUpperCase().replaceAll("(\r\n|\r|\n|\n\r)", "<br/>") + "</span>"
                                                + "<br/><br/><span style='font-weight:bold'>Hasil Pemeriksaan : </span><br/>" + rsTHT.getString("hasilnya").replaceAll("(\r\n|\r|\n|\n\r)", "<br/>") + "</td>"
                                                + "</tr>");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rsTHT != null) {
                                        rsTHT.close();
                                    }
                                }
                            }
                            
                            //menampilkan status kaki diabetes
                            if (Sequel.cariInteger("select count(-1) from data_dasar_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "'") > 0) {
                                try {
                                    StringBuilder sbk = new StringBuilder();
                                    sbk.append("Select *, date_format(tgl_masuk,'%d/%m/%Y') tglmsk, if(lama_rawat='','-',concat(lama_rawat,' hari')) lmrwt, ");
                                    sbk.append("if(lama_diketahui='','-',concat(lama_diketahui,' tahun (pembulatan ke bawah)')) lmDiketahui from data_dasar_kaki_diabetes ");
                                    sbk.append("where no_rawat='" + rs2.getString("no_rawat") + "'");
                                    rsDiabet = koneksi.prepareStatement(sbk.toString()).executeQuery();
                                    if (rsDiabet.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"                                                
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status Kaki Diabetes</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>"                                                
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                                + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>I. Data Dasar</span></td>"
                                                + "<tr align='center'>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Usia</td>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>TB/BB</td>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>BMI</td>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tensi</td>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Ras/Suku</td>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tgl. Masuk</td>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"                                                        
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Lama Rawat</td>"
                                                + "</tr>");
                                        rsDiabet.beforeFirst();
                                        while (rsDiabet.next()) {
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top'>" + rs2.getString("usia") + "</td>"
                                                    + "<td valign='top'>" + rsDiabet.getString("tb") + " Cm/" + rsDiabet.getString("bb") + " Kg</td>"
                                                    + "<td valign='top'>" + rsDiabet.getString("bmi") + " kg/m²</td>"
                                                    + "<td valign='top'>" + rsDiabet.getString("tensi") + " mmHg</td>"
                                                    + "<td valign='top'>" + rs.getString("nama_suku_bangsa") + "</td>"
                                                    + "<td valign='top'>" + rsDiabet.getString("tglmsk") + "</td>"
                                                    + "<td valign='top'>" + rsDiabet.getString("jns_rawat") + "</td>"
                                                    + "<td valign='top'>" + rsDiabet.getString("lmrwt") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>II. Anamnesis</span></td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Tipe Diabetes</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Lama Diketahui Diabetes</td>"                                                    
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("tipe_diabetes").equals("Lainnya")) {
                                                if (rsDiabet.getString("tipe_diabet_lain").equals("")) {
                                                    tipeDiabet = rsDiabet.getString("tipe_diabetes") + " : -";
                                                } else {
                                                    tipeDiabet = rsDiabet.getString("tipe_diabetes") + " : " + rsDiabet.getString("tipe_diabet_lain");
                                                }
                                            } else {
                                                tipeDiabet = rsDiabet.getString("tipe_diabetes");
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"                                                    
                                                    + "<td valign='top' colspan='3'>" + tipeDiabet + "</td>"
                                                    + "<td valign='top' colspan='2'>" + rsDiabet.getString("lmDiketahui") + "</td>"
                                                    + "</tr>");
                                            
                                            if (Sequel.cariInteger("select count(-1) from riwayat_pengobatan_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "'") > 0) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='8'>Riwayat Pengobatan Diabetes :</td>"
                                                        + "<tr align='center'>"
                                                        + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Obat</td>"
                                                        + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Jenis</td>"
                                                        + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Dosis</td>"
                                                        + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Lama</td>"
                                                        + "</tr>");
                                                
                                                //riwayat penggunaan obat
                                                try {
                                                    psRDO = koneksi.prepareStatement("SELECT * FROM riwayat_pengobatan_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "' order by waktu_simpan");
                                                    try {
                                                        rsRDO = psRDO.executeQuery();
                                                        while (rsRDO.next()) {
                                                            htmlContent.append(
                                                                    "<tr>"
                                                                    + "<td valign='top' colspan='2'>" + rsRDO.getString("obat") + "</td>"
                                                                    + "<td valign='top' colspan='2'>" + rsRDO.getString("jenis") + "</td>"
                                                                    + "<td valign='top' colspan='2'>" + rsRDO.getString("dosis") + "</td>"
                                                                    + "<td valign='top' colspan='2'>" + rsRDO.getString("lama") + "</td>"
                                                                    + "</tr>");
                                                        }
                                                    } catch (Exception e) {
                                                        System.out.println("Notifikasi : " + e);
                                                    } finally {
                                                        if (rsRDO != null) {
                                                            rsRDO.close();
                                                        }
                                                        if (psRDO != null) {
                                                            psRDO.close();
                                                        }
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("Notifikasi : " + e);
                                                }
                                                //---------------------------------
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr align='center'>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Merokok</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Lama Luka</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Riwayat Edukasi Kaki DM</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Jenis Alas Kaki</td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("merokok").equals("Ya")) {
                                                if (rsDiabet.getString("merokok_ya").equals("")) {
                                                    merokok = rsDiabet.getString("merokok");
                                                } else {
                                                    merokok = rsDiabet.getString("merokok") + " (" + rsDiabet.getString("merokok_ya") + " batang/hari)";
                                                }
                                            } else if (rsDiabet.getString("merokok").equals("Mantan")) {
                                                if (rsDiabet.getString("merokok_mantan").equals("")) {
                                                    merokok = rsDiabet.getString("merokok");
                                                } else {
                                                    merokok = rsDiabet.getString("merokok") + " (" + rsDiabet.getString("merokok_mantan") + " tahun lalu)";
                                                }
                                            } else {
                                                merokok = rsDiabet.getString("merokok");
                                            }
                                            
                                            if (rsDiabet.getString("lama_luka").equals("")) {
                                                lmLuka = "-";
                                            } else {
                                                if (rsDiabet.getString("satuan_lama_luka").equals("-")) {
                                                    lmLuka = rsDiabet.getString("lama_luka");
                                                } else {
                                                    lmLuka = rsDiabet.getString("lama_luka") + " " + rsDiabet.getString("satuan_lama_luka");
                                                }
                                            }
                                            
                                            if (rsDiabet.getString("jns_alas_kaki").equals("Sepatu")) {
                                                if (rsDiabet.getString("jns_alas_kaki_sepatu").equals("")) {
                                                    jnsAlas = rsDiabet.getString("jns_alas_kaki");
                                                } else {
                                                    jnsAlas = rsDiabet.getString("jns_alas_kaki") + " (" + rsDiabet.getString("jns_alas_kaki_sepatu") + " )";
                                                }
                                            } else {
                                                jnsAlas = rsDiabet.getString("jns_alas_kaki");
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"                                                    
                                                    + "<td valign='top' colspan='2'>" + merokok + "</td>"
                                                    + "<td valign='top' colspan='2'>" + lmLuka + "</td>"
                                                    + "<td valign='top' colspan='2'>" + rsDiabet.getString("riwayat_edukasi") + "</td>"
                                                    + "<td valign='top' colspan='2'>" + jnsAlas + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'>Penyebab :</td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Trauma Mekanik</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Trauma Kimia</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Trauma Termis</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Spontan</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Lain-Lain</td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("trauma_mekanik").equals("ya")) {
                                                if (rsDiabet.getString("tersandung").equals("ya")) {
                                                    tersandung = "Tersandung, ";
                                                } else {
                                                    tersandung = "";
                                                }
                                                
                                                if (rsDiabet.getString("memakai_sepatu").equals("ya")) {
                                                    memakai = "Memakai sepatu sempit, ";
                                                } else {
                                                    memakai = "";
                                                }
                                                
                                                if (rsDiabet.getString("tertusuk").equals("ya")) {
                                                    tertusuk = "Tertusuk paku/duri, ";
                                                } else {
                                                    tertusuk = "";
                                                }
                                                
                                                if (rsDiabet.getString("dll_sebutkan_mekanik").equals("ya")) {
                                                    if (rsDiabet.getString("ket_dll_sebutkan_mekanik").equals("")) {
                                                        dllTraMeka = "-";
                                                    } else {
                                                        dllTraMeka = rsDiabet.getString("ket_dll_sebutkan_mekanik");
                                                    }                                                    
                                                } else {
                                                    dllTraMeka = "";
                                                }
                                                traMekanik = tersandung + memakai + tertusuk + dllTraMeka;
                                            } else {
                                                traMekanik = "-";
                                            }

                                            if (rsDiabet.getString("trauma_kimia").equals("ya")) {
                                                if (rsDiabet.getString("terkena_zat").equals("ya")) {
                                                    if (rsDiabet.getString("ket_terkena_zat").equals("")) {
                                                        traKimia = "Terkena zat kimia";
                                                    } else {
                                                        traKimia = "Terkena zat kimia (" + rsDiabet.getString("ket_terkena_zat") + ")";
                                                    }
                                                } else {
                                                    traKimia = "-";
                                                }
                                            } else {
                                                traKimia = "-";
                                            }
                                            
                                            if (rsDiabet.getString("trauma_termis").equals("ya")) {
                                                if (rsDiabet.getString("terkena_air_panas").equals("ya")) {
                                                    terkenaAir = "Terkena air panas, ";
                                                } else {
                                                    terkenaAir = "";
                                                }
                                                
                                                if (rsDiabet.getString("terkena_pemanas").equals("ya")) {
                                                    terkenaPemanas = "Terkena pemanas listrik, ";
                                                } else {
                                                    terkenaPemanas = "";
                                                }

                                                if (rsDiabet.getString("dll_sebutkan_termis").equals("ya")) {
                                                    if (rsDiabet.getString("ket_dll_sebutkan_termis").equals("")) {
                                                        dllTraTermis = "-";
                                                    } else {
                                                        dllTraTermis = rsDiabet.getString("ket_dll_sebutkan_termis");
                                                    }                    
                                                } else {
                                                    dllTraTermis = "";
                                                }
                                                traTermis = terkenaAir + terkenaPemanas + dllTraTermis;
                                            } else {
                                                traTermis = "-";
                                            }
                                            
                                            if (rsDiabet.getString("spontan").equals("ya")) {
                                                spontan = "Ya";
                                            } else {
                                                spontan = "-";
                                            }
                                            
                                            if (rsDiabet.getString("penyebab_lain").equals("ya")) {
                                                if (rsDiabet.getString("ket_penyebab_lain").equals("")) {
                                                    lainPenyebab = "-";
                                                } else {
                                                    lainPenyebab = rsDiabet.getString("ket_penyebab_lain");
                                                }
                                            } else {
                                                lainPenyebab = "-";
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"                                                    
                                                    + "<td valign='top' colspan='2'>" + traMekanik + "</td>"
                                                    + "<td valign='top' colspan='2'>" + traKimia + "</td>"
                                                    + "<td valign='top' colspan='2'>" + traTermis + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + spontan + "</td>"
                                                    + "<td valign='top' colspan='1'>" + lainPenyebab + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'>Riwayat Luka/Ulkus : " + rsDiabet.getString("riwayat_ulkus") + "</td>"
                                                    + "</tr>");

                                            if (Sequel.cariInteger("select count(-1) from riwayat_ulkus_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "'") > 0) {
                                                htmlContent.append(
                                                        "<tr align='center'>"
                                                        + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Tahun</td>"
                                                        + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Lokasi</td>"
                                                        + "<td valign='top' colspan='4' bgcolor='#f8fdf3'>Penyebab</td>"
                                                        + "</tr>");

                                                //riwayat luka/ulkus
                                                try {
                                                    psRDU = koneksi.prepareStatement("SELECT * FROM riwayat_ulkus_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "' order by waktu_simpan");
                                                    try {
                                                        rsRDU = psRDU.executeQuery();
                                                        while (rsRDU.next()) {
                                                            htmlContent.append(
                                                                    "<tr>"
                                                                    + "<td valign='top' align='center' colspan='1'>" + rsRDU.getString("tahun") + "</td>"
                                                                    + "<td valign='top' colspan='3'>" + rsRDU.getString("lokasi") + "</td>"
                                                                    + "<td valign='top' colspan='4'>" + rsRDU.getString("penyebab") + "</td>"
                                                                    + "</tr>");
                                                        }
                                                    } catch (Exception e) {
                                                        System.out.println("Notifikasi : " + e);
                                                    } finally {
                                                        if (rsRDU != null) {
                                                            rsRDU.close();
                                                        }
                                                        if (psRDU != null) {
                                                            psRDU.close();
                                                        }
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("Notifikasi : " + e);
                                                }
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'>Riwayat Amputasi :</td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Kiri</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Kanan</td>"
                                                    + "</tr>");

                                            if (rsDiabet.getString("kiri").equals("jari kaki ke")) {
                                                if (rsDiabet.getString("jari_kaki_kiri_ke").equals("")) {
                                                    amputasiKiri = rsDiabet.getString("kiri");
                                                } else {
                                                    amputasiKiri = rsDiabet.getString("kiri") + " " + rsDiabet.getString("jari_kaki_kiri_ke");
                                                }
                                            } else if (rsDiabet.getString("kiri").equals("Transmetatarsal, tahun")) {
                                                if (rsDiabet.getString("trans_kiri_tahun").equals("")) {
                                                    amputasiKiri = rsDiabet.getString("kiri");
                                                } else {
                                                    amputasiKiri = rsDiabet.getString("kiri") + " " + rsDiabet.getString("trans_kiri_tahun");
                                                }
                                            } else {
                                                amputasiKiri = rsDiabet.getString("kiri");
                                            }
                                            
                                            if (rsDiabet.getString("kanan").equals("jari kaki ke")) {
                                                if (rsDiabet.getString("jari_kaki_kanan_ke").equals("")) {
                                                    amputasiKanan = rsDiabet.getString("kanan");
                                                } else {
                                                    amputasiKanan = rsDiabet.getString("kanan") + " " + rsDiabet.getString("jari_kaki_kanan_ke");
                                                }
                                            } else if (rsDiabet.getString("kanan").equals("Transmetatarsal, tahun")) {
                                                if (rsDiabet.getString("trans_kanan_tahun").equals("")) {
                                                    amputasiKanan = rsDiabet.getString("kanan");
                                                } else {
                                                    amputasiKanan = rsDiabet.getString("kanan") + " " + rsDiabet.getString("trans_kanan_tahun");
                                                }
                                            } else {
                                                amputasiKanan = rsDiabet.getString("kanan");
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='2'>" + amputasiKiri + "</td>"
                                                    + "<td valign='top' colspan='2'>" + amputasiKanan + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>III. Riwayat Komplikasi/Penyakit Penyerta</span></td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Mata</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Ginjal</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Penyakit Jantung Koroner</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Hipertensi</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Stroke</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>PAD</td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("mata").equals("ya")) {
                                                if (rsDiabet.getString("riwayat_mata").equals("Terapi laser tahun")) {
                                                    if (rsDiabet.getString("terapi_mata_tahun").equals("")) {
                                                        mataDiabet = rsDiabet.getString("riwayat_mata") + "(-)";
                                                    } else {
                                                        mataDiabet = rsDiabet.getString("riwayat_mata") + " " + rsDiabet.getString("terapi_mata_tahun");
                                                    }
                                                } else {
                                                    mataDiabet = rsDiabet.getString("riwayat_mata");
                                                }
                                            } else {
                                                mataDiabet = "-";
                                            }
                                            
                                            if (rsDiabet.getString("ginjal").equals("ya")) {
                                                ginjal = rsDiabet.getString("riwayat_ginjal");
                                            } else {
                                                ginjal = "-";
                                            }
                                            
                                            if (rsDiabet.getString("penyakit_jantung").equals("ya")) {
                                                pnyJantung = "Ya";
                                            } else {
                                                pnyJantung = "-";
                                            }
                                            
                                            if (rsDiabet.getString("hipertensi").equals("ya")) {
                                                hipertensi = "Ya";
                                            } else {
                                                hipertensi = "-";
                                            }
                                            
                                            if (rsDiabet.getString("strok").equals("ya")) {
                                                strok = "Ya";
                                            } else {
                                                strok = "-";
                                            }
                                            
                                            if (rsDiabet.getString("pad").equals("ya")) {
                                                pad = "Ya";
                                            } else {
                                                pad = "-";
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='2'>" + mataDiabet + "</td>"
                                                    + "<td valign='top' colspan='2'>" + ginjal + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + pnyJantung + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + hipertensi + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + strok + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + pad + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>IV. Pemeriksaan Fisik</span><br>a. Jenis Luka :</br></td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Non Ulkus</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Ulkus</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Ulkus & Gangen</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Selulitis</td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("non_ulkus").equals("ya")) {
                                                nonUlkus = "Ya";
                                            } else {
                                                nonUlkus = "-";
                                            }
                                            
                                            if (rsDiabet.getString("ulkus").equals("ya")) {
                                                ulkus = "Ya";
                                            } else {
                                                ulkus = "-";
                                            }
                                            
                                            if (rsDiabet.getString("ulkus_gangen").equals("ya")) {
                                                ulkusGang = "Ya";
                                            } else {
                                                ulkusGang = "-";
                                            }
                                            
                                            if (rsDiabet.getString("selulitis").equals("ya")) {
                                                sellu = "Ya";
                                            } else {
                                                sellu = "-";
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' align='center' colspan='1'>" + nonUlkus + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + ulkus + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + ulkusGang + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + sellu + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr align='center'>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Penjelasan Dorsal Kanan</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Penjelasan Plantar Kanan</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Penjelasan Dorsal Kiri</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Penjelasan Plantar Kiri</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='2'>" + rsDiabet.getString("deskripsi_dorsal_kanan") + "</td>"
                                                    + "<td valign='top' colspan='2'>" + rsDiabet.getString("deskripsi_plantar_kanan") + "</td>"
                                                    + "<td valign='top' colspan='2'>" + rsDiabet.getString("deskripsi_dorsal_kiri") + "</td>"
                                                    + "<td valign='top' colspan='2'>" + rsDiabet.getString("deskripsi_plantar_kiri") + "</td>"
                                                    + "</tr>");
                                            
                                            if (Sequel.cariInteger("select count(-1) from deformitas_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "'") > 0) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='8'>b. Deformitas :</td>"
                                                        + "<tr align='center'>"
                                                        + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Lokasi Kelainan</td>"
                                                        + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Kanan</td>"
                                                        + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Kiri</td>"
                                                        + "</tr>");

                                                //deformitas
                                                try {
                                                    psRDD = koneksi.prepareStatement("SELECT * FROM deformitas_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "' order by waktu_simpan");
                                                    try {
                                                        rsRDD = psRDD.executeQuery();
                                                        i = 1;
                                                        while (rsRDD.next()) {
                                                            htmlContent.append(
                                                                    "<tr>"
                                                                    + "<td valign='top' colspan='2'>" + i + ". " + rsRDD.getString("lokasi") + "</td>"
                                                                    + "<td valign='top' colspan='3'>" + rsRDD.getString("kanan") + "</td>"
                                                                    + "<td valign='top' colspan='3'>" + rsRDD.getString("kiri") + "</td>"
                                                                    + "</tr>");
                                                            i++;
                                                        }
                                                    } catch (Exception e) {
                                                        System.out.println("Notifikasi : " + e);
                                                    } finally {
                                                        if (rsRDD != null) {
                                                            rsRDD.close();
                                                        }
                                                        if (psRDD != null) {
                                                            psRDD.close();
                                                        }
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("Notifikasi : " + e);
                                                }
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='3' align='left'>c. Inspeksi Kaki</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Kaki Kanan</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Kaki Kiri</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='5'><span style='font-weight:bold'>Kulit Kaki</span></td>"
                                                        + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Kering/bersisik</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_kering") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_kering") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Tumit pecah-pecah</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_tumit") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_tumit") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Bulu rambut menipis</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_bulu") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_bulu") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Tinea pedis</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_tinea") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_tinea") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Kalus</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_kalus") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_kalus") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Korn</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_korn") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_korn") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Hiperpigmentasi</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_hiperpig") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_hiperpig") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Edema</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_edema") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_edema") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Healed Ulcer</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kanan_healed") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kulit_kiri_healed") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='5'><span style='font-weight:bold'>Kuku Kaki</span></td>"
                                                        + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Menebal</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kanan_menebal") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kiri_menebal") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Infeksi</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kanan_infeksi") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kiri_infeksi") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Perubahan Warna</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kanan_perubahan") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kiri_perubahan") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Rapuh</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kanan_rapuh") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kiri_rapuh") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Ingrowing nail</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kanan_ingro") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kiri_ingro") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Atrofi</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kanan_atrofi") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kiri_atrofi") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Lain-lain</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kanan_lain") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("kuku_kiri_lain") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='5'><span style='font-weight:bold'>Telapak Kaki</span></td>"
                                                        + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Hallux toe</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("telapak_kanan_hallux") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("telapak_kiri_hallux") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Pel Planus</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("telapak_kanan_pel") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("telapak_kiri_pel") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Charcot foot</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("telapak_kanan_char") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("telapak_kiri_char") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='5'><span style='font-weight:bold'>Jari Kaki</span></td>"
                                                        + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Hammer toe</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kanan_hammer") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kiri_hammer") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Claw toe</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kanan_claw") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kiri_claw") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Hiperekstensi</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kanan_hiper") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kiri_hiper") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Maserasi interdigital</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kanan_maser") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("jari_kiri_maser") + "</td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("ket_jari_kanan_lain").equals("")) {
                                                jarKakiKanan = rsDiabet.getString("jari_kanan_lain");
                                            } else {
                                                jarKakiKanan = rsDiabet.getString("jari_kanan_lain") + " (" + rsDiabet.getString("ket_jari_kanan_lain") + ")";
                                            }
                                            
                                            if (rsDiabet.getString("ket_jari_kiri_lain").equals("")) {
                                                jarKakiKiri = rsDiabet.getString("jari_kiri_lain");
                                            } else {
                                                jarKakiKiri = rsDiabet.getString("jari_kiri_lain") + " (" + rsDiabet.getString("ket_jari_kiri_lain") + ")";
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>Lain-lain (sebutkan)</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + jarKakiKanan + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + jarKakiKiri + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>V. Pemeriksaan Vaskular</span></td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='2'></td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Kaki Kanan</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Kaki Kiri</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='2'>A. Dorsalis Pedis</td>"
                                                    + "<td valign='top' colspan='1'>" + rsDiabet.getString("dorsalis_kaki_kanan") + "</td>"
                                                    + "<td valign='top' colspan='1'>" + rsDiabet.getString("dorsalis_kaki_kiri") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='2'>A. Tibialis Posterior</td>"
                                                    + "<td valign='top' colspan='1'>" + rsDiabet.getString("tibialis_kaki_kanan") + "</td>"
                                                    + "<td valign='top' colspan='1'>" + rsDiabet.getString("tibialis_kaki_kiri") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'>Pemeriksaan ABI :</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='1'>TDS A. Brachialis</td>"
                                                    + "<td valign='top' colspan='1'>: " + rsDiabet.getString("brachialis") + " mmHg</td>"
                                                    + "<td valign='top' colspan='1'></td>"
                                                    + "<td valign='top' colspan='1'>TDS A. Dorsalis pedis</td>"
                                                    + "<td valign='top' colspan='1'>: " + rsDiabet.getString("dorsalis_pedis") + " mmHg</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='1'>Score ABI :</td>"
                                                    + "<td valign='top' colspan='1'><span style='text-decoration:underline'>TDS A. Dorsalis pedis</span><br>TDS A. Brachialis</br></td>"
                                                    + "<td valign='top' colspan='1'><span style='text-decoration:underline'>" + rsDiabet.getString("dorsalis_pedis") + " mmHg</span><br>" + rsDiabet.getString("brachialis") + " mmHg</br></td>"
                                                    + "<td valign='top' colspan='1'>= " + rsDiabet.getString("skor_abi") + " mmHg</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>VI. Pemeriksaan Neuropati</span></td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='1'></td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Kaki Kanan</td>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Kaki Kiri</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='1'>Monofilamen 10 g</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("monofilamen_kanan") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("monofilamen_kiri") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='1'>Garputala 128 Hz</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("garputala_kanan") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("garputala_kiri") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='1'>Reflex tendo Achilles</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("reflex_kanan") + "</td>"
                                                    + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("reflex_kiri") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>VII. Deraja Luka</span><br>Klasifikasi Ulkus Wagner</br></td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Checklist</td>"
                                                    + "<td valign='top' colspan='7' bgcolor='#f8fdf3'>Deskripsi</td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("derajat0").equals("ya")) {
                                                der0 = "<input type='checkbox' checked>";
                                            } else {
                                                der0 = "<input type='checkbox' disabled>";
                                            }
                                            
                                            if (rsDiabet.getString("derajat1").equals("ya")) {
                                                der1 = "<input type='checkbox' checked>";
                                            } else {
                                                der1 = "<input type='checkbox' disabled>";
                                            }
                                            
                                            if (rsDiabet.getString("derajat2").equals("ya")) {
                                                der2 = "<input type='checkbox' checked>";
                                            } else {
                                                der2 = "<input type='checkbox' disabled>";
                                            }
                                            
                                            if (rsDiabet.getString("derajat3").equals("ya")) {
                                                der3 = "<input type='checkbox' checked>";
                                            } else {
                                                der3 = "<input type='checkbox' disabled>";
                                            }
                                            
                                            if (rsDiabet.getString("derajat4").equals("ya")) {
                                                der4 = "<input type='checkbox' checked>";
                                            } else {
                                                der4 = "<input type='checkbox' disabled>";
                                            }
                                            
                                            if (rsDiabet.getString("derajat5").equals("ya")) {
                                                der5 = "<input type='checkbox' checked>";
                                            } else {
                                                der5 = "<input type='checkbox' disabled>";
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' align='center' colspan='1'>" + der0 + "</td>"
                                                    + "<td valign='top' colspan='7'>0 : Tidak ada luka terbuka, mungkin terdapat deformitas atau selulitis</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' align='center' colspan='1'>" + der1 + "</td>"
                                                    + "<td valign='top' colspan='7'>1 : Ulkus diabetes superfisial (partial and full thickness)</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' align='center' colspan='1'>" + der2 + "</td>"
                                                    + "<td valign='top' colspan='7'>2 : Ulkus meluas sampai ligament, tendon, kapsula sendi atau fasia dalam tanpa abses atau osteomielitis</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' align='center' colspan='1'>" + der3 + "</td>"
                                                    + "<td valign='top' colspan='7'>3 : Ulkus dalam dengan abses, osteomielitis, atau sepsis sendi</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' align='center' colspan='1'>" + der4 + "</td>"
                                                    + "<td valign='top' colspan='7'>4 : Gangren yang terbatas pada kaki bagian depan atau tumit</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' align='center' colspan='1'>" + der5 + "</td>"
                                                    + "<td valign='top' colspan='7'>5 : Gangren yang meluas meliputi seluruh kaki</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>VIII. Pemeriksaan Penunjang</span></td>"
                                                    + "<tr align='left'>"
                                                    + "<td valign='top' colspan='8' bgcolor='#f8fdf3'>Laboratorium rutin (dalam 3 bulan terakhir)</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='8'>" + rsDiabet.getString("pemeriksaan_lab").replaceAll(":&lt", ": kurang dari ").replaceAll(" &lt", " kurang dari ").replaceAll("\n", "<br/>") + "<br/></td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("ronsen_kaki").equals("ya")) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='8'>Rontgen Kaki : </td>"
                                                        + "<tr align='center'>"
                                                        + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Tanggal</td>"
                                                        + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Kesimpulan</td>"
                                                        + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Osteomielitis</td>"
                                                        + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Lokasi</td>"
                                                        + "</tr>");

                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' colspan='1'>" + Valid.SetTglINDONESIA(rsDiabet.getString("ronsen_kaki_tgl")) + "</td>"
                                                        + "<td valign='top' colspan='3'>" + rsDiabet.getString("kesimpulan_ronsen") + "</td>"
                                                        + "<td valign='top' align='center' colspan='1'>" + rsDiabet.getString("osteomielitis") + "</td>"
                                                        + "<td valign='top' colspan='3'>" + rsDiabet.getString("lokasi") + "</td>"
                                                        + "</tr>");
                                            }
                                            
                                            if (Sequel.cariInteger("select count(-1) from mikrobiologi_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "'") > 0) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top' width='20%' align='left' colspan='8'>Mikrobiologi :</td>"
                                                        + "<tr align='center'>"
                                                        + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Bakteri</td>"
                                                        + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Sensitif</td>"
                                                        + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Resisten</td>"
                                                        + "</tr>");
                                                
                                                //mikrobiologi
                                                try {
                                                    psRDM = koneksi.prepareStatement("SELECT * FROM mikrobiologi_kaki_diabetes where no_rawat='" + rs2.getString("no_rawat") + "' order by waktu_simpan");
                                                    try {
                                                        rsRDM = psRDM.executeQuery();
                                                        i = 1;
                                                        while (rsRDM.next()) {
                                                            htmlContent.append(
                                                                    "<tr>"
                                                                    + "<td valign='top' colspan='2'>" + i + ". " + rsRDM.getString("bakteri") + "</td>"
                                                                    + "<td valign='top' colspan='3'>" + rsRDM.getString("sensitif") + "</td>"
                                                                    + "<td valign='top' colspan='3'>" + rsRDM.getString("resisten") + "</td>"
                                                                    + "</tr>");
                                                            i++;
                                                        }
                                                    } catch (Exception e) {
                                                        System.out.println("Notifikasi : " + e);
                                                    } finally {
                                                        if (rsRDM != null) {
                                                            rsRDM.close();
                                                        }
                                                        if (psRDM != null) {
                                                            psRDM.close();
                                                        }
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("Notifikasi : " + e);
                                                }
                                            }
                                            
                                            htmlContent.append(
                                                    "<tr align='center'>"
                                                    + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>Rontgen Thorax (Kesimpulan)</td>"
                                                    + "<td valign='top' colspan='3' bgcolor='#f8fdf3'>EKG (Kesimpulan)</td>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>USG Dopler</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='3'>" + rsDiabet.getString("kes_ronsen_thorax") + "</td>"
                                                    + "<td valign='top' colspan='3'>" + rsDiabet.getString("kes_ekg") + "</td>"
                                                    + "<td valign='top' colspan='2'>" + rsDiabet.getString("usg_dopler") + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' width='20%' align='left' colspan='8'><span style='font-weight:bold'>IX. Tata Laksana Rawat Luka</span></td>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='2' bgcolor='#f8fdf3'>Debridement</td>"
                                                    + "<td valign='top' colspan='6' bgcolor='#f8fdf3'>Modern Dressing</td>"
                                                    + "</tr>");
                                            
                                            if (rsDiabet.getString("surgical").equals("ya")) {
                                                surgi = "Surgical, ";
                                            } else {
                                                surgi = "";
                                            }

                                            if (rsDiabet.getString("chemical").equals("ya")) {
                                                chemi = "Chemical, ";
                                            } else {
                                                chemi = "";
                                            }

                                            if (rsDiabet.getString("biology").equals("ya")) {
                                                bio = "Biology";
                                            } else {
                                                bio = "";
                                            }
                                            debri = surgi + chemi + bio;
                                            
                                            if (rsDiabet.getString("hidrocol").equals("ya")) {
                                                hydro = "Hydrocolloid, ";
                                            } else {
                                                hydro = "";
                                            }
                                            
                                            if (rsDiabet.getString("foam").equals("ya")) {
                                                foam = "Foam, ";
                                            } else {
                                                foam = "";
                                            }
                                            
                                            if (rsDiabet.getString("allginate").equals("ya")) {
                                                algi = "Allginate, ";
                                            } else {
                                                algi = "";
                                            }
                                            
                                            if (rsDiabet.getString("silver").equals("ya")) {
                                                silver = "Silver Sulfadiazine, ";
                                            } else {
                                                silver = "";
                                            }
                                            
                                            if (rsDiabet.getString("cadexomer").equals("ya")) {
                                                cadex = "Cadexomer, ";
                                            } else {
                                                cadex = "";
                                            }

                                            if (rsDiabet.getString("madu").equals("ya")) {
                                                madu = "Madu, ";
                                            } else {
                                                madu = "";
                                            }

                                            if (rsDiabet.getString("modern_dresing_lain").equals("ya")) {
                                                if (rsDiabet.getString("ket_modern_dresing_lain").equals("")) {
                                                    lainModern = "Lain-lain (-)";
                                                } else {
                                                    lainModern = "Lain-lain (" + rsDiabet.getString("ket_modern_dresing_lain") + ")";
                                                }
                                            } else {
                                                lainModern = "";
                                            }
                                            modernDres = hydro + foam + algi + silver + cadex + madu + lainModern;

                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='2'>" + debri + "</td>"
                                                    + "<td valign='top' colspan='6'>" + modernDres + "</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<tr align='center'>"
                                                    + "<td valign='top' colspan='4' bgcolor='#f8fdf3'>Perawat</td>"
                                                    + "<td valign='top' colspan='4' bgcolor='#f8fdf3'>Dokter Pemeriksa</td>"
                                                    + "</tr>");
                                            
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top' colspan='4'>" + Sequel.cariIsi("select ifnull(nama,'-') from pegawai where nik='" + rsDiabet.getString("nip_perawat") + "'") + "</td>"
                                                    + "<td valign='top' colspan='4'>" + Sequel.cariIsi("select ifnull(nama,'-') from pegawai where nik='" + rsDiabet.getString("nip_dokter") + "'") + "</td>"
                                                    + "</tr>");
                                        }
                                        htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rsDiabet != null) {
                                        rsDiabet.close();
                                    }
                                }
                            }
                            
                            //menampilkan reasesmen pemeriksaan
                            if (Sequel.cariIsi("select reasesmen from pemeriksaan_ralan WHERE no_rawat='" + rs2.getString("no_rawat") + "'").equals("1")) {
                                try {
                                    StringBuilder sb10 = new StringBuilder();
                                    sb10.append("SELECT kesimpulan, rekomendasi FROM pemeriksaan_ralan WHERE no_rawat='" + rs2.getString("no_rawat") + "'");
                                    rs3 = koneksi.prepareStatement(sb10.toString()).executeQuery();

                                    if (rs3.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Reasesmen Pemeriksaan Dokter</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                                + "<tr align='center'>"
                                                + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Kesimpulan</td>"
                                                + "<td valign='top' width='13%' bgcolor='#f8fdf3'>Rekomendasi</td>"
                                                + "</tr>"
                                        );
                                        rs3.beforeFirst();
                                        while (rs3.next()) {
                                            htmlContent.append(
                                                    "<tr>"
                                                    + "<td valign='top'>" + rs3.getString("kesimpulan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "<br><br></td>"
                                                    + "<td valign='top'>" + rs3.getString("rekomendasi").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "<br><br></td>"
                                                    + "</tr>");
                                        }
                                        htmlContent.append(
                                                "</table>"
                                                + "</td>"
                                                + "</tr>");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rs3 != null) {
                                        rs3.close();
                                    }
                                }
                            }
                            
                            //menampilkan konsul internal poliklinik
                            try {
                                StringBuilder sb11 = new StringBuilder();
                                sb11.append("select sk.*, p.no_rkm_medis, p.nm_pasien, pl1.nm_poli poliAwal, d.nm_dokter, date_format(sk.tgl_permintaan_konsul,'%d/%m/%Y') tglKonsul,");
                                sb11.append("pl2.nm_poli poliTujuan, DATE_FORMAT(sk.tgl_permintaan_konsul,'%d-%m-%Y') tglKonsul, if(sk.tgl_menjawab='0000-00-00','-',sk.tgl_menjawab) tgljawab, ");
                                sb11.append("date_format(sk.tgl_menjawab,'%d/%m/%Y') tglmenjawab, date_format(sk.tgl_konsul_ulang,'%d/%m/%Y') tglkonsululang from surat_konsul_unit_ralan sk ");
                                sb11.append("inner join reg_periksa rp on rp.no_rawat=sk.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis ");
                                sb11.append("inner join poliklinik pl1 on pl1.kd_poli=sk.kd_poli inner join poliklinik pl2 on pl2.kd_poli=sk.kd_poli_pembalas ");
                                sb11.append("inner join dokter d on d.kd_dokter=sk.kd_dokter_pembalas WHERE sk.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb11.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Konsultasi Internal Poliklinik</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Poliklinik Tujuan</td>"
                                            + "<td valign='top' width='13%' bgcolor='#f8fdf3'>Jns. Konsul</td>"
                                            + "<td valign='top' width='13%' bgcolor='#f8fdf3'>Tgl. Konsul</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Tujuan Konsul</td>"
                                            + "<td valign='top' width='150%' bgcolor='#f8fdf3'>Permintaan/Ket. Konsul</td>"
                                            + "<td valign='top' width='150%' bgcolor='#f8fdf3'>Uraian Jawaban Konsul</td>"
                                            + "<td valign='top' width='24%' bgcolor='#f8fdf3'>Dijawab Tgl.</td>"
                                            + "<td valign='top' width='24%' bgcolor='#f8fdf3'>Tgl. Konsul Ulang</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Dijawab Oleh Dokter</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        String tujuan = "", tgljwb = "", tglkonsulUlang = "", cekJawaban = "";
                                        if (rs3.getString("tujuan").equals("Lainnya")) {
                                            tujuan = "<td valign='top'>" + rs3.getString("tujuan") + " (" + rs3.getString("ket_tujuan_lain") + ")</td>";
                                        } else {
                                            tujuan = "<td valign='top'>" + rs3.getString("tujuan") + "</td>";
                                        }

                                        if (rs3.getString("tgljawab").equals("-")) {
                                            tgljwb = "<td valign='top' align='center'>-</td>";
                                        } else {
                                            tgljwb = "<td valign='top' align='center'>" + rs3.getString("tglmenjawab") + "</td>";
                                        }

                                        if (rs3.getString("konsul_ulang").equals("tidak")) {
                                            tglkonsulUlang = "<td valign='top' align='center'>-</td>";
                                        } else {
                                            tglkonsulUlang = "<td valign='top' align='center'>" + rs3.getString("tglkonsululang") + "</td>";
                                        }

                                        if (rs3.getString("no_rawat_pembalas").equals("-")) {
                                            cekJawaban = "<td valign='top'>-</td>";
                                        } else {
                                            if (rs3.getString("kasus_ditemukan").equals("")) {
                                                cekJawaban = "<td valign='top'>" + rs3.getString("ket_klinis_jawaban").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "<br><br></td>";
                                            } else {
                                                cekJawaban = "<td valign='top'>Ditemukan kasus : " + rs3.getString("kasus_ditemukan") + "<br><br>" + rs3.getString("ket_klinis_jawaban").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "<br><br></td>";
                                            }
                                        }

                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("poliTujuan") + "</td>"
                                                + "<td valign='top' align='center'>" + rs3.getString("jenis_konsul") + "</td>"
                                                + "<td valign='top' align='center'>" + rs3.getString("tglKonsul") + "</td>"
                                                + tujuan
                                                + "<td valign='top'>" + rs3.getString("keterangan_klinis").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "<br><br></td>"
                                                + cekJawaban
                                                + tgljwb
                                                + tglkonsulUlang
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan rujukan internal poliklinik
                            try {
                                StringBuilder sb12 = new StringBuilder();
                                sb12.append("SELECT ifnull(pl.nm_poli,'-') ke_poli, ifnull(DATE_FORMAT(ri.tgl_rencana_dirujuk,'%d-%m-%Y'),'-') tgl_dirujuk, ");
                                sb12.append("ifnull(ri.keterangan,'-') keterangan, ifnull(ri.keterangan_balasan,'-') jwbn, ifnull(d.nm_dokter,'') drMenjawab FROM rujukan_internal_poli ri ");
                                sb12.append("INNER JOIN poliklinik pl on pl.kd_poli=ri.kd_poli_pembalas left join dokter d on d.kd_dokter=ri.kd_dokter_pembalas ");
                                sb12.append("WHERE ri.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb12.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dirujuk Internal Ke</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Poliklinik/Inst.</td>"
                                            + "<td valign='top' width='13%' bgcolor='#f8fdf3'>Renc. Dirujuk</td>"
                                            + "<td valign='top' width='150%' bgcolor='#f8fdf3'>Isi/Pesan Rujukan</td>"
                                            + "<td valign='top' width='150%' bgcolor='#f8fdf3'>Balasan/Jawaban Rujukan</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("ke_poli") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_dirujuk") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keterangan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("jwbn").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "<br><br>Ttd.<br>" + rs3.getString("drMenjawab") + "</td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan riwayat pemeriksaan ralan petugas
                            try {
                                StringBuilder sb13 = new StringBuilder();
                                sb13.append("select pemeriksaan_ralan_petugas.suhu_tubuh,pemeriksaan_ralan_petugas.tensi,pemeriksaan_ralan_petugas.nadi,pemeriksaan_ralan_petugas.respirasi,");
                                sb13.append("pemeriksaan_ralan_petugas.tinggi,pemeriksaan_ralan_petugas.berat,pemeriksaan_ralan_petugas.gcs,pemeriksaan_ralan_petugas.keluhan, ");
                                sb13.append("pemeriksaan_ralan_petugas.pemeriksaan,pemeriksaan_ralan_petugas.alergi,ifnull(pemeriksaan_ralan_petugas.diagnosa,'-') diagnosa, ");
                                sb13.append("ifnull(pemeriksaan_ralan_petugas.rincian_tindakan,'-') rincian_tindakan, ");
                                sb13.append("ifnull(pemeriksaan_ralan_petugas.terapi,'-') terapi, ifnull(pemeriksaan_ralan_petugas.spo2,'-') spo2 from pemeriksaan_ralan_petugas where ");
                                sb13.append("pemeriksaan_ralan_petugas.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb13.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Petugas</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='2%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='14%' bgcolor='#f8fdf3'>Keluhan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Diagnosa</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Alergi</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Suhu(C)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tensi</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Berat(Kg)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>SPO2</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Rincian Tindakan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Terapi</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("diagnosa").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("alergi").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("suhu_tubuh") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tensi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nadi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("respirasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tinggi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("berat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("gcs") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("spo2") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("rincian_tindakan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("terapi").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan riwayat pemeriksaan ranap
//                            try {
//                                rs3=koneksi.prepareStatement(
//                                        "select pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi,pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi," +
//                                        "pemeriksaan_ranap.tinggi,pemeriksaan_ranap.berat,pemeriksaan_ranap.gcs,pemeriksaan_ranap.keluhan," +
//                                        "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.alergi,pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat from pemeriksaan_ranap where pemeriksaan_ranap.no_rawat='"+rs2.getString("no_rawat")+"'").executeQuery();
//                                if(rs3.next()){
//                                    htmlContent.append(
//                                      "<tr class='isi'>"+ 
//                                        "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Rawat Inap</td>"+
//                                        "<td valign='top' width='1%' align='center'>:</td>"+
//                                        "<td valign='top' width='79%'>"+
//                                          "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
//                                             "<tr align='center'>"+
//                                                "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"+
//                                                "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"+
//                                                "<td valign='top' width='5%' bgcolor='#f8fdf3'>Suhu(C)</td>"+
//                                                "<td valign='top' width='5%' bgcolor='#f8fdf3'>Tensi</td>"+
//                                                "<td valign='top' width='10%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"+
//                                                "<td valign='top' width='10%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"+
//                                                "<td valign='top' width='5%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"+
//                                                "<td valign='top' width='5%' bgcolor='#f8fdf3'>Berat(Kg)</td>"+
//                                                "<td valign='top' width='5%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"+
//                                                "<td valign='top' width='15%' bgcolor='#f8fdf3'>Keluhan</td>"+
//                                                "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"+
//                                                "<td valign='top' width='10%' bgcolor='#f8fdf3'>Alergi</td>"+
//                                             "</tr>"
//                                    );
//                                    rs3.beforeFirst();
//                                    w=1;
//                                    while(rs3.next()){
//                                        htmlContent.append(
//                                             "<tr>"+
//                                                "<td valign='top' align='center'>"+w+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tgl_perawatan")+" "+rs3.getString("jam_rawat")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("suhu_tubuh")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tensi")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nadi")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("respirasi")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tinggi")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("berat")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("gcs")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("keluhan")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("pemeriksaan")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("alergi")+"</td>"+
//                                             "</tr>");                                        
//                                        w++;
//                                    }
//                                    htmlContent.append(
//                                          "</table>"+
//                                        "</td>"+
//                                      "</tr>");
//                                }
//                            } catch (Exception e) {
//                                System.out.println("Notifikasi : "+e);
//                            } finally{
//                                if(rs3!=null){
//                                    rs3.close();
//                                }
//                            }
                            //hasil pemeriksaan laboratorium LIS
                            try {
                                StringBuilder sb14 = new StringBuilder();
                                sb14.append("SELECT no_lab FROM lis_reg WHERE no_rawat='" + rs2.getString("no_rawat") + "' ORDER BY no_lab");
                                rsLISMaster = koneksi.prepareStatement(sb14.toString()).executeQuery();

                                if (rsLISMaster.next()) {
                                    rsLISMaster.beforeFirst();
                                    lisM = 1;
                                    while (rsLISMaster.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Laboratorium</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                                + "<tr><td valign='top' colspan='6'>No. Lab. : " + rsLISMaster.getString("no_lab") + "</td></td></tr>"
                                                + "<tr align='center'>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Jenis Pemeriksaan/Item</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Metode Pemeriksaan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Hasil</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Rujukan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Satuan</td>"
                                                + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Flag Kode</td>"
                                                + "</tr>"
                                        );

                                        StringBuilder sb15 = new StringBuilder();
                                        sb15.append("SELECT ifnull(kategori_pemeriksaan_nama,'') kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab ");
                                        sb15.append("WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lr.no_lab ='" + rsLISMaster.getString("no_lab") + "' GROUP BY lhp.kategori_pemeriksaan_nama ");
                                        sb15.append("ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut, lhp.pemeriksaan_no_urut");
                                        rsLIS1 = koneksi.prepareStatement(sb15.toString()).executeQuery();

                                        if (rsLIS1.next()) {
                                            rsLIS1.beforeFirst();
                                            w = 1;
                                            while (rsLIS1.next()) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top'>" + rsLIS1.getString("kategori_pemeriksaan_nama") + "</td>"
                                                        + "</tr>");

                                                StringBuilder sb16 = new StringBuilder();
                                                sb16.append("SELECT ifnull(lhp.sub_kategori_pemeriksaan_nama,'') sub_kategori_pemeriksaan_nama FROM lis_reg lr ");
                                                sb16.append("LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab ");
                                                sb16.append("WHERE lr.no_lab='" + rsLISMaster.getString("no_lab") + "' and lhp.kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' ");
                                                sb16.append("GROUP BY lhp.sub_kategori_pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut, ");
                                                sb16.append("lhp.sub_kategori_pemeriksaan_nama desc, lhp.pemeriksaan_no_urut");
                                                rsLIS2 = koneksi.prepareStatement(sb16.toString()).executeQuery();
                                                
                                                if (rsLIS2.next()) {
                                                    rsLIS2.beforeFirst();
                                                    lis1 = 1;
                                                    while (rsLIS2.next()) {
                                                        htmlContent.append(
                                                                "<tr>"
                                                                + "<td valign='top'>&emsp;" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "</td>"
                                                                + "</tr>");

                                                        StringBuilder sb17 = new StringBuilder();
                                                        sb17.append("SELECT ifnull(lhp.pemeriksaan_nama,'') pemeriksaan_nama, lhp.metode, lhp.nilai_hasil, lhp.nilai_rujukan, ");
                                                        sb17.append("lhp.satuan, lhp.flag_kode FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab ");
                                                        sb17.append("LEFT JOIN lis_hasil_data_pasien lhdp ON lhdp.no_lab=lr.no_lab WHERE lr.no_lab='" + rsLISMaster.getString("no_lab") + "' and ");
                                                        sb17.append("lhp.sub_kategori_pemeriksaan_nama='" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "' and ");
                                                        sb17.append("lhp.kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' GROUP BY lhp.pemeriksaan_nama ");
                                                        sb17.append("ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut, lhp.pemeriksaan_no_urut");
                                                        rsLIS3 = koneksi.prepareStatement(sb17.toString()).executeQuery();
                                                        
                                                        if (rsLIS3.next()) {
                                                            rsLIS3.beforeFirst();
                                                            lis2 = 1;
                                                            while (rsLIS3.next()) {
                                                                htmlContent.append(
                                                                        "<tr>"
                                                                        + "<td valign='top'>&emsp;&emsp;" + rsLIS3.getString("pemeriksaan_nama") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("metode") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_hasil") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_rujukan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("satuan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("flag_kode") + "</td>"
                                                                        + "</tr>");
                                                                lis2++;
                                                            }
                                                        }
                                                        lis1++;
                                                    }
                                                }
                                                w++;
                                            }
                                            htmlContent.append(
                                                    "</table><br/>");
                                        }
                                    }
                                }

                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsLIS1 != null) {
                                    rsLIS1.close();
                                }
                            }

                            //hasil pemeriksaan radiologi
                            try {
                                StringBuilder sb18 = new StringBuilder();
                                sb18.append("SELECT date_format(pr.tgl_periksa, '%d-%m-%Y') tgl_periksa, date_format(pr.jam, '%h:%i %p') jam, ");
                                sb18.append("ifnull(jpr.nm_perawatan,'-') nm_pemeriksaan, ifnull(hr.diag_klinis_radiologi, '-') diag_klinis_radiologi, ");
                                sb18.append("ifnull(hr.hasil, '-') hasil FROM periksa_radiologi pr INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw ");
                                sb18.append("LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.kd_jenis_prw=pr.kd_jenis_prw AND hr.tgl_periksa=pr.tgl_periksa AND hr.jam=pr.jam ");
                                sb18.append("WHERE pr.no_rawat='" + rs2.getString("no_rawat") + "' ORDER BY pr.tgl_periksa, pr.jam");
                                rs3 = koneksi.prepareStatement(sb18.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Radiologi</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tgl. Periksa</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Diagnosa Klinis</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Item/Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Bacaan/Hasil Pemeriksaan</td>"
                                            + "</tr>"
                                    );

                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("diag_klinis_radiologi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("hasil").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan tarif klaim inacbg ralan
                            try {
                                StringBuilder sb19 = new StringBuilder();
                                sb19.append("SELECT ifnull(enc.no_rawat,'') no_rawat, ifnull(enc.klaim_final,'') klaim_final, ifnull(eg.cbg_desc,'') cbg_desc, ");
                                sb19.append("IFNULL(egsc.desc,'-') topup_desc, concat('Rp. ',format(ifnull(eg.cbg_tarif,''),0)) cbg_tarif, ");
                                sb19.append("concat('Rp. ',IFNULL(format(egsc.tarif,0),0)) topup_tarif, concat('Rp. ',IFNULL(format(eg.cbg_tarif+egsc.tarif,0),format(eg.cbg_tarif,0))) total_trf_grp, ");
                                sb19.append("concat('Rp. ',format(ifnull(esc.tarif_obat,''),0)) by_obat_real, CONCAT(FORMAT((esc.tarif_obat/IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100,2),' ','%') perc_pakai_obat, ");
                                sb19.append("IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=40,'#00ff00', ");
                                sb19.append("IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100>40 AND (esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=80,'#ff8040','#ff3333')) warna_sel ");
                                sb19.append("FROM eklaim_new_claim enc INNER JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep INNER JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep ");
                                sb19.append("INNER JOIN reg_periksa rp ON rp.no_rawat=enc.no_rawat INNER JOIN poliklinik p ON p.kd_poli=rp.kd_poli INNER JOIN dokter d ON d.kd_dokter=rp.kd_dokter ");
                                sb19.append("LEFT JOIN eklaim_grouping_spc_cmg egsc ON egsc.no_sep=enc.no_sep WHERE rp.status_lanjut='Ralan' and enc.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb19.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarif Klaim INACBG</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Status Klaim</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Deskripsi CBG</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Deskripsi TopUp</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Tarif CBG</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>TopUp Tarif</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Total Tarif Grouping</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Biaya Real Obat</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Pemakaian Obat</td>"
                                            + "</tr>"
                                    );

                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("klaim_final") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("total_trf_grp") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("by_obat_real") + "</td>"
                                                + "<td valign='top' bgcolor='" + rs3.getString("warna_sel") + "'><b>" + rs3.getString("perc_pakai_obat") + "</b></td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //biaya administrasi
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Biaya & Perawatan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>"
                                    + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    + "<tr>"
                                    + "<td valign='top' width='89%'>Administrasi</td>"
                                    + "<td valign='top' width='1%' align='right'>:</td>"
                                    + "<td valign='top' width='10%' align='right'>" + Valid.SetAngka(rs2.getDouble("biaya_reg")) + "</td>"
                                    + "</tr>"
                                    + "</table>"
                            );

                            //tindakan dokter ralan
                            try {
                                StringBuilder sb20 = new StringBuilder();
                                sb20.append("select rawat_jl_dr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,rawat_jl_dr.biaya_rawat ");
                                sb20.append("from rawat_jl_dr inner join jns_perawatan inner join dokter ");
                                sb20.append("on rawat_jl_dr.kd_jenis_prw=jns_perawatan.kd_jenis_prw ");
                                sb20.append("and rawat_jl_dr.kd_dokter=dokter.kd_dokter where rawat_jl_dr.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb20.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Jalan Dokter</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan paramedis ralan
                            try {
                                StringBuilder sb21 = new StringBuilder();
                                sb21.append("select rawat_jl_pr.kd_jenis_prw,jns_perawatan.nm_perawatan,petugas.nama,rawat_jl_pr.biaya_rawat ");
                                sb21.append("from rawat_jl_pr inner join jns_perawatan inner join petugas ");
                                sb21.append("on rawat_jl_pr.kd_jenis_prw=jns_perawatan.kd_jenis_prw ");
                                sb21.append("and rawat_jl_pr.nip=petugas.nip where rawat_jl_pr.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb21.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Jalan Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan ralan dokter dan paramedis
                            try {
                                StringBuilder sb22 = new StringBuilder();
                                sb22.append("select rawat_jl_drpr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,petugas.nama,rawat_jl_drpr.biaya_rawat ");
                                sb22.append("from rawat_jl_drpr inner join jns_perawatan inner join dokter inner join petugas ");
                                sb22.append("on rawat_jl_drpr.kd_jenis_prw=jns_perawatan.kd_jenis_prw and rawat_jl_drpr.nip=petugas.nip ");
                                sb22.append("and rawat_jl_drpr.kd_dokter=dokter.kd_dokter where rawat_jl_drpr.no_rawat='" + rs2.getString("no_rawat") + "'");
                                rs3 = koneksi.prepareStatement(sb22.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Tindakan Rawat Jalan Dokter & Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='25%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan dokter ranap
//                            try{
//                                rs3=koneksi.prepareStatement(
//                                        "select rawat_inap_dr.tgl_perawatan,rawat_inap_dr.jam_rawat,"+
//                                        "rawat_inap_dr.kd_jenis_prw,jns_perawatan_inap.nm_perawatan,"+
//                                        "dokter.nm_dokter,rawat_inap_dr.biaya_rawat "+
//                                        "from rawat_inap_dr inner join jns_perawatan_inap inner join dokter "+
//                                        "on rawat_inap_dr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
//                                        "and rawat_inap_dr.kd_dokter=dokter.kd_dokter where rawat_inap_dr.no_rawat='"+rs2.getString("no_rawat")+"'").executeQuery();
//                                if(rs3.next()){                                    
//                                    htmlContent.append(  
//                                      "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
//                                        "<tr><td valign='top' colspan='4'>Tindakan Rawat Inap Dokter</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"+
//                                        "<tr align='center'>"+
//                                          "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"+
//                                          "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Kode</td>"+
//                                          "<td valign='top' width='40%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"+
//                                          "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"+
//                                        "</tr>");
//                                    rs3.beforeFirst();
//                                    w=1;
//                                    while(rs3.next()){
//                                        htmlContent.append(
//                                             "<tr>"+
//                                                "<td valign='top' align='center'>"+w+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tgl_perawatan")+" "+rs3.getString("jam_rawat")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("kd_jenis_prw")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nm_perawatan")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nm_dokter")+"</td>"+
//                                                "<td valign='top' align='right'>"+Valid.SetAngka(rs3.getDouble("biaya_rawat"))+"</td>"+
//                                             "</tr>"); 
//                                        w++;
//                                    }
//                                    htmlContent.append(
//                                      "</table>");
//                                }                                
//                            } catch (Exception e) {
//                                System.out.println("Notifikasi : "+e);
//                            } finally{
//                                if(rs3!=null){
//                                    rs3.close();
//                                }
//                            }
                            //tindakan paramedis ranap
//                            try{
//                                rs3=koneksi.prepareStatement(
//                                        "select rawat_inap_pr.tgl_perawatan,rawat_inap_pr.jam_rawat,"+
//                                        "rawat_inap_pr.kd_jenis_prw,jns_perawatan_inap.nm_perawatan,"+
//                                        "petugas.nama,rawat_inap_pr.biaya_rawat "+
//                                        "from rawat_inap_pr inner join jns_perawatan_inap inner join petugas "+
//                                        "on rawat_inap_pr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
//                                        "and rawat_inap_pr.nip=petugas.nip where rawat_inap_pr.no_rawat='"+rs2.getString("no_rawat")+"'").executeQuery();
//                                if(rs3.next()){                                    
//                                    htmlContent.append(  
//                                      "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
//                                        "<tr><td valign='top' colspan='4'>Tindakan Rawat Inap Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"+
//                                        "<tr align='center'>"+
//                                          "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"+
//                                          "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Kode</td>"+
//                                          "<td valign='top' width='40%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"+
//                                          "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"+
//                                        "</tr>");
//                                    rs3.beforeFirst();
//                                    w=1;
//                                    while(rs3.next()){
//                                        htmlContent.append(
//                                             "<tr>"+
//                                                "<td valign='top' align='center'>"+w+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tgl_perawatan")+" "+rs3.getString("jam_rawat")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("kd_jenis_prw")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nm_perawatan")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nama")+"</td>"+
//                                                "<td valign='top' align='right'>"+Valid.SetAngka(rs3.getDouble("biaya_rawat"))+"</td>"+
//                                             "</tr>"); 
//                                        w++;
//                                    }
//                                    htmlContent.append(
//                                      "</table>");
//                                }      
//                            } catch (Exception e) {
//                                System.out.println("Notifikasi : "+e);
//                            } finally{
//                                if(rs3!=null){
//                                    rs3.close();
//                                }
//                            }
//                            
//                            //tindakan paramedis dan dokter ranap
//                            try{
//                                rs3=koneksi.prepareStatement(
//                                        "select rawat_inap_drpr.tgl_perawatan,rawat_inap_drpr.jam_rawat,rawat_inap_drpr.kd_jenis_prw,"+
//                                        "jns_perawatan_inap.nm_perawatan,dokter.nm_dokter,petugas.nama,rawat_inap_drpr.biaya_rawat "+
//                                        "from rawat_inap_drpr inner join jns_perawatan_inap inner join dokter inner join petugas "+
//                                        "on rawat_inap_drpr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw and rawat_inap_drpr.nip=petugas.nip "+
//                                        "and rawat_inap_drpr.kd_dokter=dokter.kd_dokter where rawat_inap_drpr.no_rawat='"+rs2.getString("no_rawat")+"'").executeQuery();
//                                if(rs3.next()){                                    
//                                    htmlContent.append(  
//                                      "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
//                                        "<tr><td valign='top' colspan='5'>Tindakan Rawat Inap Dokter & Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"+            
//                                        "<tr align='center'>"+
//                                          "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"+
//                                          "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Kode</td>"+
//                                          "<td valign='top' width='26%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"+
//                                          "<td valign='top' width='17%' bgcolor='#f8fdf3'>Dokter</td>"+
//                                          "<td valign='top' width='17%' bgcolor='#f8fdf3'>Paramedis</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"+
//                                        "</tr>");
//                                    rs3.beforeFirst();
//                                    w=1;
//                                    while(rs3.next()){
//                                        htmlContent.append(
//                                             "<tr>"+
//                                                "<td valign='top' align='center'>"+w+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tgl_perawatan")+" "+rs3.getString("jam_rawat")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("kd_jenis_prw")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nm_perawatan")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nm_dokter")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nama")+"</td>"+
//                                                "<td valign='top' align='right'>"+Valid.SetAngka(rs3.getDouble("biaya_rawat"))+"</td>"+
//                                             "</tr>"); 
//                                        w++;
//                                    }
//                                    htmlContent.append(
//                                      "</table>");
//                                }                                
//                            } catch (Exception e) {
//                                System.out.println("Notifikasi : "+e);
//                            } finally{
//                                if(rs3!=null){
//                                    rs3.close();
//                                }
//                            }
//                            
//                            //kamar inap
//                            try{
//                                rs3=koneksi.prepareStatement(
//                                        "select kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_masuk, kamar_inap.tgl_keluar, "+
//                                        "kamar_inap.stts_pulang,kamar_inap.lama,kamar_inap.jam_masuk,kamar_inap.jam_keluar,"+
//                                        "kamar_inap.ttl_biaya from kamar_inap inner join bangsal inner join kamar "+
//                                        "on kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal  "+
//                                        "where kamar_inap.no_rawat='"+rs2.getString("no_rawat")+"'").executeQuery();
//                                if(rs3.next()){                                    
//                                    htmlContent.append(  
//                                      "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
//                                        "<tr><td valign='top' colspan='5'>Penggunaan Kamar</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"+            
//                                        "<tr align='center'>"+
//                                          "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"+
//                                          "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal Masuk</td>"+
//                                          "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggak Keluar</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Lama Inap</td>"+
//                                          "<td valign='top' width='35%' bgcolor='#f8fdf3'>Kamar</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Status</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"+
//                                        "</tr>");
//                                    rs3.beforeFirst();
//                                    w=1;
//                                    while(rs3.next()){
//                                        htmlContent.append(
//                                             "<tr>"+
//                                                "<td valign='top' align='center'>"+w+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tgl_masuk")+" "+rs3.getString("jam_masuk")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tgl_keluar")+" "+rs3.getString("jam_keluar")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("lama")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("kd_kamar")+", "+rs3.getString("nm_bangsal")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("stts_pulang")+"</td>"+
//                                                "<td valign='top' align='right'>"+Valid.SetAngka(rs3.getDouble("ttl_biaya"))+"</td>"+
//                                             "</tr>"); 
//                                        w++;
//                                    }
//                                    htmlContent.append(
//                                      "</table>");
//                                }                                
//                            } catch (Exception e) {
//                                System.out.println("Notifikasi : "+e);
//                            } finally{
//                                if(rs3!=null){
//                                    rs3.close();
//                                }
//                            }
                            //operasi
                            try {
                                StringBuilder sb23 = new StringBuilder();
                                sb23.append("select DATE_FORMAT(operasi.tgl_operasi,'%d-%m-%Y %h:%i %p') tgl_operasi,operasi.jenis_anasthesi,operasi.operator1, operasi.operator2, operasi.operator3, operasi.asisten_operator1,");
                                sb23.append("operasi.asisten_operator2, operasi.instrumen, operasi.dokter_anak, operasi.perawaat_resusitas, ");
                                sb23.append("operasi.dokter_anestesi, operasi.asisten_anestesi, operasi.bidan, operasi.bidan2, operasi.bidan3, operasi.perawat_luar, operasi.omloop,");
                                sb23.append("operasi.omloop2,operasi.omloop3,operasi.dokter_pjanak,operasi.dokter_umum, ");
                                sb23.append("operasi.kode_paket,paket_operasi.nm_perawatan, operasi.biayaoperator1, operasi.biayaoperator2, operasi.biayaoperator3, ");
                                sb23.append("operasi.biayaasisten_operator1, operasi.biayaasisten_operator2, operasi.biayainstrumen, ");
                                sb23.append("operasi.biayadokter_anak, operasi.biayaperawaat_resusitas, operasi.biayadokter_anestesi, ");
                                sb23.append("operasi.biayaasisten_anestesi, operasi.biayabidan,operasi.biayabidan2,operasi.biayabidan3, operasi.biayaperawat_luar, operasi.biayaalat,");
                                sb23.append("operasi.biayasewaok,operasi.akomodasi,operasi.bagian_rs,operasi.biaya_omloop,operasi.biaya_omloop2,operasi.biaya_omloop3,");
                                sb23.append("operasi.biayasarpras,operasi.biaya_dokter_pjanak,operasi.biaya_dokter_umum,");
                                sb23.append("(operasi.biayaoperator1+operasi.biayaoperator2+operasi.biayaoperator3+");
                                sb23.append("operasi.biayaasisten_operator1+operasi.biayaasisten_operator2+operasi.biayainstrumen+");
                                sb23.append("operasi.biayadokter_anak+operasi.biayaperawaat_resusitas+operasi.biayadokter_anestesi+");
                                sb23.append("operasi.biayaasisten_anestesi+operasi.biayabidan+operasi.biayabidan2+operasi.biayabidan3+operasi.biayaperawat_luar+operasi.biayaalat+");
                                sb23.append("operasi.biayasewaok+operasi.akomodasi+operasi.bagian_rs+operasi.biaya_omloop+operasi.biaya_omloop2+operasi.biaya_omloop3+");
                                sb23.append("operasi.biayasarpras+operasi.biaya_dokter_pjanak+operasi.biaya_dokter_umum) as total from operasi inner join paket_operasi ");
                                sb23.append("on operasi.kode_paket=paket_operasi.kode_paket where operasi.no_rawat='" + rs2.getString("no_rawat") + "' order by operasi.tgl_operasi");
                                rs3 = koneksi.prepareStatement(sb23.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Operasi/VK</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Tindakan</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Anastesi</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_operasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + " (");
                                        if (rs3.getDouble("biayaoperator1") > 0) {
                                            htmlContent.append("Operator 1 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator1")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaoperator2") > 0) {
                                            htmlContent.append("Operator 2 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaoperator3") > 0) {
                                            htmlContent.append("Operator 3 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator3")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_operator1") > 0) {
                                            htmlContent.append("Asisten Operator 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_operator1")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_operator2") > 0) {
                                            htmlContent.append("Asisten Operator 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_operator2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayainstrumen") > 0) {
                                            htmlContent.append("Instrumen : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("instrumen")) + ", ");
                                        }
                                        if (rs3.getDouble("biayadokter_anak") > 0) {
                                            htmlContent.append("Dokter Anak : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_anak")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaperawaat_resusitas") > 0) {
                                            htmlContent.append("Perawat Resusitas : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("perawaat_resusitas")) + ", ");
                                        }
                                        if (rs3.getDouble("biayadokter_anestesi") > 0) {
                                            htmlContent.append("Dokter Anestesi : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_anestesi")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_anestesi") > 0) {
                                            htmlContent.append("Asisten Anestesi : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_anestesi")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan") > 0) {
                                            htmlContent.append("Bidan 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan2") > 0) {
                                            htmlContent.append("Bidan 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan3") > 0) {
                                            htmlContent.append("Bidan 3 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan3")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaperawat_luar") > 0) {
                                            htmlContent.append("Perawat Luar : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("perawat_luar")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop") > 0) {
                                            htmlContent.append("Onloop 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop2") > 0) {
                                            htmlContent.append("Onloop 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop2")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop3") > 0) {
                                            htmlContent.append("Onloop 3 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop3")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_dokter_pjanak") > 0) {
                                            htmlContent.append("Dokter Pj Anak : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_pjanak")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_dokter_umum") > 0) {
                                            htmlContent.append("Dokter Umum : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_umum")) + ", ");
                                        }
                                        htmlContent.append(
                                                ")</td>"
                                                + "<td valign='top'>" + rs3.getString("jenis_anasthesi") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan pemeriksaan radiologi
                            try {
                                StringBuilder sb24 = new StringBuilder();
                                sb24.append("select date_format(periksa_radiologi.tgl_periksa,'%d-%m-%Y') tgl_periksa,date_format(periksa_radiologi.jam,'%h:%i %p') jam,periksa_radiologi.kd_jenis_prw, ");
                                sb24.append("jns_perawatan_radiologi.nm_perawatan,petugas.nama,periksa_radiologi.biaya,periksa_radiologi.dokter_perujuk,dokter.nm_dokter ");
                                sb24.append("from periksa_radiologi inner join jns_perawatan_radiologi inner join petugas inner join dokter ");
                                sb24.append("on periksa_radiologi.kd_jenis_prw=jns_perawatan_radiologi.kd_jenis_prw and periksa_radiologi.kd_dokter=dokter.kd_dokter ");
                                sb24.append("and periksa_radiologi.nip=petugas.nip  where periksa_radiologi.no_rawat='" + rs2.getString("no_rawat") + "' ");
                                sb24.append("order by periksa_radiologi.tgl_periksa,periksa_radiologi.jam");
                                rs3 = koneksi.prepareStatement(sb24.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemeriksaan Radiologi</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tgl. Pemeriksaan</td>"
                                            + "<td valign='top' width='26%' bgcolor='#f8fdf3'>Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Dokter Pemeriksa Rad.</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Petugas</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //gambar pemeriksaan radiologi
                            try {
                                host = Sequel.decXML(prop.getProperty("HOST"), prop.getProperty("KEY"));
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(tgl_periksa,'%d-%m-%Y') tgl_periksa,date_format(jam,'%h:%i %p') jam, "
                                        + "lokasi_gambar from gambar_radiologi where no_rawat='" + rs2.getString("no_rawat") + "' order by tgl_periksa,jam").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='3'>Gambar Radiologi</td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Gambar Radiologi</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'><a href='http://" + host + ":" + prop.getProperty("PORTWEB") + "/" + prop.getProperty("HYBRIDWEB") + "/radiologi/" + rs3.getString("lokasi_gambar") + "'>" + rs3.getString("lokasi_gambar").replaceAll("pages/upload/", "") + "</a></td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan pemeriksaan laborat
                            try {
                                StringBuilder sb25 = new StringBuilder();
                                sb25.append("SELECT DISTINCT dp.no_rawat, d.nm_dokter, pt.nama, '' nm_perawatan, '' Pemeriksaan, '' qty, '' total ");
                                sb25.append("FROM detail_periksa_lab dp INNER JOIN periksa_lab pl ON pl.no_rawat = dp.no_rawat ");
                                sb25.append("INNER JOIN dokter d ON d.kd_dokter = pl.kd_dokter INNER JOIN petugas pt ON pt.nip = pl.nip ");
                                sb25.append("WHERE dp.no_rawat = '" + rs2.getString("no_rawat") + "' UNION ALL ");
                                sb25.append("SELECT dp.no_rawat, '', '',j.nm_perawatan, tl.Pemeriksaan, count(dp.kd_jenis_prw) qty, sum(tl.biaya_item) total ");
                                sb25.append("FROM detail_periksa_lab dp LEFT JOIN jns_perawatan_lab j ON dp.kd_jenis_prw = j.kd_jenis_prw ");
                                sb25.append("LEFT JOIN template_laboratorium tl ON dp.id_template = tl.id_template ");
                                sb25.append("WHERE dp.no_rawat = '" + rs2.getString("no_rawat") + "' GROUP BY dp.no_rawat, j.nm_perawatan, tl.Pemeriksaan");
                                rs3 = koneksi.prepareStatement(sb25.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemeriksaan Laboratorium</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='18%' bgcolor='#f8fdf3'>Dokter Pnggng. Jwb. Lab.</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Nama Petugas</td>"
                                            + "<td valign='top' width='16%' bgcolor='#f8fdf3'>Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Item Pemeriksaan</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Qty.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya/Tarif</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("Pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("qty") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>"
                                        );
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //pemberian obat
                            try {
                                StringBuilder sb26 = new StringBuilder();
                                sb26.append("select date_format(detail_pemberian_obat.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,date_format(detail_pemberian_obat.jam,'%h:%i %p') jam,databarang.kode_sat, ");
                                sb26.append("detail_pemberian_obat.kode_brng,detail_pemberian_obat.jml,detail_pemberian_obat.total,");
                                sb26.append("databarang.nama_brng from detail_pemberian_obat inner join databarang ");
                                sb26.append("on detail_pemberian_obat.kode_brng=databarang.kode_brng  ");
                                sb26.append("where detail_pemberian_obat.no_rawat='" + rs2.getString("no_rawat") + "' order by detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam");
                                rs3 = koneksi.prepareStatement(sb26.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemberian Obat/BHP/Alkes</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='35%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Aturan Pakai</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jml") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top'>" + Sequel.cariIsi("select concat('Aturan pakai : ',aturan1,' ',aturan2,' ',aturan3,', Waktu : ',waktu1,' ',waktu2,', Keterangan : ',keterangan,', Masa simpan : ',waktu_simpan) "
                                                        + "from aturan_pakai where tgl_perawatan='" + rs3.getString("tgl_perawatan") + "' and "
                                                        + "jam='" + rs3.getString("jam") + "' and no_rawat='" + rs2.getString("no_rawat") + "' and "
                                                        + "kode_brng='" + rs3.getString("kode_brng") + "'") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //pemberian obat Operasi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(beri_obat_operasi.tanggal,'%d-%m-%Y') tanggal,beri_obat_operasi.kd_obat,beri_obat_operasi.hargasatuan,obatbhp_ok.kode_sat, "
                                        + "beri_obat_operasi.jumlah, obatbhp_ok.nm_obat,(beri_obat_operasi.hargasatuan*beri_obat_operasi.jumlah) as total "
                                        + "from beri_obat_operasi inner join obatbhp_ok  on  beri_obat_operasi.kd_obat=obatbhp_ok.kd_obat  "
                                        + "where beri_obat_operasi.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Penggunaan Obat/BHP Operasi</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Obat/BHP</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tanggal") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_obat") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jumlah") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Resep Pulang
                            try {
                                StringBuilder sb27 = new StringBuilder();
                                sb27.append("select resep_pulang.kode_brng,databarang.nama_brng,resep_pulang.dosis,resep_pulang.jml_barang, ");
                                sb27.append("databarang.kode_sat,resep_pulang.dosis,resep_pulang.total from resep_pulang inner join databarang ");
                                sb27.append("on resep_pulang.kode_brng=databarang.kode_brng where ");
                                sb27.append("resep_pulang.no_rawat='" + rs2.getString("no_rawat") + "' order by databarang.nama_brng");
                                rs3 = koneksi.prepareStatement(sb27.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Resep Pulang</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Dosis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("dosis") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jml_barang") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Retur Obat
                            try {
                                StringBuilder sb28 = new StringBuilder();
                                sb28.append("select databarang.kode_brng,databarang.nama_brng,detreturjual.kode_sat,detreturjual.h_retur, ");
                                sb28.append("(detreturjual.jml_retur * -1) as jumlah,(detreturjual.subtotal * -1) as total from detreturjual ");
                                sb28.append("inner join databarang inner join returjual on detreturjual.kode_brng=databarang.kode_brng ");
                                sb28.append("and returjual.no_retur_jual=detreturjual.no_retur_jual where returjual.no_retur_jual='" + rs2.getString("no_rawat") + "' order by databarang.nama_brng");
                                rs3 = koneksi.prepareStatement(sb28.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='3'>Retur Obat</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='65%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jumlah") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Tambahan Biaya
                            try {
                                StringBuilder sb29 = new StringBuilder();
                                sb29.append("select nama_biaya, besar_biaya from tambahan_biaya where no_rawat='" + rs2.getString("no_rawat") + "' order by nama_biaya");
                                rs3 = koneksi.prepareStatement(sb29.toString()).executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='2'>Tambahan Biaya</td><td valign='top' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='84%' bgcolor='#f8fdf3'>Nama Tambahan</td>"
                                            + "<td valign='top' width='1%' bgcolor='#f8fdf3'></td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_biaya") + "</td>"
                                                + "<td valign='top'></td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("besar_biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Pengurangan Biaya
                            try {
                                StringBuilder sb30 = new StringBuilder();
                                sb30.append("select nama_pengurangan, (-1*besar_pengurangan) as besar_pengurangan from pengurangan_biaya where no_rawat='" + rs2.getString("no_rawat") + "' order by nama_pengurangan");
                                rs3 = koneksi.prepareStatement(sb30.toString()).executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='2'>Potongan Biaya</td><td valign='top' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='84%' bgcolor='#f8fdf3'>Nama Potongan</td>"
                                            + "<td valign='top' width='1%' bgcolor='#f8fdf3'></td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_pengurangan") + "</td>"
                                                + "<td valign='top'></td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("besar_pengurangan")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3' bgcolor='#7eccb9'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                
                LoadHTML2.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void cekRehabMedik() {
        cekPilihanRehab = 0;
        cekPilihanRehab = Sequel.cariInteger("select count(-1) from data_rehab_medik where no_rawat='" + TNoRW.getText() + "'");

        if (cekPilihanRehab == 0) {
            label_rehab.setVisible(false);
            label_rehab.setText("");
        } else if (cekPilihanRehab > 0) {
            label_rehab.setVisible(true);
            label_rehab.setText("Jenis Rehabilitasi Medik : " + Sequel.cariIsi("select jns_rehabmedik from data_rehab_medik where no_rawat='" + TNoRW.getText() + "'"));
        }
    }

    private void simpanHistory() {
        kontrolPoli = "";
        cekTgl = "";
        nipPenyimpan = "";
        nipExecutor = "";
        if (chkTglKontrol.isSelected() == false) {
            kontrolPoli = "0000-00-00";
            cekTgl = "tidak";
        } else {
            kontrolPoli = Valid.SetTgl(TglKontrol.getSelectedItem() + "");
            cekTgl = "ya";
        }

        if (TKlgPasien.getText().equals("")) {
            TKlgPasien.setText("-");
        } else {
            TKlgPasien.setText(TKlgPasien.getText());
        }

        if (TNmDokter.getText().equals("")) {
            TNmDokter.setText("-");
        } else {
            TNmDokter.setText(TNmDokter.getText());
        }

        if (akses.getadmin() == true) {
            nipPenyimpan = "-";
            nipExecutor = "-";
        } else {
            nipPenyimpan = akses.getkode();
            nipExecutor = akses.getkode();
        }

        try {
            if (Sequel.menyimpantf("ringkasan_pulang_ranap_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Ringkasan Pulang Pasien Rawat Inap", 30, new String[]{
                TNoRW.getText(), TAlasanDirawat.getText(), TRingkasanRiwayat.getText(), Valid.mysql_real_escape_stringERM(TPemeriksaanFisik.getText()),
                Valid.mysql_real_escape_stringERM(TPemeriksaanPenunjang.getText()), TTerapiPengobatan.getText(), TDiagUtama.getText(),
                TDiagSekunder.getText(), TKeadaanumum.getText(), TKesadaran.getText(), TTensi.getText(), TSuhu.getText(), TNadi.getText(), TFrekuensiNafas.getText(),
                TCatatan.getText(), TTerapiPulang.getText(), cmbLanjutan.getSelectedItem().toString(), kontrolPoli, TNmDokter.getText(), Tgcs.getText(),
                TTindakan.getText(), TDokterLuar.getText(), cekTgl, Tedukasi.getText().replaceAll("'", ""), TKlgPasien.getText(), nipPenyimpan, THasil.getText(),
                cmbKondisiWP.getSelectedItem().toString(), nipExecutor, Sequel.cariIsi("select now()")
            }) == true) {
                if (nmgedung.equals("AL-HAKIM/PARU")) {
                    if (noreg.getText().length() == 16) {
                        Sequel.simpanReplaceInto("nomor_reg_tb", "'" + TNoRM.getText() + "','" + noreg.getText() + "'", "No. Registrasi Pasien TB");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Simpan Ringkasan Pulang Pasien History : " + e);
        }
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode2);
        try {
            ps6 = koneksi.prepareStatement("SELECT IF(pg.nama='-','Admin Utama',pg.nama) pelaku, a.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                    + "date_format(a.waktu_eksekusi,'%d-%m-%Y') tglHapus, a.waktu_eksekusi FROM ringkasan_pulang_ranap_histori a "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = a.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg ON pg.nik = a.nik_eksekutor WHERE "
                    + "date(a.waktu_eksekusi) between ? and ? and pg.nama like ? or "
                    + "date(a.waktu_eksekusi) between ? and ? and a.no_rawat like ? or "
                    + "date(a.waktu_eksekusi) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(a.waktu_eksekusi) between ? and ? and p.nm_pasien like ? order by a.waktu_eksekusi desc");
            try {
                ps6.setString(1, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps6.setString(2, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps6.setString(3, "%" + TCari2.getText().trim() + "%");
                ps6.setString(4, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps6.setString(5, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps6.setString(6, "%" + TCari2.getText().trim() + "%");
                ps6.setString(7, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps6.setString(8, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps6.setString(9, "%" + TCari2.getText().trim() + "%");
                ps6.setString(10, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps6.setString(11, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps6.setString(12, "%" + TCari2.getText().trim() + "%");
                rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    tabMode2.addRow(new String[]{
                        rs6.getString("pelaku"),
                        rs6.getString("no_rawat"),
                        rs6.getString("no_rkm_medis"),
                        rs6.getString("nm_pasien"),
                        rs6.getString("tglHapus"),
                        rs6.getString("waktu_eksekusi")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
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
        LCount1.setText("" + tabMode2.getRowCount());
    }
    
    private void kembalikanData() {
        try {
            ps7 = koneksi.prepareStatement("select * from ringkasan_pulang_ranap_histori where "
                    + "waktu_eksekusi='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString() + "'");
            try {
                rs7 = ps7.executeQuery();
                while (rs7.next()) {
                    try {
                        if (Sequel.menyimpantf("ringkasan_pulang_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 28, new String[]{
                            rs7.getString("no_rawat"),
                            rs7.getString("alasan_masuk_dirawat"),
                            rs7.getString("ringkasan_riwayat_penyakit"),                            
                            Valid.mysql_real_escape_stringERM(rs7.getString("pemeriksaan_fisik")),
                            Valid.mysql_real_escape_stringERM(rs7.getString("pemeriksaan_penunjang")),                            
                            rs7.getString("terapi_pengobatan"),
                            rs7.getString("diagnosa_utama"),
                            rs7.getString("diagnosa_sekunder"),
                            rs7.getString("keadaan_umum"),
                            rs7.getString("kesadaran"),
                            rs7.getString("tekanan_darah"),
                            rs7.getString("suhu"),
                            rs7.getString("nadi"),
                            rs7.getString("frekuensi_nafas"),
                            rs7.getString("catatan_penting"),
                            rs7.getString("terapi_pulang"),
                            rs7.getString("pengobatan_dilanjutkan"),
                            rs7.getString("tgl_kontrol_poliklinik"),
                            rs7.getString("nm_dokter_pengirim"),
                            rs7.getString("GCS"),
                            rs7.getString("tindakan_prosedur"),
                            rs7.getString("dokter_luar_lanjutan"),
                            rs7.getString("cek_tgl_kontrol"),
                            rs7.getString("edukasi"),
                            rs7.getString("penanggung_jwb_pasien"),
                            rs7.getString("nip_penyimpan"),
                            rs7.getString("hasil_pemeriksaan"),
                            rs7.getString("stts_pulang")
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
                if (rs7 != null) {
                    rs7.close();
                }
                if (ps7 != null) {
                    ps7.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilPembaca(String norw, String nolab, String tgl, String jam) {
        Valid.tabelKosong(tabModePembaca);
        try {
            ps8 = koneksi.prepareStatement("SELECT ph.*, p.nama, date_format(ph.tgl_periksa,'%d-%m-%Y') tglPeriksa, date_format(ph.waktu_simpan,'%d-%m-%Y') tglBaca, "
                    + "time_format(ph.waktu_simpan,'%H:%i:%s') jamBaca FROM pembaca_hasil_lab ph inner join pegawai p on p.nik=ph.kd_dokter where "
                    + "ph.no_rawat='" + norw + "' and ph.no_lab='" + nolab + "' and ph.tgl_periksa='" + tgl + "' "
                    + "and ph.jam_periksa='" + jam + "' order by ph.waktu_simpan");
            try {
                rs8 = ps8.executeQuery();
                while (rs8.next()) {
                    tabModePembaca.addRow(new String[]{
                        rs8.getString("no_lab"),
                        rs8.getString("nama"),
                        rs8.getString("tglPeriksa"),
                        rs8.getString("jam_periksa"),
                        rs8.getString("tglBaca"),
                        rs8.getString("jamBaca")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs8 != null) {
                    rs8.close();
                }
                if (ps8 != null) {
                    ps8.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
