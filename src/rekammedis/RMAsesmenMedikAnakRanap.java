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
public final class RMAsesmenMedikAnakRanap extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode3, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, pscppt, psrestor;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rscppt, rsrestor;
    private int i = 0, x = 0, pilihan = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    public DlgKamar kamar = new DlgKamar(null, false);
    private String hipertensi = "", diabetes = "", jantung = "", strok = "", asma = "", kejang = "",
            hati = "", kanker = "", tb = "", pms = "", perdarahan = "", ginjal = "", lainlain = "",
            turgor = "", sianosis = "", perdarahanKulit = "", ikterus = "", hematoma = "", sklerema = "", kutis = "",
            marmorata = "", kodekamar = "", dataKonfirmasi = "", user = "";

    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsesmenMedikAnakRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.RM", "Nama Pasien", "J.K.", "Tgl. Lahir", "Tgl. Asesmen", "Nama DPJP", "Ruang Perawatan",
            "kd_kamar", "tgl_asesmen", "keluhan_utama", "riw_penyakit_sekarang", "hipertensi", "dm", "jantung", "stroke", "asma",
            "kejang", "hati", "kanker", "tb", "pms", "perdarahan", "ginjal", "lain_lain", "kalimat_lainlain", "keadaan_umum", "kesadaran",
            "gcs_e", "gcs_m", "gcs_v", "tensi", "suhu", "nadi", "kualitas", "napas", "bb", "bb_persen", "bbpbtb_persen", "pbtb", "pbtb_persen",
            "lla", "lk", "turgor", "sianosis", "perdarahan_kulit", "ikterus", "kalimat_ikterus", "hematoma", "sklerema", "kutis", "marmorata",
            "kalimat_lainya_kulit", "kepala_bentuk", "kepala_rambut", "kepala_mata", "kepala_telinga", "kepala_hidung", "kepala_mulut",
            "kepala_lidah", "kepala_faring", "leher", "dada_bentuk", "dada_retraksi", "jantung_inspeksi", "jantung_palpasi", "jantung_perkusi",
            "jantung_auskultasi", "paru_inspeksi", "paru_palpasi", "paru_perkusi", "paru_auskultasi", "perut_inspeksi", "perut_palpasi",
            "perut_perkusi", "perut_auskultasi", "ekstremitas_umum", "ekstremitas_neurologis", "susunan_saraf_pusat", "tanda_meningen",
            "genitalia", "anus", "pemeriksaan_penunjang_pre_ranap", "diagnosa_kerja_diagnosa_banding", "pengobatan", "diet", "rencana",
            "nip_dpjp", "waktu_simpan"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbPenilaian.setModel(tabMode);
        tbPenilaian.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPenilaian.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 89; i++) {
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
        
        Tkeadaan_umum.setDocument(new batasInput((int) 200).getKata(Tkeadaan_umum));
        TKesadaran.setDocument(new batasInput((int) 200).getKata(TKesadaran));
        Tgcse.setDocument(new batasInput((int) 7).getKata(Tgcse));
        Tgcsv.setDocument(new batasInput((int) 7).getKata(Tgcsv));
        Tgcsm.setDocument(new batasInput((int) 7).getKata(Tgcsm));
        Ttensi.setDocument(new batasInput((int) 7).getKata(Ttensi));
        Tsuhu.setDocument(new batasInput((int) 7).getKata(Tsuhu));
        Tnadi.setDocument(new batasInput((int) 7).getKata(Tnadi));
        Tkualitas.setDocument(new batasInput((int) 200).getKata(Tkualitas));
        Tnapas.setDocument(new batasInput((int) 7).getKata(Tnapas));
        Tbb.setDocument(new batasInput((int) 7).getKata(Tbb));
        TbbPersen.setDocument(new batasInput((int) 7).getKata(TbbPersen));
        TbbpbPersen.setDocument(new batasInput((int) 7).getKata(TbbpbPersen));
        Tpbtb.setDocument(new batasInput((int) 7).getKata(Tpbtb));
        TpbtbPersen.setDocument(new batasInput((int) 7).getKata(TpbtbPersen));
        Tlla.setDocument(new batasInput((int) 7).getKata(Tlla));
        Tlk.setDocument(new batasInput((int) 7).getKata(Tlk));
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
                if (akses.getform().equals("RMAsesmenMedikAnakRanap")) {
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
                if (akses.getform().equals("RMAsesmenMedikAnakRanap")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        kamar.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
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
                if (akses.getform().equals("RMAsesmenMedikAnakRanap")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        kddpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        nmdpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
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
        label15 = new widget.Label();
        kddpjp = new widget.TextBox();
        nmdpjp = new widget.TextBox();
        BtnDpjp = new widget.Button();
        jLabel13 = new widget.Label();
        TRuangan = new widget.TextBox();
        label19 = new widget.Label();
        Tkeluhan = new widget.TextBox();
        scrollPane13 = new widget.ScrollPane();
        Triw_penyakit_sekarang = new widget.TextArea();
        ChkHipertensi = new widget.CekBox();
        ChkDiabetes = new widget.CekBox();
        ChkJantung = new widget.CekBox();
        ChkAsma = new widget.CekBox();
        ChkStroke = new widget.CekBox();
        ChkKejang = new widget.CekBox();
        ChkGinjal = new widget.CekBox();
        ChkTB = new widget.CekBox();
        ChkLain = new widget.CekBox();
        Tkalimat_lain = new widget.TextBox();
        ChkHati = new widget.CekBox();
        label36 = new widget.Label();
        Tgcse = new widget.TextBox();
        label37 = new widget.Label();
        Tgcsm = new widget.TextBox();
        label38 = new widget.Label();
        Tgcsv = new widget.TextBox();
        label39 = new widget.Label();
        Tbb = new widget.TextBox();
        label40 = new widget.Label();
        label43 = new widget.Label();
        Tsuhu = new widget.TextBox();
        jLabel23 = new widget.Label();
        label55 = new widget.Label();
        Tfaring = new widget.TextBox();
        PanelWall1 = new usu.widget.glass.PanelGlass();
        scrollPane15 = new widget.ScrollPane();
        Tpemeriksaan_penunjang = new widget.TextArea();
        scrollPane16 = new widget.ScrollPane();
        Tpengobatan = new widget.TextArea();
        label84 = new widget.Label();
        scrollPane17 = new widget.ScrollPane();
        Trencana = new widget.TextArea();
        BtnKamar = new widget.Button();
        jLabel47 = new widget.Label();
        Tkdkamar = new widget.TextBox();
        BtnRiwPenyakit = new widget.Button();
        BtnPemeriksaan = new widget.Button();
        BtnPengobatan = new widget.Button();
        BtnRencana = new widget.Button();
        BtnPastePemeriksaan = new widget.Button();
        BtnPastePengobatan = new widget.Button();
        label93 = new widget.Label();
        label94 = new widget.Label();
        label95 = new widget.Label();
        label96 = new widget.Label();
        label97 = new widget.Label();
        ChkKanker = new widget.CekBox();
        ChkPMS = new widget.CekBox();
        ChkPerdarahan = new widget.CekBox();
        label98 = new widget.Label();
        label99 = new widget.Label();
        Tkeadaan_umum = new widget.TextBox();
        label100 = new widget.Label();
        TKesadaran = new widget.TextBox();
        label101 = new widget.Label();
        label102 = new widget.Label();
        label103 = new widget.Label();
        Ttensi = new widget.TextBox();
        label104 = new widget.Label();
        Tnadi = new widget.TextBox();
        Tkualitas = new widget.TextBox();
        label105 = new widget.Label();
        Tnapas = new widget.TextBox();
        label106 = new widget.Label();
        label107 = new widget.Label();
        TbbPersen = new widget.TextBox();
        label108 = new widget.Label();
        label109 = new widget.Label();
        TbbpbPersen = new widget.TextBox();
        label110 = new widget.Label();
        label111 = new widget.Label();
        Tpbtb = new widget.TextBox();
        label112 = new widget.Label();
        TpbtbPersen = new widget.TextBox();
        label113 = new widget.Label();
        label114 = new widget.Label();
        Tlla = new widget.TextBox();
        label115 = new widget.Label();
        Tlk = new widget.TextBox();
        label116 = new widget.Label();
        label117 = new widget.Label();
        ChkTurgor = new widget.CekBox();
        ChkSianosis = new widget.CekBox();
        ChkPerdarahanKulit = new widget.CekBox();
        ChkIkterus = new widget.CekBox();
        Tikterus = new widget.TextBox();
        ChkHematoma = new widget.CekBox();
        ChkSklerema = new widget.CekBox();
        ChkKutis = new widget.CekBox();
        ChkMarmorata = new widget.CekBox();
        label118 = new widget.Label();
        TlainyaKulit = new widget.TextBox();
        label119 = new widget.Label();
        label120 = new widget.Label();
        Tbentuk = new widget.TextBox();
        label121 = new widget.Label();
        Trambut = new widget.TextBox();
        label122 = new widget.Label();
        Tmata = new widget.TextBox();
        label123 = new widget.Label();
        Ttelinga = new widget.TextBox();
        Thidung = new widget.TextBox();
        label124 = new widget.Label();
        label125 = new widget.Label();
        Tmulut = new widget.TextBox();
        label126 = new widget.Label();
        Tlidah = new widget.TextBox();
        label127 = new widget.Label();
        Tleher = new widget.TextBox();
        label128 = new widget.Label();
        label129 = new widget.Label();
        label130 = new widget.Label();
        Tbentuk_dada = new widget.TextBox();
        label131 = new widget.Label();
        Tretraksi_dada = new widget.TextBox();
        label132 = new widget.Label();
        label133 = new widget.Label();
        Tinspeksi_jantung = new widget.TextBox();
        label134 = new widget.Label();
        Tpalpasi_jantung = new widget.TextBox();
        label135 = new widget.Label();
        Tperkusi_jantung = new widget.TextBox();
        label136 = new widget.Label();
        Tauskultasi_jantung = new widget.TextBox();
        label137 = new widget.Label();
        label138 = new widget.Label();
        Tinspeksi_paru = new widget.TextBox();
        label139 = new widget.Label();
        Tpalpasi_paru = new widget.TextBox();
        label140 = new widget.Label();
        Tperkusi_paru = new widget.TextBox();
        label141 = new widget.Label();
        Tauskultasi_paru = new widget.TextBox();
        label142 = new widget.Label();
        label143 = new widget.Label();
        Tinspeksi_perut = new widget.TextBox();
        label144 = new widget.Label();
        Tpalpasi_perut = new widget.TextBox();
        label145 = new widget.Label();
        Tperkusi_perut = new widget.TextBox();
        label146 = new widget.Label();
        Tauskultasi_perut = new widget.TextBox();
        label147 = new widget.Label();
        label148 = new widget.Label();
        Tumum = new widget.TextBox();
        label149 = new widget.Label();
        Tneurologis = new widget.TextBox();
        label150 = new widget.Label();
        Tsusunan = new widget.TextBox();
        label151 = new widget.Label();
        Ttanda = new widget.TextBox();
        label152 = new widget.Label();
        Tgenitalia = new widget.TextBox();
        label153 = new widget.Label();
        Tanus = new widget.TextBox();
        label154 = new widget.Label();
        label155 = new widget.Label();
        scrollPane19 = new widget.ScrollPane();
        Tdiagnosa = new widget.TextArea();
        label156 = new widget.Label();
        BtnDiagnosa = new widget.Button();
        label157 = new widget.Label();
        scrollPane20 = new widget.ScrollPane();
        Tdiet = new widget.TextArea();
        BtnDiet = new widget.Button();
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

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Asesmen Medik Anak ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Medik Anak Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        FormInput.setToolTipText("Klik Kanan Pada Area Ini Untuk Melihat Hasil Pemeriksaan Penunjang Medis");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2273));
        FormInput.setLayout(null);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(279, 10, 300, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(207, 10, 70, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 10, 80, 23);

        Jk.setEditable(false);
        Jk.setForeground(new java.awt.Color(0, 0, 0));
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(774, 10, 80, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tanggal / Jam : ");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(625, 38, 100, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("J. Kel. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(730, 10, 40, 23);

        TglAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-10-2024 16:12:46" }));
        TglAsesmen.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsesmen.setName("TglAsesmen"); // NOI18N
        TglAsesmen.setOpaque(false);
        FormInput.add(TglAsesmen);
        TglAsesmen.setBounds(725, 38, 130, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/paru_form.jpg"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(530, 800, 160, 160);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("Nama DPJP :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(0, 38, 150, 23);

        kddpjp.setEditable(false);
        kddpjp.setForeground(new java.awt.Color(0, 0, 0));
        kddpjp.setName("kddpjp"); // NOI18N
        kddpjp.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(kddpjp);
        kddpjp.setBounds(154, 38, 100, 23);

        nmdpjp.setEditable(false);
        nmdpjp.setForeground(new java.awt.Color(0, 0, 0));
        nmdpjp.setName("nmdpjp"); // NOI18N
        nmdpjp.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(nmdpjp);
        nmdpjp.setBounds(257, 38, 318, 23);

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
        BtnDpjp.setBounds(580, 38, 28, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Ruang Perawatan Inap :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 66, 150, 23);

        TRuangan.setEditable(false);
        TRuangan.setForeground(new java.awt.Color(0, 0, 0));
        TRuangan.setName("TRuangan"); // NOI18N
        FormInput.add(TRuangan);
        TRuangan.setBounds(287, 66, 534, 23);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("KELUHAN UTAMA :");
        label19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label19);
        label19.setBounds(0, 94, 150, 23);

        Tkeluhan.setForeground(new java.awt.Color(0, 0, 0));
        Tkeluhan.setName("Tkeluhan"); // NOI18N
        Tkeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeluhanKeyPressed(evt);
            }
        });
        FormInput.add(Tkeluhan);
        Tkeluhan.setBounds(154, 94, 700, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N

        Triw_penyakit_sekarang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Triw_penyakit_sekarang.setColumns(20);
        Triw_penyakit_sekarang.setRows(5);
        Triw_penyakit_sekarang.setName("Triw_penyakit_sekarang"); // NOI18N
        Triw_penyakit_sekarang.setPreferredSize(new java.awt.Dimension(162, 900));
        Triw_penyakit_sekarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Triw_penyakit_sekarangKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Triw_penyakit_sekarang);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(154, 137, 700, 80);

        ChkHipertensi.setBackground(new java.awt.Color(255, 255, 250));
        ChkHipertensi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkHipertensi.setForeground(new java.awt.Color(0, 0, 0));
        ChkHipertensi.setText("Hipertensi");
        ChkHipertensi.setBorderPainted(true);
        ChkHipertensi.setBorderPaintedFlat(true);
        ChkHipertensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkHipertensi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHipertensi.setName("ChkHipertensi"); // NOI18N
        ChkHipertensi.setOpaque(false);
        ChkHipertensi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkHipertensi);
        ChkHipertensi.setBounds(154, 225, 74, 23);

        ChkDiabetes.setBackground(new java.awt.Color(255, 255, 250));
        ChkDiabetes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDiabetes.setForeground(new java.awt.Color(0, 0, 0));
        ChkDiabetes.setText("Diabetes");
        ChkDiabetes.setBorderPainted(true);
        ChkDiabetes.setBorderPaintedFlat(true);
        ChkDiabetes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDiabetes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDiabetes.setName("ChkDiabetes"); // NOI18N
        ChkDiabetes.setOpaque(false);
        ChkDiabetes.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDiabetes);
        ChkDiabetes.setBounds(235, 225, 75, 23);

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
        ChkJantung.setBounds(320, 225, 70, 23);

        ChkAsma.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsma.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsma.setText("Asma");
        ChkAsma.setBorderPainted(true);
        ChkAsma.setBorderPaintedFlat(true);
        ChkAsma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsma.setName("ChkAsma"); // NOI18N
        ChkAsma.setOpaque(false);
        ChkAsma.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAsma);
        ChkAsma.setBounds(470, 225, 55, 23);

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
        ChkStroke.setBounds(400, 225, 60, 23);

        ChkKejang.setBackground(new java.awt.Color(255, 255, 250));
        ChkKejang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKejang.setForeground(new java.awt.Color(0, 0, 0));
        ChkKejang.setText("Kejang");
        ChkKejang.setBorderPainted(true);
        ChkKejang.setBorderPaintedFlat(true);
        ChkKejang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKejang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKejang.setName("ChkKejang"); // NOI18N
        ChkKejang.setOpaque(false);
        ChkKejang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKejang);
        ChkKejang.setBounds(535, 225, 60, 23);

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
        ChkGinjal.setBounds(248, 253, 58, 23);

        ChkTB.setBackground(new java.awt.Color(255, 255, 250));
        ChkTB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTB.setForeground(new java.awt.Color(0, 0, 0));
        ChkTB.setText("TB");
        ChkTB.setBorderPainted(true);
        ChkTB.setBorderPaintedFlat(true);
        ChkTB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTB.setName("ChkTB"); // NOI18N
        ChkTB.setOpaque(false);
        ChkTB.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTB);
        ChkTB.setBounds(730, 225, 40, 23);

        ChkLain.setBackground(new java.awt.Color(255, 255, 250));
        ChkLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLain.setForeground(new java.awt.Color(0, 0, 0));
        ChkLain.setText("Lain-lain");
        ChkLain.setBorderPainted(true);
        ChkLain.setBorderPaintedFlat(true);
        ChkLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLain.setName("ChkLain"); // NOI18N
        ChkLain.setOpaque(false);
        ChkLain.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkLainActionPerformed(evt);
            }
        });
        FormInput.add(ChkLain);
        ChkLain.setBounds(315, 253, 75, 23);

        Tkalimat_lain.setForeground(new java.awt.Color(0, 0, 0));
        Tkalimat_lain.setName("Tkalimat_lain"); // NOI18N
        Tkalimat_lain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkalimat_lainKeyPressed(evt);
            }
        });
        FormInput.add(Tkalimat_lain);
        Tkalimat_lain.setBounds(394, 253, 460, 23);

        ChkHati.setBackground(new java.awt.Color(255, 255, 250));
        ChkHati.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkHati.setForeground(new java.awt.Color(0, 0, 0));
        ChkHati.setText("Hati");
        ChkHati.setBorderPainted(true);
        ChkHati.setBorderPaintedFlat(true);
        ChkHati.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkHati.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHati.setName("ChkHati"); // NOI18N
        ChkHati.setOpaque(false);
        ChkHati.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkHati);
        ChkHati.setBounds(602, 225, 50, 23);

        label36.setForeground(new java.awt.Color(0, 0, 0));
        label36.setText("GCS : E : ");
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label36);
        label36.setBounds(650, 296, 60, 23);

        Tgcse.setForeground(new java.awt.Color(0, 0, 0));
        Tgcse.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tgcse.setName("Tgcse"); // NOI18N
        Tgcse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcseKeyPressed(evt);
            }
        });
        FormInput.add(Tgcse);
        Tgcse.setBounds(713, 296, 40, 23);

        label37.setForeground(new java.awt.Color(0, 0, 0));
        label37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label37.setText("M : ");
        label37.setName("label37"); // NOI18N
        label37.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label37);
        label37.setBounds(825, 296, 24, 23);

        Tgcsm.setForeground(new java.awt.Color(0, 0, 0));
        Tgcsm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tgcsm.setName("Tgcsm"); // NOI18N
        Tgcsm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsmKeyPressed(evt);
            }
        });
        FormInput.add(Tgcsm);
        Tgcsm.setBounds(850, 296, 40, 23);

        label38.setForeground(new java.awt.Color(0, 0, 0));
        label38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label38.setText("V : ");
        label38.setName("label38"); // NOI18N
        label38.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label38);
        label38.setBounds(755, 296, 24, 23);

        Tgcsv.setForeground(new java.awt.Color(0, 0, 0));
        Tgcsv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tgcsv.setName("Tgcsv"); // NOI18N
        Tgcsv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsvKeyPressed(evt);
            }
        });
        FormInput.add(Tgcsv);
        Tgcsv.setBounds(780, 296, 40, 23);

        label39.setForeground(new java.awt.Color(0, 0, 0));
        label39.setText("Berat Badan :");
        label39.setName("label39"); // NOI18N
        label39.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label39);
        label39.setBounds(0, 395, 150, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        FormInput.add(Tbb);
        Tbb.setBounds(154, 395, 48, 23);

        label40.setForeground(new java.awt.Color(0, 0, 0));
        label40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label40.setText("Kg. ( ");
        label40.setName("label40"); // NOI18N
        label40.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label40);
        label40.setBounds(207, 395, 28, 23);

        label43.setForeground(new java.awt.Color(0, 0, 0));
        label43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label43.setText("x/menit, Kualitas : ");
        label43.setName("label43"); // NOI18N
        label43.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label43);
        label43.setBounds(280, 367, 95, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        FormInput.add(Tsuhu);
        Tsuhu.setBounds(361, 339, 48, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("°C");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(415, 339, 20, 23);

        label55.setForeground(new java.awt.Color(0, 0, 0));
        label55.setText("Faring :");
        label55.setName("label55"); // NOI18N
        label55.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label55);
        label55.setBounds(0, 718, 150, 23);

        Tfaring.setForeground(new java.awt.Color(0, 0, 0));
        Tfaring.setName("Tfaring"); // NOI18N
        Tfaring.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TfaringKeyPressed(evt);
            }
        });
        FormInput.add(Tfaring);
        Tfaring.setBounds(154, 718, 700, 23);

        PanelWall1.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/jantung_form.jpg"))); // NOI18N
        PanelWall1.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall1.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall1.setRound(false);
        PanelWall1.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall1.setLayout(null);
        FormInput.add(PanelWall1);
        PanelWall1.setBounds(690, 910, 190, 190);

        scrollPane15.setComponentPopupMenu(jPopupMenu1);
        scrollPane15.setName("scrollPane15"); // NOI18N

        Tpemeriksaan_penunjang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tpemeriksaan_penunjang.setColumns(20);
        Tpemeriksaan_penunjang.setRows(5);
        Tpemeriksaan_penunjang.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang");
        Tpemeriksaan_penunjang.setComponentPopupMenu(jPopupMenu1);
        Tpemeriksaan_penunjang.setName("Tpemeriksaan_penunjang"); // NOI18N
        Tpemeriksaan_penunjang.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tpemeriksaan_penunjang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tpemeriksaan_penunjangKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(Tpemeriksaan_penunjang);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(24, 1446, 830, 80);

        scrollPane16.setName("scrollPane16"); // NOI18N

        Tpengobatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tpengobatan.setColumns(20);
        Tpengobatan.setRows(5);
        Tpengobatan.setName("Tpengobatan"); // NOI18N
        Tpengobatan.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tpengobatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpengobatanKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(Tpengobatan);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(24, 1666, 830, 80);

        label84.setForeground(new java.awt.Color(0, 0, 0));
        label84.setText("8. RENCANA :");
        label84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label84.setName("label84"); // NOI18N
        label84.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label84);
        label84.setBounds(0, 1860, 100, 23);

        scrollPane17.setName("scrollPane17"); // NOI18N

        Trencana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Trencana.setColumns(20);
        Trencana.setRows(5);
        Trencana.setName("Trencana"); // NOI18N
        Trencana.setPreferredSize(new java.awt.Dimension(162, 6000));
        scrollPane17.setViewportView(Trencana);

        FormInput.add(scrollPane17);
        scrollPane17.setBounds(24, 1882, 830, 380);

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
        BtnKamar.setBounds(825, 66, 28, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("(Klik kanan pada halaman ini untuk melihat hasil pemeriksaan penunjang)");
        jLabel47.setComponentPopupMenu(jPopupMenu1);
        jLabel47.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(310, 1424, 380, 23);

        Tkdkamar.setEditable(false);
        Tkdkamar.setForeground(new java.awt.Color(0, 0, 0));
        Tkdkamar.setName("Tkdkamar"); // NOI18N
        Tkdkamar.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(Tkdkamar);
        Tkdkamar.setBounds(154, 66, 130, 23);

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
        BtnRiwPenyakit.setBounds(860, 137, 100, 23);

        BtnPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        BtnPemeriksaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPemeriksaan.setMnemonic('2');
        BtnPemeriksaan.setText("Template");
        BtnPemeriksaan.setToolTipText("Alt+2");
        BtnPemeriksaan.setName("BtnPemeriksaan"); // NOI18N
        BtnPemeriksaan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPemeriksaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPemeriksaanActionPerformed(evt);
            }
        });
        FormInput.add(BtnPemeriksaan);
        BtnPemeriksaan.setBounds(860, 1446, 100, 23);

        BtnPengobatan.setForeground(new java.awt.Color(0, 0, 0));
        BtnPengobatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPengobatan.setMnemonic('2');
        BtnPengobatan.setText("Template");
        BtnPengobatan.setToolTipText("Alt+2");
        BtnPengobatan.setName("BtnPengobatan"); // NOI18N
        BtnPengobatan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPengobatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPengobatanActionPerformed(evt);
            }
        });
        FormInput.add(BtnPengobatan);
        BtnPengobatan.setBounds(860, 1666, 100, 23);

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
        BtnRencana.setBounds(860, 1882, 100, 23);

        BtnPastePemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        BtnPastePemeriksaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPastePemeriksaan.setMnemonic('L');
        BtnPastePemeriksaan.setText("Paste");
        BtnPastePemeriksaan.setToolTipText("Alt+L");
        BtnPastePemeriksaan.setName("BtnPastePemeriksaan"); // NOI18N
        BtnPastePemeriksaan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPastePemeriksaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPastePemeriksaanActionPerformed(evt);
            }
        });
        FormInput.add(BtnPastePemeriksaan);
        BtnPastePemeriksaan.setBounds(860, 1476, 100, 23);

        BtnPastePengobatan.setForeground(new java.awt.Color(0, 0, 0));
        BtnPastePengobatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPastePengobatan.setMnemonic('L');
        BtnPastePengobatan.setText("Paste");
        BtnPastePengobatan.setToolTipText("Alt+L");
        BtnPastePengobatan.setName("BtnPastePengobatan"); // NOI18N
        BtnPastePengobatan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPastePengobatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPastePengobatanActionPerformed(evt);
            }
        });
        FormInput.add(BtnPastePengobatan);
        BtnPastePengobatan.setBounds(860, 1696, 100, 23);

        label93.setForeground(new java.awt.Color(0, 0, 0));
        label93.setText("RIWAYAT PENYAKIT :");
        label93.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label93.setName("label93"); // NOI18N
        label93.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label93);
        label93.setBounds(0, 122, 150, 23);

        label94.setForeground(new java.awt.Color(0, 0, 0));
        label94.setText("a. Riwayat Penyakit  ");
        label94.setName("label94"); // NOI18N
        label94.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label94);
        label94.setBounds(0, 137, 150, 23);

        label95.setForeground(new java.awt.Color(0, 0, 0));
        label95.setText("Sekarang :");
        label95.setName("label95"); // NOI18N
        label95.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label95);
        label95.setBounds(0, 152, 150, 23);

        label96.setForeground(new java.awt.Color(0, 0, 0));
        label96.setText("b. Riwayat Penyakit  ");
        label96.setName("label96"); // NOI18N
        label96.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label96);
        label96.setBounds(0, 225, 150, 23);

        label97.setForeground(new java.awt.Color(0, 0, 0));
        label97.setText("Dalam Keluarga :");
        label97.setName("label97"); // NOI18N
        label97.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label97);
        label97.setBounds(0, 240, 150, 23);

        ChkKanker.setBackground(new java.awt.Color(255, 255, 250));
        ChkKanker.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKanker.setForeground(new java.awt.Color(0, 0, 0));
        ChkKanker.setText("Kanker");
        ChkKanker.setBorderPainted(true);
        ChkKanker.setBorderPaintedFlat(true);
        ChkKanker.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKanker.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKanker.setName("ChkKanker"); // NOI18N
        ChkKanker.setOpaque(false);
        ChkKanker.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKanker);
        ChkKanker.setBounds(660, 225, 60, 23);

        ChkPMS.setBackground(new java.awt.Color(255, 255, 250));
        ChkPMS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPMS.setForeground(new java.awt.Color(0, 0, 0));
        ChkPMS.setText("PMS");
        ChkPMS.setBorderPainted(true);
        ChkPMS.setBorderPaintedFlat(true);
        ChkPMS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPMS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPMS.setName("ChkPMS"); // NOI18N
        ChkPMS.setOpaque(false);
        ChkPMS.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPMS);
        ChkPMS.setBounds(780, 225, 50, 23);

        ChkPerdarahan.setBackground(new java.awt.Color(255, 255, 250));
        ChkPerdarahan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPerdarahan.setForeground(new java.awt.Color(0, 0, 0));
        ChkPerdarahan.setText("Perdarahan");
        ChkPerdarahan.setBorderPainted(true);
        ChkPerdarahan.setBorderPaintedFlat(true);
        ChkPerdarahan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPerdarahan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPerdarahan.setName("ChkPerdarahan"); // NOI18N
        ChkPerdarahan.setOpaque(false);
        ChkPerdarahan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPerdarahan);
        ChkPerdarahan.setBounds(154, 253, 85, 23);

        label98.setForeground(new java.awt.Color(0, 0, 0));
        label98.setText("PEMERIKSAAN FISIK :");
        label98.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label98.setName("label98"); // NOI18N
        label98.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label98);
        label98.setBounds(0, 281, 150, 23);

        label99.setForeground(new java.awt.Color(0, 0, 0));
        label99.setText("a. Keadaan Umum :");
        label99.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label99.setName("label99"); // NOI18N
        label99.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label99);
        label99.setBounds(0, 296, 150, 23);

        Tkeadaan_umum.setForeground(new java.awt.Color(0, 0, 0));
        Tkeadaan_umum.setName("Tkeadaan_umum"); // NOI18N
        Tkeadaan_umum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkeadaan_umumKeyPressed(evt);
            }
        });
        FormInput.add(Tkeadaan_umum);
        Tkeadaan_umum.setBounds(154, 296, 230, 23);

        label100.setForeground(new java.awt.Color(0, 0, 0));
        label100.setText("b. Pengukuran :");
        label100.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label100.setName("label100"); // NOI18N
        label100.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label100);
        label100.setBounds(0, 324, 150, 23);

        TKesadaran.setForeground(new java.awt.Color(0, 0, 0));
        TKesadaran.setName("TKesadaran"); // NOI18N
        TKesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKesadaranKeyPressed(evt);
            }
        });
        FormInput.add(TKesadaran);
        TKesadaran.setBounds(458, 296, 190, 23);

        label101.setForeground(new java.awt.Color(0, 0, 0));
        label101.setText("Kesadaran : ");
        label101.setName("label101"); // NOI18N
        label101.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label101);
        label101.setBounds(385, 296, 70, 23);

        label102.setForeground(new java.awt.Color(0, 0, 0));
        label102.setText("Tanda Vital :");
        label102.setName("label102"); // NOI18N
        label102.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label102);
        label102.setBounds(0, 339, 150, 23);

        label103.setForeground(new java.awt.Color(0, 0, 0));
        label103.setText("Tensi : ");
        label103.setName("label103"); // NOI18N
        label103.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label103);
        label103.setBounds(154, 339, 50, 23);

        Ttensi.setForeground(new java.awt.Color(0, 0, 0));
        Ttensi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ttensi.setName("Ttensi"); // NOI18N
        Ttensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtensiKeyPressed(evt);
            }
        });
        FormInput.add(Ttensi);
        Ttensi.setBounds(205, 339, 70, 23);

        label104.setForeground(new java.awt.Color(0, 0, 0));
        label104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label104.setText("mmHg    Suhu : ");
        label104.setName("label104"); // NOI18N
        label104.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label104);
        label104.setBounds(280, 339, 80, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(205, 367, 70, 23);

        Tkualitas.setForeground(new java.awt.Color(0, 0, 0));
        Tkualitas.setName("Tkualitas"); // NOI18N
        Tkualitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkualitasKeyPressed(evt);
            }
        });
        FormInput.add(Tkualitas);
        Tkualitas.setBounds(375, 367, 210, 23);

        label105.setForeground(new java.awt.Color(0, 0, 0));
        label105.setText("Napas : ");
        label105.setName("label105"); // NOI18N
        label105.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label105);
        label105.setBounds(585, 367, 50, 23);

        Tnapas.setForeground(new java.awt.Color(0, 0, 0));
        Tnapas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tnapas.setName("Tnapas"); // NOI18N
        Tnapas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnapasKeyPressed(evt);
            }
        });
        FormInput.add(Tnapas);
        Tnapas.setBounds(639, 367, 70, 23);

        label106.setForeground(new java.awt.Color(0, 0, 0));
        label106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label106.setText("x/menit");
        label106.setName("label106"); // NOI18N
        label106.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label106);
        label106.setBounds(715, 367, 50, 23);

        label107.setForeground(new java.awt.Color(0, 0, 0));
        label107.setText("Nadi : ");
        label107.setName("label107"); // NOI18N
        label107.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label107);
        label107.setBounds(154, 367, 50, 23);

        TbbPersen.setForeground(new java.awt.Color(0, 0, 0));
        TbbPersen.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TbbPersen.setName("TbbPersen"); // NOI18N
        TbbPersen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbPersenKeyPressed(evt);
            }
        });
        FormInput.add(TbbPersen);
        TbbPersen.setBounds(235, 395, 52, 23);

        label108.setForeground(new java.awt.Color(0, 0, 0));
        label108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label108.setText("% BB/U)");
        label108.setName("label108"); // NOI18N
        label108.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label108);
        label108.setBounds(292, 395, 50, 23);

        label109.setForeground(new java.awt.Color(0, 0, 0));
        label109.setText("BB/PB-TB :");
        label109.setName("label109"); // NOI18N
        label109.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label109);
        label109.setBounds(0, 423, 150, 23);

        TbbpbPersen.setForeground(new java.awt.Color(0, 0, 0));
        TbbpbPersen.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TbbpbPersen.setName("TbbpbPersen"); // NOI18N
        TbbpbPersen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbpbPersenKeyPressed(evt);
            }
        });
        FormInput.add(TbbpbPersen);
        TbbpbPersen.setBounds(154, 423, 52, 23);

        label110.setForeground(new java.awt.Color(0, 0, 0));
        label110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label110.setText("% BB/PB-TB");
        label110.setName("label110"); // NOI18N
        label110.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label110);
        label110.setBounds(212, 423, 66, 23);

        label111.setForeground(new java.awt.Color(0, 0, 0));
        label111.setText("PB/TB :");
        label111.setName("label111"); // NOI18N
        label111.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label111);
        label111.setBounds(350, 395, 50, 23);

        Tpbtb.setForeground(new java.awt.Color(0, 0, 0));
        Tpbtb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tpbtb.setName("Tpbtb"); // NOI18N
        Tpbtb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpbtbKeyPressed(evt);
            }
        });
        FormInput.add(Tpbtb);
        Tpbtb.setBounds(406, 395, 52, 23);

        label112.setForeground(new java.awt.Color(0, 0, 0));
        label112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label112.setText("Cm ( ");
        label112.setName("label112"); // NOI18N
        label112.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label112);
        label112.setBounds(464, 395, 28, 23);

        TpbtbPersen.setForeground(new java.awt.Color(0, 0, 0));
        TpbtbPersen.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TpbtbPersen.setName("TpbtbPersen"); // NOI18N
        TpbtbPersen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpbtbPersenKeyPressed(evt);
            }
        });
        FormInput.add(TpbtbPersen);
        TpbtbPersen.setBounds(490, 395, 52, 23);

        label113.setForeground(new java.awt.Color(0, 0, 0));
        label113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label113.setText("% PB-TB/U)");
        label113.setName("label113"); // NOI18N
        label113.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label113);
        label113.setBounds(547, 395, 70, 23);

        label114.setForeground(new java.awt.Color(0, 0, 0));
        label114.setText("LLA :");
        label114.setName("label114"); // NOI18N
        label114.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label114);
        label114.setBounds(350, 423, 50, 23);

        Tlla.setForeground(new java.awt.Color(0, 0, 0));
        Tlla.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tlla.setName("Tlla"); // NOI18N
        Tlla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TllaKeyPressed(evt);
            }
        });
        FormInput.add(Tlla);
        Tlla.setBounds(406, 423, 52, 23);

        label115.setForeground(new java.awt.Color(0, 0, 0));
        label115.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label115.setText("Cm    LK  : ");
        label115.setName("label115"); // NOI18N
        label115.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label115);
        label115.setBounds(464, 423, 55, 23);

        Tlk.setForeground(new java.awt.Color(0, 0, 0));
        Tlk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tlk.setName("Tlk"); // NOI18N
        Tlk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlkKeyPressed(evt);
            }
        });
        FormInput.add(Tlk);
        Tlk.setBounds(520, 423, 52, 23);

        label116.setForeground(new java.awt.Color(0, 0, 0));
        label116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label116.setText("Cm ");
        label116.setName("label116"); // NOI18N
        label116.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label116);
        label116.setBounds(577, 423, 26, 23);

        label117.setForeground(new java.awt.Color(0, 0, 0));
        label117.setText("c. Kulit :");
        label117.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label117.setName("label117"); // NOI18N
        label117.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label117);
        label117.setBounds(0, 451, 150, 23);

        ChkTurgor.setBackground(new java.awt.Color(255, 255, 250));
        ChkTurgor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTurgor.setForeground(new java.awt.Color(0, 0, 0));
        ChkTurgor.setText("Turgor");
        ChkTurgor.setBorderPainted(true);
        ChkTurgor.setBorderPaintedFlat(true);
        ChkTurgor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTurgor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTurgor.setName("ChkTurgor"); // NOI18N
        ChkTurgor.setOpaque(false);
        ChkTurgor.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTurgor);
        ChkTurgor.setBounds(154, 451, 60, 23);

        ChkSianosis.setBackground(new java.awt.Color(255, 255, 250));
        ChkSianosis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSianosis.setForeground(new java.awt.Color(0, 0, 0));
        ChkSianosis.setText("Sianosis");
        ChkSianosis.setBorderPainted(true);
        ChkSianosis.setBorderPaintedFlat(true);
        ChkSianosis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSianosis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSianosis.setName("ChkSianosis"); // NOI18N
        ChkSianosis.setOpaque(false);
        ChkSianosis.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkSianosis);
        ChkSianosis.setBounds(224, 451, 75, 23);

        ChkPerdarahanKulit.setBackground(new java.awt.Color(255, 255, 250));
        ChkPerdarahanKulit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPerdarahanKulit.setForeground(new java.awt.Color(0, 0, 0));
        ChkPerdarahanKulit.setText("Perdarahan");
        ChkPerdarahanKulit.setBorderPainted(true);
        ChkPerdarahanKulit.setBorderPaintedFlat(true);
        ChkPerdarahanKulit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPerdarahanKulit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPerdarahanKulit.setName("ChkPerdarahanKulit"); // NOI18N
        ChkPerdarahanKulit.setOpaque(false);
        ChkPerdarahanKulit.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPerdarahanKulit);
        ChkPerdarahanKulit.setBounds(307, 451, 85, 23);

        ChkIkterus.setBackground(new java.awt.Color(255, 255, 250));
        ChkIkterus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkIkterus.setForeground(new java.awt.Color(0, 0, 0));
        ChkIkterus.setText("Ikterus : ");
        ChkIkterus.setBorderPainted(true);
        ChkIkterus.setBorderPaintedFlat(true);
        ChkIkterus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkIkterus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkIkterus.setName("ChkIkterus"); // NOI18N
        ChkIkterus.setOpaque(false);
        ChkIkterus.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkIkterus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkIkterusActionPerformed(evt);
            }
        });
        FormInput.add(ChkIkterus);
        ChkIkterus.setBounds(400, 451, 66, 23);

        Tikterus.setForeground(new java.awt.Color(0, 0, 0));
        Tikterus.setName("Tikterus"); // NOI18N
        Tikterus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TikterusKeyPressed(evt);
            }
        });
        FormInput.add(Tikterus);
        Tikterus.setBounds(469, 451, 385, 23);

        ChkHematoma.setBackground(new java.awt.Color(255, 255, 250));
        ChkHematoma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkHematoma.setForeground(new java.awt.Color(0, 0, 0));
        ChkHematoma.setText("Hematoma");
        ChkHematoma.setBorderPainted(true);
        ChkHematoma.setBorderPaintedFlat(true);
        ChkHematoma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkHematoma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHematoma.setName("ChkHematoma"); // NOI18N
        ChkHematoma.setOpaque(false);
        ChkHematoma.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkHematoma);
        ChkHematoma.setBounds(154, 479, 80, 23);

        ChkSklerema.setBackground(new java.awt.Color(255, 255, 250));
        ChkSklerema.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSklerema.setForeground(new java.awt.Color(0, 0, 0));
        ChkSklerema.setText("Sklerema");
        ChkSklerema.setBorderPainted(true);
        ChkSklerema.setBorderPaintedFlat(true);
        ChkSklerema.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSklerema.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSklerema.setName("ChkSklerema"); // NOI18N
        ChkSklerema.setOpaque(false);
        ChkSklerema.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkSklerema);
        ChkSklerema.setBounds(243, 479, 75, 23);

        ChkKutis.setBackground(new java.awt.Color(255, 255, 250));
        ChkKutis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKutis.setForeground(new java.awt.Color(0, 0, 0));
        ChkKutis.setText("Kutis");
        ChkKutis.setBorderPainted(true);
        ChkKutis.setBorderPaintedFlat(true);
        ChkKutis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKutis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKutis.setName("ChkKutis"); // NOI18N
        ChkKutis.setOpaque(false);
        ChkKutis.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKutis);
        ChkKutis.setBounds(327, 479, 55, 23);

        ChkMarmorata.setBackground(new java.awt.Color(255, 255, 250));
        ChkMarmorata.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMarmorata.setForeground(new java.awt.Color(0, 0, 0));
        ChkMarmorata.setText("Marmorata");
        ChkMarmorata.setBorderPainted(true);
        ChkMarmorata.setBorderPaintedFlat(true);
        ChkMarmorata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMarmorata.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMarmorata.setName("ChkMarmorata"); // NOI18N
        ChkMarmorata.setOpaque(false);
        ChkMarmorata.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkMarmorata);
        ChkMarmorata.setBounds(390, 479, 80, 23);

        label118.setForeground(new java.awt.Color(0, 0, 0));
        label118.setText("Lainnya : ");
        label118.setName("label118"); // NOI18N
        label118.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label118);
        label118.setBounds(477, 479, 65, 23);

        TlainyaKulit.setForeground(new java.awt.Color(0, 0, 0));
        TlainyaKulit.setName("TlainyaKulit"); // NOI18N
        TlainyaKulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainyaKulitKeyPressed(evt);
            }
        });
        FormInput.add(TlainyaKulit);
        TlainyaKulit.setBounds(544, 479, 310, 23);

        label119.setForeground(new java.awt.Color(0, 0, 0));
        label119.setText("d. Kepala :");
        label119.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label119.setName("label119"); // NOI18N
        label119.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label119);
        label119.setBounds(0, 507, 150, 23);

        label120.setForeground(new java.awt.Color(0, 0, 0));
        label120.setText("Bentuk :");
        label120.setName("label120"); // NOI18N
        label120.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label120);
        label120.setBounds(0, 522, 150, 23);

        Tbentuk.setForeground(new java.awt.Color(0, 0, 0));
        Tbentuk.setName("Tbentuk"); // NOI18N
        Tbentuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbentukKeyPressed(evt);
            }
        });
        FormInput.add(Tbentuk);
        Tbentuk.setBounds(154, 522, 700, 23);

        label121.setForeground(new java.awt.Color(0, 0, 0));
        label121.setText("Rambut :");
        label121.setName("label121"); // NOI18N
        label121.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label121);
        label121.setBounds(0, 550, 150, 23);

        Trambut.setForeground(new java.awt.Color(0, 0, 0));
        Trambut.setName("Trambut"); // NOI18N
        Trambut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrambutKeyPressed(evt);
            }
        });
        FormInput.add(Trambut);
        Trambut.setBounds(154, 550, 700, 23);

        label122.setForeground(new java.awt.Color(0, 0, 0));
        label122.setText("Mata :");
        label122.setName("label122"); // NOI18N
        label122.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label122);
        label122.setBounds(0, 578, 150, 23);

        Tmata.setForeground(new java.awt.Color(0, 0, 0));
        Tmata.setName("Tmata"); // NOI18N
        Tmata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmataKeyPressed(evt);
            }
        });
        FormInput.add(Tmata);
        Tmata.setBounds(154, 578, 700, 23);

        label123.setForeground(new java.awt.Color(0, 0, 0));
        label123.setText("Telinga :");
        label123.setName("label123"); // NOI18N
        label123.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label123);
        label123.setBounds(0, 606, 150, 23);

        Ttelinga.setForeground(new java.awt.Color(0, 0, 0));
        Ttelinga.setName("Ttelinga"); // NOI18N
        Ttelinga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtelingaKeyPressed(evt);
            }
        });
        FormInput.add(Ttelinga);
        Ttelinga.setBounds(154, 606, 700, 23);

        Thidung.setForeground(new java.awt.Color(0, 0, 0));
        Thidung.setName("Thidung"); // NOI18N
        Thidung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThidungKeyPressed(evt);
            }
        });
        FormInput.add(Thidung);
        Thidung.setBounds(154, 634, 700, 23);

        label124.setForeground(new java.awt.Color(0, 0, 0));
        label124.setText("Hidung :");
        label124.setName("label124"); // NOI18N
        label124.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label124);
        label124.setBounds(0, 634, 150, 23);

        label125.setForeground(new java.awt.Color(0, 0, 0));
        label125.setText("Mulut :");
        label125.setName("label125"); // NOI18N
        label125.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label125);
        label125.setBounds(0, 662, 150, 23);

        Tmulut.setForeground(new java.awt.Color(0, 0, 0));
        Tmulut.setName("Tmulut"); // NOI18N
        Tmulut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmulutKeyPressed(evt);
            }
        });
        FormInput.add(Tmulut);
        Tmulut.setBounds(154, 662, 700, 23);

        label126.setForeground(new java.awt.Color(0, 0, 0));
        label126.setText("Lidah :");
        label126.setName("label126"); // NOI18N
        label126.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label126);
        label126.setBounds(0, 690, 150, 23);

        Tlidah.setForeground(new java.awt.Color(0, 0, 0));
        Tlidah.setName("Tlidah"); // NOI18N
        Tlidah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlidahKeyPressed(evt);
            }
        });
        FormInput.add(Tlidah);
        Tlidah.setBounds(154, 690, 700, 23);

        label127.setForeground(new java.awt.Color(0, 0, 0));
        label127.setText("e. Leher :");
        label127.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label127.setName("label127"); // NOI18N
        label127.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label127);
        label127.setBounds(0, 746, 150, 23);

        Tleher.setForeground(new java.awt.Color(0, 0, 0));
        Tleher.setName("Tleher"); // NOI18N
        Tleher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TleherKeyPressed(evt);
            }
        });
        FormInput.add(Tleher);
        Tleher.setBounds(154, 746, 700, 23);

        label128.setForeground(new java.awt.Color(0, 0, 0));
        label128.setText("f. Dada :");
        label128.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label128.setName("label128"); // NOI18N
        label128.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label128);
        label128.setBounds(0, 774, 150, 23);

        label129.setForeground(new java.awt.Color(0, 0, 0));
        label129.setText("Dinding Dada :");
        label129.setName("label129"); // NOI18N
        label129.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label129);
        label129.setBounds(0, 789, 150, 23);

        label130.setForeground(new java.awt.Color(0, 0, 0));
        label130.setText("- Bentuk :");
        label130.setName("label130"); // NOI18N
        label130.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label130);
        label130.setBounds(0, 804, 150, 23);

        Tbentuk_dada.setForeground(new java.awt.Color(0, 0, 0));
        Tbentuk_dada.setName("Tbentuk_dada"); // NOI18N
        Tbentuk_dada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tbentuk_dadaKeyPressed(evt);
            }
        });
        FormInput.add(Tbentuk_dada);
        Tbentuk_dada.setBounds(154, 804, 350, 23);

        label131.setForeground(new java.awt.Color(0, 0, 0));
        label131.setText("- Retraksi :");
        label131.setName("label131"); // NOI18N
        label131.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label131);
        label131.setBounds(0, 832, 150, 23);

        Tretraksi_dada.setForeground(new java.awt.Color(0, 0, 0));
        Tretraksi_dada.setName("Tretraksi_dada"); // NOI18N
        Tretraksi_dada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tretraksi_dadaKeyPressed(evt);
            }
        });
        FormInput.add(Tretraksi_dada);
        Tretraksi_dada.setBounds(154, 832, 350, 23);

        label132.setForeground(new java.awt.Color(0, 0, 0));
        label132.setText("1) Jantung :");
        label132.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label132.setName("label132"); // NOI18N
        label132.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label132);
        label132.setBounds(0, 860, 150, 23);

        label133.setForeground(new java.awt.Color(0, 0, 0));
        label133.setText("Inspeksi :");
        label133.setName("label133"); // NOI18N
        label133.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label133);
        label133.setBounds(0, 875, 150, 23);

        Tinspeksi_jantung.setForeground(new java.awt.Color(0, 0, 0));
        Tinspeksi_jantung.setName("Tinspeksi_jantung"); // NOI18N
        Tinspeksi_jantung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tinspeksi_jantungKeyPressed(evt);
            }
        });
        FormInput.add(Tinspeksi_jantung);
        Tinspeksi_jantung.setBounds(154, 875, 350, 23);

        label134.setForeground(new java.awt.Color(0, 0, 0));
        label134.setText("Palpasi :");
        label134.setName("label134"); // NOI18N
        label134.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label134);
        label134.setBounds(0, 903, 150, 23);

        Tpalpasi_jantung.setForeground(new java.awt.Color(0, 0, 0));
        Tpalpasi_jantung.setName("Tpalpasi_jantung"); // NOI18N
        Tpalpasi_jantung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tpalpasi_jantungKeyPressed(evt);
            }
        });
        FormInput.add(Tpalpasi_jantung);
        Tpalpasi_jantung.setBounds(154, 903, 350, 23);

        label135.setForeground(new java.awt.Color(0, 0, 0));
        label135.setText("Perkusi :");
        label135.setName("label135"); // NOI18N
        label135.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label135);
        label135.setBounds(0, 931, 150, 23);

        Tperkusi_jantung.setForeground(new java.awt.Color(0, 0, 0));
        Tperkusi_jantung.setName("Tperkusi_jantung"); // NOI18N
        Tperkusi_jantung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tperkusi_jantungKeyPressed(evt);
            }
        });
        FormInput.add(Tperkusi_jantung);
        Tperkusi_jantung.setBounds(154, 931, 350, 23);

        label136.setForeground(new java.awt.Color(0, 0, 0));
        label136.setText("Auskultasi :");
        label136.setName("label136"); // NOI18N
        label136.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label136);
        label136.setBounds(0, 959, 150, 23);

        Tauskultasi_jantung.setForeground(new java.awt.Color(0, 0, 0));
        Tauskultasi_jantung.setName("Tauskultasi_jantung"); // NOI18N
        Tauskultasi_jantung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tauskultasi_jantungKeyPressed(evt);
            }
        });
        FormInput.add(Tauskultasi_jantung);
        Tauskultasi_jantung.setBounds(154, 959, 350, 23);

        label137.setForeground(new java.awt.Color(0, 0, 0));
        label137.setText("2) Paru :");
        label137.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label137.setName("label137"); // NOI18N
        label137.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label137);
        label137.setBounds(0, 987, 150, 23);

        label138.setForeground(new java.awt.Color(0, 0, 0));
        label138.setText("Inspeksi :");
        label138.setName("label138"); // NOI18N
        label138.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label138);
        label138.setBounds(0, 1002, 150, 23);

        Tinspeksi_paru.setForeground(new java.awt.Color(0, 0, 0));
        Tinspeksi_paru.setName("Tinspeksi_paru"); // NOI18N
        Tinspeksi_paru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tinspeksi_paruKeyPressed(evt);
            }
        });
        FormInput.add(Tinspeksi_paru);
        Tinspeksi_paru.setBounds(154, 1002, 350, 23);

        label139.setForeground(new java.awt.Color(0, 0, 0));
        label139.setText("Palpasi :");
        label139.setName("label139"); // NOI18N
        label139.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label139);
        label139.setBounds(0, 1030, 150, 23);

        Tpalpasi_paru.setForeground(new java.awt.Color(0, 0, 0));
        Tpalpasi_paru.setName("Tpalpasi_paru"); // NOI18N
        Tpalpasi_paru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tpalpasi_paruKeyPressed(evt);
            }
        });
        FormInput.add(Tpalpasi_paru);
        Tpalpasi_paru.setBounds(154, 1030, 350, 23);

        label140.setForeground(new java.awt.Color(0, 0, 0));
        label140.setText("Perkusi :");
        label140.setName("label140"); // NOI18N
        label140.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label140);
        label140.setBounds(0, 1058, 150, 23);

        Tperkusi_paru.setForeground(new java.awt.Color(0, 0, 0));
        Tperkusi_paru.setName("Tperkusi_paru"); // NOI18N
        Tperkusi_paru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tperkusi_paruKeyPressed(evt);
            }
        });
        FormInput.add(Tperkusi_paru);
        Tperkusi_paru.setBounds(154, 1058, 350, 23);

        label141.setForeground(new java.awt.Color(0, 0, 0));
        label141.setText("Auskultasi :");
        label141.setName("label141"); // NOI18N
        label141.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label141);
        label141.setBounds(0, 1086, 150, 23);

        Tauskultasi_paru.setForeground(new java.awt.Color(0, 0, 0));
        Tauskultasi_paru.setName("Tauskultasi_paru"); // NOI18N
        Tauskultasi_paru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tauskultasi_paruKeyPressed(evt);
            }
        });
        FormInput.add(Tauskultasi_paru);
        Tauskultasi_paru.setBounds(154, 1086, 350, 23);

        label142.setForeground(new java.awt.Color(0, 0, 0));
        label142.setText("g. Perut :");
        label142.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label142.setName("label142"); // NOI18N
        label142.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label142);
        label142.setBounds(0, 1114, 150, 23);

        label143.setForeground(new java.awt.Color(0, 0, 0));
        label143.setText("Inspeksi :");
        label143.setName("label143"); // NOI18N
        label143.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label143);
        label143.setBounds(0, 1129, 150, 23);

        Tinspeksi_perut.setForeground(new java.awt.Color(0, 0, 0));
        Tinspeksi_perut.setName("Tinspeksi_perut"); // NOI18N
        Tinspeksi_perut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tinspeksi_perutKeyPressed(evt);
            }
        });
        FormInput.add(Tinspeksi_perut);
        Tinspeksi_perut.setBounds(154, 1129, 700, 23);

        label144.setForeground(new java.awt.Color(0, 0, 0));
        label144.setText("Palpasi :");
        label144.setName("label144"); // NOI18N
        label144.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label144);
        label144.setBounds(0, 1157, 150, 23);

        Tpalpasi_perut.setForeground(new java.awt.Color(0, 0, 0));
        Tpalpasi_perut.setName("Tpalpasi_perut"); // NOI18N
        Tpalpasi_perut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tpalpasi_perutKeyPressed(evt);
            }
        });
        FormInput.add(Tpalpasi_perut);
        Tpalpasi_perut.setBounds(154, 1157, 700, 23);

        label145.setForeground(new java.awt.Color(0, 0, 0));
        label145.setText("Perkusi :");
        label145.setName("label145"); // NOI18N
        label145.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label145);
        label145.setBounds(0, 1185, 150, 23);

        Tperkusi_perut.setForeground(new java.awt.Color(0, 0, 0));
        Tperkusi_perut.setName("Tperkusi_perut"); // NOI18N
        Tperkusi_perut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tperkusi_perutKeyPressed(evt);
            }
        });
        FormInput.add(Tperkusi_perut);
        Tperkusi_perut.setBounds(154, 1185, 700, 23);

        label146.setForeground(new java.awt.Color(0, 0, 0));
        label146.setText("Auskultasi :");
        label146.setName("label146"); // NOI18N
        label146.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label146);
        label146.setBounds(0, 1213, 150, 23);

        Tauskultasi_perut.setForeground(new java.awt.Color(0, 0, 0));
        Tauskultasi_perut.setName("Tauskultasi_perut"); // NOI18N
        Tauskultasi_perut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tauskultasi_perutKeyPressed(evt);
            }
        });
        FormInput.add(Tauskultasi_perut);
        Tauskultasi_perut.setBounds(154, 1213, 700, 23);

        label147.setForeground(new java.awt.Color(0, 0, 0));
        label147.setText("h. Ekstremitas :");
        label147.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label147.setName("label147"); // NOI18N
        label147.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label147);
        label147.setBounds(0, 1241, 150, 23);

        label148.setForeground(new java.awt.Color(0, 0, 0));
        label148.setText("Umum :");
        label148.setName("label148"); // NOI18N
        label148.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label148);
        label148.setBounds(0, 1256, 150, 23);

        Tumum.setForeground(new java.awt.Color(0, 0, 0));
        Tumum.setName("Tumum"); // NOI18N
        Tumum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumumKeyPressed(evt);
            }
        });
        FormInput.add(Tumum);
        Tumum.setBounds(154, 1256, 700, 23);

        label149.setForeground(new java.awt.Color(0, 0, 0));
        label149.setText("Neurologis :");
        label149.setName("label149"); // NOI18N
        label149.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label149);
        label149.setBounds(0, 1284, 150, 23);

        Tneurologis.setForeground(new java.awt.Color(0, 0, 0));
        Tneurologis.setName("Tneurologis"); // NOI18N
        Tneurologis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TneurologisKeyPressed(evt);
            }
        });
        FormInput.add(Tneurologis);
        Tneurologis.setBounds(154, 1284, 700, 23);

        label150.setForeground(new java.awt.Color(0, 0, 0));
        label150.setText("i. Susunan Saraf Pusat :");
        label150.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label150.setName("label150"); // NOI18N
        label150.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label150);
        label150.setBounds(0, 1312, 170, 23);

        Tsusunan.setForeground(new java.awt.Color(0, 0, 0));
        Tsusunan.setName("Tsusunan"); // NOI18N
        Tsusunan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsusunanKeyPressed(evt);
            }
        });
        FormInput.add(Tsusunan);
        Tsusunan.setBounds(174, 1312, 680, 23);

        label151.setForeground(new java.awt.Color(0, 0, 0));
        label151.setText("j. Tanda-tanda Meningen :");
        label151.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label151.setName("label151"); // NOI18N
        label151.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label151);
        label151.setBounds(0, 1340, 170, 23);

        Ttanda.setForeground(new java.awt.Color(0, 0, 0));
        Ttanda.setName("Ttanda"); // NOI18N
        Ttanda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtandaKeyPressed(evt);
            }
        });
        FormInput.add(Ttanda);
        Ttanda.setBounds(174, 1340, 680, 23);

        label152.setForeground(new java.awt.Color(0, 0, 0));
        label152.setText("k. Genitalia :");
        label152.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label152.setName("label152"); // NOI18N
        label152.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label152);
        label152.setBounds(0, 1368, 170, 23);

        Tgenitalia.setForeground(new java.awt.Color(0, 0, 0));
        Tgenitalia.setName("Tgenitalia"); // NOI18N
        Tgenitalia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgenitaliaKeyPressed(evt);
            }
        });
        FormInput.add(Tgenitalia);
        Tgenitalia.setBounds(174, 1368, 680, 23);

        label153.setForeground(new java.awt.Color(0, 0, 0));
        label153.setText("l. Anus :");
        label153.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label153.setName("label153"); // NOI18N
        label153.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label153);
        label153.setBounds(0, 1396, 170, 23);

        Tanus.setForeground(new java.awt.Color(0, 0, 0));
        Tanus.setName("Tanus"); // NOI18N
        Tanus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanusKeyPressed(evt);
            }
        });
        FormInput.add(Tanus);
        Tanus.setBounds(174, 1396, 680, 23);

        label154.setForeground(new java.awt.Color(0, 0, 0));
        label154.setText("4. PEMERIKSAAN PENUNJANG PRE RAWAT INAP :");
        label154.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label154.setName("label154"); // NOI18N
        label154.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label154);
        label154.setBounds(0, 1424, 300, 23);

        label155.setForeground(new java.awt.Color(0, 0, 0));
        label155.setText("5. DIAGNOSA KERJA & DIAGNOSA BANDING :");
        label155.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label155.setName("label155"); // NOI18N
        label155.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label155);
        label155.setBounds(0, 1535, 275, 23);

        scrollPane19.setComponentPopupMenu(jPopupMenu1);
        scrollPane19.setName("scrollPane19"); // NOI18N

        Tdiagnosa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tdiagnosa.setColumns(20);
        Tdiagnosa.setRows(5);
        Tdiagnosa.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang");
        Tdiagnosa.setComponentPopupMenu(jPopupMenu1);
        Tdiagnosa.setName("Tdiagnosa"); // NOI18N
        Tdiagnosa.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tdiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaKeyPressed(evt);
            }
        });
        scrollPane19.setViewportView(Tdiagnosa);

        FormInput.add(scrollPane19);
        scrollPane19.setBounds(24, 1557, 830, 80);

        label156.setForeground(new java.awt.Color(0, 0, 0));
        label156.setText("6. PENGOBATAN :");
        label156.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label156.setName("label156"); // NOI18N
        label156.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label156);
        label156.setBounds(0, 1644, 121, 23);

        BtnDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagnosa.setMnemonic('2');
        BtnDiagnosa.setText("Template");
        BtnDiagnosa.setToolTipText("Alt+2");
        BtnDiagnosa.setName("BtnDiagnosa"); // NOI18N
        BtnDiagnosa.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagnosaActionPerformed(evt);
            }
        });
        FormInput.add(BtnDiagnosa);
        BtnDiagnosa.setBounds(860, 1557, 100, 23);

        label157.setForeground(new java.awt.Color(0, 0, 0));
        label157.setText("7. DIET :");
        label157.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label157.setName("label157"); // NOI18N
        label157.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label157);
        label157.setBounds(0, 1752, 72, 23);

        scrollPane20.setName("scrollPane20"); // NOI18N

        Tdiet.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tdiet.setColumns(20);
        Tdiet.setRows(5);
        Tdiet.setName("Tdiet"); // NOI18N
        Tdiet.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tdiet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdietKeyPressed(evt);
            }
        });
        scrollPane20.setViewportView(Tdiet);

        FormInput.add(scrollPane20);
        scrollPane20.setBounds(24, 1774, 830, 80);

        BtnDiet.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiet.setMnemonic('2');
        BtnDiet.setText("Template");
        BtnDiet.setToolTipText("Alt+2");
        BtnDiet.setName("BtnDiet"); // NOI18N
        BtnDiet.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDietActionPerformed(evt);
            }
        });
        FormInput.add(BtnDiet);
        BtnDiet.setBounds(860, 1774, 100, 23);

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
        BtnPasteRencana.setBounds(860, 1912, 100, 23);

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
        WindowTemplate.dispose();
        WindowRiwayat.dispose();
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
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            param.put("keluhan", Tkeluhan.getText());
            param.put("riw_penyakit_skrng", Triw_penyakit_sekarang.getText());
            
            if (ChkHipertensi.isSelected() == true) {
                param.put("hipertensi", "V");
            } else {
                param.put("hipertensi", "");
            }
            
            if (ChkDiabetes.isSelected() == true) {
                param.put("diabetes", "V");
            } else {
                param.put("diabetes", "");
            }
            
            if (ChkJantung.isSelected() == true) {
                param.put("jantung", "V");
            } else {
                param.put("jantung", "");
            }
            
            if (ChkStroke.isSelected() == true) {
                param.put("strok", "V");
            } else {
                param.put("strok", "");
            }
            
            if (ChkAsma.isSelected() == true) {
                param.put("asma", "V");
            } else {
                param.put("asma", "");
            }
            
            if (ChkKejang.isSelected() == true) {
                param.put("kejang", "V");
            } else {
                param.put("kejang", "");
            }
            
            if (ChkHati.isSelected() == true) {
                param.put("hati", "V");
            } else {
                param.put("hati", "");
            }
            
            if (ChkKanker.isSelected() == true) {
                param.put("kanker", "V");
            } else {
                param.put("kanker", "");
            }
            
            if (ChkTB.isSelected() == true) {
                param.put("tb", "V");
            } else {
                param.put("tb", "");
            }
            
            if (ChkPMS.isSelected() == true) {
                param.put("pms", "V");
            } else {
                param.put("pms", "");
            }
            
            if (ChkPerdarahan.isSelected() == true) {
                param.put("perdarahan", "V");
            } else {
                param.put("perdarahan", "");
            }
            
            if (ChkGinjal.isSelected() == true) {
                param.put("ginjal", "V");
            } else {
                param.put("ginjal", "");
            }
            
            if (ChkLain.isSelected() == true) {
                param.put("lainlain", "V");
                param.put("kalimat_lain", "Lain-lain : " + Tkalimat_lain.getText());
            } else {
                param.put("lainlain", "");
                param.put("kalimat_lain", "Lain-lain : -");
            }
            
            param.put("keadaan_umum", Tkeadaan_umum.getText());
            param.put("kesadaran", TKesadaran.getText());
            param.put("gcs", Tgcse.getText() + ", V : " + Tgcsv.getText() + ", M : " + Tgcsm.getText());
            param.put("tensi", Ttensi.getText() + " mmHg");
            param.put("suhu", Tsuhu.getText() + " °C");
            param.put("nadi", Tnadi.getText() + " x/menit");
            param.put("kualitas", Tkualitas.getText());
            param.put("napas", Tnapas.getText() + " x/menit");
            param.put("bb", Tbb.getText() + " Kg. (" + TbbPersen.getText() + " % BB/U)");
            param.put("bbpb", TbbpbPersen.getText() + " % BB/PB-TB");
            param.put("pbtb", Tpbtb.getText() + " Cm. (" + TpbtbPersen.getText() + " % PB-TB/U)");
            param.put("lla", Tlla.getText() + " Cm.  LK : " + Tlk.getText() + " Cm.");
            
            if (ChkTurgor.isSelected() == true) {
                param.put("turgor", "V");
            } else {
                param.put("turgor", "");
            }
            
            if (ChkSianosis.isSelected() == true) {
                param.put("sianosis", "V");
            } else {
                param.put("sianosis", "");
            }
            
            if (ChkPerdarahanKulit.isSelected() == true) {
                param.put("perdarahan_kulit", "V");
            } else {
                param.put("perdarahan_kulit", "");
            }
            
            if (ChkIkterus.isSelected() == true) {
                param.put("ikterus", "V");
                param.put("kalimat_ikterus", "Ikterus : " + Tikterus.getText());
            } else {
                param.put("ikterus", "");
                param.put("kalimat_ikterus", "Ikterus : -");
            }
            
            if (ChkHematoma.isSelected() == true) {
                param.put("hematoma", "V");
            } else {
                param.put("hematoma", "");
            }
            
            if (ChkSklerema.isSelected() == true) {
                param.put("sklerema", "V");
            } else {
                param.put("sklerema", "");
            }
            
            if (ChkKutis.isSelected() == true) {
                param.put("kutis", "V");
            } else {
                param.put("kutis", "");
            }
            
            if (ChkMarmorata.isSelected() == true) {
                param.put("marmorata", "V");
            } else {
                param.put("marmorata", "");
            }
            
            param.put("lainya_kulit", TlainyaKulit.getText());
            param.put("kepala_bentuk", Tbentuk.getText());
            param.put("kepala_rambut", Trambut.getText());
            param.put("kepala_mata", Tmata.getText());
            param.put("kepala_telinga", Ttelinga.getText());
            param.put("kepala_hidung", Thidung.getText());
            param.put("kepala_mulut", Tmulut.getText());
            param.put("kepala_lidah", Tlidah.getText());
            param.put("kepala_faring", Tfaring.getText());
            
            param.put("leher", Tleher.getText());
            param.put("bentuk_dada", Tbentuk_dada.getText());
            param.put("bentuk_retraksi", Tretraksi_dada.getText());
            
            param.put("jantung_inspeksi", Tinspeksi_jantung.getText());
            param.put("jantung_palpasi", Tpalpasi_jantung.getText());
            param.put("jantung_perkusi", Tperkusi_jantung.getText());
            param.put("jantung_auskultasi", Tauskultasi_jantung.getText());
            
            param.put("paru_inspeksi", Tinspeksi_paru.getText());
            param.put("paru_palpasi", Tpalpasi_paru.getText());
            param.put("paru_perkusi", Tperkusi_paru.getText());
            param.put("paru_auskultasi", Tauskultasi_paru.getText());
            
            param.put("perut_inspeksi", Tinspeksi_perut.getText());
            param.put("perut_palpasi", Tpalpasi_perut.getText());
            param.put("perut_perkusi", Tperkusi_perut.getText());
            param.put("perut_auskultasi", Tauskultasi_perut.getText());
            
            param.put("umum", Tumum.getText());
            param.put("neurologis", Tneurologis.getText());
            param.put("susunan", Tsusunan.getText());
            param.put("tanda", Ttanda.getText());
            param.put("genitalia", Tgenitalia.getText());
            param.put("anus", Tanus.getText());
            
            param.put("pemeriksaan_penunjang", Tpemeriksaan_penunjang.getText() + "\n");
            param.put("diagnosa", Tdiagnosa.getText() + "\n");
            param.put("pengobatan", Tpengobatan.getText() + "\n");
            param.put("diet", Tdiet.getText() + "\n");
            param.put("rencana", Trencana.getText() + "\n");
            param.put("tanggal", "Tanggal : " + Valid.SetTglINDONESIA(Sequel.cariIsi("select date(tgl_asesmen) from asesmen_medik_anak_ranap where no_rawat='" + TNoRw.getText() + "'"))
                    + ", Jam : " + Sequel.cariIsi("select time_format(tgl_asesmen,'%H:%i') from asesmen_medik_anak_ranap where no_rawat='" + TNoRw.getText() + "'") + " Wita");
            param.put("dpjp", "(" + nmdpjp.getText() + ")");

            Valid.MyReport("rptCetakAsesmenMedikAnakRanap1.jasper", "report", "::[ Laporan Asesmen Medik Anak hal. 1 ]::",
                    "SELECT now() tanggal", param);
            Valid.MyReport("rptCetakAsesmenMedikAnakRanap2.jasper", "report", "::[ Laporan Asesmen Medik Anak hal. 2 ]::",
                    "SELECT now() tanggal", param);
            
            emptTeks();            
            TabRawat.setSelectedIndex(1);
            tampil();            
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
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
        if (Sequel.cariInteger("select count(-1) from asesmen_medik_anak_ranap where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from asesmen_medik_anak_ranap where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void BtnDpjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDpjpActionPerformed
        akses.setform("RMAsesmenMedikAnakRanap");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDpjpActionPerformed

    private void ChkMerokokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkMerokokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkMerokokActionPerformed

    private void Triw_penyakit_sekarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Triw_penyakit_sekarangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            ChkHipertensi.requestFocus();
        }
    }//GEN-LAST:event_Triw_penyakit_sekarangKeyPressed

    private void TkeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeluhanKeyPressed
        Valid.pindah(evt, Tkeluhan, Triw_penyakit_sekarang);
    }//GEN-LAST:event_TkeluhanKeyPressed

    private void ChkAsma1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAsma1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAsma1ActionPerformed

    private void Tkalimat_lainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkalimat_lainKeyPressed
        Valid.pindah(evt, Tkalimat_lain, Tkeadaan_umum);
    }//GEN-LAST:event_Tkalimat_lainKeyPressed

    private void ChkLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkLainActionPerformed
        if (ChkLain.isSelected() == true) {
            Tkalimat_lain.setEnabled(true);
            Tkalimat_lain.setText("");
            Tkalimat_lain.requestFocus();
        } else {
            Tkalimat_lain.setEnabled(false);
            Tkalimat_lain.setText("");
        }
    }//GEN-LAST:event_ChkLainActionPerformed

    private void TgcseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcseKeyPressed
        Valid.pindah(evt, TKesadaran, Tgcsv);
    }//GEN-LAST:event_TgcseKeyPressed

    private void TgcsmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsmKeyPressed
        Valid.pindah(evt, Tgcsv, Ttensi);
    }//GEN-LAST:event_TgcsmKeyPressed

    private void TgcsvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsvKeyPressed
        Valid.pindah(evt, Tgcse, Tgcsm);
    }//GEN-LAST:event_TgcsvKeyPressed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Tbb.getText().contains(",") == true) {
                Tbb.setText(Tbb.getText().replaceAll(",", "."));
            }
            TbbPersen.requestFocus();
        }
    }//GEN-LAST:event_TbbKeyPressed

    private void TfaringKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfaringKeyPressed
        Valid.pindah(evt, Tlidah, Tleher);
    }//GEN-LAST:event_TfaringKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        Valid.pindah(evt, Ttensi, Tnadi);
    }//GEN-LAST:event_TsuhuKeyPressed

    private void Tpemeriksaan_penunjangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tpemeriksaan_penunjangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tdiagnosa.requestFocus();
        }
    }//GEN-LAST:event_Tpemeriksaan_penunjangKeyPressed

    private void TpengobatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpengobatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tdiet.requestFocus();
        }
    }//GEN-LAST:event_TpengobatanKeyPressed

    private void BtnKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKamarActionPerformed
        akses.setform("RMAsesmenMedikAnakRanap");
        kamar.load();
        kamar.isCek();
        kamar.emptTeks();
        kamar.tampil();
        kamar.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        kamar.setLocationRelativeTo(internalFrame1);
        kamar.setVisible(true);
    }//GEN-LAST:event_BtnKamarActionPerformed

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

    private void BtnPemeriksaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPemeriksaanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 2;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Pemeriksaan Penunjang Pre Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnPemeriksaanActionPerformed

    private void BtnPengobatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPengobatanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 4;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Pengobatan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnPengobatanActionPerformed

    private void BtnRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRencanaActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 6;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Rencana ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnRencanaActionPerformed

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

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        kodekamar = "";
        kodekamar = Sequel.cariIsi("select ki.kd_kamar from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
                + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMAsesmenMedikAnakRanap");
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

    private void BtnPastePemeriksaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPastePemeriksaanActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (Tpemeriksaan_penunjang.getText().equals("")) {
                Tpemeriksaan_penunjang.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                Tpemeriksaan_penunjang.setText(Tpemeriksaan_penunjang.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPastePemeriksaanActionPerformed

    private void BtnPastePengobatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPastePengobatanActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (Tpengobatan.getText().equals("")) {
                Tpengobatan.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                Tpengobatan.setText(Tpengobatan.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPastePengobatanActionPerformed

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
                    if (Sequel.cariInteger("select count(-1) from asesmen_medik_anak_ranap where "
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
        akses.setform("RMAsesmenMedikAnakRanap");
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
            akses.setform("RMAsesmenMedikAnakRanap");
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
            akses.setform("RMAsesmenMedikAnakRanap");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void Tkeadaan_umumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkeadaan_umumKeyPressed
        Valid.pindah(evt, Tkalimat_lain, TKesadaran);
    }//GEN-LAST:event_Tkeadaan_umumKeyPressed

    private void TKesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKesadaranKeyPressed
        Valid.pindah(evt, Tkeadaan_umum, Tgcse);
    }//GEN-LAST:event_TKesadaranKeyPressed

    private void TtensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtensiKeyPressed
        Valid.pindah(evt, Tgcsm, Tsuhu);
    }//GEN-LAST:event_TtensiKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        Valid.pindah(evt, Tsuhu, Tkualitas);
    }//GEN-LAST:event_TnadiKeyPressed

    private void TkualitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkualitasKeyPressed
        Valid.pindah(evt, Tnadi, Tnapas);
    }//GEN-LAST:event_TkualitasKeyPressed

    private void TnapasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnapasKeyPressed
        Valid.pindah(evt, Tkualitas, Tbb);
    }//GEN-LAST:event_TnapasKeyPressed

    private void TbbPersenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbPersenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TbbPersenKeyPressed

    private void TbbpbPersenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbpbPersenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TbbpbPersenKeyPressed

    private void TpbtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpbtbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Tpbtb.getText().contains(",") == true) {
                Tpbtb.setText(Tpbtb.getText().replaceAll(",", "."));
            }
            TpbtbPersen.requestFocus();
        }
    }//GEN-LAST:event_TpbtbKeyPressed

    private void TpbtbPersenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpbtbPersenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TpbtbPersenKeyPressed

    private void TllaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TllaKeyPressed
        Valid.pindah(evt, TpbtbPersen, Tlk);
    }//GEN-LAST:event_TllaKeyPressed

    private void TlkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlkKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TlkKeyPressed

    private void TikterusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TikterusKeyPressed
        Valid.pindah(evt, Tlk, TlainyaKulit);
    }//GEN-LAST:event_TikterusKeyPressed

    private void ChkIkterusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkIkterusActionPerformed
        if (ChkIkterus.isSelected() == true) {
            Tikterus.setEnabled(true);
            Tikterus.setText("");
            Tikterus.requestFocus();
        } else {
            Tikterus.setEnabled(false);
            Tikterus.setText("");
        }
    }//GEN-LAST:event_ChkIkterusActionPerformed

    private void TlainyaKulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainyaKulitKeyPressed
        Valid.pindah(evt, Tikterus, Tbentuk);
    }//GEN-LAST:event_TlainyaKulitKeyPressed

    private void TbentukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbentukKeyPressed
        Valid.pindah(evt, TlainyaKulit, Trambut);
    }//GEN-LAST:event_TbentukKeyPressed

    private void TrambutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrambutKeyPressed
        Valid.pindah(evt, Tbentuk, Tmata);
    }//GEN-LAST:event_TrambutKeyPressed

    private void TmataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmataKeyPressed
        Valid.pindah(evt, Trambut, Ttelinga);
    }//GEN-LAST:event_TmataKeyPressed

    private void TtelingaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtelingaKeyPressed
        Valid.pindah(evt, Tmata, Thidung);
    }//GEN-LAST:event_TtelingaKeyPressed

    private void ThidungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThidungKeyPressed
        Valid.pindah(evt, Ttelinga, Tmulut);
    }//GEN-LAST:event_ThidungKeyPressed

    private void TmulutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmulutKeyPressed
        Valid.pindah(evt, Thidung, Tlidah);
    }//GEN-LAST:event_TmulutKeyPressed

    private void TlidahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlidahKeyPressed
        Valid.pindah(evt, Tmulut, Tfaring);
    }//GEN-LAST:event_TlidahKeyPressed

    private void TleherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TleherKeyPressed
        Valid.pindah(evt, Tfaring, Tbentuk_dada);
    }//GEN-LAST:event_TleherKeyPressed

    private void Tbentuk_dadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tbentuk_dadaKeyPressed
        Valid.pindah(evt, Tleher, Tretraksi_dada);
    }//GEN-LAST:event_Tbentuk_dadaKeyPressed

    private void Tretraksi_dadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tretraksi_dadaKeyPressed
        Valid.pindah(evt, Tbentuk_dada, Tinspeksi_jantung);
    }//GEN-LAST:event_Tretraksi_dadaKeyPressed

    private void Tinspeksi_jantungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tinspeksi_jantungKeyPressed
        Valid.pindah(evt, Tretraksi_dada, Tpalpasi_jantung);
    }//GEN-LAST:event_Tinspeksi_jantungKeyPressed

    private void Tpalpasi_jantungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tpalpasi_jantungKeyPressed
        Valid.pindah(evt, Tinspeksi_jantung, Tperkusi_jantung);
    }//GEN-LAST:event_Tpalpasi_jantungKeyPressed

    private void Tperkusi_jantungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tperkusi_jantungKeyPressed
        Valid.pindah(evt, Tpalpasi_jantung, Tauskultasi_jantung);
    }//GEN-LAST:event_Tperkusi_jantungKeyPressed

    private void Tauskultasi_jantungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tauskultasi_jantungKeyPressed
        Valid.pindah(evt, Tperkusi_jantung, Tinspeksi_paru);
    }//GEN-LAST:event_Tauskultasi_jantungKeyPressed

    private void Tinspeksi_paruKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tinspeksi_paruKeyPressed
        Valid.pindah(evt, Tauskultasi_jantung, Tpalpasi_paru);
    }//GEN-LAST:event_Tinspeksi_paruKeyPressed

    private void Tpalpasi_paruKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tpalpasi_paruKeyPressed
        Valid.pindah(evt, Tinspeksi_paru, Tperkusi_paru);
    }//GEN-LAST:event_Tpalpasi_paruKeyPressed

    private void Tperkusi_paruKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tperkusi_paruKeyPressed
        Valid.pindah(evt, Tpalpasi_paru, Tauskultasi_paru);
    }//GEN-LAST:event_Tperkusi_paruKeyPressed

    private void Tauskultasi_paruKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tauskultasi_paruKeyPressed
        Valid.pindah(evt, Tperkusi_paru, Tinspeksi_perut);
    }//GEN-LAST:event_Tauskultasi_paruKeyPressed

    private void Tinspeksi_perutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tinspeksi_perutKeyPressed
        Valid.pindah(evt, Tauskultasi_paru, Tpalpasi_perut);
    }//GEN-LAST:event_Tinspeksi_perutKeyPressed

    private void Tpalpasi_perutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tpalpasi_perutKeyPressed
        Valid.pindah(evt, Tinspeksi_perut, Tperkusi_perut);
    }//GEN-LAST:event_Tpalpasi_perutKeyPressed

    private void Tperkusi_perutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tperkusi_perutKeyPressed
        Valid.pindah(evt, Tpalpasi_perut, Tauskultasi_perut);
    }//GEN-LAST:event_Tperkusi_perutKeyPressed

    private void Tauskultasi_perutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tauskultasi_perutKeyPressed
        Valid.pindah(evt, Tperkusi_perut, Tumum);
    }//GEN-LAST:event_Tauskultasi_perutKeyPressed

    private void TumumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumumKeyPressed
        Valid.pindah(evt, Tauskultasi_perut, Tneurologis);
    }//GEN-LAST:event_TumumKeyPressed

    private void TneurologisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TneurologisKeyPressed
        Valid.pindah(evt, Tumum, Tsusunan);
    }//GEN-LAST:event_TneurologisKeyPressed

    private void TsusunanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsusunanKeyPressed
        Valid.pindah(evt, Tneurologis, Ttanda);
    }//GEN-LAST:event_TsusunanKeyPressed

    private void TtandaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtandaKeyPressed
        Valid.pindah(evt, Tsusunan, Tgenitalia);
    }//GEN-LAST:event_TtandaKeyPressed

    private void TgenitaliaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgenitaliaKeyPressed
        Valid.pindah(evt, Ttanda, Tanus);
    }//GEN-LAST:event_TgenitaliaKeyPressed

    private void TanusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanusKeyPressed
        Valid.pindah(evt, Tgenitalia, Tpemeriksaan_penunjang);
    }//GEN-LAST:event_TanusKeyPressed

    private void TdiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tpengobatan.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaKeyPressed

    private void BtnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagnosaActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 3;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Diagnosa Kerja & Diagnosa Banding ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnDiagnosaActionPerformed

    private void TdietKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdietKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Trencana.requestFocus();
        }
    }//GEN-LAST:event_TdietKeyPressed

    private void BtnDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDietActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 5;
        tampilTemplate();
        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Diet ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnDietActionPerformed

    private void BtnPasteRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteRencanaActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (Trencana.getText().equals("")) {
                Trencana.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                Trencana.setText(Trencana.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteRencanaActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenMedikAnakRanap dialog = new RMAsesmenMedikAnakRanap(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn10;
    private widget.Button BtnCopas;
    private widget.Button BtnDiagnosa;
    private widget.Button BtnDiet;
    private widget.Button BtnDpjp;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKamar;
    private widget.Button BtnKeluar;
    private widget.Button BtnNotepad;
    private widget.Button BtnPastePemeriksaan;
    private widget.Button BtnPastePengobatan;
    private widget.Button BtnPasteRencana;
    private widget.Button BtnPemeriksaan;
    private widget.Button BtnPengobatan;
    private widget.Button BtnPrint;
    private widget.Button BtnRencana;
    private widget.Button BtnResep;
    private widget.Button BtnRestor;
    private widget.Button BtnRiwPenyakit;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAccor;
    public widget.CekBox ChkAsma;
    public widget.CekBox ChkDiabetes;
    public widget.CekBox ChkGinjal;
    public widget.CekBox ChkHati;
    public widget.CekBox ChkHematoma;
    public widget.CekBox ChkHipertensi;
    public widget.CekBox ChkIkterus;
    public widget.CekBox ChkJantung;
    public widget.CekBox ChkKanker;
    public widget.CekBox ChkKejang;
    public widget.CekBox ChkKutis;
    public widget.CekBox ChkLain;
    public widget.CekBox ChkMarmorata;
    public widget.CekBox ChkPMS;
    public widget.CekBox ChkPerdarahan;
    public widget.CekBox ChkPerdarahanKulit;
    public widget.CekBox ChkSianosis;
    public widget.CekBox ChkSklerema;
    public widget.CekBox ChkStroke;
    public widget.CekBox ChkTB;
    public widget.CekBox ChkTurgor;
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
    private widget.TextBox TKesadaran;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TRuangan;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tanus;
    private widget.TextBox Tauskultasi_jantung;
    private widget.TextBox Tauskultasi_paru;
    private widget.TextBox Tauskultasi_perut;
    private widget.TextBox Tbb;
    private widget.TextBox TbbPersen;
    private widget.TextBox TbbpbPersen;
    private widget.TextBox Tbentuk;
    private widget.TextBox Tbentuk_dada;
    private widget.TextArea Tdiagnosa;
    private widget.TextArea Tdiet;
    private widget.TextBox Tfaring;
    private widget.TextBox Tgcse;
    private widget.TextBox Tgcsm;
    private widget.TextBox Tgcsv;
    private widget.TextBox Tgenitalia;
    private widget.Tanggal TglAsesmen;
    private widget.TextBox TglLahir;
    private widget.TextArea Thasil;
    private widget.TextBox Thidung;
    private widget.TextBox Tikterus;
    private widget.TextBox Tinspeksi_jantung;
    private widget.TextBox Tinspeksi_paru;
    private widget.TextBox Tinspeksi_perut;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tkalimat_lain;
    private widget.TextBox Tkdkamar;
    private widget.TextBox Tkeadaan_umum;
    private widget.TextBox Tkeluhan;
    private widget.TextBox Tkualitas;
    private widget.TextBox TlainyaKulit;
    private widget.TextBox Tleher;
    private widget.TextBox Tlidah;
    private widget.TextBox Tlk;
    private widget.TextBox Tlla;
    private widget.TextBox Tmata;
    private widget.TextBox Tmulut;
    private widget.TextBox Tnadi;
    private widget.TextBox Tnapas;
    private widget.TextBox Tneurologis;
    private widget.TextBox Tpalpasi_jantung;
    private widget.TextBox Tpalpasi_paru;
    private widget.TextBox Tpalpasi_perut;
    private widget.TextBox Tpbtb;
    private widget.TextBox TpbtbPersen;
    private widget.TextArea Tpemeriksaan_penunjang;
    private widget.TextArea Tpengobatan;
    private widget.TextBox Tperkusi_jantung;
    private widget.TextBox Tperkusi_paru;
    private widget.TextBox Tperkusi_perut;
    private widget.TextBox Trambut;
    private widget.TextArea Trencana;
    private widget.TextBox Tretraksi_dada;
    private widget.TextArea Triw_penyakit_sekarang;
    private widget.TextBox Tsuhu;
    private widget.TextBox Tsusunan;
    private widget.TextBox Ttanda;
    private widget.TextBox Ttelinga;
    private widget.TextArea Ttemplate;
    private widget.TextBox Ttensi;
    private widget.TextBox Tumum;
    private javax.swing.JDialog WindowRiwayat;
    private javax.swing.JDialog WindowTemplate;
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
    private widget.Label jLabel13;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
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
    private widget.TextBox kddpjp;
    private widget.Label label100;
    private widget.Label label101;
    private widget.Label label102;
    private widget.Label label103;
    private widget.Label label104;
    private widget.Label label105;
    private widget.Label label106;
    private widget.Label label107;
    private widget.Label label108;
    private widget.Label label109;
    private widget.Label label11;
    private widget.Label label110;
    private widget.Label label111;
    private widget.Label label112;
    private widget.Label label113;
    private widget.Label label114;
    private widget.Label label115;
    private widget.Label label116;
    private widget.Label label117;
    private widget.Label label118;
    private widget.Label label119;
    private widget.Label label120;
    private widget.Label label121;
    private widget.Label label122;
    private widget.Label label123;
    private widget.Label label124;
    private widget.Label label125;
    private widget.Label label126;
    private widget.Label label127;
    private widget.Label label128;
    private widget.Label label129;
    private widget.Label label130;
    private widget.Label label131;
    private widget.Label label132;
    private widget.Label label133;
    private widget.Label label134;
    private widget.Label label135;
    private widget.Label label136;
    private widget.Label label137;
    private widget.Label label138;
    private widget.Label label139;
    private widget.Label label140;
    private widget.Label label141;
    private widget.Label label142;
    private widget.Label label143;
    private widget.Label label144;
    private widget.Label label145;
    private widget.Label label146;
    private widget.Label label147;
    private widget.Label label148;
    private widget.Label label149;
    private widget.Label label15;
    private widget.Label label150;
    private widget.Label label151;
    private widget.Label label152;
    private widget.Label label153;
    private widget.Label label154;
    private widget.Label label155;
    private widget.Label label156;
    private widget.Label label157;
    private widget.Label label19;
    private widget.Label label36;
    private widget.Label label37;
    private widget.Label label38;
    private widget.Label label39;
    private widget.Label label40;
    private widget.Label label43;
    private widget.Label label55;
    private widget.Label label84;
    private widget.Label label93;
    private widget.Label label94;
    private widget.Label label95;
    private widget.Label label96;
    private widget.Label label97;
    private widget.Label label98;
    private widget.Label label99;
    private widget.TextBox nmdpjp;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.ScrollPane scrollPane19;
    private widget.ScrollPane scrollPane20;
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
                    + "date_format(p.tgl_lahir,'%d-%m-%Y') tglLhr, pg.nama nm_dpjp, date_format(am.tgl_asesmen,'%d-%m-%Y %H:%i') tglases, "
                    + "am.* FROM reg_periksa rp inner join asesmen_medik_anak_ranap am on am.no_rawat=rp.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "left join pegawai pg on pg.nik=am.nip_dpjp WHERE "
                    + "date(am.tgl_asesmen) BETWEEN ? AND ? AND rp.no_rawat LIKE ? OR "
                    + "date(am.tgl_asesmen) BETWEEN ? AND ? AND p.no_rkm_medis LIKE ? OR "
                    + "date(am.tgl_asesmen) BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                    + "date(am.tgl_asesmen) BETWEEN ? AND ? AND pg.nama LIKE ? ORDER BY am.tgl_asesmen");
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
                        rs.getString("jk"),
                        rs.getString("tglLhr"),
                        rs.getString("tglases"),
                        rs.getString("nm_dpjp"),
                        Sequel.cariIsi("SELECT b.nm_bangsal FROM bangsal b INNER JOIN kamar k ON k.kd_bangsal=b.kd_bangsal WHERE k.kd_kamar='" + rs.getString("kd_kamar") + "'"),
                        rs.getString("kd_kamar"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("keluhan_utama"),
                        rs.getString("riw_penyakit_sekarang"),
                        rs.getString("hipertensi"),
                        rs.getString("dm"),
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
                        rs.getString("kalimat_lainlain"),
                        rs.getString("keadaan_umum"),
                        rs.getString("kesadaran"),
                        rs.getString("gcs_e"),
                        rs.getString("gcs_m"),
                        rs.getString("gcs_v"),
                        rs.getString("tensi"),
                        rs.getString("suhu"),
                        rs.getString("nadi"),
                        rs.getString("kualitas"),
                        rs.getString("napas"),
                        rs.getString("bb"),
                        rs.getString("bb_persen"),
                        rs.getString("bbpbtb_persen"),
                        rs.getString("pbtb"),
                        rs.getString("pbtb_persen"),
                        rs.getString("lla"),
                        rs.getString("lk"),
                        rs.getString("turgor"),
                        rs.getString("sianosis"),
                        rs.getString("perdarahan_kulit"),
                        rs.getString("ikterus"),
                        rs.getString("kalimat_ikterus"),
                        rs.getString("hematoma"),
                        rs.getString("sklerema"),
                        rs.getString("kutis"),
                        rs.getString("marmorata"),
                        rs.getString("kalimat_lainya_kulit"),
                        rs.getString("kepala_bentuk"),
                        rs.getString("kepala_rambut"),
                        rs.getString("kepala_mata"),
                        rs.getString("kepala_telinga"),
                        rs.getString("kepala_hidung"),
                        rs.getString("kepala_mulut"),
                        rs.getString("kepala_lidah"),
                        rs.getString("kepala_faring"),
                        rs.getString("leher"),
                        rs.getString("dada_bentuk"),
                        rs.getString("dada_retraksi"),
                        rs.getString("jantung_inspeksi"),
                        rs.getString("jantung_palpasi"),
                        rs.getString("jantung_perkusi"),
                        rs.getString("jantung_auskultasi"),
                        rs.getString("paru_inspeksi"),
                        rs.getString("paru_palpasi"),
                        rs.getString("paru_perkusi"),
                        rs.getString("paru_auskultasi"),
                        rs.getString("perut_inspeksi"),
                        rs.getString("perut_palpasi"),
                        rs.getString("perut_perkusi"),
                        rs.getString("perut_auskultasi"),
                        rs.getString("ekstremitas_umum"),
                        rs.getString("ekstremitas_neurologis"),
                        rs.getString("susunan_saraf_pusat"),
                        rs.getString("tanda_meningen"),
                        rs.getString("genitalia"),
                        rs.getString("anus"),
                        rs.getString("pemeriksaan_penunjang_pre_ranap"),
                        rs.getString("diagnosa_kerja_diagnosa_banding"),
                        rs.getString("pengobatan"),
                        rs.getString("diet"),
                        rs.getString("rencana"),
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
        Tkeluhan.setText("");
        Triw_penyakit_sekarang.setText("");
        
        ChkHipertensi.setSelected(false);
        ChkDiabetes.setSelected(false);
        ChkJantung.setSelected(false);
        ChkStroke.setSelected(false);
        ChkAsma.setSelected(false);
        ChkKejang.setSelected(false);
        ChkHati.setSelected(false);
        ChkKanker.setSelected(false);
        ChkTB.setSelected(false);
        ChkPMS.setSelected(false);
        ChkPerdarahan.setSelected(false);
        ChkGinjal.setSelected(false);
        ChkLain.setSelected(false);
        Tkalimat_lain.setText("");
        Tkalimat_lain.setEnabled(false);
        
        Tkeadaan_umum.setText("");
        TKesadaran.setText("");
        Tgcse.setText("");
        Tgcsm.setText("");
        Tgcsv.setText("");
        Ttensi.setText("");
        Tsuhu.setText("");
        Tnadi.setText("");
        Tkualitas.setText("");
        Tnapas.setText("");
        Tbb.setText("");
        TbbPersen.setText("");
        TbbpbPersen.setText("");
        Tpbtb.setText("");
        TpbtbPersen.setText("");
        Tlla.setText("");
        Tlk.setText("");
        
        ChkTurgor.setSelected(false);
        ChkSianosis.setSelected(false);
        ChkPerdarahanKulit.setSelected(false);
        ChkIkterus.setSelected(false);
        Tikterus.setText("");
        Tikterus.setEnabled(false);
        ChkHematoma.setSelected(false);
        ChkSklerema.setSelected(false);
        ChkKutis.setSelected(false);
        ChkMarmorata.setSelected(false);
        TlainyaKulit.setText("");
        
        Tbentuk.setText("");
        Trambut.setText("");
        Tmata.setText("");
        Ttelinga.setText("");
        Thidung.setText("");
        Tmulut.setText("");
        Tlidah.setText("");
        Tfaring.setText("");
        Tleher.setText("");
        Tbentuk_dada.setText("");
        Tretraksi_dada.setText("");
        
        Tinspeksi_jantung.setText("");
        Tpalpasi_jantung.setText("");
        Tperkusi_jantung.setText("");
        Tauskultasi_jantung.setText("");
        
        Tinspeksi_paru.setText("");
        Tpalpasi_paru.setText("");
        Tperkusi_paru.setText("");
        Tauskultasi_paru.setText("");
        
        Tinspeksi_perut.setText("");
        Tpalpasi_perut.setText("");
        Tperkusi_perut.setText("");
        Tauskultasi_perut.setText("");
        
        Tumum.setText("");
        Tneurologis.setText("");
        Tsusunan.setText("");
        Ttanda.setText("");
        Tgenitalia.setText("");
        Tanus.setText("");
        Tpemeriksaan_penunjang.setText("");
        Tdiagnosa.setText("");
        Tpengobatan.setText("");
        Tdiet.setText("");
        Trencana.setText("");
        user = "";
    }

    private void getData() {
        hipertensi = "";
        diabetes = "";
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
        lainlain = "";
        turgor = "";
        sianosis = "";
        perdarahanKulit = "";
        ikterus = "";
        hematoma = "";
        sklerema = "";
        kutis = "";
        marmorata = "";
        kodekamar = "";
        dataKonfirmasi = "";
        user = "";
        
        if (tbPenilaian.getSelectedRow() != -1) {
            TNoRw.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 0).toString());
            TNoRM.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 1).toString());
            TPasien.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 2).toString());
            Jk.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 3).toString());
            TglLahir.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 4).toString());
            Valid.SetTgl2(TglAsesmen, tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 9).toString());
            kddpjp.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 87).toString());
            nmdpjp.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 6).toString());
            TRuangan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 7).toString());
            Tkdkamar.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 8).toString());            
            Tkeluhan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 10).toString());
            Triw_penyakit_sekarang.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 11).toString());
            hipertensi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 12).toString();
            diabetes = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 13).toString();
            jantung = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 14).toString();
            strok = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 15).toString();
            asma = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 16).toString();
            kejang = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 17).toString();
            hati = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 18).toString();
            kanker = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 19).toString();
            tb = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 20).toString();
            pms = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 21).toString();
            perdarahan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 22).toString();
            ginjal = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 23).toString();
            lainlain = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 24).toString();
            Tkalimat_lain.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 25).toString());
            Tkeadaan_umum.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 26).toString());
            TKesadaran.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 27).toString());
            Tgcse.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 28).toString());
            Tgcsm.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 29).toString());
            Tgcsv.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 30).toString());
            Ttensi.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 31).toString());
            Tsuhu.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 32).toString());
            Tnadi.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 33).toString());
            Tkualitas.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 34).toString());
            Tnapas.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 35).toString());
            Tbb.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 36).toString());
            TbbPersen.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 37).toString());
            TbbpbPersen.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 38).toString());
            Tpbtb.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 39).toString());
            TpbtbPersen.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 40).toString());
            Tlla.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 41).toString());
            Tlk.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 42).toString());
            turgor = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 43).toString();
            sianosis = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 44).toString();
            perdarahanKulit = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 45).toString();
            ikterus = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 46).toString();
            Tikterus.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 47).toString());
            hematoma = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 48).toString();
            sklerema = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 49).toString();
            kutis = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 50).toString();
            marmorata = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 51).toString();
            TlainyaKulit.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 52).toString());
            Tbentuk.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 53).toString());
            Trambut.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 54).toString());
            Tmata.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 55).toString());
            Ttelinga.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 56).toString());
            Thidung.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 57).toString());
            Tmulut.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 58).toString());
            Tlidah.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 59).toString());
            Tfaring.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 60).toString());
            Tleher.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 61).toString());
            Tbentuk_dada.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 62).toString());
            Tretraksi_dada.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 63).toString());
            Tinspeksi_jantung.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 64).toString());
            Tpalpasi_jantung.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 65).toString());
            Tperkusi_jantung.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 66).toString());
            Tauskultasi_jantung.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 67).toString());
            Tinspeksi_paru.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 68).toString());
            Tpalpasi_paru.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 69).toString());
            Tperkusi_paru.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 70).toString());
            Tauskultasi_paru.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 71).toString());
            Tinspeksi_perut.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 72).toString());
            Tpalpasi_perut.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 73).toString());
            Tperkusi_perut.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 74).toString());
            Tauskultasi_perut.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 75).toString());
            Tumum.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 76).toString());
            Tneurologis.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 77).toString());
            Tsusunan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 78).toString());
            Ttanda.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 79).toString());
            Tgenitalia.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 80).toString());
            Tanus.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 81).toString());
            Tpemeriksaan_penunjang.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 82).toString());
            Tdiagnosa.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 83).toString());
            Tpengobatan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 84).toString());
            Tdiet.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 85).toString());
            Trencana.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 86).toString());
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
        BtnSimpan.setEnabled(akses.getasesmen_medik_anak_ranap());
        BtnHapus.setEnabled(akses.getasesmen_medik_anak_ranap());
        BtnEdit.setEnabled(akses.getasesmen_medik_anak_ranap());
        BtnPrint.setEnabled(akses.getasesmen_medik_anak_ranap());
        MnRiwayatData.setEnabled(akses.getadmin());
        
        if (akses.getjml2() >= 1) {            
//            BtnDpjp.setEnabled(false);
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
            if (Sequel.mengedittf("asesmen_medik_anak_ranap", "no_rawat=?", "kd_kamar=?, tgl_asesmen=?, keluhan_utama=?, riw_penyakit_sekarang=?, "
                    + "hipertensi=?, dm=?, jantung=?, stroke=?, asma=?, kejang=?, hati=?, kanker=?, tb=?, pms=?, perdarahan=?, ginjal=?, lain_lain=?, "
                    + "kalimat_lainlain=?, keadaan_umum=?, kesadaran=?, gcs_e=?, gcs_m=?, gcs_v=?, tensi=?, suhu=?, nadi=?, kualitas=?, napas=?, bb=?, "
                    + "bb_persen=?, bbpbtb_persen=?, pbtb=?, pbtb_persen=?, lla=?, lk=?, turgor=?, sianosis=?, perdarahan_kulit=?, ikterus=?, kalimat_ikterus=?, "
                    + "hematoma=?, sklerema=?, kutis=?, marmorata=?, kalimat_lainya_kulit=?, kepala_bentuk=?, kepala_rambut=?, kepala_mata=?, kepala_telinga=?, "
                    + "kepala_hidung=?, kepala_mulut=?, kepala_lidah=?, kepala_faring=?, leher=?, dada_bentuk=?, dada_retraksi=?, jantung_inspeksi=?, "
                    + "jantung_palpasi=?, jantung_perkusi=?, jantung_auskultasi=?, paru_inspeksi=?, paru_palpasi=?, paru_perkusi=?, paru_auskultasi=?, "
                    + "perut_inspeksi=?, perut_palpasi=?, perut_perkusi=?, perut_auskultasi=?, ekstremitas_umum=?, ekstremitas_neurologis=?, "
                    + "susunan_saraf_pusat=?, tanda_meningen=?, genitalia=?, anus=?, pemeriksaan_penunjang_pre_ranap=?, diagnosa_kerja_diagnosa_banding=?, "
                    + "pengobatan=?, diet=?, rencana=?, nip_dpjp=?", 81, new String[]{
                        Tkdkamar.getText(), Valid.SetTgl(TglAsesmen.getSelectedItem() + "") + " " + TglAsesmen.getSelectedItem().toString().substring(11, 19), Tkeluhan.getText(), Valid.mysql_real_escape_stringERM(Triw_penyakit_sekarang.getText()), 
                        hipertensi, diabetes, jantung, strok, asma, kejang, hati, kanker, tb, pms, perdarahan, ginjal, lainlain, Tkalimat_lain.getText(), Tkeadaan_umum.getText(), TKesadaran.getText(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(),
                        Ttensi.getText(), Tsuhu.getText(), Tnadi.getText(), Tkualitas.getText(), Tnapas.getText(), Tbb.getText(), TbbPersen.getText(), TbbpbPersen.getText(), Tpbtb.getText(), TpbtbPersen.getText(), Tlla.getText(), Tlk.getText(), 
                        turgor, sianosis, perdarahanKulit, ikterus, Tikterus.getText(), hematoma, sklerema, kutis, marmorata, TlainyaKulit.getText(), Tbentuk.getText(), Trambut.getText(), Tmata.getText(), Ttelinga.getText(), Thidung.getText(),
                        Tmulut.getText(), Tlidah.getText(), Tfaring.getText(), Tleher.getText(), Tbentuk_dada.getText(), Tretraksi_dada.getText(), Tinspeksi_jantung.getText(), Tpalpasi_jantung.getText(), Tperkusi_jantung.getText(), Tauskultasi_jantung.getText(), 
                        Tinspeksi_paru.getText(), Tpalpasi_paru.getText(), Tperkusi_paru.getText(), Tauskultasi_paru.getText(), Tinspeksi_perut.getText(), Tpalpasi_perut.getText(), Tperkusi_perut.getText(), Tauskultasi_perut.getText(),
                        Tumum.getText(), Tneurologis.getText(), Tsusunan.getText(), Ttanda.getText(), Tgenitalia.getText(), Tanus.getText(), Valid.mysql_real_escape_stringERM(Tpemeriksaan_penunjang.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa.getText()), 
                        Valid.mysql_real_escape_stringERM(Tpengobatan.getText()), Valid.mysql_real_escape_stringERM(Tdiet.getText()), Valid.mysql_real_escape_stringERM(Trencana.getText()), kddpjp.getText(),
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
            if (Sequel.menyimpantf("asesmen_medik_anak_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 82, new String[]{
                        TNoRw.getText(), Tkdkamar.getText(), Valid.SetTgl(TglAsesmen.getSelectedItem() + "") + " " + TglAsesmen.getSelectedItem().toString().substring(11, 19), Tkeluhan.getText(), Valid.mysql_real_escape_stringERM(Triw_penyakit_sekarang.getText()), 
                        hipertensi, diabetes, jantung, strok, asma, kejang, hati, kanker, tb, pms, perdarahan, ginjal, lainlain, Tkalimat_lain.getText(), Tkeadaan_umum.getText(), TKesadaran.getText(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(),
                        Ttensi.getText(), Tsuhu.getText(), Tnadi.getText(), Tkualitas.getText(), Tnapas.getText(), Tbb.getText(), TbbPersen.getText(), TbbpbPersen.getText(), Tpbtb.getText(), TpbtbPersen.getText(), Tlla.getText(), Tlk.getText(), 
                        turgor, sianosis, perdarahanKulit, ikterus, Tikterus.getText(), hematoma, sklerema, kutis, marmorata, TlainyaKulit.getText(), Tbentuk.getText(), Trambut.getText(), Tmata.getText(), Ttelinga.getText(), Thidung.getText(),
                        Tmulut.getText(), Tlidah.getText(), Tfaring.getText(), Tleher.getText(), Tbentuk_dada.getText(), Tretraksi_dada.getText(), Tinspeksi_jantung.getText(), Tpalpasi_jantung.getText(), Tperkusi_jantung.getText(), Tauskultasi_jantung.getText(), 
                        Tinspeksi_paru.getText(), Tpalpasi_paru.getText(), Tperkusi_paru.getText(), Tauskultasi_paru.getText(), Tinspeksi_perut.getText(), Tpalpasi_perut.getText(), Tperkusi_perut.getText(), Tauskultasi_perut.getText(),
                        Tumum.getText(), Tneurologis.getText(), Tsusunan.getText(), Ttanda.getText(), Tgenitalia.getText(), Tanus.getText(), Valid.mysql_real_escape_stringERM(Tpemeriksaan_penunjang.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa.getText()), 
                        Valid.mysql_real_escape_stringERM(Tpengobatan.getText()), Valid.mysql_real_escape_stringERM(Tdiet.getText()), Valid.mysql_real_escape_stringERM(Trencana.getText()), kddpjp.getText(), Sequel.cariIsi("select now()")
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
        if (ChkHipertensi.isSelected() == true) {
            hipertensi = "ya";
        } else {
            hipertensi = "tidak";
        }
        
        if (ChkDiabetes.isSelected() == true) {
            diabetes = "ya";
        } else {
            diabetes = "tidak";
        }
        
        if (ChkJantung.isSelected() == true) {
            jantung = "ya";
        } else {
            jantung = "tidak";
        }
        
        if (ChkStroke.isSelected() == true) {
            strok = "ya";
        } else {
            strok = "tidak";
        }
        
        if (ChkAsma.isSelected() == true) {
            asma = "ya";
        } else {
            asma = "tidak";
        }
        
        if (ChkKejang.isSelected() == true) {
            kejang = "ya";
        } else {
            kejang = "tidak";
        }
        
        if (ChkHati.isSelected() == true) {
            hati = "ya";
        } else {
            hati = "tidak";
        }
        
        if (ChkKanker.isSelected() == true) {
            kanker = "ya";
        } else {
            kanker = "tidak";
        }
        
        if (ChkTB.isSelected() == true) {
            tb = "ya";
        } else {
            tb = "tidak";
        }
        
        if (ChkPMS.isSelected() == true) {
            pms = "ya";
        } else {
            pms = "tidak";
        }
        
        if (ChkPerdarahan.isSelected() == true) {
            perdarahan = "ya";
        } else {
            perdarahan = "tidak";
        }
        
        if (ChkGinjal.isSelected() == true) {
            ginjal = "ya";
        } else {
            ginjal = "tidak";
        }
        
        if (ChkLain.isSelected() == true) {
            lainlain = "ya";
        } else {
            lainlain = "tidak";
        }
        
        if (ChkTurgor.isSelected() == true) {
            turgor = "ya";
        } else {
            turgor = "tidak";
        }
        
        if (ChkSianosis.isSelected() == true) {
            sianosis = "ya";
        } else {
            sianosis = "tidak";
        }
        
        if (ChkPerdarahanKulit.isSelected() == true) {
            perdarahanKulit = "ya";
        } else {
            perdarahanKulit = "tidak";
        }
        
        if (ChkIkterus.isSelected() == true) {
            ikterus = "ya";
        } else {
            ikterus = "tidak";
        }
        
        if (ChkHematoma.isSelected() == true) {
            hematoma = "ya";
        } else {
            hematoma = "tidak";
        }
        
        if (ChkSklerema.isSelected() == true) {
            sklerema = "ya";
        } else {
            sklerema = "tidak";
        }
        
        if (ChkKutis.isSelected() == true) {
            kutis = "ya";
        } else {
            kutis = "tidak";
        }
        
        if (ChkMarmorata.isSelected() == true) {
            marmorata = "ya";
        } else {
            marmorata = "tidak";
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
            if (Sequel.queryu2tf("delete from asesmen_medik_anak_ranap where no_rawat=?", 1, new String[]{
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
        if (hipertensi.equals("ya")) {
            ChkHipertensi.setSelected(true);
        } else {
            ChkHipertensi.setSelected(false);
        }
        
        if (diabetes.equals("ya")) {
            ChkDiabetes.setSelected(true);
        } else {
            ChkDiabetes.setSelected(false);
        }
        
        if (jantung.equals("ya")) {
            ChkJantung.setSelected(true);
        } else {
            ChkJantung.setSelected(false);
        }
        
        if (strok.equals("ya")) {
            ChkStroke.setSelected(true);
        } else {
            ChkStroke.setSelected(false);
        }
        
        if (asma.equals("ya")) {
            ChkAsma.setSelected(true);
        } else {
            ChkAsma.setSelected(false);
        }
        
        if (kejang.equals("ya")) {
            ChkKejang.setSelected(true);
        } else {
            ChkKejang.setSelected(false);
        }
        
        if (hati.equals("ya")) {
            ChkHati.setSelected(true);
        } else {
            ChkHati.setSelected(false);
        }
        
        if (kanker.equals("ya")) {
            ChkKanker.setSelected(true);
        } else {
            ChkKanker.setSelected(false);
        }
        
        if (tb.equals("ya")) {
            ChkTB.setSelected(true);
        } else {
            ChkTB.setSelected(false);
        }
        
        if (pms.equals("ya")) {
            ChkPMS.setSelected(true);
        } else {
            ChkPMS.setSelected(false);
        }
        
        if (perdarahan.equals("ya")) {
            ChkPerdarahan.setSelected(true);
        } else {
            ChkPerdarahan.setSelected(false);
        }
        
        if (ginjal.equals("ya")) {
            ChkGinjal.setSelected(true);
        } else {
            ChkGinjal.setSelected(false);
        }
        
        if (lainlain.equals("ya")) {
            ChkLain.setSelected(true);
            Tkalimat_lain.setEnabled(true);
        } else {
            ChkLain.setSelected(false);
            Tkalimat_lain.setEnabled(false);
        }
        
        if (turgor.equals("ya")) {
            ChkTurgor.setSelected(true);
        } else {
            ChkTurgor.setSelected(false);
        }
        
        if (sianosis.equals("ya")) {
            ChkSianosis.setSelected(true);
        } else {
            ChkSianosis.setSelected(false);
        }
        
        if (perdarahanKulit.equals("ya")) {
            ChkPerdarahanKulit.setSelected(true);
        } else {
            ChkPerdarahanKulit.setSelected(false);
        }
        
        if (ikterus.equals("ya")) {
            ChkIkterus.setSelected(true);
            Tikterus.setEnabled(true);
        } else {
            ChkIkterus.setSelected(false);
            Tikterus.setEnabled(false);
        }
        
        if (hematoma.equals("ya")) {
            ChkHematoma.setSelected(true);
        } else {
            ChkHematoma.setSelected(false);
        }
        
        if (sklerema.equals("ya")) {
            ChkSklerema.setSelected(true);
        } else {
            ChkSklerema.setSelected(false);
        }
        
        if (kutis.equals("ya")) {
            ChkKutis.setSelected(true);
        } else {
            ChkKutis.setSelected(false);
        }
        
        if (marmorata.equals("ya")) {
            ChkMarmorata.setSelected(true);
        } else {
            ChkMarmorata.setSelected(false);
        }
    }
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode1);
        try {
            if (pilihan == 1) {
                ps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_medik_anak_ranap pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.riw_penyakit_sekarang<>'' and p.no_rkm_medis like ? OR "
                        + "pa.riw_penyakit_sekarang<>'' and p.nm_pasien like ? OR "
                        + "pa.riw_penyakit_sekarang<>'' and pa.riw_penyakit_sekarang like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } else if (pilihan == 2) {
                ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_medik_anak_ranap pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.pemeriksaan_penunjang_pre_ranap<>'' and p.no_rkm_medis like ? OR "
                        + "pa.pemeriksaan_penunjang_pre_ranap<>'' and p.nm_pasien like ? OR "
                        + "pa.pemeriksaan_penunjang_pre_ranap<>'' and pa.pemeriksaan_penunjang_pre_ranap like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } else if (pilihan == 3) {
                ps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_medik_anak_ranap pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.diagnosa_kerja_diagnosa_banding<>'' and p.no_rkm_medis like ? OR "
                        + "pa.diagnosa_kerja_diagnosa_banding<>'' and p.nm_pasien like ? OR "
                        + "pa.diagnosa_kerja_diagnosa_banding<>'' and pa.diagnosa_kerja_diagnosa_banding like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } else if (pilihan == 4) {
                ps4 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_medik_anak_ranap pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.pengobatan<>'' and p.no_rkm_medis like ? OR "
                        + "pa.pengobatan<>'' and p.nm_pasien like ? OR "
                        + "pa.pengobatan<>'' and pa.pengobatan like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } else if (pilihan == 5) {
                ps5 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_medik_anak_ranap pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.diet<>'' and p.no_rkm_medis like ? OR "
                        + "pa.diet<>'' and p.nm_pasien like ? OR "
                        + "pa.diet<>'' and pa.diet like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } else if (pilihan == 6) {
                ps6 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_medik_anak_ranap pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.rencana<>'' and p.no_rkm_medis like ? OR "
                        + "pa.rencana<>'' and p.nm_pasien like ? OR "
                        + "pa.rencana<>'' and pa.rencana like ? ORDER BY pa.tgl_asesmen desc limit 20");
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
                            rs2.getString("pemeriksaan_penunjang_pre_ranap")
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
                            rs3.getString("diagnosa_kerja_diagnosa_banding")
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
                            rs4.getString("pengobatan")
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
                            rs5.getString("diet")
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
                            rs6.getString("rencana")
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
            Tpemeriksaan_penunjang.setText(Ttemplate.getText());
        } else if (pilihan == 3) {
            Tdiagnosa.setText(Ttemplate.getText());
        } else if (pilihan == 4) {
            Tpengobatan.setText(Ttemplate.getText());
        } else if (pilihan == 5) {
            Tdiet.setText(Ttemplate.getText());
        } else if (pilihan == 6) {
            Trencana.setText(Ttemplate.getText());
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
            if (Sequel.menyimpantf("asesmen_medik_anak_ranap_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 85, new String[]{
                        TNoRw.getText(), Tkdkamar.getText(), Valid.SetTgl(TglAsesmen.getSelectedItem() + "") + " " + TglAsesmen.getSelectedItem().toString().substring(11, 19), Tkeluhan.getText(), Valid.mysql_real_escape_stringERM(Triw_penyakit_sekarang.getText()), 
                        hipertensi, diabetes, jantung, strok, asma, kejang, hati, kanker, tb, pms, perdarahan, ginjal, lainlain, Tkalimat_lain.getText(), Tkeadaan_umum.getText(), TKesadaran.getText(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(),
                        Ttensi.getText(), Tsuhu.getText(), Tnadi.getText(), Tkualitas.getText(), Tnapas.getText(), Tbb.getText(), TbbPersen.getText(), TbbpbPersen.getText(), Tpbtb.getText(), TpbtbPersen.getText(), Tlla.getText(), Tlk.getText(), 
                        turgor, sianosis, perdarahanKulit, ikterus, Tikterus.getText(), hematoma, sklerema, kutis, marmorata, TlainyaKulit.getText(), Tbentuk.getText(), Trambut.getText(), Tmata.getText(), Ttelinga.getText(), Thidung.getText(),
                        Tmulut.getText(), Tlidah.getText(), Tfaring.getText(), Tleher.getText(), Tbentuk_dada.getText(), Tretraksi_dada.getText(), Tinspeksi_jantung.getText(), Tpalpasi_jantung.getText(), Tperkusi_jantung.getText(), Tauskultasi_jantung.getText(), 
                        Tinspeksi_paru.getText(), Tpalpasi_paru.getText(), Tperkusi_paru.getText(), Tauskultasi_paru.getText(), Tinspeksi_perut.getText(), Tpalpasi_perut.getText(), Tperkusi_perut.getText(), Tauskultasi_perut.getText(),
                        Tumum.getText(), Tneurologis.getText(), Tsusunan.getText(), Ttanda.getText(), Tgenitalia.getText(), Tanus.getText(), Valid.mysql_real_escape_stringERM(Tpemeriksaan_penunjang.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa.getText()), 
                        Valid.mysql_real_escape_stringERM(Tpengobatan.getText()), Valid.mysql_real_escape_stringERM(Tdiet.getText()), Valid.mysql_real_escape_stringERM(Trencana.getText()), kddpjp.getText(), tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 88).toString(),
                        "hapus", user, Sequel.cariIsi("select now()")
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
            if (Sequel.menyimpantf("asesmen_medik_anak_ranap_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 85, new String[]{
                        TNoRw.getText(), Tkdkamar.getText(), Valid.SetTgl(TglAsesmen.getSelectedItem() + "") + " " + TglAsesmen.getSelectedItem().toString().substring(11, 19), Tkeluhan.getText(), Valid.mysql_real_escape_stringERM(Triw_penyakit_sekarang.getText()),
                        hipertensi, diabetes, jantung, strok, asma, kejang, hati, kanker, tb, pms, perdarahan, ginjal, lainlain, Tkalimat_lain.getText(), Tkeadaan_umum.getText(), TKesadaran.getText(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(),
                        Ttensi.getText(), Tsuhu.getText(), Tnadi.getText(), Tkualitas.getText(), Tnapas.getText(), Tbb.getText(), TbbPersen.getText(), TbbpbPersen.getText(), Tpbtb.getText(), TpbtbPersen.getText(), Tlla.getText(), Tlk.getText(),
                        turgor, sianosis, perdarahanKulit, ikterus, Tikterus.getText(), hematoma, sklerema, kutis, marmorata, TlainyaKulit.getText(), Tbentuk.getText(), Trambut.getText(), Tmata.getText(), Ttelinga.getText(), Thidung.getText(),
                        Tmulut.getText(), Tlidah.getText(), Tfaring.getText(), Tleher.getText(), Tbentuk_dada.getText(), Tretraksi_dada.getText(), Tinspeksi_jantung.getText(), Tpalpasi_jantung.getText(), Tperkusi_jantung.getText(), Tauskultasi_jantung.getText(),
                        Tinspeksi_paru.getText(), Tpalpasi_paru.getText(), Tperkusi_paru.getText(), Tauskultasi_paru.getText(), Tinspeksi_perut.getText(), Tpalpasi_perut.getText(), Tperkusi_perut.getText(), Tauskultasi_perut.getText(),
                        Tumum.getText(), Tneurologis.getText(), Tsusunan.getText(), Ttanda.getText(), Tgenitalia.getText(), Tanus.getText(), Valid.mysql_real_escape_stringERM(Tpemeriksaan_penunjang.getText()), Valid.mysql_real_escape_stringERM(Tdiagnosa.getText()),
                        Valid.mysql_real_escape_stringERM(Tpengobatan.getText()), Valid.mysql_real_escape_stringERM(Tdiet.getText()), Valid.mysql_real_escape_stringERM(Trencana.getText()), kddpjp.getText(), tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 88).toString(),
                        "ganti", user, Sequel.cariIsi("select now()")
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
                    + "a.tgl_asesmen, a.waktu_eksekusi, upper(concat('DI',a.status_data)) sttsdata FROM asesmen_medik_anak_ranap_histori a "
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
            ps8 = koneksi.prepareStatement("select * from asesmen_medik_anak_ranap_histori where "
                    + "waktu_eksekusi='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString() + "'");
            try {
                rs8 = ps8.executeQuery();
                while (rs8.next()) {
                    try {
                        if (Sequel.menyimpantf("asesmen_medik_anak_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 82, new String[]{
                                    rs8.getString("no_rawat"),
                                    rs8.getString("kd_kamar"),
                                    rs8.getString("tgl_asesmen"),
                                    rs8.getString("keluhan_utama"),
                                    rs8.getString("riw_penyakit_sekarang"),
                                    rs8.getString("hipertensi"),
                                    rs8.getString("dm"),
                                    rs8.getString("jantung"),
                                    rs8.getString("stroke"),
                                    rs8.getString("asma"),
                                    rs8.getString("kejang"),
                                    rs8.getString("hati"),
                                    rs8.getString("kanker"),
                                    rs8.getString("tb"),
                                    rs8.getString("pms"),
                                    rs8.getString("perdarahan"),
                                    rs8.getString("ginjal"),
                                    rs8.getString("lain_lain"),
                                    rs8.getString("kalimat_lainlain"),
                                    rs8.getString("keadaan_umum"),
                                    rs8.getString("kesadaran"),
                                    rs8.getString("gcs_e"),
                                    rs8.getString("gcs_m"),
                                    rs8.getString("gcs_v"),
                                    rs8.getString("tensi"),
                                    rs8.getString("suhu"),
                                    rs8.getString("nadi"),
                                    rs8.getString("kualitas"),
                                    rs8.getString("napas"),
                                    rs8.getString("bb"),
                                    rs8.getString("bb_persen"),
                                    rs8.getString("bbpbtb_persen"),
                                    rs8.getString("pbtb"),
                                    rs8.getString("pbtb_persen"),
                                    rs8.getString("lla"),
                                    rs8.getString("lk"),
                                    rs8.getString("turgor"),
                                    rs8.getString("sianosis"),
                                    rs8.getString("perdarahan_kulit"),
                                    rs8.getString("ikterus"),
                                    rs8.getString("kalimat_ikterus"),
                                    rs8.getString("hematoma"),
                                    rs8.getString("sklerema"),
                                    rs8.getString("kutis"),
                                    rs8.getString("marmorata"),
                                    rs8.getString("kalimat_lainya_kulit"),
                                    rs8.getString("kepala_bentuk"),
                                    rs8.getString("kepala_rambut"),
                                    rs8.getString("kepala_mata"),
                                    rs8.getString("kepala_telinga"),
                                    rs8.getString("kepala_hidung"),
                                    rs8.getString("kepala_mulut"),
                                    rs8.getString("kepala_lidah"),
                                    rs8.getString("kepala_faring"),
                                    rs8.getString("leher"),
                                    rs8.getString("dada_bentuk"),
                                    rs8.getString("dada_retraksi"),
                                    rs8.getString("jantung_inspeksi"),
                                    rs8.getString("jantung_palpasi"),
                                    rs8.getString("jantung_perkusi"),
                                    rs8.getString("jantung_auskultasi"),
                                    rs8.getString("paru_inspeksi"),
                                    rs8.getString("paru_palpasi"),
                                    rs8.getString("paru_perkusi"),
                                    rs8.getString("paru_auskultasi"),
                                    rs8.getString("perut_inspeksi"),
                                    rs8.getString("perut_palpasi"),
                                    rs8.getString("perut_perkusi"),
                                    rs8.getString("perut_auskultasi"),
                                    rs8.getString("ekstremitas_umum"),
                                    rs8.getString("ekstremitas_neurologis"),
                                    rs8.getString("susunan_saraf_pusat"),
                                    rs8.getString("tanda_meningen"),
                                    rs8.getString("genitalia"),
                                    rs8.getString("anus"),
                                    rs8.getString("pemeriksaan_penunjang_pre_ranap"),
                                    rs8.getString("diagnosa_kerja_diagnosa_banding"),
                                    rs8.getString("pengobatan"),
                                    rs8.getString("diet"),
                                    rs8.getString("rencana"),
                                    rs8.getString("nip_dpjp"),
                                    rs8.getString("waktu_simpan")
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
        if (Sequel.queryu2tf("delete from asesmen_medik_anak_ranap where no_rawat=?", 1, new String[]{
            tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString()
        }) == true) {
            kembalikanData();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal dikembalikan..!!");
        }
    }
}
