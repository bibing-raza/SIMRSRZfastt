package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.net.InetAddress;
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
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import laporan.DlgICDOncologyMorphology;
import laporan.DlgICDOncologyTopography;
import laporan.DlgPenyakit;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgKabupaten;
import simrskhanza.DlgKecamatan;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class RMRegisterCancer extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabModeCppt, tabModeLis, tabModeHasilLab, tabModeRad;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, pscppt, psLab1, psLabA, psLabB, psLabC, psRad;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rscppt, rsLab1, rsLabA, rsLabB, rsLabC, rsRad, rsDok;
    private int i = 0, x = 0, urut = 0;
    private DlgKabupaten kab;
    private DlgKecamatan kec;
    private DlgICDOncologyTopography icdOtopo;
    private DlgICDOncologyMorphology icdmor;
    private DlgCariPetugas petugas;    
    private DlgCariDokter dokter;
    private String cekTglKon = "", cekTglAbs = "", sttsRawat = "", dataKonfirmasi = "", noLIS = "", cekLIS = "", ketLIS = "", tglLIS = "",
            jamLIS = "", drpengirim = "", tglPeriksaLIS = "", jamPeriksaLIS = "", kdItem = "", norawat = "", tglhasil = "", jamhasil = "",
            nmpemeriksaan = "", link = "";
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
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Admisi/MRS", "Tgl. Verif", "Nama Verifikator",
            "nm_awal", "nm_tengah", "nm_keluarga", "kode_pos_tetap", "alamat_sementara", "kec_semantara", "kab_semantara", "kode_pos_sementara", "jenkel", "suku",
            "agama", "status_pernikahan", "pekerjaan", "topography", "morphology", "most_valid", "clinical_ext", "treatment1", "treatment2", "treatment3", "treatment4",
            "treatment5", "distant_metastases1", "distant_metastases2", "distant_metastases3", "distant_metastases4", "distant_metastases5", "no_metastases",
            "tgl_diagnosis", "behavior", "grade", "stage", "laterality", "kesimpulan", "tgl_admisi_mrs", "cek_tgl_kontak_terakhir", "tgl_kontak_terakhir", "status",
            "nip_petugas_register", "cek_tgl_abstrak", "tgl_abstrak", "nip_petugas_verif", "tgl_verifikasi", "status_rawat", "waktu_simpan", "petugas_register"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRegister.setModel(tabMode);
        tbRegister.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRegister.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 54; i++) {
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
            } else if (i == 53) {
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
        
        tabModeCppt = new DefaultTableModel(null, new String[]{
            "Tgl. CPPT", "Jam CPPT", "Jenis Bagian", "DPJP Konsulen", "Jenis PPA",
            "Nama PPA", "Shift", "hasil", "instruksi", "no_rawat", "tgl_cppt", "jam_cppt"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbCPPT.setModel(tabModeCppt);
        tbCPPT.setPreferredScrollableViewportSize(new Dimension(500, 500));
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
                column.setPreferredWidth(300);
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
        
        tabModeLis = new DefaultTableModel(null, new String[]{
            "Pasien", "No. LIS", "Keterangan Hasil Lab.", "cekok", "norawat", "Tgl. Periksa",
            "Jam Periksa", "Dokter Pengirim", "Jns. Rawat"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbLIS.setModel(tabModeLis);
        tbLIS.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbLIS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 9; i++) {
            TableColumn column = tbLIS.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(260);
            } else if (i == 8) {
                column.setPreferredWidth(80);
            }
        }
        tbLIS.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeHasilLab = new DefaultTableModel(null, new Object[]{
            "Cek", "Jenis Pemeriksaan/Item", "Nilai Hasil", "Satuan", "Flag Kode", "Nilai Rujukan", "Waktu Selesai", "Metode Pemeriksaan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbHasil.setModel(tabModeHasilLab);
        tbHasil.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbHasil.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            TableColumn column = tbHasil.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(90);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(160);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbHasil.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. PA", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Dokter Pengirim", "Rg. Rawat/Poli/Inst.",
            "Tgl. Periksa", "Tgl. Hasil", "lokasi", "makroskopik", "mikroskopik", "kesimpulan", "anjuran",
            "kd_gambar", "nip_perujuk", "tgl_periksa", "tgl_lahir", "tgl_hasil", "waktu_simpan",
            "italic_makroskopik", "italic_mikroskopik", "italic_kesimpulan", "italic_anjuran"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPA.setModel(tabMode2);
        tbPA.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPA.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 25; i++) {
            TableColumn column = tbPA.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(115);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(75);
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
            }
        }
        tbPA.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPA.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPA.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPA.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbPA.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbPA.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        tbPA.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        
        tabModeRad = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien", "Jns. Rawat", "Pemeriksaan Rad.", "Dokter Perujuk", "Tgl. Periksa", "Jam Periksa",
            "no_rawat", "kd_jenis_prw", "tgl_periksa"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRadiologi.setModel(tabModeRad);
        tbRadiologi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRadiologi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 10; i++) {
            TableColumn column = tbRadiologi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(65);
            } else if (i == 1) {
                column.setPreferredWidth(260);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(260);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRadiologi.setDefaultRenderer(Object.class, new WarnaTable());

        TnmAwal.setDocument(new batasInput((int) 50).getKata(TnmAwal));
        TnmTengah.setDocument(new batasInput((int) 50).getKata(TnmTengah));
        TnmKeluarga.setDocument(new batasInput((int) 100).getKata(TnmKeluarga));
        TkdPosTetap.setDocument(new batasInput((int) 10).getKata(TkdPosTetap));
        TalamatSementera.setDocument(new batasInput((int) 200).getKata(TalamatSementera));
        TkdPos.setDocument(new batasInput((int) 10).getKata(TkdPos));
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
        
        try {
            psLabA = koneksi.prepareStatement("SELECT lhp.kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "GROUP BY lhp.kategori_pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut");
            
            psLabB = koneksi.prepareStatement("SELECT lhp.sub_kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "and lhp.kategori_pemeriksaan_nama=? GROUP BY lhp.sub_kategori_pemeriksaan_nama "
                    + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.sub_kategori_pemeriksaan_nama desc,lhp.pemeriksaan_no_urut");
            
            psLabC = koneksi.prepareStatement("SELECT lhp.pemeriksaan_nama, lhp.nilai_hasil, lhp.satuan, lhp.flag_kode, "
                    + "lhp.nilai_rujukan, DATE_FORMAT(lhdp.waktu_insert,'%d/%m/%Y - %H:%i:%s') wkt_selesai, lhp.metode "
                    + "FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp ON lhp.no_lab = lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp ON lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "and lhp.sub_kategori_pemeriksaan_nama=? and lhp.kategori_pemeriksaan_nama=? GROUP BY lhp.pemeriksaan_nama "
                    + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut");
        } catch (Exception e) {
            System.out.println(e);
        }
        
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(".isi td{border-right: 1px solid #edf2e8;font: 10px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}");
        Document doc = kit.createDefaultDocument();
        
        LoadHTML1.setEditable(true);        
        LoadHTML1.setEditorKit(kit);        
        LoadHTML1.setDocument(doc);
        LoadHTML1.setEditable(false);        
        LoadHTML1.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        LoadHTML2.setEditable(true);
        LoadHTML2.setEditorKit(kit);        
        LoadHTML2.setDocument(doc);
        LoadHTML2.setEditable(false);        
        LoadHTML2.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        try {
            link = koneksiDB.HOSTport();
        } catch (Exception e) {
            System.out.println("E : " + e);
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
        TabRegister = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
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
        TnipVerif = new widget.TextBox();
        TnmVerif = new widget.TextBox();
        BtnVerifikasi = new widget.Button();
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
        TnipRegister = new widget.TextBox();
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
        TnmRegister = new widget.TextBox();
        BtnRegister = new widget.Button();
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
        internalFrame3 = new widget.InternalFrame();
        panelGlass9 = new widget.panelisi();
        Scroll4 = new widget.ScrollPane();
        tbCPPT = new widget.Table();
        panelGlass14 = new widget.panelisi();
        scrollPane5 = new widget.ScrollPane();
        Thasil = new widget.TextArea();
        scrollPane6 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();
        panelGlass15 = new widget.panelisi();
        BtnKeluar1 = new widget.Button();
        internalFrame4 = new widget.InternalFrame();
        PanelInput = new javax.swing.JPanel();
        Scroll12 = new widget.ScrollPane();
        tbLIS = new widget.Table();
        panelGlass16 = new widget.panelisi();
        jLabel53 = new widget.Label();
        TCari3 = new widget.TextBox();
        jLabel54 = new widget.Label();
        cmbHlm = new widget.ComboBox();
        BtnCari4 = new widget.Button();
        Scroll3 = new widget.ScrollPane();
        tbHasil = new widget.Table();
        panelGlass18 = new widget.panelisi();
        BtnKeluar5 = new widget.Button();
        internalFrame5 = new widget.InternalFrame();
        panelGlass34 = new widget.panelisi();
        BtnKeluar9 = new widget.Button();
        panelGlass35 = new widget.panelisi();
        FormInput3 = new widget.PanelBiasa();
        Scroll24 = new widget.ScrollPane();
        tbPA = new widget.Table();
        panelGlass33 = new widget.panelisi();
        jLabel75 = new widget.Label();
        TCari4 = new widget.TextBox();
        jLabel82 = new widget.Label();
        cmbHlm2 = new widget.ComboBox();
        BtnCari9 = new widget.Button();
        Scroll22 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        internalFrame6 = new widget.InternalFrame();
        FormInput2 = new widget.PanelBiasa();
        Scroll14 = new widget.ScrollPane();
        tbRadiologi = new widget.Table();
        panelGlass17 = new widget.panelisi();
        jLabel55 = new widget.Label();
        TCari5 = new widget.TextBox();
        jLabel56 = new widget.Label();
        cmbHlm1 = new widget.ComboBox();
        BtnCari5 = new widget.Button();
        Scroll11 = new widget.ScrollPane();
        HasilPeriksa = new widget.TextArea();
        panelGlass19 = new widget.panelisi();
        BtnKeluar6 = new widget.Button();
        internalFrame31 = new widget.InternalFrame();
        panelGlass30 = new widget.panelisi();
        jLabel62 = new widget.Label();
        TNoRw1 = new widget.TextBox();
        TNoRm1 = new widget.TextBox();
        TPasien1 = new widget.TextBox();
        Scroll15 = new widget.ScrollPane();
        LoadHTML2 = new widget.editorpane();
        panelGlass20 = new widget.panelisi();
        ChkDokumen = new widget.CekBox();
        BtnCari6 = new widget.Button();
        BtnKeluar7 = new widget.Button();
        panelGlass21 = new widget.panelisi();
        panelGlass22 = new widget.panelisi();
        PanelWallpublic = new usu.widget.glass.PanelGlass();
        panelGlass23 = new widget.panelisi();
        PanelWallwifi = new usu.widget.glass.PanelGlass();

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

        TabRegister.setBackground(new java.awt.Color(254, 255, 254));
        TabRegister.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRegister.setName("TabRegister"); // NOI18N
        TabRegister.setPreferredSize(new java.awt.Dimension(0, 2000));
        TabRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRegisterMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(900, 900));
        internalFrame2.setLayout(new java.awt.BorderLayout());

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass13.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(600, 402));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(760, 976));
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

        TnipVerif.setEditable(false);
        TnipVerif.setForeground(new java.awt.Color(0, 0, 0));
        TnipVerif.setName("TnipVerif"); // NOI18N
        TnipVerif.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipVerif);
        TnipVerif.setBounds(145, 909, 150, 23);

        TnmVerif.setEditable(false);
        TnmVerif.setForeground(new java.awt.Color(0, 0, 0));
        TnmVerif.setName("TnmVerif"); // NOI18N
        TnmVerif.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmVerif);
        TnmVerif.setBounds(300, 909, 360, 23);

        BtnVerifikasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnVerifikasi.setMnemonic('2');
        BtnVerifikasi.setToolTipText("Alt+2");
        BtnVerifikasi.setName("BtnVerifikasi"); // NOI18N
        BtnVerifikasi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnVerifikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVerifikasiActionPerformed(evt);
            }
        });
        FormInput.add(BtnVerifikasi);
        BtnVerifikasi.setBounds(664, 909, 28, 23);

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
        TtglDiagnosis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-07-2026" }));
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
        TtglAdmisi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-07-2026" }));
        TtglAdmisi.setDisplayFormat("dd-MM-yyyy");
        TtglAdmisi.setName("TtglAdmisi"); // NOI18N
        TtglAdmisi.setOpaque(false);
        TtglAdmisi.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglAdmisi);
        TtglAdmisi.setBounds(145, 853, 90, 23);

        TtglKontak.setEditable(false);
        TtglKontak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-07-2026" }));
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
        jLabel81.setText("Petugas Register :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 881, 140, 23);

        TnipRegister.setEditable(false);
        TnipRegister.setForeground(new java.awt.Color(0, 0, 0));
        TnipRegister.setName("TnipRegister"); // NOI18N
        FormInput.add(TnipRegister);
        TnipRegister.setBounds(145, 881, 150, 23);

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
        chkTglAbstrak.setBounds(235, 937, 100, 23);

        TtglAbstrak.setEditable(false);
        TtglAbstrak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-07-2026" }));
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
        TtglAbstrak.setBounds(340, 937, 90, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Tgl. Verifikasi :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(0, 937, 140, 23);

        TtglVerif.setEditable(false);
        TtglVerif.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-07-2026" }));
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

        TnmRegister.setEditable(false);
        TnmRegister.setForeground(new java.awt.Color(0, 0, 0));
        TnmRegister.setName("TnmRegister"); // NOI18N
        TnmRegister.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmRegister);
        TnmRegister.setBounds(300, 881, 360, 23);

        BtnRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnRegister.setMnemonic('2');
        BtnRegister.setToolTipText("Alt+2");
        BtnRegister.setName("BtnRegister"); // NOI18N
        BtnRegister.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegisterActionPerformed(evt);
            }
        });
        FormInput.add(BtnRegister);
        BtnRegister.setBounds(664, 881, 28, 23);

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

        internalFrame2.add(panelGlass13, java.awt.BorderLayout.CENTER);

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

        internalFrame2.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        TabRegister.addTab("Input Register", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 47));
        panelGlass9.setLayout(new java.awt.GridLayout(1, 2));

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbCPPT.setToolTipText("Silahkan klik untuk memilih data yang dibaca cpptnya");
        tbCPPT.setName("tbCPPT"); // NOI18N
        tbCPPT.getTableHeader().setReorderingAllowed(false);
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

        panelGlass9.add(Scroll4);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass14.setLayout(new java.awt.BorderLayout());

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Hasil Pemeriksaan, Analisa, Rencana, Penatalaksanaan Pasien ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N
        scrollPane5.setPreferredSize(new java.awt.Dimension(212, 450));

        Thasil.setEditable(false);
        Thasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Thasil.setColumns(20);
        Thasil.setRows(5);
        Thasil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Thasil.setName("Thasil"); // NOI18N
        Thasil.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane5.setViewportView(Thasil);

        panelGlass14.add(scrollPane5, java.awt.BorderLayout.PAGE_START);

        scrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Instruksi Tenaga Kesehatan Termasuk Pasca Bedah/Prosedur ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        scrollPane6.setName("scrollPane6"); // NOI18N
        scrollPane6.setPreferredSize(new java.awt.Dimension(212, 150));

        Tinstruksi.setEditable(false);
        Tinstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinstruksi.setColumns(20);
        Tinstruksi.setRows(5);
        Tinstruksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane6.setViewportView(Tinstruksi);

        panelGlass14.add(scrollPane6, java.awt.BorderLayout.CENTER);

        panelGlass9.add(panelGlass14);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.CENTER);

        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(55, 47));
        panelGlass15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        panelGlass15.add(BtnKeluar1);

        internalFrame3.add(panelGlass15, java.awt.BorderLayout.PAGE_END);

        TabRegister.addTab("Rekam Medis CPPT", internalFrame3);

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 280));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Nomor Pemeriksaan Lab. :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll12.setName("Scroll12"); // NOI18N
        Scroll12.setOpaque(true);

        tbLIS.setAutoCreateRowSorter(true);
        tbLIS.setToolTipText("Silahkan klik salah satu datanya untuk melihat hasil pemeriksaannya");
        tbLIS.setName("tbLIS"); // NOI18N
        tbLIS.getTableHeader().setReorderingAllowed(false);
        tbLIS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLISMouseClicked(evt);
            }
        });
        tbLIS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbLISKeyPressed(evt);
            }
        });
        Scroll12.setViewportView(tbLIS);

        PanelInput.add(Scroll12, java.awt.BorderLayout.CENTER);

        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 7));

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Key Word :");
        jLabel53.setName("jLabel53"); // NOI18N
        jLabel53.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass16.add(jLabel53);

        TCari3.setForeground(new java.awt.Color(0, 0, 0));
        TCari3.setName("TCari3"); // NOI18N
        TCari3.setPreferredSize(new java.awt.Dimension(190, 23));
        TCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari3KeyPressed(evt);
            }
        });
        panelGlass16.add(TCari3);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Limit Data :");
        jLabel54.setName("jLabel54"); // NOI18N
        jLabel54.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass16.add(jLabel54);

        cmbHlm.setForeground(new java.awt.Color(0, 0, 0));
        cmbHlm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" }));
        cmbHlm.setName("cmbHlm"); // NOI18N
        cmbHlm.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass16.add(cmbHlm);

        BtnCari4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari4.setMnemonic('6');
        BtnCari4.setText("Tampilkan Data");
        BtnCari4.setToolTipText("Alt+6");
        BtnCari4.setName("BtnCari4"); // NOI18N
        BtnCari4.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari4ActionPerformed(evt);
            }
        });
        BtnCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari4KeyPressed(evt);
            }
        });
        panelGlass16.add(BtnCari4);

        PanelInput.add(panelGlass16, java.awt.BorderLayout.PAGE_END);

        internalFrame4.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Hasil Pemeriksaan Laboratorium :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbHasil.setName("tbHasil"); // NOI18N
        tbHasil.getTableHeader().setReorderingAllowed(false);
        Scroll3.setViewportView(tbHasil);

        internalFrame4.add(Scroll3, java.awt.BorderLayout.CENTER);

        panelGlass18.setName("panelGlass18"); // NOI18N
        panelGlass18.setPreferredSize(new java.awt.Dimension(55, 47));
        panelGlass18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        BtnKeluar5.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar5.setMnemonic('K');
        BtnKeluar5.setText("Keluar");
        BtnKeluar5.setToolTipText("Alt+K");
        BtnKeluar5.setName("BtnKeluar5"); // NOI18N
        BtnKeluar5.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar5ActionPerformed(evt);
            }
        });
        panelGlass18.add(BtnKeluar5);

        internalFrame4.add(panelGlass18, java.awt.BorderLayout.PAGE_END);

        TabRegister.addTab("Pemeriksaan LAB.", internalFrame4);

        internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass34.setName("panelGlass34"); // NOI18N
        panelGlass34.setPreferredSize(new java.awt.Dimension(55, 47));
        panelGlass34.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        BtnKeluar9.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar9.setMnemonic('K');
        BtnKeluar9.setText("Keluar");
        BtnKeluar9.setToolTipText("Alt+K");
        BtnKeluar9.setName("BtnKeluar9"); // NOI18N
        BtnKeluar9.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar9ActionPerformed(evt);
            }
        });
        panelGlass34.add(BtnKeluar9);

        internalFrame5.add(panelGlass34, java.awt.BorderLayout.PAGE_END);

        panelGlass35.setName("panelGlass35"); // NOI18N
        panelGlass35.setPreferredSize(new java.awt.Dimension(55, 47));
        panelGlass35.setLayout(new java.awt.BorderLayout());

        FormInput3.setName("FormInput3"); // NOI18N
        FormInput3.setPreferredSize(new java.awt.Dimension(190, 250));
        FormInput3.setLayout(new java.awt.BorderLayout());

        Scroll24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Pemeriksaan Patologi Anatomi :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll24.setName("Scroll24"); // NOI18N
        Scroll24.setOpaque(true);

        tbPA.setAutoCreateRowSorter(true);
        tbPA.setToolTipText("Silahkan klik salah satu datanya untuk membaca hasil pemeriksaan patologi anatomi");
        tbPA.setName("tbPA"); // NOI18N
        tbPA.getTableHeader().setReorderingAllowed(false);
        tbPA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPAMouseClicked(evt);
            }
        });
        tbPA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPAKeyPressed(evt);
            }
        });
        Scroll24.setViewportView(tbPA);

        FormInput3.add(Scroll24, java.awt.BorderLayout.CENTER);

        panelGlass33.setName("panelGlass33"); // NOI18N
        panelGlass33.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass33.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 7));

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Key Word :");
        jLabel75.setName("jLabel75"); // NOI18N
        jLabel75.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass33.add(jLabel75);

        TCari4.setForeground(new java.awt.Color(0, 0, 0));
        TCari4.setName("TCari4"); // NOI18N
        TCari4.setPreferredSize(new java.awt.Dimension(190, 23));
        TCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari4KeyPressed(evt);
            }
        });
        panelGlass33.add(TCari4);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Limit Data :");
        jLabel82.setName("jLabel82"); // NOI18N
        jLabel82.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass33.add(jLabel82);

        cmbHlm2.setForeground(new java.awt.Color(0, 0, 0));
        cmbHlm2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" }));
        cmbHlm2.setName("cmbHlm2"); // NOI18N
        cmbHlm2.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass33.add(cmbHlm2);

        BtnCari9.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari9.setMnemonic('6');
        BtnCari9.setText("Tampilkan Data");
        BtnCari9.setToolTipText("Alt+6");
        BtnCari9.setName("BtnCari9"); // NOI18N
        BtnCari9.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari9ActionPerformed(evt);
            }
        });
        BtnCari9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari9KeyPressed(evt);
            }
        });
        panelGlass33.add(BtnCari9);

        FormInput3.add(panelGlass33, java.awt.BorderLayout.PAGE_END);

        panelGlass35.add(FormInput3, java.awt.BorderLayout.PAGE_START);

        Scroll22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Preview Hasil Pemeriksaan :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll22.setName("Scroll22"); // NOI18N
        Scroll22.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll22.setViewportView(LoadHTML1);

        panelGlass35.add(Scroll22, java.awt.BorderLayout.CENTER);

        internalFrame5.add(panelGlass35, java.awt.BorderLayout.CENTER);

        TabRegister.addTab("Pemeriksaan Lab. (Patologi Anatomi)", internalFrame5);

        internalFrame6.setBorder(null);
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout());

        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(190, 250));
        FormInput2.setLayout(new java.awt.BorderLayout());

        Scroll14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Pemeriksaan Radiologi :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll14.setName("Scroll14"); // NOI18N
        Scroll14.setOpaque(true);

        tbRadiologi.setAutoCreateRowSorter(true);
        tbRadiologi.setToolTipText("Silahkan klik salah satu datanya untuk membaca hasil ekspertisenya");
        tbRadiologi.setName("tbRadiologi"); // NOI18N
        tbRadiologi.getTableHeader().setReorderingAllowed(false);
        tbRadiologi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRadiologiMouseClicked(evt);
            }
        });
        tbRadiologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRadiologiKeyPressed(evt);
            }
        });
        Scroll14.setViewportView(tbRadiologi);

        FormInput2.add(Scroll14, java.awt.BorderLayout.CENTER);

        panelGlass17.setName("panelGlass17"); // NOI18N
        panelGlass17.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 7));

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Key Word :");
        jLabel55.setName("jLabel55"); // NOI18N
        jLabel55.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(jLabel55);

        TCari5.setForeground(new java.awt.Color(0, 0, 0));
        TCari5.setName("TCari5"); // NOI18N
        TCari5.setPreferredSize(new java.awt.Dimension(190, 23));
        TCari5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari5KeyPressed(evt);
            }
        });
        panelGlass17.add(TCari5);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Limit Data :");
        jLabel56.setName("jLabel56"); // NOI18N
        jLabel56.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass17.add(jLabel56);

        cmbHlm1.setForeground(new java.awt.Color(0, 0, 0));
        cmbHlm1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" }));
        cmbHlm1.setName("cmbHlm1"); // NOI18N
        cmbHlm1.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass17.add(cmbHlm1);

        BtnCari5.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari5.setMnemonic('6');
        BtnCari5.setText("Tampilkan Data");
        BtnCari5.setToolTipText("Alt+6");
        BtnCari5.setName("BtnCari5"); // NOI18N
        BtnCari5.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari5ActionPerformed(evt);
            }
        });
        BtnCari5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari5KeyPressed(evt);
            }
        });
        panelGlass17.add(BtnCari5);

        FormInput2.add(panelGlass17, java.awt.BorderLayout.PAGE_END);

        internalFrame6.add(FormInput2, java.awt.BorderLayout.PAGE_START);

        Scroll11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Hasil Expertise Radiologi :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);

        HasilPeriksa.setEditable(false);
        HasilPeriksa.setColumns(20);
        HasilPeriksa.setRows(5);
        HasilPeriksa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        HasilPeriksa.setName("HasilPeriksa"); // NOI18N
        Scroll11.setViewportView(HasilPeriksa);

        internalFrame6.add(Scroll11, java.awt.BorderLayout.CENTER);

        panelGlass19.setName("panelGlass19"); // NOI18N
        panelGlass19.setPreferredSize(new java.awt.Dimension(55, 47));
        panelGlass19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        BtnKeluar6.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar6.setMnemonic('K');
        BtnKeluar6.setText("Keluar");
        BtnKeluar6.setToolTipText("Alt+K");
        BtnKeluar6.setName("BtnKeluar6"); // NOI18N
        BtnKeluar6.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar6ActionPerformed(evt);
            }
        });
        panelGlass19.add(BtnKeluar6);

        internalFrame6.add(panelGlass19, java.awt.BorderLayout.PAGE_END);

        TabRegister.addTab("Pemeriksaan Radiologi (Expertise)", internalFrame6);

        internalFrame31.setBorder(null);
        internalFrame31.setName("internalFrame31"); // NOI18N
        internalFrame31.setLayout(new java.awt.BorderLayout());

        panelGlass30.setName("panelGlass30"); // NOI18N
        panelGlass30.setPreferredSize(new java.awt.Dimension(44, 45));
        panelGlass30.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Pasien :");
        jLabel62.setName("jLabel62"); // NOI18N
        jLabel62.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass30.add(jLabel62);

        TNoRw1.setEditable(false);
        TNoRw1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw1.setName("TNoRw1"); // NOI18N
        TNoRw1.setPreferredSize(new java.awt.Dimension(122, 24));
        panelGlass30.add(TNoRw1);

        TNoRm1.setEditable(false);
        TNoRm1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm1.setName("TNoRm1"); // NOI18N
        TNoRm1.setPreferredSize(new java.awt.Dimension(70, 24));
        panelGlass30.add(TNoRm1);

        TPasien1.setEditable(false);
        TPasien1.setForeground(new java.awt.Color(0, 0, 0));
        TPasien1.setName("TPasien1"); // NOI18N
        TPasien1.setPreferredSize(new java.awt.Dimension(407, 24));
        panelGlass30.add(TPasien1);

        internalFrame31.add(panelGlass30, java.awt.BorderLayout.PAGE_START);

        Scroll15.setName("Scroll15"); // NOI18N
        Scroll15.setOpaque(true);

        LoadHTML2.setBorder(null);
        LoadHTML2.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML2.setName("LoadHTML2"); // NOI18N
        Scroll15.setViewportView(LoadHTML2);

        internalFrame31.add(Scroll15, java.awt.BorderLayout.CENTER);

        panelGlass20.setToolTipText("Klik kanan disini untuk menambahkan jenis dokumen penunjang baru");
        panelGlass20.setName("panelGlass20"); // NOI18N
        panelGlass20.setPreferredSize(new java.awt.Dimension(44, 47));
        panelGlass20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        ChkDokumen.setBackground(new java.awt.Color(255, 255, 250));
        ChkDokumen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDokumen.setForeground(new java.awt.Color(0, 0, 0));
        ChkDokumen.setText("Semua Dokumen Penunjang Medis");
        ChkDokumen.setBorderPainted(true);
        ChkDokumen.setBorderPaintedFlat(true);
        ChkDokumen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDokumen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDokumen.setName("ChkDokumen"); // NOI18N
        ChkDokumen.setOpaque(false);
        ChkDokumen.setPreferredSize(new java.awt.Dimension(210, 30));
        ChkDokumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkDokumenActionPerformed(evt);
            }
        });
        panelGlass20.add(ChkDokumen);

        BtnCari6.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari6.setMnemonic('1');
        BtnCari6.setText("Tampilkan Data");
        BtnCari6.setToolTipText("Alt+1");
        BtnCari6.setName("BtnCari6"); // NOI18N
        BtnCari6.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCari6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari6ActionPerformed(evt);
            }
        });
        BtnCari6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari6KeyPressed(evt);
            }
        });
        panelGlass20.add(BtnCari6);

        BtnKeluar7.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar7.setMnemonic('K');
        BtnKeluar7.setText("Keluar");
        BtnKeluar7.setToolTipText("Alt+K");
        BtnKeluar7.setName("BtnKeluar7"); // NOI18N
        BtnKeluar7.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar7ActionPerformed(evt);
            }
        });
        panelGlass20.add(BtnKeluar7);

        internalFrame31.add(panelGlass20, java.awt.BorderLayout.PAGE_END);

        panelGlass21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Scan QRCode Untuk Upload File ]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panelGlass21.setName("panelGlass21"); // NOI18N
        panelGlass21.setPreferredSize(new java.awt.Dimension(210, 422));
        panelGlass21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        panelGlass22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51), 2), "[ Koneksi Paket Data ]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 0, 51))); // NOI18N
        panelGlass22.setToolTipText("");
        panelGlass22.setName("panelGlass22"); // NOI18N
        panelGlass22.setPreferredSize(new java.awt.Dimension(160, 160));
        panelGlass22.setLayout(new java.awt.BorderLayout());

        PanelWallpublic.setBackground(new java.awt.Color(29, 29, 29));
        PanelWallpublic.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/qrcode_upload_dok_jangmed.png"))); // NOI18N
        PanelWallpublic.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWallpublic.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWallpublic.setRound(false);
        PanelWallpublic.setToolTipText("");
        PanelWallpublic.setWarna(new java.awt.Color(110, 110, 110));
        PanelWallpublic.setLayout(null);
        panelGlass22.add(PanelWallpublic, java.awt.BorderLayout.CENTER);

        panelGlass21.add(panelGlass22);

        panelGlass23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2), "[ Koneksi Wifi RS ]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 255))); // NOI18N
        panelGlass23.setToolTipText("");
        panelGlass23.setName("panelGlass23"); // NOI18N
        panelGlass23.setPreferredSize(new java.awt.Dimension(160, 160));
        panelGlass23.setLayout(new java.awt.BorderLayout());

        PanelWallwifi.setBackground(new java.awt.Color(29, 29, 29));
        PanelWallwifi.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/qrcode_upload_dok_jangmed_wifi.png"))); // NOI18N
        PanelWallwifi.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWallwifi.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWallwifi.setRound(false);
        PanelWallwifi.setToolTipText("");
        PanelWallwifi.setWarna(new java.awt.Color(110, 110, 110));
        PanelWallwifi.setLayout(null);
        panelGlass23.add(PanelWallwifi, java.awt.BorderLayout.CENTER);

        panelGlass21.add(panelGlass23);

        internalFrame31.add(panelGlass21, java.awt.BorderLayout.EAST);

        TabRegister.addTab("Dokumen Penunjang Medis", internalFrame31);

        internalFrame1.add(TabRegister, java.awt.BorderLayout.CENTER);

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
                cmbStatus.getSelectedItem().toString(), TnipRegister.getText(), cekTglAbs, Valid.SetTgl(TtglAbstrak.getSelectedItem() + ""), TnipVerif.getText(),
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
                        + "cek_tgl_kontak_terakhir=?, tgl_kontak_terakhir=?, status=?, nip_petugas_register=?, cek_tgl_abstrak=?, tgl_abstrak=?, nip_petugas_verif=?, "
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
                            cmbStatus.getSelectedItem().toString(), TnipRegister.getText(), cekTglAbs, Valid.SetTgl(TtglAbstrak.getSelectedItem() + ""), TnipVerif.getText(),
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

    private void BtnVerifikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVerifikasiActionPerformed
        initDokter();
        akses.setform("RMRegisterCancer");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnVerifikasiActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbRegister.getSelectedRow() > -1) {
            if (akses.getadmin() == true || tbRegister.getValueAt(tbRegister.getSelectedRow(), 49).toString().equals(akses.getkode())
                    || tbRegister.getValueAt(tbRegister.getSelectedRow(), 46).toString().equals(akses.getkode())) {
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
                JOptionPane.showMessageDialog(rootPane, "Maaf, data hanya bisa dihapus oleh " + tbRegister.getValueAt(tbRegister.getSelectedRow(), 7).toString() + " atau    \n"
                        + tbRegister.getValueAt(tbRegister.getSelectedRow(), 53).toString() + " ....");
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
            
            param.put("register", TnmRegister.getText());
            param.put("verif", TnmVerif.getText());
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

    private void BtnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegisterActionPerformed
        initPetugas();
        akses.setform("RMRegisterCancer");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnRegisterActionPerformed

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

    private void TabRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRegisterMouseClicked
        if (TabRegister.getSelectedIndex() == 1) {
            Thasil.setText("");
            Tinstruksi.setText("");
            tampilCppt();            
        } else if (TabRegister.getSelectedIndex() == 2) {
            if (TNoRM.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu data pasiennya terlebih dulu pada tabel.");
                TabRegister.setSelectedIndex(0);
                tampil();
            } else {
                Valid.tabelKosong(tabModeHasilLab);
                TCari3.setText("");
                tampilLIS();
            }
        } else if (TabRegister.getSelectedIndex() == 3) {
            if (TNoRM.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu data pasiennya terlebih dulu pada tabel.");
                TabRegister.setSelectedIndex(0);
                tampil();
            } else {
                TCari4.setText("");
                cmbHlm2.setSelectedIndex(0);
                tampilPeriksaPA();
                tampilPreviewPA("");
            }
        } else if (TabRegister.getSelectedIndex() == 4) {
            if (TNoRM.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu data pasiennya terlebih dulu pada tabel.");
                TabRegister.setSelectedIndex(0);
                tampil();
            } else {
                TCari5.setText("");
                HasilPeriksa.setText("");
                tampilItem();
            }
        } else if (TabRegister.getSelectedIndex() == 5) {
            if (TNoRM.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu data pasiennya terlebih dulu pada tabel.");
                TabRegister.setSelectedIndex(0);
                tampil();
            } else {
                TNoRw1.setText(TNoRw.getText());
                TNoRm1.setText(TNoRM.getText());
                TPasien1.setText(TPasien.getText());
                ChkDokumen.setSelected(false);
                tampilDokJangMed();
            }
        }
    }//GEN-LAST:event_TabRegisterMouseClicked

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void tbLISMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLISMouseClicked
        if (tabModeLis.getRowCount() != 0) {
            try {
                getDataLis();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbLISMouseClicked

    private void tbLISKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbLISKeyPressed
        if (tabModeLis.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataLis();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbLISKeyPressed

    private void TCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari4ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari4.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCari3KeyPressed

    private void BtnCari4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari4ActionPerformed
        Valid.tabelKosong(tabModeHasilLab);
        tampilLIS();
    }//GEN-LAST:event_BtnCari4ActionPerformed

    private void BtnCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari4ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari4KeyPressed

    private void BtnKeluar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar5ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar5ActionPerformed

    private void BtnKeluar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar9ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar9ActionPerformed

    private void tbPAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPAMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                if (tbPA.getSelectedRow() != -1) {
                    tampilPreviewPA(tbPA.getValueAt(tbPA.getSelectedRow(), 20).toString());
                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPAMouseClicked

    private void tbPAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPAKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    if (tbPA.getSelectedRow() != -1) {
                        tampilPreviewPA(tbPA.getValueAt(tbPA.getSelectedRow(), 20).toString());
                    }
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPAKeyPressed

    private void TCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari9ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari4KeyPressed

    private void BtnCari9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari9ActionPerformed
        tampilPeriksaPA();
        tampilPreviewPA("");
    }//GEN-LAST:event_BtnCari9ActionPerformed

    private void BtnCari9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari9KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari9ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari9KeyPressed

    private void tbRadiologiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRadiologiMouseClicked
        if (tabModeRad.getRowCount() != 0) {
            try {
                getDataRadiologi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRadiologiMouseClicked

    private void tbRadiologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRadiologiKeyPressed
        if (tabModeRad.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRadiologi();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRadiologiKeyPressed

    private void TCari5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari5ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari5.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCari5KeyPressed

    private void BtnCari5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari5ActionPerformed
        HasilPeriksa.setText("");
        tampilItem();
    }//GEN-LAST:event_BtnCari5ActionPerformed

    private void BtnCari5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari5ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari5KeyPressed

    private void BtnKeluar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar6ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar6ActionPerformed

    private void ChkDokumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkDokumenActionPerformed
        tampilDokJangMed();
    }//GEN-LAST:event_ChkDokumenActionPerformed

    private void BtnCari6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari6ActionPerformed
        tampilDokJangMed();
    }//GEN-LAST:event_BtnCari6ActionPerformed

    private void BtnCari6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari6KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari6KeyPressed

    private void BtnKeluar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar7ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar7ActionPerformed

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
    private widget.Button BtnCari4;
    private widget.Button BtnCari5;
    private widget.Button BtnCari6;
    private widget.Button BtnCari9;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKabupaten;
    private widget.Button BtnKecamatan;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar5;
    private widget.Button BtnKeluar6;
    private widget.Button BtnKeluar7;
    private widget.Button BtnKeluar9;
    private widget.Button BtnPrint;
    private widget.Button BtnRegister;
    private widget.Button BtnSimpan;
    private widget.Button BtnVerifikasi;
    public widget.CekBox ChkDokumen;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput2;
    private widget.PanelBiasa FormInput3;
    private widget.TextArea HasilPeriksa;
    private widget.Label LCount;
    private widget.editorpane LoadHTML1;
    private widget.editorpane LoadHTML2;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private usu.widget.glass.PanelGlass PanelWallpublic;
    private usu.widget.glass.PanelGlass PanelWallwifi;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll14;
    private widget.ScrollPane Scroll15;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll22;
    private widget.ScrollPane Scroll24;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    public widget.TextBox TCari;
    private widget.TextBox TCari3;
    private widget.TextBox TCari4;
    private widget.TextBox TCari5;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRm1;
    private widget.TextBox TNoRw;
    private widget.TextBox TNoRw1;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private javax.swing.JTabbedPane TabRegister;
    private widget.TextBox TalamatSementera;
    private widget.TextBox TalamatTetap;
    private widget.TextArea Thasil;
    private widget.TextArea Tinstruksi;
    private widget.TextBox TkdKab;
    private widget.TextBox TkdKec;
    private widget.TextBox TkdPos;
    private widget.TextBox TkdPosTetap;
    private widget.TextArea Tkesimpulan;
    private widget.TextBox TkodeC;
    private widget.TextBox TkodeM;
    private widget.TextBox Tnik;
    private widget.TextBox TnipRegister;
    private widget.TextBox TnipVerif;
    private widget.TextBox TnmAwal;
    private widget.TextBox TnmDiagnosaKodeC;
    private widget.TextBox TnmDiagnosaKodeM;
    private widget.TextBox TnmKab;
    private widget.TextBox TnmKec;
    private widget.TextBox TnmKeluarga;
    private widget.TextBox TnmRegister;
    private widget.TextBox TnmTengah;
    private widget.TextBox TnmVerif;
    private widget.TextBox TnoTelp;
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
    private widget.ComboBox cmbHlm;
    private widget.ComboBox cmbHlm1;
    private widget.ComboBox cmbHlm2;
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
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame31;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
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
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel6;
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
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
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
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass17;
    private widget.panelisi panelGlass18;
    private widget.panelisi panelGlass19;
    private widget.panelisi panelGlass20;
    private widget.panelisi panelGlass21;
    private widget.panelisi panelGlass22;
    private widget.panelisi panelGlass23;
    private widget.panelisi panelGlass30;
    private widget.panelisi panelGlass33;
    private widget.panelisi panelGlass34;
    private widget.panelisi panelGlass35;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.Table tbCPPT;
    private widget.Table tbHasil;
    private widget.Table tbLIS;
    private widget.Table tbPA;
    private widget.Table tbPeriksa;
    private widget.Table tbRadiologi;
    private widget.Table tbRegister;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select rc.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                    + "date_format(rc.tgl_admisi_mrs,'%d-%m-%Y') tglAdmisi, date_format(rc.tgl_verifikasi,'%d-%m-%Y') tglVerif, pg1.nama nmVerifikator, "
                    + "pg2.nama nmRegister from register_cancer rc inner join reg_periksa rp on rp.no_rawat=rc.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg1 on pg1.nik=rc.nip_petugas_verif "
                    + "inner join pegawai pg2 on pg2.nik=rc.nip_petugas_register WHERE "
                    + "rc.tgl_verifikasi between ? and ? and rc.no_rawat LIKE ? or "
                    + "rc.tgl_verifikasi between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "rc.tgl_verifikasi between ? and ? and p.nm_pasien LIKE ? or "
                    + "rc.tgl_verifikasi between ? and ? and pg1.nama LIKE ? or "
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
                        rs.getString("nmVerifikator"),
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
                        rs.getString("nip_petugas_register"),
                        rs.getString("cek_tgl_abstrak"),
                        rs.getString("tgl_abstrak"),
                        rs.getString("nip_petugas_verif"),
                        rs.getString("tgl_verifikasi"),
                        rs.getString("status_rawat"),
                        rs.getString("waktu_simpan"),
                        rs.getString("nmRegister")
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
        chkTglAbstrak.setSelected(false);
        TtglAbstrak.setEnabled(false);
        TtglAbstrak.setDate(new Date());
        TnipRegister.setText("-");
        TnmRegister.setText("-");
        TnipVerif.setText("-");
        TnmVerif.setText("-");
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
            TnipRegister.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 46).toString());
            TnmRegister.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 53).toString());
            cekTglAbs = tbRegister.getValueAt(tbRegister.getSelectedRow(), 47).toString();
            Valid.SetTgl(TtglAbstrak, tbRegister.getValueAt(tbRegister.getSelectedRow(), 48).toString());
            TnipVerif.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 49).toString());
            TnmVerif.setText(tbRegister.getValueAt(tbRegister.getSelectedRow(), 7).toString());
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
        TnmDiagnosaKodeC.setText(Sequel.cariIsi("SELECT COALESCE((SELECT nm_topography FROM master_icdo_topography WHERE "
                + "kd_topography='" + TkodeC.getText() + "' limit 1),'kode ICD-10 tidak termasuk topography ICD-O')"));
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
            TnipRegister.setText("-");
            TnmRegister.setText("-");
        } else {
            TnipRegister.setText(akses.getkode());
            TnmRegister.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipRegister.getText() + "'"));
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
                            TnipRegister.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            TnmRegister.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnRegister.requestFocus();
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
    
    private void initDokter() {
        if (dokter == null) {
            dokter = new DlgCariDokter(null, false);

            dokter.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {}
                @Override
                public void windowClosing(WindowEvent e) {}
                @Override
                public void windowClosed(WindowEvent e) {
                    if (akses.getform().equals("RMRegisterCancer")) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            TnipVerif.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            TnmVerif.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnVerifikasi.requestFocus();
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
            ps3 = koneksi.prepareStatement("select pg1.nama ptgs, date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i') jamlapor, "
                    + "pg2.nama dpjp, date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i') jamverif from cppt_konfirmasi_terapi ck "
                    + "inner join pegawai pg1 on pg1.nik=ck.nip_petugas_konfir inner join pegawai pg2 on pg2.nik=ck.nip_dpjp_konfir where "
                    + "ck.no_rawat = '" + norwt + "' and ck.tgl_cppt='" + tglcppt + "' and ck.cppt_shift='" + sift + "' "
                    + "and ck.jam_cppt='" + jamcppt + "' order by ck.waktu_simpan");
            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    if (dataKonfirmasi.equals("")) {
                        dataKonfirmasi = "Tgl. Lapor : " + rs3.getString("tgllapor") + ", Jam : " + rs3.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs3.getString("tglverif") + ", Jam : " + rs3.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs3.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs3.getString("dpjp");
                    } else {
                        dataKonfirmasi = dataKonfirmasi + "\n\nTgl. Lapor : " + rs3.getString("tgllapor") + ", Jam : " + rs3.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs3.getString("tglverif") + ", Jam : " + rs3.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs3.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs3.getString("dpjp");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
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
    
    private void tampilLIS() {
        Valid.tabelKosong(tabModeLis);
        try {
            psLab1 = koneksi.prepareStatement("SELECT CONCAT(p.no_rkm_medis,' - ',p.nm_pasien) pasien, lr.no_lab nolis, "
                    + "IF(lh.no_lab IS NULL,'Petugas Lab. belum mengirim hasil','Hasil pemeriksaan Lab. bisa dibaca') hasil_lab, "
                    + "IFNULL(lh.no_lab,'') cekOK, pl.no_rawat, IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%d-%m-%Y'),DATE_FORMAT(pl.tgl_periksa,'%d-%m-%Y')) tgl, "
                    + "IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%H:%i:%s'),DATE_FORMAT(pl.jam,'%H:%i:%s')) jam, "
                    + "IFNULL(lh.dokter_pengirim,'') dokter_pengirim, if(rp.status_lanjut='Ralan','R. Jalan','R. Inap') stts_lnjut FROM lis_reg lr "
                    + "LEFT JOIN reg_periksa rp ON rp.no_rawat=lr.no_rawat LEFT JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                    + "LEFT JOIN periksa_lab pl ON pl.no_rawat=lr.no_rawat LEFT JOIN lis_hasil_data_pasien lh ON lh.no_lab=lr.no_lab WHERE "
                    + "rp.no_rkm_medis like ? and lr.no_lab like ? or "
                    + "rp.no_rkm_medis like ? and IF(lh.no_lab IS NULL,'Petugas Lab. belum mengirim hasil','Hasil Lab. bisa dicetak') like ? or "
                    + "rp.no_rkm_medis like ? and pl.no_rawat like ? or "
                    + "rp.no_rkm_medis like ? and IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%d-%m-%Y'),DATE_FORMAT(pl.tgl_periksa,'%d-%m-%Y')) like ? or "
                    + "rp.no_rkm_medis like ? and IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%H:%i:%s'),DATE_FORMAT(pl.jam,'%H:%i:%s')) like ? or "
                    + "rp.no_rkm_medis like ? and lh.dokter_pengirim like ? or "
                    + "rp.no_rkm_medis like ? and if(rp.status_lanjut='Ralan','R. Jalan','R. Inap') like ? "
                    + "GROUP BY lr.no_lab ORDER BY rp.no_rawat desc, tgl DESC, jam DESC, hasil_lab limit " + cmbHlm.getSelectedItem().toString() + "");
            try {
                psLab1.setString(1, "%" + TNoRM.getText() + "%");
                psLab1.setString(2, "%" + TCari3.getText().trim() + "%");
                psLab1.setString(3, "%" + TNoRM.getText() + "%");
                psLab1.setString(4, "%" + TCari3.getText().trim() + "%");
                psLab1.setString(5, "%" + TNoRM.getText() + "%");
                psLab1.setString(6, "%" + TCari3.getText().trim() + "%");
                psLab1.setString(7, "%" + TNoRM.getText() + "%");
                psLab1.setString(8, "%" + TCari3.getText().trim() + "%");
                psLab1.setString(9, "%" + TNoRM.getText() + "%");
                psLab1.setString(10, "%" + TCari3.getText().trim() + "%");                
                psLab1.setString(11, "%" + TNoRM.getText() + "%");
                psLab1.setString(12, "%" + TCari3.getText().trim() + "%");
                psLab1.setString(13, "%" + TNoRM.getText() + "%");
                psLab1.setString(14, "%" + TCari3.getText().trim() + "%");
                rsLab1 = psLab1.executeQuery();
                while (rsLab1.next()) {
                    tabModeLis.addRow(new String[]{
                        rsLab1.getString("pasien"),
                        rsLab1.getString("nolis"),
                        rsLab1.getString("hasil_lab"),
                        rsLab1.getString("cekOK"),
                        rsLab1.getString("no_rawat"),
                        rsLab1.getString("tgl"),
                        rsLab1.getString("jam"),
                        rsLab1.getString("dokter_pengirim"),
                        rsLab1.getString("stts_lnjut")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsLab1 != null) {
                    rsLab1.close();
                }
                if (psLab1 != null) {
                    psLab1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataLis() {
        noLIS = "";
        cekLIS = "";
        ketLIS = "";
        tglLIS = "";
        jamLIS = "";
        drpengirim = "";
        tglPeriksaLIS = "";
        jamPeriksaLIS = "";

        if (tbLIS.getSelectedRow() != -1) {
            noLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 1).toString();
            ketLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 2).toString();
            cekLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 3).toString();
            tglLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 5).toString();
            jamLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 6).toString();
            drpengirim = tbLIS.getValueAt(tbLIS.getSelectedRow(), 7).toString();
            tglPeriksaLIS = Sequel.cariIsi("SELECT DATE(waktu_reg_lab) FROM lis_hasil_data_pasien WHERE no_lab='" + noLIS + "'");
            jamPeriksaLIS = Sequel.cariIsi("SELECT TIME(waktu_reg_lab) FROM lis_hasil_data_pasien WHERE no_lab='" + noLIS + "'");
            tampilHasil(noLIS);
        }
    }
    
    private void tampilHasil(String nolisDipilih) {
        try {
            Sequel.queryu("delete from temporary_lis");
            Valid.tabelKosong(tabModeHasilLab);
            psLabA.setString(1, nolisDipilih);
            rsLabA = psLabA.executeQuery();
            while (rsLabA.next()) {
                Sequel.menyimpan("temporary_lis", "'" + rsLabA.getString("kategori_pemeriksaan_nama") + "','','','',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Kategori Pemeriksaan");
                tabModeHasilLab.addRow(new Object[]{false, rsLabA.getString("kategori_pemeriksaan_nama"), "", "", "", "", "", ""});

                psLabB.setString(1, nolisDipilih);
                psLabB.setString(2, rsLabA.getString("kategori_pemeriksaan_nama"));
                rsLabB = psLabB.executeQuery();
                while (rsLabB.next()) {
                    Sequel.menyimpan("temporary_lis", "'   " + rsLabB.getString("sub_kategori_pemeriksaan_nama") + "','','','',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Sub Kategori Pemeriksaan");
                    tabModeHasilLab.addRow(new Object[]{false, "   " + rsLabB.getString("sub_kategori_pemeriksaan_nama"), "", "", "", "", "", ""});

                    psLabC.setString(1, nolisDipilih);
                    psLabC.setString(2, rsLabB.getString("sub_kategori_pemeriksaan_nama"));
                    psLabC.setString(3, rsLabA.getString("kategori_pemeriksaan_nama"));

                    rsLabC = psLabC.executeQuery();
                    while (rsLabC.next()) {
                        Sequel.menyimpan("temporary_lis", "'     " + Valid.mysql_real_escape_string(rsLabC.getString("pemeriksaan_nama")) + "',"
                                + "'" + Valid.mysql_real_escape_string(rsLabC.getString("nilai_hasil")) + "',"
                                + "'" + Valid.mysql_real_escape_string(rsLabC.getString("satuan")) + "',"
                                + "'" + rsLabC.getString("flag_kode") + "',"
                                + "'" + Valid.mysql_real_escape_string(rsLabC.getString("nilai_rujukan")) + "',"
                                + "'" + rsLabC.getString("wkt_selesai") + "',"
                                + "'" + Valid.mysql_real_escape_string(rsLabC.getString("metode")) + "',"
                                + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Hasil Pemeriksaan");
                        tabModeHasilLab.addRow(new Object[]{
                            false,
                            "     " + rsLabC.getString("pemeriksaan_nama"),
                            rsLabC.getString("nilai_hasil"),
                            rsLabC.getString("satuan"),
                            rsLabC.getString("flag_kode"),
                            rsLabC.getString("nilai_rujukan"),
                            rsLabC.getString("wkt_selesai"),
                            rsLabC.getString("metode")
                        });

                        if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "' and status='1' "
                                + "and (nm_dokter like '%dr.%' or nm_dokter like '%drg.%')") > 0) {
                            Sequel.menyimpanIgnore("pembaca_hasil_lab",
                                    "'" + tbLIS.getValueAt(tbLIS.getSelectedRow(), 4).toString() + "',"
                                    + "'" + akses.getkode() + "',"
                                    + "'" + tbLIS.getValueAt(tbLIS.getSelectedRow(), 1).toString() + "',"
                                    + "'" + Valid.SetTgl(tbLIS.getValueAt(tbLIS.getSelectedRow(), 5).toString() + "") + "',"
                                    + "'" + tbLIS.getValueAt(tbLIS.getSelectedRow(), 6).toString() + "',"
                                    + "'" + Sequel.cariIsi("select now()") + "'", "Pembaca Hasil Lab.");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void tampilPeriksaPA() {
        Valid.tabelKosong(tabMode2);
        try {
            ps4 = koneksi.prepareStatement("SELECT hpa.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                    + "p2.nama drPengirim, date_format(hpa.tgl_periksa,'%d-%m-%Y') tglPeriksa, date_format(hpa.tgl_hasil,'%d-%m-%Y') tglHasil, "
                    + "p.tgl_lahir FROM hasil_patologi_anatomi hpa inner join reg_periksa rp on rp.no_rawat=hpa.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai p2 on p2.nik=hpa.nip_perujuk where "
                    + "rp.no_rkm_medis like ? and hpa.no_rawat like ? or "
                    + "rp.no_rkm_medis like ? and hpa.no_pa like ? or "
                    + "rp.no_rkm_medis like ? and hpa.lokasi_organ like ? or "
                    + "rp.no_rkm_medis like ? and hpa.makroskopik like ? or "
                    + "rp.no_rkm_medis like ? and hpa.mikroskopik like ? or "
                    + "rp.no_rkm_medis like ? and hpa.kesimpulan like ? or "
                    + "rp.no_rkm_medis like ? and hpa.anjuran like ? or "
                    + "rp.no_rkm_medis like ? and p2.nama like ? or "
                    + "rp.no_rkm_medis like ? and hpa.nm_unit like ? order by hpa.waktu_simpan desc limit " + cmbHlm2.getSelectedItem().toString() + "");
            try {
                ps4.setString(1, "%" + TNoRM.getText() + "%");
                ps4.setString(2, "%" + TCari4.getText().trim() + "%");
                ps4.setString(3, "%" + TNoRM.getText() + "%");
                ps4.setString(4, "%" + TCari4.getText().trim() + "%");
                ps4.setString(5, "%" + TNoRM.getText() + "%");
                ps4.setString(6, "%" + TCari4.getText().trim() + "%");
                ps4.setString(7, "%" + TNoRM.getText() + "%");
                ps4.setString(8, "%" + TCari4.getText().trim() + "%");
                ps4.setString(9, "%" + TNoRM.getText() + "%");
                ps4.setString(10, "%" + TCari4.getText().trim() + "%");
                ps4.setString(11, "%" + TNoRM.getText() + "%");
                ps4.setString(12, "%" + TCari4.getText().trim() + "%");
                ps4.setString(13, "%" + TNoRM.getText() + "%");
                ps4.setString(14, "%" + TCari4.getText().trim() + "%");
                ps4.setString(15, "%" + TNoRM.getText() + "%");
                ps4.setString(16, "%" + TCari4.getText().trim() + "%");
                ps4.setString(17, "%" + TNoRM.getText() + "%");
                ps4.setString(18, "%" + TCari4.getText().trim() + "%");
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    tabMode2.addRow(new String[]{
                        rs4.getString("no_rawat"),
                        rs4.getString("no_pa"),
                        rs4.getString("no_rkm_medis"),
                        rs4.getString("nm_pasien"),
                        rs4.getString("jenkel"),
                        rs4.getString("tglLahir"),                        
                        rs4.getString("drPengirim"),                        
                        rs4.getString("nm_unit"),                        
                        rs4.getString("tglPeriksa"),                        
                        rs4.getString("tglHasil"),                        
                        rs4.getString("lokasi_organ"),
                        rs4.getString("makroskopik"),
                        rs4.getString("mikroskopik"),
                        rs4.getString("kesimpulan"),
                        rs4.getString("anjuran"),
                        rs4.getString("kd_gambar"),
                        rs4.getString("nip_perujuk"),
                        rs4.getString("tgl_periksa"),
                        rs4.getString("tgl_lahir"),
                        rs4.getString("tgl_hasil"),
                        rs4.getString("waktu_simpan"),                        
                        rs4.getString("italic_makroskopik"),
                        rs4.getString("italic_mikroskopik"),
                        rs4.getString("italic_kesimpulan"),
                        rs4.getString("italic_anjuran")
                    });
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilPreviewPA(String wktsimpan) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs5 = koneksi.prepareStatement("SELECT hpa.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                        + "p2.nama drPengirim, date_format(hpa.tgl_periksa,'%d-%m-%Y') tglPeriksa, date_format(hpa.tgl_hasil,'%d-%m-%Y') tglHasil, p.tgl_lahir, "
                        + "p1.nama drPatologi, date_format(hpa.waktu_simpan,'%d/%m/%Y') tgl, time(hpa.waktu_simpan) jam "
                        + "FROM hasil_patologi_anatomi hpa inner join reg_periksa rp on rp.no_rawat=hpa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai p2 on p2.nik=hpa.nip_perujuk "
                        + "inner join pegawai p1 on p1.nik=hpa.nip_dokter_pa where "
                        + "hpa.waktu_simpan='" + wktsimpan + "' order by hpa.waktu_simpan desc").executeQuery();
                if (rs5.next()) {
                    rs5.beforeFirst();
                    while (rs5.next()) {
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' align='center' colspan='8' bgcolor='#f8fdf3'><b>HASIL PEMERIKSAAN PATOLOGI ANATOMI</b></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' align='left'>Lokasi / Organ</td>"
                                + "<td valign='top' colspan='7' align='left'>: " + rs5.getString("lokasi_organ")
                                        .replace("\r\n", "<br>").replace("\n", "<br>").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;").replace("  ", "&nbsp;&nbsp;") + "<br></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' align='left'>Makroskopik</td>"
                                + "<td valign='top' colspan='7' align='left'>: " + rs5.getString("makroskopik")
                                        .replace("\r\n", "<br>").replace("\n", "<br>").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;").replace("  ", "&nbsp;&nbsp;")
                                + " <i>" + rs5.getString("italic_makroskopik") + "</i><br></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' align='left'>Mikroskopik</td>"
                                + "<td valign='top' colspan='7' align='left'>: " + rs5.getString("mikroskopik")
                                        .replace("\r\n", "<br>").replace("\n", "<br>").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;").replace("  ", "&nbsp;&nbsp;")
                                + " <i>" + rs5.getString("italic_mikroskopik") + "</i><br></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' align='left'>Kesimpulan</td>"
                                + "<td valign='top' colspan='7' align='left'>: " + rs5.getString("kesimpulan")
                                        .replace("\r\n", "<br>").replace("\n", "<br>").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;").replace("  ", "&nbsp;&nbsp;")
                                + " <i>" + rs5.getString("italic_kesimpulan") + "</i><br></td>"
                                + "</tr>");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' colspan='1' align='left'>Anjuran</td>"
                                + "<td valign='top' colspan='7' align='left'>: " + rs5.getString("anjuran")
                                        .replace("\r\n", "<br>").replace("\n", "<br>").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;").replace("  ", "&nbsp;&nbsp;")
                                + " <i>" + rs5.getString("italic_anjuran") + "</i><br></td>"
                                + "</tr>");
                        
                        String gambar = "", ipGambar = "", isi = "";
                        try {
                            //cek atau ping ip addres
                            ipGambar = "192.168.0.230";
                            InetAddress inet = InetAddress.getByName(ipGambar);

                            //ping sukses timeout 100 ms (0.1 detik)
                            if (inet.isReachable(100)) {
                                if (rs5.getString("kd_gambar").equals("")) {
                                    gambar = "http://192.168.0.230:7183/img-rme/gambar_tidak_ditemukan.jpg";
                                } else {
                                    gambar = "http://192.168.0.230:7183/rme/download.php?id=" + rs5.getString("kd_gambar");
                                }
                            //ping gagal
                            } else {
                                gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/gambar_tidak_ditemukan.jpg";
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : " + e);
                            gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/gambar_tidak_ditemukan.jpg";
                        }

                        isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='006'"),
                                        "Hasil Pemeriksaan Lab. (Patologi Anatomi)", rs5.getString("drPatologi"),
                                        rs5.getString("tgl"), rs5.getString("jam")) + "') from kalimat_tte where kode='006'");
                        Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                        
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='middle' colspan='5' rowspan='5' align='center'><br><img src='" + gambar + "' width='500' alt='Patologi Anatomi'></td>"
                                + "<td valign='top' colspan='3' align='center'><br><br><br>Pemeriksa,<br><img src='file:///" + Sequel.cariFolderTte() + File.separator + "QRTte.jpg" + "' width='150' alt='TTE Dokter Patologi Anatomi'><br>"
                                + rs5.getString("drPatologi") + "</b><br>SIP : "
                                + Sequel.cariIsi("select no_ijn_praktek from dokter where kd_dokter='" + rs5.getString("nip_dokter_pa") + "'") + "</td>"
                                + "</tr>");
                    }
                    htmlContent.append(
                            "</tbody>"
                            + "</table>");
                    
                    htmlContent.append("<tr><td><br></td></tr>");
                }

                LoadHTML1.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' colspan='8' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs5 != null) {
                    rs5.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void tampilItem() {
        Valid.tabelKosong(tabModeRad);
        Scroll11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Hasil Expertise Radiologi :.",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)));
        try {
            psRad = koneksi.prepareStatement("select p.no_rkm_medis, p.nm_pasien, if(rp.status_lanjut='ralan','R. Jalan','R. Inap') jns_rwt, j.nm_perawatan, "
                    + "date_format(pr.tgl_periksa,'%d-%m-%Y') tglnya, pr.jam, pr.no_rawat, pr.kd_jenis_prw, pr.tgl_periksa, pg.nama dr_perujuk FROM periksa_radiologi pr "
                    + "inner join jns_perawatan_radiologi j on j.kd_jenis_prw=pr.kd_jenis_prw inner join reg_periksa rp on rp.no_rawat=pr.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=pr.dokter_perujuk where "
                    + "rp.no_rkm_medis like ? and if(rp.status_lanjut='ralan','R. Jalan','R. Inap') like ? or "
                    + "rp.no_rkm_medis like ? and j.nm_perawatan like ? or "
                    + "rp.no_rkm_medis like ? and pg.nama like ? or "
                    + "rp.no_rkm_medis like ? and date_format(pr.tgl_periksa,'%d-%m-%Y') like ? or "
                    + "rp.no_rkm_medis like ? and pr.no_rawat like ? "
                    + "ORDER BY rp.no_rawat desc, pr.tgl_periksa desc, pr.jam desc limit " + cmbHlm1.getSelectedItem().toString() + "");

            try {
                psRad.setString(1, "%" + TNoRM.getText() + "%");
                psRad.setString(2, "%" + TCari5.getText().trim() + "%");
                psRad.setString(3, "%" + TNoRM.getText() + "%");
                psRad.setString(4, "%" + TCari5.getText().trim() + "%");
                psRad.setString(5, "%" + TNoRM.getText() + "%");
                psRad.setString(6, "%" + TCari5.getText().trim() + "%");
                psRad.setString(7, "%" + TNoRM.getText() + "%");
                psRad.setString(8, "%" + TCari5.getText().trim() + "%");
                psRad.setString(9, "%" + TNoRM.getText() + "%");
                psRad.setString(10, "%" + TCari5.getText().trim() + "%");
                rsRad = psRad.executeQuery();
                while (rsRad.next()) {
                    tabModeRad.addRow(new String[]{
                        rsRad.getString("no_rkm_medis"),
                        rsRad.getString("nm_pasien"),
                        rsRad.getString("jns_rwt"),
                        rsRad.getString("nm_perawatan"),
                        rsRad.getString("dr_perujuk"),
                        rsRad.getString("tglnya"),
                        rsRad.getString("jam"),
                        rsRad.getString("no_rawat"),
                        rsRad.getString("kd_jenis_prw"),
                        rsRad.getString("tgl_periksa")
                    });
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (rsRad != null) {
                    rsRad.close();
                }
                if (psRad != null) {
                    psRad.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataRadiologi() {
        kdItem = "";
        norawat = "";
        tglhasil = "";
        jamhasil = "";
        nmpemeriksaan = "";

        if (tbRadiologi.getSelectedRow() != -1) {
            nmpemeriksaan = tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 3).toString();
            jamhasil = tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 6).toString();
            norawat = tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 7).toString();
            kdItem = tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 8).toString();
            tglhasil = tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 9).toString();            
            deskripsiHasil();
        }
    }
    
    private void deskripsiHasil() {
        if (Sequel.cariInteger("select count(-1) from hasil_radiologi where no_rawat='" + norawat + "' and "
                + "tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'") == 0) {
            HasilPeriksa.setText("Hasil expertise radiologi belum dikirim ke SIMRS..!!");
        } else {
            Scroll11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "<html>.: Hasil Expertise Radiologi <b>[ " + nmpemeriksaan + " ]</b> :.</html>",
                    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                    javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)));
            
            HasilPeriksa.setText(Sequel.cariIsi("select hasil from hasil_radiologi where "
                    + "no_rawat like '%" + norawat + "%' and "
                    + "tgl_periksa like '%" + tglhasil + "%' and "
                    + "jam like '%" + jamhasil + "%' and "
                    + "kd_jenis_prw like '%" + kdItem + "%'"));

            if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "' and status='1' "
                    + "and (nm_dokter like '%dr.%' or nm_dokter like '%drg.%')") > 0) {
                Sequel.menyimpanIgnore("pembaca_hasil_radiologi",
                        "'" + tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 7).toString() + "',"
                        + "'" + akses.getkode() + "',"
                        + "'" + tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 8).toString() + "',"
                        + "'" + tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 9).toString() + "',"
                        + "'" + tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 6).toString() + "',"
                        + "'" + Sequel.cariIsi("select now()") + "'", "Pembaca hasil radiologi");
            }
        }
    }
    
    private void tampilDokJangMed() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                if (ChkDokumen.isSelected() == true) {
                    rsDok = koneksi.prepareStatement("SELECT rf.id_file, rf.no_rawat, rf.nama_file_ori, date_format(rf.tgl_upload,'%d/%m/%Y') tglUpload, "
                            + "time_format(rf.tgl_upload,'%H:%i') jam, rj.nama_pemeriksaan, ifnull(pg.nama,'-') nmpetugas, rf.jenis_pemeriksaan kode, "
                            + "rf.petugas nip from rme_file_upload rf inner join rme_jenis_pemeriksaan rj on rj.kode_jenis_pemeriksaan=rf.jenis_pemeriksaan "
                            + "left join pegawai pg on pg.nik=rf.petugas where rf.nomr='" + TNoRM.getText() + "' and rf.stts_data='1' "
                            + "order by rf.tgl_upload desc").executeQuery();
                } else {
                    rsDok = koneksi.prepareStatement("SELECT rf.id_file, rf.no_rawat, rf.nama_file_ori, date_format(rf.tgl_upload,'%d/%m/%Y') tglUpload, "
                            + "time_format(rf.tgl_upload,'%H:%i') jam, rj.nama_pemeriksaan, ifnull(pg.nama,'-') nmpetugas, rf.jenis_pemeriksaan kode, "
                            + "rf.petugas nip from rme_file_upload rf inner join rme_jenis_pemeriksaan rj on rj.kode_jenis_pemeriksaan=rf.jenis_pemeriksaan "
                            + "left join pegawai pg on pg.nik=rf.petugas where rf.no_rawat='" + TNoRw.getText() + "' and rf.stts_data='1' "
                            + "order by rf.tgl_upload desc").executeQuery();
                }

                urut = 1;
                if (rsDok.next()) {
                    htmlContent.append(
                            "<table width='100%' class='isi'>"
                            + "<thead>"
                            + "<tr class='isi'>"
                            + "<td align='center' bgcolor='#f8fdf3'><b>No.</b></td>"
                            + "<td align='center' bgcolor='#f8fdf3'><b>Kode File</b></td>"
                            + "<td align='center' bgcolor='#f8fdf3'><b>Nama Pemeriksaan</b></td>"
                            + "<td align='center' bgcolor='#f8fdf3'><b>Tgl. Upload</b></td>"
                            + "<td align='center' bgcolor='#f8fdf3'><b>Jam</b></td>"
                            + "<td align='center' bgcolor='#f8fdf3'><b>Nama File</b></td>"
                            + "<td align='center' bgcolor='#f8fdf3'><b>Petugas Yang Upload</b></td>"
                            + "</tr>"
                            + "</thead>"
                            + "<tbody>"
                    );

                    rsDok.beforeFirst();
                    while (rsDok.next()) {
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' width='20px'>" + urut + ".</td>"
                                + "<td valign='top' align='center'>" + rsDok.getString("id_file") + "</td>"
                                + "<td valign='top'>" + rsDok.getString("nama_pemeriksaan") + "</td>"
                                + "<td valign='top' align='center'>" + rsDok.getString("tglUpload") + "</td>"
                                + "<td valign='top' align='center'>" + rsDok.getString("jam") + " Wita" + "</td>"
                                + "<td valign='top'>" + rsDok.getString("nama_file_ori") + " " + "<a href='" + link + "rme/view/?id=" + rsDok.getString("id_file") + "'>[Klik Untuk Membuka Gambar]</a></td>"
                                + "<td valign='top'>" + rsDok.getString("nmpetugas") + "</td>"
                                + "</tr>"
                        );
                        urut++;
                    }
                    htmlContent.append(
                            "</tbody>"
                            + "</table>"
                    );
                }
                LoadHTML2.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsDok != null) {
                    rsDok.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
}
