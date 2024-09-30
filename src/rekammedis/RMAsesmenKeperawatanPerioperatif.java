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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class RMAsesmenKeperawatanPerioperatif extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private PreparedStatement ps, ps1, pps1, pps2;
    private ResultSet rs, rs1, rrs1, rrs2;
    private int i = 0, x = 0, pilihan = 0;
    private String supin = "", litotomi = "", pronasi = "", trendel = "", lateral = "",
            alkohol = "", iodin = "", brono = "", eksKanan = "", eksKiri = "", perhid = "", nacl = "", aqua = "",
            suction = "", turniq = "", bor = "", elek = "", bipo = "", mono = "", ngt = "", dc = "", irigasi = "", 
            ett = "", epid = "", arm = "", gip = "", drain = "", draKanan = "", draKiri = "",
            infus = "", infKanan = "", infKiri = "", ya = "", tidak = "", wb = "", prc = "", sebanyak = "", wktSimpanAses = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMAsesmenKeperawatanPerioperatif(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "waktu_simpan", "No. Rawat", "No. RM", "Nama Pasien", "Jns. Kelamin ", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Assesmen",
            "Ruang OK", "Jns. Anestesi", "Dx. Pre Operasi", "Dx. Pasca Operasi", "Mulai Operasi", "Dokter Operator", "Asisten", "Instrumen",
            "On Loop", "Dokter Anestesi", "Perawat Anestesi", "Selesai Operasi",
            "tgl_asesmen", "hari", "ruang_ok", "jenis_ruang_ok", "jenis_anestesi", "la_infiltrasi", "blok", "dx_pre_operasi", "dx_pasca_operasi", "mulai_operasi",
            "nip_dokter_operator", "nip_asisten", "instrumen", "on_loop", "nip_dokter_anestesi", "nip_perawat_anestesi", "selesai_operasi", "data_subjektif",
            "kesadaran", "td", "respi", "nadi", "suhu", "gcs_e", "gcs_m", "gcs_v", "supinasi", "litotomi", "pronasi", "trendelenburg", "lateral_kanan_kiri", "posisi_lainnya",
            "alkohol_70", "iodine_providone_10", "bronouderm", "area_operasi", "ekstremitas_kanan", "ekstremitas_kiri", "perhidrol", "nacl", "aqua_steril", "cuci_luka_lainnya",
            "suction_pump", "turniquet", "bor_ortopedi", "penggunaan_alat_lainnya", "elestric_couter", "bipolar", "monopolar", "negatif_plat_di", "ngt", "dc", "irigasi", "ett",
            "epidural", "armsling", "gips", "drain", "ket_drain", "drain_kanan", "drain_kiri", "infus", "ket_infus", "infus_kanan", "infus_kiri", "ya", "tidak_berupa", "wb", "prc",
            "sebanyak", "ket_sebanyak1", "ket_sebanyak2", "kejadian", "nip_perawat", "nmperawatOperasi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 95; i++) {
            TableColumn column = tbAsesmen.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setPreferredWidth(120);
            } else if (i == 8) {
                column.setPreferredWidth(135);
            } else if (i == 9) {
                column.setPreferredWidth(80);
            } else if (i == 10) {
                column.setPreferredWidth(220);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(220);
            } else if (i == 14) {
                column.setPreferredWidth(220);
            } else if (i == 15) {
                column.setPreferredWidth(220);
            } else if (i == 16) {
                column.setPreferredWidth(220);
            } else if (i == 17) {
                column.setPreferredWidth(220);
            } else if (i == 18) {
                column.setPreferredWidth(220);
            } else if (i == 19) {
                column.setPreferredWidth(90);
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
            } else if (i == 90) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 91) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 92) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 93) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 94) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "Jenis", "Awal", "Tambahan", "Akhir", "Keterangan", "no_rawat", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbHitungan.setModel(tabMode1);
        tbHitungan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbHitungan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbHitungan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(90);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(195);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbHitungan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{"No. RM", "Nama Pasien", "Data Template"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTemplate.setModel(tabMode2);
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

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        Tla.setDocument(new batasInput((int) 100).getKata(Tla));
        Tblok.setDocument(new batasInput((int) 100).getKata(Tblok));
        TgcsE.setDocument(new batasInput((int) 7).getKata(TgcsE));
        TgcsM.setDocument(new batasInput((int) 7).getKata(TgcsM));
        TgcsV.setDocument(new batasInput((int) 7).getKata(TgcsV));
        Ttensi.setDocument(new batasInput((int) 7).getKata(Ttensi));
        Tsuhu.setDocument(new batasInput((int) 7).getKata(Tsuhu));
        Tnadi.setDocument(new batasInput((int) 7).getKata(Tnadi));
        Trespi.setDocument(new batasInput((int) 7).getKata(Trespi));
        TlainPosisi.setDocument(new batasInput((int) 200).getKata(TlainPosisi));
        Tarea.setDocument(new batasInput((int) 200).getKata(Tarea));
        TlainCuci.setDocument(new batasInput((int) 200).getKata(TlainCuci));
        TlainPenggunaan.setDocument(new batasInput((int) 200).getKata(TlainPenggunaan));
        Tnegatif.setDocument(new batasInput((int) 200).getKata(Tnegatif));
        Tdrain.setDocument(new batasInput((int) 200).getKata(Tdrain));
        Tinfus.setDocument(new batasInput((int) 200).getKata(Tinfus));
        Tsebanyak1.setDocument(new batasInput((int) 100).getKata(Tsebanyak1));
        Tsebanyak2.setDocument(new batasInput((int) 100).getKata(Tsebanyak2));
        Tawal.setDocument(new batasInput((int) 100).getKata(Tawal));
        Ttambahan.setDocument(new batasInput((int) 100).getKata(Ttambahan));
        Takhir.setDocument(new batasInput((int) 100).getKata(Takhir));
        Tketerangan.setDocument(new batasInput((int) 200).getKata(Tketerangan));
        
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
                if (akses.getform().equals("RMAsesmenKeperawatanPerioperatif")) {
                    if (pilihan == 1) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            TnipDokterOperator.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            TnmDokterOperator.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDokterOperator.requestFocus();
                        }
                    } else if (pilihan == 2) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            TnipDokterAnestesi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            TnmDokterAnestesi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDokterAnestesi.requestFocus();
                        }
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (pilihan == 3) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        TnipAsisten.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        TnmAsisten.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnAsisten.requestFocus();
                    }
                } else if (pilihan == 4) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        TnipPerawatAnestesi.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        TnmPerawatAnestesi.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPerawatAnestesi.requestFocus();
                    }
                } else if (pilihan == 5) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        TnipPerawatOperasi.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        TnmPerawatOperasi.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPerawatOperasi.requestFocus();
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

        WindowTemplate = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        jLabel36 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCopas = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        Scroll3 = new widget.ScrollPane();
        tbTemplate = new widget.Table();
        Scroll4 = new widget.ScrollPane();
        Ttemplate = new widget.TextArea();
        internalFrame1 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TNoRw = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel11 = new widget.Label();
        Tjk = new widget.TextBox();
        jLabel64 = new widget.Label();
        Thari = new widget.TextBox();
        jLabel12 = new widget.Label();
        TtglAsesmen = new widget.Tanggal();
        jLabel13 = new widget.Label();
        cmbRuangOK = new widget.ComboBox();
        cmbJnsRuang = new widget.ComboBox();
        jLabel65 = new widget.Label();
        cmbJnsAnestesi = new widget.ComboBox();
        jLabel14 = new widget.Label();
        Tla = new widget.TextBox();
        jLabel15 = new widget.Label();
        Tblok = new widget.TextBox();
        jLabel66 = new widget.Label();
        TdxPreOperasi = new widget.TextBox();
        jLabel67 = new widget.Label();
        TdxPascaOperasi = new widget.TextBox();
        jLabel68 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel16 = new widget.Label();
        label15 = new widget.Label();
        TnipDokterOperator = new widget.TextBox();
        TnmDokterOperator = new widget.TextBox();
        BtnDokterOperator = new widget.Button();
        label16 = new widget.Label();
        TnipAsisten = new widget.TextBox();
        TnmAsisten = new widget.TextBox();
        BtnAsisten = new widget.Button();
        jLabel69 = new widget.Label();
        Tinstrumen = new widget.TextBox();
        jLabel70 = new widget.Label();
        Tonloop = new widget.TextBox();
        label17 = new widget.Label();
        TnipDokterAnestesi = new widget.TextBox();
        TnmDokterAnestesi = new widget.TextBox();
        BtnDokterAnestesi = new widget.Button();
        label18 = new widget.Label();
        TnipPerawatAnestesi = new widget.TextBox();
        TnmPerawatAnestesi = new widget.TextBox();
        BtnPerawatAnestesi = new widget.Button();
        jLabel71 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel17 = new widget.Label();
        jLabel72 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        TdataSubyektif = new widget.TextArea();
        jLabel73 = new widget.Label();
        jLabel74 = new widget.Label();
        cmbKesadaran = new widget.ComboBox();
        jLabel75 = new widget.Label();
        Ttensi = new widget.TextBox();
        label104 = new widget.Label();
        Tsuhu = new widget.TextBox();
        jLabel23 = new widget.Label();
        Tnadi = new widget.TextBox();
        label43 = new widget.Label();
        Trespi = new widget.TextBox();
        label106 = new widget.Label();
        jLabel76 = new widget.Label();
        TgcsE = new widget.TextBox();
        label105 = new widget.Label();
        TgcsM = new widget.TextBox();
        label107 = new widget.Label();
        TgcsV = new widget.TextBox();
        jLabel77 = new widget.Label();
        chkSupin = new widget.CekBox();
        chkLitotomi = new widget.CekBox();
        chkPronasi = new widget.CekBox();
        chkTrendelen = new widget.CekBox();
        chkLateral = new widget.CekBox();
        chkAlkohol = new widget.CekBox();
        TlainPosisi = new widget.TextBox();
        jLabel78 = new widget.Label();
        jLabel79 = new widget.Label();
        chkIodine = new widget.CekBox();
        chkBrono = new widget.CekBox();
        jLabel80 = new widget.Label();
        Tarea = new widget.TextBox();
        jLabel81 = new widget.Label();
        chkEksKanan = new widget.CekBox();
        chkEksKiri = new widget.CekBox();
        jLabel82 = new widget.Label();
        chkPerhid = new widget.CekBox();
        chkNacl = new widget.CekBox();
        chkAqua = new widget.CekBox();
        TlainCuci = new widget.TextBox();
        jLabel83 = new widget.Label();
        jLabel84 = new widget.Label();
        chkSuction = new widget.CekBox();
        chkTurni = new widget.CekBox();
        chkBor = new widget.CekBox();
        jLabel85 = new widget.Label();
        TlainPenggunaan = new widget.TextBox();
        chkElek = new widget.CekBox();
        chkBipo = new widget.CekBox();
        chkMono = new widget.CekBox();
        jLabel86 = new widget.Label();
        Tnegatif = new widget.TextBox();
        jLabel87 = new widget.Label();
        chkNgt = new widget.CekBox();
        chkDc = new widget.CekBox();
        chkIrigasi = new widget.CekBox();
        chkEtt = new widget.CekBox();
        chkEpid = new widget.CekBox();
        chkArm = new widget.CekBox();
        chkGips = new widget.CekBox();
        chkDrain = new widget.CekBox();
        Tdrain = new widget.TextBox();
        label108 = new widget.Label();
        chkDrainKanan = new widget.CekBox();
        chkDrainKiri = new widget.CekBox();
        chkInfus = new widget.CekBox();
        Tinfus = new widget.TextBox();
        label109 = new widget.Label();
        chkInfusKanan = new widget.CekBox();
        chkInfusKiri = new widget.CekBox();
        jLabel88 = new widget.Label();
        chkYa = new widget.CekBox();
        chkTidak = new widget.CekBox();
        chkWb = new widget.CekBox();
        chkPrc = new widget.CekBox();
        chkSebanyak = new widget.CekBox();
        Tsebanyak1 = new widget.TextBox();
        label110 = new widget.Label();
        Tsebanyak2 = new widget.TextBox();
        label111 = new widget.Label();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        cmbJnsHitungan = new widget.ComboBox();
        TlainJenis = new widget.TextBox();
        jLabel91 = new widget.Label();
        Tawal = new widget.TextBox();
        jLabel92 = new widget.Label();
        Ttambahan = new widget.TextBox();
        jLabel93 = new widget.Label();
        Takhir = new widget.TextBox();
        jLabel94 = new widget.Label();
        Tketerangan = new widget.TextBox();
        Scroll2 = new widget.ScrollPane();
        tbHitungan = new widget.Table();
        BtnTambah = new widget.Button();
        BtnHapusJns = new widget.Button();
        scrollPane5 = new widget.ScrollPane();
        Tkejadian = new widget.TextArea();
        jLabel96 = new widget.Label();
        TnipPerawatOperasi = new widget.TextBox();
        TnmPerawatOperasi = new widget.TextBox();
        BtnPerawatOperasi = new widget.Button();
        chkSaya = new widget.CekBox();
        BtnDataSubyektif = new widget.Button();
        BtnKejadian = new widget.Button();
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
        BtnAll1 = new widget.Button();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();

        WindowTemplate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTemplate.setName("WindowTemplate"); // NOI18N
        WindowTemplate.setUndecorated(true);
        WindowTemplate.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Assesmen Keperawatan Perioperatif ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        panelisi4.setBackground(new java.awt.Color(255, 150, 255));
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 46));
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

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 250));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);
        Scroll3.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTemplate.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTemplate.setName("tbTemplate"); // NOI18N
        tbTemplate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTemplateMouseClicked(evt);
            }
        });
        Scroll3.setViewportView(tbTemplate);

        jPanel1.add(Scroll3);

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        Ttemplate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Baca Template Dipilih ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Ttemplate.setColumns(20);
        Ttemplate.setRows(5);
        Ttemplate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ttemplate.setName("Ttemplate"); // NOI18N
        Scroll4.setViewportView(Ttemplate);

        jPanel1.add(Scroll4);

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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Keperawatan Perioperatif ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(600, 402));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1319));
        FormInput.setLayout(null);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(344, 10, 405, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(270, 10, 70, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(135, 10, 131, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 130, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 38, 130, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(135, 38, 440, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Jenis Kelamin :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(575, 38, 90, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setHighlighter(null);
        Tjk.setName("Tjk"); // NOI18N
        FormInput.add(Tjk);
        Tjk.setBounds(669, 38, 80, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Hari :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 66, 130, 23);

        Thari.setEditable(false);
        Thari.setForeground(new java.awt.Color(0, 0, 0));
        Thari.setName("Thari"); // NOI18N
        FormInput.add(Thari);
        Thari.setBounds(135, 66, 70, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Asesmen :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(205, 66, 85, 23);

        TtglAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-09-2024" }));
        TtglAsesmen.setDisplayFormat("dd-MM-yyyy");
        TtglAsesmen.setName("TtglAsesmen"); // NOI18N
        TtglAsesmen.setOpaque(false);
        TtglAsesmen.setPreferredSize(new java.awt.Dimension(90, 23));
        TtglAsesmen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TtglAsesmenActionPerformed(evt);
            }
        });
        FormInput.add(TtglAsesmen);
        TtglAsesmen.setBounds(295, 66, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Ruang OK :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(385, 66, 75, 23);

        cmbRuangOK.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangOK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "4", "5", "6", "7" }));
        cmbRuangOK.setName("cmbRuangOK"); // NOI18N
        cmbRuangOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuangOKActionPerformed(evt);
            }
        });
        FormInput.add(cmbRuangOK);
        cmbRuangOK.setBounds(467, 66, 40, 23);

        cmbJnsRuang.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsRuang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "OK ENDOSCOPI", "OK BRONCHOSCOPY" }));
        cmbJnsRuang.setName("cmbJnsRuang"); // NOI18N
        FormInput.add(cmbJnsRuang);
        cmbJnsRuang.setBounds(515, 66, 134, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Jenis Anestesi :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 94, 130, 23);

        cmbJnsAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsAnestesi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "RA (Seb)", "GA (LIFT)" }));
        cmbJnsAnestesi.setName("cmbJnsAnestesi"); // NOI18N
        FormInput.add(cmbJnsAnestesi);
        cmbJnsAnestesi.setBounds(135, 94, 80, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("LA : Infiltrasi :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(215, 94, 85, 23);

        Tla.setForeground(new java.awt.Color(0, 0, 0));
        Tla.setName("Tla"); // NOI18N
        Tla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlaKeyPressed(evt);
            }
        });
        FormInput.add(Tla);
        Tla.setBounds(305, 94, 230, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Blok :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(535, 94, 40, 23);

        Tblok.setForeground(new java.awt.Color(0, 0, 0));
        Tblok.setName("Tblok"); // NOI18N
        Tblok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblokKeyPressed(evt);
            }
        });
        FormInput.add(Tblok);
        Tblok.setBounds(580, 94, 170, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Dx. Pre Operasi :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 122, 130, 23);

        TdxPreOperasi.setForeground(new java.awt.Color(0, 0, 0));
        TdxPreOperasi.setName("TdxPreOperasi"); // NOI18N
        TdxPreOperasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdxPreOperasiKeyPressed(evt);
            }
        });
        FormInput.add(TdxPreOperasi);
        TdxPreOperasi.setBounds(135, 122, 615, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Dx. Pasca Operasi :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 150, 130, 23);

        TdxPascaOperasi.setForeground(new java.awt.Color(0, 0, 0));
        TdxPascaOperasi.setName("TdxPascaOperasi"); // NOI18N
        TdxPascaOperasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdxPascaOperasiKeyPressed(evt);
            }
        });
        FormInput.add(TdxPascaOperasi);
        TdxPascaOperasi.setBounds(135, 150, 615, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Operasi Mulai Pukul :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 178, 130, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(135, 178, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(186, 178, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(237, 178, 45, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Wita");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(290, 178, 40, 23);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("Dokter Operator :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(0, 206, 130, 23);

        TnipDokterOperator.setEditable(false);
        TnipDokterOperator.setForeground(new java.awt.Color(0, 0, 0));
        TnipDokterOperator.setName("TnipDokterOperator"); // NOI18N
        TnipDokterOperator.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipDokterOperator);
        TnipDokterOperator.setBounds(135, 206, 100, 23);

        TnmDokterOperator.setEditable(false);
        TnmDokterOperator.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokterOperator.setName("TnmDokterOperator"); // NOI18N
        TnmDokterOperator.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmDokterOperator);
        TnmDokterOperator.setBounds(238, 206, 410, 23);

        BtnDokterOperator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokterOperator.setMnemonic('2');
        BtnDokterOperator.setToolTipText("Alt+2");
        BtnDokterOperator.setName("BtnDokterOperator"); // NOI18N
        BtnDokterOperator.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokterOperator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterOperatorActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokterOperator);
        BtnDokterOperator.setBounds(650, 206, 28, 23);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Asisten :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label16);
        label16.setBounds(0, 234, 130, 23);

        TnipAsisten.setEditable(false);
        TnipAsisten.setForeground(new java.awt.Color(0, 0, 0));
        TnipAsisten.setName("TnipAsisten"); // NOI18N
        TnipAsisten.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipAsisten);
        TnipAsisten.setBounds(135, 234, 100, 23);

        TnmAsisten.setEditable(false);
        TnmAsisten.setForeground(new java.awt.Color(0, 0, 0));
        TnmAsisten.setName("TnmAsisten"); // NOI18N
        TnmAsisten.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmAsisten);
        TnmAsisten.setBounds(238, 234, 410, 23);

        BtnAsisten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAsisten.setMnemonic('2');
        BtnAsisten.setToolTipText("Alt+2");
        BtnAsisten.setName("BtnAsisten"); // NOI18N
        BtnAsisten.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAsisten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsistenActionPerformed(evt);
            }
        });
        FormInput.add(BtnAsisten);
        BtnAsisten.setBounds(650, 234, 28, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Instrumen :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 262, 130, 23);

        Tinstrumen.setForeground(new java.awt.Color(0, 0, 0));
        Tinstrumen.setName("Tinstrumen"); // NOI18N
        Tinstrumen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinstrumenKeyPressed(evt);
            }
        });
        FormInput.add(Tinstrumen);
        Tinstrumen.setBounds(135, 262, 615, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("On Loop :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 290, 130, 23);

        Tonloop.setForeground(new java.awt.Color(0, 0, 0));
        Tonloop.setName("Tonloop"); // NOI18N
        Tonloop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TonloopKeyPressed(evt);
            }
        });
        FormInput.add(Tonloop);
        Tonloop.setBounds(135, 290, 615, 23);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Dokter Anestesi :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label17);
        label17.setBounds(0, 318, 130, 23);

        TnipDokterAnestesi.setEditable(false);
        TnipDokterAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        TnipDokterAnestesi.setName("TnipDokterAnestesi"); // NOI18N
        TnipDokterAnestesi.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipDokterAnestesi);
        TnipDokterAnestesi.setBounds(135, 318, 100, 23);

        TnmDokterAnestesi.setEditable(false);
        TnmDokterAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokterAnestesi.setName("TnmDokterAnestesi"); // NOI18N
        TnmDokterAnestesi.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmDokterAnestesi);
        TnmDokterAnestesi.setBounds(238, 318, 410, 23);

        BtnDokterAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokterAnestesi.setMnemonic('2');
        BtnDokterAnestesi.setToolTipText("Alt+2");
        BtnDokterAnestesi.setName("BtnDokterAnestesi"); // NOI18N
        BtnDokterAnestesi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokterAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterAnestesiActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokterAnestesi);
        BtnDokterAnestesi.setBounds(650, 318, 28, 23);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setText("Perawat Anestesi :");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label18);
        label18.setBounds(0, 346, 130, 23);

        TnipPerawatAnestesi.setEditable(false);
        TnipPerawatAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        TnipPerawatAnestesi.setName("TnipPerawatAnestesi"); // NOI18N
        TnipPerawatAnestesi.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipPerawatAnestesi);
        TnipPerawatAnestesi.setBounds(135, 346, 100, 23);

        TnmPerawatAnestesi.setEditable(false);
        TnmPerawatAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawatAnestesi.setName("TnmPerawatAnestesi"); // NOI18N
        TnmPerawatAnestesi.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmPerawatAnestesi);
        TnmPerawatAnestesi.setBounds(238, 346, 410, 23);

        BtnPerawatAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPerawatAnestesi.setMnemonic('2');
        BtnPerawatAnestesi.setToolTipText("Alt+2");
        BtnPerawatAnestesi.setName("BtnPerawatAnestesi"); // NOI18N
        BtnPerawatAnestesi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPerawatAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerawatAnestesiActionPerformed(evt);
            }
        });
        FormInput.add(BtnPerawatAnestesi);
        BtnPerawatAnestesi.setBounds(650, 346, 28, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Operasi Selesai Pukul :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 374, 130, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(135, 374, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(186, 374, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(237, 374, 45, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Wita");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(290, 374, 40, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Data Subyektif :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 402, 130, 23);

        scrollPane4.setName("scrollPane4"); // NOI18N

        TdataSubyektif.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TdataSubyektif.setColumns(20);
        TdataSubyektif.setRows(5);
        TdataSubyektif.setName("TdataSubyektif"); // NOI18N
        TdataSubyektif.setPreferredSize(new java.awt.Dimension(162, 1000));
        TdataSubyektif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdataSubyektifKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(TdataSubyektif);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(135, 402, 615, 70);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Data Obyektif :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 478, 130, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Kesadaran :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(135, 478, 70, 23);

        cmbKesadaran.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Compos Mentis", "Somnolen", "Sopor", "Coma" }));
        cmbKesadaran.setName("cmbKesadaran"); // NOI18N
        FormInput.add(cmbKesadaran);
        cmbKesadaran.setBounds(210, 478, 105, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Tanda Vital : TD :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 506, 130, 23);

        Ttensi.setForeground(new java.awt.Color(0, 0, 0));
        Ttensi.setName("Ttensi"); // NOI18N
        Ttensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtensiKeyPressed(evt);
            }
        });
        FormInput.add(Ttensi);
        Ttensi.setBounds(135, 506, 65, 23);

        label104.setForeground(new java.awt.Color(0, 0, 0));
        label104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label104.setText("mmHg    Suhu :");
        label104.setName("label104"); // NOI18N
        label104.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label104);
        label104.setBounds(205, 506, 75, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        FormInput.add(Tsuhu);
        Tsuhu.setBounds(280, 506, 50, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("°C     Nadi :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(335, 506, 58, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(395, 506, 60, 23);

        label43.setForeground(new java.awt.Color(0, 0, 0));
        label43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label43.setText("x/menit      Respirasi : ");
        label43.setName("label43"); // NOI18N
        label43.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label43);
        label43.setBounds(460, 506, 108, 23);

        Trespi.setForeground(new java.awt.Color(0, 0, 0));
        Trespi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Trespi.setName("Trespi"); // NOI18N
        Trespi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrespiKeyPressed(evt);
            }
        });
        FormInput.add(Trespi);
        Trespi.setBounds(568, 506, 60, 23);

        label106.setForeground(new java.awt.Color(0, 0, 0));
        label106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label106.setText("x/menit");
        label106.setName("label106"); // NOI18N
        label106.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label106);
        label106.setBounds(635, 506, 60, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("GCS : E :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 534, 130, 23);

        TgcsE.setForeground(new java.awt.Color(0, 0, 0));
        TgcsE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TgcsE.setName("TgcsE"); // NOI18N
        TgcsE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsEKeyPressed(evt);
            }
        });
        FormInput.add(TgcsE);
        TgcsE.setBounds(135, 534, 50, 23);

        label105.setForeground(new java.awt.Color(0, 0, 0));
        label105.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label105.setText("M :");
        label105.setName("label105"); // NOI18N
        label105.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label105);
        label105.setBounds(185, 534, 30, 23);

        TgcsM.setForeground(new java.awt.Color(0, 0, 0));
        TgcsM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TgcsM.setName("TgcsM"); // NOI18N
        TgcsM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsMKeyPressed(evt);
            }
        });
        FormInput.add(TgcsM);
        TgcsM.setBounds(215, 534, 50, 23);

        label107.setForeground(new java.awt.Color(0, 0, 0));
        label107.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label107.setText("V :");
        label107.setName("label107"); // NOI18N
        label107.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label107);
        label107.setBounds(265, 534, 30, 23);

        TgcsV.setForeground(new java.awt.Color(0, 0, 0));
        TgcsV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TgcsV.setName("TgcsV"); // NOI18N
        TgcsV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsVKeyPressed(evt);
            }
        });
        FormInput.add(TgcsV);
        TgcsV.setBounds(293, 534, 50, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Posisi Pasien :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 562, 130, 23);

        chkSupin.setBackground(new java.awt.Color(242, 242, 242));
        chkSupin.setForeground(new java.awt.Color(0, 0, 0));
        chkSupin.setText("Supinasi");
        chkSupin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSupin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSupin.setName("chkSupin"); // NOI18N
        chkSupin.setOpaque(false);
        chkSupin.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSupin);
        chkSupin.setBounds(135, 562, 70, 23);

        chkLitotomi.setBackground(new java.awt.Color(242, 242, 242));
        chkLitotomi.setForeground(new java.awt.Color(0, 0, 0));
        chkLitotomi.setText("Litotomi");
        chkLitotomi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLitotomi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLitotomi.setName("chkLitotomi"); // NOI18N
        chkLitotomi.setOpaque(false);
        chkLitotomi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkLitotomi);
        chkLitotomi.setBounds(215, 562, 70, 23);

        chkPronasi.setBackground(new java.awt.Color(242, 242, 242));
        chkPronasi.setForeground(new java.awt.Color(0, 0, 0));
        chkPronasi.setText("Pronasi");
        chkPronasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPronasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPronasi.setName("chkPronasi"); // NOI18N
        chkPronasi.setOpaque(false);
        chkPronasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkPronasi);
        chkPronasi.setBounds(295, 562, 65, 23);

        chkTrendelen.setBackground(new java.awt.Color(242, 242, 242));
        chkTrendelen.setForeground(new java.awt.Color(0, 0, 0));
        chkTrendelen.setText("Trendelenburg");
        chkTrendelen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTrendelen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTrendelen.setName("chkTrendelen"); // NOI18N
        chkTrendelen.setOpaque(false);
        chkTrendelen.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkTrendelen);
        chkTrendelen.setBounds(370, 562, 100, 23);

        chkLateral.setBackground(new java.awt.Color(242, 242, 242));
        chkLateral.setForeground(new java.awt.Color(0, 0, 0));
        chkLateral.setText("Lateral Kanan/Kiri");
        chkLateral.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLateral.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLateral.setName("chkLateral"); // NOI18N
        chkLateral.setOpaque(false);
        chkLateral.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkLateral);
        chkLateral.setBounds(480, 562, 120, 23);

        chkAlkohol.setBackground(new java.awt.Color(242, 242, 242));
        chkAlkohol.setForeground(new java.awt.Color(0, 0, 0));
        chkAlkohol.setText("Alkohol 70 %");
        chkAlkohol.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAlkohol.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAlkohol.setName("chkAlkohol"); // NOI18N
        chkAlkohol.setOpaque(false);
        chkAlkohol.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkAlkohol);
        chkAlkohol.setBounds(135, 618, 90, 23);

        TlainPosisi.setForeground(new java.awt.Color(0, 0, 0));
        TlainPosisi.setName("TlainPosisi"); // NOI18N
        TlainPosisi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainPosisiKeyPressed(evt);
            }
        });
        FormInput.add(TlainPosisi);
        TlainPosisi.setBounds(200, 590, 550, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Lainnya :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(135, 590, 60, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Preparasi :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 618, 130, 23);

        chkIodine.setBackground(new java.awt.Color(242, 242, 242));
        chkIodine.setForeground(new java.awt.Color(0, 0, 0));
        chkIodine.setText("Iodine Providone 10 %");
        chkIodine.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIodine.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIodine.setName("chkIodine"); // NOI18N
        chkIodine.setOpaque(false);
        chkIodine.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkIodine);
        chkIodine.setBounds(233, 618, 140, 23);

        chkBrono.setBackground(new java.awt.Color(242, 242, 242));
        chkBrono.setForeground(new java.awt.Color(0, 0, 0));
        chkBrono.setText("Bronouderm");
        chkBrono.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBrono.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBrono.setName("chkBrono"); // NOI18N
        chkBrono.setOpaque(false);
        chkBrono.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkBrono);
        chkBrono.setBounds(380, 618, 90, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Area Operasi :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(0, 646, 130, 23);

        Tarea.setForeground(new java.awt.Color(0, 0, 0));
        Tarea.setName("Tarea"); // NOI18N
        Tarea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TareaKeyPressed(evt);
            }
        });
        FormInput.add(Tarea);
        Tarea.setBounds(135, 646, 430, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Ekstremitas :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(565, 646, 80, 23);

        chkEksKanan.setBackground(new java.awt.Color(242, 242, 242));
        chkEksKanan.setForeground(new java.awt.Color(0, 0, 0));
        chkEksKanan.setText("Kanan");
        chkEksKanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEksKanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEksKanan.setName("chkEksKanan"); // NOI18N
        chkEksKanan.setOpaque(false);
        chkEksKanan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkEksKanan);
        chkEksKanan.setBounds(650, 646, 55, 23);

        chkEksKiri.setBackground(new java.awt.Color(242, 242, 242));
        chkEksKiri.setForeground(new java.awt.Color(0, 0, 0));
        chkEksKiri.setText("Kiri");
        chkEksKiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEksKiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEksKiri.setName("chkEksKiri"); // NOI18N
        chkEksKiri.setOpaque(false);
        chkEksKiri.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkEksKiri);
        chkEksKiri.setBounds(713, 646, 43, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Cuci Luka :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 674, 130, 23);

        chkPerhid.setBackground(new java.awt.Color(242, 242, 242));
        chkPerhid.setForeground(new java.awt.Color(0, 0, 0));
        chkPerhid.setText("Perhidrol");
        chkPerhid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerhid.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerhid.setName("chkPerhid"); // NOI18N
        chkPerhid.setOpaque(false);
        chkPerhid.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkPerhid);
        chkPerhid.setBounds(135, 674, 70, 23);

        chkNacl.setBackground(new java.awt.Color(242, 242, 242));
        chkNacl.setForeground(new java.awt.Color(0, 0, 0));
        chkNacl.setText("NaCl");
        chkNacl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNacl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNacl.setName("chkNacl"); // NOI18N
        chkNacl.setOpaque(false);
        chkNacl.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkNacl);
        chkNacl.setBounds(215, 674, 50, 23);

        chkAqua.setBackground(new java.awt.Color(242, 242, 242));
        chkAqua.setForeground(new java.awt.Color(0, 0, 0));
        chkAqua.setText("Aqua Steril");
        chkAqua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAqua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAqua.setName("chkAqua"); // NOI18N
        chkAqua.setOpaque(false);
        chkAqua.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkAqua);
        chkAqua.setBounds(273, 674, 80, 23);

        TlainCuci.setForeground(new java.awt.Color(0, 0, 0));
        TlainCuci.setName("TlainCuci"); // NOI18N
        TlainCuci.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainCuciKeyPressed(evt);
            }
        });
        FormInput.add(TlainCuci);
        TlainCuci.setBounds(420, 674, 330, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Lainnya :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(367, 674, 50, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Penggunaan Alat :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(0, 702, 130, 23);

        chkSuction.setBackground(new java.awt.Color(242, 242, 242));
        chkSuction.setForeground(new java.awt.Color(0, 0, 0));
        chkSuction.setText("Suction Pump");
        chkSuction.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSuction.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSuction.setName("chkSuction"); // NOI18N
        chkSuction.setOpaque(false);
        chkSuction.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSuction);
        chkSuction.setBounds(135, 702, 90, 23);

        chkTurni.setBackground(new java.awt.Color(242, 242, 242));
        chkTurni.setForeground(new java.awt.Color(0, 0, 0));
        chkTurni.setText("Turniquet");
        chkTurni.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTurni.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTurni.setName("chkTurni"); // NOI18N
        chkTurni.setOpaque(false);
        chkTurni.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkTurni);
        chkTurni.setBounds(232, 702, 70, 23);

        chkBor.setBackground(new java.awt.Color(242, 242, 242));
        chkBor.setForeground(new java.awt.Color(0, 0, 0));
        chkBor.setText("Bor Orthopedi");
        chkBor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBor.setName("chkBor"); // NOI18N
        chkBor.setOpaque(false);
        chkBor.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkBor);
        chkBor.setBounds(310, 702, 100, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Lainnya :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(415, 702, 50, 23);

        TlainPenggunaan.setForeground(new java.awt.Color(0, 0, 0));
        TlainPenggunaan.setName("TlainPenggunaan"); // NOI18N
        TlainPenggunaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainPenggunaanKeyPressed(evt);
            }
        });
        FormInput.add(TlainPenggunaan);
        TlainPenggunaan.setBounds(470, 702, 280, 23);

        chkElek.setBackground(new java.awt.Color(242, 242, 242));
        chkElek.setForeground(new java.awt.Color(0, 0, 0));
        chkElek.setText("Electric Couter");
        chkElek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkElek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkElek.setName("chkElek"); // NOI18N
        chkElek.setOpaque(false);
        chkElek.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkElek);
        chkElek.setBounds(135, 730, 95, 23);

        chkBipo.setBackground(new java.awt.Color(242, 242, 242));
        chkBipo.setForeground(new java.awt.Color(0, 0, 0));
        chkBipo.setText("Bipolar");
        chkBipo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBipo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBipo.setName("chkBipo"); // NOI18N
        chkBipo.setOpaque(false);
        chkBipo.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkBipo);
        chkBipo.setBounds(238, 730, 60, 23);

        chkMono.setBackground(new java.awt.Color(242, 242, 242));
        chkMono.setForeground(new java.awt.Color(0, 0, 0));
        chkMono.setText("Monopolar");
        chkMono.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMono.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMono.setName("chkMono"); // NOI18N
        chkMono.setOpaque(false);
        chkMono.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkMono);
        chkMono.setBounds(305, 730, 80, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Negative Plat Di :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(390, 730, 90, 23);

        Tnegatif.setForeground(new java.awt.Color(0, 0, 0));
        Tnegatif.setName("Tnegatif"); // NOI18N
        Tnegatif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnegatifKeyPressed(evt);
            }
        });
        FormInput.add(Tnegatif);
        Tnegatif.setBounds(485, 730, 265, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Terpasang :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 758, 130, 23);

        chkNgt.setBackground(new java.awt.Color(242, 242, 242));
        chkNgt.setForeground(new java.awt.Color(0, 0, 0));
        chkNgt.setText("NGT");
        chkNgt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNgt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNgt.setName("chkNgt"); // NOI18N
        chkNgt.setOpaque(false);
        chkNgt.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkNgt);
        chkNgt.setBounds(135, 758, 50, 23);

        chkDc.setBackground(new java.awt.Color(242, 242, 242));
        chkDc.setForeground(new java.awt.Color(0, 0, 0));
        chkDc.setText("DC");
        chkDc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDc.setName("chkDc"); // NOI18N
        chkDc.setOpaque(false);
        chkDc.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkDc);
        chkDc.setBounds(193, 758, 40, 23);

        chkIrigasi.setBackground(new java.awt.Color(242, 242, 242));
        chkIrigasi.setForeground(new java.awt.Color(0, 0, 0));
        chkIrigasi.setText("Irigasi");
        chkIrigasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIrigasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIrigasi.setName("chkIrigasi"); // NOI18N
        chkIrigasi.setOpaque(false);
        chkIrigasi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkIrigasi);
        chkIrigasi.setBounds(240, 758, 60, 23);

        chkEtt.setBackground(new java.awt.Color(242, 242, 242));
        chkEtt.setForeground(new java.awt.Color(0, 0, 0));
        chkEtt.setText("ETT");
        chkEtt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEtt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEtt.setName("chkEtt"); // NOI18N
        chkEtt.setOpaque(false);
        chkEtt.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkEtt);
        chkEtt.setBounds(305, 758, 50, 23);

        chkEpid.setBackground(new java.awt.Color(242, 242, 242));
        chkEpid.setForeground(new java.awt.Color(0, 0, 0));
        chkEpid.setText("Epidural");
        chkEpid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEpid.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEpid.setName("chkEpid"); // NOI18N
        chkEpid.setOpaque(false);
        chkEpid.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkEpid);
        chkEpid.setBounds(360, 758, 65, 23);

        chkArm.setBackground(new java.awt.Color(242, 242, 242));
        chkArm.setForeground(new java.awt.Color(0, 0, 0));
        chkArm.setText("Armsling");
        chkArm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkArm.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkArm.setName("chkArm"); // NOI18N
        chkArm.setOpaque(false);
        chkArm.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkArm);
        chkArm.setBounds(435, 758, 65, 23);

        chkGips.setBackground(new java.awt.Color(242, 242, 242));
        chkGips.setForeground(new java.awt.Color(0, 0, 0));
        chkGips.setText("Gips");
        chkGips.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGips.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGips.setName("chkGips"); // NOI18N
        chkGips.setOpaque(false);
        chkGips.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkGips);
        chkGips.setBounds(510, 758, 50, 23);

        chkDrain.setBackground(new java.awt.Color(242, 242, 242));
        chkDrain.setForeground(new java.awt.Color(0, 0, 0));
        chkDrain.setText("Drain di");
        chkDrain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDrain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDrain.setName("chkDrain"); // NOI18N
        chkDrain.setOpaque(false);
        chkDrain.setPreferredSize(new java.awt.Dimension(220, 23));
        chkDrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDrainActionPerformed(evt);
            }
        });
        FormInput.add(chkDrain);
        chkDrain.setBounds(135, 786, 62, 23);

        Tdrain.setForeground(new java.awt.Color(0, 0, 0));
        Tdrain.setName("Tdrain"); // NOI18N
        Tdrain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdrainKeyPressed(evt);
            }
        });
        FormInput.add(Tdrain);
        Tdrain.setBounds(201, 786, 150, 23);

        label108.setForeground(new java.awt.Color(0, 0, 0));
        label108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label108.setText("bagian");
        label108.setToolTipText("");
        label108.setName("label108"); // NOI18N
        label108.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label108);
        label108.setBounds(357, 786, 40, 23);

        chkDrainKanan.setBackground(new java.awt.Color(242, 242, 242));
        chkDrainKanan.setForeground(new java.awt.Color(0, 0, 0));
        chkDrainKanan.setText("Kanan");
        chkDrainKanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDrainKanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDrainKanan.setName("chkDrainKanan"); // NOI18N
        chkDrainKanan.setOpaque(false);
        chkDrainKanan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkDrainKanan);
        chkDrainKanan.setBounds(405, 786, 55, 23);

        chkDrainKiri.setBackground(new java.awt.Color(242, 242, 242));
        chkDrainKiri.setForeground(new java.awt.Color(0, 0, 0));
        chkDrainKiri.setText("Kiri");
        chkDrainKiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDrainKiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDrainKiri.setName("chkDrainKiri"); // NOI18N
        chkDrainKiri.setOpaque(false);
        chkDrainKiri.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkDrainKiri);
        chkDrainKiri.setBounds(475, 786, 43, 23);

        chkInfus.setBackground(new java.awt.Color(242, 242, 242));
        chkInfus.setForeground(new java.awt.Color(0, 0, 0));
        chkInfus.setText("Infus di");
        chkInfus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInfus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInfus.setName("chkInfus"); // NOI18N
        chkInfus.setOpaque(false);
        chkInfus.setPreferredSize(new java.awt.Dimension(220, 23));
        chkInfus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkInfusActionPerformed(evt);
            }
        });
        FormInput.add(chkInfus);
        chkInfus.setBounds(135, 814, 62, 23);

        Tinfus.setForeground(new java.awt.Color(0, 0, 0));
        Tinfus.setName("Tinfus"); // NOI18N
        Tinfus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinfusKeyPressed(evt);
            }
        });
        FormInput.add(Tinfus);
        Tinfus.setBounds(201, 814, 150, 23);

        label109.setForeground(new java.awt.Color(0, 0, 0));
        label109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label109.setText("bagian");
        label109.setToolTipText("");
        label109.setName("label109"); // NOI18N
        label109.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label109);
        label109.setBounds(357, 814, 40, 23);

        chkInfusKanan.setBackground(new java.awt.Color(242, 242, 242));
        chkInfusKanan.setForeground(new java.awt.Color(0, 0, 0));
        chkInfusKanan.setText("Kanan");
        chkInfusKanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInfusKanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInfusKanan.setName("chkInfusKanan"); // NOI18N
        chkInfusKanan.setOpaque(false);
        chkInfusKanan.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkInfusKanan);
        chkInfusKanan.setBounds(405, 814, 55, 23);

        chkInfusKiri.setBackground(new java.awt.Color(242, 242, 242));
        chkInfusKiri.setForeground(new java.awt.Color(0, 0, 0));
        chkInfusKiri.setText("Kiri");
        chkInfusKiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInfusKiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInfusKiri.setName("chkInfusKiri"); // NOI18N
        chkInfusKiri.setOpaque(false);
        chkInfusKiri.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkInfusKiri);
        chkInfusKiri.setBounds(475, 814, 43, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Transfusi :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(0, 842, 130, 23);

        chkYa.setBackground(new java.awt.Color(242, 242, 242));
        chkYa.setForeground(new java.awt.Color(0, 0, 0));
        chkYa.setText("Ya");
        chkYa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkYa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkYa.setName("chkYa"); // NOI18N
        chkYa.setOpaque(false);
        chkYa.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkYa);
        chkYa.setBounds(135, 842, 40, 23);

        chkTidak.setBackground(new java.awt.Color(242, 242, 242));
        chkTidak.setForeground(new java.awt.Color(0, 0, 0));
        chkTidak.setText("Tidak Berupa");
        chkTidak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidak.setName("chkTidak"); // NOI18N
        chkTidak.setOpaque(false);
        chkTidak.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkTidak);
        chkTidak.setBounds(181, 842, 88, 23);

        chkWb.setBackground(new java.awt.Color(242, 242, 242));
        chkWb.setForeground(new java.awt.Color(0, 0, 0));
        chkWb.setText("WB");
        chkWb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkWb.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkWb.setName("chkWb"); // NOI18N
        chkWb.setOpaque(false);
        chkWb.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkWb);
        chkWb.setBounds(275, 842, 46, 23);

        chkPrc.setBackground(new java.awt.Color(242, 242, 242));
        chkPrc.setForeground(new java.awt.Color(0, 0, 0));
        chkPrc.setText("PRC");
        chkPrc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPrc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPrc.setName("chkPrc"); // NOI18N
        chkPrc.setOpaque(false);
        chkPrc.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkPrc);
        chkPrc.setBounds(330, 842, 50, 23);

        chkSebanyak.setBackground(new java.awt.Color(242, 242, 242));
        chkSebanyak.setForeground(new java.awt.Color(0, 0, 0));
        chkSebanyak.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSebanyak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSebanyak.setName("chkSebanyak"); // NOI18N
        chkSebanyak.setOpaque(false);
        chkSebanyak.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSebanyak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSebanyakActionPerformed(evt);
            }
        });
        FormInput.add(chkSebanyak);
        chkSebanyak.setBounds(390, 842, 25, 23);

        Tsebanyak1.setForeground(new java.awt.Color(0, 0, 0));
        Tsebanyak1.setName("Tsebanyak1"); // NOI18N
        Tsebanyak1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tsebanyak1KeyPressed(evt);
            }
        });
        FormInput.add(Tsebanyak1);
        Tsebanyak1.setBounds(415, 842, 110, 23);

        label110.setForeground(new java.awt.Color(0, 0, 0));
        label110.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label110.setText("Sebanyak");
        label110.setToolTipText("");
        label110.setName("label110"); // NOI18N
        label110.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label110);
        label110.setBounds(525, 842, 65, 23);

        Tsebanyak2.setForeground(new java.awt.Color(0, 0, 0));
        Tsebanyak2.setName("Tsebanyak2"); // NOI18N
        FormInput.add(Tsebanyak2);
        Tsebanyak2.setBounds(590, 842, 110, 23);

        label111.setForeground(new java.awt.Color(0, 0, 0));
        label111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label111.setText("kalf");
        label111.setName("label111"); // NOI18N
        label111.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label111);
        label111.setBounds(706, 842, 30, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Hitungan Selama Operasi");
        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 870, 170, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Jenis :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(0, 898, 130, 23);

        cmbJnsHitungan.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsHitungan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kassa Lipat", "Kassa Gulung", "Kassa Deppers", "Jarum Lepas", "Jarum ATR", "Duk Klem", "Lainnya" }));
        cmbJnsHitungan.setName("cmbJnsHitungan"); // NOI18N
        cmbJnsHitungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJnsHitunganActionPerformed(evt);
            }
        });
        FormInput.add(cmbJnsHitungan);
        cmbJnsHitungan.setBounds(135, 898, 105, 23);

        TlainJenis.setForeground(new java.awt.Color(0, 0, 0));
        TlainJenis.setName("TlainJenis"); // NOI18N
        TlainJenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainJenisKeyPressed(evt);
            }
        });
        FormInput.add(TlainJenis);
        TlainJenis.setBounds(245, 898, 505, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Awal :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(0, 926, 130, 23);

        Tawal.setForeground(new java.awt.Color(0, 0, 0));
        Tawal.setName("Tawal"); // NOI18N
        Tawal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TawalKeyPressed(evt);
            }
        });
        FormInput.add(Tawal);
        Tawal.setBounds(135, 926, 65, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Tambahan :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(200, 926, 80, 23);

        Ttambahan.setForeground(new java.awt.Color(0, 0, 0));
        Ttambahan.setName("Ttambahan"); // NOI18N
        Ttambahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtambahanKeyPressed(evt);
            }
        });
        FormInput.add(Ttambahan);
        Ttambahan.setBounds(286, 926, 65, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Akhir :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(350, 926, 50, 23);

        Takhir.setForeground(new java.awt.Color(0, 0, 0));
        Takhir.setName("Takhir"); // NOI18N
        Takhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TakhirKeyPressed(evt);
            }
        });
        FormInput.add(Takhir);
        Takhir.setBounds(405, 926, 65, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Keterangan :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(470, 926, 76, 23);

        Tketerangan.setForeground(new java.awt.Color(0, 0, 0));
        Tketerangan.setName("Tketerangan"); // NOI18N
        Tketerangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketeranganKeyPressed(evt);
            }
        });
        FormInput.add(Tketerangan);
        Tketerangan.setBounds(550, 926, 200, 23);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbHitungan.setToolTipText("Silahkan klik/pilih salah satu data untuk dihapus");
        tbHitungan.setName("tbHitungan"); // NOI18N
        Scroll2.setViewportView(tbHitungan);

        FormInput.add(Scroll2);
        Scroll2.setBounds(135, 954, 500, 200);

        BtnTambah.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/add-file-16x16.png"))); // NOI18N
        BtnTambah.setMnemonic('2');
        BtnTambah.setText("Tambah Data");
        BtnTambah.setName("BtnTambah"); // NOI18N
        BtnTambah.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambah);
        BtnTambah.setBounds(640, 954, 120, 23);

        BtnHapusJns.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusJns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusJns.setMnemonic('2');
        BtnHapusJns.setText("Hapus Jenis");
        BtnHapusJns.setName("BtnHapusJns"); // NOI18N
        BtnHapusJns.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnHapusJns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusJnsActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusJns);
        BtnHapusJns.setBounds(640, 984, 120, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kejadian Penting Selama Operasi : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N

        Tkejadian.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkejadian.setColumns(20);
        Tkejadian.setRows(5);
        Tkejadian.setName("Tkejadian"); // NOI18N
        Tkejadian.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tkejadian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkejadianKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(Tkejadian);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(135, 1159, 615, 120);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Perawat K. Operasi :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(0, 1284, 130, 23);

        TnipPerawatOperasi.setEditable(false);
        TnipPerawatOperasi.setForeground(new java.awt.Color(0, 0, 0));
        TnipPerawatOperasi.setName("TnipPerawatOperasi"); // NOI18N
        TnipPerawatOperasi.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipPerawatOperasi);
        TnipPerawatOperasi.setBounds(135, 1284, 100, 23);

        TnmPerawatOperasi.setEditable(false);
        TnmPerawatOperasi.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawatOperasi.setName("TnmPerawatOperasi"); // NOI18N
        TnmPerawatOperasi.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmPerawatOperasi);
        TnmPerawatOperasi.setBounds(238, 1284, 410, 23);

        BtnPerawatOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPerawatOperasi.setMnemonic('2');
        BtnPerawatOperasi.setToolTipText("Alt+2");
        BtnPerawatOperasi.setName("BtnPerawatOperasi"); // NOI18N
        BtnPerawatOperasi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPerawatOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerawatOperasiActionPerformed(evt);
            }
        });
        FormInput.add(BtnPerawatOperasi);
        BtnPerawatOperasi.setBounds(650, 1284, 28, 23);

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
        chkSaya.setBounds(690, 1284, 87, 23);

        BtnDataSubyektif.setForeground(new java.awt.Color(0, 0, 0));
        BtnDataSubyektif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDataSubyektif.setMnemonic('2');
        BtnDataSubyektif.setText("Template");
        BtnDataSubyektif.setToolTipText("Alt+2");
        BtnDataSubyektif.setName("BtnDataSubyektif"); // NOI18N
        BtnDataSubyektif.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDataSubyektif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataSubyektifActionPerformed(evt);
            }
        });
        FormInput.add(BtnDataSubyektif);
        BtnDataSubyektif.setBounds(760, 402, 100, 23);

        BtnKejadian.setForeground(new java.awt.Color(0, 0, 0));
        BtnKejadian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKejadian.setMnemonic('2');
        BtnKejadian.setText("Template");
        BtnKejadian.setToolTipText("Alt+2");
        BtnKejadian.setName("BtnKejadian"); // NOI18N
        BtnKejadian.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnKejadian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKejadianActionPerformed(evt);
            }
        });
        FormInput.add(BtnKejadian);
        BtnKejadian.setBounds(760, 1166, 100, 23);

        Scroll1.setViewportView(FormInput);

        internalFrame1.add(Scroll1, java.awt.BorderLayout.CENTER);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Assesmen Keperawatan Perioperatif ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(700, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbAsesmen.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbAsesmen.setName("tbAsesmen"); // NOI18N
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
        jLabel19.setText("Tgl. Assesmen :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-09-2024" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-09-2024" }));
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

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('M');
        BtnAll1.setText("Semua");
        BtnAll1.setToolTipText("Alt+M");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(100, 23));
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
        panelGlass10.add(BtnAll1);

        panelGlass11.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        PanelInput1.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput1, java.awt.BorderLayout.EAST);

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
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            cekData();
            wktSimpanAses = Sequel.cariIsi("select now()");
            if (Sequel.menyimpantf("asesmen_keperawatan_perioperatif", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 77, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""), Thari.getText(), cmbRuangOK.getSelectedItem().toString(),
                        cmbJnsRuang.getSelectedItem().toString(), cmbJnsAnestesi.getSelectedItem().toString(), Tla.getText(), Tblok.getText(), TdxPreOperasi.getText(), 
                        TdxPascaOperasi.getText(), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TnipDokterOperator.getText(),
                        TnipAsisten.getText(), Tinstrumen.getText(), Tonloop.getText(), TnipDokterAnestesi.getText(), TnipPerawatAnestesi.getText(), 
                        cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), TdataSubyektif.getText(), cmbKesadaran.getSelectedItem().toString(),
                        Ttensi.getText(), Trespi.getText(), Tnadi.getText(), Tsuhu.getText(), TgcsE.getText(), TgcsM.getText(), TgcsV.getText(), supin, litotomi, pronasi, trendel, 
                        lateral, TlainPosisi.getText(), alkohol, iodin, brono, Tarea.getText(), eksKanan, eksKiri, perhid, nacl, aqua, TlainCuci.getText(), suction, turniq, bor,
                        TlainPenggunaan.getText(), elek, bipo, mono, Tnegatif.getText(), ngt, dc, irigasi, ett, epid, arm, gip, drain, Tdrain.getText(), draKanan, draKiri, 
                        infus, Tinfus.getText(), infKanan, infKiri, ya, tidak, wb, prc, sebanyak, Tsebanyak1.getText(), Tsebanyak2.getText(), Tkejadian.getText(), TnipPerawatOperasi.getText(),                        
                        wktSimpanAses
                    }) == true) {                
                
                //simpan hitungan selama operasi                
                for (i = 0; i < tbHitungan.getRowCount(); i++) {
                    Sequel.menyimpan2("hitungan_asesmen_keperawatan_perioperatif", "?,?,?,?,?,?,?,?", 8, new String[]{
                        TNoRw.getText(), 
                        wktSimpanAses,
                        tbHitungan.getValueAt(i, 0).toString(),//jenis
                        tbHitungan.getValueAt(i, 1).toString(),//awal
                        tbHitungan.getValueAt(i, 2).toString(),//tambahan
                        tbHitungan.getValueAt(i, 3).toString(),//akhir
                        tbHitungan.getValueAt(i, 4).toString(),//keterangan
                        tbHitungan.getValueAt(i, 6).toString()//waktu_simpan
                    });
                }

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
                if (Sequel.mengedittf("asesmen_keperawatan_perioperatif", "waktu_simpan=?", "tgl_asesmen=?, hari=?, ruang_ok=?, jenis_ruang_ok=?, jenis_anestesi=?, la_infiltrasi=?, blok=?, "
                        + "dx_pre_operasi=?, dx_pasca_operasi=?, mulai_operasi=?, nip_dokter_operator=?, nip_asisten=?, instrumen=?, on_loop=?, nip_dokter_anestesi=?, nip_perawat_anestesi=?, "
                        + "selesai_operasi=?, data_subjektif=?, kesadaran=?, td=?, respi=?, nadi=?, suhu=?, gcs_e=?, gcs_m=?, gcs_v=?, supinasi=?, litotomi=?, pronasi=?, trendelenburg=?, "
                        + "lateral_kanan_kiri=?, posisi_lainnya=?, alkohol_70=?, iodine_providone_10=?, bronouderm=?, area_operasi=?, ekstremitas_kanan=?, ekstremitas_kiri=?, perhidrol=?, "
                        + "nacl=?, aqua_steril=?, cuci_luka_lainnya=?, suction_pump=?, turniquet=?, bor_ortopedi=?, penggunaan_alat_lainnya=?, elestric_couter=?, bipolar=?, monopolar=?, "
                        + "negatif_plat_di=?, ngt=?, dc=?, irigasi=?, ett=?, epidural=?, armsling=?, gips=?, drain=?, ket_drain=?, drain_kanan=?, drain_kiri=?, infus=?, ket_infus=?, infus_kanan=?, "
                        + "infus_kiri=?, ya=?, tidak_berupa=?, wb=?, prc=?, sebanyak=?, ket_sebanyak1=?, ket_sebanyak2=?, kejadian=?, nip_perawat=?", 75, new String[]{
                            Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""), Thari.getText(), cmbRuangOK.getSelectedItem().toString(),
                            cmbJnsRuang.getSelectedItem().toString(), cmbJnsAnestesi.getSelectedItem().toString(), Tla.getText(), Tblok.getText(), TdxPreOperasi.getText(),
                            TdxPascaOperasi.getText(), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TnipDokterOperator.getText(),
                            TnipAsisten.getText(), Tinstrumen.getText(), Tonloop.getText(), TnipDokterAnestesi.getText(), TnipPerawatAnestesi.getText(),
                            cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), TdataSubyektif.getText(), cmbKesadaran.getSelectedItem().toString(),
                            Ttensi.getText(), Trespi.getText(), Tnadi.getText(), Tsuhu.getText(), TgcsE.getText(), TgcsM.getText(), TgcsV.getText(), supin, litotomi, pronasi, trendel,
                            lateral, TlainPosisi.getText(), alkohol, iodin, brono, Tarea.getText(), eksKanan, eksKiri, perhid, nacl, aqua, TlainCuci.getText(), suction, turniq, bor,
                            TlainPenggunaan.getText(), elek, bipo, mono, Tnegatif.getText(), ngt, dc, irigasi, ett, epid, arm, gip, drain, Tdrain.getText(), draKanan, draKiri,
                            infus, Tinfus.getText(), infKanan, infKiri, ya, tidak, wb, prc, sebanyak, Tsebanyak1.getText(), Tsebanyak2.getText(), Tkejadian.getText(), TnipPerawatOperasi.getText(),                            
                            tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                        }) == true) {

                    //hitungan dihapus dulu
                    Sequel.meghapus("hitungan_asesmen_keperawatan_perioperatif", "waktu_simpan_asesmen='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString() + "' and no_rawat",
                            tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());

                    //simpan hitungan selama operasi
                    wktSimpanAses = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString();
                    for (i = 0; i < tbHitungan.getRowCount(); i++) {
                        Sequel.menyimpan2("hitungan_asesmen_keperawatan_perioperatif", "?,?,?,?,?,?,?,?", 8, new String[]{
                            TNoRw.getText(),
                            wktSimpanAses,
                            tbHitungan.getValueAt(i, 0).toString(),//jenis
                            tbHitungan.getValueAt(i, 1).toString(),//awal
                            tbHitungan.getValueAt(i, 2).toString(),//tambahan
                            tbHitungan.getValueAt(i, 3).toString(),//akhir
                            tbHitungan.getValueAt(i, 4).toString(),//keterangan
                            tbHitungan.getValueAt(i, 6).toString()//waktu_simpan
                        });
                    }

                    TCari.setText(TNoRw.getText());
                    tampil();
                    emptTeks();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbAsesmen.requestFocus();
            }
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnGantiActionPerformed(null);
        }else{
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

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        BtnCariActionPerformed(null);
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
            TCari.setText("");
        } else {
//            Valid.pindah(evt, BtnCari, kdkomite);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void BtnDokterOperatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterOperatorActionPerformed
        pilihan = 0;
        pilihan = 1;
        akses.setform("RMAsesmenKeperawatanPerioperatif");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterOperatorActionPerformed

    private void BtnAsistenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsistenActionPerformed
        pilihan = 0;
        pilihan = 3;
        akses.setform("RMAsesmenKeperawatanPerioperatif");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnAsistenActionPerformed

    private void BtnDokterAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterAnestesiActionPerformed
        pilihan = 0;
        pilihan = 2;
        akses.setform("RMAsesmenKeperawatanPerioperatif");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterAnestesiActionPerformed

    private void BtnPerawatAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatAnestesiActionPerformed
        pilihan = 0;
        pilihan = 4;
        akses.setform("RMAsesmenKeperawatanPerioperatif");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatAnestesiActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void TdataSubyektifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdataSubyektifKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbKesadaran.requestFocus();
        }
    }//GEN-LAST:event_TdataSubyektifKeyPressed

    private void TtensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtensiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsuhu.requestFocus();
        }
    }//GEN-LAST:event_TtensiKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnadi.requestFocus();
        }
    }//GEN-LAST:event_TsuhuKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trespi.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TrespiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrespiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TgcsE.requestFocus();
        }
    }//GEN-LAST:event_TrespiKeyPressed

    private void TgcsEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsEKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TgcsM.requestFocus();
        }
    }//GEN-LAST:event_TgcsEKeyPressed

    private void TgcsMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsMKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TgcsV.requestFocus();
        }
    }//GEN-LAST:event_TgcsMKeyPressed

    private void TgcsVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsVKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSupin.requestFocus();
        }
    }//GEN-LAST:event_TgcsVKeyPressed

    private void cmbRuangOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuangOKActionPerformed
        cmbJnsRuang.setSelectedIndex(0);
        if (cmbRuangOK.getSelectedIndex() == 0) {
            cmbJnsRuang.setEnabled(false);
        } else {
            cmbJnsRuang.setEnabled(true);
            cmbJnsRuang.requestFocus();
        }
    }//GEN-LAST:event_cmbRuangOKActionPerformed

    private void chkDrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDrainActionPerformed
        Tdrain.setText("");
        chkDrainKanan.setSelected(false);
        chkDrainKiri.setSelected(false);

        if (chkDrain.isSelected() == true) {
            Tdrain.setEnabled(true);
            Tdrain.requestFocus();
            chkDrainKanan.setEnabled(true);
            chkDrainKiri.setEnabled(true);
        } else {
            Tdrain.setEnabled(false);
            chkDrainKanan.setEnabled(false);
            chkDrainKiri.setEnabled(false);
        }
    }//GEN-LAST:event_chkDrainActionPerformed

    private void chkInfusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkInfusActionPerformed
        Tinfus.setText("");
        chkInfusKanan.setSelected(false);
        chkInfusKiri.setSelected(false);
        
        if (chkInfus.isSelected() == true) {
            Tinfus.setEnabled(true);
            Tinfus.requestFocus();
            chkInfusKanan.setEnabled(true);
            chkInfusKiri.setEnabled(true);
        } else {
            Tinfus.setEnabled(false);
            chkInfusKanan.setEnabled(false);
            chkInfusKiri.setEnabled(false);
        }
    }//GEN-LAST:event_chkInfusActionPerformed

    private void chkSebanyakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSebanyakActionPerformed
        Tsebanyak1.setText("");
        Tsebanyak2.setText("");
        
        if (chkSebanyak.isSelected() == true) {
            Tsebanyak1.setEnabled(true);
            Tsebanyak1.requestFocus();
            Tsebanyak2.setEnabled(true);
        } else {
            Tsebanyak1.setEnabled(false);
            Tsebanyak2.setEnabled(false);
        }
    }//GEN-LAST:event_chkSebanyakActionPerformed

    private void TlaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tblok.requestFocus();
        }
    }//GEN-LAST:event_TlaKeyPressed

    private void TblokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblokKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdxPreOperasi.requestFocus();
        }
    }//GEN-LAST:event_TblokKeyPressed

    private void TdxPreOperasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdxPreOperasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdxPascaOperasi.requestFocus();
        }
    }//GEN-LAST:event_TdxPreOperasiKeyPressed

    private void TdxPascaOperasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdxPascaOperasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJam.requestFocus();
        }
    }//GEN-LAST:event_TdxPascaOperasiKeyPressed

    private void TinstrumenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TinstrumenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tonloop.requestFocus();
        }
    }//GEN-LAST:event_TinstrumenKeyPressed

    private void TonloopKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TonloopKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnDokterAnestesi.requestFocus();
        }
    }//GEN-LAST:event_TonloopKeyPressed

    private void TlainPosisiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainPosisiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkAlkohol.requestFocus();
        }
    }//GEN-LAST:event_TlainPosisiKeyPressed

    private void TareaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TareaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkEksKanan.requestFocus();
        }
    }//GEN-LAST:event_TareaKeyPressed

    private void TlainCuciKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainCuciKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSuction.requestFocus();
        }
    }//GEN-LAST:event_TlainCuciKeyPressed

    private void TlainPenggunaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainPenggunaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkElek.requestFocus();
        }
    }//GEN-LAST:event_TlainPenggunaanKeyPressed

    private void TnegatifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnegatifKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkNgt.requestFocus();
        }
    }//GEN-LAST:event_TnegatifKeyPressed

    private void TdrainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdrainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkDrainKanan.requestFocus();
        }
    }//GEN-LAST:event_TdrainKeyPressed

    private void TinfusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TinfusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkInfusKanan.requestFocus();
        }
    }//GEN-LAST:event_TinfusKeyPressed

    private void Tsebanyak1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tsebanyak1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsebanyak2.requestFocus();
        }
    }//GEN-LAST:event_Tsebanyak1KeyPressed

    private void TtglAsesmenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TtglAsesmenActionPerformed
        Thari.setText(Sequel.hariINDONESIA("SELECT DATE_FORMAT('" + Valid.SetTgl(TtglAsesmen.getSelectedItem() + "") + "','%W')"));
    }//GEN-LAST:event_TtglAsesmenActionPerformed

    private void TlainJenisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainJenisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tawal.requestFocus();
        }
    }//GEN-LAST:event_TlainJenisKeyPressed

    private void TawalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TawalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttambahan.requestFocus();
        }
    }//GEN-LAST:event_TawalKeyPressed

    private void TtambahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtambahanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Takhir.requestFocus();
        }
    }//GEN-LAST:event_TtambahanKeyPressed

    private void TakhirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TakhirKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tketerangan.requestFocus();
        }
    }//GEN-LAST:event_TakhirKeyPressed

    private void TketeranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketeranganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnTambahActionPerformed(null);
        }
    }//GEN-LAST:event_TketeranganKeyPressed

    private void cmbJnsHitunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJnsHitunganActionPerformed
        TlainJenis.setText("");
        if (cmbJnsHitungan.getSelectedIndex() == 7) {
            TlainJenis.setEnabled(true);
            TlainJenis.requestFocus();
        } else {
            TlainJenis.setEnabled(false);
        }
    }//GEN-LAST:event_cmbJnsHitunganActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        if (cmbJnsHitungan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis yang dihitung..!!");
            cmbJnsHitungan.requestFocus();
        } else {
            x = 0;
            if (cmbJnsHitungan.getSelectedIndex() == 7) {
                for (i = 0; i < tbHitungan.getRowCount(); i++) {
                    if (tbHitungan.getValueAt(i, 0).toString().equals(TlainJenis.getText())) {
                        x++;
                    }
                }

                if (x > 0) {
                    JOptionPane.showMessageDialog(null, "Sudah ada jenis hitungan lainnya yang sama dimasukkan sebelumnya..!!!!");
                    tbHitungan.requestFocus();
                } else {
                    tabMode1.addRow(new String[]{TlainJenis.getText(), Tawal.getText(), Ttambahan.getText(),
                        Takhir.getText(), Tketerangan.getText(), TNoRw.getText(), Sequel.cariIsi("select now()")});
                    
                    cmbJnsHitungan.setSelectedIndex(0);
                    cmbJnsHitungan.requestFocus();
                    TlainJenis.setText("");
                    TlainJenis.setEnabled(false);
                    Tawal.setText("");
                    Ttambahan.setText("");
                    Takhir.setText("");
                    Tketerangan.setText("");
                }
            } else {
                for (i = 0; i < tbHitungan.getRowCount(); i++) {
                    if (tbHitungan.getValueAt(i, 0).toString().equals(cmbJnsHitungan.getSelectedItem().toString())) {
                        x++;
                    }
                }

                if (x > 0) {
                    JOptionPane.showMessageDialog(null, "Sudah ada jenis hitungan yang sama dimasukkan sebelumnya..!!!!");
                    tbHitungan.requestFocus();
                } else {
                    tabMode1.addRow(new String[]{cmbJnsHitungan.getSelectedItem().toString(), Tawal.getText(),
                        Ttambahan.getText(), Takhir.getText(), Tketerangan.getText(), TNoRw.getText(), Sequel.cariIsi("select now()")});
                    
                    cmbJnsHitungan.setSelectedIndex(0);
                    cmbJnsHitungan.requestFocus();
                    TlainJenis.setText("");
                    TlainJenis.setEnabled(false);
                    Tawal.setText("");
                    Ttambahan.setText("");
                    Takhir.setText("");
                    Tketerangan.setText("");
                }
            }
        }
    }//GEN-LAST:event_BtnTambahActionPerformed

    private void BtnHapusJnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusJnsActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Belum ada jenis hitungan yang bisa dihapus..!!");
        } else {
            if (tbHitungan.getSelectedRow() > -1) {
                tabMode1.removeRow(tbHitungan.getSelectedRow());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbHitungan.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusJnsActionPerformed

    private void TkejadianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkejadianKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnPerawatOperasi.requestFocus();
        }
    }//GEN-LAST:event_TkejadianKeyPressed

    private void BtnPerawatOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatOperasiActionPerformed
        pilihan = 0;
        pilihan = 5;
        akses.setform("RMAsesmenKeperawatanPerioperatif");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatOperasiActionPerformed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                TnipPerawatOperasi.setText("-");
                TnmPerawatOperasi.setText("-");
            } else {
                TnipPerawatOperasi.setText(akses.getkode());
                TnmPerawatOperasi.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipPerawatOperasi.getText() + "'"));
            }
        } else {
            TnipPerawatOperasi.setText("-");
            TnmPerawatOperasi.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void BtnDataSubyektifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataSubyektifActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 6;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Subyektif ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnDataSubyektifActionPerformed

    private void BtnKejadianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKejadianActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 7;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Kejadian Penting Selama Operasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnKejadianActionPerformed

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
        if(tabMode2.getRowCount() != 0) {
            try {
                if (tbTemplate.getSelectedRow() != -1) {
                    Ttemplate.setText(tbTemplate.getValueAt(tbTemplate.getSelectedRow(), 2).toString());
                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTemplateMouseClicked

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

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari.setText("");
        BtnCariActionPerformed(null);
        emptTeks();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
            TCari.setText("");
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {                
                if (Sequel.queryu2tf("delete from asesmen_keperawatan_perioperatif where waktu_simpan=?", 1, new String[]{
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                }) == true) {
                    Sequel.meghapus("hitungan_asesmen_keperawatan_perioperatif", "waktu_simpan_asesmen='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString() + "' and no_rawat",
                            tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());

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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenKeperawatanPerioperatif dialog = new RMAsesmenKeperawatanPerioperatif(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAsisten;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCopas;
    private widget.Button BtnDataSubyektif;
    private widget.Button BtnDokterAnestesi;
    private widget.Button BtnDokterOperator;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusJns;
    private widget.Button BtnKejadian;
    private widget.Button BtnKeluar;
    private widget.Button BtnPerawatAnestesi;
    private widget.Button BtnPerawatOperasi;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambah;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    public widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Takhir;
    private widget.TextBox Tarea;
    private widget.TextBox Tawal;
    private widget.TextBox Tblok;
    private widget.TextArea TdataSubyektif;
    private widget.TextBox Tdrain;
    private widget.TextBox TdxPascaOperasi;
    private widget.TextBox TdxPreOperasi;
    private widget.TextBox TgcsE;
    private widget.TextBox TgcsM;
    private widget.TextBox TgcsV;
    private widget.TextBox Thari;
    private widget.TextBox Tinfus;
    private widget.TextBox Tinstrumen;
    private widget.TextBox Tjk;
    private widget.TextArea Tkejadian;
    private widget.TextBox Tketerangan;
    private widget.TextBox Tla;
    private widget.TextBox TlainCuci;
    private widget.TextBox TlainJenis;
    private widget.TextBox TlainPenggunaan;
    private widget.TextBox TlainPosisi;
    private widget.TextBox Tnadi;
    private widget.TextBox Tnegatif;
    private widget.TextBox TnipAsisten;
    private widget.TextBox TnipDokterAnestesi;
    private widget.TextBox TnipDokterOperator;
    private widget.TextBox TnipPerawatAnestesi;
    private widget.TextBox TnipPerawatOperasi;
    private widget.TextBox TnmAsisten;
    private widget.TextBox TnmDokterAnestesi;
    private widget.TextBox TnmDokterOperator;
    private widget.TextBox TnmPerawatAnestesi;
    private widget.TextBox TnmPerawatOperasi;
    private widget.TextBox Tonloop;
    private widget.TextBox Trespi;
    private widget.TextBox TrgRawat;
    private widget.TextBox Tsebanyak1;
    private widget.TextBox Tsebanyak2;
    private widget.TextBox Tsuhu;
    private widget.TextBox Ttambahan;
    private widget.TextArea Ttemplate;
    private widget.TextBox Ttensi;
    private widget.Tanggal TtglAsesmen;
    private javax.swing.JDialog WindowTemplate;
    private widget.CekBox chkAlkohol;
    private widget.CekBox chkAqua;
    private widget.CekBox chkArm;
    private widget.CekBox chkBipo;
    private widget.CekBox chkBor;
    private widget.CekBox chkBrono;
    private widget.CekBox chkDc;
    private widget.CekBox chkDrain;
    private widget.CekBox chkDrainKanan;
    private widget.CekBox chkDrainKiri;
    private widget.CekBox chkEksKanan;
    private widget.CekBox chkEksKiri;
    private widget.CekBox chkElek;
    private widget.CekBox chkEpid;
    private widget.CekBox chkEtt;
    private widget.CekBox chkGips;
    private widget.CekBox chkInfus;
    private widget.CekBox chkInfusKanan;
    private widget.CekBox chkInfusKiri;
    private widget.CekBox chkIodine;
    private widget.CekBox chkIrigasi;
    private widget.CekBox chkLateral;
    private widget.CekBox chkLitotomi;
    private widget.CekBox chkMono;
    private widget.CekBox chkNacl;
    private widget.CekBox chkNgt;
    private widget.CekBox chkPerhid;
    private widget.CekBox chkPrc;
    private widget.CekBox chkPronasi;
    private widget.CekBox chkSaya;
    private widget.CekBox chkSebanyak;
    private widget.CekBox chkSuction;
    private widget.CekBox chkSupin;
    private widget.CekBox chkTidak;
    private widget.CekBox chkTrendelen;
    private widget.CekBox chkTurni;
    private widget.CekBox chkWb;
    private widget.CekBox chkYa;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJnsAnestesi;
    private widget.ComboBox cmbJnsHitungan;
    private widget.ComboBox cmbJnsRuang;
    private widget.ComboBox cmbKesadaran;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbRuangOK;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel23;
    private widget.Label jLabel36;
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
    private widget.Label jLabel96;
    private javax.swing.JPanel jPanel1;
    private widget.Label label104;
    private widget.Label label105;
    private widget.Label label106;
    private widget.Label label107;
    private widget.Label label108;
    private widget.Label label109;
    private widget.Label label110;
    private widget.Label label111;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label43;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbAsesmen;
    private widget.Table tbHitungan;
    private widget.Table tbTemplate;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT ak.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jk, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                    + "concat(ak.hari,', ',DATE_FORMAT(ak.tgl_asesmen,'%d-%m-%Y')) tglAses, concat(ak.ruang_ok,' - ',ak.jenis_ruang_ok) ruangOK, TIME_FORMAT(ak.mulai_operasi,'%H:%i Wita') jamMulai, "
                    + "pg1.nama drOperator, pg2.nama nmAsisten, pg3.nama drAnestesi, pg4.nama nmPrwtAnestesi, pg5.nama nmPrwtOperasi,TIME_FORMAT(ak.selesai_operasi,'%H:%i Wita') jamSelesai "
                    + "FROM asesmen_keperawatan_perioperatif ak INNER JOIN reg_periksa rp on rp.no_rawat=ak.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg1 on pg1.nik=ak.nip_dokter_operator INNER JOIN pegawai pg2 on pg2.nik=ak.nip_asisten INNER JOIN pegawai pg3 on pg3.nik=ak.nip_dokter_anestesi "
                    + "INNER JOIN pegawai pg4 on pg4.nik=ak.nip_perawat_anestesi INNER JOIN pegawai pg5 on pg5.nik=ak.nip_perawat WHERE "
                    + "ak.tgl_asesmen between ? and ? and ak.no_rawat LIKE ? or "
                    + "ak.tgl_asesmen between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "ak.tgl_asesmen between ? and ? and p.nm_pasien LIKE ? or "
                    + "ak.tgl_asesmen between ? and ? and pg1.nama LIKE ? or "
                    + "ak.tgl_asesmen between ? and ? and pg2.nama LIKE ? or "
                    + "ak.tgl_asesmen between ? and ? and pg3.nama LIKE ? or "
                    + "ak.tgl_asesmen between ? and ? and pg4.nama LIKE ? or "
                    + "ak.tgl_asesmen between ? and ? and pg5.nama LIKE ? or "
                    + "ak.tgl_asesmen between ? and ? and concat(ak.hari,', ',DATE_FORMAT( ak.tgl_asesmen, '%d-%m-%Y' )) LIKE ? or "
                    + "ak.tgl_asesmen between ? and ? and concat(ak.ruang_ok,' - ',ak.jenis_ruang_ok) LIKE ? ORDER BY ak.tgl_asesmen desc");
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
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText() + "%");
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText() + "%");
                ps.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(30, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();              
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("waktu_simpan"),
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jk"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglAses"),
                        rs.getString("ruangOK"),
                        rs.getString("jenis_anestesi"),
                        rs.getString("dx_pre_operasi"),
                        rs.getString("dx_pasca_operasi"),
                        rs.getString("jamMulai"),
                        rs.getString("drOperator"),
                        rs.getString("nmAsisten"),
                        rs.getString("instrumen"),
                        rs.getString("on_loop"),
                        rs.getString("drAnestesi"),
                        rs.getString("nmPrwtAnestesi"),
                        rs.getString("jamSelesai"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("hari"),
                        rs.getString("ruang_ok"),
                        rs.getString("jenis_ruang_ok"),
                        rs.getString("jenis_anestesi"),
                        rs.getString("la_infiltrasi"),
                        rs.getString("blok"),
                        rs.getString("dx_pre_operasi"),
                        rs.getString("dx_pasca_operasi"),
                        rs.getString("mulai_operasi"),
                        rs.getString("nip_dokter_operator"),
                        rs.getString("nip_asisten"),
                        rs.getString("instrumen"),
                        rs.getString("on_loop"),
                        rs.getString("nip_dokter_anestesi"),
                        rs.getString("nip_perawat_anestesi"),
                        rs.getString("selesai_operasi"),
                        rs.getString("data_subjektif"),
                        rs.getString("kesadaran"),
                        rs.getString("td"),
                        rs.getString("respi"),
                        rs.getString("nadi"),
                        rs.getString("suhu"),
                        rs.getString("gcs_e"),
                        rs.getString("gcs_m"),
                        rs.getString("gcs_v"),
                        rs.getString("supinasi"),
                        rs.getString("litotomi"),
                        rs.getString("pronasi"),
                        rs.getString("trendelenburg"),
                        rs.getString("lateral_kanan_kiri"),
                        rs.getString("posisi_lainnya"),
                        rs.getString("alkohol_70"),
                        rs.getString("iodine_providone_10"),
                        rs.getString("bronouderm"),
                        rs.getString("area_operasi"),
                        rs.getString("ekstremitas_kanan"),
                        rs.getString("ekstremitas_kiri"),
                        rs.getString("perhidrol"),
                        rs.getString("nacl"),
                        rs.getString("aqua_steril"),
                        rs.getString("cuci_luka_lainnya"),
                        rs.getString("suction_pump"),
                        rs.getString("turniquet"),
                        rs.getString("bor_ortopedi"),
                        rs.getString("penggunaan_alat_lainnya"),
                        rs.getString("elestric_couter"),
                        rs.getString("bipolar"),
                        rs.getString("monopolar"),
                        rs.getString("negatif_plat_di"),
                        rs.getString("ngt"),
                        rs.getString("dc"),
                        rs.getString("irigasi"),
                        rs.getString("ett"),
                        rs.getString("epidural"),
                        rs.getString("armsling"),
                        rs.getString("gips"),
                        rs.getString("drain"),
                        rs.getString("ket_drain"),
                        rs.getString("drain_kanan"),
                        rs.getString("drain_kiri"),
                        rs.getString("infus"),
                        rs.getString("ket_infus"),
                        rs.getString("infus_kanan"),
                        rs.getString("infus_kiri"),
                        rs.getString("ya"),
                        rs.getString("tidak_berupa"),
                        rs.getString("wb"),
                        rs.getString("prc"),
                        rs.getString("sebanyak"),
                        rs.getString("ket_sebanyak1"),
                        rs.getString("ket_sebanyak2"),
                        rs.getString("kejadian"),
                        rs.getString("nip_perawat"),
                        rs.getString("nmPrwtOperasi")
                    });
                }
            } catch (Exception e) {
                System.out.println("rekammedis.RMAsesmenKeperawatanPerioperatif.tampil() : " + e);
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
        TtglAsesmen.setDate(new Date());
        Thari.setText(Sequel.hariINDONESIA("SELECT DATE_FORMAT('" + Valid.SetTgl(TtglAsesmen.getSelectedItem() + "") + "','%W')"));
        cmbRuangOK.setSelectedIndex(0);
        cmbJnsRuang.setSelectedIndex(0);
        cmbJnsRuang.setEnabled(false);
        cmbJnsAnestesi.setSelectedIndex(0);
        Tla.setText("");
        Tblok.setText("");
        TdxPreOperasi.setText("");
        TdxPascaOperasi.setText("");
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        TnipDokterOperator.setText("-");
        TnmDokterOperator.setText("-");
        TnipAsisten.setText("-");
        TnmAsisten.setText("-");
        Tinstrumen.setText("");
        Tonloop.setText("");
        TnipDokterAnestesi.setText("-");
        TnmDokterAnestesi.setText("-");
        TnipPerawatAnestesi.setText("-");
        TnmPerawatAnestesi.setText("-");
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        TdataSubyektif.setText("");
        cmbKesadaran.setSelectedIndex(0);
        Ttensi.setText("");
        Tsuhu.setText("");
        Tnadi.setText("");
        Trespi.setText("");
        TgcsE.setText("");
        TgcsM.setText("");
        TgcsV.setText("");
        chkSupin.setSelected(false);
        chkLitotomi.setSelected(false);
        chkPronasi.setSelected(false);
        chkTrendelen.setSelected(false);
        chkLateral.setSelected(false);
        TlainPosisi.setText("");
        chkAlkohol.setSelected(false);
        chkIodine.setSelected(false);
        chkBrono.setSelected(false);
        Tarea.setText("");
        chkEksKanan.setSelected(false);
        chkEksKiri.setSelected(false);
        chkPerhid.setSelected(false);
        chkNacl.setSelected(false);
        chkAqua.setSelected(false);
        TlainCuci.setText("");
        chkSuction.setSelected(false);
        chkTurni.setSelected(false);
        chkBor.setSelected(false);
        TlainPenggunaan.setText("");
        chkElek.setSelected(false);
        chkBipo.setSelected(false);
        chkMono.setSelected(false);
        Tnegatif.setText("");
        chkNgt.setSelected(false);
        chkDc.setSelected(false);
        chkIrigasi.setSelected(false);
        chkEtt.setSelected(false);
        chkEpid.setSelected(false);
        chkArm.setSelected(false);
        chkGips.setSelected(false);
        
        chkDrain.setSelected(false);
        Tdrain.setText("");
        Tdrain.setEnabled(false);
        chkDrainKanan.setSelected(false);
        chkDrainKiri.setSelected(false);
        chkDrainKanan.setEnabled(false);
        chkDrainKiri.setEnabled(false);
        
        chkInfus.setSelected(false);
        Tinfus.setText("");
        Tinfus.setEnabled(false);
        chkInfusKanan.setSelected(false);
        chkInfusKiri.setSelected(false);
        chkInfusKanan.setEnabled(false);
        chkInfusKiri.setEnabled(false);
        
        chkYa.setSelected(false);
        chkTidak.setSelected(false);
        chkWb.setSelected(false);
        chkPrc.setSelected(false);
        chkSebanyak.setSelected(false);
        Tsebanyak1.setText("");
        Tsebanyak2.setText("");
        Tsebanyak1.setEnabled(false);
        Tsebanyak2.setEnabled(false);
        
        cmbJnsHitungan.setSelectedIndex(0);
        TlainJenis.setText("");
        TlainJenis.setEnabled(false);
        Tawal.setText("");
        Ttambahan.setText("");
        Takhir.setText("");
        Tketerangan.setText("");
        Valid.tabelKosong(tabMode1);
        Tkejadian.setText("");
        TnipPerawatOperasi.setText("-");
        TnmPerawatOperasi.setText("-");
        chkSaya.setSelected(false);
    }

    private void getData() {
        emptVariabel();
        if (tbAsesmen.getSelectedRow() != -1) {            
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
            TNoRM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 2).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 3).toString());
            Tjk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 4).toString());
            TrgRawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 6).toString());
            Thari.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString());
            Valid.SetTgl(TtglAsesmen, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString());
            cmbRuangOK.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString());
            cmbJnsRuang.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString());
            cmbJnsAnestesi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString());
            Tla.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString());
            Tblok.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString());
            TdxPreOperasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString());
            TdxPascaOperasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString());
            cmbJam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString().substring(6, 8));
            TnipDokterOperator.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString());
            TnmDokterOperator.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString());
            TnipAsisten.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString());
            TnmAsisten.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString());
            Tinstrumen.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString());
            Tonloop.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString());
            TnipDokterAnestesi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString());
            TnmDokterAnestesi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString());
            TnipPerawatAnestesi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString());
            TnmPerawatAnestesi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString());
            cmbJam1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString().substring(6, 8));
            TdataSubyektif.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString());
            cmbKesadaran.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString());
            Ttensi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString());
            Tsuhu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString());
            Tnadi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString());
            Trespi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString());
            TgcsE.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString());
            TgcsM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString());
            TgcsV.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString());
            supin = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString();
            litotomi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString();
            pronasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString();
            trendel = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString();
            lateral = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString();
            TlainPosisi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString());
            alkohol = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString();
            iodin = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString();
            brono = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString();
            Tarea.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString());
            eksKanan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString();
            eksKiri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString();
            perhid = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString();
            nacl = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString();
            aqua = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString();
            TlainCuci.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString());
            suction = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 62).toString();
            turniq = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString();
            bor = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 64).toString();
            TlainPenggunaan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString());
            elek = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 66).toString();
            bipo = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString();
            mono = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString();
            Tnegatif.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString());
            ngt = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString();
            dc = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString();
            irigasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString();
            ett = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString();
            epid = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString();
            arm = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString();
            gip = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString();
            drain = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString();
            Tdrain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString());
            draKanan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 79).toString();
            draKiri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 80).toString();
            infus = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 81).toString();
            Tinfus.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 82).toString());
            infKanan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 83).toString();
            infKiri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 84).toString();
            ya = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 85).toString();
            tidak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 86).toString();
            wb = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 87).toString();
            prc = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 88).toString();
            sebanyak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 89).toString();
            Tsebanyak1.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 90).toString());
            Tsebanyak2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 91).toString());
            Tkejadian.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 92).toString());
            TnipPerawatOperasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 93).toString());
            TnmPerawatOperasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString());
            dataCek();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getkegiatan_operasi());
        BtnGanti.setEnabled(akses.getkegiatan_operasi());
        BtnHapus.setEnabled(akses.getkegiatan_operasi());
        BtnTambah.setEnabled(akses.getkegiatan_operasi());
        BtnHapusJns.setEnabled(akses.getkegiatan_operasi());
    }
    
    private void cekData() {
        if (chkSupin.isSelected() == true) {
            supin = "ya";
        } else {
            supin = "tidak";
        }
        
        if (chkLitotomi.isSelected() == true) {
            litotomi = "ya";
        } else {
            litotomi = "tidak";
        }
        
        if (chkPronasi.isSelected() == true) {
            pronasi = "ya";
        } else {
            pronasi = "tidak";
        }
        
        if (chkTrendelen.isSelected() == true) {
            trendel = "ya";
        } else {
            trendel = "tidak";
        }
        
        if (chkLateral.isSelected() == true) {
            lateral = "ya";
        } else {
            lateral = "tidak";
        }
        
        if (chkAlkohol.isSelected() == true) {
            alkohol = "ya";
        } else {
            alkohol = "tidak";
        }
        
        if (chkIodine.isSelected() == true) {
            iodin = "ya";
        } else {
            iodin = "tidak";
        }
        
        if (chkBrono.isSelected() == true) {
            brono = "ya";
        } else {
            brono = "tidak";
        }
        
        if (chkEksKanan.isSelected() == true) {
            eksKanan = "ya";
        } else {
            eksKanan = "tidak";
        }
        
        if (chkEksKiri.isSelected() == true) {
            eksKiri = "ya";
        } else {
            eksKiri = "tidak";
        }
        
        if (chkPerhid.isSelected() == true) {
            perhid = "ya";
        } else {
            perhid = "tidak";
        }
        
        if (chkNacl.isSelected() == true) {
            nacl = "ya";
        } else {
            nacl = "tidak";
        }
        
        if (chkAqua.isSelected() == true) {
            aqua = "ya";
        } else {
            aqua = "tidak";
        }
        
        if (chkSuction.isSelected() == true) {
            suction = "ya";
        } else {
            suction = "tidak";
        }
        
        if (chkTurni.isSelected() == true) {
            turniq = "ya";
        } else {
            turniq = "tidak";
        }
        
        if (chkBor.isSelected() == true) {
            bor = "ya";
        } else {
            bor = "tidak";
        }
        
        if (chkElek.isSelected() == true) {
            elek = "ya";
        } else {
            elek = "tidak";
        }
        
        if (chkBipo.isSelected() == true) {
            bipo = "ya";
        } else {
            bipo = "tidak";
        }
        
        if (chkMono.isSelected() == true) {
            mono = "ya";
        } else {
            mono = "tidak";
        }
        
        if (chkNgt.isSelected() == true) {
            ngt = "ya";
        } else {
            ngt = "tidak";
        }
        
        if (chkDc.isSelected() == true) {
            dc = "ya";
        } else {
            dc = "tidak";
        }
        
        if (chkIrigasi.isSelected() == true) {
            irigasi = "ya";
        } else {
            irigasi = "tidak";
        }
        
        if (chkEtt.isSelected() == true) {
            ett = "ya";
        } else {
            ett = "tidak";
        }
        
        if (chkEpid.isSelected() == true) {
            epid = "ya";
        } else {
            epid = "tidak";
        }
        
        if (chkArm.isSelected() == true) {
            arm = "ya";
        } else {
            arm = "tidak";
        }
        
        if (chkGips.isSelected() == true) {
            gip = "ya";
        } else {
            gip = "tidak";
        }
        
        if (chkDrain.isSelected() == true) {
            drain = "ya";
        } else {
            drain = "tidak";
        }
        
        if (chkDrainKanan.isSelected() == true) {
            draKanan = "ya";
        } else {
            draKanan = "tidak";
        }
        
        if (chkDrainKiri.isSelected() == true) {
            draKiri = "ya";
        } else {
            draKiri = "tidak";
        }
        
        if (chkInfus.isSelected() == true) {
            infus = "ya";
        } else {
            infus = "tidak";
        }
        
        if (chkInfusKanan.isSelected() == true) {
            infKanan = "ya";
        } else {
            infKanan = "tidak";
        }
        
        if (chkInfusKiri.isSelected() == true) {
            infKiri = "ya";
        } else {
            infKiri = "tidak";
        }
        
        if (chkYa.isSelected() == true) {
            ya = "ya";
        } else {
            ya = "tidak";
        }
        
        if (chkTidak.isSelected() == true) {
            tidak = "ya";
        } else {
            tidak = "tidak";
        }
        
        if (chkWb.isSelected() == true) {
            wb = "ya";
        } else {
            wb = "tidak";
        }
        
        if (chkPrc.isSelected() == true) {
            prc = "ya";
        } else {
            prc = "tidak";
        }
        
        if (chkSebanyak.isSelected() == true) {
            sebanyak = "ya";
        } else {
            sebanyak = "tidak";
        }
    }
    
    private void dataCek() {
        if (cmbRuangOK.getSelectedIndex() == 0) {
            cmbJnsRuang.setEnabled(false);
        } else {
            cmbJnsRuang.setEnabled(true);
        }
        
        if (supin.equals("ya")) {
            chkSupin.setSelected(true);
        } else {
            chkSupin.setSelected(false);
        }
        
        if (litotomi.equals("ya")) {
            chkLitotomi.setSelected(true);
        } else {
            chkLitotomi.setSelected(false);
        }
        
        if (pronasi.equals("ya")) {
            chkPronasi.setSelected(true);
        } else {
            chkPronasi.setSelected(false);
        }
        
        if (trendel.equals("ya")) {
            chkTrendelen.setSelected(true);
        } else {
            chkTrendelen.setSelected(false);
        }
        
        if (lateral.equals("ya")) {
            chkLateral.setSelected(true);
        } else {
            chkLateral.setSelected(false);
        }
        
        if (alkohol.equals("ya")) {
            chkAlkohol.setSelected(true);
        } else {
            chkAlkohol.setSelected(false);
        }
        
        if (iodin.equals("ya")) {
            chkIodine.setSelected(true);
        } else {
            chkIodine.setSelected(false);
        }
        
        if (brono.equals("ya")) {
            chkBrono.setSelected(true);
        } else {
            chkBrono.setSelected(false);
        }
        
        if (eksKanan.equals("ya")) {
            chkEksKanan.setSelected(true);
        } else {
            chkEksKanan.setSelected(false);
        }
        
        if (eksKiri.equals("ya")) {
            chkEksKiri.setSelected(true);
        } else {
            chkEksKiri.setSelected(false);
        }
        
        if (perhid.equals("ya")) {
            chkPerhid.setSelected(true);
        } else {
            chkPerhid.setSelected(false);
        }
        
        if (nacl.equals("ya")) {
            chkNacl.setSelected(true);
        } else {
            chkNacl.setSelected(false);
        }
        
        if (aqua.equals("ya")) {
            chkAqua.setSelected(true);
        } else {
            chkAqua.setSelected(false);
        }
        
        if (suction.equals("ya")) {
            chkSuction.setSelected(true);
        } else {
            chkSuction.setSelected(false);
        }
        
        if (turniq.equals("ya")) {
            chkTurni.setSelected(true);
        } else {
            chkTurni.setSelected(false);
        }
        
        if (bor.equals("ya")) {
            chkBor.setSelected(true);
        } else {
            chkBor.setSelected(false);
        }
        
        if (elek.equals("ya")) {
            chkElek.setSelected(true);
        } else {
            chkElek.setSelected(false);
        }
        
        if (bipo.equals("ya")) {
            chkBipo.setSelected(true);
        } else {
            chkBipo.setSelected(false);
        }
        
        if (mono.equals("ya")) {
            chkMono.setSelected(true);
        } else {
            chkMono.setSelected(false);
        }
        
        if (ngt.equals("ya")) {
            chkNgt.setSelected(true);
        } else {
            chkNgt.setSelected(false);
        }
        
        if (dc.equals("ya")) {
            chkDc.setSelected(true);
        } else {
            chkDc.setSelected(false);
        }
        
        if (irigasi.equals("ya")) {
            chkIrigasi.setSelected(true);
        } else {
            chkIrigasi.setSelected(false);
        }
        
        if (ett.equals("ya")) {
            chkEtt.setSelected(true);
        } else {
            chkEtt.setSelected(false);
        }
        
        if (epid.equals("ya")) {
            chkEpid.setSelected(true);
        } else {
            chkEpid.setSelected(false);
        }
        
        if (arm.equals("ya")) {
            chkArm.setSelected(true);
        } else {
            chkArm.setSelected(false);
        }
        
        if (gip.equals("ya")) {
            chkGips.setSelected(true);
        } else {
            chkGips.setSelected(false);
        }
        
        if (drain.equals("ya")) {
            chkDrain.setSelected(true);
            Tdrain.setEnabled(true);
            chkDrainKanan.setEnabled(true);
            chkDrainKiri.setEnabled(true);
        } else {
            chkDrain.setSelected(false);
            Tdrain.setEnabled(false);
            chkDrainKanan.setEnabled(false);
            chkDrainKiri.setEnabled(false);
        }
        
        if (draKanan.equals("ya")) {
            chkDrainKanan.setSelected(true);
        } else {
            chkDrainKanan.setSelected(false);
        }
        
        if (draKiri.equals("ya")) {
            chkDrainKiri.setSelected(true);
        } else {
            chkDrainKiri.setSelected(false);
        }
        
        if (infus.equals("ya")) {
            chkInfus.setSelected(true);
            Tinfus.setEnabled(true);
            chkInfusKanan.setEnabled(true);
            chkInfusKiri.setEnabled(true);
        } else {
            chkInfus.setSelected(false);
            Tinfus.setEnabled(false);
            chkInfusKanan.setEnabled(false);
            chkInfusKiri.setEnabled(false);
        }
        
        if (infKanan.equals("ya")) {
            chkInfusKanan.setSelected(true);
        } else {
            chkInfusKanan.setSelected(false);
        }
        
        if (infKiri.equals("ya")) {
            chkInfusKiri.setSelected(true);
        } else {
            chkInfusKiri.setSelected(false);
        }
        
        if (ya.equals("ya")) {
            chkYa.setSelected(true);
        } else {
            chkYa.setSelected(false);
        }
        
        if (tidak.equals("ya")) {
            chkTidak.setSelected(true);
        } else {
            chkTidak.setSelected(false);
        }
        
        if (wb.equals("ya")) {
            chkWb.setSelected(true);
        } else {
            chkWb.setSelected(false);
        }
        
        if (prc.equals("ya")) {
            chkPrc.setSelected(true);
        } else {
            chkPrc.setSelected(false);
        }
        
        if (sebanyak.equals("ya")) {
            chkSebanyak.setSelected(true);
            Tsebanyak1.setEnabled(true);
            Tsebanyak2.setEnabled(true);
        } else {
            chkSebanyak.setSelected(false);
            Tsebanyak1.setEnabled(false);
            Tsebanyak2.setEnabled(false);
        }
        
        //data hitungan selama operasi
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("select * from hitungan_asesmen_keperawatan_perioperatif where "
                    + "no_rawat='" + TNoRw.getText().trim() + "' and waktu_simpan_asesmen='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString() + "' "
                    + "order by waktu_simpan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        rs1.getString("jenis"),
                        rs1.getString("awal"),
                        rs1.getString("tambahan"),
                        rs1.getString("akhir"),
                        rs1.getString("keterangan"),
                        rs1.getString("no_rawat"),
                        rs1.getString("waktu_simpan")
                    });
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
    
    private void emptVariabel() {
        supin = "";
        litotomi = "";
        pronasi = "";
        trendel = "";
        lateral = "";
        alkohol = "";
        iodin = "";
        brono = "";
        eksKanan = "";
        eksKiri = "";
        perhid = "";
        nacl = "";
        aqua = "";
        suction = "";
        turniq = "";
        bor = "";
        elek = "";
        bipo = "";
        mono = "";
        ngt = "";
        dc = "";
        irigasi = "";
        ett = "";
        epid = "";
        arm = "";
        gip = "";
        drain = "";
        draKanan = "";
        draKiri = "";
        infus = "";
        infKanan = "";
        infKiri = "";
        ya = "";
        tidak = "";
        wb = "";
        prc = "";
        sebanyak = "";
    }
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode2);
        try {
            if (pilihan == 6) {
                pps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_keperawatan_perioperatif pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.data_subjektif<>'' and p.no_rkm_medis like ? OR "
                        + "pa.data_subjektif<>'' and p.nm_pasien like ? OR "
                        + "pa.data_subjektif<>'' and pa.data_subjektif like ? ORDER BY pa.tgl_asesmen desc limit 100");
            } else if (pilihan == 7) {
                pps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from asesmen_keperawatan_perioperatif pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.kejadian<>'' and p.no_rkm_medis like ? OR "
                        + "pa.kejadian<>'' and p.nm_pasien like ? OR "
                        + "pa.kejadian<>'' and pa.kejadian like ? ORDER BY pa.tgl_asesmen desc limit 100");
            } 
            try {
                if (pilihan == 6) {
                    pps1.setString(1, "%" + TCari1.getText() + "%");
                    pps1.setString(2, "%" + TCari1.getText() + "%");
                    pps1.setString(3, "%" + TCari1.getText() + "%");
                    rrs1 = pps1.executeQuery();
                    while (rrs1.next()) {
                        tabMode2.addRow(new String[]{
                            rrs1.getString("no_rkm_medis"),
                            rrs1.getString("nm_pasien"),
                            rrs1.getString("data_subjektif")
                        });
                    }
                } else if (pilihan == 7) {
                    pps2.setString(1, "%" + TCari1.getText() + "%");
                    pps2.setString(2, "%" + TCari1.getText() + "%");
                    pps2.setString(3, "%" + TCari1.getText() + "%");
                    rrs2 = pps2.executeQuery();
                    while (rrs2.next()) {
                        tabMode2.addRow(new String[]{
                            rrs2.getString("no_rkm_medis"),
                            rrs2.getString("nm_pasien"),
                            rrs2.getString("kejadian")
                        });
                    }
                } 
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rrs1 != null) {
                    rrs1.close();
                } else if (rrs2 != null) {
                    rrs2.close();
                } 

                if (pps1 != null) {
                    pps1.close();
                } else if (pps2 != null) {
                    pps2.close();
                } 
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void copas() {
        if (pilihan == 6) {
            TdataSubyektif.setText(Ttemplate.getText());
        } else if (pilihan == 7) {
            Tkejadian.setText(Ttemplate.getText());
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TrgRawat.setText(ruangan);
        
        if (akses.getadmin() == true) {
            TnipPerawatOperasi.setText("-");
            TnmPerawatOperasi.setText("-");
        } else {
            TnipPerawatOperasi.setText(akses.getkode());
            TnmPerawatOperasi.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipPerawatOperasi.getText() + "'"));
        }
    }
}
