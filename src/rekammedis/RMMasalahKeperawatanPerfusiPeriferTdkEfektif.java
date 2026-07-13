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

/**
 *
 * @author dosen
 */
public class RMMasalahKeperawatanPerfusiPeriferTdkEfektif extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String sdkiHiperglikemi = "", sdkiPenurunanKonsen = "", sdkiPeningkatan = "", sdkiKekurangan = "", sdkiPenurunanAliran = "", sdkiKurangFaktor = "", sdkiKurangProses = "",
            sdkiKurangAktivitas = "", sdkiPengisian = "", sdkiNadi = "", sdkiAkral = "", sdkiWarna = "", sdkiTurgor = "", sdkiParas = "", sdkiNyeri = "", sdkiEdema = "",
            sdkiPenyembuhan = "", sdkiIndeks = "", sdkiBruit = "", sdkiTrombositopenia = "", sdkiDm = "", sdkiAnemia = "", sdkiGagal = "", sdkiTrombosisArteri = "", sdkiVarises = "",
            sdkiTrombosisVena = "", sdkiSindrom = "", sdkiKelainan = "", slkiDenyut = "", slkiPenyembuhan = "", slkiSensasi = "", slkiWarna = "", slkiEdema = "", slkiNyeri = "",
            slkiParas = "", slkiKelemahan = "", slkiKram = "", slkiNekrosis = "", slkiPengisian = "", slkiAkral = "", slkiTurgor = "", slkiSistolik = "", slkiDiastolik = "",
            sikiPeriksa = "", sikiIdentifikasi = "", sikiMonitor = "", sikiHindariInfus = "", sikiHindariTekanan = "", sikiHindariPenekanan = "", sikiPencegahan = "", sikiPerawatan = "",
            sikiHidrasi = "", sikiAnjurBerhenti = "", sikiAnjurBerolahraga = "", sikiAnjurMengecek = "", sikiAnjurMeminum = "", sikiAjarkan = "", sikiInformasi = "", sttsRawat = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMMasalahKeperawatanPerfusiPeriferTdkEfektif(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "waktu_simpan", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Simpan", "Nama Petugas",
            "ruang_rawat", "sdki_hiperglikemi", "sdki_penurunan_konsen", "sdki_peningkatan", "sdki_kekurangan", "sdki_penurunan_aliran", "sdki_kurang_faktor", "sdki_kurang_proses",
            "sdki_kurang_aktivitas", "sdki_pengisian", "sdki_nadi", "sdki_akral", "sdki_warna", "sdki_turgor", "sdki_paras", "sdki_nyeri", "sdki_edema", "sdki_penyembuhan",
            "sdki_indeks", "sdki_bruit", "sdki_trombositopenia", "sdki_dm", "sdki_anemia", "sdki_gagal", "sdki_trombosis_arteri", "sdki_varises", "sdki_trombosis_vena", "sdki_sindrom",
            "sdki_kelainan", "ket_slki_perfusi", "slki_denyut", "slki_penyembuhan", "slki_sensasi", "slki_warna", "slki_edema", "slki_nyeri", "slki_paras", "slki_kelemahan", "slki_kram",
            "slki_nekrosis", "slki_pengisian", "slki_akral", "slki_turgor", "slki_sistolik", "slki_diastolik", "siki_periksa", "siki_identifikasi", "siki_monitor", "siki_hindari_infus",
            "siki_hindari_tekanan", "siki_hindari_penekanan", "siki_pencegahan", "siki_perawatan", "siki_hidrasi", "siki_anjur_berhenti", "siki_anjur_berolahraga", "siki_anjur_mengecek",
            "siki_anjur_meminum", "siki_ajarkan", "siki_informasi", "status_rawat", "nip_petugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbMasalah.setModel(tabMode);
        tbMasalah.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMasalah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 70; i++) {
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
            }
        }
        tbMasalah.setDefaultRenderer(Object.class, new WarnaTable());

        TketSlkiPerfusi.setDocument(new batasInput((int) 100).getKata(TketSlkiPerfusi));
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
                if (akses.getform().equals("RMMasalahKeperawatanPerfusiPeriferTdkEfektif")) {
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
        chkSdkiHiperglikemi = new widget.CekBox();
        chkSdkiPenurunanKonsen = new widget.CekBox();
        chkSdkiPeningkatan = new widget.CekBox();
        chkSdkiKekurangan = new widget.CekBox();
        chkSdkiPenurunanAliran = new widget.CekBox();
        chkSdkiKurangFaktor = new widget.CekBox();
        chkSdkiParas = new widget.CekBox();
        chkSdkiNyeri = new widget.CekBox();
        chkSdkiTrombositopenia = new widget.CekBox();
        chkSdkiEdema = new widget.CekBox();
        chkSdkiPenyembuhan = new widget.CekBox();
        chkSdkiIndeks = new widget.CekBox();
        chkSdkiDm = new widget.CekBox();
        chkSdkiAnemia = new widget.CekBox();
        chkSdkiGagal = new widget.CekBox();
        chkSlkiDenyut = new widget.CekBox();
        jLabel74 = new widget.Label();
        jLabel75 = new widget.Label();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        chkSdkiKurangAktivitas = new widget.CekBox();
        chkSdkiPengisian = new widget.CekBox();
        chkSdkiNadi = new widget.CekBox();
        chkSdkiAkral = new widget.CekBox();
        chkSdkiTurgor = new widget.CekBox();
        chkSdkiWarna = new widget.CekBox();
        jLabel78 = new widget.Label();
        jLabel79 = new widget.Label();
        TketSlkiPerfusi = new widget.TextBox();
        jLabel80 = new widget.Label();
        chkSdkiTrombosisArteri = new widget.CekBox();
        chkSdkiVarises = new widget.CekBox();
        chkSdkiTrombosisVena = new widget.CekBox();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        chkSlkiPenyembuhan = new widget.CekBox();
        chkSlkiSensasi = new widget.CekBox();
        chkSlkiWarna = new widget.CekBox();
        chkSlkiEdema = new widget.CekBox();
        chkSikiPeriksa = new widget.CekBox();
        chkSikiIdentifikasi = new widget.CekBox();
        chkSikiMonitor = new widget.CekBox();
        jLabel83 = new widget.Label();
        chkSikiHindariInfus = new widget.CekBox();
        chkSikiHindariTekanan = new widget.CekBox();
        chkSikiHindariPenekanan = new widget.CekBox();
        chkSikiPencegahan = new widget.CekBox();
        jLabel84 = new widget.Label();
        chkSikiHidrasi = new widget.CekBox();
        chkSikiAnjurBerhenti = new widget.CekBox();
        chkSikiAnjurBerolahraga = new widget.CekBox();
        chkSikiAnjurMengecek = new widget.CekBox();
        chkSikiAnjurMeminum = new widget.CekBox();
        chkSikiAjarkan = new widget.CekBox();
        chkSikiInformasi = new widget.CekBox();
        chkSdkiKurangProses = new widget.CekBox();
        chkSdkiBruit = new widget.CekBox();
        chkSdkiSindrom = new widget.CekBox();
        chkSdkiKelainan = new widget.CekBox();
        jLabel91 = new widget.Label();
        chkSlkiNyeri = new widget.CekBox();
        chkSlkiParas = new widget.CekBox();
        chkSlkiKelemahan = new widget.CekBox();
        chkSlkiKram = new widget.CekBox();
        chkSlkiNekrosis = new widget.CekBox();
        chkSlkiPengisian = new widget.CekBox();
        chkSlkiAkral = new widget.CekBox();
        chkSlkiTurgor = new widget.CekBox();
        chkSlkiSistolik = new widget.CekBox();
        chkSlkiDiastolik = new widget.CekBox();
        chkSikiPerawatan = new widget.CekBox();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Masalah Keperawatan Perfusi Perifer Tidak Efektif ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(760, 1434));
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
        label20.setBounds(0, 1390, 140, 23);

        TnipPetugas.setEditable(false);
        TnipPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnipPetugas.setName("TnipPetugas"); // NOI18N
        TnipPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipPetugas);
        TnipPetugas.setBounds(145, 1390, 150, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        TnmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(300, 1390, 360, 23);

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
        BtnPetugas.setBounds(664, 1390, 28, 23);

        chkSdkiHiperglikemi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiHiperglikemi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiHiperglikemi.setText("Hiperglikemia");
        chkSdkiHiperglikemi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiHiperglikemi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiHiperglikemi.setName("chkSdkiHiperglikemi"); // NOI18N
        chkSdkiHiperglikemi.setOpaque(false);
        chkSdkiHiperglikemi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiHiperglikemi);
        chkSdkiHiperglikemi.setBounds(145, 122, 110, 23);

        chkSdkiPenurunanKonsen.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPenurunanKonsen.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPenurunanKonsen.setText("Penurunan konsentrasi hemoglobin");
        chkSdkiPenurunanKonsen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPenurunanKonsen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPenurunanKonsen.setName("chkSdkiPenurunanKonsen"); // NOI18N
        chkSdkiPenurunanKonsen.setOpaque(false);
        chkSdkiPenurunanKonsen.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPenurunanKonsen);
        chkSdkiPenurunanKonsen.setBounds(145, 150, 210, 23);

        chkSdkiPeningkatan.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPeningkatan.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPeningkatan.setText("Peningkatan tekanan darah");
        chkSdkiPeningkatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPeningkatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPeningkatan.setName("chkSdkiPeningkatan"); // NOI18N
        chkSdkiPeningkatan.setOpaque(false);
        chkSdkiPeningkatan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPeningkatan);
        chkSdkiPeningkatan.setBounds(145, 178, 170, 23);

        chkSdkiKekurangan.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKekurangan.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKekurangan.setText("Kekurangan volume cairan");
        chkSdkiKekurangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKekurangan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKekurangan.setName("chkSdkiKekurangan"); // NOI18N
        chkSdkiKekurangan.setOpaque(false);
        chkSdkiKekurangan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKekurangan);
        chkSdkiKekurangan.setBounds(145, 206, 160, 23);

        chkSdkiPenurunanAliran.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPenurunanAliran.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPenurunanAliran.setText("Penurunan aliran arteri atau vena");
        chkSdkiPenurunanAliran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPenurunanAliran.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPenurunanAliran.setName("chkSdkiPenurunanAliran"); // NOI18N
        chkSdkiPenurunanAliran.setOpaque(false);
        chkSdkiPenurunanAliran.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPenurunanAliran);
        chkSdkiPenurunanAliran.setBounds(145, 234, 190, 23);

        chkSdkiKurangFaktor.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKurangFaktor.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKurangFaktor.setText("Kurang terpapar informasi tentang faktor pemberat (mis.merokok, trauma, obesitas)");
        chkSdkiKurangFaktor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKurangFaktor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKurangFaktor.setName("chkSdkiKurangFaktor"); // NOI18N
        chkSdkiKurangFaktor.setOpaque(false);
        chkSdkiKurangFaktor.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKurangFaktor);
        chkSdkiKurangFaktor.setBounds(145, 262, 440, 23);

        chkSdkiParas.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiParas.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiParas.setText("Parasfemoralistesia");
        chkSdkiParas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiParas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiParas.setName("chkSdkiParas"); // NOI18N
        chkSdkiParas.setOpaque(false);
        chkSdkiParas.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiParas);
        chkSdkiParas.setBounds(145, 458, 130, 23);

        chkSdkiNyeri.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiNyeri.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiNyeri.setText("Nyeri ekstrimitas (klauditasi intermiten)");
        chkSdkiNyeri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiNyeri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiNyeri.setName("chkSdkiNyeri"); // NOI18N
        chkSdkiNyeri.setOpaque(false);
        chkSdkiNyeri.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiNyeri);
        chkSdkiNyeri.setBounds(145, 486, 220, 23);

        chkSdkiTrombositopenia.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTrombositopenia.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTrombositopenia.setText("Trombositopenia");
        chkSdkiTrombositopenia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTrombositopenia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTrombositopenia.setName("chkSdkiTrombositopenia"); // NOI18N
        chkSdkiTrombositopenia.setOpaque(false);
        chkSdkiTrombositopenia.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSdkiTrombositopenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSdkiTrombositopeniaActionPerformed(evt);
            }
        });
        FormInput.add(chkSdkiTrombositopenia);
        chkSdkiTrombositopenia.setBounds(145, 542, 110, 23);

        chkSdkiEdema.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiEdema.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiEdema.setText("Edema");
        chkSdkiEdema.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiEdema.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiEdema.setName("chkSdkiEdema"); // NOI18N
        chkSdkiEdema.setOpaque(false);
        chkSdkiEdema.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiEdema);
        chkSdkiEdema.setBounds(375, 458, 70, 23);

        chkSdkiPenyembuhan.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPenyembuhan.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPenyembuhan.setText("Penyembuhan luka lambat");
        chkSdkiPenyembuhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPenyembuhan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPenyembuhan.setName("chkSdkiPenyembuhan"); // NOI18N
        chkSdkiPenyembuhan.setOpaque(false);
        chkSdkiPenyembuhan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPenyembuhan);
        chkSdkiPenyembuhan.setBounds(375, 486, 160, 23);

        chkSdkiIndeks.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiIndeks.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiIndeks.setText("Indeks ankle-brachial < 0,90");
        chkSdkiIndeks.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiIndeks.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiIndeks.setName("chkSdkiIndeks"); // NOI18N
        chkSdkiIndeks.setOpaque(false);
        chkSdkiIndeks.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiIndeks);
        chkSdkiIndeks.setBounds(550, 458, 170, 23);

        chkSdkiDm.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiDm.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiDm.setText("DM");
        chkSdkiDm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiDm.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiDm.setName("chkSdkiDm"); // NOI18N
        chkSdkiDm.setOpaque(false);
        chkSdkiDm.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiDm);
        chkSdkiDm.setBounds(145, 570, 50, 23);

        chkSdkiAnemia.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiAnemia.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiAnemia.setText("Anemia");
        chkSdkiAnemia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiAnemia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiAnemia.setName("chkSdkiAnemia"); // NOI18N
        chkSdkiAnemia.setOpaque(false);
        chkSdkiAnemia.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiAnemia);
        chkSdkiAnemia.setBounds(145, 598, 70, 23);

        chkSdkiGagal.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiGagal.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiGagal.setText("Gagal jantung kongestif");
        chkSdkiGagal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiGagal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiGagal.setName("chkSdkiGagal"); // NOI18N
        chkSdkiGagal.setOpaque(false);
        chkSdkiGagal.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiGagal);
        chkSdkiGagal.setBounds(270, 542, 150, 23);

        chkSlkiDenyut.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiDenyut.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiDenyut.setText("Denyut nadi perifer meningkat");
        chkSlkiDenyut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiDenyut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiDenyut.setName("chkSlkiDenyut"); // NOI18N
        chkSlkiDenyut.setOpaque(false);
        chkSlkiDenyut.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiDenyut);
        chkSlkiDenyut.setBounds(145, 710, 180, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Diagnosis Keperawatan SDKI : Perfusi Perifer Tidak Efektif (D.0009)");
        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 66, 430, 23);

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
        jLabel76.setBounds(145, 346, 210, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel77.setText("DO/DS (Gejala dan tanda Minor)");
        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(145, 430, 210, 23);

        chkSdkiKurangAktivitas.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKurangAktivitas.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKurangAktivitas.setText("Kurang aktivitas fisik");
        chkSdkiKurangAktivitas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKurangAktivitas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKurangAktivitas.setName("chkSdkiKurangAktivitas"); // NOI18N
        chkSdkiKurangAktivitas.setOpaque(false);
        chkSdkiKurangAktivitas.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKurangAktivitas);
        chkSdkiKurangAktivitas.setBounds(145, 318, 140, 23);

        chkSdkiPengisian.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPengisian.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPengisian.setText("Pengisian kapiler >3 detik");
        chkSdkiPengisian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPengisian.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPengisian.setName("chkSdkiPengisian"); // NOI18N
        chkSdkiPengisian.setOpaque(false);
        chkSdkiPengisian.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPengisian);
        chkSdkiPengisian.setBounds(145, 374, 160, 23);

        chkSdkiNadi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiNadi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiNadi.setText("Nadi perifer menurun atau tidak teraba");
        chkSdkiNadi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiNadi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiNadi.setName("chkSdkiNadi"); // NOI18N
        chkSdkiNadi.setOpaque(false);
        chkSdkiNadi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiNadi);
        chkSdkiNadi.setBounds(145, 402, 220, 23);

        chkSdkiAkral.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiAkral.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiAkral.setText("Akral teraba dingin");
        chkSdkiAkral.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiAkral.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiAkral.setName("chkSdkiAkral"); // NOI18N
        chkSdkiAkral.setOpaque(false);
        chkSdkiAkral.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiAkral);
        chkSdkiAkral.setBounds(380, 374, 130, 23);

        chkSdkiTurgor.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTurgor.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTurgor.setText("Turgor kulit menurun");
        chkSdkiTurgor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTurgor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTurgor.setName("chkSdkiTurgor"); // NOI18N
        chkSdkiTurgor.setOpaque(false);
        chkSdkiTurgor.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTurgor);
        chkSdkiTurgor.setBounds(525, 374, 140, 23);

        chkSdkiWarna.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiWarna.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiWarna.setText("Warna kulit pucat");
        chkSdkiWarna.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiWarna.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiWarna.setName("chkSdkiWarna"); // NOI18N
        chkSdkiWarna.setOpaque(false);
        chkSdkiWarna.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiWarna);
        chkSdkiWarna.setBounds(380, 402, 120, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("Kondisi Klinis Terkait :");
        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(145, 514, 140, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("(Rencana Keperawatan) Tujuan dan Kriteria Hasil SLKI : Perfusi Perifer (L.02011)");
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 626, 500, 23);

        TketSlkiPerfusi.setForeground(new java.awt.Color(0, 0, 0));
        TketSlkiPerfusi.setName("TketSlkiPerfusi"); // NOI18N
        FormInput.add(TketSlkiPerfusi);
        TketSlkiPerfusi.setBounds(388, 654, 200, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("Masalah perfusi perifer tidak efektif pada pasien teratasi dengan kriteria hasil :");
        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(145, 682, 460, 23);

        chkSdkiTrombosisArteri.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTrombosisArteri.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTrombosisArteri.setText("Trombosis arteri");
        chkSdkiTrombosisArteri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTrombosisArteri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTrombosisArteri.setName("chkSdkiTrombosisArteri"); // NOI18N
        chkSdkiTrombosisArteri.setOpaque(false);
        chkSdkiTrombosisArteri.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTrombosisArteri);
        chkSdkiTrombosisArteri.setBounds(270, 570, 110, 23);

        chkSdkiVarises.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiVarises.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiVarises.setText("Varises");
        chkSdkiVarises.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiVarises.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiVarises.setName("chkSdkiVarises"); // NOI18N
        chkSdkiVarises.setOpaque(false);
        chkSdkiVarises.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiVarises);
        chkSdkiVarises.setBounds(270, 598, 70, 23);

        chkSdkiTrombosisVena.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTrombosisVena.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTrombosisVena.setText("Thrombosis vena dalam");
        chkSdkiTrombosisVena.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTrombosisVena.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTrombosisVena.setName("chkSdkiTrombosisVena"); // NOI18N
        chkSdkiTrombosisVena.setOpaque(false);
        chkSdkiTrombosisVena.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTrombosisVena);
        chkSdkiTrombosisVena.setBounds(430, 542, 150, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("(Rencana Keperawatan) Intervensi Keperawatan SIKI : Perawatan Sirkulasi (I.02079)");
        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 850, 520, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("Tindakan Observasi :");
        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(145, 878, 140, 23);

        chkSlkiPenyembuhan.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiPenyembuhan.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiPenyembuhan.setText("Penyembuhan luka meningkat");
        chkSlkiPenyembuhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiPenyembuhan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiPenyembuhan.setName("chkSlkiPenyembuhan"); // NOI18N
        chkSlkiPenyembuhan.setOpaque(false);
        chkSlkiPenyembuhan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiPenyembuhan);
        chkSlkiPenyembuhan.setBounds(145, 738, 180, 23);

        chkSlkiSensasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiSensasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiSensasi.setText("Sensasi meningkat");
        chkSlkiSensasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiSensasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiSensasi.setName("chkSlkiSensasi"); // NOI18N
        chkSlkiSensasi.setOpaque(false);
        chkSlkiSensasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiSensasi);
        chkSlkiSensasi.setBounds(145, 766, 120, 23);

        chkSlkiWarna.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiWarna.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiWarna.setText("Warna kulit pucat menurun");
        chkSlkiWarna.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiWarna.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiWarna.setName("chkSlkiWarna"); // NOI18N
        chkSlkiWarna.setOpaque(false);
        chkSlkiWarna.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiWarna);
        chkSlkiWarna.setBounds(145, 794, 160, 23);

        chkSlkiEdema.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiEdema.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiEdema.setText("Edema perifer menurun");
        chkSlkiEdema.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiEdema.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiEdema.setName("chkSlkiEdema"); // NOI18N
        chkSlkiEdema.setOpaque(false);
        chkSlkiEdema.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiEdema);
        chkSlkiEdema.setBounds(145, 822, 150, 23);

        chkSikiPeriksa.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiPeriksa.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiPeriksa.setText("Periksa sirkulasi perifer (mis.nadi perifer, edema, pengisian kapiler, warna, suhu)");
        chkSikiPeriksa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiPeriksa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiPeriksa.setName("chkSikiPeriksa"); // NOI18N
        chkSikiPeriksa.setOpaque(false);
        chkSikiPeriksa.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiPeriksa);
        chkSikiPeriksa.setBounds(145, 906, 420, 23);

        chkSikiIdentifikasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiIdentifikasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiIdentifikasi.setText("Identifikasi faktor risiko gangguan sirkulasi (mis.diabetes, perokok, orang tua, hipertensi)");
        chkSikiIdentifikasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiIdentifikasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiIdentifikasi.setName("chkSikiIdentifikasi"); // NOI18N
        chkSikiIdentifikasi.setOpaque(false);
        chkSikiIdentifikasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiIdentifikasi);
        chkSikiIdentifikasi.setBounds(145, 934, 460, 23);

        chkSikiMonitor.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiMonitor.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiMonitor.setText("Monitor panas, kemerahan, nyeri atau bengkak ekstrimitas");
        chkSikiMonitor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiMonitor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiMonitor.setName("chkSikiMonitor"); // NOI18N
        chkSikiMonitor.setOpaque(false);
        chkSikiMonitor.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiMonitor);
        chkSikiMonitor.setBounds(145, 962, 310, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("Terapeutik :");
        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(145, 990, 90, 23);

        chkSikiHindariInfus.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiHindariInfus.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiHindariInfus.setText("Hindari pemasangan infus atau pengambilan darah di area keterbatasan perfusi");
        chkSikiHindariInfus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiHindariInfus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiHindariInfus.setName("chkSikiHindariInfus"); // NOI18N
        chkSikiHindariInfus.setOpaque(false);
        chkSikiHindariInfus.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiHindariInfus);
        chkSikiHindariInfus.setBounds(145, 1018, 420, 23);

        chkSikiHindariTekanan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiHindariTekanan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiHindariTekanan.setText("Hindari pengukuran tekanan darah pada ekstrimitas dengan keterbatasan perfusi");
        chkSikiHindariTekanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiHindariTekanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiHindariTekanan.setName("chkSikiHindariTekanan"); // NOI18N
        chkSikiHindariTekanan.setOpaque(false);
        chkSikiHindariTekanan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiHindariTekanan);
        chkSikiHindariTekanan.setBounds(145, 1046, 430, 23);

        chkSikiHindariPenekanan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiHindariPenekanan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiHindariPenekanan.setText("Hindari penekanan dan pemasangan torniquet pada area yang cidera");
        chkSikiHindariPenekanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiHindariPenekanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiHindariPenekanan.setName("chkSikiHindariPenekanan"); // NOI18N
        chkSikiHindariPenekanan.setOpaque(false);
        chkSikiHindariPenekanan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiHindariPenekanan);
        chkSikiHindariPenekanan.setBounds(145, 1074, 370, 23);

        chkSikiPencegahan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiPencegahan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiPencegahan.setText("Lakukan pencegahan infeksi");
        chkSikiPencegahan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiPencegahan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiPencegahan.setName("chkSikiPencegahan"); // NOI18N
        chkSikiPencegahan.setOpaque(false);
        chkSikiPencegahan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiPencegahan);
        chkSikiPencegahan.setBounds(145, 1102, 170, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("Edukasi :");
        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(145, 1186, 90, 23);

        chkSikiHidrasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiHidrasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiHidrasi.setText("Lakukan hidrasi");
        chkSikiHidrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiHidrasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiHidrasi.setName("chkSikiHidrasi"); // NOI18N
        chkSikiHidrasi.setOpaque(false);
        chkSikiHidrasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiHidrasi);
        chkSikiHidrasi.setBounds(145, 1158, 110, 23);

        chkSikiAnjurBerhenti.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAnjurBerhenti.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAnjurBerhenti.setText("Anjurkan berhenti merokok");
        chkSikiAnjurBerhenti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAnjurBerhenti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAnjurBerhenti.setName("chkSikiAnjurBerhenti"); // NOI18N
        chkSikiAnjurBerhenti.setOpaque(false);
        chkSikiAnjurBerhenti.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAnjurBerhenti);
        chkSikiAnjurBerhenti.setBounds(145, 1214, 170, 23);

        chkSikiAnjurBerolahraga.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAnjurBerolahraga.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAnjurBerolahraga.setText("Anjurkan berolahraga rutin");
        chkSikiAnjurBerolahraga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAnjurBerolahraga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAnjurBerolahraga.setName("chkSikiAnjurBerolahraga"); // NOI18N
        chkSikiAnjurBerolahraga.setOpaque(false);
        chkSikiAnjurBerolahraga.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAnjurBerolahraga);
        chkSikiAnjurBerolahraga.setBounds(145, 1242, 160, 23);

        chkSikiAnjurMengecek.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAnjurMengecek.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAnjurMengecek.setText("Anjurkan mengecek suhu air sebelum mandi");
        chkSikiAnjurMengecek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAnjurMengecek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAnjurMengecek.setName("chkSikiAnjurMengecek"); // NOI18N
        chkSikiAnjurMengecek.setOpaque(false);
        chkSikiAnjurMengecek.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAnjurMengecek);
        chkSikiAnjurMengecek.setBounds(145, 1270, 240, 23);

        chkSikiAnjurMeminum.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAnjurMeminum.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAnjurMeminum.setText("Anjurkan meminum obat pengontrol tekanan darah secara teratur");
        chkSikiAnjurMeminum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAnjurMeminum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAnjurMeminum.setName("chkSikiAnjurMeminum"); // NOI18N
        chkSikiAnjurMeminum.setOpaque(false);
        chkSikiAnjurMeminum.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAnjurMeminum);
        chkSikiAnjurMeminum.setBounds(145, 1298, 350, 23);

        chkSikiAjarkan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAjarkan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAjarkan.setText("Ajarkan program diet untuk memperbaiki sirkulasi (mis.lemak jenuh, minyak ikan omega 3)");
        chkSikiAjarkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAjarkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAjarkan.setName("chkSikiAjarkan"); // NOI18N
        chkSikiAjarkan.setOpaque(false);
        chkSikiAjarkan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAjarkan);
        chkSikiAjarkan.setBounds(145, 1326, 460, 23);

        chkSikiInformasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiInformasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiInformasi.setText("<html>Informasikan tanda dan gejala darurat yang harus dilaporkan (mis.rasa sakit yang tidak hilang saat istirahat, hilangnya rasa, luka tidak sembuh)</html>");
        chkSikiInformasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiInformasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiInformasi.setName("chkSikiInformasi"); // NOI18N
        chkSikiInformasi.setOpaque(false);
        chkSikiInformasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiInformasi);
        chkSikiInformasi.setBounds(145, 1354, 560, 30);

        chkSdkiKurangProses.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKurangProses.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKurangProses.setText("Kurang terpapar informasi tentang prroses penyakit (mis.diabetes mellitus, hiperlipidemia)");
        chkSdkiKurangProses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKurangProses.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKurangProses.setName("chkSdkiKurangProses"); // NOI18N
        chkSdkiKurangProses.setOpaque(false);
        chkSdkiKurangProses.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKurangProses);
        chkSdkiKurangProses.setBounds(145, 290, 470, 23);

        chkSdkiBruit.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiBruit.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiBruit.setText("Bruit femoralis");
        chkSdkiBruit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiBruit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiBruit.setName("chkSdkiBruit"); // NOI18N
        chkSdkiBruit.setOpaque(false);
        chkSdkiBruit.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiBruit);
        chkSdkiBruit.setBounds(550, 486, 110, 23);

        chkSdkiSindrom.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiSindrom.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiSindrom.setText("Sindrom kompartemen");
        chkSdkiSindrom.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiSindrom.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiSindrom.setName("chkSdkiSindrom"); // NOI18N
        chkSdkiSindrom.setOpaque(false);
        chkSdkiSindrom.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiSindrom);
        chkSdkiSindrom.setBounds(430, 570, 140, 23);

        chkSdkiKelainan.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKelainan.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKelainan.setText("Kelainan jantung kongestif");
        chkSdkiKelainan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKelainan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKelainan.setName("chkSdkiKelainan"); // NOI18N
        chkSdkiKelainan.setOpaque(false);
        chkSdkiKelainan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKelainan);
        chkSdkiKelainan.setBounds(430, 598, 160, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel91.setText("Setelah dilakukan tindakan keperawatan selama :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(145, 654, 240, 23);

        chkSlkiNyeri.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiNyeri.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiNyeri.setText("Nyeri ekstrimitas menurun");
        chkSlkiNyeri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiNyeri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiNyeri.setName("chkSlkiNyeri"); // NOI18N
        chkSlkiNyeri.setOpaque(false);
        chkSlkiNyeri.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiNyeri);
        chkSlkiNyeri.setBounds(340, 710, 160, 23);

        chkSlkiParas.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiParas.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiParas.setText("Parastesia menurun");
        chkSlkiParas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiParas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiParas.setName("chkSlkiParas"); // NOI18N
        chkSlkiParas.setOpaque(false);
        chkSlkiParas.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiParas);
        chkSlkiParas.setBounds(340, 738, 130, 23);

        chkSlkiKelemahan.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKelemahan.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKelemahan.setText("Kelemahan otot menurun");
        chkSlkiKelemahan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKelemahan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKelemahan.setName("chkSlkiKelemahan"); // NOI18N
        chkSlkiKelemahan.setOpaque(false);
        chkSlkiKelemahan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKelemahan);
        chkSlkiKelemahan.setBounds(340, 766, 150, 23);

        chkSlkiKram.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKram.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKram.setText("Kram otot menurun");
        chkSlkiKram.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKram.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKram.setName("chkSlkiKram"); // NOI18N
        chkSlkiKram.setOpaque(false);
        chkSlkiKram.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKram);
        chkSlkiKram.setBounds(340, 794, 130, 23);

        chkSlkiNekrosis.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiNekrosis.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiNekrosis.setText("Nekrosis menurun");
        chkSlkiNekrosis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiNekrosis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiNekrosis.setName("chkSlkiNekrosis"); // NOI18N
        chkSlkiNekrosis.setOpaque(false);
        chkSlkiNekrosis.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiNekrosis);
        chkSlkiNekrosis.setBounds(340, 822, 120, 23);

        chkSlkiPengisian.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiPengisian.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiPengisian.setText("Pengisian kapiler membaik");
        chkSlkiPengisian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiPengisian.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiPengisian.setName("chkSlkiPengisian"); // NOI18N
        chkSlkiPengisian.setOpaque(false);
        chkSlkiPengisian.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiPengisian);
        chkSlkiPengisian.setBounds(520, 710, 160, 23);

        chkSlkiAkral.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiAkral.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiAkral.setText("Akral membaik");
        chkSlkiAkral.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiAkral.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiAkral.setName("chkSlkiAkral"); // NOI18N
        chkSlkiAkral.setOpaque(false);
        chkSlkiAkral.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiAkral);
        chkSlkiAkral.setBounds(520, 738, 100, 23);

        chkSlkiTurgor.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiTurgor.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiTurgor.setText("Turgor kulit membaik");
        chkSlkiTurgor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiTurgor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiTurgor.setName("chkSlkiTurgor"); // NOI18N
        chkSlkiTurgor.setOpaque(false);
        chkSlkiTurgor.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiTurgor);
        chkSlkiTurgor.setBounds(520, 766, 130, 23);

        chkSlkiSistolik.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiSistolik.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiSistolik.setText("Tekanan darah sistolik membaik");
        chkSlkiSistolik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiSistolik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiSistolik.setName("chkSlkiSistolik"); // NOI18N
        chkSlkiSistolik.setOpaque(false);
        chkSlkiSistolik.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiSistolik);
        chkSlkiSistolik.setBounds(520, 794, 180, 23);

        chkSlkiDiastolik.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiDiastolik.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiDiastolik.setText("Tekanan darah diastolik membaik");
        chkSlkiDiastolik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiDiastolik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiDiastolik.setName("chkSlkiDiastolik"); // NOI18N
        chkSlkiDiastolik.setOpaque(false);
        chkSlkiDiastolik.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiDiastolik);
        chkSlkiDiastolik.setBounds(520, 822, 190, 23);

        chkSikiPerawatan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiPerawatan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiPerawatan.setText("Lakukan perawatan kaki dan kuku");
        chkSikiPerawatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiPerawatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiPerawatan.setName("chkSikiPerawatan"); // NOI18N
        chkSikiPerawatan.setOpaque(false);
        chkSikiPerawatan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiPerawatan);
        chkSikiPerawatan.setBounds(145, 1130, 200, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass13.add(Scroll1);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data MasKep Perfusi Perifer Tidak Efektif ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
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

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-07-2026" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-07-2026" }));
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
            if (Sequel.menyimpantf("masalah_keperawatan_perfusi_perifer_tdk_efektif", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 64, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), sdkiHiperglikemi, sdkiPenurunanKonsen, sdkiPeningkatan, sdkiKekurangan, sdkiPenurunanAliran, sdkiKurangFaktor,
                        sdkiKurangProses, sdkiKurangAktivitas, sdkiPengisian, sdkiNadi, sdkiAkral, sdkiWarna, sdkiTurgor, sdkiParas, sdkiNyeri, sdkiEdema, sdkiPenyembuhan,
                        sdkiIndeks, sdkiBruit, sdkiTrombositopenia, sdkiDm, sdkiAnemia, sdkiGagal, sdkiTrombosisArteri, sdkiVarises, sdkiTrombosisVena, sdkiSindrom, sdkiKelainan,
                        TketSlkiPerfusi.getText(), slkiDenyut, slkiPenyembuhan, slkiSensasi, slkiWarna, slkiEdema, slkiNyeri, slkiParas, slkiKelemahan, slkiKram, slkiNekrosis,
                        slkiPengisian, slkiAkral, slkiTurgor, slkiSistolik, slkiDiastolik, sikiPeriksa, sikiIdentifikasi, sikiMonitor, sikiHindariInfus, sikiHindariTekanan,
                        sikiHindariPenekanan, sikiPencegahan, sikiPerawatan, sikiHidrasi, sikiAnjurBerhenti, sikiAnjurBerolahraga, sikiAnjurMengecek, sikiAnjurMeminum, sikiAjarkan,
                        sikiInformasi, sttsRawat, TnipPetugas.getText(), Sequel.cariIsi("select now()")
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Masalah Keperawatan Perfusi Perifer Tidak Efektif", "Simpan");
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
                if (Sequel.mengedittf("masalah_keperawatan_perfusi_perifer_tdk_efektif", "waktu_simpan=?", "sdki_hiperglikemi=?, sdki_penurunan_konsen=?, sdki_peningkatan=?, "
                        + "sdki_kekurangan=?, sdki_penurunan_aliran=?, sdki_kurang_faktor=?, sdki_kurang_proses=?, sdki_kurang_aktivitas=?, sdki_pengisian=?, sdki_nadi=?, sdki_akral=?, "
                        + "sdki_warna=?, sdki_turgor=?, sdki_paras=?, sdki_nyeri=?, sdki_edema=?, sdki_penyembuhan=?, sdki_indeks=?, sdki_bruit=?, sdki_trombositopenia=?, sdki_dm=?, "
                        + "sdki_anemia=?, sdki_gagal=?, sdki_trombosis_arteri=?, sdki_varises=?, sdki_trombosis_vena=?, sdki_sindrom=?, sdki_kelainan=?, ket_slki_perfusi=?, slki_denyut=?, "
                        + "slki_penyembuhan=?, slki_sensasi=?, slki_warna=?, slki_edema=?, slki_nyeri=?, slki_paras=?, slki_kelemahan=?, slki_kram=?, slki_nekrosis=?, slki_pengisian=?, "
                        + "slki_akral=?, slki_turgor=?, slki_sistolik=?, slki_diastolik=?, siki_periksa=?, siki_identifikasi=?, siki_monitor=?, siki_hindari_infus=?, siki_hindari_tekanan=?, "
                        + "siki_hindari_penekanan=?, siki_pencegahan=?, siki_perawatan=?, siki_hidrasi=?, siki_anjur_berhenti=?, siki_anjur_berolahraga=?, siki_anjur_mengecek=?, "
                        + "siki_anjur_meminum=?, siki_ajarkan=?, siki_informasi=?, nip_petugas=?", 61, new String[]{
                            sdkiHiperglikemi, sdkiPenurunanKonsen, sdkiPeningkatan, sdkiKekurangan, sdkiPenurunanAliran, sdkiKurangFaktor,
                            sdkiKurangProses, sdkiKurangAktivitas, sdkiPengisian, sdkiNadi, sdkiAkral, sdkiWarna, sdkiTurgor, sdkiParas, sdkiNyeri, sdkiEdema, sdkiPenyembuhan,
                            sdkiIndeks, sdkiBruit, sdkiTrombositopenia, sdkiDm, sdkiAnemia, sdkiGagal, sdkiTrombosisArteri, sdkiVarises, sdkiTrombosisVena, sdkiSindrom, sdkiKelainan,
                            TketSlkiPerfusi.getText(), slkiDenyut, slkiPenyembuhan, slkiSensasi, slkiWarna, slkiEdema, slkiNyeri, slkiParas, slkiKelemahan, slkiKram, slkiNekrosis,
                            slkiPengisian, slkiAkral, slkiTurgor, slkiSistolik, slkiDiastolik, sikiPeriksa, sikiIdentifikasi, sikiMonitor, sikiHindariInfus, sikiHindariTekanan,
                            sikiHindariPenekanan, sikiPencegahan, sikiPerawatan, sikiHidrasi, sikiAnjurBerhenti, sikiAnjurBerolahraga, sikiAnjurMengecek, sikiAnjurMeminum, sikiAjarkan,
                            sikiInformasi, TnipPetugas.getText(),
                            tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 0).toString()
                        }) == true) {

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Masalah Keperawatan Perfusi Perifer Tidak Efektif", "Ganti");
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
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
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
        akses.setform("RMMasalahKeperawatanPerfusiPeriferTdkEfektif");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbMasalah.getSelectedRow() > -1) {
            if (akses.getadmin() == true || tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 69).toString().equals(akses.getkode())) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from masalah_keperawatan_perfusi_perifer_tdk_efektif where waktu_simpan=?", 1, new String[]{
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

            if (chkSdkiHiperglikemi.isSelected() == true) {
                param.put("sdkiHiperglikemi", "V");
            } else {
                param.put("sdkiHiperglikemi", "");
            }

            if (chkSdkiPenurunanKonsen.isSelected() == true) {
                param.put("sdkiPenurunanKonsen", "V");
            } else {
                param.put("sdkiPenurunanKonsen", "");
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

            if (chkSdkiPenurunanAliran.isSelected() == true) {
                param.put("sdkiPenurunanAliran", "V");
            } else {
                param.put("sdkiPenurunanAliran", "");
            }

            if (chkSdkiKurangFaktor.isSelected() == true) {
                param.put("sdkiKurangFaktor", "V");
            } else {
                param.put("sdkiKurangFaktor", "");
            }

            if (chkSdkiKurangProses.isSelected() == true) {
                param.put("sdkiKurangProses", "V");
            } else {
                param.put("sdkiKurangProses", "");
            }

            if (chkSdkiKurangAktivitas.isSelected() == true) {
                param.put("sdkiKurangAktivitas", "V");
            } else {
                param.put("sdkiKurangAktivitas", "");
            }

            if (chkSdkiPengisian.isSelected() == true) {
                param.put("sdkiPengisian", "V");
            } else {
                param.put("sdkiPengisian", "");
            }

            if (chkSdkiNadi.isSelected() == true) {
                param.put("sdkiNadi", "V");
            } else {
                param.put("sdkiNadi", "");
            }

            if (chkSdkiAkral.isSelected() == true) {
                param.put("sdkiAkral", "V");
            } else {
                param.put("sdkiAkral", "");
            }

            if (chkSdkiWarna.isSelected() == true) {
                param.put("sdkiWarna", "V");
            } else {
                param.put("sdkiWarna", "");
            }

            if (chkSdkiTurgor.isSelected() == true) {
                param.put("sdkiTurgor", "V");
            } else {
                param.put("sdkiTurgor", "");
            }

            if (chkSdkiParas.isSelected() == true) {
                param.put("sdkiParas", "V");
            } else {
                param.put("sdkiParas", "");
            }

            if (chkSdkiNyeri.isSelected() == true) {
                param.put("sdkiNyeri", "V");
            } else {
                param.put("sdkiNyeri", "");
            }

            if (chkSdkiEdema.isSelected() == true) {
                param.put("sdkiEdema", "V");
            } else {
                param.put("sdkiEdema", "");
            }

            if (chkSdkiPenyembuhan.isSelected() == true) {
                param.put("sdkiPenyembuhan", "V");
            } else {
                param.put("sdkiPenyembuhan", "");
            }

            if (chkSdkiIndeks.isSelected() == true) {
                param.put("sdkiIndeks", "V");
            } else {
                param.put("sdkiIndeks", "");
            }

            if (chkSdkiBruit.isSelected() == true) {
                param.put("sdkiBruit", "V");
            } else {
                param.put("sdkiBruit", "");
            }

            if (chkSdkiTrombositopenia.isSelected() == true) {
                param.put("sdkiTrombositopenia", "V");
            } else {
                param.put("sdkiTrombositopenia", "");
            }

            if (chkSdkiDm.isSelected() == true) {
                param.put("sdkiDm", "V");
            } else {
                param.put("sdkiDm", "");
            }

            if (chkSdkiAnemia.isSelected() == true) {
                param.put("sdkiAnemia", "V");
            } else {
                param.put("sdkiAnemia", "");
            }

            if (chkSdkiGagal.isSelected() == true) {
                param.put("sdkiGagal", "V");
            } else {
                param.put("sdkiGagal", "");
            }

            if (chkSdkiTrombosisArteri.isSelected() == true) {
                param.put("sdkiTrombosisArteri", "V");
            } else {
                param.put("sdkiTrombosisArteri", "");
            }

            if (chkSdkiVarises.isSelected() == true) {
                param.put("sdkiVarises", "V");
            } else {
                param.put("sdkiVarises", "");
            }

            if (chkSdkiTrombosisVena.isSelected() == true) {
                param.put("sdkiTrombosisVena", "V");
            } else {
                param.put("sdkiTrombosisVena", "");
            }

            if (chkSdkiSindrom.isSelected() == true) {
                param.put("sdkiSindrom", "V");
            } else {
                param.put("sdkiSindrom", "");
            }

            if (chkSdkiKelainan.isSelected() == true) {
                param.put("sdkiKelainan", "V");
            } else {
                param.put("sdkiKelainan", "");
            }
            
            if (TketSlkiPerfusi.getText().equals("")) {
                param.put("ketSlkiPerfusi", "...........");
            } else {
                param.put("ketSlkiPerfusi", TketSlkiPerfusi.getText());
            }

            if (chkSlkiDenyut.isSelected() == true) {
                param.put("slkiDenyut", "V");
            } else {
                param.put("slkiDenyut", "");
            }

            if (chkSlkiPenyembuhan.isSelected() == true) {
                param.put("slkiPenyembuhan", "V");
            } else {
                param.put("slkiPenyembuhan", "");
            }

            if (chkSlkiSensasi.isSelected() == true) {
                param.put("slkiSensasi", "V");
            } else {
                param.put("slkiSensasi", "");
            }

            if (chkSlkiWarna.isSelected() == true) {
                param.put("slkiWarna", "V");
            } else {
                param.put("slkiWarna", "");
            }

            if (chkSlkiEdema.isSelected() == true) {
                param.put("slkiEdema", "V");
            } else {
                param.put("slkiEdema", "");
            }

            if (chkSlkiNyeri.isSelected() == true) {
                param.put("slkiNyeri", "V");
            } else {
                param.put("slkiNyeri", "");
            }

            if (chkSlkiParas.isSelected() == true) {
                param.put("slkiParas", "V");
            } else {
                param.put("slkiParas", "");
            }

            if (chkSlkiKelemahan.isSelected() == true) {
                param.put("slkiKelemahan", "V");
            } else {
                param.put("slkiKelemahan", "");
            }

            if (chkSlkiKram.isSelected() == true) {
                param.put("slkiKram", "V");
            } else {
                param.put("slkiKram", "");
            }

            if (chkSlkiNekrosis.isSelected() == true) {
                param.put("slkiNekrosis", "V");
            } else {
                param.put("slkiNekrosis", "");
            }

            if (chkSlkiPengisian.isSelected() == true) {
                param.put("slkiPengisian", "V");
            } else {
                param.put("slkiPengisian", "");
            }

            if (chkSlkiAkral.isSelected() == true) {
                param.put("slkiAkral", "V");
            } else {
                param.put("slkiAkral", "");
            }

            if (chkSlkiTurgor.isSelected() == true) {
                param.put("slkiTurgor", "V");
            } else {
                param.put("slkiTurgor", "");
            }

            if (chkSlkiSistolik.isSelected() == true) {
                param.put("slkiSistolik", "V");
            } else {
                param.put("slkiSistolik", "");
            }

            if (chkSlkiDiastolik.isSelected() == true) {
                param.put("slkiDiastolik", "V");
            } else {
                param.put("slkiDiastolik", "");
            }

            if (chkSikiPeriksa.isSelected() == true) {
                param.put("sikiPeriksa", "V");
            } else {
                param.put("sikiPeriksa", "");
            }

            if (chkSikiIdentifikasi.isSelected() == true) {
                param.put("sikiIdentifikasi", "V");
            } else {
                param.put("sikiIdentifikasi", "");
            }

            if (chkSikiMonitor.isSelected() == true) {
                param.put("sikiMonitor", "V");
            } else {
                param.put("sikiMonitor", "");
            }

            if (chkSikiHindariInfus.isSelected() == true) {
                param.put("sikiHindariInfus", "V");
            } else {
                param.put("sikiHindariInfus", "");
            }

            if (chkSikiHindariTekanan.isSelected() == true) {
                param.put("sikiHindariTekanan", "V");
            } else {
                param.put("sikiHindariTekanan", "");
            }

            if (chkSikiHindariPenekanan.isSelected() == true) {
                param.put("sikiHindariPenekanan", "V");
            } else {
                param.put("sikiHindariPenekanan", "");
            }

            if (chkSikiPencegahan.isSelected() == true) {
                param.put("sikiPencegahan", "V");
            } else {
                param.put("sikiPencegahan", "");
            }

            if (chkSikiPerawatan.isSelected() == true) {
                param.put("sikiPerawatan", "V");
            } else {
                param.put("sikiPerawatan", "");
            }

            if (chkSikiHidrasi.isSelected() == true) {
                param.put("sikiHidrasi", "V");
            } else {
                param.put("sikiHidrasi", "");
            }

            if (chkSikiAnjurBerhenti.isSelected() == true) {
                param.put("sikiAnjurBerhenti", "V");
            } else {
                param.put("sikiAnjurBerhenti", "");
            }

            if (chkSikiAnjurBerolahraga.isSelected() == true) {
                param.put("sikiAnjurBerolahraga", "V");
            } else {
                param.put("sikiAnjurBerolahraga", "");
            }

            if (chkSikiAnjurMengecek.isSelected() == true) {
                param.put("sikiAnjurMengecek", "V");
            } else {
                param.put("sikiAnjurMengecek", "");
            }

            if (chkSikiAnjurMeminum.isSelected() == true) {
                param.put("sikiAnjurMeminum", "V");
            } else {
                param.put("sikiAnjurMeminum", "");
            }

            if (chkSikiAjarkan.isSelected() == true) {
                param.put("sikiAjarkan", "V");
            } else {
                param.put("sikiAjarkan", "");
            }

            if (chkSikiInformasi.isSelected() == true) {
                param.put("sikiInformasi", "V");
            } else {
                param.put("sikiInformasi", "");
            }

            Valid.MyReport("rptMasKepPerfusiPeriferTidakEfektif.jasper", "report", "::[ RM Masalah Keperawatan Perfusi Perifer Tidak Efektif ]::",
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

    private void chkSdkiTrombositopeniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSdkiTrombositopeniaActionPerformed
        TketSlkiPerfusi.setText("");
        if (chkSdkiTrombositopenia.isSelected() == true) {
            TketSlkiPerfusi.setEnabled(true);
            TketSlkiPerfusi.requestFocus();
        } else {
            TketSlkiPerfusi.setEnabled(false);
        }
    }//GEN-LAST:event_chkSdkiTrombositopeniaActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMMasalahKeperawatanPerfusiPeriferTdkEfektif dialog = new RMMasalahKeperawatanPerfusiPeriferTdkEfektif(new javax.swing.JFrame(), true);
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
    private widget.TextBox TketSlkiPerfusi;
    private widget.TextBox TnipPetugas;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TrgRawat;
    private widget.CekBox chkSdkiAkral;
    private widget.CekBox chkSdkiAnemia;
    private widget.CekBox chkSdkiBruit;
    private widget.CekBox chkSdkiDm;
    private widget.CekBox chkSdkiEdema;
    private widget.CekBox chkSdkiGagal;
    private widget.CekBox chkSdkiHiperglikemi;
    private widget.CekBox chkSdkiIndeks;
    private widget.CekBox chkSdkiKekurangan;
    private widget.CekBox chkSdkiKelainan;
    private widget.CekBox chkSdkiKurangAktivitas;
    private widget.CekBox chkSdkiKurangFaktor;
    private widget.CekBox chkSdkiKurangProses;
    private widget.CekBox chkSdkiNadi;
    private widget.CekBox chkSdkiNyeri;
    private widget.CekBox chkSdkiParas;
    private widget.CekBox chkSdkiPengisian;
    private widget.CekBox chkSdkiPeningkatan;
    private widget.CekBox chkSdkiPenurunanAliran;
    private widget.CekBox chkSdkiPenurunanKonsen;
    private widget.CekBox chkSdkiPenyembuhan;
    private widget.CekBox chkSdkiSindrom;
    private widget.CekBox chkSdkiTrombosisArteri;
    private widget.CekBox chkSdkiTrombosisVena;
    private widget.CekBox chkSdkiTrombositopenia;
    private widget.CekBox chkSdkiTurgor;
    private widget.CekBox chkSdkiVarises;
    private widget.CekBox chkSdkiWarna;
    private widget.CekBox chkSikiAjarkan;
    private widget.CekBox chkSikiAnjurBerhenti;
    private widget.CekBox chkSikiAnjurBerolahraga;
    private widget.CekBox chkSikiAnjurMeminum;
    private widget.CekBox chkSikiAnjurMengecek;
    private widget.CekBox chkSikiHidrasi;
    private widget.CekBox chkSikiHindariInfus;
    private widget.CekBox chkSikiHindariPenekanan;
    private widget.CekBox chkSikiHindariTekanan;
    private widget.CekBox chkSikiIdentifikasi;
    private widget.CekBox chkSikiInformasi;
    private widget.CekBox chkSikiMonitor;
    private widget.CekBox chkSikiPencegahan;
    private widget.CekBox chkSikiPerawatan;
    private widget.CekBox chkSikiPeriksa;
    private widget.CekBox chkSlkiAkral;
    private widget.CekBox chkSlkiDenyut;
    private widget.CekBox chkSlkiDiastolik;
    private widget.CekBox chkSlkiEdema;
    private widget.CekBox chkSlkiKelemahan;
    private widget.CekBox chkSlkiKram;
    private widget.CekBox chkSlkiNekrosis;
    private widget.CekBox chkSlkiNyeri;
    private widget.CekBox chkSlkiParas;
    private widget.CekBox chkSlkiPengisian;
    private widget.CekBox chkSlkiPenyembuhan;
    private widget.CekBox chkSlkiSensasi;
    private widget.CekBox chkSlkiSistolik;
    private widget.CekBox chkSlkiTurgor;
    private widget.CekBox chkSlkiWarna;
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
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel91;
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
                    + "pg.nama nmPetugas from masalah_keperawatan_perfusi_perifer_tdk_efektif m inner join reg_periksa rp on rp.no_rawat=m.no_rawat "
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
                        rs.getString("ruang_rawat"),
                        rs.getString("sdki_hiperglikemi"),
                        rs.getString("sdki_penurunan_konsen"),
                        rs.getString("sdki_peningkatan"),
                        rs.getString("sdki_kekurangan"),
                        rs.getString("sdki_penurunan_aliran"),
                        rs.getString("sdki_kurang_faktor"),
                        rs.getString("sdki_kurang_proses"),
                        rs.getString("sdki_kurang_aktivitas"),
                        rs.getString("sdki_pengisian"),
                        rs.getString("sdki_nadi"),
                        rs.getString("sdki_akral"),
                        rs.getString("sdki_warna"),
                        rs.getString("sdki_turgor"),
                        rs.getString("sdki_paras"),
                        rs.getString("sdki_nyeri"),
                        rs.getString("sdki_edema"),
                        rs.getString("sdki_penyembuhan"),
                        rs.getString("sdki_indeks"),
                        rs.getString("sdki_bruit"),
                        rs.getString("sdki_trombositopenia"),
                        rs.getString("sdki_dm"),
                        rs.getString("sdki_anemia"),
                        rs.getString("sdki_gagal"),
                        rs.getString("sdki_trombosis_arteri"),
                        rs.getString("sdki_varises"),
                        rs.getString("sdki_trombosis_vena"),
                        rs.getString("sdki_sindrom"),
                        rs.getString("sdki_kelainan"),
                        rs.getString("ket_slki_perfusi"),
                        rs.getString("slki_denyut"),
                        rs.getString("slki_penyembuhan"),
                        rs.getString("slki_sensasi"),
                        rs.getString("slki_warna"),
                        rs.getString("slki_edema"),
                        rs.getString("slki_nyeri"),
                        rs.getString("slki_paras"),
                        rs.getString("slki_kelemahan"),
                        rs.getString("slki_kram"),
                        rs.getString("slki_nekrosis"),
                        rs.getString("slki_pengisian"),
                        rs.getString("slki_akral"),
                        rs.getString("slki_turgor"),
                        rs.getString("slki_sistolik"),
                        rs.getString("slki_diastolik"),
                        rs.getString("siki_periksa"),
                        rs.getString("siki_identifikasi"),
                        rs.getString("siki_monitor"),
                        rs.getString("siki_hindari_infus"),
                        rs.getString("siki_hindari_tekanan"),
                        rs.getString("siki_hindari_penekanan"),
                        rs.getString("siki_pencegahan"),
                        rs.getString("siki_perawatan"),
                        rs.getString("siki_hidrasi"),
                        rs.getString("siki_anjur_berhenti"),
                        rs.getString("siki_anjur_berolahraga"),
                        rs.getString("siki_anjur_mengecek"),
                        rs.getString("siki_anjur_meminum"),
                        rs.getString("siki_ajarkan"),
                        rs.getString("siki_informasi"),
                        rs.getString("status_rawat"),
                        rs.getString("nip_petugas")                        
                    });
                }
            } catch (Exception e) {
                System.out.println("rekammedis.RMMasalahKeperawatanPerfusiPeriferTdkEfektif.tampil() : " + e);
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
        chkSdkiHiperglikemi.setSelected(false);
        chkSdkiPenurunanKonsen.setSelected(false);
        chkSdkiPeningkatan.setSelected(false);
        chkSdkiKekurangan.setSelected(false);
        chkSdkiPenurunanAliran.setSelected(false);
        chkSdkiKurangFaktor.setSelected(false);
        chkSdkiKurangProses.setSelected(false);
        chkSdkiKurangAktivitas.setSelected(false);
        chkSdkiPengisian.setSelected(false);
        chkSdkiNadi.setSelected(false);
        chkSdkiAkral.setSelected(false);
        chkSdkiWarna.setSelected(false);
        chkSdkiTurgor.setSelected(false);
        chkSdkiParas.setSelected(false);
        chkSdkiNyeri.setSelected(false);
        chkSdkiEdema.setSelected(false);
        chkSdkiPenyembuhan.setSelected(false);
        chkSdkiIndeks.setSelected(false);
        chkSdkiBruit.setSelected(false);
        chkSdkiTrombositopenia.setSelected(false);
        chkSdkiDm.setSelected(false);
        chkSdkiAnemia.setSelected(false);
        chkSdkiGagal.setSelected(false);
        chkSdkiTrombosisArteri.setSelected(false);
        chkSdkiVarises.setSelected(false);
        chkSdkiTrombosisVena.setSelected(false);
        chkSdkiSindrom.setSelected(false);
        chkSdkiKelainan.setSelected(false);
        TketSlkiPerfusi.setText("");
        chkSlkiDenyut.setSelected(false);
        chkSlkiPenyembuhan.setSelected(false);
        chkSlkiSensasi.setSelected(false);
        chkSlkiWarna.setSelected(false);
        chkSlkiEdema.setSelected(false);
        chkSlkiNyeri.setSelected(false);
        chkSlkiParas.setSelected(false);
        chkSlkiKelemahan.setSelected(false);
        chkSlkiKram.setSelected(false);
        chkSlkiNekrosis.setSelected(false);
        chkSlkiPengisian.setSelected(false);
        chkSlkiAkral.setSelected(false);
        chkSlkiTurgor.setSelected(false);
        chkSlkiSistolik.setSelected(false);
        chkSlkiDiastolik.setSelected(false);

        chkSikiPeriksa.setSelected(false);
        chkSikiIdentifikasi.setSelected(false);
        chkSikiMonitor.setSelected(false);
        chkSikiHindariInfus.setSelected(false);
        chkSikiHindariTekanan.setSelected(false);
        chkSikiHindariPenekanan.setSelected(false);
        chkSikiPencegahan.setSelected(false);
        chkSikiPerawatan.setSelected(false);
        chkSikiHidrasi.setSelected(false);
        chkSikiAnjurBerhenti.setSelected(false);
        chkSikiAnjurBerolahraga.setSelected(false);
        chkSikiAnjurMengecek.setSelected(false);
        chkSikiAnjurMeminum.setSelected(false);
        chkSikiAjarkan.setSelected(false);
        chkSikiInformasi.setSelected(false);
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

            sdkiHiperglikemi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 9).toString();
            sdkiPenurunanKonsen = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 10).toString();
            sdkiPeningkatan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 11).toString();
            sdkiKekurangan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 12).toString();
            sdkiPenurunanAliran = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 13).toString();
            sdkiKurangFaktor = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 14).toString();
            sdkiKurangProses = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 15).toString();
            sdkiKurangAktivitas = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 16).toString();
            sdkiPengisian = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 17).toString();
            sdkiNadi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 18).toString();
            sdkiAkral = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 19).toString();
            sdkiWarna = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 20).toString();
            sdkiTurgor = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 21).toString();
            sdkiParas = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 22).toString();
            sdkiNyeri = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 23).toString();
            sdkiEdema = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 24).toString();
            sdkiPenyembuhan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 25).toString();
            sdkiIndeks = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 26).toString();
            sdkiBruit = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 27).toString();
            sdkiTrombositopenia = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 28).toString();
            sdkiDm = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 29).toString();
            sdkiAnemia = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 30).toString();
            sdkiGagal = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 31).toString();
            sdkiTrombosisArteri = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 32).toString();
            sdkiVarises = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 33).toString();
            sdkiTrombosisVena = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 34).toString();
            sdkiSindrom = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 35).toString();
            sdkiKelainan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 36).toString();

            TketSlkiPerfusi.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 37).toString());

            slkiDenyut = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 38).toString();
            slkiPenyembuhan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 39).toString();
            slkiSensasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 40).toString();
            slkiWarna = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 41).toString();
            slkiEdema = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 42).toString();
            slkiNyeri = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 43).toString();
            slkiParas = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 44).toString();
            slkiKelemahan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 45).toString();
            slkiKram = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 46).toString();
            slkiNekrosis = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 47).toString();
            slkiPengisian = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 48).toString();
            slkiAkral = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 49).toString();
            slkiTurgor = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 50).toString();
            slkiSistolik = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 51).toString();
            slkiDiastolik = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 52).toString();

            sikiPeriksa = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 53).toString();
            sikiIdentifikasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 54).toString();
            sikiMonitor = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 55).toString();
            sikiHindariInfus = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 56).toString();
            sikiHindariTekanan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 57).toString();
            sikiHindariPenekanan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 58).toString();
            sikiPencegahan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 59).toString();
            sikiPerawatan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 60).toString();
            sikiHidrasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 61).toString();
            sikiAnjurBerhenti = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 62).toString();
            sikiAnjurBerolahraga = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 63).toString();
            sikiAnjurMengecek = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 64).toString();
            sikiAnjurMeminum = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 65).toString();
            sikiAjarkan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 66).toString();
            sikiInformasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 67).toString();

            TnipPetugas.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 69).toString());
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
        if (sdkiHiperglikemi.equals("ya")) {
            chkSdkiHiperglikemi.setSelected(true);
        } else {
            chkSdkiHiperglikemi.setSelected(false);
        }

        if (sdkiPenurunanKonsen.equals("ya")) {
            chkSdkiPenurunanKonsen.setSelected(true);
        } else {
            chkSdkiPenurunanKonsen.setSelected(false);
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

        if (sdkiPenurunanAliran.equals("ya")) {
            chkSdkiPenurunanAliran.setSelected(true);
        } else {
            chkSdkiPenurunanAliran.setSelected(false);
        }

        if (sdkiKurangFaktor.equals("ya")) {
            chkSdkiKurangFaktor.setSelected(true);
        } else {
            chkSdkiKurangFaktor.setSelected(false);
        }

        if (sdkiKurangProses.equals("ya")) {
            chkSdkiKurangProses.setSelected(true);
        } else {
            chkSdkiKurangProses.setSelected(false);
        }

        if (sdkiKurangAktivitas.equals("ya")) {
            chkSdkiKurangAktivitas.setSelected(true);
        } else {
            chkSdkiKurangAktivitas.setSelected(false);
        }

        if (sdkiPengisian.equals("ya")) {
            chkSdkiPengisian.setSelected(true);
        } else {
            chkSdkiPengisian.setSelected(false);
        }

        if (sdkiNadi.equals("ya")) {
            chkSdkiNadi.setSelected(true);
        } else {
            chkSdkiNadi.setSelected(false);
        }

        if (sdkiAkral.equals("ya")) {
            chkSdkiAkral.setSelected(true);
        } else {
            chkSdkiAkral.setSelected(false);
        }

        if (sdkiWarna.equals("ya")) {
            chkSdkiWarna.setSelected(true);
        } else {
            chkSdkiWarna.setSelected(false);
        }

        if (sdkiTurgor.equals("ya")) {
            chkSdkiTurgor.setSelected(true);
        } else {
            chkSdkiTurgor.setSelected(false);
        }

        if (sdkiParas.equals("ya")) {
            chkSdkiParas.setSelected(true);
        } else {
            chkSdkiParas.setSelected(false);
        }

        if (sdkiNyeri.equals("ya")) {
            chkSdkiNyeri.setSelected(true);
        } else {
            chkSdkiNyeri.setSelected(false);
        }

        if (sdkiEdema.equals("ya")) {
            chkSdkiEdema.setSelected(true);
        } else {
            chkSdkiEdema.setSelected(false);
        }

        if (sdkiPenyembuhan.equals("ya")) {
            chkSdkiPenyembuhan.setSelected(true);
        } else {
            chkSdkiPenyembuhan.setSelected(false);
        }

        if (sdkiIndeks.equals("ya")) {
            chkSdkiIndeks.setSelected(true);
        } else {
            chkSdkiIndeks.setSelected(false);
        }

        if (sdkiBruit.equals("ya")) {
            chkSdkiBruit.setSelected(true);
        } else {
            chkSdkiBruit.setSelected(false);
        }

        if (sdkiTrombositopenia.equals("ya")) {
            chkSdkiTrombositopenia.setSelected(true);
        } else {
            chkSdkiTrombositopenia.setSelected(false);
        }

        if (sdkiDm.equals("ya")) {
            chkSdkiDm.setSelected(true);
        } else {
            chkSdkiDm.setSelected(false);
        }

        if (sdkiAnemia.equals("ya")) {
            chkSdkiAnemia.setSelected(true);
        } else {
            chkSdkiAnemia.setSelected(false);
        }

        if (sdkiGagal.equals("ya")) {
            chkSdkiGagal.setSelected(true);
        } else {
            chkSdkiGagal.setSelected(false);
        }

        if (sdkiTrombosisArteri.equals("ya")) {
            chkSdkiTrombosisArteri.setSelected(true);
        } else {
            chkSdkiTrombosisArteri.setSelected(false);
        }

        if (sdkiVarises.equals("ya")) {
            chkSdkiVarises.setSelected(true);
        } else {
            chkSdkiVarises.setSelected(false);
        }

        if (sdkiTrombosisVena.equals("ya")) {
            chkSdkiTrombosisVena.setSelected(true);
        } else {
            chkSdkiTrombosisVena.setSelected(false);
        }

        if (sdkiSindrom.equals("ya")) {
            chkSdkiSindrom.setSelected(true);
        } else {
            chkSdkiSindrom.setSelected(false);
        }

        if (sdkiKelainan.equals("ya")) {
            chkSdkiKelainan.setSelected(true);
        } else {
            chkSdkiKelainan.setSelected(false);
        }

        if (slkiDenyut.equals("ya")) {
            chkSlkiDenyut.setSelected(true);
        } else {
            chkSlkiDenyut.setSelected(false);
        }

        if (slkiPenyembuhan.equals("ya")) {
            chkSlkiPenyembuhan.setSelected(true);
        } else {
            chkSlkiPenyembuhan.setSelected(false);
        }

        if (slkiSensasi.equals("ya")) {
            chkSlkiSensasi.setSelected(true);
        } else {
            chkSlkiSensasi.setSelected(false);
        }

        if (slkiWarna.equals("ya")) {
            chkSlkiWarna.setSelected(true);
        } else {
            chkSlkiWarna.setSelected(false);
        }

        if (slkiEdema.equals("ya")) {
            chkSlkiEdema.setSelected(true);
        } else {
            chkSlkiEdema.setSelected(false);
        }

        if (slkiNyeri.equals("ya")) {
            chkSlkiNyeri.setSelected(true);
        } else {
            chkSlkiNyeri.setSelected(false);
        }

        if (slkiParas.equals("ya")) {
            chkSlkiParas.setSelected(true);
        } else {
            chkSlkiParas.setSelected(false);
        }

        if (slkiKelemahan.equals("ya")) {
            chkSlkiKelemahan.setSelected(true);
        } else {
            chkSlkiKelemahan.setSelected(false);
        }

        if (slkiKram.equals("ya")) {
            chkSlkiKram.setSelected(true);
        } else {
            chkSlkiKram.setSelected(false);
        }

        if (slkiNekrosis.equals("ya")) {
            chkSlkiNekrosis.setSelected(true);
        } else {
            chkSlkiNekrosis.setSelected(false);
        }

        if (slkiPengisian.equals("ya")) {
            chkSlkiPengisian.setSelected(true);
        } else {
            chkSlkiPengisian.setSelected(false);
        }

        if (slkiAkral.equals("ya")) {
            chkSlkiAkral.setSelected(true);
        } else {
            chkSlkiAkral.setSelected(false);
        }

        if (slkiTurgor.equals("ya")) {
            chkSlkiTurgor.setSelected(true);
        } else {
            chkSlkiTurgor.setSelected(false);
        }

        if (slkiSistolik.equals("ya")) {
            chkSlkiSistolik.setSelected(true);
        } else {
            chkSlkiSistolik.setSelected(false);
        }

        if (slkiDiastolik.equals("ya")) {
            chkSlkiDiastolik.setSelected(true);
        } else {
            chkSlkiDiastolik.setSelected(false);
        }

        if (sikiPeriksa.equals("ya")) {
            chkSikiPeriksa.setSelected(true);
        } else {
            chkSikiPeriksa.setSelected(false);
        }

        if (sikiIdentifikasi.equals("ya")) {
            chkSikiIdentifikasi.setSelected(true);
        } else {
            chkSikiIdentifikasi.setSelected(false);
        }

        if (sikiMonitor.equals("ya")) {
            chkSikiMonitor.setSelected(true);
        } else {
            chkSikiMonitor.setSelected(false);
        }

        if (sikiHindariInfus.equals("ya")) {
            chkSikiHindariInfus.setSelected(true);
        } else {
            chkSikiHindariInfus.setSelected(false);
        }

        if (sikiHindariTekanan.equals("ya")) {
            chkSikiHindariTekanan.setSelected(true);
        } else {
            chkSikiHindariTekanan.setSelected(false);
        }

        if (sikiHindariPenekanan.equals("ya")) {
            chkSikiHindariPenekanan.setSelected(true);
        } else {
            chkSikiHindariPenekanan.setSelected(false);
        }

        if (sikiPencegahan.equals("ya")) {
            chkSikiPencegahan.setSelected(true);
        } else {
            chkSikiPencegahan.setSelected(false);
        }

        if (sikiPerawatan.equals("ya")) {
            chkSikiPerawatan.setSelected(true);
        } else {
            chkSikiPerawatan.setSelected(false);
        }

        if (sikiHidrasi.equals("ya")) {
            chkSikiHidrasi.setSelected(true);
        } else {
            chkSikiHidrasi.setSelected(false);
        }

        if (sikiAnjurBerhenti.equals("ya")) {
            chkSikiAnjurBerhenti.setSelected(true);
        } else {
            chkSikiAnjurBerhenti.setSelected(false);
        }

        if (sikiAnjurBerolahraga.equals("ya")) {
            chkSikiAnjurBerolahraga.setSelected(true);
        } else {
            chkSikiAnjurBerolahraga.setSelected(false);
        }

        if (sikiAnjurMengecek.equals("ya")) {
            chkSikiAnjurMengecek.setSelected(true);
        } else {
            chkSikiAnjurMengecek.setSelected(false);
        }

        if (sikiAnjurMeminum.equals("ya")) {
            chkSikiAnjurMeminum.setSelected(true);
        } else {
            chkSikiAnjurMeminum.setSelected(false);
        }

        if (sikiAjarkan.equals("ya")) {
            chkSikiAjarkan.setSelected(true);
        } else {
            chkSikiAjarkan.setSelected(false);
        }

        if (sikiInformasi.equals("ya")) {
            chkSikiInformasi.setSelected(true);
        } else {
            chkSikiInformasi.setSelected(false);
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
        if (chkSdkiHiperglikemi.isSelected() == true) {
            sdkiHiperglikemi = "ya";
        } else {
            sdkiHiperglikemi = "tidak";
        }

        if (chkSdkiPenurunanKonsen.isSelected() == true) {
            sdkiPenurunanKonsen = "ya";
        } else {
            sdkiPenurunanKonsen = "tidak";
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

        if (chkSdkiPenurunanAliran.isSelected() == true) {
            sdkiPenurunanAliran = "ya";
        } else {
            sdkiPenurunanAliran = "tidak";
        }

        if (chkSdkiKurangFaktor.isSelected() == true) {
            sdkiKurangFaktor = "ya";
        } else {
            sdkiKurangFaktor = "tidak";
        }

        if (chkSdkiKurangProses.isSelected() == true) {
            sdkiKurangProses = "ya";
        } else {
            sdkiKurangProses = "tidak";
        }

        if (chkSdkiKurangAktivitas.isSelected() == true) {
            sdkiKurangAktivitas = "ya";
        } else {
            sdkiKurangAktivitas = "tidak";
        }

        if (chkSdkiPengisian.isSelected() == true) {
            sdkiPengisian = "ya";
        } else {
            sdkiPengisian = "tidak";
        }

        if (chkSdkiNadi.isSelected() == true) {
            sdkiNadi = "ya";
        } else {
            sdkiNadi = "tidak";
        }

        if (chkSdkiAkral.isSelected() == true) {
            sdkiAkral = "ya";
        } else {
            sdkiAkral = "tidak";
        }

        if (chkSdkiWarna.isSelected() == true) {
            sdkiWarna = "ya";
        } else {
            sdkiWarna = "tidak";
        }

        if (chkSdkiTurgor.isSelected() == true) {
            sdkiTurgor = "ya";
        } else {
            sdkiTurgor = "tidak";
        }

        if (chkSdkiParas.isSelected() == true) {
            sdkiParas = "ya";
        } else {
            sdkiParas = "tidak";
        }

        if (chkSdkiNyeri.isSelected() == true) {
            sdkiNyeri = "ya";
        } else {
            sdkiNyeri = "tidak";
        }

        if (chkSdkiEdema.isSelected() == true) {
            sdkiEdema = "ya";
        } else {
            sdkiEdema = "tidak";
        }

        if (chkSdkiPenyembuhan.isSelected() == true) {
            sdkiPenyembuhan = "ya";
        } else {
            sdkiPenyembuhan = "tidak";
        }

        if (chkSdkiIndeks.isSelected() == true) {
            sdkiIndeks = "ya";
        } else {
            sdkiIndeks = "tidak";
        }

        if (chkSdkiBruit.isSelected() == true) {
            sdkiBruit = "ya";
        } else {
            sdkiBruit = "tidak";
        }

        if (chkSdkiTrombositopenia.isSelected() == true) {
            sdkiTrombositopenia = "ya";
        } else {
            sdkiTrombositopenia = "tidak";
        }

        if (chkSdkiDm.isSelected() == true) {
            sdkiDm = "ya";
        } else {
            sdkiDm = "tidak";
        }

        if (chkSdkiAnemia.isSelected() == true) {
            sdkiAnemia = "ya";
        } else {
            sdkiAnemia = "tidak";
        }

        if (chkSdkiGagal.isSelected() == true) {
            sdkiGagal = "ya";
        } else {
            sdkiGagal = "tidak";
        }

        if (chkSdkiTrombosisArteri.isSelected() == true) {
            sdkiTrombosisArteri = "ya";
        } else {
            sdkiTrombosisArteri = "tidak";
        }

        if (chkSdkiVarises.isSelected() == true) {
            sdkiVarises = "ya";
        } else {
            sdkiVarises = "tidak";
        }

        if (chkSdkiTrombosisVena.isSelected() == true) {
            sdkiTrombosisVena = "ya";
        } else {
            sdkiTrombosisVena = "tidak";
        }

        if (chkSdkiSindrom.isSelected() == true) {
            sdkiSindrom = "ya";
        } else {
            sdkiSindrom = "tidak";
        }

        if (chkSdkiKelainan.isSelected() == true) {
            sdkiKelainan = "ya";
        } else {
            sdkiKelainan = "tidak";
        }

        if (chkSlkiDenyut.isSelected() == true) {
            slkiDenyut = "ya";
        } else {
            slkiDenyut = "tidak";
        }

        if (chkSlkiPenyembuhan.isSelected() == true) {
            slkiPenyembuhan = "ya";
        } else {
            slkiPenyembuhan = "tidak";
        }

        if (chkSlkiSensasi.isSelected() == true) {
            slkiSensasi = "ya";
        } else {
            slkiSensasi = "tidak";
        }

        if (chkSlkiWarna.isSelected() == true) {
            slkiWarna = "ya";
        } else {
            slkiWarna = "tidak";
        }

        if (chkSlkiEdema.isSelected() == true) {
            slkiEdema = "ya";
        } else {
            slkiEdema = "tidak";
        }

        if (chkSlkiNyeri.isSelected() == true) {
            slkiNyeri = "ya";
        } else {
            slkiNyeri = "tidak";
        }

        if (chkSlkiParas.isSelected() == true) {
            slkiParas = "ya";
        } else {
            slkiParas = "tidak";
        }

        if (chkSlkiKelemahan.isSelected() == true) {
            slkiKelemahan = "ya";
        } else {
            slkiKelemahan = "tidak";
        }

        if (chkSlkiKram.isSelected() == true) {
            slkiKram = "ya";
        } else {
            slkiKram = "tidak";
        }

        if (chkSlkiNekrosis.isSelected() == true) {
            slkiNekrosis = "ya";
        } else {
            slkiNekrosis = "tidak";
        }

        if (chkSlkiPengisian.isSelected() == true) {
            slkiPengisian = "ya";
        } else {
            slkiPengisian = "tidak";
        }

        if (chkSlkiAkral.isSelected() == true) {
            slkiAkral = "ya";
        } else {
            slkiAkral = "tidak";
        }

        if (chkSlkiTurgor.isSelected() == true) {
            slkiTurgor = "ya";
        } else {
            slkiTurgor = "tidak";
        }

        if (chkSlkiSistolik.isSelected() == true) {
            slkiSistolik = "ya";
        } else {
            slkiSistolik = "tidak";
        }

        if (chkSlkiDiastolik.isSelected() == true) {
            slkiDiastolik = "ya";
        } else {
            slkiDiastolik = "tidak";
        }

        if (chkSikiPeriksa.isSelected() == true) {
            sikiPeriksa = "ya";
        } else {
            sikiPeriksa = "tidak";
        }

        if (chkSikiIdentifikasi.isSelected() == true) {
            sikiIdentifikasi = "ya";
        } else {
            sikiIdentifikasi = "tidak";
        }

        if (chkSikiMonitor.isSelected() == true) {
            sikiMonitor = "ya";
        } else {
            sikiMonitor = "tidak";
        }

        if (chkSikiHindariInfus.isSelected() == true) {
            sikiHindariInfus = "ya";
        } else {
            sikiHindariInfus = "tidak";
        }

        if (chkSikiHindariTekanan.isSelected() == true) {
            sikiHindariTekanan = "ya";
        } else {
            sikiHindariTekanan = "tidak";
        }

        if (chkSikiHindariPenekanan.isSelected() == true) {
            sikiHindariPenekanan = "ya";
        } else {
            sikiHindariPenekanan = "tidak";
        }

        if (chkSikiPencegahan.isSelected() == true) {
            sikiPencegahan = "ya";
        } else {
            sikiPencegahan = "tidak";
        }

        if (chkSikiPerawatan.isSelected() == true) {
            sikiPerawatan = "ya";
        } else {
            sikiPerawatan = "tidak";
        }

        if (chkSikiHidrasi.isSelected() == true) {
            sikiHidrasi = "ya";
        } else {
            sikiHidrasi = "tidak";
        }

        if (chkSikiAnjurBerhenti.isSelected() == true) {
            sikiAnjurBerhenti = "ya";
        } else {
            sikiAnjurBerhenti = "tidak";
        }

        if (chkSikiAnjurBerolahraga.isSelected() == true) {
            sikiAnjurBerolahraga = "ya";
        } else {
            sikiAnjurBerolahraga = "tidak";
        }

        if (chkSikiAnjurMengecek.isSelected() == true) {
            sikiAnjurMengecek = "ya";
        } else {
            sikiAnjurMengecek = "tidak";
        }

        if (chkSikiAnjurMeminum.isSelected() == true) {
            sikiAnjurMeminum = "ya";
        } else {
            sikiAnjurMeminum = "tidak";
        }

        if (chkSikiAjarkan.isSelected() == true) {
            sikiAjarkan = "ya";
        } else {
            sikiAjarkan = "tidak";
        }

        if (chkSikiInformasi.isSelected() == true) {
            sikiInformasi = "ya";
        } else {
            sikiInformasi = "tidak";
        }
    }
    
    private void emptVariabel() {
        sdkiHiperglikemi = "";
        sdkiPenurunanKonsen = "";
        sdkiPeningkatan = "";
        sdkiKekurangan = "";
        sdkiPenurunanAliran = "";
        sdkiKurangFaktor = "";
        sdkiKurangProses = "";
        sdkiKurangAktivitas = "";
        sdkiPengisian = "";
        sdkiNadi = "";
        sdkiAkral = "";
        sdkiWarna = "";
        sdkiTurgor = "";
        sdkiParas = "";
        sdkiNyeri = "";
        sdkiEdema = "";
        sdkiPenyembuhan = "";
        sdkiIndeks = "";
        sdkiBruit = "";
        sdkiTrombositopenia = "";
        sdkiDm = "";
        sdkiAnemia = "";
        sdkiGagal = "";
        sdkiTrombosisArteri = "";
        sdkiVarises = "";
        sdkiTrombosisVena = "";
        sdkiSindrom = "";
        sdkiKelainan = "";
        slkiDenyut = "";
        slkiPenyembuhan = "";
        slkiSensasi = "";
        slkiWarna = "";
        slkiEdema = "";
        slkiNyeri = "";
        slkiParas = "";
        slkiKelemahan = "";
        slkiKram = "";
        slkiNekrosis = "";
        slkiPengisian = "";
        slkiAkral = "";
        slkiTurgor = "";
        slkiSistolik = "";
        slkiDiastolik = "";
        sikiPeriksa = "";
        sikiIdentifikasi = "";
        sikiMonitor = "";
        sikiHindariInfus = "";
        sikiHindariTekanan = "";
        sikiHindariPenekanan = "";
        sikiPencegahan = "";
        sikiPerawatan = "";
        sikiHidrasi = "";
        sikiAnjurBerhenti = "";
        sikiAnjurBerolahraga = "";
        sikiAnjurMengecek = "";
        sikiAnjurMeminum = "";
        sikiAjarkan = "";
        sikiInformasi = "";
    }
}
