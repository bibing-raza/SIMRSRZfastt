package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class RMLembarObservasiHemodialisis extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private int i = 0, x = 0;
    private double totIntak = 0, totOtput = 0;
    private DlgCariPetugas petugas;
    private DlgCariDokter dokter;
    private String nipAkses = "", nipDokter = "", wktSimpanObs = "", cekJamPost = "", cekMasalah = "", cekPerdarahan = "", cekFirst = "", cekAlergi = "", cekKram = "",
            cekHiperkalemia = "", cekHipertensi = "", cekHipotensi = "", cekNyeri = "", cekAritmia = "", cekGatal = "", cekDemam = "", cekMenggigil = "", tglReg = "",
            jamReg = "", sttsrwt = "", nilaiIntake = "", nilaiOutput = "";
    private frmUtama formUtama;
    private String isiAtauStrip(Object nilai) {
        String teks = Objects.toString(nilai, "").trim();
        return teks.isEmpty() ? "-" : teks;
    }

    /**
     * Creates new form DlgPemberianInfus
     *
     * @param parent
     * @param modal
     */
    public RMLembarObservasiHemodialisis(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Ruangan", "Jam Pre HD", "Jam Post HD", "Akses Vaskular", "Nama Dokter",
            "jam_pre_hd", "cek_jam_post", "jam_post_hd", "total_intake", "total_output", "total_balance", "hd", "kt_v", "total_uf", "masalah_akses", "perdarahan", "first",
            "alergi_thd_dializer", "kram", "hiperkalemia", "hipertensi", "hipotensi", "nyeri_dada", "aritmia", "gatal_gatal", "demam", "menggigil", "discharge_planing",
            "nip_akses_vaskuler", "obat", "catatan_medis", "nip_dokter", "waktu_simpan_obs", "waktuSimpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbObservasi.setModel(tabMode);
        tbObservasi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObservasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 37; i++) {
            TableColumn column = tbObservasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(220);
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
            }
        }
        tbObservasi.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode1 = new DefaultTableModel(null, new String[]{
            "No. Rawat", "Jam Intra HD", "QB", "UF Rate", "Tek. Darah", "Nadi", "Suhu", "Respi", "Intake (ml)", "Output (ml)", "Keterangan",
            "jam_intra", "nm_intake", "jml_intake", "waktu_simpan_obs", "waktu_simpan_intra", "waktuSimpanIntra", "jml_output"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbObsIntra.setModel(tabMode1);
        tbObsIntra.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObsIntra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 18; i++) {
            TableColumn column = tbObsIntra.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(65);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(65);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(70);
            } else if (i == 10) {
                column.setPreferredWidth(250);
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
            }
        }
        tbObsIntra.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode2 = new DefaultTableModel(null, new String[]{
            "No. Rawat", "Jam Intra HD", "QB", "UF Rate", "Tek. Darah", "Nadi", "Suhu", "Respi", "Intake (ml)", "Output (ml)", "Keterangan",
            "jam_intra", "nm_intake", "jml_intake", "waktu_simpan_obs", "waktu_simpan_intra", "waktuSimpanIntra", "jml_output"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbDetailObs.setModel(tabMode2);
        tbDetailObs.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDetailObs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 18; i++) {
            TableColumn column = tbDetailObs.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(65);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(65);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(70);
            } else if (i == 10) {
                column.setPreferredWidth(250);
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
            }
        }
        tbDetailObs.setDefaultRenderer(Object.class, new WarnaTable());

        TtotIntake.setDocument(new batasInput((int) 10).getKata(TtotIntake));
        TtotOutput.setDocument(new batasInput((int) 10).getKata(TtotOutput));
        TtotBalance.setDocument(new batasInput((int) 10).getKata(TtotBalance));
        THd.setDocument(new batasInput((int) 10).getKata(THd));
        Tkt.setDocument(new batasInput((int) 50).getKata(Tkt));
        TtotUF.setDocument(new batasInput((int) 10).getKata(TtotUF));
        Tqb.setDocument(new batasInput((int) 10).getKata(Tqb));
        Tuf.setDocument(new batasInput((int) 10).getKata(Tuf));
        Ttd.setDocument(new batasInput((int) 10).getKata(Ttd));
        Tnadi.setDocument(new batasInput((int) 10).getKata(Tnadi));
        Tsuhu.setDocument(new batasInput((int) 10).getKata(Tsuhu));
        Trespi.setDocument(new batasInput((int) 10).getKata(Trespi));
        TnmIntake.setDocument(new batasInput((int) 50).getKata(TnmIntake));
        TjmlIntake.setDocument(new batasInput((int) 10).getKata(TjmlIntake));
        TjmlOutput.setDocument(new batasInput((int) 10).getKata(TjmlOutput));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));

        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampil();
                }
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnObservasi = new javax.swing.JMenuItem();
        WindowIntraHD = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        panelGlass11 = new widget.panelisi();
        jLabel3 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        Tketerangan = new widget.TextBox();
        jLabel14 = new widget.Label();
        jLabel4 = new widget.Label();
        Tqb = new widget.TextBox();
        Ttd = new widget.TextBox();
        Tnadi = new widget.TextBox();
        jLabel29 = new widget.Label();
        Trespi = new widget.TextBox();
        jLabel30 = new widget.Label();
        Tsuhu = new widget.TextBox();
        jLabel28 = new widget.Label();
        TnmIntake = new widget.TextBox();
        jLabel8 = new widget.Label();
        label111 = new widget.Label();
        Tuf = new widget.TextBox();
        label112 = new widget.Label();
        jLabel31 = new widget.Label();
        jLabel15 = new widget.Label();
        TjmlIntake = new widget.TextBox();
        jLabel32 = new widget.Label();
        jLabel20 = new widget.Label();
        TjmlOutput = new widget.TextBox();
        jLabel33 = new widget.Label();
        Scroll1 = new widget.ScrollPane();
        tbObsIntra = new widget.Table();
        panelGlass9 = new widget.panelisi();
        BtnSimpan1 = new widget.Button();
        BtnBatal1 = new widget.Button();
        BtnHapus1 = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar1 = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        internalFrame1 = new widget.InternalFrame();
        panelGlass12 = new widget.panelisi();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel13 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel64 = new widget.Label();
        TtotIntake = new widget.TextBox();
        jLabel65 = new widget.Label();
        TtotOutput = new widget.TextBox();
        TtotBalance = new widget.TextBox();
        jLabel66 = new widget.Label();
        jLabel68 = new widget.Label();
        BtnAkses = new widget.Button();
        TnmAkses = new widget.TextBox();
        jLabel69 = new widget.Label();
        TnmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        chkSaya = new widget.CekBox();
        jLabel70 = new widget.Label();
        TrgRawat = new widget.TextBox();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        chkJamPost = new widget.CekBox();
        jLabel72 = new widget.Label();
        jLabel73 = new widget.Label();
        jLabel74 = new widget.Label();
        THd = new widget.TextBox();
        label120 = new widget.Label();
        Tkt = new widget.TextBox();
        TtotUF = new widget.TextBox();
        jLabel75 = new widget.Label();
        jLabel76 = new widget.Label();
        chkMasalahAkses = new widget.CekBox();
        chkPerdarahan = new widget.CekBox();
        chkFirst = new widget.CekBox();
        chkAlergi = new widget.CekBox();
        chkKramOtot = new widget.CekBox();
        chkHiperkalemia = new widget.CekBox();
        chkHipotensi = new widget.CekBox();
        chkHipertensi = new widget.CekBox();
        chkNyeriDada = new widget.CekBox();
        chkAritmia = new widget.CekBox();
        chkGatalGatal = new widget.CekBox();
        chkDemam = new widget.CekBox();
        chkMenggigil = new widget.CekBox();
        jLabel67 = new widget.Label();
        scrollPane6 = new widget.ScrollPane();
        Tdp = new widget.TextArea();
        jLabel77 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        Tobat = new widget.TextArea();
        jLabel78 = new widget.Label();
        scrollPane8 = new widget.ScrollPane();
        TcatatanMedis = new widget.TextArea();
        jLabel79 = new widget.Label();
        BtnTotal = new widget.Button();
        jLabel80 = new widget.Label();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObservasi = new widget.Table();
        Scroll2 = new widget.ScrollPane();
        tbDetailObs = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        jLabel71 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnObservasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnObservasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnObservasi.setText("Input Intra HD (Obs.)");
        MnObservasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnObservasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnObservasi.setIconTextGap(5);
        MnObservasi.setName("MnObservasi"); // NOI18N
        MnObservasi.setPreferredSize(new java.awt.Dimension(155, 26));
        MnObservasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnObservasiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnObservasi);

        WindowIntraHD.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowIntraHD.setName("WindowIntraHD"); // NOI18N
        WindowIntraHD.setUndecorated(true);
        WindowIntraHD.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Intra HD (Observasi) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 160));
        panelGlass11.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Jam :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass11.add(jLabel3);
        jLabel3.setBounds(2, 10, 100, 23);

        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        panelGlass11.add(cmbJam1);
        cmbJam1.setBounds(107, 10, 45, 23);

        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        panelGlass11.add(cmbMnt1);
        cmbMnt1.setBounds(157, 10, 45, 23);

        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        panelGlass11.add(cmbDtk1);
        cmbDtk1.setBounds(207, 10, 45, 23);

        Tketerangan.setName("Tketerangan"); // NOI18N
        panelGlass11.add(Tketerangan);
        Tketerangan.setBounds(293, 122, 480, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Nama Intake :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass11.add(jLabel14);
        jLabel14.setBounds(2, 94, 100, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nadi :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass11.add(jLabel4);
        jLabel4.setBounds(2, 66, 100, 23);

        Tqb.setName("Tqb"); // NOI18N
        Tqb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TqbKeyPressed(evt);
            }
        });
        panelGlass11.add(Tqb);
        Tqb.setBounds(107, 38, 70, 23);

        Ttd.setBackground(new java.awt.Color(245, 250, 240));
        Ttd.setName("Ttd"); // NOI18N
        Ttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdKeyPressed(evt);
            }
        });
        panelGlass11.add(Ttd);
        Ttd.setBounds(480, 38, 70, 23);

        Tnadi.setBackground(new java.awt.Color(245, 250, 240));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        panelGlass11.add(Tnadi);
        Tnadi.setBounds(107, 66, 70, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("x/mnt      Suhu : ");
        jLabel29.setName("jLabel29"); // NOI18N
        panelGlass11.add(jLabel29);
        jLabel29.setBounds(185, 66, 80, 23);

        Trespi.setBackground(new java.awt.Color(245, 250, 240));
        Trespi.setName("Trespi"); // NOI18N
        Trespi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrespiKeyPressed(evt);
            }
        });
        panelGlass11.add(Trespi);
        Trespi.setBounds(432, 66, 70, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("x/mnt");
        jLabel30.setName("jLabel30"); // NOI18N
        panelGlass11.add(jLabel30);
        jLabel30.setBounds(510, 66, 40, 23);

        Tsuhu.setBackground(new java.awt.Color(245, 250, 240));
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        panelGlass11.add(Tsuhu);
        Tsuhu.setBounds(268, 66, 70, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("°C      Respirasi : ");
        jLabel28.setName("jLabel28"); // NOI18N
        panelGlass11.add(jLabel28);
        jLabel28.setBounds(345, 66, 85, 23);

        TnmIntake.setBackground(new java.awt.Color(245, 250, 240));
        TnmIntake.setName("TnmIntake"); // NOI18N
        TnmIntake.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmIntakeKeyPressed(evt);
            }
        });
        panelGlass11.add(TnmIntake);
        TnmIntake.setBounds(107, 94, 200, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("QB :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass11.add(jLabel8);
        jLabel8.setBounds(2, 38, 100, 23);

        label111.setForeground(new java.awt.Color(0, 0, 0));
        label111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label111.setText("ml/mnt     UF Rate :");
        label111.setName("label111"); // NOI18N
        label111.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(label111);
        label111.setBounds(185, 38, 98, 23);

        Tuf.setName("Tuf"); // NOI18N
        Tuf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TufKeyPressed(evt);
            }
        });
        panelGlass11.add(Tuf);
        Tuf.setBounds(285, 38, 70, 23);

        label112.setForeground(new java.awt.Color(0, 0, 0));
        label112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label112.setText("ml.     Tekanan Darah :");
        label112.setName("label112"); // NOI18N
        label112.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(label112);
        label112.setBounds(363, 38, 115, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("mmHg");
        jLabel31.setName("jLabel31"); // NOI18N
        panelGlass11.add(jLabel31);
        jLabel31.setBounds(557, 38, 40, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Jml. Intake :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass11.add(jLabel15);
        jLabel15.setBounds(307, 94, 85, 23);

        TjmlIntake.setBackground(new java.awt.Color(245, 250, 240));
        TjmlIntake.setName("TjmlIntake"); // NOI18N
        TjmlIntake.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjmlIntakeKeyPressed(evt);
            }
        });
        panelGlass11.add(TjmlIntake);
        TjmlIntake.setBounds(398, 94, 70, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("ml.");
        jLabel32.setName("jLabel32"); // NOI18N
        panelGlass11.add(jLabel32);
        jLabel32.setBounds(475, 94, 40, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jumlah Output :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelGlass11.add(jLabel20);
        jLabel20.setBounds(2, 122, 100, 23);

        TjmlOutput.setBackground(new java.awt.Color(245, 250, 240));
        TjmlOutput.setName("TjmlOutput"); // NOI18N
        TjmlOutput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjmlOutputKeyPressed(evt);
            }
        });
        panelGlass11.add(TjmlOutput);
        TjmlOutput.setBounds(107, 122, 70, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("ml.         Keterangan :");
        jLabel33.setName("jLabel33"); // NOI18N
        panelGlass11.add(jLabel33);
        jLabel33.setBounds(183, 122, 110, 23);

        internalFrame5.add(panelGlass11, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbObsIntra.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObsIntra.setName("tbObsIntra"); // NOI18N
        tbObsIntra.getTableHeader().setReorderingAllowed(false);
        tbObsIntra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObsIntraMouseClicked(evt);
            }
        });
        tbObsIntra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObsIntraKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbObsIntra);

        internalFrame5.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnSimpan1);

        BtnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal1.setMnemonic('B');
        BtnBatal1.setText("Baru");
        BtnBatal1.setToolTipText("Alt+B");
        BtnBatal1.setName("BtnBatal1"); // NOI18N
        BtnBatal1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal1ActionPerformed(evt);
            }
        });
        BtnBatal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatal1KeyPressed(evt);
            }
        });
        panelGlass9.add(BtnBatal1);

        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setMnemonic('H');
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Alt+H");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnHapus1);

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
        panelGlass9.add(BtnEdit);

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
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });
        panelGlass9.add(BtnKeluar1);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        internalFrame5.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        WindowIntraHD.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Lembar Observasi Hemodialisis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.GridLayout(1, 2));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 385));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(195, 613));
        FormInput.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(247, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(319, 10, 410, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jam Pre HD :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 66, 110, 23);

        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(115, 66, 45, 23);

        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(165, 66, 45, 23);

        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(215, 66, 45, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Total Intake :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(287, 94, 88, 23);

        TtotIntake.setName("TtotIntake"); // NOI18N
        TtotIntake.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtotIntakeKeyPressed(evt);
            }
        });
        FormInput.add(TtotIntake);
        TtotIntake.setBounds(380, 94, 62, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("EVALUASI KEPERAWATAN :");
        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 122, 170, 23);

        TtotOutput.setName("TtotOutput"); // NOI18N
        TtotOutput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtotOutputKeyPressed(evt);
            }
        });
        FormInput.add(TtotOutput);
        TtotOutput.setBounds(550, 94, 62, 23);

        TtotBalance.setName("TtotBalance"); // NOI18N
        TtotBalance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtotBalanceKeyPressed(evt);
            }
        });
        FormInput.add(TtotBalance);
        TtotBalance.setBounds(700, 94, 62, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("HD :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 150, 110, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("<html><div style=\"text-align: right;\">Akses Vaskular :<br>Oleh&nbsp;&nbsp;</div></html>");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 348, 110, 30);

        BtnAkses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAkses.setMnemonic('2');
        BtnAkses.setToolTipText("Alt+2");
        BtnAkses.setName("BtnAkses"); // NOI18N
        BtnAkses.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAkses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAksesActionPerformed(evt);
            }
        });
        FormInput.add(BtnAkses);
        BtnAkses.setBounds(550, 348, 28, 23);

        TnmAkses.setEditable(false);
        TnmAkses.setToolTipText("Alt+C");
        TnmAkses.setName("TnmAkses"); // NOI18N
        TnmAkses.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TnmAkses);
        TnmAkses.setBounds(115, 348, 430, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Dokter :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 576, 110, 23);

        TnmDokter.setEditable(false);
        TnmDokter.setToolTipText("Alt+C");
        TnmDokter.setName("TnmDokter"); // NOI18N
        TnmDokter.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TnmDokter);
        TnmDokter.setBounds(115, 576, 430, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(550, 576, 28, 23);

        chkSaya.setBackground(new java.awt.Color(242, 242, 242));
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
        chkSaya.setBounds(585, 348, 90, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Ruangan :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 38, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setToolTipText("Alt+C");
        TrgRawat.setName("TrgRawat"); // NOI18N
        TrgRawat.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(115, 38, 430, 23);

        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(135, 94, 45, 23);

        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(185, 94, 45, 23);

        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(235, 94, 45, 23);

        chkJamPost.setBackground(new java.awt.Color(255, 255, 250));
        chkJamPost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJamPost.setText("Jam Post HD :");
        chkJamPost.setBorderPainted(true);
        chkJamPost.setBorderPaintedFlat(true);
        chkJamPost.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkJamPost.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkJamPost.setName("chkJamPost"); // NOI18N
        chkJamPost.setOpaque(false);
        chkJamPost.setPreferredSize(new java.awt.Dimension(175, 23));
        chkJamPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJamPostActionPerformed(evt);
            }
        });
        FormInput.add(chkJamPost);
        chkJamPost.setBounds(0, 94, 128, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel72.setText("ml.     Total Ouput :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(450, 94, 97, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel73.setText("ml.     Balance :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(620, 94, 78, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("ml.");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(770, 94, 20, 23);

        THd.setName("THd"); // NOI18N
        THd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THdKeyPressed(evt);
            }
        });
        FormInput.add(THd);
        THd.setBounds(115, 150, 50, 23);

        label120.setForeground(new java.awt.Color(0, 0, 0));
        label120.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label120.setText("Jam     Kt/V :");
        label120.setName("label120"); // NOI18N
        label120.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label120);
        label120.setBounds(170, 150, 65, 23);

        Tkt.setName("Tkt"); // NOI18N
        Tkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TktKeyPressed(evt);
            }
        });
        FormInput.add(Tkt);
        Tkt.setBounds(238, 150, 150, 23);

        TtotUF.setName("TtotUF"); // NOI18N
        TtotUF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtotUFKeyPressed(evt);
            }
        });
        FormInput.add(TtotUF);
        TtotUF.setBounds(700, 122, 62, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel75.setText("ml.");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(770, 122, 20, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Penyulit Selama HD :");
        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 178, 170, 23);

        chkMasalahAkses.setBackground(new java.awt.Color(255, 255, 250));
        chkMasalahAkses.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMasalahAkses.setText("Masalah Akses");
        chkMasalahAkses.setBorderPainted(true);
        chkMasalahAkses.setBorderPaintedFlat(true);
        chkMasalahAkses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMasalahAkses.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMasalahAkses.setName("chkMasalahAkses"); // NOI18N
        chkMasalahAkses.setOpaque(false);
        chkMasalahAkses.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMasalahAkses);
        chkMasalahAkses.setBounds(115, 206, 100, 23);

        chkPerdarahan.setBackground(new java.awt.Color(255, 255, 250));
        chkPerdarahan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerdarahan.setText("Perdarahan");
        chkPerdarahan.setBorderPainted(true);
        chkPerdarahan.setBorderPaintedFlat(true);
        chkPerdarahan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerdarahan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerdarahan.setName("chkPerdarahan"); // NOI18N
        chkPerdarahan.setOpaque(false);
        chkPerdarahan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerdarahan);
        chkPerdarahan.setBounds(225, 206, 85, 23);

        chkFirst.setBackground(new java.awt.Color(255, 255, 250));
        chkFirst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFirst.setText("First Use Syndrome");
        chkFirst.setBorderPainted(true);
        chkFirst.setBorderPaintedFlat(true);
        chkFirst.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFirst.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFirst.setName("chkFirst"); // NOI18N
        chkFirst.setOpaque(false);
        chkFirst.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkFirst);
        chkFirst.setBounds(320, 206, 123, 23);

        chkAlergi.setBackground(new java.awt.Color(255, 255, 250));
        chkAlergi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAlergi.setText("Alergi Terhadap Dializer");
        chkAlergi.setBorderPainted(true);
        chkAlergi.setBorderPaintedFlat(true);
        chkAlergi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAlergi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAlergi.setName("chkAlergi"); // NOI18N
        chkAlergi.setOpaque(false);
        chkAlergi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAlergi);
        chkAlergi.setBounds(452, 206, 140, 23);

        chkKramOtot.setBackground(new java.awt.Color(255, 255, 250));
        chkKramOtot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKramOtot.setText("Kram Otot");
        chkKramOtot.setBorderPainted(true);
        chkKramOtot.setBorderPaintedFlat(true);
        chkKramOtot.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKramOtot.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKramOtot.setName("chkKramOtot"); // NOI18N
        chkKramOtot.setOpaque(false);
        chkKramOtot.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKramOtot);
        chkKramOtot.setBounds(600, 206, 75, 23);

        chkHiperkalemia.setBackground(new java.awt.Color(255, 255, 250));
        chkHiperkalemia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHiperkalemia.setText("Hiperkalemia");
        chkHiperkalemia.setBorderPainted(true);
        chkHiperkalemia.setBorderPaintedFlat(true);
        chkHiperkalemia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHiperkalemia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHiperkalemia.setName("chkHiperkalemia"); // NOI18N
        chkHiperkalemia.setOpaque(false);
        chkHiperkalemia.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHiperkalemia);
        chkHiperkalemia.setBounds(685, 206, 90, 23);

        chkHipotensi.setBackground(new java.awt.Color(255, 255, 250));
        chkHipotensi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHipotensi.setText("Hipotensi");
        chkHipotensi.setBorderPainted(true);
        chkHipotensi.setBorderPaintedFlat(true);
        chkHipotensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHipotensi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHipotensi.setName("chkHipotensi"); // NOI18N
        chkHipotensi.setOpaque(false);
        chkHipotensi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHipotensi);
        chkHipotensi.setBounds(225, 234, 75, 23);

        chkHipertensi.setBackground(new java.awt.Color(255, 255, 250));
        chkHipertensi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHipertensi.setText("Hipertensi");
        chkHipertensi.setBorderPainted(true);
        chkHipertensi.setBorderPaintedFlat(true);
        chkHipertensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHipertensi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHipertensi.setName("chkHipertensi"); // NOI18N
        chkHipertensi.setOpaque(false);
        chkHipertensi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHipertensi);
        chkHipertensi.setBounds(115, 234, 85, 23);

        chkNyeriDada.setBackground(new java.awt.Color(255, 255, 250));
        chkNyeriDada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNyeriDada.setText("Nyeri Dada");
        chkNyeriDada.setBorderPainted(true);
        chkNyeriDada.setBorderPaintedFlat(true);
        chkNyeriDada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNyeriDada.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNyeriDada.setName("chkNyeriDada"); // NOI18N
        chkNyeriDada.setOpaque(false);
        chkNyeriDada.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNyeriDada);
        chkNyeriDada.setBounds(320, 234, 85, 23);

        chkAritmia.setBackground(new java.awt.Color(255, 255, 250));
        chkAritmia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAritmia.setText("Aritmia");
        chkAritmia.setBorderPainted(true);
        chkAritmia.setBorderPaintedFlat(true);
        chkAritmia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAritmia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAritmia.setName("chkAritmia"); // NOI18N
        chkAritmia.setOpaque(false);
        chkAritmia.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAritmia);
        chkAritmia.setBounds(415, 234, 65, 23);

        chkGatalGatal.setBackground(new java.awt.Color(255, 255, 250));
        chkGatalGatal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGatalGatal.setText("Gatal - Gatal");
        chkGatalGatal.setBorderPainted(true);
        chkGatalGatal.setBorderPaintedFlat(true);
        chkGatalGatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGatalGatal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGatalGatal.setName("chkGatalGatal"); // NOI18N
        chkGatalGatal.setOpaque(false);
        chkGatalGatal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGatalGatal);
        chkGatalGatal.setBounds(490, 234, 90, 23);

        chkDemam.setBackground(new java.awt.Color(255, 255, 250));
        chkDemam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDemam.setText("Demam");
        chkDemam.setBorderPainted(true);
        chkDemam.setBorderPaintedFlat(true);
        chkDemam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDemam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDemam.setName("chkDemam"); // NOI18N
        chkDemam.setOpaque(false);
        chkDemam.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDemam);
        chkDemam.setBounds(590, 234, 65, 23);

        chkMenggigil.setBackground(new java.awt.Color(255, 255, 250));
        chkMenggigil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMenggigil.setText("Menggigil / Dingin");
        chkMenggigil.setBorderPainted(true);
        chkMenggigil.setBorderPaintedFlat(true);
        chkMenggigil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMenggigil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMenggigil.setName("chkMenggigil"); // NOI18N
        chkMenggigil.setOpaque(false);
        chkMenggigil.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMenggigil);
        chkMenggigil.setBounds(665, 234, 115, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("<html><div style=\"text-align: right;\"><b>DISCHART :<br>PLANNING</b>&nbsp;&nbsp;</div></html>");
        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 262, 110, 30);

        scrollPane6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane6.setName("scrollPane6"); // NOI18N

        Tdp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tdp.setColumns(20);
        Tdp.setRows(5);
        Tdp.setName("Tdp"); // NOI18N
        Tdp.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tdp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdpKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(Tdp);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(115, 262, 620, 80);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Obat :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 404, 110, 23);

        scrollPane7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane7.setName("scrollPane7"); // NOI18N

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
        scrollPane7.setViewportView(Tobat);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(115, 404, 620, 80);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Catatan Medis :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(0, 490, 110, 23);

        scrollPane8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane8.setName("scrollPane8"); // NOI18N

        TcatatanMedis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TcatatanMedis.setColumns(20);
        TcatatanMedis.setRows(5);
        TcatatanMedis.setName("TcatatanMedis"); // NOI18N
        TcatatanMedis.setPreferredSize(new java.awt.Dimension(162, 2000));
        TcatatanMedis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanMedisKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(TcatatanMedis);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(115, 490, 620, 80);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("EVALUASI MEDIK :");
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 376, 170, 23);

        BtnTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnTotal.setMnemonic('2');
        BtnTotal.setText("Cek Total Intake & Output");
        BtnTotal.setGlassColor(new java.awt.Color(255, 102, 0));
        BtnTotal.setName("BtnTotal"); // NOI18N
        BtnTotal.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTotalActionPerformed(evt);
            }
        });
        FormInput.add(BtnTotal);
        BtnTotal.setBounds(380, 66, 190, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("         Total UF :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(620, 122, 78, 23);

        scrollInput.setViewportView(FormInput);

        panelGlass12.add(scrollInput);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(420, 900));
        internalFrame2.setLayout(new java.awt.GridLayout(2, 1));

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Observasi Pasien ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObservasi.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki atau dihapus");
        tbObservasi.setComponentPopupMenu(jPopupMenu1);
        tbObservasi.setName("tbObservasi"); // NOI18N
        tbObservasi.getTableHeader().setReorderingAllowed(false);
        tbObservasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObservasiMouseClicked(evt);
            }
        });
        tbObservasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObservasiKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObservasi);

        internalFrame2.add(Scroll);

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Observasi Yang Dilakukan (Intra HD) ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbDetailObs.setName("tbDetailObs"); // NOI18N
        tbDetailObs.getTableHeader().setReorderingAllowed(false);
        Scroll2.setViewportView(tbDetailObs);

        internalFrame2.add(Scroll2);

        panelGlass12.add(internalFrame2);

        internalFrame1.add(panelGlass12, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelGlass8.add(BtnBatal);

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

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Cetak Dalam Bentuk :");
        jLabel71.setName("jLabel71"); // NOI18N
        jLabel71.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel71);

        cmbPilihCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak.setName("cmbPilihCetak"); // NOI18N
        cmbPilihCetak.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass8.add(cmbPilihCetak);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setEnabled(false);
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
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-11-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-11-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

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

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            cekData();
            if (Sequel.menyimpantf("observasi_hemodialisis", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 31, new String[]{
                TNoRw.getText(), sttsrwt, TrgRawat.getText(), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                cekJamPost, cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), TtotIntake.getText(), TtotOutput.getText(),
                TtotBalance.getText(), THd.getText(), Tkt.getText(), TtotUF.getText(), cekMasalah, cekPerdarahan, cekFirst, cekAlergi, cekKram, cekHiperkalemia,
                cekHipertensi, cekHipotensi, cekNyeri, cekAritmia, cekGatal, cekDemam, cekMenggigil, Tdp.getText(), nipAkses, Tobat.getText(), TcatatanMedis.getText(),
                nipDokter, Sequel.cariIsi("select now()")
            }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Lembar Observasi Hemodialisis", "Simpan");
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

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (tbObservasi.getSelectedRow() > -1) {
            cekData();
            if (Sequel.mengedittf("observasi_hemodialisis", "waktu_simpan_obs=?", "jam_pre_hd=?, cek_jam_post=?, jam_post_hd=?, total_intake=?, total_output=?, total_balance=?, hd=?, "
                    + "kt_v=?, total_uf=?, masalah_akses=?, perdarahan=?, first=?, alergi_thd_dializer=?, kram=?, hiperkalemia=?, hipertensi=?, hipotensi=?, nyeri_dada=?, "
                    + "aritmia=?, gatal_gatal=?, demam=?, menggigil=?, discharge_planing=?, nip_akses_vaskuler=?, obat=?, catatan_medis=?, nip_dokter=?", 28, new String[]{
                        cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), cekJamPost,
                        cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), TtotIntake.getText(), TtotOutput.getText(),
                        TtotBalance.getText(), THd.getText(), Tkt.getText(), TtotUF.getText(), cekMasalah, cekPerdarahan, cekFirst, cekAlergi, cekKram, cekHiperkalemia,
                        cekHipertensi, cekHipotensi, cekNyeri, cekAritmia, cekGatal, cekDemam, cekMenggigil, Tdp.getText(), nipAkses, Tobat.getText(), TcatatanMedis.getText(),
                        nipDokter,
                        tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 36).toString()
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Lembar Observasi Hemodialisis", "Ganti");
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel.");
            tampil();
            tbObservasi.requestFocus();
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
        WindowIntraHD.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            frmUtama.getInstance().tutupDialogDiPanelUtama(this);
            WindowIntraHD.dispose();
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

    private void tbObservasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObservasiMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObservasiMouseClicked

    private void tbObservasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObservasiKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObservasiKeyPressed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void BtnAksesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAksesActionPerformed
        initPetugas();
        akses.setform("RMLembarObservasiHemodialisis");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnAksesActionPerformed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        initDokter();
        akses.setform("RMLembarObservasiHemodialisis");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipAkses = "-";
                TnmAkses.setText("-");
            } else {
                nipAkses = akses.getkode();
                TnmAkses.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipAkses + "'"));
            }
        } else {
            nipAkses = "-";
            TnmAkses.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void TtotIntakeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtotIntakeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtotOutput.requestFocus();
        }
    }//GEN-LAST:event_TtotIntakeKeyPressed

    private void TtotOutputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtotOutputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtotBalance.requestFocus();
        }
    }//GEN-LAST:event_TtotOutputKeyPressed

    private void TtotBalanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtotBalanceKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtotUF.requestFocus();
        }
    }//GEN-LAST:event_TtotBalanceKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbObservasi.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from observasi_hemodialisis where waktu_simpan_obs=?", 1, new String[]{
                    tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 36).toString()
                }) == true) {
                    Sequel.meghapus2("detail_observasi_hemodialisis", "waktu_simpan_obs", tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 36).toString());
                    TCari.setText(TNoRw.getText());
                    emptTeks();
                    tampil();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                TCari.setText(TNoRw.getText());
                emptTeks();
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel.");
            tampil();
            tbObservasi.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnGanti);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void MnObservasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnObservasiActionPerformed
        if (tbObservasi.getSelectedRow() > -1) {
            WindowIntraHD.setSize(829, internalFrame1.getHeight() - 40);
            WindowIntraHD.setLocationRelativeTo(internalFrame1);
            WindowIntraHD.setAlwaysOnTop(false);
            WindowIntraHD.setVisible(true);
            emptTeksObs();
            tampilIntra(wktSimpanObs);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel.");
            tbObservasi.requestFocus();
            tampil();
        }
    }//GEN-LAST:event_MnObservasiActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void TtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnadi.requestFocus();
        }
    }//GEN-LAST:event_TtdKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsuhu.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TrespiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrespiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmIntake.requestFocus();
        }
    }//GEN-LAST:event_TrespiKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trespi.requestFocus();
        }
    }//GEN-LAST:event_TsuhuKeyPressed

    private void TqbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TqbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tuf.requestFocus();
        }
    }//GEN-LAST:event_TqbKeyPressed

    private void TnmIntakeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmIntakeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjmlIntake.requestFocus();
        }
    }//GEN-LAST:event_TnmIntakeKeyPressed

    private void tbObsIntraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObsIntraMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataIntra1();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObsIntraMouseClicked

    private void tbObsIntraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObsIntraKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataIntra1();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObsIntraKeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            cekAngka();
            if (Sequel.menyimpantf("detail_observasi_hemodialisis", "?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 14, new String[]{
                TNoRw.getText(), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                Tqb.getText(), Tuf.getText(), Ttd.getText(), Tnadi.getText(), Tsuhu.getText(), Trespi.getText(), TnmIntake.getText(),
                nilaiIntake, nilaiOutput, Tketerangan.getText(), wktSimpanObs, Sequel.cariIsi("select now()")
            }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Intra HD (Observasi) Yang Dilakukan", "Simpan");
                emptTeksObs();
                tampilIntra(wktSimpanObs);
                updateTotal();
            }
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
        emptTeksObs();
        tampilIntra(wktSimpanObs);
    }//GEN-LAST:event_BtnBatal1ActionPerformed

    private void BtnBatal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatal1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeksObs();
            tampilIntra(wktSimpanObs);
        } else {
            Valid.pindah(evt, BtnSimpan1, BtnHapus1);
        }
    }//GEN-LAST:event_BtnBatal1KeyPressed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        if (tbObsIntra.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from detail_observasi_hemodialisis where waktu_simpan_intra=?", 1, new String[]{
                    tbObsIntra.getValueAt(tbObsIntra.getSelectedRow(), 16).toString()
                }) == true) {
                    tampilIntra(wktSimpanObs);
                    emptTeksObs();
                    updateTotal();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampilIntra(wktSimpanObs);
                emptTeksObs();
                updateTotal();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu.");
            tbObsIntra.requestFocus();
            tampilIntra(wktSimpanObs);
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (tbObsIntra.getSelectedRow() > -1) {
            cekAngka();
            if (Sequel.mengedittf("detail_observasi_hemodialisis", "waktu_simpan_intra=?", "jam_intra=?, qb=?, uf_rate=?, td=?, nadi=?, suhu=?, respirasi=?, "
                    + "nm_intake=?, jml_intake=?, jml_output=?, keterangan=?, waktu_simpan_obs=?", 13, new String[]{
                        cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                        Tqb.getText(), Tuf.getText(), Ttd.getText(), Tnadi.getText(), Tsuhu.getText(), Trespi.getText(), TnmIntake.getText(),
                        nilaiIntake, nilaiOutput, Tketerangan.getText(), wktSimpanObs,
                        tbObsIntra.getValueAt(tbObsIntra.getSelectedRow(), 16).toString()
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Intra HD (Observasi) Yang Dilakukan", "Ganti");
                tampilIntra(wktSimpanObs);
                emptTeksObs();
                updateTotal();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu.");
            tbObsIntra.requestFocus();
            tampilIntra(wktSimpanObs);
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowIntraHD.dispose();
        emptTeks();
        tampil();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluar1ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void cmbJam2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam2MouseReleased
        AutoCompleteDecorator.decorate(cmbJam2);
    }//GEN-LAST:event_cmbJam2MouseReleased

    private void cmbMnt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt2MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt2);
    }//GEN-LAST:event_cmbMnt2MouseReleased

    private void cmbDtk2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk2);
    }//GEN-LAST:event_cmbDtk2MouseReleased

    private void chkJamPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJamPostActionPerformed
        cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk2.setSelectedIndex(0);

        if (chkJamPost.isSelected() == true) {
            cmbJam2.setEnabled(true);
            cmbJam2.requestFocus();
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
            TtotIntake.setEnabled(true);
            TtotOutput.setEnabled(true);
            TtotBalance.setEnabled(true);
            TtotUF.setEnabled(true);
        } else {
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
            TtotIntake.setEnabled(false);
            TtotOutput.setEnabled(false);
            TtotBalance.setEnabled(false);
            TtotUF.setEnabled(false);
        }
    }//GEN-LAST:event_chkJamPostActionPerformed

    private void THdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkt.requestFocus();
        }
    }//GEN-LAST:event_THdKeyPressed

    private void TktKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TktKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkMasalahAkses.requestFocus();
        }
    }//GEN-LAST:event_TktKeyPressed

    private void TtotUFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtotUFKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            THd.requestFocus();
        }
    }//GEN-LAST:event_TtotUFKeyPressed

    private void TdpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnAkses.requestFocus();
        }
    }//GEN-LAST:event_TdpKeyPressed

    private void TobatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TcatatanMedis.requestFocus();
        }
    }//GEN-LAST:event_TobatKeyPressed

    private void TcatatanMedisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanMedisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnDokter.requestFocus();
        }
    }//GEN-LAST:event_TcatatanMedisKeyPressed

    private void TufKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TufKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttd.requestFocus();
        }
    }//GEN-LAST:event_TufKeyPressed

    private void TjmlIntakeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjmlIntakeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjmlOutput.requestFocus();
        }
    }//GEN-LAST:event_TjmlIntakeKeyPressed

    private void TjmlOutputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjmlOutputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tketerangan.requestFocus();
        }
    }//GEN-LAST:event_TjmlOutputKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbObservasi.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));

            param.put("preHD", cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " WITA");
            
            //data post hd
            List<Map<String, ?>> dataTabel = new ArrayList<>();
            for (int i = 0; i < tabMode2.getRowCount(); i++) {
                Map<String, Object> row = new HashMap<>();
                row.put("jam", tabMode2.getValueAt(i, 1).toString());
                row.put("qb", tabMode2.getValueAt(i, 2).toString());
                row.put("ufRate", tabMode2.getValueAt(i, 3).toString());
                row.put("td", tabMode2.getValueAt(i, 4).toString());
                row.put("nadi", tabMode2.getValueAt(i, 5).toString());
                row.put("suhu", tabMode2.getValueAt(i, 6).toString());                
                row.put("respi", tabMode2.getValueAt(i, 7).toString());
                row.put("intake", tabMode2.getValueAt(i, 8).toString());
                row.put("output", tabMode2.getValueAt(i, 9).toString());
                row.put("keterangan", tabMode2.getValueAt(i, 10).toString());
                dataTabel.add(row);
            }
            
            //jika data kosong
            if (dataTabel.isEmpty()) {
                Map<String, Object> row = new HashMap<>();
                row.put("jam", "-");
                row.put("qb", "-");
                row.put("ufRate", "-");
                row.put("td", "-");
                row.put("nadi", "-");
                row.put("suhu", "-");
                row.put("respi", "-");
                row.put("intake", "-");
                row.put("output", "-");
                row.put("keterangan", "-");
                dataTabel.add(row);
            }
            
            JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(dataTabel);
            
            if (chkJamPost.isSelected() == true) {
                param.put("postHD", cmbJam2.getSelectedItem().toString() + ":" + cmbMnt2.getSelectedItem().toString() + " WITA");
            } else {
                param.put("postHD", "......");
            }
            
            if (TtotIntake.getText().equals("")) {
                param.put("totIntake", "......");
            } else {
                param.put("totIntake", TtotIntake.getText());
            }
            
            if (TtotOutput.getText().equals("")) {
                param.put("totOutput", "......");
            } else {
                param.put("totOutput", TtotOutput.getText());
            }
            
            if (TtotBalance.getText().equals("")) {
                param.put("totBalan", "......");
            } else {
                param.put("totBalan", TtotBalance.getText());
            }
            
            if (TtotUF.getText().equals("")) {
                param.put("totUF", "......");
            } else {
                param.put("totUF", TtotUF.getText());
            }
            
            if (THd.getText().equals("")) {
                param.put("hd", "......");
            } else {
                param.put("hd", THd.getText());
            }
            
            if (Tkt.getText().equals("")) {
                param.put("kt", "......");
            } else {
                param.put("kt", Tkt.getText());
            }
            
            if (chkMasalahAkses.isSelected() == true) {
                param.put("masalah", "V");
            } else {
                param.put("masalah", "");
            }
            
            if (chkPerdarahan.isSelected() == true) {
                param.put("perdarahan", "V");
            } else {
                param.put("perdarahan", "");
            }
            
            if (chkFirst.isSelected() == true) {
                param.put("first", "V");
            } else {
                param.put("first", "");
            }
            
            if (chkAlergi.isSelected() == true) {
                param.put("alergi", "V");
            } else {
                param.put("alergi", "");
            }
            
            if (chkKramOtot.isSelected() == true) {
                param.put("kram", "V");
            } else {
                param.put("kram", "");
            }
            
            if (chkHiperkalemia.isSelected() == true) {
                param.put("hiperkalemia", "V");
            } else {
                param.put("hiperkalemia", "");
            }
            
            if (chkHipertensi.isSelected() == true) {
                param.put("hipertensi", "V");
            } else {
                param.put("hipertensi", "");
            }
            
            if (chkHipotensi.isSelected() == true) {
                param.put("hipotensi", "V");
            } else {
                param.put("hipotensi", "");
            }
            
            if (chkNyeriDada.isSelected() == true) {
                param.put("nyeri", "V");
            } else {
                param.put("nyeri", "");
            }
            
            if (chkAritmia.isSelected() == true) {
                param.put("arit", "V");
            } else {
                param.put("arit", "");
            }
            
            if (chkGatalGatal.isSelected() == true) {
                param.put("gatal", "V");
            } else {
                param.put("gatal", "");
            }
            
            if (chkDemam.isSelected() == true) {
                param.put("demam", "V");
            } else {
                param.put("demam", "");
            }
            
            if (chkMenggigil.isSelected() == true) {
                param.put("menggigil", "V");
            } else {
                param.put("menggigil", "");
            }
            
            if (Tdp.getText().equals("")) {
                param.put("dp", "......................");
            } else {
                param.put("dp", Tdp.getText());
            }
            
            param.put("nmAkses", TnmAkses.getText());
            
            if (Tobat.getText().equals("")) {
                param.put("obat", "......................");
            } else {
                param.put("obat", Tobat.getText());
            }
            
            if (TcatatanMedis.getText().equals("")) {
                param.put("catatan", "......................");
            } else {
                param.put("catatan", TcatatanMedis.getText());
            }
            
            param.put("nmDokter", TnmDokter.getText());

            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isiAkses = "", isiDokter = "", tgl = "", jam = "";
                tgl = Sequel.cariIsi("select date_format(waktu_simpan_obs,'%d/%m/%Y') from observasi_hemodialisis where "
                        + "waktu_simpan_obs='" + wktSimpanObs + "' order by convert(waktu_simpan_obs,DATETIME) desc limit 1");
                jam = Sequel.cariIsi("select time_format(waktu_simpan_obs,'%H:%i:%s') from observasi_hemodialisis where "
                        + "waktu_simpan_obs='" + wktSimpanObs + "' order by convert(waktu_simpan_obs,DATETIME) desc limit 1");
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));
                
                //akses vaskuler
                if (nipAkses.equals("") || nipAkses.equals("-") || nipAkses.equals("--")) {
                    param.put("lokasiQrAkses", "");
                } else {
                    isiAkses = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Lembar Observasi Hemodialisis", TnmAkses.getText() + " (Akses Vaskuler)",
                                    tgl, jam) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiAkses, Sequel.cariFolderTte(), "QRTteAkses.jpg", "select logo from setting");
                    param.put("lokasiQrAkses", Sequel.cariFolderTte() + File.separator + "QRTteAkses.jpg");
                }
                
                //dokter
                if (nipDokter.equals("") || nipDokter.equals("-") || nipDokter.equals("--")) {
                    param.put("lokasiQrDokter", "");
                } else {
                    isiDokter = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Lembar Observasi Hemodialisis", TnmDokter.getText() + " (Dokter)",
                                    tgl, jam) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiDokter, Sequel.cariFolderTte(), "QRTteDokter.jpg", "select logo from setting");
                    param.put("lokasiQrDokter", Sequel.cariFolderTte() + File.separator + "QRTteDokter.jpg");
                }

                Valid.MyReportDataSource("rptLembarObservasiHemodialisisQr.jasper", "report", "::[ Lembar Observasi Hemodialisis ]::", dataSource, param);
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            } else {
                Valid.MyReportDataSource("rptLembarObservasiHemodialisis.jasper", "report", "::[ Lembar Observasi Hemodialisis ]::", dataSource, param);
            }

            TCari.setText(TNoRw.getText());
            emptTeks();
            tampil();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel observasi pasien.");
            tbObservasi.requestFocus();
            tampil();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTotalActionPerformed
        if (tbObservasi.getSelectedRow() > -1) {
            tampilIntra(wktSimpanObs);
            updateTotal();
            TtotIntake.setText(Sequel.cariIsi("select total_intake from observasi_hemodialisis where waktu_simpan_obs='" + wktSimpanObs + "'"));
            TtotOutput.setText(Sequel.cariIsi("select total_output from observasi_hemodialisis where waktu_simpan_obs='" + wktSimpanObs + "'"));
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel.");
            tampil();
            tbObservasi.requestFocus();
        }
    }//GEN-LAST:event_BtnTotalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMLembarObservasiHemodialisis dialog = new RMLembarObservasiHemodialisis(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAkses;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnBatal1;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnTotal;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnObservasi;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    public widget.TextBox TCari;
    private widget.TextBox THd;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea TcatatanMedis;
    private widget.TextArea Tdp;
    private widget.TextBox TjmlIntake;
    private widget.TextBox TjmlOutput;
    private widget.TextBox Tketerangan;
    private widget.TextBox Tkt;
    private widget.TextBox Tnadi;
    private widget.TextBox TnmAkses;
    private widget.TextBox TnmDokter;
    private widget.TextBox TnmIntake;
    private widget.TextArea Tobat;
    private widget.TextBox Tqb;
    private widget.TextBox Trespi;
    private widget.TextBox TrgRawat;
    private widget.TextBox Tsuhu;
    private widget.TextBox Ttd;
    private widget.TextBox TtotBalance;
    private widget.TextBox TtotIntake;
    private widget.TextBox TtotOutput;
    private widget.TextBox TtotUF;
    private widget.TextBox Tuf;
    private javax.swing.JDialog WindowIntraHD;
    public widget.CekBox chkAlergi;
    public widget.CekBox chkAritmia;
    public widget.CekBox chkDemam;
    public widget.CekBox chkFirst;
    public widget.CekBox chkGatalGatal;
    public widget.CekBox chkHiperkalemia;
    public widget.CekBox chkHipertensi;
    public widget.CekBox chkHipotensi;
    public widget.CekBox chkJamPost;
    public widget.CekBox chkKramOtot;
    public widget.CekBox chkMasalahAkses;
    public widget.CekBox chkMenggigil;
    public widget.CekBox chkNyeriDada;
    public widget.CekBox chkPerdarahan;
    private widget.CekBox chkSaya;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbPilihCetak;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
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
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label111;
    private widget.Label label112;
    private widget.Label label120;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.Table tbDetailObs;
    private widget.Table tbObsIntra;
    private widget.Table tbObservasi;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode2);
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select oh.*, p.no_rkm_medis, p.nm_pasien, time_format(oh.jam_pre_hd,'%H:%i WITA') jamPre, "
                    + "if(oh.cek_jam_post='ya',time_format(oh.jam_post_hd,'%H:%i WITA'),'-') jamPos, pg1.nama nmAkses, pg2.nama nmDokter, "
                    + "date_format(oh.waktu_simpan_obs,'%Y-%m-%d %H:%i:%s') waktuSimpan from observasi_hemodialisis oh "
                    + "inner join reg_periksa rp on rp.no_rawat=oh.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg1 on pg1.nik=oh.nip_akses_vaskuler inner join pegawai pg2 on pg2.nik=oh.nip_dokter where "
                    + "date(oh.waktu_simpan_obs) between ? and ? and oh.no_rawat like ? or "
                    + "date(oh.waktu_simpan_obs) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(oh.waktu_simpan_obs) between ? and ? and p.nm_pasien like ? or "
                    + "date(oh.waktu_simpan_obs) between ? and ? and oh.ruang_rawat like ? or "
                    + "date(oh.waktu_simpan_obs) between ? and ? and pg1.nama like ? or "
                    + "date(oh.waktu_simpan_obs) between ? and ? and pg2.nama like ? order by date(oh.waktu_simpan_obs), oh.jam_pre_hd");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("ruang_rawat"),
                        rs.getString("jamPre"),
                        rs.getString("jamPos"),
                        rs.getString("nmAkses"),
                        rs.getString("nmDokter"),
                        rs.getString("jam_pre_hd"),
                        rs.getString("cek_jam_post"),
                        rs.getString("jam_post_hd"),
                        rs.getString("total_intake"),
                        rs.getString("total_output"),
                        rs.getString("total_balance"),
                        rs.getString("hd"),
                        rs.getString("kt_v"),
                        rs.getString("total_uf"),
                        rs.getString("masalah_akses"),
                        rs.getString("perdarahan"),
                        rs.getString("first"),
                        rs.getString("alergi_thd_dializer"),
                        rs.getString("kram"),
                        rs.getString("hiperkalemia"),
                        rs.getString("hipertensi"),
                        rs.getString("hipotensi"),
                        rs.getString("nyeri_dada"),
                        rs.getString("aritmia"),
                        rs.getString("gatal_gatal"),
                        rs.getString("demam"),
                        rs.getString("menggigil"),
                        rs.getString("discharge_planing"),
                        rs.getString("nip_akses_vaskuler"),
                        rs.getString("obat"),
                        rs.getString("catatan_medis"),
                        rs.getString("nip_dokter"),
                        rs.getString("waktu_simpan_obs"),
                        rs.getString("waktuSimpan")
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
    }

    public void emptTeks() {
        Valid.tabelKosong(tabMode2);
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        chkJamPost.setSelected(false);
        cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk2.setSelectedIndex(0);
        TtotIntake.setText("");
        TtotOutput.setText("");
        TtotBalance.setText("");
        TtotUF.setText("");
        cmbJam2.setEnabled(false);
        cmbMnt2.setEnabled(false);
        cmbDtk2.setEnabled(false);
        TtotIntake.setEnabled(false);
        TtotOutput.setEnabled(false);
        TtotBalance.setEnabled(false);
        TtotUF.setEnabled(false);
        THd.setText("");
        Tkt.setText("");
        chkMasalahAkses.setSelected(false);
        chkPerdarahan.setSelected(false);
        chkFirst.setSelected(false);
        chkAlergi.setSelected(false);
        chkKramOtot.setSelected(false);
        chkHiperkalemia.setSelected(false);
        chkHipertensi.setSelected(false);
        chkHipotensi.setSelected(false);
        chkNyeriDada.setSelected(false);
        chkAritmia.setSelected(false);
        chkGatalGatal.setSelected(false);
        chkDemam.setSelected(false);
        chkMenggigil.setSelected(false);
        Tdp.setText("");
        nipAkses = "-";
        TnmAkses.setText("-");
        Tobat.setText("");
        TcatatanMedis.setText("");
        nipDokter = "-";
        TnmDokter.setText("-");
        chkSaya.setSelected(false);
    }

    private void getData() {
        variabelBersih();
        if (tbObservasi.getSelectedRow() != -1) {
            TNoRw.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 0).toString());
            isPasien();
            TrgRawat.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 3).toString());
            cmbJam.setSelectedItem(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 8).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 8).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 8).toString().substring(6, 8));
            cekJamPost = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 9).toString();
            cmbJam2.setSelectedItem(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 10).toString().substring(0, 2));
            cmbMnt2.setSelectedItem(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 10).toString().substring(3, 5));
            cmbDtk2.setSelectedItem(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 10).toString().substring(6, 8));
            TtotIntake.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 11).toString());
            TtotOutput.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 12).toString());
            TtotBalance.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 13).toString());
            THd.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 14).toString());
            Tkt.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 15).toString());
            TtotUF.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 16).toString());
            cekMasalah = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 17).toString();
            cekPerdarahan = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 18).toString();
            cekFirst = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 19).toString();
            cekAlergi = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 20).toString();
            cekKram = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 21).toString();
            cekHiperkalemia = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 22).toString();
            cekHipertensi = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 23).toString();
            cekHipotensi = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 24).toString();
            cekNyeri = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 25).toString();
            cekAritmia = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 26).toString();
            cekGatal = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 27).toString();
            cekDemam = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 28).toString();
            cekMenggigil = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 29).toString();
            Tdp.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 30).toString());
            nipAkses = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 31).toString();
            TnmAkses.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 6).toString());
            Tobat.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 32).toString());
            TcatatanMedis.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 33).toString());
            nipDokter = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 34).toString();
            TnmDokter.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 7).toString());
            wktSimpanObs = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 36).toString();
            dataCek();
            tampilIntra(wktSimpanObs);
        }
    }

    private void getDataIntra1() {
        if (tbObsIntra.getSelectedRow() != -1) {
            cmbJam1.setSelectedItem(tbObsIntra.getValueAt(tbObsIntra.getSelectedRow(), 11).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbObsIntra.getValueAt(tbObsIntra.getSelectedRow(), 11).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbObsIntra.getValueAt(tbObsIntra.getSelectedRow(), 11).toString().substring(6, 8));
            Tqb.setText(tbObsIntra.getValueAt(tbObsIntra.getSelectedRow(), 2).toString());
            Tuf.setText(tbObsIntra.getValueAt(tbObsIntra.getSelectedRow(), 3).toString());
            Ttd.setText(tbObsIntra.getValueAt(tbObsIntra.getSelectedRow(), 4).toString());
            Tnadi.setText(tbObsIntra.getValueAt(tbObsIntra.getSelectedRow(), 5).toString());
            Tsuhu.setText(tbObsIntra.getValueAt(tbObsIntra.getSelectedRow(), 6).toString());
            Trespi.setText(tbObsIntra.getValueAt(tbObsIntra.getSelectedRow(), 7).toString());
            TnmIntake.setText(tbObsIntra.getValueAt(tbObsIntra.getSelectedRow(), 12).toString());
            TjmlIntake.setText(tbObsIntra.getValueAt(tbObsIntra.getSelectedRow(), 13).toString());
            TjmlOutput.setText(tbObsIntra.getValueAt(tbObsIntra.getSelectedRow(), 17).toString());
            Tketerangan.setText(tbObsIntra.getValueAt(tbObsIntra.getSelectedRow(), 10).toString());
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.gethemodialisa());
        BtnGanti.setEnabled(akses.gethemodialisa());
        BtnHapus.setEnabled(akses.gethemodialisa());
        MnObservasi.setEnabled(akses.gethemodialisa());
    }

    public void setData(String norw, String sttsRawat, String unit) {
        TNoRw.setText(norw);
        TrgRawat.setText(unit);
        sttsrwt = sttsRawat;
        isPasien();
        Valid.SetTgl(DTPCari1, tglReg);
        cmbJam.setSelectedItem(jamReg.toString().substring(0, 2));
        cmbMnt.setSelectedItem(jamReg.toString().substring(3, 5));
        cmbDtk.setSelectedItem(jamReg.toString().substring(6, 8));
        cmbJam2.setSelectedItem(jamReg.toString().substring(0, 2));
        cmbMnt2.setSelectedItem(jamReg.toString().substring(3, 5));
        cmbDtk2.setSelectedItem(jamReg.toString().substring(6, 8));
        TCari.setText(norw);

        if (akses.getadmin() == true) {
            nipAkses = "-";
            TnmAkses.setText("-");
        } else {
            nipAkses = akses.getkode();
            TnmAkses.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipAkses + "'"));
        }
    }

    private void emptTeksObs() {
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        Tqb.setText("");
        Tuf.setText("");
        Ttd.setText("");
        Tnadi.setText("");
        Tsuhu.setText("");
        Trespi.setText("");
        TnmIntake.setText("");
        TjmlIntake.setText("");
        TjmlOutput.setText("");
        Tketerangan.setText("");
    }

    public void tampilIntra(String wktSimpan) {
        Valid.tabelKosong(tabMode1);
        Valid.tabelKosong(tabMode2);
        try {
            ps1 = koneksi.prepareStatement("select do.*, time_format(do.jam_intra,'%H:%i WITA') jamIntra, "
                    + "concat(do.nm_intake,' ',if(do.jml_intake='0','.....',do.jml_intake),' ml.') intak, if(do.jml_output='0','-',do.jml_output) otput, "
                    + "date_format(do.waktu_simpan_intra,'%Y-%m-%d %H:%i:%s') waktuIntra from detail_observasi_hemodialisis do where "
                    + "do.waktu_simpan_obs='" + wktSimpan + "' order by do.jam_intra");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        rs1.getString("no_rawat"),
                        rs1.getString("jamIntra"),
                        rs1.getString("qb"),
                        rs1.getString("uf_rate"),
                        rs1.getString("td"),
                        rs1.getString("nadi"),
                        rs1.getString("suhu"),
                        rs1.getString("respirasi"),
                        rs1.getString("intak"),
                        rs1.getString("otput"),                        
                        rs1.getString("keterangan"),
                        rs1.getString("jam_intra"),
                        rs1.getString("nm_intake"),
                        rs1.getString("jml_intake"),
                        rs1.getString("waktu_simpan_obs"),
                        rs1.getString("waktu_simpan_intra"),
                        rs1.getString("waktuIntra"),
                        rs1.getString("jml_output")
                    });
                    
                    tabMode2.addRow(new String[]{
                        rs1.getString("no_rawat"),
                        rs1.getString("jamIntra"),
                        rs1.getString("qb"),
                        rs1.getString("uf_rate"),
                        rs1.getString("td"),
                        rs1.getString("nadi"),
                        rs1.getString("suhu"),
                        rs1.getString("respirasi"),
                        rs1.getString("intak"),
                        rs1.getString("otput"),                        
                        rs1.getString("keterangan"),
                        rs1.getString("jam_intra"),
                        rs1.getString("nm_intake"),
                        rs1.getString("jml_intake"),
                        rs1.getString("waktu_simpan_obs"),
                        rs1.getString("waktu_simpan_intra"),
                        rs1.getString("waktuIntra"),
                        rs1.getString("jml_output")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilIntra() : " + e);
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
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipAkses = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmAkses.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        chkSaya.setSelected(false);
                        BtnAkses.requestFocus();
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
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    if (akses.getform().equals("RMLembarObservasiHemodialisis")) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nipDokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                            TnmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        }
                        BtnDokter.requestFocus();
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

    private void cekData() {
        if (chkJamPost.isSelected() == true) {
            cekJamPost = "ya";
        } else {
            cekJamPost = "tidak";
        }

        if (chkMasalahAkses.isSelected() == true) {
            cekMasalah = "ya";
        } else {
            cekMasalah = "tidak";
        }

        if (chkPerdarahan.isSelected() == true) {
            cekPerdarahan = "ya";
        } else {
            cekPerdarahan = "tidak";
        }

        if (chkFirst.isSelected() == true) {
            cekFirst = "ya";
        } else {
            cekFirst = "tidak";
        }

        if (chkAlergi.isSelected() == true) {
            cekAlergi = "ya";
        } else {
            cekAlergi = "tidak";
        }

        if (chkKramOtot.isSelected() == true) {
            cekKram = "ya";
        } else {
            cekKram = "tidak";
        }

        if (chkHiperkalemia.isSelected() == true) {
            cekHiperkalemia = "ya";
        } else {
            cekHiperkalemia = "tidak";
        }

        if (chkHipertensi.isSelected() == true) {
            cekHipertensi = "ya";
        } else {
            cekHipertensi = "tidak";
        }
        
        if (chkHipotensi.isSelected() == true) {
            cekHipotensi = "ya";
        } else {
            cekHipotensi = "tidak";
        }

        if (chkNyeriDada.isSelected() == true) {
            cekNyeri = "ya";
        } else {
            cekNyeri = "tidak";
        }

        if (chkAritmia.isSelected() == true) {
            cekAritmia = "ya";
        } else {
            cekAritmia = "tidak";
        }

        if (chkGatalGatal.isSelected() == true) {
            cekGatal = "ya";
        } else {
            cekGatal = "tidak";
        }

        if (chkDemam.isSelected() == true) {
            cekDemam = "ya";
        } else {
            cekDemam = "tidak";
        }

        if (chkMenggigil.isSelected() == true) {
            cekMenggigil = "ya";
        } else {
            cekMenggigil = "tidak";
        }
    }
    
    private void dataCek() {
        if (cekJamPost.equals("ya")) {
            chkJamPost.setSelected(true);
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
            TtotIntake.setEnabled(true);
            TtotOutput.setEnabled(true);
            TtotBalance.setEnabled(true);
            TtotUF.setEnabled(true);
        } else {
            chkJamPost.setSelected(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
            TtotIntake.setEnabled(false);
            TtotOutput.setEnabled(false);
            TtotBalance.setEnabled(false);
            TtotUF.setEnabled(false);
        }
        
        if (cekMasalah.equals("ya")) {
            chkMasalahAkses.setSelected(true);
        } else {
            chkMasalahAkses.setSelected(false);
        }
        
        if (cekPerdarahan.equals("ya")) {
            chkPerdarahan.setSelected(true);
        } else {
            chkPerdarahan.setSelected(false);
        }
        
        if (cekFirst.equals("ya")) {
            chkFirst.setSelected(true);
        } else {
            chkFirst.setSelected(false);
        }
        
        if (cekAlergi.equals("ya")) {
            chkAlergi.setSelected(true);
        } else {
            chkAlergi.setSelected(false);
        }
        
        if (cekKram.equals("ya")) {
            chkKramOtot.setSelected(true);
        } else {
            chkKramOtot.setSelected(false);
        }
        
        if (cekHiperkalemia.equals("ya")) {
            chkHiperkalemia.setSelected(true);
        } else {
            chkHiperkalemia.setSelected(false);
        }
        
        if (cekHipertensi.equals("ya")) {
            chkHipertensi.setSelected(true);
        } else {
            chkHipertensi.setSelected(false);
        }
        
        if (cekHipotensi.equals("ya")) {
            chkHipotensi.setSelected(true);
        } else {
            chkHipotensi.setSelected(false);
        }
        
        if (cekNyeri.equals("ya")) {
            chkNyeriDada.setSelected(true);
        } else {
            chkNyeriDada.setSelected(false);
        }
        
        if (cekAritmia.equals("ya")) {
            chkAritmia.setSelected(true);
        } else {
            chkAritmia.setSelected(false);
        }
        
        if (cekGatal.equals("ya")) {
            chkGatalGatal.setSelected(true);
        } else {
            chkGatalGatal.setSelected(false);
        }
        
        if (cekDemam.equals("ya")) {
            chkDemam.setSelected(true);
        } else {
            chkDemam.setSelected(false);
        }
        
        if (cekMenggigil.equals("ya")) {
            chkMenggigil.setSelected(true);
        } else {
            chkMenggigil.setSelected(false);
        }
    }

    private void isPasien() {
        try {
            ps2 = koneksi.prepareStatement("select p.*, rp.tgl_registrasi, rp.jam_reg, pj.png_jawab from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join penjab pj on pj.kd_pj=rp.kd_pj where rp.no_rawat='" + TNoRw.getText() + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    TNoRM.setText(rs2.getString("no_rkm_medis"));
                    TPasien.setText(rs2.getString("nm_pasien"));
                    tglReg = rs2.getString("tgl_registrasi");
                    jamReg = rs2.getString("jam_reg");
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

    private void variabelBersih() {
        nipAkses = "";
        nipDokter = "";
        wktSimpanObs = "";
        cekJamPost = "";
        cekMasalah = "";
        cekPerdarahan = "";
        cekFirst = "";
        cekAlergi = "";
        cekKram = "";
        cekHiperkalemia = "";
        cekHipertensi = "";
        cekHipotensi = "";
        cekNyeri = "";
        cekAritmia = "";
        cekGatal = "";
        cekDemam = "";
        cekMenggigil = "";
        tglReg = "";
        jamReg = "";
    }

    private void cekAngka() {
        if (TjmlIntake.getText().equals("")) {
            nilaiIntake = "0";
        }

        if (TjmlOutput.getText().equals("")) {
            nilaiOutput = "0";
        }

        if (TjmlIntake.getText().contains(",") == true) {
            nilaiIntake = TjmlIntake.getText().replaceAll(",", ".");
        }

        if (TjmlOutput.getText().contains(",") == true) {
            nilaiOutput = TjmlOutput.getText().replaceAll(",", ".");
        }
        
        nilaiIntake = TjmlIntake.getText();
        nilaiOutput = TjmlOutput.getText();
    }
    
    private void updateTotal() {
        totIntak = 0;
        totOtput = 0;

        javax.swing.table.TableModel model = tbObsIntra.getModel();

        try {
            for (int baris = 0; baris < model.getRowCount(); baris++) {
                totIntak += bacaNilaiObservasi(model.getValueAt(baris, 13), baris, "Intake");
                totOtput += bacaNilaiObservasi(model.getValueAt(baris, 17), baris, "Output");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Total belum diperbarui");
            return;
        }

        Sequel.mengedit("observasi_hemodialisis", "waktu_simpan_obs='" + wktSimpanObs + "'", 
                "total_intake='" + Valid.SetAngka4(totIntak) + "', total_output='" + Valid.SetAngka4(totOtput) + "'");
    }

    private double bacaNilaiObservasi(Object nilai, int baris, String namaKolom) {
        String teks = nilai == null ? "" : nilai.toString().trim();

        if (teks.isEmpty() || teks.equals("-")) {
            return 0;
        }

        if (teks.matches("[0-9]+(\\.[0-9]+)?")) {
            double angka = Double.parseDouble(teks);
            if (Double.isFinite(angka)) {
                return angka;
            }
        }

        int barisTampilan = tbObsIntra.convertRowIndexToView(baris);
        String posisi = barisTampilan >= 0
                ? "baris " + (barisTampilan + 1)
                : "baris data " + (baris + 1) + " (tersembunyi oleh filter)";

        throw new IllegalArgumentException(
                namaKolom + " pada " + posisi + " berisi \"" + teks
                + "\". Masukkan angka seperti 100 atau 12.5."
        );
    }

    public void awalData() {
        tampil();
    }
}
