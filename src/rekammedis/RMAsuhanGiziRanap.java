/*
 * Kontribusi dari Bibing, RSUD Ratu Zalecha
 */

package rekammedis;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCatatanResep;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import kepegawaian.DlgCariPetugas;
import laporan.DlgHasilPenunjangMedis;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgNotepad;

/**
 *
 * @author perpustakaan
 */
public final class RMAsuhanGiziRanap extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, pscppt, psrestor;
    private ResultSet rs, rs1, rs2, rs3, rscppt, rsrestor;
    private int i = 0, x = 0, jml = 0;
    private boolean[] pilih;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String user = "", dataKonfirmasi = "", mual = "", nyeri = "", diare = "", kesulitan = "", odema = "",
            konstipasi = "", anoreksia = "", gangguan = "", makanlebih = "", makankurang = "", lebihintek = "",
            kurangintek = "", hasil = "", alergi = "", pantangan = "";
    private String[] kode, diagnosa;

    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsuhanGiziRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "Kode Restrain", "No. RM", "Nama Pasien", "Tgl. Pengkajian", "Jam", "Perawat/Bidan", "Ruang Perawatan",
            "tgl_asesmen", "jam_asesmen", "compos", "apatis", "delirium", "somnolen", "sopor", "koma", "gcs_e", "gcs_m", "gcs_v",
            "tensi", "nadi", "suhu", "napas", "skala_nyeri", "obs_gelisah", "obs_kooperatif", "obs_ketidakmampuan", "klinis_diri_sendiri",
            "klinis_orang_lain", "klinis_gagal", "res_non_farmakologi", "res_tempat_tidur", "res_pergelangan_tangan", "res_tangan_kiri",
            "res_tangan_kanan", "res_pergelangan_kaki", "res_kaki_kiri", "res_kaki_kanan", "res_lainlain", "res_farmakologi", 
            "kalimat_res_farmakologi", "kajian_1_jam", "kajian_2_jam", "kajian_lanjutan_2_jam", "kajian_lanjutan_4_jam", "obs_tanda", "obs_lanjutan",
            "menjelaskan_alasan", "menjelaskan_kriteria", "menjelaskan_informasi", "nip_petugas"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbRestrain.setModel(tabMode);
        tbRestrain.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRestrain.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 51; i++) {
            TableColumn column = tbRestrain.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(60);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(250);
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
        tbRestrain.setDefaultRenderer(Object.class, new WarnaTable()); 
        
        tabModeCppt=new DefaultTableModel(null, new Object[]{
            "Tgl. CPPT", "Jam CPPT", "Jenis Bagian", "DPJP Konsulen", "Jenis PPA",
            "Nama PPA", "Shift", "hasil", "instruksi", "no_rawat", "tgl_cppt", "jam_cppt"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbCPPT.setModel(tabModeCppt);
        tbCPPT.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCPPT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbCPPT.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(40);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            } 
        }
        tbCPPT.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Dilakukan Oleh", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Asesmen", "Tgl. Eksekusi", "Status Data"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayat.setModel(tabMode1);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
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
                column.setPreferredWidth(130);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } 
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null,new Object[]{
                "Cek", "Kode Diagnosa", "Deskripsi Diagnosa"
            }){
             @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbDiagnosa.setModel(tabMode2);
        tbDiagnosa.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbDiagnosa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(90);
            } else if (i == 2) {
                column.setPreferredWidth(480);
            }
        }
        tbDiagnosa.setDefaultRenderer(Object.class, new WarnaTable());
        
        Tbb.setDocument(new batasInput((int) 3).getKata(Tbb));
        Tlila.setDocument(new batasInput((int) 3).getKata(Tlila));
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
                    Tnip.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
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
        
        ChkAccor.setSelected(false);
        isMenu();
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
        MnDokumenJangMed = new javax.swing.JMenuItem();
        MnRiwayatData = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnHapusConteng = new javax.swing.JMenuItem();
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
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnNotepad = new widget.Button();
        BtnMonev = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel10 = new widget.Label();
        Tbb = new widget.TextBox();
        Tlila = new widget.TextBox();
        jLabel34 = new widget.Label();
        Tnip = new widget.TextBox();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        chkSaya = new widget.CekBox();
        jLabel12 = new widget.Label();
        tglAsuhan = new widget.Tanggal();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel64 = new widget.Label();
        jLabel65 = new widget.Label();
        label105 = new widget.Label();
        Ttb = new widget.TextBox();
        Tjk = new widget.TextBox();
        jLabel66 = new widget.Label();
        label107 = new widget.Label();
        BtnNilaiIMT = new widget.Button();
        Timt = new widget.TextBox();
        label108 = new widget.Label();
        label109 = new widget.Label();
        jLabel67 = new widget.Label();
        jLabel68 = new widget.Label();
        Ttl = new widget.TextBox();
        label106 = new widget.Label();
        Tulna = new widget.TextBox();
        label110 = new widget.Label();
        BtnNilaiTB = new widget.Button();
        TtbEst = new widget.TextBox();
        label111 = new widget.Label();
        TbbKoreksi = new widget.TextBox();
        label112 = new widget.Label();
        jLabel69 = new widget.Label();
        cmbSttsGizi = new widget.ComboBox();
        jLabel70 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        Tbiokimia = new widget.TextArea();
        jLabel71 = new widget.Label();
        ChkMual = new widget.CekBox();
        ChkNyeri = new widget.CekBox();
        ChkDiare = new widget.CekBox();
        ChkKesulitan = new widget.CekBox();
        ChkOedema = new widget.CekBox();
        ChkKonstipasi = new widget.CekBox();
        ChkAnorek = new widget.CekBox();
        ChkGangguan = new widget.CekBox();
        jLabel72 = new widget.Label();
        TklinisLain = new widget.TextBox();
        jLabel73 = new widget.Label();
        jLabel74 = new widget.Label();
        ChkMakanLebih3 = new widget.CekBox();
        ChkMakanKurang3 = new widget.CekBox();
        TriwayatLain = new widget.TextBox();
        jLabel75 = new widget.Label();
        ChkAlergi = new widget.CekBox();
        Talergi = new widget.TextBox();
        ChkPantangan = new widget.CekBox();
        Tpantangan = new widget.TextBox();
        jLabel76 = new widget.Label();
        ChkKelebihan = new widget.CekBox();
        ChkKekurangan = new widget.CekBox();
        ChkHasil = new widget.CekBox();
        ThasilRecall = new widget.TextBox();
        jLabel77 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        TriwPenyakit = new widget.TextArea();
        jLabel78 = new widget.Label();
        Scroll5 = new widget.ScrollPane();
        tbDiagnosa = new widget.Table();
        jLabel79 = new widget.Label();
        TCariDiagnosa = new widget.TextBox();
        BtnCariDiagnosa = new widget.Button();
        BtnAllDiagnosa = new widget.Button();
        jLabel13 = new widget.Label();
        Tumur = new widget.TextBox();
        Tsttsumur = new widget.Label();
        jLabel80 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        Tberkaitan = new widget.TextArea();
        scrollPane16 = new widget.ScrollPane();
        Tditandai = new widget.TextArea();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        cmbBentuk = new widget.ComboBox();
        jLabel84 = new widget.Label();
        TjnsDiet = new widget.TextBox();
        jLabel85 = new widget.Label();
        Tkalori = new widget.TextBox();
        jLabel86 = new widget.Label();
        Tprotein = new widget.TextBox();
        jLabel87 = new widget.Label();
        jLabel88 = new widget.Label();
        Tlemak = new widget.TextBox();
        Tkarbo = new widget.TextBox();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        Scroll4 = new widget.ScrollPane();
        tbCPPT = new widget.Table();
        panelGlass14 = new widget.panelisi();
        scrollPane5 = new widget.ScrollPane();
        Thasil = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbRestrain = new widget.Table();
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

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnDokumenJangMed.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokumenJangMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokumenJangMed.setText("Dokumen Penunjang Medis");
        MnDokumenJangMed.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokumenJangMed.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokumenJangMed.setIconTextGap(5);
        MnDokumenJangMed.setName("MnDokumenJangMed"); // NOI18N
        MnDokumenJangMed.setPreferredSize(new java.awt.Dimension(195, 26));
        MnDokumenJangMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokumenJangMedActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDokumenJangMed);

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

        MnHapusConteng.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusConteng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusConteng.setText("Hapus Ceklist");
        MnHapusConteng.setToolTipText("");
        MnHapusConteng.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusConteng.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusConteng.setIconTextGap(5);
        MnHapusConteng.setName("MnHapusConteng"); // NOI18N
        MnHapusConteng.setPreferredSize(new java.awt.Dimension(130, 26));
        MnHapusConteng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusContengActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHapusConteng);

        WindowRiwayat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayat.setName("WindowRiwayat"); // NOI18N
        WindowRiwayat.setUndecorated(true);
        WindowRiwayat.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Asuhan Gizi Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-06-2024" }));
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

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-06-2024" }));
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
        tbRiwayat.setName("tbRiwayat"); // NOI18N
        Scroll6.setViewportView(tbRiwayat);

        internalFrame13.add(Scroll6, java.awt.BorderLayout.CENTER);

        WindowRiwayat.getContentPane().add(internalFrame13, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Asuhan Gizi Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
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

        BtnMonev.setForeground(new java.awt.Color(0, 0, 0));
        BtnMonev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/addressbook-edit24.png"))); // NOI18N
        BtnMonev.setMnemonic('O');
        BtnMonev.setText("Monitoring & Evaluasi");
        BtnMonev.setToolTipText("Alt+O");
        BtnMonev.setGlassColor(new java.awt.Color(0, 204, 204));
        BtnMonev.setName("BtnMonev"); // NOI18N
        BtnMonev.setPreferredSize(new java.awt.Dimension(180, 30));
        BtnMonev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMonevActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnMonev);

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

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setToolTipText("");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1027));
        FormInput.setLayout(null);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(319, 10, 415, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(247, 10, 70, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 110, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        FormInput.add(Tbb);
        Tbb.setBounds(115, 124, 50, 23);

        Tlila.setForeground(new java.awt.Color(0, 0, 0));
        Tlila.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tlila.setName("Tlila"); // NOI18N
        Tlila.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlilaKeyPressed(evt);
            }
        });
        FormInput.add(Tlila);
        Tlila.setBounds(565, 124, 50, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Nama Ahli Gizi :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 989, 110, 23);

        Tnip.setEditable(false);
        Tnip.setForeground(new java.awt.Color(0, 0, 0));
        Tnip.setName("Tnip"); // NOI18N
        FormInput.add(Tnip);
        Tnip.setBounds(115, 989, 170, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(289, 989, 390, 23);

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
        BtnPetugas.setBounds(680, 989, 28, 23);

        chkSaya.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya.setText("Saya Sendiri");
        chkSaya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya.setName("chkSaya"); // NOI18N
        chkSaya.setOpaque(false);
        chkSaya.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaActionPerformed(evt);
            }
        });
        FormInput.add(chkSaya);
        chkSaya.setBounds(715, 989, 87, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Asuhan :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 38, 110, 23);

        tglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-06-2024" }));
        tglAsuhan.setDisplayFormat("dd-MM-yyyy");
        tglAsuhan.setName("tglAsuhan"); // NOI18N
        tglAsuhan.setOpaque(false);
        tglAsuhan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(tglAsuhan);
        tglAsuhan.setBounds(115, 38, 90, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 66, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(115, 66, 620, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("ASSESMEN :");
        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 94, 110, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Berat Badan :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 124, 110, 23);

        label105.setForeground(new java.awt.Color(0, 0, 0));
        label105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label105.setText("Kg.    TB : ");
        label105.setName("label105"); // NOI18N
        label105.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label105);
        label105.setBounds(170, 124, 50, 23);

        Ttb.setForeground(new java.awt.Color(0, 0, 0));
        Ttb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ttb.setName("Ttb"); // NOI18N
        Ttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbKeyPressed(evt);
            }
        });
        FormInput.add(Ttb);
        Ttb.setBounds(220, 124, 50, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        FormInput.add(Tjk);
        Tjk.setBounds(645, 38, 90, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Jenis Kelamin : ");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(555, 38, 90, 23);

        label107.setForeground(new java.awt.Color(0, 0, 0));
        label107.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label107.setText("Cm.");
        label107.setName("label107"); // NOI18N
        label107.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label107);
        label107.setBounds(275, 124, 30, 23);

        BtnNilaiIMT.setForeground(new java.awt.Color(0, 0, 0));
        BtnNilaiIMT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnNilaiIMT.setMnemonic('I');
        BtnNilaiIMT.setText("Nilai IMT :");
        BtnNilaiIMT.setToolTipText("Alt+I");
        BtnNilaiIMT.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnNilaiIMT.setName("BtnNilaiIMT"); // NOI18N
        BtnNilaiIMT.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNilaiIMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNilaiIMTActionPerformed(evt);
            }
        });
        BtnNilaiIMT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnNilaiIMTKeyPressed(evt);
            }
        });
        FormInput.add(BtnNilaiIMT);
        BtnNilaiIMT.setBounds(310, 124, 93, 23);

        Timt.setEditable(false);
        Timt.setForeground(new java.awt.Color(0, 0, 0));
        Timt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Timt.setName("Timt"); // NOI18N
        FormInput.add(Timt);
        Timt.setBounds(410, 124, 68, 23);

        label108.setForeground(new java.awt.Color(0, 0, 0));
        label108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label108.setText("Kg/Cm3    LILA : ");
        label108.setName("label108"); // NOI18N
        label108.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label108);
        label108.setBounds(483, 124, 80, 23);

        label109.setForeground(new java.awt.Color(0, 0, 0));
        label109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label109.setText("Cm.");
        label109.setName("label109"); // NOI18N
        label109.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label109);
        label109.setBounds(621, 124, 30, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Antropometri :");
        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 109, 110, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Tinggi Lutut :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 152, 110, 23);

        Ttl.setForeground(new java.awt.Color(0, 0, 0));
        Ttl.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ttl.setName("Ttl"); // NOI18N
        Ttl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtlKeyPressed(evt);
            }
        });
        FormInput.add(Ttl);
        Ttl.setBounds(115, 152, 50, 23);

        label106.setForeground(new java.awt.Color(0, 0, 0));
        label106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label106.setText("Cm.    ULNA : ");
        label106.setName("label106"); // NOI18N
        label106.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label106);
        label106.setBounds(170, 152, 67, 23);

        Tulna.setForeground(new java.awt.Color(0, 0, 0));
        Tulna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tulna.setName("Tulna"); // NOI18N
        Tulna.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TulnaKeyPressed(evt);
            }
        });
        FormInput.add(Tulna);
        Tulna.setBounds(238, 152, 50, 23);

        label110.setForeground(new java.awt.Color(0, 0, 0));
        label110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label110.setText("Cm.");
        label110.setName("label110"); // NOI18N
        label110.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label110);
        label110.setBounds(293, 152, 30, 23);

        BtnNilaiTB.setForeground(new java.awt.Color(0, 0, 0));
        BtnNilaiTB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnNilaiTB.setMnemonic('I');
        BtnNilaiTB.setText("Nilai TB est :");
        BtnNilaiTB.setToolTipText("Alt+I");
        BtnNilaiTB.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnNilaiTB.setName("BtnNilaiTB"); // NOI18N
        BtnNilaiTB.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNilaiTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNilaiTBActionPerformed(evt);
            }
        });
        BtnNilaiTB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnNilaiTBKeyPressed(evt);
            }
        });
        FormInput.add(BtnNilaiTB);
        BtnNilaiTB.setBounds(325, 152, 105, 23);

        TtbEst.setForeground(new java.awt.Color(0, 0, 0));
        TtbEst.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TtbEst.setName("TtbEst"); // NOI18N
        TtbEst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbEstKeyPressed(evt);
            }
        });
        FormInput.add(TtbEst);
        TtbEst.setBounds(435, 152, 70, 23);

        label111.setForeground(new java.awt.Color(0, 0, 0));
        label111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label111.setText("Cm.    BB Koreksi : ");
        label111.setName("label111"); // NOI18N
        label111.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label111);
        label111.setBounds(510, 152, 90, 23);

        TbbKoreksi.setForeground(new java.awt.Color(0, 0, 0));
        TbbKoreksi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TbbKoreksi.setName("TbbKoreksi"); // NOI18N
        TbbKoreksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKoreksiKeyPressed(evt);
            }
        });
        FormInput.add(TbbKoreksi);
        TbbKoreksi.setBounds(602, 152, 50, 23);

        label112.setForeground(new java.awt.Color(0, 0, 0));
        label112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label112.setText("Kg.");
        label112.setName("label112"); // NOI18N
        label112.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label112);
        label112.setBounds(657, 152, 30, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Status Gizi :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 180, 110, 23);

        cmbSttsGizi.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsGizi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Buruk", "Kurang", "Normal", "Lebih", "Obesitas" }));
        cmbSttsGizi.setName("cmbSttsGizi"); // NOI18N
        cmbSttsGizi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbSttsGizi);
        cmbSttsGizi.setBounds(115, 180, 75, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Biokimia :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(190, 180, 60, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N

        Tbiokimia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tbiokimia.setColumns(20);
        Tbiokimia.setRows(5);
        Tbiokimia.setName("Tbiokimia"); // NOI18N
        Tbiokimia.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tbiokimia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbiokimiaKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Tbiokimia);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(255, 180, 480, 60);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Klinis / Fisik :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 245, 110, 23);

        ChkMual.setBackground(new java.awt.Color(255, 255, 250));
        ChkMual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMual.setForeground(new java.awt.Color(0, 0, 0));
        ChkMual.setText("Mual Muntah");
        ChkMual.setBorderPainted(true);
        ChkMual.setBorderPaintedFlat(true);
        ChkMual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMual.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMual.setName("ChkMual"); // NOI18N
        ChkMual.setOpaque(false);
        ChkMual.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkMual);
        ChkMual.setBounds(115, 245, 90, 23);

        ChkNyeri.setBackground(new java.awt.Color(255, 255, 250));
        ChkNyeri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNyeri.setForeground(new java.awt.Color(0, 0, 0));
        ChkNyeri.setText("Nyeri Ulu Hati");
        ChkNyeri.setBorderPainted(true);
        ChkNyeri.setBorderPaintedFlat(true);
        ChkNyeri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNyeri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNyeri.setName("ChkNyeri"); // NOI18N
        ChkNyeri.setOpaque(false);
        ChkNyeri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkNyeri);
        ChkNyeri.setBounds(115, 273, 90, 23);

        ChkDiare.setBackground(new java.awt.Color(255, 255, 250));
        ChkDiare.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDiare.setForeground(new java.awt.Color(0, 0, 0));
        ChkDiare.setText("Diare");
        ChkDiare.setBorderPainted(true);
        ChkDiare.setBorderPaintedFlat(true);
        ChkDiare.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDiare.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDiare.setName("ChkDiare"); // NOI18N
        ChkDiare.setOpaque(false);
        ChkDiare.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDiare);
        ChkDiare.setBounds(115, 301, 90, 23);

        ChkKesulitan.setBackground(new java.awt.Color(255, 255, 250));
        ChkKesulitan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKesulitan.setForeground(new java.awt.Color(0, 0, 0));
        ChkKesulitan.setText("Kesulitan Menelan");
        ChkKesulitan.setBorderPainted(true);
        ChkKesulitan.setBorderPaintedFlat(true);
        ChkKesulitan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKesulitan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKesulitan.setName("ChkKesulitan"); // NOI18N
        ChkKesulitan.setOpaque(false);
        ChkKesulitan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKesulitan);
        ChkKesulitan.setBounds(115, 329, 114, 23);

        ChkOedema.setBackground(new java.awt.Color(255, 255, 250));
        ChkOedema.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkOedema.setForeground(new java.awt.Color(0, 0, 0));
        ChkOedema.setText("Oedema");
        ChkOedema.setBorderPainted(true);
        ChkOedema.setBorderPaintedFlat(true);
        ChkOedema.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkOedema.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkOedema.setName("ChkOedema"); // NOI18N
        ChkOedema.setOpaque(false);
        ChkOedema.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkOedema);
        ChkOedema.setBounds(250, 245, 72, 23);

        ChkKonstipasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkKonstipasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKonstipasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkKonstipasi.setText("Konstipasi");
        ChkKonstipasi.setBorderPainted(true);
        ChkKonstipasi.setBorderPaintedFlat(true);
        ChkKonstipasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKonstipasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKonstipasi.setName("ChkKonstipasi"); // NOI18N
        ChkKonstipasi.setOpaque(false);
        ChkKonstipasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKonstipasi);
        ChkKonstipasi.setBounds(250, 273, 78, 23);

        ChkAnorek.setBackground(new java.awt.Color(255, 255, 250));
        ChkAnorek.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAnorek.setForeground(new java.awt.Color(0, 0, 0));
        ChkAnorek.setText("Anoreksia");
        ChkAnorek.setBorderPainted(true);
        ChkAnorek.setBorderPaintedFlat(true);
        ChkAnorek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAnorek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAnorek.setName("ChkAnorek"); // NOI18N
        ChkAnorek.setOpaque(false);
        ChkAnorek.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAnorek);
        ChkAnorek.setBounds(250, 301, 78, 23);

        ChkGangguan.setBackground(new java.awt.Color(255, 255, 250));
        ChkGangguan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGangguan.setForeground(new java.awt.Color(0, 0, 0));
        ChkGangguan.setText("Gangguan Gigi Geligi");
        ChkGangguan.setBorderPainted(true);
        ChkGangguan.setBorderPaintedFlat(true);
        ChkGangguan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGangguan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGangguan.setName("ChkGangguan"); // NOI18N
        ChkGangguan.setOpaque(false);
        ChkGangguan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkGangguan);
        ChkGangguan.setBounds(250, 329, 125, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Klinis / Fisik Lainnya : ");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(330, 245, 110, 23);

        TklinisLain.setForeground(new java.awt.Color(0, 0, 0));
        TklinisLain.setHighlighter(null);
        TklinisLain.setName("TklinisLain"); // NOI18N
        FormInput.add(TklinisLain);
        TklinisLain.setBounds(440, 245, 294, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Riwayat Gizi :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 352, 110, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("Riwayat Makan SMRS");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(115, 352, 110, 23);

        ChkMakanLebih3.setBackground(new java.awt.Color(255, 255, 250));
        ChkMakanLebih3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMakanLebih3.setForeground(new java.awt.Color(0, 0, 0));
        ChkMakanLebih3.setText("Makan > 3 x Sehari");
        ChkMakanLebih3.setBorderPainted(true);
        ChkMakanLebih3.setBorderPaintedFlat(true);
        ChkMakanLebih3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMakanLebih3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMakanLebih3.setName("ChkMakanLebih3"); // NOI18N
        ChkMakanLebih3.setOpaque(false);
        ChkMakanLebih3.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkMakanLebih3);
        ChkMakanLebih3.setBounds(115, 372, 120, 23);

        ChkMakanKurang3.setBackground(new java.awt.Color(255, 255, 250));
        ChkMakanKurang3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMakanKurang3.setForeground(new java.awt.Color(0, 0, 0));
        ChkMakanKurang3.setText("Makan < 3 x Sehari");
        ChkMakanKurang3.setBorderPainted(true);
        ChkMakanKurang3.setBorderPaintedFlat(true);
        ChkMakanKurang3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMakanKurang3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMakanKurang3.setName("ChkMakanKurang3"); // NOI18N
        ChkMakanKurang3.setOpaque(false);
        ChkMakanKurang3.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkMakanKurang3);
        ChkMakanKurang3.setBounds(115, 400, 120, 23);

        TriwayatLain.setForeground(new java.awt.Color(0, 0, 0));
        TriwayatLain.setHighlighter(null);
        TriwayatLain.setName("TriwayatLain"); // NOI18N
        FormInput.add(TriwayatLain);
        TriwayatLain.setBounds(115, 428, 620, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Riwayat Gizi Lain :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 428, 110, 23);

        ChkAlergi.setBackground(new java.awt.Color(255, 255, 250));
        ChkAlergi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAlergi.setForeground(new java.awt.Color(0, 0, 0));
        ChkAlergi.setText("Alergi Makanan");
        ChkAlergi.setBorderPainted(true);
        ChkAlergi.setBorderPaintedFlat(true);
        ChkAlergi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAlergi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAlergi.setName("ChkAlergi"); // NOI18N
        ChkAlergi.setOpaque(false);
        ChkAlergi.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkAlergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAlergiActionPerformed(evt);
            }
        });
        FormInput.add(ChkAlergi);
        ChkAlergi.setBounds(115, 456, 105, 23);

        Talergi.setForeground(new java.awt.Color(0, 0, 0));
        Talergi.setHighlighter(null);
        Talergi.setName("Talergi"); // NOI18N
        FormInput.add(Talergi);
        Talergi.setBounds(220, 456, 515, 23);

        ChkPantangan.setBackground(new java.awt.Color(255, 255, 250));
        ChkPantangan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPantangan.setForeground(new java.awt.Color(0, 0, 0));
        ChkPantangan.setText("Pantangani Makan");
        ChkPantangan.setBorderPainted(true);
        ChkPantangan.setBorderPaintedFlat(true);
        ChkPantangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPantangan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPantangan.setName("ChkPantangan"); // NOI18N
        ChkPantangan.setOpaque(false);
        ChkPantangan.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkPantangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPantanganActionPerformed(evt);
            }
        });
        FormInput.add(ChkPantangan);
        ChkPantangan.setBounds(115, 484, 115, 23);

        Tpantangan.setForeground(new java.awt.Color(0, 0, 0));
        Tpantangan.setHighlighter(null);
        Tpantangan.setName("Tpantangan"); // NOI18N
        FormInput.add(Tpantangan);
        Tpantangan.setBounds(230, 484, 505, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("Asupan Makan SMRS");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(255, 352, 110, 23);

        ChkKelebihan.setBackground(new java.awt.Color(255, 255, 250));
        ChkKelebihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKelebihan.setForeground(new java.awt.Color(0, 0, 0));
        ChkKelebihan.setText("Kelebihan Intake");
        ChkKelebihan.setBorderPainted(true);
        ChkKelebihan.setBorderPaintedFlat(true);
        ChkKelebihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKelebihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKelebihan.setName("ChkKelebihan"); // NOI18N
        ChkKelebihan.setOpaque(false);
        ChkKelebihan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKelebihan);
        ChkKelebihan.setBounds(255, 372, 120, 23);

        ChkKekurangan.setBackground(new java.awt.Color(255, 255, 250));
        ChkKekurangan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKekurangan.setForeground(new java.awt.Color(0, 0, 0));
        ChkKekurangan.setText("Kekurangan Intake");
        ChkKekurangan.setBorderPainted(true);
        ChkKekurangan.setBorderPaintedFlat(true);
        ChkKekurangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKekurangan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKekurangan.setName("ChkKekurangan"); // NOI18N
        ChkKekurangan.setOpaque(false);
        ChkKekurangan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKekurangan);
        ChkKekurangan.setBounds(255, 400, 120, 23);

        ChkHasil.setBackground(new java.awt.Color(255, 255, 250));
        ChkHasil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkHasil.setForeground(new java.awt.Color(0, 0, 0));
        ChkHasil.setText("Hasil Recall Intake");
        ChkHasil.setBorderPainted(true);
        ChkHasil.setBorderPaintedFlat(true);
        ChkHasil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkHasil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHasil.setName("ChkHasil"); // NOI18N
        ChkHasil.setOpaque(false);
        ChkHasil.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkHasilActionPerformed(evt);
            }
        });
        FormInput.add(ChkHasil);
        ChkHasil.setBounds(380, 372, 115, 23);

        ThasilRecall.setForeground(new java.awt.Color(0, 0, 0));
        ThasilRecall.setHighlighter(null);
        ThasilRecall.setName("ThasilRecall"); // NOI18N
        FormInput.add(ThasilRecall);
        ThasilRecall.setBounds(498, 372, 235, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Riwayat Penyakit Personal :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 512, 150, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        TriwPenyakit.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TriwPenyakit.setColumns(20);
        TriwPenyakit.setRows(5);
        TriwPenyakit.setName("TriwPenyakit"); // NOI18N
        TriwPenyakit.setPreferredSize(new java.awt.Dimension(162, 1000));
        TriwPenyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwPenyakitKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(TriwPenyakit);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(155, 512, 580, 60);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("DIAGNOSA GIZI :");
        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(0, 576, 110, 23);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ P ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbDiagnosa.setToolTipText("");
        tbDiagnosa.setComponentPopupMenu(jPopupMenu2);
        tbDiagnosa.setName("tbDiagnosa"); // NOI18N
        Scroll5.setViewportView(tbDiagnosa);

        FormInput.add(Scroll5);
        Scroll5.setBounds(115, 576, 620, 150);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Key Word :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 730, 110, 23);

        TCariDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        TCariDiagnosa.setName("TCariDiagnosa"); // NOI18N
        TCariDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariDiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(TCariDiagnosa);
        TCariDiagnosa.setBounds(115, 730, 289, 23);

        BtnCariDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariDiagnosa.setMnemonic('1');
        BtnCariDiagnosa.setToolTipText("Alt+1");
        BtnCariDiagnosa.setName("BtnCariDiagnosa"); // NOI18N
        BtnCariDiagnosa.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariDiagnosaActionPerformed(evt);
            }
        });
        BtnCariDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariDiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(BtnCariDiagnosa);
        BtnCariDiagnosa.setBounds(410, 730, 28, 23);

        BtnAllDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        BtnAllDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllDiagnosa.setMnemonic('2');
        BtnAllDiagnosa.setText("Semua Diagnosa");
        BtnAllDiagnosa.setToolTipText("Alt+2");
        BtnAllDiagnosa.setName("BtnAllDiagnosa"); // NOI18N
        BtnAllDiagnosa.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllDiagnosaActionPerformed(evt);
            }
        });
        BtnAllDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllDiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(BtnAllDiagnosa);
        BtnAllDiagnosa.setBounds(445, 730, 140, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Umur : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(415, 38, 50, 23);

        Tumur.setEditable(false);
        Tumur.setForeground(new java.awt.Color(0, 0, 0));
        Tumur.setName("Tumur"); // NOI18N
        FormInput.add(Tumur);
        Tumur.setBounds(465, 38, 50, 23);

        Tsttsumur.setForeground(new java.awt.Color(0, 0, 0));
        Tsttsumur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Tsttsumur.setText("tahun.");
        Tsttsumur.setName("Tsttsumur"); // NOI18N
        FormInput.add(Tsttsumur);
        Tsttsumur.setBounds(520, 38, 50, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Berkaitan Dengan [ E ] :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(0, 758, 150, 23);

        scrollPane15.setName("scrollPane15"); // NOI18N

        Tberkaitan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tberkaitan.setColumns(20);
        Tberkaitan.setRows(5);
        Tberkaitan.setName("Tberkaitan"); // NOI18N
        Tberkaitan.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tberkaitan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TberkaitanKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(Tberkaitan);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(155, 758, 580, 60);

        scrollPane16.setName("scrollPane16"); // NOI18N

        Tditandai.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tditandai.setColumns(20);
        Tditandai.setRows(5);
        Tditandai.setName("Tditandai"); // NOI18N
        Tditandai.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tditandai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TditandaiKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(Tditandai);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(155, 824, 580, 60);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Ditandai Dengan [ S ] :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 824, 150, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("INTERVENSI :");
        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 890, 110, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Bentuk Makanan :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(0, 905, 110, 23);

        cmbBentuk.setForeground(new java.awt.Color(0, 0, 0));
        cmbBentuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Biasa", "Lunak", "Saring", "Cair" }));
        cmbBentuk.setName("cmbBentuk"); // NOI18N
        cmbBentuk.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBentuk);
        cmbBentuk.setBounds(115, 905, 70, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Jenis Diet :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(190, 905, 70, 23);

        TjnsDiet.setForeground(new java.awt.Color(0, 0, 0));
        TjnsDiet.setHighlighter(null);
        TjnsDiet.setName("TjnsDiet"); // NOI18N
        FormInput.add(TjnsDiet);
        TjnsDiet.setBounds(265, 905, 470, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Kalori (KKal) :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(0, 933, 110, 23);

        Tkalori.setForeground(new java.awt.Color(0, 0, 0));
        Tkalori.setName("Tkalori"); // NOI18N
        Tkalori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkaloriKeyPressed(evt);
            }
        });
        FormInput.add(Tkalori);
        Tkalori.setBounds(115, 933, 90, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Protein :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 961, 110, 23);

        Tprotein.setForeground(new java.awt.Color(0, 0, 0));
        Tprotein.setName("Tprotein"); // NOI18N
        Tprotein.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TproteinKeyPressed(evt);
            }
        });
        FormInput.add(Tprotein);
        Tprotein.setBounds(115, 961, 90, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Lemak :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(320, 933, 90, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Karbohidrat :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(320, 961, 90, 23);

        Tlemak.setForeground(new java.awt.Color(0, 0, 0));
        Tlemak.setName("Tlemak"); // NOI18N
        Tlemak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlemakKeyPressed(evt);
            }
        });
        FormInput.add(Tlemak);
        Tlemak.setBounds(415, 933, 90, 23);

        Tkarbo.setForeground(new java.awt.Color(0, 0, 0));
        Tkarbo.setName("Tkarbo"); // NOI18N
        Tkarbo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkarboKeyPressed(evt);
            }
        });
        FormInput.add(Tkarbo);
        Tkarbo.setBounds(415, 961, 90, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(500, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setToolTipText("Silahkan Klik Untuk Membaca CPPT");
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(22, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormMenu.setBackground(new java.awt.Color(250, 250, 245));
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(150, 483));
        FormMenu.setLayout(new java.awt.GridLayout(1, 2));

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " CPPT ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbCPPT.setToolTipText("Silahkan klik untuk memilih data yang dibaca cpptnya");
        tbCPPT.setName("tbCPPT"); // NOI18N
        tbCPPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCPPTMouseClicked(evt);
            }
        });
        tbCPPT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCPPTKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbCPPT);

        FormMenu.add(Scroll4);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass14.setLayout(new java.awt.BorderLayout());

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Hasil Pemeriksaan, Analisa, Rencana, Penatalaksanaan Pasien ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N
        scrollPane5.setPreferredSize(new java.awt.Dimension(212, 450));

        Thasil.setEditable(false);
        Thasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Thasil.setColumns(20);
        Thasil.setRows(5);
        Thasil.setToolTipText("Silahkan klik kanan utk. copy data CPPT hasil pemeriksaan");
        Thasil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Thasil.setName("Thasil"); // NOI18N
        Thasil.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane5.setViewportView(Thasil);

        panelGlass14.add(scrollPane5, java.awt.BorderLayout.PAGE_START);

        scrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Instruksi Tenaga Kesehatan Termasuk Pasca Bedah/Prosedur ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setPreferredSize(new java.awt.Dimension(212, 150));

        Tinstruksi.setEditable(false);
        Tinstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinstruksi.setColumns(20);
        Tinstruksi.setRows(5);
        Tinstruksi.setToolTipText("Silahkan klik kanan utk. copy data CPPT instruksi nakes");
        Tinstruksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane4.setViewportView(Tinstruksi);

        panelGlass14.add(scrollPane4, java.awt.BorderLayout.CENTER);

        FormMenu.add(panelGlass14);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);

        internalFrame2.add(PanelAccor, java.awt.BorderLayout.EAST);

        TabRawat.addTab("Input Asuhan", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbRestrain.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRestrain.setComponentPopupMenu(jPopupMenu1);
        tbRestrain.setName("tbRestrain"); // NOI18N
        tbRestrain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRestrainMouseClicked(evt);
            }
        });
        tbRestrain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRestrainKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbRestrain);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Asuhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-06-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-06-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('T');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+T");
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
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Asuhan", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            cekData();
//            if (Sequel.menyimpantf("asesmen_restrain", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 46, new String[]{
//                        TNoRw.getText(), TkodeRestrain.getText(), Valid.SetTgl(tglAsuhan.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TrgRawat.getText(),
//                        compos, apatis, delirium, somnolen, sopor, koma, Tbb.getText(), Tlila.getText(), Tgcsv.getText(), Ttensi.getText(), Tnadi.getText(), Tsuhu.getText(), Tnapas.getText(), Tskala.getText(), gelisah, kooperatif,
//                        ketidakmampuan, klinik_diri, klinis_orang, klinis_gagal, resnonfarma, restempat, resgelangtangan, restangankiri, restangankanan, resgelangkaki, reskakikiri, reskakikanan, reslain, resfarmakologi,
//                        TResFarmakologi.getText(), kajian1jam, kajian2jam, kajianlanjutan2jam, kajianlanjutan4jam, kajiantanda, kajianlanjutan, jelasalasan, jelaskriteria, jelasinformari, Tnip.getText()
//                    }) == true) {
//
//                TCari.setText(TNoRw.getText());
//                TabRawat.setSelectedIndex(1);
//                emptTeks();
//                tampil();
//            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        TCariDiagnosa.setText("");
        tampilDiagnosa();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbRestrain.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapus();
            } else {
                if (Tnip.getText().equals(tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 50).toString())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh perawat/bidan yang bersangkutan..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }   
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            if (tbRestrain.getSelectedRow() > -1) {
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
                    if (Tnip.getText().equals(tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 50).toString())) {
                        gantiDisimpan();
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh perawat/bidan yang bersangkutan..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
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
        WindowRiwayat.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbRestrain.getSelectedRow() > -1) {
//            Map<String, Object> param = new HashMap<>();
//            param.put("namars", akses.getnamars());            
//            param.put("logo", Sequel.cariGambar("select logo from setting"));
//            param.put("norm", TNoRM.getText());
//            param.put("nmpasien", TPasien.getText());
//            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
//
//            if (ChkCompos.isSelected() == true) {
//                param.put("compos", "V");
//            } else {
//                param.put("compos", "");
//            }
//            
//            if (ChkApatis.isSelected() == true) {
//                param.put("apatis", "V");
//            } else {
//                param.put("apatis", "");
//            }
//            
//            if (ChkDelirium.isSelected() == true) {
//                param.put("delirium", "V");
//            } else {
//                param.put("delirium", "");
//            }
//            
//            if (ChkSomnolen.isSelected() == true) {
//                param.put("somnolen", "V");
//            } else {
//                param.put("somnolen", "");
//            }
//            
//            if (ChkSopor.isSelected() == true) {
//                param.put("sopor", "V");
//            } else {
//                param.put("sopor", "");
//            }
//            
//            if (ChkKoma.isSelected() == true) {
//                param.put("koma", "V");
//            } else {
//                param.put("koma", "");
//            }
//            
//            param.put("gcs", "E : " + Tbb.getText() + ", M : " + Tlila.getText() + ", V : " + Tgcsv.getText());
//            param.put("tensi", Ttensi.getText() + " mmHg");
//            param.put("nadi", Tnadi.getText() + " x/menit");
//            param.put("suhu", Tsuhu.getText() + " °C");
//            param.put("napas", Tnapas.getText() + " x/menit");
//            param.put("skala", Tskala.getText());
//
//            if (ChkGelisah.isSelected() == true) {
//                param.put("gelisah", "V");
//            } else {
//                param.put("gelisah", "");
//            }
//            
//            if (ChkKooperatif.isSelected() == true) {
//                param.put("kooperatif", "V");
//            } else {
//                param.put("kooperatif", "");
//            }
//            
//            if (ChkKetidakmampuan.isSelected() == true) {
//                param.put("ketidakmampuan", "V");
//            } else {
//                param.put("ketidakmampuan", "");
//            }
//
//            if (ChkKlinisDiri.isSelected() == true) {
//                param.put("klinis_diri", "V");
//            } else {
//                param.put("klinis_diri", "");
//            }
//            
//            if (ChkKlinisOrang.isSelected() == true) {
//                param.put("klinis_orang", "V");
//            } else {
//                param.put("klinis_orang", "");
//            }
//            
//            if (ChkKlinisGagal.isSelected() == true) {
//                param.put("klinis_gagal", "V");
//            } else {
//                param.put("klinis_gagal", "");
//            }
//            
//            if (ChkResTempatTidur.isSelected() == true) {
//                param.put("res_tempat", "V");
//            } else {
//                param.put("res_tempat", "");
//            }
//            
//            if (ChkResGelangTangan.isSelected() == true) {
//                param.put("res_gelang_tangan", "V");
//            } else {
//                param.put("res_gelang_tangan", "");
//            }
//            
//            if (ChkResTanganKiri.isSelected() == true) {
//                param.put("res_tangan_kiri", "V");
//            } else {
//                param.put("res_tangan_kiri", "");
//            }
//            
//            if (ChkResTanganKanan.isSelected() == true) {
//                param.put("res_tangan_kanan", "V");
//            } else {
//                param.put("res_tangan_kanan", "");
//            }
//            
//            if (ChkResGelangKaki.isSelected() == true) {
//                param.put("res_gelang_kaki", "V");
//            } else {
//                param.put("res_gelang_kaki", "");
//            }
//            
//            if (ChkResKakiKiri.isSelected() == true) {
//                param.put("res_kaki_kiri", "V");
//            } else {
//                param.put("res_kaki_kiri", "");
//            }
//            
//            if (ChkResKakiKanan.isSelected() == true) {
//                param.put("res_kaki_kanan", "V");
//            } else {
//                param.put("res_kaki_kanan", "");
//            }
//            
//            if (ChkResLain.isSelected() == true) {
//                param.put("res_lain", "V");
//            } else {
//                param.put("res_lain", "");
//            }
//            
//            param.put("res_farmakologi", TResFarmakologi.getText());
//            
//            if (ChkKajian1Jam.isSelected() == true) {
//                param.put("kajian1jam", "V");
//            } else {
//                param.put("kajian1jam", "");
//            }
//            
//            if (ChkKajian2Jam.isSelected() == true) {
//                param.put("kajian2jam", "V");
//            } else {
//                param.put("kajian2jam", "");
//            }
//            
//            if (ChkKajianLanjut2Jam.isSelected() == true) {
//                param.put("kajian_lanjut2jam", "V");
//            } else {
//                param.put("kajian_lanjut2jam", "");
//            }
//            
//            if (ChkKajianLanjut4Jam.isSelected() == true) {
//                param.put("kajian_lanjut4jam", "V");
//            } else {
//                param.put("kajian_lanjut4jam", "");
//            }
//            
//            if (ChkObsTanda.isSelected() == true) {
//                param.put("kajian_tanda", "V");
//            } else {
//                param.put("kajian_tanda", "");
//            }
//            
//            if (ChkObsLanjutan.isSelected() == true) {
//                param.put("kajian_lanjutan", "V");
//            } else {
//                param.put("kajian_lanjutan", "");
//            }
//            
//            if (ChkAlasan.isSelected() == true) {
//                param.put("jelas_alasan", "V");
//            } else {
//                param.put("jelas_alasan", "");
//            }
//            
//            if (ChkKriteria.isSelected() == true) {
//                param.put("jelas_kriteria", "V");
//            } else {
//                param.put("jelas_kriteria", "");
//            }
//            
//            if (ChkInformasi.isSelected() == true) {
//                param.put("jelas_informasi", "V");
//            } else {
//                param.put("jelas_informasi", "");
//            }
//
//            param.put("tanggal", "Pengkajian Tanggal " + Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_asesmen from asesmen_restrain where no_rawat='" + TNoRw.getText() + "'"))
//                    + ", Pukul : " + Sequel.cariIsi("select time_format(jam_asesmen,'%H:%i') from asesmen_restrain where no_rawat='" + TNoRw.getText() + "'") + " WITA");
//            param.put("petugas", "(" + TnmPetugas.getText() + ")");
//
//            Valid.MyReport("rptCetakAsesmenRestrain.jasper", "report", "::[ Laporan Asesmen Restrain hal. 1 ]::",
//                    "SELECT now() tanggal", param);
            
            emptTeks();            
            TabRawat.setSelectedIndex(1);
            tampil();            
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
            tbRestrain.requestFocus();
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

    private void tbRestrainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRestrainMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {                
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbRestrain.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbRestrainMouseClicked

    private void tbRestrainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRestrainKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbRestrainKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            ChkAccor.setSelected(false);
            isMenu();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampilDiagnosa();
        tampil();
        
        if (Sequel.cariInteger("select count(-1) from asuhan_gizi_ranap where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from asuhan_gizi_ranap where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void ChkMerokokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkMerokokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkMerokokActionPerformed

    private void ChkAsma1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAsma1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAsma1ActionPerformed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        Valid.pindah(evt, Tbb, Ttb);
    }//GEN-LAST:event_TbbKeyPressed

    private void TlilaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlilaKeyPressed
        Valid.pindah(evt, Ttb, Ttl);
    }//GEN-LAST:event_TlilaKeyPressed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        isMenu();
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void tbCPPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCPPTMouseClicked
        if (tabModeCppt.getRowCount() != 0) {
            try {
                getDataCppt();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbCPPTMouseClicked

    private void tbCPPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyPressed
        if (tabModeCppt.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataCppt();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbCPPTKeyPressed

    private void MnRiwayatDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatDataActionPerformed
        ChkAccor.setSelected(false);
        isMenu();

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
                    if (Sequel.cariInteger("select count(-1) from asesmen_restrain where "
                            + "no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString() + "'") > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Proses kembalikan/restore data gagal, krn. sudah ada datanya dg. no. rawat yg. sama..!!");
                    } else {
                        kembalikanData();
                        TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                        BtnCloseIn10ActionPerformed(null);
                        tampil();
                        emptTeks();
                        TabRawat.setSelectedIndex(1);
                    }
                } else {
                    kembalikanDataDiganti();
                    TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                    BtnCloseIn10ActionPerformed(null);
                    tampil();
                    emptTeks();
                    TabRawat.setSelectedIndex(1);
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

    private void BtnCloseIn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn10ActionPerformed
        WindowRiwayat.dispose();
        TCari2.setText("");
    }//GEN-LAST:event_BtnCloseIn10ActionPerformed

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        ChkAccor.setSelected(false);
        isMenu();
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMAsuhanGiziRanap");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMAsuhanGiziRanap");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        ChkAccor.setSelected(false);
        isMenu();

        akses.setform("RMAsuhanGiziRanap");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                Tnip.setText("-");
                TnmPetugas.setText("-");
            } else {
                Tnip.setText(akses.getkode());
                TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + Tnip.getText() + "'"));
            }
        } else {
            Tnip.setText("-");
            TnmPetugas.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void BtnMonevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMonevActionPerformed
        ChkAccor.setSelected(false);
        isMenu();
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMAsuhanGiziRanap");
        DlgMonevAsuhanGizi form = new DlgMonevAsuhanGizi(null, false);
        form.emptTeks();
        form.isCek();
        form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TrgRawat.getText());
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnMonevActionPerformed

    private void TtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Tbb.getText().contains(",") == true) {
                Tbb.setText(Tbb.getText().replaceAll(",", "."));
            }

            if (Ttb.getText().contains(",") == true) {
                Ttb.setText(Ttb.getText().replaceAll(",", "."));
            }
            BtnNilaiIMTActionPerformed(null);
        }
    }//GEN-LAST:event_TtbKeyPressed

    private void BtnNilaiIMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNilaiIMTActionPerformed
        hitungIMT();
    }//GEN-LAST:event_BtnNilaiIMTActionPerformed

    private void BtnNilaiIMTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnNilaiIMTKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnNilaiIMTActionPerformed(null);
        } 
    }//GEN-LAST:event_BtnNilaiIMTKeyPressed

    private void TtlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtlKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Ttl.getText().contains(",") == true) {
                Ttl.setText(Ttl.getText().replaceAll(",", "."));
            }

            if (Ttl.getText().contains(",") == true) {
                Ttl.setText(Ttl.getText().replaceAll(",", "."));
            }
            BtnNilaiTBActionPerformed(null);
            Tulna.requestFocus();
        }
    }//GEN-LAST:event_TtlKeyPressed

    private void TulnaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TulnaKeyPressed
        Valid.pindah(evt, Ttl, TtbEst);
    }//GEN-LAST:event_TulnaKeyPressed

    private void BtnNilaiTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNilaiTBActionPerformed
        hitungTBest();
    }//GEN-LAST:event_BtnNilaiTBActionPerformed

    private void BtnNilaiTBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnNilaiTBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnNilaiTBActionPerformed(null);
        }
    }//GEN-LAST:event_BtnNilaiTBKeyPressed

    private void TbbKoreksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKoreksiKeyPressed
        Valid.pindah(evt, TtbEst, cmbSttsGizi);
    }//GEN-LAST:event_TbbKoreksiKeyPressed

    private void TbiokimiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbiokimiaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            ChkMual.requestFocus();
        }
    }//GEN-LAST:event_TbiokimiaKeyPressed

    private void TriwPenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwPenyakitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            tbDiagnosa.requestFocus();
        }
    }//GEN-LAST:event_TriwPenyakitKeyPressed

    private void TtbEstKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbEstKeyPressed
        Valid.pindah(evt, Tulna, TbbKoreksi);
    }//GEN-LAST:event_TtbEstKeyPressed

    private void ChkHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkHasilActionPerformed
        ThasilRecall.setText("");
        if (ChkHasil.isSelected() == true) {
            ThasilRecall.setEnabled(true);
            ThasilRecall.requestFocus();
        } else {
            ThasilRecall.setEnabled(false);
        }
    }//GEN-LAST:event_ChkHasilActionPerformed

    private void ChkAlergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAlergiActionPerformed
        Talergi.setText("");
        if (ChkAlergi.isSelected() == true) {
            Talergi.setEnabled(true);
            Talergi.requestFocus();
        } else {
            Talergi.setEnabled(false);
        }
    }//GEN-LAST:event_ChkAlergiActionPerformed

    private void ChkPantanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPantanganActionPerformed
        Tpantangan.setText("");
        if (ChkPantangan.isSelected() == true) {
            Tpantangan.setEnabled(true);
            Tpantangan.requestFocus();
        } else {
            Tpantangan.setEnabled(false);
        }
    }//GEN-LAST:event_ChkPantanganActionPerformed

    private void BtnCariDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariDiagnosaActionPerformed
        tampilDiagnosa();
    }//GEN-LAST:event_BtnCariDiagnosaActionPerformed

    private void BtnCariDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariDiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilDiagnosa();
        }
    }//GEN-LAST:event_BtnCariDiagnosaKeyPressed

    private void BtnAllDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllDiagnosaActionPerformed
        TCariDiagnosa.setText("");
        tampilDiagnosa();
    }//GEN-LAST:event_BtnAllDiagnosaActionPerformed

    private void BtnAllDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllDiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllDiagnosaActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCariDiagnosa, TCariDiagnosa);
        }
    }//GEN-LAST:event_BtnAllDiagnosaKeyPressed

    private void TCariDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariDiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariDiagnosaActionPerformed(null);
        }
    }//GEN-LAST:event_TCariDiagnosaKeyPressed

    private void MnHapusContengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusContengActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data diagnosa gizi belum ada...!!!!");
        } else {            
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                tbDiagnosa.setValueAt(Boolean.FALSE, i, 0);
            }
            tampilDiagnosa();
        }
    }//GEN-LAST:event_MnHapusContengActionPerformed

    private void TberkaitanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TberkaitanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tditandai.requestFocus();
        }
    }//GEN-LAST:event_TberkaitanKeyPressed

    private void TditandaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TditandaiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbBentuk.requestFocus();
        }
    }//GEN-LAST:event_TditandaiKeyPressed

    private void TkaloriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkaloriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TkaloriKeyPressed

    private void TproteinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TproteinKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TproteinKeyPressed

    private void TlemakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlemakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TlemakKeyPressed

    private void TkarboKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkarboKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TkarboKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsuhanGiziRanap dialog = new RMAsuhanGiziRanap(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll1;
    private widget.Button BtnAllDiagnosa;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari2;
    private widget.Button BtnCariDiagnosa;
    private widget.Button BtnCloseIn10;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnMonev;
    private widget.Button BtnNilaiIMT;
    private widget.Button BtnNilaiTB;
    private widget.Button BtnNotepad;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnRestor;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAccor;
    public widget.CekBox ChkAlergi;
    public widget.CekBox ChkAnorek;
    public widget.CekBox ChkDiare;
    public widget.CekBox ChkGangguan;
    public widget.CekBox ChkHasil;
    public widget.CekBox ChkKekurangan;
    public widget.CekBox ChkKelebihan;
    public widget.CekBox ChkKesulitan;
    public widget.CekBox ChkKonstipasi;
    public widget.CekBox ChkMakanKurang3;
    public widget.CekBox ChkMakanLebih3;
    public widget.CekBox ChkMual;
    public widget.CekBox ChkNyeri;
    public widget.CekBox ChkOedema;
    public widget.CekBox ChkPantangan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMenu;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHapusConteng;
    private javax.swing.JMenuItem MnRiwayatData;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.TextBox TCari;
    private widget.TextBox TCari2;
    private widget.TextBox TCariDiagnosa;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Talergi;
    private widget.TextBox Tbb;
    private widget.TextBox TbbKoreksi;
    private widget.TextArea Tberkaitan;
    private widget.TextArea Tbiokimia;
    private widget.TextArea Tditandai;
    private widget.TextArea Thasil;
    private widget.TextBox ThasilRecall;
    private widget.TextBox Timt;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tjk;
    private widget.TextBox TjnsDiet;
    private widget.TextBox Tkalori;
    private widget.TextBox Tkarbo;
    private widget.TextBox TklinisLain;
    private widget.TextBox Tlemak;
    private widget.TextBox Tlila;
    private widget.TextBox Tnip;
    private widget.TextBox TnmPetugas;
    private widget.TextBox Tpantangan;
    private widget.TextBox Tprotein;
    private widget.TextBox TrgRawat;
    private widget.TextArea TriwPenyakit;
    private widget.TextBox TriwayatLain;
    private widget.Label Tsttsumur;
    private widget.TextBox Ttb;
    private widget.TextBox TtbEst;
    private widget.TextBox Ttl;
    private widget.TextBox Tulna;
    private widget.TextBox Tumur;
    private javax.swing.JDialog WindowRiwayat;
    private widget.CekBox chkSaya;
    private widget.ComboBox cmbBentuk;
    private widget.ComboBox cmbSttsGizi;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
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
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.Label label105;
    private widget.Label label106;
    private widget.Label label107;
    private widget.Label label108;
    private widget.Label label109;
    private widget.Label label110;
    private widget.Label label111;
    private widget.Label label112;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbCPPT;
    private widget.Table tbDiagnosa;
    private widget.Table tbRestrain;
    private widget.Table tbRiwayat;
    private widget.Tanggal tglAsuhan;
    // End of variables declaration//GEN-END:variables

     private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pg.nama nmPetugas, date_format(ar.tgl_asesmen,'%d-%m-%Y') tglpengkajian, "
                    + "time_format(ar.jam_asesmen,'%H:%i') jam, ar.* FROM reg_periksa rp inner join asesmen_restrain ar on ar.no_rawat=rp.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis left join pegawai pg on pg.nik=ar.nip_petugas WHERE "
                    + "ar.tgl_asesmen BETWEEN ? AND ? AND rp.no_rawat LIKE ? OR "
                    + "ar.tgl_asesmen BETWEEN ? AND ? AND p.no_rkm_medis LIKE ? OR "
                    + "ar.tgl_asesmen BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                    + "ar.tgl_asesmen BETWEEN ? AND ? AND pg.nama LIKE ? ORDER BY ar.tgl_asesmen");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("kd_restrain"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglpengkajian"),
                        rs.getString("jam"),
                        rs.getString("nmPetugas"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("jam_asesmen"),
                        rs.getString("compos"),
                        rs.getString("apatis"),
                        rs.getString("delirium"),
                        rs.getString("somnolen"),
                        rs.getString("sopor"),
                        rs.getString("koma"),
                        rs.getString("gcs_e"),
                        rs.getString("gcs_m"),
                        rs.getString("gcs_v"),
                        rs.getString("tensi"),
                        rs.getString("nadi"),
                        rs.getString("suhu"),
                        rs.getString("napas"),
                        rs.getString("skala_nyeri"),
                        rs.getString("obs_gelisah"),
                        rs.getString("obs_kooperatif"),
                        rs.getString("obs_ketidakmampuan"),
                        rs.getString("klinis_diri_sendiri"),
                        rs.getString("klinis_orang_lain"),
                        rs.getString("klinis_gagal"),
                        rs.getString("res_non_farmakologi"),
                        rs.getString("res_tempat_tidur"),
                        rs.getString("res_pergelangan_tangan"),
                        rs.getString("res_tangan_kiri"),
                        rs.getString("res_tangan_kanan"),
                        rs.getString("res_pergelangan_kaki"),
                        rs.getString("res_kaki_kiri"),
                        rs.getString("res_kaki_kanan"),
                        rs.getString("res_lainlain"),
                        rs.getString("res_farmakologi"),
                        rs.getString("kalimat_res_farmakologi"),
                        rs.getString("kajian_1_jam"),
                        rs.getString("kajian_2_jam"),
                        rs.getString("kajian_lanjutan_2_jam"),
                        rs.getString("kajian_lanjutan_4_jam"),
                        rs.getString("obs_tanda"),
                        rs.getString("obs_lanjutan"),
                        rs.getString("menjelaskan_alasan"),
                        rs.getString("menjelaskan_kriteria"),
                        rs.getString("menjelaskan_informasi"),
                        rs.getString("nip_petugas")
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

    public void emptTeks() {
        Tbb.setText("0");
        Ttb.setText("0");
        hitungIMT();
        Tlila.setText("0");
        Ttl.setText("0");
        hitungTBest();
        Tulna.setText("0");
        TtbEst.setText("0");
        TbbKoreksi.setText("0");
        cmbSttsGizi.setSelectedIndex(0);
        Tbiokimia.setText("");
        
        mual = "";
        nyeri = "";
        diare = "";
        kesulitan = "";
        odema = "";
        konstipasi = "";
        anoreksia = "";
        gangguan = "";
        ChkMual.setSelected(false);
        ChkNyeri.setSelected(false);
        ChkDiare.setSelected(false);
        ChkKesulitan.setSelected(false);
        ChkOedema.setSelected(false);
        ChkKonstipasi.setSelected(false);
        ChkAnorek.setSelected(false);
        ChkGangguan.setSelected(false);
        TklinisLain.setText("");
        
        makanlebih = "";
        makankurang = "";
        lebihintek = "";
        kurangintek = "";
        hasil = "";
        alergi = "";
        pantangan = "";
        ChkMakanLebih3.setSelected(false);
        ChkMakanKurang3.setSelected(false);
        ChkKelebihan.setSelected(false);
        ChkKekurangan.setSelected(false);
        ChkHasil.setSelected(false);
        ThasilRecall.setText("");
        ThasilRecall.setEnabled(false);
        TriwayatLain.setText("");
        ChkAlergi.setSelected(false);
        Talergi.setText("");
        Talergi.setEnabled(false);
        ChkPantangan.setSelected(false);
        Tpantangan.setText("");
        Tpantangan.setEnabled(false);
        TriwPenyakit.setText("");
        Tberkaitan.setText("");
        Tditandai.setText("");
        cmbBentuk.setSelectedIndex(0);
        TjnsDiet.setText("");
        
        Tkalori.setText("");
        Tprotein.setText("");
        Tlemak.setText("");
        Tkarbo.setText("");
        Tnip.setText("-");
        TnmPetugas.setText("-");
        chkSaya.setSelected(false);
    }

    private void getData() {
        variabelBersih();
        if (tbRestrain.getSelectedRow() != -1) {
            TNoRw.setText(tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 0).toString());
            TNoRM.setText(tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 2).toString());
            TPasien.setText(tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 3).toString());
            Valid.SetTgl(tglAsuhan, tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 8).toString());
            TnmPetugas.setText(tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 6).toString());
            TrgRawat.setText(tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 7).toString());
            Tbb.setText(tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 16).toString());
            Tlila.setText(tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 17).toString());
            Tnip.setText(tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 50).toString());
            dataCek();
        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement("SELECT rp.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-Laki','Perempuan') jk, "
                    + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllahir, rp.tgl_registrasi, rp.umurdaftar, rp.sttsumur, rp.no_rawat "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "WHERE rp.no_rawat = ?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    Tjk.setText(rs.getString("jk"));                    
                    Valid.SetTgl(tglAsuhan, rs.getString("tgl_registrasi"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    
                    if (rs.getString("sttsumur").equals("Bl") || rs.getString("sttsumur").equals("Hr")) {
                        Tumur.setText(rs.getString("umurdaftar") + " " + rs.getString("sttsumur"));
                        Tsttsumur.setText("");
                        Tbb.setText(Sequel.cariIsi("select ifnull(bb_msk_rs,'0') from penilaian_awal_keperawatan_anak_ranap "
                                + "where no_rawat='" + rs.getString("no_rawat") + "'"));
                        Ttb.setText(Sequel.cariIsi("select ifnull(tb,'0') from penilaian_awal_keperawatan_anak_ranap "
                                + "where no_rawat='" + rs.getString("no_rawat") + "'"));
                    } else {
                        Tumur.setText(rs.getString("umurdaftar"));
                        Tsttsumur.setText("Tahun.");
                        Tbb.setText(Sequel.cariIsi("select ifnull(bb_msk_rs,'0') from penilaian_awal_keperawatan_dewasa_ranap "
                                + "where no_rawat='" + rs.getString("no_rawat") + "'"));
                        Ttb.setText(Sequel.cariIsi("select ifnull(tb,'0') from penilaian_awal_keperawatan_dewasa_ranap "
                                + "where no_rawat='" + rs.getString("no_rawat") + "'"));
                    }
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
            System.out.println("Notif : " + e);
        }
    }
    
    public void setData(String norwt, String rgrawat) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(new Date());
        TrgRawat.setText(rgrawat);
        isRawat();
        hitungIMT();
    }    
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnEdit.setEnabled(akses.getcppt());
        BtnPrint.setEnabled(akses.getcppt());
        BtnMonev.setEnabled(akses.getcppt());
        MnRiwayatData.setEnabled(akses.getadmin());
        
        if (akses.getjml2() >= 1) {            
//            BtnPerawat.setEnabled(false);
            Tnip.setText(akses.getkode());    
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPetugas, Tnip.getText());
            if (TnmPetugas.getText().equals("")) {
                Tnip.setText("");
//                JOptionPane.showMessageDialog(null, "User login bukan dokter...!!");
            }
        }        
    }

    public void setTampil(){
       TabRawat.setSelectedIndex(1);
       tampil();
    }
    
    private void ganti() {
        cekData();
//        if (Sequel.mengedittf("asesmen_restrain", "no_rawat=?", "tgl_asesmen=?, jam_asesmen=?, ruang_rawat=?, compos=?, "
//                + "apatis=?, delirium=?, somnolen=?, sopor=?, koma=?, gcs_e=?, gcs_m=?, gcs_v=?, tensi=?, nadi=?, suhu=?, napas=?, skala_nyeri=?, "
//                + "obs_gelisah=?, obs_kooperatif=?, obs_ketidakmampuan=?, klinis_diri_sendiri=?, klinis_orang_lain=?, klinis_gagal=?, "
//                + "res_non_farmakologi=?, res_tempat_tidur=?, res_pergelangan_tangan=?, res_tangan_kiri=?, res_tangan_kanan=?, "
//                + "res_pergelangan_kaki=?, res_kaki_kiri=?, res_kaki_kanan=?, res_lainlain=?, res_farmakologi=?, kalimat_res_farmakologi=?, "
//                + "kajian_1_jam=?, kajian_2_jam=?, kajian_lanjutan_2_jam=?, kajian_lanjutan_4_jam=?, obs_tanda=?, obs_lanjutan=?, "
//                + "menjelaskan_alasan=?, menjelaskan_kriteria=?, menjelaskan_informasi=?, nip_petugas=?", 45, new String[]{
//                    Valid.SetTgl(tglAsuhan.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TrgRawat.getText(),
//                    compos, apatis, delirium, somnolen, sopor, koma, Tbb.getText(), Tlila.getText(), Tgcsv.getText(), Ttensi.getText(), Tnadi.getText(), Tsuhu.getText(),
//                    Tnapas.getText(), Tskala.getText(), gelisah, kooperatif, ketidakmampuan, klinik_diri, klinis_orang, klinis_gagal, resnonfarma, restempat, resgelangtangan,
//                    restangankiri, restangankanan, resgelangkaki, reskakikiri, reskakikanan, reslain, resfarmakologi, TResFarmakologi.getText(), kajian1jam, kajian2jam,
//                    kajianlanjutan2jam, kajianlanjutan4jam, kajiantanda, kajianlanjutan, jelasalasan, jelaskriteria, jelasinformari, Tnip.getText(),
//                    tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 0).toString()
//                }) == true) {
//
//            TCari.setText(TNoRw.getText());
//            tampil();
//            emptTeks();
//            TabRawat.setSelectedIndex(1);
//        }
    }
    
    private void cekData() {
        // klinis/fisik
        if (ChkMual.isSelected() == true) {
            mual = "ya";
        } else {
            mual = "tidak";
        }
        
        if (ChkNyeri.isSelected() == true) {
            nyeri = "ya";
        } else {
            nyeri = "tidak";
        }
        
        if (ChkDiare.isSelected() == true) {
            diare = "ya";
        } else {
            diare = "tidak";
        }
        
        if (ChkKesulitan.isSelected() == true) {
            kesulitan = "ya";
        } else {
            kesulitan = "tidak";
        }
        
        if (ChkOedema.isSelected() == true) {
            odema = "ya";
        } else {
            odema = "tidak";
        }
        
        if (ChkKonstipasi.isSelected() == true) {
            konstipasi = "ya";
        } else {
            konstipasi = "tidak";
        }
        
        if (ChkAnorek.isSelected() == true) {
            anoreksia = "ya";
        } else {
            anoreksia = "tidak";
        }
        
        if (ChkGangguan.isSelected() == true) {
            gangguan = "ya";
        } else {
            gangguan = "tidak";
        }
        
        //riwayat gizi
        if (ChkMakanLebih3.isSelected() == true) {
            makanlebih = "ya";
        } else {
            makanlebih = "tidak";
        }
        
        if (ChkMakanKurang3.isSelected() == true) {
            makankurang = "ya";
        } else {
            makankurang = "tidak";
        }
        
        if (ChkKelebihan.isSelected() == true) {
            lebihintek = "ya";
        } else {
            lebihintek = "tidak";
        }
        
        if (ChkKekurangan.isSelected() == true) {
            kurangintek = "ya";
        } else {
            kurangintek = "tidak";
        }
        
        if (ChkHasil.isSelected() == true) {
            hasil = "ya";
        } else {
            hasil = "tidak";
        }
        
        if (ChkAlergi.isSelected() == true) {
            alergi = "ya";
        } else {
            alergi = "tidak";
        }
        
        if (ChkPantangan.isSelected() == true) {
            pantangan = "ya";
        } else {
            pantangan = "tidak";
        }
    }
    
    private void hapus() {
        if (Sequel.cariInteger("select count(-1) from observasi_restrain where no_rawat='" + TNoRw.getText() + "'") == 0) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                user = "";
                if (akses.getadmin() == true) {
                    user = "-";
                } else {
                    user = akses.getkode();
                }

                hapusDisimpan();
                if (Sequel.queryu2tf("delete from asesmen_restrain where no_rawat=?", 1, new String[]{
                    tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 0).toString()
                }) == true) {
                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data observasi restrain sudah ada tersimpan, hapus dulu data observasinya..!!");
        }
    }
    
    private void dataCek() {
        //klinis /fisik
        if (mual.equals("ya")) {
            ChkMual.setSelected(true);
        } else {
            ChkMual.setSelected(false);
        }
        
        if (nyeri.equals("ya")) {
            ChkNyeri.setSelected(true);
        } else {
            ChkNyeri.setSelected(false);
        }
        
        if (diare.equals("ya")) {
            ChkDiare.setSelected(true);
        } else {
            ChkDiare.setSelected(false);
        }
        
        if (kesulitan.equals("ya")) {
            ChkKesulitan.setSelected(true);
        } else {
            ChkKesulitan.setSelected(false);
        }
        
        if (odema.equals("ya")) {
            ChkOedema.setSelected(true);
        } else {
            ChkOedema.setSelected(false);
        }
        
        if (konstipasi.equals("ya")) {
            ChkKonstipasi.setSelected(true);
        } else {
            ChkKonstipasi.setSelected(false);
        }
        
        if (anoreksia.equals("ya")) {
            ChkAnorek.setSelected(true);
        } else {
            ChkAnorek.setSelected(false);
        }
        
        if (gangguan.equals("ya")) {
            ChkGangguan.setSelected(true);
        } else {
            ChkGangguan.setSelected(false);
        }
        
        //riwayat gizi
        if (makanlebih.equals("ya")) {
            ChkMakanLebih3.setSelected(true);
        } else {
            ChkMakanLebih3.setSelected(false);
        }
        
        if (makankurang.equals("ya")) {
            ChkMakanKurang3.setSelected(true);
        } else {
            ChkMakanKurang3.setSelected(false);
        }
        
        if (lebihintek.equals("ya")) {
            ChkKelebihan.setSelected(true);
        } else {
            ChkKelebihan.setSelected(false);
        }
        
        if (kurangintek.equals("ya")) {
            ChkKekurangan.setSelected(true);
        } else {
            ChkKekurangan.setSelected(false);
        }
        
        if (hasil.equals("ya")) {
            ChkHasil.setSelected(true);
            ThasilRecall.setEnabled(true);
        } else {
            ChkHasil.setSelected(false);
            ThasilRecall.setEnabled(false);
        }
        
        if (alergi.equals("ya")) {
            ChkAlergi.setSelected(true);
            Talergi.setEnabled(true);
        } else {
            ChkAlergi.setSelected(false);
            Talergi.setEnabled(false);
        }
        
        if (pantangan.equals("ya")) {
            ChkPantangan.setSelected(true);
            Tpantangan.setEnabled(true);
        } else {
            ChkPantangan.setSelected(false);
            Tpantangan.setEnabled(false);
        }
    }
    
    public void isMenu() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(900, HEIGHT));
            FormMenu.setVisible(true);
            ChkAccor.setVisible(true);
            Thasil.setText("");
            Tinstruksi.setText("");
            tampilCppt();
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(22, HEIGHT));
            FormMenu.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }
    
    private void tampilCppt() {
        Valid.tabelKosong(tabModeCppt);
        try {
            pscppt = koneksi.prepareStatement("SELECT c.verifikasi, DATE_FORMAT(c.tgl_cppt,'%d-%m-%Y') tgl, if(c.cek_jam='ya',TIME_FORMAT(c.jam_cppt,'%H:%i'),'-') jam, "
                    + "c.jenis_bagian, pg1.nama nmdpjp, c.jenis_ppa, pg2.nama nmppa, c.cppt_shift, c.hasil_pemeriksaan, "
                    + "c.instruksi_nakes, c.waktu_simpan, c.no_rawat, c.tgl_cppt, c.jam_cppt from cppt c "
                    + "inner join pegawai pg1 on pg1.nik=c.nip_konsulen "
                    + "inner join pegawai pg2 on pg2.nik=c.nip_ppa where "
                    + "c.flag_hapus='tidak' and c.status='ranap' and c.no_rawat='" + TNoRw.getText() + "' order by c.tgl_cppt, c.jam_cppt");
            try {
                rscppt = pscppt.executeQuery();                
                while (rscppt.next()) {
                    tabModeCppt.addRow(new String[]{
                        rscppt.getString("tgl"),
                        rscppt.getString("jam"),
                        rscppt.getString("jenis_bagian"),
                        rscppt.getString("nmdpjp"),
                        rscppt.getString("jenis_ppa"),
                        rscppt.getString("nmppa"),
                        rscppt.getString("cppt_shift"),
                        rscppt.getString("hasil_pemeriksaan"),
                        rscppt.getString("instruksi_nakes"),
                        rscppt.getString("no_rawat"),
                        rscppt.getString("tgl_cppt"),
                        rscppt.getString("jam_cppt")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscppt != null) {
                    rscppt.close();
                }
                if (pscppt != null) {
                    pscppt.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataCppt() {
        dataKonfirmasi = "";
        
        if (tbCPPT.getSelectedRow() != -1) {
            Thasil.setText("Tgl. CPPT : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 0).toString() + ", Jam : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 1).toString() + " WITA\n\n"
                    + "" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString());
            
            //konfirmasi terapi
            if (Sequel.cariInteger("select count(-1) from cppt_konfirmasi_terapi where "
                    + "no_rawat='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString() + "' "
                    + "and tgl_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString() + "' "
                    + "and cppt_shift='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString() + "' "
                    + "and jam_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString() + "'") > 0) {

                tampilKonfirmasi(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString());
                
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                }
            } else {
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString());
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")");
                }
            }
        }
    }
    
    private void tampilKonfirmasi(String norwt, String tglcppt, String sift, String jamcppt) {
        try {
            ps1 = koneksi.prepareStatement("select pg1.nama ptgs, date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i') jamlapor, "
                    + "pg2.nama dpjp, date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i') jamverif from cppt_konfirmasi_terapi ck "
                    + "inner join pegawai pg1 on pg1.nik=ck.nip_petugas_konfir inner join pegawai pg2 on pg2.nik=ck.nip_dpjp_konfir where "
                    + "ck.no_rawat = '" + norwt + "' and ck.tgl_cppt='" + tglcppt + "' and ck.cppt_shift='" + sift + "' "
                    + "and ck.jam_cppt='" + jamcppt + "' order by ck.waktu_simpan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    if (dataKonfirmasi.equals("")) {
                        dataKonfirmasi = "Tgl. Lapor : " + rs1.getString("tgllapor") + ", Jam : " + rs1.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs1.getString("tglverif") + ", Jam : " + rs1.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs1.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs1.getString("dpjp");
                    } else {
                        dataKonfirmasi = dataKonfirmasi + "\n\nTgl. Lapor : " + rs1.getString("tgllapor") + ", Jam : " + rs1.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs1.getString("tglverif") + ", Jam : " + rs1.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs1.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs1.getString("dpjp");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hapusDisimpan() {
        cekData();
//        if (Sequel.menyimpantf("asesmen_restrain_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 49, new String[]{
//                    TNoRw.getText(), TkodeRestrain.getText(), Valid.SetTgl(tglAsuhan.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TrgRawat.getText(),
//                    compos, apatis, delirium, somnolen, sopor, koma, Tbb.getText(), Tlila.getText(), Tgcsv.getText(), Ttensi.getText(), Tnadi.getText(), Tsuhu.getText(), Tnapas.getText(), Tskala.getText(), gelisah, kooperatif,
//                    ketidakmampuan, klinik_diri, klinis_orang, klinis_gagal, resnonfarma, restempat, resgelangtangan, restangankiri, restangankanan, resgelangkaki, reskakikiri, reskakikanan, reslain, resfarmakologi,
//                    TResFarmakologi.getText(), kajian1jam, kajian2jam, kajianlanjutan2jam, kajianlanjutan4jam, kajiantanda, kajianlanjutan, jelasalasan, jelaskriteria, jelasinformari, Tnip.getText(),
//                    "hapus", user, Sequel.cariIsi("select now()")
//                }) == true) {
//            System.out.println("Asesmen Restrain Dihapus Berhasil Tersimpan Sebagai Data Histori..!!");
//        }
    }
    
    private void gantiDisimpan() {
        cekData();
//        if (Sequel.menyimpantf("asesmen_restrain_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 49, new String[]{
//                    TNoRw.getText(), TkodeRestrain.getText(), Valid.SetTgl(tglAsuhan.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TrgRawat.getText(),
//                    compos, apatis, delirium, somnolen, sopor, koma, Tbb.getText(), Tlila.getText(), Tgcsv.getText(), Ttensi.getText(), Tnadi.getText(), Tsuhu.getText(), Tnapas.getText(), Tskala.getText(), gelisah, kooperatif,
//                    ketidakmampuan, klinik_diri, klinis_orang, klinis_gagal, resnonfarma, restempat, resgelangtangan, restangankiri, restangankanan, resgelangkaki, reskakikiri, reskakikanan, reslain, resfarmakologi,
//                    TResFarmakologi.getText(), kajian1jam, kajian2jam, kajianlanjutan2jam, kajianlanjutan4jam, kajiantanda, kajianlanjutan, jelasalasan, jelaskriteria, jelasinformari, Tnip.getText(),
//                    "ganti", user, Sequel.cariIsi("select now()")
//                }) == true) {
//            System.out.println("Asesmen Restrain Diganti Berhasil Tersimpan Sebagai Data Histori..!!");
//        }
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode1);
        try {
            psrestor = koneksi.prepareStatement("SELECT IF(pg.nama='-','Admin Utama',pg.nama) pelaku, a.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                    + "a.tgl_asesmen, a.waktu_eksekusi, upper(concat('DI',a.status_data)) sttsdata FROM asesmen_restrain_histori a "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = a.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg ON pg.nik = a.nik_eksekutor WHERE "
                    + "a.tgl_asesmen between ? and ? and pg.nama like ? or "
                    + "a.tgl_asesmen between ? and ? and a.no_rawat like ? or "
                    + "a.tgl_asesmen between ? and ? and p.no_rkm_medis like ? or "
                    + "a.tgl_asesmen between ? and ? and a.status_data like ? or "
                    + "a.tgl_asesmen between ? and ? and p.nm_pasien like ? order by a.tgl_asesmen desc");
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
                        rsrestor.getString("tgl_asesmen"),
                        rsrestor.getString("waktu_eksekusi"),
                        rsrestor.getString("sttsdata")
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
            ps2 = koneksi.prepareStatement("select * from asesmen_restrain_histori where "
                    + "waktu_eksekusi='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString() + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    try {
                        if (Sequel.menyimpantf("asesmen_restrain", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 46, new String[]{
                            rs2.getString("no_rawat"),
                            rs2.getString("kd_restrain"),
                            rs2.getString("tgl_asesmen"),
                            rs2.getString("jam_asesmen"),
                            rs2.getString("ruang_rawat"),
                            rs2.getString("compos"),
                            rs2.getString("apatis"),
                            rs2.getString("delirium"),
                            rs2.getString("somnolen"),
                            rs2.getString("sopor"),
                            rs2.getString("koma"),
                            rs2.getString("gcs_e"),
                            rs2.getString("gcs_m"),
                            rs2.getString("gcs_v"),
                            rs2.getString("tensi"),
                            rs2.getString("nadi"),
                            rs2.getString("suhu"),
                            rs2.getString("napas"),
                            rs2.getString("skala_nyeri"),
                            rs2.getString("obs_gelisah"),
                            rs2.getString("obs_kooperatif"),
                            rs2.getString("obs_ketidakmampuan"),
                            rs2.getString("klinis_diri_sendiri"),
                            rs2.getString("klinis_orang_lain"),
                            rs2.getString("klinis_gagal"),
                            rs2.getString("res_non_farmakologi"),
                            rs2.getString("res_tempat_tidur"),
                            rs2.getString("res_pergelangan_tangan"),
                            rs2.getString("res_tangan_kiri"),
                            rs2.getString("res_tangan_kanan"),
                            rs2.getString("res_pergelangan_kaki"),
                            rs2.getString("res_kaki_kiri"),
                            rs2.getString("res_kaki_kanan"),
                            rs2.getString("res_lainlain"),
                            rs2.getString("res_farmakologi"),
                            rs2.getString("kalimat_res_farmakologi"),
                            rs2.getString("kajian_1_jam"),
                            rs2.getString("kajian_2_jam"),
                            rs2.getString("kajian_lanjutan_2_jam"),
                            rs2.getString("kajian_lanjutan_4_jam"),
                            rs2.getString("obs_tanda"),
                            rs2.getString("obs_lanjutan"),
                            rs2.getString("menjelaskan_alasan"),
                            rs2.getString("menjelaskan_kriteria"),
                            rs2.getString("menjelaskan_informasi"),
                            rs2.getString("nip_petugas")
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
    
    private void kembalikanDataDiganti() {
        if (Sequel.queryu2tf("delete from asesmen_restrain where no_rawat=?", 1, new String[]{
            tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString()
        }) == true) {
            kembalikanData();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal dikembalikan..!!");
        }
    }
    
    private void variabelBersih() {
        user = "";
        dataKonfirmasi = "";
        mual = "";
        nyeri = "";
        diare = "";
        kesulitan = "";
        odema = "";
        konstipasi = "";
        anoreksia = "";
        gangguan = "";
        makanlebih = "";
        makankurang = "";
        lebihintek = "";
        kurangintek = "";
        hasil = "";
        alergi = "";
        pantangan = "";
    }
    
    public void tampilDiagnosa() {
        jml = 0;
        for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
            if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                jml++;
            }
        }
        
        pilih = null;
        pilih = new boolean[jml];
        kode = null;
        kode = new String[jml];
        diagnosa = null;
        diagnosa = new String[jml];
        
        jml = 0;
        for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
            if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                pilih[jml] = true;
                kode[jml] = tbDiagnosa.getValueAt(i, 1).toString();
                diagnosa[jml] = tbDiagnosa.getValueAt(i, 2).toString();
                jml++;
            }
        }
        
        Valid.tabelKosong(tabMode2);
        for (i = 0; i < jml; i++) {
            tabMode2.addRow(new Object[]{pilih[i], kode[i], diagnosa[i]});
        }
        
        try {
            ps3 = koneksi.prepareStatement("select * from katalog_diagnosa_gizi where "
                    + "deskripsi_diagnosa like ? order by kd_diagnosa_gizi");
            try {
                ps3.setString(1, "%" + TCariDiagnosa.getText().trim() + "%");
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabMode2.addRow(new Object[]{
                        false,
                        rs3.getString("kd_diagnosa_gizi"),
                        rs3.getString("deskripsi_diagnosa")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs3 != null) {
                    rs3.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hitungIMT() {
        try {
            double A, B, C, D, Total;
            if (Tbb.getText().equals("")) {
                Tbb.setText("0");
            }

            if (Ttb.getText().equals("")) {
                Ttb.setText("0");
            }
            
            if (Tbb.getText().contains(",") == true) {
                Tbb.setText(Tbb.getText().replaceAll(",", "."));
            }
            
            if (Ttb.getText().contains(",") == true) {
                Ttb.setText(Ttb.getText().replaceAll(",", "."));
            }

            A = Double.parseDouble(Tbb.getText());
            B = Double.parseDouble(Ttb.getText());
            C = B / 100;
            D = C * C;

            Total = 0;
            Total = A / D;
            
            if (Valid.SetAngka4(Total).equals("NaN")) {
                Timt.setText("0");
            } else {
                Timt.setText(Valid.SetAngka4(Total));
            }
            
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka BB masuk RS & tinggi badannya,    \n"
                    + "jika menggunakan koma, gantilah tanda koma dengan titik sebagai komanya !!");
        }
    }
    
    private void hitungTBest() {
        try {
            String umurCek = "";
            double A, B, C, D, E, F, totLaki, totPerempuan;
            if (Ttl.getText().equals("")) {
                Ttl.setText("0");
            }

            if (Ttl.getText().contains(",") == true) {
                Ttl.setText(Ttl.getText().replaceAll(",", "."));
            }
            
            if (Tsttsumur.getText().equals("")) {
                umurCek = "0";
            } else {
                umurCek = Tumur.getText();
                if (umurCek.equals("")) {
                    umurCek = "0";
                }

                if (umurCek.contains(",") == true) {
                    umurCek = umurCek.replaceAll(",", ".");
                }
            }

            A = Double.parseDouble(Ttl.getText());
            B = Double.parseDouble(umurCek);
            
            if (Tjk.getText().equals("Laki-Laki")) {
                // rumus laki-laki : (2,02XTL)-(0,04XU) + 64,19
                C = 2.02 * A;
                D = 0.04 * B;
                totLaki = C - D + 64.19;

                if (Valid.SetAngka4(totLaki).equals("NaN")) {
                    TtbEst.setText("0");
                } else {
                    TtbEst.setText(Valid.SetAngka4(totLaki));
                }
            } else {
                // rumus perempuan : (1,83XTL)-(0,24XU) + 84,88
                E = 1.83 * A;
                F = 0.24 * B;
                totPerempuan = E - F + 84.88;
                
                if (Valid.SetAngka4(totPerempuan).equals("NaN")) {
                    TtbEst.setText("0");
                } else {
                    TtbEst.setText(Valid.SetAngka4(totPerempuan));
                }
            }
            
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka tinggi lututnya, jika menggunakan    \n"
                    + "koma, gantilah tanda koma dengan titik sebagai komanya !!");
        }
    }
}
