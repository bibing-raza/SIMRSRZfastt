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

/**
 *
 * @author dosen
 */
public class RMPasienUntukTindakan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3, pscppt;
    private ResultSet rs, rs1, rs2, rs3, rscppt;
    private int i = 0, x = 0, pilihan = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String cateterBlm = "", ngtBlm = "", nasalBlm = "", kateterUrinBlm = "", drainBlm = "", alatBlm = "",
            cmBlm = "", soporBlm = "", apatisBlm = "", somnolenBlm = "", sedasiBlm = "", nipSerahBlm = "", nipTerimaBlm = "",
            cateterSdh = "", ngtSdh = "", nasalSdh = "", kateterUrinSdh = "", drainSdh = "", alatSdh = "", wktSimpanSdh = "",
            cmSdh = "", soporSdh = "", apatisSdh = "", somnolenSdh = "", sedasiSdh = "", nipSerahSdh = "", nipTerimaSdh = "",
            cekGanti = "", dataKonfirmasi = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMPasienUntukTindakan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "Kode Transfer", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Tindakan", "Riw. Alergi",
            "Diagnosa", "Kat. Derajat", "Resiko Jatuh", "Skala Nyeri", "Paparan Infeksi", "Jns. Paparan", "IV Cateter", "NGT",
            "Nasal Kanula", "Kateter Urine", "Drain/WSD", "Alat Lain", "Ket. Alat Lain", "CM", "Sopor", "Apatis", "Sedasi", "Somnolen",
            "Tensi", "RR", "Nadi", "Spo2", "Catatan", "Petugas Menyerahkan", "Petugas Menerima",
            "tgl_tindakan", "cateter", "ngt", "nasal", "kateter_urin", "drain", "alat_lain", "kondisi_cm", "kondisi_sopor", "kondisi_apatis",
            "kondisi_pengaruh_sedasi", "kondisi_somnolen", "nip_menyerahkan", "nip_menerima", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbTransferBelum.setModel(tabMode);
        tbTransferBelum.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTransferBelum.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 48; i++) {
            TableColumn column = tbTransferBelum.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(130);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(70);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            } else if (i == 14) {
                column.setPreferredWidth(80);
            } else if (i == 15) {
                column.setPreferredWidth(50);
            } else if (i == 16) {
                column.setPreferredWidth(80);
            } else if (i == 17) {
                column.setPreferredWidth(90);
            } else if (i == 18) {
                column.setPreferredWidth(75);
            } else if (i == 19) {
                column.setPreferredWidth(75);
            } else if (i == 20) {
                column.setPreferredWidth(200);
            } else if (i == 21) {
                column.setPreferredWidth(50);
            } else if (i == 22) {
                column.setPreferredWidth(60);
            } else if (i == 23) {
                column.setPreferredWidth(60);
            } else if (i == 24) {
                column.setPreferredWidth(60);
            } else if (i == 25) {
                column.setPreferredWidth(70);
            } else if (i == 26) {
                column.setPreferredWidth(60);
            } else if (i == 27) {
                column.setPreferredWidth(60);
            } else if (i == 28) {
                column.setPreferredWidth(60);
            } else if (i == 29) {
                column.setPreferredWidth(60);
            } else if (i == 30) {
                column.setPreferredWidth(220);
            } else if (i == 31) {
                column.setPreferredWidth(220);
            } else if (i == 32) {
                column.setPreferredWidth(220);
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
            }
        }
        tbTransferBelum.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "No. Rawat", "Kode Transfer", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Tindakan", "Riw. Alergi",
            "Diagnosa", "Kat. Derajat", "Resiko Jatuh", "Skala Nyeri", "Paparan Infeksi", "Jns. Paparan", "IV Cateter", "NGT",
            "Nasal Kanula", "Kateter Urine", "Drain/WSD", "Alat Lain", "Ket. Alat Lain", "CM", "Sopor", "Apatis", "Sedasi", "Somnolen",
            "Tensi", "RR", "Nadi", "Spo2", "Catatan", "Petugas Menyerahkan", "Petugas Menerima",
            "tgl_tindakan", "cateter", "ngt", "nasal", "kateter_urin", "drain", "alat_lain", "kondisi_cm", "kondisi_sopor", "kondisi_apatis",
            "kondisi_pengaruh_sedasi", "kondisi_somnolen", "nip_menyerahkan", "nip_menerima", "waktu_simpan", "tgltindkn"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbTransferSudah.setModel(tabMode1);
        tbTransferSudah.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTransferSudah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 49; i++) {
            TableColumn column = tbTransferSudah.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(130);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(70);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            } else if (i == 14) {
                column.setPreferredWidth(80);
            } else if (i == 15) {
                column.setPreferredWidth(50);
            } else if (i == 16) {
                column.setPreferredWidth(80);
            } else if (i == 17) {
                column.setPreferredWidth(90);
            } else if (i == 18) {
                column.setPreferredWidth(75);
            } else if (i == 19) {
                column.setPreferredWidth(75);
            } else if (i == 20) {
                column.setPreferredWidth(200);
            } else if (i == 21) {
                column.setPreferredWidth(50);
            } else if (i == 22) {
                column.setPreferredWidth(60);
            } else if (i == 23) {
                column.setPreferredWidth(60);
            } else if (i == 24) {
                column.setPreferredWidth(60);
            } else if (i == 25) {
                column.setPreferredWidth(70);
            } else if (i == 26) {
                column.setPreferredWidth(60);
            } else if (i == 27) {
                column.setPreferredWidth(60);
            } else if (i == 28) {
                column.setPreferredWidth(60);
            } else if (i == 29) {
                column.setPreferredWidth(60);
            } else if (i == 30) {
                column.setPreferredWidth(220);
            } else if (i == 31) {
                column.setPreferredWidth(220);
            } else if (i == 32) {
                column.setPreferredWidth(220);
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
            }
        }
        tbTransferSudah.setDefaultRenderer(Object.class, new WarnaTable());
        
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

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TtensiBelum.setDocument(new batasInput((int) 7).getKata(TtensiBelum));
        TrrBelum.setDocument(new batasInput((int) 7).getKata(TrrBelum));
        TnadiBelum.setDocument(new batasInput((int) 7).getKata(TnadiBelum));
        TspoBelum.setDocument(new batasInput((int) 7).getKata(TspoBelum));
        TtensiSudah.setDocument(new batasInput((int) 7).getKata(TtensiSudah));
        TrrSudah.setDocument(new batasInput((int) 7).getKata(TrrSudah));
        TnadiSudah.setDocument(new batasInput((int) 7).getKata(TnadiSudah));
        TspoSudah.setDocument(new batasInput((int) 7).getKata(TspoSudah));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampilSebelum();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampilSebelum();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampilSebelum();}
            });
        }
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (pilihan == 1) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipSerahBlm = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugasSerahBelum.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPetugas1Belum.requestFocus();
                    }
                } else if (pilihan == 2) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipTerimaBlm = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugasTerimaBelum.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPetugas2Belum.requestFocus();
                    }
                } else if (pilihan == 3) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipSerahSdh = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugasSerahSudah.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPetugas1Sudah.requestFocus();
                    }
                } else if (pilihan == 4) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipTerimaSdh = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugasTerimaSudah.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPetugas2Sudah.requestFocus();
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
        MnHasilPemeriksaanPenunjang = new javax.swing.JMenuItem();
        MnDokumenJangMed = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        TabTransfer = new javax.swing.JTabbedPane();
        internalFrame20 = new widget.InternalFrame();
        PanelInput = new javax.swing.JPanel();
        jLabel10 = new widget.Label();
        TNoRwBelum = new widget.TextBox();
        TNoRMBelum = new widget.TextBox();
        TPasienBelum = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawatBelum = new widget.TextBox();
        jLabel64 = new widget.Label();
        TkdTransferBelum = new widget.TextBox();
        jLabel65 = new widget.Label();
        TtglTindakanBelum = new widget.Tanggal();
        jLabel66 = new widget.Label();
        cmbRiwAlergiBelum = new widget.ComboBox();
        jLabel67 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        TdiagnosaBelum = new widget.TextArea();
        jLabel68 = new widget.Label();
        cmbKategoriBelum = new widget.ComboBox();
        jLabel69 = new widget.Label();
        cmbResikoBelum = new widget.ComboBox();
        jLabel70 = new widget.Label();
        cmbSkalaBelum = new widget.ComboBox();
        jLabel71 = new widget.Label();
        cmbPaparanBelum = new widget.ComboBox();
        jLabel72 = new widget.Label();
        cmbJnsPaparanBelum = new widget.ComboBox();
        jLabel73 = new widget.Label();
        ChkCateterBelum = new widget.CekBox();
        ChkNgtBelum = new widget.CekBox();
        ChkNasalBelum = new widget.CekBox();
        ChkKateterBelum = new widget.CekBox();
        ChkDrainBelum = new widget.CekBox();
        ChkAlatBelum = new widget.CekBox();
        TalatLainBelum = new widget.TextBox();
        jLabel74 = new widget.Label();
        ChkCMBelum = new widget.CekBox();
        ChkSoporBelum = new widget.CekBox();
        ChkApatisBelum = new widget.CekBox();
        ChkSomnolenBelum = new widget.CekBox();
        ChkDalamBelum = new widget.CekBox();
        jLabel75 = new widget.Label();
        TtensiBelum = new widget.TextBox();
        jLabel27 = new widget.Label();
        TrrBelum = new widget.TextBox();
        jLabel30 = new widget.Label();
        TnadiBelum = new widget.TextBox();
        jLabel29 = new widget.Label();
        TspoBelum = new widget.TextBox();
        jLabel37 = new widget.Label();
        jLabel76 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        TcatatanBelum = new widget.TextArea();
        jLabel77 = new widget.Label();
        TnmPetugasSerahBelum = new widget.TextBox();
        jLabel78 = new widget.Label();
        TnmPetugasTerimaBelum = new widget.TextBox();
        BtnPetugas1Belum = new widget.Button();
        BtnPetugas2Belum = new widget.Button();
        chkSaya1 = new widget.CekBox();
        chkSaya2 = new widget.CekBox();
        Scroll = new widget.ScrollPane();
        tbTransferBelum = new widget.Table();
        internalFrame21 = new widget.InternalFrame();
        PanelInput1 = new javax.swing.JPanel();
        jLabel11 = new widget.Label();
        TNoRwSudah = new widget.TextBox();
        TNoRMSudah = new widget.TextBox();
        TPasienSudah = new widget.TextBox();
        jLabel79 = new widget.Label();
        TrgRawatSudah = new widget.TextBox();
        jLabel80 = new widget.Label();
        TkdTransferSudah = new widget.TextBox();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        TdiagnosaSudah = new widget.TextArea();
        jLabel84 = new widget.Label();
        cmbKategoriSudah = new widget.ComboBox();
        jLabel85 = new widget.Label();
        cmbResikoSudah = new widget.ComboBox();
        jLabel86 = new widget.Label();
        cmbSkalaSudah = new widget.ComboBox();
        jLabel87 = new widget.Label();
        cmbPaparanSudah = new widget.ComboBox();
        jLabel88 = new widget.Label();
        cmbJnsPaparanSudah = new widget.ComboBox();
        jLabel89 = new widget.Label();
        ChkCateterSudah = new widget.CekBox();
        ChkNgtSudah = new widget.CekBox();
        ChkNasalSudah = new widget.CekBox();
        ChkKateterSudah = new widget.CekBox();
        ChkDrainSudah = new widget.CekBox();
        ChkAlatSudah = new widget.CekBox();
        TalatLainSudah = new widget.TextBox();
        jLabel90 = new widget.Label();
        ChkCMSudah = new widget.CekBox();
        ChkSoporSudah = new widget.CekBox();
        ChkApatisSudah = new widget.CekBox();
        ChkSomnolenSudah = new widget.CekBox();
        ChkDalamSudah = new widget.CekBox();
        jLabel91 = new widget.Label();
        TtensiSudah = new widget.TextBox();
        jLabel28 = new widget.Label();
        TrrSudah = new widget.TextBox();
        jLabel31 = new widget.Label();
        TnadiSudah = new widget.TextBox();
        jLabel32 = new widget.Label();
        TspoSudah = new widget.TextBox();
        jLabel38 = new widget.Label();
        jLabel92 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        TcatatanSudah = new widget.TextArea();
        jLabel93 = new widget.Label();
        TnmPetugasSerahSudah = new widget.TextBox();
        jLabel94 = new widget.Label();
        TnmPetugasTerimaSudah = new widget.TextBox();
        BtnPetugas1Sudah = new widget.Button();
        BtnPetugas2Sudah = new widget.Button();
        chkSaya3 = new widget.CekBox();
        chkSaya4 = new widget.CekBox();
        TtglTindakanSudah = new widget.TextBox();
        TRiwAlergiSudah = new widget.TextBox();
        Scroll1 = new widget.ScrollPane();
        tbTransferSudah = new widget.Table();
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
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Transfer Pasien Untuk Tindakan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabTransfer.setBackground(new java.awt.Color(254, 255, 254));
        TabTransfer.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        TabTransfer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabTransfer.setName("TabTransfer"); // NOI18N
        TabTransfer.setPreferredSize(new java.awt.Dimension(0, 2000));
        TabTransfer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabTransferMouseClicked(evt);
            }
        });

        internalFrame20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: SEBELUM TINDAKAN :.", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame20.setToolTipText("Klik Kanan Pada Area Ini Untuk Melihat Hasil Pemeriksaan Penunjang Medis");
        internalFrame20.setName("internalFrame20"); // NOI18N
        internalFrame20.setPreferredSize(new java.awt.Dimension(900, 900));
        internalFrame20.setLayout(new java.awt.BorderLayout());

        PanelInput.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang");
        PanelInput.setComponentPopupMenu(jPopupMenu1);
        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 480));
        PanelInput.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        PanelInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 110, 23);

        TNoRwBelum.setEditable(false);
        TNoRwBelum.setForeground(new java.awt.Color(0, 0, 0));
        TNoRwBelum.setName("TNoRwBelum"); // NOI18N
        PanelInput.add(TNoRwBelum);
        TNoRwBelum.setBounds(114, 10, 131, 23);

        TNoRMBelum.setEditable(false);
        TNoRMBelum.setForeground(new java.awt.Color(0, 0, 0));
        TNoRMBelum.setName("TNoRMBelum"); // NOI18N
        PanelInput.add(TNoRMBelum);
        TNoRMBelum.setBounds(247, 10, 70, 23);

        TPasienBelum.setEditable(false);
        TPasienBelum.setForeground(new java.awt.Color(0, 0, 0));
        TPasienBelum.setHighlighter(null);
        TPasienBelum.setName("TPasienBelum"); // NOI18N
        PanelInput.add(TPasienBelum);
        TPasienBelum.setBounds(319, 10, 410, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        PanelInput.add(jLabel63);
        jLabel63.setBounds(0, 38, 110, 23);

        TrgRawatBelum.setEditable(false);
        TrgRawatBelum.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawatBelum.setName("TrgRawatBelum"); // NOI18N
        PanelInput.add(TrgRawatBelum);
        TrgRawatBelum.setBounds(115, 38, 388, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Kode Transfer :");
        jLabel64.setName("jLabel64"); // NOI18N
        PanelInput.add(jLabel64);
        jLabel64.setBounds(505, 38, 87, 23);

        TkdTransferBelum.setEditable(false);
        TkdTransferBelum.setForeground(new java.awt.Color(0, 0, 0));
        TkdTransferBelum.setName("TkdTransferBelum"); // NOI18N
        PanelInput.add(TkdTransferBelum);
        TkdTransferBelum.setBounds(600, 38, 129, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Tgl. Tindakan :");
        jLabel65.setName("jLabel65"); // NOI18N
        PanelInput.add(jLabel65);
        jLabel65.setBounds(0, 66, 110, 23);

        TtglTindakanBelum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-09-2024" }));
        TtglTindakanBelum.setDisplayFormat("dd-MM-yyyy");
        TtglTindakanBelum.setName("TtglTindakanBelum"); // NOI18N
        TtglTindakanBelum.setOpaque(false);
        TtglTindakanBelum.setPreferredSize(new java.awt.Dimension(90, 23));
        PanelInput.add(TtglTindakanBelum);
        TtglTindakanBelum.setBounds(115, 66, 90, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Riwayat Alergi :");
        jLabel66.setName("jLabel66"); // NOI18N
        PanelInput.add(jLabel66);
        jLabel66.setBounds(205, 66, 98, 23);

        cmbRiwAlergiBelum.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwAlergiBelum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbRiwAlergiBelum.setName("cmbRiwAlergiBelum"); // NOI18N
        PanelInput.add(cmbRiwAlergiBelum);
        cmbRiwAlergiBelum.setBounds(309, 66, 67, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Diagnosa :");
        jLabel67.setName("jLabel67"); // NOI18N
        PanelInput.add(jLabel67);
        jLabel67.setBounds(0, 94, 110, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N
        scrollPane13.setPreferredSize(new java.awt.Dimension(174, 100));

        TdiagnosaBelum.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TdiagnosaBelum.setColumns(20);
        TdiagnosaBelum.setRows(5);
        TdiagnosaBelum.setName("TdiagnosaBelum"); // NOI18N
        TdiagnosaBelum.setPreferredSize(new java.awt.Dimension(162, 700));
        TdiagnosaBelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaBelumKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(TdiagnosaBelum);

        PanelInput.add(scrollPane13);
        scrollPane13.setBounds(115, 94, 615, 72);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("Kategori Derajat Pendampingan Pasien :");
        jLabel68.setName("jLabel68"); // NOI18N
        PanelInput.add(jLabel68);
        jLabel68.setBounds(115, 172, 200, 23);

        cmbKategoriBelum.setForeground(new java.awt.Color(0, 0, 0));
        cmbKategoriBelum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3" }));
        cmbKategoriBelum.setName("cmbKategoriBelum"); // NOI18N
        PanelInput.add(cmbKategoriBelum);
        cmbKategoriBelum.setBounds(318, 172, 45, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Resiko Jatuh :");
        jLabel69.setName("jLabel69"); // NOI18N
        PanelInput.add(jLabel69);
        jLabel69.setBounds(366, 172, 92, 23);

        cmbResikoBelum.setForeground(new java.awt.Color(0, 0, 0));
        cmbResikoBelum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ringan", "Sedang", "Berat" }));
        cmbResikoBelum.setName("cmbResikoBelum"); // NOI18N
        PanelInput.add(cmbResikoBelum);
        cmbResikoBelum.setBounds(466, 172, 75, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Skala Nyeri :");
        jLabel70.setName("jLabel70"); // NOI18N
        PanelInput.add(jLabel70);
        jLabel70.setBounds(540, 172, 80, 23);

        cmbSkalaBelum.setForeground(new java.awt.Color(0, 0, 0));
        cmbSkalaBelum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cmbSkalaBelum.setName("cmbSkalaBelum"); // NOI18N
        PanelInput.add(cmbSkalaBelum);
        cmbSkalaBelum.setBounds(628, 172, 45, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Paparan Infeksi :");
        jLabel71.setName("jLabel71"); // NOI18N
        PanelInput.add(jLabel71);
        jLabel71.setBounds(0, 200, 110, 23);

        cmbPaparanBelum.setForeground(new java.awt.Color(0, 0, 0));
        cmbPaparanBelum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbPaparanBelum.setName("cmbPaparanBelum"); // NOI18N
        cmbPaparanBelum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPaparanBelumActionPerformed(evt);
            }
        });
        PanelInput.add(cmbPaparanBelum);
        cmbPaparanBelum.setBounds(115, 200, 67, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Jenis Paparan :");
        jLabel72.setName("jLabel72"); // NOI18N
        PanelInput.add(jLabel72);
        jLabel72.setBounds(185, 200, 92, 23);

        cmbJnsPaparanBelum.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsPaparanBelum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Air Bone", "Percikan", "Kontak Langsung" }));
        cmbJnsPaparanBelum.setName("cmbJnsPaparanBelum"); // NOI18N
        PanelInput.add(cmbJnsPaparanBelum);
        cmbJnsPaparanBelum.setBounds(284, 200, 120, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Alat Medis Yang Digunakan :");
        jLabel73.setName("jLabel73"); // NOI18N
        PanelInput.add(jLabel73);
        jLabel73.setBounds(0, 228, 170, 23);

        ChkCateterBelum.setBackground(new java.awt.Color(255, 255, 250));
        ChkCateterBelum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCateterBelum.setForeground(new java.awt.Color(0, 0, 0));
        ChkCateterBelum.setText("IV Catheter");
        ChkCateterBelum.setBorderPainted(true);
        ChkCateterBelum.setBorderPaintedFlat(true);
        ChkCateterBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCateterBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCateterBelum.setName("ChkCateterBelum"); // NOI18N
        ChkCateterBelum.setOpaque(false);
        ChkCateterBelum.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkCateterBelum);
        ChkCateterBelum.setBounds(176, 228, 100, 23);

        ChkNgtBelum.setBackground(new java.awt.Color(255, 255, 250));
        ChkNgtBelum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNgtBelum.setForeground(new java.awt.Color(0, 0, 0));
        ChkNgtBelum.setText("NGT");
        ChkNgtBelum.setBorderPainted(true);
        ChkNgtBelum.setBorderPaintedFlat(true);
        ChkNgtBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNgtBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNgtBelum.setName("ChkNgtBelum"); // NOI18N
        ChkNgtBelum.setOpaque(false);
        ChkNgtBelum.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkNgtBelum);
        ChkNgtBelum.setBounds(284, 228, 60, 23);

        ChkNasalBelum.setBackground(new java.awt.Color(255, 255, 250));
        ChkNasalBelum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNasalBelum.setForeground(new java.awt.Color(0, 0, 0));
        ChkNasalBelum.setText("Nasal Kanula / Mask / Ventilator");
        ChkNasalBelum.setBorderPainted(true);
        ChkNasalBelum.setBorderPaintedFlat(true);
        ChkNasalBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNasalBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNasalBelum.setName("ChkNasalBelum"); // NOI18N
        ChkNasalBelum.setOpaque(false);
        ChkNasalBelum.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkNasalBelum);
        ChkNasalBelum.setBounds(380, 228, 190, 23);

        ChkKateterBelum.setBackground(new java.awt.Color(255, 255, 250));
        ChkKateterBelum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKateterBelum.setForeground(new java.awt.Color(0, 0, 0));
        ChkKateterBelum.setText("Kateter Urine");
        ChkKateterBelum.setBorderPainted(true);
        ChkKateterBelum.setBorderPaintedFlat(true);
        ChkKateterBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKateterBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKateterBelum.setName("ChkKateterBelum"); // NOI18N
        ChkKateterBelum.setOpaque(false);
        ChkKateterBelum.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkKateterBelum);
        ChkKateterBelum.setBounds(176, 256, 100, 23);

        ChkDrainBelum.setBackground(new java.awt.Color(255, 255, 250));
        ChkDrainBelum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDrainBelum.setForeground(new java.awt.Color(0, 0, 0));
        ChkDrainBelum.setText("Drain / WSD");
        ChkDrainBelum.setBorderPainted(true);
        ChkDrainBelum.setBorderPaintedFlat(true);
        ChkDrainBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDrainBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDrainBelum.setName("ChkDrainBelum"); // NOI18N
        ChkDrainBelum.setOpaque(false);
        ChkDrainBelum.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkDrainBelum);
        ChkDrainBelum.setBounds(284, 256, 90, 23);

        ChkAlatBelum.setBackground(new java.awt.Color(255, 255, 250));
        ChkAlatBelum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAlatBelum.setForeground(new java.awt.Color(0, 0, 0));
        ChkAlatBelum.setText("Alat Lain");
        ChkAlatBelum.setBorderPainted(true);
        ChkAlatBelum.setBorderPaintedFlat(true);
        ChkAlatBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAlatBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAlatBelum.setName("ChkAlatBelum"); // NOI18N
        ChkAlatBelum.setOpaque(false);
        ChkAlatBelum.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkAlatBelum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAlatBelumActionPerformed(evt);
            }
        });
        PanelInput.add(ChkAlatBelum);
        ChkAlatBelum.setBounds(380, 256, 70, 23);

        TalatLainBelum.setForeground(new java.awt.Color(0, 0, 0));
        TalatLainBelum.setName("TalatLainBelum"); // NOI18N
        TalatLainBelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalatLainBelumKeyPressed(evt);
            }
        });
        PanelInput.add(TalatLainBelum);
        TalatLainBelum.setBounds(450, 256, 280, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Kondisi Klinis Sebelum Transfer :");
        jLabel74.setName("jLabel74"); // NOI18N
        PanelInput.add(jLabel74);
        jLabel74.setBounds(0, 284, 190, 23);

        ChkCMBelum.setBackground(new java.awt.Color(255, 255, 250));
        ChkCMBelum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCMBelum.setForeground(new java.awt.Color(0, 0, 0));
        ChkCMBelum.setText("CM");
        ChkCMBelum.setBorderPainted(true);
        ChkCMBelum.setBorderPaintedFlat(true);
        ChkCMBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCMBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCMBelum.setName("ChkCMBelum"); // NOI18N
        ChkCMBelum.setOpaque(false);
        ChkCMBelum.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkCMBelum);
        ChkCMBelum.setBounds(198, 284, 50, 23);

        ChkSoporBelum.setBackground(new java.awt.Color(255, 255, 250));
        ChkSoporBelum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSoporBelum.setForeground(new java.awt.Color(0, 0, 0));
        ChkSoporBelum.setText("Sopor");
        ChkSoporBelum.setBorderPainted(true);
        ChkSoporBelum.setBorderPaintedFlat(true);
        ChkSoporBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSoporBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSoporBelum.setName("ChkSoporBelum"); // NOI18N
        ChkSoporBelum.setOpaque(false);
        ChkSoporBelum.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkSoporBelum);
        ChkSoporBelum.setBounds(256, 284, 60, 23);

        ChkApatisBelum.setBackground(new java.awt.Color(255, 255, 250));
        ChkApatisBelum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkApatisBelum.setForeground(new java.awt.Color(0, 0, 0));
        ChkApatisBelum.setText("Apatis");
        ChkApatisBelum.setBorderPainted(true);
        ChkApatisBelum.setBorderPaintedFlat(true);
        ChkApatisBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkApatisBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkApatisBelum.setName("ChkApatisBelum"); // NOI18N
        ChkApatisBelum.setOpaque(false);
        ChkApatisBelum.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkApatisBelum);
        ChkApatisBelum.setBounds(324, 284, 60, 23);

        ChkSomnolenBelum.setBackground(new java.awt.Color(255, 255, 250));
        ChkSomnolenBelum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSomnolenBelum.setForeground(new java.awt.Color(0, 0, 0));
        ChkSomnolenBelum.setText("Somnolen");
        ChkSomnolenBelum.setBorderPainted(true);
        ChkSomnolenBelum.setBorderPaintedFlat(true);
        ChkSomnolenBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSomnolenBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSomnolenBelum.setName("ChkSomnolenBelum"); // NOI18N
        ChkSomnolenBelum.setOpaque(false);
        ChkSomnolenBelum.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkSomnolenBelum);
        ChkSomnolenBelum.setBounds(390, 284, 78, 23);

        ChkDalamBelum.setBackground(new java.awt.Color(255, 255, 250));
        ChkDalamBelum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDalamBelum.setForeground(new java.awt.Color(0, 0, 0));
        ChkDalamBelum.setText("Dalam Pengaruh Sedasi");
        ChkDalamBelum.setBorderPainted(true);
        ChkDalamBelum.setBorderPaintedFlat(true);
        ChkDalamBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDalamBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDalamBelum.setName("ChkDalamBelum"); // NOI18N
        ChkDalamBelum.setOpaque(false);
        ChkDalamBelum.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkDalamBelum);
        ChkDalamBelum.setBounds(476, 284, 150, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Tekanan Darah :");
        jLabel75.setName("jLabel75"); // NOI18N
        PanelInput.add(jLabel75);
        jLabel75.setBounds(0, 312, 190, 23);

        TtensiBelum.setBackground(new java.awt.Color(245, 250, 240));
        TtensiBelum.setForeground(new java.awt.Color(0, 0, 0));
        TtensiBelum.setName("TtensiBelum"); // NOI18N
        TtensiBelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtensiBelumKeyPressed(evt);
            }
        });
        PanelInput.add(TtensiBelum);
        TtensiBelum.setBounds(198, 312, 70, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("mmHg       RR : ");
        jLabel27.setName("jLabel27"); // NOI18N
        PanelInput.add(jLabel27);
        jLabel27.setBounds(273, 312, 78, 23);

        TrrBelum.setBackground(new java.awt.Color(245, 250, 240));
        TrrBelum.setForeground(new java.awt.Color(0, 0, 0));
        TrrBelum.setName("TrrBelum"); // NOI18N
        TrrBelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrBelumKeyPressed(evt);
            }
        });
        PanelInput.add(TrrBelum);
        TrrBelum.setBounds(353, 312, 58, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("x/mnt      Nadi : ");
        jLabel30.setName("jLabel30"); // NOI18N
        PanelInput.add(jLabel30);
        jLabel30.setBounds(416, 312, 80, 23);

        TnadiBelum.setBackground(new java.awt.Color(245, 250, 240));
        TnadiBelum.setForeground(new java.awt.Color(0, 0, 0));
        TnadiBelum.setName("TnadiBelum"); // NOI18N
        TnadiBelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiBelumKeyPressed(evt);
            }
        });
        PanelInput.add(TnadiBelum);
        TnadiBelum.setBounds(496, 312, 58, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("x/mnt      SPO2 : ");
        jLabel29.setName("jLabel29"); // NOI18N
        PanelInput.add(jLabel29);
        jLabel29.setBounds(561, 312, 83, 23);

        TspoBelum.setBackground(new java.awt.Color(245, 250, 240));
        TspoBelum.setForeground(new java.awt.Color(0, 0, 0));
        TspoBelum.setName("TspoBelum"); // NOI18N
        TspoBelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TspoBelumKeyPressed(evt);
            }
        });
        PanelInput.add(TspoBelum);
        TspoBelum.setBounds(646, 312, 58, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("%");
        jLabel37.setName("jLabel37"); // NOI18N
        PanelInput.add(jLabel37);
        jLabel37.setBounds(709, 312, 30, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Hal-hal Yang Harus Diperhatikan :");
        jLabel76.setName("jLabel76"); // NOI18N
        PanelInput.add(jLabel76);
        jLabel76.setBounds(0, 340, 190, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N
        scrollPane14.setPreferredSize(new java.awt.Dimension(174, 100));

        TcatatanBelum.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TcatatanBelum.setColumns(20);
        TcatatanBelum.setRows(5);
        TcatatanBelum.setName("TcatatanBelum"); // NOI18N
        TcatatanBelum.setPreferredSize(new java.awt.Dimension(162, 2000));
        TcatatanBelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanBelumKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(TcatatanBelum);

        PanelInput.add(scrollPane14);
        scrollPane14.setBounds(195, 340, 535, 72);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Petugas Yang Menyerahkan :");
        jLabel77.setName("jLabel77"); // NOI18N
        PanelInput.add(jLabel77);
        jLabel77.setBounds(0, 417, 190, 23);

        TnmPetugasSerahBelum.setEditable(false);
        TnmPetugasSerahBelum.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugasSerahBelum.setName("TnmPetugasSerahBelum"); // NOI18N
        PanelInput.add(TnmPetugasSerahBelum);
        TnmPetugasSerahBelum.setBounds(195, 417, 500, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Petugas Yang Menerima :");
        jLabel78.setName("jLabel78"); // NOI18N
        PanelInput.add(jLabel78);
        jLabel78.setBounds(0, 445, 190, 23);

        TnmPetugasTerimaBelum.setEditable(false);
        TnmPetugasTerimaBelum.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugasTerimaBelum.setName("TnmPetugasTerimaBelum"); // NOI18N
        PanelInput.add(TnmPetugasTerimaBelum);
        TnmPetugasTerimaBelum.setBounds(195, 445, 500, 23);

        BtnPetugas1Belum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas1Belum.setMnemonic('2');
        BtnPetugas1Belum.setToolTipText("Alt+2");
        BtnPetugas1Belum.setName("BtnPetugas1Belum"); // NOI18N
        BtnPetugas1Belum.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas1Belum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas1BelumActionPerformed(evt);
            }
        });
        PanelInput.add(BtnPetugas1Belum);
        BtnPetugas1Belum.setBounds(700, 417, 28, 23);

        BtnPetugas2Belum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas2Belum.setMnemonic('2');
        BtnPetugas2Belum.setToolTipText("Alt+2");
        BtnPetugas2Belum.setName("BtnPetugas2Belum"); // NOI18N
        BtnPetugas2Belum.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas2Belum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas2BelumActionPerformed(evt);
            }
        });
        PanelInput.add(BtnPetugas2Belum);
        BtnPetugas2Belum.setBounds(700, 445, 28, 23);

        chkSaya1.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya1.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya1.setText("Saya Sendiri");
        chkSaya1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya1.setName("chkSaya1"); // NOI18N
        chkSaya1.setOpaque(false);
        chkSaya1.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya1ActionPerformed(evt);
            }
        });
        PanelInput.add(chkSaya1);
        chkSaya1.setBounds(735, 417, 87, 23);

        chkSaya2.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya2.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya2.setText("Saya Sendiri");
        chkSaya2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya2.setName("chkSaya2"); // NOI18N
        chkSaya2.setOpaque(false);
        chkSaya2.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya2ActionPerformed(evt);
            }
        });
        PanelInput.add(chkSaya2);
        chkSaya2.setBounds(735, 445, 87, 23);

        internalFrame20.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbTransferBelum.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbTransferBelum.setName("tbTransferBelum"); // NOI18N
        tbTransferBelum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTransferBelumMouseClicked(evt);
            }
        });
        tbTransferBelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTransferBelumKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbTransferBelum);

        internalFrame20.add(Scroll, java.awt.BorderLayout.CENTER);

        TabTransfer.addTab("Sebelum Tindakan", internalFrame20);

        internalFrame21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: SESUDAH TINDAKAN :.", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame21.setName("internalFrame21"); // NOI18N
        internalFrame21.setLayout(new java.awt.BorderLayout());

        PanelInput1.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang");
        PanelInput1.setComponentPopupMenu(jPopupMenu1);
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(192, 480));
        PanelInput1.setLayout(null);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("No. Rawat :");
        jLabel11.setName("jLabel11"); // NOI18N
        PanelInput1.add(jLabel11);
        jLabel11.setBounds(0, 10, 110, 23);

        TNoRwSudah.setEditable(false);
        TNoRwSudah.setForeground(new java.awt.Color(0, 0, 0));
        TNoRwSudah.setName("TNoRwSudah"); // NOI18N
        PanelInput1.add(TNoRwSudah);
        TNoRwSudah.setBounds(114, 10, 131, 23);

        TNoRMSudah.setEditable(false);
        TNoRMSudah.setForeground(new java.awt.Color(0, 0, 0));
        TNoRMSudah.setName("TNoRMSudah"); // NOI18N
        PanelInput1.add(TNoRMSudah);
        TNoRMSudah.setBounds(247, 10, 70, 23);

        TPasienSudah.setEditable(false);
        TPasienSudah.setForeground(new java.awt.Color(0, 0, 0));
        TPasienSudah.setHighlighter(null);
        TPasienSudah.setName("TPasienSudah"); // NOI18N
        PanelInput1.add(TPasienSudah);
        TPasienSudah.setBounds(319, 10, 410, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Ruang Rawat :");
        jLabel79.setName("jLabel79"); // NOI18N
        PanelInput1.add(jLabel79);
        jLabel79.setBounds(0, 38, 110, 23);

        TrgRawatSudah.setEditable(false);
        TrgRawatSudah.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawatSudah.setName("TrgRawatSudah"); // NOI18N
        PanelInput1.add(TrgRawatSudah);
        TrgRawatSudah.setBounds(115, 38, 388, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Kode Transfer :");
        jLabel80.setName("jLabel80"); // NOI18N
        PanelInput1.add(jLabel80);
        jLabel80.setBounds(505, 38, 87, 23);

        TkdTransferSudah.setEditable(false);
        TkdTransferSudah.setForeground(new java.awt.Color(0, 0, 0));
        TkdTransferSudah.setName("TkdTransferSudah"); // NOI18N
        PanelInput1.add(TkdTransferSudah);
        TkdTransferSudah.setBounds(600, 38, 129, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Tgl. Tindakan :");
        jLabel81.setName("jLabel81"); // NOI18N
        PanelInput1.add(jLabel81);
        jLabel81.setBounds(0, 66, 110, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Riwayat Alergi :");
        jLabel82.setName("jLabel82"); // NOI18N
        PanelInput1.add(jLabel82);
        jLabel82.setBounds(255, 66, 98, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Diagnosa :");
        jLabel83.setName("jLabel83"); // NOI18N
        PanelInput1.add(jLabel83);
        jLabel83.setBounds(0, 94, 110, 23);

        scrollPane15.setName("scrollPane15"); // NOI18N
        scrollPane15.setPreferredSize(new java.awt.Dimension(174, 100));

        TdiagnosaSudah.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TdiagnosaSudah.setColumns(20);
        TdiagnosaSudah.setRows(5);
        TdiagnosaSudah.setName("TdiagnosaSudah"); // NOI18N
        TdiagnosaSudah.setPreferredSize(new java.awt.Dimension(162, 700));
        TdiagnosaSudah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaSudahKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(TdiagnosaSudah);

        PanelInput1.add(scrollPane15);
        scrollPane15.setBounds(115, 94, 615, 72);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("Kategori Derajat Pendampingan Pasien :");
        jLabel84.setName("jLabel84"); // NOI18N
        PanelInput1.add(jLabel84);
        jLabel84.setBounds(115, 172, 200, 23);

        cmbKategoriSudah.setForeground(new java.awt.Color(0, 0, 0));
        cmbKategoriSudah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3" }));
        cmbKategoriSudah.setName("cmbKategoriSudah"); // NOI18N
        PanelInput1.add(cmbKategoriSudah);
        cmbKategoriSudah.setBounds(318, 172, 45, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Resiko Jatuh :");
        jLabel85.setName("jLabel85"); // NOI18N
        PanelInput1.add(jLabel85);
        jLabel85.setBounds(366, 172, 92, 23);

        cmbResikoSudah.setForeground(new java.awt.Color(0, 0, 0));
        cmbResikoSudah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ringan", "Sedang", "Berat" }));
        cmbResikoSudah.setName("cmbResikoSudah"); // NOI18N
        PanelInput1.add(cmbResikoSudah);
        cmbResikoSudah.setBounds(466, 172, 75, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Skala Nyeri :");
        jLabel86.setName("jLabel86"); // NOI18N
        PanelInput1.add(jLabel86);
        jLabel86.setBounds(540, 172, 80, 23);

        cmbSkalaSudah.setForeground(new java.awt.Color(0, 0, 0));
        cmbSkalaSudah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cmbSkalaSudah.setName("cmbSkalaSudah"); // NOI18N
        PanelInput1.add(cmbSkalaSudah);
        cmbSkalaSudah.setBounds(628, 172, 45, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Paparan Infeksi :");
        jLabel87.setName("jLabel87"); // NOI18N
        PanelInput1.add(jLabel87);
        jLabel87.setBounds(0, 200, 110, 23);

        cmbPaparanSudah.setForeground(new java.awt.Color(0, 0, 0));
        cmbPaparanSudah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbPaparanSudah.setName("cmbPaparanSudah"); // NOI18N
        cmbPaparanSudah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPaparanSudahActionPerformed(evt);
            }
        });
        PanelInput1.add(cmbPaparanSudah);
        cmbPaparanSudah.setBounds(115, 200, 67, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Jenis Paparan :");
        jLabel88.setName("jLabel88"); // NOI18N
        PanelInput1.add(jLabel88);
        jLabel88.setBounds(185, 200, 92, 23);

        cmbJnsPaparanSudah.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsPaparanSudah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Air Bone", "Percikan", "Kontak Langsung" }));
        cmbJnsPaparanSudah.setName("cmbJnsPaparanSudah"); // NOI18N
        PanelInput1.add(cmbJnsPaparanSudah);
        cmbJnsPaparanSudah.setBounds(284, 200, 120, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Alat Medis Yang Digunakan :");
        jLabel89.setName("jLabel89"); // NOI18N
        PanelInput1.add(jLabel89);
        jLabel89.setBounds(0, 228, 170, 23);

        ChkCateterSudah.setBackground(new java.awt.Color(255, 255, 250));
        ChkCateterSudah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCateterSudah.setForeground(new java.awt.Color(0, 0, 0));
        ChkCateterSudah.setText("IV Catheter");
        ChkCateterSudah.setBorderPainted(true);
        ChkCateterSudah.setBorderPaintedFlat(true);
        ChkCateterSudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCateterSudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCateterSudah.setName("ChkCateterSudah"); // NOI18N
        ChkCateterSudah.setOpaque(false);
        ChkCateterSudah.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput1.add(ChkCateterSudah);
        ChkCateterSudah.setBounds(176, 228, 100, 23);

        ChkNgtSudah.setBackground(new java.awt.Color(255, 255, 250));
        ChkNgtSudah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNgtSudah.setForeground(new java.awt.Color(0, 0, 0));
        ChkNgtSudah.setText("NGT");
        ChkNgtSudah.setBorderPainted(true);
        ChkNgtSudah.setBorderPaintedFlat(true);
        ChkNgtSudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNgtSudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNgtSudah.setName("ChkNgtSudah"); // NOI18N
        ChkNgtSudah.setOpaque(false);
        ChkNgtSudah.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput1.add(ChkNgtSudah);
        ChkNgtSudah.setBounds(284, 228, 60, 23);

        ChkNasalSudah.setBackground(new java.awt.Color(255, 255, 250));
        ChkNasalSudah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNasalSudah.setForeground(new java.awt.Color(0, 0, 0));
        ChkNasalSudah.setText("Nasal Kanula / Mask / Ventilator");
        ChkNasalSudah.setBorderPainted(true);
        ChkNasalSudah.setBorderPaintedFlat(true);
        ChkNasalSudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNasalSudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNasalSudah.setName("ChkNasalSudah"); // NOI18N
        ChkNasalSudah.setOpaque(false);
        ChkNasalSudah.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput1.add(ChkNasalSudah);
        ChkNasalSudah.setBounds(380, 228, 190, 23);

        ChkKateterSudah.setBackground(new java.awt.Color(255, 255, 250));
        ChkKateterSudah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKateterSudah.setForeground(new java.awt.Color(0, 0, 0));
        ChkKateterSudah.setText("Kateter Urine");
        ChkKateterSudah.setBorderPainted(true);
        ChkKateterSudah.setBorderPaintedFlat(true);
        ChkKateterSudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKateterSudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKateterSudah.setName("ChkKateterSudah"); // NOI18N
        ChkKateterSudah.setOpaque(false);
        ChkKateterSudah.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput1.add(ChkKateterSudah);
        ChkKateterSudah.setBounds(176, 256, 100, 23);

        ChkDrainSudah.setBackground(new java.awt.Color(255, 255, 250));
        ChkDrainSudah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDrainSudah.setForeground(new java.awt.Color(0, 0, 0));
        ChkDrainSudah.setText("Drain / WSD");
        ChkDrainSudah.setBorderPainted(true);
        ChkDrainSudah.setBorderPaintedFlat(true);
        ChkDrainSudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDrainSudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDrainSudah.setName("ChkDrainSudah"); // NOI18N
        ChkDrainSudah.setOpaque(false);
        ChkDrainSudah.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput1.add(ChkDrainSudah);
        ChkDrainSudah.setBounds(284, 256, 90, 23);

        ChkAlatSudah.setBackground(new java.awt.Color(255, 255, 250));
        ChkAlatSudah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAlatSudah.setForeground(new java.awt.Color(0, 0, 0));
        ChkAlatSudah.setText("Alat Lain");
        ChkAlatSudah.setBorderPainted(true);
        ChkAlatSudah.setBorderPaintedFlat(true);
        ChkAlatSudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAlatSudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAlatSudah.setName("ChkAlatSudah"); // NOI18N
        ChkAlatSudah.setOpaque(false);
        ChkAlatSudah.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkAlatSudah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAlatSudahActionPerformed(evt);
            }
        });
        PanelInput1.add(ChkAlatSudah);
        ChkAlatSudah.setBounds(380, 256, 70, 23);

        TalatLainSudah.setForeground(new java.awt.Color(0, 0, 0));
        TalatLainSudah.setName("TalatLainSudah"); // NOI18N
        TalatLainSudah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalatLainSudahKeyPressed(evt);
            }
        });
        PanelInput1.add(TalatLainSudah);
        TalatLainSudah.setBounds(450, 256, 280, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Kondisi Klinis Sebelum Transfer :");
        jLabel90.setName("jLabel90"); // NOI18N
        PanelInput1.add(jLabel90);
        jLabel90.setBounds(0, 284, 190, 23);

        ChkCMSudah.setBackground(new java.awt.Color(255, 255, 250));
        ChkCMSudah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCMSudah.setForeground(new java.awt.Color(0, 0, 0));
        ChkCMSudah.setText("CM");
        ChkCMSudah.setBorderPainted(true);
        ChkCMSudah.setBorderPaintedFlat(true);
        ChkCMSudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCMSudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCMSudah.setName("ChkCMSudah"); // NOI18N
        ChkCMSudah.setOpaque(false);
        ChkCMSudah.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput1.add(ChkCMSudah);
        ChkCMSudah.setBounds(198, 284, 50, 23);

        ChkSoporSudah.setBackground(new java.awt.Color(255, 255, 250));
        ChkSoporSudah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSoporSudah.setForeground(new java.awt.Color(0, 0, 0));
        ChkSoporSudah.setText("Sopor");
        ChkSoporSudah.setBorderPainted(true);
        ChkSoporSudah.setBorderPaintedFlat(true);
        ChkSoporSudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSoporSudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSoporSudah.setName("ChkSoporSudah"); // NOI18N
        ChkSoporSudah.setOpaque(false);
        ChkSoporSudah.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput1.add(ChkSoporSudah);
        ChkSoporSudah.setBounds(256, 284, 60, 23);

        ChkApatisSudah.setBackground(new java.awt.Color(255, 255, 250));
        ChkApatisSudah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkApatisSudah.setForeground(new java.awt.Color(0, 0, 0));
        ChkApatisSudah.setText("Apatis");
        ChkApatisSudah.setBorderPainted(true);
        ChkApatisSudah.setBorderPaintedFlat(true);
        ChkApatisSudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkApatisSudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkApatisSudah.setName("ChkApatisSudah"); // NOI18N
        ChkApatisSudah.setOpaque(false);
        ChkApatisSudah.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput1.add(ChkApatisSudah);
        ChkApatisSudah.setBounds(324, 284, 60, 23);

        ChkSomnolenSudah.setBackground(new java.awt.Color(255, 255, 250));
        ChkSomnolenSudah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSomnolenSudah.setForeground(new java.awt.Color(0, 0, 0));
        ChkSomnolenSudah.setText("Somnolen");
        ChkSomnolenSudah.setBorderPainted(true);
        ChkSomnolenSudah.setBorderPaintedFlat(true);
        ChkSomnolenSudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSomnolenSudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSomnolenSudah.setName("ChkSomnolenSudah"); // NOI18N
        ChkSomnolenSudah.setOpaque(false);
        ChkSomnolenSudah.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput1.add(ChkSomnolenSudah);
        ChkSomnolenSudah.setBounds(390, 284, 78, 23);

        ChkDalamSudah.setBackground(new java.awt.Color(255, 255, 250));
        ChkDalamSudah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDalamSudah.setForeground(new java.awt.Color(0, 0, 0));
        ChkDalamSudah.setText("Dalam Pengaruh Sedasi");
        ChkDalamSudah.setBorderPainted(true);
        ChkDalamSudah.setBorderPaintedFlat(true);
        ChkDalamSudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDalamSudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDalamSudah.setName("ChkDalamSudah"); // NOI18N
        ChkDalamSudah.setOpaque(false);
        ChkDalamSudah.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput1.add(ChkDalamSudah);
        ChkDalamSudah.setBounds(476, 284, 150, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Tekanan Darah :");
        jLabel91.setName("jLabel91"); // NOI18N
        PanelInput1.add(jLabel91);
        jLabel91.setBounds(0, 312, 190, 23);

        TtensiSudah.setBackground(new java.awt.Color(245, 250, 240));
        TtensiSudah.setForeground(new java.awt.Color(0, 0, 0));
        TtensiSudah.setName("TtensiSudah"); // NOI18N
        TtensiSudah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtensiSudahKeyPressed(evt);
            }
        });
        PanelInput1.add(TtensiSudah);
        TtensiSudah.setBounds(198, 312, 70, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("mmHg       RR : ");
        jLabel28.setName("jLabel28"); // NOI18N
        PanelInput1.add(jLabel28);
        jLabel28.setBounds(273, 312, 78, 23);

        TrrSudah.setBackground(new java.awt.Color(245, 250, 240));
        TrrSudah.setForeground(new java.awt.Color(0, 0, 0));
        TrrSudah.setName("TrrSudah"); // NOI18N
        TrrSudah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrSudahKeyPressed(evt);
            }
        });
        PanelInput1.add(TrrSudah);
        TrrSudah.setBounds(353, 312, 58, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("x/mnt      Nadi : ");
        jLabel31.setName("jLabel31"); // NOI18N
        PanelInput1.add(jLabel31);
        jLabel31.setBounds(416, 312, 80, 23);

        TnadiSudah.setBackground(new java.awt.Color(245, 250, 240));
        TnadiSudah.setForeground(new java.awt.Color(0, 0, 0));
        TnadiSudah.setName("TnadiSudah"); // NOI18N
        TnadiSudah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiSudahKeyPressed(evt);
            }
        });
        PanelInput1.add(TnadiSudah);
        TnadiSudah.setBounds(496, 312, 58, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("x/mnt      SPO2 : ");
        jLabel32.setName("jLabel32"); // NOI18N
        PanelInput1.add(jLabel32);
        jLabel32.setBounds(561, 312, 83, 23);

        TspoSudah.setBackground(new java.awt.Color(245, 250, 240));
        TspoSudah.setForeground(new java.awt.Color(0, 0, 0));
        TspoSudah.setName("TspoSudah"); // NOI18N
        TspoSudah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TspoSudahKeyPressed(evt);
            }
        });
        PanelInput1.add(TspoSudah);
        TspoSudah.setBounds(646, 312, 58, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("%");
        jLabel38.setName("jLabel38"); // NOI18N
        PanelInput1.add(jLabel38);
        jLabel38.setBounds(709, 312, 30, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Hal-hal Yang Harus Diperhatikan :");
        jLabel92.setName("jLabel92"); // NOI18N
        PanelInput1.add(jLabel92);
        jLabel92.setBounds(0, 340, 190, 23);

        scrollPane16.setName("scrollPane16"); // NOI18N
        scrollPane16.setPreferredSize(new java.awt.Dimension(174, 100));

        TcatatanSudah.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TcatatanSudah.setColumns(20);
        TcatatanSudah.setRows(5);
        TcatatanSudah.setName("TcatatanSudah"); // NOI18N
        TcatatanSudah.setPreferredSize(new java.awt.Dimension(162, 2000));
        TcatatanSudah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanSudahKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(TcatatanSudah);

        PanelInput1.add(scrollPane16);
        scrollPane16.setBounds(195, 340, 535, 72);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Petugas Yang Menyerahkan :");
        jLabel93.setName("jLabel93"); // NOI18N
        PanelInput1.add(jLabel93);
        jLabel93.setBounds(0, 417, 190, 23);

        TnmPetugasSerahSudah.setEditable(false);
        TnmPetugasSerahSudah.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugasSerahSudah.setName("TnmPetugasSerahSudah"); // NOI18N
        PanelInput1.add(TnmPetugasSerahSudah);
        TnmPetugasSerahSudah.setBounds(195, 417, 500, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Petugas Yang Menerima :");
        jLabel94.setName("jLabel94"); // NOI18N
        PanelInput1.add(jLabel94);
        jLabel94.setBounds(0, 445, 190, 23);

        TnmPetugasTerimaSudah.setEditable(false);
        TnmPetugasTerimaSudah.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugasTerimaSudah.setName("TnmPetugasTerimaSudah"); // NOI18N
        PanelInput1.add(TnmPetugasTerimaSudah);
        TnmPetugasTerimaSudah.setBounds(195, 445, 500, 23);

        BtnPetugas1Sudah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas1Sudah.setMnemonic('2');
        BtnPetugas1Sudah.setToolTipText("Alt+2");
        BtnPetugas1Sudah.setName("BtnPetugas1Sudah"); // NOI18N
        BtnPetugas1Sudah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas1Sudah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas1SudahActionPerformed(evt);
            }
        });
        PanelInput1.add(BtnPetugas1Sudah);
        BtnPetugas1Sudah.setBounds(700, 417, 28, 23);

        BtnPetugas2Sudah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas2Sudah.setMnemonic('2');
        BtnPetugas2Sudah.setToolTipText("Alt+2");
        BtnPetugas2Sudah.setName("BtnPetugas2Sudah"); // NOI18N
        BtnPetugas2Sudah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas2Sudah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas2SudahActionPerformed(evt);
            }
        });
        PanelInput1.add(BtnPetugas2Sudah);
        BtnPetugas2Sudah.setBounds(700, 445, 28, 23);

        chkSaya3.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya3.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya3.setText("Saya Sendiri");
        chkSaya3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya3.setName("chkSaya3"); // NOI18N
        chkSaya3.setOpaque(false);
        chkSaya3.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya3ActionPerformed(evt);
            }
        });
        PanelInput1.add(chkSaya3);
        chkSaya3.setBounds(735, 417, 87, 23);

        chkSaya4.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya4.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya4.setText("Saya Sendiri");
        chkSaya4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya4.setName("chkSaya4"); // NOI18N
        chkSaya4.setOpaque(false);
        chkSaya4.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya4ActionPerformed(evt);
            }
        });
        PanelInput1.add(chkSaya4);
        chkSaya4.setBounds(735, 445, 87, 23);

        TtglTindakanSudah.setEditable(false);
        TtglTindakanSudah.setForeground(new java.awt.Color(0, 0, 0));
        TtglTindakanSudah.setName("TtglTindakanSudah"); // NOI18N
        PanelInput1.add(TtglTindakanSudah);
        TtglTindakanSudah.setBounds(115, 66, 100, 23);

        TRiwAlergiSudah.setEditable(false);
        TRiwAlergiSudah.setForeground(new java.awt.Color(0, 0, 0));
        TRiwAlergiSudah.setName("TRiwAlergiSudah"); // NOI18N
        PanelInput1.add(TRiwAlergiSudah);
        TRiwAlergiSudah.setBounds(359, 66, 70, 23);

        internalFrame21.add(PanelInput1, java.awt.BorderLayout.PAGE_START);

        Scroll1.setComponentPopupMenu(jPopupMenu1);
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbTransferSudah.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbTransferSudah.setName("tbTransferSudah"); // NOI18N
        tbTransferSudah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTransferSudahMouseClicked(evt);
            }
        });
        tbTransferSudah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTransferSudahKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbTransferSudah);

        internalFrame21.add(Scroll1, java.awt.BorderLayout.CENTER);

        TabTransfer.addTab("Sesudah Tindakan", internalFrame21);

        internalFrame1.add(TabTransfer, java.awt.BorderLayout.CENTER);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(900, 43));
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
        Tinstruksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane4.setViewportView(Tinstruksi);

        panelGlass14.add(scrollPane4, java.awt.BorderLayout.CENTER);

        FormMenu.add(panelGlass14);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelAccor, java.awt.BorderLayout.EAST);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TabTransfer.getSelectedIndex() == 0) {
            if (TNoRwBelum.getText().trim().equals("")) {
                Valid.textKosong(TPasienBelum, "Pasien");
            } else {
                AutoNomorKode();
                cekDataBelum();
                if (Sequel.menyimpantf("transfer_sebelum_tindakan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 31, new String[]{
                    TNoRwBelum.getText(), TkdTransferBelum.getText(), TrgRawatBelum.getText(), Valid.SetTgl(TtglTindakanBelum.getSelectedItem() + ""),
                    cmbRiwAlergiBelum.getSelectedItem().toString(), TdiagnosaBelum.getText(), cmbKategoriBelum.getSelectedItem().toString(), cmbResikoBelum.getSelectedItem().toString(),
                    cmbSkalaBelum.getSelectedItem().toString(), cmbPaparanBelum.getSelectedItem().toString(), cmbJnsPaparanBelum.getSelectedItem().toString(),
                    cateterBlm, ngtBlm, nasalBlm, kateterUrinBlm, drainBlm, alatBlm, TalatLainBelum.getText(), cmBlm, soporBlm, apatisBlm, sedasiBlm, somnolenBlm,
                    TtensiBelum.getText(), TrrBelum.getText().trim(), TnadiBelum.getText().trim(), TspoBelum.getText().trim(), TcatatanBelum.getText(), nipSerahBlm,
                    nipTerimaBlm, Sequel.cariIsi("select now()")
                }) == true) {
                    TCari.setText(TNoRwBelum.getText());
                    emptTeksSebelum();
                    tampilSebelum();
                }
            }
        } else if (TabTransfer.getSelectedIndex() == 1) {
            if (TNoRwSudah.getText().trim().equals("")) {
                Valid.textKosong(TPasienSudah, "Pasien");
            } else if (Sequel.cariInteger("select count(-1) from transfer_sesudah_tindakan where no_rawat='" + TNoRwSudah.getText() + "' and kode_transfer='" + TkdTransferSudah.getText() + "'") > 0) {
                JOptionPane.showMessageDialog(null, "Data transfer pasien sesudah tindakan telah tersimpan sebelumnya, proses simpan data gagal..!!!!");
            } else {
                cekDataSudah();
                if (Sequel.menyimpantf("transfer_sesudah_tindakan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 31, new String[]{
                    TNoRwSudah.getText(), TkdTransferSudah.getText(), TrgRawatSudah.getText(), tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 33).toString(),
                    TRiwAlergiSudah.getText(), TdiagnosaSudah.getText(), cmbKategoriSudah.getSelectedItem().toString(), cmbResikoSudah.getSelectedItem().toString(),
                    cmbSkalaSudah.getSelectedItem().toString(), cmbPaparanSudah.getSelectedItem().toString(), cmbJnsPaparanSudah.getSelectedItem().toString(),
                    cateterSdh, ngtSdh, nasalSdh, kateterUrinSdh, drainSdh, alatSdh, TalatLainSudah.getText(), cmSdh, soporSdh, apatisSdh, sedasiSdh, somnolenSdh,
                    TtensiSudah.getText(), TrrSudah.getText().trim(), TnadiSudah.getText().trim(), TspoSudah.getText().trim(), TcatatanSudah.getText(), nipSerahSdh,
                    nipTerimaSdh, Sequel.cariIsi("select now()")
                }) == true) {
                    TCari.setText(TNoRwSudah.getText());
                    emptTeksSesudah();
                    tampilSesudah();
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        ChkAccor.setSelected(false);
        isMenu();
        
        if (TabTransfer.getSelectedIndex() == 0) {
            emptTeksSebelum();
        } else if (TabTransfer.getSelectedIndex() == 1) {
            emptTeksSesudah();
        }
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBatalActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TabTransfer.getSelectedIndex() == 0) {
            if (TNoRwBelum.getText().trim().equals("")) {
                Valid.textKosong(TPasienBelum, "Pasien");
            } else {
                if (tbTransferBelum.getSelectedRow() > -1) {
                    gantiSebelum();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                }
            }
        } else if (TabTransfer.getSelectedIndex() == 1) {
            if (tbTransferSudah.getRowCount() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Klik tombol simpan, untuk menyimpan data..!!");
            } else {
                if (cekGanti.equals("dari sebelum")) {
                    gantiSesudah(Sequel.cariIsi("select waktu_simpan from transfer_sesudah_tindakan "
                            + "where no_rawat='" + TNoRwSudah.getText() + "' and kode_transfer='" + TkdTransferSudah.getText() + "'"));
                } else if (cekGanti.equals("dari tersimpan")) {
                    if (TNoRwSudah.getText().trim().equals("")) {
                        Valid.textKosong(TPasienSudah, "Pasien");
                    } else {
                        if (tbTransferSudah.getSelectedRow() > -1) {
                            gantiSesudah(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 47).toString());
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                        }
                    }
                }
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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnBatal,TCari);}
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
        ChkAccor.setSelected(false);
        isMenu();
        
        if (TabTransfer.getSelectedIndex() == 0) {
            tampilSebelum();
        } else if (TabTransfer.getSelectedIndex() == 1) {
            tampilSesudah();
        }
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
        
        if (TabTransfer.getSelectedIndex() == 0) {
            emptTeksSebelum();
        } else if (TabTransfer.getSelectedIndex() == 1) {
            emptTeksSesudah();
        }
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
            TCari.setText("");
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbTransferBelumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTransferBelumMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getDataBelum();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbTransferBelumMouseClicked

    private void tbTransferBelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTransferBelumKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataBelum();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbTransferBelumKeyPressed

    private void TdiagnosaBelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaBelumKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbKategoriBelum.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaBelumKeyPressed

    private void TtensiBelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtensiBelumKeyPressed
        Valid.pindah(evt, TtensiBelum, TrrBelum);
    }//GEN-LAST:event_TtensiBelumKeyPressed

    private void TrrBelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrBelumKeyPressed
        Valid.pindah(evt, TtensiBelum, TnadiBelum);
    }//GEN-LAST:event_TrrBelumKeyPressed

    private void TnadiBelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiBelumKeyPressed
        Valid.pindah(evt, TrrBelum, TspoBelum);
    }//GEN-LAST:event_TnadiBelumKeyPressed

    private void TspoBelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TspoBelumKeyPressed
        Valid.pindah(evt, TnadiBelum, TcatatanBelum);
    }//GEN-LAST:event_TspoBelumKeyPressed

    private void TcatatanBelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanBelumKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnPetugas1Belum.requestFocus();
        }
    }//GEN-LAST:event_TcatatanBelumKeyPressed

    private void ChkAlatBelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAlatBelumActionPerformed
        TalatLainBelum.setText("");
        if (ChkAlatBelum.isSelected() == true) {
            TalatLainBelum.setEnabled(true);
            TalatLainBelum.requestFocus();
        } else {
            TalatLainBelum.setEnabled(false);
        }
    }//GEN-LAST:event_ChkAlatBelumActionPerformed

    private void TalatLainBelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalatLainBelumKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ChkCMBelum.requestFocus();
        }
    }//GEN-LAST:event_TalatLainBelumKeyPressed

    private void BtnPetugas1BelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas1BelumActionPerformed
        ChkAccor.setSelected(false);
        isMenu();
        
        pilihan = 0;
        pilihan = 1;
        akses.setform("RMPasienUntukTindakan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas1BelumActionPerformed

    private void BtnPetugas2BelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas2BelumActionPerformed
        ChkAccor.setSelected(false);
        isMenu();
        
        pilihan = 0;
        pilihan = 2;
        akses.setform("RMPasienUntukTindakan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas2BelumActionPerformed

    private void chkSaya1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya1ActionPerformed
        if (chkSaya1.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipSerahBlm = "-";
                TnmPetugasSerahBelum.setText("-");
            } else {
                nipSerahBlm = akses.getkode();
                TnmPetugasSerahBelum.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipSerahBlm + "'"));
            }
        } else if (chkSaya1.isSelected() == false) {
            nipSerahBlm = "-";
            TnmPetugasSerahBelum.setText("-");
        }
    }//GEN-LAST:event_chkSaya1ActionPerformed

    private void chkSaya2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya2ActionPerformed
        if (chkSaya2.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipTerimaBlm = "-";
                TnmPetugasTerimaBelum.setText("-");
            } else {
                nipTerimaBlm = akses.getkode();
                TnmPetugasTerimaBelum.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipTerimaBlm + "'"));
            }
        } else if (chkSaya2.isSelected() == false) {
            nipTerimaBlm = "-";
            TnmPetugasTerimaBelum.setText("-");
        }
    }//GEN-LAST:event_chkSaya2ActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TabTransfer.getSelectedIndex() == 0) {
            hapusSebelum();
        } else if (TabTransfer.getSelectedIndex() == 1) {
            hapusSesudah();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampilSebelum();
        TabTransfer.setSelectedIndex(0);
        TabTransfer.setEnabledAt(1, false);
    }//GEN-LAST:event_formWindowOpened

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRwBelum.getText().trim().equals("") || TPasienBelum.getText().trim().equals("")) {
            Valid.textKosong(TNoRwBelum, "Pasien");
        } else {
            akses.setform("RMPasienUntukTindakan");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRwBelum.getText(), TPasienBelum.getText(), TNoRMBelum.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void cmbPaparanBelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPaparanBelumActionPerformed
        cmbJnsPaparanBelum.setSelectedIndex(0);
        if (cmbPaparanBelum.getSelectedIndex() == 1) {
            cmbJnsPaparanBelum.setEnabled(true);
            cmbJnsPaparanBelum.requestFocus();
        } else {
            cmbJnsPaparanBelum.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPaparanBelumActionPerformed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRwBelum.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMPasienUntukTindakan");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRwBelum.getText(), TNoRMBelum.getText(), TPasienBelum.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void TabTransferMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabTransferMouseClicked
        ChkAccor.setSelected(false);
        isMenu();
        
        if (TabTransfer.getSelectedIndex() == 0) {
            TabTransfer.setEnabledAt(1, false);
            emptTeksSebelum();
            tampilSebelum();
        } else if (TabTransfer.getSelectedIndex() == 1) {
            tampilSesudah();
        }
    }//GEN-LAST:event_TabTransferMouseClicked

    private void TdiagnosaSudahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaSudahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbKategoriSudah.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaSudahKeyPressed

    private void cmbPaparanSudahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPaparanSudahActionPerformed
        cmbJnsPaparanSudah.setSelectedIndex(0);
        if (cmbPaparanSudah.getSelectedIndex() == 1) {
            cmbJnsPaparanSudah.setEnabled(true);
            cmbJnsPaparanSudah.requestFocus();
        } else {
            cmbJnsPaparanSudah.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPaparanSudahActionPerformed

    private void ChkAlatSudahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAlatSudahActionPerformed
        TalatLainSudah.setText("");
        if (ChkAlatSudah.isSelected() == true) {
            TalatLainSudah.setEnabled(true);
            TalatLainSudah.requestFocus();
        } else {
            TalatLainSudah.setEnabled(false);
        }
    }//GEN-LAST:event_ChkAlatSudahActionPerformed

    private void TalatLainSudahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalatLainSudahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ChkCMSudah.requestFocus();
        }
    }//GEN-LAST:event_TalatLainSudahKeyPressed

    private void TtensiSudahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtensiSudahKeyPressed
        Valid.pindah(evt, TtensiSudah, TrrSudah);
    }//GEN-LAST:event_TtensiSudahKeyPressed

    private void TrrSudahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrSudahKeyPressed
        Valid.pindah(evt, TtensiSudah, TnadiSudah);
    }//GEN-LAST:event_TrrSudahKeyPressed

    private void TnadiSudahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiSudahKeyPressed
        Valid.pindah(evt, TrrSudah, TspoSudah);
    }//GEN-LAST:event_TnadiSudahKeyPressed

    private void TspoSudahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TspoSudahKeyPressed
        Valid.pindah(evt, TnadiSudah, TcatatanSudah);
    }//GEN-LAST:event_TspoSudahKeyPressed

    private void TcatatanSudahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanSudahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnPetugas1Sudah.requestFocus();
        }
    }//GEN-LAST:event_TcatatanSudahKeyPressed

    private void BtnPetugas1SudahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas1SudahActionPerformed
        ChkAccor.setSelected(false);
        isMenu();
        
        pilihan = 0;
        pilihan = 3;
        akses.setform("RMPasienUntukTindakan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas1SudahActionPerformed

    private void BtnPetugas2SudahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas2SudahActionPerformed
        ChkAccor.setSelected(false);
        isMenu();
        
        pilihan = 0;
        pilihan = 4;
        akses.setform("RMPasienUntukTindakan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas2SudahActionPerformed

    private void chkSaya3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya3ActionPerformed
        if (chkSaya3.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipSerahSdh = "-";
                TnmPetugasSerahSudah.setText("-");
            } else {
                nipSerahSdh = akses.getkode();
                TnmPetugasSerahSudah.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipSerahSdh + "'"));
            }
        } else if (chkSaya3.isSelected() == false) {
            nipSerahSdh = "-";
            TnmPetugasSerahSudah.setText("-");
        }
    }//GEN-LAST:event_chkSaya3ActionPerformed

    private void chkSaya4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya4ActionPerformed
        if (chkSaya4.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipTerimaSdh = "-";
                TnmPetugasTerimaSudah.setText("-");
            } else {
                nipTerimaSdh = akses.getkode();
                TnmPetugasTerimaSudah.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipTerimaSdh + "'"));
            }
        } else if (chkSaya4.isSelected() == false) {
            nipTerimaSdh = "-";
            TnmPetugasTerimaSudah.setText("-");
        }
    }//GEN-LAST:event_chkSaya4ActionPerformed

    private void tbTransferSudahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTransferSudahMouseClicked
        if(tabMode1.getRowCount()!=0){
            try {
                getDataSudah();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTransferSudahMouseClicked

    private void tbTransferSudahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTransferSudahKeyPressed
        if(tabMode1.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataSudah();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTransferSudahKeyPressed

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

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        JOptionPane.showMessageDialog(rootPane, "Masih dalam proses dikerjakan..!!");
        
//        if (tbRestrain.getSelectedRow() > -1) {
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
//            param.put("gcs", "E : " + Tgcse.getText() + ", M : " + Tgcsm.getText() + ", V : " + Tgcsv.getText());
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
//                + ", Pukul : " + Sequel.cariIsi("select time_format(jam_asesmen,'%H:%i') from asesmen_restrain where no_rawat='" + TNoRw.getText() + "'") + " WITA");
//            param.put("petugas", "(" + TnmPetugas.getText() + ")");
//
//            Valid.MyReport("rptCetakAsesmenRestrain.jasper", "report", "::[ Laporan Asesmen Restrain hal. 1 ]::",
//                "SELECT now() tanggal", param);
//
//            emptTeks();
//            TabRawat.setSelectedIndex(1);
//            tampil();
//        } else {
//            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
//            tbRestrain.requestFocus();
//        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPasienUntukTindakan dialog = new RMPasienUntukTindakan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnPetugas1Belum;
    private widget.Button BtnPetugas1Sudah;
    private widget.Button BtnPetugas2Belum;
    private widget.Button BtnPetugas2Sudah;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAccor;
    public widget.CekBox ChkAlatBelum;
    public widget.CekBox ChkAlatSudah;
    public widget.CekBox ChkApatisBelum;
    public widget.CekBox ChkApatisSudah;
    public widget.CekBox ChkCMBelum;
    public widget.CekBox ChkCMSudah;
    public widget.CekBox ChkCateterBelum;
    public widget.CekBox ChkCateterSudah;
    public widget.CekBox ChkDalamBelum;
    public widget.CekBox ChkDalamSudah;
    public widget.CekBox ChkDrainBelum;
    public widget.CekBox ChkDrainSudah;
    public widget.CekBox ChkKateterBelum;
    public widget.CekBox ChkKateterSudah;
    public widget.CekBox ChkNasalBelum;
    public widget.CekBox ChkNasalSudah;
    public widget.CekBox ChkNgtBelum;
    public widget.CekBox ChkNgtSudah;
    public widget.CekBox ChkSomnolenBelum;
    public widget.CekBox ChkSomnolenSudah;
    public widget.CekBox ChkSoporBelum;
    public widget.CekBox ChkSoporSudah;
    private widget.PanelBiasa FormMenu;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private widget.PanelBiasa PanelAccor;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll4;
    public widget.TextBox TCari;
    private widget.TextBox TNoRMBelum;
    private widget.TextBox TNoRMSudah;
    private widget.TextBox TNoRwBelum;
    private widget.TextBox TNoRwSudah;
    private widget.TextBox TPasienBelum;
    private widget.TextBox TPasienSudah;
    private widget.TextBox TRiwAlergiSudah;
    private javax.swing.JTabbedPane TabTransfer;
    private widget.TextBox TalatLainBelum;
    private widget.TextBox TalatLainSudah;
    private widget.TextArea TcatatanBelum;
    private widget.TextArea TcatatanSudah;
    private widget.TextArea TdiagnosaBelum;
    private widget.TextArea TdiagnosaSudah;
    private widget.TextArea Thasil;
    private widget.TextArea Tinstruksi;
    private widget.TextBox TkdTransferBelum;
    private widget.TextBox TkdTransferSudah;
    private widget.TextBox TnadiBelum;
    private widget.TextBox TnadiSudah;
    private widget.TextBox TnmPetugasSerahBelum;
    private widget.TextBox TnmPetugasSerahSudah;
    private widget.TextBox TnmPetugasTerimaBelum;
    private widget.TextBox TnmPetugasTerimaSudah;
    private widget.TextBox TrgRawatBelum;
    private widget.TextBox TrgRawatSudah;
    private widget.TextBox TrrBelum;
    private widget.TextBox TrrSudah;
    private widget.TextBox TspoBelum;
    private widget.TextBox TspoSudah;
    private widget.TextBox TtensiBelum;
    private widget.TextBox TtensiSudah;
    private widget.Tanggal TtglTindakanBelum;
    private widget.TextBox TtglTindakanSudah;
    private widget.CekBox chkSaya1;
    private widget.CekBox chkSaya2;
    private widget.CekBox chkSaya3;
    private widget.CekBox chkSaya4;
    private widget.ComboBox cmbJnsPaparanBelum;
    private widget.ComboBox cmbJnsPaparanSudah;
    private widget.ComboBox cmbKategoriBelum;
    private widget.ComboBox cmbKategoriSudah;
    private widget.ComboBox cmbPaparanBelum;
    private widget.ComboBox cmbPaparanSudah;
    private widget.ComboBox cmbResikoBelum;
    private widget.ComboBox cmbResikoSudah;
    private widget.ComboBox cmbRiwAlergiBelum;
    private widget.ComboBox cmbSkalaBelum;
    private widget.ComboBox cmbSkalaSudah;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame20;
    private widget.InternalFrame internalFrame21;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
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
    private widget.Label jLabel89;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbCPPT;
    private widget.Table tbTransferBelum;
    private widget.Table tbTransferSudah;
    // End of variables declaration//GEN-END:variables

    public void tampilSebelum() {
        Valid.tabelKosong(tabMode);
        try {
            if (TCari.getText().equals("")) {
                ps = koneksi.prepareStatement("SELECT tb.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                        + "date_format(tb.tgl_tindakan,'%d-%m-%Y') tgltindakan, if(tb.cateter='ya','V','') cat, if(tb.ngt='ya','V','') nngt, "
                        + "if(tb.nasal='ya','V','') nas, if(tb.kateter_urin='ya','V','') kat, if(tb.drain='ya','V','') dra, "
                        + "if(tb.alat_lain='ya','V','') alt, if(tb.kondisi_cm='ya','V','') ccm, if(tb.kondisi_sopor='ya','V','') sop, "
                        + "if(tb.kondisi_apatis='ya','V','') apa, if(tb.kondisi_pengaruh_sedasi='ya','V','') peng, "
                        + "if(tb.kondisi_somnolen='ya','V','') som, pg1.nama ptgsSerah, pg2.nama ptgsTerima, "
                        + "time_format(tb.waktu_simpan,'%H:%i') jamTindakan FROM transfer_sebelum_tindakan tb "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=tb.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg1 on pg1.nik=tb.nip_menyerahkan INNER JOIN pegawai pg2 on pg2.nik=tb.nip_menerima WHERE "
                        + "tb.no_rawat like ? or "
                        + "tb.kode_transfer like ? or "
                        + "p.no_rkm_medis like ? or "
                        + "p.nm_pasien like ? or "
                        + "tb.diagnosa like ? order by tb.waktu_simpan desc limit 100");
            } else {
                ps = koneksi.prepareStatement("SELECT tb.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                        + "date_format(tb.tgl_tindakan,'%d-%m-%Y') tgltindakan, if(tb.cateter='ya','V','') cat, if(tb.ngt='ya','V','') nngt, "
                        + "if(tb.nasal='ya','V','') nas, if(tb.kateter_urin='ya','V','') kat, if(tb.drain='ya','V','') dra, "
                        + "if(tb.alat_lain='ya','V','') alt, if(tb.kondisi_cm='ya','V','') ccm, if(tb.kondisi_sopor='ya','V','') sop, "
                        + "if(tb.kondisi_apatis='ya','V','') apa, if(tb.kondisi_pengaruh_sedasi='ya','V','') peng, "
                        + "if(tb.kondisi_somnolen='ya','V','') som, pg1.nama ptgsSerah, pg2.nama ptgsTerima, "
                        + "time_format(tb.waktu_simpan,'%H:%i') jamTindakan FROM transfer_sebelum_tindakan tb "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=tb.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg1 on pg1.nik=tb.nip_menyerahkan INNER JOIN pegawai pg2 on pg2.nik=tb.nip_menerima WHERE "
                        + "tb.no_rawat like ? or "
                        + "tb.kode_transfer like ? or "
                        + "p.no_rkm_medis like ? or "
                        + "p.nm_pasien like ? or "
                        + "tb.diagnosa like ? order by tb.waktu_simpan desc");
            }

            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{                        
                        rs.getString("no_rawat"),
                        rs.getString("kode_transfer"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgllahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tgltindakan") + ", " + rs.getString("jamTindakan") + " Wita",
                        rs.getString("riwayat_alergi"),
                        rs.getString("diagnosa"),
                        rs.getString("kategori_derajat"),
                        rs.getString("resiko_jatuh"),
                        rs.getString("skala_nyeri"),
                        rs.getString("paparan_infeksi"),
                        rs.getString("jenis_paparan"),
                        rs.getString("cat"),
                        rs.getString("nngt"),
                        rs.getString("nas"),
                        rs.getString("kat"),
                        rs.getString("dra"),
                        rs.getString("alt"),
                        rs.getString("ket_alat_lain"),
                        rs.getString("ccm"),
                        rs.getString("sop"),
                        rs.getString("apa"),
                        rs.getString("peng"),
                        rs.getString("som"),
                        rs.getString("td"),
                        rs.getString("respi"),
                        rs.getString("nadi"),
                        rs.getString("spo2"),
                        rs.getString("catatan"),
                        rs.getString("ptgsSerah"),
                        rs.getString("ptgsTerima"),
                        rs.getString("tgl_tindakan"),
                        rs.getString("cateter"),
                        rs.getString("ngt"),
                        rs.getString("nasal"),
                        rs.getString("kateter_urin"),
                        rs.getString("drain"),
                        rs.getString("alat_lain"),
                        rs.getString("kondisi_cm"),
                        rs.getString("kondisi_sopor"),
                        rs.getString("kondisi_apatis"),
                        rs.getString("kondisi_pengaruh_sedasi"),
                        rs.getString("kondisi_somnolen"),
                        rs.getString("nip_menyerahkan"),
                        rs.getString("nip_menerima"),
                        rs.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("rekammedis.RMPasienUntukTindakan.tampilSebelum() : " + e);
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
    
    private void tampilSesudah() {
        String jamTindakanSudah = "";
        Valid.tabelKosong(tabMode1);
        try {
            if (TCari.getText().equals("")) {
                ps1 = koneksi.prepareStatement("SELECT tb.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                        + "date_format(tb.tgl_tindakan,'%d-%m-%Y') tgltindakan, if(tb.cateter='ya','V','') cat, if(tb.ngt='ya','V','') nngt, "
                        + "if(tb.nasal='ya','V','') nas, if(tb.kateter_urin='ya','V','') kat, if(tb.drain='ya','V','') dra, "
                        + "if(tb.alat_lain='ya','V','') alt, if(tb.kondisi_cm='ya','V','') ccm, if(tb.kondisi_sopor='ya','V','') sop, "
                        + "if(tb.kondisi_apatis='ya','V','') apa, if(tb.kondisi_pengaruh_sedasi='ya','V','') peng, "
                        + "if(tb.kondisi_somnolen='ya','V','') som, pg1.nama ptgsSerah, pg2.nama ptgsTerima FROM transfer_sesudah_tindakan tb "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=tb.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg1 on pg1.nik=tb.nip_menyerahkan INNER JOIN pegawai pg2 on pg2.nik=tb.nip_menerima WHERE "
                        + "tb.no_rawat like ? or "
                        + "tb.kode_transfer like ? or "
                        + "p.no_rkm_medis like ? or "
                        + "p.nm_pasien like ? or "
                        + "tb.diagnosa like ? order by tb.waktu_simpan desc limit 100");
            } else {
                ps1 = koneksi.prepareStatement("SELECT tb.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                        + "date_format(tb.tgl_tindakan,'%d-%m-%Y') tgltindakan, if(tb.cateter='ya','V','') cat, if(tb.ngt='ya','V','') nngt, "
                        + "if(tb.nasal='ya','V','') nas, if(tb.kateter_urin='ya','V','') kat, if(tb.drain='ya','V','') dra, "
                        + "if(tb.alat_lain='ya','V','') alt, if(tb.kondisi_cm='ya','V','') ccm, if(tb.kondisi_sopor='ya','V','') sop, "
                        + "if(tb.kondisi_apatis='ya','V','') apa, if(tb.kondisi_pengaruh_sedasi='ya','V','') peng, "
                        + "if(tb.kondisi_somnolen='ya','V','') som, pg1.nama ptgsSerah, pg2.nama ptgsTerima FROM transfer_sesudah_tindakan tb "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=tb.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg1 on pg1.nik=tb.nip_menyerahkan INNER JOIN pegawai pg2 on pg2.nik=tb.nip_menerima WHERE "
                        + "tb.no_rawat like ? or "
                        + "tb.kode_transfer like ? or "
                        + "p.no_rkm_medis like ? or "
                        + "p.nm_pasien like ? or "
                        + "tb.diagnosa like ? order by tb.waktu_simpan desc");
            }

            try {
                ps1.setString(1, "%" + TCari.getText().trim() + "%");
                ps1.setString(2, "%" + TCari.getText().trim() + "%");
                ps1.setString(3, "%" + TCari.getText().trim() + "%");
                ps1.setString(4, "%" + TCari.getText().trim() + "%");
                ps1.setString(5, "%" + TCari.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    jamTindakanSudah = Sequel.cariIsi("select time_format(waktu_simpan,'%H:%i') from transfer_sebelum_tindakan "
                            + "where no_rawat='" + rs1.getString("no_rawat") + "' and kode_transfer='" + rs1.getString("kode_transfer") + "'");

                    tabMode1.addRow(new String[]{                        
                        rs1.getString("no_rawat"),
                        rs1.getString("kode_transfer"),
                        rs1.getString("no_rkm_medis"),
                        rs1.getString("nm_pasien"),
                        rs1.getString("tgllahir"),
                        rs1.getString("ruang_rawat"),
                        rs1.getString("tgltindakan") + ", " + jamTindakanSudah + " Wita",
                        rs1.getString("riwayat_alergi"),
                        rs1.getString("diagnosa"),
                        rs1.getString("kategori_derajat"),
                        rs1.getString("resiko_jatuh"),
                        rs1.getString("skala_nyeri"),
                        rs1.getString("paparan_infeksi"),
                        rs1.getString("jenis_paparan"),
                        rs1.getString("cat"),
                        rs1.getString("nngt"),
                        rs1.getString("nas"),
                        rs1.getString("kat"),
                        rs1.getString("dra"),
                        rs1.getString("alt"),
                        rs1.getString("ket_alat_lain"),
                        rs1.getString("ccm"),
                        rs1.getString("sop"),
                        rs1.getString("apa"),
                        rs1.getString("peng"),
                        rs1.getString("som"),
                        rs1.getString("td"),
                        rs1.getString("respi"),
                        rs1.getString("nadi"),
                        rs1.getString("spo2"),
                        rs1.getString("catatan"),
                        rs1.getString("ptgsSerah"),
                        rs1.getString("ptgsTerima"),
                        rs1.getString("tgl_tindakan"),
                        rs1.getString("cateter"),
                        rs1.getString("ngt"),
                        rs1.getString("nasal"),
                        rs1.getString("kateter_urin"),
                        rs1.getString("drain"),
                        rs1.getString("alat_lain"),
                        rs1.getString("kondisi_cm"),
                        rs1.getString("kondisi_sopor"),
                        rs1.getString("kondisi_apatis"),
                        rs1.getString("kondisi_pengaruh_sedasi"),
                        rs1.getString("kondisi_somnolen"),
                        rs1.getString("nip_menyerahkan"),
                        rs1.getString("nip_menerima"),
                        rs1.getString("waktu_simpan"),
                        rs1.getString("tgltindakan")
                    });
                }
            } catch (Exception e) {
                System.out.println("rekammedis.RMPasienUntukTindakan.tampilSesudah() : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode1.getRowCount());
    }
    
    public void emptTeksSebelum() {
        TtglTindakanBelum.setDate(new Date());
        cmbRiwAlergiBelum.setSelectedIndex(0);
        TdiagnosaBelum.setText("");
        cmbKategoriBelum.setSelectedIndex(0);
        cmbResikoBelum.setSelectedIndex(0);
        cmbSkalaBelum.setSelectedIndex(0);
        cmbPaparanBelum.setSelectedIndex(0);
        cmbJnsPaparanBelum.setEnabled(false);
        cmbJnsPaparanBelum.setSelectedIndex(0);
        ChkCateterBelum.setSelected(false);
        ChkNgtBelum.setSelected(false);
        ChkNasalBelum.setSelected(false);
        ChkKateterBelum.setSelected(false);
        ChkDrainBelum.setSelected(false);
        ChkAlatBelum.setSelected(false);
        TalatLainBelum.setEnabled(false);
        TalatLainBelum.setText("");
        ChkCMBelum.setSelected(false);
        ChkSoporBelum.setSelected(false);
        ChkApatisBelum.setSelected(false);
        ChkSomnolenBelum.setSelected(false);
        ChkDalamBelum.setSelected(false);
        TtensiBelum.setText("");
        TrrBelum.setText("");
        TnadiBelum.setText("");
        TspoBelum.setText("");
        TcatatanBelum.setText("");
        nipSerahBlm = "-";
        nipTerimaBlm = "-";
        TnmPetugasSerahBelum.setText("-");
        TnmPetugasTerimaBelum.setText("-");
        chkSaya1.setSelected(false);
        chkSaya2.setSelected(false);
        cekGanti = "";
        AutoNomorKode();
    }
    
    private void emptTeksSesudah() {
        TdiagnosaSudah.setText("");
        cmbKategoriSudah.setSelectedIndex(0);
        cmbResikoSudah.setSelectedIndex(0);
        cmbSkalaSudah.setSelectedIndex(0);
        cmbPaparanSudah.setSelectedIndex(0);
        cmbJnsPaparanSudah.setEnabled(false);
        cmbJnsPaparanSudah.setSelectedIndex(0);
        ChkCateterSudah.setSelected(false);
        ChkNgtSudah.setSelected(false);
        ChkNasalSudah.setSelected(false);
        ChkKateterSudah.setSelected(false);
        ChkDrainSudah.setSelected(false);
        ChkAlatSudah.setSelected(false);
        TalatLainSudah.setEnabled(false);
        TalatLainSudah.setText("");
        ChkCMSudah.setSelected(false);
        ChkSoporSudah.setSelected(false);
        ChkApatisSudah.setSelected(false);
        ChkSomnolenSudah.setSelected(false);
        ChkDalamSudah.setSelected(false);
        TtensiSudah.setText("");
        TrrSudah.setText("");
        TnadiSudah.setText("");
        TspoSudah.setText("");
        TcatatanSudah.setText("");
        nipSerahSdh = "-";
        nipTerimaSdh = "-";
        TnmPetugasSerahSudah.setText("-");
        TnmPetugasTerimaSudah.setText("-");
        chkSaya3.setSelected(false);
        chkSaya4.setSelected(false);
        cekGanti = "";
    }

    private void getDataBelum() {
        cekGanti = "";
        emptVariabelBlm();
        if (tbTransferBelum.getSelectedRow() != -1) {
            TNoRwBelum.setText(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 0).toString());
            TkdTransferBelum.setText(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 1).toString());
            TNoRMBelum.setText(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 2).toString());
            TPasienBelum.setText(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 3).toString());
            TrgRawatBelum.setText(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 5).toString());
            Valid.SetTgl(TtglTindakanBelum, tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 33).toString());
            cmbRiwAlergiBelum.setSelectedItem(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 7).toString());
            TdiagnosaBelum.setText(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 8).toString());
            cmbKategoriBelum.setSelectedItem(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 9).toString());
            cmbResikoBelum.setSelectedItem(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 10).toString());
            cmbSkalaBelum.setSelectedItem(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 11).toString());
            cmbPaparanBelum.setSelectedItem(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 12).toString());
            cmbJnsPaparanBelum.setSelectedItem(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 13).toString());
            cateterBlm = tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 34).toString();
            ngtBlm = tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 35).toString();
            nasalBlm = tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 36).toString();
            kateterUrinBlm = tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 37).toString();
            drainBlm = tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 38).toString();
            alatBlm = tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 39).toString();
            TalatLainBelum.setText(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 20).toString());
            cmBlm = tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 40).toString();
            soporBlm = tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 41).toString();
            apatisBlm = tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 42).toString();
            sedasiBlm = tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 43).toString();
            somnolenBlm = tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 44).toString();
            TtensiBelum.setText(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 26).toString());
            TrrBelum.setText(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 27).toString());
            TnadiBelum.setText(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 28).toString());
            TspoBelum.setText(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 29).toString());
            TcatatanBelum.setText(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 30).toString());
            nipSerahBlm = tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 45).toString();
            nipTerimaBlm = tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 46).toString();
            TnmPetugasSerahBelum.setText(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 31).toString());
            TnmPetugasTerimaBelum.setText(tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 32).toString());
            TCari.setText(TNoRwBelum.getText());
            dataCekBelum();
            cekSesudah();
            tampilSesudah();
        }
    }
    
    private void getDataSudah() {
        cekGanti = "";
        emptTeksSesudah();
        if (tbTransferSudah.getSelectedRow() != -1) {
            TNoRwSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 0).toString());
            TkdTransferSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 1).toString());
            TNoRMSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 2).toString());
            TPasienSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 3).toString());
            TrgRawatSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 5).toString());
            TtglTindakanSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 48).toString());
            TRiwAlergiSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 7).toString());
            TdiagnosaSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 8).toString());
            cmbKategoriSudah.setSelectedItem(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 9).toString());
            cmbResikoSudah.setSelectedItem(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 10).toString());
            cmbSkalaSudah.setSelectedItem(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 11).toString());
            cmbPaparanSudah.setSelectedItem(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 12).toString());
            cmbJnsPaparanSudah.setSelectedItem(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 13).toString());
            cateterSdh = tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 34).toString();
            ngtSdh = tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 35).toString();
            nasalSdh = tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 36).toString();
            kateterUrinSdh = tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 37).toString();
            drainSdh = tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 38).toString();
            alatSdh = tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 39).toString();
            TalatLainSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 20).toString());
            cmSdh = tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 40).toString();
            soporSdh = tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 41).toString();
            apatisSdh = tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 42).toString();
            sedasiSdh = tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 43).toString();
            somnolenSdh = tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 44).toString();
            TtensiSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 26).toString());
            TrrSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 27).toString());
            TnadiSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 28).toString());
            TspoSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 29).toString());
            TcatatanSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 30).toString());
            nipSerahSdh = tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 45).toString();
            nipTerimaSdh = tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 46).toString();
            TnmPetugasSerahSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 31).toString());
            TnmPetugasTerimaSudah.setText(tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 32).toString());
            dataCekSudah();
            cekGanti = "dari tersimpan";
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnGanti.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
    }

    private void AutoNomorKode() {
        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(kode_transfer,6),signed)),0) from transfer_sebelum_tindakan where "
                + "tgl_tindakan like '%" + Valid.SetTgl(TtglTindakanBelum.getSelectedItem() + "").substring(0, 7) + "%' ",
                "/TPT/" + Valid.SetTgl(TtglTindakanBelum.getSelectedItem() + "").substring(5, 7) + "/" + Valid.SetTgl(TtglTindakanBelum.getSelectedItem() + "").substring(0, 4), 6, TkdTransferBelum);
    }
    
    private void cekDataBelum() {
        if (ChkCateterBelum.isSelected() == true) {
            cateterBlm = "ya";
        } else {
            cateterBlm = "tidak";
        }
        
        if (ChkNgtBelum.isSelected() == true) {
            ngtBlm = "ya";
        } else {
            ngtBlm = "tidak";
        }
        
        if (ChkNasalBelum.isSelected() == true) {
            nasalBlm = "ya";
        } else {
            nasalBlm = "tidak";
        }
        
        if (ChkKateterBelum.isSelected() == true) {
            kateterUrinBlm = "ya";
        } else {
            kateterUrinBlm = "tidak";
        }
        
        if (ChkDrainBelum.isSelected() == true) {
            drainBlm = "ya";
        } else {
            drainBlm = "tidak";
        }
        
        if (ChkAlatBelum.isSelected() == true) {
            alatBlm = "ya";
        } else {
            alatBlm = "tidak";
        }
        
        if (ChkCMBelum.isSelected() == true) {
            cmBlm = "ya";
        } else {
            cmBlm = "tidak";
        }
        
        if (ChkSoporBelum.isSelected() == true) {
            soporBlm = "ya";
        } else {
            soporBlm = "tidak";
        }
        
        if (ChkApatisBelum.isSelected() == true) {
            apatisBlm = "ya";
        } else {
            apatisBlm = "tidak";
        }
        
        if (ChkSomnolenBelum.isSelected() == true) {
            somnolenBlm = "ya";
        } else {
            somnolenBlm = "tidak";
        }
        
        if (ChkDalamBelum.isSelected() == true) {
            sedasiBlm = "ya";
        } else {
            sedasiBlm = "tidak";
        }
    }
    
    private void cekDataSudah() {
        if (ChkCateterSudah.isSelected() == true) {
            cateterSdh = "ya";
        } else {
            cateterSdh = "tidak";
        }
        
        if (ChkNgtSudah.isSelected() == true) {
            ngtSdh = "ya";
        } else {
            ngtSdh = "tidak";
        }
        
        if (ChkNasalSudah.isSelected() == true) {
            nasalSdh = "ya";
        } else {
            nasalSdh = "tidak";
        }
        
        if (ChkKateterSudah.isSelected() == true) {
            kateterUrinSdh = "ya";
        } else {
            kateterUrinSdh = "tidak";
        }
        
        if (ChkDrainSudah.isSelected() == true) {
            drainSdh = "ya";
        } else {
            drainSdh = "tidak";
        }
        
        if (ChkAlatSudah.isSelected() == true) {
            alatSdh = "ya";
        } else {
            alatSdh = "tidak";
        }
        
        if (ChkCMSudah.isSelected() == true) {
            cmSdh = "ya";
        } else {
            cmSdh = "tidak";
        }
        
        if (ChkSoporSudah.isSelected() == true) {
            soporSdh = "ya";
        } else {
            soporSdh = "tidak";
        }
        
        if (ChkApatisSudah.isSelected() == true) {
            apatisSdh = "ya";
        } else {
            apatisSdh = "tidak";
        }
        
        if (ChkSomnolenSudah.isSelected() == true) {
            somnolenSdh = "ya";
        } else {
            somnolenSdh = "tidak";
        }
        
        if (ChkDalamSudah.isSelected() == true) {
            sedasiSdh = "ya";
        } else {
            sedasiSdh = "tidak";
        }
    }

    private void gantiSebelum() {
        cekDataBelum();
        if (Sequel.mengedittf("transfer_sebelum_tindakan", "waktu_simpan=?", "tgl_tindakan=?, riwayat_alergi=?, diagnosa=?, kategori_derajat=?, "
                + "resiko_jatuh=?, skala_nyeri=?, paparan_infeksi=?, jenis_paparan=?, cateter=?, ngt=?, nasal=?, kateter_urin=?, drain=?, alat_lain=?, "
                + "ket_alat_lain=?, kondisi_cm=?, kondisi_sopor=?, kondisi_apatis=?, kondisi_pengaruh_sedasi=?, kondisi_somnolen=?, td=?, respi=?, nadi=?, "
                + "spo2=?, catatan=?, nip_menyerahkan=?, nip_menerima=?", 28, new String[]{
                    Valid.SetTgl(TtglTindakanBelum.getSelectedItem() + ""), cmbRiwAlergiBelum.getSelectedItem().toString(), TdiagnosaBelum.getText(),
                    cmbKategoriBelum.getSelectedItem().toString(), cmbResikoBelum.getSelectedItem().toString(), cmbSkalaBelum.getSelectedItem().toString(), 
                    cmbPaparanBelum.getSelectedItem().toString(), cmbJnsPaparanBelum.getSelectedItem().toString(), cateterBlm, ngtBlm, nasalBlm, kateterUrinBlm, 
                    drainBlm, alatBlm, TalatLainBelum.getText(), cmBlm, soporBlm, apatisBlm, sedasiBlm, somnolenBlm, TtensiBelum.getText(), TrrBelum.getText().trim(), 
                    TnadiBelum.getText().trim(), TspoBelum.getText().trim(), TcatatanBelum.getText(), nipSerahBlm, nipTerimaBlm,
                    tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 47).toString()
                }) == true) {

            Sequel.mengedit("transfer_sesudah_tindakan", "no_rawat='" + TNoRwBelum.getText() + "' and kode_transfer='" + TkdTransferBelum.getText() + "'",
                    "tgl_tindakan='" + Valid.SetTgl(TtglTindakanBelum.getSelectedItem() + "") + "', "
                    + "riwayat_alergi='" + cmbRiwAlergiBelum.getSelectedItem().toString() + "'");
            
            TCari.setText(TNoRwBelum.getText());
            tampilSebelum();
            emptTeksSebelum();
        }
    }

    private void gantiSesudah(String wktSimpan) {
        cekDataSudah();
        if (Sequel.mengedittf("transfer_sesudah_tindakan", "waktu_simpan=?", "diagnosa=?, kategori_derajat=?, resiko_jatuh=?, skala_nyeri=?, "
                + "paparan_infeksi=?, jenis_paparan=?, cateter=?, ngt=?, nasal=?, kateter_urin=?, drain=?, alat_lain=?, ket_alat_lain=?, kondisi_cm=?, "
                + "kondisi_sopor=?, kondisi_apatis=?, kondisi_pengaruh_sedasi=?, kondisi_somnolen=?, td=?, respi=?, nadi=?, spo2=?, catatan=?, "
                + "nip_menyerahkan=?, nip_menerima=?", 26, new String[]{
                    TdiagnosaSudah.getText(), cmbKategoriSudah.getSelectedItem().toString(), cmbResikoSudah.getSelectedItem().toString(),
                    cmbSkalaSudah.getSelectedItem().toString(), cmbPaparanSudah.getSelectedItem().toString(), cmbJnsPaparanSudah.getSelectedItem().toString(),
                    cateterSdh, ngtSdh, nasalSdh, kateterUrinSdh, drainSdh, alatSdh, TalatLainSudah.getText(), cmSdh, soporSdh, apatisSdh, sedasiSdh,
                    somnolenSdh, TtensiSudah.getText(), TrrSudah.getText().trim(), TnadiSudah.getText().trim(), TspoSudah.getText().trim(),
                    TcatatanSudah.getText(), nipSerahSdh, nipTerimaSdh,
                    wktSimpan
                }) == true) {

            TCari.setText(TNoRwSudah.getText());
            tampilSesudah();
            emptTeksSesudah();
        }
    }
    
    private void dataCekBelum() {
        if (cateterBlm.equals("ya")) {
            ChkCateterBelum.setSelected(true);
        } else {
            ChkCateterBelum.setSelected(false);
        }

        if (ngtBlm.equals("ya")) {
            ChkNgtBelum.setSelected(true);
        } else {
            ChkNgtBelum.setSelected(false);
        }

        if (nasalBlm.equals("ya")) {
            ChkNasalBelum.setSelected(true);
        } else {
            ChkNasalBelum.setSelected(false);
        }

        if (kateterUrinBlm.equals("ya")) {
            ChkKateterBelum.setSelected(true);
        } else {
            ChkKateterBelum.setSelected(false);
        }

        if (drainBlm.equals("ya")) {
            ChkDrainBelum.setSelected(true);
        } else {
            ChkDrainBelum.setSelected(false);
        }

        if (alatBlm.equals("ya")) {
            ChkAlatBelum.setSelected(true);
            TalatLainBelum.setEnabled(true);
        } else {
            ChkAlatBelum.setSelected(false);
            TalatLainBelum.setEnabled(false);
        }

        if (cmBlm.equals("ya")) {
            ChkCMBelum.setSelected(true);
        } else {
            ChkCMBelum.setSelected(false);
        }

        if (soporBlm.equals("ya")) {
            ChkSoporBelum.setSelected(true);
        } else {
            ChkSoporBelum.setSelected(false);
        }

        if (apatisBlm.equals("ya")) {
            ChkApatisBelum.setSelected(true);
        } else {
            ChkApatisBelum.setSelected(false);
        }

        if (somnolenBlm.equals("ya")) {
            ChkSomnolenBelum.setSelected(true);
        } else {
            ChkSomnolenBelum.setSelected(false);
        }

        if (sedasiBlm.equals("ya")) {
            ChkDalamBelum.setSelected(true);
        } else {
            ChkDalamBelum.setSelected(false);
        }
        
        if (cmbPaparanBelum.getSelectedIndex() == 1) {
            cmbJnsPaparanBelum.setEnabled(true);
        } else {
            cmbJnsPaparanBelum.setEnabled(false);
        }
    }
    
    private void dataCekSudah() {
        if (cateterSdh.equals("ya")) {
            ChkCateterSudah.setSelected(true);
        } else {
            ChkCateterSudah.setSelected(false);
        }

        if (ngtSdh.equals("ya")) {
            ChkNgtSudah.setSelected(true);
        } else {
            ChkNgtSudah.setSelected(false);
        }

        if (nasalSdh.equals("ya")) {
            ChkNasalSudah.setSelected(true);
        } else {
            ChkNasalSudah.setSelected(false);
        }

        if (kateterUrinSdh.equals("ya")) {
            ChkKateterSudah.setSelected(true);
        } else {
            ChkKateterSudah.setSelected(false);
        }

        if (drainSdh.equals("ya")) {
            ChkDrainSudah.setSelected(true);
        } else {
            ChkDrainSudah.setSelected(false);
        }

        if (alatSdh.equals("ya")) {
            ChkAlatSudah.setSelected(true);
            TalatLainSudah.setEnabled(true);
        } else {
            ChkAlatSudah.setSelected(false);
            TalatLainSudah.setEnabled(false);
        }

        if (cmSdh.equals("ya")) {
            ChkCMSudah.setSelected(true);
        } else {
            ChkCMSudah.setSelected(false);
        }

        if (soporSdh.equals("ya")) {
            ChkSoporSudah.setSelected(true);
        } else {
            ChkSoporSudah.setSelected(false);
        }

        if (apatisSdh.equals("ya")) {
            ChkApatisSudah.setSelected(true);
        } else {
            ChkApatisSudah.setSelected(false);
        }

        if (somnolenSdh.equals("ya")) {
            ChkSomnolenSudah.setSelected(true);
        } else {
            ChkSomnolenSudah.setSelected(false);
        }

        if (sedasiSdh.equals("ya")) {
            ChkDalamSudah.setSelected(true);
        } else {
            ChkDalamSudah.setSelected(false);
        }
        
        if (cmbPaparanSudah.getSelectedIndex() == 1) {
            cmbJnsPaparanSudah.setEnabled(true);
        } else {
            cmbJnsPaparanSudah.setEnabled(false);
        }
    }
    
    private void emptVariabelBlm() {
        cateterBlm = "";
        ngtBlm = "";
        nasalBlm = "";
        kateterUrinBlm = "";
        drainBlm = "";
        alatBlm = "";
        cmBlm = "";
        soporBlm = "";
        apatisBlm = "";
        somnolenBlm = "";
        sedasiBlm = "";
        nipSerahBlm = "";
        nipTerimaBlm = "";
    }
    
    private void emptVariabelSdh() {
        cateterSdh = "";
        ngtSdh = "";
        nasalSdh = "";
        kateterUrinSdh = "";
        drainSdh = "";
        alatSdh = "";
        cmSdh = "";
        soporSdh = "";
        apatisSdh = "";
        somnolenSdh = "";
        sedasiSdh = "";
        nipSerahSdh = "";
        nipTerimaSdh = "";
        wktSimpanSdh = "";
    }

    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRwBelum.setText(norw);
        TNoRMBelum.setText(norm);
        TPasienBelum.setText(nmpasien);
        TrgRawatBelum.setText(ruangan);
        TCari.setText(norw);
    }

    private void hapusSebelum() {
        if (tbTransferBelum.getSelectedRow() > -1) {
            if (Sequel.cariInteger("select count(-1) from transfer_sesudah_tindakan where "
                    + "no_rawat='" + tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 0).toString() + "' and "
                    + "kode_transfer='" + tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 1).toString() + "'") == 0) {

                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from transfer_sebelum_tindakan where waktu_simpan=?", 1, new String[]{
                        tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 47).toString()
                    }) == true) {
                        tampilSebelum();
                        emptTeksSebelum();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                }
            } else {
                x = JOptionPane.showConfirmDialog(rootPane, "Data transfer pasien sesudah tindakan telah tersimpan,   \n"
                        + "Apakah data transfer sebelum & sesudah yakin akan dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from transfer_sebelum_tindakan where waktu_simpan=?", 1, new String[]{
                        tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 47).toString()
                    }) == true) {
                        Sequel.meghapus("transfer_sesudah_tindakan", "kode_transfer='" + tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 1).toString() + "' and no_rawat",
                                tbTransferBelum.getValueAt(tbTransferBelum.getSelectedRow(), 0).toString());

                        tampilSebelum();
                        emptTeksSebelum();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbTransferBelum.requestFocus();
        }
    }

    private void hapusSesudah() {
        if (tbTransferSudah.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from transfer_sesudah_tindakan where waktu_simpan=?", 1, new String[]{
                    tbTransferSudah.getValueAt(tbTransferSudah.getSelectedRow(), 47).toString()
                }) == true) {
                    tampilSesudah();
                    emptTeksSesudah();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbTransferSudah.requestFocus();
        }
    }

    private void cekSesudah() {
        TabTransfer.setEnabledAt(1, true);
        emptTeksSesudah();
        cekGanti = "dari sebelum";
        
        if (Sequel.cariInteger("select count(-1) from transfer_sesudah_tindakan where "
                + "no_rawat='" + TNoRwBelum.getText() + "' and kode_transfer='" + TkdTransferBelum.getText() + "'") == 0) {
            TNoRwSudah.setText(TNoRwBelum.getText());
            TkdTransferSudah.setText(TkdTransferBelum.getText());
            TNoRMSudah.setText(TNoRMBelum.getText());
            TPasienSudah.setText(TPasienBelum.getText());
            TrgRawatSudah.setText(TrgRawatBelum.getText());
            TtglTindakanSudah.setText(TtglTindakanBelum.getSelectedItem().toString());
            TRiwAlergiSudah.setText(cmbRiwAlergiBelum.getSelectedItem().toString());
        } else {
            emptVariabelSdh();
            try {
                ps2 = koneksi.prepareStatement("select t.*, p.no_rkm_medis, p.nm_pasien, date_format(t.tgl_tindakan,'%d-%m-%Y') tgltindakan, "
                        + "pg1.nama ptgsSerah, pg2.nama ptgsTerima from transfer_sesudah_tindakan t INNER JOIN reg_periksa rp on rp.no_rawat=t.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN pegawai pg1 on pg1.nik=t.nip_menyerahkan "
                        + "INNER JOIN pegawai pg2 on pg2.nik=t.nip_menerima where "
                        + "t.no_rawat='" + TNoRwBelum.getText() + "' and t.kode_transfer='" + TkdTransferBelum.getText() + "'");
                try {
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        TNoRwSudah.setText(rs2.getString("no_rawat"));
                        TkdTransferSudah.setText(rs2.getString("kode_transfer"));
                        TNoRMSudah.setText(rs2.getString("no_rkm_medis"));
                        TPasienSudah.setText(rs2.getString("nm_pasien"));
                        TrgRawatSudah.setText(rs2.getString("ruang_rawat"));
                        TtglTindakanSudah.setText(rs2.getString("tgltindakan"));
                        TRiwAlergiSudah.setText(rs2.getString("riwayat_alergi"));
                        TdiagnosaSudah.setText(rs2.getString("diagnosa"));
                        cmbKategoriSudah.setSelectedItem(rs2.getString("kategori_derajat"));
                        cmbResikoSudah.setSelectedItem(rs2.getString("resiko_jatuh"));
                        cmbSkalaSudah.setSelectedItem(rs2.getString("skala_nyeri"));
                        cmbPaparanSudah.setSelectedItem(rs2.getString("paparan_infeksi"));
                        cmbJnsPaparanSudah.setSelectedItem(rs2.getString("jenis_paparan"));
                        cateterSdh = rs2.getString("cateter");
                        ngtSdh = rs2.getString("ngt");
                        nasalSdh = rs2.getString("nasal");
                        kateterUrinSdh = rs2.getString("kateter_urin");
                        drainSdh = rs2.getString("drain");
                        alatSdh = rs2.getString("alat_lain");
                        TalatLainSudah.setText(rs2.getString("ket_alat_lain"));
                        cmSdh = rs2.getString("kondisi_cm");
                        soporSdh = rs2.getString("kondisi_sopor");
                        apatisSdh = rs2.getString("kondisi_apatis");
                        sedasiSdh = rs2.getString("kondisi_pengaruh_sedasi");
                        somnolenSdh = rs2.getString("kondisi_somnolen");
                        TtensiSudah.setText(rs2.getString("td"));
                        TrrSudah.setText(rs2.getString("respi"));
                        TnadiSudah.setText(rs2.getString("nadi"));
                        TspoSudah.setText(rs2.getString("spo2"));
                        TcatatanSudah.setText(rs2.getString("catatan"));
                        nipSerahSdh = rs2.getString("nip_menyerahkan");
                        nipTerimaSdh = rs2.getString("nip_menerima");
                        TnmPetugasSerahSudah.setText(rs2.getString("ptgsSerah"));
                        TnmPetugasTerimaSudah.setText(rs2.getString("ptgsTerima"));
                        wktSimpanSdh = rs2.getString("waktu_simpan");
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
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
            dataCekSudah();
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
                    + "c.flag_hapus='tidak' and c.status='ranap' and c.no_rawat='" + TNoRwBelum.getText() + "' order by c.tgl_cppt, c.jam_cppt");
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
}
