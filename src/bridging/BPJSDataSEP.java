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

package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.prism.impl.PrismSettings;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.tools.ant.taskdefs.Sleep;
import org.bouncycastle.util.Strings;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import simrskhanza.DlgRujukMasuk;

/**
 *
 * @author perpustakaan
 */
public final class BPJSDataSEP extends javax.swing.JDialog {
    private DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabMode5, tabMode6, tabMode7;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3;
    private ResultSet rs, rs1, rs2, rs3;
    private int i = 0, pilihan = 0, x, tglpulangnya = 0, cekPulang = 0, cekDataPlg = 0, cekRujukan = 0, cekSEP = 0;
    private final Properties prop = new Properties();
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    public DlgRujukMasuk rujukmasuk = new DlgRujukMasuk(null, false);
    private BPJSApi api = new BPJSApi();
    private BPJSCekReferensiFaskes faskes = new BPJSCekReferensiFaskes(null, false);
    private BPJSCekReferensiPenyakit penyakit = new BPJSCekReferensiPenyakit(null, false);
    private BPJSCekReferensiProsedur prosedur = new BPJSCekReferensiProsedur(null, false);
    private BPJSCekReferensiPoli poli = new BPJSCekReferensiPoli(null, false);
    private BPJSCekNoKartu cekViaBPJSKartu = new BPJSCekNoKartu();
    private BPJSCekReferensiDokterDPJP dpjp = new BPJSCekReferensiDokterDPJP(null, false);
    private BPJSCekReferensiPropinsi provinsi = new BPJSCekReferensiPropinsi(null, false);
    private BPJSCekReferensiKabupaten kabupaten = new BPJSCekReferensiKabupaten(null, false);
    private BPJSCekReferensiKecamatan kecamatan = new BPJSCekReferensiKecamatan(null, false);
    private BPJSCekReferensiKelasRawat kelas = new BPJSCekReferensiKelasRawat(null, false);
    private BPJSSuratKontrol suratKontrol = new BPJSSuratKontrol(null, false);
    private BPJSSPRI spri = new BPJSSPRI(null, false);
    private String no_peserta = "", requestJson, URL = "", jkel = "", duplikat = "", user = "", tglkkl = "", tglkkl_ok = "", jnsrawat = "", dialog_simpan = "",
            cttnVclaim = "", diagVclaim = "", jpelVclaim = "", KRVclaim = "", noRujukVclaim = "", JPVclaim = "", kelaminVclaim = "",
            namaVclaim = "", nokaVclaim = "", nomrVclaim = "", tglLhrVclaim = "", poliVclaim = "", poliEksVclaim = "", tglSEPVclaim = "",
            nilaiJP = "", nilaiKR = "", nilaiEKS = "", utc = "", pembi = "", kdpnjg = "", flag = "", asesmen = "", kdSttsPlg = "",
            cekstsPulang = "", tglJiun = "", noLPJiun = "", desSttsPlg = "", norujukan = "", kddiagSekun = "", nmdiagSekun = "",
            kdprosedur = "", nmprosedur = "", diagSekunderKirim = "", prosedurKirim = "", diagSekunderSimpan = "", prosedurSimpan = "", respons = "",
            tglPulangInap = "", cekLaka = "", SEPkontrol = "";

    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public BPJSDataSEP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        //this.setLocation(10,2);
        //setSize(706,674);

        WindowUpdatePulang.setSize(447, 223);
        WindowRujukanBiasa.setSize(697, 288);
        WindowRujukanKhusus.setSize(660, 379);
        WindowCariSEP.setSize(444, 115);
        WindowCariNoRujuk.setSize(874, 250);
        WindowCariRujukanVClaim.setSize(409, 127);

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.SEP", "Tanggal SEP", "No.Rawat", "No.RM", "Nama Pasien", "No.Rujukan", "Tgl. Rujukan", "Asal Rujukan",
            "Nama PPK Rujukan", "No. Rencana Kontrol/SPRI", "Nama DPJP", "Kode Poli", "Nama Poli", "Nama DPJP Layan", "Tuj. Kunjungan",
            "Assesmen Pely.", "Flag Procedur", "KD Penunjang", "Nama Diagnosa", "Jenis Pelayanan", "Kelas Rawat", "Kls. Rawat Hak",
            "Naik Kls. Rawat", "Nama Kls. Naiknya", "Catatan", "User Input", "Tgl.Lahir", "Peserta", "J.Kel", "No.Kartu", "Status",
            "Tanggal Pulang", "No.Telp", "Eksekutif", "COB", "Kasus Katarak", "Pembiayaan", "Png. Jawab", "Laka Lantas", "Lokasi Laka Lantas",
            "Tgl. KLL", "Keterangan KLL", "Suplesi", "No.SEP Suplesi", "KD Prov.", "Nama Provinsi", "KD Kab.", "Nama Kabupaten", "KD Kec.",
            "Nama Kecamatan", "Urutan SEP", "Kode PPK Rujukan", "Kode PPK Pelayanan", "Nama PPK Pelayanan", "KD DPJP", "KD DPJP Layan", "Kode Diagnosa"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,                
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,                
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbSEP.setModel(tabMode);
        tbSEP.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSEP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 57; i++) {
            TableColumn column = tbSEP.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(165);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(135);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {
                column.setPreferredWidth(145);
            } else if (i == 10) {
                column.setPreferredWidth(300);
            } else if (i == 11) {
                column.setPreferredWidth(65);
            } else if (i == 12) {
                column.setPreferredWidth(180);
            } else if (i == 13) {
                column.setPreferredWidth(300);
            } else if (i == 14) {
                column.setPreferredWidth(100);
            } else if (i == 15) {
                column.setPreferredWidth(280);
            } else if (i == 16) {
                column.setPreferredWidth(180);
            } else if (i == 17) {
                column.setPreferredWidth(80);
            } else if (i == 18) {
                column.setPreferredWidth(300);
            } else if (i == 19) {
                column.setPreferredWidth(100);
            } else if (i == 20) {
                column.setPreferredWidth(85);
            } else if (i == 21) {
                column.setPreferredWidth(90);
            } else if (i == 22) {
                column.setPreferredWidth(165);
            } else if (i == 23) {
                column.setPreferredWidth(165);
            } else if (i == 24) {
                column.setPreferredWidth(165);
            } else if (i == 25) {
                column.setPreferredWidth(65);
            } else if (i == 26) {
                column.setPreferredWidth(75);
            } else if (i == 27) {
                column.setPreferredWidth(130);
            } else if (i == 28) {
                column.setPreferredWidth(50);
            } else if (i == 29) {
                column.setPreferredWidth(95);
            } else if (i == 30) {
                column.setPreferredWidth(75);
            } else if (i == 31) {
                column.setPreferredWidth(120);
            } else if (i == 32) {
                column.setPreferredWidth(120);
            } else if (i == 33) {
                column.setPreferredWidth(60);
            } else if (i == 34) {
                column.setPreferredWidth(75);
            } else if (i == 35) {
                column.setPreferredWidth(80);
            } else if (i == 36) {
                column.setPreferredWidth(180);
            } else if (i == 37) {
                column.setPreferredWidth(165);
            } else if (i == 38) {
                column.setPreferredWidth(200);
            } else if (i == 39) {
                column.setPreferredWidth(165);
            } else if (i == 40) {
                column.setPreferredWidth(165);
            } else if (i == 41) {
                column.setPreferredWidth(230);
            } else if (i == 42) {
                column.setPreferredWidth(75);
            } else if (i == 43) {
                column.setPreferredWidth(200);
            } else if (i == 44) {
                column.setPreferredWidth(75);
            } else if (i == 45) {
                column.setPreferredWidth(165);
            } else if (i == 46) {
                column.setPreferredWidth(75);
            } else if (i == 47) {
                column.setPreferredWidth(165);
            } else if (i == 48) {
                column.setPreferredWidth(75);
            } else if (i == 49) {
                column.setPreferredWidth(165);
            } else if (i == 50) {
                column.setPreferredWidth(75);
            } else if (i == 51) {
//                column.setPreferredWidth(165);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 52) {
//                column.setPreferredWidth(165);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 53) {
//                column.setPreferredWidth(165);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 54) {
//                column.setPreferredWidth(165);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 55) {
//                column.setPreferredWidth(165);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 56) {
//                column.setPreferredWidth(165);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbSEP.setDefaultRenderer(Object.class, new WarnaTable());  
        
        //tabel 10 riwayat kunjungan terakhir
        tabMode7=new DefaultTableModel(null,new String[]{"No.","No. RM","Tgl. Kunjungan","Jns. Rawat",
                "Poliklinik","Cara Bayar","No. SEP","No. Rujukan","No. Surat Kontrol/SPRI","Urutan SEP","kdpj","jk",
                "norawat","nokartu","nmpasien","tgllahir","diagnosa"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRiwayat.setModel(tabMode7);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 17; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(150);
            } else if (i == 9) {
                column.setPreferredWidth(75);
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
            }
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());
        
        //tabel riwayat rujukan faskes 1
        tabMode1=new DefaultTableModel(null,new String[]{"No.","No. Rujukan","Tgl. Rujukan",
                "kode_ppk","Nama PPK Rujukan","Poli Rujukan","kode_icd","Diagnosa","Keluhan","kode_poli",
                "kode_ply","Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbFaskes1.setModel(tabMode1);
        tbFaskes1.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbFaskes1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbFaskes1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setPreferredWidth(150);
            }
        }
        tbFaskes1.setDefaultRenderer(Object.class, new WarnaTable());

        //tabel riwayat rujukan faskes 2
        tabMode2=new DefaultTableModel(null,new String[]{"No.","No. Rujukan","Tgl. Rujukan",
                "kode_ppk","Nama PPK Rujukan","Poli Rujukan","kode_icd","Diagnosa","Keluhan","kode_poli",
                "kode_ply","Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbFaskes2.setModel(tabMode2);
        tbFaskes2.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbFaskes2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbFaskes2.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setPreferredWidth(150);
            }
        }
        tbFaskes2.setDefaultRenderer(Object.class, new WarnaTable());
        
        //tabel riwayat rujukan faskes 1 banyak
        tabMode3=new DefaultTableModel(null,new String[]{"No.","No. Rujukan","Tgl. Rujukan",
                "kode_ppk","Nama PPK Rujukan","Poli Rujukan","kode_icd","Diagnosa","Keluhan","kode_poli",
                "kode_ply","Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbFaskes3.setModel(tabMode3);
        tbFaskes3.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbFaskes3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbFaskes3.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setPreferredWidth(150);
            }
        }
        tbFaskes3.setDefaultRenderer(Object.class, new WarnaTable());
        
        //tabel riwayat rujukan faskes 2 banyak
        tabMode4=new DefaultTableModel(null,new String[]{"No.","No. Rujukan","Tgl. Rujukan",
                "kode_ppk","Nama PPK Rujukan","Poli Rujukan","kode_icd","Diagnosa","Keluhan","kode_poli",
                "kode_ply","Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbFaskes4.setModel(tabMode4);
        tbFaskes4.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbFaskes4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbFaskes4.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setPreferredWidth(150);
            }
        }
        tbFaskes4.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode5 = new DefaultTableModel(null, new Object[]{"Cek","Kode ICD-10", "Deskripsi ICD-10"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbdiagSekunder.setModel(tabMode5);
        tbdiagSekunder.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbdiagSekunder.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbdiagSekunder.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(40);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(320);
            }
        }
        tbdiagSekunder.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode6 = new DefaultTableModel(null, new Object[]{"Cek","Kode ICD 9", "Deskripsi ICD 9 CM"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbProsedur.setModel(tabMode6);
        tbProsedur.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbProsedur.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbProsedur.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(40);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(320);
            }
        }
        tbProsedur.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        NoRujukan.setDocument(new batasInput((byte) 40).getKata(NoRujukan));
        noSurat.setDocument(new batasInput((byte) 20).getKata(noSurat));
        NoSEPSuplesi.setDocument(new batasInput((byte) 40).getKata(NoSEPSuplesi));
        pngJwb.setDocument(new batasInput((byte) 100).getKata(pngJwb));
        NoSEP.setDocument(new batasInput((byte) 19).getKata(NoSEP));
        
        //Catatan.setDocument(new batasInput((byte)255).getKata(Catatan));
        Ket.setDocument(new batasInput((byte)50).getKata(Ket));
        //Catatan1.setDocument(new batasInput((byte)255).getKata(Catatan1));
        
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

        faskes.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (faskes.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        KdPpkRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 1).toString());
                        NmPpkRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 2).toString());
                        KdPpkRujukan.requestFocus();
                    } else if (pilihan == 2) {
                        KdPpkRujukan1.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 1).toString());
                        NmPpkRujukan1.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 2).toString());
                        KdPpkRujukan1.requestFocus();
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
        
        rujukmasuk.WindowPerujuk.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("BPJSDataSEP")) {
                    if (rujukmasuk.tbPerujuk.getSelectedRow() != -1) {                        
                        KdPpkRujukan.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 1).toString());
                        NmPpkRujukan.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 2).toString());
                        rujukanSEP.setText(NmPpkRujukan.getText()); 
                        kode_rujukanya.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 4).toString());
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

        faskes.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    faskes.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (penyakit.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        KdPenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        NmPenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 2).toString());
                        KdPenyakit.requestFocus();
                    } else if (pilihan == 2) {
                        KdPenyakit1.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        NmPenyakit1.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 2).toString());
                        KdPenyakit1.requestFocus();
                    } else if (pilihan == 3) {
                        kdDiagPrimer.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        nmDiagPrimer.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 2).toString());
                        btnDiagPrimer.requestFocus();
                    } else if (pilihan == 4) {
                        tabMode5.addRow(new Object[]{                            
                            false,
                            kddiagSekun = penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString(),
                            nmdiagSekun = penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 2).toString()
                        });
                        btnDiagSekunder.requestFocus();
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

        penyakit.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    penyakit.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        prosedur.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (prosedur.getTable().getSelectedRow() != -1) {
                    tabMode6.addRow(new Object[]{
                        false,
                        kdprosedur = prosedur.getTable().getValueAt(prosedur.getTable().getSelectedRow(), 1).toString(),
                        nmprosedur = prosedur.getTable().getValueAt(prosedur.getTable().getSelectedRow(), 2).toString()
                    });
                    btnProsedur.requestFocus();
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

        prosedur.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    prosedur.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (poli.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        KdPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                        NmPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 2).toString());
                        KdPoli.requestFocus();
                    } else if (pilihan == 2) {
                        KdPoli1.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                        NmPoli1.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 2).toString());
                        KdPoli1.requestFocus();
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

        poli.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    poli.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        dpjp.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (dpjp.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        Kddpjp.setText(dpjp.getTable().getValueAt(dpjp.getTable().getSelectedRow(), 1).toString());
                        NmDPJP.setText(dpjp.getTable().getValueAt(dpjp.getTable().getSelectedRow(), 2).toString());
                        KddpjpLayan.setText(Kddpjp.getText());
                        nmdpjpLayan.setText(NmDPJP.getText());
                        btnDPJP.requestFocus();
                    } else if (pilihan == 2) {
                        KddpjpLayan.setText(dpjp.getTable().getValueAt(dpjp.getTable().getSelectedRow(), 1).toString());
                        nmdpjpLayan.setText(dpjp.getTable().getValueAt(dpjp.getTable().getSelectedRow(), 2).toString());
                        btnDPJPlayan.requestFocus();
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

        dpjp.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    dpjp.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        kelas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (kelas.getTable().getSelectedRow() != -1) {
                    kdNaikKls.setText(kelas.getTable().getValueAt(kelas.getTable().getSelectedRow(), 1).toString());
                    nmKlsNaik.setText(kelas.getTable().getValueAt(kelas.getTable().getSelectedRow(), 2).toString());
                    btnNaikKls.requestFocus();                    
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

        kelas.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kelas.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        provinsi.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(provinsi.getTable().getSelectedRow()!= -1){                   
                    KdProv.setText(provinsi.getTable().getValueAt(provinsi.getTable().getSelectedRow(),1).toString());
                    NmProv.setText(provinsi.getTable().getValueAt(provinsi.getTable().getSelectedRow(),2).toString());
                    btnProv.requestFocus();
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

        provinsi.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    provinsi.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        kabupaten.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(kabupaten.getTable().getSelectedRow()!= -1){                   
                    KdKab.setText(kabupaten.getTable().getValueAt(kabupaten.getTable().getSelectedRow(),1).toString());
                    NmKab.setText(kabupaten.getTable().getValueAt(kabupaten.getTable().getSelectedRow(),2).toString());
                    btnKab.requestFocus();
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

        kabupaten.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kabupaten.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        kecamatan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(kecamatan.getTable().getSelectedRow()!= -1){                   
                    KdKec.setText(kecamatan.getTable().getValueAt(kecamatan.getTable().getSelectedRow(),1).toString());
                    NmKec.setText(kecamatan.getTable().getValueAt(kecamatan.getTable().getSelectedRow(),2).toString());
                    btnKec.requestFocus();
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

        kecamatan.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kecamatan.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
//        skdp.addWindowListener(new WindowListener() {
//            @Override
//            public void windowOpened(WindowEvent e) {}
//            @Override
//            public void windowClosing(WindowEvent e) {}
//            @Override
//            public void windowClosed(WindowEvent e) {
//                if(skdp.getTable().getSelectedRow()!= -1){                   
//                    noSurat.setText(skdp.getTable().getValueAt(skdp.getTable().getSelectedRow(),11).toString());
//                    btnDPJP.requestFocus();
//                }                  
//            }
//            @Override
//            public void windowIconified(WindowEvent e) {}
//            @Override
//            public void windowDeiconified(WindowEvent e) {}
//            @Override
//            public void windowActivated(WindowEvent e) {}
//            @Override
//            public void windowDeactivated(WindowEvent e) {}
//        });
//        
//        skdp.getTable().addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {}
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if(e.getKeyCode()==KeyEvent.VK_SPACE){
//                    skdp.dispose();
//                }
//            }
//            @Override
//            public void keyReleased(KeyEvent e) {}
//        });
        
        suratKontrol.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(suratKontrol.getTable().getSelectedRow()!= -1){                   
                    noSurat.setText(suratKontrol.getTable().getValueAt(suratKontrol.getTable().getSelectedRow(), 9).toString());
                    Kddpjp.setText(suratKontrol.getTable().getValueAt(suratKontrol.getTable().getSelectedRow(), 11).toString());
                    NmDPJP.setText(suratKontrol.getTable().getValueAt(suratKontrol.getTable().getSelectedRow(), 12).toString());
                    KddpjpLayan.setText(suratKontrol.getTable().getValueAt(suratKontrol.getTable().getSelectedRow(), 11).toString());
                    nmdpjpLayan.setText(suratKontrol.getTable().getValueAt(suratKontrol.getTable().getSelectedRow(), 12).toString());
                    KdPenyakit.setText(suratKontrol.getTable().getValueAt(suratKontrol.getTable().getSelectedRow(), 15).toString());
                    NmPenyakit.setText(suratKontrol.getTable().getValueAt(suratKontrol.getTable().getSelectedRow(), 7).toString());                    
                    KdPoli.setText(suratKontrol.getTable().getValueAt(suratKontrol.getTable().getSelectedRow(), 13).toString());
                    NmPoli.setText(suratKontrol.getTable().getValueAt(suratKontrol.getTable().getSelectedRow(), 14).toString());
                    btnNoSurat.requestFocus();
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
        
        suratKontrol.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    suratKontrol.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        spri.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(spri.getTable().getSelectedRow()!= -1){                   
                    noSurat.setText(spri.getTable().getValueAt(spri.getTable().getSelectedRow(), 8).toString());
                    Kddpjp.setText(spri.getTable().getValueAt(spri.getTable().getSelectedRow(), 10).toString());
                    NmDPJP.setText(spri.getTable().getValueAt(spri.getTable().getSelectedRow(), 11).toString());
                    KdPenyakit.setText(spri.getTable().getValueAt(spri.getTable().getSelectedRow(), 14).toString());
                    NmPenyakit.setText(Sequel.cariIsi("select nm_penyakit from penyakit where kd_penyakit='" + KdPenyakit.getText() + "'"));
                    btnNoSurat.requestFocus();
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
        
        spri.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    spri.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        try {
            KdPPK.setText(Sequel.cariIsi("select kode_ppk from setting"));
            NmPPK.setText(Sequel.cariIsi("select nama_instansi from setting"));
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            if (akses.getkode().equals("Admin Utama")) {
                user = "AdminUtama";
            } else {
                user = akses.getnamauser().replace(" ", "").substring(0, 10);
            }
        } catch (Exception e) {
            if (akses.getkode().equals("Admin Utama")) {
                user = "AdminUtama";
            } else {
                user = akses.getnamauser();
            }
        }

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
        } catch (Exception e) {
            System.out.println("SEP XML : "+e);
        }
        
        cekLAYAN();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup = new javax.swing.JPopupMenu();
        ppSEP = new javax.swing.JMenuItem();
        ppDetailSEPvclaim = new javax.swing.JMenuItem();
        ppSEPinternal = new javax.swing.JMenuItem();
        ppUpdateSEPdariVclaim = new javax.swing.JMenuItem();
        ppPulang = new javax.swing.JMenuItem();
        ppProgramPRB = new javax.swing.JMenuItem();
        ppRencanaKontrollagi = new javax.swing.JMenuItem();
        ppDataRujukanKeluar = new javax.swing.JMenuItem();
        ppMembuatRujukan = new javax.swing.JMenu();
        ppRujukanBiasa = new javax.swing.JMenuItem();
        ppAmbilRujukanBiasaVclaim = new javax.swing.JMenuItem();
        ppRujukanKhusus = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MnRekapSEPRanap = new javax.swing.JMenuItem();
        MnRekapSEPRalan = new javax.swing.JMenuItem();
        Popup1 = new javax.swing.JPopupMenu();
        ppPengajuan = new javax.swing.JMenu();
        MnBackdate = new javax.swing.JMenuItem();
        MnFingerPrin = new javax.swing.JMenuItem();
        ppAprovalSEP = new javax.swing.JMenu();
        MnBackdate1 = new javax.swing.JMenuItem();
        MnFingerPrin1 = new javax.swing.JMenuItem();
        ppAmbilSep = new javax.swing.JMenuItem();
        ppRencanaKontrol = new javax.swing.JMenuItem();
        ppSPRI = new javax.swing.JMenuItem();
        ppCekFingerPrin = new javax.swing.JMenuItem();
        Popup2 = new javax.swing.JPopupMenu();
        ppRencanaKontrollagi1 = new javax.swing.JMenuItem();
        WindowUpdatePulang = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        jLabel26 = new widget.Label();
        jLabel38 = new widget.Label();
        cmbSttsPlg = new widget.ComboBox();
        jLabel44 = new widget.Label();
        noSrtMati = new widget.TextBox();
        tglMati = new widget.Tanggal();
        jLabel48 = new widget.Label();
        noLP = new widget.Tanggal();
        jLabel52 = new widget.Label();
        tglPlgRuangan = new widget.TextBox();
        jLabel54 = new widget.Label();
        BtnUpdatePulang = new widget.Button();
        WindowRujukanBiasa = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        BtnCloseIn5 = new widget.Button();
        BtnSimpan5 = new widget.Button();
        jLabel30 = new widget.Label();
        TanggalRujukKeluar = new widget.Tanggal();
        jLabel12 = new widget.Label();
        KdPpkRujukan1 = new widget.TextBox();
        NmPpkRujukan1 = new widget.TextBox();
        btnPPKRujukan1 = new widget.Button();
        jLabel31 = new widget.Label();
        JenisPelayanan1 = new widget.ComboBox();
        jLabel32 = new widget.Label();
        KdPenyakit1 = new widget.TextBox();
        NmPenyakit1 = new widget.TextBox();
        btnDiagnosa1 = new widget.Button();
        nosep = new widget.Label();
        KdPoli1 = new widget.TextBox();
        NmPoli1 = new widget.TextBox();
        btnPoli1 = new widget.Button();
        jLabel33 = new widget.Label();
        TipeRujukan = new widget.ComboBox();
        jLabel34 = new widget.Label();
        Catatan1 = new widget.TextBox();
        LabelPoli2 = new widget.Label();
        sepRujuk = new widget.TextBox();
        BtnResetRujuk = new widget.Button();
        jLabel49 = new widget.Label();
        tglRencanaKunjungan = new widget.Tanggal();
        BtnGantiRujukan = new widget.Button();
        WindowRujukanKhusus = new javax.swing.JDialog();
        internalFrame10 = new widget.InternalFrame();
        BtnCloseIn7 = new widget.Button();
        BtnSimpan7 = new widget.Button();
        jLabel50 = new widget.Label();
        jLabel51 = new widget.Label();
        btnDiagSekunder = new widget.Button();
        jLabel53 = new widget.Label();
        btnProsedur = new widget.Button();
        LabelPoli3 = new widget.Label();
        sepRujukKhusus = new widget.TextBox();
        BtnResetRujuk1 = new widget.Button();
        jLabel56 = new widget.Label();
        noRujukanKhusus = new widget.TextBox();
        kdDiagPrimer = new widget.TextBox();
        nmDiagPrimer = new widget.TextBox();
        btnDiagPrimer = new widget.Button();
        Scroll4 = new widget.ScrollPane();
        tbdiagSekunder = new widget.Table();
        BtnHapusSekun = new widget.Button();
        Scroll5 = new widget.ScrollPane();
        tbProsedur = new widget.Table();
        BtnHapusProsedur = new widget.Button();
        WindowCariSEP = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        BtnCloseIn6 = new widget.Button();
        BtnSimpan6 = new widget.Button();
        NoSEP = new widget.TextBox();
        BtnCari1 = new widget.Button();
        ChkNoSEP = new widget.CekBox();
        WindowCariNoRujuk = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        TabRujukan = new javax.swing.JTabbedPane();
        internalFrame4 = new widget.InternalFrame();
        scrollPane3 = new widget.ScrollPane();
        tbFaskes3 = new widget.Table();
        internalFrame9 = new widget.InternalFrame();
        scrollPane4 = new widget.ScrollPane();
        tbFaskes4 = new widget.Table();
        internalFrame2 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbFaskes1 = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        scrollPane2 = new widget.ScrollPane();
        tbFaskes2 = new widget.Table();
        panelisi1 = new widget.panelisi();
        BtnKeluar1 = new widget.Button();
        WindowCariRujukanVClaim = new javax.swing.JDialog();
        internalFrame14 = new widget.InternalFrame();
        BtnCloseIn8 = new widget.Button();
        BtnSimpan8 = new widget.Button();
        NoRujukanVclaim = new widget.TextBox();
        jLabel9 = new widget.Label();
        NoBalasan = new widget.TextBox();
        LokasiLaka = new widget.TextBox();
        kode_rujukanya = new widget.TextBox();
        nmfaskes_keluar = new widget.TextBox();
        KdPPK = new widget.TextBox();
        NmPPK = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame11 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput1 = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel5 = new widget.Label();
        NoKartu = new widget.TextBox();
        jLabel20 = new widget.Label();
        TanggalSEP = new widget.Tanggal();
        jLabel22 = new widget.Label();
        TanggalRujuk = new widget.Tanggal();
        jLabel23 = new widget.Label();
        NoRujukan = new widget.TextBox();
        label_surat = new widget.Label();
        btnPPKRujukan = new widget.Button();
        jLabel10 = new widget.Label();
        KdPpkRujukan = new widget.TextBox();
        NmPpkRujukan = new widget.TextBox();
        jLabel11 = new widget.Label();
        NmPenyakit = new widget.TextBox();
        btnDiagnosa = new widget.Button();
        btnPoli = new widget.Button();
        NmPoli = new widget.TextBox();
        KdPoli = new widget.TextBox();
        LabelKatarak = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        JenisPelayanan = new widget.ComboBox();
        hakKelas = new widget.ComboBox();
        LabTglkll = new widget.Label();
        LakaLantas = new widget.ComboBox();
        LabNoSup = new widget.Label();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel18 = new widget.Label();
        jLabel24 = new widget.Label();
        JenisPeserta = new widget.TextBox();
        jLabel25 = new widget.Label();
        Status = new widget.TextBox();
        rujukanSEP = new widget.Label();
        AsalRujukan = new widget.ComboBox();
        jLabel15 = new widget.Label();
        Eksekutif = new widget.ComboBox();
        LabelKelas1 = new widget.Label();
        COB = new widget.ComboBox();
        LabKetkll = new widget.Label();
        jLabel29 = new widget.Label();
        NoTelp = new widget.TextBox();
        jLabel36 = new widget.Label();
        TanggalKejadian = new widget.Tanggal();
        Ket = new widget.TextBox();
        LabProv = new widget.Label();
        LabKab = new widget.Label();
        KdProv = new widget.TextBox();
        KdKab = new widget.TextBox();
        KdKec = new widget.TextBox();
        NmProv = new widget.TextBox();
        NmKab = new widget.TextBox();
        NmKec = new widget.TextBox();
        btnProv = new widget.Button();
        btnKab = new widget.Button();
        btnKec = new widget.Button();
        LabKec = new widget.Label();
        suplesi = new widget.ComboBox();
        LabSup = new widget.Label();
        NoSEPSuplesi = new widget.TextBox();
        LabelPoli = new widget.Label();
        KasusKatarak = new widget.ComboBox();
        jLabel42 = new widget.Label();
        noSurat = new widget.TextBox();
        NmDPJP = new widget.TextBox();
        btnDPJP = new widget.Button();
        btnNoSurat = new widget.Button();
        btnNoRujukan = new widget.Button();
        jLabel28 = new widget.Label();
        Scroll3 = new widget.ScrollPane();
        Catatan = new widget.TextArea();
        jLabel16 = new widget.Label();
        jLabel17 = new widget.Label();
        jLabel27 = new widget.Label();
        jLabel37 = new widget.Label();
        pngJwb = new widget.TextBox();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        jLabel41 = new widget.Label();
        tujuanKun = new widget.ComboBox();
        flagPro = new widget.ComboBox();
        kdPenunjang = new widget.ComboBox();
        asesmenPel = new widget.ComboBox();
        jLabel45 = new widget.Label();
        btnDPJPlayan = new widget.Button();
        cmbPembiayaan = new widget.ComboBox();
        jLabel46 = new widget.Label();
        nmdpjpLayan = new widget.TextBox();
        JK = new widget.TextBox();
        btnNoRujukanInap = new widget.Button();
        kdNaikKls = new widget.TextBox();
        nmKlsNaik = new widget.TextBox();
        btnNaikKls = new widget.Button();
        Kddpjp = new widget.TextBox();
        KdPenyakit = new widget.TextBox();
        KddpjpLayan = new widget.TextBox();
        jPanel4 = new javax.swing.JPanel();
        panelGlass10 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar = new widget.Button();
        internalFrame12 = new widget.InternalFrame();
        scrollInput1 = new widget.ScrollPane();
        tbSEP = new widget.Table();
        jPanel5 = new javax.swing.JPanel();
        panelGlass11 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar2 = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        internalFrame13 = new widget.InternalFrame();
        scrollInput2 = new widget.ScrollPane();
        tbRiwayat = new widget.Table();
        jPanel6 = new javax.swing.JPanel();
        panelGlass12 = new widget.panelisi();
        jLabel47 = new widget.Label();
        LCount1 = new widget.Label();
        BtnKeluar3 = new widget.Button();

        Popup.setName("Popup"); // NOI18N

        ppSEP.setBackground(new java.awt.Color(242, 242, 242));
        ppSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSEP.setText("Print SEP");
        ppSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSEP.setIconTextGap(8);
        ppSEP.setName("ppSEP"); // NOI18N
        ppSEP.setPreferredSize(new java.awt.Dimension(200, 25));
        ppSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSEPBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppSEP);

        ppDetailSEPvclaim.setBackground(new java.awt.Color(242, 242, 242));
        ppDetailSEPvclaim.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDetailSEPvclaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDetailSEPvclaim.setText("Detail SEP Pasien di VClaim");
        ppDetailSEPvclaim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppDetailSEPvclaim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppDetailSEPvclaim.setIconTextGap(8);
        ppDetailSEPvclaim.setName("ppDetailSEPvclaim"); // NOI18N
        ppDetailSEPvclaim.setPreferredSize(new java.awt.Dimension(200, 25));
        ppDetailSEPvclaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDetailSEPvclaimBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppDetailSEPvclaim);

        ppSEPinternal.setBackground(new java.awt.Color(242, 242, 242));
        ppSEPinternal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSEPinternal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSEPinternal.setText("Lihat SEP Internal");
        ppSEPinternal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSEPinternal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSEPinternal.setIconTextGap(8);
        ppSEPinternal.setName("ppSEPinternal"); // NOI18N
        ppSEPinternal.setPreferredSize(new java.awt.Dimension(200, 25));
        ppSEPinternal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSEPinternalBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppSEPinternal);

        ppUpdateSEPdariVclaim.setBackground(new java.awt.Color(242, 242, 242));
        ppUpdateSEPdariVclaim.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppUpdateSEPdariVclaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppUpdateSEPdariVclaim.setText("Update SEP dari VCLAIM");
        ppUpdateSEPdariVclaim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppUpdateSEPdariVclaim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppUpdateSEPdariVclaim.setIconTextGap(8);
        ppUpdateSEPdariVclaim.setName("ppUpdateSEPdariVclaim"); // NOI18N
        ppUpdateSEPdariVclaim.setPreferredSize(new java.awt.Dimension(200, 25));
        ppUpdateSEPdariVclaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppUpdateSEPdariVclaimBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppUpdateSEPdariVclaim);

        ppPulang.setBackground(new java.awt.Color(242, 242, 242));
        ppPulang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPulang.setText("Update Tgl. Pulang");
        ppPulang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPulang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPulang.setIconTextGap(8);
        ppPulang.setName("ppPulang"); // NOI18N
        ppPulang.setPreferredSize(new java.awt.Dimension(200, 25));
        ppPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPulangBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppPulang);

        ppProgramPRB.setBackground(new java.awt.Color(242, 242, 242));
        ppProgramPRB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppProgramPRB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppProgramPRB.setText("Program PRB BPJS");
        ppProgramPRB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppProgramPRB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppProgramPRB.setIconTextGap(8);
        ppProgramPRB.setName("ppProgramPRB"); // NOI18N
        ppProgramPRB.setPreferredSize(new java.awt.Dimension(200, 25));
        ppProgramPRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppProgramPRBActionPerformed(evt);
            }
        });
        Popup.add(ppProgramPRB);

        ppRencanaKontrollagi.setBackground(new java.awt.Color(242, 242, 242));
        ppRencanaKontrollagi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRencanaKontrollagi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRencanaKontrollagi.setText("Penjadwalan Rencana Kontrol");
        ppRencanaKontrollagi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRencanaKontrollagi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRencanaKontrollagi.setIconTextGap(8);
        ppRencanaKontrollagi.setName("ppRencanaKontrollagi"); // NOI18N
        ppRencanaKontrollagi.setPreferredSize(new java.awt.Dimension(200, 25));
        ppRencanaKontrollagi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRencanaKontrollagiActionPerformed(evt);
            }
        });
        Popup.add(ppRencanaKontrollagi);

        ppDataRujukanKeluar.setBackground(new java.awt.Color(242, 242, 242));
        ppDataRujukanKeluar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDataRujukanKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDataRujukanKeluar.setText("Data Rujukan Keluar");
        ppDataRujukanKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppDataRujukanKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppDataRujukanKeluar.setIconTextGap(8);
        ppDataRujukanKeluar.setName("ppDataRujukanKeluar"); // NOI18N
        ppDataRujukanKeluar.setPreferredSize(new java.awt.Dimension(200, 25));
        ppDataRujukanKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDataRujukanKeluarActionPerformed(evt);
            }
        });
        Popup.add(ppDataRujukanKeluar);

        ppMembuatRujukan.setBackground(new java.awt.Color(242, 242, 242));
        ppMembuatRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppMembuatRujukan.setText("Membuat Rujukan Keluar");
        ppMembuatRujukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppMembuatRujukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppMembuatRujukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppMembuatRujukan.setIconTextGap(8);
        ppMembuatRujukan.setName("ppMembuatRujukan"); // NOI18N
        ppMembuatRujukan.setOpaque(true);
        ppMembuatRujukan.setPreferredSize(new java.awt.Dimension(200, 25));

        ppRujukanBiasa.setBackground(new java.awt.Color(242, 242, 242));
        ppRujukanBiasa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRujukanBiasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRujukanBiasa.setText("Rujukan Biasa");
        ppRujukanBiasa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRujukanBiasa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRujukanBiasa.setIconTextGap(8);
        ppRujukanBiasa.setName("ppRujukanBiasa"); // NOI18N
        ppRujukanBiasa.setPreferredSize(new java.awt.Dimension(210, 25));
        ppRujukanBiasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRujukanBiasaBtnPrintActionPerformed(evt);
            }
        });
        ppMembuatRujukan.add(ppRujukanBiasa);

        ppAmbilRujukanBiasaVclaim.setBackground(new java.awt.Color(242, 242, 242));
        ppAmbilRujukanBiasaVclaim.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppAmbilRujukanBiasaVclaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppAmbilRujukanBiasaVclaim.setText("Ambil Rujukan Biasa dari VClaim");
        ppAmbilRujukanBiasaVclaim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppAmbilRujukanBiasaVclaim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppAmbilRujukanBiasaVclaim.setIconTextGap(8);
        ppAmbilRujukanBiasaVclaim.setName("ppAmbilRujukanBiasaVclaim"); // NOI18N
        ppAmbilRujukanBiasaVclaim.setPreferredSize(new java.awt.Dimension(210, 25));
        ppAmbilRujukanBiasaVclaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppAmbilRujukanBiasaVclaimBtnPrintActionPerformed(evt);
            }
        });
        ppMembuatRujukan.add(ppAmbilRujukanBiasaVclaim);

        ppRujukanKhusus.setBackground(new java.awt.Color(242, 242, 242));
        ppRujukanKhusus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRujukanKhusus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRujukanKhusus.setText("Rujukan Khusus");
        ppRujukanKhusus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRujukanKhusus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRujukanKhusus.setIconTextGap(8);
        ppRujukanKhusus.setName("ppRujukanKhusus"); // NOI18N
        ppRujukanKhusus.setPreferredSize(new java.awt.Dimension(210, 25));
        ppRujukanKhusus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRujukanKhususBtnPrintActionPerformed(evt);
            }
        });
        ppMembuatRujukan.add(ppRujukanKhusus);

        Popup.add(ppMembuatRujukan);

        jSeparator1.setForeground(new java.awt.Color(170, 190, 145));
        jSeparator1.setName("jSeparator1"); // NOI18N
        Popup.add(jSeparator1);

        MnRekapSEPRanap.setBackground(new java.awt.Color(242, 242, 242));
        MnRekapSEPRanap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapSEPRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnRekapSEPRanap.setText("Rekap No. SEP R. INAP");
        MnRekapSEPRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekapSEPRanap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekapSEPRanap.setIconTextGap(8);
        MnRekapSEPRanap.setName("MnRekapSEPRanap"); // NOI18N
        MnRekapSEPRanap.setPreferredSize(new java.awt.Dimension(200, 25));
        MnRekapSEPRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapSEPRanapBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(MnRekapSEPRanap);

        MnRekapSEPRalan.setBackground(new java.awt.Color(242, 242, 242));
        MnRekapSEPRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapSEPRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnRekapSEPRalan.setText("Rekap No. SEP R. JALAN");
        MnRekapSEPRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekapSEPRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekapSEPRalan.setIconTextGap(8);
        MnRekapSEPRalan.setName("MnRekapSEPRalan"); // NOI18N
        MnRekapSEPRalan.setPreferredSize(new java.awt.Dimension(200, 25));
        MnRekapSEPRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapSEPRalanBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(MnRekapSEPRalan);

        Popup1.setName("Popup1"); // NOI18N

        ppPengajuan.setBackground(new java.awt.Color(242, 242, 242));
        ppPengajuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPengajuan.setText("Pengajuan SEP");
        ppPengajuan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPengajuan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPengajuan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPengajuan.setIconTextGap(5);
        ppPengajuan.setName("ppPengajuan"); // NOI18N
        ppPengajuan.setOpaque(true);
        ppPengajuan.setPreferredSize(new java.awt.Dimension(195, 25));

        MnBackdate.setBackground(new java.awt.Color(242, 242, 242));
        MnBackdate.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBackdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBackdate.setText("Backdate");
        MnBackdate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBackdate.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBackdate.setIconTextGap(5);
        MnBackdate.setName("MnBackdate"); // NOI18N
        MnBackdate.setPreferredSize(new java.awt.Dimension(110, 25));
        MnBackdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBackdateActionPerformed(evt);
            }
        });
        ppPengajuan.add(MnBackdate);

        MnFingerPrin.setBackground(new java.awt.Color(242, 242, 242));
        MnFingerPrin.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnFingerPrin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnFingerPrin.setText("Finger Print");
        MnFingerPrin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnFingerPrin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnFingerPrin.setIconTextGap(5);
        MnFingerPrin.setName("MnFingerPrin"); // NOI18N
        MnFingerPrin.setPreferredSize(new java.awt.Dimension(110, 25));
        MnFingerPrin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFingerPrinActionPerformed(evt);
            }
        });
        ppPengajuan.add(MnFingerPrin);

        Popup1.add(ppPengajuan);

        ppAprovalSEP.setBackground(new java.awt.Color(242, 242, 242));
        ppAprovalSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppAprovalSEP.setText("Aproval Pengajuan SEP");
        ppAprovalSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppAprovalSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppAprovalSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppAprovalSEP.setIconTextGap(5);
        ppAprovalSEP.setName("ppAprovalSEP"); // NOI18N
        ppAprovalSEP.setOpaque(true);
        ppAprovalSEP.setPreferredSize(new java.awt.Dimension(195, 25));

        MnBackdate1.setBackground(new java.awt.Color(242, 242, 242));
        MnBackdate1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBackdate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBackdate1.setText("Backdate");
        MnBackdate1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBackdate1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBackdate1.setIconTextGap(5);
        MnBackdate1.setName("MnBackdate1"); // NOI18N
        MnBackdate1.setPreferredSize(new java.awt.Dimension(110, 25));
        MnBackdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBackdate1ActionPerformed(evt);
            }
        });
        ppAprovalSEP.add(MnBackdate1);

        MnFingerPrin1.setBackground(new java.awt.Color(242, 242, 242));
        MnFingerPrin1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnFingerPrin1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnFingerPrin1.setText("Finger Print");
        MnFingerPrin1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnFingerPrin1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnFingerPrin1.setIconTextGap(5);
        MnFingerPrin1.setName("MnFingerPrin1"); // NOI18N
        MnFingerPrin1.setPreferredSize(new java.awt.Dimension(110, 25));
        MnFingerPrin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFingerPrin1ActionPerformed(evt);
            }
        });
        ppAprovalSEP.add(MnFingerPrin1);

        Popup1.add(ppAprovalSEP);

        ppAmbilSep.setBackground(new java.awt.Color(242, 242, 242));
        ppAmbilSep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppAmbilSep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppAmbilSep.setText("Ambil SEP di VClaim");
        ppAmbilSep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppAmbilSep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppAmbilSep.setIconTextGap(5);
        ppAmbilSep.setName("ppAmbilSep"); // NOI18N
        ppAmbilSep.setPreferredSize(new java.awt.Dimension(195, 25));
        ppAmbilSep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppAmbilSepBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(ppAmbilSep);

        ppRencanaKontrol.setBackground(new java.awt.Color(242, 242, 242));
        ppRencanaKontrol.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRencanaKontrol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRencanaKontrol.setText("Penjadwalan Rencana Kontrol");
        ppRencanaKontrol.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRencanaKontrol.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRencanaKontrol.setIconTextGap(5);
        ppRencanaKontrol.setName("ppRencanaKontrol"); // NOI18N
        ppRencanaKontrol.setPreferredSize(new java.awt.Dimension(195, 25));
        ppRencanaKontrol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRencanaKontrolBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(ppRencanaKontrol);

        ppSPRI.setBackground(new java.awt.Color(242, 242, 242));
        ppSPRI.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSPRI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSPRI.setText("Pembuatan SPRI BPJS");
        ppSPRI.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSPRI.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSPRI.setIconTextGap(5);
        ppSPRI.setName("ppSPRI"); // NOI18N
        ppSPRI.setPreferredSize(new java.awt.Dimension(195, 25));
        ppSPRI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSPRIBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(ppSPRI);

        ppCekFingerPrin.setBackground(new java.awt.Color(242, 242, 242));
        ppCekFingerPrin.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCekFingerPrin.setForeground(new java.awt.Color(50, 50, 50));
        ppCekFingerPrin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCekFingerPrin.setText("Cek Finger Print BPJS");
        ppCekFingerPrin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCekFingerPrin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCekFingerPrin.setIconTextGap(5);
        ppCekFingerPrin.setName("ppCekFingerPrin"); // NOI18N
        ppCekFingerPrin.setPreferredSize(new java.awt.Dimension(195, 25));
        ppCekFingerPrin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCekFingerPrinBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(ppCekFingerPrin);

        Popup2.setName("Popup2"); // NOI18N

        ppRencanaKontrollagi1.setBackground(new java.awt.Color(242, 242, 242));
        ppRencanaKontrollagi1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRencanaKontrollagi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRencanaKontrollagi1.setText("Penjadwalan Rencana Kontrol");
        ppRencanaKontrollagi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRencanaKontrollagi1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRencanaKontrollagi1.setIconTextGap(8);
        ppRencanaKontrollagi1.setName("ppRencanaKontrollagi1"); // NOI18N
        ppRencanaKontrollagi1.setPreferredSize(new java.awt.Dimension(200, 25));
        ppRencanaKontrollagi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRencanaKontrollagi1ActionPerformed(evt);
            }
        });
        Popup2.add(ppRencanaKontrollagi1);

        WindowUpdatePulang.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowUpdatePulang.setName("WindowUpdatePulang"); // NOI18N
        WindowUpdatePulang.setUndecorated(true);
        WindowUpdatePulang.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Update Tanggal Pulang ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(null);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(300, 170, 100, 30);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Status Pulang :");
        jLabel26.setName("jLabel26"); // NOI18N
        internalFrame5.add(jLabel26);
        jLabel26.setBounds(6, 32, 120, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("No. Surat Meninggal :");
        jLabel38.setName("jLabel38"); // NOI18N
        internalFrame5.add(jLabel38);
        jLabel38.setBounds(6, 62, 120, 23);

        cmbSttsPlg.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsPlg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Dirujuk", "APS", "Meninggal >= 48 Jam", "Meninggal < 48 Jam", "Sembuh/BLPL", "Kabur" }));
        cmbSttsPlg.setName("cmbSttsPlg"); // NOI18N
        internalFrame5.add(cmbSttsPlg);
        cmbSttsPlg.setBounds(130, 32, 140, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Tgl. Meninggal :");
        jLabel44.setToolTipText("");
        jLabel44.setName("jLabel44"); // NOI18N
        internalFrame5.add(jLabel44);
        jLabel44.setBounds(6, 91, 120, 23);

        noSrtMati.setEditable(false);
        noSrtMati.setBackground(new java.awt.Color(245, 250, 240));
        noSrtMati.setForeground(new java.awt.Color(0, 0, 0));
        noSrtMati.setName("noSrtMati"); // NOI18N
        internalFrame5.add(noSrtMati);
        noSrtMati.setBounds(130, 62, 280, 23);

        tglMati.setEditable(false);
        tglMati.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-08-2023" }));
        tglMati.setDisplayFormat("dd-MM-yyyy");
        tglMati.setName("tglMati"); // NOI18N
        tglMati.setOpaque(false);
        tglMati.setPreferredSize(new java.awt.Dimension(95, 23));
        internalFrame5.add(tglMati);
        tglMati.setBounds(130, 91, 90, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("No. LP Manual :");
        jLabel48.setToolTipText("");
        jLabel48.setName("jLabel48"); // NOI18N
        internalFrame5.add(jLabel48);
        jLabel48.setBounds(220, 91, 90, 23);

        noLP.setEditable(false);
        noLP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-08-2023" }));
        noLP.setDisplayFormat("dd-MM-yyyy");
        noLP.setName("noLP"); // NOI18N
        noLP.setOpaque(false);
        noLP.setPreferredSize(new java.awt.Dimension(95, 23));
        internalFrame5.add(noLP);
        noLP.setBounds(315, 91, 90, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Tgl. Pulang dari ");
        jLabel52.setToolTipText("");
        jLabel52.setName("jLabel52"); // NOI18N
        internalFrame5.add(jLabel52);
        jLabel52.setBounds(6, 121, 120, 23);

        tglPlgRuangan.setEditable(false);
        tglPlgRuangan.setBackground(new java.awt.Color(245, 250, 240));
        tglPlgRuangan.setForeground(new java.awt.Color(0, 0, 0));
        tglPlgRuangan.setName("tglPlgRuangan"); // NOI18N
        internalFrame5.add(tglPlgRuangan);
        tglPlgRuangan.setBounds(130, 121, 128, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Ruang Rawat Inap : ");
        jLabel54.setToolTipText("");
        jLabel54.setName("jLabel54"); // NOI18N
        internalFrame5.add(jLabel54);
        jLabel54.setBounds(6, 135, 120, 23);

        BtnUpdatePulang.setForeground(new java.awt.Color(0, 0, 0));
        BtnUpdatePulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnUpdatePulang.setMnemonic('S');
        BtnUpdatePulang.setText("Simpan");
        BtnUpdatePulang.setToolTipText("Alt+S");
        BtnUpdatePulang.setName("BtnUpdatePulang"); // NOI18N
        BtnUpdatePulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdatePulangActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnUpdatePulang);
        BtnUpdatePulang.setBounds(190, 170, 100, 30);

        WindowUpdatePulang.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowRujukanBiasa.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRujukanBiasa.setName("WindowRujukanBiasa"); // NOI18N
        WindowRujukanBiasa.setUndecorated(true);
        WindowRujukanBiasa.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Membuat Rujukan Keluar VClaim (BIASA) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(null);

        BtnCloseIn5.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn5.setMnemonic('U');
        BtnCloseIn5.setText("Tutup");
        BtnCloseIn5.setToolTipText("Alt+U");
        BtnCloseIn5.setName("BtnCloseIn5"); // NOI18N
        BtnCloseIn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn5ActionPerformed(evt);
            }
        });
        internalFrame6.add(BtnCloseIn5);
        BtnCloseIn5.setBounds(550, 230, 100, 30);

        BtnSimpan5.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan5.setMnemonic('S');
        BtnSimpan5.setText("Simpan");
        BtnSimpan5.setToolTipText("Alt+S");
        BtnSimpan5.setName("BtnSimpan5"); // NOI18N
        BtnSimpan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan5ActionPerformed(evt);
            }
        });
        internalFrame6.add(BtnSimpan5);
        BtnSimpan5.setBounds(330, 230, 100, 30);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tgl. Rujukan :");
        jLabel30.setName("jLabel30"); // NOI18N
        internalFrame6.add(jLabel30);
        jLabel30.setBounds(0, 25, 160, 23);

        TanggalRujukKeluar.setEditable(false);
        TanggalRujukKeluar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-08-2023" }));
        TanggalRujukKeluar.setDisplayFormat("dd-MM-yyyy");
        TanggalRujukKeluar.setName("TanggalRujukKeluar"); // NOI18N
        TanggalRujukKeluar.setOpaque(false);
        TanggalRujukKeluar.setPreferredSize(new java.awt.Dimension(95, 23));
        internalFrame6.add(TanggalRujukKeluar);
        TanggalRujukKeluar.setBounds(165, 25, 90, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("PPK Rujukan :");
        jLabel12.setName("jLabel12"); // NOI18N
        internalFrame6.add(jLabel12);
        jLabel12.setBounds(0, 83, 160, 23);

        KdPpkRujukan1.setEditable(false);
        KdPpkRujukan1.setBackground(new java.awt.Color(245, 250, 240));
        KdPpkRujukan1.setForeground(new java.awt.Color(0, 0, 0));
        KdPpkRujukan1.setName("KdPpkRujukan1"); // NOI18N
        internalFrame6.add(KdPpkRujukan1);
        KdPpkRujukan1.setBounds(165, 83, 75, 23);

        NmPpkRujukan1.setEditable(false);
        NmPpkRujukan1.setBackground(new java.awt.Color(245, 250, 240));
        NmPpkRujukan1.setForeground(new java.awt.Color(0, 0, 0));
        NmPpkRujukan1.setHighlighter(null);
        NmPpkRujukan1.setName("NmPpkRujukan1"); // NOI18N
        internalFrame6.add(NmPpkRujukan1);
        NmPpkRujukan1.setBounds(245, 83, 384, 23);

        btnPPKRujukan1.setForeground(new java.awt.Color(0, 0, 0));
        btnPPKRujukan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPPKRujukan1.setMnemonic('X');
        btnPPKRujukan1.setToolTipText("Alt+X");
        btnPPKRujukan1.setName("btnPPKRujukan1"); // NOI18N
        btnPPKRujukan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPKRujukan1ActionPerformed(evt);
            }
        });
        internalFrame6.add(btnPPKRujukan1);
        btnPPKRujukan1.setBounds(630, 83, 28, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Jns.Pelayanan :");
        jLabel31.setName("jLabel31"); // NOI18N
        internalFrame6.add(jLabel31);
        jLabel31.setBounds(255, 54, 90, 23);

        JenisPelayanan1.setForeground(new java.awt.Color(0, 0, 0));
        JenisPelayanan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Rawat Inap", "2. Rawat Jalan" }));
        JenisPelayanan1.setSelectedIndex(1);
        JenisPelayanan1.setName("JenisPelayanan1"); // NOI18N
        JenisPelayanan1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JenisPelayanan1ItemStateChanged(evt);
            }
        });
        JenisPelayanan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisPelayanan1KeyPressed(evt);
            }
        });
        internalFrame6.add(JenisPelayanan1);
        JenisPelayanan1.setBounds(350, 54, 110, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Diagnosa Rujuk :");
        jLabel32.setName("jLabel32"); // NOI18N
        internalFrame6.add(jLabel32);
        jLabel32.setBounds(0, 112, 160, 23);

        KdPenyakit1.setEditable(false);
        KdPenyakit1.setBackground(new java.awt.Color(245, 250, 240));
        KdPenyakit1.setForeground(new java.awt.Color(0, 0, 0));
        KdPenyakit1.setHighlighter(null);
        KdPenyakit1.setName("KdPenyakit1"); // NOI18N
        internalFrame6.add(KdPenyakit1);
        KdPenyakit1.setBounds(165, 112, 75, 23);

        NmPenyakit1.setEditable(false);
        NmPenyakit1.setBackground(new java.awt.Color(245, 250, 240));
        NmPenyakit1.setForeground(new java.awt.Color(0, 0, 0));
        NmPenyakit1.setName("NmPenyakit1"); // NOI18N
        internalFrame6.add(NmPenyakit1);
        NmPenyakit1.setBounds(245, 112, 384, 23);

        btnDiagnosa1.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagnosa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiagnosa1.setMnemonic('X');
        btnDiagnosa1.setToolTipText("Alt+X");
        btnDiagnosa1.setName("btnDiagnosa1"); // NOI18N
        btnDiagnosa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosa1ActionPerformed(evt);
            }
        });
        internalFrame6.add(btnDiagnosa1);
        btnDiagnosa1.setBounds(630, 112, 28, 23);

        nosep.setForeground(new java.awt.Color(0, 0, 0));
        nosep.setText("No. SEP :");
        nosep.setName("nosep"); // NOI18N
        internalFrame6.add(nosep);
        nosep.setBounds(0, 170, 160, 23);

        KdPoli1.setEditable(false);
        KdPoli1.setBackground(new java.awt.Color(245, 250, 240));
        KdPoli1.setForeground(new java.awt.Color(0, 0, 0));
        KdPoli1.setHighlighter(null);
        KdPoli1.setName("KdPoli1"); // NOI18N
        internalFrame6.add(KdPoli1);
        KdPoli1.setBounds(165, 141, 75, 23);

        NmPoli1.setEditable(false);
        NmPoli1.setBackground(new java.awt.Color(245, 250, 240));
        NmPoli1.setForeground(new java.awt.Color(0, 0, 0));
        NmPoli1.setHighlighter(null);
        NmPoli1.setName("NmPoli1"); // NOI18N
        internalFrame6.add(NmPoli1);
        NmPoli1.setBounds(245, 141, 384, 23);

        btnPoli1.setForeground(new java.awt.Color(0, 0, 0));
        btnPoli1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPoli1.setMnemonic('X');
        btnPoli1.setToolTipText("Alt+X");
        btnPoli1.setName("btnPoli1"); // NOI18N
        btnPoli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPoli1ActionPerformed(evt);
            }
        });
        internalFrame6.add(btnPoli1);
        btnPoli1.setBounds(630, 141, 28, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Tipe Rujukan :");
        jLabel33.setName("jLabel33"); // NOI18N
        internalFrame6.add(jLabel33);
        jLabel33.setBounds(255, 25, 90, 23);

        TipeRujukan.setForeground(new java.awt.Color(0, 0, 0));
        TipeRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Penuh", "1. Partial", "2. Balik PRB" }));
        TipeRujukan.setName("TipeRujukan"); // NOI18N
        TipeRujukan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TipeRujukanItemStateChanged(evt);
            }
        });
        TipeRujukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TipeRujukanMouseClicked(evt);
            }
        });
        TipeRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipeRujukanActionPerformed(evt);
            }
        });
        TipeRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TipeRujukanKeyPressed(evt);
            }
        });
        internalFrame6.add(TipeRujukan);
        TipeRujukan.setBounds(350, 25, 110, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Catatan :");
        jLabel34.setName("jLabel34"); // NOI18N
        internalFrame6.add(jLabel34);
        jLabel34.setBounds(0, 200, 160, 23);

        Catatan1.setForeground(new java.awt.Color(0, 0, 0));
        Catatan1.setName("Catatan1"); // NOI18N
        Catatan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Catatan1KeyPressed(evt);
            }
        });
        internalFrame6.add(Catatan1);
        Catatan1.setBounds(165, 200, 490, 23);

        LabelPoli2.setForeground(new java.awt.Color(0, 0, 0));
        LabelPoli2.setText("Poliklinik Tujuan :");
        LabelPoli2.setName("LabelPoli2"); // NOI18N
        internalFrame6.add(LabelPoli2);
        LabelPoli2.setBounds(0, 141, 160, 23);

        sepRujuk.setEditable(false);
        sepRujuk.setForeground(new java.awt.Color(0, 0, 0));
        sepRujuk.setName("sepRujuk"); // NOI18N
        sepRujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sepRujukKeyPressed(evt);
            }
        });
        internalFrame6.add(sepRujuk);
        sepRujuk.setBounds(165, 170, 160, 23);

        BtnResetRujuk.setForeground(new java.awt.Color(0, 0, 0));
        BtnResetRujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnResetRujuk.setMnemonic('B');
        BtnResetRujuk.setText("Baru");
        BtnResetRujuk.setToolTipText("Alt+B");
        BtnResetRujuk.setName("BtnResetRujuk"); // NOI18N
        BtnResetRujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResetRujukActionPerformed(evt);
            }
        });
        internalFrame6.add(BtnResetRujuk);
        BtnResetRujuk.setBounds(220, 230, 100, 30);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Tgl. Rencana Kunjungan :");
        jLabel49.setName("jLabel49"); // NOI18N
        internalFrame6.add(jLabel49);
        jLabel49.setBounds(0, 54, 160, 23);

        tglRencanaKunjungan.setEditable(false);
        tglRencanaKunjungan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-08-2023" }));
        tglRencanaKunjungan.setDisplayFormat("dd-MM-yyyy");
        tglRencanaKunjungan.setName("tglRencanaKunjungan"); // NOI18N
        tglRencanaKunjungan.setOpaque(false);
        tglRencanaKunjungan.setPreferredSize(new java.awt.Dimension(95, 23));
        internalFrame6.add(tglRencanaKunjungan);
        tglRencanaKunjungan.setBounds(165, 54, 90, 23);

        BtnGantiRujukan.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiRujukan.setMnemonic('G');
        BtnGantiRujukan.setText("Ganti");
        BtnGantiRujukan.setToolTipText("Alt+G");
        BtnGantiRujukan.setName("BtnGantiRujukan"); // NOI18N
        BtnGantiRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiRujukanActionPerformed(evt);
            }
        });
        internalFrame6.add(BtnGantiRujukan);
        BtnGantiRujukan.setBounds(440, 230, 100, 30);

        WindowRujukanBiasa.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        WindowRujukanKhusus.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRujukanKhusus.setName("WindowRujukanKhusus"); // NOI18N
        WindowRujukanKhusus.setUndecorated(true);
        WindowRujukanKhusus.setResizable(false);

        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Membuat Rujukan Keluar VClaim (KHUSUS) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame10.setLayout(null);

        BtnCloseIn7.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn7.setMnemonic('U');
        BtnCloseIn7.setText("Tutup");
        BtnCloseIn7.setToolTipText("Alt+U");
        BtnCloseIn7.setName("BtnCloseIn7"); // NOI18N
        BtnCloseIn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn7ActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnCloseIn7);
        BtnCloseIn7.setBounds(520, 330, 80, 30);

        BtnSimpan7.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan7.setMnemonic('S');
        BtnSimpan7.setText("Simpan");
        BtnSimpan7.setToolTipText("Alt+S");
        BtnSimpan7.setName("BtnSimpan7"); // NOI18N
        BtnSimpan7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan7ActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnSimpan7);
        BtnSimpan7.setBounds(410, 330, 100, 30);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("No. Rujukan :");
        jLabel50.setName("jLabel50"); // NOI18N
        internalFrame10.add(jLabel50);
        jLabel50.setBounds(0, 25, 130, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Diagnosa Sekunder :");
        jLabel51.setName("jLabel51"); // NOI18N
        internalFrame10.add(jLabel51);
        jLabel51.setBounds(0, 83, 130, 23);

        btnDiagSekunder.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagSekunder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiagSekunder.setMnemonic('X');
        btnDiagSekunder.setToolTipText("Alt+X");
        btnDiagSekunder.setName("btnDiagSekunder"); // NOI18N
        btnDiagSekunder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagSekunderActionPerformed(evt);
            }
        });
        internalFrame10.add(btnDiagSekunder);
        btnDiagSekunder.setBounds(600, 83, 28, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Prosedur / Tindakan :");
        jLabel53.setName("jLabel53"); // NOI18N
        internalFrame10.add(jLabel53);
        jLabel53.setBounds(0, 189, 130, 23);

        btnProsedur.setForeground(new java.awt.Color(0, 0, 0));
        btnProsedur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnProsedur.setMnemonic('X');
        btnProsedur.setToolTipText("Alt+X");
        btnProsedur.setName("btnProsedur"); // NOI18N
        btnProsedur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsedurActionPerformed(evt);
            }
        });
        internalFrame10.add(btnProsedur);
        btnProsedur.setBounds(600, 189, 28, 23);

        LabelPoli3.setForeground(new java.awt.Color(0, 0, 0));
        LabelPoli3.setText("No. SEP :");
        LabelPoli3.setName("LabelPoli3"); // NOI18N
        internalFrame10.add(LabelPoli3);
        LabelPoli3.setBounds(0, 295, 130, 23);

        sepRujukKhusus.setEditable(false);
        sepRujukKhusus.setForeground(new java.awt.Color(0, 0, 0));
        sepRujukKhusus.setName("sepRujukKhusus"); // NOI18N
        sepRujukKhusus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sepRujukKhususKeyPressed(evt);
            }
        });
        internalFrame10.add(sepRujukKhusus);
        sepRujukKhusus.setBounds(135, 295, 160, 23);

        BtnResetRujuk1.setForeground(new java.awt.Color(0, 0, 0));
        BtnResetRujuk1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnResetRujuk1.setMnemonic('B');
        BtnResetRujuk1.setText("Baru");
        BtnResetRujuk1.setToolTipText("Alt+B");
        BtnResetRujuk1.setName("BtnResetRujuk1"); // NOI18N
        BtnResetRujuk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResetRujuk1ActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnResetRujuk1);
        BtnResetRujuk1.setBounds(330, 330, 70, 30);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Diagnosa Primer :");
        jLabel56.setName("jLabel56"); // NOI18N
        internalFrame10.add(jLabel56);
        jLabel56.setBounds(0, 54, 130, 23);

        noRujukanKhusus.setEditable(false);
        noRujukanKhusus.setForeground(new java.awt.Color(0, 0, 0));
        noRujukanKhusus.setName("noRujukanKhusus"); // NOI18N
        internalFrame10.add(noRujukanKhusus);
        noRujukanKhusus.setBounds(135, 25, 290, 23);

        kdDiagPrimer.setEditable(false);
        kdDiagPrimer.setBackground(new java.awt.Color(245, 250, 240));
        kdDiagPrimer.setForeground(new java.awt.Color(0, 0, 0));
        kdDiagPrimer.setName("kdDiagPrimer"); // NOI18N
        internalFrame10.add(kdDiagPrimer);
        kdDiagPrimer.setBounds(135, 54, 75, 23);

        nmDiagPrimer.setEditable(false);
        nmDiagPrimer.setBackground(new java.awt.Color(245, 250, 240));
        nmDiagPrimer.setForeground(new java.awt.Color(0, 0, 0));
        nmDiagPrimer.setHighlighter(null);
        nmDiagPrimer.setName("nmDiagPrimer"); // NOI18N
        internalFrame10.add(nmDiagPrimer);
        nmDiagPrimer.setBounds(215, 54, 384, 23);

        btnDiagPrimer.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagPrimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiagPrimer.setMnemonic('X');
        btnDiagPrimer.setToolTipText("Alt+X");
        btnDiagPrimer.setName("btnDiagPrimer"); // NOI18N
        btnDiagPrimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagPrimerActionPerformed(evt);
            }
        });
        internalFrame10.add(btnDiagPrimer);
        btnDiagPrimer.setBounds(600, 54, 28, 23);

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbdiagSekunder.setAutoCreateRowSorter(true);
        tbdiagSekunder.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbdiagSekunder.setName("tbdiagSekunder"); // NOI18N
        Scroll4.setViewportView(tbdiagSekunder);

        internalFrame10.add(Scroll4);
        Scroll4.setBounds(135, 83, 460, 100);

        BtnHapusSekun.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusSekun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusSekun.setMnemonic('H');
        BtnHapusSekun.setToolTipText("Alt+H");
        BtnHapusSekun.setName("BtnHapusSekun"); // NOI18N
        BtnHapusSekun.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapusSekun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusSekunActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnHapusSekun);
        BtnHapusSekun.setBounds(600, 112, 28, 28);

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbProsedur.setAutoCreateRowSorter(true);
        tbProsedur.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbProsedur.setName("tbProsedur"); // NOI18N
        Scroll5.setViewportView(tbProsedur);

        internalFrame10.add(Scroll5);
        Scroll5.setBounds(135, 189, 460, 100);

        BtnHapusProsedur.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusProsedur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusProsedur.setMnemonic('H');
        BtnHapusProsedur.setToolTipText("Alt+H");
        BtnHapusProsedur.setName("BtnHapusProsedur"); // NOI18N
        BtnHapusProsedur.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapusProsedur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusProsedurActionPerformed(evt);
            }
        });
        BtnHapusProsedur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusProsedurKeyPressed(evt);
            }
        });
        internalFrame10.add(BtnHapusProsedur);
        BtnHapusProsedur.setBounds(600, 218, 28, 28);

        WindowRujukanKhusus.getContentPane().add(internalFrame10, java.awt.BorderLayout.CENTER);

        WindowCariSEP.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCariSEP.setName("WindowCariSEP"); // NOI18N
        WindowCariSEP.setUndecorated(true);
        WindowCariSEP.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ambil SEP di VClaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame7.setLayout(null);

        BtnCloseIn6.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn6.setMnemonic('U');
        BtnCloseIn6.setText("Tutup");
        BtnCloseIn6.setToolTipText("Alt+U");
        BtnCloseIn6.setName("BtnCloseIn6"); // NOI18N
        BtnCloseIn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn6ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnCloseIn6);
        BtnCloseIn6.setBounds(290, 70, 100, 30);

        BtnSimpan6.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan6.setMnemonic('S');
        BtnSimpan6.setText("Simpan");
        BtnSimpan6.setToolTipText("Alt+S");
        BtnSimpan6.setName("BtnSimpan6"); // NOI18N
        BtnSimpan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan6ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnSimpan6);
        BtnSimpan6.setBounds(20, 70, 100, 30);

        NoSEP.setForeground(new java.awt.Color(0, 0, 0));
        NoSEP.setName("NoSEP"); // NOI18N
        NoSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NoSEPKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSEPKeyPressed(evt);
            }
        });
        internalFrame7.add(NoSEP);
        NoSEP.setBounds(126, 25, 220, 23);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('C');
        BtnCari1.setText("Cek");
        BtnCari1.setToolTipText("Alt+C");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnCari1);
        BtnCari1.setBounds(350, 25, 65, 23);

        ChkNoSEP.setBackground(new java.awt.Color(255, 255, 250));
        ChkNoSEP.setBorder(null);
        ChkNoSEP.setForeground(new java.awt.Color(0, 0, 0));
        ChkNoSEP.setText("No. SEP VClaim :");
        ChkNoSEP.setBorderPainted(true);
        ChkNoSEP.setBorderPaintedFlat(true);
        ChkNoSEP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkNoSEP.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkNoSEP.setName("ChkNoSEP"); // NOI18N
        ChkNoSEP.setOpaque(false);
        ChkNoSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkNoSEPActionPerformed(evt);
            }
        });
        internalFrame7.add(ChkNoSEP);
        ChkNoSEP.setBounds(0, 25, 120, 23);

        WindowCariSEP.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        WindowCariNoRujuk.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCariNoRujuk.setName("WindowCariNoRujuk"); // NOI18N
        WindowCariNoRujuk.setUndecorated(true);
        WindowCariNoRujuk.setResizable(false);
        WindowCariNoRujuk.getContentPane().setLayout(new java.awt.BorderLayout(1, 1));

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Cek Riwayat Rujukan Faskes ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        TabRujukan.setToolTipText("");
        TabRujukan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRujukan.setName("TabRujukan"); // NOI18N
        TabRujukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRujukanMouseClicked(evt);
            }
        });

        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane3.setName("scrollPane3"); // NOI18N
        scrollPane3.setOpaque(true);

        tbFaskes3.setToolTipText("Klik data di tabel");
        tbFaskes3.setName("tbFaskes3"); // NOI18N
        tbFaskes3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaskes3MouseClicked(evt);
            }
        });
        tbFaskes3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaskes3KeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(tbFaskes3);

        internalFrame4.add(scrollPane3, java.awt.BorderLayout.CENTER);

        TabRujukan.addTab(".: Faskes 1 (banyak)", internalFrame4);

        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setOpaque(true);

        tbFaskes4.setToolTipText("Klik data di tabel");
        tbFaskes4.setName("tbFaskes4"); // NOI18N
        tbFaskes4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaskes4MouseClicked(evt);
            }
        });
        tbFaskes4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaskes4KeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(tbFaskes4);

        internalFrame9.add(scrollPane4, java.awt.BorderLayout.CENTER);

        TabRujukan.addTab(".: Faskes 2 (RS) banyak", internalFrame9);

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbFaskes1.setToolTipText("Klik data di tabel");
        tbFaskes1.setName("tbFaskes1"); // NOI18N
        tbFaskes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaskes1MouseClicked(evt);
            }
        });
        tbFaskes1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaskes1KeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbFaskes1);

        internalFrame2.add(scrollPane1, java.awt.BorderLayout.CENTER);

        TabRujukan.addTab(".: Faskes 1", internalFrame2);

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

        tbFaskes2.setToolTipText("Klik data di tabel");
        tbFaskes2.setName("tbFaskes2"); // NOI18N
        tbFaskes2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaskes2MouseClicked(evt);
            }
        });
        tbFaskes2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaskes2KeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(tbFaskes2);

        internalFrame3.add(scrollPane2, java.awt.BorderLayout.CENTER);

        TabRujukan.addTab(".: Faskes 2 (RS)", internalFrame3);

        internalFrame8.add(TabRujukan, java.awt.BorderLayout.CENTER);
        TabRujukan.getAccessibleContext().setAccessibleName(".: Faskes 1");

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(44, 54));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });
        panelisi1.add(BtnKeluar1);

        internalFrame8.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        WindowCariNoRujuk.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        WindowCariRujukanVClaim.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCariRujukanVClaim.setName("WindowCariRujukanVClaim"); // NOI18N
        WindowCariRujukanVClaim.setUndecorated(true);
        WindowCariRujukanVClaim.setResizable(false);

        internalFrame14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ambil Rujukan Biasa di VClaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame14.setName("internalFrame14"); // NOI18N
        internalFrame14.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame14.setLayout(null);

        BtnCloseIn8.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn8.setMnemonic('U');
        BtnCloseIn8.setText("Tutup");
        BtnCloseIn8.setToolTipText("Alt+U");
        BtnCloseIn8.setName("BtnCloseIn8"); // NOI18N
        BtnCloseIn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn8ActionPerformed(evt);
            }
        });
        internalFrame14.add(BtnCloseIn8);
        BtnCloseIn8.setBounds(270, 70, 100, 30);

        BtnSimpan8.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan8.setMnemonic('S');
        BtnSimpan8.setText("Simpan");
        BtnSimpan8.setToolTipText("Alt+S");
        BtnSimpan8.setName("BtnSimpan8"); // NOI18N
        BtnSimpan8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan8ActionPerformed(evt);
            }
        });
        internalFrame14.add(BtnSimpan8);
        BtnSimpan8.setBounds(20, 70, 100, 30);

        NoRujukanVclaim.setForeground(new java.awt.Color(0, 0, 0));
        NoRujukanVclaim.setName("NoRujukanVclaim"); // NOI18N
        NoRujukanVclaim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NoRujukanVclaimKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRujukanVclaimKeyPressed(evt);
            }
        });
        internalFrame14.add(NoRujukanVclaim);
        NoRujukanVclaim.setBounds(95, 25, 280, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("No. Rujukan :");
        jLabel9.setName("jLabel9"); // NOI18N
        internalFrame14.add(jLabel9);
        jLabel9.setBounds(0, 25, 90, 23);

        WindowCariRujukanVClaim.getContentPane().add(internalFrame14, java.awt.BorderLayout.CENTER);

        NoBalasan.setHighlighter(null);
        NoBalasan.setName("NoBalasan"); // NOI18N

        LokasiLaka.setForeground(new java.awt.Color(0, 0, 0));
        LokasiLaka.setHighlighter(null);
        LokasiLaka.setName("LokasiLaka"); // NOI18N
        LokasiLaka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LokasiLakaKeyPressed(evt);
            }
        });

        kode_rujukanya.setForeground(new java.awt.Color(0, 0, 0));
        kode_rujukanya.setHighlighter(null);
        kode_rujukanya.setName("kode_rujukanya"); // NOI18N
        kode_rujukanya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kode_rujukanyaKeyPressed(evt);
            }
        });

        nmfaskes_keluar.setForeground(new java.awt.Color(0, 0, 0));
        nmfaskes_keluar.setHighlighter(null);
        nmfaskes_keluar.setName("nmfaskes_keluar"); // NOI18N
        nmfaskes_keluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmfaskes_keluarKeyPressed(evt);
            }
        });

        KdPPK.setEditable(false);
        KdPPK.setBackground(new java.awt.Color(245, 250, 240));
        KdPPK.setForeground(new java.awt.Color(0, 0, 0));
        KdPPK.setName("KdPPK"); // NOI18N

        NmPPK.setEditable(false);
        NmPPK.setBackground(new java.awt.Color(245, 250, 240));
        NmPPK.setForeground(new java.awt.Color(0, 0, 0));
        NmPPK.setHighlighter(null);
        NmPPK.setName("NmPPK"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Bridging SEP VClaim BPJS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.setPreferredSize(new java.awt.Dimension(140, 1100));
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame11.setBorder(null);
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 900));

        FormInput1.setBackground(new java.awt.Color(255, 255, 255));
        FormInput1.setBorder(null);
        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(100, 440));
        FormInput1.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput1.add(jLabel4);
        jLabel4.setBounds(0, 12, 90, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput1.add(TNoRw);
        TNoRw.setBounds(93, 12, 130, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput1.add(TPasien);
        TPasien.setBounds(299, 12, 400, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNoRMActionPerformed(evt);
            }
        });
        FormInput1.add(TNoRM);
        TNoRM.setBounds(226, 12, 70, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("No.Kartu :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput1.add(jLabel5);
        jLabel5.setBounds(0, 72, 90, 23);

        NoKartu.setEditable(false);
        NoKartu.setBackground(new java.awt.Color(245, 250, 240));
        NoKartu.setForeground(new java.awt.Color(0, 0, 0));
        NoKartu.setComponentPopupMenu(Popup1);
        NoKartu.setName("NoKartu"); // NOI18N
        FormInput1.add(NoKartu);
        NoKartu.setBounds(93, 72, 120, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Tgl.SEP :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput1.add(jLabel20);
        jLabel20.setBounds(183, 102, 53, 23);

        TanggalSEP.setEditable(false);
        TanggalSEP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-08-2023" }));
        TanggalSEP.setDisplayFormat("dd-MM-yyyy");
        TanggalSEP.setName("TanggalSEP"); // NOI18N
        TanggalSEP.setOpaque(false);
        TanggalSEP.setPreferredSize(new java.awt.Dimension(95, 23));
        TanggalSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalSEPKeyPressed(evt);
            }
        });
        FormInput1.add(TanggalSEP);
        TanggalSEP.setBounds(242, 102, 90, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Tgl.Rujuk :");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput1.add(jLabel22);
        jLabel22.setBounds(0, 102, 90, 23);

        TanggalRujuk.setEditable(false);
        TanggalRujuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-08-2023" }));
        TanggalRujuk.setDisplayFormat("dd-MM-yyyy");
        TanggalRujuk.setName("TanggalRujuk"); // NOI18N
        TanggalRujuk.setOpaque(false);
        TanggalRujuk.setPreferredSize(new java.awt.Dimension(95, 23));
        TanggalRujuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TanggalRujukMouseClicked(evt);
            }
        });
        TanggalRujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalRujukKeyPressed(evt);
            }
        });
        FormInput1.add(TanggalRujuk);
        TanggalRujuk.setBounds(93, 102, 90, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("No.Rujukan :");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput1.add(jLabel23);
        jLabel23.setBounds(400, 72, 100, 23);

        NoRujukan.setForeground(new java.awt.Color(0, 0, 0));
        NoRujukan.setName("NoRujukan"); // NOI18N
        NoRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRujukanKeyPressed(evt);
            }
        });
        FormInput1.add(NoRujukan);
        NoRujukan.setBounds(503, 72, 195, 23);

        label_surat.setForeground(new java.awt.Color(255, 0, 0));
        label_surat.setText("No. Surat Kontrol :");
        label_surat.setName("label_surat"); // NOI18N
        FormInput1.add(label_surat);
        label_surat.setBounds(400, 102, 100, 23);

        btnPPKRujukan.setForeground(new java.awt.Color(0, 0, 0));
        btnPPKRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPPKRujukan.setMnemonic('X');
        btnPPKRujukan.setToolTipText("Alt+X");
        btnPPKRujukan.setName("btnPPKRujukan"); // NOI18N
        btnPPKRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPKRujukanActionPerformed(evt);
            }
        });
        btnPPKRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPPKRujukanKeyPressed(evt);
            }
        });
        FormInput1.add(btnPPKRujukan);
        btnPPKRujukan.setBounds(352, 162, 28, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("PPK Rujukan :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput1.add(jLabel10);
        jLabel10.setBounds(0, 162, 90, 23);

        KdPpkRujukan.setEditable(false);
        KdPpkRujukan.setBackground(new java.awt.Color(245, 250, 240));
        KdPpkRujukan.setForeground(new java.awt.Color(0, 0, 0));
        KdPpkRujukan.setName("KdPpkRujukan"); // NOI18N
        FormInput1.add(KdPpkRujukan);
        KdPpkRujukan.setBounds(93, 162, 75, 23);

        NmPpkRujukan.setEditable(false);
        NmPpkRujukan.setBackground(new java.awt.Color(245, 250, 240));
        NmPpkRujukan.setForeground(new java.awt.Color(0, 0, 0));
        NmPpkRujukan.setName("NmPpkRujukan"); // NOI18N
        FormInput1.add(NmPpkRujukan);
        NmPpkRujukan.setBounds(170, 162, 180, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Diagnosa Awal :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput1.add(jLabel11);
        jLabel11.setBounds(0, 192, 90, 23);

        NmPenyakit.setEditable(false);
        NmPenyakit.setBackground(new java.awt.Color(245, 250, 240));
        NmPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        NmPenyakit.setName("NmPenyakit"); // NOI18N
        FormInput1.add(NmPenyakit);
        NmPenyakit.setBounds(170, 192, 180, 23);

        btnDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiagnosa.setMnemonic('X');
        btnDiagnosa.setToolTipText("Alt+X");
        btnDiagnosa.setName("btnDiagnosa"); // NOI18N
        btnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosaActionPerformed(evt);
            }
        });
        btnDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDiagnosaKeyPressed(evt);
            }
        });
        FormInput1.add(btnDiagnosa);
        btnDiagnosa.setBounds(352, 192, 28, 23);

        btnPoli.setForeground(new java.awt.Color(0, 0, 0));
        btnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPoli.setMnemonic('X');
        btnPoli.setToolTipText("Alt+X");
        btnPoli.setName("btnPoli"); // NOI18N
        btnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPoliActionPerformed(evt);
            }
        });
        btnPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPoliKeyPressed(evt);
            }
        });
        FormInput1.add(btnPoli);
        btnPoli.setBounds(352, 222, 28, 23);

        NmPoli.setEditable(false);
        NmPoli.setBackground(new java.awt.Color(245, 250, 240));
        NmPoli.setForeground(new java.awt.Color(0, 0, 0));
        NmPoli.setName("NmPoli"); // NOI18N
        FormInput1.add(NmPoli);
        NmPoli.setBounds(170, 222, 180, 23);

        KdPoli.setEditable(false);
        KdPoli.setBackground(new java.awt.Color(245, 250, 240));
        KdPoli.setForeground(new java.awt.Color(0, 0, 0));
        KdPoli.setName("KdPoli"); // NOI18N
        FormInput1.add(KdPoli);
        KdPoli.setBounds(93, 222, 75, 23);

        LabelKatarak.setForeground(new java.awt.Color(0, 0, 0));
        LabelKatarak.setText("Kasus Katarak :");
        LabelKatarak.setName("LabelKatarak"); // NOI18N
        FormInput1.add(LabelKatarak);
        LabelKatarak.setBounds(545, 192, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jns.Pelayanan :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput1.add(jLabel13);
        jLabel13.setBounds(380, 162, 85, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Catatan :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput1.add(jLabel14);
        jLabel14.setBounds(0, 372, 90, 23);

        JenisPelayanan.setForeground(new java.awt.Color(0, 0, 0));
        JenisPelayanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Ranap", "2. Ralan" }));
        JenisPelayanan.setEnabled(false);
        JenisPelayanan.setName("JenisPelayanan"); // NOI18N
        JenisPelayanan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JenisPelayananItemStateChanged(evt);
            }
        });
        JenisPelayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JenisPelayananKeyReleased(evt);
            }
        });
        FormInput1.add(JenisPelayanan);
        JenisPelayanan.setBounds(470, 162, 75, 23);

        hakKelas.setForeground(new java.awt.Color(0, 0, 0));
        hakKelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Kelas 1", "2. Kelas 2", "3. Kelas 3" }));
        hakKelas.setName("hakKelas"); // NOI18N
        hakKelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hakKelasKeyPressed(evt);
            }
        });
        FormInput1.add(hakKelas);
        hakKelas.setBounds(470, 222, 75, 23);

        LabTglkll.setForeground(new java.awt.Color(0, 0, 0));
        LabTglkll.setText("Tgl. Kejadian :");
        LabTglkll.setName("LabTglkll"); // NOI18N
        FormInput1.add(LabTglkll);
        LabTglkll.setBounds(806, 42, 93, 23);

        LakaLantas.setForeground(new java.awt.Color(0, 0, 0));
        LakaLantas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Bukan Kecelakaan lalu lintas [BKLL]", "1. KLL dan Bukan Kecelakaan Kerja [BKK]", "2. KLL dan KK", "3. KK" }));
        LakaLantas.setName("LakaLantas"); // NOI18N
        LakaLantas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LakaLantasMouseClicked(evt);
            }
        });
        LakaLantas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LakaLantasKeyPressed(evt);
            }
        });
        FormInput1.add(LakaLantas);
        LakaLantas.setBounds(904, 12, 230, 23);

        LabNoSup.setForeground(new java.awt.Color(0, 0, 0));
        LabNoSup.setText("No. SEP Suplesi :");
        LabNoSup.setName("LabNoSup"); // NOI18N
        FormInput1.add(LabNoSup);
        LabNoSup.setBounds(806, 222, 93, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput1.add(jLabel8);
        jLabel8.setBounds(0, 42, 90, 23);

        TglLahir.setEditable(false);
        TglLahir.setBackground(new java.awt.Color(245, 250, 240));
        TglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput1.add(TglLahir);
        TglLahir.setBounds(93, 42, 100, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Jns. Kelamin : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput1.add(jLabel18);
        jLabel18.setBounds(420, 42, 80, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Peserta :");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput1.add(jLabel24);
        jLabel24.setBounds(213, 42, 50, 23);

        JenisPeserta.setEditable(false);
        JenisPeserta.setBackground(new java.awt.Color(245, 250, 240));
        JenisPeserta.setForeground(new java.awt.Color(0, 0, 0));
        JenisPeserta.setName("JenisPeserta"); // NOI18N
        FormInput1.add(JenisPeserta);
        JenisPeserta.setBounds(268, 42, 150, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Status :");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput1.add(jLabel25);
        jLabel25.setBounds(213, 72, 50, 23);

        Status.setEditable(false);
        Status.setBackground(new java.awt.Color(245, 250, 240));
        Status.setForeground(new java.awt.Color(0, 0, 0));
        Status.setHighlighter(null);
        Status.setName("Status"); // NOI18N
        FormInput1.add(Status);
        Status.setBounds(268, 72, 100, 23);

        rujukanSEP.setForeground(new java.awt.Color(0, 0, 0));
        rujukanSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rujukanSEP.setText("- belum terisi -");
        rujukanSEP.setName("rujukanSEP"); // NOI18N
        FormInput1.add(rujukanSEP);
        rujukanSEP.setBounds(203, 132, 250, 23);

        AsalRujukan.setForeground(new java.awt.Color(0, 0, 0));
        AsalRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Faskes 1", "2. Faskes 2(RS)" }));
        AsalRujukan.setName("AsalRujukan"); // NOI18N
        AsalRujukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AsalRujukanMouseClicked(evt);
            }
        });
        AsalRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AsalRujukanActionPerformed(evt);
            }
        });
        AsalRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AsalRujukanKeyPressed(evt);
            }
        });
        FormInput1.add(AsalRujukan);
        AsalRujukan.setBounds(93, 132, 106, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Eksekutif :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput1.add(jLabel15);
        jLabel15.setBounds(395, 192, 70, 23);

        Eksekutif.setForeground(new java.awt.Color(0, 0, 0));
        Eksekutif.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Tidak", "1.Ya" }));
        Eksekutif.setName("Eksekutif"); // NOI18N
        FormInput1.add(Eksekutif);
        Eksekutif.setBounds(470, 192, 75, 23);

        LabelKelas1.setForeground(new java.awt.Color(0, 0, 0));
        LabelKelas1.setText("COB :");
        LabelKelas1.setName("LabelKelas1"); // NOI18N
        FormInput1.add(LabelKelas1);
        LabelKelas1.setBounds(545, 162, 90, 23);

        COB.setForeground(new java.awt.Color(0, 0, 0));
        COB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Tidak ", "1.Ya" }));
        COB.setName("COB"); // NOI18N
        COB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                COBMouseClicked(evt);
            }
        });
        COB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                COBKeyPressed(evt);
            }
        });
        FormInput1.add(COB);
        COB.setBounds(640, 162, 75, 23);

        LabKetkll.setForeground(new java.awt.Color(0, 0, 0));
        LabKetkll.setText("Keterangan :");
        LabKetkll.setName("LabKetkll"); // NOI18N
        FormInput1.add(LabKetkll);
        LabKetkll.setBounds(806, 72, 93, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("No.Telp :");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput1.add(jLabel29);
        jLabel29.setBounds(463, 342, 55, 23);

        NoTelp.setForeground(new java.awt.Color(0, 0, 0));
        NoTelp.setMaxLenth(13);
        NoTelp.setName("NoTelp"); // NOI18N
        FormInput1.add(NoTelp);
        NoTelp.setBounds(523, 342, 170, 23);

        jLabel36.setForeground(new java.awt.Color(255, 0, 0));
        jLabel36.setText("Laka Lantas :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput1.add(jLabel36);
        jLabel36.setBounds(806, 12, 93, 23);

        TanggalKejadian.setEditable(false);
        TanggalKejadian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-08-2023" }));
        TanggalKejadian.setDisplayFormat("dd-MM-yyyy");
        TanggalKejadian.setName("TanggalKejadian"); // NOI18N
        TanggalKejadian.setOpaque(false);
        TanggalKejadian.setPreferredSize(new java.awt.Dimension(95, 23));
        FormInput1.add(TanggalKejadian);
        TanggalKejadian.setBounds(904, 42, 90, 23);

        Ket.setForeground(new java.awt.Color(0, 0, 0));
        Ket.setHighlighter(null);
        Ket.setName("Ket"); // NOI18N
        Ket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetKeyPressed(evt);
            }
        });
        FormInput1.add(Ket);
        Ket.setBounds(904, 72, 270, 23);

        LabProv.setForeground(new java.awt.Color(0, 0, 0));
        LabProv.setText("Provinsi :");
        LabProv.setName("LabProv"); // NOI18N
        FormInput1.add(LabProv);
        LabProv.setBounds(806, 102, 93, 23);

        LabKab.setForeground(new java.awt.Color(0, 0, 0));
        LabKab.setText("Kabupaten :");
        LabKab.setName("LabKab"); // NOI18N
        FormInput1.add(LabKab);
        LabKab.setBounds(806, 132, 93, 23);

        KdProv.setEditable(false);
        KdProv.setBackground(new java.awt.Color(245, 250, 240));
        KdProv.setForeground(new java.awt.Color(0, 0, 0));
        KdProv.setHighlighter(null);
        KdProv.setName("KdProv"); // NOI18N
        FormInput1.add(KdProv);
        KdProv.setBounds(904, 102, 60, 23);

        KdKab.setEditable(false);
        KdKab.setBackground(new java.awt.Color(245, 250, 240));
        KdKab.setForeground(new java.awt.Color(0, 0, 0));
        KdKab.setHighlighter(null);
        KdKab.setName("KdKab"); // NOI18N
        FormInput1.add(KdKab);
        KdKab.setBounds(904, 132, 60, 23);

        KdKec.setEditable(false);
        KdKec.setBackground(new java.awt.Color(245, 250, 240));
        KdKec.setForeground(new java.awt.Color(0, 0, 0));
        KdKec.setHighlighter(null);
        KdKec.setName("KdKec"); // NOI18N
        FormInput1.add(KdKec);
        KdKec.setBounds(904, 162, 60, 23);

        NmProv.setEditable(false);
        NmProv.setBackground(new java.awt.Color(245, 250, 240));
        NmProv.setForeground(new java.awt.Color(0, 0, 0));
        NmProv.setHighlighter(null);
        NmProv.setName("NmProv"); // NOI18N
        FormInput1.add(NmProv);
        NmProv.setBounds(967, 102, 170, 23);

        NmKab.setEditable(false);
        NmKab.setBackground(new java.awt.Color(245, 250, 240));
        NmKab.setForeground(new java.awt.Color(0, 0, 0));
        NmKab.setHighlighter(null);
        NmKab.setName("NmKab"); // NOI18N
        FormInput1.add(NmKab);
        NmKab.setBounds(967, 132, 170, 23);

        NmKec.setEditable(false);
        NmKec.setBackground(new java.awt.Color(245, 250, 240));
        NmKec.setForeground(new java.awt.Color(0, 0, 0));
        NmKec.setHighlighter(null);
        NmKec.setName("NmKec"); // NOI18N
        FormInput1.add(NmKec);
        NmKec.setBounds(967, 162, 170, 23);

        btnProv.setForeground(new java.awt.Color(0, 0, 0));
        btnProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnProv.setMnemonic('X');
        btnProv.setToolTipText("Alt+X");
        btnProv.setName("btnProv"); // NOI18N
        btnProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProvActionPerformed(evt);
            }
        });
        btnProv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnProvKeyPressed(evt);
            }
        });
        FormInput1.add(btnProv);
        btnProv.setBounds(1138, 102, 28, 23);

        btnKab.setForeground(new java.awt.Color(0, 0, 0));
        btnKab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKab.setMnemonic('X');
        btnKab.setToolTipText("Alt+X");
        btnKab.setName("btnKab"); // NOI18N
        btnKab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKabActionPerformed(evt);
            }
        });
        btnKab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnKabKeyPressed(evt);
            }
        });
        FormInput1.add(btnKab);
        btnKab.setBounds(1138, 132, 28, 23);

        btnKec.setForeground(new java.awt.Color(0, 0, 0));
        btnKec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKec.setMnemonic('X');
        btnKec.setToolTipText("Alt+X");
        btnKec.setName("btnKec"); // NOI18N
        btnKec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKecActionPerformed(evt);
            }
        });
        btnKec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnKecKeyPressed(evt);
            }
        });
        FormInput1.add(btnKec);
        btnKec.setBounds(1138, 162, 28, 23);

        LabKec.setForeground(new java.awt.Color(0, 0, 0));
        LabKec.setText("Kecamatan :");
        LabKec.setName("LabKec"); // NOI18N
        FormInput1.add(LabKec);
        LabKec.setBounds(806, 162, 93, 23);

        suplesi.setForeground(new java.awt.Color(0, 0, 0));
        suplesi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Tidak", "1. Ya" }));
        suplesi.setName("suplesi"); // NOI18N
        suplesi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suplesiMouseClicked(evt);
            }
        });
        suplesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                suplesiKeyPressed(evt);
            }
        });
        FormInput1.add(suplesi);
        suplesi.setBounds(904, 192, 70, 23);

        LabSup.setForeground(new java.awt.Color(0, 0, 0));
        LabSup.setText("Suplesi :");
        LabSup.setName("LabSup"); // NOI18N
        FormInput1.add(LabSup);
        LabSup.setBounds(806, 192, 93, 23);

        NoSEPSuplesi.setForeground(new java.awt.Color(0, 0, 0));
        NoSEPSuplesi.setHighlighter(null);
        NoSEPSuplesi.setName("NoSEPSuplesi"); // NOI18N
        NoSEPSuplesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSEPSuplesiKeyPressed(evt);
            }
        });
        FormInput1.add(NoSEPSuplesi);
        NoSEPSuplesi.setBounds(904, 222, 270, 23);

        LabelPoli.setForeground(new java.awt.Color(0, 0, 0));
        LabelPoli.setText("Poli Tujuan :");
        LabelPoli.setName("LabelPoli"); // NOI18N
        FormInput1.add(LabelPoli);
        LabelPoli.setBounds(0, 222, 90, 23);

        KasusKatarak.setForeground(new java.awt.Color(0, 0, 0));
        KasusKatarak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Tidak", "1. Ya" }));
        KasusKatarak.setName("KasusKatarak"); // NOI18N
        KasusKatarak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KasusKatarakMouseClicked(evt);
            }
        });
        KasusKatarak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KasusKatarakKeyPressed(evt);
            }
        });
        FormInput1.add(KasusKatarak);
        KasusKatarak.setBounds(640, 192, 75, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("DPJP :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput1.add(jLabel42);
        jLabel42.setBounds(460, 132, 40, 23);

        noSurat.setForeground(new java.awt.Color(0, 0, 0));
        noSurat.setName("noSurat"); // NOI18N
        noSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noSuratKeyPressed(evt);
            }
        });
        FormInput1.add(noSurat);
        noSurat.setBounds(503, 102, 195, 23);

        NmDPJP.setEditable(false);
        NmDPJP.setBackground(new java.awt.Color(245, 250, 240));
        NmDPJP.setForeground(new java.awt.Color(0, 0, 0));
        NmDPJP.setName("NmDPJP"); // NOI18N
        FormInput1.add(NmDPJP);
        NmDPJP.setBounds(568, 132, 130, 23);

        btnDPJP.setForeground(new java.awt.Color(0, 0, 0));
        btnDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDPJP.setMnemonic('X');
        btnDPJP.setToolTipText("Alt+X");
        btnDPJP.setName("btnDPJP"); // NOI18N
        btnDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDPJPActionPerformed(evt);
            }
        });
        FormInput1.add(btnDPJP);
        btnDPJP.setBounds(700, 132, 28, 23);

        btnNoSurat.setForeground(new java.awt.Color(0, 0, 0));
        btnNoSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnNoSurat.setMnemonic('X');
        btnNoSurat.setToolTipText("Alt+X");
        btnNoSurat.setName("btnNoSurat"); // NOI18N
        btnNoSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoSuratActionPerformed(evt);
            }
        });
        FormInput1.add(btnNoSurat);
        btnNoSurat.setBounds(700, 102, 28, 23);

        btnNoRujukan.setForeground(new java.awt.Color(0, 0, 0));
        btnNoRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnNoRujukan.setMnemonic('X');
        btnNoRujukan.setToolTipText("Alt+X");
        btnNoRujukan.setName("btnNoRujukan"); // NOI18N
        btnNoRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoRujukanActionPerformed(evt);
            }
        });
        FormInput1.add(btnNoRujukan);
        btnNoRujukan.setBounds(700, 72, 28, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Asal Rujukan :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput1.add(jLabel28);
        jLabel28.setBounds(0, 132, 90, 23);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        Catatan.setColumns(20);
        Catatan.setRows(5);
        Catatan.setName("Catatan"); // NOI18N
        Catatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CatatanKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(Catatan);

        FormInput1.add(Scroll3);
        Scroll3.setBounds(93, 372, 600, 50);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Hak Kls. Rawat :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput1.add(jLabel16);
        jLabel16.setBounds(380, 222, 85, 23);

        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("Naik Kls. Rawat :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput1.add(jLabel17);
        jLabel17.setBounds(380, 252, 85, 23);

        jLabel27.setForeground(new java.awt.Color(255, 0, 0));
        jLabel27.setText("Pembiayaan :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput1.add(jLabel27);
        jLabel27.setBounds(204, 282, 80, 23);

        jLabel37.setForeground(new java.awt.Color(255, 0, 0));
        jLabel37.setText("Pngg. Jawab :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput1.add(jLabel37);
        jLabel37.setBounds(480, 282, 80, 23);

        pngJwb.setEditable(false);
        pngJwb.setForeground(new java.awt.Color(0, 0, 0));
        pngJwb.setMaxLenth(13);
        pngJwb.setName("pngJwb"); // NOI18N
        pngJwb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pngJwbKeyPressed(evt);
            }
        });
        FormInput1.add(pngJwb);
        pngJwb.setBounds(565, 282, 180, 23);

        jLabel39.setForeground(new java.awt.Color(255, 0, 0));
        jLabel39.setText("Flag Prosedur :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput1.add(jLabel39);
        jLabel39.setBounds(0, 312, 90, 23);

        jLabel40.setForeground(new java.awt.Color(255, 0, 0));
        jLabel40.setText("Asses. Pelyn. :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput1.add(jLabel40);
        jLabel40.setBounds(0, 342, 90, 23);

        jLabel41.setForeground(new java.awt.Color(255, 0, 0));
        jLabel41.setText("Tujuan Kunj. :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput1.add(jLabel41);
        jLabel41.setBounds(0, 282, 90, 23);

        tujuanKun.setForeground(new java.awt.Color(0, 0, 0));
        tujuanKun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Normal", "1. Prosedur", "2. Konsul Dokter" }));
        tujuanKun.setName("tujuanKun"); // NOI18N
        FormInput1.add(tujuanKun);
        tujuanKun.setBounds(93, 282, 110, 23);

        flagPro.setForeground(new java.awt.Color(0, 0, 0));
        flagPro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "0. Prosedur Tidak Berkelanjutan", "1. Prosedur dan Terapi Berkelanjutan" }));
        flagPro.setName("flagPro"); // NOI18N
        FormInput1.add(flagPro);
        flagPro.setBounds(93, 312, 210, 23);

        kdPenunjang.setForeground(new java.awt.Color(0, 0, 0));
        kdPenunjang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. Radioterapi", "2. Kemoterapi", "3. Rehabilitasi Medik", "4. Rehabilitasi Psikososial", "5. Transfusi Darah", "6. Pelayanan Gigi", "7. Laboratorium", "8. USG", "9. Farmasi", "10. Lain-Lain", "11. MRI", "12. HEMODIALISA" }));
        kdPenunjang.setName("kdPenunjang"); // NOI18N
        FormInput1.add(kdPenunjang);
        kdPenunjang.setBounds(404, 312, 150, 23);

        asesmenPel.setForeground(new java.awt.Color(0, 0, 0));
        asesmenPel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. Poli spesialis tidak tersedia pada hari sebelumnya", "2. Jam Poli telah berakhir pada hari sebelumnya", "3. Dokter Spesialis yang dimaksud tidak praktek pada hari sebelumnya", "4. Atas Instruksi RS", "5. Tujuan Kontrol" }));
        asesmenPel.setName("asesmenPel"); // NOI18N
        FormInput1.add(asesmenPel);
        asesmenPel.setBounds(93, 342, 370, 23);

        jLabel45.setForeground(new java.awt.Color(255, 0, 0));
        jLabel45.setText("Kode Penunjang :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput1.add(jLabel45);
        jLabel45.setBounds(303, 312, 95, 23);

        btnDPJPlayan.setForeground(new java.awt.Color(0, 0, 0));
        btnDPJPlayan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDPJPlayan.setMnemonic('X');
        btnDPJPlayan.setToolTipText("Alt+X");
        btnDPJPlayan.setName("btnDPJPlayan"); // NOI18N
        btnDPJPlayan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDPJPlayanActionPerformed(evt);
            }
        });
        FormInput1.add(btnDPJPlayan);
        btnDPJPlayan.setBounds(352, 252, 28, 23);

        cmbPembiayaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPembiayaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. Pribadi", "2. Pemberi Kerja", "3. Asuransi Kesehatan Tambahan" }));
        cmbPembiayaan.setName("cmbPembiayaan"); // NOI18N
        cmbPembiayaan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPembiayaanItemStateChanged(evt);
            }
        });
        cmbPembiayaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbPembiayaanKeyReleased(evt);
            }
        });
        FormInput1.add(cmbPembiayaan);
        cmbPembiayaan.setBounds(290, 282, 190, 23);

        jLabel46.setForeground(new java.awt.Color(255, 0, 0));
        jLabel46.setText("DPJP Layan :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput1.add(jLabel46);
        jLabel46.setBounds(0, 252, 90, 23);

        nmdpjpLayan.setEditable(false);
        nmdpjpLayan.setBackground(new java.awt.Color(245, 250, 240));
        nmdpjpLayan.setForeground(new java.awt.Color(0, 0, 0));
        nmdpjpLayan.setName("nmdpjpLayan"); // NOI18N
        FormInput1.add(nmdpjpLayan);
        nmdpjpLayan.setBounds(170, 252, 180, 23);

        JK.setEditable(false);
        JK.setForeground(new java.awt.Color(0, 0, 0));
        JK.setName("JK"); // NOI18N
        FormInput1.add(JK);
        JK.setBounds(503, 42, 195, 23);

        btnNoRujukanInap.setForeground(new java.awt.Color(0, 0, 0));
        btnNoRujukanInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        btnNoRujukanInap.setToolTipText("Cek No. SEP Rawat Inap terakhir");
        btnNoRujukanInap.setName("btnNoRujukanInap"); // NOI18N
        btnNoRujukanInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoRujukanInapActionPerformed(evt);
            }
        });
        FormInput1.add(btnNoRujukanInap);
        btnNoRujukanInap.setBounds(733, 72, 32, 23);

        kdNaikKls.setEditable(false);
        kdNaikKls.setForeground(new java.awt.Color(0, 0, 0));
        kdNaikKls.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        kdNaikKls.setMaxLenth(13);
        kdNaikKls.setName("kdNaikKls"); // NOI18N
        kdNaikKls.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdNaikKlsKeyPressed(evt);
            }
        });
        FormInput1.add(kdNaikKls);
        kdNaikKls.setBounds(470, 252, 33, 23);

        nmKlsNaik.setEditable(false);
        nmKlsNaik.setForeground(new java.awt.Color(0, 0, 0));
        nmKlsNaik.setMaxLenth(13);
        nmKlsNaik.setName("nmKlsNaik"); // NOI18N
        nmKlsNaik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmKlsNaikKeyPressed(evt);
            }
        });
        FormInput1.add(nmKlsNaik);
        nmKlsNaik.setBounds(508, 252, 80, 23);

        btnNaikKls.setForeground(new java.awt.Color(0, 0, 0));
        btnNaikKls.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnNaikKls.setMnemonic('X');
        btnNaikKls.setToolTipText("Alt+X");
        btnNaikKls.setName("btnNaikKls"); // NOI18N
        btnNaikKls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNaikKlsActionPerformed(evt);
            }
        });
        FormInput1.add(btnNaikKls);
        btnNaikKls.setBounds(590, 252, 28, 23);

        Kddpjp.setEditable(false);
        Kddpjp.setBackground(new java.awt.Color(245, 250, 240));
        Kddpjp.setForeground(new java.awt.Color(0, 0, 0));
        Kddpjp.setName("Kddpjp"); // NOI18N
        FormInput1.add(Kddpjp);
        Kddpjp.setBounds(503, 132, 62, 23);

        KdPenyakit.setEditable(false);
        KdPenyakit.setBackground(new java.awt.Color(245, 250, 240));
        KdPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        KdPenyakit.setName("KdPenyakit"); // NOI18N
        FormInput1.add(KdPenyakit);
        KdPenyakit.setBounds(93, 192, 75, 23);

        KddpjpLayan.setEditable(false);
        KddpjpLayan.setForeground(new java.awt.Color(0, 0, 0));
        KddpjpLayan.setName("KddpjpLayan"); // NOI18N
        FormInput1.add(KddpjpLayan);
        KddpjpLayan.setBounds(93, 252, 75, 23);

        scrollInput.setViewportView(FormInput1);

        internalFrame11.add(scrollInput, java.awt.BorderLayout.CENTER);

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(44, 51));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelGlass10.add(BtnSimpan);

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
        panelGlass10.add(BtnBatal);

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
        panelGlass10.add(BtnHapus);

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
        panelGlass10.add(BtnEdit);

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
        panelGlass10.add(BtnKeluar);

        jPanel4.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame11.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Input Data", internalFrame11);

        internalFrame12.setBorder(null);
        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput1.setName("scrollInput1"); // NOI18N
        scrollInput1.setPreferredSize(new java.awt.Dimension(102, 1200));

        tbSEP.setAutoCreateRowSorter(true);
        tbSEP.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbSEP.setComponentPopupMenu(Popup);
        tbSEP.setName("tbSEP"); // NOI18N
        tbSEP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSEPMouseClicked(evt);
            }
        });
        tbSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSEPKeyPressed(evt);
            }
        });
        scrollInput1.setViewportView(tbSEP);

        internalFrame12.add(scrollInput1, java.awt.BorderLayout.CENTER);

        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(44, 51));
        jPanel5.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. SEP :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass11.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-08-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DTPCari1MouseClicked(evt);
            }
        });
        panelGlass11.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass11.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-08-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DTPCari2MouseClicked(evt);
            }
        });
        panelGlass11.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass11.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+3");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(140, 23));
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
        panelGlass11.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 30));
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
        panelGlass11.add(BtnAll);

        BtnKeluar2.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setMnemonic('K');
        BtnKeluar2.setText("Keluar");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass11.add(BtnKeluar2);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass11.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass11.add(LCount);

        jPanel5.add(panelGlass11, java.awt.BorderLayout.CENTER);

        internalFrame12.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data SEP BPJS Pasien", internalFrame12);

        internalFrame13.setBorder(null);
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput2.setName("scrollInput2"); // NOI18N
        scrollInput2.setPreferredSize(new java.awt.Dimension(102, 1200));

        tbRiwayat.setToolTipText("");
        tbRiwayat.setComponentPopupMenu(Popup2);
        tbRiwayat.setName("tbRiwayat"); // NOI18N
        tbRiwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatMouseClicked(evt);
            }
        });
        tbRiwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatKeyPressed(evt);
            }
        });
        scrollInput2.setViewportView(tbRiwayat);

        internalFrame13.add(scrollInput2, java.awt.BorderLayout.CENTER);

        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(44, 51));
        jPanel6.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Record :");
        jLabel47.setName("jLabel47"); // NOI18N
        jLabel47.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass12.add(jLabel47);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass12.add(LCount1);

        BtnKeluar3.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar3.setMnemonic('K');
        BtnKeluar3.setText("Keluar");
        BtnKeluar3.setToolTipText("Alt+K");
        BtnKeluar3.setName("BtnKeluar3"); // NOI18N
        BtnKeluar3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar3ActionPerformed(evt);
            }
        });
        BtnKeluar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar3KeyPressed(evt);
            }
        });
        panelGlass12.add(BtnKeluar3);

        jPanel6.add(panelGlass12, java.awt.BorderLayout.CENTER);

        internalFrame13.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("10 Riwayat Kunjungan Terakhir", internalFrame13);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoKartu, "Nomor Kartu");
        } else if (NoRujukan.getText().trim().equals("") && !KdPoli.getText().equals("IGD")) {
            Valid.textKosong(NoRujukan, "Nomor Rujukan");
        } else if (KdPpkRujukan.getText().trim().equals("") || NmPpkRujukan.getText().trim().equals("") || rujukanSEP.getText().equals("- belum terisi -")) {
            Valid.textKosong(KdPpkRujukan, "PPK Rujukan");
        } else if (KdPPK.getText().trim().equals("") || NmPPK.getText().trim().equals("")) {
            Valid.textKosong(KdPPK, "PPK Pelayanan");
        } else if (KdPenyakit.getText().trim().equals("") || NmPenyakit.getText().trim().equals("")) {
            Valid.textKosong(KdPenyakit, "Diagnosa");
        } else if (Catatan.getText().trim().equals("")) {
            Valid.textKosong(Catatan, "Catatan");
        } else if ((JenisPelayanan.getSelectedIndex() == 1) && (KdPoli.getText().trim().equals("") || NmPoli.getText().trim().equals(""))) {
            Valid.textKosong(KdPoli, "Poli Tujuan");
        } else {
            insertSEP();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,LokasiLaka,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

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
        if (tbSEP.getSelectedRow() != -1) {
            try {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (!Sequel.cariIsi("select urutan_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "'").equals("1")) {                        
                        Sequel.queryu("delete from bridging_sep where no_rawat='" + TNoRw.getText() + "' and no_sep='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "'");
                        tampil();
                        emptTeks();
                    } else {
                        bodyWithDeleteRequest();
                    }
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu data yang mau dihapus..!!");
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
        if (tbSEP.getSelectedRow() != -1) {
            if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                Valid.textKosong(TNoRw, "Pasien");
            } else if (NoKartu.getText().trim().equals("")) {
                Valid.textKosong(NoKartu, "Nomor Kartu");
            } else if (NoRujukan.getText().trim().equals("") && !KdPoli.getText().equals("IGD")) {
                Valid.textKosong(NoRujukan, "Nomor Rujukan");
            } else if (KdPpkRujukan.getText().trim().equals("") || NmPpkRujukan.getText().trim().equals("")) {
                Valid.textKosong(KdPpkRujukan, "PPK Rujukan");
            } else if (KdPPK.getText().trim().equals("") || NmPPK.getText().trim().equals("")) {
                Valid.textKosong(KdPPK, "PPK Pelayanan");
            } else if (KdPenyakit.getText().trim().equals("") || NmPenyakit.getText().trim().equals("")) {
                Valid.textKosong(KdPenyakit, "Diagnosa");
            } else if (Catatan.getText().trim().equals("")) {
                Valid.textKosong(Catatan, "Catatan");
            } else {
                try {
                    pembi = "";
                    kdpnjg = "";
                    flag = "";
                    asesmen = "";                    

                    //tanggal kejadian untuk disimpan ke database
                    if (LakaLantas.getSelectedIndex() == 1 || LakaLantas.getSelectedIndex() == 2 || LakaLantas.getSelectedIndex() == 3) {
                        tglkkl = Valid.SetTgl(TanggalKejadian.getSelectedItem() + "");
                        tglkkl_ok = tglkkl;
                    } else {
                        tglkkl = "0000-00-00";
                        tglkkl_ok = "";
                    }

                    //-----------------------------------------------------------------------------------------------
//                    if (tujuanKun.getSelectedIndex() == 0) {
//                        flagPro.setSelectedIndex(0);
//                        kdPenunjang.setSelectedIndex(0);
//                    } else {
//                        flagPro.setSelectedIndex(1);
//                        kdPenunjang.setSelectedIndex(1);
//                    }
//
//                    if (tujuanKun.getSelectedIndex() == 0 || tujuanKun.getSelectedIndex() == 2) {
//                        asesmenPel.setSelectedIndex(1);
//                    } else if (tujuanKun.getSelectedIndex() == 1) {
//                        asesmenPel.setSelectedIndex(0);
//                    }
                    //-----------------------------------------------------------------------------------------------

                    if (cmbPembiayaan.getSelectedIndex() == 0) {
                        pembi = "";
                    } else {
                        pembi = cmbPembiayaan.getSelectedItem().toString().substring(0, 1);
                    }

                    if (flagPro.getSelectedIndex() == 0) {
                        flag = "";
                    } else {
                        flag = flagPro.getSelectedItem().toString().substring(0, 1);
                    }

                    if (kdPenunjang.getSelectedIndex() == 10 || kdPenunjang.getSelectedIndex() == 11 || kdPenunjang.getSelectedIndex() == 12) {
                        kdpnjg = kdPenunjang.getSelectedItem().toString().substring(0, 2);
                    } else if (kdPenunjang.getSelectedIndex() == 0) {
                        kdpnjg = "";
                    } else {
                        kdpnjg = kdPenunjang.getSelectedItem().toString().substring(0, 1);
                    }

                    if (asesmenPel.getSelectedIndex() == 0) {
                        asesmen = "";
                    } else {
                        asesmen = asesmenPel.getSelectedItem().toString().substring(0, 1);
                    }

                    if (JenisPelayanan.getSelectedIndex() == 0) {
                        KddpjpLayan.setText("");
                        nmdpjpLayan.setText("");
                    }
                    //-----------------------------------------------------------------------------------------------

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
                    utc = String.valueOf(api.GetUTCdatetimeAsString());
                    headers.add("X-Timestamp", utc);
                    headers.add("X-Signature", api.getHmac(utc));
                    headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
                    URL = prop.getProperty("URLAPIBPJS")+"/SEP/2.0/update";
                    
                    requestJson = "{"
                            + "\"request\": {"
                            + "\"t_sep\": {"
                            + "\"noSep\":\"" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "\","
//ini yang baru -----------
                            + "\"klsRawat\":{"
                            + "\"klsRawatHak\":\"" + hakKelas.getSelectedItem().toString().substring(0, 1) + "\","
                            + "\"klsRawatNaik\":\"" + kdNaikKls.getText() + "\","
                            + "\"pembiayaan\":\"" + pembi + "\","
                            + "\"penanggungJawab\":\"" + pngJwb.getText() + "\""
                            + "},"
//sampai sini ------------
                            + "\"noMR\":\"" + TNoRM.getText() + "\","
                            + "\"catatan\":\"" + Catatan.getText() + "\","
                            + "\"diagAwal\":\"" + KdPenyakit.getText() + "\","
                            + "\"poli\": {"
                            + "\"tujuan\": \"" + KdPoli.getText() + "\","
                            + "\"eksekutif\": \"" + Eksekutif.getSelectedItem().toString().substring(0, 1) + "\""
                            + "},"
                            + "\"cob\": {"
                            + "\"cob\": \"" + COB.getSelectedItem().toString().substring(0, 1) + "\""
                            + "},"
                            + "\"katarak\": {"
                            + "\"katarak\": \"" + KasusKatarak.getSelectedItem().toString().substring(0, 1) + "\""
                            + "},"
                            + "\"jaminan\": {"
                            + "\"lakaLantas\":\"" + LakaLantas.getSelectedItem().toString().substring(0, 1) + "\","
                            + "\"penjamin\": {"
                            + "\"tglKejadian\": \"" + tglkkl_ok + "\","
                            + "\"keterangan\": \"" + Ket.getText() + "\","
                            + "\"suplesi\": {"
                            + "\"suplesi\": \"" + suplesi.getSelectedItem().toString().substring(0, 1) + "\","
                            + "\"noSepSuplesi\": \"" + NoSEPSuplesi.getText() + "\","
                            + "\"lokasiLaka\": {"
                            + "\"kdPropinsi\": \"" + KdProv.getText() + "\","
                            + "\"kdKabupaten\": \"" + KdKab.getText() + "\","
                            + "\"kdKecamatan\": \"" + KdKec.getText() + "\""
                            + "}"
                            + "}"
                            + "}"
                            + "},"
//ini yang baru -----------
                            + "\"dpjpLayan\": \"" + KddpjpLayan.getText() + "\","
//sampai sini -------------                            
                            + "\"noTelp\":\"" + NoTelp.getText() + "\","
                            + "\"user\":\"" + user + "\""
                            + "}"
                            + "}"
                            + "}";
                    
//                    System.out.println("tes : " + requestJson);
                    HttpEntity requestEntity = new HttpEntity(requestJson,headers);
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody());
                    JsonNode nameNode = root.path("metaData");
                    System.out.println("code : "+nameNode.path("code").asText());
                    System.out.println("message : "+nameNode.path("message").asText());
                    JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());

                    if (nameNode.path("code").asText().equals("200")) {
                        Sequel.mengedit("bridging_sep", "no_sep='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "'",
                                "no_rawat='" + TNoRw.getText() + "', "
                                + "tglsep='" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "', "
                                + "tglrujukan='" + Valid.SetTgl(TanggalRujuk.getSelectedItem() + "") + "', "
                                + "no_rujukan='" + NoRujukan.getText() + "', "
                                + "kdppkrujukan='" + KdPpkRujukan.getText() + "', "
                                + "nmppkrujukan='" + rujukanSEP.getText() + "', "
                                + "kdppkpelayanan='" + KdPPK.getText() + "', "
                                + "nmppkpelayanan='" + NmPPK.getText() + "', "
                                + "jnspelayanan='" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "',"
                                + "catatan='" + Catatan.getText() + "', "
                                + "diagawal='" + KdPenyakit.getText() + "', "
                                + "nmdiagnosaawal='" + NmPenyakit.getText() + "', "
                                + "kdpolitujuan='" + KdPoli.getText() + "', "
                                + "nmpolitujuan='" + NmPoli.getText() + "', "
                                + "klsrawat='" + hakKelas.getSelectedItem().toString().substring(0, 1) + "', "
                                + "lakalantas='" + LakaLantas.getSelectedItem().toString().substring(0, 1) + "', "
                                + "lokasilaka='" + LokasiLaka.getText() + "', "
                                + "user='" + user + "', "
                                + "nomr='" + TNoRM.getText() + "', "
                                + "nama_pasien='" + TPasien.getText() + "', "
                                + "tanggal_lahir='" + TglLahir.getText() + "', "
                                + "peserta='" + JenisPeserta.getText() + "', "
                                + "no_kartu='" + NoKartu.getText() + "', "
                                + "asal_rujukan='" + AsalRujukan.getSelectedItem().toString() + "', "
                                + "eksekutif='" + Eksekutif.getSelectedItem().toString() + "', "
                                + "cob='" + COB.getSelectedItem().toString() + "', "
                                + "notelep='" + NoTelp.getText() + "', "
                                + "katarak='" + KasusKatarak.getSelectedItem().toString() + "', "
                                + "tglkkl='" + tglkkl + "', "
                                + "keterangankkl='" + Ket.getText() + "', "
                                + "suplesi='" + suplesi.getSelectedItem().toString() + "', "
                                + "no_sep_suplesi='" + NoSEPSuplesi.getText() + "', "
                                + "kdprop='" + KdProv.getText() + "', "
                                + "nmprop='" + NmProv.getText() + "', "
                                + "kdkab='" + KdKab.getText() + "', "
                                + "nmkab='" + NmKab.getText() + "', "
                                + "kdkec='" + KdKec.getText() + "', "
                                + "nmkec='" + NmKec.getText() + "', "
                                + "noskdp='" + noSurat.getText() + "', "
                                + "kddpjp='" + Kddpjp.getText() + "', "
                                + "nmdpdjp='" + NmDPJP.getText() + "', "
                                + "klsRawatHak='" + hakKelas.getSelectedItem().toString().substring(0, 1) + "', "
                                + "klsRawatNaik='" + kdNaikKls.getText() + "', "
                                + "pembiayaan='" + pembi + "', "
                                + "penanggungJawab='" + pngJwb.getText() + "', "
                                + "tujuanKunjungan='" + tujuanKun.getSelectedItem().toString() + "', "
                                + "flagProcedur='" + flag + "', "
                                + "kdPenunjang='" + kdpnjg + "', "
                                + "assesmentPel='" + asesmen + "', "
                                + "dpjpLayan='" + KddpjpLayan.getText() + "', "
                                + "nmdpjpLayan='" + nmdpjpLayan.getText() + "', "
                                + "nmKelasNaiknya='" + nmKlsNaik.getText() + "'");
                   
                        Sequel.mengedit("rujuk_masuk", "no_rawat='" + TNoRw.getText() + "'",
                                "no_rawat='" + TNoRw.getText() + "', "
                                + "perujuk='" + NmPpkRujukan.getText() + "',"
                                + "no_rujuk='" + NoRujukan.getText() + "', "
                                + "kd_rujukan='" + Sequel.cariIsi("select kd_rujukan from master_nama_rujukan "
                                        + "where status='1' and kode_faskes_bpjs='" + KdPpkRujukan.getText() + "'") + "'");
                        
                        emptTeks();
                        tampil();

                    } else {
                        JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi Bridging : " + ex);
                    if (ex.toString().contains("UnknownHostException")) {
                        JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu data yang mau diganti..!!");
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        WindowRujukanBiasa.dispose();
        WindowUpdatePulang.dispose();
        faskes.dispose();
        penyakit.dispose();
        poli.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

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
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
        emptTeks();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbSEPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSEPMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
//            if (evt.getClickCount() == 2) {
//                i = tbObat.getSelectedColumn();
//                if (i == 0) {
//                    ppSEPBtnPrintActionPerformed(null);
//                } else if (i == 1) {
//                    ppPulangBtnPrintActionPerformed(null);
//                } else if (i == 2) {
//                    ppDetailSEPPesertaBtnPrintActionPerformed(null);
//                } else if (i == 3) {
//                    ppRujukanBtnPrintActionPerformed(null);
//                }
//            }
        }
}//GEN-LAST:event_tbSEPMouseClicked

    private void tbSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSEPKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
//                i = tbObat.getSelectedColumn();
//                if (i == 0) {
//                    ppSEPBtnPrintActionPerformed(null);
//                } else if (i == 1) {
//                    ppPulangBtnPrintActionPerformed(null);
//                } else if (i == 2) {
//                    ppDetailSEPPesertaBtnPrintActionPerformed(null);
//                } else if (i == 3) {
//                    ppRujukanBtnPrintActionPerformed(null);
//                }
            }
        }
}//GEN-LAST:event_tbSEPKeyPressed

    private void NoRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRujukanKeyPressed
        Valid.pindah(evt, TCari, TanggalRujuk);
    }//GEN-LAST:event_NoRujukanKeyPressed

    private void btnPPKRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPKRujukanActionPerformed
        akses.setform("BPJSDataSEP");
        rujukmasuk.tampil2();
        rujukmasuk.WindowPerujuk.setSize(900, 573);
        rujukmasuk.WindowPerujuk.setLocationRelativeTo(internalFrame1);
        rujukmasuk.WindowPerujuk.setVisible(true);
        rujukmasuk.onCariPerujuk();

//        pilihan=1;
//        faskes.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
//        faskes.setLocationRelativeTo(internalFrame1);
//        faskes.setVisible(true);
//        faskes.fokus();
    }//GEN-LAST:event_btnPPKRujukanActionPerformed

    private void btnPPKRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPPKRujukanKeyPressed
        Valid.pindah(evt,AsalRujukan,btnDiagnosa);
    }//GEN-LAST:event_btnPPKRujukanKeyPressed

    private void btnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosaActionPerformed
        pilihan=1;
        penyakit.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
        penyakit.fokus();
    }//GEN-LAST:event_btnDiagnosaActionPerformed

    private void btnDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDiagnosaKeyPressed
        Valid.pindah(evt,btnPPKRujukan,btnPoli);
    }//GEN-LAST:event_btnDiagnosaKeyPressed

    private void btnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPoliActionPerformed
        pilihan=1;
        poli.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        poli.fokus();
    }//GEN-LAST:event_btnPoliActionPerformed

    private void btnPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPoliKeyPressed
        Valid.pindah(evt,btnDiagnosa,LakaLantas);
    }//GEN-LAST:event_btnPoliKeyPressed

    private void LokasiLakaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LokasiLakaKeyPressed
        Valid.pindah(evt,Catatan,BtnSimpan);
    }//GEN-LAST:event_LokasiLakaKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        if (akses.getform().equals("DlgReg")
                || akses.getform().equals("DlgIGD")
                || akses.getform().equals("DlgKamarInap")
                || akses.getform().equals("DlgBookingRegistrasi")
                || akses.getform().equals("DlgKasirRalan")) {
            no_peserta = Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis=?", TNoRM.getText());
            if (no_peserta.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Pasien tidak mempunyai kepesertaan BPJS");
                dispose();
            } else {
                cekViaBPJSKartu.tampil(no_peserta, Sequel.cariIsi("SELECT DATE(NOW())"));
                if (cekViaBPJSKartu.informasi.equals("OK")) {
                    if (cekViaBPJSKartu.statusPesertaketerangan.equals("AKTIF")) {
                        TPasien.setText(Strings.toUpperCase(cekViaBPJSKartu.nama));
                        TglLahir.setText(cekViaBPJSKartu.tglLahir);                        
                        NoKartu.setText(no_peserta);
                        JenisPeserta.setText(cekViaBPJSKartu.jenisPesertaketerangan);
                        Status.setText(cekViaBPJSKartu.statusPesertaketerangan);

                        if (cekViaBPJSKartu.sex.equals("L")) {
                            JK.setText("Laki-laki");
                        } else {
                            JK.setText("Perempuan");
                        }

                        if (AsalRujukan.getSelectedIndex() == 0) {
                            KdPpkRujukan.setText(cekViaBPJSKartu.provUmumkdProvider);
                            rujukanSEP.setText(cekViaBPJSKartu.provUmumnmProvider);
                            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
                            Sequel.cariIsi("select kd_rujukan from master_nama_rujukan where status='1' and kode_faskes_bpjs=? ", kode_rujukanya, KdPpkRujukan.getText());
//                            NmPpkRujukan.setText("");
                        } else if (AsalRujukan.getSelectedIndex() == 1) {
                            KdPpkRujukan.setText(KdPPK.getText());
//                            NmPpkRujukan.setText(NmPPK.getText());
                            rujukanSEP.setText(NmPPK.getText());
                            NmPpkRujukan.setText(NmPPK.getText());
                            Sequel.cariIsi("select kd_rujukan from master_nama_rujukan where status='1' and kode_faskes_bpjs=? ", kode_rujukanya, KdPpkRujukan.getText());
                        }

                        if (cekViaBPJSKartu.hakKelaskode.equals("1")) {
                            hakKelas.setSelectedIndex(0);
                        } else if (cekViaBPJSKartu.hakKelaskode.equals("2")) {
                            hakKelas.setSelectedIndex(1);
                        } else if (cekViaBPJSKartu.hakKelaskode.equals("3")) {
                            hakKelas.setSelectedIndex(2);
                        }
                        
                        if (cekViaBPJSKartu.mrnoTelepon.equals("null")) {
                            NoTelp.setText(Sequel.cariIsi("select ifnull(no_tlp,'0') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
                        } else {
                            NoTelp.setText(cekViaBPJSKartu.mrnoTelepon);
                        }
                        NoTelp.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(null, "Status kepesertaan tidak aktif..!!");
                        dispose();
                    }
                } else {
                    dispose();
                }
            }
        }
    }//GEN-LAST:event_formWindowOpened

    private void ppSEPBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSEPBtnPrintActionPerformed
        if (tbSEP.getSelectedRow() != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select bpjs from gambar"));
            if (JenisPelayanan.getSelectedIndex() == 0) {
                Sequel.queryu("delete from bridging_sep where no_rawat='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 2).toString() + "' and jnspelayanan='1' and LENGTH(no_sep)<19");
                
                Valid.MyReport("rptBridgingSEP.jasper", "report", "::[ Cetak SEP Rawat Inap ]::",
                        " SELECT no_sep, tglsep, CONCAT(no_kartu,' (MR. ',nomr,')') noka, nama_pasien, CONCAT(tanggal_lahir,' Kelamin : ',IF(jkel='L','Laki-laki','Perempuan')) tgl_lhr, "
                        + "IFNULL(notelep,'') notelp, IF(nmpolitujuan='','-',nmpolitujuan) sub_spesialis, nmdpdjp dokter, UPPER(nmppkrujukan) faskes_perujuk, "
                        + "nmdiagnosaawal diag_awal, catatan, peserta, IF(jnspelayanan='2','R. Jalan','R.Inap') jns_rawat, SUBSTRING(tujuanKunjungan,4,14) jns_kun, '-' poli_perujuk, "
                        + "IF(klsrawat='1','Kelas 1',IF(klsrawat='2','Kelas 2','Kelas 3')) klsHak, IFNULL(nmKelasNaiknya,'') nmklsnaik, "
                        + "if(penjamin='null','',penjamin) penjamin FROM bridging_sep WHERE no_rawat='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 2).toString() + "' "
                        + "AND jnsPelayanan='1'", param);

            } else {                
                if (Sequel.cariIsi("select urutan_sep from bridging_sep where no_rawat='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 2).toString() + "' and jnspelayanan='2'").equals("1")) {
                    param.put("kunjunganInternal", "-");
                } else {
                    param.put("kunjunganInternal", "- Kunjungan rujukan internal");
                }
                
                Sequel.queryu("delete from bridging_sep where no_rawat='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 2).toString() + "' and jnspelayanan='2' and LENGTH(no_sep)<19");
                
                if (Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 2).toString() + "'").equals("HIV")) {
                    param.put("subSpesialis", Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='HIV'"));
                    param.put("dokter", Sequel.cariIsi("select d.nm_dokter from reg_periksa rp inner join dokter d on d.kd_dokter=rp.kd_dokter "
                            + "where rp.no_rawat='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 2).toString() + "'"));
                    
                    Valid.MyReport("rptBridgingSEPvct.jasper", "report", "::[ Cetak SEP Rawat Jalan Poliklinik VCT ]::",
                            " SELECT no_sep, tglsep, CONCAT(no_kartu,' (MR. ',nomr,')') nomor, nama_pasien, "
                            + "CONCAT(tanggal_lahir,' Kelamin : ',if(jkel='L','Laki-laki','Perempuan')) tgl_lhr, IFNULL(notelep,'') notelp, "
                            + "nmpolitujuan sub_spesialis, nmdpjpLayan doktr, nmppkrujukan faskes_perujuk, nmdiagnosaawal diag_awal, catatan, "
                            + "peserta, if(jnspelayanan='2','R. Jalan','R.Inap') jns_rawat, SUBSTRING(tujuanKunjungan,4,14) jns_kun, '-' poli_perujuk, "
                            + "if(klsrawat='1','Kelas 1',if(klsrawat='2','Kelas 2','Kelas 3')) klsHak, IFNULL(nmKelasNaiknya,'') nmklsnaik, "
                            + "if(penjamin='null','',penjamin) penjamin FROM bridging_sep WHERE no_rawat='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 2).toString() + "' "
                            + "and jnspelayanan='2'", param);
                } else {
                    Valid.MyReport("rptBridgingSEP2.jasper", "report", "::[ Cetak SEP Rawat Jalan ]::",
                            " SELECT no_sep, tglsep, CONCAT(no_kartu,' (MR. ',nomr,')') nomor, nama_pasien, "
                            + "CONCAT(tanggal_lahir,' Kelamin : ',if(jkel='L','Laki-laki','Perempuan')) tgl_lhr, IFNULL(notelep,'') notelp, "
                            + "nmpolitujuan sub_spesialis, nmdpjpLayan doktr, nmppkrujukan faskes_perujuk, nmdiagnosaawal diag_awal, catatan, "
                            + "peserta, if(jnspelayanan='2','R. Jalan','R.Inap') jns_rawat, SUBSTRING(tujuanKunjungan,4,14) jns_kun, '-' poli_perujuk, "
                            + "if(klsrawat='1','Kelas 1',if(klsrawat='2','Kelas 2','Kelas 3')) klsHak, IFNULL(nmKelasNaiknya,'') nmklsnaik, "
                            + "if(penjamin='null','',penjamin) penjamin FROM bridging_sep WHERE no_rawat='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 2).toString() + "' "
                            + "and jnspelayanan='2'", param);
                }
                
                Sequel.mengedit("kelengkapan_booking_sep_bpjs", "no_rawat='" + TNoRw.getText() + "'", "status_cetak_sep='SUDAH' ");
            }
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data SEP yang mau dicetak...!!!!");
            BtnBatal.requestFocus();
        }
    }//GEN-LAST:event_ppSEPBtnPrintActionPerformed

    private void ppPulangBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPulangBtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tbSEP.getSelectedRow() != -1) {
            cekPulang = 0;
            cekDataPlg = 0;
            cekstsPulang = "";
            tglPulangInap = "";
            cekPulang = Sequel.cariInteger("SELECT count(-1) FROM kamar_inap WHERE no_rawat='" + TNoRw.getText() + "' and tgl_keluar='0000-00-00' and stts_pulang='-'");

            if (cekPulang == 1) {
                JOptionPane.showMessageDialog(null, "Pasien ini masih dalam proses perawatan inap & belum dipulangkan...!!");
                tbSEP.requestFocus();
            } else {
                cekDataPlg = Sequel.cariInteger("select count(-1) from bridging_sep_tgl_pulang where no_sep='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "'");
                cekstsPulang = Sequel.cariIsi("select stts_pulang from kamar_inap where no_rawat='" + TNoRw.getText() + "' and stts_pulang not in ('-','pindah kamar')");
                tglPulangInap = Sequel.cariIsi("select tgl_keluar from kamar_inap where no_rawat='" + TNoRw.getText() + "' and stts_pulang not in ('-','pindah kamar')");
                tglPlgRuangan.setText(Sequel.cariIsi("select CONCAT(tgl_keluar,' ',jam_keluar) from kamar_inap where "
                        + "no_rawat='" + TNoRw.getText() + "' and stts_pulang not in ('-','pindah kamar')"));
                
                if (cekDataPlg > 0) {
                    validasiDataPlg();
                    dataPulang();
                } else if (cekDataPlg == 0) {                    
                    validasiDataPlg();
                }
                WindowUpdatePulang.setLocationRelativeTo(internalFrame1);
                WindowUpdatePulang.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data SEP yang mau diupdate tanggal pulangnya...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppPulangBtnPrintActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowUpdatePulang.dispose();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void TanggalRujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalRujukKeyPressed
        Valid.pindah(evt, NoRujukan, TanggalSEP);
    }//GEN-LAST:event_TanggalRujukKeyPressed

    private void TanggalSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalSEPKeyPressed
        Valid.pindah(evt, TanggalRujuk,AsalRujukan);
    }//GEN-LAST:event_TanggalSEPKeyPressed

    private void LakaLantasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LakaLantasKeyPressed
        Valid.pindah(evt,btnPoli,JenisPelayanan);
    }//GEN-LAST:event_LakaLantasKeyPressed

    private void hakKelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hakKelasKeyPressed
        Valid.pindah(evt,JenisPelayanan,Eksekutif);
    }//GEN-LAST:event_hakKelasKeyPressed

    private void JenisPelayananItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JenisPelayananItemStateChanged
        cekLAYAN();
        cekPPKRUJUKAN();
    }//GEN-LAST:event_JenisPelayananItemStateChanged

    private void AsalRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsalRujukanKeyPressed
        Valid.pindah(evt, TanggalSEP,btnPPKRujukan);
    }//GEN-LAST:event_AsalRujukanKeyPressed

    private void COBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_COBKeyPressed
        Valid.pindah(evt,Eksekutif,Catatan);
    }//GEN-LAST:event_COBKeyPressed

    private void TNoRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNoRMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRMActionPerformed

    private void ppRujukanBiasaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRujukanBiasaBtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tbSEP.getSelectedRow() != -1) {
            cekRujukan = 0;
            cekRujukan = Sequel.cariInteger("select count(-1) from bridging_rujukan_bpjs where no_sep='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "'");

            if (cekRujukan == 0) {
                norujukan = "";
                TipeRujukan.setSelectedIndex(0);
                JenisPelayanan1.setSelectedIndex(1);
                KdPpkRujukan1.setText("");
                NmPpkRujukan1.setText("");
                KdPoli1.setText("");
                NmPoli1.setText("");
                Catatan1.setText("");
            } else if (cekRujukan > 0) {
                dataRujukan();
            }

            WindowRujukanBiasa.setLocationRelativeTo(internalFrame1);
            WindowRujukanBiasa.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data SEP yang mau dibuatkan rujukan...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppRujukanBiasaBtnPrintActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowRujukanBiasa.dispose();
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

    private void BtnSimpan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan5ActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoKartu, "Nomor Kartu");
        } else if (KdPpkRujukan1.getText().trim().equals("") || NmPpkRujukan1.getText().trim().equals("")) {
            Valid.textKosong(KdPpkRujukan1, "PPK Rujukan");
        } else if (KdPPK.getText().trim().equals("") || NmPPK.getText().trim().equals("")) {
            Valid.textKosong(KdPPK, "PPK Pelayanan");
        } else if (KdPenyakit1.getText().trim().equals("") || NmPenyakit1.getText().trim().equals("")) {
            Valid.textKosong(KdPenyakit1, "Diagnosa");
        } else if (Catatan1.getText().trim().equals("")) {
            Valid.textKosong(Catatan1, "Catatan");
        } else {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
                URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/2.0/insert";

                requestJson = "{"
                        + "\"request\": {"
                        + "\"t_rujukan\": {"
                        + "\"noSep\": \"" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "\","
                        + "\"tglRujukan\": \"" + Valid.SetTgl(TanggalRujukKeluar.getSelectedItem() + "") + "\","
                        + "\"tglRencanaKunjungan\": \"" + Valid.SetTgl(tglRencanaKunjungan.getSelectedItem() + "") + "\","
                        + "\"ppkDirujuk\": \"" + KdPpkRujukan1.getText() + "\","
                        + "\"jnsPelayanan\": \"" + JenisPelayanan1.getSelectedItem().toString().substring(0, 1) + "\","
                        + "\"catatan\": \"" + Catatan1.getText() + "\","
                        + "\"diagRujukan\": \"" + KdPenyakit1.getText() + "\","
                        + "\"tipeRujukan\": \"" + TipeRujukan.getSelectedItem().toString().substring(0, 1) + "\","
                        + "\"poliRujukan\": \"" + KdPoli1.getText() + "\","
                        + "\"user\": \"" + user + "\""
                        + "}"
                        + "}"
                        + "}";
                
                HttpEntity requestEntity = new HttpEntity(requestJson, headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
//                JsonNode response = root.path("response");

                if (nameNode.path("code").asText().equals("200")) {
//ini yang baru -----------            
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("rujukan").path("noRujukan");
//sampai sini -------------

                    if (Sequel.menyimpantf2("bridging_rujukan_bpjs", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rujukan", 15, new String[]{
                        tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString(), Valid.SetTgl(TanggalRujukKeluar.getSelectedItem() + ""),
                        KdPpkRujukan1.getText(), NmPpkRujukan1.getText(), JenisPelayanan1.getSelectedItem().toString().substring(0, 1),
                        Catatan1.getText(), KdPenyakit1.getText(), NmPenyakit1.getText(),
                        TipeRujukan.getSelectedItem().toString(), KdPoli1.getText(),
                        NmPoli1.getText(), response.asText(), user, Valid.SetTgl(tglRencanaKunjungan.getSelectedItem() + ""), TNoRw.getText()
                    }) == true) {

                        Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", nmfaskes_keluar, KdPpkRujukan1.getText());
                        Sequel.cariIsi("select kd_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", kode_rujukanya, KdPpkRujukan1.getText());

                        Sequel.menyimpan("rujuk", "'" + response.asText() + "','"
                                + TNoRw.getText() + "','" + nmfaskes_keluar.getText() + "','"
                                + Valid.SetTgl(TanggalRujukKeluar.getSelectedItem() + "") + "','"
                                + NmPenyakit1.getText() + "','" + Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?", TNoRw.getText())
                                + "','-','-','" + Catatan1.getText() + "','12:00:01','" + kode_rujukanya.getText() + "','" + NmPoli1.getText() + "'", "No.Rujuk");

                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        LocalDate tgldirujuk = LocalDate.parse(Valid.SetTgl(TanggalRujukKeluar.getSelectedItem() + ""));
                        LocalDate habisBerlaku = tgldirujuk.plusDays(90);

                        Map<String, Object> param = new HashMap<>();
                        param.put("namars", akses.getnamars());
                        param.put("alamatrs", akses.getalamatrs());
                        param.put("kotars", akses.getkabupatenrs());
                        param.put("propinsirs", akses.getpropinsirs());
                        param.put("kontakrs", akses.getkontakrs());
                        param.put("norujuk", response.asText());
                        param.put("logo", Sequel.cariGambar("select bpjs from gambar"));
                        param.put("tglRujukan", Valid.SetTglINDONESIA(Valid.SetTgl(TanggalRujukKeluar.getSelectedItem() + "")));
                        param.put("tglLahir", Valid.SetTglINDONESIA(TglLahir.getText()));
                        param.put("berlakuSampai", Valid.SetTglINDONESIA(habisBerlaku));
                        Valid.MyReport("rptBridgingRujukanBPJS.jasper", "report", "::[ Surat Rujukan Keluar VClaim ]::",
                                " SELECT br.no_sep, bs.no_rawat, bs.nomr,bs.nama_pasien,br.tglRujukan, br.no_rujukan,br.ppkDirujuk, br.nm_ppkDirujuk, "
                                + "IF(br.jnsPelayanan='1','Rawat Inap','Rawat Jalan')  jenis, br.tipeRujukan,br.catatan, br.diagRujukan,br.nama_diagRujukan, "
                                + "br.poliRujukan,br.nama_poliRujukan,bs.no_kartu, IF(bs.jkel='L','Laki-Laki','Perempuan') jkel "
                                + "FROM bridging_sep bs LEFT JOIN bridging_rujukan_bpjs br ON br.no_rawat=bs.no_rawat "
                                + "WHERE br.no_rujukan='" + response.asText() + "'", param);
                        WindowRujukanBiasa.dispose();
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                    Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Rujukan Keluar VClaim','Simpan'");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText()); 
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        }
    }//GEN-LAST:event_BtnSimpan5ActionPerformed

    private void btnPPKRujukan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPKRujukan1ActionPerformed
        pilihan=2;
        faskes.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        faskes.setLocationRelativeTo(internalFrame1);
        faskes.setVisible(true);
        faskes.fokus();
    }//GEN-LAST:event_btnPPKRujukan1ActionPerformed

    private void JenisPelayanan1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JenisPelayanan1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisPelayanan1ItemStateChanged

    private void JenisPelayanan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisPelayanan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisPelayanan1KeyPressed

    private void btnDiagnosa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosa1ActionPerformed
        pilihan=2;
        penyakit.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
        penyakit.fokus();
    }//GEN-LAST:event_btnDiagnosa1ActionPerformed

    private void btnPoli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPoli1ActionPerformed
        pilihan=2;
        poli.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        poli.fokus();
    }//GEN-LAST:event_btnPoli1ActionPerformed

    private void TipeRujukanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TipeRujukanItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TipeRujukanItemStateChanged

    private void TipeRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TipeRujukanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipeRujukanKeyPressed

    private void Catatan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Catatan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Catatan1KeyPressed

    private void TipeRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipeRujukanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipeRujukanActionPerformed

    private void TanggalRujukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TanggalRujukMouseClicked

    }//GEN-LAST:event_TanggalRujukMouseClicked

    private void AsalRujukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AsalRujukanMouseClicked
        AsalRujukan.setEditable(false);
    }//GEN-LAST:event_AsalRujukanMouseClicked

    private void LakaLantasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LakaLantasMouseClicked
        LakaLantas.setEditable(false);
    }//GEN-LAST:event_LakaLantasMouseClicked

    private void COBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_COBMouseClicked
        COB.setEditable(false);
    }//GEN-LAST:event_COBMouseClicked

    private void DTPCari1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DTPCari1MouseClicked
        DTPCari1.setEditable(false);
    }//GEN-LAST:event_DTPCari1MouseClicked

    private void DTPCari2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DTPCari2MouseClicked
        DTPCari2.setEditable(false);
    }//GEN-LAST:event_DTPCari2MouseClicked

    private void ppAmbilSepBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppAmbilSepBtnPrintActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoKartu, "Nomor Kartu");
        } else {
            WindowCariSEP.setLocationRelativeTo(internalFrame1);
            WindowCariSEP.setVisible(true);
            ChkNoSEP.setSelected(false);
            NoSEP.setText("");
            NoSEP.requestFocus();
            BtnSimpan6.setEnabled(false);
        }
    }//GEN-LAST:event_ppAmbilSepBtnPrintActionPerformed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowCariSEP.dispose();
        ChkNoSEP.setSelected(false);
        NoSEP.setText("");
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
        if(!NoSEP.getText().equals("")){
            pembi = "";
            kdpnjg = "";
            flag = "";
            asesmen = "";
//            jkel = "";
            cekSEP = 0;

            URL = prop.getProperty("URLAPIBPJS") + "/SEP/2.0/insert";

            tglkkl = "0000-00-00";
            if (LakaLantas.getSelectedIndex() == 1 || LakaLantas.getSelectedIndex() == 2 || LakaLantas.getSelectedIndex() == 3) {
                tglkkl = Valid.SetTgl(TanggalKejadian.getSelectedItem() + "");
            }

            //-----------------------------------------------------------------------------------------------
//            if (tujuanKun.getSelectedIndex() == 0) {
//                flagPro.setSelectedIndex(0);
//                kdPenunjang.setSelectedIndex(0);
//            } else {
//                flagPro.setSelectedIndex(1);
//                kdPenunjang.setSelectedIndex(1);
//            }
//
//            if (tujuanKun.getSelectedIndex() == 0 || tujuanKun.getSelectedIndex() == 2) {
//                asesmenPel.setSelectedIndex(1);
//            } else if (tujuanKun.getSelectedIndex() == 1) {
//                asesmenPel.setSelectedIndex(0);
//            }
            //-----------------------------------------------------------------------------------------------

            if (cmbPembiayaan.getSelectedIndex() == 0) {
                pembi = "";
            } else {
                pembi = cmbPembiayaan.getSelectedItem().toString().substring(0, 1);
            }
            
            if (flagPro.getSelectedIndex() == 0) {
                flag = "";
            } else {
                flag = flagPro.getSelectedItem().toString().substring(0, 1);
            }

            if (kdPenunjang.getSelectedIndex() == 10 || kdPenunjang.getSelectedIndex() == 11 || kdPenunjang.getSelectedIndex() == 12) {
                kdpnjg = kdPenunjang.getSelectedItem().toString().substring(0, 2);
            } else if (kdPenunjang.getSelectedIndex() == 0) {
                kdpnjg = "";
            } else {
                kdpnjg = kdPenunjang.getSelectedItem().toString().substring(0, 1);
            }

            if (asesmenPel.getSelectedIndex() == 0) {
                asesmen = "";
            } else {
                asesmen = asesmenPel.getSelectedItem().toString().substring(0, 1);
            }

            if (JenisPelayanan.getSelectedIndex() == 0) {
                KddpjpLayan.setText("");
                nmdpjpLayan.setText("");
            }
            //-----------------------------------------------------------------------------------------------
            
            cekSEP = Sequel.cariInteger("select count(-1) from bridging_sep where no_sep='" + NoSEP.getText() + "' and urutan_sep='1'");
            if (cekSEP > 0) {
                Sequel.menyimpantf("bridging_sep", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "SEP", 59, new String[]{
                    NoSEP.getText(), TNoRw.getText(), Valid.SetTgl(TanggalSEP.getSelectedItem() + ""), Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""),
                    NoRujukan.getText(), KdPpkRujukan.getText(), rujukanSEP.getText(), KdPPK.getText(), NmPPK.getText(),
                    JenisPelayanan.getSelectedItem().toString().substring(0, 1), Catatan.getText(), KdPenyakit.getText(),
                    NmPenyakit.getText(), KdPoli.getText(), NmPoli.getText(), hakKelas.getSelectedItem().toString().substring(0, 1),
                    LakaLantas.getSelectedItem().toString().substring(0, 1), LokasiLaka.getText(), user,
                    TNoRM.getText(), TPasien.getText(), TglLahir.getText(), JenisPeserta.getText(), jkel, NoKartu.getText(),
                    "0000-00-00 00:00:00", AsalRujukan.getSelectedItem().toString(), Eksekutif.getSelectedItem().toString(),
                    COB.getSelectedItem().toString(), "", NoTelp.getText(), KasusKatarak.getSelectedItem().toString(),
                    tglkkl, Ket.getText(), suplesi.getSelectedItem().toString(),
                    NoSEPSuplesi.getText(), KdProv.getText(), NmProv.getText(), KdKab.getText(), NmKab.getText(),
                    KdKec.getText(), NmKec.getText(), noSurat.getText(), Kddpjp.getText(), NmDPJP.getText(), "", hakKelas.getSelectedItem().toString().substring(0, 1),
                    kdNaikKls.getText(), pembi, pngJwb.getText(), tujuanKun.getSelectedItem().toString(), flag, kdpnjg, asesmen, KddpjpLayan.getText(), nmdpjpLayan.getText(),
                    Sequel.cariIsi("select ifnull(MAX(urutan_sep)+1,1) from bridging_sep where no_sep='" + NoSEP.getText() + "'"), nmKlsNaik.getText(), Status.getText()
                });
            } else {
                Sequel.menyimpantf("bridging_sep", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "SEP", 59, new String[]{
                    NoSEP.getText(), TNoRw.getText(), Valid.SetTgl(TanggalSEP.getSelectedItem() + ""), Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""),
                    NoRujukan.getText(), KdPpkRujukan.getText(), rujukanSEP.getText(), KdPPK.getText(), NmPPK.getText(),
                    JenisPelayanan.getSelectedItem().toString().substring(0, 1), Catatan.getText(), KdPenyakit.getText(),
                    NmPenyakit.getText(), KdPoli.getText(), NmPoli.getText(), hakKelas.getSelectedItem().toString().substring(0, 1),
                    LakaLantas.getSelectedItem().toString().substring(0, 1), LokasiLaka.getText(), user,
                    TNoRM.getText(), TPasien.getText(), TglLahir.getText(), JenisPeserta.getText(), jkel, NoKartu.getText(),
                    "0000-00-00 00:00:00", AsalRujukan.getSelectedItem().toString(), Eksekutif.getSelectedItem().toString(),
                    COB.getSelectedItem().toString(), "", NoTelp.getText(), KasusKatarak.getSelectedItem().toString(),
                    tglkkl, Ket.getText(), suplesi.getSelectedItem().toString(),
                    NoSEPSuplesi.getText(), KdProv.getText(), NmProv.getText(), KdKab.getText(), NmKab.getText(),
                    KdKec.getText(), NmKec.getText(), noSurat.getText(), Kddpjp.getText(), NmDPJP.getText(), "", hakKelas.getSelectedItem().toString().substring(0, 1),
                    kdNaikKls.getText(), pembi, pngJwb.getText(), tujuanKun.getSelectedItem().toString(), flag, kdpnjg, asesmen, KddpjpLayan.getText(), 
                    nmdpjpLayan.getText(), "1", nmKlsNaik.getText(), Status.getText()
                });
            }

            WindowCariSEP.dispose();            
            JOptionPane.showMessageDialog(null, "Data VClaim SEP BPJS pasien tersebut berhasil tersimpan...!!!!");
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void NoSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSEPKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCari1.requestFocus();
        }
    }//GEN-LAST:event_NoSEPKeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        cekSEPVclaim();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void KetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetKeyPressed

    private void btnProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProvActionPerformed
        pilihan = 1;
        provinsi.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        provinsi.setLocationRelativeTo(internalFrame1);
        provinsi.setVisible(true);
        provinsi.fokus();
    }//GEN-LAST:event_btnProvActionPerformed

    private void btnProvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnProvKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProvKeyPressed

    private void btnKabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKabActionPerformed
        pilihan = 1;
        kabupaten.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        kabupaten.setLocationRelativeTo(internalFrame1);
        kabupaten.setVisible(true);
        kabupaten.fokus(KdProv.getText(), NmProv.getText());
    }//GEN-LAST:event_btnKabActionPerformed

    private void btnKabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnKabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKabKeyPressed

    private void btnKecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKecActionPerformed
        pilihan = 1;
        kecamatan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        kecamatan.setLocationRelativeTo(internalFrame1);
        kecamatan.setVisible(true);
        kecamatan.fokus(KdKab.getText(), NmKab.getText());
    }//GEN-LAST:event_btnKecActionPerformed

    private void btnKecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnKecKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKecKeyPressed

    private void suplesiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suplesiMouseClicked
        suplesi.setEditable(false);
    }//GEN-LAST:event_suplesiMouseClicked

    private void suplesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_suplesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_suplesiKeyPressed

    private void NoSEPSuplesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSEPSuplesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoSEPSuplesiKeyPressed

    private void KasusKatarakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KasusKatarakMouseClicked
        KasusKatarak.setEditable(false);
    }//GEN-LAST:event_KasusKatarakMouseClicked

    private void KasusKatarakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KasusKatarakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KasusKatarakKeyPressed

    private void noSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noSuratKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noSuratKeyPressed

    private void btnDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDPJPActionPerformed
        pilihan = 1;
        dpjp.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dpjp.setLocationRelativeTo(internalFrame1);
        dpjp.setVisible(true);
        dpjp.fokus();
        dpjp.poliklinik(KdPoli.getText(), NmPoli.getText());
    }//GEN-LAST:event_btnDPJPActionPerformed

    private void btnNoSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoSuratActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            if (JenisPelayanan.getSelectedIndex() == 0) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                spri.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                spri.setLocationRelativeTo(internalFrame1);
                spri.emptTeks();
                spri.TCari.setText(NoKartu.getText());
                spri.TCari.requestFocus();
                spri.tampil();
                spri.ChkInput.setSelected(false);
                spri.isForm();
                spri.isCek();
                spri.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                suratKontrol.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                suratKontrol.setLocationRelativeTo(internalFrame1);
                suratKontrol.emptTeks();
                suratKontrol.TCari.setText(NoKartu.getText());                
                suratKontrol.TCari.requestFocus();
                suratKontrol.tampil();
                suratKontrol.ChkInput.setSelected(false);
                suratKontrol.isForm();
                suratKontrol.isCek();
                suratKontrol.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }  
        }        
        
//        skdp.setNoRm(TNoRM.getText(),TPasien.getText());
//        skdp.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
//        skdp.setLocationRelativeTo(internalFrame1);        
//        skdp.setVisible(true);        
//        skdp.fokusdata();
    }//GEN-LAST:event_btnNoSuratActionPerformed

    private void TipeRujukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TipeRujukanMouseClicked
        TipeRujukan.setEditable(false);
    }//GEN-LAST:event_TipeRujukanMouseClicked

    private void sepRujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sepRujukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_sepRujukKeyPressed

    private void BtnResetRujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResetRujukActionPerformed
        KdPpkRujukan1.setText("");
        NmPpkRujukan1.setText("");
        KdPenyakit1.setText("");
        NmPenyakit1.setText("");
        KdPoli1.setText("");
        NmPoli1.setText("");
        Catatan1.setText("");
        nmfaskes_keluar.setText("");
        kode_rujukanya.setText("");
        TanggalRujukKeluar.requestFocus();
    }//GEN-LAST:event_BtnResetRujukActionPerformed

    private void btnNoRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoRujukanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoKartu, "Nomor Kartu");
        } else {
            WindowCariNoRujuk.setLocationRelativeTo(internalFrame1);
            WindowCariNoRujuk.setVisible(true);
            TabRujukan.setSelectedIndex(0);
            tampilFaskes1BYK();
        }
    }//GEN-LAST:event_btnNoRujukanActionPerformed

    private void tbFaskes1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaskes1MouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataFK1();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaskes1MouseClicked

    private void tbFaskes1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaskes1KeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataFK1();
                } catch (java.lang.NullPointerException e) {
                }
            }
            WindowCariNoRujuk.dispose();
        }
    }//GEN-LAST:event_tbFaskes1KeyPressed

    private void tbFaskes2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaskes2MouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataFK2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaskes2MouseClicked

    private void tbFaskes2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaskes2KeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataFK2();
                } catch (java.lang.NullPointerException e) {
                }
            }
            WindowCariNoRujuk.dispose();
        }
    }//GEN-LAST:event_tbFaskes2KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowCariNoRujuk.dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll, BtnKeluar);
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void TabRujukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRujukanMouseClicked
        if (TabRujukan.getSelectedIndex() == 0) {
            tampilFaskes1BYK();            
        } else if (TabRujukan.getSelectedIndex() == 1) {
            tampilFaskes2BYK();            
        } else if (TabRujukan.getSelectedIndex() == 2) {
            tampilFaskes1();
        } else if (TabRujukan.getSelectedIndex() == 3) {
            tampilFaskes2();
        }
    }//GEN-LAST:event_TabRujukanMouseClicked

    private void tbFaskes3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaskes3MouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getDataFK1byk();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaskes3MouseClicked

    private void tbFaskes3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaskes3KeyPressed
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataFK1byk();
                } catch (java.lang.NullPointerException e) {
                }
            }
            WindowCariNoRujuk.dispose();
        }
    }//GEN-LAST:event_tbFaskes3KeyPressed

    private void tbFaskes4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaskes4MouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                getDataFK2byk();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaskes4MouseClicked

    private void tbFaskes4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaskes4KeyPressed
        if (tabMode4.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataFK2byk();
                } catch (java.lang.NullPointerException e) {
                }
            }
            WindowCariNoRujuk.dispose();
        }
    }//GEN-LAST:event_tbFaskes4KeyPressed

    private void AsalRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AsalRujukanActionPerformed
        if (AsalRujukan.getSelectedIndex() == 1) {
            KdPpkRujukan.setText(KdPPK.getText());
            rujukanSEP.setText(NmPPK.getText());
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
//            NmPpkRujukan.setText(NmPPK.getText());
//            NmPpkRujukan.setText("");
        } else if (AsalRujukan.getSelectedIndex() == 0) {
            KdPpkRujukan.setText("");
            rujukanSEP.setText("- belum terisi -");
            NmPpkRujukan.setText("");
        }
    }//GEN-LAST:event_AsalRujukanActionPerformed

    private void CatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CatatanKeyPressed
        Valid.pindah(evt,COB,LokasiLaka);
    }//GEN-LAST:event_CatatanKeyPressed

    private void NoSEPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSEPKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_NoSEPKeyTyped

    private void kode_rujukanyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_rujukanyaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_rujukanyaKeyPressed

    private void nmfaskes_keluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmfaskes_keluarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmfaskes_keluarKeyPressed

    private void MnRekapSEPRanapBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapSEPRanapBtnPrintActionPerformed
        if (tbSEP.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa diexport menjadi file excel...!!!!");
            BtnBatal.requestFocus();
        } else if (tbSEP.getRowCount() != 0) {
            ExportSEPRanap();
        }
    }//GEN-LAST:event_MnRekapSEPRanapBtnPrintActionPerformed

    private void MnRekapSEPRalanBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapSEPRalanBtnPrintActionPerformed
        if (tbSEP.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa diexport menjadi file excel...!!!!");
            BtnBatal.requestFocus();
        } else if (tbSEP.getRowCount() != 0) {
            ExportSEPRalan();
        }
    }//GEN-LAST:event_MnRekapSEPRalanBtnPrintActionPerformed

    private void ppRencanaKontrolBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRencanaKontrolBtnPrintActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));            
            if (tabMode.getRowCount() < 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasiennya...!!!");
            } else if (SEPkontrol.equals("")) {
                x = JOptionPane.showConfirmDialog(null, "No. SEP utk. rencana kontrol blm. ditentukan. (Tombol YES dibikinkan dari registrasi, NO dibikinkan dari data SEP)", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    BtnKeluarActionPerformed(null);
                } else {
                    TabRawat.setSelectedIndex(1);
                    DTPCari1.requestFocus();
                }
            } else {
                suratKontrol.setNoRm(TNoRw.getText(), SEPkontrol, NoKartu.getText(), TNoRM.getText(),
                        TPasien.getText(), TglLahir.getText(), tbSEP.getValueAt(tbSEP.getSelectedRow(), 28).toString(),
                        NmPenyakit.getText(), tbSEP.getValueAt(tbSEP.getSelectedRow(), 11).toString());
                suratKontrol.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                suratKontrol.setLocationRelativeTo(internalFrame1);
                suratKontrol.ChkInput.setSelected(true);
                suratKontrol.TCari.setText(NoKartu.getText());
                suratKontrol.tampil();
                suratKontrol.isForm();
                suratKontrol.isCek();
                suratKontrol.setVisible(true);
                BtnKeluarActionPerformed(null);
            }
            this.setCursor(Cursor.getDefaultCursor());            
        }
    }//GEN-LAST:event_ppRencanaKontrolBtnPrintActionPerformed

    private void ppUpdateSEPdariVclaimBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppUpdateSEPdariVclaimBtnPrintActionPerformed
        cttnVclaim = "";
        diagVclaim = "";
        jpelVclaim = "";
        KRVclaim = "";
        noRujukVclaim = "";
        JPVclaim = "";
        kelaminVclaim = "";
        namaVclaim = "";
        nokaVclaim = "";
        nomrVclaim = "";
        tglLhrVclaim = "";
        poliVclaim = "";
        poliEksVclaim = "";
        tglSEPVclaim = "";
        nilaiJP = "";
        nilaiKR = "";
        nilaiEKS = "";
 
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
            URL = prop.getProperty("URLAPIBPJS") + "/SEP/" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString();
            
            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());
            
            if (nameNode.path("message").asText().equals("Sukses")) {                
//ini yang baru -----------            
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
//sampai sini -------------
                cttnVclaim = response.path("catatan").asText();
                diagVclaim = response.path("diagnosa").asText();
                jpelVclaim = response.path("jnsPelayanan").asText();
                KRVclaim = response.path("kelasRawat").asText();
                noRujukVclaim = response.path("noRujukan").asText();
                JPVclaim = response.path("peserta").path("jnsPeserta").asText();
                kelaminVclaim = response.path("peserta").path("kelamin").asText();
                namaVclaim = response.path("peserta").path("nama").asText();
                nokaVclaim = response.path("peserta").path("noKartu").asText();
                nomrVclaim = response.path("peserta").path("noMr").asText();
                tglLhrVclaim = response.path("peserta").path("tglLahir").asText();
                poliVclaim = response.path("poli").asText();
                poliEksVclaim = response.path("poliEksekutif").asText();
                tglSEPVclaim = response.path("tglSep").asText();
                
                if (jpelVclaim.equals("Rawat Jalan")) {
                    nilaiJP = "2";
                } else {
                    nilaiJP = "1";
                }
                
                if (KRVclaim.equals("-")) {
                    nilaiKR = "3";
                } else {
                    nilaiKR = KRVclaim;
                }
                
                if (poliEksVclaim.equals("0")) {
                    nilaiEKS = "0. Tidak";
                } else {
                    nilaiEKS = "1. Ya";
                }

                Sequel.mengedit("bridging_sep", "no_sep='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "'",
                        "catatan='" + cttnVclaim + "', nmdiagnosaawal='" + diagVclaim + "', jnspelayanan='" + nilaiJP + "', klsrawat='" + nilaiKR + "', "
                        + "no_rujukan='" + noRujukVclaim + "', peserta='" + JPVclaim + "', jkel='" + kelaminVclaim + "', nama_pasien='" + namaVclaim + "', "
                        + "no_kartu='" + nokaVclaim + "', nomr='" + nomrVclaim + "', tanggal_lahir='" + tglLhrVclaim + "', nmpolitujuan='" + poliVclaim + "', "
                        + "eksekutif='" + nilaiEKS + "', tglsep='" + tglSEPVclaim + "', user='" + akses.getkode() + "'");
                
                Sequel.mengedit("rujuk_masuk", "no_rawat=?", "no_rawat=?,perujuk=?,no_rujuk=?,kd_rujukan=?", 5, new String[]{
                    TNoRw.getText(), NmPpkRujukan.getText(), noRujukVclaim,
                    tbSEP.getValueAt(tbSEP.getSelectedRow(), 2).toString(), kode_rujukanya.getText()
                });
                
                JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());
                emptTeks();
                tampil();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                dispose();
            }
        }        
    }//GEN-LAST:event_ppUpdateSEPdariVclaimBtnPrintActionPerformed

    private void MnBackdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBackdateActionPerformed
        PengajuanBackdate();
    }//GEN-LAST:event_MnBackdateActionPerformed

    private void MnFingerPrinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFingerPrinActionPerformed
        PengajuanFingerPrint();
    }//GEN-LAST:event_MnFingerPrinActionPerformed

    private void pngJwbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pngJwbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pngJwbKeyPressed

    private void btnDPJPlayanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDPJPlayanActionPerformed
        pilihan = 2;
        dpjp.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dpjp.setLocationRelativeTo(internalFrame1);
        dpjp.setVisible(true);
        dpjp.fokus();
        dpjp.poliklinik(KdPoli.getText(), NmPoli.getText());
    }//GEN-LAST:event_btnDPJPlayanActionPerformed

    private void cmbPembiayaanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbPembiayaanKeyReleased
        if (cmbPembiayaan.getSelectedIndex() == 0) {
            pngJwb.setText("");
        } else if (cmbPembiayaan.getSelectedIndex() == 1) {
            pngJwb.setText("Pribadi");
        } else if (cmbPembiayaan.getSelectedIndex() == 2) {
            pngJwb.setText("Pemberi Kerja");
        } else if (cmbPembiayaan.getSelectedIndex() == 3) {
            pngJwb.setText("Asuransi Kesehatan Tambahan");
        }
    }//GEN-LAST:event_cmbPembiayaanKeyReleased

    private void JenisPelayananKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisPelayananKeyReleased
        cekLAYAN();
    }//GEN-LAST:event_JenisPelayananKeyReleased

    private void cmbPembiayaanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPembiayaanItemStateChanged
        cmbPembiayaanKeyReleased(null);
    }//GEN-LAST:event_cmbPembiayaanItemStateChanged

    private void BtnGantiRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiRujukanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoKartu, "Nomor Kartu");
        } else if (KdPpkRujukan1.getText().trim().equals("") || NmPpkRujukan1.getText().trim().equals("")) {
            Valid.textKosong(KdPpkRujukan1, "PPK Rujukan");
        } else if (KdPPK.getText().trim().equals("") || NmPPK.getText().trim().equals("")) {
            Valid.textKosong(KdPPK, "PPK Pelayanan");
        } else if (KdPenyakit1.getText().trim().equals("") || NmPenyakit1.getText().trim().equals("")) {
            Valid.textKosong(KdPenyakit1, "Diagnosa");
        } else if (Catatan1.getText().trim().equals("")) {
            Valid.textKosong(Catatan1, "Catatan");
        } else if (norujukan.equals("")) {
            JOptionPane.showMessageDialog(null, "Rujukan keluar untuk pasien ini belum dibikinkan,..!!");
        } else {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
                URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/2.0/Update";

                requestJson = "{"
                        + "\"request\": {"
                        + "\"t_rujukan\": {"
                        + "\"noRujukan\": \"" + norujukan + "\","
                        + "\"tglRujukan\": \"" + Valid.SetTgl(TanggalRujukKeluar.getSelectedItem() + "") + "\","
                        + "\"tglRencanaKunjungan\": \"" + Valid.SetTgl(tglRencanaKunjungan.getSelectedItem() + "") + "\","
                        + "\"ppkDirujuk\": \"" + KdPpkRujukan1.getText() + "\","
                        + "\"jnsPelayanan\": \"" + JenisPelayanan1.getSelectedItem().toString().substring(0, 1) + "\","
                        + "\"catatan\": \"" + Catatan1.getText() + "\","
                        + "\"diagRujukan\": \"" + KdPenyakit1.getText() + "\","
                        + "\"tipeRujukan\": \"" + TipeRujukan.getSelectedItem().toString().substring(0, 1) + "\","
                        + "\"poliRujukan\": \"" + KdPoli1.getText() + "\","
                        + "\"user\": \"" + user + "\""
                        + "}"
                        + "}"
                        + "}";
                
                HttpEntity requestEntity = new HttpEntity(requestJson, headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
//ini yang baru -----------            
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
//sampai sini -------------
//                JsonNode response = root.path("response");

                if (nameNode.path("code").asText().equals("200")) {
                    Sequel.mengedit("bridging_rujukan_bpjs", "no_rujukan='" + norujukan + "'",
                            "tglRujukan='" + Valid.SetTgl(TanggalRujukKeluar.getSelectedItem() + "") + "', "
                            + "ppkDirujuk='" + KdPpkRujukan1.getText() + "', "
                            + "nm_ppkDirujuk='" + NmPpkRujukan1.getText() + "',"
                            + "jnsPelayanan='" + JenisPelayanan1.getSelectedItem().toString().substring(0, 1) + "', "
                            + "catatan='" + Catatan1.getText() + "',"
                            + "diagRujukan='" + KdPenyakit1.getText() + "',"
                            + "nama_diagRujukan='" + NmPenyakit1.getText() + "',"
                            + "tipeRujukan='" + TipeRujukan.getSelectedItem().toString() + "',"
                            + "poliRujukan='" + KdPoli1.getText() + "',"
                            + "nama_poliRujukan='" + NmPoli1.getText() + "',"
                            + "user='" + user + "',"
                            + "tglRencanaKunjungan='" + Valid.SetTgl(tglRencanaKunjungan.getSelectedItem() + "") + "'");

                    Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", nmfaskes_keluar, KdPpkRujukan1.getText());
                    Sequel.cariIsi("select kd_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", kode_rujukanya, KdPpkRujukan1.getText());

                    Sequel.mengedit("rujuk", "no_rujuk='" + norujukan + "'",
                            "rujuk_ke='" + nmfaskes_keluar.getText() + "',"
                            + "tgl_rujuk='" + Valid.SetTgl(TanggalRujukKeluar.getSelectedItem() + "") + "', "
                            + "keterangan_diagnosa='" + NmPenyakit1.getText() + "', "
                            + "keterangan='" + Catatan1.getText() + "',"
                            + "kd_rujukan='" + kode_rujukanya.getText() + "',"
                            + "poliklinik_tujuan='" + NmPoli1.getText() + "'");

                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    LocalDate tgldirujuk = LocalDate.parse(Valid.SetTgl(TanggalRujukKeluar.getSelectedItem() + ""));
                    LocalDate habisBerlaku = tgldirujuk.plusDays(90);
                    
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("norujuk", norujukan);
                    param.put("logo", Sequel.cariGambar("select bpjs from gambar"));
                    param.put("tglRujukan", Valid.SetTglINDONESIA(Valid.SetTgl(TanggalRujukKeluar.getSelectedItem() + "")));
                    param.put("tglLahir", Valid.SetTglINDONESIA(TglLahir.getText()));
                    param.put("berlakuSampai", Valid.SetTglINDONESIA(habisBerlaku));
                    Valid.MyReport("rptBridgingRujukanBPJS.jasper", "report", "::[ Surat Rujukan Keluar VClaim ]::",
                            " SELECT br.no_sep, bs.no_rawat, bs.nomr,bs.nama_pasien,br.tglRujukan, br.no_rujukan,br.ppkDirujuk, br.nm_ppkDirujuk, "
                            + "IF(br.jnsPelayanan='1','Rawat Inap','Rawat Jalan')  jenis, br.tipeRujukan,br.catatan, br.diagRujukan,br.nama_diagRujukan, "
                            + "br.poliRujukan,br.nama_poliRujukan,bs.no_kartu, IF(bs.jkel='L','Laki-Laki','Perempuan') jkel "
                            + "FROM bridging_sep bs LEFT JOIN bridging_rujukan_bpjs br ON br.no_rawat=bs.no_rawat "
                            + "WHERE br.no_rujukan='" + norujukan + "'", param);
                    WindowRujukanBiasa.dispose();
                    this.setCursor(Cursor.getDefaultCursor());

                    Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Rujukan Biasa Keluar VClaim','Ganti'");
                } else {
                    JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        }
    }//GEN-LAST:event_BtnGantiRujukanActionPerformed

    private void ppRujukanKhususBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRujukanKhususBtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tbSEP.getSelectedRow() != -1) {
            cekRujukan = 0;
            cekRujukan = Sequel.cariInteger("select count(-1) from bridging_sep_rujukan_khusus where no_sep='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "'");

            if (cekRujukan == 0) {
                noRujukanKhusus.setText(Sequel.cariIsi("select no_rujukan from bridging_rujukan_bpjs where no_sep='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "'"));
                kdDiagPrimer.setText("");
                nmDiagPrimer.setText("");
                kddiagSekun = "";
                nmdiagSekun = "";
                kdprosedur = "";
                nmprosedur = "";
                sepRujukKhusus.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString());
                Valid.tabelKosong(tabMode5);
                Valid.tabelKosong(tabMode6);
            } else if (cekRujukan > 0) {
                JOptionPane.showMessageDialog(null, "Rujukan khusus untuk pasien ini sudah dibikinkan...!");
                tbSEP.requestFocus();
            }

            WindowRujukanKhusus.setLocationRelativeTo(internalFrame1);
            WindowRujukanKhusus.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data SEP yang mau dibuatkan rujukan...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppRujukanKhususBtnPrintActionPerformed

    private void BtnCloseIn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn7ActionPerformed
        WindowRujukanKhusus.dispose();
    }//GEN-LAST:event_BtnCloseIn7ActionPerformed

    private void BtnSimpan7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan7ActionPerformed
        if (noRujukanKhusus.getText().trim().equals("")) {
            Valid.textKosong(noRujukanKhusus, "No. Rujukan");
        } else if (kdDiagPrimer.getText().trim().equals("") || nmDiagPrimer.getText().equals("")) {
            Valid.textKosong(kdDiagPrimer, "Diagnosa Primer");
        } else if (tabMode5.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Diagnosa sekunder harus diisi dulu...!!!!");
            btnDiagSekunder.requestFocus();
        } else {
            diagSekunderSIAP();
            prosedurSIAP();
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
                URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/Khusus/insert";
                
                requestJson = "{"
                        + "\"noRujukan\": \"" + noRujukanKhusus.getText() + "\","
                        + "\"diagnosa\": ["
                        + "{\"kode\": \"P;" + kdDiagPrimer.getText() + "\"},"
                        + "{\"kode\": \"" + diagSekunderKirim + "\"}"                        
                        + "],"
                        + "\"procedure\": ["
                        + "{\"kode\": \"" + prosedurKirim + "\"}"
                        + "],"
                        + "\"user\": \"" + user + "\""
                        + "}";
                
                HttpEntity requestEntity = new HttpEntity(requestJson, headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
//ini yang baru -----------            
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("rujukan");
//sampai sini -------------
//                JsonNode response = root.path("response");

                if (nameNode.path("code").asText().equals("200")) {
                    Sequel.menyimpan("bridging_sep_rujukan_khusus",
                            "'" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "',"
                            + "'" + TNoRw.getText() + "',"
                            + "'" + noRujukanKhusus.getText() + "',"
                            + "'" + kdDiagPrimer.getText() + "',"
                            + "'" + diagSekunderSimpan + "',"
                            + "'" + prosedurSimpan + "',"
                            + "'" + user + "',"
                            + "'" + response.path("nokapst").asText() + "',"
                            + "'" + response.path("nmpst").asText() + "',"
                            + "'" + response.path("diagppk").asText() + "',"
                            + "'" + response.path("tglrujukan_awal").asText() + "',"
                            + "'" + response.path("tglrujukan_berakhir").asText() + "'");

//                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//                    Map<String, Object> param = new HashMap<>();
//                    param.put("namars", var.getnamars());
//                    param.put("alamatrs", var.getalamatrs());
//                    param.put("kotars", var.getkabupatenrs());
//                    param.put("propinsirs", var.getpropinsirs());
//                    param.put("kontakrs", var.getkontakrs());
//                    param.put("norujuk", response.path("rujukan").path("noRujukan").asText());
//                    param.put("logo", Sequel.cariGambar("select bpjs from gambar"));
//                    Valid.MyReport("rptBridgingRujukanBPJS.jasper", param, "::[ Surat Rujukan Keluar VClaim BPJS ]::");
//                    WindowRujukanKhusus.dispose();
//                    this.setCursor(Cursor.getDefaultCursor());

                    Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Rujukan Khusus Keluar VClaim','Simpan'");
                } else {
                    JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        }
        
    }//GEN-LAST:event_BtnSimpan7ActionPerformed

    private void btnDiagSekunderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagSekunderActionPerformed
        pilihan = 4;
        penyakit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
        penyakit.fokus();
    }//GEN-LAST:event_btnDiagSekunderActionPerformed

    private void btnProsedurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsedurActionPerformed
        prosedur.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        prosedur.setLocationRelativeTo(internalFrame1);
        prosedur.setVisible(true);
        prosedur.fokus();
    }//GEN-LAST:event_btnProsedurActionPerformed

    private void sepRujukKhususKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sepRujukKhususKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_sepRujukKhususKeyPressed

    private void BtnResetRujuk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResetRujuk1ActionPerformed
        noRujukanKhusus.setText("");
        kdDiagPrimer.setText("");
        nmDiagPrimer.setText("");
        kddiagSekun = "";
        nmdiagSekun = "";
        kdprosedur = "";
        nmprosedur = "";
        sepRujukKhusus.setText("");
        Valid.tabelKosong(tabMode5);
        Valid.tabelKosong(tabMode6);
    }//GEN-LAST:event_BtnResetRujuk1ActionPerformed

    private void btnDiagPrimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagPrimerActionPerformed
        pilihan = 3;
        penyakit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
        penyakit.fokus();
    }//GEN-LAST:event_btnDiagPrimerActionPerformed

    private void BtnHapusSekunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusSekunActionPerformed
        for (i = 0; i < tbdiagSekunder.getRowCount(); i++) {
            if (tbdiagSekunder.getValueAt(i, 0).toString().equals("true")) {
                tabMode5.removeRow(i);
            }
        }
        BtnHapusSekun.requestFocus();
    }//GEN-LAST:event_BtnHapusSekunActionPerformed

    private void BtnHapusProsedurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusProsedurActionPerformed
        for (i = 0; i < tbProsedur.getRowCount(); i++) {
            if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                tabMode6.removeRow(i);
            }
        }
        BtnHapusProsedur.requestFocus();
    }//GEN-LAST:event_BtnHapusProsedurActionPerformed

    private void BtnHapusProsedurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusProsedurKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnHapusProsedurKeyPressed

    private void ppSEPinternalBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSEPinternalBtnPrintActionPerformed
        BPJSCekSEPInternal sepInternal = new BPJSCekSEPInternal(null, false);
        sepInternal.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        sepInternal.setLocationRelativeTo(internalFrame1);
        sepInternal.setVisible(true);
        sepInternal.NoSEP.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString());
        sepInternal.tampil();
    }//GEN-LAST:event_ppSEPinternalBtnPrintActionPerformed

    private void ppSPRIBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSPRIBtnPrintActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            spri.setNoRm(TNoRw.getText(), NoKartu.getText(), TNoRM.getText(), TPasien.getText(),
                    TglLahir.getText(), Sequel.cariIsi("select jk from pasien where no_rkm_medis='" + TNoRM.getText() + "'"),
                    Sequel.cariIsi("select ifnull(kd_dokter_ranap,'-') from data_igd where no_rawat='" + TNoRw.getText() + "'"));
            spri.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            spri.setLocationRelativeTo(internalFrame1);
            spri.ChkInput.setSelected(true);
            spri.TCari.setText(NoKartu.getText());
            spri.tampil();
            spri.isForm();
            spri.isCek();
            spri.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppSPRIBtnPrintActionPerformed

    private void ppCekFingerPrinBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCekFingerPrinBtnPrintActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasiennya...!!!");
            tbSEP.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {
                prop.loadFromXML(new FileInputStream("setting/database.xml"));
                URL = prop.getProperty("URLAPIBPJS") + "SEP/FingerPrint/Peserta/" + NoKartu.getText() + "/TglPelayanan/" + Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'");

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key", koneksiDB.USERKEYAPIBPJS());

                HttpEntity requestEntity = new HttpEntity(headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());

                if (nameNode.path("code").asText().equals("200")) {
                    //ini yang baru -----------
                    JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                    //sampai sini -------------
                    JOptionPane.showMessageDialog(null, "Pasien A.n. " + TPasien.getText() + ", dg. tgl. kunjungan : "
                        + Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'"))
                        + ", sesuai data dari BPJS bahwa " + response.path("status").asText());
                } else {
                    System.out.println("Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", isi pesan : " + nameNode.path("message").asText());
                    JOptionPane.showMessageDialog(null, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", isi pesan : " + nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Peserta : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppCekFingerPrinBtnPrintActionPerformed

    private void MnBackdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBackdate1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!NoKartu.getText().equals("")) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
                URL = prop.getProperty("URLAPIBPJS") + "/Sep/aprovalSEP";
                
                requestJson = "{"
                        + "\"request\": {"
                        + "\"t_sep\": {"
                        + "\"noKartu\": \"" + NoKartu.getText() + "\","
                        + "\"tglSep\": \"" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "\","
                        + "\"jnsPelayanan\": \"" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "\","                        
                        + "\"keterangan\": \"" + Catatan.getText() + "\","
                        + "\"user\": \"" + user + "\""
                        + "}"
                        + "}"
                        + "}";
                
                System.out.println("Ini mengirim : " + requestJson);
                HttpEntity requestEntity = new HttpEntity(requestJson, headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
//                JsonNode response = root.path("response");

                if (nameNode.path("code").asText().equals("200")) {
                    Sequel.simpanReplaceInto("bridging_sep_aproval_pengajuan",
                            "'"+ TNoRw.getText() + "','"
                            + NoKartu.getText() + "','"
                            + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "','"
                            + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "','"
                            + Catatan.getText() + "','"
                            + user + "','1'", "Aproval Pengajuan SEP Backdate");

                    JOptionPane.showMessageDialog(null, "Proses aproval backdate selesai, data nomor rawat berhasil dikirim ke SEP..!!");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data peserta yang mau dimapping transaksinya...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBackdate1ActionPerformed

    private void MnFingerPrin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFingerPrin1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!NoKartu.getText().equals("")) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
                URL = prop.getProperty("URLAPIBPJS") + "/Sep/aprovalSEP";

                requestJson = "{"
                        + "\"request\": {"
                        + "\"t_sep\": {"
                        + "\"noKartu\": \"" + NoKartu.getText() + "\","
                        + "\"tglSep\": \"" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "\","
                        + "\"jnsPelayanan\": \"" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "\","
                        + "\"jnsPengajuan\": \"2\","
                        + "\"keterangan\": \"" + Catatan.getText() + "\","
                        + "\"user\": \"" + user + "\""
                        + "}"
                        + "}"
                        + "}";

                System.out.println("Ini mengirim : " + requestJson);
                HttpEntity requestEntity = new HttpEntity(requestJson, headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
//                JsonNode response = root.path("response");

                if (nameNode.path("code").asText().equals("200")) {
                    Sequel.simpanReplaceInto("bridging_sep_aproval_pengajuan",
                            "'"+ TNoRw.getText() + "','"
                            + NoKartu.getText() + "','"
                            + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "','"
                            + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "','"
                            + Catatan.getText() + "','"
                            + user + "','2'", "Aproval Pengajuan SEP Finger Print");

                    JOptionPane.showMessageDialog(null, "Proses aproval finger print selesai, data nomor rawat berhasil dikirim ke SEP..!!");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data peserta yang mau dimapping transaksinya...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnFingerPrin1ActionPerformed

    private void btnNoRujukanInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoRujukanInapActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            cekDataPlg = 0;
            tglPulangInap = "";
            cekDataPlg = Sequel.cariInteger("select count(-1) from reg_periksa where no_rkm_medis='" + TNoRM.getText() + "' and status_lanjut='Ranap'");
            
            if (cekDataPlg > 0) {
                tglPulangInap = Sequel.cariIsi("SELECT CONCAT('tgl. ',DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y'),', Jam : ',ki.jam_keluar) FROM kamar_inap ki "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=ki.no_rawat WHERE rp.no_rkm_medis='" + TNoRM.getText() + "' AND "
                        + "rp.status_lanjut='Ranap' AND ki.stts_pulang NOT IN ('-','pindah kamar') ORDER BY ki.tgl_keluar DESC LIMIT 1");

                x = JOptionPane.showConfirmDialog(rootPane, "Terakhir plg. rawat inap " + tglPulangInap + ". Apakah No. SEP nya akan dipakai utk. No. Rujukan kontrol rawat jalan..?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    NoRujukan.setText(Sequel.cariIsi("select ifnull(no_sep,'no. sep tidak ditemukan') from bridging_sep where nomr='" + TNoRM.getText() + "' "
                            + "and jnsPelayanan='1' order by tglsep desc limit 1"));
                } else {
                    NoRujukan.setText("");
                }
            } else if (cekDataPlg == 0) {
                JOptionPane.showMessageDialog(null, "Selama berobat disini, pasien ini belum pernah rawat inap...!!!!");
                NoRujukan.setText("");
            }            
        }
    }//GEN-LAST:event_btnNoRujukanInapActionPerformed

    private void kdNaikKlsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdNaikKlsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdNaikKlsKeyPressed

    private void nmKlsNaikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmKlsNaikKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmKlsNaikKeyPressed

    private void btnNaikKlsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNaikKlsActionPerformed
        kelas.setSize(649, 295);
        kelas.setLocationRelativeTo(internalFrame1);
        kelas.setVisible(true);
        kelas.tampil("");
        kelas.Kelas.requestFocus();
    }//GEN-LAST:event_btnNaikKlsActionPerformed

    private void ppProgramPRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppProgramPRBActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else if (JenisPelayanan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk kasus rawat jalan saja...!!!");
        } else {
            if (tbSEP.getSelectedRow() != -1) {
                try {
                    ps = koneksi.prepareStatement("select concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) alamat "
                            + "from pasien inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel "
                            + "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "
                            + "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis=?");
                    try {
                        ps.setString(1, tbSEP.getValueAt(tbSEP.getSelectedRow(), 3).toString());
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            BPJSProgramPRB form = new BPJSProgramPRB(null, false);
                            form.setNoRm(tbSEP.getValueAt(tbSEP.getSelectedRow(), 2).toString(), 
                                    tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString(), 
                                    tbSEP.getValueAt(tbSEP.getSelectedRow(), 29).toString(), 
                                    tbSEP.getValueAt(tbSEP.getSelectedRow(), 3).toString(), 
                                    tbSEP.getValueAt(tbSEP.getSelectedRow(), 4).toString(), 
                                    rs.getString("alamat"), 
                                    tbSEP.getValueAt(tbSEP.getSelectedRow(), 55).toString(), 
                                    tbSEP.getValueAt(tbSEP.getSelectedRow(), 13).toString());
                            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            form.setLocationRelativeTo(internalFrame1);
                            form.setVisible(true);
                            this.setCursor(Cursor.getDefaultCursor());
                        } else {
                            JOptionPane.showMessageDialog(null, "No.RM tidak ditemukan");
                            TCari.requestFocus();
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
        }
    }//GEN-LAST:event_ppProgramPRBActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        } else if (TabRawat.getSelectedIndex() == 2) {
            if (TCari.getText().trim().equals("")) {
                Valid.tabelKosong(tabMode7);
                LCount1.setText("0");
            } else {
                SEPkontrol = "";
                tampilKunjungan();                
            }
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void ppDataRujukanKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDataRujukanKeluarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSRujukanKeluar rujukanvclaim = new BPJSRujukanKeluar(null, false);
        rujukanvclaim.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        rujukanvclaim.setLocationRelativeTo(internalFrame1);
        rujukanvclaim.setVisible(true);
        rujukanvclaim.isCek();
        rujukanvclaim.tampil();
        rujukanvclaim.TCari.requestFocus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppDataRujukanKeluarActionPerformed

    private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar2ActionPerformed

    private void BtnKeluar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKeluar2KeyPressed

    private void ppRencanaKontrollagiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRencanaKontrollagiActionPerformed
        ppRencanaKontrolBtnPrintActionPerformed(null);
    }//GEN-LAST:event_ppRencanaKontrollagiActionPerformed

    private void BtnUpdatePulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdatePulangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoKartu, "Nomor Kartu");
        } else {
            if (cmbSttsPlg.getSelectedIndex() == 3 || cmbSttsPlg.getSelectedIndex() == 4) {
                if (noSrtMati.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Jika status pulangnya meninggal, No. Surat Meninggalnya wajib diisi..!!");
                } else {
                    PulangnyaDiupdate();
                }
            } else {
                PulangnyaDiupdate();
            }
        }
    }//GEN-LAST:event_BtnUpdatePulangActionPerformed

    private void ChkNoSEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkNoSEPActionPerformed
        if (ChkNoSEP.isSelected() == true) {
            NoSEP.setText("1704R004" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "").substring(5, 7) + "" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "").substring(2, 4) + "V");
            NoSEP.requestFocus();
        } else if (ChkNoSEP.isSelected() == false) {
            NoSEP.setText("");
            NoSEP.requestFocus();
        }
    }//GEN-LAST:event_ChkNoSEPActionPerformed

    private void tbRiwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatMouseClicked
        if (tabMode7.getRowCount() != 0) {
            try {
                getDataKunjungan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwayatMouseClicked

    private void tbRiwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatKeyPressed
        if (tabMode7.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataKunjungan();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    getDataKunjungan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatKeyPressed

    private void BtnKeluar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar3ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar3ActionPerformed

    private void BtnKeluar3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKeluar3KeyPressed

    private void ppRencanaKontrollagi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRencanaKontrollagi1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode7.getRowCount() < 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasiennya...!!!");
        } else if (SEPkontrol.equals("") || SEPkontrol.equals("-")) {
            x = JOptionPane.showConfirmDialog(null, "No. SEP utk. rencana kontrol blm. ditentukan. (Tombol YES dibikinkan dari registrasi, NO dibikinkan dari data riwayat kunjungan terakhir)", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                BtnKeluarActionPerformed(null);
            } else {
                TabRawat.setSelectedIndex(2);
                tbRiwayat.requestFocus();
            }
        } else if (!tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 10).toString().equals("B01")) {
            JOptionPane.showMessageDialog(null, "Data kunjungan yg. dipilih bukan dg. cara bayar BPJS Kesehatan...!!!");
            tbRiwayat.requestFocus();
        } else {
            suratKontrol.setNoRm(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 12).toString(), SEPkontrol,
                    tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 13).toString(), tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString(),
                    tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 14).toString(), tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 15).toString(),
                    tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 11).toString(), tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 16).toString(),
                    Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 12).toString() + "'"));
            suratKontrol.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            suratKontrol.setLocationRelativeTo(internalFrame1);
            suratKontrol.ChkInput.setSelected(true);
            suratKontrol.TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 13).toString());
            suratKontrol.tampil();
            suratKontrol.isForm();
            suratKontrol.isCek();
            suratKontrol.setVisible(true);
            BtnKeluarActionPerformed(null);
        }
        this.setCursor(Cursor.getDefaultCursor());        
    }//GEN-LAST:event_ppRencanaKontrollagi1ActionPerformed

    private void ppAmbilRujukanBiasaVclaimBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppAmbilRujukanBiasaVclaimBtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tbSEP.getSelectedRow() != -1) {
            if (Sequel.cariInteger("select count(-1) from bridging_rujukan_bpjs where no_rawat='" + TNoRw.getText() + "'") == 0) {
                WindowCariRujukanVClaim.setLocationRelativeTo(internalFrame1);
                WindowCariRujukanVClaim.setVisible(true);
                NoRujukanVclaim.setText("");
                NoRujukanVclaim.requestFocus();         
            } else if (Sequel.cariInteger("select count(-1) from bridging_rujukan_bpjs where no_rawat='" + TNoRw.getText() + "'") > 0) {
                JOptionPane.showMessageDialog(rootPane, "No. Rujukan VClaim : " + Sequel.cariIsi("select no_rujukan from bridging_rujukan_bpjs where no_rawat='" + TNoRw.getText() + "'") + ", "
                        + "utk. pasien ini sdh. tersimpan didatabase...!!!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data SEP yang mau dibuatkan rujukan...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppAmbilRujukanBiasaVclaimBtnPrintActionPerformed

    private void BtnCloseIn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn8ActionPerformed
        WindowCariRujukanVClaim.dispose();
    }//GEN-LAST:event_BtnCloseIn8ActionPerformed

    private void BtnSimpan8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan8ActionPerformed
        if (NoRujukanVclaim.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "No. Rujukan dari VClaim harus diisi dulu...!");
            NoRujukanVclaim.requestFocus();
        } else {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
                URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/Keluar/" + NoRujukanVclaim.getText();

                HttpEntity requestEntity = new HttpEntity(headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
                JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Isi Pesan : " + nameNode.path("message").asText());

                if (nameNode.path("code").asText().equals("200")) {
//ini yang baru -----------            
                    JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("rujukan");
                    System.out.println("ini responnya : " + mapper.readTree(api.Decrypt(root.path("response").asText(), utc)));
//sampai sini -------------
                    if (Sequel.menyimpantf2("bridging_rujukan_bpjs", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rujukan", 15, new String[]{
                        response.path("noSep").asText(), response.path("tglRujukan").asText(),
                        response.path("ppkDirujuk").asText(), response.path("namaPpkDirujuk").asText(), response.path("jnsPelayanan").asText(),
                        response.path("catatan").asText(), response.path("diagRujukan").asText(), response.path("namaDiagRujukan").asText(),
                        response.path("tipeRujukan").asText(), response.path("poliRujukan").asText(),
                        response.path("namaPoliRujukan").asText(), response.path("noRujukan").asText(), user, 
                        response.path("tglRencanaKunjungan").asText(), TNoRw.getText()
                    }) == true) {

                        Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", nmfaskes_keluar, response.path("ppkDirujuk").asText());
                        Sequel.cariIsi("select kd_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", kode_rujukanya, response.path("ppkDirujuk").asText());

                        Sequel.menyimpan("rujuk", "'" + response.asText() + "','"
                                + TNoRw.getText() + "','" + nmfaskes_keluar.getText() + "','"
                                + response.path("tglRujukan").asText() + "','"
                                + response.path("namaDiagRujukan").asText() + "','" + Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?", TNoRw.getText())
                                + "','-','-','" + response.path("catatan").asText() + "','12:00:01','" + kode_rujukanya.getText() + "','" +response.path("namaPoliRujukan").asText() + "'", "No.Rujuk");

                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        LocalDate tgldirujuk = LocalDate.parse(response.path("tglRujukan").asText());
                        LocalDate habisBerlaku = tgldirujuk.plusDays(90);

                        Map<String, Object> param = new HashMap<>();
                        param.put("namars", akses.getnamars());
                        param.put("alamatrs", akses.getalamatrs());
                        param.put("kotars", akses.getkabupatenrs());
                        param.put("propinsirs", akses.getpropinsirs());
                        param.put("kontakrs", akses.getkontakrs());
                        param.put("norujuk", response.asText());
                        param.put("logo", Sequel.cariGambar("select bpjs from gambar"));
                        param.put("tglRujukan", Valid.SetTglINDONESIA(response.path("tglRujukan").asText()));
                        param.put("tglLahir", Valid.SetTglINDONESIA(Sequel.cariIsi("select p.tgl_lahir from reg_periksa rp "
                                + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where rp.no_rawat='" + TNoRw.getText() + "'")));
                        param.put("berlakuSampai", Valid.SetTglINDONESIA(habisBerlaku));
                        Valid.MyReport("rptBridgingRujukanBPJS.jasper", "report", "::[ Surat Rujukan Keluar VClaim ]::",
                                " SELECT br.no_sep, bs.no_rawat, bs.nomr,bs.nama_pasien,br.tglRujukan, br.no_rujukan,br.ppkDirujuk, br.nm_ppkDirujuk, "
                                + "IF(br.jnsPelayanan='1','Rawat Inap','Rawat Jalan')  jenis, br.tipeRujukan,br.catatan, br.diagRujukan,br.nama_diagRujukan, "
                                + "br.poliRujukan,br.nama_poliRujukan,bs.no_kartu, IF(bs.jkel='L','Laki-Laki','Perempuan') jkel "
                                + "FROM bridging_sep bs LEFT JOIN bridging_rujukan_bpjs br ON br.no_rawat=bs.no_rawat "
                                + "WHERE br.no_rujukan='" + response.path("noRujukan").asText() + "'", param);
                        WindowCariRujukanVClaim.dispose();
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                    Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Rujukan Keluar dari VClaim','Simpan'");
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Peserta : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                    dispose();
                }
            }
        }
    }//GEN-LAST:event_BtnSimpan8ActionPerformed

    private void NoRujukanVclaimKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRujukanVclaimKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_NoRujukanVclaimKeyTyped

    private void NoRujukanVclaimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRujukanVclaimKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpan8ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSimpan8.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnSimpan8.requestFocus();
        }
    }//GEN-LAST:event_NoRujukanVclaimKeyPressed

    private void ppDetailSEPvclaimBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDetailSEPvclaimBtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tbSEP.getSelectedRow() != -1) {
            BPJSCekDetailSEP detail = new BPJSCekDetailSEP(null, false);
            detail.tampil(tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString());
            detail.setSize(720, internalFrame1.getHeight() - 40);
            detail.setLocationRelativeTo(internalFrame1);
            detail.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data SEP ...!!!!");
            tbSEP.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppDetailSEPvclaimBtnPrintActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSDataSEP dialog = new BPJSDataSEP(new javax.swing.JFrame(), true);
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
    private widget.ComboBox AsalRujukan;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnCloseIn7;
    private widget.Button BtnCloseIn8;
    private widget.Button BtnEdit;
    private widget.Button BtnGantiRujukan;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusProsedur;
    private widget.Button BtnHapusSekun;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar2;
    private widget.Button BtnKeluar3;
    private widget.Button BtnResetRujuk;
    private widget.Button BtnResetRujuk1;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan5;
    private widget.Button BtnSimpan6;
    private widget.Button BtnSimpan7;
    private widget.Button BtnSimpan8;
    private widget.Button BtnUpdatePulang;
    private widget.ComboBox COB;
    public widget.TextArea Catatan;
    private widget.TextBox Catatan1;
    private widget.CekBox ChkNoSEP;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Eksekutif;
    private widget.PanelBiasa FormInput1;
    private widget.TextBox JK;
    private widget.ComboBox JenisPelayanan;
    private widget.ComboBox JenisPelayanan1;
    private widget.TextBox JenisPeserta;
    private widget.ComboBox KasusKatarak;
    private widget.TextBox KdKab;
    private widget.TextBox KdKec;
    private widget.TextBox KdPPK;
    private widget.TextBox KdPenyakit;
    private widget.TextBox KdPenyakit1;
    private widget.TextBox KdPoli;
    private widget.TextBox KdPoli1;
    private widget.TextBox KdPpkRujukan;
    private widget.TextBox KdPpkRujukan1;
    private widget.TextBox KdProv;
    private widget.TextBox Kddpjp;
    private widget.TextBox KddpjpLayan;
    private widget.TextBox Ket;
    private widget.Label LCount;
    private widget.Label LCount1;
    private widget.Label LabKab;
    private widget.Label LabKec;
    private widget.Label LabKetkll;
    private widget.Label LabNoSup;
    private widget.Label LabProv;
    private widget.Label LabSup;
    private widget.Label LabTglkll;
    private widget.Label LabelKatarak;
    private widget.Label LabelKelas1;
    private widget.Label LabelPoli;
    private widget.Label LabelPoli2;
    private widget.Label LabelPoli3;
    private widget.ComboBox LakaLantas;
    private widget.TextBox LokasiLaka;
    private javax.swing.JMenuItem MnBackdate;
    private javax.swing.JMenuItem MnBackdate1;
    private javax.swing.JMenuItem MnFingerPrin;
    private javax.swing.JMenuItem MnFingerPrin1;
    private javax.swing.JMenuItem MnRekapSEPRalan;
    private javax.swing.JMenuItem MnRekapSEPRanap;
    private widget.TextBox NmDPJP;
    private widget.TextBox NmKab;
    private widget.TextBox NmKec;
    private widget.TextBox NmPPK;
    private widget.TextBox NmPenyakit;
    private widget.TextBox NmPenyakit1;
    private widget.TextBox NmPoli;
    private widget.TextBox NmPoli1;
    private widget.TextBox NmPpkRujukan;
    private widget.TextBox NmPpkRujukan1;
    private widget.TextBox NmProv;
    private widget.TextBox NoBalasan;
    private widget.TextBox NoKartu;
    private widget.TextBox NoRujukan;
    private widget.TextBox NoRujukanVclaim;
    private widget.TextBox NoSEP;
    private widget.TextBox NoSEPSuplesi;
    private widget.TextBox NoTelp;
    private javax.swing.JPopupMenu Popup;
    private javax.swing.JPopupMenu Popup1;
    private javax.swing.JPopupMenu Popup2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.TextBox Status;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    public javax.swing.JTabbedPane TabRawat;
    private javax.swing.JTabbedPane TabRujukan;
    private widget.Tanggal TanggalKejadian;
    private widget.Tanggal TanggalRujuk;
    private widget.Tanggal TanggalRujukKeluar;
    private widget.Tanggal TanggalSEP;
    private widget.TextBox TglLahir;
    private widget.ComboBox TipeRujukan;
    private javax.swing.JDialog WindowCariNoRujuk;
    private javax.swing.JDialog WindowCariRujukanVClaim;
    private javax.swing.JDialog WindowCariSEP;
    private javax.swing.JDialog WindowRujukanBiasa;
    private javax.swing.JDialog WindowRujukanKhusus;
    private javax.swing.JDialog WindowUpdatePulang;
    private widget.ComboBox asesmenPel;
    private widget.Button btnDPJP;
    private widget.Button btnDPJPlayan;
    private widget.Button btnDiagPrimer;
    private widget.Button btnDiagSekunder;
    private widget.Button btnDiagnosa;
    private widget.Button btnDiagnosa1;
    private widget.Button btnKab;
    private widget.Button btnKec;
    private widget.Button btnNaikKls;
    private widget.Button btnNoRujukan;
    private widget.Button btnNoRujukanInap;
    private widget.Button btnNoSurat;
    private widget.Button btnPPKRujukan;
    private widget.Button btnPPKRujukan1;
    private widget.Button btnPoli;
    private widget.Button btnPoli1;
    private widget.Button btnProsedur;
    private widget.Button btnProv;
    private widget.ComboBox cmbPembiayaan;
    private widget.ComboBox cmbSttsPlg;
    private widget.ComboBox flagPro;
    private widget.ComboBox hakKelas;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame12;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame14;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel10;
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
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
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
    private widget.Label jLabel56;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private widget.TextBox kdDiagPrimer;
    private widget.TextBox kdNaikKls;
    private widget.ComboBox kdPenunjang;
    private widget.TextBox kode_rujukanya;
    private widget.Label label_surat;
    private widget.TextBox nmDiagPrimer;
    private widget.TextBox nmKlsNaik;
    private widget.TextBox nmdpjpLayan;
    private widget.TextBox nmfaskes_keluar;
    private widget.Tanggal noLP;
    private widget.TextBox noRujukanKhusus;
    private widget.TextBox noSrtMati;
    private widget.TextBox noSurat;
    private widget.Label nosep;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelisi1;
    private widget.TextBox pngJwb;
    private javax.swing.JMenuItem ppAmbilRujukanBiasaVclaim;
    private javax.swing.JMenuItem ppAmbilSep;
    private javax.swing.JMenu ppAprovalSEP;
    private javax.swing.JMenuItem ppCekFingerPrin;
    private javax.swing.JMenuItem ppDataRujukanKeluar;
    private javax.swing.JMenuItem ppDetailSEPvclaim;
    private javax.swing.JMenu ppMembuatRujukan;
    private javax.swing.JMenu ppPengajuan;
    private javax.swing.JMenuItem ppProgramPRB;
    private javax.swing.JMenuItem ppPulang;
    private javax.swing.JMenuItem ppRencanaKontrol;
    private javax.swing.JMenuItem ppRencanaKontrollagi;
    private javax.swing.JMenuItem ppRencanaKontrollagi1;
    private javax.swing.JMenuItem ppRujukanBiasa;
    private javax.swing.JMenuItem ppRujukanKhusus;
    private javax.swing.JMenuItem ppSEP;
    private javax.swing.JMenuItem ppSEPinternal;
    private javax.swing.JMenuItem ppSPRI;
    private javax.swing.JMenuItem ppUpdateSEPdariVclaim;
    private widget.Label rujukanSEP;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollInput1;
    private widget.ScrollPane scrollInput2;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.TextBox sepRujuk;
    private widget.TextBox sepRujukKhusus;
    private widget.ComboBox suplesi;
    private widget.Table tbFaskes1;
    private widget.Table tbFaskes2;
    private widget.Table tbFaskes3;
    private widget.Table tbFaskes4;
    private widget.Table tbProsedur;
    public widget.Table tbRiwayat;
    public widget.Table tbSEP;
    private widget.Table tbdiagSekunder;
    private widget.Tanggal tglMati;
    private widget.TextBox tglPlgRuangan;
    private widget.Tanggal tglRencanaKunjungan;
    private widget.ComboBox tujuanKun;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        //menghapus data di tabel bridging_sep_backup 3 bulan mundur
        Sequel.queryu("delete from bridging_sep_backup where tglsep < DATE_FORMAT(date_sub(now(), interval 90 day),'%Y-%m-%d')");
        //----------------------------------------------------------------------
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT no_sep, tglsep, no_rawat, nomr, nama_pasien, no_rujukan, tglrujukan, asal_rujukan, "
                    + "nmppkrujukan, noskdp, nmdpdjp, kdpolitujuan, nmpolitujuan, ifnull(nmdpjpLayan,'') nmdpjpLayan, "
                    + "ifnull(tujuanKunjungan,'0. Normal') tujuanKunjungan, ifnull(assesmentPel,'') assesmentPel, ifnull(flagProcedur,'') flagProcedur, "
                    + "ifnull(kdPenunjang,'') kdPenunjang, nmdiagnosaawal, jnspelayanan, klsrawat, ifnull(klsRawatHak,klsrawat) klsRawatHak, "
                    + "ifnull(klsRawatNaik,'') klsRawatNaik, IFNULL(nmKelasNaiknya,'') nmnaikkls, catatan, "
                    + "USER, tanggal_lahir, peserta, jkel, no_kartu, IFNULL(STATUS,'') stts, tglpulang, notelep, "
                    + "eksekutif, cob, katarak, ifnull(pembiayaan,'') pembiayaan, ifnull(penanggungJawab,'') penanggungJawab, "
                    + "lakalantas, lokasilaka, if(tglkkl='0000-00-00','-',tglkkl) tglcelaka, keterangankkl, suplesi, no_sep_suplesi, kdprop, nmprop, kdkab, nmkab, kdkec, "
                    + "nmkec, urutan_sep, kdppkrujukan, kdppkpelayanan, nmppkpelayanan, kddpjp, ifnull(dpjpLayan,'') dpjpLayan, diagawal FROM bridging_sep where "
                    + " tglsep between ? and ? and no_sep like ? or "
                    + " tglsep between ? and ? and nomr like ? or "
                    + " tglsep between ? and ? and nama_pasien like ? or "
                    + " tglsep between ? and ? and nmppkrujukan like ? or "
                    + " tglsep between ? and ? and diagawal like ? or "
                    + " tglsep between ? and ? and nmdiagnosaawal like ? or "
                    + " tglsep between ? and ? and no_rawat like ? or "
                    + " tglsep between ? and ? and no_kartu like ? or "
                    + " tglsep between ? and ? and if(jnspelayanan='1','Ranap','Ralan') like ? or "
                    + " tglsep between ? and ? and nmpolitujuan like ? order by tglsep");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                ps.setString(27, "%" + TCari.getText().trim() + "%");                
                ps.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                ps.setString(30, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_sep"),
                        rs.getString("tglsep"),
                        rs.getString("no_rawat"),
                        rs.getString("nomr"),
                        rs.getString("nama_pasien"),
                        rs.getString("no_rujukan"),
                        rs.getString("tglrujukan"),
                        rs.getString("asal_rujukan"),
                        rs.getString("nmppkrujukan"),
                        rs.getString("noskdp"),
                        rs.getString("nmdpdjp"),
                        rs.getString("kdpolitujuan"),
                        rs.getString("nmpolitujuan"),
                        rs.getString("nmdpjpLayan"),
                        rs.getString("tujuanKunjungan"),
                        rs.getString("assesmentPel").
                                replaceAll("", "").
                                replaceAll("1", "1. Poli spesialis tidak tersedia pada hari sebelumnya").
                                replaceAll("2", "2. Jam Poli telah berakhir pada hari sebelumnya").
                                replaceAll("3", "3. Dokter Spesialis yang dimaksud tidak praktek pada hari sebelumnya").
                                replaceAll("4", "4. Atas Instruksi RS").
                                replaceAll("5", "5. Tujuan Kontrol"),
                        rs.getString("flagProcedur").
                                replaceAll("", "").
                                replaceAll("0", "0. Prosedur Tidak Berkelanjutan").
                                replaceAll("1", "1. Prosedur dan Terapi Berkelanjutan"),
                        rs.getString("kdPenunjang").
                                replaceAll("", "").
                                replaceAll("1", "1. Radioterapi").
                                replaceAll("2", "2. Kemoterapi").
                                replaceAll("3", "3. Rehabilitasi Medik").
                                replaceAll("4", "4. Rehabilitasi Psikososial").
                                replaceAll("5", "5. Transfusi Darah").
                                replaceAll("6", "6. Pelayanan Gigi").
                                replaceAll("7", "7. Laboratorium").
                                replaceAll("8", "8. USG").
                                replaceAll("9", "9. Farmasi").
                                replaceAll("10", "10. Lain-Lain").
                                replaceAll("11", "11. MRI").                                
                                replaceAll("12", "12. HEMODIALISA"),
                        rs.getString("nmdiagnosaawal"),
                        rs.getString("jnspelayanan").replaceAll("1", "1. Ranap").replaceAll("2", "2. Ralan"),
                        rs.getString("klsrawat").
                                replaceAll("1", "1. Kelas 1").
                                replaceAll("2", "2. Kelas 2").
                                replaceAll("3", "3. Kelas 3"),
                        rs.getString("klsRawatHak"),
                        rs.getString("klsRawatNaik"),
                        rs.getString("nmnaikkls"),
                        rs.getString("catatan"),
                        rs.getString("USER"),
                        rs.getString("tanggal_lahir"),
                        rs.getString("peserta"),
                        rs.getString("jkel"),
                        rs.getString("no_kartu"),
                        rs.getString("stts"),
                        rs.getString("tglpulang"),
                        rs.getString("notelep"),
                        rs.getString("eksekutif"),
                        rs.getString("cob"),
                        rs.getString("katarak"),
                        rs.getString("pembiayaan").
                                replaceAll("", "").
                                replaceAll("1", "1. Pribadi").
                                replaceAll("2", "2. Pemberi Kerja").
                                replaceAll("3", "3. Asuransi Kesehatan Tambahan"),
                        rs.getString("penanggungJawab"),
                        rs.getString("lakalantas").
                                replaceAll("0", "0. Bukan Kecelakaan lalu lintas [BKLL]").
                                replaceAll("1", "1. KLL dan Bukan Kecelakaan Kerja [BKK]").
                                replaceAll("2", "2. KLL dan KK").
                                replaceAll("3", "3. KK"),
                        rs.getString("lokasilaka"),
                        rs.getString("tglcelaka"),
                        rs.getString("keterangankkl"),
                        rs.getString("suplesi"),
                        rs.getString("no_sep_suplesi"),
                        rs.getString("kdprop"),
                        rs.getString("nmprop"),
                        rs.getString("kdkab"),
                        rs.getString("nmkab"),
                        rs.getString("kdkec"),
                        rs.getString("nmkec"),
                        rs.getString("urutan_sep"),
                        rs.getString("kdppkrujukan"),
                        rs.getString("kdppkpelayanan"),
                        rs.getString("nmppkpelayanan"),
                        rs.getString("kddpjp"),
                        rs.getString("dpjpLayan"),
                        rs.getString("diagawal")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
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

    private void isRawat() {
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRM, TNoRw.getText());
        Catatan.setText("-");
    }

    public void emptTeks(){
        TNoRw.setText("");
        TPasien.setText("");
        TanggalSEP.setDate(new Date());
        TanggalRujuk.setDate(new Date());
        TglLahir.setText("");
        NoKartu.setText("");
        JenisPeserta.setText("");
        Status.setText("");
        JK.setText("");
        NoRujukan.setText("");
        KdPpkRujukan.setText("");
        NmPpkRujukan.setText("");
        JenisPelayanan.setSelectedIndex(1);
        Catatan.setText("");
        KdPenyakit.setText("");
        NmPenyakit.setText("");
        KdPoli.setText("");
        NmPoli.setText("");
        hakKelas.setSelectedIndex(0);
        LakaLantas.setSelectedIndex(0);
        LokasiLaka.setText("-");
        TNoRM.setText("");
        NoTelp.requestFocus();
        NoTelp.setText("0");
        rujukanSEP.setText("- belum terisi -");
        
        noSurat.setText("");
        Kddpjp.setText("");
        NmDPJP.setText("");
        KasusKatarak.setSelectedIndex(0);
        Ket.setText("");
        TanggalKejadian.setDate(new Date());
        KdProv.setText("");
        NmProv.setText("");
        KdKab.setText("");
        NmKab.setText("");
        KdKec.setText("");
        NmKec.setText("");
        suplesi.setSelectedIndex(0);
        NoSEPSuplesi.setText("");
        kode_rujukanya.setText("");
        nmfaskes_keluar.setText("");
        
        kdNaikKls.setText("");        
        nmKlsNaik.setText("");
        cmbPembiayaan.setSelectedIndex(0);
        pngJwb.setText("");
        tujuanKun.setSelectedIndex(0);
        flagPro.setSelectedIndex(0);
        kdPenunjang.setSelectedIndex(0);
        asesmenPel.setSelectedIndex(0);
        KddpjpLayan.setText("");
        nmdpjpLayan.setText("");
        cmbSttsPlg.setSelectedIndex(0);
        noSrtMati.setText("");
        kdSttsPlg = "";
        desSttsPlg = "";
        tglJiun = "";
        noLPJiun = "";
        ChkNoSEP.setSelected(false);
        NoSEP.setText("");
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(rujuk_masuk.no_rawat,4),signed)),0) from reg_periksa inner join rujuk_masuk on reg_periksa.no_rawat=rujuk_masuk.no_rawat where reg_periksa.tgl_registrasi='"+Valid.SetTgl(TanggalSEP.getSelectedItem()+"")+"' ","BR/"+dateformat.format(TanggalSEP.getDate())+"/",4,NoBalasan);
    }

    public void setNoRm(String norwt, String status, String kdpoli, String namapoli) {
        TNoRw.setText(norwt);        
        KdPoli.setText(kdpoli);
        NmPoli.setText(namapoli);
        LakaLantas.setSelectedIndex(0);
        Valid.SetTgl(TanggalSEP, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        Valid.SetTgl(TanggalRujuk, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        JenisPelayanan.setSelectedItem(status);
        JenisPelayananItemStateChanged(null);
        isRawat();
        TCari.setText(TNoRM.getText());
    }

    public void isCek(){
        BtnSimpan.setEnabled(akses.getbpjs_sep());
//        BtnHapus.setEnabled(var.getbpjs_sep());
        BtnEdit.setEnabled(akses.getbpjs_sep());
        ppSEPinternal.setEnabled(akses.getbpjs_sep());
        ppPengajuan.setEnabled(akses.getbpjs_sep());
        ppAprovalSEP.setEnabled(akses.getbpjs_sep());
        ppProgramPRB.setEnabled(akses.getbpjs_sep());
        ppAmbilSep.setEnabled(akses.getbpjs_sep());
        ppSPRI.setEnabled(akses.getSPRIJKN());
        ppRencanaKontrol.setEnabled(akses.getRencanaKontrolJKN());
        ppPulang.setEnabled(akses.getbpjs_sep());
        ppSEP.setEnabled(akses.getbpjs_sep());
        ppUpdateSEPdariVclaim.setEnabled(akses.getbpjs_sep());
        ppRujukanBiasa.setEnabled(akses.getbpjs_sep());
        ppMembuatRujukan.setEnabled(akses.getbpjs_sep());        
        ppDataRujukanKeluar.setEnabled(akses.getbpjs_sep());
        
        if (akses.getkode().equals("Admin Utama") || akses.getHapusSEP() == true) {
            BtnHapus.setEnabled(true);
        } else {
            BtnHapus.setEnabled(false);
        }
    }

    private void getData() {
        tglkkl = "";
        tglkkl_ok = "";
        SEPkontrol = "";
        
        if (tbSEP.getSelectedRow() != -1) {
            sepRujuk.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString());
            SEPkontrol = sepRujuk.getText();
            Valid.SetTgl(TanggalSEP, tbSEP.getValueAt(tbSEP.getSelectedRow(), 1).toString());
            Valid.SetTgl(TanggalRujukKeluar, tbSEP.getValueAt(tbSEP.getSelectedRow(), 1).toString());
            TNoRw.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 2).toString());
            TNoRM.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 3).toString());
            TPasien.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 4).toString());
            NoRujukan.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 5).toString());
            Valid.SetTgl(TanggalRujuk, tbSEP.getValueAt(tbSEP.getSelectedRow(), 6).toString());
            AsalRujukan.setSelectedItem(tbSEP.getValueAt(tbSEP.getSelectedRow(), 7).toString());
            NmPpkRujukan.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 8).toString());
            rujukanSEP.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 8).toString());
            noSurat.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 9).toString());
            NmDPJP.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 10).toString());
            KdPoli.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 11).toString());
            NmPoli.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 12).toString());
            nmdpjpLayan.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 13).toString());
            tujuanKun.setSelectedItem(tbSEP.getValueAt(tbSEP.getSelectedRow(), 14).toString());
            asesmenPel.setSelectedItem(tbSEP.getValueAt(tbSEP.getSelectedRow(), 15).toString());
            flagPro.setSelectedItem(tbSEP.getValueAt(tbSEP.getSelectedRow(), 16).toString());
            kdPenunjang.setSelectedItem(tbSEP.getValueAt(tbSEP.getSelectedRow(), 17).toString());
            NmPenyakit.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 18).toString().replaceAll(KdPenyakit.getText(), ""));
            NmPenyakit1.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 18).toString().replaceAll(KdPenyakit.getText(), ""));
            JenisPelayanan.setSelectedItem(tbSEP.getValueAt(tbSEP.getSelectedRow(), 19).toString());
            hakKelas.setSelectedItem(tbSEP.getValueAt(tbSEP.getSelectedRow(), 20).toString());
            kdNaikKls.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 22).toString());
            nmKlsNaik.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 23).toString());
            Catatan.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 24).toString());
            TglLahir.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 26).toString());
            JenisPeserta.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 27).toString());            
            JK.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 28).toString().replaceAll("L", "Laki-laki").replaceAll("P", "Perempuan"));
            NoKartu.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 29).toString());
            Status.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 30).toString());
            NoTelp.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 32).toString());
            Eksekutif.setSelectedItem(tbSEP.getValueAt(tbSEP.getSelectedRow(), 33).toString());
            COB.setSelectedItem(tbSEP.getValueAt(tbSEP.getSelectedRow(), 34).toString());
            KasusKatarak.setSelectedItem(tbSEP.getValueAt(tbSEP.getSelectedRow(), 35).toString());
            cmbPembiayaan.setSelectedItem(tbSEP.getValueAt(tbSEP.getSelectedRow(), 36).toString());
            pngJwb.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 37).toString());
            LakaLantas.setSelectedItem(tbSEP.getValueAt(tbSEP.getSelectedRow(), 38).toString());
            LokasiLaka.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 39).toString());
            tglkkl = tbSEP.getValueAt(tbSEP.getSelectedRow(), 40).toString();
            Ket.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 41).toString());
            suplesi.setSelectedItem(tbSEP.getValueAt(tbSEP.getSelectedRow(), 42).toString());
            NoSEPSuplesi.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 43).toString());
            KdProv.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 44).toString());
            NmProv.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 45).toString());
            KdKab.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 46).toString());
            NmKab.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 47).toString());
            KdKec.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 48).toString());
            NmKec.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 49).toString());
            KdPpkRujukan.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 51).toString());
            KdPPK.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 52).toString());
            NmPPK.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 53).toString());
            Kddpjp.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 54).toString());
            KddpjpLayan.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 55).toString());
            KdPenyakit.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 56).toString());
            KdPenyakit1.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 56).toString());            
            
            Sequel.cariIsi("select kd_rujukan from rujuk_masuk where no_rawat=? ", kode_rujukanya, TNoRw.getText());
//            jkel = tbSEP.getValueAt(tbSEP.getSelectedRow(), 28).toString();
//            Sequel.cariIsi("select perujuk from rujuk_masuk where no_rawat=? ", NmPpkRujukan, TNoRw.getText());
//            NmPpkRujukan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());            
//            LakaLantas.setSelectedItem(tbSEP.getValueAt(tbSEP.getSelectedRow(), 18).toString());            
//            jkel = Sequel.cariIsi("select jkel from bridging_sep where no_sep='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "'");
            
            //tanggal kejadian untuk dikirim ke ws trusk mark
            if (tglkkl.equals("-")) {
                tglkkl_ok = "";
                TanggalKejadian.setDate(new Date());
            } else {
                tglkkl_ok = tglkkl;
                Valid.SetTgl(TanggalKejadian, tglkkl);
            }
        }
    }
    
    private void getDataKunjungan() {
        SEPkontrol = "";        
        if (tbRiwayat.getSelectedRow() != -1) {
            SEPkontrol = tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 6).toString();            
        }
    }

    public static class HttpEntityEnclosingDeleteRequest extends HttpEntityEnclosingRequestBase {
        public HttpEntityEnclosingDeleteRequest(final URI uri) {
            super();
            setURI(uri);
        }

        @Override
        public String getMethod() {
            return "DELETE";
        }
    }

    public void bodyWithDeleteRequest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers= {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {return null;}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
            }
        };
        sslContext.init(null,trustManagers , new SecureRandom());
        SSLSocketFactory sslFactory=new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme=new Scheme("https",443,sslFactory);
    
        HttpComponentsClientHttpRequestFactory factory=new HttpComponentsClientHttpRequestFactory(){
            @Override
            protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
                if (HttpMethod.DELETE == httpMethod) {
                    return new HttpEntityEnclosingDeleteRequest(uri);
                }
                return super.createHttpUriRequest(httpMethod, uri);
            }
        };
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        restTemplate.setRequestFactory(factory);
        
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
            URL = prop.getProperty("URLAPIBPJS") + "/SEP/2.0/delete";

            requestJson = "{"
                    + "\"request\": {"
                    + "\"t_sep\": {"
                    + "\"noSep\":\"" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "\","
                    + "\"user\":\"" + user + "\""
                    + "}"
                    + "}"
                    + "}";

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(restTemplate.exchange(URL, HttpMethod.DELETE, new HttpEntity<String>(requestJson, headers), String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());

            if (nameNode.path("code").asText().equals("200")) {
                Sequel.meghapus("bridging_sep", "no_sep", tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString());
                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
            }
        } catch (Exception e) {   
            System.out.println("Notif : "+e);
        }
    }

    private void insertSEP(){
        try {
            pembi = "";
            kdpnjg = "";
            flag = "";
            asesmen = "";
            jkel = "";
            cekSEP = 0;
            
            if (JK.getText().equals("Laki-laki")) {
                jkel = "L";
            } else {
                jkel = "P";
            }

            tglkkl = "0000-00-00";
            if (LakaLantas.getSelectedIndex() == 1 || LakaLantas.getSelectedIndex() == 2 || LakaLantas.getSelectedIndex() == 3) {
                tglkkl = Valid.SetTgl(TanggalKejadian.getSelectedItem() + "");
            }
            
            //-----------------------------------------------------------------------------------------------            
            if (cmbPembiayaan.getSelectedIndex() == 0) {
                pembi = "";
            } else {
                pembi = cmbPembiayaan.getSelectedItem().toString().substring(0, 1);
            }
            
            if (flagPro.getSelectedIndex() == 0) {
                flag = "";
            } else {
                flag = flagPro.getSelectedItem().toString().substring(0, 1);
            }
            
            if (kdPenunjang.getSelectedIndex() == 10 || kdPenunjang.getSelectedIndex() == 11 || kdPenunjang.getSelectedIndex() == 12) {
                kdpnjg = kdPenunjang.getSelectedItem().toString().substring(0, 2);
            } else if (kdPenunjang.getSelectedIndex() == 0) {
                kdpnjg = "";
            } else {
                kdpnjg = kdPenunjang.getSelectedItem().toString().substring(0, 1);
            }
            
            if (asesmenPel.getSelectedIndex() == 0) {
                asesmen = "";
            } else {
                asesmen = asesmenPel.getSelectedItem().toString().substring(0, 1);
            }
            
            if (JenisPelayanan.getSelectedIndex() == 0) {
                KddpjpLayan.setText("");
                nmdpjpLayan.setText("");
            }

            //-----------------------------------------------------------------------------------------------
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
            URL = prop.getProperty("URLAPIBPJS") + "/SEP/2.0/insert";
                        
            requestJson = "{"
                    + "\"request\":{"
                    + "\"t_sep\":{"                    
                    + "\"noKartu\":\"" + NoKartu.getText() + "\","
                    + "\"tglSep\":\"" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "\","
                    + "\"ppkPelayanan\":\"" + KdPPK.getText() + "\","
                    + "\"jnsPelayanan\":\"" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "\","
//ini yang baru -----------
                    + "\"klsRawat\":{"
                    + "\"klsRawatHak\":\"" + hakKelas.getSelectedItem().toString().substring(0, 1) + "\","
                    + "\"klsRawatNaik\":\"" + kdNaikKls.getText() + "\","
                    + "\"pembiayaan\":\"" + pembi + "\","
                    + "\"penanggungJawab\":\"" + pngJwb.getText() + "\""
                    + "},"
//sampai sini ------------
                    + "\"noMR\":\"" + TNoRM.getText() + "\","
                    + "\"rujukan\":{"
                    + "\"asalRujukan\":\"" + AsalRujukan.getSelectedItem().toString().substring(0, 1) + "\","
                    + "\"tglRujukan\":\"" + Valid.SetTgl(TanggalRujuk.getSelectedItem() + "") + "\","
                    + "\"noRujukan\":\"" + NoRujukan.getText() + "\","
                    + "\"ppkRujukan\":\"" + KdPpkRujukan.getText() + "\""
                    + "},"
                    + "\"catatan\":\"" + Catatan.getText() + "\","
                    + "\"diagAwal\":\"" + KdPenyakit.getText() + "\","
                    + "\"poli\":{"
                    + "\"tujuan\": \"" + KdPoli.getText() + "\","
                    + "\"eksekutif\": \"" + Eksekutif.getSelectedItem().toString().substring(0, 1) + "\""
                    + "},"
                    + "\"cob\":{"
                    + "\"cob\": \"" + COB.getSelectedItem().toString().substring(0, 1) + "\""
                    + "},"
                    + "\"katarak\":{"
                    + "\"katarak\":\"" + KasusKatarak.getSelectedItem().toString().substring(0, 1) + "\""
                    + "},"	                    
                    + "\"jaminan\":{"
                    + "\"lakaLantas\":\"" + LakaLantas.getSelectedItem().toString().substring(0, 1) + "\","
                    + "\"penjamin\":{"                    
                    + "\"tglKejadian\":\"" + tglkkl + "\","
                    + "\"keterangan\":\"" + Ket.getText() + "\","
                    + "\"suplesi\":{"
                    + "\"suplesi\":\"" + suplesi.getSelectedItem().toString().substring(0, 1) + "\","
                    + "\"noSepSuplesi\":\"" + NoSEPSuplesi.getText() + "\","
                    + "\"lokasiLaka\":{"
                    + "\"kdPropinsi\":\"" + KdProv.getText() + "\","
                    + "\"kdKabupaten\":\"" + KdKab.getText() + "\","
                    + "\"kdKecamatan\":\"" + KdKec.getText() + "\""
                    + "}"
                    + "}"
                    + "}"
                    + "},"
//ini yang baru -----------
                    + "\"tujuanKunj\":\"" + tujuanKun.getSelectedItem().toString().substring(0, 1) + "\","
                    + "\"flagProcedure\":\"" + flag + "\","
                    + "\"kdPenunjang\":\"" + kdpnjg + "\","
                    + "\"assesmentPel\":\"" + asesmen + "\","
//sampai sini ------------
                    + "\"skdp\":{"
                    + "\"noSurat\":\"" + noSurat.getText() + "\","
                    + "\"kodeDPJP\":\"" + Kddpjp.getText() + "\""
                    + "},"                    
//ini yang baru -----------
                    + "\"dpjpLayan\": \"" + KddpjpLayan.getText() + "\","
//sampai sini -------------
                    + "\"noTelp\": \"" + NoTelp.getText() + "\","
                    + "\"user\":\"" + user + "\""
                    + "}"
                    + "}"
                    + "}";
            
            simpanBackupSEPAwal();
            //jeda 3 detik
            Thread.sleep(3000);
            System.out.println("Pesan SEP Backup : Berhasil menyimpan data ketabel bridging_sep_backup (Waktu : " + Sequel.cariIsi("select date_format(now(),'%d-%m-%Y %H:%i:%s')") + ") ...!!!");
            
            System.out.println("ini mengirim : " + requestJson);
            HttpEntity requestEntity = new HttpEntity(requestJson, headers);
            ObjectMapper mapper = new ObjectMapper();            
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());            
            JsonNode nameNode = root.path("metaData");
            respons = nameNode.toString();
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());
            JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());
            
//ini yang baru -----------            
//            JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("sep").path("noSep");
//sampai sini -------------
//            System.out.println("ini responya : " + mapper.readTree(api.Decrypt(root.path("response").asText(), utc)));

            if (nameNode.path("code").asText().equals("200")) {
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("sep").path("noSep");
                System.out.println("ini responya : " + mapper.readTree(api.Decrypt(root.path("response").asText(), utc)));
                System.out.println("No. SEP : " + response.asText());
                cekSEP = Sequel.cariInteger("select count(-1) from bridging_sep where no_sep='" + response.asText() + "' and urutan_sep='1'");
                if (cekSEP > 0) {
                    if (Sequel.menyimpantf("bridging_sep", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "SEP", 59, new String[]{
                        response.asText(), TNoRw.getText(), Valid.SetTgl(TanggalSEP.getSelectedItem() + ""), Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""),
                        NoRujukan.getText(), KdPpkRujukan.getText(), rujukanSEP.getText(), KdPPK.getText(), NmPPK.getText(),
                        JenisPelayanan.getSelectedItem().toString().substring(0, 1), Catatan.getText(), KdPenyakit.getText(),
                        NmPenyakit.getText(), KdPoli.getText(), NmPoli.getText(), hakKelas.getSelectedItem().toString().substring(0, 1),
                        LakaLantas.getSelectedItem().toString().substring(0, 1), LokasiLaka.getText(), user,
                        TNoRM.getText(), TPasien.getText(), TglLahir.getText(), JenisPeserta.getText(), jkel, NoKartu.getText(),
                        "0000-00-00 00:00:00", AsalRujukan.getSelectedItem().toString(), Eksekutif.getSelectedItem().toString(),
                        COB.getSelectedItem().toString(), "", NoTelp.getText(), KasusKatarak.getSelectedItem().toString(),
                        tglkkl, Ket.getText(), suplesi.getSelectedItem().toString(),
                        NoSEPSuplesi.getText(), KdProv.getText(), NmProv.getText(), KdKab.getText(), NmKab.getText(),
                        KdKec.getText(), NmKec.getText(), noSurat.getText(), Kddpjp.getText(), NmDPJP.getText(), "", hakKelas.getSelectedItem().toString().substring(0, 1),
                        kdNaikKls.getText(), pembi, pngJwb.getText(), tujuanKun.getSelectedItem().toString(), flag, kdpnjg, asesmen, KddpjpLayan.getText(), nmdpjpLayan.getText(),
                        Sequel.cariIsi("select ifnull(MAX(urutan_sep)+1,1) from bridging_sep where no_sep='" + response.asText() + "'"), nmKlsNaik.getText(), Status.getText()
                    }) == true) {
                        Sequel.menyimpan("rujuk_masuk", "?,?,?,?,?,?,?,?,?,?,?", 11, new String[]{
                            TNoRw.getText(), NmPpkRujukan.getText(), "-", NoRujukan.getText(), "0", NmPpkRujukan.getText(), KdPenyakit.getText(), "-",
                            "-", NoBalasan.getText(), kode_rujukanya.getText()
                        });

                        Sequel.mengedit("kelengkapan_booking_sep_bpjs", "no_rawat='" + TNoRw.getText() + "'", "status_cetak_sep='SUDAH' ");
                    }

                    //Simpan Ke tabel bridging_sep_backup
                    if (Sequel.menyimpantf("bridging_sep_backup", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "SEP", 59, new String[]{
                        response.asText(), TNoRw.getText(), Valid.SetTgl(TanggalSEP.getSelectedItem() + ""), Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""),
                        NoRujukan.getText(), KdPpkRujukan.getText(), rujukanSEP.getText(), KdPPK.getText(), NmPPK.getText(),
                        JenisPelayanan.getSelectedItem().toString().substring(0, 1), Catatan.getText(), KdPenyakit.getText(),
                        NmPenyakit.getText(), KdPoli.getText(), NmPoli.getText(), hakKelas.getSelectedItem().toString().substring(0, 1),
                        LakaLantas.getSelectedItem().toString().substring(0, 1), LokasiLaka.getText(), user,
                        TNoRM.getText(), TPasien.getText(), TglLahir.getText(), JenisPeserta.getText(), jkel, NoKartu.getText(),
                        "0000-00-00 00:00:00", AsalRujukan.getSelectedItem().toString(), Eksekutif.getSelectedItem().toString(),
                        COB.getSelectedItem().toString(), "", NoTelp.getText(), nameNode.path("code").asText(), nameNode.path("message").asText(),
                        KasusKatarak.getSelectedItem().toString().substring(0, 1), tglkkl, Ket.getText(), suplesi.getSelectedItem().toString(),
                        NoSEPSuplesi.getText(), KdProv.getText(), NmProv.getText(), KdKab.getText(), NmKab.getText(),
                        KdKec.getText(), NmKec.getText(), noSurat.getText(), Kddpjp.getText(), NmDPJP.getText(), "", hakKelas.getSelectedItem().toString().substring(0, 1),
                        kdNaikKls.getText(), pembi, pngJwb.getText(), tujuanKun.getSelectedItem().toString(), flag, kdpnjg, asesmen, KddpjpLayan.getText(), api.Decrypt(root.path("response").asText(), utc)
                    }) == false) {
                        Sequel.menyimpantf("bridging_sep_backup", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "SEP", 59, new String[]{
                            response.asText(), TNoRw.getText(), Valid.SetTgl(TanggalSEP.getSelectedItem() + ""), Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""),
                            NoRujukan.getText(), KdPpkRujukan.getText(), rujukanSEP.getText(), KdPPK.getText(), NmPPK.getText(),
                            JenisPelayanan.getSelectedItem().toString().substring(0, 1), Catatan.getText(), KdPenyakit.getText(),
                            NmPenyakit.getText(), KdPoli.getText(), NmPoli.getText(), hakKelas.getSelectedItem().toString().substring(0, 1),
                            LakaLantas.getSelectedItem().toString().substring(0, 1), LokasiLaka.getText(), user,
                            TNoRM.getText(), TPasien.getText(), TglLahir.getText(), JenisPeserta.getText(), jkel, NoKartu.getText(),
                            "0000-00-00 00:00:00", AsalRujukan.getSelectedItem().toString(), Eksekutif.getSelectedItem().toString(),
                            COB.getSelectedItem().toString(), "", NoTelp.getText(), nameNode.path("code").asText(), nameNode.path("message").asText(),
                            KasusKatarak.getSelectedItem().toString().substring(0, 1), tglkkl, Ket.getText(), suplesi.getSelectedItem().toString(),
                            NoSEPSuplesi.getText(), KdProv.getText(), NmProv.getText(), KdKab.getText(), NmKab.getText(),
                            KdKec.getText(), NmKec.getText(), noSurat.getText(), Kddpjp.getText(), NmDPJP.getText(), "", hakKelas.getSelectedItem().toString().substring(0, 1),
                            kdNaikKls.getText(), pembi, pngJwb.getText(), tujuanKun.getSelectedItem().toString(), flag, kdpnjg, asesmen, KddpjpLayan.getText(), nmdpjpLayan.getText(),api.Decrypt(root.path("response").asText(), utc)
                        });
                    }
                    emptTeks();
                    tampil();

                } else {
                    if (Sequel.menyimpantf("bridging_sep", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "SEP", 59, new String[]{
                        response.asText(), TNoRw.getText(), Valid.SetTgl(TanggalSEP.getSelectedItem() + ""), Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""),
                        NoRujukan.getText(), KdPpkRujukan.getText(), rujukanSEP.getText(), KdPPK.getText(), NmPPK.getText(),
                        JenisPelayanan.getSelectedItem().toString().substring(0, 1), Catatan.getText(), KdPenyakit.getText(),
                        NmPenyakit.getText(), KdPoli.getText(), NmPoli.getText(), hakKelas.getSelectedItem().toString().substring(0, 1),
                        LakaLantas.getSelectedItem().toString().substring(0, 1), LokasiLaka.getText(), user,
                        TNoRM.getText(), TPasien.getText(), TglLahir.getText(), JenisPeserta.getText(), jkel, NoKartu.getText(),
                        "0000-00-00 00:00:00", AsalRujukan.getSelectedItem().toString(), Eksekutif.getSelectedItem().toString(),
                        COB.getSelectedItem().toString(), "", NoTelp.getText(), KasusKatarak.getSelectedItem().toString(),
                        tglkkl, Ket.getText(), suplesi.getSelectedItem().toString(),
                        NoSEPSuplesi.getText(), KdProv.getText(), NmProv.getText(), KdKab.getText(), NmKab.getText(),
                        KdKec.getText(), NmKec.getText(), noSurat.getText(), Kddpjp.getText(), NmDPJP.getText(), "", hakKelas.getSelectedItem().toString().substring(0, 1),
                        kdNaikKls.getText(), pembi, pngJwb.getText(), tujuanKun.getSelectedItem().toString(), flag, kdpnjg, asesmen, 
                        KddpjpLayan.getText(), nmdpjpLayan.getText(), "1", nmKlsNaik.getText(), Status.getText()
                    }) == true) {
                        Sequel.menyimpan("rujuk_masuk", "?,?,?,?,?,?,?,?,?,?,?", 11, new String[]{
                            TNoRw.getText(), NmPpkRujukan.getText(), "-", NoRujukan.getText(), "0", NmPpkRujukan.getText(), KdPenyakit.getText(), "-",
                            "-", NoBalasan.getText(), kode_rujukanya.getText()
                        });

                        Sequel.mengedit("kelengkapan_booking_sep_bpjs", "no_rawat='" + TNoRw.getText() + "'", "status_cetak_sep='SUDAH' ");
                    }
                    
                    //Simpan Ke tabel bridging_sep_backup
                    if (Sequel.menyimpantf("bridging_sep_backup", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "SEP", 59, new String[]{
                        response.asText(), TNoRw.getText(), Valid.SetTgl(TanggalSEP.getSelectedItem() + ""), Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""),
                        NoRujukan.getText(), KdPpkRujukan.getText(), rujukanSEP.getText(), KdPPK.getText(), NmPPK.getText(),
                        JenisPelayanan.getSelectedItem().toString().substring(0, 1), Catatan.getText(), KdPenyakit.getText(),
                        NmPenyakit.getText(), KdPoli.getText(), NmPoli.getText(), hakKelas.getSelectedItem().toString().substring(0, 1),
                        LakaLantas.getSelectedItem().toString().substring(0, 1), LokasiLaka.getText(), user,
                        TNoRM.getText(), TPasien.getText(), TglLahir.getText(), JenisPeserta.getText(), jkel, NoKartu.getText(),
                        "0000-00-00 00:00:00", AsalRujukan.getSelectedItem().toString(), Eksekutif.getSelectedItem().toString(),
                        COB.getSelectedItem().toString(), "", NoTelp.getText(), nameNode.path("code").asText(), nameNode.path("message").asText(),
                        KasusKatarak.getSelectedItem().toString().substring(0, 1), tglkkl, Ket.getText(), suplesi.getSelectedItem().toString(),
                        NoSEPSuplesi.getText(), KdProv.getText(), NmProv.getText(), KdKab.getText(), NmKab.getText(),
                        KdKec.getText(), NmKec.getText(), noSurat.getText(), Kddpjp.getText(), NmDPJP.getText(), "", hakKelas.getSelectedItem().toString().substring(0, 1),
                        kdNaikKls.getText(), pembi, pngJwb.getText(), tujuanKun.getSelectedItem().toString(), flag, kdpnjg, asesmen, KddpjpLayan.getText(), nmdpjpLayan.getText(),api.Decrypt(root.path("response").asText(), utc)
                    }) == false) {
                        Sequel.menyimpantf("bridging_sep_backup", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "SEP", 59, new String[]{
                            response.asText(), TNoRw.getText(), Valid.SetTgl(TanggalSEP.getSelectedItem() + ""), Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""),
                            NoRujukan.getText(), KdPpkRujukan.getText(), rujukanSEP.getText(), KdPPK.getText(), NmPPK.getText(),
                            JenisPelayanan.getSelectedItem().toString().substring(0, 1), Catatan.getText(), KdPenyakit.getText(),
                            NmPenyakit.getText(), KdPoli.getText(), NmPoli.getText(), hakKelas.getSelectedItem().toString().substring(0, 1),
                            LakaLantas.getSelectedItem().toString().substring(0, 1), LokasiLaka.getText(), user,
                            TNoRM.getText(), TPasien.getText(), TglLahir.getText(), JenisPeserta.getText(), jkel, NoKartu.getText(),
                            "0000-00-00 00:00:00", AsalRujukan.getSelectedItem().toString(), Eksekutif.getSelectedItem().toString(),
                            COB.getSelectedItem().toString(), "", NoTelp.getText(), nameNode.path("code").asText(), nameNode.path("message").asText(),
                            KasusKatarak.getSelectedItem().toString().substring(0, 1), tglkkl, Ket.getText(), suplesi.getSelectedItem().toString(),
                            NoSEPSuplesi.getText(), KdProv.getText(), NmProv.getText(), KdKab.getText(), NmKab.getText(),
                            KdKec.getText(), NmKec.getText(), noSurat.getText(), Kddpjp.getText(), NmDPJP.getText(), "", hakKelas.getSelectedItem().toString().substring(0, 1),
                            kdNaikKls.getText(), pembi, pngJwb.getText(), tujuanKun.getSelectedItem().toString(), flag, kdpnjg, asesmen, KddpjpLayan.getText(), nmdpjpLayan.getText(),api.Decrypt(root.path("response").asText(), utc)
                        });
                    }
                    emptTeks();
                    tampil();
                }

//                     if(JenisPelayanan.getSelectedIndex()==1){
//                        try {
//                            HttpHeaders headers2 = new HttpHeaders();
//                            headers2.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//                            headers2.add("X-Cons-ID",prop.getProperty("CONSIDAPIBPJS"));
//                            utc=String.valueOf(api.GetUTCdatetimeAsString());
//                            headers2.add("X-Timestamp", utc);
//                            headers2.add("X-Signature",api.getHmac(utc));
//                            URL = prop.getProperty("URLAPIBPJS")+"/Sep/updtglplg";
//                            
//                            requestJson ="{" +
//                                          "\"request\":" +
//                                             "{" +
//                                                "\"t_sep\":" +
//                                                   "{" +
//                                                    "\"noSep\":\""+response.asText()+"\"," +
//                                                    "\"tglPulang\":\""+Valid.SetTgl(TanggalSEP.getSelectedItem()+"")+"\"," +
//                                                    "\"user\":\""+user+"\"" +
//                                                   "}" +
//                                             "}" +
//                                         "}";
//                            HttpEntity requestEntity2 = new HttpEntity(requestJson,headers2);
//                            ObjectMapper mapper2 = new ObjectMapper();
//                            JsonNode root2 = mapper2.readTree(api.getRest().exchange(URL, HttpMethod.PUT, requestEntity2, String.class).getBody());
//                            JsonNode nameNode2 = root2.path("metaData");
//                            System.out.println("code : "+nameNode2.path("code").asText());
//                            System.out.println("message : "+nameNode2.path("message").asText());
//                            JsonNode response2 = root2.path("response");
//                            if(nameNode2.path("code").asText().equals("200")){
//                                Sequel.mengedit("bridging_sep","no_sep=?","tglpulang=?",2,new String[]{                             
//                                     Valid.SetTgl(TanggalSEP.getSelectedItem()+""),
//                                     response.asText()
//                                });
//                                emptTeks();
//                                tampil();
//                            }else{
//                                JOptionPane.showMessageDialog(null,nameNode2.path("message").asText());
//                            }
//                        }catch (Exception ex) {
//                            System.out.println("Notifikasi Bridging : "+ex);
//                            if(ex.toString().contains("UnknownHostException")){
//                                JOptionPane.showMessageDialog(null,"Koneksi ke server BPJS terputus...!");
//                            }
//                        }
//                     }
            } else {
                //Simpan Ke tabel bridging_sep_backup jika gagal membuat sep
                if (Sequel.menyimpantf("bridging_sep_backup", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "SEP", 59, new String[]{
                    "-", TNoRw.getText(), Valid.SetTgl(TanggalSEP.getSelectedItem() + ""), Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""),
                    NoRujukan.getText(), KdPpkRujukan.getText(), rujukanSEP.getText(), KdPPK.getText(), NmPPK.getText(),
                    JenisPelayanan.getSelectedItem().toString().substring(0, 1), Catatan.getText(), KdPenyakit.getText(),
                    NmPenyakit.getText(), KdPoli.getText(), NmPoli.getText(), hakKelas.getSelectedItem().toString().substring(0, 1),
                    LakaLantas.getSelectedItem().toString().substring(0, 1), LokasiLaka.getText(), user,
                    TNoRM.getText(), TPasien.getText(), TglLahir.getText(), JenisPeserta.getText(), jkel, NoKartu.getText(),
                    "0000-00-00 00:00:00", AsalRujukan.getSelectedItem().toString(), Eksekutif.getSelectedItem().toString(),
                    COB.getSelectedItem().toString(), "", NoTelp.getText(), nameNode.path("code").asText(), nameNode.path("message").asText(),
                    KasusKatarak.getSelectedItem().toString().substring(0, 1), tglkkl, Ket.getText(), suplesi.getSelectedItem().toString(),
                    NoSEPSuplesi.getText(), KdProv.getText(), NmProv.getText(), KdKab.getText(), NmKab.getText(),
                    KdKec.getText(), NmKec.getText(), noSurat.getText(), Kddpjp.getText(), NmDPJP.getText(), "", hakKelas.getSelectedItem().toString().substring(0, 1),
                    kdNaikKls.getText(), pembi, pngJwb.getText(), tujuanKun.getSelectedItem().toString(), flag, kdpnjg, asesmen, KddpjpLayan.getText(), nmdpjpLayan.getText(),respons
                }) == false) {
                    Sequel.menyimpantf("bridging_sep_backup", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "SEP", 59, new String[]{
                        "-", TNoRw.getText(), Valid.SetTgl(TanggalSEP.getSelectedItem() + ""), Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""),
                        NoRujukan.getText(), KdPpkRujukan.getText(), rujukanSEP.getText(), KdPPK.getText(), NmPPK.getText(),
                        JenisPelayanan.getSelectedItem().toString().substring(0, 1), Catatan.getText(), KdPenyakit.getText(),
                        NmPenyakit.getText(), KdPoli.getText(), NmPoli.getText(), hakKelas.getSelectedItem().toString().substring(0, 1),
                        LakaLantas.getSelectedItem().toString().substring(0, 1), LokasiLaka.getText(), user,
                        TNoRM.getText(), TPasien.getText(), TglLahir.getText(), JenisPeserta.getText(), jkel, NoKartu.getText(),
                        "0000-00-00 00:00:00", AsalRujukan.getSelectedItem().toString(), Eksekutif.getSelectedItem().toString(),
                        COB.getSelectedItem().toString(), "", NoTelp.getText(), nameNode.path("code").asText(), nameNode.path("message").asText(),
                        KasusKatarak.getSelectedItem().toString().substring(0, 1), tglkkl, Ket.getText(), suplesi.getSelectedItem().toString(),
                        NoSEPSuplesi.getText(), KdProv.getText(), NmProv.getText(), KdKab.getText(), NmKab.getText(),
                        KdKec.getText(), NmKec.getText(), noSurat.getText(), Kddpjp.getText(), NmDPJP.getText(), "", hakKelas.getSelectedItem().toString().substring(0, 1),
                        kdNaikKls.getText(), pembi, pngJwb.getText(), tujuanKun.getSelectedItem().toString(), flag, kdpnjg, asesmen, KddpjpLayan.getText(), nmdpjpLayan.getText(),respons
                    });
                }                
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi Bridging : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
            }
        }
        
        //%%%%%%%%%%%%%%%%
    }

    public void tampilNoRujukan(String nomorrujukan) {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc=String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
            String URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/Peserta/" + nomorrujukan;

            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            if (nameNode.path("code").asText().equals("200")) {
                Valid.tabelKosong(tabMode);
                JsonNode response = root.path("response").path("rujukan");
                KdPenyakit.setText(response.path("diagnosa").path("kode").asText());
                NmPenyakit.setText(response.path("diagnosa").path("nama").asText());
                NoRujukan.setText(response.path("noKunjungan").asText());
                KdPpkRujukan.setText(response.path("provPerujuk").path("kode").asText());
//                NmPpkRujukan.setText(response.path("provPerujuk").path("nama").asText());
                Valid.SetTgl(TanggalRujuk, response.path("tglKunjungan").asText());
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText() + " di Faskes 1.");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
    }
    
    public void cekSEPVclaim() {
        if (!NoSEP.getText().equals("")) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
                URL = prop.getProperty("URLAPIBPJS") + "/SEP/" + NoSEP.getText();

                HttpEntity requestEntity = new HttpEntity(headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
                JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Isi Pesan : " + nameNode.path("message").asText());

                if (nameNode.path("message").asText().equals("Sukses")) {
                    BtnSimpan6.setEnabled(true);
                    BtnSimpan6.requestFocus();
//ini yang baru -----------            
                    JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                    System.out.println("ini responnya : " + mapper.readTree(api.Decrypt(root.path("response").asText(), utc)));
//sampai sini -------------
                    Catatan.setText(response.path("catatan").asText());                    
                    Status.setText(Status.getText());
                    KdPoli.setText("");
                    NmPoli.setText(response.path("poli").asText());
                    NmPenyakit.setText(response.path("diagnosa").asText());
                    Valid.SetTgl(TanggalSEP, response.path("tglSep").asText());
                    NoRujukan.setText(response.path("noRujukan").asText());
                    jkel = response.path("peserta").path("kelamin").asText();
                    JenisPeserta.setText(response.path("peserta").path("jnsPeserta").asText());
                    
                    if (jkel.equals("L")) {
                        JK.setText("Laki-laki");
                    } else {
                        JK.setText("Perempuan");
                    }

                    if (response.path("jnsPelayanan").asText().toLowerCase().contains("inap")) {
                        JenisPelayanan.setSelectedIndex(0);
                    } else {
                        JenisPelayanan.setSelectedIndex(1);
                    }

                    if (response.path("kelasRawat").asText().toLowerCase().equals("1")) {
                        hakKelas.setSelectedIndex(0);
                    } else if (response.path("kelasRawat").asText().toLowerCase().equals("2")) {
                        hakKelas.setSelectedIndex(1);
                    } else if (response.path("kelasRawat").asText().toLowerCase().equals("3")) {
                        hakKelas.setSelectedIndex(2);
                    }

                    if (response.path("poliEksekutif").asText().equals("0")) {
                        Eksekutif.setSelectedIndex(0);
                    } else {
                        Eksekutif.setSelectedIndex(1);
                    }
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Peserta : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                    dispose();
                }
            }
        }
    }
    
    public void cekLAYAN() {
        if (JenisPelayanan.getSelectedIndex() == 0) {
            KdPoli.setText("");
            NmPoli.setText("");
            LabelPoli.setVisible(false);
            KdPoli.setVisible(false);
            NmPoli.setVisible(false);
            btnPoli.setVisible(false);
            
            label_surat.setText("No. SPRI :");
            NoRujukan.setText(TNoRw.getText());
            noSurat.setText(Sequel.cariIsi("SELECT ifnull(no_surat,'') FROM bridging_surat_pri_bpjs WHERE no_rawat='" + TNoRw.getText() + "'"));
            Kddpjp.setText(Sequel.cariIsi("SELECT ifnull(kd_dokter_bpjs,'') FROM bridging_surat_pri_bpjs WHERE no_rawat='" + TNoRw.getText() + "'"));
            NmDPJP.setText(Sequel.cariIsi("SELECT ifnull(nm_dokter_bpjs,'') FROM bridging_surat_pri_bpjs WHERE no_rawat='" + TNoRw.getText() + "'"));
            AsalRujukan.setSelectedIndex(1);
        } else {
            LabelPoli.setVisible(true);
            KdPoli.setVisible(true);
            NmPoli.setVisible(true);
            btnPoli.setVisible(true);
            label_surat.setText("No. Surat Kontrol :");
            noSurat.setText("");
            AsalRujukan.setSelectedIndex(0);
        }
    }
    
    public void tampilFaskes1() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            Valid.tabelKosong(tabMode1);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
            URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/Peserta/" + NoKartu.getText();

            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");

            if (nameNode.path("code").asText().equals("200")) {
//ini yang baru -----------            
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("rujukan");
//sampai sini -------------                 
//                JsonNode response = root.path("response").path("rujukan");
                tabMode1.addRow(new Object[]{
                    "1", response.path("noKunjungan").asText(),
                    response.path("tglKunjungan").asText(),
                    response.path("provPerujuk").path("kode").asText(),
                    response.path("provPerujuk").path("nama").asText(),
                    response.path("poliRujukan").path("nama").asText(),
                    response.path("diagnosa").path("kode").asText(),
                    response.path("diagnosa").path("nama").asText(),
                    response.path("keluhan").asText(),
                    response.path("poliRujukan").path("kode").asText(),
                    response.path("pelayanan").path("kode").asText(),
                    response.path("pelayanan").path("nama").asText()
                });
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText() + " di Faskes 1.");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
    }
    
    public void tampilFaskes2() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            Valid.tabelKosong(tabMode2);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
            URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/RS/Peserta/" + NoKartu.getText();

            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");

            if (nameNode.path("code").asText().equals("200")) {
//ini yang baru -----------            
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("rujukan");
//sampai sini -------------                
//                JsonNode response = root.path("response").path("rujukan");
                tabMode2.addRow(new Object[]{
                    "1", response.path("noKunjungan").asText(),
                    response.path("tglKunjungan").asText(),
                    response.path("provPerujuk").path("kode").asText(),
                    response.path("provPerujuk").path("nama").asText(),
                    response.path("poliRujukan").path("nama").asText(),
                    response.path("diagnosa").path("kode").asText(),
                    response.path("diagnosa").path("nama").asText(),
                    response.path("keluhan").asText(),
                    response.path("poliRujukan").path("kode").asText(),
                    response.path("pelayanan").path("kode").asText(),
                    response.path("pelayanan").path("nama").asText()
                });
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText() + " di Faskes 2.");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
    }
    
    public void tampilFaskes1BYK() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            Valid.tabelKosong(tabMode3);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc=String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
            URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/List/Peserta/" + NoKartu.getText();            

            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            
            if (nameNode.path("code").asText().equals("200")) {                
//ini yang baru -----------            
            JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
//sampai sini -------------                
//                JsonNode response = root.path("response");
                if (response.path("rujukan").isArray()) {
                    i = 1;
                    for (JsonNode list : response.path("rujukan")) {
                        tabMode3.addRow(new Object[]{
                            i + ".",
                            list.path("noKunjungan").asText(),
                            list.path("tglKunjungan").asText(),
                            list.path("provPerujuk").path("kode").asText(),
                            list.path("provPerujuk").path("nama").asText(),
                            list.path("poliRujukan").path("nama").asText(),
                            list.path("diagnosa").path("kode").asText(),
                            list.path("diagnosa").path("nama").asText(),
                            list.path("keluhan").asText(),
                            list.path("poliRujukan").path("kode").asText(),
                            list.path("pelayanan").path("kode").asText(),
                            list.path("pelayanan").path("nama").asText()
                        });
                        i++;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText() + " di Faskes 1 data rujukan banyak.");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
    }
    
    public void tampilFaskes2BYK() {
    try {            
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            Valid.tabelKosong(tabMode4);
                        
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("X-Cons-ID",Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc=String.valueOf(api.GetUTCdatetimeAsString());
	    headers.add("X-Timestamp", utc);            
	    headers.add("X-Signature",api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
            URL = prop.getProperty("URLAPIBPJS")+"/Rujukan/RS/List/Peserta/"+NoKartu.getText();
            
	    HttpEntity requestEntity = new HttpEntity(headers);
	    ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            
            if(nameNode.path("code").asText().equals("200")){                
//ini yang baru -----------            
            JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
//sampai sini -------------                
//                JsonNode response = root.path("response");
                if(response.path("rujukan").isArray()){
                    i=1;
                    for(JsonNode list:response.path("rujukan")){
                        tabMode4.addRow(new Object[]{
                            i+".",
                            list.path("noKunjungan").asText(),
                            list.path("tglKunjungan").asText(),
                            list.path("provPerujuk").path("kode").asText(),
                            list.path("provPerujuk").path("nama").asText(),
                            list.path("poliRujukan").path("nama").asText(),
                            list.path("diagnosa").path("kode").asText(),
                            list.path("diagnosa").path("nama").asText(),
                            list.path("keluhan").asText(),
                            list.path("poliRujukan").path("kode").asText(),
                            list.path("pelayanan").path("kode").asText(),
                            list.path("pelayanan").path("nama").asText()
                        });
                        i++;
                    }
                }
            }else {
                JOptionPane.showMessageDialog(null,nameNode.path("message").asText()+" di Faskes 2 data rujukan banyak.");                
            }  
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(rootPane,"Koneksi ke server BPJS terputus...!");
            }
        }
    }
    
    private void getDataFK1() {
        if (tbFaskes1.getSelectedRow() != -1) {
            AsalRujukan.setSelectedIndex(0);
            NoRujukan.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 1).toString());
            Valid.SetTgl(TanggalRujuk, tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 2).toString());
            KdPpkRujukan.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 3).toString());
            rujukanSEP.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 4).toString()); 
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
//NmPpkRujukan.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 4).toString());
            KdPenyakit.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 6).toString());
            NmPenyakit.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 7).toString());
//            KdPoli.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 9).toString());
//            NmPoli.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 5).toString());

            if (tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 10).toString().contains("2")) {
                JenisPelayanan.setSelectedIndex(1);
            } else {
                JenisPelayanan.setSelectedIndex(0);
            }
        }
    }
    
    private void getDataFK2() {
        if (tbFaskes2.getSelectedRow() != -1) {
            AsalRujukan.setSelectedIndex(1);
            NoRujukan.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 1).toString());
            Valid.SetTgl(TanggalRujuk, tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 2).toString());
            KdPpkRujukan.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 3).toString());
            rujukanSEP.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 4).toString());
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            //NmPpkRujukan.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 4).toString());
            KdPenyakit.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 6).toString());
            NmPenyakit.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 7).toString());
//            KdPoli.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 9).toString());
//            NmPoli.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 5).toString());            

            if (tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 10).toString().contains("2")) {
                JenisPelayanan.setSelectedIndex(1);
            } else {
                JenisPelayanan.setSelectedIndex(0);
            }
        }
    }
    
    private void getDataFK1byk() {
        if (tbFaskes3.getSelectedRow() != -1) {
            AsalRujukan.setSelectedIndex(0);
            NoRujukan.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 1).toString());
            Valid.SetTgl(TanggalRujuk, tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 2).toString());
            KdPpkRujukan.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 3).toString());
            rujukanSEP.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 4).toString()); 
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            //NmPpkRujukan.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 4).toString());
            KdPenyakit.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 6).toString());
            NmPenyakit.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 7).toString());
//            KdPoli.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 9).toString());
//            NmPoli.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 5).toString());

            if (tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 10).toString().contains("2")) {
                JenisPelayanan.setSelectedIndex(1);
            } else {
                JenisPelayanan.setSelectedIndex(0);
            }
        }
    }
    
    private void getDataFK2byk() {
        if (tbFaskes4.getSelectedRow() != -1) {
            AsalRujukan.setSelectedIndex(1);
            NoRujukan.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 1).toString());
            Valid.SetTgl(TanggalRujuk, tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 2).toString());
            KdPpkRujukan.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 3).toString());
            rujukanSEP.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 4).toString());
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            //NmPpkRujukan.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 4).toString());
            KdPenyakit.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 6).toString());
            NmPenyakit.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 7).toString());
//            KdPoli.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 9).toString());
//            NmPoli.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 5).toString());

            if (tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 10).toString().contains("2")) {
                JenisPelayanan.setSelectedIndex(1);
            } else {
                JenisPelayanan.setSelectedIndex(0);
            }
        }
    }
    
    public void cekPPKRUJUKAN() {
        if (!NoKartu.getText().equals("")) {
            if (AsalRujukan.getSelectedIndex() == 0) {
                KdPpkRujukan.setText(cekViaBPJSKartu.provUmumkdProvider);
//                NmPpkRujukan.setText(cekViaBPJSKartu.provUmumnmProvider);
                Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            } else if (AsalRujukan.getSelectedIndex() == 1) {
                KdPpkRujukan.setText(KdPPK.getText());
//                NmPpkRujukan.setText(NmPPK.getText());
                Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            }
        } else if (NoKartu.getText().equals("")) {
            cekLAYAN();
        } else {
            JOptionPane.showMessageDialog(null, "Status kepesertaan tidak aktif..!!");
            btnDiagnosa.requestFocus();
        }
    }
 
    private void ExportSEPRanap() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        dialog_simpan = Valid.openDialog();
        Valid.MyReportToExcel("SELECT p.no_rkm_medis 'No. RM', p.nm_pasien 'Nama Pasien', bs.no_sep 'No. SEP', d.nm_dokter 'DPJP', b.nm_bangsal 'Ruang Perawatan', "
                + "date_format(rp.tgl_registrasi,'%d/%m/%Y') 'Tgl. MRS', date_format(ki.tgl_keluar,'%d/%m/%Y') 'Tgl. Pulang' "
                + "FROM bridging_sep bs INNER JOIN reg_periksa rp ON rp.no_rawat = bs.no_rawat "
                + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN dpjp_ranap dr ON dr.no_rawat = bs.no_rawat "
                + "INNER JOIN dokter d ON d.kd_dokter = dr.kd_dokter INNER JOIN kamar_inap ki ON ki.no_rawat=bs.no_rawat "
                + "INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal WHERE "
                + "bs.tglsep BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND bs.jnspelayanan='1' "
                + "AND rp.tgl_registrasi>='" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND rp.tgl_registrasi<='" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + "AND ki.stts_pulang NOT IN ('-','Pindah Kamar') ORDER BY b.nm_bangsal", dialog_simpan);
        
//        Valid.MyReportToExcel("SELECT p.no_rkm_medis 'No. RM', p.nm_pasien 'Nama Pasien', bs.no_sep 'No. SEP', d.nm_dokter 'DPJP' FROM bridging_sep bs "
//                + "INNER JOIN reg_periksa rp on rp.no_rawat=bs.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
//                + "INNER JOIN dpjp_ranap dr on dr.no_rawat=bs.no_rawat INNER JOIN dokter d on d.kd_dokter=dr.kd_dokter "
//                + "WHERE bs.tglsep BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and bs.jnspelayanan='1' "
//                + "and rp.tgl_registrasi>='" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and "
//                + "rp.tgl_registrasi<='" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'", dialog_simpan);

        JOptionPane.showMessageDialog(null, "Data SEP Rawat Inap berhasil diexport menjadi file excel,..!!!");
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void ExportSEPRalan() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        dialog_simpan = Valid.openDialog();
        Valid.MyReportToExcel("SELECT p.no_rkm_medis 'No. RM', p.nm_pasien 'Nama Pasien', date_format(bs.tglsep,'%d-%m-%Y') 'Tgl. SEP', "
                + "bs.no_sep 'No. SEP', d.nm_dokter 'Dokter Poliklinik' FROM bridging_sep bs "
                + "INNER JOIN reg_periksa rp on rp.no_rawat=bs.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                + "INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter INNER JOIN poliklinik pl on pl.kd_poli=rp.kd_poli "
                + "WHERE bs.tglsep BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + "and bs.jnspelayanan='2' and rp.tgl_registrasi>='" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                + "and rp.tgl_registrasi<='" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'", dialog_simpan);
        
        JOptionPane.showMessageDialog(null, "Data SEP Rawat Jalan berhasil diexport menjadi file excel,..!!!");
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public JTable getTable(){
        return tbSEP;
    }
    
    private void PengajuanBackdate() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!NoKartu.getText().equals("")) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
                utc=String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
                URL = prop.getProperty("URLAPIBPJS") + "/Sep/pengajuanSEP";
                
                requestJson = " {"
                        + "\"request\": {"
                        + "\"t_sep\": {"
                        + "\"noKartu\": \"" + NoKartu.getText() + "\","
                        + "\"tglSep\": \"" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "\","
                        + "\"jnsPelayanan\": \"" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "\","
                        + "\"jnsPengajuan\": \"1\","
                        + "\"keterangan\": \"" + Catatan.getText() + "\","
                        + "\"user\": \"" + user + "\""
                        + "}"
                        + "}"
                        + "}";
                
                HttpEntity requestEntity = new HttpEntity(requestJson, headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
//                JsonNode response = root.path("response");

                if (nameNode.path("code").asText().equals("200")) {
                    Sequel.simpanReplaceInto("bridging_sep_pengajuan",
                            "'"+ TNoRw.getText() + "','"
                            + NoKartu.getText() + "','"
                            + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "','"
                            + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "','"
                            + "1','"
                            + "Pengajuan Backdate','"
                            + Catatan.getText() + "','"
                            + user + "'", "Pengajuan SEP Backdate");
                    JOptionPane.showMessageDialog(null, "Proses mapping selesai, data nomor rawat berhasil dikirim ke SEP (Pengajuan Backdate)..!!");
                } else {
                    JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data peserta yang mau dimapping transaksinya...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void PengajuanFingerPrint() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!NoKartu.getText().equals("")) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
                utc=String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
                URL = prop.getProperty("URLAPIBPJS") + "/Sep/pengajuanSEP";
                
                requestJson = " {"
                        + "\"request\": {"
                        + "\"t_sep\": {"
                        + "\"noKartu\": \"" + NoKartu.getText() + "\","
                        + "\"tglSep\": \"" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "\","
                        + "\"jnsPelayanan\": \"" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "\","
                        + "\"jnsPengajuan\": \"2\","
                        + "\"keterangan\": \"" + Catatan.getText() + "\","
                        + "\"user\": \"" + user + "\""
                        + "}"
                        + "}"
                        + "}";
                
                HttpEntity requestEntity = new HttpEntity(requestJson, headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
//                JsonNode response = root.path("response");

                if (nameNode.path("code").asText().equals("200")) {
                    Sequel.simpanReplaceInto("bridging_sep_pengajuan",
                            "'"+ TNoRw.getText() + "','"
                            + NoKartu.getText() + "','"
                            + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "','"
                            + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "','"
                            + "2','"
                            + "Pengajuan Finger Print','"
                            + Catatan.getText() + "','"
                            + user + "'", "Pengajuan SEP Finger Print");
                    
                    JOptionPane.showMessageDialog(null, "Proses mapping selesai, data nomor rawat berhasil dikirim ke SEP (Pengajuan Finger Print)..!!");
                } else {
                    JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data peserta yang mau dimapping transaksinya...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void dataPulang() {
        try {
            ps1 = koneksi.prepareStatement("select kdStatusPlg, noSrtMeninggal, tglMeninggal, "
                    + "noLPmanual from bridging_sep_tgl_pulang where no_sep='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "'");

            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    kdSttsPlg = rs1.getString("kdStatusPlg");
                    
                    if (kdSttsPlg.equals("4")) {
                        noSrtMati.setText(rs1.getString("noSrtMeninggal"));
                        tglJiun = rs1.getString("tglMeninggal");
                        Valid.SetTgl(tglMati, rs1.getString("tglMeninggal"));
                        noLPJiun = rs1.getString("noLPmanual");
                        Valid.SetTgl(noLP, rs1.getString("noLPmanual"));
                    } else {
                        noSrtMati.setText("");
                        tglJiun = "";
                        noLPJiun = "";
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
    
    private void dataRujukan() {
        try {
            ps2 = koneksi.prepareStatement("select no_sep, tglRujukan, ppkDirujuk, nm_ppkDirujuk, jnsPelayanan, catatan, diagRujukan, nama_diagRujukan, "
                    + "tipeRujukan, poliRujukan, nama_poliRujukan, tglRencanaKunjungan, no_rujukan from bridging_rujukan_bpjs "
                    + "where no_sep='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "'");

            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    norujukan = rs2.getString("no_rujukan");
                    Valid.SetTgl(TanggalRujukKeluar, rs2.getString("tglRujukan"));
                    Valid.SetTgl(tglRencanaKunjungan, rs2.getString("tglRencanaKunjungan"));
                    TipeRujukan.setSelectedItem(rs2.getString("tipeRujukan"));
                    KdPpkRujukan1.setText(rs2.getString("ppkDirujuk"));
                    NmPpkRujukan1.setText(rs2.getString("nm_ppkDirujuk"));
                    KdPenyakit1.setText(rs2.getString("diagRujukan"));
                    NmPenyakit1.setText(rs2.getString("nama_diagRujukan"));
                    KdPoli1.setText(rs2.getString("poliRujukan"));
                    NmPoli1.setText(rs2.getString("nama_poliRujukan"));
                    sepRujuk.setText(rs2.getString("no_sep"));
                    Catatan1.setText(rs2.getString("catatan"));
                    
                    if (rs2.getString("jnsPelayanan").equals("1")) {
                        JenisPelayanan1.setSelectedIndex(0);
                    } else {
                        JenisPelayanan1.setSelectedIndex(1);
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
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void validasiDataPlg() {
        noSrtMati.setEditable(false);
        tglMati.setEnabled(false);
        noSrtMati.setText("");
            
        if (cekstsPulang.equals("Dirujuk")) {
            cmbSttsPlg.setSelectedIndex(1);            
            
        } else if (cekstsPulang.equals("APS")) {
            cmbSttsPlg.setSelectedIndex(2);

        } else if (cekstsPulang.equals("Meninggal >= 48 Jam")) {
            cmbSttsPlg.setSelectedIndex(3);
            noSrtMati.setText(Sequel.cariIsi("select concat('474.3/',no_surat) from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' AND no_surat<>''"));
            Valid.SetTgl(tglMati, Sequel.cariIsi("select tanggal from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "'"));
        } else if (cekstsPulang.equals("Meninggal < 48 Jam")) {
            cmbSttsPlg.setSelectedIndex(4);
            noSrtMati.setText(Sequel.cariIsi("select concat('474.3/',no_surat) from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' AND no_surat<>''"));
            Valid.SetTgl(tglMati, Sequel.cariIsi("select tanggal from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "'"));

        } else if (cekstsPulang.equals("Sembuh/BLPL")) {
            cmbSttsPlg.setSelectedIndex(5);
        } else if (cekstsPulang.equals("Kabur")) {
            cmbSttsPlg.setSelectedIndex(6);
        }
        
        if (LakaLantas.getSelectedIndex() == 1 || LakaLantas.getSelectedIndex() == 2) {
            noLP.setEnabled(true);
        } else {
            noLP.setEnabled(false);
        }
    }
   
    private void diagSekunderSIAP() {
        diagSekunderKirim = "";
        diagSekunderSimpan = "";

        for (i = 0; i < tbdiagSekunder.getRowCount(); i++) {
            diagSekunderKirim = diagSekunderKirim + "," + "S;" + kddiagSekun;
            diagSekunderSimpan = diagSekunderSimpan + "S;" + kddiagSekun + "#";
        }
    }
    
    private void prosedurSIAP() {
        prosedurKirim = "";
        prosedurSimpan = "";
        
        for (i = 0; i < tbProsedur.getRowCount(); i++) {
            prosedurKirim = prosedurKirim + "," + kdprosedur;
            prosedurSimpan = prosedurSimpan + kdprosedur + "#";
        }
    }
    
    private void PulangnyaDiupdate() {
        kdSttsPlg = "";
        tglJiun = "";
        noLPJiun = "";
        desSttsPlg = "";

        if (cmbSttsPlg.getSelectedIndex() == 1 || cmbSttsPlg.getSelectedIndex() == 5) {
            kdSttsPlg = "1";
            desSttsPlg = "Atas Persetujuan Dokter";
            tglJiun = "";
        } else if (cmbSttsPlg.getSelectedIndex() == 2) {
            kdSttsPlg = "3";
            desSttsPlg = "Atas Permintaan Sendiri";
            tglJiun = "";
        } else if (cmbSttsPlg.getSelectedIndex() == 3 || cmbSttsPlg.getSelectedIndex() == 4) {
            kdSttsPlg = "4";
            desSttsPlg = "Meninggal";
            tglJiun = Valid.SetTgl(tglMati.getSelectedItem() + "");
        } else if (cmbSttsPlg.getSelectedIndex() == 6) {
            kdSttsPlg = "5";
            desSttsPlg = "Lain-lain";
            tglJiun = "";
        }
        
        if (LakaLantas.getSelectedIndex() == 0 || LakaLantas.getSelectedIndex() == 3) {
            noLPJiun = "";
        } else {
            noLPJiun = Valid.SetTgl(noLP.getSelectedItem() + "");
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
            URL = prop.getProperty("URLAPIBPJS") + "/SEP/2.0/updtglplg";

            requestJson = "{"
                    + "\"request\":{"
                    + "\"t_sep\":{"
                    + "\"noSep\":\"" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "\","
                    + "\"statusPulang\":\"" + kdSttsPlg + "\","
                    + "\"noSuratMeninggal\":\"" + noSrtMati.getText() + "\","
                    + "\"tglMeninggal\":\"" + tglJiun + "\","
                    + "\"tglPulang\":\"" + tglPulangInap + "\","
                    + "\"noLPManual\":\"" + noLPJiun + "\","
                    + "\"user\":\"" + user + "\""
                    + "}"
                    + "}"
                    + "}";

            System.out.println("Ini mengirim : " + requestJson);
            HttpEntity requestEntity = new HttpEntity(requestJson, headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());
//                JsonNode response = root.path("response");

            if (nameNode.path("code").asText().equals("200")) {
                Sequel.mengedit("bridging_sep", "no_sep='" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "'",
                        "tglpulang='" + tglPlgRuangan.getText() + "'");

                Sequel.simpanReplaceInto("bridging_sep_tgl_pulang",
                        "'" + tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString() + "','"
                        + TNoRw.getText() + "','"
                        + kdSttsPlg + "','"
                        + desSttsPlg + "','"
                        + noSrtMati.getText() + "','"
                        + tglJiun + "','"
                        + tglPlgRuangan.getText() + "','"
                        + noLPJiun + "','"
                        + user + "'", "Menyimpan tanggal & status pulang");

                emptTeks();
                tampil();
                WindowUpdatePulang.dispose();
                JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi Bridging : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
            }
        }
    }
    
    public void tampilKunjungan() {
        Valid.tabelKosong(tabMode7);
        try {
            ps3 = koneksi.prepareStatement("SELECT r.no_rkm_medis, date_format(r.tgl_registrasi,'%d-%m-%Y') tglReg, if(r.status_lanjut='Ralan','R. Jalan','R. Inap') status_lanjut, "
                    + "p.nm_poli, pj.png_jawab, ifnull(bs.no_sep,'-') no_sep, ifnull(bs.no_rujukan,'-') no_rujukan, r.kd_pj, ifnull(bs.jkel,'-') jkel, "
                    + "ifnull(bs.noskdp,'-') noskdp, ifnull(bs.urutan_sep,'-') urutan_sep, r.no_rawat, bs.no_kartu,bs.nama_pasien, bs.tanggal_lahir, bs.diagawal, "
                    + "bs.nmdiagnosaawal FROM reg_periksa r INNER JOIN poliklinik p ON p.kd_poli=r.kd_poli INNER JOIN penjab pj ON pj.kd_pj=r.kd_pj "
                    + "LEFT JOIN bridging_sep bs ON bs.no_rawat=r.no_rawat WHERE r.no_rkm_medis ='" + TCari.getText() + "' ORDER BY r.tgl_registrasi DESC LIMIT 10");
            try {
                rs3 = ps3.executeQuery();
                i = 1;
                while (rs3.next()) {
                    tabMode7.addRow(new String[]{
                        i + ".",
                        rs3.getString("no_rkm_medis"),
                        rs3.getString("tglReg"),
                        rs3.getString("status_lanjut"),
                        rs3.getString("nm_poli"),
                        rs3.getString("png_jawab"),
                        rs3.getString("no_sep"),
                        rs3.getString("no_rujukan"),
                        rs3.getString("noskdp"),
                        rs3.getString("urutan_sep"),
                        rs3.getString("kd_pj"),
                        rs3.getString("jkel"),                        
                        rs3.getString("no_rawat"),
                        rs3.getString("no_kartu"),
                        rs3.getString("nama_pasien"),
                        rs3.getString("tanggal_lahir"),
                        rs3.getString("nmdiagnosaawal")
                    });
                    i++;
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
        LCount1.setText("" + tabMode7.getRowCount());

        if (TCari.getText().trim().equals("") || tabMode7.getRowCount() < 1) {
            JOptionPane.showMessageDialog(rootPane, "Periksa lagi Key Word & harus terisi No. RM pasien dengan benar...!!!");
        }
    }
    
    private void simpanBackupSEPAwal() {
        //Simpan Ke tabel bridging_sep_backup
        System.out.println("Pesan SEP Backup : Menyiapkan data ketabel bridging_sep_backup utk. disimpan (Waktu : " + Sequel.cariIsi("select date_format(now(),'%d-%m-%Y %H:%i:%s')") + ") ...!!!");
        if (Sequel.menyimpantf("bridging_sep_backup", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "SEP", 59, new String[]{
            "-", TNoRw.getText(), Valid.SetTgl(TanggalSEP.getSelectedItem() + ""), Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""),
            NoRujukan.getText(), KdPpkRujukan.getText(), rujukanSEP.getText(), KdPPK.getText(), NmPPK.getText(),
            JenisPelayanan.getSelectedItem().toString().substring(0, 1), Catatan.getText(), KdPenyakit.getText(),
            NmPenyakit.getText(), KdPoli.getText(), NmPoli.getText(), hakKelas.getSelectedItem().toString().substring(0, 1),
            LakaLantas.getSelectedItem().toString().substring(0, 1), LokasiLaka.getText(), user,
            TNoRM.getText(), TPasien.getText(), TglLahir.getText(), JenisPeserta.getText(), jkel, NoKartu.getText(),
            "0000-00-00 00:00:00", AsalRujukan.getSelectedItem().toString(), Eksekutif.getSelectedItem().toString(),
            COB.getSelectedItem().toString(), "", NoTelp.getText(), "XXX", "PETUGAS MENGIRIM",
            KasusKatarak.getSelectedItem().toString().substring(0, 1), tglkkl, Ket.getText(), suplesi.getSelectedItem().toString(),
            NoSEPSuplesi.getText(), KdProv.getText(), NmProv.getText(), KdKab.getText(), NmKab.getText(),
            KdKec.getText(), NmKec.getText(), noSurat.getText(), Kddpjp.getText(), NmDPJP.getText(), "", hakKelas.getSelectedItem().toString().substring(0, 1),
            kdNaikKls.getText(), pembi, pngJwb.getText(), tujuanKun.getSelectedItem().toString(), flag, kdpnjg, asesmen, KddpjpLayan.getText(), nmdpjpLayan.getText(), requestJson
        }) == false) {
            Sequel.menyimpantf("bridging_sep_backup", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "SEP", 59, new String[]{
                "-", TNoRw.getText(), Valid.SetTgl(TanggalSEP.getSelectedItem() + ""), Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""),
                NoRujukan.getText(), KdPpkRujukan.getText(), rujukanSEP.getText(), KdPPK.getText(), NmPPK.getText(),
                JenisPelayanan.getSelectedItem().toString().substring(0, 1), Catatan.getText(), KdPenyakit.getText(),
                NmPenyakit.getText(), KdPoli.getText(), NmPoli.getText(), hakKelas.getSelectedItem().toString().substring(0, 1),
                LakaLantas.getSelectedItem().toString().substring(0, 1), LokasiLaka.getText(), user,
                TNoRM.getText(), TPasien.getText(), TglLahir.getText(), JenisPeserta.getText(), jkel, NoKartu.getText(),
                "0000-00-00 00:00:00", AsalRujukan.getSelectedItem().toString(), Eksekutif.getSelectedItem().toString(),
                COB.getSelectedItem().toString(), "", NoTelp.getText(), "XXX", "PETUGAS MENGIRIM",
                KasusKatarak.getSelectedItem().toString().substring(0, 1), tglkkl, Ket.getText(), suplesi.getSelectedItem().toString(),
                NoSEPSuplesi.getText(), KdProv.getText(), NmProv.getText(), KdKab.getText(), NmKab.getText(),
                KdKec.getText(), NmKec.getText(), noSurat.getText(), Kddpjp.getText(), NmDPJP.getText(), "", hakKelas.getSelectedItem().toString().substring(0, 1),
                kdNaikKls.getText(), pembi, pngJwb.getText(), tujuanKun.getSelectedItem().toString(), flag, kdpnjg, asesmen, KddpjpLayan.getText(), nmdpjpLayan.getText(), requestJson
            });
        }        
    }
}
