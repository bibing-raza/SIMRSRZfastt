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
import laporan.DlgPenyakit;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariPeriksaRadiologi;
import simrskhanza.DlgNotepad;

/**
 *
 * @author perpustakaan
 */
public final class RMPemberianInformasiEdukasi extends javax.swing.JDialog {
    private DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private String nip = "", bahasa = "", pendengaran = "", masalahPenglihatan = "", hilangMemori = "", tidakAdaPartisipasi = "", 
            secaraFisiologi = "", tidakDitemukanHambatan = "", cemas = "", emosi = "", kognitif = "", motifasiBuruk = "", bahasaIndonesia = "", 
            bahasaDaerah = "", bahasaInggris = "", bahasaLainnya = "", prosesPenyakit = "", pengobatan = "", alatBantuMedis = "", lainLain = "", 
            terapiObat = "", nutrisi = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPemberianInformasiEdukasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        
        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Edukasi", "Jam Edukasi",
            "bahasa", "pendengaran", "masalah_penglihatan", "hilang_memori", "tidak_ada_partisipasi", "secara_fisiologi", "tidak_ditemukan_hambatan", 
            "cemas", "emosi", "kognitif", "motifasi_buruk", "bicara", "ket_kapan", "bahasa_indonesia", "indonesia", "bahasa_daerah", "ket_daerah", 
            "bahasa_inggris", "inggris", "bahasa_lainnya", "ket_bahasa_lainnya", "penerjemah", "ket_penerjemah", "nilai_pasien", "kesediaan_menerima", 
            "proses_penyakit", "pengobatan", "alat_bantu_medis", "lain_lain", "ket_lainlain", "terapi_obat", "nutrisi", "penggunaan_herbal", "vegetarian", 
            "menolak_vaksinasi", "kepercayaan_terhadap", "puasa", "menolak_dilakukan", "menolak_pulang", "menolak_dilayani", "tidak_memakan", 
            "lain_lain_identifikasi", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbPemberian.setModel(tabMode);
        tbPemberian.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPemberian.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 51; i++) {
            TableColumn column = tbPemberian.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(80);
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
            }
        }
        tbPemberian.setDefaultRenderer(Object.class, new WarnaTable());
        
        Tkapan.setDocument(new batasInput((int) 200).getKata(Tkapan));
        Tdaerah.setDocument(new batasInput((int) 200).getKata(Tdaerah));
        TbhsLainya.setDocument(new batasInput((int) 200).getKata(TbhsLainya));
        Tperlu.setDocument(new batasInput((int) 200).getKata(Tperlu));
        TPotensialLain.setDocument(new batasInput((int) 200).getKata(TPotensialLain));        
        TCari1.setDocument(new batasInput((int) 100).getKata(TCari1));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari1.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari1.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari1.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari1.getText().length()>2){
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

        internalFrame1 = new widget.InternalFrame();
        TabEdukasi = new javax.swing.JTabbedPane();
        FormAsesmen = new widget.InternalFrame();
        ScrollTriase1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel5 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel146 = new widget.Label();
        chkBahasa = new widget.CekBox();
        chkPendengaran = new widget.CekBox();
        chkMasalah = new widget.CekBox();
        chkHilangMemori = new widget.CekBox();
        chkTidakAda = new widget.CekBox();
        chkSecara = new widget.CekBox();
        chkTidakDitemukan = new widget.CekBox();
        chkCemas = new widget.CekBox();
        chkEmosi = new widget.CekBox();
        chkKognitif = new widget.CekBox();
        chkMotivasi = new widget.CekBox();
        jLabel147 = new widget.Label();
        jLabel148 = new widget.Label();
        cmbBicara = new widget.ComboBox();
        Tkapan = new widget.TextBox();
        jLabel149 = new widget.Label();
        chkBhsIndonesia = new widget.CekBox();
        cmbBhsIndo = new widget.ComboBox();
        chkDaerah = new widget.CekBox();
        Tdaerah = new widget.TextBox();
        chkBhsInggris = new widget.CekBox();
        cmbBhsInggris = new widget.ComboBox();
        chkBhsLainya = new widget.CekBox();
        TbhsLainya = new widget.TextBox();
        jLabel150 = new widget.Label();
        cmbPerlu = new widget.ComboBox();
        Tperlu = new widget.TextBox();
        jLabel151 = new widget.Label();
        Tpnd = new widget.TextBox();
        jLabel152 = new widget.Label();
        Tagama = new widget.TextBox();
        jLabel153 = new widget.Label();
        cmbNilaiPasien = new widget.ComboBox();
        jLabel154 = new widget.Label();
        cmbKesediaan = new widget.ComboBox();
        jLabel155 = new widget.Label();
        chkProses = new widget.CekBox();
        chkPengobatan = new widget.CekBox();
        chkAlatBantu = new widget.CekBox();
        chkTerapi = new widget.CekBox();
        chkNutrisi = new widget.CekBox();
        chkPotensialLain = new widget.CekBox();
        TPotensialLain = new widget.TextBox();
        jLabel156 = new widget.Label();
        jLabel157 = new widget.Label();
        jLabel158 = new widget.Label();
        jLabel159 = new widget.Label();
        jLabel160 = new widget.Label();
        jLabel161 = new widget.Label();
        jLabel162 = new widget.Label();
        jLabel163 = new widget.Label();
        jLabel164 = new widget.Label();
        jLabel165 = new widget.Label();
        jLabel166 = new widget.Label();
        cmbPenggunaanHerbal = new widget.ComboBox();
        cmbVegetarian = new widget.ComboBox();
        cmbMenolakVaksin = new widget.ComboBox();
        cmbKepercayaan = new widget.ComboBox();
        cmbPuasa = new widget.ComboBox();
        cmbMenolakDilakukan = new widget.ComboBox();
        cmbMenolakPulang = new widget.ComboBox();
        cmbMenolakDilayani = new widget.ComboBox();
        cmbTidakMemakan = new widget.ComboBox();
        cmbLainLain = new widget.ComboBox();
        FormInput2 = new widget.PanelBiasa();
        Scroll1 = new widget.ScrollPane();
        tbPemberian = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel20 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel8 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel9 = new widget.Label();
        LCount1 = new widget.Label();
        internalFrame4 = new widget.InternalFrame();
        ScrollTriase2 = new widget.ScrollPane();
        FormInput1 = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw1 = new widget.TextBox();
        TPasien1 = new widget.TextBox();
        TNoRM1 = new widget.TextBox();
        jLabel11 = new widget.Label();
        TrgRawat1 = new widget.TextBox();
        jLabel12 = new widget.Label();
        Ttgl = new widget.Tanggal();
        jLabel13 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel14 = new widget.Label();
        cmbPenerimaanPnd = new widget.ComboBox();
        jLabel15 = new widget.Label();
        cmbMetode = new widget.ComboBox();
        jLabel16 = new widget.Label();
        cmbProfesi = new widget.ComboBox();
        Tprofesi = new widget.TextBox();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        chkDiagnosis = new widget.CekBox();
        chkKondisi = new widget.CekBox();
        chkTindakan = new widget.CekBox();
        chkTataCara = new widget.CekBox();
        chkManfaat = new widget.CekBox();
        chkNamaOrang = new widget.CekBox();
        chkKemungkinan = new widget.CekBox();
        chkPrognosis = new widget.CekBox();
        chkKemTdkTerduga = new widget.CekBox();
        chkKemBila = new widget.CekBox();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        chkPendidikanKes = new widget.CekBox();
        TpendidikanKes = new widget.TextBox();
        chkHasilAsuhan = new widget.CekBox();
        chkPenanganan = new widget.CekBox();
        chkPerawatan = new widget.CekBox();
        chkAlatAlat = new widget.CekBox();
        chkInformasi = new widget.CekBox();
        chkKeamanan = new widget.CekBox();
        chkProsedur = new widget.CekBox();
        chkFarmaObat = new widget.CekBox();
        chkFarmaInjek = new widget.CekBox();
        chkFarmaSedasi = new widget.CekBox();
        chkPerawatanLatihan = new widget.CekBox();
        chkDistraksi = new widget.CekBox();
        chkPengalihan = new widget.CekBox();
        chkCaraCuci = new widget.CekBox();
        chkEtika = new widget.CekBox();
        chkCaraBuang = new widget.CekBox();
        chkTempat = new widget.CekBox();
        chkLainProPerawat = new widget.CekBox();
        TpndPerawatLain = new widget.TextBox();
        jLabel25 = new widget.Label();
        jLabel26 = new widget.Label();
        chkDiet = new widget.CekBox();
        chkKonsulGiziRanap = new widget.CekBox();
        chkKonsulGiziRalan = new widget.CekBox();
        scrollPane14 = new widget.ScrollPane();
        TpndNutrisionisLain = new widget.TextArea();
        jLabel27 = new widget.Label();
        jLabel28 = new widget.Label();
        chkHak = new widget.CekBox();
        chkJam = new widget.CekBox();
        chkInfoKejadian = new widget.CekBox();
        scrollPane15 = new widget.ScrollPane();
        TpndAdmisiLain = new widget.TextArea();
        jLabel29 = new widget.Label();
        jLabel30 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        TpndLainyaLain = new widget.TextArea();
        jLabel31 = new widget.Label();
        cmbTingkat = new widget.ComboBox();
        jLabel32 = new widget.Label();
        cmbEvaluasi = new widget.ComboBox();
        jLabel33 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel34 = new widget.Label();
        TnmPenerima = new widget.TextBox();
        FormInput3 = new widget.PanelBiasa();
        Scroll = new widget.ScrollPane();
        tbPenilaian = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari3 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pemberian Informasi Dan Edukasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabEdukasi.setBackground(new java.awt.Color(255, 255, 254));
        TabEdukasi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabEdukasi.setName("TabEdukasi"); // NOI18N
        TabEdukasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabEdukasiMouseClicked(evt);
            }
        });

        FormAsesmen.setBorder(null);
        FormAsesmen.setName("FormAsesmen"); // NOI18N
        FormAsesmen.setLayout(new java.awt.GridLayout(1, 2));

        ScrollTriase1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        ScrollTriase1.setName("ScrollTriase1"); // NOI18N
        ScrollTriase1.setOpaque(true);
        ScrollTriase1.setPreferredSize(new java.awt.Dimension(102, 720));

        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 718));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 122, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(315, 10, 407, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(240, 10, 70, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Ruang Rawat : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setBackground(new java.awt.Color(245, 250, 240));
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(114, 38, 608, 23);

        jLabel146.setForeground(new java.awt.Color(0, 0, 0));
        jLabel146.setText("Pengkajian Hambatan :");
        jLabel146.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(0, 66, 150, 23);

        chkBahasa.setBackground(new java.awt.Color(255, 255, 250));
        chkBahasa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBahasa.setForeground(new java.awt.Color(0, 0, 0));
        chkBahasa.setText("Bahasa");
        chkBahasa.setBorderPainted(true);
        chkBahasa.setBorderPaintedFlat(true);
        chkBahasa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBahasa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBahasa.setName("chkBahasa"); // NOI18N
        chkBahasa.setOpaque(false);
        chkBahasa.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBahasa);
        chkBahasa.setBounds(160, 66, 80, 23);

        chkPendengaran.setBackground(new java.awt.Color(255, 255, 250));
        chkPendengaran.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPendengaran.setForeground(new java.awt.Color(0, 0, 0));
        chkPendengaran.setText("Pendengaran");
        chkPendengaran.setBorderPainted(true);
        chkPendengaran.setBorderPaintedFlat(true);
        chkPendengaran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPendengaran.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPendengaran.setName("chkPendengaran"); // NOI18N
        chkPendengaran.setOpaque(false);
        chkPendengaran.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPendengaran);
        chkPendengaran.setBounds(160, 94, 100, 23);

        chkMasalah.setBackground(new java.awt.Color(255, 255, 250));
        chkMasalah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMasalah.setForeground(new java.awt.Color(0, 0, 0));
        chkMasalah.setText("Masalah Penglihatan");
        chkMasalah.setBorderPainted(true);
        chkMasalah.setBorderPaintedFlat(true);
        chkMasalah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMasalah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMasalah.setName("chkMasalah"); // NOI18N
        chkMasalah.setOpaque(false);
        chkMasalah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMasalah);
        chkMasalah.setBounds(160, 122, 130, 23);

        chkHilangMemori.setBackground(new java.awt.Color(255, 255, 250));
        chkHilangMemori.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHilangMemori.setForeground(new java.awt.Color(0, 0, 0));
        chkHilangMemori.setText("Hilang Memori");
        chkHilangMemori.setBorderPainted(true);
        chkHilangMemori.setBorderPaintedFlat(true);
        chkHilangMemori.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHilangMemori.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHilangMemori.setName("chkHilangMemori"); // NOI18N
        chkHilangMemori.setOpaque(false);
        chkHilangMemori.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHilangMemori);
        chkHilangMemori.setBounds(160, 150, 100, 23);

        chkTidakAda.setBackground(new java.awt.Color(255, 255, 250));
        chkTidakAda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTidakAda.setForeground(new java.awt.Color(0, 0, 0));
        chkTidakAda.setText("Tidak Ada Partisipasi Dari Caregive");
        chkTidakAda.setBorderPainted(true);
        chkTidakAda.setBorderPaintedFlat(true);
        chkTidakAda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidakAda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidakAda.setName("chkTidakAda"); // NOI18N
        chkTidakAda.setOpaque(false);
        chkTidakAda.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTidakAda);
        chkTidakAda.setBounds(160, 178, 200, 23);

        chkSecara.setBackground(new java.awt.Color(255, 255, 250));
        chkSecara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSecara.setForeground(new java.awt.Color(0, 0, 0));
        chkSecara.setText("Secara Fisiologi Tidak Mampu Belajar");
        chkSecara.setBorderPainted(true);
        chkSecara.setBorderPaintedFlat(true);
        chkSecara.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSecara.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSecara.setName("chkSecara"); // NOI18N
        chkSecara.setOpaque(false);
        chkSecara.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSecara);
        chkSecara.setBounds(160, 206, 200, 23);

        chkTidakDitemukan.setBackground(new java.awt.Color(255, 255, 250));
        chkTidakDitemukan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTidakDitemukan.setForeground(new java.awt.Color(0, 0, 0));
        chkTidakDitemukan.setText("Tidak Ditemukan Hambatan Belajar");
        chkTidakDitemukan.setBorderPainted(true);
        chkTidakDitemukan.setBorderPaintedFlat(true);
        chkTidakDitemukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidakDitemukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidakDitemukan.setName("chkTidakDitemukan"); // NOI18N
        chkTidakDitemukan.setOpaque(false);
        chkTidakDitemukan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTidakDitemukan);
        chkTidakDitemukan.setBounds(400, 66, 200, 23);

        chkCemas.setBackground(new java.awt.Color(255, 255, 250));
        chkCemas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCemas.setForeground(new java.awt.Color(0, 0, 0));
        chkCemas.setText("Cemas");
        chkCemas.setBorderPainted(true);
        chkCemas.setBorderPaintedFlat(true);
        chkCemas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCemas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCemas.setName("chkCemas"); // NOI18N
        chkCemas.setOpaque(false);
        chkCemas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCemas);
        chkCemas.setBounds(400, 94, 70, 23);

        chkEmosi.setBackground(new java.awt.Color(255, 255, 250));
        chkEmosi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEmosi.setForeground(new java.awt.Color(0, 0, 0));
        chkEmosi.setText("Emosi");
        chkEmosi.setBorderPainted(true);
        chkEmosi.setBorderPaintedFlat(true);
        chkEmosi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEmosi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEmosi.setName("chkEmosi"); // NOI18N
        chkEmosi.setOpaque(false);
        chkEmosi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkEmosi);
        chkEmosi.setBounds(400, 122, 60, 23);

        chkKognitif.setBackground(new java.awt.Color(255, 255, 250));
        chkKognitif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKognitif.setForeground(new java.awt.Color(0, 0, 0));
        chkKognitif.setText("Kognitif");
        chkKognitif.setBorderPainted(true);
        chkKognitif.setBorderPaintedFlat(true);
        chkKognitif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKognitif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKognitif.setName("chkKognitif"); // NOI18N
        chkKognitif.setOpaque(false);
        chkKognitif.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKognitif);
        chkKognitif.setBounds(400, 150, 70, 23);

        chkMotivasi.setBackground(new java.awt.Color(255, 255, 250));
        chkMotivasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMotivasi.setForeground(new java.awt.Color(0, 0, 0));
        chkMotivasi.setText("Motivasi Buruk");
        chkMotivasi.setBorderPainted(true);
        chkMotivasi.setBorderPaintedFlat(true);
        chkMotivasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMotivasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMotivasi.setName("chkMotivasi"); // NOI18N
        chkMotivasi.setOpaque(false);
        chkMotivasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMotivasi);
        chkMotivasi.setBounds(400, 178, 110, 23);

        jLabel147.setForeground(new java.awt.Color(0, 0, 0));
        jLabel147.setText("Pengkajian : ");
        jLabel147.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput.add(jLabel147);
        jLabel147.setBounds(0, 234, 170, 23);

        jLabel148.setForeground(new java.awt.Color(0, 0, 0));
        jLabel148.setText("Bicara : ");
        jLabel148.setName("jLabel148"); // NOI18N
        FormInput.add(jLabel148);
        jLabel148.setBounds(0, 262, 170, 23);

        cmbBicara.setForeground(new java.awt.Color(0, 0, 0));
        cmbBicara.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Serangan Awal Gangguan", "Bicara, Kapan" }));
        cmbBicara.setName("cmbBicara"); // NOI18N
        cmbBicara.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbBicara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBicaraActionPerformed(evt);
            }
        });
        FormInput.add(cmbBicara);
        cmbBicara.setBounds(174, 262, 160, 23);

        Tkapan.setBackground(new java.awt.Color(245, 250, 240));
        Tkapan.setForeground(new java.awt.Color(0, 0, 0));
        Tkapan.setName("Tkapan"); // NOI18N
        Tkapan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkapanKeyPressed(evt);
            }
        });
        FormInput.add(Tkapan);
        Tkapan.setBounds(342, 262, 380, 23);

        jLabel149.setForeground(new java.awt.Color(0, 0, 0));
        jLabel149.setText("Bahasa Sehari - Hari : ");
        jLabel149.setName("jLabel149"); // NOI18N
        FormInput.add(jLabel149);
        jLabel149.setBounds(0, 290, 170, 23);

        chkBhsIndonesia.setBackground(new java.awt.Color(255, 255, 250));
        chkBhsIndonesia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBhsIndonesia.setForeground(new java.awt.Color(0, 0, 0));
        chkBhsIndonesia.setText("Indonesia");
        chkBhsIndonesia.setBorderPainted(true);
        chkBhsIndonesia.setBorderPaintedFlat(true);
        chkBhsIndonesia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBhsIndonesia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBhsIndonesia.setName("chkBhsIndonesia"); // NOI18N
        chkBhsIndonesia.setOpaque(false);
        chkBhsIndonesia.setPreferredSize(new java.awt.Dimension(175, 23));
        chkBhsIndonesia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBhsIndonesiaActionPerformed(evt);
            }
        });
        FormInput.add(chkBhsIndonesia);
        chkBhsIndonesia.setBounds(174, 290, 75, 23);

        cmbBhsIndo.setForeground(new java.awt.Color(0, 0, 0));
        cmbBhsIndo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Aktif", "Pasif" }));
        cmbBhsIndo.setName("cmbBhsIndo"); // NOI18N
        cmbBhsIndo.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBhsIndo);
        cmbBhsIndo.setBounds(255, 290, 60, 23);

        chkDaerah.setBackground(new java.awt.Color(255, 255, 250));
        chkDaerah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDaerah.setForeground(new java.awt.Color(0, 0, 0));
        chkDaerah.setText("Daerah");
        chkDaerah.setBorderPainted(true);
        chkDaerah.setBorderPaintedFlat(true);
        chkDaerah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDaerah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDaerah.setName("chkDaerah"); // NOI18N
        chkDaerah.setOpaque(false);
        chkDaerah.setPreferredSize(new java.awt.Dimension(175, 23));
        chkDaerah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDaerahActionPerformed(evt);
            }
        });
        FormInput.add(chkDaerah);
        chkDaerah.setBounds(174, 318, 75, 23);

        Tdaerah.setBackground(new java.awt.Color(245, 250, 240));
        Tdaerah.setForeground(new java.awt.Color(0, 0, 0));
        Tdaerah.setName("Tdaerah"); // NOI18N
        Tdaerah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdaerahKeyPressed(evt);
            }
        });
        FormInput.add(Tdaerah);
        Tdaerah.setBounds(255, 318, 467, 23);

        chkBhsInggris.setBackground(new java.awt.Color(255, 255, 250));
        chkBhsInggris.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBhsInggris.setForeground(new java.awt.Color(0, 0, 0));
        chkBhsInggris.setText("Inggris");
        chkBhsInggris.setBorderPainted(true);
        chkBhsInggris.setBorderPaintedFlat(true);
        chkBhsInggris.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBhsInggris.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBhsInggris.setName("chkBhsInggris"); // NOI18N
        chkBhsInggris.setOpaque(false);
        chkBhsInggris.setPreferredSize(new java.awt.Dimension(175, 23));
        chkBhsInggris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBhsInggrisActionPerformed(evt);
            }
        });
        FormInput.add(chkBhsInggris);
        chkBhsInggris.setBounds(342, 290, 60, 23);

        cmbBhsInggris.setForeground(new java.awt.Color(0, 0, 0));
        cmbBhsInggris.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Aktif", "Pasif" }));
        cmbBhsInggris.setName("cmbBhsInggris"); // NOI18N
        cmbBhsInggris.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBhsInggris);
        cmbBhsInggris.setBounds(408, 290, 60, 23);

        chkBhsLainya.setBackground(new java.awt.Color(255, 255, 250));
        chkBhsLainya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBhsLainya.setForeground(new java.awt.Color(0, 0, 0));
        chkBhsLainya.setText("Lainnya");
        chkBhsLainya.setBorderPainted(true);
        chkBhsLainya.setBorderPaintedFlat(true);
        chkBhsLainya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBhsLainya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBhsLainya.setName("chkBhsLainya"); // NOI18N
        chkBhsLainya.setOpaque(false);
        chkBhsLainya.setPreferredSize(new java.awt.Dimension(175, 23));
        chkBhsLainya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBhsLainyaActionPerformed(evt);
            }
        });
        FormInput.add(chkBhsLainya);
        chkBhsLainya.setBounds(174, 346, 75, 23);

        TbhsLainya.setBackground(new java.awt.Color(245, 250, 240));
        TbhsLainya.setForeground(new java.awt.Color(0, 0, 0));
        TbhsLainya.setName("TbhsLainya"); // NOI18N
        TbhsLainya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbhsLainyaKeyPressed(evt);
            }
        });
        FormInput.add(TbhsLainya);
        TbhsLainya.setBounds(255, 346, 467, 23);

        jLabel150.setForeground(new java.awt.Color(0, 0, 0));
        jLabel150.setText("Perlu Penerjemah : ");
        jLabel150.setName("jLabel150"); // NOI18N
        FormInput.add(jLabel150);
        jLabel150.setBounds(0, 374, 170, 23);

        cmbPerlu.setForeground(new java.awt.Color(0, 0, 0));
        cmbPerlu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya, Bahasa Asing" }));
        cmbPerlu.setName("cmbPerlu"); // NOI18N
        cmbPerlu.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbPerlu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPerluActionPerformed(evt);
            }
        });
        FormInput.add(cmbPerlu);
        cmbPerlu.setBounds(174, 374, 120, 23);

        Tperlu.setBackground(new java.awt.Color(245, 250, 240));
        Tperlu.setForeground(new java.awt.Color(0, 0, 0));
        Tperlu.setName("Tperlu"); // NOI18N
        Tperlu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TperluKeyPressed(evt);
            }
        });
        FormInput.add(Tperlu);
        Tperlu.setBounds(302, 374, 420, 23);

        jLabel151.setForeground(new java.awt.Color(0, 0, 0));
        jLabel151.setText("Tingkat Pendidikan : ");
        jLabel151.setName("jLabel151"); // NOI18N
        FormInput.add(jLabel151);
        jLabel151.setBounds(0, 402, 170, 23);

        Tpnd.setEditable(false);
        Tpnd.setBackground(new java.awt.Color(245, 250, 240));
        Tpnd.setForeground(new java.awt.Color(0, 0, 0));
        Tpnd.setName("Tpnd"); // NOI18N
        FormInput.add(Tpnd);
        Tpnd.setBounds(174, 402, 60, 23);

        jLabel152.setForeground(new java.awt.Color(0, 0, 0));
        jLabel152.setText("Agama :");
        jLabel152.setName("jLabel152"); // NOI18N
        FormInput.add(jLabel152);
        jLabel152.setBounds(240, 402, 50, 23);

        Tagama.setEditable(false);
        Tagama.setBackground(new java.awt.Color(245, 250, 240));
        Tagama.setForeground(new java.awt.Color(0, 0, 0));
        Tagama.setName("Tagama"); // NOI18N
        FormInput.add(Tagama);
        Tagama.setBounds(295, 402, 250, 23);

        jLabel153.setForeground(new java.awt.Color(0, 0, 0));
        jLabel153.setText("Nilai - Nilai Pasien Dan Budaya : ");
        jLabel153.setName("jLabel153"); // NOI18N
        FormInput.add(jLabel153);
        jLabel153.setBounds(0, 430, 170, 23);

        cmbNilaiPasien.setForeground(new java.awt.Color(0, 0, 0));
        cmbNilaiPasien.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Modern", "Moderat", "Konvensional" }));
        cmbNilaiPasien.setName("cmbNilaiPasien"); // NOI18N
        cmbNilaiPasien.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbNilaiPasien);
        cmbNilaiPasien.setBounds(174, 430, 100, 23);

        jLabel154.setForeground(new java.awt.Color(0, 0, 0));
        jLabel154.setText("Kesediaan Menerima Informasi :");
        jLabel154.setName("jLabel154"); // NOI18N
        FormInput.add(jLabel154);
        jLabel154.setBounds(280, 430, 170, 23);

        cmbKesediaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesediaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKesediaan.setName("cmbKesediaan"); // NOI18N
        cmbKesediaan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKesediaan);
        cmbKesediaan.setBounds(456, 430, 60, 23);

        jLabel155.setForeground(new java.awt.Color(0, 0, 0));
        jLabel155.setText("Potensial Kebutuhan Pembelajaran : ");
        jLabel155.setName("jLabel155"); // NOI18N
        FormInput.add(jLabel155);
        jLabel155.setBounds(0, 458, 200, 23);

        chkProses.setBackground(new java.awt.Color(255, 255, 250));
        chkProses.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkProses.setForeground(new java.awt.Color(0, 0, 0));
        chkProses.setText("Proses Penyakit");
        chkProses.setBorderPainted(true);
        chkProses.setBorderPaintedFlat(true);
        chkProses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkProses.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkProses.setName("chkProses"); // NOI18N
        chkProses.setOpaque(false);
        chkProses.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkProses);
        chkProses.setBounds(205, 458, 120, 23);

        chkPengobatan.setBackground(new java.awt.Color(255, 255, 250));
        chkPengobatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPengobatan.setForeground(new java.awt.Color(0, 0, 0));
        chkPengobatan.setText("Pengobatan/Tindakan");
        chkPengobatan.setBorderPainted(true);
        chkPengobatan.setBorderPaintedFlat(true);
        chkPengobatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPengobatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPengobatan.setName("chkPengobatan"); // NOI18N
        chkPengobatan.setOpaque(false);
        chkPengobatan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPengobatan);
        chkPengobatan.setBounds(205, 486, 134, 23);

        chkAlatBantu.setBackground(new java.awt.Color(255, 255, 250));
        chkAlatBantu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAlatBantu.setForeground(new java.awt.Color(0, 0, 0));
        chkAlatBantu.setText("Alat Bantu Medis");
        chkAlatBantu.setBorderPainted(true);
        chkAlatBantu.setBorderPaintedFlat(true);
        chkAlatBantu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAlatBantu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAlatBantu.setName("chkAlatBantu"); // NOI18N
        chkAlatBantu.setOpaque(false);
        chkAlatBantu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAlatBantu);
        chkAlatBantu.setBounds(205, 514, 120, 23);

        chkTerapi.setBackground(new java.awt.Color(255, 255, 250));
        chkTerapi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTerapi.setForeground(new java.awt.Color(0, 0, 0));
        chkTerapi.setText("Terapi/Obat");
        chkTerapi.setBorderPainted(true);
        chkTerapi.setBorderPaintedFlat(true);
        chkTerapi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTerapi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTerapi.setName("chkTerapi"); // NOI18N
        chkTerapi.setOpaque(false);
        chkTerapi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTerapi);
        chkTerapi.setBounds(350, 458, 100, 23);

        chkNutrisi.setBackground(new java.awt.Color(255, 255, 250));
        chkNutrisi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNutrisi.setForeground(new java.awt.Color(0, 0, 0));
        chkNutrisi.setText("Nutrisi");
        chkNutrisi.setBorderPainted(true);
        chkNutrisi.setBorderPaintedFlat(true);
        chkNutrisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNutrisi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNutrisi.setName("chkNutrisi"); // NOI18N
        chkNutrisi.setOpaque(false);
        chkNutrisi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNutrisi);
        chkNutrisi.setBounds(350, 486, 60, 23);

        chkPotensialLain.setBackground(new java.awt.Color(255, 255, 250));
        chkPotensialLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPotensialLain.setForeground(new java.awt.Color(0, 0, 0));
        chkPotensialLain.setText("Lain-Lain, Jelaskan");
        chkPotensialLain.setBorderPainted(true);
        chkPotensialLain.setBorderPaintedFlat(true);
        chkPotensialLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPotensialLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPotensialLain.setName("chkPotensialLain"); // NOI18N
        chkPotensialLain.setOpaque(false);
        chkPotensialLain.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPotensialLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPotensialLainActionPerformed(evt);
            }
        });
        FormInput.add(chkPotensialLain);
        chkPotensialLain.setBounds(350, 514, 120, 23);

        TPotensialLain.setBackground(new java.awt.Color(245, 250, 240));
        TPotensialLain.setForeground(new java.awt.Color(0, 0, 0));
        TPotensialLain.setName("TPotensialLain"); // NOI18N
        FormInput.add(TPotensialLain);
        TPotensialLain.setBounds(472, 514, 250, 23);

        jLabel156.setForeground(new java.awt.Color(0, 0, 0));
        jLabel156.setText("Identifikasi Nilai - Nilai Dan Kepercayaan :");
        jLabel156.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel156.setName("jLabel156"); // NOI18N
        FormInput.add(jLabel156);
        jLabel156.setBounds(0, 542, 260, 23);

        jLabel157.setForeground(new java.awt.Color(0, 0, 0));
        jLabel157.setText("Penggunaan Herbal / Jamu :");
        jLabel157.setName("jLabel157"); // NOI18N
        FormInput.add(jLabel157);
        jLabel157.setBounds(0, 570, 230, 23);

        jLabel158.setForeground(new java.awt.Color(0, 0, 0));
        jLabel158.setText("Vegetarian :");
        jLabel158.setName("jLabel158"); // NOI18N
        FormInput.add(jLabel158);
        jLabel158.setBounds(0, 598, 230, 23);

        jLabel159.setForeground(new java.awt.Color(0, 0, 0));
        jLabel159.setText("Menolak Vaksinasi :");
        jLabel159.setName("jLabel159"); // NOI18N
        FormInput.add(jLabel159);
        jLabel159.setBounds(0, 626, 230, 23);

        jLabel160.setForeground(new java.awt.Color(0, 0, 0));
        jLabel160.setText("Kepercayaan Terhadap Mistik/Hal-Hal Gaib :");
        jLabel160.setName("jLabel160"); // NOI18N
        FormInput.add(jLabel160);
        jLabel160.setBounds(0, 654, 230, 23);

        jLabel161.setForeground(new java.awt.Color(0, 0, 0));
        jLabel161.setText("Puasa Pada Hari Tertentu :");
        jLabel161.setName("jLabel161"); // NOI18N
        FormInput.add(jLabel161);
        jLabel161.setBounds(0, 682, 230, 23);

        jLabel162.setForeground(new java.awt.Color(0, 0, 0));
        jLabel162.setText("Menolak Dilakukan Transfusi Darah :");
        jLabel162.setName("jLabel162"); // NOI18N
        FormInput.add(jLabel162);
        jLabel162.setBounds(300, 570, 330, 23);

        jLabel163.setForeground(new java.awt.Color(0, 0, 0));
        jLabel163.setText("Menolak Pulang Hari Tertentu :");
        jLabel163.setName("jLabel163"); // NOI18N
        FormInput.add(jLabel163);
        jLabel163.setBounds(300, 598, 330, 23);

        jLabel164.setForeground(new java.awt.Color(0, 0, 0));
        jLabel164.setText("Menolak Dilayani Oleh Petugas Laki-Laki Pada Pasien Perempuan :");
        jLabel164.setName("jLabel164"); // NOI18N
        FormInput.add(jLabel164);
        jLabel164.setBounds(300, 626, 330, 23);

        jLabel165.setForeground(new java.awt.Color(0, 0, 0));
        jLabel165.setText("Tidak Memakan Suatu Jenis Makanan Tertentu, Misal Daging Sapi, Ikan Tidak Bersisik, dll :");
        jLabel165.setName("jLabel165"); // NOI18N
        FormInput.add(jLabel165);
        jLabel165.setBounds(300, 654, 440, 23);

        jLabel166.setForeground(new java.awt.Color(0, 0, 0));
        jLabel166.setText("Lain - Lain :");
        jLabel166.setName("jLabel166"); // NOI18N
        FormInput.add(jLabel166);
        jLabel166.setBounds(300, 682, 330, 23);

        cmbPenggunaanHerbal.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenggunaanHerbal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPenggunaanHerbal.setName("cmbPenggunaanHerbal"); // NOI18N
        cmbPenggunaanHerbal.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbPenggunaanHerbal);
        cmbPenggunaanHerbal.setBounds(236, 570, 60, 23);

        cmbVegetarian.setForeground(new java.awt.Color(0, 0, 0));
        cmbVegetarian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbVegetarian.setName("cmbVegetarian"); // NOI18N
        cmbVegetarian.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbVegetarian);
        cmbVegetarian.setBounds(236, 598, 60, 23);

        cmbMenolakVaksin.setForeground(new java.awt.Color(0, 0, 0));
        cmbMenolakVaksin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMenolakVaksin.setName("cmbMenolakVaksin"); // NOI18N
        cmbMenolakVaksin.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMenolakVaksin);
        cmbMenolakVaksin.setBounds(236, 626, 60, 23);

        cmbKepercayaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKepercayaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKepercayaan.setName("cmbKepercayaan"); // NOI18N
        cmbKepercayaan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKepercayaan);
        cmbKepercayaan.setBounds(236, 654, 60, 23);

        cmbPuasa.setForeground(new java.awt.Color(0, 0, 0));
        cmbPuasa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPuasa.setName("cmbPuasa"); // NOI18N
        cmbPuasa.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbPuasa);
        cmbPuasa.setBounds(236, 682, 60, 23);

        cmbMenolakDilakukan.setForeground(new java.awt.Color(0, 0, 0));
        cmbMenolakDilakukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMenolakDilakukan.setName("cmbMenolakDilakukan"); // NOI18N
        cmbMenolakDilakukan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMenolakDilakukan);
        cmbMenolakDilakukan.setBounds(635, 570, 60, 23);

        cmbMenolakPulang.setForeground(new java.awt.Color(0, 0, 0));
        cmbMenolakPulang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMenolakPulang.setName("cmbMenolakPulang"); // NOI18N
        cmbMenolakPulang.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMenolakPulang);
        cmbMenolakPulang.setBounds(635, 598, 60, 23);

        cmbMenolakDilayani.setForeground(new java.awt.Color(0, 0, 0));
        cmbMenolakDilayani.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMenolakDilayani.setName("cmbMenolakDilayani"); // NOI18N
        cmbMenolakDilayani.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMenolakDilayani);
        cmbMenolakDilayani.setBounds(635, 626, 60, 23);

        cmbTidakMemakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbTidakMemakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbTidakMemakan.setName("cmbTidakMemakan"); // NOI18N
        cmbTidakMemakan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbTidakMemakan);
        cmbTidakMemakan.setBounds(747, 654, 60, 23);

        cmbLainLain.setForeground(new java.awt.Color(0, 0, 0));
        cmbLainLain.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbLainLain.setName("cmbLainLain"); // NOI18N
        cmbLainLain.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbLainLain);
        cmbLainLain.setBounds(635, 682, 60, 23);

        ScrollTriase1.setViewportView(FormInput);

        FormAsesmen.add(ScrollTriase1);

        FormInput2.setBorder(null);
        FormInput2.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis..!!");
        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(870, 718));
        FormInput2.setLayout(new java.awt.BorderLayout());

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Data Informasi dan Edukasi ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbPemberian.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemberian.setName("tbPemberian"); // NOI18N
        tbPemberian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemberianMouseClicked(evt);
            }
        });
        tbPemberian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemberianKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbPemberian);

        FormInput2.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Tgl. Pemberian :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(jLabel20);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-03-2025" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d.");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel22);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-03-2025" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari2);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Key Word :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel8);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

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
        panelGlass10.add(BtnCari);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Record :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel9);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount1);

        FormInput2.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        FormAsesmen.add(FormInput2);

        TabEdukasi.addTab("Informasi Edukasi", FormAsesmen);

        internalFrame4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.GridLayout(1, 2));

        ScrollTriase2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        ScrollTriase2.setName("ScrollTriase2"); // NOI18N
        ScrollTriase2.setOpaque(true);
        ScrollTriase2.setPreferredSize(new java.awt.Dimension(102, 420));

        FormInput1.setBorder(null);
        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(870, 1158));
        FormInput1.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput1.add(jLabel10);
        jLabel10.setBounds(0, 10, 110, 23);

        TNoRw1.setEditable(false);
        TNoRw1.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw1.setName("TNoRw1"); // NOI18N
        FormInput1.add(TNoRw1);
        TNoRw1.setBounds(114, 10, 122, 23);

        TPasien1.setEditable(false);
        TPasien1.setBackground(new java.awt.Color(245, 250, 240));
        TPasien1.setForeground(new java.awt.Color(0, 0, 0));
        TPasien1.setName("TPasien1"); // NOI18N
        FormInput1.add(TPasien1);
        TPasien1.setBounds(315, 10, 407, 23);

        TNoRM1.setEditable(false);
        TNoRM1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM1.setName("TNoRM1"); // NOI18N
        FormInput1.add(TNoRM1);
        TNoRM1.setBounds(240, 10, 70, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Ruang Rawat : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput1.add(jLabel11);
        jLabel11.setBounds(0, 38, 110, 23);

        TrgRawat1.setEditable(false);
        TrgRawat1.setBackground(new java.awt.Color(245, 250, 240));
        TrgRawat1.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat1.setName("TrgRawat1"); // NOI18N
        FormInput1.add(TrgRawat1);
        TrgRawat1.setBounds(114, 38, 608, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tanggal : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput1.add(jLabel12);
        jLabel12.setBounds(0, 66, 110, 23);

        Ttgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-03-2025" }));
        Ttgl.setDisplayFormat("dd-MM-yyyy");
        Ttgl.setName("Ttgl"); // NOI18N
        Ttgl.setOpaque(false);
        Ttgl.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput1.add(Ttgl);
        Ttgl.setBounds(114, 66, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jam : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput1.add(jLabel13);
        jLabel13.setBounds(210, 66, 50, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput1.add(cmbJam);
        cmbJam.setBounds(265, 66, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput1.add(cmbMnt);
        cmbMnt.setBounds(317, 66, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput1.add(cmbDtk);
        cmbDtk.setBounds(370, 66, 45, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Penerimaan Pendidikan : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput1.add(jLabel14);
        jLabel14.setBounds(0, 94, 160, 23);

        cmbPenerimaanPnd.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenerimaanPnd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "P - Pasien", "K - Keluarga", "L - Lain-lain" }));
        cmbPenerimaanPnd.setName("cmbPenerimaanPnd"); // NOI18N
        cmbPenerimaanPnd.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbPenerimaanPnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPenerimaanPndActionPerformed(evt);
            }
        });
        FormInput1.add(cmbPenerimaanPnd);
        cmbPenerimaanPnd.setBounds(165, 94, 90, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Metode :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput1.add(jLabel15);
        jLabel15.setBounds(260, 94, 60, 23);

        cmbMetode.setForeground(new java.awt.Color(0, 0, 0));
        cmbMetode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1 - Audio", "2 - Demonstrasi", "3 - Lisan", "4 - Tulisan", "5 - Visual" }));
        cmbMetode.setName("cmbMetode"); // NOI18N
        cmbMetode.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput1.add(cmbMetode);
        cmbMetode.setBounds(326, 94, 110, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Profesi :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput1.add(jLabel16);
        jLabel16.setBounds(440, 94, 60, 23);

        cmbProfesi.setForeground(new java.awt.Color(0, 0, 0));
        cmbProfesi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Dokter", "Perawat", "Bidan", "Nutrisionis", "Admisi", "Lainnya" }));
        cmbProfesi.setName("cmbProfesi"); // NOI18N
        cmbProfesi.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbProfesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProfesiActionPerformed(evt);
            }
        });
        FormInput1.add(cmbProfesi);
        cmbProfesi.setBounds(506, 94, 85, 23);

        Tprofesi.setBackground(new java.awt.Color(245, 250, 240));
        Tprofesi.setForeground(new java.awt.Color(0, 0, 0));
        Tprofesi.setName("Tprofesi"); // NOI18N
        Tprofesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprofesiKeyPressed(evt);
            }
        });
        FormInput1.add(Tprofesi);
        Tprofesi.setBounds(597, 94, 125, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Profesi Dokter : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput1.add(jLabel17);
        jLabel17.setBounds(0, 150, 160, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("ISI PENDIDIKAN KESEHATAN");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput1.add(jLabel18);
        jLabel18.setBounds(165, 150, 190, 23);

        chkDiagnosis.setBackground(new java.awt.Color(255, 255, 250));
        chkDiagnosis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDiagnosis.setForeground(new java.awt.Color(0, 0, 0));
        chkDiagnosis.setText("Diagnosis (Diagnosis Kerja & Diagnosis Banding Dan Dasar)");
        chkDiagnosis.setBorderPainted(true);
        chkDiagnosis.setBorderPaintedFlat(true);
        chkDiagnosis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDiagnosis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDiagnosis.setName("chkDiagnosis"); // NOI18N
        chkDiagnosis.setOpaque(false);
        chkDiagnosis.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkDiagnosis);
        chkDiagnosis.setBounds(165, 178, 310, 23);

        chkKondisi.setBackground(new java.awt.Color(255, 255, 250));
        chkKondisi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKondisi.setForeground(new java.awt.Color(0, 0, 0));
        chkKondisi.setText("Kondisi Pasien");
        chkKondisi.setBorderPainted(true);
        chkKondisi.setBorderPaintedFlat(true);
        chkKondisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKondisi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKondisi.setName("chkKondisi"); // NOI18N
        chkKondisi.setOpaque(false);
        chkKondisi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkKondisi);
        chkKondisi.setBounds(165, 206, 100, 23);

        chkTindakan.setBackground(new java.awt.Color(255, 255, 250));
        chkTindakan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTindakan.setForeground(new java.awt.Color(0, 0, 0));
        chkTindakan.setText("Tindakan Yang Diusulkan");
        chkTindakan.setBorderPainted(true);
        chkTindakan.setBorderPaintedFlat(true);
        chkTindakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTindakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTindakan.setName("chkTindakan"); // NOI18N
        chkTindakan.setOpaque(false);
        chkTindakan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkTindakan);
        chkTindakan.setBounds(165, 234, 150, 23);

        chkTataCara.setBackground(new java.awt.Color(255, 255, 250));
        chkTataCara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTataCara.setForeground(new java.awt.Color(0, 0, 0));
        chkTataCara.setText("Tata Cara dan Tujuan Tindakan");
        chkTataCara.setBorderPainted(true);
        chkTataCara.setBorderPaintedFlat(true);
        chkTataCara.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTataCara.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTataCara.setName("chkTataCara"); // NOI18N
        chkTataCara.setOpaque(false);
        chkTataCara.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkTataCara);
        chkTataCara.setBounds(165, 262, 190, 23);

        chkManfaat.setBackground(new java.awt.Color(255, 255, 250));
        chkManfaat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkManfaat.setForeground(new java.awt.Color(0, 0, 0));
        chkManfaat.setText("Manfaat Dan Resiko Tindakan");
        chkManfaat.setBorderPainted(true);
        chkManfaat.setBorderPaintedFlat(true);
        chkManfaat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkManfaat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkManfaat.setName("chkManfaat"); // NOI18N
        chkManfaat.setOpaque(false);
        chkManfaat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkManfaat);
        chkManfaat.setBounds(165, 290, 190, 23);

        chkNamaOrang.setBackground(new java.awt.Color(255, 255, 250));
        chkNamaOrang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNamaOrang.setForeground(new java.awt.Color(0, 0, 0));
        chkNamaOrang.setText("Nama Orang Yang Mengerjakan Tindakan");
        chkNamaOrang.setBorderPainted(true);
        chkNamaOrang.setBorderPaintedFlat(true);
        chkNamaOrang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNamaOrang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNamaOrang.setName("chkNamaOrang"); // NOI18N
        chkNamaOrang.setOpaque(false);
        chkNamaOrang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkNamaOrang);
        chkNamaOrang.setBounds(490, 178, 230, 23);

        chkKemungkinan.setBackground(new java.awt.Color(255, 255, 250));
        chkKemungkinan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKemungkinan.setForeground(new java.awt.Color(0, 0, 0));
        chkKemungkinan.setText("Kemungkinan Alternative");
        chkKemungkinan.setBorderPainted(true);
        chkKemungkinan.setBorderPaintedFlat(true);
        chkKemungkinan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKemungkinan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKemungkinan.setName("chkKemungkinan"); // NOI18N
        chkKemungkinan.setOpaque(false);
        chkKemungkinan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkKemungkinan);
        chkKemungkinan.setBounds(490, 206, 160, 23);

        chkPrognosis.setBackground(new java.awt.Color(255, 255, 250));
        chkPrognosis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPrognosis.setForeground(new java.awt.Color(0, 0, 0));
        chkPrognosis.setText("Prognosis Dari Tindakan");
        chkPrognosis.setBorderPainted(true);
        chkPrognosis.setBorderPaintedFlat(true);
        chkPrognosis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPrognosis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPrognosis.setName("chkPrognosis"); // NOI18N
        chkPrognosis.setOpaque(false);
        chkPrognosis.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkPrognosis);
        chkPrognosis.setBounds(490, 234, 150, 23);

        chkKemTdkTerduga.setBackground(new java.awt.Color(255, 255, 250));
        chkKemTdkTerduga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKemTdkTerduga.setForeground(new java.awt.Color(0, 0, 0));
        chkKemTdkTerduga.setText("Kemungkinan Hasil Yang Tidak Terduga");
        chkKemTdkTerduga.setBorderPainted(true);
        chkKemTdkTerduga.setBorderPaintedFlat(true);
        chkKemTdkTerduga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKemTdkTerduga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKemTdkTerduga.setName("chkKemTdkTerduga"); // NOI18N
        chkKemTdkTerduga.setOpaque(false);
        chkKemTdkTerduga.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkKemTdkTerduga);
        chkKemTdkTerduga.setBounds(490, 262, 220, 23);

        chkKemBila.setBackground(new java.awt.Color(255, 255, 250));
        chkKemBila.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKemBila.setForeground(new java.awt.Color(0, 0, 0));
        chkKemBila.setText("Kemungkinan Hasil Bila Tidak Dilakukan Tindakan");
        chkKemBila.setBorderPainted(true);
        chkKemBila.setBorderPaintedFlat(true);
        chkKemBila.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKemBila.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKemBila.setName("chkKemBila"); // NOI18N
        chkKemBila.setOpaque(false);
        chkKemBila.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkKemBila);
        chkKemBila.setBounds(490, 290, 270, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Profesi Perawat / Bidan : ");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput1.add(jLabel23);
        jLabel23.setBounds(0, 318, 160, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("ISI PENDIDIKAN KESEHATAN");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput1.add(jLabel24);
        jLabel24.setBounds(165, 318, 190, 23);

        chkPendidikanKes.setBackground(new java.awt.Color(255, 255, 250));
        chkPendidikanKes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPendidikanKes.setForeground(new java.awt.Color(0, 0, 0));
        chkPendidikanKes.setText("Pendidikan Kesehatan Tentang :");
        chkPendidikanKes.setBorderPainted(true);
        chkPendidikanKes.setBorderPaintedFlat(true);
        chkPendidikanKes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPendidikanKes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPendidikanKes.setName("chkPendidikanKes"); // NOI18N
        chkPendidikanKes.setOpaque(false);
        chkPendidikanKes.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPendidikanKes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPendidikanKesActionPerformed(evt);
            }
        });
        FormInput1.add(chkPendidikanKes);
        chkPendidikanKes.setBounds(165, 346, 180, 23);

        TpendidikanKes.setBackground(new java.awt.Color(245, 250, 240));
        TpendidikanKes.setForeground(new java.awt.Color(0, 0, 0));
        TpendidikanKes.setName("TpendidikanKes"); // NOI18N
        TpendidikanKes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpendidikanKesKeyPressed(evt);
            }
        });
        FormInput1.add(TpendidikanKes);
        TpendidikanKes.setBounds(347, 346, 375, 23);

        chkHasilAsuhan.setBackground(new java.awt.Color(255, 255, 250));
        chkHasilAsuhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHasilAsuhan.setForeground(new java.awt.Color(0, 0, 0));
        chkHasilAsuhan.setText("Hasil Asuhan Keperawatan");
        chkHasilAsuhan.setBorderPainted(true);
        chkHasilAsuhan.setBorderPaintedFlat(true);
        chkHasilAsuhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHasilAsuhan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHasilAsuhan.setName("chkHasilAsuhan"); // NOI18N
        chkHasilAsuhan.setOpaque(false);
        chkHasilAsuhan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkHasilAsuhan);
        chkHasilAsuhan.setBounds(165, 374, 180, 23);

        chkPenanganan.setBackground(new java.awt.Color(255, 255, 250));
        chkPenanganan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPenanganan.setForeground(new java.awt.Color(0, 0, 0));
        chkPenanganan.setText("Penanganan & Cara Perawatan Di Rumah");
        chkPenanganan.setBorderPainted(true);
        chkPenanganan.setBorderPaintedFlat(true);
        chkPenanganan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPenanganan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPenanganan.setName("chkPenanganan"); // NOI18N
        chkPenanganan.setOpaque(false);
        chkPenanganan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkPenanganan);
        chkPenanganan.setBounds(165, 402, 230, 23);

        chkPerawatan.setBackground(new java.awt.Color(255, 255, 250));
        chkPerawatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerawatan.setForeground(new java.awt.Color(0, 0, 0));
        chkPerawatan.setText("Perawatan Luka");
        chkPerawatan.setBorderPainted(true);
        chkPerawatan.setBorderPaintedFlat(true);
        chkPerawatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerawatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerawatan.setName("chkPerawatan"); // NOI18N
        chkPerawatan.setOpaque(false);
        chkPerawatan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkPerawatan);
        chkPerawatan.setBounds(420, 374, 110, 23);

        chkAlatAlat.setBackground(new java.awt.Color(255, 255, 250));
        chkAlatAlat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAlatAlat.setForeground(new java.awt.Color(0, 0, 0));
        chkAlatAlat.setText("Alat - Alat Yang Perlu Disiapkan Di Rumah");
        chkAlatAlat.setBorderPainted(true);
        chkAlatAlat.setBorderPaintedFlat(true);
        chkAlatAlat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAlatAlat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAlatAlat.setName("chkAlatAlat"); // NOI18N
        chkAlatAlat.setOpaque(false);
        chkAlatAlat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkAlatAlat);
        chkAlatAlat.setBounds(420, 402, 230, 23);

        chkInformasi.setBackground(new java.awt.Color(255, 255, 250));
        chkInformasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkInformasi.setForeground(new java.awt.Color(0, 0, 0));
        chkInformasi.setText("Informasi Pasien Baru");
        chkInformasi.setBorderPainted(true);
        chkInformasi.setBorderPaintedFlat(true);
        chkInformasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInformasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInformasi.setName("chkInformasi"); // NOI18N
        chkInformasi.setOpaque(false);
        chkInformasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkInformasi);
        chkInformasi.setBounds(165, 430, 140, 23);

        chkKeamanan.setBackground(new java.awt.Color(255, 255, 250));
        chkKeamanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKeamanan.setForeground(new java.awt.Color(0, 0, 0));
        chkKeamanan.setText("Keamanan Penggunaan Alat-Alat Kesehatan (Infuse Pump, Siringe Pump, Injeksi Insulin, Dsb)");
        chkKeamanan.setBorderPainted(true);
        chkKeamanan.setBorderPaintedFlat(true);
        chkKeamanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKeamanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKeamanan.setName("chkKeamanan"); // NOI18N
        chkKeamanan.setOpaque(false);
        chkKeamanan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkKeamanan);
        chkKeamanan.setBounds(165, 458, 480, 23);

        chkProsedur.setBackground(new java.awt.Color(255, 255, 250));
        chkProsedur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkProsedur.setForeground(new java.awt.Color(0, 0, 0));
        chkProsedur.setText("Prosedur Tindakan/Kedokteran Yang Tidak Perlu Informed Consent (Infuse Line, Foley Catather, Nasogastric Tube Sesuai Kebijakan)");
        chkProsedur.setBorderPainted(true);
        chkProsedur.setBorderPaintedFlat(true);
        chkProsedur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkProsedur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkProsedur.setName("chkProsedur"); // NOI18N
        chkProsedur.setOpaque(false);
        chkProsedur.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkProsedur);
        chkProsedur.setBounds(165, 486, 670, 23);

        chkFarmaObat.setBackground(new java.awt.Color(255, 255, 250));
        chkFarmaObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFarmaObat.setForeground(new java.awt.Color(0, 0, 0));
        chkFarmaObat.setText("Farmakologi : Obat Oral");
        chkFarmaObat.setBorderPainted(true);
        chkFarmaObat.setBorderPaintedFlat(true);
        chkFarmaObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFarmaObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFarmaObat.setName("chkFarmaObat"); // NOI18N
        chkFarmaObat.setOpaque(false);
        chkFarmaObat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkFarmaObat);
        chkFarmaObat.setBounds(165, 514, 140, 23);

        chkFarmaInjek.setBackground(new java.awt.Color(255, 255, 250));
        chkFarmaInjek.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFarmaInjek.setForeground(new java.awt.Color(0, 0, 0));
        chkFarmaInjek.setText("Farmakologi : Injeksi");
        chkFarmaInjek.setBorderPainted(true);
        chkFarmaInjek.setBorderPaintedFlat(true);
        chkFarmaInjek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFarmaInjek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFarmaInjek.setName("chkFarmaInjek"); // NOI18N
        chkFarmaInjek.setOpaque(false);
        chkFarmaInjek.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkFarmaInjek);
        chkFarmaInjek.setBounds(330, 514, 130, 23);

        chkFarmaSedasi.setBackground(new java.awt.Color(255, 255, 250));
        chkFarmaSedasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFarmaSedasi.setForeground(new java.awt.Color(0, 0, 0));
        chkFarmaSedasi.setText("Farmakologi : Sedasi");
        chkFarmaSedasi.setBorderPainted(true);
        chkFarmaSedasi.setBorderPaintedFlat(true);
        chkFarmaSedasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFarmaSedasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFarmaSedasi.setName("chkFarmaSedasi"); // NOI18N
        chkFarmaSedasi.setOpaque(false);
        chkFarmaSedasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkFarmaSedasi);
        chkFarmaSedasi.setBounds(470, 514, 130, 23);

        chkPerawatanLatihan.setBackground(new java.awt.Color(255, 255, 250));
        chkPerawatanLatihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerawatanLatihan.setForeground(new java.awt.Color(0, 0, 0));
        chkPerawatanLatihan.setText("Perawatan Latihan Nafas Dalam");
        chkPerawatanLatihan.setBorderPainted(true);
        chkPerawatanLatihan.setBorderPaintedFlat(true);
        chkPerawatanLatihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerawatanLatihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerawatanLatihan.setName("chkPerawatanLatihan"); // NOI18N
        chkPerawatanLatihan.setOpaque(false);
        chkPerawatanLatihan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkPerawatanLatihan);
        chkPerawatanLatihan.setBounds(165, 542, 190, 23);

        chkDistraksi.setBackground(new java.awt.Color(255, 255, 250));
        chkDistraksi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDistraksi.setForeground(new java.awt.Color(0, 0, 0));
        chkDistraksi.setText("Distraksi");
        chkDistraksi.setBorderPainted(true);
        chkDistraksi.setBorderPaintedFlat(true);
        chkDistraksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDistraksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDistraksi.setName("chkDistraksi"); // NOI18N
        chkDistraksi.setOpaque(false);
        chkDistraksi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkDistraksi);
        chkDistraksi.setBounds(165, 570, 70, 23);

        chkPengalihan.setBackground(new java.awt.Color(255, 255, 250));
        chkPengalihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPengalihan.setForeground(new java.awt.Color(0, 0, 0));
        chkPengalihan.setText("Pengalihan Perhatian, Evaluasi Nyeri Sesuai Derajatnya");
        chkPengalihan.setBorderPainted(true);
        chkPengalihan.setBorderPaintedFlat(true);
        chkPengalihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPengalihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPengalihan.setName("chkPengalihan"); // NOI18N
        chkPengalihan.setOpaque(false);
        chkPengalihan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkPengalihan);
        chkPengalihan.setBounds(165, 598, 295, 23);

        chkCaraCuci.setBackground(new java.awt.Color(255, 255, 250));
        chkCaraCuci.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCaraCuci.setForeground(new java.awt.Color(0, 0, 0));
        chkCaraCuci.setText("Cara Cuci Tangan");
        chkCaraCuci.setBorderPainted(true);
        chkCaraCuci.setBorderPaintedFlat(true);
        chkCaraCuci.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCaraCuci.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCaraCuci.setName("chkCaraCuci"); // NOI18N
        chkCaraCuci.setOpaque(false);
        chkCaraCuci.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkCaraCuci);
        chkCaraCuci.setBounds(165, 626, 120, 23);

        chkEtika.setBackground(new java.awt.Color(255, 255, 250));
        chkEtika.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEtika.setForeground(new java.awt.Color(0, 0, 0));
        chkEtika.setText("Etika Batuk");
        chkEtika.setBorderPainted(true);
        chkEtika.setBorderPaintedFlat(true);
        chkEtika.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEtika.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEtika.setName("chkEtika"); // NOI18N
        chkEtika.setOpaque(false);
        chkEtika.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkEtika);
        chkEtika.setBounds(470, 542, 90, 23);

        chkCaraBuang.setBackground(new java.awt.Color(255, 255, 250));
        chkCaraBuang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCaraBuang.setForeground(new java.awt.Color(0, 0, 0));
        chkCaraBuang.setText("Cara Buang Sampah Medis Dan Non Medis");
        chkCaraBuang.setBorderPainted(true);
        chkCaraBuang.setBorderPaintedFlat(true);
        chkCaraBuang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCaraBuang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCaraBuang.setName("chkCaraBuang"); // NOI18N
        chkCaraBuang.setOpaque(false);
        chkCaraBuang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkCaraBuang);
        chkCaraBuang.setBounds(470, 570, 230, 23);

        chkTempat.setBackground(new java.awt.Color(255, 255, 250));
        chkTempat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTempat.setForeground(new java.awt.Color(0, 0, 0));
        chkTempat.setText("Tempat Toileting");
        chkTempat.setBorderPainted(true);
        chkTempat.setBorderPaintedFlat(true);
        chkTempat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTempat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTempat.setName("chkTempat"); // NOI18N
        chkTempat.setOpaque(false);
        chkTempat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkTempat);
        chkTempat.setBounds(470, 598, 110, 23);

        chkLainProPerawat.setBackground(new java.awt.Color(255, 255, 250));
        chkLainProPerawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainProPerawat.setForeground(new java.awt.Color(0, 0, 0));
        chkLainProPerawat.setText("Lain - Lain");
        chkLainProPerawat.setBorderPainted(true);
        chkLainProPerawat.setBorderPaintedFlat(true);
        chkLainProPerawat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainProPerawat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainProPerawat.setName("chkLainProPerawat"); // NOI18N
        chkLainProPerawat.setOpaque(false);
        chkLainProPerawat.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainProPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainProPerawatActionPerformed(evt);
            }
        });
        FormInput1.add(chkLainProPerawat);
        chkLainProPerawat.setBounds(165, 654, 75, 23);

        TpndPerawatLain.setBackground(new java.awt.Color(245, 250, 240));
        TpndPerawatLain.setForeground(new java.awt.Color(0, 0, 0));
        TpndPerawatLain.setName("TpndPerawatLain"); // NOI18N
        FormInput1.add(TpndPerawatLain);
        TpndPerawatLain.setBounds(242, 654, 480, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Profesi Nutrisionis : ");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput1.add(jLabel25);
        jLabel25.setBounds(0, 682, 160, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("ISI PENDIDIKAN KESEHATAN");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput1.add(jLabel26);
        jLabel26.setBounds(165, 682, 190, 23);

        chkDiet.setBackground(new java.awt.Color(255, 255, 250));
        chkDiet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDiet.setForeground(new java.awt.Color(0, 0, 0));
        chkDiet.setText("Diet Dan Nutrisi");
        chkDiet.setBorderPainted(true);
        chkDiet.setBorderPaintedFlat(true);
        chkDiet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDiet.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDiet.setName("chkDiet"); // NOI18N
        chkDiet.setOpaque(false);
        chkDiet.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkDiet);
        chkDiet.setBounds(165, 710, 106, 23);

        chkKonsulGiziRanap.setBackground(new java.awt.Color(255, 255, 250));
        chkKonsulGiziRanap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKonsulGiziRanap.setForeground(new java.awt.Color(0, 0, 0));
        chkKonsulGiziRanap.setText("Konsultasi Gizi Rawat Inap");
        chkKonsulGiziRanap.setBorderPainted(true);
        chkKonsulGiziRanap.setBorderPaintedFlat(true);
        chkKonsulGiziRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKonsulGiziRanap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKonsulGiziRanap.setName("chkKonsulGiziRanap"); // NOI18N
        chkKonsulGiziRanap.setOpaque(false);
        chkKonsulGiziRanap.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkKonsulGiziRanap);
        chkKonsulGiziRanap.setBounds(280, 710, 160, 23);

        chkKonsulGiziRalan.setBackground(new java.awt.Color(255, 255, 250));
        chkKonsulGiziRalan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKonsulGiziRalan.setForeground(new java.awt.Color(0, 0, 0));
        chkKonsulGiziRalan.setText("Konsultasi Gizi Rawat Jalan");
        chkKonsulGiziRalan.setBorderPainted(true);
        chkKonsulGiziRalan.setBorderPaintedFlat(true);
        chkKonsulGiziRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKonsulGiziRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKonsulGiziRalan.setName("chkKonsulGiziRalan"); // NOI18N
        chkKonsulGiziRalan.setOpaque(false);
        chkKonsulGiziRalan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkKonsulGiziRalan);
        chkKonsulGiziRalan.setBounds(450, 710, 160, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        TpndNutrisionisLain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TpndNutrisionisLain.setColumns(20);
        TpndNutrisionisLain.setRows(5);
        TpndNutrisionisLain.setName("TpndNutrisionisLain"); // NOI18N
        TpndNutrisionisLain.setPreferredSize(new java.awt.Dimension(162, 2000));
        scrollPane14.setViewportView(TpndNutrisionisLain);

        FormInput1.add(scrollPane14);
        scrollPane14.setBounds(165, 738, 560, 74);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Profesi Admisi : ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput1.add(jLabel27);
        jLabel27.setBounds(0, 818, 160, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("ISI PENDIDIKAN KESEHATAN");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput1.add(jLabel28);
        jLabel28.setBounds(165, 818, 190, 23);

        chkHak.setBackground(new java.awt.Color(255, 255, 250));
        chkHak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHak.setForeground(new java.awt.Color(0, 0, 0));
        chkHak.setText("Hak Dan Kewajiban Pasien");
        chkHak.setBorderPainted(true);
        chkHak.setBorderPaintedFlat(true);
        chkHak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHak.setName("chkHak"); // NOI18N
        chkHak.setOpaque(false);
        chkHak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkHak);
        chkHak.setBounds(165, 846, 160, 23);

        chkJam.setBackground(new java.awt.Color(255, 255, 250));
        chkJam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJam.setForeground(new java.awt.Color(0, 0, 0));
        chkJam.setText("Jam Konsultasi, Biaya, Tata Tertib, Fasilitas RS");
        chkJam.setBorderPainted(true);
        chkJam.setBorderPaintedFlat(true);
        chkJam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJam.setName("chkJam"); // NOI18N
        chkJam.setOpaque(false);
        chkJam.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkJam);
        chkJam.setBounds(165, 874, 250, 23);

        chkInfoKejadian.setBackground(new java.awt.Color(255, 255, 250));
        chkInfoKejadian.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkInfoKejadian.setForeground(new java.awt.Color(0, 0, 0));
        chkInfoKejadian.setText("Informasi Kejadian Yang Tidak Diharapkan");
        chkInfoKejadian.setBorderPainted(true);
        chkInfoKejadian.setBorderPaintedFlat(true);
        chkInfoKejadian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInfoKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInfoKejadian.setName("chkInfoKejadian"); // NOI18N
        chkInfoKejadian.setOpaque(false);
        chkInfoKejadian.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkInfoKejadian);
        chkInfoKejadian.setBounds(350, 846, 230, 23);

        scrollPane15.setName("scrollPane15"); // NOI18N

        TpndAdmisiLain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TpndAdmisiLain.setColumns(20);
        TpndAdmisiLain.setRows(5);
        TpndAdmisiLain.setName("TpndAdmisiLain"); // NOI18N
        TpndAdmisiLain.setPreferredSize(new java.awt.Dimension(162, 2000));
        scrollPane15.setViewportView(TpndAdmisiLain);

        FormInput1.add(scrollPane15);
        scrollPane15.setBounds(165, 902, 560, 74);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Profesi Lainnya : ");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput1.add(jLabel29);
        jLabel29.setBounds(0, 982, 160, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("ISI PENDIDIKAN KESEHATAN");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput1.add(jLabel30);
        jLabel30.setBounds(165, 982, 190, 23);

        scrollPane16.setName("scrollPane16"); // NOI18N

        TpndLainyaLain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TpndLainyaLain.setColumns(20);
        TpndLainyaLain.setRows(5);
        TpndLainyaLain.setName("TpndLainyaLain"); // NOI18N
        TpndLainyaLain.setPreferredSize(new java.awt.Dimension(162, 2000));
        scrollPane16.setViewportView(TpndLainyaLain);

        FormInput1.add(scrollPane16);
        scrollPane16.setBounds(165, 1010, 560, 74);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Tingkat Pemahaman : ");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput1.add(jLabel31);
        jLabel31.setBounds(0, 1090, 160, 23);

        cmbTingkat.setForeground(new java.awt.Color(0, 0, 0));
        cmbTingkat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1 - Edukasi Pertama", "2 - ReEdukasi ke 2/3 atau Lebih" }));
        cmbTingkat.setName("cmbTingkat"); // NOI18N
        cmbTingkat.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput1.add(cmbTingkat);
        cmbTingkat.setBounds(165, 1090, 185, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Evaluasi Respon :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput1.add(jLabel32);
        jLabel32.setBounds(350, 1090, 110, 23);

        cmbEvaluasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbEvaluasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1 - Tidak Mengerti", "2 - Menyatakan Pemahaman", "3 - Mampu Menjelaskan", "4 - Mampu Demonstrasi / Simulasi" }));
        cmbEvaluasi.setName("cmbEvaluasi"); // NOI18N
        cmbEvaluasi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput1.add(cmbEvaluasi);
        cmbEvaluasi.setBounds(466, 1090, 195, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Nama Petugas Yang Memberikan Pendidikan Kesehatan : ");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput1.add(jLabel33);
        jLabel33.setBounds(0, 122, 323, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setBackground(new java.awt.Color(245, 250, 240));
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        FormInput1.add(TnmPetugas);
        TnmPetugas.setBounds(326, 122, 360, 23);

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
        FormInput1.add(BtnPetugas);
        BtnPetugas.setBounds(690, 122, 28, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Nama Penerima Edukasi : ");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput1.add(jLabel34);
        jLabel34.setBounds(0, 1118, 160, 23);

        TnmPenerima.setBackground(new java.awt.Color(245, 250, 240));
        TnmPenerima.setForeground(new java.awt.Color(0, 0, 0));
        TnmPenerima.setName("TnmPenerima"); // NOI18N
        FormInput1.add(TnmPenerima);
        TnmPenerima.setBounds(165, 1118, 440, 23);

        ScrollTriase2.setViewportView(FormInput1);

        internalFrame4.add(ScrollTriase2);

        FormInput3.setBorder(null);
        FormInput3.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis..!!");
        FormInput3.setName("FormInput3"); // NOI18N
        FormInput3.setPreferredSize(new java.awt.Dimension(870, 718));
        FormInput3.setLayout(new java.awt.BorderLayout());

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Data Penilaian Pemberian Pendidikan Kesehatan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPenilaian.setAutoCreateRowSorter(true);
        tbPenilaian.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPenilaian.setName("tbPenilaian"); // NOI18N
        tbPenilaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPenilaianMouseClicked(evt);
            }
        });
        tbPenilaian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPenilaianKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPenilaian);

        FormInput3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Penilaian :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel19);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-03-2025" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari3);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-03-2025" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari4);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass9.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('3');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+3");
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
        panelGlass9.add(BtnCari1);

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

        FormInput3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        internalFrame4.add(FormInput3);

        TabEdukasi.addTab("Penilaian Pemberian Pendidikan Kesehatan", internalFrame4);

        internalFrame1.add(TabEdukasi, java.awt.BorderLayout.CENTER);

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
        if (TabEdukasi.getSelectedIndex() == 0) {
            simpanInformasiEdukasi();
        } else {
        
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        if (TabEdukasi.getSelectedIndex() == 0) {
            emptTeks();
            tampil();
        } else {
            
        }
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
    if (TabEdukasi.getSelectedIndex() == 0) {
        hapusInformasiEdukasi();
    } else {

    }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TabEdukasi.getSelectedIndex() == 0) {
            gantiInformasiEdukasi();
        } else {
            
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
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari1);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbPenilaian.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));

            Valid.MyReport("rptAsesmenKeperawatanPerinatologi2.jasper", "report", "::[ Asesmen Keperawatan Perinatologi Hal. 2 ]::",
                    "SELECT now() tanggal", param);
            Valid.MyReport("rptAsesmenKeperawatanPerinatologi1.jasper", "report", "::[ Asesmen Keperawatan Perinatologi Hal. 1 ]::",
                    "SELECT now() tanggal", param);            
            
            TabEdukasi.setSelectedIndex(1);
            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
        }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampil();
}//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari1, BtnAll);
        }
}//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        if (TabEdukasi.getSelectedIndex() == 0) {
            TCari.setText("");
            tampil();
        } else {
            TCari1.setText("");
        }
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari1.setText("");
            tampil();
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (Sequel.cariInteger("select count(-1) from pemberian_informasi_edukasi where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabEdukasi.setSelectedIndex(1);
            tampil();
        } else if (Sequel.cariInteger("select count(-1) from pemberian_informasi_edukasi where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabEdukasi.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void TabEdukasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabEdukasiMouseClicked
        if (TabEdukasi.getSelectedIndex() == 0) {
            tampil();
        } else {
        
        }
    }//GEN-LAST:event_TabEdukasiMouseClicked

    private void tbPenilaianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPenilaianKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPenilaianKeyPressed

    private void tbPenilaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPenilaianMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {                
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbPenilaian.getSelectedColumn() == 0)) {
                TabEdukasi.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tbPenilaianMouseClicked

    private void cmbBicaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBicaraActionPerformed
        Tkapan.setText("");
        if (cmbBicara.getSelectedIndex() == 3) {
            Tkapan.setEnabled(true);
            Tkapan.requestFocus();
        } else {
            Tkapan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbBicaraActionPerformed

    private void chkBhsIndonesiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBhsIndonesiaActionPerformed
        cmbBhsIndo.setSelectedIndex(0);
        if (chkBhsIndonesia.isSelected() == true) {
            cmbBhsIndo.setEnabled(true);
            cmbBhsIndo.requestFocus();
        } else {
            cmbBhsIndo.setEnabled(false);
        }
    }//GEN-LAST:event_chkBhsIndonesiaActionPerformed

    private void TkapanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkapanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkBhsIndonesia.requestFocus();
        }
    }//GEN-LAST:event_TkapanKeyPressed

    private void chkDaerahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDaerahActionPerformed
        Tdaerah.setText("");
        if (chkDaerah.isSelected() == true) {
            Tdaerah.setEnabled(true);
            Tdaerah.requestFocus();
        } else {
            Tdaerah.setEnabled(false);
        }
    }//GEN-LAST:event_chkDaerahActionPerformed

    private void chkBhsInggrisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBhsInggrisActionPerformed
        cmbBhsInggris.setSelectedIndex(0);
        if (chkBhsInggris.isSelected() == true) {
            cmbBhsInggris.setEnabled(true);
            cmbBhsInggris.requestFocus();
        } else {
            cmbBhsInggris.setEnabled(false);
        }
    }//GEN-LAST:event_chkBhsInggrisActionPerformed

    private void TdaerahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdaerahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkBhsLainya.requestFocus();
        }
    }//GEN-LAST:event_TdaerahKeyPressed

    private void chkBhsLainyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBhsLainyaActionPerformed
        TbhsLainya.setText("");
        if (chkBhsLainya.isSelected() == true) {
            TbhsLainya.setEnabled(true);
            TbhsLainya.requestFocus();
        } else {
            TbhsLainya.setEnabled(false);
        }
    }//GEN-LAST:event_chkBhsLainyaActionPerformed

    private void TbhsLainyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbhsLainyaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPerlu.requestFocus();
        }
    }//GEN-LAST:event_TbhsLainyaKeyPressed

    private void cmbPerluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPerluActionPerformed
        Tperlu.setText("");
        if (cmbPerlu.getSelectedIndex() == 2) {
            Tperlu.setEnabled(true);
            Tperlu.requestFocus();
        } else {
            Tperlu.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPerluActionPerformed

    private void chkPotensialLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPotensialLainActionPerformed
        TPotensialLain.setText("");
        if (chkPotensialLain.isSelected() == true) {
            TPotensialLain.setEnabled(true);
            TPotensialLain.requestFocus();
        } else {
            TPotensialLain.setEnabled(false);
        }
    }//GEN-LAST:event_chkPotensialLainActionPerformed

    private void TperluKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TperluKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbNilaiPasien.requestFocus();
        }
    }//GEN-LAST:event_TperluKeyPressed

    private void tbPemberianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemberianMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPemberianMouseClicked

    private void tbPemberianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemberianKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPemberianKeyPressed

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

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void cmbProfesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProfesiActionPerformed
        Tprofesi.setText("");
        chkDiagnosis.setSelected(false);
        chkKondisi.setSelected(false);
        chkTindakan.setSelected(false);
        chkTataCara.setSelected(false);
        chkManfaat.setSelected(false);
        chkNamaOrang.setSelected(false);
        chkKemungkinan.setSelected(false);
        chkPrognosis.setSelected(false);
        chkKemTdkTerduga.setSelected(false);
        chkKemBila.setSelected(false);
        
        chkPendidikanKes.setSelected(false);
        chkHasilAsuhan.setSelected(false);
        chkPenanganan.setSelected(false);
        chkPerawatan.setSelected(false);
        chkAlatAlat.setSelected(false);
        chkInformasi.setSelected(false);
        chkKeamanan.setSelected(false);
        chkProsedur.setSelected(false);
        chkFarmaObat.setSelected(false);
        chkFarmaInjek.setSelected(false);
        chkFarmaSedasi.setSelected(false);
        chkPerawatanLatihan.setSelected(false);
        chkDistraksi.setSelected(false);
        chkPengalihan.setSelected(false);
        chkEtika.setSelected(false);
        chkCaraBuang.setSelected(false);
        chkTempat.setSelected(false);
        chkCaraCuci.setSelected(false);
        chkLainProPerawat.setSelected(false);
        TpendidikanKes.setText("");
        TpndPerawatLain.setText("");
        
        chkDiet.setSelected(false);
        chkKonsulGiziRanap.setSelected(false);
        chkKonsulGiziRalan.setSelected(false);
        TpndNutrisionisLain.setText("");
        
        chkHak.setSelected(false);
        chkJam.setSelected(false);
        chkInfoKejadian.setSelected(false);
        TpndAdmisiLain.setText("");
        
        TpndLainyaLain.setText("");

        if (cmbProfesi.getSelectedIndex() == 1) {
            dokterTRUE();
            perawatBidanFALSE();
            nutrisionisFALSE();
            admisiFALSE();
            TpndLainyaLain.setEnabled(false);
        } else if (cmbProfesi.getSelectedIndex() == 2 || cmbProfesi.getSelectedIndex() == 3) {
            dokterFALSE();
            perawatBidanTRUE();
            nutrisionisFALSE();
            admisiFALSE();
            TpndLainyaLain.setEnabled(false);
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
        } else if (cmbProfesi.getSelectedIndex() == 4) {
            dokterFALSE();
            perawatBidanFALSE();
            nutrisionisTRUE();
            admisiFALSE();
            TpndLainyaLain.setEnabled(false);
        } else if (cmbProfesi.getSelectedIndex() == 5) {
            dokterFALSE();
            perawatBidanFALSE();
            nutrisionisFALSE();
            admisiTRUE();
            TpndLainyaLain.setEnabled(false);
        } else if (cmbProfesi.getSelectedIndex() == 6) {
            Tprofesi.setEnabled(true);
            Tprofesi.requestFocus();
            dokterFALSE();
            perawatBidanFALSE();
            nutrisionisFALSE();
            admisiFALSE();
            TpndLainyaLain.setEnabled(true);
        } else {
            Tprofesi.setEnabled(false);
            chkDiagnosis.setEnabled(false);
            chkKondisi.setEnabled(false);
            chkTindakan.setEnabled(false);
            chkTataCara.setEnabled(false);
            chkManfaat.setEnabled(false);
            chkNamaOrang.setEnabled(false);
            chkKemungkinan.setEnabled(false);
            chkPrognosis.setEnabled(false);
            chkKemTdkTerduga.setEnabled(false);
            chkKemBila.setEnabled(false);
            
            chkPendidikanKes.setEnabled(false);
            chkHasilAsuhan.setEnabled(false);
            chkPenanganan.setEnabled(false);
            chkPerawatan.setEnabled(false);
            chkAlatAlat.setEnabled(false);
            chkInformasi.setEnabled(false);
            chkKeamanan.setEnabled(false);
            chkProsedur.setEnabled(false);
            chkFarmaObat.setEnabled(false);
            chkFarmaInjek.setEnabled(false);
            chkFarmaSedasi.setEnabled(false);
            chkPerawatanLatihan.setEnabled(false);
            chkDistraksi.setEnabled(false);
            chkPengalihan.setEnabled(false);
            chkEtika.setEnabled(false);
            chkCaraBuang.setEnabled(false);
            chkTempat.setEnabled(false);
            chkCaraCuci.setEnabled(false);
            chkLainProPerawat.setEnabled(false);
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            
            chkDiet.setEnabled(false);
            chkKonsulGiziRanap.setEnabled(false);
            chkKonsulGiziRalan.setEnabled(false);
            TpndNutrisionisLain.setEnabled(false);
            
            chkHak.setEnabled(false);
            chkJam.setEnabled(false);
            chkInfoKejadian.setEnabled(false);
            TpndAdmisiLain.setEnabled(false);
            
            TpndLainyaLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbProfesiActionPerformed

    private void TprofesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprofesiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TpndLainyaLain.requestFocus();
        }
    }//GEN-LAST:event_TprofesiKeyPressed

    private void TpendidikanKesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpendidikanKesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkHasilAsuhan.requestFocus();
        }
    }//GEN-LAST:event_TpendidikanKesKeyPressed

    private void chkPendidikanKesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPendidikanKesActionPerformed
        TpendidikanKes.setText("");
        if (chkPendidikanKes.isSelected() == true) {
            TpendidikanKes.setEnabled(true);
            TpendidikanKes.requestFocus();
        } else {
            TpendidikanKes.setEnabled(false);
        }
    }//GEN-LAST:event_chkPendidikanKesActionPerformed

    private void chkLainProPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainProPerawatActionPerformed
        TpndPerawatLain.setText("");
        if (chkLainProPerawat.isSelected() == true) {
            TpndPerawatLain.setEnabled(true);
            TpndPerawatLain.requestFocus();
        } else {
            TpndPerawatLain.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainProPerawatActionPerformed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        akses.setform("RMPemberianInformasiEdukasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void cmbPenerimaanPndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPenerimaanPndActionPerformed
        TnmPenerima.setText("");
        if (cmbPenerimaanPnd.getSelectedIndex() == 1) {
            TnmPenerima.setText(TPasien1.getText());
        } else {
            TnmPenerima.setText("");
        }
    }//GEN-LAST:event_cmbPenerimaanPndActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPemberianInformasiEdukasi dialog = new RMPemberianInformasiEdukasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.InternalFrame FormAsesmen;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.PanelBiasa FormInput2;
    private widget.PanelBiasa FormInput3;
    private widget.Label LCount;
    private widget.Label LCount1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane ScrollTriase1;
    private widget.ScrollPane ScrollTriase2;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRM1;
    private widget.TextBox TNoRw;
    private widget.TextBox TNoRw1;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private widget.TextBox TPotensialLain;
    private javax.swing.JTabbedPane TabEdukasi;
    private widget.TextBox Tagama;
    private widget.TextBox TbhsLainya;
    private widget.TextBox Tdaerah;
    private widget.TextBox Tkapan;
    private widget.TextBox TnmPenerima;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TpendidikanKes;
    private widget.TextBox Tperlu;
    private widget.TextBox Tpnd;
    private widget.TextArea TpndAdmisiLain;
    private widget.TextArea TpndLainyaLain;
    private widget.TextArea TpndNutrisionisLain;
    private widget.TextBox TpndPerawatLain;
    private widget.TextBox Tprofesi;
    private widget.TextBox TrgRawat;
    private widget.TextBox TrgRawat1;
    private widget.Tanggal Ttgl;
    public widget.CekBox chkAlatAlat;
    public widget.CekBox chkAlatBantu;
    public widget.CekBox chkBahasa;
    public widget.CekBox chkBhsIndonesia;
    public widget.CekBox chkBhsInggris;
    public widget.CekBox chkBhsLainya;
    public widget.CekBox chkCaraBuang;
    public widget.CekBox chkCaraCuci;
    public widget.CekBox chkCemas;
    public widget.CekBox chkDaerah;
    public widget.CekBox chkDiagnosis;
    public widget.CekBox chkDiet;
    public widget.CekBox chkDistraksi;
    public widget.CekBox chkEmosi;
    public widget.CekBox chkEtika;
    public widget.CekBox chkFarmaInjek;
    public widget.CekBox chkFarmaObat;
    public widget.CekBox chkFarmaSedasi;
    public widget.CekBox chkHak;
    public widget.CekBox chkHasilAsuhan;
    public widget.CekBox chkHilangMemori;
    public widget.CekBox chkInfoKejadian;
    public widget.CekBox chkInformasi;
    public widget.CekBox chkJam;
    public widget.CekBox chkKeamanan;
    public widget.CekBox chkKemBila;
    public widget.CekBox chkKemTdkTerduga;
    public widget.CekBox chkKemungkinan;
    public widget.CekBox chkKognitif;
    public widget.CekBox chkKondisi;
    public widget.CekBox chkKonsulGiziRalan;
    public widget.CekBox chkKonsulGiziRanap;
    public widget.CekBox chkLainProPerawat;
    public widget.CekBox chkManfaat;
    public widget.CekBox chkMasalah;
    public widget.CekBox chkMotivasi;
    public widget.CekBox chkNamaOrang;
    public widget.CekBox chkNutrisi;
    public widget.CekBox chkPenanganan;
    public widget.CekBox chkPendengaran;
    public widget.CekBox chkPendidikanKes;
    public widget.CekBox chkPengalihan;
    public widget.CekBox chkPengobatan;
    public widget.CekBox chkPerawatan;
    public widget.CekBox chkPerawatanLatihan;
    public widget.CekBox chkPotensialLain;
    public widget.CekBox chkPrognosis;
    public widget.CekBox chkProsedur;
    public widget.CekBox chkProses;
    public widget.CekBox chkSecara;
    public widget.CekBox chkTataCara;
    public widget.CekBox chkTempat;
    public widget.CekBox chkTerapi;
    public widget.CekBox chkTidakAda;
    public widget.CekBox chkTidakDitemukan;
    public widget.CekBox chkTindakan;
    private widget.ComboBox cmbBhsIndo;
    private widget.ComboBox cmbBhsInggris;
    private widget.ComboBox cmbBicara;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbEvaluasi;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbKepercayaan;
    private widget.ComboBox cmbKesediaan;
    private widget.ComboBox cmbLainLain;
    private widget.ComboBox cmbMenolakDilakukan;
    private widget.ComboBox cmbMenolakDilayani;
    private widget.ComboBox cmbMenolakPulang;
    private widget.ComboBox cmbMenolakVaksin;
    private widget.ComboBox cmbMetode;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbNilaiPasien;
    private widget.ComboBox cmbPenerimaanPnd;
    private widget.ComboBox cmbPenggunaanHerbal;
    private widget.ComboBox cmbPerlu;
    private widget.ComboBox cmbProfesi;
    private widget.ComboBox cmbPuasa;
    private widget.ComboBox cmbTidakMemakan;
    private widget.ComboBox cmbTingkat;
    private widget.ComboBox cmbVegetarian;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel146;
    private widget.Label jLabel147;
    private widget.Label jLabel148;
    private widget.Label jLabel149;
    private widget.Label jLabel15;
    private widget.Label jLabel150;
    private widget.Label jLabel151;
    private widget.Label jLabel152;
    private widget.Label jLabel153;
    private widget.Label jLabel154;
    private widget.Label jLabel155;
    private widget.Label jLabel156;
    private widget.Label jLabel157;
    private widget.Label jLabel158;
    private widget.Label jLabel159;
    private widget.Label jLabel16;
    private widget.Label jLabel160;
    private widget.Label jLabel161;
    private widget.Label jLabel162;
    private widget.Label jLabel163;
    private widget.Label jLabel164;
    private widget.Label jLabel165;
    private widget.Label jLabel166;
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
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.Table tbPemberian;
    private widget.Table tbPenilaian;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT pi.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, "
                    + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tglLahir, DATE_FORMAT(pi.waktu_simpan,'%d-%m-%Y') tglEdukasi, TIME_FORMAT(pi.waktu_simpan,'%H:%i Wita') jamEdukasi "
                    + "FROM pemberian_informasi_edukasi pi INNER JOIN reg_periksa rp on rp.no_rawat=pi.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                    + "date(pi.waktu_simpan) between ? and ? and pi.no_rawat like ? or "
                    + "date(pi.waktu_simpan) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(pi.waktu_simpan) between ? and ? and p.nm_pasien like ? or "
                    + "date(pi.waktu_simpan) between ? and ? and pi.ruang_rawat like ? order by pi.waktu_simpan desc");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari1.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari1.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari1.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari1.getText().trim() + "%");                
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jenkel"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglEdukasi"),
                        rs.getString("jamEdukasi"),
                        rs.getString("bahasa"),
                        rs.getString("pendengaran"),
                        rs.getString("masalah_penglihatan"),
                        rs.getString("hilang_memori"),
                        rs.getString("tidak_ada_partisipasi"),
                        rs.getString("secara_fisiologi"),
                        rs.getString("tidak_ditemukan_hambatan"),
                        rs.getString("cemas"),
                        rs.getString("emosi"),
                        rs.getString("kognitif"),
                        rs.getString("motifasi_buruk"),
                        rs.getString("bicara"),
                        rs.getString("ket_kapan"),
                        rs.getString("bahasa_indonesia"),
                        rs.getString("indonesia"),
                        rs.getString("bahasa_daerah"),
                        rs.getString("ket_daerah"),
                        rs.getString("bahasa_inggris"),
                        rs.getString("inggris"),
                        rs.getString("bahasa_lainnya"),
                        rs.getString("ket_bahasa_lainnya"),
                        rs.getString("penerjemah"),
                        rs.getString("ket_penerjemah"),
                        rs.getString("nilai_pasien"),
                        rs.getString("kesediaan_menerima"),
                        rs.getString("proses_penyakit"),
                        rs.getString("pengobatan"),
                        rs.getString("alat_bantu_medis"),
                        rs.getString("lain_lain"),
                        rs.getString("ket_lainlain"),
                        rs.getString("terapi_obat"),
                        rs.getString("nutrisi"),
                        rs.getString("penggunaan_herbal"),
                        rs.getString("vegetarian"),
                        rs.getString("menolak_vaksinasi"),
                        rs.getString("kepercayaan_terhadap"),
                        rs.getString("puasa"),
                        rs.getString("menolak_dilakukan"),
                        rs.getString("menolak_pulang"),
                        rs.getString("menolak_dilayani"),
                        rs.getString("tidak_memakan"),
                        rs.getString("lain_lain_identifikasi"),
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
        chkBahasa.setSelected(false);
        chkPendengaran.setSelected(false);
        chkMasalah.setSelected(false);
        chkHilangMemori.setSelected(false);
        chkTidakAda.setSelected(false);
        chkSecara.setSelected(false);
        chkTidakDitemukan.setSelected(false);
        chkCemas.setSelected(false);
        chkEmosi.setSelected(false);
        chkKognitif.setSelected(false);
        chkMotivasi.setSelected(false);
        cmbBicara.setSelectedIndex(0);        
        Tkapan.setText("");
        Tkapan.setEnabled(false);
        chkBhsIndonesia.setSelected(false);
        cmbBhsIndo.setSelectedIndex(0);
        cmbBhsIndo.setEnabled(false);
        chkBhsInggris.setSelected(false);
        cmbBhsInggris.setSelectedIndex(0);
        cmbBhsInggris.setEnabled(false);
        chkDaerah.setSelected(false);
        Tdaerah.setText("");
        Tdaerah.setEnabled(false);
        chkBhsLainya.setSelected(false);
        TbhsLainya.setText("");
        TbhsLainya.setEnabled(false);
        cmbPerlu.setSelectedIndex(0);
        Tperlu.setText("");
        Tperlu.setEnabled(false);
        cmbNilaiPasien.setSelectedIndex(0);
        cmbKesediaan.setSelectedIndex(0);
        chkProses.setSelected(false);
        chkPengobatan.setSelected(false);
        chkAlatBantu.setSelected(false);
        chkTerapi.setSelected(false);
        chkNutrisi.setSelected(false);
        chkPotensialLain.setSelected(false);
        TPotensialLain.setText("");
        TPotensialLain.setEnabled(false);
        cmbPenggunaanHerbal.setSelectedIndex(0);
        cmbVegetarian.setSelectedIndex(0);
        cmbMenolakVaksin.setSelectedIndex(0);
        cmbKepercayaan.setSelectedIndex(0);
        cmbPuasa.setSelectedIndex(0);
        cmbMenolakDilakukan.setSelectedIndex(0);
        cmbMenolakPulang.setSelectedIndex(0);
        cmbMenolakDilayani.setSelectedIndex(0);
        cmbTidakMemakan.setSelectedIndex(0);
        cmbLainLain.setSelectedIndex(0);
    }
    
    public void setData(String norwt, String rgrawat) {
        TNoRw.setText(norwt);
        TNoRw1.setText(norwt);
        TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norwt + "'"));
        TNoRM1.setText(TNoRM.getText());
        TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        TPasien1.setText(TPasien.getText());
        TrgRawat.setText(rgrawat);
        TrgRawat1.setText(rgrawat);
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        Valid.SetTgl(DTPCari3, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(new Date());
        DTPCari4.setDate(new Date());
        Tpnd.setText(Sequel.cariIsi("select pnd from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        Tagama.setText(Sequel.cariIsi("select agama from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        TCari1.setText(norwt);
        TCari.setText(norwt);        
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnPrint.setEnabled(akses.getcppt());
        BtnEdit.setEnabled(akses.getcppt());
        
        if (akses.getjml2() >= 1) {
            nip = akses.getkode();
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPetugas, nip);
            if (TnmPetugas.getText().equals("")) {
                nip = "-";
                TnmPetugas.setText("-");
            }
        }  
    }

    private void getData() {
        if (TabEdukasi.getSelectedIndex() == 0) {
            variabelBersihInformasiEdukasi();
            if (tbPemberian.getSelectedRow() != -1) {
                TCari.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 0).toString());
                TNoRw.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 0).toString());
                TNoRM.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 1).toString());
                TPasien.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 2).toString());
                TrgRawat.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 5).toString());
                bahasa = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 8).toString();
                pendengaran = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 9).toString();
                masalahPenglihatan = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 10).toString();
                hilangMemori = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 11).toString();
                tidakAdaPartisipasi = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 12).toString();
                secaraFisiologi = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 13).toString();
                tidakDitemukanHambatan = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 14).toString();
                cemas = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 15).toString();
                emosi = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 16).toString();
                kognitif = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 17).toString();
                motifasiBuruk = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 18).toString();
                cmbBicara.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 19).toString());
                Tkapan.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 20).toString());
                bahasaIndonesia = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 21).toString();
                cmbBhsIndo.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 22).toString());
                bahasaDaerah = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 23).toString();
                Tdaerah.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 24).toString());
                bahasaInggris = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 25).toString();
                cmbBhsInggris.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 26).toString());
                bahasaLainnya = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 27).toString();
                TbhsLainya.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 28).toString());
                cmbPerlu.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 29).toString());
                Tperlu.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 30).toString());
                Tpnd.setText(Sequel.cariIsi("select pnd from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
                Tagama.setText(Sequel.cariIsi("select agama from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
                cmbNilaiPasien.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 31).toString());
                cmbKesediaan.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 32).toString());
                prosesPenyakit = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 33).toString();
                pengobatan = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 34).toString();
                alatBantuMedis = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 35).toString();
                lainLain = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 36).toString();
                TPotensialLain.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 37).toString());
                terapiObat = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 38).toString();
                nutrisi = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 39).toString();
                cmbPenggunaanHerbal.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 40).toString());
                cmbVegetarian.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 41).toString());
                cmbMenolakVaksin.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 42).toString());
                cmbKepercayaan.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 43).toString());
                cmbPuasa.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 44).toString());
                cmbMenolakDilakukan.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 45).toString());
                cmbMenolakPulang.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 46).toString());
                cmbMenolakDilayani.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 47).toString());
                cmbTidakMemakan.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 48).toString());
                cmbLainLain.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 49).toString());
                dataCekPemberian();
            }
        } else {
            
        }
    }
    
    private void cekDataPemberian() {
        if (chkBahasa.isSelected() == true) {
            bahasa = "ya";
        } else {
            bahasa = "tidak";
        }
        
        if (chkPendengaran.isSelected() == true) {
            pendengaran = "ya";
        } else {
            pendengaran = "tidak";
        }
        
        if (chkMasalah.isSelected() == true) {
            masalahPenglihatan = "ya";
        } else {
            masalahPenglihatan = "tidak";
        }
        
        if (chkHilangMemori.isSelected() == true) {
            hilangMemori = "ya";
        } else {
            hilangMemori = "tidak";
        }
        
        if (chkTidakAda.isSelected() == true) {
            tidakAdaPartisipasi = "ya";
        } else {
            tidakAdaPartisipasi = "tidak";
        }
        
        if (chkSecara.isSelected() == true) {
            secaraFisiologi = "ya";
        } else {
            secaraFisiologi = "tidak";
        }
        
        if (chkTidakDitemukan.isSelected() == true) {
            tidakDitemukanHambatan = "ya";
        } else {
            tidakDitemukanHambatan = "tidak";
        }
        
        if (chkCemas.isSelected() == true) {
            cemas = "ya";
        } else {
            cemas = "tidak";
        }
        
        if (chkEmosi.isSelected() == true) {
            emosi = "ya";
        } else {
            emosi = "tidak";
        }
        
        if (chkKognitif.isSelected() == true) {
            kognitif = "ya";
        } else {
            kognitif = "tidak";
        }
        
        if (chkMotivasi.isSelected() == true) {
            motifasiBuruk = "ya";
        } else {
            motifasiBuruk = "tidak";
        }
        
        if (chkBhsIndonesia.isSelected() == true) {
            bahasaIndonesia = "ya";
        } else {
            bahasaIndonesia = "tidak";
        }
        
        if (chkDaerah.isSelected() == true) {
            bahasaDaerah = "ya";
        } else {
            bahasaDaerah = "tidak";
        }
        
        if (chkBhsInggris.isSelected() == true) {
            bahasaInggris = "ya";
        } else {
            bahasaInggris = "tidak";
        }
        
        if (chkBhsLainya.isSelected() == true) {
            bahasaLainnya = "ya";
        } else {
            bahasaLainnya = "tidak";
        }
        
        if (chkProses.isSelected() == true) {
            prosesPenyakit = "ya";
        } else {
            prosesPenyakit = "tidak";
        }
        
        if (chkPengobatan.isSelected() == true) {
            pengobatan = "ya";
        } else {
            pengobatan = "tidak";
        }
        
        if (chkAlatBantu.isSelected() == true) {
            alatBantuMedis = "ya";
        } else {
            alatBantuMedis = "tidak";
        }
        
        if (chkPotensialLain.isSelected() == true) {
            lainLain = "ya";
        } else {
            lainLain = "tidak";
        }
        
        if (chkTerapi.isSelected() == true) {
            terapiObat = "ya";
        } else {
            terapiObat = "tidak";
        }
        
        if (chkNutrisi.isSelected() == true) {
            nutrisi = "ya";
        } else {
            nutrisi = "tidak";
        }
    }
    
    private void dataCekPemberian() {
        if (bahasa.equals("ya")) {
            chkBahasa.setSelected(true);
        } else {
            chkBahasa.setSelected(false);
        }
        
        if (pendengaran.equals("ya")) {
            chkPendengaran.setSelected(true);
        } else {
            chkPendengaran.setSelected(false);
        }
        
        if (masalahPenglihatan.equals("ya")) {
            chkMasalah.setSelected(true);
        } else {
            chkMasalah.setSelected(false);
        }
        
        if (hilangMemori.equals("ya")) {
            chkHilangMemori.setSelected(true);
        } else {
            chkHilangMemori.setSelected(false);
        }
        
        if (tidakAdaPartisipasi.equals("ya")) {
            chkTidakAda.setSelected(true);
        } else {
            chkTidakAda.setSelected(false);
        }
        
        if (secaraFisiologi.equals("ya")) {
            chkSecara.setSelected(true);
        } else {
            chkSecara.setSelected(false);
        }
        
        if (tidakDitemukanHambatan.equals("ya")) {
            chkTidakDitemukan.setSelected(true);
        } else {
            chkTidakDitemukan.setSelected(false);
        }
        
        if (cemas.equals("ya")) {
            chkCemas.setSelected(true);
        } else {
            chkCemas.setSelected(false);
        }
        
        if (emosi.equals("ya")) {
            chkEmosi.setSelected(true);
        } else {
            chkEmosi.setSelected(false);
        }
        
        if (kognitif.equals("ya")) {
            chkKognitif.setSelected(true);
        } else {
            chkKognitif.setSelected(false);
        }
        
        if (motifasiBuruk.equals("ya")) {
            chkMotivasi.setSelected(true);
        } else {
            chkMotivasi.setSelected(false);
        }
        
        if (bahasaIndonesia.equals("ya")) {
            chkBhsIndonesia.setSelected(true);
            cmbBhsIndo.setEnabled(true);
        } else {
            chkBhsIndonesia.setSelected(false);
            cmbBhsIndo.setEnabled(false);
        }
        
        if (bahasaDaerah.equals("ya")) {
            chkDaerah.setSelected(true);
            Tdaerah.setEnabled(true);
        } else {
            chkDaerah.setSelected(false);
            Tdaerah.setEnabled(false);
        }
        
        if (bahasaInggris.equals("ya")) {
            chkBhsInggris.setSelected(true);
            cmbBhsInggris.setEnabled(true);
        } else {
            chkBhsInggris.setSelected(false);
            cmbBhsInggris.setEnabled(false);
        }
        
        if (bahasaLainnya.equals("ya")) {
            chkBhsLainya.setSelected(true);
            TbhsLainya.setEnabled(true);
        } else {
            chkBhsLainya.setSelected(false);
            TbhsLainya.setEnabled(false);
        }
        
        if (prosesPenyakit.equals("ya")) {
            chkProses.setSelected(true);
        } else {
            chkProses.setSelected(false);
        }
        
        if (pengobatan.equals("ya")) {
            chkPengobatan.setSelected(true);
        } else {
            chkPengobatan.setSelected(false);
        }
        
        if (alatBantuMedis.equals("ya")) {
            chkAlatBantu.setSelected(true);
        } else {
            chkAlatBantu.setSelected(false);
        }
        
        if (lainLain.equals("ya")) {
            chkPotensialLain.setSelected(true);
            TPotensialLain.setEnabled(true);
        } else {
            chkPotensialLain.setSelected(false);
            TPotensialLain.setEnabled(false);
        }
        
        if (terapiObat.equals("ya")) {
            chkTerapi.setSelected(true);
        } else {
            chkTerapi.setSelected(false);
        }
        
        if (nutrisi.equals("ya")) {
            chkNutrisi.setSelected(true);
        } else {
            chkNutrisi.setSelected(false);
        }
    }
    
    private void variabelBersihInformasiEdukasi() {
        nip = "";
        bahasa = "";
        pendengaran = "";
        masalahPenglihatan = "";
        hilangMemori = "";
        tidakAdaPartisipasi = "";
        secaraFisiologi = "";
        tidakDitemukanHambatan = "";
        cemas = "";
        emosi = "";
        kognitif = "";
        motifasiBuruk = "";
        bahasaIndonesia = "";
        bahasaDaerah = "";
        bahasaInggris = "";
        bahasaLainnya = "";
        prosesPenyakit = "";
        pengobatan = "";
        alatBantuMedis = "";
        lainLain = "";
        terapiObat = "";
        nutrisi = "";
    }
    
    public void setTampil(){
       TabEdukasi.setSelectedIndex(1);
       tampil();
    }
    
    private void simpanInformasiEdukasi() {
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            cekDataPemberian();
            if (Sequel.menyimpantf("pemberian_informasi_edukasi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 45, new String[]{
                TNoRw.getText(), TrgRawat.getText(), bahasa, pendengaran, masalahPenglihatan, hilangMemori, tidakAdaPartisipasi, secaraFisiologi, tidakDitemukanHambatan,
                cemas, emosi, kognitif, motifasiBuruk, cmbBicara.getSelectedItem().toString(), Tkapan.getText(), bahasaIndonesia, cmbBhsIndo.getSelectedItem().toString(),
                bahasaDaerah, Tdaerah.getText(), bahasaInggris, cmbBhsInggris.getSelectedItem().toString(), bahasaLainnya, TbhsLainya.getText(), cmbPerlu.getSelectedItem().toString(),
                Tperlu.getText(), cmbNilaiPasien.getSelectedItem().toString(), cmbKesediaan.getSelectedItem().toString(), prosesPenyakit, pengobatan, alatBantuMedis,
                lainLain, TPotensialLain.getText(), terapiObat, nutrisi, cmbPenggunaanHerbal.getSelectedItem().toString(), cmbVegetarian.getSelectedItem().toString(),
                cmbMenolakVaksin.getSelectedItem().toString(), cmbKepercayaan.getSelectedItem().toString(), cmbPuasa.getSelectedItem().toString(), cmbMenolakDilakukan.getSelectedItem().toString(),
                cmbMenolakPulang.getSelectedItem().toString(), cmbMenolakDilayani.getSelectedItem().toString(), cmbTidakMemakan.getSelectedItem().toString(),
                cmbLainLain.getSelectedItem().toString(), Sequel.cariIsi("select now()")
            }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Pemberian Informasi Dan Edukasi", "Simpan");
                TCari1.setText(TNoRw.getText());
                tampil();
                emptTeks();
            }
        }
    }
    
    private void gantiInformasiEdukasi() {
        if (tbPemberian.getSelectedRow() > -1) {
            cekDataPemberian();
            if (Sequel.mengedittf("pemberian_informasi_edukasi", "no_rawat=?", "bahasa=?, pendengaran=?, masalah_penglihatan=?, hilang_memori=?, "
                    + "tidak_ada_partisipasi=?, secara_fisiologi=?, tidak_ditemukan_hambatan=?, cemas=?, emosi=?, kognitif=?, motifasi_buruk=?, bicara=?, ket_kapan=?, "
                    + "bahasa_indonesia=?, indonesia=?, bahasa_daerah=?, ket_daerah=?, bahasa_inggris=?, inggris=?, bahasa_lainnya=?, ket_bahasa_lainnya=?, penerjemah=?, "
                    + "ket_penerjemah=?, nilai_pasien=?, kesediaan_menerima=?, proses_penyakit=?, pengobatan=?, alat_bantu_medis=?, lain_lain=?, ket_lainlain=?, "
                    + "terapi_obat=?, nutrisi=?, penggunaan_herbal=?, vegetarian=?, menolak_vaksinasi=?, kepercayaan_terhadap=?, puasa=?, menolak_dilakukan=?, "
                    + "menolak_pulang=?, menolak_dilayani=?, tidak_memakan=?, lain_lain_identifikasi=?", 43, new String[]{
                        bahasa, pendengaran, masalahPenglihatan, hilangMemori, tidakAdaPartisipasi, secaraFisiologi, tidakDitemukanHambatan,
                        cemas, emosi, kognitif, motifasiBuruk, cmbBicara.getSelectedItem().toString(), Tkapan.getText(), bahasaIndonesia,
                        cmbBhsIndo.getSelectedItem().toString(), bahasaDaerah, Tdaerah.getText(), bahasaInggris, cmbBhsInggris.getSelectedItem().toString(),
                        bahasaLainnya, TbhsLainya.getText(), cmbPerlu.getSelectedItem().toString(), Tperlu.getText(), cmbNilaiPasien.getSelectedItem().toString(),
                        cmbKesediaan.getSelectedItem().toString(), prosesPenyakit, pengobatan, alatBantuMedis, lainLain, TPotensialLain.getText(), terapiObat, nutrisi,
                        cmbPenggunaanHerbal.getSelectedItem().toString(), cmbVegetarian.getSelectedItem().toString(), cmbMenolakVaksin.getSelectedItem().toString(),
                        cmbKepercayaan.getSelectedItem().toString(), cmbPuasa.getSelectedItem().toString(), cmbMenolakDilakukan.getSelectedItem().toString(),
                        cmbMenolakPulang.getSelectedItem().toString(), cmbMenolakDilayani.getSelectedItem().toString(), cmbTidakMemakan.getSelectedItem().toString(),
                        cmbLainLain.getSelectedItem().toString(),
                        tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 0).toString()
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Pemberian Informasi Dan Edukasi", "Ganti");
                TCari1.setText(TNoRw.getText());
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }

    private void hapusInformasiEdukasi() {
        if (tbPemberian.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin semua data pemberian informasi & edukasi termasuk penilaiannya mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from pemberian_informasi_edukasi where no_rawat=?", 1, new String[]{
                    tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 0).toString()
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
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }

    private void dokterTRUE() {
        chkDiagnosis.setEnabled(true);
        chkKondisi.setEnabled(true);
        chkTindakan.setEnabled(true);
        chkTataCara.setEnabled(true);
        chkManfaat.setEnabled(true);
        chkNamaOrang.setEnabled(true);
        chkKemungkinan.setEnabled(true);
        chkPrognosis.setEnabled(true);
        chkKemTdkTerduga.setEnabled(true);
        chkKemBila.setEnabled(true);
    }

    private void dokterFALSE() {
        chkDiagnosis.setEnabled(false);
        chkKondisi.setEnabled(false);
        chkTindakan.setEnabled(false);
        chkTataCara.setEnabled(false);
        chkManfaat.setEnabled(false);
        chkNamaOrang.setEnabled(false);
        chkKemungkinan.setEnabled(false);
        chkPrognosis.setEnabled(false);
        chkKemTdkTerduga.setEnabled(false);
        chkKemBila.setEnabled(false);
    }
    
    private void perawatBidanTRUE() {
        chkPendidikanKes.setEnabled(true);
        chkHasilAsuhan.setEnabled(true);
        chkPenanganan.setEnabled(true);
        chkPerawatan.setEnabled(true);
        chkAlatAlat.setEnabled(true);
        chkInformasi.setEnabled(true);
        chkKeamanan.setEnabled(true);
        chkProsedur.setEnabled(true);
        chkFarmaObat.setEnabled(true);
        chkFarmaInjek.setEnabled(true);
        chkFarmaSedasi.setEnabled(true);
        chkPerawatanLatihan.setEnabled(true);
        chkDistraksi.setEnabled(true);
        chkPengalihan.setEnabled(true);
        chkEtika.setEnabled(true);
        chkCaraBuang.setEnabled(true);
        chkTempat.setEnabled(true);
        chkCaraCuci.setEnabled(true);
        chkLainProPerawat.setEnabled(true);
    }
    
    private void perawatBidanFALSE() {
        chkPendidikanKes.setEnabled(false);
        chkHasilAsuhan.setEnabled(false);
        chkPenanganan.setEnabled(false);
        chkPerawatan.setEnabled(false);
        chkAlatAlat.setEnabled(false);
        chkInformasi.setEnabled(false);
        chkKeamanan.setEnabled(false);
        chkProsedur.setEnabled(false);
        chkFarmaObat.setEnabled(false);
        chkFarmaInjek.setEnabled(false);
        chkFarmaSedasi.setEnabled(false);
        chkPerawatanLatihan.setEnabled(false);
        chkDistraksi.setEnabled(false);
        chkPengalihan.setEnabled(false);
        chkEtika.setEnabled(false);
        chkCaraBuang.setEnabled(false);
        chkTempat.setEnabled(false);
        chkCaraCuci.setEnabled(false);
        chkLainProPerawat.setEnabled(false);
    }
    
    private void nutrisionisTRUE() {
        chkDiet.setEnabled(true);
        chkKonsulGiziRanap.setEnabled(true);
        chkKonsulGiziRalan.setEnabled(true);
        TpndNutrisionisLain.setEnabled(true);
    }
    
    private void nutrisionisFALSE() {
        chkDiet.setEnabled(false);
        chkKonsulGiziRanap.setEnabled(false);
        chkKonsulGiziRalan.setEnabled(false);
        TpndNutrisionisLain.setEnabled(false);
    }
    
    private void admisiTRUE() {
        chkHak.setEnabled(true);
        chkJam.setEnabled(true);
        chkInfoKejadian.setEnabled(true);
        TpndAdmisiLain.setEnabled(true);
    }

    private void admisiFALSE() {
        chkHak.setEnabled(false);
        chkJam.setEnabled(false);
        chkInfoKejadian.setEnabled(false);
        TpndAdmisiLain.setEnabled(false);
    }
}
