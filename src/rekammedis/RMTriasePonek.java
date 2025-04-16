/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */

package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import kepegawaian.DlgCariPetugas;
import laporan.DlgHasilPenunjangMedis;
import laporan.DlgPenyakit;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgNotepad;

/**
 *
 * @author perpustakaan
 */
public final class RMTriasePonek extends javax.swing.JDialog {
    private DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);    
    private PreparedStatement ps, psx, psTemp1, psTemp2;
    private ResultSet rs, rsx, rsTemp1, rsTemp2;
    private int i = 0, x = 0, totskor = 0, pilihan = 0,
        //skor 0    
            sdr_pnh_skor0 = 0,
            seratus_skor0 = 0,
            seratus1_skor0 = 0,
            sembilan_bls_skor0 = 0,
            tiga5_skor0 = 0,
            sembilan6_skor0 = 0,
        //skor 1        
            seratus2_skor1 = 0,
            dua_plh_skor1 = 0,
            sembilan4_skor1 = 0,
        //skor 2        
            sembilan9_skor2 = 0,
            dua2_skor2 = 0,
            sembilan2_skor2 = 0,
        //skor 3        
            selain_skor3 = 0,
            tiga5_skor3 = 0,
            sembilan2_skor3 = 0,
        //hasil tanda vital        
            jlh_kesadaran = 0,
            jlh_tekanan = 0,
            jlh_nadi = 0,
            jlh_respirasi = 0,
            jlh_tempe = 0,
            jlh_satur = 0;
    private String nip = "", kll_tunggal = "", tgl_kejadian_tggl = "", kll = "", tgl_kejadian_kll = "", jatuh = "", luka = "",
            trauma_listrik = "", trauma_zat = "", trauma_lain = "", pacs1 = "", pacs2 = "", pacs3 = "", pacs4 = "", skor0_sadar = "",
            skor0_100 = "", skor0_101 = "", skor0_19 = "", skor0_35 = "", skor0_96 = "", skor102 = "", skor20 = "", skor94 = "",
            skor99 = "", skor22 = "", skor92 = "", skor3_sadar = "", skor3_35 = "", skor3_92 = "", resus = "", nonresus = "", 
            klinik = "", doa = "", skortotal = "", itemDipilih = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMTriasePonek(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        
        tabMode = new DefaultTableModel(null, new String[]{
            "No.Rawat", "No.RM", "Nama Pasien", "Tgl. Lahir", "Tgl. Triase", "Cara Masuk", "Sudah Terpasang", "Alasan Kedatangan", "Rujukan Dari", "Dijemput Oleh",
            "Kendaraan", "Penjelasan Bkn. Ambulan", "Nama Pengantar", "No. Tlp. Pengantar", "Kasus", "Petugas Triase",
            "tanggal", "pukul", "cara_masuk", "sudah_terpasang", "alasan_kedatangan", "rujukan_dari", "dijemput_oleh", "kendaraan", "bukan_ambulan", "nm_pengantar", 
            "telp_pengantar", "kasus", "kll_tunggal", "kll_tunggal_tmpt_kejadian", "tgl_kejadian_kll_tunggal", "kll_tunggal_tanggal", "kll_tunggal_pukul", "kll_versus", 
            "versus1", "versus2", "kll_tmpt_kejadian", "tgl_kejadian_kll", "kll_tanggal", "kll_pukul", "jatuh", "ket_jatuh", "luka_bakar", "ket_luka_bakar", 
            "trauma_listrik", "ket_trauma_listrik", "trauma_zat_kimia", "ket_trauma_zat_kimia", "trauma_lain", "ket_trauma_lain", "icd_10", "keluhan_utama", "pacs1", 
            "pacs2", "pacs3", "pacs4", "kesadaran", "td", "nadi", "napas", "temperatur", "saturasi", "nyeri", "bb", "tb", "skor0_sadar_penuh", "skor0_100", "skor0_101", 
            "skor0_19", "skor0_35_3", "skor0_96_100", "skor1_102", "skor1_20_21", "skor1_94_95", "skor2_99", "skor2_22", "skor2_92_93", "skor3_selain", "skor3_35_3", 
            "skor3_92", "total_skor", "catatan", "keputusan", "pukul_keputusan", "triase_resusitasi", "triase_non_resusitasi", "triase_klinik", "triase_doa", 
            "nip_petugas", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbTriase.setModel(tabMode);
        tbTriase.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTriase.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 90; i++) {
            TableColumn column = tbTriase.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(115);
            } else if (i == 4) {
                column.setPreferredWidth(120);
            } else if (i == 5) {
                column.setPreferredWidth(100);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(100);                
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            } else if (i == 11) {
                column.setPreferredWidth(250);
            } else if (i == 12) {
                column.setPreferredWidth(220);
            } else if (i == 13) {
                column.setPreferredWidth(120);
            } else if (i == 14) {
                column.setPreferredWidth(90);
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
            }
        }
        tbTriase.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        rujukan_dari.setDocument(new batasInput((int) 200).getKata(rujukan_dari));
        dijemput_oleh.setDocument(new batasInput((int) 200).getKata(dijemput_oleh));
        bkn_ambulan.setDocument(new batasInput((int) 150).getKata(bkn_ambulan));
        nm_pengantar.setDocument(new batasInput((int) 40).getKata(nm_pengantar));
        tlpn_pengantar.setDocument(new batasInput((int) 13).getKata(tlpn_pengantar));
        tmpt_kejadian_tunggal.setDocument(new batasInput((int) 100).getKata(tmpt_kejadian_tunggal));
        versus1.setDocument(new batasInput((int) 100).getKata(versus1));
        versus2.setDocument(new batasInput((int) 100).getKata(versus2));
        tmpt_kejadian.setDocument(new batasInput((int) 100).getKata(tmpt_kejadian));
        Ticd10.setDocument(new batasInput((int) 10).getKata(Ticd10));
        tkann_darah.setDocument(new batasInput((int) 7).getKata(tkann_darah));
        nadi.setDocument(new batasInput((int) 7).getKata(nadi));
        pernapasan.setDocument(new batasInput((int) 7).getKata(pernapasan));
        temperatur.setDocument(new batasInput((int) 7).getKata(temperatur));
        saturasi.setDocument(new batasInput((int) 7).getKata(saturasi));
        Tnyeri.setDocument(new batasInput((int) 100).getKata(Tnyeri));
        bb.setDocument(new batasInput((int) 7).getKata(bb));
        tb.setDocument(new batasInput((int) 7).getKata(tb));
        Tkeputusan.setDocument(new batasInput((int) 230).getKata(Tkeputusan));
        
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    nip = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();                    
                    nm_petugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    btnPetugas.requestFocus();
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHasilPemeriksaanPenunjang = new javax.swing.JMenuItem();
        MnDokumenJangMed = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroupKesadaran = new javax.swing.ButtonGroup();
        buttonGroupTekanan = new javax.swing.ButtonGroup();
        buttonGroupNadi = new javax.swing.ButtonGroup();
        buttonGroupRespirasi = new javax.swing.ButtonGroup();
        buttonGroupTemperatur = new javax.swing.ButtonGroup();
        buttonGroupSaturasi = new javax.swing.ButtonGroup();
        WindowTemplate = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        jLabel32 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCopas = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        Scroll1 = new widget.ScrollPane();
        tbTemplate = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        Ttemplate = new widget.TextArea();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        FormTriase = new widget.InternalFrame();
        ScrollTriase1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel63 = new widget.Label();
        jLabel68 = new widget.Label();
        Ttgl = new widget.Tanggal();
        jLabel69 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel70 = new widget.Label();
        TtglLahir = new widget.TextBox();
        jLabel64 = new widget.Label();
        cmbCaraMasuk = new widget.ComboBox();
        jLabel8 = new widget.Label();
        sdh_terpasang = new widget.TextBox();
        jLabel23 = new widget.Label();
        cmbAlasanKedatangan = new widget.ComboBox();
        jLabel9 = new widget.Label();
        rujukan_dari = new widget.TextBox();
        jLabel10 = new widget.Label();
        dijemput_oleh = new widget.TextBox();
        jLabel24 = new widget.Label();
        cmbKendaraan = new widget.ComboBox();
        jLabel11 = new widget.Label();
        bkn_ambulan = new widget.TextBox();
        jLabel25 = new widget.Label();
        nm_pengantar = new widget.TextBox();
        jLabel12 = new widget.Label();
        tlpn_pengantar = new widget.TextBox();
        jLabel26 = new widget.Label();
        cmbKasus = new widget.ComboBox();
        jLabel5 = new widget.Label();
        nm_petugas = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel27 = new widget.Label();
        ChkKll_tunggal = new widget.CekBox();
        jLabel13 = new widget.Label();
        tmpt_kejadian_tunggal = new widget.TextBox();
        Chktgl_kll_tunggal = new widget.CekBox();
        tgl_kejadian_tunggal = new widget.Tanggal();
        jLabel71 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        ChkKll = new widget.CekBox();
        versus1 = new widget.TextBox();
        jLabel14 = new widget.Label();
        versus2 = new widget.TextBox();
        jLabel15 = new widget.Label();
        tmpt_kejadian = new widget.TextBox();
        Chktgl_kll = new widget.CekBox();
        tgl_kejadian = new widget.Tanggal();
        jLabel72 = new widget.Label();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        ChkJatuh = new widget.CekBox();
        jLabel16 = new widget.Label();
        ket_jatuh = new widget.TextBox();
        ChkLuka = new widget.CekBox();
        jLabel17 = new widget.Label();
        ket_luka = new widget.TextBox();
        Chktrauma_listrik = new widget.CekBox();
        jLabel22 = new widget.Label();
        ket_trauma_listrik = new widget.TextBox();
        Chktrauma_zat = new widget.CekBox();
        jLabel30 = new widget.Label();
        ket_trauma_zat = new widget.TextBox();
        Chktrauma_lain = new widget.CekBox();
        jLabel31 = new widget.Label();
        ket_trauma_lain = new widget.TextBox();
        scrollPane9 = new widget.ScrollPane();
        Tkeluhan_utama = new widget.TextArea();
        jLabel33 = new widget.Label();
        BtnKeluhan = new widget.Button();
        jLabel34 = new widget.Label();
        Chkpacs1 = new widget.CekBox();
        Chkpacs2 = new widget.CekBox();
        Chkpacs3 = new widget.CekBox();
        Chkpacs4 = new widget.CekBox();
        jLabel35 = new widget.Label();
        jLabel45 = new widget.Label();
        jLabel28 = new widget.Label();
        cmbKesadaran = new widget.ComboBox();
        jLabel46 = new widget.Label();
        bb = new widget.TextBox();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        tb = new widget.TextBox();
        jLabel50 = new widget.Label();
        jLabel18 = new widget.Label();
        Ticd10 = new widget.TextBox();
        jLabel29 = new widget.Label();
        tkann_darah = new widget.TextBox();
        jLabel36 = new widget.Label();
        jLabel38 = new widget.Label();
        pernapasan = new widget.TextBox();
        jLabel40 = new widget.Label();
        jLabel41 = new widget.Label();
        saturasi = new widget.TextBox();
        jLabel44 = new widget.Label();
        jLabel37 = new widget.Label();
        nadi = new widget.TextBox();
        jLabel43 = new widget.Label();
        jLabel39 = new widget.Label();
        temperatur = new widget.TextBox();
        nadi1 = new widget.Label();
        jLabel42 = new widget.Label();
        Tnyeri = new widget.TextBox();
        jLabel49 = new widget.Label();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        jLabel60 = new widget.Label();
        jLabel61 = new widget.Label();
        jLabel67 = new widget.Label();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel51 = new widget.Label();
        Chksadar_skor0 = new widget.CekBox();
        Chksadar_skor3 = new widget.CekBox();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel52 = new widget.Label();
        Chk100 = new widget.CekBox();
        Chk99 = new widget.CekBox();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel53 = new widget.Label();
        Chk101 = new widget.CekBox();
        Chk102 = new widget.CekBox();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel54 = new widget.Label();
        Chk19 = new widget.CekBox();
        Chk20 = new widget.CekBox();
        Chk22 = new widget.CekBox();
        jSeparator11 = new javax.swing.JSeparator();
        Chk35_skor3 = new widget.CekBox();
        Chk35_skor0 = new widget.CekBox();
        jLabel55 = new widget.Label();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel56 = new widget.Label();
        Chk96 = new widget.CekBox();
        Chk94 = new widget.CekBox();
        Chk92 = new widget.CekBox();
        Chk92_skor3 = new widget.CekBox();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel57 = new widget.Label();
        label_5 = new widget.Label();
        label_24 = new widget.Label();
        label_01 = new widget.Label();
        scrollPane10 = new widget.ScrollPane();
        Tcttn_khusus = new widget.TextArea();
        jLabel62 = new widget.Label();
        jLabel65 = new widget.Label();
        Tkeputusan = new widget.TextBox();
        cmbJam3 = new widget.ComboBox();
        cmbMnt3 = new widget.ComboBox();
        cmbDtk3 = new widget.ComboBox();
        Chkresus = new widget.CekBox();
        Chknon_resus = new widget.CekBox();
        Chkklinik = new widget.CekBox();
        Chkdoa = new widget.CekBox();
        BtnCatatan = new widget.Button();
        jLabel66 = new widget.Label();
        internalFrame4 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbTriase = new widget.Table();
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
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnNotepad = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHasilPemeriksaanPenunjang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanPenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanPenunjang.setText("Hasil Pemeriksaan Penunjang");
        MnHasilPemeriksaanPenunjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanPenunjang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanPenunjang.setIconTextGap(5);
        MnHasilPemeriksaanPenunjang.setName("MnHasilPemeriksaanPenunjang"); // NOI18N
        MnHasilPemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(190, 26));
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
        MnDokumenJangMed.setPreferredSize(new java.awt.Dimension(190, 26));
        MnDokumenJangMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokumenJangMedActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDokumenJangMed);

        WindowTemplate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTemplate.setName("WindowTemplate"); // NOI18N
        WindowTemplate.setUndecorated(true);
        WindowTemplate.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Triase IGD ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        panelisi4.setBackground(new java.awt.Color(255, 150, 255));
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Key Word :");
        jLabel32.setName("jLabel32"); // NOI18N
        jLabel32.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel32.setRequestFocusEnabled(false);
        panelisi4.add(jLabel32);

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

        internalFrame5.add(panelisi4, java.awt.BorderLayout.PAGE_END);

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

        internalFrame5.add(jPanel1, java.awt.BorderLayout.CENTER);

        WindowTemplate.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Triase Ponek ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        FormTriase.setBorder(null);
        FormTriase.setName("FormTriase"); // NOI18N
        FormTriase.setLayout(new java.awt.BorderLayout(1, 1));

        ScrollTriase1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        ScrollTriase1.setName("ScrollTriase1"); // NOI18N
        ScrollTriase1.setOpaque(true);
        ScrollTriase1.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBorder(null);
        FormInput.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1162));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 130, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(136, 10, 122, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(332, 10, 410, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(260, 10, 70, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Kontak Awal Pasien :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 38, 130, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Tanggal :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(136, 38, 60, 23);

        Ttgl.setEditable(false);
        Ttgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-04-2025" }));
        Ttgl.setDisplayFormat("dd-MM-yyyy");
        Ttgl.setName("Ttgl"); // NOI18N
        Ttgl.setOpaque(false);
        Ttgl.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(Ttgl);
        Ttgl.setBounds(200, 38, 90, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Pukul :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(290, 38, 55, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(350, 38, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(403, 38, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(456, 38, 45, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Tgl. Lahir :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(500, 38, 67, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setBackground(new java.awt.Color(245, 250, 240));
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        FormInput.add(TtglLahir);
        TtglLahir.setBounds(572, 38, 170, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Cara Masuk :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 66, 130, 23);

        cmbCaraMasuk.setForeground(new java.awt.Color(0, 0, 0));
        cmbCaraMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Jalan", "Brandcar", "Kursi Roda", "Digendong" }));
        cmbCaraMasuk.setName("cmbCaraMasuk"); // NOI18N
        cmbCaraMasuk.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbCaraMasuk);
        cmbCaraMasuk.setBounds(136, 66, 90, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Sudah Terpasang :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(227, 66, 110, 23);

        sdh_terpasang.setForeground(new java.awt.Color(0, 0, 0));
        sdh_terpasang.setName("sdh_terpasang"); // NOI18N
        sdh_terpasang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sdh_terpasangKeyPressed(evt);
            }
        });
        FormInput.add(sdh_terpasang);
        sdh_terpasang.setBounds(342, 66, 400, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Alasan Kedatangan :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 94, 130, 23);

        cmbAlasanKedatangan.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlasanKedatangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Datang Sendiri", "Polisi", "Rujukan, dari", "Dijemput oleh" }));
        cmbAlasanKedatangan.setName("cmbAlasanKedatangan"); // NOI18N
        cmbAlasanKedatangan.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbAlasanKedatangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAlasanKedatanganActionPerformed(evt);
            }
        });
        FormInput.add(cmbAlasanKedatangan);
        cmbAlasanKedatangan.setBounds(136, 94, 110, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Rujukan dari :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(245, 94, 92, 23);

        rujukan_dari.setForeground(new java.awt.Color(0, 0, 0));
        rujukan_dari.setName("rujukan_dari"); // NOI18N
        rujukan_dari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rujukan_dariKeyPressed(evt);
            }
        });
        FormInput.add(rujukan_dari);
        rujukan_dari.setBounds(342, 94, 400, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Dijemput Oleh :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(245, 122, 92, 23);

        dijemput_oleh.setForeground(new java.awt.Color(0, 0, 0));
        dijemput_oleh.setName("dijemput_oleh"); // NOI18N
        dijemput_oleh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dijemput_olehKeyPressed(evt);
            }
        });
        FormInput.add(dijemput_oleh);
        dijemput_oleh.setBounds(342, 122, 400, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Kendaraan :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 150, 130, 23);

        cmbKendaraan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKendaraan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ambulance", "Kendaraan bukan ambulance" }));
        cmbKendaraan.setName("cmbKendaraan"); // NOI18N
        cmbKendaraan.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbKendaraan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKendaraanActionPerformed(evt);
            }
        });
        FormInput.add(cmbKendaraan);
        cmbKendaraan.setBounds(136, 150, 175, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Bukan Ambulance :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(311, 150, 110, 23);

        bkn_ambulan.setForeground(new java.awt.Color(0, 0, 0));
        bkn_ambulan.setName("bkn_ambulan"); // NOI18N
        bkn_ambulan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bkn_ambulanKeyPressed(evt);
            }
        });
        FormInput.add(bkn_ambulan);
        bkn_ambulan.setBounds(425, 150, 317, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Nama Pengantar :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 178, 130, 23);

        nm_pengantar.setForeground(new java.awt.Color(0, 0, 0));
        nm_pengantar.setName("nm_pengantar"); // NOI18N
        nm_pengantar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nm_pengantarKeyPressed(evt);
            }
        });
        FormInput.add(nm_pengantar);
        nm_pengantar.setBounds(136, 178, 380, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("No. Telpn. :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(521, 178, 70, 23);

        tlpn_pengantar.setForeground(new java.awt.Color(0, 0, 0));
        tlpn_pengantar.setName("tlpn_pengantar"); // NOI18N
        tlpn_pengantar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tlpn_pengantarKeyPressed(evt);
            }
        });
        FormInput.add(tlpn_pengantar);
        tlpn_pengantar.setBounds(595, 178, 147, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Kasus :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 206, 130, 23);

        cmbKasus.setForeground(new java.awt.Color(0, 0, 0));
        cmbKasus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Trauma", "Non Trauma" }));
        cmbKasus.setName("cmbKasus"); // NOI18N
        cmbKasus.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbKasus);
        cmbKasus.setBounds(136, 206, 100, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Petugas Triase :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(236, 206, 93, 23);

        nm_petugas.setEditable(false);
        nm_petugas.setForeground(new java.awt.Color(0, 0, 0));
        nm_petugas.setName("nm_petugas"); // NOI18N
        FormInput.add(nm_petugas);
        nm_petugas.setBounds(334, 206, 407, 23);

        btnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('1');
        btnPetugas.setToolTipText("Alt+1");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        FormInput.add(btnPetugas);
        btnPetugas.setBounds(742, 206, 28, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("MEKANISME TRAUMA");
        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(0, 234, 150, 23);

        ChkKll_tunggal.setBackground(new java.awt.Color(255, 255, 250));
        ChkKll_tunggal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(ChkKll_tunggal);
        ChkKll_tunggal.setForeground(new java.awt.Color(0, 0, 0));
        ChkKll_tunggal.setText("KLL Tunggal");
        ChkKll_tunggal.setBorderPainted(true);
        ChkKll_tunggal.setBorderPaintedFlat(true);
        ChkKll_tunggal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKll_tunggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKll_tunggal.setName("ChkKll_tunggal"); // NOI18N
        ChkKll_tunggal.setOpaque(false);
        ChkKll_tunggal.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkKll_tunggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkKll_tunggalActionPerformed(evt);
            }
        });
        FormInput.add(ChkKll_tunggal);
        ChkKll_tunggal.setBounds(20, 262, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tempat Kejadian :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(118, 262, 100, 23);

        tmpt_kejadian_tunggal.setForeground(new java.awt.Color(0, 0, 0));
        tmpt_kejadian_tunggal.setName("tmpt_kejadian_tunggal"); // NOI18N
        FormInput.add(tmpt_kejadian_tunggal);
        tmpt_kejadian_tunggal.setBounds(223, 262, 310, 23);

        Chktgl_kll_tunggal.setBackground(new java.awt.Color(255, 255, 250));
        Chktgl_kll_tunggal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        Chktgl_kll_tunggal.setForeground(new java.awt.Color(0, 0, 0));
        Chktgl_kll_tunggal.setText("Tgl. Kejadian :");
        Chktgl_kll_tunggal.setBorderPainted(true);
        Chktgl_kll_tunggal.setBorderPaintedFlat(true);
        Chktgl_kll_tunggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Chktgl_kll_tunggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chktgl_kll_tunggal.setName("Chktgl_kll_tunggal"); // NOI18N
        Chktgl_kll_tunggal.setOpaque(false);
        Chktgl_kll_tunggal.setPreferredSize(new java.awt.Dimension(175, 23));
        Chktgl_kll_tunggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chktgl_kll_tunggalActionPerformed(evt);
            }
        });
        FormInput.add(Chktgl_kll_tunggal);
        Chktgl_kll_tunggal.setBounds(535, 262, 90, 23);

        tgl_kejadian_tunggal.setEditable(false);
        tgl_kejadian_tunggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-04-2025" }));
        tgl_kejadian_tunggal.setDisplayFormat("dd-MM-yyyy");
        tgl_kejadian_tunggal.setName("tgl_kejadian_tunggal"); // NOI18N
        tgl_kejadian_tunggal.setOpaque(false);
        tgl_kejadian_tunggal.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(tgl_kejadian_tunggal);
        tgl_kejadian_tunggal.setBounds(630, 262, 90, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Pukul :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(720, 262, 50, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(775, 262, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(827, 262, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(880, 262, 45, 23);

        ChkKll.setBackground(new java.awt.Color(255, 255, 250));
        ChkKll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(ChkKll);
        ChkKll.setForeground(new java.awt.Color(0, 0, 0));
        ChkKll.setText("KLL");
        ChkKll.setBorderPainted(true);
        ChkKll.setBorderPaintedFlat(true);
        ChkKll.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKll.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKll.setName("ChkKll"); // NOI18N
        ChkKll.setOpaque(false);
        ChkKll.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkKll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkKllActionPerformed(evt);
            }
        });
        FormInput.add(ChkKll);
        ChkKll.setBounds(20, 290, 40, 23);

        versus1.setForeground(new java.awt.Color(0, 0, 0));
        versus1.setName("versus1"); // NOI18N
        versus1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                versus1KeyPressed(evt);
            }
        });
        FormInput.add(versus1);
        versus1.setBounds(80, 290, 160, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("VS.");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(242, 290, 30, 23);

        versus2.setForeground(new java.awt.Color(0, 0, 0));
        versus2.setName("versus2"); // NOI18N
        versus2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                versus2KeyPressed(evt);
            }
        });
        FormInput.add(versus2);
        versus2.setBounds(272, 290, 160, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tempat Kejadian :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(433, 290, 100, 23);

        tmpt_kejadian.setForeground(new java.awt.Color(0, 0, 0));
        tmpt_kejadian.setName("tmpt_kejadian"); // NOI18N
        FormInput.add(tmpt_kejadian);
        tmpt_kejadian.setBounds(538, 290, 202, 23);

        Chktgl_kll.setBackground(new java.awt.Color(255, 255, 250));
        Chktgl_kll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        Chktgl_kll.setForeground(new java.awt.Color(0, 0, 0));
        Chktgl_kll.setText("Tgl. Kejadian :");
        Chktgl_kll.setBorderPainted(true);
        Chktgl_kll.setBorderPaintedFlat(true);
        Chktgl_kll.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Chktgl_kll.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chktgl_kll.setName("Chktgl_kll"); // NOI18N
        Chktgl_kll.setOpaque(false);
        Chktgl_kll.setPreferredSize(new java.awt.Dimension(175, 23));
        Chktgl_kll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chktgl_kllActionPerformed(evt);
            }
        });
        FormInput.add(Chktgl_kll);
        Chktgl_kll.setBounds(535, 318, 90, 23);

        tgl_kejadian.setEditable(false);
        tgl_kejadian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-04-2025" }));
        tgl_kejadian.setDisplayFormat("dd-MM-yyyy");
        tgl_kejadian.setName("tgl_kejadian"); // NOI18N
        tgl_kejadian.setOpaque(false);
        tgl_kejadian.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(tgl_kejadian);
        tgl_kejadian.setBounds(630, 318, 90, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Pukul :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(720, 318, 50, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(775, 318, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(827, 318, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(880, 318, 45, 23);

        ChkJatuh.setBackground(new java.awt.Color(255, 255, 250));
        ChkJatuh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(ChkJatuh);
        ChkJatuh.setForeground(new java.awt.Color(0, 0, 0));
        ChkJatuh.setText("Jatuh Dari Ketinggian,");
        ChkJatuh.setBorderPainted(true);
        ChkJatuh.setBorderPaintedFlat(true);
        ChkJatuh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkJatuh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkJatuh.setName("ChkJatuh"); // NOI18N
        ChkJatuh.setOpaque(false);
        ChkJatuh.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkJatuh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJatuhActionPerformed(evt);
            }
        });
        FormInput.add(ChkJatuh);
        ChkJatuh.setBounds(20, 346, 130, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Jelaskan :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(155, 346, 60, 23);

        ket_jatuh.setForeground(new java.awt.Color(0, 0, 0));
        ket_jatuh.setName("ket_jatuh"); // NOI18N
        FormInput.add(ket_jatuh);
        ket_jatuh.setBounds(218, 346, 522, 23);

        ChkLuka.setBackground(new java.awt.Color(255, 255, 250));
        ChkLuka.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(ChkLuka);
        ChkLuka.setForeground(new java.awt.Color(0, 0, 0));
        ChkLuka.setText("Luka Bakar,");
        ChkLuka.setBorderPainted(true);
        ChkLuka.setBorderPaintedFlat(true);
        ChkLuka.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLuka.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLuka.setName("ChkLuka"); // NOI18N
        ChkLuka.setOpaque(false);
        ChkLuka.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkLuka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkLukaActionPerformed(evt);
            }
        });
        FormInput.add(ChkLuka);
        ChkLuka.setBounds(20, 374, 130, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Jelaskan :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(155, 374, 60, 23);

        ket_luka.setForeground(new java.awt.Color(0, 0, 0));
        ket_luka.setName("ket_luka"); // NOI18N
        FormInput.add(ket_luka);
        ket_luka.setBounds(218, 374, 522, 23);

        Chktrauma_listrik.setBackground(new java.awt.Color(255, 255, 250));
        Chktrauma_listrik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(Chktrauma_listrik);
        Chktrauma_listrik.setForeground(new java.awt.Color(0, 0, 0));
        Chktrauma_listrik.setText("Trauma Listrik,");
        Chktrauma_listrik.setBorderPainted(true);
        Chktrauma_listrik.setBorderPaintedFlat(true);
        Chktrauma_listrik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chktrauma_listrik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chktrauma_listrik.setName("Chktrauma_listrik"); // NOI18N
        Chktrauma_listrik.setOpaque(false);
        Chktrauma_listrik.setPreferredSize(new java.awt.Dimension(175, 23));
        Chktrauma_listrik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chktrauma_listrikActionPerformed(evt);
            }
        });
        FormInput.add(Chktrauma_listrik);
        Chktrauma_listrik.setBounds(20, 402, 130, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Jelaskan :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(155, 402, 60, 23);

        ket_trauma_listrik.setForeground(new java.awt.Color(0, 0, 0));
        ket_trauma_listrik.setName("ket_trauma_listrik"); // NOI18N
        FormInput.add(ket_trauma_listrik);
        ket_trauma_listrik.setBounds(218, 402, 522, 23);

        Chktrauma_zat.setBackground(new java.awt.Color(255, 255, 250));
        Chktrauma_zat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(Chktrauma_zat);
        Chktrauma_zat.setForeground(new java.awt.Color(0, 0, 0));
        Chktrauma_zat.setText("Trauma Zat Kimia,");
        Chktrauma_zat.setBorderPainted(true);
        Chktrauma_zat.setBorderPaintedFlat(true);
        Chktrauma_zat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chktrauma_zat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chktrauma_zat.setName("Chktrauma_zat"); // NOI18N
        Chktrauma_zat.setOpaque(false);
        Chktrauma_zat.setPreferredSize(new java.awt.Dimension(175, 23));
        Chktrauma_zat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chktrauma_zatActionPerformed(evt);
            }
        });
        FormInput.add(Chktrauma_zat);
        Chktrauma_zat.setBounds(20, 430, 130, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Jelaskan :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(155, 430, 60, 23);

        ket_trauma_zat.setForeground(new java.awt.Color(0, 0, 0));
        ket_trauma_zat.setName("ket_trauma_zat"); // NOI18N
        FormInput.add(ket_trauma_zat);
        ket_trauma_zat.setBounds(218, 430, 522, 23);

        Chktrauma_lain.setBackground(new java.awt.Color(255, 255, 250));
        Chktrauma_lain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(Chktrauma_lain);
        Chktrauma_lain.setForeground(new java.awt.Color(0, 0, 0));
        Chktrauma_lain.setText("Trauma Lainnya,");
        Chktrauma_lain.setBorderPainted(true);
        Chktrauma_lain.setBorderPaintedFlat(true);
        Chktrauma_lain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chktrauma_lain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chktrauma_lain.setName("Chktrauma_lain"); // NOI18N
        Chktrauma_lain.setOpaque(false);
        Chktrauma_lain.setPreferredSize(new java.awt.Dimension(175, 23));
        Chktrauma_lain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chktrauma_lainActionPerformed(evt);
            }
        });
        FormInput.add(Chktrauma_lain);
        Chktrauma_lain.setBounds(20, 458, 130, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Jelaskan :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(155, 458, 60, 23);

        ket_trauma_lain.setForeground(new java.awt.Color(0, 0, 0));
        ket_trauma_lain.setName("ket_trauma_lain"); // NOI18N
        ket_trauma_lain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ket_trauma_lainKeyPressed(evt);
            }
        });
        FormInput.add(ket_trauma_lain);
        ket_trauma_lain.setBounds(218, 458, 390, 23);

        scrollPane9.setName("scrollPane9"); // NOI18N

        Tkeluhan_utama.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkeluhan_utama.setColumns(20);
        Tkeluhan_utama.setRows(5);
        Tkeluhan_utama.setName("Tkeluhan_utama"); // NOI18N
        Tkeluhan_utama.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tkeluhan_utama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkeluhan_utamaKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(Tkeluhan_utama);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(136, 486, 600, 70);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Keluhan Utama :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(0, 486, 130, 23);

        BtnKeluhan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKeluhan.setMnemonic('2');
        BtnKeluhan.setText("Template");
        BtnKeluhan.setToolTipText("Alt+2");
        BtnKeluhan.setName("BtnKeluhan"); // NOI18N
        BtnKeluhan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnKeluhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluhanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKeluhan);
        BtnKeluhan.setBounds(745, 486, 100, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) :");
        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(20, 560, 400, 23);

        Chkpacs1.setBackground(new java.awt.Color(255, 255, 250));
        Chkpacs1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup2.add(Chkpacs1);
        Chkpacs1.setForeground(new java.awt.Color(0, 0, 0));
        Chkpacs1.setText("PACS 1");
        Chkpacs1.setBorderPainted(true);
        Chkpacs1.setBorderPaintedFlat(true);
        Chkpacs1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chkpacs1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chkpacs1.setName("Chkpacs1"); // NOI18N
        Chkpacs1.setOpaque(false);
        Chkpacs1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(Chkpacs1);
        Chkpacs1.setBounds(20, 585, 80, 23);

        Chkpacs2.setBackground(new java.awt.Color(255, 255, 250));
        Chkpacs2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup2.add(Chkpacs2);
        Chkpacs2.setForeground(new java.awt.Color(0, 0, 0));
        Chkpacs2.setText("PACS 2");
        Chkpacs2.setBorderPainted(true);
        Chkpacs2.setBorderPaintedFlat(true);
        Chkpacs2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chkpacs2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chkpacs2.setName("Chkpacs2"); // NOI18N
        Chkpacs2.setOpaque(false);
        Chkpacs2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(Chkpacs2);
        Chkpacs2.setBounds(110, 585, 80, 23);

        Chkpacs3.setBackground(new java.awt.Color(255, 255, 250));
        Chkpacs3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup2.add(Chkpacs3);
        Chkpacs3.setForeground(new java.awt.Color(0, 0, 0));
        Chkpacs3.setText("PACS 3");
        Chkpacs3.setBorderPainted(true);
        Chkpacs3.setBorderPaintedFlat(true);
        Chkpacs3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chkpacs3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chkpacs3.setName("Chkpacs3"); // NOI18N
        Chkpacs3.setOpaque(false);
        Chkpacs3.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(Chkpacs3);
        Chkpacs3.setBounds(200, 585, 80, 23);

        Chkpacs4.setBackground(new java.awt.Color(255, 255, 250));
        Chkpacs4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup2.add(Chkpacs4);
        Chkpacs4.setForeground(new java.awt.Color(0, 0, 0));
        Chkpacs4.setText("PACS 4");
        Chkpacs4.setBorderPainted(true);
        Chkpacs4.setBorderPaintedFlat(true);
        Chkpacs4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chkpacs4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chkpacs4.setName("Chkpacs4"); // NOI18N
        Chkpacs4.setOpaque(false);
        Chkpacs4.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(Chkpacs4);
        Chkpacs4.setBounds(290, 585, 80, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("TANDA VITAL :");
        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(0, 613, 130, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("ANTROPOMETRI : ");
        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(263, 613, 100, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Kesadaran :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(0, 641, 130, 23);

        cmbKesadaran.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sadar Penuh", "Respon Suara", "Respon Nyeri", "Tidak Respon" }));
        cmbKesadaran.setName("cmbKesadaran"); // NOI18N
        cmbKesadaran.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbKesadaran);
        cmbKesadaran.setBounds(136, 641, 110, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Berat Badan :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(270, 641, 80, 23);

        bb.setBackground(new java.awt.Color(245, 250, 240));
        bb.setForeground(new java.awt.Color(0, 0, 0));
        bb.setName("bb"); // NOI18N
        bb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bbKeyPressed(evt);
            }
        });
        FormInput.add(bb);
        bb.setBounds(357, 641, 70, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("Kg.");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(432, 641, 30, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Tinggi Badan :");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(490, 641, 80, 23);

        tb.setBackground(new java.awt.Color(245, 250, 240));
        tb.setForeground(new java.awt.Color(0, 0, 0));
        tb.setName("tb"); // NOI18N
        FormInput.add(tb);
        tb.setBounds(575, 641, 70, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("Cm.");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(650, 641, 50, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("ICD 10 :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(610, 458, 50, 23);

        Ticd10.setForeground(new java.awt.Color(0, 0, 0));
        Ticd10.setName("Ticd10"); // NOI18N
        FormInput.add(Ticd10);
        Ticd10.setBounds(665, 458, 75, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Tekanan Darah :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(0, 669, 130, 23);

        tkann_darah.setBackground(new java.awt.Color(245, 250, 240));
        tkann_darah.setForeground(new java.awt.Color(0, 0, 0));
        tkann_darah.setName("tkann_darah"); // NOI18N
        tkann_darah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tkann_darahKeyPressed(evt);
            }
        });
        FormInput.add(tkann_darah);
        tkann_darah.setBounds(136, 669, 70, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("mmHg");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(212, 669, 50, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Pernapasan :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(270, 669, 80, 23);

        pernapasan.setBackground(new java.awt.Color(245, 250, 240));
        pernapasan.setForeground(new java.awt.Color(0, 0, 0));
        pernapasan.setName("pernapasan"); // NOI18N
        pernapasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pernapasanKeyPressed(evt);
            }
        });
        FormInput.add(pernapasan);
        pernapasan.setBounds(357, 669, 70, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("x/menit");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(432, 669, 50, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Saturasi O2 :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(490, 669, 80, 23);

        saturasi.setBackground(new java.awt.Color(245, 250, 240));
        saturasi.setForeground(new java.awt.Color(0, 0, 0));
        saturasi.setName("saturasi"); // NOI18N
        saturasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                saturasiKeyPressed(evt);
            }
        });
        FormInput.add(saturasi);
        saturasi.setBounds(575, 669, 70, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("%");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(650, 669, 50, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Nadi :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(0, 697, 130, 23);

        nadi.setBackground(new java.awt.Color(245, 250, 240));
        nadi.setForeground(new java.awt.Color(0, 0, 0));
        nadi.setName("nadi"); // NOI18N
        nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nadiKeyPressed(evt);
            }
        });
        FormInput.add(nadi);
        nadi.setBounds(136, 697, 70, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("x/menit");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(212, 697, 50, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Temperatur :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(270, 697, 80, 23);

        temperatur.setBackground(new java.awt.Color(245, 250, 240));
        temperatur.setForeground(new java.awt.Color(0, 0, 0));
        temperatur.setName("temperatur"); // NOI18N
        temperatur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                temperaturKeyPressed(evt);
            }
        });
        FormInput.add(temperatur);
        temperatur.setBounds(357, 697, 70, 23);

        nadi1.setForeground(new java.awt.Color(0, 0, 0));
        nadi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nadi1.setText("°C");
        nadi1.setName("nadi1"); // NOI18N
        FormInput.add(nadi1);
        nadi1.setBounds(432, 697, 50, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Nyeri :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(490, 697, 80, 23);

        Tnyeri.setBackground(new java.awt.Color(245, 250, 240));
        Tnyeri.setForeground(new java.awt.Color(0, 0, 0));
        Tnyeri.setName("Tnyeri"); // NOI18N
        Tnyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnyeriKeyPressed(evt);
            }
        });
        FormInput.add(Tnyeri);
        Tnyeri.setBounds(575, 697, 170, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("WORTHING PHYSIOLOGICAL SCORING SYSTEM (WPSS) :");
        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(20, 725, 530, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("TANDA VITAL");
        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(0, 745, 150, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("SKOR 0");
        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(165, 745, 65, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("SKOR 1");
        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(265, 745, 65, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("SKOR 2");
        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(355, 745, 65, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("SKOR 3");
        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(441, 745, 65, 23);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(155, 745, 1, 204);

        jSeparator15.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator15.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator15.setName("jSeparator15"); // NOI18N
        FormInput.add(jSeparator15);
        jSeparator15.setBounds(255, 745, 1, 204);

        jSeparator16.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator16.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator16.setName("jSeparator16"); // NOI18N
        FormInput.add(jSeparator16);
        jSeparator16.setBounds(345, 745, 1, 204);

        jSeparator17.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator17.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator17.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator17.setName("jSeparator17"); // NOI18N
        FormInput.add(jSeparator17);
        jSeparator17.setBounds(431, 745, 1, 204);

        jSeparator7.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator7.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput.add(jSeparator7);
        jSeparator7.setBounds(15, 770, 540, 1);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("KESADARAN");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(0, 774, 150, 23);

        Chksadar_skor0.setBackground(new java.awt.Color(255, 255, 250));
        Chksadar_skor0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroupKesadaran.add(Chksadar_skor0);
        Chksadar_skor0.setForeground(new java.awt.Color(0, 0, 0));
        Chksadar_skor0.setText("Sadar Penuh");
        Chksadar_skor0.setBorderPainted(true);
        Chksadar_skor0.setBorderPaintedFlat(true);
        Chksadar_skor0.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chksadar_skor0.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chksadar_skor0.setName("Chksadar_skor0"); // NOI18N
        Chksadar_skor0.setOpaque(false);
        Chksadar_skor0.setPreferredSize(new java.awt.Dimension(175, 23));
        Chksadar_skor0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chksadar_skor0ActionPerformed(evt);
            }
        });
        FormInput.add(Chksadar_skor0);
        Chksadar_skor0.setBounds(165, 774, 90, 23);

        Chksadar_skor3.setBackground(new java.awt.Color(255, 255, 250));
        Chksadar_skor3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroupKesadaran.add(Chksadar_skor3);
        Chksadar_skor3.setForeground(new java.awt.Color(0, 0, 0));
        Chksadar_skor3.setText("Selain Sadar Penuh");
        Chksadar_skor3.setBorderPainted(true);
        Chksadar_skor3.setBorderPaintedFlat(true);
        Chksadar_skor3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chksadar_skor3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chksadar_skor3.setName("Chksadar_skor3"); // NOI18N
        Chksadar_skor3.setOpaque(false);
        Chksadar_skor3.setPreferredSize(new java.awt.Dimension(175, 23));
        Chksadar_skor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chksadar_skor3ActionPerformed(evt);
            }
        });
        FormInput.add(Chksadar_skor3);
        Chksadar_skor3.setBounds(441, 774, 130, 23);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(15, 800, 540, 1);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("TEKANAN DARAH SISTOLIK");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(0, 805, 150, 23);

        Chk100.setBackground(new java.awt.Color(255, 255, 250));
        Chk100.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroupTekanan.add(Chk100);
        Chk100.setForeground(new java.awt.Color(0, 0, 0));
        Chk100.setText(">= 100");
        Chk100.setBorderPainted(true);
        Chk100.setBorderPaintedFlat(true);
        Chk100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chk100.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chk100.setName("Chk100"); // NOI18N
        Chk100.setOpaque(false);
        Chk100.setPreferredSize(new java.awt.Dimension(175, 23));
        Chk100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk100ActionPerformed(evt);
            }
        });
        FormInput.add(Chk100);
        Chk100.setBounds(165, 805, 90, 23);

        Chk99.setBackground(new java.awt.Color(255, 255, 250));
        Chk99.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroupTekanan.add(Chk99);
        Chk99.setForeground(new java.awt.Color(0, 0, 0));
        Chk99.setText("<= 99");
        Chk99.setBorderPainted(true);
        Chk99.setBorderPaintedFlat(true);
        Chk99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chk99.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chk99.setName("Chk99"); // NOI18N
        Chk99.setOpaque(false);
        Chk99.setPreferredSize(new java.awt.Dimension(175, 23));
        Chk99.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk99ActionPerformed(evt);
            }
        });
        FormInput.add(Chk99);
        Chk99.setBounds(355, 805, 65, 23);

        jSeparator9.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator9.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(15, 830, 540, 1);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("NADI");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(0, 835, 150, 23);

        Chk101.setBackground(new java.awt.Color(255, 255, 250));
        Chk101.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroupNadi.add(Chk101);
        Chk101.setForeground(new java.awt.Color(0, 0, 0));
        Chk101.setText("<= 101");
        Chk101.setBorderPainted(true);
        Chk101.setBorderPaintedFlat(true);
        Chk101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chk101.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chk101.setName("Chk101"); // NOI18N
        Chk101.setOpaque(false);
        Chk101.setPreferredSize(new java.awt.Dimension(175, 23));
        Chk101.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk101ActionPerformed(evt);
            }
        });
        FormInput.add(Chk101);
        Chk101.setBounds(165, 835, 90, 23);

        Chk102.setBackground(new java.awt.Color(255, 255, 250));
        Chk102.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroupNadi.add(Chk102);
        Chk102.setForeground(new java.awt.Color(0, 0, 0));
        Chk102.setText(">= 102");
        Chk102.setBorderPainted(true);
        Chk102.setBorderPaintedFlat(true);
        Chk102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chk102.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chk102.setName("Chk102"); // NOI18N
        Chk102.setOpaque(false);
        Chk102.setPreferredSize(new java.awt.Dimension(175, 23));
        Chk102.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk102ActionPerformed(evt);
            }
        });
        FormInput.add(Chk102);
        Chk102.setBounds(265, 835, 65, 23);

        jSeparator10.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator10.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(15, 860, 540, 1);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("RESPIRASI");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(0, 865, 150, 23);

        Chk19.setBackground(new java.awt.Color(255, 255, 250));
        Chk19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroupRespirasi.add(Chk19);
        Chk19.setForeground(new java.awt.Color(0, 0, 0));
        Chk19.setText("<= 19");
        Chk19.setBorderPainted(true);
        Chk19.setBorderPaintedFlat(true);
        Chk19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chk19.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chk19.setName("Chk19"); // NOI18N
        Chk19.setOpaque(false);
        Chk19.setPreferredSize(new java.awt.Dimension(175, 23));
        Chk19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk19ActionPerformed(evt);
            }
        });
        FormInput.add(Chk19);
        Chk19.setBounds(165, 865, 90, 23);

        Chk20.setBackground(new java.awt.Color(255, 255, 250));
        Chk20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroupRespirasi.add(Chk20);
        Chk20.setForeground(new java.awt.Color(0, 0, 0));
        Chk20.setText("20 - 21");
        Chk20.setBorderPainted(true);
        Chk20.setBorderPaintedFlat(true);
        Chk20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chk20.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chk20.setName("Chk20"); // NOI18N
        Chk20.setOpaque(false);
        Chk20.setPreferredSize(new java.awt.Dimension(175, 23));
        Chk20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk20ActionPerformed(evt);
            }
        });
        FormInput.add(Chk20);
        Chk20.setBounds(265, 865, 65, 23);

        Chk22.setBackground(new java.awt.Color(255, 255, 250));
        Chk22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroupRespirasi.add(Chk22);
        Chk22.setForeground(new java.awt.Color(0, 0, 0));
        Chk22.setText(">= 22");
        Chk22.setBorderPainted(true);
        Chk22.setBorderPaintedFlat(true);
        Chk22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chk22.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chk22.setName("Chk22"); // NOI18N
        Chk22.setOpaque(false);
        Chk22.setPreferredSize(new java.awt.Dimension(175, 23));
        Chk22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk22ActionPerformed(evt);
            }
        });
        FormInput.add(Chk22);
        Chk22.setBounds(355, 865, 65, 23);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(15, 890, 540, 1);

        Chk35_skor3.setBackground(new java.awt.Color(255, 255, 250));
        Chk35_skor3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroupTemperatur.add(Chk35_skor3);
        Chk35_skor3.setForeground(new java.awt.Color(0, 0, 0));
        Chk35_skor3.setText("< 35,3");
        Chk35_skor3.setBorderPainted(true);
        Chk35_skor3.setBorderPaintedFlat(true);
        Chk35_skor3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chk35_skor3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chk35_skor3.setName("Chk35_skor3"); // NOI18N
        Chk35_skor3.setOpaque(false);
        Chk35_skor3.setPreferredSize(new java.awt.Dimension(175, 23));
        Chk35_skor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk35_skor3ActionPerformed(evt);
            }
        });
        FormInput.add(Chk35_skor3);
        Chk35_skor3.setBounds(441, 895, 60, 23);

        Chk35_skor0.setBackground(new java.awt.Color(255, 255, 250));
        Chk35_skor0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroupTemperatur.add(Chk35_skor0);
        Chk35_skor0.setForeground(new java.awt.Color(0, 0, 0));
        Chk35_skor0.setText(">= 35,3");
        Chk35_skor0.setBorderPainted(true);
        Chk35_skor0.setBorderPaintedFlat(true);
        Chk35_skor0.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chk35_skor0.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chk35_skor0.setName("Chk35_skor0"); // NOI18N
        Chk35_skor0.setOpaque(false);
        Chk35_skor0.setPreferredSize(new java.awt.Dimension(175, 23));
        Chk35_skor0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk35_skor0ActionPerformed(evt);
            }
        });
        FormInput.add(Chk35_skor0);
        Chk35_skor0.setBounds(165, 895, 90, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("TEMPERATUR");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(0, 895, 150, 23);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(15, 920, 540, 1);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("SATURASI O2");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(0, 925, 150, 23);

        Chk96.setBackground(new java.awt.Color(255, 255, 250));
        Chk96.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroupSaturasi.add(Chk96);
        Chk96.setForeground(new java.awt.Color(0, 0, 0));
        Chk96.setText("96 - 100");
        Chk96.setBorderPainted(true);
        Chk96.setBorderPaintedFlat(true);
        Chk96.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chk96.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chk96.setName("Chk96"); // NOI18N
        Chk96.setOpaque(false);
        Chk96.setPreferredSize(new java.awt.Dimension(175, 23));
        Chk96.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk96ActionPerformed(evt);
            }
        });
        FormInput.add(Chk96);
        Chk96.setBounds(165, 925, 90, 23);

        Chk94.setBackground(new java.awt.Color(255, 255, 250));
        Chk94.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroupSaturasi.add(Chk94);
        Chk94.setForeground(new java.awt.Color(0, 0, 0));
        Chk94.setText("94 - 95");
        Chk94.setBorderPainted(true);
        Chk94.setBorderPaintedFlat(true);
        Chk94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chk94.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chk94.setName("Chk94"); // NOI18N
        Chk94.setOpaque(false);
        Chk94.setPreferredSize(new java.awt.Dimension(175, 23));
        Chk94.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk94ActionPerformed(evt);
            }
        });
        FormInput.add(Chk94);
        Chk94.setBounds(265, 925, 65, 23);

        Chk92.setBackground(new java.awt.Color(255, 255, 250));
        Chk92.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroupSaturasi.add(Chk92);
        Chk92.setForeground(new java.awt.Color(0, 0, 0));
        Chk92.setText("92 - 93");
        Chk92.setBorderPainted(true);
        Chk92.setBorderPaintedFlat(true);
        Chk92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chk92.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chk92.setName("Chk92"); // NOI18N
        Chk92.setOpaque(false);
        Chk92.setPreferredSize(new java.awt.Dimension(175, 23));
        Chk92.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk92ActionPerformed(evt);
            }
        });
        FormInput.add(Chk92);
        Chk92.setBounds(355, 925, 65, 23);

        Chk92_skor3.setBackground(new java.awt.Color(255, 255, 250));
        Chk92_skor3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroupSaturasi.add(Chk92_skor3);
        Chk92_skor3.setForeground(new java.awt.Color(0, 0, 0));
        Chk92_skor3.setText("< 92");
        Chk92_skor3.setBorderPainted(true);
        Chk92_skor3.setBorderPaintedFlat(true);
        Chk92_skor3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chk92_skor3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chk92_skor3.setName("Chk92_skor3"); // NOI18N
        Chk92_skor3.setOpaque(false);
        Chk92_skor3.setPreferredSize(new java.awt.Dimension(175, 23));
        Chk92_skor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk92_skor3ActionPerformed(evt);
            }
        });
        FormInput.add(Chk92_skor3);
        Chk92_skor3.setBounds(441, 925, 60, 23);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(15, 950, 540, 1);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("TOTAL SKOR");
        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(0, 955, 150, 23);

        label_5.setBackground(new java.awt.Color(255, 0, 51));
        label_5.setForeground(new java.awt.Color(255, 255, 255));
        label_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_5.setText(">= 5");
        label_5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        label_5.setIconTextGap(12);
        label_5.setName("label_5"); // NOI18N
        label_5.setOpaque(true);
        FormInput.add(label_5);
        label_5.setBounds(165, 955, 90, 23);

        label_24.setBackground(new java.awt.Color(253, 217, 0));
        label_24.setForeground(new java.awt.Color(0, 0, 0));
        label_24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_24.setText("2 - 4");
        label_24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_24.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        label_24.setIconTextGap(12);
        label_24.setName("label_24"); // NOI18N
        label_24.setOpaque(true);
        FormInput.add(label_24);
        label_24.setBounds(265, 955, 90, 23);

        label_01.setBackground(new java.awt.Color(0, 153, 51));
        label_01.setForeground(new java.awt.Color(255, 255, 255));
        label_01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_01.setText("0 - 1");
        label_01.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_01.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        label_01.setIconTextGap(12);
        label_01.setName("label_01"); // NOI18N
        label_01.setOpaque(true);
        FormInput.add(label_01);
        label_01.setBounds(366, 955, 90, 23);

        scrollPane10.setName("scrollPane10"); // NOI18N

        Tcttn_khusus.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tcttn_khusus.setColumns(20);
        Tcttn_khusus.setRows(5);
        Tcttn_khusus.setName("Tcttn_khusus"); // NOI18N
        Tcttn_khusus.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tcttn_khusus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tcttn_khususKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(Tcttn_khusus);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(136, 986, 420, 160);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("CATATAN KHUSUS :");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(0, 986, 130, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("KEPUTUSAN :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(560, 986, 90, 23);

        Tkeputusan.setForeground(new java.awt.Color(0, 0, 0));
        Tkeputusan.setName("Tkeputusan"); // NOI18N
        FormInput.add(Tkeputusan);
        Tkeputusan.setBounds(655, 986, 290, 23);

        cmbJam3.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam3.setName("cmbJam3"); // NOI18N
        cmbJam3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam3MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam3);
        cmbJam3.setBounds(655, 1014, 45, 23);

        cmbMnt3.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt3.setName("cmbMnt3"); // NOI18N
        cmbMnt3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt3MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt3);
        cmbMnt3.setBounds(707, 1014, 45, 23);

        cmbDtk3.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk3.setName("cmbDtk3"); // NOI18N
        cmbDtk3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk3MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk3);
        cmbDtk3.setBounds(760, 1014, 45, 23);

        Chkresus.setBackground(new java.awt.Color(255, 0, 51));
        Chkresus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup3.add(Chkresus);
        Chkresus.setForeground(new java.awt.Color(255, 255, 255));
        Chkresus.setText("Ruang Resusitasi");
        Chkresus.setBorderPainted(true);
        Chkresus.setBorderPaintedFlat(true);
        Chkresus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Chkresus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chkresus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chkresus.setIconTextGap(6);
        Chkresus.setName("Chkresus"); // NOI18N
        Chkresus.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(Chkresus);
        Chkresus.setBounds(655, 1042, 200, 23);

        Chknon_resus.setBackground(new java.awt.Color(253, 217, 0));
        Chknon_resus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup3.add(Chknon_resus);
        Chknon_resus.setForeground(new java.awt.Color(0, 0, 0));
        Chknon_resus.setText("Ruang Non Resusitasi");
        Chknon_resus.setBorderPainted(true);
        Chknon_resus.setBorderPaintedFlat(true);
        Chknon_resus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Chknon_resus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chknon_resus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chknon_resus.setIconTextGap(6);
        Chknon_resus.setName("Chknon_resus"); // NOI18N
        Chknon_resus.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(Chknon_resus);
        Chknon_resus.setBounds(655, 1070, 200, 23);

        Chkklinik.setBackground(new java.awt.Color(0, 153, 51));
        Chkklinik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup3.add(Chkklinik);
        Chkklinik.setForeground(new java.awt.Color(255, 255, 255));
        Chkklinik.setText("Klinik Umum 24 Jam");
        Chkklinik.setBorderPainted(true);
        Chkklinik.setBorderPaintedFlat(true);
        Chkklinik.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Chkklinik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chkklinik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chkklinik.setIconTextGap(6);
        Chkklinik.setName("Chkklinik"); // NOI18N
        Chkklinik.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(Chkklinik);
        Chkklinik.setBounds(655, 1098, 200, 23);

        Chkdoa.setBackground(new java.awt.Color(204, 204, 204));
        Chkdoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup3.add(Chkdoa);
        Chkdoa.setForeground(new java.awt.Color(0, 0, 0));
        Chkdoa.setText("DOA (Death On Arrival)");
        Chkdoa.setBorderPainted(true);
        Chkdoa.setBorderPaintedFlat(true);
        Chkdoa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Chkdoa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Chkdoa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chkdoa.setIconTextGap(6);
        Chkdoa.setName("Chkdoa"); // NOI18N
        Chkdoa.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(Chkdoa);
        Chkdoa.setBounds(655, 1126, 200, 23);

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
        BtnCatatan.setBounds(26, 1010, 100, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Pukul :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(560, 1014, 90, 23);

        ScrollTriase1.setViewportView(FormInput);

        FormTriase.add(ScrollTriase1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Triase", FormTriase);

        internalFrame4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbTriase.setAutoCreateRowSorter(true);
        tbTriase.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTriase.setComponentPopupMenu(jPopupMenu1);
        tbTriase.setName("tbTriase"); // NOI18N
        tbTriase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTriaseMouseClicked(evt);
            }
        });
        tbTriase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTriaseKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbTriase);

        internalFrame4.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl.Triase :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-04-2025" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-04-2025" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+3");
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
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        internalFrame4.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Triase", internalFrame4);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

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
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (nip.equals("")) {
            Valid.textKosong(nm_petugas, "Petugas Triase");
            btnPetugas.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from triase_ponek where no_rawat='" + TNoRw.getText() + "'") == 0) {
                cekData();
                hitungSkor();
                if (Sequel.menyimpantf("triase_ponek", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 75, new String[]{
                            TNoRw.getText(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            cmbCaraMasuk.getSelectedItem().toString(), sdh_terpasang.getText(), cmbAlasanKedatangan.getSelectedItem().toString(), rujukan_dari.getText(),
                            dijemput_oleh.getText(), cmbKendaraan.getSelectedItem().toString(), bkn_ambulan.getText(), nm_pengantar.getText(), tlpn_pengantar.getText(),
                            cmbKasus.getSelectedItem().toString(), kll_tunggal, tmpt_kejadian_tunggal.getText(), tgl_kejadian_tggl, Valid.SetTgl(tgl_kejadian_tunggal.getSelectedItem() + ""),
                            cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), kll, versus1.getText(), versus2.getText(), tmpt_kejadian.getText(),
                            tgl_kejadian_kll, Valid.SetTgl(tgl_kejadian.getSelectedItem() + ""), cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(),
                            jatuh, ket_jatuh.getText(), luka, ket_luka.getText(), trauma_listrik, ket_trauma_listrik.getText(), trauma_zat, ket_trauma_zat.getText(), trauma_lain,
                            ket_trauma_lain.getText(), Ticd10.getText(), Tkeluhan_utama.getText(), pacs1, pacs2, pacs3, pacs4, cmbKesadaran.getSelectedItem().toString(), tkann_darah.getText(),
                            nadi.getText(), pernapasan.getText(), temperatur.getText(), saturasi.getText(), Tnyeri.getText(), bb.getText(), tb.getText(), skor0_sadar, skor0_100, skor0_101,
                            skor0_19, skor0_35, skor0_96, skor102, skor20, skor94, skor99, skor22, skor92, skor3_sadar, skor3_35, skor3_92, skortotal, Tcttn_khusus.getText(), Tkeputusan.getText(),
                            cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem(), resus, nonresus, klinik, doa, nip, Sequel.cariIsi("select now()")
                        }) == true) {

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Triase Ponek", "Simpan");
                    TCari.setText(TNoRw.getText());
                    BtnBatalActionPerformed(null);
                    TabRawat.setSelectedIndex(1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data triase pasien ini sudah tersimpan di triase ponek...!!");
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

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
        if (tbTriase.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapus();
            } else {
                if (tbTriase.getValueAt(tbTriase.getSelectedRow(), 67).toString().equals(akses.getkode())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh Petugas Triase yang bersangkutan..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbTriase.requestFocus();
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (nip.equals("")) {
            Valid.textKosong(nm_petugas, "Petugas Triase");
            btnPetugas.requestFocus();
        } else {
            if (tbTriase.getSelectedRow() > -1) {
                if (akses.getadmin() == true) {
                    ganti();
                } else {
                    if (tbTriase.getValueAt(tbTriase.getSelectedRow(), 67).toString().equals(akses.getkode())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh Petugas Triase yang bersangkutan..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbTriase.requestFocus();
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
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbTriase.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            
            if (cmbAlasanKedatangan.getSelectedIndex() == 0) {
                param.put("alasan_kedatangan", "-");
            } else if (cmbAlasanKedatangan.getSelectedIndex() == 1 || cmbAlasanKedatangan.getSelectedIndex() == 2) {
                param.put("alasan_kedatangan", cmbAlasanKedatangan.getSelectedItem().toString());
            } else if (cmbAlasanKedatangan.getSelectedIndex() == 3) {
                param.put("alasan_kedatangan", "Rujukan, dari : " + rujukan_dari.getText());
            } else if (cmbAlasanKedatangan.getSelectedIndex() == 4) {
                param.put("alasan_kedatangan", "Dijemput oleh : " + dijemput_oleh.getText());
            }
            
            if (cmbKendaraan.getSelectedIndex() == 0) {
                param.put("kendaraan", "-");
            } else if (cmbKendaraan.getSelectedIndex() == 1) {
                param.put("kendaraan", cmbKendaraan.getSelectedItem().toString());
            } else if (cmbKendaraan.getSelectedIndex() == 2) {
                param.put("kendaraan", "Kendaraan bukan ambulance, jelaskan : " + bkn_ambulan.getText());
            }
            
            if (ChkKll_tunggal.isSelected() == true) {
                param.put("kll_tunggal", "KLL Tunggal Tempat Kejadian " + tmpt_kejadian_tunggal.getText() + " Tanggal Kejadian "
                        + Sequel.cariIsi("select date_format(kll_tunggal_tanggal,'%d-%m-%Y    Pukul : %H:%i') from triase_igd "
                                + "where no_rawat='" + tbTriase.getValueAt(tbTriase.getSelectedRow(), 0).toString() + "'"));
            } else {
                param.put("kll_tunggal", "KLL Tunggal");
            }
            
            if (ChkKll.isSelected() == true) {
                param.put("kll", "KLL " + versus1.getText() + " Vs. " + versus2.getText() + " Tempat Kejadian " + tmpt_kejadian.getText() + " Tanggal Kejadian "
                        + Sequel.cariIsi("select date_format(kll_tanggal,'%d-%m-%Y    Pukul : %H:%i') from triase_igd "
                                + "where no_rawat='" + tbTriase.getValueAt(tbTriase.getSelectedRow(), 0).toString() + "'"));
            } else {
                param.put("kll", "KLL");
            }
            
            if (ChkJatuh.isSelected() == true) {
                param.put("jatuh", "Jatuh dari ketinggian, Jelaskan : " + ket_jatuh.getText());
            } else {
                param.put("jatuh", "Jatuh dari ketinggian,");
            }
            
            if (ChkLuka.isSelected() == true) {
                param.put("luka", "Luka bakar, Jelaskan : " + ket_luka.getText());
            } else {
                param.put("luka", "Luka bakar,");
            }
            
            if (Chktrauma_listrik.isSelected() == true) {
                param.put("trauma_listrik", "Trauma listrik, Jelaskan : " + ket_trauma_listrik.getText());
            } else {
                param.put("trauma_listrik", "Trauma listrik,");
            }
            
            if (Chktrauma_zat.isSelected() == true) {
                param.put("trauma_zat", "Trauma zat kimia, Jelaskan : " + ket_trauma_zat.getText());
            } else {
                param.put("trauma_zat", "Trauma zat kimia,");
            }
            
            if (Chktrauma_lain.isSelected() == true) {
                param.put("trauma_lain", "Trauma lainnya (" + ket_trauma_lain.getText() + ")");
            } else {
                param.put("trauma_lain", "Trauma lainnya");
            }
            
            if (Chkpacs1.isSelected() == true) {
                param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 1");
            } else if (Chkpacs2.isSelected() == true) {
                param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 2");
            } else if (Chkpacs3.isSelected() == true) {
                param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 3");
            } else if (Chkpacs4.isSelected() == true) {
                param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 4");
            } else {
                param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : -");
            }
            
            if (totskor >= 5) {
                param.put("total5", "V");
                param.put("total24", "");
                param.put("total01", "");
            } else if (totskor >= 2 && totskor <= 4) {
                param.put("total5", "");
                param.put("total24", "V");
                param.put("total01", "");
            } else if (totskor >= 0 && totskor <= 1) {
                param.put("total5", "");
                param.put("total24", "");
                param.put("total01", "V");
            } else {
                param.put("total5", "");
                param.put("total24", "");
                param.put("total01", "");
            }
                        
            Valid.MyReport("rptTriaseIGD.jasper", "report", "::[ Laporan Data Triase IGD ]::",
                    "SELECT ti.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, date_format(ti.tanggal,'Tanggal : %d-%m-%Y    Pukul : %H:%i') kontak_awal, "
                    + "if(ti.cara_masuk='','-',ti.cara_masuk) cr_msk, ti.sudah_terpasang, concat('Nama : ',ti.nm_pengantar,'    No. Telp : ',ti.telp_pengantar) iden_pengntar, "
                    + "ti.kasus, ti.keluhan_utama, if(ti.kesadaran='','KESADARAN : -',concat('KESADARAN : ',ti.kesadaran)) kesadaran, ti.td, ti.nadi, ti.napas, ti.temperatur, "
                    + "ti.saturasi, ti.nyeri, ti.vas, if(ti.skor0_sadar_penuh='ya','V','') skor0_sadar, if(ti.skor0_100='ya','V','') skor0_100, if(ti.skor0_101='ya','V','') skor0_101, "
                    + "if(ti.skor0_19='ya','V','') skor0_19, if(ti.skor0_35_3='ya','V','') skor0_35, if(ti.skor0_96_100='ya','V','') skor0_96, if(ti.skor1_102='ya','V','') skor1_102, "
                    + "if(ti.skor1_20_21='ya','V','') skor1_20, if(ti.skor1_94_95='ya','V','') skor1_94, if(ti.skor2_99='ya','V','') skor2_99, if(ti.skor2_22='ya','V','') skor2_22, "
                    + "if(ti.skor2_92_93='ya','V','') skor2_92, if(ti.skor3_selain='ya','V','') skor3_selain, if(ti.skor3_35_3='ya','V','') skor3_35, if(ti.skor3_92='ya','V','') skor3_92, "
                    + "ti.catatan, ti.pukul, if(ti.triase_resusitasi='ya','V','') resus, if(ti.triase_non_resusitasi='ya','V','') nonresus, if(ti.triase_klinik='ya','V','') klinik, "
                    + "if(ti.triase_doa='ya','V','') doa, pg.nama petgas, if(ti.kll_tunggal='ya','V','') kll_tunggal, if(ti.kll_versus='ya','V','') kll_versus, if(ti.jatuh='ya','V','') jatuh, "
                    + "if(ti.luka_bakar='ya','V','') luka, if(ti.trauma_listrik='ya','V','') trauma_listrik, if(ti.trauma_zat_kimia='ya','V','') trauma_zat, if(ti.trauma_lain='ya','V','') trauma_lain, "
                    + "ti.bb, ti.tb from triase_igd ti inner join reg_periksa rp on rp.no_rawat=ti.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg on nik=ti.nip_petugas where ti.no_rawat='" + tbTriase.getValueAt(tbTriase.getSelectedRow(), 0).toString() + "'", param);
            
            BtnBatalActionPerformed(null);
            TabRawat.setSelectedIndex(1);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbTriase.requestFocus();
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        
        if (Sequel.cariInteger("select count(-1) from triase_ponek where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from triase_ponek where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
            sdh_terpasang.requestFocus();
        }
    }//GEN-LAST:event_formWindowOpened

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void tbTriaseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTriaseKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTriaseKeyPressed

    private void tbTriaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriaseMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {                
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbTriase.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tbTriaseMouseClicked

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

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMTriaseIGD");
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
            akses.setform("RMTriaseIGD");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMTriaseIGD");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void sdh_terpasangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sdh_terpasangKeyPressed
        Valid.pindah(evt, sdh_terpasang, cmbCaraMasuk);
    }//GEN-LAST:event_sdh_terpasangKeyPressed

    private void cmbAlasanKedatanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAlasanKedatanganActionPerformed
        rujukan_dari.setText("");
        dijemput_oleh.setText("");

        if (cmbAlasanKedatangan.getSelectedIndex() == 3) {            
            rujukan_dari.setEnabled(true);
            rujukan_dari.requestFocus();
            dijemput_oleh.setEnabled(false);
        } else if (cmbAlasanKedatangan.getSelectedIndex() == 4) {
            rujukan_dari.setEnabled(false);
            dijemput_oleh.setEnabled(true);
            dijemput_oleh.requestFocus();
        } else {
            rujukan_dari.setEnabled(false);
            dijemput_oleh.setEnabled(false);
        }
    }//GEN-LAST:event_cmbAlasanKedatanganActionPerformed

    private void rujukan_dariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rujukan_dariKeyPressed
        Valid.pindah(evt, cmbAlasanKedatangan, cmbKendaraan);
    }//GEN-LAST:event_rujukan_dariKeyPressed

    private void dijemput_olehKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dijemput_olehKeyPressed
        Valid.pindah(evt, cmbAlasanKedatangan, cmbKendaraan);
    }//GEN-LAST:event_dijemput_olehKeyPressed

    private void cmbKendaraanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKendaraanActionPerformed
        bkn_ambulan.setText("");
        if (cmbKendaraan.getSelectedIndex() == 2) {            
            bkn_ambulan.setEnabled(true);
            bkn_ambulan.requestFocus();
        } else {            
            bkn_ambulan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbKendaraanActionPerformed

    private void bkn_ambulanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bkn_ambulanKeyPressed
        Valid.pindah(evt, cmbKendaraan, nm_pengantar);
    }//GEN-LAST:event_bkn_ambulanKeyPressed

    private void nm_pengantarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nm_pengantarKeyPressed
        Valid.pindah(evt, bkn_ambulan, tlpn_pengantar);
    }//GEN-LAST:event_nm_pengantarKeyPressed

    private void tlpn_pengantarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tlpn_pengantarKeyPressed
        Valid.pindah(evt, nm_pengantar, btnPetugas);
    }//GEN-LAST:event_tlpn_pengantarKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        akses.setform("RMTriasePonek");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void ChkKll_tunggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkKll_tunggalActionPerformed
        tgl_kejadian_tunggal.setEnabled(false);
        cmbJam1.setEnabled(false);
        cmbMnt1.setEnabled(false);
        cmbDtk1.setEnabled(false);
        tmpt_kejadian_tunggal.setText("");
        Chktgl_kll_tunggal.setSelected(false);

        if (ChkKll_tunggal.isSelected() == true) {
            tmpt_kejadian_tunggal.setEnabled(true);            
            Chktgl_kll_tunggal.setEnabled(true);            
            tmpt_kejadian_tunggal.requestFocus();

            versus1.setEnabled(false);
            versus2.setEnabled(false);
            tmpt_kejadian.setEnabled(false);
            Chktgl_kll.setEnabled(false);
            tgl_kejadian.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);

            versus1.setText("");
            versus2.setText("");
            tmpt_kejadian.setText("");
            Chktgl_kll.setSelected(false);

            ket_jatuh.setEnabled(false);
            ket_jatuh.setText("");
            ket_luka.setEnabled(false);
            ket_luka.setText("");
            ket_trauma_listrik.setEnabled(false);
            ket_trauma_listrik.setText("");
            ket_trauma_zat.setEnabled(false);
            ket_trauma_zat.setText("");
            ket_trauma_lain.setEnabled(false);
            ket_trauma_lain.setText("");
        } else {
            tmpt_kejadian_tunggal.setEnabled(false);
            Chktgl_kll_tunggal.setEnabled(false);
        }
    }//GEN-LAST:event_ChkKll_tunggalActionPerformed

    private void Chktgl_kll_tunggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chktgl_kll_tunggalActionPerformed
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        
        if (Chktgl_kll_tunggal.isSelected() == true) {
            tgl_kejadian_tunggal.setEnabled(true);
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
            tgl_kejadian_tunggal.requestFocus();
        } else {
            tgl_kejadian_tunggal.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
        }
    }//GEN-LAST:event_Chktgl_kll_tunggalActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void ChkKllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkKllActionPerformed
        tgl_kejadian.setEnabled(false);
        cmbJam2.setEnabled(false);
        cmbMnt2.setEnabled(false);
        cmbDtk2.setEnabled(false);
        versus1.setText("");
        versus2.setText("");
        tmpt_kejadian.setText("");
        Chktgl_kll.setSelected(false);
        
        if (ChkKll.isSelected() == true) {
            versus1.setEnabled(true);            
            versus2.setEnabled(true);
            tmpt_kejadian.setEnabled(true);
            Chktgl_kll.setEnabled(true);
            versus1.requestFocus();

            tmpt_kejadian_tunggal.setEnabled(false);
            Chktgl_kll_tunggal.setSelected(false);
            Chktgl_kll_tunggal.setEnabled(false);
            tgl_kejadian_tunggal.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);

            tmpt_kejadian_tunggal.setText("");

            ket_jatuh.setEnabled(false);
            ket_jatuh.setText("");
            ket_luka.setEnabled(false);
            ket_luka.setText("");
            ket_trauma_listrik.setEnabled(false);
            ket_trauma_listrik.setText("");
            ket_trauma_zat.setEnabled(false);
            ket_trauma_zat.setText("");
            ket_trauma_lain.setEnabled(false);
            ket_trauma_lain.setText("");
        } else {
            versus1.setEnabled(false);
            versus2.setEnabled(false);
            tmpt_kejadian.setEnabled(false);
            Chktgl_kll.setEnabled(false);
        }
    }//GEN-LAST:event_ChkKllActionPerformed

    private void versus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_versus1KeyPressed
        Valid.pindah(evt, versus1, versus2);
    }//GEN-LAST:event_versus1KeyPressed

    private void versus2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_versus2KeyPressed
        Valid.pindah(evt, versus1, tmpt_kejadian);
    }//GEN-LAST:event_versus2KeyPressed

    private void Chktgl_kllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chktgl_kllActionPerformed
        cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk2.setSelectedIndex(0);
        
        if (Chktgl_kll.isSelected() == true) {
            tgl_kejadian.setEnabled(true);
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
            tgl_kejadian.requestFocus();
        } else {
            tgl_kejadian.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
        }
    }//GEN-LAST:event_Chktgl_kllActionPerformed

    private void cmbJam2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam2MouseReleased
        AutoCompleteDecorator.decorate(cmbJam2);
    }//GEN-LAST:event_cmbJam2MouseReleased

    private void cmbMnt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt2MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt2);
    }//GEN-LAST:event_cmbMnt2MouseReleased

    private void cmbDtk2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk2);
    }//GEN-LAST:event_cmbDtk2MouseReleased

    private void ChkJatuhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJatuhActionPerformed
        ket_jatuh.setText("");
        
        if (ChkJatuh.isSelected() == true) {
            ket_jatuh.setEnabled(true);
            ket_jatuh.requestFocus();            

            tmpt_kejadian_tunggal.setEnabled(false);
            Chktgl_kll_tunggal.setSelected(false);
            Chktgl_kll_tunggal.setEnabled(false);
            tgl_kejadian_tunggal.setEnabled(false);            
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);

            tmpt_kejadian_tunggal.setText("");

            versus1.setEnabled(false);
            versus2.setEnabled(false);
            tmpt_kejadian.setEnabled(false);
            Chktgl_kll.setEnabled(false);
            tgl_kejadian.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);

            versus1.setText("");
            versus2.setText("");
            tmpt_kejadian.setText("");
            Chktgl_kll.setSelected(false);

            ket_luka.setEnabled(false);
            ket_luka.setText("");
            ket_trauma_listrik.setEnabled(false);
            ket_trauma_listrik.setText("");
            ket_trauma_zat.setEnabled(false);
            ket_trauma_zat.setText("");
            ket_trauma_lain.setEnabled(false);
            ket_trauma_lain.setText("");
        } else {
            ket_jatuh.setEnabled(false);
        }
    }//GEN-LAST:event_ChkJatuhActionPerformed

    private void ChkLukaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkLukaActionPerformed
        ket_luka.setText("");
        if (ChkLuka.isSelected() == true) {
            ket_luka.setEnabled(true);
            ket_luka.requestFocus();            

            tmpt_kejadian_tunggal.setEnabled(false);
            Chktgl_kll_tunggal.setSelected(false);
            Chktgl_kll_tunggal.setEnabled(false);
            tgl_kejadian_tunggal.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);

            tmpt_kejadian_tunggal.setText("");

            versus1.setEnabled(false);
            versus2.setEnabled(false);
            tmpt_kejadian.setEnabled(false);
            Chktgl_kll.setEnabled(false);
            tgl_kejadian.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);

            versus1.setText("");
            versus2.setText("");
            tmpt_kejadian.setText("");
            Chktgl_kll.setSelected(false);

            ket_jatuh.setEnabled(false);
            ket_jatuh.setText("");
            ket_trauma_listrik.setEnabled(false);
            ket_trauma_listrik.setText("");
            ket_trauma_zat.setEnabled(false);
            ket_trauma_zat.setText("");
            ket_trauma_lain.setEnabled(false);
            ket_trauma_lain.setText("");
        } else {
            ket_luka.setEnabled(false);
        }
    }//GEN-LAST:event_ChkLukaActionPerformed

    private void Chktrauma_listrikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chktrauma_listrikActionPerformed
        ket_trauma_listrik.setText("");
        if (Chktrauma_listrik.isSelected() == true) {
            ket_trauma_listrik.setEnabled(true);
            ket_trauma_listrik.requestFocus();            

            tmpt_kejadian_tunggal.setEnabled(false);
            Chktgl_kll_tunggal.setSelected(false);
            Chktgl_kll_tunggal.setEnabled(false);
            tgl_kejadian_tunggal.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);

            tmpt_kejadian_tunggal.setText("");

            versus1.setEnabled(false);
            versus2.setEnabled(false);
            tmpt_kejadian.setEnabled(false);
            Chktgl_kll.setEnabled(false);
            tgl_kejadian.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);

            versus1.setText("");
            versus2.setText("");
            tmpt_kejadian.setText("");
            Chktgl_kll.setSelected(false);

            ket_jatuh.setEnabled(false);
            ket_jatuh.setText("");
            ket_luka.setEnabled(false);
            ket_luka.setText("");
            ket_trauma_zat.setEnabled(false);
            ket_trauma_zat.setText("");
            ket_trauma_lain.setEnabled(false);
            ket_trauma_lain.setText("");
        } else {
            ket_trauma_listrik.setEnabled(false);
        }
    }//GEN-LAST:event_Chktrauma_listrikActionPerformed

    private void Chktrauma_zatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chktrauma_zatActionPerformed
        ket_trauma_zat.setText("");
        if (Chktrauma_zat.isSelected() == true) {
            ket_trauma_zat.setEnabled(true);
            ket_trauma_zat.requestFocus();            

            tmpt_kejadian_tunggal.setEnabled(false);
            Chktgl_kll_tunggal.setSelected(false);
            Chktgl_kll_tunggal.setEnabled(false);
            tgl_kejadian_tunggal.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);

            tmpt_kejadian_tunggal.setText("");

            versus1.setEnabled(false);
            versus2.setEnabled(false);
            tmpt_kejadian.setEnabled(false);
            Chktgl_kll.setEnabled(false);
            tgl_kejadian.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);

            versus1.setText("");
            versus2.setText("");
            tmpt_kejadian.setText("");
            Chktgl_kll.setSelected(false);

            ket_jatuh.setEnabled(false);
            ket_jatuh.setText("");
            ket_luka.setEnabled(false);
            ket_luka.setText("");
            ket_trauma_listrik.setEnabled(false);
            ket_trauma_listrik.setText("");
            ket_trauma_lain.setEnabled(false);
            ket_trauma_lain.setText("");
        } else {
            ket_trauma_zat.setEnabled(false);
        }
    }//GEN-LAST:event_Chktrauma_zatActionPerformed

    private void Chktrauma_lainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chktrauma_lainActionPerformed
        ket_trauma_lain.setText("");
        if (Chktrauma_lain.isSelected() == true) {
            ket_trauma_lain.setEnabled(true);
            ket_trauma_lain.requestFocus();            

            tmpt_kejadian_tunggal.setEnabled(false);
            Chktgl_kll_tunggal.setSelected(false);
            Chktgl_kll_tunggal.setEnabled(false);
            tgl_kejadian_tunggal.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);

            tmpt_kejadian_tunggal.setText("");

            versus1.setEnabled(false);
            versus2.setEnabled(false);
            tmpt_kejadian.setEnabled(false);
            Chktgl_kll.setEnabled(false);
            tgl_kejadian.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);

            versus1.setText("");
            versus2.setText("");
            tmpt_kejadian.setText("");
            Chktgl_kll.setSelected(false);

            ket_jatuh.setEnabled(false);
            ket_jatuh.setText("");
            ket_luka.setEnabled(false);
            ket_luka.setText("");
            ket_trauma_listrik.setEnabled(false);
            ket_trauma_listrik.setText("");
            ket_trauma_zat.setEnabled(false);
            ket_trauma_zat.setText("");
        } else {
            ket_trauma_lain.setEnabled(false);
        }
    }//GEN-LAST:event_Chktrauma_lainActionPerformed

    private void Tkeluhan_utamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkeluhan_utamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Chkpacs1.requestFocus();
        }
    }//GEN-LAST:event_Tkeluhan_utamaKeyPressed

    private void BtnKeluhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluhanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 1;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Keluhan Utama ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnKeluhanActionPerformed

    private void bbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bbKeyPressed
        Valid.pindah(evt, bb, tb);
    }//GEN-LAST:event_bbKeyPressed

    private void ket_trauma_lainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ket_trauma_lainKeyPressed
        Valid.pindah(evt, Ticd10, Ticd10);
    }//GEN-LAST:event_ket_trauma_lainKeyPressed

    private void tkann_darahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkann_darahKeyPressed
        Valid.pindah(evt, cmbKesadaran, nadi);
    }//GEN-LAST:event_tkann_darahKeyPressed

    private void pernapasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pernapasanKeyPressed
        Valid.pindah(evt, nadi, temperatur);
    }//GEN-LAST:event_pernapasanKeyPressed

    private void saturasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saturasiKeyPressed
        Valid.pindah(evt, temperatur, Tnyeri);
    }//GEN-LAST:event_saturasiKeyPressed

    private void nadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nadiKeyPressed
        Valid.pindah(evt, tkann_darah, pernapasan);
    }//GEN-LAST:event_nadiKeyPressed

    private void temperaturKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_temperaturKeyPressed
        Valid.pindah(evt, pernapasan, saturasi);
    }//GEN-LAST:event_temperaturKeyPressed

    private void TnyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnyeriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TnyeriKeyPressed

    private void Chksadar_skor0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chksadar_skor0ActionPerformed
        hitungSkor();
    }//GEN-LAST:event_Chksadar_skor0ActionPerformed

    private void Chksadar_skor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chksadar_skor3ActionPerformed
        hitungSkor();
    }//GEN-LAST:event_Chksadar_skor3ActionPerformed

    private void Chk100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk100ActionPerformed
        hitungSkor();
    }//GEN-LAST:event_Chk100ActionPerformed

    private void Chk99ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk99ActionPerformed
        hitungSkor();
    }//GEN-LAST:event_Chk99ActionPerformed

    private void Chk101ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk101ActionPerformed
        hitungSkor();
    }//GEN-LAST:event_Chk101ActionPerformed

    private void Chk102ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk102ActionPerformed
        hitungSkor();
    }//GEN-LAST:event_Chk102ActionPerformed

    private void Chk19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk19ActionPerformed
        hitungSkor();
    }//GEN-LAST:event_Chk19ActionPerformed

    private void Chk20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk20ActionPerformed
        hitungSkor();
    }//GEN-LAST:event_Chk20ActionPerformed

    private void Chk22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk22ActionPerformed
        hitungSkor();
    }//GEN-LAST:event_Chk22ActionPerformed

    private void Chk35_skor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk35_skor3ActionPerformed
        hitungSkor();
    }//GEN-LAST:event_Chk35_skor3ActionPerformed

    private void Chk35_skor0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk35_skor0ActionPerformed
        hitungSkor();
    }//GEN-LAST:event_Chk35_skor0ActionPerformed

    private void Chk96ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk96ActionPerformed
        hitungSkor();
    }//GEN-LAST:event_Chk96ActionPerformed

    private void Chk94ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk94ActionPerformed
        hitungSkor();
    }//GEN-LAST:event_Chk94ActionPerformed

    private void Chk92ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk92ActionPerformed
        hitungSkor();
    }//GEN-LAST:event_Chk92ActionPerformed

    private void Chk92_skor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk92_skor3ActionPerformed
        hitungSkor();
    }//GEN-LAST:event_Chk92_skor3ActionPerformed

    private void Tcttn_khususKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tcttn_khususKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tkeputusan.requestFocus();
        }
    }//GEN-LAST:event_Tcttn_khususKeyPressed

    private void cmbJam3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam3MouseReleased
        AutoCompleteDecorator.decorate(cmbJam3);
    }//GEN-LAST:event_cmbJam3MouseReleased

    private void cmbMnt3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt3MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt3);
    }//GEN-LAST:event_cmbMnt3MouseReleased

    private void cmbDtk3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk3MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk3);
    }//GEN-LAST:event_cmbDtk3MouseReleased

    private void BtnCatatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCatatanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 2;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Catatan Khusus ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnCatatanActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMTriasePonek dialog = new RMTriasePonek(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnCatatan;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCopas;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluhan;
    private widget.Button BtnNotepad;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    public widget.CekBox Chk100;
    public widget.CekBox Chk101;
    public widget.CekBox Chk102;
    public widget.CekBox Chk19;
    public widget.CekBox Chk20;
    public widget.CekBox Chk22;
    public widget.CekBox Chk35_skor0;
    public widget.CekBox Chk35_skor3;
    public widget.CekBox Chk92;
    public widget.CekBox Chk92_skor3;
    public widget.CekBox Chk94;
    public widget.CekBox Chk96;
    public widget.CekBox Chk99;
    public widget.CekBox ChkJatuh;
    public widget.CekBox ChkKll;
    public widget.CekBox ChkKll_tunggal;
    public widget.CekBox ChkLuka;
    public widget.CekBox Chkdoa;
    public widget.CekBox Chkklinik;
    public widget.CekBox Chknon_resus;
    public widget.CekBox Chkpacs1;
    public widget.CekBox Chkpacs2;
    public widget.CekBox Chkpacs3;
    public widget.CekBox Chkpacs4;
    public widget.CekBox Chkresus;
    public widget.CekBox Chksadar_skor0;
    public widget.CekBox Chksadar_skor3;
    public widget.CekBox Chktgl_kll;
    public widget.CekBox Chktgl_kll_tunggal;
    public widget.CekBox Chktrauma_lain;
    public widget.CekBox Chktrauma_listrik;
    public widget.CekBox Chktrauma_zat;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.InternalFrame FormTriase;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane ScrollTriase1;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextArea Tcttn_khusus;
    private widget.TextBox Ticd10;
    private widget.TextArea Tkeluhan_utama;
    private widget.TextBox Tkeputusan;
    private widget.TextBox Tnyeri;
    private widget.TextArea Ttemplate;
    private widget.Tanggal Ttgl;
    private widget.TextBox TtglLahir;
    private javax.swing.JDialog WindowTemplate;
    private widget.TextBox bb;
    private widget.TextBox bkn_ambulan;
    private widget.Button btnPetugas;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroupKesadaran;
    private javax.swing.ButtonGroup buttonGroupNadi;
    private javax.swing.ButtonGroup buttonGroupRespirasi;
    private javax.swing.ButtonGroup buttonGroupSaturasi;
    private javax.swing.ButtonGroup buttonGroupTekanan;
    private javax.swing.ButtonGroup buttonGroupTemperatur;
    private widget.ComboBox cmbAlasanKedatangan;
    private widget.ComboBox cmbCaraMasuk;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbDtk3;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbJam3;
    private widget.ComboBox cmbKasus;
    private widget.ComboBox cmbKendaraan;
    private widget.ComboBox cmbKesadaran;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbMnt3;
    private widget.TextBox dijemput_oleh;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
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
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
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
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private widget.TextBox ket_jatuh;
    private widget.TextBox ket_luka;
    private widget.TextBox ket_trauma_lain;
    private widget.TextBox ket_trauma_listrik;
    private widget.TextBox ket_trauma_zat;
    private widget.Label label_01;
    private widget.Label label_24;
    private widget.Label label_5;
    private widget.TextBox nadi;
    private widget.Label nadi1;
    private widget.TextBox nm_pengantar;
    private widget.TextBox nm_petugas;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi4;
    private widget.TextBox pernapasan;
    private widget.TextBox rujukan_dari;
    private widget.TextBox saturasi;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane9;
    private widget.TextBox sdh_terpasang;
    private widget.TextBox tb;
    private widget.Table tbTemplate;
    private widget.Table tbTriase;
    private widget.TextBox temperatur;
    private widget.Tanggal tgl_kejadian;
    private widget.Tanggal tgl_kejadian_tunggal;
    private widget.TextBox tkann_darah;
    private widget.TextBox tlpn_pengantar;
    private widget.TextBox tmpt_kejadian;
    private widget.TextBox tmpt_kejadian_tunggal;
    private widget.TextBox versus1;
    private widget.TextBox versus2;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT tp.*, p.no_rkm_medis, p.nm_pasien, p.tgl_lahir, concat(date_format(tp.tanggal,'%d/%m/%Y'),' ',time_format(tp.pukul,'%H:%i'),' Wita') tglTriase, "
                    + "pg.nama nmPetugas FROM triase_ponek tp inner join reg_periksa rp on rp.no_rawat=tp.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=tp.nip_petugas where "
                    + "tp.tanggal between ? and ? and tp.no_rawat like ? or "
                    + "tp.tanggal between ? and ? and p.no_rkm_medis like ? or "
                    + "tp.tanggal between ? and ? and p.nm_pasien like ? or "
                    + "tp.tanggal between ? and ? and tp.nm_pengantar like ? or "
                    + "tp.tanggal between ? and ? and tp.telp_pengantar like ? or "
                    + "tp.tanggal between ? and ? and pg.nama like ? or "
                    + "tp.tanggal between ? and ? and tp.kasus like ? order by tp.tanggal desc");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        Valid.SetTglINDONESIA(rs.getString("tgl_lahir")),
                        rs.getString("tglTriase"),
                        rs.getString("cara_masuk"),
                        rs.getString("sudah_terpasang"),
                        rs.getString("alasan_kedatangan"),
                        rs.getString("rujukan_dari"),
                        rs.getString("dijemput_oleh"),
                        rs.getString("kendaraan"),
                        rs.getString("bukan_ambulan"),
                        rs.getString("nm_pengantar"),
                        rs.getString("telp_pengantar"),
                        rs.getString("kasus"),
                        rs.getString("nmPetugas"),
                        rs.getString("tanggal"),
                        rs.getString("pukul"),
                        rs.getString("cara_masuk"),
                        rs.getString("sudah_terpasang"),
                        rs.getString("alasan_kedatangan"),
                        rs.getString("rujukan_dari"),
                        rs.getString("dijemput_oleh"),
                        rs.getString("kendaraan"),
                        rs.getString("bukan_ambulan"),
                        rs.getString("nm_pengantar"),
                        rs.getString("telp_pengantar"),
                        rs.getString("kasus"),
                        rs.getString("kll_tunggal"),
                        rs.getString("kll_tunggal_tmpt_kejadian"),
                        rs.getString("tgl_kejadian_kll_tunggal"),
                        rs.getString("kll_tunggal_tanggal"),
                        rs.getString("kll_tunggal_pukul"),
                        rs.getString("kll_versus"),
                        rs.getString("versus1"),
                        rs.getString("versus2"),
                        rs.getString("kll_tmpt_kejadian"),
                        rs.getString("tgl_kejadian_kll"),
                        rs.getString("kll_tanggal"),
                        rs.getString("kll_pukul"),
                        rs.getString("jatuh"),
                        rs.getString("ket_jatuh"),
                        rs.getString("luka_bakar"),
                        rs.getString("ket_luka_bakar"),
                        rs.getString("trauma_listrik"),
                        rs.getString("ket_trauma_listrik"),
                        rs.getString("trauma_zat_kimia"),
                        rs.getString("ket_trauma_zat_kimia"),
                        rs.getString("trauma_lain"),
                        rs.getString("ket_trauma_lain"),
                        rs.getString("icd_10"),
                        rs.getString("keluhan_utama"),
                        rs.getString("pacs1"),
                        rs.getString("pacs2"),
                        rs.getString("pacs3"),
                        rs.getString("pacs4"),
                        rs.getString("kesadaran"),
                        rs.getString("td"),
                        rs.getString("nadi"),
                        rs.getString("napas"),
                        rs.getString("temperatur"),
                        rs.getString("saturasi"),
                        rs.getString("nyeri"),
                        rs.getString("bb"),
                        rs.getString("tb"),
                        rs.getString("skor0_sadar_penuh"),
                        rs.getString("skor0_100"),
                        rs.getString("skor0_101"),
                        rs.getString("skor0_19"),
                        rs.getString("skor0_35_3"),
                        rs.getString("skor0_96_100"),
                        rs.getString("skor1_102"),
                        rs.getString("skor1_20_21"),
                        rs.getString("skor1_94_95"),
                        rs.getString("skor2_99"),
                        rs.getString("skor2_22"),
                        rs.getString("skor2_92_93"),
                        rs.getString("skor3_selain"),
                        rs.getString("skor3_35_3"),
                        rs.getString("skor3_92"),
                        rs.getString("total_skor"),
                        rs.getString("catatan"),
                        rs.getString("keputusan"),
                        rs.getString("pukul_keputusan"),
                        rs.getString("triase_resusitasi"),
                        rs.getString("triase_non_resusitasi"),
                        rs.getString("triase_klinik"),
                        rs.getString("triase_doa"),
                        rs.getString("nip_petugas"),
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
    
    public void emptTeks(){
        Ttgl.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        cmbCaraMasuk.setSelectedIndex(0);
        sdh_terpasang.setText("");
        cmbAlasanKedatangan.setSelectedIndex(0);
        rujukan_dari.setText("");  
        dijemput_oleh.setText("");
        rujukan_dari.setEnabled(false);
        dijemput_oleh.setEnabled(false);
        cmbKendaraan.setSelectedIndex(0);
        bkn_ambulan.setText("");
        bkn_ambulan.setEnabled(false);
        nm_pengantar.setText("");
        tlpn_pengantar.setText("");
        cmbKasus.setSelectedIndex(0);
        
        ChkKll_tunggal.setSelected(false);
        tmpt_kejadian_tunggal.setText("");
        tmpt_kejadian_tunggal.setEnabled(false);
        Chktgl_kll_tunggal.setSelected(false);
        Chktgl_kll_tunggal.setEnabled(false);
        tgl_kejadian_tunggal.setDate(new Date());
        tgl_kejadian_tunggal.setEnabled(false);        
        cmbJam1.setEnabled(false);
        cmbMnt1.setEnabled(false);
        cmbDtk1.setEnabled(false);
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        
        ChkKll.setSelected(false);
        versus1.setText("");
        versus1.setEnabled(false);
        versus2.setText("");
        versus2.setEnabled(false);
        tmpt_kejadian.setText("");
        tmpt_kejadian.setEnabled(false);
        Chktgl_kll.setSelected(false);
        Chktgl_kll.setEnabled(false);
        tgl_kejadian.setDate(new Date());
        tgl_kejadian.setEnabled(false);
        cmbJam2.setEnabled(false);
        cmbMnt2.setEnabled(false);
        cmbDtk2.setEnabled(false);
        cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk2.setSelectedIndex(0);
        
        ChkJatuh.setSelected(false);
        ket_jatuh.setText("");
        ket_jatuh.setEnabled(false);
        ChkLuka.setSelected(false);
        ket_luka.setText("");
        ket_luka.setEnabled(false);
        Chktrauma_listrik.setSelected(false);
        ket_trauma_listrik.setText("");
        ket_trauma_listrik.setEnabled(false);
        Chktrauma_zat.setSelected(false);
        ket_trauma_zat.setText("");
        ket_trauma_zat.setEnabled(false);
        Chktrauma_lain.setSelected(false);
        ket_trauma_lain.setText("");
        ket_trauma_lain.setEnabled(false);
        Ticd10.setText("");
        Tkeluhan_utama.setText("");
        Chkpacs1.setSelected(false);
        Chkpacs2.setSelected(false);
        Chkpacs3.setSelected(false);
        Chkpacs4.setSelected(false);
        
        cmbKesadaran.setSelectedIndex(0);        
        tkann_darah.setText("");
        nadi.setText("");
        pernapasan.setText("");
        temperatur.setText("");
        saturasi.setText("");
        Tnyeri.setText("");
        bb.setText("");
        tb.setText("");
        
        Chksadar_skor0.setSelected(false);
        Chk100.setSelected(false);
        Chk101.setSelected(false);
        Chk19.setSelected(false);
        Chk35_skor0.setSelected(false);        
        Chk96.setSelected(false);
        
        Chk102.setSelected(false);
        Chk20.setSelected(false);
        Chk94.setSelected(false);
        
        Chk99.setSelected(false);
        Chk22.setSelected(false);
        Chk92.setSelected(false);
        
        Chksadar_skor3.setSelected(false);
        Chk35_skor3.setSelected(false);
        Chk92_skor3.setSelected(false);

        totskor = 0;
        skortotal = "";
        //skor 0    
        sdr_pnh_skor0 = 0;
        seratus_skor0 = 0;
        seratus1_skor0 = 0;
        sembilan_bls_skor0 = 0;
        tiga5_skor0 = 0;
        sembilan6_skor0 = 0;
        //skor 1        
        seratus2_skor1 = 0;
        dua_plh_skor1 = 0;
        sembilan4_skor1 = 0;
        //skor 2        
        sembilan9_skor2 = 0;
        dua2_skor2 = 0;
        sembilan2_skor2 = 0;
        //skor 3        
        selain_skor3 = 0;
        tiga5_skor3 = 0;
        sembilan2_skor3 = 0;
        //hasil tanda vital        
        jlh_kesadaran = 0;
        jlh_tekanan = 0;
        jlh_nadi = 0;
        jlh_respirasi = 0;
        jlh_tempe = 0;
        jlh_satur = 0;

        Tcttn_khusus.setText("");
        Tkeputusan.setText("");
        Chkresus.setSelected(false);
        Chknon_resus.setSelected(false);
        Chkklinik.setSelected(false);
        Chkdoa.setSelected(false);
        cmbJam3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk3.setSelectedIndex(0);
        
        label_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        label_24.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        label_01.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        
        buttonGroupKesadaran.clearSelection();
        buttonGroupTekanan.clearSelection();
        buttonGroupNadi.clearSelection();
        buttonGroupRespirasi.clearSelection();
        buttonGroupTemperatur.clearSelection();
        buttonGroupSaturasi.clearSelection();        
        Valid.tabelKosong(tabMode1);
    }
    
    public void setNoRm(String norwt) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(new Date());
        isRawat();
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnPrint.setEnabled(akses.getcppt());
        BtnEdit.setEnabled(akses.getcppt());
        
        if (akses.getjml2() >= 1) {            
            btnPetugas.setEnabled(false);
            nip = akses.getkode();            
            Sequel.cariIsi("select nama from pegawai where nik=?", nm_petugas, nip);
            if (nm_petugas.getText().equals("")) {
                nip = "-";
                nm_petugas.setText("-");
            }
        }  
    }
    
    private void getData() {
        nip = "";
        kll_tunggal = "";
        tgl_kejadian_tggl = "";
        kll = "";
        tgl_kejadian_kll = "";
        jatuh = "";
        luka = "";
        trauma_listrik = "";
        trauma_zat = "";
        trauma_lain = "";
        pacs1 = "";
        pacs2 = "";
        pacs3 = "";
        pacs4 = "";
        skor0_sadar = "";
        skor0_100 = "";
        skor0_101 = "";
        skor0_19 = "";
        skor0_35 = "";
        skor0_96 = "";
        skor102 = "";
        skor20 = "";
        skor94 = "";
        skor99 = "";
        skor22 = "";
        skor92 = "";
        skor3_sadar = "";
        skor3_35 = "";
        skor3_92 = "";
//        totskor = 0;
//        skortotal = "";
        resus = "";
        nonresus = "";
        klinik = "";
        doa = "";
        
        if (tbTriase.getSelectedRow() != -1) {
            TNoRw.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 0).toString());
            TNoRM.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 1).toString());
            TPasien.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 2).toString());
            TtglLahir.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 3).toString());
            Valid.SetTgl(Ttgl, tbTriase.getValueAt(tbTriase.getSelectedRow(), 16).toString());
            cmbJam.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 17).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 17).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 17).toString().substring(6, 8));            
            cmbCaraMasuk.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 5).toString());
            sdh_terpasang.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 6).toString());
            cmbAlasanKedatangan.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 7).toString());
            rujukan_dari.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 8).toString());
            dijemput_oleh.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 9).toString());
            cmbKendaraan.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 10).toString());
            bkn_ambulan.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 11).toString());
            nm_pengantar.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 12).toString());
            tlpn_pengantar.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 13).toString());
            cmbKasus.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 14).toString());
            nip = tbTriase.getValueAt(tbTriase.getSelectedRow(), 88).toString();
            nm_petugas.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 15).toString());      
            
            kll_tunggal = tbTriase.getValueAt(tbTriase.getSelectedRow(), 28).toString();
            tmpt_kejadian_tunggal.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 29).toString());
            tgl_kejadian_tggl = tbTriase.getValueAt(tbTriase.getSelectedRow(), 30).toString();
            Valid.SetTgl(tgl_kejadian_tunggal, tbTriase.getValueAt(tbTriase.getSelectedRow(), 31).toString());
            cmbJam1.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 32).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 32).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 32).toString().substring(6, 8));             
            
            kll = tbTriase.getValueAt(tbTriase.getSelectedRow(), 33).toString();
            versus1.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 34).toString());
            versus2.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 35).toString());
            tmpt_kejadian.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 36).toString());
            tgl_kejadian_kll = tbTriase.getValueAt(tbTriase.getSelectedRow(), 37).toString();            
            Valid.SetTgl(tgl_kejadian, tbTriase.getValueAt(tbTriase.getSelectedRow(), 38).toString());
            cmbJam2.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 39).toString().substring(0, 2));
            cmbMnt2.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 39).toString().substring(3, 5));
            cmbDtk2.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 39).toString().substring(6, 8));                         
            
            jatuh = tbTriase.getValueAt(tbTriase.getSelectedRow(), 40).toString();
            ket_jatuh.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 41).toString());
            luka = tbTriase.getValueAt(tbTriase.getSelectedRow(), 42).toString();
            ket_luka.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 43).toString());
            trauma_listrik = tbTriase.getValueAt(tbTriase.getSelectedRow(), 44).toString();
            ket_trauma_listrik.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 45).toString());
            trauma_zat = tbTriase.getValueAt(tbTriase.getSelectedRow(), 46).toString();
            ket_trauma_zat.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 47).toString());
            trauma_lain = tbTriase.getValueAt(tbTriase.getSelectedRow(), 48).toString();
            ket_trauma_lain.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 49).toString());
            Ticd10.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 50).toString());            
            Tkeluhan_utama.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 51).toString());
            pacs1 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 52).toString();
            pacs2 = tbTriase.getValueAt(tbTriase.getSelectedRow(),53).toString();
            pacs3 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 54).toString();
            pacs4 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 55).toString();
            cmbKesadaran.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 56).toString());
            tkann_darah.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 57).toString());
            nadi.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 58).toString());
            pernapasan.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 59).toString());
            temperatur.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 60).toString());
            saturasi.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 61).toString());
            Tnyeri.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 62).toString());
            bb.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 63).toString());
            tb.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 64).toString());
            
            skor0_sadar = tbTriase.getValueAt(tbTriase.getSelectedRow(), 65).toString();
            skor0_100 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 66).toString();
            skor0_101 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 67).toString();
            skor0_19 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 68).toString();
            skor0_35 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 69).toString();
            skor0_96 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 70).toString();
            
            skor102 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 71).toString();
            skor20 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 72).toString();
            skor94 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 73).toString();
            skor99 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 74).toString();
            skor22 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 75).toString();
            skor92 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 76).toString();
            skor3_sadar = tbTriase.getValueAt(tbTriase.getSelectedRow(), 77).toString();
            skor3_35 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 78).toString();
            skor3_92 = tbTriase.getValueAt(tbTriase.getSelectedRow(), 79).toString();
//            totskor = Integer.parseInt(tbTriase.getValueAt(tbTriase.getSelectedRow(), 63).toString());
//            skortotal = tbTriase.getValueAt(tbTriase.getSelectedRow(), 63).toString();
            Tcttn_khusus.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 81).toString());
            Tkeputusan.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 82).toString());
            cmbJam3.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 83).toString().substring(0, 2));
            cmbMnt3.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 83).toString().substring(3, 5));
            cmbDtk3.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(), 83).toString().substring(6, 8));
            resus = tbTriase.getValueAt(tbTriase.getSelectedRow(), 84).toString();
            nonresus = tbTriase.getValueAt(tbTriase.getSelectedRow(), 85).toString();
            klinik = tbTriase.getValueAt(tbTriase.getSelectedRow(), 86).toString();
            doa = tbTriase.getValueAt(tbTriase.getSelectedRow(), 87).toString();            
            dataCek();
            cekMekanisme();
            hitungSkor();
        }
    }
    
    private void cekData() {
        if (ChkKll_tunggal.isSelected() == true) {
            kll_tunggal = "ya";
        } else {
            kll_tunggal = "tidak";
        }

        if (Chktgl_kll_tunggal.isSelected() == true) {
            tgl_kejadian_tggl = "ya";
        } else {
            tgl_kejadian_tggl = "tidak";
        }

        if (ChkKll.isSelected() == true) {
            kll = "ya";
        } else {
            kll = "tidak";
        }

        if (Chktgl_kll.isSelected() == true) {
            tgl_kejadian_kll = "ya";
        } else {
            tgl_kejadian_kll = "tidak";
        }

        if (ChkJatuh.isSelected() == true) {
            jatuh = "ya";
        } else {
            jatuh = "tidak";
        }

        if (ChkLuka.isSelected() == true) {
            luka = "ya";
        } else {
            luka = "tidak";
        }

        if (Chktrauma_listrik.isSelected() == true) {
            trauma_listrik = "ya";
        } else {
            trauma_listrik = "tidak";
        }

        if (Chktrauma_zat.isSelected() == true) {
            trauma_zat = "ya";
        } else {
            trauma_zat = "tidak";
        }

        if (Chktrauma_lain.isSelected() == true) {
            trauma_lain = "ya";
        } else {
            trauma_lain = "tidak";
        }
        
        if (Chkpacs1.isSelected() == true) {
            pacs1 = "ya";
        } else {
            pacs1 = "tidak";
        }
        
        if (Chkpacs2.isSelected() == true) {
            pacs2 = "ya";
        } else {
            pacs2 = "tidak";
        }
        
        if (Chkpacs3.isSelected() == true) {
            pacs3 = "ya";
        } else {
            pacs3 = "tidak";
        }
        
        if (Chkpacs4.isSelected() == true) {
            pacs4 = "ya";
        } else {
            pacs4 = "tidak";
        }
        
        if (Chksadar_skor0.isSelected() == true) {
            skor0_sadar = "ya";
        } else {
            skor0_sadar = "tidak";
        }
        
        if (Chk100.isSelected() == true) {
            skor0_100 = "ya";
        } else {
            skor0_100 = "tidak";
        }
        
        if (Chk101.isSelected() == true) {
            skor0_101 = "ya";
        } else {
            skor0_101 = "tidak";
        }
        
        if (Chk19.isSelected() == true) {
            skor0_19 = "ya";
        } else {
            skor0_19 = "tidak";
        }
        
        if (Chk35_skor0.isSelected() == true) {
            skor0_35 = "ya";
        } else {
            skor0_35 = "tidak";
        }
        
        if (Chk96.isSelected() == true) {
            skor0_96 = "ya";
        } else {
            skor0_96 = "tidak";
        }
        
        if (Chk102.isSelected() == true) {
            skor102 = "ya";
        } else {
            skor102 = "tidak";
        }
        
        if (Chk20.isSelected() == true) {
            skor20 = "ya";
        } else {
            skor20 = "tidak";
        }
        
        if (Chk94.isSelected() == true) {
            skor94 = "ya";
        } else {
            skor94 = "tidak";
        }
        
        if (Chk99.isSelected() == true) {
            skor99 = "ya";
        } else {
            skor99 = "tidak";
        }
        
        if (Chk22.isSelected() == true) {
            skor22 = "ya";
        } else {
            skor22 = "tidak";
        }
        
        if (Chk92.isSelected() == true) {
            skor92 = "ya";
        } else {
            skor92 = "tidak";
        }
        
        if (Chksadar_skor3.isSelected() == true) {
            skor3_sadar = "ya";
        } else {
            skor3_sadar = "tidak";
        }
        
        if (Chk35_skor3.isSelected() == true) {
            skor3_35 = "ya";
        } else {
            skor3_35 = "tidak";
        }
        
        if (Chk92_skor3.isSelected() == true) {
            skor3_92 = "ya";
        } else {
            skor3_92 = "tidak";
        }
        
        if (Chkresus.isSelected() == true) {
            resus = "ya";
        } else {
            resus = "tidak";
        }
        
        if (Chknon_resus.isSelected() == true) {
            nonresus = "ya";
        } else {
            nonresus = "tidak";
        }
        
        if (Chkklinik.isSelected() == true) {
            klinik = "ya";
        } else {
            klinik = "tidak";
        }
        
        if (Chkdoa.isSelected() == true) {
            doa = "ya";
        } else {
            doa = "tidak";
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from triase_ponek where no_rawat=?", 1, new String[]{
                tbTriase.getValueAt(tbTriase.getSelectedRow(), 0).toString()
            }) == true) {
                BtnBatalActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        } else {
            BtnBatalActionPerformed(null);
        }
    }

    private void ganti() {
        cekData();
        hitungSkor();
        if (Sequel.mengedittf("triase_ponek", "no_rawat=?", "tanggal=?, pukul=?, cara_masuk=?, sudah_terpasang=?, alasan_kedatangan=?, rujukan_dari=?, dijemput_oleh=?, kendaraan=?, "
                + "bukan_ambulan=?, nm_pengantar=?, telp_pengantar=?, kasus=?, kll_tunggal=?, kll_tunggal_tmpt_kejadian=?, tgl_kejadian_kll_tunggal=?, kll_tunggal_tanggal=?, kll_tunggal_pukul=?, "
                + "kll_versus=?, versus1=?, versus2=?, kll_tmpt_kejadian=?, tgl_kejadian_kll=?, kll_tanggal=?, kll_pukul=?, jatuh=?, ket_jatuh=?, luka_bakar=?, ket_luka_bakar=?, trauma_listrik=?, "
                + "ket_trauma_listrik=?, trauma_zat_kimia=?, ket_trauma_zat_kimia=?, trauma_lain=?, ket_trauma_lain=?, icd_10=?, keluhan_utama=?, pacs1=?, pacs2=?, pacs3=?, pacs4=?, kesadaran=?, td=?, "
                + "nadi=?, napas=?, temperatur=?, saturasi=?, nyeri=?, bb=?, tb=?, skor0_sadar_penuh=?, skor0_100=?, skor0_101=?, skor0_19=?, skor0_35_3=?, skor0_96_100=?, skor1_102=?, skor1_20_21=?, "
                + "skor1_94_95=?, skor2_99=?, skor2_22=?, skor2_92_93=?, skor3_selain=?, skor3_35_3=?, skor3_92=?, total_skor=?, catatan=?, keputusan=?, pukul_keputusan=?, triase_resusitasi=?, "
                + "triase_non_resusitasi=?, triase_klinik=?, triase_doa=?, nip_petugas=?", 74, new String[]{
                    Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                    cmbCaraMasuk.getSelectedItem().toString(), sdh_terpasang.getText(), cmbAlasanKedatangan.getSelectedItem().toString(), rujukan_dari.getText(),
                    dijemput_oleh.getText(), cmbKendaraan.getSelectedItem().toString(), bkn_ambulan.getText(), nm_pengantar.getText(), tlpn_pengantar.getText(),
                    cmbKasus.getSelectedItem().toString(), kll_tunggal, tmpt_kejadian_tunggal.getText(), tgl_kejadian_tggl, Valid.SetTgl(tgl_kejadian_tunggal.getSelectedItem() + ""),
                    cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), kll, versus1.getText(), versus2.getText(), tmpt_kejadian.getText(),
                    tgl_kejadian_kll, Valid.SetTgl(tgl_kejadian.getSelectedItem() + ""), cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(),
                    jatuh, ket_jatuh.getText(), luka, ket_luka.getText(), trauma_listrik, ket_trauma_listrik.getText(), trauma_zat, ket_trauma_zat.getText(), trauma_lain,
                    ket_trauma_lain.getText(), Ticd10.getText(), Tkeluhan_utama.getText(), pacs1, pacs2, pacs3, pacs4, cmbKesadaran.getSelectedItem().toString(), tkann_darah.getText(),
                    nadi.getText(), pernapasan.getText(), temperatur.getText(), saturasi.getText(), Tnyeri.getText(), bb.getText(), tb.getText(), skor0_sadar, skor0_100, skor0_101,
                    skor0_19, skor0_35, skor0_96, skor102, skor20, skor94, skor99, skor22, skor92, skor3_sadar, skor3_35, skor3_92, skortotal, Tcttn_khusus.getText(), Tkeputusan.getText(),
                    cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem(), resus, nonresus, klinik, doa, nip,
                    tbTriase.getValueAt(tbTriase.getSelectedRow(), 0).toString()
                }) == true) {

            Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Triase Ponek", "Ganti");
            TCari.setText(TNoRw.getText());
            BtnBatalActionPerformed(null);
            TabRawat.setSelectedIndex(1);
        }
    }
    
    private void isRawat() {
        try {
            psx = koneksi.prepareStatement("SELECT rp.no_rkm_medis, p.nm_pasien, p.tgl_lahir, rp.tgl_registrasi, "
                    + "IF(p.no_tlp_pj='','0',IFNULL(p.no_tlp_pj,'0')) notelppj, "
                    + "IF(p.namakeluarga='', '-',p.namakeluarga) namakeluarga, rp.jam_reg "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis WHERE rp.no_rawat=?");
            try {
                psx.setString(1, TNoRw.getText());
                rsx = psx.executeQuery();
                if (rsx.next()) {
                    TNoRM.setText(rsx.getString("no_rkm_medis"));
                    TPasien.setText(rsx.getString("nm_pasien"));
                    TtglLahir.setText(Valid.SetTglINDONESIA(rsx.getString("tgl_lahir")));
                    DTPCari1.setDate(rsx.getDate("tgl_registrasi"));
                    nm_pengantar.setText(rsx.getString("namakeluarga"));
                    tlpn_pengantar.setText(rsx.getString("notelppj"));
                    Valid.SetTgl(Ttgl, rsx.getDate("tgl_registrasi").toString());
                    cmbJam.setSelectedItem(rsx.getDate("jam_reg").toString().substring(0, 2));
                    cmbMnt.setSelectedItem(rsx.getDate("jam_reg").toString().substring(3, 5));
                    cmbDtk.setSelectedItem(rsx.getDate("jam_reg").toString().substring(6, 8));
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rsx != null) {
                    rsx.close();
                }
                if (psx != null) {
                    psx.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }
    
    private void dataCek() {
        if (kll_tunggal.equals("ya")) {
            ChkKll_tunggal.setSelected(true);
        } else {
            ChkKll_tunggal.setSelected(false);
        }
        
        if (tgl_kejadian_tggl.equals("ya")) {
            Chktgl_kll_tunggal.setSelected(true);
        } else {
            Chktgl_kll_tunggal.setSelected(false);
        }
        
        if (kll.equals("ya")) {
            ChkKll.setSelected(true);
        } else {
            ChkKll.setSelected(false);
        }
        
        if (tgl_kejadian_kll.equals("ya")) {
            Chktgl_kll.setSelected(true);
        } else {
            Chktgl_kll.setSelected(false);
        }
        
        if (jatuh.equals("ya")) {
            ChkJatuh.setSelected(true);
        } else {
            ChkJatuh.setSelected(false);
        }
        
        if (luka.equals("ya")) {
            ChkLuka.setSelected(true);
        } else {
            ChkLuka.setSelected(false);
        }
        
        if (trauma_listrik.equals("ya")) {
            Chktrauma_listrik.setSelected(true);
        } else {
            Chktrauma_listrik.setSelected(false);
        }
        
        if (trauma_zat.equals("ya")) {
            Chktrauma_zat.setSelected(true);
        } else {
            Chktrauma_zat.setSelected(false);
        }
        
        if (trauma_lain.equals("ya")) {
            Chktrauma_lain.setSelected(true);
        } else {
            Chktrauma_lain.setSelected(false);
        }
        
        if (pacs1.equals("ya")) {
            Chkpacs1.setSelected(true);
        } else {
            Chkpacs1.setSelected(false);
        }
        
        if (pacs2.equals("ya")) {
            Chkpacs2.setSelected(true);
        } else {
            Chkpacs2.setSelected(false);
        }
        
        if (pacs3.equals("ya")) {
            Chkpacs3.setSelected(true);
        } else {
            Chkpacs3.setSelected(false);
        }
        
        if (pacs4.equals("ya")) {
            Chkpacs4.setSelected(true);
        } else {
            Chkpacs4.setSelected(false);
        }
        
        if (skor0_sadar.equals("ya")) {
            Chksadar_skor0.setSelected(true);
        } else {
            Chksadar_skor0.setSelected(false);
        }
        
        if (skor0_100.equals("ya")) {
            Chk100.setSelected(true);
        } else {
            Chk100.setSelected(false);
        }
        
        if (skor0_101.equals("ya")) {
            Chk101.setSelected(true);
        } else {
            Chk101.setSelected(false);
        }
        
        if (skor0_19.equals("ya")) {
            Chk19.setSelected(true);
        } else {
            Chk19.setSelected(false);
        }
        
        if (skor0_35.equals("ya")) {
            Chk35_skor0.setSelected(true);
        } else {
            Chk35_skor0.setSelected(false);
        }
        
        if (skor0_96.equals("ya")) {
            Chk96.setSelected(true);
        } else {
            Chk96.setSelected(false);
        }
        
        if (skor102.equals("ya")) {
            Chk102.setSelected(true);
        } else {
            Chk102.setSelected(false);
        }
        
        if (skor20.equals("ya")) {
            Chk20.setSelected(true);
        } else {
            Chk20.setSelected(false);
        }
        
        if (skor94.equals("ya")) {
            Chk94.setSelected(true);
        } else {
            Chk94.setSelected(false);
        }
        
        if (skor99.equals("ya")) {
            Chk99.setSelected(true);
        } else {
            Chk99.setSelected(false);
        }
        
        if (skor22.equals("ya")) {
            Chk22.setSelected(true);
        } else {
            Chk22.setSelected(false);
        }
        
        if (skor92.equals("ya")) {
            Chk92.setSelected(true);
        } else {
            Chk92.setSelected(false);
        }
        
        if (skor3_sadar.equals("ya")) {
            Chksadar_skor3.setSelected(true);
        } else {
            Chksadar_skor3.setSelected(false);
        }
        
        if (skor3_35.equals("ya")) {
            Chk35_skor3.setSelected(true);
        } else {
            Chk35_skor3.setSelected(false);
        }
        
        if (skor3_92.equals("ya")) {
            Chk92_skor3.setSelected(true);
        } else {
            Chk92_skor3.setSelected(false);
        }
        
        if (resus.equals("ya")) {
            Chkresus.setSelected(true);
        } else {
            Chkresus.setSelected(false);
        }
        
        if (nonresus.equals("ya")) {
            Chknon_resus.setSelected(true);
        } else {
            Chknon_resus.setSelected(false);
        }

        if (klinik.equals("ya")) {
            Chkklinik.setSelected(true);
        } else {
            Chkklinik.setSelected(false);
        }
        
        if (doa.equals("ya")) {
            Chkdoa.setSelected(true);
        } else {
            Chkdoa.setSelected(false);
        }
    }
    
    private void cekMekanisme() {
        if (ChkKll_tunggal.isSelected() == true) {
            tmpt_kejadian_tunggal.setEnabled(true);            
            Chktgl_kll_tunggal.setEnabled(true);
            tgl_kejadian_tunggal.setEnabled(true);
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
            
            versus1.setEnabled(false);            
            versus2.setEnabled(false);
            tmpt_kejadian.setEnabled(false);
            Chktgl_kll.setEnabled(false);
            tgl_kejadian.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);

            ket_jatuh.setEnabled(false);            
            ket_luka.setEnabled(false);
            ket_trauma_listrik.setEnabled(false);
            ket_trauma_zat.setEnabled(false);
            ket_trauma_lain.setEnabled(false);
        } else {
            tmpt_kejadian_tunggal.setEnabled(false);
            Chktgl_kll_tunggal.setEnabled(false);
            tgl_kejadian_tunggal.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
        }

        if (ChkKll.isSelected() == true) {
            versus1.setEnabled(true);            
            versus2.setEnabled(true);
            tmpt_kejadian.setEnabled(true);
            Chktgl_kll.setEnabled(true);
            tgl_kejadian.setEnabled(true);
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);

            tmpt_kejadian_tunggal.setEnabled(false);
            Chktgl_kll_tunggal.setEnabled(false);
            tgl_kejadian_tunggal.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);

            ket_jatuh.setEnabled(false);
            ket_luka.setEnabled(false);
            ket_trauma_listrik.setEnabled(false);
            ket_trauma_zat.setEnabled(false);
            ket_trauma_lain.setEnabled(false);
        } else {
            versus1.setEnabled(false);
            versus2.setEnabled(false);
            tmpt_kejadian.setEnabled(false);
            Chktgl_kll.setEnabled(false);
            tgl_kejadian.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
        }

        if (ChkJatuh.isSelected() == true) {
            ket_jatuh.setEnabled(true);

            tmpt_kejadian_tunggal.setEnabled(false);            
            tgl_kejadian_tunggal.setEnabled(false);
            Chktgl_kll_tunggal.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);

            versus1.setEnabled(false);
            versus2.setEnabled(false);
            tmpt_kejadian.setEnabled(false);
            Chktgl_kll.setEnabled(false);
            tgl_kejadian.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);

            ket_luka.setEnabled(false);
            ket_trauma_listrik.setEnabled(false);
            ket_trauma_zat.setEnabled(false);
            ket_trauma_lain.setEnabled(false);
        } else {
            ket_jatuh.setEnabled(false);
        }
        
        if (ChkLuka.isSelected() == true) {
            ket_luka.setEnabled(true);

            tmpt_kejadian_tunggal.setEnabled(false);
            Chktgl_kll_tunggal.setEnabled(false);
            tgl_kejadian_tunggal.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);

            versus1.setEnabled(false);
            versus2.setEnabled(false);
            tmpt_kejadian.setEnabled(false);
            Chktgl_kll.setEnabled(false);
            tgl_kejadian.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);

            ket_jatuh.setEnabled(false);
            ket_trauma_listrik.setEnabled(false);
            ket_trauma_zat.setEnabled(false);
            ket_trauma_lain.setEnabled(false);
        } else {
            ket_luka.setEnabled(false);
        }
        
        if (Chktrauma_listrik.isSelected() == true) {
            ket_trauma_listrik.setEnabled(true);
            
            tmpt_kejadian_tunggal.setEnabled(false);
            Chktgl_kll_tunggal.setEnabled(false);
            tgl_kejadian_tunggal.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            
            versus1.setEnabled(false);
            versus2.setEnabled(false);
            tmpt_kejadian.setEnabled(false);
            Chktgl_kll.setEnabled(false);
            tgl_kejadian.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
            
            ket_jatuh.setEnabled(false);   
            ket_luka.setEnabled(false);
            ket_trauma_zat.setEnabled(false);
            ket_trauma_lain.setEnabled(false);
        } else {
            ket_trauma_listrik.setEnabled(false);
        }
        
        if (Chktrauma_zat.isSelected() == true) {
            ket_trauma_zat.setEnabled(true);
            
            tmpt_kejadian_tunggal.setEnabled(false);
            Chktgl_kll_tunggal.setEnabled(false);
            tgl_kejadian_tunggal.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            
            versus1.setEnabled(false);            
            versus2.setEnabled(false);
            tmpt_kejadian.setEnabled(false);
            Chktgl_kll.setEnabled(false);
            tgl_kejadian.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
            
            ket_jatuh.setEnabled(false);   
            ket_luka.setEnabled(false);
            ket_trauma_listrik.setEnabled(false);
            ket_trauma_lain.setEnabled(false);
        } else {
            ket_trauma_zat.setEnabled(false);
        }
        
        if (Chktrauma_lain.isSelected() == true) {
            ket_trauma_lain.setEnabled(true);
            
            tmpt_kejadian_tunggal.setEnabled(false);
            Chktgl_kll_tunggal.setEnabled(false);
            tgl_kejadian_tunggal.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            
            versus1.setEnabled(false);            
            versus2.setEnabled(false);
            tmpt_kejadian.setEnabled(false);
            Chktgl_kll.setEnabled(false);
            tgl_kejadian.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
            
            ket_jatuh.setEnabled(false);   
            ket_luka.setEnabled(false);
            ket_trauma_listrik.setEnabled(false);
            ket_trauma_zat.setEnabled(false);
        } else {
            ket_trauma_lain.setEnabled(false);
        }        
    }
    
    private void cekSkor() {
        totskor = 0;
        skortotal = "";
        //skor 0
        if (Chksadar_skor0.isSelected() == true || Chksadar_skor0.isSelected() == false) {
            sdr_pnh_skor0 = 0;            
        }
        if (Chk100.isSelected() == true || Chk100.isSelected() == false) {
            seratus_skor0 = 0;            
        }
        if (Chk101.isSelected() == true || Chk101.isSelected() == false) {
            seratus1_skor0 = 0;
        }
        if (Chk19.isSelected() == true || Chk19.isSelected() == false) {
            sembilan_bls_skor0 = 0;
        }
        if (Chk35_skor0.isSelected() == true || Chk35_skor0.isSelected() == false) {
            tiga5_skor0 = 0;
        }
        if (Chk96.isSelected() == true || Chk96.isSelected() == false) {
            sembilan6_skor0 = 0;
        }
        
        //skor 1
        if (Chk102.isSelected() == true) {
            seratus2_skor1 = 1;
        } else {
            seratus2_skor1 = 0;
        }
        if (Chk20.isSelected() == true) {
            dua_plh_skor1 = 1;
        } else {
            dua_plh_skor1 = 0;
        }
        if (Chk94.isSelected() == true) {
            sembilan4_skor1 = 1;
        } else {
            sembilan4_skor1 = 0;
        }
        
        //skor 2
        if (Chk99.isSelected() == true) {
            sembilan9_skor2 = 2;
        } else {
            sembilan9_skor2 = 0;
        }
        if (Chk22.isSelected() == true) {
            dua2_skor2 = 2;
        } else {
            dua2_skor2 = 0;
        }
        if (Chk92.isSelected() == true) {
            sembilan2_skor2 = 2;
        } else {
            sembilan2_skor2 = 0;
        }
        
        //skor 3
        if (Chksadar_skor3.isSelected() == true) {
            selain_skor3 = 3;
        } else {
            selain_skor3 = 0;
        }
        if (Chk35_skor3.isSelected() == true) {
            tiga5_skor3 = 3;
        } else {
            tiga5_skor3 = 0;
        }
        if (Chk92_skor3.isSelected() == true) {
            sembilan2_skor3 = 3;
        } else {
            sembilan2_skor3 = 0;
        }
    }
    
    private void hitungSkor() {
        cekSkor();        
        jlh_kesadaran = sdr_pnh_skor0 + selain_skor3;
        jlh_tekanan = seratus_skor0 + sembilan9_skor2;
        jlh_nadi = seratus1_skor0 + seratus2_skor1;
        jlh_respirasi = sembilan_bls_skor0 + dua_plh_skor1 + dua2_skor2;
        jlh_tempe = tiga5_skor0 + tiga5_skor3;
        jlh_satur = sembilan6_skor0 + sembilan4_skor1 + sembilan2_skor2 + sembilan2_skor3;
        
        totskor = jlh_kesadaran + jlh_tekanan + jlh_nadi + jlh_respirasi + jlh_tempe + jlh_satur;
        skortotal = String.valueOf(totskor);        
        
        if (totskor >= 5) {
            label_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/checked2.png")));
            label_24.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
            label_01.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
            
            Chkresus.setSelected(true);
            Chknon_resus.setSelected(false);
            Chkklinik.setSelected(false);
            Chkdoa.setSelected(false);
        } else if (totskor >= 2 && totskor <= 4) {
            label_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
            label_24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/checked1.png")));
            label_01.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
            
            Chkresus.setSelected(false);
            Chknon_resus.setSelected(true);
            Chkklinik.setSelected(false);
            Chkdoa.setSelected(false);
        } else if (totskor >= 0 && totskor <= 1) {
            label_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
            label_24.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
            label_01.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/checked2.png")));
            
            Chkresus.setSelected(false);
            Chknon_resus.setSelected(false);
            Chkklinik.setSelected(true);
            Chkdoa.setSelected(false);
        } else if (Chksadar_skor0.isSelected() == false && Chk100.isSelected() == false && Chk101.isSelected() == false && Chk19.isSelected() == false && Chk35_skor0.isSelected() == false && Chk96.isSelected() == false 
                && Chk102.isSelected() == false && Chk20.isSelected() == false && Chk94.isSelected() == false
                && Chk99.isSelected() == false && Chk22.isSelected() == false && Chk92.isSelected() == false
                && Chksadar_skor3.isSelected() == false && Chk35_skor3.isSelected() == false && Chk92_skor3.isSelected() == false) {
            label_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
            label_24.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
            label_01.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
            totskor = 0;
            skortotal = "";
            
            Chkresus.setSelected(false);
            Chknon_resus.setSelected(false);
            Chkklinik.setSelected(false);
            Chkdoa.setSelected(false);
        } else {
            label_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
            label_24.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
            label_01.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
            totskor = 0;
            skortotal = "";
            
            Chkresus.setSelected(false);
            Chknon_resus.setSelected(false);
            Chkklinik.setSelected(false);
            Chkdoa.setSelected(false);
        }
    }
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode1);
        try {
            if (pilihan == 1) {
                psTemp1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, tp.* from triase_ponek tp "
                        + "inner join reg_periksa rp on rp.no_rawat=tp.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "tp.keluhan_utama<>'' and p.no_rkm_medis like ? OR "
                        + "tp.keluhan_utama<>'' and p.nm_pasien like ? OR "
                        + "tp.keluhan_utama<>'' and tp.keluhan_utama like ? ORDER BY tp.tanggal desc limit 20");
            } else if (pilihan == 2) {
                psTemp2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, tp.* from triase_ponek tp "
                        + "inner join reg_periksa rp on rp.no_rawat=tp.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "tp.catatan<>'' and p.no_rkm_medis like ? OR "
                        + "tp.catatan<>'' and p.nm_pasien like ? OR "
                        + "tp.catatan<>'' and tp.catatan like ? ORDER BY tp.tanggal desc limit 20");
            }
            try {
                if (pilihan == 1) {
                    psTemp1.setString(1, "%" + TCari1.getText() + "%");
                    psTemp1.setString(2, "%" + TCari1.getText() + "%");
                    psTemp1.setString(3, "%" + TCari1.getText() + "%");
                    rsTemp1 = psTemp1.executeQuery();
                    while (rsTemp1.next()) {
                        tabMode1.addRow(new String[]{
                            rsTemp1.getString("no_rkm_medis"),
                            rsTemp1.getString("nm_pasien"),
                            rsTemp1.getString("keluhan_utama")
                        });
                    }
                } else if (pilihan == 2) {
                    psTemp2.setString(1, "%" + TCari1.getText() + "%");
                    psTemp2.setString(2, "%" + TCari1.getText() + "%");
                    psTemp2.setString(3, "%" + TCari1.getText() + "%");
                    rsTemp2 = psTemp2.executeQuery();
                    while (rsTemp2.next()) {
                        tabMode1.addRow(new String[]{
                            rsTemp2.getString("no_rkm_medis"),
                            rsTemp2.getString("nm_pasien"),
                            rsTemp2.getString("catatan")
                        });
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rsTemp1 != null) {
                    rsTemp1.close();
                } else if (rsTemp2 != null) {
                    rsTemp2.close();
                }
                
                if (psTemp1 != null) {
                    psTemp1.close();
                } else if (psTemp2 != null) {
                    psTemp2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copas() {
        if (pilihan == 1) {
            Tkeluhan_utama.setText(Ttemplate.getText());
        } else if (pilihan == 2) {
            Tcttn_khusus.setText(Ttemplate.getText());
        }
    }
}
