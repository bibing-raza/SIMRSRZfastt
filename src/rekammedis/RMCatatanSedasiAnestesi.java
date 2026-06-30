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
import kepegawaian.DlgCariPetugas;
import laporan.DlgHasilPenunjangMedis;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class RMCatatanSedasiAnestesi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0, pilihDokter = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String nipDrBedah = "", nipDrAnes = "", nipPenata = "", urutData = "", urutanKe = "",
            total = "", partial = "", gagal = "", ett = "", lma = "", fima = "", tiva = "", spinal = "", epidural = "", cse = "", infil = "", blok = "", ga = "",
            induksi = "", pasienSiap = "", insisi = "", operasi = "", ekstub = "", pasienKlr = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMCatatanSedasiAnestesi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Tanggal", "Jam", "Anamnesa Dari", "Diagnosa", "Dokter Bedah", "Dokter Anestesi", 
            "ruang_rawat", "alergi_obat", "anamnesa_dari", "tgl_ruang", "jam_ruang", "diagnosa", "rencana_tindakan", "td", "bb", "nadi", "rr", "tb", "suhu", "obat_dikonsumsi", 
            "ket_obat_dikonsumsi", "tgl_operasi", "nip_spesialis_bedah", "nip_spesialis_anestesi", "riwayat_anestesi", "bebas", "leher_pendek", "gerak_leher", "sulit_ventilasi", 
            "alat_bantu", "massa", "obesitas", "protusi", "mallampathy", "buka_mulut", "jarak_thyro", "gigi", "ps_asa", "penyulit", "rencana_anestesi", "instruksi_anestesi", 
            "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbCatatan.setModel(tabMode);
        tbCatatan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCatatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 47; i++) {
            TableColumn column = tbCatatan.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(250);
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
            }
        }
        tbCatatan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "no_rawat", "urutan", "Status Fisik", "Servo", "Iso", "Halo", "Eth", "N2O", "O2", "Infus", "TD (Sistole)", "TD (Diastole)", "Nadi",
            "waktu_data_catatan", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbStatus.setModel(tabMode1);
        tbStatus.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbStatus.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 15; i++) {
            TableColumn column = tbStatus.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setPreferredWidth(65);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(65);
            } else if (i == 8) {
                column.setPreferredWidth(65);
            } else if (i == 9) {
                column.setPreferredWidth(65);
            } else if (i == 10) {
                column.setPreferredWidth(75);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            } else if (i == 12) {
                column.setPreferredWidth(65);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbStatus.setDefaultRenderer(Object.class, new WarnaTable());
        
        TdiagnosaPra.setDocument(new batasInput((int) 255).getKata(TdiagnosaPra));
        TdiagnosaPasca.setDocument(new batasInput((int) 255).getKata(TdiagnosaPasca));
        TprosedurOps.setDocument(new batasInput((int) 255).getKata(TprosedurOps));
        Tpremedikasi.setDocument(new batasInput((int) 200).getKata(Tpremedikasi));
        Tsedasi.setDocument(new batasInput((int) 200).getKata(Tsedasi));
        Tinduksi.setDocument(new batasInput((int) 200).getKata(Tinduksi));
        Tpelumpuh.setDocument(new batasInput((int) 200).getKata(Tpelumpuh));
        TketGagal.setDocument(new batasInput((int) 100).getKata(TketGagal));
        Ttransfusi1.setDocument(new batasInput((int) 200).getKata(Ttransfusi1));
        Ttransfusi2.setDocument(new batasInput((int) 200).getKata(Ttransfusi2));
        Ttransfusi3.setDocument(new batasInput((int) 200).getKata(Ttransfusi3));
        Ttransfusi4.setDocument(new batasInput((int) 200).getKata(Ttransfusi4));
        Ttransfusi5.setDocument(new batasInput((int) 200).getKata(Ttransfusi5));
        Tapgar.setDocument(new batasInput((int) 50).getKata(Tapgar));
        Tkristaloid.setDocument(new batasInput((int) 7).getKata(Tkristaloid));
        Tkoloid.setDocument(new batasInput((int) 7).getKata(Tkoloid));
        Twb.setDocument(new batasInput((int) 7).getKata(Twb));
        Tprc.setDocument(new batasInput((int) 7).getKata(Tprc));
        TjmlCairan.setDocument(new batasInput((int) 7).getKata(TjmlCairan));
        Tpendarahan.setDocument(new batasInput((int) 7).getKata(Tpendarahan));
        Turin.setDocument(new batasInput((int) 7).getKata(Turin));
        Tlain.setDocument(new batasInput((int) 7).getKata(Tlain));

        Tservo.setDocument(new batasInput((int) 20).getKata(Tservo));
        Tiso.setDocument(new batasInput((int) 20).getKata(Tiso));
        Thalo.setDocument(new batasInput((int) 20).getKata(Thalo));
        Teth.setDocument(new batasInput((int) 20).getKata(Teth));
        Tn2o.setDocument(new batasInput((int) 20).getKata(Tn2o));
        To2.setDocument(new batasInput((int) 20).getKata(To2));
        Tinfus.setDocument(new batasInput((int) 20).getKata(Tinfus));
        Tsistol.setDocument(new batasInput((int) 7).getKata(Tsistol));
        Tdistol.setDocument(new batasInput((int) 7).getKata(Tdistol));
        Tnadi.setDocument(new batasInput((int) 7).getKata(Tnadi));
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
                if (akses.getform().equals("RMCatatanSedasiAnestesi")) {
                    if (pilihDokter == 1) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nipDrBedah  = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                            TnmDrBedah.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDrBedah.requestFocus();
                        }                        
                    } else if (pilihDokter == 2) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nipDrAnes = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                            TnmDokterAnes.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDokterAnes.requestFocus();
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
                if (akses.getform().equals("RMCatatanSedasiAnestesi")) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipPenata = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPenata.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPenata.requestFocus();
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHasilPemeriksaanPenunjang = new javax.swing.JMenuItem();
        MnDokumenJangMed = new javax.swing.JMenuItem();
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
        TnmDrBedah = new widget.TextBox();
        TnmDokterAnes = new widget.TextBox();
        BtnDrBedah = new widget.Button();
        BtnDokterAnes = new widget.Button();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel276 = new widget.Label();
        jLabel66 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel67 = new widget.Label();
        TdiagnosaPra = new widget.TextBox();
        jLabel68 = new widget.Label();
        TdiagnosaPasca = new widget.TextBox();
        jLabel69 = new widget.Label();
        TprosedurOps = new widget.TextBox();
        jLabel71 = new widget.Label();
        jLabel70 = new widget.Label();
        Tpremedikasi = new widget.TextBox();
        jLabel72 = new widget.Label();
        Tsedasi = new widget.TextBox();
        jLabel73 = new widget.Label();
        Tinduksi = new widget.TextBox();
        jLabel74 = new widget.Label();
        Tpelumpuh = new widget.TextBox();
        jLabel75 = new widget.Label();
        chkTotal = new widget.CekBox();
        chkPartial = new widget.CekBox();
        chkGagal = new widget.CekBox();
        TketGagal = new widget.TextBox();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        chkEtt = new widget.CekBox();
        chkLma = new widget.CekBox();
        chkFima = new widget.CekBox();
        chkTiva = new widget.CekBox();
        jLabel79 = new widget.Label();
        chkSpinal = new widget.CekBox();
        chkEpid = new widget.CekBox();
        chkCse = new widget.CekBox();
        jLabel80 = new widget.Label();
        chkInfil = new widget.CekBox();
        chkBlok = new widget.CekBox();
        jLabel81 = new widget.Label();
        chkGa = new widget.CekBox();
        jLabel82 = new widget.Label();
        scrollPane19 = new widget.ScrollPane();
        Tobat = new widget.TextArea();
        jLabel83 = new widget.Label();
        jLabel84 = new widget.Label();
        Ttransfusi1 = new widget.TextBox();
        jLabel85 = new widget.Label();
        Ttransfusi2 = new widget.TextBox();
        jLabel86 = new widget.Label();
        Ttransfusi3 = new widget.TextBox();
        jLabel87 = new widget.Label();
        Ttransfusi4 = new widget.TextBox();
        jLabel88 = new widget.Label();
        Ttransfusi5 = new widget.TextBox();
        jLabel94 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        jLabel96 = new widget.Label();
        Tservo = new widget.TextBox();
        jLabel97 = new widget.Label();
        Tiso = new widget.TextBox();
        jLabel98 = new widget.Label();
        jLabel99 = new widget.Label();
        Thalo = new widget.TextBox();
        Teth = new widget.TextBox();
        jLabel100 = new widget.Label();
        jLabel101 = new widget.Label();
        Tn2o = new widget.TextBox();
        To2 = new widget.TextBox();
        jLabel102 = new widget.Label();
        Tinfus = new widget.TextBox();
        jLabel103 = new widget.Label();
        Tsistol = new widget.TextBox();
        jLabel104 = new widget.Label();
        Tdistol = new widget.TextBox();
        jLabel105 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel106 = new widget.Label();
        Scroll2 = new widget.ScrollPane();
        tbStatus = new widget.Table();
        BtnBaruStts = new widget.Button();
        BtnTambahStts = new widget.Button();
        BtnHapusStts = new widget.Button();
        BtnGantiStts = new widget.Button();
        jLabel107 = new widget.Label();
        Tapgar = new widget.TextBox();
        chkInduksi = new widget.CekBox();
        chkPasien = new widget.CekBox();
        chkInsisi = new widget.CekBox();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        jLabel277 = new widget.Label();
        cmbJam3 = new widget.ComboBox();
        cmbMnt3 = new widget.ComboBox();
        cmbDtk3 = new widget.ComboBox();
        jLabel278 = new widget.Label();
        cmbJam4 = new widget.ComboBox();
        cmbMnt4 = new widget.ComboBox();
        cmbDtk4 = new widget.ComboBox();
        jLabel279 = new widget.Label();
        chkOperasi = new widget.CekBox();
        chkEkstubasi = new widget.CekBox();
        chkPasienKeluar = new widget.CekBox();
        cmbJam5 = new widget.ComboBox();
        cmbMnt5 = new widget.ComboBox();
        cmbDtk5 = new widget.ComboBox();
        jLabel280 = new widget.Label();
        cmbJam6 = new widget.ComboBox();
        cmbMnt6 = new widget.ComboBox();
        cmbDtk6 = new widget.ComboBox();
        jLabel281 = new widget.Label();
        cmbDtk7 = new widget.ComboBox();
        cmbMnt7 = new widget.ComboBox();
        jLabel282 = new widget.Label();
        cmbJam7 = new widget.ComboBox();
        jLabel108 = new widget.Label();
        scrollPane20 = new widget.ScrollPane();
        Tcatatan = new widget.TextArea();
        jLabel109 = new widget.Label();
        jLabel89 = new widget.Label();
        Tkristaloid = new widget.TextBox();
        jLabel110 = new widget.Label();
        jLabel90 = new widget.Label();
        Tkoloid = new widget.TextBox();
        jLabel111 = new widget.Label();
        jLabel91 = new widget.Label();
        Twb = new widget.TextBox();
        jLabel112 = new widget.Label();
        jLabel92 = new widget.Label();
        Tprc = new widget.TextBox();
        jLabel113 = new widget.Label();
        jLabel93 = new widget.Label();
        TjmlCairan = new widget.TextBox();
        jLabel114 = new widget.Label();
        jLabel115 = new widget.Label();
        jLabel116 = new widget.Label();
        Tpendarahan = new widget.TextBox();
        jLabel117 = new widget.Label();
        jLabel118 = new widget.Label();
        Turin = new widget.TextBox();
        jLabel119 = new widget.Label();
        jLabel120 = new widget.Label();
        Tlain = new widget.TextBox();
        jLabel121 = new widget.Label();
        jLabel122 = new widget.Label();
        jLabel123 = new widget.Label();
        TnmPenata = new widget.TextBox();
        BtnPenata = new widget.Button();
        PanelInput1 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbCatatan = new widget.Table();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Catatan Sedasi / Anestesi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1218));
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

        TnmDrBedah.setEditable(false);
        TnmDrBedah.setForeground(new java.awt.Color(0, 0, 0));
        TnmDrBedah.setName("TnmDrBedah"); // NOI18N
        FormInput.add(TnmDrBedah);
        TnmDrBedah.setBounds(145, 150, 410, 23);

        TnmDokterAnes.setEditable(false);
        TnmDokterAnes.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokterAnes.setName("TnmDokterAnes"); // NOI18N
        FormInput.add(TnmDokterAnes);
        TnmDokterAnes.setBounds(445, 1180, 310, 23);

        BtnDrBedah.setForeground(new java.awt.Color(0, 0, 0));
        BtnDrBedah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDrBedah.setMnemonic('1');
        BtnDrBedah.setToolTipText("Alt+1");
        BtnDrBedah.setName("BtnDrBedah"); // NOI18N
        BtnDrBedah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDrBedahActionPerformed(evt);
            }
        });
        FormInput.add(BtnDrBedah);
        BtnDrBedah.setBounds(560, 150, 28, 23);

        BtnDokterAnes.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokterAnes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokterAnes.setMnemonic('1');
        BtnDokterAnes.setToolTipText("Alt+1");
        BtnDokterAnes.setName("BtnDokterAnes"); // NOI18N
        BtnDokterAnes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterAnesActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokterAnes);
        BtnDokterAnes.setBounds(760, 1180, 28, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(280, 402, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(332, 402, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(384, 402, 45, 23);

        jLabel276.setForeground(new java.awt.Color(0, 0, 0));
        jLabel276.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel276.setText("Wita");
        jLabel276.setName("jLabel276"); // NOI18N
        FormInput.add(jLabel276);
        jLabel276.setBounds(435, 402, 50, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Ruang Rawat :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 38, 140, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(145, 38, 615, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Diagnosa Pra Operasi :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 66, 140, 23);

        TdiagnosaPra.setForeground(new java.awt.Color(0, 0, 0));
        TdiagnosaPra.setName("TdiagnosaPra"); // NOI18N
        TdiagnosaPra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaPraKeyPressed(evt);
            }
        });
        FormInput.add(TdiagnosaPra);
        TdiagnosaPra.setBounds(145, 66, 615, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Diagnosa Pasca Operasi :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 94, 140, 23);

        TdiagnosaPasca.setForeground(new java.awt.Color(0, 0, 0));
        TdiagnosaPasca.setName("TdiagnosaPasca"); // NOI18N
        TdiagnosaPasca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaPascaKeyPressed(evt);
            }
        });
        FormInput.add(TdiagnosaPasca);
        TdiagnosaPasca.setBounds(145, 94, 615, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Prosedur Operasi :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 122, 140, 23);

        TprosedurOps.setForeground(new java.awt.Color(0, 0, 0));
        TprosedurOps.setName("TprosedurOps"); // NOI18N
        TprosedurOps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprosedurOpsKeyPressed(evt);
            }
        });
        FormInput.add(TprosedurOps);
        TprosedurOps.setBounds(145, 122, 615, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Dokter Bedah :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 150, 140, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Premedikasi :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 178, 140, 23);

        Tpremedikasi.setForeground(new java.awt.Color(0, 0, 0));
        Tpremedikasi.setName("Tpremedikasi"); // NOI18N
        Tpremedikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpremedikasiKeyPressed(evt);
            }
        });
        FormInput.add(Tpremedikasi);
        Tpremedikasi.setBounds(145, 178, 615, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Sedasi :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 206, 140, 23);

        Tsedasi.setForeground(new java.awt.Color(0, 0, 0));
        Tsedasi.setName("Tsedasi"); // NOI18N
        Tsedasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsedasiKeyPressed(evt);
            }
        });
        FormInput.add(Tsedasi);
        Tsedasi.setBounds(145, 206, 615, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Induksi :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 234, 140, 23);

        Tinduksi.setForeground(new java.awt.Color(0, 0, 0));
        Tinduksi.setName("Tinduksi"); // NOI18N
        Tinduksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinduksiKeyPressed(evt);
            }
        });
        FormInput.add(Tinduksi);
        Tinduksi.setBounds(145, 234, 615, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Pelumpuh Otot :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 262, 140, 23);

        Tpelumpuh.setForeground(new java.awt.Color(0, 0, 0));
        Tpelumpuh.setName("Tpelumpuh"); // NOI18N
        Tpelumpuh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpelumpuhKeyPressed(evt);
            }
        });
        FormInput.add(Tpelumpuh);
        Tpelumpuh.setBounds(145, 262, 615, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Hasil :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 290, 140, 23);

        chkTotal.setBackground(new java.awt.Color(242, 242, 242));
        chkTotal.setForeground(new java.awt.Color(0, 0, 0));
        chkTotal.setText("Total Block");
        chkTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTotal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTotal.setName("chkTotal"); // NOI18N
        chkTotal.setOpaque(false);
        chkTotal.setPreferredSize(new java.awt.Dimension(220, 23));
        chkTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTotalActionPerformed(evt);
            }
        });
        FormInput.add(chkTotal);
        chkTotal.setBounds(145, 290, 80, 23);

        chkPartial.setBackground(new java.awt.Color(242, 242, 242));
        chkPartial.setForeground(new java.awt.Color(0, 0, 0));
        chkPartial.setText("Partial");
        chkPartial.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPartial.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPartial.setName("chkPartial"); // NOI18N
        chkPartial.setOpaque(false);
        chkPartial.setPreferredSize(new java.awt.Dimension(220, 23));
        chkPartial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPartialActionPerformed(evt);
            }
        });
        FormInput.add(chkPartial);
        chkPartial.setBounds(240, 290, 60, 23);

        chkGagal.setBackground(new java.awt.Color(242, 242, 242));
        chkGagal.setForeground(new java.awt.Color(0, 0, 0));
        chkGagal.setText("Gagal :");
        chkGagal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGagal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGagal.setName("chkGagal"); // NOI18N
        chkGagal.setOpaque(false);
        chkGagal.setPreferredSize(new java.awt.Dimension(220, 23));
        chkGagal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkGagalActionPerformed(evt);
            }
        });
        FormInput.add(chkGagal);
        chkGagal.setBounds(315, 290, 60, 23);

        TketGagal.setForeground(new java.awt.Color(0, 0, 0));
        TketGagal.setName("TketGagal"); // NOI18N
        TketGagal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketGagalKeyPressed(evt);
            }
        });
        FormInput.add(TketGagal);
        TketGagal.setBounds(375, 290, 385, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Jenis Anestesi :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 318, 140, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("GA :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(145, 318, 60, 23);

        chkEtt.setBackground(new java.awt.Color(242, 242, 242));
        chkEtt.setForeground(new java.awt.Color(0, 0, 0));
        chkEtt.setText("ETT");
        chkEtt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEtt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEtt.setName("chkEtt"); // NOI18N
        chkEtt.setOpaque(false);
        chkEtt.setPreferredSize(new java.awt.Dimension(220, 23));
        chkEtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEttActionPerformed(evt);
            }
        });
        FormInput.add(chkEtt);
        chkEtt.setBounds(210, 318, 50, 23);

        chkLma.setBackground(new java.awt.Color(242, 242, 242));
        chkLma.setForeground(new java.awt.Color(0, 0, 0));
        chkLma.setText("LMA");
        chkLma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLma.setName("chkLma"); // NOI18N
        chkLma.setOpaque(false);
        chkLma.setPreferredSize(new java.awt.Dimension(220, 23));
        chkLma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLmaActionPerformed(evt);
            }
        });
        FormInput.add(chkLma);
        chkLma.setBounds(267, 318, 50, 23);

        chkFima.setBackground(new java.awt.Color(242, 242, 242));
        chkFima.setForeground(new java.awt.Color(0, 0, 0));
        chkFima.setText("FIMA");
        chkFima.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFima.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFima.setName("chkFima"); // NOI18N
        chkFima.setOpaque(false);
        chkFima.setPreferredSize(new java.awt.Dimension(220, 23));
        chkFima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFimaActionPerformed(evt);
            }
        });
        FormInput.add(chkFima);
        chkFima.setBounds(330, 318, 55, 23);

        chkTiva.setBackground(new java.awt.Color(242, 242, 242));
        chkTiva.setForeground(new java.awt.Color(0, 0, 0));
        chkTiva.setText("TIVA");
        chkTiva.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTiva.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTiva.setName("chkTiva"); // NOI18N
        chkTiva.setOpaque(false);
        chkTiva.setPreferredSize(new java.awt.Dimension(220, 23));
        chkTiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTivaActionPerformed(evt);
            }
        });
        FormInput.add(chkTiva);
        chkTiva.setBounds(395, 318, 55, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Regional :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(145, 346, 60, 23);

        chkSpinal.setBackground(new java.awt.Color(242, 242, 242));
        chkSpinal.setForeground(new java.awt.Color(0, 0, 0));
        chkSpinal.setText("Spinal");
        chkSpinal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSpinal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSpinal.setName("chkSpinal"); // NOI18N
        chkSpinal.setOpaque(false);
        chkSpinal.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSpinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSpinalActionPerformed(evt);
            }
        });
        FormInput.add(chkSpinal);
        chkSpinal.setBounds(210, 346, 60, 23);

        chkEpid.setBackground(new java.awt.Color(242, 242, 242));
        chkEpid.setForeground(new java.awt.Color(0, 0, 0));
        chkEpid.setText("Epidural");
        chkEpid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEpid.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEpid.setName("chkEpid"); // NOI18N
        chkEpid.setOpaque(false);
        chkEpid.setPreferredSize(new java.awt.Dimension(220, 23));
        chkEpid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEpidActionPerformed(evt);
            }
        });
        FormInput.add(chkEpid);
        chkEpid.setBounds(280, 346, 70, 23);

        chkCse.setBackground(new java.awt.Color(242, 242, 242));
        chkCse.setForeground(new java.awt.Color(0, 0, 0));
        chkCse.setText("CSE");
        chkCse.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCse.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCse.setName("chkCse"); // NOI18N
        chkCse.setOpaque(false);
        chkCse.setPreferredSize(new java.awt.Dimension(220, 23));
        chkCse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCseActionPerformed(evt);
            }
        });
        FormInput.add(chkCse);
        chkCse.setBounds(360, 346, 55, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Lokal :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(145, 374, 60, 23);

        chkInfil.setBackground(new java.awt.Color(242, 242, 242));
        chkInfil.setForeground(new java.awt.Color(0, 0, 0));
        chkInfil.setText("Infiltrasi");
        chkInfil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInfil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInfil.setName("chkInfil"); // NOI18N
        chkInfil.setOpaque(false);
        chkInfil.setPreferredSize(new java.awt.Dimension(220, 23));
        chkInfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkInfilActionPerformed(evt);
            }
        });
        FormInput.add(chkInfil);
        chkInfil.setBounds(210, 374, 75, 23);

        chkBlok.setBackground(new java.awt.Color(242, 242, 242));
        chkBlok.setForeground(new java.awt.Color(0, 0, 0));
        chkBlok.setText("Block");
        chkBlok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBlok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBlok.setName("chkBlok"); // NOI18N
        chkBlok.setOpaque(false);
        chkBlok.setPreferredSize(new java.awt.Dimension(220, 23));
        chkBlok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBlokActionPerformed(evt);
            }
        });
        FormInput.add(chkBlok);
        chkBlok.setBounds(300, 374, 60, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Konversi Ke :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(105, 402, 100, 23);

        chkGa.setBackground(new java.awt.Color(242, 242, 242));
        chkGa.setForeground(new java.awt.Color(0, 0, 0));
        chkGa.setText("GA Jam :");
        chkGa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGa.setName("chkGa"); // NOI18N
        chkGa.setOpaque(false);
        chkGa.setPreferredSize(new java.awt.Dimension(220, 23));
        chkGa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkGaActionPerformed(evt);
            }
        });
        FormInput.add(chkGa);
        chkGa.setBounds(210, 402, 70, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Obat - Obatan :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 430, 140, 23);

        scrollPane19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane19.setName("scrollPane19"); // NOI18N

        Tobat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tobat.setColumns(20);
        Tobat.setRows(5);
        Tobat.setName("Tobat"); // NOI18N
        Tobat.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tobat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatKeyPressed(evt);
            }
        });
        scrollPane19.setViewportView(Tobat);

        FormInput.add(scrollPane19);
        scrollPane19.setBounds(145, 430, 615, 60);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("IUFD / Transfusi :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(0, 496, 140, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("1. :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(145, 496, 30, 23);

        Ttransfusi1.setForeground(new java.awt.Color(0, 0, 0));
        Ttransfusi1.setName("Ttransfusi1"); // NOI18N
        Ttransfusi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Ttransfusi1KeyPressed(evt);
            }
        });
        FormInput.add(Ttransfusi1);
        Ttransfusi1.setBounds(180, 496, 580, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("2. :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(145, 524, 30, 23);

        Ttransfusi2.setForeground(new java.awt.Color(0, 0, 0));
        Ttransfusi2.setName("Ttransfusi2"); // NOI18N
        Ttransfusi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Ttransfusi2KeyPressed(evt);
            }
        });
        FormInput.add(Ttransfusi2);
        Ttransfusi2.setBounds(180, 524, 580, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("3. :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(145, 552, 30, 23);

        Ttransfusi3.setForeground(new java.awt.Color(0, 0, 0));
        Ttransfusi3.setName("Ttransfusi3"); // NOI18N
        Ttransfusi3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Ttransfusi3KeyPressed(evt);
            }
        });
        FormInput.add(Ttransfusi3);
        Ttransfusi3.setBounds(180, 552, 580, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("4. :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(145, 580, 30, 23);

        Ttransfusi4.setForeground(new java.awt.Color(0, 0, 0));
        Ttransfusi4.setName("Ttransfusi4"); // NOI18N
        Ttransfusi4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Ttransfusi4KeyPressed(evt);
            }
        });
        FormInput.add(Ttransfusi4);
        Ttransfusi4.setBounds(180, 580, 580, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("5. :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(145, 608, 30, 23);

        Ttransfusi5.setForeground(new java.awt.Color(0, 0, 0));
        Ttransfusi5.setName("Ttransfusi5"); // NOI18N
        Ttransfusi5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Ttransfusi5KeyPressed(evt);
            }
        });
        FormInput.add(Ttransfusi5);
        Ttransfusi5.setBounds(180, 608, 580, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Status Fisik ASA :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(0, 636, 140, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "I", "II", "III", "IV", "E" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });
        FormInput.add(cmbStatus);
        cmbStatus.setBounds(145, 636, 45, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Servo :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(190, 636, 60, 23);

        Tservo.setForeground(new java.awt.Color(0, 0, 0));
        Tservo.setName("Tservo"); // NOI18N
        Tservo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TservoKeyPressed(evt);
            }
        });
        FormInput.add(Tservo);
        Tservo.setBounds(255, 636, 110, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Iso :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(190, 664, 60, 23);

        Tiso.setForeground(new java.awt.Color(0, 0, 0));
        Tiso.setName("Tiso"); // NOI18N
        Tiso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TisoKeyPressed(evt);
            }
        });
        FormInput.add(Tiso);
        Tiso.setBounds(255, 664, 110, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Halo :");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(365, 636, 50, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Eth :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(365, 664, 50, 23);

        Thalo.setForeground(new java.awt.Color(0, 0, 0));
        Thalo.setName("Thalo"); // NOI18N
        Thalo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThaloKeyPressed(evt);
            }
        });
        FormInput.add(Thalo);
        Thalo.setBounds(420, 636, 80, 23);

        Teth.setForeground(new java.awt.Color(0, 0, 0));
        Teth.setName("Teth"); // NOI18N
        Teth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TethKeyPressed(evt);
            }
        });
        FormInput.add(Teth);
        Teth.setBounds(420, 664, 80, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("N2O :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(500, 636, 50, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("O2 :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(500, 664, 50, 23);

        Tn2o.setForeground(new java.awt.Color(0, 0, 0));
        Tn2o.setName("Tn2o"); // NOI18N
        Tn2o.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tn2oKeyPressed(evt);
            }
        });
        FormInput.add(Tn2o);
        Tn2o.setBounds(555, 636, 80, 23);

        To2.setForeground(new java.awt.Color(0, 0, 0));
        To2.setName("To2"); // NOI18N
        To2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                To2KeyPressed(evt);
            }
        });
        FormInput.add(To2);
        To2.setBounds(555, 664, 80, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Infus :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(635, 636, 50, 23);

        Tinfus.setForeground(new java.awt.Color(0, 0, 0));
        Tinfus.setName("Tinfus"); // NOI18N
        Tinfus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinfusKeyPressed(evt);
            }
        });
        FormInput.add(Tinfus);
        Tinfus.setBounds(690, 636, 70, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("TD (Sistole) :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(170, 692, 80, 23);

        Tsistol.setForeground(new java.awt.Color(0, 0, 0));
        Tsistol.setName("Tsistol"); // NOI18N
        Tsistol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsistolKeyPressed(evt);
            }
        });
        FormInput.add(Tsistol);
        Tsistol.setBounds(255, 692, 60, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("TD (Diastole) :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(315, 692, 80, 23);

        Tdistol.setForeground(new java.awt.Color(0, 0, 0));
        Tdistol.setName("Tdistol"); // NOI18N
        Tdistol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdistolKeyPressed(evt);
            }
        });
        FormInput.add(Tdistol);
        Tdistol.setBounds(400, 692, 60, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("mmHg      Nadi :");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(465, 692, 80, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(545, 692, 60, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel106.setText("x/menit");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(610, 692, 50, 23);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbStatus.setName("tbStatus"); // NOI18N
        tbStatus.getTableHeader().setReorderingAllowed(false);
        tbStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbStatusMouseClicked(evt);
            }
        });
        tbStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbStatusKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbStatus);

        FormInput.add(Scroll2);
        Scroll2.setBounds(145, 725, 500, 150);

        BtnBaruStts.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaruStts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaruStts.setText("Baru");
        BtnBaruStts.setToolTipText("Data DJJ Baru");
        BtnBaruStts.setName("BtnBaruStts"); // NOI18N
        BtnBaruStts.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnBaruStts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruSttsActionPerformed(evt);
            }
        });
        FormInput.add(BtnBaruStts);
        BtnBaruStts.setBounds(660, 725, 90, 30);

        BtnTambahStts.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahStts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahStts.setText("Tambah");
        BtnTambahStts.setToolTipText("Tambah Data Air Ketuban/Mulase");
        BtnTambahStts.setName("BtnTambahStts"); // NOI18N
        BtnTambahStts.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahStts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahSttsActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahStts);
        BtnTambahStts.setBounds(660, 764, 90, 30);

        BtnHapusStts.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusStts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusStts.setText("Hapus");
        BtnHapusStts.setToolTipText("Hapus Air Ketuban/Mulase");
        BtnHapusStts.setName("BtnHapusStts"); // NOI18N
        BtnHapusStts.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusStts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusSttsActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusStts);
        BtnHapusStts.setBounds(660, 803, 90, 30);

        BtnGantiStts.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiStts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiStts.setText("Ganti");
        BtnGantiStts.setToolTipText("Ganti Air Ketuban/Mulase");
        BtnGantiStts.setName("BtnGantiStts"); // NOI18N
        BtnGantiStts.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiStts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiSttsActionPerformed(evt);
            }
        });
        FormInput.add(BtnGantiStts);
        BtnGantiStts.setBounds(660, 842, 90, 30);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("Apgar Score :");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(0, 882, 140, 23);

        Tapgar.setForeground(new java.awt.Color(0, 0, 0));
        Tapgar.setName("Tapgar"); // NOI18N
        Tapgar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TapgarKeyPressed(evt);
            }
        });
        FormInput.add(Tapgar);
        Tapgar.setBounds(145, 882, 350, 23);

        chkInduksi.setBackground(new java.awt.Color(242, 242, 242));
        chkInduksi.setForeground(new java.awt.Color(0, 0, 0));
        chkInduksi.setText("Induksi Pukul :");
        chkInduksi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkInduksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInduksi.setName("chkInduksi"); // NOI18N
        chkInduksi.setOpaque(false);
        chkInduksi.setPreferredSize(new java.awt.Dimension(220, 23));
        chkInduksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkInduksiActionPerformed(evt);
            }
        });
        FormInput.add(chkInduksi);
        chkInduksi.setBounds(145, 910, 120, 23);

        chkPasien.setBackground(new java.awt.Color(242, 242, 242));
        chkPasien.setForeground(new java.awt.Color(0, 0, 0));
        chkPasien.setText("Pasien Siap Insisi :");
        chkPasien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPasien.setName("chkPasien"); // NOI18N
        chkPasien.setOpaque(false);
        chkPasien.setPreferredSize(new java.awt.Dimension(220, 23));
        chkPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPasienActionPerformed(evt);
            }
        });
        FormInput.add(chkPasien);
        chkPasien.setBounds(145, 938, 120, 23);

        chkInsisi.setBackground(new java.awt.Color(242, 242, 242));
        chkInsisi.setForeground(new java.awt.Color(0, 0, 0));
        chkInsisi.setText("Insisi Mulai Pukul :");
        chkInsisi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkInsisi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInsisi.setName("chkInsisi"); // NOI18N
        chkInsisi.setOpaque(false);
        chkInsisi.setPreferredSize(new java.awt.Dimension(220, 23));
        chkInsisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkInsisiActionPerformed(evt);
            }
        });
        FormInput.add(chkInsisi);
        chkInsisi.setBounds(145, 966, 120, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(270, 910, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(322, 910, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(374, 910, 45, 23);

        jLabel277.setForeground(new java.awt.Color(0, 0, 0));
        jLabel277.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel277.setText("Wita");
        jLabel277.setName("jLabel277"); // NOI18N
        FormInput.add(jLabel277);
        jLabel277.setBounds(425, 910, 30, 23);

        cmbJam3.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam3.setName("cmbJam3"); // NOI18N
        cmbJam3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam3MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam3);
        cmbJam3.setBounds(270, 938, 45, 23);

        cmbMnt3.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt3.setName("cmbMnt3"); // NOI18N
        cmbMnt3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt3MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt3);
        cmbMnt3.setBounds(322, 938, 45, 23);

        cmbDtk3.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk3.setName("cmbDtk3"); // NOI18N
        cmbDtk3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk3MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk3);
        cmbDtk3.setBounds(374, 938, 45, 23);

        jLabel278.setForeground(new java.awt.Color(0, 0, 0));
        jLabel278.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel278.setText("Wita");
        jLabel278.setName("jLabel278"); // NOI18N
        FormInput.add(jLabel278);
        jLabel278.setBounds(425, 938, 30, 23);

        cmbJam4.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam4.setName("cmbJam4"); // NOI18N
        cmbJam4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam4MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam4);
        cmbJam4.setBounds(270, 966, 45, 23);

        cmbMnt4.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt4.setName("cmbMnt4"); // NOI18N
        cmbMnt4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt4MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt4);
        cmbMnt4.setBounds(322, 966, 45, 23);

        cmbDtk4.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk4.setName("cmbDtk4"); // NOI18N
        cmbDtk4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk4MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk4);
        cmbDtk4.setBounds(374, 966, 45, 23);

        jLabel279.setForeground(new java.awt.Color(0, 0, 0));
        jLabel279.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel279.setText("Wita");
        jLabel279.setName("jLabel279"); // NOI18N
        FormInput.add(jLabel279);
        jLabel279.setBounds(425, 966, 30, 23);

        chkOperasi.setBackground(new java.awt.Color(242, 242, 242));
        chkOperasi.setForeground(new java.awt.Color(0, 0, 0));
        chkOperasi.setText("Operasi Selesai Pukul :");
        chkOperasi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkOperasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkOperasi.setName("chkOperasi"); // NOI18N
        chkOperasi.setOpaque(false);
        chkOperasi.setPreferredSize(new java.awt.Dimension(220, 23));
        chkOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkOperasiActionPerformed(evt);
            }
        });
        FormInput.add(chkOperasi);
        chkOperasi.setBounds(460, 910, 140, 23);

        chkEkstubasi.setBackground(new java.awt.Color(242, 242, 242));
        chkEkstubasi.setForeground(new java.awt.Color(0, 0, 0));
        chkEkstubasi.setText("Ekstubasi Pukul :");
        chkEkstubasi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkEkstubasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEkstubasi.setName("chkEkstubasi"); // NOI18N
        chkEkstubasi.setOpaque(false);
        chkEkstubasi.setPreferredSize(new java.awt.Dimension(220, 23));
        chkEkstubasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEkstubasiActionPerformed(evt);
            }
        });
        FormInput.add(chkEkstubasi);
        chkEkstubasi.setBounds(460, 938, 140, 23);

        chkPasienKeluar.setBackground(new java.awt.Color(242, 242, 242));
        chkPasienKeluar.setForeground(new java.awt.Color(0, 0, 0));
        chkPasienKeluar.setText("<html><div style='text-align:right'>Pasien Keluar Kamar Operasi Pukul :</div></html>");
        chkPasienKeluar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkPasienKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPasienKeluar.setName("chkPasienKeluar"); // NOI18N
        chkPasienKeluar.setOpaque(false);
        chkPasienKeluar.setPreferredSize(new java.awt.Dimension(220, 23));
        chkPasienKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPasienKeluarActionPerformed(evt);
            }
        });
        FormInput.add(chkPasienKeluar);
        chkPasienKeluar.setBounds(470, 966, 130, 30);

        cmbJam5.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam5.setName("cmbJam5"); // NOI18N
        cmbJam5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam5MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam5);
        cmbJam5.setBounds(605, 910, 45, 23);

        cmbMnt5.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt5.setName("cmbMnt5"); // NOI18N
        cmbMnt5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt5MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt5);
        cmbMnt5.setBounds(656, 910, 45, 23);

        cmbDtk5.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk5.setName("cmbDtk5"); // NOI18N
        cmbDtk5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk5MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk5);
        cmbDtk5.setBounds(708, 910, 45, 23);

        jLabel280.setForeground(new java.awt.Color(0, 0, 0));
        jLabel280.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel280.setText("Wita");
        jLabel280.setName("jLabel280"); // NOI18N
        FormInput.add(jLabel280);
        jLabel280.setBounds(760, 910, 30, 23);

        cmbJam6.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam6.setName("cmbJam6"); // NOI18N
        cmbJam6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam6MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam6);
        cmbJam6.setBounds(605, 938, 45, 23);

        cmbMnt6.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt6.setName("cmbMnt6"); // NOI18N
        cmbMnt6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt6MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt6);
        cmbMnt6.setBounds(656, 938, 45, 23);

        cmbDtk6.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk6.setName("cmbDtk6"); // NOI18N
        cmbDtk6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk6MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk6);
        cmbDtk6.setBounds(708, 938, 45, 23);

        jLabel281.setForeground(new java.awt.Color(0, 0, 0));
        jLabel281.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel281.setText("Wita");
        jLabel281.setName("jLabel281"); // NOI18N
        FormInput.add(jLabel281);
        jLabel281.setBounds(760, 938, 30, 23);

        cmbDtk7.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk7.setName("cmbDtk7"); // NOI18N
        cmbDtk7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk7MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk7);
        cmbDtk7.setBounds(708, 966, 45, 23);

        cmbMnt7.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt7.setName("cmbMnt7"); // NOI18N
        cmbMnt7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt7MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt7);
        cmbMnt7.setBounds(656, 966, 45, 23);

        jLabel282.setForeground(new java.awt.Color(0, 0, 0));
        jLabel282.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel282.setText("Wita");
        jLabel282.setName("jLabel282"); // NOI18N
        FormInput.add(jLabel282);
        jLabel282.setBounds(760, 966, 30, 23);

        cmbJam7.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam7.setName("cmbJam7"); // NOI18N
        cmbJam7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam7MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam7);
        cmbJam7.setBounds(605, 966, 45, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("Catatan :");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(0, 1002, 140, 23);

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
        scrollPane20.setBounds(145, 1002, 615, 60);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("CAIRAN (INPUT) :");
        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(0, 1068, 140, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Kristaloid :");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(145, 1068, 90, 23);

        Tkristaloid.setForeground(new java.awt.Color(0, 0, 0));
        Tkristaloid.setName("Tkristaloid"); // NOI18N
        Tkristaloid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkristaloidKeyPressed(evt);
            }
        });
        FormInput.add(Tkristaloid);
        Tkristaloid.setBounds(240, 1068, 70, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel110.setText("cc");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(315, 1068, 25, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Koloid :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(145, 1096, 90, 23);

        Tkoloid.setForeground(new java.awt.Color(0, 0, 0));
        Tkoloid.setName("Tkoloid"); // NOI18N
        Tkoloid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkoloidKeyPressed(evt);
            }
        });
        FormInput.add(Tkoloid);
        Tkoloid.setBounds(240, 1096, 70, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel111.setText("cc");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(315, 1096, 25, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Transfusi : WB :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(145, 1124, 90, 23);

        Twb.setForeground(new java.awt.Color(0, 0, 0));
        Twb.setName("Twb"); // NOI18N
        Twb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TwbKeyPressed(evt);
            }
        });
        FormInput.add(Twb);
        Twb.setBounds(240, 1124, 70, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("cc");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(315, 1124, 25, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("PRC :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(145, 1152, 90, 23);

        Tprc.setForeground(new java.awt.Color(0, 0, 0));
        Tprc.setName("Tprc"); // NOI18N
        Tprc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprcKeyPressed(evt);
            }
        });
        FormInput.add(Tprc);
        Tprc.setBounds(240, 1152, 70, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("cc");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(315, 1152, 25, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Jumlah Cairan :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(145, 1180, 90, 23);

        TjmlCairan.setForeground(new java.awt.Color(0, 0, 0));
        TjmlCairan.setName("TjmlCairan"); // NOI18N
        TjmlCairan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjmlCairanKeyPressed(evt);
            }
        });
        FormInput.add(TjmlCairan);
        TjmlCairan.setBounds(240, 1180, 70, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("cc");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(315, 1180, 25, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("CAIRAN (OUPUT) :");
        jLabel115.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(340, 1068, 120, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("Pendarahan :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(465, 1068, 80, 23);

        Tpendarahan.setForeground(new java.awt.Color(0, 0, 0));
        Tpendarahan.setName("Tpendarahan"); // NOI18N
        Tpendarahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpendarahanKeyPressed(evt);
            }
        });
        FormInput.add(Tpendarahan);
        Tpendarahan.setBounds(550, 1068, 70, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel117.setText("cc");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(625, 1068, 25, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("Urine :");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(465, 1096, 80, 23);

        Turin.setForeground(new java.awt.Color(0, 0, 0));
        Turin.setName("Turin"); // NOI18N
        Turin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TurinKeyPressed(evt);
            }
        });
        FormInput.add(Turin);
        Turin.setBounds(550, 1096, 70, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel119.setText("cc");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(625, 1096, 25, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("Lain-lain :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(465, 1124, 80, 23);

        Tlain.setForeground(new java.awt.Color(0, 0, 0));
        Tlain.setName("Tlain"); // NOI18N
        Tlain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainKeyPressed(evt);
            }
        });
        FormInput.add(Tlain);
        Tlain.setBounds(550, 1124, 70, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel121.setText("cc");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(625, 1124, 25, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("Penata Anestesi :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(340, 1152, 100, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("Dokter Anestesi :");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(340, 1180, 100, 23);

        TnmPenata.setEditable(false);
        TnmPenata.setForeground(new java.awt.Color(0, 0, 0));
        TnmPenata.setName("TnmPenata"); // NOI18N
        FormInput.add(TnmPenata);
        TnmPenata.setBounds(445, 1152, 310, 23);

        BtnPenata.setForeground(new java.awt.Color(0, 0, 0));
        BtnPenata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPenata.setMnemonic('1');
        BtnPenata.setToolTipText("Alt+1");
        BtnPenata.setName("BtnPenata"); // NOI18N
        BtnPenata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenataActionPerformed(evt);
            }
        });
        FormInput.add(BtnPenata);
        BtnPenata.setBounds(760, 1152, 28, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass9.add(Scroll1, java.awt.BorderLayout.CENTER);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Catatan Sedasi / Anestesi Operasi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(900, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbCatatan.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbCatatan.setName("tbCatatan"); // NOI18N
        tbCatatan.getTableHeader().setReorderingAllowed(false);
        tbCatatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCatatanMouseClicked(evt);
            }
        });
        tbCatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCatatanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbCatatan);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-06-2026" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-06-2026" }));
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
//            try {
//                if (Sequel.menyimpantf("evaluasi_pra_anestesi_operasi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 37, new String[]{
//                    TNoRw.getText(), TrgRawat.getText(), TalergiObat.getText(), cmbAnamnesa.getSelectedItem().toString(), Valid.SetTgl(TtglRuang.getSelectedItem() + ""),
//                    cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), Tdiagnosa.getText(), Valid.mysql_real_escape_stringERM(TrencanaTindakan.getText()),
//                    Ttd.getText(), Tbb.getText(), Tnadi.getText(), Trr.getText(), Ttb.getText(), Tsuhu.getText(), cmbObat.getSelectedItem().toString(),
//                    TketObat.getText(), Valid.SetTgl(TtglOperasi.getSelectedItem() + ""), nipSpesBedah, nipSpesAnes, Valid.mysql_real_escape_stringERM(TriwAnestesi.getText()),
//                    cmbBebas.getSelectedItem().toString(), cmbLeher.getSelectedItem().toString(), cmbGerak.getSelectedItem().toString(), cmbSulit.getSelectedItem().toString(),
//                    cmbAlat.getSelectedItem().toString(), cmbMassa.getSelectedItem().toString(), cmbObes.getSelectedItem().toString(), cmbProtusi.getSelectedItem().toString(),
//                    cmbMallam.getSelectedItem().toString(), cmbBuka.getSelectedItem().toString(), Tjarak.getText(), Tgigi.getText(), TpsAsa.getText(), Tpenyulit.getText(),
//                    Valid.mysql_real_escape_stringERM(TrencanaAnes.getText()), Valid.mysql_real_escape_stringERM(Tinstruksi.getText()), Sequel.cariIsi("select now()")
//                }) == true) {
//
//                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Evaluasi Pra Anestesi", "Simpan");
//                    TCari.setText(TNoRw.getText());
//                    emptTeks();
//                    tampil();
//                }
//            } catch (Exception e) {
//                System.out.println("Simpan Evaluasi Pra Anestesi : " + e);
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
            if (tbCatatan.getSelectedRow() > -1) {
//                try {
//                    if (Sequel.mengedittf("evaluasi_pra_anestesi_operasi", "waktu_simpan=?", "alergi_obat=?, anamnesa_dari=?, tgl_ruang=?, jam_ruang=?, diagnosa=?, rencana_tindakan=?, "
//                            + "td=?, bb=?, nadi=?, rr=?, tb=?, suhu=?, obat_dikonsumsi=?, ket_obat_dikonsumsi=?, tgl_operasi=?, nip_spesialis_bedah=?, nip_spesialis_anestesi=?, "
//                            + "riwayat_anestesi=?, bebas=?, leher_pendek=?, gerak_leher=?, sulit_ventilasi=?, alat_bantu=?, massa=?, obesitas=?, protusi=?, mallampathy=?, buka_mulut=?, "
//                            + "jarak_thyro=?, gigi=?, ps_asa=?, penyulit=?, rencana_anestesi=?, instruksi_anestesi=?", 35, new String[]{
//                                TalergiObat.getText(), cmbAnamnesa.getSelectedItem().toString(), Valid.SetTgl(TtglRuang.getSelectedItem() + ""),
//                                cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), Tdiagnosa.getText(), Valid.mysql_real_escape_stringERM(TrencanaTindakan.getText()),
//                                Ttd.getText(), Tbb.getText(), Tnadi.getText(), Trr.getText(), Ttb.getText(), Tsuhu.getText(), cmbObat.getSelectedItem().toString(),
//                                TketObat.getText(), Valid.SetTgl(TtglOperasi.getSelectedItem() + ""), nipSpesBedah, nipSpesAnes, Valid.mysql_real_escape_stringERM(TriwAnestesi.getText()),
//                                cmbBebas.getSelectedItem().toString(), cmbLeher.getSelectedItem().toString(), cmbGerak.getSelectedItem().toString(), cmbSulit.getSelectedItem().toString(),
//                                cmbAlat.getSelectedItem().toString(), cmbMassa.getSelectedItem().toString(), cmbObes.getSelectedItem().toString(), cmbProtusi.getSelectedItem().toString(),
//                                cmbMallam.getSelectedItem().toString(), cmbBuka.getSelectedItem().toString(), Tjarak.getText(), Tgigi.getText(), TpsAsa.getText(), Tpenyulit.getText(),
//                                Valid.mysql_real_escape_stringERM(TrencanaAnes.getText()), Valid.mysql_real_escape_stringERM(Tinstruksi.getText()),
//                                tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 46).toString()
//                            }) == true) {
//
//                        Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Evaluasi Pra Anestesi", "Ganti");
//                        TCari.setText(TNoRw.getText());
//                        tampil();
//                        emptTeks();
//                    }
//                } catch (Exception e) {
//                    System.out.println("Ganti Evaluasi Pra Anestesi : " + e);
//                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                tbCatatan.requestFocus();
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

    private void tbCatatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCatatanMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbCatatanMouseClicked

    private void tbCatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCatatanKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbCatatanKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbCatatan.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from evaluasi_pra_anestesi_operasi where waktu_simpan=?", 1, new String[]{
                    tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 46).toString()
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
            tbCatatan.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbCatatan.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
//            if (TalergiObat.getText().equals("")) {
//                param.put("alergiObat", "...........................");
//            } else {
//                param.put("alergiObat", TalergiObat.getText());
//            }
            
            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isi = "";
//                if (nipSpesAnes.equals("") || nipSpesAnes.equals("-") || nipSpesAnes.equals("--")) {
//                    JOptionPane.showMessageDialog(rootPane, "Maaf, nama dokter anestesi harus diisi dulu,..");
//                } else {
//                    isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
//                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
//                                    "Evaluasi Pra Anestesi", TnmDokterAnes.getText(),
//                                    Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from evaluasi_pra_anestesi_operasi where "
//                                            + "waktu_simpan='" + tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 46).toString() + "'"),
//                                    Sequel.cariIsi("select time(waktu_simpan) from evaluasi_pra_anestesi_operasi where "
//                                            + "waktu_simpan='" + tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 46).toString() + "'")) + "') from kalimat_tte where kode='001'");
//
//                    Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
//                    Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
//                    Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Evaluasi Pra Anestesi", Sequel.cariFolderPrintTte());
//                    param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
//                    param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));
//
//                    Valid.MyReport("rptEvaluasiPraAnestesiQr.jasper", "report", "::[ Evaluasi Pra Anestesi ]::",
//                            "SELECT now() tanggal", param);
//                    
//                    emptTeks();
//                    tampil();
//                    Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
//                }
            } else {
                Valid.MyReport("rptEvaluasiPraAnestesi.jasper", "report", "::[ Evaluasi Pra Anestesi ]::",
                        "SELECT now() tanggal", param);
                
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel..!!!!");
            tbCatatan.requestFocus();
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

    private void BtnDrBedahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDrBedahActionPerformed
        pilihDokter = 0;
        pilihDokter = 1;
        akses.setform("RMCatatanSedasiAnestesi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDrBedahActionPerformed

    private void BtnDokterAnesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterAnesActionPerformed
        pilihDokter = 0;
        pilihDokter = 2;
        akses.setform("RMCatatanSedasiAnestesi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterAnesActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMCatatanSedasiAnestesi");
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
            akses.setform("RMCatatanSedasiAnestesi");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void chkTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTotalActionPerformed
//        if (chkTotal.isSelected() == true) {
//            if (akses.getadmin() == true) {
//                TnipBangsal.setText("-");
//                TnmPerawatBangsal.setText("-");
//            } else {
//                TnipBangsal.setText(akses.getkode());
//                TnmPerawatBangsal.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipBangsal.getText() + "'"));
//            }
//        } else {
//            TnipBangsal.setText("-");
//            TnmPerawatBangsal.setText("-");
//        }
    }//GEN-LAST:event_chkTotalActionPerformed

    private void chkPartialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPartialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPartialActionPerformed

    private void chkGagalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkGagalActionPerformed
        TketGagal.setText("");
        if (chkGagal.isSelected() == true) {
            TketGagal.setEnabled(true);
            TketGagal.requestFocus();
        } else {
            TketGagal.setEnabled(false);
        }
    }//GEN-LAST:event_chkGagalActionPerformed

    private void chkEttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEttActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkEttActionPerformed

    private void chkLmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkLmaActionPerformed

    private void chkFimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFimaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkFimaActionPerformed

    private void chkTivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTivaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkTivaActionPerformed

    private void chkSpinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSpinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSpinalActionPerformed

    private void chkEpidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEpidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkEpidActionPerformed

    private void chkCseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCseActionPerformed

    private void chkInfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkInfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkInfilActionPerformed

    private void chkBlokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBlokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkBlokActionPerformed

    private void chkGaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkGaActionPerformed
        if (chkGa.isSelected() == true) {
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
            cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtk1.setSelectedIndex(0);
        } else {
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            cmbJam1.setSelectedIndex(0);
            cmbMnt1.setSelectedIndex(0);
            cmbDtk1.setSelectedIndex(0);
        }
    }//GEN-LAST:event_chkGaActionPerformed

    private void tbStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbStatusMouseClicked
//        if (tabMode1.getRowCount() != 0) {
//            try {
//                getDataTtv();
//            } catch (java.lang.NullPointerException e) {
//            }
//        }
    }//GEN-LAST:event_tbStatusMouseClicked

    private void tbStatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbStatusKeyPressed
//        if (tabMode1.getRowCount() != 0) {
//            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
//                try {
//                    getDataTtv();
//                } catch (java.lang.NullPointerException e) {
//                }
//            }
//        }
    }//GEN-LAST:event_tbStatusKeyPressed

    private void BtnBaruSttsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruSttsActionPerformed
        emptTeksStatus();
        urutkanData();
    }//GEN-LAST:event_BtnBaruSttsActionPerformed

    private void BtnTambahSttsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahSttsActionPerformed
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

            tabMode1.addRow(new String[]{TNoRw.getText(), urutData, cmbStatus.getSelectedItem().toString(), Tservo.getText(), Tiso.getText(), Thalo.getText(),
                Teth.getText(), Tn2o.getText(), To2.getText(), Tinfus.getText(), Tsistol.getText(), Tdistol.getText(), Tnadi.getText(), 
                "0000-00-00 00:00:00", Sequel.cariIsi("select now()")});
            BtnBaruSttsActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTambahSttsActionPerformed

    private void BtnHapusSttsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusSttsActionPerformed
        if (tbStatus.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data yang bisa dihapus..!!");
        } else {
            if (tbStatus.getSelectedRow() > -1) {
                int row = tbStatus.convertRowIndexToModel(tbStatus.getSelectedRow());
                tabMode1.removeRow(row);
                BtnBaruSttsActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbStatus.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusSttsActionPerformed

    private void BtnGantiSttsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiSttsActionPerformed
        if (tbStatus.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data yang bisa diganti..!!");
        } else {
            if (tbStatus.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else {
                    int row = tbStatus.convertRowIndexToModel(tbStatus.getSelectedRow());
                    tabMode1.setValueAt(TNoRw.getText(), row, 0);
                    tabMode1.setValueAt(urutanKe, row, 1);
                    tabMode1.setValueAt(cmbStatus.getSelectedItem().toString(), row, 2);
                    tabMode1.setValueAt(Tservo.getText(), row, 3);
                    tabMode1.setValueAt(Tiso.getText(), row, 4);
                    tabMode1.setValueAt(Thalo.getText(), row, 5);
                    tabMode1.setValueAt(Teth.getText(), row, 6);
                    tabMode1.setValueAt(Tn2o.getText(), row, 7);
                    tabMode1.setValueAt(To2.getText(), row, 8);
                    tabMode1.setValueAt(Tinfus.getText(), row, 9);
                    tabMode1.setValueAt(Tsistol.getText(), row, 10);
                    tabMode1.setValueAt(Tdistol.getText(), row, 11);
                    tabMode1.setValueAt(Tnadi.getText(), row, 12);
                    tabMode1.setValueAt("0000-00-00 00:00:00", row, 13);
                    tabMode1.setValueAt(Sequel.cariIsi("select now()"), row, 14);

                    BtnBaruSttsActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel...!!");
                tbStatus.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiSttsActionPerformed

    private void chkInduksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkInduksiActionPerformed
        if (chkInduksi.isSelected() == true) {
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
            cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtk2.setSelectedIndex(0);
        } else {
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
            cmbJam2.setSelectedIndex(0);
            cmbMnt2.setSelectedIndex(0);
            cmbDtk2.setSelectedIndex(0);
        }
    }//GEN-LAST:event_chkInduksiActionPerformed

    private void chkPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPasienActionPerformed
        if (chkPasien.isSelected() == true) {
            cmbJam3.setEnabled(true);
            cmbMnt3.setEnabled(true);
            cmbDtk3.setEnabled(true);
            cmbJam3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMnt3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtk3.setSelectedIndex(0);
        } else {
            cmbJam3.setEnabled(false);
            cmbMnt3.setEnabled(false);
            cmbDtk3.setEnabled(false);
            cmbJam3.setSelectedIndex(0);
            cmbMnt3.setSelectedIndex(0);
            cmbDtk3.setSelectedIndex(0);
        }
    }//GEN-LAST:event_chkPasienActionPerformed

    private void chkInsisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkInsisiActionPerformed
        if (chkInsisi.isSelected() == true) {
            cmbJam4.setEnabled(true);
            cmbMnt4.setEnabled(true);
            cmbDtk4.setEnabled(true);
            cmbJam4.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMnt4.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtk4.setSelectedIndex(0);
        } else {
            cmbJam4.setEnabled(false);
            cmbMnt4.setEnabled(false);
            cmbDtk4.setEnabled(false);
            cmbJam4.setSelectedIndex(0);
            cmbMnt4.setSelectedIndex(0);
            cmbDtk4.setSelectedIndex(0);
        }
    }//GEN-LAST:event_chkInsisiActionPerformed

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

    private void chkOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkOperasiActionPerformed
        if (chkOperasi.isSelected() == true) {
            cmbJam5.setEnabled(true);
            cmbMnt5.setEnabled(true);
            cmbDtk5.setEnabled(true);
            cmbJam5.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMnt5.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtk5.setSelectedIndex(0);
        } else {
            cmbJam5.setEnabled(false);
            cmbMnt5.setEnabled(false);
            cmbDtk5.setEnabled(false);
            cmbJam5.setSelectedIndex(0);
            cmbMnt5.setSelectedIndex(0);
            cmbDtk5.setSelectedIndex(0);
        }
    }//GEN-LAST:event_chkOperasiActionPerformed

    private void chkEkstubasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEkstubasiActionPerformed
        if (chkEkstubasi.isSelected() == true) {
            cmbJam6.setEnabled(true);
            cmbMnt6.setEnabled(true);
            cmbDtk6.setEnabled(true);
            cmbJam6.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMnt6.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtk6.setSelectedIndex(0);
        } else {
            cmbJam6.setEnabled(false);
            cmbMnt6.setEnabled(false);
            cmbDtk6.setEnabled(false);
            cmbJam6.setSelectedIndex(0);
            cmbMnt6.setSelectedIndex(0);
            cmbDtk6.setSelectedIndex(0);
        }
    }//GEN-LAST:event_chkEkstubasiActionPerformed

    private void chkPasienKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPasienKeluarActionPerformed
        if (chkPasienKeluar.isSelected() == true) {
            cmbJam7.setEnabled(true);
            cmbMnt7.setEnabled(true);
            cmbDtk7.setEnabled(true);
            cmbJam7.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMnt7.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtk7.setSelectedIndex(0);
        } else {
            cmbJam7.setEnabled(false);
            cmbMnt7.setEnabled(false);
            cmbDtk7.setEnabled(false);
            cmbJam7.setSelectedIndex(0);
            cmbMnt7.setSelectedIndex(0);
            cmbDtk7.setSelectedIndex(0);
        }
    }//GEN-LAST:event_chkPasienKeluarActionPerformed

    private void cmbJam5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam5MouseReleased
        AutoCompleteDecorator.decorate(cmbJam5);
    }//GEN-LAST:event_cmbJam5MouseReleased

    private void cmbMnt5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt5MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt5);
    }//GEN-LAST:event_cmbMnt5MouseReleased

    private void cmbDtk5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk5MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk5);
    }//GEN-LAST:event_cmbDtk5MouseReleased

    private void cmbJam6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam6MouseReleased
        AutoCompleteDecorator.decorate(cmbJam6);
    }//GEN-LAST:event_cmbJam6MouseReleased

    private void cmbMnt6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt6MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt6);
    }//GEN-LAST:event_cmbMnt6MouseReleased

    private void cmbDtk6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk6MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk6);
    }//GEN-LAST:event_cmbDtk6MouseReleased

    private void cmbDtk7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk7MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk7);
    }//GEN-LAST:event_cmbDtk7MouseReleased

    private void cmbMnt7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt7MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt7);
    }//GEN-LAST:event_cmbMnt7MouseReleased

    private void cmbJam7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam7MouseReleased
        AutoCompleteDecorator.decorate(cmbJam7);
    }//GEN-LAST:event_cmbJam7MouseReleased

    private void BtnPenataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenataActionPerformed
        akses.setform("RMCatatanSedasiAnestesi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPenataActionPerformed

    private void TdiagnosaPraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaPraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdiagnosaPasca.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaPraKeyPressed

    private void TdiagnosaPascaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaPascaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TprosedurOps.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaPascaKeyPressed

    private void TprosedurOpsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprosedurOpsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnDrBedah.requestFocus();
        }
    }//GEN-LAST:event_TprosedurOpsKeyPressed

    private void TpremedikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpremedikasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsedasi.requestFocus();
        }
    }//GEN-LAST:event_TpremedikasiKeyPressed

    private void TsedasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsedasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tinduksi.requestFocus();
        }
    }//GEN-LAST:event_TsedasiKeyPressed

    private void TinduksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TinduksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpelumpuh.requestFocus();
        }
    }//GEN-LAST:event_TinduksiKeyPressed

    private void TpelumpuhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpelumpuhKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTotal.requestFocus();
        }
    }//GEN-LAST:event_TpelumpuhKeyPressed

    private void TketGagalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketGagalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkEtt.requestFocus();
        }
    }//GEN-LAST:event_TketGagalKeyPressed

    private void TobatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Ttransfusi1.requestFocus();
        }
    }//GEN-LAST:event_TobatKeyPressed

    private void Ttransfusi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ttransfusi1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttransfusi2.requestFocus();
        }
    }//GEN-LAST:event_Ttransfusi1KeyPressed

    private void Ttransfusi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ttransfusi2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttransfusi3.requestFocus();
        }
    }//GEN-LAST:event_Ttransfusi2KeyPressed

    private void Ttransfusi3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ttransfusi3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttransfusi4.requestFocus();
        }
    }//GEN-LAST:event_Ttransfusi3KeyPressed

    private void Ttransfusi4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ttransfusi4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttransfusi5.requestFocus();
        }
    }//GEN-LAST:event_Ttransfusi4KeyPressed

    private void Ttransfusi5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ttransfusi5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbStatus.requestFocus();
        }
    }//GEN-LAST:event_Ttransfusi5KeyPressed

    private void TservoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TservoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tiso.requestFocus();
        }
    }//GEN-LAST:event_TservoKeyPressed

    private void TisoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TisoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Thalo.requestFocus();
        }
    }//GEN-LAST:event_TisoKeyPressed

    private void ThaloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThaloKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Teth.requestFocus();
        }
    }//GEN-LAST:event_ThaloKeyPressed

    private void TethKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TethKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tn2o.requestFocus();
        }
    }//GEN-LAST:event_TethKeyPressed

    private void Tn2oKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tn2oKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            To2.requestFocus();
        }
    }//GEN-LAST:event_Tn2oKeyPressed

    private void To2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_To2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tinfus.requestFocus();
        }
    }//GEN-LAST:event_To2KeyPressed

    private void TinfusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TinfusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsistol.requestFocus();
        }
    }//GEN-LAST:event_TinfusKeyPressed

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
            BtnTambahStts.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TapgarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TapgarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkInduksi.requestFocus();
        }
    }//GEN-LAST:event_TapgarKeyPressed

    private void TcatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tkristaloid.requestFocus();
        }
    }//GEN-LAST:event_TcatatanKeyPressed

    private void TkristaloidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkristaloidKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkoloid.requestFocus();
        }
    }//GEN-LAST:event_TkristaloidKeyPressed

    private void TkoloidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkoloidKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Twb.requestFocus();
        }
    }//GEN-LAST:event_TkoloidKeyPressed

    private void TwbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TwbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tprc.requestFocus();
        }
    }//GEN-LAST:event_TwbKeyPressed

    private void TprcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprcKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjmlCairan.requestFocus();
        }
    }//GEN-LAST:event_TprcKeyPressed

    private void TjmlCairanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjmlCairanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpendarahan.requestFocus();
        }
    }//GEN-LAST:event_TjmlCairanKeyPressed

    private void TpendarahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpendarahanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Turin.requestFocus();
        }
    }//GEN-LAST:event_TpendarahanKeyPressed

    private void TurinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TurinKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlain.requestFocus();
        }
    }//GEN-LAST:event_TurinKeyPressed

    private void TlainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnPenata.requestFocus();
        }
    }//GEN-LAST:event_TlainKeyPressed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        Tservo.setText("");
        Tiso.setText("");
        Thalo.setText("");
        Teth.setText("");
        Tn2o.setText("");
        To2.setText("");
        Tinfus.setText("");
        Tsistol.setText("");
        Tdistol.setText("");
        Tnadi.setText("");
        if (cmbStatus.getSelectedIndex() != 0) {
            Tservo.setEnabled(true);
            Tiso.setEnabled(true);
            Thalo.setEnabled(true);
            Teth.setEnabled(true);
            Tn2o.setEnabled(true);
            To2.setEnabled(true);
            Tinfus.setEnabled(true);
            Tsistol.setEnabled(true);
            Tdistol.setEnabled(true);
            Tnadi.setEnabled(true);
            BtnBaruStts.setEnabled(true);
            BtnTambahStts.setEnabled(true);
            BtnHapusStts.setEnabled(true);
            BtnGantiStts.setEnabled(true);
            Tservo.requestFocus();
        } else {
            Tservo.setEnabled(false);
            Tiso.setEnabled(false);
            Thalo.setEnabled(false);
            Teth.setEnabled(false);
            Tn2o.setEnabled(false);
            To2.setEnabled(false);
            Tinfus.setEnabled(false);
            Tsistol.setEnabled(false);
            Tdistol.setEnabled(false);
            Tnadi.setEnabled(false);
            BtnBaruStts.setEnabled(false);
            BtnTambahStts.setEnabled(false);
            BtnHapusStts.setEnabled(false);
            BtnGantiStts.setEnabled(false);
        }
    }//GEN-LAST:event_cmbStatusActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMCatatanSedasiAnestesi dialog = new RMCatatanSedasiAnestesi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBaruStts;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokterAnes;
    private widget.Button BtnDrBedah;
    private widget.Button BtnGanti;
    private widget.Button BtnGantiStts;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusStts;
    private widget.Button BtnKeluar;
    private widget.Button BtnPenata;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambahStts;
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
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tapgar;
    private widget.TextArea Tcatatan;
    private widget.TextBox TdiagnosaPasca;
    private widget.TextBox TdiagnosaPra;
    private widget.TextBox Tdistol;
    private widget.TextBox Teth;
    private widget.TextBox Thalo;
    private widget.TextBox Tinduksi;
    private widget.TextBox Tinfus;
    private widget.TextBox Tiso;
    private widget.TextBox TjmlCairan;
    private widget.TextBox TketGagal;
    private widget.TextBox Tkoloid;
    private widget.TextBox Tkristaloid;
    private widget.TextBox Tlain;
    private widget.TextBox Tn2o;
    private widget.TextBox Tnadi;
    private widget.TextBox TnmDokterAnes;
    private widget.TextBox TnmDrBedah;
    private widget.TextBox TnmPenata;
    private widget.TextBox To2;
    private widget.TextArea Tobat;
    private widget.TextBox Tpelumpuh;
    private widget.TextBox Tpendarahan;
    private widget.TextBox Tprc;
    private widget.TextBox Tpremedikasi;
    private widget.TextBox TprosedurOps;
    private widget.TextBox TrgRawat;
    private widget.TextBox Tsedasi;
    private widget.TextBox Tservo;
    private widget.TextBox Tsistol;
    private widget.TextBox Ttransfusi1;
    private widget.TextBox Ttransfusi2;
    private widget.TextBox Ttransfusi3;
    private widget.TextBox Ttransfusi4;
    private widget.TextBox Ttransfusi5;
    private widget.TextBox Turin;
    private widget.TextBox Twb;
    private widget.CekBox chkBlok;
    private widget.CekBox chkCse;
    private widget.CekBox chkEkstubasi;
    private widget.CekBox chkEpid;
    private widget.CekBox chkEtt;
    private widget.CekBox chkFima;
    private widget.CekBox chkGa;
    private widget.CekBox chkGagal;
    private widget.CekBox chkInduksi;
    private widget.CekBox chkInfil;
    private widget.CekBox chkInsisi;
    private widget.CekBox chkLma;
    private widget.CekBox chkOperasi;
    private widget.CekBox chkPartial;
    private widget.CekBox chkPasien;
    private widget.CekBox chkPasienKeluar;
    private widget.CekBox chkSpinal;
    private widget.CekBox chkTiva;
    private widget.CekBox chkTotal;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbDtk3;
    private widget.ComboBox cmbDtk4;
    private widget.ComboBox cmbDtk5;
    private widget.ComboBox cmbDtk6;
    private widget.ComboBox cmbDtk7;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbJam3;
    private widget.ComboBox cmbJam4;
    private widget.ComboBox cmbJam5;
    private widget.ComboBox cmbJam6;
    private widget.ComboBox cmbJam7;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbMnt3;
    private widget.ComboBox cmbMnt4;
    private widget.ComboBox cmbMnt5;
    private widget.ComboBox cmbMnt6;
    private widget.ComboBox cmbMnt7;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbStatus;
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
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel276;
    private widget.Label jLabel277;
    private widget.Label jLabel278;
    private widget.Label jLabel279;
    private widget.Label jLabel280;
    private widget.Label jLabel281;
    private widget.Label jLabel282;
    private widget.Label jLabel6;
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
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane19;
    private widget.ScrollPane scrollPane20;
    private widget.Table tbCatatan;
    private widget.Table tbStatus;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select ap.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, pg1.nama drBedah, pg2.nama drAnes, "
                    + "date_format(ap.tgl_ruang,'%d-%m-%Y') tglRuang, time_format(ap.jam_ruang,'%H:%i Wita') jamRuang from evaluasi_pra_anestesi_operasi ap "
                    + "inner join reg_periksa rp on rp.no_rawat=ap.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg1 on pg1.nik=ap.nip_spesialis_bedah inner join pegawai pg2 on pg2.nik=ap.nip_spesialis_anestesi where "
                    + "ap.tgl_operasi between ? and ? and ap.no_rawat LIKE ? or "
                    + "ap.tgl_operasi between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "ap.tgl_operasi between ? and ? and p.nm_pasien LIKE ? or "
                    + "ap.tgl_operasi between ? and ? and pg1.nama LIKE ? or "
                    + "ap.tgl_operasi between ? and ? and pg2.nama LIKE ? or "
                    + "ap.tgl_operasi between ? and ? and ap.diagnosa LIKE ? or "
                    + "ap.tgl_operasi between ? and ? and ap.anamnesa_dari LIKE ? or "
                    + "ap.tgl_operasi between ? and ? and ap.ruang_rawat LIKE ? ORDER BY ap.tgl_operasi desc");
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
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgllahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglRuang"),
                        rs.getString("jamRuang"),
                        rs.getString("anamnesa_dari"),
                        rs.getString("diagnosa"),
                        rs.getString("drBedah"),
                        rs.getString("drAnes"),
                        rs.getString("ruang_rawat"),
                        rs.getString("alergi_obat"),
                        rs.getString("anamnesa_dari"),
                        rs.getString("tgl_ruang"),
                        rs.getString("jam_ruang"),
                        rs.getString("diagnosa"),
                        rs.getString("rencana_tindakan"),
                        rs.getString("td"),
                        rs.getString("bb"),
                        rs.getString("nadi"),
                        rs.getString("rr"),
                        rs.getString("tb"),
                        rs.getString("suhu"),
                        rs.getString("obat_dikonsumsi"),
                        rs.getString("ket_obat_dikonsumsi"),
                        rs.getString("tgl_operasi"),
                        rs.getString("nip_spesialis_bedah"),
                        rs.getString("nip_spesialis_anestesi"),
                        rs.getString("riwayat_anestesi"),
                        rs.getString("bebas"),
                        rs.getString("leher_pendek"),
                        rs.getString("gerak_leher"),
                        rs.getString("sulit_ventilasi"),
                        rs.getString("alat_bantu"),
                        rs.getString("massa"),
                        rs.getString("obesitas"),
                        rs.getString("protusi"),
                        rs.getString("mallampathy"),
                        rs.getString("buka_mulut"),
                        rs.getString("jarak_thyro"),
                        rs.getString("gigi"),
                        rs.getString("ps_asa"),
                        rs.getString("penyulit"),
                        rs.getString("rencana_anestesi"),
                        rs.getString("instruksi_anestesi"),
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
        TdiagnosaPra.setText("");
        TdiagnosaPasca.setText("");
        TprosedurOps.setText("");
        nipDrBedah = "-";
        TnmDrBedah.setText("-");
        Tpremedikasi.setText("");
        Tsedasi.setText("");
        Tinduksi.setText("");
        Tpelumpuh.setText("");
        chkTotal.setSelected(false);
        chkPartial.setSelected(false);
        chkGagal.setSelected(false);
        TketGagal.setText("");
        TketGagal.setEnabled(false);
        chkEtt.setSelected(false);
        chkLma.setSelected(false);
        chkFima.setSelected(false);
        chkTiva.setSelected(false);
        chkSpinal.setSelected(false);
        chkEpid.setSelected(false);
        chkCse.setSelected(false);
        chkInfil.setSelected(false);
        chkBlok.setSelected(false);
        chkGa.setSelected(false);
        cmbJam1.setEnabled(false);
        cmbMnt1.setEnabled(false);
        cmbDtk1.setEnabled(false);        
        cmbJam1.setSelectedIndex(0);
        cmbMnt1.setSelectedIndex(0);
        cmbDtk1.setSelectedIndex(0);
        Tobat.setText("");
        Ttransfusi1.setText("");
        Ttransfusi2.setText("");
        Ttransfusi3.setText("");
        Ttransfusi4.setText("");
        Ttransfusi5.setText("");
        cmbStatus.setSelectedIndex(0);
        emptTeksStatus();
        BtnBaruStts.setEnabled(false);
        BtnTambahStts.setEnabled(false);
        BtnHapusStts.setEnabled(false);
        BtnGantiStts.setEnabled(false);
        Valid.tabelKosong(tabMode1);
        Tapgar.setText("");
        chkInduksi.setSelected(false);
        chkPasien.setSelected(false);
        chkInsisi.setSelected(false);
        chkOperasi.setSelected(false);
        chkEkstubasi.setSelected(false);
        chkPasienKeluar.setSelected(false);
        cmbJam2.setEnabled(false);
        cmbMnt2.setEnabled(false);
        cmbDtk2.setEnabled(false);
        cmbJam2.setSelectedIndex(0);
        cmbMnt2.setSelectedIndex(0);
        cmbDtk2.setSelectedIndex(0);
        cmbJam3.setEnabled(false);
        cmbMnt3.setEnabled(false);
        cmbDtk3.setEnabled(false);
        cmbJam3.setSelectedIndex(0);
        cmbMnt3.setSelectedIndex(0);
        cmbDtk3.setSelectedIndex(0);
        cmbJam4.setEnabled(false);
        cmbMnt4.setEnabled(false);
        cmbDtk4.setEnabled(false);
        cmbJam4.setSelectedIndex(0);
        cmbMnt4.setSelectedIndex(0);
        cmbDtk4.setSelectedIndex(0);
        cmbJam5.setEnabled(false);
        cmbMnt5.setEnabled(false);
        cmbDtk5.setEnabled(false);
        cmbJam5.setSelectedIndex(0);
        cmbMnt5.setSelectedIndex(0);
        cmbDtk5.setSelectedIndex(0);
        cmbJam6.setEnabled(false);
        cmbMnt6.setEnabled(false);
        cmbDtk6.setEnabled(false);
        cmbJam6.setSelectedIndex(0);
        cmbMnt6.setSelectedIndex(0);
        cmbDtk6.setSelectedIndex(0);
        cmbJam7.setEnabled(false);
        cmbMnt7.setEnabled(false);
        cmbDtk7.setEnabled(false);
        cmbJam7.setSelectedIndex(0);
        cmbMnt7.setSelectedIndex(0);
        cmbDtk7.setSelectedIndex(0);
        Tcatatan.setText("");
        Tkristaloid.setText("");
        Tkoloid.setText("");
        Twb.setText("");
        Tprc.setText("");
        TjmlCairan.setText("");
        Tpendarahan.setText("");
        Turin.setText("");
        Tlain.setText("");
        nipPenata = "-";
        TnmPenata.setText("-");
        nipDrAnes = "-";
        TnmDokterAnes.setText("-");
    }

    private void getData() {
        nipDrBedah = "";
        nipDrAnes = "";
        nipPenata = "";
        
        if (tbCatatan.getSelectedRow() != -1) {
            TNoRw.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 0).toString());
            TNoRM.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 1).toString());
            TPasien.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 2).toString());
//            TalergiObat.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 12).toString());
//            cmbAnamnesa.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 13).toString());
//            TrgRawat.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 4).toString());
//            Valid.SetTgl(TtglRuang, tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 14).toString());
            cmbJam1.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 15).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 15).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 15).toString().substring(6, 8));
//            Tdiagnosa.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 16).toString());
//            TrencanaTindakan.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 17).toString());
//            Ttd.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 18).toString());
//            Tbb.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 19).toString());
            Tnadi.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 20).toString());
//            Trr.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 21).toString());
//            Ttb.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 22).toString());
//            Tsuhu.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 23).toString());
//            cmbObat.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 24).toString());
//            TketObat.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 25).toString());
//            Valid.SetTgl(TtglOperasi, tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 26).toString());
            nipDrBedah = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 27).toString();
            TnmDrBedah.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 9).toString());
            nipDrAnes = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 28).toString();
            TnmDokterAnes.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 10).toString());
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnGanti.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TrgRawat.setText(ruangan);
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'"));
        TCari.setText(norw);
    }
    
    private void emptTeksStatus() {
        Tservo.setText("");
        Tiso.setText("");
        Thalo.setText("");
        Teth.setText("");
        Tn2o.setText("");
        To2.setText("");
        Tinfus.setText("");
        Tsistol.setText("");
        Tdistol.setText("");
        Tnadi.setText("");
    }
    
    private void urutkanData() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabMode1);
        tbStatus.setRowSorter(sorter);

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
    
    private void cekData() {
        if (chkTotal.isSelected() == true) {
            total = "ya";
        } else {
            total = "tidak";
        }
        
        if (chkPartial.isSelected() == true) {
            partial = "ya";
        } else {
            partial = "tidak";
        }
        
        if (chkGagal.isSelected() == true) {
            gagal = "ya";
        } else {
            gagal = "tidak";
        }
        
        if (chkEtt.isSelected() == true) {
            ett = "ya";
        } else {
            ett = "tidak";
        }
        
        if (chkLma.isSelected() == true) {
            lma = "ya";
        } else {
            lma = "tidak";
        }
        
        if (chkFima.isSelected() == true) {
            fima = "ya";
        } else {
            fima = "tidak";
        }
        
        if (chkTiva.isSelected() == true) {
            tiva = "ya";
        } else {
            tiva = "tidak";
        }
        
        if (chkSpinal.isSelected() == true) {
            spinal = "ya";
        } else {
            spinal = "tidak";
        }
        
        if (chkEpid.isSelected() == true) {
            epidural = "ya";
        } else {
            epidural = "tidak";
        }
        
        if (chkCse.isSelected() == true) {
            cse = "ya";
        } else {
            cse = "tidak";
        }
        
        if (chkInfil.isSelected() == true) {
            infil = "ya";
        } else {
            infil = "tidak";
        }
        
        if (chkBlok.isSelected() == true) {
            blok = "ya";
        } else {
            blok = "tidak";
        }
        
        if (chkGa.isSelected() == true) {
            ga = "ya";
        } else {
            ga = "tidak";
        }
        
        if (chkInduksi.isSelected() == true) {
            induksi = "ya";
        } else {
            induksi = "tidak";
        }
        
        if (chkPasien.isSelected() == true) {
            pasienSiap = "ya";
        } else {
            pasienSiap = "tidak";
        }
        
        if (chkInsisi.isSelected() == true) {
            insisi = "ya";
        } else {
            insisi = "tidak";
        }
        
        if (chkOperasi.isSelected() == true) {
            operasi = "ya";
        } else {
            operasi = "tidak";
        }
        
        if (chkEkstubasi.isSelected() == true) {
            ekstub = "ya";
        } else {
            ekstub = "tidak";
        }
        
        if (chkPasienKeluar.isSelected() == true) {
            pasienKlr = "ya";
        } else {
            pasienKlr = "tidak";
        }
    }
    
    private void dataCek() {
        if (total.equals("ya")) {
            chkTotal.setSelected(true);
        } else {
            chkTotal.setSelected(false);
        }
        
        if (partial.equals("ya")) {
            chkPartial.setSelected(true);
        } else {
            chkPartial.setSelected(false);
        }
        
        if (gagal.equals("ya")) {
            chkGagal.setSelected(true);
            TketGagal.setEnabled(true);
        } else {
            chkGagal.setSelected(false);
            TketGagal.setEnabled(false);
        }
        
        if (ett.equals("ya")) {
            chkEtt.setSelected(true);
        } else {
            chkEtt.setSelected(false);
        }
        
        if (lma.equals("ya")) {
            chkLma.setSelected(true);
        } else {
            chkLma.setSelected(false);
        }
        
        if (fima.equals("ya")) {
            chkFima.setSelected(true);
        } else {
            chkFima.setSelected(false);
        }
        
        if (tiva.equals("ya")) {
            chkTiva.setSelected(true);
        } else {
            chkTiva.setSelected(false);
        }
        
        if (spinal.equals("ya")) {
            chkSpinal.setSelected(true);
        } else {
            chkSpinal.setSelected(false);
        }
        
        if (epidural.equals("ya")) {
            chkEpid.setSelected(true);
        } else {
            chkEpid.setSelected(false);
        }
        
        if (cse.equals("ya")) {
            chkCse.setSelected(true);
        } else {
            chkCse.setSelected(false);
        }
        
        if (infil.equals("ya")) {
            chkInfil.setSelected(true);
        } else {
            chkInfil.setSelected(false);
        }
        
        if (blok.equals("ya")) {
            chkBlok.setSelected(true);
        } else {
            chkBlok.setSelected(false);
        }
        
        if (ga.equals("ya")) {
            chkGa.setSelected(true);
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
        } else {
            chkGa.setSelected(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
        }
        
        if (cmbStatus.getSelectedIndex() != 0) {
            Tservo.setEnabled(true);
            Tiso.setEnabled(true);
            Thalo.setEnabled(true);
            Teth.setEnabled(true);
            Tn2o.setEnabled(true);
            To2.setEnabled(true);
            Tinfus.setEnabled(true);
            Tsistol.setEnabled(true);
            Tdistol.setEnabled(true);
            Tnadi.setEnabled(true);
            BtnBaruStts.setEnabled(true);
            BtnTambahStts.setEnabled(true);
            BtnHapusStts.setEnabled(true);
            BtnGantiStts.setEnabled(true);
            Tservo.requestFocus();
        } else {
            Tservo.setEnabled(false);
            Tiso.setEnabled(false);
            Thalo.setEnabled(false);
            Teth.setEnabled(false);
            Tn2o.setEnabled(false);
            To2.setEnabled(false);
            Tinfus.setEnabled(false);
            Tsistol.setEnabled(false);
            Tdistol.setEnabled(false);
            Tnadi.setEnabled(false);
            BtnBaruStts.setEnabled(false);
            BtnTambahStts.setEnabled(false);
            BtnHapusStts.setEnabled(false);
            BtnGantiStts.setEnabled(false);
        }
        
        if (induksi.equals("ya")) {
            chkInduksi.setSelected(true);
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
        } else {
            chkInduksi.setSelected(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
        }
        
        if (pasienSiap.equals("ya")) {
            chkPasien.setSelected(true);
            cmbJam3.setEnabled(true);
            cmbMnt3.setEnabled(true);
            cmbDtk3.setEnabled(true);
        } else {
            chkPasien.setSelected(false);
            cmbJam3.setEnabled(false);
            cmbMnt3.setEnabled(false);
            cmbDtk3.setEnabled(false);
        }
        
        if (insisi.equals("ya")) {
            chkInsisi.setSelected(true);
            cmbJam4.setEnabled(true);
            cmbMnt4.setEnabled(true);
            cmbDtk4.setEnabled(true);
        } else {
            chkInsisi.setSelected(false);
            cmbJam4.setEnabled(false);
            cmbMnt4.setEnabled(false);
            cmbDtk4.setEnabled(false);
        }
        
        if (operasi.equals("ya")) {
            chkOperasi.setSelected(true);
            cmbJam5.setEnabled(true);
            cmbMnt5.setEnabled(true);
            cmbDtk5.setEnabled(true);
        } else {
            chkOperasi.setSelected(false);
            cmbJam5.setEnabled(false);
            cmbMnt5.setEnabled(false);
            cmbDtk5.setEnabled(false);
        }
        
        if (ekstub.equals("ya")) {
            chkEkstubasi.setSelected(true);
            cmbJam6.setEnabled(true);
            cmbMnt6.setEnabled(true);
            cmbDtk6.setEnabled(true);
        } else {
            chkEkstubasi.setSelected(false);
            cmbJam6.setEnabled(false);
            cmbMnt6.setEnabled(false);
            cmbDtk6.setEnabled(false);
        }
        
        if (pasienKlr.equals("ya")) {
            chkPasienKeluar.setSelected(true);
            cmbJam7.setEnabled(true);
            cmbMnt7.setEnabled(true);
            cmbDtk7.setEnabled(true);
        } else {
            chkPasienKeluar.setSelected(false);
            cmbJam7.setEnabled(false);
            cmbMnt7.setEnabled(false);
            cmbDtk7.setEnabled(false);
        }
    }
}
