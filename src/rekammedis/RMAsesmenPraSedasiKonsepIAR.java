package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import laporan.DlgHasilPenunjangMedis;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class RMAsesmenPraSedasiKonsepIAR extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8;
    private int i = 0, x = 0, pilihan = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String nipDokter = "", hilang = "", masalah = "", leher = "", strok = "", sesak = "", sakit = "", denyut = "", sedang = "", kejang = "", obes = "",
            buka = "", jarak = "", gerakan = "", gigi = "", asa1 = "", asa2 = "", asa3 = "", asa4 = "", emer = "", spinal = "", epid = "", kaudal = "", blok = "",
            ekg = "", spo2 = "", nibp = "", temp = "", lain = "", puasa = "", rencana = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMAsesmenPraSedasiKonsepIAR(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Diagnosa", "Rencana Tindakan", "Dokter Anestesi",
            "riwayat_alergi", "riwayat_anestesi", "obat_saat_ini", "hilangnya_gigi", "masalah_mobilisasi", "leher_pendek", "stroke", "sesak_nafas", "sakit_dada", "denyut_jantung",
            "sedang_hamil", "kejang", "obesitas", "gcs", "tb", "td", "bb", "rr", "nadi", "vas", "suhu", "buka_mulut", "jarak", "gerakan_leher", "gigi_palsu", "mallampathy", "kepala",
            "sklera", "conjungtiva", "leher", "paru_paru", "jantung", "abdomen", "extremitas", "diagnosis", "rencana_tindakan", "asa1", "asa2", "asa3", "asa4", "ket_asa4", "emergency",
            "sedasi_obat1", "sedasi_obat2", "sedasi_obat3", "ga", "spinal", "epidural", "kaudal", "blok", "ekg", "spo2", "nibp", "temp", "lain_lain", "ket_lain", "perawatan_pasca",
            "rawat_khusus", "kesimpulan_anestesi", "cek_puasa", "jam_puasa", "tgl_puasa", "cek_rencana", "jam_rencana", "tgl_rencana", "instruksi", "catatan", "nip_anestesi",
            "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 77; i++) {
            TableColumn column = tbAsesmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(300);
            } else if (i == 6) {
                column.setPreferredWidth(300);
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
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        
        Tgcs.setDocument(new batasInput((int) 7).getKata(Tgcs));
        Ttb.setDocument(new batasInput((int) 7).getKata(Ttb));
        Ttd.setDocument(new batasInput((int) 7).getKata(Ttd));
        Tbb.setDocument(new batasInput((int) 7).getKata(Tbb));
        Trr.setDocument(new batasInput((int) 7).getKata(Trr));
        Tnadi.setDocument(new batasInput((int) 7).getKata(Tnadi));
        Tvas.setDocument(new batasInput((int) 20).getKata(Tvas));        
        Tsuhu.setDocument(new batasInput((int) 7).getKata(Tsuhu));        
        Tkepala.setDocument(new batasInput((int) 100).getKata(Tkepala));        
        Tsklera.setDocument(new batasInput((int) 100).getKata(Tsklera));        
        Tconjung.setDocument(new batasInput((int) 100).getKata(Tconjung));        
        Tleher.setDocument(new batasInput((int) 100).getKata(Tleher));
        Tparu.setDocument(new batasInput((int) 200).getKata(Tparu));
        Tjantung.setDocument(new batasInput((int) 200).getKata(Tjantung));
        Tabdomen.setDocument(new batasInput((int) 200).getKata(Tabdomen));
        Tekstremitas.setDocument(new batasInput((int) 200).getKata(Tekstremitas));
        TketAsa4.setDocument(new batasInput((int) 100).getKata(TketAsa4));
        Tsedasi1.setDocument(new batasInput((int) 100).getKata(Tsedasi1));
        Tsedasi2.setDocument(new batasInput((int) 100).getKata(Tsedasi2));
        Tsedasi3.setDocument(new batasInput((int) 100).getKata(Tsedasi1));
        Tga.setDocument(new batasInput((int) 200).getKata(Tga));
        TketLain.setDocument(new batasInput((int) 100).getKata(TketLain));
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMAsesmenPraSedasiKonsepIAR")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        nipDokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                        TnmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        BtnDokter.requestFocus();
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
        WindowTemplate = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        jPanel1 = new javax.swing.JPanel();
        Scroll2 = new widget.ScrollPane();
        tbTemplate = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        Ttemplate = new widget.TextArea();
        panelisi4 = new widget.panelisi();
        jLabel36 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCopas = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        jLabel95 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        TnmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel276 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        TriwayatAlergi = new widget.TextArea();
        jLabel94 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        TriwayatAnestesi = new widget.TextArea();
        jLabel96 = new widget.Label();
        jLabel97 = new widget.Label();
        jLabel98 = new widget.Label();
        Ttd = new widget.TextBox();
        jLabel99 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel100 = new widget.Label();
        jLabel101 = new widget.Label();
        Tbb = new widget.TextBox();
        Tvas = new widget.TextBox();
        jLabel102 = new widget.Label();
        jLabel103 = new widget.Label();
        Tsuhu = new widget.TextBox();
        Trr = new widget.TextBox();
        jLabel104 = new widget.Label();
        jLabel105 = new widget.Label();
        TtglPuasa = new widget.Tanggal();
        scrollPane16 = new widget.ScrollPane();
        TobatSaatIni = new widget.TextArea();
        cmbPerawatan = new widget.ComboBox();
        cmbRuangKhusus = new widget.ComboBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel127 = new widget.Label();
        chkHilangGigi = new widget.CekBox();
        chkMasalah = new widget.CekBox();
        chkLeherPendek = new widget.CekBox();
        chkStrok = new widget.CekBox();
        chkSesakNafas = new widget.CekBox();
        chkSakitDada = new widget.CekBox();
        chkDenyut = new widget.CekBox();
        chkSedangHamil = new widget.CekBox();
        chkKejang = new widget.CekBox();
        chkObesitas = new widget.CekBox();
        jLabel108 = new widget.Label();
        jLabel109 = new widget.Label();
        Tgcs = new widget.TextBox();
        jLabel113 = new widget.Label();
        Ttb = new widget.TextBox();
        jLabel114 = new widget.Label();
        chkBukaMulut = new widget.CekBox();
        chkJarak = new widget.CekBox();
        chkGerakan = new widget.CekBox();
        chkGigiPalsu = new widget.CekBox();
        jLabel115 = new widget.Label();
        cmbMallam = new widget.ComboBox();
        jLabel116 = new widget.Label();
        jLabel117 = new widget.Label();
        Tkepala = new widget.TextBox();
        jLabel118 = new widget.Label();
        Tsklera = new widget.TextBox();
        jLabel119 = new widget.Label();
        Tconjung = new widget.TextBox();
        jLabel120 = new widget.Label();
        Tleher = new widget.TextBox();
        jLabel121 = new widget.Label();
        Tparu = new widget.TextBox();
        jLabel122 = new widget.Label();
        Tjantung = new widget.TextBox();
        jLabel123 = new widget.Label();
        Tabdomen = new widget.TextBox();
        jLabel124 = new widget.Label();
        Tekstremitas = new widget.TextBox();
        scrollPane17 = new widget.ScrollPane();
        Tdiagnosis = new widget.TextArea();
        jLabel128 = new widget.Label();
        jLabel125 = new widget.Label();
        chkAsa1 = new widget.CekBox();
        chkAsa2 = new widget.CekBox();
        chkAsa3 = new widget.CekBox();
        chkAsa4 = new widget.CekBox();
        TketAsa4 = new widget.TextBox();
        jLabel126 = new widget.Label();
        chkEmergency = new widget.CekBox();
        scrollPane18 = new widget.ScrollPane();
        TkesAnestesi = new widget.TextArea();
        jLabel129 = new widget.Label();
        jLabel130 = new widget.Label();
        jLabel131 = new widget.Label();
        jLabel132 = new widget.Label();
        jLabel133 = new widget.Label();
        Tsedasi1 = new widget.TextBox();
        jLabel134 = new widget.Label();
        Tsedasi2 = new widget.TextBox();
        jLabel135 = new widget.Label();
        Tsedasi3 = new widget.TextBox();
        jLabel136 = new widget.Label();
        Tga = new widget.TextBox();
        jLabel137 = new widget.Label();
        chkSpinal = new widget.CekBox();
        chkEpidural = new widget.CekBox();
        chkKaudal = new widget.CekBox();
        chkBlok = new widget.CekBox();
        jLabel138 = new widget.Label();
        chkEkg = new widget.CekBox();
        chkSpo2 = new widget.CekBox();
        chkNibp = new widget.CekBox();
        chkTemp = new widget.CekBox();
        chkLain = new widget.CekBox();
        TketLain = new widget.TextBox();
        jLabel139 = new widget.Label();
        jLabel140 = new widget.Label();
        chkPuasa = new widget.CekBox();
        chkRencana = new widget.CekBox();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        jLabel277 = new widget.Label();
        TtglRencana = new widget.Tanggal();
        scrollPane19 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();
        jLabel141 = new widget.Label();
        jLabel142 = new widget.Label();
        scrollPane20 = new widget.ScrollPane();
        Tcatatan = new widget.TextArea();
        jLabel143 = new widget.Label();
        scrollPane21 = new widget.ScrollPane();
        TrencanaTindakan = new widget.TextArea();
        jLabel144 = new widget.Label();
        BtnRiwAlergi = new widget.Button();
        BtnRiwAnestesi = new widget.Button();
        BtnObat = new widget.Button();
        BtnDiagnosis = new widget.Button();
        BtnRencanaTindakan = new widget.Button();
        BtnKesimpulan = new widget.Button();
        BtnInstruksi = new widget.Button();
        BtnCatatan = new widget.Button();
        PanelInput1 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbAsesmen = new widget.Table();
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

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHasilPemeriksaanPenunjang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanPenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanPenunjang.setText("Hasil Pemeriksaan Penunjang");
        MnHasilPemeriksaanPenunjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanPenunjang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanPenunjang.setIconTextGap(5);
        MnHasilPemeriksaanPenunjang.setName("MnHasilPemeriksaanPenunjang"); // NOI18N
        MnHasilPemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(195, 26));
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
        MnDokumenJangMed.setPreferredSize(new java.awt.Dimension(195, 26));
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

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 250));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTemplate.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTemplate.setName("tbTemplate"); // NOI18N
        tbTemplate.getTableHeader().setReorderingAllowed(false);
        tbTemplate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTemplateMouseClicked(evt);
            }
        });
        Scroll2.setViewportView(tbTemplate);

        jPanel1.add(Scroll2);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        Ttemplate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Baca Template Dipilih ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Ttemplate.setColumns(20);
        Ttemplate.setRows(5);
        Ttemplate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ttemplate.setName("Ttemplate"); // NOI18N
        Ttemplate.setPreferredSize(new java.awt.Dimension(210, 4000));
        Scroll3.setViewportView(Ttemplate);

        jPanel1.add(Scroll3);

        internalFrame5.add(jPanel1, java.awt.BorderLayout.CENTER);

        panelisi4.setBackground(new java.awt.Color(255, 150, 255));
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Key Word :");
        jLabel36.setName("jLabel36"); // NOI18N
        jLabel36.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel36.setRequestFocusEnabled(false);
        panelisi4.add(jLabel36);

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

        WindowTemplate.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Asesmen Pra Sedasi Konsep IAR ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Cetak Dalam Bentuk :");
        jLabel95.setName("jLabel95"); // NOI18N
        jLabel95.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel95);

        cmbPilihCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak.setName("cmbPilihCetak"); // NOI18N
        cmbPilihCetak.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass8.add(cmbPilihCetak);

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

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 47));
        panelGlass9.setLayout(new java.awt.BorderLayout());

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(600, 402));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setToolTipText("Klik kanan untuk melihat hasil pemeriksaan penunjang medis");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1559));
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

        TnmDokter.setEditable(false);
        TnmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter.setName("TnmDokter"); // NOI18N
        FormInput.add(TnmDokter);
        TnmDokter.setBounds(145, 1515, 410, 23);

        BtnDokter.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('1');
        BtnDokter.setToolTipText("Alt+1");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(560, 1515, 28, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(304, 1327, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(355, 1327, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(407, 1327, 45, 23);

        jLabel276.setForeground(new java.awt.Color(0, 0, 0));
        jLabel276.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel276.setText("Wita     Tanggal :");
        jLabel276.setName("jLabel276"); // NOI18N
        FormInput.add(jLabel276);
        jLabel276.setBounds(460, 1327, 85, 23);

        scrollPane14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane14.setName("scrollPane14"); // NOI18N

        TriwayatAlergi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TriwayatAlergi.setColumns(20);
        TriwayatAlergi.setRows(5);
        TriwayatAlergi.setName("TriwayatAlergi"); // NOI18N
        TriwayatAlergi.setPreferredSize(new java.awt.Dimension(162, 2000));
        TriwayatAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwayatAlergiKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(TriwayatAlergi);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(145, 66, 615, 60);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Riwayat Alergi :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(0, 66, 140, 23);

        scrollPane15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane15.setName("scrollPane15"); // NOI18N

        TriwayatAnestesi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TriwayatAnestesi.setColumns(20);
        TriwayatAnestesi.setRows(5);
        TriwayatAnestesi.setName("TriwayatAnestesi"); // NOI18N
        TriwayatAnestesi.setPreferredSize(new java.awt.Dimension(162, 2000));
        TriwayatAnestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwayatAnestesiKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(TriwayatAnestesi);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(145, 131, 615, 60);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Riwayat Anestesi :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(0, 131, 140, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("KAJIAN SISTEM :");
        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(0, 263, 140, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("TD :");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(145, 431, 50, 23);

        Ttd.setForeground(new java.awt.Color(0, 0, 0));
        Ttd.setName("Ttd"); // NOI18N
        Ttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdKeyPressed(evt);
            }
        });
        FormInput.add(Ttd);
        Ttd.setBounds(200, 431, 70, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Nadi :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(145, 459, 50, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(200, 459, 70, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("mmHg       BB :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(275, 431, 70, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel101.setText("x/menit   VAS :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(275, 459, 75, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        FormInput.add(Tbb);
        Tbb.setBounds(350, 431, 70, 23);

        Tvas.setForeground(new java.awt.Color(0, 0, 0));
        Tvas.setName("Tvas"); // NOI18N
        Tvas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TvasKeyPressed(evt);
            }
        });
        FormInput.add(Tvas);
        Tvas.setBounds(350, 459, 70, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel102.setText("Kg.      RR :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(425, 431, 60, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Suhu :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(425, 459, 55, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        FormInput.add(Tsuhu);
        Tsuhu.setBounds(485, 459, 70, 23);

        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        FormInput.add(Trr);
        Trr.setBounds(485, 431, 70, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel104.setText("x/menit");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(560, 431, 50, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("°C");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(560, 459, 30, 23);

        TtglPuasa.setEditable(false);
        TtglPuasa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-06-2026" }));
        TtglPuasa.setDisplayFormat("dd-MM-yyyy");
        TtglPuasa.setName("TtglPuasa"); // NOI18N
        TtglPuasa.setOpaque(false);
        TtglPuasa.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglPuasa);
        TtglPuasa.setBounds(550, 1327, 90, 23);

        scrollPane16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane16.setName("scrollPane16"); // NOI18N

        TobatSaatIni.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TobatSaatIni.setColumns(20);
        TobatSaatIni.setRows(5);
        TobatSaatIni.setName("TobatSaatIni"); // NOI18N
        TobatSaatIni.setPreferredSize(new java.awt.Dimension(162, 2000));
        TobatSaatIni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatSaatIniKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(TobatSaatIni);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(145, 197, 615, 60);

        cmbPerawatan.setBackground(new java.awt.Color(245, 253, 240));
        cmbPerawatan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPerawatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Rawat Inap", "Rawat Jalan", "Rawat Khusus" }));
        cmbPerawatan.setLightWeightPopupEnabled(false);
        cmbPerawatan.setName("cmbPerawatan"); // NOI18N
        cmbPerawatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPerawatanActionPerformed(evt);
            }
        });
        FormInput.add(cmbPerawatan);
        cmbPerawatan.setBounds(270, 1233, 100, 23);

        cmbRuangKhusus.setBackground(new java.awt.Color(245, 253, 240));
        cmbRuangKhusus.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangKhusus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "ICU", "HCU" }));
        cmbRuangKhusus.setLightWeightPopupEnabled(false);
        cmbRuangKhusus.setName("cmbRuangKhusus"); // NOI18N
        FormInput.add(cmbRuangKhusus);
        cmbRuangKhusus.setBounds(375, 1233, 55, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 38, 140, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(145, 38, 615, 23);

        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setText("Obat Saat Ini :");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(0, 197, 140, 23);

        chkHilangGigi.setBackground(new java.awt.Color(255, 255, 250));
        chkHilangGigi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHilangGigi.setForeground(new java.awt.Color(0, 0, 0));
        chkHilangGigi.setText("Hilangnya Gigi");
        chkHilangGigi.setBorderPainted(true);
        chkHilangGigi.setBorderPaintedFlat(true);
        chkHilangGigi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHilangGigi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHilangGigi.setName("chkHilangGigi"); // NOI18N
        chkHilangGigi.setOpaque(false);
        chkHilangGigi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHilangGigi);
        chkHilangGigi.setBounds(145, 263, 100, 23);

        chkMasalah.setBackground(new java.awt.Color(255, 255, 250));
        chkMasalah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMasalah.setForeground(new java.awt.Color(0, 0, 0));
        chkMasalah.setText("Masalah Mobilisasi Leher");
        chkMasalah.setBorderPainted(true);
        chkMasalah.setBorderPaintedFlat(true);
        chkMasalah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMasalah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMasalah.setName("chkMasalah"); // NOI18N
        chkMasalah.setOpaque(false);
        chkMasalah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMasalah);
        chkMasalah.setBounds(145, 291, 150, 23);

        chkLeherPendek.setBackground(new java.awt.Color(255, 255, 250));
        chkLeherPendek.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLeherPendek.setForeground(new java.awt.Color(0, 0, 0));
        chkLeherPendek.setText("Leher Pendek");
        chkLeherPendek.setBorderPainted(true);
        chkLeherPendek.setBorderPaintedFlat(true);
        chkLeherPendek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLeherPendek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLeherPendek.setName("chkLeherPendek"); // NOI18N
        chkLeherPendek.setOpaque(false);
        chkLeherPendek.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLeherPendek);
        chkLeherPendek.setBounds(145, 319, 100, 23);

        chkStrok.setBackground(new java.awt.Color(255, 255, 250));
        chkStrok.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkStrok.setForeground(new java.awt.Color(0, 0, 0));
        chkStrok.setText("Stroke");
        chkStrok.setBorderPainted(true);
        chkStrok.setBorderPaintedFlat(true);
        chkStrok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkStrok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkStrok.setName("chkStrok"); // NOI18N
        chkStrok.setOpaque(false);
        chkStrok.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkStrok);
        chkStrok.setBounds(145, 347, 70, 23);

        chkSesakNafas.setBackground(new java.awt.Color(255, 255, 250));
        chkSesakNafas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSesakNafas.setForeground(new java.awt.Color(0, 0, 0));
        chkSesakNafas.setText("Sesak Nafas");
        chkSesakNafas.setBorderPainted(true);
        chkSesakNafas.setBorderPaintedFlat(true);
        chkSesakNafas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSesakNafas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSesakNafas.setName("chkSesakNafas"); // NOI18N
        chkSesakNafas.setOpaque(false);
        chkSesakNafas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSesakNafas);
        chkSesakNafas.setBounds(145, 375, 100, 23);

        chkSakitDada.setBackground(new java.awt.Color(255, 255, 250));
        chkSakitDada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSakitDada.setForeground(new java.awt.Color(0, 0, 0));
        chkSakitDada.setText("Sakit Dada");
        chkSakitDada.setBorderPainted(true);
        chkSakitDada.setBorderPaintedFlat(true);
        chkSakitDada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSakitDada.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSakitDada.setName("chkSakitDada"); // NOI18N
        chkSakitDada.setOpaque(false);
        chkSakitDada.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSakitDada);
        chkSakitDada.setBounds(330, 263, 90, 23);

        chkDenyut.setBackground(new java.awt.Color(255, 255, 250));
        chkDenyut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDenyut.setForeground(new java.awt.Color(0, 0, 0));
        chkDenyut.setText("Denyut Jantung Tidak Normal");
        chkDenyut.setBorderPainted(true);
        chkDenyut.setBorderPaintedFlat(true);
        chkDenyut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDenyut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDenyut.setName("chkDenyut"); // NOI18N
        chkDenyut.setOpaque(false);
        chkDenyut.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDenyut);
        chkDenyut.setBounds(330, 291, 180, 23);

        chkSedangHamil.setBackground(new java.awt.Color(255, 255, 250));
        chkSedangHamil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSedangHamil.setForeground(new java.awt.Color(0, 0, 0));
        chkSedangHamil.setText("Sedang Hamil");
        chkSedangHamil.setBorderPainted(true);
        chkSedangHamil.setBorderPaintedFlat(true);
        chkSedangHamil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSedangHamil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSedangHamil.setName("chkSedangHamil"); // NOI18N
        chkSedangHamil.setOpaque(false);
        chkSedangHamil.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSedangHamil);
        chkSedangHamil.setBounds(330, 319, 100, 23);

        chkKejang.setBackground(new java.awt.Color(255, 255, 250));
        chkKejang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKejang.setForeground(new java.awt.Color(0, 0, 0));
        chkKejang.setText("Kejang");
        chkKejang.setBorderPainted(true);
        chkKejang.setBorderPaintedFlat(true);
        chkKejang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKejang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKejang.setName("chkKejang"); // NOI18N
        chkKejang.setOpaque(false);
        chkKejang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKejang);
        chkKejang.setBounds(330, 347, 70, 23);

        chkObesitas.setBackground(new java.awt.Color(255, 255, 250));
        chkObesitas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkObesitas.setForeground(new java.awt.Color(0, 0, 0));
        chkObesitas.setText("Obesitas");
        chkObesitas.setBorderPainted(true);
        chkObesitas.setBorderPaintedFlat(true);
        chkObesitas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkObesitas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkObesitas.setName("chkObesitas"); // NOI18N
        chkObesitas.setOpaque(false);
        chkObesitas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkObesitas);
        chkObesitas.setBounds(330, 375, 80, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("PEMERIKSAAN FISIK :");
        jLabel108.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(0, 403, 140, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("GCS :");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(145, 403, 50, 23);

        Tgcs.setForeground(new java.awt.Color(0, 0, 0));
        Tgcs.setName("Tgcs"); // NOI18N
        Tgcs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsKeyPressed(evt);
            }
        });
        FormInput.add(Tgcs);
        Tgcs.setBounds(200, 403, 70, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("TB :");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(270, 403, 50, 23);

        Ttb.setForeground(new java.awt.Color(0, 0, 0));
        Ttb.setName("Ttb"); // NOI18N
        Ttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbKeyPressed(evt);
            }
        });
        FormInput.add(Ttb);
        Ttb.setBounds(325, 403, 70, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("Cm.");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(400, 403, 30, 23);

        chkBukaMulut.setBackground(new java.awt.Color(255, 255, 250));
        chkBukaMulut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBukaMulut.setForeground(new java.awt.Color(0, 0, 0));
        chkBukaMulut.setText("Buka Mulut > 2 Jari");
        chkBukaMulut.setBorderPainted(true);
        chkBukaMulut.setBorderPaintedFlat(true);
        chkBukaMulut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBukaMulut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBukaMulut.setName("chkBukaMulut"); // NOI18N
        chkBukaMulut.setOpaque(false);
        chkBukaMulut.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBukaMulut);
        chkBukaMulut.setBounds(145, 487, 120, 23);

        chkJarak.setBackground(new java.awt.Color(255, 255, 250));
        chkJarak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJarak.setForeground(new java.awt.Color(0, 0, 0));
        chkJarak.setText("Jarak Thyromental > 3 Jari");
        chkJarak.setBorderPainted(true);
        chkJarak.setBorderPaintedFlat(true);
        chkJarak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJarak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJarak.setName("chkJarak"); // NOI18N
        chkJarak.setOpaque(false);
        chkJarak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkJarak);
        chkJarak.setBounds(145, 515, 160, 23);

        chkGerakan.setBackground(new java.awt.Color(255, 255, 250));
        chkGerakan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGerakan.setForeground(new java.awt.Color(0, 0, 0));
        chkGerakan.setText("Gerakan Leher Maksimal");
        chkGerakan.setBorderPainted(true);
        chkGerakan.setBorderPaintedFlat(true);
        chkGerakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGerakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGerakan.setName("chkGerakan"); // NOI18N
        chkGerakan.setOpaque(false);
        chkGerakan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGerakan);
        chkGerakan.setBounds(320, 487, 150, 23);

        chkGigiPalsu.setBackground(new java.awt.Color(255, 255, 250));
        chkGigiPalsu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGigiPalsu.setForeground(new java.awt.Color(0, 0, 0));
        chkGigiPalsu.setText("Gigi Palsu");
        chkGigiPalsu.setBorderPainted(true);
        chkGigiPalsu.setBorderPaintedFlat(true);
        chkGigiPalsu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGigiPalsu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGigiPalsu.setName("chkGigiPalsu"); // NOI18N
        chkGigiPalsu.setOpaque(false);
        chkGigiPalsu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGigiPalsu);
        chkGigiPalsu.setBounds(320, 515, 80, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("Mallampathy :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(480, 487, 80, 23);

        cmbMallam.setBackground(new java.awt.Color(245, 253, 240));
        cmbMallam.setForeground(new java.awt.Color(0, 0, 0));
        cmbMallam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "I", "II", "III", "IV" }));
        cmbMallam.setLightWeightPopupEnabled(false);
        cmbMallam.setName("cmbMallam"); // NOI18N
        FormInput.add(cmbMallam);
        cmbMallam.setBounds(566, 487, 45, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("KEADAAN UMUM :");
        jLabel116.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(0, 543, 140, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("Kepala :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(145, 543, 80, 23);

        Tkepala.setForeground(new java.awt.Color(0, 0, 0));
        Tkepala.setName("Tkepala"); // NOI18N
        Tkepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkepalaKeyPressed(evt);
            }
        });
        FormInput.add(Tkepala);
        Tkepala.setBounds(230, 543, 530, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("Sklera :");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(145, 571, 80, 23);

        Tsklera.setForeground(new java.awt.Color(0, 0, 0));
        Tsklera.setName("Tsklera"); // NOI18N
        Tsklera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskleraKeyPressed(evt);
            }
        });
        FormInput.add(Tsklera);
        Tsklera.setBounds(230, 571, 530, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("Conjungtiva :");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(145, 599, 80, 23);

        Tconjung.setForeground(new java.awt.Color(0, 0, 0));
        Tconjung.setName("Tconjung"); // NOI18N
        Tconjung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TconjungKeyPressed(evt);
            }
        });
        FormInput.add(Tconjung);
        Tconjung.setBounds(230, 599, 530, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("Leher :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(145, 627, 80, 23);

        Tleher.setForeground(new java.awt.Color(0, 0, 0));
        Tleher.setName("Tleher"); // NOI18N
        Tleher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TleherKeyPressed(evt);
            }
        });
        FormInput.add(Tleher);
        Tleher.setBounds(230, 627, 530, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("Paru - paru :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(145, 655, 80, 23);

        Tparu.setForeground(new java.awt.Color(0, 0, 0));
        Tparu.setName("Tparu"); // NOI18N
        Tparu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TparuKeyPressed(evt);
            }
        });
        FormInput.add(Tparu);
        Tparu.setBounds(230, 655, 530, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("Jantung :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(145, 683, 80, 23);

        Tjantung.setForeground(new java.awt.Color(0, 0, 0));
        Tjantung.setName("Tjantung"); // NOI18N
        Tjantung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjantungKeyPressed(evt);
            }
        });
        FormInput.add(Tjantung);
        Tjantung.setBounds(230, 683, 530, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("Abdomen :");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(145, 711, 80, 23);

        Tabdomen.setForeground(new java.awt.Color(0, 0, 0));
        Tabdomen.setName("Tabdomen"); // NOI18N
        Tabdomen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TabdomenKeyPressed(evt);
            }
        });
        FormInput.add(Tabdomen);
        Tabdomen.setBounds(230, 711, 530, 23);

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setText("Ekstremitas :");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(145, 739, 80, 23);

        Tekstremitas.setForeground(new java.awt.Color(0, 0, 0));
        Tekstremitas.setName("Tekstremitas"); // NOI18N
        Tekstremitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TekstremitasKeyPressed(evt);
            }
        });
        FormInput.add(Tekstremitas);
        Tekstremitas.setBounds(230, 739, 530, 23);

        scrollPane17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane17.setName("scrollPane17"); // NOI18N

        Tdiagnosis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tdiagnosis.setColumns(20);
        Tdiagnosis.setRows(5);
        Tdiagnosis.setName("Tdiagnosis"); // NOI18N
        Tdiagnosis.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tdiagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosisKeyPressed(evt);
            }
        });
        scrollPane17.setViewportView(Tdiagnosis);

        FormInput.add(scrollPane17);
        scrollPane17.setBounds(145, 767, 615, 60);

        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setText("Diagnosis :");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(0, 767, 140, 23);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setText("ASA CLASSIFICATION :");
        jLabel125.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(0, 897, 140, 23);

        chkAsa1.setBackground(new java.awt.Color(255, 255, 250));
        chkAsa1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsa1.setForeground(new java.awt.Color(0, 0, 0));
        chkAsa1.setText("ASA 1 Pasien Normal Yang Sehat");
        chkAsa1.setBorderPainted(true);
        chkAsa1.setBorderPaintedFlat(true);
        chkAsa1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsa1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsa1.setName("chkAsa1"); // NOI18N
        chkAsa1.setOpaque(false);
        chkAsa1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAsa1);
        chkAsa1.setBounds(145, 897, 190, 23);

        chkAsa2.setBackground(new java.awt.Color(255, 255, 250));
        chkAsa2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsa2.setForeground(new java.awt.Color(0, 0, 0));
        chkAsa2.setText("ASA 2 Pasien Dengan Penyakit Sistemik Ringan");
        chkAsa2.setBorderPainted(true);
        chkAsa2.setBorderPaintedFlat(true);
        chkAsa2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsa2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsa2.setName("chkAsa2"); // NOI18N
        chkAsa2.setOpaque(false);
        chkAsa2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAsa2);
        chkAsa2.setBounds(145, 925, 260, 23);

        chkAsa3.setBackground(new java.awt.Color(255, 255, 250));
        chkAsa3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsa3.setForeground(new java.awt.Color(0, 0, 0));
        chkAsa3.setText("ASA 3 Pasien Dengan Penyakit Sistemik Berat");
        chkAsa3.setBorderPainted(true);
        chkAsa3.setBorderPaintedFlat(true);
        chkAsa3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsa3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsa3.setName("chkAsa3"); // NOI18N
        chkAsa3.setOpaque(false);
        chkAsa3.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAsa3);
        chkAsa3.setBounds(145, 953, 260, 23);

        chkAsa4.setBackground(new java.awt.Color(255, 255, 250));
        chkAsa4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsa4.setForeground(new java.awt.Color(0, 0, 0));
        chkAsa4.setBorderPainted(true);
        chkAsa4.setBorderPaintedFlat(true);
        chkAsa4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsa4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsa4.setName("chkAsa4"); // NOI18N
        chkAsa4.setOpaque(false);
        chkAsa4.setPreferredSize(new java.awt.Dimension(175, 23));
        chkAsa4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAsa4ActionPerformed(evt);
            }
        });
        FormInput.add(chkAsa4);
        chkAsa4.setBounds(145, 981, 20, 23);

        TketAsa4.setForeground(new java.awt.Color(0, 0, 0));
        TketAsa4.setName("TketAsa4"); // NOI18N
        TketAsa4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketAsa4KeyPressed(evt);
            }
        });
        FormInput.add(TketAsa4);
        TketAsa4.setBounds(170, 981, 220, 23);

        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel126.setText("ASA 4 Pasien Dengan Penyakit Sistemik Berat Yang Mengancam Nyawa");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(395, 981, 350, 23);

        chkEmergency.setBackground(new java.awt.Color(255, 255, 250));
        chkEmergency.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEmergency.setForeground(new java.awt.Color(0, 0, 0));
        chkEmergency.setText("EMERGENCY");
        chkEmergency.setBorderPainted(true);
        chkEmergency.setBorderPaintedFlat(true);
        chkEmergency.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEmergency.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEmergency.setName("chkEmergency"); // NOI18N
        chkEmergency.setOpaque(false);
        chkEmergency.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkEmergency);
        chkEmergency.setBounds(145, 1009, 90, 23);

        scrollPane18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane18.setName("scrollPane18"); // NOI18N

        TkesAnestesi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TkesAnestesi.setColumns(20);
        TkesAnestesi.setRows(5);
        TkesAnestesi.setName("TkesAnestesi"); // NOI18N
        TkesAnestesi.setPreferredSize(new java.awt.Dimension(162, 2000));
        TkesAnestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesAnestesiKeyPressed(evt);
            }
        });
        scrollPane18.setViewportView(TkesAnestesi);

        FormInput.add(scrollPane18);
        scrollPane18.setBounds(145, 1261, 615, 60);

        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setText("Kesimpulan Anestesi :");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(0, 1261, 140, 23);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setText("PERENCANAAN ANESTESIA :");
        jLabel130.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(0, 1037, 180, 23);

        jLabel131.setForeground(new java.awt.Color(0, 0, 0));
        jLabel131.setText("Teknis Anestesia Dan Sedasi :");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(0, 1065, 180, 23);

        jLabel132.setForeground(new java.awt.Color(0, 0, 0));
        jLabel132.setText("Sedasi :");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(185, 1065, 80, 23);

        jLabel133.setForeground(new java.awt.Color(0, 0, 0));
        jLabel133.setText("Obat (Dosis & Rute) : 1.");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(272, 1065, 130, 23);

        Tsedasi1.setForeground(new java.awt.Color(0, 0, 0));
        Tsedasi1.setName("Tsedasi1"); // NOI18N
        Tsedasi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tsedasi1KeyPressed(evt);
            }
        });
        FormInput.add(Tsedasi1);
        Tsedasi1.setBounds(405, 1065, 355, 23);

        jLabel134.setForeground(new java.awt.Color(0, 0, 0));
        jLabel134.setText("2.");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(372, 1093, 30, 23);

        Tsedasi2.setForeground(new java.awt.Color(0, 0, 0));
        Tsedasi2.setName("Tsedasi2"); // NOI18N
        Tsedasi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tsedasi2KeyPressed(evt);
            }
        });
        FormInput.add(Tsedasi2);
        Tsedasi2.setBounds(405, 1093, 355, 23);

        jLabel135.setForeground(new java.awt.Color(0, 0, 0));
        jLabel135.setText("3.");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(372, 1121, 30, 23);

        Tsedasi3.setForeground(new java.awt.Color(0, 0, 0));
        Tsedasi3.setName("Tsedasi3"); // NOI18N
        Tsedasi3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tsedasi3KeyPressed(evt);
            }
        });
        FormInput.add(Tsedasi3);
        Tsedasi3.setBounds(405, 1121, 355, 23);

        jLabel136.setForeground(new java.awt.Color(0, 0, 0));
        jLabel136.setText("GA :");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(185, 1149, 80, 23);

        Tga.setForeground(new java.awt.Color(0, 0, 0));
        Tga.setName("Tga"); // NOI18N
        Tga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgaKeyPressed(evt);
            }
        });
        FormInput.add(Tga);
        Tga.setBounds(270, 1149, 490, 23);

        jLabel137.setForeground(new java.awt.Color(0, 0, 0));
        jLabel137.setText("Regional :");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(185, 1177, 80, 23);

        chkSpinal.setBackground(new java.awt.Color(255, 255, 250));
        chkSpinal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSpinal.setForeground(new java.awt.Color(0, 0, 0));
        chkSpinal.setText("Spinal");
        chkSpinal.setBorderPainted(true);
        chkSpinal.setBorderPaintedFlat(true);
        chkSpinal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSpinal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSpinal.setName("chkSpinal"); // NOI18N
        chkSpinal.setOpaque(false);
        chkSpinal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSpinal);
        chkSpinal.setBounds(270, 1177, 60, 23);

        chkEpidural.setBackground(new java.awt.Color(255, 255, 250));
        chkEpidural.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEpidural.setForeground(new java.awt.Color(0, 0, 0));
        chkEpidural.setText("Epidural");
        chkEpidural.setBorderPainted(true);
        chkEpidural.setBorderPaintedFlat(true);
        chkEpidural.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEpidural.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEpidural.setName("chkEpidural"); // NOI18N
        chkEpidural.setOpaque(false);
        chkEpidural.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkEpidural);
        chkEpidural.setBounds(340, 1177, 70, 23);

        chkKaudal.setBackground(new java.awt.Color(255, 255, 250));
        chkKaudal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKaudal.setForeground(new java.awt.Color(0, 0, 0));
        chkKaudal.setText("Kaudal");
        chkKaudal.setBorderPainted(true);
        chkKaudal.setBorderPaintedFlat(true);
        chkKaudal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKaudal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKaudal.setName("chkKaudal"); // NOI18N
        chkKaudal.setOpaque(false);
        chkKaudal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKaudal);
        chkKaudal.setBounds(420, 1177, 60, 23);

        chkBlok.setBackground(new java.awt.Color(255, 255, 250));
        chkBlok.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBlok.setForeground(new java.awt.Color(0, 0, 0));
        chkBlok.setText("Blok Perifer");
        chkBlok.setBorderPainted(true);
        chkBlok.setBorderPaintedFlat(true);
        chkBlok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBlok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBlok.setName("chkBlok"); // NOI18N
        chkBlok.setOpaque(false);
        chkBlok.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBlok);
        chkBlok.setBounds(495, 1177, 90, 23);

        jLabel138.setForeground(new java.awt.Color(0, 0, 0));
        jLabel138.setText("Monitoring :");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(185, 1205, 80, 23);

        chkEkg.setBackground(new java.awt.Color(255, 255, 250));
        chkEkg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEkg.setForeground(new java.awt.Color(0, 0, 0));
        chkEkg.setText("EKG");
        chkEkg.setBorderPainted(true);
        chkEkg.setBorderPaintedFlat(true);
        chkEkg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEkg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEkg.setName("chkEkg"); // NOI18N
        chkEkg.setOpaque(false);
        chkEkg.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkEkg);
        chkEkg.setBounds(270, 1205, 50, 23);

        chkSpo2.setBackground(new java.awt.Color(255, 255, 250));
        chkSpo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSpo2.setForeground(new java.awt.Color(0, 0, 0));
        chkSpo2.setText("SpO2");
        chkSpo2.setBorderPainted(true);
        chkSpo2.setBorderPaintedFlat(true);
        chkSpo2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSpo2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSpo2.setName("chkSpo2"); // NOI18N
        chkSpo2.setOpaque(false);
        chkSpo2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSpo2);
        chkSpo2.setBounds(330, 1205, 55, 23);

        chkNibp.setBackground(new java.awt.Color(255, 255, 250));
        chkNibp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNibp.setForeground(new java.awt.Color(0, 0, 0));
        chkNibp.setText("NIBP");
        chkNibp.setBorderPainted(true);
        chkNibp.setBorderPaintedFlat(true);
        chkNibp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNibp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNibp.setName("chkNibp"); // NOI18N
        chkNibp.setOpaque(false);
        chkNibp.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNibp);
        chkNibp.setBounds(395, 1205, 55, 23);

        chkTemp.setBackground(new java.awt.Color(255, 255, 250));
        chkTemp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTemp.setForeground(new java.awt.Color(0, 0, 0));
        chkTemp.setText("Temp");
        chkTemp.setBorderPainted(true);
        chkTemp.setBorderPaintedFlat(true);
        chkTemp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTemp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTemp.setName("chkTemp"); // NOI18N
        chkTemp.setOpaque(false);
        chkTemp.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTemp);
        chkTemp.setBounds(460, 1205, 60, 23);

        chkLain.setBackground(new java.awt.Color(255, 255, 250));
        chkLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLain.setForeground(new java.awt.Color(0, 0, 0));
        chkLain.setText("Lain - lain :");
        chkLain.setBorderPainted(true);
        chkLain.setBorderPaintedFlat(true);
        chkLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLain.setName("chkLain"); // NOI18N
        chkLain.setOpaque(false);
        chkLain.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainActionPerformed(evt);
            }
        });
        FormInput.add(chkLain);
        chkLain.setBounds(525, 1205, 75, 23);

        TketLain.setForeground(new java.awt.Color(0, 0, 0));
        TketLain.setName("TketLain"); // NOI18N
        TketLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketLainKeyPressed(evt);
            }
        });
        FormInput.add(TketLain);
        TketLain.setBounds(600, 1205, 160, 23);

        jLabel139.setForeground(new java.awt.Color(0, 0, 0));
        jLabel139.setText("Perawatan Pasca Anestesia :");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(105, 1233, 160, 23);

        jLabel140.setForeground(new java.awt.Color(0, 0, 0));
        jLabel140.setText("PERSIAPAN PRA ANESTESIA :");
        jLabel140.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(0, 1327, 180, 23);

        chkPuasa.setBackground(new java.awt.Color(255, 255, 250));
        chkPuasa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPuasa.setForeground(new java.awt.Color(0, 0, 0));
        chkPuasa.setText("Puasa Mulai : Jam");
        chkPuasa.setBorderPainted(true);
        chkPuasa.setBorderPaintedFlat(true);
        chkPuasa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPuasa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPuasa.setName("chkPuasa"); // NOI18N
        chkPuasa.setOpaque(false);
        chkPuasa.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPuasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPuasaActionPerformed(evt);
            }
        });
        FormInput.add(chkPuasa);
        chkPuasa.setBounds(190, 1327, 110, 23);

        chkRencana.setBackground(new java.awt.Color(255, 255, 250));
        chkRencana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRencana.setForeground(new java.awt.Color(0, 0, 0));
        chkRencana.setText("Rencana Operasi : Jam");
        chkRencana.setBorderPainted(true);
        chkRencana.setBorderPaintedFlat(true);
        chkRencana.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRencana.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRencana.setName("chkRencana"); // NOI18N
        chkRencana.setOpaque(false);
        chkRencana.setPreferredSize(new java.awt.Dimension(175, 23));
        chkRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRencanaActionPerformed(evt);
            }
        });
        FormInput.add(chkRencana);
        chkRencana.setBounds(190, 1355, 135, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(330, 1355, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(382, 1355, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cmbDtk2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(435, 1355, 45, 23);

        jLabel277.setForeground(new java.awt.Color(0, 0, 0));
        jLabel277.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel277.setText("Wita     Tanggal :");
        jLabel277.setName("jLabel277"); // NOI18N
        FormInput.add(jLabel277);
        jLabel277.setBounds(485, 1355, 85, 23);

        TtglRencana.setEditable(false);
        TtglRencana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-06-2026" }));
        TtglRencana.setDisplayFormat("dd-MM-yyyy");
        TtglRencana.setName("TtglRencana"); // NOI18N
        TtglRencana.setOpaque(false);
        TtglRencana.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglRencana);
        TtglRencana.setBounds(574, 1355, 90, 23);

        scrollPane19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane19.setName("scrollPane19"); // NOI18N

        Tinstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinstruksi.setColumns(20);
        Tinstruksi.setRows(5);
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tinstruksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinstruksiKeyPressed(evt);
            }
        });
        scrollPane19.setViewportView(Tinstruksi);

        FormInput.add(scrollPane19);
        scrollPane19.setBounds(145, 1383, 615, 60);

        jLabel141.setForeground(new java.awt.Color(0, 0, 0));
        jLabel141.setText("Instruksi Pra Anestesia :");
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(0, 1383, 140, 23);

        jLabel142.setForeground(new java.awt.Color(0, 0, 0));
        jLabel142.setText("CATATAN :");
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput.add(jLabel142);
        jLabel142.setBounds(0, 1449, 140, 23);

        scrollPane20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane20.setName("scrollPane20"); // NOI18N

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
        scrollPane20.setViewportView(Tcatatan);

        FormInput.add(scrollPane20);
        scrollPane20.setBounds(145, 1449, 615, 60);

        jLabel143.setForeground(new java.awt.Color(0, 0, 0));
        jLabel143.setText("Dokter Anestesi :");
        jLabel143.setName("jLabel143"); // NOI18N
        FormInput.add(jLabel143);
        jLabel143.setBounds(0, 1515, 140, 23);

        scrollPane21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane21.setName("scrollPane21"); // NOI18N

        TrencanaTindakan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TrencanaTindakan.setColumns(20);
        TrencanaTindakan.setRows(5);
        TrencanaTindakan.setName("TrencanaTindakan"); // NOI18N
        TrencanaTindakan.setPreferredSize(new java.awt.Dimension(162, 2000));
        TrencanaTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrencanaTindakanKeyPressed(evt);
            }
        });
        scrollPane21.setViewportView(TrencanaTindakan);

        FormInput.add(scrollPane21);
        scrollPane21.setBounds(145, 832, 615, 60);

        jLabel144.setForeground(new java.awt.Color(0, 0, 0));
        jLabel144.setText("Rencana Tindakan :");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput.add(jLabel144);
        jLabel144.setBounds(0, 832, 140, 23);

        BtnRiwAlergi.setForeground(new java.awt.Color(0, 0, 0));
        BtnRiwAlergi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnRiwAlergi.setMnemonic('2');
        BtnRiwAlergi.setText("Template");
        BtnRiwAlergi.setName("BtnRiwAlergi"); // NOI18N
        BtnRiwAlergi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnRiwAlergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRiwAlergiActionPerformed(evt);
            }
        });
        FormInput.add(BtnRiwAlergi);
        BtnRiwAlergi.setBounds(770, 66, 100, 23);

        BtnRiwAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        BtnRiwAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnRiwAnestesi.setMnemonic('2');
        BtnRiwAnestesi.setText("Template");
        BtnRiwAnestesi.setName("BtnRiwAnestesi"); // NOI18N
        BtnRiwAnestesi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnRiwAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRiwAnestesiActionPerformed(evt);
            }
        });
        FormInput.add(BtnRiwAnestesi);
        BtnRiwAnestesi.setBounds(770, 131, 100, 23);

        BtnObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnObat.setMnemonic('2');
        BtnObat.setText("Template");
        BtnObat.setName("BtnObat"); // NOI18N
        BtnObat.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnObatActionPerformed(evt);
            }
        });
        FormInput.add(BtnObat);
        BtnObat.setBounds(770, 197, 100, 23);

        BtnDiagnosis.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagnosis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagnosis.setMnemonic('2');
        BtnDiagnosis.setText("Template");
        BtnDiagnosis.setName("BtnDiagnosis"); // NOI18N
        BtnDiagnosis.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDiagnosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagnosisActionPerformed(evt);
            }
        });
        FormInput.add(BtnDiagnosis);
        BtnDiagnosis.setBounds(770, 767, 100, 23);

        BtnRencanaTindakan.setForeground(new java.awt.Color(0, 0, 0));
        BtnRencanaTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnRencanaTindakan.setMnemonic('2');
        BtnRencanaTindakan.setText("Template");
        BtnRencanaTindakan.setName("BtnRencanaTindakan"); // NOI18N
        BtnRencanaTindakan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnRencanaTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRencanaTindakanActionPerformed(evt);
            }
        });
        FormInput.add(BtnRencanaTindakan);
        BtnRencanaTindakan.setBounds(770, 832, 100, 23);

        BtnKesimpulan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKesimpulan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKesimpulan.setMnemonic('2');
        BtnKesimpulan.setText("Template");
        BtnKesimpulan.setName("BtnKesimpulan"); // NOI18N
        BtnKesimpulan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnKesimpulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKesimpulanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKesimpulan);
        BtnKesimpulan.setBounds(770, 1261, 100, 23);

        BtnInstruksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnInstruksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnInstruksi.setMnemonic('2');
        BtnInstruksi.setText("Template");
        BtnInstruksi.setName("BtnInstruksi"); // NOI18N
        BtnInstruksi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnInstruksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInstruksiActionPerformed(evt);
            }
        });
        FormInput.add(BtnInstruksi);
        BtnInstruksi.setBounds(770, 1383, 100, 23);

        BtnCatatan.setForeground(new java.awt.Color(0, 0, 0));
        BtnCatatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnCatatan.setMnemonic('2');
        BtnCatatan.setText("Template");
        BtnCatatan.setName("BtnCatatan"); // NOI18N
        BtnCatatan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCatatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCatatanActionPerformed(evt);
            }
        });
        FormInput.add(BtnCatatan);
        BtnCatatan.setBounds(770, 1449, 100, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass9.add(Scroll1, java.awt.BorderLayout.CENTER);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Asesmen Pra Sedasi Konsep IAR ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(900, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbAsesmen.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbAsesmen.setName("tbAsesmen"); // NOI18N
        tbAsesmen.getTableHeader().setReorderingAllowed(false);
        tbAsesmen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAsesmenMouseClicked(evt);
            }
        });
        tbAsesmen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAsesmenKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbAsesmen);

        PanelInput1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 86));
        panelGlass11.setLayout(new java.awt.BorderLayout());

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Simpan Data :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass12.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-06-2026" }));
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

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-06-2026" }));
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

        panelGlass9.add(PanelInput1, java.awt.BorderLayout.EAST);

        internalFrame1.add(panelGlass9, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            cekData();
            if (Sequel.menyimpantf("asesmen_pra_sedasi_konsep_iar", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 71, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), TriwayatAlergi.getText(), TriwayatAnestesi.getText(), TobatSaatIni.getText(), hilang, masalah, leher, strok,
                        sesak, sakit, denyut, sedang, kejang, obes, Tgcs.getText(), Ttb.getText(), Ttd.getText(), Tbb.getText(), Trr.getText(), Tnadi.getText(), Tvas.getText(),
                        Tsuhu.getText(), buka, jarak, gerakan, gigi, cmbMallam.getSelectedItem().toString(), Tkepala.getText(), Tsklera.getText(), Tconjung.getText(), Tleher.getText(),
                        Tparu.getText(), Tjantung.getText(), Tabdomen.getText(), Tekstremitas.getText(), Tdiagnosis.getText(), TrencanaTindakan.getText(), asa1, asa2, asa3, asa4,
                        TketAsa4.getText(), emer, Tsedasi1.getText(), Tsedasi2.getText(), Tsedasi3.getText(), Tga.getText(), spinal, epid, kaudal, blok, ekg, spo2, nibp, temp, lain,
                        TketLain.getText(), cmbPerawatan.getSelectedItem().toString(), cmbRuangKhusus.getSelectedItem().toString(), TkesAnestesi.getText(), puasa,
                        cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), Valid.SetTgl(TtglPuasa.getSelectedItem() + ""), rencana,
                        cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), Valid.SetTgl(TtglRencana.getSelectedItem() + ""),
                        Tinstruksi.getText(), Tcatatan.getText(), nipDokter, Sequel.cariIsi("select now()")
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Asesmen Pra Sedasi Konsep IAR", "Simpan");
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
            if (tbAsesmen.getSelectedRow() > -1) {
                cekData();
                if (Sequel.mengedittf("asesmen_pra_sedasi_konsep_iar", "waktu_simpan=?", "riwayat_alergi=?, riwayat_anestesi=?, obat_saat_ini=?, hilangnya_gigi=?, "
                        + "masalah_mobilisasi=?, leher_pendek=?, stroke=?, sesak_nafas=?, sakit_dada=?, denyut_jantung=?, sedang_hamil=?, kejang=?, obesitas=?, gcs=?, "
                        + "tb=?, td=?, bb=?, rr=?, nadi=?, vas=?, suhu=?, buka_mulut=?, jarak=?, gerakan_leher=?, gigi_palsu=?, mallampathy=?, kepala=?, sklera=?, conjungtiva=?, "
                        + "leher=?, paru_paru=?, jantung=?, abdomen=?, extremitas=?, diagnosis=?, rencana_tindakan=?, asa1=?, asa2=?, asa3=?, asa4=?, ket_asa4=?, emergency=?, "
                        + "sedasi_obat1=?, sedasi_obat2=?, sedasi_obat3=?, ga=?, spinal=?, epidural=?, kaudal=?, blok=?, ekg=?, spo2=?, nibp=?, temp=?, lain_lain=?, ket_lain=?, "
                        + "perawatan_pasca=?, rawat_khusus=?, kesimpulan_anestesi=?, cek_puasa=?, jam_puasa=?, tgl_puasa=?, cek_rencana=?, jam_rencana=?, tgl_rencana=?, instruksi=?, "
                        + "catatan=?, nip_anestesi=?", 69, new String[]{
                            TriwayatAlergi.getText(), TriwayatAnestesi.getText(), TobatSaatIni.getText(), hilang, masalah, leher, strok,
                            sesak, sakit, denyut, sedang, kejang, obes, Tgcs.getText(), Ttb.getText(), Ttd.getText(), Tbb.getText(), Trr.getText(), Tnadi.getText(), Tvas.getText(),
                            Tsuhu.getText(), buka, jarak, gerakan, gigi, cmbMallam.getSelectedItem().toString(), Tkepala.getText(), Tsklera.getText(), Tconjung.getText(), Tleher.getText(),
                            Tparu.getText(), Tjantung.getText(), Tabdomen.getText(), Tekstremitas.getText(), Tdiagnosis.getText(), TrencanaTindakan.getText(), asa1, asa2, asa3, asa4,
                            TketAsa4.getText(), emer, Tsedasi1.getText(), Tsedasi2.getText(), Tsedasi3.getText(), Tga.getText(), spinal, epid, kaudal, blok, ekg, spo2, nibp, temp, lain,
                            TketLain.getText(), cmbPerawatan.getSelectedItem().toString(), cmbRuangKhusus.getSelectedItem().toString(), TkesAnestesi.getText(), puasa,
                            cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), Valid.SetTgl(TtglPuasa.getSelectedItem() + ""), rencana,
                            cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), Valid.SetTgl(TtglRencana.getSelectedItem() + ""),
                            Tinstruksi.getText(), Tcatatan.getText(), nipDokter,
                            tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString()
                        }) == true) {

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Asesmen Pra Sedasi Konsep IAR", "Ganti");
                    TCari.setText(TNoRw.getText());
                    tampil();
                    emptTeks();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                tbAsesmen.requestFocus();
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
        WindowTemplate.dispose();
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

    private void tbAsesmenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAsesmenMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbAsesmenMouseClicked

    private void tbAsesmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAsesmenKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbAsesmenKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from asesmen_pra_sedasi_konsep_iar where waktu_simpan=?", 1, new String[]{
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString()
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
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbAsesmen.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
            param.put("ruangRwt", TrgRawat.getText());
            param.put("jamRuang", cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " WITA");
            
            if (TriwayatAlergi.getText().equals("")) {
                param.put("riwayatAlergi", "...........................");
            } else {
                param.put("riwayatAlergi", TriwayatAlergi.getText());
            }
            
            if (TriwayatAnestesi.getText().equals("")) {
                param.put("riwayatAnestesi", "...........................");
            } else {
                param.put("riwayatAnestesi", TriwayatAnestesi.getText());
            }
            
            if (TobatSaatIni.getText().equals("")) {
                param.put("obat", "...........................");
            } else {
                param.put("obat", TobatSaatIni.getText());
            }
            
            if (chkHilangGigi.isSelected() == true) {
                param.put("hilang", "V");
            } else {
                param.put("hilang", "");
            }
            
            if (chkMasalah.isSelected() == true) {
                param.put("masalah", "V");
            } else {
                param.put("masalah", "");
            }
            
            if (chkLeherPendek.isSelected() == true) {
                param.put("leherPendek", "V");
            } else {
                param.put("leherPendek", "");
            }
            
            if (chkStrok.isSelected() == true) {
                param.put("strok", "V");
            } else {
                param.put("strok", "");
            }
            
            if (chkSesakNafas.isSelected() == true) {
                param.put("sesak", "V");
            } else {
                param.put("sesak", "");
            }
            
            if (chkSakitDada.isSelected() == true) {
                param.put("sakit", "V");
            } else {
                param.put("sakit", "");
            }
            
            if (chkDenyut.isSelected() == true) {
                param.put("denyut", "V");
            } else {
                param.put("denyut", "");
            }
            
            if (chkSedangHamil.isSelected() == true) {
                param.put("sedang", "V");
            } else {
                param.put("sedang", "");
            }
            
            if (chkKejang.isSelected() == true) {
                param.put("kejang", "V");
            } else {
                param.put("kejang", "");
            }
            
            if (chkObesitas.isSelected() == true) {
                param.put("obes", "V");
            } else {
                param.put("obes", "");
            }
            
            if (Tgcs.getText().equals("")) {
                param.put("gcs", ".........");
            } else {
                param.put("gcs", Tgcs.getText());
            }
            
            if (Ttb.getText().equals("")) {
                param.put("tb", "......... Cm.");
            } else {
                param.put("tb", Ttb.getText() + " Cm.");
            }
            
            if (Ttd.getText().equals("")) {
                param.put("td", "......... mmHg");
            } else {
                param.put("td", Ttd.getText() + " mmHg");
            }
            
            if (Tbb.getText().equals("")) {
                param.put("bb", "......... Kg.");
            } else {
                param.put("bb", Tbb.getText() + " Kg.");
            }
            
            if (Trr.getText().equals("")) {
                param.put("rr", "......... x/menit");
            } else {
                param.put("rr", Trr.getText() + " x/menit");
            }
            
            if (Tnadi.getText().equals("")) {
                param.put("nadi", "......... x/menit");
            } else {
                param.put("nadi", Tnadi.getText() + " x/menit");
            }
            
            if (Tvas.getText().equals("")) {
                param.put("vas", ".........");
            } else {
                param.put("vas", Tvas.getText());
            }
            
            if (Tsuhu.getText().equals("")) {
                param.put("suhu", "......... °C");
            } else {
                param.put("suhu", Tsuhu.getText() + " °C");
            }
            
            if (chkBukaMulut.isSelected() == true) {
                param.put("buka", "V");
            } else {
                param.put("buka", "");
            }
            
            if (chkJarak.isSelected() == true) {
                param.put("jarak", "V");
            } else {
                param.put("jarak", "");
            }
            
            if (chkGerakan.isSelected() == true) {
                param.put("gerakan", "V");
            } else {
                param.put("gerakan", "");
            }
            
            if (chkGigiPalsu.isSelected() == true) {
                param.put("gigi", "V");
            } else {
                param.put("gigi", "");
            }
            
            param.put("mallam", cmbMallam.getSelectedItem().toString());
            
            if (Tkepala.getText().equals("")) {
                param.put("kepala", "...........................");
            } else {
                param.put("kepala", Tkepala.getText());
            }
            
            if (Tsklera.getText().equals("")) {
                param.put("sklera", "...........................");
            } else {
                param.put("sklera", Tsklera.getText());
            }
            
            if (Tconjung.getText().equals("")) {
                param.put("conjung", "...........................");
            } else {
                param.put("conjung", Tconjung.getText());
            }
            
            if (Tleher.getText().equals("")) {
                param.put("leher", "...........................");
            } else {
                param.put("leher", Tleher.getText());
            }
            
            if (Tparu.getText().equals("")) {
                param.put("paru", "...........................");
            } else {
                param.put("paru", Tparu.getText());
            }
            
            if (Tjantung.getText().equals("")) {
                param.put("jantung", "...........................");
            } else {
                param.put("jantung", Tjantung.getText());
            }
            
            if (Tabdomen.getText().equals("")) {
                param.put("abdom", "...........................");
            } else {
                param.put("abdom", Tabdomen.getText());
            }
            
            if (Tekstremitas.getText().equals("")) {
                param.put("ekstrem", "...........................");
            } else {
                param.put("ekstrem", Tekstremitas.getText());
            }
            
            if (Tdiagnosis.getText().equals("")) {
                param.put("diagnosa", "...........................");
            } else {
                param.put("diagnosa", Tdiagnosis.getText());
            }
            
            if (TrencanaTindakan.getText().equals("")) {
                param.put("rencanaTindakan", "...........................");
            } else {
                param.put("rencanaTindakan", TrencanaTindakan.getText());
            }
            
            if (chkAsa1.isSelected() == true) {
                param.put("asa1", "V");
            } else {
                param.put("asa1", "");
            }
            
            if (chkAsa2.isSelected() == true) {
                param.put("asa2", "V");
            } else {
                param.put("asa2", "");
            }
            
            if (chkAsa3.isSelected() == true) {
                param.put("asa3", "V");
            } else {
                param.put("asa3", "");
            }

            if (chkAsa4.isSelected() == true) {
                param.put("asa4", "V");
                if (TketAsa4.getText().equals("")) {
                    param.put("ketAsa4", ".......................... ASA 4 Pasien Dengan Penyakit Sistemik Berat Yang Mengancam Nyawa");
                } else {
                    param.put("ketAsa4", TketAsa4.getText() + " ASA 4 Pasien Dengan Penyakit Sistemik Berat Yang Mengancam Nyawa");
                }
            } else {
                param.put("asa4", "");
                param.put("ketAsa4", ".......................... ASA 4 Pasien Dengan Penyakit Sistemik Berat Yang Mengancam Nyawa");
            }
            
            if (chkEmergency.isSelected() == true) {
                param.put("emer", "V");
            } else {
                param.put("emer", "");
            }
            
            if (Tsedasi1.getText().equals("")) {
                param.put("sedasi1", "...........................");
            } else {
                param.put("sedasi1", Tsedasi1.getText());
            }
            
            if (Tsedasi2.getText().equals("")) {
                param.put("sedasi2", "...........................");
            } else {
                param.put("sedasi2", Tsedasi2.getText());
            }
            
            if (Tsedasi3.getText().equals("")) {
                param.put("sedasi3", "...........................");
            } else {
                param.put("sedasi3", Tsedasi3.getText());
            }
            
            if (Tga.getText().equals("")) {
                param.put("ga", "...........................");
            } else {
                param.put("ga", Tga.getText());
            }
            
            if (chkSpinal.isSelected() == true) {
                param.put("spinal", "V");
            } else {
                param.put("spinal", "");
            }
            
            if (chkEpidural.isSelected() == true) {
                param.put("epid", "V");
            } else {
                param.put("epid", "");
            }
            
            if (chkKaudal.isSelected() == true) {
                param.put("kaudal", "V");
            } else {
                param.put("kaudal", "");
            }
            
            if (chkBlok.isSelected() == true) {
                param.put("blok", "V");
            } else {
                param.put("blok", "");
            }
            
            if (chkEkg.isSelected() == true) {
                param.put("ekg", "V");
            } else {
                param.put("ekg", "");
            }
            
            if (chkSpo2.isSelected() == true) {
                param.put("spo2", "V");
            } else {
                param.put("spo2", "");
            }
            
            if (chkNibp.isSelected() == true) {
                param.put("nibp", "V");
            } else {
                param.put("nibp", "");
            }
            
            if (chkTemp.isSelected() == true) {
                param.put("temp", "V");
            } else {
                param.put("temp", "");
            }

            if (chkLain.isSelected() == true) {
                param.put("lain", "V");
                if (TketLain.getText().equals("")) {
                    param.put("ketlain", "...........................");
                } else {
                    param.put("ketlain", TketLain.getText());
                }
            } else {
                param.put("lain", "");
                param.put("ketlain", "...........................");
            }
            
            if (cmbPerawatan.getSelectedIndex() == 3) {
                if (cmbRuangKhusus.getSelectedIndex() != 0) {
                    param.put("perawatan", cmbPerawatan.getSelectedItem().toString() + " : " + cmbRuangKhusus.getSelectedItem().toString());
                } else {
                    param.put("perawatan", cmbPerawatan.getSelectedItem().toString());
                }
            } else {
                param.put("perawatan", cmbPerawatan.getSelectedItem().toString());
            }
            
            if (TkesAnestesi.getText().equals("")) {
                param.put("kesimpulan", "...........................");
            } else {
                param.put("kesimpulan", TkesAnestesi.getText());
            }
            
            if (chkPuasa.isSelected() == true) {
                param.put("puasa", "Jam " + cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " WITA, Tanggal : " + Valid.SetTglINDONESIA(Valid.SetTgl(TtglPuasa.getSelectedItem() + "")));
            } else {
                param.put("puasa", "Jam ..............  WITA, Tanggal : ..............");
            }
            
            if (chkRencana.isSelected() == true) {
                param.put("rencana", "Jam " + cmbJam2.getSelectedItem().toString() + ":" + cmbMnt2.getSelectedItem().toString() + " WITA, Tanggal : " + Valid.SetTglINDONESIA(Valid.SetTgl(TtglRencana.getSelectedItem() + "")));
            } else {
                param.put("rencana", "Jam ..............  WITA, Tanggal : ..............");
            }
            
            if (Tinstruksi.getText().equals("")) {
                param.put("instruksi", "...........................");
            } else {
                param.put("instruksi", Tinstruksi.getText());
            }
            
            if (Tcatatan.getText().equals("")) {
                param.put("catatan", "...........................");
            } else {
                param.put("catatan", Tcatatan.getText());
            }
            
            param.put("dokterAnes", TnmDokter.getText());
            
            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isi = "";
                if (nipDokter.equals("") || nipDokter.equals("-") || nipDokter.equals("--")) {
                    JOptionPane.showMessageDialog(rootPane, "Maaf, nama dokter anestesi harus diisi dulu,..");
                } else {
                    isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Asesmen Pra Sedasi Konsep IAR", TnmDokter.getText(),
                                    Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from asesmen_pra_sedasi_konsep_iar where "
                                            + "waktu_simpan='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString() + "'"),
                                    Sequel.cariIsi("select time(waktu_simpan) from asesmen_pra_sedasi_konsep_iar where "
                                            + "waktu_simpan='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString() + "'")) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                    Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                    Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Asesmen Pra Sedasi Konsep IAR", Sequel.cariFolderPrintTte());
                    param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                    param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));

                    Valid.MyReport("rptAsesmenPraSedasiKonsepIAR2Qr.jasper", "report", "::[ Asesmen Pra Sedasi Konsep IAR (hal. 2) ]::",
                            "SELECT now() tanggal", param);
                    Valid.MyReport("rptAsesmenPraSedasiKonsepIAR1Qr.jasper", "report", "::[ Asesmen Pra Sedasi Konsep IAR (hal. 1) ]::",
                            "SELECT now() tanggal", param);
                    
                    emptTeks();
                    tampil();
                    Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                }
            } else {
                Valid.MyReport("rptAsesmenPraSedasiKonsepIAR2.jasper", "report", "::[ Asesmen Pra Sedasi Konsep IAR (hal. 2) ]::",
                        "SELECT now() tanggal", param);
                Valid.MyReport("rptAsesmenPraSedasiKonsepIAR1.jasper", "report", "::[ Asesmen Pra Sedasi Konsep IAR (hal. 1) ]::",
                        "SELECT now() tanggal", param);
                
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel..!!!!");
            tbAsesmen.requestFocus();
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

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        akses.setform("RMAsesmenPraSedasiKonsepIAR");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void TriwayatAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwayatAlergiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TriwayatAnestesi.requestFocus();
        }
    }//GEN-LAST:event_TriwayatAlergiKeyPressed

    private void TriwayatAnestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwayatAnestesiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TobatSaatIni.requestFocus();
        }
    }//GEN-LAST:event_TriwayatAnestesiKeyPressed

    private void TobatSaatIniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatSaatIniKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            chkHilangGigi.requestFocus();
        }
    }//GEN-LAST:event_TobatSaatIniKeyPressed

    private void TtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tbb.requestFocus();
        }
    }//GEN-LAST:event_TtdKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tvas.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trr.requestFocus();
        }
    }//GEN-LAST:event_TbbKeyPressed

    private void TvasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TvasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsuhu.requestFocus();
        }
    }//GEN-LAST:event_TvasKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnadi.requestFocus();
        }
    }//GEN-LAST:event_TrrKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkBukaMulut.requestFocus();
        }
    }//GEN-LAST:event_TsuhuKeyPressed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMEvaluasiPraAnestesi");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMEvaluasiPraAnestesi");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void TgcsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttb.requestFocus();
        }
    }//GEN-LAST:event_TgcsKeyPressed

    private void TtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttd.requestFocus();
        }
    }//GEN-LAST:event_TtbKeyPressed

    private void TkepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkepalaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsklera.requestFocus();
        }
    }//GEN-LAST:event_TkepalaKeyPressed

    private void TskleraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskleraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tconjung.requestFocus();
        }
    }//GEN-LAST:event_TskleraKeyPressed

    private void TconjungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TconjungKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tleher.requestFocus();
        }
    }//GEN-LAST:event_TconjungKeyPressed

    private void TleherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TleherKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tparu.requestFocus();
        }
    }//GEN-LAST:event_TleherKeyPressed

    private void TparuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TparuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tjantung.requestFocus();
        }
    }//GEN-LAST:event_TparuKeyPressed

    private void TjantungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjantungKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tabdomen.requestFocus();
        }
    }//GEN-LAST:event_TjantungKeyPressed

    private void TabdomenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabdomenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tekstremitas.requestFocus();
        }
    }//GEN-LAST:event_TabdomenKeyPressed

    private void TekstremitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TekstremitasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdiagnosis.requestFocus();
        }
    }//GEN-LAST:event_TekstremitasKeyPressed

    private void TdiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TrencanaTindakan.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosisKeyPressed

    private void TketAsa4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketAsa4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkEmergency.requestFocus();
        }
    }//GEN-LAST:event_TketAsa4KeyPressed

    private void TkesAnestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesAnestesiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            chkPuasa.requestFocus();
        }
    }//GEN-LAST:event_TkesAnestesiKeyPressed

    private void Tsedasi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tsedasi1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsedasi2.requestFocus();
        }
    }//GEN-LAST:event_Tsedasi1KeyPressed

    private void Tsedasi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tsedasi2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsedasi3.requestFocus();
        }
    }//GEN-LAST:event_Tsedasi2KeyPressed

    private void Tsedasi3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tsedasi3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tga.requestFocus();
        }
    }//GEN-LAST:event_Tsedasi3KeyPressed

    private void TgaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSpinal.requestFocus();
        }
    }//GEN-LAST:event_TgaKeyPressed

    private void TketLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPerawatan.requestFocus();
        }
    }//GEN-LAST:event_TketLainKeyPressed

    private void cmbJam2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam2MouseReleased
        AutoCompleteDecorator.decorate(cmbJam2);
    }//GEN-LAST:event_cmbJam2MouseReleased

    private void cmbMnt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt2MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt2);
    }//GEN-LAST:event_cmbMnt2MouseReleased

    private void cmbDtk2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDtk2MouseReleased

    private void TinstruksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TinstruksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tcatatan.requestFocus();
        }
    }//GEN-LAST:event_TinstruksiKeyPressed

    private void TcatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnDokter.requestFocus();
        }
    }//GEN-LAST:event_TcatatanKeyPressed

    private void TrencanaTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrencanaTindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            chkAsa1.requestFocus();
        }
    }//GEN-LAST:event_TrencanaTindakanKeyPressed

    private void cmbDtk2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MousePressed
        AutoCompleteDecorator.decorate(cmbDtk2);
    }//GEN-LAST:event_cmbDtk2MousePressed

    private void chkAsa4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAsa4ActionPerformed
        TketAsa4.setText("");
        if (chkAsa4.isSelected() == true) {
            TketAsa4.setEnabled(true);
            TketAsa4.requestFocus();
        } else {
            TketAsa4.setEnabled(false);
        }
    }//GEN-LAST:event_chkAsa4ActionPerformed

    private void chkLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainActionPerformed
        TketLain.setText("");
        if (chkLain.isSelected() == true) {
            TketLain.setEnabled(true);
            TketLain.requestFocus();
        } else {
            TketLain.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainActionPerformed

    private void cmbPerawatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPerawatanActionPerformed
        cmbRuangKhusus.setSelectedIndex(0);
        if (cmbPerawatan.getSelectedIndex() == 3) {
            cmbRuangKhusus.setEnabled(true);
            cmbRuangKhusus.requestFocus();
        } else {
            cmbRuangKhusus.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPerawatanActionPerformed

    private void chkPuasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPuasaActionPerformed
        TtglPuasa.setDate(new Date());
        if (chkPuasa.isSelected() == true) {
            cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtk1.setSelectedIndex(0);
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
            TtglPuasa.setEnabled(true);
        } else {
            cmbJam1.setSelectedIndex(0);
            cmbMnt1.setSelectedIndex(0);
            cmbDtk1.setSelectedIndex(0);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            TtglPuasa.setEnabled(false);
        }
    }//GEN-LAST:event_chkPuasaActionPerformed

    private void chkRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRencanaActionPerformed
        TtglRencana.setDate(new Date());
        if (chkRencana.isSelected() == true) {
            cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtk2.setSelectedIndex(0);
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
            TtglRencana.setEnabled(true);
        } else {
            cmbJam2.setSelectedIndex(0);
            cmbMnt2.setSelectedIndex(0);
            cmbDtk2.setSelectedIndex(0);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
            TtglRencana.setEnabled(false);
        }
    }//GEN-LAST:event_chkRencanaActionPerformed

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

    private void BtnRiwAlergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRiwAlergiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 1;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Riwayat Alergi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnRiwAlergiActionPerformed

    private void BtnRiwAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRiwAnestesiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 2;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Riwayat Anestesi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnRiwAnestesiActionPerformed

    private void BtnObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnObatActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 3;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Obat Saat Ini ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnObatActionPerformed

    private void BtnDiagnosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagnosisActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 4;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Diagnosis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnDiagnosisActionPerformed

    private void BtnRencanaTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRencanaTindakanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 5;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Rencana Tindakan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnRencanaTindakanActionPerformed

    private void BtnKesimpulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKesimpulanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 6;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Kesimpulan Anestesi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnKesimpulanActionPerformed

    private void BtnInstruksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInstruksiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 7;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Instruksi Pra Anestesia ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnInstruksiActionPerformed

    private void BtnCatatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCatatanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 8;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Catatan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
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
            RMAsesmenPraSedasiKonsepIAR dialog = new RMAsesmenPraSedasiKonsepIAR(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDiagnosis;
    private widget.Button BtnDokter;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnInstruksi;
    private widget.Button BtnKeluar;
    private widget.Button BtnKesimpulan;
    private widget.Button BtnObat;
    private widget.Button BtnPrint;
    private widget.Button BtnRencanaTindakan;
    private widget.Button BtnRiwAlergi;
    private widget.Button BtnRiwAnestesi;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    public widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tabdomen;
    private widget.TextBox Tbb;
    private widget.TextArea Tcatatan;
    private widget.TextBox Tconjung;
    private widget.TextArea Tdiagnosis;
    private widget.TextBox Tekstremitas;
    private widget.TextBox Tga;
    private widget.TextBox Tgcs;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tjantung;
    private widget.TextBox Tkepala;
    private widget.TextArea TkesAnestesi;
    private widget.TextBox TketAsa4;
    private widget.TextBox TketLain;
    private widget.TextBox Tleher;
    private widget.TextBox Tnadi;
    private widget.TextBox TnmDokter;
    private widget.TextArea TobatSaatIni;
    private widget.TextBox Tparu;
    private widget.TextArea TrencanaTindakan;
    private widget.TextBox TrgRawat;
    private widget.TextArea TriwayatAlergi;
    private widget.TextArea TriwayatAnestesi;
    private widget.TextBox Trr;
    private widget.TextBox Tsedasi1;
    private widget.TextBox Tsedasi2;
    private widget.TextBox Tsedasi3;
    private widget.TextBox Tsklera;
    private widget.TextBox Tsuhu;
    private widget.TextBox Ttb;
    private widget.TextBox Ttd;
    private widget.TextArea Ttemplate;
    private widget.Tanggal TtglPuasa;
    private widget.Tanggal TtglRencana;
    private widget.TextBox Tvas;
    private javax.swing.JDialog WindowTemplate;
    public widget.CekBox chkAsa1;
    public widget.CekBox chkAsa2;
    public widget.CekBox chkAsa3;
    public widget.CekBox chkAsa4;
    public widget.CekBox chkBlok;
    public widget.CekBox chkBukaMulut;
    public widget.CekBox chkDenyut;
    public widget.CekBox chkEkg;
    public widget.CekBox chkEmergency;
    public widget.CekBox chkEpidural;
    public widget.CekBox chkGerakan;
    public widget.CekBox chkGigiPalsu;
    public widget.CekBox chkHilangGigi;
    public widget.CekBox chkJarak;
    public widget.CekBox chkKaudal;
    public widget.CekBox chkKejang;
    public widget.CekBox chkLain;
    public widget.CekBox chkLeherPendek;
    public widget.CekBox chkMasalah;
    public widget.CekBox chkNibp;
    public widget.CekBox chkObesitas;
    public widget.CekBox chkPuasa;
    public widget.CekBox chkRencana;
    public widget.CekBox chkSakitDada;
    public widget.CekBox chkSedangHamil;
    public widget.CekBox chkSesakNafas;
    public widget.CekBox chkSpinal;
    public widget.CekBox chkSpo2;
    public widget.CekBox chkStrok;
    public widget.CekBox chkTemp;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbMallam;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbPerawatan;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbRuangKhusus;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel136;
    private widget.Label jLabel137;
    private widget.Label jLabel138;
    private widget.Label jLabel139;
    private widget.Label jLabel140;
    private widget.Label jLabel141;
    private widget.Label jLabel142;
    private widget.Label jLabel143;
    private widget.Label jLabel144;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel276;
    private widget.Label jLabel277;
    private widget.Label jLabel36;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel7;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.ScrollPane scrollPane18;
    private widget.ScrollPane scrollPane19;
    private widget.ScrollPane scrollPane20;
    private widget.ScrollPane scrollPane21;
    private widget.Table tbAsesmen;
    private widget.Table tbTemplate;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select ap.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, pg.nama nmDokter "
                    + "from asesmen_pra_sedasi_konsep_iar ap inner join reg_periksa rp on rp.no_rawat=ap.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=ap.nip_anestesi where "
                    + "date(ap.waktu_simpan) between ? and ? and ap.no_rawat LIKE ? or "
                    + "date(ap.waktu_simpan) between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "date(ap.waktu_simpan) between ? and ? and p.nm_pasien LIKE ? or "
                    + "date(ap.waktu_simpan) between ? and ? and pg.nama LIKE ? or "
                    + "date(ap.waktu_simpan) between ? and ? and ap.diagnosis LIKE ? or "
                    + "date(ap.waktu_simpan) between ? and ? and ap.rencana_tindakan LIKE ? or "
                    + "date(ap.waktu_simpan) between ? and ? and ap.ruang_rawat LIKE ? ORDER BY date(ap.waktu_simpan) desc");
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
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText() + "%");
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText() + "%");                
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgllahir"),
                        rs.getString("ruang_rawat"),                        
                        rs.getString("diagnosis"),
                        rs.getString("rencana_tindakan"),
                        rs.getString("nmDokter"),
                        rs.getString("riwayat_alergi"),
                        rs.getString("riwayat_anestesi"),
                        rs.getString("obat_saat_ini"),
                        rs.getString("hilangnya_gigi"),
                        rs.getString("masalah_mobilisasi"),
                        rs.getString("leher_pendek"),
                        rs.getString("stroke"),
                        rs.getString("sesak_nafas"),
                        rs.getString("sakit_dada"),
                        rs.getString("denyut_jantung"),
                        rs.getString("sedang_hamil"),
                        rs.getString("kejang"),
                        rs.getString("obesitas"),
                        rs.getString("gcs"),
                        rs.getString("tb"),
                        rs.getString("td"),
                        rs.getString("bb"),
                        rs.getString("rr"),
                        rs.getString("nadi"),
                        rs.getString("vas"),
                        rs.getString("suhu"),
                        rs.getString("buka_mulut"),
                        rs.getString("jarak"),
                        rs.getString("gerakan_leher"),
                        rs.getString("gigi_palsu"),
                        rs.getString("mallampathy"),
                        rs.getString("kepala"),
                        rs.getString("sklera"),
                        rs.getString("conjungtiva"),
                        rs.getString("leher"),
                        rs.getString("paru_paru"),
                        rs.getString("jantung"),
                        rs.getString("abdomen"),
                        rs.getString("extremitas"),
                        rs.getString("diagnosis"),
                        rs.getString("rencana_tindakan"),
                        rs.getString("asa1"),
                        rs.getString("asa2"),
                        rs.getString("asa3"),
                        rs.getString("asa4"),
                        rs.getString("ket_asa4"),
                        rs.getString("emergency"),
                        rs.getString("sedasi_obat1"),
                        rs.getString("sedasi_obat2"),
                        rs.getString("sedasi_obat3"),
                        rs.getString("ga"),
                        rs.getString("spinal"),
                        rs.getString("epidural"),
                        rs.getString("kaudal"),
                        rs.getString("blok"),
                        rs.getString("ekg"),
                        rs.getString("spo2"),
                        rs.getString("nibp"),
                        rs.getString("temp"),
                        rs.getString("lain_lain"),
                        rs.getString("ket_lain"),
                        rs.getString("perawatan_pasca"),
                        rs.getString("rawat_khusus"),
                        rs.getString("kesimpulan_anestesi"),
                        rs.getString("cek_puasa"),
                        rs.getString("jam_puasa"),
                        rs.getString("tgl_puasa"),
                        rs.getString("cek_rencana"),
                        rs.getString("jam_rencana"),
                        rs.getString("tgl_rencana"),
                        rs.getString("instruksi"),
                        rs.getString("catatan"),
                        rs.getString("nip_anestesi"),
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
        TriwayatAlergi.setText("");
        TriwayatAnestesi.setText("");
        TobatSaatIni.setText("");        
        chkHilangGigi.setSelected(false);
        chkMasalah.setSelected(false);
        chkLeherPendek.setSelected(false);
        chkStrok.setSelected(false);
        chkSesakNafas.setSelected(false);
        chkSakitDada.setSelected(false);
        chkDenyut.setSelected(false);
        chkSedangHamil.setSelected(false);
        chkKejang.setSelected(false);
        chkObesitas.setSelected(false);
        Tgcs.setText("");
        Ttb.setText("");
        Ttd.setText("");
        Tbb.setText("");
        Trr.setText("");
        Tnadi.setText("");
        Tvas.setText("");
        Tsuhu.setText("");
        chkBukaMulut.setSelected(false);
        chkGerakan.setSelected(false);
        cmbMallam.setSelectedIndex(0);
        chkJarak.setSelected(false);
        chkGigiPalsu.setSelected(false);
        Tkepala.setText("");
        Tsklera.setText("");
        Tconjung.setText("");
        Tleher.setText("");
        Tparu.setText("");
        Tjantung.setText("");
        Tabdomen.setText("");
        Tekstremitas.setText("");
        Tdiagnosis.setText("");
        TrencanaTindakan.setText("");
        chkAsa1.setSelected(false);
        chkAsa2.setSelected(false);
        chkAsa3.setSelected(false);
        chkAsa4.setSelected(false);
        TketAsa4.setText("");
        TketAsa4.setEnabled(false);
        chkEmergency.setSelected(false);
        Tsedasi1.setText("");
        Tsedasi2.setText("");
        Tsedasi3.setText("");
        Tga.setText("");
        chkSpinal.setSelected(false);
        chkEpidural.setSelected(false);
        chkKaudal.setSelected(false);
        chkBlok.setSelected(false);
        chkEkg.setSelected(false);
        chkSpo2.setSelected(false);
        chkNibp.setSelected(false);
        chkTemp.setSelected(false);
        chkLain.setSelected(false);
        TketLain.setText("");
        TketLain.setEnabled(false);        
        cmbPerawatan.setSelectedIndex(0);
        cmbRuangKhusus.setSelectedIndex(0);
        cmbRuangKhusus.setEnabled(false);
        TkesAnestesi.setText("");
        chkPuasa.setSelected(false);
        cmbJam1.setEnabled(false);
        cmbMnt1.setEnabled(false);
        cmbDtk1.setEnabled(false);
        TtglPuasa.setEnabled(false);
        cmbJam1.setSelectedIndex(0);
        cmbMnt1.setSelectedIndex(0);
        cmbDtk1.setSelectedIndex(0);
        TtglPuasa.setDate(new Date());        
        chkRencana.setSelected(false);
        cmbJam2.setEnabled(false);
        cmbMnt2.setEnabled(false);
        cmbDtk2.setEnabled(false);
        TtglRencana.setEnabled(false);
        cmbJam2.setSelectedIndex(0);
        cmbMnt2.setSelectedIndex(0);
        cmbDtk2.setSelectedIndex(0);
        TtglRencana.setDate(new Date());
        Tinstruksi.setText("");
        Tcatatan.setText("");        
        nipDokter = "-";
        TnmDokter.setText("-");
    }

    private void getData() {
        variabelBersih();        
        if (tbAsesmen.getSelectedRow() != -1) {
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 4).toString());
            TriwayatAlergi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 8).toString());
            TriwayatAnestesi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 9).toString());
            TobatSaatIni.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString());
            hilang = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString();
            masalah = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString();
            leher = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString();
            strok = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString();
            sesak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString();
            sakit = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString();
            denyut = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString();
            sedang = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString();
            kejang = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 19).toString();
            obes = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString();
            Tgcs.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString());
            Ttb.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString());
            Ttd.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString());
            Tbb.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString());
            Trr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString());
            Tnadi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString());
            Tvas.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString());
            Tsuhu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString());
            buka = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString();
            jarak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString();
            gerakan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString();
            gigi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 32).toString();
            cmbMallam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 33).toString());
            Tkepala.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString());
            Tsklera.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString());
            Tconjung.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString());
            Tleher.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString());
            Tparu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString());
            Tjantung.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString());
            Tabdomen.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString());
            Tekstremitas.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString());
            Tdiagnosis.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString());
            TrencanaTindakan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString());
            asa1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString();
            asa2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString();
            asa3 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString();
            asa4 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString();
            TketAsa4.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString());
            emer = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString();
            Tsedasi1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString());
            Tsedasi2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString());
            Tsedasi3.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString());
            Tga.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString());
            spinal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString();
            epid = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString();
            kaudal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString();
            blok = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString();
            ekg = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString();
            spo2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString();
            nibp = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString();
            temp = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString();
            lain = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 62).toString();
            TketLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString());
            cmbPerawatan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 64).toString());
            cmbRuangKhusus.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString());
            TkesAnestesi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 66).toString());
            puasa = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString();
            cmbJam1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString().substring(6, 8));
            Valid.SetTgl(TtglPuasa, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString());            
            rencana = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString();
            cmbJam2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString().substring(0, 2));
            cmbMnt2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString().substring(3, 5));
            cmbDtk2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString().substring(6, 8));
            Valid.SetTgl(TtglRencana, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString());
            Tinstruksi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString());
            Tcatatan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString());
            nipDokter = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString();            
            TnmDokter.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 7).toString());
            dataCek();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnGanti.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        
        if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "'") > 0) {
            nipDokter = akses.getkode();
            TnmDokter.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipDokter + "'"));
        } else {
            nipDokter = "-";
            TnmDokter.setText("-");
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TrgRawat.setText(ruangan);
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'"));
        TCari.setText(norw);
    }
    
    private void cekData() {
        if (chkHilangGigi.isSelected() == true) {
            hilang = "ya";
        } else {
            hilang = "tidak";
        }
        
        if (chkMasalah.isSelected() == true) {
            masalah = "ya";
        } else {
            masalah = "tidak";
        }
        
        if (chkLeherPendek.isSelected() == true) {
            leher = "ya";
        } else {
            leher = "tidak";
        }
        
        if (chkStrok.isSelected() == true) {
            strok = "ya";
        } else {
            strok = "tidak";
        }
        
        if (chkSesakNafas.isSelected() == true) {
            sesak = "ya";
        } else {
            sesak = "tidak";
        }
        
        if (chkSakitDada.isSelected() == true) {
            sakit = "ya";
        } else {
            sakit = "tidak";
        }
        
        if (chkDenyut.isSelected() == true) {
            denyut = "ya";
        } else {
            denyut = "tidak";
        }
        
        if (chkSedangHamil.isSelected() == true) {
            sedang = "ya";
        } else {
            sedang = "tidak";
        }
        
        if (chkKejang.isSelected() == true) {
            kejang = "ya";
        } else {
            kejang = "tidak";
        }
        
        if (chkObesitas.isSelected() == true) {
            obes = "ya";
        } else {
            obes = "tidak";
        }
        
        if (chkBukaMulut.isSelected() == true) {
            buka = "ya";
        } else {
            buka = "tidak";
        }
        
        if (chkJarak.isSelected() == true) {
            jarak = "ya";
        } else {
            jarak = "tidak";
        }
        
        if (chkGerakan.isSelected() == true) {
            gerakan = "ya";
        } else {
            gerakan = "tidak";
        }
        
        if (chkGigiPalsu.isSelected() == true) {
            gigi = "ya";
        } else {
            gigi = "tidak";
        }
        
        if (chkAsa1.isSelected() == true) {
            asa1 = "ya";
        } else {
            asa1 = "tidak";
        }
        
        if (chkAsa2.isSelected() == true) {
            asa2 = "ya";
        } else {
            asa2 = "tidak";
        }
        
        if (chkAsa3.isSelected() == true) {
            asa3 = "ya";
        } else {
            asa3 = "tidak";
        }
        
        if (chkAsa4.isSelected() == true) {
            asa4 = "ya";
        } else {
            asa4 = "tidak";
        }
        
        if (chkEmergency.isSelected() == true) {
            emer = "ya";
        } else {
            emer = "tidak";
        }
        
        if (chkSpinal.isSelected() == true) {
            spinal = "ya";
        } else {
            spinal = "tidak";
        }
        
        if (chkEpidural.isSelected() == true) {
            epid = "ya";
        } else {
            epid = "tidak";
        }
        
        if (chkKaudal.isSelected() == true) {
            kaudal = "ya";
        } else {
            kaudal = "tidak";
        }
        
        if (chkBlok.isSelected() == true) {
            blok = "ya";
        } else {
            blok = "tidak";
        }
        
        if (chkEkg.isSelected() == true) {
            ekg = "ya";
        } else {
            ekg = "tidak";
        }
        
        if (chkSpo2.isSelected() == true) {
            spo2 = "ya";
        } else {
            spo2 = "tidak";
        }
        
        if (chkNibp.isSelected() == true) {
            nibp = "ya";
        } else {
            nibp = "tidak";
        }
        
        if (chkTemp.isSelected() == true) {
            temp = "ya";
        } else {
            temp = "tidak";
        }
        
        if (chkLain.isSelected() == true) {
            lain = "ya";
        } else {
            lain = "tidak";
        }
        
        if (chkPuasa.isSelected() == true) {
            puasa = "ya";
        } else {
            puasa = "tidak";
        }
        
        if (chkRencana.isSelected() == true) {
            rencana = "ya";
        } else {
            rencana = "tidak";
        }
    }
    
    private void dataCek() {
        if (hilang.equals("ya")) {
            chkHilangGigi.setSelected(true);
        } else {
            chkHilangGigi.setSelected(false);
        }
        
        if (masalah.equals("ya")) {
            chkMasalah.setSelected(true);
        } else {
            chkMasalah.setSelected(false);
        }
        
        if (leher.equals("ya")) {
            chkLeherPendek.setSelected(true);
        } else {
            chkLeherPendek.setSelected(false);
        }
        
        if (strok.equals("ya")) {
            chkStrok.setSelected(true);
        } else {
            chkStrok.setSelected(false);
        }
        
        if (sesak.equals("ya")) {
            chkSesakNafas.setSelected(true);
        } else {
            chkSesakNafas.setSelected(false);
        }
        
        if (sakit.equals("ya")) {
            chkSakitDada.setSelected(true);
        } else {
            chkSakitDada.setSelected(false);
        }
        
        if (denyut.equals("ya")) {
            chkDenyut.setSelected(true);
        } else {
            chkDenyut.setSelected(false);
        }
        
        if (sedang.equals("ya")) {
            chkSedangHamil.setSelected(true);
        } else {
            chkSedangHamil.setSelected(false);
        }
        
        if (kejang.equals("ya")) {
            chkKejang.setSelected(true);
        } else {
            chkKejang.setSelected(false);
        }
        
        if (obes.equals("ya")) {
            chkObesitas.setSelected(true);
        } else {
            chkObesitas.setSelected(false);
        }
        
        if (buka.equals("ya")) {
            chkBukaMulut.setSelected(true);
        } else {
            chkBukaMulut.setSelected(false);
        }
        
        if (jarak.equals("ya")) {
            chkJarak.setSelected(true);
        } else {
            chkJarak.setSelected(false);
        }
        
        if (gerakan.equals("ya")) {
            chkGerakan.setSelected(true);
        } else {
            chkGerakan.setSelected(false);
        }
        
        if (gigi.equals("ya")) {
            chkGigiPalsu.setSelected(true);
        } else {
            chkGigiPalsu.setSelected(false);
        }
        
        if (asa1.equals("ya")) {
            chkAsa1.setSelected(true);
        } else {
            chkAsa1.setSelected(false);
        }
        
        if (asa2.equals("ya")) {
            chkAsa2.setSelected(true);
        } else {
            chkAsa2.setSelected(false);
        }
        
        if (asa3.equals("ya")) {
            chkAsa3.setSelected(true);
        } else {
            chkAsa3.setSelected(false);
        }
        
        if (asa4.equals("ya")) {
            chkAsa4.setSelected(true);
            TketAsa4.setEnabled(true);
        } else {
            chkAsa4.setSelected(false);
            TketAsa4.setEnabled(false);
        }
        
        if (emer.equals("ya")) {
            chkEmergency.setSelected(true);
        } else {
            chkEmergency.setSelected(false);
        }
        
        if (spinal.equals("ya")) {
            chkSpinal.setSelected(true);
        } else {
            chkSpinal.setSelected(false);
        }
        
        if (epid.equals("ya")) {
            chkEpidural.setSelected(true);
        } else {
            chkEpidural.setSelected(false);
        }
        
        if (kaudal.equals("ya")) {
            chkKaudal.setSelected(true);
        } else {
            chkKaudal.setSelected(false);
        }
        
        if (blok.equals("ya")) {
            chkBlok.setSelected(true);
        } else {
            chkBlok.setSelected(false);
        }
        
        if (ekg.equals("ya")) {
            chkEkg.setSelected(true);
        } else {
            chkEkg.setSelected(false);
        }
        
        if (spo2.equals("ya")) {
            chkSpo2.setSelected(true);
        } else {
            chkSpo2.setSelected(false);
        }
        
        if (nibp.equals("ya")) {
            chkNibp.setSelected(true);
        } else {
            chkNibp.setSelected(false);
        }
        
        if (temp.equals("ya")) {
            chkTemp.setSelected(true);
        } else {
            chkTemp.setSelected(false);
        }
        
        if (lain.equals("ya")) {
            chkLain.setSelected(true);
            TketLain.setEnabled(true);
        } else {
            chkLain.setSelected(false);
            TketLain.setEnabled(false);
        }
        
        if (puasa.equals("ya")) {
            chkPuasa.setSelected(true);
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
            TtglPuasa.setEnabled(true);
        } else {
            chkPuasa.setSelected(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            TtglPuasa.setEnabled(false);
        }
        
        if (rencana.equals("ya")) {
            chkRencana.setSelected(true);
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
            TtglRencana.setEnabled(true);
        } else {
            chkRencana.setSelected(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
            TtglRencana.setEnabled(false);
        }
    }
    
    private void variabelBersih() {
        nipDokter = "";
        hilang = "";
        masalah = "";
        leher = "";
        strok = "";
        sesak = "";
        sakit = "";
        denyut = "";
        sedang = "";
        kejang = "";
        obes = "";
        buka = "";
        jarak = "";
        gerakan = "";
        gigi = "";
        asa1 = "";
        asa2 = "";
        asa3 = "";
        asa4 = "";
        emer = "";
        spinal = "";
        epid = "";
        kaudal = "";
        blok = "";
        ekg = "";
        spo2 = "";
        nibp = "";
        temp = "";
        lain = "";
        puasa = "";
        rencana = "";
    }
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode1);
        try {
            if (pilihan == 1) {
                ps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, a.* from asesmen_pra_sedasi_konsep_iar a "
                        + "inner join reg_periksa rp on rp.no_rawat=a.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "a.riwayat_alergi<>'' and p.no_rkm_medis like ? OR "
                        + "a.riwayat_alergi<>'' and p.nm_pasien like ? OR "
                        + "a.riwayat_alergi<>'' and a.riwayat_alergi like ? ORDER BY a.waktu_simpan desc limit 20");
            } else if (pilihan == 2) {
                ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, a.* from asesmen_pra_sedasi_konsep_iar a "
                        + "inner join reg_periksa rp on rp.no_rawat=a.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "a.riwayat_anestesi<>'' and p.no_rkm_medis like ? OR "
                        + "a.riwayat_anestesi<>'' and p.nm_pasien like ? OR "
                        + "a.riwayat_anestesi<>'' and a.riwayat_anestesi like ? ORDER BY a.waktu_simpan desc limit 20");
            } else if (pilihan == 3) {
                ps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, a.* from asesmen_pra_sedasi_konsep_iar a "
                        + "inner join reg_periksa rp on rp.no_rawat=a.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "a.obat_saat_ini<>'' and p.no_rkm_medis like ? OR "
                        + "a.obat_saat_ini<>'' and p.nm_pasien like ? OR "
                        + "a.obat_saat_ini<>'' and a.obat_saat_ini like ? ORDER BY a.waktu_simpan desc limit 20");
            } else if (pilihan == 4) {
                ps4 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, a.* from asesmen_pra_sedasi_konsep_iar a "
                        + "inner join reg_periksa rp on rp.no_rawat=a.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "a.diagnosis<>'' and p.no_rkm_medis like ? OR "
                        + "a.diagnosis<>'' and p.nm_pasien like ? OR "
                        + "a.diagnosis<>'' and a.diagnosis like ? ORDER BY a.waktu_simpan desc limit 20");
            } else if (pilihan == 5) {
                ps5 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, a.* from asesmen_pra_sedasi_konsep_iar a "
                        + "inner join reg_periksa rp on rp.no_rawat=a.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "a.rencana_tindakan<>'' and p.no_rkm_medis like ? OR "
                        + "a.rencana_tindakan<>'' and p.nm_pasien like ? OR "
                        + "a.rencana_tindakan<>'' and a.rencana_tindakan like ? ORDER BY a.waktu_simpan desc limit 20");
            } else if (pilihan == 6) {
                ps6 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, a.* from asesmen_pra_sedasi_konsep_iar a "
                        + "inner join reg_periksa rp on rp.no_rawat=a.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "a.kesimpulan_anestesi<>'' and p.no_rkm_medis like ? OR "
                        + "a.kesimpulan_anestesi<>'' and p.nm_pasien like ? OR "
                        + "a.kesimpulan_anestesi<>'' and a.kesimpulan_anestesi like ? ORDER BY a.waktu_simpan desc limit 20");
            } else if (pilihan == 7) {
                ps7 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, a.* from asesmen_pra_sedasi_konsep_iar a "
                        + "inner join reg_periksa rp on rp.no_rawat=a.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "a.instruksi<>'' and p.no_rkm_medis like ? OR "
                        + "a.instruksi<>'' and p.nm_pasien like ? OR "
                        + "a.instruksi<>'' and a.instruksi like ? ORDER BY a.waktu_simpan desc limit 20");
            } else if (pilihan == 8) {
                ps8 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, a.* from asesmen_pra_sedasi_konsep_iar a "
                        + "inner join reg_periksa rp on rp.no_rawat=a.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "a.catatan<>'' and p.no_rkm_medis like ? OR "
                        + "a.catatan<>'' and p.nm_pasien like ? OR "
                        + "a.catatan<>'' and a.catatan like ? ORDER BY a.waktu_simpan desc limit 20");
            } 
            
            try {
                if (pilihan == 1) {
                    ps1.setString(1, "%" + TCari1.getText() + "%");
                    ps1.setString(2, "%" + TCari1.getText() + "%");
                    ps1.setString(3, "%" + TCari1.getText() + "%");
                    rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        tabMode1.addRow(new String[]{
                            rs1.getString("no_rkm_medis"),
                            rs1.getString("nm_pasien"),
                            rs1.getString("riwayat_alergi")
                        });
                    }
                } else if (pilihan == 2) {
                    ps2.setString(1, "%" + TCari1.getText() + "%");
                    ps2.setString(2, "%" + TCari1.getText() + "%");
                    ps2.setString(3, "%" + TCari1.getText() + "%");
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        tabMode1.addRow(new String[]{
                            rs2.getString("no_rkm_medis"),
                            rs2.getString("nm_pasien"),
                            rs2.getString("riwayat_anestesi")
                        });
                    }
                } else if (pilihan == 3) {
                    ps3.setString(1, "%" + TCari1.getText() + "%");
                    ps3.setString(2, "%" + TCari1.getText() + "%");
                    ps3.setString(3, "%" + TCari1.getText() + "%");
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        tabMode1.addRow(new String[]{
                            rs3.getString("no_rkm_medis"),
                            rs3.getString("nm_pasien"),
                            rs3.getString("obat_saat_ini")
                        });
                    }
                } else if (pilihan == 4) {
                    ps4.setString(1, "%" + TCari1.getText() + "%");
                    ps4.setString(2, "%" + TCari1.getText() + "%");
                    ps4.setString(3, "%" + TCari1.getText() + "%");
                    rs4 = ps4.executeQuery();
                    while (rs4.next()) {
                        tabMode1.addRow(new String[]{
                            rs4.getString("no_rkm_medis"),
                            rs4.getString("nm_pasien"),
                            rs4.getString("diagnosis")
                        });
                    }
                } else if (pilihan == 5) {
                    ps5.setString(1, "%" + TCari1.getText() + "%");
                    ps5.setString(2, "%" + TCari1.getText() + "%");
                    ps5.setString(3, "%" + TCari1.getText() + "%");
                    rs5 = ps5.executeQuery();
                    while (rs5.next()) {
                        tabMode1.addRow(new String[]{
                            rs5.getString("no_rkm_medis"),
                            rs5.getString("nm_pasien"),
                            rs5.getString("rencana_tindakan")
                        });
                    }
                } else if (pilihan == 6) {
                    ps6.setString(1, "%" + TCari1.getText() + "%");
                    ps6.setString(2, "%" + TCari1.getText() + "%");
                    ps6.setString(3, "%" + TCari1.getText() + "%");
                    rs6 = ps6.executeQuery();
                    while (rs6.next()) {
                        tabMode1.addRow(new String[]{
                            rs6.getString("no_rkm_medis"),
                            rs6.getString("nm_pasien"),
                            rs6.getString("kesimpulan_anestesi")
                        });
                    }
                } else if (pilihan == 7) {
                    ps7.setString(1, "%" + TCari1.getText() + "%");
                    ps7.setString(2, "%" + TCari1.getText() + "%");
                    ps7.setString(3, "%" + TCari1.getText() + "%");
                    rs7 = ps7.executeQuery();
                    while (rs7.next()) {
                        tabMode1.addRow(new String[]{
                            rs7.getString("no_rkm_medis"),
                            rs7.getString("nm_pasien"),
                            rs7.getString("instruksi")
                        });
                    }
                } else if (pilihan == 8) {
                    ps8.setString(1, "%" + TCari1.getText() + "%");
                    ps8.setString(2, "%" + TCari1.getText() + "%");
                    ps8.setString(3, "%" + TCari1.getText() + "%");
                    rs8 = ps8.executeQuery();
                    while (rs8.next()) {
                        tabMode1.addRow(new String[]{
                            rs8.getString("no_rkm_medis"),
                            rs8.getString("nm_pasien"),
                            rs8.getString("catatan")
                        });
                    }
                } 
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                } else if (rs2 != null) {
                    rs2.close();
                } else if (rs3 != null) {
                    rs3.close();
                } else if (rs4 != null) {
                    rs4.close();
                } else if (rs5 != null) {
                    rs5.close();
                } else if (rs6 != null) {
                    rs6.close();
                } else if (rs7 != null) {
                    rs7.close();
                } else if (rs8 != null) {
                    rs8.close();
                } 

                if (ps1 != null) {
                    ps1.close();
                } else if (ps2 != null) {
                    ps2.close();
                } else if (ps3 != null) {
                    ps3.close();
                } else if (ps4 != null) {
                    ps4.close();
                } else if (ps5 != null) {
                    ps5.close();
                } else if (ps6 != null) {
                    ps6.close();
                } else if (ps7 != null) {
                    ps7.close();
                } else if (ps8 != null) {
                    ps8.close();
                } 
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copas() {
        if (pilihan == 1) {
            TriwayatAlergi.setText(Ttemplate.getText());
        } else if (pilihan == 2) {
            TriwayatAnestesi.setText(Ttemplate.getText());
        } else if (pilihan == 3) {
            TobatSaatIni.setText(Ttemplate.getText());
        } else if (pilihan == 4) {
            Tdiagnosis.setText(Ttemplate.getText());
        } else if (pilihan == 5) {
            TrencanaTindakan.setText(Ttemplate.getText());
        } else if (pilihan == 6) {
            TkesAnestesi.setText(Ttemplate.getText());
        } else if (pilihan == 7) {
            Tinstruksi.setText(Ttemplate.getText());
        } else if (pilihan == 8) {
            Tcatatan.setText(Ttemplate.getText());
        } 
    }
}
