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
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import laporan.DlgHasilPenunjangMedis;
import laporan.DlgPenyakit;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;
import simrskhanza.frmUtama;

/**
 *
 * @author perpustakaan
 */
public final class RMAsesmenMedikKebidanan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, pst1, pst2, pst3, pst4, pst5, pst6, pst7, pscppt;
    private ResultSet rs, rs1, rst1, rst2, rst3, rst4, rst5, rst6, rst7, rscppt;
    private int i = 0, x = 0, pilihan = 0, pilihTemplate = 0;
    private DlgCariPetugas petugas;
    private DlgCariDokter dokter;
    private DlgPenyakit icd10;
    private final Properties prop = new Properties();
    private String nipPemberi = "", nipDokter = "", nipBidan = "", nipDpjp = "", user = "", dataKonfirmasi = "",
            cervi = "", rjp = "", defri = "", intu = "", vtp = "", dekom = "", balut = "", katet = "", ngt = "", infus = "", obat = "", tdkAda = "",
            paten = "", obsPar = "", obsTot = "", trauma = "", resiko = "", benda = "", 
            defor = "", contu = "", penet = "", tender = "", swel = "", eksko = "", abras = "", burn = "", laser = "", tdkTampak = "",
            hipDulu = "", dmDulu = "", lainDulu = "", hipKlg = "", dmKlg = "", janKlg = "", lainKlg = "", merokok = "", lainBiasa = "",
            jamKlr = "", jamMening = "", jamKeluar = "", jamMeninggal = "", idFileTtd = "", idParameterTtd = "", URL = "", usernya = "", pwdnya = "";
    private frmUtama formUtama;

    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsesmenMedikKebidanan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       
        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Penanganan", "Pukul", "Ruang Perawatan", "Nama Petugas Pengedukasi", "Nama Penerima Edukasi", "Nama Dokter Pengedukasi", "Nama Bidan", "Nama DPJP",
            "tgl_penanganan", "pukul_penanganan", "cervical_collar", "rjp", "defribrilasi", "intubasi", "vtp", "dekompresi", "balut_bidai", "kateter_urin", "ngt", "infus",
            "obat", "ket_obat", "tidak_ada", "paten", "obs_partial", "jns_obs_partial", "obs_total", "trauma_jln_nafas", "jns_trauma_jln_nafas", "resiko_aspirasi", "jns_resiko_aspirasi",
            "benda_asing", "ket_benda_asing", "kes_jalan_nafas", "pernafasan", "jns_spontan", "gerakan_dada", "tipe_pernapasan", "kes_pernapasan", "nadi", "jns_reguler", "kulit_mukosa",
            "akral", "jns_akral", "crt", "kes_sirkulasi", "gcs_e", "gcs_v", "gcs_m", "pupil", "diameter_kanan", "diameter_kiri", "ref_cahaya_kanan", "ref_cahaya_kiri", "meningeal_signs",
            "lateralisasi", "deformitas", "contusio", "penetrasi", "tenderness", "swelling", "ekskoriasi", "abrasi", "burn", "laserasi", "tdk_tampak_jelas", "alergi",
            "rp_dahulu_hipertensi", "rp_dahulu_dm", "rp_dahulu_lainya", "rp_dahulu_ket_lainya", "rp_klg_hipertensi", "rp_klg_dm", "rp_klg_jantung", "rp_klg_lainya", "rp_klg_ket_lainya",
            "merokok", "kebiasaan_lainya", "ket_kebiasaan_lainya", "anamnesis", "diagnosis_medis", "icd_10", "rencana_instruksi", "terapi", "diberikan_informasi_edukasi_ttg",
            "rencana_asuhan_diharapkan", "nip_pemberi", "nm_penerima_edukasi", "nip_dokter", "tgl_keluar", "cek_jam_keluar", "jam_keluar", "opname_diruangan", "indikasi_masuk",
            "dipulangkan", "dirujuk_ke", "alasan_dirujuk", "cek_jam_meninggal", "jam_meninggal", "penyebab", "ku", "td", "hr", "rr", "temp", "spo2", "gcs", "nip_bidan", "nip_dpjp",
            "nip_penyimpan_data", "waktu_simpan", "id_file_nm_penerima_edukasi"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 115; i++) {
            TableColumn column = tbAsesmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(90);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setPreferredWidth(190);
            } else if (i == 8) {
                column.setPreferredWidth(220);
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
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new String[]{"No. RM", "Nama Pasien", "Data Template"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTemplate.setModel(tabMode1);
        tbTemplate.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTemplate.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbTemplate.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            }
        }
        tbTemplate.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        
        TObat.setDocument(new batasInput((int) 200).getKata(TObat));
        TBendaAsing.setDocument(new batasInput((int) 200).getKata(TBendaAsing));
        TgcsE.setDocument(new batasInput((int) 3).getKata(TgcsE));
        TgcsV.setDocument(new batasInput((int) 3).getKata(TgcsV));
        TgcsM.setDocument(new batasInput((int) 3).getKata(TgcsM));
        TDiam_kanan.setDocument(new batasInput((int) 4).getKata(TDiam_kanan));
        TDiam_kiri.setDocument(new batasInput((int) 4).getKata(TDiam_kiri));
        TRef_kanan.setDocument(new batasInput((int) 4).getKata(TRef_kanan));
        TRef_kiri.setDocument(new batasInput((int) 4).getKata(TRef_kiri));
        TMeningeal.setDocument(new batasInput((int) 200).getKata(TMeningeal));
        TAlergi.setDocument(new batasInput((int) 200).getKata(TAlergi));
        TLainPenyakitDulu.setDocument(new batasInput((int) 200).getKata(TLainPenyakitDulu));
        TLainPenyakitKlg.setDocument(new batasInput((int) 200).getKata(TLainPenyakitKlg));
        TLainPenyakitBiasa.setDocument(new batasInput((int) 200).getKata(TLainPenyakitBiasa));
        Ticd10.setDocument(new batasInput((int) 15).getKata(Ticd10));
        Tedukasi.setDocument(new batasInput((int) 200).getKata(Tedukasi));
        Trencana.setDocument(new batasInput((int) 200).getKata(Trencana));
        Tnmpenerima.setDocument(new batasInput((int) 200).getKata(Tnmpenerima));
        Tindikasi.setDocument(new batasInput((int) 150).getKata(Tindikasi));
        Tdipulangkan.setDocument(new batasInput((int) 150).getKata(Tdipulangkan));
        Tdirujuk.setDocument(new batasInput((int) 150).getKata(Tdirujuk));
        TAlasanDirujuk.setDocument(new batasInput((int) 200).getKata(TAlasanDirujuk));
        Tpenyebab.setDocument(new batasInput((int) 200).getKata(Tpenyebab));
        Tku.setDocument(new batasInput((int) 200).getKata(Tku));
        Ttd.setDocument(new batasInput((int) 7).getKata(Ttd));
        Thr.setDocument(new batasInput((int) 7).getKata(Thr));
        Trr.setDocument(new batasInput((int) 7).getKata(Trr));
        Ttemp.setDocument(new batasInput((int) 7).getKata(Ttemp));
        Tspo.setDocument(new batasInput((int) 7).getKata(Tspo));
        Tgcs.setDocument(new batasInput((int) 7).getKata(Tgcs));
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

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
        } catch (Exception e) {
            System.out.println(e.toString());
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
        MnDokumenJangMed = new javax.swing.JMenuItem();
        MnHasilPemeriksaanPenunjang = new javax.swing.JMenuItem();
        MnHapusTtd = new javax.swing.JMenuItem();
        MnBikinQrCode = new javax.swing.JMenuItem();
        WindowTemplate = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        jLabel9 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCopas = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        Scroll1 = new widget.ScrollPane();
        tbTemplate = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        Ttemplate = new widget.TextArea();
        WindowNomorDokumenRM = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        jLabel125 = new widget.Label();
        cmbRM = new widget.ComboBox();
        panelisi6 = new widget.panelisi();
        BtnTampilkanQr = new widget.Button();
        BtnCloseIn2 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        jLabel68 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel12 = new widget.Label();
        tglPenanganan = new widget.Tanggal();
        jLabel13 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel64 = new widget.Label();
        ChkCervical = new widget.CekBox();
        ChkRJP = new widget.CekBox();
        ChkDefribilasi = new widget.CekBox();
        ChkIntubasi = new widget.CekBox();
        ChkVTP = new widget.CekBox();
        ChkDekompresi = new widget.CekBox();
        ChkBalut = new widget.CekBox();
        ChkKateter = new widget.CekBox();
        ChkNGT = new widget.CekBox();
        ChkInfus = new widget.CekBox();
        ChkObat = new widget.CekBox();
        ChkTidak = new widget.CekBox();
        TObat = new widget.TextBox();
        jLabel65 = new widget.Label();
        jLabel66 = new widget.Label();
        ChkPaten = new widget.CekBox();
        ChkObstruksiP = new widget.CekBox();
        cmbObstruksi = new widget.ComboBox();
        ChkObstruksiT = new widget.CekBox();
        ChkTrauma = new widget.CekBox();
        cmbTrauma = new widget.ComboBox();
        ChkResiko = new widget.CekBox();
        cmbResiko = new widget.ComboBox();
        ChkBendaAsing = new widget.CekBox();
        TBendaAsing = new widget.TextBox();
        jLabel101 = new widget.Label();
        cmbKesJalanNafas = new widget.ComboBox();
        jLabel67 = new widget.Label();
        cmbSpontan = new widget.ComboBox();
        cmbReguler = new widget.ComboBox();
        jLabel103 = new widget.Label();
        cmbGerakanDada = new widget.ComboBox();
        jLabel104 = new widget.Label();
        cmbTipePernapasan = new widget.ComboBox();
        jLabel105 = new widget.Label();
        cmbKesPernapasan = new widget.ComboBox();
        jLabel69 = new widget.Label();
        jLabel107 = new widget.Label();
        cmbNadi1 = new widget.ComboBox();
        cmbNadi2 = new widget.ComboBox();
        jLabel108 = new widget.Label();
        cmbKulit = new widget.ComboBox();
        jLabel109 = new widget.Label();
        cmbAkral1 = new widget.ComboBox();
        cmbAkral2 = new widget.ComboBox();
        jLabel110 = new widget.Label();
        cmbCRT = new widget.ComboBox();
        jLabel111 = new widget.Label();
        cmbKesSirkulasi = new widget.ComboBox();
        jLabel70 = new widget.Label();
        jLabel102 = new widget.Label();
        TgcsE = new widget.TextBox();
        jLabel106 = new widget.Label();
        TgcsV = new widget.TextBox();
        jLabel112 = new widget.Label();
        TgcsM = new widget.TextBox();
        jLabel113 = new widget.Label();
        cmbPupil = new widget.ComboBox();
        jLabel114 = new widget.Label();
        TDiam_kanan = new widget.TextBox();
        jLabel193 = new widget.Label();
        jLabel115 = new widget.Label();
        TDiam_kiri = new widget.TextBox();
        jLabel194 = new widget.Label();
        jLabel116 = new widget.Label();
        TRef_kanan = new widget.TextBox();
        jLabel117 = new widget.Label();
        TRef_kiri = new widget.TextBox();
        jLabel118 = new widget.Label();
        TMeningeal = new widget.TextBox();
        jLabel119 = new widget.Label();
        cmbLater = new widget.ComboBox();
        jLabel71 = new widget.Label();
        ChkDeformitas = new widget.CekBox();
        ChkContusio = new widget.CekBox();
        ChkPenetrasi = new widget.CekBox();
        ChkTenderness = new widget.CekBox();
        ChkSwelling = new widget.CekBox();
        ChkEkskoriasi = new widget.CekBox();
        ChkAbrasi = new widget.CekBox();
        ChkBurn = new widget.CekBox();
        ChkLaserasi = new widget.CekBox();
        ChkTdkTampk = new widget.CekBox();
        jLabel72 = new widget.Label();
        TAlergi = new widget.TextBox();
        jLabel73 = new widget.Label();
        ChkHipertensiDulu = new widget.CekBox();
        ChkDMDulu = new widget.CekBox();
        ChkLainyaDulu = new widget.CekBox();
        TLainPenyakitDulu = new widget.TextBox();
        jLabel74 = new widget.Label();
        ChkHipertensiKlg = new widget.CekBox();
        ChkDMKlg = new widget.CekBox();
        ChkJantungKlg = new widget.CekBox();
        ChkLainyaKlg = new widget.CekBox();
        TLainPenyakitKlg = new widget.TextBox();
        jLabel75 = new widget.Label();
        ChkMerokok = new widget.CekBox();
        ChkLainyaBiasa = new widget.CekBox();
        TLainPenyakitBiasa = new widget.TextBox();
        jLabel76 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        TAnamnesis = new widget.TextArea();
        scrollPane3 = new widget.ScrollPane();
        Tdiagnosis = new widget.TextArea();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        jLabel79 = new widget.Label();
        Ticd10 = new widget.TextBox();
        jLabel80 = new widget.Label();
        cmbRencana = new widget.ComboBox();
        jLabel81 = new widget.Label();
        scrollPane6 = new widget.ScrollPane();
        Tterapi = new widget.TextArea();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        Tedukasi = new widget.TextBox();
        jLabel84 = new widget.Label();
        Trencana = new widget.TextBox();
        jLabel85 = new widget.Label();
        Tnmpemberi = new widget.TextBox();
        BtnPemberi = new widget.Button();
        jLabel86 = new widget.Label();
        Tnmpenerima = new widget.TextBox();
        jLabel87 = new widget.Label();
        Tnmdokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel88 = new widget.Label();
        jLabel89 = new widget.Label();
        tglKeluar = new widget.Tanggal();
        ChkJamKlr = new widget.CekBox();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel90 = new widget.Label();
        cmbRuangan = new widget.ComboBox();
        jLabel91 = new widget.Label();
        Tindikasi = new widget.TextBox();
        jLabel92 = new widget.Label();
        Tdipulangkan = new widget.TextBox();
        jLabel94 = new widget.Label();
        Tdirujuk = new widget.TextBox();
        jLabel95 = new widget.Label();
        TAlasanDirujuk = new widget.TextBox();
        ChkJamMeninggal = new widget.CekBox();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        jLabel96 = new widget.Label();
        Tpenyebab = new widget.TextBox();
        jLabel97 = new widget.Label();
        Tku = new widget.TextBox();
        jLabel98 = new widget.Label();
        Ttd = new widget.TextBox();
        jLabel99 = new widget.Label();
        Thr = new widget.TextBox();
        jLabel100 = new widget.Label();
        jLabel120 = new widget.Label();
        Trr = new widget.TextBox();
        Ttemp = new widget.TextBox();
        jLabel121 = new widget.Label();
        jLabel122 = new widget.Label();
        Tspo = new widget.TextBox();
        Tgcs = new widget.TextBox();
        jLabel123 = new widget.Label();
        Tnmbidan = new widget.TextBox();
        BtnBidan = new widget.Button();
        jLabel124 = new widget.Label();
        Tnmdpjp = new widget.TextBox();
        BtnDpjp = new widget.Button();
        BtnICD = new widget.Button();
        ChkSamaPetugas = new widget.CekBox();
        ChkSamaDokter = new widget.CekBox();
        BtnAnamnesis = new widget.Button();
        BtnDiagnosis = new widget.Button();
        BtnTerapi = new widget.Button();
        BtnEdukasi = new widget.Button();
        BtnRencana = new widget.Button();
        BtnAlasan = new widget.Button();
        BtnPenyebab = new widget.Button();
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
        panelGlass10 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbAsesmen = new widget.Table();
        panelGlass11 = new widget.panelisi();
        panelGlass12 = new widget.panelisi();
        scrollPane7 = new widget.ScrollPane();
        gambarQR = new Painter();
        jLabel93 = new widget.Label();
        Scroll5 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
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

        MnHapusTtd.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTtd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusTtd.setText("Hapus Tanda Tangan");
        MnHapusTtd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTtd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTtd.setIconTextGap(5);
        MnHapusTtd.setName("MnHapusTtd"); // NOI18N
        MnHapusTtd.setPreferredSize(new java.awt.Dimension(195, 26));
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
        MnBikinQrCode.setPreferredSize(new java.awt.Dimension(195, 26));
        MnBikinQrCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBikinQrCodeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBikinQrCode);

        WindowTemplate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTemplate.setName("WindowTemplate"); // NOI18N
        WindowTemplate.setUndecorated(true);
        WindowTemplate.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Asesmen Medik Kebidanan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame4.setLayout(new java.awt.BorderLayout());

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Key Word :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel9.setRequestFocusEnabled(false);
        panelisi4.add(jLabel9);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi4.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
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
        panelisi4.add(BtnCari1);

        BtnCopas.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopas.setMnemonic('U');
        BtnCopas.setText("Copy & Paste");
        BtnCopas.setToolTipText("Alt+U");
        BtnCopas.setName("BtnCopas"); // NOI18N
        BtnCopas.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCopas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopasActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCopas);

        BtnCloseIn1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn1.setMnemonic('U');
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

        internalFrame4.add(panelisi4, java.awt.BorderLayout.PAGE_END);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 250));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTemplate.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTemplate.setName("tbTemplate"); // NOI18N
        tbTemplate.getTableHeader().setReorderingAllowed(false);
        tbTemplate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTemplateMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbTemplate);

        jPanel1.add(Scroll1);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        Ttemplate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Baca Template Dipilih ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Ttemplate.setColumns(20);
        Ttemplate.setRows(5);
        Ttemplate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ttemplate.setName("Ttemplate"); // NOI18N
        Scroll3.setViewportView(Ttemplate);

        jPanel1.add(Scroll3);

        internalFrame4.add(jPanel1, java.awt.BorderLayout.CENTER);

        WindowTemplate.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Medik Kebidanan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Cetak Dalam Bentuk :");
        jLabel68.setName("jLabel68"); // NOI18N
        jLabel68.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel68);

        cmbPilihCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak.setName("cmbPilihCetak"); // NOI18N
        cmbPilihCetak.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass8.add(cmbPilihCetak);

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
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1316));
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

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Penanganan :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 38, 110, 23);

        tglPenanganan.setEditable(false);
        tglPenanganan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-06-2026" }));
        tglPenanganan.setDisplayFormat("dd-MM-yyyy");
        tglPenanganan.setName("tglPenanganan"); // NOI18N
        tglPenanganan.setOpaque(false);
        tglPenanganan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(tglPenanganan);
        tglPenanganan.setBounds(115, 38, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Pukul :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(205, 38, 60, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(272, 38, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(325, 38, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(378, 38, 45, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 66, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(115, 66, 400, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("INTERVENSI PREHOSPITAL :");
        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 94, 190, 23);

        ChkCervical.setBackground(new java.awt.Color(255, 255, 250));
        ChkCervical.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCervical.setForeground(new java.awt.Color(0, 0, 0));
        ChkCervical.setText("Cervical Collar");
        ChkCervical.setBorderPainted(true);
        ChkCervical.setBorderPaintedFlat(true);
        ChkCervical.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCervical.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCervical.setName("ChkCervical"); // NOI18N
        ChkCervical.setOpaque(false);
        ChkCervical.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkCervical);
        ChkCervical.setBounds(115, 122, 95, 23);

        ChkRJP.setBackground(new java.awt.Color(255, 255, 250));
        ChkRJP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkRJP.setForeground(new java.awt.Color(0, 0, 0));
        ChkRJP.setText("RJP");
        ChkRJP.setBorderPainted(true);
        ChkRJP.setBorderPaintedFlat(true);
        ChkRJP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkRJP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkRJP.setName("ChkRJP"); // NOI18N
        ChkRJP.setOpaque(false);
        ChkRJP.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkRJP);
        ChkRJP.setBounds(115, 150, 60, 23);

        ChkDefribilasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkDefribilasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDefribilasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkDefribilasi.setText("Defribrilasi");
        ChkDefribilasi.setBorderPainted(true);
        ChkDefribilasi.setBorderPaintedFlat(true);
        ChkDefribilasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDefribilasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDefribilasi.setName("ChkDefribilasi"); // NOI18N
        ChkDefribilasi.setOpaque(false);
        ChkDefribilasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDefribilasi);
        ChkDefribilasi.setBounds(225, 122, 80, 23);

        ChkIntubasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkIntubasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkIntubasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkIntubasi.setText("Intubasi");
        ChkIntubasi.setBorderPainted(true);
        ChkIntubasi.setBorderPaintedFlat(true);
        ChkIntubasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkIntubasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkIntubasi.setName("ChkIntubasi"); // NOI18N
        ChkIntubasi.setOpaque(false);
        ChkIntubasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkIntubasi);
        ChkIntubasi.setBounds(225, 150, 80, 23);

        ChkVTP.setBackground(new java.awt.Color(255, 255, 250));
        ChkVTP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkVTP.setForeground(new java.awt.Color(0, 0, 0));
        ChkVTP.setText("VTP");
        ChkVTP.setBorderPainted(true);
        ChkVTP.setBorderPaintedFlat(true);
        ChkVTP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkVTP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkVTP.setName("ChkVTP"); // NOI18N
        ChkVTP.setOpaque(false);
        ChkVTP.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkVTP);
        ChkVTP.setBounds(320, 122, 50, 23);

        ChkDekompresi.setBackground(new java.awt.Color(255, 255, 250));
        ChkDekompresi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDekompresi.setForeground(new java.awt.Color(0, 0, 0));
        ChkDekompresi.setText("Dekompresi Jarum/WSD*");
        ChkDekompresi.setBorderPainted(true);
        ChkDekompresi.setBorderPaintedFlat(true);
        ChkDekompresi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDekompresi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDekompresi.setName("ChkDekompresi"); // NOI18N
        ChkDekompresi.setOpaque(false);
        ChkDekompresi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDekompresi);
        ChkDekompresi.setBounds(320, 150, 150, 23);

        ChkBalut.setBackground(new java.awt.Color(255, 255, 250));
        ChkBalut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBalut.setForeground(new java.awt.Color(0, 0, 0));
        ChkBalut.setText("Balut / Bidai*");
        ChkBalut.setBorderPainted(true);
        ChkBalut.setBorderPaintedFlat(true);
        ChkBalut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBalut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBalut.setName("ChkBalut"); // NOI18N
        ChkBalut.setOpaque(false);
        ChkBalut.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkBalut);
        ChkBalut.setBounds(485, 122, 90, 23);

        ChkKateter.setBackground(new java.awt.Color(255, 255, 250));
        ChkKateter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKateter.setForeground(new java.awt.Color(0, 0, 0));
        ChkKateter.setText("Kateter Urin");
        ChkKateter.setBorderPainted(true);
        ChkKateter.setBorderPaintedFlat(true);
        ChkKateter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKateter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKateter.setName("ChkKateter"); // NOI18N
        ChkKateter.setOpaque(false);
        ChkKateter.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKateter);
        ChkKateter.setBounds(485, 150, 90, 23);

        ChkNGT.setBackground(new java.awt.Color(255, 255, 250));
        ChkNGT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNGT.setForeground(new java.awt.Color(0, 0, 0));
        ChkNGT.setText("NGT");
        ChkNGT.setBorderPainted(true);
        ChkNGT.setBorderPaintedFlat(true);
        ChkNGT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNGT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNGT.setName("ChkNGT"); // NOI18N
        ChkNGT.setOpaque(false);
        ChkNGT.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkNGT);
        ChkNGT.setBounds(585, 122, 50, 23);

        ChkInfus.setBackground(new java.awt.Color(255, 255, 250));
        ChkInfus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkInfus.setForeground(new java.awt.Color(0, 0, 0));
        ChkInfus.setText("Infus");
        ChkInfus.setBorderPainted(true);
        ChkInfus.setBorderPaintedFlat(true);
        ChkInfus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInfus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInfus.setName("ChkInfus"); // NOI18N
        ChkInfus.setOpaque(false);
        ChkInfus.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkInfus);
        ChkInfus.setBounds(585, 150, 50, 23);

        ChkObat.setBackground(new java.awt.Color(255, 255, 250));
        ChkObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObat.setForeground(new java.awt.Color(0, 0, 0));
        ChkObat.setText("Obat :");
        ChkObat.setBorderPainted(true);
        ChkObat.setBorderPaintedFlat(true);
        ChkObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkObat.setName("ChkObat"); // NOI18N
        ChkObat.setOpaque(false);
        ChkObat.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkObatActionPerformed(evt);
            }
        });
        FormInput.add(ChkObat);
        ChkObat.setBounds(645, 122, 55, 23);

        ChkTidak.setBackground(new java.awt.Color(255, 255, 250));
        ChkTidak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTidak.setForeground(new java.awt.Color(0, 0, 0));
        ChkTidak.setText("Tidak Ada");
        ChkTidak.setBorderPainted(true);
        ChkTidak.setBorderPaintedFlat(true);
        ChkTidak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTidak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTidak.setName("ChkTidak"); // NOI18N
        ChkTidak.setOpaque(false);
        ChkTidak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTidak);
        ChkTidak.setBounds(645, 150, 90, 23);

        TObat.setForeground(new java.awt.Color(0, 0, 0));
        TObat.setName("TObat"); // NOI18N
        TObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TObatKeyPressed(evt);
            }
        });
        FormInput.add(TObat);
        TObat.setBounds(700, 122, 400, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("SURVEI PRIMER :");
        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 178, 130, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("JALAN NAFAS :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 206, 130, 23);

        ChkPaten.setBackground(new java.awt.Color(255, 255, 250));
        ChkPaten.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPaten.setForeground(new java.awt.Color(0, 0, 0));
        ChkPaten.setText("Paten");
        ChkPaten.setBorderPainted(true);
        ChkPaten.setBorderPaintedFlat(true);
        ChkPaten.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPaten.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPaten.setName("ChkPaten"); // NOI18N
        ChkPaten.setOpaque(false);
        ChkPaten.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPaten);
        ChkPaten.setBounds(135, 206, 60, 23);

        ChkObstruksiP.setBackground(new java.awt.Color(255, 255, 250));
        ChkObstruksiP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObstruksiP.setForeground(new java.awt.Color(0, 0, 0));
        ChkObstruksiP.setText("Obstruksi Partial :");
        ChkObstruksiP.setBorderPainted(true);
        ChkObstruksiP.setBorderPaintedFlat(true);
        ChkObstruksiP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkObstruksiP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkObstruksiP.setName("ChkObstruksiP"); // NOI18N
        ChkObstruksiP.setOpaque(false);
        ChkObstruksiP.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkObstruksiP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkObstruksiPActionPerformed(evt);
            }
        });
        FormInput.add(ChkObstruksiP);
        ChkObstruksiP.setBounds(135, 234, 110, 23);

        cmbObstruksi.setForeground(new java.awt.Color(0, 0, 0));
        cmbObstruksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Stridor", "Snoring", "Gurgling", "Wheezing" }));
        cmbObstruksi.setName("cmbObstruksi"); // NOI18N
        FormInput.add(cmbObstruksi);
        cmbObstruksi.setBounds(248, 234, 80, 23);

        ChkObstruksiT.setBackground(new java.awt.Color(255, 255, 250));
        ChkObstruksiT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObstruksiT.setForeground(new java.awt.Color(0, 0, 0));
        ChkObstruksiT.setText("Obstruksi Total");
        ChkObstruksiT.setBorderPainted(true);
        ChkObstruksiT.setBorderPaintedFlat(true);
        ChkObstruksiT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkObstruksiT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkObstruksiT.setName("ChkObstruksiT"); // NOI18N
        ChkObstruksiT.setOpaque(false);
        ChkObstruksiT.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkObstruksiT);
        ChkObstruksiT.setBounds(135, 262, 100, 23);

        ChkTrauma.setBackground(new java.awt.Color(255, 255, 250));
        ChkTrauma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTrauma.setForeground(new java.awt.Color(0, 0, 0));
        ChkTrauma.setText("Trauma Jalan Nafas :");
        ChkTrauma.setBorderPainted(true);
        ChkTrauma.setBorderPaintedFlat(true);
        ChkTrauma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTrauma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTrauma.setName("ChkTrauma"); // NOI18N
        ChkTrauma.setOpaque(false);
        ChkTrauma.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkTrauma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkTraumaActionPerformed(evt);
            }
        });
        FormInput.add(ChkTrauma);
        ChkTrauma.setBounds(135, 290, 128, 23);

        cmbTrauma.setForeground(new java.awt.Color(0, 0, 0));
        cmbTrauma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Fasial", "Leher", "Inhalasi*" }));
        cmbTrauma.setName("cmbTrauma"); // NOI18N
        FormInput.add(cmbTrauma);
        cmbTrauma.setBounds(265, 290, 78, 23);

        ChkResiko.setBackground(new java.awt.Color(255, 255, 250));
        ChkResiko.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkResiko.setForeground(new java.awt.Color(0, 0, 0));
        ChkResiko.setText("Resiko Aspirasi :");
        ChkResiko.setBorderPainted(true);
        ChkResiko.setBorderPaintedFlat(true);
        ChkResiko.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkResiko.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkResiko.setName("ChkResiko"); // NOI18N
        ChkResiko.setOpaque(false);
        ChkResiko.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkResiko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkResikoActionPerformed(evt);
            }
        });
        FormInput.add(ChkResiko);
        ChkResiko.setBounds(135, 318, 105, 23);

        cmbResiko.setForeground(new java.awt.Color(0, 0, 0));
        cmbResiko.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Perdarahan", "Muntahan*" }));
        cmbResiko.setName("cmbResiko"); // NOI18N
        FormInput.add(cmbResiko);
        cmbResiko.setBounds(240, 318, 92, 23);

        ChkBendaAsing.setBackground(new java.awt.Color(255, 255, 250));
        ChkBendaAsing.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBendaAsing.setForeground(new java.awt.Color(0, 0, 0));
        ChkBendaAsing.setText("Benda Asing : ");
        ChkBendaAsing.setBorderPainted(true);
        ChkBendaAsing.setBorderPaintedFlat(true);
        ChkBendaAsing.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBendaAsing.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBendaAsing.setName("ChkBendaAsing"); // NOI18N
        ChkBendaAsing.setOpaque(false);
        ChkBendaAsing.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkBendaAsing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkBendaAsingActionPerformed(evt);
            }
        });
        FormInput.add(ChkBendaAsing);
        ChkBendaAsing.setBounds(135, 346, 90, 23);

        TBendaAsing.setForeground(new java.awt.Color(0, 0, 0));
        TBendaAsing.setName("TBendaAsing"); // NOI18N
        TBendaAsing.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBendaAsingKeyPressed(evt);
            }
        });
        FormInput.add(TBendaAsing);
        TBendaAsing.setBounds(227, 346, 240, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Kesimpulan Jalan Nafas :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(135, 374, 130, 23);

        cmbKesJalanNafas.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesJalanNafas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Aman", "Mengancam Jiwa" }));
        cmbKesJalanNafas.setName("cmbKesJalanNafas"); // NOI18N
        FormInput.add(cmbKesJalanNafas);
        cmbKesJalanNafas.setBounds(272, 374, 115, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("PERNAPASAN :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(360, 206, 90, 23);

        cmbSpontan.setForeground(new java.awt.Color(0, 0, 0));
        cmbSpontan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Spontan", "Tidak Spontan" }));
        cmbSpontan.setName("cmbSpontan"); // NOI18N
        cmbSpontan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSpontanActionPerformed(evt);
            }
        });
        FormInput.add(cmbSpontan);
        cmbSpontan.setBounds(455, 206, 105, 23);

        cmbReguler.setForeground(new java.awt.Color(0, 0, 0));
        cmbReguler.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Reguler", "Irreguler" }));
        cmbReguler.setName("cmbReguler"); // NOI18N
        FormInput.add(cmbReguler);
        cmbReguler.setBounds(567, 206, 80, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Gerakan Dada :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(455, 234, 105, 23);

        cmbGerakanDada.setForeground(new java.awt.Color(0, 0, 0));
        cmbGerakanDada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Simetris", "Asimetris", "Jejas dinding dada Kanan", "Jejas dinding dada Kiri", "Jejas dinding dada Kanan & Kiri" }));
        cmbGerakanDada.setName("cmbGerakanDada"); // NOI18N
        FormInput.add(cmbGerakanDada);
        cmbGerakanDada.setBounds(567, 234, 183, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Tipe Pernapasan :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(455, 262, 105, 23);

        cmbTipePernapasan.setForeground(new java.awt.Color(0, 0, 0));
        cmbTipePernapasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Kussmaul", "Biot", "Apneustic", "Retraktif", "Takipneu", "Hiperventilasi", "Cheyne Stoke", "Flare" }));
        cmbTipePernapasan.setName("cmbTipePernapasan"); // NOI18N
        FormInput.add(cmbTipePernapasan);
        cmbTipePernapasan.setBounds(567, 262, 100, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Kesimpulan Pernapasan :");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(410, 290, 150, 23);

        cmbKesPernapasan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesPernapasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Aman", "Mengancam Jiwa" }));
        cmbKesPernapasan.setName("cmbKesPernapasan"); // NOI18N
        FormInput.add(cmbKesPernapasan);
        cmbKesPernapasan.setBounds(567, 290, 115, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("SIRKULASI :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(750, 206, 75, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("Nadi :");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(830, 206, 50, 23);

        cmbNadi1.setForeground(new java.awt.Color(0, 0, 0));
        cmbNadi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Reguler", "Irreguler" }));
        cmbNadi1.setName("cmbNadi1"); // NOI18N
        FormInput.add(cmbNadi1);
        cmbNadi1.setBounds(885, 206, 80, 23);

        cmbNadi2.setForeground(new java.awt.Color(0, 0, 0));
        cmbNadi2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kuat", "Lemah" }));
        cmbNadi2.setName("cmbNadi2"); // NOI18N
        FormInput.add(cmbNadi2);
        cmbNadi2.setBounds(970, 206, 70, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("Kulit / Mukosa :");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(800, 234, 80, 23);

        cmbKulit.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Jaundice", "Berkeringat", "Pucat", "Cyanosis" }));
        cmbKulit.setName("cmbKulit"); // NOI18N
        FormInput.add(cmbKulit);
        cmbKulit.setBounds(885, 234, 90, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("Akral :");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(800, 262, 80, 23);

        cmbAkral1.setForeground(new java.awt.Color(0, 0, 0));
        cmbAkral1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Hangat", "Dingin" }));
        cmbAkral1.setName("cmbAkral1"); // NOI18N
        FormInput.add(cmbAkral1);
        cmbAkral1.setBounds(885, 262, 70, 23);

        cmbAkral2.setForeground(new java.awt.Color(0, 0, 0));
        cmbAkral2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kering", "Basah" }));
        cmbAkral2.setName("cmbAkral2"); // NOI18N
        FormInput.add(cmbAkral2);
        cmbAkral2.setBounds(962, 262, 70, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("CRT :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(800, 290, 80, 23);

        cmbCRT.setForeground(new java.awt.Color(0, 0, 0));
        cmbCRT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "< 2 detik", "> 2 detik" }));
        cmbCRT.setName("cmbCRT"); // NOI18N
        FormInput.add(cmbCRT);
        cmbCRT.setBounds(885, 290, 80, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Kesimpulan Sirkulasi :");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(750, 318, 130, 23);

        cmbKesSirkulasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesSirkulasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Aman", "Mengancam Jiwa" }));
        cmbKesSirkulasi.setName("cmbKesSirkulasi"); // NOI18N
        FormInput.add(cmbKesSirkulasi);
        cmbKesSirkulasi.setBounds(885, 318, 115, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("DISABILITAS :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 402, 130, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("GCS : E :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(135, 402, 60, 23);

        TgcsE.setForeground(new java.awt.Color(0, 0, 0));
        TgcsE.setName("TgcsE"); // NOI18N
        TgcsE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsEKeyPressed(evt);
            }
        });
        FormInput.add(TgcsE);
        TgcsE.setBounds(200, 402, 60, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("V :");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(265, 402, 20, 23);

        TgcsV.setForeground(new java.awt.Color(0, 0, 0));
        TgcsV.setName("TgcsV"); // NOI18N
        TgcsV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsVKeyPressed(evt);
            }
        });
        FormInput.add(TgcsV);
        TgcsV.setBounds(290, 402, 60, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("M :");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(355, 402, 20, 23);

        TgcsM.setForeground(new java.awt.Color(0, 0, 0));
        TgcsM.setName("TgcsM"); // NOI18N
        TgcsM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsMKeyPressed(evt);
            }
        });
        FormInput.add(TgcsM);
        TgcsM.setBounds(380, 402, 60, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("Pupil :");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(135, 430, 60, 23);

        cmbPupil.setForeground(new java.awt.Color(0, 0, 0));
        cmbPupil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Isokor", "Anisokor" }));
        cmbPupil.setName("cmbPupil"); // NOI18N
        FormInput.add(cmbPupil);
        cmbPupil.setBounds(200, 430, 75, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("Diameter Kanan :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(275, 430, 100, 23);

        TDiam_kanan.setForeground(new java.awt.Color(0, 0, 0));
        TDiam_kanan.setName("TDiam_kanan"); // NOI18N
        TDiam_kanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiam_kananKeyPressed(evt);
            }
        });
        FormInput.add(TDiam_kanan);
        TDiam_kanan.setBounds(380, 430, 60, 23);

        jLabel193.setForeground(new java.awt.Color(0, 0, 0));
        jLabel193.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel193.setText("mm");
        jLabel193.setName("jLabel193"); // NOI18N
        FormInput.add(jLabel193);
        jLabel193.setBounds(445, 430, 30, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("Diameter Kiri :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(275, 458, 100, 23);

        TDiam_kiri.setForeground(new java.awt.Color(0, 0, 0));
        TDiam_kiri.setName("TDiam_kiri"); // NOI18N
        TDiam_kiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiam_kiriKeyPressed(evt);
            }
        });
        FormInput.add(TDiam_kiri);
        TDiam_kiri.setBounds(380, 458, 60, 23);

        jLabel194.setForeground(new java.awt.Color(0, 0, 0));
        jLabel194.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel194.setText("mm");
        jLabel194.setName("jLabel194"); // NOI18N
        FormInput.add(jLabel194);
        jLabel194.setBounds(445, 458, 30, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("Reflek Cahaya Kanan :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(245, 486, 130, 23);

        TRef_kanan.setForeground(new java.awt.Color(0, 0, 0));
        TRef_kanan.setName("TRef_kanan"); // NOI18N
        TRef_kanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRef_kananKeyPressed(evt);
            }
        });
        FormInput.add(TRef_kanan);
        TRef_kanan.setBounds(380, 486, 60, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("Reflek Cahaya Kiri :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(245, 514, 130, 23);

        TRef_kiri.setForeground(new java.awt.Color(0, 0, 0));
        TRef_kiri.setName("TRef_kiri"); // NOI18N
        TRef_kiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRef_kiriKeyPressed(evt);
            }
        });
        FormInput.add(TRef_kiri);
        TRef_kiri.setBounds(380, 514, 60, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("Meningeal Signs :");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(85, 542, 110, 23);

        TMeningeal.setForeground(new java.awt.Color(0, 0, 0));
        TMeningeal.setName("TMeningeal"); // NOI18N
        TMeningeal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMeningealKeyPressed(evt);
            }
        });
        FormInput.add(TMeningeal);
        TMeningeal.setBounds(200, 542, 330, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("Lateralisasi :");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(85, 570, 110, 23);

        cmbLater.setForeground(new java.awt.Color(0, 0, 0));
        cmbLater.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kanan", "Kiri", "Tidak Ada*" }));
        cmbLater.setName("cmbLater"); // NOI18N
        FormInput.add(cmbLater);
        cmbLater.setBounds(200, 570, 85, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("EKSPOSUR :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(460, 402, 90, 23);

        ChkDeformitas.setBackground(new java.awt.Color(255, 255, 250));
        ChkDeformitas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDeformitas.setForeground(new java.awt.Color(0, 0, 0));
        ChkDeformitas.setText("Deformitas");
        ChkDeformitas.setBorderPainted(true);
        ChkDeformitas.setBorderPaintedFlat(true);
        ChkDeformitas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDeformitas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDeformitas.setName("ChkDeformitas"); // NOI18N
        ChkDeformitas.setOpaque(false);
        ChkDeformitas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDeformitas);
        ChkDeformitas.setBounds(555, 402, 85, 23);

        ChkContusio.setBackground(new java.awt.Color(255, 255, 250));
        ChkContusio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkContusio.setForeground(new java.awt.Color(0, 0, 0));
        ChkContusio.setText("Contusio");
        ChkContusio.setBorderPainted(true);
        ChkContusio.setBorderPaintedFlat(true);
        ChkContusio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkContusio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkContusio.setName("ChkContusio"); // NOI18N
        ChkContusio.setOpaque(false);
        ChkContusio.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkContusio);
        ChkContusio.setBounds(555, 430, 85, 23);

        ChkPenetrasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkPenetrasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPenetrasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkPenetrasi.setText("Penetrasi");
        ChkPenetrasi.setBorderPainted(true);
        ChkPenetrasi.setBorderPaintedFlat(true);
        ChkPenetrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPenetrasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPenetrasi.setName("ChkPenetrasi"); // NOI18N
        ChkPenetrasi.setOpaque(false);
        ChkPenetrasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPenetrasi);
        ChkPenetrasi.setBounds(555, 458, 85, 23);

        ChkTenderness.setBackground(new java.awt.Color(255, 255, 250));
        ChkTenderness.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTenderness.setForeground(new java.awt.Color(0, 0, 0));
        ChkTenderness.setText("Tenderness");
        ChkTenderness.setBorderPainted(true);
        ChkTenderness.setBorderPaintedFlat(true);
        ChkTenderness.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTenderness.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTenderness.setName("ChkTenderness"); // NOI18N
        ChkTenderness.setOpaque(false);
        ChkTenderness.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTenderness);
        ChkTenderness.setBounds(555, 486, 85, 23);

        ChkSwelling.setBackground(new java.awt.Color(255, 255, 250));
        ChkSwelling.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSwelling.setForeground(new java.awt.Color(0, 0, 0));
        ChkSwelling.setText("Swelling");
        ChkSwelling.setBorderPainted(true);
        ChkSwelling.setBorderPaintedFlat(true);
        ChkSwelling.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSwelling.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSwelling.setName("ChkSwelling"); // NOI18N
        ChkSwelling.setOpaque(false);
        ChkSwelling.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkSwelling);
        ChkSwelling.setBounds(555, 514, 85, 23);

        ChkEkskoriasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkEkskoriasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkEkskoriasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkEkskoriasi.setText("Ekskoriasi");
        ChkEkskoriasi.setBorderPainted(true);
        ChkEkskoriasi.setBorderPaintedFlat(true);
        ChkEkskoriasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkEkskoriasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkEkskoriasi.setName("ChkEkskoriasi"); // NOI18N
        ChkEkskoriasi.setOpaque(false);
        ChkEkskoriasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkEkskoriasi);
        ChkEkskoriasi.setBounds(555, 542, 85, 23);

        ChkAbrasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkAbrasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAbrasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkAbrasi.setText("Abrasi");
        ChkAbrasi.setBorderPainted(true);
        ChkAbrasi.setBorderPaintedFlat(true);
        ChkAbrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAbrasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAbrasi.setName("ChkAbrasi"); // NOI18N
        ChkAbrasi.setOpaque(false);
        ChkAbrasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAbrasi);
        ChkAbrasi.setBounds(650, 402, 70, 23);

        ChkBurn.setBackground(new java.awt.Color(255, 255, 250));
        ChkBurn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBurn.setForeground(new java.awt.Color(0, 0, 0));
        ChkBurn.setText("Burn");
        ChkBurn.setBorderPainted(true);
        ChkBurn.setBorderPaintedFlat(true);
        ChkBurn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBurn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBurn.setName("ChkBurn"); // NOI18N
        ChkBurn.setOpaque(false);
        ChkBurn.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkBurn);
        ChkBurn.setBounds(650, 430, 70, 23);

        ChkLaserasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkLaserasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLaserasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkLaserasi.setText("Laserasi");
        ChkLaserasi.setBorderPainted(true);
        ChkLaserasi.setBorderPaintedFlat(true);
        ChkLaserasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLaserasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLaserasi.setName("ChkLaserasi"); // NOI18N
        ChkLaserasi.setOpaque(false);
        ChkLaserasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkLaserasi);
        ChkLaserasi.setBounds(650, 458, 70, 23);

        ChkTdkTampk.setBackground(new java.awt.Color(255, 255, 250));
        ChkTdkTampk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTdkTampk.setForeground(new java.awt.Color(0, 0, 0));
        ChkTdkTampk.setText("Tidak Tampak Jelas");
        ChkTdkTampk.setBorderPainted(true);
        ChkTdkTampk.setBorderPaintedFlat(true);
        ChkTdkTampk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTdkTampk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTdkTampk.setName("ChkTdkTampk"); // NOI18N
        ChkTdkTampk.setOpaque(false);
        ChkTdkTampk.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTdkTampk);
        ChkTdkTampk.setBounds(650, 486, 120, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("ALERGI :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(780, 402, 70, 23);

        TAlergi.setForeground(new java.awt.Color(0, 0, 0));
        TAlergi.setName("TAlergi"); // NOI18N
        TAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlergiKeyPressed(evt);
            }
        });
        FormInput.add(TAlergi);
        TAlergi.setBounds(855, 402, 330, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Riwayat Penyakit Dahulu :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(780, 430, 150, 23);

        ChkHipertensiDulu.setBackground(new java.awt.Color(255, 255, 250));
        ChkHipertensiDulu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkHipertensiDulu.setForeground(new java.awt.Color(0, 0, 0));
        ChkHipertensiDulu.setText("Hipertensi");
        ChkHipertensiDulu.setBorderPainted(true);
        ChkHipertensiDulu.setBorderPaintedFlat(true);
        ChkHipertensiDulu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkHipertensiDulu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHipertensiDulu.setName("ChkHipertensiDulu"); // NOI18N
        ChkHipertensiDulu.setOpaque(false);
        ChkHipertensiDulu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkHipertensiDulu);
        ChkHipertensiDulu.setBounds(935, 430, 80, 23);

        ChkDMDulu.setBackground(new java.awt.Color(255, 255, 250));
        ChkDMDulu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDMDulu.setForeground(new java.awt.Color(0, 0, 0));
        ChkDMDulu.setText("DM");
        ChkDMDulu.setBorderPainted(true);
        ChkDMDulu.setBorderPaintedFlat(true);
        ChkDMDulu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDMDulu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDMDulu.setName("ChkDMDulu"); // NOI18N
        ChkDMDulu.setOpaque(false);
        ChkDMDulu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDMDulu);
        ChkDMDulu.setBounds(1030, 430, 50, 23);

        ChkLainyaDulu.setBackground(new java.awt.Color(255, 255, 250));
        ChkLainyaDulu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLainyaDulu.setForeground(new java.awt.Color(0, 0, 0));
        ChkLainyaDulu.setText("Lainnya :");
        ChkLainyaDulu.setBorderPainted(true);
        ChkLainyaDulu.setBorderPaintedFlat(true);
        ChkLainyaDulu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLainyaDulu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLainyaDulu.setName("ChkLainyaDulu"); // NOI18N
        ChkLainyaDulu.setOpaque(false);
        ChkLainyaDulu.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkLainyaDulu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkLainyaDuluActionPerformed(evt);
            }
        });
        FormInput.add(ChkLainyaDulu);
        ChkLainyaDulu.setBounds(935, 458, 70, 23);

        TLainPenyakitDulu.setForeground(new java.awt.Color(0, 0, 0));
        TLainPenyakitDulu.setName("TLainPenyakitDulu"); // NOI18N
        TLainPenyakitDulu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLainPenyakitDuluKeyPressed(evt);
            }
        });
        FormInput.add(TLainPenyakitDulu);
        TLainPenyakitDulu.setBounds(1005, 458, 180, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Riwayat Penyakit Keluarga :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(780, 486, 150, 23);

        ChkHipertensiKlg.setBackground(new java.awt.Color(255, 255, 250));
        ChkHipertensiKlg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkHipertensiKlg.setForeground(new java.awt.Color(0, 0, 0));
        ChkHipertensiKlg.setText("Hipertensi");
        ChkHipertensiKlg.setBorderPainted(true);
        ChkHipertensiKlg.setBorderPaintedFlat(true);
        ChkHipertensiKlg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkHipertensiKlg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHipertensiKlg.setName("ChkHipertensiKlg"); // NOI18N
        ChkHipertensiKlg.setOpaque(false);
        ChkHipertensiKlg.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkHipertensiKlg);
        ChkHipertensiKlg.setBounds(935, 486, 80, 23);

        ChkDMKlg.setBackground(new java.awt.Color(255, 255, 250));
        ChkDMKlg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDMKlg.setForeground(new java.awt.Color(0, 0, 0));
        ChkDMKlg.setText("DM");
        ChkDMKlg.setBorderPainted(true);
        ChkDMKlg.setBorderPaintedFlat(true);
        ChkDMKlg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDMKlg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDMKlg.setName("ChkDMKlg"); // NOI18N
        ChkDMKlg.setOpaque(false);
        ChkDMKlg.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDMKlg);
        ChkDMKlg.setBounds(1030, 486, 50, 23);

        ChkJantungKlg.setBackground(new java.awt.Color(255, 255, 250));
        ChkJantungKlg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkJantungKlg.setForeground(new java.awt.Color(0, 0, 0));
        ChkJantungKlg.setText("Jantung");
        ChkJantungKlg.setBorderPainted(true);
        ChkJantungKlg.setBorderPaintedFlat(true);
        ChkJantungKlg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkJantungKlg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkJantungKlg.setName("ChkJantungKlg"); // NOI18N
        ChkJantungKlg.setOpaque(false);
        ChkJantungKlg.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkJantungKlg);
        ChkJantungKlg.setBounds(1090, 486, 70, 23);

        ChkLainyaKlg.setBackground(new java.awt.Color(255, 255, 250));
        ChkLainyaKlg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLainyaKlg.setForeground(new java.awt.Color(0, 0, 0));
        ChkLainyaKlg.setText("Lainnya :");
        ChkLainyaKlg.setBorderPainted(true);
        ChkLainyaKlg.setBorderPaintedFlat(true);
        ChkLainyaKlg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLainyaKlg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLainyaKlg.setName("ChkLainyaKlg"); // NOI18N
        ChkLainyaKlg.setOpaque(false);
        ChkLainyaKlg.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkLainyaKlg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkLainyaKlgActionPerformed(evt);
            }
        });
        FormInput.add(ChkLainyaKlg);
        ChkLainyaKlg.setBounds(935, 514, 70, 23);

        TLainPenyakitKlg.setForeground(new java.awt.Color(0, 0, 0));
        TLainPenyakitKlg.setName("TLainPenyakitKlg"); // NOI18N
        TLainPenyakitKlg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLainPenyakitKlgKeyPressed(evt);
            }
        });
        FormInput.add(TLainPenyakitKlg);
        TLainPenyakitKlg.setBounds(1005, 514, 180, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Riwayat Kebiasaan :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(780, 542, 150, 23);

        ChkMerokok.setBackground(new java.awt.Color(255, 255, 250));
        ChkMerokok.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMerokok.setForeground(new java.awt.Color(0, 0, 0));
        ChkMerokok.setText("Merokok");
        ChkMerokok.setBorderPainted(true);
        ChkMerokok.setBorderPaintedFlat(true);
        ChkMerokok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMerokok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMerokok.setName("ChkMerokok"); // NOI18N
        ChkMerokok.setOpaque(false);
        ChkMerokok.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkMerokok);
        ChkMerokok.setBounds(935, 542, 70, 23);

        ChkLainyaBiasa.setBackground(new java.awt.Color(255, 255, 250));
        ChkLainyaBiasa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLainyaBiasa.setForeground(new java.awt.Color(0, 0, 0));
        ChkLainyaBiasa.setText("Lainnya :");
        ChkLainyaBiasa.setBorderPainted(true);
        ChkLainyaBiasa.setBorderPaintedFlat(true);
        ChkLainyaBiasa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLainyaBiasa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLainyaBiasa.setName("ChkLainyaBiasa"); // NOI18N
        ChkLainyaBiasa.setOpaque(false);
        ChkLainyaBiasa.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkLainyaBiasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkLainyaBiasaActionPerformed(evt);
            }
        });
        FormInput.add(ChkLainyaBiasa);
        ChkLainyaBiasa.setBounds(935, 570, 70, 23);

        TLainPenyakitBiasa.setForeground(new java.awt.Color(0, 0, 0));
        TLainPenyakitBiasa.setName("TLainPenyakitBiasa"); // NOI18N
        TLainPenyakitBiasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLainPenyakitBiasaKeyPressed(evt);
            }
        });
        FormInput.add(TLainPenyakitBiasa);
        TLainPenyakitBiasa.setBounds(1005, 570, 180, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Anamnesis :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 598, 130, 23);

        scrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane2.setName("scrollPane2"); // NOI18N

        TAnamnesis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TAnamnesis.setColumns(20);
        TAnamnesis.setRows(5);
        TAnamnesis.setName("TAnamnesis"); // NOI18N
        TAnamnesis.setPreferredSize(new java.awt.Dimension(162, 2000));
        TAnamnesis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAnamnesisKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(TAnamnesis);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(135, 598, 700, 130);

        scrollPane3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane3.setName("scrollPane3"); // NOI18N

        Tdiagnosis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tdiagnosis.setColumns(20);
        Tdiagnosis.setRows(5);
        Tdiagnosis.setName("Tdiagnosis"); // NOI18N
        Tdiagnosis.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tdiagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosisKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(Tdiagnosis);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(135, 734, 700, 60);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Diagnosis Medis :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 734, 130, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Sementara/Masalah  ");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(0, 749, 130, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("ICD - 10 :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(840, 734, 70, 23);

        Ticd10.setForeground(new java.awt.Color(0, 0, 0));
        Ticd10.setName("Ticd10"); // NOI18N
        Ticd10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Ticd10KeyPressed(evt);
            }
        });
        FormInput.add(Ticd10);
        Ticd10.setBounds(915, 734, 80, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("RENCANA / INSTRUKSI :");
        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(0, 800, 170, 23);

        cmbRencana.setForeground(new java.awt.Color(0, 0, 0));
        cmbRencana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kuratif", "Paliatif", "Rehabilitatif" }));
        cmbRencana.setName("cmbRencana"); // NOI18N
        FormInput.add(cmbRencana);
        cmbRencana.setBounds(178, 800, 90, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Terapi :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 828, 170, 23);

        scrollPane6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane6.setName("scrollPane6"); // NOI18N

        Tterapi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tterapi.setColumns(20);
        Tterapi.setRows(5);
        Tterapi.setName("Tterapi"); // NOI18N
        Tterapi.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tterapi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TterapiKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(Tterapi);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(178, 828, 655, 80);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("TELAH DIBERIKAN INFORMASI / EDUKASI TENTANG :");
        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 914, 320, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Informasi / Edukasi Tentang :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(0, 942, 170, 23);

        Tedukasi.setForeground(new java.awt.Color(0, 0, 0));
        Tedukasi.setName("Tedukasi"); // NOI18N
        Tedukasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TedukasiKeyPressed(evt);
            }
        });
        FormInput.add(Tedukasi);
        Tedukasi.setBounds(178, 942, 655, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Rencana Asuhan Diharapkan :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(0, 970, 170, 23);

        Trencana.setForeground(new java.awt.Color(0, 0, 0));
        Trencana.setName("Trencana"); // NOI18N
        Trencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrencanaKeyPressed(evt);
            }
        });
        FormInput.add(Trencana);
        Trencana.setBounds(178, 970, 655, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Pemberi Edukasi / Informasi :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(0, 998, 170, 23);

        Tnmpemberi.setEditable(false);
        Tnmpemberi.setForeground(new java.awt.Color(0, 0, 0));
        Tnmpemberi.setName("Tnmpemberi"); // NOI18N
        FormInput.add(Tnmpemberi);
        Tnmpemberi.setBounds(178, 998, 260, 23);

        BtnPemberi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPemberi.setMnemonic('2');
        BtnPemberi.setToolTipText("Alt+2");
        BtnPemberi.setName("BtnPemberi"); // NOI18N
        BtnPemberi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPemberi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPemberiActionPerformed(evt);
            }
        });
        FormInput.add(BtnPemberi);
        BtnPemberi.setBounds(440, 998, 28, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Penerima Edukasi :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(475, 998, 110, 23);

        Tnmpenerima.setForeground(new java.awt.Color(0, 0, 0));
        Tnmpenerima.setName("Tnmpenerima"); // NOI18N
        Tnmpenerima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmpenerimaKeyPressed(evt);
            }
        });
        FormInput.add(Tnmpenerima);
        Tnmpenerima.setBounds(588, 998, 245, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Nama Dokter : ");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 1026, 170, 23);

        Tnmdokter.setEditable(false);
        Tnmdokter.setForeground(new java.awt.Color(0, 0, 0));
        Tnmdokter.setName("Tnmdokter"); // NOI18N
        FormInput.add(Tnmdokter);
        Tnmdokter.setBounds(178, 1026, 260, 23);

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
        BtnDokter.setBounds(440, 1026, 28, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("PASIEN KELUAR PONEK :");
        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(0, 1054, 170, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Tgl. Keluar :");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 1082, 130, 23);

        tglKeluar.setEditable(false);
        tglKeluar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-06-2026" }));
        tglKeluar.setDisplayFormat("dd-MM-yyyy");
        tglKeluar.setName("tglKeluar"); // NOI18N
        tglKeluar.setOpaque(false);
        tglKeluar.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(tglKeluar);
        tglKeluar.setBounds(135, 1082, 90, 23);

        ChkJamKlr.setBackground(new java.awt.Color(255, 255, 250));
        ChkJamKlr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkJamKlr.setForeground(new java.awt.Color(0, 0, 0));
        ChkJamKlr.setText("Jam Keluar :");
        ChkJamKlr.setBorderPainted(true);
        ChkJamKlr.setBorderPaintedFlat(true);
        ChkJamKlr.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkJamKlr.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkJamKlr.setName("ChkJamKlr"); // NOI18N
        ChkJamKlr.setOpaque(false);
        ChkJamKlr.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkJamKlr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJamKlrActionPerformed(evt);
            }
        });
        FormInput.add(ChkJamKlr);
        ChkJamKlr.setBounds(230, 1082, 95, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(332, 1082, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(385, 1082, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(438, 1082, 45, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Opname Diruangan :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(0, 1110, 130, 23);

        cmbRuangan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbRuangan.setName("cmbRuangan"); // NOI18N
        cmbRuangan.setPreferredSize(new java.awt.Dimension(145, 23));
        cmbRuangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbRuanganMouseReleased(evt);
            }
        });
        FormInput.add(cmbRuangan);
        cmbRuangan.setBounds(135, 1110, 170, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Indikasi Masuk :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(0, 1138, 130, 23);

        Tindikasi.setForeground(new java.awt.Color(0, 0, 0));
        Tindikasi.setName("Tindikasi"); // NOI18N
        Tindikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindikasiKeyPressed(evt);
            }
        });
        FormInput.add(Tindikasi);
        Tindikasi.setBounds(135, 1138, 350, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Diplngkan, Kontrol Ke :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(0, 1166, 130, 23);

        Tdipulangkan.setForeground(new java.awt.Color(0, 0, 0));
        Tdipulangkan.setName("Tdipulangkan"); // NOI18N
        Tdipulangkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdipulangkanKeyPressed(evt);
            }
        });
        FormInput.add(Tdipulangkan);
        Tdipulangkan.setBounds(135, 1166, 350, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Dirujuk Ke :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(0, 1194, 130, 23);

        Tdirujuk.setForeground(new java.awt.Color(0, 0, 0));
        Tdirujuk.setName("Tdirujuk"); // NOI18N
        Tdirujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdirujukKeyPressed(evt);
            }
        });
        FormInput.add(Tdirujuk);
        Tdirujuk.setBounds(135, 1194, 350, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Alasan Dirujuk :");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(0, 1222, 130, 23);

        TAlasanDirujuk.setForeground(new java.awt.Color(0, 0, 0));
        TAlasanDirujuk.setName("TAlasanDirujuk"); // NOI18N
        TAlasanDirujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlasanDirujukKeyPressed(evt);
            }
        });
        FormInput.add(TAlasanDirujuk);
        TAlasanDirujuk.setBounds(135, 1222, 770, 23);

        ChkJamMeninggal.setBackground(new java.awt.Color(255, 255, 250));
        ChkJamMeninggal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkJamMeninggal.setForeground(new java.awt.Color(0, 0, 0));
        ChkJamMeninggal.setText("Meninggal Jam :");
        ChkJamMeninggal.setBorderPainted(true);
        ChkJamMeninggal.setBorderPaintedFlat(true);
        ChkJamMeninggal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkJamMeninggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkJamMeninggal.setName("ChkJamMeninggal"); // NOI18N
        ChkJamMeninggal.setOpaque(false);
        ChkJamMeninggal.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkJamMeninggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJamMeninggalActionPerformed(evt);
            }
        });
        FormInput.add(ChkJamMeninggal);
        ChkJamMeninggal.setBounds(135, 1250, 103, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(240, 1250, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(293, 1250, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(346, 1250, 45, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Penyebab :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(0, 1278, 130, 23);

        Tpenyebab.setForeground(new java.awt.Color(0, 0, 0));
        Tpenyebab.setName("Tpenyebab"); // NOI18N
        Tpenyebab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpenyebabKeyPressed(evt);
            }
        });
        FormInput.add(Tpenyebab);
        Tpenyebab.setBounds(135, 1278, 770, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("K/u :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(490, 1082, 50, 23);

        Tku.setForeground(new java.awt.Color(0, 0, 0));
        Tku.setName("Tku"); // NOI18N
        Tku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkuKeyPressed(evt);
            }
        });
        FormInput.add(Tku);
        Tku.setBounds(543, 1082, 322, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("TD :");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(490, 1110, 50, 23);

        Ttd.setForeground(new java.awt.Color(0, 0, 0));
        Ttd.setName("Ttd"); // NOI18N
        Ttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdKeyPressed(evt);
            }
        });
        FormInput.add(Ttd);
        Ttd.setBounds(543, 1110, 70, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("HR :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(490, 1138, 50, 23);

        Thr.setForeground(new java.awt.Color(0, 0, 0));
        Thr.setName("Thr"); // NOI18N
        Thr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThrKeyPressed(evt);
            }
        });
        FormInput.add(Thr);
        Thr.setBounds(543, 1138, 70, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("RR :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(615, 1110, 50, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("Temp :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(615, 1138, 50, 23);

        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        FormInput.add(Trr);
        Trr.setBounds(670, 1110, 70, 23);

        Ttemp.setForeground(new java.awt.Color(0, 0, 0));
        Ttemp.setName("Ttemp"); // NOI18N
        Ttemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtempKeyPressed(evt);
            }
        });
        FormInput.add(Ttemp);
        Ttemp.setBounds(670, 1138, 70, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("SPO2 :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(740, 1110, 50, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("GCS :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(740, 1138, 50, 23);

        Tspo.setForeground(new java.awt.Color(0, 0, 0));
        Tspo.setName("Tspo"); // NOI18N
        Tspo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TspoKeyPressed(evt);
            }
        });
        FormInput.add(Tspo);
        Tspo.setBounds(795, 1110, 70, 23);

        Tgcs.setForeground(new java.awt.Color(0, 0, 0));
        Tgcs.setName("Tgcs"); // NOI18N
        Tgcs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsKeyPressed(evt);
            }
        });
        FormInput.add(Tgcs);
        Tgcs.setBounds(795, 1138, 70, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("Bidan Yang Menyerahkan :");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(490, 1166, 150, 23);

        Tnmbidan.setEditable(false);
        Tnmbidan.setForeground(new java.awt.Color(0, 0, 0));
        Tnmbidan.setName("Tnmbidan"); // NOI18N
        FormInput.add(Tnmbidan);
        Tnmbidan.setBounds(645, 1166, 260, 23);

        BtnBidan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnBidan.setMnemonic('2');
        BtnBidan.setToolTipText("Alt+2");
        BtnBidan.setName("BtnBidan"); // NOI18N
        BtnBidan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnBidan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBidanActionPerformed(evt);
            }
        });
        FormInput.add(BtnBidan);
        BtnBidan.setBounds(905, 1166, 28, 23);

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setText("Mengetahui DPJP :");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(490, 1194, 150, 23);

        Tnmdpjp.setEditable(false);
        Tnmdpjp.setForeground(new java.awt.Color(0, 0, 0));
        Tnmdpjp.setName("Tnmdpjp"); // NOI18N
        FormInput.add(Tnmdpjp);
        Tnmdpjp.setBounds(645, 1194, 260, 23);

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
        BtnDpjp.setBounds(905, 1194, 28, 23);

        BtnICD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnICD.setMnemonic('2');
        BtnICD.setToolTipText("Alt+2");
        BtnICD.setName("BtnICD"); // NOI18N
        BtnICD.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnICD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnICDActionPerformed(evt);
            }
        });
        FormInput.add(BtnICD);
        BtnICD.setBounds(1000, 734, 28, 23);

        ChkSamaPetugas.setBackground(new java.awt.Color(255, 255, 250));
        ChkSamaPetugas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSamaPetugas.setForeground(new java.awt.Color(0, 0, 0));
        ChkSamaPetugas.setText("Sama dg. Pemberi Edukasi");
        ChkSamaPetugas.setBorderPainted(true);
        ChkSamaPetugas.setBorderPaintedFlat(true);
        ChkSamaPetugas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSamaPetugas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSamaPetugas.setName("ChkSamaPetugas"); // NOI18N
        ChkSamaPetugas.setOpaque(false);
        ChkSamaPetugas.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkSamaPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSamaPetugasActionPerformed(evt);
            }
        });
        FormInput.add(ChkSamaPetugas);
        ChkSamaPetugas.setBounds(945, 1166, 170, 23);

        ChkSamaDokter.setBackground(new java.awt.Color(255, 255, 250));
        ChkSamaDokter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSamaDokter.setForeground(new java.awt.Color(0, 0, 0));
        ChkSamaDokter.setText("Sama dg. Dokter Edukasi");
        ChkSamaDokter.setBorderPainted(true);
        ChkSamaDokter.setBorderPaintedFlat(true);
        ChkSamaDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSamaDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSamaDokter.setName("ChkSamaDokter"); // NOI18N
        ChkSamaDokter.setOpaque(false);
        ChkSamaDokter.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkSamaDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSamaDokterActionPerformed(evt);
            }
        });
        FormInput.add(ChkSamaDokter);
        ChkSamaDokter.setBounds(945, 1194, 170, 23);

        BtnAnamnesis.setForeground(new java.awt.Color(0, 0, 0));
        BtnAnamnesis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAnamnesis.setMnemonic('2');
        BtnAnamnesis.setText("Template");
        BtnAnamnesis.setToolTipText("Alt+2");
        BtnAnamnesis.setName("BtnAnamnesis"); // NOI18N
        BtnAnamnesis.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAnamnesis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAnamnesisActionPerformed(evt);
            }
        });
        FormInput.add(BtnAnamnesis);
        BtnAnamnesis.setBounds(850, 598, 100, 23);

        BtnDiagnosis.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagnosis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagnosis.setMnemonic('2');
        BtnDiagnosis.setText("Template");
        BtnDiagnosis.setToolTipText("Alt+2");
        BtnDiagnosis.setName("BtnDiagnosis"); // NOI18N
        BtnDiagnosis.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDiagnosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagnosisActionPerformed(evt);
            }
        });
        FormInput.add(BtnDiagnosis);
        BtnDiagnosis.setBounds(850, 764, 100, 23);

        BtnTerapi.setForeground(new java.awt.Color(0, 0, 0));
        BtnTerapi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnTerapi.setMnemonic('2');
        BtnTerapi.setText("Template");
        BtnTerapi.setToolTipText("Alt+2");
        BtnTerapi.setName("BtnTerapi"); // NOI18N
        BtnTerapi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTerapi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTerapiActionPerformed(evt);
            }
        });
        FormInput.add(BtnTerapi);
        BtnTerapi.setBounds(850, 828, 100, 23);

        BtnEdukasi.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdukasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnEdukasi.setMnemonic('2');
        BtnEdukasi.setText("Template");
        BtnEdukasi.setToolTipText("Alt+2");
        BtnEdukasi.setName("BtnEdukasi"); // NOI18N
        BtnEdukasi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnEdukasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdukasiActionPerformed(evt);
            }
        });
        FormInput.add(BtnEdukasi);
        BtnEdukasi.setBounds(850, 942, 100, 23);

        BtnRencana.setForeground(new java.awt.Color(0, 0, 0));
        BtnRencana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnRencana.setMnemonic('2');
        BtnRencana.setText("Template");
        BtnRencana.setToolTipText("Alt+2");
        BtnRencana.setName("BtnRencana"); // NOI18N
        BtnRencana.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRencanaActionPerformed(evt);
            }
        });
        FormInput.add(BtnRencana);
        BtnRencana.setBounds(850, 970, 100, 23);

        BtnAlasan.setForeground(new java.awt.Color(0, 0, 0));
        BtnAlasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAlasan.setMnemonic('2');
        BtnAlasan.setText("Template");
        BtnAlasan.setToolTipText("Alt+2");
        BtnAlasan.setName("BtnAlasan"); // NOI18N
        BtnAlasan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAlasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlasanActionPerformed(evt);
            }
        });
        FormInput.add(BtnAlasan);
        BtnAlasan.setBounds(910, 1222, 100, 23);

        BtnPenyebab.setForeground(new java.awt.Color(0, 0, 0));
        BtnPenyebab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPenyebab.setMnemonic('2');
        BtnPenyebab.setText("Template");
        BtnPenyebab.setToolTipText("Alt+2");
        BtnPenyebab.setName("BtnPenyebab"); // NOI18N
        BtnPenyebab.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPenyebab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenyebabActionPerformed(evt);
            }
        });
        FormInput.add(BtnPenyebab);
        BtnPenyebab.setBounds(910, 1278, 100, 23);

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
        tbCPPT.getTableHeader().setReorderingAllowed(false);
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

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.BorderLayout());

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

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("<html><b>Catatan :</b><br>Perangkat (smartphone / tab) harus terhubung dengan wifi rumah sakit diruangan ini terlebih dulu.</html>");
        jLabel93.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel93.setName("jLabel93"); // NOI18N
        jLabel93.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        panelGlass11.add(jLabel93);
        jLabel93.setBounds(20, 262, 210, 60);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: TANDA TANGAN :.", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll5.setViewportView(LoadHTML1);

        panelGlass11.add(Scroll5);
        Scroll5.setBounds(12, 335, 260, 240);

        panelGlass10.add(panelGlass11, java.awt.BorderLayout.EAST);

        internalFrame3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Penanganan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-06-2026" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-06-2026" }));
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
            if (ChkJamKlr.isSelected() == true) {
                jamKeluar = cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem();
            } else {
                jamKeluar = "00:00:00";
            }
            
            if (ChkJamMeninggal.isSelected() == true) {
                jamMeninggal = cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem();
            } else {
                jamMeninggal = "00:00:00";
            }
            
            if (akses.getadmin() == true) {
                user = "-";
            } else {
                user = akses.getkode();
            }
            
            cekData();
            if (Sequel.menyimpantf("asesmen_medik_kebidanan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 106, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), Valid.SetTgl(tglPenanganan.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        cervi, rjp, defri, intu, vtp, dekom, balut, katet, ngt, infus, obat, TObat.getText(), tdkAda, paten, obsPar, cmbObstruksi.getSelectedItem().toString(),
                        obsTot, trauma, cmbTrauma.getSelectedItem().toString(), resiko, cmbResiko.getSelectedItem().toString(), benda, TBendaAsing.getText(),
                        cmbKesJalanNafas.getSelectedItem().toString(), cmbSpontan.getSelectedItem().toString(), cmbReguler.getSelectedItem().toString(),
                        cmbGerakanDada.getSelectedItem().toString(), cmbTipePernapasan.getSelectedItem().toString(), cmbKesPernapasan.getSelectedItem().toString(),
                        cmbNadi1.getSelectedItem().toString(), cmbNadi2.getSelectedItem().toString(), cmbKulit.getSelectedItem().toString(), cmbAkral1.getSelectedItem().toString(),
                        cmbAkral2.getSelectedItem().toString(), cmbCRT.getSelectedItem().toString(), cmbKesSirkulasi.getSelectedItem().toString(), TgcsE.getText(), TgcsV.getText(),
                        TgcsM.getText(), cmbPupil.getSelectedItem().toString(), TDiam_kanan.getText(), TDiam_kiri.getText(), TRef_kanan.getText(), TRef_kiri.getText(), TMeningeal.getText(),
                        cmbLater.getSelectedItem().toString(), defor, contu, penet, tender, swel, eksko, abras, burn, laser, tdkTampak, TAlergi.getText(), hipDulu, dmDulu, lainDulu,
                        TLainPenyakitDulu.getText(), hipKlg, dmKlg, janKlg, lainKlg, TLainPenyakitKlg.getText(), merokok, lainBiasa, TLainPenyakitBiasa.getText(), TAnamnesis.getText(),
                        Tdiagnosis.getText(), Ticd10.getText(), cmbRencana.getSelectedItem().toString(), Tterapi.getText(), Tedukasi.getText(), Trencana.getText(), nipPemberi,
                        Tnmpenerima.getText(), nipDokter, Valid.SetTgl(tglKeluar.getSelectedItem() + ""), jamKlr, jamKeluar, cmbRuangan.getSelectedItem().toString(), Tindikasi.getText(),
                        Tdipulangkan.getText(), Tdirujuk.getText(), TAlasanDirujuk.getText(), jamMening, jamMeninggal, Tpenyebab.getText(), Tku.getText(), Ttd.getText(), Thr.getText(),
                        Trr.getText(), Ttemp.getText(), Tspo.getText(), Tgcs.getText(), nipBidan, nipDpjp, user, Sequel.cariIsi("select now()"), ""
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Assesmen Medik Kebidanan", "Simpan");
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
            if (akses.getpic_igd() == true || akses.getadmin() == true) {
                hapus();
            } else {
                if (akses.getkode().equals(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 112).toString())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, data tidak bisa dihapus. Silahkan hubungi petugas PIC atau        \n" + 
                            Sequel.cariIsi("select nama from pegawai where nik='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 112).toString() + "'") + " (Petugas yg. menyimpan data).");
                    tampil();
                    emptTeks();
                    TabRawat.setSelectedIndex(1);
                }
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
                ganti();                
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                emptTeks();
                tampil();
                TabRawat.setSelectedIndex(1);
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
        WindowTemplate.dispose();
        WindowNomorDokumenRM.dispose();
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
            
            param.put("tglPenanganan", Valid.SetTglINDONESIA(Valid.SetTgl(tglPenanganan.getSelectedItem() + "")));
            param.put("pukul", cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + " Wita");
            
            if (ChkCervical.isSelected() == true) {
                param.put("cervi", "V");
            } else {
                param.put("cervi", "");
            }
            
            if (ChkRJP.isSelected() == true) {
                param.put("rjp", "V");
            } else {
                param.put("rjp", "");
            }
            
            if (ChkDefribilasi.isSelected() == true) {
                param.put("defri", "V");
            } else {
                param.put("defri", "");
            }
            
            if (ChkIntubasi.isSelected() == true) {
                param.put("intu", "V");
            } else {
                param.put("intu", "");
            }
            
            if (ChkVTP.isSelected() == true) {
                param.put("vtp", "V");
            } else {
                param.put("vtp", "");
            }
            
            if (ChkDekompresi.isSelected() == true) {
                param.put("dekom", "V");
            } else {
                param.put("dekom", "");
            }
            
            if (ChkBalut.isSelected() == true) {
                param.put("balut", "V");
            } else {
                param.put("balut", "");
            }
            
            if (ChkKateter.isSelected() == true) {
                param.put("kate", "V");
            } else {
                param.put("kate", "");
            }
            
            if (ChkNGT.isSelected() == true) {
                param.put("ngt", "V");
            } else {
                param.put("ngt", "");
            }
            
            if (ChkInfus.isSelected() == true) {
                param.put("infus", "V");
            } else {
                param.put("infus", "");
            }
            
            if (ChkObat.isSelected() == true) {
                param.put("obat", "V");
                if (TObat.getText().equals("")) {
                    param.put("ketObat", "Obat");
                } else {
                    param.put("ketObat", "Obat : " + TObat.getText());
                }
            } else {
                param.put("obat", "");
                param.put("ketObat", "Obat");
            }
            
            if (ChkTidak.isSelected() == true) {
                param.put("tdkAda", "V");
            } else {
                param.put("tdkAda", "");
            }
            
            if (ChkPaten.isSelected() == true) {
                param.put("paten", "V");
            } else {
                param.put("paten", "");
            }
            
            if (ChkObstruksiP.isSelected() == true) {
                param.put("obsPar", "V");
                if (cmbObstruksi.getSelectedIndex() == 0) {
                    param.put("jnsObs", "Obstruksi Partial");
                } else {
                    param.put("jnsObs", "Obstruksi Partial : " + cmbObstruksi.getSelectedItem().toString());
                }
            } else {
                param.put("obsPar", "");
                param.put("jnsObs", "Obstruksi Partial");
            }
            
            if (ChkObstruksiT.isSelected() == true) {
                param.put("obsTot", "V");
            } else {
                param.put("obsTot", "");
            }
            
            if (ChkTrauma.isSelected() == true) {
                param.put("trauma", "V");
                if (cmbTrauma.getSelectedIndex() == 0) {
                    param.put("jnsTrauma", "Trauma Jalan Nafas");
                } else {
                    param.put("jnsTrauma", "Trauma Jalan Nafas : " + cmbTrauma.getSelectedItem().toString());
                }
            } else {
                param.put("trauma", "");
                param.put("jnsTrauma", "Trauma Jalan Nafas");
            }
            
            if (ChkResiko.isSelected() == true) {
                param.put("resiko", "V");
                if (cmbResiko.getSelectedIndex() == 0) {
                    param.put("jnsResiko", "Resiko Aspirasi");
                } else {
                    param.put("jnsResiko", "Resiko Aspirasi : " + cmbResiko.getSelectedItem().toString());
                }
            } else {
                param.put("resiko", "");
                param.put("jnsResiko", "Resiko Aspirasi");
            }
            
            if (ChkBendaAsing.isSelected() == true) {
                param.put("benda", "V");
                if (TBendaAsing.getText().equals("")) {
                    param.put("nmBenda", "Benda Asing");
                } else {
                    param.put("nmBenda", "Benda Asing : " + TBendaAsing.getText());
                }
            } else {
                param.put("benda", "");
                param.put("nmBenda", "Benda Asing");
            }
            
            param.put("kesJlnNafas", "Kesimpulan Jalan Nafas : " + cmbKesJalanNafas.getSelectedItem().toString());
            param.put("pernapasan", cmbSpontan.getSelectedItem().toString() + " : " + cmbReguler.getSelectedItem().toString());
            param.put("gerakanDada", cmbGerakanDada.getSelectedItem().toString());
            param.put("tipePernapasan", cmbTipePernapasan.getSelectedItem().toString());
            param.put("kesPernapasan", "Kesimpulan Pernapasan : " + cmbKesPernapasan.getSelectedItem().toString());
            param.put("nadi", cmbNadi1.getSelectedItem().toString() + " : " + cmbNadi2.getSelectedItem().toString());
            param.put("kulit", cmbKulit.getSelectedItem().toString());
            param.put("akral", cmbAkral1.getSelectedItem().toString() + " : " + cmbAkral2.getSelectedItem().toString());
            param.put("crt", cmbCRT.getSelectedItem().toString());
            param.put("kesSirkulasi", "Kesimpulan Sirkulasi : " + cmbKesSirkulasi.getSelectedItem().toString());
            
            if (TgcsE.getText().equals("")) {
                param.put("gcsE", "E : ....");
            } else {
                param.put("gcsE", "E : " + TgcsE.getText());
            }
            
            if (TgcsV.getText().equals("")) {
                param.put("gcsV", "V : ....");
            } else {
                param.put("gcsV", "V : " + TgcsV.getText());
            }
            
            if (TgcsM.getText().equals("")) {
                param.put("gcsM", "M : ....");
            } else {
                param.put("gcsM", "M : " + TgcsM.getText());
            }
            
            param.put("pupil", cmbPupil.getSelectedItem().toString());
            
            if (TDiam_kanan.getText().equals("")) {
                param.put("diamKanan", "..... mm");
            } else {
                param.put("diamKanan", TDiam_kanan.getText() + " mm");
            }
            
            if (TDiam_kiri.getText().equals("")) {
                param.put("diamKiri", "..... mm");
            } else {
                param.put("diamKiri", TDiam_kiri.getText() + " mm");
            }
            
            if (TRef_kanan.getText().equals("")) {
                param.put("refKanan", ".....");
            } else {
                param.put("refKanan", TRef_kanan.getText());
            }
            
            if (TRef_kiri.getText().equals("")) {
                param.put("refKiri", ".....");
            } else {
                param.put("refKiri", TRef_kiri.getText());
            }
            
            if (TMeningeal.getText().equals("")) {
                param.put("mening", "........");
            } else {
                param.put("mening", TMeningeal.getText());
            }
            
            param.put("later", cmbLater.getSelectedItem().toString());
            
            if (ChkDeformitas.isSelected() == true) {
                param.put("defor", "V");
            } else {
                param.put("defor", "");
            }
            
            if (ChkContusio.isSelected() == true) {
                param.put("contu", "V");
            } else {
                param.put("contu", "");
            }
            
            if (ChkPenetrasi.isSelected() == true) {
                param.put("penet", "V");
            } else {
                param.put("penet", "");
            }
            
            if (ChkTenderness.isSelected() == true) {
                param.put("tender", "V");
            } else {
                param.put("tender", "");
            }
            
            if (ChkSwelling.isSelected() == true) {
                param.put("swell", "V");
            } else {
                param.put("swell", "");
            }
            
            if (ChkEkskoriasi.isSelected() == true) {
                param.put("eksko", "V");
            } else {
                param.put("eksko", "");
            }
            
            if (ChkAbrasi.isSelected() == true) {
                param.put("abra", "V");
            } else {
                param.put("abra", "");
            }
            
            if (ChkBurn.isSelected() == true) {
                param.put("burn", "V");
            } else {
                param.put("burn", "");
            }
            
            if (ChkLaserasi.isSelected() == true) {
                param.put("laser", "V");
            } else {
                param.put("laser", "");
            }
            
            if (ChkTdkTampk.isSelected() == true) {
                param.put("tdkTampak", "V");
            } else {
                param.put("tdkTampak", "");
            }
            
            if (TAlergi.getText().equals("")) {
                param.put("alergi", ".........");
            } else {
                param.put("alergi", TAlergi.getText());
            }
            
            if (ChkHipertensiDulu.isSelected() == true) {
                param.put("hipDulu", "V");
            } else {
                param.put("hipDulu", "");
            }
            
            if (ChkDMDulu.isSelected() == true) {
                param.put("dmDulu", "V");
            } else {
                param.put("dmDulu", "");
            }
            
            if (ChkLainyaDulu.isSelected() == true) {
                param.put("lainDulu", "V");
                if (TLainPenyakitDulu.getText().equals("")) {
                    param.put("ketLainDulu", "Lainnya : -");
                } else {
                    param.put("ketLainDulu", "Lainnya : " + TLainPenyakitDulu.getText());
                }
            } else {
                param.put("lainDulu", "");
                param.put("ketLainDulu", "Lainnya : -");
            }
            
            if (ChkHipertensiKlg.isSelected() == true) {
                param.put("hipKlg", "V");
            } else {
                param.put("hipKlg", "");
            }
            
            if (ChkDMKlg.isSelected() == true) {
                param.put("dmKlg", "V");
            } else {
                param.put("dmKlg", "");
            }
            
            if (ChkJantungKlg.isSelected() == true) {
                param.put("janKlg", "V");
            } else {
                param.put("janKlg", "");
            }
            
            if (ChkLainyaKlg.isSelected() == true) {
                param.put("lainKlg", "V");
                if (TLainPenyakitKlg.getText().equals("")) {
                    param.put("ketLainKlg", "Lainnya : -");
                } else {
                    param.put("ketLainKlg", "Lainnya : " + TLainPenyakitKlg.getText());
                }
            } else {
                param.put("lainKlg", "");
                param.put("ketLainKlg", "Lainnya : -");
            }
            
            if (ChkMerokok.isSelected() == true) {
                param.put("merokok", "V");
            } else {
                param.put("merokok", "");
            }
            
            if (ChkLainyaBiasa.isSelected() == true) {
                param.put("lainBiasa", "V");
                if (TLainPenyakitBiasa.getText().equals("")) {
                    param.put("ketLainBiasa", "Lainnya : -");
                } else {
                    param.put("ketLainBiasa", "Lainnya : " + TLainPenyakitBiasa.getText());
                }
            } else {
                param.put("lainBiasa", "");
                param.put("ketLainBiasa", "Lainnya : -");
            }
            
            if (TAnamnesis.getText().equals("")) {
                param.put("anam", ".....");
            } else {
                param.put("anam", TAnamnesis.getText());
            }
            
            if (Tdiagnosis.getText().equals("")) {
                param.put("diagnos", ".....");
            } else {
                param.put("diagnos", Tdiagnosis.getText());
            }
            
            if (Ticd10.getText().equals("")) {
                param.put("icd", ".....");
            } else {
                param.put("icd", Ticd10.getText());
            }
            
            param.put("rencanaInstruksi", cmbRencana.getSelectedItem().toString());
            
            if (Tterapi.getText().equals("")) {
                param.put("terapi", ".....");
            } else {
                param.put("terapi", Tterapi.getText());
            }
            
            if (Tedukasi.getText().equals("")) {
                param.put("edukasi", ".....");
            } else {
                param.put("edukasi", Tedukasi.getText());
            }
            
            if (Trencana.getText().equals("")) {
                param.put("rencAsuhan", ".....");
            } else {
                param.put("rencAsuhan", Trencana.getText());
            }
            
            param.put("ptgsPemberi", "(" + Tnmpemberi.getText() + ")");
            param.put("penerimaEdukasi", "(" + Tnmpenerima.getText() + ")");
            param.put("dktrPemberi", "(" + Tnmdokter.getText() + ")");
            param.put("tglKeluar", ": " + Valid.SetTglINDONESIA(Valid.SetTgl(tglKeluar.getSelectedItem() + "")));
            
            if (ChkJamKlr.isSelected() == true) {
                param.put("jamKlr", ": " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + " Wita");
            } else {
                param.put("jamKlr", ": -");
            }
            
            param.put("opname", ": " + cmbRuangan.getSelectedItem().toString());
            
            if (Tindikasi.getText().equals("")) {
                param.put("indikasi", ": .....");
            } else {
                param.put("indikasi", ": " + Tindikasi.getText());
            }
            
            if (Tdipulangkan.getText().equals("")) {
                param.put("dipulangkan", ": .....");
            } else {
                param.put("dipulangkan", ": " + Tdipulangkan.getText());
            }
            
            if (Tdirujuk.getText().equals("")) {
                param.put("dirujuk", ": .....");
            } else {
                param.put("dirujuk", ": " + Tdirujuk.getText());
            }
            
            if (TAlasanDirujuk.getText().equals("")) {
                param.put("alasan", ": .....");
            } else {
                param.put("alasan", ": " + TAlasanDirujuk.getText());
            }            
            
            if (ChkJamMeninggal.isSelected() == true) {
                param.put("jamMening", ": " + cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + " Wita");
            } else {
                param.put("jamMening", ": -");
            }
            
            if (Tpenyebab.getText().equals("")) {
                param.put("penyebab", ": .....");
            } else {
                param.put("penyebab", ": "+Tpenyebab.getText());
            }
            
            if (Tku.getText().equals("")) {
                param.put("ku", ".....");
            } else {
                param.put("ku", Tku.getText());
            }
            
            if (Ttd.getText().equals("")) {
                param.put("td", " : .....");
            } else {
                param.put("td", " : " + Ttd.getText());
            }
            
            if (Thr.getText().equals("")) {
                param.put("hr", " : .....");
            } else {
                param.put("hr", " : " + Thr.getText());
            }
            
            if (Trr.getText().equals("")) {
                param.put("rr", " : .....");
            } else {
                param.put("rr", " : " + Trr.getText());
            }
            
            if (Ttemp.getText().equals("")) {
                param.put("temp", " : .....");
            } else {
                param.put("temp", " : " + Ttemp.getText());
            }
            
            if (Tspo.getText().equals("")) {
                param.put("spo", " : .....");
            } else {
                param.put("spo", " : " + Tspo.getText());
            }
            
            if (Tgcs.getText().equals("")) {
                param.put("gcs", " : .....");
            } else {
                param.put("gcs", " : " + Tgcs.getText());
            }
            
            param.put("bidan", "(" + Tnmbidan.getText() + ")");
            param.put("dpjp", "(" + Tnmdpjp.getText() + ")");
            
            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isiPemberi = "", isiDokter = "", isiBidan = "", isiDpjp = "", tglSimpan = "", jamSimpan = "";
                tglSimpan = Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from asesmen_medik_kebidanan where no_rawat='" + TNoRw.getText() + "'");
                jamSimpan = Sequel.cariIsi("select time(waktu_simpan) from asesmen_medik_kebidanan where no_rawat='" + TNoRw.getText() + "'");
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));
                
                try {
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

                    param.put("gambarTtd", gambar);
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
                
                //pemberi edukasi/informasi
                if (nipPemberi.equals("") || nipPemberi.equals("-") || nipPemberi.equals("--")) {
                    param.put("lokasiQrPemberi", "");
                } else {
                    isiPemberi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Assesmen Medik Kebidanan", Tnmpemberi.getText() + " (Pemberi Edukasi/Informasi)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiPemberi, Sequel.cariFolderTte(), "QRTtePemberi.jpg", "select logo from setting");
                    param.put("lokasiQrPemberi", Sequel.cariFolderTte() + File.separator + "QRTtePemberi.jpg");
                }
                
                //dokter edukasi/informasi
                if (nipDokter.equals("") || nipDokter.equals("-") || nipDokter.equals("--")) {
                    param.put("lokasiQrDokter", "");
                } else {
                    isiDokter = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Assesmen Medik Kebidanan", Tnmdokter.getText() + " (Dokter)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiDokter, Sequel.cariFolderTte(), "QRTteDokter.jpg", "select logo from setting");
                    param.put("lokasiQrDokter", Sequel.cariFolderTte() + File.separator + "QRTteDokter.jpg");
                }
                
                //bidan
                if (nipBidan.equals("") || nipBidan.equals("-") || nipBidan.equals("--")) {
                    param.put("lokasiQrBidan", "");
                } else {
                    isiBidan = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Assesmen Medik Kebidanan", Tnmbidan.getText() + " (Bidan Yang Menyerahkan)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiBidan, Sequel.cariFolderTte(), "QRTteBidan.jpg", "select logo from setting");
                    param.put("lokasiQrBidan", Sequel.cariFolderTte() + File.separator + "QRTteBidan.jpg");
                }
                
                //dpjp
                if (nipDpjp.equals("") || nipDpjp.equals("-") || nipDpjp.equals("--")) {
                    param.put("lokasiQrDpjp", "");
                } else {
                    isiDpjp = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Assesmen Medik Kebidanan", Tnmdpjp.getText() + " (Mengetahui DPJP)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiDpjp, Sequel.cariFolderTte(), "QRTteDpjp.jpg", "select logo from setting");
                    param.put("lokasiQrDpjp", Sequel.cariFolderTte() + File.separator + "QRTteDpjp.jpg");
                }

                Valid.MyReport("rptCetakAsesmenMedikKebidananQr.jasper", "report", "::[ Assesmen Medik Kebidanan ]::",
                        "SELECT now() tanggal", param);
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            } else {
                Valid.MyReport("rptCetakAsesmenMedikKebidanan.jasper", "report", "::[ Assesmen Medik Kebidanan ]::",
                        "SELECT now() tanggal", param);
            }
            
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
            scrollKeAtas();
        } else if (TabRawat.getSelectedIndex() == 1) {
            ((RMAsesmenMedikKebidanan.Painter) gambarQR).setImage("");
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (Sequel.cariInteger("select count(-1) from asesmen_medik_kebidanan where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from asesmen_medik_kebidanan where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
            scrollKeAtas();
        }
        
        Sequel.cariIsiComboDB("SELECT distinct CASE WHEN nm_gedung IN ('AR-RAUDAH ATAS', 'AR-RAUDAH BAWAH') THEN 'AR-RAUDAH' ELSE nm_gedung END AS gedungnya "
                + "FROM bangsal WHERE status = '1' "
                + "AND nm_gedung NOT LIKE '%instalasi%' "
                + "AND nm_gedung NOT LIKE '%sdm%' "
                + "AND nm_gedung NOT LIKE '%ipsrs%' "
                + "AND nm_gedung NOT LIKE '%uang%' "
                + "AND nm_gedung NOT LIKE '%sanitasi%' "
                + "AND nm_gedung NOT LIKE '%inst.%' "
                + "AND nm_gedung NOT LIKE '%bid.%' "
                + "AND nm_gedung NOT LIKE '%unit%' "
                + "AND nm_gedung NOT LIKE '%bag.%' "
                + "AND nm_gedung NOT LIKE '%upm%' "
                + "AND nm_gedung <>'-' GROUP BY nm_gedung ORDER BY nm_gedung", cmbRuangan);
        tampil();
        
        ((RMAsesmenMedikKebidanan.Painter) gambarQR).setImage("");
        Sequel.cariIsiComboDB("select nm_dokumen from master_nomor_dokumen_erm where "
                + "status='aktif' and unit_pengguna='Instalasi' and ttd_keluarga_pasien='Ya' order by kode_erm", cmbRM);
        Sequel.queryu("DELETE FROM parameter_ttd_rme WHERE DATE(waktu_kirim) < CURDATE()");
    }//GEN-LAST:event_formWindowOpened

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

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMAsesmenMedikKebidanan");
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

    private void ChkObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkObatActionPerformed
        TObat.setText("");        
        if (ChkObat.isSelected() == true) {
            TObat.setEnabled(true);
            TObat.requestFocus();
        } else {
            TObat.setText("");
            TObat.setEnabled(false);
        }
    }//GEN-LAST:event_ChkObatActionPerformed

    private void ChkObstruksiPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkObstruksiPActionPerformed
        cmbObstruksi.setSelectedIndex(0);
        if (ChkObstruksiP.isSelected() == true) {
            cmbObstruksi.setEnabled(true);
            cmbObstruksi.requestFocus();
        } else {
            cmbObstruksi.setEnabled(false);
        }
    }//GEN-LAST:event_ChkObstruksiPActionPerformed

    private void ChkTraumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTraumaActionPerformed
        cmbTrauma.setSelectedIndex(0);
        if (ChkTrauma.isSelected() == true) {
            cmbTrauma.setEnabled(true);
            cmbTrauma.requestFocus();
        } else {
            cmbTrauma.setEnabled(false);
        }
    }//GEN-LAST:event_ChkTraumaActionPerformed

    private void ChkResikoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkResikoActionPerformed
        cmbResiko.setSelectedIndex(0);
        if (ChkResiko.isSelected() == true) {
            cmbResiko.setEnabled(true);
            cmbResiko.requestFocus();
        } else {
            cmbResiko.setEnabled(false);
        }
    }//GEN-LAST:event_ChkResikoActionPerformed

    private void ChkBendaAsingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkBendaAsingActionPerformed
        TBendaAsing.setText("");
        if (ChkBendaAsing.isSelected() == true) {
            TBendaAsing.setEnabled(true);
            TBendaAsing.requestFocus();
        } else {
            TBendaAsing.setEnabled(false);
        }
    }//GEN-LAST:event_ChkBendaAsingActionPerformed

    private void cmbSpontanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSpontanActionPerformed
        cmbReguler.setSelectedIndex(0);
        if (cmbSpontan.getSelectedIndex() == 0) {
            cmbReguler.setEnabled(false);
        } else {
            cmbReguler.setEnabled(true);
            cmbReguler.requestFocus();
        }
    }//GEN-LAST:event_cmbSpontanActionPerformed

    private void TgcsEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsEKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TgcsV.requestFocus();
        }
    }//GEN-LAST:event_TgcsEKeyPressed

    private void TgcsVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsVKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TgcsM.requestFocus();
        }
    }//GEN-LAST:event_TgcsVKeyPressed

    private void TgcsMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsMKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPupil.requestFocus();
        }
    }//GEN-LAST:event_TgcsMKeyPressed

    private void TDiam_kananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiam_kananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TDiam_kiri.requestFocus();
        }
    }//GEN-LAST:event_TDiam_kananKeyPressed

    private void TDiam_kiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiam_kiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TRef_kanan.requestFocus();
        }
    }//GEN-LAST:event_TDiam_kiriKeyPressed

    private void TRef_kananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRef_kananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TRef_kiri.requestFocus();
        }
    }//GEN-LAST:event_TRef_kananKeyPressed

    private void TRef_kiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRef_kiriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TMeningeal.requestFocus();
        }
    }//GEN-LAST:event_TRef_kiriKeyPressed

    private void TMeningealKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMeningealKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbLater.requestFocus();
        }
    }//GEN-LAST:event_TMeningealKeyPressed

    private void TAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlergiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ChkHipertensiDulu.requestFocus();
        }
    }//GEN-LAST:event_TAlergiKeyPressed

    private void TLainPenyakitDuluKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLainPenyakitDuluKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ChkHipertensiKlg.requestFocus();
        }
    }//GEN-LAST:event_TLainPenyakitDuluKeyPressed

    private void TLainPenyakitKlgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLainPenyakitKlgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ChkMerokok.requestFocus();
        }
    }//GEN-LAST:event_TLainPenyakitKlgKeyPressed

    private void TLainPenyakitBiasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLainPenyakitBiasaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TAnamnesis.requestFocus();
        }
    }//GEN-LAST:event_TLainPenyakitBiasaKeyPressed

    private void TAnamnesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAnamnesisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tdiagnosis.requestFocus();
        }
    }//GEN-LAST:event_TAnamnesisKeyPressed

    private void TdiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Ticd10.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosisKeyPressed

    private void Ticd10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ticd10KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbRencana.requestFocus();
        }
    }//GEN-LAST:event_Ticd10KeyPressed

    private void TterapiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TterapiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tedukasi.requestFocus();
        }
    }//GEN-LAST:event_TterapiKeyPressed

    private void TedukasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TedukasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trencana.requestFocus();
        }
    }//GEN-LAST:event_TedukasiKeyPressed

    private void TrencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrencanaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnPemberi.requestFocus();
        }
    }//GEN-LAST:event_TrencanaKeyPressed

    private void BtnPemberiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPemberiActionPerformed
        initPetugas();
        pilihan = 0;
        pilihan = 1;
        akses.setform("RMAsesmenMedikKebidanan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);        
        petugas.setVisible(true);
        petugas.toFront();
        petugas.requestFocus();
    }//GEN-LAST:event_BtnPemberiActionPerformed

    private void TnmpenerimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmpenerimaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnDokter.requestFocus();
        }
    }//GEN-LAST:event_TnmpenerimaKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        initDokter();
        pilihan = 0;
        pilihan = 3;
        akses.setform("RMAsesmenMedikKebidanan");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);        
        dokter.setVisible(true);
        dokter.toFront();
        dokter.requestFocus();
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void cmbRuanganMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuanganMouseReleased
        AutoCompleteDecorator.decorate(cmbRuangan);
    }//GEN-LAST:event_cmbRuanganMouseReleased

    private void TindikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindikasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdipulangkan.requestFocus();
        }
    }//GEN-LAST:event_TindikasiKeyPressed

    private void TdipulangkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdipulangkanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdirujuk.requestFocus();
        }
    }//GEN-LAST:event_TdipulangkanKeyPressed

    private void TdirujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdirujukKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TAlasanDirujuk.requestFocus();
        }
    }//GEN-LAST:event_TdirujukKeyPressed

    private void TAlasanDirujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlasanDirujukKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ChkJamMeninggal.requestFocus();
        }
    }//GEN-LAST:event_TAlasanDirujukKeyPressed

    private void cmbJam2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam2MouseReleased
        AutoCompleteDecorator.decorate(cmbJam2);
    }//GEN-LAST:event_cmbJam2MouseReleased

    private void cmbMnt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt2MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt2);
    }//GEN-LAST:event_cmbMnt2MouseReleased

    private void cmbDtk2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk2);
    }//GEN-LAST:event_cmbDtk2MouseReleased

    private void TpenyebabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpenyebabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tku.requestFocus();
        }
    }//GEN-LAST:event_TpenyebabKeyPressed

    private void TkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttd.requestFocus();
        }
    }//GEN-LAST:event_TkuKeyPressed

    private void TtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Thr.requestFocus();
        }
    }//GEN-LAST:event_TtdKeyPressed

    private void ThrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trr.requestFocus();
        }
    }//GEN-LAST:event_ThrKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttemp.requestFocus();
        }
    }//GEN-LAST:event_TrrKeyPressed

    private void TtempKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtempKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tspo.requestFocus();
        }
    }//GEN-LAST:event_TtempKeyPressed

    private void TspoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TspoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tgcs.requestFocus();
        }
    }//GEN-LAST:event_TspoKeyPressed

    private void TgcsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnBidan.requestFocus();
        }
    }//GEN-LAST:event_TgcsKeyPressed

    private void BtnBidanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBidanActionPerformed
        initPetugas();
        pilihan = 0;
        pilihan = 2;
        akses.setform("RMAsesmenMedikKebidanan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);        
        petugas.setVisible(true);
        petugas.toFront();
        petugas.requestFocus();
    }//GEN-LAST:event_BtnBidanActionPerformed

    private void BtnDpjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDpjpActionPerformed
        initDokter();
        pilihan = 0;
        pilihan = 4;
        akses.setform("RMAsesmenMedikKebidanan");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);        
        dokter.setVisible(true);
        dokter.toFront();
        dokter.requestFocus();
    }//GEN-LAST:event_BtnDpjpActionPerformed

    private void TObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ChkPaten.requestFocus();
        }
    }//GEN-LAST:event_TObatKeyPressed

    private void TBendaAsingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBendaAsingKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbKesJalanNafas.requestFocus();
        }
    }//GEN-LAST:event_TBendaAsingKeyPressed

    private void ChkLainyaDuluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkLainyaDuluActionPerformed
        TLainPenyakitDulu.setText("");
        if (ChkLainyaDulu.isSelected() == true) {
            TLainPenyakitDulu.setEnabled(true);
            TLainPenyakitDulu.requestFocus();
        } else {
            TLainPenyakitDulu.setEnabled(false);
        }
    }//GEN-LAST:event_ChkLainyaDuluActionPerformed

    private void ChkLainyaKlgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkLainyaKlgActionPerformed
        TLainPenyakitKlg.setText("");
        if (ChkLainyaKlg.isSelected() == true) {
            TLainPenyakitKlg.setEnabled(true);
            TLainPenyakitKlg.requestFocus();
        } else {
            TLainPenyakitKlg.setEnabled(false);
        }
    }//GEN-LAST:event_ChkLainyaKlgActionPerformed

    private void ChkLainyaBiasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkLainyaBiasaActionPerformed
        TLainPenyakitBiasa.setText("");
        if (ChkLainyaBiasa.isSelected() == true) {
            TLainPenyakitBiasa.setEnabled(true);
            TLainPenyakitBiasa.requestFocus();
        } else {
            TLainPenyakitBiasa.setEnabled(false);
        }
    }//GEN-LAST:event_ChkLainyaBiasaActionPerformed

    private void ChkJamKlrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJamKlrActionPerformed
        if (ChkJamKlr.isSelected() == true) {
            if (tbAsesmen.getSelectedRow() != -1) {
                cmbJam1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString().substring(0, 2));
                cmbMnt1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString().substring(3, 5));
                cmbDtk1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString().substring(6, 8));
            } else {
                cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
                cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
                cmbDtk1.setSelectedIndex(0);
            }
            
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
            cmbJam1.requestFocus();
        } else {
            cmbJam1.setSelectedIndex(0);
            cmbMnt1.setSelectedIndex(0);
            cmbDtk1.setSelectedIndex(0);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
        }
    }//GEN-LAST:event_ChkJamKlrActionPerformed

    private void ChkJamMeninggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJamMeninggalActionPerformed
        if (ChkJamMeninggal.isSelected() == true) {
            if (tbAsesmen.getSelectedRow() != -1) {
                cmbJam2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString().substring(0, 2));
                cmbMnt2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString().substring(3, 5));
                cmbDtk2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString().substring(6, 8));
            } else {
                cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
                cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
                cmbDtk2.setSelectedIndex(0);
            }
            
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
            cmbJam2.requestFocus();
        } else {
            cmbJam2.setSelectedIndex(0);
            cmbMnt2.setSelectedIndex(0);
            cmbDtk2.setSelectedIndex(0);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
        }
    }//GEN-LAST:event_ChkJamMeninggalActionPerformed

    private void BtnICDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnICDActionPerformed
        initICD();
        akses.setform("RMAsesmenMedikKebidanan");
        icd10.isCek();
        icd10.emptTeks();
        icd10.ChkInput.setSelected(false);
        icd10.isForm();
        icd10.setSize(983, internalFrame1.getHeight() - 40);
        icd10.setLocationRelativeTo(internalFrame1);        
        icd10.setVisible(true);
        icd10.toFront();
        icd10.requestFocus();
    }//GEN-LAST:event_BtnICDActionPerformed

    private void ChkSamaPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSamaPetugasActionPerformed
        if (ChkSamaPetugas.isSelected() == true) {
            nipBidan = nipPemberi;
            Tnmbidan.setText(Tnmpemberi.getText());
        } else {
            nipBidan = "-";
            Tnmbidan.setText("-");
        }
    }//GEN-LAST:event_ChkSamaPetugasActionPerformed

    private void ChkSamaDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSamaDokterActionPerformed
        if (ChkSamaDokter.isSelected() == true) {
            nipDpjp = nipDokter;
            Tnmdpjp.setText(Tnmdokter.getText());
        } else {
            nipDpjp = "-";
            Tnmdpjp.setText("-");
        }
    }//GEN-LAST:event_ChkSamaDokterActionPerformed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilTemplate();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnCopasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopasActionPerformed
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah data ini akan dipakai..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            copas();
            WindowTemplate.dispose();
        }
    }//GEN-LAST:event_BtnCopasActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowTemplate.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void tbTemplateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTemplateMouseClicked
        if(tabMode1.getRowCount() != 0) {
            try {
                if (tbTemplate.getSelectedRow() != -1) {
                    Ttemplate.setText(tbTemplate.getValueAt(tbTemplate.getSelectedRow(), 2).toString());
                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTemplateMouseClicked

    private void BtnAnamnesisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnamnesisActionPerformed
        pilihTemplate = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihTemplate = 1;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), 
                "::[ Data Template Anamnesis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);        
        WindowTemplate.setVisible(true);
        WindowTemplate.toFront();
        WindowTemplate.requestFocus();
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnAnamnesisActionPerformed

    private void BtnDiagnosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagnosisActionPerformed
        pilihTemplate = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihTemplate = 2;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), 
                "::[ Data Template Diagnosis Medis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);        
        WindowTemplate.setVisible(true);
        WindowTemplate.toFront();
        WindowTemplate.requestFocus();
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnDiagnosisActionPerformed

    private void BtnTerapiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTerapiActionPerformed
        pilihTemplate = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihTemplate = 3;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), 
                "::[ Data Template Terapi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);        
        WindowTemplate.setVisible(true);
        WindowTemplate.toFront();
        WindowTemplate.requestFocus();
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnTerapiActionPerformed

    private void BtnEdukasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdukasiActionPerformed
        pilihTemplate = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihTemplate = 4;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), 
                "::[ Data Template Informasi/Edukasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);        
        WindowTemplate.setVisible(true);
        WindowTemplate.toFront();
        WindowTemplate.requestFocus();
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnEdukasiActionPerformed

    private void BtnRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRencanaActionPerformed
        pilihTemplate = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihTemplate = 5;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), 
                "::[ Data Template Rencana Asuhan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);        
        WindowTemplate.setVisible(true);
        WindowTemplate.toFront();
        WindowTemplate.requestFocus();
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnRencanaActionPerformed

    private void BtnAlasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlasanActionPerformed
        pilihTemplate = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihTemplate = 6;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), 
                "::[ Data Template Alasan Dirujuk ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);        
        WindowTemplate.setVisible(true);
        WindowTemplate.toFront();
        WindowTemplate.requestFocus();
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnAlasanActionPerformed

    private void BtnPenyebabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenyebabActionPerformed
        pilihTemplate = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihTemplate = 7;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), 
                "::[ Data Template Penyebab ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);        
        WindowTemplate.setVisible(true);
        WindowTemplate.toFront();
        WindowTemplate.requestFocus();
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnPenyebabActionPerformed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMAsesmenMedikKebidanan");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

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
                                Sequel.mengedit("asesmen_medik_kebidanan", "no_rawat='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString() + "'",
                                    "id_file_nm_penerima_edukasi=''");
                                tampil();
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
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_MnHapusTtdActionPerformed

    private void MnBikinQrCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBikinQrCodeActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            ((RMAsesmenMedikKebidanan.Painter) gambarQR).setImage("");
            if (Sequel.cariInteger("select count(-1) from master_nomor_dokumen_erm where nm_dokumen like '%ASESMEN MEDIK KEBIDANAN%'") > 0) {
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
            Sequel.menyimpanQrTte("parameter_ttd_rme", "'" + idParameterTtd + "','" + usernya + "','" + pwdnya + "','" + TNoRw.getText() + "','" + TNoRM.getText() + "','"
                + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen='" + cmbRM.getSelectedItem().toString() + "'") + "',"
                + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

            try {
                ((RMAsesmenMedikKebidanan.Painter) gambarQR).setImage("");
                ResultSet hasil = koneksi.createStatement().executeQuery(
                    "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
                for (int I = 0; hasil.next(); I++) {
                    Blob blob = hasil.getBlob(1);
                    ((RMAsesmenMedikKebidanan.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
                        blob.getBytes(1, (int) (blob.length()))));
                blob.free();
            }

            BtnCloseIn2ActionPerformed(null);
            tampil();
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
            RMAsesmenMedikKebidanan dialog = new RMAsesmenMedikKebidanan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAlasan;
    private widget.Button BtnAll;
    private widget.Button BtnAnamnesis;
    private widget.Button BtnBatal;
    private widget.Button BtnBidan;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn2;
    private widget.Button BtnCopas;
    private widget.Button BtnDiagnosis;
    private widget.Button BtnDokter;
    private widget.Button BtnDpjp;
    private widget.Button BtnEdit;
    private widget.Button BtnEdukasi;
    private widget.Button BtnHapus;
    private widget.Button BtnICD;
    private widget.Button BtnKeluar;
    private widget.Button BtnPemberi;
    private widget.Button BtnPenyebab;
    private widget.Button BtnPrint;
    private widget.Button BtnRencana;
    private widget.Button BtnSimpan;
    private widget.Button BtnTampilkanQr;
    private widget.Button BtnTerapi;
    public widget.CekBox ChkAbrasi;
    public widget.CekBox ChkAccor;
    public widget.CekBox ChkBalut;
    public widget.CekBox ChkBendaAsing;
    public widget.CekBox ChkBurn;
    public widget.CekBox ChkCervical;
    public widget.CekBox ChkContusio;
    public widget.CekBox ChkDMDulu;
    public widget.CekBox ChkDMKlg;
    public widget.CekBox ChkDeformitas;
    public widget.CekBox ChkDefribilasi;
    public widget.CekBox ChkDekompresi;
    public widget.CekBox ChkEkskoriasi;
    public widget.CekBox ChkHipertensiDulu;
    public widget.CekBox ChkHipertensiKlg;
    public widget.CekBox ChkInfus;
    public widget.CekBox ChkIntubasi;
    public widget.CekBox ChkJamKlr;
    public widget.CekBox ChkJamMeninggal;
    public widget.CekBox ChkJantungKlg;
    public widget.CekBox ChkKateter;
    public widget.CekBox ChkLainyaBiasa;
    public widget.CekBox ChkLainyaDulu;
    public widget.CekBox ChkLainyaKlg;
    public widget.CekBox ChkLaserasi;
    public widget.CekBox ChkMerokok;
    public widget.CekBox ChkNGT;
    public widget.CekBox ChkObat;
    public widget.CekBox ChkObstruksiP;
    public widget.CekBox ChkObstruksiT;
    public widget.CekBox ChkPaten;
    public widget.CekBox ChkPenetrasi;
    public widget.CekBox ChkRJP;
    public widget.CekBox ChkResiko;
    public widget.CekBox ChkSamaDokter;
    public widget.CekBox ChkSamaPetugas;
    public widget.CekBox ChkSwelling;
    public widget.CekBox ChkTdkTampk;
    public widget.CekBox ChkTenderness;
    public widget.CekBox ChkTidak;
    public widget.CekBox ChkTrauma;
    public widget.CekBox ChkVTP;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMenu;
    private widget.Label LCount;
    private widget.editorpane LoadHTML1;
    private javax.swing.JMenuItem MnBikinQrCode;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHapusTtd;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.TextBox TAlasanDirujuk;
    private widget.TextBox TAlergi;
    private widget.TextArea TAnamnesis;
    private widget.TextBox TBendaAsing;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TDiam_kanan;
    private widget.TextBox TDiam_kiri;
    private widget.TextBox TLainPenyakitBiasa;
    private widget.TextBox TLainPenyakitDulu;
    private widget.TextBox TLainPenyakitKlg;
    private widget.TextBox TMeningeal;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TObat;
    private widget.TextBox TPasien;
    private widget.TextBox TRef_kanan;
    private widget.TextBox TRef_kiri;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextArea Tdiagnosis;
    private widget.TextBox Tdipulangkan;
    private widget.TextBox Tdirujuk;
    private widget.TextBox Tedukasi;
    private widget.TextBox Tgcs;
    private widget.TextBox TgcsE;
    private widget.TextBox TgcsM;
    private widget.TextBox TgcsV;
    private widget.TextArea Thasil;
    private widget.TextBox Thr;
    private widget.TextBox Ticd10;
    private widget.TextBox Tindikasi;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tku;
    private widget.TextBox Tnmbidan;
    private widget.TextBox Tnmdokter;
    private widget.TextBox Tnmdpjp;
    private widget.TextBox Tnmpemberi;
    private widget.TextBox Tnmpenerima;
    private widget.TextBox Tpenyebab;
    private widget.TextBox Trencana;
    private widget.TextBox TrgRawat;
    private widget.TextBox Trr;
    private widget.TextBox Tspo;
    private widget.TextBox Ttd;
    private widget.TextBox Ttemp;
    private widget.TextArea Ttemplate;
    private widget.TextArea Tterapi;
    private javax.swing.JDialog WindowNomorDokumenRM;
    private javax.swing.JDialog WindowTemplate;
    private widget.ComboBox cmbAkral1;
    private widget.ComboBox cmbAkral2;
    private widget.ComboBox cmbCRT;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbGerakanDada;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbKesJalanNafas;
    private widget.ComboBox cmbKesPernapasan;
    private widget.ComboBox cmbKesSirkulasi;
    private widget.ComboBox cmbKulit;
    private widget.ComboBox cmbLater;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbNadi1;
    private widget.ComboBox cmbNadi2;
    private widget.ComboBox cmbObstruksi;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbPupil;
    private widget.ComboBox cmbRM;
    private widget.ComboBox cmbReguler;
    private widget.ComboBox cmbRencana;
    private widget.ComboBox cmbResiko;
    private widget.ComboBox cmbRuangan;
    private widget.ComboBox cmbSpontan;
    private widget.ComboBox cmbTipePernapasan;
    private widget.ComboBox cmbTrauma;
    private java.awt.Canvas gambarQR;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
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
    private widget.Label jLabel13;
    private widget.Label jLabel19;
    private widget.Label jLabel193;
    private widget.Label jLabel194;
    private widget.Label jLabel21;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi6;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.Table tbAsesmen;
    private widget.Table tbCPPT;
    private widget.Table tbTemplate;
    private widget.Tanggal tglKeluar;
    private widget.Tanggal tglPenanganan;
    // End of variables declaration//GEN-END:variables

     private void tampil() {
         LoadHTML1.setText("");
         Valid.tabelKosong(tabMode);
         try {
             ps = koneksi.prepareStatement("select amk.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(amk.tgl_penanganan,'%d-%m-%Y') tglPenang, "
                     + "TIME_FORMAT(amk.pukul_penanganan,'%H:%i Wita') pukul, pg1.nama ptgsEdukasi, pg2.nama dktrEdukasi, pg3.nama nmbidan, pg4.nama nmdpjp "
                     + "from asesmen_medik_kebidanan amk inner join reg_periksa rp on rp.no_rawat=amk.no_rawat "
                     + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg1 on pg1.nik=amk.nip_pemberi "
                     + "inner join pegawai pg2 on pg2.nik=amk.nip_dokter inner join pegawai pg3 on pg3.nik=amk.nip_bidan "
                     + "inner join pegawai pg4 on pg4.nik=amk.nip_dpjp where "
                     + "amk.tgl_penanganan between ? and ? and amk.no_rawat like ? or "
                     + "amk.tgl_penanganan between ? and ? and p.no_rkm_medis like ? or "
                     + "amk.tgl_penanganan between ? and ? and p.nm_pasien like ? or "
                     + "amk.tgl_penanganan between ? and ? and amk.nm_penerima_edukasi like ? or "
                     + "amk.tgl_penanganan between ? and ? and pg1.nama like ? or "
                     + "amk.tgl_penanganan between ? and ? and pg2.nama like ? or "
                     + "amk.tgl_penanganan between ? and ? and pg3.nama like ? or "
                     + "amk.tgl_penanganan between ? and ? and pg4.nama like ? order by amk.waktu_simpan");
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
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText() + "%");
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglPenang"),
                        rs.getString("pukul"),
                        rs.getString("ruang_rawat"),
                        rs.getString("ptgsEdukasi"),
                        rs.getString("nm_penerima_edukasi"),
                        rs.getString("dktrEdukasi"),
                        rs.getString("nmbidan"),
                        rs.getString("nmdpjp"),
                        rs.getString("tgl_penanganan"),
                        rs.getString("pukul_penanganan"),
                        rs.getString("cervical_collar"),
                        rs.getString("rjp"),
                        rs.getString("defribrilasi"),
                        rs.getString("intubasi"),
                        rs.getString("vtp"),
                        rs.getString("dekompresi"),
                        rs.getString("balut_bidai"),
                        rs.getString("kateter_urin"),
                        rs.getString("ngt"),
                        rs.getString("infus"),
                        rs.getString("obat"),
                        rs.getString("ket_obat"),
                        rs.getString("tidak_ada"),
                        rs.getString("paten"),
                        rs.getString("obs_partial"),
                        rs.getString("jns_obs_partial"),
                        rs.getString("obs_total"),
                        rs.getString("trauma_jln_nafas"),
                        rs.getString("jns_trauma_jln_nafas"),
                        rs.getString("resiko_aspirasi"),
                        rs.getString("jns_resiko_aspirasi"),
                        rs.getString("benda_asing"),
                        rs.getString("ket_benda_asing"),
                        rs.getString("kes_jalan_nafas"),
                        rs.getString("pernafasan"),
                        rs.getString("jns_spontan"),
                        rs.getString("gerakan_dada"),
                        rs.getString("tipe_pernapasan"),
                        rs.getString("kes_pernapasan"),
                        rs.getString("nadi"),
                        rs.getString("jns_reguler"),
                        rs.getString("kulit_mukosa"),
                        rs.getString("akral"),
                        rs.getString("jns_akral"),
                        rs.getString("crt"),
                        rs.getString("kes_sirkulasi"),
                        rs.getString("gcs_e"),
                        rs.getString("gcs_v"),
                        rs.getString("gcs_m"),
                        rs.getString("pupil"),
                        rs.getString("diameter_kanan"),
                        rs.getString("diameter_kiri"),
                        rs.getString("ref_cahaya_kanan"),
                        rs.getString("ref_cahaya_kiri"),
                        rs.getString("meningeal_signs"),
                        rs.getString("lateralisasi"),
                        rs.getString("deformitas"),
                        rs.getString("contusio"),
                        rs.getString("penetrasi"),
                        rs.getString("tenderness"),
                        rs.getString("swelling"),
                        rs.getString("ekskoriasi"),
                        rs.getString("abrasi"),
                        rs.getString("burn"),
                        rs.getString("laserasi"),
                        rs.getString("tdk_tampak_jelas"),
                        rs.getString("alergi"),
                        rs.getString("rp_dahulu_hipertensi"),
                        rs.getString("rp_dahulu_dm"),
                        rs.getString("rp_dahulu_lainya"),
                        rs.getString("rp_dahulu_ket_lainya"),
                        rs.getString("rp_klg_hipertensi"),
                        rs.getString("rp_klg_dm"),
                        rs.getString("rp_klg_jantung"),
                        rs.getString("rp_klg_lainya"),
                        rs.getString("rp_klg_ket_lainya"),
                        rs.getString("merokok"),
                        rs.getString("kebiasaan_lainya"),
                        rs.getString("ket_kebiasaan_lainya"),
                        rs.getString("anamnesis"),
                        rs.getString("diagnosis_medis"),
                        rs.getString("icd_10"),
                        rs.getString("rencana_instruksi"),
                        rs.getString("terapi"),
                        rs.getString("diberikan_informasi_edukasi_ttg"),
                        rs.getString("rencana_asuhan_diharapkan"),
                        rs.getString("nip_pemberi"),
                        rs.getString("nm_penerima_edukasi"),
                        rs.getString("nip_dokter"),
                        rs.getString("tgl_keluar"),
                        rs.getString("cek_jam_keluar"),
                        rs.getString("jam_keluar"),
                        rs.getString("opname_diruangan"),
                        rs.getString("indikasi_masuk"),
                        rs.getString("dipulangkan"),
                        rs.getString("dirujuk_ke"),
                        rs.getString("alasan_dirujuk"),
                        rs.getString("cek_jam_meninggal"),
                        rs.getString("jam_meninggal"),
                        rs.getString("penyebab"),
                        rs.getString("ku"),
                        rs.getString("td"),
                        rs.getString("hr"),
                        rs.getString("rr"),
                        rs.getString("temp"),
                        rs.getString("spo2"),
                        rs.getString("gcs"),
                        rs.getString("nip_bidan"),
                        rs.getString("nip_dpjp"),
                        rs.getString("nip_penyimpan_data"),
                        rs.getString("waktu_simpan"),
                        rs.getString("id_file_nm_penerima_edukasi")
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
        tglPenanganan.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        ChkCervical.setSelected(false);
        ChkRJP.setSelected(false);
        ChkDefribilasi.setSelected(false);
        ChkIntubasi.setSelected(false);
        ChkVTP.setSelected(false);
        ChkDekompresi.setSelected(false);
        ChkBalut.setSelected(false);
        ChkKateter.setSelected(false);
        ChkNGT.setSelected(false);
        ChkInfus.setSelected(false);
        ChkObat.setSelected(false);
        ChkTidak.setSelected(false);        
        TObat.setText("");
        TObat.setEnabled(false);
        
        ChkPaten.setSelected(false);
        ChkObstruksiP.setSelected(false);
        cmbObstruksi.setSelectedIndex(0);
        cmbObstruksi.setEnabled(false);
        ChkObstruksiT.setSelected(false);
        ChkTrauma.setSelected(false);
        cmbTrauma.setSelectedIndex(0);
        cmbTrauma.setEnabled(false);
        ChkResiko.setSelected(false);
        cmbResiko.setSelectedIndex(0);
        cmbResiko.setEnabled(false);
        ChkBendaAsing.setSelected(false);
        TBendaAsing.setText("");
        TBendaAsing.setEnabled(false);
        cmbKesJalanNafas.setSelectedIndex(0);
        
        cmbSpontan.setSelectedIndex(0);
        cmbReguler.setSelectedIndex(0);
        cmbReguler.setEnabled(false);
        cmbGerakanDada.setSelectedIndex(0);
        cmbTipePernapasan.setSelectedIndex(0);
        cmbKesPernapasan.setSelectedIndex(0);
        
        cmbNadi1.setSelectedIndex(0);
        cmbNadi2.setSelectedIndex(0);
        cmbKulit.setSelectedIndex(0);
        cmbAkral1.setSelectedIndex(0);
        cmbAkral2.setSelectedIndex(0);
        cmbCRT.setSelectedIndex(0);
        cmbKesSirkulasi.setSelectedIndex(0);
        
        TgcsE.setText("");
        TgcsV.setText("");
        TgcsM.setText("");
        cmbPupil.setSelectedIndex(0);
        TDiam_kanan.setText("");
        TDiam_kiri.setText("");
        TRef_kanan.setText("");
        TRef_kiri.setText("");
        TMeningeal.setText("");
        cmbLater.setSelectedIndex(0);
        
        ChkDeformitas.setSelected(false);
        ChkContusio.setSelected(false);
        ChkPenetrasi.setSelected(false);
        ChkTenderness.setSelected(false);
        ChkSwelling.setSelected(false);
        ChkEkskoriasi.setSelected(false);
        ChkAbrasi.setSelected(false);
        ChkBurn.setSelected(false);
        ChkLaserasi.setSelected(false);
        ChkTdkTampk.setSelected(false);
        
        TAlergi.setText("");
        ChkHipertensiDulu.setSelected(false);
        ChkDMDulu.setSelected(false);
        ChkLainyaDulu.setSelected(false);
        TLainPenyakitDulu.setText("");
        TLainPenyakitDulu.setEnabled(false);
        ChkHipertensiKlg.setSelected(false);
        ChkDMKlg.setSelected(false);
        ChkJantungKlg.setSelected(false);
        ChkLainyaKlg.setSelected(false);
        TLainPenyakitKlg.setText("");
        TLainPenyakitKlg.setEnabled(false);
        ChkMerokok.setSelected(false);
        ChkLainyaBiasa.setSelected(false);
        TLainPenyakitBiasa.setText("");
        TLainPenyakitBiasa.setEnabled(false);
        
        TAnamnesis.setText("");
        Tdiagnosis.setText("");
        Ticd10.setText("");
        cmbRencana.setSelectedIndex(0);
        Tterapi.setText("");
        Tedukasi.setText("");
        Trencana.setText("");
        nipPemberi = "-";
        Tnmpemberi.setText("-");
        nipDokter = "-";
        Tnmdokter.setText("-");
        Tnmpenerima.setText("");
        
        tglKeluar.setDate(new Date());
        ChkJamKlr.setSelected(false);
        cmbJam1.setSelectedIndex(0);
        cmbMnt1.setSelectedIndex(0);
        cmbDtk1.setSelectedIndex(0);
        cmbJam1.setEnabled(false);
        cmbMnt1.setEnabled(false);
        cmbDtk1.setEnabled(false);
        cmbRuangan.setSelectedIndex(0);
        Tindikasi.setText("");
        Tdipulangkan.setText("");
        Tdirujuk.setText("");
        TAlasanDirujuk.setText("");
        ChkJamMeninggal.setSelected(false);
        cmbJam2.setSelectedIndex(0);
        cmbMnt2.setSelectedIndex(0);
        cmbDtk2.setSelectedIndex(0);
        cmbJam2.setEnabled(false);
        cmbMnt2.setEnabled(false);
        cmbDtk2.setEnabled(false);
        Tpenyebab.setText("");
        Tku.setText("");
        Ttd.setText("");
        Thr.setText("");
        Trr.setText("");
        Ttemp.setText("");
        Tspo.setText("");
        Tgcs.setText("");
        nipBidan = "-";
        Tnmbidan.setText("-");
        nipDpjp = "-";
        Tnmdpjp.setText("-");
        
        ChkSamaPetugas.setSelected(false);
        ChkSamaDokter.setSelected(false);
        ChkAccor.setSelected(false);
        LoadHTML1.setText("");
        isMenu();
    }

    private void getData() {
        variabelBersih();
        if (tbAsesmen.getSelectedRow() != -1) {
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 2).toString());
            Valid.SetTgl(tglPenanganan, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString());
            cmbJam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString().substring(6, 8));
            TrgRawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 5).toString());
            cervi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString();
            rjp = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString();
            defri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString();
            intu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString();
            vtp = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString();
            dekom = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString();
            balut = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 19).toString();
            katet = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString();
            ngt = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString();
            infus = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString();
            obat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString();
            TObat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString());
            tdkAda = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString();
            paten = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString();
            obsPar = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString();
            cmbObstruksi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString());
            obsTot = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString();
            trauma = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString();
            cmbTrauma.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString());
            resiko = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 32).toString();
            cmbResiko.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 33).toString());
            benda = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString();
            TBendaAsing.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString());
            cmbKesJalanNafas.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString());
            cmbSpontan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString());
            cmbReguler.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString());
            cmbGerakanDada.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString());
            cmbTipePernapasan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString());
            cmbKesPernapasan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString());
            cmbNadi1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString());
            cmbNadi2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString());
            cmbKulit.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString());
            cmbAkral1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString());
            cmbAkral2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString());
            cmbCRT.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString());
            cmbKesSirkulasi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString());
            TgcsE.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString());
            TgcsV.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString());
            TgcsM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString());
            cmbPupil.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString());
            TDiam_kanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString());
            TDiam_kiri.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString());
            TRef_kanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString());
            TRef_kiri.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString());
            TMeningeal.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString());
            cmbLater.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString());
            defor = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString();
            contu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString();
            penet = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString();
            tender = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 62).toString();
            swel = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString();
            eksko = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 64).toString();
            abras = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString();
            burn = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 66).toString();
            laser = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString();
            tdkTampak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString();
            TAlergi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString());            
            hipDulu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString();
            dmDulu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString();
            lainDulu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString();
            TLainPenyakitDulu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString());            
            hipKlg = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString();
            dmKlg = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString();
            janKlg = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString();
            lainKlg = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString();
            TLainPenyakitKlg.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString());            
            merokok = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 79).toString();
            lainBiasa = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 80).toString();
            TLainPenyakitBiasa.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 81).toString());            
            TAnamnesis.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 82).toString());
            Tdiagnosis.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 83).toString());
            Ticd10.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 84).toString());
            cmbRencana.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 85).toString());
            Tterapi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 86).toString());            
            Tedukasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 87).toString());
            Trencana.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 88).toString());
            nipPemberi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 89).toString();
            Tnmpemberi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 6).toString());
            Tnmpenerima.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 90).toString());
            nipDokter = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 91).toString();
            Tnmdokter.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 8).toString());
            Valid.SetTgl(tglKeluar, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 92).toString());
            jamKlr = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 93).toString();
            cmbJam1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString().substring(6, 8));
            cmbRuangan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 95).toString());            
            Tindikasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 96).toString());
            Tdipulangkan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 97).toString());
            Tdirujuk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 98).toString());
            TAlasanDirujuk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 99).toString());
            jamMening = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 100).toString();
            cmbJam2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString().substring(0, 2));
            cmbMnt2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString().substring(3, 5));
            cmbDtk2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString().substring(6, 8));
            Tpenyebab.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 102).toString());            
            Tku.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 103).toString());
            Ttd.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 104).toString());
            Thr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 105).toString());
            Trr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 106).toString());
            Ttemp.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 107).toString());            
            Tspo.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 108).toString());
            Tgcs.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 109).toString());
            nipBidan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 110).toString();
            Tnmbidan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 9).toString());
            nipDpjp = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 111).toString();
            Tnmdpjp.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString());
            idFileTtd = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 114).toString();
            dataCek();
            tampilTTD();
        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement("SELECT rp.no_rkm_medis, p.nm_pasien, rp.tgl_registrasi, rp.jam_reg, if(tp.td is null,'',tp.td) td, "
                    + "if(tp.nadi is null,'',tp.nadi) hr, if(tp.napas is null,'',tp.napas) rr, if(tp.temperatur is null,'',tp.temperatur) temp, "
                    + "if(tp.saturasi is null,'',tp.saturasi) spo2, ifnull(r.rujuk_ke, '') rujuk_ke, ifnull(r.keterangan, '') ket_rujuk, ifnull(pm.jam, '00:00:00') jam_mati "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis left join triase_ponek tp on tp.no_rawat=rp.no_rawat "
                    + "LEFT JOIN rujuk r ON r.no_rawat = rp.no_rawat LEFT JOIN pasien_mati pm ON pm.no_rkm_medis = rp.no_rkm_medis WHERE rp.no_rawat = ?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    Valid.SetTgl(tglPenanganan, rs.getString("tgl_registrasi"));
                    cmbJam.setSelectedItem(rs.getString("jam_reg").toString().substring(0, 2));
                    cmbMnt.setSelectedItem(rs.getString("jam_reg").toString().substring(3, 5));
                    cmbDtk.setSelectedItem(rs.getString("jam_reg").toString().substring(6, 8));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    
                    Tdirujuk.setText(rs.getString("rujuk_ke"));
                    TAlasanDirujuk.setText(rs.getString("ket_rujuk"));                    
                    Ttd.setText(rs.getString("td"));
                    Thr.setText(rs.getString("hr"));
                    Trr.setText(rs.getString("rr"));
                    Ttemp.setText(rs.getString("temp"));
                    Tspo.setText(rs.getString("spo2"));
                  
                    if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + rs.getString("no_rkm_medis") + "'") == 0) {
                        ChkJamMeninggal.setSelected(false);
                        cmbJam2.setSelectedIndex(0);
                        cmbMnt2.setSelectedIndex(0);
                        cmbDtk2.setSelectedIndex(0);

                        cmbJam2.setEnabled(false);
                        cmbMnt2.setEnabled(false);
                        cmbDtk2.setEnabled(false);
                    } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + rs.getString("no_rkm_medis") + "'") > 0) {
                        ChkJamMeninggal.setSelected(true);
                        cmbJam2.setSelectedItem(rs.getString("jam_mati").toString().substring(0, 2));
                        cmbMnt2.setSelectedItem(rs.getString("jam_mati").toString().substring(3, 5));
                        cmbDtk2.setSelectedItem(rs.getString("jam_mati").toString().substring(6, 8));

                        cmbJam2.setEnabled(true);
                        cmbMnt2.setEnabled(true);
                        cmbDtk2.setEnabled(true);
                    }
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
    
    public void setData(String norwt, String rgrawat) {
        TNoRw.setText(norwt);
        TrgRawat.setText(rgrawat);
        TCari.setText(norwt);
        DTPCari2.setDate(new Date());        
        isRawat();
    }    
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getresep_dokter());
        BtnHapus.setEnabled(akses.getresep_dokter());
        BtnEdit.setEnabled(akses.getresep_dokter());
        BtnPrint.setEnabled(akses.getresep_dokter());
        
        if (akses.getadmin() == false) {
            nipDokter = akses.getkode();
            nipDpjp = akses.getkode();
            Tnmdokter.setText(Sequel.cariIsi("SELECT COALESCE((SELECT nm_dokter FROM dokter WHERE kd_dokter='" + nipDokter + "' LIMIT 1),'-')"));
            Tnmdpjp.setText(Sequel.cariIsi("SELECT COALESCE((SELECT nm_dokter FROM dokter WHERE kd_dokter='" + nipDpjp + "' LIMIT 1),'-')"));
        }     
    }

    private void ganti() {
        if (ChkJamKlr.isSelected() == true) {
            jamKeluar = cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem();
        } else {
            jamKeluar = "00:00:00";
        }

        if (ChkJamMeninggal.isSelected() == true) {
            jamMeninggal = cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem();
        } else {
            jamMeninggal = "00:00:00";
        }

        cekData();
        if (Sequel.mengedittf("asesmen_medik_kebidanan", "no_rawat=?", "tgl_penanganan=?, pukul_penanganan=?, cervical_collar=?, rjp=?, defribrilasi=?, intubasi=?, vtp=?, "
                + "dekompresi=?, balut_bidai=?, kateter_urin=?, ngt=?, infus=?, obat=?, ket_obat=?, tidak_ada=?, paten=?, obs_partial=?, jns_obs_partial=?, obs_total=?, "
                + "trauma_jln_nafas=?, jns_trauma_jln_nafas=?, resiko_aspirasi=?, jns_resiko_aspirasi=?, benda_asing=?, ket_benda_asing=?, kes_jalan_nafas=?, pernafasan=?, "
                + "jns_spontan=?, gerakan_dada=?, tipe_pernapasan=?, kes_pernapasan=?, nadi=?, jns_reguler=?, kulit_mukosa=?, akral=?, jns_akral=?, crt=?, kes_sirkulasi=?, "
                + "gcs_e=?, gcs_v=?, gcs_m=?, pupil=?, diameter_kanan=?, diameter_kiri=?, ref_cahaya_kanan=?, ref_cahaya_kiri=?, meningeal_signs=?, lateralisasi=?, deformitas=?, "
                + "contusio=?, penetrasi=?, tenderness=?, swelling=?, ekskoriasi=?, abrasi=?, burn=?, laserasi=?, tdk_tampak_jelas=?, alergi=?, rp_dahulu_hipertensi=?, rp_dahulu_dm=?, "
                + "rp_dahulu_lainya=?, rp_dahulu_ket_lainya=?, rp_klg_hipertensi=?, rp_klg_dm=?, rp_klg_jantung=?, rp_klg_lainya=?, rp_klg_ket_lainya=?, merokok=?, kebiasaan_lainya=?, "
                + "ket_kebiasaan_lainya=?, anamnesis=?, diagnosis_medis=?, icd_10=?, rencana_instruksi=?, terapi=?, diberikan_informasi_edukasi_ttg=?, rencana_asuhan_diharapkan=?, "
                + "nip_pemberi=?, nm_penerima_edukasi=?, nip_dokter=?, tgl_keluar=?, cek_jam_keluar=?, jam_keluar=?, opname_diruangan=?, indikasi_masuk=?, dipulangkan=?, dirujuk_ke=?, "
                + "alasan_dirujuk=?, cek_jam_meninggal=?, jam_meninggal=?, penyebab=?, ku=?, td=?, hr=?, rr=?, temp=?, spo2=?, gcs=?, nip_bidan=?, nip_dpjp=?", 102, new String[]{
                    Valid.SetTgl(tglPenanganan.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                    cervi, rjp, defri, intu, vtp, dekom, balut, katet, ngt, infus, obat, TObat.getText(), tdkAda, paten, obsPar, cmbObstruksi.getSelectedItem().toString(),
                    obsTot, trauma, cmbTrauma.getSelectedItem().toString(), resiko, cmbResiko.getSelectedItem().toString(), benda, TBendaAsing.getText(),
                    cmbKesJalanNafas.getSelectedItem().toString(), cmbSpontan.getSelectedItem().toString(), cmbReguler.getSelectedItem().toString(),
                    cmbGerakanDada.getSelectedItem().toString(), cmbTipePernapasan.getSelectedItem().toString(), cmbKesPernapasan.getSelectedItem().toString(),
                    cmbNadi1.getSelectedItem().toString(), cmbNadi2.getSelectedItem().toString(), cmbKulit.getSelectedItem().toString(), cmbAkral1.getSelectedItem().toString(),
                    cmbAkral2.getSelectedItem().toString(), cmbCRT.getSelectedItem().toString(), cmbKesSirkulasi.getSelectedItem().toString(), TgcsE.getText(), TgcsV.getText(),
                    TgcsM.getText(), cmbPupil.getSelectedItem().toString(), TDiam_kanan.getText(), TDiam_kiri.getText(), TRef_kanan.getText(), TRef_kiri.getText(), TMeningeal.getText(),
                    cmbLater.getSelectedItem().toString(), defor, contu, penet, tender, swel, eksko, abras, burn, laser, tdkTampak, TAlergi.getText(), hipDulu, dmDulu, lainDulu,
                    TLainPenyakitDulu.getText(), hipKlg, dmKlg, janKlg, lainKlg, TLainPenyakitKlg.getText(), merokok, lainBiasa, TLainPenyakitBiasa.getText(), TAnamnesis.getText(),
                    Tdiagnosis.getText(), Ticd10.getText(), cmbRencana.getSelectedItem().toString(), Tterapi.getText(), Tedukasi.getText(), Trencana.getText(), nipPemberi,
                    Tnmpenerima.getText(), nipDokter, Valid.SetTgl(tglKeluar.getSelectedItem() + ""), jamKlr, jamKeluar, cmbRuangan.getSelectedItem().toString(), Tindikasi.getText(),
                    Tdipulangkan.getText(), Tdirujuk.getText(), TAlasanDirujuk.getText(), jamMening, jamMeninggal, Tpenyebab.getText(), Tku.getText(), Ttd.getText(), Thr.getText(),
                    Trr.getText(), Ttemp.getText(), Tspo.getText(), Tgcs.getText(), nipBidan, nipDpjp,
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                }) == true) {

            Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Assesmen Medik Kebidanan", "Ganti");
            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
            TabRawat.setSelectedIndex(1);
        }
    }
    
    private void cekData() {
        if (ChkCervical.isSelected() == true) {
            cervi = "ya";
        } else {
            cervi = "tidak";
        }
        
        if (ChkRJP.isSelected() == true) {
            rjp = "ya";
        } else {
            rjp = "tidak";
        }
        
        if (ChkDefribilasi.isSelected() == true) {
            defri = "ya";
        } else {
            defri = "tidak";
        }
        
        if (ChkIntubasi.isSelected() == true) {
            intu = "ya";
        } else {
            intu = "tidak";
        }
        
        if (ChkVTP.isSelected() == true) {
            vtp = "ya";
        } else {
            vtp = "tidak";
        }
        
        if (ChkDekompresi.isSelected() == true) {
            dekom = "ya";
        } else {
            dekom = "tidak";
        }
        
        if (ChkBalut.isSelected() == true) {
            balut = "ya";
        } else {
            balut = "tidak";
        }
        
        if (ChkKateter.isSelected() == true) {
            katet = "ya";
        } else {
            katet = "tidak";
        }
        
        if (ChkNGT.isSelected() == true) {
            ngt = "ya";
        } else {
            ngt = "tidak";
        }
        
        if (ChkInfus.isSelected() == true) {
            infus = "ya";
        } else {
            infus = "tidak";
        }
        
        if (ChkObat.isSelected() == true) {
            obat = "ya";
        } else {
            obat = "tidak";
        }
        
        if (ChkTidak.isSelected() == true) {
            tdkAda = "ya";
        } else {
            tdkAda = "tidak";
        }
        
        if (ChkPaten.isSelected() == true) {
            paten = "ya";
        } else {
            paten = "tidak";
        }
        
        if (ChkObstruksiP.isSelected() == true) {
            obsPar = "ya";
        } else {
            obsPar = "tidak";
        }
        
        if (ChkObstruksiT.isSelected() == true) {
            obsTot = "ya";
        } else {
            obsTot = "tidak";
        }
        
        if (ChkTrauma.isSelected() == true) {
            trauma = "ya";
        } else {
            trauma = "tidak";
        }
        
        if (ChkResiko.isSelected() == true) {
            resiko = "ya";
        } else {
            resiko = "tidak";
        }
        
        if (ChkBendaAsing.isSelected() == true) {
            benda = "ya";
        } else {
            benda = "tidak";
        }
        
        if (ChkDeformitas.isSelected() == true) {
            defor = "ya";
        } else {
            defor = "tidak";
        }
        
        if (ChkContusio.isSelected() == true) {
            contu = "ya";
        } else {
            contu = "tidak";
        }
        
        if (ChkPenetrasi.isSelected() == true) {
            penet = "ya";
        } else {
            penet = "tidak";
        }
        
        if (ChkTenderness.isSelected() == true) {
            tender = "ya";
        } else {
            tender = "tidak";
        }
        
        if (ChkSwelling.isSelected() == true) {
            swel = "ya";
        } else {
            swel = "tidak";
        }
        
        if (ChkEkskoriasi.isSelected() == true) {
            eksko = "ya";
        } else {
            eksko = "tidak";
        }
        
        if (ChkAbrasi.isSelected() == true) {
            abras = "ya";
        } else {
            abras = "tidak";
        }
        
        if (ChkBurn.isSelected() == true) {
            burn = "ya";
        } else {
            burn = "tidak";
        }
        
        if (ChkLaserasi.isSelected() == true) {
            laser = "ya";
        } else {
            laser = "tidak";
        }
        
        if (ChkTdkTampk.isSelected() == true) {
            tdkTampak = "ya";
        } else {
            tdkTampak = "tidak";
        }
        
        if (ChkHipertensiDulu.isSelected() == true) {
            hipDulu = "ya";
        } else {
            hipDulu = "tidak";
        }
        
        if (ChkDMDulu.isSelected() == true) {
            dmDulu = "ya";
        } else {
            dmDulu = "tidak";
        }
        
        if (ChkLainyaDulu.isSelected() == true) {
            lainDulu = "ya";
        } else {
            lainDulu = "tidak";
        }
        
        if (ChkHipertensiKlg.isSelected() == true) {
            hipKlg = "ya";
        } else {
            hipKlg = "tidak";
        }
        
        if (ChkDMKlg.isSelected() == true) {
            dmKlg = "ya";
        } else {
            dmKlg = "tidak";
        }
        
        if (ChkJantungKlg.isSelected() == true) {
            janKlg = "ya";
        } else {
            janKlg = "tidak";
        }
        
        if (ChkLainyaKlg.isSelected() == true) {
            lainKlg = "ya";
        } else {
            lainKlg = "tidak";
        }
        
        if (ChkMerokok.isSelected() == true) {
            merokok = "ya";
        } else {
            merokok = "tidak";
        }
        
        if (ChkLainyaBiasa.isSelected() == true) {
            lainBiasa = "ya";
        } else {
            lainBiasa = "tidak";
        }
        
        if (ChkJamKlr.isSelected() == true) {
            jamKlr = "ya";
        } else {
            jamKlr = "tidak";
        }
        
        if (ChkJamMeninggal.isSelected() == true) {
            jamMening = "ya";
        } else {
            jamMening = "tidak";
        }
    }
    
    private void dataCek() {
        if (cervi.equals("ya")) {
            ChkCervical.setSelected(true);
        } else {
            ChkCervical.setSelected(false);
        }
        
        if (rjp.equals("ya")) {
            ChkRJP.setSelected(true);
        } else {
            ChkRJP.setSelected(false);
        }
        
        if (defri.equals("ya")) {
            ChkDefribilasi.setSelected(true);
        } else {
            ChkDefribilasi.setSelected(false);
        }
        
        if (intu.equals("ya")) {
            ChkIntubasi.setSelected(true);
        } else {
            ChkIntubasi.setSelected(false);
        }
        
        if (vtp.equals("ya")) {
            ChkVTP.setSelected(true);
        } else {
            ChkVTP.setSelected(false);
        }
        
        if (dekom.equals("ya")) {
            ChkDekompresi.setSelected(true);
        } else {
            ChkDekompresi.setSelected(false);
        }
        
        if (balut.equals("ya")) {
            ChkBalut.setSelected(true);
        } else {
            ChkBalut.setSelected(false);
        }
        
        if (katet.equals("ya")) {
            ChkKateter.setSelected(true);
        } else {
            ChkKateter.setSelected(false);
        }
        
        if (ngt.equals("ya")) {
            ChkNGT.setSelected(true);
        } else {
            ChkNGT.setSelected(false);
        }
        
        if (infus.equals("ya")) {
            ChkInfus.setSelected(true);
        } else {
            ChkInfus.setSelected(false);
        }
        
        if (obat.equals("ya")) {
            ChkObat.setSelected(true);
            TObat.setEnabled(true);
        } else {
            ChkObat.setSelected(false);
            TObat.setEnabled(false);
        }
        
        if (tdkAda.equals("ya")) {
            ChkTidak.setSelected(true);
        } else {
            ChkTidak.setSelected(false);
        }
        
        if (paten.equals("ya")) {
            ChkPaten.setSelected(true);
        } else {
            ChkPaten.setSelected(false);
        }
        
        if (obsPar.equals("ya")) {
            ChkObstruksiP.setSelected(true);
            cmbObstruksi.setEnabled(true);
        } else {
            ChkObstruksiP.setSelected(false);
            cmbObstruksi.setEnabled(false);
        }
        
        if (obsTot.equals("ya")) {
            ChkObstruksiT.setSelected(true);
        } else {
            ChkObstruksiT.setSelected(false);
        }
        
        if (trauma.equals("ya")) {
            ChkTrauma.setSelected(true);
            cmbTrauma.setEnabled(true);
        } else {
            ChkTrauma.setSelected(false);
            cmbTrauma.setEnabled(false);
        }
        
        if (resiko.equals("ya")) {
            ChkResiko.setSelected(true);
            cmbResiko.setEnabled(true);
        } else {
            ChkResiko.setSelected(false);
            cmbResiko.setEnabled(false);
        }
        
        if (benda.equals("ya")) {
            ChkBendaAsing.setSelected(true);
            TBendaAsing.setEnabled(true);
        } else {
            ChkBendaAsing.setSelected(false);
            TBendaAsing.setEnabled(false);
        }
        
        if (cmbSpontan.getSelectedIndex() == 0) {
            cmbReguler.setEnabled(false);
        } else {
            cmbReguler.setEnabled(true);
        }
        
        if (defor.equals("ya")) {
            ChkDeformitas.setSelected(true);
        } else {
            ChkDeformitas.setSelected(false);
        }
        
        if (contu.equals("ya")) {
            ChkContusio.setSelected(true);
        } else {
            ChkContusio.setSelected(false);
        }
        
        if (penet.equals("ya")) {
            ChkPenetrasi.setSelected(true);
        } else {
            ChkPenetrasi.setSelected(false);
        }
        
        if (tender.equals("ya")) {
            ChkTenderness.setSelected(true);
        } else {
            ChkTenderness.setSelected(false);
        }
        
        if (swel.equals("ya")) {
            ChkSwelling.setSelected(true);
        } else {
            ChkSwelling.setSelected(false);
        }
        
        if (eksko.equals("ya")) {
            ChkEkskoriasi.setSelected(true);
        } else {
            ChkEkskoriasi.setSelected(false);
        }
        
        if (abras.equals("ya")) {
            ChkAbrasi.setSelected(true);
        } else {
            ChkAbrasi.setSelected(false);
        }
        
        if (burn.equals("ya")) {
            ChkBurn.setSelected(true);
        } else {
            ChkBurn.setSelected(false);
        }
        
        if (laser.equals("ya")) {
            ChkLaserasi.setSelected(true);
        } else {
            ChkLaserasi.setSelected(false);
        }
        
        if (tdkTampak.equals("ya")) {
            ChkTdkTampk.setSelected(true);
        } else {
            ChkTdkTampk.setSelected(false);
        }
        
        if (hipDulu.equals("ya")) {
            ChkHipertensiDulu.setSelected(true);
        } else {
            ChkHipertensiDulu.setSelected(false);
        }
        
        if (dmDulu.equals("ya")) {
            ChkDMDulu.setSelected(true);
        } else {
            ChkDMDulu.setSelected(false);
        }
        
        if (lainDulu.equals("ya")) {
            ChkLainyaDulu.setSelected(true);
            TLainPenyakitDulu.setEnabled(true);
        } else {
            ChkLainyaDulu.setSelected(false);
            TLainPenyakitDulu.setEnabled(false);
        }
        
        if (hipKlg.equals("ya")) {
            ChkHipertensiKlg.setSelected(true);
        } else {
            ChkHipertensiKlg.setSelected(false);
        }
        
        if (dmKlg.equals("ya")) {
            ChkDMKlg.setSelected(true);
        } else {
            ChkDMKlg.setSelected(false);
        }
        
        if (janKlg.equals("ya")) {
            ChkJantungKlg.setSelected(true);
        } else {
            ChkJantungKlg.setSelected(false);
        }
        
        if (lainKlg.equals("ya")) {
            ChkLainyaKlg.setSelected(true);
            TLainPenyakitKlg.setEnabled(true);
        } else {
            ChkLainyaKlg.setSelected(false);
            TLainPenyakitKlg.setEnabled(false);
        }
        
        if (merokok.equals("ya")) {
            ChkMerokok.setSelected(true);
        } else {
            ChkMerokok.setSelected(false);
        }
        
        if (lainBiasa.equals("ya")) {
            ChkLainyaBiasa.setSelected(true);
            TLainPenyakitBiasa.setEnabled(true);
        } else {
            ChkLainyaBiasa.setSelected(false);
            TLainPenyakitBiasa.setEnabled(false);
        }
        
        if (jamKlr.equals("ya")) {
            ChkJamKlr.setSelected(true);
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
        } else {
            ChkJamKlr.setSelected(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
        }
        
        if (jamMening.equals("ya")) {
            ChkJamMeninggal.setSelected(true);
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
        } else {
            ChkJamMeninggal.setSelected(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
        }
        
        ChkSamaPetugas.setSelected(false);
        ChkSamaDokter.setSelected(false);
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
    
    private void variabelBersih() {
        nipPemberi = "";
        nipDokter = "";
        nipBidan = "";
        nipDpjp = "";
        user = "";
        cervi = "";
        rjp = "";
        defri = "";
        intu = "";
        vtp = "";
        dekom = "";
        balut = "";
        katet = "";
        ngt = "";
        infus = "";
        obat = "";
        tdkAda = "";
        paten = "";
        obsPar = "";
        obsTot = "";
        trauma = "";
        resiko = "";
        benda = "";
        defor = "";
        contu = "";
        penet = "";
        tender = "";
        swel = "";
        eksko = "";
        abras = "";
        burn = "";
        laser = "";
        tdkTampak = "";
        hipDulu = "";
        dmDulu = "";
        lainDulu = "";
        hipKlg = "";
        dmKlg = "";
        janKlg = "";
        lainKlg = "";
        merokok = "";
        lainBiasa = "";
        jamKlr = "";
        jamMening = "";
        jamKeluar = "";
        jamMeninggal = "";
        idFileTtd = "";
        idParameterTtd = "";
        URL = "";
        usernya = "";
        pwdnya = "";
        ((RMAsesmenMedikKebidanan.Painter) gambarQR).setImage("");
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from asesmen_medik_kebidanan where no_rawat=?", 1, new String[]{
                tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
            }) == true) {
                if (!idFileTtd.equals("")) {
                    Sequel.hapusSemuaTtd(idFileTtd);
                }
                
                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        } else {
            tampil();
            emptTeks();
            TabRawat.setSelectedIndex(1);
        }
    }

    private void tampilTemplate() {
        Valid.tabelKosong(tabMode1);
        try {
            if (pilihTemplate == 1) {
                pst1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, am.* from asesmen_medik_kebidanan am "
                        + "inner join reg_periksa rp on rp.no_rawat=am.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "am.anamnesis<>'' and p.no_rkm_medis like ? OR "
                        + "am.anamnesis<>'' and p.nm_pasien like ? OR "
                        + "am.anamnesis<>'' and am.anamnesis like ? ORDER BY am.tgl_penanganan desc limit 20");
            } else if (pilihTemplate == 2) {
                pst2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, am.* from asesmen_medik_kebidanan am "
                        + "inner join reg_periksa rp on rp.no_rawat=am.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "am.diagnosis_medis<>'' and p.no_rkm_medis like ? OR "
                        + "am.diagnosis_medis<>'' and p.nm_pasien like ? OR "
                        + "am.diagnosis_medis<>'' and am.diagnosis_medis like ? ORDER BY am.tgl_penanganan desc limit 20");
            } else if (pilihTemplate == 3) {
                pst3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, am.* from asesmen_medik_kebidanan am "
                        + "inner join reg_periksa rp on rp.no_rawat=am.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "am.terapi<>'' and p.no_rkm_medis like ? OR "
                        + "am.terapi<>'' and p.nm_pasien like ? OR "
                        + "am.terapi<>'' and am.terapi like ? ORDER BY am.tgl_penanganan desc limit 20");
            } else if (pilihTemplate == 4) {
                pst4 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, am.* from asesmen_medik_kebidanan am "
                        + "inner join reg_periksa rp on rp.no_rawat=am.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "am.diberikan_informasi_edukasi_ttg<>'' and p.no_rkm_medis like ? OR "
                        + "am.diberikan_informasi_edukasi_ttg<>'' and p.nm_pasien like ? OR "
                        + "am.diberikan_informasi_edukasi_ttg<>'' and am.diberikan_informasi_edukasi_ttg like ? ORDER BY am.tgl_penanganan desc limit 20");
            } else if (pilihTemplate == 5) {
                pst5 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, am.* from asesmen_medik_kebidanan am "
                        + "inner join reg_periksa rp on rp.no_rawat=am.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "am.rencana_asuhan_diharapkan<>'' and p.no_rkm_medis like ? OR "
                        + "am.rencana_asuhan_diharapkan<>'' and p.nm_pasien like ? OR "
                        + "am.rencana_asuhan_diharapkan<>'' and am.rencana_asuhan_diharapkan like ? ORDER BY am.tgl_penanganan desc limit 20");
            } else if (pilihTemplate == 6) {
                pst6 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, am.* from asesmen_medik_kebidanan am "
                        + "inner join reg_periksa rp on rp.no_rawat=am.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "am.alasan_dirujuk<>'' and p.no_rkm_medis like ? OR "
                        + "am.alasan_dirujuk<>'' and p.nm_pasien like ? OR "
                        + "am.alasan_dirujuk<>'' and am.alasan_dirujuk like ? ORDER BY am.tgl_penanganan desc limit 20");
            } else if (pilihTemplate == 7) {
                pst7 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, am.* from asesmen_medik_kebidanan am "
                        + "inner join reg_periksa rp on rp.no_rawat=am.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "am.penyebab<>'' and p.no_rkm_medis like ? OR "
                        + "am.penyebab<>'' and p.nm_pasien like ? OR "
                        + "am.penyebab<>'' and am.penyebab like ? ORDER BY am.tgl_penanganan desc limit 20");
            } 
            try {
                if (pilihTemplate == 1) {
                    pst1.setString(1, "%" + TCari1.getText() + "%");
                    pst1.setString(2, "%" + TCari1.getText() + "%");
                    pst1.setString(3, "%" + TCari1.getText() + "%");
                    rst1 = pst1.executeQuery();
                    while (rst1.next()) {
                        tabMode1.addRow(new String[]{
                            rst1.getString("no_rkm_medis"),
                            rst1.getString("nm_pasien"),
                            rst1.getString("anamnesis")
                        });
                    }
                } else if (pilihTemplate == 2) {
                    pst2.setString(1, "%" + TCari1.getText() + "%");
                    pst2.setString(2, "%" + TCari1.getText() + "%");
                    pst2.setString(3, "%" + TCari1.getText() + "%");
                    rst2 = pst2.executeQuery();
                    while (rst2.next()) {
                        tabMode1.addRow(new String[]{
                            rst2.getString("no_rkm_medis"),
                            rst2.getString("nm_pasien"),
                            rst2.getString("diagnosis_medis")
                        });
                    }
                } else if (pilihTemplate == 3) {
                    pst3.setString(1, "%" + TCari1.getText() + "%");
                    pst3.setString(2, "%" + TCari1.getText() + "%");
                    pst3.setString(3, "%" + TCari1.getText() + "%");
                    rst3 = pst3.executeQuery();
                    while (rst3.next()) {
                        tabMode1.addRow(new String[]{
                            rst3.getString("no_rkm_medis"),
                            rst3.getString("nm_pasien"),
                            rst3.getString("terapi")
                        });
                    }
                } else if (pilihTemplate == 4) {
                    pst4.setString(1, "%" + TCari1.getText() + "%");
                    pst4.setString(2, "%" + TCari1.getText() + "%");
                    pst4.setString(3, "%" + TCari1.getText() + "%");
                    rst4 = pst4.executeQuery();
                    while (rst4.next()) {
                        tabMode1.addRow(new String[]{
                            rst4.getString("no_rkm_medis"),
                            rst4.getString("nm_pasien"),
                            rst4.getString("diberikan_informasi_edukasi_ttg")
                        });
                    }
                } else if (pilihTemplate == 5) {
                    pst5.setString(1, "%" + TCari1.getText() + "%");
                    pst5.setString(2, "%" + TCari1.getText() + "%");
                    pst5.setString(3, "%" + TCari1.getText() + "%");
                    rst5 = pst5.executeQuery();
                    while (rst5.next()) {
                        tabMode1.addRow(new String[]{
                            rst5.getString("no_rkm_medis"),
                            rst5.getString("nm_pasien"),
                            rst5.getString("rencana_asuhan_diharapkan")
                        });
                    }
                } else if (pilihTemplate == 6) {
                    pst6.setString(1, "%" + TCari1.getText() + "%");
                    pst6.setString(2, "%" + TCari1.getText() + "%");
                    pst6.setString(3, "%" + TCari1.getText() + "%");
                    rst6 = pst6.executeQuery();
                    while (rst6.next()) {
                        tabMode1.addRow(new String[]{
                            rst6.getString("no_rkm_medis"),
                            rst6.getString("nm_pasien"),
                            rst6.getString("alasan_dirujuk")
                        });
                    }
                } else if (pilihTemplate == 7) {
                    pst7.setString(1, "%" + TCari1.getText() + "%");
                    pst7.setString(2, "%" + TCari1.getText() + "%");
                    pst7.setString(3, "%" + TCari1.getText() + "%");
                    rst7 = pst7.executeQuery();
                    while (rst7.next()) {
                        tabMode1.addRow(new String[]{
                            rst7.getString("no_rkm_medis"),
                            rst7.getString("nm_pasien"),
                            rst7.getString("penyebab")
                        });
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rst1 != null) {
                    rst1.close();
                } else if (rst2 != null) {
                    rst2.close();
                } else if (rst3 != null) {
                    rst3.close();
                } else if (rst4 != null) {
                    rst4.close();
                } else if (rst5 != null) {
                    rst5.close();
                } else if (rst6 != null) {
                    rst6.close();
                } else if (rst7 != null) {
                    rst7.close();
                }

                if (pst1 != null) {
                    pst1.close();
                } else if (pst2 != null) {
                    pst2.close();
                } else if (pst3 != null) {
                    pst3.close();
                } else if (pst4 != null) {
                    pst4.close();
                } else if (pst5 != null) {
                    pst5.close();
                } else if (pst6 != null) {
                    pst6.close();
                } else if (pst7 != null) {
                    pst7.close();
                } 
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copas() {
        if (pilihTemplate == 1) {
            TAnamnesis.setText(Ttemplate.getText());
        } else if (pilihTemplate == 2) {
            Tdiagnosis.setText(Ttemplate.getText());
        } else if (pilihTemplate == 3) {
            Tterapi.setText(Ttemplate.getText());
        } else if (pilihTemplate == 4) {
            Tedukasi.setText(Ttemplate.getText());
        } else if (pilihTemplate == 5) {
            Trencana.setText(Ttemplate.getText());
        } else if (pilihTemplate == 6) {
            TAlasanDirujuk.setText(Ttemplate.getText());
        } else if (pilihTemplate == 7) {
            Tpenyebab.setText(Ttemplate.getText());
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
                            nipPemberi = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                            Tnmpemberi.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnPemberi.requestFocus();
                        }
                    } else if (pilihan == 2) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            nipBidan = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                            Tnmbidan.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnBidan.requestFocus();
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
                    if (akses.getform().equals("RMAsesmenMedikKebidanan")) {
                        if (pilihan == 3) {
                            if (dokter.getTable().getSelectedRow() != -1) {
                                nipDokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                                Tnmdokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                                BtnDokter.requestFocus();
                            }
                        } else if (pilihan == 4) {
                            if (dokter.getTable().getSelectedRow() != -1) {
                                nipDpjp = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                                Tnmdpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                                BtnDpjp.requestFocus();
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
        }
    }

    private void initICD() {
        if (icd10 == null) {
            icd10 = new DlgPenyakit(null, false);

            icd10.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    if (icd10.getTable().getSelectedRow() != -1) {
                        x = JOptionPane.showConfirmDialog(rootPane, "Apakah deskripsi ICD 10 akan ditambahkan juga utk. diagnosa medis sementara..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                        if (x == JOptionPane.YES_OPTION) {
                            Ticd10.setText(icd10.getTable().getValueAt(icd10.getTable().getSelectedRow(), 1).toString());
                            if (Tdiagnosis.getText().equals("")) {
                                Tdiagnosis.setText(icd10.getTable().getValueAt(icd10.getTable().getSelectedRow(), 3).toString());
                            } else {
                                Tdiagnosis.setText(Tdiagnosis.getText() + " (" + icd10.getTable().getValueAt(icd10.getTable().getSelectedRow(), 3).toString() + ")");
                            }
                            BtnICD.requestFocus();
                        } else {
                            Ticd10.setText(icd10.getTable().getValueAt(icd10.getTable().getSelectedRow(), 1).toString());
                            BtnICD.requestFocus();
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

            icd10.getTable().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (akses.getform().equals("RMAsesmenMedikKebidanan")) {
                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            icd10.dispose();
                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }
    }

    private void scrollKeAtas() {
        SwingUtilities.invokeLater(() -> {
            scrollInput.getVerticalScrollBar().setValue(0);
            scrollInput.getHorizontalScrollBar().setValue(0);
        });
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
                    + "<td align='center' bgcolor='#f8fdf3'><b>Penerima Edukasi</b></td>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>"
            );

            htmlContent.append(
                    "<tr class='isi'>"
                    + "<td valign='middle' align='center'><img src='" + gambar + "' width='160' height='160' alt='TTD Penerima Edukasi'><br>(" + Tnmpenerima.getText() + ")<br></td>"
                    + "</tr>"
            );

            htmlContent.append("</tbody>"
                    + "</table>");

            LoadHTML1.setText(
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
        Sequel.menyimpanQrTte("parameter_ttd_rme", "'" + idParameterTtd + "','" + usernya + "','" + pwdnya + "','" + TNoRw.getText() + "','" + TNoRM.getText() + "','"
                + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen like '%ASESMEN MEDIK KEBIDANAN%'") + "',"
                + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

        try {
            ((RMAsesmenMedikKebidanan.Painter) gambarQR).setImage("");
            ResultSet hasil = koneksi.createStatement().executeQuery(
                    "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
            for (int I = 0; hasil.next(); I++) {
                Blob blob = hasil.getBlob(1);
                ((RMAsesmenMedikKebidanan.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
                        blob.getBytes(1, (int) (blob.length()))));
                blob.free();
            }

            emptTeks();
            tampil();
            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void awalData() {
        if (Sequel.cariInteger("select count(-1) from asesmen_medik_kebidanan where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from asesmen_medik_kebidanan where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
            scrollKeAtas();
        }
        
        Sequel.cariIsiComboDB("SELECT distinct CASE WHEN nm_gedung IN ('AR-RAUDAH ATAS', 'AR-RAUDAH BAWAH') THEN 'AR-RAUDAH' ELSE nm_gedung END AS gedungnya "
                + "FROM bangsal WHERE status = '1' "
                + "AND nm_gedung NOT LIKE '%instalasi%' "
                + "AND nm_gedung NOT LIKE '%sdm%' "
                + "AND nm_gedung NOT LIKE '%ipsrs%' "
                + "AND nm_gedung NOT LIKE '%uang%' "
                + "AND nm_gedung NOT LIKE '%sanitasi%' "
                + "AND nm_gedung NOT LIKE '%inst.%' "
                + "AND nm_gedung NOT LIKE '%bid.%' "
                + "AND nm_gedung NOT LIKE '%unit%' "
                + "AND nm_gedung NOT LIKE '%bag.%' "
                + "AND nm_gedung NOT LIKE '%upm%' "
                + "AND nm_gedung <>'-' GROUP BY nm_gedung ORDER BY nm_gedung", cmbRuangan);
        tampil();
        
        ((RMAsesmenMedikKebidanan.Painter) gambarQR).setImage("");
        Sequel.cariIsiComboDB("select nm_dokumen from master_nomor_dokumen_erm where "
                + "status='aktif' and unit_pengguna='Instalasi' and ttd_keluarga_pasien='Ya' order by kode_erm", cmbRM);
        Sequel.queryu("DELETE FROM parameter_ttd_rme WHERE DATE(waktu_kirim) < CURDATE()");
    }
}
