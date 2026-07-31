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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class RMMasalahKeperawatanKetidakstabilanGlukosaDarah extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String sdkiDisfungsiPankreas = "", sdkiResistensi = "", sdkiGangguanToleransi = "", sdkiGangguanGlukosa = "", sdkiPenggunaanInsulin = "", sdkiHiperinsulin = "",
            sdkiEndokrin = "", sdkiDisfungsiHati = "", sdkiDisfungsiGinjal = "", sdkiEfek = "", sdkiTindakan = "", sdkiGangguanMetabolik = "", sdkiMengantuk = "", sdkiPusing = "",
            sdkiGangguanKordinasi = "", sdkiKadarRendah = "", sdkiLelah = "", sdkiKadarTinggi = "", sdkiPalpitasi = "", sdkiMengeluh = "", sdkiGemetar = "", sdkiKesadaran = "",
            sdkiPerilaku = "", sdkiSulit = "", sdkiBerkeringat = "", sdkiMulut = "", sdkiJumlah = "", sdkiHaus = "", sdkiDiabetesMellitus = "", sdkiKetoasidosis = "",
            sdkiHipoglikemia = "", sdkiHiperglikemia = "", sdkiPenggunaanKorti = "", sdkiNutrisi = "", sdkiDiabetesGestasional = "", slkiKestabilan = "", slkiKesadaran = "",
            slkiMengantuk = "", slkiPusing = "", slkiLelah = "", slkiKeluhan = "", slkiGemetar = "", slkiBerkeringat = "", slkiRasa = "", slkiKadar = "", slkiJumlah = "",
            sikiIdenKemungkinan = "", sikiIdenSituasi = "", sikiMonitorKadar = "", sikiMonitorTanda = "", sikiMonitorIntake = "", sikiMonitorKeton = "", sikiBerikan = "",
            sikiKonsultasi = "", sikiFasilitasi = "", sikiAnjurMenghindari = "", sikiAnjurMonitor = "", sikiAnjurKepatuhan = "", sikiAjarkanIndikasi = "", sikiAjarkanPengelolaan = "",
            sikiKolabInsulin = "", sikiKolabIv = "", sikiKolabKalium = "", sttsRawat = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMMasalahKeperawatanKetidakstabilanGlukosaDarah(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "waktu_simpan", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Simpan", "Nama Petugas",
            "sdki_disfungsi_pankreas", "sdki_resistensi", "sdki_gangguan_toleransi", "sdki_gangguan_glukosa", "sdki_penggunaan_insulin", "sdki_hiperinsulin", "sdki_endokrin",
            "sdki_disfungsi_hati", "sdki_disfungsi_ginjal", "sdki_efek", "sdki_tindakan", "sdki_gangguan_metabolik", "sdki_mengantuk", "sdki_pusing", "sdki_gangguan_kordinasi",
            "sdki_kadar_rendah", "sdki_lelah", "sdki_kadar_tinggi", "sdki_palpitasi", "sdki_mengeluh", "sdki_gemetar", "sdki_kesadaran", "sdki_perilaku", "sdki_sulit",
            "sdki_berkeringat", "sdki_mulut", "sdki_jumlah", "sdki_haus", "sdki_diabetes_mellitus", "sdki_ketoasidosis", "sdki_hipoglikemia", "sdki_hiperglikemia",
            "sdki_penggunaan_korti", "sdki_nutrisi", "sdki_diabetes_gestasional", "slki_kestabilan", "ket_selama", "slki_kesadaran", "slki_mengantuk", "slki_pusing", "slki_lelah",
            "slki_keluhan", "slki_gemetar", "slki_berkeringat", "slki_rasa", "slki_kadar", "slki_jumlah", "siki_iden_kemungkinan", "siki_iden_situasi", "siki_monitor_kadar",
            "siki_monitor_tanda", "siki_monitor_intake", "siki_monitor_keton", "siki_berikan", "siki_konsultasi", "siki_fasilitasi", "siki_anjur_menghindari", "siki_anjur_monitor",
            "siki_anjur_kepatuhan", "siki_ajarkan_indikasi", "siki_ajarkan_pengelolaan", "siki_kolab_insulin", "siki_kolab_iv", "siki_kolab_kalium", "status_rawat", "nip_petugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbMasalah.setModel(tabMode);
        tbMasalah.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMasalah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 74; i++) {
            TableColumn column = tbMasalah.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(220);
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
            }
        }
        tbMasalah.setDefaultRenderer(Object.class, new WarnaTable());

        TketSelama.setDocument(new batasInput((int) 20).getKata(TketSelama));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMMasalahKeperawatanKetidakstabilanGlukosaDarah")) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        TnipPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPetugas.requestFocus();
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
    }
 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        panelGlass13 = new widget.panelisi();
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        label20 = new widget.Label();
        TnipPetugas = new widget.TextBox();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        chkSdkiDisfungsiPankreas = new widget.CekBox();
        chkSdkiResistensi = new widget.CekBox();
        chkSdkiPenggunaanInsulin = new widget.CekBox();
        chkSdkiHiperinsulin = new widget.CekBox();
        chkSdkiEndokrin = new widget.CekBox();
        chkSdkiLelah = new widget.CekBox();
        chkSdkiPalpitasi = new widget.CekBox();
        chkSdkiKadarTinggi = new widget.CekBox();
        jLabel74 = new widget.Label();
        jLabel75 = new widget.Label();
        jLabel76 = new widget.Label();
        chkSdkiGangguanToleransi = new widget.CekBox();
        chkSdkiMengantuk = new widget.CekBox();
        chkSdkiPusing = new widget.CekBox();
        chkSdkiGangguanKordinasi = new widget.CekBox();
        jLabel79 = new widget.Label();
        TketSelama = new widget.TextBox();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        chkSdkiMulut = new widget.CekBox();
        chkSdkiDiabetesMellitus = new widget.CekBox();
        chkSlkiKesadaran = new widget.CekBox();
        chkSlkiLelah = new widget.CekBox();
        chkSlkiKeluhan = new widget.CekBox();
        jLabel83 = new widget.Label();
        chkSikiIdenKemungkinan = new widget.CekBox();
        chkSikiIdenSituasi = new widget.CekBox();
        chkSikiMonitorKadar = new widget.CekBox();
        chkSikiMonitorTanda = new widget.CekBox();
        jLabel84 = new widget.Label();
        chkSikiMonitorKeton = new widget.CekBox();
        chkSdkiDisfungsiHati = new widget.CekBox();
        jLabel91 = new widget.Label();
        chkSlkiKestabilan = new widget.CekBox();
        chkSdkiHipoglikemia = new widget.CekBox();
        chkSdkiHiperglikemia = new widget.CekBox();
        chkSdkiPenggunaanKorti = new widget.CekBox();
        chkSdkiNutrisi = new widget.CekBox();
        chkSikiMonitorIntake = new widget.CekBox();
        chkSdkiGangguanGlukosa = new widget.CekBox();
        chkSdkiDisfungsiGinjal = new widget.CekBox();
        chkSdkiEfek = new widget.CekBox();
        chkSdkiTindakan = new widget.CekBox();
        chkSdkiGangguanMetabolik = new widget.CekBox();
        jLabel92 = new widget.Label();
        chkSdkiKetoasidosis = new widget.CekBox();
        chkSdkiDiabetesGestasional = new widget.CekBox();
        jLabel77 = new widget.Label();
        chkSdkiKadarRendah = new widget.CekBox();
        jLabel80 = new widget.Label();
        jLabel85 = new widget.Label();
        chkSdkiMengeluh = new widget.CekBox();
        chkSdkiGemetar = new widget.CekBox();
        chkSdkiKesadaran = new widget.CekBox();
        chkSdkiSulit = new widget.CekBox();
        chkSdkiPerilaku = new widget.CekBox();
        chkSdkiBerkeringat = new widget.CekBox();
        jLabel86 = new widget.Label();
        chkSdkiJumlah = new widget.CekBox();
        chkSdkiHaus = new widget.CekBox();
        jLabel87 = new widget.Label();
        chkSlkiMengantuk = new widget.CekBox();
        chkSlkiPusing = new widget.CekBox();
        chkSlkiGemetar = new widget.CekBox();
        chkSlkiBerkeringat = new widget.CekBox();
        chkSlkiRasa = new widget.CekBox();
        chkSlkiKadar = new widget.CekBox();
        chkSlkiJumlah = new widget.CekBox();
        chkSikiBerikan = new widget.CekBox();
        chkSikiKonsultasi = new widget.CekBox();
        chkSikiFasilitasi = new widget.CekBox();
        chkSikiAnjurMenghindari = new widget.CekBox();
        chkSikiAnjurMonitor = new widget.CekBox();
        chkSikiAnjurKepatuhan = new widget.CekBox();
        chkSikiAjarkanIndikasi = new widget.CekBox();
        chkSikiAjarkanPengelolaan = new widget.CekBox();
        jLabel88 = new widget.Label();
        chkSikiKolabInsulin = new widget.CekBox();
        chkSikiKolabIv = new widget.CekBox();
        chkSikiKolabKalium = new widget.CekBox();
        PanelInput1 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbMasalah = new widget.Table();
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
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Masalah Keperawatan Ketidakstabilan Glukosa Darah ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass13.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(600, 402));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(760, 1486));
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
        TrgRawat.setBounds(145, 38, 380, 23);

        label20.setForeground(new java.awt.Color(0, 0, 0));
        label20.setText("Nama Petugas :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label20);
        label20.setBounds(0, 1444, 140, 23);

        TnipPetugas.setEditable(false);
        TnipPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnipPetugas.setName("TnipPetugas"); // NOI18N
        TnipPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipPetugas);
        TnipPetugas.setBounds(145, 1444, 150, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        TnmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(300, 1444, 360, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('2');
        BtnPetugas.setToolTipText("Alt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(665, 1444, 28, 23);

        chkSdkiDisfungsiPankreas.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiDisfungsiPankreas.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiDisfungsiPankreas.setText("Disfungsi pankreas");
        chkSdkiDisfungsiPankreas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiDisfungsiPankreas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiDisfungsiPankreas.setName("chkSdkiDisfungsiPankreas"); // NOI18N
        chkSdkiDisfungsiPankreas.setOpaque(false);
        chkSdkiDisfungsiPankreas.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiDisfungsiPankreas);
        chkSdkiDisfungsiPankreas.setBounds(145, 122, 150, 23);

        chkSdkiResistensi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiResistensi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiResistensi.setText("Resistensi insulin");
        chkSdkiResistensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiResistensi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiResistensi.setName("chkSdkiResistensi"); // NOI18N
        chkSdkiResistensi.setOpaque(false);
        chkSdkiResistensi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiResistensi);
        chkSdkiResistensi.setBounds(145, 150, 120, 23);

        chkSdkiPenggunaanInsulin.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPenggunaanInsulin.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPenggunaanInsulin.setText("Penggunaan insulin atau obat glikemik oral");
        chkSdkiPenggunaanInsulin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPenggunaanInsulin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPenggunaanInsulin.setName("chkSdkiPenggunaanInsulin"); // NOI18N
        chkSdkiPenggunaanInsulin.setOpaque(false);
        chkSdkiPenggunaanInsulin.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPenggunaanInsulin);
        chkSdkiPenggunaanInsulin.setBounds(145, 206, 240, 23);

        chkSdkiHiperinsulin.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiHiperinsulin.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiHiperinsulin.setText("Hiperinsulinemia (mis.insulinoma)");
        chkSdkiHiperinsulin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiHiperinsulin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiHiperinsulin.setName("chkSdkiHiperinsulin"); // NOI18N
        chkSdkiHiperinsulin.setOpaque(false);
        chkSdkiHiperinsulin.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiHiperinsulin);
        chkSdkiHiperinsulin.setBounds(145, 234, 190, 23);

        chkSdkiEndokrin.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiEndokrin.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiEndokrin.setText("Endokrinopati (mis.kerusakan adrenal atau pituitari)");
        chkSdkiEndokrin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiEndokrin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiEndokrin.setName("chkSdkiEndokrin"); // NOI18N
        chkSdkiEndokrin.setOpaque(false);
        chkSdkiEndokrin.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiEndokrin);
        chkSdkiEndokrin.setBounds(145, 262, 280, 23);

        chkSdkiLelah.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiLelah.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiLelah.setText("Lelah atau lesu");
        chkSdkiLelah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiLelah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiLelah.setName("chkSdkiLelah"); // NOI18N
        chkSdkiLelah.setOpaque(false);
        chkSdkiLelah.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSdkiLelah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSdkiLelahActionPerformed(evt);
            }
        });
        FormInput.add(chkSdkiLelah);
        chkSdkiLelah.setBounds(145, 402, 105, 23);

        chkSdkiPalpitasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPalpitasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPalpitasi.setText("Palpitasi");
        chkSdkiPalpitasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPalpitasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPalpitasi.setName("chkSdkiPalpitasi"); // NOI18N
        chkSdkiPalpitasi.setOpaque(false);
        chkSdkiPalpitasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPalpitasi);
        chkSdkiPalpitasi.setBounds(145, 458, 80, 23);

        chkSdkiKadarTinggi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKadarTinggi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKadarTinggi.setText("Kadar glukosa dalam darah/urin tinggi");
        chkSdkiKadarTinggi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKadarTinggi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKadarTinggi.setName("chkSdkiKadarTinggi"); // NOI18N
        chkSdkiKadarTinggi.setOpaque(false);
        chkSdkiKadarTinggi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKadarTinggi);
        chkSdkiKadarTinggi.setBounds(260, 402, 220, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Diagnosis Keperawatan SDKI : Ketidakstabilan glukosa darah (D.0027)");
        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 66, 450, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("<html><b>Berhubungan Dengan :</b> Hiperglikemia</html>");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 94, 250, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("<html><b>DO/DS (Gejala dan tanda Mayor) :</b> Hipoglikemia</html>");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(145, 318, 280, 23);

        chkSdkiGangguanToleransi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiGangguanToleransi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiGangguanToleransi.setText("Gangguan toleransi glukosa darah");
        chkSdkiGangguanToleransi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiGangguanToleransi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiGangguanToleransi.setName("chkSdkiGangguanToleransi"); // NOI18N
        chkSdkiGangguanToleransi.setOpaque(false);
        chkSdkiGangguanToleransi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiGangguanToleransi);
        chkSdkiGangguanToleransi.setBounds(350, 122, 200, 23);

        chkSdkiMengantuk.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMengantuk.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMengantuk.setText("Mengantuk");
        chkSdkiMengantuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMengantuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMengantuk.setName("chkSdkiMengantuk"); // NOI18N
        chkSdkiMengantuk.setOpaque(false);
        chkSdkiMengantuk.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMengantuk);
        chkSdkiMengantuk.setBounds(145, 346, 85, 23);

        chkSdkiPusing.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPusing.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPusing.setText("Pusing");
        chkSdkiPusing.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPusing.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPusing.setName("chkSdkiPusing"); // NOI18N
        chkSdkiPusing.setOpaque(false);
        chkSdkiPusing.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPusing);
        chkSdkiPusing.setBounds(240, 346, 60, 23);

        chkSdkiGangguanKordinasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiGangguanKordinasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiGangguanKordinasi.setText("Gangguan koordinasi");
        chkSdkiGangguanKordinasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiGangguanKordinasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiGangguanKordinasi.setName("chkSdkiGangguanKordinasi"); // NOI18N
        chkSdkiGangguanKordinasi.setOpaque(false);
        chkSdkiGangguanKordinasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiGangguanKordinasi);
        chkSdkiGangguanKordinasi.setBounds(310, 346, 130, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("(Rencana Keperawatan) Tujuan dan Kriteria Hasil SLKI :");
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 654, 370, 23);

        TketSelama.setForeground(new java.awt.Color(0, 0, 0));
        TketSelama.setName("TketSelama"); // NOI18N
        FormInput.add(TketSelama);
        TketSelama.setBounds(380, 710, 100, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("(Rencana Keperawatan) Intervensi Keperawatan SIKI : Manajemen Hiperglikemia (I.03115)");
        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 878, 580, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("Tindakan Observasi :");
        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(145, 905, 140, 23);

        chkSdkiMulut.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMulut.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMulut.setText("Mulut kering");
        chkSdkiMulut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMulut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMulut.setName("chkSdkiMulut"); // NOI18N
        chkSdkiMulut.setOpaque(false);
        chkSdkiMulut.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMulut);
        chkSdkiMulut.setBounds(145, 542, 100, 23);

        chkSdkiDiabetesMellitus.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiDiabetesMellitus.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiDiabetesMellitus.setText("Diabetes mellitus");
        chkSdkiDiabetesMellitus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiDiabetesMellitus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiDiabetesMellitus.setName("chkSdkiDiabetesMellitus"); // NOI18N
        chkSdkiDiabetesMellitus.setOpaque(false);
        chkSdkiDiabetesMellitus.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiDiabetesMellitus);
        chkSdkiDiabetesMellitus.setBounds(145, 598, 110, 23);

        chkSlkiKesadaran.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKesadaran.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKesadaran.setText("Kesadaran meningkat");
        chkSlkiKesadaran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKesadaran.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKesadaran.setName("chkSlkiKesadaran"); // NOI18N
        chkSlkiKesadaran.setOpaque(false);
        chkSlkiKesadaran.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKesadaran);
        chkSlkiKesadaran.setBounds(145, 738, 140, 23);

        chkSlkiLelah.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiLelah.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiLelah.setText("Lelah/lesu menurun");
        chkSlkiLelah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiLelah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiLelah.setName("chkSlkiLelah"); // NOI18N
        chkSlkiLelah.setOpaque(false);
        chkSlkiLelah.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiLelah);
        chkSlkiLelah.setBounds(145, 822, 130, 23);

        chkSlkiKeluhan.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKeluhan.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKeluhan.setText("Keluhan lapar menurun");
        chkSlkiKeluhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKeluhan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKeluhan.setName("chkSlkiKeluhan"); // NOI18N
        chkSlkiKeluhan.setOpaque(false);
        chkSlkiKeluhan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKeluhan);
        chkSlkiKeluhan.setBounds(145, 850, 150, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("Terapeutik :");
        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(145, 1101, 90, 23);

        chkSikiIdenKemungkinan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiIdenKemungkinan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiIdenKemungkinan.setText("Identifikasi kemungkinan penyebab hiperglikemia");
        chkSikiIdenKemungkinan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiIdenKemungkinan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiIdenKemungkinan.setName("chkSikiIdenKemungkinan"); // NOI18N
        chkSikiIdenKemungkinan.setOpaque(false);
        chkSikiIdenKemungkinan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiIdenKemungkinan);
        chkSikiIdenKemungkinan.setBounds(145, 933, 270, 23);

        chkSikiIdenSituasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiIdenSituasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiIdenSituasi.setText("Identifikasi situasi yang menyebabkan kebutuhan insulin meningkat (mis. Penyakit kambuhan)");
        chkSikiIdenSituasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiIdenSituasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiIdenSituasi.setName("chkSikiIdenSituasi"); // NOI18N
        chkSikiIdenSituasi.setOpaque(false);
        chkSikiIdenSituasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiIdenSituasi);
        chkSikiIdenSituasi.setBounds(145, 961, 480, 23);

        chkSikiMonitorKadar.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiMonitorKadar.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiMonitorKadar.setText("Monitor kadar glukosa darah jika perlu");
        chkSikiMonitorKadar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiMonitorKadar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiMonitorKadar.setName("chkSikiMonitorKadar"); // NOI18N
        chkSikiMonitorKadar.setOpaque(false);
        chkSikiMonitorKadar.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiMonitorKadar);
        chkSikiMonitorKadar.setBounds(145, 989, 220, 23);

        chkSikiMonitorTanda.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiMonitorTanda.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiMonitorTanda.setText("Monitor tanda dan gejala hiperglikemia (mis. Poliuria, polidipsia, polifagia, kelemahan, malaise, pandangan kabur, dan sakit kepala)");
        chkSikiMonitorTanda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiMonitorTanda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiMonitorTanda.setName("chkSikiMonitorTanda"); // NOI18N
        chkSikiMonitorTanda.setOpaque(false);
        chkSikiMonitorTanda.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiMonitorTanda);
        chkSikiMonitorTanda.setBounds(145, 1017, 660, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("Edukasi :");
        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(145, 1213, 90, 23);

        chkSikiMonitorKeton.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiMonitorKeton.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiMonitorKeton.setText("Monitor keton urin, AGD, elektrolit, tekanan darah ortostatik, dan frekuensi nadi");
        chkSikiMonitorKeton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiMonitorKeton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiMonitorKeton.setName("chkSikiMonitorKeton"); // NOI18N
        chkSikiMonitorKeton.setOpaque(false);
        chkSikiMonitorKeton.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiMonitorKeton);
        chkSikiMonitorKeton.setBounds(145, 1073, 420, 23);

        chkSdkiDisfungsiHati.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiDisfungsiHati.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiDisfungsiHati.setText("Disfungsi hati");
        chkSdkiDisfungsiHati.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiDisfungsiHati.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiDisfungsiHati.setName("chkSdkiDisfungsiHati"); // NOI18N
        chkSdkiDisfungsiHati.setOpaque(false);
        chkSdkiDisfungsiHati.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiDisfungsiHati);
        chkSdkiDisfungsiHati.setBounds(145, 290, 100, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel91.setText("Setelah dilakukan tindakan keperawatan selama");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(145, 710, 235, 23);

        chkSlkiKestabilan.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKestabilan.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKestabilan.setText("Kestabilan Kadar Glukosa Darah (L.03022)");
        chkSlkiKestabilan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKestabilan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKestabilan.setName("chkSlkiKestabilan"); // NOI18N
        chkSlkiKestabilan.setOpaque(false);
        chkSlkiKestabilan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKestabilan);
        chkSlkiKestabilan.setBounds(145, 682, 240, 23);

        chkSdkiHipoglikemia.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiHipoglikemia.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiHipoglikemia.setText("Hipoglikemia");
        chkSdkiHipoglikemia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiHipoglikemia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiHipoglikemia.setName("chkSdkiHipoglikemia"); // NOI18N
        chkSdkiHipoglikemia.setOpaque(false);
        chkSdkiHipoglikemia.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiHipoglikemia);
        chkSdkiHipoglikemia.setBounds(290, 598, 100, 23);

        chkSdkiHiperglikemia.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiHiperglikemia.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiHiperglikemia.setText("Hiperglikemia");
        chkSdkiHiperglikemia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiHiperglikemia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiHiperglikemia.setName("chkSdkiHiperglikemia"); // NOI18N
        chkSdkiHiperglikemia.setOpaque(false);
        chkSdkiHiperglikemia.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiHiperglikemia);
        chkSdkiHiperglikemia.setBounds(290, 626, 100, 23);

        chkSdkiPenggunaanKorti.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPenggunaanKorti.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPenggunaanKorti.setText("Penggunaan kortikosteroid");
        chkSdkiPenggunaanKorti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPenggunaanKorti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPenggunaanKorti.setName("chkSdkiPenggunaanKorti"); // NOI18N
        chkSdkiPenggunaanKorti.setOpaque(false);
        chkSdkiPenggunaanKorti.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPenggunaanKorti);
        chkSdkiPenggunaanKorti.setBounds(400, 598, 160, 23);

        chkSdkiNutrisi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiNutrisi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiNutrisi.setText("Nutrisi Parenteral total");
        chkSdkiNutrisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiNutrisi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiNutrisi.setName("chkSdkiNutrisi"); // NOI18N
        chkSdkiNutrisi.setOpaque(false);
        chkSdkiNutrisi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiNutrisi);
        chkSdkiNutrisi.setBounds(400, 626, 140, 23);

        chkSikiMonitorIntake.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiMonitorIntake.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiMonitorIntake.setText("Monitor intake dan output cairan");
        chkSikiMonitorIntake.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiMonitorIntake.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiMonitorIntake.setName("chkSikiMonitorIntake"); // NOI18N
        chkSikiMonitorIntake.setOpaque(false);
        chkSikiMonitorIntake.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiMonitorIntake);
        chkSikiMonitorIntake.setBounds(145, 1045, 190, 23);

        chkSdkiGangguanGlukosa.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiGangguanGlukosa.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiGangguanGlukosa.setText("Gangguan glukosa darah puasa");
        chkSdkiGangguanGlukosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiGangguanGlukosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiGangguanGlukosa.setName("chkSdkiGangguanGlukosa"); // NOI18N
        chkSdkiGangguanGlukosa.setOpaque(false);
        chkSdkiGangguanGlukosa.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiGangguanGlukosa);
        chkSdkiGangguanGlukosa.setBounds(350, 150, 200, 23);

        chkSdkiDisfungsiGinjal.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiDisfungsiGinjal.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiDisfungsiGinjal.setText("Disfungsi ginjal kronis");
        chkSdkiDisfungsiGinjal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiDisfungsiGinjal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiDisfungsiGinjal.setName("chkSdkiDisfungsiGinjal"); // NOI18N
        chkSdkiDisfungsiGinjal.setOpaque(false);
        chkSdkiDisfungsiGinjal.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiDisfungsiGinjal);
        chkSdkiDisfungsiGinjal.setBounds(450, 206, 140, 23);

        chkSdkiEfek.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiEfek.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiEfek.setText("Efek agen farmakologis");
        chkSdkiEfek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiEfek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiEfek.setName("chkSdkiEfek"); // NOI18N
        chkSdkiEfek.setOpaque(false);
        chkSdkiEfek.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiEfek);
        chkSdkiEfek.setBounds(450, 234, 150, 23);

        chkSdkiTindakan.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTindakan.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTindakan.setText("Tindakan pembedahan neoplasma");
        chkSdkiTindakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTindakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTindakan.setName("chkSdkiTindakan"); // NOI18N
        chkSdkiTindakan.setOpaque(false);
        chkSdkiTindakan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTindakan);
        chkSdkiTindakan.setBounds(450, 262, 190, 23);

        chkSdkiGangguanMetabolik.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiGangguanMetabolik.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiGangguanMetabolik.setText("<html>Gangguan metabolik bawaan (mis.gangguan penyimpanan lisosomal, galaktosemia, gangguan penyimpanan glikogen)</html>");
        chkSdkiGangguanMetabolik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiGangguanMetabolik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiGangguanMetabolik.setName("chkSdkiGangguanMetabolik"); // NOI18N
        chkSdkiGangguanMetabolik.setOpaque(false);
        chkSdkiGangguanMetabolik.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiGangguanMetabolik);
        chkSdkiGangguanMetabolik.setBounds(450, 290, 390, 30);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel92.setText("<html>Masalah ketidakstabilan glukosa darah pasien teratasi dengan kriteria hasil :</html>");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(486, 710, 230, 30);

        chkSdkiKetoasidosis.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKetoasidosis.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKetoasidosis.setText("Ketoasidosis diabetic");
        chkSdkiKetoasidosis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKetoasidosis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKetoasidosis.setName("chkSdkiKetoasidosis"); // NOI18N
        chkSdkiKetoasidosis.setOpaque(false);
        chkSdkiKetoasidosis.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKetoasidosis);
        chkSdkiKetoasidosis.setBounds(145, 626, 130, 23);

        chkSdkiDiabetesGestasional.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiDiabetesGestasional.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiDiabetesGestasional.setText("Diabetes gestasional");
        chkSdkiDiabetesGestasional.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiDiabetesGestasional.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiDiabetesGestasional.setName("chkSdkiDiabetesGestasional"); // NOI18N
        chkSdkiDiabetesGestasional.setOpaque(false);
        chkSdkiDiabetesGestasional.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiDiabetesGestasional);
        chkSdkiDiabetesGestasional.setBounds(570, 598, 130, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("<html><b>Berhubungan Dengan :</b> Hipoglikemia</html>");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 178, 250, 23);

        chkSdkiKadarRendah.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKadarRendah.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKadarRendah.setText("Kadar glukosa dalam darah/urin rendah");
        chkSdkiKadarRendah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKadarRendah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKadarRendah.setName("chkSdkiKadarRendah"); // NOI18N
        chkSdkiKadarRendah.setOpaque(false);
        chkSdkiKadarRendah.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKadarRendah);
        chkSdkiKadarRendah.setBounds(450, 346, 220, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("<html><b>DO/DS (Gejala dan tanda Mayor) :</b> Hiperglikemia</html>");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(145, 374, 280, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel85.setText("<html><b>DO/DS (Gejala dan tanda Minor) :</b> Hipoglikemia</html>");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(145, 430, 280, 23);

        chkSdkiMengeluh.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMengeluh.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMengeluh.setText("Mengeluh lapar");
        chkSdkiMengeluh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMengeluh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMengeluh.setName("chkSdkiMengeluh"); // NOI18N
        chkSdkiMengeluh.setOpaque(false);
        chkSdkiMengeluh.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMengeluh);
        chkSdkiMengeluh.setBounds(145, 486, 100, 23);

        chkSdkiGemetar.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiGemetar.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiGemetar.setText("Gemetar");
        chkSdkiGemetar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiGemetar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiGemetar.setName("chkSdkiGemetar"); // NOI18N
        chkSdkiGemetar.setOpaque(false);
        chkSdkiGemetar.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiGemetar);
        chkSdkiGemetar.setBounds(260, 458, 80, 23);

        chkSdkiKesadaran.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKesadaran.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKesadaran.setText("Kesadaran menurun");
        chkSdkiKesadaran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKesadaran.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKesadaran.setName("chkSdkiKesadaran"); // NOI18N
        chkSdkiKesadaran.setOpaque(false);
        chkSdkiKesadaran.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKesadaran);
        chkSdkiKesadaran.setBounds(260, 486, 130, 23);

        chkSdkiSulit.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiSulit.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiSulit.setText("Sulit bicara");
        chkSdkiSulit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiSulit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiSulit.setName("chkSdkiSulit"); // NOI18N
        chkSdkiSulit.setOpaque(false);
        chkSdkiSulit.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiSulit);
        chkSdkiSulit.setBounds(400, 486, 90, 23);

        chkSdkiPerilaku.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPerilaku.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPerilaku.setText("Perilaku aneh");
        chkSdkiPerilaku.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPerilaku.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPerilaku.setName("chkSdkiPerilaku"); // NOI18N
        chkSdkiPerilaku.setOpaque(false);
        chkSdkiPerilaku.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPerilaku);
        chkSdkiPerilaku.setBounds(400, 458, 100, 23);

        chkSdkiBerkeringat.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiBerkeringat.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiBerkeringat.setText("Berkeringat");
        chkSdkiBerkeringat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiBerkeringat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiBerkeringat.setName("chkSdkiBerkeringat"); // NOI18N
        chkSdkiBerkeringat.setOpaque(false);
        chkSdkiBerkeringat.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiBerkeringat);
        chkSdkiBerkeringat.setBounds(520, 458, 90, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel86.setText("<html><b>DO/DS (Gejala dan tanda Minor) :</b> Hiperglikemia</html>");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(145, 514, 280, 23);

        chkSdkiJumlah.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiJumlah.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiJumlah.setText("Jumlah urin meningkat");
        chkSdkiJumlah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiJumlah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiJumlah.setName("chkSdkiJumlah"); // NOI18N
        chkSdkiJumlah.setOpaque(false);
        chkSdkiJumlah.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiJumlah);
        chkSdkiJumlah.setBounds(260, 542, 140, 23);

        chkSdkiHaus.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiHaus.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiHaus.setText("Haus meningkat");
        chkSdkiHaus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiHaus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiHaus.setName("chkSdkiHaus"); // NOI18N
        chkSdkiHaus.setOpaque(false);
        chkSdkiHaus.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiHaus);
        chkSdkiHaus.setBounds(410, 542, 110, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel87.setText("Kondisi Klinis Terkait :");
        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(145, 570, 140, 23);

        chkSlkiMengantuk.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiMengantuk.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiMengantuk.setText("Mengantuk menurun");
        chkSlkiMengantuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiMengantuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiMengantuk.setName("chkSlkiMengantuk"); // NOI18N
        chkSlkiMengantuk.setOpaque(false);
        chkSlkiMengantuk.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiMengantuk);
        chkSlkiMengantuk.setBounds(145, 766, 130, 23);

        chkSlkiPusing.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiPusing.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiPusing.setText("Pusing menurun");
        chkSlkiPusing.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiPusing.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiPusing.setName("chkSlkiPusing"); // NOI18N
        chkSlkiPusing.setOpaque(false);
        chkSlkiPusing.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiPusing);
        chkSlkiPusing.setBounds(145, 794, 110, 23);

        chkSlkiGemetar.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiGemetar.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiGemetar.setText("Gemetar menurun");
        chkSlkiGemetar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiGemetar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiGemetar.setName("chkSlkiGemetar"); // NOI18N
        chkSlkiGemetar.setOpaque(false);
        chkSlkiGemetar.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiGemetar);
        chkSlkiGemetar.setBounds(310, 738, 120, 23);

        chkSlkiBerkeringat.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiBerkeringat.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiBerkeringat.setText("Berkeringat menurun");
        chkSlkiBerkeringat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiBerkeringat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiBerkeringat.setName("chkSlkiBerkeringat"); // NOI18N
        chkSlkiBerkeringat.setOpaque(false);
        chkSlkiBerkeringat.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiBerkeringat);
        chkSlkiBerkeringat.setBounds(310, 766, 130, 23);

        chkSlkiRasa.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiRasa.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiRasa.setText("Rasa haus menurun");
        chkSlkiRasa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiRasa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiRasa.setName("chkSlkiRasa"); // NOI18N
        chkSlkiRasa.setOpaque(false);
        chkSlkiRasa.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiRasa);
        chkSlkiRasa.setBounds(310, 794, 130, 23);

        chkSlkiKadar.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKadar.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKadar.setText("Kadar glukosa dalam darah membaik");
        chkSlkiKadar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKadar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKadar.setName("chkSlkiKadar"); // NOI18N
        chkSlkiKadar.setOpaque(false);
        chkSlkiKadar.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKadar);
        chkSlkiKadar.setBounds(310, 822, 210, 23);

        chkSlkiJumlah.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiJumlah.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiJumlah.setText("Jumlah urine membaik");
        chkSlkiJumlah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiJumlah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiJumlah.setName("chkSlkiJumlah"); // NOI18N
        chkSlkiJumlah.setOpaque(false);
        chkSlkiJumlah.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiJumlah);
        chkSlkiJumlah.setBounds(310, 850, 140, 23);

        chkSikiBerikan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiBerikan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiBerikan.setText("Berikan asupan cairan oral");
        chkSikiBerikan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiBerikan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiBerikan.setName("chkSikiBerikan"); // NOI18N
        chkSikiBerikan.setOpaque(false);
        chkSikiBerikan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiBerikan);
        chkSikiBerikan.setBounds(145, 1129, 160, 23);

        chkSikiKonsultasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiKonsultasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiKonsultasi.setText("Konsultasi dengan medis jika tanda dan gejala hiperglikemia tetap ada dan memburu");
        chkSikiKonsultasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiKonsultasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiKonsultasi.setName("chkSikiKonsultasi"); // NOI18N
        chkSikiKonsultasi.setOpaque(false);
        chkSikiKonsultasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiKonsultasi);
        chkSikiKonsultasi.setBounds(145, 1157, 440, 23);

        chkSikiFasilitasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiFasilitasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiFasilitasi.setText("Fasilitasi ambulasi jika ada hipotensi ortostatik");
        chkSikiFasilitasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiFasilitasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiFasilitasi.setName("chkSikiFasilitasi"); // NOI18N
        chkSikiFasilitasi.setOpaque(false);
        chkSikiFasilitasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiFasilitasi);
        chkSikiFasilitasi.setBounds(145, 1185, 250, 23);

        chkSikiAnjurMenghindari.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAnjurMenghindari.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAnjurMenghindari.setText("Anjurkan menghindari olahraga saat kadar glukosa darah >250 mg/dl");
        chkSikiAnjurMenghindari.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAnjurMenghindari.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAnjurMenghindari.setName("chkSikiAnjurMenghindari"); // NOI18N
        chkSikiAnjurMenghindari.setOpaque(false);
        chkSikiAnjurMenghindari.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAnjurMenghindari);
        chkSikiAnjurMenghindari.setBounds(145, 1241, 370, 23);

        chkSikiAnjurMonitor.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAnjurMonitor.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAnjurMonitor.setText("Anjurkan monitor kadar glukosa darah secara mandiri");
        chkSikiAnjurMonitor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAnjurMonitor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAnjurMonitor.setName("chkSikiAnjurMonitor"); // NOI18N
        chkSikiAnjurMonitor.setOpaque(false);
        chkSikiAnjurMonitor.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAnjurMonitor);
        chkSikiAnjurMonitor.setBounds(145, 1269, 290, 23);

        chkSikiAnjurKepatuhan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAnjurKepatuhan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAnjurKepatuhan.setText("Anjurkan kepatuhan terhadap diet dan olahraga");
        chkSikiAnjurKepatuhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAnjurKepatuhan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAnjurKepatuhan.setName("chkSikiAnjurKepatuhan"); // NOI18N
        chkSikiAnjurKepatuhan.setOpaque(false);
        chkSikiAnjurKepatuhan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAnjurKepatuhan);
        chkSikiAnjurKepatuhan.setBounds(145, 1297, 270, 23);

        chkSikiAjarkanIndikasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAjarkanIndikasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAjarkanIndikasi.setText("Ajarkan indikasi dan pentingnya pengujian keton urin jika perlu");
        chkSikiAjarkanIndikasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAjarkanIndikasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAjarkanIndikasi.setName("chkSikiAjarkanIndikasi"); // NOI18N
        chkSikiAjarkanIndikasi.setOpaque(false);
        chkSikiAjarkanIndikasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAjarkanIndikasi);
        chkSikiAjarkanIndikasi.setBounds(145, 1325, 330, 23);

        chkSikiAjarkanPengelolaan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAjarkanPengelolaan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAjarkanPengelolaan.setText("<html>Ajarkan pengelolaan diabetes (mis. Penggunaan insukin, obat oral, monitor asupan cairan, penggantian karbohidrat, dan bantuan profesional kesehatan)</html>");
        chkSikiAjarkanPengelolaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAjarkanPengelolaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAjarkanPengelolaan.setName("chkSikiAjarkanPengelolaan"); // NOI18N
        chkSikiAjarkanPengelolaan.setOpaque(false);
        chkSikiAjarkanPengelolaan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAjarkanPengelolaan);
        chkSikiAjarkanPengelolaan.setBounds(145, 1353, 650, 30);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel88.setText("Kolaborasi :");
        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(145, 1388, 90, 23);

        chkSikiKolabInsulin.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiKolabInsulin.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiKolabInsulin.setText("Kolaborasi pemberian insulin jika perlu");
        chkSikiKolabInsulin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiKolabInsulin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiKolabInsulin.setName("chkSikiKolabInsulin"); // NOI18N
        chkSikiKolabInsulin.setOpaque(false);
        chkSikiKolabInsulin.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiKolabInsulin);
        chkSikiKolabInsulin.setBounds(145, 1416, 210, 23);

        chkSikiKolabIv.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiKolabIv.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiKolabIv.setText("Kolaborasi pemberian IV jika perlu");
        chkSikiKolabIv.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiKolabIv.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiKolabIv.setName("chkSikiKolabIv"); // NOI18N
        chkSikiKolabIv.setOpaque(false);
        chkSikiKolabIv.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiKolabIv);
        chkSikiKolabIv.setBounds(370, 1416, 190, 23);

        chkSikiKolabKalium.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiKolabKalium.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiKolabKalium.setText("Kolaborasi pemberian kalium jika perlu");
        chkSikiKolabKalium.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiKolabKalium.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiKolabKalium.setName("chkSikiKolabKalium"); // NOI18N
        chkSikiKolabKalium.setOpaque(false);
        chkSikiKolabKalium.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiKolabKalium);
        chkSikiKolabKalium.setBounds(575, 1416, 220, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass13.add(Scroll1);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data MasKep Ketidakstabilan Glukosa Darah ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(700, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbMasalah.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbMasalah.setName("tbMasalah"); // NOI18N
        tbMasalah.getTableHeader().setReorderingAllowed(false);
        tbMasalah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMasalahMouseClicked(evt);
            }
        });
        tbMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMasalahKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbMasalah);

        PanelInput1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 86));
        panelGlass11.setLayout(new java.awt.BorderLayout());

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Simpan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-07-2026" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-07-2026" }));
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

        panelGlass13.add(PanelInput1);

        internalFrame1.add(panelGlass13, java.awt.BorderLayout.CENTER);

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

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (TrgRawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data ruang rawat harus terisi, tutup dulu lalu buka lagi....");
        } else {
            cekData();
            if (Sequel.menyimpantf("masalah_keperawatan_ketidakstabilan_glukosa_darah", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 69, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), sdkiDisfungsiPankreas, sdkiResistensi, sdkiGangguanToleransi, sdkiGangguanGlukosa, sdkiPenggunaanInsulin,
                        sdkiHiperinsulin, sdkiEndokrin, sdkiDisfungsiHati, sdkiDisfungsiGinjal, sdkiEfek, sdkiTindakan, sdkiGangguanMetabolik, sdkiMengantuk, sdkiPusing,
                        sdkiGangguanKordinasi, sdkiKadarRendah, sdkiLelah, sdkiKadarTinggi, sdkiPalpitasi, sdkiMengeluh, sdkiGemetar, sdkiKesadaran, sdkiPerilaku, sdkiSulit,
                        sdkiBerkeringat, sdkiMulut, sdkiJumlah, sdkiHaus, sdkiDiabetesMellitus, sdkiKetoasidosis, sdkiHipoglikemia, sdkiHiperglikemia, sdkiPenggunaanKorti,
                        sdkiNutrisi, sdkiDiabetesGestasional, slkiKestabilan, TketSelama.getText(), slkiKesadaran, slkiMengantuk, slkiPusing, slkiLelah, slkiKeluhan, slkiGemetar,
                        slkiBerkeringat, slkiRasa, slkiKadar, slkiJumlah, sikiIdenKemungkinan, sikiIdenSituasi, sikiMonitorKadar, sikiMonitorTanda, sikiMonitorIntake,
                        sikiMonitorKeton, sikiBerikan, sikiKonsultasi, sikiFasilitasi, sikiAnjurMenghindari, sikiAnjurMonitor, sikiAnjurKepatuhan, sikiAjarkanIndikasi,
                        sikiAjarkanPengelolaan, sikiKolabInsulin, sikiKolabIv, sikiKolabKalium, sttsRawat, TnipPetugas.getText(), Sequel.cariIsi("select now()")
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Masalah Keperawatan Ketidakstabilan Glukosa Darah", "Simpan");
                TCari.setText(TNoRw.getText());
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
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (TrgRawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data ruang rawat harus terisi, tutup dulu lalu buka lagi....");
        } else {
            if (tbMasalah.getSelectedRow() > -1) {
                cekData();
                if (Sequel.mengedittf("masalah_keperawatan_ketidakstabilan_glukosa_darah", "waktu_simpan=?", "sdki_disfungsi_pankreas=?, sdki_resistensi=?, sdki_gangguan_toleransi=?, "
                        + "sdki_gangguan_glukosa=?, sdki_penggunaan_insulin=?, sdki_hiperinsulin=?, sdki_endokrin=?, sdki_disfungsi_hati=?, sdki_disfungsi_ginjal=?, sdki_efek=?, "
                        + "sdki_tindakan=?, sdki_gangguan_metabolik=?, sdki_mengantuk=?, sdki_pusing=?, sdki_gangguan_kordinasi=?, sdki_kadar_rendah=?, sdki_lelah=?, sdki_kadar_tinggi=?, "
                        + "sdki_palpitasi=?, sdki_mengeluh=?, sdki_gemetar=?, sdki_kesadaran=?, sdki_perilaku=?, sdki_sulit=?, sdki_berkeringat=?, sdki_mulut=?, sdki_jumlah=?, "
                        + "sdki_haus=?, sdki_diabetes_mellitus=?, sdki_ketoasidosis=?, sdki_hipoglikemia=?, sdki_hiperglikemia=?, sdki_penggunaan_korti=?, sdki_nutrisi=?, "
                        + "sdki_diabetes_gestasional=?, slki_kestabilan=?, ket_selama=?, slki_kesadaran=?, slki_mengantuk=?, slki_pusing=?, slki_lelah=?, slki_keluhan=?, slki_gemetar=?, "
                        + "slki_berkeringat=?, slki_rasa=?, slki_kadar=?, slki_jumlah=?, siki_iden_kemungkinan=?, siki_iden_situasi=?, siki_monitor_kadar=?, siki_monitor_tanda=?, "
                        + "siki_monitor_intake=?, siki_monitor_keton=?, siki_berikan=?, siki_konsultasi=?, siki_fasilitasi=?, siki_anjur_menghindari=?, siki_anjur_monitor=?, "
                        + "siki_anjur_kepatuhan=?, siki_ajarkan_indikasi=?, siki_ajarkan_pengelolaan=?, siki_kolab_insulin=?, siki_kolab_iv=?, siki_kolab_kalium=?, "
                        + "nip_petugas=?", 66, new String[]{
                            sdkiDisfungsiPankreas, sdkiResistensi, sdkiGangguanToleransi, sdkiGangguanGlukosa, sdkiPenggunaanInsulin, sdkiHiperinsulin, sdkiEndokrin, sdkiDisfungsiHati,
                            sdkiDisfungsiGinjal, sdkiEfek, sdkiTindakan, sdkiGangguanMetabolik, sdkiMengantuk, sdkiPusing, sdkiGangguanKordinasi, sdkiKadarRendah, sdkiLelah,
                            sdkiKadarTinggi, sdkiPalpitasi, sdkiMengeluh, sdkiGemetar, sdkiKesadaran, sdkiPerilaku, sdkiSulit, sdkiBerkeringat, sdkiMulut, sdkiJumlah, sdkiHaus,
                            sdkiDiabetesMellitus, sdkiKetoasidosis, sdkiHipoglikemia, sdkiHiperglikemia, sdkiPenggunaanKorti, sdkiNutrisi, sdkiDiabetesGestasional, slkiKestabilan,
                            TketSelama.getText(), slkiKesadaran, slkiMengantuk, slkiPusing, slkiLelah, slkiKeluhan, slkiGemetar, slkiBerkeringat, slkiRasa, slkiKadar, slkiJumlah,
                            sikiIdenKemungkinan, sikiIdenSituasi, sikiMonitorKadar, sikiMonitorTanda, sikiMonitorIntake, sikiMonitorKeton, sikiBerikan, sikiKonsultasi, sikiFasilitasi,
                            sikiAnjurMenghindari, sikiAnjurMonitor, sikiAnjurKepatuhan, sikiAjarkanIndikasi, sikiAjarkanPengelolaan, sikiKolabInsulin, sikiKolabIv, sikiKolabKalium,
                            TnipPetugas.getText(),
                            tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 0).toString()
                        }) == true) {

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Masalah Keperawatan Ketidakstabilan Glukosa Darah", "Ganti");
                    TCari.setText(TNoRw.getText());
                    tampil();
                    emptTeks();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                tbMasalah.requestFocus();
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
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            frmUtama.getInstance().tutupDialogDiPanelUtama(this);
        } else {
            Valid.pindah(evt, BtnBatal, TCari);
        }
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

    private void tbMasalahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMasalahMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbMasalahMouseClicked

    private void tbMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasalahKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbMasalahKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        akses.setform("RMMasalahKeperawatanKetidakstabilanGlukosaDarah");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbMasalah.getSelectedRow() > -1) {
            if (akses.getadmin() == true || tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 73).toString().equals(akses.getkode())) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from masalah_keperawatan_ketidakstabilan_glukosa_darah where waktu_simpan=?", 1, new String[]{
                        tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 0).toString()
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
                JOptionPane.showMessageDialog(rootPane, "Maaf, data hanya bisa dihapus oleh " + tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 7).toString() + " ....");
                tbMasalah.requestFocus();
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbMasalah.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbMasalah.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));

            if (chkSdkiDisfungsiPankreas.isSelected() == true) {
                param.put("sdkiDisfungsiPankreas", "V");
            } else {
                param.put("sdkiDisfungsiPankreas", "");
            }

            if (chkSdkiResistensi.isSelected() == true) {
                param.put("sdkiResistensi", "V");
            } else {
                param.put("sdkiResistensi", "");
            }

            if (chkSdkiGangguanToleransi.isSelected() == true) {
                param.put("sdkiGangguanToleransi", "V");
            } else {
                param.put("sdkiGangguanToleransi", "");
            }

            if (chkSdkiGangguanGlukosa.isSelected() == true) {
                param.put("sdkiGangguanGlukosa", "V");
            } else {
                param.put("sdkiGangguanGlukosa", "");
            }

            if (chkSdkiPenggunaanInsulin.isSelected() == true) {
                param.put("sdkiPenggunaanInsulin", "V");
            } else {
                param.put("sdkiPenggunaanInsulin", "");
            }

            if (chkSdkiHiperinsulin.isSelected() == true) {
                param.put("sdkiHiperinsulin", "V");
            } else {
                param.put("sdkiHiperinsulin", "");
            }

            if (chkSdkiEndokrin.isSelected() == true) {
                param.put("sdkiEndokrin", "V");
            } else {
                param.put("sdkiEndokrin", "");
            }

            if (chkSdkiDisfungsiHati.isSelected() == true) {
                param.put("sdkiDisfungsiHati", "V");
            } else {
                param.put("sdkiDisfungsiHati", "");
            }

            if (chkSdkiDisfungsiGinjal.isSelected() == true) {
                param.put("sdkiDisfungsiGinjal", "V");
            } else {
                param.put("sdkiDisfungsiGinjal", "");
            }

            if (chkSdkiEfek.isSelected() == true) {
                param.put("sdkiEfek", "V");
            } else {
                param.put("sdkiEfek", "");
            }

            if (chkSdkiTindakan.isSelected() == true) {
                param.put("sdkiTindakan", "V");
            } else {
                param.put("sdkiTindakan", "");
            }

            if (chkSdkiGangguanMetabolik.isSelected() == true) {
                param.put("sdkiGangguanMetabolik", "V");
            } else {
                param.put("sdkiGangguanMetabolik", "");
            }

            if (chkSdkiMengantuk.isSelected() == true) {
                param.put("sdkiMengantuk", "V");
            } else {
                param.put("sdkiMengantuk", "");
            }

            if (chkSdkiPusing.isSelected() == true) {
                param.put("sdkiPusing", "V");
            } else {
                param.put("sdkiPusing", "");
            }

            if (chkSdkiGangguanKordinasi.isSelected() == true) {
                param.put("sdkiGangguanKordinasi", "V");
            } else {
                param.put("sdkiGangguanKordinasi", "");
            }

            if (chkSdkiKadarRendah.isSelected() == true) {
                param.put("sdkiKadarRendah", "V");
            } else {
                param.put("sdkiKadarRendah", "");
            }

            if (chkSdkiLelah.isSelected() == true) {
                param.put("sdkiLelah", "V");
            } else {
                param.put("sdkiLelah", "");
            }

            if (chkSdkiKadarTinggi.isSelected() == true) {
                param.put("sdkiKadarTinggi", "V");
            } else {
                param.put("sdkiKadarTinggi", "");
            }

            if (chkSdkiPalpitasi.isSelected() == true) {
                param.put("sdkiPalpitasi", "V");
            } else {
                param.put("sdkiPalpitasi", "");
            }

            if (chkSdkiMengeluh.isSelected() == true) {
                param.put("sdkiMengeluh", "V");
            } else {
                param.put("sdkiMengeluh", "");
            }

            if (chkSdkiGemetar.isSelected() == true) {
                param.put("sdkiGemetar", "V");
            } else {
                param.put("sdkiGemetar", "");
            }

            if (chkSdkiKesadaran.isSelected() == true) {
                param.put("sdkiKesadaran", "V");
            } else {
                param.put("sdkiKesadaran", "");
            }

            if (chkSdkiPerilaku.isSelected() == true) {
                param.put("sdkiPerilaku", "V");
            } else {
                param.put("sdkiPerilaku", "");
            }

            if (chkSdkiSulit.isSelected() == true) {
                param.put("sdkiSulit", "V");
            } else {
                param.put("sdkiSulit", "");
            }

            if (chkSdkiBerkeringat.isSelected() == true) {
                param.put("sdkiBerkeringat", "V");
            } else {
                param.put("sdkiBerkeringat", "");
            }

            if (chkSdkiMulut.isSelected() == true) {
                param.put("sdkiMulut", "V");
            } else {
                param.put("sdkiMulut", "");
            }

            if (chkSdkiJumlah.isSelected() == true) {
                param.put("sdkiJumlah", "V");
            } else {
                param.put("sdkiJumlah", "");
            }

            if (chkSdkiHaus.isSelected() == true) {
                param.put("sdkiHaus", "V");
            } else {
                param.put("sdkiHaus", "");
            }

            if (chkSdkiDiabetesMellitus.isSelected() == true) {
                param.put("sdkiDiabetesMellitus", "V");
            } else {
                param.put("sdkiDiabetesMellitus", "");
            }

            if (chkSdkiKetoasidosis.isSelected() == true) {
                param.put("sdkiKetoasidosis", "V");
            } else {
                param.put("sdkiKetoasidosis", "");
            }

            if (chkSdkiHipoglikemia.isSelected() == true) {
                param.put("sdkiHipoglikemia", "V");
            } else {
                param.put("sdkiHipoglikemia", "");
            }

            if (chkSdkiHiperglikemia.isSelected() == true) {
                param.put("sdkiHiperglikemia", "V");
            } else {
                param.put("sdkiHiperglikemia", "");
            }

            if (chkSdkiPenggunaanKorti.isSelected() == true) {
                param.put("sdkiPenggunaanKorti", "V");
            } else {
                param.put("sdkiPenggunaanKorti", "");
            }

            if (chkSdkiNutrisi.isSelected() == true) {
                param.put("sdkiNutrisi", "V");
            } else {
                param.put("sdkiNutrisi", "");
            }

            if (chkSdkiDiabetesGestasional.isSelected() == true) {
                param.put("sdkiDiabetesGestasional", "V");
            } else {
                param.put("sdkiDiabetesGestasional", "");
            }

            if (chkSlkiKestabilan.isSelected() == true) {
                param.put("slkiKestabilan", "V");
            } else {
                param.put("slkiKestabilan", "");
            }

            if (TketSelama.getText().equals("")) {
                param.put("ketSelama", ".............");
            } else {
                param.put("ketSelama", TketSelama.getText());
            }

            if (chkSlkiKesadaran.isSelected() == true) {
                param.put("slkiKesadaran", "V");
            } else {
                param.put("slkiKesadaran", "");
            }

            if (chkSlkiMengantuk.isSelected() == true) {
                param.put("slkiMengantuk", "V");
            } else {
                param.put("slkiMengantuk", "");
            }

            if (chkSlkiPusing.isSelected() == true) {
                param.put("slkiPusing", "V");
            } else {
                param.put("slkiPusing", "");
            }

            if (chkSlkiLelah.isSelected() == true) {
                param.put("slkiLelah", "V");
            } else {
                param.put("slkiLelah", "");
            }

            if (chkSlkiKeluhan.isSelected() == true) {
                param.put("slkiKeluhan", "V");
            } else {
                param.put("slkiKeluhan", "");
            }

            if (chkSlkiGemetar.isSelected() == true) {
                param.put("slkiGemetar", "V");
            } else {
                param.put("slkiGemetar", "");
            }

            if (chkSlkiBerkeringat.isSelected() == true) {
                param.put("slkiBerkeringat", "V");
            } else {
                param.put("slkiBerkeringat", "");
            }

            if (chkSlkiRasa.isSelected() == true) {
                param.put("slkiRasa", "V");
            } else {
                param.put("slkiRasa", "");
            }

            if (chkSlkiKadar.isSelected() == true) {
                param.put("slkiKadar", "V");
            } else {
                param.put("slkiKadar", "");
            }

            if (chkSlkiJumlah.isSelected() == true) {
                param.put("slkiJumlah", "V");
            } else {
                param.put("slkiJumlah", "");
            }

            if (chkSikiIdenKemungkinan.isSelected() == true) {
                param.put("sikiIdenKemungkinan", "V");
            } else {
                param.put("sikiIdenKemungkinan", "");
            }

            if (chkSikiIdenSituasi.isSelected() == true) {
                param.put("sikiIdenSituasi", "V");
            } else {
                param.put("sikiIdenSituasi", "");
            }

            if (chkSikiMonitorKadar.isSelected() == true) {
                param.put("sikiMonitorKadar", "V");
            } else {
                param.put("sikiMonitorKadar", "");
            }

            if (chkSikiMonitorTanda.isSelected() == true) {
                param.put("sikiMonitorTanda", "V");
            } else {
                param.put("sikiMonitorTanda", "");
            }

            if (chkSikiMonitorIntake.isSelected() == true) {
                param.put("sikiMonitorIntake", "V");
            } else {
                param.put("sikiMonitorIntake", "");
            }

            if (chkSikiMonitorKeton.isSelected() == true) {
                param.put("sikiMonitorKeton", "V");
            } else {
                param.put("sikiMonitorKeton", "");
            }

            if (chkSikiBerikan.isSelected() == true) {
                param.put("sikiBerikan", "V");
            } else {
                param.put("sikiBerikan", "");
            }

            if (chkSikiKonsultasi.isSelected() == true) {
                param.put("sikiKonsultasi", "V");
            } else {
                param.put("sikiKonsultasi", "");
            }

            if (chkSikiFasilitasi.isSelected() == true) {
                param.put("sikiFasilitasi", "V");
            } else {
                param.put("sikiFasilitasi", "");
            }

            if (chkSikiAnjurMenghindari.isSelected() == true) {
                param.put("sikiAnjurMenghindari", "V");
            } else {
                param.put("sikiAnjurMenghindari", "");
            }

            if (chkSikiAnjurMonitor.isSelected() == true) {
                param.put("sikiAnjurMonitor", "V");
            } else {
                param.put("sikiAnjurMonitor", "");
            }

            if (chkSikiAnjurKepatuhan.isSelected() == true) {
                param.put("sikiAnjurKepatuhan", "V");
            } else {
                param.put("sikiAnjurKepatuhan", "");
            }

            if (chkSikiAjarkanIndikasi.isSelected() == true) {
                param.put("sikiAjarkanIndikasi", "V");
            } else {
                param.put("sikiAjarkanIndikasi", "");
            }

            if (chkSikiAjarkanPengelolaan.isSelected() == true) {
                param.put("sikiAjarkanPengelolaan", "V");
            } else {
                param.put("sikiAjarkanPengelolaan", "");
            }

            if (chkSikiKolabInsulin.isSelected() == true) {
                param.put("sikiKolabInsulin", "V");
            } else {
                param.put("sikiKolabInsulin", "");
            }

            if (chkSikiKolabIv.isSelected() == true) {
                param.put("sikiKolabIv", "V");
            } else {
                param.put("sikiKolabIv", "");
            }

            if (chkSikiKolabKalium.isSelected() == true) {
                param.put("sikiKolabKalium", "V");
            } else {
                param.put("sikiKolabKalium", "");
            }

            Valid.MyReport("rptMasKepKetidakstabilanGlukosa.jasper", "report", "::[ RM Masalah Keperawatan Ketidakstabilan Glukosa Darah ]::",
                    "SELECT now() tanggal", param);
            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel..!!!!");
            tbMasalah.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void chkSdkiLelahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSdkiLelahActionPerformed
        TketSelama.setText("");
        if (chkSdkiLelah.isSelected() == true) {
            TketSelama.setEnabled(true);
            TketSelama.requestFocus();
        } else {
            TketSelama.setEnabled(false);
        }
    }//GEN-LAST:event_chkSdkiLelahActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMMasalahKeperawatanKetidakstabilanGlukosaDarah dialog = new RMMasalahKeperawatanKetidakstabilanGlukosaDarah(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TketSelama;
    private widget.TextBox TnipPetugas;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TrgRawat;
    private widget.CekBox chkSdkiBerkeringat;
    private widget.CekBox chkSdkiDiabetesGestasional;
    private widget.CekBox chkSdkiDiabetesMellitus;
    private widget.CekBox chkSdkiDisfungsiGinjal;
    private widget.CekBox chkSdkiDisfungsiHati;
    private widget.CekBox chkSdkiDisfungsiPankreas;
    private widget.CekBox chkSdkiEfek;
    private widget.CekBox chkSdkiEndokrin;
    private widget.CekBox chkSdkiGangguanGlukosa;
    private widget.CekBox chkSdkiGangguanKordinasi;
    private widget.CekBox chkSdkiGangguanMetabolik;
    private widget.CekBox chkSdkiGangguanToleransi;
    private widget.CekBox chkSdkiGemetar;
    private widget.CekBox chkSdkiHaus;
    private widget.CekBox chkSdkiHiperglikemia;
    private widget.CekBox chkSdkiHiperinsulin;
    private widget.CekBox chkSdkiHipoglikemia;
    private widget.CekBox chkSdkiJumlah;
    private widget.CekBox chkSdkiKadarRendah;
    private widget.CekBox chkSdkiKadarTinggi;
    private widget.CekBox chkSdkiKesadaran;
    private widget.CekBox chkSdkiKetoasidosis;
    private widget.CekBox chkSdkiLelah;
    private widget.CekBox chkSdkiMengantuk;
    private widget.CekBox chkSdkiMengeluh;
    private widget.CekBox chkSdkiMulut;
    private widget.CekBox chkSdkiNutrisi;
    private widget.CekBox chkSdkiPalpitasi;
    private widget.CekBox chkSdkiPenggunaanInsulin;
    private widget.CekBox chkSdkiPenggunaanKorti;
    private widget.CekBox chkSdkiPerilaku;
    private widget.CekBox chkSdkiPusing;
    private widget.CekBox chkSdkiResistensi;
    private widget.CekBox chkSdkiSulit;
    private widget.CekBox chkSdkiTindakan;
    private widget.CekBox chkSikiAjarkanIndikasi;
    private widget.CekBox chkSikiAjarkanPengelolaan;
    private widget.CekBox chkSikiAnjurKepatuhan;
    private widget.CekBox chkSikiAnjurMenghindari;
    private widget.CekBox chkSikiAnjurMonitor;
    private widget.CekBox chkSikiBerikan;
    private widget.CekBox chkSikiFasilitasi;
    private widget.CekBox chkSikiIdenKemungkinan;
    private widget.CekBox chkSikiIdenSituasi;
    private widget.CekBox chkSikiKolabInsulin;
    private widget.CekBox chkSikiKolabIv;
    private widget.CekBox chkSikiKolabKalium;
    private widget.CekBox chkSikiKonsultasi;
    private widget.CekBox chkSikiMonitorIntake;
    private widget.CekBox chkSikiMonitorKadar;
    private widget.CekBox chkSikiMonitorKeton;
    private widget.CekBox chkSikiMonitorTanda;
    private widget.CekBox chkSlkiBerkeringat;
    private widget.CekBox chkSlkiGemetar;
    private widget.CekBox chkSlkiJumlah;
    private widget.CekBox chkSlkiKadar;
    private widget.CekBox chkSlkiKeluhan;
    private widget.CekBox chkSlkiKesadaran;
    private widget.CekBox chkSlkiKestabilan;
    private widget.CekBox chkSlkiLelah;
    private widget.CekBox chkSlkiMengantuk;
    private widget.CekBox chkSlkiPusing;
    private widget.CekBox chkSlkiRasa;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel7;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
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
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label label20;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass8;
    private widget.Table tbMasalah;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select m.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d/%m/%Y') tglLahir, DATE_FORMAT(m.waktu_simpan,'%d/%m/%Y') tglSimpan, "
                    + "pg.nama nmPetugas from masalah_keperawatan_ketidakstabilan_glukosa_darah m inner join reg_periksa rp on rp.no_rawat=m.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=m.nip_petugas WHERE "
                    + "date(m.waktu_simpan) between ? and ? and m.no_rawat LIKE ? or "
                    + "date(m.waktu_simpan) between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "date(m.waktu_simpan) between ? and ? and p.nm_pasien LIKE ? or "
                    + "date(m.waktu_simpan) between ? and ? and pg.nama LIKE ? or "
                    + "date(m.waktu_simpan) between ? and ? and m.ruang_rawat LIKE ? ORDER BY m.waktu_simpan desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("waktu_simpan"),
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglSimpan"),
                        rs.getString("nmPetugas"),
                        rs.getString("sdki_disfungsi_pankreas"),
                        rs.getString("sdki_resistensi"),
                        rs.getString("sdki_gangguan_toleransi"),
                        rs.getString("sdki_gangguan_glukosa"),
                        rs.getString("sdki_penggunaan_insulin"),
                        rs.getString("sdki_hiperinsulin"),
                        rs.getString("sdki_endokrin"),
                        rs.getString("sdki_disfungsi_hati"),
                        rs.getString("sdki_disfungsi_ginjal"),
                        rs.getString("sdki_efek"),
                        rs.getString("sdki_tindakan"),
                        rs.getString("sdki_gangguan_metabolik"),
                        rs.getString("sdki_mengantuk"),
                        rs.getString("sdki_pusing"),
                        rs.getString("sdki_gangguan_kordinasi"),
                        rs.getString("sdki_kadar_rendah"),
                        rs.getString("sdki_lelah"),
                        rs.getString("sdki_kadar_tinggi"),
                        rs.getString("sdki_palpitasi"),
                        rs.getString("sdki_mengeluh"),
                        rs.getString("sdki_gemetar"),
                        rs.getString("sdki_kesadaran"),
                        rs.getString("sdki_perilaku"),
                        rs.getString("sdki_sulit"),
                        rs.getString("sdki_berkeringat"),
                        rs.getString("sdki_mulut"),
                        rs.getString("sdki_jumlah"),
                        rs.getString("sdki_haus"),
                        rs.getString("sdki_diabetes_mellitus"),
                        rs.getString("sdki_ketoasidosis"),
                        rs.getString("sdki_hipoglikemia"),
                        rs.getString("sdki_hiperglikemia"),
                        rs.getString("sdki_penggunaan_korti"),
                        rs.getString("sdki_nutrisi"),
                        rs.getString("sdki_diabetes_gestasional"),
                        rs.getString("slki_kestabilan"),
                        rs.getString("ket_selama"),
                        rs.getString("slki_kesadaran"),
                        rs.getString("slki_mengantuk"),
                        rs.getString("slki_pusing"),
                        rs.getString("slki_lelah"),
                        rs.getString("slki_keluhan"),
                        rs.getString("slki_gemetar"),
                        rs.getString("slki_berkeringat"),
                        rs.getString("slki_rasa"),
                        rs.getString("slki_kadar"),
                        rs.getString("slki_jumlah"),
                        rs.getString("siki_iden_kemungkinan"),
                        rs.getString("siki_iden_situasi"),
                        rs.getString("siki_monitor_kadar"),
                        rs.getString("siki_monitor_tanda"),
                        rs.getString("siki_monitor_intake"),
                        rs.getString("siki_monitor_keton"),
                        rs.getString("siki_berikan"),
                        rs.getString("siki_konsultasi"),
                        rs.getString("siki_fasilitasi"),
                        rs.getString("siki_anjur_menghindari"),
                        rs.getString("siki_anjur_monitor"),
                        rs.getString("siki_anjur_kepatuhan"),
                        rs.getString("siki_ajarkan_indikasi"),
                        rs.getString("siki_ajarkan_pengelolaan"),
                        rs.getString("siki_kolab_insulin"),
                        rs.getString("siki_kolab_iv"),
                        rs.getString("siki_kolab_kalium"),
                        rs.getString("status_rawat"),
                        rs.getString("nip_petugas")
                    });
                }
            } catch (Exception e) {
                System.out.println("rekammedis.RMMasalahKeperawatanKetidakstabilanGlukosaDarah.tampil() : " + e);
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
        TrgRawat.setText("");
        chkSdkiDisfungsiPankreas.setSelected(false);
        chkSdkiResistensi.setSelected(false);
        chkSdkiGangguanToleransi.setSelected(false);
        chkSdkiGangguanGlukosa.setSelected(false);
        chkSdkiPenggunaanInsulin.setSelected(false);
        chkSdkiHiperinsulin.setSelected(false);
        chkSdkiEndokrin.setSelected(false);
        chkSdkiDisfungsiHati.setSelected(false);
        chkSdkiDisfungsiGinjal.setSelected(false);
        chkSdkiEfek.setSelected(false);
        chkSdkiTindakan.setSelected(false);
        chkSdkiGangguanMetabolik.setSelected(false);
        chkSdkiMengantuk.setSelected(false);
        chkSdkiPusing.setSelected(false);
        chkSdkiGangguanKordinasi.setSelected(false);
        chkSdkiKadarRendah.setSelected(false);
        chkSdkiLelah.setSelected(false);
        chkSdkiKadarTinggi.setSelected(false);
        chkSdkiPalpitasi.setSelected(false);
        chkSdkiMengeluh.setSelected(false);
        chkSdkiGemetar.setSelected(false);
        chkSdkiKesadaran.setSelected(false);
        chkSdkiPerilaku.setSelected(false);
        chkSdkiSulit.setSelected(false);
        chkSdkiBerkeringat.setSelected(false);
        chkSdkiMulut.setSelected(false);
        chkSdkiJumlah.setSelected(false);
        chkSdkiHaus.setSelected(false);
        chkSdkiDiabetesMellitus.setSelected(false);
        chkSdkiKetoasidosis.setSelected(false);
        chkSdkiHipoglikemia.setSelected(false);
        chkSdkiHiperglikemia.setSelected(false);
        chkSdkiPenggunaanKorti.setSelected(false);
        chkSdkiNutrisi.setSelected(false);
        chkSdkiDiabetesGestasional.setSelected(false);

        chkSlkiKestabilan.setSelected(false);
        TketSelama.setText("");
        chkSlkiKesadaran.setSelected(false);
        chkSlkiMengantuk.setSelected(false);
        chkSlkiPusing.setSelected(false);
        chkSlkiLelah.setSelected(false);
        chkSlkiKeluhan.setSelected(false);
        chkSlkiGemetar.setSelected(false);
        chkSlkiBerkeringat.setSelected(false);
        chkSlkiRasa.setSelected(false);
        chkSlkiKadar.setSelected(false);
        chkSlkiJumlah.setSelected(false);

        chkSikiIdenKemungkinan.setSelected(false);
        chkSikiIdenSituasi.setSelected(false);
        chkSikiMonitorKadar.setSelected(false);
        chkSikiMonitorTanda.setSelected(false);
        chkSikiMonitorIntake.setSelected(false);
        chkSikiMonitorKeton.setSelected(false);
        chkSikiBerikan.setSelected(false);
        chkSikiKonsultasi.setSelected(false);
        chkSikiFasilitasi.setSelected(false);
        chkSikiAnjurMenghindari.setSelected(false);
        chkSikiAnjurMonitor.setSelected(false);
        chkSikiAnjurKepatuhan.setSelected(false);
        chkSikiAjarkanIndikasi.setSelected(false);
        chkSikiAjarkanPengelolaan.setSelected(false);
        chkSikiKolabInsulin.setSelected(false);
        chkSikiKolabIv.setSelected(false);
        chkSikiKolabKalium.setSelected(false);

        TnipPetugas.setText("-");
        TnmPetugas.setText("-");
    }

    private void getData() {
        emptVariabel();
        if (tbMasalah.getSelectedRow() != -1) {
            TNoRw.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 1).toString());
            TNoRM.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 2).toString());
            TPasien.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 3).toString());
            TrgRawat.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 5).toString());

            sdkiDisfungsiPankreas = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 8).toString();
            sdkiResistensi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 9).toString();
            sdkiGangguanToleransi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 10).toString();
            sdkiGangguanGlukosa = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 11).toString();
            sdkiPenggunaanInsulin = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 12).toString();
            sdkiHiperinsulin = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 13).toString();
            sdkiEndokrin = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 14).toString();
            sdkiDisfungsiHati = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 15).toString();
            sdkiDisfungsiGinjal = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 16).toString();
            sdkiEfek = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 17).toString();
            sdkiTindakan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 18).toString();
            sdkiGangguanMetabolik = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 19).toString();
            sdkiMengantuk = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 20).toString();
            sdkiPusing = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 21).toString();
            sdkiGangguanKordinasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 22).toString();
            sdkiKadarRendah = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 23).toString();
            sdkiLelah = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 24).toString();
            sdkiKadarTinggi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 25).toString();
            sdkiPalpitasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 26).toString();
            sdkiMengeluh = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 27).toString();
            sdkiGemetar = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 28).toString();
            sdkiKesadaran = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 29).toString();
            sdkiPerilaku = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 30).toString();
            sdkiSulit = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 31).toString();
            sdkiBerkeringat = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 32).toString();
            sdkiMulut = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 33).toString();
            sdkiJumlah = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 34).toString();
            sdkiHaus = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 35).toString();
            sdkiDiabetesMellitus = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 36).toString();
            sdkiKetoasidosis = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 37).toString();
            sdkiHipoglikemia = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 38).toString();
            sdkiHiperglikemia = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 39).toString();
            sdkiPenggunaanKorti = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 40).toString();
            sdkiNutrisi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 41).toString();
            sdkiDiabetesGestasional = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 42).toString();

            slkiKestabilan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 43).toString();
            TketSelama.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 44).toString());
            slkiKesadaran = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 45).toString();
            slkiMengantuk = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 46).toString();
            slkiPusing = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 47).toString();
            slkiLelah = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 48).toString();
            slkiKeluhan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 49).toString();
            slkiGemetar = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 50).toString();
            slkiBerkeringat = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 51).toString();
            slkiRasa = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 52).toString();
            slkiKadar = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 53).toString();
            slkiJumlah = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 54).toString();

            sikiIdenKemungkinan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 55).toString();
            sikiIdenSituasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 56).toString();
            sikiMonitorKadar = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 57).toString();
            sikiMonitorTanda = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 58).toString();
            sikiMonitorIntake = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 59).toString();
            sikiMonitorKeton = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 60).toString();
            sikiBerikan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 61).toString();
            sikiKonsultasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 62).toString();
            sikiFasilitasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 63).toString();
            sikiAnjurMenghindari = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 64).toString();
            sikiAnjurMonitor = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 65).toString();
            sikiAnjurKepatuhan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 66).toString();
            sikiAjarkanIndikasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 67).toString();
            sikiAjarkanPengelolaan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 68).toString();
            sikiKolabInsulin = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 69).toString();
            sikiKolabIv = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 70).toString();
            sikiKolabKalium = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 71).toString();           

            TnipPetugas.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 73).toString());
            TnmPetugas.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 7).toString());
            dataCek();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnGanti.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
    }

    private void dataCek() {
        if (sdkiDisfungsiPankreas.equals("ya")) {
            chkSdkiDisfungsiPankreas.setSelected(true);
        } else {
            chkSdkiDisfungsiPankreas.setSelected(false);
        }

        if (sdkiResistensi.equals("ya")) {
            chkSdkiResistensi.setSelected(true);
        } else {
            chkSdkiResistensi.setSelected(false);
        }

        if (sdkiGangguanToleransi.equals("ya")) {
            chkSdkiGangguanToleransi.setSelected(true);
        } else {
            chkSdkiGangguanToleransi.setSelected(false);
        }

        if (sdkiGangguanGlukosa.equals("ya")) {
            chkSdkiGangguanGlukosa.setSelected(true);
        } else {
            chkSdkiGangguanGlukosa.setSelected(false);
        }

        if (sdkiPenggunaanInsulin.equals("ya")) {
            chkSdkiPenggunaanInsulin.setSelected(true);
        } else {
            chkSdkiPenggunaanInsulin.setSelected(false);
        }

        if (sdkiHiperinsulin.equals("ya")) {
            chkSdkiHiperinsulin.setSelected(true);
        } else {
            chkSdkiHiperinsulin.setSelected(false);
        }

        if (sdkiEndokrin.equals("ya")) {
            chkSdkiEndokrin.setSelected(true);
        } else {
            chkSdkiEndokrin.setSelected(false);
        }

        if (sdkiDisfungsiHati.equals("ya")) {
            chkSdkiDisfungsiHati.setSelected(true);
        } else {
            chkSdkiDisfungsiHati.setSelected(false);
        }

        if (sdkiDisfungsiGinjal.equals("ya")) {
            chkSdkiDisfungsiGinjal.setSelected(true);
        } else {
            chkSdkiDisfungsiGinjal.setSelected(false);
        }

        if (sdkiEfek.equals("ya")) {
            chkSdkiEfek.setSelected(true);
        } else {
            chkSdkiEfek.setSelected(false);
        }

        if (sdkiTindakan.equals("ya")) {
            chkSdkiTindakan.setSelected(true);
        } else {
            chkSdkiTindakan.setSelected(false);
        }

        if (sdkiGangguanMetabolik.equals("ya")) {
            chkSdkiGangguanMetabolik.setSelected(true);
        } else {
            chkSdkiGangguanMetabolik.setSelected(false);
        }

        if (sdkiMengantuk.equals("ya")) {
            chkSdkiMengantuk.setSelected(true);
        } else {
            chkSdkiMengantuk.setSelected(false);
        }

        if (sdkiPusing.equals("ya")) {
            chkSdkiPusing.setSelected(true);
        } else {
            chkSdkiPusing.setSelected(false);
        }

        if (sdkiGangguanKordinasi.equals("ya")) {
            chkSdkiGangguanKordinasi.setSelected(true);
        } else {
            chkSdkiGangguanKordinasi.setSelected(false);
        }

        if (sdkiKadarRendah.equals("ya")) {
            chkSdkiKadarRendah.setSelected(true);
        } else {
            chkSdkiKadarRendah.setSelected(false);
        }

        if (sdkiLelah.equals("ya")) {
            chkSdkiLelah.setSelected(true);
        } else {
            chkSdkiLelah.setSelected(false);
        }

        if (sdkiKadarTinggi.equals("ya")) {
            chkSdkiKadarTinggi.setSelected(true);
        } else {
            chkSdkiKadarTinggi.setSelected(false);
        }

        if (sdkiPalpitasi.equals("ya")) {
            chkSdkiPalpitasi.setSelected(true);
        } else {
            chkSdkiPalpitasi.setSelected(false);
        }

        if (sdkiMengeluh.equals("ya")) {
            chkSdkiMengeluh.setSelected(true);
        } else {
            chkSdkiMengeluh.setSelected(false);
        }

        if (sdkiGemetar.equals("ya")) {
            chkSdkiGemetar.setSelected(true);
        } else {
            chkSdkiGemetar.setSelected(false);
        }

        if (sdkiKesadaran.equals("ya")) {
            chkSdkiKesadaran.setSelected(true);
        } else {
            chkSdkiKesadaran.setSelected(false);
        }

        if (sdkiPerilaku.equals("ya")) {
            chkSdkiPerilaku.setSelected(true);
        } else {
            chkSdkiPerilaku.setSelected(false);
        }

        if (sdkiSulit.equals("ya")) {
            chkSdkiSulit.setSelected(true);
        } else {
            chkSdkiSulit.setSelected(false);
        }

        if (sdkiBerkeringat.equals("ya")) {
            chkSdkiBerkeringat.setSelected(true);
        } else {
            chkSdkiBerkeringat.setSelected(false);
        }

        if (sdkiMulut.equals("ya")) {
            chkSdkiMulut.setSelected(true);
        } else {
            chkSdkiMulut.setSelected(false);
        }

        if (sdkiJumlah.equals("ya")) {
            chkSdkiJumlah.setSelected(true);
        } else {
            chkSdkiJumlah.setSelected(false);
        }

        if (sdkiHaus.equals("ya")) {
            chkSdkiHaus.setSelected(true);
        } else {
            chkSdkiHaus.setSelected(false);
        }

        if (sdkiDiabetesMellitus.equals("ya")) {
            chkSdkiDiabetesMellitus.setSelected(true);
        } else {
            chkSdkiDiabetesMellitus.setSelected(false);
        }

        if (sdkiKetoasidosis.equals("ya")) {
            chkSdkiKetoasidosis.setSelected(true);
        } else {
            chkSdkiKetoasidosis.setSelected(false);
        }

        if (sdkiHipoglikemia.equals("ya")) {
            chkSdkiHipoglikemia.setSelected(true);
        } else {
            chkSdkiHipoglikemia.setSelected(false);
        }

        if (sdkiHiperglikemia.equals("ya")) {
            chkSdkiHiperglikemia.setSelected(true);
        } else {
            chkSdkiHiperglikemia.setSelected(false);
        }

        if (sdkiPenggunaanKorti.equals("ya")) {
            chkSdkiPenggunaanKorti.setSelected(true);
        } else {
            chkSdkiPenggunaanKorti.setSelected(false);
        }

        if (sdkiNutrisi.equals("ya")) {
            chkSdkiNutrisi.setSelected(true);
        } else {
            chkSdkiNutrisi.setSelected(false);
        }

        if (sdkiDiabetesGestasional.equals("ya")) {
            chkSdkiDiabetesGestasional.setSelected(true);
        } else {
            chkSdkiDiabetesGestasional.setSelected(false);
        }

        if (slkiKestabilan.equals("ya")) {
            chkSlkiKestabilan.setSelected(true);
        } else {
            chkSlkiKestabilan.setSelected(false);
        }

        if (slkiKesadaran.equals("ya")) {
            chkSlkiKesadaran.setSelected(true);
        } else {
            chkSlkiKesadaran.setSelected(false);
        }

        if (slkiMengantuk.equals("ya")) {
            chkSlkiMengantuk.setSelected(true);
        } else {
            chkSlkiMengantuk.setSelected(false);
        }

        if (slkiPusing.equals("ya")) {
            chkSlkiPusing.setSelected(true);
        } else {
            chkSlkiPusing.setSelected(false);
        }

        if (slkiLelah.equals("ya")) {
            chkSlkiLelah.setSelected(true);
        } else {
            chkSlkiLelah.setSelected(false);
        }

        if (slkiKeluhan.equals("ya")) {
            chkSlkiKeluhan.setSelected(true);
        } else {
            chkSlkiKeluhan.setSelected(false);
        }

        if (slkiGemetar.equals("ya")) {
            chkSlkiGemetar.setSelected(true);
        } else {
            chkSlkiGemetar.setSelected(false);
        }

        if (slkiBerkeringat.equals("ya")) {
            chkSlkiBerkeringat.setSelected(true);
        } else {
            chkSlkiBerkeringat.setSelected(false);
        }

        if (slkiRasa.equals("ya")) {
            chkSlkiRasa.setSelected(true);
        } else {
            chkSlkiRasa.setSelected(false);
        }

        if (slkiKadar.equals("ya")) {
            chkSlkiKadar.setSelected(true);
        } else {
            chkSlkiKadar.setSelected(false);
        }

        if (slkiJumlah.equals("ya")) {
            chkSlkiJumlah.setSelected(true);
        } else {
            chkSlkiJumlah.setSelected(false);
        }

        if (sikiIdenKemungkinan.equals("ya")) {
            chkSikiIdenKemungkinan.setSelected(true);
        } else {
            chkSikiIdenKemungkinan.setSelected(false);
        }

        if (sikiIdenSituasi.equals("ya")) {
            chkSikiIdenSituasi.setSelected(true);
        } else {
            chkSikiIdenSituasi.setSelected(false);
        }

        if (sikiMonitorKadar.equals("ya")) {
            chkSikiMonitorKadar.setSelected(true);
        } else {
            chkSikiMonitorKadar.setSelected(false);
        }

        if (sikiMonitorTanda.equals("ya")) {
            chkSikiMonitorTanda.setSelected(true);
        } else {
            chkSikiMonitorTanda.setSelected(false);
        }

        if (sikiMonitorIntake.equals("ya")) {
            chkSikiMonitorIntake.setSelected(true);
        } else {
            chkSikiMonitorIntake.setSelected(false);
        }

        if (sikiMonitorKeton.equals("ya")) {
            chkSikiMonitorKeton.setSelected(true);
        } else {
            chkSikiMonitorKeton.setSelected(false);
        }

        if (sikiBerikan.equals("ya")) {
            chkSikiBerikan.setSelected(true);
        } else {
            chkSikiBerikan.setSelected(false);
        }

        if (sikiKonsultasi.equals("ya")) {
            chkSikiKonsultasi.setSelected(true);
        } else {
            chkSikiKonsultasi.setSelected(false);
        }

        if (sikiFasilitasi.equals("ya")) {
            chkSikiFasilitasi.setSelected(true);
        } else {
            chkSikiFasilitasi.setSelected(false);
        }

        if (sikiAnjurMenghindari.equals("ya")) {
            chkSikiAnjurMenghindari.setSelected(true);
        } else {
            chkSikiAnjurMenghindari.setSelected(false);
        }

        if (sikiAnjurMonitor.equals("ya")) {
            chkSikiAnjurMonitor.setSelected(true);
        } else {
            chkSikiAnjurMonitor.setSelected(false);
        }

        if (sikiAnjurKepatuhan.equals("ya")) {
            chkSikiAnjurKepatuhan.setSelected(true);
        } else {
            chkSikiAnjurKepatuhan.setSelected(false);
        }

        if (sikiAjarkanIndikasi.equals("ya")) {
            chkSikiAjarkanIndikasi.setSelected(true);
        } else {
            chkSikiAjarkanIndikasi.setSelected(false);
        }

        if (sikiAjarkanPengelolaan.equals("ya")) {
            chkSikiAjarkanPengelolaan.setSelected(true);
        } else {
            chkSikiAjarkanPengelolaan.setSelected(false);
        }

        if (sikiKolabInsulin.equals("ya")) {
            chkSikiKolabInsulin.setSelected(true);
        } else {
            chkSikiKolabInsulin.setSelected(false);
        }

        if (sikiKolabIv.equals("ya")) {
            chkSikiKolabIv.setSelected(true);
        } else {
            chkSikiKolabIv.setSelected(false);
        }

        if (sikiKolabKalium.equals("ya")) {
            chkSikiKolabKalium.setSelected(true);
        } else {
            chkSikiKolabKalium.setSelected(false);
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan, String stts_rwt) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TrgRawat.setText(ruangan);
        sttsRawat = stts_rwt;
        
        if (akses.getadmin() == true) {
            TnipPetugas.setText("-");
            TnmPetugas.setText("-");
        } else {
            TnipPetugas.setText(akses.getkode());
            TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipPetugas.getText() + "'"));
        }
    }
    
    private void cekData() {
        if (chkSdkiDisfungsiPankreas.isSelected() == true) {
            sdkiDisfungsiPankreas = "ya";
        } else {
            sdkiDisfungsiPankreas = "tidak";
        }

        if (chkSdkiResistensi.isSelected() == true) {
            sdkiResistensi = "ya";
        } else {
            sdkiResistensi = "tidak";
        }

        if (chkSdkiGangguanToleransi.isSelected() == true) {
            sdkiGangguanToleransi = "ya";
        } else {
            sdkiGangguanToleransi = "tidak";
        }

        if (chkSdkiGangguanGlukosa.isSelected() == true) {
            sdkiGangguanGlukosa = "ya";
        } else {
            sdkiGangguanGlukosa = "tidak";
        }

        if (chkSdkiPenggunaanInsulin.isSelected() == true) {
            sdkiPenggunaanInsulin = "ya";
        } else {
            sdkiPenggunaanInsulin = "tidak";
        }

        if (chkSdkiHiperinsulin.isSelected() == true) {
            sdkiHiperinsulin = "ya";
        } else {
            sdkiHiperinsulin = "tidak";
        }

        if (chkSdkiEndokrin.isSelected() == true) {
            sdkiEndokrin = "ya";
        } else {
            sdkiEndokrin = "tidak";
        }

        if (chkSdkiDisfungsiHati.isSelected() == true) {
            sdkiDisfungsiHati = "ya";
        } else {
            sdkiDisfungsiHati = "tidak";
        }

        if (chkSdkiDisfungsiGinjal.isSelected() == true) {
            sdkiDisfungsiGinjal = "ya";
        } else {
            sdkiDisfungsiGinjal = "tidak";
        }

        if (chkSdkiEfek.isSelected() == true) {
            sdkiEfek = "ya";
        } else {
            sdkiEfek = "tidak";
        }

        if (chkSdkiTindakan.isSelected() == true) {
            sdkiTindakan = "ya";
        } else {
            sdkiTindakan = "tidak";
        }

        if (chkSdkiGangguanMetabolik.isSelected() == true) {
            sdkiGangguanMetabolik = "ya";
        } else {
            sdkiGangguanMetabolik = "tidak";
        }

        if (chkSdkiMengantuk.isSelected() == true) {
            sdkiMengantuk = "ya";
        } else {
            sdkiMengantuk = "tidak";
        }

        if (chkSdkiPusing.isSelected() == true) {
            sdkiPusing = "ya";
        } else {
            sdkiPusing = "tidak";
        }

        if (chkSdkiGangguanKordinasi.isSelected() == true) {
            sdkiGangguanKordinasi = "ya";
        } else {
            sdkiGangguanKordinasi = "tidak";
        }

        if (chkSdkiKadarRendah.isSelected() == true) {
            sdkiKadarRendah = "ya";
        } else {
            sdkiKadarRendah = "tidak";
        }

        if (chkSdkiLelah.isSelected() == true) {
            sdkiLelah = "ya";
        } else {
            sdkiLelah = "tidak";
        }

        if (chkSdkiKadarTinggi.isSelected() == true) {
            sdkiKadarTinggi = "ya";
        } else {
            sdkiKadarTinggi = "tidak";
        }

        if (chkSdkiPalpitasi.isSelected() == true) {
            sdkiPalpitasi = "ya";
        } else {
            sdkiPalpitasi = "tidak";
        }

        if (chkSdkiMengeluh.isSelected() == true) {
            sdkiMengeluh = "ya";
        } else {
            sdkiMengeluh = "tidak";
        }

        if (chkSdkiGemetar.isSelected() == true) {
            sdkiGemetar = "ya";
        } else {
            sdkiGemetar = "tidak";
        }

        if (chkSdkiKesadaran.isSelected() == true) {
            sdkiKesadaran = "ya";
        } else {
            sdkiKesadaran = "tidak";
        }

        if (chkSdkiPerilaku.isSelected() == true) {
            sdkiPerilaku = "ya";
        } else {
            sdkiPerilaku = "tidak";
        }

        if (chkSdkiSulit.isSelected() == true) {
            sdkiSulit = "ya";
        } else {
            sdkiSulit = "tidak";
        }

        if (chkSdkiBerkeringat.isSelected() == true) {
            sdkiBerkeringat = "ya";
        } else {
            sdkiBerkeringat = "tidak";
        }

        if (chkSdkiMulut.isSelected() == true) {
            sdkiMulut = "ya";
        } else {
            sdkiMulut = "tidak";
        }

        if (chkSdkiJumlah.isSelected() == true) {
            sdkiJumlah = "ya";
        } else {
            sdkiJumlah = "tidak";
        }

        if (chkSdkiHaus.isSelected() == true) {
            sdkiHaus = "ya";
        } else {
            sdkiHaus = "tidak";
        }

        if (chkSdkiDiabetesMellitus.isSelected() == true) {
            sdkiDiabetesMellitus = "ya";
        } else {
            sdkiDiabetesMellitus = "tidak";
        }

        if (chkSdkiKetoasidosis.isSelected() == true) {
            sdkiKetoasidosis = "ya";
        } else {
            sdkiKetoasidosis = "tidak";
        }

        if (chkSdkiHipoglikemia.isSelected() == true) {
            sdkiHipoglikemia = "ya";
        } else {
            sdkiHipoglikemia = "tidak";
        }

        if (chkSdkiHiperglikemia.isSelected() == true) {
            sdkiHiperglikemia = "ya";
        } else {
            sdkiHiperglikemia = "tidak";
        }

        if (chkSdkiPenggunaanKorti.isSelected() == true) {
            sdkiPenggunaanKorti = "ya";
        } else {
            sdkiPenggunaanKorti = "tidak";
        }

        if (chkSdkiNutrisi.isSelected() == true) {
            sdkiNutrisi = "ya";
        } else {
            sdkiNutrisi = "tidak";
        }

        if (chkSdkiDiabetesGestasional.isSelected() == true) {
            sdkiDiabetesGestasional = "ya";
        } else {
            sdkiDiabetesGestasional = "tidak";
        }

        if (chkSlkiKestabilan.isSelected() == true) {
            slkiKestabilan = "ya";
        } else {
            slkiKestabilan = "tidak";
        }

        if (chkSlkiKesadaran.isSelected() == true) {
            slkiKesadaran = "ya";
        } else {
            slkiKesadaran = "tidak";
        }

        if (chkSlkiMengantuk.isSelected() == true) {
            slkiMengantuk = "ya";
        } else {
            slkiMengantuk = "tidak";
        }

        if (chkSlkiPusing.isSelected() == true) {
            slkiPusing = "ya";
        } else {
            slkiPusing = "tidak";
        }

        if (chkSlkiLelah.isSelected() == true) {
            slkiLelah = "ya";
        } else {
            slkiLelah = "tidak";
        }

        if (chkSlkiKeluhan.isSelected() == true) {
            slkiKeluhan = "ya";
        } else {
            slkiKeluhan = "tidak";
        }

        if (chkSlkiGemetar.isSelected() == true) {
            slkiGemetar = "ya";
        } else {
            slkiGemetar = "tidak";
        }

        if (chkSlkiBerkeringat.isSelected() == true) {
            slkiBerkeringat = "ya";
        } else {
            slkiBerkeringat = "tidak";
        }

        if (chkSlkiRasa.isSelected() == true) {
            slkiRasa = "ya";
        } else {
            slkiRasa = "tidak";
        }

        if (chkSlkiKadar.isSelected() == true) {
            slkiKadar = "ya";
        } else {
            slkiKadar = "tidak";
        }

        if (chkSlkiJumlah.isSelected() == true) {
            slkiJumlah = "ya";
        } else {
            slkiJumlah = "tidak";
        }

        if (chkSikiIdenKemungkinan.isSelected() == true) {
            sikiIdenKemungkinan = "ya";
        } else {
            sikiIdenKemungkinan = "tidak";
        }

        if (chkSikiIdenSituasi.isSelected() == true) {
            sikiIdenSituasi = "ya";
        } else {
            sikiIdenSituasi = "tidak";
        }

        if (chkSikiMonitorKadar.isSelected() == true) {
            sikiMonitorKadar = "ya";
        } else {
            sikiMonitorKadar = "tidak";
        }

        if (chkSikiMonitorTanda.isSelected() == true) {
            sikiMonitorTanda = "ya";
        } else {
            sikiMonitorTanda = "tidak";
        }

        if (chkSikiMonitorIntake.isSelected() == true) {
            sikiMonitorIntake = "ya";
        } else {
            sikiMonitorIntake = "tidak";
        }

        if (chkSikiMonitorKeton.isSelected() == true) {
            sikiMonitorKeton = "ya";
        } else {
            sikiMonitorKeton = "tidak";
        }

        if (chkSikiBerikan.isSelected() == true) {
            sikiBerikan = "ya";
        } else {
            sikiBerikan = "tidak";
        }

        if (chkSikiKonsultasi.isSelected() == true) {
            sikiKonsultasi = "ya";
        } else {
            sikiKonsultasi = "tidak";
        }

        if (chkSikiFasilitasi.isSelected() == true) {
            sikiFasilitasi = "ya";
        } else {
            sikiFasilitasi = "tidak";
        }

        if (chkSikiAnjurMenghindari.isSelected() == true) {
            sikiAnjurMenghindari = "ya";
        } else {
            sikiAnjurMenghindari = "tidak";
        }

        if (chkSikiAnjurMonitor.isSelected() == true) {
            sikiAnjurMonitor = "ya";
        } else {
            sikiAnjurMonitor = "tidak";
        }

        if (chkSikiAnjurKepatuhan.isSelected() == true) {
            sikiAnjurKepatuhan = "ya";
        } else {
            sikiAnjurKepatuhan = "tidak";
        }

        if (chkSikiAjarkanIndikasi.isSelected() == true) {
            sikiAjarkanIndikasi = "ya";
        } else {
            sikiAjarkanIndikasi = "tidak";
        }

        if (chkSikiAjarkanPengelolaan.isSelected() == true) {
            sikiAjarkanPengelolaan = "ya";
        } else {
            sikiAjarkanPengelolaan = "tidak";
        }

        if (chkSikiKolabInsulin.isSelected() == true) {
            sikiKolabInsulin = "ya";
        } else {
            sikiKolabInsulin = "tidak";
        }

        if (chkSikiKolabIv.isSelected() == true) {
            sikiKolabIv = "ya";
        } else {
            sikiKolabIv = "tidak";
        }

        if (chkSikiKolabKalium.isSelected() == true) {
            sikiKolabKalium = "ya";
        } else {
            sikiKolabKalium = "tidak";
        }
    }
    
    private void emptVariabel() {
        sdkiDisfungsiPankreas = "";
        sdkiResistensi = "";
        sdkiGangguanToleransi = "";
        sdkiGangguanGlukosa = "";
        sdkiPenggunaanInsulin = "";
        sdkiHiperinsulin = "";
        sdkiEndokrin = "";
        sdkiDisfungsiHati = "";
        sdkiDisfungsiGinjal = "";
        sdkiEfek = "";
        sdkiTindakan = "";
        sdkiGangguanMetabolik = "";
        sdkiMengantuk = "";
        sdkiPusing = "";
        sdkiGangguanKordinasi = "";
        sdkiKadarRendah = "";
        sdkiLelah = "";
        sdkiKadarTinggi = "";
        sdkiPalpitasi = "";
        sdkiMengeluh = "";
        sdkiGemetar = "";
        sdkiKesadaran = "";
        sdkiPerilaku = "";
        sdkiSulit = "";
        sdkiBerkeringat = "";
        sdkiMulut = "";
        sdkiJumlah = "";
        sdkiHaus = "";
        sdkiDiabetesMellitus = "";
        sdkiKetoasidosis = "";
        sdkiHipoglikemia = "";
        sdkiHiperglikemia = "";
        sdkiPenggunaanKorti = "";
        sdkiNutrisi = "";
        sdkiDiabetesGestasional = "";
        slkiKestabilan = "";
        slkiKesadaran = "";
        slkiMengantuk = "";
        slkiPusing = "";
        slkiLelah = "";
        slkiKeluhan = "";
        slkiGemetar = "";
        slkiBerkeringat = "";
        slkiRasa = "";
        slkiKadar = "";
        slkiJumlah = "";
        sikiIdenKemungkinan = "";
        sikiIdenSituasi = "";
        sikiMonitorKadar = "";
        sikiMonitorTanda = "";
        sikiMonitorIntake = "";
        sikiMonitorKeton = "";
        sikiBerikan = "";
        sikiKonsultasi = "";
        sikiFasilitasi = "";
        sikiAnjurMenghindari = "";
        sikiAnjurMonitor = "";
        sikiAnjurKepatuhan = "";
        sikiAjarkanIndikasi = "";
        sikiAjarkanPengelolaan = "";
        sikiKolabInsulin = "";
        sikiKolabIv = "";
        sikiKolabKalium = "";
    }
    
    public void awalData() {
        tampil();
    }
}
