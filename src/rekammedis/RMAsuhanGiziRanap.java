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
    private final DefaultTableModel tabMode, tabMode1, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, pscppt, psrestor;
    private ResultSet rs, rs1, rs2, rscppt, rsrestor;
    private int i = 0, x = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String compos = "", apatis = "", delirium = "", somnolen = "", sopor = "", koma = "", gelisah = "", kooperatif = "",
            ketidakmampuan = "", klinik_diri = "", klinis_orang = "", klinis_gagal = "", resnonfarma = "", restempat = "",
            resgelangtangan = "", restangankiri = "", restangankanan = "", resgelangkaki = "", reskakikiri = "", reskakikanan = "",
            reslain = "", resfarmakologi = "", kajian1jam = "", kajian2jam = "", kajianlanjutan2jam = "", kajianlanjutan4jam = "",
            kajiantanda = "", kajianlanjutan = "", jelasalasan = "", jelaskriteria = "", jelasinformari = "", user = "",
            dataKonfirmasi = "";

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
        BtnObservasi = new widget.Button();
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

        WindowRiwayat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayat.setName("WindowRiwayat"); // NOI18N
        WindowRiwayat.setUndecorated(true);
        WindowRiwayat.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Asesmen Restrain Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-06-2024" }));
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

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-06-2024" }));
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

        BtnObservasi.setForeground(new java.awt.Color(0, 0, 0));
        BtnObservasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/addressbook-edit24.png"))); // NOI18N
        BtnObservasi.setMnemonic('O');
        BtnObservasi.setText("Monitoring & Evaluasi");
        BtnObservasi.setToolTipText("Alt+O");
        BtnObservasi.setGlassColor(new java.awt.Color(0, 204, 204));
        BtnObservasi.setName("BtnObservasi"); // NOI18N
        BtnObservasi.setPreferredSize(new java.awt.Dimension(180, 30));
        BtnObservasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnObservasiActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnObservasi);

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
        FormInput.setPreferredSize(new java.awt.Dimension(870, 740));
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
        TPasien.setBounds(319, 10, 410, 23);

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
        Tbb.setBounds(115, 81, 50, 23);

        Tlila.setForeground(new java.awt.Color(0, 0, 0));
        Tlila.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tlila.setName("Tlila"); // NOI18N
        Tlila.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlilaKeyPressed(evt);
            }
        });
        FormInput.add(Tlila);
        Tlila.setBounds(567, 81, 50, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Perawat/Bidan :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 698, 110, 23);

        Tnip.setEditable(false);
        Tnip.setForeground(new java.awt.Color(0, 0, 0));
        Tnip.setName("Tnip"); // NOI18N
        FormInput.add(Tnip);
        Tnip.setBounds(115, 698, 170, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(289, 698, 390, 23);

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
        BtnPetugas.setBounds(680, 698, 28, 23);

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
        chkSaya.setBounds(715, 698, 87, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Asuhan :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 38, 110, 23);

        tglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-06-2024" }));
        tglAsuhan.setDisplayFormat("dd-MM-yyyy");
        tglAsuhan.setName("tglAsuhan"); // NOI18N
        tglAsuhan.setOpaque(false);
        tglAsuhan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(tglAsuhan);
        tglAsuhan.setBounds(115, 38, 90, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat : ");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(350, 38, 90, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(440, 38, 290, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Antropometri :");
        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 66, 110, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Berat Badan :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 81, 110, 23);

        label105.setForeground(new java.awt.Color(0, 0, 0));
        label105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label105.setText("Kg.    TB : ");
        label105.setName("label105"); // NOI18N
        label105.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label105);
        label105.setBounds(170, 81, 50, 23);

        Ttb.setForeground(new java.awt.Color(0, 0, 0));
        Ttb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ttb.setName("Ttb"); // NOI18N
        Ttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbKeyPressed(evt);
            }
        });
        FormInput.add(Ttb);
        Ttb.setBounds(220, 81, 50, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        FormInput.add(Tjk);
        Tjk.setBounds(260, 38, 90, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Jns. Kel : ");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(205, 38, 55, 23);

        label107.setForeground(new java.awt.Color(0, 0, 0));
        label107.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label107.setText("Cm.");
        label107.setName("label107"); // NOI18N
        label107.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label107);
        label107.setBounds(275, 81, 30, 23);

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
        BtnNilaiIMT.setBounds(310, 81, 93, 23);

        Timt.setEditable(false);
        Timt.setForeground(new java.awt.Color(0, 0, 0));
        Timt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Timt.setName("Timt"); // NOI18N
        FormInput.add(Timt);
        Timt.setBounds(410, 81, 70, 23);

        label108.setForeground(new java.awt.Color(0, 0, 0));
        label108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label108.setText("Kg/Cm3    LILA : ");
        label108.setName("label108"); // NOI18N
        label108.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label108);
        label108.setBounds(485, 81, 80, 23);

        label109.setForeground(new java.awt.Color(0, 0, 0));
        label109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label109.setText("Cm.");
        label109.setName("label109"); // NOI18N
        label109.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label109);
        label109.setBounds(623, 81, 30, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(400, 43));
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

        TabRawat.addTab("Input Assesmen", internalFrame2);

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
        jLabel19.setText("Tgl. Pengkajian :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-06-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-06-2024" }));
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

        TabRawat.addTab("Data Assesmen", internalFrame3);

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
        if (Sequel.cariInteger("select count(-1) from asesmen_restrain where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from asesmen_restrain where no_rawat='" + TNoRw.getText() + "'") == 0) {
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
        Valid.pindah(evt, Tbb, Tlila);
    }//GEN-LAST:event_TbbKeyPressed

    private void TlilaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlilaKeyPressed
//        Valid.pindah(evt, Tbb, Tgcsv);
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
        akses.setform("RMAsesmenRestrain");
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
            akses.setform("RMAsesmenRestrain");
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

        akses.setform("RMAsesmenRestrain");
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

    private void BtnObservasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnObservasiActionPerformed
        ChkAccor.setSelected(false);
        isMenu();

        if (Sequel.cariInteger("select count(-1) from asesmen_restrain where no_rawat='" + TNoRw.getText() + "'") > 0) {
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
        } else {
            JOptionPane.showMessageDialog(null, "Sebelum mengisi monitoring & evaluasi gizi, simpan dulu data asuhan gizinya...!!!");
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_BtnObservasiActionPerformed

    private void TtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TtbKeyPressed

    private void BtnNilaiIMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNilaiIMTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnNilaiIMTActionPerformed

    private void BtnNilaiIMTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnNilaiIMTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnNilaiIMTKeyPressed

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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari2;
    private widget.Button BtnCloseIn10;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnNilaiIMT;
    private widget.Button BtnNotepad;
    private widget.Button BtnObservasi;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnRestor;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAccor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMenu;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnRiwayatData;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll6;
    private widget.TextBox TCari;
    private widget.TextBox TCari2;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tbb;
    private widget.TextArea Thasil;
    private widget.TextBox Timt;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tjk;
    private widget.TextBox Tlila;
    private widget.TextBox Tnip;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TrgRawat;
    private widget.TextBox Ttb;
    private javax.swing.JDialog WindowRiwayat;
    private widget.CekBox chkSaya;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel12;
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
    private widget.Label jLabel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label105;
    private widget.Label label107;
    private widget.Label label108;
    private widget.Label label109;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbCPPT;
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
        compos = "";
        apatis = "";
        delirium = "";
        somnolen = "";
        sopor = "";
        koma = "";
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
            compos = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 10).toString();
            apatis = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 11).toString();
            delirium = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 12).toString();
            somnolen = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 13).toString();
            sopor = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 14).toString();
            koma = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 15).toString();
            Tbb.setText(tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 16).toString());
            Tlila.setText(tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 17).toString());
            gelisah = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 24).toString();
            kooperatif = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 25).toString();
            ketidakmampuan = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 26).toString();
            klinik_diri = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 27).toString();
            klinis_orang = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 28).toString();
            klinis_gagal = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 29).toString();
            resnonfarma = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 30).toString();
            restempat = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 31).toString();
            resgelangtangan = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 32).toString();
            restangankiri = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 33).toString();
            restangankanan = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 34).toString();
            resgelangkaki = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 35).toString();
            reskakikiri = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 36).toString();
            reskakikanan = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 37).toString();
            reslain = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 38).toString();
            resfarmakologi = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 39).toString();
            kajian1jam = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 41).toString();
            kajian2jam = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 42).toString();
            kajianlanjutan2jam = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 43).toString();
            kajianlanjutan4jam = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 44).toString();
            kajiantanda = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 45).toString();
            kajianlanjutan = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 46).toString();
            jelasalasan = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 47).toString();
            jelaskriteria = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 48).toString();
            jelasinformari = tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 49).toString();
            Tnip.setText(tbRestrain.getValueAt(tbRestrain.getSelectedRow(), 50).toString());
            dataCek();
        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement("SELECT rp.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-Laki','Perempuan') jk, "
                    + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllahir, rp.tgl_registrasi "
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
    
    public void setNoRm(String norwt, String rgrawat) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(new Date());
        TrgRawat.setText(rgrawat);
        isRawat();
        tampil();
    }    
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnEdit.setEnabled(akses.getcppt());
        BtnPrint.setEnabled(akses.getcppt());
        BtnObservasi.setEnabled(akses.getcppt());
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
        //kesadaran
//        if (ChkCompos.isSelected() == true) {
//            compos = "ya";
//        } else {
//            compos = "tidak";
//        }
//        
//        if (ChkApatis.isSelected() == true) {
//            apatis = "ya";
//        } else {
//            apatis = "tidak";
//        }
//        
//        if (ChkDelirium.isSelected() == true) {
//            delirium = "ya";
//        } else {
//            delirium = "tidak";
//        }
//        
//        if (ChkSomnolen.isSelected() == true) {
//            somnolen = "ya";
//        } else {
//            somnolen = "tidak";
//        }
//        
//        if (ChkSopor.isSelected() == true) {
//            sopor = "ya";
//        } else {
//            sopor = "tidak";
//        }
//        
//        if (ChkKoma.isSelected() == true) {
//            koma = "ya";
//        } else {
//            koma = "tidak";
//        }
//        
//        //hasil observasi
//        if (ChkGelisah.isSelected() == true) {
//            gelisah = "ya";
//        } else {
//            gelisah = "tidak";
//        }
//        
//        if (ChkKooperatif.isSelected() == true) {
//            kooperatif = "ya";
//        } else {
//            kooperatif = "tidak";
//        }
//        
//        if (ChkKetidakmampuan.isSelected() == true) {
//            ketidakmampuan = "ya";
//        } else {
//            ketidakmampuan = "tidak";
//        }
//        
//        //pertimbangan klinis
//        if (ChkKlinisDiri.isSelected() == true) {
//            klinik_diri = "ya";
//        } else {
//            klinik_diri = "tidak";
//        }
//        
//        if (ChkKlinisOrang.isSelected() == true) {
//            klinis_orang = "ya";
//        } else {
//            klinis_orang = "tidak";
//        }
//        
//        if (ChkKlinisGagal.isSelected() == true) {
//            klinis_gagal = "ya";
//        } else {
//            klinis_gagal = "tidak";
//        }
//        
//        //penilaian & instruksi dokter
//        if (ChkResNonFarma.isSelected() == true) {
//            resnonfarma = "ya";
//        } else {
//            resnonfarma = "tidak";
//        }
//        
//        if (ChkResTempatTidur.isSelected() == true) {
//            restempat = "ya";
//        } else {
//            restempat = "tidak";
//        }
//        
//        if (ChkResGelangTangan.isSelected() == true) {
//            resgelangtangan = "ya";
//        } else {
//            resgelangtangan = "tidak";
//        }
//        
//        if (ChkResTanganKiri.isSelected() == true) {
//            restangankiri = "ya";
//        } else {
//            restangankiri = "tidak";
//        }
//        
//        if (ChkResTanganKanan.isSelected() == true) {
//            restangankanan = "ya";
//        } else {
//            restangankanan = "tidak";
//        }
//        
//        if (ChkResGelangKaki.isSelected() == true) {
//            resgelangkaki = "ya";
//        } else {
//            resgelangkaki = "tidak";
//        }
//        
//        if (ChkResKakiKiri.isSelected() == true) {
//            reskakikiri = "ya";
//        } else {
//            reskakikiri = "tidak";
//        }
//        
//        if (ChkResKakiKanan.isSelected() == true) {
//            reskakikanan = "ya";
//        } else {
//            reskakikanan = "tidak";
//        }
//        
//        if (ChkResLain.isSelected() == true) {
//            reslain = "ya";
//        } else {
//            reslain = "tidak";
//        }
//        
//        if (ChkResFarmakologi.isSelected() == true) {
//            resfarmakologi = "ya";
//        } else {
//            resfarmakologi = "tidak";
//        }
//        
//        //rencana pengkajian lanjutan
//        if (ChkKajian1Jam.isSelected() == true) {
//            kajian1jam = "ya";
//        } else {
//            kajian1jam = "tidak";
//        }
//        
//        if (ChkKajian2Jam.isSelected() == true) {
//            kajian2jam = "ya";
//        } else {
//            kajian2jam = "tidak";
//        }
//        
//        if (ChkKajianLanjut2Jam.isSelected() == true) {
//            kajianlanjutan2jam = "ya";
//        } else {
//            kajianlanjutan2jam = "tidak";
//        }
//        
//        if (ChkKajianLanjut4Jam.isSelected() == true) {
//            kajianlanjutan4jam = "ya";
//        } else {
//            kajianlanjutan4jam = "tidak";
//        }
//        
//        if (ChkObsTanda.isSelected() == true) {
//            kajiantanda = "ya";
//        } else {
//            kajiantanda = "tidak";
//        }
//        
//        if (ChkObsLanjutan.isSelected() == true) {
//            kajianlanjutan = "ya";
//        } else {
//            kajianlanjutan = "tidak";
//        }
//        
//        //pendidikan restrain pasien
//        if (ChkAlasan.isSelected() == true) {
//            jelasalasan = "ya";
//        } else {
//            jelasalasan = "tidak";
//        }
//        
//        if (ChkKriteria.isSelected() == true) {
//            jelaskriteria = "ya";
//        } else {
//            jelaskriteria = "tidak";
//        }
//        
//        if (ChkInformasi.isSelected() == true) {
//            jelasinformari = "ya";
//        } else {
//            jelasinformari = "tidak";
//        }
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
        //kesadaran
//        if (compos.equals("ya")) {
//            ChkCompos.setSelected(true);
//        } else {
//            ChkCompos.setSelected(false);
//        }
//        
//        if (apatis.equals("ya")) {
//            ChkApatis.setSelected(true);
//        } else {
//            ChkApatis.setSelected(false);
//        }
//        
//        if (delirium.equals("ya")) {
//            ChkDelirium.setSelected(true);
//        } else {
//            ChkDelirium.setSelected(false);
//        }
//        
//        if (somnolen.equals("ya")) {
//            ChkSomnolen.setSelected(true);
//        } else {
//            ChkSomnolen.setSelected(false);
//        }
//        
//        if (sopor.equals("ya")) {
//            ChkSopor.setSelected(true);
//        } else {
//            ChkSopor.setSelected(false);
//        }
//        
//        if (koma.equals("ya")) {
//            ChkKoma.setSelected(true);
//        } else {
//            ChkKoma.setSelected(false);
//        }
//        
//        //hasil observasi
//        if (gelisah.equals("ya")) {
//            ChkGelisah.setSelected(true);
//        } else {
//            ChkGelisah.setSelected(false);
//        }
//        
//        if (kooperatif.equals("ya")) {
//            ChkKooperatif.setSelected(true);
//        } else {
//            ChkKooperatif.setSelected(false);
//        }
//        
//        if (ketidakmampuan.equals("ya")) {
//            ChkKetidakmampuan.setSelected(true);
//        } else {
//            ChkKetidakmampuan.setSelected(false);
//        }
//        
//        //pertimbangan klinis
//        if (klinik_diri.equals("ya")) {
//            ChkKlinisDiri.setSelected(true);
//        } else {
//            ChkKlinisDiri.setSelected(false);
//        }
//        
//        if (klinis_orang.equals("ya")) {
//            ChkKlinisOrang.setSelected(true);
//        } else {
//            ChkKlinisOrang.setSelected(false);
//        }
//        
//        if (klinis_gagal.equals("ya")) {
//            ChkKlinisGagal.setSelected(true);
//        } else {
//            ChkKlinisGagal.setSelected(false);
//        }
//        
//        //penilaian & instruksi dokter
//        if (resnonfarma.equals("ya")) {
//            ChkResNonFarma.setSelected(true);
//            ChkResTempatTidur.setEnabled(true);
//            ChkResGelangTangan.setEnabled(true);
//            ChkResGelangKaki.setEnabled(true);
//            ChkResLain.setEnabled(true);
//        } else {
//            ChkResNonFarma.setSelected(false);
//            ChkResTempatTidur.setEnabled(false);
//            ChkResGelangTangan.setEnabled(false);
//            ChkResGelangKaki.setEnabled(false);
//            ChkResLain.setEnabled(false);
//            
//            ChkResTanganKiri.setEnabled(false);
//            ChkResTanganKanan.setEnabled(false);
//            ChkResKakiKiri.setEnabled(false);
//            ChkResKakiKanan.setEnabled(false);
//        }
//        
//        if (restempat.equals("ya")) {
//            ChkResTempatTidur.setSelected(true);
//        } else {
//            ChkResTempatTidur.setSelected(false);
//        }
//        
//        if (resgelangtangan.equals("ya")) {
//            ChkResGelangTangan.setSelected(true);
//            ChkResTanganKiri.setEnabled(true);
//            ChkResTanganKanan.setEnabled(true);
//        } else {
//            ChkResGelangTangan.setSelected(false);
//            ChkResTanganKiri.setEnabled(false);
//            ChkResTanganKanan.setEnabled(false);
//        }
//        
//        if (restangankiri.equals("ya")) {
//            ChkResTanganKiri.setSelected(true);
//        } else {
//            ChkResTanganKiri.setSelected(false);
//        }
//        
//        if (restangankanan.equals("ya")) {
//            ChkResTanganKanan.setSelected(true);
//        } else {
//            ChkResTanganKanan.setSelected(false);
//        }
//        
//        if (resgelangkaki.equals("ya")) {
//            ChkResGelangKaki.setSelected(true);
//            ChkResKakiKiri.setEnabled(true);
//            ChkResKakiKanan.setEnabled(true);
//        } else {
//            ChkResGelangKaki.setSelected(false);
//            ChkResKakiKiri.setEnabled(false);
//            ChkResKakiKanan.setEnabled(false);
//        }
//        
//        if (reskakikiri.equals("ya")) {
//            ChkResKakiKiri.setSelected(true);
//        } else {
//            ChkResKakiKiri.setSelected(false);
//        }
//        
//        if (reskakikanan.equals("ya")) {
//            ChkResKakiKanan.setSelected(true);
//        } else {
//            ChkResKakiKanan.setSelected(false);
//        }
//        
//        if (reslain.equals("ya")) {
//            ChkResLain.setSelected(true);
//        } else {
//            ChkResLain.setSelected(false);
//        }
//        
//        if (resfarmakologi.equals("ya")) {
//            ChkResFarmakologi.setSelected(true);
//            TResFarmakologi.setEnabled(true);
//        } else {
//            ChkResFarmakologi.setSelected(false);
//            TResFarmakologi.setEnabled(false);
//        }
//        
//        //rencana pengkajian lanjutan
//        if (kajian1jam.equals("ya")) {
//            ChkKajian1Jam.setSelected(true);
//        } else {
//            ChkKajian1Jam.setSelected(false);
//        }
//        
//        if (kajian2jam.equals("ya")) {
//            ChkKajian2Jam.setSelected(true);
//        } else {
//            ChkKajian2Jam.setSelected(false);
//        }
//        
//        if (kajianlanjutan2jam.equals("ya")) {
//            ChkKajianLanjut2Jam.setSelected(true);
//        } else {
//            ChkKajianLanjut2Jam.setSelected(false);
//        }
//        
//        if (kajianlanjutan4jam.equals("ya")) {
//            ChkKajianLanjut4Jam.setSelected(true);
//        } else {
//            ChkKajianLanjut4Jam.setSelected(false);
//        }
//        
//        if (kajiantanda.equals("ya")) {
//            ChkObsTanda.setSelected(true);
//        } else {
//            ChkObsTanda.setSelected(false);
//        }
//        
//        if (kajianlanjutan.equals("ya")) {
//            ChkObsLanjutan.setSelected(true);
//        } else {
//            ChkObsLanjutan.setSelected(false);
//        }
//        
//        //pendidikan restrain pasien
//        if (jelasalasan.equals("ya")) {
//            ChkAlasan.setSelected(true);
//        } else {
//            ChkAlasan.setSelected(false);
//        }
//        
//        if (jelaskriteria.equals("ya")) {
//            ChkKriteria.setSelected(true);
//        } else {
//            ChkKriteria.setSelected(false);
//        }
//        
//        if (jelasinformari.equals("ya")) {
//            ChkInformasi.setSelected(true);
//        } else {
//            ChkInformasi.setSelected(false);
//        }
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
        compos = "";
        apatis = "";
        delirium = "";
        somnolen = "";
        sopor = "";
        koma = "";
        gelisah = "";
        kooperatif = "";
        ketidakmampuan = "";
        klinik_diri = "";
        klinis_orang = "";
        klinis_gagal = "";
        resnonfarma = "";
        restempat = "";
        resgelangtangan = "";
        restangankiri = "";
        restangankanan = "";
        resgelangkaki = "";
        reskakikiri = "";
        reskakikanan = "";
        reslain = "";
        resfarmakologi = "";
        kajian1jam = "";
        kajian2jam = "";
        kajianlanjutan2jam = "";
        kajianlanjutan4jam = "";
        kajiantanda = "";
        kajianlanjutan = "";
        jelasalasan = "";
        jelaskriteria = "";
        jelasinformari = "";
        user = "";
    }
}
