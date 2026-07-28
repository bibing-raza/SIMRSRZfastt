package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
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
public class RMMasalahKeperawatanBersihanJlnNafas extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String sdkiSpasme = "", sdkiHipersekresi = "", sdkiDisfungsi = "", sdkiBenda = "", sdkiAdanya = "", sdkiSekresi = "", sdkiHiperplasia = "", sdkiProses = "",
            sdkiRespon = "", sdkiEfek = "", sdkiMerokokAktif = "", sdkiMerokokPasif = "", sdkiTerpajan = "", sdkiBatuk = "", sdkiTidak = "", sdkiSputum = "", sdkiMengi = "",
            sdkiMekonium = "", sdkiDispnea = "", sdkiSulit = "", sdkiOrtopnea = "", sdkiGelisah = "", sdkiSianosis = "", sdkiBunyi = "", sdkiPola = "", sdkiGullian = "",
            sdkiSclerosis = "", sdkiMyasthenia = "", sdkiProsedur = "", sdkiDepresi = "", sdkiCedera = "", sdkiStroke = "", sdkiKuadri = "", sdkiSindrom = "", sdkiInfeksi = "",
            slkiBersihan = "", slkiBatuk = "", slkiProduksi = "", slkiWheezing = "", slkiMengi = "", slkiDispnea = "", slkiSulit = "", slkiGelisah = "", slkiFrekuensi = "",
            slkiPola = "", sikiMonitorPola = "", sikiMonitorBunyi = "", sikiMonitorSputum = "", sikiPertahankan = "", sikiPosisikan = "", sikiBerikanMinum = "", sikiLakukanFisio = "",
            sikiLakukanPenghisapan = "", sikiLakukanHiper = "", sikiKeluarkan = "", sikiBerikanOksigen = "", sikiAnjurkan = "", sikiAjarkan = "", sikiKolaborasi = "", sttsRawat = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMMasalahKeperawatanBersihanJlnNafas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "waktu_simpan", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Simpan", "Nama Petugas",
            "sdki_spasme", "sdki_hipersekresi", "sdki_disfungsi", "sdki_benda", "sdki_adanya", "sdki_sekresi", "sdki_hiperplasia", "sdki_proses", "sdki_respon",
            "sdki_efek", "sdki_merokok_aktif", "sdki_merokok_pasif", "sdki_terpajan", "sdki_batuk", "sdki_tidak", "sdki_sputum", "sdki_mengi", "sdki_mekonium", "sdki_dispnea",
            "sdki_sulit", "sdki_ortopnea", "sdki_gelisah", "sdki_sianosis", "sdki_bunyi", "sdki_pola", "sdki_gullian", "sdki_sclerosis", "sdki_myasthenia", "sdki_prosedur",
            "sdki_depresi", "sdki_cedera", "sdki_stroke", "sdki_kuadri", "sdki_sindrom", "sdki_infeksi", "slki_bersihan", "ket_selama", "slki_batuk", "slki_produksi", "slki_wheezing",
            "slki_mengi", "slki_dispnea", "slki_sulit", "slki_gelisah", "slki_frekuensi", "slki_pola", "siki_monitor_pola", "siki_monitor_bunyi", "siki_monitor_sputum",
            "siki_pertahankan", "siki_posisikan", "siki_berikan_minum", "siki_lakukan_fisio", "siki_lakukan_penghisapan", "siki_lakukan_hiper", "siki_keluarkan", "siki_berikan_oksigen",
            "siki_anjurkan", "siki_ajarkan", "siki_kolaborasi", "ket_kolaborasi", "status_rawat", "nip_petugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbMasalah.setModel(tabMode);
        tbMasalah.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMasalah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 71; i++) {
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
            }
        }
        tbMasalah.setDefaultRenderer(Object.class, new WarnaTable());

        TketSelama.setDocument(new batasInput((int) 20).getKata(TketSelama));
        TketKolaborasi.setDocument(new batasInput((int) 100).getKata(TketKolaborasi));
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
                if (akses.getform().equals("RMMasalahKeperawatanBersihanJlnNafas")) {
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
        chkSdkiSpasme = new widget.CekBox();
        chkSdkiHipersekresi = new widget.CekBox();
        chkSdkiDisfungsi = new widget.CekBox();
        chkSdkiBenda = new widget.CekBox();
        chkSdkiAdanya = new widget.CekBox();
        chkSdkiSulit = new widget.CekBox();
        chkSdkiGullian = new widget.CekBox();
        chkSdkiOrtopnea = new widget.CekBox();
        chkSdkiSclerosis = new widget.CekBox();
        jLabel74 = new widget.Label();
        jLabel75 = new widget.Label();
        jLabel76 = new widget.Label();
        jLabel78 = new widget.Label();
        jLabel79 = new widget.Label();
        TketSelama = new widget.TextBox();
        chkSdkiGelisah = new widget.CekBox();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        chkSdkiMyasthenia = new widget.CekBox();
        chkSikiMonitorPola = new widget.CekBox();
        chkSikiMonitorSputum = new widget.CekBox();
        jLabel83 = new widget.Label();
        chkSikiPertahankan = new widget.CekBox();
        chkSikiPosisikan = new widget.CekBox();
        chkSikiBerikanMinum = new widget.CekBox();
        jLabel84 = new widget.Label();
        chkSikiAnjurkan = new widget.CekBox();
        jLabel91 = new widget.Label();
        jLabel92 = new widget.Label();
        chkSdkiBatuk = new widget.CekBox();
        chkSdkiTidak = new widget.CekBox();
        chkSdkiSputum = new widget.CekBox();
        chkSdkiMengi = new widget.CekBox();
        chkSdkiMekonium = new widget.CekBox();
        chkSdkiDispnea = new widget.CekBox();
        chkSdkiPola = new widget.CekBox();
        chkSdkiSianosis = new widget.CekBox();
        chkSdkiBunyi = new widget.CekBox();
        chkSdkiProsedur = new widget.CekBox();
        chkSlkiBersihan = new widget.CekBox();
        chkSlkiBatuk = new widget.CekBox();
        chkSlkiProduksi = new widget.CekBox();
        chkSlkiWheezing = new widget.CekBox();
        chkSlkiMengi = new widget.CekBox();
        chkSlkiDispnea = new widget.CekBox();
        chkSlkiSulit = new widget.CekBox();
        chkSikiAjarkan = new widget.CekBox();
        jLabel85 = new widget.Label();
        chkSikiKolaborasi = new widget.CekBox();
        chkSdkiEfek = new widget.CekBox();
        chkSdkiRespon = new widget.CekBox();
        chkSdkiProses = new widget.CekBox();
        chkSdkiHiperplasia = new widget.CekBox();
        chkSdkiSekresi = new widget.CekBox();
        jLabel80 = new widget.Label();
        chkSdkiMerokokAktif = new widget.CekBox();
        chkSdkiMerokokPasif = new widget.CekBox();
        chkSdkiTerpajan = new widget.CekBox();
        jLabel86 = new widget.Label();
        chkSdkiDepresi = new widget.CekBox();
        chkSdkiInfeksi = new widget.CekBox();
        chkSdkiSindrom = new widget.CekBox();
        chkSdkiKuadri = new widget.CekBox();
        chkSdkiStroke = new widget.CekBox();
        chkSdkiCedera = new widget.CekBox();
        chkSlkiGelisah = new widget.CekBox();
        chkSlkiFrekuensi = new widget.CekBox();
        chkSlkiPola = new widget.CekBox();
        chkSikiMonitorBunyi = new widget.CekBox();
        chkSikiLakukanFisio = new widget.CekBox();
        chkSikiLakukanPenghisapan = new widget.CekBox();
        chkSikiLakukanHiper = new widget.CekBox();
        chkSikiKeluarkan = new widget.CekBox();
        chkSikiBerikanOksigen = new widget.CekBox();
        TketKolaborasi = new widget.TextBox();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Masalah Keperawatan Bersihan Jalan Nafas Tidak Efektif ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(760, 1306));
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
        label20.setBounds(0, 1264, 140, 23);

        TnipPetugas.setEditable(false);
        TnipPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnipPetugas.setName("TnipPetugas"); // NOI18N
        TnipPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipPetugas);
        TnipPetugas.setBounds(145, 1264, 150, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        TnmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(300, 1264, 360, 23);

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
        BtnPetugas.setBounds(665, 1264, 28, 23);

        chkSdkiSpasme.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiSpasme.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiSpasme.setText("Spasme jalan napas");
        chkSdkiSpasme.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiSpasme.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiSpasme.setName("chkSdkiSpasme"); // NOI18N
        chkSdkiSpasme.setOpaque(false);
        chkSdkiSpasme.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiSpasme);
        chkSdkiSpasme.setBounds(145, 122, 140, 23);

        chkSdkiHipersekresi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiHipersekresi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiHipersekresi.setText("Hipersekresi jalan napas");
        chkSdkiHipersekresi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiHipersekresi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiHipersekresi.setName("chkSdkiHipersekresi"); // NOI18N
        chkSdkiHipersekresi.setOpaque(false);
        chkSdkiHipersekresi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiHipersekresi);
        chkSdkiHipersekresi.setBounds(145, 150, 150, 23);

        chkSdkiDisfungsi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiDisfungsi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiDisfungsi.setText("Disfungsi neuromuskuler");
        chkSdkiDisfungsi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiDisfungsi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiDisfungsi.setName("chkSdkiDisfungsi"); // NOI18N
        chkSdkiDisfungsi.setOpaque(false);
        chkSdkiDisfungsi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiDisfungsi);
        chkSdkiDisfungsi.setBounds(145, 178, 150, 23);

        chkSdkiBenda.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiBenda.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiBenda.setText("Benda asing dalam jalan napas");
        chkSdkiBenda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiBenda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiBenda.setName("chkSdkiBenda"); // NOI18N
        chkSdkiBenda.setOpaque(false);
        chkSdkiBenda.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiBenda);
        chkSdkiBenda.setBounds(145, 206, 180, 23);

        chkSdkiAdanya.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiAdanya.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiAdanya.setText("Adanya jalan napas buatan");
        chkSdkiAdanya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiAdanya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiAdanya.setName("chkSdkiAdanya"); // NOI18N
        chkSdkiAdanya.setOpaque(false);
        chkSdkiAdanya.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiAdanya);
        chkSdkiAdanya.setBounds(145, 234, 170, 23);

        chkSdkiSulit.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiSulit.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiSulit.setText("Sulit bicara");
        chkSdkiSulit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiSulit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiSulit.setName("chkSdkiSulit"); // NOI18N
        chkSdkiSulit.setOpaque(false);
        chkSdkiSulit.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSdkiSulit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSdkiSulitActionPerformed(evt);
            }
        });
        FormInput.add(chkSdkiSulit);
        chkSdkiSulit.setBounds(145, 458, 80, 23);

        chkSdkiGullian.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiGullian.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiGullian.setText("Gullian Bare Syndrome");
        chkSdkiGullian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiGullian.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiGullian.setName("chkSdkiGullian"); // NOI18N
        chkSdkiGullian.setOpaque(false);
        chkSdkiGullian.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiGullian);
        chkSdkiGullian.setBounds(145, 514, 140, 23);

        chkSdkiOrtopnea.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiOrtopnea.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiOrtopnea.setText("Ortopnea");
        chkSdkiOrtopnea.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiOrtopnea.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiOrtopnea.setName("chkSdkiOrtopnea"); // NOI18N
        chkSdkiOrtopnea.setOpaque(false);
        chkSdkiOrtopnea.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiOrtopnea);
        chkSdkiOrtopnea.setBounds(240, 430, 80, 23);

        chkSdkiSclerosis.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiSclerosis.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiSclerosis.setText("Sclerosis multiple");
        chkSdkiSclerosis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiSclerosis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiSclerosis.setName("chkSdkiSclerosis"); // NOI18N
        chkSdkiSclerosis.setOpaque(false);
        chkSdkiSclerosis.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiSclerosis);
        chkSdkiSclerosis.setBounds(145, 542, 110, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Diagnosis Keperawatan SDKI : Bersihan Jalan Napas Tidak Efektif (D.0001)");
        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 66, 460, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Berhubungan Dengan : Fisiologis");
        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 94, 230, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("DO/DS (Gejala dan tanda Mayor)");
        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(145, 318, 210, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("Kondisi Klinis Terkait :");
        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(145, 486, 140, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("(Rencana Keperawatan) Tujuan dan Kriteria Hasil SLKI :");
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 654, 370, 23);

        TketSelama.setForeground(new java.awt.Color(0, 0, 0));
        TketSelama.setName("TketSelama"); // NOI18N
        TketSelama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketSelamaKeyPressed(evt);
            }
        });
        FormInput.add(TketSelama);
        TketSelama.setBounds(380, 710, 80, 23);

        chkSdkiGelisah.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiGelisah.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiGelisah.setText("Gelisah");
        chkSdkiGelisah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiGelisah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiGelisah.setName("chkSdkiGelisah"); // NOI18N
        chkSdkiGelisah.setOpaque(false);
        chkSdkiGelisah.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiGelisah);
        chkSdkiGelisah.setBounds(240, 458, 70, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("(Rencana Keperawatan) Intervensi Keperawatan SIKI : Manajemen Jalan Napas (I.01011)");
        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 822, 550, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("Tindakan Observasi :");
        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(145, 850, 140, 23);

        chkSdkiMyasthenia.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMyasthenia.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMyasthenia.setText("Myasthenia Gravis");
        chkSdkiMyasthenia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMyasthenia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMyasthenia.setName("chkSdkiMyasthenia"); // NOI18N
        chkSdkiMyasthenia.setOpaque(false);
        chkSdkiMyasthenia.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMyasthenia);
        chkSdkiMyasthenia.setBounds(145, 570, 120, 23);

        chkSikiMonitorPola.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiMonitorPola.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiMonitorPola.setText("Monitor pola napas (frekuensi, kedalaman,usaha napas)");
        chkSikiMonitorPola.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiMonitorPola.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiMonitorPola.setName("chkSikiMonitorPola"); // NOI18N
        chkSikiMonitorPola.setOpaque(false);
        chkSikiMonitorPola.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiMonitorPola);
        chkSikiMonitorPola.setBounds(145, 878, 310, 23);

        chkSikiMonitorSputum.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiMonitorSputum.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiMonitorSputum.setText("Monitor sputum (jumlah, warna, aroma)");
        chkSikiMonitorSputum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiMonitorSputum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiMonitorSputum.setName("chkSikiMonitorSputum"); // NOI18N
        chkSikiMonitorSputum.setOpaque(false);
        chkSikiMonitorSputum.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiMonitorSputum);
        chkSikiMonitorSputum.setBounds(145, 934, 220, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("Terapeutik :");
        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(145, 962, 90, 23);

        chkSikiPertahankan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiPertahankan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiPertahankan.setText("<html>Pertahankan kepatenan jalan napas dengan <i>head tilt</i> dan <i>chin lift</i> (<i>jaw-thrust</i> jika curiga trauma servical)</html>");
        chkSikiPertahankan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiPertahankan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiPertahankan.setName("chkSikiPertahankan"); // NOI18N
        chkSikiPertahankan.setOpaque(false);
        chkSikiPertahankan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiPertahankan);
        chkSikiPertahankan.setBounds(145, 990, 560, 23);

        chkSikiPosisikan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiPosisikan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiPosisikan.setText("Posisikan semi Fowler-atau fowler");
        chkSikiPosisikan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiPosisikan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiPosisikan.setName("chkSikiPosisikan"); // NOI18N
        chkSikiPosisikan.setOpaque(false);
        chkSikiPosisikan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiPosisikan);
        chkSikiPosisikan.setBounds(145, 1018, 210, 23);

        chkSikiBerikanMinum.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiBerikanMinum.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiBerikanMinum.setText("Berikan minum hangat");
        chkSikiBerikanMinum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiBerikanMinum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiBerikanMinum.setName("chkSikiBerikanMinum"); // NOI18N
        chkSikiBerikanMinum.setOpaque(false);
        chkSikiBerikanMinum.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiBerikanMinum);
        chkSikiBerikanMinum.setBounds(145, 1046, 140, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("Edukasi :");
        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(145, 1124, 90, 23);

        chkSikiAnjurkan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAnjurkan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAnjurkan.setText("Anjurkan asupan cairan 200 ml/hari, jika tidak ada kontraindikasi");
        chkSikiAnjurkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAnjurkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAnjurkan.setName("chkSikiAnjurkan"); // NOI18N
        chkSikiAnjurkan.setOpaque(false);
        chkSikiAnjurkan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAnjurkan);
        chkSikiAnjurkan.setBounds(145, 1152, 340, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel91.setText("Setelah dilakukan tindakan keperawatan selama");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(145, 710, 235, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel92.setText("Bersihan jalan napas pasien menjadi efektif dengan kriteria hasil :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(465, 710, 320, 23);

        chkSdkiBatuk.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiBatuk.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiBatuk.setText("Batuk tidak efektif");
        chkSdkiBatuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiBatuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiBatuk.setName("chkSdkiBatuk"); // NOI18N
        chkSdkiBatuk.setOpaque(false);
        chkSdkiBatuk.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiBatuk);
        chkSdkiBatuk.setBounds(145, 346, 120, 23);

        chkSdkiTidak.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTidak.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTidak.setText("Tidak mampu batuk");
        chkSdkiTidak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTidak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTidak.setName("chkSdkiTidak"); // NOI18N
        chkSdkiTidak.setOpaque(false);
        chkSdkiTidak.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTidak);
        chkSdkiTidak.setBounds(145, 374, 120, 23);

        chkSdkiSputum.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiSputum.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiSputum.setText("Sputum berlebih");
        chkSdkiSputum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiSputum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiSputum.setName("chkSdkiSputum"); // NOI18N
        chkSdkiSputum.setOpaque(false);
        chkSdkiSputum.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiSputum);
        chkSdkiSputum.setBounds(280, 346, 110, 23);

        chkSdkiMengi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMengi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMengi.setText("Mengi, wheezing dan/atau ronki kering");
        chkSdkiMengi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMengi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMengi.setName("chkSdkiMengi"); // NOI18N
        chkSdkiMengi.setOpaque(false);
        chkSdkiMengi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMengi);
        chkSdkiMengi.setBounds(280, 374, 220, 23);

        chkSdkiMekonium.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMekonium.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMekonium.setText("Mekonium di jalan napas (pada neonatus)");
        chkSdkiMekonium.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMekonium.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMekonium.setName("chkSdkiMekonium"); // NOI18N
        chkSdkiMekonium.setOpaque(false);
        chkSdkiMekonium.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMekonium);
        chkSdkiMekonium.setBounds(440, 346, 230, 23);

        chkSdkiDispnea.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiDispnea.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiDispnea.setText("Dispnea");
        chkSdkiDispnea.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiDispnea.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiDispnea.setName("chkSdkiDispnea"); // NOI18N
        chkSdkiDispnea.setOpaque(false);
        chkSdkiDispnea.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiDispnea);
        chkSdkiDispnea.setBounds(145, 430, 80, 23);

        chkSdkiPola.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPola.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPola.setText("Pola napas berubah");
        chkSdkiPola.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPola.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPola.setName("chkSdkiPola"); // NOI18N
        chkSdkiPola.setOpaque(false);
        chkSdkiPola.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPola);
        chkSdkiPola.setBounds(460, 430, 130, 23);

        chkSdkiSianosis.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiSianosis.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiSianosis.setText("Sianosis");
        chkSdkiSianosis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiSianosis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiSianosis.setName("chkSdkiSianosis"); // NOI18N
        chkSdkiSianosis.setOpaque(false);
        chkSdkiSianosis.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiSianosis);
        chkSdkiSianosis.setBounds(330, 430, 70, 23);

        chkSdkiBunyi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiBunyi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiBunyi.setText("Bunyi napas berubah");
        chkSdkiBunyi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiBunyi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiBunyi.setName("chkSdkiBunyi"); // NOI18N
        chkSdkiBunyi.setOpaque(false);
        chkSdkiBunyi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiBunyi);
        chkSdkiBunyi.setBounds(330, 458, 140, 23);

        chkSdkiProsedur.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiProsedur.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiProsedur.setText("Prosedur diagnostic (mis. Bronkoskopi)");
        chkSdkiProsedur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiProsedur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiProsedur.setName("chkSdkiProsedur"); // NOI18N
        chkSdkiProsedur.setOpaque(false);
        chkSdkiProsedur.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiProsedur);
        chkSdkiProsedur.setBounds(145, 598, 220, 23);

        chkSlkiBersihan.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiBersihan.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiBersihan.setText("Bersihan jalan napas (L.01001)");
        chkSlkiBersihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiBersihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiBersihan.setName("chkSlkiBersihan"); // NOI18N
        chkSlkiBersihan.setOpaque(false);
        chkSlkiBersihan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiBersihan);
        chkSlkiBersihan.setBounds(145, 682, 190, 23);

        chkSlkiBatuk.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiBatuk.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiBatuk.setText("Batuk efektif meningkat");
        chkSlkiBatuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiBatuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiBatuk.setName("chkSlkiBatuk"); // NOI18N
        chkSlkiBatuk.setOpaque(false);
        chkSlkiBatuk.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiBatuk);
        chkSlkiBatuk.setBounds(145, 738, 150, 23);

        chkSlkiProduksi.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiProduksi.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiProduksi.setText("Produksi sputum menurun");
        chkSlkiProduksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiProduksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiProduksi.setName("chkSlkiProduksi"); // NOI18N
        chkSlkiProduksi.setOpaque(false);
        chkSlkiProduksi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiProduksi);
        chkSlkiProduksi.setBounds(145, 766, 155, 23);

        chkSlkiWheezing.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiWheezing.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiWheezing.setText("Wheezing menurun");
        chkSlkiWheezing.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiWheezing.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiWheezing.setName("chkSlkiWheezing"); // NOI18N
        chkSlkiWheezing.setOpaque(false);
        chkSlkiWheezing.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiWheezing);
        chkSlkiWheezing.setBounds(145, 794, 130, 23);

        chkSlkiMengi.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiMengi.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiMengi.setText("Mengi menurun");
        chkSlkiMengi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiMengi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiMengi.setName("chkSlkiMengi"); // NOI18N
        chkSlkiMengi.setOpaque(false);
        chkSlkiMengi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiMengi);
        chkSlkiMengi.setBounds(310, 738, 120, 23);

        chkSlkiDispnea.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiDispnea.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiDispnea.setText("Dispnea menurun");
        chkSlkiDispnea.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiDispnea.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiDispnea.setName("chkSlkiDispnea"); // NOI18N
        chkSlkiDispnea.setOpaque(false);
        chkSlkiDispnea.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiDispnea);
        chkSlkiDispnea.setBounds(310, 766, 130, 23);

        chkSlkiSulit.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiSulit.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiSulit.setText("Sulit bicara menurun");
        chkSlkiSulit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiSulit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiSulit.setName("chkSlkiSulit"); // NOI18N
        chkSlkiSulit.setOpaque(false);
        chkSlkiSulit.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiSulit);
        chkSlkiSulit.setBounds(310, 794, 130, 23);

        chkSikiAjarkan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAjarkan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAjarkan.setText("Ajarkan teknik batuk efektif");
        chkSikiAjarkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAjarkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAjarkan.setName("chkSikiAjarkan"); // NOI18N
        chkSikiAjarkan.setOpaque(false);
        chkSikiAjarkan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAjarkan);
        chkSikiAjarkan.setBounds(500, 1152, 180, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel85.setText("Kolaborasi :");
        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(145, 1180, 90, 23);

        chkSikiKolaborasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiKolaborasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiKolaborasi.setText("Kolaborasi pemberian bronkodilator, ekspetoran, mukolitik, jika perlu :");
        chkSikiKolaborasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiKolaborasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiKolaborasi.setName("chkSikiKolaborasi"); // NOI18N
        chkSikiKolaborasi.setOpaque(false);
        chkSikiKolaborasi.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSikiKolaborasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSikiKolaborasiActionPerformed(evt);
            }
        });
        FormInput.add(chkSikiKolaborasi);
        chkSikiKolaborasi.setBounds(145, 1208, 360, 23);

        chkSdkiEfek.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiEfek.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiEfek.setText("Efek agen farmakologis (mis.anestesi)");
        chkSdkiEfek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiEfek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiEfek.setName("chkSdkiEfek"); // NOI18N
        chkSdkiEfek.setOpaque(false);
        chkSdkiEfek.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiEfek);
        chkSdkiEfek.setBounds(340, 234, 220, 23);

        chkSdkiRespon.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiRespon.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiRespon.setText("Respon alergi");
        chkSdkiRespon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiRespon.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiRespon.setName("chkSdkiRespon"); // NOI18N
        chkSdkiRespon.setOpaque(false);
        chkSdkiRespon.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiRespon);
        chkSdkiRespon.setBounds(340, 206, 100, 23);

        chkSdkiProses.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiProses.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiProses.setText("Proses infeks");
        chkSdkiProses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiProses.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiProses.setName("chkSdkiProses"); // NOI18N
        chkSdkiProses.setOpaque(false);
        chkSdkiProses.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiProses);
        chkSdkiProses.setBounds(340, 178, 100, 23);

        chkSdkiHiperplasia.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiHiperplasia.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiHiperplasia.setText("Hiperplasia dinding jalan napas");
        chkSdkiHiperplasia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiHiperplasia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiHiperplasia.setName("chkSdkiHiperplasia"); // NOI18N
        chkSdkiHiperplasia.setOpaque(false);
        chkSdkiHiperplasia.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiHiperplasia);
        chkSdkiHiperplasia.setBounds(340, 150, 180, 23);

        chkSdkiSekresi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiSekresi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiSekresi.setText("Sekresi yang tertahan");
        chkSdkiSekresi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiSekresi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiSekresi.setName("chkSdkiSekresi"); // NOI18N
        chkSdkiSekresi.setOpaque(false);
        chkSdkiSekresi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiSekresi);
        chkSdkiSekresi.setBounds(340, 122, 140, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Berhubungan Dengan : Situasional");
        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(0, 262, 230, 23);

        chkSdkiMerokokAktif.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMerokokAktif.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMerokokAktif.setText("Merokok aktif");
        chkSdkiMerokokAktif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMerokokAktif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMerokokAktif.setName("chkSdkiMerokokAktif"); // NOI18N
        chkSdkiMerokokAktif.setOpaque(false);
        chkSdkiMerokokAktif.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMerokokAktif);
        chkSdkiMerokokAktif.setBounds(145, 290, 95, 23);

        chkSdkiMerokokPasif.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMerokokPasif.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMerokokPasif.setText("Merokok pasif");
        chkSdkiMerokokPasif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMerokokPasif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMerokokPasif.setName("chkSdkiMerokokPasif"); // NOI18N
        chkSdkiMerokokPasif.setOpaque(false);
        chkSdkiMerokokPasif.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMerokokPasif);
        chkSdkiMerokokPasif.setBounds(250, 290, 100, 23);

        chkSdkiTerpajan.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTerpajan.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTerpajan.setText("Terpajan polutan");
        chkSdkiTerpajan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTerpajan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTerpajan.setName("chkSdkiTerpajan"); // NOI18N
        chkSdkiTerpajan.setOpaque(false);
        chkSdkiTerpajan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTerpajan);
        chkSdkiTerpajan.setBounds(360, 290, 120, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel86.setText("DO/DS (Gejala dan tanda Minor)");
        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(145, 402, 210, 23);

        chkSdkiDepresi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiDepresi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiDepresi.setText("Depresi system saraf pusat");
        chkSdkiDepresi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiDepresi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiDepresi.setName("chkSdkiDepresi"); // NOI18N
        chkSdkiDepresi.setOpaque(false);
        chkSdkiDepresi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiDepresi);
        chkSdkiDepresi.setBounds(145, 626, 170, 23);

        chkSdkiInfeksi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiInfeksi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiInfeksi.setText("Infeksi saluran nafas");
        chkSdkiInfeksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiInfeksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiInfeksi.setName("chkSdkiInfeksi"); // NOI18N
        chkSdkiInfeksi.setOpaque(false);
        chkSdkiInfeksi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiInfeksi);
        chkSdkiInfeksi.setBounds(370, 626, 140, 23);

        chkSdkiSindrom.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiSindrom.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiSindrom.setText("Sindrom aspirasi meconium");
        chkSdkiSindrom.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiSindrom.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiSindrom.setName("chkSdkiSindrom"); // NOI18N
        chkSdkiSindrom.setOpaque(false);
        chkSdkiSindrom.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiSindrom);
        chkSdkiSindrom.setBounds(370, 598, 160, 23);

        chkSdkiKuadri.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKuadri.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKuadri.setText("Kuadriplegia");
        chkSdkiKuadri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKuadri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKuadri.setName("chkSdkiKuadri"); // NOI18N
        chkSdkiKuadri.setOpaque(false);
        chkSdkiKuadri.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKuadri);
        chkSdkiKuadri.setBounds(370, 570, 90, 23);

        chkSdkiStroke.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiStroke.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiStroke.setText("Stroke");
        chkSdkiStroke.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiStroke.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiStroke.setName("chkSdkiStroke"); // NOI18N
        chkSdkiStroke.setOpaque(false);
        chkSdkiStroke.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiStroke);
        chkSdkiStroke.setBounds(370, 542, 60, 23);

        chkSdkiCedera.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiCedera.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiCedera.setText("Cedera kepala");
        chkSdkiCedera.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiCedera.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiCedera.setName("chkSdkiCedera"); // NOI18N
        chkSdkiCedera.setOpaque(false);
        chkSdkiCedera.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiCedera);
        chkSdkiCedera.setBounds(370, 514, 100, 23);

        chkSlkiGelisah.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiGelisah.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiGelisah.setText("Gelisah menurun");
        chkSlkiGelisah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiGelisah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiGelisah.setName("chkSlkiGelisah"); // NOI18N
        chkSlkiGelisah.setOpaque(false);
        chkSlkiGelisah.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiGelisah);
        chkSlkiGelisah.setBounds(455, 738, 120, 23);

        chkSlkiFrekuensi.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiFrekuensi.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiFrekuensi.setText("Frekuensi napas membaik");
        chkSlkiFrekuensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiFrekuensi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiFrekuensi.setName("chkSlkiFrekuensi"); // NOI18N
        chkSlkiFrekuensi.setOpaque(false);
        chkSlkiFrekuensi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiFrekuensi);
        chkSlkiFrekuensi.setBounds(455, 766, 160, 23);

        chkSlkiPola.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiPola.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiPola.setText("Pola napas membaik");
        chkSlkiPola.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiPola.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiPola.setName("chkSlkiPola"); // NOI18N
        chkSlkiPola.setOpaque(false);
        chkSlkiPola.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiPola);
        chkSlkiPola.setBounds(455, 794, 130, 23);

        chkSikiMonitorBunyi.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiMonitorBunyi.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiMonitorBunyi.setText("Monitor bunyi napas tambahan (mis.gurgling, mengi, wheezing, ronkhi kering)");
        chkSikiMonitorBunyi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiMonitorBunyi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiMonitorBunyi.setName("chkSikiMonitorBunyi"); // NOI18N
        chkSikiMonitorBunyi.setOpaque(false);
        chkSikiMonitorBunyi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiMonitorBunyi);
        chkSikiMonitorBunyi.setBounds(145, 906, 410, 23);

        chkSikiLakukanFisio.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiLakukanFisio.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiLakukanFisio.setText("Lakukan fisioterapi dada, jika perlu");
        chkSikiLakukanFisio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiLakukanFisio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiLakukanFisio.setName("chkSikiLakukanFisio"); // NOI18N
        chkSikiLakukanFisio.setOpaque(false);
        chkSikiLakukanFisio.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiLakukanFisio);
        chkSikiLakukanFisio.setBounds(145, 1074, 200, 23);

        chkSikiLakukanPenghisapan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiLakukanPenghisapan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiLakukanPenghisapan.setText("Lakukan penghisapan lendir kurang dari 15 detik");
        chkSikiLakukanPenghisapan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiLakukanPenghisapan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiLakukanPenghisapan.setName("chkSikiLakukanPenghisapan"); // NOI18N
        chkSikiLakukanPenghisapan.setOpaque(false);
        chkSikiLakukanPenghisapan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiLakukanPenghisapan);
        chkSikiLakukanPenghisapan.setBounds(145, 1102, 260, 23);

        chkSikiLakukanHiper.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiLakukanHiper.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiLakukanHiper.setText("Lakukan hiperoksigenasi sebelum penghisapan endotrakeal");
        chkSikiLakukanHiper.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiLakukanHiper.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiLakukanHiper.setName("chkSikiLakukanHiper"); // NOI18N
        chkSikiLakukanHiper.setOpaque(false);
        chkSikiLakukanHiper.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiLakukanHiper);
        chkSikiLakukanHiper.setBounds(420, 1046, 320, 23);

        chkSikiKeluarkan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiKeluarkan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiKeluarkan.setText("Keluarkan sumbatan benda padat dengan forsep McGill");
        chkSikiKeluarkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiKeluarkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiKeluarkan.setName("chkSikiKeluarkan"); // NOI18N
        chkSikiKeluarkan.setOpaque(false);
        chkSikiKeluarkan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiKeluarkan);
        chkSikiKeluarkan.setBounds(420, 1074, 300, 23);

        chkSikiBerikanOksigen.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiBerikanOksigen.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiBerikanOksigen.setText("Berikan oksigen, jika perlu");
        chkSikiBerikanOksigen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiBerikanOksigen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiBerikanOksigen.setName("chkSikiBerikanOksigen"); // NOI18N
        chkSikiBerikanOksigen.setOpaque(false);
        chkSikiBerikanOksigen.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiBerikanOksigen);
        chkSikiBerikanOksigen.setBounds(420, 1102, 160, 23);

        TketKolaborasi.setForeground(new java.awt.Color(0, 0, 0));
        TketKolaborasi.setName("TketKolaborasi"); // NOI18N
        TketKolaborasi.setPreferredSize(new java.awt.Dimension(80, 23));
        TketKolaborasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketKolaborasiKeyPressed(evt);
            }
        });
        FormInput.add(TketKolaborasi);
        TketKolaborasi.setBounds(145, 1236, 400, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass13.add(Scroll1);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data MasKep Bersihan Jalan Nafas Tidak Efektif ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
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

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-07-2026" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-07-2026" }));
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
            if (Sequel.menyimpantf("masalah_keperawatan_bersihan_jln_nafas_tdk_efektif", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 66, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), sdkiSpasme, sdkiHipersekresi, sdkiDisfungsi, sdkiBenda, sdkiAdanya, sdkiSekresi, sdkiHiperplasia, sdkiProses, sdkiRespon,
                        sdkiEfek, sdkiMerokokAktif, sdkiMerokokPasif, sdkiTerpajan, sdkiBatuk, sdkiTidak, sdkiSputum, sdkiMengi, sdkiMekonium, sdkiDispnea, sdkiSulit, sdkiOrtopnea,
                        sdkiGelisah, sdkiSianosis, sdkiBunyi, sdkiPola, sdkiGullian, sdkiSclerosis, sdkiMyasthenia, sdkiProsedur, sdkiDepresi, sdkiCedera, sdkiStroke, sdkiKuadri,
                        sdkiSindrom, sdkiInfeksi, slkiBersihan, TketSelama.getText(), slkiBatuk, slkiProduksi, slkiWheezing, slkiMengi, slkiDispnea, slkiSulit, slkiGelisah, slkiFrekuensi,
                        slkiPola, sikiMonitorPola, sikiMonitorBunyi, sikiMonitorSputum, sikiPertahankan, sikiPosisikan, sikiBerikanMinum, sikiLakukanFisio, sikiLakukanPenghisapan,
                        sikiLakukanHiper, sikiKeluarkan, sikiBerikanOksigen, sikiAnjurkan, sikiAjarkan, sikiKolaborasi, TketKolaborasi.getText(), sttsRawat, TnipPetugas.getText(),
                        Sequel.cariIsi("select now()")
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Masalah Keperawatan Bersihan Jalan Nafas Tidak Efektif", "Simpan");
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
                if (Sequel.mengedittf("masalah_keperawatan_bersihan_jln_nafas_tdk_efektif", "waktu_simpan=?", "sdki_spasme=?, sdki_hipersekresi=?, sdki_disfungsi=?, sdki_benda=?, "
                        + "sdki_adanya=?, sdki_sekresi=?, sdki_hiperplasia=?, sdki_proses=?, sdki_respon=?, sdki_efek=?, sdki_merokok_aktif=?, sdki_merokok_pasif=?, sdki_terpajan=?, "
                        + "sdki_batuk=?, sdki_tidak=?, sdki_sputum=?, sdki_mengi=?, sdki_mekonium=?, sdki_dispnea=?, sdki_sulit=?, sdki_ortopnea=?, sdki_gelisah=?, sdki_sianosis=?, "
                        + "sdki_bunyi=?, sdki_pola=?, sdki_gullian=?, sdki_sclerosis=?, sdki_myasthenia=?, sdki_prosedur=?, sdki_depresi=?, sdki_cedera=?, sdki_stroke=?, sdki_kuadri=?, "
                        + "sdki_sindrom=?, sdki_infeksi=?, slki_bersihan=?, ket_selama=?, slki_batuk=?, slki_produksi=?, slki_wheezing=?, slki_mengi=?, slki_dispnea=?, slki_sulit=?, "
                        + "slki_gelisah=?, slki_frekuensi=?, slki_pola=?, siki_monitor_pola=?, siki_monitor_bunyi=?, siki_monitor_sputum=?, siki_pertahankan=?, siki_posisikan=?, "
                        + "siki_berikan_minum=?, siki_lakukan_fisio=?, siki_lakukan_penghisapan=?, siki_lakukan_hiper=?, siki_keluarkan=?, siki_berikan_oksigen=?, siki_anjurkan=?, "
                        + "siki_ajarkan=?, siki_kolaborasi=?, ket_kolaborasi=?, nip_petugas=?", 63, new String[]{
                            sdkiSpasme, sdkiHipersekresi, sdkiDisfungsi, sdkiBenda, sdkiAdanya, sdkiSekresi, sdkiHiperplasia, sdkiProses, sdkiRespon, sdkiEfek, sdkiMerokokAktif,
                            sdkiMerokokPasif, sdkiTerpajan, sdkiBatuk, sdkiTidak, sdkiSputum, sdkiMengi, sdkiMekonium, sdkiDispnea, sdkiSulit, sdkiOrtopnea, sdkiGelisah, sdkiSianosis,
                            sdkiBunyi, sdkiPola, sdkiGullian, sdkiSclerosis, sdkiMyasthenia, sdkiProsedur, sdkiDepresi, sdkiCedera, sdkiStroke, sdkiKuadri, sdkiSindrom, sdkiInfeksi,
                            slkiBersihan, TketSelama.getText(), slkiBatuk, slkiProduksi, slkiWheezing, slkiMengi, slkiDispnea, slkiSulit, slkiGelisah, slkiFrekuensi, slkiPola,
                            sikiMonitorPola, sikiMonitorBunyi, sikiMonitorSputum, sikiPertahankan, sikiPosisikan, sikiBerikanMinum, sikiLakukanFisio, sikiLakukanPenghisapan,
                            sikiLakukanHiper, sikiKeluarkan, sikiBerikanOksigen, sikiAnjurkan, sikiAjarkan, sikiKolaborasi, TketKolaborasi.getText(), TnipPetugas.getText(),
                            tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 0).toString()
                        }) == true) {

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Masalah Keperawatan Bersihan Jalan Nafas Tidak Efektif", "Ganti");
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
        akses.setform("RMMasalahKeperawatanBersihanJlnNafas");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbMasalah.getSelectedRow() > -1) {
            if (akses.getadmin() == true || tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 70).toString().equals(akses.getkode())) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from masalah_keperawatan_bersihan_jln_nafas_tdk_efektif where waktu_simpan=?", 1, new String[]{
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

            if (chkSdkiSpasme.isSelected() == true) {
                param.put("sdkiSpasme", "V");
            } else {
                param.put("sdkiSpasme", "");
            }

            if (chkSdkiHipersekresi.isSelected() == true) {
                param.put("sdkiHipersekresi", "V");
            } else {
                param.put("sdkiHipersekresi", "");
            }

            if (chkSdkiDisfungsi.isSelected() == true) {
                param.put("sdkiDisfungsi", "V");
            } else {
                param.put("sdkiDisfungsi", "");
            }

            if (chkSdkiBenda.isSelected() == true) {
                param.put("sdkiBenda", "V");
            } else {
                param.put("sdkiBenda", "");
            }

            if (chkSdkiAdanya.isSelected() == true) {
                param.put("sdkiAdanya", "V");
            } else {
                param.put("sdkiAdanya", "");
            }

            if (chkSdkiSekresi.isSelected() == true) {
                param.put("sdkiSekresi", "V");
            } else {
                param.put("sdkiSekresi", "");
            }

            if (chkSdkiHiperplasia.isSelected() == true) {
                param.put("sdkiHiperplasia", "V");
            } else {
                param.put("sdkiHiperplasia", "");
            }

            if (chkSdkiProses.isSelected() == true) {
                param.put("sdkiProses", "V");
            } else {
                param.put("sdkiProses", "");
            }

            if (chkSdkiRespon.isSelected() == true) {
                param.put("sdkiRespon", "V");
            } else {
                param.put("sdkiRespon", "");
            }

            if (chkSdkiEfek.isSelected() == true) {
                param.put("sdkiEfek", "V");
            } else {
                param.put("sdkiEfek", "");
            }

            if (chkSdkiMerokokAktif.isSelected() == true) {
                param.put("sdkiMerokokAktif", "V");
            } else {
                param.put("sdkiMerokokAktif", "");
            }

            if (chkSdkiMerokokPasif.isSelected() == true) {
                param.put("sdkiMerokokPasif", "V");
            } else {
                param.put("sdkiMerokokPasif", "");
            }

            if (chkSdkiTerpajan.isSelected() == true) {
                param.put("sdkiTerpajan", "V");
            } else {
                param.put("sdkiTerpajan", "");
            }

            if (chkSdkiBatuk.isSelected() == true) {
                param.put("sdkiBatuk", "V");
            } else {
                param.put("sdkiBatuk", "");
            }

            if (chkSdkiTidak.isSelected() == true) {
                param.put("sdkiTidak", "V");
            } else {
                param.put("sdkiTidak", "");
            }

            if (chkSdkiSputum.isSelected() == true) {
                param.put("sdkiSputum", "V");
            } else {
                param.put("sdkiSputum", "");
            }

            if (chkSdkiMengi.isSelected() == true) {
                param.put("sdkiMengi", "V");
            } else {
                param.put("sdkiMengi", "");
            }

            if (chkSdkiMekonium.isSelected() == true) {
                param.put("sdkiMekonium", "V");
            } else {
                param.put("sdkiMekonium", "");
            }

            if (chkSdkiDispnea.isSelected() == true) {
                param.put("sdkiDispnea", "V");
            } else {
                param.put("sdkiDispnea", "");
            }

            if (chkSdkiSulit.isSelected() == true) {
                param.put("sdkiSulit", "V");
            } else {
                param.put("sdkiSulit", "");
            }

            if (chkSdkiOrtopnea.isSelected() == true) {
                param.put("sdkiOrtopnea", "V");
            } else {
                param.put("sdkiOrtopnea", "");
            }

            if (chkSdkiGelisah.isSelected() == true) {
                param.put("sdkiGelisah", "V");
            } else {
                param.put("sdkiGelisah", "");
            }

            if (chkSdkiSianosis.isSelected() == true) {
                param.put("sdkiSianosis", "V");
            } else {
                param.put("sdkiSianosis", "");
            }

            if (chkSdkiBunyi.isSelected() == true) {
                param.put("sdkiBunyi", "V");
            } else {
                param.put("sdkiBunyi", "");
            }

            if (chkSdkiPola.isSelected() == true) {
                param.put("sdkiPola", "V");
            } else {
                param.put("sdkiPola", "");
            }

            if (chkSdkiGullian.isSelected() == true) {
                param.put("sdkiGullian", "V");
            } else {
                param.put("sdkiGullian", "");
            }

            if (chkSdkiSclerosis.isSelected() == true) {
                param.put("sdkiSclerosis", "V");
            } else {
                param.put("sdkiSclerosis", "");
            }

            if (chkSdkiMyasthenia.isSelected() == true) {
                param.put("sdkiMyasthenia", "V");
            } else {
                param.put("sdkiMyasthenia", "");
            }

            if (chkSdkiProsedur.isSelected() == true) {
                param.put("sdkiProsedur", "V");
            } else {
                param.put("sdkiProsedur", "");
            }

            if (chkSdkiDepresi.isSelected() == true) {
                param.put("sdkiDepresi", "V");
            } else {
                param.put("sdkiDepresi", "");
            }

            if (chkSdkiCedera.isSelected() == true) {
                param.put("sdkiCedera", "V");
            } else {
                param.put("sdkiCedera", "");
            }

            if (chkSdkiStroke.isSelected() == true) {
                param.put("sdkiStroke", "V");
            } else {
                param.put("sdkiStroke", "");
            }

            if (chkSdkiKuadri.isSelected() == true) {
                param.put("sdkiKuadri", "V");
            } else {
                param.put("sdkiKuadri", "");
            }

            if (chkSdkiSindrom.isSelected() == true) {
                param.put("sdkiSindrom", "V");
            } else {
                param.put("sdkiSindrom", "");
            }

            if (chkSdkiInfeksi.isSelected() == true) {
                param.put("sdkiInfeksi", "V");
            } else {
                param.put("sdkiInfeksi", "");
            }

            if (chkSlkiBersihan.isSelected() == true) {
                param.put("slkiBersihan", "V");
            } else {
                param.put("slkiBersihan", "");
            }
            
            if (TketSelama.getText().equals("")) {
                param.put("ketSelama", "...........");
            } else {
                param.put("ketSelama", TketSelama.getText());
            }
            
            if (chkSlkiBatuk.isSelected() == true) {
                param.put("slkiBatuk", "V");
            } else {
                param.put("slkiBatuk", "");
            }

            if (chkSlkiProduksi.isSelected() == true) {
                param.put("slkiProduksi", "V");
            } else {
                param.put("slkiProduksi", "");
            }

            if (chkSlkiWheezing.isSelected() == true) {
                param.put("slkiWheezing", "V");
            } else {
                param.put("slkiWheezing", "");
            }

            if (chkSlkiMengi.isSelected() == true) {
                param.put("slkiMengi", "V");
            } else {
                param.put("slkiMengi", "");
            }

            if (chkSlkiDispnea.isSelected() == true) {
                param.put("slkiDispnea", "V");
            } else {
                param.put("slkiDispnea", "");
            }

            if (chkSlkiSulit.isSelected() == true) {
                param.put("slkiSulit", "V");
            } else {
                param.put("slkiSulit", "");
            }

            if (chkSlkiGelisah.isSelected() == true) {
                param.put("slkiGelisah", "V");
            } else {
                param.put("slkiGelisah", "");
            }

            if (chkSlkiFrekuensi.isSelected() == true) {
                param.put("slkiFrekuensi", "V");
            } else {
                param.put("slkiFrekuensi", "");
            }

            if (chkSlkiPola.isSelected() == true) {
                param.put("slkiPola", "V");
            } else {
                param.put("slkiPola", "");
            }

            if (chkSikiMonitorPola.isSelected() == true) {
                param.put("sikiMonitorPola", "V");
            } else {
                param.put("sikiMonitorPola", "");
            }

            if (chkSikiMonitorBunyi.isSelected() == true) {
                param.put("sikiMonitorBunyi", "V");
            } else {
                param.put("sikiMonitorBunyi", "");
            }

            if (chkSikiMonitorSputum.isSelected() == true) {
                param.put("sikiMonitorSputum", "V");
            } else {
                param.put("sikiMonitorSputum", "");
            }

            if (chkSikiPertahankan.isSelected() == true) {
                param.put("sikiPertahankan", "V");
            } else {
                param.put("sikiPertahankan", "");
            }

            if (chkSikiPosisikan.isSelected() == true) {
                param.put("sikiPosisikan", "V");
            } else {
                param.put("sikiPosisikan", "");
            }

            if (chkSikiBerikanMinum.isSelected() == true) {
                param.put("sikiBerikanMinum", "V");
            } else {
                param.put("sikiBerikanMinum", "");
            }

            if (chkSikiLakukanFisio.isSelected() == true) {
                param.put("sikiLakukanFisio", "V");
            } else {
                param.put("sikiLakukanFisio", "");
            }

            if (chkSikiLakukanPenghisapan.isSelected() == true) {
                param.put("sikiLakukanPenghisapan", "V");
            } else {
                param.put("sikiLakukanPenghisapan", "");
            }

            if (chkSikiLakukanHiper.isSelected() == true) {
                param.put("sikiLakukanHiper", "V");
            } else {
                param.put("sikiLakukanHiper", "");
            }

            if (chkSikiKeluarkan.isSelected() == true) {
                param.put("sikiKeluarkan", "V");
            } else {
                param.put("sikiKeluarkan", "");
            }

            if (chkSikiBerikanOksigen.isSelected() == true) {
                param.put("sikiBerikanOksigen", "V");
            } else {
                param.put("sikiBerikanOksigen", "");
            }

            if (chkSikiAnjurkan.isSelected() == true) {
                param.put("sikiAnjurkan", "V");
            } else {
                param.put("sikiAnjurkan", "");
            }

            if (chkSikiAjarkan.isSelected() == true) {
                param.put("sikiAjarkan", "V");
            } else {
                param.put("sikiAjarkan", "");
            }

            if (chkSikiKolaborasi.isSelected() == true) {
                param.put("sikiKolaborasi", "V");
                if (TketKolaborasi.getText().equals("")) {
                    param.put("ketKolaborasi", "...................");
                } else {
                    param.put("ketKolaborasi", TketKolaborasi.getText());
                }
            } else {
                param.put("sikiKolaborasi", "");
                param.put("ketKolaborasi", "...................");
            }

            Valid.MyReport("rptMasKepBersihanJlnNafasTidakEfektif.jasper", "report", "::[ RM Masalah Keperawatan Bersihan Jalan Nafas Tidak Efektif ]::",
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

    private void chkSdkiSulitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSdkiSulitActionPerformed
        TketSelama.setText("");
        if (chkSdkiSulit.isSelected() == true) {
            TketSelama.setEnabled(true);
            TketSelama.requestFocus();
        } else {
            TketSelama.setEnabled(false);
        }
    }//GEN-LAST:event_chkSdkiSulitActionPerformed

    private void chkSikiKolaborasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSikiKolaborasiActionPerformed
        TketKolaborasi.setText("");
        if (chkSikiKolaborasi.isSelected() == true) {
            TketKolaborasi.setEnabled(true);
            TketKolaborasi.requestFocus();
        } else {
            TketKolaborasi.setEnabled(false);
        }
    }//GEN-LAST:event_chkSikiKolaborasiActionPerformed

    private void TketSelamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketSelamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSlkiBatuk.requestFocus();
        }
    }//GEN-LAST:event_TketSelamaKeyPressed

    private void TketKolaborasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketKolaborasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnPetugas.requestFocus();
        }
    }//GEN-LAST:event_TketKolaborasiKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMMasalahKeperawatanBersihanJlnNafas dialog = new RMMasalahKeperawatanBersihanJlnNafas(new javax.swing.JFrame(), true);
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
    private widget.TextBox TketKolaborasi;
    private widget.TextBox TketSelama;
    private widget.TextBox TnipPetugas;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TrgRawat;
    private widget.CekBox chkSdkiAdanya;
    private widget.CekBox chkSdkiBatuk;
    private widget.CekBox chkSdkiBenda;
    private widget.CekBox chkSdkiBunyi;
    private widget.CekBox chkSdkiCedera;
    private widget.CekBox chkSdkiDepresi;
    private widget.CekBox chkSdkiDisfungsi;
    private widget.CekBox chkSdkiDispnea;
    private widget.CekBox chkSdkiEfek;
    private widget.CekBox chkSdkiGelisah;
    private widget.CekBox chkSdkiGullian;
    private widget.CekBox chkSdkiHiperplasia;
    private widget.CekBox chkSdkiHipersekresi;
    private widget.CekBox chkSdkiInfeksi;
    private widget.CekBox chkSdkiKuadri;
    private widget.CekBox chkSdkiMekonium;
    private widget.CekBox chkSdkiMengi;
    private widget.CekBox chkSdkiMerokokAktif;
    private widget.CekBox chkSdkiMerokokPasif;
    private widget.CekBox chkSdkiMyasthenia;
    private widget.CekBox chkSdkiOrtopnea;
    private widget.CekBox chkSdkiPola;
    private widget.CekBox chkSdkiProsedur;
    private widget.CekBox chkSdkiProses;
    private widget.CekBox chkSdkiRespon;
    private widget.CekBox chkSdkiSclerosis;
    private widget.CekBox chkSdkiSekresi;
    private widget.CekBox chkSdkiSianosis;
    private widget.CekBox chkSdkiSindrom;
    private widget.CekBox chkSdkiSpasme;
    private widget.CekBox chkSdkiSputum;
    private widget.CekBox chkSdkiStroke;
    private widget.CekBox chkSdkiSulit;
    private widget.CekBox chkSdkiTerpajan;
    private widget.CekBox chkSdkiTidak;
    private widget.CekBox chkSikiAjarkan;
    private widget.CekBox chkSikiAnjurkan;
    private widget.CekBox chkSikiBerikanMinum;
    private widget.CekBox chkSikiBerikanOksigen;
    private widget.CekBox chkSikiKeluarkan;
    private widget.CekBox chkSikiKolaborasi;
    private widget.CekBox chkSikiLakukanFisio;
    private widget.CekBox chkSikiLakukanHiper;
    private widget.CekBox chkSikiLakukanPenghisapan;
    private widget.CekBox chkSikiMonitorBunyi;
    private widget.CekBox chkSikiMonitorPola;
    private widget.CekBox chkSikiMonitorSputum;
    private widget.CekBox chkSikiPertahankan;
    private widget.CekBox chkSikiPosisikan;
    private widget.CekBox chkSlkiBatuk;
    private widget.CekBox chkSlkiBersihan;
    private widget.CekBox chkSlkiDispnea;
    private widget.CekBox chkSlkiFrekuensi;
    private widget.CekBox chkSlkiGelisah;
    private widget.CekBox chkSlkiMengi;
    private widget.CekBox chkSlkiPola;
    private widget.CekBox chkSlkiProduksi;
    private widget.CekBox chkSlkiSulit;
    private widget.CekBox chkSlkiWheezing;
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
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
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
                    + "pg.nama nmPetugas from masalah_keperawatan_bersihan_jln_nafas_tdk_efektif m inner join reg_periksa rp on rp.no_rawat=m.no_rawat "
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
                        rs.getString("sdki_spasme"),
                        rs.getString("sdki_hipersekresi"),
                        rs.getString("sdki_disfungsi"),
                        rs.getString("sdki_benda"),
                        rs.getString("sdki_adanya"),
                        rs.getString("sdki_sekresi"),
                        rs.getString("sdki_hiperplasia"),
                        rs.getString("sdki_proses"),
                        rs.getString("sdki_respon"),
                        rs.getString("sdki_efek"),
                        rs.getString("sdki_merokok_aktif"),
                        rs.getString("sdki_merokok_pasif"),
                        rs.getString("sdki_terpajan"),
                        rs.getString("sdki_batuk"),
                        rs.getString("sdki_tidak"),
                        rs.getString("sdki_sputum"),
                        rs.getString("sdki_mengi"),
                        rs.getString("sdki_mekonium"),
                        rs.getString("sdki_dispnea"),
                        rs.getString("sdki_sulit"),
                        rs.getString("sdki_ortopnea"),
                        rs.getString("sdki_gelisah"),
                        rs.getString("sdki_sianosis"),
                        rs.getString("sdki_bunyi"),
                        rs.getString("sdki_pola"),
                        rs.getString("sdki_gullian"),
                        rs.getString("sdki_sclerosis"),
                        rs.getString("sdki_myasthenia"),
                        rs.getString("sdki_prosedur"),
                        rs.getString("sdki_depresi"),
                        rs.getString("sdki_cedera"),
                        rs.getString("sdki_stroke"),
                        rs.getString("sdki_kuadri"),
                        rs.getString("sdki_sindrom"),
                        rs.getString("sdki_infeksi"),
                        rs.getString("slki_bersihan"),
                        rs.getString("ket_selama"),
                        rs.getString("slki_batuk"),
                        rs.getString("slki_produksi"),
                        rs.getString("slki_wheezing"),
                        rs.getString("slki_mengi"),
                        rs.getString("slki_dispnea"),
                        rs.getString("slki_sulit"),
                        rs.getString("slki_gelisah"),
                        rs.getString("slki_frekuensi"),
                        rs.getString("slki_pola"),
                        rs.getString("siki_monitor_pola"),
                        rs.getString("siki_monitor_bunyi"),
                        rs.getString("siki_monitor_sputum"),
                        rs.getString("siki_pertahankan"),
                        rs.getString("siki_posisikan"),
                        rs.getString("siki_berikan_minum"),
                        rs.getString("siki_lakukan_fisio"),
                        rs.getString("siki_lakukan_penghisapan"),
                        rs.getString("siki_lakukan_hiper"),
                        rs.getString("siki_keluarkan"),
                        rs.getString("siki_berikan_oksigen"),
                        rs.getString("siki_anjurkan"),
                        rs.getString("siki_ajarkan"),
                        rs.getString("siki_kolaborasi"),
                        rs.getString("ket_kolaborasi"),
                        rs.getString("status_rawat"),
                        rs.getString("nip_petugas")
                    });
                }
            } catch (Exception e) {
                System.out.println("rekammedis.RMMasalahKeperawatanBersihanJlnNafas.tampil() : " + e);
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
        chkSdkiSpasme.setSelected(false);
        chkSdkiHipersekresi.setSelected(false);
        chkSdkiDisfungsi.setSelected(false);
        chkSdkiBenda.setSelected(false);
        chkSdkiAdanya.setSelected(false);
        chkSdkiSekresi.setSelected(false);
        chkSdkiHiperplasia.setSelected(false);
        chkSdkiProses.setSelected(false);
        chkSdkiRespon.setSelected(false);
        chkSdkiEfek.setSelected(false);
        chkSdkiMerokokAktif.setSelected(false);
        chkSdkiMerokokPasif.setSelected(false);
        chkSdkiTerpajan.setSelected(false);
        chkSdkiBatuk.setSelected(false);
        chkSdkiTidak.setSelected(false);
        chkSdkiSputum.setSelected(false);
        chkSdkiMengi.setSelected(false);
        chkSdkiMekonium.setSelected(false);
        chkSdkiDispnea.setSelected(false);
        chkSdkiSulit.setSelected(false);
        chkSdkiOrtopnea.setSelected(false);
        chkSdkiGelisah.setSelected(false);
        chkSdkiSianosis.setSelected(false);
        chkSdkiBunyi.setSelected(false);
        chkSdkiPola.setSelected(false);
        chkSdkiGullian.setSelected(false);
        chkSdkiSclerosis.setSelected(false);
        chkSdkiMyasthenia.setSelected(false);
        chkSdkiProsedur.setSelected(false);
        chkSdkiDepresi.setSelected(false);
        chkSdkiCedera.setSelected(false);
        chkSdkiStroke.setSelected(false);
        chkSdkiKuadri.setSelected(false);
        chkSdkiSindrom.setSelected(false);
        chkSdkiInfeksi.setSelected(false);

        chkSlkiBersihan.setSelected(false);
        TketSelama.setText("");
        chkSlkiBatuk.setSelected(false);
        chkSlkiProduksi.setSelected(false);
        chkSlkiWheezing.setSelected(false);
        chkSlkiMengi.setSelected(false);
        chkSlkiDispnea.setSelected(false);
        chkSlkiSulit.setSelected(false);
        chkSlkiGelisah.setSelected(false);
        chkSlkiFrekuensi.setSelected(false);
        chkSlkiPola.setSelected(false);

        chkSikiMonitorPola.setSelected(false);
        chkSikiMonitorBunyi.setSelected(false);
        chkSikiMonitorSputum.setSelected(false);
        chkSikiPertahankan.setSelected(false);
        chkSikiPosisikan.setSelected(false);
        chkSikiBerikanMinum.setSelected(false);
        chkSikiLakukanFisio.setSelected(false);
        chkSikiLakukanPenghisapan.setSelected(false);
        chkSikiLakukanHiper.setSelected(false);
        chkSikiKeluarkan.setSelected(false);
        chkSikiBerikanOksigen.setSelected(false);
        chkSikiAnjurkan.setSelected(false);
        chkSikiAjarkan.setSelected(false);
        chkSikiKolaborasi.setSelected(false);
        TketKolaborasi.setText("");
        TketKolaborasi.setEnabled(false);
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

            sdkiSpasme = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 8).toString();
            sdkiHipersekresi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 9).toString();
            sdkiDisfungsi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 10).toString();
            sdkiBenda = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 11).toString();
            sdkiAdanya = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 12).toString();
            sdkiSekresi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 13).toString();
            sdkiHiperplasia = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 14).toString();
            sdkiProses = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 15).toString();
            sdkiRespon = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 16).toString();
            sdkiEfek = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 17).toString();
            sdkiMerokokAktif = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 18).toString();
            sdkiMerokokPasif = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 19).toString();
            sdkiTerpajan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 20).toString();
            sdkiBatuk = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 21).toString();
            sdkiTidak = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 22).toString();
            sdkiSputum = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 23).toString();
            sdkiMengi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 24).toString();
            sdkiMekonium = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 25).toString();
            sdkiDispnea = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 26).toString();
            sdkiSulit = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 27).toString();
            sdkiOrtopnea = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 28).toString();
            sdkiGelisah = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 29).toString();
            sdkiSianosis = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 30).toString();
            sdkiBunyi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 31).toString();
            sdkiPola = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 32).toString();
            sdkiGullian = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 33).toString();
            sdkiSclerosis = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 34).toString();
            sdkiMyasthenia = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 35).toString();
            sdkiProsedur = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 36).toString();
            sdkiDepresi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 37).toString();
            sdkiCedera = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 38).toString();
            sdkiStroke = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 39).toString();
            sdkiKuadri = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 40).toString();
            sdkiSindrom = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 41).toString();
            sdkiInfeksi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 42).toString();
            
            slkiBersihan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 43).toString();
            TketSelama.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 44).toString());
            slkiBatuk = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 45).toString();
            slkiProduksi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 46).toString();
            slkiWheezing = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 47).toString();
            slkiMengi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 48).toString();
            slkiDispnea = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 49).toString();
            slkiSulit = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 50).toString();
            slkiGelisah = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 51).toString();
            slkiFrekuensi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 52).toString();
            slkiPola = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 53).toString();

            sikiMonitorPola = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 54).toString();
            sikiMonitorBunyi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 55).toString();
            sikiMonitorSputum = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 56).toString();
            sikiPertahankan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 57).toString();
            sikiPosisikan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 58).toString();
            sikiBerikanMinum = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 59).toString();
            sikiLakukanFisio = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 60).toString();
            sikiLakukanPenghisapan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 61).toString();
            sikiLakukanHiper = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 62).toString();
            sikiKeluarkan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 63).toString();
            sikiBerikanOksigen = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 64).toString();
            sikiAnjurkan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 65).toString();
            sikiAjarkan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 66).toString();
            sikiKolaborasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 67).toString();
            TketKolaborasi.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 68).toString());

            TnipPetugas.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 70).toString());
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
        if (sdkiSpasme.equals("ya")) {
            chkSdkiSpasme.setSelected(true);
        } else {
            chkSdkiSpasme.setSelected(false);
        }

        if (sdkiHipersekresi.equals("ya")) {
            chkSdkiHipersekresi.setSelected(true);
        } else {
            chkSdkiHipersekresi.setSelected(false);
        }

        if (sdkiDisfungsi.equals("ya")) {
            chkSdkiDisfungsi.setSelected(true);
        } else {
            chkSdkiDisfungsi.setSelected(false);
        }

        if (sdkiBenda.equals("ya")) {
            chkSdkiBenda.setSelected(true);
        } else {
            chkSdkiBenda.setSelected(false);
        }

        if (sdkiAdanya.equals("ya")) {
            chkSdkiAdanya.setSelected(true);
        } else {
            chkSdkiAdanya.setSelected(false);
        }

        if (sdkiSekresi.equals("ya")) {
            chkSdkiSekresi.setSelected(true);
        } else {
            chkSdkiSekresi.setSelected(false);
        }

        if (sdkiHiperplasia.equals("ya")) {
            chkSdkiHiperplasia.setSelected(true);
        } else {
            chkSdkiHiperplasia.setSelected(false);
        }

        if (sdkiProses.equals("ya")) {
            chkSdkiProses.setSelected(true);
        } else {
            chkSdkiProses.setSelected(false);
        }

        if (sdkiRespon.equals("ya")) {
            chkSdkiRespon.setSelected(true);
        } else {
            chkSdkiRespon.setSelected(false);
        }

        if (sdkiEfek.equals("ya")) {
            chkSdkiEfek.setSelected(true);
        } else {
            chkSdkiEfek.setSelected(false);
        }

        if (sdkiMerokokAktif.equals("ya")) {
            chkSdkiMerokokAktif.setSelected(true);
        } else {
            chkSdkiMerokokAktif.setSelected(false);
        }

        if (sdkiMerokokPasif.equals("ya")) {
            chkSdkiMerokokPasif.setSelected(true);
        } else {
            chkSdkiMerokokPasif.setSelected(false);
        }

        if (sdkiTerpajan.equals("ya")) {
            chkSdkiTerpajan.setSelected(true);
        } else {
            chkSdkiTerpajan.setSelected(false);
        }

        if (sdkiBatuk.equals("ya")) {
            chkSdkiBatuk.setSelected(true);
        } else {
            chkSdkiBatuk.setSelected(false);
        }

        if (sdkiTidak.equals("ya")) {
            chkSdkiTidak.setSelected(true);
        } else {
            chkSdkiTidak.setSelected(false);
        }

        if (sdkiSputum.equals("ya")) {
            chkSdkiSputum.setSelected(true);
        } else {
            chkSdkiSputum.setSelected(false);
        }

        if (sdkiMengi.equals("ya")) {
            chkSdkiMengi.setSelected(true);
        } else {
            chkSdkiMengi.setSelected(false);
        }

        if (sdkiMekonium.equals("ya")) {
            chkSdkiMekonium.setSelected(true);
        } else {
            chkSdkiMekonium.setSelected(false);
        }

        if (sdkiDispnea.equals("ya")) {
            chkSdkiDispnea.setSelected(true);
        } else {
            chkSdkiDispnea.setSelected(false);
        }

        if (sdkiSulit.equals("ya")) {
            chkSdkiSulit.setSelected(true);
        } else {
            chkSdkiSulit.setSelected(false);
        }

        if (sdkiOrtopnea.equals("ya")) {
            chkSdkiOrtopnea.setSelected(true);
        } else {
            chkSdkiOrtopnea.setSelected(false);
        }

        if (sdkiGelisah.equals("ya")) {
            chkSdkiGelisah.setSelected(true);
        } else {
            chkSdkiGelisah.setSelected(false);
        }

        if (sdkiSianosis.equals("ya")) {
            chkSdkiSianosis.setSelected(true);
        } else {
            chkSdkiSianosis.setSelected(false);
        }

        if (sdkiBunyi.equals("ya")) {
            chkSdkiBunyi.setSelected(true);
        } else {
            chkSdkiBunyi.setSelected(false);
        }

        if (sdkiPola.equals("ya")) {
            chkSdkiPola.setSelected(true);
        } else {
            chkSdkiPola.setSelected(false);
        }

        if (sdkiGullian.equals("ya")) {
            chkSdkiGullian.setSelected(true);
        } else {
            chkSdkiGullian.setSelected(false);
        }

        if (sdkiSclerosis.equals("ya")) {
            chkSdkiSclerosis.setSelected(true);
        } else {
            chkSdkiSclerosis.setSelected(false);
        }

        if (sdkiMyasthenia.equals("ya")) {
            chkSdkiMyasthenia.setSelected(true);
        } else {
            chkSdkiMyasthenia.setSelected(false);
        }

        if (sdkiProsedur.equals("ya")) {
            chkSdkiProsedur.setSelected(true);
        } else {
            chkSdkiProsedur.setSelected(false);
        }

        if (sdkiDepresi.equals("ya")) {
            chkSdkiDepresi.setSelected(true);
        } else {
            chkSdkiDepresi.setSelected(false);
        }

        if (sdkiCedera.equals("ya")) {
            chkSdkiCedera.setSelected(true);
        } else {
            chkSdkiCedera.setSelected(false);
        }

        if (sdkiStroke.equals("ya")) {
            chkSdkiStroke.setSelected(true);
        } else {
            chkSdkiStroke.setSelected(false);
        }

        if (sdkiKuadri.equals("ya")) {
            chkSdkiKuadri.setSelected(true);
        } else {
            chkSdkiKuadri.setSelected(false);
        }

        if (sdkiSindrom.equals("ya")) {
            chkSdkiSindrom.setSelected(true);
        } else {
            chkSdkiSindrom.setSelected(false);
        }

        if (sdkiInfeksi.equals("ya")) {
            chkSdkiInfeksi.setSelected(true);
        } else {
            chkSdkiInfeksi.setSelected(false);
        }

        if (slkiBersihan.equals("ya")) {
            chkSlkiBersihan.setSelected(true);
            TketSelama.setEnabled(true);
        } else {
            chkSlkiBersihan.setSelected(false);
            TketSelama.setEnabled(false);
        }

        if (slkiBatuk.equals("ya")) {
            chkSlkiBatuk.setSelected(true);
        } else {
            chkSlkiBatuk.setSelected(false);
        }

        if (slkiProduksi.equals("ya")) {
            chkSlkiProduksi.setSelected(true);
        } else {
            chkSlkiProduksi.setSelected(false);
        }

        if (slkiWheezing.equals("ya")) {
            chkSlkiWheezing.setSelected(true);
        } else {
            chkSlkiWheezing.setSelected(false);
        }

        if (slkiMengi.equals("ya")) {
            chkSlkiMengi.setSelected(true);
        } else {
            chkSlkiMengi.setSelected(false);
        }

        if (slkiDispnea.equals("ya")) {
            chkSlkiDispnea.setSelected(true);
        } else {
            chkSlkiDispnea.setSelected(false);
        }

        if (slkiSulit.equals("ya")) {
            chkSlkiSulit.setSelected(true);
        } else {
            chkSlkiSulit.setSelected(false);
        }

        if (slkiGelisah.equals("ya")) {
            chkSlkiGelisah.setSelected(true);
        } else {
            chkSlkiGelisah.setSelected(false);
        }

        if (slkiFrekuensi.equals("ya")) {
            chkSlkiFrekuensi.setSelected(true);
        } else {
            chkSlkiFrekuensi.setSelected(false);
        }

        if (slkiPola.equals("ya")) {
            chkSlkiPola.setSelected(true);
        } else {
            chkSlkiPola.setSelected(false);
        }

        if (sikiMonitorPola.equals("ya")) {
            chkSikiMonitorPola.setSelected(true);
        } else {
            chkSikiMonitorPola.setSelected(false);
        }

        if (sikiMonitorBunyi.equals("ya")) {
            chkSikiMonitorBunyi.setSelected(true);
        } else {
            chkSikiMonitorBunyi.setSelected(false);
        }

        if (sikiMonitorSputum.equals("ya")) {
            chkSikiMonitorSputum.setSelected(true);
        } else {
            chkSikiMonitorSputum.setSelected(false);
        }

        if (sikiPertahankan.equals("ya")) {
            chkSikiPertahankan.setSelected(true);
        } else {
            chkSikiPertahankan.setSelected(false);
        }

        if (sikiPosisikan.equals("ya")) {
            chkSikiPosisikan.setSelected(true);
        } else {
            chkSikiPosisikan.setSelected(false);
        }

        if (sikiBerikanMinum.equals("ya")) {
            chkSikiBerikanMinum.setSelected(true);
        } else {
            chkSikiBerikanMinum.setSelected(false);
        }

        if (sikiLakukanFisio.equals("ya")) {
            chkSikiLakukanFisio.setSelected(true);
        } else {
            chkSikiLakukanFisio.setSelected(false);
        }

        if (sikiLakukanPenghisapan.equals("ya")) {
            chkSikiLakukanPenghisapan.setSelected(true);
        } else {
            chkSikiLakukanPenghisapan.setSelected(false);
        }

        if (sikiLakukanHiper.equals("ya")) {
            chkSikiLakukanHiper.setSelected(true);
        } else {
            chkSikiLakukanHiper.setSelected(false);
        }

        if (sikiKeluarkan.equals("ya")) {
            chkSikiKeluarkan.setSelected(true);
        } else {
            chkSikiKeluarkan.setSelected(false);
        }

        if (sikiBerikanOksigen.equals("ya")) {
            chkSikiBerikanOksigen.setSelected(true);
        } else {
            chkSikiBerikanOksigen.setSelected(false);
        }

        if (sikiAnjurkan.equals("ya")) {
            chkSikiAnjurkan.setSelected(true);
        } else {
            chkSikiAnjurkan.setSelected(false);
        }

        if (sikiAjarkan.equals("ya")) {
            chkSikiAjarkan.setSelected(true);
        } else {
            chkSikiAjarkan.setSelected(false);
        }

        if (sikiKolaborasi.equals("ya")) {
            chkSikiKolaborasi.setSelected(true);
            TketKolaborasi.setEnabled(true);
        } else {
            chkSikiKolaborasi.setSelected(false);
            TketKolaborasi.setEnabled(false);
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
        if (chkSdkiSpasme.isSelected() == true) {
            sdkiSpasme = "ya";
        } else {
            sdkiSpasme = "tidak";
        }

        if (chkSdkiHipersekresi.isSelected() == true) {
            sdkiHipersekresi = "ya";
        } else {
            sdkiHipersekresi = "tidak";
        }

        if (chkSdkiDisfungsi.isSelected() == true) {
            sdkiDisfungsi = "ya";
        } else {
            sdkiDisfungsi = "tidak";
        }

        if (chkSdkiBenda.isSelected() == true) {
            sdkiBenda = "ya";
        } else {
            sdkiBenda = "tidak";
        }

        if (chkSdkiAdanya.isSelected() == true) {
            sdkiAdanya = "ya";
        } else {
            sdkiAdanya = "tidak";
        }

        if (chkSdkiSekresi.isSelected() == true) {
            sdkiSekresi = "ya";
        } else {
            sdkiSekresi = "tidak";
        }

        if (chkSdkiHiperplasia.isSelected() == true) {
            sdkiHiperplasia = "ya";
        } else {
            sdkiHiperplasia = "tidak";
        }

        if (chkSdkiProses.isSelected() == true) {
            sdkiProses = "ya";
        } else {
            sdkiProses = "tidak";
        }

        if (chkSdkiRespon.isSelected() == true) {
            sdkiRespon = "ya";
        } else {
            sdkiRespon = "tidak";
        }

        if (chkSdkiEfek.isSelected() == true) {
            sdkiEfek = "ya";
        } else {
            sdkiEfek = "tidak";
        }

        if (chkSdkiMerokokAktif.isSelected() == true) {
            sdkiMerokokAktif = "ya";
        } else {
            sdkiMerokokAktif = "tidak";
        }

        if (chkSdkiMerokokPasif.isSelected() == true) {
            sdkiMerokokPasif = "ya";
        } else {
            sdkiMerokokPasif = "tidak";
        }

        if (chkSdkiTerpajan.isSelected() == true) {
            sdkiTerpajan = "ya";
        } else {
            sdkiTerpajan = "tidak";
        }

        if (chkSdkiBatuk.isSelected() == true) {
            sdkiBatuk = "ya";
        } else {
            sdkiBatuk = "tidak";
        }

        if (chkSdkiTidak.isSelected() == true) {
            sdkiTidak = "ya";
        } else {
            sdkiTidak = "tidak";
        }

        if (chkSdkiSputum.isSelected() == true) {
            sdkiSputum = "ya";
        } else {
            sdkiSputum = "tidak";
        }

        if (chkSdkiMengi.isSelected() == true) {
            sdkiMengi = "ya";
        } else {
            sdkiMengi = "tidak";
        }

        if (chkSdkiMekonium.isSelected() == true) {
            sdkiMekonium = "ya";
        } else {
            sdkiMekonium = "tidak";
        }

        if (chkSdkiDispnea.isSelected() == true) {
            sdkiDispnea = "ya";
        } else {
            sdkiDispnea = "tidak";
        }

        if (chkSdkiSulit.isSelected() == true) {
            sdkiSulit = "ya";
        } else {
            sdkiSulit = "tidak";
        }

        if (chkSdkiOrtopnea.isSelected() == true) {
            sdkiOrtopnea = "ya";
        } else {
            sdkiOrtopnea = "tidak";
        }

        if (chkSdkiGelisah.isSelected() == true) {
            sdkiGelisah = "ya";
        } else {
            sdkiGelisah = "tidak";
        }

        if (chkSdkiSianosis.isSelected() == true) {
            sdkiSianosis = "ya";
        } else {
            sdkiSianosis = "tidak";
        }

        if (chkSdkiBunyi.isSelected() == true) {
            sdkiBunyi = "ya";
        } else {
            sdkiBunyi = "tidak";
        }

        if (chkSdkiPola.isSelected() == true) {
            sdkiPola = "ya";
        } else {
            sdkiPola = "tidak";
        }

        if (chkSdkiGullian.isSelected() == true) {
            sdkiGullian = "ya";
        } else {
            sdkiGullian = "tidak";
        }

        if (chkSdkiSclerosis.isSelected() == true) {
            sdkiSclerosis = "ya";
        } else {
            sdkiSclerosis = "tidak";
        }

        if (chkSdkiMyasthenia.isSelected() == true) {
            sdkiMyasthenia = "ya";
        } else {
            sdkiMyasthenia = "tidak";
        }

        if (chkSdkiProsedur.isSelected() == true) {
            sdkiProsedur = "ya";
        } else {
            sdkiProsedur = "tidak";
        }

        if (chkSdkiDepresi.isSelected() == true) {
            sdkiDepresi = "ya";
        } else {
            sdkiDepresi = "tidak";
        }

        if (chkSdkiCedera.isSelected() == true) {
            sdkiCedera = "ya";
        } else {
            sdkiCedera = "tidak";
        }

        if (chkSdkiStroke.isSelected() == true) {
            sdkiStroke = "ya";
        } else {
            sdkiStroke = "tidak";
        }

        if (chkSdkiKuadri.isSelected() == true) {
            sdkiKuadri = "ya";
        } else {
            sdkiKuadri = "tidak";
        }

        if (chkSdkiSindrom.isSelected() == true) {
            sdkiSindrom = "ya";
        } else {
            sdkiSindrom = "tidak";
        }

        if (chkSdkiInfeksi.isSelected() == true) {
            sdkiInfeksi = "ya";
        } else {
            sdkiInfeksi = "tidak";
        }

        if (chkSlkiBersihan.isSelected() == true) {
            slkiBersihan = "ya";
        } else {
            slkiBersihan = "tidak";
        }

        if (chkSlkiBatuk.isSelected() == true) {
            slkiBatuk = "ya";
        } else {
            slkiBatuk = "tidak";
        }

        if (chkSlkiProduksi.isSelected() == true) {
            slkiProduksi = "ya";
        } else {
            slkiProduksi = "tidak";
        }

        if (chkSlkiWheezing.isSelected() == true) {
            slkiWheezing = "ya";
        } else {
            slkiWheezing = "tidak";
        }

        if (chkSlkiMengi.isSelected() == true) {
            slkiMengi = "ya";
        } else {
            slkiMengi = "tidak";
        }

        if (chkSlkiDispnea.isSelected() == true) {
            slkiDispnea = "ya";
        } else {
            slkiDispnea = "tidak";
        }

        if (chkSlkiSulit.isSelected() == true) {
            slkiSulit = "ya";
        } else {
            slkiSulit = "tidak";
        }

        if (chkSlkiGelisah.isSelected() == true) {
            slkiGelisah = "ya";
        } else {
            slkiGelisah = "tidak";
        }

        if (chkSlkiFrekuensi.isSelected() == true) {
            slkiFrekuensi = "ya";
        } else {
            slkiFrekuensi = "tidak";
        }

        if (chkSlkiPola.isSelected() == true) {
            slkiPola = "ya";
        } else {
            slkiPola = "tidak";
        }

        if (chkSikiMonitorPola.isSelected() == true) {
            sikiMonitorPola = "ya";
        } else {
            sikiMonitorPola = "tidak";
        }

        if (chkSikiMonitorBunyi.isSelected() == true) {
            sikiMonitorBunyi = "ya";
        } else {
            sikiMonitorBunyi = "tidak";
        }

        if (chkSikiMonitorSputum.isSelected() == true) {
            sikiMonitorSputum = "ya";
        } else {
            sikiMonitorSputum = "tidak";
        }

        if (chkSikiPertahankan.isSelected() == true) {
            sikiPertahankan = "ya";
        } else {
            sikiPertahankan = "tidak";
        }

        if (chkSikiPosisikan.isSelected() == true) {
            sikiPosisikan = "ya";
        } else {
            sikiPosisikan = "tidak";
        }

        if (chkSikiBerikanMinum.isSelected() == true) {
            sikiBerikanMinum = "ya";
        } else {
            sikiBerikanMinum = "tidak";
        }

        if (chkSikiLakukanFisio.isSelected() == true) {
            sikiLakukanFisio = "ya";
        } else {
            sikiLakukanFisio = "tidak";
        }

        if (chkSikiLakukanPenghisapan.isSelected() == true) {
            sikiLakukanPenghisapan = "ya";
        } else {
            sikiLakukanPenghisapan = "tidak";
        }

        if (chkSikiLakukanHiper.isSelected() == true) {
            sikiLakukanHiper = "ya";
        } else {
            sikiLakukanHiper = "tidak";
        }

        if (chkSikiKeluarkan.isSelected() == true) {
            sikiKeluarkan = "ya";
        } else {
            sikiKeluarkan = "tidak";
        }

        if (chkSikiBerikanOksigen.isSelected() == true) {
            sikiBerikanOksigen = "ya";
        } else {
            sikiBerikanOksigen = "tidak";
        }

        if (chkSikiAnjurkan.isSelected() == true) {
            sikiAnjurkan = "ya";
        } else {
            sikiAnjurkan = "tidak";
        }

        if (chkSikiAjarkan.isSelected() == true) {
            sikiAjarkan = "ya";
        } else {
            sikiAjarkan = "tidak";
        }

        if (chkSikiKolaborasi.isSelected() == true) {
            sikiKolaborasi = "ya";
        } else {
            sikiKolaborasi = "tidak";
        }
    }
    
    private void emptVariabel() {
        sdkiSpasme = "";
        sdkiHipersekresi = "";
        sdkiDisfungsi = "";
        sdkiBenda = "";
        sdkiAdanya = "";
        sdkiSekresi = "";
        sdkiHiperplasia = "";
        sdkiProses = "";
        sdkiRespon = "";
        sdkiEfek = "";
        sdkiMerokokAktif = "";
        sdkiMerokokPasif = "";
        sdkiTerpajan = "";
        sdkiBatuk = "";
        sdkiTidak = "";
        sdkiSputum = "";
        sdkiMengi = "";
        sdkiMekonium = "";
        sdkiDispnea = "";
        sdkiSulit = "";
        sdkiOrtopnea = "";
        sdkiGelisah = "";
        sdkiSianosis = "";
        sdkiBunyi = "";
        sdkiPola = "";
        sdkiGullian = "";
        sdkiSclerosis = "";
        sdkiMyasthenia = "";
        sdkiProsedur = "";
        sdkiDepresi = "";
        sdkiCedera = "";
        sdkiStroke = "";
        sdkiKuadri = "";
        sdkiSindrom = "";
        sdkiInfeksi = "";
        slkiBersihan = "";
        slkiBatuk = "";
        slkiProduksi = "";
        slkiWheezing = "";
        slkiMengi = "";
        slkiDispnea = "";
        slkiSulit = "";
        slkiGelisah = "";
        slkiFrekuensi = "";
        slkiPola = "";
        sikiMonitorPola = "";
        sikiMonitorBunyi = "";
        sikiMonitorSputum = "";
        sikiPertahankan = "";
        sikiPosisikan = "";
        sikiBerikanMinum = "";
        sikiLakukanFisio = "";
        sikiLakukanPenghisapan = "";
        sikiLakukanHiper = "";
        sikiKeluarkan = "";
        sikiBerikanOksigen = "";
        sikiAnjurkan = "";
        sikiAjarkan = "";
        sikiKolaborasi = "";
    }
    
    public void awalData() {
        tampil();
    }
}
