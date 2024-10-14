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
import kepegawaian.DlgCariPetugas;
import keuangan.DlgKamar;
import laporan.DlgHasilPenunjangMedis;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgNotepad;

/**
 *
 * @author perpustakaan
 */
public final class RMAsesmenMedikDewasaRanap extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode3, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, pscppt, psrestor;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rscppt, rsrestor;
    private int i = 0, x = 0, pilihan = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    public DlgKamar kamar = new DlgKamar(null, false);
    private String rujukan = "", dtg_sendiri = "", diantar = "", hipertensi1 = "", dm1 = "", pjk = "",
            asma1 = "", strok = "", liver = "", ginjal = "", tb = "", lain1 = "", hipertensi2 = "", dm2 = "",
            jantung = "", asma2 = "", lain2 = "", nyeri = "", resus = "", reguler = "", ireguler = "",
            kodekamar = "", dataKonfirmasi = "", user = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsesmenMedikDewasaRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.RM", "Nama Pasien", "J.K.", "Tgl. Lahir", "Tgl. Asesmen", "Nama DPJP", "Ruang Perawatan", "kd_kamar", "tgl_asesmen", "rujukan", "ket_rs",
            "ket_puskes", "ket_praktek", "ket_lainya", "diagnosa_rujukan", "datang_sendiri", "diantar", "ket_diantar", "nip_dokter_memeriksa", "nip_supervisor",
            "tgl_anamnese", "keluhan_utama", "riw_penyakit_sekarang", "hipertensi_1", "dm_1", "pjk", "asma_1", "stroke", "liver", "ginjal", "tb_paru", "lain_lain_1",
            "ket_lain_1", "pernah_dirawat", "ket_kapan", "ket_dimana", "diagnosis", "hipertensi_2", "dm_2", "jantung", "asma_2", "lain_lain_2", "ket_lain_2",
            "riw_alergi", "nyeri", "ket_lokasi", "ket_intensitas", "skor", "jenis", "keadaan_umum", "gizi", "gcs_e", "gcs_m", "gcs_v", "tindakan_resus", "bb", "tb",
            "td", "nadi", "respirasi", "suhu_axila", "suhu_rektal", "mata_anemis", "ikterik", "pupil", "diameter_kanan", "diameter_kiri", "udem_palpebra", "tonsil",
            "faring", "lidah", "bibir", "jvp", "kelenjar_limfe", "ket_ada_kelenjar", "kaku_kuduk", "thoraks", "ket_asimetris", "cor_s1s2", "reguler", "ireguler",
            "murmur", "lain_lain", "suara_nafas", "ronchi", "ket_ronchi", "wheezing", "ket_wheezing", "distended", "meteorismus", "peristaltik", "asites", "nyeri_tekan",
            "lokasi", "hepar", "lien", "extremitas", "udem", "ket_udem", "hasil_pemeriksaan", "diagnosis1", "diagnosis2", "diagnosis3", "diagnosis4", "rencana_kerja",
            "perencanaan_pemulangan", "catatan_penting", "tgl_dpjp", "nip_dpjp", "nmdokter_memeriksa", "nm_super", "pem_fisik_lain", "saturasi", "diagnosis5", "diagnosis6",
            "diagnosis7", "waktu_simpan"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbPenilaian.setModel(tabMode);
        tbPenilaian.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPenilaian.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 118; i++) {
            TableColumn column = tbPenilaian.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(250);
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
            }
        }
        tbPenilaian.setDefaultRenderer(Object.class, new WarnaTable()); 
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"No. RM", "Nama Pasien", "Data Template"}) {
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
        
        tabMode3 = new DefaultTableModel(null, new Object[]{
            "Dilakukan Oleh", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Asesmen", "Tgl. Eksekusi", "Status Data"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayat.setModel(tabMode3);
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
        
        Tket_rs.setDocument(new batasInput((int) 150).getKata(Tket_rs));
        Tket_puskes.setDocument(new batasInput((int) 150).getKata(Tket_puskes));
        Tket_praktek.setDocument(new batasInput((int) 150).getKata(Tket_praktek));
        Tket_lainya.setDocument(new batasInput((int) 150).getKata(Tket_lainya));
        Tdiantar.setDocument(new batasInput((int) 150).getKata(Tdiantar));
        Tket_lain1.setDocument(new batasInput((int) 150).getKata(Tket_lain1));
        Tkapan.setDocument(new batasInput((int) 150).getKata(Tkapan));
        Tdimana.setDocument(new batasInput((int) 150).getKata(Tdimana));
        Tket_lain2.setDocument(new batasInput((int) 150).getKata(Tket_lain2));
        Tlokasi.setDocument(new batasInput((int) 150).getKata(Tlokasi));
        Tintensitas.setDocument(new batasInput((int) 150).getKata(Tintensitas));
        Tskor.setDocument(new batasInput((byte) 3).getOnlyAngka(Tskor));
        Tgcse.setDocument(new batasInput((int) 7).getKata(Tgcse));
        Tgcsv.setDocument(new batasInput((int) 7).getKata(Tgcsv));
        Tgcsm.setDocument(new batasInput((int) 7).getKata(Tgcsm));
        Tbb.setDocument(new batasInput((byte) 3).getOnlyAngka(Tbb));
        Ttb.setDocument(new batasInput((byte) 3).getOnlyAngka(Ttb));
        Ttd.setDocument(new batasInput((int) 7).getKata(Ttd));
        Tnadi.setDocument(new batasInput((int) 7).getKata(Tnadi));
        Trespi.setDocument(new batasInput((int) 7).getKata(Trespi));
        Taxila.setDocument(new batasInput((int) 7).getKata(Taxila));
        Trektal.setDocument(new batasInput((int) 7).getKata(Trektal));        
        Tdiameter_kanan.setDocument(new batasInput((int) 7).getKata(Tdiameter_kanan));
        Tdiameter_kiri.setDocument(new batasInput((int) 7).getKata(Tdiameter_kiri));
        Ttonsil.setDocument(new batasInput((int) 150).getKata(Ttonsil));
        Tfaring.setDocument(new batasInput((int) 150).getKata(Tfaring));
        Tlidah.setDocument(new batasInput((int) 150).getKata(Tlidah));
        Tbibir.setDocument(new batasInput((int) 150).getKata(Tbibir));
        Tjvp.setDocument(new batasInput((int) 150).getKata(Tjvp));
        Tkelenjar.setDocument(new batasInput((int) 150).getKata(Tkelenjar));
        saturasi.setDocument(new batasInput((int) 7).getKata(saturasi));
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
        
        kamar.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMAsesmenMedikDewasaRanap")) {
                    if (kamar.getTable().getSelectedRow() != -1) {
                        Tkdkamar.setText(kamar.getTable().getValueAt(kamar.getTable().getSelectedRow(), 1).toString());
                        TRuangan.setText(Sequel.cariIsi("SELECT b.nm_bangsal FROM bangsal b INNER JOIN kamar k ON k.kd_bangsal=b.kd_bangsal WHERE k.kd_kamar='" + Tkdkamar.getText() + "'"));
                        BtnKamar.requestFocus();
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
        
        kamar.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("RMAsesmenMedikDewasaRanap")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        kamar.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    kdsuper.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                    nmsuper.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMAsesmenMedikDewasaRanap")) {
                    if (pilihan == 1) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            kddpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            nmdpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        }
                        BtnDpjp.requestFocus();
                    } else if (pilihan == 2) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            kddokter_meriksa.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            nmdokter_meriksa.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        }
                        BtnDokter_meriksa.requestFocus();
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
        MnCopyHasil = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        MnCopyInstruksi = new javax.swing.JMenuItem();
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnNotepad = new widget.Button();
        BtnResep = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        label11 = new widget.Label();
        jLabel11 = new widget.Label();
        TglAsesmen = new widget.Tanggal();
        PanelWall = new usu.widget.glass.PanelGlass();
        jLabel12 = new widget.Label();
        label15 = new widget.Label();
        kddpjp = new widget.TextBox();
        nmdpjp = new widget.TextBox();
        BtnDpjp = new widget.Button();
        jLabel13 = new widget.Label();
        TRuangan = new widget.TextBox();
        ChkRujukan = new widget.CekBox();
        jLabel14 = new widget.Label();
        Tket_rs = new widget.TextBox();
        jLabel15 = new widget.Label();
        jLabel16 = new widget.Label();
        Tket_puskes = new widget.TextBox();
        jLabel17 = new widget.Label();
        Tket_praktek = new widget.TextBox();
        jLabel18 = new widget.Label();
        Tket_lainya = new widget.TextBox();
        jLabel20 = new widget.Label();
        Tdiag_rujukan = new widget.TextBox();
        ChkDatangSendiri = new widget.CekBox();
        ChkDiantar = new widget.CekBox();
        Tdiantar = new widget.TextBox();
        label16 = new widget.Label();
        kddokter_meriksa = new widget.TextBox();
        nmdokter_meriksa = new widget.TextBox();
        BtnDokter_meriksa = new widget.Button();
        label17 = new widget.Label();
        kdsuper = new widget.TextBox();
        nmsuper = new widget.TextBox();
        BtnSuper = new widget.Button();
        label18 = new widget.Label();
        label12 = new widget.Label();
        TglAnamnesa = new widget.Tanggal();
        label19 = new widget.Label();
        Tkeluhan = new widget.TextBox();
        label20 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        Triw_penyakit_sekarang = new widget.TextArea();
        label21 = new widget.Label();
        ChkHipertensi1 = new widget.CekBox();
        ChkDM1 = new widget.CekBox();
        ChkPJK = new widget.CekBox();
        ChkAsma1 = new widget.CekBox();
        ChkStroke = new widget.CekBox();
        ChkLiver = new widget.CekBox();
        ChkGinjal = new widget.CekBox();
        ChkTB = new widget.CekBox();
        ChkLain1 = new widget.CekBox();
        Tket_lain1 = new widget.TextBox();
        cmbPernah = new widget.ComboBox();
        label22 = new widget.Label();
        label23 = new widget.Label();
        Tkapan = new widget.TextBox();
        label24 = new widget.Label();
        Tdimana = new widget.TextBox();
        label25 = new widget.Label();
        Tdiagnosis = new widget.TextBox();
        label26 = new widget.Label();
        ChkHipertensi2 = new widget.CekBox();
        ChkDM2 = new widget.CekBox();
        ChkJantung = new widget.CekBox();
        ChkAsma2 = new widget.CekBox();
        ChkLain2 = new widget.CekBox();
        Tket_lain2 = new widget.TextBox();
        scrollPane14 = new widget.ScrollPane();
        Triw_alergi = new widget.TextArea();
        label27 = new widget.Label();
        label28 = new widget.Label();
        ChkNyeri = new widget.CekBox();
        label29 = new widget.Label();
        Tlokasi = new widget.TextBox();
        label30 = new widget.Label();
        Tintensitas = new widget.TextBox();
        label31 = new widget.Label();
        Tskor = new widget.TextBox();
        label32 = new widget.Label();
        cmbJenis = new widget.ComboBox();
        label33 = new widget.Label();
        label34 = new widget.Label();
        cmbKeadaan = new widget.ComboBox();
        label35 = new widget.Label();
        cmbGizi = new widget.ComboBox();
        label36 = new widget.Label();
        Tgcse = new widget.TextBox();
        label37 = new widget.Label();
        Tgcsm = new widget.TextBox();
        label38 = new widget.Label();
        Tgcsv = new widget.TextBox();
        ChkTindakanResus = new widget.CekBox();
        label39 = new widget.Label();
        Tbb = new widget.TextBox();
        label40 = new widget.Label();
        Ttb = new widget.TextBox();
        label41 = new widget.Label();
        Ttd = new widget.TextBox();
        label42 = new widget.Label();
        Tnadi = new widget.TextBox();
        label43 = new widget.Label();
        label44 = new widget.Label();
        Trespi = new widget.TextBox();
        label45 = new widget.Label();
        Taxila = new widget.TextBox();
        jLabel22 = new widget.Label();
        Trektal = new widget.TextBox();
        jLabel23 = new widget.Label();
        label46 = new widget.Label();
        label47 = new widget.Label();
        cmbMata = new widget.ComboBox();
        label48 = new widget.Label();
        cmbIkterik = new widget.ComboBox();
        label49 = new widget.Label();
        cmbPupil = new widget.ComboBox();
        label50 = new widget.Label();
        Tdiameter_kanan = new widget.TextBox();
        label51 = new widget.Label();
        Tdiameter_kiri = new widget.TextBox();
        label52 = new widget.Label();
        label53 = new widget.Label();
        cmbUdem_palpe = new widget.ComboBox();
        label54 = new widget.Label();
        Ttonsil = new widget.TextBox();
        label55 = new widget.Label();
        Tfaring = new widget.TextBox();
        label56 = new widget.Label();
        Tlidah = new widget.TextBox();
        label57 = new widget.Label();
        Tbibir = new widget.TextBox();
        label58 = new widget.Label();
        Tjvp = new widget.TextBox();
        label59 = new widget.Label();
        cmbKelenjar = new widget.ComboBox();
        Tkelenjar = new widget.TextBox();
        label60 = new widget.Label();
        cmbKaku = new widget.ComboBox();
        label61 = new widget.Label();
        cmbThoraks = new widget.ComboBox();
        Tasimetris = new widget.TextBox();
        label62 = new widget.Label();
        Tcor = new widget.TextBox();
        ChkReguler = new widget.CekBox();
        ChkIreguler = new widget.CekBox();
        Tmurmur = new widget.TextBox();
        label63 = new widget.Label();
        Tlain_lain = new widget.TextBox();
        label64 = new widget.Label();
        Tsuara_nfs = new widget.TextBox();
        label65 = new widget.Label();
        cmbRonchi = new widget.ComboBox();
        Tronchi = new widget.TextBox();
        label66 = new widget.Label();
        cmbWhezing = new widget.ComboBox();
        Twhezing = new widget.TextBox();
        label67 = new widget.Label();
        cmbDisten = new widget.ComboBox();
        label68 = new widget.Label();
        cmbMeteo = new widget.ComboBox();
        label69 = new widget.Label();
        cmbPeris = new widget.ComboBox();
        label70 = new widget.Label();
        cmbAsites = new widget.ComboBox();
        label71 = new widget.Label();
        cmbNyeri = new widget.ComboBox();
        label72 = new widget.Label();
        Tlokasi_nyeri = new widget.TextBox();
        label73 = new widget.Label();
        Thepar = new widget.TextBox();
        label74 = new widget.Label();
        Tlien = new widget.TextBox();
        label75 = new widget.Label();
        cmbExtrem = new widget.ComboBox();
        label76 = new widget.Label();
        cmbUdem = new widget.ComboBox();
        Tudem = new widget.TextBox();
        PanelWall1 = new usu.widget.glass.PanelGlass();
        label77 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        Thsl_pemeriksaan = new widget.TextArea();
        label78 = new widget.Label();
        Tdiagnosa1 = new widget.TextBox();
        label79 = new widget.Label();
        label80 = new widget.Label();
        Tdiagnosa2 = new widget.TextBox();
        label81 = new widget.Label();
        Tdiagnosa3 = new widget.TextBox();
        label82 = new widget.Label();
        Tdiagnosa4 = new widget.TextBox();
        label83 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        Trencana_kerja = new widget.TextArea();
        label84 = new widget.Label();
        scrollPane17 = new widget.ScrollPane();
        Trencana_pulang = new widget.TextArea();
        label85 = new widget.Label();
        scrollPane18 = new widget.ScrollPane();
        Tcatatan = new widget.TextArea();
        label86 = new widget.Label();
        TglDpjp = new widget.Tanggal();
        BtnKamar = new widget.Button();
        jLabel47 = new widget.Label();
        Tkdkamar = new widget.TextBox();
        BtnRiwPenyakit = new widget.Button();
        BtnRiwAlergi = new widget.Button();
        BtnHasil = new widget.Button();
        BtnRencana = new widget.Button();
        BtnPerencana = new widget.Button();
        BtnCatatan = new widget.Button();
        label87 = new widget.Label();
        TPemFisikLain = new widget.TextBox();
        label88 = new widget.Label();
        saturasi = new widget.TextBox();
        label89 = new widget.Label();
        label90 = new widget.Label();
        label91 = new widget.Label();
        label92 = new widget.Label();
        Tdiagnosa5 = new widget.TextBox();
        Tdiagnosa6 = new widget.TextBox();
        Tdiagnosa7 = new widget.TextBox();
        BtnPasteHasil = new widget.Button();
        BtnPasteRencana = new widget.Button();
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

        MnCopyHasil.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopyHasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopyHasil.setText("Copy Semua Hasil Pemeriksaan");
        MnCopyHasil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCopyHasil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCopyHasil.setIconTextGap(5);
        MnCopyHasil.setName("MnCopyHasil"); // NOI18N
        MnCopyHasil.setPreferredSize(new java.awt.Dimension(190, 26));
        MnCopyHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyHasilActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnCopyHasil);

        jPopupMenu3.setName("jPopupMenu3"); // NOI18N

        MnCopyInstruksi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopyInstruksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopyInstruksi.setText("Copy Semua Instruksi Nakes");
        MnCopyInstruksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCopyInstruksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCopyInstruksi.setIconTextGap(5);
        MnCopyInstruksi.setName("MnCopyInstruksi"); // NOI18N
        MnCopyInstruksi.setPreferredSize(new java.awt.Dimension(190, 26));
        MnCopyInstruksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyInstruksiActionPerformed(evt);
            }
        });
        jPopupMenu3.add(MnCopyInstruksi);

        WindowTemplate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTemplate.setName("WindowTemplate"); // NOI18N
        WindowTemplate.setUndecorated(true);
        WindowTemplate.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Asesmen Medik Dewasa ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame4.setLayout(new java.awt.BorderLayout());

        panelisi4.setBackground(new java.awt.Color(255, 150, 255));
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

        internalFrame4.add(panelisi4, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 250));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTemplate.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTemplate.setName("tbTemplate"); // NOI18N
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

        internalFrame4.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        WindowTemplate.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        WindowRiwayat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayat.setName("WindowRiwayat"); // NOI18N
        WindowRiwayat.setUndecorated(true);
        WindowRiwayat.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Asesmen Medik Dewasa Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-10-2024" }));
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

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-10-2024" }));
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Medik Dewasa Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2510));
        FormInput.setLayout(null);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 30, 131, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(279, 30, 300, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(207, 30, 70, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 30, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 30, 80, 23);

        Jk.setEditable(false);
        Jk.setForeground(new java.awt.Color(0, 0, 0));
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(774, 30, 80, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 30, 70, 23);

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tgl. Asesmen : ");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(625, 60, 100, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("J. Kel. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(730, 30, 40, 23);

        TglAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-10-2024 16:10:34" }));
        TglAsesmen.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsesmen.setName("TglAsesmen"); // NOI18N
        TglAsesmen.setOpaque(false);
        FormInput.add(TglAsesmen);
        TglAsesmen.setBounds(725, 60, 130, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/paru_form.jpg"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(660, 1143, 170, 170);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("MULAI PENANGANAN :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(10, 8, 130, 23);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("Mengetahui DPJP :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(0, 60, 150, 23);

        kddpjp.setEditable(false);
        kddpjp.setForeground(new java.awt.Color(0, 0, 0));
        kddpjp.setName("kddpjp"); // NOI18N
        kddpjp.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(kddpjp);
        kddpjp.setBounds(154, 60, 100, 23);

        nmdpjp.setEditable(false);
        nmdpjp.setForeground(new java.awt.Color(0, 0, 0));
        nmdpjp.setName("nmdpjp"); // NOI18N
        nmdpjp.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(nmdpjp);
        nmdpjp.setBounds(257, 60, 318, 23);

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
        BtnDpjp.setBounds(580, 60, 28, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Ruang Perawatan Inap :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 90, 150, 23);

        TRuangan.setEditable(false);
        TRuangan.setForeground(new java.awt.Color(0, 0, 0));
        TRuangan.setName("TRuangan"); // NOI18N
        FormInput.add(TRuangan);
        TRuangan.setBounds(287, 90, 534, 23);

        ChkRujukan.setBackground(new java.awt.Color(255, 255, 250));
        ChkRujukan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkRujukan.setForeground(new java.awt.Color(0, 0, 0));
        ChkRujukan.setText("Rujukan");
        ChkRujukan.setBorderPainted(true);
        ChkRujukan.setBorderPaintedFlat(true);
        ChkRujukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkRujukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkRujukan.setName("ChkRujukan"); // NOI18N
        ChkRujukan.setOpaque(false);
        ChkRujukan.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkRujukanActionPerformed(evt);
            }
        });
        FormInput.add(ChkRujukan);
        ChkRujukan.setBounds(20, 120, 70, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Dari : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(98, 120, 50, 23);

        Tket_rs.setForeground(new java.awt.Color(0, 0, 0));
        Tket_rs.setName("Tket_rs"); // NOI18N
        Tket_rs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tket_rsKeyPressed(evt);
            }
        });
        FormInput.add(Tket_rs);
        Tket_rs.setBounds(219, 120, 635, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("RS : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(148, 120, 70, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Puskesmas : ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(148, 150, 70, 23);

        Tket_puskes.setForeground(new java.awt.Color(0, 0, 0));
        Tket_puskes.setName("Tket_puskes"); // NOI18N
        Tket_puskes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tket_puskesKeyPressed(evt);
            }
        });
        FormInput.add(Tket_puskes);
        Tket_puskes.setBounds(219, 150, 635, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Praktek : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(148, 180, 70, 23);

        Tket_praktek.setForeground(new java.awt.Color(0, 0, 0));
        Tket_praktek.setName("Tket_praktek"); // NOI18N
        Tket_praktek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tket_praktekKeyPressed(evt);
            }
        });
        FormInput.add(Tket_praktek);
        Tket_praktek.setBounds(219, 180, 635, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Lainnya : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(148, 210, 70, 23);

        Tket_lainya.setForeground(new java.awt.Color(0, 0, 0));
        Tket_lainya.setName("Tket_lainya"); // NOI18N
        Tket_lainya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tket_lainyaKeyPressed(evt);
            }
        });
        FormInput.add(Tket_lainya);
        Tket_lainya.setBounds(219, 210, 635, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Diagnosa Rujukan : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(78, 240, 140, 23);

        Tdiag_rujukan.setForeground(new java.awt.Color(0, 0, 0));
        Tdiag_rujukan.setName("Tdiag_rujukan"); // NOI18N
        Tdiag_rujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdiag_rujukanKeyPressed(evt);
            }
        });
        FormInput.add(Tdiag_rujukan);
        Tdiag_rujukan.setBounds(219, 240, 635, 23);

        ChkDatangSendiri.setBackground(new java.awt.Color(255, 255, 250));
        ChkDatangSendiri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup2.add(ChkDatangSendiri);
        ChkDatangSendiri.setForeground(new java.awt.Color(0, 0, 0));
        ChkDatangSendiri.setText("Datang Sendiri");
        ChkDatangSendiri.setBorderPainted(true);
        ChkDatangSendiri.setBorderPaintedFlat(true);
        ChkDatangSendiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDatangSendiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDatangSendiri.setName("ChkDatangSendiri"); // NOI18N
        ChkDatangSendiri.setOpaque(false);
        ChkDatangSendiri.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkDatangSendiri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkDatangSendiriActionPerformed(evt);
            }
        });
        FormInput.add(ChkDatangSendiri);
        ChkDatangSendiri.setBounds(40, 270, 100, 23);

        ChkDiantar.setBackground(new java.awt.Color(255, 255, 250));
        ChkDiantar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup2.add(ChkDiantar);
        ChkDiantar.setForeground(new java.awt.Color(0, 0, 0));
        ChkDiantar.setText("Diantar");
        ChkDiantar.setBorderPainted(true);
        ChkDiantar.setBorderPaintedFlat(true);
        ChkDiantar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDiantar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDiantar.setName("ChkDiantar"); // NOI18N
        ChkDiantar.setOpaque(false);
        ChkDiantar.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkDiantar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkDiantarActionPerformed(evt);
            }
        });
        FormInput.add(ChkDiantar);
        ChkDiantar.setBounds(155, 270, 60, 23);

        Tdiantar.setForeground(new java.awt.Color(0, 0, 0));
        Tdiantar.setName("Tdiantar"); // NOI18N
        FormInput.add(Tdiantar);
        Tdiantar.setBounds(219, 270, 635, 23);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Dokter Yang Memeriksa :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label16);
        label16.setBounds(0, 300, 170, 23);

        kddokter_meriksa.setEditable(false);
        kddokter_meriksa.setForeground(new java.awt.Color(0, 0, 0));
        kddokter_meriksa.setName("kddokter_meriksa"); // NOI18N
        kddokter_meriksa.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(kddokter_meriksa);
        kddokter_meriksa.setBounds(175, 300, 100, 23);

        nmdokter_meriksa.setEditable(false);
        nmdokter_meriksa.setForeground(new java.awt.Color(0, 0, 0));
        nmdokter_meriksa.setName("nmdokter_meriksa"); // NOI18N
        nmdokter_meriksa.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(nmdokter_meriksa);
        nmdokter_meriksa.setBounds(280, 300, 388, 23);

        BtnDokter_meriksa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter_meriksa.setMnemonic('2');
        BtnDokter_meriksa.setToolTipText("Alt+2");
        BtnDokter_meriksa.setName("BtnDokter_meriksa"); // NOI18N
        BtnDokter_meriksa.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter_meriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter_meriksaActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter_meriksa);
        BtnDokter_meriksa.setBounds(670, 300, 28, 23);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Supervisor / Koordinator Jaga :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label17);
        label17.setBounds(0, 330, 170, 23);

        kdsuper.setEditable(false);
        kdsuper.setForeground(new java.awt.Color(0, 0, 0));
        kdsuper.setName("kdsuper"); // NOI18N
        kdsuper.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(kdsuper);
        kdsuper.setBounds(175, 330, 100, 23);

        nmsuper.setEditable(false);
        nmsuper.setForeground(new java.awt.Color(0, 0, 0));
        nmsuper.setName("nmsuper"); // NOI18N
        nmsuper.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(nmsuper);
        nmsuper.setBounds(280, 330, 388, 23);

        BtnSuper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSuper.setMnemonic('2');
        BtnSuper.setToolTipText("Alt+2");
        BtnSuper.setName("BtnSuper"); // NOI18N
        BtnSuper.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSuper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSuperActionPerformed(evt);
            }
        });
        FormInput.add(BtnSuper);
        BtnSuper.setBounds(670, 330, 28, 23);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setText("ANAMNESA :");
        label18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label18);
        label18.setBounds(0, 360, 90, 23);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Tgl. Anamnesa : ");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label12);
        label12.setBounds(625, 360, 100, 23);

        TglAnamnesa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-10-2024 16:10:35" }));
        TglAnamnesa.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAnamnesa.setName("TglAnamnesa"); // NOI18N
        TglAnamnesa.setOpaque(false);
        FormInput.add(TglAnamnesa);
        TglAnamnesa.setBounds(725, 360, 130, 23);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("Keluhan Utama : ");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label19);
        label19.setBounds(0, 390, 170, 23);

        Tkeluhan.setForeground(new java.awt.Color(0, 0, 0));
        Tkeluhan.setName("Tkeluhan"); // NOI18N
        Tkeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeluhanKeyPressed(evt);
            }
        });
        FormInput.add(Tkeluhan);
        Tkeluhan.setBounds(174, 390, 680, 23);

        label20.setForeground(new java.awt.Color(0, 0, 0));
        label20.setText("Riwayat Penyakit Sekarang : ");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label20);
        label20.setBounds(0, 420, 170, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N

        Triw_penyakit_sekarang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Triw_penyakit_sekarang.setColumns(20);
        Triw_penyakit_sekarang.setRows(5);
        Triw_penyakit_sekarang.setName("Triw_penyakit_sekarang"); // NOI18N
        Triw_penyakit_sekarang.setPreferredSize(new java.awt.Dimension(162, 200));
        Triw_penyakit_sekarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Triw_penyakit_sekarangKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Triw_penyakit_sekarang);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(174, 420, 680, 80);

        label21.setForeground(new java.awt.Color(0, 0, 0));
        label21.setText("Riwayat Penyakit Dahulu : ");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label21);
        label21.setBounds(0, 507, 170, 23);

        ChkHipertensi1.setBackground(new java.awt.Color(255, 255, 250));
        ChkHipertensi1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkHipertensi1.setForeground(new java.awt.Color(0, 0, 0));
        ChkHipertensi1.setText("Hipertensi");
        ChkHipertensi1.setBorderPainted(true);
        ChkHipertensi1.setBorderPaintedFlat(true);
        ChkHipertensi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkHipertensi1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHipertensi1.setName("ChkHipertensi1"); // NOI18N
        ChkHipertensi1.setOpaque(false);
        ChkHipertensi1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkHipertensi1);
        ChkHipertensi1.setBounds(50, 527, 74, 23);

        ChkDM1.setBackground(new java.awt.Color(255, 255, 250));
        ChkDM1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDM1.setForeground(new java.awt.Color(0, 0, 0));
        ChkDM1.setText("DM");
        ChkDM1.setBorderPainted(true);
        ChkDM1.setBorderPaintedFlat(true);
        ChkDM1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDM1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDM1.setName("ChkDM1"); // NOI18N
        ChkDM1.setOpaque(false);
        ChkDM1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDM1);
        ChkDM1.setBounds(135, 527, 40, 23);

        ChkPJK.setBackground(new java.awt.Color(255, 255, 250));
        ChkPJK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPJK.setForeground(new java.awt.Color(0, 0, 0));
        ChkPJK.setText("PJK");
        ChkPJK.setBorderPainted(true);
        ChkPJK.setBorderPaintedFlat(true);
        ChkPJK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPJK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPJK.setName("ChkPJK"); // NOI18N
        ChkPJK.setOpaque(false);
        ChkPJK.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPJK);
        ChkPJK.setBounds(186, 527, 40, 23);

        ChkAsma1.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsma1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsma1.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsma1.setText("Asma");
        ChkAsma1.setBorderPainted(true);
        ChkAsma1.setBorderPaintedFlat(true);
        ChkAsma1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsma1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsma1.setName("ChkAsma1"); // NOI18N
        ChkAsma1.setOpaque(false);
        ChkAsma1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAsma1);
        ChkAsma1.setBounds(237, 527, 50, 23);

        ChkStroke.setBackground(new java.awt.Color(255, 255, 250));
        ChkStroke.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkStroke.setForeground(new java.awt.Color(0, 0, 0));
        ChkStroke.setText("Stroke");
        ChkStroke.setBorderPainted(true);
        ChkStroke.setBorderPaintedFlat(true);
        ChkStroke.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkStroke.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkStroke.setName("ChkStroke"); // NOI18N
        ChkStroke.setOpaque(false);
        ChkStroke.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkStroke);
        ChkStroke.setBounds(300, 527, 60, 23);

        ChkLiver.setBackground(new java.awt.Color(255, 255, 250));
        ChkLiver.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLiver.setForeground(new java.awt.Color(0, 0, 0));
        ChkLiver.setText("Liver");
        ChkLiver.setBorderPainted(true);
        ChkLiver.setBorderPaintedFlat(true);
        ChkLiver.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLiver.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLiver.setName("ChkLiver"); // NOI18N
        ChkLiver.setOpaque(false);
        ChkLiver.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkLiver);
        ChkLiver.setBounds(365, 527, 50, 23);

        ChkGinjal.setBackground(new java.awt.Color(255, 255, 250));
        ChkGinjal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGinjal.setForeground(new java.awt.Color(0, 0, 0));
        ChkGinjal.setText("Ginjal");
        ChkGinjal.setBorderPainted(true);
        ChkGinjal.setBorderPaintedFlat(true);
        ChkGinjal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGinjal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGinjal.setName("ChkGinjal"); // NOI18N
        ChkGinjal.setOpaque(false);
        ChkGinjal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkGinjal);
        ChkGinjal.setBounds(420, 527, 50, 23);

        ChkTB.setBackground(new java.awt.Color(255, 255, 250));
        ChkTB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTB.setForeground(new java.awt.Color(0, 0, 0));
        ChkTB.setText("TB Paru");
        ChkTB.setBorderPainted(true);
        ChkTB.setBorderPaintedFlat(true);
        ChkTB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTB.setName("ChkTB"); // NOI18N
        ChkTB.setOpaque(false);
        ChkTB.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTB);
        ChkTB.setBounds(480, 527, 65, 23);

        ChkLain1.setBackground(new java.awt.Color(255, 255, 250));
        ChkLain1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLain1.setForeground(new java.awt.Color(0, 0, 0));
        ChkLain1.setText("Lain-lain");
        ChkLain1.setBorderPainted(true);
        ChkLain1.setBorderPaintedFlat(true);
        ChkLain1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLain1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLain1.setName("ChkLain1"); // NOI18N
        ChkLain1.setOpaque(false);
        ChkLain1.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkLain1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkLain1ActionPerformed(evt);
            }
        });
        FormInput.add(ChkLain1);
        ChkLain1.setBounds(550, 527, 65, 23);

        Tket_lain1.setForeground(new java.awt.Color(0, 0, 0));
        Tket_lain1.setName("Tket_lain1"); // NOI18N
        Tket_lain1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tket_lain1KeyPressed(evt);
            }
        });
        FormInput.add(Tket_lain1);
        Tket_lain1.setBounds(624, 527, 230, 23);

        cmbPernah.setForeground(new java.awt.Color(0, 0, 0));
        cmbPernah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Ya", "Tidak" }));
        cmbPernah.setName("cmbPernah"); // NOI18N
        cmbPernah.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbPernah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPernahActionPerformed(evt);
            }
        });
        FormInput.add(cmbPernah);
        cmbPernah.setBounds(136, 557, 60, 23);

        label22.setForeground(new java.awt.Color(0, 0, 0));
        label22.setText("Pernah Dirawat :");
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label22);
        label22.setBounds(0, 557, 130, 23);

        label23.setForeground(new java.awt.Color(0, 0, 0));
        label23.setText("Kapan : ");
        label23.setName("label23"); // NOI18N
        label23.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label23);
        label23.setBounds(200, 557, 70, 23);

        Tkapan.setForeground(new java.awt.Color(0, 0, 0));
        Tkapan.setName("Tkapan"); // NOI18N
        Tkapan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkapanKeyPressed(evt);
            }
        });
        FormInput.add(Tkapan);
        Tkapan.setBounds(272, 557, 582, 23);

        label24.setForeground(new java.awt.Color(0, 0, 0));
        label24.setText("Dimana : ");
        label24.setName("label24"); // NOI18N
        label24.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label24);
        label24.setBounds(200, 587, 70, 23);

        Tdimana.setForeground(new java.awt.Color(0, 0, 0));
        Tdimana.setName("Tdimana"); // NOI18N
        Tdimana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdimanaKeyPressed(evt);
            }
        });
        FormInput.add(Tdimana);
        Tdimana.setBounds(272, 587, 582, 23);

        label25.setForeground(new java.awt.Color(0, 0, 0));
        label25.setText("Diagnosis : ");
        label25.setName("label25"); // NOI18N
        label25.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label25);
        label25.setBounds(200, 617, 70, 23);

        Tdiagnosis.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosis.setName("Tdiagnosis"); // NOI18N
        Tdiagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosisKeyPressed(evt);
            }
        });
        FormInput.add(Tdiagnosis);
        Tdiagnosis.setBounds(272, 617, 582, 23);

        label26.setForeground(new java.awt.Color(0, 0, 0));
        label26.setText("Riwayat Penyakit Keluarga : ");
        label26.setName("label26"); // NOI18N
        label26.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label26);
        label26.setBounds(0, 647, 170, 23);

        ChkHipertensi2.setBackground(new java.awt.Color(255, 255, 250));
        ChkHipertensi2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkHipertensi2.setForeground(new java.awt.Color(0, 0, 0));
        ChkHipertensi2.setText("Hipertensi");
        ChkHipertensi2.setBorderPainted(true);
        ChkHipertensi2.setBorderPaintedFlat(true);
        ChkHipertensi2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkHipertensi2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHipertensi2.setName("ChkHipertensi2"); // NOI18N
        ChkHipertensi2.setOpaque(false);
        ChkHipertensi2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkHipertensi2);
        ChkHipertensi2.setBounds(50, 667, 74, 23);

        ChkDM2.setBackground(new java.awt.Color(255, 255, 250));
        ChkDM2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDM2.setForeground(new java.awt.Color(0, 0, 0));
        ChkDM2.setText("DM");
        ChkDM2.setBorderPainted(true);
        ChkDM2.setBorderPaintedFlat(true);
        ChkDM2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDM2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDM2.setName("ChkDM2"); // NOI18N
        ChkDM2.setOpaque(false);
        ChkDM2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDM2);
        ChkDM2.setBounds(135, 667, 40, 23);

        ChkJantung.setBackground(new java.awt.Color(255, 255, 250));
        ChkJantung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkJantung.setForeground(new java.awt.Color(0, 0, 0));
        ChkJantung.setText("Jantung");
        ChkJantung.setBorderPainted(true);
        ChkJantung.setBorderPaintedFlat(true);
        ChkJantung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkJantung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkJantung.setName("ChkJantung"); // NOI18N
        ChkJantung.setOpaque(false);
        ChkJantung.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkJantung);
        ChkJantung.setBounds(186, 667, 65, 23);

        ChkAsma2.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsma2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsma2.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsma2.setText("Asma");
        ChkAsma2.setBorderPainted(true);
        ChkAsma2.setBorderPaintedFlat(true);
        ChkAsma2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsma2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsma2.setName("ChkAsma2"); // NOI18N
        ChkAsma2.setOpaque(false);
        ChkAsma2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAsma2);
        ChkAsma2.setBounds(260, 667, 50, 23);

        ChkLain2.setBackground(new java.awt.Color(255, 255, 250));
        ChkLain2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLain2.setForeground(new java.awt.Color(0, 0, 0));
        ChkLain2.setText("Lain-lain");
        ChkLain2.setBorderPainted(true);
        ChkLain2.setBorderPaintedFlat(true);
        ChkLain2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLain2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLain2.setName("ChkLain2"); // NOI18N
        ChkLain2.setOpaque(false);
        ChkLain2.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkLain2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkLain2ActionPerformed(evt);
            }
        });
        FormInput.add(ChkLain2);
        ChkLain2.setBounds(330, 667, 62, 23);

        Tket_lain2.setForeground(new java.awt.Color(0, 0, 0));
        Tket_lain2.setName("Tket_lain2"); // NOI18N
        Tket_lain2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tket_lain2KeyPressed(evt);
            }
        });
        FormInput.add(Tket_lain2);
        Tket_lain2.setBounds(394, 667, 460, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        Triw_alergi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Triw_alergi.setColumns(20);
        Triw_alergi.setRows(5);
        Triw_alergi.setName("Triw_alergi"); // NOI18N
        Triw_alergi.setPreferredSize(new java.awt.Dimension(162, 200));
        Triw_alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Triw_alergiKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Triw_alergi);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(174, 697, 680, 80);

        label27.setForeground(new java.awt.Color(0, 0, 0));
        label27.setText("Riwayat Alergi : ");
        label27.setName("label27"); // NOI18N
        label27.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label27);
        label27.setBounds(0, 697, 170, 23);

        label28.setForeground(new java.awt.Color(0, 0, 0));
        label28.setText("PENILAIAN NYERI :");
        label28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label28.setName("label28"); // NOI18N
        label28.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label28);
        label28.setBounds(0, 783, 130, 23);

        ChkNyeri.setBackground(new java.awt.Color(255, 255, 250));
        ChkNyeri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNyeri.setForeground(new java.awt.Color(0, 0, 0));
        ChkNyeri.setText("Nyeri");
        ChkNyeri.setBorderPainted(true);
        ChkNyeri.setBorderPaintedFlat(true);
        ChkNyeri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNyeri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNyeri.setName("ChkNyeri"); // NOI18N
        ChkNyeri.setOpaque(false);
        ChkNyeri.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkNyeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkNyeriActionPerformed(evt);
            }
        });
        FormInput.add(ChkNyeri);
        ChkNyeri.setBounds(50, 803, 50, 23);

        label29.setForeground(new java.awt.Color(0, 0, 0));
        label29.setText("Lokasi : ");
        label29.setName("label29"); // NOI18N
        label29.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label29);
        label29.setBounds(110, 803, 60, 23);

        Tlokasi.setForeground(new java.awt.Color(0, 0, 0));
        Tlokasi.setName("Tlokasi"); // NOI18N
        Tlokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlokasiKeyPressed(evt);
            }
        });
        FormInput.add(Tlokasi);
        Tlokasi.setBounds(174, 803, 260, 23);

        label30.setForeground(new java.awt.Color(0, 0, 0));
        label30.setText("Intensitas : ");
        label30.setName("label30"); // NOI18N
        label30.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label30);
        label30.setBounds(440, 803, 70, 23);

        Tintensitas.setForeground(new java.awt.Color(0, 0, 0));
        Tintensitas.setName("Tintensitas"); // NOI18N
        Tintensitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TintensitasKeyPressed(evt);
            }
        });
        FormInput.add(Tintensitas);
        Tintensitas.setBounds(515, 803, 340, 23);

        label31.setForeground(new java.awt.Color(0, 0, 0));
        label31.setText("Skor : ");
        label31.setName("label31"); // NOI18N
        label31.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label31);
        label31.setBounds(0, 833, 80, 23);

        Tskor.setForeground(new java.awt.Color(0, 0, 0));
        Tskor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tskor.setName("Tskor"); // NOI18N
        Tskor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorKeyPressed(evt);
            }
        });
        FormInput.add(Tskor);
        Tskor.setBounds(84, 833, 40, 23);

        label32.setForeground(new java.awt.Color(0, 0, 0));
        label32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label32.setText("(Metode VAS)       Jenis : ");
        label32.setName("label32"); // NOI18N
        label32.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label32);
        label32.setBounds(130, 833, 123, 23);

        cmbJenis.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Akut", "Kronis" }));
        cmbJenis.setName("cmbJenis"); // NOI18N
        cmbJenis.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbJenis);
        cmbJenis.setBounds(255, 833, 60, 23);

        label33.setForeground(new java.awt.Color(0, 0, 0));
        label33.setText("TANDA VITAL :");
        label33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label33.setName("label33"); // NOI18N
        label33.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label33);
        label33.setBounds(0, 863, 130, 23);

        label34.setForeground(new java.awt.Color(0, 0, 0));
        label34.setText("Keadaan Umum : ");
        label34.setName("label34"); // NOI18N
        label34.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label34);
        label34.setBounds(0, 883, 130, 23);

        cmbKeadaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKeadaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Baik", "Sedang", "Lemah", "Jelek" }));
        cmbKeadaan.setName("cmbKeadaan"); // NOI18N
        cmbKeadaan.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbKeadaan);
        cmbKeadaan.setBounds(134, 883, 76, 23);

        label35.setForeground(new java.awt.Color(0, 0, 0));
        label35.setText("Gizi : ");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label35);
        label35.setBounds(210, 883, 40, 23);

        cmbGizi.setForeground(new java.awt.Color(0, 0, 0));
        cmbGizi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Baik", "Kurang", "Buruk" }));
        cmbGizi.setName("cmbGizi"); // NOI18N
        cmbGizi.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbGizi);
        cmbGizi.setBounds(254, 883, 70, 23);

        label36.setForeground(new java.awt.Color(0, 0, 0));
        label36.setText("GCS : E : ");
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label36);
        label36.setBounds(325, 883, 60, 23);

        Tgcse.setForeground(new java.awt.Color(0, 0, 0));
        Tgcse.setName("Tgcse"); // NOI18N
        Tgcse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcseKeyPressed(evt);
            }
        });
        FormInput.add(Tgcse);
        Tgcse.setBounds(390, 883, 80, 23);

        label37.setForeground(new java.awt.Color(0, 0, 0));
        label37.setText("M : ");
        label37.setName("label37"); // NOI18N
        label37.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label37);
        label37.setBounds(470, 883, 30, 23);

        Tgcsm.setForeground(new java.awt.Color(0, 0, 0));
        Tgcsm.setName("Tgcsm"); // NOI18N
        Tgcsm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsmKeyPressed(evt);
            }
        });
        FormInput.add(Tgcsm);
        Tgcsm.setBounds(503, 883, 80, 23);

        label38.setForeground(new java.awt.Color(0, 0, 0));
        label38.setText("V : ");
        label38.setName("label38"); // NOI18N
        label38.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label38);
        label38.setBounds(585, 883, 30, 23);

        Tgcsv.setForeground(new java.awt.Color(0, 0, 0));
        Tgcsv.setName("Tgcsv"); // NOI18N
        Tgcsv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsvKeyPressed(evt);
            }
        });
        FormInput.add(Tgcsv);
        Tgcsv.setBounds(618, 883, 80, 23);

        ChkTindakanResus.setBackground(new java.awt.Color(255, 255, 250));
        ChkTindakanResus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTindakanResus.setForeground(new java.awt.Color(0, 0, 0));
        ChkTindakanResus.setText("Tindakan Resusitasi");
        ChkTindakanResus.setBorderPainted(true);
        ChkTindakanResus.setBorderPaintedFlat(true);
        ChkTindakanResus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTindakanResus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTindakanResus.setName("ChkTindakanResus"); // NOI18N
        ChkTindakanResus.setOpaque(false);
        ChkTindakanResus.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTindakanResus);
        ChkTindakanResus.setBounds(710, 883, 140, 23);

        label39.setForeground(new java.awt.Color(0, 0, 0));
        label39.setText("Berat Badan : ");
        label39.setName("label39"); // NOI18N
        label39.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label39);
        label39.setBounds(0, 913, 130, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        FormInput.add(Tbb);
        Tbb.setBounds(134, 913, 48, 23);

        label40.setForeground(new java.awt.Color(0, 0, 0));
        label40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label40.setText("Kg.      Tinggi Badan : ");
        label40.setName("label40"); // NOI18N
        label40.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label40);
        label40.setBounds(187, 913, 107, 23);

        Ttb.setForeground(new java.awt.Color(0, 0, 0));
        Ttb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ttb.setName("Ttb"); // NOI18N
        Ttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbKeyPressed(evt);
            }
        });
        FormInput.add(Ttb);
        Ttb.setBounds(295, 913, 48, 23);

        label41.setForeground(new java.awt.Color(0, 0, 0));
        label41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label41.setText("Cm.     Tekanan Darah : ");
        label41.setName("label41"); // NOI18N
        label41.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label41);
        label41.setBounds(347, 913, 120, 23);

        Ttd.setForeground(new java.awt.Color(0, 0, 0));
        Ttd.setName("Ttd"); // NOI18N
        Ttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdKeyPressed(evt);
            }
        });
        FormInput.add(Ttd);
        Ttd.setBounds(467, 913, 70, 23);

        label42.setForeground(new java.awt.Color(0, 0, 0));
        label42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label42.setText("mmHg,     Nadi : ");
        label42.setName("label42"); // NOI18N
        label42.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label42);
        label42.setBounds(544, 913, 80, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(626, 913, 70, 23);

        label43.setForeground(new java.awt.Color(0, 0, 0));
        label43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label43.setText("x/mnt");
        label43.setName("label43"); // NOI18N
        label43.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label43);
        label43.setBounds(704, 913, 40, 23);

        label44.setForeground(new java.awt.Color(0, 0, 0));
        label44.setText("Respirasi : ");
        label44.setName("label44"); // NOI18N
        label44.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label44);
        label44.setBounds(0, 943, 130, 23);

        Trespi.setForeground(new java.awt.Color(0, 0, 0));
        Trespi.setName("Trespi"); // NOI18N
        Trespi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrespiKeyPressed(evt);
            }
        });
        FormInput.add(Trespi);
        Trespi.setBounds(134, 943, 70, 23);

        label45.setForeground(new java.awt.Color(0, 0, 0));
        label45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label45.setText("x/mnt     Suhu : Axila/Rektal : ");
        label45.setName("label45"); // NOI18N
        label45.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label45);
        label45.setBounds(211, 943, 145, 23);

        Taxila.setForeground(new java.awt.Color(0, 0, 0));
        Taxila.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Taxila.setName("Taxila"); // NOI18N
        Taxila.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TaxilaKeyPressed(evt);
            }
        });
        FormInput.add(Taxila);
        Taxila.setBounds(357, 943, 48, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("°C /");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(412, 943, 24, 23);

        Trektal.setForeground(new java.awt.Color(0, 0, 0));
        Trektal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Trektal.setName("Trektal"); // NOI18N
        Trektal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrektalKeyPressed(evt);
            }
        });
        FormInput.add(Trektal);
        Trektal.setBounds(437, 943, 48, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("°C");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(492, 943, 24, 23);

        label46.setForeground(new java.awt.Color(0, 0, 0));
        label46.setText("PEMERIKSAAN FISIK :");
        label46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label46.setName("label46"); // NOI18N
        label46.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label46);
        label46.setBounds(0, 973, 140, 23);

        label47.setForeground(new java.awt.Color(0, 0, 0));
        label47.setText("Mata : Anemis : ");
        label47.setName("label47"); // NOI18N
        label47.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label47);
        label47.setBounds(0, 993, 130, 23);

        cmbMata.setForeground(new java.awt.Color(0, 0, 0));
        cmbMata.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ada" }));
        cmbMata.setName("cmbMata"); // NOI18N
        cmbMata.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbMata);
        cmbMata.setBounds(134, 993, 60, 23);

        label48.setForeground(new java.awt.Color(0, 0, 0));
        label48.setText("Ikterik : ");
        label48.setName("label48"); // NOI18N
        label48.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label48);
        label48.setBounds(200, 993, 50, 23);

        cmbIkterik.setForeground(new java.awt.Color(0, 0, 0));
        cmbIkterik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ada" }));
        cmbIkterik.setName("cmbIkterik"); // NOI18N
        cmbIkterik.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbIkterik);
        cmbIkterik.setBounds(254, 993, 60, 23);

        label49.setForeground(new java.awt.Color(0, 0, 0));
        label49.setText("Pupil : ");
        label49.setName("label49"); // NOI18N
        label49.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label49);
        label49.setBounds(320, 993, 50, 23);

        cmbPupil.setForeground(new java.awt.Color(0, 0, 0));
        cmbPupil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Anisokor", "Isokor" }));
        cmbPupil.setName("cmbPupil"); // NOI18N
        cmbPupil.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbPupil);
        cmbPupil.setBounds(374, 993, 74, 23);

        label50.setForeground(new java.awt.Color(0, 0, 0));
        label50.setText("Diameter Kanan : ");
        label50.setName("label50"); // NOI18N
        label50.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label50);
        label50.setBounds(450, 993, 100, 23);

        Tdiameter_kanan.setForeground(new java.awt.Color(0, 0, 0));
        Tdiameter_kanan.setName("Tdiameter_kanan"); // NOI18N
        Tdiameter_kanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdiameter_kananKeyPressed(evt);
            }
        });
        FormInput.add(Tdiameter_kanan);
        Tdiameter_kanan.setBounds(552, 993, 48, 23);

        label51.setForeground(new java.awt.Color(0, 0, 0));
        label51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label51.setText("mm     Diameter Kiri : ");
        label51.setName("label51"); // NOI18N
        label51.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label51);
        label51.setBounds(605, 993, 103, 23);

        Tdiameter_kiri.setForeground(new java.awt.Color(0, 0, 0));
        Tdiameter_kiri.setName("Tdiameter_kiri"); // NOI18N
        FormInput.add(Tdiameter_kiri);
        Tdiameter_kiri.setBounds(708, 993, 48, 23);

        label52.setForeground(new java.awt.Color(0, 0, 0));
        label52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label52.setText("mm");
        label52.setName("label52"); // NOI18N
        label52.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label52);
        label52.setBounds(762, 993, 30, 23);

        label53.setForeground(new java.awt.Color(0, 0, 0));
        label53.setText("Udem Palpebrae : ");
        label53.setName("label53"); // NOI18N
        label53.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label53);
        label53.setBounds(0, 1023, 130, 23);

        cmbUdem_palpe.setForeground(new java.awt.Color(0, 0, 0));
        cmbUdem_palpe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ada" }));
        cmbUdem_palpe.setName("cmbUdem_palpe"); // NOI18N
        cmbUdem_palpe.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbUdem_palpe);
        cmbUdem_palpe.setBounds(134, 1023, 60, 23);

        label54.setForeground(new java.awt.Color(0, 0, 0));
        label54.setText("THT : Tonsil : ");
        label54.setName("label54"); // NOI18N
        label54.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label54);
        label54.setBounds(200, 1023, 80, 23);

        Ttonsil.setForeground(new java.awt.Color(0, 0, 0));
        Ttonsil.setName("Ttonsil"); // NOI18N
        Ttonsil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtonsilKeyPressed(evt);
            }
        });
        FormInput.add(Ttonsil);
        Ttonsil.setBounds(282, 1023, 250, 23);

        label55.setForeground(new java.awt.Color(0, 0, 0));
        label55.setText("Faring : ");
        label55.setName("label55"); // NOI18N
        label55.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label55);
        label55.setBounds(535, 1023, 50, 23);

        Tfaring.setForeground(new java.awt.Color(0, 0, 0));
        Tfaring.setName("Tfaring"); // NOI18N
        Tfaring.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TfaringKeyPressed(evt);
            }
        });
        FormInput.add(Tfaring);
        Tfaring.setBounds(586, 1023, 260, 23);

        label56.setForeground(new java.awt.Color(0, 0, 0));
        label56.setText("Lidah : ");
        label56.setName("label56"); // NOI18N
        label56.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label56);
        label56.setBounds(230, 1053, 50, 23);

        Tlidah.setForeground(new java.awt.Color(0, 0, 0));
        Tlidah.setName("Tlidah"); // NOI18N
        Tlidah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlidahKeyPressed(evt);
            }
        });
        FormInput.add(Tlidah);
        Tlidah.setBounds(282, 1053, 250, 23);

        label57.setForeground(new java.awt.Color(0, 0, 0));
        label57.setText("Bibir : ");
        label57.setName("label57"); // NOI18N
        label57.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label57);
        label57.setBounds(535, 1053, 50, 23);

        Tbibir.setForeground(new java.awt.Color(0, 0, 0));
        Tbibir.setName("Tbibir"); // NOI18N
        Tbibir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbibirKeyPressed(evt);
            }
        });
        FormInput.add(Tbibir);
        Tbibir.setBounds(586, 1053, 260, 23);

        label58.setForeground(new java.awt.Color(0, 0, 0));
        label58.setText("Leher : JVP : ");
        label58.setName("label58"); // NOI18N
        label58.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label58);
        label58.setBounds(0, 1083, 130, 23);

        Tjvp.setForeground(new java.awt.Color(0, 0, 0));
        Tjvp.setName("Tjvp"); // NOI18N
        Tjvp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjvpKeyPressed(evt);
            }
        });
        FormInput.add(Tjvp);
        Tjvp.setBounds(134, 1083, 250, 23);

        label59.setForeground(new java.awt.Color(0, 0, 0));
        label59.setText("Pembesaran Kelenjar Limfe : ");
        label59.setName("label59"); // NOI18N
        label59.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label59);
        label59.setBounds(385, 1083, 155, 23);

        cmbKelenjar.setForeground(new java.awt.Color(0, 0, 0));
        cmbKelenjar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ada" }));
        cmbKelenjar.setName("cmbKelenjar"); // NOI18N
        cmbKelenjar.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbKelenjar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKelenjarActionPerformed(evt);
            }
        });
        FormInput.add(cmbKelenjar);
        cmbKelenjar.setBounds(542, 1083, 60, 23);

        Tkelenjar.setForeground(new java.awt.Color(0, 0, 0));
        Tkelenjar.setName("Tkelenjar"); // NOI18N
        Tkelenjar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkelenjarKeyPressed(evt);
            }
        });
        FormInput.add(Tkelenjar);
        Tkelenjar.setBounds(606, 1083, 240, 23);

        label60.setForeground(new java.awt.Color(0, 0, 0));
        label60.setText("Kaku Kuduk : ");
        label60.setName("label60"); // NOI18N
        label60.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label60);
        label60.setBounds(0, 1113, 130, 23);

        cmbKaku.setForeground(new java.awt.Color(0, 0, 0));
        cmbKaku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ada" }));
        cmbKaku.setName("cmbKaku"); // NOI18N
        cmbKaku.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbKaku);
        cmbKaku.setBounds(134, 1113, 60, 23);

        label61.setForeground(new java.awt.Color(0, 0, 0));
        label61.setText("Thoraks : ");
        label61.setName("label61"); // NOI18N
        label61.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label61);
        label61.setBounds(200, 1113, 60, 23);

        cmbThoraks.setForeground(new java.awt.Color(0, 0, 0));
        cmbThoraks.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Simetris", "Asimetris" }));
        cmbThoraks.setName("cmbThoraks"); // NOI18N
        cmbThoraks.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbThoraks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbThoraksActionPerformed(evt);
            }
        });
        FormInput.add(cmbThoraks);
        cmbThoraks.setBounds(264, 1113, 80, 23);

        Tasimetris.setForeground(new java.awt.Color(0, 0, 0));
        Tasimetris.setName("Tasimetris"); // NOI18N
        Tasimetris.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TasimetrisKeyPressed(evt);
            }
        });
        FormInput.add(Tasimetris);
        Tasimetris.setBounds(351, 1113, 495, 23);

        label62.setForeground(new java.awt.Color(0, 0, 0));
        label62.setText("Cor : S1/S2 ");
        label62.setName("label62"); // NOI18N
        label62.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label62);
        label62.setBounds(0, 1143, 130, 23);

        Tcor.setForeground(new java.awt.Color(0, 0, 0));
        Tcor.setName("Tcor"); // NOI18N
        Tcor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcorKeyPressed(evt);
            }
        });
        FormInput.add(Tcor);
        Tcor.setBounds(134, 1143, 505, 23);

        ChkReguler.setBackground(new java.awt.Color(255, 255, 250));
        ChkReguler.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(ChkReguler);
        ChkReguler.setForeground(new java.awt.Color(0, 0, 0));
        ChkReguler.setText("Reguler");
        ChkReguler.setBorderPainted(true);
        ChkReguler.setBorderPaintedFlat(true);
        ChkReguler.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkReguler.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkReguler.setName("ChkReguler"); // NOI18N
        ChkReguler.setOpaque(false);
        ChkReguler.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkReguler);
        ChkReguler.setBounds(134, 1173, 65, 23);

        ChkIreguler.setBackground(new java.awt.Color(255, 255, 250));
        ChkIreguler.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(ChkIreguler);
        ChkIreguler.setForeground(new java.awt.Color(0, 0, 0));
        ChkIreguler.setText("Ireguler, Murmur : ");
        ChkIreguler.setBorderPainted(true);
        ChkIreguler.setBorderPaintedFlat(true);
        ChkIreguler.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkIreguler.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkIreguler.setName("ChkIreguler"); // NOI18N
        ChkIreguler.setOpaque(false);
        ChkIreguler.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkIreguler);
        ChkIreguler.setBounds(210, 1173, 110, 23);

        Tmurmur.setForeground(new java.awt.Color(0, 0, 0));
        Tmurmur.setName("Tmurmur"); // NOI18N
        Tmurmur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmurmurKeyPressed(evt);
            }
        });
        FormInput.add(Tmurmur);
        Tmurmur.setBounds(320, 1173, 319, 23);

        label63.setForeground(new java.awt.Color(0, 0, 0));
        label63.setText("Lain-lain : ");
        label63.setName("label63"); // NOI18N
        label63.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label63);
        label63.setBounds(0, 1203, 130, 23);

        Tlain_lain.setForeground(new java.awt.Color(0, 0, 0));
        Tlain_lain.setName("Tlain_lain"); // NOI18N
        Tlain_lain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tlain_lainKeyPressed(evt);
            }
        });
        FormInput.add(Tlain_lain);
        Tlain_lain.setBounds(134, 1203, 505, 23);

        label64.setForeground(new java.awt.Color(0, 0, 0));
        label64.setText("Pulmo : Suara Nafas : ");
        label64.setName("label64"); // NOI18N
        label64.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label64);
        label64.setBounds(0, 1233, 130, 23);

        Tsuara_nfs.setForeground(new java.awt.Color(0, 0, 0));
        Tsuara_nfs.setName("Tsuara_nfs"); // NOI18N
        Tsuara_nfs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tsuara_nfsKeyPressed(evt);
            }
        });
        FormInput.add(Tsuara_nfs);
        Tsuara_nfs.setBounds(134, 1233, 505, 23);

        label65.setForeground(new java.awt.Color(0, 0, 0));
        label65.setText("Ronchi : ");
        label65.setName("label65"); // NOI18N
        label65.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label65);
        label65.setBounds(0, 1263, 130, 23);

        cmbRonchi.setForeground(new java.awt.Color(0, 0, 0));
        cmbRonchi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ada" }));
        cmbRonchi.setName("cmbRonchi"); // NOI18N
        cmbRonchi.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbRonchi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRonchiActionPerformed(evt);
            }
        });
        FormInput.add(cmbRonchi);
        cmbRonchi.setBounds(134, 1263, 60, 23);

        Tronchi.setForeground(new java.awt.Color(0, 0, 0));
        Tronchi.setName("Tronchi"); // NOI18N
        Tronchi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TronchiKeyPressed(evt);
            }
        });
        FormInput.add(Tronchi);
        Tronchi.setBounds(199, 1263, 440, 23);

        label66.setForeground(new java.awt.Color(0, 0, 0));
        label66.setText("Wheezing : ");
        label66.setName("label66"); // NOI18N
        label66.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label66);
        label66.setBounds(0, 1293, 130, 23);

        cmbWhezing.setForeground(new java.awt.Color(0, 0, 0));
        cmbWhezing.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ada" }));
        cmbWhezing.setName("cmbWhezing"); // NOI18N
        cmbWhezing.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbWhezing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbWhezingActionPerformed(evt);
            }
        });
        FormInput.add(cmbWhezing);
        cmbWhezing.setBounds(134, 1293, 60, 23);

        Twhezing.setForeground(new java.awt.Color(0, 0, 0));
        Twhezing.setName("Twhezing"); // NOI18N
        Twhezing.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TwhezingKeyPressed(evt);
            }
        });
        FormInput.add(Twhezing);
        Twhezing.setBounds(199, 1293, 440, 23);

        label67.setForeground(new java.awt.Color(0, 0, 0));
        label67.setText("Abdomen : Distended : ");
        label67.setName("label67"); // NOI18N
        label67.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label67);
        label67.setBounds(0, 1323, 130, 23);

        cmbDisten.setForeground(new java.awt.Color(0, 0, 0));
        cmbDisten.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ada" }));
        cmbDisten.setName("cmbDisten"); // NOI18N
        cmbDisten.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbDisten);
        cmbDisten.setBounds(134, 1323, 60, 23);

        label68.setForeground(new java.awt.Color(0, 0, 0));
        label68.setText("Meteorismus : ");
        label68.setName("label68"); // NOI18N
        label68.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label68);
        label68.setBounds(200, 1323, 90, 23);

        cmbMeteo.setForeground(new java.awt.Color(0, 0, 0));
        cmbMeteo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ada" }));
        cmbMeteo.setName("cmbMeteo"); // NOI18N
        cmbMeteo.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbMeteo);
        cmbMeteo.setBounds(293, 1323, 60, 23);

        label69.setForeground(new java.awt.Color(0, 0, 0));
        label69.setText("Peristaltik : ");
        label69.setName("label69"); // NOI18N
        label69.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label69);
        label69.setBounds(360, 1323, 70, 23);

        cmbPeris.setForeground(new java.awt.Color(0, 0, 0));
        cmbPeris.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Normal", "Meningkat", "Menurun", "Tidak Ada" }));
        cmbPeris.setName("cmbPeris"); // NOI18N
        cmbPeris.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbPeris);
        cmbPeris.setBounds(433, 1323, 83, 23);

        label70.setForeground(new java.awt.Color(0, 0, 0));
        label70.setText("Asites : ");
        label70.setName("label70"); // NOI18N
        label70.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label70);
        label70.setBounds(520, 1323, 50, 23);

        cmbAsites.setForeground(new java.awt.Color(0, 0, 0));
        cmbAsites.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ada" }));
        cmbAsites.setName("cmbAsites"); // NOI18N
        cmbAsites.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbAsites);
        cmbAsites.setBounds(575, 1323, 60, 23);

        label71.setForeground(new java.awt.Color(0, 0, 0));
        label71.setText("Nyeri Tekan : ");
        label71.setName("label71"); // NOI18N
        label71.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label71);
        label71.setBounds(0, 1353, 130, 23);

        cmbNyeri.setForeground(new java.awt.Color(0, 0, 0));
        cmbNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ada" }));
        cmbNyeri.setName("cmbNyeri"); // NOI18N
        cmbNyeri.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbNyeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNyeriActionPerformed(evt);
            }
        });
        FormInput.add(cmbNyeri);
        cmbNyeri.setBounds(134, 1353, 60, 23);

        label72.setForeground(new java.awt.Color(0, 0, 0));
        label72.setText("Lokasi : ");
        label72.setName("label72"); // NOI18N
        label72.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label72);
        label72.setBounds(200, 1353, 57, 23);

        Tlokasi_nyeri.setForeground(new java.awt.Color(0, 0, 0));
        Tlokasi_nyeri.setName("Tlokasi_nyeri"); // NOI18N
        Tlokasi_nyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tlokasi_nyeriKeyPressed(evt);
            }
        });
        FormInput.add(Tlokasi_nyeri);
        Tlokasi_nyeri.setBounds(259, 1353, 380, 23);

        label73.setForeground(new java.awt.Color(0, 0, 0));
        label73.setText("Hepar : ");
        label73.setName("label73"); // NOI18N
        label73.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label73);
        label73.setBounds(0, 1383, 130, 23);

        Thepar.setForeground(new java.awt.Color(0, 0, 0));
        Thepar.setName("Thepar"); // NOI18N
        Thepar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TheparKeyPressed(evt);
            }
        });
        FormInput.add(Thepar);
        Thepar.setBounds(134, 1383, 505, 23);

        label74.setForeground(new java.awt.Color(0, 0, 0));
        label74.setText("Lien : ");
        label74.setName("label74"); // NOI18N
        label74.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label74);
        label74.setBounds(0, 1413, 130, 23);

        Tlien.setForeground(new java.awt.Color(0, 0, 0));
        Tlien.setName("Tlien"); // NOI18N
        Tlien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlienKeyPressed(evt);
            }
        });
        FormInput.add(Tlien);
        Tlien.setBounds(134, 1413, 505, 23);

        label75.setForeground(new java.awt.Color(0, 0, 0));
        label75.setText("Extremitas : ");
        label75.setName("label75"); // NOI18N
        label75.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label75);
        label75.setBounds(0, 1443, 130, 23);

        cmbExtrem.setForeground(new java.awt.Color(0, 0, 0));
        cmbExtrem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Hangat", "Dingin" }));
        cmbExtrem.setName("cmbExtrem"); // NOI18N
        cmbExtrem.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbExtrem);
        cmbExtrem.setBounds(134, 1443, 70, 23);

        label76.setForeground(new java.awt.Color(0, 0, 0));
        label76.setText("Udem : ");
        label76.setName("label76"); // NOI18N
        label76.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label76);
        label76.setBounds(210, 1443, 50, 23);

        cmbUdem.setForeground(new java.awt.Color(0, 0, 0));
        cmbUdem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ada" }));
        cmbUdem.setName("cmbUdem"); // NOI18N
        cmbUdem.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbUdem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUdemActionPerformed(evt);
            }
        });
        FormInput.add(cmbUdem);
        cmbUdem.setBounds(263, 1443, 60, 23);

        Tudem.setForeground(new java.awt.Color(0, 0, 0));
        Tudem.setName("Tudem"); // NOI18N
        Tudem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TudemKeyPressed(evt);
            }
        });
        FormInput.add(Tudem);
        Tudem.setBounds(329, 1443, 310, 23);

        PanelWall1.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/abdomen_form.jpg"))); // NOI18N
        PanelWall1.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall1.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall1.setRound(false);
        PanelWall1.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall1.setLayout(null);
        FormInput.add(PanelWall1);
        PanelWall1.setBounds(660, 1320, 170, 170);

        label77.setForeground(new java.awt.Color(0, 0, 0));
        label77.setText("HASIL PEMERIKSAAN PENUNJANG :");
        label77.setComponentPopupMenu(jPopupMenu1);
        label77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label77.setName("label77"); // NOI18N
        label77.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label77);
        label77.setBounds(0, 1508, 210, 23);

        scrollPane15.setComponentPopupMenu(jPopupMenu1);
        scrollPane15.setName("scrollPane15"); // NOI18N

        Thsl_pemeriksaan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Thsl_pemeriksaan.setColumns(20);
        Thsl_pemeriksaan.setRows(5);
        Thsl_pemeriksaan.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang");
        Thsl_pemeriksaan.setComponentPopupMenu(jPopupMenu1);
        Thsl_pemeriksaan.setName("Thsl_pemeriksaan"); // NOI18N
        Thsl_pemeriksaan.setPreferredSize(new java.awt.Dimension(162, 200));
        Thsl_pemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Thsl_pemeriksaanKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(Thsl_pemeriksaan);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(24, 1529, 830, 80);

        label78.setForeground(new java.awt.Color(0, 0, 0));
        label78.setText("DIAGNOSIS :");
        label78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label78.setName("label78"); // NOI18N
        label78.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label78);
        label78.setBounds(0, 1615, 100, 23);

        Tdiagnosa1.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosa1.setName("Tdiagnosa1"); // NOI18N
        Tdiagnosa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdiagnosa1KeyPressed(evt);
            }
        });
        FormInput.add(Tdiagnosa1);
        Tdiagnosa1.setBounds(49, 1635, 380, 23);

        label79.setForeground(new java.awt.Color(0, 0, 0));
        label79.setText("1. ");
        label79.setName("label79"); // NOI18N
        label79.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label79);
        label79.setBounds(0, 1635, 47, 23);

        label80.setForeground(new java.awt.Color(0, 0, 0));
        label80.setText("2. ");
        label80.setName("label80"); // NOI18N
        label80.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label80);
        label80.setBounds(0, 1665, 47, 23);

        Tdiagnosa2.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosa2.setName("Tdiagnosa2"); // NOI18N
        Tdiagnosa2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdiagnosa2KeyPressed(evt);
            }
        });
        FormInput.add(Tdiagnosa2);
        Tdiagnosa2.setBounds(49, 1665, 380, 23);

        label81.setForeground(new java.awt.Color(0, 0, 0));
        label81.setText("3. ");
        label81.setName("label81"); // NOI18N
        label81.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label81);
        label81.setBounds(0, 1695, 47, 23);

        Tdiagnosa3.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosa3.setName("Tdiagnosa3"); // NOI18N
        Tdiagnosa3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdiagnosa3KeyPressed(evt);
            }
        });
        FormInput.add(Tdiagnosa3);
        Tdiagnosa3.setBounds(49, 1695, 380, 23);

        label82.setForeground(new java.awt.Color(0, 0, 0));
        label82.setText("4. ");
        label82.setName("label82"); // NOI18N
        label82.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label82);
        label82.setBounds(0, 1725, 47, 23);

        Tdiagnosa4.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosa4.setName("Tdiagnosa4"); // NOI18N
        Tdiagnosa4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdiagnosa4KeyPressed(evt);
            }
        });
        FormInput.add(Tdiagnosa4);
        Tdiagnosa4.setBounds(49, 1725, 380, 23);

        label83.setForeground(new java.awt.Color(0, 0, 0));
        label83.setText("RENCANA KERJA DAN TERAPI :");
        label83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label83.setName("label83"); // NOI18N
        label83.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label83);
        label83.setBounds(0, 1755, 190, 23);

        scrollPane16.setName("scrollPane16"); // NOI18N

        Trencana_kerja.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Trencana_kerja.setColumns(20);
        Trencana_kerja.setRows(5);
        Trencana_kerja.setName("Trencana_kerja"); // NOI18N
        Trencana_kerja.setPreferredSize(new java.awt.Dimension(162, 9000));
        Trencana_kerja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Trencana_kerjaKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(Trencana_kerja);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(24, 1775, 830, 480);

        label84.setForeground(new java.awt.Color(0, 0, 0));
        label84.setText("PERENCANAAN PEMULANGAN PASIEN :");
        label84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label84.setName("label84"); // NOI18N
        label84.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label84);
        label84.setBounds(0, 2260, 230, 23);

        scrollPane17.setName("scrollPane17"); // NOI18N

        Trencana_pulang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Trencana_pulang.setColumns(20);
        Trencana_pulang.setRows(5);
        Trencana_pulang.setName("Trencana_pulang"); // NOI18N
        Trencana_pulang.setPreferredSize(new java.awt.Dimension(162, 2000));
        Trencana_pulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Trencana_pulangKeyPressed(evt);
            }
        });
        scrollPane17.setViewportView(Trencana_pulang);

        FormInput.add(scrollPane17);
        scrollPane17.setBounds(24, 2280, 830, 80);

        label85.setForeground(new java.awt.Color(0, 0, 0));
        label85.setText("CATATAN PENTING :");
        label85.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label85.setName("label85"); // NOI18N
        label85.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label85);
        label85.setBounds(0, 2368, 132, 23);

        scrollPane18.setName("scrollPane18"); // NOI18N

        Tcatatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tcatatan.setColumns(20);
        Tcatatan.setRows(5);
        Tcatatan.setName("Tcatatan"); // NOI18N
        Tcatatan.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tcatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanKeyPressed(evt);
            }
        });
        scrollPane18.setViewportView(Tcatatan);

        FormInput.add(scrollPane18);
        scrollPane18.setBounds(24, 2388, 830, 80);

        label86.setForeground(new java.awt.Color(0, 0, 0));
        label86.setText("Tanggal / Jam : ");
        label86.setName("label86"); // NOI18N
        label86.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label86);
        label86.setBounds(625, 2475, 100, 23);

        TglDpjp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-10-2024 16:10:36" }));
        TglDpjp.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglDpjp.setName("TglDpjp"); // NOI18N
        TglDpjp.setOpaque(false);
        FormInput.add(TglDpjp);
        TglDpjp.setBounds(725, 2475, 130, 23);

        BtnKamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKamar.setMnemonic('2');
        BtnKamar.setToolTipText("Alt+2");
        BtnKamar.setName("BtnKamar"); // NOI18N
        BtnKamar.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKamarActionPerformed(evt);
            }
        });
        FormInput.add(BtnKamar);
        BtnKamar.setBounds(825, 90, 28, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("(Klik kanan pada halaman ini untuk melihat hasil pemeriksaan penunjang)");
        jLabel47.setComponentPopupMenu(jPopupMenu1);
        jLabel47.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(220, 1508, 380, 23);

        Tkdkamar.setEditable(false);
        Tkdkamar.setForeground(new java.awt.Color(0, 0, 0));
        Tkdkamar.setName("Tkdkamar"); // NOI18N
        Tkdkamar.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(Tkdkamar);
        Tkdkamar.setBounds(154, 90, 130, 23);

        BtnRiwPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        BtnRiwPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnRiwPenyakit.setMnemonic('2');
        BtnRiwPenyakit.setText("Template");
        BtnRiwPenyakit.setToolTipText("Alt+2");
        BtnRiwPenyakit.setName("BtnRiwPenyakit"); // NOI18N
        BtnRiwPenyakit.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnRiwPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRiwPenyakitActionPerformed(evt);
            }
        });
        FormInput.add(BtnRiwPenyakit);
        BtnRiwPenyakit.setBounds(860, 420, 100, 23);

        BtnRiwAlergi.setForeground(new java.awt.Color(0, 0, 0));
        BtnRiwAlergi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnRiwAlergi.setMnemonic('2');
        BtnRiwAlergi.setText("Template");
        BtnRiwAlergi.setToolTipText("Alt+2");
        BtnRiwAlergi.setName("BtnRiwAlergi"); // NOI18N
        BtnRiwAlergi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnRiwAlergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRiwAlergiActionPerformed(evt);
            }
        });
        FormInput.add(BtnRiwAlergi);
        BtnRiwAlergi.setBounds(860, 697, 100, 23);

        BtnHasil.setForeground(new java.awt.Color(0, 0, 0));
        BtnHasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnHasil.setMnemonic('2');
        BtnHasil.setText("Template");
        BtnHasil.setToolTipText("Alt+2");
        BtnHasil.setName("BtnHasil"); // NOI18N
        BtnHasil.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHasilActionPerformed(evt);
            }
        });
        FormInput.add(BtnHasil);
        BtnHasil.setBounds(860, 1529, 100, 23);

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
        BtnRencana.setBounds(860, 1775, 100, 23);

        BtnPerencana.setForeground(new java.awt.Color(0, 0, 0));
        BtnPerencana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPerencana.setMnemonic('2');
        BtnPerencana.setText("Template");
        BtnPerencana.setToolTipText("Alt+2");
        BtnPerencana.setName("BtnPerencana"); // NOI18N
        BtnPerencana.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPerencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerencanaActionPerformed(evt);
            }
        });
        FormInput.add(BtnPerencana);
        BtnPerencana.setBounds(860, 2280, 100, 23);

        BtnCatatan.setForeground(new java.awt.Color(0, 0, 0));
        BtnCatatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnCatatan.setMnemonic('2');
        BtnCatatan.setText("Template");
        BtnCatatan.setToolTipText("Alt+2");
        BtnCatatan.setName("BtnCatatan"); // NOI18N
        BtnCatatan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCatatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCatatanActionPerformed(evt);
            }
        });
        FormInput.add(BtnCatatan);
        BtnCatatan.setBounds(860, 2388, 100, 23);

        label87.setForeground(new java.awt.Color(0, 0, 0));
        label87.setText("Lain-lain : ");
        label87.setName("label87"); // NOI18N
        label87.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label87);
        label87.setBounds(0, 1473, 130, 23);

        TPemFisikLain.setForeground(new java.awt.Color(0, 0, 0));
        TPemFisikLain.setName("TPemFisikLain"); // NOI18N
        TPemFisikLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemFisikLainKeyPressed(evt);
            }
        });
        FormInput.add(TPemFisikLain);
        TPemFisikLain.setBounds(134, 1473, 505, 23);

        label88.setForeground(new java.awt.Color(0, 0, 0));
        label88.setText("Saturasi O2 :");
        label88.setName("label88"); // NOI18N
        label88.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label88);
        label88.setBounds(0, 1053, 130, 23);

        saturasi.setForeground(new java.awt.Color(0, 0, 0));
        saturasi.setName("saturasi"); // NOI18N
        saturasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                saturasiKeyPressed(evt);
            }
        });
        FormInput.add(saturasi);
        saturasi.setBounds(134, 1053, 70, 23);

        label89.setForeground(new java.awt.Color(0, 0, 0));
        label89.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label89.setText("%");
        label89.setName("label89"); // NOI18N
        label89.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label89);
        label89.setBounds(210, 1053, 20, 23);

        label90.setForeground(new java.awt.Color(0, 0, 0));
        label90.setText("5. ");
        label90.setName("label90"); // NOI18N
        label90.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label90);
        label90.setBounds(430, 1635, 26, 23);

        label91.setForeground(new java.awt.Color(0, 0, 0));
        label91.setText("6. ");
        label91.setName("label91"); // NOI18N
        label91.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label91);
        label91.setBounds(430, 1665, 26, 23);

        label92.setForeground(new java.awt.Color(0, 0, 0));
        label92.setText("7. ");
        label92.setName("label92"); // NOI18N
        label92.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label92);
        label92.setBounds(430, 1695, 26, 23);

        Tdiagnosa5.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosa5.setName("Tdiagnosa5"); // NOI18N
        Tdiagnosa5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdiagnosa5KeyPressed(evt);
            }
        });
        FormInput.add(Tdiagnosa5);
        Tdiagnosa5.setBounds(460, 1635, 395, 23);

        Tdiagnosa6.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosa6.setName("Tdiagnosa6"); // NOI18N
        Tdiagnosa6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdiagnosa6KeyPressed(evt);
            }
        });
        FormInput.add(Tdiagnosa6);
        Tdiagnosa6.setBounds(460, 1665, 395, 23);

        Tdiagnosa7.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosa7.setName("Tdiagnosa7"); // NOI18N
        Tdiagnosa7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdiagnosa7KeyPressed(evt);
            }
        });
        FormInput.add(Tdiagnosa7);
        Tdiagnosa7.setBounds(460, 1695, 395, 23);

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
        BtnPasteHasil.setBounds(860, 1559, 100, 23);

        BtnPasteRencana.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasteRencana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPasteRencana.setMnemonic('L');
        BtnPasteRencana.setText("Paste");
        BtnPasteRencana.setToolTipText("Alt+L");
        BtnPasteRencana.setName("BtnPasteRencana"); // NOI18N
        BtnPasteRencana.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPasteRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasteRencanaActionPerformed(evt);
            }
        });
        FormInput.add(BtnPasteRencana);
        BtnPasteRencana.setBounds(860, 1805, 100, 23);

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
        Thasil.setComponentPopupMenu(jPopupMenu2);
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
        Tinstruksi.setComponentPopupMenu(jPopupMenu3);
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

        tbPenilaian.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPenilaian.setComponentPopupMenu(jPopupMenu1);
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

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Asuhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-10-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-10-2024" }));
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
        } else if (kddpjp.getText().equals("")) {
            Valid.textKosong(kddpjp, "Mengetahui DPJP");
            BtnDpjp.requestFocus();
        } else {            
            simpan();
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
        if (tbPenilaian.getSelectedRow() > -1) {
            hapus();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
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
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (kddpjp.getText().equals("")) {
            Valid.textKosong(kddpjp, "Mengetahui DPJP");
            BtnDpjp.requestFocus();
        } else {
            if (tbPenilaian.getSelectedRow() > -1) {
                user = "";
                if (akses.getadmin() == true) {
                    user = "-";
                } else {
                    user = akses.getkode();
                }

                gantiDisimpan();
                ganti();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbPenilaian.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());            
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("ruangan", TRuangan.getText());
            param.put("riw_penyakit_skrng", Triw_penyakit_sekarang.getText());
            param.put("riw_alergi", Triw_alergi.getText());
            param.put("hsl_pemeriksaan", Thsl_pemeriksaan.getText());
            param.put("rencana_krj", Trencana_kerja.getText());
            param.put("rencana_plg", Trencana_pulang.getText());
            param.put("catatan", Tcatatan.getText());

            Valid.MyReport("rptCetakAsesmenMedikDewasaRanap.jasper", "report", "::[ Laporan Asesmen Medik Dewasa hal. 1 ]::",
                    "select p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tglLhr, "
                    + "concat('Tanggal : ',date_format(am.tgl_asesmen,'%d-%m-%Y'),'   Jam : ',date_format(am.tgl_asesmen,'%H:%i')) tglAsesmen, "
                    + "IF (am.rujukan = 'ya', 'V', '') rujukan_ya, IF (am.rujukan = 'tidak', 'V', '') rujukan_tdk, am.ket_rs, am.ket_puskes, am.ket_praktek, am.ket_lainya, am.diagnosa_rujukan, "
                    + "if(am.datang_sendiri='ya','V','') dtg_sndri, if(am.diantar='ya','V','') diantar, am.ket_diantar, p1.nama nm_dr_meriksa, "
                    + "p2.nama nm_super, date_format(am.tgl_anamnese,'%d-%m-%Y') tglAnam, date_format(am.tgl_anamnese,'%H:%i') jamAnam, am.keluhan_utama, "
                    + "am.riw_penyakit_sekarang, if(am.hipertensi_1='ya','V','') hiper1, if(am.dm_1='ya','V','') dm1, if(am.pjk='ya','V','') pjk, "
                    + "if(am.asma_1='ya','V','') asma1, if(am.stroke='ya','V','') strok, if(am.liver='ya','V','') liver, if(am.ginjal='ya','V','') ginjal, "
                    + "if(am.tb_paru='ya','V','') tbParu, if(am.lain_lain_1='ya','V','') lain1, am.ket_lain_1, if(am.pernah_dirawat='','Pernah Dirawat : -',if(am.pernah_dirawat='Ya',concat('Pernah Dirawat : Ya, Kapan ',am.ket_kapan,' Dimana ',am.ket_dimana,' Diagnosis ',am.diagnosis),concat('Pernah Dirawat : Tidak'))) prnh_dirawat, "
                    + "if(am.hipertensi_2='ya','V','') hiper2, if(am.dm_2='ya','V','') dm2, if(am.jantung='ya','V','') jantung, "
                    + "if(am.asma_2='ya','V','') asma2, if(am.lain_lain_2='ya','V','') lain2, am.lain_lain_2, am.ket_lain_2, am.riw_alergi, "
                    + "if(am.nyeri='ya','V','') nyeri, if(am.nyeri = 'ya',concat('Ya, Lokasi : ',am.ket_lokasi,' Intensitas : ',am.ket_intensitas),'Tidak') nilai_nyeri, "
                    + "am.skor, am.jenis, am.keadaan_umum, am.gizi, am.gcs_e, am.gcs_m, am.gcs_v, IF(am.tindakan_resus = 'ya','Ya','Tidak') tndk_resus, am.bb, "
                    + "am.tb, am.td, am.nadi, am.respirasi, am.suhu_axila, am.suhu_rektal, am.mata_anemis, am.ikterik, if(am.pupil='','-',if(am.pupil='Anisokor','Anisokor',concat('Isokor, Diameter ',am.diameter_kanan,' mm / ',am.diameter_kiri,' mm'))) pupil, "
                    + "am.udem_palpebra, am.tonsil, am.faring, am.lidah, am.bibir, am.jvp, if(am.kelenjar_limfe='Ada',concat('Ada, ',am.ket_ada_kelenjar),am.kelenjar_limfe) kel_limfe, am.kaku_kuduk, am.saturasi "
                    + "from asesmen_medik_dewasa_ranap am inner join reg_periksa rp on rp.no_rawat=am.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai p1 on p1.nik=am.nip_dokter_memeriksa inner join pegawai p2 on p2.nik=am.nip_supervisor where "
                    + "am.no_rawat='" + tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 0).toString() + "'", param);

            Valid.MyReport("rptCetakAsesmenMedikDewasaRanap1.jasper", "report", "::[ Laporan Asesmen Medik Dewasa hal. 2 ]::",
                    "SELECT if(a.thoraks='','-',if(a.thoraks='Asimetris',concat('Asimetris : ',a.ket_asimetris),a.thoraks)) toraks, "
                    + "a.cor_s1s2, if(a.reguler='ya','V','') reguler, if(a.ireguler='ya','V','') ireguler, "
                    + "if(a.ireguler='ya',concat('Ireguler, Murmur : ',a.murmur),'Ireguler, Murmur : -') murmur, "
                    + "a.lain_lain, a.suara_nafas, if(a.ronchi='','-',if(a.ronchi='Ada',concat('Ada : ',a.ket_ronchi),a.ronchi)) ronci, "
                    + "if(a.wheezing='','-',if(a.wheezing='Ada',concat('Ada, ',a.ket_wheezing),a.wheezing)) whezing, "
                    + "if(a.distended='','-',a.distended) disten, if(a.meteorismus='','-',a.meteorismus) meteo, "
                    + "if(a.peristaltik='','-',a.peristaltik) peris, if(a.asites='','-',a.asites) asites, "
                    + "if(a.nyeri_tekan='','-',if(a.nyeri_tekan='Ada',concat('Ada, Lokasi : ',a.lokasi),a.nyeri_tekan)) nyeri, "
                    + "concat(a.hepar,', Lien : ',a.lien) hepar, if(a.extremitas='','-',a.extremitas) ekstrem, "
                    + "if(a.udem='','-',if(a.udem='Ada',concat('Ada, ',a.ket_udem),a.udem)) udem, a.pemeriksaan_fisik_lain, "
                    + "a.diagnosis1, a.diagnosis2, a.diagnosis3, a.diagnosis4, date_format(a.tgl_dpjp,'%d-%m-%Y %H:%i') tgljam, "
                    + "p.nama dpjp, a.diagnosis5, a.diagnosis6, a.diagnosis7 from asesmen_medik_dewasa_ranap a inner join pegawai p on p.nik=a.nip_dpjp where "
                    + "a.no_rawat='" + tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 0).toString() + "'", param);
            
            emptTeks();            
            TabRawat.setSelectedIndex(1);
            tampil();            
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data terlebih dahulu..!!!!");
        }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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

    private void tbPenilaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPenilaianMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {                
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbPenilaian.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbPenilaianMouseClicked

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

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            ChkAccor.setSelected(false);
            isMenu();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (Sequel.cariInteger("select count(-1) from asesmen_medik_dewasa_ranap where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from asesmen_medik_dewasa_ranap where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void BtnDpjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDpjpActionPerformed
        pilihan = 1;
        akses.setform("RMAsesmenMedikDewasaRanap");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDpjpActionPerformed

    private void ChkMerokokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkMerokokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkMerokokActionPerformed

    private void ChkRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkRujukanActionPerformed
        if (ChkRujukan.isSelected() == true) {
            Tket_rs.setEnabled(true);
            Tket_puskes.setEnabled(true);
            Tket_praktek.setEnabled(true);
            Tket_lainya.setEnabled(true);
            
            Tket_rs.setText("");
            Tket_puskes.setText("");
            Tket_praktek.setText("");
            Tket_lainya.setText("");
            Tket_rs.requestFocus();
        } else {
            Tket_rs.setEnabled(false);
            Tket_puskes.setEnabled(false);
            Tket_praktek.setEnabled(false);
            Tket_lainya.setEnabled(false);

            Tket_rs.setText("");
            Tket_puskes.setText("");
            Tket_praktek.setText("");
            Tket_lainya.setText("");
        }
    }//GEN-LAST:event_ChkRujukanActionPerformed

    private void Tket_rsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tket_rsKeyPressed
        Valid.pindah(evt, Tket_rs, Tket_puskes);
    }//GEN-LAST:event_Tket_rsKeyPressed

    private void Tket_puskesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tket_puskesKeyPressed
        Valid.pindah(evt, Tket_rs, Tket_praktek);
    }//GEN-LAST:event_Tket_puskesKeyPressed

    private void Tket_praktekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tket_praktekKeyPressed
        Valid.pindah(evt, Tket_puskes, Tket_lainya);
    }//GEN-LAST:event_Tket_praktekKeyPressed

    private void Tket_lainyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tket_lainyaKeyPressed
        Valid.pindah(evt, Tket_praktek, Tdiag_rujukan);
    }//GEN-LAST:event_Tket_lainyaKeyPressed

    private void Tdiag_rujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdiag_rujukanKeyPressed
        Valid.pindah(evt, Tdiag_rujukan, Tdiag_rujukan);
    }//GEN-LAST:event_Tdiag_rujukanKeyPressed

    private void ChkDiantarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkDiantarActionPerformed
        if (ChkDiantar.isSelected() == true) {
            Tdiantar.setEnabled(true);
            Tdiantar.setText("");
            Tdiantar.requestFocus();
        } else {
            Tdiantar.setEnabled(false);
            Tdiantar.setText("");
        }
    }//GEN-LAST:event_ChkDiantarActionPerformed

    private void BtnDokter_meriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter_meriksaActionPerformed
        pilihan = 2;
        akses.setform("RMAsesmenMedikDewasaRanap");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokter_meriksaActionPerformed

    private void BtnSuperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSuperActionPerformed
        akses.setform("RMAsesmenMedikDewasaRanap");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnSuperActionPerformed

    private void Triw_penyakit_sekarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Triw_penyakit_sekarangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            ChkHipertensi1.requestFocus();
        }
    }//GEN-LAST:event_Triw_penyakit_sekarangKeyPressed

    private void TkeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeluhanKeyPressed
        Valid.pindah(evt, Tkeluhan, Triw_penyakit_sekarang);
    }//GEN-LAST:event_TkeluhanKeyPressed

    private void ChkAsma1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAsma1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAsma1ActionPerformed

    private void Tket_lain1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tket_lain1KeyPressed
        Valid.pindah(evt, Tket_lain1, cmbPernah);
    }//GEN-LAST:event_Tket_lain1KeyPressed

    private void ChkLain1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkLain1ActionPerformed
        if (ChkLain1.isSelected() == true) {
            Tket_lain1.setEnabled(true);
            Tket_lain1.setText("");
            Tket_lain1.requestFocus();
        } else {
            Tket_lain1.setEnabled(false);
            Tket_lain1.setText("");
        }
    }//GEN-LAST:event_ChkLain1ActionPerformed

    private void cmbPernahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPernahActionPerformed
        if (cmbPernah.getSelectedIndex() == 1) {
            Tkapan.setText("");
            Tdimana.setText("");
            Tdiagnosis.setText("");
            Tkapan.setEnabled(true);
            Tdimana.setEnabled(true);
            Tdiagnosis.setEnabled(true);
            Tkapan.requestFocus();
        } else {
            Tkapan.setText("");
            Tdimana.setText("");
            Tdiagnosis.setText("");
            Tkapan.setEnabled(false);
            Tdimana.setEnabled(false);
            Tdiagnosis.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPernahActionPerformed

    private void TkapanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkapanKeyPressed
        Valid.pindah(evt, cmbPernah, Tdimana);
    }//GEN-LAST:event_TkapanKeyPressed

    private void TdimanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdimanaKeyPressed
        Valid.pindah(evt, Tkapan, Tdiagnosis);
    }//GEN-LAST:event_TdimanaKeyPressed

    private void TdiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TdiagnosisKeyPressed

    private void ChkLain2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkLain2ActionPerformed
        if (ChkLain2.isSelected() == true) {
            Tket_lain2.setEnabled(true);
            Tket_lain2.setText("");
            Tket_lain2.requestFocus();
        } else {
            Tket_lain2.setEnabled(false);
            Tket_lain2.setText("");
        }
    }//GEN-LAST:event_ChkLain2ActionPerformed

    private void Tket_lain2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tket_lain2KeyPressed
        Valid.pindah(evt, Tket_lain2, Triw_alergi);
    }//GEN-LAST:event_Tket_lain2KeyPressed

    private void Triw_alergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Triw_alergiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            ChkNyeri.requestFocus();
        }
    }//GEN-LAST:event_Triw_alergiKeyPressed

    private void TlokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlokasiKeyPressed
        Valid.pindah(evt, Triw_alergi, Tintensitas);
    }//GEN-LAST:event_TlokasiKeyPressed

    private void TintensitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TintensitasKeyPressed
        Valid.pindah(evt, Tintensitas, Tskor);
    }//GEN-LAST:event_TintensitasKeyPressed

    private void ChkNyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkNyeriActionPerformed
        if (ChkNyeri.isSelected() == true) {
            Tlokasi.setText("");
            Tintensitas.setText("");
            Tlokasi.setEnabled(true);
            Tintensitas.setEnabled(true);
            Tlokasi.requestFocus();
        } else {
            Tlokasi.setText("");
            Tintensitas.setText("");
            Tlokasi.setEnabled(false);
            Tintensitas.setEnabled(false);
        }
    }//GEN-LAST:event_ChkNyeriActionPerformed

    private void TskorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorKeyPressed

    private void TgcseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcseKeyPressed
        Valid.pindah(evt, Tgcse, Tgcsm);
    }//GEN-LAST:event_TgcseKeyPressed

    private void TgcsmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsmKeyPressed
        Valid.pindah(evt, Tgcse, Tgcsv);
    }//GEN-LAST:event_TgcsmKeyPressed

    private void TgcsvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsvKeyPressed
        Valid.pindah(evt, Tgcsm, Tbb);
    }//GEN-LAST:event_TgcsvKeyPressed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        Valid.pindah(evt, Tgcsv, Ttb);
    }//GEN-LAST:event_TbbKeyPressed

    private void TtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbKeyPressed
        Valid.pindah(evt, Tbb, Ttd);
    }//GEN-LAST:event_TtbKeyPressed

    private void TtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdKeyPressed
        Valid.pindah(evt, Ttb, Tnadi);
    }//GEN-LAST:event_TtdKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        Valid.pindah(evt, Ttd, Trespi);
    }//GEN-LAST:event_TnadiKeyPressed

    private void TrespiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrespiKeyPressed
        Valid.pindah(evt, Tnadi, Taxila);
    }//GEN-LAST:event_TrespiKeyPressed

    private void TaxilaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TaxilaKeyPressed
        Valid.pindah(evt, Trespi, Trektal);
    }//GEN-LAST:event_TaxilaKeyPressed

    private void Tdiameter_kananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdiameter_kananKeyPressed
        Valid.pindah(evt, cmbPupil, Tdiameter_kiri);
    }//GEN-LAST:event_Tdiameter_kananKeyPressed

    private void TtonsilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtonsilKeyPressed
        Valid.pindah(evt, cmbUdem_palpe, Tfaring);
    }//GEN-LAST:event_TtonsilKeyPressed

    private void TfaringKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfaringKeyPressed
        Valid.pindah(evt, Ttonsil, Tlidah);
    }//GEN-LAST:event_TfaringKeyPressed

    private void TrektalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrektalKeyPressed
        Valid.pindah(evt, Taxila, cmbMata);
    }//GEN-LAST:event_TrektalKeyPressed

    private void TlidahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlidahKeyPressed
        Valid.pindah(evt, Tfaring, Tbibir);
    }//GEN-LAST:event_TlidahKeyPressed

    private void TbibirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbibirKeyPressed
        Valid.pindah(evt, Tlidah, Tjvp);
    }//GEN-LAST:event_TbibirKeyPressed

    private void TjvpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjvpKeyPressed
        Valid.pindah(evt, Tbibir, cmbKelenjar);
    }//GEN-LAST:event_TjvpKeyPressed

    private void cmbKelenjarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKelenjarActionPerformed
        if (cmbKelenjar.getSelectedIndex() == 2) {
            Tkelenjar.setText("");
            Tkelenjar.setEnabled(true);
            Tkelenjar.requestFocus();
        } else {
            Tkelenjar.setText("");
            Tkelenjar.setEnabled(false);
        }
    }//GEN-LAST:event_cmbKelenjarActionPerformed

    private void TkelenjarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkelenjarKeyPressed
        Valid.pindah(evt, cmbKelenjar, cmbKaku);
    }//GEN-LAST:event_TkelenjarKeyPressed

    private void TasimetrisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TasimetrisKeyPressed
        Valid.pindah(evt, cmbThoraks, Tcor);
    }//GEN-LAST:event_TasimetrisKeyPressed

    private void cmbThoraksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbThoraksActionPerformed
        if (cmbThoraks.getSelectedIndex() == 2) {
            Tasimetris.setText("");
            Tasimetris.setEnabled(true);
            Tasimetris.requestFocus();
        } else {
            Tasimetris.setText("");
            Tasimetris.setEnabled(false);
        }
    }//GEN-LAST:event_cmbThoraksActionPerformed

    private void TcorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TcorKeyPressed

    private void TmurmurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmurmurKeyPressed
        Valid.pindah(evt, Tmurmur, Tlain_lain);
    }//GEN-LAST:event_TmurmurKeyPressed

    private void Tlain_lainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tlain_lainKeyPressed
        Valid.pindah(evt, Tlain_lain, Tsuara_nfs);
    }//GEN-LAST:event_Tlain_lainKeyPressed

    private void Tsuara_nfsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tsuara_nfsKeyPressed
        Valid.pindah(evt, Tlain_lain, cmbRonchi);
    }//GEN-LAST:event_Tsuara_nfsKeyPressed

    private void TronchiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TronchiKeyPressed
        Valid.pindah(evt, cmbRonchi, cmbWhezing);
    }//GEN-LAST:event_TronchiKeyPressed

    private void cmbRonchiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRonchiActionPerformed
        if (cmbRonchi.getSelectedIndex() == 2) {
            Tronchi.setText("");
            Tronchi.setEnabled(true);
            Tronchi.requestFocus();
        } else {
            Tronchi.setText("");
            Tronchi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRonchiActionPerformed

    private void cmbWhezingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbWhezingActionPerformed
        if (cmbWhezing.getSelectedIndex() == 2) {
            Twhezing.setText("");
            Twhezing.setEnabled(true);
            Twhezing.requestFocus();
        } else {
            Twhezing.setText("");
            Twhezing.setEnabled(false);
        }
    }//GEN-LAST:event_cmbWhezingActionPerformed

    private void TwhezingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TwhezingKeyPressed
        Valid.pindah(evt, cmbWhezing, cmbDisten);
    }//GEN-LAST:event_TwhezingKeyPressed

    private void Tlokasi_nyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tlokasi_nyeriKeyPressed
        Valid.pindah(evt, cmbNyeri, Thepar);
    }//GEN-LAST:event_Tlokasi_nyeriKeyPressed

    private void cmbNyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNyeriActionPerformed
        if (cmbNyeri.getSelectedIndex() == 2) {
            Tlokasi_nyeri.setText("");
            Tlokasi_nyeri.setEnabled(true);
            Tlokasi_nyeri.requestFocus();
        } else {
            Tlokasi_nyeri.setText("");
            Tlokasi_nyeri.setEnabled(false);
        }
    }//GEN-LAST:event_cmbNyeriActionPerformed

    private void TheparKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TheparKeyPressed
        Valid.pindah(evt, Thepar, Tlien);
    }//GEN-LAST:event_TheparKeyPressed

    private void TlienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlienKeyPressed
        Valid.pindah(evt, Thepar, cmbExtrem);
    }//GEN-LAST:event_TlienKeyPressed

    private void TudemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TudemKeyPressed
        Valid.pindah(evt, cmbUdem, TPemFisikLain);
    }//GEN-LAST:event_TudemKeyPressed

    private void cmbUdemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUdemActionPerformed
        if (cmbUdem.getSelectedIndex() == 2) {
            Tudem.setText("");
            Tudem.setEnabled(true);
            Tudem.requestFocus();
        } else {
            Tudem.setText("");
            Tudem.setEnabled(false);
        }
    }//GEN-LAST:event_cmbUdemActionPerformed

    private void Thsl_pemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Thsl_pemeriksaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tdiagnosa1.requestFocus();
        }
    }//GEN-LAST:event_Thsl_pemeriksaanKeyPressed

    private void Tdiagnosa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdiagnosa1KeyPressed
        Valid.pindah(evt, Thsl_pemeriksaan, Tdiagnosa2);
    }//GEN-LAST:event_Tdiagnosa1KeyPressed

    private void Tdiagnosa2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdiagnosa2KeyPressed
        Valid.pindah(evt, Tdiagnosa1, Tdiagnosa3);
    }//GEN-LAST:event_Tdiagnosa2KeyPressed

    private void Tdiagnosa3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdiagnosa3KeyPressed
        Valid.pindah(evt, Tdiagnosa2, Tdiagnosa4);
    }//GEN-LAST:event_Tdiagnosa3KeyPressed

    private void Tdiagnosa4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdiagnosa4KeyPressed
        Valid.pindah(evt, Tdiagnosa3, Tdiagnosa5);
    }//GEN-LAST:event_Tdiagnosa4KeyPressed

    private void Trencana_kerjaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Trencana_kerjaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Trencana_pulang.requestFocus();
        }
    }//GEN-LAST:event_Trencana_kerjaKeyPressed

    private void Trencana_pulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Trencana_pulangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tcatatan.requestFocus();
        }
    }//GEN-LAST:event_Trencana_pulangKeyPressed

    private void TcatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TglDpjp.requestFocus();
        }
    }//GEN-LAST:event_TcatatanKeyPressed

    private void BtnKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKamarActionPerformed
        akses.setform("RMAsesmenMedikDewasaRanap");
        kamar.load();
        kamar.isCek();
        kamar.emptTeks();
        kamar.tampil();
        kamar.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        kamar.setLocationRelativeTo(internalFrame1);
        kamar.setVisible(true);
    }//GEN-LAST:event_BtnKamarActionPerformed

    private void ChkDatangSendiriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkDatangSendiriActionPerformed
        if (ChkDatangSendiri.isSelected() == true) {
            Tdiantar.setEnabled(false);
            Tdiantar.setText("");
        }
    }//GEN-LAST:event_ChkDatangSendiriActionPerformed

    private void BtnRiwPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRiwPenyakitActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 1;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Riwayat Penyakit Sekarang ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnRiwPenyakitActionPerformed

    private void BtnRiwAlergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRiwAlergiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 2;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Riwayat Alergi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnRiwAlergiActionPerformed

    private void BtnHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHasilActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 3;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Hasil Pemeriksaan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnHasilActionPerformed

    private void BtnRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRencanaActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 4;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Rencana Kerja & Terapi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnRencanaActionPerformed

    private void BtnPerencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerencanaActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 5;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Perencanaan Pemulangan Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnPerencanaActionPerformed

    private void BtnCatatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCatatanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 6;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Catatan Penting ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnCatatanActionPerformed

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

    private void TPemFisikLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemFisikLainKeyPressed
        Valid.pindah(evt, cmbUdem, Thsl_pemeriksaan);
    }//GEN-LAST:event_TPemFisikLainKeyPressed

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        kodekamar = "";
        kodekamar = Sequel.cariIsi("select ki.kd_kamar from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
                + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMAsesmenMedikDewasaRanap");
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

    private void saturasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saturasiKeyPressed
        Valid.pindah(evt, cmbUdem_palpe, Tjvp);
    }//GEN-LAST:event_saturasiKeyPressed

    private void Tdiagnosa5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdiagnosa5KeyPressed
        Valid.pindah(evt, Tdiagnosa4, Tdiagnosa6);
    }//GEN-LAST:event_Tdiagnosa5KeyPressed

    private void Tdiagnosa6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdiagnosa6KeyPressed
        Valid.pindah(evt, Tdiagnosa5, Tdiagnosa7);
    }//GEN-LAST:event_Tdiagnosa6KeyPressed

    private void Tdiagnosa7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdiagnosa7KeyPressed
        Valid.pindah(evt, Tdiagnosa6, Trencana_kerja);
    }//GEN-LAST:event_Tdiagnosa7KeyPressed

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

    private void BtnPasteHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteHasilActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (Thsl_pemeriksaan.getText().equals("")) {
                Thsl_pemeriksaan.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                Thsl_pemeriksaan.setText(Thsl_pemeriksaan.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteHasilActionPerformed

    private void BtnPasteRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteRencanaActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (Trencana_kerja.getText().equals("")) {
                Trencana_kerja.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                Trencana_kerja.setText(Trencana_kerja.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteRencanaActionPerformed

    private void MnCopyHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyHasilActionPerformed
        if (Thasil.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data hasil pemeriksaan CPPT masih kosong, silahkan pilih dulu data CPPT nya...!!!!");
            tbCPPT.requestFocus();
        } else {
            akses.setCopyData(Thasil.getText());
        }
    }//GEN-LAST:event_MnCopyHasilActionPerformed

    private void MnCopyInstruksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyInstruksiActionPerformed
        if (Tinstruksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data instruksi nakes pada CPPT masih kosong, silahkan pilih dulu data CPPT nya...!!!!");
            tbCPPT.requestFocus();
        } else {
            akses.setCopyData(Tinstruksi.getText());
        }
    }//GEN-LAST:event_MnCopyInstruksiActionPerformed

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
                    if (Sequel.cariInteger("select count(-1) from asesmen_medik_dewasa_ranap where "
                            + "no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString() + "'") > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Proses kembalikan/restore data gagal, krn. sudah ada datanya dg. no. rawat yg. sama..!!");
                    } else {
                        kembalikanData();
                        TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                        BtnCloseIn10ActionPerformed(null);
                        tampil();
                        emptTeks();
                        TabRawat.setSelectedIndex(1);
                    }
                } else {
                    kembalikanDataDiganti();
                    TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                    BtnCloseIn10ActionPerformed(null);
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

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMAsesmenMedikDewasaRanap");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMAsesmenMedikDewasaRanap");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMAsesmenMedikDewasaRanap");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenMedikDewasaRanap dialog = new RMAsesmenMedikDewasaRanap(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCatatan;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn10;
    private widget.Button BtnCopas;
    private widget.Button BtnDokter_meriksa;
    private widget.Button BtnDpjp;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnHasil;
    private widget.Button BtnKamar;
    private widget.Button BtnKeluar;
    private widget.Button BtnNotepad;
    private widget.Button BtnPasteHasil;
    private widget.Button BtnPasteRencana;
    private widget.Button BtnPerencana;
    private widget.Button BtnPrint;
    private widget.Button BtnRencana;
    private widget.Button BtnResep;
    private widget.Button BtnRestor;
    private widget.Button BtnRiwAlergi;
    private widget.Button BtnRiwPenyakit;
    private widget.Button BtnSimpan;
    private widget.Button BtnSuper;
    public widget.CekBox ChkAccor;
    public widget.CekBox ChkAsma1;
    public widget.CekBox ChkAsma2;
    public widget.CekBox ChkDM1;
    public widget.CekBox ChkDM2;
    public widget.CekBox ChkDatangSendiri;
    public widget.CekBox ChkDiantar;
    public widget.CekBox ChkGinjal;
    public widget.CekBox ChkHipertensi1;
    public widget.CekBox ChkHipertensi2;
    public widget.CekBox ChkIreguler;
    public widget.CekBox ChkJantung;
    public widget.CekBox ChkLain1;
    public widget.CekBox ChkLain2;
    public widget.CekBox ChkLiver;
    public widget.CekBox ChkNyeri;
    public widget.CekBox ChkPJK;
    public widget.CekBox ChkReguler;
    public widget.CekBox ChkRujukan;
    public widget.CekBox ChkStroke;
    public widget.CekBox ChkTB;
    public widget.CekBox ChkTindakanResus;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMenu;
    private widget.TextBox Jk;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnCopyHasil;
    private javax.swing.JMenuItem MnCopyInstruksi;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private javax.swing.JMenuItem MnRiwayatData;
    private widget.PanelBiasa PanelAccor;
    private usu.widget.glass.PanelGlass PanelWall;
    private usu.widget.glass.PanelGlass PanelWall1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll6;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TCari2;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPemFisikLain;
    private widget.TextBox TRuangan;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tasimetris;
    private widget.TextBox Taxila;
    private widget.TextBox Tbb;
    private widget.TextBox Tbibir;
    private widget.TextArea Tcatatan;
    private widget.TextBox Tcor;
    private widget.TextBox Tdiag_rujukan;
    private widget.TextBox Tdiagnosa1;
    private widget.TextBox Tdiagnosa2;
    private widget.TextBox Tdiagnosa3;
    private widget.TextBox Tdiagnosa4;
    private widget.TextBox Tdiagnosa5;
    private widget.TextBox Tdiagnosa6;
    private widget.TextBox Tdiagnosa7;
    private widget.TextBox Tdiagnosis;
    private widget.TextBox Tdiameter_kanan;
    private widget.TextBox Tdiameter_kiri;
    private widget.TextBox Tdiantar;
    private widget.TextBox Tdimana;
    private widget.TextBox Tfaring;
    private widget.TextBox Tgcse;
    private widget.TextBox Tgcsm;
    private widget.TextBox Tgcsv;
    private widget.Tanggal TglAnamnesa;
    private widget.Tanggal TglAsesmen;
    private widget.Tanggal TglDpjp;
    private widget.TextBox TglLahir;
    private widget.TextArea Thasil;
    private widget.TextBox Thepar;
    private widget.TextArea Thsl_pemeriksaan;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tintensitas;
    private widget.TextBox Tjvp;
    private widget.TextBox Tkapan;
    private widget.TextBox Tkdkamar;
    private widget.TextBox Tkelenjar;
    private widget.TextBox Tkeluhan;
    private widget.TextBox Tket_lain1;
    private widget.TextBox Tket_lain2;
    private widget.TextBox Tket_lainya;
    private widget.TextBox Tket_praktek;
    private widget.TextBox Tket_puskes;
    private widget.TextBox Tket_rs;
    private widget.TextBox Tlain_lain;
    private widget.TextBox Tlidah;
    private widget.TextBox Tlien;
    private widget.TextBox Tlokasi;
    private widget.TextBox Tlokasi_nyeri;
    private widget.TextBox Tmurmur;
    private widget.TextBox Tnadi;
    private widget.TextBox Trektal;
    private widget.TextArea Trencana_kerja;
    private widget.TextArea Trencana_pulang;
    private widget.TextBox Trespi;
    private widget.TextArea Triw_alergi;
    private widget.TextArea Triw_penyakit_sekarang;
    private widget.TextBox Tronchi;
    private widget.TextBox Tskor;
    private widget.TextBox Tsuara_nfs;
    private widget.TextBox Ttb;
    private widget.TextBox Ttd;
    private widget.TextArea Ttemplate;
    private widget.TextBox Ttonsil;
    private widget.TextBox Tudem;
    private widget.TextBox Twhezing;
    private javax.swing.JDialog WindowRiwayat;
    private javax.swing.JDialog WindowTemplate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private widget.ComboBox cmbAsites;
    private widget.ComboBox cmbDisten;
    private widget.ComboBox cmbExtrem;
    private widget.ComboBox cmbGizi;
    private widget.ComboBox cmbIkterik;
    private widget.ComboBox cmbJenis;
    private widget.ComboBox cmbKaku;
    private widget.ComboBox cmbKeadaan;
    private widget.ComboBox cmbKelenjar;
    private widget.ComboBox cmbMata;
    private widget.ComboBox cmbMeteo;
    private widget.ComboBox cmbNyeri;
    private widget.ComboBox cmbPeris;
    private widget.ComboBox cmbPernah;
    private widget.ComboBox cmbPupil;
    private widget.ComboBox cmbRonchi;
    private widget.ComboBox cmbThoraks;
    private widget.ComboBox cmbUdem;
    private widget.ComboBox cmbUdem_palpe;
    private widget.ComboBox cmbWhezing;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
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
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel47;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private widget.TextBox kddokter_meriksa;
    private widget.TextBox kddpjp;
    private widget.TextBox kdsuper;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label22;
    private widget.Label label23;
    private widget.Label label24;
    private widget.Label label25;
    private widget.Label label26;
    private widget.Label label27;
    private widget.Label label28;
    private widget.Label label29;
    private widget.Label label30;
    private widget.Label label31;
    private widget.Label label32;
    private widget.Label label33;
    private widget.Label label34;
    private widget.Label label35;
    private widget.Label label36;
    private widget.Label label37;
    private widget.Label label38;
    private widget.Label label39;
    private widget.Label label40;
    private widget.Label label41;
    private widget.Label label42;
    private widget.Label label43;
    private widget.Label label44;
    private widget.Label label45;
    private widget.Label label46;
    private widget.Label label47;
    private widget.Label label48;
    private widget.Label label49;
    private widget.Label label50;
    private widget.Label label51;
    private widget.Label label52;
    private widget.Label label53;
    private widget.Label label54;
    private widget.Label label55;
    private widget.Label label56;
    private widget.Label label57;
    private widget.Label label58;
    private widget.Label label59;
    private widget.Label label60;
    private widget.Label label61;
    private widget.Label label62;
    private widget.Label label63;
    private widget.Label label64;
    private widget.Label label65;
    private widget.Label label66;
    private widget.Label label67;
    private widget.Label label68;
    private widget.Label label69;
    private widget.Label label70;
    private widget.Label label71;
    private widget.Label label72;
    private widget.Label label73;
    private widget.Label label74;
    private widget.Label label75;
    private widget.Label label76;
    private widget.Label label77;
    private widget.Label label78;
    private widget.Label label79;
    private widget.Label label80;
    private widget.Label label81;
    private widget.Label label82;
    private widget.Label label83;
    private widget.Label label84;
    private widget.Label label85;
    private widget.Label label86;
    private widget.Label label87;
    private widget.Label label88;
    private widget.Label label89;
    private widget.Label label90;
    private widget.Label label91;
    private widget.Label label92;
    private widget.TextBox nmdokter_meriksa;
    private widget.TextBox nmdpjp;
    private widget.TextBox nmsuper;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi4;
    private widget.TextBox saturasi;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.ScrollPane scrollPane18;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbCPPT;
    private widget.Table tbPenilaian;
    private widget.Table tbRiwayat;
    private widget.Table tbTemplate;
    // End of variables declaration//GEN-END:variables

     private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jk, "
                    + "date_format(p.tgl_lahir,'%d-%m-%Y') tglLhr, pg1.nama nm_dpjp, pg2.nama nmdr_memeriksa, "
                    + "pg3.nama nm_super, date_format(am.tgl_asesmen,'%d-%m-%Y %H:%i') tglases, am.* FROM reg_periksa rp "
                    + "inner join asesmen_medik_dewasa_ranap am on am.no_rawat=rp.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "left join pegawai pg1 on pg1.nik=am.nip_dpjp "
                    + "left join pegawai pg2 on pg2.nik=am.nip_dokter_memeriksa "
                    + "left join pegawai pg3 on pg3.nik=am.nip_supervisor WHERE "
                    + "date(am.tgl_asesmen) BETWEEN ? AND ? AND rp.no_rawat LIKE ? OR "
                    + "date(am.tgl_asesmen) BETWEEN ? AND ? AND p.no_rkm_medis LIKE ? OR "
                    + "date(am.tgl_asesmen) BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                    + "date(am.tgl_asesmen) BETWEEN ? AND ? AND pg1.nama LIKE ? OR "
                    + "date(am.tgl_asesmen) BETWEEN ? AND ? AND pg2.nama LIKE ? OR "
                    + "date(am.tgl_asesmen) BETWEEN ? AND ? AND pg3.nama LIKE ? ORDER BY am.tgl_asesmen");
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
                        rs.getString("jk"),
                        rs.getString("tglLhr"),
                        rs.getString("tglases"),
                        rs.getString("nm_dpjp"),
                        Sequel.cariIsi("SELECT b.nm_bangsal FROM bangsal b INNER JOIN kamar k ON k.kd_bangsal=b.kd_bangsal WHERE k.kd_kamar='" + rs.getString("kd_kamar") + "'"),                        
                        rs.getString("kd_kamar"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("rujukan"),
                        rs.getString("ket_rs"),
                        rs.getString("ket_puskes"),
                        rs.getString("ket_praktek"),
                        rs.getString("ket_lainya"),
                        rs.getString("diagnosa_rujukan"),
                        rs.getString("datang_sendiri"),
                        rs.getString("diantar"),
                        rs.getString("ket_diantar"),
                        rs.getString("nip_dokter_memeriksa"),
                        rs.getString("nip_supervisor"),
                        rs.getString("tgl_anamnese"),
                        rs.getString("keluhan_utama"),
                        rs.getString("riw_penyakit_sekarang"),
                        rs.getString("hipertensi_1"),
                        rs.getString("dm_1"),
                        rs.getString("pjk"),
                        rs.getString("asma_1"),
                        rs.getString("stroke"),
                        rs.getString("liver"),
                        rs.getString("ginjal"),
                        rs.getString("tb_paru"),
                        rs.getString("lain_lain_1"),
                        rs.getString("ket_lain_1"),
                        rs.getString("pernah_dirawat"),
                        rs.getString("ket_kapan"),
                        rs.getString("ket_dimana"),
                        rs.getString("diagnosis"),
                        rs.getString("hipertensi_2"),
                        rs.getString("dm_2"),
                        rs.getString("jantung"),
                        rs.getString("asma_2"),
                        rs.getString("lain_lain_2"),
                        rs.getString("ket_lain_2"),
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
                        rs.getString("td"),
                        rs.getString("nadi"),
                        rs.getString("respirasi"),
                        rs.getString("suhu_axila"),
                        rs.getString("suhu_rektal"),
                        rs.getString("mata_anemis"),
                        rs.getString("ikterik"),
                        rs.getString("pupil"),
                        rs.getString("diameter_kanan"),
                        rs.getString("diameter_kiri"),
                        rs.getString("udem_palpebra"),
                        rs.getString("tonsil"),
                        rs.getString("faring"),
                        rs.getString("lidah"),
                        rs.getString("bibir"),
                        rs.getString("jvp"),
                        rs.getString("kelenjar_limfe"),
                        rs.getString("ket_ada_kelenjar"),
                        rs.getString("kaku_kuduk"),
                        rs.getString("thoraks"),
                        rs.getString("ket_asimetris"),
                        rs.getString("cor_s1s2"),
                        rs.getString("reguler"),
                        rs.getString("ireguler"),
                        rs.getString("murmur"),
                        rs.getString("lain_lain"),
                        rs.getString("suara_nafas"),
                        rs.getString("ronchi"),
                        rs.getString("ket_ronchi"),
                        rs.getString("wheezing"),
                        rs.getString("ket_wheezing"),
                        rs.getString("distended"),
                        rs.getString("meteorismus"),
                        rs.getString("peristaltik"),
                        rs.getString("asites"),
                        rs.getString("nyeri_tekan"),
                        rs.getString("lokasi"),
                        rs.getString("hepar"),
                        rs.getString("lien"),
                        rs.getString("extremitas"),
                        rs.getString("udem"),
                        rs.getString("ket_udem"),
                        rs.getString("hasil_pemeriksaan"),
                        rs.getString("diagnosis1"),
                        rs.getString("diagnosis2"),
                        rs.getString("diagnosis3"),
                        rs.getString("diagnosis4"),
                        rs.getString("rencana_kerja"),
                        rs.getString("perencanaan_pemulangan"),
                        rs.getString("catatan_penting"),
                        rs.getString("tgl_dpjp"),
                        rs.getString("nip_dpjp"),
                        rs.getString("nmdr_memeriksa"),
                        rs.getString("nm_super"),
                        rs.getString("pemeriksaan_fisik_lain"),
                        rs.getString("saturasi"),
                        rs.getString("diagnosis5"),
                        rs.getString("diagnosis6"),
                        rs.getString("diagnosis7"),
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
        ChkRujukan.setSelected(false);
        Tket_rs.setText("");
        Tket_puskes.setText("");
        Tket_praktek.setText("");
        Tket_lainya.setText("");        
        Tket_rs.setEnabled(false);
        Tket_puskes.setEnabled(false);
        Tket_praktek.setEnabled(false);
        Tket_lainya.setEnabled(false);        
        Tdiag_rujukan.setText("");
        ChkDatangSendiri.setSelected(false);
        ChkDiantar.setSelected(false);
        Tdiantar.setText("");
        Tdiantar.setEnabled(false);
        kddokter_meriksa.setText("-");
        nmdokter_meriksa.setText("-");
        kdsuper.setText("-");
        nmsuper.setText("-");
        TglAnamnesa.setDate(new Date());
        Tkeluhan.setText("");
        Triw_penyakit_sekarang.setText("");
        
        ChkHipertensi1.setSelected(false);
        ChkDM1.setSelected(false);
        ChkPJK.setSelected(false);
        ChkAsma1.setSelected(false);
        ChkStroke.setSelected(false);
        ChkLiver.setSelected(false);
        ChkGinjal.setSelected(false);
        ChkTB.setSelected(false);
        ChkLain1.setSelected(false);
        Tket_lain1.setText("");
        Tket_lain1.setEnabled(false);
        cmbPernah.setSelectedIndex(0);
        Tkapan.setText("");
        Tdimana.setText("");
        Tdiagnosis.setText("");
        Tkapan.setEnabled(false);
        Tdimana.setEnabled(false);
        Tdiagnosis.setEnabled(false);
        ChkHipertensi2.setSelected(false);
        ChkDM2.setSelected(false);
        ChkJantung.setSelected(false);
        ChkAsma2.setSelected(false);
        ChkLain2.setSelected(false);
        Tket_lain2.setText("");
        Tket_lain2.setEnabled(false);
        Triw_alergi.setText("");
        ChkNyeri.setSelected(false);
        Tlokasi.setText("");
        Tintensitas.setText("");
        Tlokasi.setEnabled(false);
        Tintensitas.setEnabled(false);
        Tskor.setText("");
        cmbJenis.setSelectedIndex(0);
        cmbKeadaan.setSelectedIndex(0);
        cmbGizi.setSelectedIndex(0);
        Tgcse.setText("");
        Tgcsm.setText("");
        Tgcsv.setText("");
        ChkTindakanResus.setSelected(false);
        Tbb.setText("");
        Ttb.setText("");
        Ttd.setText("");
        Tnadi.setText("");
        Trespi.setText("");
        Taxila.setText("");
        Trektal.setText("");
        cmbMata.setSelectedIndex(0);
        cmbIkterik.setSelectedIndex(0);
        cmbPupil.setSelectedIndex(0);
        Tdiameter_kanan.setText("");
        Tdiameter_kiri.setText("");
        cmbUdem_palpe.setSelectedIndex(0);
        Ttonsil.setText("");
        Tfaring.setText("");
        Tlidah.setText("");
        Tbibir.setText("");
        Tjvp.setText("");
        cmbKelenjar.setSelectedIndex(0);
        Tkelenjar.setText("");
        Tkelenjar.setEnabled(false);
        cmbKaku.setSelectedIndex(0);
        cmbThoraks.setSelectedIndex(0);
        Tasimetris.setText("");
        Tasimetris.setEnabled(false);
        ChkReguler.setSelected(false);
        ChkIreguler.setSelected(false);
        Tmurmur.setText("");        
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        Tlain_lain.setText("");
        Tsuara_nfs.setText("");
        cmbRonchi.setSelectedIndex(0);
        Tronchi.setText("");
        Tronchi.setEnabled(false);        
        cmbWhezing.setSelectedIndex(0);
        Twhezing.setText("");
        Twhezing.setEnabled(false);
        cmbDisten.setSelectedIndex(0);
        cmbMeteo.setSelectedIndex(0);
        cmbPeris.setSelectedIndex(0);
        cmbAsites.setSelectedIndex(0);
        cmbNyeri.setSelectedIndex(0);        
        Tlokasi_nyeri.setText("");
        Tlokasi_nyeri.setEnabled(false);
        Thepar.setText("");
        Tlien.setText("");
        cmbExtrem.setSelectedIndex(0);
        cmbUdem.setSelectedIndex(0);
        Tudem.setText("");
        Tudem.setEnabled(false);
        Thsl_pemeriksaan.setText("");
        Tdiagnosa1.setText("");
        Tdiagnosa2.setText("");
        Tdiagnosa3.setText("");
        Tdiagnosa4.setText("");
        Tdiagnosa5.setText("");
        Tdiagnosa6.setText("");
        Tdiagnosa7.setText("");
        Trencana_kerja.setText("");
        Trencana_pulang.setText("");
        Tcatatan.setText("");
        TglDpjp.setDate(new Date());
        TPemFisikLain.setText("");
        saturasi.setText("");
        user = "";
    }

    private void getData() {
        rujukan = "";
        dtg_sendiri = "";
        diantar = "";
        hipertensi1 = "";
        dm1 = "";
        pjk = "";
        asma1 = "";
        strok = "";
        liver = "";
        ginjal = "";
        tb = "";
        lain1 = "";
        hipertensi2 = "";
        dm2 = "";
        jantung = "";
        asma2 = "";
        lain2 = "";
        nyeri = "";
        resus = "";
        reguler = "";
        ireguler = "";
        
        if (tbPenilaian.getSelectedRow() != -1) {
            TNoRw.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 0).toString());
            TNoRM.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 1).toString());
            TPasien.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 2).toString());
            Jk.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 3).toString());
            TglLahir.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 4).toString());
            Valid.SetTgl2(TglAsesmen, tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 9).toString());
            nmdpjp.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 6).toString());            
            TRuangan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 7).toString());
            Tkdkamar.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 8).toString());            
            rujukan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 10).toString();
            Tket_rs.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 11).toString());
            Tket_puskes.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 12).toString());
            Tket_praktek.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 13).toString());
            Tket_lainya.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 14).toString());
            Tdiag_rujukan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 15).toString());
            dtg_sendiri = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 16).toString();
            diantar = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 17).toString();
            Tdiantar.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 18).toString());
            kddokter_meriksa.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 19).toString());
            kdsuper.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 20).toString());
            Valid.SetTgl2(TglAnamnesa, tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 21).toString());
            Tkeluhan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 22).toString());
            Triw_penyakit_sekarang.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 23).toString());
            hipertensi1 = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 24).toString();
            dm1 = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 25).toString();
            pjk = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 26).toString();
            asma1 = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 27).toString();
            strok = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 28).toString();
            liver = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 29).toString();
            ginjal = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 30).toString();
            tb = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 31).toString();
            lain1 = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 32).toString();
            Tket_lain1.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 33).toString());
            cmbPernah.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 34).toString());
            Tkapan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 35).toString());
            Tdimana.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 36).toString());
            Tdiagnosis.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 37).toString());
            hipertensi2 = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 38).toString();
            dm2 = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 39).toString();
            jantung = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 40).toString();
            asma2 = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 41).toString();
            lain2 = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 42).toString();
            Tket_lain2.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 43).toString());
            Triw_alergi.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 44).toString());
            nyeri = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 45).toString();
            Tlokasi.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 46).toString());
            Tintensitas.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 47).toString());
            Tskor.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 48).toString());
            cmbJenis.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 49).toString());
            cmbKeadaan.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 50).toString());
            cmbGizi.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 51).toString());
            Tgcse.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 52).toString());
            Tgcsm.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 53).toString());
            Tgcsv.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 54).toString());
            resus = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 55).toString();
            Tbb.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 56).toString());
            Ttb.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 57).toString());
            Ttd.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 58).toString());
            Tnadi.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 59).toString());
            Trespi.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 60).toString());
            Taxila.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 61).toString());
            Trektal.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 62).toString());
            cmbMata.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 63).toString());
            cmbIkterik.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 64).toString());
            cmbPupil.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 65).toString());
            Tdiameter_kanan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 66).toString());
            Tdiameter_kiri.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 67).toString());
            cmbUdem_palpe.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 68).toString());
            Ttonsil.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 69).toString());
            Tfaring.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 70).toString());
            Tlidah.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 71).toString());
            Tbibir.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 72).toString());
            Tjvp.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 73).toString());
            cmbKelenjar.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 74).toString());
            Tkelenjar.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 75).toString());
            cmbKaku.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 76).toString());
            cmbThoraks.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 77).toString());
            Tasimetris.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 78).toString());
            Tcor.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 79).toString());
            reguler = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 80).toString();
            ireguler = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 81).toString();
            Tmurmur.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 82).toString());
            Tlain_lain.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 83).toString());
            Tsuara_nfs.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 84).toString());
            cmbRonchi.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 85).toString());
            Tronchi.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 86).toString());
            cmbWhezing.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 87).toString());
            Twhezing.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 88).toString());
            cmbDisten.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 89).toString());
            cmbMeteo.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 90).toString());
            cmbPeris.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 91).toString());
            cmbAsites.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 92).toString());
            cmbNyeri.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 93).toString());
            Tlokasi_nyeri.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 94).toString());
            Thepar.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 95).toString());
            Tlien.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 96).toString());
            cmbExtrem.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 97).toString());
            cmbUdem.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 98).toString());
            Tudem.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 99).toString());
            Thsl_pemeriksaan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 100).toString());
            Tdiagnosa1.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 101).toString());
            Tdiagnosa2.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 102).toString());
            Tdiagnosa3.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 103).toString());
            Tdiagnosa4.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 104).toString());
            Trencana_kerja.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 105).toString());
            Trencana_pulang.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 106).toString());
            Tcatatan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 107).toString());
            Valid.SetTgl2(TglDpjp, tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 108).toString());
            kddpjp.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 109).toString());
            nmdokter_meriksa.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 110).toString());
            nmsuper.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 111).toString());
            TPemFisikLain.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 112).toString());
            saturasi.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 113).toString());
            Tdiagnosa5.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 114).toString());
            Tdiagnosa6.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 115).toString());
            Tdiagnosa7.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 116).toString());
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
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgllahir"));
                    Valid.SetTgl2(TglAsesmen, rs.getDate("tgl_registrasi").toString() + " " + rs.getString("jam_reg").toString());
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
    
    public void setNoRm(String norwt, String kdkmr) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(new Date());
        Tkdkamar.setText(kdkmr);
        TRuangan.setText(Sequel.cariIsi("SELECT b.nm_bangsal FROM bangsal b INNER JOIN kamar k ON k.kd_bangsal=b.kd_bangsal WHERE k.kd_kamar='" + kdkmr + "'"));
        isRawat();
        tampil();        
    }    
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getasesmen_medik_dewasa_ranap());
        BtnHapus.setEnabled(akses.getasesmen_medik_dewasa_ranap());
        BtnEdit.setEnabled(akses.getasesmen_medik_dewasa_ranap());
        BtnPrint.setEnabled(akses.getasesmen_medik_dewasa_ranap());
        MnRiwayatData.setEnabled(akses.getadmin());
        
        if (akses.getjml2() >= 1) {            
            BtnDpjp.setEnabled(false);            
            kddpjp.setText(akses.getkode());              
            Sequel.cariIsi("select nama from petugas where nip=?", nmdpjp, kddpjp.getText());
            if (nmdpjp.getText().equals("")) {
                kddpjp.setText("");                
                JOptionPane.showMessageDialog(null, "User login bukan dokter...!!");
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
            if (Sequel.mengedittf("asesmen_medik_dewasa_ranap", "no_rawat=?", "no_rawat=?, kd_kamar=?, tgl_asesmen=?, rujukan=?, ket_rs=?, ket_puskes=?, ket_praktek=?, ket_lainya=?, diagnosa_rujukan=?, "
                    + "datang_sendiri=?, diantar=?, ket_diantar=?, nip_dokter_memeriksa=?, nip_supervisor=?, tgl_anamnese=?, keluhan_utama=?, riw_penyakit_sekarang=?, hipertensi_1=?, dm_1=?, pjk=?, "
                    + "asma_1=?, stroke=?, liver=?, ginjal=?, tb_paru=?, lain_lain_1=?, ket_lain_1=?, pernah_dirawat=?, ket_kapan=?, ket_dimana=?, diagnosis=?, hipertensi_2=?, dm_2=?, jantung=?, "
                    + "asma_2=?, lain_lain_2=?, ket_lain_2=?, riw_alergi=?, nyeri=?, ket_lokasi=?, ket_intensitas=?, skor=?, jenis=?, keadaan_umum=?, gizi=?, gcs_e=?, gcs_m=?, gcs_v=?, tindakan_resus=?, "
                    + "bb=?, tb=?, td=?, nadi=?, respirasi=?, suhu_axila=?, suhu_rektal=?, mata_anemis=?, ikterik=?, pupil=?, diameter_kanan=?, diameter_kiri=?, udem_palpebra=?, tonsil=?, faring=?, "
                    + "lidah=?, bibir=?, jvp=?, kelenjar_limfe=?, ket_ada_kelenjar=?, kaku_kuduk=?, thoraks=?, ket_asimetris=?, cor_s1s2=?, reguler=?, ireguler=?, murmur=?, lain_lain=?, suara_nafas=?, "
                    + "ronchi=?, ket_ronchi=?, wheezing=?, ket_wheezing=?, distended=?, meteorismus=?, peristaltik=?, asites=?, nyeri_tekan=?, lokasi=?, hepar=?, lien=?, extremitas=?, udem=?, ket_udem=?, "
                    + "hasil_pemeriksaan=?, diagnosis1=?, diagnosis2=?, diagnosis3=?, diagnosis4=?, rencana_kerja=?, perencanaan_pemulangan=?, catatan_penting=?, tgl_dpjp=?, nip_dpjp=?, pemeriksaan_fisik_lain=?, "
                    + "saturasi=?, diagnosis5=?, diagnosis6=?, diagnosis7=?", 109, new String[]{
                        TNoRw.getText(), Tkdkamar.getText(), Valid.SetTgl(TglAsesmen.getSelectedItem() + "") + " " + TglAsesmen.getSelectedItem().toString().substring(11, 19), rujukan, Tket_rs.getText(), Tket_puskes.getText(), Tket_praktek.getText(),
                        Tket_lainya.getText(), Tdiag_rujukan.getText(), dtg_sendiri, diantar, Tdiantar.getText(), kddokter_meriksa.getText(), kdsuper.getText(), Valid.SetTgl(TglAnamnesa.getSelectedItem() + "") + " " + TglAnamnesa.getSelectedItem().toString().substring(11, 19),
                        Tkeluhan.getText(), Valid.mysql_real_escape_stringERM(Triw_penyakit_sekarang.getText()), hipertensi1, dm1, pjk, asma1, strok, liver, ginjal, tb, lain1, Tket_lain1.getText(), cmbPernah.getSelectedItem().toString(), Tkapan.getText(), Tdimana.getText(),
                        Tdiagnosis.getText(), hipertensi2, dm2, jantung, asma2, lain2, Tket_lain2.getText(), Valid.mysql_real_escape_stringERM(Triw_alergi.getText()), nyeri, Tlokasi.getText(), Tintensitas.getText(), Tskor.getText(), cmbJenis.getSelectedItem().toString(),
                        cmbKeadaan.getSelectedItem().toString(), cmbGizi.getSelectedItem().toString(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(), resus, Tbb.getText(), Ttb.getText(), Ttd.getText(), Tnadi.getText(), Trespi.getText(), Taxila.getText(),
                        Trektal.getText(), cmbMata.getSelectedItem().toString(), cmbIkterik.getSelectedItem().toString(), cmbPupil.getSelectedItem().toString(), Tdiameter_kanan.getText(), Tdiameter_kiri.getText(), cmbUdem_palpe.getSelectedItem().toString(),
                        Ttonsil.getText(), Tfaring.getText(), Tlidah.getText(), Tbibir.getText(), Tjvp.getText(), cmbKelenjar.getSelectedItem().toString(), Tkelenjar.getText(), cmbKaku.getSelectedItem().toString(), cmbThoraks.getSelectedItem().toString(),
                        Tasimetris.getText(), Tcor.getText(), reguler, ireguler, Tmurmur.getText(), Tlain_lain.getText(), Tsuara_nfs.getText(), cmbRonchi.getSelectedItem().toString(), Tronchi.getText(), cmbWhezing.getSelectedItem().toString(),
                        Twhezing.getText(), cmbDisten.getSelectedItem().toString(), cmbMeteo.getSelectedItem().toString(), cmbPeris.getSelectedItem().toString(), cmbAsites.getSelectedItem().toString(), cmbNyeri.getSelectedItem().toString(),
                        Tlokasi_nyeri.getText(), Thepar.getText(), Tlien.getText(), cmbExtrem.getSelectedItem().toString(), cmbUdem.getSelectedItem().toString(), Tudem.getText(), Valid.mysql_real_escape_stringERM(Thsl_pemeriksaan.getText()), 
                        Valid.mysql_real_escape_stringERM(Tdiagnosa1.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa2.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa3.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa4.getText()), 
                        Valid.mysql_real_escape_stringERM(Trencana_kerja.getText()), Valid.mysql_real_escape_stringERM(Trencana_pulang.getText()), Valid.mysql_real_escape_stringERM(Tcatatan.getText()), 
                        Valid.SetTgl(TglDpjp.getSelectedItem() + "") + " " + TglDpjp.getSelectedItem().toString().substring(11, 19),
                        kddpjp.getText(), TPemFisikLain.getText(), saturasi.getText(), Valid.mysql_real_escape_stringERM(Tdiagnosa5.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa6.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa7.getText()),
                        tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 0).toString()
                    }) == true) {
                
                if (Sequel.cariInteger("select count(-1) from dpjp_ranap where no_rawat='" + TNoRw.getText() + "'") == 0) {
                    Sequel.menyimpan("dpjp_ranap", "'" + TNoRw.getText() + "','" + kddpjp.getText() + "'");
                }
                
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                TabRawat.setSelectedIndex(1);
            }
        } catch (Exception e) {
            System.out.println("Ganti : " + e);
        }
    }
    
    private void simpan() {
        cekData();
        try {
            if (Sequel.menyimpantf("asesmen_medik_dewasa_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 109, new String[]{
                        TNoRw.getText(), Tkdkamar.getText(), Valid.SetTgl(TglAsesmen.getSelectedItem() + "") + " " + TglAsesmen.getSelectedItem().toString().substring(11, 19), rujukan, Tket_rs.getText(), Tket_puskes.getText(), Tket_praktek.getText(),
                        Tket_lainya.getText(), Tdiag_rujukan.getText(), dtg_sendiri, diantar, Tdiantar.getText(), kddokter_meriksa.getText(), kdsuper.getText(), Valid.SetTgl(TglAnamnesa.getSelectedItem() + "") + " " + TglAnamnesa.getSelectedItem().toString().substring(11, 19),
                        Tkeluhan.getText(), Valid.mysql_real_escape_stringERM(Triw_penyakit_sekarang.getText()), hipertensi1, dm1, pjk, asma1, strok, liver, ginjal, tb, lain1, Tket_lain1.getText(), cmbPernah.getSelectedItem().toString(), Tkapan.getText(), Tdimana.getText(),
                        Tdiagnosis.getText(), hipertensi2, dm2, jantung, asma2, lain2, Tket_lain2.getText(), Valid.mysql_real_escape_stringERM(Triw_alergi.getText()), nyeri, Tlokasi.getText(), Tintensitas.getText(), Tskor.getText(), cmbJenis.getSelectedItem().toString(),
                        cmbKeadaan.getSelectedItem().toString(), cmbGizi.getSelectedItem().toString(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(), resus, Tbb.getText(), Ttb.getText(), Ttd.getText(), Tnadi.getText(), Trespi.getText(), Taxila.getText(),
                        Trektal.getText(), cmbMata.getSelectedItem().toString(), cmbIkterik.getSelectedItem().toString(), cmbPupil.getSelectedItem().toString(), Tdiameter_kanan.getText(), Tdiameter_kiri.getText(), cmbUdem_palpe.getSelectedItem().toString(),
                        Ttonsil.getText(), Tfaring.getText(), Tlidah.getText(), Tbibir.getText(), Tjvp.getText(), cmbKelenjar.getSelectedItem().toString(), Tkelenjar.getText(), cmbKaku.getSelectedItem().toString(), cmbThoraks.getSelectedItem().toString(),
                        Tasimetris.getText(), Tcor.getText(), reguler, ireguler, Tmurmur.getText(), Tlain_lain.getText(), Tsuara_nfs.getText(), cmbRonchi.getSelectedItem().toString(), Tronchi.getText(), cmbWhezing.getSelectedItem().toString(),
                        Twhezing.getText(), cmbDisten.getSelectedItem().toString(), cmbMeteo.getSelectedItem().toString(), cmbPeris.getSelectedItem().toString(), cmbAsites.getSelectedItem().toString(), cmbNyeri.getSelectedItem().toString(),
                        Tlokasi_nyeri.getText(), Thepar.getText(), Tlien.getText(), cmbExtrem.getSelectedItem().toString(), cmbUdem.getSelectedItem().toString(), Tudem.getText(), Valid.mysql_real_escape_stringERM(Thsl_pemeriksaan.getText()),
                        Valid.mysql_real_escape_stringERM(Tdiagnosa1.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa2.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa3.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa4.getText()),
                        Valid.mysql_real_escape_stringERM(Trencana_kerja.getText()), Valid.mysql_real_escape_stringERM(Trencana_pulang.getText()), Valid.mysql_real_escape_stringERM(Tcatatan.getText()),
                        Valid.SetTgl(TglDpjp.getSelectedItem() + "") + " " + TglDpjp.getSelectedItem().toString().substring(11, 19),
                        kddpjp.getText(), TPemFisikLain.getText(), Sequel.cariIsi("select now()"), saturasi.getText(), Valid.mysql_real_escape_stringERM(Tdiagnosa5.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa6.getText()),
                        Valid.mysql_real_escape_stringERM(Tdiagnosa7.getText())
                    }) == true) {
                
                if (Sequel.cariInteger("select count(-1) from dpjp_ranap where no_rawat='" + TNoRw.getText() + "'") == 0) {
                    Sequel.menyimpan("dpjp_ranap", "'" + TNoRw.getText() + "','" + kddpjp.getText() + "'");
                }
                
                TCari.setText(TNoRw.getText());
                TabRawat.setSelectedIndex(1);
                emptTeks();
                tampil();
            }
        } catch (Exception e) {
            System.out.println("Simpan : " + e);
        }
    }
    
    private void cekData() {
        if (ChkRujukan.isSelected() == true) {
            rujukan = "ya";
        } else {
            rujukan = "tidak";
        }
        
        if (ChkDatangSendiri.isSelected() == true) {
            dtg_sendiri = "ya";
        } else {
            dtg_sendiri = "tidak";
        }
        
        if (ChkDiantar.isSelected() == true) {
            diantar = "ya";
        } else {
            diantar = "tidak";
        }
        
        if (ChkHipertensi1.isSelected() == true) {
            hipertensi1 = "ya";
        } else {
            hipertensi1 = "tidak";
        }
        
        if (ChkDM1.isSelected() == true) {
            dm1 = "ya";
        } else {
            dm1 = "tidak";
        }
        
        if (ChkPJK.isSelected() == true) {
            pjk = "ya";
        } else {
            pjk = "tidak";
        }
        
        if (ChkAsma1.isSelected() == true) {
            asma1 = "ya";
        } else {
            asma1 = "tidak";
        }
        
        if (ChkStroke.isSelected() == true) {
            strok = "ya";
        } else {
            strok = "tidak";
        }
        
        if (ChkLiver.isSelected() == true) {
            liver = "ya";
        } else {
            liver = "tidak";
        }
        
        if (ChkGinjal.isSelected() == true) {
            ginjal = "ya";
        } else {
            ginjal = "tidak";
        }
        
        if (ChkTB.isSelected() == true) {
            tb = "ya";
        } else {
            tb = "tidak";
        }
        
        if (ChkLain1.isSelected() == true) {
            lain1 = "ya";
        } else {
            lain1 = "tidak";
        }
        
        if (ChkHipertensi2.isSelected() == true) {
            hipertensi2 = "ya";
        } else {
            hipertensi2 = "tidak";
        }
        
        if (ChkDM2.isSelected() == true) {
            dm2 = "ya";
        } else {
            dm2 = "tidak";
        }
        
        if (ChkJantung.isSelected() == true) {
            jantung = "ya";
        } else {
            jantung = "tidak";
        }
        
        if (ChkAsma2.isSelected() == true) {
            asma2 = "ya";
        } else {
            asma2 = "tidak";
        }
        
        if (ChkLain2.isSelected() == true) {
            lain2 = "ya";
        } else {
            lain2 = "tidak";
        }
        
        if (ChkNyeri.isSelected() == true) {
            nyeri = "ya";
        } else {
            nyeri = "tidak";
        }
        
        if (ChkTindakanResus.isSelected() == true) {
            resus = "ya";
        } else {
            resus = "tidak";
        }
        
        if (ChkReguler.isSelected() == true) {
            reguler = "ya";
        } else {
            reguler = "tidak";
        }
        
        if (ChkIreguler.isSelected() == true) {
            ireguler = "ya";
        } else {
            ireguler = "tidak";
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
            if (Sequel.queryu2tf("delete from asesmen_medik_dewasa_ranap where no_rawat=?", 1, new String[]{
                tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 0).toString()
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
        if (rujukan.equals("ya")) {
            ChkRujukan.setSelected(true);
            Tket_rs.setEnabled(true);
            Tket_puskes.setEnabled(true);
            Tket_praktek.setEnabled(true);
            Tket_lainya.setEnabled(true);
        } else {
            ChkRujukan.setSelected(false);
            Tket_rs.setEnabled(false);
            Tket_puskes.setEnabled(false);
            Tket_praktek.setEnabled(false);
            Tket_lainya.setEnabled(false);
        }
        
        if (dtg_sendiri.equals("ya")) {
            ChkDatangSendiri.setSelected(true);
        } else {
            ChkDatangSendiri.setSelected(false);
        }
        
        if (diantar.equals("ya")) {
            ChkDiantar.setSelected(true);
            Tdiantar.setEnabled(true);
        } else {
            ChkDiantar.setSelected(false);
            Tdiantar.setEnabled(false);
        }
        
        if (hipertensi1.equals("ya")) {
            ChkHipertensi1.setSelected(true);
        } else {
            ChkHipertensi1.setSelected(false);
        }
        
        if (dm1.equals("ya")) {
            ChkDM1.setSelected(true);
        } else {
            ChkDM1.setSelected(false);
        }
        
        if (pjk.equals("ya")) {
            ChkPJK.setSelected(true);
        } else {
            ChkPJK.setSelected(false);
        }
        
        if (asma1.equals("ya")) {
            ChkAsma1.setSelected(true);
        } else {
            ChkAsma1.setSelected(false);
        }
        
        if (strok.equals("ya")) {
            ChkStroke.setSelected(true);
        } else {
            ChkStroke.setSelected(false);
        }
        
        if (liver.equals("ya")) {
            ChkLiver.setSelected(true);
        } else {
            ChkLiver.setSelected(false);
        }
        
        if (ginjal.equals("ya")) {
            ChkGinjal.setSelected(true);
        } else {
            ChkGinjal.setSelected(false);
        }
        
        if (tb.equals("ya")) {
            ChkTB.setSelected(true);
        } else {
            ChkTB.setSelected(false);
        }
        
        if (lain1.equals("ya")) {
            ChkLain1.setSelected(true);
            Tket_lain1.setEnabled(true);
        } else {
            ChkLain1.setSelected(false);
            Tket_lain1.setEnabled(false);
        }
        
        if (hipertensi2.equals("ya")) {
            ChkHipertensi2.setSelected(true);
        } else {
            ChkHipertensi2.setSelected(false);
        }
        
        if (dm2.equals("ya")) {
            ChkDM2.setSelected(true);
        } else {
            ChkDM2.setSelected(false);
        }
        
        if (jantung.equals("ya")) {
            ChkJantung.setSelected(true);
        } else {
            ChkJantung.setSelected(false);
        }
        
        if (asma2.equals("ya")) {
            ChkAsma2.setSelected(true);
        } else {
            ChkAsma2.setSelected(false);
        }
        
        if (lain2.equals("ya")) {
            ChkLain2.setSelected(true);
            Tket_lain2.setEnabled(true);
        } else {
            ChkLain2.setSelected(false);
            Tket_lain2.setEnabled(false);
        }
        
        if (nyeri.equals("ya")) {
            ChkNyeri.setSelected(true);
            Tlokasi.setEnabled(true);
            Tintensitas.setEnabled(true);
        } else {
            ChkNyeri.setSelected(false);
            Tlokasi.setEnabled(false);
            Tintensitas.setEnabled(false);
        }
        
        if (resus.equals("ya")) {
            ChkTindakanResus.setSelected(true);
        } else {
            ChkTindakanResus.setSelected(false);
        }
        
        if (reguler.equals("ya")) {
            ChkReguler.setSelected(true);
        } else {
            ChkReguler.setSelected(false);
        }
        
        if (ireguler.equals("ya")) {
            ChkIreguler.setSelected(true);
        } else {
            ChkIreguler.setSelected(false);
        }
    }
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode1);
        try {
            if (pilihan == 1) {
                ps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_medik_dewasa_ranap pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.riw_penyakit_sekarang<>'' and p.no_rkm_medis like ? OR "
                        + "pa.riw_penyakit_sekarang<>'' and p.nm_pasien like ? OR "
                        + "pa.riw_penyakit_sekarang<>'' and pa.riw_penyakit_sekarang like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } else if (pilihan == 2) {
                ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_medik_dewasa_ranap pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.riw_alergi<>'' and p.no_rkm_medis like ? OR "
                        + "pa.riw_alergi<>'' and p.nm_pasien like ? OR "
                        + "pa.riw_alergi<>'' and pa.riw_alergi like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } else if (pilihan == 3) {
                ps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_medik_dewasa_ranap pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.hasil_pemeriksaan<>'' and p.no_rkm_medis like ? OR "
                        + "pa.hasil_pemeriksaan<>'' and p.nm_pasien like ? OR "
                        + "pa.hasil_pemeriksaan<>'' and pa.hasil_pemeriksaan like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } else if (pilihan == 4) {
                ps4 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_medik_dewasa_ranap pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.rencana_kerja<>'' and p.no_rkm_medis like ? OR "
                        + "pa.rencana_kerja<>'' and p.nm_pasien like ? OR "
                        + "pa.rencana_kerja<>'' and pa.rencana_kerja like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } else if (pilihan == 5) {
                ps5 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_medik_dewasa_ranap pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.perencanaan_pemulangan<>'' and p.no_rkm_medis like ? OR "
                        + "pa.perencanaan_pemulangan<>'' and p.nm_pasien like ? OR "
                        + "pa.perencanaan_pemulangan<>'' and pa.perencanaan_pemulangan like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } else if (pilihan == 6) {
                ps6 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_medik_dewasa_ranap pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.catatan_penting<>'' and p.no_rkm_medis like ? OR "
                        + "pa.catatan_penting<>'' and p.nm_pasien like ? OR "
                        + "pa.catatan_penting<>'' and pa.catatan_penting like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } 
            try {
                if (pilihan == 1) {
                    ps1.setString(1, "%" + TCari1.getText() + "%");
                    ps1.setString(2, "%" + TCari1.getText() + "%");
                    ps1.setString(3, "%" + TCari1.getText() + "%");
                    rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        tabMode1.addRow(new String[]{
                            rs1.getString("no_rkm_medis"),
                            rs1.getString("nm_pasien"),
                            rs1.getString("riw_penyakit_sekarang")
                        });
                    }
                } else if (pilihan == 2) {
                    ps2.setString(1, "%" + TCari1.getText() + "%");
                    ps2.setString(2, "%" + TCari1.getText() + "%");
                    ps2.setString(3, "%" + TCari1.getText() + "%");
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        tabMode1.addRow(new String[]{
                            rs2.getString("no_rkm_medis"),
                            rs2.getString("nm_pasien"),
                            rs2.getString("riw_alergi")
                        });
                    }
                } else if (pilihan == 3) {
                    ps3.setString(1, "%" + TCari1.getText() + "%");
                    ps3.setString(2, "%" + TCari1.getText() + "%");
                    ps3.setString(3, "%" + TCari1.getText() + "%");
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        tabMode1.addRow(new String[]{
                            rs3.getString("no_rkm_medis"),
                            rs3.getString("nm_pasien"),
                            rs3.getString("hasil_pemeriksaan")
                        });
                    }                    
                } else if (pilihan == 4) {
                    ps4.setString(1, "%" + TCari1.getText() + "%");
                    ps4.setString(2, "%" + TCari1.getText() + "%");
                    ps4.setString(3, "%" + TCari1.getText() + "%");
                    rs4 = ps4.executeQuery();
                    while (rs4.next()) {
                        tabMode1.addRow(new String[]{
                            rs4.getString("no_rkm_medis"),
                            rs4.getString("nm_pasien"),                            
                            rs4.getString("rencana_kerja")
                        });
                    }
                } else if (pilihan == 5) {
                    ps5.setString(1, "%" + TCari1.getText() + "%");
                    ps5.setString(2, "%" + TCari1.getText() + "%");
                    ps5.setString(3, "%" + TCari1.getText() + "%");
                    rs5 = ps5.executeQuery();
                    while (rs5.next()) {
                        tabMode1.addRow(new String[]{
                            rs5.getString("no_rkm_medis"),
                            rs5.getString("nm_pasien"),
                            rs5.getString("perencanaan_pemulangan")
                        });
                    }
                } else if (pilihan == 6) {
                    ps6.setString(1, "%" + TCari1.getText() + "%");
                    ps6.setString(2, "%" + TCari1.getText() + "%");
                    ps6.setString(3, "%" + TCari1.getText() + "%");
                    rs6 = ps6.executeQuery();
                    while (rs6.next()) {
                        tabMode1.addRow(new String[]{
                            rs6.getString("no_rkm_medis"),
                            rs6.getString("nm_pasien"),
                            rs6.getString("catatan_penting")
                        });
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                } else if (rs2 != null) {
                    rs2.close();
                } else if (rs3 != null) {
                    rs3.close();
                } else if (rs4 != null) {
                    rs4.close();
                } else if (rs5 != null) {
                    rs5.close();
                } else if (rs6 != null) {
                    rs6.close();
                }
                
                if (ps1 != null) {
                    ps1.close();
                } else if (ps2 != null) {
                    ps2.close();
                } else if (ps3 != null) {
                    ps3.close();
                } else if (ps4 != null) {
                    ps4.close();
                } else if (ps5 != null) {
                    ps5.close();
                } else if (ps6 != null) {
                    ps6.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void copas() {
        if (pilihan == 1) {
            Triw_penyakit_sekarang.setText(Ttemplate.getText());
        } else if (pilihan == 2) {
            Triw_alergi.setText(Ttemplate.getText());
        } else if (pilihan == 3) {
            Thsl_pemeriksaan.setText(Ttemplate.getText());
        } else if (pilihan == 4) {
            Trencana_kerja.setText(Ttemplate.getText());
        } else if (pilihan == 5) {
            Trencana_pulang.setText(Ttemplate.getText());
        } else if (pilihan == 6) {
            Tcatatan.setText(Ttemplate.getText());
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
            ps7 = koneksi.prepareStatement("select pg1.nama ptgs, date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i') jamlapor, "
                    + "pg2.nama dpjp, date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i') jamverif from cppt_konfirmasi_terapi ck "
                    + "inner join pegawai pg1 on pg1.nik=ck.nip_petugas_konfir inner join pegawai pg2 on pg2.nik=ck.nip_dpjp_konfir where "
                    + "ck.no_rawat = '" + norwt + "' and ck.tgl_cppt='" + tglcppt + "' and ck.cppt_shift='" + sift + "' "
                    + "and ck.jam_cppt='" + jamcppt + "' order by ck.waktu_simpan");
            try {
                rs7 = ps7.executeQuery();
                while (rs7.next()) {
                    if (dataKonfirmasi.equals("")) {
                        dataKonfirmasi = "Tgl. Lapor : " + rs7.getString("tgllapor") + ", Jam : " + rs7.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs7.getString("tglverif") + ", Jam : " + rs7.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs7.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs7.getString("dpjp");
                    } else {
                        dataKonfirmasi = dataKonfirmasi + "\n\nTgl. Lapor : " + rs7.getString("tgllapor") + ", Jam : " + rs7.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs7.getString("tglverif") + ", Jam : " + rs7.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs7.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs7.getString("dpjp");
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
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hapusDisimpan() {
        cekData();
        try {
            if (Sequel.menyimpantf("asesmen_medik_dewasa_ranap_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 112, new String[]{
                        TNoRw.getText(), Tkdkamar.getText(), Valid.SetTgl(TglAsesmen.getSelectedItem() + "") + " " + TglAsesmen.getSelectedItem().toString().substring(11, 19), rujukan, Tket_rs.getText(), Tket_puskes.getText(), Tket_praktek.getText(),
                        Tket_lainya.getText(), Tdiag_rujukan.getText(), dtg_sendiri, diantar, Tdiantar.getText(), kddokter_meriksa.getText(), kdsuper.getText(), Valid.SetTgl(TglAnamnesa.getSelectedItem() + "") + " " + TglAnamnesa.getSelectedItem().toString().substring(11, 19),
                        Tkeluhan.getText(), Valid.mysql_real_escape_stringERM(Triw_penyakit_sekarang.getText()), hipertensi1, dm1, pjk, asma1, strok, liver, ginjal, tb, lain1, Tket_lain1.getText(), cmbPernah.getSelectedItem().toString(), Tkapan.getText(), Tdimana.getText(),
                        Tdiagnosis.getText(), hipertensi2, dm2, jantung, asma2, lain2, Tket_lain2.getText(), Valid.mysql_real_escape_stringERM(Triw_alergi.getText()), nyeri, Tlokasi.getText(), Tintensitas.getText(), Tskor.getText(), cmbJenis.getSelectedItem().toString(),
                        cmbKeadaan.getSelectedItem().toString(), cmbGizi.getSelectedItem().toString(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(), resus, Tbb.getText(), Ttb.getText(), Ttd.getText(), Tnadi.getText(), Trespi.getText(), Taxila.getText(),
                        Trektal.getText(), cmbMata.getSelectedItem().toString(), cmbIkterik.getSelectedItem().toString(), cmbPupil.getSelectedItem().toString(), Tdiameter_kanan.getText(), Tdiameter_kiri.getText(), cmbUdem_palpe.getSelectedItem().toString(),
                        Ttonsil.getText(), Tfaring.getText(), Tlidah.getText(), Tbibir.getText(), Tjvp.getText(), cmbKelenjar.getSelectedItem().toString(), Tkelenjar.getText(), cmbKaku.getSelectedItem().toString(), cmbThoraks.getSelectedItem().toString(),
                        Tasimetris.getText(), Tcor.getText(), reguler, ireguler, Tmurmur.getText(), Tlain_lain.getText(), Tsuara_nfs.getText(), cmbRonchi.getSelectedItem().toString(), Tronchi.getText(), cmbWhezing.getSelectedItem().toString(),
                        Twhezing.getText(), cmbDisten.getSelectedItem().toString(), cmbMeteo.getSelectedItem().toString(), cmbPeris.getSelectedItem().toString(), cmbAsites.getSelectedItem().toString(), cmbNyeri.getSelectedItem().toString(),
                        Tlokasi_nyeri.getText(), Thepar.getText(), Tlien.getText(), cmbExtrem.getSelectedItem().toString(), cmbUdem.getSelectedItem().toString(), Tudem.getText(), Valid.mysql_real_escape_stringERM(Thsl_pemeriksaan.getText()),
                        Valid.mysql_real_escape_stringERM(Tdiagnosa1.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa2.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa3.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa4.getText()),
                        Valid.mysql_real_escape_stringERM(Trencana_kerja.getText()), Valid.mysql_real_escape_stringERM(Trencana_pulang.getText()), Valid.mysql_real_escape_stringERM(Tcatatan.getText()),
                        Valid.SetTgl(TglDpjp.getSelectedItem() + "") + " " + TglDpjp.getSelectedItem().toString().substring(11, 19),
                        kddpjp.getText(), TPemFisikLain.getText(), tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 117).toString(), saturasi.getText(), Valid.mysql_real_escape_stringERM(Tdiagnosa5.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa6.getText()),
                        Valid.mysql_real_escape_stringERM(Tdiagnosa7.getText()), "hapus", user, Sequel.cariIsi("select now()")
                    }) == true) {
                
                if (Sequel.cariInteger("select count(-1) from dpjp_ranap where no_rawat='" + TNoRw.getText() + "'") == 0) {
                    Sequel.menyimpan("dpjp_ranap", "'" + TNoRw.getText() + "','" + kddpjp.getText() + "'");
                }
            }
        } catch (Exception e) {
            System.out.println("Simpan data awal sebelum dihapus : " + e);
        }
    }
    
    private void gantiDisimpan() {
        cekData();
        try {
            if (Sequel.menyimpantf("asesmen_medik_dewasa_ranap_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 112, new String[]{
                        TNoRw.getText(), Tkdkamar.getText(), Valid.SetTgl(TglAsesmen.getSelectedItem() + "") + " " + TglAsesmen.getSelectedItem().toString().substring(11, 19), rujukan, Tket_rs.getText(), Tket_puskes.getText(), Tket_praktek.getText(),
                        Tket_lainya.getText(), Tdiag_rujukan.getText(), dtg_sendiri, diantar, Tdiantar.getText(), kddokter_meriksa.getText(), kdsuper.getText(), Valid.SetTgl(TglAnamnesa.getSelectedItem() + "") + " " + TglAnamnesa.getSelectedItem().toString().substring(11, 19),
                        Tkeluhan.getText(), Valid.mysql_real_escape_stringERM(Triw_penyakit_sekarang.getText()), hipertensi1, dm1, pjk, asma1, strok, liver, ginjal, tb, lain1, Tket_lain1.getText(), cmbPernah.getSelectedItem().toString(), Tkapan.getText(), Tdimana.getText(),
                        Tdiagnosis.getText(), hipertensi2, dm2, jantung, asma2, lain2, Tket_lain2.getText(), Valid.mysql_real_escape_stringERM(Triw_alergi.getText()), nyeri, Tlokasi.getText(), Tintensitas.getText(), Tskor.getText(), cmbJenis.getSelectedItem().toString(),
                        cmbKeadaan.getSelectedItem().toString(), cmbGizi.getSelectedItem().toString(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(), resus, Tbb.getText(), Ttb.getText(), Ttd.getText(), Tnadi.getText(), Trespi.getText(), Taxila.getText(),
                        Trektal.getText(), cmbMata.getSelectedItem().toString(), cmbIkterik.getSelectedItem().toString(), cmbPupil.getSelectedItem().toString(), Tdiameter_kanan.getText(), Tdiameter_kiri.getText(), cmbUdem_palpe.getSelectedItem().toString(),
                        Ttonsil.getText(), Tfaring.getText(), Tlidah.getText(), Tbibir.getText(), Tjvp.getText(), cmbKelenjar.getSelectedItem().toString(), Tkelenjar.getText(), cmbKaku.getSelectedItem().toString(), cmbThoraks.getSelectedItem().toString(),
                        Tasimetris.getText(), Tcor.getText(), reguler, ireguler, Tmurmur.getText(), Tlain_lain.getText(), Tsuara_nfs.getText(), cmbRonchi.getSelectedItem().toString(), Tronchi.getText(), cmbWhezing.getSelectedItem().toString(),
                        Twhezing.getText(), cmbDisten.getSelectedItem().toString(), cmbMeteo.getSelectedItem().toString(), cmbPeris.getSelectedItem().toString(), cmbAsites.getSelectedItem().toString(), cmbNyeri.getSelectedItem().toString(),
                        Tlokasi_nyeri.getText(), Thepar.getText(), Tlien.getText(), cmbExtrem.getSelectedItem().toString(), cmbUdem.getSelectedItem().toString(), Tudem.getText(), Valid.mysql_real_escape_stringERM(Thsl_pemeriksaan.getText()),
                        Valid.mysql_real_escape_stringERM(Tdiagnosa1.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa2.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa3.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa4.getText()),
                        Valid.mysql_real_escape_stringERM(Trencana_kerja.getText()), Valid.mysql_real_escape_stringERM(Trencana_pulang.getText()), Valid.mysql_real_escape_stringERM(Tcatatan.getText()),
                        Valid.SetTgl(TglDpjp.getSelectedItem() + "") + " " + TglDpjp.getSelectedItem().toString().substring(11, 19),
                        kddpjp.getText(), TPemFisikLain.getText(), tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 117).toString(), saturasi.getText(), Valid.mysql_real_escape_stringERM(Tdiagnosa5.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa6.getText()),
                        Valid.mysql_real_escape_stringERM(Tdiagnosa7.getText()), "ganti", user, Sequel.cariIsi("select now()")
                    }) == true) {
                
                if (Sequel.cariInteger("select count(-1) from dpjp_ranap where no_rawat='" + TNoRw.getText() + "'") == 0) {
                    Sequel.menyimpan("dpjp_ranap", "'" + TNoRw.getText() + "','" + kddpjp.getText() + "'");
                }
            }
        } catch (Exception e) {
            System.out.println("Simpan data awal sebelum diganti : " + e);
        }
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode3);
        try {
            psrestor = koneksi.prepareStatement("SELECT IF(pg.nama='-','Admin Utama',pg.nama) pelaku, a.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                    + "a.tgl_asesmen, a.waktu_eksekusi, upper(concat('DI',a.status_data)) sttsdata FROM asesmen_medik_dewasa_ranap_histori a "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = a.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg ON pg.nik = a.nik_eksekutor WHERE "
                    + "date(a.tgl_asesmen) between ? and ? and pg.nama like ? or "
                    + "date(a.tgl_asesmen) between ? and ? and a.no_rawat like ? or "
                    + "date(a.tgl_asesmen) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(a.tgl_asesmen) between ? and ? and a.status_data like ? or "
                    + "date(a.tgl_asesmen) between ? and ? and p.nm_pasien like ? order by a.tgl_asesmen desc");
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
                    tabMode3.addRow(new String[]{
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
        LCount1.setText("" + tabMode3.getRowCount());
    }
    
    private void kembalikanData() {
        try {
            ps8 = koneksi.prepareStatement("select * from asesmen_medik_dewasa_ranap_histori where "
                    + "waktu_eksekusi='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString() + "'");
            try {
                rs8 = ps8.executeQuery();
                while (rs8.next()) {
                    try {
                        if (Sequel.menyimpantf("asesmen_medik_dewasa_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 109, new String[]{
                                    rs8.getString("no_rawat"),
                                    rs8.getString("kd_kamar"),
                                    rs8.getString("tgl_asesmen"),
                                    rs8.getString("rujukan"),
                                    rs8.getString("ket_rs"),
                                    rs8.getString("ket_puskes"),
                                    rs8.getString("ket_praktek"),
                                    rs8.getString("ket_lainya"),
                                    rs8.getString("diagnosa_rujukan"),
                                    rs8.getString("datang_sendiri"),
                                    rs8.getString("diantar"),
                                    rs8.getString("ket_diantar"),
                                    rs8.getString("nip_dokter_memeriksa"),
                                    rs8.getString("nip_supervisor"),
                                    rs8.getString("tgl_anamnese"),
                                    rs8.getString("keluhan_utama"),
                                    rs8.getString("riw_penyakit_sekarang"),
                                    rs8.getString("hipertensi_1"),
                                    rs8.getString("dm_1"),
                                    rs8.getString("pjk"),
                                    rs8.getString("asma_1"),
                                    rs8.getString("stroke"),
                                    rs8.getString("liver"),
                                    rs8.getString("ginjal"),
                                    rs8.getString("tb_paru"),
                                    rs8.getString("lain_lain_1"),
                                    rs8.getString("ket_lain_1"),
                                    rs8.getString("pernah_dirawat"),
                                    rs8.getString("ket_kapan"),
                                    rs8.getString("ket_dimana"),
                                    rs8.getString("diagnosis"),
                                    rs8.getString("hipertensi_2"),
                                    rs8.getString("dm_2"),
                                    rs8.getString("jantung"),
                                    rs8.getString("asma_2"),
                                    rs8.getString("lain_lain_2"),
                                    rs8.getString("ket_lain_2"),
                                    rs8.getString("riw_alergi"),
                                    rs8.getString("nyeri"),
                                    rs8.getString("ket_lokasi"),
                                    rs8.getString("ket_intensitas"),
                                    rs8.getString("skor"),
                                    rs8.getString("jenis"),
                                    rs8.getString("keadaan_umum"),
                                    rs8.getString("gizi"),
                                    rs8.getString("gcs_e"),
                                    rs8.getString("gcs_m"),
                                    rs8.getString("gcs_v"),
                                    rs8.getString("tindakan_resus"),
                                    rs8.getString("bb"),
                                    rs8.getString("tb"),
                                    rs8.getString("td"),
                                    rs8.getString("nadi"),
                                    rs8.getString("respirasi"),
                                    rs8.getString("suhu_axila"),
                                    rs8.getString("suhu_rektal"),
                                    rs8.getString("mata_anemis"),
                                    rs8.getString("ikterik"),
                                    rs8.getString("pupil"),
                                    rs8.getString("diameter_kanan"),
                                    rs8.getString("diameter_kiri"),
                                    rs8.getString("udem_palpebra"),
                                    rs8.getString("tonsil"),
                                    rs8.getString("faring"),
                                    rs8.getString("lidah"),
                                    rs8.getString("bibir"),
                                    rs8.getString("jvp"),
                                    rs8.getString("kelenjar_limfe"),
                                    rs8.getString("ket_ada_kelenjar"),
                                    rs8.getString("kaku_kuduk"),
                                    rs8.getString("thoraks"),
                                    rs8.getString("ket_asimetris"),
                                    rs8.getString("cor_s1s2"),
                                    rs8.getString("reguler"),
                                    rs8.getString("ireguler"),
                                    rs8.getString("murmur"),
                                    rs8.getString("lain_lain"),
                                    rs8.getString("suara_nafas"),
                                    rs8.getString("ronchi"),
                                    rs8.getString("ket_ronchi"),
                                    rs8.getString("wheezing"),
                                    rs8.getString("ket_wheezing"),
                                    rs8.getString("distended"),
                                    rs8.getString("meteorismus"),
                                    rs8.getString("peristaltik"),
                                    rs8.getString("asites"),
                                    rs8.getString("nyeri_tekan"),
                                    rs8.getString("lokasi"),
                                    rs8.getString("hepar"),
                                    rs8.getString("lien"),
                                    rs8.getString("extremitas"),
                                    rs8.getString("udem"),
                                    rs8.getString("ket_udem"),
                                    rs8.getString("hasil_pemeriksaan"),
                                    rs8.getString("diagnosis1"),
                                    rs8.getString("diagnosis2"),
                                    rs8.getString("diagnosis3"),
                                    rs8.getString("diagnosis4"),
                                    rs8.getString("rencana_kerja"),
                                    rs8.getString("perencanaan_pemulangan"),
                                    rs8.getString("catatan_penting"),
                                    rs8.getString("tgl_dpjp"),
                                    rs8.getString("nip_dpjp"),
                                    rs8.getString("pemeriksaan_fisik_lain"),
                                    rs8.getString("waktu_simpan"),
                                    rs8.getString("saturasi"),
                                    rs8.getString("diagnosis5"),
                                    rs8.getString("diagnosis6"),
                                    rs8.getString("diagnosis7")
                                }) == true) {
                 
                            if (Sequel.cariInteger("select count(-1) from dpjp_ranap where no_rawat='" + rs8.getString("no_rawat") + "'") == 0) {
                                Sequel.menyimpan("dpjp_ranap", "'" + rs8.getString("no_rawat") + "','" + rs8.getString("nip_dpjp") + "'");
                            }                            
                        }
                    } catch (Exception e) {
                        System.out.println("Simpan : " + e);
                    }
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void kembalikanDataDiganti() {
        if (Sequel.queryu2tf("delete from asesmen_medik_dewasa_ranap where no_rawat=?", 1, new String[]{
            tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString()
        }) == true) {
            kembalikanData();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal dikembalikan..!!");
        }
    }
}
