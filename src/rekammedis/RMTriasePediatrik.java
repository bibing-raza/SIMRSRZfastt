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
import simrskhanza.DlgNotepad;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author perpustakaan
 */
public final class RMTriasePediatrik extends javax.swing.JDialog {
    private DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);    
    private PreparedStatement ps, psx;
    private ResultSet rs, rsx;
    private int i = 0, x = 0;
    private String nip = "", autoanamnese = "", heteroanamnese = "", stabil = "", distress = "", gagalNafas = "", ssp = "",
            gagalJantung = "", shock = "", airwayLevel2 = "", airwayLevel3 = "", airwayLevel4 = "", airwayLevel5 = "", breathingLevel11 = "",
            breathingLevel22 = "", circulationLevel1 = "", circulationLevel2 = "", circulationLevel222 = "", circulationLevel3 = "",
            circulationLevel4 = "", circulationLevel44 = "", circulationLevel5 = "", circulationLevel55 = "", disabilityLevel1 = "",
            disabilityLevel11 = "", disabilityLevel111 = "", disabilityLevel2 = "", disabilityLevel22 = "", disabilityLevel222 = "",
            disabilityLevel4 = "", disabilityLevel44 = "", disabilityLevel5 = "", disabilityLevel55 = "", vas = "", kesimpulanLevel1 = "",
            kesimpulanLevel2 = "", kesimpulanLevel3 = "", kesimpulanLevel4 = "", kesimpulanLevel5 = "", trauma = "", nonTrauma = "", doa = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMTriasePediatrik(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.RM", "Nama Pasien", "Tgl. Lahir", "Tgl. Triase", "Cara Masuk", "Sudah Terpasang", "Alasan Kedatangan", "Rujukan Dari", "Dijemput Oleh",
            "Kendaraan", "Penjelasan Bkn. Ambulan", "Nama Pengantar", "No. Tlp. Pengantar", "Kasus", "kll_tunggal", "kll_tunggal_tmpt_kejadian", "kll_tunggal_tanggal",
            "kll_versus", "versus1", "versus2", "kll_tmpt_kejadian", "kll_tanggal", "jatuh", "ket_jatuh", "luka_bakar", "ket_luka_bakar", "trauma_listrik",
            "ket_trauma_listrik", "trauma_zat_kimia", "ket_trauma_zat_kimia", "trauma_lain", "ket_trauma_lain", "keluhan_utama", "pacs1", "pacs2", "pacs3",
            "pacs4", "kesadaran", "td", "nadi", "napas", "temperatur", "saturasi", "nyeri", "skor0_sadar_penuh", "skor0_100", "skor0_101", "skor0_19",
            "skor0_35_3", "skor0_96_100", "skor1_102", "skor1_20_21", "skor1_94_95", "skor2_99", "skor2_22", "skor2_92_93", "skor3_selain", "skor3_35_3", "skor3_92",
            "total_skor", "catatan", "pukul", "triase_resusitasi", "triase_non_resusitasi", "triase_klinik", "triase_doa", "nip_petugas",
            "tgl_kejadian_kll_tunggal", "tgl_kejadian_kll", "vas", "bb", "tb"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbTriase.setModel(tabMode);
        tbTriase.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTriase.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 73; i++) {
            TableColumn column = tbTriase.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(80);
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
                column.setPreferredWidth(250);
            } else if (i == 13) {
                column.setPreferredWidth(120);
            } else if (i == 14) {
                column.setPreferredWidth(90);
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
            }
        }
        tbTriase.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    nip = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();                    
                    TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    BtnPetugas.requestFocus();
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
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        FormTriase = new widget.InternalFrame();
        ScrollTriase1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel5 = new widget.Label();
        TtglLahir = new widget.TextBox();
        jLabel8 = new widget.Label();
        Tjenkel = new widget.TextBox();
        jLabel9 = new widget.Label();
        cmbGelang = new widget.ComboBox();
        jLabel10 = new widget.Label();
        cmbDiisi = new widget.ComboBox();
        jLabel11 = new widget.Label();
        TtglTiba = new widget.Tanggal();
        jLabel12 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel13 = new widget.Label();
        Tpembiayaan = new widget.TextBox();
        jLabel14 = new widget.Label();
        chkAutoanam = new widget.CekBox();
        chkHeteroanam = new widget.CekBox();
        jLabel15 = new widget.Label();
        Tnama = new widget.TextBox();
        jLabel16 = new widget.Label();
        Thubungan = new widget.TextBox();
        jLabel17 = new widget.Label();
        cmbRujukan = new widget.ComboBox();
        jLabel18 = new widget.Label();
        Tdari = new widget.TextBox();
        jLabel20 = new widget.Label();
        cmbTransportasi = new widget.ComboBox();
        jLabel22 = new widget.Label();
        Tkeluhan = new widget.TextBox();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        cmbPenampilan = new widget.ComboBox();
        PanelWall = new usu.widget.glass.PanelGlass();
        jLabel25 = new widget.Label();
        cmbUsaha = new widget.ComboBox();
        jLabel26 = new widget.Label();
        cmbSirkulasi = new widget.ComboBox();
        jLabel27 = new widget.Label();
        PanelWall1 = new usu.widget.glass.PanelGlass();
        chkStabil = new widget.CekBox();
        PanelWall2 = new usu.widget.glass.PanelGlass();
        chkDistres = new widget.CekBox();
        PanelWall3 = new usu.widget.glass.PanelGlass();
        chkGagalNafas = new widget.CekBox();
        PanelWall4 = new usu.widget.glass.PanelGlass();
        PanelWall5 = new usu.widget.glass.PanelGlass();
        PanelWall6 = new usu.widget.glass.PanelGlass();
        PanelWall7 = new usu.widget.glass.PanelGlass();
        chkSsp = new widget.CekBox();
        chkGagalJantung = new widget.CekBox();
        chkShock = new widget.CekBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel28 = new widget.Label();
        jLabel29 = new widget.Label();
        jLabel30 = new widget.Label();
        jLabel31 = new widget.Label();
        jLabel32 = new widget.Label();
        jLabel33 = new widget.Label();
        jLabel34 = new widget.Label();
        jLabel35 = new widget.Label();
        jLabel36 = new widget.Label();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        jLabel41 = new widget.Label();
        jLabel42 = new widget.Label();
        jLabel43 = new widget.Label();
        jLabel44 = new widget.Label();
        jLabel45 = new widget.Label();
        jLabel46 = new widget.Label();
        jLabel47 = new widget.Label();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel48 = new widget.Label();
        cmbAirway = new widget.ComboBox();
        chkAncaman = new widget.CekBox();
        chkBebas3 = new widget.CekBox();
        chkBebas4 = new widget.CekBox();
        chkBebas5 = new widget.CekBox();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel49 = new widget.Label();
        cmbBreaLevel1 = new widget.ComboBox();
        chkBreaLevel1 = new widget.CekBox();
        cmbBreaLevel2 = new widget.ComboBox();
        chkBreaLevel2 = new widget.CekBox();
        cmbBreaLevel3 = new widget.ComboBox();
        cmbBreaLevel4 = new widget.ComboBox();
        cmbBreaLevel5 = new widget.ComboBox();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel50 = new widget.Label();
        chkCircuLevel1 = new widget.CekBox();
        chkCircuAkralD = new widget.CekBox();
        cmbCircuLevel2 = new widget.ComboBox();
        chkCircuCrt = new widget.CekBox();
        chkCircuAkralHL3 = new widget.CekBox();
        chkCircuSadar = new widget.CekBox();
        chkCircuGcs = new widget.CekBox();
        chkCircuNadi = new widget.CekBox();
        chkCircuAkralHL5 = new widget.CekBox();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel51 = new widget.Label();
        chkDisTidak = new widget.CekBox();
        chkDisKejang = new widget.CekBox();
        chkDisGcs9 = new widget.CekBox();
        chkDisRespon = new widget.CekBox();
        chkDisGelisah = new widget.CekBox();
        chkDisGcs912 = new widget.CekBox();
        jLabel52 = new widget.Label();
        cmbDisLevel3 = new widget.ComboBox();
        chkDisSadarL4 = new widget.CekBox();
        chkDisGcsL4 = new widget.CekBox();
        chkDisSadarL5 = new widget.CekBox();
        chkDisGcsL5 = new widget.CekBox();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel53 = new widget.Label();
        cmbSumLevel3 = new widget.ComboBox();
        cmbSumLevel4 = new widget.ComboBox();
        cmbSumLevel5 = new widget.ComboBox();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel54 = new widget.Label();
        chkVas = new widget.CekBox();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel55 = new widget.Label();
        chkKesLevel1 = new widget.CekBox();
        chkKesLevel2 = new widget.CekBox();
        chkKesLevel3 = new widget.CekBox();
        chkKesLevel4 = new widget.CekBox();
        chkKesLevel5 = new widget.CekBox();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel56 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel57 = new widget.Label();
        Trespi = new widget.TextBox();
        jLabel58 = new widget.Label();
        Tspo = new widget.TextBox();
        jLabel59 = new widget.Label();
        jLabel60 = new widget.Label();
        jLabel61 = new widget.Label();
        cmbUmur = new widget.ComboBox();
        jLabel62 = new widget.Label();
        TumurNadi = new widget.TextBox();
        jLabel63 = new widget.Label();
        TumurNafas = new widget.TextBox();
        jLabel64 = new widget.Label();
        TumurTemp = new widget.TextBox();
        jLabel65 = new widget.Label();
        TumurSpo = new widget.TextBox();
        jLabel66 = new widget.Label();
        jLabel67 = new widget.Label();
        jLabel68 = new widget.Label();
        chkTrauma = new widget.CekBox();
        chkNonTrauma = new widget.CekBox();
        chkDoa = new widget.CekBox();
        jLabel69 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel70 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        Tcatatan = new widget.TextArea();
        jLabel71 = new widget.Label();
        jLabel72 = new widget.Label();
        TtglKeputusan = new widget.Tanggal();
        jLabel73 = new widget.Label();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        cmbKeputusan = new widget.ComboBox();
        jLabel74 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Triase Pediatrik ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1388));
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
        TPasien.setBounds(332, 10, 407, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(260, 10, 70, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tgl. Lahir :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 130, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setBackground(new java.awt.Color(245, 250, 240));
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        FormInput.add(TtglLahir);
        TtglLahir.setBounds(136, 38, 140, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Jns. Kelamin :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(280, 38, 80, 23);

        Tjenkel.setEditable(false);
        Tjenkel.setBackground(new java.awt.Color(245, 250, 240));
        Tjenkel.setForeground(new java.awt.Color(0, 0, 0));
        Tjenkel.setName("Tjenkel"); // NOI18N
        FormInput.add(Tjenkel);
        Tjenkel.setBounds(366, 38, 90, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Gelang :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(460, 38, 55, 23);

        cmbGelang.setForeground(new java.awt.Color(0, 0, 0));
        cmbGelang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Biru", "Pink" }));
        cmbGelang.setName("cmbGelang"); // NOI18N
        cmbGelang.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbGelang);
        cmbGelang.setBounds(520, 38, 55, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Disi Oleh :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 66, 130, 23);

        cmbDiisi.setForeground(new java.awt.Color(0, 0, 0));
        cmbDiisi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Dokter", "Perawat" }));
        cmbDiisi.setName("cmbDiisi"); // NOI18N
        cmbDiisi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbDiisi);
        cmbDiisi.setBounds(136, 66, 75, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Saat Tiba Di IGD : Tanggal :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(210, 66, 160, 23);

        TtglTiba.setEditable(false);
        TtglTiba.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-02-2025" }));
        TtglTiba.setDisplayFormat("dd-MM-yyyy");
        TtglTiba.setName("TtglTiba"); // NOI18N
        TtglTiba.setOpaque(false);
        TtglTiba.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglTiba);
        TtglTiba.setBounds(375, 66, 90, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Jam :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(465, 66, 50, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(520, 66, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(570, 66, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(621, 66, 45, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Pembiayaan :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 94, 130, 23);

        Tpembiayaan.setEditable(false);
        Tpembiayaan.setBackground(new java.awt.Color(245, 250, 240));
        Tpembiayaan.setForeground(new java.awt.Color(0, 0, 0));
        Tpembiayaan.setName("Tpembiayaan"); // NOI18N
        FormInput.add(Tpembiayaan);
        Tpembiayaan.setBounds(136, 94, 400, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Sumber Data :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 122, 130, 23);

        chkAutoanam.setBackground(new java.awt.Color(255, 255, 250));
        chkAutoanam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAutoanam.setForeground(new java.awt.Color(0, 0, 0));
        chkAutoanam.setText("Autoanamnese");
        chkAutoanam.setBorderPainted(true);
        chkAutoanam.setBorderPaintedFlat(true);
        chkAutoanam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAutoanam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAutoanam.setName("chkAutoanam"); // NOI18N
        chkAutoanam.setOpaque(false);
        chkAutoanam.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAutoanam);
        chkAutoanam.setBounds(136, 122, 105, 23);

        chkHeteroanam.setBackground(new java.awt.Color(255, 255, 250));
        chkHeteroanam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHeteroanam.setForeground(new java.awt.Color(0, 0, 0));
        chkHeteroanam.setText("Heteroanamnese");
        chkHeteroanam.setBorderPainted(true);
        chkHeteroanam.setBorderPaintedFlat(true);
        chkHeteroanam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHeteroanam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHeteroanam.setName("chkHeteroanam"); // NOI18N
        chkHeteroanam.setOpaque(false);
        chkHeteroanam.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHeteroanam);
        chkHeteroanam.setBounds(250, 122, 110, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Nama :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 150, 130, 23);

        Tnama.setBackground(new java.awt.Color(245, 250, 240));
        Tnama.setForeground(new java.awt.Color(0, 0, 0));
        Tnama.setName("Tnama"); // NOI18N
        Tnama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnamaKeyPressed(evt);
            }
        });
        FormInput.add(Tnama);
        Tnama.setBounds(136, 150, 605, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Hub. Dengan Pasien :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 178, 130, 23);

        Thubungan.setBackground(new java.awt.Color(245, 250, 240));
        Thubungan.setForeground(new java.awt.Color(0, 0, 0));
        Thubungan.setName("Thubungan"); // NOI18N
        Thubungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThubunganKeyPressed(evt);
            }
        });
        FormInput.add(Thubungan);
        Thubungan.setBounds(136, 178, 280, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Rujukan :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 206, 130, 23);

        cmbRujukan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbRujukan.setName("cmbRujukan"); // NOI18N
        cmbRujukan.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRujukanActionPerformed(evt);
            }
        });
        FormInput.add(cmbRujukan);
        cmbRujukan.setBounds(136, 206, 60, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Dari :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(200, 206, 40, 23);

        Tdari.setBackground(new java.awt.Color(245, 250, 240));
        Tdari.setForeground(new java.awt.Color(0, 0, 0));
        Tdari.setName("Tdari"); // NOI18N
        Tdari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdariKeyPressed(evt);
            }
        });
        FormInput.add(Tdari);
        Tdari.setBounds(246, 206, 495, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Transportasi :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(0, 234, 130, 23);

        cmbTransportasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbTransportasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ambulance", "Kendaraan Pribadi" }));
        cmbTransportasi.setName("cmbTransportasi"); // NOI18N
        cmbTransportasi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbTransportasi);
        cmbTransportasi.setBounds(136, 234, 120, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Keluhan Utama :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(260, 234, 90, 23);

        Tkeluhan.setBackground(new java.awt.Color(245, 250, 240));
        Tkeluhan.setForeground(new java.awt.Color(0, 0, 0));
        Tkeluhan.setName("Tkeluhan"); // NOI18N
        Tkeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeluhanKeyPressed(evt);
            }
        });
        FormInput.add(Tkeluhan);
        Tkeluhan.setBounds(356, 234, 385, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("PAT (Pediatric Assesment Triangle)");
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 273, 230, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Penampilan :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 300, 130, 23);

        cmbPenampilan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenampilan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Gerakan Tidak Aktif", "Lumpuh", "Penurunan Kesadaran", "Penurunan Interaksi Dengan Pengasuh", "Menangis Terus Menerus", "Gelisah", "Pandangan Kosong/Tidak Fokus", "Tidak Menangis", "Suara Lemah" }));
        cmbPenampilan.setName("cmbPenampilan"); // NOI18N
        cmbPenampilan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbPenampilan);
        cmbPenampilan.setBounds(136, 300, 220, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/triangle.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(360, 300, 90, 80);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Usaha Nafas :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(455, 300, 80, 23);

        cmbUsaha.setForeground(new java.awt.Color(0, 0, 0));
        cmbUsaha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mengorok", "Merintih", "Stridor", "Suara Parau", "Sniffing Position", "Nafas Cuping Hidung", "Tripoding Position", "Kepala Terayun", "Menolak Berbaring", "Retraksi" }));
        cmbUsaha.setName("cmbUsaha"); // NOI18N
        cmbUsaha.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbUsaha);
        cmbUsaha.setBounds(540, 300, 135, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Sirkulasi Kulit :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(310, 390, 80, 23);

        cmbSirkulasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbSirkulasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kulit Dan Mukosa Pucat", "Kulit Bercak Kebiruan", "Kulit Dan Mukosa Biru" }));
        cmbSirkulasi.setName("cmbSirkulasi"); // NOI18N
        cmbSirkulasi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbSirkulasi);
        cmbSirkulasi.setBounds(397, 390, 145, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Interpretasi PAT :");
        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(0, 430, 130, 23);

        PanelWall1.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/pat_stabil.jpg"))); // NOI18N
        PanelWall1.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall1.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall1.setRound(false);
        PanelWall1.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall1.setLayout(null);
        FormInput.add(PanelWall1);
        PanelWall1.setBounds(136, 430, 60, 50);

        chkStabil.setBackground(new java.awt.Color(255, 255, 250));
        chkStabil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkStabil);
        chkStabil.setForeground(new java.awt.Color(0, 0, 0));
        chkStabil.setText("Stabil");
        chkStabil.setBorderPainted(true);
        chkStabil.setBorderPaintedFlat(true);
        chkStabil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkStabil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkStabil.setName("chkStabil"); // NOI18N
        chkStabil.setOpaque(false);
        chkStabil.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkStabil);
        chkStabil.setBounds(200, 445, 70, 23);

        PanelWall2.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall2.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/pat_distress.jpg"))); // NOI18N
        PanelWall2.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall2.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall2.setRound(false);
        PanelWall2.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall2.setLayout(null);
        FormInput.add(PanelWall2);
        PanelWall2.setBounds(136, 488, 60, 50);

        chkDistres.setBackground(new java.awt.Color(255, 255, 250));
        chkDistres.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkDistres);
        chkDistres.setForeground(new java.awt.Color(0, 0, 0));
        chkDistres.setText("Distress Pernafasan");
        chkDistres.setBorderPainted(true);
        chkDistres.setBorderPaintedFlat(true);
        chkDistres.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDistres.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDistres.setName("chkDistres"); // NOI18N
        chkDistres.setOpaque(false);
        chkDistres.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDistres);
        chkDistres.setBounds(200, 501, 130, 23);

        PanelWall3.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall3.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/pat_gagal_nafas.jpg"))); // NOI18N
        PanelWall3.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall3.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall3.setRound(false);
        PanelWall3.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall3.setLayout(null);
        FormInput.add(PanelWall3);
        PanelWall3.setBounds(136, 546, 60, 50);

        chkGagalNafas.setBackground(new java.awt.Color(255, 255, 250));
        chkGagalNafas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkGagalNafas);
        chkGagalNafas.setForeground(new java.awt.Color(0, 0, 0));
        chkGagalNafas.setText("Gagal Nafas");
        chkGagalNafas.setBorderPainted(true);
        chkGagalNafas.setBorderPaintedFlat(true);
        chkGagalNafas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGagalNafas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGagalNafas.setName("chkGagalNafas"); // NOI18N
        chkGagalNafas.setOpaque(false);
        chkGagalNafas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGagalNafas);
        chkGagalNafas.setBounds(200, 557, 90, 23);

        PanelWall4.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall4.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/pat_ssp.jpg"))); // NOI18N
        PanelWall4.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall4.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall4.setRound(false);
        PanelWall4.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall4.setLayout(null);
        FormInput.add(PanelWall4);
        PanelWall4.setBounds(350, 430, 60, 50);

        PanelWall5.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall5.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/pat_gagal_jantung.jpg"))); // NOI18N
        PanelWall5.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall5.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall5.setRound(false);
        PanelWall5.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall5.setLayout(null);
        FormInput.add(PanelWall5);
        PanelWall5.setBounds(350, 488, 60, 50);

        PanelWall6.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall6.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/pat_shock1.jpg"))); // NOI18N
        PanelWall6.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall6.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall6.setRound(false);
        PanelWall6.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall6.setLayout(null);
        FormInput.add(PanelWall6);
        PanelWall6.setBounds(350, 546, 60, 50);

        PanelWall7.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall7.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/pat_shock2.jpg"))); // NOI18N
        PanelWall7.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall7.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall7.setRound(false);
        PanelWall7.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall7.setLayout(null);
        FormInput.add(PanelWall7);
        PanelWall7.setBounds(405, 546, 60, 50);

        chkSsp.setBackground(new java.awt.Color(255, 255, 250));
        chkSsp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkSsp);
        chkSsp.setForeground(new java.awt.Color(0, 0, 0));
        chkSsp.setText("SSP/Metabolik");
        chkSsp.setBorderPainted(true);
        chkSsp.setBorderPaintedFlat(true);
        chkSsp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSsp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSsp.setName("chkSsp"); // NOI18N
        chkSsp.setOpaque(false);
        chkSsp.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSsp);
        chkSsp.setBounds(415, 445, 100, 23);

        chkGagalJantung.setBackground(new java.awt.Color(255, 255, 250));
        chkGagalJantung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkGagalJantung);
        chkGagalJantung.setForeground(new java.awt.Color(0, 0, 0));
        chkGagalJantung.setText("Gagal Jantung Pernafasan");
        chkGagalJantung.setBorderPainted(true);
        chkGagalJantung.setBorderPaintedFlat(true);
        chkGagalJantung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGagalJantung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGagalJantung.setName("chkGagalJantung"); // NOI18N
        chkGagalJantung.setOpaque(false);
        chkGagalJantung.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGagalJantung);
        chkGagalJantung.setBounds(415, 501, 160, 23);

        chkShock.setBackground(new java.awt.Color(255, 255, 250));
        chkShock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkShock);
        chkShock.setForeground(new java.awt.Color(0, 0, 0));
        chkShock.setText("Shock");
        chkShock.setBorderPainted(true);
        chkShock.setBorderPaintedFlat(true);
        chkShock.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkShock.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkShock.setName("chkShock"); // NOI18N
        chkShock.setOpaque(false);
        chkShock.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkShock);
        chkShock.setBounds(470, 557, 60, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 420, 880, 1);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 268, 880, 1);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 607, 880, 1);

        jLabel28.setBackground(new java.awt.Color(255, 0, 51));
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("LEVEL 1");
        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setOpaque(true);
        FormInput.add(jLabel28);
        jLabel28.setBounds(136, 615, 130, 23);

        jLabel29.setBackground(new java.awt.Color(255, 0, 51));
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("RESUSITASI /");
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setOpaque(true);
        FormInput.add(jLabel29);
        jLabel29.setBounds(136, 632, 130, 23);

        jLabel30.setBackground(new java.awt.Color(255, 0, 51));
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("(RED ZONE)");
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setName("jLabel30"); // NOI18N
        jLabel30.setOpaque(true);
        FormInput.add(jLabel30);
        jLabel30.setBounds(136, 649, 130, 23);

        jLabel31.setBackground(new java.awt.Color(255, 0, 51));
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Segera");
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setName("jLabel31"); // NOI18N
        jLabel31.setOpaque(true);
        FormInput.add(jLabel31);
        jLabel31.setBounds(136, 666, 130, 23);

        jLabel32.setBackground(new java.awt.Color(255, 0, 51));
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("LEVEL 2");
        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setName("jLabel32"); // NOI18N
        jLabel32.setOpaque(true);
        FormInput.add(jLabel32);
        jLabel32.setBounds(271, 615, 130, 23);

        jLabel33.setBackground(new java.awt.Color(255, 0, 51));
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("EMERGENSI");
        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setName("jLabel33"); // NOI18N
        jLabel33.setOpaque(true);
        FormInput.add(jLabel33);
        jLabel33.setBounds(271, 632, 130, 23);

        jLabel34.setBackground(new java.awt.Color(255, 0, 51));
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("(RED ZONE)");
        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setName("jLabel34"); // NOI18N
        jLabel34.setOpaque(true);
        FormInput.add(jLabel34);
        jLabel34.setBounds(271, 649, 130, 23);

        jLabel35.setBackground(new java.awt.Color(255, 0, 51));
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("10 Menit");
        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setName("jLabel35"); // NOI18N
        jLabel35.setOpaque(true);
        FormInput.add(jLabel35);
        jLabel35.setBounds(271, 666, 130, 23);

        jLabel36.setBackground(new java.awt.Color(255, 255, 0));
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("LEVEL 3");
        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setName("jLabel36"); // NOI18N
        jLabel36.setOpaque(true);
        FormInput.add(jLabel36);
        jLabel36.setBounds(407, 615, 130, 23);

        jLabel37.setBackground(new java.awt.Color(255, 255, 0));
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("URGENT");
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setName("jLabel37"); // NOI18N
        jLabel37.setOpaque(true);
        FormInput.add(jLabel37);
        jLabel37.setBounds(407, 632, 130, 23);

        jLabel38.setBackground(new java.awt.Color(255, 255, 0));
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("(YELLOW ZONE)");
        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setName("jLabel38"); // NOI18N
        jLabel38.setOpaque(true);
        FormInput.add(jLabel38);
        jLabel38.setBounds(407, 649, 130, 23);

        jLabel39.setBackground(new java.awt.Color(255, 255, 0));
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("30 Menit");
        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setName("jLabel39"); // NOI18N
        jLabel39.setOpaque(true);
        FormInput.add(jLabel39);
        jLabel39.setBounds(407, 666, 130, 23);

        jLabel40.setBackground(new java.awt.Color(255, 255, 0));
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("LEVEL 4");
        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setName("jLabel40"); // NOI18N
        jLabel40.setOpaque(true);
        FormInput.add(jLabel40);
        jLabel40.setBounds(544, 615, 140, 23);

        jLabel41.setBackground(new java.awt.Color(255, 255, 0));
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("URGENT");
        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setName("jLabel41"); // NOI18N
        jLabel41.setOpaque(true);
        FormInput.add(jLabel41);
        jLabel41.setBounds(544, 632, 140, 23);

        jLabel42.setBackground(new java.awt.Color(255, 255, 0));
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("(YELLOW ZONE)");
        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setName("jLabel42"); // NOI18N
        jLabel42.setOpaque(true);
        FormInput.add(jLabel42);
        jLabel42.setBounds(544, 649, 140, 23);

        jLabel43.setBackground(new java.awt.Color(255, 255, 0));
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("60 Menit");
        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setName("jLabel43"); // NOI18N
        jLabel43.setOpaque(true);
        FormInput.add(jLabel43);
        jLabel43.setBounds(544, 666, 140, 23);

        jLabel44.setBackground(new java.awt.Color(0, 204, 51));
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("LEVEL 5");
        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setName("jLabel44"); // NOI18N
        jLabel44.setOpaque(true);
        FormInput.add(jLabel44);
        jLabel44.setBounds(692, 615, 130, 23);

        jLabel45.setBackground(new java.awt.Color(0, 204, 51));
        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("FALSE EMERGENCY");
        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setName("jLabel45"); // NOI18N
        jLabel45.setOpaque(true);
        FormInput.add(jLabel45);
        jLabel45.setBounds(692, 632, 130, 23);

        jLabel46.setBackground(new java.awt.Color(0, 204, 51));
        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("(GREEN ZONE)");
        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setName("jLabel46"); // NOI18N
        jLabel46.setOpaque(true);
        FormInput.add(jLabel46);
        jLabel46.setBounds(692, 649, 130, 23);

        jLabel47.setBackground(new java.awt.Color(0, 204, 51));
        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("120 Menit");
        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setName("jLabel47"); // NOI18N
        jLabel47.setOpaque(true);
        FormInput.add(jLabel47);
        jLabel47.setBounds(692, 666, 130, 23);

        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(50, 693, 800, 1);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Airway");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(0, 698, 120, 23);

        cmbAirway.setForeground(new java.awt.Color(0, 0, 0));
        cmbAirway.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Terintubasi", "Sumbatan" }));
        cmbAirway.setName("cmbAirway"); // NOI18N
        cmbAirway.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbAirway);
        cmbAirway.setBounds(142, 698, 85, 23);

        chkAncaman.setBackground(new java.awt.Color(255, 255, 250));
        chkAncaman.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAncaman.setForeground(new java.awt.Color(0, 0, 0));
        chkAncaman.setText("Ancaman");
        chkAncaman.setBorderPainted(true);
        chkAncaman.setBorderPaintedFlat(true);
        chkAncaman.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAncaman.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAncaman.setName("chkAncaman"); // NOI18N
        chkAncaman.setOpaque(false);
        chkAncaman.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAncaman);
        chkAncaman.setBounds(283, 698, 90, 23);

        chkBebas3.setBackground(new java.awt.Color(255, 255, 250));
        chkBebas3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBebas3.setForeground(new java.awt.Color(0, 0, 0));
        chkBebas3.setText("Bebas");
        chkBebas3.setBorderPainted(true);
        chkBebas3.setBorderPaintedFlat(true);
        chkBebas3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBebas3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBebas3.setName("chkBebas3"); // NOI18N
        chkBebas3.setOpaque(false);
        chkBebas3.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBebas3);
        chkBebas3.setBounds(420, 698, 90, 23);

        chkBebas4.setBackground(new java.awt.Color(255, 255, 250));
        chkBebas4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBebas4.setForeground(new java.awt.Color(0, 0, 0));
        chkBebas4.setText("Bebas");
        chkBebas4.setBorderPainted(true);
        chkBebas4.setBorderPaintedFlat(true);
        chkBebas4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBebas4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBebas4.setName("chkBebas4"); // NOI18N
        chkBebas4.setOpaque(false);
        chkBebas4.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBebas4);
        chkBebas4.setBounds(555, 698, 90, 23);

        chkBebas5.setBackground(new java.awt.Color(255, 255, 250));
        chkBebas5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBebas5.setForeground(new java.awt.Color(0, 0, 0));
        chkBebas5.setText("Bebas");
        chkBebas5.setBorderPainted(true);
        chkBebas5.setBorderPaintedFlat(true);
        chkBebas5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBebas5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBebas5.setName("chkBebas5"); // NOI18N
        chkBebas5.setOpaque(false);
        chkBebas5.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBebas5);
        chkBebas5.setBounds(705, 698, 90, 23);

        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(50, 726, 800, 1);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Breathing");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(0, 732, 120, 23);

        cmbBreaLevel1.setForeground(new java.awt.Color(0, 0, 0));
        cmbBreaLevel1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Apnoe", "Ventilator" }));
        cmbBreaLevel1.setName("cmbBreaLevel1"); // NOI18N
        cmbBreaLevel1.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBreaLevel1);
        cmbBreaLevel1.setBounds(142, 732, 80, 23);

        chkBreaLevel1.setBackground(new java.awt.Color(255, 255, 250));
        chkBreaLevel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBreaLevel1.setForeground(new java.awt.Color(0, 0, 0));
        chkBreaLevel1.setText("SPO2 : < 90 %");
        chkBreaLevel1.setBorderPainted(true);
        chkBreaLevel1.setBorderPaintedFlat(true);
        chkBreaLevel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBreaLevel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBreaLevel1.setName("chkBreaLevel1"); // NOI18N
        chkBreaLevel1.setOpaque(false);
        chkBreaLevel1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBreaLevel1);
        chkBreaLevel1.setBounds(142, 760, 110, 23);

        cmbBreaLevel2.setForeground(new java.awt.Color(0, 0, 0));
        cmbBreaLevel2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Spontan", "Takhipnue", "Bradipnue" }));
        cmbBreaLevel2.setName("cmbBreaLevel2"); // NOI18N
        cmbBreaLevel2.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBreaLevel2);
        cmbBreaLevel2.setBounds(283, 732, 85, 23);

        chkBreaLevel2.setBackground(new java.awt.Color(255, 255, 250));
        chkBreaLevel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBreaLevel2.setForeground(new java.awt.Color(0, 0, 0));
        chkBreaLevel2.setText("SPO2 : 90 - 92 %");
        chkBreaLevel2.setBorderPainted(true);
        chkBreaLevel2.setBorderPaintedFlat(true);
        chkBreaLevel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBreaLevel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBreaLevel2.setName("chkBreaLevel2"); // NOI18N
        chkBreaLevel2.setOpaque(false);
        chkBreaLevel2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBreaLevel2);
        chkBreaLevel2.setBounds(283, 760, 116, 23);

        cmbBreaLevel3.setForeground(new java.awt.Color(0, 0, 0));
        cmbBreaLevel3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Spontan", "Normal" }));
        cmbBreaLevel3.setName("cmbBreaLevel3"); // NOI18N
        cmbBreaLevel3.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBreaLevel3);
        cmbBreaLevel3.setBounds(420, 732, 75, 23);

        cmbBreaLevel4.setForeground(new java.awt.Color(0, 0, 0));
        cmbBreaLevel4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Spontan", "Normal" }));
        cmbBreaLevel4.setName("cmbBreaLevel4"); // NOI18N
        cmbBreaLevel4.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBreaLevel4);
        cmbBreaLevel4.setBounds(555, 732, 75, 23);

        cmbBreaLevel5.setForeground(new java.awt.Color(0, 0, 0));
        cmbBreaLevel5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Spontan", "Normal" }));
        cmbBreaLevel5.setName("cmbBreaLevel5"); // NOI18N
        cmbBreaLevel5.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBreaLevel5);
        cmbBreaLevel5.setBounds(705, 732, 75, 23);

        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput.add(jSeparator6);
        jSeparator6.setBounds(50, 788, 800, 1);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Circulation");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(0, 793, 120, 23);

        chkCircuLevel1.setBackground(new java.awt.Color(255, 255, 250));
        chkCircuLevel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCircuLevel1.setForeground(new java.awt.Color(0, 0, 0));
        chkCircuLevel1.setText("Henti Jantung");
        chkCircuLevel1.setBorderPainted(true);
        chkCircuLevel1.setBorderPaintedFlat(true);
        chkCircuLevel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCircuLevel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCircuLevel1.setName("chkCircuLevel1"); // NOI18N
        chkCircuLevel1.setOpaque(false);
        chkCircuLevel1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCircuLevel1);
        chkCircuLevel1.setBounds(142, 793, 110, 23);

        chkCircuAkralD.setBackground(new java.awt.Color(255, 255, 250));
        chkCircuAkralD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCircuAkralD.setForeground(new java.awt.Color(0, 0, 0));
        chkCircuAkralD.setText("Akral Dingin");
        chkCircuAkralD.setBorderPainted(true);
        chkCircuAkralD.setBorderPaintedFlat(true);
        chkCircuAkralD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCircuAkralD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCircuAkralD.setName("chkCircuAkralD"); // NOI18N
        chkCircuAkralD.setOpaque(false);
        chkCircuAkralD.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCircuAkralD);
        chkCircuAkralD.setBounds(283, 793, 110, 23);

        cmbCircuLevel2.setForeground(new java.awt.Color(0, 0, 0));
        cmbCircuLevel2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bradikardi", "Takhikardi" }));
        cmbCircuLevel2.setName("cmbCircuLevel2"); // NOI18N
        cmbCircuLevel2.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbCircuLevel2);
        cmbCircuLevel2.setBounds(283, 821, 80, 23);

        chkCircuCrt.setBackground(new java.awt.Color(255, 255, 250));
        chkCircuCrt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCircuCrt.setForeground(new java.awt.Color(0, 0, 0));
        chkCircuCrt.setText("CRT > 2 Detik");
        chkCircuCrt.setBorderPainted(true);
        chkCircuCrt.setBorderPaintedFlat(true);
        chkCircuCrt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCircuCrt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCircuCrt.setName("chkCircuCrt"); // NOI18N
        chkCircuCrt.setOpaque(false);
        chkCircuCrt.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCircuCrt);
        chkCircuCrt.setBounds(283, 849, 110, 23);

        chkCircuAkralHL3.setBackground(new java.awt.Color(255, 255, 250));
        chkCircuAkralHL3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCircuAkralHL3.setForeground(new java.awt.Color(0, 0, 0));
        chkCircuAkralHL3.setText("Akral Hangat");
        chkCircuAkralHL3.setBorderPainted(true);
        chkCircuAkralHL3.setBorderPaintedFlat(true);
        chkCircuAkralHL3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCircuAkralHL3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCircuAkralHL3.setName("chkCircuAkralHL3"); // NOI18N
        chkCircuAkralHL3.setOpaque(false);
        chkCircuAkralHL3.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCircuAkralHL3);
        chkCircuAkralHL3.setBounds(420, 793, 110, 23);

        chkCircuSadar.setBackground(new java.awt.Color(255, 255, 250));
        chkCircuSadar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCircuSadar.setForeground(new java.awt.Color(0, 0, 0));
        chkCircuSadar.setText("Sadar");
        chkCircuSadar.setBorderPainted(true);
        chkCircuSadar.setBorderPaintedFlat(true);
        chkCircuSadar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCircuSadar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCircuSadar.setName("chkCircuSadar"); // NOI18N
        chkCircuSadar.setOpaque(false);
        chkCircuSadar.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCircuSadar);
        chkCircuSadar.setBounds(555, 793, 70, 23);

        chkCircuGcs.setBackground(new java.awt.Color(255, 255, 250));
        chkCircuGcs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCircuGcs.setForeground(new java.awt.Color(0, 0, 0));
        chkCircuGcs.setText("GCS : 15");
        chkCircuGcs.setBorderPainted(true);
        chkCircuGcs.setBorderPaintedFlat(true);
        chkCircuGcs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCircuGcs.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCircuGcs.setName("chkCircuGcs"); // NOI18N
        chkCircuGcs.setOpaque(false);
        chkCircuGcs.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCircuGcs);
        chkCircuGcs.setBounds(555, 821, 70, 23);

        chkCircuNadi.setBackground(new java.awt.Color(255, 255, 250));
        chkCircuNadi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCircuNadi.setForeground(new java.awt.Color(0, 0, 0));
        chkCircuNadi.setText("Nadi Teraba Kuat");
        chkCircuNadi.setBorderPainted(true);
        chkCircuNadi.setBorderPaintedFlat(true);
        chkCircuNadi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCircuNadi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCircuNadi.setName("chkCircuNadi"); // NOI18N
        chkCircuNadi.setOpaque(false);
        chkCircuNadi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCircuNadi);
        chkCircuNadi.setBounds(705, 793, 120, 23);

        chkCircuAkralHL5.setBackground(new java.awt.Color(255, 255, 250));
        chkCircuAkralHL5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCircuAkralHL5.setForeground(new java.awt.Color(0, 0, 0));
        chkCircuAkralHL5.setText("Akral Hangat");
        chkCircuAkralHL5.setBorderPainted(true);
        chkCircuAkralHL5.setBorderPaintedFlat(true);
        chkCircuAkralHL5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCircuAkralHL5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCircuAkralHL5.setName("chkCircuAkralHL5"); // NOI18N
        chkCircuAkralHL5.setOpaque(false);
        chkCircuAkralHL5.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCircuAkralHL5);
        chkCircuAkralHL5.setBounds(705, 821, 100, 23);

        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput.add(jSeparator7);
        jSeparator7.setBounds(50, 878, 800, 1);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Disability");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(0, 884, 120, 23);

        chkDisTidak.setBackground(new java.awt.Color(255, 255, 250));
        chkDisTidak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDisTidak.setForeground(new java.awt.Color(0, 0, 0));
        chkDisTidak.setText("Tidak Berespon");
        chkDisTidak.setBorderPainted(true);
        chkDisTidak.setBorderPaintedFlat(true);
        chkDisTidak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDisTidak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDisTidak.setName("chkDisTidak"); // NOI18N
        chkDisTidak.setOpaque(false);
        chkDisTidak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDisTidak);
        chkDisTidak.setBounds(142, 884, 110, 23);

        chkDisKejang.setBackground(new java.awt.Color(255, 255, 250));
        chkDisKejang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDisKejang.setForeground(new java.awt.Color(0, 0, 0));
        chkDisKejang.setText("Kejang");
        chkDisKejang.setBorderPainted(true);
        chkDisKejang.setBorderPaintedFlat(true);
        chkDisKejang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDisKejang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDisKejang.setName("chkDisKejang"); // NOI18N
        chkDisKejang.setOpaque(false);
        chkDisKejang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDisKejang);
        chkDisKejang.setBounds(142, 912, 80, 23);

        chkDisGcs9.setBackground(new java.awt.Color(255, 255, 250));
        chkDisGcs9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDisGcs9.setForeground(new java.awt.Color(0, 0, 0));
        chkDisGcs9.setText("GCS < 9");
        chkDisGcs9.setBorderPainted(true);
        chkDisGcs9.setBorderPaintedFlat(true);
        chkDisGcs9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDisGcs9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDisGcs9.setName("chkDisGcs9"); // NOI18N
        chkDisGcs9.setOpaque(false);
        chkDisGcs9.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDisGcs9);
        chkDisGcs9.setBounds(142, 940, 80, 23);

        chkDisRespon.setBackground(new java.awt.Color(255, 255, 250));
        chkDisRespon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDisRespon.setForeground(new java.awt.Color(0, 0, 0));
        chkDisRespon.setText("Respon Dengan");
        chkDisRespon.setBorderPainted(true);
        chkDisRespon.setBorderPaintedFlat(true);
        chkDisRespon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDisRespon.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDisRespon.setName("chkDisRespon"); // NOI18N
        chkDisRespon.setOpaque(false);
        chkDisRespon.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDisRespon);
        chkDisRespon.setBounds(283, 884, 110, 23);

        chkDisGelisah.setBackground(new java.awt.Color(255, 255, 250));
        chkDisGelisah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDisGelisah.setForeground(new java.awt.Color(0, 0, 0));
        chkDisGelisah.setText("Gelisah");
        chkDisGelisah.setBorderPainted(true);
        chkDisGelisah.setBorderPaintedFlat(true);
        chkDisGelisah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDisGelisah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDisGelisah.setName("chkDisGelisah"); // NOI18N
        chkDisGelisah.setOpaque(false);
        chkDisGelisah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDisGelisah);
        chkDisGelisah.setBounds(283, 927, 80, 23);

        chkDisGcs912.setBackground(new java.awt.Color(255, 255, 250));
        chkDisGcs912.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDisGcs912.setForeground(new java.awt.Color(0, 0, 0));
        chkDisGcs912.setText("GCS 9 - 12");
        chkDisGcs912.setBorderPainted(true);
        chkDisGcs912.setBorderPaintedFlat(true);
        chkDisGcs912.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDisGcs912.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDisGcs912.setName("chkDisGcs912"); // NOI18N
        chkDisGcs912.setOpaque(false);
        chkDisGcs912.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDisGcs912);
        chkDisGcs912.setBounds(283, 955, 80, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("Rangsangan Nyeri");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(304, 901, 95, 23);

        cmbDisLevel3.setForeground(new java.awt.Color(0, 0, 0));
        cmbDisLevel3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Respon Verbal", "Apatis", "Somnolen", "GCS 13-14" }));
        cmbDisLevel3.setName("cmbDisLevel3"); // NOI18N
        cmbDisLevel3.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbDisLevel3);
        cmbDisLevel3.setBounds(420, 884, 105, 23);

        chkDisSadarL4.setBackground(new java.awt.Color(255, 255, 250));
        chkDisSadarL4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDisSadarL4.setForeground(new java.awt.Color(0, 0, 0));
        chkDisSadarL4.setText("Sadar");
        chkDisSadarL4.setBorderPainted(true);
        chkDisSadarL4.setBorderPaintedFlat(true);
        chkDisSadarL4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDisSadarL4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDisSadarL4.setName("chkDisSadarL4"); // NOI18N
        chkDisSadarL4.setOpaque(false);
        chkDisSadarL4.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDisSadarL4);
        chkDisSadarL4.setBounds(555, 884, 70, 23);

        chkDisGcsL4.setBackground(new java.awt.Color(255, 255, 250));
        chkDisGcsL4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDisGcsL4.setForeground(new java.awt.Color(0, 0, 0));
        chkDisGcsL4.setText("GCS : 15");
        chkDisGcsL4.setBorderPainted(true);
        chkDisGcsL4.setBorderPaintedFlat(true);
        chkDisGcsL4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDisGcsL4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDisGcsL4.setName("chkDisGcsL4"); // NOI18N
        chkDisGcsL4.setOpaque(false);
        chkDisGcsL4.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDisGcsL4);
        chkDisGcsL4.setBounds(555, 912, 70, 23);

        chkDisSadarL5.setBackground(new java.awt.Color(255, 255, 250));
        chkDisSadarL5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDisSadarL5.setForeground(new java.awt.Color(0, 0, 0));
        chkDisSadarL5.setText("Sadar");
        chkDisSadarL5.setBorderPainted(true);
        chkDisSadarL5.setBorderPaintedFlat(true);
        chkDisSadarL5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDisSadarL5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDisSadarL5.setName("chkDisSadarL5"); // NOI18N
        chkDisSadarL5.setOpaque(false);
        chkDisSadarL5.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDisSadarL5);
        chkDisSadarL5.setBounds(705, 884, 70, 23);

        chkDisGcsL5.setBackground(new java.awt.Color(255, 255, 250));
        chkDisGcsL5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDisGcsL5.setForeground(new java.awt.Color(0, 0, 0));
        chkDisGcsL5.setText("GCS : 15");
        chkDisGcsL5.setBorderPainted(true);
        chkDisGcsL5.setBorderPaintedFlat(true);
        chkDisGcsL5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDisGcsL5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDisGcsL5.setName("chkDisGcsL5"); // NOI18N
        chkDisGcsL5.setOpaque(false);
        chkDisGcsL5.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDisGcsL5);
        chkDisGcsL5.setBounds(705, 912, 70, 23);

        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(50, 983, 800, 1);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Sumber Daya");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(0, 989, 120, 23);

        cmbSumLevel3.setForeground(new java.awt.Color(0, 0, 0));
        cmbSumLevel3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Komplek", ">= 2 DPJP" }));
        cmbSumLevel3.setName("cmbSumLevel3"); // NOI18N
        cmbSumLevel3.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbSumLevel3);
        cmbSumLevel3.setBounds(420, 989, 85, 23);

        cmbSumLevel4.setForeground(new java.awt.Color(0, 0, 0));
        cmbSumLevel4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Simpel", "DPJP Sp/dr. Umum" }));
        cmbSumLevel4.setName("cmbSumLevel4"); // NOI18N
        cmbSumLevel4.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbSumLevel4);
        cmbSumLevel4.setBounds(555, 989, 122, 23);

        cmbSumLevel5.setForeground(new java.awt.Color(0, 0, 0));
        cmbSumLevel5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak Perlu", "Dr. Umum" }));
        cmbSumLevel5.setName("cmbSumLevel5"); // NOI18N
        cmbSumLevel5.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbSumLevel5);
        cmbSumLevel5.setBounds(705, 989, 85, 23);

        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(50, 1017, 800, 1);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("VAS");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(0, 1023, 120, 23);

        chkVas.setBackground(new java.awt.Color(255, 255, 250));
        chkVas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkVas.setForeground(new java.awt.Color(0, 0, 0));
        chkVas.setText(">= 7");
        chkVas.setBorderPainted(true);
        chkVas.setBorderPaintedFlat(true);
        chkVas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkVas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkVas.setName("chkVas"); // NOI18N
        chkVas.setOpaque(false);
        chkVas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkVas);
        chkVas.setBounds(283, 1023, 80, 23);

        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(50, 1050, 800, 1);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Kesimpulan");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(0, 1055, 120, 23);

        chkKesLevel1.setBackground(new java.awt.Color(255, 255, 250));
        chkKesLevel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesLevel1.setForeground(new java.awt.Color(0, 0, 0));
        chkKesLevel1.setText("LEVEL 1");
        chkKesLevel1.setBorderPainted(true);
        chkKesLevel1.setBorderPaintedFlat(true);
        chkKesLevel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKesLevel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKesLevel1.setName("chkKesLevel1"); // NOI18N
        chkKesLevel1.setOpaque(false);
        chkKesLevel1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesLevel1);
        chkKesLevel1.setBounds(142, 1055, 80, 23);

        chkKesLevel2.setBackground(new java.awt.Color(255, 255, 250));
        chkKesLevel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesLevel2.setForeground(new java.awt.Color(0, 0, 0));
        chkKesLevel2.setText("LEVEL 2");
        chkKesLevel2.setBorderPainted(true);
        chkKesLevel2.setBorderPaintedFlat(true);
        chkKesLevel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKesLevel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKesLevel2.setName("chkKesLevel2"); // NOI18N
        chkKesLevel2.setOpaque(false);
        chkKesLevel2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesLevel2);
        chkKesLevel2.setBounds(283, 1055, 80, 23);

        chkKesLevel3.setBackground(new java.awt.Color(255, 255, 250));
        chkKesLevel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesLevel3.setForeground(new java.awt.Color(0, 0, 0));
        chkKesLevel3.setText("LEVEL 3");
        chkKesLevel3.setBorderPainted(true);
        chkKesLevel3.setBorderPaintedFlat(true);
        chkKesLevel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKesLevel3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKesLevel3.setName("chkKesLevel3"); // NOI18N
        chkKesLevel3.setOpaque(false);
        chkKesLevel3.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesLevel3);
        chkKesLevel3.setBounds(420, 1055, 80, 23);

        chkKesLevel4.setBackground(new java.awt.Color(255, 255, 250));
        chkKesLevel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesLevel4.setForeground(new java.awt.Color(0, 0, 0));
        chkKesLevel4.setText("LEVEL 4");
        chkKesLevel4.setBorderPainted(true);
        chkKesLevel4.setBorderPaintedFlat(true);
        chkKesLevel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKesLevel4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKesLevel4.setName("chkKesLevel4"); // NOI18N
        chkKesLevel4.setOpaque(false);
        chkKesLevel4.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesLevel4);
        chkKesLevel4.setBounds(555, 1055, 80, 23);

        chkKesLevel5.setBackground(new java.awt.Color(255, 255, 250));
        chkKesLevel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesLevel5.setForeground(new java.awt.Color(0, 0, 0));
        chkKesLevel5.setText("LEVEL 5");
        chkKesLevel5.setBorderPainted(true);
        chkKesLevel5.setBorderPaintedFlat(true);
        chkKesLevel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKesLevel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKesLevel5.setName("chkKesLevel5"); // NOI18N
        chkKesLevel5.setOpaque(false);
        chkKesLevel5.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesLevel5);
        chkKesLevel5.setBounds(705, 1055, 80, 23);

        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(50, 1083, 800, 1);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(132, 615, 1, 469);

        jSeparator15.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator15.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator15.setName("jSeparator15"); // NOI18N
        FormInput.add(jSeparator15);
        jSeparator15.setBounds(268, 615, 1, 469);

        jSeparator16.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator16.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator16.setName("jSeparator16"); // NOI18N
        FormInput.add(jSeparator16);
        jSeparator16.setBounds(403, 615, 1, 469);

        jSeparator17.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator17.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator17.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator17.setName("jSeparator17"); // NOI18N
        FormInput.add(jSeparator17);
        jSeparator17.setBounds(540, 615, 1, 469);

        jSeparator18.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator18.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator18.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator18.setName("jSeparator18"); // NOI18N
        FormInput.add(jSeparator18);
        jSeparator18.setBounds(688, 615, 1, 469);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Nadi :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(0, 1090, 130, 23);

        Tnadi.setBackground(new java.awt.Color(245, 250, 240));
        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(136, 1090, 60, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("x/menit        Respirasi :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(200, 1090, 113, 23);

        Trespi.setBackground(new java.awt.Color(245, 250, 240));
        Trespi.setForeground(new java.awt.Color(0, 0, 0));
        Trespi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Trespi.setName("Trespi"); // NOI18N
        Trespi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrespiKeyPressed(evt);
            }
        });
        FormInput.add(Trespi);
        Trespi.setBounds(315, 1090, 60, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("x/menit        SpO2 :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(380, 1090, 96, 23);

        Tspo.setBackground(new java.awt.Color(245, 250, 240));
        Tspo.setForeground(new java.awt.Color(0, 0, 0));
        Tspo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tspo.setName("Tspo"); // NOI18N
        Tspo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TspoKeyPressed(evt);
            }
        });
        FormInput.add(Tspo);
        Tspo.setBounds(480, 1090, 60, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("%");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(545, 1090, 20, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Tanda Peringatan Untuk Pasien Pediatrik (Pilihlah Yang Sesuai Dengan Kondisi Pasien)");
        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(0, 1118, 530, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Umur :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(0, 1146, 130, 23);

        cmbUmur.setForeground(new java.awt.Color(0, 0, 0));
        cmbUmur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "< 3 bulan", "3 bulan - 3 tahun", "3 - 8 tahun", "8 tahun" }));
        cmbUmur.setName("cmbUmur"); // NOI18N
        cmbUmur.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbUmur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUmurActionPerformed(evt);
            }
        });
        FormInput.add(cmbUmur);
        cmbUmur.setBounds(136, 1146, 115, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Nadi x/menit :");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(250, 1146, 90, 23);

        TumurNadi.setEditable(false);
        TumurNadi.setBackground(new java.awt.Color(245, 250, 240));
        TumurNadi.setForeground(new java.awt.Color(0, 0, 0));
        TumurNadi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TumurNadi.setName("TumurNadi"); // NOI18N
        FormInput.add(TumurNadi);
        TumurNadi.setBounds(345, 1146, 60, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Nafas x/menit :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(405, 1146, 90, 23);

        TumurNafas.setEditable(false);
        TumurNafas.setBackground(new java.awt.Color(245, 250, 240));
        TumurNafas.setForeground(new java.awt.Color(0, 0, 0));
        TumurNafas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TumurNafas.setName("TumurNafas"); // NOI18N
        FormInput.add(TumurNafas);
        TumurNafas.setBounds(500, 1146, 60, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Temperatur :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(560, 1146, 80, 23);

        TumurTemp.setEditable(false);
        TumurTemp.setBackground(new java.awt.Color(245, 250, 240));
        TumurTemp.setForeground(new java.awt.Color(0, 0, 0));
        TumurTemp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TumurTemp.setName("TumurTemp"); // NOI18N
        FormInput.add(TumurTemp);
        TumurTemp.setBounds(646, 1146, 70, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("SpO2 :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(715, 1146, 50, 23);

        TumurSpo.setBackground(new java.awt.Color(245, 250, 240));
        TumurSpo.setForeground(new java.awt.Color(0, 0, 0));
        TumurSpo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TumurSpo.setName("TumurSpo"); // NOI18N
        TumurSpo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurSpoKeyPressed(evt);
            }
        });
        FormInput.add(TumurSpo);
        TumurSpo.setBounds(770, 1146, 60, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("%");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(836, 1146, 20, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Keterangan : Termasuk Level 2 Apabila Tanda Peringatan Yang Ada Menimbulkan/Berkaitan Dengan Kondisi Yang Beresiko Memburuk Pada Pasien");
        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 1174, 870, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Kasus :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 1202, 130, 23);

        chkTrauma.setBackground(new java.awt.Color(255, 255, 250));
        chkTrauma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTrauma.setForeground(new java.awt.Color(0, 0, 0));
        chkTrauma.setText("Trauma");
        chkTrauma.setBorderPainted(true);
        chkTrauma.setBorderPaintedFlat(true);
        chkTrauma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTrauma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTrauma.setName("chkTrauma"); // NOI18N
        chkTrauma.setOpaque(false);
        chkTrauma.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTrauma);
        chkTrauma.setBounds(136, 1202, 80, 23);

        chkNonTrauma.setBackground(new java.awt.Color(255, 255, 250));
        chkNonTrauma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNonTrauma.setForeground(new java.awt.Color(0, 0, 0));
        chkNonTrauma.setText("Non Trauma");
        chkNonTrauma.setBorderPainted(true);
        chkNonTrauma.setBorderPaintedFlat(true);
        chkNonTrauma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNonTrauma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNonTrauma.setName("chkNonTrauma"); // NOI18N
        chkNonTrauma.setOpaque(false);
        chkNonTrauma.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNonTrauma);
        chkNonTrauma.setBounds(225, 1202, 90, 23);

        chkDoa.setBackground(new java.awt.Color(255, 255, 250));
        chkDoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDoa.setForeground(new java.awt.Color(0, 0, 0));
        chkDoa.setText("DOA");
        chkDoa.setBorderPainted(true);
        chkDoa.setBorderPaintedFlat(true);
        chkDoa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDoa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDoa.setName("chkDoa"); // NOI18N
        chkDoa.setOpaque(false);
        chkDoa.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDoa);
        chkDoa.setBounds(325, 1202, 55, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Jam :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(385, 1202, 40, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(430, 1202, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(480, 1202, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(531, 1202, 45, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("CATATAN KHUSUS :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 1230, 130, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        Tcatatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tcatatan.setColumns(20);
        Tcatatan.setRows(5);
        Tcatatan.setName("Tcatatan"); // NOI18N
        Tcatatan.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tcatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Tcatatan);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(136, 1230, 700, 90);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("KEPUTUSAN :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 1326, 130, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Tanggal :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(136, 1326, 60, 23);

        TtglKeputusan.setEditable(false);
        TtglKeputusan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-02-2025" }));
        TtglKeputusan.setDisplayFormat("dd-MM-yyyy");
        TtglKeputusan.setName("TtglKeputusan"); // NOI18N
        TtglKeputusan.setOpaque(false);
        TtglKeputusan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglKeputusan);
        TtglKeputusan.setBounds(202, 1326, 90, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Jam :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(296, 1326, 40, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(340, 1326, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(390, 1326, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(441, 1326, 45, 23);

        cmbKeputusan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKeputusan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ruang Resusitasi", "Ruang Non Resusitasi IGD" }));
        cmbKeputusan.setName("cmbKeputusan"); // NOI18N
        cmbKeputusan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKeputusan);
        cmbKeputusan.setBounds(495, 1326, 160, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("PETUGAS TRIASE :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 1354, 130, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setBackground(new java.awt.Color(245, 250, 240));
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(136, 1354, 450, 23);

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
        BtnPetugas.setBounds(590, 1354, 28, 23);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-02-2025" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-02-2025" }));
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
//        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
//            Valid.textKosong(TNoRw, "Pasien");
//        } else if (nip.equals("")) {
//            Valid.textKosong(nm_petugas, "Petugas Triase");
//            btnPetugas.requestFocus();
//        } else {
//            cekData();
//            hitungSkor();
//            if (Sequel.menyimpantf("triase_igd", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 71, new String[]{
//                        TNoRw.getText(), Valid.SetTgl(tgl_kunjungan.getSelectedItem() + "") + " " + tgl_kunjungan.getSelectedItem().toString().substring(11, 19), cmbCaraMasuk.getSelectedItem().toString(),
//                        sdh_terpasang.getText(), cmbAlasanKedatangan.getSelectedItem().toString(), rujukan_dari.getText(), dijemput_oleh.getText(), cmbKendaraan.getSelectedItem().toString(), bkn_ambulan.getText(), 
//                        nm_pengantar.getText(), tlpn_pengantar.getText(), cmbKasus.getSelectedItem().toString(), kll_tunggal, tmpt_kejadian_tunggal.getText(), Valid.SetTgl(tgl_kejadian_tunggal.getSelectedItem() + "") + " " + tgl_kejadian_tunggal.getSelectedItem().toString().substring(11, 19),
//                        kll, versus1.getText(), versus2.getText(), tmpt_kejadian.getText(), Valid.SetTgl(tgl_kejadian.getSelectedItem() + "") + " " + tgl_kejadian.getSelectedItem().toString().substring(11, 19), 
//                        jatuh, ket_jatuh.getText(), luka, ket_luka.getText(), trauma_listrik, ket_trauma_listrik.getText(), trauma_zat, ket_trauma_zat.getText(), trauma_lain, ket_trauma_lain.getText(),
//                        Tkeluhan_utama.getText(), pacs1, pacs2, pacs3, pacs4, cmbKesadaran.getSelectedItem().toString(), tkann_darah.getText(), nadi.getText(), pernapasan.getText(),
//                        temperatur.getText(), saturasi.getText(), cmbNyeri.getSelectedItem().toString(), skor0_sadar, skor0_100, skor0_101, skor0_19, skor0_35, skor0_96, skor102, skor20, skor94,
//                        skor99, skor22, skor92, skor3_sadar, skor3_35, skor3_92, skortotal, Tcttn_khusus.getText(), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
//                        resus, nonresus, klinik, doa, nip, tgl_kejadian_tggl, tgl_kejadian_kll, Tvas.getText(), bb.getText(), tb.getText(), Sequel.cariIsi("select now()")
//                    }) == true) {
//                
//                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Triase IGD", "Simpan");
//                TCari.setText(TNoRw.getText());
//                BtnBatalActionPerformed(null);
//                tampil();
//                TabRawat.setSelectedIndex(1);                
//            }
//        }
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
                if (nip.equals(tbTriase.getValueAt(tbTriase.getSelectedRow(), 67).toString())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh Petugas Triase yang bersangkutan..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
//        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
//            Valid.textKosong(TNoRw, "Pasien");
//        } else if (nip.equals("")) {
//            Valid.textKosong(nm_petugas, "Petugas Triase");
//            btnPetugas.requestFocus();
//        } else {
//            if (tbTriase.getSelectedRow() > -1) {
//                if (akses.getkode().equals("Admin Utama")) {
//                    ganti();
//                } else {
//                    if (nip.equals(tbTriase.getValueAt(tbTriase.getSelectedRow(), 67).toString())) {
//                        ganti();
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh Petugas Triase yang bersangkutan..!!");
//                    }
//                }
//            } else {
//                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
//            }
//        }
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
            tampil();            
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data terlebih dahulu..!!!!");
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
        
        if (Sequel.cariInteger("select count(-1) from triase_igd where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from triase_igd where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
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

    private void TnamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Thubungan.requestFocus();
        }
    }//GEN-LAST:event_TnamaKeyPressed

    private void TdariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbTransportasi.requestFocus();
        }
    }//GEN-LAST:event_TdariKeyPressed

    private void ThubunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThubunganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbRujukan.requestFocus();
        }
    }//GEN-LAST:event_ThubunganKeyPressed

    private void cmbRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRujukanActionPerformed
        Tdari.setText("");
        if (cmbRujukan.getSelectedIndex() == 1) {
            Tdari.setEnabled(true);
            Tdari.requestFocus();
        } else {
            Tdari.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRujukanActionPerformed

    private void TkeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeluhanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPenampilan.requestFocus();
        }
    }//GEN-LAST:event_TkeluhanKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trespi.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TrespiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrespiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tspo.requestFocus();
        }
    }//GEN-LAST:event_TrespiKeyPressed

    private void TspoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TspoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbUmur.requestFocus();
        }
    }//GEN-LAST:event_TspoKeyPressed

    private void TumurSpoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurSpoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTrauma.requestFocus();
        }
    }//GEN-LAST:event_TumurSpoKeyPressed

    private void cmbUmurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUmurActionPerformed
        if (cmbUmur.getSelectedIndex() == 1) {
            TumurNadi.setText("> 180");
            TumurNafas.setText("> 50");
            TumurTemp.setText("> 38 °C");
            TumurSpo.requestFocus();
        } else if (cmbUmur.getSelectedIndex() == 2) {
            TumurNadi.setText("> 160");
            TumurNafas.setText("> 40");
            TumurTemp.setText("");
            TumurSpo.requestFocus();
        } else if (cmbUmur.getSelectedIndex() == 3) {
            TumurNadi.setText("> 140");
            TumurNafas.setText("> 30");
            TumurTemp.setText("");
            TumurSpo.requestFocus();
        } else if (cmbUmur.getSelectedIndex() == 4) {
            TumurNadi.setText("> 100");
            TumurNafas.setText("");
            TumurTemp.setText("");
            TumurSpo.requestFocus();
        } else {
            TumurNadi.setText("");
            TumurNafas.setText("");
            TumurTemp.setText("");
        }
    }//GEN-LAST:event_cmbUmurActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void TcatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TtglKeputusan.requestFocus();
        }
    }//GEN-LAST:event_TcatatanKeyPressed

    private void cmbJam2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam2MouseReleased
        AutoCompleteDecorator.decorate(cmbJam2);
    }//GEN-LAST:event_cmbJam2MouseReleased

    private void cmbMnt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt2MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt2);
    }//GEN-LAST:event_cmbMnt2MouseReleased

    private void cmbDtk2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk2);
    }//GEN-LAST:event_cmbDtk2MouseReleased

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        akses.setform("RMTriasePediatrik");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMTriasePediatrik dialog = new RMTriasePediatrik(new javax.swing.JFrame(), true);
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
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnNotepad;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.InternalFrame FormTriase;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private usu.widget.glass.PanelGlass PanelWall;
    private usu.widget.glass.PanelGlass PanelWall1;
    private usu.widget.glass.PanelGlass PanelWall2;
    private usu.widget.glass.PanelGlass PanelWall3;
    private usu.widget.glass.PanelGlass PanelWall4;
    private usu.widget.glass.PanelGlass PanelWall5;
    private usu.widget.glass.PanelGlass PanelWall6;
    private usu.widget.glass.PanelGlass PanelWall7;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane ScrollTriase1;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextArea Tcatatan;
    private widget.TextBox Tdari;
    private widget.TextBox Thubungan;
    private widget.TextBox Tjenkel;
    private widget.TextBox Tkeluhan;
    private widget.TextBox Tnadi;
    private widget.TextBox Tnama;
    private widget.TextBox TnmPetugas;
    private widget.TextBox Tpembiayaan;
    private widget.TextBox Trespi;
    private widget.TextBox Tspo;
    private widget.Tanggal TtglKeputusan;
    private widget.TextBox TtglLahir;
    private widget.Tanggal TtglTiba;
    private widget.TextBox TumurNadi;
    private widget.TextBox TumurNafas;
    private widget.TextBox TumurSpo;
    private widget.TextBox TumurTemp;
    private javax.swing.ButtonGroup buttonGroup1;
    public widget.CekBox chkAncaman;
    public widget.CekBox chkAutoanam;
    public widget.CekBox chkBebas3;
    public widget.CekBox chkBebas4;
    public widget.CekBox chkBebas5;
    public widget.CekBox chkBreaLevel1;
    public widget.CekBox chkBreaLevel2;
    public widget.CekBox chkCircuAkralD;
    public widget.CekBox chkCircuAkralHL3;
    public widget.CekBox chkCircuAkralHL5;
    public widget.CekBox chkCircuCrt;
    public widget.CekBox chkCircuGcs;
    public widget.CekBox chkCircuLevel1;
    public widget.CekBox chkCircuNadi;
    public widget.CekBox chkCircuSadar;
    public widget.CekBox chkDisGcs9;
    public widget.CekBox chkDisGcs912;
    public widget.CekBox chkDisGcsL4;
    public widget.CekBox chkDisGcsL5;
    public widget.CekBox chkDisGelisah;
    public widget.CekBox chkDisKejang;
    public widget.CekBox chkDisRespon;
    public widget.CekBox chkDisSadarL4;
    public widget.CekBox chkDisSadarL5;
    public widget.CekBox chkDisTidak;
    public widget.CekBox chkDistres;
    public widget.CekBox chkDoa;
    public widget.CekBox chkGagalJantung;
    public widget.CekBox chkGagalNafas;
    public widget.CekBox chkHeteroanam;
    public widget.CekBox chkKesLevel1;
    public widget.CekBox chkKesLevel2;
    public widget.CekBox chkKesLevel3;
    public widget.CekBox chkKesLevel4;
    public widget.CekBox chkKesLevel5;
    public widget.CekBox chkNonTrauma;
    public widget.CekBox chkShock;
    public widget.CekBox chkSsp;
    public widget.CekBox chkStabil;
    public widget.CekBox chkTrauma;
    public widget.CekBox chkVas;
    private widget.ComboBox cmbAirway;
    private widget.ComboBox cmbBreaLevel1;
    private widget.ComboBox cmbBreaLevel2;
    private widget.ComboBox cmbBreaLevel3;
    private widget.ComboBox cmbBreaLevel4;
    private widget.ComboBox cmbBreaLevel5;
    private widget.ComboBox cmbCircuLevel2;
    private widget.ComboBox cmbDiisi;
    private widget.ComboBox cmbDisLevel3;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbGelang;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbKeputusan;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbPenampilan;
    private widget.ComboBox cmbRujukan;
    private widget.ComboBox cmbSirkulasi;
    private widget.ComboBox cmbSumLevel3;
    private widget.ComboBox cmbSumLevel4;
    private widget.ComboBox cmbSumLevel5;
    private widget.ComboBox cmbTransportasi;
    private widget.ComboBox cmbUmur;
    private widget.ComboBox cmbUsaha;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame4;
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
    private widget.Label jLabel20;
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
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane14;
    private widget.Table tbTriase;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgl_lahir, ti.* from triase_igd ti "
                    + "inner join reg_periksa rp on rp.no_rawat=ti.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                    + "date(ti.tanggal) between ? and ? and ti.no_rawat like ? or "
                    + "date(ti.tanggal) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(ti.tanggal) between ? and ? and p.nm_pasien like ? or "
                    + "date(ti.tanggal) between ? and ? and ti.nm_pengantar like ? or "
                    + "date(ti.tanggal) between ? and ? and ti.telp_pengantar like ? or "
                    + "date(ti.tanggal) between ? and ? and ti.kasus like ? order by ti.tanggal desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl_lahir"),
                        rs.getString("tanggal"),
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
                        rs.getString("kll_tunggal_tanggal"),
                        rs.getString("kll_versus"),
                        rs.getString("versus1"),
                        rs.getString("versus2"),
                        rs.getString("kll_tmpt_kejadian"),
                        rs.getString("kll_tanggal"),
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
                        rs.getString("pukul"),
                        rs.getString("triase_resusitasi"),
                        rs.getString("triase_non_resusitasi"),
                        rs.getString("triase_klinik"),
                        rs.getString("triase_doa"),
                        rs.getString("nip_petugas"),
                        rs.getString("tgl_kejadian_kll_tunggal"),
                        rs.getString("tgl_kejadian_kll"),
                        rs.getString("vas"),
                        rs.getString("bb"),
                        rs.getString("tb")
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
        cmbGelang.setSelectedIndex(0);
        cmbDiisi.setSelectedIndex(0);
        TtglTiba.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        chkAutoanam.setSelected(false);
        chkHeteroanam.setSelected(false);
        Tnama.setText("");
        Thubungan.setText("");
        cmbRujukan.setSelectedIndex(0);
        Tdari.setText("");
        Tdari.setEnabled(false);
        cmbTransportasi.setSelectedIndex(0);
        Tkeluhan.setText("");
        cmbPenampilan.setSelectedIndex(0);
        cmbUsaha.setSelectedIndex(0);
        cmbSirkulasi.setSelectedIndex(0);
        
        chkStabil.setSelected(false);
        chkDistres.setSelected(false);
        chkGagalNafas.setSelected(false);
        chkSsp.setSelected(false);
        chkGagalJantung.setSelected(false);
        chkShock.setSelected(false);
        buttonGroup1.clearSelection();
        
        cmbAirway.setSelectedIndex(0);
        chkAncaman.setSelected(false);
        chkBebas3.setSelected(false);
        chkBebas4.setSelected(false);
        chkBebas5.setSelected(false);
        
        cmbBreaLevel1.setSelectedIndex(0);
        chkBreaLevel1.setSelected(false);
        cmbBreaLevel2.setSelectedIndex(0);
        chkBreaLevel2.setSelected(false);
        cmbBreaLevel3.setSelectedIndex(0);
        cmbBreaLevel4.setSelectedIndex(0);
        cmbBreaLevel5.setSelectedIndex(0);
        
        chkCircuLevel1.setSelected(false);
        chkCircuAkralD.setSelected(false);
        cmbCircuLevel2.setSelectedIndex(0);
        chkCircuCrt.setSelected(false);
        chkCircuAkralHL3.setSelected(false);
        chkCircuSadar.setSelected(false);
        chkCircuGcs.setSelected(false);
        chkCircuNadi.setSelected(false);
        chkCircuAkralHL5.setSelected(false);
        
        chkDisTidak.setSelected(false);
        chkDisKejang.setSelected(false);
        chkDisGcs9.setSelected(false);
        chkDisRespon.setSelected(false);
        chkDisGelisah.setSelected(false);
        chkDisGcs912.setSelected(false);
        cmbDisLevel3.setSelectedIndex(0);
        chkDisSadarL4.setSelected(false);
        chkDisGcsL4.setSelected(false);
        chkDisSadarL5.setSelected(false);
        chkDisGcsL5.setSelected(false);
        
        cmbSumLevel3.setSelectedIndex(0);
        cmbSumLevel4.setSelectedIndex(0);
        cmbSumLevel5.setSelectedIndex(0);
        
        chkVas.setSelected(false);
        
        chkKesLevel1.setSelected(false);
        chkKesLevel2.setSelected(false);
        chkKesLevel3.setSelected(false);
        chkKesLevel4.setSelected(false);
        chkKesLevel5.setSelected(false);
        
        Tnadi.setText("");
        Trespi.setText("");
        Tspo.setText("");
        
        cmbUmur.setSelectedIndex(0);
        TumurNadi.setText("");
        TumurNafas.setText("");
        TumurTemp.setText("");
        TumurSpo.setText("");
        
        chkTrauma.setSelected(false);
        chkNonTrauma.setSelected(false);
        chkDoa.setSelected(false);
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        Tcatatan.setText("");
        TtglKeputusan.setDate(new Date());
        cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk2.setSelectedIndex(0);
        cmbKeputusan.setSelectedIndex(0);
    }
    
    public void setNoRm(String norwt) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(new Date());
        isRawat();
        tampil();
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getdata_triase_igd());
        BtnHapus.setEnabled(akses.getdata_triase_igd());
        BtnPrint.setEnabled(akses.getdata_triase_igd());
        BtnEdit.setEnabled(akses.getdata_triase_igd());
        
//        if (akses.getjml2() >= 1) {
//            btnPetugas.setEnabled(false);
//            nip = akses.getkode();            
//            Sequel.cariIsi("select nama from pegawai where nik=?", nm_petugas, nip);
//            if (nm_petugas.getText().equals("")) {
//                nip = "";
//            }
//        }
    }
    
    private void getData() {
        nip = "";
        if (tbTriase.getSelectedRow() != -1) {
            TNoRw.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 0).toString());
            TNoRM.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 1).toString());
            TPasien.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(), 2).toString());
//            Valid.SetTgl2(tgl_kunjungan, tbTriase.getValueAt(tbTriase.getSelectedRow(), 4).toString());
            
            nip = tbTriase.getValueAt(tbTriase.getSelectedRow(), 67).toString();
            dataCek();
        }
    }
    
    private void cekData() {
        if (chkAutoanam.isSelected() == true) {
            autoanamnese = "ya";
        } else {
            autoanamnese = "tidak";
        }
        
        if (chkHeteroanam.isSelected() == true) {
            heteroanamnese = "ya";
        } else {
            heteroanamnese = "tidak";
        }
        
        if (chkStabil.isSelected() == true) {
            stabil = "ya";
        } else {
            stabil = "tidak";
        }
        
        if (chkDistres.isSelected() == true) {
            distress = "ya";
        } else {
            distress = "tidak";
        }
        
        if (chkGagalNafas.isSelected() == true) {
            gagalNafas = "ya";
        } else {
            gagalNafas = "tidak";
        }
        
        if (chkSsp.isSelected() == true) {
            ssp = "ya";
        } else {
            ssp = "tidak";
        }
        
        if (chkGagalJantung.isSelected() == true) {
            gagalJantung = "ya";
        } else {
            gagalJantung = "tidak";
        }
        
        if (chkShock.isSelected() == true) {
            shock = "ya";
        } else {
            shock = "tidak";
        }
        
        if (chkAncaman.isSelected() == true) {
            airwayLevel2 = "ya";
        } else {
            airwayLevel2 = "tidak";
        }
        
        if (chkBebas3.isSelected() == true) {
            airwayLevel3 = "ya";
        } else {
            airwayLevel3 = "tidak";
        }
        
        if (chkBebas4.isSelected() == true) {
            airwayLevel4 = "ya";
        } else {
            airwayLevel4 = "tidak";
        }
        
        if (chkBebas5.isSelected() == true) {
            airwayLevel5 = "ya";
        } else {
            airwayLevel5 = "tidak";
        }
        
        if (chkBreaLevel1.isSelected() == true) {
            breathingLevel11 = "ya";
        } else {
            breathingLevel11 = "tidak";
        }
        
        if (chkBreaLevel2.isSelected() == true) {
            breathingLevel22 = "ya";
        } else {
            breathingLevel22 = "tidak";
        }
        
        if (chkCircuLevel1.isSelected() == true) {
            circulationLevel1 = "ya";
        } else {
            circulationLevel1 = "tidak";
        }
        
        if (chkCircuAkralD.isSelected() == true) {
            circulationLevel2 = "ya";
        } else {
            circulationLevel2 = "tidak";
        }
        
        if (chkCircuCrt.isSelected() == true) {
            circulationLevel222 = "ya";
        } else {
            circulationLevel222 = "tidak";
        }
        
        if (chkCircuAkralHL3.isSelected() == true) {
            circulationLevel3 = "ya";
        } else {
            circulationLevel3 = "tidak";
        }
        
        if (chkCircuSadar.isSelected() == true) {
            circulationLevel4 = "ya";
        } else {
            circulationLevel4 = "tidak";
        }
        
        if (chkCircuGcs.isSelected() == true) {
            circulationLevel44 = "ya";
        } else {
            circulationLevel44 = "tidak";
        }
        
        if (chkCircuNadi.isSelected() == true) {
            circulationLevel5 = "ya";
        } else {
            circulationLevel5 = "tidak";
        }
        
        if (chkCircuAkralHL5.isSelected() == true) {
            circulationLevel55 = "ya";
        } else {
            circulationLevel55 = "tidak";
        }
        
        if (chkDisTidak.isSelected() == true) {
            disabilityLevel1 = "ya";
        } else {
            disabilityLevel1 = "tidak";
        }
        
        if (chkDisKejang.isSelected() == true) {
            disabilityLevel11 = "ya";
        } else {
            disabilityLevel11 = "tidak";
        }
        
        if (chkDisGcs9.isSelected() == true) {
            disabilityLevel111 = "ya";
        } else {
            disabilityLevel111 = "tidak";
        }
        
        if (chkDisRespon.isSelected() == true) {
            disabilityLevel2 = "ya";
        } else {
            disabilityLevel2 = "tidak";
        }
        
        if (chkDisGelisah.isSelected() == true) {
            disabilityLevel22 = "ya";
        } else {
            disabilityLevel22 = "tidak";
        }
        
        if (chkDisGcs912.isSelected() == true) {
            disabilityLevel222 = "ya";
        } else {
            disabilityLevel222 = "tidak";
        }
        
        if (chkDisSadarL4.isSelected() == true) {
            disabilityLevel4 = "ya";
        } else {
            disabilityLevel4 = "tidak";
        }
        
        if (chkDisGcsL4.isSelected() == true) {
            disabilityLevel44 = "ya";
        } else {
            disabilityLevel44 = "tidak";
        }
        
        if (chkDisSadarL5.isSelected() == true) {
            disabilityLevel5 = "ya";
        } else {
            disabilityLevel5 = "tidak";
        }
        
        if (chkDisGcsL5.isSelected() == true) {
            disabilityLevel55 = "ya";
        } else {
            disabilityLevel55 = "tidak";
        }
        
        if (chkVas.isSelected() == true) {
            vas = "ya";
        } else {
            vas = "tidak";
        }
        
        if (chkKesLevel1.isSelected() == true) {
            kesimpulanLevel1 = "ya";
        } else {
            kesimpulanLevel1 = "tidak";
        }
        
        if (chkKesLevel2.isSelected() == true) {
            kesimpulanLevel2 = "ya";
        } else {
            kesimpulanLevel2 = "tidak";
        }
        
        if (chkKesLevel3.isSelected() == true) {
            kesimpulanLevel3 = "ya";
        } else {
            kesimpulanLevel3 = "tidak";
        }
        
        if (chkKesLevel4.isSelected() == true) {
            kesimpulanLevel4 = "ya";
        } else {
            kesimpulanLevel4 = "tidak";
        }
        
        if (chkKesLevel5.isSelected() == true) {
            kesimpulanLevel5 = "ya";
        } else {
            kesimpulanLevel5 = "tidak";
        }
        
        if (chkTrauma.isSelected() == true) {
            trauma = "ya";
        } else {
            trauma = "tidak";
        }
        
        if (chkNonTrauma.isSelected() == true) {
            nonTrauma = "ya";
        } else {
            nonTrauma = "tidak";
        }
        
        if (chkDoa.isSelected() == true) {
            doa = "ya";
        } else {
            doa = "tidak";
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from triase_igd where no_rawat=?", 1, new String[]{
                tbTriase.getValueAt(tbTriase.getSelectedRow(), 0).toString()
            }) == true) {
                tampil();
                BtnBatalActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        } else {
            tampil();
            BtnBatalActionPerformed(null);
        }
    }
    
    private void ganti() {
        cekData();
//        if (Sequel.mengedittf("triase_igd", "no_rawat=?", "no_rawat=?, tanggal=?, cara_masuk=?, sudah_terpasang=?, alasan_kedatangan=?, rujukan_dari=?, dijemput_oleh=?, kendaraan=?, bukan_ambulan=?, nm_pengantar=?, "
//                + "telp_pengantar=?, kasus=?, kll_tunggal=?, kll_tunggal_tmpt_kejadian=?, kll_tunggal_tanggal=?, kll_versus=?, versus1=?, versus2=?, kll_tmpt_kejadian=?, "
//                + "kll_tanggal=?, jatuh=?, ket_jatuh=?, luka_bakar=?, ket_luka_bakar=?, trauma_listrik=?, ket_trauma_listrik=?, trauma_zat_kimia=?, ket_trauma_zat_kimia=?, "
//                + "trauma_lain=?, ket_trauma_lain=?, keluhan_utama=?, pacs1=?, pacs2=?, pacs3=?, pacs4=?, kesadaran=?, td=?, nadi=?, napas=?, temperatur=?, saturasi=?, "
//                + "nyeri=?, skor0_sadar_penuh=?, skor0_100=?, skor0_101=?, skor0_19=?, skor0_35_3=?, skor0_96_100=?, skor1_102=?, skor1_20_21=?, skor1_94_95=?, skor2_99=?, "
//                + "skor2_22=?, skor2_92_93=?, skor3_selain=?, skor3_35_3=?, skor3_92=?, total_skor=?, catatan=?, pukul=?, triase_resusitasi=?, triase_non_resusitasi=?, "
//                + "triase_klinik=?, triase_doa=?, nip_petugas=?, tgl_kejadian_kll_tunggal=?, tgl_kejadian_kll=?, vas=?, bb=?, tb=?", 71, new String[]{
//                    TNoRw.getText(), Valid.SetTgl(tgl_kunjungan.getSelectedItem() + "") + " " + tgl_kunjungan.getSelectedItem().toString().substring(11, 19), cmbCaraMasuk.getSelectedItem().toString(),
//                    sdh_terpasang.getText(), cmbAlasanKedatangan.getSelectedItem().toString(), rujukan_dari.getText(), dijemput_oleh.getText(), cmbKendaraan.getSelectedItem().toString(), bkn_ambulan.getText(),
//                    nm_pengantar.getText(), tlpn_pengantar.getText(), cmbKasus.getSelectedItem().toString(), kll_tunggal, tmpt_kejadian_tunggal.getText(), Valid.SetTgl(tgl_kejadian_tunggal.getSelectedItem() + "") + " " + tgl_kejadian_tunggal.getSelectedItem().toString().substring(11, 19),
//                    kll, versus1.getText(), versus2.getText(), tmpt_kejadian.getText(), Valid.SetTgl(tgl_kejadian.getSelectedItem() + "") + " " + tgl_kejadian.getSelectedItem().toString().substring(11, 19),
//                    jatuh, ket_jatuh.getText(), luka, ket_luka.getText(), trauma_listrik, ket_trauma_listrik.getText(), trauma_zat, ket_trauma_zat.getText(), trauma_lain, ket_trauma_lain.getText(),
//                    Tkeluhan_utama.getText(), pacs1, pacs2, pacs3, pacs4, cmbKesadaran.getSelectedItem().toString(), tkann_darah.getText(), nadi.getText(), pernapasan.getText(),
//                    temperatur.getText(), saturasi.getText(), cmbNyeri.getSelectedItem().toString(), skor0_sadar, skor0_100, skor0_101, skor0_19, skor0_35, skor0_96, skor102, skor20, skor94,
//                    skor99, skor22, skor92, skor3_sadar, skor3_35, skor3_92, skortotal, Tcttn_khusus.getText(), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
//                    resus, nonresus, klinik, doa, nip, tgl_kejadian_tggl, tgl_kejadian_kll, Tvas.getText(), bb.getText(), tb.getText(),
//                    tbTriase.getValueAt(tbTriase.getSelectedRow(), 0).toString()
//                }) == true) {
//
//            Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Triase IGD", "Ganti");
//            TCari.setText(TNoRw.getText());
//            tampil();
//            BtnBatalActionPerformed(null);
//            TabRawat.setSelectedIndex(1);
//        }
    }
    
    private void isRawat() {
        try {
            psx = koneksi.prepareStatement("SELECT rp.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir, '%d-%m-%Y') tgl_lahir, "
                    + "rp.tgl_registrasi, IF (p.no_tlp_pj = '', '0', IFNULL(p.no_tlp_pj,'0')) notelppj, "
                    + "IF (p.namakeluarga = '','-',p.namakeluarga) namakeluarga, IFNULL(pa.td,'') td, IFNULL(pa.nadi,'') nadi, "
                    + "IFNULL(pa.nafas,'') nafas, IFNULL(pa.suhu,'') suhu, rp.jam_reg FROM reg_periksa rp "
                    + "INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "LEFT JOIN penilaian_awal_keperawatan_igdrz pa ON pa.no_rawat = rp.no_rawat WHERE rp.no_rawat= ?");
            try {
                psx.setString(1, TNoRw.getText());
                rsx = psx.executeQuery();
                if (rsx.next()) {
                    TNoRM.setText(rsx.getString("no_rkm_medis"));
                    TPasien.setText(rsx.getString("nm_pasien"));
//                    tglLahir.setText(rsx.getString("tgl_lahir"));
                    DTPCari1.setDate(rsx.getDate("tgl_registrasi"));
                    
//                    Valid.SetTgl2(tgl_kunjungan, rsx.getDate("tgl_registrasi").toString() + " " + rsx.getString("jam_reg").toString());
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
//        if (kll_tunggal.equals("ya")) {
//            ChkKll_tunggal.setSelected(true);
//        } else {
//            ChkKll_tunggal.setSelected(false);
//        }
    }
}
