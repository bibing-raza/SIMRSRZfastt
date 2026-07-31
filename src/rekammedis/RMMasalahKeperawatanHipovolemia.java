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
public class RMMasalahKeperawatanHipovolemia extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String sdkiKehilangan = "", sdkiKegagalan = "", sdkiPeningkatan = "", sdkiKekurangan = "", sdkiEvaporasi = "", sdkiFrekuensi = "", sdkiNadi = "", sdkiTekananDarah = "",
            sdkiTekananNadi = "", sdkiTurgor = "", sdkiMembran = "", sdkiVolume = "", sdkiHematokrit = "", sdkiMerasa = "", sdkiMengeluh = "", sdkiPengisian = "", sdkiStatus = "",
            sdkiSuhu = "", sdkiKonsentrasi = "", sdkiBerat = "", sdkiTrauma = "", sdkiLuka = "", sdkiAids = "", sdkiMuntah = "", sdkiDiare = "", sdkiHipoalbumin = "", slkiStatus = "",
            slkiElektrolit = "", slkiCairan = "", slkiAsupanCairan = "", slkiHaluaran = "", slkiKelembapan = "", slkiAsupanMakanan = "", slkiEdema = "", slkiTekananDarah = "",
            slkiDenyut = "", slkiTekananArteri = "", slkiMembran = "", slkiMata = "", slkiTurgorMembaik = "", slkiBerat = "", slkiSerum = "", slkiKalium = "", slkiKekuatan = "",
            slkiTurgorMeningkat = "", slkiOutput = "", sikiPeriksa = "", sikiMonitor = "", sikiHitung = "", sikiBerikanPosisi = "", sikiBerikanAsupan = "", sikiAnjurMemperbanyak = "",
            sikiAnjurMenghindari = "", sikiKolabNacl = "", sikiKolabGlukosa = "", sikiKolabKoloid = "", sikiKolabProduk = "", sttsRawat = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMMasalahKeperawatanHipovolemia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "waktu_simpan", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Simpan", "Nama Petugas",
            "sdki_kehilangan", "sdki_kegagalan", "sdki_peningkatan", "sdki_kekurangan", "sdki_evaporasi", "sdki_frekuensi", "sdki_nadi", "sdki_tekanan_darah",
            "sdki_tekanan_nadi", "sdki_turgor", "sdki_membran", "sdki_volume", "sdki_hematokrit", "sdki_merasa", "sdki_mengeluh", "sdki_pengisian", "sdki_status",
            "sdki_suhu", "sdki_konsentrasi", "sdki_berat", "sdki_trauma", "sdki_luka", "sdki_aids", "sdki_muntah", "sdki_diare", "sdki_hipoalbumin", "slki_status",
            "slki_elektrolit", "slki_cairan", "ket_selama", "slki_asupan_cairan", "slki_haluaran", "slki_kelembapan", "slki_asupan_makanan", "slki_edema",
            "slki_tekanan_darah", "slki_denyut", "slki_tekanan_arteri", "slki_membran", "slki_mata", "slki_turgor_membaik", "slki_berat", "slki_serum", "slki_kalium",
            "slki_kekuatan", "slki_turgor_meningkat", "slki_output", "siki_periksa", "siki_monitor", "siki_hitung", "siki_berikan_posisi", "siki_berikan_asupan",
            "siki_anjur_memperbanyak", "siki_anjur_menghindari", "siki_kolab_nacl", "siki_kolab_glukosa", "siki_kolab_koloid", "siki_kolab_produk", "status_rawat",
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

        for (i = 0; i < 68; i++) {
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
                if (akses.getform().equals("RMMasalahKeperawatanHipovolemia")) {
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
        chkSdkiKehilangan = new widget.CekBox();
        chkSdkiKegagalan = new widget.CekBox();
        chkSdkiPeningkatan = new widget.CekBox();
        chkSdkiKekurangan = new widget.CekBox();
        chkSdkiEvaporasi = new widget.CekBox();
        chkSdkiTrauma = new widget.CekBox();
        chkSdkiLuka = new widget.CekBox();
        chkSdkiAids = new widget.CekBox();
        chkSlkiStatus = new widget.CekBox();
        jLabel74 = new widget.Label();
        jLabel75 = new widget.Label();
        jLabel76 = new widget.Label();
        chkSdkiFrekuensi = new widget.CekBox();
        chkSdkiTekananNadi = new widget.CekBox();
        chkSdkiVolume = new widget.CekBox();
        jLabel78 = new widget.Label();
        jLabel79 = new widget.Label();
        TketSelama = new widget.TextBox();
        chkSdkiMuntah = new widget.CekBox();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        chkSlkiElektrolit = new widget.CekBox();
        chkSikiPeriksa = new widget.CekBox();
        chkSikiMonitor = new widget.CekBox();
        jLabel83 = new widget.Label();
        chkSikiHitung = new widget.CekBox();
        chkSikiBerikanPosisi = new widget.CekBox();
        chkSikiBerikanAsupan = new widget.CekBox();
        jLabel84 = new widget.Label();
        chkSikiAnjurMemperbanyak = new widget.CekBox();
        jLabel91 = new widget.Label();
        jLabel92 = new widget.Label();
        chkSdkiNadi = new widget.CekBox();
        chkSdkiTekananDarah = new widget.CekBox();
        chkSdkiTurgor = new widget.CekBox();
        chkSdkiMembran = new widget.CekBox();
        chkSdkiHematokrit = new widget.CekBox();
        jLabel77 = new widget.Label();
        chkSdkiMerasa = new widget.CekBox();
        chkSdkiMengeluh = new widget.CekBox();
        chkSdkiPengisian = new widget.CekBox();
        chkSdkiStatus = new widget.CekBox();
        chkSdkiSuhu = new widget.CekBox();
        chkSdkiKonsentrasi = new widget.CekBox();
        chkSdkiBerat = new widget.CekBox();
        chkSdkiDiare = new widget.CekBox();
        chkSdkiHipoalbumin = new widget.CekBox();
        chkSlkiCairan = new widget.CekBox();
        chkSlkiAsupanCairan = new widget.CekBox();
        chkSlkiHaluaran = new widget.CekBox();
        chkSlkiKelembapan = new widget.CekBox();
        chkSlkiAsupanMakanan = new widget.CekBox();
        chkSlkiEdema = new widget.CekBox();
        chkSlkiTekananDarah = new widget.CekBox();
        chkSlkiDenyut = new widget.CekBox();
        chkSlkiTekananArteri = new widget.CekBox();
        chkSlkiMembran = new widget.CekBox();
        chkSlkiMata = new widget.CekBox();
        chkSlkiTurgorMembaik = new widget.CekBox();
        chkSlkiBerat = new widget.CekBox();
        chkSlkiSerum = new widget.CekBox();
        chkSlkiKalium = new widget.CekBox();
        chkSlkiKekuatan = new widget.CekBox();
        chkSlkiTurgorMeningkat = new widget.CekBox();
        chkSlkiOutput = new widget.CekBox();
        chkSikiAnjurMenghindari = new widget.CekBox();
        jLabel85 = new widget.Label();
        chkSikiKolabNacl = new widget.CekBox();
        chkSikiKolabKoloid = new widget.CekBox();
        chkSikiKolabGlukosa = new widget.CekBox();
        chkSikiKolabProduk = new widget.CekBox();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Masalah Keperawatan Resiko Hipovolemia ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        chkSdkiKehilangan.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKehilangan.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKehilangan.setText("Kehilangan cairan aktif");
        chkSdkiKehilangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKehilangan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKehilangan.setName("chkSdkiKehilangan"); // NOI18N
        chkSdkiKehilangan.setOpaque(false);
        chkSdkiKehilangan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKehilangan);
        chkSdkiKehilangan.setBounds(145, 122, 150, 23);

        chkSdkiKegagalan.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKegagalan.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKegagalan.setText("Kegagalan mekanisme regulasi");
        chkSdkiKegagalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKegagalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKegagalan.setName("chkSdkiKegagalan"); // NOI18N
        chkSdkiKegagalan.setOpaque(false);
        chkSdkiKegagalan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKegagalan);
        chkSdkiKegagalan.setBounds(145, 150, 180, 23);

        chkSdkiPeningkatan.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPeningkatan.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPeningkatan.setText("Peningkatan permeabilitas kapiler");
        chkSdkiPeningkatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPeningkatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPeningkatan.setName("chkSdkiPeningkatan"); // NOI18N
        chkSdkiPeningkatan.setOpaque(false);
        chkSdkiPeningkatan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPeningkatan);
        chkSdkiPeningkatan.setBounds(145, 178, 200, 23);

        chkSdkiKekurangan.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKekurangan.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKekurangan.setText("Kekurangan intake cairan");
        chkSdkiKekurangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKekurangan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKekurangan.setName("chkSdkiKekurangan"); // NOI18N
        chkSdkiKekurangan.setOpaque(false);
        chkSdkiKekurangan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKekurangan);
        chkSdkiKekurangan.setBounds(145, 206, 160, 23);

        chkSdkiEvaporasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiEvaporasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiEvaporasi.setText("Evaporasi");
        chkSdkiEvaporasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiEvaporasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiEvaporasi.setName("chkSdkiEvaporasi"); // NOI18N
        chkSdkiEvaporasi.setOpaque(false);
        chkSdkiEvaporasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiEvaporasi);
        chkSdkiEvaporasi.setBounds(145, 234, 90, 23);

        chkSdkiTrauma.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTrauma.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTrauma.setText("Trauma/perdarahan");
        chkSdkiTrauma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTrauma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTrauma.setName("chkSdkiTrauma"); // NOI18N
        chkSdkiTrauma.setOpaque(false);
        chkSdkiTrauma.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSdkiTrauma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSdkiTraumaActionPerformed(evt);
            }
        });
        FormInput.add(chkSdkiTrauma);
        chkSdkiTrauma.setBounds(145, 486, 140, 23);

        chkSdkiLuka.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiLuka.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiLuka.setText("Luka bakar");
        chkSdkiLuka.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiLuka.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiLuka.setName("chkSdkiLuka"); // NOI18N
        chkSdkiLuka.setOpaque(false);
        chkSdkiLuka.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiLuka);
        chkSdkiLuka.setBounds(145, 514, 120, 23);

        chkSdkiAids.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiAids.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiAids.setText("AIDS");
        chkSdkiAids.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiAids.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiAids.setName("chkSdkiAids"); // NOI18N
        chkSdkiAids.setOpaque(false);
        chkSdkiAids.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiAids);
        chkSdkiAids.setBounds(300, 486, 70, 23);

        chkSlkiStatus.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiStatus.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiStatus.setText("Status cairan (L.03028)");
        chkSlkiStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiStatus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiStatus.setName("chkSlkiStatus"); // NOI18N
        chkSlkiStatus.setOpaque(false);
        chkSlkiStatus.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiStatus);
        chkSlkiStatus.setBounds(145, 570, 150, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Diagnosis Keperawatan SDKI : Hipovolemia");
        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 66, 290, 23);

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
        jLabel76.setBounds(145, 262, 210, 23);

        chkSdkiFrekuensi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiFrekuensi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiFrekuensi.setText("Frekuensi nadi meningkat");
        chkSdkiFrekuensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiFrekuensi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiFrekuensi.setName("chkSdkiFrekuensi"); // NOI18N
        chkSdkiFrekuensi.setOpaque(false);
        chkSdkiFrekuensi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiFrekuensi);
        chkSdkiFrekuensi.setBounds(145, 290, 160, 23);

        chkSdkiTekananNadi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTekananNadi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTekananNadi.setText("Tekanan nadi menyempit");
        chkSdkiTekananNadi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTekananNadi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTekananNadi.setName("chkSdkiTekananNadi"); // NOI18N
        chkSdkiTekananNadi.setOpaque(false);
        chkSdkiTekananNadi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTekananNadi);
        chkSdkiTekananNadi.setBounds(330, 290, 160, 23);

        chkSdkiVolume.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiVolume.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiVolume.setText("Volume urin meningkat");
        chkSdkiVolume.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiVolume.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiVolume.setName("chkSdkiVolume"); // NOI18N
        chkSdkiVolume.setOpaque(false);
        chkSdkiVolume.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiVolume);
        chkSdkiVolume.setBounds(510, 290, 150, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("Kondisi Klinis Terkait :");
        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(145, 458, 140, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("(Rencana Keperawatan) Tujuan dan Kriteria Hasil SLKI :");
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 542, 370, 23);

        TketSelama.setForeground(new java.awt.Color(0, 0, 0));
        TketSelama.setName("TketSelama"); // NOI18N
        FormInput.add(TketSelama);
        TketSelama.setBounds(380, 654, 150, 23);

        chkSdkiMuntah.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMuntah.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMuntah.setText("Muntah");
        chkSdkiMuntah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMuntah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMuntah.setName("chkSdkiMuntah"); // NOI18N
        chkSdkiMuntah.setOpaque(false);
        chkSdkiMuntah.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMuntah);
        chkSdkiMuntah.setBounds(300, 514, 70, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("(Rencana Keperawatan) Intervensi Keperawatan SIKI : Manajemen Hipovolemia (I. 03116)");
        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 878, 570, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("Tindakan Observasi :");
        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(145, 906, 140, 23);

        chkSlkiElektrolit.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiElektrolit.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiElektrolit.setText("Keseimbangan elektrolit (L.03021)");
        chkSlkiElektrolit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiElektrolit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiElektrolit.setName("chkSlkiElektrolit"); // NOI18N
        chkSlkiElektrolit.setOpaque(false);
        chkSlkiElektrolit.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiElektrolit);
        chkSlkiElektrolit.setBounds(145, 598, 200, 23);

        chkSikiPeriksa.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiPeriksa.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiPeriksa.setText("<html>Periksa tanda dan gejala hipovolemia (mis. Frekuensi nadi meningkat, nadi teraba lemah, tekanan darah menurun, tekanan nadi menyempit, turgor kulit menurun, membran mukosa kering, volume urin menurun, hematokrit meningkat, haus, lemah)</html>");
        chkSikiPeriksa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiPeriksa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiPeriksa.setName("chkSikiPeriksa"); // NOI18N
        chkSikiPeriksa.setOpaque(false);
        chkSikiPeriksa.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiPeriksa);
        chkSikiPeriksa.setBounds(145, 934, 610, 45);

        chkSikiMonitor.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiMonitor.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiMonitor.setText("Monitor intake dan output cairan");
        chkSikiMonitor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiMonitor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiMonitor.setName("chkSikiMonitor"); // NOI18N
        chkSikiMonitor.setOpaque(false);
        chkSikiMonitor.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiMonitor);
        chkSikiMonitor.setBounds(145, 984, 210, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("Terapeutik :");
        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(145, 1012, 90, 23);

        chkSikiHitung.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiHitung.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiHitung.setText("Hitung kebutuhan cairan");
        chkSikiHitung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiHitung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiHitung.setName("chkSikiHitung"); // NOI18N
        chkSikiHitung.setOpaque(false);
        chkSikiHitung.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiHitung);
        chkSikiHitung.setBounds(145, 1040, 160, 23);

        chkSikiBerikanPosisi.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiBerikanPosisi.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiBerikanPosisi.setText("<html>Berikan posisi <i>modified Tredelenburg</i></html>");
        chkSikiBerikanPosisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiBerikanPosisi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiBerikanPosisi.setName("chkSikiBerikanPosisi"); // NOI18N
        chkSikiBerikanPosisi.setOpaque(false);
        chkSikiBerikanPosisi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiBerikanPosisi);
        chkSikiBerikanPosisi.setBounds(145, 1068, 210, 23);

        chkSikiBerikanAsupan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiBerikanAsupan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiBerikanAsupan.setText("Berikan asupan cairan oral");
        chkSikiBerikanAsupan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiBerikanAsupan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiBerikanAsupan.setName("chkSikiBerikanAsupan"); // NOI18N
        chkSikiBerikanAsupan.setOpaque(false);
        chkSikiBerikanAsupan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiBerikanAsupan);
        chkSikiBerikanAsupan.setBounds(145, 1096, 160, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("Edukasi :");
        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(145, 1124, 90, 23);

        chkSikiAnjurMemperbanyak.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAnjurMemperbanyak.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAnjurMemperbanyak.setText("Anjurkan memperbanyak asupan cairan oral");
        chkSikiAnjurMemperbanyak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAnjurMemperbanyak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAnjurMemperbanyak.setName("chkSikiAnjurMemperbanyak"); // NOI18N
        chkSikiAnjurMemperbanyak.setOpaque(false);
        chkSikiAnjurMemperbanyak.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAnjurMemperbanyak);
        chkSikiAnjurMemperbanyak.setBounds(145, 1152, 240, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel91.setText("Setelah dilakukan tindakan keperawatan selama");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(145, 654, 235, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel92.setText("jam, hipovolemia teratasi dengan kriteria :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(535, 654, 220, 23);

        chkSdkiNadi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiNadi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiNadi.setText("Nadi teraba lemah");
        chkSdkiNadi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiNadi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiNadi.setName("chkSdkiNadi"); // NOI18N
        chkSdkiNadi.setOpaque(false);
        chkSdkiNadi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiNadi);
        chkSdkiNadi.setBounds(145, 318, 160, 23);

        chkSdkiTekananDarah.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTekananDarah.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTekananDarah.setText("Tekanan darah meningkat");
        chkSdkiTekananDarah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTekananDarah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTekananDarah.setName("chkSdkiTekananDarah"); // NOI18N
        chkSdkiTekananDarah.setOpaque(false);
        chkSdkiTekananDarah.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTekananDarah);
        chkSdkiTekananDarah.setBounds(145, 346, 160, 23);

        chkSdkiTurgor.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTurgor.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTurgor.setText("Turgor kulit menurun");
        chkSdkiTurgor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTurgor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTurgor.setName("chkSdkiTurgor"); // NOI18N
        chkSdkiTurgor.setOpaque(false);
        chkSdkiTurgor.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTurgor);
        chkSdkiTurgor.setBounds(330, 318, 160, 23);

        chkSdkiMembran.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMembran.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMembran.setText("Membran mukosa kering");
        chkSdkiMembran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMembran.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMembran.setName("chkSdkiMembran"); // NOI18N
        chkSdkiMembran.setOpaque(false);
        chkSdkiMembran.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMembran);
        chkSdkiMembran.setBounds(330, 346, 160, 23);

        chkSdkiHematokrit.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiHematokrit.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiHematokrit.setText("Hematokrit meningkat");
        chkSdkiHematokrit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiHematokrit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiHematokrit.setName("chkSdkiHematokrit"); // NOI18N
        chkSdkiHematokrit.setOpaque(false);
        chkSdkiHematokrit.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiHematokrit);
        chkSdkiHematokrit.setBounds(510, 318, 140, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel77.setText("DO/DS (Gejala dan tanda Minor)");
        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(145, 374, 210, 23);

        chkSdkiMerasa.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMerasa.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMerasa.setText("Merasa lamah");
        chkSdkiMerasa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMerasa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMerasa.setName("chkSdkiMerasa"); // NOI18N
        chkSdkiMerasa.setOpaque(false);
        chkSdkiMerasa.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMerasa);
        chkSdkiMerasa.setBounds(145, 402, 110, 23);

        chkSdkiMengeluh.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMengeluh.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMengeluh.setText("Mengeluh haus");
        chkSdkiMengeluh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMengeluh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMengeluh.setName("chkSdkiMengeluh"); // NOI18N
        chkSdkiMengeluh.setOpaque(false);
        chkSdkiMengeluh.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMengeluh);
        chkSdkiMengeluh.setBounds(145, 430, 110, 23);

        chkSdkiPengisian.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPengisian.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPengisian.setText("Pengisian vena menurun");
        chkSdkiPengisian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPengisian.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPengisian.setName("chkSdkiPengisian"); // NOI18N
        chkSdkiPengisian.setOpaque(false);
        chkSdkiPengisian.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPengisian);
        chkSdkiPengisian.setBounds(270, 402, 150, 23);

        chkSdkiStatus.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiStatus.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiStatus.setText("Status mental berubah");
        chkSdkiStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiStatus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiStatus.setName("chkSdkiStatus"); // NOI18N
        chkSdkiStatus.setOpaque(false);
        chkSdkiStatus.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiStatus);
        chkSdkiStatus.setBounds(270, 430, 150, 23);

        chkSdkiSuhu.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiSuhu.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiSuhu.setText("Suhu tubuh meningkat");
        chkSdkiSuhu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiSuhu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiSuhu.setName("chkSdkiSuhu"); // NOI18N
        chkSdkiSuhu.setOpaque(false);
        chkSdkiSuhu.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiSuhu);
        chkSdkiSuhu.setBounds(440, 402, 150, 23);

        chkSdkiKonsentrasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKonsentrasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKonsentrasi.setText("Konsentrasi urin meningkat");
        chkSdkiKonsentrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKonsentrasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKonsentrasi.setName("chkSdkiKonsentrasi"); // NOI18N
        chkSdkiKonsentrasi.setOpaque(false);
        chkSdkiKonsentrasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKonsentrasi);
        chkSdkiKonsentrasi.setBounds(440, 430, 170, 23);

        chkSdkiBerat.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiBerat.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiBerat.setText("Berat badan turun tiba-tiba");
        chkSdkiBerat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiBerat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiBerat.setName("chkSdkiBerat"); // NOI18N
        chkSdkiBerat.setOpaque(false);
        chkSdkiBerat.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiBerat);
        chkSdkiBerat.setBounds(610, 402, 170, 23);

        chkSdkiDiare.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiDiare.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiDiare.setText("Diare");
        chkSdkiDiare.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiDiare.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiDiare.setName("chkSdkiDiare"); // NOI18N
        chkSdkiDiare.setOpaque(false);
        chkSdkiDiare.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiDiare);
        chkSdkiDiare.setBounds(390, 486, 70, 23);

        chkSdkiHipoalbumin.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiHipoalbumin.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiHipoalbumin.setText("Hipoalbuminemia");
        chkSdkiHipoalbumin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiHipoalbumin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiHipoalbumin.setName("chkSdkiHipoalbumin"); // NOI18N
        chkSdkiHipoalbumin.setOpaque(false);
        chkSdkiHipoalbumin.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiHipoalbumin);
        chkSdkiHipoalbumin.setBounds(390, 514, 120, 23);

        chkSlkiCairan.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiCairan.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiCairan.setText("Keseimbangan Cairan (L.03020)");
        chkSlkiCairan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiCairan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiCairan.setName("chkSlkiCairan"); // NOI18N
        chkSlkiCairan.setOpaque(false);
        chkSlkiCairan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiCairan);
        chkSlkiCairan.setBounds(145, 626, 200, 23);

        chkSlkiAsupanCairan.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiAsupanCairan.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiAsupanCairan.setText("Asupan cairan meningkat");
        chkSlkiAsupanCairan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiAsupanCairan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiAsupanCairan.setName("chkSlkiAsupanCairan"); // NOI18N
        chkSlkiAsupanCairan.setOpaque(false);
        chkSlkiAsupanCairan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiAsupanCairan);
        chkSlkiAsupanCairan.setBounds(145, 682, 170, 23);

        chkSlkiHaluaran.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiHaluaran.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiHaluaran.setText("Haluaran urin meningkat");
        chkSlkiHaluaran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiHaluaran.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiHaluaran.setName("chkSlkiHaluaran"); // NOI18N
        chkSlkiHaluaran.setOpaque(false);
        chkSlkiHaluaran.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiHaluaran);
        chkSlkiHaluaran.setBounds(145, 710, 170, 23);

        chkSlkiKelembapan.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKelembapan.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKelembapan.setText("Kelembaban membran mukosa meningkat");
        chkSlkiKelembapan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKelembapan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKelembapan.setName("chkSlkiKelembapan"); // NOI18N
        chkSlkiKelembapan.setOpaque(false);
        chkSlkiKelembapan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKelembapan);
        chkSlkiKelembapan.setBounds(145, 738, 230, 23);

        chkSlkiAsupanMakanan.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiAsupanMakanan.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiAsupanMakanan.setText("Asupan makanan meningkat");
        chkSlkiAsupanMakanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiAsupanMakanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiAsupanMakanan.setName("chkSlkiAsupanMakanan"); // NOI18N
        chkSlkiAsupanMakanan.setOpaque(false);
        chkSlkiAsupanMakanan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiAsupanMakanan);
        chkSlkiAsupanMakanan.setBounds(145, 766, 170, 23);

        chkSlkiEdema.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiEdema.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiEdema.setText("Edema, Dehidrasi, asites, Konfusi menurun");
        chkSlkiEdema.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiEdema.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiEdema.setName("chkSlkiEdema"); // NOI18N
        chkSlkiEdema.setOpaque(false);
        chkSlkiEdema.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiEdema);
        chkSlkiEdema.setBounds(145, 794, 240, 23);

        chkSlkiTekananDarah.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiTekananDarah.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiTekananDarah.setText("Tekanan darah membaik");
        chkSlkiTekananDarah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiTekananDarah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiTekananDarah.setName("chkSlkiTekananDarah"); // NOI18N
        chkSlkiTekananDarah.setOpaque(false);
        chkSlkiTekananDarah.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiTekananDarah);
        chkSlkiTekananDarah.setBounds(145, 822, 170, 23);

        chkSlkiDenyut.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiDenyut.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiDenyut.setText("Denyut nadi radial membaik");
        chkSlkiDenyut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiDenyut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiDenyut.setName("chkSlkiDenyut"); // NOI18N
        chkSlkiDenyut.setOpaque(false);
        chkSlkiDenyut.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiDenyut);
        chkSlkiDenyut.setBounds(145, 850, 170, 23);

        chkSlkiTekananArteri.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiTekananArteri.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiTekananArteri.setText("Tekanan arteri rata-rata membaik");
        chkSlkiTekananArteri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiTekananArteri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiTekananArteri.setName("chkSlkiTekananArteri"); // NOI18N
        chkSlkiTekananArteri.setOpaque(false);
        chkSlkiTekananArteri.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiTekananArteri);
        chkSlkiTekananArteri.setBounds(400, 682, 190, 23);

        chkSlkiMembran.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiMembran.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiMembran.setText("Membran mukosa membaik");
        chkSlkiMembran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiMembran.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiMembran.setName("chkSlkiMembran"); // NOI18N
        chkSlkiMembran.setOpaque(false);
        chkSlkiMembran.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiMembran);
        chkSlkiMembran.setBounds(400, 710, 170, 23);

        chkSlkiMata.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiMata.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiMata.setText("Mata cekung membaik");
        chkSlkiMata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiMata.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiMata.setName("chkSlkiMata"); // NOI18N
        chkSlkiMata.setOpaque(false);
        chkSlkiMata.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiMata);
        chkSlkiMata.setBounds(400, 738, 160, 23);

        chkSlkiTurgorMembaik.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiTurgorMembaik.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiTurgorMembaik.setText("Turgor kulit membaik");
        chkSlkiTurgorMembaik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiTurgorMembaik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiTurgorMembaik.setName("chkSlkiTurgorMembaik"); // NOI18N
        chkSlkiTurgorMembaik.setOpaque(false);
        chkSlkiTurgorMembaik.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiTurgorMembaik);
        chkSlkiTurgorMembaik.setBounds(400, 766, 170, 23);

        chkSlkiBerat.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiBerat.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiBerat.setText("Berat badan membaik");
        chkSlkiBerat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiBerat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiBerat.setName("chkSlkiBerat"); // NOI18N
        chkSlkiBerat.setOpaque(false);
        chkSlkiBerat.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiBerat);
        chkSlkiBerat.setBounds(400, 794, 170, 23);

        chkSlkiSerum.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiSerum.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiSerum.setText("Serum Natrium meningkat");
        chkSlkiSerum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiSerum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiSerum.setName("chkSlkiSerum"); // NOI18N
        chkSlkiSerum.setOpaque(false);
        chkSlkiSerum.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiSerum);
        chkSlkiSerum.setBounds(400, 822, 170, 23);

        chkSlkiKalium.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKalium.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKalium.setText("Kalium dan Klorida meningkat");
        chkSlkiKalium.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKalium.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKalium.setName("chkSlkiKalium"); // NOI18N
        chkSlkiKalium.setOpaque(false);
        chkSlkiKalium.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKalium);
        chkSlkiKalium.setBounds(400, 850, 170, 23);

        chkSlkiKekuatan.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKekuatan.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKekuatan.setText("Kekuatan nadi meningkat");
        chkSlkiKekuatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKekuatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKekuatan.setName("chkSlkiKekuatan"); // NOI18N
        chkSlkiKekuatan.setOpaque(false);
        chkSlkiKekuatan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKekuatan);
        chkSlkiKekuatan.setBounds(600, 682, 155, 23);

        chkSlkiTurgorMeningkat.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiTurgorMeningkat.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiTurgorMeningkat.setText("Turgor kulit meningkat");
        chkSlkiTurgorMeningkat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiTurgorMeningkat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiTurgorMeningkat.setName("chkSlkiTurgorMeningkat"); // NOI18N
        chkSlkiTurgorMeningkat.setOpaque(false);
        chkSlkiTurgorMeningkat.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiTurgorMeningkat);
        chkSlkiTurgorMeningkat.setBounds(600, 710, 155, 23);

        chkSlkiOutput.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiOutput.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiOutput.setText("Output urine meningkat");
        chkSlkiOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiOutput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiOutput.setName("chkSlkiOutput"); // NOI18N
        chkSlkiOutput.setOpaque(false);
        chkSlkiOutput.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiOutput);
        chkSlkiOutput.setBounds(600, 738, 155, 23);

        chkSikiAnjurMenghindari.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAnjurMenghindari.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAnjurMenghindari.setText("Anjurkan menghindari perubahan posisi mendadak");
        chkSikiAnjurMenghindari.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAnjurMenghindari.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAnjurMenghindari.setName("chkSikiAnjurMenghindari"); // NOI18N
        chkSikiAnjurMenghindari.setOpaque(false);
        chkSikiAnjurMenghindari.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAnjurMenghindari);
        chkSikiAnjurMenghindari.setBounds(400, 1152, 280, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel85.setText("Kolaborasi :");
        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(145, 1180, 90, 23);

        chkSikiKolabNacl.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiKolabNacl.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiKolabNacl.setText("Kolaborasi pemberian cairan IV isotonis (mis. NaCl, RL)");
        chkSikiKolabNacl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiKolabNacl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiKolabNacl.setName("chkSikiKolabNacl"); // NOI18N
        chkSikiKolabNacl.setOpaque(false);
        chkSikiKolabNacl.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiKolabNacl);
        chkSikiKolabNacl.setBounds(145, 1208, 290, 23);

        chkSikiKolabKoloid.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiKolabKoloid.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiKolabKoloid.setText("Kolaborasi pemberian cairan koloid (mis. Albumin)");
        chkSikiKolabKoloid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiKolabKoloid.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiKolabKoloid.setName("chkSikiKolabKoloid"); // NOI18N
        chkSikiKolabKoloid.setOpaque(false);
        chkSikiKolabKoloid.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiKolabKoloid);
        chkSikiKolabKoloid.setBounds(540, 1208, 270, 23);

        chkSikiKolabGlukosa.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiKolabGlukosa.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiKolabGlukosa.setText("Kolaborasi pemberian cairan IV isotonis (mis. Glukosa 2, 5%, NaCl 0,4 %)");
        chkSikiKolabGlukosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiKolabGlukosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiKolabGlukosa.setName("chkSikiKolabGlukosa"); // NOI18N
        chkSikiKolabGlukosa.setOpaque(false);
        chkSikiKolabGlukosa.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiKolabGlukosa);
        chkSikiKolabGlukosa.setBounds(145, 1236, 380, 23);

        chkSikiKolabProduk.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiKolabProduk.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiKolabProduk.setText("Kolaborasi pemberian produk darah");
        chkSikiKolabProduk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiKolabProduk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiKolabProduk.setName("chkSikiKolabProduk"); // NOI18N
        chkSikiKolabProduk.setOpaque(false);
        chkSikiKolabProduk.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiKolabProduk);
        chkSikiKolabProduk.setBounds(540, 1236, 200, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass13.add(Scroll1);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data MasKep Resiko Hipovolemia ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
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

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-07-2026" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-07-2026" }));
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
            if (Sequel.menyimpantf("masalah_keperawatan_hipovolemia", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 63, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), sdkiKehilangan, sdkiKegagalan, sdkiPeningkatan, sdkiKekurangan, sdkiEvaporasi, sdkiFrekuensi, sdkiNadi,
                        sdkiTekananDarah, sdkiTekananNadi, sdkiTurgor, sdkiMembran, sdkiVolume, sdkiHematokrit, sdkiMerasa, sdkiMengeluh, sdkiPengisian, sdkiStatus,
                        sdkiSuhu, sdkiKonsentrasi, sdkiBerat, sdkiTrauma, sdkiLuka, sdkiAids, sdkiMuntah, sdkiDiare, sdkiHipoalbumin, slkiStatus, slkiElektrolit,
                        slkiCairan, TketSelama.getText(), slkiAsupanCairan, slkiHaluaran, slkiKelembapan, slkiAsupanMakanan, slkiEdema, slkiTekananDarah, slkiDenyut,
                        slkiTekananArteri, slkiMembran, slkiMata, slkiTurgorMembaik, slkiBerat, slkiSerum, slkiKalium, slkiKekuatan, slkiTurgorMeningkat, slkiOutput,
                        sikiPeriksa, sikiMonitor, sikiHitung, sikiBerikanPosisi, sikiBerikanAsupan, sikiAnjurMemperbanyak, sikiAnjurMenghindari, sikiKolabNacl, sikiKolabGlukosa,
                        sikiKolabKoloid, sikiKolabProduk, sttsRawat, TnipPetugas.getText(), Sequel.cariIsi("select now()")
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Masalah Keperawatan Resiko Hipovolemia", "Simpan");
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
                if (Sequel.mengedittf("masalah_keperawatan_hipovolemia", "waktu_simpan=?", "sdki_kehilangan=?, sdki_kegagalan=?, sdki_peningkatan=?, sdki_kekurangan=?, "
                        + "sdki_evaporasi=?, sdki_frekuensi=?, sdki_nadi=?, sdki_tekanan_darah=?, sdki_tekanan_nadi=?, sdki_turgor=?, sdki_membran=?, sdki_volume=?, sdki_hematokrit=?, "
                        + "sdki_merasa=?, sdki_mengeluh=?, sdki_pengisian=?, sdki_status=?, sdki_suhu=?, sdki_konsentrasi=?, sdki_berat=?, sdki_trauma=?, sdki_luka=?, sdki_aids=?, "
                        + "sdki_muntah=?, sdki_diare=?, sdki_hipoalbumin=?, slki_status=?, slki_elektrolit=?, slki_cairan=?, ket_selama=?, slki_asupan_cairan=?, slki_haluaran=?, "
                        + "slki_kelembapan=?, slki_asupan_makanan=?, slki_edema=?, slki_tekanan_darah=?, slki_denyut=?, slki_tekanan_arteri=?, slki_membran=?, slki_mata=?, "
                        + "slki_turgor_membaik=?, slki_berat=?, slki_serum=?, slki_kalium=?, slki_kekuatan=?, slki_turgor_meningkat=?, slki_output=?, siki_periksa=?, siki_monitor=?, "
                        + "siki_hitung=?, siki_berikan_posisi=?, siki_berikan_asupan=?, siki_anjur_memperbanyak=?, siki_anjur_menghindari=?, siki_kolab_nacl=?, siki_kolab_glukosa=?, "
                        + "siki_kolab_koloid=?, siki_kolab_produk=?, nip_petugas=?", 60, new String[]{
                            sdkiKehilangan, sdkiKegagalan, sdkiPeningkatan, sdkiKekurangan, sdkiEvaporasi, sdkiFrekuensi, sdkiNadi, sdkiTekananDarah, sdkiTekananNadi, sdkiTurgor,
                            sdkiMembran, sdkiVolume, sdkiHematokrit, sdkiMerasa, sdkiMengeluh, sdkiPengisian, sdkiStatus, sdkiSuhu, sdkiKonsentrasi, sdkiBerat, sdkiTrauma, sdkiLuka,
                            sdkiAids, sdkiMuntah, sdkiDiare, sdkiHipoalbumin, slkiStatus, slkiElektrolit, slkiCairan, TketSelama.getText(), slkiAsupanCairan, slkiHaluaran,
                            slkiKelembapan, slkiAsupanMakanan, slkiEdema, slkiTekananDarah, slkiDenyut, slkiTekananArteri, slkiMembran, slkiMata, slkiTurgorMembaik, slkiBerat,
                            slkiSerum, slkiKalium, slkiKekuatan, slkiTurgorMeningkat, slkiOutput, sikiPeriksa, sikiMonitor, sikiHitung, sikiBerikanPosisi, sikiBerikanAsupan,
                            sikiAnjurMemperbanyak, sikiAnjurMenghindari, sikiKolabNacl, sikiKolabGlukosa, sikiKolabKoloid, sikiKolabProduk, TnipPetugas.getText(),
                            tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 0).toString()
                        }) == true) {

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Masalah Keperawatan Resiko Hipovolemia", "Ganti");
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
        akses.setform("RMMasalahKeperawatanHipovolemia");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbMasalah.getSelectedRow() > -1) {
            if (akses.getadmin() == true || tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 67).toString().equals(akses.getkode())) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from masalah_keperawatan_hipovolemia where waktu_simpan=?", 1, new String[]{
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

            if (chkSdkiKehilangan.isSelected() == true) {
                param.put("sdkiKehilangan", "V");
            } else {
                param.put("sdkiKehilangan", "");
            }

            if (chkSdkiKegagalan.isSelected() == true) {
                param.put("sdkiKegagalan", "V");
            } else {
                param.put("sdkiKegagalan", "");
            }

            if (chkSdkiPeningkatan.isSelected() == true) {
                param.put("sdkiPeningkatan", "V");
            } else {
                param.put("sdkiPeningkatan", "");
            }

            if (chkSdkiKekurangan.isSelected() == true) {
                param.put("sdkiKekurangan", "V");
            } else {
                param.put("sdkiKekurangan", "");
            }

            if (chkSdkiEvaporasi.isSelected() == true) {
                param.put("sdkiEvaporasi", "V");
            } else {
                param.put("sdkiEvaporasi", "");
            }

            if (chkSdkiFrekuensi.isSelected() == true) {
                param.put("sdkiFrekuensi", "V");
            } else {
                param.put("sdkiFrekuensi", "");
            }

            if (chkSdkiNadi.isSelected() == true) {
                param.put("sdkiNadi", "V");
            } else {
                param.put("sdkiNadi", "");
            }

            if (chkSdkiTekananDarah.isSelected() == true) {
                param.put("sdkiTekananDarah", "V");
            } else {
                param.put("sdkiTekananDarah", "");
            }

            if (chkSdkiTekananNadi.isSelected() == true) {
                param.put("sdkiTekananNadi", "V");
            } else {
                param.put("sdkiTekananNadi", "");
            }

            if (chkSdkiTurgor.isSelected() == true) {
                param.put("sdkiTurgor", "V");
            } else {
                param.put("sdkiTurgor", "");
            }

            if (chkSdkiMembran.isSelected() == true) {
                param.put("sdkiMembran", "V");
            } else {
                param.put("sdkiMembran", "");
            }

            if (chkSdkiVolume.isSelected() == true) {
                param.put("sdkiVolume", "V");
            } else {
                param.put("sdkiVolume", "");
            }

            if (chkSdkiHematokrit.isSelected() == true) {
                param.put("sdkiHematokrit", "V");
            } else {
                param.put("sdkiHematokrit", "");
            }

            if (chkSdkiMerasa.isSelected() == true) {
                param.put("sdkiMerasa", "V");
            } else {
                param.put("sdkiMerasa", "");
            }

            if (chkSdkiMengeluh.isSelected() == true) {
                param.put("sdkiMengeluh", "V");
            } else {
                param.put("sdkiMengeluh", "");
            }

            if (chkSdkiPengisian.isSelected() == true) {
                param.put("sdkiPengisian", "V");
            } else {
                param.put("sdkiPengisian", "");
            }

            if (chkSdkiStatus.isSelected() == true) {
                param.put("sdkiStatus", "V");
            } else {
                param.put("sdkiStatus", "");
            }

            if (chkSdkiSuhu.isSelected() == true) {
                param.put("sdkiSuhu", "V");
            } else {
                param.put("sdkiSuhu", "");
            }

            if (chkSdkiKonsentrasi.isSelected() == true) {
                param.put("sdkiKonsentrasi", "V");
            } else {
                param.put("sdkiKonsentrasi", "");
            }

            if (chkSdkiBerat.isSelected() == true) {
                param.put("sdkiBerat", "V");
            } else {
                param.put("sdkiBerat", "");
            }

            if (chkSdkiTrauma.isSelected() == true) {
                param.put("sdkiTrauma", "V");
            } else {
                param.put("sdkiTrauma", "");
            }

            if (chkSdkiLuka.isSelected() == true) {
                param.put("sdkiLuka", "V");
            } else {
                param.put("sdkiLuka", "");
            }

            if (chkSdkiAids.isSelected() == true) {
                param.put("sdkiAids", "V");
            } else {
                param.put("sdkiAids", "");
            }

            if (chkSdkiMuntah.isSelected() == true) {
                param.put("sdkiMuntah", "V");
            } else {
                param.put("sdkiMuntah", "");
            }

            if (chkSdkiDiare.isSelected() == true) {
                param.put("sdkiDiare", "V");
            } else {
                param.put("sdkiDiare", "");
            }

            if (chkSdkiHipoalbumin.isSelected() == true) {
                param.put("sdkiHipoalbumin", "V");
            } else {
                param.put("sdkiHipoalbumin", "");
            }

            if (chkSlkiStatus.isSelected() == true) {
                param.put("slkiStatus", "V");
            } else {
                param.put("slkiStatus", "");
            }

            if (chkSlkiElektrolit.isSelected() == true) {
                param.put("slkiElektrolit", "V");
            } else {
                param.put("slkiElektrolit", "");
            }

            if (chkSlkiCairan.isSelected() == true) {
                param.put("slkiCairan", "V");
            } else {
                param.put("slkiCairan", "");
            }

            if (TketSelama.getText().equals("")) {
                param.put("ketSelama", "...........");
            } else {
                param.put("ketSelama", TketSelama.getText());
            }

            if (chkSlkiAsupanCairan.isSelected() == true) {
                param.put("slkiAsupanCairan", "V");
            } else {
                param.put("slkiAsupanCairan", "");
            }

            if (chkSlkiHaluaran.isSelected() == true) {
                param.put("slkiHaluaran", "V");
            } else {
                param.put("slkiHaluaran", "");
            }

            if (chkSlkiKelembapan.isSelected() == true) {
                param.put("slkiKelembapan", "V");
            } else {
                param.put("slkiKelembapan", "");
            }

            if (chkSlkiAsupanMakanan.isSelected() == true) {
                param.put("slkiAsupanMakanan", "V");
            } else {
                param.put("slkiAsupanMakanan", "");
            }

            if (chkSlkiEdema.isSelected() == true) {
                param.put("slkiEdema", "V");
            } else {
                param.put("slkiEdema", "");
            }

            if (chkSlkiTekananDarah.isSelected() == true) {
                param.put("slkiTekananDarah", "V");
            } else {
                param.put("slkiTekananDarah", "");
            }

            if (chkSlkiDenyut.isSelected() == true) {
                param.put("slkiDenyut", "V");
            } else {
                param.put("slkiDenyut", "");
            }

            if (chkSlkiTekananArteri.isSelected() == true) {
                param.put("slkiTekananArteri", "V");
            } else {
                param.put("slkiTekananArteri", "");
            }

            if (chkSlkiMembran.isSelected() == true) {
                param.put("slkiMembran", "V");
            } else {
                param.put("slkiMembran", "");
            }

            if (chkSlkiMata.isSelected() == true) {
                param.put("slkiMata", "V");
            } else {
                param.put("slkiMata", "");
            }

            if (chkSlkiTurgorMembaik.isSelected() == true) {
                param.put("slkiTurgorMembaik", "V");
            } else {
                param.put("slkiTurgorMembaik", "");
            }

            if (chkSlkiBerat.isSelected() == true) {
                param.put("slkiBerat", "V");
            } else {
                param.put("slkiBerat", "");
            }

            if (chkSlkiSerum.isSelected() == true) {
                param.put("slkiSerum", "V");
            } else {
                param.put("slkiSerum", "");
            }

            if (chkSlkiKalium.isSelected() == true) {
                param.put("slkiKalium", "V");
            } else {
                param.put("slkiKalium", "");
            }

            if (chkSlkiKekuatan.isSelected() == true) {
                param.put("slkiKekuatan", "V");
            } else {
                param.put("slkiKekuatan", "");
            }

            if (chkSlkiTurgorMeningkat.isSelected() == true) {
                param.put("slkiTurgorMeningkat", "V");
            } else {
                param.put("slkiTurgorMeningkat", "");
            }

            if (chkSlkiOutput.isSelected() == true) {
                param.put("slkiOutput", "V");
            } else {
                param.put("slkiOutput", "");
            }

            if (chkSikiPeriksa.isSelected() == true) {
                param.put("sikiPeriksa", "V");
            } else {
                param.put("sikiPeriksa", "");
            }

            if (chkSikiMonitor.isSelected() == true) {
                param.put("sikiMonitor", "V");
            } else {
                param.put("sikiMonitor", "");
            }

            if (chkSikiHitung.isSelected() == true) {
                param.put("sikiHitung", "V");
            } else {
                param.put("sikiHitung", "");
            }

            if (chkSikiBerikanPosisi.isSelected() == true) {
                param.put("sikiBerikanPosisi", "V");
            } else {
                param.put("sikiBerikanPosisi", "");
            }

            if (chkSikiBerikanAsupan.isSelected() == true) {
                param.put("sikiBerikanAsupan", "V");
            } else {
                param.put("sikiBerikanAsupan", "");
            }

            if (chkSikiAnjurMemperbanyak.isSelected() == true) {
                param.put("sikiAnjurMemperbanyak", "V");
            } else {
                param.put("sikiAnjurMemperbanyak", "");
            }

            if (chkSikiAnjurMenghindari.isSelected() == true) {
                param.put("sikiAnjurMenghindari", "V");
            } else {
                param.put("sikiAnjurMenghindari", "");
            }

            if (chkSikiKolabNacl.isSelected() == true) {
                param.put("sikiKolabNacl", "V");
            } else {
                param.put("sikiKolabNacl", "");
            }

            if (chkSikiKolabGlukosa.isSelected() == true) {
                param.put("sikiKolabGlukosa", "V");
            } else {
                param.put("sikiKolabGlukosa", "");
            }

            if (chkSikiKolabKoloid.isSelected() == true) {
                param.put("sikiKolabKoloid", "V");
            } else {
                param.put("sikiKolabKoloid", "");
            }

            if (chkSikiKolabProduk.isSelected() == true) {
                param.put("sikiKolabProduk", "V");
            } else {
                param.put("sikiKolabProduk", "");
            }

            Valid.MyReport("rptMasKepHipovolemia.jasper", "report", "::[ RM Masalah Keperawatan Resiko Hipovolemia ]::",
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

    private void chkSdkiTraumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSdkiTraumaActionPerformed
        TketSelama.setText("");
        if (chkSdkiTrauma.isSelected() == true) {
            TketSelama.setEnabled(true);
            TketSelama.requestFocus();
        } else {
            TketSelama.setEnabled(false);
        }
    }//GEN-LAST:event_chkSdkiTraumaActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMMasalahKeperawatanHipovolemia dialog = new RMMasalahKeperawatanHipovolemia(new javax.swing.JFrame(), true);
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
    private widget.CekBox chkSdkiAids;
    private widget.CekBox chkSdkiBerat;
    private widget.CekBox chkSdkiDiare;
    private widget.CekBox chkSdkiEvaporasi;
    private widget.CekBox chkSdkiFrekuensi;
    private widget.CekBox chkSdkiHematokrit;
    private widget.CekBox chkSdkiHipoalbumin;
    private widget.CekBox chkSdkiKegagalan;
    private widget.CekBox chkSdkiKehilangan;
    private widget.CekBox chkSdkiKekurangan;
    private widget.CekBox chkSdkiKonsentrasi;
    private widget.CekBox chkSdkiLuka;
    private widget.CekBox chkSdkiMembran;
    private widget.CekBox chkSdkiMengeluh;
    private widget.CekBox chkSdkiMerasa;
    private widget.CekBox chkSdkiMuntah;
    private widget.CekBox chkSdkiNadi;
    private widget.CekBox chkSdkiPengisian;
    private widget.CekBox chkSdkiPeningkatan;
    private widget.CekBox chkSdkiStatus;
    private widget.CekBox chkSdkiSuhu;
    private widget.CekBox chkSdkiTekananDarah;
    private widget.CekBox chkSdkiTekananNadi;
    private widget.CekBox chkSdkiTrauma;
    private widget.CekBox chkSdkiTurgor;
    private widget.CekBox chkSdkiVolume;
    private widget.CekBox chkSikiAnjurMemperbanyak;
    private widget.CekBox chkSikiAnjurMenghindari;
    private widget.CekBox chkSikiBerikanAsupan;
    private widget.CekBox chkSikiBerikanPosisi;
    private widget.CekBox chkSikiHitung;
    private widget.CekBox chkSikiKolabGlukosa;
    private widget.CekBox chkSikiKolabKoloid;
    private widget.CekBox chkSikiKolabNacl;
    private widget.CekBox chkSikiKolabProduk;
    private widget.CekBox chkSikiMonitor;
    private widget.CekBox chkSikiPeriksa;
    private widget.CekBox chkSlkiAsupanCairan;
    private widget.CekBox chkSlkiAsupanMakanan;
    private widget.CekBox chkSlkiBerat;
    private widget.CekBox chkSlkiCairan;
    private widget.CekBox chkSlkiDenyut;
    private widget.CekBox chkSlkiEdema;
    private widget.CekBox chkSlkiElektrolit;
    private widget.CekBox chkSlkiHaluaran;
    private widget.CekBox chkSlkiKalium;
    private widget.CekBox chkSlkiKekuatan;
    private widget.CekBox chkSlkiKelembapan;
    private widget.CekBox chkSlkiMata;
    private widget.CekBox chkSlkiMembran;
    private widget.CekBox chkSlkiOutput;
    private widget.CekBox chkSlkiSerum;
    private widget.CekBox chkSlkiStatus;
    private widget.CekBox chkSlkiTekananArteri;
    private widget.CekBox chkSlkiTekananDarah;
    private widget.CekBox chkSlkiTurgorMembaik;
    private widget.CekBox chkSlkiTurgorMeningkat;
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
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
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
                    + "pg.nama nmPetugas from masalah_keperawatan_hipovolemia m inner join reg_periksa rp on rp.no_rawat=m.no_rawat "
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
                        rs.getString("sdki_kehilangan"),
                        rs.getString("sdki_kegagalan"),
                        rs.getString("sdki_peningkatan"),
                        rs.getString("sdki_kekurangan"),
                        rs.getString("sdki_evaporasi"),
                        rs.getString("sdki_frekuensi"),
                        rs.getString("sdki_nadi"),
                        rs.getString("sdki_tekanan_darah"),
                        rs.getString("sdki_tekanan_nadi"),
                        rs.getString("sdki_turgor"),
                        rs.getString("sdki_membran"),
                        rs.getString("sdki_volume"),
                        rs.getString("sdki_hematokrit"),
                        rs.getString("sdki_merasa"),
                        rs.getString("sdki_mengeluh"),
                        rs.getString("sdki_pengisian"),
                        rs.getString("sdki_status"),
                        rs.getString("sdki_suhu"),
                        rs.getString("sdki_konsentrasi"),
                        rs.getString("sdki_berat"),
                        rs.getString("sdki_trauma"),
                        rs.getString("sdki_luka"),
                        rs.getString("sdki_aids"),
                        rs.getString("sdki_muntah"),
                        rs.getString("sdki_diare"),
                        rs.getString("sdki_hipoalbumin"),
                        rs.getString("slki_status"),
                        rs.getString("slki_elektrolit"),
                        rs.getString("slki_cairan"),
                        rs.getString("ket_selama"),
                        rs.getString("slki_asupan_cairan"),
                        rs.getString("slki_haluaran"),
                        rs.getString("slki_kelembapan"),
                        rs.getString("slki_asupan_makanan"),
                        rs.getString("slki_edema"),
                        rs.getString("slki_tekanan_darah"),
                        rs.getString("slki_denyut"),
                        rs.getString("slki_tekanan_arteri"),
                        rs.getString("slki_membran"),
                        rs.getString("slki_mata"),
                        rs.getString("slki_turgor_membaik"),
                        rs.getString("slki_berat"),
                        rs.getString("slki_serum"),
                        rs.getString("slki_kalium"),
                        rs.getString("slki_kekuatan"),
                        rs.getString("slki_turgor_meningkat"),
                        rs.getString("slki_output"),
                        rs.getString("siki_periksa"),
                        rs.getString("siki_monitor"),
                        rs.getString("siki_hitung"),
                        rs.getString("siki_berikan_posisi"),
                        rs.getString("siki_berikan_asupan"),
                        rs.getString("siki_anjur_memperbanyak"),
                        rs.getString("siki_anjur_menghindari"),
                        rs.getString("siki_kolab_nacl"),
                        rs.getString("siki_kolab_glukosa"),
                        rs.getString("siki_kolab_koloid"),
                        rs.getString("siki_kolab_produk"),
                        rs.getString("status_rawat"),
                        rs.getString("nip_petugas")
                    });
                }
            } catch (Exception e) {
                System.out.println("rekammedis.RMMasalahKeperawatanHipovolemia.tampil() : " + e);
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
        chkSdkiKehilangan.setSelected(false);
        chkSdkiKegagalan.setSelected(false);
        chkSdkiPeningkatan.setSelected(false);
        chkSdkiKekurangan.setSelected(false);
        chkSdkiEvaporasi.setSelected(false);
        chkSdkiFrekuensi.setSelected(false);
        chkSdkiNadi.setSelected(false);
        chkSdkiTekananDarah.setSelected(false);
        chkSdkiTekananNadi.setSelected(false);
        chkSdkiTurgor.setSelected(false);
        chkSdkiMembran.setSelected(false);
        chkSdkiVolume.setSelected(false);
        chkSdkiHematokrit.setSelected(false);
        chkSdkiMerasa.setSelected(false);
        chkSdkiMengeluh.setSelected(false);
        chkSdkiPengisian.setSelected(false);
        chkSdkiStatus.setSelected(false);
        chkSdkiSuhu.setSelected(false);
        chkSdkiKonsentrasi.setSelected(false);
        chkSdkiBerat.setSelected(false);
        chkSdkiTrauma.setSelected(false);
        chkSdkiLuka.setSelected(false);
        chkSdkiAids.setSelected(false);
        chkSdkiMuntah.setSelected(false);
        chkSdkiDiare.setSelected(false);
        chkSdkiHipoalbumin.setSelected(false);

        chkSlkiStatus.setSelected(false);
        chkSlkiElektrolit.setSelected(false);
        chkSlkiCairan.setSelected(false);
        TketSelama.setText("");
        chkSlkiAsupanCairan.setSelected(false);
        chkSlkiHaluaran.setSelected(false);
        chkSlkiKelembapan.setSelected(false);
        chkSlkiAsupanMakanan.setSelected(false);
        chkSlkiEdema.setSelected(false);
        chkSlkiTekananDarah.setSelected(false);
        chkSlkiDenyut.setSelected(false);
        chkSlkiTekananArteri.setSelected(false);
        chkSlkiMembran.setSelected(false);
        chkSlkiMata.setSelected(false);
        chkSlkiTurgorMembaik.setSelected(false);
        chkSlkiBerat.setSelected(false);
        chkSlkiSerum.setSelected(false);
        chkSlkiKalium.setSelected(false);
        chkSlkiKekuatan.setSelected(false);
        chkSlkiTurgorMeningkat.setSelected(false);
        chkSlkiOutput.setSelected(false);

        chkSikiPeriksa.setSelected(false);
        chkSikiMonitor.setSelected(false);
        chkSikiHitung.setSelected(false);
        chkSikiBerikanPosisi.setSelected(false);
        chkSikiBerikanAsupan.setSelected(false);
        chkSikiAnjurMemperbanyak.setSelected(false);
        chkSikiAnjurMenghindari.setSelected(false);
        chkSikiKolabNacl.setSelected(false);
        chkSikiKolabGlukosa.setSelected(false);
        chkSikiKolabKoloid.setSelected(false);
        chkSikiKolabProduk.setSelected(false);
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

            sdkiKehilangan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 8).toString();
            sdkiKegagalan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 9).toString();
            sdkiPeningkatan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 10).toString();
            sdkiKekurangan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 11).toString();
            sdkiEvaporasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 12).toString();
            sdkiFrekuensi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 13).toString();
            sdkiNadi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 14).toString();
            sdkiTekananDarah = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 15).toString();
            sdkiTekananNadi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 16).toString();
            sdkiTurgor = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 17).toString();
            sdkiMembran = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 18).toString();
            sdkiVolume = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 19).toString();
            sdkiHematokrit = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 20).toString();
            sdkiMerasa = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 21).toString();
            sdkiMengeluh = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 22).toString();
            sdkiPengisian = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 23).toString();
            sdkiStatus = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 24).toString();
            sdkiSuhu = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 25).toString();
            sdkiKonsentrasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 26).toString();
            sdkiBerat = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 27).toString();
            sdkiTrauma = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 28).toString();
            sdkiLuka = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 29).toString();
            sdkiAids = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 30).toString();
            sdkiMuntah = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 31).toString();
            sdkiDiare = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 32).toString();
            sdkiHipoalbumin = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 33).toString();

            slkiStatus = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 34).toString();
            slkiElektrolit = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 35).toString();
            slkiCairan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 36).toString();
            
            TketSelama.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 37).toString());
            
            slkiAsupanCairan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 38).toString();
            slkiHaluaran = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 39).toString();
            slkiKelembapan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 40).toString();
            slkiAsupanMakanan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 41).toString();
            slkiEdema = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 42).toString();
            slkiTekananDarah = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 43).toString();
            slkiDenyut = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 44).toString();
            slkiTekananArteri = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 45).toString();
            slkiMembran = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 46).toString();
            slkiMata = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 47).toString();
            slkiTurgorMembaik = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 48).toString();
            slkiBerat = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 49).toString();
            slkiSerum = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 50).toString();
            slkiKalium = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 51).toString();
            slkiKekuatan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 52).toString();
            slkiTurgorMeningkat = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 53).toString();
            slkiOutput = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 54).toString();

            sikiPeriksa = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 55).toString();
            sikiMonitor = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 56).toString();
            sikiHitung = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 57).toString();
            sikiBerikanPosisi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 58).toString();
            sikiBerikanAsupan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 59).toString();
            sikiAnjurMemperbanyak = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 60).toString();
            sikiAnjurMenghindari = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 61).toString();
            sikiKolabNacl = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 62).toString();
            sikiKolabGlukosa = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 63).toString();
            sikiKolabKoloid = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 64).toString();
            sikiKolabProduk = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 65).toString();
            
            TnipPetugas.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 67).toString());
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
        if (sdkiKehilangan.equals("ya")) {
            chkSdkiKehilangan.setSelected(true);
        } else {
            chkSdkiKehilangan.setSelected(false);
        }

        if (sdkiKegagalan.equals("ya")) {
            chkSdkiKegagalan.setSelected(true);
        } else {
            chkSdkiKegagalan.setSelected(false);
        }

        if (sdkiPeningkatan.equals("ya")) {
            chkSdkiPeningkatan.setSelected(true);
        } else {
            chkSdkiPeningkatan.setSelected(false);
        }

        if (sdkiKekurangan.equals("ya")) {
            chkSdkiKekurangan.setSelected(true);
        } else {
            chkSdkiKekurangan.setSelected(false);
        }

        if (sdkiEvaporasi.equals("ya")) {
            chkSdkiEvaporasi.setSelected(true);
        } else {
            chkSdkiEvaporasi.setSelected(false);
        }

        if (sdkiFrekuensi.equals("ya")) {
            chkSdkiFrekuensi.setSelected(true);
        } else {
            chkSdkiFrekuensi.setSelected(false);
        }

        if (sdkiNadi.equals("ya")) {
            chkSdkiNadi.setSelected(true);
        } else {
            chkSdkiNadi.setSelected(false);
        }

        if (sdkiTekananDarah.equals("ya")) {
            chkSdkiTekananDarah.setSelected(true);
        } else {
            chkSdkiTekananDarah.setSelected(false);
        }

        if (sdkiTekananNadi.equals("ya")) {
            chkSdkiTekananNadi.setSelected(true);
        } else {
            chkSdkiTekananNadi.setSelected(false);
        }

        if (sdkiTurgor.equals("ya")) {
            chkSdkiTurgor.setSelected(true);
        } else {
            chkSdkiTurgor.setSelected(false);
        }

        if (sdkiMembran.equals("ya")) {
            chkSdkiMembran.setSelected(true);
        } else {
            chkSdkiMembran.setSelected(false);
        }

        if (sdkiVolume.equals("ya")) {
            chkSdkiVolume.setSelected(true);
        } else {
            chkSdkiVolume.setSelected(false);
        }

        if (sdkiHematokrit.equals("ya")) {
            chkSdkiHematokrit.setSelected(true);
        } else {
            chkSdkiHematokrit.setSelected(false);
        }

        if (sdkiMerasa.equals("ya")) {
            chkSdkiMerasa.setSelected(true);
        } else {
            chkSdkiMerasa.setSelected(false);
        }

        if (sdkiMengeluh.equals("ya")) {
            chkSdkiMengeluh.setSelected(true);
        } else {
            chkSdkiMengeluh.setSelected(false);
        }

        if (sdkiPengisian.equals("ya")) {
            chkSdkiPengisian.setSelected(true);
        } else {
            chkSdkiPengisian.setSelected(false);
        }

        if (sdkiStatus.equals("ya")) {
            chkSdkiStatus.setSelected(true);
        } else {
            chkSdkiStatus.setSelected(false);
        }

        if (sdkiSuhu.equals("ya")) {
            chkSdkiSuhu.setSelected(true);
        } else {
            chkSdkiSuhu.setSelected(false);
        }

        if (sdkiKonsentrasi.equals("ya")) {
            chkSdkiKonsentrasi.setSelected(true);
        } else {
            chkSdkiKonsentrasi.setSelected(false);
        }

        if (sdkiBerat.equals("ya")) {
            chkSdkiBerat.setSelected(true);
        } else {
            chkSdkiBerat.setSelected(false);
        }

        if (sdkiTrauma.equals("ya")) {
            chkSdkiTrauma.setSelected(true);
        } else {
            chkSdkiTrauma.setSelected(false);
        }

        if (sdkiLuka.equals("ya")) {
            chkSdkiLuka.setSelected(true);
        } else {
            chkSdkiLuka.setSelected(false);
        }

        if (sdkiAids.equals("ya")) {
            chkSdkiAids.setSelected(true);
        } else {
            chkSdkiAids.setSelected(false);
        }

        if (sdkiMuntah.equals("ya")) {
            chkSdkiMuntah.setSelected(true);
        } else {
            chkSdkiMuntah.setSelected(false);
        }

        if (sdkiDiare.equals("ya")) {
            chkSdkiDiare.setSelected(true);
        } else {
            chkSdkiDiare.setSelected(false);
        }

        if (sdkiHipoalbumin.equals("ya")) {
            chkSdkiHipoalbumin.setSelected(true);
        } else {
            chkSdkiHipoalbumin.setSelected(false);
        }

        if (slkiStatus.equals("ya")) {
            chkSlkiStatus.setSelected(true);
        } else {
            chkSlkiStatus.setSelected(false);
        }

        if (slkiElektrolit.equals("ya")) {
            chkSlkiElektrolit.setSelected(true);
        } else {
            chkSlkiElektrolit.setSelected(false);
        }

        if (slkiCairan.equals("ya")) {
            chkSlkiCairan.setSelected(true);
        } else {
            chkSlkiCairan.setSelected(false);
        }

        if (slkiAsupanCairan.equals("ya")) {
            chkSlkiAsupanCairan.setSelected(true);
        } else {
            chkSlkiAsupanCairan.setSelected(false);
        }

        if (slkiHaluaran.equals("ya")) {
            chkSlkiHaluaran.setSelected(true);
        } else {
            chkSlkiHaluaran.setSelected(false);
        }

        if (slkiKelembapan.equals("ya")) {
            chkSlkiKelembapan.setSelected(true);
        } else {
            chkSlkiKelembapan.setSelected(false);
        }

        if (slkiAsupanMakanan.equals("ya")) {
            chkSlkiAsupanMakanan.setSelected(true);
        } else {
            chkSlkiAsupanMakanan.setSelected(false);
        }

        if (slkiEdema.equals("ya")) {
            chkSlkiEdema.setSelected(true);
        } else {
            chkSlkiEdema.setSelected(false);
        }

        if (slkiTekananDarah.equals("ya")) {
            chkSlkiTekananDarah.setSelected(true);
        } else {
            chkSlkiTekananDarah.setSelected(false);
        }

        if (slkiDenyut.equals("ya")) {
            chkSlkiDenyut.setSelected(true);
        } else {
            chkSlkiDenyut.setSelected(false);
        }

        if (slkiTekananArteri.equals("ya")) {
            chkSlkiTekananArteri.setSelected(true);
        } else {
            chkSlkiTekananArteri.setSelected(false);
        }

        if (slkiMembran.equals("ya")) {
            chkSlkiMembran.setSelected(true);
        } else {
            chkSlkiMembran.setSelected(false);
        }

        if (slkiMata.equals("ya")) {
            chkSlkiMata.setSelected(true);
        } else {
            chkSlkiMata.setSelected(false);
        }

        if (slkiTurgorMembaik.equals("ya")) {
            chkSlkiTurgorMembaik.setSelected(true);
        } else {
            chkSlkiTurgorMembaik.setSelected(false);
        }

        if (slkiBerat.equals("ya")) {
            chkSlkiBerat.setSelected(true);
        } else {
            chkSlkiBerat.setSelected(false);
        }

        if (slkiSerum.equals("ya")) {
            chkSlkiSerum.setSelected(true);
        } else {
            chkSlkiSerum.setSelected(false);
        }

        if (slkiKalium.equals("ya")) {
            chkSlkiKalium.setSelected(true);
        } else {
            chkSlkiKalium.setSelected(false);
        }

        if (slkiKekuatan.equals("ya")) {
            chkSlkiKekuatan.setSelected(true);
        } else {
            chkSlkiKekuatan.setSelected(false);
        }

        if (slkiTurgorMeningkat.equals("ya")) {
            chkSlkiTurgorMeningkat.setSelected(true);
        } else {
            chkSlkiTurgorMeningkat.setSelected(false);
        }

        if (slkiOutput.equals("ya")) {
            chkSlkiOutput.setSelected(true);
        } else {
            chkSlkiOutput.setSelected(false);
        }

        if (sikiPeriksa.equals("ya")) {
            chkSikiPeriksa.setSelected(true);
        } else {
            chkSikiPeriksa.setSelected(false);
        }

        if (sikiMonitor.equals("ya")) {
            chkSikiMonitor.setSelected(true);
        } else {
            chkSikiMonitor.setSelected(false);
        }

        if (sikiHitung.equals("ya")) {
            chkSikiHitung.setSelected(true);
        } else {
            chkSikiHitung.setSelected(false);
        }

        if (sikiBerikanPosisi.equals("ya")) {
            chkSikiBerikanPosisi.setSelected(true);
        } else {
            chkSikiBerikanPosisi.setSelected(false);
        }

        if (sikiBerikanAsupan.equals("ya")) {
            chkSikiBerikanAsupan.setSelected(true);
        } else {
            chkSikiBerikanAsupan.setSelected(false);
        }

        if (sikiAnjurMemperbanyak.equals("ya")) {
            chkSikiAnjurMemperbanyak.setSelected(true);
        } else {
            chkSikiAnjurMemperbanyak.setSelected(false);
        }

        if (sikiAnjurMenghindari.equals("ya")) {
            chkSikiAnjurMenghindari.setSelected(true);
        } else {
            chkSikiAnjurMenghindari.setSelected(false);
        }

        if (sikiKolabNacl.equals("ya")) {
            chkSikiKolabNacl.setSelected(true);
        } else {
            chkSikiKolabNacl.setSelected(false);
        }

        if (sikiKolabGlukosa.equals("ya")) {
            chkSikiKolabGlukosa.setSelected(true);
        } else {
            chkSikiKolabGlukosa.setSelected(false);
        }

        if (sikiKolabKoloid.equals("ya")) {
            chkSikiKolabKoloid.setSelected(true);
        } else {
            chkSikiKolabKoloid.setSelected(false);
        }

        if (sikiKolabProduk.equals("ya")) {
            chkSikiKolabProduk.setSelected(true);
        } else {
            chkSikiKolabProduk.setSelected(false);
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
        if (chkSdkiKehilangan.isSelected() == true) {
            sdkiKehilangan = "ya";
        } else {
            sdkiKehilangan = "tidak";
        }

        if (chkSdkiKegagalan.isSelected() == true) {
            sdkiKegagalan = "ya";
        } else {
            sdkiKegagalan = "tidak";
        }

        if (chkSdkiPeningkatan.isSelected() == true) {
            sdkiPeningkatan = "ya";
        } else {
            sdkiPeningkatan = "tidak";
        }

        if (chkSdkiKekurangan.isSelected() == true) {
            sdkiKekurangan = "ya";
        } else {
            sdkiKekurangan = "tidak";
        }

        if (chkSdkiEvaporasi.isSelected() == true) {
            sdkiEvaporasi = "ya";
        } else {
            sdkiEvaporasi = "tidak";
        }

        if (chkSdkiFrekuensi.isSelected() == true) {
            sdkiFrekuensi = "ya";
        } else {
            sdkiFrekuensi = "tidak";
        }

        if (chkSdkiNadi.isSelected() == true) {
            sdkiNadi = "ya";
        } else {
            sdkiNadi = "tidak";
        }

        if (chkSdkiTekananDarah.isSelected() == true) {
            sdkiTekananDarah = "ya";
        } else {
            sdkiTekananDarah = "tidak";
        }

        if (chkSdkiTekananNadi.isSelected() == true) {
            sdkiTekananNadi = "ya";
        } else {
            sdkiTekananNadi = "tidak";
        }

        if (chkSdkiTurgor.isSelected() == true) {
            sdkiTurgor = "ya";
        } else {
            sdkiTurgor = "tidak";
        }

        if (chkSdkiMembran.isSelected() == true) {
            sdkiMembran = "ya";
        } else {
            sdkiMembran = "tidak";
        }

        if (chkSdkiVolume.isSelected() == true) {
            sdkiVolume = "ya";
        } else {
            sdkiVolume = "tidak";
        }

        if (chkSdkiHematokrit.isSelected() == true) {
            sdkiHematokrit = "ya";
        } else {
            sdkiHematokrit = "tidak";
        }

        if (chkSdkiMerasa.isSelected() == true) {
            sdkiMerasa = "ya";
        } else {
            sdkiMerasa = "tidak";
        }

        if (chkSdkiMengeluh.isSelected() == true) {
            sdkiMengeluh = "ya";
        } else {
            sdkiMengeluh = "tidak";
        }

        if (chkSdkiPengisian.isSelected() == true) {
            sdkiPengisian = "ya";
        } else {
            sdkiPengisian = "tidak";
        }

        if (chkSdkiStatus.isSelected() == true) {
            sdkiStatus = "ya";
        } else {
            sdkiStatus = "tidak";
        }

        if (chkSdkiSuhu.isSelected() == true) {
            sdkiSuhu = "ya";
        } else {
            sdkiSuhu = "tidak";
        }

        if (chkSdkiKonsentrasi.isSelected() == true) {
            sdkiKonsentrasi = "ya";
        } else {
            sdkiKonsentrasi = "tidak";
        }

        if (chkSdkiBerat.isSelected() == true) {
            sdkiBerat = "ya";
        } else {
            sdkiBerat = "tidak";
        }

        if (chkSdkiTrauma.isSelected() == true) {
            sdkiTrauma = "ya";
        } else {
            sdkiTrauma = "tidak";
        }

        if (chkSdkiLuka.isSelected() == true) {
            sdkiLuka = "ya";
        } else {
            sdkiLuka = "tidak";
        }

        if (chkSdkiAids.isSelected() == true) {
            sdkiAids = "ya";
        } else {
            sdkiAids = "tidak";
        }

        if (chkSdkiMuntah.isSelected() == true) {
            sdkiMuntah = "ya";
        } else {
            sdkiMuntah = "tidak";
        }

        if (chkSdkiDiare.isSelected() == true) {
            sdkiDiare = "ya";
        } else {
            sdkiDiare = "tidak";
        }

        if (chkSdkiHipoalbumin.isSelected() == true) {
            sdkiHipoalbumin = "ya";
        } else {
            sdkiHipoalbumin = "tidak";
        }

        if (chkSlkiStatus.isSelected() == true) {
            slkiStatus = "ya";
        } else {
            slkiStatus = "tidak";
        }

        if (chkSlkiElektrolit.isSelected() == true) {
            slkiElektrolit = "ya";
        } else {
            slkiElektrolit = "tidak";
        }

        if (chkSlkiCairan.isSelected() == true) {
            slkiCairan = "ya";
        } else {
            slkiCairan = "tidak";
        }

        if (chkSlkiAsupanCairan.isSelected() == true) {
            slkiAsupanCairan = "ya";
        } else {
            slkiAsupanCairan = "tidak";
        }

        if (chkSlkiHaluaran.isSelected() == true) {
            slkiHaluaran = "ya";
        } else {
            slkiHaluaran = "tidak";
        }

        if (chkSlkiKelembapan.isSelected() == true) {
            slkiKelembapan = "ya";
        } else {
            slkiKelembapan = "tidak";
        }

        if (chkSlkiAsupanMakanan.isSelected() == true) {
            slkiAsupanMakanan = "ya";
        } else {
            slkiAsupanMakanan = "tidak";
        }

        if (chkSlkiEdema.isSelected() == true) {
            slkiEdema = "ya";
        } else {
            slkiEdema = "tidak";
        }

        if (chkSlkiTekananDarah.isSelected() == true) {
            slkiTekananDarah = "ya";
        } else {
            slkiTekananDarah = "tidak";
        }

        if (chkSlkiDenyut.isSelected() == true) {
            slkiDenyut = "ya";
        } else {
            slkiDenyut = "tidak";
        }

        if (chkSlkiTekananArteri.isSelected() == true) {
            slkiTekananArteri = "ya";
        } else {
            slkiTekananArteri = "tidak";
        }

        if (chkSlkiMembran.isSelected() == true) {
            slkiMembran = "ya";
        } else {
            slkiMembran = "tidak";
        }

        if (chkSlkiMata.isSelected() == true) {
            slkiMata = "ya";
        } else {
            slkiMata = "tidak";
        }

        if (chkSlkiTurgorMembaik.isSelected() == true) {
            slkiTurgorMembaik = "ya";
        } else {
            slkiTurgorMembaik = "tidak";
        }

        if (chkSlkiBerat.isSelected() == true) {
            slkiBerat = "ya";
        } else {
            slkiBerat = "tidak";
        }

        if (chkSlkiSerum.isSelected() == true) {
            slkiSerum = "ya";
        } else {
            slkiSerum = "tidak";
        }

        if (chkSlkiKalium.isSelected() == true) {
            slkiKalium = "ya";
        } else {
            slkiKalium = "tidak";
        }

        if (chkSlkiKekuatan.isSelected() == true) {
            slkiKekuatan = "ya";
        } else {
            slkiKekuatan = "tidak";
        }

        if (chkSlkiTurgorMeningkat.isSelected() == true) {
            slkiTurgorMeningkat = "ya";
        } else {
            slkiTurgorMeningkat = "tidak";
        }

        if (chkSlkiOutput.isSelected() == true) {
            slkiOutput = "ya";
        } else {
            slkiOutput = "tidak";
        }

        if (chkSikiPeriksa.isSelected() == true) {
            sikiPeriksa = "ya";
        } else {
            sikiPeriksa = "tidak";
        }

        if (chkSikiMonitor.isSelected() == true) {
            sikiMonitor = "ya";
        } else {
            sikiMonitor = "tidak";
        }

        if (chkSikiHitung.isSelected() == true) {
            sikiHitung = "ya";
        } else {
            sikiHitung = "tidak";
        }

        if (chkSikiBerikanPosisi.isSelected() == true) {
            sikiBerikanPosisi = "ya";
        } else {
            sikiBerikanPosisi = "tidak";
        }

        if (chkSikiBerikanAsupan.isSelected() == true) {
            sikiBerikanAsupan = "ya";
        } else {
            sikiBerikanAsupan = "tidak";
        }

        if (chkSikiAnjurMemperbanyak.isSelected() == true) {
            sikiAnjurMemperbanyak = "ya";
        } else {
            sikiAnjurMemperbanyak = "tidak";
        }

        if (chkSikiAnjurMenghindari.isSelected() == true) {
            sikiAnjurMenghindari = "ya";
        } else {
            sikiAnjurMenghindari = "tidak";
        }

        if (chkSikiKolabNacl.isSelected() == true) {
            sikiKolabNacl = "ya";
        } else {
            sikiKolabNacl = "tidak";
        }

        if (chkSikiKolabGlukosa.isSelected() == true) {
            sikiKolabGlukosa = "ya";
        } else {
            sikiKolabGlukosa = "tidak";
        }

        if (chkSikiKolabKoloid.isSelected() == true) {
            sikiKolabKoloid = "ya";
        } else {
            sikiKolabKoloid = "tidak";
        }

        if (chkSikiKolabProduk.isSelected() == true) {
            sikiKolabProduk = "ya";
        } else {
            sikiKolabProduk = "tidak";
        }
    }
    
    private void emptVariabel() {
        sdkiKehilangan = "";
        sdkiKegagalan = "";
        sdkiPeningkatan = "";
        sdkiKekurangan = "";
        sdkiEvaporasi = "";
        sdkiFrekuensi = "";
        sdkiNadi = "";
        sdkiTekananDarah = "";
        sdkiTekananNadi = "";
        sdkiTurgor = "";
        sdkiMembran = "";
        sdkiVolume = "";
        sdkiHematokrit = "";
        sdkiMerasa = "";
        sdkiMengeluh = "";
        sdkiPengisian = "";
        sdkiStatus = "";
        sdkiSuhu = "";
        sdkiKonsentrasi = "";
        sdkiBerat = "";
        sdkiTrauma = "";
        sdkiLuka = "";
        sdkiAids = "";
        sdkiMuntah = "";
        sdkiDiare = "";
        sdkiHipoalbumin = "";
        slkiStatus = "";
        slkiElektrolit = "";
        slkiCairan = "";
        slkiAsupanCairan = "";
        slkiHaluaran = "";
        slkiKelembapan = "";
        slkiAsupanMakanan = "";
        slkiEdema = "";
        slkiTekananDarah = "";
        slkiDenyut = "";
        slkiTekananArteri = "";
        slkiMembran = "";
        slkiMata = "";
        slkiTurgorMembaik = "";
        slkiBerat = "";
        slkiSerum = "";
        slkiKalium = "";
        slkiKekuatan = "";
        slkiTurgorMeningkat = "";
        slkiOutput = "";
        sikiPeriksa = "";
        sikiMonitor = "";
        sikiHitung = "";
        sikiBerikanPosisi = "";
        sikiBerikanAsupan = "";
        sikiAnjurMemperbanyak = "";
        sikiAnjurMenghindari = "";
        sikiKolabNacl = "";
        sikiKolabGlukosa = "";
        sikiKolabKoloid = "";
        sikiKolabProduk = "";
    }
    
    public void awalData() {
        tampil();
    }
}
