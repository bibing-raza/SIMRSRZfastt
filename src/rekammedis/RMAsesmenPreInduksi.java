package rekammedis;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import laporan.DlgHasilPenunjangMedis;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class RMAsesmenPreInduksi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps2, pps1, pps2, pps3, pps4, pps5, pps6, psrestor;
    private ResultSet rs, rs2, rrs1, rrs2, rrs3, rrs4, rrs5, rrs6, rsrestor;
    private int i = 0, x = 0, pilihan = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String user = "", supin = "", litotomi = "", lateral = "", prone = "", perlindungan = "", kanan = "", kiri = "", lainPosisi = "",
            oralPreme = "", im = "", iv = "",
            intravena = "", inhalasi = "",
            sesudah = "", oralIntu = "", traceos = "", blin = "", nasal = "", nasalKA = "", nasalKI = "", level = "", dengan = "", cuff = "",
            pack = "", sulitVenti = "", sulitIntu = "",
            spontan = "", kontrol = "", ventilator = "", lainVenti = "", fiksasi = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMAsesmenPreInduksi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "waktu_simpan", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Assesmen",
            "Pukul", "Tensi", "Respirasi", "Nadi", "Suhu", "EKG", "Lain-lain", "Assesmen", "Dokter Anestesi",
            "perencanaan", "akses_intravena", "cvc", "supine", "lithotomi", "lateral", "prone", "perlindungan_mata",
            "kanan", "lain", "ket_lain", "kiri", "oral_premedikasi", "im", "iv", "intravena", "inhalasi", "face_mask_no",
            "oro_no", "ett_no", "jenis_ett", "lma_no", "jenis_lma", "fiksasi1", "ket_traceostomi", "sesudah_tidur",
            "oral_intubasi", "traceostomi", "blind", "nasal", "nasal_ka", "nasal_ki", "level_ett", "dengan_margile",
            "cuff", "pack", "sulit_ventilasi", "ket_sulit_ventilasi", "sulit_intubasi", "ket_sulit_intubasi", "spontan",
            "kontrol", "ventilator", "ket_ventilator", "tv", "rr", "peep", "lain_ventilasi1", "ket_lain_ventilasi1",
            "broncoskopi", "glidescope", "lain_ventilasi2", "jenis", "lokasi", "jenis_jarum", "kateter", "fiksasi",
            "fiksasi2", "obat_obatan", "komplikasi", "nip_dokter", "tgl_asesmen", "jam_asesmen"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 79; i++) {
            TableColumn column = tbAsesmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(60);
            } else if (i == 8) {
                column.setPreferredWidth(70);
            } else if (i == 9) {
                column.setPreferredWidth(80);
            } else if (i == 10) {
                column.setPreferredWidth(60);
            } else if (i == 11) {
                column.setPreferredWidth(60);
            } else if (i == 12) {
                column.setPreferredWidth(200);
            } else if (i == 13) {
                column.setPreferredWidth(200);
            } else if (i == 14) {
                column.setPreferredWidth(200);
            } else if (i == 15) {
                column.setPreferredWidth(220);
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
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        
        tabMode2 = new DefaultTableModel(null, new Object[]{"No. RM", "Nama Pasien", "Data Template"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTemplate.setModel(tabMode2);
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

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        Ttensi.setDocument(new batasInput((byte) 7).getKata(Ttensi));
        Tsuhu.setDocument(new batasInput((byte) 7).getKata(Tsuhu));
        Tnadi.setDocument(new batasInput((byte) 7).getKata(Tnadi));
        Trespi.setDocument(new batasInput((byte) 7).getKata(Trespi));
        Tekg.setDocument(new batasInput((int) 200).getKata(Tekg));
        Tface.setDocument(new batasInput((int) 200).getKata(Tface));
        Toro.setDocument(new batasInput((int) 200).getKata(Toro));
        Tett.setDocument(new batasInput((int) 200).getKata(Tett));
        TjenisEtt.setDocument(new batasInput((int) 200).getKata(TjenisEtt));
        Tlma.setDocument(new batasInput((int) 200).getKata(Tlma));
        TjenisLma.setDocument(new batasInput((int) 200).getKata(TjenisLma));
        Ttraceostomi.setDocument(new batasInput((int) 200).getKata(Ttraceostomi));
        Tventilator.setDocument(new batasInput((int) 200).getKata(Tventilator));
        Ttv.setDocument(new batasInput((int) 200).getKata(Ttv));
        Trr.setDocument(new batasInput((int) 200).getKata(Trr));
        Tpeep.setDocument(new batasInput((int) 200).getKata(Tpeep));
        TlainVentiCek.setDocument(new batasInput((int) 200).getKata(TlainVentiCek));
        Tfiksasi.setDocument(new batasInput((byte) 7).getKata(Tfiksasi));
        
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
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMAsesmenPreInduksi")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        TnipDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
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
        WindowTemplate = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        jLabel36 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCopas = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        Scroll2 = new widget.ScrollPane();
        tbTemplate = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        Ttemplate = new widget.TextArea();
        internalFrame1 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        label15 = new widget.Label();
        TnipDokter = new widget.TextBox();
        TnmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        label17 = new widget.Label();
        jLabel67 = new widget.Label();
        Ttensi = new widget.TextBox();
        label104 = new widget.Label();
        Tsuhu = new widget.TextBox();
        jLabel23 = new widget.Label();
        Tnadi = new widget.TextBox();
        label43 = new widget.Label();
        Trespi = new widget.TextBox();
        label106 = new widget.Label();
        jLabel79 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        scrollPane13 = new widget.ScrollPane();
        Tperencanaan = new widget.TextArea();
        jLabel85 = new widget.Label();
        TtglAsesmen = new widget.Tanggal();
        jLabel64 = new widget.Label();
        Tekg = new widget.TextBox();
        jLabel65 = new widget.Label();
        Tlainlain = new widget.TextBox();
        jLabel66 = new widget.Label();
        cmbAsesmen = new widget.ComboBox();
        jLabel68 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        TposisiLain = new widget.TextArea();
        jLabel69 = new widget.Label();
        jLabel70 = new widget.Label();
        Tcvc = new widget.TextBox();
        chkSupin = new widget.CekBox();
        chkLitotomi = new widget.CekBox();
        chkLateral = new widget.CekBox();
        chkProne = new widget.CekBox();
        chkPerlindungan = new widget.CekBox();
        chkKanan = new widget.CekBox();
        chkKiri = new widget.CekBox();
        chkLain = new widget.CekBox();
        label18 = new widget.Label();
        chkOralPreme = new widget.CekBox();
        chkIm = new widget.CekBox();
        chkIv = new widget.CekBox();
        label20 = new widget.Label();
        chkIntravena = new widget.CekBox();
        chkInhalasi = new widget.CekBox();
        label21 = new widget.Label();
        jLabel71 = new widget.Label();
        Tface = new widget.TextBox();
        jLabel72 = new widget.Label();
        Toro = new widget.TextBox();
        jLabel73 = new widget.Label();
        Tett = new widget.TextBox();
        jLabel74 = new widget.Label();
        TjenisEtt = new widget.TextBox();
        jLabel75 = new widget.Label();
        Tfiksasi = new widget.TextBox();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        Tlma = new widget.TextBox();
        jLabel78 = new widget.Label();
        TjenisLma = new widget.TextBox();
        jLabel80 = new widget.Label();
        Ttraceostomi = new widget.TextBox();
        label22 = new widget.Label();
        chkSesudah = new widget.CekBox();
        chkOralIntubasi = new widget.CekBox();
        chkTraceostomi = new widget.CekBox();
        chkBlind = new widget.CekBox();
        chkNasal = new widget.CekBox();
        chkLevel = new widget.CekBox();
        chkNasalKa = new widget.CekBox();
        chkNasalKi = new widget.CekBox();
        chkDengan = new widget.CekBox();
        chkCuff = new widget.CekBox();
        chkPack = new widget.CekBox();
        chkSulitVenti = new widget.CekBox();
        chkSulitIntu = new widget.CekBox();
        TsulitVenti = new widget.TextBox();
        TsulitIntu = new widget.TextBox();
        label23 = new widget.Label();
        chkSpontan = new widget.CekBox();
        chkKontrol = new widget.CekBox();
        chkVentilator = new widget.CekBox();
        chkLainVenti = new widget.CekBox();
        TlainVentiCek = new widget.TextBox();
        Tventilator = new widget.TextBox();
        jLabel81 = new widget.Label();
        Ttv = new widget.TextBox();
        jLabel82 = new widget.Label();
        Trr = new widget.TextBox();
        jLabel83 = new widget.Label();
        Tpeep = new widget.TextBox();
        jLabel84 = new widget.Label();
        Tbroncos = new widget.TextBox();
        jLabel86 = new widget.Label();
        Tglides = new widget.TextBox();
        jLabel87 = new widget.Label();
        TlainVenti = new widget.TextBox();
        label24 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        TjenisTeknik = new widget.TextArea();
        jLabel88 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        TaksesIntra = new widget.TextArea();
        jLabel89 = new widget.Label();
        Tlokasi = new widget.TextBox();
        jLabel90 = new widget.Label();
        Tjarum = new widget.TextBox();
        jLabel91 = new widget.Label();
        cmbKateter = new widget.ComboBox();
        chkFiksasi = new widget.CekBox();
        TfiksasiKateter = new widget.TextBox();
        jLabel92 = new widget.Label();
        scrollPane17 = new widget.ScrollPane();
        Tobat = new widget.TextArea();
        jLabel93 = new widget.Label();
        scrollPane18 = new widget.ScrollPane();
        Tkomplikasi = new widget.TextArea();
        jLabel94 = new widget.Label();
        BtnPerencanaan = new widget.Button();
        BtnAkses = new widget.Button();
        BtnLain = new widget.Button();
        BtnJenis = new widget.Button();
        BtnObat = new widget.Button();
        BtnKomplikasi = new widget.Button();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelInput1 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbAsesmen = new widget.Table();
        panelGlass11 = new widget.panelisi();
        panelGlass12 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();

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

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Asesmen Pre Induksi Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-09-2024" }));
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

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-09-2024" }));
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

        WindowTemplate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTemplate.setName("WindowTemplate"); // NOI18N
        WindowTemplate.setUndecorated(true);
        WindowTemplate.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Asesmen Pre Induksi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        panelisi4.setBackground(new java.awt.Color(255, 150, 255));
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Key Word :");
        jLabel36.setName("jLabel36"); // NOI18N
        jLabel36.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel36.setRequestFocusEnabled(false);
        panelisi4.add(jLabel36);

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

        internalFrame5.add(panelisi4, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 250));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTemplate.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTemplate.setName("tbTemplate"); // NOI18N
        tbTemplate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTemplateMouseClicked(evt);
            }
        });
        Scroll2.setViewportView(tbTemplate);

        jPanel1.add(Scroll2);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        Ttemplate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Baca Template Dipilih ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Ttemplate.setColumns(20);
        Ttemplate.setRows(5);
        Ttemplate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ttemplate.setName("Ttemplate"); // NOI18N
        Scroll3.setViewportView(Ttemplate);

        jPanel1.add(Scroll3);

        internalFrame5.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        WindowTemplate.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Pre Induksi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(600, 402));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setToolTipText("Klik Kanan Pada Area Ini Untuk Melihat Hasil Pemeriksaan Penunjang Medis");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1392));
        FormInput.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 140, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(145, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(279, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(352, 10, 407, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 38, 140, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(145, 38, 615, 23);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("Dokter Anestesi :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(0, 1358, 140, 23);

        TnipDokter.setEditable(false);
        TnipDokter.setForeground(new java.awt.Color(0, 0, 0));
        TnipDokter.setName("TnipDokter"); // NOI18N
        TnipDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipDokter);
        TnipDokter.setBounds(145, 1358, 150, 23);

        TnmDokter.setEditable(false);
        TnmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter.setName("TnmDokter"); // NOI18N
        TnmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmDokter);
        TnmDokter.setBounds(298, 1358, 360, 23);

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
        BtnDokter.setBounds(660, 1358, 28, 23);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Posisi :");
        label17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label17);
        label17.setBounds(0, 405, 140, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Tekanan Darah :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 94, 140, 23);

        Ttensi.setForeground(new java.awt.Color(0, 0, 0));
        Ttensi.setName("Ttensi"); // NOI18N
        Ttensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtensiKeyPressed(evt);
            }
        });
        FormInput.add(Ttensi);
        Ttensi.setBounds(145, 94, 70, 23);

        label104.setForeground(new java.awt.Color(0, 0, 0));
        label104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label104.setText("mmHg    Suhu : ");
        label104.setName("label104"); // NOI18N
        label104.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label104);
        label104.setBounds(220, 94, 75, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        FormInput.add(Tsuhu);
        Tsuhu.setBounds(298, 94, 48, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("°C     Nadi :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(350, 94, 58, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(409, 94, 70, 23);

        label43.setForeground(new java.awt.Color(0, 0, 0));
        label43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label43.setText("x/menit      Respirasi : ");
        label43.setName("label43"); // NOI18N
        label43.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label43);
        label43.setBounds(485, 94, 108, 23);

        Trespi.setForeground(new java.awt.Color(0, 0, 0));
        Trespi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Trespi.setName("Trespi"); // NOI18N
        Trespi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrespiKeyPressed(evt);
            }
        });
        FormInput.add(Trespi);
        Trespi.setBounds(593, 94, 60, 23);

        label106.setForeground(new java.awt.Color(0, 0, 0));
        label106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label106.setText("x/menit");
        label106.setName("label106"); // NOI18N
        label106.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label106);
        label106.setBounds(657, 94, 60, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Pukul :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(237, 66, 53, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(295, 66, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(347, 66, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(400, 66, 45, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N

        Tperencanaan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tperencanaan.setColumns(20);
        Tperencanaan.setRows(5);
        Tperencanaan.setName("Tperencanaan"); // NOI18N
        Tperencanaan.setPreferredSize(new java.awt.Dimension(162, 1500));
        Tperencanaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TperencanaanKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Tperencanaan);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(145, 206, 615, 80);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Tgl. Assesmen :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(0, 66, 140, 23);

        TtglAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-09-2024" }));
        TtglAsesmen.setDisplayFormat("dd-MM-yyyy");
        TtglAsesmen.setName("TtglAsesmen"); // NOI18N
        TtglAsesmen.setOpaque(false);
        TtglAsesmen.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglAsesmen);
        TtglAsesmen.setBounds(145, 66, 90, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("EKG :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 122, 140, 23);

        Tekg.setForeground(new java.awt.Color(0, 0, 0));
        Tekg.setName("Tekg"); // NOI18N
        Tekg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TekgKeyPressed(evt);
            }
        });
        FormInput.add(Tekg);
        Tekg.setBounds(145, 122, 615, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Lain - Lain :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 150, 140, 23);

        Tlainlain.setForeground(new java.awt.Color(0, 0, 0));
        Tlainlain.setName("Tlainlain"); // NOI18N
        Tlainlain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainlainKeyPressed(evt);
            }
        });
        FormInput.add(Tlainlain);
        Tlainlain.setBounds(145, 150, 615, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Assesmen :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 178, 140, 23);

        cmbAsesmen.setForeground(new java.awt.Color(0, 0, 0));
        cmbAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sesuai Asessmen Pre Sedasi/Anestesi", "Tidak Sesuai Asessmen Pre Sedasi/Anestesi" }));
        cmbAsesmen.setName("cmbAsesmen"); // NOI18N
        FormInput.add(cmbAsesmen);
        cmbAsesmen.setBounds(145, 178, 247, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Perencanaan :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 206, 140, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        TposisiLain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TposisiLain.setColumns(20);
        TposisiLain.setRows(5);
        TposisiLain.setName("TposisiLain"); // NOI18N
        TposisiLain.setPreferredSize(new java.awt.Dimension(162, 1500));
        TposisiLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TposisiLainKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(TposisiLain);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(440, 405, 320, 80);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Akses Intra Vena :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 291, 140, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("CVC :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 377, 140, 23);

        Tcvc.setForeground(new java.awt.Color(0, 0, 0));
        Tcvc.setName("Tcvc"); // NOI18N
        Tcvc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcvcKeyPressed(evt);
            }
        });
        FormInput.add(Tcvc);
        Tcvc.setBounds(145, 377, 615, 23);

        chkSupin.setBackground(new java.awt.Color(242, 242, 242));
        chkSupin.setForeground(new java.awt.Color(0, 0, 0));
        chkSupin.setText("Supine");
        chkSupin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSupin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSupin.setName("chkSupin"); // NOI18N
        chkSupin.setOpaque(false);
        chkSupin.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSupin);
        chkSupin.setBounds(145, 405, 80, 23);

        chkLitotomi.setBackground(new java.awt.Color(242, 242, 242));
        chkLitotomi.setForeground(new java.awt.Color(0, 0, 0));
        chkLitotomi.setText("Lithotomi");
        chkLitotomi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLitotomi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLitotomi.setName("chkLitotomi"); // NOI18N
        chkLitotomi.setOpaque(false);
        chkLitotomi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkLitotomi);
        chkLitotomi.setBounds(145, 433, 80, 23);

        chkLateral.setBackground(new java.awt.Color(242, 242, 242));
        chkLateral.setForeground(new java.awt.Color(0, 0, 0));
        chkLateral.setText("Lateral");
        chkLateral.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLateral.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLateral.setName("chkLateral"); // NOI18N
        chkLateral.setOpaque(false);
        chkLateral.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkLateral);
        chkLateral.setBounds(145, 461, 80, 23);

        chkProne.setBackground(new java.awt.Color(242, 242, 242));
        chkProne.setForeground(new java.awt.Color(0, 0, 0));
        chkProne.setText("Prone");
        chkProne.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkProne.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkProne.setName("chkProne"); // NOI18N
        chkProne.setOpaque(false);
        chkProne.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkProne);
        chkProne.setBounds(234, 405, 80, 23);

        chkPerlindungan.setBackground(new java.awt.Color(242, 242, 242));
        chkPerlindungan.setForeground(new java.awt.Color(0, 0, 0));
        chkPerlindungan.setText("Perlindungan Mata");
        chkPerlindungan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerlindungan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerlindungan.setName("chkPerlindungan"); // NOI18N
        chkPerlindungan.setOpaque(false);
        chkPerlindungan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkPerlindungan);
        chkPerlindungan.setBounds(234, 433, 120, 23);

        chkKanan.setBackground(new java.awt.Color(242, 242, 242));
        chkKanan.setForeground(new java.awt.Color(0, 0, 0));
        chkKanan.setText("Kanan");
        chkKanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKanan.setName("chkKanan"); // NOI18N
        chkKanan.setOpaque(false);
        chkKanan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkKanan);
        chkKanan.setBounds(234, 461, 60, 23);

        chkKiri.setBackground(new java.awt.Color(242, 242, 242));
        chkKiri.setForeground(new java.awt.Color(0, 0, 0));
        chkKiri.setText("Kiri");
        chkKiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKiri.setName("chkKiri"); // NOI18N
        chkKiri.setOpaque(false);
        chkKiri.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkKiri);
        chkKiri.setBounds(310, 461, 45, 23);

        chkLain.setBackground(new java.awt.Color(242, 242, 242));
        chkLain.setForeground(new java.awt.Color(0, 0, 0));
        chkLain.setText("Lain - Lain");
        chkLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLain.setName("chkLain"); // NOI18N
        chkLain.setOpaque(false);
        chkLain.setPreferredSize(new java.awt.Dimension(220, 23));
        chkLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainActionPerformed(evt);
            }
        });
        FormInput.add(chkLain);
        chkLain.setBounds(360, 405, 80, 23);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setText("Premedikasi :");
        label18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label18);
        label18.setBounds(0, 489, 140, 23);

        chkOralPreme.setBackground(new java.awt.Color(242, 242, 242));
        chkOralPreme.setForeground(new java.awt.Color(0, 0, 0));
        chkOralPreme.setText("Oral");
        chkOralPreme.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkOralPreme.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkOralPreme.setName("chkOralPreme"); // NOI18N
        chkOralPreme.setOpaque(false);
        chkOralPreme.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkOralPreme);
        chkOralPreme.setBounds(145, 489, 60, 23);

        chkIm.setBackground(new java.awt.Color(242, 242, 242));
        chkIm.setForeground(new java.awt.Color(0, 0, 0));
        chkIm.setText("IM");
        chkIm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIm.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIm.setName("chkIm"); // NOI18N
        chkIm.setOpaque(false);
        chkIm.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkIm);
        chkIm.setBounds(234, 489, 50, 23);

        chkIv.setBackground(new java.awt.Color(242, 242, 242));
        chkIv.setForeground(new java.awt.Color(0, 0, 0));
        chkIv.setText("IV");
        chkIv.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIv.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIv.setName("chkIv"); // NOI18N
        chkIv.setOpaque(false);
        chkIv.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkIv);
        chkIv.setBounds(310, 489, 45, 23);

        label20.setForeground(new java.awt.Color(0, 0, 0));
        label20.setText("Induksi :");
        label20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label20);
        label20.setBounds(0, 517, 140, 23);

        chkIntravena.setBackground(new java.awt.Color(242, 242, 242));
        chkIntravena.setForeground(new java.awt.Color(0, 0, 0));
        chkIntravena.setText("Intravena");
        chkIntravena.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIntravena.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIntravena.setName("chkIntravena"); // NOI18N
        chkIntravena.setOpaque(false);
        chkIntravena.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkIntravena);
        chkIntravena.setBounds(145, 517, 80, 23);

        chkInhalasi.setBackground(new java.awt.Color(242, 242, 242));
        chkInhalasi.setForeground(new java.awt.Color(0, 0, 0));
        chkInhalasi.setText("Inhalasi");
        chkInhalasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInhalasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInhalasi.setName("chkInhalasi"); // NOI18N
        chkInhalasi.setOpaque(false);
        chkInhalasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkInhalasi);
        chkInhalasi.setBounds(234, 517, 70, 23);

        label21.setForeground(new java.awt.Color(0, 0, 0));
        label21.setText("Tata Laksana Jalan Nafas :");
        label21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label21);
        label21.setBounds(0, 545, 170, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Face Mask No. :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(175, 545, 90, 23);

        Tface.setForeground(new java.awt.Color(0, 0, 0));
        Tface.setName("Tface"); // NOI18N
        Tface.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TfaceKeyPressed(evt);
            }
        });
        FormInput.add(Tface);
        Tface.setBounds(270, 545, 490, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Oro/Nasopharing No. :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(125, 573, 140, 23);

        Toro.setForeground(new java.awt.Color(0, 0, 0));
        Toro.setName("Toro"); // NOI18N
        Toro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ToroKeyPressed(evt);
            }
        });
        FormInput.add(Toro);
        Toro.setBounds(270, 573, 490, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("ETT No. :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(195, 601, 70, 23);

        Tett.setForeground(new java.awt.Color(0, 0, 0));
        Tett.setName("Tett"); // NOI18N
        Tett.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TettKeyPressed(evt);
            }
        });
        FormInput.add(Tett);
        Tett.setBounds(270, 601, 210, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Jenis :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(485, 601, 50, 23);

        TjenisEtt.setForeground(new java.awt.Color(0, 0, 0));
        TjenisEtt.setName("TjenisEtt"); // NOI18N
        TjenisEtt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjenisEttKeyPressed(evt);
            }
        });
        FormInput.add(TjenisEtt);
        TjenisEtt.setBounds(540, 601, 220, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Fiksasi :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(195, 629, 70, 23);

        Tfiksasi.setForeground(new java.awt.Color(0, 0, 0));
        Tfiksasi.setName("Tfiksasi"); // NOI18N
        Tfiksasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TfiksasiKeyPressed(evt);
            }
        });
        FormInput.add(Tfiksasi);
        Tfiksasi.setBounds(270, 629, 60, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("Cm.");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(335, 629, 30, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("LMA No. :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(195, 657, 70, 23);

        Tlma.setForeground(new java.awt.Color(0, 0, 0));
        Tlma.setName("Tlma"); // NOI18N
        Tlma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlmaKeyPressed(evt);
            }
        });
        FormInput.add(Tlma);
        Tlma.setBounds(270, 657, 210, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Jenis :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(485, 657, 50, 23);

        TjenisLma.setForeground(new java.awt.Color(0, 0, 0));
        TjenisLma.setName("TjenisLma"); // NOI18N
        TjenisLma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjenisLmaKeyPressed(evt);
            }
        });
        FormInput.add(TjenisLma);
        TjenisLma.setBounds(540, 657, 220, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Tracheostomi :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(165, 685, 100, 23);

        Ttraceostomi.setForeground(new java.awt.Color(0, 0, 0));
        Ttraceostomi.setName("Ttraceostomi"); // NOI18N
        Ttraceostomi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtraceostomiKeyPressed(evt);
            }
        });
        FormInput.add(Ttraceostomi);
        Ttraceostomi.setBounds(270, 685, 490, 23);

        label22.setForeground(new java.awt.Color(0, 0, 0));
        label22.setText("Intubasi :");
        label22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label22);
        label22.setBounds(0, 713, 140, 23);

        chkSesudah.setBackground(new java.awt.Color(242, 242, 242));
        chkSesudah.setForeground(new java.awt.Color(0, 0, 0));
        chkSesudah.setText("Sesudah Tidur");
        chkSesudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSesudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSesudah.setName("chkSesudah"); // NOI18N
        chkSesudah.setOpaque(false);
        chkSesudah.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSesudah);
        chkSesudah.setBounds(145, 713, 100, 23);

        chkOralIntubasi.setBackground(new java.awt.Color(242, 242, 242));
        chkOralIntubasi.setForeground(new java.awt.Color(0, 0, 0));
        chkOralIntubasi.setText("Oral");
        chkOralIntubasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkOralIntubasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkOralIntubasi.setName("chkOralIntubasi"); // NOI18N
        chkOralIntubasi.setOpaque(false);
        chkOralIntubasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkOralIntubasi);
        chkOralIntubasi.setBounds(145, 741, 100, 23);

        chkTraceostomi.setBackground(new java.awt.Color(242, 242, 242));
        chkTraceostomi.setForeground(new java.awt.Color(0, 0, 0));
        chkTraceostomi.setText("Tracheostomi");
        chkTraceostomi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTraceostomi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTraceostomi.setName("chkTraceostomi"); // NOI18N
        chkTraceostomi.setOpaque(false);
        chkTraceostomi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkTraceostomi);
        chkTraceostomi.setBounds(145, 769, 100, 23);

        chkBlind.setBackground(new java.awt.Color(242, 242, 242));
        chkBlind.setForeground(new java.awt.Color(0, 0, 0));
        chkBlind.setText("Blind");
        chkBlind.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBlind.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBlind.setName("chkBlind"); // NOI18N
        chkBlind.setOpaque(false);
        chkBlind.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkBlind);
        chkBlind.setBounds(257, 713, 60, 23);

        chkNasal.setBackground(new java.awt.Color(242, 242, 242));
        chkNasal.setForeground(new java.awt.Color(0, 0, 0));
        chkNasal.setText("Nasal :");
        chkNasal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNasal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNasal.setName("chkNasal"); // NOI18N
        chkNasal.setOpaque(false);
        chkNasal.setPreferredSize(new java.awt.Dimension(220, 23));
        chkNasal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNasalActionPerformed(evt);
            }
        });
        FormInput.add(chkNasal);
        chkNasal.setBounds(257, 741, 60, 23);

        chkLevel.setBackground(new java.awt.Color(242, 242, 242));
        chkLevel.setForeground(new java.awt.Color(0, 0, 0));
        chkLevel.setText("Level ETT");
        chkLevel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLevel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLevel.setName("chkLevel"); // NOI18N
        chkLevel.setOpaque(false);
        chkLevel.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkLevel);
        chkLevel.setBounds(257, 769, 80, 23);

        chkNasalKa.setBackground(new java.awt.Color(242, 242, 242));
        chkNasalKa.setForeground(new java.awt.Color(0, 0, 0));
        chkNasalKa.setText("Ka");
        chkNasalKa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNasalKa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNasalKa.setName("chkNasalKa"); // NOI18N
        chkNasalKa.setOpaque(false);
        chkNasalKa.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkNasalKa);
        chkNasalKa.setBounds(325, 741, 40, 23);

        chkNasalKi.setBackground(new java.awt.Color(242, 242, 242));
        chkNasalKi.setForeground(new java.awt.Color(0, 0, 0));
        chkNasalKi.setText("Ki");
        chkNasalKi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNasalKi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNasalKi.setName("chkNasalKi"); // NOI18N
        chkNasalKi.setOpaque(false);
        chkNasalKi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkNasalKi);
        chkNasalKi.setBounds(380, 741, 40, 23);

        chkDengan.setBackground(new java.awt.Color(242, 242, 242));
        chkDengan.setForeground(new java.awt.Color(0, 0, 0));
        chkDengan.setText("Dengan Margile");
        chkDengan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDengan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDengan.setName("chkDengan"); // NOI18N
        chkDengan.setOpaque(false);
        chkDengan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkDengan);
        chkDengan.setBounds(430, 713, 110, 23);

        chkCuff.setBackground(new java.awt.Color(242, 242, 242));
        chkCuff.setForeground(new java.awt.Color(0, 0, 0));
        chkCuff.setText("Cuff");
        chkCuff.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCuff.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCuff.setName("chkCuff"); // NOI18N
        chkCuff.setOpaque(false);
        chkCuff.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkCuff);
        chkCuff.setBounds(430, 741, 60, 23);

        chkPack.setBackground(new java.awt.Color(242, 242, 242));
        chkPack.setForeground(new java.awt.Color(0, 0, 0));
        chkPack.setText("Pack");
        chkPack.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPack.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPack.setName("chkPack"); // NOI18N
        chkPack.setOpaque(false);
        chkPack.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkPack);
        chkPack.setBounds(430, 769, 60, 23);

        chkSulitVenti.setBackground(new java.awt.Color(242, 242, 242));
        chkSulitVenti.setForeground(new java.awt.Color(0, 0, 0));
        chkSulitVenti.setText("Sulit Ventilasi");
        chkSulitVenti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSulitVenti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSulitVenti.setName("chkSulitVenti"); // NOI18N
        chkSulitVenti.setOpaque(false);
        chkSulitVenti.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSulitVenti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSulitVentiActionPerformed(evt);
            }
        });
        FormInput.add(chkSulitVenti);
        chkSulitVenti.setBounds(145, 797, 100, 23);

        chkSulitIntu.setBackground(new java.awt.Color(242, 242, 242));
        chkSulitIntu.setForeground(new java.awt.Color(0, 0, 0));
        chkSulitIntu.setText("Sulit Intubasi");
        chkSulitIntu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSulitIntu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSulitIntu.setName("chkSulitIntu"); // NOI18N
        chkSulitIntu.setOpaque(false);
        chkSulitIntu.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSulitIntu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSulitIntuActionPerformed(evt);
            }
        });
        FormInput.add(chkSulitIntu);
        chkSulitIntu.setBounds(145, 825, 100, 23);

        TsulitVenti.setForeground(new java.awt.Color(0, 0, 0));
        TsulitVenti.setName("TsulitVenti"); // NOI18N
        TsulitVenti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsulitVentiKeyPressed(evt);
            }
        });
        FormInput.add(TsulitVenti);
        TsulitVenti.setBounds(250, 797, 510, 23);

        TsulitIntu.setForeground(new java.awt.Color(0, 0, 0));
        TsulitIntu.setName("TsulitIntu"); // NOI18N
        TsulitIntu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsulitIntuKeyPressed(evt);
            }
        });
        FormInput.add(TsulitIntu);
        TsulitIntu.setBounds(250, 825, 510, 23);

        label23.setForeground(new java.awt.Color(0, 0, 0));
        label23.setText("Ventilasi :");
        label23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label23.setName("label23"); // NOI18N
        label23.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label23);
        label23.setBounds(0, 853, 140, 23);

        chkSpontan.setBackground(new java.awt.Color(242, 242, 242));
        chkSpontan.setForeground(new java.awt.Color(0, 0, 0));
        chkSpontan.setText("Spontan");
        chkSpontan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSpontan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSpontan.setName("chkSpontan"); // NOI18N
        chkSpontan.setOpaque(false);
        chkSpontan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSpontan);
        chkSpontan.setBounds(145, 853, 70, 23);

        chkKontrol.setBackground(new java.awt.Color(242, 242, 242));
        chkKontrol.setForeground(new java.awt.Color(0, 0, 0));
        chkKontrol.setText("Kontrol");
        chkKontrol.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKontrol.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKontrol.setName("chkKontrol"); // NOI18N
        chkKontrol.setOpaque(false);
        chkKontrol.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkKontrol);
        chkKontrol.setBounds(145, 881, 70, 23);

        chkVentilator.setBackground(new java.awt.Color(242, 242, 242));
        chkVentilator.setForeground(new java.awt.Color(0, 0, 0));
        chkVentilator.setText("Ventilator :");
        chkVentilator.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkVentilator.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkVentilator.setName("chkVentilator"); // NOI18N
        chkVentilator.setOpaque(false);
        chkVentilator.setPreferredSize(new java.awt.Dimension(220, 23));
        chkVentilator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVentilatorActionPerformed(evt);
            }
        });
        FormInput.add(chkVentilator);
        chkVentilator.setBounds(225, 853, 80, 23);

        chkLainVenti.setBackground(new java.awt.Color(242, 242, 242));
        chkLainVenti.setForeground(new java.awt.Color(0, 0, 0));
        chkLainVenti.setText("Lain-lain :");
        chkLainVenti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainVenti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainVenti.setName("chkLainVenti"); // NOI18N
        chkLainVenti.setOpaque(false);
        chkLainVenti.setPreferredSize(new java.awt.Dimension(220, 23));
        chkLainVenti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainVentiActionPerformed(evt);
            }
        });
        FormInput.add(chkLainVenti);
        chkLainVenti.setBounds(225, 881, 75, 23);

        TlainVentiCek.setForeground(new java.awt.Color(0, 0, 0));
        TlainVentiCek.setName("TlainVentiCek"); // NOI18N
        TlainVentiCek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainVentiCekKeyPressed(evt);
            }
        });
        FormInput.add(TlainVentiCek);
        TlainVentiCek.setBounds(300, 881, 460, 23);

        Tventilator.setForeground(new java.awt.Color(0, 0, 0));
        Tventilator.setName("Tventilator"); // NOI18N
        Tventilator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TventilatorKeyPressed(evt);
            }
        });
        FormInput.add(Tventilator);
        Tventilator.setBounds(310, 853, 120, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("TV :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(430, 853, 30, 23);

        Ttv.setForeground(new java.awt.Color(0, 0, 0));
        Ttv.setName("Ttv"); // NOI18N
        Ttv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtvKeyPressed(evt);
            }
        });
        FormInput.add(Ttv);
        Ttv.setBounds(465, 853, 70, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("RR :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(535, 853, 30, 23);

        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        FormInput.add(Trr);
        Trr.setBounds(570, 853, 70, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("PEEP :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(640, 853, 45, 23);

        Tpeep.setForeground(new java.awt.Color(0, 0, 0));
        Tpeep.setName("Tpeep"); // NOI18N
        Tpeep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpeepKeyPressed(evt);
            }
        });
        FormInput.add(Tpeep);
        Tpeep.setBounds(690, 853, 70, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Bronchoscopi Fiberoptik :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(0, 909, 140, 23);

        Tbroncos.setForeground(new java.awt.Color(0, 0, 0));
        Tbroncos.setName("Tbroncos"); // NOI18N
        Tbroncos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbroncosKeyPressed(evt);
            }
        });
        FormInput.add(Tbroncos);
        Tbroncos.setBounds(145, 909, 615, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Glidescope :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 937, 140, 23);

        Tglides.setForeground(new java.awt.Color(0, 0, 0));
        Tglides.setName("Tglides"); // NOI18N
        Tglides.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglidesKeyPressed(evt);
            }
        });
        FormInput.add(Tglides);
        Tglides.setBounds(145, 937, 615, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Lain - Lain :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 965, 140, 23);

        TlainVenti.setForeground(new java.awt.Color(0, 0, 0));
        TlainVenti.setName("TlainVenti"); // NOI18N
        TlainVenti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainVentiKeyPressed(evt);
            }
        });
        FormInput.add(TlainVenti);
        TlainVenti.setBounds(145, 965, 615, 23);

        label24.setForeground(new java.awt.Color(0, 0, 0));
        label24.setText("Teknik Regional/Block Perifer :");
        label24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label24.setName("label24"); // NOI18N
        label24.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label24);
        label24.setBounds(0, 993, 200, 23);

        scrollPane15.setName("scrollPane15"); // NOI18N

        TjenisTeknik.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TjenisTeknik.setColumns(20);
        TjenisTeknik.setRows(5);
        TjenisTeknik.setName("TjenisTeknik"); // NOI18N
        TjenisTeknik.setPreferredSize(new java.awt.Dimension(162, 1500));
        TjenisTeknik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjenisTeknikKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(TjenisTeknik);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(145, 1018, 615, 80);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Jenis :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(0, 1018, 140, 23);

        scrollPane16.setName("scrollPane16"); // NOI18N

        TaksesIntra.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TaksesIntra.setColumns(20);
        TaksesIntra.setRows(5);
        TaksesIntra.setName("TaksesIntra"); // NOI18N
        TaksesIntra.setPreferredSize(new java.awt.Dimension(162, 1500));
        TaksesIntra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TaksesIntraKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(TaksesIntra);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(145, 291, 615, 80);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Lokasi :");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 1103, 140, 23);

        Tlokasi.setForeground(new java.awt.Color(0, 0, 0));
        Tlokasi.setName("Tlokasi"); // NOI18N
        Tlokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlokasiKeyPressed(evt);
            }
        });
        FormInput.add(Tlokasi);
        Tlokasi.setBounds(145, 1103, 615, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Jenis Jarum / No. :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(0, 1131, 140, 23);

        Tjarum.setForeground(new java.awt.Color(0, 0, 0));
        Tjarum.setName("Tjarum"); // NOI18N
        Tjarum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjarumKeyPressed(evt);
            }
        });
        FormInput.add(Tjarum);
        Tjarum.setBounds(145, 1131, 615, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Kateter :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(0, 1159, 140, 23);

        cmbKateter.setForeground(new java.awt.Color(0, 0, 0));
        cmbKateter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbKateter.setName("cmbKateter"); // NOI18N
        FormInput.add(cmbKateter);
        cmbKateter.setBounds(145, 1159, 60, 23);

        chkFiksasi.setBackground(new java.awt.Color(242, 242, 242));
        chkFiksasi.setForeground(new java.awt.Color(0, 0, 0));
        chkFiksasi.setText("Fiksasi :");
        chkFiksasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFiksasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFiksasi.setName("chkFiksasi"); // NOI18N
        chkFiksasi.setOpaque(false);
        chkFiksasi.setPreferredSize(new java.awt.Dimension(220, 23));
        chkFiksasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFiksasiActionPerformed(evt);
            }
        });
        FormInput.add(chkFiksasi);
        chkFiksasi.setBounds(225, 1159, 66, 23);

        TfiksasiKateter.setForeground(new java.awt.Color(0, 0, 0));
        TfiksasiKateter.setName("TfiksasiKateter"); // NOI18N
        TfiksasiKateter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TfiksasiKateterKeyPressed(evt);
            }
        });
        FormInput.add(TfiksasiKateter);
        TfiksasiKateter.setBounds(290, 1159, 60, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel92.setText("Cm.");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(355, 1159, 30, 23);

        scrollPane17.setName("scrollPane17"); // NOI18N

        Tobat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tobat.setColumns(20);
        Tobat.setRows(5);
        Tobat.setName("Tobat"); // NOI18N
        Tobat.setPreferredSize(new java.awt.Dimension(162, 1500));
        Tobat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatKeyPressed(evt);
            }
        });
        scrollPane17.setViewportView(Tobat);

        FormInput.add(scrollPane17);
        scrollPane17.setBounds(145, 1187, 615, 80);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Obat - Obatan :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(0, 1187, 140, 23);

        scrollPane18.setName("scrollPane18"); // NOI18N

        Tkomplikasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkomplikasi.setColumns(20);
        Tkomplikasi.setRows(5);
        Tkomplikasi.setName("Tkomplikasi"); // NOI18N
        Tkomplikasi.setPreferredSize(new java.awt.Dimension(162, 1500));
        Tkomplikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkomplikasiKeyPressed(evt);
            }
        });
        scrollPane18.setViewportView(Tkomplikasi);

        FormInput.add(scrollPane18);
        scrollPane18.setBounds(145, 1272, 615, 80);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Komplikasi :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(0, 1272, 140, 23);

        BtnPerencanaan.setForeground(new java.awt.Color(0, 0, 0));
        BtnPerencanaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPerencanaan.setMnemonic('2');
        BtnPerencanaan.setText("Template");
        BtnPerencanaan.setToolTipText("Alt+2");
        BtnPerencanaan.setName("BtnPerencanaan"); // NOI18N
        BtnPerencanaan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPerencanaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerencanaanActionPerformed(evt);
            }
        });
        FormInput.add(BtnPerencanaan);
        BtnPerencanaan.setBounds(770, 206, 100, 23);

        BtnAkses.setForeground(new java.awt.Color(0, 0, 0));
        BtnAkses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAkses.setMnemonic('2');
        BtnAkses.setText("Template");
        BtnAkses.setToolTipText("Alt+2");
        BtnAkses.setName("BtnAkses"); // NOI18N
        BtnAkses.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAkses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAksesActionPerformed(evt);
            }
        });
        FormInput.add(BtnAkses);
        BtnAkses.setBounds(770, 291, 100, 23);

        BtnLain.setForeground(new java.awt.Color(0, 0, 0));
        BtnLain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnLain.setMnemonic('2');
        BtnLain.setText("Template");
        BtnLain.setToolTipText("Alt+2");
        BtnLain.setName("BtnLain"); // NOI18N
        BtnLain.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLainActionPerformed(evt);
            }
        });
        FormInput.add(BtnLain);
        BtnLain.setBounds(770, 405, 100, 23);

        BtnJenis.setForeground(new java.awt.Color(0, 0, 0));
        BtnJenis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnJenis.setMnemonic('2');
        BtnJenis.setText("Template");
        BtnJenis.setToolTipText("Alt+2");
        BtnJenis.setName("BtnJenis"); // NOI18N
        BtnJenis.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnJenisActionPerformed(evt);
            }
        });
        FormInput.add(BtnJenis);
        BtnJenis.setBounds(770, 1018, 100, 23);

        BtnObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnObat.setMnemonic('2');
        BtnObat.setText("Template");
        BtnObat.setToolTipText("Alt+2");
        BtnObat.setName("BtnObat"); // NOI18N
        BtnObat.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnObatActionPerformed(evt);
            }
        });
        FormInput.add(BtnObat);
        BtnObat.setBounds(770, 1187, 100, 23);

        BtnKomplikasi.setForeground(new java.awt.Color(0, 0, 0));
        BtnKomplikasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKomplikasi.setMnemonic('2');
        BtnKomplikasi.setText("Template");
        BtnKomplikasi.setToolTipText("Alt+2");
        BtnKomplikasi.setName("BtnKomplikasi"); // NOI18N
        BtnKomplikasi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnKomplikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKomplikasiActionPerformed(evt);
            }
        });
        FormInput.add(BtnKomplikasi);
        BtnKomplikasi.setBounds(770, 1272, 100, 23);

        Scroll1.setViewportView(FormInput);

        internalFrame1.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 47));
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
        panelGlass8.add(BtnHapus);

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('G');
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

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Assesmen Pre Induksi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(680, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbAsesmen.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
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

        PanelInput1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 86));
        panelGlass11.setLayout(new java.awt.BorderLayout());

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Assesmen :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-09-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass12.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-09-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(DTPCari2);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass12.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass12.add(LCount);

        panelGlass11.add(panelGlass12, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 42));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
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
        panelGlass10.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 23));
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
        panelGlass10.add(BtnAll);

        panelGlass11.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        PanelInput1.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput1, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            cekData();
            if (Sequel.menyimpantf("asesmen_pre_induksi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 73, new String[]{
                TNoRw.getText(), TrgRawat.getText(), Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                Ttensi.getText(), Trespi.getText(), Tnadi.getText(), Tsuhu.getText(), Tekg.getText(), Tlainlain.getText(), cmbAsesmen.getSelectedItem().toString(), Tperencanaan.getText(),
                TaksesIntra.getText(), Tcvc.getText(), supin, litotomi, lateral, prone, perlindungan, kanan, lainPosisi, TposisiLain.getText(), kiri, oralPreme, im, iv, intravena, inhalasi,
                Tface.getText(), Toro.getText(), Tett.getText(), TjenisEtt.getText(), Tlma.getText(), TjenisLma.getText(), Tfiksasi.getText(), Ttraceostomi.getText(), sesudah, oralIntu,
                traceos, blin, nasal, nasalKA, nasalKI, level, dengan, cuff, pack, sulitVenti, TsulitVenti.getText(), sulitIntu, TsulitIntu.getText(), spontan, kontrol, ventilator, Tventilator.getText(),
                Ttv.getText(), Trr.getText(), Tpeep.getText(), lainVenti, TlainVentiCek.getText(), Tbroncos.getText(), Tglides.getText(), TlainVenti.getText(), TjenisTeknik.getText(),
                Tlokasi.getText(), Tjarum.getText(), cmbKateter.getSelectedItem().toString(), fiksasi, TfiksasiKateter.getText(), Tobat.getText(), Tkomplikasi.getText(), TnipDokter.getText(),
                Sequel.cariIsi("select now()")
            }) == true) {

                TCari.setText(TNoRw.getText());
                emptTeks();
                tampil();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
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
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
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

                if (akses.getadmin() == true) {
                    gantiDisimpan();
                    ganti();
                } else {
                    if (tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString().equals(akses.getkode())) {
                        gantiDisimpan();
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh " + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString() + " ...!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbAsesmen.requestFocus();
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
        dispose();
        WindowRiwayat.dispose();
        WindowTemplate.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnBatal,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

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
        BtnCariActionPerformed(null);
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
            TCari.setText("");
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbAsesmenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAsesmenMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbAsesmenMouseClicked

    private void tbAsesmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAsesmenKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbAsesmenKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnadi.requestFocus();
        }
    }//GEN-LAST:event_TsuhuKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trespi.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TrespiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrespiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tekg.requestFocus();
        }
    }//GEN-LAST:event_TrespiKeyPressed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void TperencanaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TperencanaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TaksesIntra.requestFocus();
        }
    }//GEN-LAST:event_TperencanaanKeyPressed

    private void TtensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtensiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsuhu.requestFocus();
        }
    }//GEN-LAST:event_TtensiKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapus();
            } else {
                if (tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString().equals(akses.getkode())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh " + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString() + " ...!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbAsesmen.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
//            param.put("diagnosa", Tdiagnosa.getText());
//            param.put("rencana", Trencana.getText());
//            param.put("dokterOperator", TnmDokter.getText());
//            param.put("dokterAnastesi", TnmAnastesi.getText());
//            param.put("kesadaran", Tkesadaran.getText());
//            param.put("tensi", Ttensi.getText() + " mmHg");
//            param.put("suhu", Tsuhu.getText() + " °C");
//            param.put("nadi", Tnadi.getText() + " x/menit");
//            param.put("respi", Trespi.getText() + " x/menit");
//            
//            if (cmbInfus.getSelectedIndex() == 1) {
//                param.put("infus", cmbInfus.getSelectedItem().toString() + ", " + Tinfus.getText());
//            } else {
//                param.put("infus", cmbInfus.getSelectedItem().toString());
//            }
//            
//            if (cmbKateter.getSelectedIndex() == 1) {
//                param.put("kateter", cmbKateter.getSelectedItem().toString() + ", " + Tkateter.getText());
//            } else {
//                param.put("kateter", cmbKateter.getSelectedItem().toString());
//            }
//            
//            if (cmbCukur.getSelectedIndex() == 1) {
//                param.put("cukur", cmbCukur.getSelectedItem().toString() + ", " + Tcukur.getText());
//            } else {
//                param.put("cukur", cmbCukur.getSelectedItem().toString());
//            }
//            
//            if (cmbLavemen.getSelectedIndex() == 1) {
//                param.put("lavemen", cmbLavemen.getSelectedItem().toString() + ", " + Tlavemen.getText());
//            } else {
//                param.put("lavemen", cmbLavemen.getSelectedItem().toString());
//            }
//
//            param.put("gigi", cmbGigi.getSelectedItem().toString());
//            param.put("baju", cmbBaju.getSelectedItem().toString());
//            param.put("penandaan", cmbPenandaan.getSelectedItem().toString());
//            param.put("superAnastesi", cmbSuperAnastesi.getSelectedItem().toString());
//            param.put("superTindakan", cmbSuperTindakan.getSelectedItem().toString());
//            param.put("superTransfusi", cmbSuperTransfusi.getSelectedItem().toString());
//            
//            if (cmbAntibiotik.getSelectedIndex() == 1) {
//                param.put("antibiotik", cmbAntibiotik.getSelectedItem().toString() + ", " + Tantibiotik.getText() + " gr, Jam " + cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " WITA");
//            } else {
//                param.put("antibiotik", cmbAntibiotik.getSelectedItem().toString());
//            }
//            
//            param.put("pemeriksaan", Tperencanaan.getText() + "\n");
//            param.put("ekg", cmbEkg.getSelectedItem().toString());
//            param.put("intepretasiEkg", TintepretasiEkg.getText());
//            param.put("intepretasiRo", cmbIntepretasiRo.getSelectedItem().toString());
//            param.put("darah", cmbPersiapanDarah.getSelectedItem().toString());
//            param.put("puasa", cmbPersiapanPuasa.getSelectedItem().toString());
//            param.put("tglsurat", "Martapura, " + Valid.SetTglINDONESIA(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString()));
//            param.put("perawatBangsal", "(" + TnmPerawatBangsal.getText() + ")");
//            param.put("perawatIbs", "(" + TnmPerawatIbs.getText() + ")");

            Valid.MyReport("rptCeklisPraOperasi.jasper", "report", "::[ Lembar Checklist Pra Operasi ]::",
                "SELECT now() tanggal", param);

            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel..!!!!");
            tbAsesmen.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMAsesmenPreInduksi");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TposisiLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TposisiLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            chkOralPreme.requestFocus();
        }
    }//GEN-LAST:event_TposisiLainKeyPressed

    private void TjenisTeknikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjenisTeknikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tlokasi.requestFocus();
        }
    }//GEN-LAST:event_TjenisTeknikKeyPressed

    private void TaksesIntraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TaksesIntraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tcvc.requestFocus();
        }
    }//GEN-LAST:event_TaksesIntraKeyPressed

    private void TobatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tkomplikasi.requestFocus();
        }
    }//GEN-LAST:event_TobatKeyPressed

    private void TkomplikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkomplikasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnDokter.requestFocus();
        }
    }//GEN-LAST:event_TkomplikasiKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        akses.setform("RMAsesmenPreInduksi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void chkLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainActionPerformed
        TposisiLain.setText("");
        if (chkLain.isSelected() == true) {
            TposisiLain.setEnabled(true);
            BtnLain.setEnabled(true);
            TposisiLain.requestFocus();
        } else {
            TposisiLain.setEnabled(false);
            BtnLain.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainActionPerformed

    private void chkSulitVentiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSulitVentiActionPerformed
        TsulitVenti.setText("");
        if (chkSulitVenti.isSelected() == true) {
            TsulitVenti.setEnabled(true);
            TsulitVenti.requestFocus();
        } else {
            TsulitVenti.setEnabled(false);
        }
    }//GEN-LAST:event_chkSulitVentiActionPerformed

    private void chkSulitIntuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSulitIntuActionPerformed
        TsulitIntu.setText("");
        if (chkSulitIntu.isSelected() == true) {
            TsulitIntu.setEnabled(true);
            TsulitIntu.requestFocus();
        } else {
            TsulitIntu.setEnabled(false);
        }
    }//GEN-LAST:event_chkSulitIntuActionPerformed

    private void chkVentilatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVentilatorActionPerformed
        Tventilator.setText("");
        if (chkVentilator.isSelected() == true) {
            Tventilator.setEnabled(true);
            Tventilator.requestFocus();
        } else {
            Tventilator.setEnabled(false);
        }
    }//GEN-LAST:event_chkVentilatorActionPerformed

    private void chkLainVentiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainVentiActionPerformed
        TlainVentiCek.setText("");
        if (chkLainVenti.isSelected() == true) {
            TlainVentiCek.setEnabled(true);
            TlainVentiCek.requestFocus();
        } else {
            TlainVentiCek.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainVentiActionPerformed

    private void chkFiksasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFiksasiActionPerformed
        TfiksasiKateter.setText("");
        if (chkFiksasi.isSelected() == true) {
            TfiksasiKateter.setEnabled(true);
            TfiksasiKateter.requestFocus();
        } else {
            TfiksasiKateter.setEnabled(false);
        }
    }//GEN-LAST:event_chkFiksasiActionPerformed

    private void chkNasalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNasalActionPerformed
        chkNasalKa.setSelected(false);
        chkNasalKi.setSelected(false);
        if (chkNasal.isSelected() == true) {
            chkNasalKa.setEnabled(true);
            chkNasalKi.setEnabled(true);
            chkNasalKa.requestFocus();
        } else {
            chkNasalKa.setEnabled(false);
            chkNasalKi.setEnabled(false);
        }
    }//GEN-LAST:event_chkNasalActionPerformed

    private void TekgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TekgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlainlain.requestFocus();
        }
    }//GEN-LAST:event_TekgKeyPressed

    private void TlainlainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainlainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbAsesmen.requestFocus();
        }
    }//GEN-LAST:event_TlainlainKeyPressed

    private void TcvcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcvcKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSupin.requestFocus();
        }
    }//GEN-LAST:event_TcvcKeyPressed

    private void TfaceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfaceKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Toro.requestFocus();
        }
    }//GEN-LAST:event_TfaceKeyPressed

    private void ToroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ToroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tett.requestFocus();
        }
    }//GEN-LAST:event_ToroKeyPressed

    private void TettKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TettKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjenisEtt.requestFocus();
        }
    }//GEN-LAST:event_TettKeyPressed

    private void TjenisEttKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjenisEttKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tfiksasi.requestFocus();
        }
    }//GEN-LAST:event_TjenisEttKeyPressed

    private void TfiksasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfiksasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlma.requestFocus();
        }
    }//GEN-LAST:event_TfiksasiKeyPressed

    private void TlmaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlmaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjenisLma.requestFocus();
        }
    }//GEN-LAST:event_TlmaKeyPressed

    private void TjenisLmaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjenisLmaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttraceostomi.requestFocus();
        }
    }//GEN-LAST:event_TjenisLmaKeyPressed

    private void TtraceostomiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtraceostomiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSesudah.requestFocus();
        }
    }//GEN-LAST:event_TtraceostomiKeyPressed

    private void TsulitVentiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsulitVentiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSulitIntu.requestFocus();
        }
    }//GEN-LAST:event_TsulitVentiKeyPressed

    private void TsulitIntuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsulitIntuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSpontan.requestFocus();
        }
    }//GEN-LAST:event_TsulitIntuKeyPressed

    private void TventilatorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TventilatorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttv.requestFocus();
        }
    }//GEN-LAST:event_TventilatorKeyPressed

    private void TtvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtvKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trr.requestFocus();
        }
    }//GEN-LAST:event_TtvKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpeep.requestFocus();
        }
    }//GEN-LAST:event_TrrKeyPressed

    private void TpeepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpeepKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkKontrol.requestFocus();
        }
    }//GEN-LAST:event_TpeepKeyPressed

    private void TlainVentiCekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainVentiCekKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tbroncos.requestFocus();
        }
    }//GEN-LAST:event_TlainVentiCekKeyPressed

    private void TbroncosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbroncosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tglides.requestFocus();
        }
    }//GEN-LAST:event_TbroncosKeyPressed

    private void TglidesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglidesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TlainVenti.requestFocus();
        }
    }//GEN-LAST:event_TglidesKeyPressed

    private void TlainVentiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainVentiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjenisTeknik.requestFocus();
        }
    }//GEN-LAST:event_TlainVentiKeyPressed

    private void TlokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlokasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tjarum.requestFocus();
        }
    }//GEN-LAST:event_TlokasiKeyPressed

    private void TjarumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjarumKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbKateter.requestFocus();
        }
    }//GEN-LAST:event_TjarumKeyPressed

    private void TfiksasiKateterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfiksasiKateterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tobat.requestFocus();
        }
    }//GEN-LAST:event_TfiksasiKateterKeyPressed

    private void MnRiwayatDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatDataActionPerformed
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
                    if (Sequel.cariInteger("select count(-1) from asesmen_pre_induksi where "
                        + "waktu_simpan='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString() + "'") > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Proses kembalikan/restore data gagal, krn. sudah ada datanya yg. sama..!!");
                } else {
                    kembalikanData();
                    TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                    BtnCloseIn10ActionPerformed(null);
                    tampil();
                    emptTeks();
                }
            } else {
                kembalikanDataDiganti();
                TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                BtnCloseIn10ActionPerformed(null);
                tampil();
                emptTeks();
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

    private void kembalikanDataDiganti() {
        if (Sequel.queryu2tf("delete from asesmen_pre_induksi where waktu_simpan=?", 1, new String[]{
            tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString()
        }) == true) {
            kembalikanData();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal dikembalikan..!!");
        }
    }
    
    private void BtnCloseIn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn10ActionPerformed
        WindowRiwayat.dispose();
        TCari2.setText("");
    }//GEN-LAST:event_BtnCloseIn10ActionPerformed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (tbRiwayat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data riwayat asesmen pre induksi masih kosong...!!!");
            tbRiwayat.requestFocus();
        } else {
            if (tbRiwayat.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from asesmen_pre_induksi_histori where waktu_eksekusi=?", 1, new String[]{
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
        if(tabMode2.getRowCount() != 0) {
            try {
                if (tbTemplate.getSelectedRow() != -1) {
                    Ttemplate.setText(tbTemplate.getValueAt(tbTemplate.getSelectedRow(), 2).toString());
                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTemplateMouseClicked

    private void BtnPerencanaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerencanaanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 1;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Perencanaan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnPerencanaanActionPerformed

    private void BtnAksesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAksesActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 2;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Akses Intra Vena ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnAksesActionPerformed

    private void BtnLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLainActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 3;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Posisi Lain-lain ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnLainActionPerformed

    private void BtnJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnJenisActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 4;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Teknik Regional Jenis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnJenisActionPerformed

    private void BtnObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnObatActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 5;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Obat-obatan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnObatActionPerformed

    private void BtnKomplikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKomplikasiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 6;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Komplikasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnKomplikasiActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenPreInduksi dialog = new RMAsesmenPreInduksi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAkses;
    private widget.Button BtnAll;
    private widget.Button BtnAll1;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn10;
    private widget.Button BtnCopas;
    private widget.Button BtnDokter;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnJenis;
    private widget.Button BtnKeluar;
    private widget.Button BtnKomplikasi;
    private widget.Button BtnLain;
    private widget.Button BtnObat;
    private widget.Button BtnPerencanaan;
    private widget.Button BtnPrint;
    private widget.Button BtnRestor;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnHapus;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private javax.swing.JMenuItem MnRiwayatData;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll6;
    public widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TCari2;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea TaksesIntra;
    private widget.TextBox Tbroncos;
    private widget.TextBox Tcvc;
    private widget.TextBox Tekg;
    private widget.TextBox Tett;
    private widget.TextBox Tface;
    private widget.TextBox Tfiksasi;
    private widget.TextBox TfiksasiKateter;
    private widget.TextBox Tglides;
    private widget.TextBox Tjarum;
    private widget.TextBox TjenisEtt;
    private widget.TextBox TjenisLma;
    private widget.TextArea TjenisTeknik;
    private widget.TextArea Tkomplikasi;
    private widget.TextBox TlainVenti;
    private widget.TextBox TlainVentiCek;
    private widget.TextBox Tlainlain;
    private widget.TextBox Tlma;
    private widget.TextBox Tlokasi;
    private widget.TextBox Tnadi;
    private widget.TextBox TnipDokter;
    private widget.TextBox TnmDokter;
    private widget.TextArea Tobat;
    private widget.TextBox Toro;
    private widget.TextBox Tpeep;
    private widget.TextArea Tperencanaan;
    private widget.TextArea TposisiLain;
    private widget.TextBox Trespi;
    private widget.TextBox TrgRawat;
    private widget.TextBox Trr;
    private widget.TextBox Tsuhu;
    private widget.TextBox TsulitIntu;
    private widget.TextBox TsulitVenti;
    private widget.TextArea Ttemplate;
    private widget.TextBox Ttensi;
    private widget.Tanggal TtglAsesmen;
    private widget.TextBox Ttraceostomi;
    private widget.TextBox Ttv;
    private widget.TextBox Tventilator;
    private javax.swing.JDialog WindowRiwayat;
    private javax.swing.JDialog WindowTemplate;
    private widget.CekBox chkBlind;
    private widget.CekBox chkCuff;
    private widget.CekBox chkDengan;
    private widget.CekBox chkFiksasi;
    private widget.CekBox chkIm;
    private widget.CekBox chkInhalasi;
    private widget.CekBox chkIntravena;
    private widget.CekBox chkIv;
    private widget.CekBox chkKanan;
    private widget.CekBox chkKiri;
    private widget.CekBox chkKontrol;
    private widget.CekBox chkLain;
    private widget.CekBox chkLainVenti;
    private widget.CekBox chkLateral;
    private widget.CekBox chkLevel;
    private widget.CekBox chkLitotomi;
    private widget.CekBox chkNasal;
    private widget.CekBox chkNasalKa;
    private widget.CekBox chkNasalKi;
    private widget.CekBox chkOralIntubasi;
    private widget.CekBox chkOralPreme;
    private widget.CekBox chkPack;
    private widget.CekBox chkPerlindungan;
    private widget.CekBox chkProne;
    private widget.CekBox chkSesudah;
    private widget.CekBox chkSpontan;
    private widget.CekBox chkSulitIntu;
    private widget.CekBox chkSulitVenti;
    private widget.CekBox chkSupin;
    private widget.CekBox chkTraceostomi;
    private widget.CekBox chkVentilator;
    private widget.ComboBox cmbAsesmen;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbKateter;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel23;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel36;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.Label label104;
    private widget.Label label106;
    private widget.Label label15;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label22;
    private widget.Label label23;
    private widget.Label label24;
    private widget.Label label43;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.ScrollPane scrollPane18;
    private widget.Table tbAsesmen;
    private widget.Table tbRiwayat;
    private widget.Table tbTemplate;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT ap.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d/%m/%Y') tglLahir, "
                    + "DATE_FORMAT(ap.tgl_asesmen,'%d/%m/%Y') tglAses, TIME_FORMAT(ap.jam_asesmen,'%H:%i') jamAses, pg.nama nmDokter "
                    + "FROM asesmen_pre_induksi ap INNER JOIN reg_periksa rp ON rp.no_rawat = ap.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN pegawai pg ON pg.nik = ap.nip_dokter WHERE "
                    + "ap.tgl_asesmen between ? and ? and ap.no_rawat LIKE ? or "
                    + "ap.tgl_asesmen between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "ap.tgl_asesmen between ? and ? and p.nm_pasien LIKE ? or "
                    + "ap.tgl_asesmen between ? and ? and pg.nama LIKE ? or "
                    + "ap.tgl_asesmen between ? and ? and ap.perencanaan LIKE ? or "
                    + "ap.tgl_asesmen between ? and ? and ap.asesmen LIKE ? ORDER BY ap.tgl_asesmen desc, ap.jam_asesmen desc");
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
                        rs.getString("waktu_simpan"),
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglAses"),
                        rs.getString("jamAses"),
                        rs.getString("td"),
                        rs.getString("respi"),
                        rs.getString("nadi"),
                        rs.getString("suhu"),
                        rs.getString("ekg"),
                        rs.getString("lain_lain"),
                        rs.getString("asesmen"),
                        rs.getString("nmDokter"),
                        rs.getString("perencanaan"),
                        rs.getString("akses_intravena"),
                        rs.getString("cvc"),
                        rs.getString("supine"),
                        rs.getString("lithotomi"),
                        rs.getString("lateral"),
                        rs.getString("prone"),
                        rs.getString("perlindungan_mata"),
                        rs.getString("kanan"),
                        rs.getString("lain"),
                        rs.getString("ket_lain"),
                        rs.getString("kiri"),
                        rs.getString("oral_premedikasi"),
                        rs.getString("im"),
                        rs.getString("iv"),
                        rs.getString("intravena"),
                        rs.getString("inhalasi"),
                        rs.getString("face_mask_no"),
                        rs.getString("oro_no"),
                        rs.getString("ett_no"),
                        rs.getString("jenis_ett"),
                        rs.getString("lma_no"),
                        rs.getString("jenis_lma"),
                        rs.getString("fiksasi1"),
                        rs.getString("ket_traceostomi"),
                        rs.getString("sesudah_tidur"),
                        rs.getString("oral_intubasi"),
                        rs.getString("traceostomi"),
                        rs.getString("blind"),
                        rs.getString("nasal"),
                        rs.getString("nasal_ka"),
                        rs.getString("nasal_ki"),
                        rs.getString("level_ett"),
                        rs.getString("dengan_margile"),
                        rs.getString("cuff"),
                        rs.getString("pack"),
                        rs.getString("sulit_ventilasi"),
                        rs.getString("ket_sulit_ventilasi"),
                        rs.getString("sulit_intubasi"),
                        rs.getString("ket_sulit_intubasi"),
                        rs.getString("spontan"),
                        rs.getString("kontrol"),
                        rs.getString("ventilator"),
                        rs.getString("ket_ventilator"),
                        rs.getString("tv"),
                        rs.getString("rr"),
                        rs.getString("peep"),
                        rs.getString("lain_ventilasi1"),
                        rs.getString("ket_lain_ventilasi1"),
                        rs.getString("broncoskopi"),
                        rs.getString("glidescope"),
                        rs.getString("lain_ventilasi2"),
                        rs.getString("jenis"),
                        rs.getString("lokasi"),
                        rs.getString("jenis_jarum"),
                        rs.getString("kateter"),
                        rs.getString("fiksasi"),
                        rs.getString("fiksasi2"),
                        rs.getString("obat_obatan"),
                        rs.getString("komplikasi"),
                        rs.getString("nip_dokter"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("jam_asesmen")
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.RMAsesmenPreInduksi.tampil() : " + e);
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
        LCount.setText("" + tabMode.getRowCount());
    }
    
    public void emptTeks() {
        TtglAsesmen.requestFocus();
        TtglAsesmen.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        Ttensi.setText("");
        Tsuhu.setText("");
        Tnadi.setText("");
        Trespi.setText("");
        Tekg.setText("");
        Tlainlain.setText("");
        cmbAsesmen.setSelectedIndex(0);
        Tperencanaan.setText("");
        TaksesIntra.setText("");
        Tcvc.setText("");
        chkSupin.setSelected(false);
        chkLitotomi.setSelected(false);
        chkLateral.setSelected(false);
        chkProne.setSelected(false);
        chkPerlindungan.setSelected(false);
        chkKanan.setSelected(false);
        chkKiri.setSelected(false);
        chkLain.setSelected(false);
        TposisiLain.setText("");
        TposisiLain.setEnabled(false);
        BtnLain.setEnabled(false);
        chkOralPreme.setSelected(false);
        chkIm.setSelected(false);
        chkIv.setSelected(false);
        chkIntravena.setSelected(false);
        chkInhalasi.setSelected(false);
        Tface.setText("");
        Toro.setText("");
        Tett.setText("");
        TjenisEtt.setText("");
        Tfiksasi.setText("");
        Tlma.setText("");
        TjenisLma.setText("");
        Ttraceostomi.setText("");
        chkSesudah.setSelected(false);
        chkOralIntubasi.setSelected(false);
        chkTraceostomi.setSelected(false);
        chkBlind.setSelected(false);
        chkNasal.setSelected(false);
        chkNasalKa.setEnabled(false);
        chkNasalKi.setEnabled(false);
        chkNasalKa.setSelected(false);
        chkNasalKi.setSelected(false);
        chkLevel.setSelected(false);
        chkDengan.setSelected(false);
        chkCuff.setSelected(false);
        chkPack.setSelected(false);
        chkSulitVenti.setSelected(false);
        TsulitVenti.setText("");
        TsulitVenti.setEnabled(false);
        chkSulitIntu.setSelected(false);
        TsulitIntu.setEnabled(false);
        TsulitIntu.setText("");
        chkSpontan.setSelected(false);
        chkKontrol.setSelected(false);
        chkVentilator.setSelected(false);
        Tventilator.setEnabled(false);
        Tventilator.setText("");
        Ttv.setText("");
        Trr.setText("");
        Tpeep.setText("");
        chkLainVenti.setSelected(false);
        TlainVentiCek.setEnabled(false);
        TlainVentiCek.setText("");
        Tbroncos.setText("");
        Tglides.setText("");
        TlainVenti.setText("");
        TjenisTeknik.setText("");
        Tlokasi.setText("");
        Tjarum.setText("");
        cmbKateter.setSelectedIndex(0);
        chkFiksasi.setSelected(false);
        TfiksasiKateter.setText("");
        TfiksasiKateter.setEnabled(false);
        Tobat.setText("");
        Tkomplikasi.setText("");
        TnipDokter.setText("-");
        TnmDokter.setText("-");
    }

    private void getData() {
        emptVariabel();
        if (tbAsesmen.getSelectedRow() != -1) {
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
            TNoRM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 2).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 3).toString());
            TrgRawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 5).toString());
            Valid.SetTgl(TtglAsesmen, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString());
            cmbJam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString().substring(6, 8));
            Ttensi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 8).toString());
            Trespi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 9).toString());
            Tnadi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString());
            Tsuhu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString());
            Tekg.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString());
            Tlainlain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString());
            cmbAsesmen.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString());
            Tperencanaan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString());
            TaksesIntra.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString());
            Tcvc.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString());
            supin = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 19).toString();
            litotomi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString();
            lateral = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString();
            prone = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString();
            perlindungan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString();
            kanan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString();
            kiri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString();
            lainPosisi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString();
            TposisiLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString());
            oralPreme = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString();
            im = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString();
            iv = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString();
            intravena = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString();
            inhalasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 32).toString();
            Tface.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 33).toString());
            Toro.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString());
            Tett.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString());
            TjenisEtt.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString());
            Tlma.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString());
            TjenisLma.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString());
            Tfiksasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString());
            Ttraceostomi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString());
            sesudah = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString();
            oralIntu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString();
            traceos = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString();
            blin = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString();
            nasal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString();
            nasalKA = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString();
            nasalKI = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString();
            level = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString();
            dengan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString();
            cuff = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString();
            pack = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString();
            sulitVenti = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString();
            TsulitVenti.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString());
            sulitIntu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString();
            TsulitIntu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString());
            spontan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString();
            kontrol = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString();
            ventilator = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString();
            Tventilator.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString());
            Ttv.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString());
            Trr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString());
            Tpeep.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 62).toString());
            lainVenti = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString();
            TlainVentiCek.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 64).toString());
            Tbroncos.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString());
            Tglides.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 66).toString());
            TlainVenti.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString());
            TjenisTeknik.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString());
            Tlokasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString());
            Tjarum.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString());
            cmbKateter.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString());
            fiksasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString();
            TfiksasiKateter.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString());
            Tobat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString());
            Tkomplikasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString());
            TnipDokter.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString());
            TnmDokter.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString());
            dataCek();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getresep_dokter());
        BtnGanti.setEnabled(akses.getresep_dokter());
        BtnHapus.setEnabled(akses.getresep_dokter());
        BtnPrint.setEnabled(akses.getresep_dokter());
        MnRiwayatData.setEnabled(akses.getadmin());
    }
    
    private void dataCek() {
        //posisi
        if (supin.equals("ya")) {
            chkSupin.setSelected(true);
        } else {
            chkSupin.setSelected(false);
        }
        
        if (litotomi.equals("ya")) {
            chkLitotomi.setSelected(true);
        } else {
            chkLitotomi.setSelected(false);
        }
        
        if (lateral.equals("ya")) {
            chkLateral.setSelected(true);
        } else {
            chkLateral.setSelected(false);
        }
        
        if (prone.equals("ya")) {
            chkProne.setSelected(true);
        } else {
            chkProne.setSelected(false);
        }
        
        if (perlindungan.equals("ya")) {
            chkPerlindungan.setSelected(true);
        } else {
            chkPerlindungan.setSelected(false);
        }
        
        if (kanan.equals("ya")) {
            chkKanan.setSelected(true);
        } else {
            chkKanan.setSelected(false);
        }
        
        if (kiri.equals("ya")) {
            chkKiri.setSelected(true);
        } else {
            chkKiri.setSelected(false);
        }
        
        if (lainPosisi.equals("ya")) {
            chkLain.setSelected(true);
            TposisiLain.setEnabled(true);
            BtnLain.setEnabled(true);
        } else {
            chkLain.setSelected(false);
            TposisiLain.setEnabled(false);
            BtnLain.setEnabled(false);
        }
        
        //premedikasi
        if (oralPreme.equals("ya")) {
            chkOralPreme.setSelected(true);
        } else {
            chkOralPreme.setSelected(false);
        }
        
        if (im.equals("ya")) {
            chkIm.setSelected(true);
        } else {
            chkIm.setSelected(false);
        }
        
        if (iv.equals("ya")) {
            chkIv.setSelected(true);
        } else {
            chkIv.setSelected(false);
        }
        
        //induksi
        if (intravena.equals("ya")) {
            chkIntravena.setSelected(true);
        } else {
            chkIntravena.setSelected(false);
        }
        
        if (inhalasi.equals("ya")) {
            chkInhalasi.setSelected(true);
        } else {
            chkInhalasi.setSelected(false);
        }
        
        //intubasi
        if (sesudah.equals("ya")) {
            chkSesudah.setSelected(true);
        } else {
            chkSesudah.setSelected(false);
        }
        
        if (oralIntu.equals("ya")) {
            chkOralIntubasi.setSelected(true);
        } else {
            chkOralIntubasi.setSelected(false);
        }
        
        if (traceos.equals("ya")) {
            chkTraceostomi.setSelected(true);
        } else {
            chkTraceostomi.setSelected(false);
        }
        
        if (blin.equals("ya")) {
            chkBlind.setSelected(true);
        } else {
            chkBlind.setSelected(false);
        }
        
        if (nasal.equals("ya")) {
            chkNasal.setSelected(true);
            chkNasalKa.setEnabled(true);
            chkNasalKi.setEnabled(true);
        } else {
            chkNasal.setSelected(false);
            chkNasalKa.setEnabled(false);
            chkNasalKi.setEnabled(false);
        }
        
        if (nasalKA.equals("ya")) {
            chkNasalKa.setSelected(true);
        } else {
            chkNasalKa.setSelected(false);
        }
        
        if (nasalKI.equals("ya")) {
            chkNasalKi.setSelected(true);
        } else {
            chkNasalKi.setSelected(false);
        }
        
        if (level.equals("ya")) {
            chkLevel.setSelected(true);
        } else {
            chkLevel.setSelected(false);
        }
        
        if (dengan.equals("ya")) {
            chkDengan.setSelected(true);
        } else {
            chkDengan.setSelected(false);
        }
        
        if (cuff.equals("ya")) {
            chkCuff.setSelected(true);
        } else {
            chkCuff.setSelected(false);
        }
        
        if (pack.equals("ya")) {
            chkPack.setSelected(true);
        } else {
            chkPack.setSelected(false);
        }
        
        if (sulitVenti.equals("ya")) {
            chkSulitVenti.setSelected(true);
            TsulitVenti.setEnabled(true);
        } else {
            chkSulitVenti.setSelected(false);
            TsulitVenti.setEnabled(false);
        }
        
        if (sulitIntu.equals("ya")) {
            chkSulitIntu.setSelected(true);
            TsulitIntu.setEnabled(true);
        } else {
            chkSulitIntu.setSelected(false);
            TsulitIntu.setEnabled(false);
        }
        
        //ventilasi
        if (spontan.equals("ya")) {
            chkSpontan.setSelected(true);
        } else {
            chkSpontan.setSelected(false);
        }
        
        if (kontrol.equals("ya")) {
            chkKontrol.setSelected(true);
        } else {
            chkKontrol.setSelected(false);
        }
        
        if (ventilator.equals("ya")) {
            chkVentilator.setSelected(true);
            Tventilator.setEnabled(true);
        } else {
            chkVentilator.setSelected(false);
            Tventilator.setEnabled(false);
        }
        
        if (lainVenti.equals("ya")) {
            chkLainVenti.setSelected(true);
            TlainVentiCek.setEnabled(true);
        } else {
            chkLainVenti.setSelected(false);
            TlainVentiCek.setEnabled(false);
        }
        
        //teknik regional
        if (fiksasi.equals("ya")) {
            chkFiksasi.setSelected(true);
            TfiksasiKateter.setEnabled(true);
        } else {
            chkFiksasi.setSelected(false);
            TfiksasiKateter.setEnabled(false);
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TrgRawat.setText(ruangan);
        
        if (akses.getadmin() == true) {
            TnipDokter.setText("-");
            TnmDokter.setText("-");
        } else {
            TnipDokter.setText(akses.getkode());
            TnmDokter.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipDokter.getText() + "'"));
        }
    }
    
    private void cekData() {
        //posisi
        if (chkSupin.isSelected() == true) {
            supin = "ya";
        } else {
            supin = "tidak";
        }
        
        if (chkLitotomi.isSelected() == true) {
            litotomi = "ya";
        } else {
            litotomi = "tidak";
        }
        
        if (chkLateral.isSelected() == true) {
            lateral = "ya";
        } else {
            lateral = "tidak";
        }
        
        if (chkProne.isSelected() == true) {
            prone = "ya";
        } else {
            prone = "tidak";
        }
        
        if (chkPerlindungan.isSelected() == true) {
            perlindungan = "ya";
        } else {
            perlindungan = "tidak";
        }
        
        if (chkKanan.isSelected() == true) {
            kanan = "ya";
        } else {
            kanan = "tidak";
        }
        
        if (chkKiri.isSelected() == true) {
            kiri = "ya";
        } else {
            kiri = "tidak";
        }
        
        if (chkLain.isSelected() == true) {
            lainPosisi = "ya";
        } else {
            lainPosisi = "tidak";
        }
        
        //premedikasi
        if (chkOralPreme.isSelected() == true) {
            oralPreme = "ya";
        } else {
            oralPreme = "tidak";
        }
        
        if (chkIm.isSelected() == true) {
            im = "ya";
        } else {
            im = "tidak";
        }
        
        if (chkIv.isSelected() == true) {
            iv = "ya";
        } else {
            iv = "tidak";
        }
        
        //induksi
        if (chkIntravena.isSelected() == true) {
            intravena = "ya";
        } else {
            intravena = "tidak";
        }
        
        if (chkInhalasi.isSelected() == true) {
            inhalasi = "ya";
        } else {
            inhalasi = "tidak";
        }
        
        //intubasi
        if (chkSesudah.isSelected() == true) {
            sesudah = "ya";
        } else {
            sesudah = "tidak";
        }
        
        if (chkOralIntubasi.isSelected() == true) {
            oralIntu = "ya";
        } else {
            oralIntu = "tidak";
        }
        
        if (chkTraceostomi.isSelected() == true) {
            traceos = "ya";
        } else {
            traceos = "tidak";
        }
        
        if (chkBlind.isSelected() == true) {
            blin = "ya";
        } else {
            blin = "tidak";
        }
        
        if (chkNasal.isSelected() == true) {
            nasal = "ya";
        } else {
            nasal = "tidak";
        }
        
        if (chkNasalKa.isSelected() == true) {
            nasalKA = "ya";
        } else {
            nasalKA = "tidak";
        }
        
        if (chkNasalKi.isSelected() == true) {
            nasalKI = "ya";
        } else {
            nasalKI = "tidak";
        }
        
        if (chkLevel.isSelected() == true) {
            level = "ya";
        } else {
            level = "tidak";
        }
        
        if (chkDengan.isSelected() == true) {
            dengan = "ya";
        } else {
            dengan = "tidak";
        }
        
        if (chkCuff.isSelected() == true) {
            cuff = "ya";
        } else {
            cuff = "tidak";
        }
        
        if (chkPack.isSelected() == true) {
            pack = "ya";
        } else {
            pack = "tidak";
        }
        
        if (chkSulitVenti.isSelected() == true) {
            sulitVenti = "ya";
        } else {
            sulitVenti = "tidak";
        }
        
        if (chkSulitIntu.isSelected() == true) {
            sulitIntu = "ya";
        } else {
            sulitIntu = "tidak";
        }
        
        //ventilasi
        if (chkSpontan.isSelected() == true) {
            spontan = "ya";
        } else {
            spontan = "tidak";
        }
        
        if (chkKontrol.isSelected() == true) {
            kontrol = "ya";
        } else {
            kontrol = "tidak";
        }
        
        if (chkVentilator.isSelected() == true) {
            ventilator = "ya";
        } else {
            ventilator = "tidak";
        }
        
        if (chkLainVenti.isSelected() == true) {
            lainVenti = "ya";
        } else {
            lainVenti = "tidak";
        }
        
        //teknik regional
        if (chkFiksasi.isSelected() == true) {
            fiksasi = "ya";
        } else {
            fiksasi = "tidak";
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
            if (Sequel.queryu2tf("delete from asesmen_pre_induksi where waktu_simpan=?", 1, new String[]{
                tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
            }) == true) {
                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        }
    }
    
    private void hapusDisimpan() {
        cekData();
        if (Sequel.menyimpantf("asesmen_pre_induksi_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 76, new String[]{
            TNoRw.getText(), TrgRawat.getText(), Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
            Ttensi.getText(), Trespi.getText(), Tnadi.getText(), Tsuhu.getText(), Tekg.getText(), Tlainlain.getText(), cmbAsesmen.getSelectedItem().toString(), Tperencanaan.getText(),
            TaksesIntra.getText(), Tcvc.getText(), supin, litotomi, lateral, prone, perlindungan, kanan, lainPosisi, TposisiLain.getText(), kiri, oralPreme, im, iv, intravena, inhalasi,
            Tface.getText(), Toro.getText(), Tett.getText(), TjenisEtt.getText(), Tlma.getText(), TjenisLma.getText(), Tfiksasi.getText(), Ttraceostomi.getText(), sesudah, oralIntu,
            traceos, blin, nasal, nasalKA, nasalKI, level, dengan, cuff, pack, sulitVenti, TsulitVenti.getText(), sulitIntu, TsulitIntu.getText(), spontan, kontrol, ventilator, Tventilator.getText(),
            Ttv.getText(), Trr.getText(), Tpeep.getText(), lainVenti, TlainVentiCek.getText(), Tbroncos.getText(), Tglides.getText(), TlainVenti.getText(), TjenisTeknik.getText(),
            Tlokasi.getText(), Tjarum.getText(), cmbKateter.getSelectedItem().toString(), fiksasi, TfiksasiKateter.getText(), Tobat.getText(), Tkomplikasi.getText(), TnipDokter.getText(),
            tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString(),
            "hapus", user, Sequel.cariIsi("select now()")
        }) == true) {
            System.out.println("Asesmen Pre Induksi Dihapus Berhasil Tersimpan Sebagai Data Histori..!!");
        }
    }

    private void ganti() {
        cekData();
        if (Sequel.mengedittf("asesmen_pre_induksi", "waktu_simpan=?", "tgl_asesmen=?, jam_asesmen=?, td=?, respi=?, nadi=?, suhu=?, ekg=?, lain_lain=?, asesmen=?, perencanaan=?, akses_intravena=?, "
                + "cvc=?, supine=?, lithotomi=?, lateral=?, prone=?, perlindungan_mata=?, kanan=?, lain=?, ket_lain=?, kiri=?, oral_premedikasi=?, im=?, iv=?, intravena=?, inhalasi=?, face_mask_no=?, "
                + "oro_no=?, ett_no=?, jenis_ett=?, lma_no=?, jenis_lma=?, fiksasi1=?, ket_traceostomi=?, sesudah_tidur=?, oral_intubasi=?, traceostomi=?, blind=?, nasal=?, nasal_ka=?, nasal_ki=?, "
                + "level_ett=?, dengan_margile=?, cuff=?, pack=?, sulit_ventilasi=?, ket_sulit_ventilasi=?, sulit_intubasi=?, ket_sulit_intubasi=?, spontan=?, kontrol=?, ventilator=?, ket_ventilator=?, "
                + "tv=?, rr=?, peep=?, lain_ventilasi1=?, ket_lain_ventilasi1=?, broncoskopi=?, glidescope=?, lain_ventilasi2=?, jenis=?, lokasi=?, jenis_jarum=?, kateter=?, fiksasi=?, fiksasi2=?, "
                + "obat_obatan=?, komplikasi=?, nip_dokter=?", 71, new String[]{
                    Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                    Ttensi.getText(), Trespi.getText(), Tnadi.getText(), Tsuhu.getText(), Tekg.getText(), Tlainlain.getText(), cmbAsesmen.getSelectedItem().toString(), Tperencanaan.getText(),
                    TaksesIntra.getText(), Tcvc.getText(), supin, litotomi, lateral, prone, perlindungan, kanan, lainPosisi, TposisiLain.getText(), kiri, oralPreme, im, iv, intravena, inhalasi,
                    Tface.getText(), Toro.getText(), Tett.getText(), TjenisEtt.getText(), Tlma.getText(), TjenisLma.getText(), Tfiksasi.getText(), Ttraceostomi.getText(), sesudah, oralIntu,
                    traceos, blin, nasal, nasalKA, nasalKI, level, dengan, cuff, pack, sulitVenti, TsulitVenti.getText(), sulitIntu, TsulitIntu.getText(), spontan, kontrol, ventilator, Tventilator.getText(),
                    Ttv.getText(), Trr.getText(), Tpeep.getText(), lainVenti, TlainVentiCek.getText(), Tbroncos.getText(), Tglides.getText(), TlainVenti.getText(), TjenisTeknik.getText(),
                    Tlokasi.getText(), Tjarum.getText(), cmbKateter.getSelectedItem().toString(), fiksasi, TfiksasiKateter.getText(), Tobat.getText(), Tkomplikasi.getText(), TnipDokter.getText(),
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                }) == true) {

            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
        }
    }
    
    private void gantiDisimpan() {
        cekData();
        if (Sequel.menyimpantf("asesmen_pre_induksi_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 76, new String[]{
            TNoRw.getText(), TrgRawat.getText(), Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
            Ttensi.getText(), Trespi.getText(), Tnadi.getText(), Tsuhu.getText(), Tekg.getText(), Tlainlain.getText(), cmbAsesmen.getSelectedItem().toString(), Tperencanaan.getText(),
            TaksesIntra.getText(), Tcvc.getText(), supin, litotomi, lateral, prone, perlindungan, kanan, lainPosisi, TposisiLain.getText(), kiri, oralPreme, im, iv, intravena, inhalasi,
            Tface.getText(), Toro.getText(), Tett.getText(), TjenisEtt.getText(), Tlma.getText(), TjenisLma.getText(), Tfiksasi.getText(), Ttraceostomi.getText(), sesudah, oralIntu,
            traceos, blin, nasal, nasalKA, nasalKI, level, dengan, cuff, pack, sulitVenti, TsulitVenti.getText(), sulitIntu, TsulitIntu.getText(), spontan, kontrol, ventilator, Tventilator.getText(),
            Ttv.getText(), Trr.getText(), Tpeep.getText(), lainVenti, TlainVentiCek.getText(), Tbroncos.getText(), Tglides.getText(), TlainVenti.getText(), TjenisTeknik.getText(),
            Tlokasi.getText(), Tjarum.getText(), cmbKateter.getSelectedItem().toString(), fiksasi, TfiksasiKateter.getText(), Tobat.getText(), Tkomplikasi.getText(), TnipDokter.getText(),
            tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString(),
            "ganti", user, Sequel.cariIsi("select now()")
        }) == true) {
            System.out.println("Asesmen Pre Induksi Dihapus Berhasil Tersimpan Sebagai Data Histori..!!");
        }
    }

    private void emptVariabel() {
        user = "";
        supin = "";
        litotomi = "";
        lateral = "";
        prone = "";
        perlindungan = "";
        kanan = "";
        kiri = "";
        lainPosisi = "";
        oralPreme = "";
        im = "";
        iv = "";
        intravena = "";
        inhalasi = "";
        sesudah = "";
        oralIntu = "";
        traceos = "";
        blin = "";
        nasal = "";
        nasalKA = "";
        nasalKI = "";
        level = "";
        dengan = "";
        cuff = "";
        pack = "";
        sulitVenti = "";
        sulitIntu = "";
        spontan = "";
        kontrol = "";
        ventilator = "";
        lainVenti = "";
        fiksasi = "";
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode1);
        try {
            psrestor = koneksi.prepareStatement("SELECT IF(pg.nama='-','Admin Utama',pg.nama) pelaku, a.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                    + "date(a.waktu_simpan) tglsimpan, a.waktu_eksekusi, upper(concat('DI',a.status_data)) sttsdata, a.waktu_simpan FROM asesmen_pre_induksi_histori a "
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
            ps2 = koneksi.prepareStatement("select * from asesmen_pre_induksi_histori where "
                    + "waktu_eksekusi='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString() + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    try {
                        if (Sequel.menyimpantf("asesmen_pre_induksi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 73, new String[]{
                                    rs2.getString("no_rawat"),
                                    rs2.getString("ruang_rawat"),
                                    rs2.getString("tgl_asesmen"),
                                    rs2.getString("jam_asesmen"),
                                    rs2.getString("td"),
                                    rs2.getString("respi"),
                                    rs2.getString("nadi"),
                                    rs2.getString("suhu"),
                                    rs2.getString("ekg"),
                                    rs2.getString("lain_lain"),
                                    rs2.getString("asesmen"),
                                    rs2.getString("perencanaan"),
                                    rs2.getString("akses_intravena"),
                                    rs2.getString("cvc"),
                                    rs2.getString("supine"),
                                    rs2.getString("lithotomi"),
                                    rs2.getString("lateral"),
                                    rs2.getString("prone"),
                                    rs2.getString("perlindungan_mata"),
                                    rs2.getString("kanan"),
                                    rs2.getString("lain"),
                                    rs2.getString("ket_lain"),
                                    rs2.getString("kiri"),
                                    rs2.getString("oral_premedikasi"),
                                    rs2.getString("im"),
                                    rs2.getString("iv"),
                                    rs2.getString("intravena"),
                                    rs2.getString("inhalasi"),
                                    rs2.getString("face_mask_no"),
                                    rs2.getString("oro_no"),
                                    rs2.getString("ett_no"),
                                    rs2.getString("jenis_ett"),
                                    rs2.getString("lma_no"),
                                    rs2.getString("jenis_lma"),
                                    rs2.getString("fiksasi1"),
                                    rs2.getString("ket_traceostomi"),
                                    rs2.getString("sesudah_tidur"),
                                    rs2.getString("oral_intubasi"),
                                    rs2.getString("traceostomi"),
                                    rs2.getString("blind"),
                                    rs2.getString("nasal"),
                                    rs2.getString("nasal_ka"),
                                    rs2.getString("nasal_ki"),
                                    rs2.getString("level_ett"),
                                    rs2.getString("dengan_margile"),
                                    rs2.getString("cuff"),
                                    rs2.getString("pack"),
                                    rs2.getString("sulit_ventilasi"),
                                    rs2.getString("ket_sulit_ventilasi"),
                                    rs2.getString("sulit_intubasi"),
                                    rs2.getString("ket_sulit_intubasi"),
                                    rs2.getString("spontan"),
                                    rs2.getString("kontrol"),
                                    rs2.getString("ventilator"),
                                    rs2.getString("ket_ventilator"),
                                    rs2.getString("tv"),
                                    rs2.getString("rr"),
                                    rs2.getString("peep"),
                                    rs2.getString("lain_ventilasi1"),
                                    rs2.getString("ket_lain_ventilasi1"),
                                    rs2.getString("broncoskopi"),
                                    rs2.getString("glidescope"),
                                    rs2.getString("lain_ventilasi2"),
                                    rs2.getString("jenis"),
                                    rs2.getString("lokasi"),
                                    rs2.getString("jenis_jarum"),
                                    rs2.getString("kateter"),
                                    rs2.getString("fiksasi"),
                                    rs2.getString("fiksasi2"),
                                    rs2.getString("obat_obatan"),
                                    rs2.getString("komplikasi"),
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
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode2);
        try {
            if (pilihan == 1) {
                pps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_pre_induksi pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.perencanaan<>'' and p.no_rkm_medis like ? OR "
                        + "pa.perencanaan<>'' and p.nm_pasien like ? OR "
                        + "pa.perencanaan<>'' and pa.perencanaan like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } else if (pilihan == 2) {
                pps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_pre_induksi pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.akses_intravena<>'' and p.no_rkm_medis like ? OR "
                        + "pa.akses_intravena<>'' and p.nm_pasien like ? OR "
                        + "pa.akses_intravena<>'' and pa.akses_intravena like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } else if (pilihan == 3) {
                pps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_pre_induksi pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.ket_lain<>'' and p.no_rkm_medis like ? OR "
                        + "pa.ket_lain<>'' and p.nm_pasien like ? OR "
                        + "pa.ket_lain<>'' and pa.ket_lain like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } else if (pilihan == 4) {
                pps4 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_pre_induksi pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.jenis<>'' and p.no_rkm_medis like ? OR "
                        + "pa.jenis<>'' and p.nm_pasien like ? OR "
                        + "pa.jenis<>'' and pa.jenis like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } else if (pilihan == 5) {
                pps5 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_pre_induksi pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.obat_obatan<>'' and p.no_rkm_medis like ? OR "
                        + "pa.obat_obatan<>'' and p.nm_pasien like ? OR "
                        + "pa.obat_obatan<>'' and pa.obat_obatan like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } else if (pilihan == 6) {
                pps6 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_pre_induksi pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.komplikasi<>'' and p.no_rkm_medis like ? OR "
                        + "pa.komplikasi<>'' and p.nm_pasien like ? OR "
                        + "pa.komplikasi<>'' and pa.komplikasi like ? ORDER BY pa.tgl_asesmen desc limit 20");
            } 
            try {
                if (pilihan == 1) {
                    pps1.setString(1, "%" + TCari1.getText() + "%");
                    pps1.setString(2, "%" + TCari1.getText() + "%");
                    pps1.setString(3, "%" + TCari1.getText() + "%");
                    rrs1 = pps1.executeQuery();
                    while (rrs1.next()) {
                        tabMode2.addRow(new String[]{
                            rrs1.getString("no_rkm_medis"),
                            rrs1.getString("nm_pasien"),
                            rrs1.getString("perencanaan")
                        });
                    }
                } else if (pilihan == 2) {
                    pps2.setString(1, "%" + TCari1.getText() + "%");
                    pps2.setString(2, "%" + TCari1.getText() + "%");
                    pps2.setString(3, "%" + TCari1.getText() + "%");
                    rrs2 = pps2.executeQuery();
                    while (rrs2.next()) {
                        tabMode2.addRow(new String[]{
                            rrs2.getString("no_rkm_medis"),
                            rrs2.getString("nm_pasien"),
                            rrs2.getString("akses_intravena")
                        });
                    }
                } else if (pilihan == 3) {
                    pps3.setString(1, "%" + TCari1.getText() + "%");
                    pps3.setString(2, "%" + TCari1.getText() + "%");
                    pps3.setString(3, "%" + TCari1.getText() + "%");
                    rrs3 = pps3.executeQuery();
                    while (rrs3.next()) {
                        tabMode2.addRow(new String[]{
                            rrs3.getString("no_rkm_medis"),
                            rrs3.getString("nm_pasien"),
                            rrs3.getString("ket_lain")
                        });
                    }
                } else if (pilihan == 4) {
                    pps4.setString(1, "%" + TCari1.getText() + "%");
                    pps4.setString(2, "%" + TCari1.getText() + "%");
                    pps4.setString(3, "%" + TCari1.getText() + "%");
                    rrs4 = pps4.executeQuery();
                    while (rrs4.next()) {
                        tabMode2.addRow(new String[]{
                            rrs4.getString("no_rkm_medis"),
                            rrs4.getString("nm_pasien"),
                            rrs4.getString("jenis")
                        });
                    }
                } else if (pilihan == 5) {
                    pps5.setString(1, "%" + TCari1.getText() + "%");
                    pps5.setString(2, "%" + TCari1.getText() + "%");
                    pps5.setString(3, "%" + TCari1.getText() + "%");
                    rrs5 = pps5.executeQuery();
                    while (rrs5.next()) {
                        tabMode2.addRow(new String[]{
                            rrs5.getString("no_rkm_medis"),
                            rrs5.getString("nm_pasien"),
                            rrs5.getString("obat_obatan")
                        });
                    }
                } else if (pilihan == 6) {
                    pps6.setString(1, "%" + TCari1.getText() + "%");
                    pps6.setString(2, "%" + TCari1.getText() + "%");
                    pps6.setString(3, "%" + TCari1.getText() + "%");
                    rrs6 = pps6.executeQuery();
                    while (rrs6.next()) {
                        tabMode2.addRow(new String[]{
                            rrs6.getString("no_rkm_medis"),
                            rrs6.getString("nm_pasien"),
                            rrs6.getString("komplikasi")
                        });
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rrs1 != null) {
                    rrs1.close();
                } else if (rrs2 != null) {
                    rrs2.close();
                } else if (rrs3 != null) {
                    rrs3.close();
                } else if (rrs4 != null) {
                    rrs4.close();
                } else if (rrs5 != null) {
                    rrs5.close();
                } else if (rrs6 != null) {
                    rrs6.close();
                }

                if (pps1 != null) {
                    pps1.close();
                } else if (pps2 != null) {
                    pps2.close();
                } else if (pps3 != null) {
                    pps3.close();
                } else if (pps4 != null) {
                    pps4.close();
                } else if (pps5 != null) {
                    pps5.close();
                } else if (pps6 != null) {
                    pps6.close();
                } 
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copas() {
        if (pilihan == 1) {
            Tperencanaan.setText(Ttemplate.getText());
        } else if (pilihan == 2) {
            TaksesIntra.setText(Ttemplate.getText());
        } else if (pilihan == 3) {
            TposisiLain.setText(Ttemplate.getText());
        } else if (pilihan == 4) {
            TjenisTeknik.setText(Ttemplate.getText());
        } else if (pilihan == 5) {
            Tobat.setText(Ttemplate.getText());
        } else if (pilihan == 6) {
            Tkomplikasi.setText(Ttemplate.getText());
        }
    }
}
