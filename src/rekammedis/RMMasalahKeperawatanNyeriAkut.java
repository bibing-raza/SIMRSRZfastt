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
public class RMMasalahKeperawatanNyeriAkut extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String sdkiAgenFisiologis = "", sdkiAgenKimiawi = "", sdkiAgenFisik = "", sdkiMengeluh = "", sdkiTampak = "", sdkiBersikap = "", sdkiGelisah = "",
            sdkiFrekuensi = "", sdkiSulit = "", sdkiTekanan = "", sdkiPola = "", sdkiNafsu = "", sdkiProses = "", sdkiMenarik = "", sdkiBerfokus = "",
            sdkiDiaforesis = "", sdkiKondisi = "", sdkiCedera = "", sdkiInfeksi = "", sdkiSindrom = "", sdkiGlaukoma = "", slkiTingkat = "", slkiKeluhan = "",
            slkiMeringis = "", slkiSikap = "", slkiGelisah = "", slkiKesulitan = "", slkiMenarik = "", slkiPerasaan = "", sikiIdenLokasi = "", sikiIdenSkala = "",
            sikiIdenRespon = "", sikiIdenFaktor = "", sikiIdenPengetahuan = "", sikiIdenPengaruh = "", sikiMonKeberhasilan = "", sikiMonEfek = "",
            sikiBerikan = "", sikiKontrol = "", sikiFasilitas = "", sikiPertimbangkanJenis = "", sikiJelasPenyebab = "", sikiJelasStrategi = "",
            sikiAnjurMemonitor = "", sikiAnjurMenggunakan = "", sikiAnjurTeknik = "", sikiIdenKarakteristik = "", sikiIdenRiwayat = "", sikiIdenKesesusian = "",
            sikiMonitorTanda = "", sikiDiskusikan = "", sikiPertimbangkanGuna = "", sikiTetapkan = "", sikiDokumentasi = "", sikiJelaskan = "", sikiKolaborasi = "",
            sttsRawat = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMMasalahKeperawatanNyeriAkut(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "waktu_simpan", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Simpan", "Nama Petugas",
            "ruang_rawat", "sdki_agen_fisiologis", "sdki_agen_kimiawi", "sdki_agen_fisik", "sdki_mengeluh", "sdki_tampak", "sdki_bersikap", "sdki_gelisah", "sdki_frekuensi",
            "sdki_sulit", "sdki_tekanan", "sdki_pola", "sdki_nafsu", "sdki_proses", "sdki_menarik", "sdki_berfokus", "sdki_diaforesis", "sdki_kondisi", "sdki_cedera", "sdki_infeksi",
            "sdki_sindrom", "sdki_glaukoma", "slki_tingkat", "ket_slki_tingkat", "slki_keluhan", "slki_meringis", "slki_sikap", "slki_gelisah", "slki_kesulitan", "slki_menarik",
            "slki_perasaan", "siki_iden_lokasi", "siki_iden_skala", "siki_iden_respon", "siki_iden_faktor", "siki_iden_pengetahuan", "siki_iden_pengaruh", "siki_mon_keberhasilan",
            "siki_mon_efek", "siki_berikan", "siki_kontrol", "siki_fasilitas", "siki_pertimbangkan_jenis", "siki_jelas_penyebab", "siki_jelas_strategi", "siki_anjur_memonitor",
            "siki_anjur_menggunakan", "siki_anjur_teknik", "ket_kolaborasi_manajemen", "siki_iden_karakteristik", "siki_iden_riwayat", "siki_iden_kesesusian", "siki_monitor_tanda",
            "siki_diskusikan", "siki_pertimbangkan_guna", "siki_tetapkan", "siki_dokumentasi", "siki_jelaskan", "siki_kolaborasi", "ket_kolaborasi_pemberian", "status_rawat", 
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

        TketSlkiTingkat.setDocument(new batasInput((int) 20).getKata(TketSlkiTingkat));
        TketKolaborasiManajemen.setDocument(new batasInput((int) 100).getKata(TketKolaborasiManajemen));
        TketKolaborasiPemberian.setDocument(new batasInput((int) 100).getKata(TketKolaborasiPemberian));
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
                if (akses.getform().equals("RMMasalahKeperawatanNyeriAkut")) {
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
        chkSdkiAgenFisiologis = new widget.CekBox();
        chkSdkiAgenKimiawi = new widget.CekBox();
        chkSdkiAgenFisik = new widget.CekBox();
        chkSdkiMengeluh = new widget.CekBox();
        chkSdkiTampak = new widget.CekBox();
        chkSdkiBersikap = new widget.CekBox();
        chkSdkiGelisah = new widget.CekBox();
        chkSdkiFrekuensi = new widget.CekBox();
        chkSdkiSulit = new widget.CekBox();
        chkSdkiKondisi = new widget.CekBox();
        chkSdkiCedera = new widget.CekBox();
        chkSlkiTingkat = new widget.CekBox();
        chkSdkiInfeksi = new widget.CekBox();
        chkSdkiSindrom = new widget.CekBox();
        chkSdkiGlaukoma = new widget.CekBox();
        chkSlkiKeluhan = new widget.CekBox();
        chkSlkiMeringis = new widget.CekBox();
        chkSlkiGelisah = new widget.CekBox();
        chkSlkiSikap = new widget.CekBox();
        jLabel74 = new widget.Label();
        jLabel75 = new widget.Label();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        chkSdkiTekanan = new widget.CekBox();
        chkSdkiPola = new widget.CekBox();
        chkSdkiNafsu = new widget.CekBox();
        chkSdkiProses = new widget.CekBox();
        chkSdkiMenarik = new widget.CekBox();
        chkSdkiBerfokus = new widget.CekBox();
        chkSdkiDiaforesis = new widget.CekBox();
        jLabel78 = new widget.Label();
        jLabel79 = new widget.Label();
        TketSlkiTingkat = new widget.TextBox();
        jLabel80 = new widget.Label();
        chkSlkiKesulitan = new widget.CekBox();
        chkSlkiMenarik = new widget.CekBox();
        chkSlkiPerasaan = new widget.CekBox();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        chkSikiIdenLokasi = new widget.CekBox();
        chkSikiIdenSkala = new widget.CekBox();
        chkSikiIdenRespon = new widget.CekBox();
        chkSikiIdenFaktor = new widget.CekBox();
        chkSikiIdenPengetahuan = new widget.CekBox();
        chkSikiIdenPengaruh = new widget.CekBox();
        chkSikiMonKeberhasilan = new widget.CekBox();
        chkSikiMonEfek = new widget.CekBox();
        jLabel83 = new widget.Label();
        chkSikiBerikan = new widget.CekBox();
        chkSikiKontrol = new widget.CekBox();
        chkSikiFasilitas = new widget.CekBox();
        chkSikiPertimbangkanJenis = new widget.CekBox();
        jLabel84 = new widget.Label();
        chkSikiJelasPenyebab = new widget.CekBox();
        chkSikiJelasStrategi = new widget.CekBox();
        chkSikiAnjurMemonitor = new widget.CekBox();
        chkSikiAnjurMenggunakan = new widget.CekBox();
        chkSikiAnjurTeknik = new widget.CekBox();
        jLabel85 = new widget.Label();
        TketKolaborasiManajemen = new widget.TextBox();
        jLabel86 = new widget.Label();
        jLabel87 = new widget.Label();
        chkSikiIdenKarakteristik = new widget.CekBox();
        chkSikiIdenRiwayat = new widget.CekBox();
        chkSikiIdenKesesusian = new widget.CekBox();
        chkSikiMonitorTanda = new widget.CekBox();
        jLabel88 = new widget.Label();
        chkSikiDiskusikan = new widget.CekBox();
        chkSikiPertimbangkanGuna = new widget.CekBox();
        chkSikiTetapkan = new widget.CekBox();
        chkSikiDokumentasi = new widget.CekBox();
        chkSikiJelaskan = new widget.CekBox();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        chkSikiKolaborasi = new widget.CekBox();
        TketKolaborasiPemberian = new widget.TextBox();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Masalah Keperawatan Nyeri Akut ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(760, 1810));
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
        label20.setBounds(0, 1768, 140, 23);

        TnipPetugas.setEditable(false);
        TnipPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnipPetugas.setName("TnipPetugas"); // NOI18N
        TnipPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipPetugas);
        TnipPetugas.setBounds(145, 1768, 150, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        TnmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(300, 1768, 360, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setToolTipText("Alt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(664, 1768, 28, 23);

        chkSdkiAgenFisiologis.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiAgenFisiologis.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiAgenFisiologis.setText("Agen pencedera fisiologis (mis. Inflamasi, iskemia, neoplasma)");
        chkSdkiAgenFisiologis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiAgenFisiologis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiAgenFisiologis.setName("chkSdkiAgenFisiologis"); // NOI18N
        chkSdkiAgenFisiologis.setOpaque(false);
        chkSdkiAgenFisiologis.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiAgenFisiologis);
        chkSdkiAgenFisiologis.setBounds(145, 122, 340, 23);

        chkSdkiAgenKimiawi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiAgenKimiawi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiAgenKimiawi.setText("Agen pencedera kimiawi (mis.terbakar, bahan kimia iritan)");
        chkSdkiAgenKimiawi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiAgenKimiawi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiAgenKimiawi.setName("chkSdkiAgenKimiawi"); // NOI18N
        chkSdkiAgenKimiawi.setOpaque(false);
        chkSdkiAgenKimiawi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiAgenKimiawi);
        chkSdkiAgenKimiawi.setBounds(145, 150, 320, 23);

        chkSdkiAgenFisik.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiAgenFisik.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiAgenFisik.setText("<html>Agen pencedera fisik (mis.abses, amputasi,terbakar, terpotong, mengangkat berat, prosedur operasi, trauma, latihan<br>fisik berlebihan</html>");
        chkSdkiAgenFisik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiAgenFisik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiAgenFisik.setName("chkSdkiAgenFisik"); // NOI18N
        chkSdkiAgenFisik.setOpaque(false);
        chkSdkiAgenFisik.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiAgenFisik);
        chkSdkiAgenFisik.setBounds(145, 178, 610, 30);

        chkSdkiMengeluh.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMengeluh.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMengeluh.setText("Mengeluh nyeri");
        chkSdkiMengeluh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMengeluh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMengeluh.setName("chkSdkiMengeluh"); // NOI18N
        chkSdkiMengeluh.setOpaque(false);
        chkSdkiMengeluh.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMengeluh);
        chkSdkiMengeluh.setBounds(145, 241, 110, 23);

        chkSdkiTampak.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTampak.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTampak.setText("Tampak meringis");
        chkSdkiTampak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTampak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTampak.setName("chkSdkiTampak"); // NOI18N
        chkSdkiTampak.setOpaque(false);
        chkSdkiTampak.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTampak);
        chkSdkiTampak.setBounds(145, 269, 120, 23);

        chkSdkiBersikap.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiBersikap.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiBersikap.setText("Bersikap protektif (mis.waspada, posisi menghindari nyeri)");
        chkSdkiBersikap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiBersikap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiBersikap.setName("chkSdkiBersikap"); // NOI18N
        chkSdkiBersikap.setOpaque(false);
        chkSdkiBersikap.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiBersikap);
        chkSdkiBersikap.setBounds(145, 297, 310, 23);

        chkSdkiGelisah.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiGelisah.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiGelisah.setText("Gelisah");
        chkSdkiGelisah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiGelisah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiGelisah.setName("chkSdkiGelisah"); // NOI18N
        chkSdkiGelisah.setOpaque(false);
        chkSdkiGelisah.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiGelisah);
        chkSdkiGelisah.setBounds(470, 241, 70, 23);

        chkSdkiFrekuensi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiFrekuensi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiFrekuensi.setText("Frekuensi nadi meningkat");
        chkSdkiFrekuensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiFrekuensi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiFrekuensi.setName("chkSdkiFrekuensi"); // NOI18N
        chkSdkiFrekuensi.setOpaque(false);
        chkSdkiFrekuensi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiFrekuensi);
        chkSdkiFrekuensi.setBounds(470, 269, 160, 23);

        chkSdkiSulit.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiSulit.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiSulit.setText("Sulit tidur");
        chkSdkiSulit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiSulit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiSulit.setName("chkSdkiSulit"); // NOI18N
        chkSdkiSulit.setOpaque(false);
        chkSdkiSulit.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiSulit);
        chkSdkiSulit.setBounds(470, 297, 80, 23);

        chkSdkiKondisi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiKondisi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiKondisi.setText("Kondisi pembedahan");
        chkSdkiKondisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiKondisi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiKondisi.setName("chkSdkiKondisi"); // NOI18N
        chkSdkiKondisi.setOpaque(false);
        chkSdkiKondisi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiKondisi);
        chkSdkiKondisi.setBounds(145, 493, 140, 23);

        chkSdkiCedera.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiCedera.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiCedera.setText("Cedera traumatis");
        chkSdkiCedera.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiCedera.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiCedera.setName("chkSdkiCedera"); // NOI18N
        chkSdkiCedera.setOpaque(false);
        chkSdkiCedera.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiCedera);
        chkSdkiCedera.setBounds(145, 521, 120, 23);

        chkSlkiTingkat.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiTingkat.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiTingkat.setText("<html>Tingkat Nyeri (L.08066)<br>Setelah dilakukan tindakan keperawatan selama :</html>");
        chkSlkiTingkat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiTingkat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiTingkat.setName("chkSlkiTingkat"); // NOI18N
        chkSlkiTingkat.setOpaque(false);
        chkSlkiTingkat.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSlkiTingkat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSlkiTingkatActionPerformed(evt);
            }
        });
        FormInput.add(chkSlkiTingkat);
        chkSlkiTingkat.setBounds(145, 577, 260, 30);

        chkSdkiInfeksi.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiInfeksi.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiInfeksi.setText("Infeksi");
        chkSdkiInfeksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiInfeksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiInfeksi.setName("chkSdkiInfeksi"); // NOI18N
        chkSdkiInfeksi.setOpaque(false);
        chkSdkiInfeksi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiInfeksi);
        chkSdkiInfeksi.setBounds(300, 493, 70, 23);

        chkSdkiSindrom.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiSindrom.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiSindrom.setText("Sindrom koroner akut");
        chkSdkiSindrom.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiSindrom.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiSindrom.setName("chkSdkiSindrom"); // NOI18N
        chkSdkiSindrom.setOpaque(false);
        chkSdkiSindrom.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiSindrom);
        chkSdkiSindrom.setBounds(300, 521, 140, 23);

        chkSdkiGlaukoma.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiGlaukoma.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiGlaukoma.setText("Glaukoma");
        chkSdkiGlaukoma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiGlaukoma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiGlaukoma.setName("chkSdkiGlaukoma"); // NOI18N
        chkSdkiGlaukoma.setOpaque(false);
        chkSdkiGlaukoma.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiGlaukoma);
        chkSdkiGlaukoma.setBounds(445, 493, 80, 23);

        chkSlkiKeluhan.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKeluhan.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKeluhan.setText("Keluhan nyeri menurun");
        chkSlkiKeluhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKeluhan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKeluhan.setName("chkSlkiKeluhan"); // NOI18N
        chkSlkiKeluhan.setOpaque(false);
        chkSlkiKeluhan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKeluhan);
        chkSlkiKeluhan.setBounds(145, 641, 140, 23);

        chkSlkiMeringis.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiMeringis.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiMeringis.setText("Meringis menurun");
        chkSlkiMeringis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiMeringis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiMeringis.setName("chkSlkiMeringis"); // NOI18N
        chkSlkiMeringis.setOpaque(false);
        chkSlkiMeringis.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiMeringis);
        chkSlkiMeringis.setBounds(145, 669, 130, 23);

        chkSlkiGelisah.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiGelisah.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiGelisah.setText("Gelisah menurun");
        chkSlkiGelisah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiGelisah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiGelisah.setName("chkSlkiGelisah"); // NOI18N
        chkSlkiGelisah.setOpaque(false);
        chkSlkiGelisah.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiGelisah);
        chkSlkiGelisah.setBounds(300, 641, 110, 23);

        chkSlkiSikap.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiSikap.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiSikap.setText("Sikap protektif menurun");
        chkSlkiSikap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiSikap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiSikap.setName("chkSlkiSikap"); // NOI18N
        chkSlkiSikap.setOpaque(false);
        chkSlkiSikap.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiSikap);
        chkSlkiSikap.setBounds(145, 697, 150, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Diagnosis Keperawatan SDKI : Nyeri Akut (D.0077)");
        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 66, 330, 23);

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
        jLabel76.setBounds(145, 213, 210, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel77.setText("DO/DS (Gejala dan tanda Minor)");
        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(145, 325, 210, 23);

        chkSdkiTekanan.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiTekanan.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiTekanan.setText("Tekanan darah meningkat");
        chkSdkiTekanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiTekanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiTekanan.setName("chkSdkiTekanan"); // NOI18N
        chkSdkiTekanan.setOpaque(false);
        chkSdkiTekanan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiTekanan);
        chkSdkiTekanan.setBounds(145, 353, 160, 23);

        chkSdkiPola.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiPola.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiPola.setText("Pola napas berubah");
        chkSdkiPola.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiPola.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiPola.setName("chkSdkiPola"); // NOI18N
        chkSdkiPola.setOpaque(false);
        chkSdkiPola.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiPola);
        chkSdkiPola.setBounds(145, 381, 130, 23);

        chkSdkiNafsu.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiNafsu.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiNafsu.setText("Nafsu makan berubah");
        chkSdkiNafsu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiNafsu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiNafsu.setName("chkSdkiNafsu"); // NOI18N
        chkSdkiNafsu.setOpaque(false);
        chkSdkiNafsu.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiNafsu);
        chkSdkiNafsu.setBounds(145, 409, 140, 23);

        chkSdkiProses.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiProses.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiProses.setText("Proses berpikir terganggu");
        chkSdkiProses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiProses.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiProses.setName("chkSdkiProses"); // NOI18N
        chkSdkiProses.setOpaque(false);
        chkSdkiProses.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiProses);
        chkSdkiProses.setBounds(145, 437, 160, 23);

        chkSdkiMenarik.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiMenarik.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiMenarik.setText("Menarik diri");
        chkSdkiMenarik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiMenarik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiMenarik.setName("chkSdkiMenarik"); // NOI18N
        chkSdkiMenarik.setOpaque(false);
        chkSdkiMenarik.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiMenarik);
        chkSdkiMenarik.setBounds(330, 353, 100, 23);

        chkSdkiBerfokus.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiBerfokus.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiBerfokus.setText("Berfokus pada diri sendiri");
        chkSdkiBerfokus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiBerfokus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiBerfokus.setName("chkSdkiBerfokus"); // NOI18N
        chkSdkiBerfokus.setOpaque(false);
        chkSdkiBerfokus.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiBerfokus);
        chkSdkiBerfokus.setBounds(330, 381, 160, 23);

        chkSdkiDiaforesis.setBackground(new java.awt.Color(242, 242, 242));
        chkSdkiDiaforesis.setForeground(new java.awt.Color(0, 0, 0));
        chkSdkiDiaforesis.setText("Diaforesis");
        chkSdkiDiaforesis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSdkiDiaforesis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSdkiDiaforesis.setName("chkSdkiDiaforesis"); // NOI18N
        chkSdkiDiaforesis.setOpaque(false);
        chkSdkiDiaforesis.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSdkiDiaforesis);
        chkSdkiDiaforesis.setBounds(330, 409, 90, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("Kondisi Klinis Terkait :");
        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(145, 465, 140, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("(Rencana Keperawatan) Tujuan dan Kriteria Hasil SLKI :");
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 549, 340, 23);

        TketSlkiTingkat.setForeground(new java.awt.Color(0, 0, 0));
        TketSlkiTingkat.setName("TketSlkiTingkat"); // NOI18N
        FormInput.add(TketSlkiTingkat);
        TketSlkiTingkat.setBounds(407, 585, 200, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("Masalah nyeri pasien teratasi dengan kriteria hasil :");
        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(145, 613, 300, 23);

        chkSlkiKesulitan.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiKesulitan.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiKesulitan.setText("Kesulitan tidur menurun");
        chkSlkiKesulitan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiKesulitan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiKesulitan.setName("chkSlkiKesulitan"); // NOI18N
        chkSlkiKesulitan.setOpaque(false);
        chkSlkiKesulitan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiKesulitan);
        chkSlkiKesulitan.setBounds(300, 669, 150, 23);

        chkSlkiMenarik.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiMenarik.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiMenarik.setText("Menarik diri menurun");
        chkSlkiMenarik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiMenarik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiMenarik.setName("chkSlkiMenarik"); // NOI18N
        chkSlkiMenarik.setOpaque(false);
        chkSlkiMenarik.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiMenarik);
        chkSlkiMenarik.setBounds(300, 697, 130, 23);

        chkSlkiPerasaan.setBackground(new java.awt.Color(242, 242, 242));
        chkSlkiPerasaan.setForeground(new java.awt.Color(0, 0, 0));
        chkSlkiPerasaan.setText("Perasaan depresi menurun");
        chkSlkiPerasaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSlkiPerasaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSlkiPerasaan.setName("chkSlkiPerasaan"); // NOI18N
        chkSlkiPerasaan.setOpaque(false);
        chkSlkiPerasaan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSlkiPerasaan);
        chkSlkiPerasaan.setBounds(470, 641, 160, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("(Rencana Keperawatan) Intervensi Keperawatan SIKI : Manajemen Nyeri (I.08238)");
        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 725, 490, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("Tindakan Observasi :");
        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(145, 753, 140, 23);

        chkSikiIdenLokasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiIdenLokasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiIdenLokasi.setText("Identifikasi lokasi, karakteristik, durasi, frekuensi, kualitas,intensitas nyeri");
        chkSikiIdenLokasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiIdenLokasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiIdenLokasi.setName("chkSikiIdenLokasi"); // NOI18N
        chkSikiIdenLokasi.setOpaque(false);
        chkSikiIdenLokasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiIdenLokasi);
        chkSikiIdenLokasi.setBounds(145, 781, 390, 23);

        chkSikiIdenSkala.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiIdenSkala.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiIdenSkala.setText("Indentifikasi skala nyeri");
        chkSikiIdenSkala.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiIdenSkala.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiIdenSkala.setName("chkSikiIdenSkala"); // NOI18N
        chkSikiIdenSkala.setOpaque(false);
        chkSikiIdenSkala.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiIdenSkala);
        chkSikiIdenSkala.setBounds(145, 809, 150, 23);

        chkSikiIdenRespon.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiIdenRespon.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiIdenRespon.setText("Identifikasi respons nyeri non verbal");
        chkSikiIdenRespon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiIdenRespon.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiIdenRespon.setName("chkSikiIdenRespon"); // NOI18N
        chkSikiIdenRespon.setOpaque(false);
        chkSikiIdenRespon.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiIdenRespon);
        chkSikiIdenRespon.setBounds(145, 837, 210, 23);

        chkSikiIdenFaktor.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiIdenFaktor.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiIdenFaktor.setText("Identifikasi faktor yang memperberat dan memperingan nyeri");
        chkSikiIdenFaktor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiIdenFaktor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiIdenFaktor.setName("chkSikiIdenFaktor"); // NOI18N
        chkSikiIdenFaktor.setOpaque(false);
        chkSikiIdenFaktor.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiIdenFaktor);
        chkSikiIdenFaktor.setBounds(145, 865, 330, 23);

        chkSikiIdenPengetahuan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiIdenPengetahuan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiIdenPengetahuan.setText("Identifikasi pengetahuan bidaya terhadap respon nyeri");
        chkSikiIdenPengetahuan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiIdenPengetahuan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiIdenPengetahuan.setName("chkSikiIdenPengetahuan"); // NOI18N
        chkSikiIdenPengetahuan.setOpaque(false);
        chkSikiIdenPengetahuan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiIdenPengetahuan);
        chkSikiIdenPengetahuan.setBounds(145, 893, 300, 23);

        chkSikiIdenPengaruh.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiIdenPengaruh.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiIdenPengaruh.setText("Identifikasi pengaruh nyeri pada kualitas hidup");
        chkSikiIdenPengaruh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiIdenPengaruh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiIdenPengaruh.setName("chkSikiIdenPengaruh"); // NOI18N
        chkSikiIdenPengaruh.setOpaque(false);
        chkSikiIdenPengaruh.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiIdenPengaruh);
        chkSikiIdenPengaruh.setBounds(145, 921, 260, 23);

        chkSikiMonKeberhasilan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiMonKeberhasilan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiMonKeberhasilan.setText("Monitor keberhasilan terapi komplementer yang sudah diberikan");
        chkSikiMonKeberhasilan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiMonKeberhasilan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiMonKeberhasilan.setName("chkSikiMonKeberhasilan"); // NOI18N
        chkSikiMonKeberhasilan.setOpaque(false);
        chkSikiMonKeberhasilan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiMonKeberhasilan);
        chkSikiMonKeberhasilan.setBounds(145, 949, 350, 23);

        chkSikiMonEfek.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiMonEfek.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiMonEfek.setText("Monitor efek samping pengunaan analgetik");
        chkSikiMonEfek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiMonEfek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiMonEfek.setName("chkSikiMonEfek"); // NOI18N
        chkSikiMonEfek.setOpaque(false);
        chkSikiMonEfek.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiMonEfek);
        chkSikiMonEfek.setBounds(145, 977, 250, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("Terapeutik :");
        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(145, 1005, 90, 23);

        chkSikiBerikan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiBerikan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiBerikan.setText("<html>Berikan teknik non farmakologis untuk mengurangi rasa nyeri (mis.TENS, hipnosis, akupresur, terapi musik, biofeedback, terapi pijat, aromaterapi, teknik imajinasi terbimbing, kompres hangat/dingin, terapi bermain)</html>");
        chkSikiBerikan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiBerikan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiBerikan.setName("chkSikiBerikan"); // NOI18N
        chkSikiBerikan.setOpaque(false);
        chkSikiBerikan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiBerikan);
        chkSikiBerikan.setBounds(145, 1033, 550, 30);

        chkSikiKontrol.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiKontrol.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiKontrol.setText("Kontrol lingkungan yang memperberat rasa nyeri (mis.suhu ruangan, pencahayaan, kebisingan)");
        chkSikiKontrol.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiKontrol.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiKontrol.setName("chkSikiKontrol"); // NOI18N
        chkSikiKontrol.setOpaque(false);
        chkSikiKontrol.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiKontrol);
        chkSikiKontrol.setBounds(145, 1068, 500, 23);

        chkSikiFasilitas.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiFasilitas.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiFasilitas.setText("Fasilitasi istirahat dan tidur");
        chkSikiFasilitas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiFasilitas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiFasilitas.setName("chkSikiFasilitas"); // NOI18N
        chkSikiFasilitas.setOpaque(false);
        chkSikiFasilitas.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiFasilitas);
        chkSikiFasilitas.setBounds(145, 1096, 170, 23);

        chkSikiPertimbangkanJenis.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiPertimbangkanJenis.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiPertimbangkanJenis.setText("Pertimbangkan jenis-jenis dan sumber nyeri dalam pemilihan strategi meredakan nyeri");
        chkSikiPertimbangkanJenis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiPertimbangkanJenis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiPertimbangkanJenis.setName("chkSikiPertimbangkanJenis"); // NOI18N
        chkSikiPertimbangkanJenis.setOpaque(false);
        chkSikiPertimbangkanJenis.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiPertimbangkanJenis);
        chkSikiPertimbangkanJenis.setBounds(145, 1124, 450, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("Edukasi :");
        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(145, 1152, 90, 23);

        chkSikiJelasPenyebab.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiJelasPenyebab.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiJelasPenyebab.setText("Jelaskan penyebab, periode dan pemicu nyeri");
        chkSikiJelasPenyebab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiJelasPenyebab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiJelasPenyebab.setName("chkSikiJelasPenyebab"); // NOI18N
        chkSikiJelasPenyebab.setOpaque(false);
        chkSikiJelasPenyebab.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiJelasPenyebab);
        chkSikiJelasPenyebab.setBounds(145, 1180, 250, 23);

        chkSikiJelasStrategi.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiJelasStrategi.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiJelasStrategi.setText("Jelaskan strategi meredakan nyeri");
        chkSikiJelasStrategi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiJelasStrategi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiJelasStrategi.setName("chkSikiJelasStrategi"); // NOI18N
        chkSikiJelasStrategi.setOpaque(false);
        chkSikiJelasStrategi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiJelasStrategi);
        chkSikiJelasStrategi.setBounds(145, 1208, 200, 23);

        chkSikiAnjurMemonitor.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAnjurMemonitor.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAnjurMemonitor.setText("Anjurkan memonitor nyeri secara mandiri");
        chkSikiAnjurMemonitor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAnjurMemonitor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAnjurMemonitor.setName("chkSikiAnjurMemonitor"); // NOI18N
        chkSikiAnjurMemonitor.setOpaque(false);
        chkSikiAnjurMemonitor.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAnjurMemonitor);
        chkSikiAnjurMemonitor.setBounds(145, 1236, 240, 23);

        chkSikiAnjurMenggunakan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAnjurMenggunakan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAnjurMenggunakan.setText("Anjurkan menggunakan analgetik secara tepat");
        chkSikiAnjurMenggunakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAnjurMenggunakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAnjurMenggunakan.setName("chkSikiAnjurMenggunakan"); // NOI18N
        chkSikiAnjurMenggunakan.setOpaque(false);
        chkSikiAnjurMenggunakan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAnjurMenggunakan);
        chkSikiAnjurMenggunakan.setBounds(145, 1264, 260, 23);

        chkSikiAnjurTeknik.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiAnjurTeknik.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiAnjurTeknik.setText("Anjurkan teknik nonfarmakologis untuk mengurangi rasa nyeri Kolaborasi");
        chkSikiAnjurTeknik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiAnjurTeknik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiAnjurTeknik.setName("chkSikiAnjurTeknik"); // NOI18N
        chkSikiAnjurTeknik.setOpaque(false);
        chkSikiAnjurTeknik.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiAnjurTeknik);
        chkSikiAnjurTeknik.setBounds(145, 1292, 380, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Kolaborasi pemberian analgesik, jika perlu :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(145, 1320, 230, 23);

        TketKolaborasiManajemen.setForeground(new java.awt.Color(0, 0, 0));
        TketKolaborasiManajemen.setName("TketKolaborasiManajemen"); // NOI18N
        FormInput.add(TketKolaborasiManajemen);
        TketKolaborasiManajemen.setBounds(380, 1320, 200, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("(Rencana Keperawatan) Intervensi Keperawatan SIKI : Pemberian Analgetik (I.08243)");
        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 1348, 520, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel87.setText("Tindakan Observasi :");
        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(145, 1376, 140, 23);

        chkSikiIdenKarakteristik.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiIdenKarakteristik.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiIdenKarakteristik.setText("Identifikasi karakteristik nyeri (mis.pencetus, pereda, kualitas, lokasi, intensitas, frekuesni, durasi)");
        chkSikiIdenKarakteristik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiIdenKarakteristik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiIdenKarakteristik.setName("chkSikiIdenKarakteristik"); // NOI18N
        chkSikiIdenKarakteristik.setOpaque(false);
        chkSikiIdenKarakteristik.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiIdenKarakteristik);
        chkSikiIdenKarakteristik.setBounds(145, 1404, 510, 23);

        chkSikiIdenRiwayat.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiIdenRiwayat.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiIdenRiwayat.setText("Identifikasi riwayat alergi total");
        chkSikiIdenRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiIdenRiwayat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiIdenRiwayat.setName("chkSikiIdenRiwayat"); // NOI18N
        chkSikiIdenRiwayat.setOpaque(false);
        chkSikiIdenRiwayat.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiIdenRiwayat);
        chkSikiIdenRiwayat.setBounds(145, 1432, 190, 23);

        chkSikiIdenKesesusian.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiIdenKesesusian.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiIdenKesesusian.setText("Identifikasi kesesuaian jenis analgesik (mis.narkotika, non narkotik, atau NSAIO) dengan tingkat keparahan nyeri");
        chkSikiIdenKesesusian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiIdenKesesusian.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiIdenKesesusian.setName("chkSikiIdenKesesusian"); // NOI18N
        chkSikiIdenKesesusian.setOpaque(false);
        chkSikiIdenKesesusian.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiIdenKesesusian);
        chkSikiIdenKesesusian.setBounds(145, 1460, 570, 23);

        chkSikiMonitorTanda.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiMonitorTanda.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiMonitorTanda.setText("Monitor tanda-tanda vital sebelum dan sesudah pemberian analgesik");
        chkSikiMonitorTanda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiMonitorTanda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiMonitorTanda.setName("chkSikiMonitorTanda"); // NOI18N
        chkSikiMonitorTanda.setOpaque(false);
        chkSikiMonitorTanda.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiMonitorTanda);
        chkSikiMonitorTanda.setBounds(145, 1488, 360, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel88.setText("Terapeutik :");
        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(145, 1516, 90, 23);

        chkSikiDiskusikan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiDiskusikan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiDiskusikan.setText("Diskusikan jenis analgesik yang disukai untuk mencapai analgesik optimal, jika perlu");
        chkSikiDiskusikan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiDiskusikan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiDiskusikan.setName("chkSikiDiskusikan"); // NOI18N
        chkSikiDiskusikan.setOpaque(false);
        chkSikiDiskusikan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiDiskusikan);
        chkSikiDiskusikan.setBounds(145, 1544, 430, 23);

        chkSikiPertimbangkanGuna.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiPertimbangkanGuna.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiPertimbangkanGuna.setText("Pertimbangkan penggunaan infus kontinu, atau bolus opioid untuk mempertahankan kadar atau serum");
        chkSikiPertimbangkanGuna.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiPertimbangkanGuna.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiPertimbangkanGuna.setName("chkSikiPertimbangkanGuna"); // NOI18N
        chkSikiPertimbangkanGuna.setOpaque(false);
        chkSikiPertimbangkanGuna.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiPertimbangkanGuna);
        chkSikiPertimbangkanGuna.setBounds(145, 1572, 530, 23);

        chkSikiTetapkan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiTetapkan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiTetapkan.setText("Tetapkan target efektifitas analgesik untuk mengoptimalkan respon pasien");
        chkSikiTetapkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiTetapkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiTetapkan.setName("chkSikiTetapkan"); // NOI18N
        chkSikiTetapkan.setOpaque(false);
        chkSikiTetapkan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiTetapkan);
        chkSikiTetapkan.setBounds(145, 1600, 390, 23);

        chkSikiDokumentasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiDokumentasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiDokumentasi.setText("Dokumentasikan respons terhadap efek analgesik dan efek yang tidak diinginkan");
        chkSikiDokumentasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiDokumentasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiDokumentasi.setName("chkSikiDokumentasi"); // NOI18N
        chkSikiDokumentasi.setOpaque(false);
        chkSikiDokumentasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiDokumentasi);
        chkSikiDokumentasi.setBounds(145, 1628, 430, 23);

        chkSikiJelaskan.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiJelaskan.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiJelaskan.setText("Jelaskan efek terapi dan efek samping obat");
        chkSikiJelaskan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSikiJelaskan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSikiJelaskan.setName("chkSikiJelaskan"); // NOI18N
        chkSikiJelaskan.setOpaque(false);
        chkSikiJelaskan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSikiJelaskan);
        chkSikiJelaskan.setBounds(145, 1684, 430, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel89.setText("Edukasi :");
        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(145, 1656, 90, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel90.setText("Kolaborasi :");
        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(145, 1712, 90, 23);

        chkSikiKolaborasi.setBackground(new java.awt.Color(242, 242, 242));
        chkSikiKolaborasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSikiKolaborasi.setText("Kolaborasi pemberian dosis dan jenis analgetik, jika perlu :");
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
        chkSikiKolaborasi.setBounds(145, 1740, 305, 23);

        TketKolaborasiPemberian.setForeground(new java.awt.Color(0, 0, 0));
        TketKolaborasiPemberian.setName("TketKolaborasiPemberian"); // NOI18N
        FormInput.add(TketKolaborasiPemberian);
        TketKolaborasiPemberian.setBounds(450, 1740, 200, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass13.add(Scroll1);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data MasKep Nyeri Akut ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
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

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-07-2026" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-07-2026" }));
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
            if (Sequel.menyimpantf("masalah_keperawatan_nyeri_akut", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 64, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), sdkiAgenFisiologis, sdkiAgenKimiawi, sdkiAgenFisik, sdkiMengeluh, sdkiTampak, sdkiBersikap, sdkiGelisah,
                        sdkiFrekuensi, sdkiSulit, sdkiTekanan, sdkiPola, sdkiNafsu, sdkiProses, sdkiMenarik, sdkiBerfokus,
                        sdkiDiaforesis, sdkiKondisi, sdkiCedera, sdkiInfeksi, sdkiSindrom, sdkiGlaukoma, slkiTingkat, TketSlkiTingkat.getText(), slkiKeluhan,
                        slkiMeringis, slkiSikap, slkiGelisah, slkiKesulitan, slkiMenarik, slkiPerasaan, sikiIdenLokasi, sikiIdenSkala,
                        sikiIdenRespon, sikiIdenFaktor, sikiIdenPengetahuan, sikiIdenPengaruh, sikiMonKeberhasilan, sikiMonEfek,
                        sikiBerikan, sikiKontrol, sikiFasilitas, sikiPertimbangkanJenis, sikiJelasPenyebab, sikiJelasStrategi,
                        sikiAnjurMemonitor, sikiAnjurMenggunakan, sikiAnjurTeknik, TketKolaborasiManajemen.getText(), sikiIdenKarakteristik, sikiIdenRiwayat, sikiIdenKesesusian,
                        sikiMonitorTanda, sikiDiskusikan, sikiPertimbangkanGuna, sikiTetapkan, sikiDokumentasi, sikiJelaskan, sikiKolaborasi, TketKolaborasiPemberian.getText(),
                        sttsRawat, TnipPetugas.getText(), Sequel.cariIsi("select now()")
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Masalah Keperawatan Nyeri Akut", "Simpan");
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
                if (Sequel.mengedittf("masalah_keperawatan_nyeri_akut", "waktu_simpan=?", "sdki_agen_fisiologis=?, sdki_agen_kimiawi=?, sdki_agen_fisik=?, sdki_mengeluh=?, "
                        + "sdki_tampak=?, sdki_bersikap=?, sdki_gelisah=?, sdki_frekuensi=?, sdki_sulit=?, sdki_tekanan=?, sdki_pola=?, sdki_nafsu=?, sdki_proses=?, sdki_menarik=?, "
                        + "sdki_berfokus=?, sdki_diaforesis=?, sdki_kondisi=?, sdki_cedera=?, sdki_infeksi=?, sdki_sindrom=?, sdki_glaukoma=?, slki_tingkat=?, ket_slki_tingkat=?, "
                        + "slki_keluhan=?, slki_meringis=?, slki_sikap=?, slki_gelisah=?, slki_kesulitan=?, slki_menarik=?, slki_perasaan=?, siki_iden_lokasi=?, siki_iden_skala=?, "
                        + "siki_iden_respon=?, siki_iden_faktor=?, siki_iden_pengetahuan=?, siki_iden_pengaruh=?, siki_mon_keberhasilan=?, siki_mon_efek=?, siki_berikan=?, "
                        + "siki_kontrol=?, siki_fasilitas=?, siki_pertimbangkan_jenis=?, siki_jelas_penyebab=?, siki_jelas_strategi=?, siki_anjur_memonitor=?, siki_anjur_menggunakan=?, "
                        + "siki_anjur_teknik=?, ket_kolaborasi_manajemen=?, siki_iden_karakteristik=?, siki_iden_riwayat=?, siki_iden_kesesusian=?, siki_monitor_tanda=?, "
                        + "siki_diskusikan=?, siki_pertimbangkan_guna=?, siki_tetapkan=?, siki_dokumentasi=?, siki_jelaskan=?, siki_kolaborasi=?, ket_kolaborasi_pemberian=?, "
                        + "nip_petugas=?", 61, new String[]{
                            sdkiAgenFisiologis, sdkiAgenKimiawi, sdkiAgenFisik, sdkiMengeluh, sdkiTampak, sdkiBersikap, sdkiGelisah,
                            sdkiFrekuensi, sdkiSulit, sdkiTekanan, sdkiPola, sdkiNafsu, sdkiProses, sdkiMenarik, sdkiBerfokus,
                            sdkiDiaforesis, sdkiKondisi, sdkiCedera, sdkiInfeksi, sdkiSindrom, sdkiGlaukoma, slkiTingkat, TketSlkiTingkat.getText(), slkiKeluhan,
                            slkiMeringis, slkiSikap, slkiGelisah, slkiKesulitan, slkiMenarik, slkiPerasaan, sikiIdenLokasi, sikiIdenSkala,
                            sikiIdenRespon, sikiIdenFaktor, sikiIdenPengetahuan, sikiIdenPengaruh, sikiMonKeberhasilan, sikiMonEfek,
                            sikiBerikan, sikiKontrol, sikiFasilitas, sikiPertimbangkanJenis, sikiJelasPenyebab, sikiJelasStrategi,
                            sikiAnjurMemonitor, sikiAnjurMenggunakan, sikiAnjurTeknik, TketKolaborasiManajemen.getText(), sikiIdenKarakteristik, sikiIdenRiwayat, sikiIdenKesesusian,
                            sikiMonitorTanda, sikiDiskusikan, sikiPertimbangkanGuna, sikiTetapkan, sikiDokumentasi, sikiJelaskan, sikiKolaborasi, TketKolaborasiPemberian.getText(),
                            TnipPetugas.getText(),
                            tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 0).toString()
                        }) == true) {

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Masalah Keperawatan Nyeri Akut", "Ganti");
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
        akses.setform("RMMasalahKeperawatanNyeriAkut");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbMasalah.getSelectedRow() > -1) {
            if (akses.getadmin() == true || tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 68).toString().equals(akses.getkode())) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from masalah_keperawatan_nyeri_akut where waktu_simpan=?", 1, new String[]{
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

            if (chkSdkiAgenFisiologis.isSelected() == true) {
                param.put("sdkiAgenFisiologis", "V");
            } else {
                param.put("sdkiAgenFisiologis", "");
            }

            if (chkSdkiAgenKimiawi.isSelected() == true) {
                param.put("sdkiAgenKimiawi", "V");
            } else {
                param.put("sdkiAgenKimiawi", "");
            }

            if (chkSdkiAgenFisik.isSelected() == true) {
                param.put("sdkiAgenFisik", "V");
            } else {
                param.put("sdkiAgenFisik", "");
            }

            if (chkSdkiMengeluh.isSelected() == true) {
                param.put("sdkiMengeluh", "V");
            } else {
                param.put("sdkiMengeluh", "");
            }

            if (chkSdkiTampak.isSelected() == true) {
                param.put("sdkiTampak", "V");
            } else {
                param.put("sdkiTampak", "");
            }

            if (chkSdkiBersikap.isSelected() == true) {
                param.put("sdkiBersikap", "V");
            } else {
                param.put("sdkiBersikap", "");
            }

            if (chkSdkiGelisah.isSelected() == true) {
                param.put("sdkiGelisah", "V");
            } else {
                param.put("sdkiGelisah", "");
            }

            if (chkSdkiFrekuensi.isSelected() == true) {
                param.put("sdkiFrekuensi", "V");
            } else {
                param.put("sdkiFrekuensi", "");
            }

            if (chkSdkiSulit.isSelected() == true) {
                param.put("sdkiSulit", "V");
            } else {
                param.put("sdkiSulit", "");
            }

            if (chkSdkiTekanan.isSelected() == true) {
                param.put("sdkiTekanan", "V");
            } else {
                param.put("sdkiTekanan", "");
            }

            if (chkSdkiPola.isSelected() == true) {
                param.put("sdkiPola", "V");
            } else {
                param.put("sdkiPola", "");
            }

            if (chkSdkiNafsu.isSelected() == true) {
                param.put("sdkiNafsu", "V");
            } else {
                param.put("sdkiNafsu", "");
            }

            if (chkSdkiProses.isSelected() == true) {
                param.put("sdkiProses", "V");
            } else {
                param.put("sdkiProses", "");
            }

            if (chkSdkiMenarik.isSelected() == true) {
                param.put("sdkiMenarik", "V");
            } else {
                param.put("sdkiMenarik", "");
            }

            if (chkSdkiBerfokus.isSelected() == true) {
                param.put("sdkiBerfokus", "V");
            } else {
                param.put("sdkiBerfokus", "");
            }

            if (chkSdkiDiaforesis.isSelected() == true) {
                param.put("sdkiDiaforesis", "V");
            } else {
                param.put("sdkiDiaforesis", "");
            }

            if (chkSdkiKondisi.isSelected() == true) {
                param.put("sdkiKondisi", "V");
            } else {
                param.put("sdkiKondisi", "");
            }

            if (chkSdkiCedera.isSelected() == true) {
                param.put("sdkiCedera", "V");
            } else {
                param.put("sdkiCedera", "");
            }

            if (chkSdkiInfeksi.isSelected() == true) {
                param.put("sdkiInfeksi", "V");
            } else {
                param.put("sdkiInfeksi", "");
            }

            if (chkSdkiSindrom.isSelected() == true) {
                param.put("sdkiSindrom", "V");
            } else {
                param.put("sdkiSindrom", "");
            }

            if (chkSdkiGlaukoma.isSelected() == true) {
                param.put("sdkiGlaukoma", "V");
            } else {
                param.put("sdkiGlaukoma", "");
            }

            if (chkSlkiTingkat.isSelected() == true) {
                param.put("slkiTingkat", "V");
                if (TketSlkiTingkat.getText().equals("")) {
                    param.put("ketslkiTingkat", "..........");
                } else {
                    param.put("ketslkiTingkat", TketSlkiTingkat.getText());
                }
            } else {
                param.put("slkiTingkat", "");
                param.put("ketslkiTingkat", "..........");
            }

            if (chkSlkiKeluhan.isSelected() == true) {
                param.put("slkiKeluhan", "V");
            } else {
                param.put("slkiKeluhan", "");
            }

            if (chkSlkiMeringis.isSelected() == true) {
                param.put("slkiMeringis", "V");
            } else {
                param.put("slkiMeringis", "");
            }

            if (chkSlkiSikap.isSelected() == true) {
                param.put("slkiSikap", "V");
            } else {
                param.put("slkiSikap", "");
            }

            if (chkSlkiGelisah.isSelected() == true) {
                param.put("slkiGelisah", "V");
            } else {
                param.put("slkiGelisah", "");
            }

            if (chkSlkiKesulitan.isSelected() == true) {
                param.put("slkiKesulitan", "V");
            } else {
                param.put("slkiKesulitan", "");
            }

            if (chkSlkiMenarik.isSelected() == true) {
                param.put("slkiMenarik", "V");
            } else {
                param.put("slkiMenarik", "");
            }

            if (chkSlkiPerasaan.isSelected() == true) {
                param.put("slkiPerasaan", "V");
            } else {
                param.put("slkiPerasaan", "");
            }

            if (chkSikiIdenLokasi.isSelected() == true) {
                param.put("sikiIdenLokasi", "V");
            } else {
                param.put("sikiIdenLokasi", "");
            }

            if (chkSikiIdenSkala.isSelected() == true) {
                param.put("sikiIdenSkala", "V");
            } else {
                param.put("sikiIdenSkala", "");
            }

            if (chkSikiIdenRespon.isSelected() == true) {
                param.put("sikiIdenRespon", "V");
            } else {
                param.put("sikiIdenRespon", "");
            }

            if (chkSikiIdenFaktor.isSelected() == true) {
                param.put("sikiIdenFaktor", "V");
            } else {
                param.put("sikiIdenFaktor", "");
            }

            if (chkSikiIdenPengetahuan.isSelected() == true) {
                param.put("sikiIdenPengetahuan", "V");
            } else {
                param.put("sikiIdenPengetahuan", "");
            }

            if (chkSikiIdenPengaruh.isSelected() == true) {
                param.put("sikiIdenPengaruh", "V");
            } else {
                param.put("sikiIdenPengaruh", "");
            }

            if (chkSikiMonKeberhasilan.isSelected() == true) {
                param.put("sikiMonKeberhasilan", "V");
            } else {
                param.put("sikiMonKeberhasilan", "");
            }

            if (chkSikiMonEfek.isSelected() == true) {
                param.put("sikiMonEfek", "V");
            } else {
                param.put("sikiMonEfek", "");
            }

            if (chkSikiBerikan.isSelected() == true) {
                param.put("sikiBerikan", "V");
            } else {
                param.put("sikiBerikan", "");
            }

            if (chkSikiKontrol.isSelected() == true) {
                param.put("sikiKontrol", "V");
            } else {
                param.put("sikiKontrol", "");
            }

            if (chkSikiFasilitas.isSelected() == true) {
                param.put("sikiFasilitas", "V");
            } else {
                param.put("sikiFasilitas", "");
            }

            if (chkSikiPertimbangkanJenis.isSelected() == true) {
                param.put("sikiPertimbangkanJenis", "V");
            } else {
                param.put("sikiPertimbangkanJenis", "");
            }

            if (chkSikiJelasPenyebab.isSelected() == true) {
                param.put("sikiJelasPenyebab", "V");
            } else {
                param.put("sikiJelasPenyebab", "");
            }

            if (chkSikiJelasStrategi.isSelected() == true) {
                param.put("sikiJelasStrategi", "V");
            } else {
                param.put("sikiJelasStrategi", "");
            }

            if (chkSikiAnjurMemonitor.isSelected() == true) {
                param.put("sikiAnjurMemonitor", "V");
            } else {
                param.put("sikiAnjurMemonitor", "");
            }

            if (chkSikiAnjurMenggunakan.isSelected() == true) {
                param.put("sikiAnjurMenggunakan", "V");
            } else {
                param.put("sikiAnjurMenggunakan", "");
            }

            if (chkSikiAnjurTeknik.isSelected() == true) {
                param.put("sikiAnjurTeknik", "V");
            } else {
                param.put("sikiAnjurTeknik", "");
            }
            
            if (TketKolaborasiManajemen.getText().equals("")) {
                param.put("ketKolaborasiManajemen", "...........");
            } else {
                param.put("ketKolaborasiManajemen", TketKolaborasiManajemen.getText());
            }

            if (chkSikiIdenKarakteristik.isSelected() == true) {
                param.put("sikiIdenKarakteristik", "V");
            } else {
                param.put("sikiIdenKarakteristik", "");
            }

            if (chkSikiIdenRiwayat.isSelected() == true) {
                param.put("sikiIdenRiwayat", "V");
            } else {
                param.put("sikiIdenRiwayat", "");
            }

            if (chkSikiIdenKesesusian.isSelected() == true) {
                param.put("sikiIdenKesesusian", "V");
            } else {
                param.put("sikiIdenKesesusian", "");
            }

            if (chkSikiMonitorTanda.isSelected() == true) {
                param.put("sikiMonitorTanda", "V");
            } else {
                param.put("sikiMonitorTanda", "");
            }

            if (chkSikiDiskusikan.isSelected() == true) {
                param.put("sikiDiskusikan", "V");
            } else {
                param.put("sikiDiskusikan", "");
            }

            if (chkSikiPertimbangkanGuna.isSelected() == true) {
                param.put("sikiPertimbangkanGuna", "V");
            } else {
                param.put("sikiPertimbangkanGuna", "");
            }

            if (chkSikiTetapkan.isSelected() == true) {
                param.put("sikiTetapkan", "V");
            } else {
                param.put("sikiTetapkan", "");
            }

            if (chkSikiDokumentasi.isSelected() == true) {
                param.put("sikiDokumentasi", "V");
            } else {
                param.put("sikiDokumentasi", "");
            }

            if (chkSikiJelaskan.isSelected() == true) {
                param.put("sikiJelaskan", "V");
            } else {
                param.put("sikiJelaskan", "");
            }

            if (chkSikiKolaborasi.isSelected() == true) {
                param.put("sikiKolaborasi", "V");
                if (TketKolaborasiPemberian.getText().equals("")) {
                    param.put("ketKolaborasiPemberian", "............");
                } else {
                    param.put("ketKolaborasiPemberian", TketKolaborasiPemberian.getText());
                }
            } else {
                param.put("sikiKolaborasi", "");
                param.put("ketKolaborasiPemberian", "............");
            }

            Valid.MyReport("rptMasKepNyeriAkut.jasper", "report", "::[ RM Masalah Keperawatan Nyeri Akut ]::",
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

    private void chkSlkiTingkatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSlkiTingkatActionPerformed
        TketSlkiTingkat.setText("");
        if (chkSlkiTingkat.isSelected() == true) {
            TketSlkiTingkat.setEnabled(true);
            TketSlkiTingkat.requestFocus();
        } else {
            TketSlkiTingkat.setEnabled(false);
        }
    }//GEN-LAST:event_chkSlkiTingkatActionPerformed

    private void chkSikiKolaborasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSikiKolaborasiActionPerformed
        TketKolaborasiPemberian.setText("");
        if (chkSikiKolaborasi.isSelected() == true) {
            TketKolaborasiPemberian.setEnabled(true);
            TketKolaborasiPemberian.requestFocus();
        } else {
            TketKolaborasiPemberian.setEnabled(false);
        }
    }//GEN-LAST:event_chkSikiKolaborasiActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMMasalahKeperawatanNyeriAkut dialog = new RMMasalahKeperawatanNyeriAkut(new javax.swing.JFrame(), true);
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
    private widget.TextBox TketKolaborasiManajemen;
    private widget.TextBox TketKolaborasiPemberian;
    private widget.TextBox TketSlkiTingkat;
    private widget.TextBox TnipPetugas;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TrgRawat;
    private widget.CekBox chkSdkiAgenFisik;
    private widget.CekBox chkSdkiAgenFisiologis;
    private widget.CekBox chkSdkiAgenKimiawi;
    private widget.CekBox chkSdkiBerfokus;
    private widget.CekBox chkSdkiBersikap;
    private widget.CekBox chkSdkiCedera;
    private widget.CekBox chkSdkiDiaforesis;
    private widget.CekBox chkSdkiFrekuensi;
    private widget.CekBox chkSdkiGelisah;
    private widget.CekBox chkSdkiGlaukoma;
    private widget.CekBox chkSdkiInfeksi;
    private widget.CekBox chkSdkiKondisi;
    private widget.CekBox chkSdkiMenarik;
    private widget.CekBox chkSdkiMengeluh;
    private widget.CekBox chkSdkiNafsu;
    private widget.CekBox chkSdkiPola;
    private widget.CekBox chkSdkiProses;
    private widget.CekBox chkSdkiSindrom;
    private widget.CekBox chkSdkiSulit;
    private widget.CekBox chkSdkiTampak;
    private widget.CekBox chkSdkiTekanan;
    private widget.CekBox chkSikiAnjurMemonitor;
    private widget.CekBox chkSikiAnjurMenggunakan;
    private widget.CekBox chkSikiAnjurTeknik;
    private widget.CekBox chkSikiBerikan;
    private widget.CekBox chkSikiDiskusikan;
    private widget.CekBox chkSikiDokumentasi;
    private widget.CekBox chkSikiFasilitas;
    private widget.CekBox chkSikiIdenFaktor;
    private widget.CekBox chkSikiIdenKarakteristik;
    private widget.CekBox chkSikiIdenKesesusian;
    private widget.CekBox chkSikiIdenLokasi;
    private widget.CekBox chkSikiIdenPengaruh;
    private widget.CekBox chkSikiIdenPengetahuan;
    private widget.CekBox chkSikiIdenRespon;
    private widget.CekBox chkSikiIdenRiwayat;
    private widget.CekBox chkSikiIdenSkala;
    private widget.CekBox chkSikiJelasPenyebab;
    private widget.CekBox chkSikiJelasStrategi;
    private widget.CekBox chkSikiJelaskan;
    private widget.CekBox chkSikiKolaborasi;
    private widget.CekBox chkSikiKontrol;
    private widget.CekBox chkSikiMonEfek;
    private widget.CekBox chkSikiMonKeberhasilan;
    private widget.CekBox chkSikiMonitorTanda;
    private widget.CekBox chkSikiPertimbangkanGuna;
    private widget.CekBox chkSikiPertimbangkanJenis;
    private widget.CekBox chkSikiTetapkan;
    private widget.CekBox chkSlkiGelisah;
    private widget.CekBox chkSlkiKeluhan;
    private widget.CekBox chkSlkiKesulitan;
    private widget.CekBox chkSlkiMenarik;
    private widget.CekBox chkSlkiMeringis;
    private widget.CekBox chkSlkiPerasaan;
    private widget.CekBox chkSlkiSikap;
    private widget.CekBox chkSlkiTingkat;
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
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel90;
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
                    + "pg.nama nmPetugas from masalah_keperawatan_nyeri_akut m inner join reg_periksa rp on rp.no_rawat=m.no_rawat "
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
                        rs.getString("sdki_agen_fisiologis"),
                        rs.getString("sdki_agen_kimiawi"),
                        rs.getString("sdki_agen_fisik"),
                        rs.getString("sdki_mengeluh"),
                        rs.getString("sdki_tampak"),
                        rs.getString("sdki_bersikap"),
                        rs.getString("sdki_gelisah"),
                        rs.getString("sdki_frekuensi"),
                        rs.getString("sdki_sulit"),
                        rs.getString("sdki_tekanan"),
                        rs.getString("sdki_pola"),
                        rs.getString("sdki_nafsu"),
                        rs.getString("sdki_proses"),
                        rs.getString("sdki_menarik"),
                        rs.getString("sdki_berfokus"),
                        rs.getString("sdki_diaforesis"),
                        rs.getString("sdki_kondisi"),
                        rs.getString("sdki_cedera"),
                        rs.getString("sdki_infeksi"),
                        rs.getString("sdki_sindrom"),
                        rs.getString("sdki_glaukoma"),
                        rs.getString("slki_tingkat"),
                        rs.getString("ket_slki_tingkat"),
                        rs.getString("slki_keluhan"),
                        rs.getString("slki_meringis"),
                        rs.getString("slki_sikap"),
                        rs.getString("slki_gelisah"),
                        rs.getString("slki_kesulitan"),
                        rs.getString("slki_menarik"),
                        rs.getString("slki_perasaan"),
                        rs.getString("siki_iden_lokasi"),
                        rs.getString("siki_iden_skala"),
                        rs.getString("siki_iden_respon"),
                        rs.getString("siki_iden_faktor"),
                        rs.getString("siki_iden_pengetahuan"),
                        rs.getString("siki_iden_pengaruh"),
                        rs.getString("siki_mon_keberhasilan"),
                        rs.getString("siki_mon_efek"),
                        rs.getString("siki_berikan"),
                        rs.getString("siki_kontrol"),
                        rs.getString("siki_fasilitas"),
                        rs.getString("siki_pertimbangkan_jenis"),
                        rs.getString("siki_jelas_penyebab"),
                        rs.getString("siki_jelas_strategi"),
                        rs.getString("siki_anjur_memonitor"),
                        rs.getString("siki_anjur_menggunakan"),
                        rs.getString("siki_anjur_teknik"),
                        rs.getString("ket_kolaborasi_manajemen"),
                        rs.getString("siki_iden_karakteristik"),
                        rs.getString("siki_iden_riwayat"),
                        rs.getString("siki_iden_kesesusian"),
                        rs.getString("siki_monitor_tanda"),
                        rs.getString("siki_diskusikan"),
                        rs.getString("siki_pertimbangkan_guna"),
                        rs.getString("siki_tetapkan"),
                        rs.getString("siki_dokumentasi"),
                        rs.getString("siki_jelaskan"),
                        rs.getString("siki_kolaborasi"),
                        rs.getString("ket_kolaborasi_pemberian"),
                        rs.getString("status_rawat"),
                        rs.getString("nip_petugas")
                    });
                }
            } catch (Exception e) {
                System.out.println("rekammedis.RMMasalahKeperawatanNyeriAkut.tampil() : " + e);
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
        chkSdkiAgenFisiologis.setSelected(false);
        chkSdkiAgenKimiawi.setSelected(false);
        chkSdkiAgenFisik.setSelected(false);
        chkSdkiMengeluh.setSelected(false);
        chkSdkiTampak.setSelected(false);
        chkSdkiBersikap.setSelected(false);
        chkSdkiGelisah.setSelected(false);
        chkSdkiFrekuensi.setSelected(false);
        chkSdkiSulit.setSelected(false);
        chkSdkiTekanan.setSelected(false);
        chkSdkiPola.setSelected(false);
        chkSdkiNafsu.setSelected(false);
        chkSdkiProses.setSelected(false);
        chkSdkiMenarik.setSelected(false);
        chkSdkiBerfokus.setSelected(false);
        chkSdkiDiaforesis.setSelected(false);
        chkSdkiKondisi.setSelected(false);
        chkSdkiCedera.setSelected(false);
        chkSdkiInfeksi.setSelected(false);
        chkSdkiSindrom.setSelected(false);
        chkSdkiGlaukoma.setSelected(false);
        chkSlkiTingkat.setSelected(false);
        TketSlkiTingkat.setText("");
        TketSlkiTingkat.setEnabled(false);
        chkSlkiKeluhan.setSelected(false);
        chkSlkiMeringis.setSelected(false);
        chkSlkiSikap.setSelected(false);
        chkSlkiGelisah.setSelected(false);
        chkSlkiKesulitan.setSelected(false);
        chkSlkiMenarik.setSelected(false);
        chkSlkiPerasaan.setSelected(false);
        chkSikiIdenLokasi.setSelected(false);
        chkSikiIdenSkala.setSelected(false);
        chkSikiIdenRespon.setSelected(false);
        chkSikiIdenFaktor.setSelected(false);
        chkSikiIdenPengetahuan.setSelected(false);
        chkSikiIdenPengaruh.setSelected(false);
        chkSikiMonKeberhasilan.setSelected(false);
        chkSikiMonEfek.setSelected(false);
        chkSikiBerikan.setSelected(false);
        chkSikiKontrol.setSelected(false);
        chkSikiFasilitas.setSelected(false);
        chkSikiPertimbangkanJenis.setSelected(false);
        chkSikiJelasPenyebab.setSelected(false);
        chkSikiJelasStrategi.setSelected(false);
        chkSikiAnjurMemonitor.setSelected(false);
        chkSikiAnjurMenggunakan.setSelected(false);
        chkSikiAnjurTeknik.setSelected(false);
        TketKolaborasiManajemen.setText("");
        chkSikiIdenKarakteristik.setSelected(false);
        chkSikiIdenRiwayat.setSelected(false);
        chkSikiIdenKesesusian.setSelected(false);
        chkSikiMonitorTanda.setSelected(false);
        chkSikiDiskusikan.setSelected(false);
        chkSikiPertimbangkanGuna.setSelected(false);
        chkSikiTetapkan.setSelected(false);
        chkSikiDokumentasi.setSelected(false);
        chkSikiJelaskan.setSelected(false);
        chkSikiKolaborasi.setSelected(false);
        TketKolaborasiPemberian.setText("");
        TketKolaborasiPemberian.setEnabled(false);
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

            sdkiAgenFisiologis = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 9).toString();
            sdkiAgenKimiawi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 10).toString();
            sdkiAgenFisik = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 11).toString();
            sdkiMengeluh = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 12).toString();
            sdkiTampak = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 13).toString();
            sdkiBersikap = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 14).toString();
            sdkiGelisah = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 15).toString();
            sdkiFrekuensi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 16).toString();
            sdkiSulit = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 17).toString();
            sdkiTekanan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 18).toString();
            sdkiPola = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 19).toString();
            sdkiNafsu = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 20).toString();
            sdkiProses = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 21).toString();
            sdkiMenarik = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 22).toString();
            sdkiBerfokus = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 23).toString();
            sdkiDiaforesis = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 24).toString();
            sdkiKondisi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 25).toString();
            sdkiCedera = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 26).toString();
            sdkiInfeksi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 27).toString();
            sdkiSindrom = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 28).toString();
            sdkiGlaukoma = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 29).toString();
            slkiTingkat = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 30).toString();
            TketSlkiTingkat.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 31).toString());
            slkiKeluhan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 32).toString();
            slkiMeringis = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 33).toString();
            slkiSikap = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 34).toString();
            slkiGelisah = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 35).toString();
            slkiKesulitan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 36).toString();
            slkiMenarik = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 37).toString();
            slkiPerasaan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 38).toString();
            sikiIdenLokasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 39).toString();
            sikiIdenSkala = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 40).toString();
            sikiIdenRespon = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 41).toString();
            sikiIdenFaktor = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 42).toString();
            sikiIdenPengetahuan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 43).toString();
            sikiIdenPengaruh = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 44).toString();
            sikiMonKeberhasilan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 45).toString();
            sikiMonEfek = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 46).toString();
            sikiBerikan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 47).toString();
            sikiKontrol = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 48).toString();
            sikiFasilitas = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 49).toString();
            sikiPertimbangkanJenis = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 50).toString();
            sikiJelasPenyebab = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 51).toString();
            sikiJelasStrategi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 52).toString();
            sikiAnjurMemonitor = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 53).toString();
            sikiAnjurMenggunakan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 54).toString();
            sikiAnjurTeknik = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 55).toString();
            TketKolaborasiManajemen.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 56).toString());
            sikiIdenKarakteristik = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 57).toString();
            sikiIdenRiwayat = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 58).toString();
            sikiIdenKesesusian = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 59).toString();
            sikiMonitorTanda = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 60).toString();
            sikiDiskusikan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 61).toString();
            sikiPertimbangkanGuna = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 62).toString();
            sikiTetapkan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 63).toString();
            sikiDokumentasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 64).toString();
            sikiJelaskan = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 65).toString();
            sikiKolaborasi = tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 66).toString();
            TketKolaborasiPemberian.setText(tbMasalah.getValueAt(tbMasalah.getSelectedRow(), 67).toString());
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
        if (sdkiAgenFisiologis.equals("ya")) {
            chkSdkiAgenFisiologis.setSelected(true);
        } else {
            chkSdkiAgenFisiologis.setSelected(false);
        }

        if (sdkiAgenKimiawi.equals("ya")) {
            chkSdkiAgenKimiawi.setSelected(true);
        } else {
            chkSdkiAgenKimiawi.setSelected(false);
        }

        if (sdkiAgenFisik.equals("ya")) {
            chkSdkiAgenFisik.setSelected(true);
        } else {
            chkSdkiAgenFisik.setSelected(false);
        }

        if (sdkiMengeluh.equals("ya")) {
            chkSdkiMengeluh.setSelected(true);
        } else {
            chkSdkiMengeluh.setSelected(false);
        }

        if (sdkiTampak.equals("ya")) {
            chkSdkiTampak.setSelected(true);
        } else {
            chkSdkiTampak.setSelected(false);
        }

        if (sdkiBersikap.equals("ya")) {
            chkSdkiBersikap.setSelected(true);
        } else {
            chkSdkiBersikap.setSelected(false);
        }

        if (sdkiGelisah.equals("ya")) {
            chkSdkiGelisah.setSelected(true);
        } else {
            chkSdkiGelisah.setSelected(false);
        }

        if (sdkiFrekuensi.equals("ya")) {
            chkSdkiFrekuensi.setSelected(true);
        } else {
            chkSdkiFrekuensi.setSelected(false);
        }

        if (sdkiSulit.equals("ya")) {
            chkSdkiSulit.setSelected(true);
        } else {
            chkSdkiSulit.setSelected(false);
        }

        if (sdkiTekanan.equals("ya")) {
            chkSdkiTekanan.setSelected(true);
        } else {
            chkSdkiTekanan.setSelected(false);
        }

        if (sdkiPola.equals("ya")) {
            chkSdkiPola.setSelected(true);
        } else {
            chkSdkiPola.setSelected(false);
        }

        if (sdkiNafsu.equals("ya")) {
            chkSdkiNafsu.setSelected(true);
        } else {
            chkSdkiNafsu.setSelected(false);
        }

        if (sdkiProses.equals("ya")) {
            chkSdkiProses.setSelected(true);
        } else {
            chkSdkiProses.setSelected(false);
        }

        if (sdkiMenarik.equals("ya")) {
            chkSdkiMenarik.setSelected(true);
        } else {
            chkSdkiMenarik.setSelected(false);
        }

        if (sdkiBerfokus.equals("ya")) {
            chkSdkiBerfokus.setSelected(true);
        } else {
            chkSdkiBerfokus.setSelected(false);
        }

        if (sdkiDiaforesis.equals("ya")) {
            chkSdkiDiaforesis.setSelected(true);
        } else {
            chkSdkiDiaforesis.setSelected(false);
        }

        if (sdkiKondisi.equals("ya")) {
            chkSdkiKondisi.setSelected(true);
        } else {
            chkSdkiKondisi.setSelected(false);
        }

        if (sdkiCedera.equals("ya")) {
            chkSdkiCedera.setSelected(true);
        } else {
            chkSdkiCedera.setSelected(false);
        }

        if (sdkiInfeksi.equals("ya")) {
            chkSdkiInfeksi.setSelected(true);
        } else {
            chkSdkiInfeksi.setSelected(false);
        }

        if (sdkiSindrom.equals("ya")) {
            chkSdkiSindrom.setSelected(true);
        } else {
            chkSdkiSindrom.setSelected(false);
        }

        if (sdkiGlaukoma.equals("ya")) {
            chkSdkiGlaukoma.setSelected(true);
        } else {
            chkSdkiGlaukoma.setSelected(false);
        }

        if (slkiTingkat.equals("ya")) {
            chkSlkiTingkat.setSelected(true);
            TketSlkiTingkat.setEnabled(true);
        } else {
            chkSlkiTingkat.setSelected(false);
            TketSlkiTingkat.setEnabled(false);
        }

        if (slkiKeluhan.equals("ya")) {
            chkSlkiKeluhan.setSelected(true);
        } else {
            chkSlkiKeluhan.setSelected(false);
        }

        if (slkiMeringis.equals("ya")) {
            chkSlkiMeringis.setSelected(true);
        } else {
            chkSlkiMeringis.setSelected(false);
        }

        if (slkiSikap.equals("ya")) {
            chkSlkiSikap.setSelected(true);
        } else {
            chkSlkiSikap.setSelected(false);
        }

        if (slkiGelisah.equals("ya")) {
            chkSlkiGelisah.setSelected(true);
        } else {
            chkSlkiGelisah.setSelected(false);
        }

        if (slkiKesulitan.equals("ya")) {
            chkSlkiKesulitan.setSelected(true);
        } else {
            chkSlkiKesulitan.setSelected(false);
        }

        if (slkiMenarik.equals("ya")) {
            chkSlkiMenarik.setSelected(true);
        } else {
            chkSlkiMenarik.setSelected(false);
        }

        if (slkiPerasaan.equals("ya")) {
            chkSlkiPerasaan.setSelected(true);
        } else {
            chkSlkiPerasaan.setSelected(false);
        }

        if (sikiIdenLokasi.equals("ya")) {
            chkSikiIdenLokasi.setSelected(true);
        } else {
            chkSikiIdenLokasi.setSelected(false);
        }

        if (sikiIdenSkala.equals("ya")) {
            chkSikiIdenSkala.setSelected(true);
        } else {
            chkSikiIdenSkala.setSelected(false);
        }

        if (sikiIdenRespon.equals("ya")) {
            chkSikiIdenRespon.setSelected(true);
        } else {
            chkSikiIdenRespon.setSelected(false);
        }

        if (sikiIdenFaktor.equals("ya")) {
            chkSikiIdenFaktor.setSelected(true);
        } else {
            chkSikiIdenFaktor.setSelected(false);
        }

        if (sikiIdenPengetahuan.equals("ya")) {
            chkSikiIdenPengetahuan.setSelected(true);
        } else {
            chkSikiIdenPengetahuan.setSelected(false);
        }

        if (sikiIdenPengaruh.equals("ya")) {
            chkSikiIdenPengaruh.setSelected(true);
        } else {
            chkSikiIdenPengaruh.setSelected(false);
        }

        if (sikiMonKeberhasilan.equals("ya")) {
            chkSikiMonKeberhasilan.setSelected(true);
        } else {
            chkSikiMonKeberhasilan.setSelected(false);
        }

        if (sikiMonEfek.equals("ya")) {
            chkSikiMonEfek.setSelected(true);
        } else {
            chkSikiMonEfek.setSelected(false);
        }

        if (sikiBerikan.equals("ya")) {
            chkSikiBerikan.setSelected(true);
        } else {
            chkSikiBerikan.setSelected(false);
        }

        if (sikiKontrol.equals("ya")) {
            chkSikiKontrol.setSelected(true);
        } else {
            chkSikiKontrol.setSelected(false);
        }

        if (sikiFasilitas.equals("ya")) {
            chkSikiFasilitas.setSelected(true);
        } else {
            chkSikiFasilitas.setSelected(false);
        }

        if (sikiPertimbangkanJenis.equals("ya")) {
            chkSikiPertimbangkanJenis.setSelected(true);
        } else {
            chkSikiPertimbangkanJenis.setSelected(false);
        }

        if (sikiJelasPenyebab.equals("ya")) {
            chkSikiJelasPenyebab.setSelected(true);
        } else {
            chkSikiJelasPenyebab.setSelected(false);
        }

        if (sikiJelasStrategi.equals("ya")) {
            chkSikiJelasStrategi.setSelected(true);
        } else {
            chkSikiJelasStrategi.setSelected(false);
        }

        if (sikiAnjurMemonitor.equals("ya")) {
            chkSikiAnjurMemonitor.setSelected(true);
        } else {
            chkSikiAnjurMemonitor.setSelected(false);
        }

        if (sikiAnjurMenggunakan.equals("ya")) {
            chkSikiAnjurMenggunakan.setSelected(true);
        } else {
            chkSikiAnjurMenggunakan.setSelected(false);
        }

        if (sikiAnjurTeknik.equals("ya")) {
            chkSikiAnjurTeknik.setSelected(true);
        } else {
            chkSikiAnjurTeknik.setSelected(false);
        }

        if (sikiIdenKarakteristik.equals("ya")) {
            chkSikiIdenKarakteristik.setSelected(true);
        } else {
            chkSikiIdenKarakteristik.setSelected(false);
        }

        if (sikiIdenRiwayat.equals("ya")) {
            chkSikiIdenRiwayat.setSelected(true);
        } else {
            chkSikiIdenRiwayat.setSelected(false);
        }

        if (sikiIdenKesesusian.equals("ya")) {
            chkSikiIdenKesesusian.setSelected(true);
        } else {
            chkSikiIdenKesesusian.setSelected(false);
        }

        if (sikiMonitorTanda.equals("ya")) {
            chkSikiMonitorTanda.setSelected(true);
        } else {
            chkSikiMonitorTanda.setSelected(false);
        }

        if (sikiDiskusikan.equals("ya")) {
            chkSikiDiskusikan.setSelected(true);
        } else {
            chkSikiDiskusikan.setSelected(false);
        }

        if (sikiPertimbangkanGuna.equals("ya")) {
            chkSikiPertimbangkanGuna.setSelected(true);
        } else {
            chkSikiPertimbangkanGuna.setSelected(false);
        }

        if (sikiTetapkan.equals("ya")) {
            chkSikiTetapkan.setSelected(true);
        } else {
            chkSikiTetapkan.setSelected(false);
        }

        if (sikiDokumentasi.equals("ya")) {
            chkSikiDokumentasi.setSelected(true);
        } else {
            chkSikiDokumentasi.setSelected(false);
        }

        if (sikiJelaskan.equals("ya")) {
            chkSikiJelaskan.setSelected(true);
        } else {
            chkSikiJelaskan.setSelected(false);
        }

        if (sikiKolaborasi.equals("ya")) {
            chkSikiKolaborasi.setSelected(true);
            TketKolaborasiPemberian.setEnabled(true);
        } else {
            chkSikiKolaborasi.setSelected(false);
            TketKolaborasiPemberian.setEnabled(false);
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
        if (chkSdkiAgenFisiologis.isSelected() == true) {
            sdkiAgenFisiologis = "ya";
        } else {
            sdkiAgenFisiologis = "tidak";
        }

        if (chkSdkiAgenKimiawi.isSelected() == true) {
            sdkiAgenKimiawi = "ya";
        } else {
            sdkiAgenKimiawi = "tidak";
        }

        if (chkSdkiAgenFisik.isSelected() == true) {
            sdkiAgenFisik = "ya";
        } else {
            sdkiAgenFisik = "tidak";
        }

        if (chkSdkiMengeluh.isSelected() == true) {
            sdkiMengeluh = "ya";
        } else {
            sdkiMengeluh = "tidak";
        }

        if (chkSdkiTampak.isSelected() == true) {
            sdkiTampak = "ya";
        } else {
            sdkiTampak = "tidak";
        }

        if (chkSdkiBersikap.isSelected() == true) {
            sdkiBersikap = "ya";
        } else {
            sdkiBersikap = "tidak";
        }

        if (chkSdkiGelisah.isSelected() == true) {
            sdkiGelisah = "ya";
        } else {
            sdkiGelisah = "tidak";
        }

        if (chkSdkiFrekuensi.isSelected() == true) {
            sdkiFrekuensi = "ya";
        } else {
            sdkiFrekuensi = "tidak";
        }

        if (chkSdkiSulit.isSelected() == true) {
            sdkiSulit = "ya";
        } else {
            sdkiSulit = "tidak";
        }

        if (chkSdkiTekanan.isSelected() == true) {
            sdkiTekanan = "ya";
        } else {
            sdkiTekanan = "tidak";
        }

        if (chkSdkiPola.isSelected() == true) {
            sdkiPola = "ya";
        } else {
            sdkiPola = "tidak";
        }

        if (chkSdkiNafsu.isSelected() == true) {
            sdkiNafsu = "ya";
        } else {
            sdkiNafsu = "tidak";
        }

        if (chkSdkiProses.isSelected() == true) {
            sdkiProses = "ya";
        } else {
            sdkiProses = "tidak";
        }

        if (chkSdkiMenarik.isSelected() == true) {
            sdkiMenarik = "ya";
        } else {
            sdkiMenarik = "tidak";
        }

        if (chkSdkiBerfokus.isSelected() == true) {
            sdkiBerfokus = "ya";
        } else {
            sdkiBerfokus = "tidak";
        }

        if (chkSdkiDiaforesis.isSelected() == true) {
            sdkiDiaforesis = "ya";
        } else {
            sdkiDiaforesis = "tidak";
        }

        if (chkSdkiKondisi.isSelected() == true) {
            sdkiKondisi = "ya";
        } else {
            sdkiKondisi = "tidak";
        }

        if (chkSdkiCedera.isSelected() == true) {
            sdkiCedera = "ya";
        } else {
            sdkiCedera = "tidak";
        }

        if (chkSdkiInfeksi.isSelected() == true) {
            sdkiInfeksi = "ya";
        } else {
            sdkiInfeksi = "tidak";
        }

        if (chkSdkiSindrom.isSelected() == true) {
            sdkiSindrom = "ya";
        } else {
            sdkiSindrom = "tidak";
        }

        if (chkSdkiGlaukoma.isSelected() == true) {
            sdkiGlaukoma = "ya";
        } else {
            sdkiGlaukoma = "tidak";
        }

        if (chkSlkiTingkat.isSelected() == true) {
            slkiTingkat = "ya";
        } else {
            slkiTingkat = "tidak";
        }

        if (chkSlkiKeluhan.isSelected() == true) {
            slkiKeluhan = "ya";
        } else {
            slkiKeluhan = "tidak";
        }

        if (chkSlkiMeringis.isSelected() == true) {
            slkiMeringis = "ya";
        } else {
            slkiMeringis = "tidak";
        }

        if (chkSlkiSikap.isSelected() == true) {
            slkiSikap = "ya";
        } else {
            slkiSikap = "tidak";
        }

        if (chkSlkiGelisah.isSelected() == true) {
            slkiGelisah = "ya";
        } else {
            slkiGelisah = "tidak";
        }

        if (chkSlkiKesulitan.isSelected() == true) {
            slkiKesulitan = "ya";
        } else {
            slkiKesulitan = "tidak";
        }

        if (chkSlkiMenarik.isSelected() == true) {
            slkiMenarik = "ya";
        } else {
            slkiMenarik = "tidak";
        }

        if (chkSlkiPerasaan.isSelected() == true) {
            slkiPerasaan = "ya";
        } else {
            slkiPerasaan = "tidak";
        }

        if (chkSikiIdenLokasi.isSelected() == true) {
            sikiIdenLokasi = "ya";
        } else {
            sikiIdenLokasi = "tidak";
        }

        if (chkSikiIdenSkala.isSelected() == true) {
            sikiIdenSkala = "ya";
        } else {
            sikiIdenSkala = "tidak";
        }

        if (chkSikiIdenRespon.isSelected() == true) {
            sikiIdenRespon = "ya";
        } else {
            sikiIdenRespon = "tidak";
        }

        if (chkSikiIdenFaktor.isSelected() == true) {
            sikiIdenFaktor = "ya";
        } else {
            sikiIdenFaktor = "tidak";
        }

        if (chkSikiIdenPengetahuan.isSelected() == true) {
            sikiIdenPengetahuan = "ya";
        } else {
            sikiIdenPengetahuan = "tidak";
        }

        if (chkSikiIdenPengaruh.isSelected() == true) {
            sikiIdenPengaruh = "ya";
        } else {
            sikiIdenPengaruh = "tidak";
        }

        if (chkSikiMonKeberhasilan.isSelected() == true) {
            sikiMonKeberhasilan = "ya";
        } else {
            sikiMonKeberhasilan = "tidak";
        }

        if (chkSikiMonEfek.isSelected() == true) {
            sikiMonEfek = "ya";
        } else {
            sikiMonEfek = "tidak";
        }

        if (chkSikiBerikan.isSelected() == true) {
            sikiBerikan = "ya";
        } else {
            sikiBerikan = "tidak";
        }

        if (chkSikiKontrol.isSelected() == true) {
            sikiKontrol = "ya";
        } else {
            sikiKontrol = "tidak";
        }

        if (chkSikiFasilitas.isSelected() == true) {
            sikiFasilitas = "ya";
        } else {
            sikiFasilitas = "tidak";
        }

        if (chkSikiPertimbangkanJenis.isSelected() == true) {
            sikiPertimbangkanJenis = "ya";
        } else {
            sikiPertimbangkanJenis = "tidak";
        }

        if (chkSikiJelasPenyebab.isSelected() == true) {
            sikiJelasPenyebab = "ya";
        } else {
            sikiJelasPenyebab = "tidak";
        }

        if (chkSikiJelasStrategi.isSelected() == true) {
            sikiJelasStrategi = "ya";
        } else {
            sikiJelasStrategi = "tidak";
        }

        if (chkSikiAnjurMemonitor.isSelected() == true) {
            sikiAnjurMemonitor = "ya";
        } else {
            sikiAnjurMemonitor = "tidak";
        }

        if (chkSikiAnjurMenggunakan.isSelected() == true) {
            sikiAnjurMenggunakan = "ya";
        } else {
            sikiAnjurMenggunakan = "tidak";
        }

        if (chkSikiAnjurTeknik.isSelected() == true) {
            sikiAnjurTeknik = "ya";
        } else {
            sikiAnjurTeknik = "tidak";
        }

        if (chkSikiIdenKarakteristik.isSelected() == true) {
            sikiIdenKarakteristik = "ya";
        } else {
            sikiIdenKarakteristik = "tidak";
        }

        if (chkSikiIdenRiwayat.isSelected() == true) {
            sikiIdenRiwayat = "ya";
        } else {
            sikiIdenRiwayat = "tidak";
        }

        if (chkSikiIdenKesesusian.isSelected() == true) {
            sikiIdenKesesusian = "ya";
        } else {
            sikiIdenKesesusian = "tidak";
        }

        if (chkSikiMonitorTanda.isSelected() == true) {
            sikiMonitorTanda = "ya";
        } else {
            sikiMonitorTanda = "tidak";
        }

        if (chkSikiDiskusikan.isSelected() == true) {
            sikiDiskusikan = "ya";
        } else {
            sikiDiskusikan = "tidak";
        }

        if (chkSikiPertimbangkanGuna.isSelected() == true) {
            sikiPertimbangkanGuna = "ya";
        } else {
            sikiPertimbangkanGuna = "tidak";
        }

        if (chkSikiTetapkan.isSelected() == true) {
            sikiTetapkan = "ya";
        } else {
            sikiTetapkan = "tidak";
        }

        if (chkSikiDokumentasi.isSelected() == true) {
            sikiDokumentasi = "ya";
        } else {
            sikiDokumentasi = "tidak";
        }

        if (chkSikiJelaskan.isSelected() == true) {
            sikiJelaskan = "ya";
        } else {
            sikiJelaskan = "tidak";
        }

        if (chkSikiKolaborasi.isSelected() == true) {
            sikiKolaborasi = "ya";
        } else {
            sikiKolaborasi = "tidak";
        }
    }
    
    private void emptVariabel() {
        sdkiAgenFisiologis = "";
        sdkiAgenKimiawi = "";
        sdkiAgenFisik = "";
        sdkiMengeluh = "";
        sdkiTampak = "";
        sdkiBersikap = "";
        sdkiGelisah = "";
        sdkiFrekuensi = "";
        sdkiSulit = "";
        sdkiTekanan = "";
        sdkiPola = "";
        sdkiNafsu = "";
        sdkiProses = "";
        sdkiMenarik = "";
        sdkiBerfokus = "";
        sdkiDiaforesis = "";
        sdkiKondisi = "";
        sdkiCedera = "";
        sdkiInfeksi = "";
        sdkiSindrom = "";
        sdkiGlaukoma = "";
        slkiTingkat = "";
        slkiKeluhan = "";
        slkiMeringis = "";
        slkiSikap = "";
        slkiGelisah = "";
        slkiKesulitan = "";
        slkiMenarik = "";
        slkiPerasaan = "";
        sikiIdenLokasi = "";
        sikiIdenSkala = "";
        sikiIdenRespon = "";
        sikiIdenFaktor = "";
        sikiIdenPengetahuan = "";
        sikiIdenPengaruh = "";
        sikiMonKeberhasilan = "";
        sikiMonEfek = "";
        sikiBerikan = "";
        sikiKontrol = "";
        sikiFasilitas = "";
        sikiPertimbangkanJenis = "";
        sikiJelasPenyebab = "";
        sikiJelasStrategi = "";
        sikiAnjurMemonitor = "";
        sikiAnjurMenggunakan = "";
        sikiAnjurTeknik = "";
        sikiIdenKarakteristik = "";
        sikiIdenRiwayat = "";
        sikiIdenKesesusian = "";
        sikiMonitorTanda = "";
        sikiDiskusikan = "";
        sikiPertimbangkanGuna = "";
        sikiTetapkan = "";
        sikiDokumentasi = "";
        sikiJelaskan = "";
        sikiKolaborasi = "";
    }
}
