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
import javax.sound.sampled.TargetDataLine;
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
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;
import org.vosk.Model;
import org.vosk.Recognizer;
import javax.sound.sampled.*;
import javax.swing.SwingUtilities;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import javax.sound.sampled.*;

/**
 *
 * @author dosen
 */
public class RMLaporanOperasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private int i = 0, x = 0, pilihDokter = 0, pilihPetugas = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private TargetDataLine microphone;
    private boolean running = false;
    private TargetDataLine whisperMic;
    private boolean whisperRecording = false;
    private File fileRekamWhisper;
    private String nipDrOperator = "", nipAsisten = "", nipInstrumen = "", nipOnloop = "", nipDrAnes = "", nipPrwtAnes = "",
            khusus = "", besar = "", sedang = "", kecil = "", elektif = "", darurat = "", odc = "", bersih = "", konta = "", kotor = "",
            jml1 = "", jml2 = "", jml3 = "", jmlLain = "", singin = "", time = "", singot = "", selesai = "", urutData = "", urutanKe = "", wktSimpan = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMLaporanOperasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Dokter Operator", "Asisten", "Instrumen", "Onloop", "Dokter Anestesi", "Perawat Anestesi",
            "diag_pra_bedah", "diag_pasca_bedah", "tindakan_operasi", "jns_ops_khusus", "jns_ops_besar", "jns_ops_sedang", "jns_ops_kecil", "urg_ops_elektif", "urg_ops_darurat",
            "urg_ops_odc", "mcm_ops_bersih", "mcm_ops_kontaminasi", "mcm_ops_kotor", "jml_1", "jml_2", "jml_3", "jml_lainya", "ket_jml_lainya", "implan", "ket_implan_ada",
            "nip_dr_operator", "nip_asisten", "nip_instrumen", "nip_onloop", "nip_dr_anastesi", "nip_perawat_anastesi", "tgl_operasi", "cek_sign_in", "cek_time_out", "cek_sign_out",
            "cek_selesai", "sign_in", "time_out", "sign_out", "selesai", "catatan_deskripsi", "instruksi_post_ops", "jml_perdarahan", "transfusi", "jns_transfusi", "jml_transfusi",
            "jaringan", "jns_jaringan", "pemeriksaan_pa", "komplikasi", "tgl_laporan", "jam_laporan", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbLaporanOps.setModel(tabMode);
        tbLaporanOps.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbLaporanOps.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 59; i++) {
            TableColumn column = tbLaporanOps.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setPreferredWidth(220);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setPreferredWidth(220);
            } else if (i == 10) {
                column.setPreferredWidth(220);
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
            }
        }
        tbLaporanOps.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new String[]{"no_rawat", "urutan", "Pukul", "TD (Sistole)", "TD (Diastole)",
            "Nadi", "RR", "Suhu", "SpO2", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbTTV.setModel(tabMode1);
        tbTTV.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTTV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbTTV.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(60);
            } else if (i == 6) {
                column.setPreferredWidth(60);
            } else if (i == 7) {
                column.setPreferredWidth(60);
            } else if (i == 8) {
                column.setPreferredWidth(60);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbTTV.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbTTV.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTTV.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTTV.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbTTV.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbTTV.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbTTV.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbTTV.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        
        TketJml.setDocument(new batasInput((int) 100).getKata(TketJml));
        TketImplan.setDocument(new batasInput((int) 200).getKata(TketImplan));        
        Tsistol.setDocument(new batasInput((int) 5).getKata(Tsistol));
        Tdistol.setDocument(new batasInput((int) 5).getKata(Tdistol));
        Tnadi.setDocument(new batasInput((int) 5).getKata(Tnadi));
        Trr.setDocument(new batasInput((int) 5).getKata(Trr));
        Tsuhu.setDocument(new batasInput((int) 5).getKata(Tsuhu));
        Tspo.setDocument(new batasInput((int) 5).getKata(Tspo));
        TjmlPerdarahan.setDocument(new batasInput((int) 10).getKata(TjmlPerdarahan));
        TjmlTrans.setDocument(new batasInput((int) 10).getKata(TjmlTrans));
        TjnsJaringan.setDocument(new batasInput((int) 200).getKata(TjnsJaringan));
        Tkomplikasi.setDocument(new batasInput((int) 200).getKata(Tkomplikasi));        
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
                if (akses.getform().equals("RMLaporanOperasi")) {
                    if (pilihDokter == 1) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nipDrOperator  = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                            TnmDrOperator.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDrOperator.requestFocus();
                        }                        
                    } else if (pilihDokter == 2) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nipDrAnes = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                            TnmDrAnestesi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDrAnestesi.requestFocus();
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
                if (akses.getform().equals("RMLaporanOperasi")) {
                    if (pilihPetugas == 1) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            nipAsisten = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                            TnmAsisten.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnAsisten.requestFocus();
                        }
                    } else if (pilihPetugas == 2) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            nipInstrumen = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                            TnmInstrumen.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnInstrumen.requestFocus();
                        }
                    } else if (pilihPetugas == 3) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            nipOnloop = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                            TnmOnloop.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnOnloop.requestFocus();
                        }
                    } else if (pilihPetugas == 4) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            nipPrwtAnes = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                            TnmPrwtAnestesi.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnPrwtAnestesi.requestFocus();
                        }
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
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel86 = new widget.Label();
        jLabel64 = new widget.Label();
        TpraBedah = new widget.TextBox();
        jLabel65 = new widget.Label();
        TpascaBedah = new widget.TextBox();
        jLabel87 = new widget.Label();
        scrollPane11 = new widget.ScrollPane();
        Ttindakan = new widget.TextArea();
        jLabel66 = new widget.Label();
        chkKhusus = new widget.CekBox();
        chkBesar = new widget.CekBox();
        chkSedang = new widget.CekBox();
        chkKecil = new widget.CekBox();
        jLabel67 = new widget.Label();
        chkElektif = new widget.CekBox();
        chkDarurat = new widget.CekBox();
        chkOdc = new widget.CekBox();
        jLabel68 = new widget.Label();
        chkBersih = new widget.CekBox();
        chkKontaminasi = new widget.CekBox();
        chkKotor = new widget.CekBox();
        jLabel69 = new widget.Label();
        chkJml1 = new widget.CekBox();
        chkJml2 = new widget.CekBox();
        chkJml3 = new widget.CekBox();
        chkJmlLain = new widget.CekBox();
        TketJml = new widget.TextBox();
        jLabel70 = new widget.Label();
        TnmDrOperator = new widget.TextBox();
        jLabel71 = new widget.Label();
        TnmAsisten = new widget.TextBox();
        jLabel72 = new widget.Label();
        TnmInstrumen = new widget.TextBox();
        jLabel73 = new widget.Label();
        TnmOnloop = new widget.TextBox();
        jLabel74 = new widget.Label();
        TnmDrAnestesi = new widget.TextBox();
        jLabel75 = new widget.Label();
        TnmPrwtAnestesi = new widget.TextBox();
        BtnDrOperator = new widget.Button();
        BtnAsisten = new widget.Button();
        BtnInstrumen = new widget.Button();
        BtnOnloop = new widget.Button();
        BtnDrAnestesi = new widget.Button();
        BtnPrwtAnestesi = new widget.Button();
        jLabel76 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel275 = new widget.Label();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        jLabel276 = new widget.Label();
        cmbJam3 = new widget.ComboBox();
        cmbMnt3 = new widget.ComboBox();
        cmbDtk3 = new widget.ComboBox();
        jLabel277 = new widget.Label();
        cmbJam4 = new widget.ComboBox();
        cmbMnt4 = new widget.ComboBox();
        cmbDtk4 = new widget.ComboBox();
        jLabel278 = new widget.Label();
        scrollPane12 = new widget.ScrollPane();
        TCttnOperasi = new widget.TextArea();
        chkCekSignIn = new widget.CekBox();
        chkCekTime = new widget.CekBox();
        chkCekSignOut = new widget.CekBox();
        chkCekSelesai = new widget.CekBox();
        TtglOperasi = new widget.Tanggal();
        jLabel77 = new widget.Label();
        TketImplan = new widget.TextBox();
        cmbImplan = new widget.ComboBox();
        jLabel89 = new widget.Label();
        jLabel78 = new widget.Label();
        cmbJam5 = new widget.ComboBox();
        cmbMnt5 = new widget.ComboBox();
        cmbDtk5 = new widget.ComboBox();
        jLabel279 = new widget.Label();
        jLabel79 = new widget.Label();
        jLabel280 = new widget.Label();
        Tsistol = new widget.TextBox();
        jLabel281 = new widget.Label();
        Tdistol = new widget.TextBox();
        jLabel282 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel283 = new widget.Label();
        Trr = new widget.TextBox();
        jLabel284 = new widget.Label();
        jLabel285 = new widget.Label();
        Tspo = new widget.TextBox();
        Tsuhu = new widget.TextBox();
        Scroll2 = new widget.ScrollPane();
        tbTTV = new widget.Table();
        BtnBaruTtv = new widget.Button();
        BtnTambahTtv = new widget.Button();
        BtnHapusTtv = new widget.Button();
        BtnGantiTtv = new widget.Button();
        scrollPane13 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();
        jLabel80 = new widget.Label();
        TjmlPerdarahan = new widget.TextBox();
        jLabel81 = new widget.Label();
        cmbTransfusi = new widget.ComboBox();
        jLabel82 = new widget.Label();
        cmbJnsTransfusi = new widget.ComboBox();
        jLabel83 = new widget.Label();
        TjmlTrans = new widget.TextBox();
        jLabel84 = new widget.Label();
        jLabel85 = new widget.Label();
        cmbJaringan = new widget.ComboBox();
        jLabel88 = new widget.Label();
        TjnsJaringan = new widget.TextBox();
        jLabel90 = new widget.Label();
        cmbPemeriksaan = new widget.ComboBox();
        jLabel91 = new widget.Label();
        Tkomplikasi = new widget.TextBox();
        jLabel92 = new widget.Label();
        TtglLaporan = new widget.Tanggal();
        jLabel93 = new widget.Label();
        cmbJam6 = new widget.ComboBox();
        cmbMnt6 = new widget.ComboBox();
        cmbDtk6 = new widget.ComboBox();
        jLabel286 = new widget.Label();
        BtnMicCatatan = new widget.Button();
        PanelInput1 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbLaporanOps = new widget.Table();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Laporan Operasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        panelGlass9.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(600, 402));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1962));
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

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("DIAGNOSIS :");
        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 66, 140, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Pra Bedah :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 94, 140, 23);

        TpraBedah.setForeground(new java.awt.Color(0, 0, 0));
        TpraBedah.setName("TpraBedah"); // NOI18N
        TpraBedah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpraBedahKeyPressed(evt);
            }
        });
        FormInput.add(TpraBedah);
        TpraBedah.setBounds(145, 94, 615, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Pasca Bedah :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 122, 140, 23);

        TpascaBedah.setForeground(new java.awt.Color(0, 0, 0));
        TpascaBedah.setName("TpascaBedah"); // NOI18N
        TpascaBedah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpascaBedahKeyPressed(evt);
            }
        });
        FormInput.add(TpascaBedah);
        TpascaBedah.setBounds(145, 122, 615, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("TINDAKAN OPERASI :");
        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 150, 140, 23);

        scrollPane11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane11.setName("scrollPane11"); // NOI18N

        Ttindakan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Ttindakan.setColumns(20);
        Ttindakan.setRows(5);
        Ttindakan.setName("Ttindakan"); // NOI18N
        Ttindakan.setPreferredSize(new java.awt.Dimension(162, 2000));
        Ttindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtindakanKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(Ttindakan);

        FormInput.add(scrollPane11);
        scrollPane11.setBounds(145, 150, 615, 110);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Jenis Operasi :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 267, 140, 23);

        chkKhusus.setBackground(new java.awt.Color(255, 255, 250));
        chkKhusus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKhusus.setForeground(new java.awt.Color(0, 0, 0));
        chkKhusus.setText("Khusus");
        chkKhusus.setBorderPainted(true);
        chkKhusus.setBorderPaintedFlat(true);
        chkKhusus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKhusus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKhusus.setName("chkKhusus"); // NOI18N
        chkKhusus.setOpaque(false);
        chkKhusus.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKhusus);
        chkKhusus.setBounds(145, 267, 70, 23);

        chkBesar.setBackground(new java.awt.Color(255, 255, 250));
        chkBesar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBesar.setForeground(new java.awt.Color(0, 0, 0));
        chkBesar.setText("Besar");
        chkBesar.setBorderPainted(true);
        chkBesar.setBorderPaintedFlat(true);
        chkBesar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBesar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBesar.setName("chkBesar"); // NOI18N
        chkBesar.setOpaque(false);
        chkBesar.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBesar);
        chkBesar.setBounds(225, 267, 60, 23);

        chkSedang.setBackground(new java.awt.Color(255, 255, 250));
        chkSedang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSedang.setForeground(new java.awt.Color(0, 0, 0));
        chkSedang.setText("Sedang");
        chkSedang.setBorderPainted(true);
        chkSedang.setBorderPaintedFlat(true);
        chkSedang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSedang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSedang.setName("chkSedang"); // NOI18N
        chkSedang.setOpaque(false);
        chkSedang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSedang);
        chkSedang.setBounds(295, 267, 70, 23);

        chkKecil.setBackground(new java.awt.Color(255, 255, 250));
        chkKecil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKecil.setForeground(new java.awt.Color(0, 0, 0));
        chkKecil.setText("Kecil");
        chkKecil.setBorderPainted(true);
        chkKecil.setBorderPaintedFlat(true);
        chkKecil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKecil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKecil.setName("chkKecil"); // NOI18N
        chkKecil.setOpaque(false);
        chkKecil.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKecil);
        chkKecil.setBounds(375, 267, 60, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Urgensi Operasi :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 295, 140, 23);

        chkElektif.setBackground(new java.awt.Color(255, 255, 250));
        chkElektif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkElektif.setForeground(new java.awt.Color(0, 0, 0));
        chkElektif.setText("Elektif");
        chkElektif.setBorderPainted(true);
        chkElektif.setBorderPaintedFlat(true);
        chkElektif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkElektif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkElektif.setName("chkElektif"); // NOI18N
        chkElektif.setOpaque(false);
        chkElektif.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkElektif);
        chkElektif.setBounds(145, 295, 70, 23);

        chkDarurat.setBackground(new java.awt.Color(255, 255, 250));
        chkDarurat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDarurat.setForeground(new java.awt.Color(0, 0, 0));
        chkDarurat.setText("Darurat");
        chkDarurat.setBorderPainted(true);
        chkDarurat.setBorderPaintedFlat(true);
        chkDarurat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDarurat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDarurat.setName("chkDarurat"); // NOI18N
        chkDarurat.setOpaque(false);
        chkDarurat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDarurat);
        chkDarurat.setBounds(225, 295, 75, 23);

        chkOdc.setBackground(new java.awt.Color(255, 255, 250));
        chkOdc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkOdc.setForeground(new java.awt.Color(0, 0, 0));
        chkOdc.setText("One Day Care");
        chkOdc.setBorderPainted(true);
        chkOdc.setBorderPaintedFlat(true);
        chkOdc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkOdc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkOdc.setName("chkOdc"); // NOI18N
        chkOdc.setOpaque(false);
        chkOdc.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkOdc);
        chkOdc.setBounds(310, 295, 105, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Macam Operasi :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 323, 140, 23);

        chkBersih.setBackground(new java.awt.Color(255, 255, 250));
        chkBersih.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBersih.setForeground(new java.awt.Color(0, 0, 0));
        chkBersih.setText("Bersih");
        chkBersih.setBorderPainted(true);
        chkBersih.setBorderPaintedFlat(true);
        chkBersih.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBersih.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBersih.setName("chkBersih"); // NOI18N
        chkBersih.setOpaque(false);
        chkBersih.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBersih);
        chkBersih.setBounds(145, 323, 70, 23);

        chkKontaminasi.setBackground(new java.awt.Color(255, 255, 250));
        chkKontaminasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKontaminasi.setForeground(new java.awt.Color(0, 0, 0));
        chkKontaminasi.setText("Kontaminasi");
        chkKontaminasi.setBorderPainted(true);
        chkKontaminasi.setBorderPaintedFlat(true);
        chkKontaminasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKontaminasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKontaminasi.setName("chkKontaminasi"); // NOI18N
        chkKontaminasi.setOpaque(false);
        chkKontaminasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKontaminasi);
        chkKontaminasi.setBounds(225, 323, 90, 23);

        chkKotor.setBackground(new java.awt.Color(255, 255, 250));
        chkKotor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKotor.setForeground(new java.awt.Color(0, 0, 0));
        chkKotor.setText("Kotor / Infeksi");
        chkKotor.setBorderPainted(true);
        chkKotor.setBorderPaintedFlat(true);
        chkKotor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKotor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKotor.setName("chkKotor"); // NOI18N
        chkKotor.setOpaque(false);
        chkKotor.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKotor);
        chkKotor.setBounds(330, 323, 105, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Jumlah :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 351, 140, 23);

        chkJml1.setBackground(new java.awt.Color(255, 255, 250));
        chkJml1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJml1.setForeground(new java.awt.Color(0, 0, 0));
        chkJml1.setText("1");
        chkJml1.setBorderPainted(true);
        chkJml1.setBorderPaintedFlat(true);
        chkJml1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJml1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJml1.setName("chkJml1"); // NOI18N
        chkJml1.setOpaque(false);
        chkJml1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkJml1);
        chkJml1.setBounds(145, 351, 40, 23);

        chkJml2.setBackground(new java.awt.Color(255, 255, 250));
        chkJml2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJml2.setForeground(new java.awt.Color(0, 0, 0));
        chkJml2.setText("2");
        chkJml2.setBorderPainted(true);
        chkJml2.setBorderPaintedFlat(true);
        chkJml2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJml2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJml2.setName("chkJml2"); // NOI18N
        chkJml2.setOpaque(false);
        chkJml2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkJml2);
        chkJml2.setBounds(195, 351, 40, 23);

        chkJml3.setBackground(new java.awt.Color(255, 255, 250));
        chkJml3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJml3.setForeground(new java.awt.Color(0, 0, 0));
        chkJml3.setText("3");
        chkJml3.setBorderPainted(true);
        chkJml3.setBorderPaintedFlat(true);
        chkJml3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJml3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJml3.setName("chkJml3"); // NOI18N
        chkJml3.setOpaque(false);
        chkJml3.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkJml3);
        chkJml3.setBounds(244, 351, 40, 23);

        chkJmlLain.setBackground(new java.awt.Color(255, 255, 250));
        chkJmlLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJmlLain.setForeground(new java.awt.Color(0, 0, 0));
        chkJmlLain.setText(":");
        chkJmlLain.setBorderPainted(true);
        chkJmlLain.setBorderPaintedFlat(true);
        chkJmlLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJmlLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJmlLain.setName("chkJmlLain"); // NOI18N
        chkJmlLain.setOpaque(false);
        chkJmlLain.setPreferredSize(new java.awt.Dimension(175, 23));
        chkJmlLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJmlLainActionPerformed(evt);
            }
        });
        FormInput.add(chkJmlLain);
        chkJmlLain.setBounds(294, 351, 30, 23);

        TketJml.setForeground(new java.awt.Color(0, 0, 0));
        TketJml.setName("TketJml"); // NOI18N
        TketJml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketJmlKeyPressed(evt);
            }
        });
        FormInput.add(TketJml);
        TketJml.setBounds(325, 351, 435, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Dokter Operator :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 407, 140, 23);

        TnmDrOperator.setEditable(false);
        TnmDrOperator.setForeground(new java.awt.Color(0, 0, 0));
        TnmDrOperator.setName("TnmDrOperator"); // NOI18N
        FormInput.add(TnmDrOperator);
        TnmDrOperator.setBounds(145, 407, 410, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Asisten :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 435, 140, 23);

        TnmAsisten.setEditable(false);
        TnmAsisten.setForeground(new java.awt.Color(0, 0, 0));
        TnmAsisten.setName("TnmAsisten"); // NOI18N
        FormInput.add(TnmAsisten);
        TnmAsisten.setBounds(145, 435, 410, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Instrumen :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 463, 140, 23);

        TnmInstrumen.setEditable(false);
        TnmInstrumen.setForeground(new java.awt.Color(0, 0, 0));
        TnmInstrumen.setName("TnmInstrumen"); // NOI18N
        FormInput.add(TnmInstrumen);
        TnmInstrumen.setBounds(145, 463, 410, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Onloop :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 491, 140, 23);

        TnmOnloop.setEditable(false);
        TnmOnloop.setForeground(new java.awt.Color(0, 0, 0));
        TnmOnloop.setName("TnmOnloop"); // NOI18N
        FormInput.add(TnmOnloop);
        TnmOnloop.setBounds(145, 491, 410, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Dokter Anestesi :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 519, 140, 23);

        TnmDrAnestesi.setEditable(false);
        TnmDrAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        TnmDrAnestesi.setName("TnmDrAnestesi"); // NOI18N
        FormInput.add(TnmDrAnestesi);
        TnmDrAnestesi.setBounds(145, 519, 410, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Perawat Anestesi :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 547, 140, 23);

        TnmPrwtAnestesi.setEditable(false);
        TnmPrwtAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        TnmPrwtAnestesi.setName("TnmPrwtAnestesi"); // NOI18N
        FormInput.add(TnmPrwtAnestesi);
        TnmPrwtAnestesi.setBounds(145, 547, 410, 23);

        BtnDrOperator.setForeground(new java.awt.Color(0, 0, 0));
        BtnDrOperator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDrOperator.setMnemonic('1');
        BtnDrOperator.setToolTipText("Alt+1");
        BtnDrOperator.setName("BtnDrOperator"); // NOI18N
        BtnDrOperator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDrOperatorActionPerformed(evt);
            }
        });
        FormInput.add(BtnDrOperator);
        BtnDrOperator.setBounds(560, 407, 28, 23);

        BtnAsisten.setForeground(new java.awt.Color(0, 0, 0));
        BtnAsisten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAsisten.setMnemonic('1');
        BtnAsisten.setToolTipText("Alt+1");
        BtnAsisten.setName("BtnAsisten"); // NOI18N
        BtnAsisten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsistenActionPerformed(evt);
            }
        });
        FormInput.add(BtnAsisten);
        BtnAsisten.setBounds(560, 435, 28, 23);

        BtnInstrumen.setForeground(new java.awt.Color(0, 0, 0));
        BtnInstrumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnInstrumen.setMnemonic('1');
        BtnInstrumen.setToolTipText("Alt+1");
        BtnInstrumen.setName("BtnInstrumen"); // NOI18N
        BtnInstrumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInstrumenActionPerformed(evt);
            }
        });
        FormInput.add(BtnInstrumen);
        BtnInstrumen.setBounds(560, 463, 28, 23);

        BtnOnloop.setForeground(new java.awt.Color(0, 0, 0));
        BtnOnloop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnOnloop.setMnemonic('1');
        BtnOnloop.setToolTipText("Alt+1");
        BtnOnloop.setName("BtnOnloop"); // NOI18N
        BtnOnloop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnOnloopActionPerformed(evt);
            }
        });
        FormInput.add(BtnOnloop);
        BtnOnloop.setBounds(560, 491, 28, 23);

        BtnDrAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        BtnDrAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDrAnestesi.setMnemonic('1');
        BtnDrAnestesi.setToolTipText("Alt+1");
        BtnDrAnestesi.setName("BtnDrAnestesi"); // NOI18N
        BtnDrAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDrAnestesiActionPerformed(evt);
            }
        });
        FormInput.add(BtnDrAnestesi);
        BtnDrAnestesi.setBounds(560, 519, 28, 23);

        BtnPrwtAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrwtAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPrwtAnestesi.setMnemonic('1');
        BtnPrwtAnestesi.setToolTipText("Alt+1");
        BtnPrwtAnestesi.setName("BtnPrwtAnestesi"); // NOI18N
        BtnPrwtAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrwtAnestesiActionPerformed(evt);
            }
        });
        FormInput.add(BtnPrwtAnestesi);
        BtnPrwtAnestesi.setBounds(560, 547, 28, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Waktu Operasi :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 575, 140, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(233, 575, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(286, 575, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(338, 575, 45, 23);

        jLabel275.setForeground(new java.awt.Color(0, 0, 0));
        jLabel275.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel275.setText("Wita         Tgl. Operasi :");
        jLabel275.setName("jLabel275"); // NOI18N
        FormInput.add(jLabel275);
        jLabel275.setBounds(390, 575, 120, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(233, 603, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(286, 603, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(338, 603, 45, 23);

        jLabel276.setForeground(new java.awt.Color(0, 0, 0));
        jLabel276.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel276.setText("Wita");
        jLabel276.setName("jLabel276"); // NOI18N
        FormInput.add(jLabel276);
        jLabel276.setBounds(390, 603, 50, 23);

        cmbJam3.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam3.setName("cmbJam3"); // NOI18N
        cmbJam3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam3MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam3);
        cmbJam3.setBounds(233, 631, 45, 23);

        cmbMnt3.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt3.setName("cmbMnt3"); // NOI18N
        cmbMnt3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt3MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt3);
        cmbMnt3.setBounds(286, 631, 45, 23);

        cmbDtk3.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk3.setName("cmbDtk3"); // NOI18N
        cmbDtk3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk3MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk3);
        cmbDtk3.setBounds(338, 631, 45, 23);

        jLabel277.setForeground(new java.awt.Color(0, 0, 0));
        jLabel277.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel277.setText("Wita");
        jLabel277.setName("jLabel277"); // NOI18N
        FormInput.add(jLabel277);
        jLabel277.setBounds(390, 631, 50, 23);

        cmbJam4.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam4.setName("cmbJam4"); // NOI18N
        cmbJam4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam4MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam4);
        cmbJam4.setBounds(233, 659, 45, 23);

        cmbMnt4.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt4.setName("cmbMnt4"); // NOI18N
        cmbMnt4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt4MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt4);
        cmbMnt4.setBounds(286, 659, 45, 23);

        cmbDtk4.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk4.setName("cmbDtk4"); // NOI18N
        cmbDtk4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk4MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk4);
        cmbDtk4.setBounds(338, 659, 45, 23);

        jLabel278.setForeground(new java.awt.Color(0, 0, 0));
        jLabel278.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel278.setText("Wita");
        jLabel278.setName("jLabel278"); // NOI18N
        FormInput.add(jLabel278);
        jLabel278.setBounds(390, 659, 50, 23);

        scrollPane12.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), " Catatan Mengenai Jalannya Operasi, Kesulitan-kesulitan yang dialami selama operasi berlangsung : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        scrollPane12.setName("scrollPane12"); // NOI18N

        TCttnOperasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TCttnOperasi.setColumns(20);
        TCttnOperasi.setRows(5);
        TCttnOperasi.setName("TCttnOperasi"); // NOI18N
        TCttnOperasi.setPreferredSize(new java.awt.Dimension(162, 20000));
        scrollPane12.setViewportView(TCttnOperasi);

        FormInput.add(scrollPane12);
        scrollPane12.setBounds(40, 687, 720, 430);

        chkCekSignIn.setBackground(new java.awt.Color(255, 255, 250));
        chkCekSignIn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCekSignIn.setForeground(new java.awt.Color(0, 0, 0));
        chkCekSignIn.setText("Sign In :");
        chkCekSignIn.setBorderPainted(true);
        chkCekSignIn.setBorderPaintedFlat(true);
        chkCekSignIn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkCekSignIn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCekSignIn.setName("chkCekSignIn"); // NOI18N
        chkCekSignIn.setOpaque(false);
        chkCekSignIn.setPreferredSize(new java.awt.Dimension(175, 23));
        chkCekSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCekSignInActionPerformed(evt);
            }
        });
        FormInput.add(chkCekSignIn);
        chkCekSignIn.setBounds(145, 575, 80, 23);

        chkCekTime.setBackground(new java.awt.Color(255, 255, 250));
        chkCekTime.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCekTime.setForeground(new java.awt.Color(0, 0, 0));
        chkCekTime.setText("Time Out :");
        chkCekTime.setBorderPainted(true);
        chkCekTime.setBorderPaintedFlat(true);
        chkCekTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkCekTime.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCekTime.setName("chkCekTime"); // NOI18N
        chkCekTime.setOpaque(false);
        chkCekTime.setPreferredSize(new java.awt.Dimension(175, 23));
        chkCekTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCekTimeActionPerformed(evt);
            }
        });
        FormInput.add(chkCekTime);
        chkCekTime.setBounds(145, 603, 80, 23);

        chkCekSignOut.setBackground(new java.awt.Color(255, 255, 250));
        chkCekSignOut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCekSignOut.setForeground(new java.awt.Color(0, 0, 0));
        chkCekSignOut.setText("Sign Out :");
        chkCekSignOut.setBorderPainted(true);
        chkCekSignOut.setBorderPaintedFlat(true);
        chkCekSignOut.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkCekSignOut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCekSignOut.setName("chkCekSignOut"); // NOI18N
        chkCekSignOut.setOpaque(false);
        chkCekSignOut.setPreferredSize(new java.awt.Dimension(175, 23));
        chkCekSignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCekSignOutActionPerformed(evt);
            }
        });
        FormInput.add(chkCekSignOut);
        chkCekSignOut.setBounds(145, 631, 80, 23);

        chkCekSelesai.setBackground(new java.awt.Color(255, 255, 250));
        chkCekSelesai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCekSelesai.setForeground(new java.awt.Color(0, 0, 0));
        chkCekSelesai.setText("Selesai :");
        chkCekSelesai.setBorderPainted(true);
        chkCekSelesai.setBorderPaintedFlat(true);
        chkCekSelesai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkCekSelesai.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCekSelesai.setName("chkCekSelesai"); // NOI18N
        chkCekSelesai.setOpaque(false);
        chkCekSelesai.setPreferredSize(new java.awt.Dimension(175, 23));
        chkCekSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCekSelesaiActionPerformed(evt);
            }
        });
        FormInput.add(chkCekSelesai);
        chkCekSelesai.setBounds(145, 659, 80, 23);

        TtglOperasi.setEditable(false);
        TtglOperasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-06-2026" }));
        TtglOperasi.setDisplayFormat("dd-MM-yyyy");
        TtglOperasi.setName("TtglOperasi"); // NOI18N
        TtglOperasi.setOpaque(false);
        TtglOperasi.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglOperasi);
        TtglOperasi.setBounds(512, 575, 90, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Implant :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 379, 140, 23);

        TketImplan.setForeground(new java.awt.Color(0, 0, 0));
        TketImplan.setName("TketImplan"); // NOI18N
        TketImplan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketImplanKeyPressed(evt);
            }
        });
        FormInput.add(TketImplan);
        TketImplan.setBounds(259, 379, 500, 23);

        cmbImplan.setBackground(new java.awt.Color(245, 253, 240));
        cmbImplan.setForeground(new java.awt.Color(0, 0, 0));
        cmbImplan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak Ada", "Ada, Sebutkan" }));
        cmbImplan.setLightWeightPopupEnabled(false);
        cmbImplan.setName("cmbImplan"); // NOI18N
        cmbImplan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbImplanActionPerformed(evt);
            }
        });
        FormInput.add(cmbImplan);
        cmbImplan.setBounds(145, 379, 105, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("OBSERVASI TANDA VITAL :");
        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 1122, 180, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Pukul :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(0, 1150, 140, 23);

        cmbJam5.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam5.setName("cmbJam5"); // NOI18N
        cmbJam5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam5MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam5);
        cmbJam5.setBounds(145, 1150, 45, 23);

        cmbMnt5.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt5.setName("cmbMnt5"); // NOI18N
        cmbMnt5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt5MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt5);
        cmbMnt5.setBounds(198, 1150, 45, 23);

        cmbDtk5.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk5.setName("cmbDtk5"); // NOI18N
        cmbDtk5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk5MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk5);
        cmbDtk5.setBounds(250, 1150, 45, 23);

        jLabel279.setForeground(new java.awt.Color(0, 0, 0));
        jLabel279.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel279.setText("Wita");
        jLabel279.setName("jLabel279"); // NOI18N
        FormInput.add(jLabel279);
        jLabel279.setBounds(302, 1150, 50, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Tekanan Darah :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 1178, 140, 23);

        jLabel280.setForeground(new java.awt.Color(0, 0, 0));
        jLabel280.setText("Sistole :");
        jLabel280.setName("jLabel280"); // NOI18N
        FormInput.add(jLabel280);
        jLabel280.setBounds(145, 1178, 50, 23);

        Tsistol.setForeground(new java.awt.Color(0, 0, 0));
        Tsistol.setName("Tsistol"); // NOI18N
        Tsistol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsistolKeyPressed(evt);
            }
        });
        FormInput.add(Tsistol);
        Tsistol.setBounds(200, 1178, 60, 23);

        jLabel281.setForeground(new java.awt.Color(0, 0, 0));
        jLabel281.setText("Diastole :");
        jLabel281.setName("jLabel281"); // NOI18N
        FormInput.add(jLabel281);
        jLabel281.setBounds(125, 1206, 70, 23);

        Tdistol.setForeground(new java.awt.Color(0, 0, 0));
        Tdistol.setName("Tdistol"); // NOI18N
        Tdistol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdistolKeyPressed(evt);
            }
        });
        FormInput.add(Tdistol);
        Tdistol.setBounds(200, 1206, 60, 23);

        jLabel282.setForeground(new java.awt.Color(0, 0, 0));
        jLabel282.setText("Nadi :");
        jLabel282.setName("jLabel282"); // NOI18N
        FormInput.add(jLabel282);
        jLabel282.setBounds(265, 1178, 60, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(330, 1178, 60, 23);

        jLabel283.setForeground(new java.awt.Color(0, 0, 0));
        jLabel283.setText("RR :");
        jLabel283.setName("jLabel283"); // NOI18N
        FormInput.add(jLabel283);
        jLabel283.setBounds(265, 1206, 60, 23);

        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        FormInput.add(Trr);
        Trr.setBounds(330, 1206, 60, 23);

        jLabel284.setForeground(new java.awt.Color(0, 0, 0));
        jLabel284.setText("Suhu :");
        jLabel284.setName("jLabel284"); // NOI18N
        FormInput.add(jLabel284);
        jLabel284.setBounds(390, 1178, 60, 23);

        jLabel285.setForeground(new java.awt.Color(0, 0, 0));
        jLabel285.setText("SpO2 :");
        jLabel285.setName("jLabel285"); // NOI18N
        FormInput.add(jLabel285);
        jLabel285.setBounds(390, 1206, 60, 23);

        Tspo.setForeground(new java.awt.Color(0, 0, 0));
        Tspo.setName("Tspo"); // NOI18N
        Tspo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TspoKeyPressed(evt);
            }
        });
        FormInput.add(Tspo);
        Tspo.setBounds(456, 1206, 60, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        FormInput.add(Tsuhu);
        Tsuhu.setBounds(456, 1178, 60, 23);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbTTV.setName("tbTTV"); // NOI18N
        tbTTV.getTableHeader().setReorderingAllowed(false);
        tbTTV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTTVMouseClicked(evt);
            }
        });
        tbTTV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTTVKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbTTV);

        FormInput.add(Scroll2);
        Scroll2.setBounds(145, 1240, 500, 150);

        BtnBaruTtv.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaruTtv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaruTtv.setText("Baru");
        BtnBaruTtv.setToolTipText("Data DJJ Baru");
        BtnBaruTtv.setName("BtnBaruTtv"); // NOI18N
        BtnBaruTtv.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnBaruTtv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruTtvActionPerformed(evt);
            }
        });
        FormInput.add(BtnBaruTtv);
        BtnBaruTtv.setBounds(660, 1240, 90, 30);

        BtnTambahTtv.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahTtv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahTtv.setText("Tambah");
        BtnTambahTtv.setToolTipText("Tambah Data Air Ketuban/Mulase");
        BtnTambahTtv.setName("BtnTambahTtv"); // NOI18N
        BtnTambahTtv.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahTtv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahTtvActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahTtv);
        BtnTambahTtv.setBounds(660, 1279, 90, 30);

        BtnHapusTtv.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusTtv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusTtv.setText("Hapus");
        BtnHapusTtv.setToolTipText("Hapus Air Ketuban/Mulase");
        BtnHapusTtv.setName("BtnHapusTtv"); // NOI18N
        BtnHapusTtv.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusTtv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusTtvActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusTtv);
        BtnHapusTtv.setBounds(660, 1318, 90, 30);

        BtnGantiTtv.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiTtv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiTtv.setText("Ganti");
        BtnGantiTtv.setToolTipText("Ganti Air Ketuban/Mulase");
        BtnGantiTtv.setName("BtnGantiTtv"); // NOI18N
        BtnGantiTtv.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiTtv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiTtvActionPerformed(evt);
            }
        });
        FormInput.add(BtnGantiTtv);
        BtnGantiTtv.setBounds(660, 1357, 90, 30);

        scrollPane13.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), " Instruksi Post Operasi : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        scrollPane13.setName("scrollPane13"); // NOI18N

        Tinstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinstruksi.setColumns(20);
        Tinstruksi.setRows(5);
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(162, 20000));
        scrollPane13.setViewportView(Tinstruksi);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(40, 1396, 720, 430);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Jumlah Perdarahan :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(0, 1830, 140, 23);

        TjmlPerdarahan.setForeground(new java.awt.Color(0, 0, 0));
        TjmlPerdarahan.setName("TjmlPerdarahan"); // NOI18N
        TjmlPerdarahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjmlPerdarahanKeyPressed(evt);
            }
        });
        FormInput.add(TjmlPerdarahan);
        TjmlPerdarahan.setBounds(145, 1830, 70, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel81.setText("cc.       Transfusi :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(220, 1830, 90, 23);

        cmbTransfusi.setBackground(new java.awt.Color(245, 253, 240));
        cmbTransfusi.setForeground(new java.awt.Color(0, 0, 0));
        cmbTransfusi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbTransfusi.setLightWeightPopupEnabled(false);
        cmbTransfusi.setName("cmbTransfusi"); // NOI18N
        cmbTransfusi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTransfusiActionPerformed(evt);
            }
        });
        FormInput.add(cmbTransfusi);
        cmbTransfusi.setBounds(313, 1830, 60, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Jenis :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(375, 1830, 50, 23);

        cmbJnsTransfusi.setBackground(new java.awt.Color(245, 253, 240));
        cmbJnsTransfusi.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsTransfusi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "WB", "PRC" }));
        cmbJnsTransfusi.setLightWeightPopupEnabled(false);
        cmbJnsTransfusi.setName("cmbJnsTransfusi"); // NOI18N
        FormInput.add(cmbJnsTransfusi);
        cmbJnsTransfusi.setBounds(430, 1830, 55, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Jumlah :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(485, 1830, 55, 23);

        TjmlTrans.setForeground(new java.awt.Color(0, 0, 0));
        TjmlTrans.setName("TjmlTrans"); // NOI18N
        TjmlTrans.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjmlTransKeyPressed(evt);
            }
        });
        FormInput.add(TjmlTrans);
        TjmlTrans.setBounds(545, 1830, 70, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("Kolf");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(620, 1830, 40, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Jaringan :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(0, 1858, 140, 23);

        cmbJaringan.setBackground(new java.awt.Color(245, 253, 240));
        cmbJaringan.setForeground(new java.awt.Color(0, 0, 0));
        cmbJaringan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak" }));
        cmbJaringan.setLightWeightPopupEnabled(false);
        cmbJaringan.setName("cmbJaringan"); // NOI18N
        cmbJaringan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJaringanActionPerformed(evt);
            }
        });
        FormInput.add(cmbJaringan);
        cmbJaringan.setBounds(145, 1858, 60, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Jenis Jaringan :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(210, 1858, 90, 23);

        TjnsJaringan.setForeground(new java.awt.Color(0, 0, 0));
        TjnsJaringan.setName("TjnsJaringan"); // NOI18N
        TjnsJaringan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjnsJaringanKeyPressed(evt);
            }
        });
        FormInput.add(TjnsJaringan);
        TjnsJaringan.setBounds(304, 1858, 455, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Pemeriksaan PA :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(0, 1886, 140, 23);

        cmbPemeriksaan.setBackground(new java.awt.Color(245, 253, 240));
        cmbPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPemeriksaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPemeriksaan.setLightWeightPopupEnabled(false);
        cmbPemeriksaan.setName("cmbPemeriksaan"); // NOI18N
        FormInput.add(cmbPemeriksaan);
        cmbPemeriksaan.setBounds(145, 1886, 60, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Komplikasi :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(210, 1886, 90, 23);

        Tkomplikasi.setForeground(new java.awt.Color(0, 0, 0));
        Tkomplikasi.setName("Tkomplikasi"); // NOI18N
        Tkomplikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkomplikasiKeyPressed(evt);
            }
        });
        FormInput.add(Tkomplikasi);
        Tkomplikasi.setBounds(304, 1886, 455, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Tgl. Laporan Operasi :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(0, 1914, 140, 23);

        TtglLaporan.setEditable(false);
        TtglLaporan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-06-2026" }));
        TtglLaporan.setDisplayFormat("dd-MM-yyyy");
        TtglLaporan.setName("TtglLaporan"); // NOI18N
        TtglLaporan.setOpaque(false);
        TtglLaporan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglLaporan);
        TtglLaporan.setBounds(145, 1914, 90, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Jam :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(240, 1914, 60, 23);

        cmbJam6.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam6.setName("cmbJam6"); // NOI18N
        cmbJam6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam6MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam6);
        cmbJam6.setBounds(304, 1914, 45, 23);

        cmbMnt6.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt6.setName("cmbMnt6"); // NOI18N
        cmbMnt6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt6MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt6);
        cmbMnt6.setBounds(357, 1914, 45, 23);

        cmbDtk6.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk6.setName("cmbDtk6"); // NOI18N
        cmbDtk6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk6MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk6);
        cmbDtk6.setBounds(409, 1914, 45, 23);

        jLabel286.setForeground(new java.awt.Color(0, 0, 0));
        jLabel286.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel286.setText("Wita");
        jLabel286.setName("jLabel286"); // NOI18N
        FormInput.add(jLabel286);
        jLabel286.setBounds(461, 1914, 50, 23);

        BtnMicCatatan.setForeground(new java.awt.Color(0, 0, 0));
        BtnMicCatatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/mic_off.png"))); // NOI18N
        BtnMicCatatan.setText("MIC OFF");
        BtnMicCatatan.setName("BtnMicCatatan"); // NOI18N
        BtnMicCatatan.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnMicCatatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnMicCatatanMouseClicked(evt);
            }
        });
        FormInput.add(BtnMicCatatan);
        BtnMicCatatan.setBounds(650, 645, 110, 40);

        Scroll1.setViewportView(FormInput);

        panelGlass9.add(Scroll1);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Laporan Operasi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(700, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbLaporanOps.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbLaporanOps.setName("tbLaporanOps"); // NOI18N
        tbLaporanOps.getTableHeader().setReorderingAllowed(false);
        tbLaporanOps.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLaporanOpsMouseClicked(evt);
            }
        });
        tbLaporanOps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbLaporanOpsKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbLaporanOps);

        PanelInput1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 86));
        panelGlass11.setLayout(new java.awt.BorderLayout());

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Operasi :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-06-2026" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-06-2026" }));
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

        panelGlass9.add(PanelInput1);

        internalFrame1.add(panelGlass9, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            try {
                wktSimpan = Sequel.cariIsi("select now()");
                cekData();
                if (Sequel.menyimpantf("laporan_operasi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 50, new String[]{
                    TNoRw.getText(), TrgRawat.getText(), TpraBedah.getText(), TpascaBedah.getText(), Ttindakan.getText(), khusus, besar, sedang, kecil,
                    elektif, darurat, odc, bersih, konta, kotor, jml1, jml2, jml3, jmlLain, TketJml.getText(), cmbImplan.getSelectedItem().toString(),
                    TketImplan.getText(), nipDrOperator, nipAsisten, nipInstrumen, nipOnloop, nipDrAnes, nipPrwtAnes, Valid.SetTgl(TtglOperasi.getSelectedItem() + ""),
                    singin, time, singot, selesai, cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                    cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(),
                    cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem(),
                    cmbJam4.getSelectedItem() + ":" + cmbMnt4.getSelectedItem() + ":" + cmbDtk4.getSelectedItem(),
                    Valid.mysql_real_escape_stringERM(TCttnOperasi.getText()), Valid.mysql_real_escape_stringERM(Tinstruksi.getText()), TjmlPerdarahan.getText(),
                    cmbTransfusi.getSelectedItem().toString(), cmbJnsTransfusi.getSelectedItem().toString(), TjmlTrans.getText(), cmbJaringan.getSelectedItem().toString(),
                    TjnsJaringan.getText(), cmbPemeriksaan.getSelectedItem().toString(), Tkomplikasi.getText(), Valid.SetTgl(TtglLaporan.getSelectedItem() + ""),
                    cmbJam6.getSelectedItem() + ":" + cmbMnt6.getSelectedItem() + ":" + cmbDtk6.getSelectedItem(), wktSimpan
                }) == true) {

                    if (tbTTV.getRowCount() != 0) {
                        for (i = 0; i < tbTTV.getRowCount(); i++) {
                            Sequel.menyimpanIgnore("laporan_operasi_obs_ttv",
                                    "'" + tbTTV.getValueAt(i, 0).toString() + "','"
                                    + tbTTV.getValueAt(i, 1).toString() + "','"
                                    + tbTTV.getValueAt(i, 2).toString() + "','"
                                    + tbTTV.getValueAt(i, 3).toString() + "','"
                                    + tbTTV.getValueAt(i, 4).toString() + "','"
                                    + tbTTV.getValueAt(i, 5).toString() + "','"
                                    + tbTTV.getValueAt(i, 6).toString() + "','"
                                    + tbTTV.getValueAt(i, 7).toString() + "','"
                                    + tbTTV.getValueAt(i, 8).toString() + "','"
                                    + wktSimpan + "'", "Data Observasi TTV");
                        }
                    }

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Laporan Operasi", "Simpan");
                    TCari.setText(TNoRw.getText());
                    emptTeks();
                    tampil();
                }
            } catch (Exception e) {
                System.out.println("Simpan Laporan Operasi : " + e);
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
            if (tbLaporanOps.getSelectedRow() > -1) {
                try {
                    cekData();
                    if (Sequel.mengedittf("laporan_operasi", "waktu_simpan=?", "diag_pra_bedah=?, diag_pasca_bedah=?, tindakan_operasi=?, jns_ops_khusus=?, jns_ops_besar=?, "
                            + "jns_ops_sedang=?, jns_ops_kecil=?, urg_ops_elektif=?, urg_ops_darurat=?, urg_ops_odc=?, mcm_ops_bersih=?, mcm_ops_kontaminasi=?, mcm_ops_kotor=?, "
                            + "jml_1=?, jml_2=?, jml_3=?, jml_lainya=?, ket_jml_lainya=?, implan=?, ket_implan_ada=?, nip_dr_operator=?, nip_asisten=?, nip_instrumen=?, "
                            + "nip_onloop=?, nip_dr_anastesi=?, nip_perawat_anastesi=?, tgl_operasi=?, cek_sign_in=?, cek_time_out=?, cek_sign_out=?, cek_selesai=?, sign_in=?, "
                            + "time_out=?, sign_out=?, selesai=?, catatan_deskripsi=?, instruksi_post_ops=?, jml_perdarahan=?, transfusi=?, jns_transfusi=?, jml_transfusi=?, "
                            + "jaringan=?, jns_jaringan=?, pemeriksaan_pa=?, komplikasi=?, tgl_laporan=?, jam_laporan=?", 48, new String[]{
                                TpraBedah.getText(), TpascaBedah.getText(), Ttindakan.getText(), khusus, besar, sedang, kecil,
                                elektif, darurat, odc, bersih, konta, kotor, jml1, jml2, jml3, jmlLain, TketJml.getText(), cmbImplan.getSelectedItem().toString(),
                                TketImplan.getText(), nipDrOperator, nipAsisten, nipInstrumen, nipOnloop, nipDrAnes, nipPrwtAnes, Valid.SetTgl(TtglOperasi.getSelectedItem() + ""),
                                singin, time, singot, selesai, cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                                cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(),
                                cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem(),
                                cmbJam4.getSelectedItem() + ":" + cmbMnt4.getSelectedItem() + ":" + cmbDtk4.getSelectedItem(),
                                Valid.mysql_real_escape_stringERM(TCttnOperasi.getText()), Valid.mysql_real_escape_stringERM(Tinstruksi.getText()), TjmlPerdarahan.getText(),
                                cmbTransfusi.getSelectedItem().toString(), cmbJnsTransfusi.getSelectedItem().toString(), TjmlTrans.getText(), cmbJaringan.getSelectedItem().toString(),
                                TjnsJaringan.getText(), cmbPemeriksaan.getSelectedItem().toString(), Tkomplikasi.getText(), Valid.SetTgl(TtglLaporan.getSelectedItem() + ""),
                                cmbJam6.getSelectedItem() + ":" + cmbMnt6.getSelectedItem() + ":" + cmbDtk6.getSelectedItem(),
                                tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 58).toString()
                            }) == true) {

                        if (tbTTV.getRowCount() != 0) {
                            Sequel.queryu("delete from laporan_operasi_obs_ttv where waktu_simpan='" + tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 58).toString() + "'");
                            for (i = 0; i < tbTTV.getRowCount(); i++) {
                                Sequel.menyimpanIgnore("laporan_operasi_obs_ttv",
                                        "'" + tbTTV.getValueAt(i, 0).toString() + "','"
                                        + tbTTV.getValueAt(i, 1).toString() + "','"
                                        + tbTTV.getValueAt(i, 2).toString() + "','"
                                        + tbTTV.getValueAt(i, 3).toString() + "','"
                                        + tbTTV.getValueAt(i, 4).toString() + "','"
                                        + tbTTV.getValueAt(i, 5).toString() + "','"
                                        + tbTTV.getValueAt(i, 6).toString() + "','"
                                        + tbTTV.getValueAt(i, 7).toString() + "','"
                                        + tbTTV.getValueAt(i, 8).toString() + "','"
                                        + tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 58).toString() + "'", "Data Observasi TTV");
                            }
                        }
                        
                        Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Laporan Operasi", "Ganti");
                        TCari.setText(TNoRw.getText());
                        tampil();
                        emptTeks();
                    }
                } catch (Exception e) {
                    System.out.println("Ganti Laporan Operasi : " + e);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                tbLaporanOps.requestFocus();
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

    private void tbLaporanOpsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLaporanOpsMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbLaporanOpsMouseClicked

    private void tbLaporanOpsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbLaporanOpsKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbLaporanOpsKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbLaporanOps.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from laporan_operasi where waktu_simpan=?", 1, new String[]{
                    tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 58).toString()
                }) == true) {                    
                    Sequel.queryu("delete from laporan_operasi_obs_ttv where waktu_simpan='" + tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 58).toString() + "'");
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
            tbLaporanOps.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbLaporanOps.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
            param.put("prabedah", TpraBedah.getText());
            param.put("pasca", TpascaBedah.getText());
            param.put("tindakan", Ttindakan.getText());
            
            if (chkKhusus.isSelected() == true) {
                param.put("khusus", "V");
            } else {
                param.put("khusus", "");
            }
            
            if (chkBesar.isSelected() == true) {
                param.put("besar", "V");
            } else {
                param.put("besar", "");
            }
            
            if (chkSedang.isSelected() == true) {
                param.put("sedang", "V");
            } else {
                param.put("sedang", "");
            }
            
            if (chkKecil.isSelected() == true) {
                param.put("kecil", "V");
            } else {
                param.put("kecil", "");
            }
            
            if (chkElektif.isSelected() == true) {
                param.put("elektif", "V");
            } else {
                param.put("elektif", "");
            }
            
            if (chkDarurat.isSelected() == true) {
                param.put("darurat", "V");
            } else {
                param.put("darurat", "");
            }
            
            if (chkOdc.isSelected() == true) {
                param.put("odc", "V");
            } else {
                param.put("odc", "");
            }
            
            if (chkBersih.isSelected() == true) {
                param.put("bersih", "V");
            } else {
                param.put("bersih", "");
            }
            
            if (chkKontaminasi.isSelected() == true) {
                param.put("konta", "V");
            } else {
                param.put("konta", "");
            }
            
            if (chkKotor.isSelected() == true) {
                param.put("kotor", "V");
            } else {
                param.put("kotor", "");
            }
            
            if (chkJml1.isSelected() == true) {
                param.put("jml1", "V");
            } else {
                param.put("jml1", "");
            }
            
            if (chkJml2.isSelected() == true) {
                param.put("jml2", "V");
            } else {
                param.put("jml2", "");
            }
            
            if (chkJml3.isSelected() == true) {
                param.put("jml3", "V");
            } else {
                param.put("jml3", "");
            }
            
            if (chkJmlLain.isSelected() == true) {
                param.put("jmlLain", "V");
                if (TketJml.getText().equals("")) {
                    param.put("ketJmlLain", "....................");
                } else {
                    param.put("ketJmlLain", TketJml.getText());
                }             
            } else {
                param.put("jmlLain", "");
                param.put("ketJmlLain", "....................");
            }
            
            if (cmbImplan.getSelectedIndex() == 2) {
                if (TketImplan.getText().equals("")) {
                    param.put("implan", cmbImplan.getSelectedItem().toString() + " : ....................");
                } else {
                    param.put("implan", cmbImplan.getSelectedItem().toString() + " : " + TketImplan.getText());
                }
            } else {
                param.put("implan", cmbImplan.getSelectedItem().toString());
            }            
            
            param.put("dokterOpr", TnmDrOperator.getText());
            param.put("asisten", TnmAsisten.getText());
            param.put("instrumen", TnmInstrumen.getText());
            param.put("onloop", TnmOnloop.getText());
            param.put("dokterAnes", TnmDrAnestesi.getText());
            param.put("prwtAnes", TnmPrwtAnestesi.getText());
            param.put("tglOperasi", Valid.SetTglINDONESIA(Valid.SetTgl(TtglOperasi.getSelectedItem() + "")));
            
            if (chkCekSignIn.isSelected() == true) {
                param.put("jamSignIn", cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " Wita");
            } else {
                param.put("jamSignIn", "........");
            }
            
            if (chkCekTime.isSelected() == true) {
                param.put("jamTime", cmbJam2.getSelectedItem().toString() + ":" + cmbMnt2.getSelectedItem().toString() + " Wita");
            } else {
                param.put("jamTime", "........");
            }
            
            if (chkCekSignOut.isSelected() == true) {
                param.put("jamSignOut", cmbJam3.getSelectedItem().toString() + ":" + cmbMnt3.getSelectedItem().toString() + " Wita");
            } else {
                param.put("jamSignOut", "........");
            }
            
            if (chkCekSelesai.isSelected() == true) {
                param.put("jamSelesai", cmbJam4.getSelectedItem().toString() + ":" + cmbMnt4.getSelectedItem().toString() + " Wita");
            } else {
                param.put("jamSelesai", "........");
            }

            if (TCttnOperasi.getText().equals("")) {
                param.put("cttn", "");
            } else {
                param.put("cttn", TCttnOperasi.getText() + "\n");
            }
            
            if (Tinstruksi.getText().equals("")) {
                param.put("instruksi", "");
            } else {
                param.put("instruksi", Tinstruksi.getText() + "\n");
            }
            
            if (TjmlPerdarahan.getText().equals("")) {
                param.put("jmlPerdarahan", ".......");
            } else {
                param.put("jmlPerdarahan", TjmlPerdarahan.getText() + " cc.");
            }
            
            param.put("transfusi", cmbTransfusi.getSelectedItem().toString() + ", Jenis : " + cmbJnsTransfusi.getSelectedItem().toString());
            
            if (TjmlTrans.getText().equals("")) {
                param.put("jmlTransfusi", "....... Kolf");
            } else {
                param.put("jmlTransfusi", TjmlTrans.getText() + " Kolf");
            }
            
            if (TjnsJaringan.getText().equals("")) {
                param.put("jaringan", cmbJaringan.getSelectedItem().toString() + ", Jenis Jaringan : .......");
            } else {
                param.put("jaringan", cmbJaringan.getSelectedItem().toString() + ", Jenis Jaringan : " + TjnsJaringan.getText());
            }
            
            param.put("pemeriksaanPa", cmbPemeriksaan.getSelectedItem().toString());
            
            if (Tkomplikasi.getText().equals("")) {
                param.put("komplikasi", ".......");
            } else {
                param.put("komplikasi", Tkomplikasi.getText());
            }
            
            param.put("tglLaporan", "Martapura, " + Valid.SetTglINDONESIA(Valid.SetTgl(TtglLaporan.getSelectedItem() + ""))
                    + ", Jam " + cmbJam6.getSelectedItem().toString() + ":" + cmbMnt6.getSelectedItem().toString() + " Wita");
            
            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isi = "";
                if (nipDrOperator.equals("") || nipDrOperator.equals("-") || nipDrOperator.equals("--")) {
                    JOptionPane.showMessageDialog(rootPane, "Maaf, nama dokter operator harus diisi dulu,..");
                } else {
                    isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Laporan Operasi", TnmDrOperator.getText(),
                                    Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from laporan_operasi where "
                                            + "waktu_simpan='" + tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 58).toString() + "'"),
                                    Sequel.cariIsi("select time(waktu_simpan) from laporan_operasi where "
                                            + "waktu_simpan='" + tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 58).toString() + "'")) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                    Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                    Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Laporan Operasi", Sequel.cariFolderPrintTte());
                    param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                    param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));

                    Valid.MyReport("rptLaporanOperasi1Qr.jasper", "report", "::[ Laporan Operasi hal. 1 ]::",
                            "SELECT now() tanggal", param);
                    
                    //data observasi ttv
                    if (Sequel.cariInteger("select count(-1) from laporan_operasi_obs_ttv where waktu_simpan='" + tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 58).toString() + "'") > 0) {
                        Valid.MyReport("rptLaporanOperasi2Qr.jasper", "report", "::[ Laporan Operasi hal. 2 ]::",
                                "select no_rawat, urutan, TIME_FORMAT(pukul,'%H:%i:%s') pukul, td_sistole, td_diastole, nadi, rr, suhu, spo2, waktu_simpan "
                                + "from laporan_operasi_obs_ttv where waktu_simpan='" + tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 58).toString() + "' "
                                + "order by urutan", param);
                    } else {
                        Valid.MyReport("rptLaporanOperasi2Qr.jasper", "report", "::[ Laporan Operasi hal. 2 ]::",
                                "SELECT '-' no_rawat, '-' urutan, '-' pukul, '-' td_sistole, '-' td_diastole , '-' nadi, '-' rr, '-' suhu, '-' spo2, '-' waktu_simpan FROM dual "
                                + "WHERE NOT EXISTS (SELECT 1 FROM laporan_operasi_obs_ttv WHERE "
                                + "waktu_simpan='" + tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 58).toString() + "')", param);
                    }

                    emptTeks();
                    tampil();
                    Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                }
            } else {
                Valid.MyReport("rptLaporanOperasi1.jasper", "report", "::[ Laporan Operasi hal. 1 ]::",
                        "SELECT now() tanggal", param);

                //data observasi ttv
                if (Sequel.cariInteger("select count(-1) from laporan_operasi_obs_ttv where waktu_simpan='" + tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 58).toString() + "'") > 0) {
                    Valid.MyReport("rptLaporanOperasi2.jasper", "report", "::[ Laporan Operasi hal. 2 ]::",
                            "select * from laporan_operasi_obs_ttv where waktu_simpan='" + tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 58).toString() + "' order by urutan", param);
                } else {
                    Valid.MyReport("rptLaporanOperasi2.jasper", "report", "::[ Laporan Operasi hal. 2 ]::",
                            "SELECT '-' no_rawat, '-' urutan, '-' pukul, '-' td_sistole, '-' td_diastole , '-' nadi, '-' rr, '-' suhu, '-' spo2, '-' waktu_simpan FROM dual "
                            + "WHERE NOT EXISTS (SELECT 1 FROM laporan_operasi_obs_ttv WHERE "
                            + "waktu_simpan='" + tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 58).toString() + "')", param);
                }
                
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel..!!!!");
            tbLaporanOps.requestFocus();
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
        cekDaftarMicrophone();
    }//GEN-LAST:event_formWindowOpened

    private void TtindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            chkKhusus.requestFocus();
        }
    }//GEN-LAST:event_TtindakanKeyPressed

    private void BtnDrOperatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDrOperatorActionPerformed
        pilihDokter = 0;
        pilihDokter = 1;
        akses.setform("RMLaporanOperasi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDrOperatorActionPerformed

    private void BtnAsistenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsistenActionPerformed
        pilihPetugas = 0;
        pilihPetugas = 1;
        akses.setform("RMLaporanOperasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnAsistenActionPerformed

    private void BtnInstrumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInstrumenActionPerformed
        pilihPetugas = 0;
        pilihPetugas = 2;
        akses.setform("RMLaporanOperasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnInstrumenActionPerformed

    private void BtnOnloopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOnloopActionPerformed
        pilihPetugas = 0;
        pilihPetugas = 3;
        akses.setform("RMLaporanOperasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnOnloopActionPerformed

    private void BtnDrAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDrAnestesiActionPerformed
        pilihDokter = 0;
        pilihDokter = 2;
        akses.setform("RMLaporanOperasi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDrAnestesiActionPerformed

    private void BtnPrwtAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrwtAnestesiActionPerformed
        pilihPetugas = 0;
        pilihPetugas = 4;
        akses.setform("RMLaporanOperasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPrwtAnestesiActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void cmbJam2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam2MouseReleased
        AutoCompleteDecorator.decorate(cmbJam2);
    }//GEN-LAST:event_cmbJam2MouseReleased

    private void cmbMnt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt2MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt2);
    }//GEN-LAST:event_cmbMnt2MouseReleased

    private void cmbDtk2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk2);
    }//GEN-LAST:event_cmbDtk2MouseReleased

    private void cmbJam3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam3MouseReleased
        AutoCompleteDecorator.decorate(cmbJam3);
    }//GEN-LAST:event_cmbJam3MouseReleased

    private void cmbMnt3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt3MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt3);
    }//GEN-LAST:event_cmbMnt3MouseReleased

    private void cmbDtk3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk3MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk3);
    }//GEN-LAST:event_cmbDtk3MouseReleased

    private void cmbJam4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam4MouseReleased
        AutoCompleteDecorator.decorate(cmbJam4);
    }//GEN-LAST:event_cmbJam4MouseReleased

    private void cmbMnt4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt4MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt4);
    }//GEN-LAST:event_cmbMnt4MouseReleased

    private void cmbDtk4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk4MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk4);
    }//GEN-LAST:event_cmbDtk4MouseReleased

    private void TpraBedahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpraBedahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TpascaBedah.requestFocus();
        }
    }//GEN-LAST:event_TpraBedahKeyPressed

    private void TpascaBedahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpascaBedahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttindakan.requestFocus();
        }
    }//GEN-LAST:event_TpascaBedahKeyPressed

    private void TketJmlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketJmlKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbImplan.requestFocus();
        }
    }//GEN-LAST:event_TketJmlKeyPressed

    private void chkJmlLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJmlLainActionPerformed
        TketJml.setText("");
        if (chkJmlLain.isSelected() == true) {
            TketJml.setEnabled(true);
            TketJml.requestFocus();
        } else {
            TketJml.setEnabled(false);
        }
    }//GEN-LAST:event_chkJmlLainActionPerformed

    private void chkCekSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCekSignInActionPerformed
        if (chkCekSignIn.isSelected() == true) {
            cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtk1.setSelectedIndex(0);
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
            cmbJam1.requestFocus();
        } else {
            cmbJam1.setSelectedIndex(0);
            cmbMnt1.setSelectedIndex(0);
            cmbDtk1.setSelectedIndex(0);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
        }
    }//GEN-LAST:event_chkCekSignInActionPerformed

    private void chkCekTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCekTimeActionPerformed
        if (chkCekTime.isSelected() == true) {
            cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtk2.setSelectedIndex(0);
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
            cmbJam2.requestFocus();
        } else {
            cmbJam2.setSelectedIndex(0);
            cmbMnt2.setSelectedIndex(0);
            cmbDtk2.setSelectedIndex(0);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
        }
    }//GEN-LAST:event_chkCekTimeActionPerformed

    private void chkCekSignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCekSignOutActionPerformed
        if (chkCekSignOut.isSelected() == true) {
            cmbJam3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMnt3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtk3.setSelectedIndex(0);
            cmbJam3.setEnabled(true);
            cmbMnt3.setEnabled(true);
            cmbDtk3.setEnabled(true);
            cmbJam3.requestFocus();
        } else {
            cmbJam3.setSelectedIndex(0);
            cmbMnt3.setSelectedIndex(0);
            cmbDtk3.setSelectedIndex(0);
            cmbJam3.setEnabled(false);
            cmbMnt3.setEnabled(false);
            cmbDtk3.setEnabled(false);
        }
    }//GEN-LAST:event_chkCekSignOutActionPerformed

    private void chkCekSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCekSelesaiActionPerformed
        if (chkCekSelesai.isSelected() == true) {
            cmbJam4.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMnt4.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtk4.setSelectedIndex(0);
            cmbJam4.setEnabled(true);
            cmbMnt4.setEnabled(true);
            cmbDtk4.setEnabled(true);
            cmbJam4.requestFocus();
        } else {
            cmbJam4.setSelectedIndex(0);
            cmbMnt4.setSelectedIndex(0);
            cmbDtk4.setSelectedIndex(0);
            cmbJam4.setEnabled(false);
            cmbMnt4.setEnabled(false);
            cmbDtk4.setEnabled(false);
        }
    }//GEN-LAST:event_chkCekSelesaiActionPerformed

    private void TketImplanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketImplanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnDrOperator.requestFocus();
        }
    }//GEN-LAST:event_TketImplanKeyPressed

    private void cmbImplanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbImplanActionPerformed
        TketImplan.setText("");
        if (cmbImplan.getSelectedIndex() == 2) {
            TketImplan.setEnabled(true);
            TketImplan.requestFocus();
        } else {
            TketImplan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbImplanActionPerformed

    private void cmbJam5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam5MouseReleased
        AutoCompleteDecorator.decorate(cmbJam5);
    }//GEN-LAST:event_cmbJam5MouseReleased

    private void cmbMnt5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt5MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt5);
    }//GEN-LAST:event_cmbMnt5MouseReleased

    private void cmbDtk5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk5MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk5);
    }//GEN-LAST:event_cmbDtk5MouseReleased

    private void tbTTVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTTVMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataTtv();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTTVMouseClicked

    private void tbTTVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTTVKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataTtv();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTTVKeyPressed

    private void BtnBaruTtvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruTtvActionPerformed
        emptTeksTTV();
        urutkanDataTTV();
    }//GEN-LAST:event_BtnBaruTtvActionPerformed

    private void TsistolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsistolKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdistol.requestFocus();
        }
    }//GEN-LAST:event_TsistolKeyPressed

    private void TdistolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdistolKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnadi.requestFocus();
        }
    }//GEN-LAST:event_TdistolKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trr.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsuhu.requestFocus();
        }
    }//GEN-LAST:event_TrrKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tspo.requestFocus();
        }
    }//GEN-LAST:event_TsuhuKeyPressed

    private void BtnTambahTtvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahTtvActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            urutData = "1";
            if (tabMode1.getRowCount() > 0) {
                int max = 0;
                for (int i = 0; i < tabMode1.getRowCount(); i++) {
                    int nilai = Integer.parseInt(tabMode1.getValueAt(i, 1).toString());
                    if (nilai > max) {
                        max = nilai;
                    }
                }
                urutData = String.valueOf(max + 1);
            }

            tabMode1.addRow(new String[]{TNoRw.getText(), urutData, cmbJam5.getSelectedItem() + ":" + cmbMnt5.getSelectedItem() + ":" + cmbDtk5.getSelectedItem(),
                Tsistol.getText(), Tdistol.getText(), Tnadi.getText(), Trr.getText(), Tsuhu.getText(), Tspo.getText(), ""});
            BtnBaruTtvActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTambahTtvActionPerformed

    private void BtnHapusTtvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusTtvActionPerformed
        if (tbTTV.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data observasi tanda vital yang bisa dihapus..!!");
        } else {
            if (tbTTV.getSelectedRow() > -1) {
                int row = tbTTV.convertRowIndexToModel(tbTTV.getSelectedRow());
                tabMode1.removeRow(row);
                BtnBaruTtvActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel observasi tanda vital..!!");
                tbTTV.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusTtvActionPerformed

    private void BtnGantiTtvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiTtvActionPerformed
        if (tbTTV.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data observasi tanda vital yang bisa diganti..!!");
        } else {
            if (tbTTV.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else {
                    int row = tbTTV.convertRowIndexToModel(tbTTV.getSelectedRow());
                    tabMode1.setValueAt(TNoRw.getText(), row, 0);
                    tabMode1.setValueAt(urutanKe, row, 1);
                    tabMode1.setValueAt(cmbJam5.getSelectedItem() + ":" + cmbMnt5.getSelectedItem() + ":" + cmbDtk5.getSelectedItem(), row, 2);
                    tabMode1.setValueAt(Tsistol.getText(), row, 3);
                    tabMode1.setValueAt(Tdistol.getText(), row, 4);
                    tabMode1.setValueAt(Tnadi.getText(), row, 5);
                    tabMode1.setValueAt(Trr.getText(), row, 6);
                    tabMode1.setValueAt(Tsuhu.getText(), row, 7);
                    tabMode1.setValueAt(Tspo.getText(), row, 8);
                    tabMode1.setValueAt("", row, 9);

                    BtnBaruTtvActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel observasi tanda vital..!!");
                tbTTV.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiTtvActionPerformed

    private void TspoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TspoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnTambahTtv.requestFocus();
        }
    }//GEN-LAST:event_TspoKeyPressed

    private void cmbTransfusiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTransfusiActionPerformed
        cmbJnsTransfusi.setSelectedIndex(0);
        TjmlTrans.setText("");
        if (cmbTransfusi.getSelectedIndex() == 1) {
            cmbJnsTransfusi.setEnabled(true);
            TjmlTrans.setEnabled(true);
            cmbJnsTransfusi.requestFocus();
        } else {
            cmbJnsTransfusi.setEnabled(false);
            TjmlTrans.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTransfusiActionPerformed

    private void TjmlPerdarahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjmlPerdarahanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbTransfusi.requestFocus();
        }
    }//GEN-LAST:event_TjmlPerdarahanKeyPressed

    private void TjmlTransKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjmlTransKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJaringan.requestFocus();
        }
    }//GEN-LAST:event_TjmlTransKeyPressed

    private void cmbJaringanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJaringanActionPerformed
        TjnsJaringan.setText("");
        if (cmbJaringan.getSelectedIndex() == 1) {
            TjnsJaringan.setEnabled(true);
            TjnsJaringan.requestFocus();
        } else {
            TjnsJaringan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbJaringanActionPerformed

    private void TjnsJaringanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjnsJaringanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPemeriksaan.requestFocus();
        }
    }//GEN-LAST:event_TjnsJaringanKeyPressed

    private void TkomplikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkomplikasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglLaporan.requestFocus();
        }
    }//GEN-LAST:event_TkomplikasiKeyPressed

    private void cmbJam6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam6MouseReleased
        AutoCompleteDecorator.decorate(cmbJam6);
    }//GEN-LAST:event_cmbJam6MouseReleased

    private void cmbMnt6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt6MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt6);
    }//GEN-LAST:event_cmbMnt6MouseReleased

    private void cmbDtk6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk6MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk6);
    }//GEN-LAST:event_cmbDtk6MouseReleased

    private void BtnMicCatatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnMicCatatanMouseClicked
        if (BtnMicCatatan.getText().equals("MIC OFF")) {
            BtnMicCatatan.setText("MIC ON");
            BtnMicCatatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/mic_on.png")));
            // mulai rekam dan hasilnya masuk ke TCttnOperasi
//            micMulai(TCttnOperasi);
            whisperMulaiRekam();
        } else {
            BtnMicCatatan.setText("MIC OFF");
            BtnMicCatatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/mic_off.png")));
            // stop rekam
//            micStop();
            whisperStopDanTranskrip();
        }
    }//GEN-LAST:event_BtnMicCatatanMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMLaporanOperasi dialog = new RMLaporanOperasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAsisten;
    private widget.Button BtnBaruTtv;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDrAnestesi;
    private widget.Button BtnDrOperator;
    private widget.Button BtnGanti;
    private widget.Button BtnGantiTtv;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusTtv;
    private widget.Button BtnInstrumen;
    private widget.Button BtnKeluar;
    private widget.Button BtnMicCatatan;
    private widget.Button BtnOnloop;
    private widget.Button BtnPrint;
    private widget.Button BtnPrwtAnestesi;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambahTtv;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    public widget.TextBox TCari;
    private widget.TextArea TCttnOperasi;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tdistol;
    private widget.TextArea Tinstruksi;
    private widget.TextBox TjmlPerdarahan;
    private widget.TextBox TjmlTrans;
    private widget.TextBox TjnsJaringan;
    private widget.TextBox TketImplan;
    private widget.TextBox TketJml;
    private widget.TextBox Tkomplikasi;
    private widget.TextBox Tnadi;
    private widget.TextBox TnmAsisten;
    private widget.TextBox TnmDrAnestesi;
    private widget.TextBox TnmDrOperator;
    private widget.TextBox TnmInstrumen;
    private widget.TextBox TnmOnloop;
    private widget.TextBox TnmPrwtAnestesi;
    private widget.TextBox TpascaBedah;
    private widget.TextBox TpraBedah;
    private widget.TextBox TrgRawat;
    private widget.TextBox Trr;
    private widget.TextBox Tsistol;
    private widget.TextBox Tspo;
    private widget.TextBox Tsuhu;
    private widget.Tanggal TtglLaporan;
    private widget.Tanggal TtglOperasi;
    private widget.TextArea Ttindakan;
    public widget.CekBox chkBersih;
    public widget.CekBox chkBesar;
    public widget.CekBox chkCekSelesai;
    public widget.CekBox chkCekSignIn;
    public widget.CekBox chkCekSignOut;
    public widget.CekBox chkCekTime;
    public widget.CekBox chkDarurat;
    public widget.CekBox chkElektif;
    public widget.CekBox chkJml1;
    public widget.CekBox chkJml2;
    public widget.CekBox chkJml3;
    public widget.CekBox chkJmlLain;
    public widget.CekBox chkKecil;
    public widget.CekBox chkKhusus;
    public widget.CekBox chkKontaminasi;
    public widget.CekBox chkKotor;
    public widget.CekBox chkOdc;
    public widget.CekBox chkSedang;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbDtk3;
    private widget.ComboBox cmbDtk4;
    private widget.ComboBox cmbDtk5;
    private widget.ComboBox cmbDtk6;
    private widget.ComboBox cmbImplan;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbJam3;
    private widget.ComboBox cmbJam4;
    private widget.ComboBox cmbJam5;
    private widget.ComboBox cmbJam6;
    private widget.ComboBox cmbJaringan;
    private widget.ComboBox cmbJnsTransfusi;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbMnt3;
    private widget.ComboBox cmbMnt4;
    private widget.ComboBox cmbMnt5;
    private widget.ComboBox cmbMnt6;
    private widget.ComboBox cmbPemeriksaan;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbTransfusi;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel275;
    private widget.Label jLabel276;
    private widget.Label jLabel277;
    private widget.Label jLabel278;
    private widget.Label jLabel279;
    private widget.Label jLabel280;
    private widget.Label jLabel281;
    private widget.Label jLabel282;
    private widget.Label jLabel283;
    private widget.Label jLabel284;
    private widget.Label jLabel285;
    private widget.Label jLabel286;
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
    private widget.Label jLabel95;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane13;
    private widget.Table tbLaporanOps;
    private widget.Table tbTTV;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select lo.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, pg1.nama drOpera, pg2.nama nmAsis, "
                    + "pg3.nama nmInstru, pg4.nama nmOnlop, pg5.nama drAnes, pg6.nama nmPerawat from laporan_operasi lo "
                    + "inner join reg_periksa rp on rp.no_rawat=lo.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg1 on pg1.nik=lo.nip_dr_operator inner join pegawai pg2 on pg2.nik=lo.nip_asisten "
                    + "inner join pegawai pg3 on pg3.nik=lo.nip_instrumen inner join pegawai pg4 on pg4.nik=lo.nip_onloop "
                    + "inner join pegawai pg5 on pg5.nik=lo.nip_dr_anastesi inner join pegawai pg6 on pg6.nik=lo.nip_perawat_anastesi where "
                    + "lo.tgl_operasi between ? and ? and lo.no_rawat LIKE ? or "
                    + "lo.tgl_operasi between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "lo.tgl_operasi between ? and ? and p.nm_pasien LIKE ? or "
                    + "lo.tgl_operasi between ? and ? and pg1.nama LIKE ? or "
                    + "lo.tgl_operasi between ? and ? and pg2.nama LIKE ? or "
                    + "lo.tgl_operasi between ? and ? and pg3.nama LIKE ? or "
                    + "lo.tgl_operasi between ? and ? and pg4.nama LIKE ? or "
                    + "lo.tgl_operasi between ? and ? and pg5.nama LIKE ? or "
                    + "lo.tgl_operasi between ? and ? and pg6.nama LIKE ? or "
                    + "lo.tgl_operasi between ? and ? and lo.ruang_rawat LIKE ? ORDER BY lo.tgl_operasi desc");
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
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgllahir"),
                        rs.getString("ruang_rawat"),                        
                        rs.getString("drOpera"),                        
                        rs.getString("nmAsis"),                        
                        rs.getString("nmInstru"),                        
                        rs.getString("nmOnlop"),                        
                        rs.getString("drAnes"),
                        rs.getString("nmPerawat"),
                        rs.getString("diag_pra_bedah"),
                        rs.getString("diag_pasca_bedah"),
                        rs.getString("tindakan_operasi"),
                        rs.getString("jns_ops_khusus"),
                        rs.getString("jns_ops_besar"),
                        rs.getString("jns_ops_sedang"),
                        rs.getString("jns_ops_kecil"),
                        rs.getString("urg_ops_elektif"),
                        rs.getString("urg_ops_darurat"),
                        rs.getString("urg_ops_odc"),
                        rs.getString("mcm_ops_bersih"),
                        rs.getString("mcm_ops_kontaminasi"),
                        rs.getString("mcm_ops_kotor"),
                        rs.getString("jml_1"),
                        rs.getString("jml_2"),
                        rs.getString("jml_3"),
                        rs.getString("jml_lainya"),
                        rs.getString("ket_jml_lainya"),
                        rs.getString("implan"),
                        rs.getString("ket_implan_ada"),
                        rs.getString("nip_dr_operator"),
                        rs.getString("nip_asisten"),
                        rs.getString("nip_instrumen"),
                        rs.getString("nip_onloop"),
                        rs.getString("nip_dr_anastesi"),
                        rs.getString("nip_perawat_anastesi"),
                        rs.getString("tgl_operasi"),
                        rs.getString("cek_sign_in"),
                        rs.getString("cek_time_out"),
                        rs.getString("cek_sign_out"),
                        rs.getString("cek_selesai"),
                        rs.getString("sign_in"),
                        rs.getString("time_out"),
                        rs.getString("sign_out"),
                        rs.getString("selesai"),
                        rs.getString("catatan_deskripsi"),
                        rs.getString("instruksi_post_ops"),
                        rs.getString("jml_perdarahan"),
                        rs.getString("transfusi"),
                        rs.getString("jns_transfusi"),
                        rs.getString("jml_transfusi"),
                        rs.getString("jaringan"),
                        rs.getString("jns_jaringan"),
                        rs.getString("pemeriksaan_pa"),
                        rs.getString("komplikasi"),
                        rs.getString("tgl_laporan"),
                        rs.getString("jam_laporan"),
                        rs.getString("waktu_simpan")
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.RMLaporanOperasi.tampil() : " + e);
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
        TpraBedah.setText("");
        TpascaBedah.setText("");
        Ttindakan.setText("");
        chkKhusus.setSelected(false);
        chkBesar.setSelected(false);
        chkSedang.setSelected(false);
        chkKecil.setSelected(false);
        chkElektif.setSelected(false);
        chkDarurat.setSelected(false);
        chkOdc.setSelected(false);
        chkBersih.setSelected(false);
        chkKontaminasi.setSelected(false);
        chkKotor.setSelected(false);
        chkJml1.setSelected(false);
        chkJml2.setSelected(false);
        chkJml3.setSelected(false);
        chkJmlLain.setSelected(false);
        TketJml.setEnabled(false);
        TketJml.setText("");
        cmbImplan.setSelectedIndex(0);
        TketImplan.setText("");
        TketImplan.setEnabled(false);
        nipDrOperator = "-";
        nipAsisten = "-";
        nipInstrumen = "-";
        nipOnloop = "-";
        nipDrAnes = "-";
        nipPrwtAnes = "-";
        TnmDrOperator.setText("-");
        TnmAsisten.setText("-");
        TnmInstrumen.setText("-");
        TnmOnloop.setText("-");
        TnmDrAnestesi.setText("-");
        TnmPrwtAnestesi.setText("-");
        TtglOperasi.setDate(new Date());
        chkCekSignIn.setSelected(false);
        chkCekTime.setSelected(false);
        chkCekSignOut.setSelected(false);
        chkCekSelesai.setSelected(false);
        cmbJam1.setEnabled(false);
        cmbMnt1.setEnabled(false);
        cmbDtk1.setEnabled(false);
        cmbJam2.setEnabled(false);
        cmbMnt2.setEnabled(false);
        cmbDtk2.setEnabled(false);
        cmbJam3.setEnabled(false);
        cmbMnt3.setEnabled(false);
        cmbDtk3.setEnabled(false);
        cmbJam4.setEnabled(false);
        cmbMnt4.setEnabled(false);
        cmbDtk4.setEnabled(false);
        cmbJam1.setSelectedIndex(0);
        cmbMnt1.setSelectedIndex(0);
        cmbDtk1.setSelectedIndex(0);
        cmbJam2.setSelectedIndex(0);
        cmbMnt2.setSelectedIndex(0);
        cmbDtk2.setSelectedIndex(0);
        cmbJam3.setSelectedIndex(0);
        cmbMnt3.setSelectedIndex(0);
        cmbDtk3.setSelectedIndex(0);
        cmbJam4.setSelectedIndex(0);
        cmbMnt4.setSelectedIndex(0);
        cmbDtk4.setSelectedIndex(0);
        TCttnOperasi.setText("");
        Valid.tabelKosong(tabMode1);
        emptTeksTTV();
        Tinstruksi.setText("");
        TjmlPerdarahan.setText("");
        cmbTransfusi.setSelectedIndex(0);
        cmbJnsTransfusi.setEnabled(false);
        cmbJnsTransfusi.setSelectedIndex(0);
        TjmlTrans.setText("");
        TjmlTrans.setEnabled(false);
        cmbJaringan.setSelectedIndex(0);
        TjnsJaringan.setText("");
        TjnsJaringan.setEnabled(false);
        cmbPemeriksaan.setSelectedIndex(0);
        Tkomplikasi.setText("");
        TtglLaporan.setDate(new Date());
        cmbJam6.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt6.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk6.setSelectedIndex(0);
        BtnMicCatatan.setText("MIC OFF");
        BtnMicCatatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/mic_off.png")));
    }

    private void getData() {
        variabelBersih();
        if (tbLaporanOps.getSelectedRow() != -1) {
            TNoRw.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 0).toString());
            TNoRM.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 1).toString());
            TPasien.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 4).toString());
            TpraBedah.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 11).toString());
            TpascaBedah.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 12).toString());
            Ttindakan.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 13).toString());
            khusus = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 14).toString();
            besar = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 15).toString();
            sedang = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 16).toString();
            kecil = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 17).toString();
            elektif = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 18).toString();
            darurat = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 19).toString();
            odc = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 20).toString();
            bersih = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 21).toString();
            konta = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 22).toString();
            kotor = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 23).toString();
            jml1 = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 24).toString();
            jml2 = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 25).toString();
            jml3 = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 26).toString();
            jmlLain = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 27).toString();
            TketJml.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 28).toString());
            cmbImplan.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 29).toString());
            TketImplan.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 30).toString());
            nipDrOperator = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 31).toString();
            nipAsisten = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 32).toString();
            nipInstrumen = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 33).toString();
            nipOnloop = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 34).toString();
            nipDrAnes = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 35).toString();
            nipPrwtAnes = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 36).toString();
            TnmDrOperator.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 5).toString());
            TnmAsisten.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 6).toString());
            TnmInstrumen.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 7).toString());
            TnmOnloop.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 8).toString());
            TnmDrAnestesi.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 9).toString());
            TnmPrwtAnestesi.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 10).toString());
            Valid.SetTgl(TtglOperasi, tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 37).toString());
            singin = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 38).toString();
            time = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 39).toString();
            singot = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 40).toString();
            selesai = tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 41).toString();
            cmbJam1.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 42).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 42).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 42).toString().substring(6, 8));
            cmbJam2.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 43).toString().substring(0, 2));
            cmbMnt2.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 43).toString().substring(3, 5));
            cmbDtk2.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 43).toString().substring(6, 8));
            cmbJam3.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 44).toString().substring(0, 2));
            cmbMnt3.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 44).toString().substring(3, 5));
            cmbDtk3.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 44).toString().substring(6, 8));
            cmbJam4.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 45).toString().substring(0, 2));
            cmbMnt4.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 45).toString().substring(3, 5));
            cmbDtk4.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 45).toString().substring(6, 8));
            TCttnOperasi.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 46).toString());            
            Tinstruksi.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 47).toString());
            TjmlPerdarahan.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 48).toString());
            cmbTransfusi.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 49).toString());
            cmbJnsTransfusi.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 50).toString());
            TjmlTrans.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 51).toString());
            cmbJaringan.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 52).toString());
            TjnsJaringan.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 53).toString());
            cmbPemeriksaan.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 54).toString());
            Tkomplikasi.setText(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 55).toString());
            Valid.SetTgl(TtglLaporan, tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 56).toString());
            cmbJam6.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 57).toString().substring(0, 2));
            cmbMnt6.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 57).toString().substring(3, 5));
            cmbDtk6.setSelectedItem(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 57).toString().substring(6, 8));
            emptTeksTTV();
            tampilTtv(tbLaporanOps.getValueAt(tbLaporanOps.getSelectedRow(), 58).toString());
            dataCek();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getkegiatan_operasi());
        BtnGanti.setEnabled(akses.getkegiatan_operasi());
        BtnHapus.setEnabled(akses.getkegiatan_operasi());
        BtnMicCatatan.setEnabled(akses.getadmin());
    }
    
    private void dataCek() {
        if (khusus.equals("ya")) {
            chkKhusus.setSelected(true);
        } else {
            chkKhusus.setSelected(false);
        }
        
        if (besar.equals("ya")) {
            chkBesar.setSelected(true);
        } else {
            chkBesar.setSelected(false);
        }
        
        if (sedang.equals("ya")) {
            chkSedang.setSelected(true);
        } else {
            chkSedang.setSelected(false);
        }
        
        if (kecil.equals("ya")) {
            chkKecil.setSelected(true);
        } else {
            chkKecil.setSelected(false);
        }
        
        if (elektif.equals("ya")) {
            chkElektif.setSelected(true);
        } else {
            chkElektif.setSelected(false);
        }
        
        if (darurat.equals("ya")) {
            chkDarurat.setSelected(true);
        } else {
            chkDarurat.setSelected(false);
        }
        
        if (odc.equals("ya")) {
            chkOdc.setSelected(true);
        } else {
            chkOdc.setSelected(false);
        }
        
        if (bersih.equals("ya")) {
            chkBersih.setSelected(true);
        } else {
            chkBersih.setSelected(false);
        }
        
        if (konta.equals("ya")) {
            chkKontaminasi.setSelected(true);
        } else {
            chkKontaminasi.setSelected(false);
        }
        
        if (kotor.equals("ya")) {
            chkKotor.setSelected(true);
        } else {
            chkKotor.setSelected(false);
        }
        
        if (jml1.equals("ya")) {
            chkJml1.setSelected(true);
        } else {
            chkJml1.setSelected(false);
        }
        
        if (jml2.equals("ya")) {
            chkJml2.setSelected(true);
        } else {
            chkJml2.setSelected(false);
        }
        
        if (jml3.equals("ya")) {
            chkJml3.setSelected(true);
        } else {
            chkJml3.setSelected(false);
        }

        if (jmlLain.equals("ya")) {
            chkJmlLain.setSelected(true);
            TketJml.setEnabled(true);
        } else {
            chkJmlLain.setSelected(false);
            TketJml.setEnabled(false);
        }
        
        if (cmbImplan.getSelectedIndex() == 2) {
            TketImplan.setEnabled(true);
        } else {
            TketImplan.setEnabled(false);
        }
        
        if (singin.equals("ya")) {
            chkCekSignIn.setSelected(true);
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
        } else {
            chkCekSignIn.setSelected(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
        }
        
        if (time.equals("ya")) {
            chkCekTime.setSelected(true);
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
        } else {
            chkCekTime.setSelected(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
        }
        
        if (singot.equals("ya")) {
            chkCekSignOut.setSelected(true);
            cmbJam3.setEnabled(true);
            cmbMnt3.setEnabled(true);
            cmbDtk3.setEnabled(true);
        } else {
            chkCekSignOut.setSelected(false);
            cmbJam3.setEnabled(false);
            cmbMnt3.setEnabled(false);
            cmbDtk3.setEnabled(false);
        }
        
        if (selesai.equals("ya")) {
            chkCekSelesai.setSelected(true);
            cmbJam4.setEnabled(true);
            cmbMnt4.setEnabled(true);
            cmbDtk4.setEnabled(true);
        } else {
            chkCekSelesai.setSelected(false);
            cmbJam4.setEnabled(false);
            cmbMnt4.setEnabled(false);
            cmbDtk4.setEnabled(false);
        }  
        
        if (cmbTransfusi.getSelectedIndex() == 1) {
            cmbJnsTransfusi.setEnabled(true);
            TjmlTrans.setEnabled(true);
        } else {
            cmbJnsTransfusi.setEnabled(false);
            TjmlTrans.setEnabled(false);
        }
        
        if (cmbJaringan.getSelectedIndex() == 1) {
            TjnsJaringan.setEnabled(true);
        } else {
            TjnsJaringan.setEnabled(false);
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
        if (chkKhusus.isSelected() == true) {
            khusus = "ya";
        } else {
            khusus = "tidak";
        }
        
        if (chkBesar.isSelected() == true) {
            besar = "ya";
        } else {
            besar = "tidak";
        }
        
        if (chkSedang.isSelected() == true) {
            sedang = "ya";
        } else {
            sedang = "tidak";
        }
        
        if (chkKecil.isSelected() == true) {
            kecil = "ya";
        } else {
            kecil = "tidak";
        }
        
        if (chkElektif.isSelected() == true) {
            elektif = "ya";
        } else {
            elektif = "tidak";
        }
        
        if (chkDarurat.isSelected() == true) {
            darurat = "ya";
        } else {
            darurat = "tidak";
        }
        
        if (chkOdc.isSelected() == true) {
            odc = "ya";
        } else {
            odc = "tidak";
        }
        
        if (chkBersih.isSelected() == true) {
            bersih = "ya";
        } else {
            bersih = "tidak";
        }
        
        if (chkKontaminasi.isSelected() == true) {
            konta = "ya";
        } else {
            konta = "tidak";
        }
        
        if (chkKotor.isSelected() == true) {
            kotor = "ya";
        } else {
            kotor = "tidak";
        }
        
        if (chkJml1.isSelected() == true) {
            jml1 = "ya";
        } else {
            jml1 = "tidak";
        }
        
        if (chkJml2.isSelected() == true) {
            jml2 = "ya";
        } else {
            jml2 = "tidak";
        }
        
        if (chkJml3.isSelected() == true) {
            jml3 = "ya";
        } else {
            jml3 = "tidak";
        }
        
        if (chkJmlLain.isSelected() == true) {
            jmlLain = "ya";
        } else {
            jmlLain = "tidak";
        }
        
        if (chkCekSignIn.isSelected() == true) {
            singin = "ya";
        } else {
            singin = "tidak";
        }
        
        if (chkCekTime.isSelected() == true) {
            time = "ya";
        } else {
            time = "tidak";
        }
        
        if (chkCekSignOut.isSelected() == true) {
            singot = "ya";
        } else {
            singot = "tidak";
        }
        
        if (chkCekSelesai.isSelected() == true) {
            selesai = "ya";
        } else {
            selesai = "tidak";
        }
    }
    
    private void variabelBersih() {
        nipDrOperator = "";
        nipAsisten = "";
        nipInstrumen = "";
        nipOnloop = "";
        nipDrAnes = "";
        nipPrwtAnes = "";
        khusus = "";
        besar = "";
        sedang = "";
        kecil = "";
        elektif = "";
        darurat = "";
        odc = "";
        bersih = "";
        konta = "";
        kotor = "";
        jml1 = "";
        jml2 = "";
        jml3 = "";
        jmlLain = "";
        singin = "";
        time = "";
        singot = "";
        selesai = "";
    }
    
    private void emptTeksTTV() {
        cmbJam5.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt5.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk5.setSelectedIndex(0);
        Tsistol.setText("");
        Tdistol.setText("");
        Tnadi.setText("");
        Trr.setText("");
        Tsuhu.setText("");
        Tspo.setText("");
    }
    
    private void urutkanDataTTV() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabMode1);
        tbTTV.setRowSorter(sorter);

        /*Penjelasan : sorter.setComparator(1 
        new RowSorter.SortKey(1
        angka 1 adalah kolom ke nya
         */
        sorter.setComparator(1, (o1, o2) -> {
            Integer n1 = Integer.parseInt(o1.toString());
            Integer n2 = Integer.parseInt(o2.toString());
            return n1.compareTo(n2);
        });

        sorter.setSortKeys(Arrays.asList(
                new RowSorter.SortKey(1, SortOrder.ASCENDING)
        ));

        sorter.sort();
    }
    
    private void getDataTtv() {
        urutanKe = "";
        if (tbTTV.getSelectedRow() != -1) {
            urutanKe = tbTTV.getValueAt(tbTTV.getSelectedRow(), 1).toString();
            cmbJam5.setSelectedItem(tbTTV.getValueAt(tbTTV.getSelectedRow(), 2).toString().substring(0, 2));
            cmbMnt5.setSelectedItem(tbTTV.getValueAt(tbTTV.getSelectedRow(), 2).toString().substring(3, 5));
            cmbDtk5.setSelectedItem(tbTTV.getValueAt(tbTTV.getSelectedRow(), 2).toString().substring(6, 8));
            Tsistol.setText(tbTTV.getValueAt(tbTTV.getSelectedRow(), 3).toString());
            Tdistol.setText(tbTTV.getValueAt(tbTTV.getSelectedRow(), 4).toString());
            Tnadi.setText(tbTTV.getValueAt(tbTTV.getSelectedRow(), 5).toString());
            Trr.setText(tbTTV.getValueAt(tbTTV.getSelectedRow(), 6).toString());
            Tsuhu.setText(tbTTV.getValueAt(tbTTV.getSelectedRow(), 7).toString());
            Tspo.setText(tbTTV.getValueAt(tbTTV.getSelectedRow(), 8).toString());            
        }
    }
    
    private void tampilTtv(String wktsimpan) {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("select * from laporan_operasi_obs_ttv where waktu_simpan ='" + wktsimpan + "' order by urutan");
            try {                
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        rs1.getString("no_rawat"),
                        rs1.getString("urutan"),
                        rs1.getString("pukul"),
                        rs1.getString("td_sistole"),
                        rs1.getString("td_diastole"),
                        rs1.getString("nadi"),
                        rs1.getString("rr"),
                        rs1.getString("suhu"),
                        rs1.getString("spo2"),
                        rs1.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilTtv() : " + e);
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
    }
    
    private void micMulai(javax.swing.JTextArea txtHasil) {
        new Thread(() -> {
            Model model = null;
            Recognizer recognizer = null;

            try {
                model = Sequel.getSpeechModel();

                if (model == null) {
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        BtnMicCatatan.setText("MIC OFF");
                        BtnMicCatatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/mic_off.png")));
                    });
                    return;
                }

                AudioFormat format = new AudioFormat(16000, 16, 1, true, false);
                DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

//                microphone = (TargetDataLine) AudioSystem.getLine(info);
                microphone = getMicrophoneByName(format, "Noir");
                microphone.open(format);
                microphone.start();
                System.out.println("Microphone aktif...");

                recognizer = new Recognizer(model, 16000);
                byte[] buffer = new byte[4096];
                running = true;

                while (running) {
                    int bytesRead = microphone.read(buffer, 0, buffer.length);
                    
                    int level = 0;
                    for (int i = 0; i < bytesRead; i += 2) {
                        int sample = (buffer[i + 1] << 8) | (buffer[i] & 0xff);
                        level += Math.abs(sample);
                    }
                    level = level / (bytesRead / 2);

//                    System.out.println("LEVEL SUARA : " + level);
//                    System.out.println("Bytes terbaca : " + bytesRead);

                    if (recognizer.acceptWaveForm(buffer, bytesRead)) {

                        String hasil = recognizer.getResult();
//                        System.out.println("HASIL JSON : " + hasil);

                        String text = ambilTextDariJson(hasil);

                        if (!text.equals("")) {
                            System.out.println("TEXT : " + text);

                            javax.swing.SwingUtilities.invokeLater(() -> {
                                txtHasil.append(text + "\n");
                                txtHasil.setCaretPosition(
                                        txtHasil.getDocument().getLength()
                                );
                            });
                        }

                    } else {

                        String partial = recognizer.getPartialResult();
                        System.out.println("PARTIAL : " + partial);

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                javax.swing.SwingUtilities.invokeLater(() -> {
                    txtHasil.append("\nError : " + e.getMessage() + "\n");
                    BtnMicCatatan.setText("MIC OFF");
                    BtnMicCatatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/mic_off.png")));
                });
            } finally {
                try {
                    if (recognizer != null) {
                        recognizer.close();
                    }
                    if (model != null) {
                        model.close();
                    }
                    if (microphone != null) {
                        microphone.stop();
                        microphone.close();
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi stop mic : " + e);
                }
            }
        }).start();
    }
    
    private void micStop() {
        running = false;

        try {
            if (microphone != null) {
                microphone.stop();
                microphone.close();
            }
        } catch (Exception e) {
            System.out.println("Notifikasi micStop : " + e);
        }
    }

    private String ambilTextDariJson(String json) {
        try {
            return json.replace("{", "")
                    .replace("}", "")
                    .replace("\"text\" :", "")
                    .replace("\"", "")
                    .trim();
        } catch (Exception e) {
            return "";
        }
    }
    
    private void cekDaftarMicrophone() {
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();

        for (Mixer.Info mixerInfo : mixers) {
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getTargetLineInfo();

            if (lineInfos.length > 0) {
                System.out.println("MIC DEVICE : " + mixerInfo.getName()
                        + " - " + mixerInfo.getDescription());
            }
        }
    }
    
    private TargetDataLine getMicrophoneByName(AudioFormat format, String namaDevice) throws Exception {
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();

        for (Mixer.Info mixerInfo : mixers) {
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, format);

            if (mixer.isLineSupported(dataLineInfo)) {
                System.out.println("MIC TERDETEKSI : " + mixerInfo.getName());

                if (mixerInfo.getName().toLowerCase().contains(namaDevice.toLowerCase())) {
                    System.out.println("MIC DIPAKAI : " + mixerInfo.getName());
                    return (TargetDataLine) mixer.getLine(dataLineInfo);
                }
            }
        }

        // fallback kalau Noir tidak ketemu
        System.out.println("MIC Noir tidak ketemu, pakai default.");
        return (TargetDataLine) AudioSystem.getLine(
                new DataLine.Info(TargetDataLine.class, format)
        );
    }
    
    private void whisperMulaiRekam() {
        try {
            AudioFormat format = new AudioFormat(16000, 16, 1, true, false);

            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            whisperMic = (TargetDataLine) AudioSystem.getLine(info);
            whisperMic.open(format);
            whisperMic.start();

            fileRekamWhisper = new File(System.getProperty("java.io.tmpdir"), "rekam_whisper.wav");

            whisperRecording = true;

            Thread thread = new Thread(() -> {
                try {
                    AudioInputStream ais = new AudioInputStream(whisperMic);
                    AudioSystem.write(ais, AudioFileFormat.Type.WAVE, fileRekamWhisper);
                } catch (Exception e) {
                    System.out.println("Error rekam whisper : " + e);
                }
            });

            thread.start();

            System.out.println("Mulai rekam Whisper : " + fileRekamWhisper.getAbsolutePath());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mulai rekam Whisper\n" + e.getMessage());
        }
    }

    private void whisperStopDanTranskrip() {
        try {
            whisperRecording = false;

            if (whisperMic != null) {
                whisperMic.stop();
                whisperMic.close();
            }

            System.out.println("Stop rekam Whisper");

            new Thread(() -> {
                String hasil = jalankanWhisper(fileRekamWhisper);

                if (!hasil.trim().equals("")) {
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        TCttnOperasi.append(hasil.trim() + "\n");
                        TCttnOperasi.setCaretPosition(TCttnOperasi.getDocument().getLength());
                    });
                }
            }).start();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal stop/transkrip Whisper\n" + e.getMessage());
        }
    }
    
    private String jalankanWhisper(File fileWav) {
        StringBuilder hasil = new StringBuilder();

        try {
            String whisperCli = Sequel.getWhisperCli();
            String model = Sequel.getWhisperModel();

            System.out.println("WHISPER CLI : " + whisperCli);
            System.out.println("MODEL       : " + model);
            System.out.println("FILE WAV    : " + fileWav.getAbsolutePath());

            if (!new File(whisperCli).exists()) {
                return "File whisper-cli tidak ditemukan : " + whisperCli;
            }

            if (!new File(model).exists()) {
                return "File model Whisper tidak ditemukan : " + model;
            }

            ProcessBuilder pb = new ProcessBuilder(
                    whisperCli,
                    "-m", model,
                    "-f", fileWav.getAbsolutePath(),
                    "-l", "id",
                    "-nt"
            );

            pb.redirectErrorStream(true);

            Process proses = pb.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(proses.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("WHISPER : " + line);

                if (!line.trim().equals("")
                        && !line.contains("whisper_")
                        && !line.contains("system_info")
                        && !line.contains("main:")) {
                    hasil.append(line.trim()).append("\n");
                }
            }

            proses.waitFor();

        } catch (Exception e) {
            System.out.println("Gagal jalankan Whisper : " + e);
            return "Gagal jalankan Whisper : " + e.getMessage();
        }

        return hasil.toString();
    }
}
