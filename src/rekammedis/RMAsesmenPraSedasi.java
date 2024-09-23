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
import simrskhanza.DlgNotepad;

/**
 *
 * @author perpustakaan
 */
public final class RMAsesmenPraSedasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, pscppt, psrestor;
    private ResultSet rs, rs1, rs2, rscppt, rsrestor;
    private int i = 0, x = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String mal1 = "", mal2 = "", mal3 = "", mal4 = "", sedasi = "", ga = "", regional = "", spinal = "", epidural = "",
            kaudal = "", blok = "", ekg = "", spo2 = "", nibp = "", temp = "", lainlain = "", ranap = "", ralan = "", rakhus = "",
            icu = "", hcu = "", asa1 = "", asa2 = "", asa3 = "", asa4 = "", emer = "", user = "", dataKonfirmasi = "", sttsrawat = "";

    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsesmenPraSedasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Tgl. Puasa", "Jam Puasa", "Tgl. Renc. Ops.", "Jam Renc. Ops.", "Ruang Perawatan", "Diagnosa", "Penyulit",
            "riwayat_alergi_obat", "obat_saat_ini", "riwayat_anastesi", "kepala", "sklera", "conjungtiva", "leher", "paru", "jantung", "abdomen",
            "extremitas", "gcs", "tensi", "rr", "nadi", "suhu", "tb", "bb", "vas", "hilangnya_gigi", "masalah", "leher_pendek", "stroke", "sesak_nafas",
            "buka_mulut", "jarak", "sakit_dada", "denyut", "sedang_hamil", "kejang", "obesitas", "gigi_palsu", "gerakan_leher", "malampati1",
            "malampati2", "malampati3", "malampati4", "pemeriksaan_penunjang", "diagnosa", "sedasi", "obat1", "obat2", "obat3", "ga", "keterangan_ga",
            "regional", "spinal", "epidural", "kaudal", "blok_perifer", "ekg", "spo2", "nibp", "temp", "lain_lain", "ket_lainlain", "rawat_inap", "rawat_jalan",
            "rawat_khusus", "icu", "hcu", "catatan", "asa1", "asa2", "asa3", "asa4", "emergency", "penyulit", "jam_mulai", "tgl_mulai", "jam_rencana",
            "tgl_rencana", "nip_dokter", "waktu_simpan", "nmdokter"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbSedasi.setModel(tabMode);
        tbSedasi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSedasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 86; i++) {
            TableColumn column = tbSedasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(95);
            } else if (i == 7) {
                column.setPreferredWidth(95);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setPreferredWidth(250);
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
            }

        }
        tbSedasi.setDefaultRenderer(Object.class, new WarnaTable()); 
        
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
            "Dilakukan Oleh", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Simpan", "Tgl. Eksekusi", "Status Data", "Waktu Simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayat.setModel(tabMode1);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
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
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(130);
            } 
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());
        
        Tkepala.setDocument(new batasInput((int) 100).getKata(Tkepala));
        Tsklera.setDocument(new batasInput((int) 100).getKata(Tsklera));
        Tconjung.setDocument(new batasInput((int) 100).getKata(Tconjung));
        Tleher.setDocument(new batasInput((int) 100).getKata(Tleher));        
        Tgcs.setDocument(new batasInput((int) 15).getKata(Tgcs));
        Ttd.setDocument(new batasInput((int) 7).getKata(Ttd));
        Trr.setDocument(new batasInput((int) 7).getKata(Trr));
        Tnadi.setDocument(new batasInput((int) 7).getKata(Tnadi));
        Tsuhu.setDocument(new batasInput((int) 7).getKata(Tsuhu));
        Ttb.setDocument(new batasInput((int) 7).getKata(Ttb));
        Tbb.setDocument(new batasInput((int) 7).getKata(Tbb));
        Tvas.setDocument(new batasInput((int) 100).getKata(Tvas));        
        Tobat1.setDocument(new batasInput((int) 100).getKata(Tobat1));
        Tobat2.setDocument(new batasInput((int) 100).getKata(Tobat2));
        Tobat3.setDocument(new batasInput((int) 100).getKata(Tobat3));        
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
                if (akses.getform().equals("RMAsesmenPraSedasi")) {
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
        BtnNotepad = new widget.Button();
        BtnResep = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        PanelAsesmen = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel34 = new widget.Label();
        Tnip = new widget.TextBox();
        TnmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        chkSaya = new widget.CekBox();
        jLabel12 = new widget.Label();
        TtglPuasa = new widget.Tanggal();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel68 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        TriwAlergiObat = new widget.TextArea();
        jLabel69 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        TobatSaatIni = new widget.TextArea();
        jLabel70 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        TriwAnastesi = new widget.TextArea();
        jLabel11 = new widget.Label();
        jLabel14 = new widget.Label();
        jLabel71 = new widget.Label();
        Tkepala = new widget.TextBox();
        jLabel72 = new widget.Label();
        Tsklera = new widget.TextBox();
        jLabel73 = new widget.Label();
        Tconjung = new widget.TextBox();
        jLabel74 = new widget.Label();
        Tleher = new widget.TextBox();
        jLabel75 = new widget.Label();
        Tparu = new widget.TextBox();
        jLabel76 = new widget.Label();
        Tjantung = new widget.TextBox();
        jLabel77 = new widget.Label();
        Tabdomen = new widget.TextBox();
        jLabel78 = new widget.Label();
        Tekstremitas = new widget.TextBox();
        jLabel15 = new widget.Label();
        jLabel79 = new widget.Label();
        Tgcs = new widget.TextBox();
        jLabel80 = new widget.Label();
        Ttd = new widget.TextBox();
        label42 = new widget.Label();
        Trr = new widget.TextBox();
        label45 = new widget.Label();
        Tnadi = new widget.TextBox();
        label43 = new widget.Label();
        jLabel81 = new widget.Label();
        Tsuhu = new widget.TextBox();
        jLabel23 = new widget.Label();
        Ttb = new widget.TextBox();
        label44 = new widget.Label();
        Tbb = new widget.TextBox();
        label46 = new widget.Label();
        Tvas = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel82 = new widget.Label();
        cmbHilang = new widget.ComboBox();
        jLabel83 = new widget.Label();
        cmbMasalah = new widget.ComboBox();
        jLabel84 = new widget.Label();
        cmbLeher = new widget.ComboBox();
        jLabel85 = new widget.Label();
        cmbStroke = new widget.ComboBox();
        jLabel86 = new widget.Label();
        cmbSesak = new widget.ComboBox();
        jLabel87 = new widget.Label();
        cmbBuka = new widget.ComboBox();
        jLabel88 = new widget.Label();
        cmbJarak = new widget.ComboBox();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        jLabel91 = new widget.Label();
        jLabel92 = new widget.Label();
        jLabel93 = new widget.Label();
        jLabel94 = new widget.Label();
        jLabel95 = new widget.Label();
        cmbSakit = new widget.ComboBox();
        cmbDenyut = new widget.ComboBox();
        cmbSedang = new widget.ComboBox();
        cmbKejang = new widget.ComboBox();
        cmbObes = new widget.ComboBox();
        cmbGigi = new widget.ComboBox();
        cmbGerakan = new widget.ComboBox();
        jLabel96 = new widget.Label();
        ChkSatu = new widget.CekBox();
        ChkDua = new widget.CekBox();
        ChkTiga = new widget.CekBox();
        ChkEmpat = new widget.CekBox();
        jLabel17 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        Tpemeriksaan = new widget.TextArea();
        jLabel18 = new widget.Label();
        scrollPane17 = new widget.ScrollPane();
        Tdiagnosa = new widget.TextArea();
        jLabel20 = new widget.Label();
        jLabel97 = new widget.Label();
        ChkSedasi = new widget.CekBox();
        Tobat1 = new widget.TextBox();
        jLabel98 = new widget.Label();
        Tobat2 = new widget.TextBox();
        jLabel99 = new widget.Label();
        Tobat3 = new widget.TextBox();
        ChkGA = new widget.CekBox();
        Tga = new widget.TextBox();
        ChkRegional = new widget.CekBox();
        ChkSpinal = new widget.CekBox();
        ChkEpidural = new widget.CekBox();
        ChkKaudal = new widget.CekBox();
        ChkBlok = new widget.CekBox();
        jLabel100 = new widget.Label();
        ChkEkg = new widget.CekBox();
        ChkSpo = new widget.CekBox();
        ChkNibp = new widget.CekBox();
        ChkTemp = new widget.CekBox();
        ChkLain = new widget.CekBox();
        Tlain = new widget.TextBox();
        jLabel101 = new widget.Label();
        ChkRanap = new widget.CekBox();
        ChkRalan = new widget.CekBox();
        ChkRakhus = new widget.CekBox();
        ChkIcu = new widget.CekBox();
        ChkHcu = new widget.CekBox();
        jLabel102 = new widget.Label();
        scrollPane18 = new widget.ScrollPane();
        Tcatatan = new widget.TextArea();
        jLabel22 = new widget.Label();
        jLabel24 = new widget.Label();
        ChkAsa1 = new widget.CekBox();
        ChkAsa2 = new widget.CekBox();
        ChkAsa3 = new widget.CekBox();
        ChkAsa4 = new widget.CekBox();
        ChkEmergency = new widget.CekBox();
        jLabel25 = new widget.Label();
        scrollPane19 = new widget.ScrollPane();
        Tpenyulit = new widget.TextArea();
        jLabel26 = new widget.Label();
        jLabel103 = new widget.Label();
        jLabel104 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel13 = new widget.Label();
        TtglRencana = new widget.Tanggal();
        BtnPasteObat = new widget.Button();
        BtnPastePenunjang = new widget.Button();
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
        tbSedasi = new widget.Table();
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

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Asesmen Pra Sedasi Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-09-2024" }));
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

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-09-2024" }));
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Pra Sedasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        PanelAsesmen.setBackground(new java.awt.Color(255, 255, 255));
        PanelAsesmen.setBorder(null);
        PanelAsesmen.setToolTipText("");
        PanelAsesmen.setComponentPopupMenu(jPopupMenu1);
        PanelAsesmen.setName("PanelAsesmen"); // NOI18N
        PanelAsesmen.setPreferredSize(new java.awt.Dimension(870, 1586));
        PanelAsesmen.setLayout(null);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        PanelAsesmen.add(TNoRw);
        TNoRw.setBounds(114, 26, 131, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        PanelAsesmen.add(TPasien);
        TPasien.setBounds(319, 26, 410, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        PanelAsesmen.add(TNoRM);
        TNoRM.setBounds(247, 26, 70, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        PanelAsesmen.add(jLabel10);
        jLabel10.setBounds(0, 26, 110, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Dokter Anastesi :");
        jLabel34.setName("jLabel34"); // NOI18N
        PanelAsesmen.add(jLabel34);
        jLabel34.setBounds(0, 1546, 150, 23);

        Tnip.setEditable(false);
        Tnip.setForeground(new java.awt.Color(0, 0, 0));
        Tnip.setName("Tnip"); // NOI18N
        PanelAsesmen.add(Tnip);
        Tnip.setBounds(156, 1546, 170, 23);

        TnmDokter.setEditable(false);
        TnmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter.setName("TnmDokter"); // NOI18N
        PanelAsesmen.add(TnmDokter);
        TnmDokter.setBounds(330, 1546, 390, 23);

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
        PanelAsesmen.add(BtnDokter);
        BtnDokter.setBounds(720, 1546, 28, 23);

        chkSaya.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya.setText("Saya Sendiri");
        chkSaya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya.setName("chkSaya"); // NOI18N
        chkSaya.setOpaque(false);
        chkSaya.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaActionPerformed(evt);
            }
        });
        PanelAsesmen.add(chkSaya);
        chkSaya.setBounds(755, 1546, 87, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tanggal :");
        jLabel12.setName("jLabel12"); // NOI18N
        PanelAsesmen.add(jLabel12);
        jLabel12.setBounds(310, 1490, 60, 23);

        TtglPuasa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-09-2024" }));
        TtglPuasa.setDisplayFormat("dd-MM-yyyy");
        TtglPuasa.setName("TtglPuasa"); // NOI18N
        TtglPuasa.setOpaque(false);
        TtglPuasa.setPreferredSize(new java.awt.Dimension(90, 23));
        PanelAsesmen.add(TtglPuasa);
        TtglPuasa.setBounds(376, 1490, 90, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        PanelAsesmen.add(cmbJam);
        cmbJam.setBounds(160, 1490, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        PanelAsesmen.add(cmbMnt);
        cmbMnt.setBounds(210, 1490, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        PanelAsesmen.add(cmbDtk);
        cmbDtk.setBounds(260, 1490, 45, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        PanelAsesmen.add(jLabel63);
        jLabel63.setBounds(0, 54, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        PanelAsesmen.add(TrgRawat);
        TrgRawat.setBounds(115, 54, 615, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Riw. Alergi Obat :");
        jLabel68.setName("jLabel68"); // NOI18N
        PanelAsesmen.add(jLabel68);
        jLabel68.setBounds(0, 82, 110, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N

        TriwAlergiObat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TriwAlergiObat.setColumns(20);
        TriwAlergiObat.setRows(5);
        TriwAlergiObat.setName("TriwAlergiObat"); // NOI18N
        TriwAlergiObat.setPreferredSize(new java.awt.Dimension(162, 1500));
        TriwAlergiObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwAlergiObatKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(TriwAlergiObat);

        PanelAsesmen.add(scrollPane13);
        scrollPane13.setBounds(115, 82, 615, 80);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Obat Saat Ini :");
        jLabel69.setName("jLabel69"); // NOI18N
        PanelAsesmen.add(jLabel69);
        jLabel69.setBounds(0, 166, 110, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        TobatSaatIni.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TobatSaatIni.setColumns(20);
        TobatSaatIni.setRows(5);
        TobatSaatIni.setName("TobatSaatIni"); // NOI18N
        TobatSaatIni.setPreferredSize(new java.awt.Dimension(162, 1500));
        TobatSaatIni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatSaatIniKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(TobatSaatIni);

        PanelAsesmen.add(scrollPane14);
        scrollPane14.setBounds(115, 166, 615, 80);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Riw. Anastesi & Komplikasi :");
        jLabel70.setName("jLabel70"); // NOI18N
        PanelAsesmen.add(jLabel70);
        jLabel70.setBounds(0, 251, 150, 23);

        scrollPane15.setName("scrollPane15"); // NOI18N

        TriwAnastesi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TriwAnastesi.setColumns(20);
        TriwAnastesi.setRows(5);
        TriwAnastesi.setName("TriwAnastesi"); // NOI18N
        TriwAnastesi.setPreferredSize(new java.awt.Dimension(162, 1500));
        TriwAnastesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwAnastesiKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(TriwAnastesi);

        PanelAsesmen.add(scrollPane15);
        scrollPane15.setBounds(155, 251, 575, 80);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("ANAMNESA :");
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        PanelAsesmen.add(jLabel11);
        jLabel11.setBounds(0, 6, 110, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("KEADAAN UMUM :");
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        PanelAsesmen.add(jLabel14);
        jLabel14.setBounds(0, 335, 120, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Kepala :");
        jLabel71.setName("jLabel71"); // NOI18N
        PanelAsesmen.add(jLabel71);
        jLabel71.setBounds(0, 354, 110, 23);

        Tkepala.setForeground(new java.awt.Color(0, 0, 0));
        Tkepala.setName("Tkepala"); // NOI18N
        Tkepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkepalaKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tkepala);
        Tkepala.setBounds(114, 354, 270, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Sklera :");
        jLabel72.setName("jLabel72"); // NOI18N
        PanelAsesmen.add(jLabel72);
        jLabel72.setBounds(385, 354, 50, 23);

        Tsklera.setForeground(new java.awt.Color(0, 0, 0));
        Tsklera.setName("Tsklera"); // NOI18N
        Tsklera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskleraKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tsklera);
        Tsklera.setBounds(440, 354, 290, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Conjungtiva :");
        jLabel73.setName("jLabel73"); // NOI18N
        PanelAsesmen.add(jLabel73);
        jLabel73.setBounds(0, 382, 110, 23);

        Tconjung.setForeground(new java.awt.Color(0, 0, 0));
        Tconjung.setName("Tconjung"); // NOI18N
        Tconjung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TconjungKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tconjung);
        Tconjung.setBounds(115, 382, 270, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Leher :");
        jLabel74.setName("jLabel74"); // NOI18N
        PanelAsesmen.add(jLabel74);
        jLabel74.setBounds(385, 382, 50, 23);

        Tleher.setForeground(new java.awt.Color(0, 0, 0));
        Tleher.setName("Tleher"); // NOI18N
        Tleher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TleherKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tleher);
        Tleher.setBounds(440, 382, 290, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Paru - Paru :");
        jLabel75.setName("jLabel75"); // NOI18N
        PanelAsesmen.add(jLabel75);
        jLabel75.setBounds(0, 410, 110, 23);

        Tparu.setForeground(new java.awt.Color(0, 0, 0));
        Tparu.setName("Tparu"); // NOI18N
        Tparu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TparuKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tparu);
        Tparu.setBounds(115, 410, 615, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Jantung :");
        jLabel76.setName("jLabel76"); // NOI18N
        PanelAsesmen.add(jLabel76);
        jLabel76.setBounds(0, 438, 110, 23);

        Tjantung.setForeground(new java.awt.Color(0, 0, 0));
        Tjantung.setName("Tjantung"); // NOI18N
        Tjantung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjantungKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tjantung);
        Tjantung.setBounds(115, 438, 615, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Abdomen :");
        jLabel77.setName("jLabel77"); // NOI18N
        PanelAsesmen.add(jLabel77);
        jLabel77.setBounds(0, 466, 110, 23);

        Tabdomen.setForeground(new java.awt.Color(0, 0, 0));
        Tabdomen.setName("Tabdomen"); // NOI18N
        Tabdomen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TabdomenKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tabdomen);
        Tabdomen.setBounds(115, 466, 615, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Ekstremitas :");
        jLabel78.setName("jLabel78"); // NOI18N
        PanelAsesmen.add(jLabel78);
        jLabel78.setBounds(0, 494, 110, 23);

        Tekstremitas.setForeground(new java.awt.Color(0, 0, 0));
        Tekstremitas.setName("Tekstremitas"); // NOI18N
        Tekstremitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TekstremitasKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tekstremitas);
        Tekstremitas.setBounds(115, 494, 615, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("PEMERIKSAAN FISIK :");
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        PanelAsesmen.add(jLabel15);
        jLabel15.setBounds(0, 517, 140, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("GCS :");
        jLabel79.setName("jLabel79"); // NOI18N
        PanelAsesmen.add(jLabel79);
        jLabel79.setBounds(0, 540, 110, 23);

        Tgcs.setForeground(new java.awt.Color(0, 0, 0));
        Tgcs.setName("Tgcs"); // NOI18N
        Tgcs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tgcs);
        Tgcs.setBounds(114, 540, 90, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Tekanan Darah :");
        jLabel80.setName("jLabel80"); // NOI18N
        PanelAsesmen.add(jLabel80);
        jLabel80.setBounds(205, 540, 90, 23);

        Ttd.setForeground(new java.awt.Color(0, 0, 0));
        Ttd.setName("Ttd"); // NOI18N
        Ttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Ttd);
        Ttd.setBounds(300, 540, 60, 23);

        label42.setForeground(new java.awt.Color(0, 0, 0));
        label42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label42.setText("mmHg     RR : ");
        label42.setName("label42"); // NOI18N
        label42.setPreferredSize(new java.awt.Dimension(70, 23));
        PanelAsesmen.add(label42);
        label42.setBounds(365, 540, 70, 23);

        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Trr);
        Trr.setBounds(435, 540, 60, 23);

        label45.setForeground(new java.awt.Color(0, 0, 0));
        label45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label45.setText("x/menit      Nadi :");
        label45.setName("label45"); // NOI18N
        label45.setPreferredSize(new java.awt.Dimension(70, 23));
        PanelAsesmen.add(label45);
        label45.setBounds(500, 540, 85, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tnadi);
        Tnadi.setBounds(587, 540, 60, 23);

        label43.setForeground(new java.awt.Color(0, 0, 0));
        label43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label43.setText("x/menit");
        label43.setName("label43"); // NOI18N
        label43.setPreferredSize(new java.awt.Dimension(70, 23));
        PanelAsesmen.add(label43);
        label43.setBounds(650, 540, 50, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Suhu :");
        jLabel81.setName("jLabel81"); // NOI18N
        PanelAsesmen.add(jLabel81);
        jLabel81.setBounds(0, 568, 110, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tsuhu);
        Tsuhu.setBounds(114, 568, 60, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("°C     Tinggi Badan :");
        jLabel23.setName("jLabel23"); // NOI18N
        PanelAsesmen.add(jLabel23);
        jLabel23.setBounds(180, 568, 98, 23);

        Ttb.setForeground(new java.awt.Color(0, 0, 0));
        Ttb.setName("Ttb"); // NOI18N
        Ttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Ttb);
        Ttb.setBounds(280, 568, 60, 23);

        label44.setForeground(new java.awt.Color(0, 0, 0));
        label44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label44.setText("Cm      Berat Badan :");
        label44.setName("label44"); // NOI18N
        label44.setPreferredSize(new java.awt.Dimension(70, 23));
        PanelAsesmen.add(label44);
        label44.setBounds(345, 568, 103, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tbb);
        Tbb.setBounds(450, 568, 60, 23);

        label46.setForeground(new java.awt.Color(0, 0, 0));
        label46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label46.setText("Kg     VAS :");
        label46.setName("label46"); // NOI18N
        label46.setPreferredSize(new java.awt.Dimension(70, 23));
        PanelAsesmen.add(label46);
        label46.setBounds(515, 568, 57, 23);

        Tvas.setForeground(new java.awt.Color(0, 0, 0));
        Tvas.setName("Tvas"); // NOI18N
        Tvas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TvasKeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tvas);
        Tvas.setBounds(572, 568, 160, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("KAJIAN SISTEM :");
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        PanelAsesmen.add(jLabel16);
        jLabel16.setBounds(0, 596, 120, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Hilangnya Gigi :");
        jLabel82.setName("jLabel82"); // NOI18N
        PanelAsesmen.add(jLabel82);
        jLabel82.setBounds(0, 616, 150, 23);

        cmbHilang.setForeground(new java.awt.Color(0, 0, 0));
        cmbHilang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbHilang.setName("cmbHilang"); // NOI18N
        cmbHilang.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelAsesmen.add(cmbHilang);
        cmbHilang.setBounds(155, 616, 60, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Masalah Mobilisasi Leher :");
        jLabel83.setName("jLabel83"); // NOI18N
        PanelAsesmen.add(jLabel83);
        jLabel83.setBounds(0, 644, 150, 23);

        cmbMasalah.setForeground(new java.awt.Color(0, 0, 0));
        cmbMasalah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbMasalah.setName("cmbMasalah"); // NOI18N
        cmbMasalah.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelAsesmen.add(cmbMasalah);
        cmbMasalah.setBounds(155, 644, 60, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Leher Pendek :");
        jLabel84.setName("jLabel84"); // NOI18N
        PanelAsesmen.add(jLabel84);
        jLabel84.setBounds(0, 672, 150, 23);

        cmbLeher.setForeground(new java.awt.Color(0, 0, 0));
        cmbLeher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbLeher.setName("cmbLeher"); // NOI18N
        cmbLeher.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelAsesmen.add(cmbLeher);
        cmbLeher.setBounds(155, 672, 60, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Stroke :");
        jLabel85.setName("jLabel85"); // NOI18N
        PanelAsesmen.add(jLabel85);
        jLabel85.setBounds(0, 700, 150, 23);

        cmbStroke.setForeground(new java.awt.Color(0, 0, 0));
        cmbStroke.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbStroke.setName("cmbStroke"); // NOI18N
        cmbStroke.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelAsesmen.add(cmbStroke);
        cmbStroke.setBounds(155, 700, 60, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Sesak Nafas :");
        jLabel86.setName("jLabel86"); // NOI18N
        PanelAsesmen.add(jLabel86);
        jLabel86.setBounds(0, 728, 150, 23);

        cmbSesak.setForeground(new java.awt.Color(0, 0, 0));
        cmbSesak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbSesak.setName("cmbSesak"); // NOI18N
        cmbSesak.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelAsesmen.add(cmbSesak);
        cmbSesak.setBounds(155, 728, 60, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Buka Mulut > 2 Jari :");
        jLabel87.setName("jLabel87"); // NOI18N
        PanelAsesmen.add(jLabel87);
        jLabel87.setBounds(0, 756, 150, 23);

        cmbBuka.setForeground(new java.awt.Color(0, 0, 0));
        cmbBuka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbBuka.setName("cmbBuka"); // NOI18N
        cmbBuka.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelAsesmen.add(cmbBuka);
        cmbBuka.setBounds(155, 756, 60, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Jarak Thyromental > 3 Jari :");
        jLabel88.setName("jLabel88"); // NOI18N
        PanelAsesmen.add(jLabel88);
        jLabel88.setBounds(0, 784, 150, 23);

        cmbJarak.setForeground(new java.awt.Color(0, 0, 0));
        cmbJarak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbJarak.setName("cmbJarak"); // NOI18N
        cmbJarak.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelAsesmen.add(cmbJarak);
        cmbJarak.setBounds(155, 784, 60, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Sakit Dada :");
        jLabel89.setName("jLabel89"); // NOI18N
        PanelAsesmen.add(jLabel89);
        jLabel89.setBounds(220, 616, 160, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Denyut Jantung Tidak Normal :");
        jLabel90.setName("jLabel90"); // NOI18N
        PanelAsesmen.add(jLabel90);
        jLabel90.setBounds(220, 644, 160, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Sedang Hamil :");
        jLabel91.setName("jLabel91"); // NOI18N
        PanelAsesmen.add(jLabel91);
        jLabel91.setBounds(220, 672, 160, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Kejang :");
        jLabel92.setName("jLabel92"); // NOI18N
        PanelAsesmen.add(jLabel92);
        jLabel92.setBounds(220, 700, 160, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Obesitas :");
        jLabel93.setName("jLabel93"); // NOI18N
        PanelAsesmen.add(jLabel93);
        jLabel93.setBounds(220, 728, 160, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Gigi Palsu :");
        jLabel94.setName("jLabel94"); // NOI18N
        PanelAsesmen.add(jLabel94);
        jLabel94.setBounds(220, 756, 160, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Gerakan Leher Maksimal :");
        jLabel95.setName("jLabel95"); // NOI18N
        PanelAsesmen.add(jLabel95);
        jLabel95.setBounds(220, 784, 160, 23);

        cmbSakit.setForeground(new java.awt.Color(0, 0, 0));
        cmbSakit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbSakit.setName("cmbSakit"); // NOI18N
        cmbSakit.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelAsesmen.add(cmbSakit);
        cmbSakit.setBounds(385, 616, 60, 23);

        cmbDenyut.setForeground(new java.awt.Color(0, 0, 0));
        cmbDenyut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbDenyut.setName("cmbDenyut"); // NOI18N
        cmbDenyut.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelAsesmen.add(cmbDenyut);
        cmbDenyut.setBounds(385, 644, 60, 23);

        cmbSedang.setForeground(new java.awt.Color(0, 0, 0));
        cmbSedang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbSedang.setName("cmbSedang"); // NOI18N
        cmbSedang.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelAsesmen.add(cmbSedang);
        cmbSedang.setBounds(385, 672, 60, 23);

        cmbKejang.setForeground(new java.awt.Color(0, 0, 0));
        cmbKejang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbKejang.setName("cmbKejang"); // NOI18N
        cmbKejang.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelAsesmen.add(cmbKejang);
        cmbKejang.setBounds(385, 700, 60, 23);

        cmbObes.setForeground(new java.awt.Color(0, 0, 0));
        cmbObes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbObes.setName("cmbObes"); // NOI18N
        cmbObes.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelAsesmen.add(cmbObes);
        cmbObes.setBounds(385, 728, 60, 23);

        cmbGigi.setForeground(new java.awt.Color(0, 0, 0));
        cmbGigi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbGigi.setName("cmbGigi"); // NOI18N
        cmbGigi.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelAsesmen.add(cmbGigi);
        cmbGigi.setBounds(385, 756, 60, 23);

        cmbGerakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbGerakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbGerakan.setName("cmbGerakan"); // NOI18N
        cmbGerakan.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelAsesmen.add(cmbGerakan);
        cmbGerakan.setBounds(385, 784, 60, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Mallampati :");
        jLabel96.setName("jLabel96"); // NOI18N
        PanelAsesmen.add(jLabel96);
        jLabel96.setBounds(450, 616, 70, 23);

        ChkSatu.setBackground(new java.awt.Color(255, 255, 250));
        ChkSatu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSatu.setForeground(new java.awt.Color(0, 0, 0));
        ChkSatu.setText("I");
        ChkSatu.setBorderPainted(true);
        ChkSatu.setBorderPaintedFlat(true);
        ChkSatu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSatu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSatu.setName("ChkSatu"); // NOI18N
        ChkSatu.setOpaque(false);
        ChkSatu.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkSatu);
        ChkSatu.setBounds(525, 616, 40, 23);

        ChkDua.setBackground(new java.awt.Color(255, 255, 250));
        ChkDua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDua.setForeground(new java.awt.Color(0, 0, 0));
        ChkDua.setText("II");
        ChkDua.setBorderPainted(true);
        ChkDua.setBorderPaintedFlat(true);
        ChkDua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDua.setName("ChkDua"); // NOI18N
        ChkDua.setOpaque(false);
        ChkDua.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkDua);
        ChkDua.setBounds(525, 644, 40, 23);

        ChkTiga.setBackground(new java.awt.Color(255, 255, 250));
        ChkTiga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTiga.setForeground(new java.awt.Color(0, 0, 0));
        ChkTiga.setText("III");
        ChkTiga.setBorderPainted(true);
        ChkTiga.setBorderPaintedFlat(true);
        ChkTiga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTiga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTiga.setName("ChkTiga"); // NOI18N
        ChkTiga.setOpaque(false);
        ChkTiga.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkTiga);
        ChkTiga.setBounds(525, 672, 40, 23);

        ChkEmpat.setBackground(new java.awt.Color(255, 255, 250));
        ChkEmpat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkEmpat.setForeground(new java.awt.Color(0, 0, 0));
        ChkEmpat.setText("IV");
        ChkEmpat.setBorderPainted(true);
        ChkEmpat.setBorderPaintedFlat(true);
        ChkEmpat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkEmpat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkEmpat.setName("ChkEmpat"); // NOI18N
        ChkEmpat.setOpaque(false);
        ChkEmpat.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkEmpat);
        ChkEmpat.setBounds(525, 700, 40, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("LABORATORIUM / PEMERIKSAAN PENUNJANG :");
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        PanelAsesmen.add(jLabel17);
        jLabel17.setBounds(0, 812, 280, 23);

        scrollPane16.setName("scrollPane16"); // NOI18N

        Tpemeriksaan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tpemeriksaan.setColumns(20);
        Tpemeriksaan.setRows(5);
        Tpemeriksaan.setName("Tpemeriksaan"); // NOI18N
        Tpemeriksaan.setPreferredSize(new java.awt.Dimension(162, 1500));
        Tpemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpemeriksaanKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(Tpemeriksaan);

        PanelAsesmen.add(scrollPane16);
        scrollPane16.setBounds(20, 832, 710, 80);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("DIAGNOSA :");
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N
        PanelAsesmen.add(jLabel18);
        jLabel18.setBounds(0, 917, 90, 23);

        scrollPane17.setName("scrollPane17"); // NOI18N

        Tdiagnosa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tdiagnosa.setColumns(20);
        Tdiagnosa.setRows(5);
        Tdiagnosa.setName("Tdiagnosa"); // NOI18N
        Tdiagnosa.setPreferredSize(new java.awt.Dimension(162, 1500));
        Tdiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaKeyPressed(evt);
            }
        });
        scrollPane17.setViewportView(Tdiagnosa);

        PanelAsesmen.add(scrollPane17);
        scrollPane17.setBounds(20, 937, 710, 80);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("PERENCANAAN ANASTESI :");
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N
        PanelAsesmen.add(jLabel20);
        jLabel20.setBounds(0, 1023, 165, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Teknik Anestesia & Sedasi :");
        jLabel97.setName("jLabel97"); // NOI18N
        PanelAsesmen.add(jLabel97);
        jLabel97.setBounds(0, 1043, 150, 23);

        ChkSedasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkSedasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSedasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkSedasi.setText("Sedasi : Obat (Dosis & Rute) : 1.");
        ChkSedasi.setBorderPainted(true);
        ChkSedasi.setBorderPaintedFlat(true);
        ChkSedasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSedasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSedasi.setName("ChkSedasi"); // NOI18N
        ChkSedasi.setOpaque(false);
        ChkSedasi.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkSedasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSedasiActionPerformed(evt);
            }
        });
        PanelAsesmen.add(ChkSedasi);
        ChkSedasi.setBounds(157, 1043, 180, 23);

        Tobat1.setForeground(new java.awt.Color(0, 0, 0));
        Tobat1.setName("Tobat1"); // NOI18N
        Tobat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tobat1KeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tobat1);
        Tobat1.setBounds(340, 1043, 110, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel98.setText("2.");
        jLabel98.setName("jLabel98"); // NOI18N
        PanelAsesmen.add(jLabel98);
        jLabel98.setBounds(450, 1043, 20, 23);

        Tobat2.setForeground(new java.awt.Color(0, 0, 0));
        Tobat2.setName("Tobat2"); // NOI18N
        Tobat2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tobat2KeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tobat2);
        Tobat2.setBounds(470, 1043, 110, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel99.setText("3.");
        jLabel99.setName("jLabel99"); // NOI18N
        PanelAsesmen.add(jLabel99);
        jLabel99.setBounds(580, 1043, 20, 23);

        Tobat3.setForeground(new java.awt.Color(0, 0, 0));
        Tobat3.setName("Tobat3"); // NOI18N
        Tobat3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tobat3KeyPressed(evt);
            }
        });
        PanelAsesmen.add(Tobat3);
        Tobat3.setBounds(600, 1043, 130, 23);

        ChkGA.setBackground(new java.awt.Color(255, 255, 250));
        ChkGA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGA.setForeground(new java.awt.Color(0, 0, 0));
        ChkGA.setText("GA :");
        ChkGA.setBorderPainted(true);
        ChkGA.setBorderPaintedFlat(true);
        ChkGA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGA.setName("ChkGA"); // NOI18N
        ChkGA.setOpaque(false);
        ChkGA.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkGA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkGAActionPerformed(evt);
            }
        });
        PanelAsesmen.add(ChkGA);
        ChkGA.setBounds(157, 1071, 45, 23);

        Tga.setForeground(new java.awt.Color(0, 0, 0));
        Tga.setName("Tga"); // NOI18N
        PanelAsesmen.add(Tga);
        Tga.setBounds(205, 1071, 525, 23);

        ChkRegional.setBackground(new java.awt.Color(255, 255, 250));
        ChkRegional.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkRegional.setForeground(new java.awt.Color(0, 0, 0));
        ChkRegional.setText("Regional :");
        ChkRegional.setBorderPainted(true);
        ChkRegional.setBorderPaintedFlat(true);
        ChkRegional.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkRegional.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkRegional.setName("ChkRegional"); // NOI18N
        ChkRegional.setOpaque(false);
        ChkRegional.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkRegional);
        ChkRegional.setBounds(157, 1099, 73, 23);

        ChkSpinal.setBackground(new java.awt.Color(255, 255, 250));
        ChkSpinal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSpinal.setForeground(new java.awt.Color(0, 0, 0));
        ChkSpinal.setText("Spinal");
        ChkSpinal.setBorderPainted(true);
        ChkSpinal.setBorderPaintedFlat(true);
        ChkSpinal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSpinal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSpinal.setName("ChkSpinal"); // NOI18N
        ChkSpinal.setOpaque(false);
        ChkSpinal.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkSpinal);
        ChkSpinal.setBounds(236, 1099, 60, 23);

        ChkEpidural.setBackground(new java.awt.Color(255, 255, 250));
        ChkEpidural.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkEpidural.setForeground(new java.awt.Color(0, 0, 0));
        ChkEpidural.setText("Epidural");
        ChkEpidural.setBorderPainted(true);
        ChkEpidural.setBorderPaintedFlat(true);
        ChkEpidural.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkEpidural.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkEpidural.setName("ChkEpidural"); // NOI18N
        ChkEpidural.setOpaque(false);
        ChkEpidural.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkEpidural);
        ChkEpidural.setBounds(305, 1099, 70, 23);

        ChkKaudal.setBackground(new java.awt.Color(255, 255, 250));
        ChkKaudal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKaudal.setForeground(new java.awt.Color(0, 0, 0));
        ChkKaudal.setText("Kaudal");
        ChkKaudal.setBorderPainted(true);
        ChkKaudal.setBorderPaintedFlat(true);
        ChkKaudal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKaudal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKaudal.setName("ChkKaudal"); // NOI18N
        ChkKaudal.setOpaque(false);
        ChkKaudal.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkKaudal);
        ChkKaudal.setBounds(380, 1099, 65, 23);

        ChkBlok.setBackground(new java.awt.Color(255, 255, 250));
        ChkBlok.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBlok.setForeground(new java.awt.Color(0, 0, 0));
        ChkBlok.setText("Blok Perifer");
        ChkBlok.setBorderPainted(true);
        ChkBlok.setBorderPaintedFlat(true);
        ChkBlok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBlok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBlok.setName("ChkBlok"); // NOI18N
        ChkBlok.setOpaque(false);
        ChkBlok.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkBlok);
        ChkBlok.setBounds(450, 1099, 85, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Monitoring :");
        jLabel100.setName("jLabel100"); // NOI18N
        PanelAsesmen.add(jLabel100);
        jLabel100.setBounds(0, 1127, 150, 23);

        ChkEkg.setBackground(new java.awt.Color(255, 255, 250));
        ChkEkg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkEkg.setForeground(new java.awt.Color(0, 0, 0));
        ChkEkg.setText("EKG");
        ChkEkg.setBorderPainted(true);
        ChkEkg.setBorderPaintedFlat(true);
        ChkEkg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkEkg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkEkg.setName("ChkEkg"); // NOI18N
        ChkEkg.setOpaque(false);
        ChkEkg.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkEkg);
        ChkEkg.setBounds(157, 1127, 50, 23);

        ChkSpo.setBackground(new java.awt.Color(255, 255, 250));
        ChkSpo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSpo.setForeground(new java.awt.Color(0, 0, 0));
        ChkSpo.setText("SpO2");
        ChkSpo.setBorderPainted(true);
        ChkSpo.setBorderPaintedFlat(true);
        ChkSpo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSpo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSpo.setName("ChkSpo"); // NOI18N
        ChkSpo.setOpaque(false);
        ChkSpo.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkSpo);
        ChkSpo.setBounds(215, 1127, 60, 23);

        ChkNibp.setBackground(new java.awt.Color(255, 255, 250));
        ChkNibp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNibp.setForeground(new java.awt.Color(0, 0, 0));
        ChkNibp.setText("NIBP");
        ChkNibp.setBorderPainted(true);
        ChkNibp.setBorderPaintedFlat(true);
        ChkNibp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNibp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNibp.setName("ChkNibp"); // NOI18N
        ChkNibp.setOpaque(false);
        ChkNibp.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkNibp);
        ChkNibp.setBounds(280, 1127, 55, 23);

        ChkTemp.setBackground(new java.awt.Color(255, 255, 250));
        ChkTemp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTemp.setForeground(new java.awt.Color(0, 0, 0));
        ChkTemp.setText("Temp");
        ChkTemp.setBorderPainted(true);
        ChkTemp.setBorderPaintedFlat(true);
        ChkTemp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTemp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTemp.setName("ChkTemp"); // NOI18N
        ChkTemp.setOpaque(false);
        ChkTemp.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkTemp);
        ChkTemp.setBounds(340, 1127, 50, 23);

        ChkLain.setBackground(new java.awt.Color(255, 255, 250));
        ChkLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLain.setForeground(new java.awt.Color(0, 0, 0));
        ChkLain.setText("Lain Lain");
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
        PanelAsesmen.add(ChkLain);
        ChkLain.setBounds(400, 1127, 67, 23);

        Tlain.setForeground(new java.awt.Color(0, 0, 0));
        Tlain.setName("Tlain"); // NOI18N
        PanelAsesmen.add(Tlain);
        Tlain.setBounds(470, 1127, 260, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Perawatan Pasca Anastesi :");
        jLabel101.setName("jLabel101"); // NOI18N
        PanelAsesmen.add(jLabel101);
        jLabel101.setBounds(0, 1155, 150, 23);

        ChkRanap.setBackground(new java.awt.Color(255, 255, 250));
        ChkRanap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkRanap.setForeground(new java.awt.Color(0, 0, 0));
        ChkRanap.setText("Rawat Inap");
        ChkRanap.setBorderPainted(true);
        ChkRanap.setBorderPaintedFlat(true);
        ChkRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkRanap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkRanap.setName("ChkRanap"); // NOI18N
        ChkRanap.setOpaque(false);
        ChkRanap.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkRanap);
        ChkRanap.setBounds(157, 1155, 80, 23);

        ChkRalan.setBackground(new java.awt.Color(255, 255, 250));
        ChkRalan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkRalan.setForeground(new java.awt.Color(0, 0, 0));
        ChkRalan.setText("Rawat Jalan");
        ChkRalan.setBorderPainted(true);
        ChkRalan.setBorderPaintedFlat(true);
        ChkRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkRalan.setName("ChkRalan"); // NOI18N
        ChkRalan.setOpaque(false);
        ChkRalan.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkRalan);
        ChkRalan.setBounds(245, 1155, 86, 23);

        ChkRakhus.setBackground(new java.awt.Color(255, 255, 250));
        ChkRakhus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkRakhus.setForeground(new java.awt.Color(0, 0, 0));
        ChkRakhus.setText("Rawat Khusus");
        ChkRakhus.setBorderPainted(true);
        ChkRakhus.setBorderPaintedFlat(true);
        ChkRakhus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkRakhus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkRakhus.setName("ChkRakhus"); // NOI18N
        ChkRakhus.setOpaque(false);
        ChkRakhus.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkRakhus);
        ChkRakhus.setBounds(340, 1155, 100, 23);

        ChkIcu.setBackground(new java.awt.Color(255, 255, 250));
        ChkIcu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkIcu.setForeground(new java.awt.Color(0, 0, 0));
        ChkIcu.setText("ICU");
        ChkIcu.setBorderPainted(true);
        ChkIcu.setBorderPaintedFlat(true);
        ChkIcu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkIcu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkIcu.setName("ChkIcu"); // NOI18N
        ChkIcu.setOpaque(false);
        ChkIcu.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkIcu);
        ChkIcu.setBounds(449, 1155, 48, 23);

        ChkHcu.setBackground(new java.awt.Color(255, 255, 250));
        ChkHcu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkHcu.setForeground(new java.awt.Color(0, 0, 0));
        ChkHcu.setText("HCU");
        ChkHcu.setBorderPainted(true);
        ChkHcu.setBorderPaintedFlat(true);
        ChkHcu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkHcu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHcu.setName("ChkHcu"); // NOI18N
        ChkHcu.setOpaque(false);
        ChkHcu.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkHcu);
        ChkHcu.setBounds(503, 1155, 48, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Catatan :");
        jLabel102.setName("jLabel102"); // NOI18N
        PanelAsesmen.add(jLabel102);
        jLabel102.setBounds(0, 1183, 110, 23);

        scrollPane18.setName("scrollPane18"); // NOI18N

        Tcatatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tcatatan.setColumns(20);
        Tcatatan.setRows(5);
        Tcatatan.setName("Tcatatan"); // NOI18N
        Tcatatan.setPreferredSize(new java.awt.Dimension(162, 1500));
        Tcatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanKeyPressed(evt);
            }
        });
        scrollPane18.setViewportView(Tcatatan);

        PanelAsesmen.add(scrollPane18);
        scrollPane18.setBounds(115, 1183, 615, 80);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("KESIMPULAN ANASTESI");
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N
        PanelAsesmen.add(jLabel22);
        jLabel22.setBounds(0, 1267, 165, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("ASA CLASSIFICATION :");
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N
        PanelAsesmen.add(jLabel24);
        jLabel24.setBounds(0, 1282, 165, 23);

        ChkAsa1.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsa1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsa1.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsa1.setText("ASA 1 Pasien Normal Yang Sehat");
        ChkAsa1.setBorderPainted(true);
        ChkAsa1.setBorderPaintedFlat(true);
        ChkAsa1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsa1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsa1.setName("ChkAsa1"); // NOI18N
        ChkAsa1.setOpaque(false);
        ChkAsa1.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkAsa1);
        ChkAsa1.setBounds(37, 1302, 260, 23);

        ChkAsa2.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsa2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsa2.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsa2.setText("ASA 2 Pasien Dengan Penyakit Sistemik Ringan");
        ChkAsa2.setBorderPainted(true);
        ChkAsa2.setBorderPaintedFlat(true);
        ChkAsa2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsa2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsa2.setName("ChkAsa2"); // NOI18N
        ChkAsa2.setOpaque(false);
        ChkAsa2.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkAsa2);
        ChkAsa2.setBounds(37, 1330, 260, 23);

        ChkAsa3.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsa3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsa3.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsa3.setText("ASA 3 Pasien Dengan Penyakit Sistemik Berat");
        ChkAsa3.setBorderPainted(true);
        ChkAsa3.setBorderPaintedFlat(true);
        ChkAsa3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsa3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsa3.setName("ChkAsa3"); // NOI18N
        ChkAsa3.setOpaque(false);
        ChkAsa3.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkAsa3);
        ChkAsa3.setBounds(310, 1302, 380, 23);

        ChkAsa4.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsa4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsa4.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsa4.setText("ASA 4 Pasien Dengan Penyakit Sistemik Berat Yang Mengancam Nyawa");
        ChkAsa4.setBorderPainted(true);
        ChkAsa4.setBorderPaintedFlat(true);
        ChkAsa4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsa4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsa4.setName("ChkAsa4"); // NOI18N
        ChkAsa4.setOpaque(false);
        ChkAsa4.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkAsa4);
        ChkAsa4.setBounds(310, 1330, 380, 23);

        ChkEmergency.setBackground(new java.awt.Color(255, 255, 250));
        ChkEmergency.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkEmergency.setForeground(new java.awt.Color(0, 0, 0));
        ChkEmergency.setText("EMERGENCY");
        ChkEmergency.setBorderPainted(true);
        ChkEmergency.setBorderPaintedFlat(true);
        ChkEmergency.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkEmergency.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkEmergency.setName("ChkEmergency"); // NOI18N
        ChkEmergency.setOpaque(false);
        ChkEmergency.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelAsesmen.add(ChkEmergency);
        ChkEmergency.setBounds(37, 1358, 100, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Penyulit :");
        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N
        PanelAsesmen.add(jLabel25);
        jLabel25.setBounds(0, 1386, 80, 23);

        scrollPane19.setName("scrollPane19"); // NOI18N

        Tpenyulit.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tpenyulit.setColumns(20);
        Tpenyulit.setRows(5);
        Tpenyulit.setName("Tpenyulit"); // NOI18N
        Tpenyulit.setPreferredSize(new java.awt.Dimension(162, 1500));
        Tpenyulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpenyulitKeyPressed(evt);
            }
        });
        scrollPane19.setViewportView(Tpenyulit);

        PanelAsesmen.add(scrollPane19);
        scrollPane19.setBounds(85, 1386, 645, 80);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("PERSIAPAN PRA ANASTESI :");
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N
        PanelAsesmen.add(jLabel26);
        jLabel26.setBounds(0, 1470, 175, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Puasa Mulai : Jam");
        jLabel103.setName("jLabel103"); // NOI18N
        PanelAsesmen.add(jLabel103);
        jLabel103.setBounds(0, 1490, 150, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Rencana Operasi : Jam");
        jLabel104.setName("jLabel104"); // NOI18N
        PanelAsesmen.add(jLabel104);
        jLabel104.setBounds(0, 1518, 150, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        PanelAsesmen.add(cmbJam1);
        cmbJam1.setBounds(160, 1518, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        PanelAsesmen.add(cmbMnt1);
        cmbMnt1.setBounds(210, 1518, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        PanelAsesmen.add(cmbDtk1);
        cmbDtk1.setBounds(260, 1518, 45, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tanggal :");
        jLabel13.setName("jLabel13"); // NOI18N
        PanelAsesmen.add(jLabel13);
        jLabel13.setBounds(310, 1518, 60, 23);

        TtglRencana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-09-2024" }));
        TtglRencana.setDisplayFormat("dd-MM-yyyy");
        TtglRencana.setName("TtglRencana"); // NOI18N
        TtglRencana.setOpaque(false);
        TtglRencana.setPreferredSize(new java.awt.Dimension(90, 23));
        PanelAsesmen.add(TtglRencana);
        TtglRencana.setBounds(376, 1518, 90, 23);

        BtnPasteObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasteObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPasteObat.setMnemonic('L');
        BtnPasteObat.setText("Paste");
        BtnPasteObat.setToolTipText("Alt+L");
        BtnPasteObat.setName("BtnPasteObat"); // NOI18N
        BtnPasteObat.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPasteObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasteObatActionPerformed(evt);
            }
        });
        PanelAsesmen.add(BtnPasteObat);
        BtnPasteObat.setBounds(740, 166, 90, 23);

        BtnPastePenunjang.setForeground(new java.awt.Color(0, 0, 0));
        BtnPastePenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPastePenunjang.setMnemonic('L');
        BtnPastePenunjang.setText("Paste");
        BtnPastePenunjang.setToolTipText("Alt+L");
        BtnPastePenunjang.setName("BtnPastePenunjang"); // NOI18N
        BtnPastePenunjang.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPastePenunjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPastePenunjangActionPerformed(evt);
            }
        });
        PanelAsesmen.add(BtnPastePenunjang);
        BtnPastePenunjang.setBounds(740, 832, 90, 23);

        scrollInput.setViewportView(PanelAsesmen);

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

        tbSedasi.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbSedasi.setComponentPopupMenu(jPopupMenu1);
        tbSedasi.setName("tbSedasi"); // NOI18N
        tbSedasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSedasiMouseClicked(evt);
            }
        });
        tbSedasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSedasiKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbSedasi);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Renc. Operasi :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-09-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-09-2024" }));
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
            if (Sequel.menyimpantf("asesmen_pra_sedasi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 76, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), TriwAlergiObat.getText(), TobatSaatIni.getText(), TriwAnastesi.getText(), Tkepala.getText(), Tsklera.getText(),
                        Tconjung.getText(), Tleher.getText(), Tparu.getText(), Tjantung.getText(), Tabdomen.getText(), Tekstremitas.getText(), Tgcs.getText(), Ttd.getText(),
                        Trr.getText(), Tnadi.getText(), Tsuhu.getText(), Ttb.getText(), Tbb.getText(), Tvas.getText(), cmbHilang.getSelectedItem().toString(),
                        cmbMasalah.getSelectedItem().toString(), cmbLeher.getSelectedItem().toString(), cmbStroke.getSelectedItem().toString(), cmbSesak.getSelectedItem().toString(),
                        cmbBuka.getSelectedItem().toString(), cmbJarak.getSelectedItem().toString(), cmbSakit.getSelectedItem().toString(), cmbDenyut.getSelectedItem().toString(),
                        cmbSedang.getSelectedItem().toString(), cmbKejang.getSelectedItem().toString(), cmbObes.getSelectedItem().toString(), cmbGigi.getSelectedItem().toString(),
                        cmbGerakan.getSelectedItem().toString(), mal1, mal2, mal3, mal4, Tpemeriksaan.getText(), Tdiagnosa.getText(), sedasi, Tobat1.getText(), Tobat2.getText(),
                        Tobat3.getText(), ga, Tga.getText(), regional, spinal, epidural, kaudal, blok, ekg, spo2, nibp, temp, lainlain, Tlain.getText(), ranap, ralan, rakhus,
                        icu, hcu, Tcatatan.getText(), asa1, asa2, asa3, asa4, emer, Tpenyulit.getText(), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        Valid.SetTgl(TtglPuasa.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                        Valid.SetTgl(TtglRencana.getSelectedItem() + ""), Tnip.getText(), Sequel.cariIsi("select now()")
                    }) == true) {

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
        if (tbSedasi.getSelectedRow() > -1) {
            hapus();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbSedasi.requestFocus();
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
            if (tbSedasi.getSelectedRow() > -1) {
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
        if (tbSedasi.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            //report rptAsesmenPraSedasi1
            param.put("namars", akses.getnamars());            
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
            param.put("riwAlergiObat", TriwAlergiObat.getText() + "\n");
            param.put("obatSaatIni", TobatSaatIni.getText() + "\n");
            param.put("riwAnastesi", TriwAnastesi.getText() + "\n");
            
            param.put("keadaan", "Kepala : " + Tkepala.getText() + ", Sklera : " + Tsklera.getText() + ", Conjungtiva : " + Tconjung.getText() + ", Leher : " + Tleher.getText() + "\n"
                    + "Paru-paru : " + Tparu.getText() + "\n"
                    + "Jantung : " + Tjantung.getText() + "\n"
                    + "Abdomen : " + Tabdomen.getText() + "\n"
                    + "Ekstremitas : " + Tekstremitas.getText() + "\n");
            
            param.put("pemeriksaan", "GCS : " + Tgcs.getText() + ", Tekanan Darah : " + Ttd.getText() + " mmHg, RR : " + Trr.getText() + " x/menit, Nadi : " + Tnadi.getText() + " x/menit\n"
                    + "Suhu : " + Tsuhu.getText() + " °C, Tinggi Badan : " + Ttb.getText() + " Cm, Berat Badan : " + Tbb.getText() + " Kg, VAS : " + Tvas.getText() + "\n");
            
            param.put("kajian1", ": " + cmbHilang.getSelectedItem().toString() + "\n"
                    + ": " + cmbMasalah.getSelectedItem().toString() + "\n"
                    + ": " + cmbLeher.getSelectedItem().toString() + "\n"
                    + ": " + cmbStroke.getSelectedItem().toString() + "\n"
                    + ": " + cmbSesak.getSelectedItem().toString() + "\n"
                    + ": " + cmbBuka.getSelectedItem().toString() + "\n"
                    + ": " + cmbJarak.getSelectedItem().toString());
            
            param.put("kajian2", ": " + cmbSakit.getSelectedItem().toString() + "\n"
                    + ": " + cmbDenyut.getSelectedItem().toString() + "\n"
                    + ": " + cmbSedang.getSelectedItem().toString() + "\n"
                    + ": " + cmbKejang.getSelectedItem().toString() + "\n"
                    + ": " + cmbObes.getSelectedItem().toString() + "\n"
                    + ": " + cmbGigi.getSelectedItem().toString() + "\n"
                    + ": " + cmbGerakan.getSelectedItem().toString());

            if (ChkSatu.isSelected() == true) {
                param.put("mal1", "V");
            } else {
                param.put("mal1", "");
            }
            
            if (ChkDua.isSelected() == true) {
                param.put("mal2", "V");
            } else {
                param.put("mal2", "");
            }
            
            if (ChkTiga.isSelected() == true) {
                param.put("mal3", "V");
            } else {
                param.put("mal3", "");
            }
            
            if (ChkEmpat.isSelected() == true) {
                param.put("mal4", "V");
            } else {
                param.put("mal4", "");
            }
            
            param.put("penunjang", Tpemeriksaan.getText() + "\n");
            param.put("diagnosa", Tdiagnosa.getText() + "\n");            
            
            //report rptAsesmenPraSedasi2 
            if (ChkSedasi.isSelected() == true) {
                param.put("sedasi", "V");
                param.put("obat1", Tobat1.getText());
                param.put("obat2", Tobat2.getText());
                param.put("obat3", Tobat3.getText());
            } else {
                param.put("sedasi", "");
                param.put("obat1", "");
                param.put("obat2", "");
                param.put("obat3", "");
            }
            
            if (ChkGA.isSelected() == true) {
                param.put("ga", "V");
                param.put("ketGa", Tga.getText());
            } else {
                param.put("ga", "");
                param.put("ketGa", "");
            }
            
            if (ChkRegional.isSelected() == true) {
                param.put("regional", "V");
            } else {
                param.put("regional", "");
            }
            
            if (ChkSpinal.isSelected() == true) {
                param.put("spinal", "V");
            } else {
                param.put("spinal", "");
            }
            
            if (ChkEpidural.isSelected() == true) {
                param.put("epidural", "V");
            } else {
                param.put("epidural", "");
            }
            
            if (ChkKaudal.isSelected() == true) {
                param.put("kaudal", "V");
            } else {
                param.put("kaudal", "");
            }
            
            if (ChkBlok.isSelected() == true) {
                param.put("blok", "V");
            } else {
                param.put("blok", "");
            }
            
            if (ChkEkg.isSelected() == true) {
                param.put("ekg", "V");
            } else {
                param.put("ekg", "");
            }
            
            if (ChkSpo.isSelected() == true) {
                param.put("spo2", "V");
            } else {
                param.put("spo2", "");
            }
            
            if (ChkNibp.isSelected() == true) {
                param.put("nibp", "V");
            } else {
                param.put("nibp", "");
            }
            
            if (ChkTemp.isSelected() == true) {
                param.put("temp", "V");
            } else {
                param.put("temp", "");
            }
            
            if (ChkLain.isSelected() == true) {
                param.put("lain", "V");
                param.put("ketLain", Tlain.getText());
            } else {
                param.put("lain", "");
                param.put("ketLain", "");
            }
            
            if (ChkRanap.isSelected() == true) {
                param.put("ranap", "V");
            } else {
                param.put("ranap", "");
            }
            
            if (ChkRalan.isSelected() == true) {
                param.put("ralan", "V");
            } else {
                param.put("ralan", "");
            }
            
            if (ChkRakhus.isSelected() == true) {
                param.put("rakhus", "V");
            } else {
                param.put("rakhus", "");
            }
            
            if (ChkIcu.isSelected() == true) {
                param.put("icu", "V");
            } else {
                param.put("icu", "");
            }
            
            if (ChkHcu.isSelected() == true) {
                param.put("hcu", "V");
            } else {
                param.put("hcu", "");
            }
            
            param.put("catatan", Tcatatan.getText() + "\n");
            
            if (ChkAsa1.isSelected() == true) {
                param.put("asa1", "V");
            } else {
                param.put("asa1", "");
            }
            
            if (ChkAsa2.isSelected() == true) {
                param.put("asa2", "V");
            } else {
                param.put("asa2", "");
            }
            
            if (ChkAsa3.isSelected() == true) {
                param.put("asa3", "V");
            } else {
                param.put("asa3", "");
            }
            
            if (ChkAsa4.isSelected() == true) {
                param.put("asa4", "V");
            } else {
                param.put("asa4", "");
            }
            
            if (ChkEmergency.isSelected() == true) {
                param.put("emer", "V");
            } else {
                param.put("emer", "");
            }
            
            param.put("penyulit", Tpenyulit.getText() + "\n");
            param.put("puasaMulai", cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " Wita, Tanggal : " + TtglPuasa.getSelectedItem().toString());
            param.put("rencana", cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " Wita, Tanggal : " + TtglRencana.getSelectedItem().toString());
            param.put("dokter", "(" + TnmDokter.getText() + ")");

            Valid.MyReport("rptAsesmenPraSedasi2.jasper", "report", "::[ Laporan Asesmen Pra Sedasi hal. 2 ]::",
                    "SELECT now() tanggal", param);
            Valid.MyReport("rptAsesmenPraSedasi1.jasper", "report", "::[ Laporan Asesmen Pra Sedasi hal. 1 ]::",
                    "SELECT now() tanggal", param);
            
            emptTeks();            
            TabRawat.setSelectedIndex(1);
            tampil();            
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
            tbSedasi.requestFocus();
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

    private void tbSedasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSedasiMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {                
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbSedasi.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbSedasiMouseClicked

    private void tbSedasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSedasiKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbSedasiKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            ChkAccor.setSelected(false);
            isMenu();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (TNoRw.getText().equals("")) {
            TabRawat.setSelectedIndex(1);
            tampil();
        } else {
            if (Sequel.cariInteger("select count(-1) from asesmen_pra_sedasi where no_rawat='" + TNoRw.getText() + "'") > 0) {
                TabRawat.setSelectedIndex(1);
                tampil();
            } else if (Sequel.cariInteger("select count(-1) from asesmen_pra_sedasi where no_rawat='" + TNoRw.getText() + "'") == 0) {
                TabRawat.setSelectedIndex(0);
            }
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
                    if (Sequel.cariInteger("select count(-1) from asesmen_pra_sedasi where "
                            + "waktu_simpan='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString() + "'") > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Proses kembalikan/restore data gagal, krn. sudah ada datanya yg. sama..!!");
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
        ChkAccor.setSelected(false);
        isMenu();
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMAsesmenPraSedasi");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMAsesmenPraSedasi");
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

        akses.setform("RMAsesmenPraSedasi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                Tnip.setText("-");
                TnmDokter.setText("-");
            } else {
                Tnip.setText(akses.getkode());
                TnmDokter.setText(Sequel.cariIsi("select nama from pegawai where nik='" + Tnip.getText() + "'"));
            }
        } else {
            Tnip.setText("-");
            TnmDokter.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void TriwAlergiObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwAlergiObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TobatSaatIni.requestFocus();
        }
    }//GEN-LAST:event_TriwAlergiObatKeyPressed

    private void TobatSaatIniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatSaatIniKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TriwAnastesi.requestFocus();
        }
    }//GEN-LAST:event_TobatSaatIniKeyPressed

    private void TriwAnastesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwAnastesiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tkepala.requestFocus();
        }
    }//GEN-LAST:event_TriwAnastesiKeyPressed

    private void TpemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpemeriksaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tdiagnosa.requestFocus();
        }
    }//GEN-LAST:event_TpemeriksaanKeyPressed

    private void TdiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            ChkSedasi.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaKeyPressed

    private void TcatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            ChkAsa1.requestFocus();
        }
    }//GEN-LAST:event_TcatatanKeyPressed

    private void TpenyulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpenyulitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbJam.requestFocus();
        }
    }//GEN-LAST:event_TpenyulitKeyPressed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void TkepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkepalaKeyPressed
        Valid.pindah(evt, Tkepala, Tsklera);
    }//GEN-LAST:event_TkepalaKeyPressed

    private void TskleraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskleraKeyPressed
        Valid.pindah(evt, Tkepala, Tconjung);
    }//GEN-LAST:event_TskleraKeyPressed

    private void TconjungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TconjungKeyPressed
        Valid.pindah(evt, Tsklera, Tleher);
    }//GEN-LAST:event_TconjungKeyPressed

    private void TleherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TleherKeyPressed
        Valid.pindah(evt, Tconjung, Tparu);
    }//GEN-LAST:event_TleherKeyPressed

    private void TparuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TparuKeyPressed
        Valid.pindah(evt, Tleher, Tjantung);
    }//GEN-LAST:event_TparuKeyPressed

    private void TjantungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjantungKeyPressed
        Valid.pindah(evt, Tparu, Tabdomen);
    }//GEN-LAST:event_TjantungKeyPressed

    private void TabdomenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabdomenKeyPressed
        Valid.pindah(evt, Tjantung, Tekstremitas);
    }//GEN-LAST:event_TabdomenKeyPressed

    private void TekstremitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TekstremitasKeyPressed
        Valid.pindah(evt, Tabdomen, Tgcs);
    }//GEN-LAST:event_TekstremitasKeyPressed

    private void TgcsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsKeyPressed
        Valid.pindah(evt, Tekstremitas, Ttd);
    }//GEN-LAST:event_TgcsKeyPressed

    private void TtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdKeyPressed
        Valid.pindah(evt, Tgcs, Trr);
    }//GEN-LAST:event_TtdKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        Valid.pindah(evt, Ttd, Tnadi);
    }//GEN-LAST:event_TrrKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        Valid.pindah(evt, Trr, Tsuhu);
    }//GEN-LAST:event_TnadiKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        Valid.pindah(evt, Tnadi, Ttb);
    }//GEN-LAST:event_TsuhuKeyPressed

    private void TtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbKeyPressed
        Valid.pindah(evt, Tsuhu, Tbb);
    }//GEN-LAST:event_TtbKeyPressed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        Valid.pindah(evt, Ttb, Tvas);
    }//GEN-LAST:event_TbbKeyPressed

    private void TvasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TvasKeyPressed
        Valid.pindah(evt, Ttb, cmbHilang);
    }//GEN-LAST:event_TvasKeyPressed

    private void ChkSedasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSedasiActionPerformed
        Tobat1.setText("");
        Tobat2.setText("");
        Tobat3.setText("");
        
        if (ChkSedasi.isSelected() == true) {
            Tobat1.setEnabled(true);
            Tobat2.setEnabled(true);
            Tobat3.setEnabled(true);
            Tobat1.requestFocus();
        } else {
            Tobat1.setEnabled(false);
            Tobat2.setEnabled(false);
            Tobat3.setEnabled(false);
        }
    }//GEN-LAST:event_ChkSedasiActionPerformed

    private void ChkGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkGAActionPerformed
        Tga.setText("");

        if (ChkGA.isSelected() == true) {
            Tga.setEnabled(true);
            Tga.requestFocus();
        } else {
            Tga.setEnabled(false);
        }
    }//GEN-LAST:event_ChkGAActionPerformed

    private void ChkLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkLainActionPerformed
        Tlain.setText("");
        
        if (ChkLain.isSelected() == true) {
            Tlain.setEnabled(true);
            Tlain.requestFocus();
        } else {
            Tlain.setEnabled(false);
        }
    }//GEN-LAST:event_ChkLainActionPerformed

    private void Tobat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tobat1KeyPressed
        Valid.pindah(evt, Tobat1, Tobat2);
    }//GEN-LAST:event_Tobat1KeyPressed

    private void Tobat2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tobat2KeyPressed
        Valid.pindah(evt, Tobat1, Tobat3);
    }//GEN-LAST:event_Tobat2KeyPressed

    private void Tobat3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tobat3KeyPressed
        Valid.pindah(evt, Tobat2, Tobat3);
    }//GEN-LAST:event_Tobat3KeyPressed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMAsesmenPraSedasi");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void BtnPasteObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteObatActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (TobatSaatIni.getText().equals("")) {
                TobatSaatIni.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                TobatSaatIni.setText(TobatSaatIni.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteObatActionPerformed

    private void BtnPastePenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPastePenunjangActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (Tpemeriksaan.getText().equals("")) {
                Tpemeriksaan.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                Tpemeriksaan.setText(Tpemeriksaan.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPastePenunjangActionPerformed

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            if (!sttsrawat.equals("")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("RMAsesmenPraSedasi");
                DlgCatatanResep form = new DlgCatatanResep(null, false);
                form.isCek();
                form.setData(TNoRw.getText(), sttsrawat);
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
                tbSedasi.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnResepActionPerformed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (tbRiwayat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data riwayat asesmen pra sedasi masih kosong...!!!");
            tbRiwayat.requestFocus();
        } else {
            if (tbRiwayat.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from asesmen_pra_sedasi_histori where waktu_eksekusi=?", 1, new String[]{
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
            RMAsesmenPraSedasi dialog = new RMAsesmenPraSedasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnNotepad;
    private widget.Button BtnPasteObat;
    private widget.Button BtnPastePenunjang;
    private widget.Button BtnPrint;
    private widget.Button BtnResep;
    private widget.Button BtnRestor;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAccor;
    public widget.CekBox ChkAsa1;
    public widget.CekBox ChkAsa2;
    public widget.CekBox ChkAsa3;
    public widget.CekBox ChkAsa4;
    public widget.CekBox ChkBlok;
    public widget.CekBox ChkDua;
    public widget.CekBox ChkEkg;
    public widget.CekBox ChkEmergency;
    public widget.CekBox ChkEmpat;
    public widget.CekBox ChkEpidural;
    public widget.CekBox ChkGA;
    public widget.CekBox ChkHcu;
    public widget.CekBox ChkIcu;
    public widget.CekBox ChkKaudal;
    public widget.CekBox ChkLain;
    public widget.CekBox ChkNibp;
    public widget.CekBox ChkRakhus;
    public widget.CekBox ChkRalan;
    public widget.CekBox ChkRanap;
    public widget.CekBox ChkRegional;
    public widget.CekBox ChkSatu;
    public widget.CekBox ChkSedasi;
    public widget.CekBox ChkSpinal;
    public widget.CekBox ChkSpo;
    public widget.CekBox ChkTemp;
    public widget.CekBox ChkTiga;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.PanelBiasa FormMenu;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHapus;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private javax.swing.JMenuItem MnRiwayatData;
    private widget.PanelBiasa PanelAccor;
    private widget.PanelBiasa PanelAsesmen;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll6;
    private widget.TextBox TCari;
    private widget.TextBox TCari2;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tabdomen;
    private widget.TextBox Tbb;
    private widget.TextArea Tcatatan;
    private widget.TextBox Tconjung;
    private widget.TextArea Tdiagnosa;
    private widget.TextBox Tekstremitas;
    private widget.TextBox Tga;
    private widget.TextBox Tgcs;
    private widget.TextArea Thasil;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tjantung;
    private widget.TextBox Tkepala;
    private widget.TextBox Tlain;
    private widget.TextBox Tleher;
    private widget.TextBox Tnadi;
    private widget.TextBox Tnip;
    private widget.TextBox TnmDokter;
    private widget.TextBox Tobat1;
    private widget.TextBox Tobat2;
    private widget.TextBox Tobat3;
    private widget.TextArea TobatSaatIni;
    private widget.TextBox Tparu;
    private widget.TextArea Tpemeriksaan;
    private widget.TextArea Tpenyulit;
    private widget.TextBox TrgRawat;
    private widget.TextArea TriwAlergiObat;
    private widget.TextArea TriwAnastesi;
    private widget.TextBox Trr;
    private widget.TextBox Tsklera;
    private widget.TextBox Tsuhu;
    private widget.TextBox Ttb;
    private widget.TextBox Ttd;
    private widget.Tanggal TtglPuasa;
    private widget.Tanggal TtglRencana;
    private widget.TextBox Tvas;
    private javax.swing.JDialog WindowRiwayat;
    private widget.CekBox chkSaya;
    private widget.ComboBox cmbBuka;
    private widget.ComboBox cmbDenyut;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbGerakan;
    private widget.ComboBox cmbGigi;
    private widget.ComboBox cmbHilang;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJarak;
    private widget.ComboBox cmbKejang;
    private widget.ComboBox cmbLeher;
    private widget.ComboBox cmbMasalah;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbObes;
    private widget.ComboBox cmbSakit;
    private widget.ComboBox cmbSedang;
    private widget.ComboBox cmbSesak;
    private widget.ComboBox cmbStroke;
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
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
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
    private widget.Label label42;
    private widget.Label label43;
    private widget.Label label44;
    private widget.Label label45;
    private widget.Label label46;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.ScrollPane scrollPane18;
    private widget.ScrollPane scrollPane19;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbCPPT;
    private widget.Table tbRiwayat;
    private widget.Table tbSedasi;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT ap.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                    + "DATE_FORMAT(ap.tgl_mulai,'%d-%m-%Y') tglPuasa, TIME_FORMAT(ap.jam_mulai,'%H:%i Wita') jamPuasa, DATE_FORMAT(ap.tgl_rencana,'%d-%m-%Y') tglOperasi, "
                    + "TIME_FORMAT(ap.jam_rencana,'%H:%i Wita') jamOperasi, pg.nama nmDokter FROM asesmen_pra_sedasi ap "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=ap.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg on pg.nik=ap.nip_dokter WHERE "
                    + "ap.tgl_rencana BETWEEN ? AND ? AND ap.no_rawat LIKE ? OR "
                    + "ap.tgl_rencana BETWEEN ? AND ? AND p.no_rkm_medis LIKE ? OR "
                    + "ap.tgl_rencana BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                    + "ap.tgl_rencana BETWEEN ? AND ? AND ap.ruang_rawat LIKE ? OR "
                    + "ap.tgl_rencana BETWEEN ? AND ? AND ap.diagnosa LIKE ? OR "
                    + "ap.tgl_rencana BETWEEN ? AND ? AND ap.penyulit LIKE ? OR "
                    + "ap.tgl_rencana BETWEEN ? AND ? AND pg.nama LIKE ? ORDER BY ap.tgl_rencana desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgllahir"),
                        rs.getString("tglPuasa"),
                        rs.getString("jamPuasa"),
                        rs.getString("tglOperasi"),
                        rs.getString("jamOperasi"),
                        rs.getString("ruang_rawat"),
                        rs.getString("diagnosa"),
                        rs.getString("penyulit"),                        
                        rs.getString("riwayat_alergi_obat"),
                        rs.getString("obat_saat_ini"),
                        rs.getString("riwayat_anastesi"),
                        rs.getString("kepala"),
                        rs.getString("sklera"),
                        rs.getString("conjungtiva"),
                        rs.getString("leher"),
                        rs.getString("paru"),
                        rs.getString("jantung"),
                        rs.getString("abdomen"),
                        rs.getString("extremitas"),
                        rs.getString("gcs"),
                        rs.getString("tensi"),
                        rs.getString("rr"),
                        rs.getString("nadi"),
                        rs.getString("suhu"),
                        rs.getString("tb"),
                        rs.getString("bb"),
                        rs.getString("vas"),
                        rs.getString("hilangnya_gigi"),
                        rs.getString("masalah"),
                        rs.getString("leher_pendek"),
                        rs.getString("stroke"),
                        rs.getString("sesak_nafas"),
                        rs.getString("buka_mulut"),
                        rs.getString("jarak"),
                        rs.getString("sakit_dada"),
                        rs.getString("denyut"),
                        rs.getString("sedang_hamil"),
                        rs.getString("kejang"),
                        rs.getString("obesitas"),
                        rs.getString("gigi_palsu"),
                        rs.getString("gerakan_leher"),
                        rs.getString("malampati1"),
                        rs.getString("malampati2"),
                        rs.getString("malampati3"),
                        rs.getString("malampati4"),
                        rs.getString("pemeriksaan_penunjang"),
                        rs.getString("diagnosa"),
                        rs.getString("sedasi"),
                        rs.getString("obat1"),
                        rs.getString("obat2"),
                        rs.getString("obat3"),
                        rs.getString("ga"),
                        rs.getString("keterangan_ga"),
                        rs.getString("regional"),
                        rs.getString("spinal"),
                        rs.getString("epidural"),
                        rs.getString("kaudal"),
                        rs.getString("blok_perifer"),
                        rs.getString("ekg"),
                        rs.getString("spo2"),
                        rs.getString("nibp"),
                        rs.getString("temp"),
                        rs.getString("lain_lain"),
                        rs.getString("ket_lainlain"),
                        rs.getString("rawat_inap"),
                        rs.getString("rawat_jalan"),
                        rs.getString("rawat_khusus"),
                        rs.getString("icu"),
                        rs.getString("hcu"),
                        rs.getString("catatan"),
                        rs.getString("asa1"),
                        rs.getString("asa2"),
                        rs.getString("asa3"),
                        rs.getString("asa4"),
                        rs.getString("emergency"),
                        rs.getString("penyulit"),
                        rs.getString("jam_mulai"),
                        rs.getString("tgl_mulai"),
                        rs.getString("jam_rencana"),
                        rs.getString("tgl_rencana"),
                        rs.getString("nip_dokter"),
                        rs.getString("waktu_simpan"),
                        rs.getString("nmDokter")
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
        TNoRw.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        TrgRawat.setText("");
        TriwAlergiObat.setText("");
        TobatSaatIni.setText("");
        TriwAnastesi.setText("");
        Tkepala.setText("");
        Tsklera.setText("");
        Tconjung.setText("");
        Tleher.setText("");
        Tparu.setText("");
        Tjantung.setText("");
        Tabdomen.setText("");
        Tekstremitas.setText("");
        Tgcs.setText("");
        Ttd.setText("");
        Trr.setText("");
        Tnadi.setText("");
        Tsuhu.setText("");
        Ttb.setText("");
        Tbb.setText("");
        Tvas.setText("");
        cmbHilang.setSelectedIndex(0);
        cmbMasalah.setSelectedIndex(0);
        cmbLeher.setSelectedIndex(0);
        cmbStroke.setSelectedIndex(0);
        cmbSesak.setSelectedIndex(0);
        cmbBuka.setSelectedIndex(0);
        cmbJarak.setSelectedIndex(0);
        cmbSakit.setSelectedIndex(0);
        cmbDenyut.setSelectedIndex(0);
        cmbSedang.setSelectedIndex(0);
        cmbKejang.setSelectedIndex(0);
        cmbObes.setSelectedIndex(0);
        cmbGigi.setSelectedIndex(0);
        cmbGerakan.setSelectedIndex(0);
        ChkSatu.setSelected(false);
        ChkDua.setSelected(false);
        ChkTiga.setSelected(false);
        ChkEmpat.setSelected(false);
        Tpemeriksaan.setText("");
        Tdiagnosa.setText("");
        ChkSedasi.setSelected(false);
        Tobat1.setText("");
        Tobat2.setText("");
        Tobat3.setText("");
        Tobat1.setEnabled(false);
        Tobat2.setEnabled(false);
        Tobat3.setEnabled(false);
        ChkGA.setSelected(false);
        Tga.setText("");
        Tga.setEnabled(false);
        ChkRegional.setSelected(false);
        ChkSpinal.setSelected(false);
        ChkEpidural.setSelected(false);
        ChkKaudal.setSelected(false);
        ChkBlok.setSelected(false);
        ChkEkg.setSelected(false);
        ChkSpo.setSelected(false);
        ChkNibp.setSelected(false);
        ChkTemp.setSelected(false);
        ChkLain.setSelected(false);
        Tlain.setText("");
        Tlain.setEnabled(false);
        ChkRanap.setSelected(false);
        ChkRalan.setSelected(false);
        ChkRakhus.setSelected(false);
        ChkIcu.setSelected(false);
        ChkHcu.setSelected(false);
        Tcatatan.setText("");
        ChkAsa1.setSelected(false);
        ChkAsa2.setSelected(false);
        ChkAsa3.setSelected(false);
        ChkAsa4.setSelected(false);
        ChkEmergency.setSelected(false);
        Tpenyulit.setText("");
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        TtglPuasa.setDate(new Date());
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        TtglRencana.setDate(new Date());
        Tnip.setText("-");
        TnmDokter.setText("-");
        chkSaya.setSelected(false);
        sttsrawat = "";
    }

    private void getData() {
        variabelBersih();
        if (tbSedasi.getSelectedRow() != -1) {
            TNoRw.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 0).toString());
            TNoRM.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 1).toString());
            TPasien.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 8).toString());
            TriwAlergiObat.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 11).toString());
            TobatSaatIni.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 12).toString());
            TriwAnastesi.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 13).toString());            
            Tkepala.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 14).toString());
            Tsklera.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 15).toString());
            Tconjung.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 16).toString());
            Tleher.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 17).toString());            
            Tparu.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 18).toString());
            Tjantung.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 19).toString());
            Tabdomen.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 20).toString());
            Tekstremitas.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 21).toString());
            Tgcs.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 22).toString());
            Ttd.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 23).toString());
            Trr.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 24).toString());
            Tnadi.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 25).toString());
            Tsuhu.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 26).toString());
            Ttb.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 27).toString());
            Tbb.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 28).toString());
            Tvas.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 29).toString());
            cmbHilang.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 30).toString());
            cmbMasalah.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 31).toString());
            cmbLeher.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 32).toString());
            cmbStroke.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 33).toString());
            cmbSesak.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 34).toString());
            cmbBuka.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 35).toString());
            cmbJarak.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 36).toString());
            cmbSakit.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 37).toString());
            cmbDenyut.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 38).toString());
            cmbSedang.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 39).toString());
            cmbKejang.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 40).toString());
            cmbObes.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 41).toString());
            cmbGigi.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 42).toString());
            cmbGerakan.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 43).toString());            
            mal1 = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 44).toString();
            mal2 = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 45).toString();
            mal3 = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 46).toString();
            mal4 = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 47).toString();
            Tpemeriksaan.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 48).toString());
            Tdiagnosa.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 49).toString());            
            sedasi = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 50).toString();            
            Tobat1.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 51).toString());
            Tobat2.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 52).toString());
            Tobat3.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 53).toString());
            ga = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 54).toString();
            Tga.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 55).toString());
            regional = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 56).toString();
            spinal = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 57).toString();
            epidural = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 58).toString();
            kaudal = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 59).toString();
            blok = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 60).toString();
            ekg = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 61).toString();
            spo2 = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 62).toString();            
            nibp = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 63).toString();
            temp = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 64).toString();
            lainlain = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 65).toString();
            Tlain.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 66).toString());
            ranap = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 67).toString();
            ralan = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 68).toString();
            rakhus = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 69).toString();
            icu = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 70).toString();            
            hcu = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 71).toString();
            Tcatatan.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 72).toString());            
            asa1 = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 73).toString();
            asa2 = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 74).toString();
            asa3 = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 75).toString();
            asa4 = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 76).toString();
            emer = tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 77).toString();
            Tpenyulit.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 78).toString());            
            cmbJam.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 79).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 79).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 79).toString().substring(6, 8));
            Valid.SetTgl(TtglPuasa, tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 80).toString());            
            cmbJam1.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 81).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 81).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 81).toString().substring(6, 8));
            Valid.SetTgl(TtglRencana, tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 82).toString());
            Tnip.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 83).toString());            
            TnmDokter.setText(tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 85).toString());
            sttsrawat = Sequel.cariIsi("select ifnull(status_lanjut,'') from reg_periksa where no_rawat='" + TNoRw.getText() + "'");
            dataCek();
        }
    }

    public void setNoRm(String norwt, String norm, String nmpasien, String rgrawat, String sttsrwt) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TrgRawat.setText(rgrawat);        
        TCari.setText(norwt);
        sttsrawat = sttsrwt;
        
        if (akses.getadmin() == true) {
            Tnip.setText("-");
            TnmDokter.setText("-");
        } else {
            Tnip.setText(akses.getkode());
            TnmDokter.setText(Sequel.cariIsi("select nama from pegawai where nik='" + Tnip.getText() + "'"));
        }
    }    
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getresep_dokter());
        BtnHapus.setEnabled(akses.getresep_dokter());
        BtnEdit.setEnabled(akses.getresep_dokter());
        BtnPrint.setEnabled(akses.getresep_dokter());
        MnRiwayatData.setEnabled(akses.getadmin());
    }

    private void ganti() {
        cekData();
        if (Sequel.mengedittf("asesmen_pra_sedasi", "waktu_simpan=?", "riwayat_alergi_obat=?, obat_saat_ini=?, riwayat_anastesi=?, kepala=?, "
                + "sklera=?, conjungtiva=?, leher=?, paru=?, jantung=?, abdomen=?, extremitas=?, gcs=?, tensi=?, rr=?, nadi=?, suhu=?, tb=?, bb=?, vas=?, hilangnya_gigi=?, "
                + "masalah=?, leher_pendek=?, stroke=?, sesak_nafas=?, buka_mulut=?, jarak=?, sakit_dada=?, denyut=?, sedang_hamil=?, kejang=?, obesitas=?, gigi_palsu=?, "
                + "gerakan_leher=?, malampati1=?, malampati2=?, malampati3=?, malampati4=?, pemeriksaan_penunjang=?, diagnosa=?, sedasi=?, obat1=?, obat2=?, obat3=?, ga=?, "
                + "keterangan_ga=?, regional=?, spinal=?, epidural=?, kaudal=?, blok_perifer=?, ekg=?, spo2=?, nibp=?, temp=?, lain_lain=?, ket_lainlain=?, rawat_inap=?, "
                + "rawat_jalan=?, rawat_khusus=?, icu=?, hcu=?, catatan=?, asa1=?, asa2=?, asa3=?, asa4=?, emergency=?, penyulit=?, jam_mulai=?, tgl_mulai=?, jam_rencana=?, "
                + "tgl_rencana=?, nip_dokter=?", 74, new String[]{
                    TriwAlergiObat.getText(), TobatSaatIni.getText(), TriwAnastesi.getText(), Tkepala.getText(), Tsklera.getText(),
                    Tconjung.getText(), Tleher.getText(), Tparu.getText(), Tjantung.getText(), Tabdomen.getText(), Tekstremitas.getText(), Tgcs.getText(), Ttd.getText(),
                    Trr.getText(), Tnadi.getText(), Tsuhu.getText(), Ttb.getText(), Tbb.getText(), Tvas.getText(), cmbHilang.getSelectedItem().toString(),
                    cmbMasalah.getSelectedItem().toString(), cmbLeher.getSelectedItem().toString(), cmbStroke.getSelectedItem().toString(), cmbSesak.getSelectedItem().toString(),
                    cmbBuka.getSelectedItem().toString(), cmbJarak.getSelectedItem().toString(), cmbSakit.getSelectedItem().toString(), cmbDenyut.getSelectedItem().toString(),
                    cmbSedang.getSelectedItem().toString(), cmbKejang.getSelectedItem().toString(), cmbObes.getSelectedItem().toString(), cmbGigi.getSelectedItem().toString(),
                    cmbGerakan.getSelectedItem().toString(), mal1, mal2, mal3, mal4, Tpemeriksaan.getText(), Tdiagnosa.getText(), sedasi, Tobat1.getText(), Tobat2.getText(),
                    Tobat3.getText(), ga, Tga.getText(), regional, spinal, epidural, kaudal, blok, ekg, spo2, nibp, temp, lainlain, Tlain.getText(), ranap, ralan, rakhus,
                    icu, hcu, Tcatatan.getText(), asa1, asa2, asa3, asa4, emer, Tpenyulit.getText(), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                    Valid.SetTgl(TtglPuasa.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                    Valid.SetTgl(TtglRencana.getSelectedItem() + ""), Tnip.getText(),
                    tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 84).toString()
                }) == true) {

            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
            TabRawat.setSelectedIndex(1);
        }
    }
    
    private void cekData() {
        if (ChkSatu.isSelected() == true) {
            mal1 = "ya";
        } else {
            mal1 = "tidak";
        }
        
        if (ChkDua.isSelected() == true) {
            mal2 = "ya";
        } else {
            mal2 = "tidak";
        }
        
        if (ChkTiga.isSelected() == true) {
            mal3 = "ya";
        } else {
            mal3 = "tidak";
        }
        
        if (ChkEmpat.isSelected() == true) {
            mal4 = "ya";
        } else {
            mal4 = "tidak";
        }
        
        if (ChkSedasi.isSelected() == true) {
            sedasi = "ya";
        } else {
            sedasi = "tidak";
        }
        
        if (ChkGA.isSelected() == true) {
            ga = "ya";
        } else {
            ga = "tidak";
        }
        
        if (ChkRegional.isSelected() == true) {
            regional = "ya";
        } else {
            regional = "tidak";
        }
        
        if (ChkSpinal.isSelected() == true) {
            spinal = "ya";
        } else {
            spinal = "tidak";
        }
        
        if (ChkEpidural.isSelected() == true) {
            epidural = "ya";
        } else {
            epidural = "tidak";
        }
        
        if (ChkKaudal.isSelected() == true) {
            kaudal = "ya";
        } else {
            kaudal = "tidak";
        }
        
        if (ChkBlok.isSelected() == true) {
            blok = "ya";
        } else {
            blok = "tidak";
        }
        
        if (ChkEkg.isSelected() == true) {
            ekg = "ya";
        } else {
            ekg = "tidak";
        }
        
        if (ChkSpo.isSelected() == true) {
            spo2 = "ya";
        } else {
            spo2 = "tidak";
        }
        
        if (ChkNibp.isSelected() == true) {
            nibp = "ya";
        } else {
            nibp = "tidak";
        }
        
        if (ChkTemp.isSelected() == true) {
            temp = "ya";
        } else {
            temp = "tidak";
        }
        
        if (ChkLain.isSelected() == true) {
            lainlain = "ya";
        } else {
            lainlain = "tidak";
        }
        
        if (ChkRanap.isSelected() == true) {
            ranap = "ya";
        } else {
            ranap = "tidak";
        }
        
        if (ChkRalan.isSelected() == true) {
            ralan = "ya";
        } else {
            ralan = "tidak";
        }
        
        if (ChkRakhus.isSelected() == true) {
            rakhus = "ya";
        } else {
            rakhus = "tidak";
        }
        
        if (ChkIcu.isSelected() == true) {
            icu = "ya";
        } else {
            icu = "tidak";
        }
        
        if (ChkHcu.isSelected() == true) {
            hcu = "ya";
        } else {
            hcu = "tidak";
        }
        
        if (ChkAsa1.isSelected() == true) {
            asa1 = "ya";
        } else {
            asa1 = "tidak";
        }
        
        if (ChkAsa2.isSelected() == true) {
            asa2 = "ya";
        } else {
            asa2 = "tidak";
        }
        
        if (ChkAsa3.isSelected() == true) {
            asa3 = "ya";
        } else {
            asa3 = "tidak";
        }
        
        if (ChkAsa4.isSelected() == true) {
            asa4 = "ya";
        } else {
            asa4 = "tidak";
        }
        
        if (ChkEmergency.isSelected() == true) {
            emer = "ya";
        } else {
            emer = "tidak";
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
            if (Sequel.queryu2tf("delete from asesmen_pra_sedasi where waktu_simpan=?", 1, new String[]{
                tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 84).toString()
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
        if (mal1.equals("ya")) {
            ChkSatu.setSelected(true);
        } else {
            ChkSatu.setSelected(false);
        }
        
        if (mal2.equals("ya")) {
            ChkDua.setSelected(true);
        } else {
            ChkDua.setSelected(false);
        }
        
        if (mal3.equals("ya")) {
            ChkTiga.setSelected(true);
        } else {
            ChkTiga.setSelected(false);
        }
        
        if (mal4.equals("ya")) {
            ChkEmpat.setSelected(true);
        } else {
            ChkEmpat.setSelected(false);
        }
        
        if (sedasi.equals("ya")) {
            ChkSedasi.setSelected(true);
            Tobat1.setEnabled(true);
            Tobat2.setEnabled(true);
            Tobat3.setEnabled(true);
        } else {
            ChkSedasi.setSelected(false);
            Tobat1.setEnabled(false);
            Tobat2.setEnabled(false);
            Tobat3.setEnabled(false);
        }
        
        if (ga.equals("ya")) {
            ChkGA.setSelected(true);
            Tga.setEnabled(true);
        } else {
            ChkGA.setSelected(false);
            Tga.setEnabled(false);
        }
        
        if (regional.equals("ya")) {
            ChkRegional.setSelected(true);
        } else {
            ChkRegional.setSelected(false);
        }
        
        if (spinal.equals("ya")) {
            ChkSpinal.setSelected(true);
        } else {
            ChkSpinal.setSelected(false);
        }
        
        if (epidural.equals("ya")) {
            ChkEpidural.setSelected(true);
        } else {
            ChkEpidural.setSelected(false);
        }
        
        if (kaudal.equals("ya")) {
            ChkKaudal.setSelected(true);
        } else {
            ChkKaudal.setSelected(false);
        }
        
        if (blok.equals("ya")) {
            ChkBlok.setSelected(true);
        } else {
            ChkBlok.setSelected(false);
        }
        
        if (ekg.equals("ya")) {
            ChkEkg.setSelected(true);
        } else {
            ChkEkg.setSelected(false);
        }
        
        if (spo2.equals("ya")) {
            ChkSpo.setSelected(true);
        } else {
            ChkSpo.setSelected(false);
        }
        
        if (nibp.equals("ya")) {
            ChkNibp.setSelected(true);
        } else {
            ChkNibp.setSelected(false);
        }
        
        if (temp.equals("ya")) {
            ChkTemp.setSelected(true);
        } else {
            ChkTemp.setSelected(false);
        }
        
        if (lainlain.equals("ya")) {
            ChkLain.setSelected(true);
            Tlain.setEnabled(true);
        } else {
            ChkLain.setSelected(false);
            Tlain.setEnabled(false);
        }
        
        if (ranap.equals("ya")) {
            ChkRanap.setSelected(true);
        } else {
            ChkRanap.setSelected(false);
        }
        
        if (ralan.equals("ya")) {
            ChkRalan.setSelected(true);
        } else {
            ChkRalan.setSelected(false);
        }
        
        if (rakhus.equals("ya")) {
            ChkRakhus.setSelected(true);
        } else {
            ChkRakhus.setSelected(false);
        }
        
        if (icu.equals("ya")) {
            ChkIcu.setSelected(true);
        } else {
            ChkIcu.setSelected(false);
        }
        
        if (hcu.equals("ya")) {
            ChkHcu.setSelected(true);
        } else {
            ChkHcu.setSelected(false);
        }
        
        if (asa1.equals("ya")) {
            ChkAsa1.setSelected(true);
        } else {
            ChkAsa1.setSelected(false);
        }
        
        if (asa2.equals("ya")) {
            ChkAsa2.setSelected(true);
        } else {
            ChkAsa2.setSelected(false);
        }
        
        if (asa3.equals("ya")) {
            ChkAsa3.setSelected(true);
        } else {
            ChkAsa3.setSelected(false);
        }
        
        if (asa4.equals("ya")) {
            ChkAsa4.setSelected(true);
        } else {
            ChkAsa4.setSelected(false);
        }
        
        if (emer.equals("ya")) {
            ChkEmergency.setSelected(true);
        } else {
            ChkEmergency.setSelected(false);
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
        if (Sequel.menyimpantf("asesmen_pra_sedasi_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 79, new String[]{
                    TNoRw.getText(), TrgRawat.getText(), TriwAlergiObat.getText(), TobatSaatIni.getText(), TriwAnastesi.getText(), Tkepala.getText(), Tsklera.getText(),
                    Tconjung.getText(), Tleher.getText(), Tparu.getText(), Tjantung.getText(), Tabdomen.getText(), Tekstremitas.getText(), Tgcs.getText(), Ttd.getText(),
                    Trr.getText(), Tnadi.getText(), Tsuhu.getText(), Ttb.getText(), Tbb.getText(), Tvas.getText(), cmbHilang.getSelectedItem().toString(),
                    cmbMasalah.getSelectedItem().toString(), cmbLeher.getSelectedItem().toString(), cmbStroke.getSelectedItem().toString(), cmbSesak.getSelectedItem().toString(),
                    cmbBuka.getSelectedItem().toString(), cmbJarak.getSelectedItem().toString(), cmbSakit.getSelectedItem().toString(), cmbDenyut.getSelectedItem().toString(),
                    cmbSedang.getSelectedItem().toString(), cmbKejang.getSelectedItem().toString(), cmbObes.getSelectedItem().toString(), cmbGigi.getSelectedItem().toString(),
                    cmbGerakan.getSelectedItem().toString(), mal1, mal2, mal3, mal4, Tpemeriksaan.getText(), Tdiagnosa.getText(), sedasi, Tobat1.getText(), Tobat2.getText(),
                    Tobat3.getText(), ga, Tga.getText(), regional, spinal, epidural, kaudal, blok, ekg, spo2, nibp, temp, lainlain, Tlain.getText(), ranap, ralan, rakhus,
                    icu, hcu, Tcatatan.getText(), asa1, asa2, asa3, asa4, emer, Tpenyulit.getText(), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                    Valid.SetTgl(TtglPuasa.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                    Valid.SetTgl(TtglRencana.getSelectedItem() + ""), Tnip.getText(), tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 84).toString(),
                    "hapus", user, Sequel.cariIsi("select now()")
                }) == true) {
            System.out.println("Asesmen Pra Sedasi Dihapus Berhasil Tersimpan Sebagai Data Histori..!!");
        }
    }
    
    private void gantiDisimpan() {
        cekData();
        if (Sequel.menyimpantf("asesmen_pra_sedasi_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 79, new String[]{
                    TNoRw.getText(), TrgRawat.getText(), TriwAlergiObat.getText(), TobatSaatIni.getText(), TriwAnastesi.getText(), Tkepala.getText(), Tsklera.getText(),
                    Tconjung.getText(), Tleher.getText(), Tparu.getText(), Tjantung.getText(), Tabdomen.getText(), Tekstremitas.getText(), Tgcs.getText(), Ttd.getText(),
                    Trr.getText(), Tnadi.getText(), Tsuhu.getText(), Ttb.getText(), Tbb.getText(), Tvas.getText(), cmbHilang.getSelectedItem().toString(),
                    cmbMasalah.getSelectedItem().toString(), cmbLeher.getSelectedItem().toString(), cmbStroke.getSelectedItem().toString(), cmbSesak.getSelectedItem().toString(),
                    cmbBuka.getSelectedItem().toString(), cmbJarak.getSelectedItem().toString(), cmbSakit.getSelectedItem().toString(), cmbDenyut.getSelectedItem().toString(),
                    cmbSedang.getSelectedItem().toString(), cmbKejang.getSelectedItem().toString(), cmbObes.getSelectedItem().toString(), cmbGigi.getSelectedItem().toString(),
                    cmbGerakan.getSelectedItem().toString(), mal1, mal2, mal3, mal4, Tpemeriksaan.getText(), Tdiagnosa.getText(), sedasi, Tobat1.getText(), Tobat2.getText(),
                    Tobat3.getText(), ga, Tga.getText(), regional, spinal, epidural, kaudal, blok, ekg, spo2, nibp, temp, lainlain, Tlain.getText(), ranap, ralan, rakhus,
                    icu, hcu, Tcatatan.getText(), asa1, asa2, asa3, asa4, emer, Tpenyulit.getText(), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                    Valid.SetTgl(TtglPuasa.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                    Valid.SetTgl(TtglRencana.getSelectedItem() + ""), Tnip.getText(), tbSedasi.getValueAt(tbSedasi.getSelectedRow(), 84).toString(),
                    "ganti", user, Sequel.cariIsi("select now()")
                }) == true) {
            System.out.println("Asesmen Pra Sedasi Diganti Berhasil Tersimpan Sebagai Data Histori..!!");
        }
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode1);
        try {
            psrestor = koneksi.prepareStatement("SELECT IF(pg.nama='-','Admin Utama',pg.nama) pelaku, a.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                    + "date(a.waktu_simpan) tglsimpan, a.waktu_eksekusi, upper(concat('DI',a.status_data)) sttsdata, a.waktu_simpan FROM asesmen_pra_sedasi_histori a "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = a.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg ON pg.nik = a.nik_eksekutor WHERE "
                    + "date(a.waktu_simpan) between ? and ? and pg.nama like ? or "
                    + "date(a.waktu_simpan) between ? and ? and a.no_rawat like ? or "
                    + "date(a.waktu_simpan) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(a.waktu_simpan) between ? and ? and a.status_data like ? or "
                    + "date(a.waktu_simpan) between ? and ? and p.nm_pasien like ? order by a.waktu_eksekusi desc");
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
                        rsrestor.getString("tglsimpan"),
                        rsrestor.getString("waktu_eksekusi"),
                        rsrestor.getString("sttsdata"),
                        rsrestor.getString("waktu_simpan")
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
            ps2 = koneksi.prepareStatement("select * from asesmen_pra_sedasi_histori where "
                    + "waktu_eksekusi='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString() + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    try {
                        if (Sequel.menyimpantf("asesmen_pra_sedasi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 76, new String[]{
                                    rs2.getString("no_rawat"),
                                    rs2.getString("ruang_rawat"),
                                    rs2.getString("riwayat_alergi_obat"),
                                    rs2.getString("obat_saat_ini"),
                                    rs2.getString("riwayat_anastesi"),
                                    rs2.getString("kepala"),
                                    rs2.getString("sklera"),
                                    rs2.getString("conjungtiva"),
                                    rs2.getString("leher"),
                                    rs2.getString("paru"),
                                    rs2.getString("jantung"),
                                    rs2.getString("abdomen"),
                                    rs2.getString("extremitas"),
                                    rs2.getString("gcs"),
                                    rs2.getString("tensi"),
                                    rs2.getString("rr"),
                                    rs2.getString("nadi"),
                                    rs2.getString("suhu"),
                                    rs2.getString("tb"),
                                    rs2.getString("bb"),
                                    rs2.getString("vas"),
                                    rs2.getString("hilangnya_gigi"),
                                    rs2.getString("masalah"),
                                    rs2.getString("leher_pendek"),
                                    rs2.getString("stroke"),
                                    rs2.getString("sesak_nafas"),
                                    rs2.getString("buka_mulut"),
                                    rs2.getString("jarak"),
                                    rs2.getString("sakit_dada"),
                                    rs2.getString("denyut"),
                                    rs2.getString("sedang_hamil"),
                                    rs2.getString("kejang"),
                                    rs2.getString("obesitas"),
                                    rs2.getString("gigi_palsu"),
                                    rs2.getString("gerakan_leher"),
                                    rs2.getString("malampati1"),
                                    rs2.getString("malampati2"),
                                    rs2.getString("malampati3"),
                                    rs2.getString("malampati4"),
                                    rs2.getString("pemeriksaan_penunjang"),
                                    rs2.getString("diagnosa"),
                                    rs2.getString("sedasi"),
                                    rs2.getString("obat1"),
                                    rs2.getString("obat2"),
                                    rs2.getString("obat3"),
                                    rs2.getString("ga"),
                                    rs2.getString("keterangan_ga"),
                                    rs2.getString("regional"),
                                    rs2.getString("spinal"),
                                    rs2.getString("epidural"),
                                    rs2.getString("kaudal"),
                                    rs2.getString("blok_perifer"),
                                    rs2.getString("ekg"),
                                    rs2.getString("spo2"),
                                    rs2.getString("nibp"),
                                    rs2.getString("temp"),
                                    rs2.getString("lain_lain"),
                                    rs2.getString("ket_lainlain"),
                                    rs2.getString("rawat_inap"),
                                    rs2.getString("rawat_jalan"),
                                    rs2.getString("rawat_khusus"),
                                    rs2.getString("icu"),
                                    rs2.getString("hcu"),
                                    rs2.getString("catatan"),
                                    rs2.getString("asa1"),
                                    rs2.getString("asa2"),
                                    rs2.getString("asa3"),
                                    rs2.getString("asa4"),
                                    rs2.getString("emergency"),
                                    rs2.getString("penyulit"),
                                    rs2.getString("jam_mulai"),
                                    rs2.getString("tgl_mulai"),
                                    rs2.getString("jam_rencana"),
                                    rs2.getString("tgl_rencana"),
                                    rs2.getString("nip_dokter"),
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
        if (Sequel.queryu2tf("delete from asesmen_pra_sedasi where waktu_simpan=?", 1, new String[]{
            tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString()
        }) == true) {
            kembalikanData();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal dikembalikan..!!");
        }
    }
    
    private void variabelBersih() {
        mal1 = "";
        mal2 = "";
        mal3 = "";
        mal4 = "";
        sedasi = "";
        ga = "";
        regional = "";
        spinal = "";
        epidural = "";
        kaudal = "";
        blok = "";
        ekg = "";
        spo2 = "";
        nibp = "";
        temp = "";
        lainlain = "";
        ranap = "";
        ralan = "";
        rakhus = "";
        icu = "";
        hcu = "";
        asa1 = "";
        asa2 = "";
        asa3 = "";
        asa4 = "";
        emer = "";
        user = "";
        sttsrawat = "";
    }
}
