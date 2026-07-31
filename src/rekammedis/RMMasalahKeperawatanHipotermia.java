package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCatatanResep;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
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
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class RMMasalahKeperawatanHipotermia extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String sdkiKerusakan = "", sdkiKonsumsi = "", sdkiBeratEkstrim = "", sdkiKekurangan = "", sdkiTerpapar = "", sdkiMalnutrisi = "", sdkiPemakaian = "", sdkiPenurunan = "",
            sdkiTidak = "", sdkiTransfer = "", sdkiTrauma = "", sdkiProses = "", sdkiEfek = "", sdkiKurang = "", sdkiKulit = "", sdkiMenggigil = "", sdkiSuhu = "", sdkiHipotiroid = "",
            sdkiAnoreksia = "", sdkiCedera = "", sdkiPrema = "", sdkiBeratRendah = "", sdkiTenggelam = "", slkiTermoregulasi = "", slkiTermoregulasiNeo = "", slkiMenggigil = "",
            slkiKulit = "", slkiKejang = "", slkiAkros = "", slkiKonsumsi = "", slkiPilo = "", slkiVasok = "", slkiKutis = "", slkiPucat = "", slkiTakik = "", slkiTakip = "",
            slkiBradi = "", slkiDasar = "", slkiHipoksia = "", slkiSuhuTubuh = "", slkiSuhuKulit = "", slkiKadar = "", slkiPengisian = "", slkiVentilasi = "", slkiTekanan = "",
            sikiMonitorSuhu = "", sikiIdentifikasi = "", sikiMonitorTanda = "", sikiSediakan = "", sikiGanti = "", sikiLakukanPasif = "", sikiAktifEksternal = "", sikiAktifInternal = "",
            sikiAnjurkan = "", sttsRawat = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMMasalahKeperawatanHipotermia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "waktu_simpan", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Simpan", "Nama Petugas",
            "sdki_kerusakan", "sdki_konsumsi", "sdki_berat_ekstrim", "sdki_kekurangan", "sdki_terpapar", "sdki_malnutrisi", "sdki_pemakaian", "sdki_penurunan", "sdki_tidak",
            "sdki_transfer", "sdki_trauma", "sdki_proses", "sdki_efek", "sdki_kurang", "sdki_kulit", "sdki_menggigil", "sdki_suhu", "sdki_hipotiroid", "sdki_anoreksia",
            "sdki_cedera", "sdki_prema", "sdki_berat_rendah", "sdki_tenggelam", "slki_termoregulasi", "slki_termoregulasi_neo", "ket_selama", "slki_menggigil", "slki_kulit",
            "slki_kejang", "slki_akros", "slki_konsumsi", "slki_pilo", "slki_vasok", "slki_kutis", "slki_pucat", "slki_takik", "slki_takip", "slki_bradi", "slki_dasar",
            "slki_hipoksia", "slki_suhu_tubuh", "slki_suhu_kulit", "slki_kadar", "slki_pengisian", "slki_ventilasi", "slki_tekanan", "siki_monitor_suhu", "siki_identifikasi",
            "siki_monitor_tanda", "siki_sediakan", "siki_ganti", "siki_lakukan_pasif", "siki_aktif_eksternal", "siki_aktif_internal", "siki_anjurkan", "status_rawat",
            "nip_petugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbMasalah.setModel(tabMode);
        tbMasalah.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMasalah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 65; i++) {
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
                if (akses.getform().equals("RMMasalahKeperawatanHipotermia")) {
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
        chkSdkiKerusakan = new widget.CekBox();
        chkSdkiKonsumsi = new widget.CekBox();
        chkSdkiBeratEkstrim = new widget.CekBox();
        chkSdkiKekurangan = new widget.CekBox();
        chkSdkiTerpapar = new widget.CekBox();
        chkSdkiMalnutrisi = new widget.CekBox();
        chkSdkiHipotiroid = new widget.CekBox();
        chkSdkiAnoreksia = new widget.CekBox();
        chkSdkiCedera = new widget.CekBox();
        chkSdkiPrema = new widget.CekBox();
        chkSlkiTermoregulasi = new widget.CekBox();
        jLabel74 = new widget.Label();
        jLabel75 = new widget.Label();
        jLabel76 = new widget.Label();
        chkSdkiPenurunan = new widget.CekBox();
        chkSdkiKulit = new widget.CekBox();
        chkSdkiMenggigil = new widget.CekBox();
        chkSdkiSuhu = new widget.CekBox();
        jLabel78 = new widget.Label();
        jLabel79 = new widget.Label();
        TketSelama = new widget.TextBox();
        chkSdkiBeratRendah = new widget.CekBox();
        chkSdkiTenggelam = new widget.CekBox();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        chkSlkiTermoregulasiNeo = new widget.CekBox();
        chkSlkiMenggigil = new widget.CekBox();
        chkSlkiKulit = new widget.CekBox();
        chkSlkiKejang = new widget.CekBox();
        chkSikiMonitorSuhu = new widget.CekBox();
        chkSikiIdentifikasi = new widget.CekBox();
        chkSikiMonitorTanda = new widget.CekBox();
        jLabel83 = new widget.Label();
        chkSikiSediakan = new widget.CekBox();
        chkSikiGanti = new widget.CekBox();
        chkSikiLakukanPasif = new widget.CekBox();
        chkSikiAktifEksternal = new widget.CekBox();
        jLabel84 = new widget.Label();
        chkSikiAnjurkan = new widget.CekBox();
        chkSdkiPemakaian = new widget.CekBox();
        jLabel91 = new widget.Label();
        chkSlkiKonsumsi = new widget.CekBox();
        chkSlkiPilo = new widget.CekBox();
        chkSlkiVasok = new widget.CekBox();
        chkSlkiKutis = new widget.CekBox();
        chkSlkiPucat = new widget.CekBox();
        chkSlkiTakip = new widget.CekBox();
        chkSlkiBradi = new widget.CekBox();
        chkSlkiDasar = new widget.CekBox();
        chkSlkiHipoksia = new widget.CekBox();
        chkSlkiSuhuTubuh = new widget.CekBox();
        chkSikiAktifInternal = new widget.CekBox();
        chkSdkiTidak = new widget.CekBox();
        chkSdkiTransfer = new widget.CekBox();
        chkSdkiTrauma = new widget.CekBox();
        chkSdkiProses = new widget.CekBox();
        chkSdkiEfek = new widget.CekBox();
        chkSdkiKurang = new widget.CekBox();
        jLabel92 = new widget.Label();
        chkSlkiAkros = new widget.CekBox();
        chkSlkiTakik = new widget.CekBox();
        chkSlkiSuhuKulit = new widget.CekBox();
        chkSlkiKadar = new widget.CekBox();
        chkSlkiPengisian = new widget.CekBox();
        chkSlkiVentilasi = new widget.CekBox();
        chkSlkiTekanan = new widget.CekBox();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Masalah Keperawatan Resiko Hipotermia ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(760, 1169));
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
        label20.setBounds(0, 1129, 140, 23);

        TnipPetugas.setEditable(false);
        TnipPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnipPetugas.setName("TnipPetugas"); // NOI18N
        TnipPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipPetugas);
        TnipPetugas.setBounds(145, 1129, 150, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        TnmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(300, 1129, 360, 23);

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
        BtnPetugas.setBounds(664, 1129, 28, 23);

        chkSdkiKerusakan.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKerusakan.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKerusakan.setText("Kerusakan hipotalamus");
        chkSdkiKerusakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKerusakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKerusakan.setName("chkSdkiKerusakan"); // NOI18N
        chkSdkiKerusakan.setOpaque(false);
        chkSdkiKerusakan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKerusakan);
        chkSdkiKerusakan.setBounds(145, 122, 150, 23);

        chkSdkiKonsumsi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKonsumsi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKonsumsi.setText("Konsumsi alkohol");
        chkSdkiKonsumsi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKonsumsi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKonsumsi.setName("chkSdkiKonsumsi"); // NOI18N
        chkSdkiKonsumsi.setOpaque(false);
        chkSdkiKonsumsi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKonsumsi);
        chkSdkiKonsumsi.setBounds(145, 150, 120, 23);

        chkSdkiBeratEkstrim.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiBeratEkstrim.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiBeratEkstrim.setText("Berat badan ekstrim");
        chkSdkiBeratEkstrim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiBeratEkstrim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiBeratEkstrim.setName("chkSdkiBeratEkstrim"); // NOI18N
        chkSdkiBeratEkstrim.setOpaque(false);
        chkSdkiBeratEkstrim.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiBeratEkstrim);
        chkSdkiBeratEkstrim.setBounds(145, 178, 170, 23);

        chkSdkiKekurangan.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKekurangan.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKekurangan.setText("Kekurangan lemak subkutan");
        chkSdkiKekurangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKekurangan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKekurangan.setName("chkSdkiKekurangan"); // NOI18N
        chkSdkiKekurangan.setOpaque(false);
        chkSdkiKekurangan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKekurangan);
        chkSdkiKekurangan.setBounds(145, 206, 170, 23);

        chkSdkiTerpapar.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTerpapar.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTerpapar.setText("Terpapar suhu lingkungan rendah");
        chkSdkiTerpapar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTerpapar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTerpapar.setName("chkSdkiTerpapar"); // NOI18N
        chkSdkiTerpapar.setOpaque(false);
        chkSdkiTerpapar.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTerpapar);
        chkSdkiTerpapar.setBounds(145, 234, 190, 23);

        chkSdkiMalnutrisi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMalnutrisi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMalnutrisi.setText("Malnutrisi");
        chkSdkiMalnutrisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMalnutrisi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMalnutrisi.setName("chkSdkiMalnutrisi"); // NOI18N
        chkSdkiMalnutrisi.setOpaque(false);
        chkSdkiMalnutrisi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMalnutrisi);
        chkSdkiMalnutrisi.setBounds(145, 262, 90, 23);

        chkSdkiHipotiroid.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiHipotiroid.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiHipotiroid.setText("Hipotiroidisme");
        chkSdkiHipotiroid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiHipotiroid.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiHipotiroid.setName("chkSdkiHipotiroid"); // NOI18N
        chkSdkiHipotiroid.setOpaque(false);
        chkSdkiHipotiroid.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSdkiHipotiroid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSdkiHipotiroidActionPerformed(evt);
            }
        });
        FormInput.add(chkSdkiHipotiroid);
        chkSdkiHipotiroid.setBounds(145, 402, 110, 23);

        chkSdkiAnoreksia.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiAnoreksia.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiAnoreksia.setText("Anoreksia nervosa");
        chkSdkiAnoreksia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiAnoreksia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiAnoreksia.setName("chkSdkiAnoreksia"); // NOI18N
        chkSdkiAnoreksia.setOpaque(false);
        chkSdkiAnoreksia.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiAnoreksia);
        chkSdkiAnoreksia.setBounds(145, 430, 120, 23);

        chkSdkiCedera.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiCedera.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiCedera.setText("Cedera batang otak");
        chkSdkiCedera.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiCedera.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiCedera.setName("chkSdkiCedera"); // NOI18N
        chkSdkiCedera.setOpaque(false);
        chkSdkiCedera.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiCedera);
        chkSdkiCedera.setBounds(145, 458, 130, 23);

        chkSdkiPrema.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPrema.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPrema.setText("Prematuritas");
        chkSdkiPrema.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPrema.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPrema.setName("chkSdkiPrema"); // NOI18N
        chkSdkiPrema.setOpaque(false);
        chkSdkiPrema.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPrema);
        chkSdkiPrema.setBounds(350, 402, 100, 23);

        chkSlkiTermoregulasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiTermoregulasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiTermoregulasi.setText("Termoregulasi (L.14134)");
        chkSlkiTermoregulasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiTermoregulasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiTermoregulasi.setName("chkSlkiTermoregulasi"); // NOI18N
        chkSlkiTermoregulasi.setOpaque(false);
        chkSlkiTermoregulasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiTermoregulasi);
        chkSlkiTermoregulasi.setBounds(145, 514, 150, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Diagnosis Keperawatan SDKI : Hipotermia (D.0131)");
        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 66, 340, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Berhubungan Dengan :");
        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 94, 180, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("DO/DS (Gejala dan tanda Mayor)");
        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(145, 318, 210, 23);

        chkSdkiPenurunan.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPenurunan.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPenurunan.setText("Penurunan laju metabolisme");
        chkSdkiPenurunan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPenurunan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPenurunan.setName("chkSdkiPenurunan"); // NOI18N
        chkSdkiPenurunan.setOpaque(false);
        chkSdkiPenurunan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPenurunan);
        chkSdkiPenurunan.setBounds(350, 122, 170, 23);

        chkSdkiKulit.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKulit.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKulit.setText("Kulit teraba dingin");
        chkSdkiKulit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKulit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKulit.setName("chkSdkiKulit"); // NOI18N
        chkSdkiKulit.setOpaque(false);
        chkSdkiKulit.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKulit);
        chkSdkiKulit.setBounds(145, 346, 120, 23);

        chkSdkiMenggigil.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMenggigil.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMenggigil.setText("Menggigil");
        chkSdkiMenggigil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMenggigil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMenggigil.setName("chkSdkiMenggigil"); // NOI18N
        chkSdkiMenggigil.setOpaque(false);
        chkSdkiMenggigil.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMenggigil);
        chkSdkiMenggigil.setBounds(350, 346, 80, 23);

        chkSdkiSuhu.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiSuhu.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiSuhu.setText("Suhu tubuh dibawah nilai normal");
        chkSdkiSuhu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiSuhu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiSuhu.setName("chkSdkiSuhu"); // NOI18N
        chkSdkiSuhu.setOpaque(false);
        chkSdkiSuhu.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiSuhu);
        chkSdkiSuhu.setBounds(450, 346, 190, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("Kondisi Klinis Terkait :");
        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(145, 374, 140, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("(Rencana Keperawatan) Tujuan dan Kriteria Hasil SLKI :");
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 486, 370, 23);

        TketSelama.setForeground(new java.awt.Color(0, 0, 0));
        TketSelama.setName("TketSelama"); // NOI18N
        FormInput.add(TketSelama);
        TketSelama.setBounds(384, 570, 150, 23);

        chkSdkiBeratRendah.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiBeratRendah.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiBeratRendah.setText("Berat badan lahir rendah");
        chkSdkiBeratRendah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiBeratRendah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiBeratRendah.setName("chkSdkiBeratRendah"); // NOI18N
        chkSdkiBeratRendah.setOpaque(false);
        chkSdkiBeratRendah.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiBeratRendah);
        chkSdkiBeratRendah.setBounds(350, 430, 150, 23);

        chkSdkiTenggelam.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTenggelam.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTenggelam.setText("Tenggelam");
        chkSdkiTenggelam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTenggelam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTenggelam.setName("chkSdkiTenggelam"); // NOI18N
        chkSdkiTenggelam.setOpaque(false);
        chkSdkiTenggelam.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTenggelam);
        chkSdkiTenggelam.setBounds(350, 458, 90, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("(Rencana Keperawatan) Intervensi Keperawatan SIKI : Manajemen hipotermia (I.14507)");
        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 738, 540, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("Tindakan Observasi :");
        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(145, 766, 140, 23);

        chkSlkiTermoregulasiNeo.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiTermoregulasiNeo.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiTermoregulasiNeo.setText("Termoregulasi neonatus (L.14135)");
        chkSlkiTermoregulasiNeo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiTermoregulasiNeo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiTermoregulasiNeo.setName("chkSlkiTermoregulasiNeo"); // NOI18N
        chkSlkiTermoregulasiNeo.setOpaque(false);
        chkSlkiTermoregulasiNeo.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiTermoregulasiNeo);
        chkSlkiTermoregulasiNeo.setBounds(145, 542, 200, 23);

        chkSlkiMenggigil.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiMenggigil.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiMenggigil.setText("Mengigil menurun");
        chkSlkiMenggigil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiMenggigil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiMenggigil.setName("chkSlkiMenggigil"); // NOI18N
        chkSlkiMenggigil.setOpaque(false);
        chkSlkiMenggigil.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiMenggigil);
        chkSlkiMenggigil.setBounds(145, 598, 120, 23);

        chkSlkiKulit.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKulit.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKulit.setText("Kulit merah menurun");
        chkSlkiKulit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKulit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKulit.setName("chkSlkiKulit"); // NOI18N
        chkSlkiKulit.setOpaque(false);
        chkSlkiKulit.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKulit);
        chkSlkiKulit.setBounds(145, 626, 130, 23);

        chkSlkiKejang.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKejang.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKejang.setText("Kejang menurun");
        chkSlkiKejang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKejang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKejang.setName("chkSlkiKejang"); // NOI18N
        chkSlkiKejang.setOpaque(false);
        chkSlkiKejang.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKejang);
        chkSlkiKejang.setBounds(145, 654, 110, 23);

        chkSikiMonitorSuhu.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiMonitorSuhu.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiMonitorSuhu.setText("Periksa sirkulasi perifer (mis.nadi perifer, edema, pengisian kapiler, warna, suhu)");
        chkSikiMonitorSuhu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiMonitorSuhu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiMonitorSuhu.setName("chkSikiMonitorSuhu"); // NOI18N
        chkSikiMonitorSuhu.setOpaque(false);
        chkSikiMonitorSuhu.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiMonitorSuhu);
        chkSikiMonitorSuhu.setBounds(145, 794, 420, 23);

        chkSikiIdentifikasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiIdentifikasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiIdentifikasi.setText("<html>Identifikasi penyebab hipotermia (mis, terpapar suhu lingkungan rendah, pakaian tipis,kerusakan hipotalamus, penurunan laju metabolisme, kekurangan lemak subkutan)</html>");
        chkSikiIdentifikasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiIdentifikasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiIdentifikasi.setName("chkSikiIdentifikasi"); // NOI18N
        chkSikiIdentifikasi.setOpaque(false);
        chkSikiIdentifikasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiIdentifikasi);
        chkSikiIdentifikasi.setBounds(145, 822, 640, 30);

        chkSikiMonitorTanda.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiMonitorTanda.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiMonitorTanda.setText("<html>Monitor tanda dan gejala akibat hipotermia (hipotermia ringan : takipnea, disartria, mengigil, hipertensi, diuresis ; hipotermia sedang : aritmia, hipotensi, apatis, koagulopati, refleks menurun, hipotermia berat : oliguria, refleks menghilang, edema paru, asam-basa abnormal)</html>");
        chkSikiMonitorTanda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiMonitorTanda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiMonitorTanda.setName("chkSikiMonitorTanda"); // NOI18N
        chkSikiMonitorTanda.setOpaque(false);
        chkSikiMonitorTanda.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiMonitorTanda);
        chkSikiMonitorTanda.setBounds(145, 857, 640, 43);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("Terapeutik :");
        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(145, 905, 90, 23);

        chkSikiSediakan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiSediakan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiSediakan.setText("Sediakan lingkungan yang hangat (mis, atur suhu ruangan, inkubator)");
        chkSikiSediakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiSediakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiSediakan.setName("chkSikiSediakan"); // NOI18N
        chkSikiSediakan.setOpaque(false);
        chkSikiSediakan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiSediakan);
        chkSikiSediakan.setBounds(145, 933, 370, 23);

        chkSikiGanti.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiGanti.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiGanti.setText("Ganti pakaian dan/atau linen yang basah");
        chkSikiGanti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiGanti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiGanti.setName("chkSikiGanti"); // NOI18N
        chkSikiGanti.setOpaque(false);
        chkSikiGanti.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiGanti);
        chkSikiGanti.setBounds(145, 961, 240, 23);

        chkSikiLakukanPasif.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiLakukanPasif.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiLakukanPasif.setText("Lakukan penghangatan pasif (mis, selimut, menutup kepala, pakaian tebal)");
        chkSikiLakukanPasif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiLakukanPasif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiLakukanPasif.setName("chkSikiLakukanPasif"); // NOI18N
        chkSikiLakukanPasif.setOpaque(false);
        chkSikiLakukanPasif.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiLakukanPasif);
        chkSikiLakukanPasif.setBounds(145, 989, 390, 23);

        chkSikiAktifEksternal.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAktifEksternal.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAktifEksternal.setText("Lakukan penghangatan aktif eksternal (mis, kompres hangat, botol hangat, selimut hangat, perawatan metode kangguru)");
        chkSikiAktifEksternal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAktifEksternal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAktifEksternal.setName("chkSikiAktifEksternal"); // NOI18N
        chkSikiAktifEksternal.setOpaque(false);
        chkSikiAktifEksternal.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAktifEksternal);
        chkSikiAktifEksternal.setBounds(145, 1017, 620, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("Edukasi :");
        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(145, 1073, 90, 23);

        chkSikiAnjurkan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAnjurkan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAnjurkan.setText("Anjurkan makan/minum hangat");
        chkSikiAnjurkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAnjurkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAnjurkan.setName("chkSikiAnjurkan"); // NOI18N
        chkSikiAnjurkan.setOpaque(false);
        chkSikiAnjurkan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAnjurkan);
        chkSikiAnjurkan.setBounds(145, 1101, 190, 23);

        chkSdkiPemakaian.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPemakaian.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPemakaian.setText("Pemakaian pakaian tipis");
        chkSdkiPemakaian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPemakaian.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPemakaian.setName("chkSdkiPemakaian"); // NOI18N
        chkSdkiPemakaian.setOpaque(false);
        chkSdkiPemakaian.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPemakaian);
        chkSdkiPemakaian.setBounds(145, 290, 150, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel91.setText("Setelah dilakukan tindakan keperawatan selama");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(145, 570, 235, 23);

        chkSlkiKonsumsi.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKonsumsi.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKonsumsi.setText("Konsumsi oksigen menurun");
        chkSlkiKonsumsi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKonsumsi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKonsumsi.setName("chkSlkiKonsumsi"); // NOI18N
        chkSlkiKonsumsi.setOpaque(false);
        chkSlkiKonsumsi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKonsumsi);
        chkSlkiKonsumsi.setBounds(145, 710, 155, 23);

        chkSlkiPilo.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiPilo.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiPilo.setText("Piloereksi menurun");
        chkSlkiPilo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiPilo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiPilo.setName("chkSlkiPilo"); // NOI18N
        chkSlkiPilo.setOpaque(false);
        chkSlkiPilo.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiPilo);
        chkSlkiPilo.setBounds(310, 598, 130, 23);

        chkSlkiVasok.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiVasok.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiVasok.setText("Vasokonstriksi perifer menurun");
        chkSlkiVasok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiVasok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiVasok.setName("chkSlkiVasok"); // NOI18N
        chkSlkiVasok.setOpaque(false);
        chkSlkiVasok.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiVasok);
        chkSlkiVasok.setBounds(310, 626, 180, 23);

        chkSlkiKutis.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKutis.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKutis.setText("Kutis memorata menurun");
        chkSlkiKutis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKutis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKutis.setName("chkSlkiKutis"); // NOI18N
        chkSlkiKutis.setOpaque(false);
        chkSlkiKutis.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKutis);
        chkSlkiKutis.setBounds(310, 654, 160, 23);

        chkSlkiPucat.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiPucat.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiPucat.setText("Pucat menurun");
        chkSlkiPucat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiPucat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiPucat.setName("chkSlkiPucat"); // NOI18N
        chkSlkiPucat.setOpaque(false);
        chkSlkiPucat.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiPucat);
        chkSlkiPucat.setBounds(310, 682, 110, 23);

        chkSlkiTakip.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiTakip.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiTakip.setText("Takipnea menurun");
        chkSlkiTakip.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiTakip.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiTakip.setName("chkSlkiTakip"); // NOI18N
        chkSlkiTakip.setOpaque(false);
        chkSlkiTakip.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiTakip);
        chkSlkiTakip.setBounds(500, 598, 120, 23);

        chkSlkiBradi.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiBradi.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiBradi.setText("Bradikardi menurun");
        chkSlkiBradi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiBradi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiBradi.setName("chkSlkiBradi"); // NOI18N
        chkSlkiBradi.setOpaque(false);
        chkSlkiBradi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiBradi);
        chkSlkiBradi.setBounds(500, 626, 120, 23);

        chkSlkiDasar.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiDasar.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiDasar.setText("Dasar kuku sianotik menurun");
        chkSlkiDasar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiDasar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiDasar.setName("chkSlkiDasar"); // NOI18N
        chkSlkiDasar.setOpaque(false);
        chkSlkiDasar.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiDasar);
        chkSlkiDasar.setBounds(500, 654, 170, 23);

        chkSlkiHipoksia.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiHipoksia.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiHipoksia.setText("Hipoksia menurun");
        chkSlkiHipoksia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiHipoksia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiHipoksia.setName("chkSlkiHipoksia"); // NOI18N
        chkSlkiHipoksia.setOpaque(false);
        chkSlkiHipoksia.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiHipoksia);
        chkSlkiHipoksia.setBounds(500, 682, 120, 23);

        chkSlkiSuhuTubuh.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiSuhuTubuh.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiSuhuTubuh.setText("Suhu tubuh membaik");
        chkSlkiSuhuTubuh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiSuhuTubuh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiSuhuTubuh.setName("chkSlkiSuhuTubuh"); // NOI18N
        chkSlkiSuhuTubuh.setOpaque(false);
        chkSlkiSuhuTubuh.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiSuhuTubuh);
        chkSlkiSuhuTubuh.setBounds(500, 710, 130, 23);

        chkSikiAktifInternal.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAktifInternal.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAktifInternal.setText("Lakukan penghangatan aktif internal (mis, infus cairan hangat, oksigen hangat, lavase peritoneal dengan cairan hangat)");
        chkSikiAktifInternal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAktifInternal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAktifInternal.setName("chkSikiAktifInternal"); // NOI18N
        chkSikiAktifInternal.setOpaque(false);
        chkSikiAktifInternal.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAktifInternal);
        chkSikiAktifInternal.setBounds(145, 1045, 610, 23);

        chkSdkiTidak.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTidak.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTidak.setText("Tidak beraktivitas");
        chkSdkiTidak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTidak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTidak.setName("chkSdkiTidak"); // NOI18N
        chkSdkiTidak.setOpaque(false);
        chkSdkiTidak.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTidak);
        chkSdkiTidak.setBounds(350, 150, 120, 23);

        chkSdkiTransfer.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTransfer.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTransfer.setText("Trasfer panas (mis, konduksi, konveksi, evaporasi, radiasi)");
        chkSdkiTransfer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTransfer.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTransfer.setName("chkSdkiTransfer"); // NOI18N
        chkSdkiTransfer.setOpaque(false);
        chkSdkiTransfer.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTransfer);
        chkSdkiTransfer.setBounds(350, 178, 330, 23);

        chkSdkiTrauma.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTrauma.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTrauma.setText("Trauma");
        chkSdkiTrauma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTrauma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTrauma.setName("chkSdkiTrauma"); // NOI18N
        chkSdkiTrauma.setOpaque(false);
        chkSdkiTrauma.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTrauma);
        chkSdkiTrauma.setBounds(350, 206, 80, 23);

        chkSdkiProses.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiProses.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiProses.setText("Proses penuaan");
        chkSdkiProses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiProses.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiProses.setName("chkSdkiProses"); // NOI18N
        chkSdkiProses.setOpaque(false);
        chkSdkiProses.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiProses);
        chkSdkiProses.setBounds(350, 234, 110, 23);

        chkSdkiEfek.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiEfek.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiEfek.setText("Efek agen farmakologis");
        chkSdkiEfek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiEfek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiEfek.setName("chkSdkiEfek"); // NOI18N
        chkSdkiEfek.setOpaque(false);
        chkSdkiEfek.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiEfek);
        chkSdkiEfek.setBounds(350, 262, 150, 23);

        chkSdkiKurang.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKurang.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKurang.setText("Kurang terpapar informasi tentang pencegahan hipotermia");
        chkSdkiKurang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKurang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKurang.setName("chkSdkiKurang"); // NOI18N
        chkSdkiKurang.setOpaque(false);
        chkSdkiKurang.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKurang);
        chkSdkiKurang.setBounds(350, 290, 320, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel92.setText("hipotermia pada pasien teratasi dengan kriteria hasil :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(540, 570, 270, 23);

        chkSlkiAkros.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiAkros.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiAkros.setText("Akrosianosis menurun");
        chkSlkiAkros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiAkros.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiAkros.setName("chkSlkiAkros"); // NOI18N
        chkSlkiAkros.setOpaque(false);
        chkSlkiAkros.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiAkros);
        chkSlkiAkros.setBounds(145, 682, 140, 23);

        chkSlkiTakik.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiTakik.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiTakik.setText("Takikardi menurun");
        chkSlkiTakik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiTakik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiTakik.setName("chkSlkiTakik"); // NOI18N
        chkSlkiTakik.setOpaque(false);
        chkSlkiTakik.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiTakik);
        chkSlkiTakik.setBounds(310, 710, 120, 23);

        chkSlkiSuhuKulit.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiSuhuKulit.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiSuhuKulit.setText("Suhu kulit membaik");
        chkSlkiSuhuKulit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiSuhuKulit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiSuhuKulit.setName("chkSlkiSuhuKulit"); // NOI18N
        chkSlkiSuhuKulit.setOpaque(false);
        chkSlkiSuhuKulit.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiSuhuKulit);
        chkSlkiSuhuKulit.setBounds(680, 598, 120, 23);

        chkSlkiKadar.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKadar.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKadar.setText("Kadar glukosa darah membaik");
        chkSlkiKadar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKadar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKadar.setName("chkSlkiKadar"); // NOI18N
        chkSlkiKadar.setOpaque(false);
        chkSlkiKadar.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKadar);
        chkSlkiKadar.setBounds(680, 626, 180, 23);

        chkSlkiPengisian.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiPengisian.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiPengisian.setText("Pengisian kapiler membaik");
        chkSlkiPengisian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiPengisian.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiPengisian.setName("chkSlkiPengisian"); // NOI18N
        chkSlkiPengisian.setOpaque(false);
        chkSlkiPengisian.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiPengisian);
        chkSlkiPengisian.setBounds(680, 654, 160, 23);

        chkSlkiVentilasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiVentilasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiVentilasi.setText("Ventilasi membaik");
        chkSlkiVentilasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiVentilasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiVentilasi.setName("chkSlkiVentilasi"); // NOI18N
        chkSlkiVentilasi.setOpaque(false);
        chkSlkiVentilasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiVentilasi);
        chkSlkiVentilasi.setBounds(680, 682, 120, 23);

        chkSlkiTekanan.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiTekanan.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiTekanan.setText("Tekanan darah membaik");
        chkSlkiTekanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiTekanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiTekanan.setName("chkSlkiTekanan"); // NOI18N
        chkSlkiTekanan.setOpaque(false);
        chkSlkiTekanan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiTekanan);
        chkSlkiTekanan.setBounds(680, 710, 160, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass13.add(Scroll1);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data MasKep Resiko Hipotermia ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
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

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-07-2026" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-07-2026" }));
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
            if (Sequel.menyimpantf("masalah_keperawatan_hipotermia", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 60, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), sdkiKerusakan, sdkiKonsumsi, sdkiBeratEkstrim, sdkiKekurangan, sdkiTerpapar, sdkiMalnutrisi, sdkiPemakaian,
                        sdkiPenurunan, sdkiTidak, sdkiTransfer, sdkiTrauma, sdkiProses, sdkiEfek, sdkiKurang, sdkiKulit, sdkiMenggigil, sdkiSuhu, sdkiHipotiroid, sdkiAnoreksia,
                        sdkiCedera, sdkiPrema, sdkiBeratRendah, sdkiTenggelam, slkiTermoregulasi, slkiTermoregulasiNeo, TketSelama.getText(), slkiMenggigil, slkiKulit, slkiKejang, 
                        slkiAkros, slkiKonsumsi, slkiPilo, slkiVasok, slkiKutis, slkiPucat, slkiTakik, slkiTakip, slkiBradi, slkiDasar, slkiHipoksia, slkiSuhuTubuh, slkiSuhuKulit,
                        slkiKadar, slkiPengisian, slkiVentilasi, slkiTekanan, sikiMonitorSuhu, sikiIdentifikasi, sikiMonitorTanda, sikiSediakan, sikiGanti, sikiLakukanPasif,
                        sikiAktifEksternal, sikiAktifInternal, sikiAnjurkan, sttsRawat, TnipPetugas.getText(), Sequel.cariIsi("select now()")
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Masalah Keperawatan Resiko Hipotermia", "Simpan");
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
                if (Sequel.mengedittf("masalah_keperawatan_hipotermia", "waktu_simpan=?", "sdki_kerusakan=?, sdki_konsumsi=?, sdki_berat_ekstrim=?, sdki_kekurangan=?, sdki_terpapar=?, "
                        + "sdki_malnutrisi=?, sdki_pemakaian=?, sdki_penurunan=?, sdki_tidak=?, sdki_transfer=?, sdki_trauma=?, sdki_proses=?, sdki_efek=?, sdki_kurang=?, sdki_kulit=?, "
                        + "sdki_menggigil=?, sdki_suhu=?, sdki_hipotiroid=?, sdki_anoreksia=?, sdki_cedera=?, sdki_prema=?, sdki_berat_rendah=?, sdki_tenggelam=?, slki_termoregulasi=?, "
                        + "slki_termoregulasi_neo=?, ket_selama=?, slki_menggigil=?, slki_kulit=?, slki_kejang=?, slki_akros=?, slki_konsumsi=?, slki_pilo=?, slki_vasok=?, slki_kutis=?, "
                        + "slki_pucat=?, slki_takik=?, slki_takip=?, slki_bradi=?, slki_dasar=?, slki_hipoksia=?, slki_suhu_tubuh=?, slki_suhu_kulit=?, slki_kadar=?, slki_pengisian=?, "
                        + "slki_ventilasi=?, slki_tekanan=?, siki_monitor_suhu=?, siki_identifikasi=?, siki_monitor_tanda=?, siki_sediakan=?, siki_ganti=?, siki_lakukan_pasif=?, "
                        + "siki_aktif_eksternal=?, siki_aktif_internal=?, siki_anjurkan=?, nip_petugas=?", 57, new String[]{
                            sdkiKerusakan, sdkiKonsumsi, sdkiBeratEkstrim, sdkiKekurangan, sdkiTerpapar, sdkiMalnutrisi, sdkiPemakaian,
                            sdkiPenurunan, sdkiTidak, sdkiTransfer, sdkiTrauma, sdkiProses, sdkiEfek, sdkiKurang, sdkiKulit, sdkiMenggigil, sdkiSuhu, sdkiHipotiroid, sdkiAnoreksia,
                            sdkiCedera, sdkiPrema, sdkiBeratRendah, sdkiTenggelam, slkiTermoregulasi, slkiTermoregulasiNeo, TketSelama.getText(), slkiMenggigil, slkiKulit, slkiKejang,
                            slkiAkros, slkiKonsumsi, slkiPilo, slkiVasok, slkiKutis, slkiPucat, slkiTakik, slkiTakip, slkiBradi, slkiDasar, slkiHipoksia, slkiSuhuTubuh, slkiSuhuKulit,
                            slkiKadar, slkiPengisian, slkiVentilasi, slkiTekanan, sikiMonitorSuhu, sikiIdentifikasi, sikiMonitorTanda, sikiSediakan, sikiGanti, sikiLakukanPasif,
                            sikiAktifEksternal, sikiAktifInternal, sikiAnjurkan, TnipPetugas.getText(),
                            tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 0).toString()
                        }) == true) {

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Masalah Keperawatan Resiko Hipotermia", "Ganti");
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
        akses.setform("RMMasalahKeperawatanHipotermia");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbMasalah.getSelectedRow() > -1) {
            if (akses.getadmin() == true || tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 64).toString().equals(akses.getkode())) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from masalah_keperawatan_hipotermia where waktu_simpan=?", 1, new String[]{
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

            if (chkSdkiKerusakan.isSelected() == true) {
                param.put("sdkiKerusakan", "V");
            } else {
                param.put("sdkiKerusakan", "");
            }

            if (chkSdkiKonsumsi.isSelected() == true) {
                param.put("sdkiKonsumsi", "V");
            } else {
                param.put("sdkiKonsumsi", "");
            }

            if (chkSdkiBeratEkstrim.isSelected() == true) {
                param.put("sdkiBeratEkstrim", "V");
            } else {
                param.put("sdkiBeratEkstrim", "");
            }

            if (chkSdkiKekurangan.isSelected() == true) {
                param.put("sdkiKekurangan", "V");
            } else {
                param.put("sdkiKekurangan", "");
            }

            if (chkSdkiTerpapar.isSelected() == true) {
                param.put("sdkiTerpapar", "V");
            } else {
                param.put("sdkiTerpapar", "");
            }

            if (chkSdkiMalnutrisi.isSelected() == true) {
                param.put("sdkiMalnutrisi", "V");
            } else {
                param.put("sdkiMalnutrisi", "");
            }

            if (chkSdkiPemakaian.isSelected() == true) {
                param.put("sdkiPemakaian", "V");
            } else {
                param.put("sdkiPemakaian", "");
            }

            if (chkSdkiPenurunan.isSelected() == true) {
                param.put("sdkiPenurunan", "V");
            } else {
                param.put("sdkiPenurunan", "");
            }

            if (chkSdkiTidak.isSelected() == true) {
                param.put("sdkiTidak", "V");
            } else {
                param.put("sdkiTidak", "");
            }

            if (chkSdkiTransfer.isSelected() == true) {
                param.put("sdkiTransfer", "V");
            } else {
                param.put("sdkiTransfer", "");
            }

            if (chkSdkiTrauma.isSelected() == true) {
                param.put("sdkiTrauma", "V");
            } else {
                param.put("sdkiTrauma", "");
            }

            if (chkSdkiProses.isSelected() == true) {
                param.put("sdkiProses", "V");
            } else {
                param.put("sdkiProses", "");
            }

            if (chkSdkiEfek.isSelected() == true) {
                param.put("sdkiEfek", "V");
            } else {
                param.put("sdkiEfek", "");
            }

            if (chkSdkiKurang.isSelected() == true) {
                param.put("sdkiKurang", "V");
            } else {
                param.put("sdkiKurang", "");
            }

            if (chkSdkiKulit.isSelected() == true) {
                param.put("sdkiKulit", "V");
            } else {
                param.put("sdkiKulit", "");
            }

            if (chkSdkiMenggigil.isSelected() == true) {
                param.put("sdkiMenggigil", "V");
            } else {
                param.put("sdkiMenggigil", "");
            }

            if (chkSdkiSuhu.isSelected() == true) {
                param.put("sdkiSuhu", "V");
            } else {
                param.put("sdkiSuhu", "");
            }

            if (chkSdkiHipotiroid.isSelected() == true) {
                param.put("sdkiHipotiroid", "V");
            } else {
                param.put("sdkiHipotiroid", "");
            }

            if (chkSdkiAnoreksia.isSelected() == true) {
                param.put("sdkiAnoreksia", "V");
            } else {
                param.put("sdkiAnoreksia", "");
            }

            if (chkSdkiCedera.isSelected() == true) {
                param.put("sdkiCedera", "V");
            } else {
                param.put("sdkiCedera", "");
            }

            if (chkSdkiPrema.isSelected() == true) {
                param.put("sdkiPrema", "V");
            } else {
                param.put("sdkiPrema", "");
            }

            if (chkSdkiBeratRendah.isSelected() == true) {
                param.put("sdkiBeratRendah", "V");
            } else {
                param.put("sdkiBeratRendah", "");
            }

            if (chkSdkiTenggelam.isSelected() == true) {
                param.put("sdkiTenggelam", "V");
            } else {
                param.put("sdkiTenggelam", "");
            }

            if (chkSlkiTermoregulasi.isSelected() == true) {
                param.put("slkiTermoregulasi", "V");
            } else {
                param.put("slkiTermoregulasi", "");
            }

            if (chkSlkiTermoregulasiNeo.isSelected() == true) {
                param.put("slkiTermoregulasiNeo", "V");
            } else {
                param.put("slkiTermoregulasiNeo", "");
            }

            if (TketSelama.getText().equals("")) {
                param.put("ketSelama", "........");
            } else {
                param.put("ketSelama", TketSelama.getText());
            }

            if (chkSlkiMenggigil.isSelected() == true) {
                param.put("slkiMenggigil", "V");
            } else {
                param.put("slkiMenggigil", "");
            }

            if (chkSlkiKulit.isSelected() == true) {
                param.put("slkiKulit", "V");
            } else {
                param.put("slkiKulit", "");
            }

            if (chkSlkiKejang.isSelected() == true) {
                param.put("slkiKejang", "V");
            } else {
                param.put("slkiKejang", "");
            }

            if (chkSlkiAkros.isSelected() == true) {
                param.put("slkiAkros", "V");
            } else {
                param.put("slkiAkros", "");
            }

            if (chkSlkiKonsumsi.isSelected() == true) {
                param.put("slkiKonsumsi", "V");
            } else {
                param.put("slkiKonsumsi", "");
            }

            if (chkSlkiPilo.isSelected() == true) {
                param.put("slkiPilo", "V");
            } else {
                param.put("slkiPilo", "");
            }

            if (chkSlkiVasok.isSelected() == true) {
                param.put("slkiVasok", "V");
            } else {
                param.put("slkiVasok", "");
            }

            if (chkSlkiKutis.isSelected() == true) {
                param.put("slkiKutis", "V");
            } else {
                param.put("slkiKutis", "");
            }

            if (chkSlkiPucat.isSelected() == true) {
                param.put("slkiPucat", "V");
            } else {
                param.put("slkiPucat", "");
            }

            if (chkSlkiTakik.isSelected() == true) {
                param.put("slkiTakik", "V");
            } else {
                param.put("slkiTakik", "");
            }

            if (chkSlkiTakip.isSelected() == true) {
                param.put("slkiTakip", "V");
            } else {
                param.put("slkiTakip", "");
            }

            if (chkSlkiBradi.isSelected() == true) {
                param.put("slkiBradi", "V");
            } else {
                param.put("slkiBradi", "");
            }

            if (chkSlkiDasar.isSelected() == true) {
                param.put("slkiDasar", "V");
            } else {
                param.put("slkiDasar", "");
            }

            if (chkSlkiHipoksia.isSelected() == true) {
                param.put("slkiHipoksia", "V");
            } else {
                param.put("slkiHipoksia", "");
            }

            if (chkSlkiSuhuTubuh.isSelected() == true) {
                param.put("slkiSuhuTubuh", "V");
            } else {
                param.put("slkiSuhuTubuh", "");
            }

            if (chkSlkiSuhuKulit.isSelected() == true) {
                param.put("slkiSuhuKulit", "V");
            } else {
                param.put("slkiSuhuKulit", "");
            }

            if (chkSlkiKadar.isSelected() == true) {
                param.put("slkiKadar", "V");
            } else {
                param.put("slkiKadar", "");
            }

            if (chkSlkiPengisian.isSelected() == true) {
                param.put("slkiPengisian", "V");
            } else {
                param.put("slkiPengisian", "");
            }

            if (chkSlkiVentilasi.isSelected() == true) {
                param.put("slkiVentilasi", "V");
            } else {
                param.put("slkiVentilasi", "");
            }

            if (chkSlkiTekanan.isSelected() == true) {
                param.put("slkiTekanan", "V");
            } else {
                param.put("slkiTekanan", "");
            }

            if (chkSikiMonitorSuhu.isSelected() == true) {
                param.put("sikiMonitorSuhu", "V");
            } else {
                param.put("sikiMonitorSuhu", "");
            }

            if (chkSikiIdentifikasi.isSelected() == true) {
                param.put("sikiIdentifikasi", "V");
            } else {
                param.put("sikiIdentifikasi", "");
            }

            if (chkSikiMonitorTanda.isSelected() == true) {
                param.put("sikiMonitorTanda", "V");
            } else {
                param.put("sikiMonitorTanda", "");
            }

            if (chkSikiSediakan.isSelected() == true) {
                param.put("sikiSediakan", "V");
            } else {
                param.put("sikiSediakan", "");
            }

            if (chkSikiGanti.isSelected() == true) {
                param.put("sikiGanti", "V");
            } else {
                param.put("sikiGanti", "");
            }

            if (chkSikiLakukanPasif.isSelected() == true) {
                param.put("sikiLakukanPasif", "V");
            } else {
                param.put("sikiLakukanPasif", "");
            }

            if (chkSikiAktifEksternal.isSelected() == true) {
                param.put("sikiAktifEksternal", "V");
            } else {
                param.put("sikiAktifEksternal", "");
            }

            if (chkSikiAktifInternal.isSelected() == true) {
                param.put("sikiAktifInternal", "V");
            } else {
                param.put("sikiAktifInternal", "");
            }

            if (chkSikiAnjurkan.isSelected() == true) {
                param.put("sikiAnjurkan", "V");
            } else {
                param.put("sikiAnjurkan", "");
            }

            Valid.MyReport("rptMasKepHipotermia.jasper", "report", "::[ RM Masalah Keperawatan Resiko Hipotermia ]::",
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

    private void chkSdkiHipotiroidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSdkiHipotiroidActionPerformed
        TketSelama.setText("");
        if (chkSdkiHipotiroid.isSelected() == true) {
            TketSelama.setEnabled(true);
            TketSelama.requestFocus();
        } else {
            TketSelama.setEnabled(false);
        }
    }//GEN-LAST:event_chkSdkiHipotiroidActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMMasalahKeperawatanHipotermia dialog = new RMMasalahKeperawatanHipotermia(new javax.swing.JFrame(), true);
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
    private widget.CekBox chkSdkiAnoreksia;
    private widget.CekBox chkSdkiBeratEkstrim;
    private widget.CekBox chkSdkiBeratRendah;
    private widget.CekBox chkSdkiCedera;
    private widget.CekBox chkSdkiEfek;
    private widget.CekBox chkSdkiHipotiroid;
    private widget.CekBox chkSdkiKekurangan;
    private widget.CekBox chkSdkiKerusakan;
    private widget.CekBox chkSdkiKonsumsi;
    private widget.CekBox chkSdkiKulit;
    private widget.CekBox chkSdkiKurang;
    private widget.CekBox chkSdkiMalnutrisi;
    private widget.CekBox chkSdkiMenggigil;
    private widget.CekBox chkSdkiPemakaian;
    private widget.CekBox chkSdkiPenurunan;
    private widget.CekBox chkSdkiPrema;
    private widget.CekBox chkSdkiProses;
    private widget.CekBox chkSdkiSuhu;
    private widget.CekBox chkSdkiTenggelam;
    private widget.CekBox chkSdkiTerpapar;
    private widget.CekBox chkSdkiTidak;
    private widget.CekBox chkSdkiTransfer;
    private widget.CekBox chkSdkiTrauma;
    private widget.CekBox chkSikiAktifEksternal;
    private widget.CekBox chkSikiAktifInternal;
    private widget.CekBox chkSikiAnjurkan;
    private widget.CekBox chkSikiGanti;
    private widget.CekBox chkSikiIdentifikasi;
    private widget.CekBox chkSikiLakukanPasif;
    private widget.CekBox chkSikiMonitorSuhu;
    private widget.CekBox chkSikiMonitorTanda;
    private widget.CekBox chkSikiSediakan;
    private widget.CekBox chkSlkiAkros;
    private widget.CekBox chkSlkiBradi;
    private widget.CekBox chkSlkiDasar;
    private widget.CekBox chkSlkiHipoksia;
    private widget.CekBox chkSlkiKadar;
    private widget.CekBox chkSlkiKejang;
    private widget.CekBox chkSlkiKonsumsi;
    private widget.CekBox chkSlkiKulit;
    private widget.CekBox chkSlkiKutis;
    private widget.CekBox chkSlkiMenggigil;
    private widget.CekBox chkSlkiPengisian;
    private widget.CekBox chkSlkiPilo;
    private widget.CekBox chkSlkiPucat;
    private widget.CekBox chkSlkiSuhuKulit;
    private widget.CekBox chkSlkiSuhuTubuh;
    private widget.CekBox chkSlkiTakik;
    private widget.CekBox chkSlkiTakip;
    private widget.CekBox chkSlkiTekanan;
    private widget.CekBox chkSlkiTermoregulasi;
    private widget.CekBox chkSlkiTermoregulasiNeo;
    private widget.CekBox chkSlkiVasok;
    private widget.CekBox chkSlkiVentilasi;
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
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
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
                    + "pg.nama nmPetugas from masalah_keperawatan_hipotermia m inner join reg_periksa rp on rp.no_rawat=m.no_rawat "
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
                        rs.getString("sdki_kerusakan"),
                        rs.getString("sdki_konsumsi"),
                        rs.getString("sdki_berat_ekstrim"),
                        rs.getString("sdki_kekurangan"),
                        rs.getString("sdki_terpapar"),
                        rs.getString("sdki_malnutrisi"),
                        rs.getString("sdki_pemakaian"),
                        rs.getString("sdki_penurunan"),
                        rs.getString("sdki_tidak"),
                        rs.getString("sdki_transfer"),
                        rs.getString("sdki_trauma"),
                        rs.getString("sdki_proses"),
                        rs.getString("sdki_efek"),
                        rs.getString("sdki_kurang"),
                        rs.getString("sdki_kulit"),
                        rs.getString("sdki_menggigil"),
                        rs.getString("sdki_suhu"),
                        rs.getString("sdki_hipotiroid"),
                        rs.getString("sdki_anoreksia"),
                        rs.getString("sdki_cedera"),
                        rs.getString("sdki_prema"),
                        rs.getString("sdki_berat_rendah"),
                        rs.getString("sdki_tenggelam"),
                        rs.getString("slki_termoregulasi"),
                        rs.getString("slki_termoregulasi_neo"),
                        rs.getString("ket_selama"),
                        rs.getString("slki_menggigil"),
                        rs.getString("slki_kulit"),
                        rs.getString("slki_kejang"),
                        rs.getString("slki_akros"),
                        rs.getString("slki_konsumsi"),
                        rs.getString("slki_pilo"),
                        rs.getString("slki_vasok"),
                        rs.getString("slki_kutis"),
                        rs.getString("slki_pucat"),
                        rs.getString("slki_takik"),
                        rs.getString("slki_takip"),
                        rs.getString("slki_bradi"),
                        rs.getString("slki_dasar"),
                        rs.getString("slki_hipoksia"),
                        rs.getString("slki_suhu_tubuh"),
                        rs.getString("slki_suhu_kulit"),
                        rs.getString("slki_kadar"),
                        rs.getString("slki_pengisian"),
                        rs.getString("slki_ventilasi"),
                        rs.getString("slki_tekanan"),
                        rs.getString("siki_monitor_suhu"),
                        rs.getString("siki_identifikasi"),
                        rs.getString("siki_monitor_tanda"),
                        rs.getString("siki_sediakan"),
                        rs.getString("siki_ganti"),
                        rs.getString("siki_lakukan_pasif"),
                        rs.getString("siki_aktif_eksternal"),
                        rs.getString("siki_aktif_internal"),
                        rs.getString("siki_anjurkan"),
                        rs.getString("status_rawat"),
                        rs.getString("nip_petugas")
                    });
                }
            } catch (Exception e) {
                System.out.println("rekammedis.RMMasalahKeperawatanHipotermia.tampil() : " + e);
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
        chkSdkiKerusakan.setSelected(false);
        chkSdkiKonsumsi.setSelected(false);
        chkSdkiBeratEkstrim.setSelected(false);
        chkSdkiKekurangan.setSelected(false);
        chkSdkiTerpapar.setSelected(false);
        chkSdkiMalnutrisi.setSelected(false);
        chkSdkiPemakaian.setSelected(false);
        chkSdkiPenurunan.setSelected(false);
        chkSdkiTidak.setSelected(false);
        chkSdkiTransfer.setSelected(false);
        chkSdkiTrauma.setSelected(false);
        chkSdkiProses.setSelected(false);
        chkSdkiEfek.setSelected(false);
        chkSdkiKurang.setSelected(false);
        chkSdkiKulit.setSelected(false);
        chkSdkiMenggigil.setSelected(false);
        chkSdkiSuhu.setSelected(false);
        chkSdkiHipotiroid.setSelected(false);
        chkSdkiAnoreksia.setSelected(false);
        chkSdkiCedera.setSelected(false);
        chkSdkiPrema.setSelected(false);
        chkSdkiBeratRendah.setSelected(false);
        chkSdkiTenggelam.setSelected(false);

        chkSlkiTermoregulasi.setSelected(false);
        chkSlkiTermoregulasiNeo.setSelected(false);
        TketSelama.setText("");
        chkSlkiMenggigil.setSelected(false);
        chkSlkiKulit.setSelected(false);
        chkSlkiKejang.setSelected(false);
        chkSlkiAkros.setSelected(false);
        chkSlkiKonsumsi.setSelected(false);
        chkSlkiPilo.setSelected(false);
        chkSlkiVasok.setSelected(false);
        chkSlkiKutis.setSelected(false);
        chkSlkiPucat.setSelected(false);
        chkSlkiTakik.setSelected(false);
        chkSlkiTakip.setSelected(false);
        chkSlkiBradi.setSelected(false);
        chkSlkiDasar.setSelected(false);
        chkSlkiHipoksia.setSelected(false);
        chkSlkiSuhuTubuh.setSelected(false);
        chkSlkiSuhuKulit.setSelected(false);
        chkSlkiKadar.setSelected(false);
        chkSlkiPengisian.setSelected(false);
        chkSlkiVentilasi.setSelected(false);
        chkSlkiTekanan.setSelected(false);

        chkSikiMonitorSuhu.setSelected(false);
        chkSikiIdentifikasi.setSelected(false);
        chkSikiMonitorTanda.setSelected(false);
        chkSikiSediakan.setSelected(false);
        chkSikiGanti.setSelected(false);
        chkSikiLakukanPasif.setSelected(false);
        chkSikiAktifEksternal.setSelected(false);
        chkSikiAktifInternal.setSelected(false);
        chkSikiAnjurkan.setSelected(false);
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

            sdkiKerusakan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 8).toString();
            sdkiKonsumsi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 9).toString();
            sdkiBeratEkstrim = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 10).toString();
            sdkiKekurangan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 11).toString();
            sdkiTerpapar = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 12).toString();
            sdkiMalnutrisi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 13).toString();
            sdkiPemakaian = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 14).toString();
            sdkiPenurunan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 15).toString();
            sdkiTidak = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 16).toString();
            sdkiTransfer = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 17).toString();
            sdkiTrauma = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 18).toString();
            sdkiProses = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 19).toString();
            sdkiEfek = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 20).toString();
            sdkiKurang = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 21).toString();
            sdkiKulit = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 22).toString();
            sdkiMenggigil = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 23).toString();
            sdkiSuhu = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 24).toString();
            sdkiHipotiroid = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 25).toString();
            sdkiAnoreksia = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 26).toString();
            sdkiCedera = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 27).toString();
            sdkiPrema = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 28).toString();
            sdkiBeratRendah = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 29).toString();
            sdkiTenggelam = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 30).toString();

            slkiTermoregulasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 31).toString();
            slkiTermoregulasiNeo = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 32).toString();

            TketSelama.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 33).toString());

            slkiMenggigil = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 34).toString();
            slkiKulit = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 35).toString();
            slkiKejang = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 36).toString();
            slkiAkros = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 37).toString();
            slkiKonsumsi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 38).toString();
            slkiPilo = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 39).toString();
            slkiVasok = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 40).toString();
            slkiKutis = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 41).toString();
            slkiPucat = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 42).toString();
            slkiTakik = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 43).toString();
            slkiTakip = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 44).toString();
            slkiBradi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 45).toString();
            slkiDasar = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 46).toString();
            slkiHipoksia = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 47).toString();
            slkiSuhuTubuh = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 48).toString();
            slkiSuhuKulit = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 49).toString();
            slkiKadar = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 50).toString();
            slkiPengisian = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 51).toString();
            slkiVentilasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 52).toString();
            slkiTekanan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 53).toString();

            sikiMonitorSuhu = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 54).toString();
            sikiIdentifikasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 55).toString();
            sikiMonitorTanda = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 56).toString();
            sikiSediakan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 57).toString();
            sikiGanti = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 58).toString();
            sikiLakukanPasif = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 59).toString();
            sikiAktifEksternal = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 60).toString();
            sikiAktifInternal = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 61).toString();
            sikiAnjurkan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 62).toString();

            TnipPetugas.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 64).toString());
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
        if (sdkiKerusakan.equals("ya")) {
            chkSdkiKerusakan.setSelected(true);
        } else {
            chkSdkiKerusakan.setSelected(false);
        }

        if (sdkiKonsumsi.equals("ya")) {
            chkSdkiKonsumsi.setSelected(true);
        } else {
            chkSdkiKonsumsi.setSelected(false);
        }

        if (sdkiBeratEkstrim.equals("ya")) {
            chkSdkiBeratEkstrim.setSelected(true);
        } else {
            chkSdkiBeratEkstrim.setSelected(false);
        }

        if (sdkiKekurangan.equals("ya")) {
            chkSdkiKekurangan.setSelected(true);
        } else {
            chkSdkiKekurangan.setSelected(false);
        }

        if (sdkiTerpapar.equals("ya")) {
            chkSdkiTerpapar.setSelected(true);
        } else {
            chkSdkiTerpapar.setSelected(false);
        }

        if (sdkiMalnutrisi.equals("ya")) {
            chkSdkiMalnutrisi.setSelected(true);
        } else {
            chkSdkiMalnutrisi.setSelected(false);
        }

        if (sdkiPemakaian.equals("ya")) {
            chkSdkiPemakaian.setSelected(true);
        } else {
            chkSdkiPemakaian.setSelected(false);
        }

        if (sdkiPenurunan.equals("ya")) {
            chkSdkiPenurunan.setSelected(true);
        } else {
            chkSdkiPenurunan.setSelected(false);
        }

        if (sdkiTidak.equals("ya")) {
            chkSdkiTidak.setSelected(true);
        } else {
            chkSdkiTidak.setSelected(false);
        }

        if (sdkiTransfer.equals("ya")) {
            chkSdkiTransfer.setSelected(true);
        } else {
            chkSdkiTransfer.setSelected(false);
        }

        if (sdkiTrauma.equals("ya")) {
            chkSdkiTrauma.setSelected(true);
        } else {
            chkSdkiTrauma.setSelected(false);
        }

        if (sdkiProses.equals("ya")) {
            chkSdkiProses.setSelected(true);
        } else {
            chkSdkiProses.setSelected(false);
        }

        if (sdkiEfek.equals("ya")) {
            chkSdkiEfek.setSelected(true);
        } else {
            chkSdkiEfek.setSelected(false);
        }

        if (sdkiKurang.equals("ya")) {
            chkSdkiKurang.setSelected(true);
        } else {
            chkSdkiKurang.setSelected(false);
        }

        if (sdkiKulit.equals("ya")) {
            chkSdkiKulit.setSelected(true);
        } else {
            chkSdkiKulit.setSelected(false);
        }

        if (sdkiMenggigil.equals("ya")) {
            chkSdkiMenggigil.setSelected(true);
        } else {
            chkSdkiMenggigil.setSelected(false);
        }

        if (sdkiSuhu.equals("ya")) {
            chkSdkiSuhu.setSelected(true);
        } else {
            chkSdkiSuhu.setSelected(false);
        }

        if (sdkiHipotiroid.equals("ya")) {
            chkSdkiHipotiroid.setSelected(true);
        } else {
            chkSdkiHipotiroid.setSelected(false);
        }

        if (sdkiAnoreksia.equals("ya")) {
            chkSdkiAnoreksia.setSelected(true);
        } else {
            chkSdkiAnoreksia.setSelected(false);
        }

        if (sdkiCedera.equals("ya")) {
            chkSdkiCedera.setSelected(true);
        } else {
            chkSdkiCedera.setSelected(false);
        }

        if (sdkiPrema.equals("ya")) {
            chkSdkiPrema.setSelected(true);
        } else {
            chkSdkiPrema.setSelected(false);
        }

        if (sdkiBeratRendah.equals("ya")) {
            chkSdkiBeratRendah.setSelected(true);
        } else {
            chkSdkiBeratRendah.setSelected(false);
        }

        if (sdkiTenggelam.equals("ya")) {
            chkSdkiTenggelam.setSelected(true);
        } else {
            chkSdkiTenggelam.setSelected(false);
        }

        if (slkiTermoregulasi.equals("ya")) {
            chkSlkiTermoregulasi.setSelected(true);
        } else {
            chkSlkiTermoregulasi.setSelected(false);
        }

        if (slkiTermoregulasiNeo.equals("ya")) {
            chkSlkiTermoregulasiNeo.setSelected(true);
        } else {
            chkSlkiTermoregulasiNeo.setSelected(false);
        }

        if (slkiMenggigil.equals("ya")) {
            chkSlkiMenggigil.setSelected(true);
        } else {
            chkSlkiMenggigil.setSelected(false);
        }

        if (slkiKulit.equals("ya")) {
            chkSlkiKulit.setSelected(true);
        } else {
            chkSlkiKulit.setSelected(false);
        }

        if (slkiKejang.equals("ya")) {
            chkSlkiKejang.setSelected(true);
        } else {
            chkSlkiKejang.setSelected(false);
        }

        if (slkiAkros.equals("ya")) {
            chkSlkiAkros.setSelected(true);
        } else {
            chkSlkiAkros.setSelected(false);
        }

        if (slkiKonsumsi.equals("ya")) {
            chkSlkiKonsumsi.setSelected(true);
        } else {
            chkSlkiKonsumsi.setSelected(false);
        }

        if (slkiPilo.equals("ya")) {
            chkSlkiPilo.setSelected(true);
        } else {
            chkSlkiPilo.setSelected(false);
        }

        if (slkiVasok.equals("ya")) {
            chkSlkiVasok.setSelected(true);
        } else {
            chkSlkiVasok.setSelected(false);
        }

        if (slkiKutis.equals("ya")) {
            chkSlkiKutis.setSelected(true);
        } else {
            chkSlkiKutis.setSelected(false);
        }

        if (slkiPucat.equals("ya")) {
            chkSlkiPucat.setSelected(true);
        } else {
            chkSlkiPucat.setSelected(false);
        }

        if (slkiTakik.equals("ya")) {
            chkSlkiTakik.setSelected(true);
        } else {
            chkSlkiTakik.setSelected(false);
        }

        if (slkiTakip.equals("ya")) {
            chkSlkiTakip.setSelected(true);
        } else {
            chkSlkiTakip.setSelected(false);
        }

        if (slkiBradi.equals("ya")) {
            chkSlkiBradi.setSelected(true);
        } else {
            chkSlkiBradi.setSelected(false);
        }

        if (slkiDasar.equals("ya")) {
            chkSlkiDasar.setSelected(true);
        } else {
            chkSlkiDasar.setSelected(false);
        }

        if (slkiHipoksia.equals("ya")) {
            chkSlkiHipoksia.setSelected(true);
        } else {
            chkSlkiHipoksia.setSelected(false);
        }

        if (slkiSuhuTubuh.equals("ya")) {
            chkSlkiSuhuTubuh.setSelected(true);
        } else {
            chkSlkiSuhuTubuh.setSelected(false);
        }

        if (slkiSuhuKulit.equals("ya")) {
            chkSlkiSuhuKulit.setSelected(true);
        } else {
            chkSlkiSuhuKulit.setSelected(false);
        }

        if (slkiKadar.equals("ya")) {
            chkSlkiKadar.setSelected(true);
        } else {
            chkSlkiKadar.setSelected(false);
        }

        if (slkiPengisian.equals("ya")) {
            chkSlkiPengisian.setSelected(true);
        } else {
            chkSlkiPengisian.setSelected(false);
        }

        if (slkiVentilasi.equals("ya")) {
            chkSlkiVentilasi.setSelected(true);
        } else {
            chkSlkiVentilasi.setSelected(false);
        }

        if (slkiTekanan.equals("ya")) {
            chkSlkiTekanan.setSelected(true);
        } else {
            chkSlkiTekanan.setSelected(false);
        }

        if (sikiMonitorSuhu.equals("ya")) {
            chkSikiMonitorSuhu.setSelected(true);
        } else {
            chkSikiMonitorSuhu.setSelected(false);
        }

        if (sikiIdentifikasi.equals("ya")) {
            chkSikiIdentifikasi.setSelected(true);
        } else {
            chkSikiIdentifikasi.setSelected(false);
        }

        if (sikiMonitorTanda.equals("ya")) {
            chkSikiMonitorTanda.setSelected(true);
        } else {
            chkSikiMonitorTanda.setSelected(false);
        }

        if (sikiSediakan.equals("ya")) {
            chkSikiSediakan.setSelected(true);
        } else {
            chkSikiSediakan.setSelected(false);
        }

        if (sikiGanti.equals("ya")) {
            chkSikiGanti.setSelected(true);
        } else {
            chkSikiGanti.setSelected(false);
        }

        if (sikiLakukanPasif.equals("ya")) {
            chkSikiLakukanPasif.setSelected(true);
        } else {
            chkSikiLakukanPasif.setSelected(false);
        }

        if (sikiAktifEksternal.equals("ya")) {
            chkSikiAktifEksternal.setSelected(true);
        } else {
            chkSikiAktifEksternal.setSelected(false);
        }

        if (sikiAktifInternal.equals("ya")) {
            chkSikiAktifInternal.setSelected(true);
        } else {
            chkSikiAktifInternal.setSelected(false);
        }

        if (sikiAnjurkan.equals("ya")) {
            chkSikiAnjurkan.setSelected(true);
        } else {
            chkSikiAnjurkan.setSelected(false);
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
        if (chkSdkiKerusakan.isSelected() == true) {
            sdkiKerusakan = "ya";
        } else {
            sdkiKerusakan = "tidak";
        }

        if (chkSdkiKonsumsi.isSelected() == true) {
            sdkiKonsumsi = "ya";
        } else {
            sdkiKonsumsi = "tidak";
        }

        if (chkSdkiBeratEkstrim.isSelected() == true) {
            sdkiBeratEkstrim = "ya";
        } else {
            sdkiBeratEkstrim = "tidak";
        }

        if (chkSdkiKekurangan.isSelected() == true) {
            sdkiKekurangan = "ya";
        } else {
            sdkiKekurangan = "tidak";
        }

        if (chkSdkiTerpapar.isSelected() == true) {
            sdkiTerpapar = "ya";
        } else {
            sdkiTerpapar = "tidak";
        }

        if (chkSdkiMalnutrisi.isSelected() == true) {
            sdkiMalnutrisi = "ya";
        } else {
            sdkiMalnutrisi = "tidak";
        }

        if (chkSdkiPemakaian.isSelected() == true) {
            sdkiPemakaian = "ya";
        } else {
            sdkiPemakaian = "tidak";
        }

        if (chkSdkiPenurunan.isSelected() == true) {
            sdkiPenurunan = "ya";
        } else {
            sdkiPenurunan = "tidak";
        }

        if (chkSdkiTidak.isSelected() == true) {
            sdkiTidak = "ya";
        } else {
            sdkiTidak = "tidak";
        }

        if (chkSdkiTransfer.isSelected() == true) {
            sdkiTransfer = "ya";
        } else {
            sdkiTransfer = "tidak";
        }

        if (chkSdkiTrauma.isSelected() == true) {
            sdkiTrauma = "ya";
        } else {
            sdkiTrauma = "tidak";
        }

        if (chkSdkiProses.isSelected() == true) {
            sdkiProses = "ya";
        } else {
            sdkiProses = "tidak";
        }

        if (chkSdkiEfek.isSelected() == true) {
            sdkiEfek = "ya";
        } else {
            sdkiEfek = "tidak";
        }

        if (chkSdkiKurang.isSelected() == true) {
            sdkiKurang = "ya";
        } else {
            sdkiKurang = "tidak";
        }

        if (chkSdkiKulit.isSelected() == true) {
            sdkiKulit = "ya";
        } else {
            sdkiKulit = "tidak";
        }

        if (chkSdkiMenggigil.isSelected() == true) {
            sdkiMenggigil = "ya";
        } else {
            sdkiMenggigil = "tidak";
        }

        if (chkSdkiSuhu.isSelected() == true) {
            sdkiSuhu = "ya";
        } else {
            sdkiSuhu = "tidak";
        }

        if (chkSdkiHipotiroid.isSelected() == true) {
            sdkiHipotiroid = "ya";
        } else {
            sdkiHipotiroid = "tidak";
        }

        if (chkSdkiAnoreksia.isSelected() == true) {
            sdkiAnoreksia = "ya";
        } else {
            sdkiAnoreksia = "tidak";
        }

        if (chkSdkiCedera.isSelected() == true) {
            sdkiCedera = "ya";
        } else {
            sdkiCedera = "tidak";
        }

        if (chkSdkiPrema.isSelected() == true) {
            sdkiPrema = "ya";
        } else {
            sdkiPrema = "tidak";
        }

        if (chkSdkiBeratRendah.isSelected() == true) {
            sdkiBeratRendah = "ya";
        } else {
            sdkiBeratRendah = "tidak";
        }

        if (chkSdkiTenggelam.isSelected() == true) {
            sdkiTenggelam = "ya";
        } else {
            sdkiTenggelam = "tidak";
        }

        if (chkSlkiTermoregulasi.isSelected() == true) {
            slkiTermoregulasi = "ya";
        } else {
            slkiTermoregulasi = "tidak";
        }

        if (chkSlkiTermoregulasiNeo.isSelected() == true) {
            slkiTermoregulasiNeo = "ya";
        } else {
            slkiTermoregulasiNeo = "tidak";
        }

        if (chkSlkiMenggigil.isSelected() == true) {
            slkiMenggigil = "ya";
        } else {
            slkiMenggigil = "tidak";
        }

        if (chkSlkiKulit.isSelected() == true) {
            slkiKulit = "ya";
        } else {
            slkiKulit = "tidak";
        }

        if (chkSlkiKejang.isSelected() == true) {
            slkiKejang = "ya";
        } else {
            slkiKejang = "tidak";
        }

        if (chkSlkiAkros.isSelected() == true) {
            slkiAkros = "ya";
        } else {
            slkiAkros = "tidak";
        }

        if (chkSlkiKonsumsi.isSelected() == true) {
            slkiKonsumsi = "ya";
        } else {
            slkiKonsumsi = "tidak";
        }

        if (chkSlkiPilo.isSelected() == true) {
            slkiPilo = "ya";
        } else {
            slkiPilo = "tidak";
        }

        if (chkSlkiVasok.isSelected() == true) {
            slkiVasok = "ya";
        } else {
            slkiVasok = "tidak";
        }

        if (chkSlkiKutis.isSelected() == true) {
            slkiKutis = "ya";
        } else {
            slkiKutis = "tidak";
        }

        if (chkSlkiPucat.isSelected() == true) {
            slkiPucat = "ya";
        } else {
            slkiPucat = "tidak";
        }

        if (chkSlkiTakik.isSelected() == true) {
            slkiTakik = "ya";
        } else {
            slkiTakik = "tidak";
        }

        if (chkSlkiTakip.isSelected() == true) {
            slkiTakip = "ya";
        } else {
            slkiTakip = "tidak";
        }

        if (chkSlkiBradi.isSelected() == true) {
            slkiBradi = "ya";
        } else {
            slkiBradi = "tidak";
        }

        if (chkSlkiDasar.isSelected() == true) {
            slkiDasar = "ya";
        } else {
            slkiDasar = "tidak";
        }

        if (chkSlkiHipoksia.isSelected() == true) {
            slkiHipoksia = "ya";
        } else {
            slkiHipoksia = "tidak";
        }

        if (chkSlkiSuhuTubuh.isSelected() == true) {
            slkiSuhuTubuh = "ya";
        } else {
            slkiSuhuTubuh = "tidak";
        }

        if (chkSlkiSuhuKulit.isSelected() == true) {
            slkiSuhuKulit = "ya";
        } else {
            slkiSuhuKulit = "tidak";
        }

        if (chkSlkiKadar.isSelected() == true) {
            slkiKadar = "ya";
        } else {
            slkiKadar = "tidak";
        }

        if (chkSlkiPengisian.isSelected() == true) {
            slkiPengisian = "ya";
        } else {
            slkiPengisian = "tidak";
        }

        if (chkSlkiVentilasi.isSelected() == true) {
            slkiVentilasi = "ya";
        } else {
            slkiVentilasi = "tidak";
        }

        if (chkSlkiTekanan.isSelected() == true) {
            slkiTekanan = "ya";
        } else {
            slkiTekanan = "tidak";
        }

        if (chkSikiMonitorSuhu.isSelected() == true) {
            sikiMonitorSuhu = "ya";
        } else {
            sikiMonitorSuhu = "tidak";
        }

        if (chkSikiIdentifikasi.isSelected() == true) {
            sikiIdentifikasi = "ya";
        } else {
            sikiIdentifikasi = "tidak";
        }

        if (chkSikiMonitorTanda.isSelected() == true) {
            sikiMonitorTanda = "ya";
        } else {
            sikiMonitorTanda = "tidak";
        }

        if (chkSikiSediakan.isSelected() == true) {
            sikiSediakan = "ya";
        } else {
            sikiSediakan = "tidak";
        }

        if (chkSikiGanti.isSelected() == true) {
            sikiGanti = "ya";
        } else {
            sikiGanti = "tidak";
        }

        if (chkSikiLakukanPasif.isSelected() == true) {
            sikiLakukanPasif = "ya";
        } else {
            sikiLakukanPasif = "tidak";
        }

        if (chkSikiAktifEksternal.isSelected() == true) {
            sikiAktifEksternal = "ya";
        } else {
            sikiAktifEksternal = "tidak";
        }

        if (chkSikiAktifInternal.isSelected() == true) {
            sikiAktifInternal = "ya";
        } else {
            sikiAktifInternal = "tidak";
        }

        if (chkSikiAnjurkan.isSelected() == true) {
            sikiAnjurkan = "ya";
        } else {
            sikiAnjurkan = "tidak";
        }
    }
    
    private void emptVariabel() {
        sdkiKerusakan = "";
        sdkiKonsumsi = "";
        sdkiBeratEkstrim = "";
        sdkiKekurangan = "";
        sdkiTerpapar = "";
        sdkiMalnutrisi = "";
        sdkiPemakaian = "";
        sdkiPenurunan = "";
        sdkiTidak = "";
        sdkiTransfer = "";
        sdkiTrauma = "";
        sdkiProses = "";
        sdkiEfek = "";
        sdkiKurang = "";
        sdkiKulit = "";
        sdkiMenggigil = "";
        sdkiSuhu = "";
        sdkiHipotiroid = "";
        sdkiAnoreksia = "";
        sdkiCedera = "";
        sdkiPrema = "";
        sdkiBeratRendah = "";
        sdkiTenggelam = "";
        slkiTermoregulasi = "";
        slkiTermoregulasiNeo = "";
        slkiMenggigil = "";
        slkiKulit = "";
        slkiKejang = "";
        slkiAkros = "";
        slkiKonsumsi = "";
        slkiPilo = "";
        slkiVasok = "";
        slkiKutis = "";
        slkiPucat = "";
        slkiTakik = "";
        slkiTakip = "";
        slkiBradi = "";
        slkiDasar = "";
        slkiHipoksia = "";
        slkiSuhuTubuh = "";
        slkiSuhuKulit = "";
        slkiKadar = "";
        slkiPengisian = "";
        slkiVentilasi = "";
        slkiTekanan = "";
        sikiMonitorSuhu = "";
        sikiIdentifikasi = "";
        sikiMonitorTanda = "";
        sikiSediakan = "";
        sikiGanti = "";
        sikiLakukanPasif = "";
        sikiAktifEksternal = "";
        sikiAktifInternal = "";
        sikiAnjurkan = "";
    }
    
    public void awalData() {
        tampil();
    }
}
