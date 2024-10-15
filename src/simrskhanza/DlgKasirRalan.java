package simrskhanza;

import bridging.BPJSApi;
import permintaan.DlgPermintaanLabRAZA;
import bridging.BPJSCekKartu;
import bridging.BPJSDataSEP;
import bridging.BPJSProgramPRB;
import bridging.BPJSSuratKontrol;
import bridging.CoronaPasien;
import bridging.DlgDataKanker;
import bridging.DlgDataTB;
import bridging.DlgSKDPBPJS;
import bridging.DlgVerifikasiKodeBoking;
import bridging.INACBGDaftarKlaim;
import bridging.INACBGPerawatanCorona;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import inventory.DlgResepObat;
import inventory.DlgPemberianObat;
import laporan.DlgDiagnosaPenyakit;
import keuangan.DlgBilingRalan;
import fungsi.WarnaTable;
import fungsi.WarnaTableKasirRalan;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCatatanResep;
import inventory.DlgPelaksanaPemberiObat;
import inventory.DlgPemberianObatPasien;
import inventory.DlgPenjualan;
import inventory.DlgPeresepanDokter;
import inventory.DlgReturJual;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import keuangan.DlgLhtPiutang;
import laporan.DlgDataHAIs;
import laporan.DlgHasilPenunjangMedis;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import permintaan.DlgCariPermintaanLab;
import permintaan.DlgCariPermintaanRadiologi;
import permintaan.DlgPermintaanRadiologi;
import permintaan.DlgSuratIstirahatSakit;
import permintaan.DlgSuratKeteranganDisabilitas;
import permintaan.DlgSuratKeteranganDokter;
import permintaan.DlgSuratKeteranganNapza;
import permintaan.DlgSuratKeteranganRohani;
import rekammedis.DlgCPPT;
import rekammedis.DlgFollowUpPerawatanTerapiARThiv;
import rekammedis.DlgPemeriksaanKlinisLabHIV;
import rekammedis.DlgRekamPsikologisAnak;
import rekammedis.DlgRekamPsikologisDewasa;
import rekammedis.DlgRekamPsikologisPerkawinan;
import rekammedis.DlgTerapiAntiretroviralHIV;
import rekammedis.DlgVerifikasiCPPT;
import rekammedis.RMAsesmenKebidananRalan;
import rekammedis.RMDokumenPenunjangMedis;
import rekammedis.RMLembarObservasi;
import rekammedis.RMPenilaianAwalKeperawatanIGDrz;
import rekammedis.RMPenilaianAwalKeperawatanKebidanan;
import rekammedis.RMPenilaianAwalKeperawatanRalan;
import rekammedis.RMPenilaianAwalMedikIGD;
import rekammedis.RMPenilaianAwalMedikObstetriRalan;
import rekammedis.RMPenilaianAwalMedisRalanGeriatri;
import rekammedis.RMTriaseIGD;
import rekammedis.RMPenilaianAwalMedisRalanMata;
import rekammedis.RMPenilaianAwalMedisRalanTHT;
import rekammedis.RMPenilaianTambahanGeriatri;
import rekammedis.RMProtokolKemoterapi;
import rekammedis.RMTindakanKedokteran;
import rekammedis.RMTransferSerahTerimaIGD;

/**
 *
 * @author dosen
 */
public final class DlgKasirRalan extends javax.swing.JDialog {
    private final DefaultTableModel tabModekasir, tabModeMati, tabModeKunjungan, tabModeResiko;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement psotomatis, psotomatis2, pskasir, pscaripiutang, psumurpasien, ps, ps1,
            pspasienboking, pspasien, psdiagnosa, psprosedur, psdokter, psMati, psRiwKunj, psLaprm,
            psFakIGD, psRes, psCek, psCetak;
    private ResultSet rskasir, rsumurpasien, rspasienboking, rspasien, rsdiagnosa, rsprosedur,
            rsdokter, rsMati, rsRiwKunj, rs, rs1, rsLaprm, rsFakIGD, rsRes, rsCek, rsCetak;
    private final Properties prop = new Properties();
    private Date cal = new Date();
    private String umur = "0", sttsumur = "Th", cekSEPboking = "", tglklaim = "", drdpjp = "", poli = "", crBayar = "", diagnosa_ok = "",
            sql = " pasien_mati.no_rkm_medis=pasien.no_rkm_medis ", cekPOLI = "", namadokter = "", nik = "", aktifjadwal = "", 
            namapoli = "", norw_dipilih = "", kddokter_dipilih = "", TPngJwb = "", TAlmt = "", THbngn = "", TBiaya = "", TStatus = "", sttsumur1 = "",
            kdsuku = "", kdbahasa = "", skorAsesIGD = "", kesimpulanGZanak = "", kesimpulanGZDewasa = "", TotSkorGZD = "", TotSkorGZA = "",
            faktorresikoigd = "", TotSkorRJ = "", kesimpulanResikoJatuh = "", kdItemrad = "", itemDipilih = "", tglRad = "", jamRad = "", pilihMenu = "",
            konfirmasi_terapi = "", aksesRM = "", dataKonfir = "";
    private String bangsal = Sequel.cariIsi("select kd_bangsal from set_lokasi limit 1"), nonota = "", URUTNOREG = "",
            sqlpsotomatis2 = "insert into rawat_jl_dr values (?,?,?,?,?,?,?,?,?,?,?)",
            sqlpsotomatis2petugas = "insert into rawat_jl_pr values (?,?,?,?,?,?,?,?,?,?,?)",
            sqlpsotomatis2dokterpetugas = "insert into rawat_jl_drpr values (?,?,?,?,?,?,?,?,?,?,?,?,?)",
            sqlpsotomatisdokterpetugas
            = "select jns_perawatan.kd_jenis_prw,jns_perawatan.material,jns_perawatan.bhp,"
            + "jns_perawatan.tarif_tindakandr,jns_perawatan.tarif_tindakanpr,jns_perawatan.total_byrdrpr,"
            + "jns_perawatan.kso,jns_perawatan.menejemen from set_otomatis_tindakan_ralan_dokterpetugas "
            + "inner join jns_perawatan on set_otomatis_tindakan_ralan_dokterpetugas.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
            + "where set_otomatis_tindakan_ralan_dokterpetugas.kd_dokter=? and set_otomatis_tindakan_ralan_dokterpetugas.kd_pj=?",
            sqlpsotomatispetugas
            = "select jns_perawatan.kd_jenis_prw,jns_perawatan.material,jns_perawatan.bhp,"
            + "jns_perawatan.tarif_tindakanpr,jns_perawatan.total_byrpr,jns_perawatan.kso,jns_perawatan.menejemen from set_otomatis_tindakan_ralan_petugas "
            + "inner join jns_perawatan on set_otomatis_tindakan_ralan_petugas.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
            + "where set_otomatis_tindakan_ralan_petugas.kd_pj=?",
            sqlpsotomatis
            = "select jns_perawatan.kd_jenis_prw,jns_perawatan.material,jns_perawatan.bhp,"
            + "jns_perawatan.tarif_tindakandr,jns_perawatan.total_byrdr,jns_perawatan.kso,jns_perawatan.menejemen from set_otomatis_tindakan_ralan "
            + "inner join jns_perawatan on set_otomatis_tindakan_ralan.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
            + "where set_otomatis_tindakan_ralan.kd_dokter=? and set_otomatis_tindakan_ralan.kd_pj=?";

    private int i = 0, pilihan = 0, sudah = 0, x = 0, n = 0, z = 0, cekSuratTindakan = 0, diagnosa_cek = 0, r = 0, cekPilihanRehab = 0,
            cekTotKun = 0, cekDiag = 0, cekPem = 0, cekPemPet = 0, cekRujukInternal = 0, panggilan = 0, cekHasilRad, cekSEP = 0,
            nomorpasien = 0, hasilantrian = 0, cekUmur = 0, totskorTriase = 0, skorGZ1 = 0, skorGZ2 = 0, skorYaGZ1 = 0, skor = 0;
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    public DlgCariDokter2 dokter1 = new DlgCariDokter2(null, false);
    public DlgCariDokter dokter2 = new DlgCariDokter(null, false);
    public DlgCariPoli poliklinik = new DlgCariPoli(null, false);
    public DlgPenanggungJawab penjab = new DlgPenanggungJawab(null, false);
    private DlgDiagnosaPenyakit resep = new DlgDiagnosaPenyakit(null, false);
    private DlgRawatJalan dlgrwjl2 = new DlgRawatJalan(null, false);
    private DlgPemberianObat dlgrwinap = new DlgPemberianObat(null, false);
    private BPJSApi api = new BPJSApi();

    /**
     * Creates new form DlgReg
     *
     * @param parent
     * @param modal
     */
    public DlgKasirRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(885, 674);

        tabModekasir = new DefaultTableModel(null, new String[]{
            "No.Rawat", "Kd.Dokter", "Dokter Dituju", "Nomer RM", "Nama Pasien", "Status", "Poliklinik/Inst.", "Jenis Bayar", "Jns. Kunjungan",
            "Reg. Online", "Tanggal", "Jam", "No. Reg.", "Status Klaim (RM IGD)", "No. Telpon/HP", "Alamat Pasien", "cek_asesmen_medik_igd", 
            "cek_penanganan_dokter_poli", "cekantrian", "tglreg", "kdpoli"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbKasirRalan.setModel(tabModekasir);
        tbKasirRalan.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbKasirRalan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 21; i++) {
            TableColumn column = tbKasirRalan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(330);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(130);
            } else if (i == 7) {
                column.setPreferredWidth(130);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(75);
            } else if (i == 11) {
                column.setPreferredWidth(65);
            } else if (i == 12) {
                column.setPreferredWidth(60);
            } else if (i == 13) {
                column.setPreferredWidth(122);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(350);
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
            }
        }
//        tbKasirRalan.setDefaultRenderer(Object.class, new WarnaTable());
        tbKasirRalan.setDefaultRenderer(Object.class, new WarnaTableKasirRalan());

        tabModeMati = new DefaultTableModel(null, new Object[]{"Tanggal", "Jam", "No.RM.", "Nama Pasien", "J.K.", "Tmp.Lahir",
            "Tgl.Lahir", "G.D.", "Stts.Nikah", "Agama", "Keterangan", "Tempat Meninggal", "ICD-10", "Unit Asal", "tgl_lahir", "tgl_mati"}) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPasienMati.setModel(tabModeMati);
        tbPasienMati.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbPasienMati.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 16; i++) {
            TableColumn column = tbPasienMati.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(30);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(30);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(170);
            } else if (i == 11) {
                column.setPreferredWidth(120);
            } else if (i == 12) {
                column.setPreferredWidth(65);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 15) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPasienMati.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeKunjungan = new DefaultTableModel(null, new Object[]{"No.", "No. Rawat", "No. RM.", "Tgl. Kunjungan",
            "Nama Pasien", "Dokter Pemeriksa", "Jenis Bayar", "kddokter"}) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbRiwayatKunj.setModel(tabModeKunjungan);
        tbRiwayatKunj.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbRiwayatKunj.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            TableColumn column = tbRiwayatKunj.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(55);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(190);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(110);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRiwayatKunj.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeResiko = new DefaultTableModel(null, new Object[]{
            "#", "KODE", "ASESMEN", "FAKTOR RESIKO", "SKALA", "SKOR"
        }) {
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbFaktorResiko.setModel(tabModeResiko);
        tbFaktorResiko.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbFaktorResiko.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbFaktorResiko.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(67);
            } else if (i == 3) {
                column.setPreferredWidth(265);
            } else if (i == 4) {
                column.setPreferredWidth(265);
            } else if (i == 5) {
                column.setPreferredWidth(40);
            }
        }
        tbFaktorResiko.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        CrPoli.setDocument(new batasInput((byte) 100).getKata(CrPoli));
        CrPtg.setDocument(new batasInput((byte) 100).getKata(CrPtg));

        kdboking.setDocument(new batasInput((byte) 15).getKata(kdboking));
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampilkasir();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampilkasir();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampilkasir();
                }
            });
        }

        poliklinik.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgKasirRalan")) {
                    if (pilihan == 1) {
                        kdpoli.setText(poliklinik.getTable().getValueAt(poliklinik.getTable().getSelectedRow(), 0).toString());
                        nmpoli.setText(poliklinik.getTable().getValueAt(poliklinik.getTable().getSelectedRow(), 1).toString());
                        tampilkasir();
                    } else if (pilihan == 2) {
                        kdpoli.setText(poliklinik.getTable().getValueAt(poliklinik.getTable().getSelectedRow(), 0).toString());
                        CrPoli.setText(poliklinik.getTable().getValueAt(poliklinik.getTable().getSelectedRow(), 1).toString());
                        CrPoli.requestFocus();
                        tampilkasir();
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

        poliklinik.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgKasirRalan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        poliklinik.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgKasirRalan")) {
                    if (penjab.getTable().getSelectedRow() != -1) {
                        kdpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 1).toString());
                        nmpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 2).toString());
                    }
                    kdpenjab.requestFocus();
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

        penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgKasirRalan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        dokter1.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (dokter1.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        KdDokter.setText(dokter1.getTable().getValueAt(dokter1.getTable().getSelectedRow(), 0).toString());
                        NmDokter.setText(dokter1.getTable().getValueAt(dokter1.getTable().getSelectedRow(), 1).toString());
                        nomorAuto();
                    } else if (pilihan == 2) {
                        kdDokterRujuk.setText(dokter1.getTable().getValueAt(dokter1.getTable().getSelectedRow(), 0).toString());
                        nmDokterRujuk.setText(dokter1.getTable().getValueAt(dokter1.getTable().getSelectedRow(), 1).toString());
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

        dokter2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgKasirRalan")) {
                    if (dokter2.getTable().getSelectedRow() != -1) {
                        if (pilihan == 1) {
                            kddokter.setText(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(), 0).toString());
                            TDokter.setText(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(), 1).toString());
                            kddokter.requestFocus();
                            tampilkasir();
                        } else if (pilihan == 2) {
                            CrPtg.setText(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(), 1).toString());
                            tampilkasir();
                            CrPtg.requestFocus();
                        } else {
                            kdDokterRujuk.setText(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(), 0).toString());
                            nmDokterRujuk.setText(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(), 1).toString());
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

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            namadokter = prop.getProperty("DOKTERAKTIFKASIRRALAN");
            namapoli = prop.getProperty("POLIAKTIFKASIRRALAN");
            aktifjadwal = prop.getProperty("JADWALDOKTERDIREGISTRASI");
            URUTNOREG = prop.getProperty("URUTNOREG");
            otomatisRefreshAntrian();
            otomatisRefresh();
        } catch (Exception ex) {
            namadokter = "";
            namapoli = "";
            aktifjadwal = "";
            URUTNOREG = "";
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnKamarInap = new javax.swing.JMenuItem();
        MnTindakanRalan = new javax.swing.JMenu();
        MnDataRalan = new javax.swing.JMenuItem();
        MnPeriksaLab = new javax.swing.JMenuItem();
        MnPeriksaRadiologi = new javax.swing.JMenuItem();
        MnOperasi = new javax.swing.JMenuItem();
        MnJadwalOperasi = new javax.swing.JMenuItem();
        MnUpdateJadwalOperasi = new javax.swing.JMenuItem();
        MnObatRalan = new javax.swing.JMenu();
        MnCatatanResep = new javax.swing.JMenuItem();
        MnReturJual = new javax.swing.JMenuItem();
        MnNoResep = new javax.swing.JMenuItem();
        MnResepFarmasi = new javax.swing.JMenuItem();
        MnDataPemberianObat = new javax.swing.JMenuItem();
        MnGanti = new javax.swing.JMenu();
        MnRehabMedik = new javax.swing.JMenuItem();
        MnPoli = new javax.swing.JMenuItem();
        MnDokter = new javax.swing.JMenuItem();
        MnPenjab = new javax.swing.JMenuItem();
        MnPermintaan = new javax.swing.JMenu();
        MnProtokolKemoterapi = new javax.swing.JMenuItem();
        MnEklaimINACBG = new javax.swing.JMenu();
        MnKlaimJKN = new javax.swing.JMenuItem();
        MnKlaimCOVID = new javax.swing.JMenuItem();
        MnKlaimKIPI = new javax.swing.JMenuItem();
        MnRencanaKontrolManual = new javax.swing.JMenuItem();
        MnRencanaKontrolNonBPJS = new javax.swing.JMenuItem();
        MnSuratPengantarRanap = new javax.swing.JMenuItem();
        MnSEPBPJS = new javax.swing.JMenuItem();
        MnSuratIstirahatSakit = new javax.swing.JMenuItem();
        MnSuratKeteranganNapza = new javax.swing.JMenuItem();
        MnSuratKeteranganRohani = new javax.swing.JMenuItem();
        MnSuratKeteranganDokter = new javax.swing.JMenuItem();
        MnSuratKeteranganDisabilitas = new javax.swing.JMenuItem();
        MnRegistrasiKeTBDOT = new javax.swing.JMenuItem();
        MnPermintaanLab = new javax.swing.JMenuItem();
        MnPermintaanRadiologi = new javax.swing.JMenuItem();
        MnCariPermintaanLab = new javax.swing.JMenuItem();
        MnCariPermintaanRad = new javax.swing.JMenuItem();
        MnAksesRM = new javax.swing.JMenu();
        MnDibuka = new javax.swing.JMenuItem();
        MnDitutup = new javax.swing.JMenuItem();
        MnRujukan = new javax.swing.JMenu();
        MnPoliInternal = new javax.swing.JMenuItem();
        MnRujuk = new javax.swing.JMenuItem();
        MnCekRujukanJKN = new javax.swing.JMenuItem();
        MnRekap = new javax.swing.JMenu();
        MnDietMakanan = new javax.swing.JMenuItem();
        MnRekapTindakanPerbup = new javax.swing.JMenuItem();
        MnStatus = new javax.swing.JMenu();
        ppBerkas = new javax.swing.JMenuItem();
        MnSudah = new javax.swing.JMenuItem();
        MnBelum = new javax.swing.JMenuItem();
        MnBatal = new javax.swing.JMenuItem();
        MnHapusData = new javax.swing.JMenuItem();
        MnInputData = new javax.swing.JMenu();
        MnKemenkes = new javax.swing.JMenu();
        MnTeridentifikasiTB = new javax.swing.JMenuItem();
        MnDataKanker = new javax.swing.JMenuItem();
        MnDokumenPenunjangMedis = new javax.swing.JMenuItem();
        MnNomorTB = new javax.swing.JMenuItem();
        MnSpirometri = new javax.swing.JMenuItem();
        ppProgramPRB = new javax.swing.JMenuItem();
        MnPemeriksaanKlinisLabHIV = new javax.swing.JMenuItem();
        MnTerapiAntiretroviralHIV = new javax.swing.JMenuItem();
        MnFollowUPPerawatanTerapiHIV = new javax.swing.JMenuItem();
        ppPasienCorona = new javax.swing.JMenuItem();
        ppSuratKontrol = new javax.swing.JMenuItem();
        MnDiet = new javax.swing.JMenuItem();
        MnDataHAIs = new javax.swing.JMenuItem();
        MnSensusParu = new javax.swing.JMenuItem();
        MnRekamMedis = new javax.swing.JMenu();
        MnRMGawatDarurat = new javax.swing.JMenu();
        MnDataTriaseIGD = new javax.swing.JMenu();
        MnInputDataTriaseIGD = new javax.swing.JMenuItem();
        MnLihatDataTriaseIGD = new javax.swing.JMenuItem();
        MnAssesmenMedikIGD = new javax.swing.JMenu();
        MnInputDataAssesmenMedikIGD = new javax.swing.JMenuItem();
        MnLihatDataAssesmenMedikIGD = new javax.swing.JMenuItem();
        MnAssesmenKeperawatanIGD = new javax.swing.JMenu();
        MnInputDataAssesmenKeperawatanIGD = new javax.swing.JMenuItem();
        MnLihatDataAssesmenKeperawatanIGD = new javax.swing.JMenuItem();
        MnTransferSerahTerimaIGD = new javax.swing.JMenu();
        MnInputDataTransferSerahTerimaIGD = new javax.swing.JMenuItem();
        MnLihatDataTransferSerahTerimaIGD = new javax.swing.JMenuItem();
        MnPemberianObat = new javax.swing.JMenuItem();
        MnPetugasPemberianObat = new javax.swing.JMenuItem();
        MnLembarObservasi = new javax.swing.JMenuItem();
        MnAsesmenMedikObstetriIGD = new javax.swing.JMenu();
        MnInputDataAsesmenMedikObstetri = new javax.swing.JMenuItem();
        MnLihatDataAsesmenMedikObstetri = new javax.swing.JMenuItem();
        MnCPPTIGD = new javax.swing.JMenu();
        MnInputDataCPPT = new javax.swing.JMenuItem();
        MnLihatDataCPPT = new javax.swing.JMenuItem();
        MnVerifCPPT = new javax.swing.JMenuItem();
        MnAsesmenKebidanan = new javax.swing.JMenu();
        MnInputDataKebidanan = new javax.swing.JMenuItem();
        MnLihatDataKebidanan = new javax.swing.JMenuItem();
        MnPenilaianAwalMedis = new javax.swing.JMenu();
        MnPenilaianAwalMedisRalanGeriatri = new javax.swing.JMenuItem();
        MnPenilaianTambahanGeriatri = new javax.swing.JMenuItem();
        MnPenilaianAwalKeperawatanRalan = new javax.swing.JMenuItem();
        MnPenilaianAwalKeperawatanKebidanan = new javax.swing.JMenuItem();
        MnPenilaianAwalMedisRalanTHT = new javax.swing.JMenuItem();
        MnPenilaianAwalMedisRalanMata = new javax.swing.JMenuItem();
        MnAsesmenMedikObstetri = new javax.swing.JMenuItem();
        ppDokumenJangMed = new javax.swing.JMenuItem();
        ppPersetujuanTindakan = new javax.swing.JMenuItem();
        ppRekamPsikologis = new javax.swing.JMenuItem();
        ppRekamPsikologiPerkawinan = new javax.swing.JMenuItem();
        MnIkhtisarPerawatanHIV = new javax.swing.JMenuItem();
        ppRiwayat = new javax.swing.JMenuItem();
        ppCekPaseinMati = new javax.swing.JMenuItem();
        ppPerawatanCorona = new javax.swing.JMenuItem();
        MnLabel = new javax.swing.JMenu();
        MrkChampion = new javax.swing.JMenuItem();
        MrkAjp = new javax.swing.JMenuItem();
        MrkCox = new javax.swing.JMenuItem();
        MrkCoxObat = new javax.swing.JMenuItem();
        MrkAlfa = new javax.swing.JMenuItem();
        MrkOlean = new javax.swing.JMenuItem();
        MrkKojico = new javax.swing.JMenuItem();
        MnLabelPxRanap1 = new javax.swing.JMenuItem();
        MnLabelPxRanap2 = new javax.swing.JMenuItem();
        MnHasilPemeriksaanPenunjang = new javax.swing.JMenuItem();
        MnCetakPemeriksaanTHT = new javax.swing.JMenuItem();
        MnNotepad = new javax.swing.JMenuItem();
        MnBilling = new javax.swing.JMenuItem();
        MnPiutangPasien = new javax.swing.JMenuItem();
        MnDiagnosa = new javax.swing.JMenuItem();
        MnLihatSEP = new javax.swing.JMenuItem();
        MnFormulirKlaim = new javax.swing.JMenuItem();
        MnLembarStatusPasien = new javax.swing.JMenuItem();
        MnStatusPasienAllKunjungan = new javax.swing.JMenuItem();
        ppCatatanPasien = new javax.swing.JMenuItem();
        WindowGantiDokter = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        jLabel13 = new widget.Label();
        kddokter = new widget.TextBox();
        TDokter = new widget.TextBox();
        btnCariDokter = new widget.Button();
        ChkSemua = new widget.CekBox();
        panelisi4 = new widget.panelisi();
        BtnSimpan1 = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        WindowGantiPoli = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        BtnSimpan4 = new widget.Button();
        jLabel18 = new widget.Label();
        kdpoli = new widget.TextBox();
        nmpoli = new widget.TextBox();
        btnCariPoli = new widget.Button();
        WindowCaraBayar = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        BtnCloseIn5 = new widget.Button();
        BtnSimpan5 = new widget.Button();
        jLabel19 = new widget.Label();
        kdpenjab = new widget.TextBox();
        nmpenjab = new widget.TextBox();
        btnBayar = new widget.Button();
        WindowPasienBooking = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        BtnCloseIn6 = new widget.Button();
        BtnSimpan6 = new widget.Button();
        jLabel20 = new widget.Label();
        kdpoli1 = new widget.TextBox();
        nmpoli1 = new widget.TextBox();
        jLabel21 = new widget.Label();
        kdboking = new widget.TextBox();
        jLabel22 = new widget.Label();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        norm = new widget.TextBox();
        nmpasien = new widget.TextBox();
        kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        jLabel25 = new widget.Label();
        KdDokter = new widget.TextBox();
        NmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel26 = new widget.Label();
        norwBoking = new widget.TextBox();
        tglPeriksa = new widget.Tanggal();
        jLabel35 = new widget.Label();
        Tantrian = new widget.TextBox();
        WindowPasienMati = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelisi1 = new widget.panelisi();
        jLabel7 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        BtnAll1 = new widget.Button();
        Scroll2 = new widget.ScrollPane();
        tbPasienMati = new widget.Table();
        panelisi2 = new widget.panelisi();
        jLabel8 = new widget.Label();
        jLabel9 = new widget.Label();
        rmMati = new widget.TextBox();
        nmpxMati = new widget.TextBox();
        tglMati = new widget.TextBox();
        jLabel11 = new widget.Label();
        jamMati = new widget.TextBox();
        jLabel27 = new widget.Label();
        ketMati = new widget.TextBox();
        jLabel28 = new widget.Label();
        icdMati = new widget.TextBox();
        jLabel29 = new widget.Label();
        Scroll3 = new widget.ScrollPane();
        desMati = new widget.TextArea();
        jLabel30 = new widget.Label();
        tglLahrMati = new widget.TextBox();
        jLabel31 = new widget.Label();
        tmptMati = new widget.TextBox();
        WindowRiwayatKunjungan = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        BtnCloseIn7 = new widget.Button();
        BtnLewati = new widget.Button();
        Scroll5 = new widget.ScrollPane();
        tbRiwayatKunj = new widget.Table();
        jLabel32 = new widget.Label();
        jLabel33 = new widget.Label();
        pasiendipilih = new widget.TextBox();
        BtnRM = new widget.Button();
        DlgNoSEP = new javax.swing.JDialog();
        internalFrame9 = new widget.InternalFrame();
        BtnCloseIn3 = new widget.Button();
        tglsep = new widget.TextBox();
        jLabel44 = new widget.Label();
        jLabel45 = new widget.Label();
        nosep = new widget.TextBox();
        jLabel46 = new widget.Label();
        jnsBayar = new widget.TextBox();
        BtnPrint2 = new widget.Button();
        WindowRehabMedik = new javax.swing.JDialog();
        internalFrame10 = new widget.InternalFrame();
        BtnCloseIn8 = new widget.Button();
        BtnSimpan7 = new widget.Button();
        jLabel34 = new widget.Label();
        cmbRM = new widget.ComboBox();
        DlgRegPoliTBDOT = new javax.swing.JDialog();
        internalFrame11 = new widget.InternalFrame();
        panelBiasa5 = new widget.PanelBiasa();
        BtnSimpanRujuk = new widget.Button();
        BtnKeluar5 = new widget.Button();
        jLabel42 = new widget.Label();
        kdDokterRujuk = new widget.TextBox();
        nmDokterRujuk = new widget.TextBox();
        BtnDokterRujuk = new widget.Button();
        Scroll8 = new widget.ScrollPane();
        tbFaktorResiko = new widget.Table();
        TabTindakanPencegahan = new javax.swing.JTabbedPane();
        panelBiasa6 = new widget.PanelBiasa();
        TabPencegahanDewasa = new javax.swing.JTabbedPane();
        panelBiasa8 = new widget.PanelBiasa();
        dewasaA = new widget.TextArea();
        panelBiasa9 = new widget.PanelBiasa();
        dewasaB = new widget.TextArea();
        panelBiasa10 = new widget.PanelBiasa();
        dewasaC = new widget.TextArea();
        panelBiasa7 = new widget.PanelBiasa();
        TabPencegahanAnak = new javax.swing.JTabbedPane();
        panelBiasa14 = new widget.PanelBiasa();
        anakA = new widget.TextArea();
        panelBiasa15 = new widget.PanelBiasa();
        anakB = new widget.TextArea();
        Kd2 = new widget.TextBox();
        TKdPny = new widget.TextBox();
        Tanggal = new widget.TextBox();
        Jam = new widget.TextBox();
        sepJkd = new widget.TextBox();
        sepJkdigd = new widget.TextBox();
        nmPasien = new widget.TextBox();
        dataGZ = new widget.TextBox();
        TNoReg = new widget.TextBox();
        cekKodeBoking = new widget.TextBox();
        cekTerdaftar = new widget.TextBox();
        cekPasien = new widget.TextBox();
        TglKunRwt = new widget.Tanggal();
        norwBARU = new widget.TextBox();
        tglPiutang = new widget.Tanggal();
        internalFrame1 = new widget.InternalFrame();
        jPanel2 = new javax.swing.JPanel();
        panelGlass6 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass7 = new widget.panelisi();
        jLabel14 = new widget.Label();
        CrPtg = new widget.TextBox();
        BtnSeek3 = new widget.Button();
        jLabel16 = new widget.Label();
        CrPoli = new widget.TextBox();
        BtnSeek4 = new widget.Button();
        jLabel94 = new widget.Label();
        TNoRw = new widget.TextBox();
        jLabel95 = new widget.Label();
        NoRM = new widget.TextBox();
        panelGlass8 = new widget.panelisi();
        jLabel15 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel12 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        BtnPxBooking = new widget.Button();
        ChkAutoRefres = new widget.CekBox();
        TabRawat = new javax.swing.JTabbedPane();
        Scroll1 = new widget.ScrollPane();
        tbKasirRalan = new widget.Table();
        internalFrame2 = new widget.InternalFrame();
        internalFrame18 = new widget.InternalFrame();
        internalFrame20 = new widget.InternalFrame();
        infobpjs = new widget.Label();
        internalFrame21 = new widget.InternalFrame();
        infoumum = new widget.Label();
        internalFrame22 = new widget.InternalFrame();
        infokhusus = new widget.Label();

        jPopupMenu1.setForeground(new java.awt.Color(60, 80, 50));
        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnKamarInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKamarInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKamarInap.setText("Kamar Inap");
        MnKamarInap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKamarInap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKamarInap.setIconTextGap(5);
        MnKamarInap.setName("MnKamarInap"); // NOI18N
        MnKamarInap.setPreferredSize(new java.awt.Dimension(220, 26));
        MnKamarInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKamarInapActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKamarInap);

        MnTindakanRalan.setBackground(new java.awt.Color(248, 253, 243));
        MnTindakanRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTindakanRalan.setText("Tindakan & Pemeriksaan");
        MnTindakanRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTindakanRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTindakanRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTindakanRalan.setIconTextGap(5);
        MnTindakanRalan.setName("MnTindakanRalan"); // NOI18N
        MnTindakanRalan.setOpaque(true);
        MnTindakanRalan.setPreferredSize(new java.awt.Dimension(220, 26));

        MnDataRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataRalan.setText("Data Tindakan Rawat Jalan");
        MnDataRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataRalan.setIconTextGap(5);
        MnDataRalan.setName("MnDataRalan"); // NOI18N
        MnDataRalan.setPreferredSize(new java.awt.Dimension(180, 26));
        MnDataRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataRalanActionPerformed(evt);
            }
        });
        MnTindakanRalan.add(MnDataRalan);

        MnPeriksaLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaLab.setText("Periksa Laboratorium");
        MnPeriksaLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaLab.setIconTextGap(5);
        MnPeriksaLab.setName("MnPeriksaLab"); // NOI18N
        MnPeriksaLab.setPreferredSize(new java.awt.Dimension(180, 26));
        MnPeriksaLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaLabActionPerformed(evt);
            }
        });
        MnTindakanRalan.add(MnPeriksaLab);

        MnPeriksaRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaRadiologi.setText("Periksa Radiologi");
        MnPeriksaRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaRadiologi.setIconTextGap(5);
        MnPeriksaRadiologi.setName("MnPeriksaRadiologi"); // NOI18N
        MnPeriksaRadiologi.setPreferredSize(new java.awt.Dimension(180, 26));
        MnPeriksaRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaRadiologiActionPerformed(evt);
            }
        });
        MnTindakanRalan.add(MnPeriksaRadiologi);

        MnOperasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnOperasi.setText("Tagihan Operasi/VK");
        MnOperasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnOperasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnOperasi.setIconTextGap(5);
        MnOperasi.setName("MnOperasi"); // NOI18N
        MnOperasi.setPreferredSize(new java.awt.Dimension(180, 26));
        MnOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnOperasiActionPerformed(evt);
            }
        });
        MnTindakanRalan.add(MnOperasi);

        MnJadwalOperasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJadwalOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnJadwalOperasi.setText("Jadwal Operasi");
        MnJadwalOperasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnJadwalOperasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnJadwalOperasi.setIconTextGap(5);
        MnJadwalOperasi.setName("MnJadwalOperasi"); // NOI18N
        MnJadwalOperasi.setPreferredSize(new java.awt.Dimension(180, 26));
        MnJadwalOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJadwalOperasiActionPerformed(evt);
            }
        });
        MnTindakanRalan.add(MnJadwalOperasi);

        MnUpdateJadwalOperasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUpdateJadwalOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUpdateJadwalOperasi.setText("Update Jadwal Operasi");
        MnUpdateJadwalOperasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUpdateJadwalOperasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUpdateJadwalOperasi.setIconTextGap(5);
        MnUpdateJadwalOperasi.setName("MnUpdateJadwalOperasi"); // NOI18N
        MnUpdateJadwalOperasi.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUpdateJadwalOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUpdateJadwalOperasiActionPerformed(evt);
            }
        });
        MnTindakanRalan.add(MnUpdateJadwalOperasi);

        jPopupMenu1.add(MnTindakanRalan);

        MnObatRalan.setBackground(new java.awt.Color(248, 253, 243));
        MnObatRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnObatRalan.setText("Obat");
        MnObatRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnObatRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnObatRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnObatRalan.setIconTextGap(5);
        MnObatRalan.setName("MnObatRalan"); // NOI18N
        MnObatRalan.setOpaque(true);
        MnObatRalan.setPreferredSize(new java.awt.Dimension(220, 26));

        MnCatatanResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCatatanResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCatatanResep.setText("Resep Dokter");
        MnCatatanResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCatatanResep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCatatanResep.setIconTextGap(5);
        MnCatatanResep.setName("MnCatatanResep"); // NOI18N
        MnCatatanResep.setPreferredSize(new java.awt.Dimension(160, 26));
        MnCatatanResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCatatanResepActionPerformed(evt);
            }
        });
        MnObatRalan.add(MnCatatanResep);

        MnReturJual.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnReturJual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnReturJual.setText("Retur Obat/Alkes");
        MnReturJual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnReturJual.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnReturJual.setIconTextGap(5);
        MnReturJual.setName("MnReturJual"); // NOI18N
        MnReturJual.setPreferredSize(new java.awt.Dimension(180, 26));
        MnReturJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnReturJualActionPerformed(evt);
            }
        });
        MnObatRalan.add(MnReturJual);

        MnNoResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnNoResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnNoResep.setText("Input No.Resep");
        MnNoResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnNoResep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnNoResep.setIconTextGap(5);
        MnNoResep.setName("MnNoResep"); // NOI18N
        MnNoResep.setPreferredSize(new java.awt.Dimension(160, 26));
        MnNoResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnNoResepActionPerformed(evt);
            }
        });
        MnObatRalan.add(MnNoResep);

        MnResepFarmasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnResepFarmasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnResepFarmasi.setText("Resep Dari Farmasi");
        MnResepFarmasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnResepFarmasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnResepFarmasi.setIconTextGap(5);
        MnResepFarmasi.setName("MnResepFarmasi"); // NOI18N
        MnResepFarmasi.setPreferredSize(new java.awt.Dimension(160, 26));
        MnResepFarmasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnResepFarmasiActionPerformed(evt);
            }
        });
        MnObatRalan.add(MnResepFarmasi);

        MnDataPemberianObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataPemberianObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataPemberianObat.setText("Data Pemberian Obat");
        MnDataPemberianObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataPemberianObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataPemberianObat.setIconTextGap(5);
        MnDataPemberianObat.setName("MnDataPemberianObat"); // NOI18N
        MnDataPemberianObat.setPreferredSize(new java.awt.Dimension(160, 26));
        MnDataPemberianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataPemberianObatActionPerformed(evt);
            }
        });
        MnObatRalan.add(MnDataPemberianObat);

        jPopupMenu1.add(MnObatRalan);

        MnGanti.setBackground(new java.awt.Color(248, 253, 243));
        MnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGanti.setText("Ganti");
        MnGanti.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGanti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGanti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGanti.setIconTextGap(5);
        MnGanti.setName("MnGanti"); // NOI18N
        MnGanti.setOpaque(true);
        MnGanti.setPreferredSize(new java.awt.Dimension(220, 26));

        MnRehabMedik.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRehabMedik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRehabMedik.setText("Pilihan Rehabilitasi Medik");
        MnRehabMedik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRehabMedik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRehabMedik.setIconTextGap(5);
        MnRehabMedik.setName("MnRehabMedik"); // NOI18N
        MnRehabMedik.setPreferredSize(new java.awt.Dimension(160, 26));
        MnRehabMedik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRehabMedikActionPerformed(evt);
            }
        });
        MnGanti.add(MnRehabMedik);

        MnPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPoli.setText("Poliklinik");
        MnPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPoli.setIconTextGap(5);
        MnPoli.setName("MnPoli"); // NOI18N
        MnPoli.setPreferredSize(new java.awt.Dimension(160, 26));
        MnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPoliActionPerformed(evt);
            }
        });
        MnGanti.add(MnPoli);

        MnDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokter.setText("Dokter Poli");
        MnDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokter.setIconTextGap(5);
        MnDokter.setName("MnDokter"); // NOI18N
        MnDokter.setPreferredSize(new java.awt.Dimension(160, 26));
        MnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokterActionPerformed(evt);
            }
        });
        MnGanti.add(MnDokter);

        MnPenjab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenjab.setText("Jenis Bayar");
        MnPenjab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPenjab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPenjab.setIconTextGap(5);
        MnPenjab.setName("MnPenjab"); // NOI18N
        MnPenjab.setPreferredSize(new java.awt.Dimension(160, 26));
        MnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenjabActionPerformed(evt);
            }
        });
        MnGanti.add(MnPenjab);

        jPopupMenu1.add(MnGanti);

        MnPermintaan.setBackground(new java.awt.Color(252, 255, 250));
        MnPermintaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaan.setText("Permintaan");
        MnPermintaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaan.setIconTextGap(5);
        MnPermintaan.setName("MnPermintaan"); // NOI18N
        MnPermintaan.setOpaque(true);
        MnPermintaan.setPreferredSize(new java.awt.Dimension(200, 26));

        MnProtokolKemoterapi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnProtokolKemoterapi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnProtokolKemoterapi.setText("Protokol Kemoterapi");
        MnProtokolKemoterapi.setEnabled(false);
        MnProtokolKemoterapi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnProtokolKemoterapi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnProtokolKemoterapi.setIconTextGap(5);
        MnProtokolKemoterapi.setName("MnProtokolKemoterapi"); // NOI18N
        MnProtokolKemoterapi.setPreferredSize(new java.awt.Dimension(200, 26));
        MnProtokolKemoterapi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnProtokolKemoterapiActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnProtokolKemoterapi);

        MnEklaimINACBG.setBackground(new java.awt.Color(248, 253, 243));
        MnEklaimINACBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnEklaimINACBG.setText("Bridging Eklaim INACBG");
        MnEklaimINACBG.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnEklaimINACBG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnEklaimINACBG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnEklaimINACBG.setIconTextGap(5);
        MnEklaimINACBG.setName("MnEklaimINACBG"); // NOI18N
        MnEklaimINACBG.setOpaque(true);
        MnEklaimINACBG.setPreferredSize(new java.awt.Dimension(220, 26));

        MnKlaimJKN.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKlaimJKN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKlaimJKN.setText("Klaim JKN");
        MnKlaimJKN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKlaimJKN.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKlaimJKN.setIconTextGap(5);
        MnKlaimJKN.setName("MnKlaimJKN"); // NOI18N
        MnKlaimJKN.setPreferredSize(new java.awt.Dimension(130, 26));
        MnKlaimJKN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKlaimJKNActionPerformed(evt);
            }
        });
        MnEklaimINACBG.add(MnKlaimJKN);

        MnKlaimCOVID.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKlaimCOVID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKlaimCOVID.setText("Klaim COVID-19");
        MnKlaimCOVID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKlaimCOVID.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKlaimCOVID.setIconTextGap(5);
        MnKlaimCOVID.setName("MnKlaimCOVID"); // NOI18N
        MnKlaimCOVID.setPreferredSize(new java.awt.Dimension(130, 26));
        MnKlaimCOVID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKlaimCOVIDActionPerformed(evt);
            }
        });
        MnEklaimINACBG.add(MnKlaimCOVID);

        MnKlaimKIPI.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKlaimKIPI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKlaimKIPI.setText("Klaim KIPI");
        MnKlaimKIPI.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKlaimKIPI.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKlaimKIPI.setIconTextGap(5);
        MnKlaimKIPI.setName("MnKlaimKIPI"); // NOI18N
        MnKlaimKIPI.setPreferredSize(new java.awt.Dimension(130, 26));
        MnKlaimKIPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKlaimKIPIActionPerformed(evt);
            }
        });
        MnEklaimINACBG.add(MnKlaimKIPI);

        MnPermintaan.add(MnEklaimINACBG);

        MnRencanaKontrolManual.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRencanaKontrolManual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRencanaKontrolManual.setText("Rencana Kontrol BPJS (MANUAL)");
        MnRencanaKontrolManual.setEnabled(false);
        MnRencanaKontrolManual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRencanaKontrolManual.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRencanaKontrolManual.setIconTextGap(5);
        MnRencanaKontrolManual.setName("MnRencanaKontrolManual"); // NOI18N
        MnRencanaKontrolManual.setPreferredSize(new java.awt.Dimension(200, 26));
        MnRencanaKontrolManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRencanaKontrolManualActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnRencanaKontrolManual);

        MnRencanaKontrolNonBPJS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRencanaKontrolNonBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRencanaKontrolNonBPJS.setText("Rencana Kontrol Non BPJS");
        MnRencanaKontrolNonBPJS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRencanaKontrolNonBPJS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRencanaKontrolNonBPJS.setIconTextGap(5);
        MnRencanaKontrolNonBPJS.setName("MnRencanaKontrolNonBPJS"); // NOI18N
        MnRencanaKontrolNonBPJS.setPreferredSize(new java.awt.Dimension(200, 26));
        MnRencanaKontrolNonBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRencanaKontrolNonBPJSActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnRencanaKontrolNonBPJS);

        MnSuratPengantarRanap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratPengantarRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratPengantarRanap.setText("Surat Pengantar Rawat Inap");
        MnSuratPengantarRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratPengantarRanap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratPengantarRanap.setIconTextGap(5);
        MnSuratPengantarRanap.setName("MnSuratPengantarRanap"); // NOI18N
        MnSuratPengantarRanap.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSuratPengantarRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratPengantarRanapActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnSuratPengantarRanap);

        MnSEPBPJS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSEPBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSEPBPJS.setText("Bridging SEP BPJS");
        MnSEPBPJS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSEPBPJS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSEPBPJS.setIconTextGap(5);
        MnSEPBPJS.setName("MnSEPBPJS"); // NOI18N
        MnSEPBPJS.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSEPBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSEPBPJSActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnSEPBPJS);

        MnSuratIstirahatSakit.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratIstirahatSakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratIstirahatSakit.setText("Surat Istirahat Sakit");
        MnSuratIstirahatSakit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratIstirahatSakit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratIstirahatSakit.setIconTextGap(5);
        MnSuratIstirahatSakit.setName("MnSuratIstirahatSakit"); // NOI18N
        MnSuratIstirahatSakit.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSuratIstirahatSakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratIstirahatSakitActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnSuratIstirahatSakit);

        MnSuratKeteranganNapza.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratKeteranganNapza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratKeteranganNapza.setText("Surat Keterangan NAPZA");
        MnSuratKeteranganNapza.setEnabled(false);
        MnSuratKeteranganNapza.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratKeteranganNapza.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratKeteranganNapza.setIconTextGap(5);
        MnSuratKeteranganNapza.setName("MnSuratKeteranganNapza"); // NOI18N
        MnSuratKeteranganNapza.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSuratKeteranganNapza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratKeteranganNapzaActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnSuratKeteranganNapza);

        MnSuratKeteranganRohani.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratKeteranganRohani.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratKeteranganRohani.setText("Surat Keterangan Rohani");
        MnSuratKeteranganRohani.setEnabled(false);
        MnSuratKeteranganRohani.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratKeteranganRohani.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratKeteranganRohani.setIconTextGap(5);
        MnSuratKeteranganRohani.setName("MnSuratKeteranganRohani"); // NOI18N
        MnSuratKeteranganRohani.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSuratKeteranganRohani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratKeteranganRohaniActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnSuratKeteranganRohani);

        MnSuratKeteranganDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratKeteranganDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratKeteranganDokter.setText("Surat Keterangan Dokter");
        MnSuratKeteranganDokter.setEnabled(false);
        MnSuratKeteranganDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratKeteranganDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratKeteranganDokter.setIconTextGap(5);
        MnSuratKeteranganDokter.setName("MnSuratKeteranganDokter"); // NOI18N
        MnSuratKeteranganDokter.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSuratKeteranganDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratKeteranganDokterActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnSuratKeteranganDokter);

        MnSuratKeteranganDisabilitas.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratKeteranganDisabilitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratKeteranganDisabilitas.setText("Surat Keterangan Disabilitas");
        MnSuratKeteranganDisabilitas.setEnabled(false);
        MnSuratKeteranganDisabilitas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratKeteranganDisabilitas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratKeteranganDisabilitas.setIconTextGap(5);
        MnSuratKeteranganDisabilitas.setName("MnSuratKeteranganDisabilitas"); // NOI18N
        MnSuratKeteranganDisabilitas.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSuratKeteranganDisabilitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratKeteranganDisabilitasActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnSuratKeteranganDisabilitas);

        MnRegistrasiKeTBDOT.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRegistrasiKeTBDOT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRegistrasiKeTBDOT.setText("Registrasi Ke Poli TB DOT");
        MnRegistrasiKeTBDOT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRegistrasiKeTBDOT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRegistrasiKeTBDOT.setIconTextGap(5);
        MnRegistrasiKeTBDOT.setName("MnRegistrasiKeTBDOT"); // NOI18N
        MnRegistrasiKeTBDOT.setPreferredSize(new java.awt.Dimension(200, 26));
        MnRegistrasiKeTBDOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRegistrasiKeTBDOTActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnRegistrasiKeTBDOT);

        MnPermintaanLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaanLab.setText("Pemeriksaan Laboratorium");
        MnPermintaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaanLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaanLab.setIconTextGap(5);
        MnPermintaanLab.setName("MnPermintaanLab"); // NOI18N
        MnPermintaanLab.setPreferredSize(new java.awt.Dimension(200, 26));
        MnPermintaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPermintaanLabActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnPermintaanLab);

        MnPermintaanRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaanRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaanRadiologi.setText("Pemeriksaan Radiologi");
        MnPermintaanRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaanRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaanRadiologi.setIconTextGap(5);
        MnPermintaanRadiologi.setName("MnPermintaanRadiologi"); // NOI18N
        MnPermintaanRadiologi.setPreferredSize(new java.awt.Dimension(200, 26));
        MnPermintaanRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPermintaanRadiologiActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnPermintaanRadiologi);

        MnCariPermintaanLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCariPermintaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCariPermintaanLab.setText("Cari Permintaan Laboratorium");
        MnCariPermintaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCariPermintaanLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCariPermintaanLab.setIconTextGap(5);
        MnCariPermintaanLab.setName("MnCariPermintaanLab"); // NOI18N
        MnCariPermintaanLab.setPreferredSize(new java.awt.Dimension(200, 26));
        MnCariPermintaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCariPermintaanLabActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnCariPermintaanLab);

        MnCariPermintaanRad.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCariPermintaanRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCariPermintaanRad.setText("Cari Permintaan Radiologi");
        MnCariPermintaanRad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCariPermintaanRad.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCariPermintaanRad.setIconTextGap(5);
        MnCariPermintaanRad.setName("MnCariPermintaanRad"); // NOI18N
        MnCariPermintaanRad.setPreferredSize(new java.awt.Dimension(200, 26));
        MnCariPermintaanRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCariPermintaanRadActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnCariPermintaanRad);

        MnAksesRM.setBackground(new java.awt.Color(248, 253, 243));
        MnAksesRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAksesRM.setText("Akses Rekam Medis");
        MnAksesRM.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAksesRM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAksesRM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAksesRM.setIconTextGap(5);
        MnAksesRM.setName("MnAksesRM"); // NOI18N
        MnAksesRM.setOpaque(true);
        MnAksesRM.setPreferredSize(new java.awt.Dimension(220, 26));

        MnDibuka.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDibuka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDibuka.setText("Permohonan Membuka");
        MnDibuka.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDibuka.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDibuka.setIconTextGap(5);
        MnDibuka.setName("MnDibuka"); // NOI18N
        MnDibuka.setPreferredSize(new java.awt.Dimension(160, 26));
        MnDibuka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDibukaActionPerformed(evt);
            }
        });
        MnAksesRM.add(MnDibuka);

        MnDitutup.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDitutup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDitutup.setText("Menutup Akses RM");
        MnDitutup.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDitutup.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDitutup.setIconTextGap(5);
        MnDitutup.setName("MnDitutup"); // NOI18N
        MnDitutup.setPreferredSize(new java.awt.Dimension(160, 26));
        MnDitutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDitutupActionPerformed(evt);
            }
        });
        MnAksesRM.add(MnDitutup);

        MnPermintaan.add(MnAksesRM);

        jPopupMenu1.add(MnPermintaan);

        MnRujukan.setBackground(new java.awt.Color(248, 253, 243));
        MnRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujukan.setText("Rujukan");
        MnRujukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujukan.setIconTextGap(5);
        MnRujukan.setName("MnRujukan"); // NOI18N
        MnRujukan.setOpaque(true);
        MnRujukan.setPreferredSize(new java.awt.Dimension(220, 26));

        MnPoliInternal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPoliInternal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPoliInternal.setText("Poliklinik Internal");
        MnPoliInternal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPoliInternal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPoliInternal.setIconTextGap(5);
        MnPoliInternal.setName("MnPoliInternal"); // NOI18N
        MnPoliInternal.setPreferredSize(new java.awt.Dimension(150, 26));
        MnPoliInternal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPoliInternalActionPerformed(evt);
            }
        });
        MnRujukan.add(MnPoliInternal);

        MnRujuk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujuk.setText("Rujukan Keluar");
        MnRujuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujuk.setIconTextGap(5);
        MnRujuk.setName("MnRujuk"); // NOI18N
        MnRujuk.setPreferredSize(new java.awt.Dimension(150, 26));
        MnRujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukActionPerformed(evt);
            }
        });
        MnRujukan.add(MnRujuk);

        MnCekRujukanJKN.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCekRujukanJKN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCekRujukanJKN.setText("Cek Rujukan BPJS");
        MnCekRujukanJKN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCekRujukanJKN.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCekRujukanJKN.setIconTextGap(5);
        MnCekRujukanJKN.setName("MnCekRujukanJKN"); // NOI18N
        MnCekRujukanJKN.setPreferredSize(new java.awt.Dimension(150, 26));
        MnCekRujukanJKN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekRujukanJKNActionPerformed(evt);
            }
        });
        MnRujukan.add(MnCekRujukanJKN);

        jPopupMenu1.add(MnRujukan);

        MnRekap.setBackground(new java.awt.Color(248, 253, 243));
        MnRekap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekap.setText("Rekap Data");
        MnRekap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekap.setIconTextGap(5);
        MnRekap.setName("MnRekap"); // NOI18N
        MnRekap.setOpaque(true);
        MnRekap.setPreferredSize(new java.awt.Dimension(220, 26));

        MnDietMakanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDietMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDietMakanan.setText("Diet Makanan");
        MnDietMakanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDietMakanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDietMakanan.setIconTextGap(5);
        MnDietMakanan.setName("MnDietMakanan"); // NOI18N
        MnDietMakanan.setPreferredSize(new java.awt.Dimension(240, 26));
        MnDietMakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDietMakananActionPerformed(evt);
            }
        });
        MnRekap.add(MnDietMakanan);

        MnRekapTindakanPerbup.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapTindakanPerbup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekapTindakanPerbup.setText("Rekap Tindakan PERBUP Ralan");
        MnRekapTindakanPerbup.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekapTindakanPerbup.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekapTindakanPerbup.setIconTextGap(5);
        MnRekapTindakanPerbup.setName("MnRekapTindakanPerbup"); // NOI18N
        MnRekapTindakanPerbup.setPreferredSize(new java.awt.Dimension(195, 26));
        MnRekapTindakanPerbup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapTindakanPerbupActionPerformed(evt);
            }
        });
        MnRekap.add(MnRekapTindakanPerbup);

        jPopupMenu1.add(MnRekap);

        MnStatus.setBackground(new java.awt.Color(248, 253, 243));
        MnStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStatus.setText("Set Status");
        MnStatus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStatus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStatus.setIconTextGap(5);
        MnStatus.setName("MnStatus"); // NOI18N
        MnStatus.setOpaque(true);
        MnStatus.setPreferredSize(new java.awt.Dimension(220, 26));

        ppBerkas.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBerkas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBerkas.setText("Berkas R.M. Diterima");
        ppBerkas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBerkas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBerkas.setIconTextGap(5);
        ppBerkas.setName("ppBerkas"); // NOI18N
        ppBerkas.setPreferredSize(new java.awt.Dimension(150, 26));
        ppBerkas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBerkasBtnPrintActionPerformed(evt);
            }
        });
        MnStatus.add(ppBerkas);

        MnSudah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSudah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSudah.setText("Sudah Periksa");
        MnSudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSudah.setIconTextGap(5);
        MnSudah.setName("MnSudah"); // NOI18N
        MnSudah.setPreferredSize(new java.awt.Dimension(150, 26));
        MnSudah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSudahActionPerformed(evt);
            }
        });
        MnStatus.add(MnSudah);

        MnBelum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBelum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBelum.setText("Belum Periksa");
        MnBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBelum.setIconTextGap(5);
        MnBelum.setName("MnBelum"); // NOI18N
        MnBelum.setPreferredSize(new java.awt.Dimension(150, 26));
        MnBelum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBelumActionPerformed(evt);
            }
        });
        MnStatus.add(MnBelum);

        MnBatal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBatal.setText("Batal Periksa");
        MnBatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBatal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBatal.setIconTextGap(5);
        MnBatal.setName("MnBatal"); // NOI18N
        MnBatal.setPreferredSize(new java.awt.Dimension(150, 26));
        MnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBatalActionPerformed(evt);
            }
        });
        MnStatus.add(MnBatal);

        jPopupMenu1.add(MnStatus);

        MnHapusData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusData.setText("Hapus Semua Transaksi");
        MnHapusData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusData.setIconTextGap(5);
        MnHapusData.setName("MnHapusData"); // NOI18N
        MnHapusData.setPreferredSize(new java.awt.Dimension(220, 26));
        MnHapusData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusDataActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusData);

        MnInputData.setBackground(new java.awt.Color(248, 253, 243));
        MnInputData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputData.setText("Input Data Tambahan");
        MnInputData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputData.setIconTextGap(5);
        MnInputData.setName("MnInputData"); // NOI18N
        MnInputData.setOpaque(true);
        MnInputData.setPreferredSize(new java.awt.Dimension(220, 26));

        MnKemenkes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKemenkes.setText("Bridging Kemenkes RI");
        MnKemenkes.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKemenkes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKemenkes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKemenkes.setIconTextGap(5);
        MnKemenkes.setName("MnKemenkes"); // NOI18N
        MnKemenkes.setOpaque(true);
        MnKemenkes.setPreferredSize(new java.awt.Dimension(230, 26));

        MnTeridentifikasiTB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTeridentifikasiTB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTeridentifikasiTB.setText("Teridentifikasi TB (SITB)");
        MnTeridentifikasiTB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTeridentifikasiTB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTeridentifikasiTB.setIconTextGap(5);
        MnTeridentifikasiTB.setName("MnTeridentifikasiTB"); // NOI18N
        MnTeridentifikasiTB.setPreferredSize(new java.awt.Dimension(190, 26));
        MnTeridentifikasiTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTeridentifikasiTBBtnPrintActionPerformed(evt);
            }
        });
        MnKemenkes.add(MnTeridentifikasiTB);

        MnDataKanker.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataKanker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataKanker.setText("Data Penyakit Kanker");
        MnDataKanker.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataKanker.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataKanker.setIconTextGap(5);
        MnDataKanker.setName("MnDataKanker"); // NOI18N
        MnDataKanker.setPreferredSize(new java.awt.Dimension(190, 26));
        MnDataKanker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataKankerBtnPrintActionPerformed(evt);
            }
        });
        MnKemenkes.add(MnDataKanker);

        MnInputData.add(MnKemenkes);

        MnDokumenPenunjangMedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokumenPenunjangMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokumenPenunjangMedis.setText("Dokumen Penunjang Medis");
        MnDokumenPenunjangMedis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokumenPenunjangMedis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokumenPenunjangMedis.setIconTextGap(5);
        MnDokumenPenunjangMedis.setName("MnDokumenPenunjangMedis"); // NOI18N
        MnDokumenPenunjangMedis.setPreferredSize(new java.awt.Dimension(230, 26));
        MnDokumenPenunjangMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokumenPenunjangMedisBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(MnDokumenPenunjangMedis);

        MnNomorTB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnNomorTB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnNomorTB.setText("Nomor Registrasi TB");
        MnNomorTB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnNomorTB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnNomorTB.setIconTextGap(5);
        MnNomorTB.setName("MnNomorTB"); // NOI18N
        MnNomorTB.setPreferredSize(new java.awt.Dimension(230, 26));
        MnNomorTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnNomorTBBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(MnNomorTB);

        MnSpirometri.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSpirometri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSpirometri.setText("Spirometri");
        MnSpirometri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSpirometri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSpirometri.setIconTextGap(5);
        MnSpirometri.setName("MnSpirometri"); // NOI18N
        MnSpirometri.setPreferredSize(new java.awt.Dimension(230, 26));
        MnSpirometri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSpirometriBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(MnSpirometri);

        ppProgramPRB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppProgramPRB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppProgramPRB.setText("Program PRB BPJS");
        ppProgramPRB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppProgramPRB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppProgramPRB.setIconTextGap(5);
        ppProgramPRB.setName("ppProgramPRB"); // NOI18N
        ppProgramPRB.setPreferredSize(new java.awt.Dimension(230, 26));
        ppProgramPRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppProgramPRBBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(ppProgramPRB);

        MnPemeriksaanKlinisLabHIV.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemeriksaanKlinisLabHIV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPemeriksaanKlinisLabHIV.setText("Pemeriksaan Klinis & Lab. HIV");
        MnPemeriksaanKlinisLabHIV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPemeriksaanKlinisLabHIV.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPemeriksaanKlinisLabHIV.setIconTextGap(5);
        MnPemeriksaanKlinisLabHIV.setName("MnPemeriksaanKlinisLabHIV"); // NOI18N
        MnPemeriksaanKlinisLabHIV.setPreferredSize(new java.awt.Dimension(230, 26));
        MnPemeriksaanKlinisLabHIV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemeriksaanKlinisLabHIVBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(MnPemeriksaanKlinisLabHIV);

        MnTerapiAntiretroviralHIV.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTerapiAntiretroviralHIV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTerapiAntiretroviralHIV.setText("Terapi Antiretroviral (ART) HIV");
        MnTerapiAntiretroviralHIV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTerapiAntiretroviralHIV.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTerapiAntiretroviralHIV.setIconTextGap(5);
        MnTerapiAntiretroviralHIV.setName("MnTerapiAntiretroviralHIV"); // NOI18N
        MnTerapiAntiretroviralHIV.setPreferredSize(new java.awt.Dimension(230, 26));
        MnTerapiAntiretroviralHIV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTerapiAntiretroviralHIVBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(MnTerapiAntiretroviralHIV);

        MnFollowUPPerawatanTerapiHIV.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnFollowUPPerawatanTerapiHIV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnFollowUPPerawatanTerapiHIV.setText("Follow-Up Perawatan & Terapi ART HIV");
        MnFollowUPPerawatanTerapiHIV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnFollowUPPerawatanTerapiHIV.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnFollowUPPerawatanTerapiHIV.setIconTextGap(5);
        MnFollowUPPerawatanTerapiHIV.setName("MnFollowUPPerawatanTerapiHIV"); // NOI18N
        MnFollowUPPerawatanTerapiHIV.setPreferredSize(new java.awt.Dimension(230, 26));
        MnFollowUPPerawatanTerapiHIV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFollowUPPerawatanTerapiHIVBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(MnFollowUPPerawatanTerapiHIV);

        ppPasienCorona.setBackground(new java.awt.Color(255, 255, 254));
        ppPasienCorona.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPasienCorona.setForeground(new java.awt.Color(50, 50, 50));
        ppPasienCorona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPasienCorona.setText("Pasien Corona Kemenkes");
        ppPasienCorona.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPasienCorona.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPasienCorona.setIconTextGap(5);
        ppPasienCorona.setName("ppPasienCorona"); // NOI18N
        ppPasienCorona.setPreferredSize(new java.awt.Dimension(230, 26));
        ppPasienCorona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPasienCoronaBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(ppPasienCorona);

        ppSuratKontrol.setBackground(new java.awt.Color(255, 255, 254));
        ppSuratKontrol.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSuratKontrol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSuratKontrol.setText("Rencana Kontrol BPJS");
        ppSuratKontrol.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSuratKontrol.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSuratKontrol.setIconTextGap(5);
        ppSuratKontrol.setName("ppSuratKontrol"); // NOI18N
        ppSuratKontrol.setPreferredSize(new java.awt.Dimension(230, 26));
        ppSuratKontrol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSuratKontrolBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(ppSuratKontrol);

        MnDiet.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDiet.setText("Diet Pasien");
        MnDiet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiet.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiet.setIconTextGap(5);
        MnDiet.setName("MnDiet"); // NOI18N
        MnDiet.setPreferredSize(new java.awt.Dimension(230, 26));
        MnDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDietActionPerformed(evt);
            }
        });
        MnInputData.add(MnDiet);

        MnDataHAIs.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataHAIs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataHAIs.setText("Data HAIs");
        MnDataHAIs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataHAIs.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataHAIs.setIconTextGap(5);
        MnDataHAIs.setName("MnDataHAIs"); // NOI18N
        MnDataHAIs.setPreferredSize(new java.awt.Dimension(230, 26));
        MnDataHAIs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataHAIsBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(MnDataHAIs);

        MnSensusParu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSensusParu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSensusParu.setText("Sensus Pasien Paru");
        MnSensusParu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSensusParu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSensusParu.setIconTextGap(5);
        MnSensusParu.setName("MnSensusParu"); // NOI18N
        MnSensusParu.setPreferredSize(new java.awt.Dimension(230, 26));
        MnSensusParu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSensusParuBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(MnSensusParu);

        jPopupMenu1.add(MnInputData);

        MnRekamMedis.setBackground(new java.awt.Color(248, 253, 243));
        MnRekamMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekamMedis.setText("Data Rekam Medis");
        MnRekamMedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekamMedis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekamMedis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekamMedis.setIconTextGap(5);
        MnRekamMedis.setName("MnRekamMedis"); // NOI18N
        MnRekamMedis.setOpaque(true);
        MnRekamMedis.setPreferredSize(new java.awt.Dimension(220, 26));

        MnRMGawatDarurat.setBackground(new java.awt.Color(255, 255, 254));
        MnRMGawatDarurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRMGawatDarurat.setText("RM Gawat Darurat");
        MnRMGawatDarurat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRMGawatDarurat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRMGawatDarurat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRMGawatDarurat.setIconTextGap(5);
        MnRMGawatDarurat.setName("MnRMGawatDarurat"); // NOI18N
        MnRMGawatDarurat.setOpaque(true);
        MnRMGawatDarurat.setPreferredSize(new java.awt.Dimension(230, 26));

        MnDataTriaseIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnDataTriaseIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataTriaseIGD.setText("Triase Gawat Darurat");
        MnDataTriaseIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataTriaseIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataTriaseIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataTriaseIGD.setIconTextGap(5);
        MnDataTriaseIGD.setName("MnDataTriaseIGD"); // NOI18N
        MnDataTriaseIGD.setOpaque(true);
        MnDataTriaseIGD.setPreferredSize(new java.awt.Dimension(230, 26));

        MnInputDataTriaseIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnInputDataTriaseIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputDataTriaseIGD.setForeground(new java.awt.Color(50, 50, 50));
        MnInputDataTriaseIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputDataTriaseIGD.setText("Input Data");
        MnInputDataTriaseIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputDataTriaseIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputDataTriaseIGD.setIconTextGap(5);
        MnInputDataTriaseIGD.setName("MnInputDataTriaseIGD"); // NOI18N
        MnInputDataTriaseIGD.setPreferredSize(new java.awt.Dimension(98, 26));
        MnInputDataTriaseIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputDataTriaseIGDActionPerformed(evt);
            }
        });
        MnDataTriaseIGD.add(MnInputDataTriaseIGD);

        MnLihatDataTriaseIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnLihatDataTriaseIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLihatDataTriaseIGD.setForeground(new java.awt.Color(50, 50, 50));
        MnLihatDataTriaseIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLihatDataTriaseIGD.setText("Lihat Data");
        MnLihatDataTriaseIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLihatDataTriaseIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLihatDataTriaseIGD.setIconTextGap(5);
        MnLihatDataTriaseIGD.setName("MnLihatDataTriaseIGD"); // NOI18N
        MnLihatDataTriaseIGD.setPreferredSize(new java.awt.Dimension(98, 26));
        MnLihatDataTriaseIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLihatDataTriaseIGDActionPerformed(evt);
            }
        });
        MnDataTriaseIGD.add(MnLihatDataTriaseIGD);

        MnRMGawatDarurat.add(MnDataTriaseIGD);

        MnAssesmenMedikIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnAssesmenMedikIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAssesmenMedikIGD.setText("Assesmen Medik IGD");
        MnAssesmenMedikIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAssesmenMedikIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAssesmenMedikIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAssesmenMedikIGD.setIconTextGap(5);
        MnAssesmenMedikIGD.setName("MnAssesmenMedikIGD"); // NOI18N
        MnAssesmenMedikIGD.setOpaque(true);
        MnAssesmenMedikIGD.setPreferredSize(new java.awt.Dimension(230, 26));

        MnInputDataAssesmenMedikIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnInputDataAssesmenMedikIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputDataAssesmenMedikIGD.setForeground(new java.awt.Color(50, 50, 50));
        MnInputDataAssesmenMedikIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputDataAssesmenMedikIGD.setText("Input Data");
        MnInputDataAssesmenMedikIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputDataAssesmenMedikIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputDataAssesmenMedikIGD.setIconTextGap(5);
        MnInputDataAssesmenMedikIGD.setName("MnInputDataAssesmenMedikIGD"); // NOI18N
        MnInputDataAssesmenMedikIGD.setPreferredSize(new java.awt.Dimension(98, 26));
        MnInputDataAssesmenMedikIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputDataAssesmenMedikIGDActionPerformed(evt);
            }
        });
        MnAssesmenMedikIGD.add(MnInputDataAssesmenMedikIGD);

        MnLihatDataAssesmenMedikIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnLihatDataAssesmenMedikIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLihatDataAssesmenMedikIGD.setForeground(new java.awt.Color(50, 50, 50));
        MnLihatDataAssesmenMedikIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLihatDataAssesmenMedikIGD.setText("Lihat Data");
        MnLihatDataAssesmenMedikIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLihatDataAssesmenMedikIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLihatDataAssesmenMedikIGD.setIconTextGap(5);
        MnLihatDataAssesmenMedikIGD.setName("MnLihatDataAssesmenMedikIGD"); // NOI18N
        MnLihatDataAssesmenMedikIGD.setPreferredSize(new java.awt.Dimension(98, 26));
        MnLihatDataAssesmenMedikIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLihatDataAssesmenMedikIGDActionPerformed(evt);
            }
        });
        MnAssesmenMedikIGD.add(MnLihatDataAssesmenMedikIGD);

        MnRMGawatDarurat.add(MnAssesmenMedikIGD);

        MnAssesmenKeperawatanIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnAssesmenKeperawatanIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAssesmenKeperawatanIGD.setText("Assesmen Keperawatan IGD");
        MnAssesmenKeperawatanIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAssesmenKeperawatanIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAssesmenKeperawatanIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAssesmenKeperawatanIGD.setIconTextGap(5);
        MnAssesmenKeperawatanIGD.setName("MnAssesmenKeperawatanIGD"); // NOI18N
        MnAssesmenKeperawatanIGD.setOpaque(true);
        MnAssesmenKeperawatanIGD.setPreferredSize(new java.awt.Dimension(230, 26));

        MnInputDataAssesmenKeperawatanIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnInputDataAssesmenKeperawatanIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputDataAssesmenKeperawatanIGD.setForeground(new java.awt.Color(50, 50, 50));
        MnInputDataAssesmenKeperawatanIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputDataAssesmenKeperawatanIGD.setText("Input Data");
        MnInputDataAssesmenKeperawatanIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputDataAssesmenKeperawatanIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputDataAssesmenKeperawatanIGD.setIconTextGap(5);
        MnInputDataAssesmenKeperawatanIGD.setName("MnInputDataAssesmenKeperawatanIGD"); // NOI18N
        MnInputDataAssesmenKeperawatanIGD.setPreferredSize(new java.awt.Dimension(98, 26));
        MnInputDataAssesmenKeperawatanIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputDataAssesmenKeperawatanIGDActionPerformed(evt);
            }
        });
        MnAssesmenKeperawatanIGD.add(MnInputDataAssesmenKeperawatanIGD);

        MnLihatDataAssesmenKeperawatanIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnLihatDataAssesmenKeperawatanIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLihatDataAssesmenKeperawatanIGD.setForeground(new java.awt.Color(50, 50, 50));
        MnLihatDataAssesmenKeperawatanIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLihatDataAssesmenKeperawatanIGD.setText("Lihat Data");
        MnLihatDataAssesmenKeperawatanIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLihatDataAssesmenKeperawatanIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLihatDataAssesmenKeperawatanIGD.setIconTextGap(5);
        MnLihatDataAssesmenKeperawatanIGD.setName("MnLihatDataAssesmenKeperawatanIGD"); // NOI18N
        MnLihatDataAssesmenKeperawatanIGD.setPreferredSize(new java.awt.Dimension(98, 26));
        MnLihatDataAssesmenKeperawatanIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLihatDataAssesmenKeperawatanIGDActionPerformed(evt);
            }
        });
        MnAssesmenKeperawatanIGD.add(MnLihatDataAssesmenKeperawatanIGD);

        MnRMGawatDarurat.add(MnAssesmenKeperawatanIGD);

        MnTransferSerahTerimaIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnTransferSerahTerimaIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTransferSerahTerimaIGD.setText("Transfer Serah Terima Pasien IGD");
        MnTransferSerahTerimaIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTransferSerahTerimaIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTransferSerahTerimaIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTransferSerahTerimaIGD.setIconTextGap(5);
        MnTransferSerahTerimaIGD.setName("MnTransferSerahTerimaIGD"); // NOI18N
        MnTransferSerahTerimaIGD.setOpaque(true);
        MnTransferSerahTerimaIGD.setPreferredSize(new java.awt.Dimension(230, 26));

        MnInputDataTransferSerahTerimaIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnInputDataTransferSerahTerimaIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputDataTransferSerahTerimaIGD.setForeground(new java.awt.Color(50, 50, 50));
        MnInputDataTransferSerahTerimaIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputDataTransferSerahTerimaIGD.setText("Input Data");
        MnInputDataTransferSerahTerimaIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputDataTransferSerahTerimaIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputDataTransferSerahTerimaIGD.setIconTextGap(5);
        MnInputDataTransferSerahTerimaIGD.setName("MnInputDataTransferSerahTerimaIGD"); // NOI18N
        MnInputDataTransferSerahTerimaIGD.setPreferredSize(new java.awt.Dimension(98, 26));
        MnInputDataTransferSerahTerimaIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputDataTransferSerahTerimaIGDActionPerformed(evt);
            }
        });
        MnTransferSerahTerimaIGD.add(MnInputDataTransferSerahTerimaIGD);

        MnLihatDataTransferSerahTerimaIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnLihatDataTransferSerahTerimaIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLihatDataTransferSerahTerimaIGD.setForeground(new java.awt.Color(50, 50, 50));
        MnLihatDataTransferSerahTerimaIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLihatDataTransferSerahTerimaIGD.setText("Lihat Data");
        MnLihatDataTransferSerahTerimaIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLihatDataTransferSerahTerimaIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLihatDataTransferSerahTerimaIGD.setIconTextGap(5);
        MnLihatDataTransferSerahTerimaIGD.setName("MnLihatDataTransferSerahTerimaIGD"); // NOI18N
        MnLihatDataTransferSerahTerimaIGD.setPreferredSize(new java.awt.Dimension(98, 26));
        MnLihatDataTransferSerahTerimaIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLihatDataTransferSerahTerimaIGDActionPerformed(evt);
            }
        });
        MnTransferSerahTerimaIGD.add(MnLihatDataTransferSerahTerimaIGD);

        MnRMGawatDarurat.add(MnTransferSerahTerimaIGD);

        MnPemberianObat.setBackground(new java.awt.Color(255, 255, 254));
        MnPemberianObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemberianObat.setForeground(new java.awt.Color(50, 50, 50));
        MnPemberianObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPemberianObat.setText("Pemberian Obat Rekam Medis");
        MnPemberianObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPemberianObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPemberianObat.setIconTextGap(5);
        MnPemberianObat.setName("MnPemberianObat"); // NOI18N
        MnPemberianObat.setPreferredSize(new java.awt.Dimension(230, 26));
        MnPemberianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemberianObatActionPerformed(evt);
            }
        });
        MnRMGawatDarurat.add(MnPemberianObat);

        MnPetugasPemberianObat.setBackground(new java.awt.Color(255, 255, 254));
        MnPetugasPemberianObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPetugasPemberianObat.setForeground(new java.awt.Color(50, 50, 50));
        MnPetugasPemberianObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPetugasPemberianObat.setText("Petugas Pelaksana Pemberian Obat");
        MnPetugasPemberianObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPetugasPemberianObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPetugasPemberianObat.setIconTextGap(5);
        MnPetugasPemberianObat.setName("MnPetugasPemberianObat"); // NOI18N
        MnPetugasPemberianObat.setPreferredSize(new java.awt.Dimension(230, 26));
        MnPetugasPemberianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPetugasPemberianObatActionPerformed(evt);
            }
        });
        MnRMGawatDarurat.add(MnPetugasPemberianObat);

        MnLembarObservasi.setBackground(new java.awt.Color(255, 255, 254));
        MnLembarObservasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarObservasi.setForeground(new java.awt.Color(50, 50, 50));
        MnLembarObservasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarObservasi.setText("Lembar Observasi");
        MnLembarObservasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLembarObservasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLembarObservasi.setIconTextGap(5);
        MnLembarObservasi.setName("MnLembarObservasi"); // NOI18N
        MnLembarObservasi.setPreferredSize(new java.awt.Dimension(230, 26));
        MnLembarObservasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarObservasiActionPerformed(evt);
            }
        });
        MnRMGawatDarurat.add(MnLembarObservasi);

        MnAsesmenMedikObstetriIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnAsesmenMedikObstetriIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsesmenMedikObstetriIGD.setText("Asesmen Medik Obstetri");
        MnAsesmenMedikObstetriIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsesmenMedikObstetriIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsesmenMedikObstetriIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsesmenMedikObstetriIGD.setIconTextGap(5);
        MnAsesmenMedikObstetriIGD.setName("MnAsesmenMedikObstetriIGD"); // NOI18N
        MnAsesmenMedikObstetriIGD.setOpaque(true);
        MnAsesmenMedikObstetriIGD.setPreferredSize(new java.awt.Dimension(230, 26));

        MnInputDataAsesmenMedikObstetri.setBackground(new java.awt.Color(255, 255, 254));
        MnInputDataAsesmenMedikObstetri.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputDataAsesmenMedikObstetri.setForeground(new java.awt.Color(50, 50, 50));
        MnInputDataAsesmenMedikObstetri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputDataAsesmenMedikObstetri.setText("Input Data");
        MnInputDataAsesmenMedikObstetri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputDataAsesmenMedikObstetri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputDataAsesmenMedikObstetri.setIconTextGap(5);
        MnInputDataAsesmenMedikObstetri.setName("MnInputDataAsesmenMedikObstetri"); // NOI18N
        MnInputDataAsesmenMedikObstetri.setPreferredSize(new java.awt.Dimension(98, 26));
        MnInputDataAsesmenMedikObstetri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputDataAsesmenMedikObstetriActionPerformed(evt);
            }
        });
        MnAsesmenMedikObstetriIGD.add(MnInputDataAsesmenMedikObstetri);

        MnLihatDataAsesmenMedikObstetri.setBackground(new java.awt.Color(255, 255, 254));
        MnLihatDataAsesmenMedikObstetri.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLihatDataAsesmenMedikObstetri.setForeground(new java.awt.Color(50, 50, 50));
        MnLihatDataAsesmenMedikObstetri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLihatDataAsesmenMedikObstetri.setText("Lihat Data");
        MnLihatDataAsesmenMedikObstetri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLihatDataAsesmenMedikObstetri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLihatDataAsesmenMedikObstetri.setIconTextGap(5);
        MnLihatDataAsesmenMedikObstetri.setName("MnLihatDataAsesmenMedikObstetri"); // NOI18N
        MnLihatDataAsesmenMedikObstetri.setPreferredSize(new java.awt.Dimension(98, 26));
        MnLihatDataAsesmenMedikObstetri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLihatDataAsesmenMedikObstetriActionPerformed(evt);
            }
        });
        MnAsesmenMedikObstetriIGD.add(MnLihatDataAsesmenMedikObstetri);

        MnRMGawatDarurat.add(MnAsesmenMedikObstetriIGD);

        MnCPPTIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnCPPTIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCPPTIGD.setText("CPPT IGD");
        MnCPPTIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCPPTIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCPPTIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCPPTIGD.setIconTextGap(5);
        MnCPPTIGD.setName("MnCPPTIGD"); // NOI18N
        MnCPPTIGD.setOpaque(true);
        MnCPPTIGD.setPreferredSize(new java.awt.Dimension(230, 26));

        MnInputDataCPPT.setBackground(new java.awt.Color(255, 255, 254));
        MnInputDataCPPT.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputDataCPPT.setForeground(new java.awt.Color(50, 50, 50));
        MnInputDataCPPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputDataCPPT.setText("Input Data");
        MnInputDataCPPT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputDataCPPT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputDataCPPT.setIconTextGap(5);
        MnInputDataCPPT.setName("MnInputDataCPPT"); // NOI18N
        MnInputDataCPPT.setPreferredSize(new java.awt.Dimension(130, 26));
        MnInputDataCPPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputDataCPPTActionPerformed(evt);
            }
        });
        MnCPPTIGD.add(MnInputDataCPPT);

        MnLihatDataCPPT.setBackground(new java.awt.Color(255, 255, 254));
        MnLihatDataCPPT.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLihatDataCPPT.setForeground(new java.awt.Color(50, 50, 50));
        MnLihatDataCPPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLihatDataCPPT.setText("Lihat Data");
        MnLihatDataCPPT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLihatDataCPPT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLihatDataCPPT.setIconTextGap(5);
        MnLihatDataCPPT.setName("MnLihatDataCPPT"); // NOI18N
        MnLihatDataCPPT.setPreferredSize(new java.awt.Dimension(130, 26));
        MnLihatDataCPPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLihatDataCPPTActionPerformed(evt);
            }
        });
        MnCPPTIGD.add(MnLihatDataCPPT);

        MnVerifCPPT.setBackground(new java.awt.Color(255, 255, 254));
        MnVerifCPPT.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnVerifCPPT.setForeground(new java.awt.Color(50, 50, 50));
        MnVerifCPPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnVerifCPPT.setText("Verifikasi CPPT");
        MnVerifCPPT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnVerifCPPT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnVerifCPPT.setIconTextGap(5);
        MnVerifCPPT.setName("MnVerifCPPT"); // NOI18N
        MnVerifCPPT.setPreferredSize(new java.awt.Dimension(130, 26));
        MnVerifCPPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnVerifCPPTActionPerformed(evt);
            }
        });
        MnCPPTIGD.add(MnVerifCPPT);

        MnRMGawatDarurat.add(MnCPPTIGD);

        MnAsesmenKebidanan.setBackground(new java.awt.Color(255, 255, 254));
        MnAsesmenKebidanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsesmenKebidanan.setText("Asesmen Kebidanan");
        MnAsesmenKebidanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsesmenKebidanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsesmenKebidanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsesmenKebidanan.setIconTextGap(5);
        MnAsesmenKebidanan.setName("MnAsesmenKebidanan"); // NOI18N
        MnAsesmenKebidanan.setOpaque(true);
        MnAsesmenKebidanan.setPreferredSize(new java.awt.Dimension(230, 26));

        MnInputDataKebidanan.setBackground(new java.awt.Color(255, 255, 254));
        MnInputDataKebidanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputDataKebidanan.setForeground(new java.awt.Color(50, 50, 50));
        MnInputDataKebidanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputDataKebidanan.setText("Input Data");
        MnInputDataKebidanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputDataKebidanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputDataKebidanan.setIconTextGap(5);
        MnInputDataKebidanan.setName("MnInputDataKebidanan"); // NOI18N
        MnInputDataKebidanan.setPreferredSize(new java.awt.Dimension(98, 26));
        MnInputDataKebidanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputDataKebidananActionPerformed(evt);
            }
        });
        MnAsesmenKebidanan.add(MnInputDataKebidanan);

        MnLihatDataKebidanan.setBackground(new java.awt.Color(255, 255, 254));
        MnLihatDataKebidanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLihatDataKebidanan.setForeground(new java.awt.Color(50, 50, 50));
        MnLihatDataKebidanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLihatDataKebidanan.setText("Lihat Data");
        MnLihatDataKebidanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLihatDataKebidanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLihatDataKebidanan.setIconTextGap(5);
        MnLihatDataKebidanan.setName("MnLihatDataKebidanan"); // NOI18N
        MnLihatDataKebidanan.setPreferredSize(new java.awt.Dimension(98, 26));
        MnLihatDataKebidanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLihatDataKebidananActionPerformed(evt);
            }
        });
        MnAsesmenKebidanan.add(MnLihatDataKebidanan);

        MnRMGawatDarurat.add(MnAsesmenKebidanan);

        MnRekamMedis.add(MnRMGawatDarurat);

        MnPenilaianAwalMedis.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianAwalMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianAwalMedis.setText("Penilaian Awal Medis Pasien");
        MnPenilaianAwalMedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianAwalMedis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPenilaianAwalMedis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPenilaianAwalMedis.setIconTextGap(5);
        MnPenilaianAwalMedis.setName("MnPenilaianAwalMedis"); // NOI18N
        MnPenilaianAwalMedis.setOpaque(true);
        MnPenilaianAwalMedis.setPreferredSize(new java.awt.Dimension(230, 26));

        MnPenilaianAwalMedisRalanGeriatri.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianAwalMedisRalanGeriatri.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianAwalMedisRalanGeriatri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianAwalMedisRalanGeriatri.setText("Penilaian Awal Medis Geriatri");
        MnPenilaianAwalMedisRalanGeriatri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPenilaianAwalMedisRalanGeriatri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPenilaianAwalMedisRalanGeriatri.setIconTextGap(5);
        MnPenilaianAwalMedisRalanGeriatri.setName("MnPenilaianAwalMedisRalanGeriatri"); // NOI18N
        MnPenilaianAwalMedisRalanGeriatri.setPreferredSize(new java.awt.Dimension(300, 26));
        MnPenilaianAwalMedisRalanGeriatri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianAwalMedisRalanGeriatriBtnPrintActionPerformed(evt);
            }
        });
        MnPenilaianAwalMedis.add(MnPenilaianAwalMedisRalanGeriatri);

        MnPenilaianTambahanGeriatri.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianTambahanGeriatri.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianTambahanGeriatri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianTambahanGeriatri.setText("Penilaian Tambahan Pasien Geriatri");
        MnPenilaianTambahanGeriatri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPenilaianTambahanGeriatri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPenilaianTambahanGeriatri.setIconTextGap(5);
        MnPenilaianTambahanGeriatri.setName("MnPenilaianTambahanGeriatri"); // NOI18N
        MnPenilaianTambahanGeriatri.setPreferredSize(new java.awt.Dimension(300, 26));
        MnPenilaianTambahanGeriatri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianTambahanGeriatriBtnPrintActionPerformed(evt);
            }
        });
        MnPenilaianAwalMedis.add(MnPenilaianTambahanGeriatri);

        MnPenilaianAwalKeperawatanRalan.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianAwalKeperawatanRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianAwalKeperawatanRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianAwalKeperawatanRalan.setText("Penilaian Awal Keperawatan (Assesmen)");
        MnPenilaianAwalKeperawatanRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPenilaianAwalKeperawatanRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPenilaianAwalKeperawatanRalan.setIconTextGap(5);
        MnPenilaianAwalKeperawatanRalan.setName("MnPenilaianAwalKeperawatanRalan"); // NOI18N
        MnPenilaianAwalKeperawatanRalan.setPreferredSize(new java.awt.Dimension(300, 26));
        MnPenilaianAwalKeperawatanRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianAwalKeperawatanRalanActionPerformed(evt);
            }
        });
        MnPenilaianAwalMedis.add(MnPenilaianAwalKeperawatanRalan);

        MnPenilaianAwalKeperawatanKebidanan.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianAwalKeperawatanKebidanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianAwalKeperawatanKebidanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianAwalKeperawatanKebidanan.setText("Penilaian Awal Keperawatan Kebidanan & Kandungan");
        MnPenilaianAwalKeperawatanKebidanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPenilaianAwalKeperawatanKebidanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPenilaianAwalKeperawatanKebidanan.setIconTextGap(5);
        MnPenilaianAwalKeperawatanKebidanan.setName("MnPenilaianAwalKeperawatanKebidanan"); // NOI18N
        MnPenilaianAwalKeperawatanKebidanan.setPreferredSize(new java.awt.Dimension(300, 26));
        MnPenilaianAwalKeperawatanKebidanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianAwalKeperawatanKebidananActionPerformed(evt);
            }
        });
        MnPenilaianAwalMedis.add(MnPenilaianAwalKeperawatanKebidanan);

        MnPenilaianAwalMedisRalanTHT.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianAwalMedisRalanTHT.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianAwalMedisRalanTHT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianAwalMedisRalanTHT.setText("Penilaian Awal Medis THT");
        MnPenilaianAwalMedisRalanTHT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPenilaianAwalMedisRalanTHT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPenilaianAwalMedisRalanTHT.setIconTextGap(5);
        MnPenilaianAwalMedisRalanTHT.setName("MnPenilaianAwalMedisRalanTHT"); // NOI18N
        MnPenilaianAwalMedisRalanTHT.setPreferredSize(new java.awt.Dimension(300, 26));
        MnPenilaianAwalMedisRalanTHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianAwalMedisRalanTHTActionPerformed(evt);
            }
        });
        MnPenilaianAwalMedis.add(MnPenilaianAwalMedisRalanTHT);

        MnPenilaianAwalMedisRalanMata.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianAwalMedisRalanMata.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianAwalMedisRalanMata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianAwalMedisRalanMata.setText("Penilaian Awal Medis Mata");
        MnPenilaianAwalMedisRalanMata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPenilaianAwalMedisRalanMata.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPenilaianAwalMedisRalanMata.setIconTextGap(5);
        MnPenilaianAwalMedisRalanMata.setName("MnPenilaianAwalMedisRalanMata"); // NOI18N
        MnPenilaianAwalMedisRalanMata.setPreferredSize(new java.awt.Dimension(300, 26));
        MnPenilaianAwalMedisRalanMata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianAwalMedisRalanMataActionPerformed(evt);
            }
        });
        MnPenilaianAwalMedis.add(MnPenilaianAwalMedisRalanMata);

        MnAsesmenMedikObstetri.setBackground(new java.awt.Color(255, 255, 254));
        MnAsesmenMedikObstetri.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsesmenMedikObstetri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsesmenMedikObstetri.setText("Asesmen Medik Obstetri");
        MnAsesmenMedikObstetri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsesmenMedikObstetri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsesmenMedikObstetri.setIconTextGap(5);
        MnAsesmenMedikObstetri.setName("MnAsesmenMedikObstetri"); // NOI18N
        MnAsesmenMedikObstetri.setPreferredSize(new java.awt.Dimension(300, 26));
        MnAsesmenMedikObstetri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsesmenMedikObstetriActionPerformed(evt);
            }
        });
        MnPenilaianAwalMedis.add(MnAsesmenMedikObstetri);

        MnRekamMedis.add(MnPenilaianAwalMedis);

        ppDokumenJangMed.setBackground(new java.awt.Color(255, 255, 254));
        ppDokumenJangMed.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDokumenJangMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDokumenJangMed.setText("Dokumen Penunjang Medis");
        ppDokumenJangMed.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppDokumenJangMed.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppDokumenJangMed.setIconTextGap(5);
        ppDokumenJangMed.setName("ppDokumenJangMed"); // NOI18N
        ppDokumenJangMed.setPreferredSize(new java.awt.Dimension(230, 26));
        ppDokumenJangMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDokumenJangMedBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(ppDokumenJangMed);

        ppPersetujuanTindakan.setBackground(new java.awt.Color(255, 255, 254));
        ppPersetujuanTindakan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPersetujuanTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPersetujuanTindakan.setText("Persetujuan/Penolakan Tindakan");
        ppPersetujuanTindakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPersetujuanTindakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPersetujuanTindakan.setIconTextGap(5);
        ppPersetujuanTindakan.setName("ppPersetujuanTindakan"); // NOI18N
        ppPersetujuanTindakan.setPreferredSize(new java.awt.Dimension(230, 26));
        ppPersetujuanTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPersetujuanTindakanBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(ppPersetujuanTindakan);

        ppRekamPsikologis.setBackground(new java.awt.Color(255, 255, 254));
        ppRekamPsikologis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRekamPsikologis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRekamPsikologis.setText("Rekam Psikologis");
        ppRekamPsikologis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRekamPsikologis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRekamPsikologis.setIconTextGap(5);
        ppRekamPsikologis.setName("ppRekamPsikologis"); // NOI18N
        ppRekamPsikologis.setPreferredSize(new java.awt.Dimension(230, 26));
        ppRekamPsikologis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRekamPsikologisBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(ppRekamPsikologis);

        ppRekamPsikologiPerkawinan.setBackground(new java.awt.Color(255, 255, 254));
        ppRekamPsikologiPerkawinan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRekamPsikologiPerkawinan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRekamPsikologiPerkawinan.setText("Rekam Psikologi Perkawinan");
        ppRekamPsikologiPerkawinan.setToolTipText("");
        ppRekamPsikologiPerkawinan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRekamPsikologiPerkawinan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRekamPsikologiPerkawinan.setIconTextGap(5);
        ppRekamPsikologiPerkawinan.setName("ppRekamPsikologiPerkawinan"); // NOI18N
        ppRekamPsikologiPerkawinan.setPreferredSize(new java.awt.Dimension(230, 26));
        ppRekamPsikologiPerkawinan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRekamPsikologiPerkawinanBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(ppRekamPsikologiPerkawinan);

        MnIkhtisarPerawatanHIV.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnIkhtisarPerawatanHIV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnIkhtisarPerawatanHIV.setText("Ikhtisar Perawatan HIV & Terapi ART");
        MnIkhtisarPerawatanHIV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnIkhtisarPerawatanHIV.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnIkhtisarPerawatanHIV.setIconTextGap(5);
        MnIkhtisarPerawatanHIV.setName("MnIkhtisarPerawatanHIV"); // NOI18N
        MnIkhtisarPerawatanHIV.setPreferredSize(new java.awt.Dimension(230, 26));
        MnIkhtisarPerawatanHIV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnIkhtisarPerawatanHIVActionPerformed(evt);
            }
        });
        MnRekamMedis.add(MnIkhtisarPerawatanHIV);

        ppRiwayat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRiwayat.setText("Riwayat Perawatan");
        ppRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRiwayat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRiwayat.setIconTextGap(5);
        ppRiwayat.setName("ppRiwayat"); // NOI18N
        ppRiwayat.setPreferredSize(new java.awt.Dimension(230, 26));
        ppRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRiwayatBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(ppRiwayat);

        ppCekPaseinMati.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCekPaseinMati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCekPaseinMati.setText("Cek Pasien Meninggal");
        ppCekPaseinMati.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCekPaseinMati.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCekPaseinMati.setIconTextGap(5);
        ppCekPaseinMati.setName("ppCekPaseinMati"); // NOI18N
        ppCekPaseinMati.setPreferredSize(new java.awt.Dimension(230, 26));
        ppCekPaseinMati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCekPaseinMatiBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(ppCekPaseinMati);

        ppPerawatanCorona.setBackground(new java.awt.Color(255, 255, 254));
        ppPerawatanCorona.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPerawatanCorona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPerawatanCorona.setText("Perawatan Pasien Corona INACBG");
        ppPerawatanCorona.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPerawatanCorona.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPerawatanCorona.setIconTextGap(5);
        ppPerawatanCorona.setName("ppPerawatanCorona"); // NOI18N
        ppPerawatanCorona.setPreferredSize(new java.awt.Dimension(230, 26));
        ppPerawatanCorona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPerawatanCoronaBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(ppPerawatanCorona);

        jPopupMenu1.add(MnRekamMedis);

        MnLabel.setBackground(new java.awt.Color(248, 253, 243));
        MnLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLabel.setText("Label Identitas Rekam Medis");
        MnLabel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabel.setIconTextGap(5);
        MnLabel.setName("MnLabel"); // NOI18N
        MnLabel.setOpaque(true);
        MnLabel.setPreferredSize(new java.awt.Dimension(220, 26));

        MrkChampion.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MrkChampion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MrkChampion.setText("Merek CHAMPION");
        MrkChampion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MrkChampion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MrkChampion.setIconTextGap(5);
        MrkChampion.setName("MrkChampion"); // NOI18N
        MrkChampion.setPreferredSize(new java.awt.Dimension(175, 26));
        MrkChampion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MrkChampionActionPerformed(evt);
            }
        });
        MnLabel.add(MrkChampion);

        MrkAjp.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MrkAjp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MrkAjp.setText("Merek AJP BRAND");
        MrkAjp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MrkAjp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MrkAjp.setIconTextGap(5);
        MrkAjp.setName("MrkAjp"); // NOI18N
        MrkAjp.setPreferredSize(new java.awt.Dimension(175, 26));
        MrkAjp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MrkAjpActionPerformed(evt);
            }
        });
        MnLabel.add(MrkAjp);

        MrkCox.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MrkCox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MrkCox.setText("Merek COX");
        MrkCox.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MrkCox.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MrkCox.setIconTextGap(5);
        MrkCox.setName("MrkCox"); // NOI18N
        MrkCox.setPreferredSize(new java.awt.Dimension(175, 26));
        MrkCox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MrkCoxActionPerformed(evt);
            }
        });
        MnLabel.add(MrkCox);

        MrkCoxObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MrkCoxObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MrkCoxObat.setText("Merek COX (Farmasi)");
        MrkCoxObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MrkCoxObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MrkCoxObat.setIconTextGap(5);
        MrkCoxObat.setName("MrkCoxObat"); // NOI18N
        MrkCoxObat.setPreferredSize(new java.awt.Dimension(175, 26));
        MrkCoxObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MrkCoxObatActionPerformed(evt);
            }
        });
        MnLabel.add(MrkCoxObat);

        MrkAlfa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MrkAlfa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MrkAlfa.setText("Merek ALFA PREMIUM");
        MrkAlfa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MrkAlfa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MrkAlfa.setIconTextGap(5);
        MrkAlfa.setName("MrkAlfa"); // NOI18N
        MrkAlfa.setPreferredSize(new java.awt.Dimension(175, 26));
        MrkAlfa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MrkAlfaActionPerformed(evt);
            }
        });
        MnLabel.add(MrkAlfa);

        MrkOlean.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MrkOlean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MrkOlean.setText("Merek OLEAN CITY BRAND");
        MrkOlean.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MrkOlean.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MrkOlean.setIconTextGap(5);
        MrkOlean.setName("MrkOlean"); // NOI18N
        MrkOlean.setPreferredSize(new java.awt.Dimension(175, 26));
        MrkOlean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MrkOleanActionPerformed(evt);
            }
        });
        MnLabel.add(MrkOlean);

        MrkKojico.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MrkKojico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MrkKojico.setText("Merek KOJICO BRAND");
        MrkKojico.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MrkKojico.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MrkKojico.setIconTextGap(5);
        MrkKojico.setName("MrkKojico"); // NOI18N
        MrkKojico.setPreferredSize(new java.awt.Dimension(175, 26));
        MrkKojico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MrkKojicoActionPerformed(evt);
            }
        });
        MnLabel.add(MrkKojico);

        MnLabelPxRanap1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelPxRanap1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLabelPxRanap1.setText("Label Pasien (3,9 x 1,9 Cm)");
        MnLabelPxRanap1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelPxRanap1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelPxRanap1.setIconTextGap(5);
        MnLabelPxRanap1.setName("MnLabelPxRanap1"); // NOI18N
        MnLabelPxRanap1.setPreferredSize(new java.awt.Dimension(175, 26));
        MnLabelPxRanap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelPxRanap1ActionPerformed(evt);
            }
        });
        MnLabel.add(MnLabelPxRanap1);

        MnLabelPxRanap2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelPxRanap2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLabelPxRanap2.setText("Label Pasien (6,4 x 3,2 Cm)");
        MnLabelPxRanap2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelPxRanap2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelPxRanap2.setIconTextGap(5);
        MnLabelPxRanap2.setName("MnLabelPxRanap2"); // NOI18N
        MnLabelPxRanap2.setPreferredSize(new java.awt.Dimension(175, 26));
        MnLabelPxRanap2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelPxRanap2ActionPerformed(evt);
            }
        });
        MnLabel.add(MnLabelPxRanap2);

        jPopupMenu1.add(MnLabel);

        MnHasilPemeriksaanPenunjang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanPenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanPenunjang.setText("Hasil Pemeriksaan Penunjang Medis");
        MnHasilPemeriksaanPenunjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanPenunjang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanPenunjang.setIconTextGap(5);
        MnHasilPemeriksaanPenunjang.setName("MnHasilPemeriksaanPenunjang"); // NOI18N
        MnHasilPemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(220, 26));
        MnHasilPemeriksaanPenunjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanPenunjangActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHasilPemeriksaanPenunjang);

        MnCetakPemeriksaanTHT.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakPemeriksaanTHT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakPemeriksaanTHT.setText("Cetak Hasil Pemeriksaan THT");
        MnCetakPemeriksaanTHT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakPemeriksaanTHT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakPemeriksaanTHT.setIconTextGap(5);
        MnCetakPemeriksaanTHT.setName("MnCetakPemeriksaanTHT"); // NOI18N
        MnCetakPemeriksaanTHT.setPreferredSize(new java.awt.Dimension(220, 26));
        MnCetakPemeriksaanTHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakPemeriksaanTHTActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakPemeriksaanTHT);

        MnNotepad.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnNotepad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnNotepad.setText("Notepad SIMRS");
        MnNotepad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnNotepad.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnNotepad.setIconTextGap(5);
        MnNotepad.setName("MnNotepad"); // NOI18N
        MnNotepad.setPreferredSize(new java.awt.Dimension(220, 26));
        MnNotepad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnNotepadActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnNotepad);

        MnBilling.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBilling.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBilling.setText("Billing/Pembayaran Pasien");
        MnBilling.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBilling.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBilling.setIconTextGap(5);
        MnBilling.setName("MnBilling"); // NOI18N
        MnBilling.setPreferredSize(new java.awt.Dimension(220, 26));
        MnBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBillingActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBilling);

        MnPiutangPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPiutangPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPiutangPasien.setText("Piutang Pasien");
        MnPiutangPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPiutangPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPiutangPasien.setIconTextGap(5);
        MnPiutangPasien.setName("MnPiutangPasien"); // NOI18N
        MnPiutangPasien.setPreferredSize(new java.awt.Dimension(220, 26));
        MnPiutangPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPiutangPasienActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPiutangPasien);

        MnDiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDiagnosa.setText("Diagnosa Pasien");
        MnDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiagnosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiagnosa.setIconTextGap(5);
        MnDiagnosa.setName("MnDiagnosa"); // NOI18N
        MnDiagnosa.setPreferredSize(new java.awt.Dimension(220, 26));
        MnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiagnosaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDiagnosa);

        MnLihatSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLihatSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLihatSEP.setText("Lihat No. SEP");
        MnLihatSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLihatSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLihatSEP.setIconTextGap(5);
        MnLihatSEP.setName("MnLihatSEP"); // NOI18N
        MnLihatSEP.setPreferredSize(new java.awt.Dimension(220, 26));
        MnLihatSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLihatSEPBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnLihatSEP);

        MnFormulirKlaim.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnFormulirKlaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnFormulirKlaim.setText("Formulir Klaim Pasien");
        MnFormulirKlaim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnFormulirKlaim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnFormulirKlaim.setIconTextGap(5);
        MnFormulirKlaim.setName("MnFormulirKlaim"); // NOI18N
        MnFormulirKlaim.setPreferredSize(new java.awt.Dimension(220, 26));
        MnFormulirKlaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFormulirKlaimBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnFormulirKlaim);

        MnLembarStatusPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarStatusPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarStatusPasien.setText("Lembar Status Pasien Baru");
        MnLembarStatusPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLembarStatusPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLembarStatusPasien.setIconTextGap(5);
        MnLembarStatusPasien.setName("MnLembarStatusPasien"); // NOI18N
        MnLembarStatusPasien.setPreferredSize(new java.awt.Dimension(220, 26));
        MnLembarStatusPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarStatusPasienBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnLembarStatusPasien);

        MnStatusPasienAllKunjungan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStatusPasienAllKunjungan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStatusPasienAllKunjungan.setText("Status Pasien Semua Kunjungan");
        MnStatusPasienAllKunjungan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStatusPasienAllKunjungan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStatusPasienAllKunjungan.setIconTextGap(5);
        MnStatusPasienAllKunjungan.setName("MnStatusPasienAllKunjungan"); // NOI18N
        MnStatusPasienAllKunjungan.setPreferredSize(new java.awt.Dimension(220, 26));
        MnStatusPasienAllKunjungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStatusPasienAllKunjunganBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnStatusPasienAllKunjungan);

        ppCatatanPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCatatanPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCatatanPasien.setText("Catatan Untuk Pasien");
        ppCatatanPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCatatanPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCatatanPasien.setIconTextGap(5);
        ppCatatanPasien.setName("ppCatatanPasien"); // NOI18N
        ppCatatanPasien.setPreferredSize(new java.awt.Dimension(220, 26));
        ppCatatanPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCatatanPasienBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppCatatanPasien);

        WindowGantiDokter.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiDokter.setName("WindowGantiDokter"); // NOI18N
        WindowGantiDokter.setUndecorated(true);
        WindowGantiDokter.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Dokter Poli ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelisi3.setBackground(new java.awt.Color(255, 150, 255));
        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 70));
        panelisi3.setLayout(null);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Dokter Dituju : ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelisi3.add(jLabel13);
        jLabel13.setBounds(0, 10, 100, 23);

        kddokter.setEditable(false);
        kddokter.setForeground(new java.awt.Color(0, 0, 0));
        kddokter.setName("kddokter"); // NOI18N
        panelisi3.add(kddokter);
        kddokter.setBounds(100, 10, 90, 23);

        TDokter.setEditable(false);
        TDokter.setForeground(new java.awt.Color(0, 0, 0));
        TDokter.setName("TDokter"); // NOI18N
        panelisi3.add(TDokter);
        TDokter.setBounds(193, 10, 271, 23);

        btnCariDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnCariDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnCariDokter.setMnemonic('7');
        btnCariDokter.setToolTipText("ALt+7");
        btnCariDokter.setName("btnCariDokter"); // NOI18N
        btnCariDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariDokterActionPerformed(evt);
            }
        });
        panelisi3.add(btnCariDokter);
        btnCariDokter.setBounds(466, 10, 28, 23);

        ChkSemua.setBackground(new java.awt.Color(255, 255, 250));
        ChkSemua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSemua.setForeground(new java.awt.Color(0, 0, 0));
        ChkSemua.setText("Semua Pasien Dipoliklinik Itu");
        ChkSemua.setBorderPainted(true);
        ChkSemua.setBorderPaintedFlat(true);
        ChkSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSemua.setName("ChkSemua"); // NOI18N
        ChkSemua.setOpaque(false);
        ChkSemua.setPreferredSize(new java.awt.Dimension(175, 23));
        panelisi3.add(ChkSemua);
        ChkSemua.setBounds(100, 38, 180, 23);

        internalFrame3.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        panelisi4.setBackground(new java.awt.Color(255, 150, 255));
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 4, 9));

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        panelisi4.add(BtnSimpan1);

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

        internalFrame3.add(panelisi4, java.awt.BorderLayout.CENTER);

        WindowGantiDokter.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        WindowGantiPoli.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiPoli.setName("WindowGantiPoli"); // NOI18N
        WindowGantiPoli.setUndecorated(true);
        WindowGantiPoli.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Poliklinik ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame5.setLayout(null);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(510, 30, 100, 30);

        BtnSimpan4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan4.setMnemonic('S');
        BtnSimpan4.setText("Simpan");
        BtnSimpan4.setToolTipText("Alt+S");
        BtnSimpan4.setName("BtnSimpan4"); // NOI18N
        BtnSimpan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnSimpan4);
        BtnSimpan4.setBounds(405, 30, 100, 30);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Poli Dituju :");
        jLabel18.setName("jLabel18"); // NOI18N
        internalFrame5.add(jLabel18);
        jLabel18.setBounds(0, 32, 77, 23);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });
        internalFrame5.add(kdpoli);
        kdpoli.setBounds(81, 32, 60, 23);

        nmpoli.setEditable(false);
        nmpoli.setForeground(new java.awt.Color(0, 0, 0));
        nmpoli.setName("nmpoli"); // NOI18N
        internalFrame5.add(nmpoli);
        nmpoli.setBounds(144, 32, 220, 23);

        btnCariPoli.setForeground(new java.awt.Color(0, 0, 0));
        btnCariPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnCariPoli.setMnemonic('7');
        btnCariPoli.setToolTipText("ALt+7");
        btnCariPoli.setName("btnCariPoli"); // NOI18N
        btnCariPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPoliActionPerformed(evt);
            }
        });
        internalFrame5.add(btnCariPoli);
        btnCariPoli.setBounds(366, 32, 28, 23);

        WindowGantiPoli.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowCaraBayar.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCaraBayar.setName("WindowCaraBayar"); // NOI18N
        WindowCaraBayar.setUndecorated(true);
        WindowCaraBayar.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Jenis Bayar ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(null);

        BtnCloseIn5.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn5.setMnemonic('U');
        BtnCloseIn5.setText("Tutup");
        BtnCloseIn5.setToolTipText("Alt+U");
        BtnCloseIn5.setName("BtnCloseIn5"); // NOI18N
        BtnCloseIn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn5ActionPerformed(evt);
            }
        });
        internalFrame6.add(BtnCloseIn5);
        BtnCloseIn5.setBounds(510, 30, 100, 30);

        BtnSimpan5.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan5.setMnemonic('S');
        BtnSimpan5.setText("Simpan");
        BtnSimpan5.setToolTipText("Alt+S");
        BtnSimpan5.setName("BtnSimpan5"); // NOI18N
        BtnSimpan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan5ActionPerformed(evt);
            }
        });
        internalFrame6.add(BtnSimpan5);
        BtnSimpan5.setBounds(405, 30, 100, 30);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Jenis Bayar :");
        jLabel19.setName("jLabel19"); // NOI18N
        internalFrame6.add(jLabel19);
        jLabel19.setBounds(0, 32, 77, 23);

        kdpenjab.setEditable(false);
        kdpenjab.setForeground(new java.awt.Color(0, 0, 0));
        kdpenjab.setHighlighter(null);
        kdpenjab.setName("kdpenjab"); // NOI18N
        kdpenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpenjabKeyPressed(evt);
            }
        });
        internalFrame6.add(kdpenjab);
        kdpenjab.setBounds(81, 32, 80, 23);

        nmpenjab.setEditable(false);
        nmpenjab.setForeground(new java.awt.Color(0, 0, 0));
        nmpenjab.setName("nmpenjab"); // NOI18N
        internalFrame6.add(nmpenjab);
        nmpenjab.setBounds(164, 32, 200, 23);

        btnBayar.setForeground(new java.awt.Color(0, 0, 0));
        btnBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnBayar.setMnemonic('7');
        btnBayar.setToolTipText("ALt+7");
        btnBayar.setName("btnBayar"); // NOI18N
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });
        internalFrame6.add(btnBayar);
        btnBayar.setBounds(366, 32, 28, 23);

        WindowCaraBayar.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        WindowPasienBooking.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowPasienBooking.setName("WindowPasienBooking"); // NOI18N
        WindowPasienBooking.setUndecorated(true);
        WindowPasienBooking.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pasien Booking ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame7.setLayout(null);

        BtnCloseIn6.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn6.setMnemonic('U');
        BtnCloseIn6.setText("Tutup");
        BtnCloseIn6.setToolTipText("Alt+U");
        BtnCloseIn6.setName("BtnCloseIn6"); // NOI18N
        BtnCloseIn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn6ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnCloseIn6);
        BtnCloseIn6.setBounds(460, 200, 100, 30);

        BtnSimpan6.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/file-edit-16x16.png"))); // NOI18N
        BtnSimpan6.setMnemonic('R');
        BtnSimpan6.setText("Registrasikan Pasien");
        BtnSimpan6.setToolTipText("Alt+R");
        BtnSimpan6.setName("BtnSimpan6"); // NOI18N
        BtnSimpan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan6ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnSimpan6);
        BtnSimpan6.setBounds(262, 200, 190, 30);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Cara Bayar Pasien : ");
        jLabel20.setName("jLabel20"); // NOI18N
        internalFrame7.add(jLabel20);
        jLabel20.setBounds(0, 80, 130, 23);

        kdpoli1.setEditable(false);
        kdpoli1.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli1.setHighlighter(null);
        kdpoli1.setName("kdpoli1"); // NOI18N
        kdpoli1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoli1KeyPressed(evt);
            }
        });
        internalFrame7.add(kdpoli1);
        kdpoli1.setBounds(132, 140, 70, 23);

        nmpoli1.setEditable(false);
        nmpoli1.setForeground(new java.awt.Color(0, 0, 0));
        nmpoli1.setName("nmpoli1"); // NOI18N
        internalFrame7.add(nmpoli1);
        nmpoli1.setBounds(205, 140, 270, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("No. Rawat : ");
        jLabel21.setName("jLabel21"); // NOI18N
        internalFrame7.add(jLabel21);
        jLabel21.setBounds(225, 110, 70, 23);

        kdboking.setForeground(new java.awt.Color(0, 0, 204));
        kdboking.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        kdboking.setHighlighter(null);
        kdboking.setName("kdboking"); // NOI18N
        kdboking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdbokingKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kdbokingKeyTyped(evt);
            }
        });
        internalFrame7.add(kdboking);
        kdboking.setBounds(132, 20, 145, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Data Pasien : ");
        jLabel22.setName("jLabel22"); // NOI18N
        internalFrame7.add(jLabel22);
        jLabel22.setBounds(0, 50, 130, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Poliklinik Tujuan : ");
        jLabel23.setName("jLabel23"); // NOI18N
        internalFrame7.add(jLabel23);
        jLabel23.setBounds(0, 140, 130, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Rencana Tgl. Periksa : ");
        jLabel24.setName("jLabel24"); // NOI18N
        internalFrame7.add(jLabel24);
        jLabel24.setBounds(0, 110, 130, 23);

        norm.setEditable(false);
        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setHighlighter(null);
        norm.setName("norm"); // NOI18N
        norm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                normKeyPressed(evt);
            }
        });
        internalFrame7.add(norm);
        norm.setBounds(132, 50, 80, 23);

        nmpasien.setEditable(false);
        nmpasien.setForeground(new java.awt.Color(0, 0, 0));
        nmpasien.setHighlighter(null);
        nmpasien.setName("nmpasien"); // NOI18N
        nmpasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmpasienKeyPressed(evt);
            }
        });
        internalFrame7.add(nmpasien);
        nmpasien.setBounds(215, 50, 330, 23);

        kdpnj.setEditable(false);
        kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        kdpnj.setHighlighter(null);
        kdpnj.setName("kdpnj"); // NOI18N
        kdpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpnjKeyPressed(evt);
            }
        });
        internalFrame7.add(kdpnj);
        kdpnj.setBounds(132, 80, 70, 23);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setName("nmpnj"); // NOI18N
        internalFrame7.add(nmpnj);
        nmpnj.setBounds(205, 80, 270, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Dokter Terjadwal : ");
        jLabel25.setName("jLabel25"); // NOI18N
        internalFrame7.add(jLabel25);
        jLabel25.setBounds(0, 170, 130, 23);

        KdDokter.setEditable(false);
        KdDokter.setForeground(new java.awt.Color(0, 0, 0));
        KdDokter.setHighlighter(null);
        KdDokter.setName("KdDokter"); // NOI18N
        internalFrame7.add(KdDokter);
        KdDokter.setBounds(132, 170, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setForeground(new java.awt.Color(0, 0, 0));
        NmDokter.setHighlighter(null);
        NmDokter.setName("NmDokter"); // NOI18N
        internalFrame7.add(NmDokter);
        NmDokter.setBounds(225, 170, 300, 23);

        BtnDokter.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('X');
        BtnDokter.setToolTipText("Alt+X");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        BtnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterKeyPressed(evt);
            }
        });
        internalFrame7.add(BtnDokter);
        BtnDokter.setBounds(530, 170, 28, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Kode Booking : ");
        jLabel26.setName("jLabel26"); // NOI18N
        internalFrame7.add(jLabel26);
        jLabel26.setBounds(0, 20, 130, 23);

        norwBoking.setEditable(false);
        norwBoking.setForeground(new java.awt.Color(0, 0, 0));
        norwBoking.setHighlighter(null);
        norwBoking.setName("norwBoking"); // NOI18N
        norwBoking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norwBokingKeyPressed(evt);
            }
        });
        internalFrame7.add(norwBoking);
        norwBoking.setBounds(298, 110, 177, 23);

        tglPeriksa.setEditable(false);
        tglPeriksa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-10-2024" }));
        tglPeriksa.setDisplayFormat("dd-MM-yyyy");
        tglPeriksa.setName("tglPeriksa"); // NOI18N
        tglPeriksa.setOpaque(false);
        tglPeriksa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tglPeriksaItemStateChanged(evt);
            }
        });
        tglPeriksa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tglPeriksaMouseClicked(evt);
            }
        });
        tglPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglPeriksaActionPerformed(evt);
            }
        });
        tglPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglPeriksaKeyPressed(evt);
            }
        });
        internalFrame7.add(tglPeriksa);
        tglPeriksa.setBounds(132, 110, 90, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Antrian Khusus : ");
        jLabel35.setName("jLabel35"); // NOI18N
        internalFrame7.add(jLabel35);
        jLabel35.setBounds(280, 20, 100, 23);

        Tantrian.setEditable(false);
        Tantrian.setForeground(new java.awt.Color(0, 0, 0));
        Tantrian.setHighlighter(null);
        Tantrian.setName("Tantrian"); // NOI18N
        Tantrian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TantrianKeyPressed(evt);
            }
        });
        internalFrame7.add(Tantrian);
        Tantrian.setBounds(385, 20, 70, 23);

        WindowPasienBooking.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        WindowPasienMati.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowPasienMati.setName("WindowPasienMati"); // NOI18N
        WindowPasienMati.setUndecorated(true);
        WindowPasienMati.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Daftar Pasien Meninggal  ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame4.setLayout(null);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(12, 42));
        panelisi1.setLayout(null);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Key Word :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi1.add(jLabel7);
        jLabel7.setBounds(0, 10, 80, 23);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(350, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi1.add(TCari1);
        TCari1.setBounds(85, 10, 320, 23);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+2");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(150, 30));
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
        panelisi1.add(BtnCari1);
        BtnCari1.setBounds(410, 10, 130, 23);

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
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });
        panelisi1.add(BtnKeluar1);
        BtnKeluar1.setBounds(665, 10, 80, 23);

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('M');
        BtnAll1.setText("Semua Data");
        BtnAll1.setToolTipText("Alt+M");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(120, 30));
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
        panelisi1.add(BtnAll1);
        BtnAll1.setBounds(545, 10, 110, 23);

        jPanel3.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        Scroll2.setToolTipText("");
        Scroll2.setComponentPopupMenu(jPopupMenu1);
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbPasienMati.setAutoCreateRowSorter(true);
        tbPasienMati.setToolTipText("Klik pada nama pasien untuk melihat datanya lebih detail");
        tbPasienMati.setComponentPopupMenu(jPopupMenu1);
        tbPasienMati.setName("tbPasienMati"); // NOI18N
        tbPasienMati.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPasienMatiMouseClicked(evt);
            }
        });
        tbPasienMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPasienMatiKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbPasienMati);

        jPanel3.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(12, 190));
        panelisi2.setLayout(null);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Pasien :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel8);
        jLabel8.setBounds(0, 8, 100, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Tgl. Meninggal :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel9);
        jLabel9.setBounds(0, 37, 100, 23);

        rmMati.setEditable(false);
        rmMati.setForeground(new java.awt.Color(0, 0, 0));
        rmMati.setName("rmMati"); // NOI18N
        rmMati.setPreferredSize(new java.awt.Dimension(350, 23));
        rmMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rmMatiKeyPressed(evt);
            }
        });
        panelisi2.add(rmMati);
        rmMati.setBounds(105, 8, 80, 23);

        nmpxMati.setEditable(false);
        nmpxMati.setForeground(new java.awt.Color(0, 0, 0));
        nmpxMati.setName("nmpxMati"); // NOI18N
        nmpxMati.setPreferredSize(new java.awt.Dimension(350, 23));
        nmpxMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmpxMatiKeyPressed(evt);
            }
        });
        panelisi2.add(nmpxMati);
        nmpxMati.setBounds(190, 8, 380, 23);

        tglMati.setEditable(false);
        tglMati.setForeground(new java.awt.Color(0, 0, 0));
        tglMati.setName("tglMati"); // NOI18N
        tglMati.setPreferredSize(new java.awt.Dimension(350, 23));
        tglMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglMatiKeyPressed(evt);
            }
        });
        panelisi2.add(tglMati);
        tglMati.setBounds(105, 37, 240, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Jam :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel11);
        jLabel11.setBounds(350, 37, 30, 23);

        jamMati.setEditable(false);
        jamMati.setForeground(new java.awt.Color(0, 0, 0));
        jamMati.setName("jamMati"); // NOI18N
        jamMati.setPreferredSize(new java.awt.Dimension(350, 23));
        jamMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jamMatiKeyPressed(evt);
            }
        });
        panelisi2.add(jamMati);
        jamMati.setBounds(385, 37, 110, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Keterangan :");
        jLabel27.setName("jLabel27"); // NOI18N
        jLabel27.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel27);
        jLabel27.setBounds(0, 66, 100, 23);

        ketMati.setEditable(false);
        ketMati.setForeground(new java.awt.Color(0, 0, 0));
        ketMati.setName("ketMati"); // NOI18N
        ketMati.setPreferredSize(new java.awt.Dimension(350, 23));
        ketMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ketMatiKeyPressed(evt);
            }
        });
        panelisi2.add(ketMati);
        ketMati.setBounds(110, 66, 800, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Deskripsi (ICD-10) :");
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel28);
        jLabel28.setBounds(0, 125, 210, 23);

        icdMati.setEditable(false);
        icdMati.setForeground(new java.awt.Color(0, 0, 0));
        icdMati.setName("icdMati"); // NOI18N
        icdMati.setPreferredSize(new java.awt.Dimension(350, 23));
        icdMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                icdMatiKeyPressed(evt);
            }
        });
        panelisi2.add(icdMati);
        icdMati.setBounds(215, 96, 70, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Diagnosa Penyebab Kematian (ICD-10) :");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel29);
        jLabel29.setBounds(0, 96, 210, 23);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        desMati.setEditable(false);
        desMati.setColumns(20);
        desMati.setRows(5);
        desMati.setName("desMati"); // NOI18N
        desMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                desMatiKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(desMati);

        panelisi2.add(Scroll3);
        Scroll3.setBounds(215, 125, 700, 60);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tgl. Lahir :");
        jLabel30.setName("jLabel30"); // NOI18N
        jLabel30.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel30);
        jLabel30.setBounds(575, 8, 60, 23);

        tglLahrMati.setEditable(false);
        tglLahrMati.setForeground(new java.awt.Color(0, 0, 0));
        tglLahrMati.setName("tglLahrMati"); // NOI18N
        tglLahrMati.setPreferredSize(new java.awt.Dimension(350, 23));
        tglLahrMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglLahrMatiKeyPressed(evt);
            }
        });
        panelisi2.add(tglLahrMati);
        tglLahrMati.setBounds(640, 8, 270, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Tempat Meninggal :");
        jLabel31.setName("jLabel31"); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel31);
        jLabel31.setBounds(497, 37, 100, 23);

        tmptMati.setEditable(false);
        tmptMati.setForeground(new java.awt.Color(0, 0, 0));
        tmptMati.setName("tmptMati"); // NOI18N
        tmptMati.setPreferredSize(new java.awt.Dimension(350, 23));
        tmptMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tmptMatiKeyPressed(evt);
            }
        });
        panelisi2.add(tmptMati);
        tmptMati.setBounds(600, 37, 310, 23);

        jPanel3.add(panelisi2, java.awt.BorderLayout.PAGE_START);

        internalFrame4.add(jPanel3);
        jPanel3.setBounds(10, 20, 970, 430);

        WindowPasienMati.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        WindowRiwayatKunjungan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayatKunjungan.setName("WindowRiwayatKunjungan"); // NOI18N
        WindowRiwayatKunjungan.setUndecorated(true);
        WindowRiwayatKunjungan.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Riwayat Kunjungan 7 Hari Yang Lalu ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame8.setLayout(null);

        BtnCloseIn7.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn7.setMnemonic('U');
        BtnCloseIn7.setText("Tutup");
        BtnCloseIn7.setToolTipText("Alt+U");
        BtnCloseIn7.setName("BtnCloseIn7"); // NOI18N
        BtnCloseIn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn7ActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnCloseIn7);
        BtnCloseIn7.setBounds(780, 315, 80, 30);

        BtnLewati.setForeground(new java.awt.Color(0, 0, 0));
        BtnLewati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        BtnLewati.setMnemonic('L');
        BtnLewati.setText("Lewati");
        BtnLewati.setToolTipText("Alt+L");
        BtnLewati.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        BtnLewati.setName("BtnLewati"); // NOI18N
        BtnLewati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLewatiActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnLewati);
        BtnLewati.setBounds(695, 315, 80, 30);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Kunjungan Yang Tercatat ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbRiwayatKunj.setToolTipText("");
        tbRiwayatKunj.setName("tbRiwayatKunj"); // NOI18N
        tbRiwayatKunj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatKunjMouseClicked(evt);
            }
        });
        tbRiwayatKunj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatKunjKeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbRiwayatKunj);

        internalFrame8.add(Scroll5);
        Scroll5.setBounds(15, 45, 850, 260);

        jLabel32.setForeground(new java.awt.Color(0, 51, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Mohon dilengkapi kekurangan data rekam medis rawat jalan pasien yang terdaftar pada tabel dibawah ini..!!");
        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setName("jLabel32"); // NOI18N
        internalFrame8.add(jLabel32);
        jLabel32.setBounds(15, 20, 790, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Pasien Dipilih : ");
        jLabel33.setName("jLabel33"); // NOI18N
        internalFrame8.add(jLabel33);
        jLabel33.setBounds(20, 315, 80, 23);

        pasiendipilih.setEditable(false);
        pasiendipilih.setForeground(new java.awt.Color(0, 0, 0));
        pasiendipilih.setHighlighter(null);
        pasiendipilih.setName("pasiendipilih"); // NOI18N
        pasiendipilih.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pasiendipilihKeyPressed(evt);
            }
        });
        internalFrame8.add(pasiendipilih);
        pasiendipilih.setBounds(100, 315, 440, 23);

        BtnRM.setForeground(new java.awt.Color(0, 0, 0));
        BtnRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/addressbook-edit24.png"))); // NOI18N
        BtnRM.setMnemonic('R');
        BtnRM.setText("Isi Rekam Medis");
        BtnRM.setToolTipText("Alt+R");
        BtnRM.setName("BtnRM"); // NOI18N
        BtnRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRMActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnRM);
        BtnRM.setBounds(547, 315, 142, 30);

        WindowRiwayatKunjungan.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        DlgNoSEP.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgNoSEP.setName("DlgNoSEP"); // NOI18N
        DlgNoSEP.setUndecorated(true);
        DlgNoSEP.setResizable(false);

        internalFrame9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Nomor SEP Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame9.setLayout(null);

        BtnCloseIn3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn3.setMnemonic('U');
        BtnCloseIn3.setText("Tutup");
        BtnCloseIn3.setToolTipText("Alt+U");
        BtnCloseIn3.setName("BtnCloseIn3"); // NOI18N
        BtnCloseIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn3ActionPerformed(evt);
            }
        });
        BtnCloseIn3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseIn3KeyPressed(evt);
            }
        });
        internalFrame9.add(BtnCloseIn3);
        BtnCloseIn3.setBounds(330, 103, 80, 30);

        tglsep.setEditable(false);
        tglsep.setForeground(new java.awt.Color(0, 0, 0));
        tglsep.setName("tglsep"); // NOI18N
        tglsep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglsepKeyPressed(evt);
            }
        });
        internalFrame9.add(tglsep);
        tglsep.setBounds(135, 47, 280, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Tgl. SEP :");
        jLabel44.setName("jLabel44"); // NOI18N
        internalFrame9.add(jLabel44);
        jLabel44.setBounds(10, 47, 120, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("No. SEP Tercetak :");
        jLabel45.setName("jLabel45"); // NOI18N
        internalFrame9.add(jLabel45);
        jLabel45.setBounds(10, 20, 120, 23);

        nosep.setEditable(false);
        nosep.setForeground(new java.awt.Color(0, 0, 0));
        nosep.setName("nosep"); // NOI18N
        nosep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nosepKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nosepKeyPressed(evt);
            }
        });
        internalFrame9.add(nosep);
        nosep.setBounds(135, 20, 280, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Jenis Bayar :");
        jLabel46.setName("jLabel46"); // NOI18N
        internalFrame9.add(jLabel46);
        jLabel46.setBounds(10, 74, 120, 23);

        jnsBayar.setEditable(false);
        jnsBayar.setForeground(new java.awt.Color(0, 0, 0));
        jnsBayar.setName("jnsBayar"); // NOI18N
        jnsBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jnsBayarKeyPressed(evt);
            }
        });
        internalFrame9.add(jnsBayar);
        jnsBayar.setBounds(135, 74, 280, 23);

        BtnPrint2.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint2.setMnemonic('T');
        BtnPrint2.setText("Print SEP");
        BtnPrint2.setToolTipText("Alt+T");
        BtnPrint2.setName("BtnPrint2"); // NOI18N
        BtnPrint2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint2ActionPerformed(evt);
            }
        });
        BtnPrint2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint2KeyPressed(evt);
            }
        });
        internalFrame9.add(BtnPrint2);
        BtnPrint2.setBounds(230, 103, 90, 30);

        DlgNoSEP.getContentPane().add(internalFrame9, java.awt.BorderLayout.CENTER);

        WindowRehabMedik.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRehabMedik.setName("WindowRehabMedik"); // NOI18N
        WindowRehabMedik.setUndecorated(true);
        WindowRehabMedik.setResizable(false);

        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pilihan Rehabilitasi Medik ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame10.setLayout(null);

        BtnCloseIn8.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn8.setMnemonic('U');
        BtnCloseIn8.setText("Tutup");
        BtnCloseIn8.setToolTipText("Alt+U");
        BtnCloseIn8.setName("BtnCloseIn8"); // NOI18N
        BtnCloseIn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn8ActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnCloseIn8);
        BtnCloseIn8.setBounds(410, 30, 100, 30);

        BtnSimpan7.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan7.setMnemonic('S');
        BtnSimpan7.setText("Simpan");
        BtnSimpan7.setToolTipText("Alt+S");
        BtnSimpan7.setName("BtnSimpan7"); // NOI18N
        BtnSimpan7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan7ActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnSimpan7);
        BtnSimpan7.setBounds(300, 30, 100, 30);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Jenis Rehabilitasi Medik : ");
        jLabel34.setName("jLabel34"); // NOI18N
        internalFrame10.add(jLabel34);
        jLabel34.setBounds(0, 32, 150, 23);

        cmbRM.setForeground(new java.awt.Color(0, 0, 0));
        cmbRM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "FISIOTERAPI", "OKUPASI TERAPI", "TERAPI WICARA" }));
        cmbRM.setName("cmbRM"); // NOI18N
        internalFrame10.add(cmbRM);
        cmbRM.setBounds(155, 32, 140, 23);

        WindowRehabMedik.getContentPane().add(internalFrame10, java.awt.BorderLayout.CENTER);

        DlgRegPoliTBDOT.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgRegPoliTBDOT.setName("DlgRegPoliTBDOT"); // NOI18N
        DlgRegPoliTBDOT.setUndecorated(true);
        DlgRegPoliTBDOT.setResizable(false);

        internalFrame11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Registrasi Ke Poliklinik TB DOT Dihari Yang Sama ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa5.setName("panelBiasa5"); // NOI18N
        panelBiasa5.setLayout(null);

        BtnSimpanRujuk.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanRujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanRujuk.setMnemonic('S');
        BtnSimpanRujuk.setText("Simpan");
        BtnSimpanRujuk.setToolTipText("Alt+S");
        BtnSimpanRujuk.setName("BtnSimpanRujuk"); // NOI18N
        BtnSimpanRujuk.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpanRujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanRujukActionPerformed(evt);
            }
        });
        panelBiasa5.add(BtnSimpanRujuk);
        BtnSimpanRujuk.setBounds(340, 38, 90, 30);

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
        panelBiasa5.add(BtnKeluar5);
        BtnKeluar5.setBounds(440, 38, 90, 30);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Nama Dokter : ");
        jLabel42.setName("jLabel42"); // NOI18N
        panelBiasa5.add(jLabel42);
        jLabel42.setBounds(0, 8, 100, 23);

        kdDokterRujuk.setEditable(false);
        kdDokterRujuk.setForeground(new java.awt.Color(0, 0, 0));
        kdDokterRujuk.setName("kdDokterRujuk"); // NOI18N
        panelBiasa5.add(kdDokterRujuk);
        kdDokterRujuk.setBounds(103, 8, 90, 23);

        nmDokterRujuk.setEditable(false);
        nmDokterRujuk.setForeground(new java.awt.Color(0, 0, 0));
        nmDokterRujuk.setName("nmDokterRujuk"); // NOI18N
        panelBiasa5.add(nmDokterRujuk);
        nmDokterRujuk.setBounds(197, 8, 310, 23);

        BtnDokterRujuk.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokterRujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokterRujuk.setMnemonic('3');
        BtnDokterRujuk.setToolTipText("ALt+3");
        BtnDokterRujuk.setName("BtnDokterRujuk"); // NOI18N
        BtnDokterRujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterRujukActionPerformed(evt);
            }
        });
        panelBiasa5.add(BtnDokterRujuk);
        BtnDokterRujuk.setBounds(507, 8, 28, 23);

        internalFrame11.add(panelBiasa5, java.awt.BorderLayout.CENTER);

        DlgRegPoliTBDOT.getContentPane().add(internalFrame11, java.awt.BorderLayout.CENTER);

        Scroll8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " ASSESMEN RESIKO JATUH : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbFaktorResiko.setName("tbFaktorResiko"); // NOI18N
        Scroll8.setViewportView(tbFaktorResiko);

        TabTindakanPencegahan.setBackground(new java.awt.Color(255, 255, 254));
        TabTindakanPencegahan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabTindakanPencegahan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabTindakanPencegahan.setName("TabTindakanPencegahan"); // NOI18N

        panelBiasa6.setName("panelBiasa6"); // NOI18N
        panelBiasa6.setLayout(new java.awt.BorderLayout());

        TabPencegahanDewasa.setBackground(new java.awt.Color(255, 255, 254));
        TabPencegahanDewasa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabPencegahanDewasa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPencegahanDewasa.setName("TabPencegahanDewasa"); // NOI18N

        panelBiasa8.setName("panelBiasa8"); // NOI18N
        panelBiasa8.setLayout(new java.awt.BorderLayout());

        dewasaA.setEditable(false);
        dewasaA.setBackground(new java.awt.Color(255, 255, 255));
        dewasaA.setColumns(20);
        dewasaA.setRows(5);
        dewasaA.setText("1. Oreintasi lingkungan\n2. Posisi tempat tidur rendah dan terkunci\n3. Rel tempat tidur dipasang (dinaikkan)\n4. Pencahayaan adekuat\n5. Edukasi pencegahan jatuh");
        dewasaA.setName("dewasaA"); // NOI18N
        dewasaA.setOpaque(true);
        panelBiasa8.add(dewasaA, java.awt.BorderLayout.CENTER);

        TabPencegahanDewasa.addTab("Pencegahan Umum (A)", panelBiasa8);

        panelBiasa9.setName("panelBiasa9"); // NOI18N
        panelBiasa9.setLayout(new java.awt.BorderLayout());

        dewasaB.setEditable(false);
        dewasaB.setBackground(new java.awt.Color(255, 255, 255));
        dewasaB.setColumns(20);
        dewasaB.setRows(5);
        dewasaB.setText("1. Lakukan semua pencegahan umum (A)\n2. Menawarkan bantuan untuk ambulansi\n3. Beri tanda identifikasi dengan pin/kancing kuning pada gelang identitas");
        dewasaB.setName("dewasaB"); // NOI18N
        dewasaB.setOpaque(true);
        panelBiasa9.add(dewasaB, java.awt.BorderLayout.CENTER);

        TabPencegahanDewasa.addTab("Pencegahan Resiko Sedang (B)", panelBiasa9);

        panelBiasa10.setName("panelBiasa10"); // NOI18N
        panelBiasa10.setLayout(new java.awt.BorderLayout());

        dewasaC.setEditable(false);
        dewasaC.setBackground(new java.awt.Color(255, 255, 255));
        dewasaC.setColumns(20);
        dewasaC.setRows(5);
        dewasaC.setText("1. Lakukan semua pencegahan umum A dan B\n2. Beri tanda segitiga warna kuning pada bed pasien\n3. Kunjungi dan monitor setiap 1 jam\n4. Pastikan pasien menggunakan alat bantu\n5. Libatkan keluarga untuk mengawasi pasien");
        dewasaC.setName("dewasaC"); // NOI18N
        dewasaC.setOpaque(true);
        panelBiasa10.add(dewasaC, java.awt.BorderLayout.CENTER);

        TabPencegahanDewasa.addTab("Pencegahan Resiko Tinggi (C)", panelBiasa10);

        panelBiasa6.add(TabPencegahanDewasa, java.awt.BorderLayout.CENTER);

        TabTindakanPencegahan.addTab("DEWASA", panelBiasa6);

        panelBiasa7.setName("panelBiasa7"); // NOI18N
        panelBiasa7.setLayout(new java.awt.BorderLayout());

        TabPencegahanAnak.setBackground(new java.awt.Color(255, 255, 254));
        TabPencegahanAnak.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabPencegahanAnak.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPencegahanAnak.setName("TabPencegahanAnak"); // NOI18N

        panelBiasa14.setName("panelBiasa14"); // NOI18N
        panelBiasa14.setLayout(new java.awt.BorderLayout());

        anakA.setEditable(false);
        anakA.setBackground(new java.awt.Color(255, 255, 255));
        anakA.setColumns(20);
        anakA.setRows(5);
        anakA.setText("1. Oreintasi lingkungan\n2. Posisi tempat tidur rendah dan terkunci\n3. Rel tempat tidur dipasang (dinaikkan)\n4. Bel & barang pribadi dalam jangkauan\n5. Pencahayaan adekuat\n6. Edukasi pencegahan jatuh");
        anakA.setName("anakA"); // NOI18N
        anakA.setOpaque(true);
        panelBiasa14.add(anakA, java.awt.BorderLayout.CENTER);

        TabPencegahanAnak.addTab("Pencegahan Umum (A)", panelBiasa14);

        panelBiasa15.setName("panelBiasa15"); // NOI18N
        panelBiasa15.setLayout(new java.awt.BorderLayout());

        anakB.setEditable(false);
        anakB.setBackground(new java.awt.Color(255, 255, 255));
        anakB.setColumns(20);
        anakB.setRows(5);
        anakB.setText("1. Lakukan semua pencegahan umum (A)\n2. Beri tanda segitiga warna kuning pada bed/RM\n3. Beri tanda identifikasi dengan pin/kancing kuning pada gelang identitas\n4. Kunjungi dan monitor setiap 1 jam\n5. Libatkan keluarga untuk mengawasi pasien");
        anakB.setName("anakB"); // NOI18N
        anakB.setOpaque(true);
        panelBiasa15.add(anakB, java.awt.BorderLayout.CENTER);

        TabPencegahanAnak.addTab("Pencegahan Resiko Tinggi (B)", panelBiasa15);

        panelBiasa7.add(TabPencegahanAnak, java.awt.BorderLayout.CENTER);

        TabTindakanPencegahan.addTab("ANAK", panelBiasa7);

        Kd2.setHighlighter(null);
        Kd2.setName("Kd2"); // NOI18N
        Kd2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kd2KeyPressed(evt);
            }
        });

        TKdPny.setName("TKdPny"); // NOI18N

        Tanggal.setHighlighter(null);
        Tanggal.setName("Tanggal"); // NOI18N

        Jam.setHighlighter(null);
        Jam.setName("Jam"); // NOI18N

        sepJkd.setHighlighter(null);
        sepJkd.setName("sepJkd"); // NOI18N
        sepJkd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sepJkdKeyPressed(evt);
            }
        });

        sepJkdigd.setHighlighter(null);
        sepJkdigd.setName("sepJkdigd"); // NOI18N
        sepJkdigd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sepJkdigdKeyPressed(evt);
            }
        });

        nmPasien.setHighlighter(null);
        nmPasien.setName("nmPasien"); // NOI18N
        nmPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmPasienKeyPressed(evt);
            }
        });

        dataGZ.setHighlighter(null);
        dataGZ.setName("dataGZ"); // NOI18N
        dataGZ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dataGZKeyPressed(evt);
            }
        });

        TNoReg.setHighlighter(null);
        TNoReg.setName("TNoReg"); // NOI18N
        TNoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRegKeyPressed(evt);
            }
        });

        cekKodeBoking.setHighlighter(null);
        cekKodeBoking.setName("cekKodeBoking"); // NOI18N
        cekKodeBoking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekKodeBokingKeyPressed(evt);
            }
        });

        cekTerdaftar.setHighlighter(null);
        cekTerdaftar.setName("cekTerdaftar"); // NOI18N
        cekTerdaftar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekTerdaftarKeyPressed(evt);
            }
        });

        cekPasien.setHighlighter(null);
        cekPasien.setName("cekPasien"); // NOI18N
        cekPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekPasienKeyPressed(evt);
            }
        });

        TglKunRwt.setEditable(false);
        TglKunRwt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-10-2024" }));
        TglKunRwt.setDisplayFormat("dd-MM-yyyy");
        TglKunRwt.setName("TglKunRwt"); // NOI18N
        TglKunRwt.setOpaque(false);
        TglKunRwt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglKunRwtItemStateChanged(evt);
            }
        });
        TglKunRwt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglKunRwtKeyPressed(evt);
            }
        });

        norwBARU.setHighlighter(null);
        norwBARU.setName("norwBARU"); // NOI18N
        norwBARU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norwBARUKeyPressed(evt);
            }
        });

        tglPiutang.setEditable(false);
        tglPiutang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-10-2024" }));
        tglPiutang.setDisplayFormat("dd-MM-yyyy");
        tglPiutang.setName("tglPiutang"); // NOI18N
        tglPiutang.setOpaque(false);
        tglPiutang.setPreferredSize(new java.awt.Dimension(100, 23));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass6.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(405, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass6.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('D');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+D");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(150, 23));
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
        panelGlass6.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 23));
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
        panelGlass6.add(BtnAll);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(60, 30));
        panelGlass6.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(100, 30));
        panelGlass6.add(LCount);

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
        panelGlass6.add(BtnKeluar);

        jPanel2.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Dokter :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass7.add(jLabel14);

        CrPtg.setEditable(false);
        CrPtg.setForeground(new java.awt.Color(0, 0, 0));
        CrPtg.setName("CrPtg"); // NOI18N
        CrPtg.setPreferredSize(new java.awt.Dimension(280, 23));
        panelGlass7.add(CrPtg);

        BtnSeek3.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnSeek3.setMnemonic('4');
        BtnSeek3.setToolTipText("ALt+4");
        BtnSeek3.setName("BtnSeek3"); // NOI18N
        BtnSeek3.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek3ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnSeek3);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Poliklinik :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass7.add(jLabel16);

        CrPoli.setEditable(false);
        CrPoli.setForeground(new java.awt.Color(0, 0, 0));
        CrPoli.setName("CrPoli"); // NOI18N
        CrPoli.setPreferredSize(new java.awt.Dimension(280, 23));
        CrPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CrPoliKeyPressed(evt);
            }
        });
        panelGlass7.add(CrPoli);

        BtnSeek4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnSeek4.setMnemonic('5');
        BtnSeek4.setToolTipText("ALt+5");
        BtnSeek4.setName("BtnSeek4"); // NOI18N
        BtnSeek4.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek4ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnSeek4);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("No. Rawat : ");
        jLabel94.setName("jLabel94"); // NOI18N
        jLabel94.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass7.add(jLabel94);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.setPreferredSize(new java.awt.Dimension(135, 23));
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        panelGlass7.add(TNoRw);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("No. RM : ");
        jLabel95.setName("jLabel95"); // NOI18N
        jLabel95.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass7.add(jLabel95);

        NoRM.setEditable(false);
        NoRM.setForeground(new java.awt.Color(0, 0, 0));
        NoRM.setName("NoRM"); // NOI18N
        NoRM.setPreferredSize(new java.awt.Dimension(65, 23));
        NoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRMKeyPressed(evt);
            }
        });
        panelGlass7.add(NoRM);

        jPanel2.add(panelGlass7, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Periode :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(jLabel15);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-10-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari1KeyPressed(evt);
            }
        });
        panelGlass8.add(DTPCari1);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass8.add(jLabel17);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-10-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari2KeyPressed(evt);
            }
        });
        panelGlass8.add(DTPCari2);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Status Periksa :");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass8.add(jLabel12);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Sudah Diperiksa Dokter", "Berkas Diterima", "Sudah", "Belum", "Bayar", "Batal" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(145, 23));
        cmbStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbStatusItemStateChanged(evt);
            }
        });
        panelGlass8.add(cmbStatus);

        BtnPxBooking.setForeground(new java.awt.Color(0, 0, 0));
        BtnPxBooking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/barralan.png"))); // NOI18N
        BtnPxBooking.setMnemonic('B');
        BtnPxBooking.setText("Pasien Booking");
        BtnPxBooking.setToolTipText("Alt+B");
        BtnPxBooking.setName("BtnPxBooking"); // NOI18N
        BtnPxBooking.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnPxBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPxBookingActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnPxBooking);

        ChkAutoRefres.setBackground(new java.awt.Color(255, 255, 250));
        ChkAutoRefres.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAutoRefres.setForeground(new java.awt.Color(0, 0, 102));
        ChkAutoRefres.setText("Aktifkan Auto Refresh Data");
        ChkAutoRefres.setBorderPainted(true);
        ChkAutoRefres.setBorderPaintedFlat(true);
        ChkAutoRefres.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ChkAutoRefres.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAutoRefres.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAutoRefres.setName("ChkAutoRefres"); // NOI18N
        ChkAutoRefres.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkAutoRefres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAutoRefresActionPerformed(evt);
            }
        });
        panelGlass8.add(ChkAutoRefres);

        jPanel2.add(panelGlass8, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(250, 255, 245));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRawat.setForeground(new java.awt.Color(0, 0, 0));
        TabRawat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        Scroll1.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
        Scroll1.setComponentPopupMenu(jPopupMenu1);
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbKasirRalan.setAutoCreateRowSorter(true);
        tbKasirRalan.setToolTipText("");
        tbKasirRalan.setComponentPopupMenu(jPopupMenu1);
        tbKasirRalan.setName("tbKasirRalan"); // NOI18N
        tbKasirRalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKasirRalanMouseClicked(evt);
            }
        });
        tbKasirRalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKasirRalanKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbKasirRalan);

        TabRawat.addTab(".: Registrasi Pasien", Scroll1);

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(2, 402));
        internalFrame2.setLayout(new java.awt.BorderLayout());

        internalFrame18.setBorder(null);
        internalFrame18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame18.setName("internalFrame18"); // NOI18N
        internalFrame18.setLayout(new java.awt.BorderLayout());

        internalFrame20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Rawat Jalan BPJS ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame20.setName("internalFrame20"); // NOI18N
        internalFrame20.setPreferredSize(new java.awt.Dimension(0, 80));
        internalFrame20.setWarnaBawah(new java.awt.Color(204, 255, 153));
        internalFrame20.setLayout(null);

        infobpjs.setForeground(new java.awt.Color(0, 0, 0));
        infobpjs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infobpjs.setText("infobpjs");
        infobpjs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        infobpjs.setName("infobpjs"); // NOI18N
        internalFrame20.add(infobpjs);
        infobpjs.setBounds(20, 20, 1080, 50);

        internalFrame18.add(internalFrame20, java.awt.BorderLayout.PAGE_START);

        internalFrame21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Rawat Jalan Umum (NON BPJS) ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame21.setName("internalFrame21"); // NOI18N
        internalFrame21.setPreferredSize(new java.awt.Dimension(0, 80));
        internalFrame21.setWarnaBawah(new java.awt.Color(204, 204, 204));
        internalFrame21.setLayout(null);

        infoumum.setForeground(new java.awt.Color(0, 0, 0));
        infoumum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoumum.setText("infoumum");
        infoumum.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        infoumum.setName("infoumum"); // NOI18N
        internalFrame21.add(infoumum);
        infoumum.setBounds(20, 20, 1080, 50);

        internalFrame18.add(internalFrame21, java.awt.BorderLayout.CENTER);

        internalFrame22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Rawat Jalan KHUSUS / PRIORITAS ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame22.setName("internalFrame22"); // NOI18N
        internalFrame22.setPreferredSize(new java.awt.Dimension(0, 80));
        internalFrame22.setWarnaBawah(new java.awt.Color(220, 124, 220));
        internalFrame22.setLayout(null);

        infokhusus.setForeground(new java.awt.Color(0, 0, 0));
        infokhusus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infokhusus.setText("infokhusus");
        infokhusus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        infokhusus.setName("infokhusus"); // NOI18N
        internalFrame22.add(infokhusus);
        infokhusus.setBounds(20, 20, 1080, 50);

        internalFrame18.add(internalFrame22, java.awt.BorderLayout.PAGE_END);

        internalFrame2.add(internalFrame18, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab(".: Informasi Antrian Loket", internalFrame2);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowPasienBooking.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, cmbStatus, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        if (namadokter.equals("")) {
            CrPtg.setText("");
        }

        if (ChkAutoRefres.isSelected() == false && namapoli.equals("")) {
            CrPoli.setText("");
        }

//        if (namapoli.equals("")) {
//            CrPoli.setText("");
//        }
        TCari.setText("");
        if (TabRawat.getSelectedIndex() == 0) {
            tampilkasir();
        } else if (TabRawat.getSelectedIndex() == 1) {
            hitungAntrianBPJS();
            hitungAntrianUMUM();
            hitungAntrianKhusus();
        }
        empttext();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampilkasir();
            TCari.setText("");
        } else {
            Valid.pindah(evt, TCari, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

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
        if (TabRawat.getSelectedIndex() == 0) {
            tampilkasir();
        } else if (TabRawat.getSelectedIndex() == 1) {
            hitungAntrianBPJS();
            hitungAntrianUMUM();
            hitungAntrianKhusus();
        }
        empttext();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void DTPCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        }
}//GEN-LAST:event_DTPCari1KeyPressed

    private void DTPCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        }
}//GEN-LAST:event_DTPCari2KeyPressed

    private void tbKasirRalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKasirRalanMouseClicked
        if (tabModekasir.getRowCount() != 0) {
            try {
                getDatakasir();
            } catch (java.lang.NullPointerException e) {
            }

            if (evt.getClickCount() == 2) {
                MnDataRalanActionPerformed(null);
//                i = tbKasirRalan.getSelectedColumn();
//                if (i == 0) {
//                    if (var.gettindakan_ralan() == true) {
//                        MnDataRalanActionPerformed(null);
//                    }
//                } else if (i == 1) {
//                    //if(var.getbilling_ralan()==true){
//                    MnBillingActionPerformed(null);
//                    //}                    
//                } else if (i == 2) {
//                    if (var.getkamar_inap() == true) {
//                        MnKamarInapActionPerformed(null);
//                    }
//                } else if (i == 3) {
//                    if (var.getkasir_ralan() == true) {
//                        MnSudahActionPerformed(null);
//                        tampilkasir();
//                    }
//                }
            }
        }
}//GEN-LAST:event_tbKasirRalanMouseClicked

    private void tbKasirRalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKasirRalanKeyPressed
        if (tabModekasir.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDatakasir();
                } catch (java.lang.NullPointerException e) {
                }
            }

//            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
//                i = tbKasirRalan.getSelectedColumn();
//                if (i == 0) {
//                    if (var.gettindakan_ralan() == true) {
//                        MnDataRalanActionPerformed(null);
//                    }
//                } else if (i == 1) {
//                    //if(var.getbilling_ralan()==true){
//                    MnBillingActionPerformed(null);
//                    //}                    
//                } else if (i == 2) {
//                    if (var.getkamar_inap() == true) {
//                        MnKamarInapActionPerformed(null);
//                    }
//                } else if (i == 3) {
//                    if (var.getkasir_ralan() == true) {
//                        MnSudahActionPerformed(null);
//                        tampilkasir();
//                    }
//                }
//            }
        }
}//GEN-LAST:event_tbKasirRalanKeyPressed

private void BtnSeek3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek3ActionPerformed
    akses.setform("DlgKasirRalan");
    pilihan = 2;
    dokter2.isCek();
    dokter2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    dokter2.setLocationRelativeTo(internalFrame1);
    dokter2.setVisible(true);
}//GEN-LAST:event_BtnSeek3ActionPerformed

private void BtnSeek4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek4ActionPerformed
    akses.setform("DlgKasirRalan");
    pilihan = 2;
    poliklinik.isCek();
    poliklinik.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    poliklinik.setLocationRelativeTo(internalFrame1);
    poliklinik.setVisible(true);
}//GEN-LAST:event_BtnSeek4ActionPerformed

private void MnBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBillingActionPerformed
    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
        //TNoReg.requestFocus();
    } else if (TNoRw.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        tbKasirRalan.requestFocus();
    } else {
        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
        } else {
            try {
                sudah = Sequel.cariInteger("select count(billing.no_rawat) from billing where billing.no_rawat=?", TNoRw.getText());
                pscaripiutang = koneksi.prepareStatement("select tgl_piutang from piutang_pasien where no_rkm_medis=? and status='Belum Lunas' order by tgl_piutang asc limit 1");
                try {
                    pscaripiutang.setString(1, tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString());
                    rskasir = pscaripiutang.executeQuery();
                    if (rskasir.next()) {
                        i = JOptionPane.showConfirmDialog(null, "Masih ada tunggakan pembayaran, apa mau bayar sekarang ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                        if (i == JOptionPane.YES_OPTION) {
                            DlgLhtPiutang piutang = new DlgLhtPiutang(null, false);
                            piutang.setNoRm(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString(), rskasir.getDate("tgl_registrasi"));
                            piutang.tampil();
                            piutang.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            piutang.setLocationRelativeTo(internalFrame1);
                            piutang.setVisible(true);
                        } else {
                            if (akses.getbilling_ralan() == true) {
                                otomatisRalan();
                            }

                            DlgBilingRalan billing = new DlgBilingRalan(null, false);
                            billing.TNoRw.setText(TNoRw.getText());
                            billing.isCek();
                            billing.isRawat();
                            if (sudah > 0) {
                                billing.setPiutang();
                            }
                            billing.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            billing.setLocationRelativeTo(internalFrame1);
                            tampilkasir();
                            billing.setVisible(true);
                        }
                    } else {
                        if (akses.getbilling_ralan() == true) {
                            otomatisRalan();
                        }

                        DlgBilingRalan billing = new DlgBilingRalan(null, false);
                        billing.TNoRw.setText(TNoRw.getText());
                        billing.isCek();
                        billing.isRawat();
                        billing.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        billing.setLocationRelativeTo(internalFrame1);
                        tampilkasir();
                        billing.setVisible(true);
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rskasir != null) {
                        rskasir.close();
                    }
                    if (pscaripiutang != null) {
                        pscaripiutang.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}//GEN-LAST:event_MnBillingActionPerformed

private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
    //Valid.pindah(evt,TNoReg,DTPReg);
}//GEN-LAST:event_TNoRwKeyPressed

private void MnSudahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSudahActionPerformed
    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TNoRw, "No.Rawat");
    } else {
        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
        } else {
            Valid.editTable(tabModekasir, "reg_periksa", "no_rawat", TNoRw, "stts='Sudah Diperiksa Petugas'");
            if (tabModekasir.getRowCount() != 0) {
                tampilkasir();
            }
        }
    }
}//GEN-LAST:event_MnSudahActionPerformed

private void MnBelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBelumActionPerformed
    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TNoRw, "No.Rawat");
    } else {
        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
        } else {
            Valid.editTable(tabModekasir, "reg_periksa", "no_rawat", TNoRw, "stts='Belum'");
            if (tabModekasir.getRowCount() != 0) {
                tampilkasir();
            }
        }
    }
}//GEN-LAST:event_MnBelumActionPerformed

private void MnDataRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataRalanActionPerformed
    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(rootPane, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TNoRw.getText().equals("")) {
        JOptionPane.showMessageDialog(rootPane, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
        tbKasirRalan.requestFocus();
    } else {
        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
        } else {
            cekReg();
        }
    }
}//GEN-LAST:event_MnDataRalanActionPerformed

private void MnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokterActionPerformed
    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TNoRw.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
        tbKasirRalan.requestFocus();
    } else if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
        JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
    } else {
        ChkSemua.setSelected(false);
        if (akses.getadmin() == true) {
            WindowGantiDokter.setSize(535, 142);
            WindowGantiDokter.setLocationRelativeTo(internalFrame1);
            WindowGantiDokter.setVisible(true);
            btnCariDokter.requestFocus();
        } else {
            if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi..!!");
            } else {
                WindowGantiDokter.setSize(535, 142);
                WindowGantiDokter.setLocationRelativeTo(internalFrame1);
                WindowGantiDokter.setVisible(true);
                btnCariDokter.requestFocus();
            }
        }
    }
}//GEN-LAST:event_MnDokterActionPerformed

private void MnPeriksaLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaLabActionPerformed
    diagnosa_ok = "";
    diagnosa_cek = 0;
    diagnosa_cek = Sequel.cariInteger("select count(1) cek from pemeriksaan_ralan where no_rawat='" + TNoRw.getText() + "'");

    if (diagnosa_cek == 0) {
        diagnosa_ok = "-";
    } else {
        diagnosa_ok = Sequel.cariIsi("select diagnosa from pemeriksaan_ralan where no_rawat='" + TNoRw.getText() + "'");
    }

    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TNoRw.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        tbKasirRalan.requestFocus();
    } else {
        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
        } else {
            DlgPeriksaLaboratorium periksalab = new DlgPeriksaLaboratorium(null, false);
            periksalab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            periksalab.setLocationRelativeTo(internalFrame1);
            periksalab.emptTeks();
            periksalab.KodePerujuk.setText(kddokter.getText());
            periksalab.setNoRm(TNoRw.getText(), "Ralan", diagnosa_ok, "-", Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
            periksalab.tampiltarif();
            periksalab.tampil();
            periksalab.isCek();
            periksalab.setVisible(true);
            periksalab.fokus();
        }
    }
}//GEN-LAST:event_MnPeriksaLabActionPerformed

private void MnKamarInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKamarInapActionPerformed
    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TNoRw.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        tbKasirRalan.requestFocus();
    } else {
        DlgKamarInap kamarinap = new DlgKamarInap(null, false);
        if (akses.getadmin() == true) {
            akses.setstatus(true);
            kamarinap.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            kamarinap.setLocationRelativeTo(internalFrame1);
            kamarinap.emptTeks();
            kamarinap.isCek();
            kamarinap.setNoRm(TNoRw.getText());
            kamarinap.tampil();
            kamarinap.setVisible(true);
            kamarinap.cekKetMati();
            kamarinap.UserValid();
        } else {
            if (kdpoli.getText().equals("-") || kdpoli.getText().equals("IGDK")) {
                if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi..!!");
                } else {
                    akses.setstatus(true);
                    kamarinap.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    kamarinap.setLocationRelativeTo(internalFrame1);
                    kamarinap.emptTeks();
                    kamarinap.isCek();
                    kamarinap.setNoRm(TNoRw.getText());
                    kamarinap.tampil();
                    kamarinap.setVisible(true);
                    kamarinap.cekKetMati();
                    kamarinap.UserValid();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Kunjungan rawat jalan poliklinik, tdk. bisa langsung rawat inap. Sepakati dulu alurnya..!!");
                tbKasirRalan.requestFocus();
            }
        }
    }
}//GEN-LAST:event_MnKamarInapActionPerformed

private void cmbStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbStatusItemStateChanged
    tampilkasir();
}//GEN-LAST:event_cmbStatusItemStateChanged

private void Kd2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kd2KeyPressed
    // TODO add your handling code here:
}//GEN-LAST:event_Kd2KeyPressed

private void MnDataPemberianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataPemberianObatActionPerformed
    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TNoRw.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
        TNoRw.requestFocus();
    } else {
        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
        } else {            
            dlgrwinap.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            dlgrwinap.setLocationRelativeTo(internalFrame1);
            dlgrwinap.isCek();
            dlgrwinap.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate(), "ralan");
            dlgrwinap.tampilPO();
            dlgrwinap.setVisible(true);
        }
    }
}//GEN-LAST:event_MnDataPemberianObatActionPerformed

    private void MnNoResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnNoResepActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                DlgResepObat resep = new DlgResepObat(null, false);
                resep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                resep.setLocationRelativeTo(internalFrame1);
                resep.emptTeks();
                resep.isCek();
                resep.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate(), Jam.getText().substring(0, 2), Jam.getText().substring(3, 5), Jam.getText().substring(6, 8));
                resep.setDokterRalan();
                resep.tampil();
                resep.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnNoResepActionPerformed

    private void MnPeriksaRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaRadiologiActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                DlgPeriksaRadiologi periksarad = new DlgPeriksaRadiologi(null, false);
                periksarad.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                periksarad.setLocationRelativeTo(internalFrame1);
                periksarad.emptTeks();
                periksarad.setNoRm(TNoRw.getText(), "Ralan");
                periksarad.tampil();
                periksarad.isCek();
                periksarad.setVisible(true);
                periksarad.fokus_kursor();
            }
        }
    }//GEN-LAST:event_MnPeriksaRadiologiActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowGantiPoli.dispose();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnSimpan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan4ActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        }
        if (kdpoli.getText().trim().equals("") || nmpoli.getText().trim().equals("")) {
            Valid.textKosong(kdpoli, "Poli");
        } else {
            Valid.editTable(tabModekasir, "reg_periksa", "no_rawat", TNoRw, " kd_poli='" + kdpoli.getText() + "'");
            Sequel.meghapus("antrian_prioritas", "no_rawat", TNoRw.getText());
            tampilkasir();
            WindowGantiPoli.dispose();
        }
    }//GEN-LAST:event_BtnSimpan4ActionPerformed

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", nmpoli, kdpoli.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnCariPoliActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCloseIn4, BtnSimpan4);
        }
    }//GEN-LAST:event_kdpoliKeyPressed

    private void btnCariPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPoliActionPerformed
        akses.setform("DlgKasirRalan");
        pilihan = 1;
        poliklinik.isCek();
        poliklinik.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poliklinik.setLocationRelativeTo(internalFrame1);
        poliklinik.setAlwaysOnTop(false);
        poliklinik.setVisible(true);
    }//GEN-LAST:event_btnCariPoliActionPerformed

    private void MnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPoliActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat=?", kdpoli, TNoRw.getText());
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", nmpoli, kdpoli.getText());

            WindowGantiPoli.setSize(630, 80);
            WindowGantiPoli.setLocationRelativeTo(internalFrame1);
            WindowGantiPoli.setAlwaysOnTop(false);
            WindowGantiPoli.setVisible(true);
            btnCariPoli.requestFocus();
        }
    }//GEN-LAST:event_MnPoliActionPerformed

    private void MnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiagnosaActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Masukkan diagnosa lewat kamar inap..!!!");
            } else {
                resep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                resep.setLocationRelativeTo(internalFrame1);
                resep.isCek();
                resep.setNoRm(TNoRw.getText(), DTPCari2.getDate(), "Ralan");
                resep.tampilDiagStatistik();
                resep.tampilDiagInadrg();
                resep.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnDiagnosaActionPerformed

    private void MnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBatalActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                Valid.editTable(tabModekasir, "reg_periksa", "no_rawat", TNoRw, "stts='Batal'");
                if (tabModekasir.getRowCount() != 0) {
                    tampilkasir();
                }
            }
        }
    }//GEN-LAST:event_MnBatalActionPerformed

    private void MnOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnOperasiActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                if (Sequel.cariInteger("select count(-1) from jadwal_operasi where nomr='" + NoRM.getText() + "' and no_rawat<>'-'") > 0) {
                    DlgTagihanOperasi dlgro = new DlgTagihanOperasi(null, false);
                    dlgro.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    dlgro.setLocationRelativeTo(internalFrame1);
                    dlgro.setNoRm(TNoRw.getText(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString() + ", " + tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 4).toString(), "Ralan");
                    dlgro.setVisible(true);
                    dlgro.fokus();
                } else if (Sequel.cariInteger("select count(-1) from jadwal_operasi where nomr='" + NoRM.getText() + "' and no_rawat='-'") > 0) {
                    x = JOptionPane.showConfirmDialog(rootPane, "Pasien ini sudah terjadwal operasi, data belum diupdate,    \n"
                            + "apakah akan diupdate jadwalnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        Sequel.mengedit("jadwal_operasi", "nomr='" + NoRM.getText() + "' and no_rawat='-'",
                                "no_rawat='" + TNoRw.getText() + "', last_update='" + Sequel.cariIsi("select now()") + "'");

                        JOptionPane.showMessageDialog(null, "Proses update jadwal operasi berhasil...!!!");
                    }
                } else {
                    x = JOptionPane.showConfirmDialog(rootPane, "Pasien ini belum dijadwalkan operasi, apakah akan dijadwalkan dulu..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        DlgJadwalOperasi jadwal = new DlgJadwalOperasi(null, false);
                        jadwal.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        jadwal.setLocationRelativeTo(internalFrame1);
                        jadwal.emptTeks();
                        jadwal.isCek();
                        jadwal.setData(NoRM.getText(), TNoRw.getText());
                        jadwal.setVisible(true);
                    }
                }
            }
        }
    }//GEN-LAST:event_MnOperasiActionPerformed

    private void MnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenjabActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbKasirRalan.requestFocus();
        } else {
            Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", kdpenjab, TNoRw.getText());
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpenjab, kdpenjab.getText());

            WindowCaraBayar.setSize(630, 80);
            WindowCaraBayar.setLocationRelativeTo(internalFrame1);
            WindowCaraBayar.setVisible(true);
            btnBayar.requestFocus();
        }
    }//GEN-LAST:event_MnPenjabActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowCaraBayar.dispose();
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

    private void BtnSimpan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan5ActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TCari, "No.Rawat");
        }
        if (kdpenjab.getText().trim().equals("") || nmpenjab.getText().trim().equals("")) {
            Valid.textKosong(kdpenjab, "Jenis Bayar");
        } else {
            //String kdpj=Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?",norawat.getText());
            Sequel.AutoComitFalse();
            //Sequel.meghapus("ubah_penjab","no_rawat",norawat.getText());
            Sequel.mengedit("reg_periksa", "no_rawat=?", " kd_pj=?", 2, new String[]{kdpenjab.getText(), TNoRw.getText()});
            //Sequel.menyimpan("ubah_penjab","?,?,?,?","Ubah Jenis Bayar",4,new String[]{norawat.getText(),now,kdpj,kdpenjab.getText()});
            Sequel.AutoComitTrue();
            tampilkasir();
            WindowCaraBayar.dispose();
        }
    }//GEN-LAST:event_BtnSimpan5ActionPerformed

    private void kdpenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpenjabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", nmpenjab, kdpenjab.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnBayarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCloseIn4, BtnSimpan4);
        }
    }//GEN-LAST:event_kdpenjabKeyPressed

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        akses.setform("DlgKasirRalan");
        penjab.emptTeks();
        penjab.isCek();
        penjab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penjab.setLocationRelativeTo(internalFrame1);
        penjab.setVisible(true);
    }//GEN-LAST:event_btnBayarActionPerformed

    private void MnHapusDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusDataActionPerformed
        cekSEPboking = "";

        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            cekSEPboking = Sequel.cariIsi("select kd_booking from booking_registrasi where no_rawat='" + TNoRw.getText() + "'");

            Sequel.AutoComitFalse();
            Sequel.queryu("delete from operasi where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from beri_obat_operasi where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from billing where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from nota_inap where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from nota_jalan where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from deposit where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from detail_beri_diet where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from diagnosa_pasien where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from dpjp_ranap where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from hemodialisa where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from kamar_inap where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from pengurangan_biaya where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from piutang_pasien where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from prosedur_pasien where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from ranap_gabung where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rujuk where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rujuk_masuk where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from tambahan_biaya where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rawat_inap_dr where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rawat_inap_drpr where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rawat_inap_pr where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rawat_jl_dr where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rawat_jl_drpr where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rawat_jl_pr where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from pemeriksaan_ralan where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from pemeriksaan_ranap where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from detail_periksa_lab where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from periksa_lab where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from beri_bhp_radiologi where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from periksa_radiologi where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from aturan_pakai where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from detail_pemberian_obat where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from tagihan_obat_langsung where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from resep_obat where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from resep_pulang where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from returpasien where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from stok_obat_pasien where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from detail_nota_jalan where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from detail_nota_inap where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from detail_piutang_pasien where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from ralan_aps where no_rawat='" + TNoRw.getText() + "'");
            Sequel.meghapus("reg_rujukan_intern", "no_rawat_ke", TNoRw.getText());

            Sequel.queryu("delete from catatan_resep where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from data_igd where no_rawat='" + TNoRw.getText() + "'");
            Sequel.meghapus("bridging_jamkesda", "no_sep", sepJkd.getText());
            Sequel.meghapus("bridging_jamkesda", "no_sep", sepJkdigd.getText());
            Sequel.meghapus("pasien_mati", "no_rkm_medis", NoRM.getText());
            Sequel.queryu("delete from reg_periksa where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from lis_reg where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from permintaan_lab_raza where no_rawat='" + TNoRw.getText() + "'");

            Sequel.mengedit("kelengkapan_booking_sep_bpjs", "kd_booking='" + cekSEPboking + "'", "status_cetak_sep='BELUM' ");
            Sequel.mengedit("booking_registrasi", "kd_booking='" + cekSEPboking + "'", "status_booking='Batal' ");

            Sequel.AutoComitTrue();
            tampilkasir();
        }
    }//GEN-LAST:event_MnHapusDataActionPerformed

    private void ppRiwayatBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRiwayatBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
            //TNoReg.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgResumePerawatan resume = new DlgResumePerawatan(null, true);
            resume.setNoRm(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 4).toString());
            resume.tampil();
            resume.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppRiwayatBtnPrintActionPerformed

    private void ppBerkasBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBerkasBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            //TNoReg.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbKasirRalan.requestFocus();
        } else {
            Sequel.menyimpan("mutasi_berkas", "'" + TNoRw.getText() + "','Sudah Diterima',now(),now(),'0000-00-00 00:00:00','0000-00-00 00:00:00'", "status='Sudah Diterima',diterima=now()", "no_rawat='" + TNoRw.getText() + "'");
            Valid.editTable(tabModekasir, "reg_periksa", "no_rawat", TNoRw, "stts='Berkas Diterima'");
            if (tabModekasir.getRowCount() != 0) {
                tampilkasir();
            }
        }
    }//GEN-LAST:event_ppBerkasBtnPrintActionPerformed

    private void MnRujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgRujuk dlgrjk = new DlgRujuk(null, false);
            dlgrjk.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgrjk.setLocationRelativeTo(internalFrame1);
            dlgrjk.emptTeks();
            dlgrjk.isCek();
            dlgrjk.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
            dlgrjk.tampilLain();
            dlgrjk.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRujukActionPerformed

    private void ppCatatanPasienBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCatatanPasienBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgCatatan catatan = new DlgCatatan(null, true);
            catatan.setNoRm(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString());
            catatan.setSize(720, 330);
            catatan.setLocationRelativeTo(internalFrame1);
            catatan.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppCatatanPasienBtnPrintActionPerformed

    private void MnResepFarmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnResepFarmasiActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                DlgPeresepanDokter resep = new DlgPeresepanDokter(null, false);
                resep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                resep.setLocationRelativeTo(internalFrame1);
                resep.isCek();
                resep.setNoRm(TNoRw.getText(), DTPCari1.getDate(), Jam.getText().substring(0, 2), Jam.getText().substring(3, 5), Jam.getText().substring(6, 8),
                        tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 2).toString());
                resep.tampilobat();
                resep.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnResepFarmasiActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            tampilkasir();
        } else if (TabRawat.getSelectedIndex() == 1) {
            hitungAntrianBPJS();
            hitungAntrianUMUM();
            hitungAntrianKhusus();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void MnPoliInternalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPoliInternalActionPerformed
        cekRujukInternal = 0;
        cekRujukInternal = Sequel.cariInteger("select count(-1) from rujukan_internal_poli where no_rawat='" + TNoRw.getText() + "'");

        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            DlgRujukanPoliInternal dlgrjk = new DlgRujukanPoliInternal(null, false);
            if (cekRujukInternal == 0 || cekRujukInternal > 1) {
                dlgrjk.setSize(787, 588);
                dlgrjk.setLocationRelativeTo(internalFrame1);
                dlgrjk.isCek();
                dlgrjk.setNoRm(TNoRw.getText(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString(),
                        tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 4).toString());
                dlgrjk.tampil();
                dlgrjk.inputbaru();
                dlgrjk.setVisible(true);
            } else if (cekRujukInternal == 1) {
                dlgrjk.setSize(787, 588);
                dlgrjk.setLocationRelativeTo(internalFrame1);
                dlgrjk.isCek();
                dlgrjk.setNoRm(TNoRw.getText(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString(),
                        tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 4).toString());
                dlgrjk.SatuTujuanPoli();
                dlgrjk.tampil();
                dlgrjk.setVisible(true);
                dlgrjk.BtnUnit.requestFocus();
            }
        }
    }//GEN-LAST:event_MnPoliInternalActionPerformed

    private void MnFormulirKlaimBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFormulirKlaimBtnPrintActionPerformed
        if (tbKasirRalan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else if (tbKasirRalan.getRowCount() != 0) {
            formulirKlaim();
        }
    }//GEN-LAST:event_MnFormulirKlaimBtnPrintActionPerformed

    private void MnLembarStatusPasienBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarStatusPasienBtnPrintActionPerformed
        if (tbKasirRalan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            BtnCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRMRaza.jasper", "report", "::[ Lembar Status Pasien Baru Rawat Jalan ]::",
                    " SELECT reg_periksa.no_rawat, pasien.no_rkm_medis,	pasien.nm_pasien, pasien.no_ktp, IF(pasien.jk='L','Laki-laki','Perempuan') as jk, "
                    + " pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir, '%d-%m-%Y') as tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) AS alamat, "
                    + " pasien.gol_darah, pasien.pekerjaan, pasien.stts_nikah, pasien.agama, reg_periksa.tgl_registrasi, pasien.no_tlp,	pasien.umur, "
                    + " pasien.pnd, penjab.png_jawab FROM pasien INNER JOIN kelurahan INNER JOIN kecamatan "
                    + " INNER JOIN kabupaten INNER JOIN penjab ON pasien.kd_kel = kelurahan.kd_kel "
                    + " AND pasien.kd_kec = kecamatan.kd_kec AND pasien.kd_kab = kabupaten.kd_kab "
                    + " INNER JOIN reg_periksa ON reg_periksa.kd_pj = penjab.kd_pj AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " WHERE reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLembarStatusPasienBtnPrintActionPerformed

    private void CrPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CrPoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_CrPoliKeyPressed

    private void sepJkdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sepJkdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_sepJkdKeyPressed

    private void sepJkdigdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sepJkdigdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_sepJkdigdKeyPressed

    private void NoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRMKeyPressed

    private void nmPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmPasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmPasienKeyPressed

    private void MnPermintaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPermintaanLabActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPermintaanLabRAZA lab = new DlgPermintaanLabRAZA(null, false);
                lab.setSize(978, internalFrame1.getHeight() - 40);
                lab.setLocationRelativeTo(internalFrame1);
                lab.isPasien(TNoRw.getText());
                lab.nmPemeriksaan.setText("");
                lab.nmPemeriksaan.requestFocus();
                lab.isCek();
                lab.tampilItemLab();
                lab.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPermintaanLabActionPerformed

    private void MnPermintaanRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPermintaanRadiologiActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPermintaanRadiologi dlgro = new DlgPermintaanRadiologi(null, false);
                dlgro.setSize(978, internalFrame1.getHeight() - 40);
                dlgro.setLocationRelativeTo(internalFrame1);
                dlgro.emptTeks();
                dlgro.isCek();
                dlgro.setNoRm(TNoRw.getText(), Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
                dlgro.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPermintaanRadiologiActionPerformed

    private void MnDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDietActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (kdpoli.getText().equals("HDL") || kdpoli.getText().equals("IGDK")) {
                DlgPemberianDiet dietralan = new DlgPemberianDiet(null, false);
                dietralan.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                dietralan.setLocationRelativeTo(internalFrame1);
                dietralan.emptTeks();
                dietralan.setNoRm(TNoRw.getText());
                dietralan.isCek();
                dietralan.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Hanya untuk pasien Hemodialisa & IGD saja...!!!!");
            }
        }
    }//GEN-LAST:event_MnDietActionPerformed

    private void dataGZKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dataGZKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataGZKeyPressed

    private void MnRekapTindakanPerbupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapTindakanPerbupActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (CrPoli.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu poli dari data yang sudah tersimpan...!!!");
            BtnSeek4.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. " + DTPCari1.getSelectedItem() + " S.D " + DTPCari2.getSelectedItem());
            param.put("poli", "REKAP TINDAKAN PERBUP KAB. BANJAR PASIEN DI POLI/UNIT " + CrPoli.getText());
            Valid.MyReport("rptrekaptindakanperbupralan.jasper", "report", "::[ Laporan Rekap Tindakan PERBUP Kab. Banjar Pasien Rawat Jalan ]::",
                    " SELECT tind.nm_perawatan, COUNT(case when r.kd_pj like 'U%' then 1 end) as Umum, COUNT(case when r.kd_pj like 'B%' then 1 end) as BPJS, "
                    + "COUNT(case when r.kd_pj like 'D%' then 1 end) as Jamkesda, COUNT(case when r.kd_pj like 'A%' then 1 end) as Asuransi, "
                    + "COUNT(case when (r.kd_pj not like 'U%' AND r.kd_pj not like 'B%' AND r.kd_pj not like 'D%' AND r.kd_pj not like 'A%')then 1 end) as Lainnya, "
                    + "COUNT(drpr.tgl_perawatan) AS Jumlah FROM rawat_jl_drpr drpr INNER JOIN jns_perawatan tind ON drpr.kd_jenis_prw = tind.kd_jenis_prw "
                    + "INNER JOIN reg_periksa r ON r.no_rawat = drpr.no_rawat INNER JOIN poliklinik po on po.kd_poli=r.kd_poli "
                    + "WHERE po.nm_poli like '%" + CrPoli.getText() + "%' AND drpr.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "GROUP BY tind.nm_perawatan order by jumlah desc", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRekapTindakanPerbupActionPerformed

    private void BtnPxBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPxBookingActionPerformed
        i = 0;
        pilihMenu = (String) JOptionPane.showInputDialog(null, "Silahkan tentukan pilihanya..!!", "Jenis Pasien Booking",
                JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Pasien BPJS   ", "Pasien UMUM   "}, "Pasien BPJS   ");
        switch (pilihMenu) {
            case "Pasien BPJS   ":
                i = 1;
                break;
            case "Pasien UMUM   ":
                i = 2;
                break;
        }

        if (i == 1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgKasirRalan");
            DlgVerifikasiKodeBoking form = new DlgVerifikasiKodeBoking(null, false);
            form.emptTeksBokingBPJS();
            form.Tantrian.setText("");
            form.setSize(460, 258);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        } else if (i == 2) {
            if (akses.getadmin() == true || akses.getreg_boking_kasir() == true) {
                WindowPasienBooking.setSize(575, 242);
                WindowPasienBooking.setLocationRelativeTo(internalFrame1);
                WindowPasienBooking.setAlwaysOnTop(false);
                WindowPasienBooking.setVisible(true);
                emptBooking();
            } else {
                JOptionPane.showMessageDialog(null, "Untuk pasien umum silahkan tunjukan kode booking langsung kekasir RS..!!");
            }
        }
    }//GEN-LAST:event_BtnPxBookingActionPerformed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowPasienBooking.dispose();
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
        if (kdboking.getText().trim().equals("")) {
            Valid.textKosong(kdboking, "kode booking pasien");
        } else if (norm.getText().equals("")) {
            Valid.textKosong(norm, "Pasien");
        } else if (KdDokter.getText().equals("")) {
            Valid.textKosong(KdDokter, "Dokter Spesialis");
        } else if (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03"))) {
            JOptionPane.showMessageDialog(null, "Untuk Pasien BPJS silakan lsg. ke anjungan SIPO yang sdh. tersedia...");
            emptBooking();
        } else {
            if (!cekTerdaftar.getText().equals("")) {
                n = JOptionPane.showConfirmDialog(null, "Pasien ini sdh. terdaftar hari ini, Apakah akan didaftarkan lagi dg. poli berbeda ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    Sequel.cariIsi("select no_rkm_medis from reg_periksa where tgl_registrasi='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "' and "
                            + "kd_poli='" + kdpoli1.getText() + "' and no_rkm_medis=?", cekPasien, norm.getText());

                    if (!cekPasien.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Pasien ini sdh. terdaftar dipoli & tanggal yang sama...");
                        emptBooking();
                    } else if (cekPasien.getText().equals("")) {
                        Sequel.menyimpantf2("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 18,
                                new String[]{TNoReg.getText(), norwBoking.getText(), Valid.SetTgl(tglPeriksa.getSelectedItem() + ""), Sequel.cariIsi("SELECT TIME(NOW())"),
                                    KdDokter.getText(), norm.getText(), kdpoli1.getText(), "-", "-", "-", 0 + "", "Belum",
                                    "Lama", "Ralan", kdpnj.getText(), umur, sttsumur, akses.getkode()});

                        Sequel.mengedit("booking_registrasi", "kd_booking='" + kdboking.getText() + "'", "status_booking='Terdaftar',no_rawat='" + norwBoking.getText() + "'");
                        Sequel.menyimpan("history_user", "Now(),'" + norwBoking.getText() + "','" + akses.getkode() + "','Registrasi Pasien','Simpan'");
                        emptBooking();
                        WindowPasienBooking.dispose();
                        tampilkasir();
                    }
                } else {
                    emptBooking();
                    WindowPasienBooking.dispose();
                    tampilkasir();
                }
            } else if (cekTerdaftar.getText().equals("")) {
                Sequel.menyimpantf2("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 18,
                        new String[]{TNoReg.getText(), norwBoking.getText(), Valid.SetTgl(tglPeriksa.getSelectedItem() + ""), Sequel.cariIsi("SELECT TIME(NOW())"),
                            KdDokter.getText(), norm.getText(), kdpoli1.getText(), "-", "-", "-", 0 + "", "Belum",
                            "Lama", "Ralan", kdpnj.getText(), umur, sttsumur, akses.getkode()});
                
                if (Tantrian.getText().equals("YA")) {
                    Sequel.menyimpanIgnore("antrian_prioritas", "'" + norwBoking.getText() + "','" + Sequel.cariIsi("select now()") + "'", "Data Antrian Prioritas");
                }

                Sequel.mengedit("booking_registrasi", "kd_booking='" + kdboking.getText() + "'", "status_booking='Terdaftar',no_rawat='" + norwBoking.getText() + "'");
                Sequel.menyimpan("history_user", "Now(),'" + norwBoking.getText() + "','" + akses.getkode() + "','Registrasi Pasien','Simpan'");
                emptBooking();
                WindowPasienBooking.dispose();
                tampilkasir();
            }
        }
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void kdpoli1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoli1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdpoli1KeyPressed

    private void kdbokingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbokingKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select kd_booking from booking_registrasi where kd_booking='" + kdboking.getText() + "' ", cekKodeBoking);

            if (kdboking.getText().trim().equals("")) {
                Valid.textKosong(kdboking, "kode booking pasien");
                emptBooking();
            } else if (cekKodeBoking.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Kode booking pasien tidak ditemukan..!!");
                emptBooking();
            } else if (!kdboking.getText().trim().equals("") && (!cekKodeBoking.getText().equals(""))) {
                cekPasienBoking();
                nomorAuto();
                umurPasien();

                Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rkm_medis='" + norm.getText() + "' "
                        + "and tgl_registrasi='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "'", cekTerdaftar);

                TCari.setText(norm.getText());
                BtnDokter.requestFocus();
            }
        }
    }//GEN-LAST:event_kdbokingKeyPressed

    private void normKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_normKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_normKeyPressed

    private void nmpasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmpasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmpasienKeyPressed

    private void kdpnjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpnjKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdpnjKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        pilihan = 1;
        dokter1.setPoli(nmpoli1.getText());
        dokter1.isCek();
        dokter1.setHari(Valid.SetTgl(tglPeriksa.getSelectedItem() + ""));
        dokter1.tampil();
        dokter1.TCari.requestFocus();
        dokter1.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter1.setLocationRelativeTo(internalFrame1);
        dokter1.setVisible(true);
        dokter1.emptTeks();
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnDokterActionPerformed(null);
        } else {
            Valid.pindah(evt, kdboking, BtnSimpan6);
        }
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void TNoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRegKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRegKeyPressed

    private void cekKodeBokingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekKodeBokingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekKodeBokingKeyPressed

    private void norwBokingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norwBokingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_norwBokingKeyPressed

    private void tglPeriksaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tglPeriksaItemStateChanged

    }//GEN-LAST:event_tglPeriksaItemStateChanged

    private void tglPeriksaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tglPeriksaMouseClicked

    }//GEN-LAST:event_tglPeriksaMouseClicked

    private void tglPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglPeriksaActionPerformed

    }//GEN-LAST:event_tglPeriksaActionPerformed

    private void tglPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglPeriksaKeyPressed

    }//GEN-LAST:event_tglPeriksaKeyPressed

    private void kdbokingKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbokingKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_kdbokingKeyTyped

    private void cekTerdaftarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekTerdaftarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekTerdaftarKeyPressed

    private void cekPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekPasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekPasienKeyPressed

    private void MnDataHAIsBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataHAIsBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            DlgDataHAIs hais = new DlgDataHAIs(null, false);
            hais.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            hais.setLocationRelativeTo(internalFrame1);
            hais.emptTeks();
            hais.isCek();
            hais.setNoRm(TNoRw.getText(), DTPCari2.getDate());
            hais.tampil();
            hais.setVisible(true);
        }
    }//GEN-LAST:event_MnDataHAIsBtnPrintActionPerformed

    private void MnPenilaianAwalKeperawatanRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianAwalKeperawatanRalanActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgKasirRalan");
                RMPenilaianAwalKeperawatanRalan form = new RMPenilaianAwalKeperawatanRalan(null, false);
                form.isCek();
                form.emptTeks();
                form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
                form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPenilaianAwalKeperawatanRalanActionPerformed

    private void MnStatusPasienAllKunjunganBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStatusPasienAllKunjunganBtnPrintActionPerformed
        x = 0;
        x = Sequel.cariInteger("SELECT count(1) cek FROM pemeriksaan_ralan_petugas prp INNER JOIN reg_periksa rp on rp.no_rawat=prp.no_rawat "
                + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter "
                + "LEFT JOIN diagnosa_pasien dp on dp.no_rawat=prp.no_rawat and dp.prioritas=1 "
                + "WHERE rp.no_rkm_medis = '" + NoRM.getText() + "' ");

        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else if (x == 0) {
            JOptionPane.showMessageDialog(null, "Data status pasien rawat jalan utk. semua kunjungan tdk. ditemukan..!!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRMRaza1.jasper", "report", "::[ Lembar Status Pasien Rawat Jalan Yang Sudah Terisi Semua Kunjungan ]::",
                    " SELECT rp.no_rawat, p.no_rkm_medis, p.nm_pasien, ifnull(p.no_ktp,'-') no_ktp, IF (p.jk = 'L','Laki-laki','Perempuan') jk, "
                    + "p.tmp_lahir, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lahir, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + "p.gol_darah, p.pekerjaan, p.stts_nikah, p.agama, concat(date_format(rp.tgl_registrasi,'%d/%m/%y'),' ',pj.png_jawab) tgl_registrasi, p.no_tlp, p.umur, p.pnd, "
                    + "LOWER(prp.pemeriksaan) pemeriksaan, LOWER(prp.diagnosa) diagnosa, IFNULL(dp.kd_penyakit,'-') kd_icd_10, LOWER(IFNULL(prp.terapi,'-')) terapi, d.nm_dokter "
                    + "FROM pemeriksaan_ralan_petugas prp INNER JOIN reg_periksa rp on rp.no_rawat=prp.no_rawat "
                    + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter "
                    + "LEFT JOIN diagnosa_pasien dp on dp.no_rawat=prp.no_rawat and dp.prioritas=1 "
                    + "WHERE rp.no_rkm_medis = '" + NoRM.getText() + "' ORDER BY rp.tgl_registrasi DESC", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnStatusPasienAllKunjunganBtnPrintActionPerformed

    private void MnCariPermintaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCariPermintaanLabActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgCariPermintaanLab cariLab = new DlgCariPermintaanLab(null, false);
            cariLab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            cariLab.setLocationRelativeTo(internalFrame1);
            cariLab.isCek(TNoRw.getText());
            cariLab.TCari.setText(TNoRw.getText());
            cariLab.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCariPermintaanLabActionPerformed

    private void MnCariPermintaanRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCariPermintaanRadActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgCariPermintaanRadiologi cariRad = new DlgCariPermintaanRadiologi(null, false);
            cariRad.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            cariRad.setLocationRelativeTo(internalFrame1);
            cariRad.isCek();
            cariRad.TCari.setText(TNoRw.getText());
            cariRad.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCariPermintaanRadActionPerformed

    private void ppPasienCoronaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPasienCoronaBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            NoRM.requestFocus();
        } else if (nmPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            cekPOLI = "";
            if (kdpoli.getText().equals("IGDK")) {
                cekPOLI = "IGD";
            } else {
                cekPOLI = "Poliklinik";
            }

            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            CoronaPasien form = new CoronaPasien(null, false);
            form.setPasien(NoRM.getText(), Tanggal.getText(), cekPOLI);
            form.isCek();
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            form.Inisial.requestFocus();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppPasienCoronaBtnPrintActionPerformed

    private void tbPasienMatiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPasienMatiMouseClicked
        if (tabModeMati.getRowCount() != 0) {
            try {
                getDataMati();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPasienMatiMouseClicked

    private void tbPasienMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienMatiKeyPressed
        if (tabModeMati.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataMati();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPasienMatiKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilMati();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowPasienMati.dispose();
        emptMati();
        TCari1.setText("");
        tampilkasir();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnCari1, TCari1);
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void rmMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rmMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_rmMatiKeyPressed

    private void nmpxMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmpxMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmpxMatiKeyPressed

    private void tglMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglMatiKeyPressed

    private void jamMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jamMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jamMatiKeyPressed

    private void ketMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ketMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ketMatiKeyPressed

    private void icdMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_icdMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_icdMatiKeyPressed

    private void desMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_desMatiKeyPressed

    }//GEN-LAST:event_desMatiKeyPressed

    private void tglLahrMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglLahrMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglLahrMatiKeyPressed

    private void tmptMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tmptMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tmptMatiKeyPressed

    private void ppCekPaseinMatiBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCekPaseinMatiBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            WindowPasienMati.setSize(992, 460);
            WindowPasienMati.setLocationRelativeTo(internalFrame1);
            WindowPasienMati.setAlwaysOnTop(false);
            WindowPasienMati.setVisible(true);
            emptMati();
            TCari1.requestFocus();
            tampilMati();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppCekPaseinMatiBtnPrintActionPerformed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        emptMati();
        tampilMati();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampilMati();
            TCari1.setText("");
        } else {
            Valid.pindah(evt, BtnCari1, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void ppPerawatanCoronaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPerawatanCoronaBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            tbKasirRalan.requestFocus();
        } else if (nmPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            INACBGPerawatanCorona form = new INACBGPerawatanCorona(null, false);
            form.emptTeks();
            form.setPasien(TNoRw.getText(), NoRM.getText(), Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + NoRM.getText() + "'"));
            form.isCek();
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppPerawatanCoronaBtnPrintActionPerformed

    private void MnSensusParuBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSensusParuBtnPrintActionPerformed
        cekPOLI = "";
        cekPOLI = Sequel.cariIsi("SELECT ifnull(kode_unit,'-') kode_unit FROM hak_akses_unit WHERE nip='" + akses.getkode() + "'");

        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else if (akses.getadmin() == true) {
            DlgRawatJalan dlgrwjl2 = new DlgRawatJalan(null, false);
            dlgrwjl2.WindowDataParu.setSize(571, 302);
            dlgrwjl2.WindowDataParu.setLocationRelativeTo(internalFrame1);
            dlgrwjl2.WindowDataParu.setVisible(true);
            dlgrwjl2.PasienParu(TNoRw.getText());
            dlgrwjl2.cekDataParu(TNoRw.getText());
        } else {
            if (cekPOLI.equals("PAR") && kdpoli.getText().equals("PAR")) {
                DlgRawatJalan dlgrwjl2 = new DlgRawatJalan(null, false);
                dlgrwjl2.WindowDataParu.setSize(571, 302);
                dlgrwjl2.WindowDataParu.setLocationRelativeTo(internalFrame1);
                dlgrwjl2.WindowDataParu.setVisible(true);
                dlgrwjl2.PasienParu(TNoRw.getText());
                dlgrwjl2.cekDataParu(TNoRw.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Pasien itu tidak terdaftar di poliklinik paru atau anda tidak ada hak aksesnya...!!!!");
                tbKasirRalan.requestFocus();
            }
        }
    }//GEN-LAST:event_MnSensusParuBtnPrintActionPerformed

    private void BtnCloseIn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn7ActionPerformed
        tampilkasir();
        empttext();
        WindowRiwayatKunjungan.dispose();
    }//GEN-LAST:event_BtnCloseIn7ActionPerformed

    private void BtnLewatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLewatiActionPerformed
        WindowRiwayatKunjungan.dispose();
        DlgRawatJalan dlgrwjl2 = new DlgRawatJalan(null, false);
        dlgrwjl2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dlgrwjl2.setLocationRelativeTo(internalFrame1);
        dlgrwjl2.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
        dlgrwjl2.tampilDrPr();
        dlgrwjl2.TotalNominal();
        dlgrwjl2.setVisible(true);
        dlgrwjl2.fokus();
        dlgrwjl2.petugas(kddokter.getText(), akses.getkode());
        dlgrwjl2.isCek();
    }//GEN-LAST:event_BtnLewatiActionPerformed

    private void tbRiwayatKunjMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatKunjMouseClicked
        if (tabModeKunjungan.getRowCount() != 0) {
            try {
                getdataRiwKunj();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwayatKunjMouseClicked

    private void tbRiwayatKunjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatKunjKeyPressed
        if (tabModeKunjungan.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getdataRiwKunj();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_V) {
                if (tbRiwayatKunj.getSelectedColumn() > 4) {
                    if (tbRiwayatKunj.getSelectedRow() != -1) {
                        if (tbRiwayatKunj.getValueAt(tbRiwayatKunj.getSelectedRow(), tbRiwayatKunj.getSelectedColumn()).toString().equals("false")) {
                            tbRiwayatKunj.setValueAt(true, tbRiwayatKunj.getSelectedRow(), tbRiwayatKunj.getSelectedColumn());
                        } else {
                            tbRiwayatKunj.setValueAt(false, tbRiwayatKunj.getSelectedRow(), tbRiwayatKunj.getSelectedColumn());
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatKunjKeyPressed

    private void pasiendipilihKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pasiendipilihKeyPressed

    }//GEN-LAST:event_pasiendipilihKeyPressed

    private void BtnRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRMActionPerformed
        if (pasiendipilih.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbRiwayatKunj.requestFocus();
        } else if (norw_dipilih.equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbRiwayatKunj.requestFocus();
        } else {
            WindowRiwayatKunjungan.dispose();
            DlgRawatJalan dlgrwjl2 = new DlgRawatJalan(null, false);
            dlgrwjl2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            dlgrwjl2.setLocationRelativeTo(internalFrame1);
            dlgrwjl2.setNoRm(norw_dipilih, TglKunRwt.getDate(), TglKunRwt.getDate());
            dlgrwjl2.tampilDrPr();
            dlgrwjl2.TotalNominal();
            dlgrwjl2.setVisible(true);
            dlgrwjl2.fokus();
            dlgrwjl2.petugas(kddokter_dipilih, akses.getkode());
            dlgrwjl2.isCek();
        }
    }//GEN-LAST:event_BtnRMActionPerformed

    private void TglKunRwtItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglKunRwtItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglKunRwtItemStateChanged

    private void TglKunRwtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglKunRwtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglKunRwtKeyPressed

    private void MnLihatSEPBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatSEPBtnPrintActionPerformed
        cekSEP = 0;
        kdpenjab.setText(Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));

        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            BtnCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan klik dulu nama pasiennya pada tabel..!!");
            tbKasirRalan.requestFocus();
        } else if (kdpenjab.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan klik dulu nama pasiennya pada tabel..!!");
            tbKasirRalan.requestFocus();
        } else if (kdpenjab.getText().equals("B01")) {
            cekSEP = Sequel.cariInteger("select count(-1) from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2'");
            if (cekSEP == 0) {
                JOptionPane.showMessageDialog(null, "SEP BPJS untuk pasien ini masih belum ada..!!");
                tbKasirRalan.requestFocus();
            } else if (cekSEP >= 1) {
                DlgNoSEP.setSize(451, 154);
                DlgNoSEP.setLocationRelativeTo(internalFrame1);
                DlgNoSEP.setVisible(true);

                nosep.setText(Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2'"));
                tglsep.setText(Sequel.cariIsi("select day(tglsep) from bridging_sep where no_sep='" + nosep.getText() + "' and jnspelayanan='2'") + " "
                        + Sequel.bulanINDONESIA("select month(tglsep) from bridging_sep where no_sep='" + nosep.getText() + "' and jnspelayanan='2'") + " "
                        + Sequel.cariIsi("select year(tglsep) from bridging_sep where no_sep='" + nosep.getText() + "' and jnspelayanan='2'"));
                jnsBayar.setText(Sequel.cariIsi("select png_jawab from penjab where kd_pj='" + kdpenjab.getText() + "'"));
            }
        } else if (!kdpenjab.getText().equals("B01")) {
            JOptionPane.showMessageDialog(null, "Pasien ini tidak menggunakan cara bayar BPJS Kesehatan..!!");
            tbKasirRalan.requestFocus();
        }
    }//GEN-LAST:event_MnLihatSEPBtnPrintActionPerformed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        DlgNoSEP.dispose();
        empttext();
        tampilkasir();
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    private void BtnCloseIn3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn3KeyPressed

    private void tglsepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglsepKeyPressed

    }//GEN-LAST:event_tglsepKeyPressed

    private void nosepKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nosepKeyTyped

    }//GEN-LAST:event_nosepKeyTyped

    private void nosepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nosepKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nosepKeyPressed

    private void jnsBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jnsBayarKeyPressed

    }//GEN-LAST:event_jnsBayarKeyPressed

    private void BtnPrint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint2ActionPerformed
        if (nosep.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data SEP tidak ditemukan...!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select bpjs from gambar"));

            if (Sequel.cariIsi("select urutan_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2'").equals("1")) {
                param.put("kunjunganInternal", "-");
            } else {
                param.put("kunjunganInternal", "- Kunjungan rujukan internal");
            }

            if (Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals("HIV")) {
                param.put("subSpesialis", Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='HIV'"));
                param.put("dokter", Sequel.cariIsi("select d.nm_dokter from reg_periksa rp inner join dokter d on d.kd_dokter=rp.kd_dokter "
                        + "where rp.no_rawat='" + TNoRw.getText() + "'"));

                Valid.MyReport("rptBridgingSEPvct.jasper", "report", "::[ Cetak SEP Rawat Jalan Poliklinik VCT ]::",
                        " SELECT no_sep, tglsep, CONCAT(no_kartu,' (MR. ',nomr,')') nomor, nama_pasien, "
                        + "CONCAT(tanggal_lahir,' Kelamin : ',if(jkel='L','Laki-laki','Perempuan')) tgl_lhr, IFNULL(notelep,'') notelp, "
                        + "nmpolitujuan sub_spesialis, nmdpjpLayan doktr, nmppkrujukan faskes_perujuk, nmdiagnosaawal diag_awal, catatan, "
                        + "peserta, if(jnspelayanan='2','R. Jalan','R.Inap') jns_rawat, SUBSTRING(tujuanKunjungan,4,14) jns_kun, '-' poli_perujuk, "
                        + "if(klsrawat='1','Kelas 1',if(klsrawat='2','Kelas 2','Kelas 3')) klsHak, IFNULL(nmKelasNaiknya,'') nmklsnaik, "
                        + "if(penjamin='null','',penjamin) penjamin FROM bridging_sep WHERE no_rawat='" + TNoRw.getText() + "' "
                        + "and jnspelayanan='2'", param);
            } else {
                Valid.MyReport("rptBridgingSEP2.jasper", "report", "::[ Cetak SEP Rawat Jalan ]::",
                        " SELECT no_sep, tglsep, CONCAT(no_kartu,' (MR. ',nomr,')') nomor, nama_pasien, "
                        + "CONCAT(tanggal_lahir,' Kelamin : ',if(jkel='L','Laki-laki','Perempuan')) tgl_lhr, IFNULL(notelep,'') notelp, "
                        + "nmpolitujuan sub_spesialis, nmdpjpLayan doktr, nmppkrujukan faskes_perujuk, nmdiagnosaawal diag_awal, catatan, "
                        + "peserta, if(jnspelayanan='2','R. Jalan','R.Inap') jns_rawat, SUBSTRING(tujuanKunjungan,4,14) jns_kun, '-' poli_perujuk, "
                        + "if(klsrawat='1','Kelas 1',if(klsrawat='2','Kelas 2','Kelas 3')) klsHak, IFNULL(nmKelasNaiknya,'') nmklsnaik, "
                        + "if(penjamin='null','',penjamin) penjamin FROM bridging_sep WHERE no_rawat='" + TNoRw.getText() + "' "
                        + "and jnspelayanan='2'", param);
            }

            Sequel.mengedit("kelengkapan_booking_sep_bpjs", "no_rawat='" + TNoRw.getText() + "'", "status_cetak_sep='SUDAH' ");

            DlgNoSEP.dispose();
            empttext();
            tampilkasir();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrint2ActionPerformed

    private void BtnPrint2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint2KeyPressed

    }//GEN-LAST:event_BtnPrint2KeyPressed

    private void ppSuratKontrolBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSuratKontrolBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + NoRM.getText() + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah berpulang ke rahmatullah, semoga Husnul Khotimah...!!!");
            tbKasirRalan.requestFocus();
        } else {
            cekSEP = 0;
            cekSEP = Sequel.cariInteger("select count(-1) from bridging_sep where no_rawat='" + TNoRw.getText() + "'");
            if (cekSEP == 0) {
                JOptionPane.showMessageDialog(null, "Pasien ini belum mempunyai/dibikinkan SEP BPJS nya...!!!");
            } else if (cekSEP > 0) {
                BPJSSuratKontrol form = new BPJSSuratKontrol(null, false);
                form.setNoRm(TNoRw.getText(),
                        Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "'"),
                        Sequel.cariIsi("select no_kartu from bridging_sep where no_rawat='" + TNoRw.getText() + "'"),
                        NoRM.getText(),
                        nmPasien.getText(),
                        Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                        Sequel.cariIsi("select IF(jk='L','Laki-laki','Perempuan') from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                        Sequel.cariIsi("select nmdiagnosaawal from bridging_sep where no_rawat='" + TNoRw.getText() + "'"),
                        kdpoli.getText());
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.ChkInput.setSelected(true);
                form.TCari.setText(NoRM.getText());
                form.tampil();
                form.isForm();
                form.isCek();
                form.setVisible(true);
            }
        }
    }//GEN-LAST:event_ppSuratKontrolBtnPrintActionPerformed

    private void ChkAutoRefresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAutoRefresActionPerformed
        if (ChkAutoRefres.isSelected() == true) {
            if (!DTPCari1.getSelectedItem().equals(DTPCari2.getSelectedItem())) {
                JOptionPane.showMessageDialog(null, "Tgl. periode harus sama, jika berbeda fitur ini tdk. bisa digunakan..!!");
                ChkAutoRefres.setSelected(false);
            } else if (CrPoli.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Poliklinik dimaksud harus dipilih dulu..!!");
                ChkAutoRefres.setSelected(false);
                BtnSeek4.requestFocus();
            } else {
                akses.tRefreshPoli.start();
            }
        } else {
            akses.tRefreshPoli.stop();
            tampilkasir();
            empttext();
        }
    }//GEN-LAST:event_ChkAutoRefresActionPerformed

    private void MnKlaimJKNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKlaimJKNActionPerformed
        crBayar = "";

        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            crBayar = Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + TNoRw.getText() + "'");
            if (crBayar.equals("B01") || crBayar.equals("A03")) {
                INACBGDaftarKlaim diklaim = new INACBGDaftarKlaim(null, false);
                diklaim.isCek();
                diklaim.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                diklaim.setLocationRelativeTo(internalFrame1);
                diklaim.verifData();
                diklaim.KlaimRAZA(TNoRw.getText(), Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "'"),
                        "JKN", "3", Sequel.cariIsi("select tglsep from bridging_sep where no_rawat='" + TNoRw.getText() + "' and urutan_sep=1"));
                diklaim.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Hanya untuk klaim pasien JKN/BPJS Kesehatan saja...!!!!");
            }
        }
    }//GEN-LAST:event_MnKlaimJKNActionPerformed

    private void MnKlaimCOVIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKlaimCOVIDActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            INACBGDaftarKlaim diklaim = new INACBGDaftarKlaim(null, false);
            diklaim.isCek();
            diklaim.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            diklaim.setLocationRelativeTo(internalFrame1);
            diklaim.verifData();
            diklaim.KlaimRAZA(TNoRw.getText(), "", "JAMINAN COVID-19", "71", Tanggal.getText());
            diklaim.setVisible(true);
        }
    }//GEN-LAST:event_MnKlaimCOVIDActionPerformed

    private void MnKlaimKIPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKlaimKIPIActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            INACBGDaftarKlaim diklaim = new INACBGDaftarKlaim(null, false);
            diklaim.isCek();
            diklaim.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            diklaim.setLocationRelativeTo(internalFrame1);
            diklaim.verifData();
            diklaim.KlaimRAZA(TNoRw.getText(), "", "JAMINAN KIPI", "72", Tanggal.getText());
            diklaim.setVisible(true);
        }
    }//GEN-LAST:event_MnKlaimKIPIActionPerformed

    private void MnSEPBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSEPBPJSActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals("B01")) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pasien BPJS Kesehatan saja...!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgKasirRalan");
            BPJSDataSEP dlgki = new BPJSDataSEP(null, false);
            dlgki.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgki.setLocationRelativeTo(internalFrame1);
            dlgki.isCek();

            if (kdpoli.getText().equals("IGDK")) {
                dlgki.setNoRm(TNoRw.getText(), "2. Ralan", "IGD", "INSTALASI GAWAT DARURAT");
            } else {
                dlgki.setNoRm(TNoRw.getText(), "2. Ralan", kdpoli.getText(), Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
            }

            dlgki.tampil();
            dlgki.setVisible(true);
            dlgki.cekLAYAN();
//            dlgki.tampilNoRujukan(Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis='" + NoRM.getText() + "'"));
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSEPBPJSActionPerformed

    private void MnDietMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDietMakananActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else {
            DlgPemberianDiet dietRalan = new DlgPemberianDiet(null, false);
            dietRalan.WindowLabelGiziRALAN.setSize(596, 370);
            dietRalan.WindowLabelGiziRALAN.setLocationRelativeTo(internalFrame1);
            dietRalan.WindowLabelGiziRALAN.setVisible(true);
            dietRalan.emptLabelGZ();
            dietRalan.tampilPoliGZ();
        }
    }//GEN-LAST:event_MnDietMakananActionPerformed

    private void BtnCloseIn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn8ActionPerformed
        WindowRehabMedik.dispose();
    }//GEN-LAST:event_BtnCloseIn8ActionPerformed

    private void BtnSimpan7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan7ActionPerformed
        cekPilihanRehab = 0;
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TCari, "No. Rawat");
        }

        if (cmbRM.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu pilihan jenis rehabilitasi medik dg. benar...!!!!");
            cmbRM.requestFocus();
        } else {
            cekPilihanRehab = Sequel.cariInteger("select count(-1) from data_rehab_medik where no_rawat='" + TNoRw.getText() + "'");

            if (cekPilihanRehab == 0) {
                Sequel.menyimpan("data_rehab_medik", "?,?", "Jenis Rehabilitasi Medik", 2, new String[]{TNoRw.getText(), cmbRM.getSelectedItem().toString()});
            } else if (cekPilihanRehab > 0) {
                Sequel.mengedit("data_rehab_medik", "no_rawat=?", " jns_rehabmedik=?", 2, new String[]{cmbRM.getSelectedItem().toString(), TNoRw.getText()});
            }

            tampilkasir();
            WindowRehabMedik.dispose();
        }
    }//GEN-LAST:event_BtnSimpan7ActionPerformed

    private void MnRehabMedikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRehabMedikActionPerformed
        cekPilihanRehab = 0;

        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("IRM") && !kdpoli.getText().equals("IRS")) {
            JOptionPane.showMessageDialog(rootPane, "Hanya utk. pasien yg. berkunjung ke poliklinik rehabilitasi medik...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            cekPilihanRehab = Sequel.cariInteger("select count(-1) from data_rehab_medik where no_rawat='" + TNoRw.getText() + "'");

            if (cekPilihanRehab == 0) {
                cmbRM.setSelectedIndex(0);
                cmbRM.requestFocus();
            } else if (cekPilihanRehab > 0) {
                cmbRM.setSelectedItem(Sequel.cariIsi("select jns_rehabmedik from data_rehab_medik where no_rawat='" + TNoRw.getText() + "'"));
            }

            WindowRehabMedik.setSize(535, 84);
            WindowRehabMedik.setLocationRelativeTo(internalFrame1);
            WindowRehabMedik.setAlwaysOnTop(false);
            WindowRehabMedik.setVisible(true);
        }
    }//GEN-LAST:event_MnRehabMedikActionPerformed

    private void MnReturJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnReturJualActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            TCari.requestFocus();
        } else {
            if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
            } else {
                akses.setform("DlgKasirRalan");
                DlgReturJual returjual = new DlgReturJual(null, false);
                returjual.emptTeks();
                returjual.isCek();
                returjual.setPasien(NoRM.getText(), TNoRw.getText());
                returjual.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                returjual.setLocationRelativeTo(internalFrame1);
                returjual.setVisible(true);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnReturJualActionPerformed

    private void MnTeridentifikasiTBBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTeridentifikasiTBBtnPrintActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Masukkan data teridentifikasi TB Kemenkes dikamar inap..!!!");
            } else if (Sequel.cariInteger("SELECT count(-1) FROM data_tb WHERE no_ktp='" + nik + "' or no_rkm_medis='" + NoRM.getText() + "'") < 1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgDataTB tb = new DlgDataTB(null, false);
                tb.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                tb.setLocationRelativeTo(internalFrame1);
                tb.isCek();
                tb.emptTeks();
                tb.setNoRM(TNoRw.getText());
                tb.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Data SITB pasien ini sudah ada, lakukan update pada halaman pasien SITB");
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgDataTB tb = new DlgDataTB(null, false);
                tb.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                tb.setLocationRelativeTo(internalFrame1);
                tb.isCek();
                tb.emptTeks();
                tb.TabRawat.setSelectedIndex(1);
                tb.tampil();
                tb.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnTeridentifikasiTBBtnPrintActionPerformed

    private void ppProgramPRBBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppProgramPRBBtnPrintActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else if (!Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals("B01")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien BPJS saja...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            BPJSProgramPRB form = new BPJSProgramPRB(null, false);
            form.setNoRm(TNoRw.getText(),
                    Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "'"),
                    Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                    NoRM.getText(),
                    nmPasien.getText(),
                    Sequel.cariIsi("select alamat from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                    Sequel.cariIsi("select ifnull(dpjpLayan,'') from bridging_sep where no_rawat='" + TNoRw.getText() + "'"),
                    Sequel.cariIsi("select ifnull(nmdpjpLayan,'') from bridging_sep where no_rawat='" + TNoRw.getText() + "'"));
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppProgramPRBBtnPrintActionPerformed

    private void MnRencanaKontrolManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRencanaKontrolManualActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from reg_periksa where no_rawat='" + TNoRw.getText() + "' and kd_pj='B01'") == 0) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya berlaku untuk pasien BPJS Kesehatan saja...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + NoRM.getText() + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah berpulang ke rahmatullah, semoga Husnul Khotimah...!!!");
            tbKasirRalan.requestFocus();
        } else {
            DlgRencanaKontrolManual form = new DlgRencanaKontrolManual(null, false);
            form.setSize(634, 221);
            form.setLocationRelativeTo(internalFrame1);
            form.setData("Ralan", kdpoli.getText(), NoRM.getText(), TDokter.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnRencanaKontrolManualActionPerformed

    private void MnPenilaianAwalKeperawatanKebidananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianAwalKeperawatanKebidananActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            tbKasirRalan.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("OBG")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar dipoliklinik KEBIDANAN & KANDUNGAN...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgKasirRalan");
                RMPenilaianAwalKeperawatanKebidanan form = new RMPenilaianAwalKeperawatanKebidanan(null, false);
                form.isCek();
                form.emptTeks();
                form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
                form.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPenilaianAwalKeperawatanKebidananActionPerformed

    private void MnIkhtisarPerawatanHIVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnIkhtisarPerawatanHIVActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("HIV")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya utk. pasien yg. berkunjung ke poliklinik VCT saja...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgKasirRalan");
                DlgIkhtisarPerawatanHIVart hiv = new DlgIkhtisarPerawatanHIVart(null, false);
                hiv.isCek();
                hiv.setNoRm(NoRM.getText());
                hiv.tampil12();
                hiv.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                hiv.setLocationRelativeTo(internalFrame1);
                hiv.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnIkhtisarPerawatanHIVActionPerformed

    private void MnPemeriksaanKlinisLabHIVBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemeriksaanKlinisLabHIVBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("HIV")) {
            JOptionPane.showMessageDialog(null, "Maaf, Menu ini TIDAK utk. pasien yg. berobat kepoli " + tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 6).toString() + "...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + NoRM.getText() + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah berpulang ke rahmatullah, semoga Husnul Khotimah...!!!");
            tbKasirRalan.requestFocus();
        } else {
            akses.setform("DlgKasirRalan");
            DlgPemeriksaanKlinisLabHIV hiv1 = new DlgPemeriksaanKlinisLabHIV(null, false);
            hiv1.setData(NoRM.getText(), Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                    Sequel.cariIsi("select if(jk='L','Laki-laki','Perempuan') from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                    Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + NoRM.getText() + "'"));
            hiv1.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            hiv1.setLocationRelativeTo(internalFrame1);
            hiv1.ChkInput.setSelected(true);
            hiv1.emptTeks();
            hiv1.TCari.setText(NoRM.getText());
            hiv1.tampil();
            hiv1.isForm();
            hiv1.isCek();
            hiv1.setVisible(true);
        }
    }//GEN-LAST:event_MnPemeriksaanKlinisLabHIVBtnPrintActionPerformed

    private void MnTerapiAntiretroviralHIVBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTerapiAntiretroviralHIVBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("HIV")) {
            JOptionPane.showMessageDialog(null, "Maaf, Menu ini TIDAK utk. pasien yg. berobat kepoli " + tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 6).toString() + "...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + NoRM.getText() + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah berpulang ke rahmatullah, semoga Husnul Khotimah...!!!");
            tbKasirRalan.requestFocus();
        } else {
            akses.setform("DlgKasirRalan");
            DlgTerapiAntiretroviralHIV hiv2 = new DlgTerapiAntiretroviralHIV(null, false);
            hiv2.setData(NoRM.getText(), Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                    Sequel.cariIsi("select if(jk='L','Laki-laki','Perempuan') from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                    Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + NoRM.getText() + "'"));
            hiv2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            hiv2.setLocationRelativeTo(internalFrame1);
            hiv2.ChkInput.setSelected(true);
            hiv2.emptTeks();
            hiv2.TCari.setText(NoRM.getText());
            hiv2.tampil();
            hiv2.isForm();
            hiv2.isCek();
            hiv2.setVisible(true);
        }
    }//GEN-LAST:event_MnTerapiAntiretroviralHIVBtnPrintActionPerformed

    private void MnFollowUPPerawatanTerapiHIVBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFollowUPPerawatanTerapiHIVBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("HIV")) {
            JOptionPane.showMessageDialog(null, "Maaf, Menu ini TIDAK utk. pasien yg. berobat kepoli " + tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 6).toString() + "...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + NoRM.getText() + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah berpulang ke rahmatullah, semoga Husnul Khotimah...!!!");
            tbKasirRalan.requestFocus();
        } else {
            akses.setform("DlgKasirRalan");
            DlgFollowUpPerawatanTerapiARThiv hiv3 = new DlgFollowUpPerawatanTerapiARThiv(null, false);
            hiv3.setData(NoRM.getText(), Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                    Sequel.cariIsi("select if(jk='L','Laki-laki','Perempuan') from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                    Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + NoRM.getText() + "'"));
            hiv3.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            hiv3.setLocationRelativeTo(internalFrame1);
            hiv3.ChkInput.setSelected(true);
            hiv3.emptTeks();
            hiv3.tampil();
            hiv3.isForm();
            hiv3.isCek();
            hiv3.setVisible(true);
        }
    }//GEN-LAST:event_MnFollowUPPerawatanTerapiHIVBtnPrintActionPerformed

    private void MnDataKankerBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataKankerBtnPrintActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from setting_bridging where kd_bridging='1' and status_aktif='Ya'") == 1) {
                if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Masukkan data penyakit kanker dikamar inap..!!!");
                } else {
                    if (Sequel.cariInteger("SELECT count(-1) FROM data_kanker_bridging WHERE no_rawat='" + TNoRw.getText() + "'") < 1) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        DlgDataKanker kanker = new DlgDataKanker(null, false);
                        kanker.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        kanker.setLocationRelativeTo(internalFrame1);
                        kanker.isCek();
                        kanker.emptTeks();
                        kanker.setData(TNoRw.getText());
                        kanker.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Data penyakit kanker pasien ini sudah ada, lakukan update pada halaman data penyakit kanker.");
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        DlgDataKanker kanker = new DlgDataKanker(null, false);
                        kanker.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        kanker.setLocationRelativeTo(internalFrame1);
                        kanker.isCek();
                        kanker.emptTeks();
                        kanker.TabRawat.setSelectedIndex(1);
                        kanker.tampil();
                        kanker.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Bridging data penyakit kanker dinonaktifkan, permasalahanya masih dalam pembahasan...");
            }
        }
    }//GEN-LAST:event_MnDataKankerBtnPrintActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        akses.tRefreshAntrian.start();
        TabRawat.setSelectedIndex(0);
    }//GEN-LAST:event_formWindowOpened

    private void MnPenilaianAwalMedisRalanTHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianAwalMedisRalanTHTActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            //TNoReg.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("THT")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar dipoliklinik THT...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgKasirRalan");
                RMPenilaianAwalMedisRalanTHT form = new RMPenilaianAwalMedisRalanTHT(null, false);
                form.isCek();
                form.emptTeks();
                form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), kddokter.getText(), TDokter.getText());
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPenilaianAwalMedisRalanTHTActionPerformed

    private void MnSuratPengantarRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratPengantarRanapActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap..!!!");
            } else {
                DlgPengantarRanap surat = new DlgPengantarRanap(null, false);
                surat.setSize(685, 217);
                surat.setLocationRelativeTo(internalFrame1);
                surat.setData(TNoRw.getText(), NoRM.getText(), Tanggal.getText(), Jam.getText(), kddokter.getText());
                surat.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnSuratPengantarRanapActionPerformed

    private void ppRekamPsikologisBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRekamPsikologisBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + NoRM.getText() + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah berpulang ke rahmatullah, semoga Husnul Khotimah...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("PSI")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar dipoliklinik PSIKOLOGI...!!!");
            tbKasirRalan.requestFocus();
        } else {
            sttsumur = "";
            cekUmur = 0;
            cekUmur = Sequel.cariInteger("select umurdaftar from reg_periksa where no_rawat='" + TNoRw.getText() + "'");
            sttsumur = Sequel.cariIsi("select sttsumur from reg_periksa where no_rawat='" + TNoRw.getText() + "'");

            if (cekUmur >= 18 && sttsumur.equals("Th")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgRekamPsikologisDewasa form = new DlgRekamPsikologisDewasa(null, false);
                form.setNoRm(TNoRw.getText(), DTPCari1.getDate());
                form.isCek();
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else if (cekUmur <= 17 && sttsumur.equals("Th")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgRekamPsikologisAnak anak = new DlgRekamPsikologisAnak(null, false);
                anak.setNoRm(TNoRw.getText(), DTPCari1.getDate());
                anak.isCek();
                anak.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                anak.setLocationRelativeTo(internalFrame1);
                anak.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Kategori umur tidak valid utk. rekam data psikologis pasien...!!!");
            }
        }
    }//GEN-LAST:event_ppRekamPsikologisBtnPrintActionPerformed

    private void MnRegistrasiKeTBDOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRegistrasiKeTBDOTActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("PAR")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar dipoliklinik PARU saja...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + NoRM.getText() + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah berpulang ke rahmatullah, semoga Husnul Khotimah...!!!");
            tbKasirRalan.requestFocus();
        } else if (!Tanggal.getText().equals(Sequel.cariIsi("SELECT DATE(NOW())"))) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya berlaku untuk tanggal yang sama...!!!");
        } else {
            if (Sequel.cariInteger("SELECT count(-1) FROM reg_rujukan_intern where no_rawat_dari='" + TNoRw.getText() + "'") == 0) {
                DlgRegPoliTBDOT.setSize(575, 104);
                DlgRegPoliTBDOT.setLocationRelativeTo(internalFrame1);
                DlgRegPoliTBDOT.setVisible(true);
                kdDokterRujuk.setText(kddokter.getText());
                nmDokterRujuk.setText(TDokter.getText());
                BtnDokterRujuk.requestFocus();
            } else if (Sequel.cariInteger("SELECT count(-1) FROM reg_rujukan_intern where no_rawat_dari='" + TNoRw.getText() + "'") > 0) {
                JOptionPane.showMessageDialog(null, "Data registrasi ke poliklinik TB DOT pasien ini sudah tersimpan...!!!");
                BtnCariActionPerformed(null);
            }
        }
    }//GEN-LAST:event_MnRegistrasiKeTBDOTActionPerformed

    private void BtnSimpanRujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanRujukActionPerformed
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where tgl_registrasi='" + Tanggal.getText() + "' and "
                + "kd_poli='TBD' and no_rkm_medis=?", cekPasien, NoRM.getText());

        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else if (kdDokterRujuk.getText().equals("")) {
            Valid.textKosong(kdDokterRujuk, "Dokter Spesialis");
        } else if (Sequel.cariInteger("select count(pasien.no_rkm_medis) from pasien inner join reg_periksa inner join kamar_inap "
                + "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat "
                + "where kamar_inap.stts_pulang='-' and pasien.no_rkm_medis=?", NoRM.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Pasien sedang dalam masa perawatan di kamar inap..!!");
        } else if (!cekPasien.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien ini sdh. terdaftar dipoli & tanggal yang sama...");
            BtnKeluar5.requestFocus();
        } else {
            isCekPasien();
            isNumberRujuk();
            if (Sequel.menyimpantf("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 18,
                    new String[]{TNoReg.getText(), norwBARU.getText(), Tanggal.getText(), Sequel.cariIsi("SELECT TIME(NOW())"),
                        kdDokterRujuk.getText(), NoRM.getText(), "TBD", TPngJwb, TAlmt, THbngn, TBiaya, "Belum",
                        TStatus, "Ralan", kdpnj.getText(), umur, sttsumur, akses.getkode()}) == true) {

                Sequel.menyimpan("reg_rujukan_intern", "'" + TNoRw.getText() + "','" + norwBARU.getText() + "'", "Reg. Rujukan Internal Poliklinik");
                Sequel.mengedit("pasien", "no_rkm_medis=?", "umur=CONCAT(CONCAT(CONCAT(TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()), ' Th '),CONCAT(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12), ' Bl ')),CONCAT(TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()), ' Hr'))", 1, new String[]{NoRM.getText()});
                Sequel.mengedit("pasien", "no_rkm_medis='" + NoRM.getText() + "'", "suku_bangsa='" + kdsuku + "', bahasa_pasien='" + kdbahasa + "' ");

                BtnKeluar5ActionPerformed(null);
                JOptionPane.showMessageDialog(null, "Registrasi Ke Poli TB DOT berhasil tersimpan...!!!");
                BtnCariActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnSimpanRujukActionPerformed

    private void BtnKeluar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar5ActionPerformed
        DlgRegPoliTBDOT.dispose();
        kdDokterRujuk.setText("");
        nmDokterRujuk.setText("");
        BtnCariActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar5ActionPerformed

    private void BtnDokterRujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterRujukActionPerformed
        akses.setform("DlgKasirRalan");
//        if (aktifjadwal.equals("aktif")) {
//            if (var.getkode().equals("Admin Utama")) {                
//                dokter2.isCek();
//                dokter2.TCari.requestFocus();
//                dokter2.setSize(1041, 332);
//                dokter2.setLocationRelativeTo(internalFrame1);
//                dokter2.setVisible(true);
//                dokter2.emptTeks();
//            } else {
//                pilihan = 2;
//                dokter1.setPoli("TB DOTS");
//                dokter1.isCek();
//                dokter1.tampil();
//                dokter1.TCari.requestFocus();
//                dokter1.setSize(1041, 332);
//                dokter1.setLocationRelativeTo(internalFrame1);
//                dokter1.setVisible(true);
//                dokter1.emptTeks();
//            }
//        } else {
        dokter2.isCek();
        dokter2.TCari.requestFocus();
        dokter2.setSize(1041, 332);
        dokter2.setLocationRelativeTo(internalFrame1);
        dokter2.setVisible(true);
        dokter2.emptTeks();
//        }
    }//GEN-LAST:event_BtnDokterRujukActionPerformed

    private void norwBARUKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norwBARUKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_norwBARUKeyPressed

    private void ppRekamPsikologiPerkawinanBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRekamPsikologiPerkawinanBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + NoRM.getText() + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah berpulang ke rahmatullah, semoga Husnul Khotimah...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("PSI")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar dipoliklinik PSIKOLOGI...!!!");
            tbKasirRalan.requestFocus();
        } else {
            sttsumur = "";
            cekUmur = 0;
            cekUmur = Sequel.cariInteger("select umurdaftar from reg_periksa where no_rawat='" + TNoRw.getText() + "'");
            sttsumur = Sequel.cariIsi("select sttsumur from reg_periksa where no_rawat='" + TNoRw.getText() + "'");

            if (cekUmur >= 15 && sttsumur.equals("Th")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgRekamPsikologisPerkawinan form = new DlgRekamPsikologisPerkawinan(null, false);
                form.setNoRm(TNoRw.getText(), DTPCari1.getDate());
                form.isCek();
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Kategori umur pernikahan tidak valid utk. rekam data psikologi perkawinan pasien...!!!");
            }
        }
    }//GEN-LAST:event_ppRekamPsikologiPerkawinanBtnPrintActionPerformed

    private void MnPenilaianTambahanGeriatriBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianTambahanGeriatriBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            BtnCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("006")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar dipoliklinik Geriatri...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                RMPenilaianTambahanGeriatri form = new RMPenilaianTambahanGeriatri(null, false);
                form.isCek();
                form.emptTeks();
                form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), kddokter.getText(), TDokter.getText());
                form.tampil();
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPenilaianTambahanGeriatriBtnPrintActionPerformed

    private void MnPenilaianAwalMedisRalanGeriatriBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianAwalMedisRalanGeriatriBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            BtnCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("006")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar dipoliklinik Geriatri...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgKasirRalan");
                RMPenilaianAwalMedisRalanGeriatri form = new RMPenilaianAwalMedisRalanGeriatri(null, false);
                form.isCek();
                form.emptTeks();
                form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), kddokter.getText(), TDokter.getText());
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPenilaianAwalMedisRalanGeriatriBtnPrintActionPerformed

    private void MnNomorTBBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnNomorTBBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + NoRM.getText() + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah berpulang ke rahmatullah, semoga Husnul Khotimah...!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgNomorTB form = new DlgNomorTB(null, false);
            form.setData(NoRM.getText(), nmPasien.getText());
            form.setSize(506, 201);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnNomorTBBtnPrintActionPerformed

    private void MnPenilaianAwalMedisRalanMataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianAwalMedisRalanMataActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            tbKasirRalan.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("MAT")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar dipoliklinik Mata...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgKasirRalan");
                RMPenilaianAwalMedisRalanMata form = new RMPenilaianAwalMedisRalanMata(null, false);
                form.isCek();
                form.emptTeks();
                form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPenilaianAwalMedisRalanMataActionPerformed

    private void MnSuratIstirahatSakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratIstirahatSakitActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            BtnCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (akses.getadmin() == true) {
                if (tbKasirRalan.getSelectedRow() != -1) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgSuratIstirahatSakit form = new DlgSuratIstirahatSakit(null, false);
                    form.isCek();
                    form.setData(TNoRw.getText());
                    form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            } else if (Sequel.cariIsi("select kode_unit from hak_akses_unit where nip='" + akses.getkode() + "' and kode_unit ='" + kdpoli.getText() + "'").equals(kdpoli.getText())
                    || Sequel.cariIsi("select h.kode_unit from hak_akses_unit h inner join petugas p on p.user_id = h.nip where "
                            + "p.nip='" + akses.getkode() + "' and h.kode_unit ='" + kdpoli.getText() + "'").equals(kdpoli.getText())
                    || Sequel.cariIsi("select kode_unit from hak_akses_unit where nip='" + akses.getkode() + "'").equals("semua ralan")
                    || Sequel.cariIsi("select kode_unit from hak_akses_unit where nip='" + akses.getkode() + "'").equals("semua ralan ranap")) {
                if (tbKasirRalan.getSelectedRow() != -1) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgSuratIstirahatSakit form = new DlgSuratIstirahatSakit(null, false);
                    form.isCek();
                    form.setData(TNoRw.getText());
                    form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Silahkan hubungi petugas poliklinik " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'") + " utk. surat istirahat sakitnya...!!!");
                tbKasirRalan.requestFocus();
            }
        }
    }//GEN-LAST:event_MnSuratIstirahatSakitActionPerformed

    private void btnCariDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariDokterActionPerformed
        akses.setform("DlgKasirRalan");
        pilihan = 1;
        dokter2.emptTeks();
        dokter2.isCek();
        dokter2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter2.setLocationRelativeTo(internalFrame1);
        dokter2.setVisible(true);
    }//GEN-LAST:event_btnCariDokterActionPerformed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (ChkSemua.isSelected() == true) {
            if (TNoRw.getText().trim().equals("")) {
                Valid.textKosong(TNoRw, "No.Rawat");
            } else if (kddokter.getText().trim().equals("") || TDokter.getText().trim().equals("")) {
                Valid.textKosong(kddokter, "Dokter");
            } else {
                try {
                    psCek = koneksi.prepareStatement("select no_rawat from reg_periksa where tgl_registrasi = '" + Tanggal.getText() + "' and kd_poli = '" + kdpoli.getText() + "'");
                    rsCek = psCek.executeQuery();
                    while (rsCek.next()) {
                        if (Sequel.cariRegistrasi(rsCek.getString("no_rawat")) == 0) {
                            Sequel.mengedit("reg_periksa", "no_rawat='" + rsCek.getString("no_rawat") + "'", "kd_dokter='" + kddokter.getText() + "'");
                            Sequel.mengedit("rawat_jl_dr", "no_rawat='" + rsCek.getString("no_rawat") + "'", "kd_dokter='" + kddokter.getText() + "'");
                            Sequel.mengedit("rawat_jl_drpr", "no_rawat='" + rsCek.getString("no_rawat") + "'", "kd_dokter='" + kddokter.getText() + "'");
                            Sequel.mengedit("pemeriksaan_ralan", "no_rawat='" + rsCek.getString("no_rawat") + "'", "kd_dokter='" + kddokter.getText() + "'");
                            Sequel.mengedit("catatan_resep", "no_rawat='" + rsCek.getString("no_rawat") + "'", "kd_dokter='" + kddokter.getText() + "'");
                        }
                    }
                } catch (Exception e) {
                }
                tampilkasir();
                WindowGantiDokter.dispose();
            }

        } else {
            if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi..!!");
            } else {
                if (TNoRw.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "No.Rawat");
                } else if (kddokter.getText().trim().equals("") || TDokter.getText().trim().equals("")) {
                    Valid.textKosong(kddokter, "Dokter");
                } else {
                    Valid.editTable(tabModekasir, "reg_periksa", "no_rawat", TNoRw, " kd_dokter='" + kddokter.getText() + "'");
                    Valid.editTable(tabModekasir, "rawat_jl_dr", "no_rawat", TNoRw, " kd_dokter='" + kddokter.getText() + "'");
                    Valid.editTable(tabModekasir, "rawat_jl_drpr", "no_rawat", TNoRw, " kd_dokter='" + kddokter.getText() + "'");
                    Valid.editTable(tabModekasir, "pemeriksaan_ralan", "no_rawat", TNoRw, " kd_dokter='" + kddokter.getText() + "'");
                    Valid.editTable(tabModekasir, "catatan_resep", "no_rawat", TNoRw, " kd_dokter='" + kddokter.getText() + "'");
                    tampilkasir();
                    WindowGantiDokter.dispose();
                }
            }
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowGantiDokter.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void MnSuratKeteranganNapzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratKeteranganNapzaActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            BtnCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgSuratKeteranganNapza form = new DlgSuratKeteranganNapza(null, false);
                form.isCek();
                form.emptTeks();
                form.setData(TNoRw.getText());
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnSuratKeteranganNapzaActionPerformed

    private void MnSuratKeteranganRohaniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratKeteranganRohaniActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            BtnCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgSuratKeteranganRohani form = new DlgSuratKeteranganRohani(null, false);
                form.isCek();
                form.emptTeks();
                form.setData(TNoRw.getText());
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnSuratKeteranganRohaniActionPerformed

    private void MnCekRujukanJKNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekRujukanJKNActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + NoRM.getText() + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah berpulang ke rahmatullah, semoga Husnul Khotimah...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from reg_periksa where no_rawat='" + TNoRw.getText() + "' and kd_pj='B01'") == 0) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya berlaku untuk pasien BPJS Kesehatan saja...!!!");
            tbKasirRalan.requestFocus();
        } else {
            BPJSCekKartu form = new BPJSCekKartu(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.SetNoKartu(Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis='" + NoRM.getText() + "'"));
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnCekRujukanJKNActionPerformed

    private void MnSuratKeteranganDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratKeteranganDokterActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            BtnCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgSuratKeteranganDokter form = new DlgSuratKeteranganDokter(null, false);
                form.isCek();
                form.emptTeks();
                form.setData(TNoRw.getText(), kdpoli.getText());
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnSuratKeteranganDokterActionPerformed

    private void MnAsesmenMedikObstetriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsesmenMedikObstetriActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (kdpoli.getText().equals("IGDK") || kdpoli.getText().equals("OBG")) {
                if (tbKasirRalan.getSelectedRow() != -1) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    akses.setform("DlgKasirRalan");
                    RMPenilaianAwalMedikObstetriRalan form = new RMPenilaianAwalMedikObstetriRalan(null, false);
                    form.emptTeks();
                    form.isCek();
                    form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
                    form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Menu ini hanya utk. pasien yg. terdaftar di IGD/PONEK/Poliklinik Kandungan saja...!!!");
                tbKasirRalan.requestFocus();
            }
        }
    }//GEN-LAST:event_MnAsesmenMedikObstetriActionPerformed

    private void MnInputDataTriaseIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputDataTriaseIGDActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("IGDK")) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pasien yang dirawat di IGD saja...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                if (akses.getadmin() == true || Sequel.cariInteger("select count(-1) from riwayat_akses_rekam_medis where "
                        + "no_rawat='" + TNoRw.getText() + "' and status_akses='terbuka' and dokumen_rme='ralan'") > 0) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    akses.setform("DlgKasirRalan");
                    RMTriaseIGD form = new RMTriaseIGD(null, false);
                    form.isCek();
                    form.emptTeks();
                    form.setNoRm(TNoRw.getText());
                    form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                } else {
                    if ((Sequel.cariInteger("select count(-1) from triase_igd where no_rawat = '" + TNoRw.getText() + "'") == 0)
                            || (Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tgl_jam_pindah,Interval 24 DAY_HOUR)") == 1)
                            || (Sequel.cariInteger("select count(-1) from triase_igd where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tanggal,Interval 24 DAY_HOUR)") == 1)) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        akses.setform("DlgKasirRalan");
                        RMTriaseIGD form = new RMTriaseIGD(null, false);
                        form.isCek();
                        form.emptTeks();
                        form.setNoRm(TNoRw.getText());
                        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        form.setLocationRelativeTo(internalFrame1);
                        form.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Sudah Lewat Dari 24 Jam, akses rekam medis sudah tertutup !!!");
                        tbKasirRalan.requestFocus();
                    }
                }
            }
        }
    }//GEN-LAST:event_MnInputDataTriaseIGDActionPerformed

    private void MnLihatDataTriaseIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatDataTriaseIGDActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from triase_igd where no_rawat='" + TNoRw.getText() + "'") > 0) {
                cetakDataTriase();
            } else {
                JOptionPane.showMessageDialog(null, "Data triase IGD tidak ditemukan...!!!");
                tbKasirRalan.requestFocus();
            }
        }
    }//GEN-LAST:event_MnLihatDataTriaseIGDActionPerformed

    private void MnInputDataAssesmenMedikIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputDataAssesmenMedikIGDActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("IGDK")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar di IGD saja...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                if (akses.getadmin() == true || Sequel.cariInteger("select count(-1) from riwayat_akses_rekam_medis where "
                        + "no_rawat='" + TNoRw.getText() + "' and status_akses='terbuka' and dokumen_rme='ralan'") > 0) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    akses.setform("DlgKasirRalan");
                    RMPenilaianAwalMedikIGD form = new RMPenilaianAwalMedikIGD(null, false);
                    form.isCek();
                    form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 6).toString());
                    form.emptTeks();
                    form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                } else {
                    if ((Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat = '" + TNoRw.getText() + "'") == 0)
                            || (Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tgl_jam_pindah,Interval 24 DAY_HOUR)") == 1)
                            || (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tanggal,Interval 24 DAY_HOUR)") == 1)) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        akses.setform("DlgKasirRalan");
                        RMPenilaianAwalMedikIGD form = new RMPenilaianAwalMedikIGD(null, false);
                        form.isCek();
                        form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 6).toString());
                        form.emptTeks();
                        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        form.setLocationRelativeTo(internalFrame1);
                        form.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Sudah Lewat Dari 24 Jam, akses rekam medis sudah tertutup !!!");
                        tbKasirRalan.requestFocus();
                    }
                }
            }
        }
    }//GEN-LAST:event_MnInputDataAssesmenMedikIGDActionPerformed

    private void MnLihatDataAssesmenMedikIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatDataAssesmenMedikIGDActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat='" + TNoRw.getText() + "'") > 0) {
                cetakAsesMedikIGD();
            } else {
                JOptionPane.showMessageDialog(null, "Data asesmen medik IGD tidak ditemukan...!!!");
                tbKasirRalan.requestFocus();
            }
        }
    }//GEN-LAST:event_MnLihatDataAssesmenMedikIGDActionPerformed

    private void MnInputDataAssesmenKeperawatanIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputDataAssesmenKeperawatanIGDActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("IGDK")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar di IGD saja...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                if (akses.getadmin() == true || Sequel.cariInteger("select count(-1) from riwayat_akses_rekam_medis where "
                        + "no_rawat='" + TNoRw.getText() + "' and status_akses='terbuka' and dokumen_rme='ralan'") > 0) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    akses.setform("DlgKasirRalan");
                    RMPenilaianAwalKeperawatanIGDrz form = new RMPenilaianAwalKeperawatanIGDrz(null, false);
                    form.emptTeks();
                    form.isCek();
                    form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
                    form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                } else {
                    if ((Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_igdrz where no_rawat = '" + TNoRw.getText() + "'") == 0)
                            || (Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tgl_jam_pindah,Interval 24 DAY_HOUR)") == 1)
                            || (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_igdrz where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tanggal,Interval 24 DAY_HOUR)") == 1)) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        akses.setform("DlgKasirRalan");
                        RMPenilaianAwalKeperawatanIGDrz form = new RMPenilaianAwalKeperawatanIGDrz(null, false);
                        form.emptTeks();
                        form.isCek();
                        form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
                        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        form.setLocationRelativeTo(internalFrame1);
                        form.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Sudah Lewat Dari 24 Jam, akses rekam medis sudah tertutup !!!");
                        tbKasirRalan.requestFocus();
                    }
                }
            }
        }
    }//GEN-LAST:event_MnInputDataAssesmenKeperawatanIGDActionPerformed

    private void MnLihatDataAssesmenKeperawatanIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatDataAssesmenKeperawatanIGDActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_igdrz where no_rawat='" + TNoRw.getText() + "'") > 0) {
                cetakAsesKepIGD();
            } else {
                JOptionPane.showMessageDialog(null, "Data asesmen keperawatan IGD tidak ditemukan...!!!");
                tbKasirRalan.requestFocus();
            }
        }
    }//GEN-LAST:event_MnLihatDataAssesmenKeperawatanIGDActionPerformed

    private void MnInputDataTransferSerahTerimaIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputDataTransferSerahTerimaIGDActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("IGDK")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar di IGD saja...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                if (akses.getadmin() == true || Sequel.cariInteger("select count(-1) from riwayat_akses_rekam_medis where "
                        + "no_rawat='" + TNoRw.getText() + "' and status_akses='terbuka' and dokumen_rme='ralan'") > 0) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    akses.setform("DlgKasirRalan");
                    RMTransferSerahTerimaIGD form = new RMTransferSerahTerimaIGD(null, false);;
                    form.emptTeks();
                    form.isCek();
                    form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 6).toString(), 
                            kdpoli.getText(), Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
                    form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                } else {
                    if ((Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat = '" + TNoRw.getText() + "'") == 0)
                            || (Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat = '" + TNoRw.getText() + "' "
                                    + "and now() <= DATE_ADD(tgl_jam_pindah,Interval 24 DAY_HOUR)") == 1)) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        akses.setform("DlgKasirRalan");
                        RMTransferSerahTerimaIGD form = new RMTransferSerahTerimaIGD(null, false);;
                        form.emptTeks();
                        form.isCek();
                        form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 6).toString(),
                                kdpoli.getText(), Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
                        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        form.setLocationRelativeTo(internalFrame1);
                        form.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Sudah Lewat Dari 24 Jam, akses rekam medis sudah tertutup !!!");
                        tbKasirRalan.requestFocus();
                    }
                }
            }
        }
    }//GEN-LAST:event_MnInputDataTransferSerahTerimaIGDActionPerformed

    private void MnLihatDataTransferSerahTerimaIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatDataTransferSerahTerimaIGDActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat='" + TNoRw.getText() + "' and status='Ralan'") > 0) {
                cetakTransferSerahTerimaIGD();
            } else {
                JOptionPane.showMessageDialog(null, "Data transfer & serah terima pasien rawat jalan/IGD tidak ditemukan...!!!");
                tbKasirRalan.requestFocus();
            }
        }
    }//GEN-LAST:event_MnLihatDataTransferSerahTerimaIGDActionPerformed

    private void MnInputDataAsesmenMedikObstetriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputDataAsesmenMedikObstetriActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (kdpoli.getText().equals("IGDK") || kdpoli.getText().equals("OBG")) {
                if (tbKasirRalan.getSelectedRow() != -1) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    akses.setform("DlgKasirRalan");
                    RMPenilaianAwalMedikObstetriRalan form = new RMPenilaianAwalMedikObstetriRalan(null, false);
                    form.emptTeks();
                    form.isCek();
                    form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
                    form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Menu ini hanya utk. pasien yg. terdaftar di IGD/PONEK/Poliklinik Kandungan saja...!!!");
                tbKasirRalan.requestFocus();
            }
        }
    }//GEN-LAST:event_MnInputDataAsesmenMedikObstetriActionPerformed

    private void MnLihatDataAsesmenMedikObstetriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatDataAsesmenMedikObstetriActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRw.getText() + "'") > 0) {
                cetakAsesMedikObs();
            } else {
                JOptionPane.showMessageDialog(null, "Data asesmen medik obstetri pasien IGD tidak ditemukan...!!!");
                tbKasirRalan.requestFocus();
            }
        }
    }//GEN-LAST:event_MnLihatDataAsesmenMedikObstetriActionPerformed

    private void MnCatatanResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCatatanResepActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
            TNoRw.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgKasirRalan");
            DlgCatatanResep form = new DlgCatatanResep(null, false);
            form.isCek();
            form.setData(TNoRw.getText(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 6).toString());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCatatanResepActionPerformed

    private void MnPemberianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemberianObatActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("IGDK")) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pasien yang dirawat di IGD saja...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPemberianObatPasien beriObat = new DlgPemberianObatPasien(null, false);
                akses.setform("DlgKasirRalan");
                beriObat.emptTeks();
                beriObat.isCek();
                beriObat.setData(TNoRw.getText(), NoRM.getText(), nmPasien.getText(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 6).toString(),
                        Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
                beriObat.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                beriObat.setLocationRelativeTo(internalFrame1);
                beriObat.setAlwaysOnTop(false);
                beriObat.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPemberianObatActionPerformed

    private void MnInputDataCPPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputDataCPPTActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("IGDK")) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pasien yang dirawat di IGD saja...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgKasirRalan");
                DlgCPPT form = new DlgCPPT(null, false);
                form.emptTeks();
                form.isCek();
                form.setData(TNoRw.getText(), NoRM.getText(), nmPasien.getText(),
                        tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 6).toString(), "IGD");
                form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnInputDataCPPTActionPerformed

    private void MnLihatDataCPPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatDataCPPTActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + TNoRw.getText() + "' and STATUS='Ralan'") > 0) {
                cetakCPPTigd();
            } else {
                JOptionPane.showMessageDialog(null, "Data CPPT IGD tidak ditemukan...!!!");
                tbKasirRalan.requestFocus();
            }
        }
    }//GEN-LAST:event_MnLihatDataCPPTActionPerformed

    private void MnSpirometriBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSpirometriBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (kdpoli.getText().equals("PAR") || kdpoli.getText().equals("UMUM")) {
            if (Sequel.cariInteger("select count(-1) from spirometri s inner join reg_periksa r on r.no_rawat=s.no_rawat where r.no_rkm_medis='" + NoRM.getText() + "'") == 0) {
                DlgSpirometri form = new DlgSpirometri(null, false);
                akses.setform("DlgKasirRalan");
                form.setData(TNoRw.getText(), NoRM.getText(), Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                        "Ralan", kdpoli.getText());
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.ChkInput.setSelected(true);
                form.TCari.setText(TNoRw.getText());
                form.tampil();
                form.isForm();
                form.isCek();
                form.setVisible(true);
            } else {
                if (Sequel.cariInteger("select count(-1) from spirometri s inner join reg_periksa r on r.no_rawat=s.no_rawat where "
                        + "r.no_rkm_medis='" + NoRM.getText() + "' and s.tgl_habis_berlaku>=now()") > 0) {
                    x = JOptionPane.showConfirmDialog(rootPane, "Hasil pemeriksaan spirometri masih bisa dipakai untuk pengobatan,\n    "
                            + "Apakah akan dicetak lagi atau diperbaiki datanya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        DlgSpirometri form = new DlgSpirometri(null, false);
                        akses.setform("DlgKasirRalan");
                        form.setData(TNoRw.getText(), NoRM.getText(), Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                                "Ralan", kdpoli.getText());
                        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        form.setLocationRelativeTo(internalFrame1);
                        form.ChkInput.setSelected(true);
                        form.TCari.setText(TNoRw.getText());
                        form.tampil();
                        form.isForm();
                        form.isCek();
                        form.setVisible(true);
                    }
                } else {
                    DlgSpirometri form = new DlgSpirometri(null, false);
                    akses.setform("DlgKasirRalan");
                    form.setData(TNoRw.getText(), NoRM.getText(), Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                            "Ralan", kdpoli.getText());
                    form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    form.setLocationRelativeTo(internalFrame1);
                    form.ChkInput.setSelected(true);
                    form.TCari.setText(TNoRw.getText());
                    form.tampil();
                    form.isForm();
                    form.isCek();
                    form.setVisible(true);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pemeriksaan spirometri hanya utk. pasien poliklinik paru & mcu saja...!!!");
            tbKasirRalan.requestFocus();
        }
    }//GEN-LAST:event_MnSpirometriBtnPrintActionPerformed

    private void MnInputDataKebidananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputDataKebidananActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("IGDK") && !kdpoli.getText().equals("OBG")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar di IGD Ponek/Poli Kandungan saja...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariIsi("select jk from pasien where no_rkm_medis='" + NoRM.getText() + "'").equals("L")) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pasien yang berjenis kelamin perempuan saja...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                if (akses.getadmin() == true || Sequel.cariInteger("select count(-1) from riwayat_akses_rekam_medis where "
                        + "no_rawat='" + TNoRw.getText() + "' and status_akses='terbuka' and dokumen_rme='ralan'") > 0) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    akses.setform("DlgKasirRalan");
                    RMAsesmenKebidananRalan form = new RMAsesmenKebidananRalan(null, false);
                    form.emptTeks();
                    form.isCek();
                    form.setData(TNoRw.getText());
                    form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                } else {
                    if ((Sequel.cariInteger("select count(-1)  from asesmen_kebidanan_ralan where no_rawat = '" + TNoRw.getText() + "'") == 0)
                            || (Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tgl_jam_pindah,Interval 24 DAY_HOUR)") == 1)
                            || (Sequel.cariInteger("select count(-1) from asesmen_kebidanan_ralan where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tanggal,Interval 24 DAY_HOUR)") == 1)) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        akses.setform("DlgKasirRalan");
                        RMAsesmenKebidananRalan form = new RMAsesmenKebidananRalan(null, false);
                        form.emptTeks();
                        form.isCek();
                        form.setData(TNoRw.getText());
                        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        form.setLocationRelativeTo(internalFrame1);
                        form.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Sudah Lewat Dari 24 Jam, akses rekam medis sudah tertutup !!!");
                        tbKasirRalan.requestFocus();
                    }
                }
            }
        }
    }//GEN-LAST:event_MnInputDataKebidananActionPerformed

    private void MnLihatDataKebidananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatDataKebidananActionPerformed
        JOptionPane.showMessageDialog(null, "Segera tayang (Comming Soon)...!!!");
    }//GEN-LAST:event_MnLihatDataKebidananActionPerformed

    private void MnVerifCPPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnVerifCPPTActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + TNoRw.getText() + "' and STATUS='Ralan'") > 0) {
                if (akses.getadmin() == true || Sequel.cariInteger("select count(-1) from riwayat_akses_rekam_medis where "
                        + "no_rawat='" + TNoRw.getText() + "' and status_akses='terbuka' and dokumen_rme='ralan'") > 0) {
                    if (TNoRw.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Pasien belum dipilih..!!!!");
                    } else {
                        DlgVerifikasiCPPT verif = new DlgVerifikasiCPPT(null, false);
                        verif.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        verif.setLocationRelativeTo(internalFrame1);
                        verif.setData(TNoRw.getText(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 6).toString());
                        verif.setVisible(true);
                    }
                } else if (akses.getkode().equals(Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + TNoRw.getText() + "'"))) {
                    if (TNoRw.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Pasien belum dipilih..!!!!");
                    } else {
                        DlgVerifikasiCPPT verif = new DlgVerifikasiCPPT(null, false);
                        verif.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        verif.setLocationRelativeTo(internalFrame1);
                        verif.setData(TNoRw.getText(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 6).toString());
                        verif.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Verifikasi CPPT hanya dilakukan oleh DPJP pasien tersebut...!!!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data CPPT IGD tidak ditemukan...!!!");
                tbKasirRalan.requestFocus();
            }
        }
    }//GEN-LAST:event_MnVerifCPPTActionPerformed

    private void MnJadwalOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJadwalOperasiActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                DlgJadwalOperasi jadwal = new DlgJadwalOperasi(null, false);
                jadwal.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                jadwal.setLocationRelativeTo(internalFrame1);
                jadwal.emptTeks();
                jadwal.isCek();
                jadwal.setData(NoRM.getText(), TNoRw.getText());
                jadwal.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnJadwalOperasiActionPerformed

    private void MnUpdateJadwalOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUpdateJadwalOperasiActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            Sequel.mengedit("jadwal_operasi", "nomr='" + NoRM.getText() + "' and no_rawat='-'",
                    "no_rawat='" + TNoRw.getText() + "', last_update='" + Sequel.cariIsi("select now()") + "'");

            JOptionPane.showMessageDialog(null, "Proses update jadwal operasi berhasil...!!!");
        }
    }//GEN-LAST:event_MnUpdateJadwalOperasiActionPerformed

    private void ppPersetujuanTindakanBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPersetujuanTindakanBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgKasirRalan");
                RMTindakanKedokteran form = new RMTindakanKedokteran(null, false);
                form.emptTeks();
                form.isCek();
                form.setData(TNoRw.getText(), NoRM.getText(), nmPasien.getText(), "Ralan");
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_ppPersetujuanTindakanBtnPrintActionPerformed

    private void MnPetugasPemberianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPetugasPemberianObatActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("IGDK")) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pasien yang dirawat di IGD saja...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pemberian_obat where no_rawat='" + TNoRw.getText() + "' and status='Ralan'") == 0) {
            JOptionPane.showMessageDialog(null, "Data pemberian obat rawat jalan/IGD utk. pasien ini belum ada tersimpan...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPelaksanaPemberiObat pelaksana = new DlgPelaksanaPemberiObat(null, false);
                akses.setform("DlgKasirRalan");
                pelaksana.emptTeks();
                pelaksana.isCek();
                pelaksana.setData(TNoRw.getText(), NoRM.getText(), nmPasien.getText(), 
                        Sequel.cariIsi2("SELECT MIN(tgl_pemberian) from pemberian_obat where no_rawat='" + TNoRw.getText() + "' and status='Ralan' limit 1"), "Ralan");
                pelaksana.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                pelaksana.setLocationRelativeTo(internalFrame1);
                pelaksana.setAlwaysOnTop(false);
                pelaksana.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPetugasPemberianObatActionPerformed

    private void MnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnNotepadActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgKasirRalan");
            DlgNotepad form = new DlgNotepad(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(akses.getkode());
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnNotepadActionPerformed

    private void MnProtokolKemoterapiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnProtokolKemoterapiActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgKasirRalan");
            RMProtokolKemoterapi form = new RMProtokolKemoterapi(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), NoRM.getText(), nmPasien.getText(), "Ralan");
            form.emptTeks();
            form.isCek();
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnProtokolKemoterapiActionPerformed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgKasirRalan");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), nmPasien.getText(), NoRM.getText());
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void ppDokumenJangMedBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDokumenJangMedBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgKasirRalan");
                RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
                form.setData(TNoRw.getText(), NoRM.getText(), nmPasien.getText());
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_ppDokumenJangMedBtnPrintActionPerformed

    private void MnDokumenPenunjangMedisBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenPenunjangMedisBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            NoRM.requestFocus();
        } else if (nmPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgKasirRalan");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), NoRM.getText(), nmPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenPenunjangMedisBtnPrintActionPerformed

    private void TantrianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TantrianKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TantrianKeyPressed

    private void MnRencanaKontrolNonBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRencanaKontrolNonBPJSActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from reg_periksa where no_rawat='" + TNoRw.getText() + "' and kd_pj='B01'") > 0) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya berlaku untuk pasien selain BPJS Kesehatan...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + NoRM.getText() + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah berpulang ke rahmatullah, semoga Husnul Khotimah...!!!");
            tbKasirRalan.requestFocus();
        } else {
            DlgRencanaKontrolUmum form = new DlgRencanaKontrolUmum(null, false);
            form.setSize(634, 221);
            form.setLocationRelativeTo(internalFrame1);
            form.setData("Ralan", kdpoli.getText(), NoRM.getText(), TDokter.getText(), TNoRw.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnRencanaKontrolNonBPJSActionPerformed

    private void MnDibukaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDibukaActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from riwayat_akses_rekam_medis where no_rawat='" + TNoRw.getText() + "' and "
                    + "status_akses='terbuka' and dokumen_rme='ralan'") > 0) {
            JOptionPane.showMessageDialog(null, "Semua dokumen e-RM rawat jalan utk. no. rawat " + TNoRw.getText() + " aksesnya sudah dalam kondisi terbuka...!!!");
        } else {
            String nip = "";
            if (akses.getadmin() == true) {
                nip = "-";
            } else {
                nip = akses.getkode();
            }

            Sequel.menyimpan("riwayat_akses_rekam_medis", "'" + TNoRw.getText() + "','" + nip + "',"
                    + "'" + Sequel.cariIsi("select now()") + "','0000-00-00 00:00:00','ralan','terbuka',"
                    + "'" + Sequel.cariIsi("select now()") + "'");
            tampilkasir();
            empttext();
        }
    }//GEN-LAST:event_MnDibukaActionPerformed

    private void MnDitutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDitutupActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            String wktSimpan = "";
            wktSimpan = Sequel.cariIsi("select waktu_simpan from riwayat_akses_rekam_medis where "
                    + "no_rawat='" + TNoRw.getText() + "' and dokumen_rme='ralan' and status_akses='terbuka'");

            Sequel.mengedit("riwayat_akses_rekam_medis", "waktu_simpan='" + wktSimpan + "'",
                    "waktu_tutup='" + Sequel.cariIsi("select now()") + "', status_akses='tertutup'");
            tampilkasir();
            empttext();
        }
    }//GEN-LAST:event_MnDitutupActionPerformed

    private void MnLembarObservasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarObservasiActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("IGDK")) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pasien yang dirawat di IGD saja...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                if (akses.getadmin() == true || Sequel.cariInteger("select count(-1) from riwayat_akses_rekam_medis where "
                        + "no_rawat='" + TNoRw.getText() + "' and status_akses='terbuka' and dokumen_rme='ralan'") > 0) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    RMLembarObservasi obs = new RMLembarObservasi(null, false);
                    akses.setform("DlgKasirRalan");
                    obs.emptTeks();
                    obs.isCek();
                    obs.setData(TNoRw.getText(), NoRM.getText(), nmPasien.getText(), Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
                    obs.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    obs.setLocationRelativeTo(internalFrame1);
                    obs.setAlwaysOnTop(false);
                    obs.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                } else {
                    if ((Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat = '" + TNoRw.getText() + "'") == 0)
                            || (Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tgl_jam_pindah,Interval 24 DAY_HOUR)") == 1)
                            || (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tanggal,Interval 24 DAY_HOUR)") == 1)) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        RMLembarObservasi obs = new RMLembarObservasi(null, false);
                        akses.setform("DlgKasirRalan");
                        obs.emptTeks();
                        obs.isCek();
                        obs.setData(TNoRw.getText(), NoRM.getText(), nmPasien.getText(), Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
                        obs.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        obs.setLocationRelativeTo(internalFrame1);
                        obs.setAlwaysOnTop(false);
                        obs.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Sudah Lewat Dari 24 Jam, akses rekam medis sudah tertutup !!!");
                        tbKasirRalan.requestFocus();
                    }
                }
            }
        }
    }//GEN-LAST:event_MnLembarObservasiActionPerformed

    private void MnSuratKeteranganDisabilitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratKeteranganDisabilitasActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            BtnCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgSuratKeteranganDisabilitas form = new DlgSuratKeteranganDisabilitas(null, false);
                form.isCek();
                form.emptTeks();
                form.setData(TNoRw.getText());
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);                
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnSuratKeteranganDisabilitasActionPerformed

    private void MnPiutangPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPiutangPasienActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (NoRM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                if (Sequel.cariInteger("select count(-1) from piutang_pasien where no_rkm_medis='" + NoRM.getText() + "' and status='Belum Lunas'") == 0) {
                    tglPiutang.setDate(new Date());
                } else {
                    Sequel.cariIsi("select tgl_piutang from piutang_pasien where no_rkm_medis='" + NoRM.getText() + "' "
                            + "and status='Belum Lunas' order by tgl_piutang asc limit 1", tglPiutang);
                }
                
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgKasirRalan");
                DlgLhtPiutang piutang = new DlgLhtPiutang(null, false);
                piutang.setNoRm(NoRM.getText(), tglPiutang.getDate());
                piutang.tampil();
                piutang.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                piutang.setLocationRelativeTo(internalFrame1);
                piutang.setVisible(true);
                BtnCariActionPerformed(null);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPiutangPasienActionPerformed

    private void MrkChampionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkChampionActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (NoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap7.jasper", "report", "::[ Label Pasien (4,9 x 1,8 Cm) Merek CHAMPION ]::",
                    "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                    + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + NoRM.getText() + "'");
        }
    }//GEN-LAST:event_MrkChampionActionPerformed

    private void MrkAjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkAjpActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (NoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap6.jasper", "report", "::[ Label Pasien (4,9 x 1,8 Cm) Merek AJP BRAND ]::",
                    "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                    + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + NoRM.getText() + "'");
        }
    }//GEN-LAST:event_MrkAjpActionPerformed

    private void MrkCoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkCoxActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (NoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap5.jasper", "report", "::[ Label Pasien (4,9 x 1,8 Cm) Merek COX ]::",
                    "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                    + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + NoRM.getText() + "'");
        }
    }//GEN-LAST:event_MrkCoxActionPerformed

    private void MrkCoxObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkCoxObatActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (NoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap5Obat.jasper", "report", "::[ Label Pasien (4,9 x 1,8 Cm) Merek COX (Farmasi) ]::",
                    "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                    + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + NoRM.getText() + "'");
        }
    }//GEN-LAST:event_MrkCoxObatActionPerformed

    private void MrkAlfaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkAlfaActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (NoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap2.jasper", "report", "::[ Label Pasien (4,9 x 1,9 Cm) Merek ALFA PREMIUM ]::",
                    "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                    + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + NoRM.getText() + "'");
        }
    }//GEN-LAST:event_MrkAlfaActionPerformed

    private void MrkOleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkOleanActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (NoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap3.jasper", "report", "::[ Label Pasien (4,9 x 1,9 Cm) Merek OLEAN CITY BRAND ]::",
                    "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                    + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + NoRM.getText() + "'");
        }
    }//GEN-LAST:event_MrkOleanActionPerformed

    private void MrkKojicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkKojicoActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (NoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap4.jasper", "report", "::[ Label Pasien (4,9 x 1,9 Cm) Merek KOJICO BRAND ]::",
                    "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                    + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + NoRM.getText() + "'");
        }
    }//GEN-LAST:event_MrkKojicoActionPerformed

    private void MnLabelPxRanap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelPxRanap1ActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (NoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap.jasper", "report", "::[ Label Pasien (3,9 x 1,9 Cm) ]::",
                    "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                    + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + NoRM.getText() + "'");
        }
    }//GEN-LAST:event_MnLabelPxRanap1ActionPerformed

    private void MnLabelPxRanap2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelPxRanap2ActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (NoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap1.jasper", "report", "::[ Label Pasien (6,4 x 3,2 Cm) ]::",
                    "select p.no_rkm_medis, concat(p.nm_pasien,' (',if(p.jk='L','LK','PR'),')') nm_pasien, date_format(p.tgl_lahir,'%d %M %Y') tgl_lhr, "
                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat from pasien p "
                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab where p.no_rkm_medis='" + NoRM.getText() + "'");
        }
    }//GEN-LAST:event_MnLabelPxRanap2ActionPerformed

    private void MnCetakPemeriksaanTHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakPemeriksaanTHTActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from pemeriksaan_tht where no_rawat='" + TNoRw.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Hasil pemeriksaan tindakan dipoli THT tidak ditemukan..!!");
            } else {
                this.setCursor(Cursor.getDefaultCursor());
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("tglPemeriksaan", Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_periksa from pemeriksaan_tht where no_rawat='" + TNoRw.getText() + "'")));

                Valid.MyReport("rptHasilPemeriksaanTHT.jasper", "report", "::[ Hasil Pemeriksaan Tindakan THT ]::",
                        "SELECT p.no_rkm_medis, p.nm_pasien, concat(p.alamat,', Kel. ',kl.nm_kel,', Kec.',kc.nm_kec,', Kab./Kota ',kb.nm_kab) almt, "
                        + "pt.nama_pemeriksaan, ifnull(rm.perujuk,'-') perujuk, pt.hasil_pemeriksaan, pg.nama nmdokter FROM pemeriksaan_tht pt "
                        + "inner join reg_periksa rp on rp.no_rawat=pt.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join pegawai pg on pg.nik=pt.nip_dokter inner join kelurahan kl on kl.kd_kel=p.kd_kel "
                        + "inner join kecamatan kc on kc.kd_kec=p.kd_kec inner join kabupaten kb on kb.kd_kab=p.kd_kab "
                        + "left join rujuk_masuk rm on rm.no_rawat=pt.no_rawat where pt.no_rawat='" + TNoRw.getText() + "'", param);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnCetakPemeriksaanTHTActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgKasirRalan dialog = new DlgKasirRalan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn3;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnCloseIn7;
    private widget.Button BtnCloseIn8;
    private widget.Button BtnDokter;
    private widget.Button BtnDokterRujuk;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar5;
    private widget.Button BtnLewati;
    private widget.Button BtnPrint2;
    private widget.Button BtnPxBooking;
    private widget.Button BtnRM;
    private widget.Button BtnSeek3;
    private widget.Button BtnSeek4;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan4;
    private widget.Button BtnSimpan5;
    private widget.Button BtnSimpan6;
    private widget.Button BtnSimpan7;
    private widget.Button BtnSimpanRujuk;
    public widget.CekBox ChkAutoRefres;
    public widget.CekBox ChkSemua;
    private widget.TextBox CrPoli;
    private widget.TextBox CrPtg;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private javax.swing.JDialog DlgNoSEP;
    private javax.swing.JDialog DlgRegPoliTBDOT;
    private widget.TextBox Jam;
    private widget.TextBox Kd2;
    private widget.TextBox KdDokter;
    private widget.Label LCount;
    private javax.swing.JMenu MnAksesRM;
    private javax.swing.JMenu MnAsesmenKebidanan;
    private javax.swing.JMenuItem MnAsesmenMedikObstetri;
    private javax.swing.JMenu MnAsesmenMedikObstetriIGD;
    private javax.swing.JMenu MnAssesmenKeperawatanIGD;
    private javax.swing.JMenu MnAssesmenMedikIGD;
    private javax.swing.JMenuItem MnBatal;
    private javax.swing.JMenuItem MnBelum;
    private javax.swing.JMenuItem MnBilling;
    private javax.swing.JMenu MnCPPTIGD;
    private javax.swing.JMenuItem MnCariPermintaanLab;
    private javax.swing.JMenuItem MnCariPermintaanRad;
    private javax.swing.JMenuItem MnCatatanResep;
    private javax.swing.JMenuItem MnCekRujukanJKN;
    private javax.swing.JMenuItem MnCetakPemeriksaanTHT;
    private javax.swing.JMenuItem MnDataHAIs;
    private javax.swing.JMenuItem MnDataKanker;
    private javax.swing.JMenuItem MnDataPemberianObat;
    private javax.swing.JMenuItem MnDataRalan;
    private javax.swing.JMenu MnDataTriaseIGD;
    private javax.swing.JMenuItem MnDiagnosa;
    private javax.swing.JMenuItem MnDibuka;
    private javax.swing.JMenuItem MnDiet;
    private javax.swing.JMenuItem MnDietMakanan;
    private javax.swing.JMenuItem MnDitutup;
    private javax.swing.JMenuItem MnDokter;
    private javax.swing.JMenuItem MnDokumenPenunjangMedis;
    private javax.swing.JMenu MnEklaimINACBG;
    private javax.swing.JMenuItem MnFollowUPPerawatanTerapiHIV;
    private javax.swing.JMenuItem MnFormulirKlaim;
    private javax.swing.JMenu MnGanti;
    private javax.swing.JMenuItem MnHapusData;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private javax.swing.JMenuItem MnIkhtisarPerawatanHIV;
    private javax.swing.JMenu MnInputData;
    private javax.swing.JMenuItem MnInputDataAsesmenMedikObstetri;
    private javax.swing.JMenuItem MnInputDataAssesmenKeperawatanIGD;
    private javax.swing.JMenuItem MnInputDataAssesmenMedikIGD;
    private javax.swing.JMenuItem MnInputDataCPPT;
    private javax.swing.JMenuItem MnInputDataKebidanan;
    private javax.swing.JMenuItem MnInputDataTransferSerahTerimaIGD;
    private javax.swing.JMenuItem MnInputDataTriaseIGD;
    private javax.swing.JMenuItem MnJadwalOperasi;
    private javax.swing.JMenuItem MnKamarInap;
    private javax.swing.JMenu MnKemenkes;
    private javax.swing.JMenuItem MnKlaimCOVID;
    private javax.swing.JMenuItem MnKlaimJKN;
    private javax.swing.JMenuItem MnKlaimKIPI;
    private javax.swing.JMenu MnLabel;
    private javax.swing.JMenuItem MnLabelPxRanap1;
    private javax.swing.JMenuItem MnLabelPxRanap2;
    private javax.swing.JMenuItem MnLembarObservasi;
    private javax.swing.JMenuItem MnLembarStatusPasien;
    private javax.swing.JMenuItem MnLihatDataAsesmenMedikObstetri;
    private javax.swing.JMenuItem MnLihatDataAssesmenKeperawatanIGD;
    private javax.swing.JMenuItem MnLihatDataAssesmenMedikIGD;
    private javax.swing.JMenuItem MnLihatDataCPPT;
    private javax.swing.JMenuItem MnLihatDataKebidanan;
    private javax.swing.JMenuItem MnLihatDataTransferSerahTerimaIGD;
    private javax.swing.JMenuItem MnLihatDataTriaseIGD;
    private javax.swing.JMenuItem MnLihatSEP;
    private javax.swing.JMenuItem MnNoResep;
    private javax.swing.JMenuItem MnNomorTB;
    private javax.swing.JMenuItem MnNotepad;
    private javax.swing.JMenu MnObatRalan;
    private javax.swing.JMenuItem MnOperasi;
    private javax.swing.JMenuItem MnPemberianObat;
    private javax.swing.JMenuItem MnPemeriksaanKlinisLabHIV;
    private javax.swing.JMenuItem MnPenilaianAwalKeperawatanKebidanan;
    private javax.swing.JMenuItem MnPenilaianAwalKeperawatanRalan;
    private javax.swing.JMenu MnPenilaianAwalMedis;
    private javax.swing.JMenuItem MnPenilaianAwalMedisRalanGeriatri;
    private javax.swing.JMenuItem MnPenilaianAwalMedisRalanMata;
    private javax.swing.JMenuItem MnPenilaianAwalMedisRalanTHT;
    private javax.swing.JMenuItem MnPenilaianTambahanGeriatri;
    private javax.swing.JMenuItem MnPenjab;
    private javax.swing.JMenuItem MnPeriksaLab;
    private javax.swing.JMenuItem MnPeriksaRadiologi;
    private javax.swing.JMenu MnPermintaan;
    private javax.swing.JMenuItem MnPermintaanLab;
    private javax.swing.JMenuItem MnPermintaanRadiologi;
    private javax.swing.JMenuItem MnPetugasPemberianObat;
    private javax.swing.JMenuItem MnPiutangPasien;
    private javax.swing.JMenuItem MnPoli;
    private javax.swing.JMenuItem MnPoliInternal;
    private javax.swing.JMenuItem MnProtokolKemoterapi;
    private javax.swing.JMenu MnRMGawatDarurat;
    private javax.swing.JMenuItem MnRegistrasiKeTBDOT;
    private javax.swing.JMenuItem MnRehabMedik;
    private javax.swing.JMenu MnRekamMedis;
    private javax.swing.JMenu MnRekap;
    private javax.swing.JMenuItem MnRekapTindakanPerbup;
    private javax.swing.JMenuItem MnRencanaKontrolManual;
    private javax.swing.JMenuItem MnRencanaKontrolNonBPJS;
    private javax.swing.JMenuItem MnResepFarmasi;
    private javax.swing.JMenuItem MnReturJual;
    private javax.swing.JMenuItem MnRujuk;
    private javax.swing.JMenu MnRujukan;
    private javax.swing.JMenuItem MnSEPBPJS;
    private javax.swing.JMenuItem MnSensusParu;
    private javax.swing.JMenuItem MnSpirometri;
    private javax.swing.JMenu MnStatus;
    private javax.swing.JMenuItem MnStatusPasienAllKunjungan;
    private javax.swing.JMenuItem MnSudah;
    private javax.swing.JMenuItem MnSuratIstirahatSakit;
    private javax.swing.JMenuItem MnSuratKeteranganDisabilitas;
    private javax.swing.JMenuItem MnSuratKeteranganDokter;
    private javax.swing.JMenuItem MnSuratKeteranganNapza;
    private javax.swing.JMenuItem MnSuratKeteranganRohani;
    private javax.swing.JMenuItem MnSuratPengantarRanap;
    private javax.swing.JMenuItem MnTerapiAntiretroviralHIV;
    private javax.swing.JMenuItem MnTeridentifikasiTB;
    private javax.swing.JMenu MnTindakanRalan;
    private javax.swing.JMenu MnTransferSerahTerimaIGD;
    private javax.swing.JMenuItem MnUpdateJadwalOperasi;
    private javax.swing.JMenuItem MnVerifCPPT;
    private javax.swing.JMenuItem MrkAjp;
    private javax.swing.JMenuItem MrkAlfa;
    private javax.swing.JMenuItem MrkChampion;
    private javax.swing.JMenuItem MrkCox;
    private javax.swing.JMenuItem MrkCoxObat;
    private javax.swing.JMenuItem MrkKojico;
    private javax.swing.JMenuItem MrkOlean;
    private widget.TextBox NmDokter;
    private widget.TextBox NoRM;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll8;
    public widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TDokter;
    private widget.TextBox TKdPny;
    private widget.TextBox TNoReg;
    private widget.TextBox TNoRw;
    private javax.swing.JTabbedPane TabPencegahanAnak;
    private javax.swing.JTabbedPane TabPencegahanDewasa;
    private javax.swing.JTabbedPane TabRawat;
    private javax.swing.JTabbedPane TabTindakanPencegahan;
    private widget.TextBox Tanggal;
    private widget.TextBox Tantrian;
    private widget.Tanggal TglKunRwt;
    private javax.swing.JDialog WindowCaraBayar;
    private javax.swing.JDialog WindowGantiDokter;
    private javax.swing.JDialog WindowGantiPoli;
    private javax.swing.JDialog WindowPasienBooking;
    private javax.swing.JDialog WindowPasienMati;
    private javax.swing.JDialog WindowRehabMedik;
    public javax.swing.JDialog WindowRiwayatKunjungan;
    private widget.TextArea anakA;
    private widget.TextArea anakB;
    private widget.Button btnBayar;
    private widget.Button btnCariDokter;
    private widget.Button btnCariPoli;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.TextBox cekKodeBoking;
    private widget.TextBox cekPasien;
    private widget.TextBox cekTerdaftar;
    private widget.ComboBox cmbRM;
    private widget.ComboBox cmbStatus;
    private widget.TextBox dataGZ;
    private widget.TextArea desMati;
    private widget.TextArea dewasaA;
    private widget.TextArea dewasaB;
    private widget.TextArea dewasaC;
    private widget.TextBox icdMati;
    private widget.Label infobpjs;
    private widget.Label infokhusus;
    private widget.Label infoumum;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame20;
    private widget.InternalFrame internalFrame21;
    private widget.InternalFrame internalFrame22;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel42;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox jamMati;
    private widget.TextBox jnsBayar;
    private widget.TextBox kdDokterRujuk;
    private widget.TextBox kdboking;
    private widget.TextBox kddokter;
    private widget.TextBox kdpenjab;
    private widget.TextBox kdpnj;
    private widget.TextBox kdpoli;
    private widget.TextBox kdpoli1;
    private widget.TextBox ketMati;
    private widget.TextBox nmDokterRujuk;
    private widget.TextBox nmPasien;
    private widget.TextBox nmpasien;
    private widget.TextBox nmpenjab;
    private widget.TextBox nmpnj;
    private widget.TextBox nmpoli;
    private widget.TextBox nmpoli1;
    private widget.TextBox nmpxMati;
    private widget.TextBox norm;
    private widget.TextBox norwBARU;
    private widget.TextBox norwBoking;
    private widget.TextBox nosep;
    private widget.PanelBiasa panelBiasa10;
    private widget.PanelBiasa panelBiasa14;
    private widget.PanelBiasa panelBiasa15;
    private widget.PanelBiasa panelBiasa5;
    private widget.PanelBiasa panelBiasa6;
    private widget.PanelBiasa panelBiasa7;
    private widget.PanelBiasa panelBiasa8;
    private widget.PanelBiasa panelBiasa9;
    private widget.panelisi panelGlass6;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.TextBox pasiendipilih;
    private javax.swing.JMenuItem ppBerkas;
    private javax.swing.JMenuItem ppCatatanPasien;
    private javax.swing.JMenuItem ppCekPaseinMati;
    private javax.swing.JMenuItem ppDokumenJangMed;
    private javax.swing.JMenuItem ppPasienCorona;
    private javax.swing.JMenuItem ppPerawatanCorona;
    private javax.swing.JMenuItem ppPersetujuanTindakan;
    private javax.swing.JMenuItem ppProgramPRB;
    private javax.swing.JMenuItem ppRekamPsikologiPerkawinan;
    private javax.swing.JMenuItem ppRekamPsikologis;
    private javax.swing.JMenuItem ppRiwayat;
    private javax.swing.JMenuItem ppSuratKontrol;
    private widget.TextBox rmMati;
    private widget.TextBox sepJkd;
    private widget.TextBox sepJkdigd;
    private widget.Table tbFaktorResiko;
    private widget.Table tbKasirRalan;
    private widget.Table tbPasienMati;
    private widget.Table tbRiwayatKunj;
    private widget.TextBox tglLahrMati;
    private widget.TextBox tglMati;
    private widget.Tanggal tglPeriksa;
    private widget.Tanggal tglPiutang;
    private widget.TextBox tglsep;
    private widget.TextBox tmptMati;
    // End of variables declaration//GEN-END:variables

    public void tampilkasir() {
        aksesRM = "";
        Valid.tabelKosong(tabModekasir);
        try {
            pskasir = koneksi.prepareStatement("SELECT rp.no_rawat, rp.kd_dokter, d.nm_dokter, rp.no_rkm_medis, concat(p.nm_pasien,' (Usia : ',CONCAT(rp.umurdaftar,' ',rp.sttsumur),', ',if(p.jk='L','Laki-laki','Perempuan'),')') nm_pasien, "
                    + "rp.stts, if(pl.kd_poli='IGDK',CONCAT(pl.nm_poli,' (',rp.status_lanjut,')'),pl.nm_poli) nm_poli, pj.png_jawab, rp.stts_daftar, IF(br.no_rawat = rp.no_rawat,'Online','-') reg_onlen, "
                    + "rp.tgl_registrasi, rp.jam_reg, rp.no_reg, IFNULL(enc.klaim_final, '-') stts_klaim, p.no_tlp, CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) almt_pasien, "
                    + "date_format(rp.tgl_registrasi,'%d-%m-%Y') tgl_reg_format, rp.kd_poli FROM reg_periksa rp INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter INNER JOIN pasien p ON rp.no_rkm_medis =p.no_rkm_medis "
                    + "INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab LEFT JOIN booking_registrasi br ON br.no_rawat = rp.no_rawat LEFT JOIN eklaim_new_claim enc ON enc.no_rawat = rp.no_rawat WHERE "
                    + "(rp.status_lanjut='Ralan' OR (rp.kd_poli='IGDK' AND rp.status_lanjut='Ranap')) and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and rp.no_reg like ? or "
                    + "(rp.status_lanjut='Ralan' OR (rp.kd_poli='IGDK' AND rp.status_lanjut='Ranap')) and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and rp.no_rawat like ? or "
                    + "(rp.status_lanjut='Ralan' OR (rp.kd_poli='IGDK' AND rp.status_lanjut='Ranap')) and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and rp.tgl_registrasi like ? or "
                    + "(rp.status_lanjut='Ralan' OR (rp.kd_poli='IGDK' AND rp.status_lanjut='Ranap')) and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and rp.kd_dokter like ? or "
                    + "(rp.status_lanjut='Ralan' OR (rp.kd_poli='IGDK' AND rp.status_lanjut='Ranap')) and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and d.nm_dokter like ? or "
                    + "(rp.status_lanjut='Ralan' OR (rp.kd_poli='IGDK' AND rp.status_lanjut='Ranap')) and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and rp.no_rkm_medis like ? or "
                    + "(rp.status_lanjut='Ralan' OR (rp.kd_poli='IGDK' AND rp.status_lanjut='Ranap')) and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and p.nm_pasien like ? or "
                    + "(rp.status_lanjut='Ralan' OR (rp.kd_poli='IGDK' AND rp.status_lanjut='Ranap')) and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and pl.nm_poli like ? or "
                    + "(rp.status_lanjut='Ralan' OR (rp.kd_poli='IGDK' AND rp.status_lanjut='Ranap')) and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and pj.png_jawab like ? or "
                    + "(rp.status_lanjut='Ralan' OR (rp.kd_poli='IGDK' AND rp.status_lanjut='Ranap')) and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) like ? or "
                    + "(rp.status_lanjut='Ralan' OR (rp.kd_poli='IGDK' AND rp.status_lanjut='Ranap')) and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and IF(br.no_rawat=rp.no_rawat,'Online','-') like ? or "
                    + "(rp.status_lanjut='Ralan' OR (rp.kd_poli='IGDK' AND rp.status_lanjut='Ranap')) and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and IFNULL(enc.klaim_final,'-') like ? "
                    + "order by rp.tgl_registrasi desc, rp.jam_reg desc");
            try {
                pskasir.setString(1, "%" + CrPoli.getText() + "%");
                pskasir.setString(2, "%" + CrPtg.getText() + "%");
                pskasir.setString(3, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(6, "%" + TCari.getText().trim() + "%");
                pskasir.setString(7, "%" + CrPoli.getText() + "%");
                pskasir.setString(8, "%" + CrPtg.getText() + "%");
                pskasir.setString(9, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(12, "%" + TCari.getText().trim() + "%");
                pskasir.setString(13, "%" + CrPoli.getText() + "%");
                pskasir.setString(14, "%" + CrPtg.getText() + "%");
                pskasir.setString(15, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(18, "%" + TCari.getText().trim() + "%");
                pskasir.setString(19, "%" + CrPoli.getText() + "%");
                pskasir.setString(20, "%" + CrPtg.getText() + "%");
                pskasir.setString(21, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(24, "%" + TCari.getText().trim() + "%");
                pskasir.setString(25, "%" + CrPoli.getText() + "%");
                pskasir.setString(26, "%" + CrPtg.getText() + "%");
                pskasir.setString(27, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(30, "%" + TCari.getText().trim() + "%");
                pskasir.setString(31, "%" + CrPoli.getText() + "%");
                pskasir.setString(32, "%" + CrPtg.getText() + "%");
                pskasir.setString(33, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(34, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(35, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(36, "%" + TCari.getText().trim() + "%");
                pskasir.setString(37, "%" + CrPoli.getText() + "%");
                pskasir.setString(38, "%" + CrPtg.getText() + "%");
                pskasir.setString(39, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(40, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(41, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(42, "%" + TCari.getText().trim() + "%");
                pskasir.setString(43, "%" + CrPoli.getText() + "%");
                pskasir.setString(44, "%" + CrPtg.getText() + "%");
                pskasir.setString(45, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(46, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(47, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(48, "%" + TCari.getText().trim() + "%");
                pskasir.setString(49, "%" + CrPoli.getText() + "%");
                pskasir.setString(50, "%" + CrPtg.getText() + "%");
                pskasir.setString(51, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(52, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(53, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(54, "%" + TCari.getText().trim() + "%");
                pskasir.setString(55, "%" + CrPoli.getText() + "%");
                pskasir.setString(56, "%" + CrPtg.getText() + "%");
                pskasir.setString(57, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(58, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(59, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(60, "%" + TCari.getText().trim() + "%");
                pskasir.setString(61, "%" + CrPoli.getText() + "%");
                pskasir.setString(62, "%" + CrPtg.getText() + "%");
                pskasir.setString(63, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(64, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(65, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(66, "%" + TCari.getText().trim() + "%");
                pskasir.setString(67, "%" + CrPoli.getText() + "%");
                pskasir.setString(68, "%" + CrPtg.getText() + "%");
                pskasir.setString(69, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(70, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(71, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(72, "%" + TCari.getText().trim() + "%");
                rskasir = pskasir.executeQuery();
                while (rskasir.next()) {
                    //cek e-RM pasien igd
                    if (rskasir.getString("nm_poli").contains("IGD") == true) {
                        if (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat = '" + rskasir.getString("no_rawat") + "'") == 0
                                || Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat = '" + rskasir.getString("no_rawat") + "' and now() <= DATE_ADD(tgl_jam_pindah,Interval 24 DAY_HOUR)") == 1
                                || Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat = '" + rskasir.getString("no_rawat") + "' and now() <= DATE_ADD(tanggal,Interval 24 DAY_HOUR)") == 1 
                                || Sequel.cariInteger("select count(-1) from riwayat_akses_rekam_medis where no_rawat='" + rskasir.getString("no_rawat") + "' and status_akses='terbuka' and dokumen_rme='ralan'") > 0) {
                            aksesRM = "(Open)";
                        } else {
                            aksesRM = "(Locked)";
                        }
                    } else {
                        aksesRM = "";
                    }
             
                    //cek pasien kemoterapi
                    if (rskasir.getString("kd_poli").equals("008")) {
                        if (Sequel.cariInteger("select count(-1) from protokol_kemoterapi where no_rkm_medis='" + rskasir.getString("no_rkm_medis") + "'") > 0) {
                            Sequel.meghapus("antrian_prioritas", "no_rawat", rskasir.getString("no_rawat"));
                            Sequel.menyimpanIgnore("antrian_prioritas", "'" + rskasir.getString("no_rawat") + "','" + Sequel.cariIsi("select now()") + "'", "Data Antrian Prioritas");
                        }
                    }
                    
                    tabModekasir.addRow(new String[]{
                        rskasir.getString("no_rawat"),
                        rskasir.getString("kd_dokter"),
                        rskasir.getString("nm_dokter"),
                        rskasir.getString("no_rkm_medis"),
                        rskasir.getString("nm_pasien"),
                        rskasir.getString("stts"),
                        rskasir.getString("nm_poli"),
                        rskasir.getString("png_jawab"),
                        rskasir.getString("stts_daftar"),
                        rskasir.getString("reg_onlen"),
                        rskasir.getString("tgl_reg_format"),
                        rskasir.getString("jam_reg"),
                        rskasir.getString("no_reg"),
                        rskasir.getString("stts_klaim") + " " + aksesRM,
                        rskasir.getString("no_tlp"),
                        rskasir.getString("almt_pasien"),
                        Sequel.cariIsi("select ifnull(no_rawat,'') from penilaian_awal_medis_igd where no_rawat='" + rskasir.getString("no_rawat") + "'"),
                        Sequel.cariIsi("select ifnull(no_rawat,'') from pemeriksaan_ralan where no_rawat='" + rskasir.getString("no_rawat") + "'"),
                        Sequel.cariIsi("select count(-1) from antrian_prioritas where no_rawat='" + rskasir.getString("no_rawat") + "'"),
                        rskasir.getString("tgl_registrasi"),
                        rskasir.getString("kd_poli"),
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rskasir != null) {
                    rskasir.close();
                }
                if (pskasir != null) {
                    pskasir.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabModekasir.getRowCount());
    }

    private void getDatakasir() {
        nik = "";
        if (tbKasirRalan.getSelectedRow() != -1) {
            TNoRw.setText(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 0).toString());
            kdpoli.setText(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 20).toString());
            Tanggal.setText(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 19).toString());
            Jam.setText(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 11).toString());
            NoRM.setText(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString());
            nmPasien.setText(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 4).toString());
            kddokter.setText(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString());
            TDokter.setText(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 2).toString());
            nik = Sequel.cariIsi("select no_ktp from pasien where no_rkm_medis='" + NoRM.getText() + "'");

            Sequel.cariIsi("select kd_booking from booking_registrasi where "
                    + "tanggal_periksa='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "' and "
                    + "no_rkm_medis=?", kdboking, NoRM.getText());

            sepJkd.setText(Sequel.cariIsi("SELECT bridging_jamkesda.no_sep FROM reg_periksa "
                    + "INNER JOIN bridging_jamkesda ON reg_periksa.no_rawat = bridging_jamkesda.no_rawat "
                    + "INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + "WHERE bridging_jamkesda.no_rawat='" + TNoRw.getText() + "' AND bridging_jamkesda.jns_rawat='Jalan' AND penjab.png_jawab like '%jamkesda%'"));
            sepJkdigd.setText(Sequel.cariIsi("SELECT bridging_jamkesda.no_sep FROM reg_periksa "
                    + "INNER JOIN bridging_jamkesda ON reg_periksa.no_rawat = bridging_jamkesda.no_rawat "
                    + "INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + "WHERE bridging_jamkesda.no_rawat='" + TNoRw.getText() + "' AND bridging_jamkesda.jns_rawat='Jalan IGD' AND penjab.png_jawab like '%jamkesda%'"));
        }        
    }

    public JTextField getTextField() {
        return TNoRw;
    }

    public JButton getButton() {
        return BtnKeluar;
    }

    public void isCek() {
        MnKamarInap.setEnabled(akses.getkamar_inap());
        MnPeriksaLab.setEnabled(akses.getperiksa_lab());
        MnResepFarmasi.setEnabled(akses.getresep_dokter());
        MnPeriksaRadiologi.setEnabled(akses.getperiksa_radiologi());
        MnOperasi.setEnabled(akses.getoperasi());
        MnJadwalOperasi.setEnabled(akses.getoperasi());
        MnUpdateJadwalOperasi.setEnabled(akses.getoperasi());
        MnNoResep.setEnabled(akses.getresep_obat());
        MnReturJual.setEnabled(akses.getresep_obat());
        //MnBilling.setEnabled(akses.getbilling_ralan());
        MnSudah.setEnabled(akses.getkasir_ralan());
        MnBelum.setEnabled(akses.getkasir_ralan());
        MnDataRalan.setEnabled(akses.gettindakan_ralan());
        MnDataPemberianObat.setEnabled(akses.getberi_obat());
        MnDokter.setEnabled(akses.getkasir_ralan());
        MnPenjab.setEnabled(akses.getkasir_ralan());
        MnDiagnosa.setEnabled(akses.getdiagnosa_pasien());
        ppRiwayat.setEnabled(akses.getresume_pasien());
        MnRujuk.setEnabled(akses.getrujukan_keluar());
        MnPoliInternal.setEnabled(akses.getrujukan_poli_internal());
        MnDiet.setEnabled(akses.getdiet_pasien());
        MnDataHAIs.setEnabled(akses.getdata_HAIs());
        MnPenilaianAwalKeperawatanRalan.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        MnPenilaianAwalKeperawatanKebidanan.setEnabled(akses.getpenilaian_awal_keperawatan_kebidanan());
        MnPenilaianAwalMedisRalanTHT.setEnabled(akses.getpenilaian_awal_medis_ralan_tht());
        MnPenilaianTambahanGeriatri.setEnabled(akses.getpenilaian_pasien_geriatri());
        MnPenilaianAwalMedisRalanGeriatri.setEnabled(akses.getpenilaian_pasien_geriatri());
        MnPenilaianAwalMedisRalanMata.setEnabled(akses.getpenilaian_awal_medis_ralan_mata());
        MnStatusPasienAllKunjungan.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        MnInputDataTriaseIGD.setEnabled(akses.getdata_triase_igd());
        MnPermintaanLab.setEnabled(akses.getpermintaan_lab());
        MnCariPermintaanLab.setEnabled(akses.getpermintaan_lab());
        MnPermintaanRadiologi.setEnabled(akses.getpermintaan_radiologi());
        MnCariPermintaanRad.setEnabled(akses.getpermintaan_radiologi());
        ppPasienCorona.setEnabled(akses.getpasien_corona());
        ppPerawatanCorona.setEnabled(akses.getpasien_corona());
        ppSuratKontrol.setEnabled(akses.getRencanaKontrolJKN());
        MnEklaimINACBG.setEnabled(akses.getinacbg_klaim_raza());
        MnSEPBPJS.setEnabled(akses.getinacbg_klaim_raza());
        ChkAutoRefres.setEnabled(akses.getrujukan_poli_internal());
        ChkAutoRefres.setSelected(false);
        MnTeridentifikasiTB.setEnabled(akses.getkemenkes_sitt());
        MnRencanaKontrolManual.setEnabled(akses.getRencanaKontrolJKN());
        MnIkhtisarPerawatanHIV.setEnabled(akses.getikhtisar_perawatan_hiv());
        MnPemeriksaanKlinisLabHIV.setEnabled(akses.getikhtisar_perawatan_hiv());
        MnTerapiAntiretroviralHIV.setEnabled(akses.getikhtisar_perawatan_hiv());
        MnDataKanker.setEnabled(akses.getkemenkes_kanker());
        ppRekamPsikologis.setEnabled(akses.getrekam_psikologis());
        ppRekamPsikologiPerkawinan.setEnabled(akses.getrekam_psikologis());
        MnStatus.setEnabled(akses.getsetstatusralan());
        MnSudah.setEnabled(akses.getsetstatusralan());
        MnSuratKeteranganNapza.setEnabled(akses.getsurat_keterangan_kir_mcu());
        MnSuratKeteranganRohani.setEnabled(akses.getsurat_keterangan_kir_mcu());
        MnSuratKeteranganDokter.setEnabled(akses.getsurat_keterangan_kir_mcu());
        MnSuratKeteranganDisabilitas.setEnabled(akses.getsurat_keterangan_kir_mcu());
        MnCekRujukanJKN.setEnabled(akses.getbpjs_cek_kartu());
        MnAsesmenMedikObstetri.setEnabled(akses.getpenilaian_awal_medis_ralan_kebidanan());
        MnInputDataTransferSerahTerimaIGD.setEnabled(akses.getpemberian_obat());
        MnPemberianObat.setEnabled(akses.getpemberian_obat());
        MnPetugasPemberianObat.setEnabled(akses.getpemberian_obat());
        MnCPPTIGD.setEnabled(akses.getcppt());
        MnVerifCPPT.setEnabled(akses.getcppt());
        MnInputDataAssesmenMedikIGD.setEnabled(akses.getresep_dokter());
        MnInputDataAssesmenKeperawatanIGD.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        MnInputDataKebidanan.setEnabled(akses.getpenilaian_awal_keperawatan_kebidanan());
        MnInputDataAsesmenMedikObstetri.setEnabled(akses.getresep_dokter());
        ppPersetujuanTindakan.setEnabled(akses.getcppt());
        MnProtokolKemoterapi.setEnabled(akses.getkemoterapi());
        MnAksesRM.setEnabled(akses.getadmin());
        MnLembarObservasi.setEnabled(akses.getcppt());
        MnPiutangPasien.setEnabled(akses.getbilling_ralan());

        if (akses.getbpjs_sep() == true || akses.getberi_obat() == true || akses.getadmin() == true) {
            ppProgramPRB.setEnabled(true);
        } else {
            ppProgramPRB.setEnabled(false);
        }

        if (akses.getadmin() == true) {
            MnHapusData.setEnabled(true);
            ppBerkas.setEnabled(true);
            MnBelum.setEnabled(true);
            MnBatal.setEnabled(true);
        } else {
            MnHapusData.setEnabled(false);
            ppBerkas.setEnabled(false);
            MnBelum.setEnabled(false);
            MnBatal.setEnabled(false);
        }

        if (!namadokter.equals("")) {
            if (akses.getadmin() == true) {
                CrPtg.setText("");
                BtnSeek3.setEnabled(true);
                CrPtg.setEditable(true);
            } else {
                CrPtg.setText(namadokter);
                BtnSeek3.setEnabled(false);
                CrPtg.setEditable(false);
            }
        } else {
            BtnSeek3.setEnabled(true);
            CrPtg.setEditable(true);
        }

        if (!namapoli.equals("")) {
            if (akses.getadmin() == true) {
                CrPoli.setText("");
                BtnSeek4.setEnabled(true);
                CrPoli.setEditable(true);
            } else {
                CrPoli.setText(namapoli);
                BtnSeek4.setEnabled(false);
                CrPoli.setEditable(false);
            }
        } else {
            BtnSeek4.setEnabled(true);
            CrPoli.setEditable(true);
        }
    }

    private void otomatisRalan() {
        if (Sequel.cariRegistrasi(TNoRw.getText()) == 0) {
            try {
                psotomatis = koneksi.prepareStatement(sqlpsotomatis);
                try {
                    psotomatis.setString(1, tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString());
                    psotomatis.setString(2, Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", TNoRw.getText()));
                    rskasir = psotomatis.executeQuery();
                    while (rskasir.next()) {
                        if (Sequel.cariIsiAngka("select count(no_rawat) from rawat_jl_dr where "
                                + "no_rawat='" + TNoRw.getText() + "' and kd_jenis_prw='" + rskasir.getString(1) + "' "
                                + "and kd_dokter='" + tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString() + "'") == 0) {
                            psotomatis2 = koneksi.prepareStatement(sqlpsotomatis2);
                            try {
                                psotomatis2.setString(1, TNoRw.getText());
                                psotomatis2.setString(2, rskasir.getString(1));
                                psotomatis2.setString(3, tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString());
                                psotomatis2.setString(4, Sequel.cariIsi("select current_date()"));
                                psotomatis2.setString(5, Sequel.cariIsi("select current_time()"));
                                psotomatis2.setDouble(6, rskasir.getDouble("material"));
                                psotomatis2.setDouble(7, rskasir.getDouble("bhp"));
                                psotomatis2.setDouble(8, rskasir.getDouble("tarif_tindakandr"));
                                psotomatis2.setDouble(9, rskasir.getDouble("kso"));
                                psotomatis2.setDouble(10, rskasir.getDouble("menejemen"));
                                psotomatis2.setDouble(11, rskasir.getDouble("total_byrdr"));
                                psotomatis2.executeUpdate();
                            } catch (Exception e) {
                                System.out.println("proses input data " + e);
                            } finally {
                                if (psotomatis2 != null) {
                                    psotomatis2.close();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rskasir != null) {
                        rskasir.close();
                    }
                    if (psotomatis != null) {
                        psotomatis.close();
                    }
                }

                if (!akses.getadmin() == true) {
                    psotomatis = koneksi.prepareStatement(sqlpsotomatispetugas);
                    try {
                        psotomatis.setString(1, Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", TNoRw.getText()));
                        rskasir = psotomatis.executeQuery();
                        while (rskasir.next()) {
                            if (Sequel.cariIsiAngka("select count(no_rawat) from rawat_jl_pr where "
                                    + "no_rawat='" + TNoRw.getText() + "' and kd_jenis_prw='" + rskasir.getString(1) + "' "
                                    + "and nip='" + akses.getkode() + "'") == 0) {
                                psotomatis2 = koneksi.prepareStatement(sqlpsotomatis2petugas);
                                try {
                                    psotomatis2.setString(1, TNoRw.getText());
                                    psotomatis2.setString(2, rskasir.getString(1));
                                    psotomatis2.setString(3, akses.getkode());
                                    psotomatis2.setString(4, Sequel.cariIsi("select current_date()"));
                                    psotomatis2.setString(5, Sequel.cariIsi("select current_time()"));
                                    psotomatis2.setDouble(6, rskasir.getDouble("material"));
                                    psotomatis2.setDouble(7, rskasir.getDouble("bhp"));
                                    psotomatis2.setDouble(8, rskasir.getDouble("tarif_tindakanpr"));
                                    psotomatis2.setDouble(9, rskasir.getDouble("kso"));
                                    psotomatis2.setDouble(10, rskasir.getDouble("menejemen"));
                                    psotomatis2.setDouble(11, rskasir.getDouble("total_byrpr"));
                                    psotomatis2.executeUpdate();
                                } catch (Exception e) {
                                    System.out.println("proses input data " + e);
                                } finally {
                                    if (psotomatis2 != null) {
                                        psotomatis2.close();
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rskasir != null) {
                            rskasir.close();
                        }
                        if (psotomatis != null) {
                            psotomatis.close();
                        }
                    }
                }

                if (!akses.getadmin() == true) {
                    psotomatis = koneksi.prepareStatement(sqlpsotomatisdokterpetugas);
                    try {
                        psotomatis.setString(1, tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString());
                        psotomatis.setString(2, Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", TNoRw.getText()));
                        rskasir = psotomatis.executeQuery();
                        while (rskasir.next()) {
                            if (Sequel.cariIsiAngka("select count(no_rawat) from rawat_jl_drpr where "
                                    + "no_rawat='" + TNoRw.getText() + "' and kd_jenis_prw='" + rskasir.getString(1) + "' "
                                    + "and kd_dokter='" + tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString() + "'") == 0) {
                                psotomatis2 = koneksi.prepareStatement(sqlpsotomatis2dokterpetugas);
                                try {
                                    psotomatis2.setString(1, TNoRw.getText());
                                    psotomatis2.setString(2, rskasir.getString(1));
                                    psotomatis2.setString(3, tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString());
                                    psotomatis2.setString(4, akses.getkode());
                                    psotomatis2.setString(5, Sequel.cariIsi("select current_date()"));
                                    psotomatis2.setString(6, Sequel.cariIsi("select current_time()"));
                                    psotomatis2.setDouble(7, rskasir.getDouble("material"));
                                    psotomatis2.setDouble(8, rskasir.getDouble("bhp"));
                                    psotomatis2.setDouble(9, rskasir.getDouble("tarif_tindakandr"));
                                    psotomatis2.setDouble(10, rskasir.getDouble("tarif_tindakanpr"));
                                    psotomatis2.setDouble(11, rskasir.getDouble("kso"));
                                    psotomatis2.setDouble(12, rskasir.getDouble("menejemen"));
                                    psotomatis2.setDouble(13, rskasir.getDouble("total_byrdrpr"));
                                    psotomatis2.executeUpdate();
                                } catch (Exception e) {
                                    System.out.println("proses input data " + e);
                                } finally {
                                    if (psotomatis2 != null) {
                                        psotomatis2.close();
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rskasir != null) {
                            rskasir.close();
                        }
                        if (psotomatis != null) {
                            psotomatis.close();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }

    private void emptBooking() {
        kdboking.setText("");
        kdboking.requestFocus();
        norm.setText("");
        nmpasien.setText("");
        kdpoli1.setText("");
        nmpoli1.setText("");
        tglPeriksa.setDate(new Date());
        tglPeriksa.setEnabled(false);
        kdpnj.setText("");
        nmpnj.setText("");
        KdDokter.setText("");
        NmDokter.setText("");
        norwBoking.setText("");
        TNoReg.setText("");
        cekKodeBoking.setText("");
        cekTerdaftar.setText("");
        cekPasien.setText("");
        Tantrian.setText("");
    }

    private void nomorAuto() {
        switch (URUTNOREG) {
            case "poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='" + kdpoli1.getText() + "' and tgl_registrasi='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "'", "", 3, TNoReg);
                break;
            case "dokter":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + KdDokter.getText() + "' and tgl_registrasi='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "'", "", 3, TNoReg);
                break;
            case "dokter & poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + KdDokter.getText() + "' and kd_poli='" + kdpoli1.getText() + "' and tgl_registrasi='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "'", "", 3, TNoReg);
                break;
            default:
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + KdDokter.getText() + "' and tgl_registrasi='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "'", "", 3, TNoReg);
                break;
        }

        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_rawat,6),signed)),0) from reg_periksa where tgl_registrasi='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "' ", dateformat.format(tglPeriksa.getDate()) + "/", 6, norwBoking);
    }

    private void cekPasienBoking() {
        try {
            pspasienboking = koneksi.prepareStatement("select br.no_rkm_medis, p.nm_pasien, br.kd_poli, pl.nm_poli, br.tanggal_periksa, "
                    + "br.kd_pj, pj.png_jawab, br.antrian_khusus from booking_registrasi br inner join pasien p on p.no_rkm_medis=br.no_rkm_medis "
                    + "inner join poliklinik pl on pl.kd_poli=br.kd_poli inner join dokter d on d.kd_dokter=br.kd_dokter "
                    + "inner join penjab pj on pj.kd_pj=br.kd_pj where br.kd_booking=?");
            try {
                pspasienboking.setString(1, kdboking.getText());
                rspasienboking = pspasienboking.executeQuery();
                while (rspasienboking.next()) {
                    norm.setText(rspasienboking.getString("no_rkm_medis"));
                    nmpasien.setText(rspasienboking.getString("nm_pasien"));
                    kdpoli1.setText(rspasienboking.getString("kd_poli"));
                    nmpoli1.setText(rspasienboking.getString("nm_poli"));
                    Valid.SetTgl(tglPeriksa, rspasienboking.getString("tanggal_periksa"));
                    kdpnj.setText(rspasienboking.getString("kd_pj"));
                    nmpnj.setText(rspasienboking.getString("png_jawab"));
                    Tantrian.setText(rspasienboking.getString("antrian_khusus"));
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rspasienboking != null) {
                    rspasienboking.close();
                }

                if (pspasienboking != null) {
                    pspasienboking.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void umurPasien() {
        try {
            psumurpasien = koneksi.prepareStatement("select TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, "
                    + "(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                    + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari from pasien "
                    + "where pasien.no_rkm_medis=?");
            try {
                psumurpasien.setString(1, norm.getText());
                rsumurpasien = psumurpasien.executeQuery();
                while (rsumurpasien.next()) {
                    umur = "0";
                    sttsumur = "Th";
                    if (rsumurpasien.getInt("tahun") > 0) {
                        umur = rsumurpasien.getString("tahun");
                        sttsumur = "Th";
                    } else if (rsumurpasien.getInt("tahun") == 0) {
                        if (rsumurpasien.getInt("bulan") > 0) {
                            umur = rsumurpasien.getString("bulan");
                            sttsumur = "Bl";
                        } else if (rsumurpasien.getInt("bulan") == 0) {
                            umur = rsumurpasien.getString("hari");
                            sttsumur = "Hr";
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rsumurpasien != null) {
                    rsumurpasien.close();
                }

                if (psumurpasien != null) {
                    psumurpasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void empttext() {
        TNoRw.setText("");
        NoRM.setText("");
        Kd2.setText("");
        TKdPny.setText("");
        Tanggal.setText("");
        Jam.setText("");
        sepJkd.setText("");
        sepJkdigd.setText("");
        nmPasien.setText("");
        dataGZ.setText("");
        TNoReg.setText("");
        kdpenjab.setText("");
        nmpenjab.setText("");
        kdpoli.setText("");
        nmpoli.setText("");
        kddokter.setText("");
        TDokter.setText("");
        pasiendipilih.setText("");
        norw_dipilih = "";
        kddokter_dipilih = "";
    }

    private void formulirKlaim() {
        tglklaim = "";
        drdpjp = "";
        poli = "";
        crBayar = "";

        try {
            Sequel.queryu("delete from temporary_formulir_klaim");
            pspasien = koneksi.prepareStatement("SELECT rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, IF (p.jk = 'L','LAKI-LAKI','PEREMPUAN') jk, "
                    + "IF (rp.status_lanjut = 'Ralan','RAWAT JALAN','-') status_kunjungan, DATE_FORMAT(p.tgl_lahir,'%d') tgl_lhr, "
                    + "CONCAT(rp.umurdaftar,' ',rp.sttsumur,'.') umur, DATE_FORMAT(rp.tgl_registrasi,'%d') tgl_kunj, d.nm_dokter, pl.nm_poli, "
                    + "pj.png_jawab, IFNULL(pr.diagnosa,'-') diag_resum, IFNULL(pr.keluhan,'-') keluhan, IFNULL(pr.pemeriksaan,'-') pemeriksaan, "
                    + "IFNULL(pr.alergi,'-') alergi, IFNULL(pr.terapi,'-') terapi, IFNULL(pr.rincian_tindakan,'-') tindakan FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli "
                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj LEFT JOIN pemeriksaan_ralan pr ON rp.no_rawat = pr.no_rawat "
                    + "WHERE rp.status_lanjut = 'Ralan' AND rp.no_rawat ='" + TNoRw.getText().trim() + "'");

            try {
                rspasien = pspasien.executeQuery();
                while (rspasien.next()) {
                    tglklaim = rspasien.getString("tgl_kunj") + " " + Sequel.bulanINDONESIA("select month(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'") + " "
                            + Sequel.cariIsi("select year(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'");
                    drdpjp = rspasien.getString("nm_dokter");
                    poli = rspasien.getString("nm_poli");
                    crBayar = rspasien.getString("png_jawab");

                    Sequel.menyimpan("temporary_formulir_klaim", "'Kode RS',': 6303015','Nama RS',': RSUD Ratu Zalecha Martapura',"
                            + "'1. No. RM',': " + rspasien.getString("no_rkm_medis") + "',"
                            + "'2. Nama Pasien',': " + rspasien.getString("nm_pasien") + "',"
                            + "'3. Jenis Kelamin',': " + rspasien.getString("jk") + "',"
                            + "'4. Jenis Perawatan',': " + rspasien.getString("status_kunjungan") + "',"
                            + "'5. Tgl. Lahir',': " + rspasien.getString("tgl_lhr") + " "
                            + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + rspasien.getString("no_rkm_medis") + "'") + " "
                            + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + rspasien.getString("no_rkm_medis") + "'") + "',"
                            + "'6. Umur',': " + rspasien.getString("umur") + "',"
                            + "'7. Tgl. Kunjungan',': " + rspasien.getString("tgl_kunj") + " "
                            + Sequel.bulanINDONESIA("select month(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'") + " "
                            + Sequel.cariIsi("select year(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'") + "',"
                            + "'8. Resume Medis',':','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Keluhan',':','" + rspasien.getString("keluhan") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Pemeriksaan',':','" + rspasien.getString("pemeriksaan") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Alergi',':','" + rspasien.getString("alergi") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Terapi',':','" + rspasien.getString("terapi") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Rincian Tindakan',':','" + rspasien.getString("tindakan") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Diagnosa Resume',':','" + rspasien.getString("diag_resum") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    //ngambil data diagnosa icd 10     
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'9. Diagnosa ICD-10',':','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    psdiagnosa = koneksi.prepareStatement("SELECT IF (d.prioritas = '1','Primer','Sekunder') stts_diagnosa, IFNULL(d.kd_penyakit,'-') AS ICD_10, "
                            + "IFNULL(p.ciri_ciri, '-') deskripsi_diagnosa FROM diagnosa_pasien d "
                            + "INNER JOIN reg_periksa r ON r.no_rawat=d.no_rawat INNER JOIN penyakit p ON p.kd_penyakit=d.kd_penyakit "
                            + "WHERE d.status='ralan' and r.no_rawat='" + TNoRw.getText().trim() + "' order by d.prioritas");
                    try {
                        rsdiagnosa = psdiagnosa.executeQuery();
                        while (rsdiagnosa.next()) {
                            Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                                    + "'      " + rsdiagnosa.getString("stts_diagnosa") + "',':','" + rsdiagnosa.getString("deskripsi_diagnosa") + "','Kode : " + rsdiagnosa.getString("ICD_10") + "',"
                                    + "'','','','','','','','','','','','','','',''", "Diagnosa Klaim Rawat Jalan");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi Formulir Klaim : " + e);
                    } finally {
                        if (rsdiagnosa != null) {
                            rsdiagnosa.close();
                        }
                        if (psdiagnosa != null) {
                            psdiagnosa.close();
                        }
                    }

                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    //ngambil data tindakan icd 9
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'10. Tindakan ICD-9-CM',':','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    psprosedur = koneksi.prepareStatement(
                            "SELECT IFNULL(pp.kode,'-') ICD_9, IFNULL(i.deskripsi_panjang,'-') des_prosedur "
                            + "FROM reg_periksa rp INNER JOIN prosedur_pasien pp on pp.no_rawat=rp.no_rawat INNER JOIN icd9 i on i.kode=pp.kode "
                            + "WHERE rp.status_lanjut = 'Ralan' AND rp.no_rawat ='" + TNoRw.getText().trim() + "'");
                    try {
                        rsprosedur = psprosedur.executeQuery();
                        while (rsprosedur.next()) {
                            Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                                    + "'      Deskripsi',':','" + rsprosedur.getString("des_prosedur") + "','Kode : " + rsprosedur.getString("ICD_9") + "',"
                                    + "'','','','','','','','','','','','','','',''", "Tindakan Klaim Rawat Jalan");
                        }
                    } catch (Exception e) {
                        System.out.println("Notif Registrasi : " + e);
                    } finally {
                        if (rsprosedur != null) {
                            rsprosedur.close();
                        }
                        if (psprosedur != null) {
                            psprosedur.close();
                        }
                    }

                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                }

            } catch (Exception e) {
                System.out.println("Notifikasi Cari Pasien : " + e);
            } finally {
                if (rspasien != null) {
                    rspasien.close();
                }
                if (pspasien != null) {
                    pspasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tglKlaim", "Martapura, " + tglklaim);
        param.put("drDPJP", "( " + drdpjp + " )");
        param.put("poli", poli);
        param.put("caraBayar", crBayar);
        Valid.MyReport("rptFormulirKlaim.jasper", "report", "::[ Lembar Formulir Klaim Pasien Rawat Jalan ]::",
                "select * from temporary_formulir_klaim", param);
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void tampilMati() {
        Valid.tabelKosong(tabModeMati);
        try {
            psMati = koneksi.prepareStatement("select tanggal,jam,pasien_mati.no_rkm_medis,nm_pasien,jk,tmp_lahir,tgl_lahir,gol_darah,stts_nikah,"
                    + "agama,keterangan,temp_meninggal,icd5,unit_asal, date_format(tgl_lahir,'%d %M %Y') tgl_lhr, "
                    + "date_format(tanggal,'%d %M %Y') tgl_mati from pasien_mati,pasien where "
                    + sql + "and tanggal like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and pasien_mati.no_rkm_medis like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and nm_pasien like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and jk like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and tmp_lahir like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and gol_darah like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and stts_nikah like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and agama like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and unit_asal like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and keterangan like '%" + TCari1.getText().trim() + "%' "
                    + " order by tanggal desc limit 50");
            try {
                rsMati = psMati.executeQuery();
                while (rsMati.next()) {
                    tabModeMati.addRow(new Object[]{
                        rsMati.getString("tanggal"),
                        rsMati.getString("jam"),
                        rsMati.getString("no_rkm_medis"),
                        rsMati.getString("nm_pasien"),
                        rsMati.getString("jk"),
                        rsMati.getString("tmp_lahir"),
                        rsMati.getString("tgl_lahir"),
                        rsMati.getString("gol_darah"),
                        rsMati.getString("stts_nikah"),
                        rsMati.getString("agama"),
                        rsMati.getString("keterangan"),
                        rsMati.getString("temp_meninggal"),
                        rsMati.getString("icd5"),
                        rsMati.getString("unit_asal"),
                        rsMati.getString("tgl_lhr"),
                        rsMati.getString("tgl_mati")
                    });
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgKasirRalan.tampil() : " + e);
            } finally {
                if (rsMati != null) {
                    rsMati.close();
                }
                if (psMati != null) {
                    psMati.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void emptMati() {
        rmMati.setText("");
        nmpxMati.setText("");
        tglLahrMati.setText("");
        tglMati.setText("");
        jamMati.setText("");
        tmptMati.setText("");
        ketMati.setText("");
        icdMati.setText("");
        desMati.setText("");
        TCari1.setText("");
    }

    private void getDataMati() {
        if (tbPasienMati.getSelectedRow() != -1) {
            rmMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 2).toString());
            nmpxMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 3).toString());
            tglLahrMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 14).toString());
            tglMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 15).toString());
            jamMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 1).toString());
            tmptMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 11).toString());
            ketMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 10).toString());
            icdMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 12).toString());
            desMati.setText(Sequel.cariIsi("select ciri_ciri from penyakit where kd_penyakit='" + icdMati.getText() + "'"));
        }
    }

    public void tampilRiwayatKun(String code) {
        Valid.tabelKosong(tabModeKunjungan);
        try {
            psRiwKunj = koneksi.prepareStatement("SELECT rp.no_rawat, "
                    + "	p.no_rkm_medis, "
                    + "	date_format( rp.tgl_registrasi, '%d-%m-%Y' ) tgl_kunj, "
                    + "	p.nm_pasien, "
                    + "	d.nm_dokter, "
                    + "	pj.png_jawab, "
                    + "	rp.kd_dokter "
                    + "FROM reg_periksa rp "
                    + "	INNER JOIN dokter d ON d.kd_dokter = rp.kd_dokter "
                    + "	INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "	INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli "
                    + "	INNER JOIN penjab pj ON pj.kd_pj = rp.kd_pj "
                    + "left join pemeriksaan_ralan_petugas s on s.no_rawat = rp.no_rawat "
                    + "left join pemeriksaan_ralan n on n.no_rawat = rp.no_rawat "
                    + "left join diagnosa_pasien dp on dp.no_rawat = rp.no_rawat "
                    + "where (rp.tgl_registrasi BETWEEN (select DATE_sub(DATE_FORMAT(now(),'%Y-%m-%d'),INTERVAL 7 DAY)) and (select DATE_sub(DATE_FORMAT(now(),'%Y-%m-%d'),INTERVAL 1 DAY))) and rp.kd_poli ='" + code + "' "
                    + "and (IFNULL(s.no_rawat,'-') = '-' and IFNULL(n.no_rawat,'-') = '-' and IFNULL(dp.no_rawat,'-') = '-')");

            try {
                rsRiwKunj = psRiwKunj.executeQuery();
                z = 1;
                while (rsRiwKunj.next()) {
                    tabModeKunjungan.addRow(new Object[]{
                        z + ".",
                        rsRiwKunj.getString("no_rawat"),
                        rsRiwKunj.getString("no_rkm_medis"),
                        rsRiwKunj.getString("tgl_kunj"),
                        rsRiwKunj.getString("nm_pasien"),
                        rsRiwKunj.getString("nm_dokter"),
                        rsRiwKunj.getString("png_jawab"),
                        rsRiwKunj.getString("kd_dokter")
                    });
                    z++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgKasirRalan.tampilRiwayatKun() : " + e);
            } finally {
                if (rsRiwKunj != null) {
                    rsRiwKunj.close();
                }
                if (psRiwKunj != null) {
                    psRiwKunj.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void cekReg() {
        if ((Sequel.cariInteger("select status_erm from poliklinik where kd_poli = '" + kdpoli.getText() + "'") == 1)) {
            cekTotKun = Sequel.cariInteger("select count(1) total from reg_periksa where "
                    + "(tgl_registrasi BETWEEN (select DATE_sub(DATE_FORMAT(now(),'%Y-%m-%d'),INTERVAL 7 DAY)) and (select DATE_sub(DATE_FORMAT(now(),'%Y-%m-%d'),INTERVAL 1 DAY))) "
                    + "and kd_poli ='" + kdpoli.getText() + "'");

            if (cekTotKun > 0) {
                cekDiag = Sequel.cariInteger("select count(1) total from reg_periksa r "
                        + "left join pemeriksaan_ralan_petugas d on d.no_rawat = r.no_rawat "
                        + "left join pemeriksaan_ralan p on p.no_rawat = r.no_rawat "
                        + "left join diagnosa_pasien s on s.no_rawat = r.no_rawat "
                        + "where (r.tgl_registrasi BETWEEN (select DATE_sub(DATE_FORMAT(now(),'%Y-%m-%d'),INTERVAL 7 DAY)) and (select DATE_sub(DATE_FORMAT(now(),'%Y-%m-%d'),INTERVAL 1 DAY))) and kd_poli ='" + kdpoli.getText() + "' "
                        + "and (IFNULL(d.no_rawat,'-') = '-' and IFNULL(s.no_rawat,'-') = '-' and IFNULL(p.no_rawat,'-') = '-')");

                if (cekDiag == 0) {                    
                    akses.setform("DlgKasirRalan");
                    dlgrwjl2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    dlgrwjl2.setLocationRelativeTo(internalFrame1);
                    dlgrwjl2.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
                    dlgrwjl2.TotalNominal();
                    dlgrwjl2.setVisible(true);
                    dlgrwjl2.fokus();
                    dlgrwjl2.petugas(kddokter.getText(), akses.getkode());
                    dlgrwjl2.isCek();
                } else {
                    WindowRiwayatKunjungan.setSize(874, 361);
                    WindowRiwayatKunjungan.setLocationRelativeTo(internalFrame1);
                    pasiendipilih.setText("");
                    norw_dipilih = "";
                    kddokter_dipilih = "";
                    tampilRiwayatKun(kdpoli.getText());
                    WindowRiwayatKunjungan.setVisible(true);
                    tbRiwayatKunj.requestFocus();
                }
            } else {
                akses.setform("DlgKasirRalan");
                dlgrwjl2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                dlgrwjl2.setLocationRelativeTo(internalFrame1);
                dlgrwjl2.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
                dlgrwjl2.TotalNominal();
                dlgrwjl2.setVisible(true);
                dlgrwjl2.fokus();
                dlgrwjl2.petugas(kddokter.getText(), akses.getkode());
                dlgrwjl2.isCek();
            }
        } else {
            akses.setform("DlgKasirRalan");
            dlgrwjl2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            dlgrwjl2.setLocationRelativeTo(internalFrame1);
            dlgrwjl2.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
            dlgrwjl2.TotalNominal();
            dlgrwjl2.setVisible(true);
            dlgrwjl2.fokus();
            dlgrwjl2.petugas(kddokter.getText(), akses.getkode());
            dlgrwjl2.isCek();
        }
    }

    private void getdataRiwKunj() {
        norw_dipilih = "";
        kddokter_dipilih = "";

        if (tbRiwayatKunj.getSelectedRow() != -1) {
            norw_dipilih = tbRiwayatKunj.getValueAt(tbRiwayatKunj.getSelectedRow(), 1).toString();
            pasiendipilih.setText(tbRiwayatKunj.getValueAt(tbRiwayatKunj.getSelectedRow(), 2).toString() + " - "
                    + tbRiwayatKunj.getValueAt(tbRiwayatKunj.getSelectedRow(), 4).toString() + " (Tgl. Kunj. : "
                    + tbRiwayatKunj.getValueAt(tbRiwayatKunj.getSelectedRow(), 3).toString() + ")");
            kddokter_dipilih = tbRiwayatKunj.getValueAt(tbRiwayatKunj.getSelectedRow(), 7).toString();
            Valid.SetTgl(TglKunRwt, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw_dipilih + "'"));
        }
    }

    private void otomatisRefresh() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ChkAutoRefres.isSelected() == true) {
                    if (!DTPCari1.getSelectedItem().equals(DTPCari2.getSelectedItem())) {
                        JOptionPane.showMessageDialog(null, "Tgl. periode harus sama, jika berbeda fitur auto refresh data tdk. bisa digunakan..!!");
                        ChkAutoRefres.setSelected(false);
                    } else if (CrPoli.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Poliklinik dimaksud harus dipilih dulu..!!");
                        ChkAutoRefres.setSelected(false);
                        BtnSeek4.requestFocus();
                    } else {
                        tampilkasir();
                    }
                }
            }
        };
        // Timer
        //interval 1000 ms = 1 detik
        //interval 30000 ms = 30 detik atau setngah menit
        akses.tRefreshPoli = new Timer(30000, taskPerformer);
    }

    private void otomatisRefreshAntrian() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungAntrianBPJS();
                hitungAntrianUMUM();
                hitungAntrianKhusus();
            }
        };
        //Timer
        //interval 1000 ms = 1 detik
        //interval 30000 ms = 30 detik atau setengah menit
        akses.tRefreshAntrian = new Timer(60000, taskPerformer);
    }

    private void hitungAntrianBPJS() {
        panggilan = 0;
        nomorpasien = 0;
        hasilantrian = 0;

        //cek nomor antrian pasien
        if (Sequel.cariIsi("select no_antrian from antrian_nomor_bpjs where date(waktu_cetak)=date(now()) order by no_antrian desc limit 1").equals("")) {
            nomorpasien = 0;
        } else {
            nomorpasien = Integer.parseInt(Sequel.cariIsi("select no_antrian from antrian_nomor_bpjs where date(waktu_cetak)=date(now()) order by no_antrian desc limit 1"));
        }

        //cek nomor panggilan pasien
        if (Sequel.cariIsi("SELECT no_antrian FROM antrian_pemanggil_bpjs where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1").equals("")) {
            panggilan = 0;
        } else {
            panggilan = Integer.parseInt(Sequel.cariIsi("select no_antrian from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1"));
        }

        if (panggilan == 0 && nomorpasien == 0) {
            infobpjs.setText("Diloket pendaftaran pasien rawat jalan BPJS belum ada pasien atau Sisa antrian pasien sudah habis...!!!");
        } else {
            //hasil hitungan antrian
            hasilantrian = nomorpasien - panggilan;
            if (nomorpasien > panggilan) {
                infobpjs.setText("Masih ada antrian pasien rawat jalan BPJS yang belum dipanggil & dilayani oleh petugas pendaftaran.");
            }

            if (hasilantrian >= 1) {
                infobpjs.setText("Masih ada " + hasilantrian + " orang lagi yang belum dipanggil & dilayani oleh petugas pendaftaran pasien rawat jalan BPJS.");
            }

            if (hasilantrian < 1) {
                infobpjs.setText("Antrian diloket pendaftaran pasien rawat jalan BPJS sudah habis.");
            }
        }
    }

    private void hitungAntrianUMUM() {
        panggilan = 0;
        nomorpasien = 0;
        hasilantrian = 0;

        //cek nomor antrian pasien
        if (Sequel.cariIsi("select no_antrian from antrian_nomor_umum where date(waktu_cetak)=date(now()) order by no_antrian desc limit 1").equals("")) {
            nomorpasien = 0;
        } else {
            nomorpasien = Integer.parseInt(Sequel.cariIsi("select no_antrian from antrian_nomor_umum where date(waktu_cetak)=date(now()) order by no_antrian desc limit 1"));
        }

        //cek nomor panggilan pasien
        if (Sequel.cariIsi("SELECT no_antrian FROM antrian_pemanggil_umum where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1").equals("")) {
            panggilan = 0;
        } else {
            panggilan = Integer.parseInt(Sequel.cariIsi("select no_antrian from antrian_pemanggil_umum where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1"));
        }

        if (panggilan == 0 && nomorpasien == 0) {
            infoumum.setText("Diloket pendaftaran pasien rawat jalan Umum (NON BPJS) belum ada pasien atau Sisa antrian pasien sudah habis...!!!");
        } else {
            //hasil hitungan antrian
            hasilantrian = nomorpasien - panggilan;
            if (nomorpasien > panggilan) {
                infoumum.setText("Masih ada antrian pasien rawat jalan Umum (NON BPJS) yang belum dipanggil & dilayani oleh petugas pendaftaran.");
            }

            if (hasilantrian >= 1) {
                infoumum.setText("Masih ada " + hasilantrian + " orang lagi yang belum dipanggil & dilayani oleh petugas pendaftaran pasien rawat jalan Umum (NON BPJS).");
            }

            if (hasilantrian < 1) {
                infoumum.setText("Antrian diloket pendaftaran pasien rawat jalan Umum (NON BPJS) sudah habis.");
            }
        }
    }

    private void hitungAntrianKhusus() {
        panggilan = 0;
        nomorpasien = 0;
        hasilantrian = 0;

        //cek nomor antrian pasien
        if (Sequel.cariIsi("select no_antrian from antrian_nomor_lansia where date(waktu_cetak)=date(now()) order by no_antrian desc limit 1").equals("")) {
            nomorpasien = 0;
        } else {
            nomorpasien = Integer.parseInt(Sequel.cariIsi("select no_antrian from antrian_nomor_lansia where date(waktu_cetak)=date(now()) order by no_antrian desc limit 1"));
        }

        //cek nomor panggilan pasien
        if (Sequel.cariIsi("SELECT no_antrian FROM antrian_pemanggil_lansia where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1").equals("")) {
            panggilan = 0;
        } else {
            panggilan = Integer.parseInt(Sequel.cariIsi("select no_antrian from antrian_pemanggil_lansia where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1"));
        }

        if (panggilan == 0 && nomorpasien == 0) {
            infokhusus.setText("Diloket pendaftaran pasien rawat jalan Khusus/Prioritas belum ada pasien atau Sisa antrian pasien sudah habis...!!!");
        } else {
            //hasil hitungan antrian
            hasilantrian = nomorpasien - panggilan;
            if (nomorpasien > panggilan) {
                infokhusus.setText("Masih ada antrian pasien rawat jalan Khusus/Prioritas yang belum dipanggil & dilayani oleh petugas pendaftaran.");
            }

            if (hasilantrian >= 1) {
                infokhusus.setText("Masih ada " + hasilantrian + " orang lagi yang belum dipanggil & dilayani oleh petugas pendaftaran pasien rawat jalan Khusus/Prioritas.");
            }

            if (hasilantrian < 1) {
                infokhusus.setText("Antrian diloket pendaftaran pasien rawat jalan Khusus/Prioritas sudah habis.");
            }
        }
    }

    private void isCekPasien() {
        try {
            ps = koneksi.prepareStatement("select concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) asal,"
                    + "namakeluarga,keluarga,pasien.kd_pj,penjab.png_jawab,if(tgl_daftar=?,'Baru','Lama') as daftar, "
                    + "TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, "
                    + "(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                    + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari, "
                    + "pasien.suku_bangsa, pasien.bahasa_pasien from pasien inner join kelurahan inner join kecamatan "
                    + "inner join kabupaten inner join penjab on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_pj=penjab.kd_pj "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis=?");

            try {
                ps.setString(1, Tanggal.getText());
                ps.setString(2, NoRM.getText());
                rs = ps.executeQuery();
                while (rs.next()) {
                    TAlmt = rs.getString("asal");
                    TPngJwb = rs.getString("namakeluarga");
                    THbngn = rs.getString("keluarga");
                    kdpnj.setText(rs.getString("kd_pj"));
                    nmpnj.setText(rs.getString("png_jawab"));
                    TStatus = rs.getString("daftar");
                    kdsuku = rs.getString("suku_bangsa");
                    kdbahasa = rs.getString("bahasa_pasien");

                    umur = "0";
                    sttsumur = "Th";
                    cekUmur = 0;
                    if (rs.getInt("tahun") > 0) {
                        cekUmur = rs.getInt("tahun");
                        umur = rs.getString("tahun");
                        sttsumur = "Th";
                    } else if (rs.getInt("tahun") == 0) {
                        if (rs.getInt("bulan") > 0) {
                            cekUmur = rs.getInt("bulan");
                            umur = rs.getString("bulan");
                            sttsumur = "Bl";
                        } else if (rs.getInt("bulan") == 0) {
                            cekUmur = rs.getInt("hari");
                            umur = rs.getString("hari");
                            sttsumur = "Hr";
                        }
                    }
                    switch (rs.getString("daftar")) {
                        case "Baru":
                            TBiaya = Sequel.cariIsi("select registrasi from poliklinik where kd_poli='" + kdpoli.getText() + "'");
                            break;
                        case "Lama":
                            TBiaya = Sequel.cariIsi("select registrasilama from poliklinik where kd_poli='" + kdpoli.getText() + "'");
                            break;
                        default:
                            TBiaya = Sequel.cariIsi("select registrasi from poliklinik where kd_poli='" + kdpoli.getText() + "'");
                            break;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void isNumberRujuk() {
        switch (URUTNOREG) {
            case "poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='TBD' and tgl_registrasi='" + Tanggal.getText() + "'", "", 3, TNoReg);
                break;
            case "dokter":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kdDokterRujuk.getText() + "' and tgl_registrasi='" + Tanggal.getText() + "'", "", 3, TNoReg);
                break;
            case "dokter & poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kdDokterRujuk.getText() + "' and kd_poli='TBD' and tgl_registrasi='" + Tanggal.getText() + "'", "", 3, TNoReg);
                break;
            default:
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kdDokterRujuk.getText() + "' and tgl_registrasi='" + Tanggal.getText() + "'", "", 3, TNoReg);
                break;
        }
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_rawat,6),signed)),0) from reg_periksa where tgl_registrasi='" + Tanggal.getText() + "' ", Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%Y/%m/%d')") + "/", 6, norwBARU);
    }

    private void cetakDataTriase() {
        totskorTriase = 0;
        try {
            psLaprm = koneksi.prepareStatement("select * from triase_igd where no_rawat='" + TNoRw.getText() + "'");
            try {
                rsLaprm = psLaprm.executeQuery();
                while (rsLaprm.next()) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));

                    if (rsLaprm.getString("alasan_kedatangan").equals("")) {
                        param.put("alasan_kedatangan", "-");
                    } else if (rsLaprm.getString("alasan_kedatangan").equals("Datang Sendiri") || rsLaprm.getString("alasan_kedatangan").equals("Polisi")) {
                        param.put("alasan_kedatangan", rsLaprm.getString("alasan_kedatangan"));
                    } else if (rsLaprm.getString("alasan_kedatangan").equals("Rujukan, dari")) {
                        param.put("alasan_kedatangan", "Rujukan, dari : " + rsLaprm.getString("rujukan_dari"));
                    } else if (rsLaprm.getString("alasan_kedatangan").equals("Dijemput oleh")) {
                        param.put("alasan_kedatangan", "Dijemput oleh : " + rsLaprm.getString("dijemput_oleh"));
                    }

                    if (rsLaprm.getString("kendaraan").equals("")) {
                        param.put("kendaraan", "-");
                    } else if (rsLaprm.getString("kendaraan").equals("Ambulance")) {
                        param.put("kendaraan", rsLaprm.getString("kendaraan"));
                    } else if (rsLaprm.getString("kendaraan").equals("Kendaraan bukan ambulance")) {
                        param.put("kendaraan", "Kendaraan bukan ambulance, jelaskan : " + rsLaprm.getString("bukan_ambulan"));
                    }

                    if (rsLaprm.getString("kll_tunggal").equals("ya")) {
                        param.put("kll_tunggal", "KLL Tunggal Tempat Kejadian " + rsLaprm.getString("kll_tunggal_tmpt_kejadian") + " Tanggal Kejadian "
                                + Sequel.cariIsi("select date_format(kll_tunggal_tanggal,'%d-%m-%Y    Pukul : %H:%i') from triase_igd "
                                        + "where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("kll_tunggal", "KLL Tunggal");
                    }

                    if (rsLaprm.getString("kll_versus").equals("ya")) {
                        param.put("kll", "KLL " + rsLaprm.getString("versus1") + " Vs. " + rsLaprm.getString("versus2") + " Tempat Kejadian "
                                + rsLaprm.getString("kll_tmpt_kejadian") + " Tanggal Kejadian " + Sequel.cariIsi("select date_format(kll_tanggal,'%d-%m-%Y    Pukul : %H:%i') from triase_igd "
                                + "where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("kll", "KLL");
                    }

                    if (rsLaprm.getString("jatuh").equals("ya")) {
                        param.put("jatuh", "Jatuh dari ketinggian, Jelaskan : " + rsLaprm.getString("ket_jatuh"));
                    } else {
                        param.put("jatuh", "Jatuh dari ketinggian,");
                    }

                    if (rsLaprm.getString("luka_bakar").equals("ya")) {
                        param.put("luka", "Luka bakar, Jelaskan : " + rsLaprm.getString("ket_luka_bakar"));
                    } else {
                        param.put("luka", "Luka bakar,");
                    }

                    if (rsLaprm.getString("trauma_listrik").equals("ya")) {
                        param.put("trauma_listrik", "Trauma listrik, Jelaskan : " + rsLaprm.getString("ket_trauma_listrik"));
                    } else {
                        param.put("trauma_listrik", "Trauma listrik,");
                    }

                    if (rsLaprm.getString("trauma_zat_kimia").equals("ya")) {
                        param.put("trauma_zat", "Trauma zat kimia, Jelaskan : " + rsLaprm.getString("ket_trauma_zat_kimia"));
                    } else {
                        param.put("trauma_zat", "Trauma zat kimia,");
                    }

                    if (rsLaprm.getString("trauma_lain").equals("ya")) {
                        param.put("trauma_lain", "Trauma lainnya (" + rsLaprm.getString("ket_trauma_lain") + ")");
                    } else {
                        param.put("trauma_lain", "Trauma lainnya");
                    }

                    if (rsLaprm.getString("pacs1").equals("ya")) {
                        param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 1");
                    } else if (rsLaprm.getString("pacs2").equals("ya")) {
                        param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 2");
                    } else if (rsLaprm.getString("pacs3").equals("ya")) {
                        param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 3");
                    } else if (rsLaprm.getString("pacs4").equals("ya")) {
                        param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 4");
                    } else {
                        param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : -");
                    }

                    totskorTriase = Integer.parseInt(rsLaprm.getString("total_skor"));
                    if (totskorTriase >= 5) {
                        param.put("total5", "V");
                        param.put("total24", "");
                        param.put("total01", "");
                    } else if (totskorTriase >= 2 && totskorTriase <= 4) {
                        param.put("total5", "");
                        param.put("total24", "V");
                        param.put("total01", "");
                    } else if (totskorTriase >= 0 && totskorTriase <= 1) {
                        param.put("total5", "");
                        param.put("total24", "");
                        param.put("total01", "V");
                    } else {
                        param.put("total5", "");
                        param.put("total24", "");
                        param.put("total01", "");
                    }

                    Valid.MyReport("rptTriaseIGD.jasper", "report", "::[ Laporan Data Triase IGD ]::",
                            "SELECT ti.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, date_format(ti.tanggal,'Tanggal : %d-%m-%Y    Pukul : %H:%i') kontak_awal, "
                            + "if(ti.cara_masuk='','-',ti.cara_masuk) cr_msk, ti.sudah_terpasang, concat('Nama : ',ti.nm_pengantar,'    No. Telp : ',ti.telp_pengantar) iden_pengntar, "
                            + "ti.kasus, ti.keluhan_utama, if(ti.kesadaran='','KESADARAN : -',concat('KESADARAN : ',ti.kesadaran)) kesadaran, ti.td, ti.nadi, ti.napas, ti.temperatur, "
                            + "ti.saturasi, ti.nyeri, ti.vas, if(ti.skor0_sadar_penuh='ya','V','') skor0_sadar, if(ti.skor0_100='ya','V','') skor0_100, if(ti.skor0_101='ya','V','') skor0_101, "
                            + "if(ti.skor0_19='ya','V','') skor0_19, if(ti.skor0_35_3='ya','V','') skor0_35, if(ti.skor0_96_100='ya','V','') skor0_96, if(ti.skor1_102='ya','V','') skor1_102, "
                            + "if(ti.skor1_20_21='ya','V','') skor1_20, if(ti.skor1_94_95='ya','V','') skor1_94, if(ti.skor2_99='ya','V','') skor2_99, if(ti.skor2_22='ya','V','') skor2_22, "
                            + "if(ti.skor2_92_93='ya','V','') skor2_92, if(ti.skor3_selain='ya','V','') skor3_selain, if(ti.skor3_35_3='ya','V','') skor3_35, if(ti.skor3_92='ya','V','') skor3_92, "
                            + "ti.catatan, ti.pukul, if(ti.triase_resusitasi='ya','V','') resus, if(ti.triase_non_resusitasi='ya','V','') nonresus, if(ti.triase_klinik='ya','V','') klinik, "
                            + "if(ti.triase_doa='ya','V','') doa, pg.nama petgas, if(ti.kll_tunggal='ya','V','') kll_tunggal, if(ti.kll_versus='ya','V','') kll_versus, if(ti.jatuh='ya','V','') jatuh, "
                            + "if(ti.luka_bakar='ya','V','') luka, if(ti.trauma_listrik='ya','V','') trauma_listrik, if(ti.trauma_zat_kimia='ya','V','') trauma_zat, if(ti.trauma_lain='ya','V','') trauma_lain, "
                            + "ti.bb, ti.tb from triase_igd ti inner join reg_periksa rp on rp.no_rawat=ti.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "INNER JOIN pegawai pg on nik=ti.nip_petugas where ti.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsLaprm != null) {
                    rsLaprm.close();
                }
                if (psLaprm != null) {
                    psLaprm.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void cetakAsesMedikIGD() {
        skorAsesIGD = "";
        try {
            psLaprm = koneksi.prepareStatement("select *, date_format(tgl_keluar_igd,'%H:%i:%s') jamklr from penilaian_awal_medis_igd where no_rawat='" + TNoRw.getText() + "'");
            try {
                rsLaprm = psLaprm.executeQuery();
                while (rsLaprm.next()) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));

                    //hitung skor
                    int A, B, C, D, E, hasil;
                    A = Integer.parseInt(rsLaprm.getString("frekuensi_nafas"));
                    B = Integer.parseInt(rsLaprm.getString("retraksi"));
                    C = Integer.parseInt(rsLaprm.getString("sianosis"));
                    D = Integer.parseInt(rsLaprm.getString("air_entry"));
                    E = Integer.parseInt(rsLaprm.getString("merintih"));

                    hasil = 0;
                    hasil = A + B + C + D + E;
                    skorAsesIGD = Valid.SetAngka2(hasil);
                    param.put("jlhSkor", skorAsesIGD);

                    if (rsLaprm.getString("meninggal").equals("ya")) {
                        param.put("jam_meninggal", rsLaprm.getString("jam_meninggal"));
                    } else {
                        param.put("jam_meninggal", "-");
                    }

                    if (rsLaprm.getString("cek_jam_keluar").equals("ya")) {
                        param.put("jam_keluar", rsLaprm.getString("jamklr"));
                    } else {
                        param.put("jam_keluar", "-");
                    }

                    Valid.MyReport("rptCetakPenilaianAwalMedisIGD.jasper", "report", "::[ Laporan Penilaian Awal Medis IGD hal. 1 ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                            + "date_format(pa.tanggal,'Tanggal : %d-%m-%Y    Pukul : %H:%i') mulai_penanganan, if(pa.cervival='ya','V','') cer, "
                            + "if(pa.rjp='ya','V','') rjp, if(pa.defribilasi='ya','V','') def, if(pa.intubasi='ya','V','') intu, if(pa.vtp='ya','V','') vtp, if(pa.dekompresi='ya','V','') dek, "
                            + "if(pa.balut='ya','V','') bal, if(pa.kateter='ya','V','') kat, if(pa.ngt='ya','V','') ngt, if(pa.infus='ya','V','') infs, if(pa.obat='ya','V','') obt, pa.ket_obat, "
                            + "if(pa.tidak_ada='ya','V','') tdk, if(pa.gangguan_jalan_nafas='ya','V','') ggnfs, if(pa.paten='ya','V','') pat, if(pa.obstruksi_partial='ya','V','') obsp, if(pa.data_obstruksi_partial='','-',pa.data_obstruksi_partial) data_obsp, "
                            + "if(pa.obstruksi_total='ya','V','') obst, if(pa.trauma_jalan_nafas='ya','V','') trauma_jln_nfs, pa.trauma, if(pa.resiko_aspirasi='ya','V','') res, pa.aspirasi, "
                            + "if(pa.benda_asing='ya','V','') ben, pa.ket_benda_asing, pa.kesimpulan_jalan_nafas, pa.pernafasan, pa.data_pernafasan, pa.gerakan_dada, pa.tipe_pernafasan, "
                            + "pa.kesimpulan_pernafasan, pa.nadi_1, pa.kulit_mukosa, pa.akral_1, pa.crt, pa.kesimpulan_sirkulasi, pa.cukup_bulan, pa.cairan_amnion, pa.pernafasan_menangis, pa.tonus, "
                            + "pa.skor_apgar, pa.gcs_e, pa.pupil, pa.diameter_kanan, pa.reflek_cahaya, pa.meningeal_sign, pa.literasi, if(pa.deformitas='ya','V','') defo, if(pa.contusio='ya','V','') con, "
                            + "if(pa.penetrasi='ya','V','') pen, if(pa.tenderness='ya','V','') ten, if(pa.swelling='ya','V','') swe, if(pa.ekskoriasi='ya','V','') eks, if(pa.abrasi='ya','V','') abr, "
                            + "if(pa.burn='ya','V','') bur, if(pa.laserasi='ya','V','') las, if(pa.tidak_tampak_jelas='ya','V','') tdk_tmpk, pa.frekuensi_nafas, pa.retraksi, pa.sianosis, pa.air_entry, "
                            + "pa.merintih, pa.Alergi, if(pa.hipertensi='ya','V','') hip, if(pa.diabetes='ya','V','') dm, if(pa.jantung='ya','V','') jan, pa.riwayat_penyakit_lain, "
                            + "if(pa.merokok='ya','V','') mer, pa.kebiasaan_lain, pa.anamnesis, pa.pemeriksaan_fisik, pa.konjungtiva, pa.sklera, pa.bibir_lidah, pa.mukosa, pa.deviasi, pa.jvp, "
                            + "pa.lnn, pa.tiroid, pa.survei_jantung, pa.survei_paru, pa.survei_abdomen, pa.survei_punggung, pa.survei_ekstremitas, pa.laboratorium, pa.x_ray, "
                            + "pa.ecg, pa.ct_scan, pa.usg, pa.lainnya_penunjang, pa.diag_medis_sementara, pa.icd_10, pa.rencana_instruksi, pa.ket_rencana_instruksi, pa.telah_diberikan_informasi_edukasi, "
                            + "pa.rencana_asuhan_diharapkan, pg1.nama pemberi_edukasi, pa.penerima_edukasi, pg2.nama nm_dokter, date_format(pa.tgl_keluar_igd,'%d-%m-%Y') tglkeluar, "
                            + "date_format(pa.tgl_keluar_igd,'%H:%i') jamkeluar, pa.opname_diruang, pa.indikasi_msk, pa.dipulangkan_kontrol_ke, pa.dirujuk_ke, pa.alasan_dirujuk, pa.penyebab, "
                            + "pa.k_u, pa.td, pa.hr, pa.rr, pa.temp, pa.spo2, pa.gcs_pulang, pg3.nama nm_perawat, pg4.nama nm_dpjp, pa.gcs_v, pa.gcs_m, pa.diameter_kiri, pa.nadi_2, pa.akral_2 "
                            + "from penilaian_awal_medis_igd pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                            + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis left join pegawai pg1 on pg1.nik=pa.nip_pemberi_edukasi "
                            + "left join pegawai pg2 on pg2.nik=pa.nip_dokter left join pegawai pg3 on pg3.nik=pa.nip_perawat "
                            + "left join pegawai pg4 on pg4.nik=pa.nip_dpjp where pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);

                    Valid.MyReport("rptCetakPenilaianAwalMedisIGD1.jasper", "report", "::[ Laporan Penilaian Awal Medis IGD hal. 2 ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                            + "date_format(pa.tanggal,'Tanggal : %d-%m-%Y    Pukul : %H:%i') mulai_penanganan, if(pa.cervival='ya','V','') cer, "
                            + "if(pa.rjp='ya','V','') rjp, if(pa.defribilasi='ya','V','') def, if(pa.intubasi='ya','V','') intu, if(pa.vtp='ya','V','') vtp, if(pa.dekompresi='ya','V','') dek, "
                            + "if(pa.balut='ya','V','') bal, if(pa.kateter='ya','V','') kat, if(pa.ngt='ya','V','') ngt, if(pa.infus='ya','V','') infs, if(pa.obat='ya','V','') obt, pa.ket_obat, "
                            + "if(pa.tidak_ada='ya','V','') tdk, if(pa.gangguan_jalan_nafas='ya','V','') ggnfs, if(pa.paten='ya','V','') pat, if(pa.obstruksi_partial='ya','V','') obsp, if(pa.data_obstruksi_partial='','-',pa.data_obstruksi_partial) data_obsp, "
                            + "if(pa.obstruksi_total='ya','V','') obst, if(pa.trauma_jalan_nafas='ya','V','') trauma_jln_nfs, pa.trauma, if(pa.resiko_aspirasi='ya','V','') res, pa.aspirasi, "
                            + "if(pa.benda_asing='ya','V','') ben, pa.ket_benda_asing, pa.kesimpulan_jalan_nafas, pa.pernafasan, pa.data_pernafasan, pa.gerakan_dada, pa.tipe_pernafasan, "
                            + "pa.kesimpulan_pernafasan, pa.nadi_1, pa.kulit_mukosa, pa.akral_1, pa.crt, pa.kesimpulan_sirkulasi, pa.cukup_bulan, pa.cairan_amnion, pa.pernafasan_menangis, pa.tonus, "
                            + "pa.skor_apgar, pa.gcs_e, pa.pupil, pa.diameter_kanan, pa.reflek_cahaya, pa.meningeal_sign, pa.literasi, if(pa.deformitas='ya','V','') defo, if(pa.contusio='ya','V','') con, "
                            + "if(pa.penetrasi='ya','V','') pen, if(pa.tenderness='ya','V','') ten, if(pa.swelling='ya','V','') swe, if(pa.ekskoriasi='ya','V','') eks, if(pa.abrasi='ya','V','') abr, "
                            + "if(pa.burn='ya','V','') bur, if(pa.laserasi='ya','V','') las, if(pa.tidak_tampak_jelas='ya','V','') tdk_tmpk, pa.frekuensi_nafas, pa.retraksi, pa.sianosis, pa.air_entry, "
                            + "pa.merintih, pa.Alergi, if(pa.hipertensi='ya','V','') hip, if(pa.diabetes='ya','V','') dm, if(pa.jantung='ya','V','') jan, pa.riwayat_penyakit_lain, "
                            + "if(pa.merokok='ya','V','') mer, pa.kebiasaan_lain, pa.anamnesis, pa.pemeriksaan_fisik, pa.konjungtiva, pa.sklera, pa.bibir_lidah, pa.mukosa, pa.deviasi, pa.jvp, "
                            + "pa.lnn, pa.tiroid, pa.survei_jantung, pa.survei_paru, pa.survei_abdomen, pa.survei_punggung, pa.survei_ekstremitas, pa.laboratorium, pa.x_ray, "
                            + "pa.ecg, pa.ct_scan, pa.usg, pa.lainnya_penunjang, pa.diag_medis_sementara, pa.icd_10, pa.rencana_instruksi, pa.ket_rencana_instruksi, pa.telah_diberikan_informasi_edukasi, "
                            + "pa.rencana_asuhan_diharapkan, ifnull(pg1.nama,'-') pemberi_edukasi, pa.penerima_edukasi, ifnull(pg2.nama,'-') nm_dokter, date_format(pa.tgl_keluar_igd,'%d-%m-%Y') tglkeluar, "
                            + "date_format(pa.tgl_keluar_igd,'%H:%i') jamkeluar, pa.opname_diruang, pa.indikasi_msk, pa.dipulangkan_kontrol_ke, pa.dirujuk_ke, pa.alasan_dirujuk, pa.penyebab, "
                            + "pa.k_u, pa.td, pa.hr, pa.rr, pa.temp, pa.spo2, pa.gcs_pulang, pg3.nama nm_perawat, pg4.nama nm_dpjp, ifnull(pa.ket_gambar,'-') ket_gambar, pa.gcs_v, pa.gcs_m, pa.diameter_kiri, "
                            + "SUBSTRING_INDEX( pa.ket_rencana_instruksi, '\n', 5 ) renc1, SUBSTRING_INDEX( pa.ket_rencana_instruksi, SUBSTRING_INDEX( pa.ket_rencana_instruksi, '\n', 5 ),- 1 ) renc2 "
                            + "from penilaian_awal_medis_igd pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                            + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis left join pegawai pg1 on pg1.nik=pa.nip_pemberi_edukasi "
                            + "left join pegawai pg2 on pg2.nik=pa.nip_dokter left join pegawai pg3 on pg3.nik=pa.nip_perawat "
                            + "left join pegawai pg4 on pg4.nik=pa.nip_dpjp where pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsLaprm != null) {
                    rsLaprm.close();
                }
                if (psLaprm != null) {
                    psLaprm.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void cetakAsesKepIGD() {
        try {
            psLaprm = koneksi.prepareStatement("select * from penilaian_awal_keperawatan_igdrz where no_rawat='" + TNoRw.getText() + "'");
            try {
                rsLaprm = psLaprm.executeQuery();
                while (rsLaprm.next()) {
                    if (rsLaprm.getString("gizi_dewasa1").equals("Tidak")) {
                        skorGZ1 = 0;
                    } else if (rsLaprm.getString("gizi_dewasa1").equals("Tidak Tahu/tidak yakin (ada tanda : baju menjadi longgar)")) {
                        skorGZ1 = 1;
                    } else if (rsLaprm.getString("gizi_dewasa1").equals("Ya, ada penurunan BB sebanyak")) {
                        skorGZ1 = 0;
                    }

                    if (rsLaprm.getString("gizi_dewasa1ya").equals("-")) {
                        skorYaGZ1 = 0;
                    } else if (rsLaprm.getString("gizi_dewasa1ya").equals("1 - 5 Kg")) {
                        skorYaGZ1 = 1;
                    } else if (rsLaprm.getString("gizi_dewasa1ya").equals("6 - 10 Kg")) {
                        skorYaGZ1 = 2;
                    } else if (rsLaprm.getString("gizi_dewasa1ya").equals("11 - 15 Kg")) {
                        skorYaGZ1 = 3;
                    } else if (rsLaprm.getString("gizi_dewasa1ya").equals("15 Kg")) {
                        skorYaGZ1 = 4;
                    } else if (rsLaprm.getString("gizi_dewasa1ya").equals("Tidak tahu berapa Kg penurunanya")) {
                        skorYaGZ1 = 2;
                    }

                    if (rsLaprm.getString("gizi_dewasa2").equals("Tidak")) {
                        skorGZ2 = 0;
                    } else if (rsLaprm.getString("gizi_dewasa2").equals("Ya")) {
                        skorGZ2 = 1;
                    }

                    //hitung skor skrining
                    int A1, B1, C1, TotD, A2, B2, C2, D2, TotA;
                    A1 = skorGZ1;
                    B1 = skorYaGZ1;
                    C1 = skorGZ2;

                    A2 = Integer.parseInt(rsLaprm.getString("gizi_anak1"));
                    B2 = Integer.parseInt(rsLaprm.getString("gizi_anak2"));
                    C2 = Integer.parseInt(rsLaprm.getString("gizi_anak3"));
                    D2 = Integer.parseInt(rsLaprm.getString("gizi_anak_penyakit"));

                    TotD = 0;
                    TotA = 0;

                    TotD = A1 + B1 + C1;
                    TotA = A2 + B2 + C2 + D2;
                    TotSkorGZD = Valid.SetAngka2(TotD);
                    TotSkorGZA = Valid.SetAngka2(TotA);

                    if (rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                        kesimpulanGZanak = "";
                        if (TotD == 0 || TotD == 1) {
                            kesimpulanGZDewasa = "0 - 1 : tidak beresiko malnutrisi";
                        } else if (TotD >= 2) {
                            kesimpulanGZDewasa = ">= 2 : beresiko malnutrisi, perlu pemantauan lanjutan oleh Tim Gizi/Dietisien";
                        }
                    }

                    if (rsLaprm.getString("skrining_gizi").equals("anak")) {
                        kesimpulanGZDewasa = "";
                        if (TotA == 0) {
                            kesimpulanGZanak = "0 : tidak beresiko malnutrisi";
                        } else if (TotA >= 1 && TotA <= 3) {
                            kesimpulanGZanak = "1 - 3 : resiko malnutrisi sedang, perlu pemantauan";
                        } else if (TotA >= 4) {
                            kesimpulanGZanak = ">= 4 : resiko malnutrisi berat, perlu pemantauan lanjutan oleh Tim Gizi/Dietisien";
                        }
                    }

                    //tindakan pencegahan resiko jatuh
                    if (rsLaprm.getString("cegah_resiko_jatuh").equals("Dewasa")) {
                        TabTindakanPencegahan.setSelectedIndex(0);
                        if (rsLaprm.getString("tindakan_pencegahan").equals("A")) {
                            TabPencegahanDewasa.setSelectedIndex(0);
                        } else if (rsLaprm.getString("tindakan_pencegahan").equals("B")) {
                            TabPencegahanDewasa.setSelectedIndex(1);
                        } else if (rsLaprm.getString("tindakan_pencegahan").equals("C")) {
                            TabPencegahanDewasa.setSelectedIndex(2);
                        } else {
                            TabPencegahanDewasa.setSelectedIndex(0);
                        }
                    } else if (rsLaprm.getString("cegah_resiko_jatuh").equals("Anak")) {
                        TabTindakanPencegahan.setSelectedIndex(1);
                        if (rsLaprm.getString("tindakan_pencegahan").equals("A")) {
                            TabPencegahanAnak.setSelectedIndex(0);
                        } else if (rsLaprm.getString("tindakan_pencegahan").equals("B")) {
                            TabPencegahanAnak.setSelectedIndex(1);
                        } else {
                            TabPencegahanAnak.setSelectedIndex(0);
                        }
                    } else {
                        TabTindakanPencegahan.setSelectedIndex(0);
                        TabPencegahanDewasa.setSelectedIndex(0);
                    }

                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));

                    if (rsLaprm.getString("skrining_gizi").equals("anak")) {
                        param.put("jenisSkrining", "ANAK (berdasarkan modifikasi form STRONG Kids)");
                        param.put("kalimatSkrining", "1. Terdapat penurunan BB atau BB menetap (pada bayi < 1 tahun) selama >= 2 bulan                 Skor (" + rsLaprm.getString("gizi_anak1") + ")\n\n"
                                + "2. Terdapat tanda-tanda klinis gangguan gizi (tampak kurus, gemuk, pendek, edema, moon face,   Skor (" + rsLaprm.getString("gizi_anak2") + ")\n"
                                + "tampak tua, iga gambang, baggy pant, anoreksia) selama 1 bulan terakhir\n\n"
                                + "3. Terdapat salah satu penyakit/kondisi yg. beresiko mengakibatkan malnutrisi berikut :                 Skor (" + rsLaprm.getString("gizi_anak3") + ")\n"
                                + "* Diare berat (> 5x/hari) dan atau muntah (> 3x/hari)\n"
                                + "* Penurunan asupan makanan selama lebih dari 7 hari\n\n"
                                + "Terdapat penyakit-penyakit / keadaan yg. meningkatkan resiko malnutrisi antara lain :                   Skor (" + rsLaprm.getString("gizi_anak_penyakit") + ")\n"
                                + "* Diare kronik > 2 minggu                          * Penyakit hati/ginjal kronik\n"
                                + "* Penyakit jantung bawaan (tersangka)        * TB Paru\n"
                                + "* Infeksi HIV (tersangka)                            * Renca/paska operasimayor\n"
                                + "* Kelainan anatomi bawaan                         * Luka bakar luas\n"
                                + "* Kelainan metabolisme bawaan                  * Terpasang stoma\n"
                                + "* Retardasi mental                                     * Trauma\n"
                                + "* Keterlambatan perkembangan                  * Lain-lain : " + rsLaprm.getString("gizi_anak_penyakit_lain") + "\n"
                                + "* Kanker (tersangka)\n"
                                + "_______________________________________________________________________\n"
                                + "Total Skor : (" + TotSkorGZA + ")\n"
                                + "Kesimpulan Skrining Gizi Anak :\n"
                                + kesimpulanGZanak + "\n");
                        param.put("resikoJatuh", "Anak (Skala Humpty Dumpty)");
                        param.put("tindakanRJ", "ANAK");
                        if (TabPencegahanAnak.getSelectedIndex() == 0) {
                            param.put("JudultindakanRJ", "Pencegahan Umum (A)");
                            param.put("IsitindakanRJ", anakA.getText());
                        } else if (TabPencegahanAnak.getSelectedIndex() == 1) {
                            param.put("JudultindakanRJ", "Pencegahan Resiko Tinggi (B)");
                            param.put("IsitindakanRJ", anakB.getText());
                        }

                    } else if (rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                        param.put("jenisSkrining", "DEWASA (Modifikasi MST)");
                        param.put("kalimatSkrining", "1. Apakah pasien mengalami penurunan BB yang tidak direncanakan/tidak diinginkan dalam 6 bulan terakhir ?\n"
                                + rsLaprm.getString("gizi_dewasa1") + "   Skor (" + skorGZ1 + ")\n"
                                + rsLaprm.getString("gizi_dewasa1ya") + "   Skor (" + skorYaGZ1 + ")\n\n"
                                + "2. Apakah asupan makan pasien berkurang karena penurunan nafsu makan / kesulitan menerima makanan ?\n"
                                + rsLaprm.getString("gizi_dewasa2") + "   Skor (" + skorGZ2 + ")\n"
                                + "_______________________________________________________________________\n"
                                + "Total Skor : (" + TotSkorGZD + ")\n"
                                + "Kesimpulan Skrining Gizi Dewasa :\n"
                                + kesimpulanGZDewasa + "\n");
                        param.put("resikoJatuh", "Dewasa (Skala Morse)");
                        param.put("tindakanRJ", "DEWASA");
                        if (TabPencegahanDewasa.getSelectedIndex() == 0) {
                            param.put("JudultindakanRJ", "Pencegahan Umum (A)");
                            param.put("IsitindakanRJ", dewasaA.getText());
                        } else if (TabPencegahanDewasa.getSelectedIndex() == 1) {
                            param.put("JudultindakanRJ", "Pencegahan Resiko Sedang (B)");
                            param.put("IsitindakanRJ", dewasaB.getText());
                        } else if (TabPencegahanDewasa.getSelectedIndex() == 2) {
                            param.put("JudultindakanRJ", "Pencegahan Resiko Tinggi (C)");
                            param.put("IsitindakanRJ", dewasaC.getText());
                        }

                    } else if (!rsLaprm.getString("skrining_gizi").equals("anak") && !rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                        param.put("jenisSkrining", "");
                        param.put("kalimatSkrining", "");
                        param.put("resikoJatuh", "");
                        param.put("tindakanRJ", "");
                        param.put("JudultindakanRJ", "");
                        param.put("IsitindakanRJ", "");
                    }

                    //data faktor resiko
                    try {
                        faktorresikoigd = "";
                        psFakIGD = koneksi.prepareStatement("select m.kode_resiko, concat('Faktor : ',m.faktor_resiko,', Skala : ',m.skala,', Skor (',m.skor,')') resiko "
                                + "FROM master_faktor_resiko_igd m INNER JOIN penilaian_awal_keperawatan_igd_resiko pm ON pm.kode_resiko = m.kode_resiko "
                                + "WHERE pm.no_rawat=? ORDER BY pm.kode_resiko");
                        try {
                            psFakIGD.setString(1, rsLaprm.getString("no_rawat"));
                            rsFakIGD = psFakIGD.executeQuery();
                            while (rsFakIGD.next()) {
                                faktorresikoigd = rsFakIGD.getString("resiko") + "\n" + faktorresikoigd;
                            }

                            if (faktorresikoigd.endsWith("\n")) {
                                faktorresikoigd = faktorresikoigd.substring(0, faktorresikoigd.length() - 1);
                            }

                        } catch (Exception e) {
                            System.out.println("Notif : " + e);
                        } finally {
                            if (rsFakIGD != null) {
                                rsFakIGD.close();
                            }
                            if (psFakIGD != null) {
                                psFakIGD.close();
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                    }

                    //cek faktor resiko                    
                    try {
                        Valid.tabelKosong(tabModeResiko);
                        if (rsLaprm.getString("skrining_gizi").equals("anak")) {
                            psRes = koneksi.prepareStatement("select m.kode_resiko, m.faktor_resiko, m.skala, m.skor, m.asesmen FROM master_faktor_resiko_igd m "
                                    + "INNER JOIN penilaian_awal_keperawatan_igd_resiko pa ON pa.kode_resiko = m.kode_resiko "
                                    + "WHERE m.asesmen = 'anak' and pa.no_rawat=? ORDER BY pa.kode_resiko");
                        } else if (rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                            psRes = koneksi.prepareStatement("select m.kode_resiko, m.faktor_resiko, m.skala, m.skor, m.asesmen FROM master_faktor_resiko_igd m "
                                    + "INNER JOIN penilaian_awal_keperawatan_igd_resiko pa ON pa.kode_resiko = m.kode_resiko "
                                    + "WHERE m.asesmen = 'dewasa' and pa.no_rawat=? ORDER BY pa.kode_resiko");
                        } else {
                            psRes = koneksi.prepareStatement("select m.kode_resiko, m.faktor_resiko, m.skala, m.skor, m.asesmen FROM master_faktor_resiko_igd m "
                                    + "INNER JOIN penilaian_awal_keperawatan_igd_resiko pa ON pa.kode_resiko = m.kode_resiko "
                                    + "WHERE pa.no_rawat=? ORDER BY pa.kode_resiko");
                        }
                        try {
                            psRes.setString(1, rsLaprm.getString("no_rawat"));
                            rsRes = psRes.executeQuery();
                            while (rsRes.next()) {
                                tabModeResiko.addRow(new Object[]{
                                    true,
                                    rsRes.getString("kode_resiko"),
                                    rsRes.getString("asesmen"),
                                    rsRes.getString("faktor_resiko"),
                                    rsRes.getString("skala"),
                                    rsRes.getString("skor")
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : " + e);
                        } finally {
                            if (rsRes != null) {
                                rsRes.close();
                            }
                            if (psRes != null) {
                                psRes.close();
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                    }

                    //hitung skor faktor resiko
                    skor = 0;
                    for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
                        if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                            skor = skor + Integer.parseInt(tbFaktorResiko.getValueAt(i, 5).toString());
                        }
                    }

                    TotSkorRJ = Valid.SetAngka2(skor);
                    if (!rsLaprm.getString("skrining_gizi").equals("dewasa") && !rsLaprm.getString("skrining_gizi").equals("anak")) {
                        TotSkorRJ = "0";
                        kesimpulanResikoJatuh = "";
                    }

                    //asesmen dewasa
                    if (rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                        if (skor >= 45) {
                            kesimpulanResikoJatuh = "Resiko Tinggi : >= 45, pasang kancing berwarna kuning";
                        } else if (skor >= 25 && skor <= 44) {
                            kesimpulanResikoJatuh = "Resiko Sedang : 25-44, pasang kancing berwarna kuning";
                        } else if (skor >= 0 && skor <= 24) {
                            kesimpulanResikoJatuh = "Resiko Rendah : 0-24";
                        }
                    }

                    //asesmen anak
                    if (rsLaprm.getString("skrining_gizi").equals("anak")) {
                        if (skor >= 12) {
                            kesimpulanResikoJatuh = "Resiko Tinggi : >= 12, pasang kancing penanda berwarna kuning";
                        } else if (skor >= 7 && skor <= 11) {
                            kesimpulanResikoJatuh = "Resiko Rendah : 7-11";
                        } else if (skor >= 0 && skor <= 6) {
                            kesimpulanResikoJatuh = "";
                        }
                    }

                    if (!rsLaprm.getString("skrining_gizi").equals("anak") && !rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                        param.put("dataResiko", "");
                        param.put("TotSkorResikoJatuh", "");
                        param.put("KesResikoJatuh", "");
                    } else {
                        param.put("dataResiko", faktorresikoigd);
                        param.put("TotSkorResikoJatuh", "Total Skor : (" + TotSkorRJ + ")");
                        param.put("KesResikoJatuh", "Kesimpulan Skor Resiko Jatuh :\n" + kesimpulanResikoJatuh);
                    }

                    Valid.MyReport("rptCetakPenilaianAwalKeperawatanIGD1.jasper", "report", "::[ Laporan Penilaian Awal Keperawatan IGD hal. 2 ]::",
                            "SELECT if(pa.nyeri='Ya',concat(pa.nyeri,', Lokasi : ',pa.ya_nyeri_lokasi),pa.nyeri) nyeri, pa.jenis_nyeri, "
                            + "if(pa.provocation='Lainnya',concat(pa.provocation,', ',pa.provocation_lain),pa.provocation) provokes, if(pa.quality='Lainnya',concat(pa.quality,', ',pa.quality_lain),pa.quality) quality, "
                            + "pa.radiation radiasi, pa.severity severity_nyeri, concat(pa.time,', Lama : ',pa.time_lama) time_nyeri, pa.skala_nyeri, pa.diagnosa_keperawatan, pa.tindakan_keperawatan, pa.evaluasi_keperawatan, "
                            + "if(pa.identifikasi1='ya','V','') iden1, if(pa.identifikasi2='ya','V','') iden2, if(pa.identifikasi3='ya','V','') iden3, if(pa.identifikasi4='ya','V','') iden4, if(pa.identifikasi5='ya','V','') iden5, "
                            + "if(pa.identifikasi6='ya','V','') iden6, if(pa.identifikasi7='ya','V','') iden7, if(pa.identifikasi8='ya','V','') iden8, if(pa.identifikasi9='ya','V','') iden9, if(pa.identifikasi10='ya','V','') iden10, "
                            + "if(pa.manajer_pelayanan='ya','V','') mpp, if(pa.discharge_planing='ya','V','') dp, concat('Tanggal, ',date_format(pa.tgl_verifikasi,'%d-%m-%Y'),'   Jam ',TIME_FORMAT(pa.tgl_verifikasi,'%H:%i:%S')) tglverif, "
                            + "pg1.nama dr_dpjp, pg2.nama perawat from penilaian_awal_keperawatan_igdrz pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "inner join pegawai pg1 on pg1.nik=pa.nip_dpjp inner join pegawai pg2 on pg2.nik=pa.nip_perawat where "
                            + "pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);

                    Valid.MyReport("rptCetakPenilaianAwalKeperawatanIGD.jasper", "report", "::[ Laporan Penilaian Awal Keperawatan IGD hal. 1 ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, concat('Tanggal : ', date_format(pa.tanggal,'%d-%m-%Y'),', Pukul : ',time_format(pa.tanggal,'%H:%i-%S')) tgl, "
                            + "pa.keluhan_utama, pa.td tensi, pa.nadi, pa.nafas, pa.suhu, pa.bb, pa.tb, pa.asesmen_psikologis psikologis, if(pa.masalah_perilaku='Ada',concat(pa.masalah_perilaku,', Sebutkan : ',pa.sebutkan_perilaku),pa.masalah_perilaku) perilaku, "
                            + "p.stts_nikah, pa.hubungan_pasien hubungan, if(pa.tempat_tinggal='Lainnya',concat(pa.tempat_tinggal,', ',pa.lainya_tempt_tgl),pa.tempat_tinggal) tmpttgl, p.pekerjaan, "
                            + "pa.alat_bantu, if(pa.cacat_tubuh='Ada',concat(pa.cacat_tubuh,', ',pa.ada_cacat_tubuh),pa.cacat_tubuh) cacat, "
                            + "pa.riwayat_alergi, if(pa.alergi_obat='ya','V','') aler_obat, if(pa.alergi_obat='ya',pa.reaksi_alergi_obat,'') reak_obat, if(pa.alergi_makanan='ya','V','') aler_mak, "
                            + "if(pa.alergi_makanan='ya',pa.reaksi_alergi_makanan,'') reak_mak, if(pa.alergi_lainnya='ya','V','') aler_lain, if(pa.alergi_lainnya='ya',pa.reaksi_alergi_lainnya,'') reak_lain, "
                            + "if(pa.pin_kancing='ya','V','') pin, pa.alergi_diberitahukan, if(pa.nyeri='Ya',concat(pa.nyeri,', Lokasi : ',pa.ya_nyeri_lokasi),pa.nyeri) nyeri, pa.jenis_nyeri, "
                            + "if(pa.provocation='Lainnya',concat(pa.provocation,', ',pa.provocation_lain),pa.provocation) provokes, if(pa.quality='Lainnya',concat(pa.quality,', ',pa.quality_lain),pa.quality) quality, "
                            + "pa.radiation radiasi, pa.severity severity_nyeri, concat(pa.time,', Lama : ',pa.time_lama) time_nyeri, pa.diagnosa_keperawatan, pa.tindakan_keperawatan, pa.evaluasi_keperawatan, if(pa.identifikasi1='ya','V','') iden1, "
                            + "if(pa.identifikasi2='ya','V','') iden2, if(pa.identifikasi3='ya','V','') iden3, if(pa.identifikasi4='ya','V','') iden4, if(pa.identifikasi5='ya','V','') iden5, if(pa.identifikasi6='ya','V','') iden6, "
                            + "if(pa.identifikasi7='ya','V','') iden7, if(pa.identifikasi8='ya','V','') iden8, if(pa.identifikasi9='ya','V','') iden9, if(pa.identifikasi10='ya','V','') iden10, "
                            + "if(pa.manajer_pelayanan='ya','V','') mpp, if(pa.discharge_planing='ya','V','') dp from penilaian_awal_keperawatan_igdrz pa "
                            + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                            + "pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsLaprm != null) {
                    rsLaprm.close();
                }
                if (psLaprm != null) {
                    psLaprm.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void cetakTransferSerahTerimaIGD() {
        try {
            psLaprm = koneksi.prepareStatement("select *, date_format(tgl_masuk,'%d-%m-%Y') tglmsk, date_format(tgl_jam_pindah,'%d-%m-%Y / %H:%i') jampindah, "
                    + "date_format(tgl_infus,'%d-%m-%Y') tglinfus, date_format(tgl_kateter,'%d-%m-%Y') tglkateter, date_format(tgl_ngt,'%d-%m-%Y') tglngt, "
                    + "date_format(tgl_oksigen,'%d-%m-%Y') tgloksigen, date_format(tgl_drain,'%d-%m-%Y') tgldrain, date_format(tgl_alat_lain,'%d-%m-%Y') tglalat "
                    + "from transfer_serah_terima_pasien_igd where no_rawat='" + TNoRw.getText() + "' and status='Ralan' order by waktu_simpan desc limit 1");
            try {
                rsLaprm = psLaprm.executeQuery();
                while (rsLaprm.next()) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    param.put("norm", NoRM.getText());
                    param.put("nmpasien", Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + NoRM.getText() + "'"));
                    param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + NoRM.getText() + "'"));
                    param.put("nmdpjpp", Sequel.cariIsi("select nama from pegawai where nik='" + rsLaprm.getString("nip_dpjp") + "'"));
                    param.put("konsulen1", Sequel.cariIsi("select nama from pegawai where nik='" + rsLaprm.getString("nip_konsulen1") + "'"));
                    param.put("konsulen2", Sequel.cariIsi("select nama from pegawai where nik='" + rsLaprm.getString("nip_konsulen2") + "'"));
                    param.put("diagnosis", rsLaprm.getString("diagnosis"));
                    param.put("tglmasuk", rsLaprm.getString("tglmsk"));                    
                    param.put("ruangkamar", Sequel.cariIsi("SELECT p.nm_poli FROM reg_periksa r inner join poliklinik p on p.kd_poli=r.kd_poli WHERE r.no_rawat='" + rsLaprm.getString("no_rawat") + "'"));                    
                    param.put("tgljampindah", rsLaprm.getString("jampindah"));
                    param.put("ruangkamarpindah", Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + rsLaprm.getString("kd_kamar_pindah") + "'"));
                    param.put("alasanranap", rsLaprm.getString("alasan_ranap"));
                    param.put("keluhan", rsLaprm.getString("keluhan"));
                    param.put("riwpenyakit", rsLaprm.getString("riwayat_penyakit"));
                    param.put("riwalergi", rsLaprm.getString("riwayat_alergi"));
                    param.put("gcse", rsLaprm.getString("gcs_e"));
                    param.put("gcsm", rsLaprm.getString("gcs_m"));
                    param.put("gcsv", rsLaprm.getString("gcs_v"));
                    param.put("kesadaran", rsLaprm.getString("kesadaran"));
                    param.put("tensi", rsLaprm.getString("td"));
                    param.put("nadi", rsLaprm.getString("nadi"));
                    param.put("suhu", rsLaprm.getString("suhu"));
                    param.put("rr", rsLaprm.getString("rr"));
                    param.put("spo2", rsLaprm.getString("spo2"));
                    param.put("nyeri", rsLaprm.getString("skala_nyeri"));
                    param.put("resikojatuh", rsLaprm.getString("resiko_jatuh"));
                    param.put("kriteria", rsLaprm.getString("kriteria_transfer"));
                    param.put("diagnosa", rsLaprm.getString("diagnosa"));
                    param.put("rekomendasi", rsLaprm.getString("rekomendasi"));
                    param.put("alasanpindahruangan", rsLaprm.getString("alasan_pindah_ruangan"));
                    param.put("nmpasienkeluarga", rsLaprm.getString("nm_pasien_keluarga"));
                    param.put("nmdokter", Sequel.cariIsi("select nama from pegawai where nik='" + rsLaprm.getString("nip_dokter_setuju") + "'"));
                    param.put("ygmenyerahkan", Sequel.cariIsi("select nama from pegawai where nik='" + rsLaprm.getString("nip_menyerahkan") + "'"));
                    param.put("ygmenerima", Sequel.cariIsi("select nama from pegawai where nik='" + rsLaprm.getString("nip_menerima") + "'"));
                    param.put("tgltransferserah",Valid.SetTglINDONESIA(rsLaprm.getString("tgl_serah_terima_transfer")));                    

                    if (rsLaprm.getString("lab").equals("ya")) {
                        param.put("lab", "V");
                    } else {
                        param.put("lab", "");
                    }

                    if (rsLaprm.getString("ekg").equals("ya")) {
                        param.put("ekg", "V");
                    } else {
                        param.put("ekg", "");
                    }

                    if (rsLaprm.getString("thoraks_foto").equals("ya")) {
                        param.put("torak", "V");
                    } else {
                        param.put("torak", "");
                    }

                    if (rsLaprm.getString("foto_cervikal").equals("ya")) {
                        param.put("fotoc", "V");
                    } else {
                        param.put("fotoc", "");
                    }

                    if (rsLaprm.getString("foto_genu").equals("ya")) {
                        param.put("fotog", "V");
                    } else {
                        param.put("fotog", "");
                    }

                    if (rsLaprm.getString("foto_abdomen").equals("ya")) {
                        param.put("fotoa", "V");
                    } else {
                        param.put("fotoa", "");
                    }

                    if (rsLaprm.getString("spiritometri").equals("ya")) {
                        param.put("spiri", "V");
                    } else {
                        param.put("spiri", "");
                    }

                    if (rsLaprm.getString("echo").equals("ya")) {
                        param.put("echo", "V");
                    } else {
                        param.put("echo", "");
                    }

                    if (rsLaprm.getString("usg").equals("ya")) {
                        param.put("usg", "V");
                    } else {
                        param.put("usg", "");
                    }

                    if (rsLaprm.getString("ct_scan").equals("ya")) {
                        param.put("ctscan", "V");
                        param.put("ketctscan", rsLaprm.getString("ket_ct_scan"));
                    } else {
                        param.put("ctscan", "");
                        param.put("ketctscan", "");
                    }

                    if (rsLaprm.getString("endoskopi").equals("ya")) {
                        param.put("endos", "V");
                        param.put("ketendos", rsLaprm.getString("ket_endoskopi"));
                    } else {
                        param.put("endos", "");
                        param.put("ketendos", "");
                    }

                    if (rsLaprm.getString("ctg").equals("ya")) {
                        param.put("ctg", "V");
                        param.put("ketctg", rsLaprm.getString("ket_ctg"));
                    } else {
                        param.put("ctg", "");
                        param.put("ketctg", "");
                    }

                    if (rsLaprm.getString("lainnya").equals("ya")) {
                        param.put("lainya", "V");
                        param.put("ketlainya", rsLaprm.getString("ket_lainnya"));
                    } else {
                        param.put("lainya", "");
                        param.put("ketlainya", "");
                    }

                    if (rsLaprm.getString("infus").equals("ya")) {
                        param.put("infus", "V");
                        param.put("tglinfus", rsLaprm.getString("tglinfus"));
                    } else {
                        param.put("infus", "");
                        param.put("tglinfus", "-");
                    }

                    if (rsLaprm.getString("kateter").equals("ya")) {
                        param.put("kateter", "V");
                        param.put("tglkateter", rsLaprm.getString("tglkateter"));
                    } else {
                        param.put("kateter", "");
                        param.put("tglkateter", "-");
                    }

                    if (rsLaprm.getString("ngt").equals("ya")) {
                        param.put("ngt", "V");
                        param.put("tglngt", rsLaprm.getString("tglngt"));
                    } else {
                        param.put("ngt", "");
                        param.put("tglngt", "-");
                    }

                    if (rsLaprm.getString("oksigen").equals("ya")) {
                        param.put("oksigen", "V");
                        param.put("tgloksigen", rsLaprm.getString("tgloksigen"));
                    } else {
                        param.put("oksigen", "");
                        param.put("tgloksigen", "-");
                    }

                    if (rsLaprm.getString("drain").equals("ya")) {
                        param.put("drain", "V");
                        param.put("tgldrain", rsLaprm.getString("tgldrain"));
                    } else {
                        param.put("drain", "");
                        param.put("tgldrain", "-");
                    }

                    if (rsLaprm.getString("lainya_alat").equals("ya")) {
                        param.put("lainalat", "V");
                        param.put("nmalatlain", rsLaprm.getString("nm_alat_lain"));
                        param.put("tgllainalat", rsLaprm.getString("tglalat"));
                    } else {
                        param.put("lainalat", "");
                        param.put("nmalatlain", "-");
                        param.put("tgllainalat", "-");
                    }

                    if (Sequel.cariInteger("select count(-1) from pemberian_obat where "
                            + "no_rawat='" + rsLaprm.getString("no_rawat") + "' and status='Ralan'") == 0) {
                        Valid.MyReport("rptTransferPasienIGDnonResep.jasper", "report", "::[ Laporan Data Transfer & Serah Terima Pasien Rawat Jalan/IGD ]::",
                                "SELECT date(now())", param);
                    } else {
                        Valid.MyReport("rptTransferPasienIGD.jasper", "report", "::[ Laporan Data Transfer & Serah Terima Pasien Rawat Jalan/IGD ]::",
                                "SELECT *, if(jadwal_pemberian='00:00:00','',concat(time_format(jadwal_pemberian,'%H:%i'),', ')) jam1, "
                                + "if(jadwal_pemberian2='00:00:00','',concat(time_format(jadwal_pemberian2,'%H:%i'),', ')) jam2, "
                                + "if(jadwal_pemberian3='00:00:00','',concat(time_format(jadwal_pemberian3,'%H:%i'),', ')) jam3, "
                                + "if(jadwal_pemberian4='00:00:00','',concat(time_format(jadwal_pemberian4,'%H:%i'),', ')) jam4, "
                                + "if(jadwal_pemberian5='00:00:00','',concat(time_format(jadwal_pemberian5,'%H:%i'),', ')) jam5, "
                                + "if(jadwal_pemberian6='00:00:00','',concat(time_format(jadwal_pemberian6,'%H:%i'),', ')) jam6, "
                                + "if(jadwal_pemberian7='00:00:00','',concat(time_format(jadwal_pemberian7,'%H:%i'),', ')) jam7, "
                                + "if(jadwal_pemberian8='00:00:00','',time_format(jadwal_pemberian8,'%H:%i')) jam8 FROM pemberian_obat WHERE "
                                + "no_rawat ='" + rsLaprm.getString("no_rawat") + "' and status='Ralan' and "
                                + "nm_unit='" + Sequel.cariIsi("SELECT p.nm_poli FROM reg_periksa r inner join poliklinik p on p.kd_poli=r.kd_poli "
                                        + "WHERE r.no_rawat='" + rsLaprm.getString("no_rawat") + "'") + "' ORDER BY waktu_simpan desc", param);
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsLaprm != null) {
                    rsLaprm.close();
                }
                if (psLaprm != null) {
                    psLaprm.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void cetakAsesMedikObs() {
        try {
            psLaprm = koneksi.prepareStatement("select *, date_format(tgl_keluar_ponek,'%H:%i:%s') jamklr "
                    + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRw.getText() + "'");
            try {
                rsLaprm = psLaprm.executeQuery();
                while (rsLaprm.next()) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));

                    if (rsLaprm.getString("cek_mengeluh_perut").equals("ya")) {
                        param.put("perut_mules", "Pasien mengeluh perut mules/nyeri mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_mengeluh_perut,'%d-%m-%Y'),', jam : ',time_format(tgl_mengeluh_perut,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("perut_mules", "Pasien mengeluh perut mules/nyeri mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("keluar_lendir").equals("Ya")) {
                        param.put("keluar_lendir", "Keluar lendir darah : " + rsLaprm.getString("keluar_lendir") + ", mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_lendir_ya,'%d-%m-%Y'),', jam : ',time_format(tgl_lendir_ya,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("keluar_lendir", "Keluar lendir darah : " + rsLaprm.getString("keluar_lendir") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("keluar_air").equals("Ya")) {
                        param.put("keluar_air", "Keluar air-air : " + rsLaprm.getString("keluar_air") + ", " + rsLaprm.getString("keluar_air_ya") + " mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_air_ya,'%d-%m-%Y'),', jam : ',time_format(tgl_air_ya,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("keluar_air", "Keluar air-air : " + rsLaprm.getString("keluar_air") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("mengeluh_pusing").equals("Ya")) {
                        param.put("mengeluh_pusing", "Pasien mengeluh pusing : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_pusing,'%d-%m-%Y'),', jam : ',time_format(tgl_pusing,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("mengeluh_pusing", "Pasien mengeluh pusing : " + rsLaprm.getString("mengeluh_pusing") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("nyeri_ulu_hati").equals("Ya")) {
                        param.put("nyeri_ulu", "Nyeri Ulu Hati : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_nyeri,'%d-%m-%Y'),', jam : ',time_format(tgl_nyeri,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("nyeri_ulu", "Nyeri Ulu Hati : " + rsLaprm.getString("nyeri_ulu_hati") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("pandangan_kabur").equals("Ya")) {
                        param.put("pandangan", "Pandangan kabur : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_pandangan,'%d-%m-%Y'),', jam : ',time_format(tgl_pandangan,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("pandangan", "Pandangan kabur : " + rsLaprm.getString("pandangan_kabur") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("mual_muntah").equals("Ya")) {
                        param.put("mual", "Mual/Muntah : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_mual_muntah,'%d-%m-%Y'),', jam : ',time_format(tgl_mual_muntah,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("mual", "Mual/Muntah : " + rsLaprm.getString("mual_muntah") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("batuk_pilek").equals("Ya")) {
                        param.put("batuk", "Batuk/Pilek : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_batuk_pilek,'%d-%m-%Y'),', jam : ',time_format(tgl_batuk_pilek,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("batuk", "Batuk/Pilek : " + rsLaprm.getString("batuk_pilek") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("demam").equals("Ya")) {
                        param.put("demam", "Demam : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_demam,'%d-%m-%Y'),', jam : ',time_format(tgl_demam,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("demam", "Demam : " + rsLaprm.getString("demam") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("pil").equals("ya")) {
                        param.put("cek_pil", "V");
                        param.put("pil", "Pil, lama : " + rsLaprm.getString("lama_pil") + " " + rsLaprm.getString("satuan_pil"));
                    } else {
                        param.put("cek_pil", "");
                        param.put("pil", "Pil, lama : -");
                    }

                    if (rsLaprm.getString("suntik_1_bln").equals("ya")) {
                        param.put("cek_suntik1", "V");
                        param.put("suntik1", "Suntik 1 bulan, lama : " + rsLaprm.getString("lama_1_bln") + " " + rsLaprm.getString("satuan_suntik1"));
                    } else {
                        param.put("cek_suntik1", "");
                        param.put("suntik1", "Suntik 1 bulan, lama : -");
                    }

                    if (rsLaprm.getString("suntik_3_bln").equals("ya")) {
                        param.put("cek_suntik3", "V");
                        param.put("suntik3", "Suntik 3 bulan, lama : " + rsLaprm.getString("lama_3_bln") + " " + rsLaprm.getString("satuan_suntik3"));
                    } else {
                        param.put("cek_suntik3", "");
                        param.put("suntik3", "Suntik 3 bulan, lama : -");
                    }

                    if (rsLaprm.getString("implan").equals("ya")) {
                        param.put("cek_implan", "V");
                        param.put("implan", "Implant, lama : " + rsLaprm.getString("lama_implan") + " " + rsLaprm.getString("satuan_implan"));
                    } else {
                        param.put("cek_implan", "");
                        param.put("implan", "Implant, lama : -");
                    }

                    if (!rsLaprm.getString("uk_usg").equals("")) {
                        param.put("uk_usg", "UK (USG) : " + rsLaprm.getString("uk_usg") + " minggu, tgl. USG : "
                                + Sequel.cariIsi("select date_format(tgl_usg,'%d-%m-%Y') from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("uk_usg", "UK (USG) : - minggu, tgl. USG : -");
                    }

                    if (rsLaprm.getString("cara_datang").equals("Sendiri")) {
                        param.put("cara_datang", rsLaprm.getString("cara_datang"));
                    } else if (rsLaprm.getString("cara_datang").equals("Rujukan Bidan/BPM")) {
                        param.put("cara_datang", rsLaprm.getString("cara_datang") + " : " + rsLaprm.getString("ket_rujukan_bidan"));
                    } else if (rsLaprm.getString("cara_datang").equals("PKM")) {
                        param.put("cara_datang", rsLaprm.getString("cara_datang") + " : " + rsLaprm.getString("ket_pkm"));
                    } else if (rsLaprm.getString("cara_datang").equals("SpOG")) {
                        param.put("cara_datang", rsLaprm.getString("ket_spog") + " SPOG");
                    } else if (rsLaprm.getString("cara_datang").equals("RS Lain")) {
                        param.put("cara_datang", rsLaprm.getString("cara_datang") + " : " + rsLaprm.getString("ket_rs_lain"));
                    } else {
                        param.put("cara_datang", "-");
                    }

                    if (rsLaprm.getString("his_kontraksi").equals("ya")) {
                        param.put("cek_his", "V");
                        param.put("his", "His/kontraksi : " + rsLaprm.getString("ket_his_kontraksi") + " x/10 mnt");
                    } else {
                        param.put("cek_his", "");
                        param.put("his", "His/kontraksi : ....  x/10 mnt");
                    }

                    if (rsLaprm.getString("meninggal").equals("ya")) {
                        param.put("jam_meninggal", rsLaprm.getString("jam_meninggal"));
                    } else {
                        param.put("jam_meninggal", "-");
                    }

                    if (rsLaprm.getString("cek_jam_keluar").equals("ya")) {
                        param.put("jam_keluar", rsLaprm.getString("jamklr"));
                    } else {
                        param.put("jam_keluar", "-");
                    }

                    Valid.MyReport("rptCetakPenilaianAwalMedisObstetriRalan.jasper", "report", "::[ Laporan Asesmen Medik Obstetri hal. 1 ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllhr, "
                            + "CONCAT('Tanggal : ',DATE_FORMAT(pa.tanggal,'%d-%m-%Y'),'  Pukul : ',TIME_FORMAT(pa.tanggal,'%H:%i')) mulai_penang, "
                            + "IF(pa.cervival='ya','V','') cer, IF(pa.rjp='ya','V','') rjp, IF(pa.defribilasi='ya','V','') def, IF(pa.intubasi='ya','V','') intu, IF(pa.vtp='ya','V','') vtp, "
                            + "IF(pa.dekompresi='ya','V','') dek, IF(pa.balut='ya','V','') bal, IF(pa.kateter='ya','V','') kat, IF(pa.ngt='ya','V','') ngt, IF(pa.infus='ya','V','') inf, "
                            + "IF(pa.obat='ya','V','') obt, IF(pa.tidak_ada='ya','V','') tdk_ada, pa.ket_obat, IF(pa.gangguan_jalan_nafas='ya','V','') ggnfs, IF(pa.paten='ya','V','') pat, "
                            + "IF(pa.obstruksi_partial='ya','V','') obsp, pa.data_obstruksi_partial, IF(pa.obstruksi_total='ya','V','') obst, IF(pa.trauma_jalan_nafas='ya','V','') traumajln, "
                            + "pa.trauma, IF(pa.resiko_aspirasi='ya','V','') res, pa.aspirasi, IF(pa.benda_asing='ya','V','') ben, pa.ket_benda_asing, pa.kesimpulan_jalan_nafas, "
                            + "pa.pernafasan, pa.data_pernafasan, pa.gerakan_dada, pa.tipe_pernafasan, pa.kesimpulan_pernafasan, pa.nadi_1, pa.kulit_mukosa, "
                            + "pa.akral_1, pa.crt, pa.kesimpulan_sirkulasi, pa.gcs_e, pa.gcs_v, pa.gcs_m, pa.pupil, pa.diameter_kanan, pa.diameter_kiri, pa.reflek_cahaya, "
                            + "pa.meningeal_sign, pa.lateralisasi, IF(pa.deformitas='ya','V','') def, IF(pa.contusio='ya','V','') con, IF(pa.penerima_edukasi='ya','V','') pen, "
                            + "IF(pa.tenderness='ya','V','') ten, IF(pa.swelling='ya','V','') swe, IF(pa.ekskoriasi='ya','V','') eks, IF(pa.abrasi='ya','V','') abr, "
                            + "IF(pa.burn='ya','V','') bur, IF(pa.laserasi='ya','V','') las, IF(pa.tidak_tampak_jelas='ya','V','') tdktampak, pa.alasan_masuk, "
                            + "pa.dengan_gr, pa.dengan_pr, pa.dengan_a, pa.hamil, pa.ket_dengan, "
                            + "IF(pa.periksa_bidan='Ya',CONCAT('Periksa ketempat bidan : Ya, Hasil / Riwayat Pemeriksaan Bidan : ',pa.hasil_periksa_bidan),CONCAT('Periksa ketempat bidan : ',pa.periksa_bidan,', Hasil / Riwayat Pemeriksaan Bidan : -')) prksa_bidan, "
                            + "IF(pa.riw_jalan_jauh='Ya',CONCAT('Riwayat perjalanan jauh : Ya, ',pa.ket_riw_jalan_jauh),CONCAT('Riwayat perjalanan jauh : ',pa.riw_jalan_jauh,', -')) riw_jln_jauh, "
                            + "pa.os_anc_bidan, pa.dengan_spog1, pa.jml_spog1, pa.dengan_spog2, pa.jml_spog2, IF(pa.hipertensi1='ya','V','') hip1, "
                            + "IF(pa.diabetes1='ya','V','') dm1, IF(pa.jantung1='ya','V','') jan1, IF(pa.asma1='ya','V','') asm1, IF(pa.lainnya1='ya','V','') lain1, "
                            + "pa.ket_lainnya1, IF(pa.hipertensi2='ya','V','') hip2, IF(pa.diabetes2='ya','V','') dm2, IF(pa.jantung2='ya','V','') jan2, IF(pa.asma2='ya','V','') asm2, "
                            + "IF(pa.lainnya2='ya','V','') lain2, pa.ket_lainnya2, pa.riw_ginekologi, pa.riwayat_kb_lain, pa.hpht, pa.hpl, pa.uk, pa.bb_blm_hamil, "
                            + "pa.bb_stlh_hamil, pa.tbi, pa.bmi, pa.lila, IF(pa.dismenorhoe='ya','V','') dismen, IF(pa.spoting='ya','V','') spot, IF(pa.menorrhagia='ya','V','') menor, "
                            + "IF(pa.metrohagia='ya','V','') metro, pa.keluhan_lain, pa.leopold1, pa.leopold2, pa.leopold3, pa.leopold4, IF(pa.nyeri_tekan='ya','V','') nyeri, "
                            + "IF(pa.bandle_ring='ya','V','') band, pa.nadi_2, pa.akral_2 FROM penilaian_awal_medis_obstetri_ralan pa INNER JOIN reg_periksa rp ON rp.no_rawat=pa.no_rawat "
                            + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis where pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);

                    Valid.MyReport("rptCetakPenilaianAwalMedisObstetriRalan1.jasper", "report", "::[ Laporan Asesmen Medik Obstetri hal. 2 ]::",
                            "SELECT pa.tfu, pa.taksiran_bb_janin, IF(pa.teratur='ya','V','')teratur, IF(pa.tdk_teratur='ya','V','')tdkteratur, IF(pa.trs_menerus='ya','V','')trsmenerus, "
                            + "pa.durasi, IF(pa.kuat='ya','V','')kuat, IF(pa.sedang='ya','V','')sedang, IF(pa.lemah='ya','V','')lemah, pa.auskultasi, IF(pa.bersih='ya','V','')bersih, "
                            + "IF(pa.oedema='ya','V','')odema, IF(pa.ruptur='ya','V','')ruptur, IF(pa.candiloma='ya','V','')candi, pa.pemeriksaan_genitalia_lain, pa.inspeksi, pa.konsistensi, "
                            + "pa.periksa_dlm_obstetri, pa.inspekulum, pa.diagnosis_sementara, pa.icd_10, pa.rencana_instruksi, pa.planing, pa.telah_diberikan_informasi_edukasi, "
                            + "pa.rencana_asuhan_diharapkan, p1.nama pemberi_edukasi, pa.penerima_edukasi, p2.nama nmdokter, DATE_FORMAT(pa.tgl_keluar_ponek,'%d-%m-%Y') tglkeluar, "
                            + "pa.opname_diruang, pa.indikasi_msk, pa.dipulangkan_kontrol_ke, pa.dirujuk_ke, pa.alasan_dirujuk, pa.penyebab, pa.k_u, pa.td, pa.hr, pa.rr, pa.temp, "
                            + "pa.spo2, pa.gcs_pulang, p3.nama nmbidan, p4.nama nmdpjp FROM penilaian_awal_medis_obstetri_ralan pa "
                            + "inner join pegawai p1 on p1.nik=pa.nip_pemberi_edukasi inner join pegawai p2 on p2.nik=pa.nip_dokter "
                            + "inner join pegawai p3 on p3.nik=pa.nip_bidan inner join pegawai p4 on p4.nik=pa.nip_dpjp where "
                            + "pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsLaprm != null) {
                    rsLaprm.close();
                }
                if (psLaprm != null) {
                    psLaprm.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void cetakCPPTigd() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (IGD)");
        simpanTemporaryCppt();
        Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Rawat Jalan/IGD ]::",
                "SELECT * from temporary_cppt", param);
    }
    
    private void simpanTemporaryCppt() {
        dataKonfir = "";
        Sequel.AutoComitFalse();
        Sequel.queryu("delete from temporary_cppt");
        try {            
            psCetak = koneksi.prepareStatement("SELECT DISTINCT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, IF(c.cek_jam='ya',concat(date_format(c.tgl_cppt,'%d-%m-%Y'),', ',date_format(c.jam_cppt,'%H:%i')), "
                    + "date_format(c.tgl_cppt,'%d-%m-%Y')) tglcppt, c.bagian, "
                    + "ifnull(if(c.jenis_bagian='' or c.jenis_bagian='-','-',if(c.jenis_bagian='Dokter IGD' or c.jenis_bagian='DPJP' or c.jenis_bagian='DPJP (K)' or c.jenis_bagian='DPJP Raber',c.jenis_bagian,concat(c.jenis_bagian,' : ',c.jenis_ppa))),'-') bagian_cppt, "
                    + "c.hasil_pemeriksaan, "
                    + "concat(c.instruksi_nakes,if(c.jenis_bagian='DPJP' or c.jenis_bagian='DPJP (K)' or c.jenis_bagian='DPJP Raber',concat('\n\n(',pg1.nama,')'),if(c.jenis_bagian='PPA',concat('\n\n(',pg2.nama,')'),''))) instruksi_nakes, "
                    + "concat('(', c.verifikasi,') - ',pg.nama) verif, "
                    + "if(c.serah_terima_cppt='ya',concat('\n\nTgl. ',date_format(c.tgl_cppt,'%d-%m-%Y'),', Jam : ',ifnull(date_format(c.jam_serah_terima,'%H:%i'),'00:00'),'\n','Menyerahkan :\n',pg3.nama),'') ptgsSerah, "
                    + "if(c.serah_terima_cppt='ya',concat('Menerima :\n',pg4.nama),'') ptgsTerima, c.tgl_cppt, c.jam_cppt, c.cppt_shift, c.status, c.no_rawat "
                    + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp LEFT JOIN pegawai pg1 on pg1.nik=c.nip_konsulen LEFT JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                    + "LEFT JOIN pegawai pg3 on pg3.nik=c.nip_petugas_serah LEFT JOIN pegawai pg4 on pg4.nik=c.nip_petugas_terima "
                    + "WHERE c.no_rawat = '" + TNoRw.getText() + "' AND c.STATUS='Ralan' and c.flag_hapus='tidak' ORDER BY c.tgl_cppt, c.jam_cppt");
            try {
                rsCetak = psCetak.executeQuery();
                while (rsCetak.next()) {
                    if (Sequel.cariInteger("select count(-1) from cppt_konfirmasi_terapi where no_rawat='" + rsCetak.getString("no_rawat") + "' "
                            + "and tgl_cppt='" + rsCetak.getString("tgl_cppt") + "' and jam_cppt='" + rsCetak.getString("jam_cppt") + "' "
                            + "and cppt_shift='" + rsCetak.getString("cppt_shift") + "'") == 0) {
                        dataKonfir = "";
                    } else {
                        konfirmasiTerapi(rsCetak.getString("no_rawat"), rsCetak.getString("tgl_cppt"),
                                rsCetak.getString("jam_cppt"), rsCetak.getString("cppt_shift"));
                        dataKonfir = konfirmasi_terapi;
                    }

                    Sequel.menyimpan("temporary_cppt",
                            "'" + rsCetak.getString("no_rkm_medis") + "','"
                            + rsCetak.getString("nm_pasien") + "','"
                            + rsCetak.getString("tgllhr") + "','"
                            + rsCetak.getString("tglcppt") + "','"
                            + rsCetak.getString("bagian") + "','"
                            + rsCetak.getString("bagian_cppt") + "','"
                            + rsCetak.getString("hasil_pemeriksaan") + "','"
                            + rsCetak.getString("instruksi_nakes") + "','"
                            + rsCetak.getString("verif") + "','"
                            + rsCetak.getString("ptgsSerah") + "','"
                            + rsCetak.getString("ptgsTerima") + "','"
                            + rsCetak.getString("tgl_cppt") + "','"
                            + rsCetak.getString("jam_cppt") + "','"
                            + rsCetak.getString("cppt_shift") + "','"
                            + rsCetak.getString("status") + "','" + dataKonfir + "','','','','','','','','','','','','','','','','','','','','',''", "CPPT");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsCetak != null) {
                    rsCetak.close();
                }
                if (psCetak != null) {
                    psCetak.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        Sequel.AutoComitTrue();
    }
    
    private void konfirmasiTerapi(String norw, String tgl, String jam, String sift) {
        konfirmasi_terapi = "";
        try {
            ps1 = koneksi.prepareStatement("SELECT date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i WITA') jamlapor, "
                    + "date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i WITA') jamverif, "
                    + "pg1.nama ptgs, pg2.nama dpjp FROM cppt_konfirmasi_terapi ck INNER JOIN pegawai pg1 ON pg1.nik = ck.nip_petugas_konfir "
                    + "INNER JOIN pegawai pg2 ON pg2.nik = ck.nip_dpjp_konfir WHERE "
                    + "ck.no_rawat = '" + norw + "' and ck.tgl_cppt='" + tgl + "' and ck.jam_cppt='" + jam + "' and ck.cppt_shift='" + sift + "' "
                    + "ORDER BY ck.waktu_simpan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    if (konfirmasi_terapi.equals("")) {
                        konfirmasi_terapi
                                = "KONFIRMASI TERAPI VIA TELP. :\nTgl. Lapor : " + rs1.getString("tgllapor") + ", Jam : " + rs1.getString("jamlapor") + "\n"
                                + "Tgl. Verif : " + rs1.getString("tglverif") + ", Jam : " + rs1.getString("jamverif") + "\n"
                                + "Petugas,\n\n\n\n(" + rs1.getString("ptgs") + ")\n\n"
                                + "Dengan DPJP,\n\n\n\n(" + rs1.getString("dpjp") + ")";
                    } else {
                        konfirmasi_terapi = konfirmasi_terapi + "\n"
                                + "KONFIRMASI TERAPI VIA TELP. :\nTgl. Lapor : " + rs1.getString("tgllapor") + ", Jam : " + rs1.getString("jamlapor") + "\n"
                                + "Tgl. Verif : " + rs1.getString("tglverif") + ", Jam : " + rs1.getString("jamverif") + "\n"
                                + "Petugas,\n\n\n\n(" + rs1.getString("ptgs") + ")\n\n"
                                + "Dengan DPJP,\n\n\n\n(" + rs1.getString("dpjp") + ")";
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
}
