package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class RMAsesmenAwalKebidanan2 extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps3, ps4, psLaprm;
    private ResultSet rs, rs1, rs3, rs4, rsPrev, rsLaprm;
    private int i = 0, x = 0, pilihan = 0;
    private DlgCariPetugas petugas;
    private DlgCariDokter dokter;
    private String nipBidan1 = "", nipBidan2 = "", nipDokter = "", tidakAda = "", tidakDiketahui = "", alergiObat = "", alergiMakanan = "",
            alergiLainya = "", gelangTanda = "", alergiDiberitahukanDokter = "", alergiDiberitahukanFarmasis = "", alergiDiberitahukanAhligizi = "",
            ya = "", pendengaran = "", penglihatan = "", kognitif = "", fisik = "", budaya = "", emosi = "", bahasa = "", lainHambatan = "",
            diagnosa = "", tindakanKeperawatan = "", lainKebutuhanEdukasi = "", obatObatan = "", rehabilitasi = "", diet = "", manajemenNyeri = "",
            pasien = "", keluargaPasien = "", tidakDapat = "", identifikasi1 = "", identifikasi2 = "", identifikasi3 = "", identifikasi4 = "",
            identifikasi5 = "", identifikasi6 = "", identifikasi7 = "", identifikasi8 = "", identifikasi9 = "", identifikasi10 = "", noRawat = "", 
            stsrwt = "", mpp = "", dp = "", idFileTtd = "", idParameterTtd = "", URL = "", usernya = "", pwdnya = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMAsesmenAwalKebidanan2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang/Poli/Inst.", "Tgl. Asesmen", "Umur Pasien", "Pekerjaan Pasien", "Agama", "Alamat Pasien", "Status Nikah", "Nama Bidan", "Nama Dokter",
            "nyeri", "lokasi_nyeri", "jenis", "skala_nyeri", "provocation", "ket_lain_provocation", "quality", "ket_lain_quality", "radiation", "severity", "time", "time_lama",
            "gizi_1", "gizi_1ya", "gizi_2", "cek_tidak_ada", "cek_tidak_diketahui", "cek_alergi_obat", "ket_alergi_obat", "ket_reaksi_alergi_obat", "cek_alergi_makanan",
            "ket_alergi_makanan", "ket_reaksi_alergi_makanan", "cek_alergi_lainya", "ket_alergi_lainya", "ket_reaksi_alergi_lainya", "cek_gelang_tanda", "cek_alergi_diberitahukan_dokter",
            "cek_alergi_diberitahukan_farmasis", "cek_alergi_diberitahukan_ahliGizi", "alat_bantu", "prothesis", "cacat_tubuh", "adl", "riwayat_jatuh", "nip_bidan", "cek_ya",
            "cek_pendengaran", "cek_penglihatan", "cek_kognitif", "cek_fisik", "cek_budaya", "cek_emosi", "cek_bahasa", "cek_lain_hambatan", "ket_lain_hambatan", "dibutuhkan_penerjemah",
            "sebutkan", "bahasa_isyarat", "cek_diagnosa", "cek_tindakan_keperawatan", "ket_tindakan_keperawatan", "cek_lain_kebutuhan_edukasi", "ket_lain_kebutuhan_edukasi",
            "cek_obat_obatan", "cek_rehabilitasi", "cek_diet", "cek_manajemen_nyeri", "cek_pasien", "cek_keluarga_pasien", "nama_keluarga_pasien", "cek_tidak_dapat", "ket_tidak_dapat",
            "tgl_edukasi", "jam_edukasi", "nip_dokter", "cek_identifikasi1", "cek_identifikasi2", "cek_identifikasi3", "cek_identifikasi4", "cek_identifikasi5", "cek_identifikasi6",
            "cek_identifikasi7", "cek_identifikasi8", "cek_identifikasi9", "cek_identifikasi10", "memerlukan", "mpp", "dp", "tgl_dp", "nm_keluarga_pasien", "nip_bidan_dp", "status_rawat",
            "waktu_simpan", "riw_jatuh_resiko_jatuh", "kondisi_kesehatan", "alat_bantu_resiko_jatuh", "terapi_IV", "gaya_berjalan", "status_mental", "jumlah_skor", "kesimpulan_resiko_jatuh",
            "id_file_nm_keluarga_pasien"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 106; i++) {
            TableColumn column = tbAsesmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(220);
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
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());

        Tlokasi.setDocument(new batasInput((int) 180).getKata(Tlokasi));
        Tprovo.setDocument(new batasInput((int) 180).getKata(Tprovo));
        Tquality.setDocument(new batasInput((int) 180).getKata(Tquality));
        TketRiwAlergiObat.setDocument(new batasInput((int) 255).getKata(TketRiwAlergiObat));
        TreakRiwAlergiObat.setDocument(new batasInput((int) 255).getKata(TreakRiwAlergiObat));
        TketRiwAlergiMak.setDocument(new batasInput((int) 255).getKata(TketRiwAlergiMak));
        TreakRiwAlergiMak.setDocument(new batasInput((int) 255).getKata(TreakRiwAlergiMak));
        TketRiwAlergiLain.setDocument(new batasInput((int) 255).getKata(TketRiwAlergiLain));
        TreakRiwAlergiLain.setDocument(new batasInput((int) 255).getKata(TreakRiwAlergiLain));
        TalatBantu.setDocument(new batasInput((int) 180).getKata(TalatBantu));
        Tprotesis.setDocument(new batasInput((int) 180).getKata(Tprotesis));
        TcacatTubuh.setDocument(new batasInput((int) 180).getKata(TcacatTubuh));
        TketLainHambatan.setDocument(new batasInput((int) 180).getKata(TketLainHambatan));
        Tsebutkan.setDocument(new batasInput((int) 180).getKata(Tsebutkan));
        TtindakanKep.setDocument(new batasInput((int) 180).getKata(TtindakanKep));
        TlainKebutuhan.setDocument(new batasInput((int) 180).getKata(TlainKebutuhan));
        TnmKlgPasien.setDocument(new batasInput((int) 180).getKata(TnmKlgPasien));
        TtidakDapat.setDocument(new batasInput((int) 180).getKata(TtidakDapat));
        Tmemerlukan.setDocument(new batasInput((int) 255).getKata(Tmemerlukan));
        TnmKeluargaPasien.setDocument(new batasInput((int) 180).getKata(TnmKeluargaPasien));

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
            System.out.println(e.toString());
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
        MnHapusTtd = new javax.swing.JMenuItem();
        MnBikinQrCode = new javax.swing.JMenuItem();
        WindowNomorDokumenRM = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        jLabel125 = new widget.Label();
        cmbRM = new widget.ComboBox();
        panelisi6 = new widget.panelisi();
        BtnTampilkanQr = new widget.Button();
        BtnCloseIn2 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame25 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        PanelInput = new javax.swing.JPanel();
        jLabel128 = new widget.Label();
        jLabel365 = new widget.Label();
        cmbNyeri = new widget.ComboBox();
        jLabel366 = new widget.Label();
        Tlokasi = new widget.TextBox();
        jLabel367 = new widget.Label();
        cmbJenis = new widget.ComboBox();
        PanelWall = new usu.widget.glass.PanelGlass();
        cmbSkala = new widget.ComboBox();
        jLabel100 = new widget.Label();
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
        jLabel129 = new widget.Label();
        jLabel130 = new widget.Label();
        jLabel64 = new widget.Label();
        jLabel75 = new widget.Label();
        chkRiwTidakAda = new widget.CekBox();
        chkRiwTidakDik = new widget.CekBox();
        cmbGizi1 = new widget.ComboBox();
        skorGizi1 = new widget.TextBox();
        chkRiwAlergiObat = new widget.CekBox();
        TketRiwAlergiObat = new widget.TextBox();
        cmbYaGizi1 = new widget.ComboBox();
        skorYaGizi1 = new widget.TextBox();
        jLabel82 = new widget.Label();
        TreakRiwAlergiObat = new widget.TextBox();
        jLabel65 = new widget.Label();
        chkRiwAlergiMak = new widget.CekBox();
        TketRiwAlergiMak = new widget.TextBox();
        kesimpulanGizi = new widget.TextArea();
        cmbGizi2 = new widget.ComboBox();
        skorGizi2 = new widget.TextBox();
        jLabel83 = new widget.Label();
        TreakRiwAlergiMak = new widget.TextBox();
        jLabel74 = new widget.Label();
        TotSkorGizi = new widget.TextBox();
        chkRiwAlergiLain = new widget.CekBox();
        TketRiwAlergiLain = new widget.TextBox();
        jLabel84 = new widget.Label();
        TreakRiwAlergiLain = new widget.TextBox();
        chkGelang = new widget.CekBox();
        jLabel85 = new widget.Label();
        jLabel131 = new widget.Label();
        chkDokter = new widget.CekBox();
        chkFarmasis = new widget.CekBox();
        chkAhliGz = new widget.CekBox();
        jLabel86 = new widget.Label();
        TalatBantu = new widget.TextBox();
        jLabel87 = new widget.Label();
        Tprotesis = new widget.TextBox();
        jLabel88 = new widget.Label();
        TcacatTubuh = new widget.TextBox();
        jLabel89 = new widget.Label();
        cmbAdl = new widget.ComboBox();
        jLabel90 = new widget.Label();
        cmbRiwJatuh = new widget.ComboBox();
        label14 = new widget.Label();
        TnmBidan1 = new widget.TextBox();
        BtnBidan1 = new widget.Button();
        chkSaya1 = new widget.CekBox();
        jLabel132 = new widget.Label();
        label15 = new widget.Label();
        chkYaTerdapat = new widget.CekBox();
        chkPendengaran = new widget.CekBox();
        chkPenglihatan = new widget.CekBox();
        chkKognitif = new widget.CekBox();
        chkBudaya = new widget.CekBox();
        chkEmosi = new widget.CekBox();
        chkBahasa = new widget.CekBox();
        chkLainHambatan = new widget.CekBox();
        TketLainHambatan = new widget.TextBox();
        label16 = new widget.Label();
        cmbDibutuhkan = new widget.ComboBox();
        jLabel91 = new widget.Label();
        Tsebutkan = new widget.TextBox();
        jLabel92 = new widget.Label();
        cmbBahasa = new widget.ComboBox();
        label17 = new widget.Label();
        chkDiagnosa = new widget.CekBox();
        chkObatTerapi = new widget.CekBox();
        chkDietNutrisi = new widget.CekBox();
        chkRehabilitasi = new widget.CekBox();
        chkManajemenNyeri = new widget.CekBox();
        chkTindakanKep = new widget.CekBox();
        TtindakanKep = new widget.TextBox();
        chkLainKebutuhan = new widget.CekBox();
        TlainKebutuhan = new widget.TextBox();
        jLabel133 = new widget.Label();
        label18 = new widget.Label();
        chkPasien = new widget.CekBox();
        chkKlgPasien = new widget.CekBox();
        TnmKlgPasien = new widget.TextBox();
        chkTidakDapat = new widget.CekBox();
        TtidakDapat = new widget.TextBox();
        label19 = new widget.Label();
        TtglEdukasi = new widget.Tanggal();
        label20 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel368 = new widget.Label();
        TnmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel134 = new widget.Label();
        label21 = new widget.Label();
        chkIdentifikai7 = new widget.CekBox();
        chkIdentifikai3 = new widget.CekBox();
        chkIdentifikai4 = new widget.CekBox();
        chkIdentifikai9 = new widget.CekBox();
        label22 = new widget.Label();
        Tmemerlukan = new widget.TextBox();
        label25 = new widget.Label();
        TnmKeluargaPasien = new widget.TextBox();
        label26 = new widget.Label();
        TnmBidan2 = new widget.TextBox();
        BtnBidan2 = new widget.Button();
        chkSaya2 = new widget.CekBox();
        chkFisik = new widget.CekBox();
        label27 = new widget.Label();
        TtglDp = new widget.Tanggal();
        jLabel369 = new widget.Label();
        jLabel370 = new widget.Label();
        jLabel371 = new widget.Label();
        jLabel372 = new widget.Label();
        Tpasien = new widget.Label();
        TrgRawat = new widget.Label();
        TumurPas = new widget.Label();
        TnmSuami = new widget.Label();
        jLabel373 = new widget.Label();
        TalasanMrs = new widget.Label();
        jLabel135 = new widget.Label();
        jLabel93 = new widget.Label();
        cmbResJatuh = new widget.ComboBox();
        jLabel94 = new widget.Label();
        cmbKondisi = new widget.ComboBox();
        jLabel95 = new widget.Label();
        cmbResAlatBantu = new widget.ComboBox();
        jLabel96 = new widget.Label();
        cmbTerapiIV = new widget.ComboBox();
        jLabel97 = new widget.Label();
        cmbGaya = new widget.ComboBox();
        jLabel98 = new widget.Label();
        cmbSttsMental = new widget.ComboBox();
        jLabel99 = new widget.Label();
        jLabel101 = new widget.Label();
        jLabel102 = new widget.Label();
        jLabel103 = new widget.Label();
        jLabel104 = new widget.Label();
        jLabel105 = new widget.Label();
        TskorResJatuh = new widget.TextBox();
        TskorKondisi = new widget.TextBox();
        TskorResAlatBantu = new widget.TextBox();
        TskorTerapi = new widget.TextBox();
        TskorGaya = new widget.TextBox();
        TskorSttsMental = new widget.TextBox();
        jLabel106 = new widget.Label();
        TJmlSkor = new widget.TextBox();
        kesimpulanResJatuh = new widget.TextArea();
        chkIdentifikai1 = new widget.RadioButton();
        chkIdentifikai2 = new widget.RadioButton();
        chkIdentifikai5 = new widget.RadioButton();
        chkIdentifikai6 = new widget.RadioButton();
        chkIdentifikai8 = new widget.RadioButton();
        chkIdentifikai10 = new widget.RadioButton();
        chkMPP = new widget.CekBox();
        chkDP = new widget.RadioButton();
        panelTombol = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        jLabel63 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        internalFrame21 = new widget.InternalFrame();
        panelGlass10 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbAsesmen = new widget.Table();
        panelGlass11 = new widget.panelisi();
        panelGlass12 = new widget.panelisi();
        scrollPane7 = new widget.ScrollPane();
        gambarQR = new Painter();
        jLabel107 = new widget.Label();
        Scroll5 = new widget.ScrollPane();
        LoadHTML2 = new widget.editorpane();
        panelGlass9 = new widget.panelisi();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnCari = new widget.Button();
        BtnHapus1 = new widget.Button();
        jLabel66 = new widget.Label();
        cmbPilihCetak1 = new widget.ComboBox();
        BtnPrint1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        internalFrame4 = new widget.InternalFrame();
        Scroll19 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        panelGlass2 = new widget.panelisi();
        BtnCari1 = new widget.Button();
        BtnKeluar2 = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHapusTtd.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTtd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusTtd.setText("Hapus Tanda Tangan");
        MnHapusTtd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTtd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTtd.setIconTextGap(5);
        MnHapusTtd.setName("MnHapusTtd"); // NOI18N
        MnHapusTtd.setPreferredSize(new java.awt.Dimension(160, 26));
        MnHapusTtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTtdActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusTtd);

        MnBikinQrCode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBikinQrCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        MnBikinQrCode.setText("Bikin QR Code Ttd");
        MnBikinQrCode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBikinQrCode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBikinQrCode.setIconTextGap(5);
        MnBikinQrCode.setName("MnBikinQrCode"); // NOI18N
        MnBikinQrCode.setPreferredSize(new java.awt.Dimension(160, 26));
        MnBikinQrCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBikinQrCodeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBikinQrCode);

        WindowNomorDokumenRM.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowNomorDokumenRM.setName("WindowNomorDokumenRM"); // NOI18N
        WindowNomorDokumenRM.setUndecorated(true);
        WindowNomorDokumenRM.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Dokumen Rekam Medis Aktif ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        panelisi3.setBackground(new java.awt.Color(255, 150, 255));
        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 70));
        panelisi3.setLayout(null);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setText("Pilih Rekam Medis :");
        jLabel125.setName("jLabel125"); // NOI18N
        panelisi3.add(jLabel125);
        jLabel125.setBounds(0, 10, 120, 23);

        cmbRM.setBackground(new java.awt.Color(245, 253, 240));
        cmbRM.setForeground(new java.awt.Color(0, 0, 0));
        cmbRM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbRM.setLightWeightPopupEnabled(false);
        cmbRM.setName("cmbRM"); // NOI18N
        panelisi3.add(cmbRM);
        cmbRM.setBounds(127, 10, 550, 23);

        internalFrame5.add(panelisi3, java.awt.BorderLayout.CENTER);

        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 48));
        panelisi6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 9, 9));

        BtnTampilkanQr.setForeground(new java.awt.Color(0, 0, 0));
        BtnTampilkanQr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/clear24.png"))); // NOI18N
        BtnTampilkanQr.setMnemonic('S');
        BtnTampilkanQr.setText("Tampilkan Qr Code TTD");
        BtnTampilkanQr.setToolTipText("Alt+S");
        BtnTampilkanQr.setName("BtnTampilkanQr"); // NOI18N
        BtnTampilkanQr.setPreferredSize(new java.awt.Dimension(180, 30));
        BtnTampilkanQr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTampilkanQrActionPerformed(evt);
            }
        });
        panelisi6.add(BtnTampilkanQr);

        BtnCloseIn2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn2.setMnemonic('U');
        BtnCloseIn2.setText("Tutup");
        BtnCloseIn2.setToolTipText("Alt+U");
        BtnCloseIn2.setName("BtnCloseIn2"); // NOI18N
        BtnCloseIn2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn2ActionPerformed(evt);
            }
        });
        panelisi6.add(BtnCloseIn2);

        internalFrame5.add(panelisi6, java.awt.BorderLayout.PAGE_END);

        WindowNomorDokumenRM.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Asesmen Awal Kebidanan (hal. 2) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.setPreferredSize(new java.awt.Dimension(0, 2000));
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame25.setBorder(null);
        internalFrame25.setName("internalFrame25"); // NOI18N
        internalFrame25.setLayout(new java.awt.BorderLayout());

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 1654));
        PanelInput.setRequestFocusEnabled(false);
        PanelInput.setLayout(null);

        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setText("ASSESMEN NYERI");
        jLabel128.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel128.setName("jLabel128"); // NOI18N
        PanelInput.add(jLabel128);
        jLabel128.setBounds(0, 10, 130, 23);

        jLabel365.setForeground(new java.awt.Color(0, 0, 0));
        jLabel365.setText("Nyeri :");
        jLabel365.setName("jLabel365"); // NOI18N
        PanelInput.add(jLabel365);
        jLabel365.setBounds(0, 38, 120, 23);

        cmbNyeri.setBackground(new java.awt.Color(245, 253, 240));
        cmbNyeri.setForeground(new java.awt.Color(0, 0, 0));
        cmbNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbNyeri.setLightWeightPopupEnabled(false);
        cmbNyeri.setName("cmbNyeri"); // NOI18N
        cmbNyeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNyeriActionPerformed(evt);
            }
        });
        PanelInput.add(cmbNyeri);
        cmbNyeri.setBounds(125, 38, 60, 23);

        jLabel366.setForeground(new java.awt.Color(0, 0, 0));
        jLabel366.setText("Lokasi :");
        jLabel366.setName("jLabel366"); // NOI18N
        PanelInput.add(jLabel366);
        jLabel366.setBounds(185, 38, 50, 23);

        Tlokasi.setForeground(new java.awt.Color(0, 0, 0));
        Tlokasi.setName("Tlokasi"); // NOI18N
        Tlokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlokasiKeyPressed(evt);
            }
        });
        PanelInput.add(Tlokasi);
        Tlokasi.setBounds(240, 38, 330, 23);

        jLabel367.setForeground(new java.awt.Color(0, 0, 0));
        jLabel367.setText("Jenis :");
        jLabel367.setName("jLabel367"); // NOI18N
        PanelInput.add(jLabel367);
        jLabel367.setBounds(580, 38, 40, 23);

        cmbJenis.setBackground(new java.awt.Color(245, 253, 240));
        cmbJenis.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Akut", "Kronis" }));
        cmbJenis.setLightWeightPopupEnabled(false);
        cmbJenis.setName("cmbJenis"); // NOI18N
        PanelInput.add(cmbJenis);
        cmbJenis.setBounds(626, 38, 65, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/skala_nyeri.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        PanelInput.add(PanelWall);
        PanelWall.setBounds(30, 66, 540, 230);

        cmbSkala.setForeground(new java.awt.Color(0, 0, 0));
        cmbSkala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cmbSkala.setName("cmbSkala"); // NOI18N
        PanelInput.add(cmbSkala);
        cmbSkala.setBounds(665, 273, 45, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Skala Nyeri : ");
        jLabel100.setName("jLabel100"); // NOI18N
        PanelInput.add(jLabel100);
        jLabel100.setBounds(580, 273, 80, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Provocation");
        jLabel68.setName("jLabel68"); // NOI18N
        PanelInput.add(jLabel68);
        jLabel68.setBounds(0, 301, 100, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText(": Faktor yang memperburuk rasa nyeri");
        jLabel70.setName("jLabel70"); // NOI18N
        PanelInput.add(jLabel70);
        jLabel70.setBounds(110, 301, 210, 23);

        cmbProvo.setForeground(new java.awt.Color(0, 0, 0));
        cmbProvo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Cahaya", "Gelap", "Gerakan", "Berbaring", "Melahirkan", "Lainnya" }));
        cmbProvo.setName("cmbProvo"); // NOI18N
        cmbProvo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvoActionPerformed(evt);
            }
        });
        PanelInput.add(cmbProvo);
        cmbProvo.setBounds(325, 301, 85, 23);

        Tprovo.setBackground(new java.awt.Color(245, 250, 240));
        Tprovo.setForeground(new java.awt.Color(0, 0, 0));
        Tprovo.setName("Tprovo"); // NOI18N
        Tprovo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprovoKeyPressed(evt);
            }
        });
        PanelInput.add(Tprovo);
        Tprovo.setBounds(415, 301, 510, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Quality");
        jLabel71.setName("jLabel71"); // NOI18N
        PanelInput.add(jLabel71);
        jLabel71.setBounds(0, 329, 100, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel72.setText(": Rasa nyeri seperti");
        jLabel72.setName("jLabel72"); // NOI18N
        PanelInput.add(jLabel72);
        jLabel72.setBounds(110, 329, 210, 23);

        cmbQuality.setForeground(new java.awt.Color(0, 0, 0));
        cmbQuality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ditusuk", "Dipukul", "Berdenyut", "Ditikam", "Kram", "Ditarik", "Dibakar", "Tajam", "Lainnya" }));
        cmbQuality.setName("cmbQuality"); // NOI18N
        cmbQuality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbQualityActionPerformed(evt);
            }
        });
        PanelInput.add(cmbQuality);
        cmbQuality.setBounds(325, 329, 85, 23);

        Tquality.setBackground(new java.awt.Color(245, 250, 240));
        Tquality.setForeground(new java.awt.Color(0, 0, 0));
        Tquality.setName("Tquality"); // NOI18N
        Tquality.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TqualityKeyPressed(evt);
            }
        });
        PanelInput.add(Tquality);
        Tquality.setBounds(415, 329, 510, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Radiation");
        jLabel73.setName("jLabel73"); // NOI18N
        PanelInput.add(jLabel73);
        jLabel73.setBounds(0, 357, 100, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText(": Nyeri menjalar ke bagian tubuh yang lain");
        jLabel76.setName("jLabel76"); // NOI18N
        PanelInput.add(jLabel76);
        jLabel76.setBounds(110, 357, 210, 23);

        cmbRadia.setForeground(new java.awt.Color(0, 0, 0));
        cmbRadia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbRadia.setName("cmbRadia"); // NOI18N
        PanelInput.add(cmbRadia);
        cmbRadia.setBounds(325, 357, 60, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Severity");
        jLabel77.setName("jLabel77"); // NOI18N
        PanelInput.add(jLabel77);
        jLabel77.setBounds(0, 385, 100, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText(": Tingkat keparahan nyeri");
        jLabel78.setName("jLabel78"); // NOI18N
        PanelInput.add(jLabel78);
        jLabel78.setBounds(110, 385, 210, 23);

        cmbSever.setForeground(new java.awt.Color(0, 0, 0));
        cmbSever.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak Nyeri", "Ringan", "Sedang", "Berat" }));
        cmbSever.setName("cmbSever"); // NOI18N
        PanelInput.add(cmbSever);
        cmbSever.setBounds(325, 385, 86, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Time");
        jLabel79.setName("jLabel79"); // NOI18N
        PanelInput.add(jLabel79);
        jLabel79.setBounds(0, 413, 100, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText(": Nyeri berlangsung");
        jLabel80.setName("jLabel80"); // NOI18N
        PanelInput.add(jLabel80);
        jLabel80.setBounds(110, 413, 210, 23);

        cmbTime.setForeground(new java.awt.Color(0, 0, 0));
        cmbTime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Terus Menerus", "Hilang Timbul" }));
        cmbTime.setName("cmbTime"); // NOI18N
        cmbTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTimeActionPerformed(evt);
            }
        });
        PanelInput.add(cmbTime);
        cmbTime.setBounds(325, 413, 105, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Lama  : ");
        jLabel81.setName("jLabel81"); // NOI18N
        PanelInput.add(jLabel81);
        jLabel81.setBounds(430, 413, 50, 23);

        cmbLama.setForeground(new java.awt.Color(0, 0, 0));
        cmbLama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "< 30 Menit", "> 30 Menit" }));
        cmbLama.setName("cmbLama"); // NOI18N
        PanelInput.add(cmbLama);
        cmbLama.setBounds(485, 413, 86, 23);

        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setText("SKRINING GIZI AWAL");
        jLabel129.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel129.setName("jLabel129"); // NOI18N
        PanelInput.add(jLabel129);
        jLabel129.setBounds(0, 441, 160, 23);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel130.setText("RIWAYAT ALERGI");
        jLabel130.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel130.setName("jLabel130"); // NOI18N
        PanelInput.add(jLabel130);
        jLabel130.setBounds(650, 441, 120, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("1. Apakah pasien mengalami penurunan BB yang tidak direncanakan/tidak diinginkan dalam 6 bulan terakhir ?");
        jLabel64.setName("jLabel64"); // NOI18N
        PanelInput.add(jLabel64);
        jLabel64.setBounds(0, 469, 560, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("Skor :");
        jLabel75.setName("jLabel75"); // NOI18N
        PanelInput.add(jLabel75);
        jLabel75.setBounds(590, 469, 40, 23);

        chkRiwTidakAda.setBackground(new java.awt.Color(255, 255, 250));
        chkRiwTidakAda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRiwTidakAda.setForeground(new java.awt.Color(0, 0, 0));
        chkRiwTidakAda.setText("Tidak Ada");
        chkRiwTidakAda.setBorderPainted(true);
        chkRiwTidakAda.setBorderPaintedFlat(true);
        chkRiwTidakAda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRiwTidakAda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRiwTidakAda.setName("chkRiwTidakAda"); // NOI18N
        chkRiwTidakAda.setOpaque(false);
        chkRiwTidakAda.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkRiwTidakAda);
        chkRiwTidakAda.setBounds(650, 469, 80, 23);

        chkRiwTidakDik.setBackground(new java.awt.Color(255, 255, 250));
        chkRiwTidakDik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRiwTidakDik.setForeground(new java.awt.Color(0, 0, 0));
        chkRiwTidakDik.setText("Tidak Diketahui");
        chkRiwTidakDik.setBorderPainted(true);
        chkRiwTidakDik.setBorderPaintedFlat(true);
        chkRiwTidakDik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRiwTidakDik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRiwTidakDik.setName("chkRiwTidakDik"); // NOI18N
        chkRiwTidakDik.setOpaque(false);
        chkRiwTidakDik.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkRiwTidakDik);
        chkRiwTidakDik.setBounds(740, 469, 100, 23);

        cmbGizi1.setForeground(new java.awt.Color(0, 0, 0));
        cmbGizi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Tidak Yakin (ada tanda : baju menjadi longgar)", "Ya ada penurunan BB sebanyak :" }));
        cmbGizi1.setName("cmbGizi1"); // NOI18N
        cmbGizi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGizi1ActionPerformed(evt);
            }
        });
        PanelInput.add(cmbGizi1);
        cmbGizi1.setBounds(320, 497, 260, 23);

        skorGizi1.setEditable(false);
        skorGizi1.setForeground(new java.awt.Color(0, 0, 0));
        skorGizi1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGizi1.setText("0");
        skorGizi1.setFocusTraversalPolicyProvider(true);
        skorGizi1.setName("skorGizi1"); // NOI18N
        PanelInput.add(skorGizi1);
        skorGizi1.setBounds(590, 497, 40, 23);

        chkRiwAlergiObat.setBackground(new java.awt.Color(255, 255, 250));
        chkRiwAlergiObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRiwAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        chkRiwAlergiObat.setText("Alergi Obat");
        chkRiwAlergiObat.setBorderPainted(true);
        chkRiwAlergiObat.setBorderPaintedFlat(true);
        chkRiwAlergiObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRiwAlergiObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRiwAlergiObat.setName("chkRiwAlergiObat"); // NOI18N
        chkRiwAlergiObat.setOpaque(false);
        chkRiwAlergiObat.setPreferredSize(new java.awt.Dimension(175, 23));
        chkRiwAlergiObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRiwAlergiObatActionPerformed(evt);
            }
        });
        PanelInput.add(chkRiwAlergiObat);
        chkRiwAlergiObat.setBounds(650, 497, 80, 23);

        TketRiwAlergiObat.setBackground(new java.awt.Color(245, 250, 240));
        TketRiwAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        TketRiwAlergiObat.setName("TketRiwAlergiObat"); // NOI18N
        TketRiwAlergiObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketRiwAlergiObatKeyPressed(evt);
            }
        });
        PanelInput.add(TketRiwAlergiObat);
        TketRiwAlergiObat.setBounds(735, 497, 390, 23);

        cmbYaGizi1.setForeground(new java.awt.Color(0, 0, 0));
        cmbYaGizi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1 - 5 Kg", "6 - 10 Kg", "11 - 15 Kg", "> 15 Kg", "Tidak tahu berapa Kg penurunanya" }));
        cmbYaGizi1.setName("cmbYaGizi1"); // NOI18N
        cmbYaGizi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbYaGizi1ActionPerformed(evt);
            }
        });
        PanelInput.add(cmbYaGizi1);
        cmbYaGizi1.setBounds(320, 525, 260, 23);

        skorYaGizi1.setEditable(false);
        skorYaGizi1.setForeground(new java.awt.Color(0, 0, 0));
        skorYaGizi1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorYaGizi1.setText("0");
        skorYaGizi1.setFocusTraversalPolicyProvider(true);
        skorYaGizi1.setName("skorYaGizi1"); // NOI18N
        PanelInput.add(skorYaGizi1);
        skorYaGizi1.setBounds(590, 525, 40, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("Reaksi Alergi Obat :");
        jLabel82.setName("jLabel82"); // NOI18N
        PanelInput.add(jLabel82);
        jLabel82.setBounds(650, 525, 110, 23);

        TreakRiwAlergiObat.setBackground(new java.awt.Color(245, 250, 240));
        TreakRiwAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        TreakRiwAlergiObat.setName("TreakRiwAlergiObat"); // NOI18N
        TreakRiwAlergiObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreakRiwAlergiObatKeyPressed(evt);
            }
        });
        PanelInput.add(TreakRiwAlergiObat);
        TreakRiwAlergiObat.setBounds(760, 525, 365, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("2. Apakah asupan makan pasien berkurang karena penurunan nafsu makan / kesulitan menerima makanan ?");
        jLabel65.setName("jLabel65"); // NOI18N
        PanelInput.add(jLabel65);
        jLabel65.setBounds(0, 553, 560, 23);

        chkRiwAlergiMak.setBackground(new java.awt.Color(255, 255, 250));
        chkRiwAlergiMak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRiwAlergiMak.setForeground(new java.awt.Color(0, 0, 0));
        chkRiwAlergiMak.setText("Alergi Makanan");
        chkRiwAlergiMak.setBorderPainted(true);
        chkRiwAlergiMak.setBorderPaintedFlat(true);
        chkRiwAlergiMak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRiwAlergiMak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRiwAlergiMak.setName("chkRiwAlergiMak"); // NOI18N
        chkRiwAlergiMak.setOpaque(false);
        chkRiwAlergiMak.setPreferredSize(new java.awt.Dimension(175, 23));
        chkRiwAlergiMak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRiwAlergiMakActionPerformed(evt);
            }
        });
        PanelInput.add(chkRiwAlergiMak);
        chkRiwAlergiMak.setBounds(650, 553, 100, 23);

        TketRiwAlergiMak.setBackground(new java.awt.Color(245, 250, 240));
        TketRiwAlergiMak.setForeground(new java.awt.Color(0, 0, 0));
        TketRiwAlergiMak.setName("TketRiwAlergiMak"); // NOI18N
        TketRiwAlergiMak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketRiwAlergiMakKeyPressed(evt);
            }
        });
        PanelInput.add(TketRiwAlergiMak);
        TketRiwAlergiMak.setBounds(755, 553, 370, 23);

        kesimpulanGizi.setEditable(false);
        kesimpulanGizi.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), " Kesimpulan Skrining Gizi : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanGizi.setColumns(20);
        kesimpulanGizi.setRows(5);
        kesimpulanGizi.setName("kesimpulanGizi"); // NOI18N
        PanelInput.add(kesimpulanGizi);
        kesimpulanGizi.setBounds(38, 581, 350, 50);

        cmbGizi2.setForeground(new java.awt.Color(0, 0, 0));
        cmbGizi2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbGizi2.setName("cmbGizi2"); // NOI18N
        cmbGizi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGizi2ActionPerformed(evt);
            }
        });
        PanelInput.add(cmbGizi2);
        cmbGizi2.setBounds(515, 581, 65, 23);

        skorGizi2.setEditable(false);
        skorGizi2.setForeground(new java.awt.Color(0, 0, 0));
        skorGizi2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGizi2.setText("0");
        skorGizi2.setFocusTraversalPolicyProvider(true);
        skorGizi2.setName("skorGizi2"); // NOI18N
        PanelInput.add(skorGizi2);
        skorGizi2.setBounds(590, 581, 40, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("Reaksi Alergi Makanan :");
        jLabel83.setName("jLabel83"); // NOI18N
        PanelInput.add(jLabel83);
        jLabel83.setBounds(650, 581, 130, 23);

        TreakRiwAlergiMak.setBackground(new java.awt.Color(245, 250, 240));
        TreakRiwAlergiMak.setForeground(new java.awt.Color(0, 0, 0));
        TreakRiwAlergiMak.setName("TreakRiwAlergiMak"); // NOI18N
        TreakRiwAlergiMak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreakRiwAlergiMakKeyPressed(evt);
            }
        });
        PanelInput.add(TreakRiwAlergiMak);
        TreakRiwAlergiMak.setBounds(780, 581, 345, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Total Skor :");
        jLabel74.setName("jLabel74"); // NOI18N
        PanelInput.add(jLabel74);
        jLabel74.setBounds(510, 609, 70, 23);

        TotSkorGizi.setEditable(false);
        TotSkorGizi.setForeground(new java.awt.Color(0, 0, 0));
        TotSkorGizi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotSkorGizi.setText("0");
        TotSkorGizi.setFocusTraversalPolicyProvider(true);
        TotSkorGizi.setName("TotSkorGizi"); // NOI18N
        PanelInput.add(TotSkorGizi);
        TotSkorGizi.setBounds(590, 609, 40, 23);

        chkRiwAlergiLain.setBackground(new java.awt.Color(255, 255, 250));
        chkRiwAlergiLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRiwAlergiLain.setForeground(new java.awt.Color(0, 0, 0));
        chkRiwAlergiLain.setText("Alergi Lainnya");
        chkRiwAlergiLain.setBorderPainted(true);
        chkRiwAlergiLain.setBorderPaintedFlat(true);
        chkRiwAlergiLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRiwAlergiLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRiwAlergiLain.setName("chkRiwAlergiLain"); // NOI18N
        chkRiwAlergiLain.setOpaque(false);
        chkRiwAlergiLain.setPreferredSize(new java.awt.Dimension(175, 23));
        chkRiwAlergiLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRiwAlergiLainActionPerformed(evt);
            }
        });
        PanelInput.add(chkRiwAlergiLain);
        chkRiwAlergiLain.setBounds(650, 609, 95, 23);

        TketRiwAlergiLain.setBackground(new java.awt.Color(245, 250, 240));
        TketRiwAlergiLain.setForeground(new java.awt.Color(0, 0, 0));
        TketRiwAlergiLain.setName("TketRiwAlergiLain"); // NOI18N
        TketRiwAlergiLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketRiwAlergiLainKeyPressed(evt);
            }
        });
        PanelInput.add(TketRiwAlergiLain);
        TketRiwAlergiLain.setBounds(750, 609, 375, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("Reaksi Alergi Lain :");
        jLabel84.setName("jLabel84"); // NOI18N
        PanelInput.add(jLabel84);
        jLabel84.setBounds(650, 637, 100, 23);

        TreakRiwAlergiLain.setBackground(new java.awt.Color(245, 250, 240));
        TreakRiwAlergiLain.setForeground(new java.awt.Color(0, 0, 0));
        TreakRiwAlergiLain.setName("TreakRiwAlergiLain"); // NOI18N
        TreakRiwAlergiLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreakRiwAlergiLainKeyPressed(evt);
            }
        });
        PanelInput.add(TreakRiwAlergiLain);
        TreakRiwAlergiLain.setBounds(750, 637, 375, 23);

        chkGelang.setBackground(new java.awt.Color(255, 255, 250));
        chkGelang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGelang.setForeground(new java.awt.Color(0, 0, 0));
        chkGelang.setText("Gelang Tanda Alergi Dipasang (Warna Merah)");
        chkGelang.setBorderPainted(true);
        chkGelang.setBorderPaintedFlat(true);
        chkGelang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGelang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGelang.setName("chkGelang"); // NOI18N
        chkGelang.setOpaque(false);
        chkGelang.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkGelang);
        chkGelang.setBounds(650, 665, 250, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Alergi Diberitahukan Kepada :");
        jLabel85.setName("jLabel85"); // NOI18N
        PanelInput.add(jLabel85);
        jLabel85.setBounds(650, 693, 160, 23);

        jLabel131.setForeground(new java.awt.Color(0, 0, 0));
        jLabel131.setText("FUNGSIONAL");
        jLabel131.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel131.setName("jLabel131"); // NOI18N
        PanelInput.add(jLabel131);
        jLabel131.setBounds(0, 889, 110, 23);

        chkDokter.setBackground(new java.awt.Color(255, 255, 250));
        chkDokter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDokter.setForeground(new java.awt.Color(0, 0, 0));
        chkDokter.setText("Dokter");
        chkDokter.setBorderPainted(true);
        chkDokter.setBorderPaintedFlat(true);
        chkDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDokter.setName("chkDokter"); // NOI18N
        chkDokter.setOpaque(false);
        chkDokter.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkDokter);
        chkDokter.setBounds(820, 693, 60, 23);

        chkFarmasis.setBackground(new java.awt.Color(255, 255, 250));
        chkFarmasis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFarmasis.setForeground(new java.awt.Color(0, 0, 0));
        chkFarmasis.setText("Farmasis / Apoteker");
        chkFarmasis.setBorderPainted(true);
        chkFarmasis.setBorderPaintedFlat(true);
        chkFarmasis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFarmasis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFarmasis.setName("chkFarmasis"); // NOI18N
        chkFarmasis.setOpaque(false);
        chkFarmasis.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkFarmasis);
        chkFarmasis.setBounds(890, 693, 125, 23);

        chkAhliGz.setBackground(new java.awt.Color(255, 255, 250));
        chkAhliGz.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAhliGz.setForeground(new java.awt.Color(0, 0, 0));
        chkAhliGz.setText("Ahli Gizi");
        chkAhliGz.setBorderPainted(true);
        chkAhliGz.setBorderPaintedFlat(true);
        chkAhliGz.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAhliGz.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAhliGz.setName("chkAhliGz"); // NOI18N
        chkAhliGz.setOpaque(false);
        chkAhliGz.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkAhliGz);
        chkAhliGz.setBounds(1025, 693, 70, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("1. Alat Bantu :");
        jLabel86.setName("jLabel86"); // NOI18N
        PanelInput.add(jLabel86);
        jLabel86.setBounds(0, 917, 120, 23);

        TalatBantu.setForeground(new java.awt.Color(0, 0, 0));
        TalatBantu.setName("TalatBantu"); // NOI18N
        TalatBantu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalatBantuKeyPressed(evt);
            }
        });
        PanelInput.add(TalatBantu);
        TalatBantu.setBounds(125, 917, 530, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("2. Prothesis :");
        jLabel87.setName("jLabel87"); // NOI18N
        PanelInput.add(jLabel87);
        jLabel87.setBounds(0, 945, 120, 23);

        Tprotesis.setForeground(new java.awt.Color(0, 0, 0));
        Tprotesis.setName("Tprotesis"); // NOI18N
        Tprotesis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprotesisKeyPressed(evt);
            }
        });
        PanelInput.add(Tprotesis);
        Tprotesis.setBounds(125, 945, 530, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("3. Cacat Tubuh :");
        jLabel88.setName("jLabel88"); // NOI18N
        PanelInput.add(jLabel88);
        jLabel88.setBounds(0, 973, 120, 23);

        TcacatTubuh.setForeground(new java.awt.Color(0, 0, 0));
        TcacatTubuh.setName("TcacatTubuh"); // NOI18N
        TcacatTubuh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcacatTubuhKeyPressed(evt);
            }
        });
        PanelInput.add(TcacatTubuh);
        TcacatTubuh.setBounds(125, 973, 530, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("4. ADL :");
        jLabel89.setName("jLabel89"); // NOI18N
        PanelInput.add(jLabel89);
        jLabel89.setBounds(0, 1001, 120, 23);

        cmbAdl.setForeground(new java.awt.Color(0, 0, 0));
        cmbAdl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mandiri", "Dibantu" }));
        cmbAdl.setName("cmbAdl"); // NOI18N
        PanelInput.add(cmbAdl);
        cmbAdl.setBounds(125, 1001, 70, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("5. Riwayat Jatuh Dalam 3 Bulan Terakhir ? :");
        jLabel90.setName("jLabel90"); // NOI18N
        PanelInput.add(jLabel90);
        jLabel90.setBounds(195, 1001, 230, 23);

        cmbRiwJatuh.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwJatuh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbRiwJatuh.setName("cmbRiwJatuh"); // NOI18N
        PanelInput.add(cmbRiwJatuh);
        cmbRiwJatuh.setBounds(432, 1001, 60, 23);

        label14.setForeground(new java.awt.Color(0, 0, 0));
        label14.setText("Nama Bidan :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label14);
        label14.setBounds(0, 1029, 120, 23);

        TnmBidan1.setEditable(false);
        TnmBidan1.setForeground(new java.awt.Color(0, 0, 0));
        TnmBidan1.setName("TnmBidan1"); // NOI18N
        PanelInput.add(TnmBidan1);
        TnmBidan1.setBounds(125, 1029, 430, 23);

        BtnBidan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnBidan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnBidan1.setMnemonic('2');
        BtnBidan1.setToolTipText("Alt+2");
        BtnBidan1.setName("BtnBidan1"); // NOI18N
        BtnBidan1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnBidan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBidan1ActionPerformed(evt);
            }
        });
        PanelInput.add(BtnBidan1);
        BtnBidan1.setBounds(555, 1029, 28, 23);

        chkSaya1.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya1.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya1.setText("Saya Sendiri");
        chkSaya1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya1.setName("chkSaya1"); // NOI18N
        chkSaya1.setOpaque(false);
        chkSaya1.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya1ActionPerformed(evt);
            }
        });
        PanelInput.add(chkSaya1);
        chkSaya1.setBounds(590, 1029, 90, 23);

        jLabel132.setForeground(new java.awt.Color(0, 0, 0));
        jLabel132.setText("KEBUTUHAN KOMUNIKASI DAN EDUKASI");
        jLabel132.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel132.setName("jLabel132"); // NOI18N
        PanelInput.add(jLabel132);
        jLabel132.setBounds(0, 1057, 250, 23);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("Terdapat Hambatan Dalam Pembelajaran :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label15);
        label15.setBounds(0, 1085, 230, 23);

        chkYaTerdapat.setBackground(new java.awt.Color(255, 255, 250));
        chkYaTerdapat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkYaTerdapat.setForeground(new java.awt.Color(0, 0, 0));
        chkYaTerdapat.setText("Ya, Jika Ya :");
        chkYaTerdapat.setBorderPainted(true);
        chkYaTerdapat.setBorderPaintedFlat(true);
        chkYaTerdapat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkYaTerdapat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkYaTerdapat.setName("chkYaTerdapat"); // NOI18N
        chkYaTerdapat.setOpaque(false);
        chkYaTerdapat.setPreferredSize(new java.awt.Dimension(175, 23));
        chkYaTerdapat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkYaTerdapatActionPerformed(evt);
            }
        });
        PanelInput.add(chkYaTerdapat);
        chkYaTerdapat.setBounds(237, 1085, 85, 23);

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
        PanelInput.add(chkPendengaran);
        chkPendengaran.setBounds(330, 1085, 90, 23);

        chkPenglihatan.setBackground(new java.awt.Color(255, 255, 250));
        chkPenglihatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPenglihatan.setForeground(new java.awt.Color(0, 0, 0));
        chkPenglihatan.setText("Penglihatan");
        chkPenglihatan.setBorderPainted(true);
        chkPenglihatan.setBorderPaintedFlat(true);
        chkPenglihatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPenglihatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPenglihatan.setName("chkPenglihatan"); // NOI18N
        chkPenglihatan.setOpaque(false);
        chkPenglihatan.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkPenglihatan);
        chkPenglihatan.setBounds(430, 1085, 85, 23);

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
        PanelInput.add(chkKognitif);
        chkKognitif.setBounds(523, 1085, 65, 23);

        chkBudaya.setBackground(new java.awt.Color(255, 255, 250));
        chkBudaya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBudaya.setForeground(new java.awt.Color(0, 0, 0));
        chkBudaya.setText("Budaya");
        chkBudaya.setBorderPainted(true);
        chkBudaya.setBorderPaintedFlat(true);
        chkBudaya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBudaya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBudaya.setName("chkBudaya"); // NOI18N
        chkBudaya.setOpaque(false);
        chkBudaya.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkBudaya);
        chkBudaya.setBounds(660, 1085, 65, 23);

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
        PanelInput.add(chkEmosi);
        chkEmosi.setBounds(735, 1085, 55, 23);

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
        PanelInput.add(chkBahasa);
        chkBahasa.setBounds(237, 1113, 65, 23);

        chkLainHambatan.setBackground(new java.awt.Color(255, 255, 250));
        chkLainHambatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainHambatan.setForeground(new java.awt.Color(0, 0, 0));
        chkLainHambatan.setText("Lainnya");
        chkLainHambatan.setBorderPainted(true);
        chkLainHambatan.setBorderPaintedFlat(true);
        chkLainHambatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainHambatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainHambatan.setName("chkLainHambatan"); // NOI18N
        chkLainHambatan.setOpaque(false);
        chkLainHambatan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainHambatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainHambatanActionPerformed(evt);
            }
        });
        PanelInput.add(chkLainHambatan);
        chkLainHambatan.setBounds(312, 1113, 65, 23);

        TketLainHambatan.setBackground(new java.awt.Color(245, 250, 240));
        TketLainHambatan.setForeground(new java.awt.Color(0, 0, 0));
        TketLainHambatan.setName("TketLainHambatan"); // NOI18N
        TketLainHambatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketLainHambatanKeyPressed(evt);
            }
        });
        PanelInput.add(TketLainHambatan);
        TketLainHambatan.setBounds(377, 1113, 595, 23);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Dibutuhkan Penerjemah :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label16);
        label16.setBounds(0, 1141, 230, 23);

        cmbDibutuhkan.setForeground(new java.awt.Color(0, 0, 0));
        cmbDibutuhkan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbDibutuhkan.setName("cmbDibutuhkan"); // NOI18N
        cmbDibutuhkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDibutuhkanActionPerformed(evt);
            }
        });
        PanelInput.add(cmbDibutuhkan);
        cmbDibutuhkan.setBounds(237, 1141, 60, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Sebutkan :");
        jLabel91.setName("jLabel91"); // NOI18N
        PanelInput.add(jLabel91);
        jLabel91.setBounds(296, 1141, 75, 23);

        Tsebutkan.setForeground(new java.awt.Color(0, 0, 0));
        Tsebutkan.setName("Tsebutkan"); // NOI18N
        Tsebutkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsebutkanKeyPressed(evt);
            }
        });
        PanelInput.add(Tsebutkan);
        Tsebutkan.setBounds(377, 1141, 300, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Bahasa Isyarat :");
        jLabel92.setName("jLabel92"); // NOI18N
        PanelInput.add(jLabel92);
        jLabel92.setBounds(677, 1141, 100, 23);

        cmbBahasa.setForeground(new java.awt.Color(0, 0, 0));
        cmbBahasa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbBahasa.setName("cmbBahasa"); // NOI18N
        PanelInput.add(cmbBahasa);
        cmbBahasa.setBounds(785, 1141, 60, 23);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Kebutuhan Edukasi (Pilih Topik Edukasi Yang Tersedia) :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label17);
        label17.setBounds(0, 1169, 300, 23);

        chkDiagnosa.setBackground(new java.awt.Color(255, 255, 250));
        chkDiagnosa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        chkDiagnosa.setText("Diagnosa dan Manajemen Penyakit");
        chkDiagnosa.setBorderPainted(true);
        chkDiagnosa.setBorderPaintedFlat(true);
        chkDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDiagnosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDiagnosa.setName("chkDiagnosa"); // NOI18N
        chkDiagnosa.setOpaque(false);
        chkDiagnosa.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkDiagnosa);
        chkDiagnosa.setBounds(305, 1169, 195, 23);

        chkObatTerapi.setBackground(new java.awt.Color(255, 255, 250));
        chkObatTerapi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkObatTerapi.setForeground(new java.awt.Color(0, 0, 0));
        chkObatTerapi.setText("Obat-obatan / Terapi");
        chkObatTerapi.setBorderPainted(true);
        chkObatTerapi.setBorderPaintedFlat(true);
        chkObatTerapi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkObatTerapi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkObatTerapi.setName("chkObatTerapi"); // NOI18N
        chkObatTerapi.setOpaque(false);
        chkObatTerapi.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkObatTerapi);
        chkObatTerapi.setBounds(510, 1169, 130, 23);

        chkDietNutrisi.setBackground(new java.awt.Color(255, 255, 250));
        chkDietNutrisi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDietNutrisi.setForeground(new java.awt.Color(0, 0, 0));
        chkDietNutrisi.setText("Diet dan Nutrisi");
        chkDietNutrisi.setBorderPainted(true);
        chkDietNutrisi.setBorderPaintedFlat(true);
        chkDietNutrisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDietNutrisi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDietNutrisi.setName("chkDietNutrisi"); // NOI18N
        chkDietNutrisi.setOpaque(false);
        chkDietNutrisi.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkDietNutrisi);
        chkDietNutrisi.setBounds(650, 1169, 100, 23);

        chkRehabilitasi.setBackground(new java.awt.Color(255, 255, 250));
        chkRehabilitasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRehabilitasi.setForeground(new java.awt.Color(0, 0, 0));
        chkRehabilitasi.setText("Rehabilitasi");
        chkRehabilitasi.setBorderPainted(true);
        chkRehabilitasi.setBorderPaintedFlat(true);
        chkRehabilitasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRehabilitasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRehabilitasi.setName("chkRehabilitasi"); // NOI18N
        chkRehabilitasi.setOpaque(false);
        chkRehabilitasi.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkRehabilitasi);
        chkRehabilitasi.setBounds(760, 1169, 83, 23);

        chkManajemenNyeri.setBackground(new java.awt.Color(255, 255, 250));
        chkManajemenNyeri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkManajemenNyeri.setForeground(new java.awt.Color(0, 0, 0));
        chkManajemenNyeri.setText("Manajemen Nyeri");
        chkManajemenNyeri.setBorderPainted(true);
        chkManajemenNyeri.setBorderPaintedFlat(true);
        chkManajemenNyeri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkManajemenNyeri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkManajemenNyeri.setName("chkManajemenNyeri"); // NOI18N
        chkManajemenNyeri.setOpaque(false);
        chkManajemenNyeri.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkManajemenNyeri);
        chkManajemenNyeri.setBounds(850, 1169, 120, 23);

        chkTindakanKep.setBackground(new java.awt.Color(255, 255, 250));
        chkTindakanKep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTindakanKep.setForeground(new java.awt.Color(0, 0, 0));
        chkTindakanKep.setText("Tindakan Keperawatan");
        chkTindakanKep.setBorderPainted(true);
        chkTindakanKep.setBorderPaintedFlat(true);
        chkTindakanKep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTindakanKep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTindakanKep.setName("chkTindakanKep"); // NOI18N
        chkTindakanKep.setOpaque(false);
        chkTindakanKep.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTindakanKep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTindakanKepActionPerformed(evt);
            }
        });
        PanelInput.add(chkTindakanKep);
        chkTindakanKep.setBounds(305, 1197, 140, 23);

        TtindakanKep.setForeground(new java.awt.Color(0, 0, 0));
        TtindakanKep.setName("TtindakanKep"); // NOI18N
        TtindakanKep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtindakanKepKeyPressed(evt);
            }
        });
        PanelInput.add(TtindakanKep);
        TtindakanKep.setBounds(445, 1197, 530, 23);

        chkLainKebutuhan.setBackground(new java.awt.Color(255, 255, 250));
        chkLainKebutuhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainKebutuhan.setForeground(new java.awt.Color(0, 0, 0));
        chkLainKebutuhan.setText("Lain-lain, Sebutkan :");
        chkLainKebutuhan.setBorderPainted(true);
        chkLainKebutuhan.setBorderPaintedFlat(true);
        chkLainKebutuhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainKebutuhan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainKebutuhan.setName("chkLainKebutuhan"); // NOI18N
        chkLainKebutuhan.setOpaque(false);
        chkLainKebutuhan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainKebutuhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainKebutuhanActionPerformed(evt);
            }
        });
        PanelInput.add(chkLainKebutuhan);
        chkLainKebutuhan.setBounds(305, 1225, 125, 23);

        TlainKebutuhan.setForeground(new java.awt.Color(0, 0, 0));
        TlainKebutuhan.setName("TlainKebutuhan"); // NOI18N
        TlainKebutuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainKebutuhanKeyPressed(evt);
            }
        });
        PanelInput.add(TlainKebutuhan);
        TlainKebutuhan.setBounds(430, 1225, 545, 23);

        jLabel133.setForeground(new java.awt.Color(0, 0, 0));
        jLabel133.setText("EDUKASI PASIEN");
        jLabel133.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel133.setName("jLabel133"); // NOI18N
        PanelInput.add(jLabel133);
        jLabel133.setBounds(0, 1253, 130, 23);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setText("Edukasi Awal Disampaikan Tentang Diagnosis, Rencana, Dan Tujuan Terapi Kepada :");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label18);
        label18.setBounds(0, 1281, 440, 23);

        chkPasien.setBackground(new java.awt.Color(255, 255, 250));
        chkPasien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPasien.setForeground(new java.awt.Color(0, 0, 0));
        chkPasien.setText("Pasien");
        chkPasien.setBorderPainted(true);
        chkPasien.setBorderPaintedFlat(true);
        chkPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPasien.setName("chkPasien"); // NOI18N
        chkPasien.setOpaque(false);
        chkPasien.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkPasien);
        chkPasien.setBounds(445, 1281, 60, 23);

        chkKlgPasien.setBackground(new java.awt.Color(255, 255, 250));
        chkKlgPasien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKlgPasien.setForeground(new java.awt.Color(0, 0, 0));
        chkKlgPasien.setText("Keluarga Pasien, Nama :");
        chkKlgPasien.setBorderPainted(true);
        chkKlgPasien.setBorderPaintedFlat(true);
        chkKlgPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKlgPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKlgPasien.setName("chkKlgPasien"); // NOI18N
        chkKlgPasien.setOpaque(false);
        chkKlgPasien.setPreferredSize(new java.awt.Dimension(175, 23));
        chkKlgPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkKlgPasienActionPerformed(evt);
            }
        });
        PanelInput.add(chkKlgPasien);
        chkKlgPasien.setBounds(515, 1281, 140, 23);

        TnmKlgPasien.setForeground(new java.awt.Color(0, 0, 0));
        TnmKlgPasien.setName("TnmKlgPasien"); // NOI18N
        TnmKlgPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmKlgPasienKeyPressed(evt);
            }
        });
        PanelInput.add(TnmKlgPasien);
        TnmKlgPasien.setBounds(660, 1281, 315, 23);

        chkTidakDapat.setBackground(new java.awt.Color(255, 255, 250));
        chkTidakDapat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTidakDapat.setForeground(new java.awt.Color(0, 0, 0));
        chkTidakDapat.setText("Tidak Dapat Memberikan Edukasi Kepada Pasien Atau Keluarga, Karena :");
        chkTidakDapat.setBorderPainted(true);
        chkTidakDapat.setBorderPaintedFlat(true);
        chkTidakDapat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidakDapat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidakDapat.setName("chkTidakDapat"); // NOI18N
        chkTidakDapat.setOpaque(false);
        chkTidakDapat.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTidakDapat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTidakDapatActionPerformed(evt);
            }
        });
        PanelInput.add(chkTidakDapat);
        chkTidakDapat.setBounds(445, 1309, 375, 23);

        TtidakDapat.setForeground(new java.awt.Color(0, 0, 0));
        TtidakDapat.setName("TtidakDapat"); // NOI18N
        TtidakDapat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtidakDapatKeyPressed(evt);
            }
        });
        PanelInput.add(TtidakDapat);
        TtidakDapat.setBounds(445, 1337, 685, 23);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("Tgl. Edukasi :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label19);
        label19.setBounds(0, 1365, 120, 23);

        TtglEdukasi.setEditable(false);
        TtglEdukasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-07-2026" }));
        TtglEdukasi.setDisplayFormat("dd-MM-yyyy");
        TtglEdukasi.setName("TtglEdukasi"); // NOI18N
        TtglEdukasi.setOpaque(false);
        TtglEdukasi.setPreferredSize(new java.awt.Dimension(90, 23));
        PanelInput.add(TtglEdukasi);
        TtglEdukasi.setBounds(125, 1365, 90, 23);

        label20.setForeground(new java.awt.Color(0, 0, 0));
        label20.setText("Jam Edukasi :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label20);
        label20.setBounds(215, 1365, 80, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        PanelInput.add(cmbJam);
        cmbJam.setBounds(300, 1365, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        PanelInput.add(cmbMnt);
        cmbMnt.setBounds(352, 1365, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        PanelInput.add(cmbDtk);
        cmbDtk.setBounds(404, 1365, 45, 23);

        jLabel368.setForeground(new java.awt.Color(0, 0, 0));
        jLabel368.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel368.setText("Wita       Nama Dokter :");
        jLabel368.setName("jLabel368"); // NOI18N
        PanelInput.add(jLabel368);
        jLabel368.setBounds(456, 1365, 118, 23);

        TnmDokter.setEditable(false);
        TnmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter.setName("TnmDokter"); // NOI18N
        PanelInput.add(TnmDokter);
        TnmDokter.setBounds(575, 1365, 420, 23);

        BtnDokter.setForeground(new java.awt.Color(0, 0, 0));
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
        PanelInput.add(BtnDokter);
        BtnDokter.setBounds(1000, 1365, 28, 23);

        jLabel134.setForeground(new java.awt.Color(0, 0, 0));
        jLabel134.setText("LEMBAR DISCHARGE PLANNING");
        jLabel134.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel134.setName("jLabel134"); // NOI18N
        PanelInput.add(jLabel134);
        jLabel134.setBounds(0, 1393, 210, 23);

        label21.setForeground(new java.awt.Color(0, 0, 0));
        label21.setText("Identifikasi, Seleksi / Skrining Pasien :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label21);
        label21.setBounds(0, 1421, 220, 23);

        chkIdentifikai7.setBackground(new java.awt.Color(255, 255, 250));
        chkIdentifikai7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIdentifikai7.setForeground(new java.awt.Color(0, 0, 0));
        chkIdentifikai7.setText("Perkiraan asuhan dengan biaya tinggi");
        chkIdentifikai7.setBorderPainted(true);
        chkIdentifikai7.setBorderPaintedFlat(true);
        chkIdentifikai7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai7.setName("chkIdentifikai7"); // NOI18N
        chkIdentifikai7.setOpaque(false);
        chkIdentifikai7.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkIdentifikai7);
        chkIdentifikai7.setBounds(640, 1449, 210, 23);

        chkIdentifikai3.setBackground(new java.awt.Color(255, 255, 250));
        chkIdentifikai3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIdentifikai3.setForeground(new java.awt.Color(0, 0, 0));
        chkIdentifikai3.setText("Pasien dengan resiko tinggi (Infeksi kejang, Penurunan kesadaran)");
        chkIdentifikai3.setBorderPainted(true);
        chkIdentifikai3.setBorderPaintedFlat(true);
        chkIdentifikai3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai3.setName("chkIdentifikai3"); // NOI18N
        chkIdentifikai3.setOpaque(false);
        chkIdentifikai3.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkIdentifikai3);
        chkIdentifikai3.setBounds(228, 1477, 350, 23);

        chkIdentifikai4.setBackground(new java.awt.Color(255, 255, 250));
        chkIdentifikai4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIdentifikai4.setForeground(new java.awt.Color(0, 0, 0));
        chkIdentifikai4.setText("Potensi komplain tinggi");
        chkIdentifikai4.setBorderPainted(true);
        chkIdentifikai4.setBorderPaintedFlat(true);
        chkIdentifikai4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai4.setName("chkIdentifikai4"); // NOI18N
        chkIdentifikai4.setOpaque(false);
        chkIdentifikai4.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkIdentifikai4);
        chkIdentifikai4.setBounds(228, 1505, 140, 23);

        chkIdentifikai9.setBackground(new java.awt.Color(255, 255, 250));
        chkIdentifikai9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIdentifikai9.setForeground(new java.awt.Color(0, 0, 0));
        chkIdentifikai9.setText("Kasus yang melebihi rata-rata lama dirawat");
        chkIdentifikai9.setBorderPainted(true);
        chkIdentifikai9.setBorderPaintedFlat(true);
        chkIdentifikai9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai9.setName("chkIdentifikai9"); // NOI18N
        chkIdentifikai9.setOpaque(false);
        chkIdentifikai9.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkIdentifikai9);
        chkIdentifikai9.setBounds(640, 1505, 240, 23);

        label22.setForeground(new java.awt.Color(0, 0, 0));
        label22.setText("Memerlukan :");
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label22);
        label22.setBounds(0, 1561, 220, 23);

        Tmemerlukan.setForeground(new java.awt.Color(0, 0, 0));
        Tmemerlukan.setName("Tmemerlukan"); // NOI18N
        Tmemerlukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmemerlukanKeyPressed(evt);
            }
        });
        PanelInput.add(Tmemerlukan);
        Tmemerlukan.setBounds(228, 1561, 685, 23);

        label25.setForeground(new java.awt.Color(0, 0, 0));
        label25.setText("Nama Pasien / Keluarga Pasien :");
        label25.setName("label25"); // NOI18N
        label25.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label25);
        label25.setBounds(0, 1617, 220, 23);

        TnmKeluargaPasien.setForeground(new java.awt.Color(0, 0, 0));
        TnmKeluargaPasien.setName("TnmKeluargaPasien"); // NOI18N
        TnmKeluargaPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmKeluargaPasienKeyPressed(evt);
            }
        });
        PanelInput.add(TnmKeluargaPasien);
        TnmKeluargaPasien.setBounds(228, 1617, 330, 23);

        label26.setForeground(new java.awt.Color(0, 0, 0));
        label26.setText("Nama Bidan :");
        label26.setName("label26"); // NOI18N
        label26.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label26);
        label26.setBounds(560, 1617, 80, 23);

        TnmBidan2.setEditable(false);
        TnmBidan2.setForeground(new java.awt.Color(0, 0, 0));
        TnmBidan2.setName("TnmBidan2"); // NOI18N
        PanelInput.add(TnmBidan2);
        TnmBidan2.setBounds(646, 1617, 350, 23);

        BtnBidan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnBidan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnBidan2.setMnemonic('2');
        BtnBidan2.setToolTipText("Alt+2");
        BtnBidan2.setName("BtnBidan2"); // NOI18N
        BtnBidan2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnBidan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBidan2ActionPerformed(evt);
            }
        });
        PanelInput.add(BtnBidan2);
        BtnBidan2.setBounds(1000, 1617, 28, 23);

        chkSaya2.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya2.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya2.setText("Saya Sendiri");
        chkSaya2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya2.setName("chkSaya2"); // NOI18N
        chkSaya2.setOpaque(false);
        chkSaya2.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya2ActionPerformed(evt);
            }
        });
        PanelInput.add(chkSaya2);
        chkSaya2.setBounds(1035, 1617, 90, 23);

        chkFisik.setBackground(new java.awt.Color(255, 255, 250));
        chkFisik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFisik.setForeground(new java.awt.Color(0, 0, 0));
        chkFisik.setText("Fisik");
        chkFisik.setBorderPainted(true);
        chkFisik.setBorderPaintedFlat(true);
        chkFisik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFisik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFisik.setName("chkFisik"); // NOI18N
        chkFisik.setOpaque(false);
        chkFisik.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkFisik);
        chkFisik.setBounds(598, 1085, 50, 23);

        label27.setForeground(new java.awt.Color(0, 0, 0));
        label27.setText("Tanggal :");
        label27.setName("label27"); // NOI18N
        label27.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label27);
        label27.setBounds(580, 1589, 60, 23);

        TtglDp.setEditable(false);
        TtglDp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-07-2026" }));
        TtglDp.setDisplayFormat("dd-MM-yyyy");
        TtglDp.setName("TtglDp"); // NOI18N
        TtglDp.setOpaque(false);
        TtglDp.setPreferredSize(new java.awt.Dimension(90, 23));
        PanelInput.add(TtglDp);
        TtglDp.setBounds(645, 1589, 90, 23);

        jLabel369.setForeground(new java.awt.Color(0, 0, 0));
        jLabel369.setText("Pasien : ");
        jLabel369.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel369.setName("jLabel369"); // NOI18N
        PanelInput.add(jLabel369);
        jLabel369.setBounds(740, 10, 120, 23);

        jLabel370.setForeground(new java.awt.Color(0, 0, 0));
        jLabel370.setText("Ruang Rawat : ");
        jLabel370.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel370.setName("jLabel370"); // NOI18N
        PanelInput.add(jLabel370);
        jLabel370.setBounds(740, 38, 120, 23);

        jLabel371.setForeground(new java.awt.Color(0, 0, 0));
        jLabel371.setText("Umur Pasien : ");
        jLabel371.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel371.setName("jLabel371"); // NOI18N
        PanelInput.add(jLabel371);
        jLabel371.setBounds(740, 66, 120, 23);

        jLabel372.setForeground(new java.awt.Color(0, 0, 0));
        jLabel372.setText("Nama Suami : ");
        jLabel372.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel372.setName("jLabel372"); // NOI18N
        PanelInput.add(jLabel372);
        jLabel372.setBounds(740, 94, 120, 23);

        Tpasien.setForeground(new java.awt.Color(0, 0, 0));
        Tpasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Tpasien.setText("pasien");
        Tpasien.setName("Tpasien"); // NOI18N
        PanelInput.add(Tpasien);
        Tpasien.setBounds(865, 10, 750, 23);

        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TrgRawat.setText("ruang rawat");
        TrgRawat.setName("TrgRawat"); // NOI18N
        PanelInput.add(TrgRawat);
        TrgRawat.setBounds(865, 38, 750, 23);

        TumurPas.setForeground(new java.awt.Color(0, 0, 0));
        TumurPas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TumurPas.setText("umur pasien");
        TumurPas.setName("TumurPas"); // NOI18N
        PanelInput.add(TumurPas);
        TumurPas.setBounds(865, 66, 750, 23);

        TnmSuami.setForeground(new java.awt.Color(0, 0, 0));
        TnmSuami.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TnmSuami.setText("nama suami");
        TnmSuami.setName("TnmSuami"); // NOI18N
        PanelInput.add(TnmSuami);
        TnmSuami.setBounds(865, 94, 750, 23);

        jLabel373.setForeground(new java.awt.Color(0, 0, 0));
        jLabel373.setText("Alasan Masuk RS : ");
        jLabel373.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel373.setName("jLabel373"); // NOI18N
        PanelInput.add(jLabel373);
        jLabel373.setBounds(740, 122, 120, 23);

        TalasanMrs.setForeground(new java.awt.Color(0, 0, 0));
        TalasanMrs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TalasanMrs.setText("alasan mrs");
        TalasanMrs.setName("TalasanMrs"); // NOI18N
        PanelInput.add(TalasanMrs);
        TalasanMrs.setBounds(865, 122, 750, 23);

        jLabel135.setForeground(new java.awt.Color(0, 0, 0));
        jLabel135.setText("ASSESMEN RESIKO JATUH MORSE");
        jLabel135.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel135.setName("jLabel135"); // NOI18N
        PanelInput.add(jLabel135);
        jLabel135.setBounds(0, 693, 220, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Resiko Jatuh :");
        jLabel93.setName("jLabel93"); // NOI18N
        PanelInput.add(jLabel93);
        jLabel93.setBounds(0, 721, 120, 23);

        cmbResJatuh.setForeground(new java.awt.Color(0, 0, 0));
        cmbResJatuh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "< dari 3 bulan", "Tidak ada atau >= 3 bulan" }));
        cmbResJatuh.setName("cmbResJatuh"); // NOI18N
        cmbResJatuh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbResJatuhActionPerformed(evt);
            }
        });
        PanelInput.add(cmbResJatuh);
        cmbResJatuh.setBounds(125, 721, 160, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Kondisi Kesehatan :");
        jLabel94.setName("jLabel94"); // NOI18N
        PanelInput.add(jLabel94);
        jLabel94.setBounds(0, 749, 120, 23);

        cmbKondisi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKondisi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "> diagnosa penyakit", "< diagnosa penyakit" }));
        cmbKondisi.setName("cmbKondisi"); // NOI18N
        cmbKondisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKondisiActionPerformed(evt);
            }
        });
        PanelInput.add(cmbKondisi);
        cmbKondisi.setBounds(125, 749, 130, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Alat Bantu :");
        jLabel95.setName("jLabel95"); // NOI18N
        PanelInput.add(jLabel95);
        jLabel95.setBounds(0, 777, 120, 23);

        cmbResAlatBantu.setForeground(new java.awt.Color(0, 0, 0));
        cmbResAlatBantu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Berpegangan pada perabot", "Tongkat/alat penopang", "Tidak ada/kursi roda/tirah baring" }));
        cmbResAlatBantu.setName("cmbResAlatBantu"); // NOI18N
        cmbResAlatBantu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbResAlatBantuActionPerformed(evt);
            }
        });
        PanelInput.add(cmbResAlatBantu);
        cmbResAlatBantu.setBounds(125, 777, 190, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Terapi IV :");
        jLabel96.setName("jLabel96"); // NOI18N
        PanelInput.add(jLabel96);
        jLabel96.setBounds(0, 805, 120, 23);

        cmbTerapiIV.setForeground(new java.awt.Color(0, 0, 0));
        cmbTerapiIV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Terapi IV terus menerus", "Tidak" }));
        cmbTerapiIV.setName("cmbTerapiIV"); // NOI18N
        cmbTerapiIV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTerapiIVActionPerformed(evt);
            }
        });
        PanelInput.add(cmbTerapiIV);
        cmbTerapiIV.setBounds(125, 805, 150, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Gaya Berjalan :");
        jLabel97.setName("jLabel97"); // NOI18N
        PanelInput.add(jLabel97);
        jLabel97.setBounds(0, 833, 120, 23);

        cmbGaya.setForeground(new java.awt.Color(0, 0, 0));
        cmbGaya.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kerusakan/terganggu", "Lemah", "Normal/tirah baring/immobilisasi" }));
        cmbGaya.setName("cmbGaya"); // NOI18N
        cmbGaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGayaActionPerformed(evt);
            }
        });
        PanelInput.add(cmbGaya);
        cmbGaya.setBounds(125, 833, 185, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Status Mental :");
        jLabel98.setName("jLabel98"); // NOI18N
        PanelInput.add(jLabel98);
        jLabel98.setBounds(0, 861, 120, 23);

        cmbSttsMental.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsMental.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Lupa keterbatasan yang dimiliki", "Sadar kemampuan diri sendiri" }));
        cmbSttsMental.setName("cmbSttsMental"); // NOI18N
        cmbSttsMental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSttsMentalActionPerformed(evt);
            }
        });
        PanelInput.add(cmbSttsMental);
        cmbSttsMental.setBounds(125, 861, 185, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Skor :");
        jLabel99.setName("jLabel99"); // NOI18N
        PanelInput.add(jLabel99);
        jLabel99.setBounds(320, 721, 50, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Skor :");
        jLabel101.setName("jLabel101"); // NOI18N
        PanelInput.add(jLabel101);
        jLabel101.setBounds(320, 749, 50, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Skor :");
        jLabel102.setName("jLabel102"); // NOI18N
        PanelInput.add(jLabel102);
        jLabel102.setBounds(320, 777, 50, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Skor :");
        jLabel103.setName("jLabel103"); // NOI18N
        PanelInput.add(jLabel103);
        jLabel103.setBounds(320, 805, 50, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Skor :");
        jLabel104.setName("jLabel104"); // NOI18N
        PanelInput.add(jLabel104);
        jLabel104.setBounds(320, 833, 50, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Skor :");
        jLabel105.setName("jLabel105"); // NOI18N
        PanelInput.add(jLabel105);
        jLabel105.setBounds(320, 861, 50, 23);

        TskorResJatuh.setEditable(false);
        TskorResJatuh.setForeground(new java.awt.Color(0, 0, 0));
        TskorResJatuh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorResJatuh.setText("0");
        TskorResJatuh.setFocusTraversalPolicyProvider(true);
        TskorResJatuh.setName("TskorResJatuh"); // NOI18N
        PanelInput.add(TskorResJatuh);
        TskorResJatuh.setBounds(375, 721, 40, 23);

        TskorKondisi.setEditable(false);
        TskorKondisi.setForeground(new java.awt.Color(0, 0, 0));
        TskorKondisi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorKondisi.setText("0");
        TskorKondisi.setFocusTraversalPolicyProvider(true);
        TskorKondisi.setName("TskorKondisi"); // NOI18N
        PanelInput.add(TskorKondisi);
        TskorKondisi.setBounds(375, 749, 40, 23);

        TskorResAlatBantu.setEditable(false);
        TskorResAlatBantu.setForeground(new java.awt.Color(0, 0, 0));
        TskorResAlatBantu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorResAlatBantu.setText("0");
        TskorResAlatBantu.setFocusTraversalPolicyProvider(true);
        TskorResAlatBantu.setName("TskorResAlatBantu"); // NOI18N
        PanelInput.add(TskorResAlatBantu);
        TskorResAlatBantu.setBounds(375, 777, 40, 23);

        TskorTerapi.setEditable(false);
        TskorTerapi.setForeground(new java.awt.Color(0, 0, 0));
        TskorTerapi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorTerapi.setText("0");
        TskorTerapi.setFocusTraversalPolicyProvider(true);
        TskorTerapi.setName("TskorTerapi"); // NOI18N
        PanelInput.add(TskorTerapi);
        TskorTerapi.setBounds(375, 805, 40, 23);

        TskorGaya.setEditable(false);
        TskorGaya.setForeground(new java.awt.Color(0, 0, 0));
        TskorGaya.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorGaya.setText("0");
        TskorGaya.setFocusTraversalPolicyProvider(true);
        TskorGaya.setName("TskorGaya"); // NOI18N
        PanelInput.add(TskorGaya);
        TskorGaya.setBounds(375, 833, 40, 23);

        TskorSttsMental.setEditable(false);
        TskorSttsMental.setForeground(new java.awt.Color(0, 0, 0));
        TskorSttsMental.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorSttsMental.setText("0");
        TskorSttsMental.setFocusTraversalPolicyProvider(true);
        TskorSttsMental.setName("TskorSttsMental"); // NOI18N
        PanelInput.add(TskorSttsMental);
        TskorSttsMental.setBounds(375, 861, 40, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("Jumlah Skor :");
        jLabel106.setName("jLabel106"); // NOI18N
        PanelInput.add(jLabel106);
        jLabel106.setBounds(420, 861, 80, 23);

        TJmlSkor.setEditable(false);
        TJmlSkor.setForeground(new java.awt.Color(0, 0, 0));
        TJmlSkor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TJmlSkor.setText("0");
        TJmlSkor.setFocusTraversalPolicyProvider(true);
        TJmlSkor.setName("TJmlSkor"); // NOI18N
        PanelInput.add(TJmlSkor);
        TJmlSkor.setBounds(505, 861, 60, 23);

        kesimpulanResJatuh.setEditable(false);
        kesimpulanResJatuh.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), " Kesimpulan Asesmen Resiko Jatuh Morse : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanResJatuh.setColumns(20);
        kesimpulanResJatuh.setRows(5);
        kesimpulanResJatuh.setName("kesimpulanResJatuh"); // NOI18N
        PanelInput.add(kesimpulanResJatuh);
        kesimpulanResJatuh.setBounds(430, 721, 350, 50);

        chkIdentifikai1.setBackground(new java.awt.Color(242, 242, 242));
        chkIdentifikai1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        chkIdentifikai1.setText("Pasien dengan keterbatasan kognitif, ketergantungan ADL tinggi.");
        chkIdentifikai1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai1.setName("chkIdentifikai1"); // NOI18N
        chkIdentifikai1.setPreferredSize(new java.awt.Dimension(95, 23));
        PanelInput.add(chkIdentifikai1);
        chkIdentifikai1.setBounds(228, 1421, 350, 23);

        chkIdentifikai2.setBackground(new java.awt.Color(242, 242, 242));
        chkIdentifikai2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        chkIdentifikai2.setText("Wanita usia rentan (Ibu hamil, Ibu menyusui, Lansia)");
        chkIdentifikai2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai2.setName("chkIdentifikai2"); // NOI18N
        chkIdentifikai2.setPreferredSize(new java.awt.Dimension(95, 23));
        PanelInput.add(chkIdentifikai2);
        chkIdentifikai2.setBounds(228, 1449, 280, 23);

        chkIdentifikai5.setBackground(new java.awt.Color(242, 242, 242));
        chkIdentifikai5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        chkIdentifikai5.setText("Pasien dengan penyakit kronis, katastropik (Penyakit Degenerative) terminal");
        chkIdentifikai5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai5.setName("chkIdentifikai5"); // NOI18N
        chkIdentifikai5.setPreferredSize(new java.awt.Dimension(95, 23));
        PanelInput.add(chkIdentifikai5);
        chkIdentifikai5.setBounds(228, 1533, 400, 23);

        chkIdentifikai6.setBackground(new java.awt.Color(242, 242, 242));
        chkIdentifikai6.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        chkIdentifikai6.setText("Sering masuk IGD, readmisi RS");
        chkIdentifikai6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai6.setName("chkIdentifikai6"); // NOI18N
        chkIdentifikai6.setPreferredSize(new java.awt.Dimension(95, 23));
        PanelInput.add(chkIdentifikai6);
        chkIdentifikai6.setBounds(640, 1421, 180, 23);

        chkIdentifikai8.setBackground(new java.awt.Color(242, 242, 242));
        chkIdentifikai8.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        chkIdentifikai8.setText("Pasien tanpa keluarga / terlantar, tinggal sendiri");
        chkIdentifikai8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai8.setName("chkIdentifikai8"); // NOI18N
        chkIdentifikai8.setPreferredSize(new java.awt.Dimension(95, 23));
        PanelInput.add(chkIdentifikai8);
        chkIdentifikai8.setBounds(640, 1477, 260, 23);

        chkIdentifikai10.setBackground(new java.awt.Color(242, 242, 242));
        chkIdentifikai10.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        chkIdentifikai10.setText("Kasus yang membutuhkan kontinuitas pelayanan, rencana pemulangan penting / beresiko");
        chkIdentifikai10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai10.setName("chkIdentifikai10"); // NOI18N
        chkIdentifikai10.setPreferredSize(new java.awt.Dimension(95, 23));
        PanelInput.add(chkIdentifikai10);
        chkIdentifikai10.setBounds(640, 1533, 460, 23);

        chkMPP.setBackground(new java.awt.Color(242, 242, 242));
        chkMPP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMPP.setForeground(new java.awt.Color(0, 0, 0));
        chkMPP.setText("Manajer Pelayanan Pasien");
        chkMPP.setBorderPainted(true);
        chkMPP.setBorderPaintedFlat(true);
        chkMPP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMPP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMPP.setName("chkMPP"); // NOI18N
        chkMPP.setOpaque(false);
        chkMPP.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkMPP);
        chkMPP.setBounds(228, 1589, 160, 23);

        chkDP.setBackground(new java.awt.Color(242, 242, 242));
        chkDP.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        chkDP.setText("Discharge Planning");
        chkDP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDP.setName("chkDP"); // NOI18N
        chkDP.setPreferredSize(new java.awt.Dimension(95, 23));
        PanelInput.add(chkDP);
        chkDP.setBounds(400, 1589, 120, 23);

        scrollInput.setViewportView(PanelInput);

        internalFrame25.add(scrollInput, java.awt.BorderLayout.CENTER);

        panelTombol.setName("panelTombol"); // NOI18N
        panelTombol.setPreferredSize(new java.awt.Dimension(44, 54));
        panelTombol.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelTombol.add(BtnSimpan);

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
        panelTombol.add(BtnBatal);

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
        panelTombol.add(BtnHapus);

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
        panelTombol.add(BtnEdit);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Cetak Dalam Bentuk :");
        jLabel63.setName("jLabel63"); // NOI18N
        jLabel63.setPreferredSize(new java.awt.Dimension(120, 23));
        panelTombol.add(jLabel63);

        cmbPilihCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak.setName("cmbPilihCetak"); // NOI18N
        cmbPilihCetak.setPreferredSize(new java.awt.Dimension(105, 23));
        panelTombol.add(cmbPilihCetak);

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
        panelTombol.add(BtnPrint);

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
        panelTombol.add(BtnKeluar);

        internalFrame25.add(panelTombol, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Input Asesmen hal. 2", internalFrame25);

        internalFrame21.setBorder(null);
        internalFrame21.setName("internalFrame21"); // NOI18N
        internalFrame21.setLayout(new java.awt.BorderLayout());

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbAsesmen.setAutoCreateRowSorter(true);
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

        panelGlass10.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(282, 44));
        panelGlass11.setLayout(null);

        panelGlass12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Scan QR Untuk TTD ]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));

        scrollPane7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrollPane7.setName("scrollPane7"); // NOI18N
        scrollPane7.setPreferredSize(new java.awt.Dimension(210, 220));

        gambarQR.setBackground(new java.awt.Color(245, 255, 235));
        gambarQR.setForeground(new java.awt.Color(235, 255, 235));
        gambarQR.setName("gambarQR"); // NOI18N
        scrollPane7.setViewportView(gambarQR);

        panelGlass12.add(scrollPane7);

        panelGlass11.add(panelGlass12);
        panelGlass12.setBounds(12, 10, 230, 245);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel107.setText("<html><b>Catatan :</b><br>Perangkat (smartphone / tab) harus terhubung dengan wifi rumah sakit diruangan ini terlebih dulu.</html>");
        jLabel107.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel107.setName("jLabel107"); // NOI18N
        jLabel107.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        panelGlass11.add(jLabel107);
        jLabel107.setBounds(20, 262, 210, 60);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: TANDA TANGAN :.", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        LoadHTML2.setBorder(null);
        LoadHTML2.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML2.setName("LoadHTML2"); // NOI18N
        Scroll5.setViewportView(LoadHTML2);

        panelGlass11.add(Scroll5);
        Scroll5.setBounds(12, 335, 260, 240);

        panelGlass10.add(panelGlass11, java.awt.BorderLayout.EAST);

        internalFrame21.add(panelGlass10, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

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

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setMnemonic('H');
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Alt+H");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnHapus1);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Cetak Dalam Bentuk :");
        jLabel66.setName("jLabel66"); // NOI18N
        jLabel66.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass9.add(jLabel66);

        cmbPilihCetak1.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetak1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak1.setName("cmbPilihCetak1"); // NOI18N
        cmbPilihCetak1.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass9.add(cmbPilihCetak1);

        BtnPrint1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setText("Cetak");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnPrint1);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnKeluar1);

        internalFrame21.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Asesmen hal. 2", internalFrame21);

        internalFrame4.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll19.setName("Scroll19"); // NOI18N
        Scroll19.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll19.setViewportView(LoadHTML1);

        internalFrame4.add(Scroll19, java.awt.BorderLayout.CENTER);

        panelGlass2.setName("panelGlass2"); // NOI18N
        panelGlass2.setPreferredSize(new java.awt.Dimension(44, 47));
        panelGlass2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('6');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+6");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        panelGlass2.add(BtnCari1);

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
        panelGlass2.add(BtnKeluar2);

        internalFrame4.add(panelGlass2, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Preview Asesmen Awal Kebidanan (hal. 1 & 2)", internalFrame4);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbNyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNyeriActionPerformed
        Tlokasi.setText("");
        if (cmbNyeri.getSelectedIndex() == 1) {
            Tlokasi.setEnabled(true);
            Tlokasi.requestFocus();
        } else {
            Tlokasi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbNyeriActionPerformed

    private void TlokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlokasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJenis.requestFocus();
        }
    }//GEN-LAST:event_TlokasiKeyPressed

    private void cmbProvoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvoActionPerformed
        Tprovo.setText("");
        if (cmbProvo.getSelectedIndex() == 6) {
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

    private void cmbTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTimeActionPerformed
        cmbLama.setSelectedIndex(0);
        if (cmbTime.getSelectedIndex() == 0) {
            cmbLama.setEnabled(false);
        } else {
            cmbLama.setEnabled(true);
            cmbLama.requestFocus();
        }
    }//GEN-LAST:event_cmbTimeActionPerformed

    private void cmbGizi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGizi1ActionPerformed
        cmbYaGizi1.setSelectedIndex(0);
        skorYaGizi1.setText("0");

        if (cmbGizi1.getSelectedIndex() == 0) {
            skorGizi1.setText("0");
            cmbYaGizi1.setEnabled(false);
        } else if (cmbGizi1.getSelectedIndex() == 1) {
            skorGizi1.setText("2");
            cmbYaGizi1.setEnabled(false);
        } else if (cmbGizi1.getSelectedIndex() == 2) {
            skorGizi1.setText("0");
            cmbYaGizi1.setEnabled(true);
        }
        hitungSkorGizi();
    }//GEN-LAST:event_cmbGizi1ActionPerformed

    private void chkRiwAlergiObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRiwAlergiObatActionPerformed
        TketRiwAlergiObat.setText("");
        TreakRiwAlergiObat.setText("");
        if (chkRiwAlergiObat.isSelected() == true) {
            TketRiwAlergiObat.setEnabled(true);
            TreakRiwAlergiObat.setEnabled(true);
            TketRiwAlergiObat.requestFocus();
        } else {
            TketRiwAlergiObat.setEnabled(false);
            TreakRiwAlergiObat.setEnabled(false);
        }
    }//GEN-LAST:event_chkRiwAlergiObatActionPerformed

    private void TketRiwAlergiObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketRiwAlergiObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TreakRiwAlergiObat.requestFocus();
        }
    }//GEN-LAST:event_TketRiwAlergiObatKeyPressed

    private void cmbYaGizi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbYaGizi1ActionPerformed
        if (cmbYaGizi1.getSelectedIndex() == 0) {
            skorYaGizi1.setText("0");
        } else if (cmbYaGizi1.getSelectedIndex() == 1) {
            skorYaGizi1.setText("1");
        } else if (cmbYaGizi1.getSelectedIndex() == 2) {
            skorYaGizi1.setText("2");
        } else if (cmbYaGizi1.getSelectedIndex() == 3) {
            skorYaGizi1.setText("3");
        } else if (cmbYaGizi1.getSelectedIndex() == 4) {
            skorYaGizi1.setText("4");
        } else if (cmbYaGizi1.getSelectedIndex() == 5) {
            skorYaGizi1.setText("2");
        }
        hitungSkorGizi();
    }//GEN-LAST:event_cmbYaGizi1ActionPerformed

    private void TreakRiwAlergiObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreakRiwAlergiObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkRiwAlergiMak.requestFocus();
        }
    }//GEN-LAST:event_TreakRiwAlergiObatKeyPressed

    private void chkRiwAlergiMakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRiwAlergiMakActionPerformed
        TketRiwAlergiMak.setText("");
        TreakRiwAlergiMak.setText("");
        if (chkRiwAlergiMak.isSelected() == true) {
            TketRiwAlergiMak.setEnabled(true);
            TreakRiwAlergiMak.setEnabled(true);
            TketRiwAlergiMak.requestFocus();
        } else {
            TketRiwAlergiMak.setEnabled(false);
            TreakRiwAlergiMak.setEnabled(false);
        }
    }//GEN-LAST:event_chkRiwAlergiMakActionPerformed

    private void TketRiwAlergiMakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketRiwAlergiMakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TreakRiwAlergiMak.requestFocus();
        }
    }//GEN-LAST:event_TketRiwAlergiMakKeyPressed

    private void cmbGizi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGizi2ActionPerformed
        if (cmbGizi2.getSelectedIndex() == 0) {
            skorGizi2.setText("0");
        } else if (cmbGizi2.getSelectedIndex() == 1) {
            skorGizi2.setText("1");
        }
        hitungSkorGizi();
    }//GEN-LAST:event_cmbGizi2ActionPerformed

    private void TreakRiwAlergiMakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreakRiwAlergiMakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkRiwAlergiLain.requestFocus();
        }
    }//GEN-LAST:event_TreakRiwAlergiMakKeyPressed

    private void chkRiwAlergiLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRiwAlergiLainActionPerformed
        TketRiwAlergiLain.setText("");
        TreakRiwAlergiLain.setText("");
        if (chkRiwAlergiLain.isSelected() == true) {
            TketRiwAlergiLain.setEnabled(true);
            TreakRiwAlergiLain.setEnabled(true);
            TketRiwAlergiLain.requestFocus();
        } else {
            TketRiwAlergiLain.setEnabled(false);
            TreakRiwAlergiLain.setEnabled(false);
        }
    }//GEN-LAST:event_chkRiwAlergiLainActionPerformed

    private void TketRiwAlergiLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketRiwAlergiLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TreakRiwAlergiLain.requestFocus();
        }
    }//GEN-LAST:event_TketRiwAlergiLainKeyPressed

    private void TreakRiwAlergiLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreakRiwAlergiLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkGelang.requestFocus();
        }
    }//GEN-LAST:event_TreakRiwAlergiLainKeyPressed

    private void TalatBantuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalatBantuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tprotesis.requestFocus();
        }
    }//GEN-LAST:event_TalatBantuKeyPressed

    private void TprotesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprotesisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TcacatTubuh.requestFocus();
        }
    }//GEN-LAST:event_TprotesisKeyPressed

    private void TcacatTubuhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcacatTubuhKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbAdl.requestFocus();
        }
    }//GEN-LAST:event_TcacatTubuhKeyPressed

    private void BtnBidan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBidan1ActionPerformed
        initPetugas();
        pilihan = 0;
        pilihan = 1;
        akses.setform("RMAsesmenAwalKebidanan2");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnBidan1ActionPerformed

    private void chkSaya1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya1ActionPerformed
        if (chkSaya1.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipBidan1 = "-";
                TnmBidan1.setText("-");
            } else {
                nipBidan1 = akses.getkode();
                TnmBidan1.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipBidan1 + "'"));
            }
        } else {
            nipBidan1 = "-";
            TnmBidan1.setText("-");
        }
    }//GEN-LAST:event_chkSaya1ActionPerformed

    private void chkYaTerdapatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkYaTerdapatActionPerformed
        chkPendengaran.setSelected(false);
        chkPenglihatan.setSelected(false);
        chkKognitif.setSelected(false);
        chkFisik.setSelected(false);
        chkBudaya.setSelected(false);
        chkEmosi.setSelected(false);
        chkBahasa.setSelected(false);
        chkLainHambatan.setSelected(false);
        TketLainHambatan.setText("");
        if (chkYaTerdapat.isSelected() == true) {
            chkPendengaran.setEnabled(true);
            chkPenglihatan.setEnabled(true);
            chkKognitif.setEnabled(true);
            chkFisik.setEnabled(true);
            chkBudaya.setEnabled(true);
            chkEmosi.setEnabled(true);
            chkBahasa.setEnabled(true);
            chkLainHambatan.setEnabled(true);
            TketLainHambatan.setEnabled(false);
            chkPendengaran.requestFocus();
        } else {
            chkPendengaran.setEnabled(false);
            chkPenglihatan.setEnabled(false);
            chkKognitif.setEnabled(false);
            chkFisik.setEnabled(false);
            chkBudaya.setEnabled(false);
            chkEmosi.setEnabled(false);
            chkBahasa.setEnabled(false);
            chkLainHambatan.setEnabled(false);
            TketLainHambatan.setEnabled(false);
        }
    }//GEN-LAST:event_chkYaTerdapatActionPerformed

    private void chkLainHambatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainHambatanActionPerformed
        TketLainHambatan.setText("");
        if (chkLainHambatan.isSelected() == true) {
            TketLainHambatan.setEnabled(true);
            TketLainHambatan.requestFocus();
        } else {
            TketLainHambatan.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainHambatanActionPerformed

    private void TketLainHambatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketLainHambatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbDibutuhkan.requestFocus();
        }
    }//GEN-LAST:event_TketLainHambatanKeyPressed

    private void cmbDibutuhkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDibutuhkanActionPerformed
        Tsebutkan.setText("");
        if (cmbDibutuhkan.getSelectedIndex() == 1) {
            Tsebutkan.setEnabled(true);
            Tsebutkan.requestFocus();
        } else {
            Tsebutkan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbDibutuhkanActionPerformed

    private void TsebutkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsebutkanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbBahasa.requestFocus();
        }
    }//GEN-LAST:event_TsebutkanKeyPressed

    private void chkTindakanKepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTindakanKepActionPerformed
        TtindakanKep.setText("");
        if (chkTindakanKep.isSelected() == true) {
            TtindakanKep.setEnabled(true);
            TtindakanKep.requestFocus();
        } else {
            TtindakanKep.setEnabled(false);
        }
    }//GEN-LAST:event_chkTindakanKepActionPerformed

    private void TtindakanKepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtindakanKepKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkLainKebutuhan.requestFocus();
        }
    }//GEN-LAST:event_TtindakanKepKeyPressed

    private void chkLainKebutuhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainKebutuhanActionPerformed
        TlainKebutuhan.setText("");
        if (chkLainKebutuhan.isSelected() == true) {
            TlainKebutuhan.setEnabled(true);
            TlainKebutuhan.requestFocus();
        } else {
            TlainKebutuhan.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainKebutuhanActionPerformed

    private void TlainKebutuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainKebutuhanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkPasien.requestFocus();
        }
    }//GEN-LAST:event_TlainKebutuhanKeyPressed

    private void chkKlgPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkKlgPasienActionPerformed
        TnmKlgPasien.setText("");
        if (chkKlgPasien.isSelected() == true) {
            TnmKlgPasien.setEnabled(true);
            TnmKlgPasien.requestFocus();
        } else {
            TnmKlgPasien.setEnabled(false);
        }
    }//GEN-LAST:event_chkKlgPasienActionPerformed

    private void TnmKlgPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmKlgPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTidakDapat.requestFocus();
        }
    }//GEN-LAST:event_TnmKlgPasienKeyPressed

    private void chkTidakDapatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTidakDapatActionPerformed
        TtidakDapat.setText("");
        if (chkTidakDapat.isSelected() == true) {
            TtidakDapat.setEnabled(true);
            TtidakDapat.requestFocus();
        } else {
            TtidakDapat.setEnabled(false);
        }
    }//GEN-LAST:event_chkTidakDapatActionPerformed

    private void TtidakDapatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtidakDapatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglEdukasi.requestFocus();
        }
    }//GEN-LAST:event_TtidakDapatKeyPressed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        initDokter();
        akses.setform("RMAsesmenAwalKebidanan2");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void TmemerlukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmemerlukanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkMPP.requestFocus();
        }
    }//GEN-LAST:event_TmemerlukanKeyPressed

    private void TnmKeluargaPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmKeluargaPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnBidan2.requestFocus();
        }
    }//GEN-LAST:event_TnmKeluargaPasienKeyPressed

    private void BtnBidan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBidan2ActionPerformed
        initPetugas();
        pilihan = 0;
        pilihan = 2;
        akses.setform("RMAsesmenAwalKebidanan2");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);;
    }//GEN-LAST:event_BtnBidan2ActionPerformed

    private void chkSaya2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya2ActionPerformed
        if (chkSaya2.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipBidan2 = "-";
                TnmBidan2.setText("-");
            } else {
                nipBidan2 = akses.getkode();
                TnmBidan2.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipBidan2 + "'"));
            }
        } else {
            nipBidan2 = "-";
            TnmBidan2.setText("-");
        }
    }//GEN-LAST:event_chkSaya2ActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            scrollKeAtas();
        } else if (TabRawat.getSelectedIndex() == 1) {
            ((RMAsesmenAwalKebidanan2.Painter) gambarQR).setImage("");
            tampil(noRawat);
        } else if (TabRawat.getSelectedIndex() == 2) {
            if (tbAsesmen.getSelectedRow() > -1 || Sequel.cariInteger("select count(-1) from asesmen_awal_kebidanan1 where no_rawat='" + noRawat + "'") > 0) {
                tampilPreview();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel..!!");
                TabRawat.setSelectedIndex(1);
                tampil(noRawat);
                emptTeks();
            }
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (noRawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu pasiennya terlebih dulu..!!");
        } else {
            cekData();
            if (Sequel.menyimpantf("asesmen_awal_kebidanan2", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 94, new String[]{
                        noRawat, cmbNyeri.getSelectedItem().toString(), Tlokasi.getText(), cmbJenis.getSelectedItem().toString(), cmbSkala.getSelectedItem().toString(),
                        cmbProvo.getSelectedItem().toString(), Tprovo.getText(), cmbQuality.getSelectedItem().toString(), Tquality.getText(), cmbRadia.getSelectedItem().toString(),
                        cmbSever.getSelectedItem().toString(), cmbTime.getSelectedItem().toString(), cmbLama.getSelectedItem().toString(), cmbGizi1.getSelectedItem().toString(),
                        cmbYaGizi1.getSelectedItem().toString(), cmbGizi2.getSelectedItem().toString(), tidakAda, tidakDiketahui, alergiObat, TketRiwAlergiObat.getText(),
                        TreakRiwAlergiObat.getText(), alergiMakanan, TketRiwAlergiMak.getText(), TreakRiwAlergiMak.getText(), alergiLainya, TketRiwAlergiLain.getText(),
                        TreakRiwAlergiLain.getText(), gelangTanda, alergiDiberitahukanDokter, alergiDiberitahukanFarmasis, alergiDiberitahukanAhligizi, TalatBantu.getText(),
                        Tprotesis.getText(), TcacatTubuh.getText(), cmbAdl.getSelectedItem().toString(), cmbRiwJatuh.getSelectedItem().toString(), nipBidan1, ya, pendengaran,
                        penglihatan, kognitif, fisik, budaya, emosi, bahasa, lainHambatan, TketLainHambatan.getText(), cmbDibutuhkan.getSelectedItem().toString(),
                        Tsebutkan.getText(), cmbBahasa.getSelectedItem().toString(), diagnosa, tindakanKeperawatan, TtindakanKep.getText(), lainKebutuhanEdukasi,
                        TlainKebutuhan.getText(), obatObatan, rehabilitasi, diet, manajemenNyeri, pasien, keluargaPasien, TnmKlgPasien.getText(), tidakDapat, TtidakDapat.getText(),
                        Valid.SetTgl(TtglEdukasi.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        nipDokter, identifikasi1, identifikasi2, identifikasi3, identifikasi4, identifikasi5, identifikasi6, identifikasi7, identifikasi8, identifikasi9,
                        identifikasi10, Tmemerlukan.getText(), mpp, dp, Valid.SetTgl(TtglDp.getSelectedItem() + ""), TnmKeluargaPasien.getText(), nipBidan2, stsrwt,
                        Sequel.cariIsi("select now()"), cmbResJatuh.getSelectedItem().toString(), cmbKondisi.getSelectedItem().toString(), cmbResAlatBantu.getSelectedItem().toString(),
                        cmbTerapiIV.getSelectedItem().toString(), cmbGaya.getSelectedItem().toString(), cmbSttsMental.getSelectedItem().toString(), TJmlSkor.getText(),
                        kesimpulanResJatuh.getText(), ""
                    }) == true) {

                TabRawat.setSelectedIndex(1);
                tampil(noRawat);
                emptTeks();
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
        tampil(noRawat);
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
//            if (akses.getadmin() == true) {
//                hapus();
//            } else {
//                if (Sequel.cariIsi("select nip_bidan_dp from asesmen_awal_kebidanan2 where no_rawat='" + TNoRw.getText() + "'").equals(akses.getkode())) {
//                    ganti();
//                } else {
//                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh " + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString() + ", karena beliau yang menyimpan datanya..!!");
//                }
//            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel..!!");
            TabRawat.setSelectedIndex(1);
            tampil(noRawat);
            emptTeks();
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
        if (noRawat.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel..!!");
        } else {
            if (tbAsesmen.getSelectedRow() > -1) {
                ganti();

//                if (akses.getadmin() == true) {
//                    ganti();
//                } else {
//                    if (Sequel.cariIsi("select nip_bidan_dp from asesmen_awal_kebidanan2 where no_rawat='" + TNoRw.getText() + "'").equals(akses.getkode())) {
//                        ganti();
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh " + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString() + ", karena beliau yang menyimpan datanya..!!");
//                    }
//                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel..!!");                
                TabRawat.setSelectedIndex(1);
                tampil(noRawat);
                emptTeks();
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

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa diprint...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0 || tbAsesmen.getSelectedRow() > -1
                || Sequel.cariInteger("select count(-1) from asesmen_awal_kebidanan1 where no_rawat='" + noRawat + "'") > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            try {
                psLaprm = koneksi.prepareStatement("SELECT ak1.*, ak2.*, ig.*, pg1.nama nmBidan, pg2.nama nmDokter, pg3.nama nmBidanDp, p.nm_pasien, "
                        + "p.no_rkm_medis, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', ',kb.nm_kab) almtPasien, "
                        + "concat(rp.umurdaftar,' ',rp.sttsumur) umurPasien, p.pekerjaan, p.agama, date_format(ak1.waktu_simpan,'%d/%m/%Y') tglak1, time(ak1.waktu_simpan) jamak1, "
                        + "if(ak2.no_rawat is not null,date_format(ak2.waktu_simpan,'%d/%m/%Y'),'-') tglak2, if(ak2.no_rawat is not null,time(ak2.waktu_simpan),'') jamak2 "
                        + "FROM asesmen_awal_kebidanan1 ak1 inner join reg_periksa rp on rp.no_rawat=ak1.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join kelurahan kl on kl.kd_kel=p.kd_kel inner join kecamatan kc on kc.kd_kec=p.kd_kec inner join kabupaten kb on kb.kd_kab=p.kd_kab "
                        + "left join asesmen_awal_kebidanan2 ak2 on ak1.no_rawat=ak2.no_rawat left join inspeksi_ginekologi_awal_kebidanan ig on ig.no_rawat=ak1.no_rawat "
                        + "left join pegawai pg1 on pg1.nik=ak2.nip_bidan left join pegawai pg2 on pg2.nik=ak2.nip_dokter "
                        + "left join pegawai pg3 on pg3.nik=ak2.nip_bidan_dp where ak1.no_rawat='" + noRawat + "'");
                try {
                    rsLaprm = psLaprm.executeQuery();
                    while (rsLaprm.next()) {
                        Map<String, Object> param = new HashMap<>();
                        param.put("namars", akses.getnamars());
                        param.put("logo", Sequel.cariGambar("select logo from setting"));
                        param.put("norm", rsLaprm.getString("no_rkm_medis"));
                        param.put("nmpasien", rsLaprm.getString("nm_pasien"));
                        param.put("tgllahir", rsLaprm.getString("tglLahir"));
                        param.put("ruangRwt", rsLaprm.getString("ruang_rawat"));
                        param.put("tglAses", Valid.SetTglINDONESIA(rsLaprm.getString("tgl_asesmen")));
                        param.put("jamAses", rsLaprm.getString("jam_asesmen").substring(0, 5) + " Wita");

                        param.put("nmPasien", rsLaprm.getString("nm_pasien"));
                        param.put("umurPasien", rsLaprm.getString("umurPasien"));
                        param.put("pekerPasien", rsLaprm.getString("pekerjaan"));
                        param.put("almtPasien", rsLaprm.getString("almtPasien"));
                        param.put("agmaPasien", rsLaprm.getString("agama"));

                        if (rsLaprm.getString("nm_suami").equals("")) {
                            param.put("nmSuami", "..............");
                        } else {
                            param.put("nmSuami", rsLaprm.getString("nm_suami"));
                        }

                        if (rsLaprm.getString("umur_suami").equals("")) {
                            param.put("umurSuami", "...... tahun");
                        } else {
                            param.put("umurSuami", rsLaprm.getString("umur_suami") + " tahun");
                        }

                        if (rsLaprm.getString("pekerjaan_suami").equals("")) {
                            param.put("pekerSuami", "..............");
                        } else {
                            param.put("pekerSuami", rsLaprm.getString("pekerjaan_suami"));
                        }

                        if (rsLaprm.getString("alamat_suami").equals("")) {
                            param.put("almtSuami", "..............");
                        } else {
                            param.put("almtSuami", rsLaprm.getString("alamat_suami"));
                        }

                        if (rsLaprm.getString("agama_suami").equals("-")) {
                            param.put("agmaSuami", "..............");
                        } else {
                            param.put("agmaSuami", rsLaprm.getString("agama_suami"));
                        }

                        if (rsLaprm.getString("alasan_masuk").equals("")) {
                            param.put("alasanMrs", "..............");
                        } else {
                            param.put("alasanMrs", rsLaprm.getString("alasan_masuk"));
                        }

                        if (rsLaprm.getString("td").equals("")) {
                            param.put("td", ".......... mmHg");
                        } else {
                            param.put("td", rsLaprm.getString("td") + " mmHg");
                        }

                        if (rsLaprm.getString("nadi").equals("")) {
                            param.put("nadi", ".......... x/menit");
                        } else {
                            param.put("nadi", rsLaprm.getString("nadi") + " x/menit");
                        }

                        if (rsLaprm.getString("respirasi").equals("")) {
                            param.put("respi", ".......... x/menit");
                        } else {
                            param.put("respi", rsLaprm.getString("respirasi") + " x/menit");
                        }

                        if (rsLaprm.getString("suhu").equals("")) {
                            param.put("suhu", "....... °C");
                        } else {
                            param.put("suhu", rsLaprm.getString("suhu") + " °C");
                        }

                        if (rsLaprm.getString("kesadaran").equals("")) {
                            param.put("kesadaran", "..............");
                        } else {
                            param.put("kesadaran", rsLaprm.getString("kesadaran"));
                        }

                        if (rsLaprm.getString("saturasi").equals("")) {
                            param.put("saturasi", "....... %");
                        } else {
                            param.put("saturasi", rsLaprm.getString("saturasi") + " %");
                        }

                        if (rsLaprm.getString("cek_sendiri").equals("ya")) {
                            param.put("caraSendiri", "V");
                        } else {
                            param.put("caraSendiri", "");
                        }

                        if (rsLaprm.getString("cek_rujukan").equals("ya")) {
                            param.put("caraRujukan", "V");
                            if (rsLaprm.getString("ket_jns_rujukan").equals("")) {
                                param.put("caraDataRujukan", "Rujukan : " + rsLaprm.getString("jns_rujukan"));
                            } else {
                                param.put("caraDataRujukan", "Rujukan : " + rsLaprm.getString("jns_rujukan") + " " + rsLaprm.getString("ket_jns_rujukan"));
                            }
                        } else {
                            param.put("caraRujukan", "");
                            param.put("caraDataRujukan", "Rujukan");
                        }

                        if (rsLaprm.getString("cek_pkm").equals("ya")) {
                            param.put("caraPkm", "V");
                            if (rsLaprm.getString("ket_pkm").equals("")) {
                                param.put("caraDataPkm", "PKM");
                            } else {
                                param.put("caraDataPkm", "PKM (" + rsLaprm.getString("ket_pkm") + ")");
                            }
                        } else {
                            param.put("caraPkm", "");
                            param.put("caraDataPkm", "PKM");
                        }

                        if (rsLaprm.getString("cek_spog").equals("ya")) {
                            param.put("caraSpog", "V");
                            if (rsLaprm.getString("ket_jns_rujukan").equals("")) {
                                param.put("caraDataSpog", "SPOG");
                            } else {
                                param.put("caraDataSpog", rsLaprm.getString("ket_jns_rujukan") + ", SPOG");
                            }                            
                        } else {
                            param.put("caraSpog", "");
                            param.put("caraDataSpog", "SPOG");
                        }

                        if (rsLaprm.getString("cek_rs_lain").equals("ya")) {
                            param.put("caraRsLain", "V");
                            if (rsLaprm.getString("ket_rs_lain").equals("")) {
                                param.put("caraDataRsLain", "RS Lain");
                            } else {
                                param.put("caraDataRsLain", "RS Lain (" + rsLaprm.getString("ket_rs_lain") + ")");
                            }
                        } else {
                            param.put("caraRsLain", "");
                            param.put("caraDataRsLain", "RS Lain");
                        }

                        String gr = "", pr = "", a = "", hamil = "";
                        if (rsLaprm.getString("gr").equals("")) {
                            gr = "Gr ......., ";
                        } else {
                            gr = "Gr " + rsLaprm.getString("gr") + ", ";
                        }

                        if (rsLaprm.getString("pr").equals("")) {
                            pr = "Pr ......., ";
                        } else {
                            pr = "Pr " + rsLaprm.getString("pr") + ", ";
                        }

                        if (rsLaprm.getString("a").equals("")) {
                            a = "A ......., ";
                        } else {
                            a = "A " + rsLaprm.getString("a") + ", ";
                        }

                        if (rsLaprm.getString("hamil").equals("")) {
                            hamil = "Hamil ....... minggu";
                        } else {
                            hamil = "Hamil " + rsLaprm.getString("hamil") + " minggu";
                        }

                        param.put("grpra", gr + pr + a + hamil);

                        if (rsLaprm.getString("gpapah").equals("")) {
                            param.put("gpapah", "..............");
                        } else {
                            param.put("gpapah", rsLaprm.getString("gpapah"));
                        }

                        if (rsLaprm.getString("dengan").equals("")) {
                            param.put("dengan", "..............");
                        } else {
                            param.put("dengan", rsLaprm.getString("dengan"));
                        }

                        if (rsLaprm.getString("perut").equals("Ya")) {
                            param.put("perut", rsLaprm.getString("perut") + " " + rsLaprm.getString("keluhan_perut") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_perut")) + ", jam " + rsLaprm.getString("jam_perut").substring(0, 5) + " Wita");
                        } else {
                            param.put("perut", rsLaprm.getString("perut"));
                        }

                        if (rsLaprm.getString("keluar").equals("Ya")) {
                            param.put("keluar", rsLaprm.getString("keluar") + " " + rsLaprm.getString("keluhan_keluar") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_keluar_lendir")) + ", jam " + rsLaprm.getString("jam_keluar_lendir").substring(0, 5) + " Wita");
                        } else {
                            param.put("keluar", rsLaprm.getString("keluar"));
                        }

                        if (rsLaprm.getString("darah").equals("Ya")) {
                            param.put("darah", rsLaprm.getString("darah") + " " + rsLaprm.getString("keluhan_darah") + " " + rsLaprm.getString("jns_darah") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_darah")) + ", jam " + rsLaprm.getString("jam_darah").substring(0, 5) + " Wita");
                        } else {
                            param.put("darah", rsLaprm.getString("darah"));
                        }

                        if (rsLaprm.getString("keluar_air").equals("Ya")) {
                            param.put("klrAir", rsLaprm.getString("keluar_air") + " " + rsLaprm.getString("keluhan_keluar_air") + " " + rsLaprm.getString("jns_keluar_air") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_keluar_air")) + ", jam " + rsLaprm.getString("jam_keluar_air").substring(0, 5) + " Wita");
                        } else {
                            param.put("klrAir", rsLaprm.getString("keluar_air"));
                        }

                        if (rsLaprm.getString("ket_pergerakan_janin_2jam_terakhir").equals("")) {
                            param.put("pergerakan", "-");
                        } else {
                            param.put("pergerakan", rsLaprm.getString("ket_pergerakan_janin_2jam_terakhir"));
                        }

                        if (rsLaprm.getString("pusing").equals("Ya")) {
                            param.put("pusing", rsLaprm.getString("pusing") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_pusing")) + ", jam " + rsLaprm.getString("jam_pusing").substring(0, 5) + " Wita");
                        } else {
                            param.put("pusing", rsLaprm.getString("pusing"));
                        }

                        if (rsLaprm.getString("nyeri_ulu_hati").equals("Ya")) {
                            param.put("nyeriUlu", rsLaprm.getString("nyeri_ulu_hati") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_nyeri_ulu_hati")) + ", jam " + rsLaprm.getString("jam_nyeri_ulu_hati").substring(0, 5) + " Wita");
                        } else {
                            param.put("nyeriUlu", rsLaprm.getString("nyeri_ulu_hati"));
                        }

                        if (rsLaprm.getString("pandangan_kabur").equals("Ya")) {
                            param.put("pandangan", rsLaprm.getString("pandangan_kabur") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_pandangan_kabur")) + ", jam " + rsLaprm.getString("jam_pandangan_kabur").substring(0, 5) + " Wita");
                        } else {
                            param.put("pandangan", rsLaprm.getString("pandangan_kabur"));
                        }

                        if (rsLaprm.getString("odema").equals("Ya")) {
                            param.put("odemma", rsLaprm.getString("odema") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_odema")) + ", di " + rsLaprm.getString("odema_di"));
                        } else {
                            param.put("odemma", rsLaprm.getString("odema"));
                        }

                        if (rsLaprm.getString("mual").equals("Ya")) {
                            param.put("mual", rsLaprm.getString("mual") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_mual")) + ", jam " + rsLaprm.getString("jam_mual").substring(0, 5) + " Wita");
                        } else {
                            param.put("mual", rsLaprm.getString("mual"));
                        }

                        if (rsLaprm.getString("muntah").equals("Ya")) {
                            param.put("muntah", rsLaprm.getString("muntah") + ", mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_muntah")) + ", jam " + rsLaprm.getString("jam_muntah").substring(0, 5) + " Wita");
                        } else {
                            param.put("muntah", rsLaprm.getString("muntah"));
                        }
                        
                        if (rsLaprm.getString("cek_batuk").equals("ya")) {
                            param.put("batuk", "Ya, mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_batuk")) + ", jam " + rsLaprm.getString("jam_batuk").substring(0, 5) + " Wita");
                        } else {
                            param.put("batuk", "Tidak");
                        }
                        
                        if (rsLaprm.getString("cek_pilek").equals("ya")) {
                            param.put("pilek", "Ya, mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_pilek")) + ", jam " + rsLaprm.getString("jam_pilek").substring(0, 5) + " Wita");
                        } else {
                            param.put("pilek", "Tidak");
                        }
                        
                        if (rsLaprm.getString("cek_demam").equals("ya")) {
                            param.put("demam", "Ya, mulai tgl. " + Valid.SetTgl3(rsLaprm.getString("tgl_demam")) + ", jam " + rsLaprm.getString("jam_demam").substring(0, 5) + " Wita");
                        } else {
                            param.put("demam", "Tidak");
                        }

                        param.put("keluhanLainKLH", rsLaprm.getString("keluhan_lainya"));

                        if (rsLaprm.getString("periksa_ketempat_bidan").equals("Ya")) {
                            if (rsLaprm.getString("hasil_pemeriksaan_bidan").equals("")) {
                                param.put("periksa", rsLaprm.getString("periksa_ketempat_bidan") + ", Hasil / Riwayat Pemeriksaan Bidan : -");
                            } else {
                                param.put("periksa", rsLaprm.getString("periksa_ketempat_bidan") + ", Hasil / Riwayat Pemeriksaan Bidan : " + rsLaprm.getString("hasil_pemeriksaan_bidan"));
                            }
                        } else {
                            param.put("periksa", rsLaprm.getString("periksa_ketempat_bidan"));
                        }

                        if (rsLaprm.getString("ibu_anc").equals("Ya")) {
                            if (rsLaprm.getString("jlh_anc").equals("")) {
                                param.put("ibuAnc", rsLaprm.getString("ibu_anc") + ", di " + rsLaprm.getString("jns_anc"));
                            } else {
                                param.put("ibuAnc", rsLaprm.getString("ibu_anc") + ", di " + rsLaprm.getString("jns_anc") + " : " + rsLaprm.getString("jlh_anc") + " X");
                            }
                        } else {
                            param.put("ibuAnc", rsLaprm.getString("ibu_anc"));
                        }

                        if (!rsLaprm.getString("dengan_dokter1").equals("")) {
                            if (rsLaprm.getString("jlh_dengan_dokter1").equals("")) {
                                param.put("dokter1", rsLaprm.getString("dengan_dokter1"));
                            } else {
                                param.put("dokter1", rsLaprm.getString("dengan_dokter1") + " (" + rsLaprm.getString("jlh_dengan_dokter1") + " X)");
                            }
                        } else {
                            param.put("dokter1", "..............");
                        }

                        if (!rsLaprm.getString("dengan_dokter2").equals("")) {
                            if (rsLaprm.getString("jlh_dengan_dokter2").equals("")) {
                                param.put("dokter2", rsLaprm.getString("dengan_dokter2"));
                            } else {
                                param.put("dokter2", rsLaprm.getString("dengan_dokter2") + " (" + rsLaprm.getString("jlh_dengan_dokter2") + " X)");
                            }
                        } else {
                            param.put("dokter2", "..............");
                        }

                        if (!rsLaprm.getString("dengan_dokter3").equals("")) {
                            if (rsLaprm.getString("jlh_dengan_dokter3").equals("")) {
                                param.put("dokter3", rsLaprm.getString("dengan_dokter3"));
                            } else {
                                param.put("dokter3", rsLaprm.getString("dengan_dokter3") + " (" + rsLaprm.getString("jlh_dengan_dokter3") + " X)");
                            }
                        } else {
                            param.put("dokter3", "..............");
                        }

                        if (rsLaprm.getString("hpht").equals("")) {
                            param.put("hpht", "..............");
                        } else {
                            param.put("hpht", rsLaprm.getString("hpht"));
                        }

                        if (rsLaprm.getString("hpl").equals("")) {
                            param.put("hpl", "..............");
                        } else {
                            param.put("hpl", rsLaprm.getString("hpl"));
                        }

                        if (rsLaprm.getString("uk").equals("")) {
                            param.put("uk", "........ mg");
                        } else {
                            param.put("uk", rsLaprm.getString("uk") + " mg");
                        }

                        if (rsLaprm.getString("bb_sebelum_hamil").equals("")) {
                            param.put("bbSebelum", "........ Kg");
                        } else {
                            param.put("bbSebelum", rsLaprm.getString("bb_sebelum_hamil") + " Kg");
                        }

                        if (rsLaprm.getString("bb_terakhir").equals("")) {
                            param.put("bbTerakhir", "........ Kg");
                        } else {
                            param.put("bbTerakhir", rsLaprm.getString("bb_terakhir") + " Kg");
                        }

                        if (rsLaprm.getString("tbi").equals("")) {
                            param.put("tbi", "........ Cm");
                        } else {
                            param.put("tbi", rsLaprm.getString("tbi") + " Cm");
                        }
                        
                        //hitung nilai BMI
                        Valid.hitungBMIbbTerakhir(rsLaprm.getString("bb_terakhir"), rsLaprm.getString("tbi"));
                        param.put("bmiHamil", akses.getPasteData() + " kg/m².      Grade : " + akses.getPasteData1());

                        if (rsLaprm.getString("umur_pertama_haid").equals("")) {
                            param.put("umurPertama", "........ tahun");
                        } else {
                            param.put("umurPertama", rsLaprm.getString("umur_pertama_haid") + " tahun");
                        }

                        if (rsLaprm.getString("lama_haid").equals("")) {
                            param.put("lamaHaid", "........ hari");
                        } else {
                            param.put("lamaHaid", rsLaprm.getString("lama_haid") + " hari");
                        }

                        if (rsLaprm.getString("berapa_kali_ganti_pembalut").equals("")) {
                            param.put("berapa", "........ x / hari");
                        } else {
                            param.put("berapa", rsLaprm.getString("berapa_kali_ganti_pembalut") + " x / hari");
                        }

                        param.put("keluhanWaktu", rsLaprm.getString("keluhan_waktu_haid"));

                        if (rsLaprm.getString("cek_dismen").equals("ya")) {
                            param.put("dismen", "V");
                        } else {
                            param.put("dismen", "");
                        }

                        if (rsLaprm.getString("cek_spoting").equals("ya")) {
                            param.put("spot", "V");
                        } else {
                            param.put("spot", "");
                        }

                        if (rsLaprm.getString("cek_menor").equals("ya")) {
                            param.put("menor", "V");
                        } else {
                            param.put("menor", "");
                        }

                        if (rsLaprm.getString("cek_metro").equals("ya")) {
                            param.put("metro", "V");
                        } else {
                            param.put("metro", "");
                        }

                        if (rsLaprm.getString("cek_lain_keluhan_haid").equals("ya")) {
                            param.put("cekKelLain", "V");
                            if (rsLaprm.getString("ket_lain_keluhan_haid").equals("")) {
                                param.put("dataKelLain", "Lainnya");
                            } else {
                                param.put("dataKelLain", "Lainnya (" + rsLaprm.getString("ket_lain_keluhan_haid") + ")");
                            }
                        } else {
                            param.put("cekKelLain", "");
                            param.put("dataKelLain", "Lainnya");
                        }

                        param.put("riwPenyDahulu", rsLaprm.getString("riw_penyakit_dahulu"));

                        if (rsLaprm.getString("cek_hipertensi_dahulu").equals("ya")) {
                            param.put("cekHiper1", "V");
                        } else {
                            param.put("cekHiper1", "");
                        }

                        if (rsLaprm.getString("cek_dm_dahulu").equals("ya")) {
                            param.put("cekDm1", "V");
                        } else {
                            param.put("cekDm1", "");
                        }

                        if (rsLaprm.getString("cek_jantung_dahulu").equals("ya")) {
                            param.put("cekJantung1", "V");
                        } else {
                            param.put("cekJantung1", "");
                        }

                        if (rsLaprm.getString("cek_asma_dahulu").equals("ya")) {
                            param.put("cekAsma1", "V");
                        } else {
                            param.put("cekAsma1", "");
                        }

                        if (rsLaprm.getString("cek_lainya_dahulu").equals("ya")) {
                            param.put("cekLainDahulu1", "V");
                            if (rsLaprm.getString("ket_lain_penyakit_dahulu").equals("")) {
                                param.put("dataLainDahulu1", "Lainnya");
                            } else {
                                param.put("dataLainDahulu1", "Lainnya (" + rsLaprm.getString("ket_lain_penyakit_dahulu") + ")");
                            }
                        } else {
                            param.put("cekLainDahulu1", "");
                            param.put("dataLainDahulu1", "Lainnya");
                        }

                        param.put("riwPenyKlg", rsLaprm.getString("riw_penyakit_keluarga"));

                        if (rsLaprm.getString("cek_hipertensi_keluarga").equals("ya")) {
                            param.put("cekHiper2", "V");
                        } else {
                            param.put("cekHiper2", "");
                        }

                        if (rsLaprm.getString("cek_dm_keluarga").equals("ya")) {
                            param.put("cekDm2", "V");
                        } else {
                            param.put("cekDm2", "");
                        }

                        if (rsLaprm.getString("cek_jantung_keluarga").equals("ya")) {
                            param.put("cekJantung2", "V");
                        } else {
                            param.put("cekJantung2", "");
                        }

                        if (rsLaprm.getString("cek_asma_keluarga").equals("ya")) {
                            param.put("cekAsma2", "V");
                        } else {
                            param.put("cekAsma2", "");
                        }

                        if (rsLaprm.getString("cek_lainya_keluarga").equals("ya")) {
                            param.put("cekLainKlg2", "V");
                            if (rsLaprm.getString("ket_lain_penyakit_keluarga").equals("")) {
                                param.put("dataLainKlg2", "Lainnya");
                            } else {
                                param.put("dataLainKlg2", "Lainnya (" + rsLaprm.getString("ket_lain_penyakit_keluarga") + ")");
                            }
                        } else {
                            param.put("cekLainKlg2", "");
                            param.put("dataLainKlg2", "Lainnya");
                        }

                        if (rsLaprm.getString("riw_ginekologi").equals("Ada")) {
                            if (rsLaprm.getString("ket_ginekologi").equals("")) {
                                param.put("riwGine", rsLaprm.getString("riw_ginekologi"));
                            } else {
                                param.put("riwGine", rsLaprm.getString("riw_ginekologi") + ", " + rsLaprm.getString("ket_ginekologi"));
                            }
                        } else {
                            param.put("riwGine", rsLaprm.getString("riw_ginekologi"));
                        }

                        if (rsLaprm.getString("cek_pil").equals("ya")) {
                            param.put("cekPil", "V");
                            if (rsLaprm.getString("lama_pil").equals("")) {
                                param.put("dataPil", "Pil, Lama : ...... " + rsLaprm.getString("satuan_lama_pil"));
                            } else {
                                param.put("dataPil", "Pil, Lama : " + rsLaprm.getString("lama_pil") + " " + rsLaprm.getString("satuan_lama_pil"));
                            }
                        } else {
                            param.put("cekPil", "");
                            param.put("dataPil", "Pil");
                        }

                        if (rsLaprm.getString("cek_suntik1").equals("ya")) {
                            param.put("cekSuntik1", "V");
                            if (rsLaprm.getString("lama_suntik1").equals("")) {
                                param.put("dataSuntik1", "Suntik 1 Bulan, Lama : ...... " + rsLaprm.getString("satuan_lama_suntik1"));
                            } else {
                                param.put("dataSuntik1", "Suntik 1 Bulan, Lama : " + rsLaprm.getString("lama_suntik1") + " " + rsLaprm.getString("satuan_lama_suntik1"));
                            }
                        } else {
                            param.put("cekSuntik1", "");
                            param.put("dataSuntik1", "Suntik 1 Bulan");
                        }

                        if (rsLaprm.getString("cek_suntik3").equals("ya")) {
                            param.put("cekSuntik3", "V");
                            if (rsLaprm.getString("lama_suntik3").equals("")) {
                                param.put("dataSuntik3", "Suntik 3 Bulan, Lama : ...... " + rsLaprm.getString("satuan_lama_suntik3"));
                            } else {
                                param.put("dataSuntik3", "Suntik 3 Bulan, Lama : " + rsLaprm.getString("lama_suntik3") + " " + rsLaprm.getString("satuan_lama_suntik3"));
                            }
                        } else {
                            param.put("cekSuntik3", "");
                            param.put("dataSuntik3", "Suntik 3 Bulan");
                        }

                        if (rsLaprm.getString("cek_implan").equals("ya")) {
                            param.put("cekImplan", "V");
                            if (rsLaprm.getString("lama_implan").equals("")) {
                                param.put("dataImplan", "Implan, Lama : ...... " + rsLaprm.getString("satuan_lama_implan"));
                            } else {
                                param.put("dataImplan", "Implan, Lama : " + rsLaprm.getString("lama_implan") + " " + rsLaprm.getString("satuan_lama_implan"));
                            }
                        } else {
                            param.put("cekImplan", "");
                            param.put("dataImplan", "Implan");
                        }

                        if (rsLaprm.getString("cek_iud").equals("ya")) {
                            param.put("cekIud", "V");
                            if (rsLaprm.getString("lama_iud").equals("")) {
                                param.put("dataIud", "IUD, Lama : ...... " + rsLaprm.getString("satuan_lama_iud"));
                            } else {
                                param.put("dataIud", "IUD, Lama : " + rsLaprm.getString("lama_iud") + " " + rsLaprm.getString("satuan_lama_iud"));
                            }
                        } else {
                            param.put("cekIud", "");
                            param.put("dataIud", "IUD");
                        }

                        if (rsLaprm.getString("cek_tidak_kb").equals("ya")) {
                            param.put("cektdkPernahKb", "V");
                        } else {
                            param.put("cektdkPernahKb", "");
                        }

                        param.put("sttsPerkawinan", rsLaprm.getString("status_perkawinan"));

                        if (rsLaprm.getString("cek_istri_kawin").equals("ya")) {
                            param.put("cekIstri", "V");
                            if (!rsLaprm.getString("jlh_perkawinan_istri").equals("-")) {
                                param.put("dataIstri", "Istri (" + rsLaprm.getString("jlh_perkawinan_istri") + ")");
                            } else {
                                param.put("dataIstri", "Istri");
                            }
                        } else {
                            param.put("cekIstri", "");
                            param.put("dataIstri", "Istri");
                        }

                        if (rsLaprm.getString("cek_suami_kawin").equals("ya")) {
                            param.put("cekSuami", "V");
                            if (!rsLaprm.getString("jlh_perkawinan_suami").equals("-")) {
                                param.put("dataSuami", " Suami (" + rsLaprm.getString("jlh_perkawinan_suami") + ")");
                            } else {
                                param.put("dataSuami", " Suami");
                            }
                        } else {
                            param.put("cekSuami", "");
                            param.put("dataSuami", " Suami");
                        }

                        if (rsLaprm.getString("usia_pertama_nikah").equals("")) {
                            param.put("usiaPertama", "....... tahun");
                        } else {
                            param.put("usiaPertama", rsLaprm.getString("usia_pertama_nikah") + " tahun");
                        }
                        
                        if (rsLaprm.getString("usia_pertama_nikah_bln").equals("")) {
                            param.put("usiaPertamaBln", "....... bulan");
                        } else {
                            param.put("usiaPertamaBln", rsLaprm.getString("usia_pertama_nikah_bln") + " bulan");
                        }

                        if (rsLaprm.getString("usia_perkawinan").equals("")) {
                            param.put("usiaPerkawinan", "....... tahun");
                        } else {
                            param.put("usiaPerkawinan", rsLaprm.getString("usia_perkawinan") + " tahun");
                        }
                        
                        if (rsLaprm.getString("usia_perkawinan_bln").equals("")) {
                            param.put("usiaPerkawinanBln", "....... bulan");
                        } else {
                            param.put("usiaPerkawinanBln", rsLaprm.getString("usia_perkawinan_bln") + " bulan");
                        }

                        if (rsLaprm.getString("keluarga_terdekat").equals("")) {
                            param.put("klgTerdekat", ".............");
                        } else {
                            param.put("klgTerdekat", rsLaprm.getString("keluarga_terdekat"));
                        }

                        if (rsLaprm.getString("hubungan").equals("")) {
                            param.put("hubKlg", ".............");
                        } else {
                            param.put("hubKlg", rsLaprm.getString("hubungan"));
                        }

                        if (rsLaprm.getString("cek_orang_tua").equals("ya")) {
                            param.put("cekOrtu", "V");
                        } else {
                            param.put("cekOrtu", "");
                        }

                        if (rsLaprm.getString("cek_suami").equals("ya")) {
                            param.put("cekTglSuami", "V");
                        } else {
                            param.put("cekTglSuami", "");
                        }

                        if (rsLaprm.getString("cek_anak").equals("ya")) {
                            param.put("cekAnak", "V");
                        } else {
                            param.put("cekAnak", "");
                        }

                        if (rsLaprm.getString("cek_tinggal_sendiri").equals("ya")) {
                            param.put("cekTglSendiri", "V");
                        } else {
                            param.put("cekTglSendiri", "");
                        }

                        param.put("curiga", rsLaprm.getString("curiga_penganiayaan"));
                        param.put("kegIbadah", rsLaprm.getString("kegiatan_ibadah"));
                        param.put("sttsEmosional", rsLaprm.getString("status_emosional"));

                        if (rsLaprm.getString("cek_asuransi").equals("ya")) {
                            param.put("cekAsuransi", "V");
                        } else {
                            param.put("cekAsuransi", "");
                        }

                        if (rsLaprm.getString("cek_jaminan").equals("ya")) {
                            param.put("cekJaminan", "V");
                        } else {
                            param.put("cekJaminan", "");
                        }

                        if (rsLaprm.getString("cek_biaya_sendiri").equals("ya")) {
                            param.put("cekBySendiri", "V");
                        } else {
                            param.put("cekBySendiri", "");
                        }

                        if (rsLaprm.getString("cek_lain_status_ekonomi").equals("ya")) {
                            param.put("cekSttsKLain", "V");
                            if (rsLaprm.getString("ket_lain_status_ekonomi").equals("")) {
                                param.put("dataSttsKLain", "Lainnya");
                            } else {
                                param.put("dataSttsKLain", "Lainnya (" + rsLaprm.getString("ket_lain_status_ekonomi") + ")");
                            }
                        } else {
                            param.put("cekSttsKLain", "");
                            param.put("dataSttsKLain", "Lainnya");
                        }

                        if (rsLaprm.getString("leopold1").equals("")) {
                            param.put("leo1", "-");
                        } else {
                            param.put("leo1", rsLaprm.getString("leopold1"));
                        }

                        if (rsLaprm.getString("leopold2").equals("")) {
                            param.put("leo2", "-");
                        } else {
                            param.put("leo2", rsLaprm.getString("leopold2"));
                        }

                        if (rsLaprm.getString("leopold3").equals("")) {
                            param.put("leo3", "-");
                        } else {
                            param.put("leo3", rsLaprm.getString("leopold3"));
                        }

                        if (rsLaprm.getString("leopold4").equals("")) {
                            param.put("leo4", "-");
                        } else {
                            param.put("leo4", rsLaprm.getString("leopold4"));
                        }

                        param.put("bandle", rsLaprm.getString("bandle_ring"));
                        param.put("perutTegang", rsLaprm.getString("perut_tegang"));

                        if (rsLaprm.getString("palpasi").equals("")) {
                            param.put("palpasi", "-");
                        } else {
                            param.put("palpasi", rsLaprm.getString("palpasi"));
                        }

                        param.put("goyang", rsLaprm.getString("goyang"));

                        if (rsLaprm.getString("vt_pembukaan").equals("")) {
                            param.put("vtPembukaan", "-");
                        } else {
                            param.put("vtPembukaan", rsLaprm.getString("vt_pembukaan"));
                        }

                        if (rsLaprm.getString("teraba_massa").equals("Ya")) {
                            if (rsLaprm.getString("sebesar").equals("")) {
                                param.put("teraba", rsLaprm.getString("teraba_massa"));
                            } else {
                                param.put("teraba", rsLaprm.getString("teraba_massa") + ", Sebesar : " + rsLaprm.getString("sebesar"));
                            }
                        } else {
                            param.put("teraba", rsLaprm.getString("teraba_massa"));
                        }

                        param.put("nyeriTekan", rsLaprm.getString("nyeri_tekan"));
                        param.put("vtNyeri", rsLaprm.getString("vt_nyeri_goyang"));
                        
                        //inspeksi ginekologi
                        if (Sequel.cariInteger("select count(-1) from inspeksi_ginekologi_awal_kebidanan where no_rawat='" + noRawat + "'") > 0) {
                            if (rsLaprm.getString("vulva_normal").equals("ya")) {
                                param.put("vulNormal", "Normal, ");
                            } else {
                                param.put("vulNormal", "");
                            }

                            if (rsLaprm.getString("vulva_hiperemis").equals("ya")) {
                                param.put("vulHiper", "Hiperemis, ");
                            } else {
                                param.put("vulHiper", "");
                            }

                            if (rsLaprm.getString("vulva_edema").equals("ya")) {
                                param.put("vulEdema", "Edema, ");
                            } else {
                                param.put("vulEdema", "");
                            }

                            if (rsLaprm.getString("vulva_ada_lesi").equals("ya")) {
                                param.put("vulAda", "Ada lesi/ulkus, ");
                            } else {
                                param.put("vulAda", "");
                            }

                            if (rsLaprm.getString("vulva_masa").equals("ya")) {
                                param.put("vulMasa", "Massa");
                            } else {
                                param.put("vulMasa", "");
                            }

                            if (rsLaprm.getString("labia_simetris").equals("ya")) {
                                param.put("labSimetris", "Simetris, ");
                            } else {
                                param.put("labSimetris", "");
                            }

                            if (rsLaprm.getString("labia_tdk_simetris").equals("ya")) {
                                param.put("labTdkSimetris", "Tidak Simetris, ");
                            } else {
                                param.put("labTdkSimetris", "");
                            }

                            if (rsLaprm.getString("labia_ada_luka").equals("ya")) {
                                param.put("labAdaLuka", "Ada Luka, ");
                            } else {
                                param.put("labAdaLuka", "");
                            }

                            if (rsLaprm.getString("labia_ada_benjolan").equals("ya")) {
                                param.put("labAdaBenjol", "Ada Benjolan");
                            } else {
                                param.put("labAdaBenjol", "");
                            }

                            if (rsLaprm.getString("klitoris_normal").equals("ya")) {
                                param.put("kliNormal", "Normal, ");
                            } else {
                                param.put("kliNormal", "");
                            }

                            if (rsLaprm.getString("klitoris_hipertrofi").equals("ya")) {
                                param.put("kliHiper", "Hipertrofi, ");
                            } else {
                                param.put("kliHiper", "");
                            }

                            if (rsLaprm.getString("klitoris_lainya").equals("ya")) {
                                if (!rsLaprm.getString("ket_klitoris_lain").equals("")) {
                                    param.put("kliLain", "Lainnya : " + rsLaprm.getString("ket_klitoris_lain"));
                                } else {
                                    param.put("kliLain", "Lainnya : ...........");
                                }
                            } else {
                                param.put("kliLain", "");
                            }

                            if (rsLaprm.getString("perineum_utuh").equals("ya")) {
                                param.put("perUtuh", "Utuh, ");
                            } else {
                                param.put("perUtuh", "");
                            }

                            if (rsLaprm.getString("perineum_ada_luka").equals("ya")) {
                                param.put("perAda", "Ada Luka, ");
                            } else {
                                param.put("perAda", "");
                            }

                            if (rsLaprm.getString("perineum_bekas").equals("ya")) {
                                param.put("perBekas", "Bekas Episiotomi, ");
                            } else {
                                param.put("perBekas", "");
                            }

                            if (rsLaprm.getString("perineum_fistula").equals("ya")) {
                                param.put("perFis", "Fistula");
                            } else {
                                param.put("perFis", "");
                            }

                            if (rsLaprm.getString("introitus_normal").equals("ya")) {
                                param.put("intNormal", "Normal, ");
                            } else {
                                param.put("intNormal", "");
                            }

                            if (rsLaprm.getString("introitus_ada_sekret").equals("ya")) {
                                param.put("intAdaS", "Ada Sekret, ");
                            } else {
                                param.put("intAdaS", "");
                            }

                            if (rsLaprm.getString("introitus_ada_perdarahan").equals("ya")) {
                                param.put("intAdaP", "Ada Perdarahan, ");
                            } else {
                                param.put("intAdaP", "");
                            }

                            if (rsLaprm.getString("introitus_ada_robekan").equals("ya")) {
                                param.put("intAdaR", "Ada Robekan");
                            } else {
                                param.put("intAdaR", "");
                            }

                            if (rsLaprm.getString("sekret_tdk_ada").equals("ya")) {
                                param.put("sekTdkAda", "Tidak Ada, ");
                            } else {
                                param.put("sekTdkAda", "");
                            }

                            if (rsLaprm.getString("sekret_jernih").equals("ya")) {
                                param.put("sekJernih", "Jernih, ");
                            } else {
                                param.put("sekJernih", "");
                            }

                            if (rsLaprm.getString("sekret_putih").equals("ya")) {
                                param.put("sekPutih", "Putih Kental, ");
                            } else {
                                param.put("sekPutih", "");
                            }

                            if (rsLaprm.getString("sekret_kuning").equals("ya")) {
                                param.put("sekKuning", "Kuning Kehijauan, ");
                            } else {
                                param.put("sekKuning", "");
                            }

                            if (rsLaprm.getString("sekret_berbau").equals("ya")) {
                                param.put("sekBerbau", "Berbau, ");
                            } else {
                                param.put("sekBerbau", "");
                            }

                            param.put("jmlSekret", "Jumlah : " + rsLaprm.getString("jumlah_sekret"));

                            if (rsLaprm.getString("dinding_normal").equals("ya")) {
                                param.put("dinNormal", "Normal, ");
                            } else {
                                param.put("dinNormal", "");
                            }

                            if (rsLaprm.getString("dinding_hiperemis").equals("ya")) {
                                param.put("dinHiper", "Hiperemis, ");
                            } else {
                                param.put("dinHiper", "");
                            }

                            if (rsLaprm.getString("dinding_atrofi").equals("ya")) {
                                param.put("dinAtro", "Atrofi, ");
                            } else {
                                param.put("dinAtro", "");
                            }

                            if (rsLaprm.getString("dinding_ada_masa").equals("ya")) {
                                param.put("dinAdaM", "Ada Massa, ");
                            } else {
                                param.put("dinAdaM", "");
                            }

                            if (rsLaprm.getString("dinding_ada_sekret").equals("ya")) {
                                param.put("dinAdaS", "Ada Sekret");
                            } else {
                                param.put("dinAdaS", "");
                            }

                            if (rsLaprm.getString("serviks_bentuk_normal").equals("ya")) {
                                param.put("serbenNormal", "Normal, ");
                            } else {
                                param.put("serbenNormal", "");
                            }

                            if (rsLaprm.getString("serviks_bentuk_erosi").equals("ya")) {
                                param.put("serbenErosi", "Erosi, ");
                            } else {
                                param.put("serbenErosi", "");
                            }

                            if (rsLaprm.getString("serviks_bentuk_polip").equals("ya")) {
                                param.put("serbenPolip", "Polip, ");
                            } else {
                                param.put("serbenPolip", "");
                            }

                            if (rsLaprm.getString("serviks_bentuk_ektropion").equals("ya")) {
                                param.put("serbenEktro", "Ektropion");
                            } else {
                                param.put("serbenEktro", "");
                            }

                            if (rsLaprm.getString("serviks_warna_normal").equals("ya")) {
                                param.put("serwarNormal", "Normal, ");
                            } else {
                                param.put("serwarNormal", "");
                            }

                            if (rsLaprm.getString("serviks_warna_hiperemis").equals("ya")) {
                                param.put("serwarHiper", "Hiperemis, ");
                            } else {
                                param.put("serwarHiper", "");
                            }

                            if (rsLaprm.getString("serviks_warna_pucat").equals("ya")) {
                                param.put("serwarPucat", "Pucat, ");
                            } else {
                                param.put("serwarPucat", "");
                            }

                            if (rsLaprm.getString("serviks_permu_halus").equals("ya")) {
                                param.put("serperHalus", "Halus, ");
                            } else {
                                param.put("serperHalus", "");
                            }

                            if (rsLaprm.getString("serviks_permu_granulasi").equals("ya")) {
                                param.put("serperGran", "Granulasi, ");
                            } else {
                                param.put("serperGran", "");
                            }

                            if (rsLaprm.getString("serviks_permu_ulserasi").equals("ya")) {
                                param.put("serperUl", "Ulserasi");
                            } else {
                                param.put("serperUl", "");
                            }

                            param.put("sekServik", rsLaprm.getString("sekret_serviks"));
                            param.put("sekServikAda", rsLaprm.getString("sekret_serviks_ada"));
                            param.put("temuan", rsLaprm.getString("temuan_tambahan"));
                        } else {
                            param.put("vulNormal", "");
                            param.put("vulHiper", "");
                            param.put("vulEdema", "");
                            param.put("vulAda", "");
                            param.put("vulMasa", "");
                            param.put("labSimetris", "");
                            param.put("labTdkSimetris", "");
                            param.put("labAdaLuka", "");
                            param.put("labAdaBenjol", "");
                            param.put("kliNormal", "");
                            param.put("kliHiper", "");
                            param.put("kliLain", "");
                            param.put("perUtuh", "");
                            param.put("perAda", "");
                            param.put("perBekas", "");
                            param.put("perFis", "");
                            param.put("intNormal", "");
                            param.put("intAdaS", "");
                            param.put("intAdaP", "");
                            param.put("intAdaR", "");
                            param.put("sekTdkAda", "");
                            param.put("sekJernih", "");
                            param.put("sekPutih", "");
                            param.put("sekKuning", "");
                            param.put("sekBerbau", "");
                            param.put("dinNormal", "");
                            param.put("dinHiper", "");
                            param.put("dinAtro", "");
                            param.put("dinAdaM", "");
                            param.put("dinAdaS", "");
                            param.put("serbenNormal", "");
                            param.put("serbenErosi", "");
                            param.put("serbenPolip", "");
                            param.put("serbenEktro", "");
                            param.put("serwarNormal", "");
                            param.put("serwarHiper", "");
                            param.put("serwarPucat", "");
                            param.put("serperHalus", "");
                            param.put("serperGran", "");
                            param.put("serperUl", "");
                            param.put("kliLain", "");
                            param.put("jmlSekret", "");
                            param.put("sekServik", "");
                            param.put("sekServikAda", "");
                            param.put("temuan", "");
                        }
                        //-----------

                        if (rsLaprm.getString("tfu").equals("")) {
                            param.put("tfu", "...... Cm");
                        } else {
                            param.put("tfu", rsLaprm.getString("tfu") + " Cm");
                        }

                        if (rsLaprm.getString("his_kontraksi").equals("")) {
                            param.put("his", "...... x / 10 menit");
                        } else {
                            param.put("his", rsLaprm.getString("his_kontraksi") + " x / 10 menit");
                        }

                        param.put("ketHis", rsLaprm.getString("jns_his_kontraksi"));

                        if (rsLaprm.getString("taksiran_berat_janin").equals("")) {
                            param.put("taksiran", "...... gram");
                        } else {
                            param.put("taksiran", rsLaprm.getString("taksiran_berat_janin") + " gram");
                        }

                        if (rsLaprm.getString("durasi").equals("")) {
                            param.put("durasi", "...... detik");
                        } else {
                            param.put("durasi", rsLaprm.getString("durasi") + " detik");
                        }

                        param.put("ketDurasi", rsLaprm.getString("jns_durasi"));

                        if (rsLaprm.getString("auskultasi").equals("")) {
                            param.put("auskultasi", "...... x / menit");
                        } else {
                            param.put("auskultasi", rsLaprm.getString("auskultasi") + " x / menit");
                        }

                        if (rsLaprm.getString("cek_bersih").equals("ya")) {
                            param.put("bersih", "V");
                        } else {
                            param.put("bersih", "");
                        }

                        if (rsLaprm.getString("cek_oedema").equals("ya")) {
                            param.put("oedema", "V");
                        } else {
                            param.put("oedema", "");
                        }

                        if (rsLaprm.getString("cek_ruftur").equals("ya")) {
                            param.put("ruftur", "V");
                        } else {
                            param.put("ruftur", "");
                        }

                        if (rsLaprm.getString("cek_candiloma").equals("ya")) {
                            param.put("candi", "V");
                        } else {
                            param.put("candi", "");
                        }

                        if (rsLaprm.getString("cek_lain_pemeriksaan_geni").equals("ya")) {
                            param.put("lainPemeriksaan", "V");
                            if (rsLaprm.getString("ket_lain_pemeriksaan_geni").equals("")) {
                                param.put("dataLainPemeriksaan", "Lainnya");
                            } else {
                                param.put("dataLainPemeriksaan", "Lainnya (" + rsLaprm.getString("ket_lain_pemeriksaan_geni") + ")");
                            }
                        } else {
                            param.put("lainPemeriksaan", "");
                            param.put("dataLainPemeriksaan", "Lainnya");
                        }

                        if (rsLaprm.getString("periksa_dalam_obstetri").equals("ya")) {
                            param.put("periksaDlm", "-");
                        } else {
                            param.put("periksaDlm", rsLaprm.getString("periksa_dalam_obstetri"));
                        }

                        if (rsLaprm.getString("inspekulo").equals("Ya")) {
                            if (rsLaprm.getString("hasil_inspekulo").equals("")) {
                                param.put("inspekulo", rsLaprm.getString("inspekulo"));
                            } else {
                                param.put("inspekulo", rsLaprm.getString("inspekulo") + ", Hasil : " + rsLaprm.getString("hasil_inspekulo"));
                            }
                        } else {
                            param.put("inspekulo", rsLaprm.getString("inspekulo"));
                        }

                        if (rsLaprm.getString("diagnosis_sementara").equals("")) {
                            param.put("diagnosis", "-");
                        } else {
                            param.put("diagnosis", rsLaprm.getString("diagnosis_sementara"));
                        }

                        if (rsLaprm.getString("icd_10").equals("")) {
                            param.put("icd", "-");
                        } else {
                            param.put("icd", rsLaprm.getString("icd_10"));
                        }

                        if (rsLaprm.getString("planing").equals("")) {
                            param.put("planing", "-");
                        } else {
                            param.put("planing", rsLaprm.getString("planing"));
                        }

                        if ((TabRawat.getSelectedIndex() == 0 && cmbPilihCetak.getSelectedIndex() == 0)
                                || TabRawat.getSelectedIndex() == 1 && cmbPilihCetak1.getSelectedIndex() == 0) {
                            if (Sequel.cariInteger("select count(-1) from asesmen_awal_kebidanan2 where no_rawat='" + noRawat + "'") > 0
                                    && (rsLaprm.getString("nip_bidan_dp").equals("") || rsLaprm.getString("nip_bidan_dp").equals("-") || rsLaprm.getString("nip_bidan_dp").equals("--"))) {
                                Valid.MyReport("rptAsesmenAwalKebidanan1.jasper", "report", "::[ Asesmen Awal Kebidanan hal. 1 ]::",
                                        "select date(now()) tgl", param);

                                Valid.MyReport("rptAsesmenAwalKebidanan2.jasper", "report", "::[ Asesmen Awal Kebidanan hal. 2 ]::",
                                        "SELECT COALESCE(no_rawat, '-') AS no_rawat, "
                                        + "COALESCE(tahun_partus, '-') AS tahun_partus, "
                                        + "COALESCE(tempat_partus, '-') AS tempat_partus, "
                                        + "COALESCE(umur_hamil, '-') AS umur_hamil, "
                                        + "COALESCE(jns_persalinan, '-') AS jns_persalinan, "
                                        + "COALESCE(penolong_persalinan, '-') AS penolong_persalinan, "
                                        + "COALESCE(penyulit, '-') AS penyulit, "
                                        + "COALESCE(jk, '-') AS jk, "
                                        + "COALESCE(bb, '-') AS bb, "
                                        + "COALESCE(keadaan_anak_skrng, '-') AS keadaan_anak_skrng, "
                                        + "COALESCE(waktu_simpan, '-') AS waktu_simpan "
                                        + "FROM (SELECT * FROM riwayat_kehamilan_asesmen_awal_kebidanan WHERE no_rawat = '" + noRawat + "' "
                                        + "UNION ALL "
                                        + "SELECT NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL from dual "
                                        + "WHERE NOT EXISTS (SELECT 1 FROM riwayat_kehamilan_asesmen_awal_kebidanan WHERE "
                                        + "no_rawat = '" + noRawat + "')) AS x ORDER BY x.waktu_simpan IS NULL, x.waktu_simpan", param);
                            } else {
                                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));
                                Valid.MyReport("rptAsesmenAwalKebidanan1Qr.jasper", "report", "::[ Asesmen Awal Kebidanan hal. 1 ]::",
                                        "select date(now()) tgl", param);

                                Valid.MyReport("rptAsesmenAwalKebidanan2Qr.jasper", "report", "::[ Asesmen Awal Kebidanan hal. 2 ]::",
                                        "SELECT COALESCE(no_rawat, '-') AS no_rawat, "
                                        + "COALESCE(tahun_partus, '-') AS tahun_partus, "
                                        + "COALESCE(tempat_partus, '-') AS tempat_partus, "
                                        + "COALESCE(umur_hamil, '-') AS umur_hamil, "
                                        + "COALESCE(jns_persalinan, '-') AS jns_persalinan, "
                                        + "COALESCE(penolong_persalinan, '-') AS penolong_persalinan, "
                                        + "COALESCE(penyulit, '-') AS penyulit, "
                                        + "COALESCE(jk, '-') AS jk, "
                                        + "COALESCE(bb, '-') AS bb, "
                                        + "COALESCE(keadaan_anak_skrng, '-') AS keadaan_anak_skrng, "
                                        + "COALESCE(waktu_simpan, '-') AS waktu_simpan "
                                        + "FROM (SELECT * FROM riwayat_kehamilan_asesmen_awal_kebidanan WHERE no_rawat = '" + noRawat + "' "
                                        + "UNION ALL "
                                        + "SELECT NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL from dual "
                                        + "WHERE NOT EXISTS (SELECT 1 FROM riwayat_kehamilan_asesmen_awal_kebidanan WHERE "
                                        + "no_rawat = '" + noRawat + "')) AS x ORDER BY x.waktu_simpan IS NULL, x.waktu_simpan", param);
                            }

                        } else {
                            Valid.MyReport("rptAsesmenAwalKebidanan1.jasper", "report", "::[ Asesmen Awal Kebidanan hal. 1 ]::",
                                    "select date(now()) tgl", param);

                            Valid.MyReport("rptAsesmenAwalKebidanan2.jasper", "report", "::[ Asesmen Awal Kebidanan hal. 2 ]::",
                                    "SELECT COALESCE(no_rawat, '-') AS no_rawat, "
                                    + "COALESCE(tahun_partus, '-') AS tahun_partus, "
                                    + "COALESCE(tempat_partus, '-') AS tempat_partus, "
                                    + "COALESCE(umur_hamil, '-') AS umur_hamil, "
                                    + "COALESCE(jns_persalinan, '-') AS jns_persalinan, "
                                    + "COALESCE(penolong_persalinan, '-') AS penolong_persalinan, "
                                    + "COALESCE(penyulit, '-') AS penyulit, "
                                    + "COALESCE(jk, '-') AS jk, "
                                    + "COALESCE(bb, '-') AS bb, "
                                    + "COALESCE(keadaan_anak_skrng, '-') AS keadaan_anak_skrng, "
                                    + "COALESCE(waktu_simpan, '-') AS waktu_simpan "
                                    + "FROM (SELECT * FROM riwayat_kehamilan_asesmen_awal_kebidanan WHERE no_rawat = '" + noRawat + "' "
                                    + "UNION ALL "
                                    + "SELECT NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL from dual "
                                    + "WHERE NOT EXISTS (SELECT 1 FROM riwayat_kehamilan_asesmen_awal_kebidanan WHERE "
                                    + "no_rawat = '" + noRawat + "')) AS x ORDER BY x.waktu_simpan IS NULL, x.waktu_simpan", param);
                        }

                        //halaman 2
                        if (Sequel.cariInteger("select count(-1) from asesmen_awal_kebidanan2 where no_rawat='" + noRawat + "'") > 0) {
                            if (rsLaprm.getString("nyeri").equals("Ya")) {
                                if (rsLaprm.getString("lokasi_nyeri").equals("")) {
                                    param.put("AsesNyeri", rsLaprm.getString("nyeri") + ", Lokasi : ..........");
                                } else {
                                    param.put("AsesNyeri", rsLaprm.getString("nyeri") + ", Lokasi : " + rsLaprm.getString("lokasi_nyeri"));
                                }
                            } else {
                                param.put("AsesNyeri", rsLaprm.getString("nyeri"));
                            }

                            param.put("AsesJenis", rsLaprm.getString("jenis"));
                            param.put("AsesSkala", rsLaprm.getString("skala_nyeri"));

                            if (rsLaprm.getString("provocation").equals("Lainnya")) {
                                if (rsLaprm.getString("ket_lain_provocation").equals("")) {
                                    param.put("AsesProvo", rsLaprm.getString("provocation") + " ..........");
                                } else {
                                    param.put("AsesProvo", rsLaprm.getString("provocation") + " : " + rsLaprm.getString("ket_lain_provocation"));
                                }
                            } else {
                                param.put("AsesProvo", rsLaprm.getString("provocation"));
                            }

                            if (rsLaprm.getString("quality").equals("Lainnya")) {
                                if (rsLaprm.getString("ket_lain_quality").equals("")) {
                                    param.put("AsesQuality", rsLaprm.getString("quality") + " ..........");
                                } else {
                                    param.put("AsesQuality", rsLaprm.getString("quality") + " : " + rsLaprm.getString("ket_lain_quality"));
                                }
                            } else {
                                param.put("AsesQuality", rsLaprm.getString("quality"));
                            }

                            param.put("AsesRadia", rsLaprm.getString("radiation"));
                            param.put("AsesSever", rsLaprm.getString("severity"));

                            if (!rsLaprm.getString("time").equals("-")) {
                                param.put("AsesTime", rsLaprm.getString("time") + ", Lama : " + rsLaprm.getString("time_lama"));
                            } else {
                                param.put("AsesTime", rsLaprm.getString("time"));
                            }

                            //hitung skor gizi
                            int skorA = 0, skorB = 0, skorC = 0, skorTotal = 0;
                            if (rsLaprm.getString("gizi_1").equals("Tidak") || rsLaprm.getString("gizi_1").equals("Ya ada penurunan BB sebanyak :")) {
                                skorA = 0;
                            } else if (rsLaprm.getString("gizi_1").equals("Tidak Yakin (ada tanda : baju menjadi longgar)")) {
                                skorA = 2;
                            }

                            if (rsLaprm.getString("gizi_1ya").equals("-")) {
                                skorB = 0;
                            } else if (rsLaprm.getString("gizi_1ya").equals("1 - 5 Kg")) {
                                skorB = 1;
                            } else if (rsLaprm.getString("gizi_1ya").equals("6 - 10 Kg") || rsLaprm.getString("gizi_1ya").equals("Tidak tahu berapa Kg penurunanya")) {
                                skorB = 2;
                            } else if (rsLaprm.getString("gizi_1ya").equals("11 - 15 Kg")) {
                                skorB = 3;
                            } else if (rsLaprm.getString("gizi_1ya").equals("> 15 Kg")) {
                                skorB = 4;
                            }

                            if (rsLaprm.getString("gizi_2").equals("Tidak")) {
                                skorC = 0;
                            } else if (rsLaprm.getString("gizi_2").equals("Ya")) {
                                skorC = 1;
                            }

                            skorTotal = skorA + skorB + skorC;
                            if (rsLaprm.getString("gizi_1").equals("Ya ada penurunan BB sebanyak :")) {
                                param.put("gizi1", rsLaprm.getString("gizi_1"));
                            } else {
                                param.put("gizi1", rsLaprm.getString("gizi_1") + " (Skor : " + skorA + ")");
                            }

                            if (rsLaprm.getString("gizi_1ya").equals("-")) {
                                param.put("gizi1Ya", rsLaprm.getString("gizi_1ya"));
                            } else {
                                param.put("gizi1Ya", rsLaprm.getString("gizi_1ya") + " (Skor : " + skorB + ")");
                            }
                            param.put("gizi2", rsLaprm.getString("gizi_2") + " (Skor : " + skorC + ")");

                            if (skorTotal == 0 || skorTotal == 1) {
                                param.put("kesGizi", "Pasien tidak beresiko malnutrisi");
                            } else if (skorTotal >= 2) {
                                param.put("kesGizi", "Skor >= 2, pasien beresiko malnutrisi, konsul ke Ahli Gizi");
                            }
                            //---------------------------------------

                            if (rsLaprm.getString("cek_tidak_ada").equals("ya")) {
                                param.put("cekRiwTdkAda", "V");
                            } else {
                                param.put("cekRiwTdkAda", "");
                            }

                            if (rsLaprm.getString("cek_tidak_diketahui").equals("ya")) {
                                param.put("cekRiwTdkDik", "V");
                            } else {
                                param.put("cekRiwTdkDik", "");
                            }

                            if (rsLaprm.getString("cek_alergi_obat").equals("ya")) {
                                param.put("cekriwAlerObat", "V");
                                if (rsLaprm.getString("ket_alergi_obat").equals("")) {
                                    param.put("ketAlerObat", "Alergi Obat");
                                } else {
                                    param.put("ketAlerObat", "Alergi Obat : " + rsLaprm.getString("ket_alergi_obat"));
                                }

                                if (rsLaprm.getString("ket_reaksi_alergi_obat").equals("")) {
                                    param.put("ketReakAlerObat", "Reaksi ..........");
                                } else {
                                    param.put("ketReakAlerObat", "Reaksi : " + rsLaprm.getString("ket_reaksi_alergi_obat"));
                                }
                            } else {
                                param.put("cekriwAlerObat", "");
                                param.put("ketAlerObat", "Alergi Obat");
                                param.put("ketReakAlerObat", "Reaksi ..........");
                            }

                            if (rsLaprm.getString("cek_alergi_makanan").equals("ya")) {
                                param.put("cekriwAlerMak", "V");
                                if (rsLaprm.getString("ket_alergi_makanan").equals("")) {
                                    param.put("ketAlerMak", "Alergi Makanan");
                                } else {
                                    param.put("ketAlerMak", "Alergi Makanan : " + rsLaprm.getString("ket_alergi_makanan"));
                                }

                                if (rsLaprm.getString("ket_reaksi_alergi_makanan").equals("")) {
                                    param.put("ketReakAlerMak", "Reaksi ..........");
                                } else {
                                    param.put("ketReakAlerMak", "Reaksi : " + rsLaprm.getString("ket_reaksi_alergi_makanan"));
                                }
                            } else {
                                param.put("cekriwAlerMak", "");
                                param.put("ketAlerMak", "Alergi Makanan");
                                param.put("ketReakAlerMak", "Reaksi ..........");
                            }

                            if (rsLaprm.getString("cek_alergi_lainya").equals("ya")) {
                                param.put("cekriwAlerLain", "V");
                                if (rsLaprm.getString("ket_alergi_lainya").equals("")) {
                                    param.put("ketAlerLain", "Alergi Lainnya");
                                } else {
                                    param.put("ketAlerLain", "Alergi Lainnya : " + rsLaprm.getString("ket_alergi_lainya"));
                                }

                                if (rsLaprm.getString("ket_reaksi_alergi_lainya").equals("")) {
                                    param.put("ketReakAlerLain", "Reaksi ..........");
                                } else {
                                    param.put("ketReakAlerLain", "Reaksi : " + rsLaprm.getString("ket_reaksi_alergi_lainya"));
                                }
                            } else {
                                param.put("cekriwAlerLain", "");
                                param.put("ketAlerLain", "Alergi Lainnya");
                                param.put("ketReakAlerLain", "Reaksi ..........");
                            }

                            if (rsLaprm.getString("cek_gelang_tanda").equals("ya")) {
                                param.put("cekGelang", "V");
                            } else {
                                param.put("cekGelang", "");
                            }

                            if (rsLaprm.getString("cek_alergi_diberitahukan_dokter").equals("ya")) {
                                param.put("cekDokter", "V");
                            } else {
                                param.put("cekDokter", "");
                            }

                            if (rsLaprm.getString("cek_alergi_diberitahukan_farmasis").equals("ya")) {
                                param.put("cekFarmasi", "V");
                            } else {
                                param.put("cekFarmasi", "");
                            }

                            if (rsLaprm.getString("cek_alergi_diberitahukan_ahliGizi").equals("ya")) {
                                param.put("cekAhliGz", "V");
                            } else {
                                param.put("cekAhliGz", "");
                            }

                            //hitung skor resiko jatuh
                            int skorRJ = 0, skorKS = 0, skorAB = 0, skorT = 0, skorGB = 0, skorSM = 0, totSkorRJ = 0;
                            if (rsLaprm.getString("riw_jatuh_resiko_jatuh").equals("-") || rsLaprm.getString("riw_jatuh_resiko_jatuh").equals("Tidak ada atau >= 3 bulan")) {
                                skorRJ = 0;
                            } else if (rsLaprm.getString("riw_jatuh_resiko_jatuh").equals("< dari 3 bulan")) {
                                skorRJ = 25;
                            }

                            if (rsLaprm.getString("kondisi_kesehatan").equals("-") || rsLaprm.getString("kondisi_kesehatan").equals("< diagnosa penyakit")) {
                                skorKS = 0;
                            } else if (rsLaprm.getString("kondisi_kesehatan").equals("> diagnosa penyakit")) {
                                skorKS = 15;
                            }

                            if (rsLaprm.getString("alat_bantu_resiko_jatuh").equals("-") || rsLaprm.getString("alat_bantu_resiko_jatuh").equals("Tidak ada/kursi roda/tirah baring")) {
                                skorAB = 0;
                            } else if (rsLaprm.getString("alat_bantu_resiko_jatuh").equals("Berpegangan pada perabot")) {
                                skorAB = 30;
                            } else if (rsLaprm.getString("alat_bantu_resiko_jatuh").equals("Tongkat/alat penopang")) {
                                skorAB = 15;
                            }

                            if (rsLaprm.getString("terapi_IV").equals("-") || rsLaprm.getString("terapi_IV").equals("Tidak")) {
                                skorT = 0;
                            } else if (rsLaprm.getString("terapi_IV").equals("Terapi IV terus menerus")) {
                                skorT = 20;
                            }

                            if (rsLaprm.getString("gaya_berjalan").equals("-") || rsLaprm.getString("gaya_berjalan").equals("Normal/tirah baring/immobilisasi")) {
                                skorGB = 0;
                            } else if (rsLaprm.getString("gaya_berjalan").equals("Kerusakan/terganggu")) {
                                skorGB = 20;
                            } else if (rsLaprm.getString("gaya_berjalan").equals("Lemah")) {
                                skorGB = 10;
                            }

                            if (rsLaprm.getString("status_mental").equals("-") || rsLaprm.getString("status_mental").equals("Sadar kemampuan diri sendiri")) {
                                skorSM = 0;
                            } else if (rsLaprm.getString("status_mental").equals("Lupa keterbatasan yang dimiliki")) {
                                skorSM = 15;
                            }

                            totSkorRJ = skorRJ + skorKS + skorAB + skorT + skorGB + skorSM;
                            param.put("asesRiwJatuh", rsLaprm.getString("riw_jatuh_resiko_jatuh"));
                            param.put("skorAsesRJ", skorRJ);
                            param.put("asesKonKes", rsLaprm.getString("kondisi_kesehatan"));
                            param.put("skorAsesKS", skorKS);
                            param.put("asesAlatBan", rsLaprm.getString("alat_bantu_resiko_jatuh"));
                            param.put("skorAsesAB", skorAB);
                            param.put("asesTer", rsLaprm.getString("terapi_IV"));
                            param.put("skorAsesT", skorT);
                            param.put("asesGayBer", rsLaprm.getString("gaya_berjalan"));
                            param.put("skorAsesGB", skorGB);
                            param.put("asesStaMen", rsLaprm.getString("status_mental"));
                            param.put("skorAsesSM", skorSM);
                            param.put("TotSkorRJ", totSkorRJ);

                            if (totSkorRJ == 0 && totSkorRJ <= 24) {
                                param.put("kesResJatuh", "Skor 0-24 Resiko Rendah");
                            } else if (totSkorRJ >= 25 && totSkorRJ <= 45) {
                                param.put("kesResJatuh", "Skor 25-45 Resiko Sedang");
                            } else if (totSkorRJ >= 46) {
                                param.put("kesResJatuh", "Skor > 45 Resiko Tinggi");
                            }
                            //---------------------------------------

                            if (rsLaprm.getString("alat_bantu").equals("")) {
                                param.put("alatBan", "-");
                            } else {
                                param.put("alatBan", rsLaprm.getString("alat_bantu"));
                            }

                            if (rsLaprm.getString("prothesis").equals("")) {
                                param.put("protesis", "-");
                            } else {
                                param.put("protesis", rsLaprm.getString("prothesis"));
                            }

                            if (rsLaprm.getString("cacat_tubuh").equals("")) {
                                param.put("cacat", "-");
                            } else {
                                param.put("cacat", rsLaprm.getString("cacat_tubuh"));
                            }

                            param.put("adl", rsLaprm.getString("adl"));
                            param.put("fungRiwJat", rsLaprm.getString("riwayat_jatuh"));
                            param.put("fungNmBidan", rsLaprm.getString("nmBidan"));
                            param.put("fungNip", rsLaprm.getString("nip_bidan"));

                            if (rsLaprm.getString("cek_ya").equals("ya")) {
                                param.put("cekYa", "V");
                            } else {
                                param.put("cekYa", "");
                            }

                            if (rsLaprm.getString("cek_pendengaran").equals("ya")) {
                                param.put("cekDengar", "V");
                            } else {
                                param.put("cekDengar", "");
                            }

                            if (rsLaprm.getString("cek_penglihatan").equals("ya")) {
                                param.put("cekLihat", "V");
                            } else {
                                param.put("cekLihat", "");
                            }

                            if (rsLaprm.getString("cek_kognitif").equals("ya")) {
                                param.put("cekKognitif", "V");
                            } else {
                                param.put("cekKognitif", "");
                            }

                            if (rsLaprm.getString("cek_fisik").equals("ya")) {
                                param.put("cekFisik", "V");
                            } else {
                                param.put("cekFisik", "");
                            }

                            if (rsLaprm.getString("cek_budaya").equals("ya")) {
                                param.put("cekBudaya", "V");
                            } else {
                                param.put("cekBudaya", "");
                            }

                            if (rsLaprm.getString("cek_emosi").equals("ya")) {
                                param.put("cekEmosi", "V");
                            } else {
                                param.put("cekEmosi", "");
                            }

                            if (rsLaprm.getString("cek_bahasa").equals("ya")) {
                                param.put("cekBahasa", "V");
                            } else {
                                param.put("cekBahasa", "");
                            }

                            if (rsLaprm.getString("cek_lain_hambatan").equals("ya")) {
                                param.put("cekLainHam", "V");
                                if (rsLaprm.getString("ket_lain_hambatan").equals("")) {
                                    param.put("ketLainHam", "Lainnya ...........");
                                } else {
                                    param.put("ketLainHam", "Lainnya : " + rsLaprm.getString("ket_lain_hambatan"));
                                }
                            } else {
                                param.put("cekLainHam", "");
                                param.put("ketLainHam", "Lainnya ...........");
                            }

                            if (rsLaprm.getString("dibutuhkan_penerjemah").equals("Ya")) {
                                if (rsLaprm.getString("sebutkan").equals("")) {
                                    param.put("dibutuhkan", rsLaprm.getString("dibutuhkan_penerjemah") + ", sebutkan .........");
                                } else {
                                    param.put("dibutuhkan", rsLaprm.getString("dibutuhkan_penerjemah") + ", sebutkan : " + rsLaprm.getString("sebutkan"));
                                }
                            } else {
                                param.put("dibutuhkan", rsLaprm.getString("dibutuhkan_penerjemah"));
                            }

                            param.put("bahasaIsya", rsLaprm.getString("bahasa_isyarat"));

                            if (rsLaprm.getString("cek_diagnosa").equals("ya")) {
                                param.put("cekDiagnosa", "V");
                            } else {
                                param.put("cekDiagnosa", "");
                            }

                            if (rsLaprm.getString("cek_tindakan_keperawatan").equals("ya")) {
                                param.put("cekTindakan", "V");
                                if (rsLaprm.getString("ket_tindakan_keperawatan").equals("")) {
                                    param.put("ketTindakan", "Tindakan Keperawatan ............");
                                } else {
                                    param.put("ketTindakan", "Tindakan Keperawatan : " + rsLaprm.getString("ket_tindakan_keperawatan"));
                                }
                            } else {
                                param.put("cekTindakan", "");
                                param.put("ketTindakan", "Tindakan Keperawatan ............");
                            }

                            if (rsLaprm.getString("cek_lain_kebutuhan_edukasi").equals("ya")) {
                                param.put("cekLainKeb", "V");
                                if (rsLaprm.getString("ket_lain_kebutuhan_edukasi").equals("")) {
                                    param.put("ketLainKeb", "Lain-lain, Sebutkan ............");
                                } else {
                                    param.put("ketLainKeb", "Lain-lain, Sebutkan : " + rsLaprm.getString("ket_lain_kebutuhan_edukasi"));
                                }
                            } else {
                                param.put("cekLainKeb", "");
                                param.put("ketLainKeb", "Lain-lain, Sebutkan ............");
                            }

                            if (rsLaprm.getString("cek_obat_obatan").equals("ya")) {
                                param.put("cekObatan", "V");
                            } else {
                                param.put("cekObatan", "");
                            }

                            if (rsLaprm.getString("cek_rehabilitasi").equals("ya")) {
                                param.put("cekRehab", "V");
                            } else {
                                param.put("cekRehab", "");
                            }

                            if (rsLaprm.getString("cek_diet").equals("ya")) {
                                param.put("cekDiet", "V");
                            } else {
                                param.put("cekDiet", "");
                            }

                            if (rsLaprm.getString("cek_manajemen_nyeri").equals("ya")) {
                                param.put("cekMana", "V");
                            } else {
                                param.put("cekMana", "");
                            }

                            if (rsLaprm.getString("cek_pasien").equals("ya")) {
                                param.put("cekPasien", "V");
                            } else {
                                param.put("cekPasien", "");
                            }

                            if (rsLaprm.getString("cek_keluarga_pasien").equals("ya")) {
                                param.put("cekKlgPasien", "V");
                                if (rsLaprm.getString("nama_keluarga_pasien").equals("")) {
                                    param.put("nmKlgPasien", "Keluarga Pasien, Nama : ........");
                                } else {
                                    param.put("nmKlgPasien", "Keluarga Pasien, Nama : " + rsLaprm.getString("nama_keluarga_pasien"));
                                }
                            } else {
                                param.put("cekKlgPasien", "");
                                param.put("nmKlgPasien", "Keluarga Pasien, Nama : ........");
                            }

                            if (rsLaprm.getString("cek_tidak_dapat").equals("ya")) {
                                param.put("cekTdkDapat", "V");
                                if (rsLaprm.getString("ket_tidak_dapat").equals("")) {
                                    param.put("ketTdkDapat", "Tidak Dapat Memberikan Edukasi Kepada Pasien Atau Keluarga, Karena : ........");
                                } else {
                                    param.put("ketTdkDapat", "Tidak Dapat Memberikan Edukasi Kepada Pasien Atau Keluarga, Karena : " + rsLaprm.getString("ket_tidak_dapat"));
                                }
                            } else {
                                param.put("cekTdkDapat", "");
                                param.put("ketTdkDapat", "Tidak Dapat Memberikan Edukasi Kepada Pasien Atau Keluarga, Karena : ........");
                            }

                            param.put("tglEdukasi", Valid.SetTglINDONESIA(rsLaprm.getString("tgl_edukasi")));
                            param.put("jamEdukasi", rsLaprm.getString("jam_edukasi").substring(0, 5) + " Wita");
                            param.put("nmDokter", Sequel.cariIsi("select nama from pegawai where nik='" + rsLaprm.getString("nip_dokter") + "'"));
                            param.put("nipDokter", rsLaprm.getString("nip_dokter"));

                            if (rsLaprm.getString("cek_identifikasi1").equals("ya")) {
                                param.put("cekIden1", "V");
                            } else {
                                param.put("cekIden1", "");
                            }

                            if (rsLaprm.getString("cek_identifikasi2").equals("ya")) {
                                param.put("cekIden2", "V");
                            } else {
                                param.put("cekIden2", "");
                            }

                            if (rsLaprm.getString("cek_identifikasi3").equals("ya")) {
                                param.put("cekIden3", "V");
                            } else {
                                param.put("cekIden3", "");
                            }

                            if (rsLaprm.getString("cek_identifikasi4").equals("ya")) {
                                param.put("cekIden4", "V");
                            } else {
                                param.put("cekIden4", "");
                            }

                            if (rsLaprm.getString("cek_identifikasi5").equals("ya")) {
                                param.put("cekIden5", "V");
                            } else {
                                param.put("cekIden5", "");
                            }

                            if (rsLaprm.getString("cek_identifikasi6").equals("ya")) {
                                param.put("cekIden6", "V");
                            } else {
                                param.put("cekIden6", "");
                            }

                            if (rsLaprm.getString("cek_identifikasi7").equals("ya")) {
                                param.put("cekIden7", "V");
                            } else {
                                param.put("cekIden7", "");
                            }

                            if (rsLaprm.getString("cek_identifikasi8").equals("ya")) {
                                param.put("cekIden8", "V");
                            } else {
                                param.put("cekIden8", "");
                            }

                            if (rsLaprm.getString("cek_identifikasi9").equals("ya")) {
                                param.put("cekIden9", "V");
                            } else {
                                param.put("cekIden9", "");
                            }

                            if (rsLaprm.getString("cek_identifikasi10").equals("ya")) {
                                param.put("cekIden10", "V");
                            } else {
                                param.put("cekIden10", "");
                            }

                            if (rsLaprm.getString("memerlukan").equals("")) {
                                param.put("memer", "-");
                            } else {
                                param.put("memer", rsLaprm.getString("memerlukan"));
                            }

                            param.put("mpp", rsLaprm.getString("mpp"));
                            param.put("dp", rsLaprm.getString("dp"));
                            param.put("tglDp", Valid.SetTglINDONESIA(rsLaprm.getString("tgl_dp")));

                            if (rsLaprm.getString("nm_keluarga_pasien").equals("")) {
                                param.put("nmKeluarga", "-");
                            } else {
                                param.put("nmKeluarga", rsLaprm.getString("nm_keluarga_pasien"));
                            }

                            param.put("namaBidanDp", Sequel.cariIsi("select nama from pegawai where nik='" + rsLaprm.getString("nip_bidan_dp") + "'"));
                            param.put("nipBidanDp", rsLaprm.getString("nip_bidan_dp"));

                            if ((TabRawat.getSelectedIndex() == 0 && cmbPilihCetak.getSelectedIndex() == 0)
                                    || TabRawat.getSelectedIndex() == 1 && cmbPilihCetak1.getSelectedIndex() == 0) {
                                if (Sequel.cariInteger("select count(-1) from asesmen_awal_kebidanan2 where no_rawat='" + noRawat + "'") > 0
                                        && (rsLaprm.getString("nip_bidan_dp").equals("") || rsLaprm.getString("nip_bidan_dp").equals("-") || rsLaprm.getString("nip_bidan_dp").equals("--"))) {
                                    Valid.MyReport("rptAsesmenAwalKebidanan3.jasper", "report", "::[ Asesmen Awal Kebidanan hal. 3 ]::",
                                            "select date(now()) tgl", param);

                                    Valid.MyReport("rptAsesmenAwalKebidanan4.jasper", "report", "::[ Asesmen Awal Kebidanan hal. 4 ]::",
                                            "select date(now()) tgl", param);
                                } else {
                                    String isiBidan = "", isiDokter = "", isiBidanAk2 = "";
                                    param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));

                                    try {
                                        String gambar = "", ipGambar = "";
                                        try {
                                            //cek atau ping ip addres
                                            ipGambar = "192.168.0.230";
                                            InetAddress inet = InetAddress.getByName(ipGambar);

                                            //ping sukses timeout 100 ms (0.1 detik)
                                            if (inet.isReachable(100)) {
                                                if (rsLaprm.getString("id_file_nm_keluarga_pasien").equals("")) {
                                                    gambar = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                                                } else {
                                                    gambar = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + rsLaprm.getString("id_file_nm_keluarga_pasien");
                                                }
                                                //ping gagal
                                            } else {
                                                gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Notif : " + e);
                                            gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                                        }

                                        param.put("gambarTtd", gambar);
                                    } catch (Exception e) {
                                        System.out.println("Notifikasi : " + e);
                                    }

                                    //bidan pertama
                                    if (rsLaprm.getString("nip_bidan").equals("") || rsLaprm.getString("nip_bidan").equals("-") || rsLaprm.getString("nip_bidan").equals("--")) {
                                        param.put("lokasiQr", "");
                                    } else {
                                        isiBidan = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                                + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                                        "Asesmen Awal Kebidanan", rsLaprm.getString("nmBidan") + " (Bidan)",
                                                        rsLaprm.getString("tglak2"), rsLaprm.getString("jamak2")) + "') from kalimat_tte where kode='001'");

                                        Valid.cetakQrTte(isiBidan, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                                        Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                                        Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Asesmen Awal Kebidanan", Sequel.cariFolderPrintTte());
                                        param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                                    }

                                    Valid.MyReport("rptAsesmenAwalKebidanan3Qr.jasper", "report", "::[ Asesmen Awal Kebidanan hal. 3 ]::",
                                            "select date(now()) tgl", param);

                                    //dokter pemberi edukasi
                                    if (rsLaprm.getString("nip_dokter").equals("") || rsLaprm.getString("nip_dokter").equals("-")
                                            || rsLaprm.getString("nip_dokter").equals("--") || rsLaprm.getString("nip_dokter").contains("D0")) {
                                        param.put("lokasiQrDokter", "");
                                    } else {
                                        isiDokter = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                                + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                                        "Asesmen Awal Kebidanan", rsLaprm.getString("nmDokter") + " (Pemberi Edukasi)",
                                                        rsLaprm.getString("tglak2"), rsLaprm.getString("jamak2")) + "') from kalimat_tte where kode='001'");

                                        Valid.cetakQrTte(isiDokter, Sequel.cariFolderTte(), "QRTteDokter.jpg", "select logo from setting");
                                        param.put("lokasiQrDokter", Sequel.cariFolderTte() + File.separator + "QRTteDokter.jpg");
                                    }

                                    //bidan kedua
                                    if (rsLaprm.getString("nip_bidan_dp").equals("") || rsLaprm.getString("nip_bidan_dp").equals("-") || rsLaprm.getString("nip_bidan_dp").equals("--")) {
                                        JOptionPane.showMessageDialog(rootPane, "Nama bidan asesmen awal kebidanan harus diisi dulu,..");
                                    } else {
                                        isiBidanAk2 = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                                + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                                        "Asesmen Awal Kebidanan", rsLaprm.getString("nmBidanDp") + " (Bidan)",
                                                        rsLaprm.getString("tglak2"), rsLaprm.getString("jamak2")) + "') from kalimat_tte where kode='001'");

                                        Valid.cetakQrTte(isiBidanAk2, Sequel.cariFolderTte(), "QRTteBidanDp.jpg", "select logo from setting");
                                        param.put("lokasiQrBidan", Sequel.cariFolderTte() + File.separator + "QRTteBidanDp.jpg");

                                        Valid.MyReport("rptAsesmenAwalKebidanan4Qr.jasper", "report", "::[ Asesmen Awal Kebidanan hal. 4 ]::",
                                                "select date(now()) tgl", param);
                                        Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                                    }
                                }
                            } else {
                                Valid.MyReport("rptAsesmenAwalKebidanan3.jasper", "report", "::[ Asesmen Awal Kebidanan hal. 3 ]::",
                                        "select date(now()) tgl", param);

                                Valid.MyReport("rptAsesmenAwalKebidanan4.jasper", "report", "::[ Asesmen Awal Kebidanan hal. 4 ]::",
                                        "select date(now()) tgl", param);
                            }
                        }

                        emptTeks();
                        TabRawat.setSelectedIndex(1);
                        tampil(noRawat);
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
            this.setCursor(Cursor.getDefaultCursor());
        }        
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowNomorDokumenRM.dispose();
        Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

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
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbAsesmenKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil(noRawat);
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        BtnHapusActionPerformed(null);
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        BtnPrintActionPerformed(null);
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (Sequel.cariInteger("select count(-1) from asesmen_awal_kebidanan2 where no_rawat='" + noRawat + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from asesmen_awal_kebidanan2 where no_rawat='" + noRawat + "'") == 0) {
            TabRawat.setSelectedIndex(0);
            scrollKeAtas();
        }

        ((RMAsesmenAwalKebidanan2.Painter) gambarQR).setImage("");
        Sequel.cariIsiComboDB("select nm_dokumen from master_nomor_dokumen_erm where "
                + "status='aktif' and unit_pengguna='Ruang Perawatan & Instalasi' and ttd_keluarga_pasien='Ya' order by kode_erm", cmbRM);
        Sequel.queryu("DELETE FROM parameter_ttd_rme WHERE DATE(waktu_kirim) < CURDATE()");
    }//GEN-LAST:event_formWindowOpened

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilPreview();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar2ActionPerformed

    private void cmbResJatuhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbResJatuhActionPerformed
        hitungResikoJatuh();
    }//GEN-LAST:event_cmbResJatuhActionPerformed

    private void cmbKondisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKondisiActionPerformed
        hitungResikoJatuh();
    }//GEN-LAST:event_cmbKondisiActionPerformed

    private void cmbResAlatBantuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbResAlatBantuActionPerformed
        hitungResikoJatuh();
    }//GEN-LAST:event_cmbResAlatBantuActionPerformed

    private void cmbTerapiIVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTerapiIVActionPerformed
        hitungResikoJatuh();
    }//GEN-LAST:event_cmbTerapiIVActionPerformed

    private void cmbGayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGayaActionPerformed
        hitungResikoJatuh();
    }//GEN-LAST:event_cmbGayaActionPerformed

    private void cmbSttsMentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSttsMentalActionPerformed
        hitungResikoJatuh();
    }//GEN-LAST:event_cmbSttsMentalActionPerformed

    private void MnHapusTtdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTtdActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin tanda tangan penerima edukasi mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String ipGambar = "";
                try {
                    //cek atau ping ip addres
                    ipGambar = "192.168.0.230";
                    InetAddress inet = InetAddress.getByName(ipGambar);

                    //ping sukses timeout 100 ms (0.1 detik)
                    if (inet.isReachable(100)) {
                        if (idFileTtd.equals("")) {
                            JOptionPane.showMessageDialog(null, "Penerima edukasi pasien ini belum melakukan tanda tangan...!!!!");
                        } else {
                            if (Sequel.hapusFileTTD(idFileTtd) == true) {
                                Sequel.mengedit("asesmen_awal_kebidanan2", "no_rawat='" + noRawat + "'", "id_file_nm_keluarga_pasien=''");
                                tampil(noRawat);
                                emptTeks();
                            }
                        }
                        //ping gagal
                    } else {
                        JOptionPane.showMessageDialog(null, "Koneksi ke server terputus...!!!!");
                    }
                } catch (Exception e) {
                    System.out.println("Notif : " + e);
                }
            } else {
                tampil(noRawat);
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_MnHapusTtdActionPerformed

    private void MnBikinQrCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBikinQrCodeActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            ((RMAsesmenAwalKebidanan2.Painter) gambarQR).setImage("");
            if (Sequel.cariInteger("select count(-1) from master_nomor_dokumen_erm where nm_dokumen like '%ASESMEN AWAL KEBIDANAN%'") > 0) {
                bikinQR();
            } else {
                WindowNomorDokumenRM.setSize(737, 125);
                WindowNomorDokumenRM.setLocationRelativeTo(internalFrame1);
                WindowNomorDokumenRM.setVisible(true);

                cmbRM.setSelectedIndex(0);
                cmbRM.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_MnBikinQrCodeActionPerformed

    private void BtnTampilkanQrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTampilkanQrActionPerformed
        if (akses.getadmin() == true) {
            usernya = "admin";
            pwdnya = "satu";
        } else {
            usernya = akses.getkode();
            pwdnya = Sequel.cariIsi("select AES_DECRYPT(u.password,'windi') from user u "
                    + "inner join petugas pt on pt.nip=AES_DECRYPT(u.id_user,'nur') where AES_DECRYPT(u.id_user,'nur')='" + akses.getkode() + "'");
        }
        
        idParameterTtd = Sequel.cariIsi("select concat('rmeRZ',replace(date(now()),'-',''),'',replace(time(now()),':',''))");

        try {
            URL = prop.getProperty("URLTTDKELUARGAPASIEN") + idParameterTtd;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        if (cmbRM.getSelectedIndex() != 0) {
            Valid.cetakQrTte(URL, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
            Sequel.menyimpanQrTte("parameter_ttd_rme", "'" + idParameterTtd + "','" + usernya + "','" + pwdnya + "','" + noRawat + "','" + Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + noRawat + "'") + "','"
                    + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen='" + cmbRM.getSelectedItem().toString() + "'") + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

            try {
                ((RMAsesmenAwalKebidanan2.Painter) gambarQR).setImage("");
                ResultSet hasil = koneksi.createStatement().executeQuery(
                        "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
                for (int I = 0; hasil.next(); I++) {
                    Blob blob = hasil.getBlob(1);
                    ((RMAsesmenAwalKebidanan2.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
                            blob.getBytes(1, (int) (blob.length()))));
                    blob.free();
                }

                BtnCloseIn2ActionPerformed(null);
                tampil(noRawat);
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu jenis rekam medis yang dipilih..!!");
            cmbRM.requestFocus();
        }
    }//GEN-LAST:event_BtnTampilkanQrActionPerformed

    private void BtnCloseIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn2ActionPerformed
        emptTeks();
        WindowNomorDokumenRM.dispose();
    }//GEN-LAST:event_BtnCloseIn2ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenAwalKebidanan2 dialog = new RMAsesmenAwalKebidanan2(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnBidan1;
    private widget.Button BtnBidan2;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn2;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar2;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnSimpan;
    private widget.Button BtnTampilkanQr;
    private widget.Label LCount;
    private widget.editorpane LoadHTML1;
    private widget.editorpane LoadHTML2;
    private javax.swing.JMenuItem MnBikinQrCode;
    private javax.swing.JMenuItem MnHapusTtd;
    private javax.swing.JPanel PanelInput;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll19;
    private widget.ScrollPane Scroll5;
    private widget.TextBox TJmlSkor;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Label TalasanMrs;
    private widget.TextBox TalatBantu;
    private widget.TextBox TcacatTubuh;
    private widget.TextBox TketLainHambatan;
    private widget.TextBox TketRiwAlergiLain;
    private widget.TextBox TketRiwAlergiMak;
    private widget.TextBox TketRiwAlergiObat;
    private widget.TextBox TlainKebutuhan;
    private widget.TextBox Tlokasi;
    private widget.TextBox Tmemerlukan;
    private widget.TextBox TnmBidan1;
    private widget.TextBox TnmBidan2;
    private widget.TextBox TnmDokter;
    private widget.TextBox TnmKeluargaPasien;
    private widget.TextBox TnmKlgPasien;
    private widget.Label TnmSuami;
    private widget.TextBox TotSkorGizi;
    private widget.Label Tpasien;
    private widget.TextBox Tprotesis;
    private widget.TextBox Tprovo;
    private widget.TextBox Tquality;
    private widget.TextBox TreakRiwAlergiLain;
    private widget.TextBox TreakRiwAlergiMak;
    private widget.TextBox TreakRiwAlergiObat;
    private widget.Label TrgRawat;
    private widget.TextBox Tsebutkan;
    private widget.TextBox TskorGaya;
    private widget.TextBox TskorKondisi;
    private widget.TextBox TskorResAlatBantu;
    private widget.TextBox TskorResJatuh;
    private widget.TextBox TskorSttsMental;
    private widget.TextBox TskorTerapi;
    private widget.Tanggal TtglDp;
    private widget.Tanggal TtglEdukasi;
    private widget.TextBox TtidakDapat;
    private widget.TextBox TtindakanKep;
    private widget.Label TumurPas;
    private javax.swing.JDialog WindowNomorDokumenRM;
    public widget.CekBox chkAhliGz;
    public widget.CekBox chkBahasa;
    public widget.CekBox chkBudaya;
    private widget.RadioButton chkDP;
    public widget.CekBox chkDiagnosa;
    public widget.CekBox chkDietNutrisi;
    public widget.CekBox chkDokter;
    public widget.CekBox chkEmosi;
    public widget.CekBox chkFarmasis;
    public widget.CekBox chkFisik;
    public widget.CekBox chkGelang;
    private widget.RadioButton chkIdentifikai1;
    private widget.RadioButton chkIdentifikai10;
    private widget.RadioButton chkIdentifikai2;
    public widget.CekBox chkIdentifikai3;
    public widget.CekBox chkIdentifikai4;
    private widget.RadioButton chkIdentifikai5;
    private widget.RadioButton chkIdentifikai6;
    public widget.CekBox chkIdentifikai7;
    private widget.RadioButton chkIdentifikai8;
    public widget.CekBox chkIdentifikai9;
    public widget.CekBox chkKlgPasien;
    public widget.CekBox chkKognitif;
    public widget.CekBox chkLainHambatan;
    public widget.CekBox chkLainKebutuhan;
    public widget.CekBox chkMPP;
    public widget.CekBox chkManajemenNyeri;
    public widget.CekBox chkObatTerapi;
    public widget.CekBox chkPasien;
    public widget.CekBox chkPendengaran;
    public widget.CekBox chkPenglihatan;
    public widget.CekBox chkRehabilitasi;
    public widget.CekBox chkRiwAlergiLain;
    public widget.CekBox chkRiwAlergiMak;
    public widget.CekBox chkRiwAlergiObat;
    public widget.CekBox chkRiwTidakAda;
    public widget.CekBox chkRiwTidakDik;
    private widget.CekBox chkSaya1;
    private widget.CekBox chkSaya2;
    public widget.CekBox chkTidakDapat;
    public widget.CekBox chkTindakanKep;
    public widget.CekBox chkYaTerdapat;
    private widget.ComboBox cmbAdl;
    private widget.ComboBox cmbBahasa;
    private widget.ComboBox cmbDibutuhkan;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbGaya;
    private widget.ComboBox cmbGizi1;
    private widget.ComboBox cmbGizi2;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJenis;
    private widget.ComboBox cmbKondisi;
    private widget.ComboBox cmbLama;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbNyeri;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbPilihCetak1;
    private widget.ComboBox cmbProvo;
    private widget.ComboBox cmbQuality;
    private widget.ComboBox cmbRM;
    private widget.ComboBox cmbRadia;
    private widget.ComboBox cmbResAlatBantu;
    private widget.ComboBox cmbResJatuh;
    private widget.ComboBox cmbRiwJatuh;
    private widget.ComboBox cmbSever;
    private widget.ComboBox cmbSkala;
    private widget.ComboBox cmbSttsMental;
    private widget.ComboBox cmbTerapiIV;
    private widget.ComboBox cmbTime;
    private widget.ComboBox cmbYaGizi1;
    private java.awt.Canvas gambarQR;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame21;
    private widget.InternalFrame internalFrame25;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel125;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel365;
    private widget.Label jLabel366;
    private widget.Label jLabel367;
    private widget.Label jLabel368;
    private widget.Label jLabel369;
    private widget.Label jLabel370;
    private widget.Label jLabel371;
    private widget.Label jLabel372;
    private widget.Label jLabel373;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel68;
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
    private widget.TextArea kesimpulanGizi;
    private widget.TextArea kesimpulanResJatuh;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label22;
    private widget.Label label25;
    private widget.Label label26;
    private widget.Label label27;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass2;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelTombol;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi6;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane7;
    private widget.TextBox skorGizi1;
    private widget.TextBox skorGizi2;
    private widget.TextBox skorYaGizi1;
    private widget.Table tbAsesmen;
    // End of variables declaration//GEN-END:variables

    public void emptTeks() {
        cmbNyeri.setSelectedIndex(0);
        Tlokasi.setText("");
        Tlokasi.setEnabled(false);
        cmbJenis.setSelectedIndex(0);
        cmbSkala.setSelectedIndex(0);

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
        cmbLama.setEnabled(false);

        cmbGizi1.setSelectedIndex(0);
        cmbYaGizi1.setSelectedIndex(0);
        cmbYaGizi1.setEnabled(false);
        skorGizi1.setText("0");
        skorYaGizi1.setText("0");
        cmbGizi2.setSelectedIndex(0);
        skorGizi2.setText("0");
        TotSkorGizi.setText("0");
        kesimpulanGizi.setText("");
        hitungSkorGizi();

        chkRiwTidakAda.setSelected(false);
        chkRiwTidakDik.setSelected(false);
        chkRiwAlergiObat.setSelected(false);
        TketRiwAlergiObat.setText("");
        TreakRiwAlergiObat.setText("");
        TketRiwAlergiObat.setEnabled(false);
        TreakRiwAlergiObat.setEnabled(false);

        chkRiwAlergiMak.setSelected(false);
        TketRiwAlergiMak.setText("");
        TreakRiwAlergiMak.setText("");
        TketRiwAlergiMak.setEnabled(false);
        TreakRiwAlergiMak.setEnabled(false);

        chkRiwAlergiLain.setSelected(false);
        TketRiwAlergiLain.setText("");
        TreakRiwAlergiLain.setText("");
        TketRiwAlergiLain.setEnabled(false);
        TreakRiwAlergiLain.setEnabled(false);
        chkGelang.setSelected(false);
        chkDokter.setSelected(false);
        chkFarmasis.setSelected(false);
        chkAhliGz.setSelected(false);
        
        cmbResJatuh.setSelectedIndex(0);
        cmbKondisi.setSelectedIndex(0);
        cmbResAlatBantu.setSelectedIndex(0);
        cmbTerapiIV.setSelectedIndex(0);
        cmbGaya.setSelectedIndex(0);
        cmbSttsMental.setSelectedIndex(0);        
        TskorResJatuh.setText("0");
        TskorKondisi.setText("0");
        TskorResAlatBantu.setText("0");
        TskorTerapi.setText("0");
        TskorGaya.setText("0");
        TskorSttsMental.setText("0");
        TJmlSkor.setText("0");
        kesimpulanResJatuh.setText("");
        hitungResikoJatuh();

        TalatBantu.setText("");
        Tprotesis.setText("");
        TcacatTubuh.setText("");
        cmbAdl.setSelectedIndex(0);
        cmbRiwJatuh.setSelectedIndex(0);
        nipBidan1 = "-";
        TnmBidan1.setText("-");

        chkYaTerdapat.setSelected(false);
        chkPendengaran.setSelected(false);
        chkPenglihatan.setSelected(false);
        chkKognitif.setSelected(false);
        chkFisik.setSelected(false);
        chkBudaya.setSelected(false);
        chkEmosi.setSelected(false);
        chkBahasa.setSelected(false);
        chkLainHambatan.setSelected(false);
        TketLainHambatan.setText("");
        chkPendengaran.setEnabled(false);
        chkPenglihatan.setEnabled(false);
        chkKognitif.setEnabled(false);
        chkFisik.setEnabled(false);
        chkBudaya.setEnabled(false);
        chkEmosi.setEnabled(false);
        chkBahasa.setEnabled(false);
        chkLainHambatan.setEnabled(false);
        TketLainHambatan.setEnabled(false);

        cmbDibutuhkan.setSelectedIndex(0);
        Tsebutkan.setText("");
        Tsebutkan.setEnabled(false);
        cmbBahasa.setSelectedIndex(0);

        chkDiagnosa.setSelected(false);
        chkObatTerapi.setSelected(false);
        chkDietNutrisi.setSelected(false);
        chkRehabilitasi.setSelected(false);
        chkManajemenNyeri.setSelected(false);

        chkTindakanKep.setSelected(false);
        TtindakanKep.setText("");
        TtindakanKep.setEnabled(false);

        chkLainKebutuhan.setSelected(false);
        TlainKebutuhan.setText("");
        TlainKebutuhan.setEnabled(false);

        chkPasien.setSelected(false);
        chkKlgPasien.setSelected(false);
        TnmKlgPasien.setText("");
        TnmKlgPasien.setEnabled(false);
        chkTidakDapat.setSelected(false);
        TtidakDapat.setText("");
        TtidakDapat.setEnabled(false);

        TtglEdukasi.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        nipDokter = "-";
        TnmDokter.setText("-");

        chkIdentifikai1.setSelected(false);
        chkIdentifikai2.setSelected(false);
        chkIdentifikai3.setSelected(false);
        chkIdentifikai4.setSelected(false);
        chkIdentifikai5.setSelected(false);
        chkIdentifikai6.setSelected(false);
        chkIdentifikai7.setSelected(false);
        chkIdentifikai8.setSelected(false);
        chkIdentifikai9.setSelected(false);
        chkIdentifikai10.setSelected(false);
        Tmemerlukan.setText("");
        chkMPP.setSelected(false);
        chkDP.setSelected(false);
        TtglDp.setDate(new Date());
        TnmKeluargaPasien.setText("");
        nipBidan2 = "-";
        TnmBidan2.setText("-");
        chkSaya1.setSelected(false);
        chkSaya2.setSelected(false);
        idFileTtd = "";
        idParameterTtd = "";
        URL = "";
        usernya = "";
        pwdnya = "";
        LoadHTML2.setText("");
    }
    
    private void hitungSkorGizi() {
        int A, B, C, Total;
        A = Integer.parseInt(skorGizi1.getText());
        B = Integer.parseInt(skorYaGizi1.getText());
        C = Integer.parseInt(skorGizi2.getText());

        Total = 0;
        Total = A + B + C;
        TotSkorGizi.setText(Valid.SetAngka2(Total));

        if (Total == 0 || Total == 1) {
            kesimpulanGizi.setText("Pasien tidak beresiko malnutrisi");
        } else if (Total >= 2) {
            kesimpulanGizi.setText("Skor >= 2, pasien beresiko malnutrisi, konsul ke Ahli Gizi");
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnEdit.setEnabled(akses.getcppt());
//        BtnSimpan.setEnabled(akses.getpenilaian_awal_keperawatan_kebidanan());
//        BtnHapus.setEnabled(akses.getpenilaian_awal_keperawatan_kebidanan());
//        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_kebidanan());
        
        if (akses.getjml2() >= 1) {
            BtnBidan1.setEnabled(false);
            BtnBidan2.setEnabled(false);
            nipBidan1 = akses.getkode();
            nipBidan2 = akses.getkode();
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmBidan1, nipBidan1);
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmBidan2, nipBidan2);
            if (TnmBidan1.getText().equals("")) {
                nipBidan1 = "";
            }

            if (TnmBidan2.getText().equals("")) {
                nipBidan2 = "";
            }
        }
    }
    
    public void tampil(String norwt) {
        LoadHTML2.setText("");
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT ak2.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tglLahir, DATE_FORMAT(ak1.tgl_asesmen,'%d-%m-%Y') tglAses, "
                    + "concat(rp.umurdaftar,' ',rp.sttsumur) umurPas, p.pekerjaan, p.agama, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamatPas, "
                    + "p.stts_nikah, pg1.nama nmBidan, pg2.nama nmDokter, ak1.ruang_rawat FROM asesmen_awal_kebidanan2 ak2 inner join asesmen_awal_kebidanan1 ak1 on ak1.no_rawat=ak2.no_rawat "
                    + "inner join reg_periksa rp on rp.no_rawat=ak2.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join kelurahan kl on kl.kd_kel=p.kd_kel inner join kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "inner join kabupaten kb on kb.kd_kab=p.kd_kab inner join pegawai pg1 on pg1.nik=ak2.nip_bidan_dp inner join pegawai pg2 on pg2.nik=ak2.nip_dokter "
                    + "where ak2.no_rawat ='" + norwt + "' order by ak1.tgl_asesmen desc");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglAses"),
                        rs.getString("umurPas"),
                        rs.getString("pekerjaan"),
                        rs.getString("agama"),
                        rs.getString("alamatPas"),
                        rs.getString("stts_nikah"),
                        rs.getString("nmBidan"),
                        rs.getString("nmDokter"),
                        rs.getString("nyeri"),
                        rs.getString("lokasi_nyeri"),
                        rs.getString("jenis"),
                        rs.getString("skala_nyeri"),
                        rs.getString("provocation"),
                        rs.getString("ket_lain_provocation"),
                        rs.getString("quality"),
                        rs.getString("ket_lain_quality"),
                        rs.getString("radiation"),
                        rs.getString("severity"),
                        rs.getString("time"),
                        rs.getString("time_lama"),
                        rs.getString("gizi_1"),
                        rs.getString("gizi_1ya"),
                        rs.getString("gizi_2"),
                        rs.getString("cek_tidak_ada"),
                        rs.getString("cek_tidak_diketahui"),
                        rs.getString("cek_alergi_obat"),
                        rs.getString("ket_alergi_obat"),
                        rs.getString("ket_reaksi_alergi_obat"),
                        rs.getString("cek_alergi_makanan"),
                        rs.getString("ket_alergi_makanan"),
                        rs.getString("ket_reaksi_alergi_makanan"),
                        rs.getString("cek_alergi_lainya"),
                        rs.getString("ket_alergi_lainya"),
                        rs.getString("ket_reaksi_alergi_lainya"),
                        rs.getString("cek_gelang_tanda"),
                        rs.getString("cek_alergi_diberitahukan_dokter"),
                        rs.getString("cek_alergi_diberitahukan_farmasis"),
                        rs.getString("cek_alergi_diberitahukan_ahliGizi"),
                        rs.getString("alat_bantu"),
                        rs.getString("prothesis"),
                        rs.getString("cacat_tubuh"),
                        rs.getString("adl"),
                        rs.getString("riwayat_jatuh"),
                        rs.getString("nip_bidan"),
                        rs.getString("cek_ya"),
                        rs.getString("cek_pendengaran"),
                        rs.getString("cek_penglihatan"),
                        rs.getString("cek_kognitif"),
                        rs.getString("cek_fisik"),
                        rs.getString("cek_budaya"),
                        rs.getString("cek_emosi"),
                        rs.getString("cek_bahasa"),
                        rs.getString("cek_lain_hambatan"),
                        rs.getString("ket_lain_hambatan"),
                        rs.getString("dibutuhkan_penerjemah"),
                        rs.getString("sebutkan"),
                        rs.getString("bahasa_isyarat"),
                        rs.getString("cek_diagnosa"),
                        rs.getString("cek_tindakan_keperawatan"),
                        rs.getString("ket_tindakan_keperawatan"),
                        rs.getString("cek_lain_kebutuhan_edukasi"),
                        rs.getString("ket_lain_kebutuhan_edukasi"),
                        rs.getString("cek_obat_obatan"),
                        rs.getString("cek_rehabilitasi"),
                        rs.getString("cek_diet"),
                        rs.getString("cek_manajemen_nyeri"),
                        rs.getString("cek_pasien"),
                        rs.getString("cek_keluarga_pasien"),
                        rs.getString("nama_keluarga_pasien"),
                        rs.getString("cek_tidak_dapat"),
                        rs.getString("ket_tidak_dapat"),
                        rs.getString("tgl_edukasi"),
                        rs.getString("jam_edukasi"),
                        rs.getString("nip_dokter"),
                        rs.getString("cek_identifikasi1"),
                        rs.getString("cek_identifikasi2"),
                        rs.getString("cek_identifikasi3"),
                        rs.getString("cek_identifikasi4"),
                        rs.getString("cek_identifikasi5"),
                        rs.getString("cek_identifikasi6"),
                        rs.getString("cek_identifikasi7"),
                        rs.getString("cek_identifikasi8"),
                        rs.getString("cek_identifikasi9"),
                        rs.getString("cek_identifikasi10"),
                        rs.getString("memerlukan"),
                        rs.getString("mpp"),
                        rs.getString("dp"),
                        rs.getString("tgl_dp"),
                        rs.getString("nm_keluarga_pasien"),
                        rs.getString("nip_bidan_dp"),
                        rs.getString("status_rawat"),
                        rs.getString("waktu_simpan"),                        
                        rs.getString("riw_jatuh_resiko_jatuh"),
                        rs.getString("kondisi_kesehatan"),
                        rs.getString("alat_bantu_resiko_jatuh"),
                        rs.getString("terapi_IV"),
                        rs.getString("gaya_berjalan"),
                        rs.getString("status_mental"),
                        rs.getString("jumlah_skor"),
                        rs.getString("kesimpulan_resiko_jatuh"),
                        rs.getString("id_file_nm_keluarga_pasien")
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
    
    private void cekData() {
        if (chkRiwTidakAda.isSelected() == true) {
            tidakAda = "ya";
        } else {
            tidakAda = "tidak";
        }
        
        if (chkRiwTidakDik.isSelected() == true) {
            tidakDiketahui = "ya";
        } else {
            tidakDiketahui = "tidak";
        }
        
        if (chkRiwAlergiObat.isSelected() == true) {
            alergiObat = "ya";
        } else {
            alergiObat = "tidak";
        }
        
        if (chkRiwAlergiMak.isSelected() == true) {
            alergiMakanan = "ya";
        } else {
            alergiMakanan = "tidak";
        }
        
        if (chkRiwAlergiLain.isSelected() == true) {
            alergiLainya = "ya";
        } else {
            alergiLainya = "tidak";
        }
        
        if (chkGelang.isSelected() == true) {
            gelangTanda = "ya";
        } else {
            gelangTanda = "tidak";
        }
        
        if (chkDokter.isSelected() == true) {
            alergiDiberitahukanDokter = "ya";
        } else {
            alergiDiberitahukanDokter = "tidak";
        }
        if (chkFarmasis.isSelected() == true) {
            alergiDiberitahukanFarmasis = "ya";
        } else {
            alergiDiberitahukanFarmasis = "tidak";
        }
        
        if (chkAhliGz.isSelected() == true) {
            alergiDiberitahukanAhligizi = "ya";
        } else {
            alergiDiberitahukanAhligizi = "tidak";
        }
        
        if (chkYaTerdapat.isSelected() == true) {
            ya = "ya";
        } else {
            ya = "tidak";
        }
        
        if (chkPendengaran.isSelected() == true) {
            pendengaran = "ya";
        } else {
            pendengaran = "tidak";
        }
        
        if (chkPenglihatan.isSelected() == true) {
            penglihatan = "ya";
        } else {
            penglihatan = "tidak";
        }
        
        if (chkKognitif.isSelected() == true) {
            kognitif = "ya";
        } else {
            kognitif = "tidak";
        }

        if (chkFisik.isSelected() == true) {
            fisik = "ya";
        } else {
            fisik = "tidak";
        }
        
        if (chkBudaya.isSelected() == true) {
            budaya = "ya";
        } else {
            budaya = "tidak";
        }
        
        if (chkEmosi.isSelected() == true) {
            emosi = "ya";
        } else {
            emosi = "tidak";
        }
        
        if (chkBahasa.isSelected() == true) {
            bahasa = "ya";
        } else {
            bahasa = "tidak";
        }
        
        if (chkLainHambatan.isSelected() == true) {
            lainHambatan = "ya";
        } else {
            lainHambatan = "tidak";
        }
        
        if (chkDiagnosa.isSelected() == true) {
            diagnosa = "ya";
        } else {
            diagnosa = "tidak";
        }
        
        if (chkTindakanKep.isSelected() == true) {
            tindakanKeperawatan = "ya";
        } else {
            tindakanKeperawatan = "tidak";
        }
        
        if (chkLainKebutuhan.isSelected() == true) {
            lainKebutuhanEdukasi = "ya";
        } else {
            lainKebutuhanEdukasi = "tidak";
        }
        
        if (chkObatTerapi.isSelected() == true) {
            obatObatan = "ya";
        } else {
            obatObatan = "tidak";
        }
        
        if (chkRehabilitasi.isSelected() == true) {
            rehabilitasi = "ya";
        } else {
            rehabilitasi = "tidak";
        }
        
        if (chkDietNutrisi.isSelected() == true) {
            diet = "ya";
        } else {
            diet = "tidak";
        }
        
        if (chkManajemenNyeri.isSelected() == true) {
            manajemenNyeri = "ya";
        } else {
            manajemenNyeri = "tidak";
        }
        
        if (chkPasien.isSelected() == true) {
            pasien = "ya";
        } else {
            pasien = "tidak";
        }
        
        if (chkKlgPasien.isSelected() == true) {
            keluargaPasien = "ya";
        } else {
            keluargaPasien = "tidak";
        }
        
        if (chkTidakDapat.isSelected() == true) {
            tidakDapat = "ya";
        } else {
            tidakDapat = "tidak";
        }
        
        if (chkIdentifikai1.isSelected() == true) {
            identifikasi1 = "ya";
        } else {
            identifikasi1 = "tidak";
        }
        
        if (chkIdentifikai2.isSelected() == true) {
            identifikasi2 = "ya";
        } else {
            identifikasi2 = "tidak";
        }
        
        if (chkIdentifikai3.isSelected() == true) {
            identifikasi3 = "ya";
        } else {
            identifikasi3 = "tidak";
        }
        
        if (chkIdentifikai4.isSelected() == true) {
            identifikasi4 = "ya";
        } else {
            identifikasi4 = "tidak";
        }
        
        if (chkIdentifikai5.isSelected() == true) {
            identifikasi5 = "ya";
        } else {
            identifikasi5 = "tidak";
        }
        
        if (chkIdentifikai6.isSelected() == true) {
            identifikasi6 = "ya";
        } else {
            identifikasi6 = "tidak";
        }
        
        if (chkIdentifikai7.isSelected() == true) {
            identifikasi7 = "ya";
        } else {
            identifikasi7 = "tidak";
        }
        
        if (chkIdentifikai8.isSelected() == true) {
            identifikasi8 = "ya";
        } else {
            identifikasi8 = "tidak";
        }
        
        if (chkIdentifikai9.isSelected() == true) {
            identifikasi9 = "ya";
        } else {
            identifikasi9 = "tidak";
        }
        
        if (chkIdentifikai10.isSelected() == true) {
            identifikasi10 = "ya";
        } else {
            identifikasi10 = "tidak";
        }
        
        if (chkMPP.isSelected() == true) {
            mpp = "Ya";
        } else {
            mpp = "Tidak";
        }
        
        if (chkDP.isSelected() == true) {
            dp = "Ya";
        } else {
            dp = "Tidak";
        }
    }
    
    private void dataCek() {
        if (cmbNyeri.getSelectedIndex() == 1) {
            Tlokasi.setEnabled(true);
        } else {
            Tlokasi.setEnabled(false);
        }
        
        if (cmbProvo.getSelectedIndex() == 6) {
            Tprovo.setEnabled(true);
        } else {
            Tprovo.setEnabled(false);
        }
        
        if (cmbQuality.getSelectedIndex() == 9) {
            Tquality.setEnabled(true);
        } else {
            Tquality.setEnabled(false);
        }
        
        if (cmbTime.getSelectedIndex() == 0) {
            cmbLama.setEnabled(false);
        } else {
            cmbLama.setEnabled(true);
        }
        
        if (cmbGizi1.getSelectedIndex() == 0) {
            skorGizi1.setText("0");
            cmbYaGizi1.setEnabled(false);
        } else if (cmbGizi1.getSelectedIndex() == 1) {
            skorGizi1.setText("2");
            cmbYaGizi1.setEnabled(false);
        } else if (cmbGizi1.getSelectedIndex() == 2) {
            skorGizi1.setText("0");
            cmbYaGizi1.setEnabled(true);
        }
        
        if (cmbYaGizi1.getSelectedIndex() == 0) {
            skorYaGizi1.setText("0");
        } else if (cmbYaGizi1.getSelectedIndex() == 1) {
            skorYaGizi1.setText("1");
        } else if (cmbYaGizi1.getSelectedIndex() == 2) {
            skorYaGizi1.setText("2");
        } else if (cmbYaGizi1.getSelectedIndex() == 3) {
            skorYaGizi1.setText("3");
        } else if (cmbYaGizi1.getSelectedIndex() == 4) {
            skorYaGizi1.setText("4");
        } else if (cmbYaGizi1.getSelectedIndex() == 5) {
            skorYaGizi1.setText("2");
        }
        
        if (cmbGizi2.getSelectedIndex() == 0) {
            skorGizi2.setText("0");
        } else if (cmbGizi2.getSelectedIndex() == 1) {
            skorGizi2.setText("1");
        }
        
        hitungSkorGizi();
        
        if (tidakAda.equals("ya")) {
            chkRiwTidakAda.setSelected(true);
        } else {
            chkRiwTidakAda.setSelected(false);
        }
        
        if (tidakDiketahui.equals("ya")) {
            chkRiwTidakDik.setSelected(true);
        } else {
            chkRiwTidakDik.setSelected(false);
        }
        
        if (alergiObat.equals("ya")) {
            chkRiwAlergiObat.setSelected(true);
            TketRiwAlergiObat.setEnabled(true);
            TreakRiwAlergiObat.setEnabled(true);
        } else {
            chkRiwAlergiObat.setSelected(false);
            TketRiwAlergiObat.setEnabled(false);
            TreakRiwAlergiObat.setEnabled(false);
        }
        
        if (alergiMakanan.equals("ya")) {
            chkRiwAlergiMak.setSelected(true);
            TketRiwAlergiMak.setEnabled(true);
            TreakRiwAlergiMak.setEnabled(true);
        } else {
            chkRiwAlergiMak.setSelected(false);
            TketRiwAlergiMak.setEnabled(false);
            TreakRiwAlergiMak.setEnabled(false);
        }
        
        if (alergiLainya.equals("ya")) {
            chkRiwAlergiLain.setSelected(true);
            TketRiwAlergiLain.setEnabled(true);
            TreakRiwAlergiLain.setEnabled(true);
        } else {
            chkRiwAlergiLain.setSelected(false);
            TketRiwAlergiLain.setEnabled(false);
            TreakRiwAlergiLain.setEnabled(false);
        }
        
        if (gelangTanda.equals("ya")) {
            chkGelang.setSelected(true);
        } else {
            chkGelang.setSelected(false);
        }
        
        if (alergiDiberitahukanDokter.equals("ya")) {
            chkDokter.setSelected(true);
        } else {
            chkDokter.setSelected(false);
        }
        
        if (alergiDiberitahukanFarmasis.equals("ya")) {
            chkFarmasis.setSelected(true);
        } else {
            chkFarmasis.setSelected(false);
        }
        
        if (alergiDiberitahukanAhligizi.equals("ya")) {
            chkAhliGz.setSelected(true);
        } else {
            chkAhliGz.setSelected(false);
        }
        
        if (ya.equals("ya")) {
            chkYaTerdapat.setSelected(true);
            chkPendengaran.setEnabled(true);
            chkPenglihatan.setEnabled(true);
            chkKognitif.setEnabled(true);
            chkFisik.setEnabled(true);
            chkBudaya.setEnabled(true);
            chkEmosi.setEnabled(true);
            chkBahasa.setEnabled(true);
            chkLainHambatan.setEnabled(true);
            TketLainHambatan.setEnabled(false);
        } else {
            chkYaTerdapat.setSelected(false);
            chkPendengaran.setEnabled(false);
            chkPenglihatan.setEnabled(false);
            chkKognitif.setEnabled(false);
            chkFisik.setEnabled(false);
            chkBudaya.setEnabled(false);
            chkEmosi.setEnabled(false);
            chkBahasa.setEnabled(false);
            chkLainHambatan.setEnabled(false);
            TketLainHambatan.setEnabled(false);
        }
        
        if (pendengaran.equals("ya")) {
            chkPendengaran.setSelected(true);
        } else {
            chkPendengaran.setSelected(false);
        }
        
        if (penglihatan.equals("ya")) {
            chkPenglihatan.setSelected(true);
        } else {
            chkPenglihatan.setSelected(false);
        }
        
        if (kognitif.equals("ya")) {
            chkKognitif.setSelected(true);
        } else {
            chkKognitif.setSelected(false);
        }
        
        if (fisik.equals("ya")) {
            chkFisik.setSelected(true);
        } else {
            chkFisik.setSelected(false);
        }
        
        if (budaya.equals("ya")) {
            chkBudaya.setSelected(true);
        } else {
            chkBudaya.setSelected(false);
        }
        
        if (emosi.equals("ya")) {
            chkEmosi.setSelected(true);
        } else {
            chkEmosi.setSelected(false);
        }
        
        if (bahasa.equals("ya")) {
            chkBahasa.setSelected(true);
        } else {
            chkBahasa.setSelected(false);
        }
        
        if (lainHambatan.equals("ya")) {
            chkLainHambatan.setSelected(true);
            TketLainHambatan.setEnabled(true);
        } else {
            chkLainHambatan.setSelected(false);
            TketLainHambatan.setEnabled(false);
        }
        
        if (cmbDibutuhkan.getSelectedIndex() == 1) {
            Tsebutkan.setEnabled(true);
        } else {
            Tsebutkan.setEnabled(false);
        }
        
        if (diagnosa.equals("ya")) {
            chkDiagnosa.setSelected(true);
        } else {
            chkDiagnosa.setSelected(false);
        }
        
        if (tindakanKeperawatan.equals("ya")) {
            chkTindakanKep.setSelected(true);
            TtindakanKep.setEnabled(true);
        } else {
            chkTindakanKep.setSelected(false);
            TtindakanKep.setEnabled(false);
        }
        
        if (lainKebutuhanEdukasi.equals("ya")) {
            chkLainKebutuhan.setSelected(true);
            TlainKebutuhan.setEnabled(true);
        } else {
            chkLainKebutuhan.setSelected(false);
            TlainKebutuhan.setEnabled(false);
        }
        
        if (obatObatan.equals("ya")) {
            chkObatTerapi.setSelected(true);
        } else {
            chkObatTerapi.setSelected(false);
        }
        
        if (rehabilitasi.equals("ya")) {
            chkRehabilitasi.setSelected(true);
        } else {
            chkRehabilitasi.setSelected(false);
        }
        
        if (diet.equals("ya")) {
            chkDietNutrisi.setSelected(true);
        } else {
            chkDietNutrisi.setSelected(false);
        }
        
        if (manajemenNyeri.equals("ya")) {
            chkManajemenNyeri.setSelected(true);
        } else {
            chkManajemenNyeri.setSelected(false);
        }
        
        if (pasien.equals("ya")) {
            chkPasien.setSelected(true);
        } else {
            chkPasien.setSelected(false);
        }
        
        if (keluargaPasien.equals("ya")) {
            chkKlgPasien.setSelected(true);
            TnmKlgPasien.setEnabled(true);
        } else {
            chkKlgPasien.setSelected(false);
            TnmKlgPasien.setEnabled(false);
        }
        
        if (tidakDapat.equals("ya")) {
            chkTidakDapat.setSelected(true);
            TtidakDapat.setEnabled(true);
        } else {
            chkTidakDapat.setSelected(false);
            TtidakDapat.setEnabled(false);
        }
        
        if (identifikasi1.equals("ya")) {
            chkIdentifikai1.setSelected(true);
        } else {
            chkIdentifikai1.setSelected(false);
        }
        
        if (identifikasi2.equals("ya")) {
            chkIdentifikai2.setSelected(true);
        } else {
            chkIdentifikai2.setSelected(false);
        }
        
        if (identifikasi3.equals("ya")) {
            chkIdentifikai3.setSelected(true);
        } else {
            chkIdentifikai3.setSelected(false);
        }
        
        if (identifikasi4.equals("ya")) {
            chkIdentifikai4.setSelected(true);
        } else {
            chkIdentifikai4.setSelected(false);
        }
        
        if (identifikasi5.equals("ya")) {
            chkIdentifikai5.setSelected(true);
        } else {
            chkIdentifikai5.setSelected(false);
        }
        
        if (identifikasi6.equals("ya")) {
            chkIdentifikai6.setSelected(true);
        } else {
            chkIdentifikai6.setSelected(false);
        }
        
        if (identifikasi7.equals("ya")) {
            chkIdentifikai7.setSelected(true);
        } else {
            chkIdentifikai7.setSelected(false);
        }
        
        if (identifikasi8.equals("ya")) {
            chkIdentifikai8.setSelected(true);
        } else {
            chkIdentifikai8.setSelected(false);
        }
        
        if (identifikasi9.equals("ya")) {
            chkIdentifikai9.setSelected(true);
        } else {
            chkIdentifikai9.setSelected(false);
        }
        
        if (identifikasi10.equals("ya")) {
            chkIdentifikai10.setSelected(true);
        } else {
            chkIdentifikai10.setSelected(false);
        }
        
        if (mpp.equals("Ya")) {
            chkMPP.setSelected(true);
        } else {
            chkMPP.setSelected(false);
        }
        
        if (dp.equals("Ya")) {
            chkDP.setSelected(true);
        } else {
            chkDP.setSelected(false);
        }
    }
    
    public void Tutup() {
        dispose();
    }
    
    private void variabelBersih() {        
        nipBidan1 = "";
        nipBidan2 = "";
        nipDokter = "";
        tidakAda = "";
        tidakDiketahui = "";
        alergiObat = "";
        alergiMakanan = "";
        alergiLainya = "";
        gelangTanda = "";
        alergiDiberitahukanDokter = "";
        alergiDiberitahukanFarmasis = "";
        alergiDiberitahukanAhligizi = "";
        ya = "";
        pendengaran = "";
        penglihatan = "";
        kognitif = "";
        fisik = "";
        budaya = "";
        emosi = "";
        bahasa = "";
        lainHambatan = "";
        diagnosa = "";
        tindakanKeperawatan = "";
        lainKebutuhanEdukasi = "";
        obatObatan = "";
        rehabilitasi = "";
        diet = "";
        manajemenNyeri = "";
        pasien = "";
        keluargaPasien = "";
        tidakDapat = "";
        identifikasi1 = "";
        identifikasi2 = "";
        identifikasi3 = "";
        identifikasi4 = "";
        identifikasi5 = "";
        identifikasi6 = "";
        identifikasi7 = "";
        identifikasi8 = "";
        identifikasi9 = "";
        identifikasi10 = "";
        noRawat = "";
        stsrwt = "";
        mpp = "";
        dp = "";
        idFileTtd = "";
        idParameterTtd = "";
        URL = "";
        usernya = "";
        pwdnya = "";
        ((RMAsesmenAwalKebidanan2.Painter) gambarQR).setImage("");
    }
    
    public void setData(String norwt, String stsRawat) {
        noRawat = norwt;
        if (stsRawat.equals("-")) {
            stsrwt = Sequel.cariIsi("select if(status_lanjut='Ralan','ralan','ranap') from reg_periksa where no_rawat='" + norwt + "'");
        } else {
            stsrwt = stsRawat;
        }
        isPasien();  
        tampil(norwt);
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from asesmen_awal_kebidanan2 where no_rawat=?", 1, new String[]{
                tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
            }) == true) {
                if (!idFileTtd.equals("")) {
                    Sequel.hapusSemuaTtd(idFileTtd);
                }
                
                TabRawat.setSelectedIndex(1);
                tampil(noRawat);
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        } else {
            TabRawat.setSelectedIndex(1);
            tampil(noRawat);
            emptTeks();
        }
    }
    
    private void ganti() {
        cekData();
        if (Sequel.mengedittf("asesmen_awal_kebidanan2", "no_rawat=?", "nyeri=?, lokasi_nyeri=?, jenis=?, skala_nyeri=?, provocation=?, ket_lain_provocation=?, "
                + "quality=?, ket_lain_quality=?, radiation=?, severity=?, time=?, time_lama=?, gizi_1=?, gizi_1ya=?, gizi_2=?, cek_tidak_ada=?, cek_tidak_diketahui=?, cek_alergi_obat=?, "
                + "ket_alergi_obat=?, ket_reaksi_alergi_obat=?, cek_alergi_makanan=?, ket_alergi_makanan=?, ket_reaksi_alergi_makanan=?, cek_alergi_lainya=?, ket_alergi_lainya=?, "
                + "ket_reaksi_alergi_lainya=?, cek_gelang_tanda=?, cek_alergi_diberitahukan_dokter=?, cek_alergi_diberitahukan_farmasis=?, cek_alergi_diberitahukan_ahliGizi=?, alat_bantu=?, "
                + "prothesis=?, cacat_tubuh=?, adl=?, riwayat_jatuh=?, nip_bidan=?, cek_ya=?, cek_pendengaran=?, cek_penglihatan=?, cek_kognitif=?, cek_fisik=?, cek_budaya=?, cek_emosi=?, "
                + "cek_bahasa=?, cek_lain_hambatan=?, ket_lain_hambatan=?, dibutuhkan_penerjemah=?, sebutkan=?, bahasa_isyarat=?, cek_diagnosa=?, cek_tindakan_keperawatan=?, "
                + "ket_tindakan_keperawatan=?, cek_lain_kebutuhan_edukasi=?, ket_lain_kebutuhan_edukasi=?, cek_obat_obatan=?, cek_rehabilitasi=?, cek_diet=?, cek_manajemen_nyeri=?, "
                + "cek_pasien=?, cek_keluarga_pasien=?, nama_keluarga_pasien=?, cek_tidak_dapat=?, ket_tidak_dapat=?, tgl_edukasi=?, jam_edukasi=?, nip_dokter=?, cek_identifikasi1=?, "
                + "cek_identifikasi2=?, cek_identifikasi3=?, cek_identifikasi4=?, cek_identifikasi5=?, cek_identifikasi6=?, cek_identifikasi7=?, cek_identifikasi8=?, cek_identifikasi9=?, "
                + "cek_identifikasi10=?, memerlukan=?, mpp=?, dp=?, tgl_dp=?, nm_keluarga_pasien=?, nip_bidan_dp=?, riw_jatuh_resiko_jatuh=?, kondisi_kesehatan=?, alat_bantu_resiko_jatuh=?, "
                + "terapi_IV=?, gaya_berjalan=?, status_mental=?, jumlah_skor=?, kesimpulan_resiko_jatuh=?", 91, new String[]{
                    cmbNyeri.getSelectedItem().toString(), Tlokasi.getText(), cmbJenis.getSelectedItem().toString(), cmbSkala.getSelectedItem().toString(),
                    cmbProvo.getSelectedItem().toString(), Tprovo.getText(), cmbQuality.getSelectedItem().toString(), Tquality.getText(), cmbRadia.getSelectedItem().toString(),
                    cmbSever.getSelectedItem().toString(), cmbTime.getSelectedItem().toString(), cmbLama.getSelectedItem().toString(), cmbGizi1.getSelectedItem().toString(),
                    cmbYaGizi1.getSelectedItem().toString(), cmbGizi2.getSelectedItem().toString(), tidakAda, tidakDiketahui, alergiObat, TketRiwAlergiObat.getText(),
                    TreakRiwAlergiObat.getText(), alergiMakanan, TketRiwAlergiMak.getText(), TreakRiwAlergiMak.getText(), alergiLainya, TketRiwAlergiLain.getText(),
                    TreakRiwAlergiLain.getText(), gelangTanda, alergiDiberitahukanDokter, alergiDiberitahukanFarmasis, alergiDiberitahukanAhligizi, TalatBantu.getText(),
                    Tprotesis.getText(), TcacatTubuh.getText(), cmbAdl.getSelectedItem().toString(), cmbRiwJatuh.getSelectedItem().toString(), nipBidan1, ya, pendengaran,
                    penglihatan, kognitif, fisik, budaya, emosi, bahasa, lainHambatan, TketLainHambatan.getText(), cmbDibutuhkan.getSelectedItem().toString(),
                    Tsebutkan.getText(), cmbBahasa.getSelectedItem().toString(), diagnosa, tindakanKeperawatan, TtindakanKep.getText(), lainKebutuhanEdukasi,
                    TlainKebutuhan.getText(), obatObatan, rehabilitasi, diet, manajemenNyeri, pasien, keluargaPasien, TnmKlgPasien.getText(), tidakDapat, TtidakDapat.getText(),
                    Valid.SetTgl(TtglEdukasi.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                    nipDokter, identifikasi1, identifikasi2, identifikasi3, identifikasi4, identifikasi5, identifikasi6, identifikasi7, identifikasi8, identifikasi9,
                    identifikasi10, Tmemerlukan.getText(), mpp, dp, Valid.SetTgl(TtglDp.getSelectedItem() + ""), TnmKeluargaPasien.getText(), nipBidan2, 
                    cmbResJatuh.getSelectedItem().toString(), cmbKondisi.getSelectedItem().toString(), cmbResAlatBantu.getSelectedItem().toString(), cmbTerapiIV.getSelectedItem().toString(), 
                    cmbGaya.getSelectedItem().toString(), cmbSttsMental.getSelectedItem().toString(), TJmlSkor.getText(), kesimpulanResJatuh.getText(), 
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                }) == true) {

            TabRawat.setSelectedIndex(1);
            tampil(noRawat);
            emptTeks();
        }
    }
    
    private void getData() {
        variabelBersih();
        if (tbAsesmen.getSelectedRow() != -1) {
            noRawat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString();
            cmbNyeri.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString());
            Tlokasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString());
            cmbJenis.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString());
            cmbSkala.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString());
            cmbProvo.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString());
            Tprovo.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString());
            cmbQuality.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 19).toString());
            Tquality.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString());
            cmbRadia.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString());
            cmbSever.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString());
            cmbTime.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString());
            cmbLama.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString());
            cmbGizi1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString());
            cmbYaGizi1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString());
            cmbGizi2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString());
            hitungSkorGizi();
            tidakAda = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString();
            tidakDiketahui = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString();
            alergiObat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString();
            TketRiwAlergiObat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString());
            TreakRiwAlergiObat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 32).toString());
            alergiMakanan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 33).toString();
            TketRiwAlergiMak.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString());
            TreakRiwAlergiMak.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString());
            alergiLainya = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString();
            TketRiwAlergiLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString());
            TreakRiwAlergiLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString());
            gelangTanda = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString();
            alergiDiberitahukanDokter = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString();
            alergiDiberitahukanFarmasis = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString();
            alergiDiberitahukanAhligizi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString();
            TalatBantu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString());
            Tprotesis.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString());
            TcacatTubuh.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString());
            cmbAdl.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString());
            cmbRiwJatuh.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString());
            nipBidan1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString();
            TnmBidan1.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipBidan1 + "'"));
            ya = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString();
            pendengaran = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString();
            penglihatan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString();
            kognitif = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString();
            fisik = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString();
            budaya = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString();
            emosi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString();
            bahasa = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString();
            lainHambatan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString();
            TketLainHambatan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString());
            cmbDibutuhkan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString());
            Tsebutkan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString());
            cmbBahasa.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString());
            diagnosa = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 62).toString();
            tindakanKeperawatan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString();
            TtindakanKep.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 64).toString());
            lainKebutuhanEdukasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString();
            TlainKebutuhan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 66).toString());
            obatObatan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString();
            rehabilitasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString();
            diet = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString();
            manajemenNyeri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString();            
            pasien = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString();
            keluargaPasien = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString();
            TnmKlgPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString());
            tidakDapat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString();
            TtidakDapat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString());
            Valid.SetTgl(TtglEdukasi, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString());
            cmbJam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString().substring(6, 8));
            nipDokter = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString();
            TnmDokter.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipDokter + "'"));            
            identifikasi1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 79).toString();
            identifikasi2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 80).toString();
            identifikasi3 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 81).toString();
            identifikasi4 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 82).toString();
            identifikasi5 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 83).toString();
            identifikasi6 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 84).toString();
            identifikasi7 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 85).toString();
            identifikasi8 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 86).toString();
            identifikasi9 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 87).toString();
            identifikasi10 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 88).toString();
            Tmemerlukan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 89).toString());
            mpp = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 90).toString();
            dp = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 91).toString();
            Valid.SetTgl(TtglDp, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 92).toString());
            TnmKeluargaPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 93).toString());
            nipBidan2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString();
            TnmBidan2.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipBidan2 + "'"));
            stsrwt = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 95).toString();            
            cmbResJatuh.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 97).toString());
            cmbKondisi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 98).toString());
            cmbResAlatBantu.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 99).toString());
            cmbTerapiIV.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 100).toString());
            cmbGaya.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString());
            cmbSttsMental.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 102).toString());
            idFileTtd = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 105).toString();
            hitungResikoJatuh();
            dataCek();
            isPasien();
            tampilTTD();
        }
    }
    
    private void isPasien() {
        try {
            ps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, concat(rp.umurdaftar,' ',rp.sttsumur) umurPx, "
                    + "ifnull(p.pekerjaan,'-') pekerjaanPx, p.agama, rp.tgl_registrasi, time_format(ak1.jam_asesmen,'%H:%i') jamAses ,ak1.* from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join asesmen_awal_kebidanan1 ak1 on ak1.no_rawat=rp.no_rawat where rp.no_rawat='" + noRawat + "'");
            try {                
                rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    Tpasien.setText(rs1.getString("no_rkm_medis") + " - " + rs1.getString("nm_pasien"));
                    TrgRawat.setText(rs1.getString("ruang_rawat") + ", Tgl. Masuk : " + Valid.SetTglINDONESIA(rs1.getString("tgl_asesmen")) + ", Pukul : " + rs1.getString("jamAses") + " Wita");
                    TumurPas.setText(rs1.getString("umurPx") + ", Pekerjaan : " + rs1.getString("pekerjaanPx") + ", Agama : " + rs1.getString("agama"));
                    TnmSuami.setText(rs1.getString("nm_suami") + ", Umur : " + rs1.getString("umur_suami") + " tahun, Pekerjaan : " + rs1.getString("pekerjaan_suami"));
                    TalasanMrs.setText(rs1.getString("alasan_masuk"));
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
    }
    
    private void tampilPreview() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            LoadHTML1.setText("");
            StringBuilder htmlContent = new StringBuilder();
            try {
                rsPrev = koneksi.prepareStatement("SELECT ak1.*, ak2.*, pg1.nama nmBidan, pg2.nama nmDokter, pg3.nama nmBidanDp, concat(p.nm_pasien,' (No. RM : ',p.no_rkm_medis,')') nm_pasien, "
                        + "concat(rp.umurdaftar,' ',rp.sttsumur,' (Tgl. Lahir : ',date_format(p.tgl_lahir,'%d-%m-%Y'),')') umurPasien, p.pekerjaan, p.agama, "
                        + "date_format(ak1.waktu_simpan,'%d/%m/%Y') tglak1, time(ak1.waktu_simpan) jamak1, if(ak2.no_rawat is not null,date_format(ak2.waktu_simpan,'%d/%m/%Y'),'-') tglak2, "
                        + "if(ak2.no_rawat is not null,time(ak2.waktu_simpan),'') jamak2, concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', Kab. ',kb.nm_kab) almtPasien, "
                        + "time_format(ak1.jam_asesmen,'%H:%i') jamAses FROM asesmen_awal_kebidanan1 ak1 inner join reg_periksa rp on rp.no_rawat=ak1.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join kelurahan kl on kl.kd_kel=p.kd_kel inner join kecamatan kc on kc.kd_kec=p.kd_kec "
                        + "inner join kabupaten kb on kb.kd_kab=p.kd_kab left join asesmen_awal_kebidanan2 ak2 on ak1.no_rawat=ak2.no_rawat left join pegawai pg1 on pg1.nik=ak2.nip_bidan "
                        + "left join pegawai pg2 on pg2.nik=ak2.nip_dokter left join pegawai pg3 on pg3.nik=ak2.nip_bidan_dp where ak1.no_rawat='" + noRawat + "'").executeQuery();
                if (rsPrev.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='79%'>"
                            + "<table width='100%' border='0' colspan='8' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + "<tr align='center'>"
                            + "<td valign='top' colspan='6' bgcolor='#f8fdf3'>Ruangan</td>"
                            + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Tanggal</td>"
                            + "<td valign='top' colspan='1' bgcolor='#f8fdf3'>Pukul</td>"
                            + "</tr>");
                    rsPrev.beforeFirst();
                    while (rsPrev.next()) {
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='6'>" + rsPrev.getString("ruang_rawat") + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + Valid.SetTglINDONESIA(rsPrev.getString("tgl_asesmen")) + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + rsPrev.getString("jamAses") + " Wita</td>"
                                + "</tr>");

                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' align='center' colspan='8' bgcolor='#f8fdf3'><span style='font-weight:bold'>IDENTITAS PASIEN</span></td>"
                                + "<tr align='center'>"
                                + "<td valign='top' colspan='4' align='left'><span style='font-weight:bold'>Pasien</span></td>"                                
                                + "<td valign='top' colspan='4' align='left'><span style='font-weight:bold'>Suami</span></td>"
                                + "</tr>");

                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Nama</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("nm_pasien") + "</td>"
                                + "<td valign='top'>Nama</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("nm_suami") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Umur</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("umurPasien") + "</td>"
                                + "<td valign='top'>Umur</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("umur_suami") + " tahun</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Pekerjaan</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("pekerjaan") + "</td>"
                                + "<td valign='top'>Pekerjaan</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("pekerjaan_suami") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Alamat</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("almtPasien") + "</td>"
                                + "<td valign='top'>Alamat</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("alamat_suami") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Agama</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("agama") + "</td>"
                                + "<td valign='top'>Agama</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("agama_suami") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><span style='font-weight:bold'>Alasan Masuk RS</span></td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("alasan_masuk") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='8' bgcolor='#f8fdf3'><span style='font-weight:bold'>Keadaan Umum</span></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='2' bgcolor='#f8fdf3' align='center'>Tekanan Darah</td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'>Nadi</td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'>Respirasi</td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'>Suhu</td>"
                                + "<td valign='top' colspan='2' bgcolor='#f8fdf3' align='center'>Kesadaran</td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'>Saturasi</td>"                                
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"                                
                                + "<td valign='top' colspan='2' align='center'>" + rsPrev.getString("td") + " mmHg</td>"
                                + "<td valign='top' colspan='1' align='center'>" + rsPrev.getString("nadi") + " x/menit</td>"
                                + "<td valign='top' colspan='1' align='center'>" + rsPrev.getString("respirasi") + " x/menit</td>"
                                + "<td valign='top' colspan='1' align='center'>" + rsPrev.getString("suhu") + " °C</td>"
                                + "<td valign='top' colspan='2' align='center'>" + rsPrev.getString("kesadaran") + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + rsPrev.getString("saturasi") + " %</td>"
                                + "</tr>");
                        
                        String prevSendiri = "", prevRujukan = "", prevPKM = "", prevSpog = "", prevRsLain = "";
                        if (rsPrev.getString("cek_sendiri").equals("ya")) {
                            prevSendiri = "Sendiri, ";
                        } else {
                            prevSendiri = "";
                        }

                        if (rsPrev.getString("cek_rujukan").equals("ya")) {
                            if (rsPrev.getString("ket_jns_rujukan").equals("")) {
                                prevRujukan = "Rujukan " + rsPrev.getString("jns_rujukan") + ", ";
                            } else {
                                prevRujukan = "Rujukan " + rsPrev.getString("jns_rujukan") + " : " + rsPrev.getString("ket_jns_rujukan") + ", ";
                            }
                        } else {
                            prevRujukan = "";
                        }
                        
                        if (rsPrev.getString("cek_pkm").equals("ya")) {
                            if (rsPrev.getString("ket_pkm").equals("")) {
                                prevPKM = "PKM, ";
                            } else {
                                prevPKM = "PKM : " + rsPrev.getString("ket_pkm") + ", ";
                            }
                        } else {
                            prevPKM = "";
                        }
                        
                        if (rsPrev.getString("cek_spog").equals("ya")) {
                            prevSpog = "SPGO, ";
                        } else {
                            prevSpog = "";
                        }
                        
                        if (rsPrev.getString("cek_rs_lain").equals("ya")) {
                            if (rsPrev.getString("ket_rs_lain").equals("")) {
                                prevRsLain = "RS Lain";
                            } else {
                                prevRsLain = "RS Lain : " + rsPrev.getString("ket_rs_lain");
                            }
                        } else {
                            prevRsLain = "";
                        }
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><span style='font-weight:bold'>Cara Pasien Datang</span></td>"
                                + "<td valign='top' colspan='7'>: " + prevSendiri + prevRujukan + prevPKM + prevSpog + prevRsLain + "</td>"
                                + "</tr>");
                        
                        String prevGr = "", prevPr = "", prevA = "", prevHamil = "", prevGpapah = "";
                        if (rsPrev.getString("gr").equals("")) {
                            prevGr = "Gr ........, ";
                        } else {
                            prevGr = "Gr " + rsPrev.getString("gr") + ", ";
                        }
                        
                        if (rsPrev.getString("pr").equals("")) {
                            prevPr = "Pr ........, ";
                        } else {
                            prevPr = "Pr " + rsPrev.getString("pr") + ", ";
                        }
                        
                        if (rsPrev.getString("a").equals("")) {
                            prevA = "A ........, ";
                        } else {
                            prevA = "A " + rsPrev.getString("a") + ", ";
                        }
                        
                        if (rsPrev.getString("hamil").equals("")) {
                            prevHamil = "Hamil : ........ Minggu, ";
                        } else {
                            prevHamil = "Hamil : " + rsPrev.getString("hamil") + " Minggu, ";
                        }
                        
                        if (rsPrev.getString("gpapah").equals("")) {
                            prevGpapah = "G.PAPAH : ........";
                        } else {
                            prevGpapah = "G.PAPAH : " + rsPrev.getString("gpapah");
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"                                
                                + "<td valign='top' colspan='8'>" + prevGr + prevPr + prevA + prevHamil + prevGpapah + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Dengan</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("dengan") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='8' bgcolor='#f8fdf3'><span style='font-weight:bold'>Keluhan</span></td>"
                                + "</tr>");
                        
                        String prevPerut = "", nilaiPerut = "", nilaiNyeriUlu = "", prevKeluar = "", nilaiKeluar = "", nilaiPandang = "", prevDarah = "", nilaiDarah = "",
                                jnsDrh = "", nilaiOdema = "", odemaDi = "", nilaiKeluarAir = "", klrAir = "", jnsKlrAir = "", nilaiMual = "", nilaiMuntah = "", nilaiPerge = "",
                                nilaiPusing = "", ketPeriksaBidan = "", nilaiIbu = "", prevAnc = "", ketDokter1 = "", ketDokter2 = "", nilaiBatuk = "", nilaiPilek = "", nilaiDemam = "",
                                ketDokter3 = "", dr1 = "", dr2 = "", dr3 = "";
                        if (rsPrev.getString("perut").equals("Ya")) {
                            if (rsPrev.getString("keluhan_perut").equals("-")) {
                                prevPerut = "Perut Mulai";
                            } else {
                                prevPerut = "Perut " + rsPrev.getString("keluhan_perut") + " Mulai";
                            }
                            nilaiPerut = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_perut")) + ", Jam : " + rsPrev.getString("jam_perut").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("perut").equals("Tidak")) {
                            prevPerut = "Perut Mules/Nyeri Mulai";
                            nilaiPerut = ": Tidak";
                        } else {
                            prevPerut = "Perut Mules/Nyeri Mulai";
                            nilaiPerut = ": -";
                        }
                        
                        if (rsPrev.getString("nyeri_ulu_hati").equals("Ya")) {
                            nilaiNyeriUlu = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_nyeri_ulu_hati")) + ", Jam : " + rsPrev.getString("jam_nyeri_ulu_hati").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("nyeri_ulu_hati").equals("Tidak")) {
                            nilaiNyeriUlu = ": Tidak";
                        } else {
                            nilaiNyeriUlu = ": -";
                        }
                        
                        if (rsPrev.getString("keluar").equals("Ya")) {
                            if (rsPrev.getString("keluhan_keluar").equals("-")) {
                                prevKeluar = "Keluar ?";
                            } else {
                                prevKeluar = "Keluar " + rsPrev.getString("keluhan_keluar");
                            }
                            nilaiKeluar = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_keluar_lendir")) + ", Jam : " + rsPrev.getString("jam_keluar_lendir").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("keluar").equals("Tidak")) {
                            prevKeluar = "Keluar lendir darah/darah";
                            nilaiKeluar = ": Tidak";
                        } else {
                            prevKeluar = "Keluar lendir darah/darah";
                            nilaiKeluar = ": -";
                        }
                        
                        if (rsPrev.getString("pandangan_kabur").equals("Ya")) {
                            nilaiPandang = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_pandangan_kabur")) + ", Jam : " + rsPrev.getString("jam_pandangan_kabur").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("pandangan_kabur").equals("Tidak")) {
                            nilaiPandang = ": Tidak";
                        } else {
                            nilaiPandang = ": -";
                        }
                        
                        if (rsPrev.getString("darah").equals("Ya")) {
                            if (rsPrev.getString("keluhan_darah").equals("-")) {
                                prevDarah = "Darah ?";
                            } else {
                                prevDarah = "Darah " + rsPrev.getString("keluhan_darah");
                            }
                            
                            if (rsPrev.getString("jns_darah").equals("-")) {
                                jnsDrh = ": Ya, ? mulai tgl. ";
                            } else {
                                jnsDrh = ": Ya, " + rsPrev.getString("jns_darah") + " mulai tgl. ";
                            }
                            nilaiDarah = jnsDrh + Valid.SetTglINDONESIA(rsPrev.getString("tgl_darah")) + ", Jam : " + rsPrev.getString("jam_darah").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("darah").equals("Tidak")) {
                            prevDarah = "Darah encer/segar/bergumpal";
                            nilaiDarah = ": Tidak";
                        } else {
                            prevDarah = "Darah encer/segar/bergumpal";
                            nilaiDarah = ": -";
                        }
                        
                        if (rsPrev.getString("odema").equals("Ya")) {
                            if (rsPrev.getString("odema_di").equals("-")) {
                                odemaDi = ", ?";
                            } else {
                                odemaDi = ", di " + rsPrev.getString("odema_di");
                            }
                            nilaiOdema = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_odema")) + odemaDi;
                        } else if (rsPrev.getString("odema").equals("Tidak")) {
                            nilaiOdema = ": Tidak";
                        } else {
                            nilaiOdema = ": -";
                        }
                        
                        if (rsPrev.getString("keluar_air").equals("Ya")) {
                            if (rsPrev.getString("jns_keluar_air").equals("-")) {
                                jnsKlrAir = ": Ya, ? mulai tgl. ";
                            } else {
                                jnsKlrAir = ": Ya, " + rsPrev.getString("jns_keluar_air") + " mulai tgl. ";
                            }
                            
                            if (rsPrev.getString("keluhan_keluar_air").equals("-")) {
                                klrAir = ", ?";
                            } else {
                                klrAir = ", " + rsPrev.getString("keluhan_keluar_air");
                            }
                            nilaiKeluarAir = jnsKlrAir + Valid.SetTglINDONESIA(rsPrev.getString("tgl_keluar_air")) + ", Jam : " + rsPrev.getString("jam_keluar_air").substring(0, 5) + " Wita" + klrAir;
                        } else if (rsPrev.getString("keluar_air").equals("Tidak")) {                            
                            nilaiKeluarAir = ": Tidak";
                        } else {                            
                            nilaiKeluarAir = ": -";
                        }
                        
                        if (rsPrev.getString("mual").equals("Ya")) {
                            nilaiMual = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_mual")) + ", Jam : " + rsPrev.getString("jam_mual").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("mual").equals("Tidak")) {
                            nilaiMual = ": Tidak";
                        } else {
                            nilaiMual = ": -";
                        }
                        
                        if (rsPrev.getString("ket_pergerakan_janin_2jam_terakhir").equals("")) {
                            nilaiPerge = ": -";
                        } else {
                            nilaiPerge = ": " + rsPrev.getString("ket_pergerakan_janin_2jam_terakhir");
                        }
                        
                        if (rsPrev.getString("muntah").equals("Ya")) {
                            nilaiMuntah = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_muntah")) + ", Jam : " + rsPrev.getString("jam_muntah").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("muntah").equals("Tidak")) {
                            nilaiMuntah = ": Tidak";
                        } else {
                            nilaiMuntah = ": -";
                        }
                        
                        if (rsPrev.getString("cek_batuk").equals("ya")) {
                            nilaiBatuk = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_batuk")) + ", Jam : " + rsPrev.getString("jam_batuk").substring(0, 5) + " Wita";
                        } else {
                            nilaiBatuk = ": Tidak";
                        }
                        
                        if (rsPrev.getString("cek_pilek").equals("ya")) {
                            nilaiPilek = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_pilek")) + ", Jam : " + rsPrev.getString("jam_pilek").substring(0, 5) + " Wita";
                        } else {
                            nilaiPilek = ": Tidak";
                        }
                        
                        if (rsPrev.getString("cek_demam").equals("ya")) {
                            nilaiDemam = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_demam")) + ", Jam : " + rsPrev.getString("jam_demam").substring(0, 5) + " Wita";
                        } else {
                            nilaiDemam = ": Tidak";
                        }
                        
                        if (rsPrev.getString("pusing").equals("Ya")) {
                            nilaiPusing = ": Ya, mulai tgl. " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_pusing")) + ", Jam : " + rsPrev.getString("jam_pusing").substring(0, 5) + " Wita";
                        } else if (rsPrev.getString("pusing").equals("Tidak")) {
                            nilaiPusing = ": Tidak";
                        } else {
                            nilaiPusing = ": -";
                        }
                        
                        if (rsPrev.getString("hasil_pemeriksaan_bidan").equals("")) {
                            ketPeriksaBidan = ": -";
                        } else {
                            ketPeriksaBidan = ": " + rsPrev.getString("hasil_pemeriksaan_bidan");
                        }

                        if (rsPrev.getString("ibu_anc").equals("Ya")) {
                            if (rsPrev.getString("jns_anc").equals("-")) {
                                prevAnc = "Ibu ANC di ?";
                            } else {
                                prevAnc = "Ibu ANC di " + rsPrev.getString("jns_anc");
                            }

                            if (rsPrev.getString("jlh_anc").equals("")) {
                                nilaiIbu = ": Ya, -";
                            } else {
                                nilaiIbu = ": Ya, " + rsPrev.getString("jlh_anc") + " X";
                            }
                            
                            if (rsPrev.getString("dengan_dokter1").equals("")) {
                                dr1 = "";
                            } else {
                                dr1 = ", Dengan : " + rsPrev.getString("dengan_dokter1");
                            }
                            
                            if (rsPrev.getString("jlh_dengan_dokter1").equals("")) {
                                ketDokter1 = "";
                            } else {
                                ketDokter1 = ", " + rsPrev.getString("jlh_dengan_dokter1") + " X";
                            }
                            
                            if (rsPrev.getString("dengan_dokter2").equals("")) {
                                dr2 = "";
                            } else {
                                dr2 = ", Dengan : " + rsPrev.getString("dengan_dokter2");
                            }
                            
                            if (rsPrev.getString("jlh_dengan_dokter2").equals("")) {
                                ketDokter2 = "";
                            } else {
                                ketDokter2 = ", " + rsPrev.getString("jlh_dengan_dokter2") + " X";
                            }
                            
                            if (rsPrev.getString("dengan_dokter3").equals("")) {
                                dr3 = "";
                            } else {
                                dr3 = ", Dengan : " + rsPrev.getString("dengan_dokter3");
                            }
                            
                            if (rsPrev.getString("jlh_dengan_dokter3").equals("")) {
                                ketDokter3 = "";
                            } else {
                                ketDokter3 = ", " + rsPrev.getString("jlh_dengan_dokter3") + " X";
                            }
                        } else if (rsPrev.getString("ibu_anc").equals("Tidak")) {
                            prevAnc = "Ibu ANC di PKM/Bidan";
                            nilaiIbu = ": Tidak";
                        } else {
                            prevAnc = "Ibu ANC di PKM/Bidan";
                            nilaiIbu = ": -";
                        }
                        
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>" + prevPerut + "</td>"
                                + "<td valign='top' colspan='3'>" + nilaiPerut + "</td>"
                                + "<td valign='top'>Nyeri Ulu Hati</td>"
                                + "<td valign='top' colspan='3'>" + nilaiNyeriUlu + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>" + prevKeluar + "</td>"
                                + "<td valign='top' colspan='3'>" + nilaiKeluar + "</td>"
                                + "<td valign='top'>Pandangan Kabur</td>"
                                + "<td valign='top' colspan='3'>" + nilaiPandang + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>" + prevDarah + "</td>"
                                + "<td valign='top' colspan='3'>" + nilaiDarah + "</td>"
                                + "<td valign='top'>Odema</td>"
                                + "<td valign='top' colspan='3'>" + nilaiOdema + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Keluar air-air</td>"
                                + "<td valign='top' colspan='3'>" + nilaiKeluarAir + "</td>"
                                + "<td valign='top'>Mual</td>"
                                + "<td valign='top' colspan='3'>" + nilaiMual + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Pergerakan Janin</td>"
                                + "<td valign='top' colspan='3'>" + nilaiPerge + "</td>"
                                + "<td valign='top'>Muntah</td>"
                                + "<td valign='top' colspan='3'>" + nilaiMuntah + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Pusing</td>"
                                + "<td valign='top' colspan='3'>" + nilaiPusing + "</td>"
                                + "<td valign='top'>Batuk</td>"
                                + "<td valign='top' colspan='3'>" + nilaiBatuk + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>Keluhan Lainya</td>"
                                + "<td valign='top' colspan='3'>: " + rsPrev.getString("keluhan_lainya") + "</td>"
                                + "<td valign='top'>Pilek</td>"
                                + "<td valign='top' colspan='3'>" + nilaiPilek + ", Demam " + nilaiDemam + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='2'>Periksa Ketempat Bidan/Dokter/RS Lain</td>"
                                + "<td valign='top' colspan='6'>: " + rsPrev.getString("periksa_ketempat_bidan") + ", Hasil / Riwayat Pemeriksaan " + ketPeriksaBidan + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top'>" + prevAnc + "</td>"
                                + "<td valign='top' colspan='7'>" + nilaiIbu + dr1 + ketDokter1 + dr2 + ketDokter2 + dr3 + ketDokter3 + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>RIWAYAT KEHAMILAN SEKARANG</span></td>"
                                + "</tr>");
                        
                        String prevHpht = "", prevHpl = "", prevUk = "", prevBbBelum = "", prevBbTerakhir = "", prevTbi = "", prevBmiHamil = "";
                        if (rsPrev.getString("hpht").equals("")) {
                            prevHpht = "HPHT : ........, ";
                        } else {
                            prevHpht = "HPHT : " + rsPrev.getString("hpht") + ", ";
                        }
                        
                        if (rsPrev.getString("hpl").equals("")) {
                            prevHpl = "HPL : ........, ";
                        } else {
                            prevHpl = "HPL : " + rsPrev.getString("hpl") + ", ";
                        }
                        
                        if (rsPrev.getString("uk").equals("")) {
                            prevUk = "UK : ........, ";
                        } else {
                            prevUk = "UK : " + rsPrev.getString("uk") + " mg, ";
                        }
                        
                        if (rsPrev.getString("bb_sebelum_hamil").equals("")) {
                            prevBbBelum = "BB Sebelum Hamil : ........ Kg, ";
                        } else {
                            prevBbBelum = "BB Sebelum Hamil : " + rsPrev.getString("bb_sebelum_hamil") + " Kg, ";
                        }

                        if (rsPrev.getString("bb_terakhir").equals("")) {
                            prevBbTerakhir = "BB Terakhir : ........ Kg, ";
                        } else {
                            prevBbTerakhir = "BB Terakhir : " + rsPrev.getString("bb_terakhir") + " Kg, ";
                        }

                        if (rsPrev.getString("tbi").equals("")) {
                            prevTbi = "TBI : ........ Cm, ";
                        } else {
                            prevTbi = "TBI : " + rsPrev.getString("tbi") + " Cm, ";
                        }
                        
                        //hitung nilai BMI
                        Valid.hitungBMIbbTerakhir(rsPrev.getString("bb_terakhir"), rsPrev.getString("tbi"));
                        prevBmiHamil = "BMI Hamil : " + akses.getPasteData() + " kg/m².      Grade : " + akses.getPasteData1();
                        
                        htmlContent.append(
                                "<tr class='isi'>"                                
                                + "<td valign='top' colspan='8'>" + prevHpht + prevHpl + prevUk + prevBbBelum + prevBbTerakhir + prevTbi + prevBmiHamil + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='8' bgcolor='#f8fdf3'><span style='font-weight:bold'>Riwayat Haid</span></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'>Umur Pertama Kali Haid</td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'>Lamanya Haid</td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'>Berapa Kali Ganti Pembalut</td>"
                                + "<td valign='top' colspan='5' bgcolor='#f8fdf3' align='left'>Keluhan Waktu Haid</td>"
                                + "</tr>");
                        
                        String prevUmurPertama = "", prevLamaHaid = "", prevBerapa = "", prevKelWaktuHaid = "", prevDismen = "", prevSpot = "", prevMenor = "", prevMetro = "", 
                                prevLainRiwHaid = "", ketLainRiwHaid = "";
                        if (rsPrev.getString("umur_pertama_haid").equals("")) {
                            prevUmurPertama = "-";
                        } else {
                            prevUmurPertama = rsPrev.getString("umur_pertama_haid") + " tahun";
                        }
                        
                        if (rsPrev.getString("lama_haid").equals("")) {
                            prevLamaHaid = "-";
                        } else {
                            prevLamaHaid = rsPrev.getString("lama_haid") + " hari";
                        }
                        
                        if (rsPrev.getString("berapa_kali_ganti_pembalut").equals("")) {
                            prevBerapa = "-";
                        } else {
                            prevBerapa = rsPrev.getString("berapa_kali_ganti_pembalut") + " x/hari";
                        }
                        
                        if (rsPrev.getString("keluhan_waktu_haid").equals("Ada")) {
                            if (rsPrev.getString("cek_dismen").equals("ya")) {
                                prevDismen = "Dismenorhoe, ";
                            } else {
                                prevDismen = "";
                            }
                            
                            if (rsPrev.getString("cek_spoting").equals("ya")) {
                                prevSpot = "Spotting, ";
                            } else {
                                prevSpot = "";
                            }
                            
                            if (rsPrev.getString("cek_menor").equals("ya")) {
                                prevMenor = "Menorhagia, ";
                            } else {
                                prevMenor = "";
                            }
                            
                            if (rsPrev.getString("cek_metro").equals("ya")) {
                                prevMetro = "Metrorhagia, ";
                            } else {
                                prevMetro = "";
                            }
                            
                            if (rsPrev.getString("cek_lain_keluhan_haid").equals("ya")) {                                
                                if (rsPrev.getString("ket_lain_keluhan_haid").equals("")) {
                                    ketLainRiwHaid = "?";
                                } else {
                                    ketLainRiwHaid = rsPrev.getString("ket_lain_keluhan_haid");
                                }
                                prevLainRiwHaid = "Lainnya : " + ketLainRiwHaid;
                            } else {
                                prevLainRiwHaid = "";
                            }
                            prevKelWaktuHaid = "Ada, " + prevDismen + prevSpot + prevMenor + prevMetro + prevLainRiwHaid;
                        } else {
                            prevKelWaktuHaid = "Tidak Ada";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' align='center'>" + prevUmurPertama + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + prevLamaHaid + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + prevBerapa + "</td>"
                                + "<td valign='top' colspan='5' align='left'>" + prevKelWaktuHaid + "</td>"
                                + "</tr>");
                        
                        String prevRiwPenDahulu = "", prevHiperRPD = "", prevDmRPD = "", prevJantungRPD = "", prevAsmaRPD = "", prevRiwLainRPD = "", ketRiwLainRPD = "";
                        if (rsPrev.getString("riw_penyakit_dahulu").equals("Ada")) {
                            if (rsPrev.getString("cek_hipertensi_dahulu").equals("ya")) {
                                prevHiperRPD = "Hipertensi, ";
                            } else {
                                prevHiperRPD = "";
                            }
                            
                            if (rsPrev.getString("cek_dm_dahulu").equals("ya")) {
                                prevDmRPD = "DM, ";
                            } else {
                                prevDmRPD = "";
                            }
                            
                            if (rsPrev.getString("cek_jantung_dahulu").equals("ya")) {
                                prevJantungRPD = "Jantung, ";
                            } else {
                                prevJantungRPD = "";
                            }
                            
                            if (rsPrev.getString("cek_asma_dahulu").equals("ya")) {
                                prevAsmaRPD = "Asma, ";
                            } else {
                                prevAsmaRPD = "";
                            }
                            
                            if (rsPrev.getString("cek_lainya_dahulu").equals("ya")) {                                
                                if (rsPrev.getString("ket_lain_penyakit_dahulu").equals("")) {
                                    ketRiwLainRPD = "?";
                                } else {
                                    ketRiwLainRPD = rsPrev.getString("ket_lain_penyakit_dahulu");
                                }
                                prevRiwLainRPD = "Lainnya : " + ketRiwLainRPD;
                            } else {
                                prevRiwLainRPD = "";
                            }
                            prevRiwPenDahulu = "Ada, " + prevHiperRPD + prevDmRPD + prevJantungRPD + prevAsmaRPD + prevRiwLainRPD;
                        } else {
                            prevRiwPenDahulu = "Tidak Ada";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><b>Riwayat Penyakit Dahulu</b></td>"
                                + "<td valign='top' colspan='7'>: " + prevRiwPenDahulu + "</td>"
                                + "</tr>");
                        
                        String prevRiwPenKlg = "", prevHiperKlg = "", prevDmKlg = "", prevJantungKlg = "", prevAsmaKlg = "", prevRiwLainKlg = "", ketRiwLainKlg = "";
                        if (rsPrev.getString("riw_penyakit_keluarga").equals("Ada")) {
                            if (rsPrev.getString("cek_hipertensi_keluarga").equals("ya")) {
                                prevHiperKlg = "Hipertensi, ";
                            } else {
                                prevHiperKlg = "";
                            }
                            
                            if (rsPrev.getString("cek_dm_keluarga").equals("ya")) {
                                prevDmKlg = "DM, ";
                            } else {
                                prevDmKlg = "";
                            }
                            
                            if (rsPrev.getString("cek_jantung_keluarga").equals("ya")) {
                                prevJantungKlg = "Jantung, ";
                            } else {
                                prevJantungKlg = "";
                            }
                            
                            if (rsPrev.getString("cek_asma_keluarga").equals("ya")) {
                                prevAsmaKlg = "Asma, ";
                            } else {
                                prevAsmaKlg = "";
                            }
                            
                            if (rsPrev.getString("cek_lainya_keluarga").equals("ya")) {
                                if (rsPrev.getString("ket_lain_penyakit_keluarga").equals("")) {
                                    ketRiwLainKlg = "?";
                                } else {
                                    ketRiwLainKlg = rsPrev.getString("ket_lain_penyakit_keluarga");
                                }
                                prevRiwLainKlg = "Lainnya : " + ketRiwLainKlg;
                            } else {
                                prevRiwLainKlg = "";
                            }
                            prevRiwPenKlg = "Ada, " + prevHiperKlg + prevDmKlg + prevJantungKlg + prevAsmaKlg + prevRiwLainKlg;
                        } else {
                            prevRiwPenKlg = "Tidak Ada";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><b>Riwayat Penyakit Keluarga</b></td>"
                                + "<td valign='top' colspan='7'>: " + prevRiwPenKlg + "</td>"
                                + "</tr>");
                                                
                        String prevRiwGinekologi = "", ketRiwGine = "";
                        if (rsPrev.getString("riw_ginekologi").equals("Ada")) {
                            if (rsPrev.getString("ket_ginekologi").equals("")) {
                                ketRiwGine = " .......";
                            } else {
                                ketRiwGine = ", " + rsPrev.getString("ket_ginekologi");
                            }

                            prevRiwGinekologi = "Ada" + ketRiwGine;
                        } else {
                            prevRiwGinekologi = "Tidak Ada";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><b>Riwayat Ginekologi</b></td>"
                                + "<td valign='top' colspan='7'>: " + prevRiwGinekologi + "</td>"
                                + "</tr>");
                        
                        String prevPil = "", prevSuntik1 = "", prevSuntik3 = "", prevImplan = "", prevIud = "", prevTdkKb = "", lamaPil = "", lamaSuntik1 = "", lamaSuntik3 = "",
                                lamaImplan = "", lamaIud = "", satPil = "", satSuntik1 = "", satSuntik3 = "", satImplan = "", satIud = "";
                        
                        if (rsPrev.getString("cek_pil").equals("ya")) {
                            if (rsPrev.getString("lama_pil").equals("")) {
                                lamaPil = ".....";
                            } else {
                                if (rsPrev.getString("satuan_lama_pil").equals("-")) {
                                    satPil = ", ";
                                } else {
                                    satPil = rsPrev.getString("satuan_lama_pil");
                                }
                                lamaPil = rsPrev.getString("lama_pil") + " " + satPil + ", ";
                            }                            
                            prevPil = "Pil, lama : " + lamaPil;
                        } else {
                            prevPil = "";
                        }
                        
                        if (rsPrev.getString("cek_suntik1").equals("ya")) {
                            if (rsPrev.getString("lama_suntik1").equals("")) {
                                lamaSuntik1 = ".....";
                            } else {
                                if (rsPrev.getString("satuan_lama_suntik1").equals("-")) {
                                    satSuntik1 = ", ";
                                } else {
                                    satSuntik1 = rsPrev.getString("satuan_lama_suntik1");
                                }
                                lamaSuntik1 = rsPrev.getString("lama_suntik1") + " " + satSuntik1 + ", ";
                            }                            
                            prevSuntik1 = "Suntik 1 bulan, lama : " + lamaSuntik1;
                        } else {
                            prevSuntik1 = "";
                        }
                        
                        if (rsPrev.getString("cek_suntik3").equals("ya")) {
                            if (rsPrev.getString("lama_suntik3").equals("")) {
                                lamaSuntik3 = ".....";
                            } else {
                                if (rsPrev.getString("satuan_lama_suntik3").equals("-")) {
                                    satSuntik3 = ", ";
                                } else {
                                    satSuntik3 = rsPrev.getString("satuan_lama_suntik3");
                                }
                                lamaSuntik3 = rsPrev.getString("lama_suntik3") + " " + satSuntik3 + ", ";
                            }                            
                            prevSuntik3 = "Suntik 3 bulan, lama : " + lamaSuntik3;
                        } else {
                            prevSuntik3 = "";
                        }
                        
                        if (rsPrev.getString("cek_implan").equals("ya")) {
                            if (rsPrev.getString("lama_implan").equals("")) {
                                lamaImplan = ".....";
                            } else {
                                if (rsPrev.getString("satuan_lama_implan").equals("-")) {
                                    satImplan = ", ";
                                } else {
                                    satImplan = rsPrev.getString("satuan_lama_implan");
                                }
                                lamaImplan = rsPrev.getString("lama_implan") + " " + satImplan + ", ";
                            }                            
                            prevImplan = "Implan, lama : " + lamaImplan;
                        } else {
                            prevImplan = "";
                        }
                        
                        if (rsPrev.getString("cek_iud").equals("ya")) {
                            if (rsPrev.getString("lama_iud").equals("")) {
                                lamaIud = ".....";
                            } else {
                                if (rsPrev.getString("satuan_lama_iud").equals("-")) {
                                    satIud = ", ";
                                } else {
                                    satIud = rsPrev.getString("satuan_lama_iud");
                                }
                                lamaIud = rsPrev.getString("lama_iud") + " " + satIud + ", ";
                            }                            
                            prevIud = "IUD, lama : " + lamaIud;
                        } else {
                            prevIud = "";
                        }
                        
                        if (rsPrev.getString("cek_tidak_kb").equals("ya")) {
                            prevTdkKb = "Tidak pernah KB";
                        } else {
                            prevTdkKb = "";
                        }                        
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><b>Riwayat KB</b></td>"
                                + "<td valign='top' colspan='7'>: " + prevPil + prevSuntik1 + prevSuntik3 + prevImplan + prevIud + prevTdkKb + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>RIWAYAT KEHAMILAN, PERSALINAN DAN NIFAS YANG LALU</span></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Tahun Partus</td>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Tempat Partus</td>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Umur Hamil</td>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Jenis Persalinan</td>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Penolong Persalinan</td>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Penyulit</td>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Jenis Kelamin/Berat Lahir</td>"
                                + "<td valign='top' bgcolor='#f8fdf3' align='center'>Keadaan Anak Sekarang</td>"
                                + "</tr>");

                        try {
                            ps3 = koneksi.prepareStatement("SELECT COALESCE(no_rawat, '-') AS no_rawat, "
                                    + "COALESCE(tahun_partus, '-') AS tahun_partus, "
                                    + "COALESCE(tempat_partus, '-') AS tempat_partus, "
                                    + "COALESCE(umur_hamil, '-') AS umur_hamil, "
                                    + "COALESCE(jns_persalinan, '-') AS jns_persalinan, "
                                    + "COALESCE(penolong_persalinan, '-') AS penolong_persalinan, "
                                    + "COALESCE(penyulit, '-') AS penyulit, "
                                    + "COALESCE(jk, '-') AS jk, "
                                    + "COALESCE(bb, '-') AS bb, "
                                    + "COALESCE(keadaan_anak_skrng, '-') AS keadaan_anak_skrng, "
                                    + "COALESCE(waktu_simpan, '-') AS waktu_simpan "
                                    + "FROM (SELECT * FROM riwayat_kehamilan_asesmen_awal_kebidanan WHERE no_rawat = '" + rsPrev.getString("no_rawat") + "' "
                                    + "UNION ALL "
                                    + "SELECT NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL from dual "
                                    + "WHERE NOT EXISTS (SELECT 1 FROM riwayat_kehamilan_asesmen_awal_kebidanan WHERE "
                                    + "no_rawat = '" + rsPrev.getString("no_rawat") + "')) AS x ORDER BY x.waktu_simpan IS NULL, x.waktu_simpan");
                            try {
                                rs3 = ps3.executeQuery();
                                while (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' align='center'>" + rs3.getString("tahun_partus") + "</td>"
                                            + "<td valign='top' align='left'>" + rs3.getString("tempat_partus") + "</td>"
                                            + "<td valign='top' align='left'>" + rs3.getString("umur_hamil") + "</td>"
                                            + "<td valign='top' align='left'>" + rs3.getString("jns_persalinan") + "</td>"
                                            + "<td valign='top' align='left'>" + rs3.getString("penolong_persalinan") + "</td>"
                                            + "<td valign='top' align='left'>" + rs3.getString("penyulit") + "</td>"
                                            + "<td valign='top' align='left'>" + rs3.getString("jk") + " (" + rs3.getString("bb") + ")</td>"
                                            + "<td valign='top' align='left'>" + rs3.getString("keadaan_anak_skrng") + "</td>"
                                            + "</tr>");
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
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>RIWAYAT PSIKOSOSIAL DAN SPIRITUAL</span></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Status Perkawinan</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("status_perkawinan") + "</td>"
                                + "</tr>");
                        
                        String prevIstri = "", prevSuami = "";
                        if (rsPrev.getString("cek_istri_kawin").equals("ya")) {
                            prevIstri = "Istri (" + rsPrev.getString("jlh_perkawinan_istri") + "), ";
                        } else {
                            prevIstri = "-";
                        }
                        
                        if (rsPrev.getString("cek_suami_kawin").equals("ya")) {
                            prevSuami = "Suami (" + rsPrev.getString("jlh_perkawinan_suami") + ")";
                        } else {
                            prevSuami = "-";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Jumlah Perkawinan</td>"
                                + "<td valign='top' colspan='7'>: " + prevIstri + prevSuami + "</td>"
                                + "</tr>");
                        
                        String prevUsiaPertamaNikah = "", prevUsiaPertamaNikahBln = "", prevPertamaNkh = "", 
                                prevUsiaPerkawinan = "", prevUsiaPerkawinanBln = "", prevPerkawinan = "";
                        if (rsPrev.getString("usia_pertama_nikah").equals("")) {
                            prevUsiaPertamaNikah = "...... tahun, ";
                        } else {
                            prevUsiaPertamaNikah = rsPrev.getString("usia_pertama_nikah") + " tahun, ";
                        }
                        
                        if (rsPrev.getString("usia_pertama_nikah_bln").equals("")) {
                            prevUsiaPertamaNikahBln = "...... bulan&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Usia Perkawinan : ";
                        } else {
                            prevUsiaPertamaNikahBln = rsPrev.getString("usia_pertama_nikah_bln") + " bulan&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Usia Perkawinan : ";
                        }
                        
                        prevPertamaNkh = prevUsiaPertamaNikah + prevUsiaPertamaNikahBln;
                        
                        if (rsPrev.getString("usia_perkawinan").equals("")) {
                            prevUsiaPerkawinan = "...... tahun, ";
                        } else {
                            prevUsiaPerkawinan = rsPrev.getString("usia_perkawinan") + " tahun, ";
                        }
                        
                        if (rsPrev.getString("usia_perkawinan_bln").equals("")) {
                            prevUsiaPerkawinanBln = "...... bulan";
                        } else {
                            prevUsiaPerkawinanBln = rsPrev.getString("usia_perkawinan_bln") + " bulan";
                        }
                        
                        prevPerkawinan = prevUsiaPerkawinan + prevUsiaPerkawinanBln;
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Usia Pertama Kali Nikah</td>"
                                + "<td valign='top' colspan='7'>: " + prevPertamaNkh + prevPerkawinan + "</td>"
                                + "</tr>");
                        
                        String prevKlgDekat = "", prevHub = "", prevOrtu = "", prevTglSuami = "", prevTglAnak = "", prevTglSendiri = "";
                        if (rsPrev.getString("keluarga_terdekat").equals("")) {
                            prevKlgDekat = "......, Hubungan ";
                        } else {
                            prevKlgDekat = rsPrev.getString("keluarga_terdekat") + ", Hubungan ";
                        }
                        
                        if (rsPrev.getString("hubungan").equals("")) {
                            prevHub = "......, Tinggal Dengan : ";
                        } else {
                            prevHub = rsPrev.getString("hubungan") + ", Tinggal Dengan : ";
                        }
                        
                        if (rsPrev.getString("cek_orang_tua").equals("ya")) {
                            prevOrtu = "Orang Tua, ";
                        } else {
                            prevOrtu = "";
                        }
                        
                        if (rsPrev.getString("cek_suami").equals("ya")) {
                            prevTglSuami = "Suami, ";
                        } else {
                            prevTglSuami = "";
                        }
                        
                        if (rsPrev.getString("cek_anak").equals("ya")) {
                            prevTglAnak = "Anak, ";
                        } else {
                            prevTglAnak = "";
                        }
                        
                        if (rsPrev.getString("cek_tinggal_sendiri").equals("ya")) {
                            prevTglSendiri = "Sendiri";
                        } else {
                            prevTglSendiri = "";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Keluarga Terdekat</td>"
                                + "<td valign='top' colspan='7'>: " + prevKlgDekat + prevHub + prevOrtu + prevTglSuami + prevTglAnak + prevTglSendiri + ", Curiga Penganiayaan / Penelantaran : " + rsPrev.getString("curiga_penganiayaan") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Kegiatan Ibadah</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("kegiatan_ibadah") + ", Status Emosional : " + rsPrev.getString("status_emosional") + "</td>"
                                + "</tr>");
                        
                        String prevAsuransi = "", prevJaminan = "", prevBySendiri = "", prevLainSttsEko = "";
                        if (rsPrev.getString("cek_asuransi").equals("ya")) {
                            prevAsuransi = "Asuransi, ";
                        } else {
                            prevAsuransi = "";
                        }
                        
                        if (rsPrev.getString("cek_jaminan").equals("ya")) {
                            prevJaminan = "Jaminan, ";
                        } else {
                            prevJaminan = "";
                        }
                        
                        if (rsPrev.getString("cek_biaya_sendiri").equals("ya")) {
                            prevBySendiri = "Biaya Sendiri, ";
                        } else {
                            prevBySendiri = "";
                        }
                        
                        if (rsPrev.getString("cek_lain_status_ekonomi").equals("ya")) {
                            if (rsPrev.getString("ket_lain_status_ekonomi").equals("")) {
                                prevLainSttsEko = "Lainnya : .......";
                            } else {
                                prevLainSttsEko = "Lainnya : " + rsPrev.getString("ket_lain_status_ekonomi");
                            }                            
                        } else {
                            prevLainSttsEko = "";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Status Ekonomi</td>"
                                + "<td valign='top' colspan='7'>: " + prevAsuransi + prevJaminan + prevBySendiri + prevLainSttsEko + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"                                
                                + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>Pemeriksaan OBSTETRI</span></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Leopold 1</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("leopold1") + "</td>"                                
                                + "</tr>");

                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Leopold 2</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("leopold2") + "</td>"                                
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Leopold 3</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("leopold3") + "</td>"                                
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Leopold 4</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("leopold4") + "</td>"                                
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>Bandle Ring</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("bandle_ring") + "</td>"                                
                                + "</tr>");

                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='8'>Perut Tegang Terus Menerus Seperti Papan : " + rsPrev.getString("perut_tegang") + "</td>"                                
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='2'>Pemeriksaan Dalam (Obstetri)</td>"
                                + "<td valign='top' colspan='6'>: " + rsPrev.getString("periksa_dalam_obstetri") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>TFU</span></td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>Taksiran Berat Janin</span></td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>His/Kontraksi</span></td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>Durasi</span></td>"
                                + "<td valign='top' colspan='1' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>Auskultasi</span></td>"
                                + "<td valign='top' colspan='3' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>Pemeriksaan Genitalia</span></td>"
                                + "</tr>");
                        
                        String prevTfu = "", prevTaksiran = "", prevHis = "", prevJnsHis = "", prevDurasi = "", prevJnsDurasi = "", prevAus = "", prevGeni = "",
                                prevBrsh = "", prevOdema = "", prevRuftur = "", prevCandi = "", prevLainGeni = "", prevInspekulo = "";
                        if (rsPrev.getString("tfu").equals("")) {
                            prevTfu = "-";
                        } else {
                            prevTfu = rsPrev.getString("tfu") + " Cm.";
                        }
                        
                        if (rsPrev.getString("taksiran_berat_janin").equals("")) {
                            prevTaksiran = "-";
                        } else {
                            prevTaksiran = rsPrev.getString("taksiran_berat_janin") + " gram";
                        }
                        
                        if (rsPrev.getString("jns_his_kontraksi").equals("-")) {
                            prevJnsHis = "";
                        } else {
                            prevJnsHis = " (" + rsPrev.getString("jns_his_kontraksi") + ")";
                        }
                        
                        if (rsPrev.getString("his_kontraksi").equals("")) {
                            prevHis = "-" + prevJnsHis;
                        } else {
                            prevHis = rsPrev.getString("his_kontraksi") + " x/10 menit" + prevJnsHis;
                        }
                        
                        if (rsPrev.getString("jns_durasi").equals("-")) {
                            prevJnsDurasi = "";
                        } else {
                            prevJnsDurasi = " (" + rsPrev.getString("jns_durasi") + ")";
                        }
                        
                        if (rsPrev.getString("durasi").equals("")) {
                            prevDurasi = "-" + prevJnsDurasi;
                        } else {
                            prevDurasi = rsPrev.getString("durasi") + " detik" + prevJnsDurasi;
                        }
                        
                        if (rsPrev.getString("auskultasi").equals("")) {
                            prevAus = "-";
                        } else {
                            prevAus = "DJJ " + rsPrev.getString("auskultasi") + " x/mnt";
                        }
                        
                        if (rsPrev.getString("cek_bersih").equals("ya")) {
                            prevBrsh = "Bersih, ";
                        } else {
                            prevBrsh = "";
                        }
                        
                        if (rsPrev.getString("cek_oedema").equals("ya")) {
                            prevOdema = "Oedema, ";
                        } else {
                            prevOdema = "";
                        }
                        
                        if (rsPrev.getString("cek_ruftur").equals("ya")) {
                            prevRuftur = "Ruftur, ";
                        } else {
                            prevRuftur = "";
                        }
                        
                        if (rsPrev.getString("cek_candiloma").equals("ya")) {
                            prevCandi = "Candiloma, ";
                        } else {
                            prevCandi = "";
                        }
                        
                        if (rsPrev.getString("cek_lain_pemeriksaan_geni").equals("ya")) {
                            if (rsPrev.getString("ket_lain_pemeriksaan_geni").equals("")) {
                                prevLainGeni = "Lainnya : ......";
                            } else {
                                prevLainGeni = "Lainnya : " + rsPrev.getString("ket_lain_pemeriksaan_geni");
                            }                            
                        } else {
                            prevLainGeni = "";
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' align='center'>" + prevTfu + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + prevTaksiran + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + prevHis + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + prevDurasi + "</td>"
                                + "<td valign='top' colspan='1' align='center'>" + prevAus + "</td>"
                                + "<td valign='top' colspan='3' align='left'>" + prevBrsh + prevOdema + prevRuftur + prevCandi + prevLainGeni + "</td>"
                                + "</tr>");
                        
                        if (rsPrev.getString("inspekulo").equals("-")) {
                            prevInspekulo = "-";
                        } else {
                            if (rsPrev.getString("hasil_inspekulo").equals("")) {
                                prevInspekulo = rsPrev.getString("inspekulo");
                            } else {
                                prevInspekulo = rsPrev.getString("inspekulo") + ", Hasil : " + rsPrev.getString("hasil_inspekulo");
                            }
                        }
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><b>Inspekulo</b></td>"
                                + "<td valign='top' colspan='7'>: " + prevInspekulo + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"                                
                                + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>Pemeriksaan GINEKOLOGI</span></td>"
                                + "</tr>");
                        
                        //inspeksi pemeriksaan ginekologi
                        if (Sequel.cariInteger("select count(-1) from inspeksi_ginekologi_awal_kebidanan where no_rawat='" + noRawat + "'") > 0) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' align='left'><b>Inspeksi Pemeriksaan Ginekologi (Bagian Yang Diperiksa) :</b></td>"
                                    + "</tr>");

                            try {
                                ps4 = koneksi.prepareStatement("select * from inspeksi_ginekologi_awal_kebidanan where no_rawat='" + noRawat + "'");
                                try {
                                    rs4 = ps4.executeQuery();
                                    while (rs4.next()) {
                                        String vulvaNormal = "", vulvaHiperemis = "", vulvaEdema = "", vulvaAdaLesi = "", vulvaMasa = "", labiaSimetris = "", labiaTdkSimetris = "",
                                                labiaAdaLuka = "", labiaAdaBenjolan = "", klitorisNormal = "", klitorisHipertrofi = "", klitorisLainya = "", temuanTambahan = "",
                                                perineumUtuh = "", perineumAdaLuka = "", perineumBekas = "", perineumFistula = "", introitusNormal = "", introitusAdaSekret = "",
                                                introitusAdaPerdarahan = "", introitusAdaRobekan = "", sekretTdkAda = "", sekretJernih = "", sekretPutih = "", sekretKuning = "",
                                                sekretBerbau = "", dindingNormal = "", dindingHiperemis = "", dindingAtrofi = "", dindingAdaMasa = "", dindingAdaSekret = "",
                                                serviksBentukNormal = "", serviksBentukErosi = "", serviksBentukPolip = "", serviksBentukEktropion = "", serviksWarnaNormal = "",
                                                serviksWarnaHiperemis = "", serviksWarnaPucat = "", serviksPermuHalus = "", serviksPermuGranulasi = "", serviksPermuUlserasi = "";

                                        if (rs4.getString("vulva_normal").equals("ya")) {
                                            vulvaNormal = "Normal, ";
                                        } else {
                                            vulvaNormal = "";
                                        }

                                        if (rs4.getString("vulva_hiperemis").equals("ya")) {
                                            vulvaHiperemis = "Hiperemis, ";
                                        } else {
                                            vulvaHiperemis = "";
                                        }

                                        if (rs4.getString("vulva_edema").equals("ya")) {
                                            vulvaEdema = "Edema, ";
                                        } else {
                                            vulvaEdema = "";
                                        }

                                        if (rs4.getString("vulva_ada_lesi").equals("ya")) {
                                            vulvaAdaLesi = "Ada lesi/ulkus, ";
                                        } else {
                                            vulvaAdaLesi = "";
                                        }

                                        if (rs4.getString("vulva_masa").equals("ya")) {
                                            vulvaMasa = "Massa";
                                        } else {
                                            vulvaMasa = "";
                                        }

                                        if (rs4.getString("sekret_tdk_ada").equals("ya")) {
                                            sekretTdkAda = "Tidak Ada, ";
                                        } else {
                                            sekretTdkAda = "";
                                        }

                                        if (rs4.getString("sekret_jernih").equals("ya")) {
                                            sekretJernih = "Jernih, ";
                                        } else {
                                            sekretJernih = "";
                                        }

                                        if (rs4.getString("sekret_putih").equals("ya")) {
                                            sekretPutih = "Putih Kental, ";
                                        } else {
                                            sekretPutih = "";
                                        }

                                        if (rs4.getString("sekret_kuning").equals("ya")) {
                                            sekretKuning = "Kuning Kehijauan, ";
                                        } else {
                                            sekretKuning = "";
                                        }

                                        if (rs4.getString("sekret_berbau").equals("ya")) {
                                            sekretBerbau = "Berbau, ";
                                        } else {
                                            sekretBerbau = "";
                                        }

                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' colspan='1'>Vulva</td>"
                                                + "<td valign='top' colspan='3'>: " + vulvaNormal + vulvaHiperemis + vulvaEdema + vulvaAdaLesi + vulvaMasa + "</td>"
                                                + "<td valign='top' colspan='1'>Sekret Vaginal</td>"
                                                + "<td valign='top' colspan='3'>: " + sekretTdkAda + sekretJernih + sekretPutih + sekretKuning + sekretBerbau + " Jumlah : " + rs4.getString("jumlah_sekret") + "</td>"
                                                + "</tr>");

                                        if (rs4.getString("labia_simetris").equals("ya")) {
                                            labiaSimetris = "Simetris, ";
                                        } else {
                                            labiaSimetris = "";
                                        }

                                        if (rs4.getString("labia_tdk_simetris").equals("ya")) {
                                            labiaTdkSimetris = "Tidak Simetris, ";
                                        } else {
                                            labiaTdkSimetris = "";
                                        }

                                        if (rs4.getString("labia_ada_luka").equals("ya")) {
                                            labiaAdaLuka = "Ada Luka, ";
                                        } else {
                                            labiaAdaLuka = "";
                                        }

                                        if (rs4.getString("labia_ada_benjolan").equals("ya")) {
                                            labiaAdaBenjolan = "Ada Benjolan";
                                        } else {
                                            labiaAdaBenjolan = "";
                                        }

                                        if (rs4.getString("dinding_normal").equals("ya")) {
                                            dindingNormal = "Normal, ";
                                        } else {
                                            dindingNormal = "";
                                        }

                                        if (rs4.getString("dinding_hiperemis").equals("ya")) {
                                            dindingHiperemis = "Hiperemis, ";
                                        } else {
                                            dindingHiperemis = "";
                                        }

                                        if (rs4.getString("dinding_atrofi").equals("ya")) {
                                            dindingAtrofi = "Atrofi, ";
                                        } else {
                                            dindingAtrofi = "";
                                        }

                                        if (rs4.getString("dinding_ada_masa").equals("ya")) {
                                            dindingAdaMasa = "Ada Massa, ";
                                        } else {
                                            dindingAdaMasa = "";
                                        }

                                        if (rs4.getString("dinding_ada_sekret").equals("ya")) {
                                            dindingAdaSekret = "Ada Sekret";
                                        } else {
                                            dindingAdaSekret = "";
                                        }

                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' colspan='1'>Labia Majora/Minora</td>"
                                                + "<td valign='top' colspan='3'>: " + labiaSimetris + labiaTdkSimetris + labiaAdaLuka + labiaAdaBenjolan + "</td>"
                                                + "<td valign='top' colspan='1'>Dinding Vagina</td>"
                                                + "<td valign='top' colspan='3'>: " + dindingNormal + dindingHiperemis + dindingAtrofi + dindingAdaMasa + dindingAdaSekret + "</td>"
                                                + "</tr>");

                                        if (rs4.getString("klitoris_normal").equals("ya")) {
                                            klitorisNormal = "Normal, ";
                                        } else {
                                            klitorisNormal = "";
                                        }

                                        if (rs4.getString("klitoris_hipertrofi").equals("ya")) {
                                            klitorisHipertrofi = "Hipertrofi, ";
                                        } else {
                                            klitorisHipertrofi = "";
                                        }

                                        if (rs4.getString("klitoris_lainya").equals("ya")) {
                                            if (!rs4.getString("ket_klitoris_lain").equals("")) {
                                                klitorisLainya = "Lainnya : " + rs4.getString("ket_klitoris_lain");
                                            } else {
                                                klitorisLainya = "Lainnya : ...........";
                                            }
                                        } else {
                                            klitorisLainya = "";
                                        }

                                        if (rs4.getString("serviks_bentuk_normal").equals("ya")) {
                                            serviksBentukNormal = "Normal, ";
                                        } else {
                                            serviksBentukNormal = "";
                                        }

                                        if (rs4.getString("serviks_bentuk_erosi").equals("ya")) {
                                            serviksBentukErosi = "Erosi, ";
                                        } else {
                                            serviksBentukErosi = "";
                                        }

                                        if (rs4.getString("serviks_bentuk_polip").equals("ya")) {
                                            serviksBentukPolip = "Polip, ";
                                        } else {
                                            serviksBentukPolip = "";
                                        }

                                        if (rs4.getString("serviks_bentuk_ektropion").equals("ya")) {
                                            serviksBentukEktropion = "Ektropion";
                                        } else {
                                            serviksBentukEktropion = "";
                                        }

                                        if (rs4.getString("serviks_warna_normal").equals("ya")) {
                                            serviksWarnaNormal = "Normal, ";
                                        } else {
                                            serviksWarnaNormal = "";
                                        }

                                        if (rs4.getString("serviks_warna_hiperemis").equals("ya")) {
                                            serviksWarnaHiperemis = "Hiperemis, ";
                                        } else {
                                            serviksWarnaHiperemis = "";
                                        }

                                        if (rs4.getString("serviks_warna_pucat").equals("ya")) {
                                            serviksWarnaPucat = "Pucat";
                                        } else {
                                            serviksWarnaPucat = "";
                                        }

                                        if (rs4.getString("serviks_permu_halus").equals("ya")) {
                                            serviksPermuHalus = "Halus, ";
                                        } else {
                                            serviksPermuHalus = "";
                                        }

                                        if (rs4.getString("serviks_permu_granulasi").equals("ya")) {
                                            serviksPermuGranulasi = "Granulasi, ";
                                        } else {
                                            serviksPermuGranulasi = "";
                                        }

                                        if (rs4.getString("serviks_permu_ulserasi").equals("ya")) {
                                            serviksPermuUlserasi = "Ulserasi";
                                        } else {
                                            serviksPermuUlserasi = "";
                                        }

                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' colspan='1'>Klitoris</td>"
                                                + "<td valign='top' colspan='3'>: " + klitorisNormal + klitorisHipertrofi + klitorisLainya + "</td>"
                                                + "<td valign='top' colspan='1'>Serviks Uteri</td>"
                                                + "<td valign='top' colspan='3'>: - Bentuk : " + serviksBentukNormal + serviksBentukErosi + serviksBentukPolip + serviksBentukEktropion
                                                + "<br>&nbsp;&nbsp;- Warna : " + serviksWarnaNormal + serviksWarnaHiperemis + serviksWarnaPucat + ""
                                                + "<br>&nbsp;&nbsp;- Permukaan : " + serviksPermuHalus + serviksPermuGranulasi + serviksPermuUlserasi + "</td>"
                                                + "</tr>");

                                        if (rs4.getString("perineum_utuh").equals("ya")) {
                                            perineumUtuh = "Utuh, ";
                                        } else {
                                            perineumUtuh = "";
                                        }

                                        if (rs4.getString("perineum_ada_luka").equals("ya")) {
                                            perineumAdaLuka = "Ada Luka, ";
                                        } else {
                                            perineumAdaLuka = "";
                                        }

                                        if (rs4.getString("perineum_bekas").equals("ya")) {
                                            perineumBekas = "Bekas Episiotomi, ";
                                        } else {
                                            perineumBekas = "";
                                        }

                                        if (rs4.getString("perineum_fistula").equals("ya")) {
                                            perineumFistula = "Fistula";
                                        } else {
                                            perineumFistula = "";
                                        }

                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' colspan='1'>Perineum</td>"
                                                + "<td valign='top' colspan='3'>: " + perineumUtuh + perineumAdaLuka + perineumBekas + perineumFistula + "</td>"
                                                + "<td valign='top' colspan='1'>Sekret Serviks</td>"
                                                + "<td valign='top' colspan='3'>: " + rs4.getString("sekret_serviks") + " : " + rs4.getString("sekret_serviks_ada") + "</td>"
                                                + "</tr>");

                                        if (rs4.getString("introitus_normal").equals("ya")) {
                                            introitusNormal = "Normal, ";
                                        } else {
                                            introitusNormal = "";
                                        }

                                        if (rs4.getString("introitus_ada_sekret").equals("ya")) {
                                            introitusAdaSekret = "Ada Sekret, ";
                                        } else {
                                            introitusAdaSekret = "";
                                        }

                                        if (rs4.getString("introitus_ada_perdarahan").equals("ya")) {
                                            introitusAdaPerdarahan = "Ada Perdarahan, ";
                                        } else {
                                            introitusAdaPerdarahan = "";
                                        }

                                        if (rs4.getString("introitus_ada_robekan").equals("ya")) {
                                            introitusAdaRobekan = "Ada Robekan";
                                        } else {
                                            introitusAdaRobekan = "";
                                        }

                                        if (rs4.getString("temuan_tambahan").equals("")) {
                                            temuanTambahan = rs4.getString("temuan_tambahan");
                                        } else {
                                            temuanTambahan = "-";
                                        }

                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' colspan='1'>Introitus Vagina</td>"
                                                + "<td valign='top' colspan='3'>: " + introitusNormal + introitusAdaSekret + introitusAdaPerdarahan + introitusAdaRobekan + "</td>"
                                                + "<td valign='top' colspan='1'>Temuan Tambahan</td>"
                                                + "<td valign='top' colspan='3'>: " + temuanTambahan + "</td>"
                                                + "</tr>");
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
                        
                        htmlContent.append(
                                "<tr class='isi'>"                                
                                + "<td valign='top' colspan='1'>Palpasi</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("palpasi") + "</td>"
                                + "</tr>");
                        
                        String prevSebesar = "";
                        if (rsPrev.getString("sebesar").equals("")) {
                            prevSebesar = ", Sebesar ........";
                        } else {
                            prevSebesar = ", Sebesar " + rsPrev.getString("sebesar");
                        }

                        htmlContent.append(
                                "<tr class='isi'>"                                
                                + "<td valign='top' colspan='1'>Teraba Massa</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("teraba_massa") + prevSebesar + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"                                
                                + "<td valign='top' colspan='1'>Goyang</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("goyang") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"                                
                                + "<td valign='top' colspan='1'>Nyeri Tekan</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("nyeri_tekan") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"                                
                                + "<td valign='top' colspan='1'>VT Pembukaan</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("vt_pembukaan") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'>VT Nyeri Goyang</td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("vt_nyeri_goyang") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='2'><b>DIAGNOSIS SEMENTARA</b></td>"
                                + "<td valign='top' colspan='4'>: " + rsPrev.getString("diagnosis_sementara") + "</td>"
                                + "<td valign='top' colspan='1'><b>ICD-10</b></td>"
                                + "<td valign='top' colspan='1'>: " + rsPrev.getString("icd_10") + "</td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1'><b>PLANNING</b></td>"
                                + "<td valign='top' colspan='7'>: " + rsPrev.getString("planing") + "</td>"
                                + "</tr>");

                        //ini mulai halaman 2
                        if (Sequel.cariInteger("select count(-1) from asesmen_awal_kebidanan2 where no_rawat='" + rsPrev.getString("no_rawat") + "'") > 0) {
                            String prevNyeri = "", prevProvo = "", prevQuality = "", prevGambar = "", ipGambar = "";
                            try {
                                //cek atau ping ip addres
                                ipGambar = "192.168.0.230";
                                InetAddress inet = InetAddress.getByName(ipGambar);

                                    //ping sukses timeout 100 ms (0.1 detik)
                                if (inet.isReachable(100)) {
                                    prevGambar = "http://192.168.0.230:7183/img-rme/skala_nyeri.png";
                                    //ping gagal
                                } else {
                                    prevGambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/skala_nyeri.png";
                                }
                            } catch (Exception e) {
                                System.out.println("Notif : " + e);
                            }

                            if (rsPrev.getString("nyeri").equals("Ya")) {
                                if (rsPrev.getString("lokasi_nyeri").equals("")) {
                                    prevNyeri = "Ya, Lokasi : -";
                                } else {
                                    prevNyeri = "Ya, Lokasi : " + rsPrev.getString("lokasi_nyeri");
                                }
                            } else {
                                prevNyeri = rsPrev.getString("nyeri");
                            }

                            if (rsPrev.getString("provocation").equals("Lainnya")) {
                                if (rsPrev.getString("ket_lain_provocation").equals("")) {
                                    prevProvo = "Lainnya : -";
                                } else {
                                    prevProvo = "Lainnya : " + rsPrev.getString("ket_lain_provocation");
                                }
                            } else {
                                prevProvo = rsPrev.getString("provocation");
                            }

                            if (rsPrev.getString("quality").equals("Lainnya")) {
                                if (rsPrev.getString("ket_lain_quality").equals("")) {
                                    prevQuality = "Lainnya : -";
                                } else {
                                    prevQuality = "Lainnya : " + rsPrev.getString("ket_lain_quality");
                                }
                            } else {
                                prevQuality = rsPrev.getString("quality");
                            }

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>ASSESMEN NYERI</span></td>"
                                    + "</tr>");

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1'>Nyeri</td>"
                                    + "<td valign='top' colspan='5'>: " + prevNyeri + "</td>"
                                    + "<td valign='top' colspan='1'>Jenis : " + rsPrev.getString("jenis") + "</td>"
                                    + "<td valign='top' colspan='1'>Skala : " + rsPrev.getString("skala_nyeri") + "</td>"
                                    + "</tr>");

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='middle' colspan='1'>Provocation</td>"
                                    + "<td valign='middle' colspan='4'>: Faktor yang memperburuk rasa nyeri " + prevProvo + "</td>"
                                    + "<td valign='middle' colspan='3' rowspan='5'><img src='" + prevGambar + "' width='500' alt='Skala Nyeri'></td>"
                                    + "</tr>");

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='middle' colspan='1'>Quality</td>"
                                    + "<td valign='middle' colspan='4'>: Rasa nyeri seperti " + prevQuality + "</td>"
                                    + "</tr>");

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='middle' colspan='1'>Radiation</td>"
                                    + "<td valign='middle' colspan='4'>: Nyeri menjalar ke bagian tubuh yang lain " + rsPrev.getString("radiation") + "</td>"
                                    + "</tr>");

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='middle' colspan='1'>Severity</td>"
                                    + "<td valign='middle' colspan='4'>: Tingkat keparahan nyeri " + rsPrev.getString("severity") + "</td>"
                                    + "</tr>");

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='middle' colspan='1'>Time</td>"
                                    + "<td valign='middle' colspan='4'>: Nyeri berlangsung " + rsPrev.getString("time") + ", Lama : " + rsPrev.getString("time_lama").replaceAll("<", "&lt;").replaceAll(">", "&gt;") + "</td>"
                                    + "</tr>");

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='4' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>SKRINING GIZI AWAL</span></td>"
                                    + "<td valign='top' colspan='4' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>RIWAYAT ALERGI</span></td>"
                                    + "</tr>");
                            
                            String prevRiwAlerAda = "", prevRiwAlerTdk = "";
                            if (rsPrev.getString("cek_tidak_ada").equals("ya")) {
                                prevRiwAlerAda = "Tidak Ada, ";
                            } else {
                                prevRiwAlerAda = "";
                            }
                            
                            if (rsPrev.getString("cek_tidak_diketahui").equals("ya")) {
                                prevRiwAlerTdk = "Tidak Diketahui";
                            } else {
                                prevRiwAlerTdk = "";
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='4' align='left'>1. Apakah pasien mengalami penurunan BB yang tidak direncanakan/tidak diinginkan dalam 6 bulan terakhir ?</td>"
                                    + "<td valign='top' colspan='4' align='left'>" + prevRiwAlerAda + prevRiwAlerTdk + "</td>"
                                    + "</tr>");
                            
                            String prevLihatSkorGz1 = "", prevSkorGz1 = "", prevLihaSkorYaGz1 = "", prevSkorYaGz1 = "", prevAlergiObat = "", prevKetAlerObat = "", prevReakAlerObat = "",
                                    prevAlergiMak = "", prevKetAlerMak = "", prevReakAlerMak = "", prevAlergiLai = "", prevKetAlerLai = "", prevReakAlerLai = "", prevSkorGz2 = "",
                                    prevGelang = "", prevKesimSkriningGZ = "", prevTotSkorGZ = "", prevDiberitauDr = "", prevDiberitauFar = "", prevDiberitauAGz = "";
                            if (rsPrev.getString("gizi_1").equals("Tidak") || rsPrev.getString("gizi_1").equals("Ya ada penurunan BB sebanyak :")) {
                                prevSkorGz1 = "0";
                            } else if (rsPrev.getString("gizi_1").equals("Tidak Yakin (ada tanda : baju menjadi longgar)")) {
                                prevSkorGz1 = "2";
                            }
                            
                            if (rsPrev.getString("gizi_1").equals("Ya ada penurunan BB sebanyak :")) {
                                prevLihatSkorGz1 = "";
                            } else {
                                prevLihatSkorGz1 = ": Skor (" + prevSkorGz1 + ")";
                            }
                            
                            if (rsPrev.getString("gizi_1ya").equals("-")) {
                                prevSkorYaGz1 = "0";
                            } else if (rsPrev.getString("gizi_1ya").equals("1 - 5 Kg")) {
                                prevSkorYaGz1 = "1";
                            } else if (rsPrev.getString("gizi_1ya").equals("6 - 10 Kg")) {
                                prevSkorYaGz1 = "2";
                            } else if (rsPrev.getString("gizi_1ya").equals("11 - 15 Kg")) {
                                prevSkorYaGz1 = "3";
                            } else if (rsPrev.getString("gizi_1ya").equals("> 15 Kg")) {
                                prevSkorYaGz1 = "4";
                            } else if (rsPrev.getString("gizi_1ya").equals("Tidak tahu berapa Kg penurunanya")) {
                                prevSkorYaGz1 = "2";
                            }
                            
                            if (rsPrev.getString("gizi_1ya").equals("-")) {
                                prevLihaSkorYaGz1 = "-";
                            } else {
                                prevLihaSkorYaGz1 = ": Skor (" + prevSkorYaGz1 + ")";
                            }
                            
                            if (rsPrev.getString("gizi_2").equals("Tidak")) {
                                prevSkorGz2 = "0";
                            } else {
                                prevSkorGz2 = "1";
                            }
                            
                            if (rsPrev.getString("cek_alergi_obat").equals("ya")) {
                                if (rsPrev.getString("ket_alergi_obat").equals("")) {
                                    prevKetAlerObat = "";
                                } else {
                                    prevKetAlerObat = " : " + rsPrev.getString("ket_alergi_obat");
                                }

                                if (rsPrev.getString("ket_reaksi_alergi_obat").equals("")) {
                                    prevReakAlerObat = "";
                                } else {
                                    prevReakAlerObat = ", Reaksi : " + rsPrev.getString("ket_reaksi_alergi_obat");
                                }
                                prevAlergiObat = "Alergi Obat" + prevKetAlerObat + prevReakAlerObat;
                            } else {
                                prevAlergiObat = "Alergi Obat ................";
                            }
                            
                            if (rsPrev.getString("cek_alergi_makanan").equals("ya")) {
                                if (rsPrev.getString("ket_alergi_makanan").equals("")) {
                                    prevKetAlerMak = "";
                                } else {
                                    prevKetAlerMak = " : " + rsPrev.getString("ket_alergi_makanan");
                                }

                                if (rsPrev.getString("ket_reaksi_alergi_makanan").equals("")) {
                                    prevReakAlerMak = "";
                                } else {
                                    prevReakAlerMak = ", Reaksi : " + rsPrev.getString("ket_reaksi_alergi_makanan");
                                }
                                prevAlergiMak = "Alergi Makanan" + prevKetAlerMak + prevReakAlerMak;
                            } else {
                                prevAlergiMak = "Alergi Makanan ................";
                            }
                            
                            if (rsPrev.getString("cek_alergi_lainya").equals("ya")) {
                                if (rsPrev.getString("ket_alergi_lainya").equals("")) {
                                    prevKetAlerLai = "";
                                } else {
                                    prevKetAlerLai = " : " + rsPrev.getString("ket_alergi_lainya");
                                }

                                if (rsPrev.getString("ket_reaksi_alergi_lainya").equals("")) {
                                    prevReakAlerLai = "";
                                } else {
                                    prevReakAlerLai = ", Reaksi : " + rsPrev.getString("ket_reaksi_alergi_lainya");
                                }
                                prevAlergiLai = "Alergi Lainnya" + prevKetAlerLai + prevReakAlerLai;
                            } else {
                                prevAlergiLai = "Alergi Lainnya ................";
                            }
                            
                            if (rsPrev.getString("cek_gelang_tanda").equals("ya")) {
                                prevGelang = "Gelang Tanda Alergi Dipasang (Warna Merah)";
                            } else {
                                prevGelang = "-";
                            }
                            
                            if (rsPrev.getString("cek_alergi_diberitahukan_dokter").equals("ya")) {
                                prevDiberitauDr = "Dokter, ";
                            } else {
                                prevDiberitauDr = "";
                            }
                            
                            if (rsPrev.getString("cek_alergi_diberitahukan_farmasis").equals("ya")) {
                                prevDiberitauFar = "Farmasis / Apoteker, ";
                            } else {
                                prevDiberitauFar = "";
                            }
                            
                            if (rsPrev.getString("cek_alergi_diberitahukan_ahliGizi").equals("ya")) {
                                prevDiberitauAGz = "Ahli Gizi";
                            } else {
                                prevDiberitauAGz = "";
                            }
                                
                            int A, B, C, Total;
                            A = Integer.parseInt(prevSkorGz1);
                            B = Integer.parseInt(prevSkorYaGz1);
                            C = Integer.parseInt(prevSkorGz2);

                            Total = 0;
                            Total = A + B + C;
                            prevTotSkorGZ = Valid.SetAngka2(Total);

                            if (Total == 0 || Total == 1) {
                                prevKesimSkriningGZ = "Pasien tidak beresiko malnutrisi";
                            } else if (Total >= 2) {
                                prevKesimSkriningGZ = "Skor >= 2, pasien beresiko malnutrisi, konsul ke Ahli Gizi";
                            }

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='3' align='left'>" + rsPrev.getString("gizi_1") + "</td>"
                                    + "<td valign='top' colspan='1' align='left'>" + prevLihatSkorGz1 + "</td>"
                                    + "<td valign='top' colspan='4' align='left'>" + prevAlergiObat + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='3' align='left'>" + rsPrev.getString("gizi_1ya").replaceAll("<", "&lt;").replaceAll(">", "&gt;") + "</td>"
                                    + "<td valign='top' colspan='1' align='left'>" + prevLihaSkorYaGz1 + "</td>"
                                    + "<td valign='top' colspan='4' align='left'>" + prevAlergiMak + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='4' align='left'>2. Apakah asupan makan pasien berkurang karena penurunan nafsu makan / kesulitan menerima makanan ?</td>"
                                    + "<td valign='top' colspan='4' align='left'>" + prevAlergiLai + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='3' align='left'>" + rsPrev.getString("gizi_2") + "</td>"
                                    + "<td valign='top' colspan='1' align='left'>: Skor (" + prevSkorGz2 + ")</td>"
                                    + "<td valign='top' colspan='4' align='left'>" + prevGelang + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='3' align='right'>Total Skor</td>"
                                    + "<td valign='top' colspan='1' align='left'>: " + prevTotSkorGZ + "</td>"
                                    + "<td valign='top' colspan='4' align='left'>Alergi Diberitahukan Kepada : " + prevDiberitauDr + prevDiberitauFar + prevDiberitauAGz + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' align='left'>Kesimpulan Skrining Gizi : " + prevKesimSkriningGZ.replaceAll("<", "&lt;").replaceAll(">", "&gt;") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>ASSESMEN RESIKO JATUH MORSE</span></td>"
                                    + "</tr>");
                            
                            String resJatuh = "", kondisi = "", resAlatBantu = "", terapi = "", gaya = "", sttsMental = "";
                            if (rsPrev.getString("riw_jatuh_resiko_jatuh").equals("-") || rsPrev.getString("riw_jatuh_resiko_jatuh").equals("Tidak ada atau >= 3 bulan")) {
                                resJatuh = "0";
                            } else {
                                resJatuh = "25";
                            }

                            if (rsPrev.getString("kondisi_kesehatan").equals("-") || rsPrev.getString("kondisi_kesehatan").equals("< diagnosa penyakit")) {
                                kondisi = "0";
                            } else {
                                kondisi = "15";
                            }

                            if (rsPrev.getString("alat_bantu_resiko_jatuh").equals("-") || rsPrev.getString("alat_bantu_resiko_jatuh").equals("Tidak ada/kursi roda/tirah baring")) {
                                resAlatBantu = "0";
                            } else if (rsPrev.getString("alat_bantu_resiko_jatuh").equals("Berpegangan pada perabot")) {
                                resAlatBantu = "30";
                            } else if (rsPrev.getString("alat_bantu_resiko_jatuh").equals("Tongkat/alat penopang")) {
                                resAlatBantu = "15";
                            }

                            if (rsPrev.getString("terapi_IV").equals("-") || rsPrev.getString("terapi_IV").equals("Tidak")) {
                                terapi = "0";
                            } else {
                                terapi = "20";
                            }

                            if (rsPrev.getString("gaya_berjalan").equals("-") || rsPrev.getString("gaya_berjalan").equals("Normal/tirah baring/immobilisasi")) {
                                gaya = "0";
                            } else if (rsPrev.getString("gaya_berjalan").equals("Kerusakan/terganggu")) {
                                gaya = "20";
                            } else if (rsPrev.getString("gaya_berjalan").equals("Lemah")) {
                                gaya = "10";
                            }

                            if (rsPrev.getString("status_mental").equals("-") || rsPrev.getString("status_mental").equals("Sadar kemampuan diri sendiri")) {
                                sttsMental = "0";
                            } else {
                                sttsMental = "15";
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>Resiko Jatuh</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("riw_jatuh_resiko_jatuh").replaceAll("<", "&lt;").replaceAll(">", "&gt;") + " (Skor : " + resJatuh + ")</td>"
                                    + "<td valign='top' colspan='1' align='left'>Terapi IV</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("terapi_IV") + " (Skor : " + terapi + ")</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>Kondisi Kesehatan</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("kondisi_kesehatan").replaceAll("<", "&lt;").replaceAll(">", "&gt;") + " (Skor : " + kondisi + ")</td>"
                                    + "<td valign='top' colspan='1' align='left'>Gaya Berjalan</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("gaya_berjalan") + " (Skor : " + gaya + ")</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>Alat Bantu</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("alat_bantu_resiko_jatuh") + " (Skor : " + resAlatBantu + ")</td>"
                                    + "<td valign='top' colspan='1' align='left'>Status Mental</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("status_mental") + " (Skor : " + sttsMental + ")</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>Jumlah Skor</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("jumlah_skor") + "</td>"
                                    + "<td valign='top' colspan='2' align='left'>Kesimpulan Assesmen Resiko Jatuh Morse</td>"
                                    + "<td valign='top' colspan='2' align='left'>: " + rsPrev.getString("kesimpulan_resiko_jatuh").replaceAll(">", "&gt;") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>FUNGSIONAL</span></td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>1. Alat Bantu</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("alat_bantu") + "</td>"
                                    + "<td valign='top' colspan='1' align='left'>4. ADL</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("adl") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>2. Prothesis</td>"
                                    + "<td valign='top' colspan='3' align='left'>: " + rsPrev.getString("prothesis") + "</td>"
                                    + "<td valign='top' colspan='2' align='left'>5. Riwayat Jatuh Dalam 3 Bulan Terakhir ?</td>"
                                    + "<td valign='top' colspan='2' align='left'>: " + rsPrev.getString("riwayat_jatuh") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>3. Cacat Tubuh</td>"
                                    + "<td valign='top' colspan='7' align='left'>: " + rsPrev.getString("cacat_tubuh") + "</td>"
                                    + "</tr>");

                            String isiBidanPrev = "", QrcodebidanPrev = "";
                            isiBidanPrev = "";

                            //bidan pertama
                            if (rsPrev.getString("nip_bidan").equals("") || rsPrev.getString("nip_bidan").equals("-") || rsPrev.getString("nip_bidan").equals("--")) {
                                QrcodebidanPrev = "";
                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td valign='top' colspan='1' align='left'><b>Nama Bidan</b></td>"
                                        + "<td valign='top' colspan='7' align='left'>: " + rsPrev.getString("nmBidan") + "</td>"
                                        + "</tr>");
                            } else {
                                isiBidanPrev = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                        + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                                "Asesmen Awal Kebidanan", rsPrev.getString("nmBidan") + " (Bidan)",
                                                rsPrev.getString("tglak2"), rsPrev.getString("jamak2")) + "') from kalimat_tte where kode='001'");

                                Valid.cetakQrTte(isiBidanPrev, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");

                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td valign='top' colspan='1' align='left'><b>Nama Bidan</b></td>"
                                        + "<td valign='top' colspan='7' align='left'>:<br>"
                                        + "<img src='file:///" + Sequel.cariFolderTte() + File.separator + "QRTte.jpg" + "' width='150' alt='TTE Bidan'><br>" + rsPrev.getString("nmBidan") + "</td>"
                                        + "</tr>");
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>KEBUTUHAN KOMUNIKASI DAN EDUKASI</span></td>"
                                    + "</tr>");

                            String prevYaHambatan = "", prevHamPend = "", prevHamPeng = "", prevHamKog = "", prevHamFis = "", prevHamBud = "", prevHamEmo = "", prevHamBah = "",
                                    prevHamLain = "", prevHamLainKet = "", prevSebutkan = "";
                            if (rsPrev.getString("cek_ya").equals("ya")) {
                                if (rsPrev.getString("cek_pendengaran").equals("ya")) {
                                    prevHamPend = "Pendengaran, ";
                                } else {
                                    prevHamPend = "";
                                }
                                
                                if (rsPrev.getString("cek_penglihatan").equals("ya")) {
                                    prevHamPeng = "Penglihatan, ";
                                } else {
                                    prevHamPeng = "";
                                }
                                
                                if (rsPrev.getString("cek_kognitif").equals("ya")) {
                                    prevHamKog = "Kognitif, ";
                                } else {
                                    prevHamKog = "";
                                }
                                
                                if (rsPrev.getString("cek_fisik").equals("ya")) {
                                    prevHamFis = "Fisik, ";
                                } else {
                                    prevHamFis = "";
                                }
                                
                                if (rsPrev.getString("cek_budaya").equals("ya")) {
                                    prevHamBud = "Budaya, ";
                                } else {
                                    prevHamBud = "";
                                }
                                
                                if (rsPrev.getString("cek_emosi").equals("ya")) {
                                    prevHamEmo = "Emosi, ";
                                } else {
                                    prevHamEmo = "";
                                }
                                
                                if (rsPrev.getString("cek_bahasa").equals("ya")) {
                                    prevHamBah = "Bahasa, ";
                                } else {
                                    prevHamBah = "";
                                }
                                
                                if (rsPrev.getString("cek_lain_hambatan").equals("ya")) {
                                    if (rsPrev.getString("ket_lain_hambatan").equals("")) {
                                        prevHamLainKet = "";
                                    } else {
                                        prevHamLainKet = " (" + rsPrev.getString("ket_lain_hambatan") + ")";
                                    }
                                    prevHamLain = "Lainnya" + prevHamLainKet;
                                } else {
                                    prevHamLain = "";
                                }
                                prevYaHambatan = "Ya, Jika Ya : " + prevHamPend + prevHamPeng + prevHamKog + prevHamFis + prevHamBud + prevHamEmo + prevHamBah + prevHamLain;
                            } else {
                                prevYaHambatan = "-";
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='2' align='left'>Terdapat Hambatan Dalam Pembelajaran</td>"
                                    + "<td valign='top' colspan='6' align='left'>: " + prevYaHambatan + "</td>"
                                    + "</tr>");
                            
                            if (rsPrev.getString("sebutkan").equals("")) {
                                prevSebutkan = "";
                            } else {
                                prevSebutkan = ", Sebutkan : " + rsPrev.getString("sebutkan");
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='2' align='left'>Dibutuhkan Penerjemah</td>"
                                    + "<td valign='top' colspan='6' align='left'>: " + rsPrev.getString("dibutuhkan_penerjemah") + prevSebutkan + ", Bahasa Isyarat : " + rsPrev.getString("bahasa_isyarat") + "</td>"
                                    + "</tr>");

                            String prevEduDiag = "", prevEduTin = "", prevEduTinKet = "", prevEduObat = "", prevEduReh = "", prevEduDiet = "", prevEduMan = "", prevEduLain = "", prevEduLainKet = "";
                            if (rsPrev.getString("cek_diagnosa").equals("ya")) {
                                prevEduDiag = "Diagnosa dan Manajemen Penyakit, ";
                            } else {
                                prevEduDiag = "";
                            }
                            
                            if (rsPrev.getString("cek_tindakan_keperawatan").equals("ya")) {
                                if (rsPrev.getString("ket_tindakan_keperawatan").equals("")) {
                                    prevEduTinKet = ", ";
                                } else {
                                    prevEduTinKet = " (" + rsPrev.getString("ket_tindakan_keperawatan") + "), ";
                                }
                                prevEduTin = "Tindakan Keperawatan" + prevEduTinKet;
                            } else {
                                prevEduTin = "";
                            }
                            
                            if (rsPrev.getString("cek_lain_kebutuhan_edukasi").equals("ya")) {
                                if (rsPrev.getString("ket_lain_kebutuhan_edukasi").equals("")) {
                                    prevEduLainKet = "";
                                } else {
                                    prevEduLainKet = " (" + rsPrev.getString("ket_lain_kebutuhan_edukasi") + ")";
                                }
                                prevEduLain = "Lain-lain : Sebutkan" + prevEduLainKet;
                            } else {
                                prevEduLain = "";
                            }
                            
                            if (rsPrev.getString("cek_obat_obatan").equals("ya")) {
                                prevEduObat = "Obat-obatan / Terapi, ";
                            } else {
                                prevEduObat = "";
                            }
                            
                            if (rsPrev.getString("cek_rehabilitasi").equals("ya")) {
                                prevEduReh = "Rehabilitasi, ";
                            } else {
                                prevEduReh = "";
                            }
                            
                            if (rsPrev.getString("cek_diet").equals("ya")) {
                                prevEduDiet = "Diet dan Nutrisi, ";
                            } else {
                                prevEduDiet = "";
                            }
                            
                            if (rsPrev.getString("cek_manajemen_nyeri").equals("ya")) {
                                prevEduMan = "Manajemen Nyeri, ";
                            } else {
                                prevEduMan = "";
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='2' align='left'>Kebutuhan Edukasi</td>"
                                    + "<td valign='top' colspan='6' align='left'>: " + prevEduDiag + prevEduObat + prevEduDiet + prevEduTin + prevEduReh + prevEduMan + prevEduLain + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='left'><span style='font-weight:bold'>EDUKASI PASIEN</span></td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' align='left'>Edukasi Awal Disampaikan Tentang Diagnosis, Rencana, Dan Tujuan Terapi Kepada :</td>"
                                    + "</tr>");
                            
                            String prevEpasPas = "", prevEpasKlgPas = "", prevEpasKlgPasKet = "", prevEpasTidak = "", prevEpasTidakKet = "";
                            if (rsPrev.getString("cek_pasien").equals("ya")) {
                                prevEpasPas = "Pasien, ";
                            } else {
                                prevEpasPas = "";
                            }
                            
                            if (rsPrev.getString("cek_keluarga_pasien").equals("ya")) {
                                if (rsPrev.getString("nama_keluarga_pasien").equals("")) {
                                    prevEpasKlgPasKet = "-";
                                } else {
                                    prevEpasKlgPasKet = rsPrev.getString("nama_keluarga_pasien");
                                }
                                prevEpasKlgPas = "Keluarga Pasien, Nama : " + prevEpasKlgPasKet + ", ";
                            } else {
                                prevEpasKlgPas = "";
                            }
                            
                            if (rsPrev.getString("cek_tidak_dapat").equals("ya")) {
                                if (rsPrev.getString("ket_tidak_dapat").equals("")) {
                                    prevEpasTidakKet = "-";
                                } else {
                                    prevEpasTidakKet = rsPrev.getString("ket_tidak_dapat");
                                }
                                prevEpasTidak = "Tidak Dapat Memberikan Edukasi Kepada Pasien Atau Keluarga, Karena : " + prevEpasTidakKet;
                            } else {
                                prevEpasTidak = "";
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' align='left'>" + prevEpasPas + prevEpasKlgPas + prevEpasTidak + "</td>"
                                    + "</tr>");

                            String isiDokterPrev = "", Qrcodedokter = "";
                            //dokter pemberi edukasi
                            if (rsPrev.getString("nip_dokter").equals("") || rsPrev.getString("nip_dokter").equals("-")
                                    || rsPrev.getString("nip_dokter").equals("--") || rsPrev.getString("nip_dokter").contains("D0")) {
                                Qrcodedokter = "";
                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td valign='top' colspan='1' align='left'>Tanggal & Jam</td>"
                                        + "<td valign='top' colspan='2' align='left'>: " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_edukasi")) + ", Jam : " + rsPrev.getString("jam_edukasi").substring(0, 5) + " Wita</td>"
                                        + "<td valign='top' colspan='1' align='left'>Nama Dokter</td>"
                                        + "<td valign='top' colspan='4' align='left'>: " + rsPrev.getString("nmDokter") + "</td>"
                                        + "</tr>");
                            } else {
                                isiDokterPrev = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                        + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                                "Asesmen Awal Kebidanan", rsPrev.getString("nmDokter") + " (Pemberi Edukasi)",
                                                rsPrev.getString("tglak2"), rsPrev.getString("jamak2")) + "') from kalimat_tte where kode='001'");

                                Valid.cetakQrTte(isiDokterPrev, Sequel.cariFolderTte(), "QRTteDokter.jpg", "select logo from setting");
                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td valign='top' colspan='1' align='left'>Tanggal & Jam</td>"
                                        + "<td valign='top' colspan='2' align='left'>: " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_edukasi")) + ", Jam : " + rsPrev.getString("jam_edukasi").substring(0, 5) + " Wita</td>"
                                        + "<td valign='top' colspan='1' align='left'>Nama Dokter</td>"
                                        + "<td valign='top' colspan='4' align='left'>:<br><img src='file:///" + Sequel.cariFolderTte() + File.separator + "QRTteDokter.jpg" + "' width='150' alt='TTE Dokter'><br>" + rsPrev.getString("nmDokter") + "</td>"
                                        + "</tr>");
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='8' bgcolor='#f8fdf3' align='center'><span style='font-weight:bold'>LEMBAR DISCHARGE PLANNING</span></td>"
                                    + "</tr>");
                            
                            String prevIden1 = "", prevIden2 = "", prevIden3 = "", prevIden4 = "", prevIden5 = "", prevIden6 = "", prevIden7 = "", prevIden8 = "", prevIden9 = "", prevIden10 = "";
                            if (rsPrev.getString("cek_identifikasi1").equals("ya")) {
                                prevIden1 = "Pasien dengan keterbatasan kognitif, ketergantungan ADL tinggi,<br>";
                            } else {
                                prevIden1 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi2").equals("ya")) {
                                prevIden2 = "Wanita usia rentan (Ibu hamil, Ibu menyusui, Lansia),<br>";
                            } else {
                                prevIden2 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi3").equals("ya")) {
                                prevIden3 = "Pasien dengan resiko tinggi (Infeksi kejang, Penurunan kesadaran),<br>";
                            } else {
                                prevIden3 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi4").equals("ya")) {
                                prevIden4 = "Potensi komplain tinggi,<br>";
                            } else {
                                prevIden4 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi5").equals("ya")) {
                                prevIden5 = "Pasien dengan penyakit kronis, katastropik (Penyakit Degenerative) terminal,<br>";
                            } else {
                                prevIden5 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi6").equals("ya")) {
                                prevIden6 = "Sering masuk IGD, readmisi RS,<br>";
                            } else {
                                prevIden6 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi7").equals("ya")) {
                                prevIden7 = "Perkiraan asuhan dengan biaya tinggi,<br>";
                            } else {
                                prevIden7 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi8").equals("ya")) {
                                prevIden8 = "Pasien tanpa keluarga / terlantar, tinggal sendiri,<br>";
                            } else {
                                prevIden8 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi9").equals("ya")) {
                                prevIden9 = "Kasus yang melebihi rata-rata lama dirawat,<br>";
                            } else {
                                prevIden9 = "";
                            }
                            
                            if (rsPrev.getString("cek_identifikasi10").equals("ya")) {
                                prevIden10 = "Kasus yang membutuhkan kontinuitas pelayanan, rencana pemulangan penting / beresiko";
                            } else {
                                prevIden10 = "";
                            }
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>Identifikasi, Seleksi / Skrining Pasien</td>"
                                    + "<td valign='top' colspan='7' align='left'>" + prevIden1 + prevIden2 + prevIden3 + prevIden4 + prevIden5 + prevIden6 + prevIden7 + prevIden8 + prevIden9 + prevIden10 + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>Memerlukan</td>"
                                    + "<td valign='top' colspan='7' align='left'>: " + rsPrev.getString("memerlukan") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='1' align='left'>Manajer Pelayanan Pasien</td>"
                                    + "<td valign='top' colspan='1' align='left'>: " + rsPrev.getString("mpp") + "</td>"
                                    + "<td valign='top' colspan='1' align='left'>Discharge Planning</td>"
                                    + "<td valign='top' colspan='5' align='left'>: " + rsPrev.getString("dp") + "</td>"
                                    + "</tr>");
                            
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' colspan='5'></td>"
                                    + "<td valign='top' colspan='3' align='center'>Martapura, " + Valid.SetTglINDONESIA(rsPrev.getString("tgl_dp")) + "</td>"
                                    + "</tr>");

                            try {
                                String gambarTtd = "", ipGambarTtd = "";
                                try {
                                    //cek atau ping ip addres
                                    ipGambarTtd = "192.168.0.230";
                                    InetAddress inet = InetAddress.getByName(ipGambarTtd);

                                    //ping sukses timeout 100 ms (0.1 detik)
                                    if (inet.isReachable(100)) {
                                        if (rsPrev.getString("id_file_nm_keluarga_pasien").equals("")) {
                                            gambarTtd = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                                        } else {
                                            gambarTtd = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + rsPrev.getString("id_file_nm_keluarga_pasien");
                                        }
                                        //ping gagal
                                    } else {
                                        gambarTtd = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notif : " + e);
                                    gambarTtd = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                                }

                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td valign='top' colspan='5'></td>"
                                        + "<td valign='top' colspan='1' align='left'>Nama Pasien / Keluarga Pasien</td>"
                                        + "<td valign='top' colspan='2' align='left'>:<br><img src='" + gambarTtd + "' width='150' alt='TTE Keluarga Pasien'><br>" + rsPrev.getString("nm_keluarga_pasien") + "<br></td>"
                                        + "</tr>");
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            }

                            String isiBidanDpPrev = "", Qrcodebidandp = "";
                            //bidan kedua
                            if (rsPrev.getString("nip_bidan_dp").equals("") || rsPrev.getString("nip_bidan_dp").equals("-") || rsPrev.getString("nip_bidan_dp").equals("--")) {
                                Qrcodebidandp = "";
                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td valign='top' colspan='5'></td>"
                                        + "<td valign='top' colspan='1' align='left'>Nama Bidan</td>"
                                        + "<td valign='top' colspan='2' align='left'>: " + rsPrev.getString("nmBidanDp") + "</td>"
                                        + "</tr>");
                            } else {
                                isiBidanDpPrev = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                        + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                                "Asesmen Awal Kebidanan", rsPrev.getString("nmBidanDp") + " (Bidan)",
                                                rsPrev.getString("tglak2"), rsPrev.getString("jamak2")) + "') from kalimat_tte where kode='001'");

                                Valid.cetakQrTte(isiBidanDpPrev, Sequel.cariFolderTte(), "QRTteBidanDp.jpg", "select logo from setting");
                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td valign='top' colspan='5'></td>"
                                        + "<td valign='top' colspan='1' align='left'>Nama Bidan</td>"
                                        + "<td valign='top' colspan='2' align='left'>:<br><img src='file:///" + Sequel.cariFolderTte() + File.separator + "QRTteBidanDp.jpg" + "' width='150' alt='TTE Bidan'><br>" + rsPrev.getString("nmBidanDp") + "<br></td>"
                                        + "</tr>");
                            }
                        }
                    }
                    
                    htmlContent.append(
                            "</table>"
                            + "</td>"
                            + "</tr>");
                }
                
                LoadHTML1.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' colspan='8' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsPrev != null) {
                    rsPrev.close();
                }
            }            
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void hitungResikoJatuh() {
        int A = 0, B = 0, C = 0, D = 0, E = 0, F = 0, hasil = 0;
        if (cmbResJatuh.getSelectedIndex() == 0 || cmbResJatuh.getSelectedIndex() == 2) {
            TskorResJatuh.setText("0");
        } else {
            TskorResJatuh.setText("25");
        }
        
        if (cmbKondisi.getSelectedIndex() == 0 || cmbKondisi.getSelectedIndex() == 2) {
            TskorKondisi.setText("0");
        } else {
            TskorKondisi.setText("15");
        }
        
        if (cmbResAlatBantu.getSelectedIndex() == 0 || cmbResAlatBantu.getSelectedIndex() == 3) {
            TskorResAlatBantu.setText("0");
        } else if (cmbResAlatBantu.getSelectedIndex() == 1) {
            TskorResAlatBantu.setText("30");
        } else if (cmbResAlatBantu.getSelectedIndex() == 2) {
            TskorResAlatBantu.setText("15");
        }
        
        if (cmbTerapiIV.getSelectedIndex() == 0 || cmbTerapiIV.getSelectedIndex() == 2) {
            TskorTerapi.setText("0");
        } else {
            TskorTerapi.setText("20");
        }
        
        if (cmbGaya.getSelectedIndex() == 0 || cmbGaya.getSelectedIndex() == 3) {
            TskorGaya.setText("0");
        } else if (cmbGaya.getSelectedIndex() == 1) {
            TskorGaya.setText("20");
        } else if (cmbGaya.getSelectedIndex() == 2) {
            TskorGaya.setText("10");
        }
        
        if (cmbSttsMental.getSelectedIndex() == 0 || cmbSttsMental.getSelectedIndex() == 2) {
            TskorSttsMental.setText("0");
        } else {
            TskorSttsMental.setText("15");
        }
        
        A = Integer.parseInt(TskorResJatuh.getText());
        B = Integer.parseInt(TskorKondisi.getText());
        C = Integer.parseInt(TskorResAlatBantu.getText());
        D = Integer.parseInt(TskorTerapi.getText());
        E = Integer.parseInt(TskorGaya.getText());
        F = Integer.parseInt(TskorSttsMental.getText());
        hasil = A + B + C + D + E + F;
        TJmlSkor.setText(Valid.SetAngka(hasil));
        
        if (hasil == 0 && hasil <= 24) {
            kesimpulanResJatuh.setText("Skor 0-24 Resiko Rendah");
        } else if (hasil >= 25 && hasil <= 45) {
            kesimpulanResJatuh.setText("Skor 25-45 Resiko Sedang");
        } else if (hasil >= 46) {
            kesimpulanResJatuh.setText("Skor > 45 Resiko Tinggi");
        }
    }
    
    private void scrollKeAtas() {
        SwingUtilities.invokeLater(() -> {
            scrollInput.getVerticalScrollBar().setValue(0);
            scrollInput.getHorizontalScrollBar().setValue(0);
        });
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
                            nipBidan1 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                            TnmBidan1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnBidan1.requestFocus();
                        }
                    } else if (pilihan == 2) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            nipBidan2 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                            TnmBidan2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnBidan2.requestFocus();
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
                    if (akses.getform().equals("RMAsesmenAwalKebidanan2")) {
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
    }
    
    private void tampilTTD() {
        try {
            StringBuilder htmlContent = new StringBuilder();
            String gambar = "", ipGambar = "";
            try {
                //cek atau ping ip addres
                ipGambar = "192.168.0.230";
                InetAddress inet = InetAddress.getByName(ipGambar);

                //ping sukses timeout 100 ms (0.1 detik)
                if (inet.isReachable(100)) {
                    if (idFileTtd.equals("")) {
                        gambar = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                    } else {
                        gambar = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFileTtd;
                    }
                    //ping gagal
                } else {
                    gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
                gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
            }

            htmlContent.append(
                    "<table width='100%' class='isi'>"
                    + "<thead>"
                    + "<tr class='isi'>"
                    + "<td align='center' bgcolor='#f8fdf3'><b>Pasien/Keluarga Pasien</b></td>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>"
            );

            htmlContent.append(
                    "<tr class='isi'>"
                    + "<td valign='middle' align='center'><img src='" + gambar + "' width='160' height='160' alt='TTD Pasien/Keluarga Pasien'><br>(" + TnmKeluargaPasien.getText() + ")<br></td>"
                    + "</tr>"
            );

            htmlContent.append("</tbody>"
                    + "</table>");

            LoadHTML2.setText(
                    "<html>"
                    + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString()
                    + "</table>"
                    + "</html>");
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public class Painter extends Canvas {

        Image image;

        public void setImage(String file) {
            URL url = null;
            try {
                url = new File(file).toURI().toURL();
            } catch (MalformedURLException ex) {
                System.out.println(ex.toString());
            }
            image = getToolkit().getImage(url);
            repaint();
        }

        public void setImageIcon(ImageIcon file) {
            image = file.getImage();
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            try {
                double d = image.getHeight(this) / this.getHeight();
                double w = image.getWidth(this) / d;
                double x = this.getWidth() / 2 - w / 2;
                g.drawImage(image, (int) x, 0, (int) (w), this.getHeight(), this);
            } catch (Exception e) {
            }
        }
    }
    
    private void bikinQR() { 
        if (akses.getadmin() == true) {
            usernya = "admin";
            pwdnya = "satu";
        } else {
            usernya = akses.getkode();
            pwdnya = Sequel.cariIsi("select AES_DECRYPT(u.password,'windi') from user u "
                    + "inner join petugas pt on pt.nip=AES_DECRYPT(u.id_user,'nur') where AES_DECRYPT(u.id_user,'nur')='" + akses.getkode() + "'");
        }
        
        idParameterTtd = Sequel.cariIsi("select concat('rmeRZ',replace(date(now()),'-',''),'',replace(time(now()),':',''))");

        try {
            URL = prop.getProperty("URLTTDKELUARGAPASIEN") + idParameterTtd;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        Valid.cetakQrTte(URL, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
        Sequel.menyimpanQrTte("parameter_ttd_rme", "'" + idParameterTtd + "','" + usernya + "','" + pwdnya + "','" + noRawat + "','" + Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + noRawat + "'") + "','"
                + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen like '%ASESMEN AWAL KEBIDANAN%'") + "',"
                + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

        try {
            ((RMAsesmenAwalKebidanan2.Painter) gambarQR).setImage("");
            ResultSet hasil = koneksi.createStatement().executeQuery(
                    "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
            for (int I = 0; hasil.next(); I++) {
                Blob blob = hasil.getBlob(1);
                ((RMAsesmenAwalKebidanan2.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
                        blob.getBytes(1, (int) (blob.length()))));
                blob.free();
            }

            emptTeks();
            tampil(noRawat);
            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
