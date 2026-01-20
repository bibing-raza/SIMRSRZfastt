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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class RMCeklisKeselamatanOperasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0, pilihDokter = 0, pilihPerawat = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String wktSimpan = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMCeklisKeselamatanOperasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "waktu_simpan", "No. Rawat", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Ceklis",
            "the_sign_in", "identifikasi_gelang", "lokasi_operasi", "prosedur", "surat_ijin_operasi", "lokasi_operasi_diberi_tanda",
            "mesin_sdh_dicek_lengkap", "pulse_oximeter", "pasien_punya_riwayat_alergi", "kesulitan_betnafas", "menggunakan_peralatan_bantuan",
            "resiko_kehilangan_darah", "500ml_pada_anak", "dua_akses_intravensi", "rencana_terapi_cairan", "nip_perawat", "nip_dokter", "waktu_simpan",
            "the_time_out", "konfirmasi_anggota_tim", "nama_pasien", "prosedur", "lokasi_insisi_dibuat", "antibiotik_diberikan", "cek_nama_antibiotik",
            "cek_dosis_antibiotik", "nama_antibiotik", "dosis_antibiotik", "antisipasi_kejadian_kritis", "review_dokter", "review_tim", "cvc_dipasang",
            "review_tim_perawat", "foto_rongsen_ditayangkan", "nip_operator", "nip_dokter", "nip_perawat", "the_sign_out", "nama_prosedur", "instrumen_kasa",
            "spesimen", "adakah_masalah", "operator_dokter_bedah", "hal_yang_diperhatikan", "tgl_tindakan", "verifikasi", "nip_perawat", "nip_dokter",
            "tglCeklisAsli", "nmPerawatC1", "nmDokterC1", "nmOperatorC2", "nmDokterC2", "nmPerawatC2", "nmPerawatC3", "nmDokterC3"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbCeklis.setModel(tabMode);
        tbCeklis.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCeklis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 64; i++) {
            TableColumn column = tbCeklis.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setPreferredWidth(75);                
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
            }
        }
        tbCeklis.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TnmAntibiotik.setDocument(new batasInput((int) 200).getKata(TnmAntibiotik));
        TdosisAntibiotik.setDocument(new batasInput((int) 200).getKata(TdosisAntibiotik));
        Tjika.setDocument(new batasInput((int) 200).getKata(Tjika));
        
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
                if (akses.getform().equals("RMCeklisKeselamatanOperasi")) {
                    if (pilihDokter == 1) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            TnipDokterAnes1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            TnmDokterAnes1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDokterAnes1.requestFocus();
                        }                        
                    } else if (pilihDokter == 2) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            TnipDokterAnes2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            TnmDokterAnes2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDokterAnes2.requestFocus();
                        }                        
                    } else if (pilihDokter == 3) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            TnipDokterAnes3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            TnmDokterAnes3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDokterAnes3.requestFocus();
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
                if (akses.getform().equals("RMCeklisKeselamatanOperasi")) {
                    if (pilihPerawat == 1) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            TnipPerawatSir.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            TnmPerawatSir.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnPerawatSir.requestFocus();
                        }
                    } else if (pilihPerawat == 2) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            TnipOperator.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            TnmOperator.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnOperator.requestFocus();
                        }
                    } else if (pilihPerawat == 3) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            TnipPerawatSirkuit1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            TnmPerawatSirkuit1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnPerawatSirkuit1.requestFocus();
                        }
                    } else if (pilihPerawat == 4) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            TnipPerawatSirkuit2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            TnmPerawatSirkuit2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnPerawatSirkuit2.requestFocus();
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
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel64 = new widget.Label();
        jLabel65 = new widget.Label();
        label15 = new widget.Label();
        TnipDokterAnes1 = new widget.TextBox();
        TnmDokterAnes1 = new widget.TextBox();
        BtnDokterAnes1 = new widget.Button();
        label16 = new widget.Label();
        TnipDokterAnes2 = new widget.TextBox();
        TnmDokterAnes2 = new widget.TextBox();
        BtnDokterAnes2 = new widget.Button();
        jLabel68 = new widget.Label();
        cmbIdentifikasi = new widget.ComboBox();
        jLabel69 = new widget.Label();
        cmbLokasi1 = new widget.ComboBox();
        jLabel70 = new widget.Label();
        cmbProsedur1 = new widget.ComboBox();
        jLabel71 = new widget.Label();
        cmbSurat = new widget.ComboBox();
        TtglTindakan = new widget.Tanggal();
        label20 = new widget.Label();
        TnipPerawatSir = new widget.TextBox();
        TnmPerawatSir = new widget.TextBox();
        BtnPerawatSir = new widget.Button();
        label21 = new widget.Label();
        TnipOperator = new widget.TextBox();
        TnmOperator = new widget.TextBox();
        BtnOperator = new widget.Button();
        chkSayaSir = new widget.CekBox();
        chkSayaOp = new widget.CekBox();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel86 = new widget.Label();
        jLabel87 = new widget.Label();
        cmbLokasi2 = new widget.ComboBox();
        jLabel88 = new widget.Label();
        cmbMesin = new widget.ComboBox();
        jLabel89 = new widget.Label();
        cmbPulse = new widget.ComboBox();
        jLabel90 = new widget.Label();
        cmbApakahPasien = new widget.ComboBox();
        jLabel91 = new widget.Label();
        cmbKesulitan = new widget.ComboBox();
        jLabel92 = new widget.Label();
        cmbDan = new widget.ComboBox();
        jLabel93 = new widget.Label();
        cmbResiko = new widget.ComboBox();
        jLabel94 = new widget.Label();
        cmb500 = new widget.ComboBox();
        jLabel95 = new widget.Label();
        cmbDua = new widget.ComboBox();
        jLabel96 = new widget.Label();
        cmbRencana = new widget.ComboBox();
        jLabel66 = new widget.Label();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        jLabel97 = new widget.Label();
        jLabel98 = new widget.Label();
        cmbKonfirmasi = new widget.ComboBox();
        jLabel99 = new widget.Label();
        cmbNama = new widget.ComboBox();
        jLabel100 = new widget.Label();
        jLabel101 = new widget.Label();
        cmbProsedur2 = new widget.ComboBox();
        jLabel102 = new widget.Label();
        cmbLokasiDimana = new widget.ComboBox();
        jLabel103 = new widget.Label();
        cmbApakahAntibiotik = new widget.ComboBox();
        jLabel104 = new widget.Label();
        TnmAntibiotik = new widget.TextBox();
        cmbNamaAntibiotik = new widget.ComboBox();
        jLabel105 = new widget.Label();
        TdosisAntibiotik = new widget.TextBox();
        cmbDosisAntibiotik = new widget.ComboBox();
        jLabel106 = new widget.Label();
        Tantisipasi = new widget.TextBox();
        jLabel107 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        TreviewA = new widget.TextArea();
        jLabel108 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        TreviewB = new widget.TextArea();
        jLabel109 = new widget.Label();
        Tjika = new widget.TextBox();
        jLabel110 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        TreviewC = new widget.TextArea();
        jLabel111 = new widget.Label();
        cmbApakahFoto = new widget.ComboBox();
        label22 = new widget.Label();
        TnipPerawatSirkuit1 = new widget.TextBox();
        TnmPerawatSirkuit1 = new widget.TextBox();
        BtnPerawatSirkuit1 = new widget.Button();
        chkSayaSirkuit1 = new widget.CekBox();
        jLabel67 = new widget.Label();
        cmbJam3 = new widget.ComboBox();
        cmbMnt3 = new widget.ComboBox();
        cmbDtk3 = new widget.ComboBox();
        jLabel112 = new widget.Label();
        jLabel113 = new widget.Label();
        cmbNamaProsedur = new widget.ComboBox();
        jLabel114 = new widget.Label();
        jLabel115 = new widget.Label();
        cmbInstrumen = new widget.ComboBox();
        jLabel116 = new widget.Label();
        cmbSpesimen = new widget.ComboBox();
        jLabel117 = new widget.Label();
        cmbAdakah = new widget.ComboBox();
        jLabel118 = new widget.Label();
        jLabel119 = new widget.Label();
        jLabel120 = new widget.Label();
        cmb2Operator = new widget.ComboBox();
        jLabel121 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        ThalYang = new widget.TextArea();
        jLabel122 = new widget.Label();
        jLabel123 = new widget.Label();
        Tverifikasi = new widget.TextBox();
        label23 = new widget.Label();
        TnipPerawatSirkuit2 = new widget.TextBox();
        TnmPerawatSirkuit2 = new widget.TextBox();
        BtnPerawatSirkuit2 = new widget.Button();
        chkSayaSirkuit2 = new widget.CekBox();
        label17 = new widget.Label();
        TnipDokterAnes3 = new widget.TextBox();
        TnmDokterAnes3 = new widget.TextBox();
        BtnDokterAnes3 = new widget.Button();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        jLabel124 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelInput1 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbCeklis = new widget.Table();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Checklist Keselamatan Operasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(600, 402));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1592));
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

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("THE SIGN IN (Pukul) :");
        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 66, 140, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("1. Pasien Telah Dikonfirmasikan :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 94, 200, 23);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("Dokter Anestesi :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(0, 458, 140, 23);

        TnipDokterAnes1.setEditable(false);
        TnipDokterAnes1.setForeground(new java.awt.Color(0, 0, 0));
        TnipDokterAnes1.setName("TnipDokterAnes1"); // NOI18N
        TnipDokterAnes1.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipDokterAnes1);
        TnipDokterAnes1.setBounds(145, 458, 150, 23);

        TnmDokterAnes1.setEditable(false);
        TnmDokterAnes1.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokterAnes1.setName("TnmDokterAnes1"); // NOI18N
        TnmDokterAnes1.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmDokterAnes1);
        TnmDokterAnes1.setBounds(298, 458, 360, 23);

        BtnDokterAnes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokterAnes1.setMnemonic('2');
        BtnDokterAnes1.setToolTipText("Alt+2");
        BtnDokterAnes1.setName("BtnDokterAnes1"); // NOI18N
        BtnDokterAnes1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokterAnes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterAnes1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokterAnes1);
        BtnDokterAnes1.setBounds(660, 458, 28, 23);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Dokter Anastesi :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label16);
        label16.setBounds(0, 1129, 140, 23);

        TnipDokterAnes2.setEditable(false);
        TnipDokterAnes2.setForeground(new java.awt.Color(0, 0, 0));
        TnipDokterAnes2.setName("TnipDokterAnes2"); // NOI18N
        TnipDokterAnes2.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipDokterAnes2);
        TnipDokterAnes2.setBounds(145, 1129, 150, 23);

        TnmDokterAnes2.setEditable(false);
        TnmDokterAnes2.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokterAnes2.setName("TnmDokterAnes2"); // NOI18N
        TnmDokterAnes2.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmDokterAnes2);
        TnmDokterAnes2.setBounds(298, 1129, 360, 23);

        BtnDokterAnes2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokterAnes2.setMnemonic('2');
        BtnDokterAnes2.setToolTipText("Alt+2");
        BtnDokterAnes2.setName("BtnDokterAnes2"); // NOI18N
        BtnDokterAnes2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokterAnes2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterAnes2ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokterAnes2);
        BtnDokterAnes2.setBounds(660, 1129, 28, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("- Identifikasi Dari Gelang Pasien :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 122, 200, 23);

        cmbIdentifikasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbIdentifikasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbIdentifikasi.setName("cmbIdentifikasi"); // NOI18N
        FormInput.add(cmbIdentifikasi);
        cmbIdentifikasi.setBounds(205, 122, 65, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("- Lokasi Operasi :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 150, 200, 23);

        cmbLokasi1.setForeground(new java.awt.Color(0, 0, 0));
        cmbLokasi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbLokasi1.setName("cmbLokasi1"); // NOI18N
        FormInput.add(cmbLokasi1);
        cmbLokasi1.setBounds(205, 150, 65, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("- Prosedur :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(270, 122, 110, 23);

        cmbProsedur1.setForeground(new java.awt.Color(0, 0, 0));
        cmbProsedur1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbProsedur1.setName("cmbProsedur1"); // NOI18N
        FormInput.add(cmbProsedur1);
        cmbProsedur1.setBounds(385, 122, 65, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("- Surat Ijin Operasi :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(270, 150, 110, 23);

        cmbSurat.setForeground(new java.awt.Color(0, 0, 0));
        cmbSurat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbSurat.setName("cmbSurat"); // NOI18N
        FormInput.add(cmbSurat);
        cmbSurat.setBounds(385, 150, 65, 23);

        TtglTindakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-01-2026" }));
        TtglTindakan.setDisplayFormat("dd-MM-yyyy");
        TtglTindakan.setName("TtglTindakan"); // NOI18N
        TtglTindakan.setOpaque(false);
        TtglTindakan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglTindakan);
        TtglTindakan.setBounds(180, 1502, 90, 23);

        label20.setForeground(new java.awt.Color(0, 0, 0));
        label20.setText("Perawat Sirkuler :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label20);
        label20.setBounds(0, 430, 140, 23);

        TnipPerawatSir.setEditable(false);
        TnipPerawatSir.setForeground(new java.awt.Color(0, 0, 0));
        TnipPerawatSir.setName("TnipPerawatSir"); // NOI18N
        TnipPerawatSir.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipPerawatSir);
        TnipPerawatSir.setBounds(145, 430, 150, 23);

        TnmPerawatSir.setEditable(false);
        TnmPerawatSir.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawatSir.setName("TnmPerawatSir"); // NOI18N
        TnmPerawatSir.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmPerawatSir);
        TnmPerawatSir.setBounds(298, 430, 360, 23);

        BtnPerawatSir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPerawatSir.setMnemonic('2');
        BtnPerawatSir.setToolTipText("Alt+2");
        BtnPerawatSir.setName("BtnPerawatSir"); // NOI18N
        BtnPerawatSir.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPerawatSir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerawatSirActionPerformed(evt);
            }
        });
        FormInput.add(BtnPerawatSir);
        BtnPerawatSir.setBounds(660, 430, 28, 23);

        label21.setForeground(new java.awt.Color(0, 0, 0));
        label21.setText("Operator :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label21);
        label21.setBounds(0, 1101, 140, 23);

        TnipOperator.setEditable(false);
        TnipOperator.setForeground(new java.awt.Color(0, 0, 0));
        TnipOperator.setName("TnipOperator"); // NOI18N
        TnipOperator.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipOperator);
        TnipOperator.setBounds(145, 1101, 150, 23);

        TnmOperator.setEditable(false);
        TnmOperator.setForeground(new java.awt.Color(0, 0, 0));
        TnmOperator.setName("TnmOperator"); // NOI18N
        TnmOperator.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmOperator);
        TnmOperator.setBounds(298, 1101, 360, 23);

        BtnOperator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnOperator.setMnemonic('2');
        BtnOperator.setToolTipText("Alt+2");
        BtnOperator.setName("BtnOperator"); // NOI18N
        BtnOperator.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnOperator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnOperatorActionPerformed(evt);
            }
        });
        FormInput.add(BtnOperator);
        BtnOperator.setBounds(660, 1101, 28, 23);

        chkSayaSir.setBackground(new java.awt.Color(242, 242, 242));
        chkSayaSir.setForeground(new java.awt.Color(0, 0, 0));
        chkSayaSir.setText("Saya Sendiri");
        chkSayaSir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSayaSir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSayaSir.setName("chkSayaSir"); // NOI18N
        chkSayaSir.setOpaque(false);
        chkSayaSir.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSayaSir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaSirActionPerformed(evt);
            }
        });
        FormInput.add(chkSayaSir);
        chkSayaSir.setBounds(695, 430, 90, 23);

        chkSayaOp.setBackground(new java.awt.Color(242, 242, 242));
        chkSayaOp.setForeground(new java.awt.Color(0, 0, 0));
        chkSayaOp.setText("Saya Sendiri");
        chkSayaOp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSayaOp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSayaOp.setName("chkSayaOp"); // NOI18N
        chkSayaOp.setOpaque(false);
        chkSayaOp.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSayaOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaOpActionPerformed(evt);
            }
        });
        FormInput.add(chkSayaOp);
        chkSayaOp.setBounds(695, 1101, 90, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(145, 66, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(196, 66, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(247, 66, 45, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel86.setText("Dilakukan sebelum induksi anestesi, minimalnya oleh perawat & dokter anestesi");
        jLabel86.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(300, 66, 460, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("2. Lokasi Operasi Sudah Diberi Tanda :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 178, 320, 23);

        cmbLokasi2.setForeground(new java.awt.Color(0, 0, 0));
        cmbLokasi2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbLokasi2.setName("cmbLokasi2"); // NOI18N
        FormInput.add(cmbLokasi2);
        cmbLokasi2.setBounds(325, 178, 65, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("3. Mesin dan Obat-obatan Anestesi Sudah Dicek Lengkap :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(0, 206, 320, 23);

        cmbMesin.setForeground(new java.awt.Color(0, 0, 0));
        cmbMesin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbMesin.setName("cmbMesin"); // NOI18N
        FormInput.add(cmbMesin);
        cmbMesin.setBounds(325, 206, 65, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("4. Pulse Oximeter Sudah Terpasang dan Berfungsi :");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 234, 320, 23);

        cmbPulse.setForeground(new java.awt.Color(0, 0, 0));
        cmbPulse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbPulse.setName("cmbPulse"); // NOI18N
        FormInput.add(cmbPulse);
        cmbPulse.setBounds(325, 234, 65, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("5. Apakah Pasien Mempunyai Riwayat Alergi :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(0, 262, 320, 23);

        cmbApakahPasien.setForeground(new java.awt.Color(0, 0, 0));
        cmbApakahPasien.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbApakahPasien.setName("cmbApakahPasien"); // NOI18N
        FormInput.add(cmbApakahPasien);
        cmbApakahPasien.setBounds(325, 262, 65, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("6. Kesulitan Bernafas Resiko Aspirasi ? :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(0, 290, 320, 23);

        cmbKesulitan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesulitan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKesulitan.setName("cmbKesulitan"); // NOI18N
        FormInput.add(cmbKesulitan);
        cmbKesulitan.setBounds(325, 290, 65, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("7. Dan Menggunakan Peralatan Bantuan ? :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(0, 318, 320, 23);

        cmbDan.setForeground(new java.awt.Color(0, 0, 0));
        cmbDan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbDan.setName("cmbDan"); // NOI18N
        FormInput.add(cmbDan);
        cmbDan.setBounds(325, 318, 65, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("8. Resiko Kehilangan Darah :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(0, 346, 320, 23);

        cmbResiko.setForeground(new java.awt.Color(0, 0, 0));
        cmbResiko.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbResiko.setName("cmbResiko"); // NOI18N
        FormInput.add(cmbResiko);
        cmbResiko.setBounds(325, 346, 65, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("a. 500 ml (7 ml/kg 88) Pada Anak ? :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(390, 346, 190, 23);

        cmb500.setForeground(new java.awt.Color(0, 0, 0));
        cmb500.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmb500.setName("cmb500"); // NOI18N
        FormInput.add(cmb500);
        cmb500.setBounds(585, 346, 65, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("9. Dua Akses Intravensi Akses Sentral Dan :");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(0, 374, 320, 23);

        cmbDua.setForeground(new java.awt.Color(0, 0, 0));
        cmbDua.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbDua.setName("cmbDua"); // NOI18N
        FormInput.add(cmbDua);
        cmbDua.setBounds(325, 374, 65, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("10. Rencana Terapi Cairan ? :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(0, 402, 320, 23);

        cmbRencana.setForeground(new java.awt.Color(0, 0, 0));
        cmbRencana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbRencana.setName("cmbRencana"); // NOI18N
        FormInput.add(cmbRencana);
        cmbRencana.setBounds(325, 402, 65, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("THE TIME OUT (Pukul) :");
        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 486, 140, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(145, 486, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(196, 486, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(247, 486, 45, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel97.setText("Dilakukan sebelum insisi kulit, diisi oleh perawat, dokter anestesi dan operator");
        jLabel97.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(300, 486, 460, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("1. Konfirmasi Anggota Tim Memperkenalkan Nama Dan Perannya Masing-Masing :");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(0, 514, 430, 23);

        cmbKonfirmasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKonfirmasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbKonfirmasi.setName("cmbKonfirmasi"); // NOI18N
        FormInput.add(cmbKonfirmasi);
        cmbKonfirmasi.setBounds(435, 514, 65, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("2. Dokter Bedah, Dokter Anestesi Dan Perawat Melakukan Konfirmasi Secara Verbal :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(0, 542, 430, 23);

        cmbNama.setForeground(new java.awt.Color(0, 0, 0));
        cmbNama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbNama.setName("cmbNama"); // NOI18N
        FormInput.add(cmbNama);
        cmbNama.setBounds(115, 570, 65, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("- Nama Pasien :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(0, 570, 110, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("- Prosedur :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(180, 570, 70, 23);

        cmbProsedur2.setForeground(new java.awt.Color(0, 0, 0));
        cmbProsedur2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbProsedur2.setName("cmbProsedur2"); // NOI18N
        FormInput.add(cmbProsedur2);
        cmbProsedur2.setBounds(255, 570, 65, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("- Lokasi Dimana Insisi Akan Dibuat :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(320, 570, 190, 23);

        cmbLokasiDimana.setForeground(new java.awt.Color(0, 0, 0));
        cmbLokasiDimana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbLokasiDimana.setName("cmbLokasiDimana"); // NOI18N
        FormInput.add(cmbLokasiDimana);
        cmbLokasiDimana.setBounds(515, 570, 65, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("3. Apakah Antibiotik Proflaksis Sudah Diberikan 30 Menit Sebelumnya :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(0, 598, 430, 23);

        cmbApakahAntibiotik.setForeground(new java.awt.Color(0, 0, 0));
        cmbApakahAntibiotik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbApakahAntibiotik.setName("cmbApakahAntibiotik"); // NOI18N
        FormInput.add(cmbApakahAntibiotik);
        cmbApakahAntibiotik.setBounds(435, 598, 65, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("- Nama Antibiotik Yang Diberikan :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(0, 626, 270, 23);

        TnmAntibiotik.setForeground(new java.awt.Color(0, 0, 0));
        TnmAntibiotik.setName("TnmAntibiotik"); // NOI18N
        TnmAntibiotik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmAntibiotikKeyPressed(evt);
            }
        });
        FormInput.add(TnmAntibiotik);
        TnmAntibiotik.setBounds(275, 626, 385, 23);

        cmbNamaAntibiotik.setForeground(new java.awt.Color(0, 0, 0));
        cmbNamaAntibiotik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbNamaAntibiotik.setName("cmbNamaAntibiotik"); // NOI18N
        FormInput.add(cmbNamaAntibiotik);
        cmbNamaAntibiotik.setBounds(665, 626, 65, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("- Dosis Antibiotik Yang Diberikan :");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(0, 654, 270, 23);

        TdosisAntibiotik.setForeground(new java.awt.Color(0, 0, 0));
        TdosisAntibiotik.setName("TdosisAntibiotik"); // NOI18N
        TdosisAntibiotik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdosisAntibiotikKeyPressed(evt);
            }
        });
        FormInput.add(TdosisAntibiotik);
        TdosisAntibiotik.setBounds(275, 654, 385, 23);

        cmbDosisAntibiotik.setForeground(new java.awt.Color(0, 0, 0));
        cmbDosisAntibiotik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbDosisAntibiotik.setName("cmbDosisAntibiotik"); // NOI18N
        FormInput.add(cmbDosisAntibiotik);
        cmbDosisAntibiotik.setBounds(665, 654, 65, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("4. Antisipasi Kejadian Kritis :");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(0, 682, 160, 23);

        Tantisipasi.setForeground(new java.awt.Color(0, 0, 0));
        Tantisipasi.setName("Tantisipasi"); // NOI18N
        Tantisipasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TantisipasiKeyPressed(evt);
            }
        });
        FormInput.add(Tantisipasi);
        Tantisipasi.setBounds(165, 682, 623, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("a. Review dokter bedah langkah apa yang akan dilakukan bila kondisi kritis atau kejadian yang tidak diharapkan, lamanya operasi, antisipasi kehilangan darah :");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(0, 710, 790, 23);

        scrollPane13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane13.setName("scrollPane13"); // NOI18N

        TreviewA.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TreviewA.setColumns(20);
        TreviewA.setRows(5);
        TreviewA.setName("TreviewA"); // NOI18N
        TreviewA.setPreferredSize(new java.awt.Dimension(162, 1500));
        TreviewA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreviewAKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(TreviewA);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(40, 738, 750, 80);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("b. Review tim anestesi apakah ada hal khusus yang perlu diperhatikan pada pasien :");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(0, 823, 790, 23);

        scrollPane14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane14.setName("scrollPane14"); // NOI18N

        TreviewB.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TreviewB.setColumns(20);
        TreviewB.setRows(5);
        TreviewB.setName("TreviewB"); // NOI18N
        TreviewB.setPreferredSize(new java.awt.Dimension(162, 1500));
        TreviewB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreviewBKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(TreviewB);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(40, 848, 750, 80);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("Jika Diperlukan CVC, Kapan Akan Dipasang :");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(0, 933, 240, 23);

        Tjika.setForeground(new java.awt.Color(0, 0, 0));
        Tjika.setName("Tjika"); // NOI18N
        Tjika.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjikaKeyPressed(evt);
            }
        });
        FormInput.add(Tjika);
        Tjika.setBounds(245, 933, 545, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("Review tim perawat apakah peralatan sudah steril, adakah alat-alat yang perlu diperhatikan khusus atau dalam masalah :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(0, 961, 790, 23);

        scrollPane15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane15.setName("scrollPane15"); // NOI18N

        TreviewC.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TreviewC.setColumns(20);
        TreviewC.setRows(5);
        TreviewC.setName("TreviewC"); // NOI18N
        TreviewC.setPreferredSize(new java.awt.Dimension(162, 1500));
        TreviewC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreviewCKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(TreviewC);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(40, 989, 750, 80);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("5. Apakah Foto Rontgen / CT Scan Dan MRI Telah Ditayangkan :");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(0, 1073, 430, 23);

        cmbApakahFoto.setForeground(new java.awt.Color(0, 0, 0));
        cmbApakahFoto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbApakahFoto.setName("cmbApakahFoto"); // NOI18N
        FormInput.add(cmbApakahFoto);
        cmbApakahFoto.setBounds(435, 1073, 65, 23);

        label22.setForeground(new java.awt.Color(0, 0, 0));
        label22.setText("Perawat Sirkuit :");
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label22);
        label22.setBounds(0, 1157, 140, 23);

        TnipPerawatSirkuit1.setEditable(false);
        TnipPerawatSirkuit1.setForeground(new java.awt.Color(0, 0, 0));
        TnipPerawatSirkuit1.setName("TnipPerawatSirkuit1"); // NOI18N
        TnipPerawatSirkuit1.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipPerawatSirkuit1);
        TnipPerawatSirkuit1.setBounds(145, 1157, 150, 23);

        TnmPerawatSirkuit1.setEditable(false);
        TnmPerawatSirkuit1.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawatSirkuit1.setName("TnmPerawatSirkuit1"); // NOI18N
        TnmPerawatSirkuit1.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmPerawatSirkuit1);
        TnmPerawatSirkuit1.setBounds(298, 1157, 360, 23);

        BtnPerawatSirkuit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPerawatSirkuit1.setMnemonic('2');
        BtnPerawatSirkuit1.setToolTipText("Alt+2");
        BtnPerawatSirkuit1.setName("BtnPerawatSirkuit1"); // NOI18N
        BtnPerawatSirkuit1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPerawatSirkuit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerawatSirkuit1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnPerawatSirkuit1);
        BtnPerawatSirkuit1.setBounds(660, 1157, 28, 23);

        chkSayaSirkuit1.setBackground(new java.awt.Color(242, 242, 242));
        chkSayaSirkuit1.setForeground(new java.awt.Color(0, 0, 0));
        chkSayaSirkuit1.setText("Saya Sendiri");
        chkSayaSirkuit1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSayaSirkuit1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSayaSirkuit1.setName("chkSayaSirkuit1"); // NOI18N
        chkSayaSirkuit1.setOpaque(false);
        chkSayaSirkuit1.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSayaSirkuit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaSirkuit1ActionPerformed(evt);
            }
        });
        FormInput.add(chkSayaSirkuit1);
        chkSayaSirkuit1.setBounds(695, 1157, 90, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("THE SIGN OUT (Pukul) :");
        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 1185, 140, 23);

        cmbJam3.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam3.setName("cmbJam3"); // NOI18N
        cmbJam3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam3MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam3);
        cmbJam3.setBounds(145, 1185, 45, 23);

        cmbMnt3.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt3.setName("cmbMnt3"); // NOI18N
        cmbMnt3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt3MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt3);
        cmbMnt3.setBounds(196, 1185, 45, 23);

        cmbDtk3.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk3.setName("cmbDtk3"); // NOI18N
        cmbDtk3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk3MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk3);
        cmbDtk3.setBounds(247, 1185, 45, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("Dilakukan sebelum pasien meninggalkan OK, diisi oleh perawat, dokter anestesi dan operator");
        jLabel112.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(300, 1185, 460, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("1. Perawat Melakukan Konfirmasi Secara Verbal dengan Tim :");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(0, 1213, 450, 23);

        cmbNamaProsedur.setForeground(new java.awt.Color(0, 0, 0));
        cmbNamaProsedur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbNamaProsedur.setName("cmbNamaProsedur"); // NOI18N
        FormInput.add(cmbNamaProsedur);
        cmbNamaProsedur.setBounds(455, 1241, 65, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("a. Nama Prosedur Tindakan Telah Dicatat :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(0, 1241, 450, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("b. Instrumen Kasa, Dan Jarum Telah Dihitung Dengan Benar :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(0, 1269, 450, 23);

        cmbInstrumen.setForeground(new java.awt.Color(0, 0, 0));
        cmbInstrumen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbInstrumen.setName("cmbInstrumen"); // NOI18N
        FormInput.add(cmbInstrumen);
        cmbInstrumen.setBounds(455, 1269, 65, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("c. Spesimen Telah Diberi Label (Termasuk Nama Pasien & Asal Jaringan Specimen) :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(0, 1297, 450, 23);

        cmbSpesimen.setForeground(new java.awt.Color(0, 0, 0));
        cmbSpesimen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbSpesimen.setName("cmbSpesimen"); // NOI18N
        FormInput.add(cmbSpesimen);
        cmbSpesimen.setBounds(455, 1297, 65, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("d. Adakah Masalah Dengan Peralatan Selama Operasi :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(0, 1325, 450, 23);

        cmbAdakah.setForeground(new java.awt.Color(0, 0, 0));
        cmbAdakah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmbAdakah.setName("cmbAdakah"); // NOI18N
        FormInput.add(cmbAdakah);
        cmbAdakah.setBounds(455, 1325, 65, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("2. Operator dokter bedah, dokter anestesi dan perawat melakukan review masalah  ");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(0, 1353, 450, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("utama apa yang harus diperhatikan untuk penyembuhan & manajemen pasien  ");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(0, 1371, 450, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("selanjutnya :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(0, 1389, 450, 23);

        cmb2Operator.setForeground(new java.awt.Color(0, 0, 0));
        cmb2Operator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sudah", "Belum" }));
        cmb2Operator.setName("cmb2Operator"); // NOI18N
        FormInput.add(cmb2Operator);
        cmb2Operator.setBounds(455, 1389, 65, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("Hal Yang Harus Diperhatikan :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(0, 1417, 175, 23);

        scrollPane16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane16.setName("scrollPane16"); // NOI18N

        ThalYang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ThalYang.setColumns(20);
        ThalYang.setRows(5);
        ThalYang.setName("ThalYang"); // NOI18N
        ThalYang.setPreferredSize(new java.awt.Dimension(162, 1500));
        ThalYang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThalYangKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(ThalYang);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(180, 1417, 610, 80);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("Tgl. Tindakan :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(0, 1502, 175, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("Verifikasi :");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(270, 1502, 60, 23);

        Tverifikasi.setForeground(new java.awt.Color(0, 0, 0));
        Tverifikasi.setName("Tverifikasi"); // NOI18N
        Tverifikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TverifikasiKeyPressed(evt);
            }
        });
        FormInput.add(Tverifikasi);
        Tverifikasi.setBounds(335, 1502, 455, 23);

        label23.setForeground(new java.awt.Color(0, 0, 0));
        label23.setText("Perawat Sirkuit :");
        label23.setName("label23"); // NOI18N
        label23.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label23);
        label23.setBounds(0, 1530, 140, 23);

        TnipPerawatSirkuit2.setEditable(false);
        TnipPerawatSirkuit2.setForeground(new java.awt.Color(0, 0, 0));
        TnipPerawatSirkuit2.setName("TnipPerawatSirkuit2"); // NOI18N
        TnipPerawatSirkuit2.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipPerawatSirkuit2);
        TnipPerawatSirkuit2.setBounds(145, 1530, 150, 23);

        TnmPerawatSirkuit2.setEditable(false);
        TnmPerawatSirkuit2.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawatSirkuit2.setName("TnmPerawatSirkuit2"); // NOI18N
        TnmPerawatSirkuit2.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmPerawatSirkuit2);
        TnmPerawatSirkuit2.setBounds(298, 1530, 360, 23);

        BtnPerawatSirkuit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPerawatSirkuit2.setMnemonic('2');
        BtnPerawatSirkuit2.setToolTipText("Alt+2");
        BtnPerawatSirkuit2.setName("BtnPerawatSirkuit2"); // NOI18N
        BtnPerawatSirkuit2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPerawatSirkuit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerawatSirkuit2ActionPerformed(evt);
            }
        });
        FormInput.add(BtnPerawatSirkuit2);
        BtnPerawatSirkuit2.setBounds(660, 1530, 28, 23);

        chkSayaSirkuit2.setBackground(new java.awt.Color(242, 242, 242));
        chkSayaSirkuit2.setForeground(new java.awt.Color(0, 0, 0));
        chkSayaSirkuit2.setText("Saya Sendiri");
        chkSayaSirkuit2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSayaSirkuit2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSayaSirkuit2.setName("chkSayaSirkuit2"); // NOI18N
        chkSayaSirkuit2.setOpaque(false);
        chkSayaSirkuit2.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSayaSirkuit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaSirkuit2ActionPerformed(evt);
            }
        });
        FormInput.add(chkSayaSirkuit2);
        chkSayaSirkuit2.setBounds(695, 1530, 90, 23);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Dokter Anastesi :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label17);
        label17.setBounds(0, 1558, 140, 23);

        TnipDokterAnes3.setEditable(false);
        TnipDokterAnes3.setForeground(new java.awt.Color(0, 0, 0));
        TnipDokterAnes3.setName("TnipDokterAnes3"); // NOI18N
        TnipDokterAnes3.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipDokterAnes3);
        TnipDokterAnes3.setBounds(145, 1558, 150, 23);

        TnmDokterAnes3.setEditable(false);
        TnmDokterAnes3.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokterAnes3.setName("TnmDokterAnes3"); // NOI18N
        TnmDokterAnes3.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmDokterAnes3);
        TnmDokterAnes3.setBounds(298, 1558, 360, 23);

        BtnDokterAnes3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokterAnes3.setMnemonic('2');
        BtnDokterAnes3.setToolTipText("Alt+2");
        BtnDokterAnes3.setName("BtnDokterAnes3"); // NOI18N
        BtnDokterAnes3.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokterAnes3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterAnes3ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokterAnes3);
        BtnDokterAnes3.setBounds(660, 1558, 28, 23);

        Scroll1.setViewportView(FormInput);

        internalFrame1.add(Scroll1, java.awt.BorderLayout.CENTER);

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

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setText("Cetak Dalam Bentuk :");
        jLabel124.setName("jLabel124"); // NOI18N
        jLabel124.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel124);

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

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Checklist Keselamatan Operasi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(700, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbCeklis.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbCeklis.setName("tbCeklis"); // NOI18N
        tbCeklis.getTableHeader().setReorderingAllowed(false);
        tbCeklis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCeklisMouseClicked(evt);
            }
        });
        tbCeklis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCeklisKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbCeklis);

        PanelInput1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 86));
        panelGlass11.setLayout(new java.awt.BorderLayout());

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Checklist :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-01-2026" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-01-2026" }));
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

        internalFrame1.add(PanelInput1, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            wktSimpan = Sequel.cariIsi("select now()");
            try {
                Sequel.simpanReplaceInto("ceklis_keselamatan_operasi1", "'" + TNoRw.getText() + "','" + TrgRawat.getText() + "',"
                        + "'" + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "',"
                        + "'" + cmbIdentifikasi.getSelectedItem().toString() + "','" + cmbLokasi1.getSelectedItem().toString() + "',"
                        + "'" + cmbProsedur1.getSelectedItem().toString() + "','" + cmbSurat.getSelectedItem().toString() + "',"
                        + "'" + cmbLokasi2.getSelectedItem().toString() + "','" + cmbMesin.getSelectedItem().toString() + "',"
                        + "'" + cmbPulse.getSelectedItem().toString() + "','" + cmbApakahPasien.getSelectedItem().toString() + "',"
                        + "'" + cmbKesulitan.getSelectedItem().toString() + "','" + cmbDan.getSelectedItem().toString() + "',"
                        + "'" + cmbResiko.getSelectedItem().toString() + "','" + cmb500.getSelectedItem().toString() + "',"
                        + "'" + cmbDua.getSelectedItem().toString() + "','" + cmbRencana.getSelectedItem().toString() + "',"
                        + "'" + TnipPerawatSir.getText() + "','" + TnipDokterAnes1.getText() + "','" + wktSimpan + "'", "No. Rawat");

                Sequel.simpanReplaceInto("ceklis_keselamatan_operasi2", "'" + TNoRw.getText() + "',"
                        + "'" + cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem() + "',"
                        + "'" + cmbKonfirmasi.getSelectedItem().toString() + "','" + cmbNama.getSelectedItem().toString() + "',"
                        + "'" + cmbProsedur2.getSelectedItem().toString() + "','" + cmbLokasiDimana.getSelectedItem().toString() + "',"
                        + "'" + cmbApakahAntibiotik.getSelectedItem().toString() + "','" + cmbNamaAntibiotik.getSelectedItem().toString() + "',"
                        + "'" + cmbDosisAntibiotik.getSelectedItem().toString() + "','" + TnmAntibiotik.getText() + "','" + TdosisAntibiotik.getText() + "',"
                        + "'" + Tantisipasi.getText() + "','" + TreviewA.getText() + "','" + TreviewB.getText() + "','" + Tjika.getText() + "',"
                        + "'" + TreviewC.getText() + "','" + cmbApakahFoto.getSelectedItem().toString() + "','" + TnipOperator.getText() + "',"
                        + "'" + TnipDokterAnes2.getText() + "','" + TnipPerawatSirkuit1.getText() + "','" + wktSimpan + "'", "The Time Out");

                Sequel.simpanReplaceInto("ceklis_keselamatan_operasi3", "'" + TNoRw.getText() + "',"
                        + "'" + cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem() + "',"
                        + "'" + cmbNamaProsedur.getSelectedItem().toString() + "','" + cmbInstrumen.getSelectedItem().toString() + "',"
                        + "'" + cmbSpesimen.getSelectedItem().toString() + "','" + cmbAdakah.getSelectedItem().toString() + "',"
                        + "'" + cmb2Operator.getSelectedItem().toString() + "','" + ThalYang.getText() + "',"
                        + "'" + Valid.SetTgl(TtglTindakan.getSelectedItem() + "") + "','" + Tverifikasi.getText() + "','" + TnipPerawatSirkuit2.getText() + "',"
                        + "'" + TnipDokterAnes3.getText() + "','" + wktSimpan + "'", "The Sign Out");

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Checklist Keselamatan Operasi", "Simpan");
                TCari.setText(TNoRw.getText());
                tampil();
                if (Sequel.cariInteger("select count(-1) from ceklis_keselamatan_operasi1 where no_rawat='" + TNoRw.getText() + "'") > 0) {
                    emptTeks();
                }
            } catch (Exception e) {
                System.out.println("Simpan Checklist Keselamatan Operasi : " + e);
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
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
            if (tbCeklis.getSelectedRow() > -1) {
                try {
                    if (Sequel.mengedittf("ceklis_keselamatan_operasi1", "waktu_simpan=?", "the_sign_in=?, identifikasi_gelang=?, lokasi_operasi=?, "
                            + "prosedur=?, surat_ijin_operasi=?, lokasi_operasi_diberi_tanda=?, mesin_sdh_dicek_lengkap=?, "
                            + "pulse_oximeter=?, pasien_punya_riwayat_alergi=?, kesulitan_betnafas=?, menggunakan_peralatan_bantuan=?, resiko_kehilangan_darah=?, "
                            + "500ml_pada_anak=?, dua_akses_intravensi=?, rencana_terapi_cairan=?, nip_perawat=?, nip_dokter=?", 18, new String[]{
                                cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                                cmbIdentifikasi.getSelectedItem().toString(), cmbLokasi1.getSelectedItem().toString(), cmbProsedur1.getSelectedItem().toString(),
                                cmbSurat.getSelectedItem().toString(), cmbLokasi2.getSelectedItem().toString(), cmbMesin.getSelectedItem().toString(),
                                cmbPulse.getSelectedItem().toString(), cmbApakahPasien.getSelectedItem().toString(), cmbKesulitan.getSelectedItem().toString(),
                                cmbDan.getSelectedItem().toString(), cmbResiko.getSelectedItem().toString(), cmb500.getSelectedItem().toString(), cmbDua.getSelectedItem().toString(),
                                cmbRencana.getSelectedItem().toString(), TnipPerawatSir.getText(), TnipDokterAnes1.getText(),
                                tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 0).toString()
                            }) == true) {

                        Sequel.mengedit("ceklis_keselamatan_operasi2", "waktu_simpan='" + tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 0).toString() + "'",
                                "the_time_out='" + cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem() + "', "
                                + "konfirmasi_anggota_tim='" + cmbKonfirmasi.getSelectedItem().toString() + "', "
                                + "nama_pasien='" + cmbNama.getSelectedItem().toString() + "', "
                                + "prosedur='" + cmbProsedur2.getSelectedItem().toString() + "', "
                                + "lokasi_insisi_dibuat='" + cmbLokasiDimana.getSelectedItem().toString() + "', "
                                + "antibiotik_diberikan='" + cmbApakahAntibiotik.getSelectedItem().toString() + "', "
                                + "cek_nama_antibiotik='" + cmbNamaAntibiotik.getSelectedItem().toString() + "', "
                                + "cek_dosis_antibiotik='" + cmbDosisAntibiotik.getSelectedItem().toString() + "', "
                                + "nama_antibiotik='" + TnmAntibiotik.getText() + "', "
                                + "dosis_antibiotik='" + TdosisAntibiotik.getText() + "', "
                                + "antisipasi_kejadian_kritis='" + Tantisipasi.getText() + "', "
                                + "review_dokter='" + TreviewA.getText() + "', "
                                + "review_tim='" + TreviewB.getText() + "', "
                                + "cvc_dipasang='" + Tjika.getText() + "', "
                                + "review_tim_perawat='" + TreviewC.getText() + "', "
                                + "foto_rongsen_ditayangkan='" + cmbApakahFoto.getSelectedItem().toString() + "', "
                                + "nip_operator='" + TnipOperator.getText() + "', "
                                + "nip_dokter='" + TnipDokterAnes2.getText() + "', "
                                + "nip_perawat='" + TnipPerawatSirkuit1.getText() + "'");
                        
                        Sequel.mengedit("ceklis_keselamatan_operasi3", "waktu_simpan='" + tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 0).toString() + "'",
                                "the_sign_out='" + cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem() + "', "
                                + "nama_prosedur='" + cmbNamaProsedur.getSelectedItem().toString() + "', "
                                + "instrumen_kasa='" + cmbInstrumen.getSelectedItem().toString() + "', "
                                + "spesimen='" + cmbSpesimen.getSelectedItem().toString() + "', "
                                + "adakah_masalah='" + cmbAdakah.getSelectedItem().toString() + "', "
                                + "operator_dokter_bedah='" + cmb2Operator.getSelectedItem().toString() + "', "
                                + "hal_yang_diperhatikan='" + ThalYang.getText() + "', "
                                + "tgl_tindakan='" + Valid.SetTgl(TtglTindakan.getSelectedItem() + "") + "', "
                                + "verifikasi='" + Tverifikasi.getText() + "', "
                                + "nip_perawat='" + TnipPerawatSirkuit2.getText() + "', "
                                + "nip_dokter='" + TnipDokterAnes3.getText() + "'");
                        
                        Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Checklist Keselamatan Operasi", "Ganti");
                        TCari.setText(TNoRw.getText());
                        tampil();
                        emptTeks();
                    }
                } catch (Exception e) {
                    System.out.println("Ganti Checklist Keselamatan Operasi : " + e);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                tbCeklis.requestFocus();
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

    private void tbCeklisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCeklisMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbCeklisMouseClicked

    private void tbCeklisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCeklisKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbCeklisKeyPressed

    private void BtnDokterAnes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterAnes1ActionPerformed
        pilihDokter = 0;
        pilihDokter = 1;
        akses.setform("RMCeklisKeselamatanOperasi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterAnes1ActionPerformed

    private void BtnDokterAnes2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterAnes2ActionPerformed
        pilihDokter = 0;
        pilihDokter = 2;
        akses.setform("RMCeklisKeselamatanOperasi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterAnes2ActionPerformed

    private void BtnPerawatSirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatSirActionPerformed
        pilihPerawat = 0;
        pilihPerawat = 1;
        akses.setform("RMCeklisKeselamatanOperasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatSirActionPerformed

    private void BtnOperatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOperatorActionPerformed
        pilihPerawat = 0;
        pilihPerawat = 2;
        akses.setform("RMCeklisKeselamatanOperasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnOperatorActionPerformed

    private void chkSayaSirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaSirActionPerformed
        if (chkSayaSir.isSelected() == true) {
            if (akses.getadmin() == true) {
                TnipPerawatSir.setText("-");
                TnmPerawatSir.setText("-");
            } else {
                TnipPerawatSir.setText(akses.getkode());
                TnmPerawatSir.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipPerawatSir.getText() + "'"));
            }
        } else {
            TnipPerawatSir.setText("-");
            TnmPerawatSir.setText("-");
        }
    }//GEN-LAST:event_chkSayaSirActionPerformed

    private void chkSayaOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaOpActionPerformed
        if (chkSayaOp.isSelected() == true) {
            if (akses.getadmin() == true) {
                TnipOperator.setText("-");
                TnmOperator.setText("-");
            } else {
                TnipOperator.setText(akses.getkode());
                TnmOperator.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipOperator.getText() + "'"));
            }
        } else {
            TnipOperator.setText("-");
            TnmOperator.setText("-");
        }
    }//GEN-LAST:event_chkSayaOpActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbCeklis.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from ceklis_keselamatan_operasi1 where waktu_simpan=?", 1, new String[]{
                    tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 0).toString()
                }) == true) {
                    Sequel.meghapus("ceklis_keselamatan_operasi2", "waktu_simpan", tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 0).toString());
                    Sequel.meghapus("ceklis_keselamatan_operasi3", "waktu_simpan", tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 0).toString());
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
            tbCeklis.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbCeklis.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo_rs_warna_miring1 from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
            param.put("theSignIn", cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + " Wita");
            param.put("identifikasi", cmbIdentifikasi.getSelectedItem().toString());
            param.put("lokasiOperasi1", cmbLokasi1.getSelectedItem().toString());
            param.put("prosedur1", cmbProsedur1.getSelectedItem().toString());
            param.put("surat", cmbSurat.getSelectedItem().toString());
            param.put("lokasiOperasi2", cmbLokasi2.getSelectedItem().toString());
            param.put("mesin", cmbMesin.getSelectedItem().toString());
            param.put("pulse", cmbPulse.getSelectedItem().toString());
            param.put("apakahPasien", cmbApakahPasien.getSelectedItem().toString());
            param.put("kesulitan", cmbKesulitan.getSelectedItem().toString());
            param.put("dan", cmbDan.getSelectedItem().toString());
            param.put("resiko", cmbResiko.getSelectedItem().toString());
            param.put("500", cmb500.getSelectedItem().toString());
            param.put("dua", cmbDua.getSelectedItem().toString());
            param.put("rencana", cmbRencana.getSelectedItem().toString());
            param.put("perawatSirkuler", "(" + TnmPerawatSir.getText() + ")");
            param.put("dokterAnestesi1", "(" + TnmDokterAnes1.getText() + ")");
            
            param.put("theTimeOut", cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + " Wita");
            param.put("konfirmasi", cmbKonfirmasi.getSelectedItem().toString());
            param.put("namaPasien", cmbNama.getSelectedItem().toString());
            param.put("prosedur2", cmbProsedur2.getSelectedItem().toString());
            param.put("lokasiDimana", cmbLokasiDimana.getSelectedItem().toString());
            param.put("apakahAntibiotik", cmbApakahAntibiotik.getSelectedItem().toString());
            param.put("cekNamaAntibiotik", cmbNamaAntibiotik.getSelectedItem().toString());
            param.put("cekDosisAntibiotik", cmbDosisAntibiotik.getSelectedItem().toString());            
            if (TnmAntibiotik.getText().equals("")) {
                param.put("nmAntibiotik", "- Nama Antibiotik Yang Diberikan .........");
            } else {
                param.put("nmAntibiotik", "- Nama Antibiotik Yang Diberikan : " + TnmAntibiotik.getText());
            }            
            if (TdosisAntibiotik.getText().equals("")) {
                param.put("dosisAntibiotik", "- Dosis Antibiotik Yang Diberikan .........");
            } else {
                param.put("dosisAntibiotik", "- Dosis Antibiotik Yang Diberikan : " + TdosisAntibiotik.getText());
            }            
            param.put("antisipasi", Tantisipasi.getText());
            param.put("reviewDokter", TreviewA.getText());
            param.put("reviewTimAnes", TreviewB.getText());
            param.put("cvc", Tjika.getText());
            param.put("reviewTimPerawat", TreviewC.getText());
            param.put("apakahFoto", cmbApakahFoto.getSelectedItem().toString());
            param.put("operator", "(" + TnmOperator.getText() + ")");
            param.put("dokterAnestesi2", "(" + TnmDokterAnes2.getText() + ")");
            param.put("perawatSirkuit1", "(" + TnmPerawatSirkuit1.getText() + ")");
            
            param.put("theSignOut", cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + " Wita");
            param.put("namaProsedur", cmbNamaProsedur.getSelectedItem().toString());
            param.put("instrumen", cmbInstrumen.getSelectedItem().toString());
            param.put("spesimen", cmbSpesimen.getSelectedItem().toString());
            param.put("adakah", cmbAdakah.getSelectedItem().toString());
            param.put("operatorDokterBedah", cmb2Operator.getSelectedItem().toString());
            param.put("halYang", ThalYang.getText());
            param.put("tglTindakan", Valid.SetTglINDONESIA(Valid.SetTgl(TtglTindakan.getSelectedItem() + "")));
            param.put("verifikasi", Tverifikasi.getText());
            param.put("perawatSirkuit2", "(" + TnmPerawatSirkuit2.getText() + ")");
            param.put("dokterAnestesi3", "(" + TnmDokterAnes3.getText() + ")");

            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isiSirkuler = "", isiDokterAnes1 = "", isiOperator = "", isiDokterAnes2 = "", isiSirkuit1 = "",
                        isiSirkuit2 = "", isiDokterAnes3 = "", tgl1 = "", jam1 = "", tgl2 = "", jam2 = "", tgl3 = "", jam3 = "";

                tgl1 = Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from ceklis_keselamatan_operasi1 where no_rawat='" + TNoRw.getText() + "'");
                jam1 = Sequel.cariIsi("select time(waktu_simpan) from ceklis_keselamatan_operasi1 where no_rawat='" + TNoRw.getText() + "'");
                tgl2 = Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from ceklis_keselamatan_operasi2 where no_rawat='" + TNoRw.getText() + "'");
                jam2 = Sequel.cariIsi("select time(waktu_simpan) from ceklis_keselamatan_operasi2 where no_rawat='" + TNoRw.getText() + "'");
                tgl3 = Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from ceklis_keselamatan_operasi3 where no_rawat='" + TNoRw.getText() + "'");
                jam3 = Sequel.cariIsi("select time(waktu_simpan) from ceklis_keselamatan_operasi3 where no_rawat='" + TNoRw.getText() + "'");
                
                if ((TnipPerawatSir.getText().equals("") || TnipPerawatSir.getText().equals("-") || TnipPerawatSir.getText().equals("--")) 
                        && (TnipDokterAnes1.getText().equals("") || TnipDokterAnes1.getText().equals("-") || TnipDokterAnes1.getText().equals("--")) 
                        && (TnipOperator.getText().equals("") || TnipOperator.getText().equals("-") || TnipOperator.getText().equals("--")) 
                        && (TnipDokterAnes2.getText().equals("") || TnipDokterAnes2.getText().equals("-") || TnipDokterAnes2.getText().equals("--")) 
                        && (TnipPerawatSirkuit1.getText().equals("") || TnipPerawatSirkuit1.getText().equals("-") || TnipPerawatSirkuit1.getText().equals("--")) 
                        && (TnipPerawatSirkuit2.getText().equals("") || TnipPerawatSirkuit2.getText().equals("-") || TnipPerawatSirkuit2.getText().equals("--"))
                        && (TnipDokterAnes3.getText().equals("") || TnipDokterAnes3.getText().equals("-") || TnipDokterAnes3.getText().equals("--"))) {
                    
                    Valid.MyReport("rptCeklisKeselamatanOperasi.jasper", "report", "::[ Lembar Checklist Keselamatan Operasi ]::",
                            "SELECT now() tanggal", param);
                    tampil();
                    emptTeks();
                } else {
                    param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));
                    //THE SIGN IN
                    if (TnipPerawatSir.getText().equals("") || TnipPerawatSir.getText().equals("-") || TnipPerawatSir.getText().equals("--")) {
                        param.put("lokasiQrSirkuler", "");
                    } else {
                        isiSirkuler = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                        "Checklist Keselamatan Operasi", TnmPerawatSir.getText() + " (Perawat Sirkuler)", tgl1, jam1) + "') from kalimat_tte where kode='001'");
                        Valid.cetakQrTte(isiSirkuler, Sequel.cariFolderTte(), "QRTteSirkuler.jpg", "select logo from setting");
                        param.put("lokasiQrSirkuler", Sequel.cariFolderTte() + File.separator + "QRTteSirkuler.jpg");
                    }
                    
                    if (TnipDokterAnes1.getText().equals("") || TnipDokterAnes1.getText().equals("-") || TnipDokterAnes1.getText().equals("--")) {
                        param.put("lokasiQrDokterAnestesi1", "");
                    } else {
                        isiDokterAnes1 = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                        "Checklist Keselamatan Operasi", TnmDokterAnes1.getText() + " (Dokter Anestesi)", tgl1, jam1) + "') from kalimat_tte where kode='001'");
                        Valid.cetakQrTte(isiDokterAnes1, Sequel.cariFolderTte(), "QRTteDokterAnes1.jpg", "select logo from setting");
                        param.put("lokasiQrDokterAnestesi1", Sequel.cariFolderTte() + File.separator + "QRTteDokterAnes1.jpg");
                    }
                    
                    //THE TIME OUT
                    if (TnipOperator.getText().equals("") || TnipOperator.getText().equals("-") || TnipOperator.getText().equals("--")) {
                        param.put("lokasiQrOperator", "");
                    } else {
                        isiOperator = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                        "Checklist Keselamatan Operasi", TnmOperator.getText() + " (Operator)", tgl1, jam1) + "') from kalimat_tte where kode='001'");
                        Valid.cetakQrTte(isiOperator, Sequel.cariFolderTte(), "QRTteOperator.jpg", "select logo from setting");
                        param.put("lokasiQrOperator", Sequel.cariFolderTte() + File.separator + "QRTteOperator.jpg");
                    }

                    if (TnipDokterAnes2.getText().equals("") || TnipDokterAnes2.getText().equals("-") || TnipDokterAnes2.getText().equals("--")) {
                        param.put("lokasiQrDokterAnestesi2", "");
                    } else {
                        isiDokterAnes2 = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                        "Checklist Keselamatan Operasi", TnmDokterAnes2.getText() + " (Dokter Anestesi)", tgl1, jam1) + "') from kalimat_tte where kode='001'");
                        Valid.cetakQrTte(isiDokterAnes2, Sequel.cariFolderTte(), "QRTteDokterAnes2.jpg", "select logo from setting");
                        param.put("lokasiQrDokterAnestesi2", Sequel.cariFolderTte() + File.separator + "QRTteDokterAnes2.jpg");
                    }
                    
                    if (TnipPerawatSirkuit1.getText().equals("") || TnipPerawatSirkuit1.getText().equals("-") || TnipPerawatSirkuit1.getText().equals("--")) {
                        param.put("lokasiQrSirkuit1", "");
                    } else {
                        isiSirkuit1 = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                        "Checklist Keselamatan Operasi", TnmPerawatSirkuit1.getText() + " (Perawat Sirkuit)", tgl1, jam1) + "') from kalimat_tte where kode='001'");
                        Valid.cetakQrTte(isiSirkuit1, Sequel.cariFolderTte(), "QRTteSirkuit1.jpg", "select logo from setting");
                        param.put("lokasiQrSirkuit1", Sequel.cariFolderTte() + File.separator + "QRTteSirkuit1.jpg");
                    }
                    
                    //THE SIGN OUT
                    if (TnipPerawatSirkuit2.getText().equals("") || TnipPerawatSirkuit2.getText().equals("-") || TnipPerawatSirkuit2.getText().equals("--")) {
                        param.put("lokasiQrSirkuit2", "");
                    } else {
                        isiSirkuit2 = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                        "Checklist Keselamatan Operasi", TnmPerawatSirkuit2.getText() + " (Perawat Sirkuit)", tgl1, jam1) + "') from kalimat_tte where kode='001'");
                        Valid.cetakQrTte(isiSirkuit2, Sequel.cariFolderTte(), "QRTteSirkuit2.jpg", "select logo from setting");
                        param.put("lokasiQrSirkuit2", Sequel.cariFolderTte() + File.separator + "QRTteSirkuit2.jpg");
                    }
                    
                    if (TnipDokterAnes3.getText().equals("") || TnipDokterAnes3.getText().equals("-") || TnipDokterAnes3.getText().equals("--")) {
                        param.put("lokasiQrDokterAnestesi3", "");
                    } else {
                        isiDokterAnes3 = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                        "Checklist Keselamatan Operasi", TnmDokterAnes3.getText() + " (Dokter Anestesi)", tgl1, jam1) + "') from kalimat_tte where kode='001'");
                        Valid.cetakQrTte(isiDokterAnes3, Sequel.cariFolderTte(), "QRTteDokterAnes3.jpg", "select logo from setting");
                        param.put("lokasiQrDokterAnestesi3", Sequel.cariFolderTte() + File.separator + "QRTteDokterAnes3.jpg");
                    }

                    Valid.MyReport("rptCeklisKeselamatanOperasiQr.jasper", "report", "::[ Lembar Checklist Keselamatan Operasi ]::",
                            "SELECT now() tanggal", param);
                    tampil();
                    emptTeks();
                    Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                }
            } else {
                Valid.MyReport("rptCeklisKeselamatanOperasi.jasper", "report", "::[ Lembar Checklist Keselamatan Operasi ]::",
                        "SELECT now() tanggal", param);
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel..!!!!");
            tbCeklis.requestFocus();
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

    private void TreviewAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreviewAKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TreviewB.requestFocus();
        }
    }//GEN-LAST:event_TreviewAKeyPressed

    private void TreviewBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreviewBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tjika.requestFocus();
        }
    }//GEN-LAST:event_TreviewBKeyPressed

    private void TreviewCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreviewCKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbApakahFoto.requestFocus();
        }
    }//GEN-LAST:event_TreviewCKeyPressed

    private void BtnPerawatSirkuit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatSirkuit1ActionPerformed
        pilihPerawat = 0;
        pilihPerawat = 3;
        akses.setform("RMCeklisKeselamatanOperasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatSirkuit1ActionPerformed

    private void chkSayaSirkuit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaSirkuit1ActionPerformed
        if (chkSayaSirkuit1.isSelected() == true) {
            if (akses.getadmin() == true) {
                TnipPerawatSirkuit1.setText("-");
                TnmPerawatSirkuit1.setText("-");
            } else {
                TnipPerawatSirkuit1.setText(akses.getkode());
                TnmPerawatSirkuit1.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipPerawatSirkuit1.getText() + "'"));
            }
        } else {
            TnipPerawatSirkuit1.setText("-");
            TnmPerawatSirkuit1.setText("-");
        }
    }//GEN-LAST:event_chkSayaSirkuit1ActionPerformed

    private void cmbJam3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam3MouseReleased
        AutoCompleteDecorator.decorate(cmbJam3);
    }//GEN-LAST:event_cmbJam3MouseReleased

    private void cmbMnt3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt3MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt3);
    }//GEN-LAST:event_cmbMnt3MouseReleased

    private void cmbDtk3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk3MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk3);
    }//GEN-LAST:event_cmbDtk3MouseReleased

    private void ThalYangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThalYangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TtglTindakan.requestFocus();
        }
    }//GEN-LAST:event_ThalYangKeyPressed

    private void BtnPerawatSirkuit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatSirkuit2ActionPerformed
        pilihPerawat = 0;
        pilihPerawat = 4;
        akses.setform("RMCeklisKeselamatanOperasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatSirkuit2ActionPerformed

    private void chkSayaSirkuit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaSirkuit2ActionPerformed
        if (chkSayaSirkuit2.isSelected() == true) {
            if (akses.getadmin() == true) {
                TnipPerawatSirkuit2.setText("-");
                TnmPerawatSirkuit2.setText("-");
            } else {
                TnipPerawatSirkuit2.setText(akses.getkode());
                TnmPerawatSirkuit2.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipPerawatSirkuit2.getText() + "'"));
            }
        } else {
            TnipPerawatSirkuit2.setText("-");
            TnmPerawatSirkuit2.setText("-");
        }
    }//GEN-LAST:event_chkSayaSirkuit2ActionPerformed

    private void BtnDokterAnes3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterAnes3ActionPerformed
        pilihDokter = 0;
        pilihDokter = 3;
        akses.setform("RMCeklisKeselamatanOperasi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterAnes3ActionPerformed

    private void TnmAntibiotikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmAntibiotikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbNamaAntibiotik.requestFocus();
        }
    }//GEN-LAST:event_TnmAntibiotikKeyPressed

    private void TdosisAntibiotikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdosisAntibiotikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbDosisAntibiotik.requestFocus();
        }
    }//GEN-LAST:event_TdosisAntibiotikKeyPressed

    private void TantisipasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TantisipasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TreviewA.requestFocus();
        }
    }//GEN-LAST:event_TantisipasiKeyPressed

    private void TjikaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjikaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TreviewC.requestFocus();
        }
    }//GEN-LAST:event_TjikaKeyPressed

    private void TverifikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TverifikasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnPerawatSirkuit2.requestFocus();
        }
    }//GEN-LAST:event_TverifikasiKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMCeklisKeselamatanOperasi dialog = new RMCeklisKeselamatanOperasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDokterAnes1;
    private widget.Button BtnDokterAnes2;
    private widget.Button BtnDokterAnes3;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnOperator;
    private widget.Button BtnPerawatSir;
    private widget.Button BtnPerawatSirkuit1;
    private widget.Button BtnPerawatSirkuit2;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tantisipasi;
    private widget.TextBox TdosisAntibiotik;
    private widget.TextArea ThalYang;
    private widget.TextBox Tjika;
    private widget.TextBox TnipDokterAnes1;
    private widget.TextBox TnipDokterAnes2;
    private widget.TextBox TnipDokterAnes3;
    private widget.TextBox TnipOperator;
    private widget.TextBox TnipPerawatSir;
    private widget.TextBox TnipPerawatSirkuit1;
    private widget.TextBox TnipPerawatSirkuit2;
    private widget.TextBox TnmAntibiotik;
    private widget.TextBox TnmDokterAnes1;
    private widget.TextBox TnmDokterAnes2;
    private widget.TextBox TnmDokterAnes3;
    private widget.TextBox TnmOperator;
    private widget.TextBox TnmPerawatSir;
    private widget.TextBox TnmPerawatSirkuit1;
    private widget.TextBox TnmPerawatSirkuit2;
    private widget.TextArea TreviewA;
    private widget.TextArea TreviewB;
    private widget.TextArea TreviewC;
    private widget.TextBox TrgRawat;
    private widget.Tanggal TtglTindakan;
    private widget.TextBox Tverifikasi;
    private widget.CekBox chkSayaOp;
    private widget.CekBox chkSayaSir;
    private widget.CekBox chkSayaSirkuit1;
    private widget.CekBox chkSayaSirkuit2;
    private widget.ComboBox cmb2Operator;
    private widget.ComboBox cmb500;
    private widget.ComboBox cmbAdakah;
    private widget.ComboBox cmbApakahAntibiotik;
    private widget.ComboBox cmbApakahFoto;
    private widget.ComboBox cmbApakahPasien;
    private widget.ComboBox cmbDan;
    private widget.ComboBox cmbDosisAntibiotik;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbDtk3;
    private widget.ComboBox cmbDua;
    private widget.ComboBox cmbIdentifikasi;
    private widget.ComboBox cmbInstrumen;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbJam3;
    private widget.ComboBox cmbKesulitan;
    private widget.ComboBox cmbKonfirmasi;
    private widget.ComboBox cmbLokasi1;
    private widget.ComboBox cmbLokasi2;
    private widget.ComboBox cmbLokasiDimana;
    private widget.ComboBox cmbMesin;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbMnt3;
    private widget.ComboBox cmbNama;
    private widget.ComboBox cmbNamaAntibiotik;
    private widget.ComboBox cmbNamaProsedur;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbProsedur1;
    private widget.ComboBox cmbProsedur2;
    private widget.ComboBox cmbPulse;
    private widget.ComboBox cmbRencana;
    private widget.ComboBox cmbResiko;
    private widget.ComboBox cmbSpesimen;
    private widget.ComboBox cmbSurat;
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
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label22;
    private widget.Label label23;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.Table tbCeklis;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT c1.*, c2.*, c3.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                    + "DATE_FORMAT(c1.waktu_simpan,'%d-%m-%Y') tglCeklis, date(c1.waktu_simpan) tglCeklisAsli, pg1.nama nmPerawatC1, pg2.nama nmDokterC1, pg3.nama nmOperatorC2, "
                    + "pg4.nama nmDokterC2, pg5.nama nmPerawatC2, pg6.nama nmPerawatC3, pg7.nama nmDokterC3, c1.nip_perawat nip_perawatC1, c1.nip_dokter nip_dokterC1, "
                    + "c2.nip_operator nip_operatorC2, c2.nip_dokter nip_dokterC2, c2.nip_perawat nip_perawatC2, c3.nip_perawat nip_perawatC3, c3.nip_dokter nip_dokterC3 "
                    + "FROM ceklis_keselamatan_operasi1 c1 inner join ceklis_keselamatan_operasi2 c2 on c2.waktu_simpan=c1.waktu_simpan "
                    + "inner join ceklis_keselamatan_operasi3 c3 on c3.waktu_simpan=c1.waktu_simpan inner join reg_periksa rp on rp.no_rawat=c1.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg1 on pg1.nik=c1.nip_perawat "
                    + "inner join pegawai pg2 on pg2.nik=c1.nip_dokter inner join pegawai pg3 on pg3.nik=c2.nip_operator inner join pegawai pg4 on pg4.nik=c2.nip_dokter "
                    + "inner join pegawai pg5 on pg5.nik=c2.nip_perawat inner join pegawai pg6 on pg6.nik=c3.nip_perawat inner join pegawai pg7 on pg7.nik=c3.nip_dokter WHERE "
                    + "date(c1.waktu_simpan) between ? and ? and c1.no_rawat LIKE ? or "
                    + "date(c1.waktu_simpan) between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "date(c1.waktu_simpan) between ? and ? and p.nm_pasien LIKE ? or "
                    + "date(c1.waktu_simpan) between ? and ? and pg1.nama LIKE ? or "
                    + "date(c1.waktu_simpan) between ? and ? and pg2.nama LIKE ? or "
                    + "date(c1.waktu_simpan) between ? and ? and pg3.nama LIKE ? or "
                    + "date(c1.waktu_simpan) between ? and ? and pg4.nama LIKE ? or "
                    + "date(c1.waktu_simpan) between ? and ? and pg5.nama LIKE ? or "
                    + "date(c1.waktu_simpan) between ? and ? and pg6.nama LIKE ? or "
                    + "date(c1.waktu_simpan) between ? and ? and pg7.nama LIKE ? ORDER BY c1.waktu_simpan desc");
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
                        rs.getString("jenkel"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglCeklis"),
                        rs.getString("the_sign_in"),
                        rs.getString("identifikasi_gelang"),
                        rs.getString("lokasi_operasi"),
                        rs.getString("prosedur"),
                        rs.getString("surat_ijin_operasi"),
                        rs.getString("lokasi_operasi_diberi_tanda"),
                        rs.getString("mesin_sdh_dicek_lengkap"),
                        rs.getString("pulse_oximeter"),
                        rs.getString("pasien_punya_riwayat_alergi"),
                        rs.getString("kesulitan_betnafas"),
                        rs.getString("menggunakan_peralatan_bantuan"),
                        rs.getString("resiko_kehilangan_darah"),
                        rs.getString("500ml_pada_anak"),
                        rs.getString("dua_akses_intravensi"),
                        rs.getString("rencana_terapi_cairan"),
                        rs.getString("nip_perawatC1"),
                        rs.getString("nip_dokterC1"),
                        rs.getString("waktu_simpan"),
                        rs.getString("the_time_out"),
                        rs.getString("konfirmasi_anggota_tim"),
                        rs.getString("nama_pasien"),
                        rs.getString("prosedur"),
                        rs.getString("lokasi_insisi_dibuat"),
                        rs.getString("antibiotik_diberikan"),
                        rs.getString("cek_nama_antibiotik"),
                        rs.getString("cek_dosis_antibiotik"),
                        rs.getString("nama_antibiotik"),
                        rs.getString("dosis_antibiotik"),
                        rs.getString("antisipasi_kejadian_kritis"),
                        rs.getString("review_dokter"),
                        rs.getString("review_tim"),
                        rs.getString("cvc_dipasang"),
                        rs.getString("review_tim_perawat"),
                        rs.getString("foto_rongsen_ditayangkan"),
                        rs.getString("nip_operatorC2"),
                        rs.getString("nip_dokterC2"),
                        rs.getString("nip_perawatC2"),
                        rs.getString("the_sign_out"),
                        rs.getString("nama_prosedur"),
                        rs.getString("instrumen_kasa"),
                        rs.getString("spesimen"),
                        rs.getString("adakah_masalah"),
                        rs.getString("operator_dokter_bedah"),
                        rs.getString("hal_yang_diperhatikan"),
                        rs.getString("tgl_tindakan"),
                        rs.getString("verifikasi"),
                        rs.getString("nip_perawatC3"),
                        rs.getString("nip_dokterC3"),
                        rs.getString("tglCeklisAsli"),                        
                        rs.getString("nmPerawatC1"),
                        rs.getString("nmDokterC1"),
                        rs.getString("nmOperatorC2"),
                        rs.getString("nmDokterC2"),
                        rs.getString("nmPerawatC2"),
                        rs.getString("nmPerawatC3"),
                        rs.getString("nmDokterC3")
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.RMCeklisKeselamatanOperasi.tampil() : " + e);
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
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        cmbIdentifikasi.setSelectedIndex(0);
        cmbLokasi1.setSelectedIndex(0);
        cmbProsedur1.setSelectedIndex(0);
        cmbSurat.setSelectedIndex(0);
        cmbLokasi2.setSelectedIndex(0);
        cmbMesin.setSelectedIndex(0);
        cmbPulse.setSelectedIndex(0);
        cmbApakahPasien.setSelectedIndex(0);
        cmbKesulitan.setSelectedIndex(0);
        cmbDan.setSelectedIndex(0);
        cmbResiko.setSelectedIndex(0);
        cmb500.setSelectedIndex(0);
        cmbDua.setSelectedIndex(0);
        cmbRencana.setSelectedIndex(0);
        TnipPerawatSir.setText("-");
        TnmPerawatSir.setText("-");
        chkSayaSir.setSelected(false);        
        TnipDokterAnes1.setText("-");
        TnmDokterAnes1.setText("-");
        cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk2.setSelectedIndex(0);
        cmbKonfirmasi.setSelectedIndex(0);
        cmbNama.setSelectedIndex(0);
        cmbProsedur2.setSelectedIndex(0);
        cmbLokasiDimana.setSelectedIndex(0);
        cmbApakahAntibiotik.setSelectedIndex(0);
        TnmAntibiotik.setText("");
        cmbNamaAntibiotik.setSelectedIndex(0);
        TdosisAntibiotik.setText("");
        cmbDosisAntibiotik.setSelectedIndex(0);
        Tantisipasi.setText("");
        TreviewA.setText("");
        TreviewB.setText("");
        Tjika.setText("");
        TreviewC.setText("");
        cmbApakahFoto.setSelectedIndex(0);
        TnipOperator.setText("-");
        TnmOperator.setText("-");        
        chkSayaOp.setSelected(false);
        TnipDokterAnes2.setText("-");
        TnmDokterAnes2.setText("-");
        TnipPerawatSirkuit1.setText("-");
        TnmPerawatSirkuit1.setText("-");        
        chkSayaSirkuit1.setSelected(false);
        cmbJam3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk3.setSelectedIndex(0);
        cmbNamaProsedur.setSelectedIndex(0);        
        cmbInstrumen.setSelectedIndex(0);
        cmbSpesimen.setSelectedIndex(0);
        cmbAdakah.setSelectedIndex(0);        
        cmb2Operator.setSelectedIndex(0);        
        ThalYang.setText("");
        TtglTindakan.setDate(new Date());
        Tverifikasi.setText("");
        TnipPerawatSirkuit2.setText("-");
        TnmPerawatSirkuit2.setText("-");        
        chkSayaSirkuit2.setSelected(false);
        TnipDokterAnes3.setText("-");
        TnmDokterAnes3.setText("-");        
    }

    private void getData() {
        wktSimpan = "";
        chkSayaSir.setSelected(false);
        chkSayaOp.setSelected(false);
        chkSayaSirkuit1.setSelected(false);
        chkSayaSirkuit2.setSelected(false);
        
        if (tbCeklis.getSelectedRow() != -1) {
            wktSimpan = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 0).toString();
            TNoRw.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 1).toString());
            TNoRM.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 2).toString());
            TPasien.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 3).toString());
            TrgRawat.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 6).toString());
            cmbJam1.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 8).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 8).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 8).toString().substring(6, 8));
            cmbIdentifikasi.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 9).toString());
            cmbLokasi1.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 10).toString());            
            cmbProsedur1.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 11).toString());
            cmbSurat.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 12).toString());
            cmbLokasi2.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 13).toString());
            cmbMesin.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 14).toString());
            cmbPulse.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 15).toString());
            cmbApakahPasien.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 16).toString());            
            cmbKesulitan.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 17).toString());
            cmbDan.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 18).toString());
            cmbResiko.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 19).toString());
            cmb500.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 20).toString());
            cmbDua.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 21).toString());
            cmbRencana.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 22).toString());
            TnipPerawatSir.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 23).toString());
            TnmPerawatSir.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 57).toString());
            TnipDokterAnes1.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 24).toString());
            TnmDokterAnes1.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 58).toString());
            
            cmbJam2.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 26).toString().substring(0, 2));
            cmbMnt2.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 26).toString().substring(3, 5));
            cmbDtk2.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 26).toString().substring(6, 8));            
            cmbKonfirmasi.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 27).toString());
            cmbNama.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 28).toString());
            cmbProsedur2.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 29).toString());
            cmbLokasiDimana.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 30).toString());
            cmbApakahAntibiotik.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 31).toString());
            cmbNamaAntibiotik.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 32).toString());
            cmbDosisAntibiotik.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 33).toString());
            TnmAntibiotik.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 34).toString());
            TdosisAntibiotik.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 35).toString());
            Tantisipasi.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 36).toString());
            TreviewA.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 37).toString());
            TreviewB.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 38).toString());
            Tjika.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 39).toString());
            TreviewC.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 40).toString());
            cmbApakahFoto.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 41).toString());
            TnipOperator.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 42).toString());
            TnmOperator.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 59).toString());
            TnipDokterAnes2.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 43).toString());
            TnmDokterAnes2.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 60).toString());
            TnipPerawatSirkuit1.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 44).toString());
            TnmPerawatSirkuit1.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 61).toString());
            
            cmbJam3.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 45).toString().substring(0, 2));
            cmbMnt3.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 45).toString().substring(3, 5));
            cmbDtk3.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 45).toString().substring(6, 8));
            cmbNamaProsedur.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 46).toString());
            cmbInstrumen.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 47).toString());
            cmbSpesimen.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 48).toString());
            cmbAdakah.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 49).toString());
            cmb2Operator.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 50).toString());
            ThalYang.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 51).toString());
            Valid.SetTgl(TtglTindakan, tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 56).toString());
            Tverifikasi.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 53).toString());
            TnipPerawatSirkuit2.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 54).toString());            
            TnmPerawatSirkuit2.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 62).toString());            
            TnipDokterAnes3.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 55).toString());
            TnmDokterAnes3.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 63).toString());
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getkegiatan_operasi());
        BtnGanti.setEnabled(akses.getkegiatan_operasi());
        BtnHapus.setEnabled(akses.getkegiatan_operasi());
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TrgRawat.setText(ruangan);
        TCari.setText(norw);
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'"));
    }
}
