package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import laporan.DlgICDOncologyMorphology;
import laporan.DlgICDOncologyTopography;
import laporan.DlgPenyakit;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import simrskhanza.DlgKabupaten;
import simrskhanza.DlgKecamatan;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class RMRegisterCancer extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private int i = 0, x = 0;
    private DlgKabupaten kab;
    private DlgKecamatan kec;
    private DlgICDOncologyTopography icdOtopo;
    private DlgICDOncologyMorphology icdmor;
    private DlgCariPetugas petugas;    
    private String cekTglKon = "", cekTglAbs = "", sttsRawat = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMRegisterCancer(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Admisi/MRS", "Tgl. Verif", "Nama Petugas",
            "nm_awal", "nm_tengah", "nm_keluarga", "kode_pos_tetap", "alamat_sementara", "kec_semantara", "kab_semantara", "kode_pos_sementara", "jenkel", "suku",
            "agama", "status_pernikahan", "pekerjaan", "topography", "morphology", "most_valid", "clinical_ext", "treatment1", "treatment2", "treatment3", "treatment4",
            "treatment5", "distant_metastases1", "distant_metastases2", "distant_metastases3", "distant_metastases4", "distant_metastases5", "no_metastases",
            "tgl_diagnosis", "behavior", "grade", "stage", "laterality", "kesimpulan", "tgl_admisi_mrs", "cek_tgl_kontak_terakhir", "tgl_kontak_terakhir", "status",
            "register", "cek_tgl_abstrak", "tgl_abstrak", "nip_petugas_verif", "tgl_verifikasi", "status_rawat", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRegister.setModel(tabMode);
        tbRegister.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRegister.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 53; i++) {
            TableColumn column = tbRegister.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(85);
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
            }
        }
        tbRegister.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "No.", "Tgl. Periksa", "Kode Rumah Sakit", "Nama Rumah Sakit", "Unit ID", "Unit", "No. PA/Lab"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPeriksa.setModel(tabMode1);
        tbPeriksa.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPeriksa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbPeriksa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(160);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(110);
            }
        }
        tbPeriksa.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPeriksa.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPeriksa.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPeriksa.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        TnmAwal.setDocument(new batasInput((int) 50).getKata(TnmAwal));
        TnmTengah.setDocument(new batasInput((int) 50).getKata(TnmTengah));
        TnmKeluarga.setDocument(new batasInput((int) 100).getKata(TnmKeluarga));
        TkdPosTetap.setDocument(new batasInput((int) 10).getKata(TkdPosTetap));
        TalamatSementera.setDocument(new batasInput((int) 200).getKata(TalamatSementera));
        TkdPos.setDocument(new batasInput((int) 10).getKata(TkdPos));
        Tregister.setDocument(new batasInput((int) 100).getKata(Tregister));
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
        jLabel79 = new widget.Label();
        chkTglKontak = new widget.CekBox();
        jLabel64 = new widget.Label();
        Tnik = new widget.TextBox();
        jLabel65 = new widget.Label();
        TnmAwal = new widget.TextBox();
        jLabel66 = new widget.Label();
        TnmTengah = new widget.TextBox();
        jLabel67 = new widget.Label();
        TnmKeluarga = new widget.TextBox();
        jLabel68 = new widget.Label();
        TtmpLahir = new widget.TextBox();
        TtglLahir = new widget.TextBox();
        jLabel69 = new widget.Label();
        jLabel70 = new widget.Label();
        TalamatTetap = new widget.TextBox();
        jLabel71 = new widget.Label();
        TkdPosTetap = new widget.TextBox();
        jLabel72 = new widget.Label();
        TalamatSementera = new widget.TextBox();
        jLabel73 = new widget.Label();
        TkdKec = new widget.TextBox();
        TnmKec = new widget.TextBox();
        jLabel74 = new widget.Label();
        TkdKab = new widget.TextBox();
        TnmKab = new widget.TextBox();
        jLabel76 = new widget.Label();
        TkdPos = new widget.TextBox();
        jLabel77 = new widget.Label();
        cmbJenkel = new widget.ComboBox();
        jLabel78 = new widget.Label();
        cmbSuku = new widget.ComboBox();
        jLabel80 = new widget.Label();
        cmbAgama = new widget.ComboBox();
        jLabel85 = new widget.Label();
        cmbSttsPernikahan = new widget.ComboBox();
        jLabel86 = new widget.Label();
        cmbPekerjaan = new widget.ComboBox();
        jLabel87 = new widget.Label();
        TnoTelp = new widget.TextBox();
        jLabel88 = new widget.Label();
        TkodeC = new widget.TextBox();
        TnmDiagnosaKodeC = new widget.TextBox();
        jLabel89 = new widget.Label();
        TkodeM = new widget.TextBox();
        TnmDiagnosaKodeM = new widget.TextBox();
        jLabel90 = new widget.Label();
        cmbMost = new widget.ComboBox();
        jLabel91 = new widget.Label();
        cmbClinic = new widget.ComboBox();
        jLabel92 = new widget.Label();
        cmbTreat1 = new widget.ComboBox();
        cmbTreat2 = new widget.ComboBox();
        cmbTreat3 = new widget.ComboBox();
        cmbTreat4 = new widget.ComboBox();
        cmbTreat5 = new widget.ComboBox();
        jLabel93 = new widget.Label();
        cmbBehaviour = new widget.ComboBox();
        jLabel94 = new widget.Label();
        cmbDistan1 = new widget.ComboBox();
        jLabel95 = new widget.Label();
        cmbGrade = new widget.ComboBox();
        jLabel96 = new widget.Label();
        cmbStage = new widget.ComboBox();
        jLabel97 = new widget.Label();
        cmbLater = new widget.ComboBox();
        jLabel98 = new widget.Label();
        Scroll2 = new widget.ScrollPane();
        tbPeriksa = new widget.Table();
        jLabel99 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        Tkesimpulan = new widget.TextArea();
        jLabel100 = new widget.Label();
        TtglDiagnosis = new widget.Tanggal();
        jLabel101 = new widget.Label();
        TtglAdmisi = new widget.Tanggal();
        TtglKontak = new widget.Tanggal();
        jLabel102 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        jLabel81 = new widget.Label();
        Tregister = new widget.TextBox();
        chkTglAbstrak = new widget.CekBox();
        TtglAbstrak = new widget.Tanggal();
        jLabel103 = new widget.Label();
        TtglVerif = new widget.Tanggal();
        BtnKecamatan = new widget.Button();
        BtnKabupaten = new widget.Button();
        btnICDtopo = new widget.Button();
        btnICDmor = new widget.Button();
        jLabel104 = new widget.Label();
        jLabel105 = new widget.Label();
        jLabel106 = new widget.Label();
        jLabel107 = new widget.Label();
        cmbDistan2 = new widget.ComboBox();
        jLabel108 = new widget.Label();
        jLabel109 = new widget.Label();
        cmbDistan3 = new widget.ComboBox();
        cmbDistan4 = new widget.ComboBox();
        cmbDistan5 = new widget.ComboBox();
        jLabel110 = new widget.Label();
        jLabel111 = new widget.Label();
        jLabel112 = new widget.Label();
        cmbNoMetas = new widget.ComboBox();
        label_pekerjaan = new widget.Label();
        PanelInput1 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbRegister = new widget.Table();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Register Cancer (CanReg) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(760, 975));
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
        label20.setText("Petugas Verifikasi :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label20);
        label20.setBounds(0, 909, 140, 23);

        TnipPetugas.setEditable(false);
        TnipPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnipPetugas.setName("TnipPetugas"); // NOI18N
        TnipPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipPetugas);
        TnipPetugas.setBounds(145, 909, 150, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        TnmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(300, 909, 360, 23);

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
        BtnPetugas.setBounds(664, 909, 28, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Data Tumor :");
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 346, 140, 23);

        chkTglKontak.setBackground(new java.awt.Color(242, 242, 242));
        chkTglKontak.setForeground(new java.awt.Color(0, 0, 0));
        chkTglKontak.setText("Tgl. Terakhir Kontak :");
        chkTglKontak.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglKontak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglKontak.setName("chkTglKontak"); // NOI18N
        chkTglKontak.setOpaque(false);
        chkTglKontak.setPreferredSize(new java.awt.Dimension(220, 23));
        chkTglKontak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglKontakActionPerformed(evt);
            }
        });
        FormInput.add(chkTglKontak);
        chkTglKontak.setBounds(235, 853, 140, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("NIK :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 66, 140, 23);

        Tnik.setEditable(false);
        Tnik.setForeground(new java.awt.Color(0, 0, 0));
        Tnik.setName("Tnik"); // NOI18N
        FormInput.add(Tnik);
        Tnik.setBounds(145, 66, 150, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Nama Awal :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(335, 66, 100, 23);

        TnmAwal.setForeground(new java.awt.Color(0, 0, 0));
        TnmAwal.setName("TnmAwal"); // NOI18N
        TnmAwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmAwalKeyPressed(evt);
            }
        });
        FormInput.add(TnmAwal);
        TnmAwal.setBounds(439, 66, 320, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Nama Tengah :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(335, 94, 100, 23);

        TnmTengah.setForeground(new java.awt.Color(0, 0, 0));
        TnmTengah.setName("TnmTengah"); // NOI18N
        TnmTengah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmTengahKeyPressed(evt);
            }
        });
        FormInput.add(TnmTengah);
        TnmTengah.setBounds(439, 94, 320, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Nama Keluarga :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(335, 122, 100, 23);

        TnmKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        TnmKeluarga.setName("TnmKeluarga"); // NOI18N
        TnmKeluarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmKeluargaKeyPressed(evt);
            }
        });
        FormInput.add(TnmKeluarga);
        TnmKeluarga.setBounds(439, 122, 320, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Tempat Lahir :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 94, 140, 23);

        TtmpLahir.setEditable(false);
        TtmpLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtmpLahir.setName("TtmpLahir"); // NOI18N
        FormInput.add(TtmpLahir);
        TtmpLahir.setBounds(145, 94, 190, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        FormInput.add(TtglLahir);
        TtglLahir.setBounds(145, 122, 190, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Tgl. Lahir / Umur :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 122, 140, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Alamat Tetap :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 150, 140, 23);

        TalamatTetap.setEditable(false);
        TalamatTetap.setForeground(new java.awt.Color(0, 0, 0));
        TalamatTetap.setName("TalamatTetap"); // NOI18N
        FormInput.add(TalamatTetap);
        TalamatTetap.setBounds(145, 150, 614, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Kode Pos Alamat Tetap :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 178, 140, 23);

        TkdPosTetap.setForeground(new java.awt.Color(0, 0, 0));
        TkdPosTetap.setName("TkdPosTetap"); // NOI18N
        TkdPosTetap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkdPosTetapKeyPressed(evt);
            }
        });
        FormInput.add(TkdPosTetap);
        TkdPosTetap.setBounds(145, 178, 90, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Alamat Sementara :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 206, 140, 23);

        TalamatSementera.setForeground(new java.awt.Color(0, 0, 0));
        TalamatSementera.setName("TalamatSementera"); // NOI18N
        TalamatSementera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalamatSementeraKeyPressed(evt);
            }
        });
        FormInput.add(TalamatSementera);
        TalamatSementera.setBounds(145, 206, 614, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Kecamatan / Kota :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 234, 140, 23);

        TkdKec.setEditable(false);
        TkdKec.setForeground(new java.awt.Color(0, 0, 0));
        TkdKec.setName("TkdKec"); // NOI18N
        FormInput.add(TkdKec);
        TkdKec.setBounds(145, 234, 70, 23);

        TnmKec.setEditable(false);
        TnmKec.setForeground(new java.awt.Color(0, 0, 0));
        TnmKec.setName("TnmKec"); // NOI18N
        FormInput.add(TnmKec);
        TnmKec.setBounds(219, 234, 350, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Kabupaten / Provinsi :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 262, 140, 23);

        TkdKab.setEditable(false);
        TkdKab.setForeground(new java.awt.Color(0, 0, 0));
        TkdKab.setName("TkdKab"); // NOI18N
        FormInput.add(TkdKab);
        TkdKab.setBounds(145, 262, 70, 23);

        TnmKab.setEditable(false);
        TnmKab.setForeground(new java.awt.Color(0, 0, 0));
        TnmKab.setName("TnmKab"); // NOI18N
        FormInput.add(TnmKab);
        TnmKab.setBounds(219, 262, 350, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Kode Pos :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(605, 262, 70, 23);

        TkdPos.setForeground(new java.awt.Color(0, 0, 0));
        TkdPos.setName("TkdPos"); // NOI18N
        TkdPos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkdPosKeyPressed(evt);
            }
        });
        FormInput.add(TkdPos);
        TkdPos.setBounds(679, 262, 80, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Jenis Kelamin :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 290, 140, 23);

        cmbJenkel.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenkel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1. Laki-laki", "2. Perempuan", "9. Unknown" }));
        cmbJenkel.setName("cmbJenkel"); // NOI18N
        cmbJenkel.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbJenkel);
        cmbJenkel.setBounds(145, 290, 100, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Suku :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(250, 290, 50, 23);

        cmbSuku.setForeground(new java.awt.Color(0, 0, 0));
        cmbSuku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "00. Tidak Diketahui", "01. Melayu", "02. Non-Melayu", "03. Mongoloid", "04. Non-Mongoloid" }));
        cmbSuku.setName("cmbSuku"); // NOI18N
        cmbSuku.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbSuku);
        cmbSuku.setBounds(305, 290, 125, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Agama :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(430, 290, 60, 23);

        cmbAgama.setForeground(new java.awt.Color(0, 0, 0));
        cmbAgama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0. Unknown", "1. Islam", "2. Katolik", "3. Protestan", "4. Hindu", "5. Budha", "9. Other" }));
        cmbAgama.setName("cmbAgama"); // NOI18N
        cmbAgama.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbAgama);
        cmbAgama.setBounds(495, 290, 90, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Status Pernikahan :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(0, 318, 140, 23);

        cmbSttsPernikahan.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsPernikahan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1. Menikah", "2. Janda/Duda", "3. Belum menikah", "9. Unknown" }));
        cmbSttsPernikahan.setName("cmbSttsPernikahan"); // NOI18N
        cmbSttsPernikahan.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbSttsPernikahan);
        cmbSttsPernikahan.setBounds(145, 318, 115, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Pekerjaan :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(260, 318, 80, 23);

        cmbPekerjaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPekerjaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "00. Unknown", "01. Staf kantor", "02. Petani", "03. Buruh pabrik", "04. Militer/polisi", "05. Ibu R. Tangga", "06. Tenaga Medis", "07. Guru", "08. Pedagang", "99. Other" }));
        cmbPekerjaan.setName("cmbPekerjaan"); // NOI18N
        cmbPekerjaan.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbPekerjaan);
        cmbPekerjaan.setBounds(345, 318, 120, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("No. Telp./HP :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(465, 318, 90, 23);

        TnoTelp.setForeground(new java.awt.Color(0, 0, 0));
        TnoTelp.setName("TnoTelp"); // NOI18N
        TnoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoTelpKeyPressed(evt);
            }
        });
        FormInput.add(TnoTelp);
        TnoTelp.setBounds(559, 318, 150, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("<html><div style='text-align:right;'>Topography :<br>(ICD-O Kode C)</div></html>");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(0, 374, 140, 30);

        TkodeC.setEditable(false);
        TkodeC.setForeground(new java.awt.Color(0, 0, 0));
        TkodeC.setName("TkodeC"); // NOI18N
        FormInput.add(TkodeC);
        TkodeC.setBounds(145, 374, 80, 23);

        TnmDiagnosaKodeC.setEditable(false);
        TnmDiagnosaKodeC.setForeground(new java.awt.Color(0, 0, 0));
        TnmDiagnosaKodeC.setName("TnmDiagnosaKodeC"); // NOI18N
        FormInput.add(TnmDiagnosaKodeC);
        TnmDiagnosaKodeC.setBounds(229, 374, 530, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("<html><div style='text-align:right;'>Morphology :<br>(ICD-O Kode M)</div></html>");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 410, 140, 30);

        TkodeM.setEditable(false);
        TkodeM.setForeground(new java.awt.Color(0, 0, 0));
        TkodeM.setName("TkodeM"); // NOI18N
        FormInput.add(TkodeM);
        TkodeM.setBounds(145, 410, 80, 23);

        TnmDiagnosaKodeM.setEditable(false);
        TnmDiagnosaKodeM.setForeground(new java.awt.Color(0, 0, 0));
        TnmDiagnosaKodeM.setName("TnmDiagnosaKodeM"); // NOI18N
        FormInput.add(TnmDiagnosaKodeM);
        TnmDiagnosaKodeM.setBounds(229, 410, 530, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Most valid Basic of diagnosis cancer :");
        jLabel90.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(0, 445, 210, 23);

        cmbMost.setForeground(new java.awt.Color(0, 0, 0));
        cmbMost.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0. Death Certivicate Only", "1. Clinical Only", "2. Clin. Invest./Ult Soun", "3. Surgery/Autopsy", "4. Laboratory test", "5. Cytology", "6. Histology of matestase", "7. Histology of primary", "8. Autopsy/Histology", "9. Unknown" }));
        cmbMost.setName("cmbMost"); // NOI18N
        cmbMost.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbMost);
        cmbMost.setBounds(215, 445, 155, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Clinical ext. of disease before treatment :");
        jLabel91.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(0, 473, 240, 23);

        cmbClinic.setForeground(new java.awt.Color(0, 0, 0));
        cmbClinic.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1. In situ", "2. Localized", "3. Direct extension", "4. Regional LN involvement", "5. Direct extension with regional LN involvement", "6. Distant metastases", "8. Not applicable", "9. Unknown" }));
        cmbClinic.setName("cmbClinic"); // NOI18N
        cmbClinic.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbClinic);
        cmbClinic.setBounds(245, 473, 265, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Treatment at reporting institution : 1.");
        jLabel92.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(0, 501, 210, 23);

        cmbTreat1.setForeground(new java.awt.Color(0, 0, 0));
        cmbTreat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "00. No Treatment", "01. Surgery", "02. Radiotherapy", "03. Chemotherapy", "04. Chemoradiation", "05. Tergeted Therapy", "06. Immuno Therapy", "07. Hormonal Therapy", "08. Other Therapy", "09. Unknown" }));
        cmbTreat1.setName("cmbTreat1"); // NOI18N
        cmbTreat1.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbTreat1);
        cmbTreat1.setBounds(215, 501, 140, 23);

        cmbTreat2.setForeground(new java.awt.Color(0, 0, 0));
        cmbTreat2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "00. No Treatment", "01. Surgery", "02. Radiotherapy", "03. Chemotherapy", "04. Chemoradiation", "05. Tergeted Therapy", "06. Immuno Therapy", "07. Hormonal Therapy", "08. Other Therapy", "09. Unknown" }));
        cmbTreat2.setName("cmbTreat2"); // NOI18N
        cmbTreat2.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbTreat2);
        cmbTreat2.setBounds(391, 501, 140, 23);

        cmbTreat3.setForeground(new java.awt.Color(0, 0, 0));
        cmbTreat3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "00. No Treatment", "01. Surgery", "02. Radiotherapy", "03. Chemotherapy", "04. Chemoradiation", "05. Tergeted Therapy", "06. Immuno Therapy", "07. Hormonal Therapy", "08. Other Therapy", "09. Unknown" }));
        cmbTreat3.setName("cmbTreat3"); // NOI18N
        cmbTreat3.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbTreat3);
        cmbTreat3.setBounds(568, 501, 140, 23);

        cmbTreat4.setForeground(new java.awt.Color(0, 0, 0));
        cmbTreat4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "00. No Treatment", "01. Surgery", "02. Radiotherapy", "03. Chemotherapy", "04. Chemoradiation", "05. Tergeted Therapy", "06. Immuno Therapy", "07. Hormonal Therapy", "08. Other Therapy", "09. Unknown" }));
        cmbTreat4.setName("cmbTreat4"); // NOI18N
        cmbTreat4.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbTreat4);
        cmbTreat4.setBounds(215, 529, 140, 23);

        cmbTreat5.setForeground(new java.awt.Color(0, 0, 0));
        cmbTreat5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "00. No Treatment", "01. Surgery", "02. Radiotherapy", "03. Chemotherapy", "04. Chemoradiation", "05. Tergeted Therapy", "06. Immuno Therapy", "07. Hormonal Therapy", "08. Other Therapy", "09. Unknown" }));
        cmbTreat5.setName("cmbTreat5"); // NOI18N
        cmbTreat5.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbTreat5);
        cmbTreat5.setBounds(391, 529, 140, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Behaviour :");
        jLabel93.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(372, 445, 70, 23);

        cmbBehaviour.setForeground(new java.awt.Color(0, 0, 0));
        cmbBehaviour.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0. Benign", "1. Uncertain", "2. Insitu", "3. Malignant", "9. Unknown" }));
        cmbBehaviour.setName("cmbBehaviour"); // NOI18N
        cmbBehaviour.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbBehaviour);
        cmbBehaviour.setBounds(447, 445, 95, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Distant Metastases : 1.");
        jLabel94.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(0, 557, 140, 23);

        cmbDistan1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDistan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "00. None", "01. Lymph Node", "02. Bone", "03. Liver", "04. Lung", "05. Brain", "06. Ovary", "07. Skin", "08. Stomach", "09. Bone Marrow", "10. Endocrine", "11. Cavum Pleura", "12. Bladder", "13. Colon", "14. Others", "15. Unknown" }));
        cmbDistan1.setName("cmbDistan1"); // NOI18N
        cmbDistan1.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbDistan1);
        cmbDistan1.setBounds(145, 557, 120, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Grade :");
        jLabel95.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(420, 557, 50, 23);

        cmbGrade.setForeground(new java.awt.Color(0, 0, 0));
        cmbGrade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "00. Unknown", "01. Well diff", "02. Mod diff", "03. Poor diff", "04. Undiff", "05. Pos T-cell", "06. Pos B-cell", "07. Null cell", "08. NK cell", "09. Not applicable", "10. Dediff" }));
        cmbGrade.setName("cmbGrade"); // NOI18N
        cmbGrade.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbGrade);
        cmbGrade.setBounds(478, 557, 120, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Stage :");
        jLabel96.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(420, 585, 50, 23);

        cmbStage.setForeground(new java.awt.Color(0, 0, 0));
        cmbStage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "001.1", "002.1A", "003.1B", "004.1C", "005.2", "006.2A", "007.2B", "008.2C", "009.3", "010.3A", "011.3B", "012.3C", "013.4", "015.4A", "016.4B", "017.4C", "018.7", "019.8", "020.9" }));
        cmbStage.setName("cmbStage"); // NOI18N
        cmbStage.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbStage);
        cmbStage.setBounds(478, 585, 70, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Laterality :");
        jLabel97.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(550, 585, 70, 23);

        cmbLater.setForeground(new java.awt.Color(0, 0, 0));
        cmbLater.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1. Right", "2. Left", "3. Central", "4. Bilateral", "5. Multiple", "8. Not applicable", "9. Unknown" }));
        cmbLater.setName("cmbLater"); // NOI18N
        cmbLater.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbLater);
        cmbLater.setBounds(627, 585, 115, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Source / Follow up :");
        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(0, 641, 140, 23);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbPeriksa.setName("tbPeriksa"); // NOI18N
        tbPeriksa.getTableHeader().setReorderingAllowed(false);
        Scroll2.setViewportView(tbPeriksa);

        FormInput.add(Scroll2);
        Scroll2.setBounds(145, 641, 630, 130);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Kesimpulan :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(0, 777, 140, 23);

        scrollPane4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane4.setName("scrollPane4"); // NOI18N

        Tkesimpulan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkesimpulan.setColumns(20);
        Tkesimpulan.setRows(5);
        Tkesimpulan.setName("Tkesimpulan"); // NOI18N
        Tkesimpulan.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tkesimpulan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesimpulanKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(Tkesimpulan);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(145, 777, 630, 70);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Tgl. Diagnosis :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(542, 445, 90, 23);

        TtglDiagnosis.setEditable(false);
        TtglDiagnosis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2026" }));
        TtglDiagnosis.setDisplayFormat("dd-MM-yyyy");
        TtglDiagnosis.setName("TtglDiagnosis"); // NOI18N
        TtglDiagnosis.setOpaque(false);
        TtglDiagnosis.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglDiagnosis);
        TtglDiagnosis.setBounds(639, 445, 90, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Tgl. Admisi/MRS :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(0, 853, 140, 23);

        TtglAdmisi.setEditable(false);
        TtglAdmisi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2026" }));
        TtglAdmisi.setDisplayFormat("dd-MM-yyyy");
        TtglAdmisi.setName("TtglAdmisi"); // NOI18N
        TtglAdmisi.setOpaque(false);
        TtglAdmisi.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglAdmisi);
        TtglAdmisi.setBounds(145, 853, 90, 23);

        TtglKontak.setEditable(false);
        TtglKontak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2026" }));
        TtglKontak.setDisplayFormat("dd-MM-yyyy");
        TtglKontak.setName("TtglKontak"); // NOI18N
        TtglKontak.setOpaque(false);
        TtglKontak.setPreferredSize(new java.awt.Dimension(90, 23));
        TtglKontak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TtglKontakActionPerformed(evt);
            }
        });
        FormInput.add(TtglKontak);
        TtglKontak.setBounds(380, 853, 90, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Status :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(470, 853, 60, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1. Hidup", "2. Meninggal", "9. Uknnown" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbStatus);
        cmbStatus.setBounds(535, 853, 95, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Register :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 881, 140, 23);

        Tregister.setForeground(new java.awt.Color(0, 0, 0));
        Tregister.setName("Tregister"); // NOI18N
        Tregister.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TregisterKeyPressed(evt);
            }
        });
        FormInput.add(Tregister);
        Tregister.setBounds(145, 881, 320, 23);

        chkTglAbstrak.setBackground(new java.awt.Color(242, 242, 242));
        chkTglAbstrak.setForeground(new java.awt.Color(0, 0, 0));
        chkTglAbstrak.setText("Tgl. Abstrak :");
        chkTglAbstrak.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglAbstrak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglAbstrak.setName("chkTglAbstrak"); // NOI18N
        chkTglAbstrak.setOpaque(false);
        chkTglAbstrak.setPreferredSize(new java.awt.Dimension(220, 23));
        chkTglAbstrak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglAbstrakActionPerformed(evt);
            }
        });
        FormInput.add(chkTglAbstrak);
        chkTglAbstrak.setBounds(470, 881, 100, 23);

        TtglAbstrak.setEditable(false);
        TtglAbstrak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2026" }));
        TtglAbstrak.setDisplayFormat("dd-MM-yyyy");
        TtglAbstrak.setName("TtglAbstrak"); // NOI18N
        TtglAbstrak.setOpaque(false);
        TtglAbstrak.setPreferredSize(new java.awt.Dimension(90, 23));
        TtglAbstrak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TtglAbstrakActionPerformed(evt);
            }
        });
        FormInput.add(TtglAbstrak);
        TtglAbstrak.setBounds(576, 881, 90, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Tgl. Verifikasi :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(0, 937, 140, 23);

        TtglVerif.setEditable(false);
        TtglVerif.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2026" }));
        TtglVerif.setDisplayFormat("dd-MM-yyyy");
        TtglVerif.setName("TtglVerif"); // NOI18N
        TtglVerif.setOpaque(false);
        TtglVerif.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglVerif);
        TtglVerif.setBounds(145, 937, 90, 23);

        BtnKecamatan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKecamatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnKecamatan.setMnemonic('3');
        BtnKecamatan.setToolTipText("ALt+3");
        BtnKecamatan.setName("BtnKecamatan"); // NOI18N
        BtnKecamatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKecamatanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKecamatan);
        BtnKecamatan.setBounds(575, 234, 28, 23);

        BtnKabupaten.setForeground(new java.awt.Color(0, 0, 0));
        BtnKabupaten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnKabupaten.setMnemonic('4');
        BtnKabupaten.setToolTipText("ALt+4");
        BtnKabupaten.setName("BtnKabupaten"); // NOI18N
        BtnKabupaten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKabupatenActionPerformed(evt);
            }
        });
        FormInput.add(BtnKabupaten);
        BtnKabupaten.setBounds(575, 262, 28, 23);

        btnICDtopo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnICDtopo.setMnemonic('2');
        btnICDtopo.setToolTipText("Alt+2");
        btnICDtopo.setName("btnICDtopo"); // NOI18N
        btnICDtopo.setPreferredSize(new java.awt.Dimension(28, 23));
        btnICDtopo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnICDtopoActionPerformed(evt);
            }
        });
        FormInput.add(btnICDtopo);
        btnICDtopo.setBounds(764, 374, 28, 23);

        btnICDmor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnICDmor.setMnemonic('2');
        btnICDmor.setToolTipText("Alt+2");
        btnICDmor.setName("btnICDmor"); // NOI18N
        btnICDmor.setPreferredSize(new java.awt.Dimension(28, 23));
        btnICDmor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnICDmorActionPerformed(evt);
            }
        });
        FormInput.add(btnICDmor);
        btnICDmor.setBounds(764, 410, 28, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("2.");
        jLabel104.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(355, 501, 30, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("3.");
        jLabel105.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(532, 501, 30, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("4.");
        jLabel106.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(180, 529, 30, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("5.");
        jLabel107.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(355, 529, 30, 23);

        cmbDistan2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDistan2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "00. None", "01. Lymph Node", "02. Bone", "03. Liver", "04. Lung", "05. Brain", "06. Ovary", "07. Skin", "08. Stomach", "09. Bone Marrow", "10. Endocrine", "11. Cavum Pleura", "12. Bladder", "13. Colon", "14. Others", "15. Unknown" }));
        cmbDistan2.setName("cmbDistan2"); // NOI18N
        cmbDistan2.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbDistan2);
        cmbDistan2.setBounds(145, 585, 120, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("2.");
        jLabel108.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(110, 585, 30, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("3.");
        jLabel109.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(110, 613, 30, 23);

        cmbDistan3.setForeground(new java.awt.Color(0, 0, 0));
        cmbDistan3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "00. None", "01. Lymph Node", "02. Bone", "03. Liver", "04. Lung", "05. Brain", "06. Ovary", "07. Skin", "08. Stomach", "09. Bone Marrow", "10. Endocrine", "11. Cavum Pleura", "12. Bladder", "13. Colon", "14. Others", "15. Unknown" }));
        cmbDistan3.setName("cmbDistan3"); // NOI18N
        cmbDistan3.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbDistan3);
        cmbDistan3.setBounds(145, 613, 120, 23);

        cmbDistan4.setForeground(new java.awt.Color(0, 0, 0));
        cmbDistan4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "00. None", "01. Lymph Node", "02. Bone", "03. Liver", "04. Lung", "05. Brain", "06. Ovary", "07. Skin", "08. Stomach", "09. Bone Marrow", "10. Endocrine", "11. Cavum Pleura", "12. Bladder", "13. Colon", "14. Others", "15. Unknown" }));
        cmbDistan4.setName("cmbDistan4"); // NOI18N
        cmbDistan4.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbDistan4);
        cmbDistan4.setBounds(300, 557, 120, 23);

        cmbDistan5.setForeground(new java.awt.Color(0, 0, 0));
        cmbDistan5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "00. None", "01. Lymph Node", "02. Bone", "03. Liver", "04. Lung", "05. Brain", "06. Ovary", "07. Skin", "08. Stomach", "09. Bone Marrow", "10. Endocrine", "11. Cavum Pleura", "12. Bladder", "13. Colon", "14. Others", "15. Unknown" }));
        cmbDistan5.setName("cmbDistan5"); // NOI18N
        cmbDistan5.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbDistan5);
        cmbDistan5.setBounds(300, 585, 120, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("5.");
        jLabel110.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(265, 585, 30, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("4.");
        jLabel111.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(265, 557, 30, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("No. of Metastases :");
        jLabel112.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(265, 613, 110, 23);

        cmbNoMetas.setForeground(new java.awt.Color(0, 0, 0));
        cmbNoMetas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "00. None", "01. Lymph Node", "02. Bone", "03. Liver", "04. Lung", "05. Brain", "06. Ovary", "07. Skin", "08. Stomach", "09. Bone Marrow", "10. Endocrine", "11. Cavum Pleura", "12. Bladder", "13. Colon", "14. Others", "15. Unknown" }));
        cmbNoMetas.setName("cmbNoMetas"); // NOI18N
        cmbNoMetas.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbNoMetas);
        cmbNoMetas.setBounds(385, 613, 120, 23);

        label_pekerjaan.setForeground(new java.awt.Color(0, 0, 0));
        label_pekerjaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_pekerjaan.setText("Pekerjaan : -");
        label_pekerjaan.setToolTipText("");
        label_pekerjaan.setName("label_pekerjaan"); // NOI18N
        FormInput.add(label_pekerjaan);
        label_pekerjaan.setBounds(285, 346, 600, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass13.add(Scroll1);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Register Cancer (CanReg) ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(700, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbRegister.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbRegister.setName("tbRegister"); // NOI18N
        tbRegister.getTableHeader().setReorderingAllowed(false);
        tbRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRegisterMouseClicked(evt);
            }
        });
        tbRegister.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRegisterKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbRegister);

        PanelInput1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 86));
        panelGlass11.setLayout(new java.awt.BorderLayout());

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Verifikasi :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2026" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2026" }));
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
        } else {
            cekData();
            if (Sequel.menyimpantf("register_cancer", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 47, new String[]{
                TNoRw.getText(), TrgRawat.getText(), TnmAwal.getText(), TnmTengah.getText(), TnmKeluarga.getText(), TkdPosTetap.getText(), TalamatSementera.getText(),
                TkdKec.getText(), TkdKab.getText(), TkdPos.getText(), cmbJenkel.getSelectedItem().toString(), cmbSuku.getSelectedItem().toString(),
                cmbAgama.getSelectedItem().toString(), cmbSttsPernikahan.getSelectedItem().toString(), cmbPekerjaan.getSelectedItem().toString(), TkodeC.getText(),
                TkodeM.getText(), cmbMost.getSelectedItem().toString(), cmbClinic.getSelectedItem().toString(), cmbTreat1.getSelectedItem().toString(),
                cmbTreat2.getSelectedItem().toString(), cmbTreat3.getSelectedItem().toString(), cmbTreat4.getSelectedItem().toString(), cmbTreat5.getSelectedItem().toString(),
                cmbDistan1.getSelectedItem().toString(), cmbDistan2.getSelectedItem().toString(), cmbDistan3.getSelectedItem().toString(), cmbDistan4.getSelectedItem().toString(),
                cmbDistan5.getSelectedItem().toString(), cmbNoMetas.getSelectedItem().toString(), Valid.SetTgl(TtglDiagnosis.getSelectedItem() + ""),
                cmbBehaviour.getSelectedItem().toString(), cmbGrade.getSelectedItem().toString(), cmbStage.getSelectedItem().toString(), cmbLater.getSelectedItem().toString(),
                Tkesimpulan.getText(), Valid.SetTgl(TtglAdmisi.getSelectedItem() + ""), cekTglKon, Valid.SetTgl(TtglKontak.getSelectedItem() + ""),
                cmbStatus.getSelectedItem().toString(), Tregister.getText(), cekTglAbs, Valid.SetTgl(TtglAbstrak.getSelectedItem() + ""), TnipPetugas.getText(),
                Valid.SetTgl(TtglVerif.getSelectedItem() + ""), sttsRawat, Sequel.cariIsi("select now()")
            }) == true) {
                Sequel.queryu("update pasien set no_tlp='" + TnoTelp.getText() + "' where no_rkm_medis='" + TNoRM.getText() + "'");
                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Register Cancer (CanReg)", "Simpan");
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
        } else {
            if (tbRegister.getSelectedRow() > -1) {
                cekData();
                if (Sequel.mengedittf("register_cancer", "no_rawat=?", "nm_awal=?, nm_tengah=?, nm_keluarga=?, kode_pos_tetap=?, alamat_sementara=?, kec_semantara=?, "
                        + "kab_semantara=?, kode_pos_sementara=?, jenkel=?, suku=?, agama=?, status_pernikahan=?, pekerjaan=?, topography=?, morphology=?, most_valid=?, clinical_ext=?, "
                        + "treatment1=?, treatment2=?, treatment3=?, treatment4=?, treatment5=?, distant_metastases1=?, distant_metastases2=?, distant_metastases3=?, "
                        + "distant_metastases4=?, distant_metastases5=?, no_metastases=?, tgl_diagnosis=?, behavior=?, grade=?, stage=?, laterality=?, kesimpulan=?, tgl_admisi_mrs=?, "
                        + "cek_tgl_kontak_terakhir=?, tgl_kontak_terakhir=?, status=?, register=?, cek_tgl_abstrak=?, tgl_abstrak=?, nip_petugas_verif=?, "
                        + "tgl_verifikasi=?", 44, new String[]{
                            TnmAwal.getText(), TnmTengah.getText(), TnmKeluarga.getText(), TkdPosTetap.getText(), TalamatSementera.getText(),
                            TkdKec.getText(), TkdKab.getText(), TkdPos.getText(), cmbJenkel.getSelectedItem().toString(), cmbSuku.getSelectedItem().toString(),
                            cmbAgama.getSelectedItem().toString(), cmbSttsPernikahan.getSelectedItem().toString(), cmbPekerjaan.getSelectedItem().toString(), TkodeC.getText(),
                            TkodeM.getText(), cmbMost.getSelectedItem().toString(), cmbClinic.getSelectedItem().toString(), cmbTreat1.getSelectedItem().toString(),
                            cmbTreat2.getSelectedItem().toString(), cmbTreat3.getSelectedItem().toString(), cmbTreat4.getSelectedItem().toString(), cmbTreat5.getSelectedItem().toString(),
                            cmbDistan1.getSelectedItem().toString(), cmbDistan2.getSelectedItem().toString(), cmbDistan3.getSelectedItem().toString(), cmbDistan4.getSelectedItem().toString(),
                            cmbDistan5.getSelectedItem().toString(), cmbNoMetas.getSelectedItem().toString(), Valid.SetTgl(TtglDiagnosis.getSelectedItem() + ""),
                            cmbBehaviour.getSelectedItem().toString(), cmbGrade.getSelectedItem().toString(), cmbStage.getSelectedItem().toString(), cmbLater.getSelectedItem().toString(),
                            Tkesimpulan.getText(), Valid.SetTgl(TtglAdmisi.getSelectedItem() + ""), cekTglKon, Valid.SetTgl(TtglKontak.getSelectedItem() + ""),
                            cmbStatus.getSelectedItem().toString(), Tregister.getText(), cekTglAbs, Valid.SetTgl(TtglAbstrak.getSelectedItem() + ""), TnipPetugas.getText(),
                            Valid.SetTgl(TtglVerif.getSelectedItem() + ""),
                            tbRegister.getValueAt(tbRegister.getSelectedRow(), 0).toString()
                        }) == true) {
                    Sequel.queryu("update pasien set no_tlp='" + TnoTelp.getText() + "' where no_rkm_medis='" + TNoRM.getText() + "'");
                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Register Cancer (CanReg)", "Ganti");
                    TCari.setText(TNoRw.getText());
                    tampil();
                    emptTeks();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                tbRegister.requestFocus();
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

    private void tbRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRegisterMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbRegisterMouseClicked

    private void tbRegisterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRegisterKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbRegisterKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        initPetugas();
        akses.setform("RMRegisterCancer");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbRegister.getSelectedRow() > -1) {
            if (akses.getadmin() == true || tbRegister.getValueAt(tbRegister.getSelectedRow(), 49).toString().equals(akses.getkode())) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from register_cancer where no_rawat=?", 1, new String[]{
                        tbRegister.getValueAt(tbRegister.getSelectedRow(), 0).toString()
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
                JOptionPane.showMessageDialog(rootPane, "Maaf, data hanya bisa dihapus oleh " + tbRegister.getValueAt(tbRegister.getSelectedRow(), 7).toString() + " ....");
                tbRegister.requestFocus();
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbRegister.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbRegister.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("nik", Tnik.getText());
            param.put("norm", TNoRM.getText());            
            param.put("nmAwal", TnmAwal.getText());
            param.put("nmTengah", TnmTengah.getText());
            param.put("nmKeluarga", TnmKeluarga.getText());
            param.put("tmptLahir", TtmpLahir.getText());
            param.put("tglLahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d/%m/%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            param.put("umur", Sequel.cariIsi("select concat(rp.umurdaftar,' ',rp.sttsumur,'.') from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where rp.no_rawat='" + TNoRw.getText() + "'"));
            param.put("alamatTtp", TalamatTetap.getText());
            param.put("kabProvttp", Sequel.cariIsi("select concat('Kec./Kota ',kc.nm_kec,', ',kb.nm_kab) from pasien p inner join kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "inner join kabupaten kb on kb.kd_kab=p.kd_kab where p.no_rkm_medis='" + TNoRM.getText() + "'"));
            param.put("kodePosTtp", TkdPosTetap.getText());
            param.put("alamatSemen", TalamatSementera.getText());
            param.put("kabProvSemen", "Kec./Kota " + TnmKec.getText() + ", " + TnmKab.getText());
            param.put("kodePosSemen", TkdPos.getText());
            param.put("jenkel", cmbJenkel.getSelectedItem().toString());
            param.put("suku", cmbSuku.getSelectedItem().toString());
            param.put("agama", cmbAgama.getSelectedItem().toString());
            param.put("sttsNikah", cmbSttsPernikahan.getSelectedItem().toString());
            param.put("pekerjaan", cmbPekerjaan.getSelectedItem().toString());
            param.put("noTelp", TnoTelp.getText());
            param.put("topograpi", TkodeC.getText());
            param.put("morpologi", TkodeM.getText());
            param.put("mostValid", cmbMost.getSelectedItem().toString());
            param.put("clinical", cmbClinic.getSelectedItem().toString());
            param.put("tremen1", cmbTreat1.getSelectedItem().toString());
            param.put("tremen2", cmbTreat2.getSelectedItem().toString());
            param.put("tremen3", cmbTreat3.getSelectedItem().toString());
            param.put("tremen4", cmbTreat4.getSelectedItem().toString());
            param.put("tremen5", cmbTreat5.getSelectedItem().toString());
            param.put("tglDiagnosa", TtglDiagnosis.getSelectedItem().toString().replaceAll("-", "/"));
            param.put("behavior", cmbBehaviour.getSelectedItem().toString());
            param.put("distan1", cmbDistan1.getSelectedItem().toString());
            param.put("distan2", cmbDistan2.getSelectedItem().toString());
            param.put("distan3", cmbDistan3.getSelectedItem().toString());
            param.put("distan4", cmbDistan4.getSelectedItem().toString());
            param.put("distan5", cmbDistan5.getSelectedItem().toString());
            param.put("noMetas", cmbNoMetas.getSelectedItem().toString());
            param.put("grade", cmbGrade.getSelectedItem().toString());
            param.put("stage", cmbGrade.getSelectedItem().toString());
            param.put("later", cmbLater.getSelectedItem().toString());
            param.put("kesimpulan", Tkesimpulan.getText());
            param.put("tglAdmisi", TtglAdmisi.getSelectedItem().toString().replaceAll("-", "/"));

            List<Map<String, ?>> dataRiwayat = new ArrayList<>();
            for (int i = 0; i < tabMode1.getRowCount(); i++) {
                Map<String, Object> row = new HashMap<>();
                row.put("tglPeriksa", tabMode1.getValueAt(i, 1).toString());
                row.put("kodeRS", tabMode1.getValueAt(i, 2).toString());
                row.put("nmRS", tabMode1.getValueAt(i, 3).toString());
                row.put("kdUnit", tabMode1.getValueAt(i, 4).toString());
                row.put("nmUnit", tabMode1.getValueAt(i, 5).toString());
                row.put("noPA", tabMode1.getValueAt(i, 6).toString());
                dataRiwayat.add(row);
            }
            
            JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(dataRiwayat);
            
            if (chkTglKontak.isSelected() == true) {
                param.put("tglKontak", TtglKontak.getSelectedItem().toString().replaceAll("-", "/"));
            } else {
                param.put("tglKontak", "");
            }
            
            param.put("status", cmbStatus.getSelectedItem().toString());
            
            if (chkTglAbstrak.isSelected() == true) {
                param.put("tglAbstrak", TtglAbstrak.getSelectedItem().toString().replaceAll("-", "/"));
            } else {
                param.put("tglAbstrak", "");
            }
            
            param.put("register", Tregister.getText());
            param.put("verif", TnmPetugas.getText());
            param.put("tglVerif", TtglVerif.getSelectedItem().toString().replaceAll("-", "/"));
            
            Valid.MyReportDataSource("rptRegisterCancer.jasper", "report", "::[ Register Cancer (CanReg) ]::", dataSource, param);
            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel..!!!!");
            tbRegister.requestFocus();
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

    private void TkesimpulanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesimpulanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TtglAdmisi.requestFocus();
        }
    }//GEN-LAST:event_TkesimpulanKeyPressed

    private void TtglKontakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TtglKontakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TtglKontakActionPerformed

    private void TtglAbstrakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TtglAbstrakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TtglAbstrakActionPerformed

    private void TnmAwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmAwalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmTengah.requestFocus();
        }
    }//GEN-LAST:event_TnmAwalKeyPressed

    private void TnmTengahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmTengahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmKeluarga.requestFocus();
        }
    }//GEN-LAST:event_TnmTengahKeyPressed

    private void TnmKeluargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmKeluargaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TkdPosTetap.requestFocus();
        }
    }//GEN-LAST:event_TnmKeluargaKeyPressed

    private void TkdPosTetapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkdPosTetapKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TalamatSementera.requestFocus();
        }
    }//GEN-LAST:event_TkdPosTetapKeyPressed

    private void TalamatSementeraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalamatSementeraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnKecamatan.requestFocus();
        }
    }//GEN-LAST:event_TalamatSementeraKeyPressed

    private void BtnKecamatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKecamatanActionPerformed
        initKecamatan();
        akses.setform("RMRegisterCancer");
        kec.setSize(703, 384);
        kec.setLocationRelativeTo(internalFrame1);
        kec.setVisible(true);
        kec.TCari.requestFocus();
    }//GEN-LAST:event_BtnKecamatanActionPerformed

    private void BtnKabupatenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKabupatenActionPerformed
        initKabupaten();
        akses.setform("RMRegisterCancer");
        kab.setSize(703, 384);
        kab.setLocationRelativeTo(internalFrame1);
        kab.setVisible(true);
        kab.TCari.requestFocus();
    }//GEN-LAST:event_BtnKabupatenActionPerformed

    private void TkdPosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkdPosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJenkel.requestFocus();
        }
    }//GEN-LAST:event_TkdPosKeyPressed

    private void btnICDtopoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnICDtopoActionPerformed
        initICDtopography();
        akses.setform("RMRegisterCancer");
        icdOtopo.isCek();
        icdOtopo.emptTeks();
        icdOtopo.setSize(737, internalFrame1.getHeight() - 40);
        icdOtopo.setLocationRelativeTo(internalFrame1);
        icdOtopo.setAlwaysOnTop(false);
        icdOtopo.setVisible(true);
    }//GEN-LAST:event_btnICDtopoActionPerformed

    private void btnICDmorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnICDmorActionPerformed
        initICDmorphology();
        akses.setform("RMRegisterCancer");
        icdmor.isCek();
        icdmor.emptTeks();
        icdmor.setSize(983, internalFrame1.getHeight() - 40);
        icdmor.setLocationRelativeTo(internalFrame1);
        icdmor.setAlwaysOnTop(false);
        icdmor.setVisible(true);
    }//GEN-LAST:event_btnICDmorActionPerformed

    private void TnoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoTelpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnICDtopo.requestFocus();
        }
    }//GEN-LAST:event_TnoTelpKeyPressed

    private void TregisterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TregisterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTglAbstrak.requestFocus();
        }
    }//GEN-LAST:event_TregisterKeyPressed

    private void chkTglKontakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglKontakActionPerformed
        TtglKontak.setDate(new Date());
        if (chkTglKontak.isSelected() == true) {
            TtglKontak.setEnabled(true);
            TtglKontak.requestFocus();
        } else {
            TtglKontak.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglKontakActionPerformed

    private void chkTglAbstrakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglAbstrakActionPerformed
        TtglAbstrak.setDate(new Date());
        if (chkTglAbstrak.isSelected() == true) {
            TtglAbstrak.setEnabled(true);
            TtglAbstrak.requestFocus();
        } else {
            TtglAbstrak.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglAbstrakActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMRegisterCancer dialog = new RMRegisterCancer(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKabupaten;
    private widget.Button BtnKecamatan;
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
    private widget.ScrollPane Scroll2;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TalamatSementera;
    private widget.TextBox TalamatTetap;
    private widget.TextBox TkdKab;
    private widget.TextBox TkdKec;
    private widget.TextBox TkdPos;
    private widget.TextBox TkdPosTetap;
    private widget.TextArea Tkesimpulan;
    private widget.TextBox TkodeC;
    private widget.TextBox TkodeM;
    private widget.TextBox Tnik;
    private widget.TextBox TnipPetugas;
    private widget.TextBox TnmAwal;
    private widget.TextBox TnmDiagnosaKodeC;
    private widget.TextBox TnmDiagnosaKodeM;
    private widget.TextBox TnmKab;
    private widget.TextBox TnmKec;
    private widget.TextBox TnmKeluarga;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TnmTengah;
    private widget.TextBox TnoTelp;
    private widget.TextBox Tregister;
    private widget.TextBox TrgRawat;
    private widget.Tanggal TtglAbstrak;
    private widget.Tanggal TtglAdmisi;
    private widget.Tanggal TtglDiagnosis;
    private widget.Tanggal TtglKontak;
    private widget.TextBox TtglLahir;
    private widget.Tanggal TtglVerif;
    private widget.TextBox TtmpLahir;
    private widget.Button btnICDmor;
    private widget.Button btnICDtopo;
    private widget.CekBox chkTglAbstrak;
    private widget.CekBox chkTglKontak;
    private widget.ComboBox cmbAgama;
    private widget.ComboBox cmbBehaviour;
    private widget.ComboBox cmbClinic;
    private widget.ComboBox cmbDistan1;
    private widget.ComboBox cmbDistan2;
    private widget.ComboBox cmbDistan3;
    private widget.ComboBox cmbDistan4;
    private widget.ComboBox cmbDistan5;
    private widget.ComboBox cmbGrade;
    private widget.ComboBox cmbJenkel;
    private widget.ComboBox cmbLater;
    private widget.ComboBox cmbMost;
    private widget.ComboBox cmbNoMetas;
    private widget.ComboBox cmbPekerjaan;
    private widget.ComboBox cmbStage;
    private widget.ComboBox cmbStatus;
    private widget.ComboBox cmbSttsPernikahan;
    private widget.ComboBox cmbSuku;
    private widget.ComboBox cmbTreat1;
    private widget.ComboBox cmbTreat2;
    private widget.ComboBox cmbTreat3;
    private widget.ComboBox cmbTreat4;
    private widget.ComboBox cmbTreat5;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
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
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private widget.Label label20;
    private widget.Label label_pekerjaan;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane4;
    private widget.Table tbPeriksa;
    private widget.Table tbRegister;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select rc.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                    + "date_format(rc.tgl_admisi_mrs,'%d-%m-%Y') tglAdmisi, date_format(rc.tgl_verifikasi,'%d-%m-%Y') tglVerif, pg.nama nmPetugas from register_cancer rc "
                    + "inner join reg_periksa rp on rp.no_rawat=rc.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=rc.nip_petugas_verif WHERE "
                    + "rc.tgl_verifikasi between ? and ? and rc.no_rawat LIKE ? or "
                    + "rc.tgl_verifikasi between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "rc.tgl_verifikasi between ? and ? and p.nm_pasien LIKE ? or "
                    + "rc.tgl_verifikasi between ? and ? and pg.nama LIKE ? or "
                    + "rc.tgl_verifikasi between ? and ? and rc.ruang_rawat LIKE ? ORDER BY rc.waktu_simpan desc");
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
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglAdmisi"),
                        rs.getString("tglVerif"),
                        rs.getString("nmPetugas"),
                        rs.getString("nm_awal"),
                        rs.getString("nm_tengah"),
                        rs.getString("nm_keluarga"),
                        rs.getString("kode_pos_tetap"),
                        rs.getString("alamat_sementara"),
                        rs.getString("kec_semantara"),
                        rs.getString("kab_semantara"),
                        rs.getString("kode_pos_sementara"),
                        rs.getString("jenkel"),
                        rs.getString("suku"),
                        rs.getString("agama"),
                        rs.getString("status_pernikahan"),
                        rs.getString("pekerjaan"),
                        rs.getString("topography"),
                        rs.getString("morphology"),
                        rs.getString("most_valid"),
                        rs.getString("clinical_ext"),
                        rs.getString("treatment1"),
                        rs.getString("treatment2"),
                        rs.getString("treatment3"),
                        rs.getString("treatment4"),
                        rs.getString("treatment5"),
                        rs.getString("distant_metastases1"),
                        rs.getString("distant_metastases2"),
                        rs.getString("distant_metastases3"),
                        rs.getString("distant_metastases4"),
                        rs.getString("distant_metastases5"),
                        rs.getString("no_metastases"),                        
                        rs.getString("tgl_diagnosis"),
                        rs.getString("behavior"),
                        rs.getString("grade"),
                        rs.getString("stage"),
                        rs.getString("laterality"),
                        rs.getString("kesimpulan"),
                        rs.getString("tgl_admisi_mrs"),
                        rs.getString("cek_tgl_kontak_terakhir"),
                        rs.getString("tgl_kontak_terakhir"),
                        rs.getString("status"),
                        rs.getString("register"),
                        rs.getString("cek_tgl_abstrak"),
                        rs.getString("tgl_abstrak"),
                        rs.getString("nip_petugas_verif"),
                        rs.getString("tgl_verifikasi"),
                        rs.getString("status_rawat"),
                        rs.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampil() : " + e);
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
        Tnik.setText("");
        TtmpLahir.setText("");
        TtglLahir.setText("");
        TnmAwal.setText("");
        TnmTengah.setText("");
        TnmKeluarga.setText("");
        TalamatTetap.setText("");
        TkdPosTetap.setText("");
        TalamatSementera.setText("");
        TkdKec.setText("0");
        TnmKec.setText("-");
        TkdKab.setText("0");
        TnmKab.setText("-");
        TkdPos.setText("");
        cmbJenkel.setSelectedIndex(0);
        cmbSuku.setSelectedIndex(2);
        cmbAgama.setSelectedIndex(0);
        cmbSttsPernikahan.setSelectedIndex(0);
        cmbPekerjaan.setSelectedIndex(0);
        label_pekerjaan.setText("Pekerjaan : -");
        TnoTelp.setText("");
        TkodeC.setText("");
        TnmDiagnosaKodeC.setText("");
        TkodeM.setText("");
        TnmDiagnosaKodeM.setText("");
        cmbMost.setSelectedIndex(0);
        cmbBehaviour.setSelectedIndex(0);
        TtglDiagnosis.setDate(new Date());
        cmbClinic.setSelectedIndex(0);
        cmbTreat1.setSelectedIndex(0);
        cmbTreat2.setSelectedIndex(0);
        cmbTreat3.setSelectedIndex(0);
        cmbTreat4.setSelectedIndex(0);
        cmbTreat5.setSelectedIndex(0);
        cmbDistan1.setSelectedIndex(0);
        cmbDistan2.setSelectedIndex(0);
        cmbDistan3.setSelectedIndex(0);
        cmbDistan4.setSelectedIndex(0);
        cmbDistan5.setSelectedIndex(0);
        cmbNoMetas.setSelectedIndex(0);        
        cmbGrade.setSelectedIndex(0);
        cmbStage.setSelectedIndex(0);
        cmbLater.setSelectedIndex(0);
        Valid.tabelKosong(tabMode1);
        Tkesimpulan.setText("");
        TtglAdmisi.setDate(new Date());
        chkTglKontak.setSelected(false);
        TtglKontak.setEnabled(false);
        TtglKontak.setDate(new Date());        
        cmbStatus.setSelectedIndex(0);
        Tregister.setText("");
        chkTglAbstrak.setSelected(false);
        TtglAbstrak.setEnabled(false);
        TtglAbstrak.setDate(new Date());
        TnipPetugas.setText("-");
        TnmPetugas.setText("-");
        TtglVerif.setDate(new Date());
    }

    private void getData() {
        cekTglKon = "";
        cekTglAbs = "";
        
        if (tbRegister.getSelectedRow() != -1) {
            TNoRw.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 0).toString());
            TrgRawat.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 4).toString());
            isPasien();
            TnmAwal.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 8).toString());
            TnmTengah.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 9).toString());
            TnmKeluarga.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 10).toString());
            TkdPosTetap.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 11).toString());
            TalamatSementera.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 12).toString());
            TkdKec.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 13).toString());
            TnmKec.setText(Sequel.cariIsi("select nm_kec from kecamatan where kd_kec='" + TkdKec.getText() + "'"));
            TkdKab.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 14).toString());
            TnmKab.setText(Sequel.cariIsi("select nm_kab from kabupaten where kd_kab='" + TkdKab.getText() + "'"));
            TkdPos.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 15).toString());
            cmbJenkel.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 16).toString());
            cmbSuku.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 17).toString());
            cmbAgama.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 18).toString());
            cmbSttsPernikahan.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 19).toString());
            cmbPekerjaan.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 20).toString());
            TkodeC.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 21).toString());
            TnmDiagnosaKodeC.setText(Sequel.cariIsi("select nm_topography from master_icdo_topography where kd_topography='" + TkodeC.getText() + "'"));
            TkodeM.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 22).toString());
            TnmDiagnosaKodeM.setText(Sequel.cariIsi("select nm_morphology from master_icdo_morphology where kd_morphology='" + TkodeM.getText() + "'"));
            cmbMost.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 23).toString());
            cmbClinic.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 24).toString());
            cmbTreat1.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 25).toString());
            cmbTreat2.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 26).toString());
            cmbTreat3.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 27).toString());
            cmbTreat4.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 28).toString());
            cmbTreat5.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 29).toString());
            cmbDistan1.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 30).toString());
            cmbDistan2.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 31).toString());
            cmbDistan3.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 32).toString());
            cmbDistan4.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 33).toString());
            cmbDistan5.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 34).toString());
            cmbNoMetas.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 35).toString());            
            Valid.SetTgl(TtglDiagnosis, tbRegister.getValueAt(tbRegister.getSelectedRow(), 36).toString());
            cmbBehaviour.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 37).toString());
            cmbGrade.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 38).toString());
            cmbStage.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 39).toString());
            cmbLater.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 40).toString());
            Tkesimpulan.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 41).toString());
            Valid.SetTgl(TtglAdmisi, tbRegister.getValueAt(tbRegister.getSelectedRow(), 42).toString());
            cekTglKon = tbRegister.getValueAt(tbRegister.getSelectedRow(), 43).toString();
            Valid.SetTgl(TtglKontak, tbRegister.getValueAt(tbRegister.getSelectedRow(), 44).toString());
            cmbStatus.setSelectedItem(tbRegister.getValueAt(tbRegister.getSelectedRow(), 45).toString());
            Tregister.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 46).toString());
            cekTglAbs = tbRegister.getValueAt(tbRegister.getSelectedRow(), 47).toString();
            Valid.SetTgl(TtglAbstrak, tbRegister.getValueAt(tbRegister.getSelectedRow(), 48).toString());
            TnipPetugas.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 49).toString());
            TnmPetugas.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 7).toString());
            Valid.SetTgl(TtglVerif, tbRegister.getValueAt(tbRegister.getSelectedRow(), 50).toString());
            isRiwayat(TNoRM.getText());
            dataCek();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getpenyakit());
        BtnGanti.setEnabled(akses.getpenyakit());
        BtnHapus.setEnabled(akses.getpenyakit());
    }

    private void dataCek() {
        if (cekTglKon.equals("ya")) {
            chkTglKontak.setSelected(true);
            TtglKontak.setEnabled(true);
        } else {
            chkTglKontak.setSelected(false);
            TtglKontak.setEnabled(false);
        }

        if (cekTglAbs.equals("ya")) {
            chkTglAbstrak.setSelected(true);
            TtglAbstrak.setEnabled(true);
        } else {
            chkTglAbstrak.setSelected(false);
            TtglAbstrak.setEnabled(false);
        }
    }
    
    public void setData(String norw, String ruangan, String stts_rwt, String kodeIcd, String sttsPulang) {
        TNoRw.setText(norw);
        TrgRawat.setText(ruangan);
        sttsRawat = stts_rwt;
        TkodeC.setText(kodeIcd);
        TnmDiagnosaKodeC.setText(Sequel.cariIsi("select nm_topography from master_icdo_topography where kd_topography='" + TkodeC.getText() + "'"));
        TCari.setText(norw);
        isPasien();
        isRiwayat(TNoRM.getText());
        
        if (sttsPulang.equals("Meninggal >= 48 Jam") || sttsPulang.equals("Meninggal < 48 Jam")) {
            cmbStatus.setSelectedIndex(2);
        } else if (sttsPulang.equals("Sembuh/BLPL")) {
            cmbStatus.setSelectedIndex(1);
        } else {
            cmbStatus.setSelectedIndex(3);
        }
        
        if (akses.getadmin() == true) {
            TnipPetugas.setText("-");
            TnmPetugas.setText("-");
        } else {
            TnipPetugas.setText(akses.getkode());
            TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipPetugas.getText() + "'"));
        }
    }
    
    private void isPasien() {
        try {
            ps1 = koneksi.prepareStatement("select p.*, date_format(p.tgl_lahir,'%d-%m-%Y') tglLhr, concat(rp.umurdaftar,' ',rp.sttsumur,'.') umurDftr, "
                    +"concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', Kab. ',kb.nm_kab) alamatTetap, rp.tgl_registrasi from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join kelurahan kl on kl.kd_kel=p.kd_kel "
                    + "inner join kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "inner join kabupaten kb on kb.kd_kab=p.kd_kab where rp.no_rawat='" + TNoRw.getText() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    TNoRM.setText(rs1.getString("no_rkm_medis"));
                    TPasien.setText(rs1.getString("nm_pasien"));
                    Tnik.setText(rs1.getString("no_ktp"));
                    TtmpLahir.setText(rs1.getString("tmp_lahir"));
                    TtglLahir.setText(Valid.SetTglINDONESIA(rs1.getString("tgl_lahir")) + " (" + rs1.getString("umurDftr") + ")");
                    TnmKeluarga.setText(rs1.getString("namakeluarga"));
                    TalamatTetap.setText(rs1.getString("alamatTetap"));
                    TnoTelp.setText(rs1.getString("no_tlp"));
                    Valid.SetTgl(TtglAdmisi, rs1.getString("tgl_registrasi"));
                    label_pekerjaan.setText("Pekerjaan : " + rs1.getString("pekerjaan"));
                    
                    if (rs1.getString("jk").equals("L")) {
                        cmbJenkel.setSelectedIndex(1);
                    } else {
                        cmbJenkel.setSelectedIndex(2);
                    }
                    
                    if (rs1.getString("agama").equals("BUDHA")) {
                        cmbAgama.setSelectedIndex(6);
                    } else if (rs1.getString("agama").equals("HINDU")) {
                        cmbAgama.setSelectedIndex(5);
                    } else if (rs1.getString("agama").equals("ISLAM")) {
                        cmbAgama.setSelectedIndex(2);
                    } else if (rs1.getString("agama").equals("KATOLIK")) {
                        cmbAgama.setSelectedIndex(3);
                    } else if (rs1.getString("agama").equals("KONG HU CHU")) {
                        cmbAgama.setSelectedIndex(7);
                    } else if (rs1.getString("agama").equals("KRISTEN")) {
                        cmbAgama.setSelectedIndex(4);
                    } else if (rs1.getString("agama").equals("-")) {
                        cmbAgama.setSelectedIndex(0);
                    }
                    
                    if (rs1.getString("stts_nikah").equals("BELUM MENIKAH")) {
                        cmbSttsPernikahan.setSelectedIndex(3);
                    } else if (rs1.getString("stts_nikah").equals("MENIKAH")) {
                        cmbSttsPernikahan.setSelectedIndex(1);
                    } else if (rs1.getString("stts_nikah").equals("JANDA") || rs1.getString("stts_nikah").equals("DUDA")) {
                        cmbSttsPernikahan.setSelectedIndex(2);
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
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
    
    private void isRiwayat(String norm) {
        Valid.tabelKosong(tabMode1);
        try {
            ps2 = koneksi.prepareStatement("select distinct date_format(rp.tgl_registrasi,'%d/%m/%Y') tglPeriksa, '6303015' kodeRS, 'RSUD Ratu Zalecha' nmRS, "
                    + "if(hpa.no_rawat is null,'-',hpa.no_pa) noPA, rp.status_lanjut, rp.no_rawat, rp.kd_poli, pl.nm_poli from reg_periksa rp "
                    + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli inner join diagnosa_pasien dp on dp.no_rawat=rp.no_rawat "
                    + "left join hasil_patologi_anatomi hpa on hpa.no_rawat=rp.no_rawat where rp.no_rkm_medis='" + norm + "' AND dp.prioritas = '1' "
                    + "AND LEFT(dp.kd_penyakit, 3) IN ('C00','C01','C02','C03','C04','C05','C06','C07','C08','C09','C10','C11','C12','C13','C14', "
                    + "'C15','C16','C17','C18','C19','C20','C21','C22','C23','C24','C25','C26','C30','C31','C32','C33','C34','C37','C38','C39', "
                    + "'C40','C41','C43','C44','C45','C46','C47','C48','C49','C50','C51','C52','C53','C54','C55','C56','C57','C58', "
                    + "'C60','C61','C62','C63','C64','C65','C66','C67','C68','C69','C70','C71','C72','C73','C74','C75','C76','C77','C78','C79','C80', "
                    + "'C81','C82','C83','C84','C85','C86','C88','C90','C91','C92','C93','C94','C95','C96') order by rp.tgl_registrasi");
            try {
                rs2 = ps2.executeQuery();
                x = 1;
                while (rs2.next()) {
                    String kdUnit = "", nmUnit = "";
                    if (rs2.getString("status_lanjut").equals("Ralan")) {
                        kdUnit = rs2.getString("kd_poli");
                        nmUnit = rs2.getString("nm_poli");
                    } else {
                        kdUnit = Sequel.cariIsi("select b.kd_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                                + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + rs2.getString("no_rawat") + "' "
                                + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");
                        nmUnit = Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                                + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + rs2.getString("no_rawat") + "' "
                                + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");
                    }
                    
                    tabMode1.addRow(new String[]{
                        x + ".",
                        rs2.getString("tglPeriksa"),
                        rs2.getString("kodeRS"),
                        rs2.getString("nmRS"),
                        kdUnit,
                        nmUnit,
                        rs2.getString("noPA")
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void cekData() {
        if (chkTglKontak.isSelected() == true) {
            cekTglKon = "ya";
        } else {
            cekTglKon = "tidak";
        }

        if (chkTglAbstrak.isSelected() == true) {
            cekTglAbs = "ya";
        } else {
            cekTglAbs = "tidak";
        }
    }
    
    public void awalData() {
        tampil();
    }
    
    private void initKecamatan() {
        if (kec == null) {
            kec = new DlgKecamatan(null, false);

            kec.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {}
                @Override
                public void windowClosing(WindowEvent e) {}
                @Override
                public void windowClosed(WindowEvent e) {
                    if (akses.getform().equals("RMRegisterCancer")) {
                        if (kec.getTable().getSelectedRow() != -1) {
                            TnmKec.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 0).toString());
                            TkdKec.setText(Sequel.cariIsi("select if(count(-1)=0,'0',kd_kec) from kecamatan where nm_kec='" + TnmKec.getText() + "'"));
                            BtnKecamatan.requestFocus();
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
    }
    
    private void initKabupaten() {
        if (kab == null) {
            kab = new DlgKabupaten(null, false);

            kab.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {}
                @Override
                public void windowClosing(WindowEvent e) {}
                @Override
                public void windowClosed(WindowEvent e) {
                    if (akses.getform().equals("RMRegisterCancer")) {
                        if (kab.getTable().getSelectedRow() != -1) {
                            TnmKab.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 0).toString());
                            TkdKab.setText(Sequel.cariIsi("select if(count(-1)=0,'0',kd_kab) from kabupaten where nm_kab='" + TnmKab.getText() + "'"));
                            BtnKabupaten.requestFocus();
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
    }
    
    private void initICDtopography() {
        if (icdOtopo == null) {
            icdOtopo = new DlgICDOncologyTopography(null, false);

            icdOtopo.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    if (icdOtopo.getTable().getSelectedRow() != -1) {
                        TkodeC.setText(icdOtopo.getTable().getValueAt(icdOtopo.getTable().getSelectedRow(), 0).toString());
                        TnmDiagnosaKodeC.setText(icdOtopo.getTable().getValueAt(icdOtopo.getTable().getSelectedRow(), 1).toString());
                        btnICDtopo.requestFocus();
                    }                    
                }

                @Override
                public void windowIconified(WindowEvent e) {
                }

                @Override
                public void windowDeiconified(WindowEvent e) {
                }

                @Override
                public void windowActivated(WindowEvent e) {
                }

                @Override
                public void windowDeactivated(WindowEvent e) {
                }
            });

            icdOtopo.getTable().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (akses.getform().equals("RMRegisterCancer")) {
                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            icdOtopo.dispose();
                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
            
            icdOtopo.getTable().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (akses.getform().equals("RMRegisterCancer")) {
                        if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                            icdOtopo.dispose();
                        }
                    }
                }
            });
        }
    }
    
    private void initICDmorphology() {
        if (icdmor == null) {
            icdmor = new DlgICDOncologyMorphology(null, false);

            icdmor.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    if (icdmor.getTable().getSelectedRow() != -1) {
                        TkodeM.setText(icdmor.getTable().getValueAt(icdmor.getTable().getSelectedRow(), 0).toString());
                        TnmDiagnosaKodeM.setText(icdmor.getTable().getValueAt(icdmor.getTable().getSelectedRow(), 3).toString());
                        
                        if (icdmor.getTable().getValueAt(icdmor.getTable().getSelectedRow(), 2).toString().equals("0")) {
                            cmbBehaviour.setSelectedIndex(1);
                        } else if (icdmor.getTable().getValueAt(icdmor.getTable().getSelectedRow(), 2).toString().equals("1")) {
                            cmbBehaviour.setSelectedIndex(2);
                        } else if (icdmor.getTable().getValueAt(icdmor.getTable().getSelectedRow(), 2).toString().equals("2")) {
                            cmbBehaviour.setSelectedIndex(3);
                        } else if (icdmor.getTable().getValueAt(icdmor.getTable().getSelectedRow(), 2).toString().equals("3")) {
                            cmbBehaviour.setSelectedIndex(4);
                        } else if (icdmor.getTable().getValueAt(icdmor.getTable().getSelectedRow(), 2).toString().equals("6")
                                || icdmor.getTable().getValueAt(icdmor.getTable().getSelectedRow(), 2).toString().equals("9")) {
                            cmbBehaviour.setSelectedIndex(5);
                        } else {
                            cmbBehaviour.setSelectedIndex(0);
                        }
                        btnICDmor.requestFocus();
                    }                    
                }

                @Override
                public void windowIconified(WindowEvent e) {
                }

                @Override
                public void windowDeiconified(WindowEvent e) {
                }

                @Override
                public void windowActivated(WindowEvent e) {
                }

                @Override
                public void windowDeactivated(WindowEvent e) {
                }
            });

            icdmor.getTable().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (akses.getform().equals("RMRegisterCancer")) {
                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            icdmor.dispose();
                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
            
            icdmor.getTable().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (akses.getform().equals("RMRegisterCancer")) {
                        if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                            icdmor.dispose();
                        }
                    }
                }
            });
        }
    }
    
    private void initPetugas() {
        if (petugas == null) {
            petugas = new DlgCariPetugas(null, false);

            petugas.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    if (akses.getform().equals("RMRegisterCancer")) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            TnipPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnPetugas.requestFocus();
                        }
                    }
                }

                @Override
                public void windowIconified(WindowEvent e) {
                }

                @Override
                public void windowDeiconified(WindowEvent e) {
                }

                @Override
                public void windowActivated(WindowEvent e) {
                }

                @Override
                public void windowDeactivated(WindowEvent e) {
                }
            });
        }
    }
}
